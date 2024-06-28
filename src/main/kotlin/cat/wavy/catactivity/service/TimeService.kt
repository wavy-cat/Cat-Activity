package cat.wavy.catactivity.service

import com.google.common.cache.CacheBuilder
import com.intellij.analysis.problemsView.ProblemsCollector
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ex.ApplicationInfoEx
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.ex.EditorEventMulticasterEx
import com.intellij.openapi.editor.ex.FocusChangeListener
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import git4idea.GitUtil
import cat.wavy.catactivity.ICONS_URL
import cat.wavy.catactivity.CatActivity.logger
import cat.wavy.catactivity.action.reloadAlert
import cat.wavy.catactivity.action.welcomeAlert
import cat.wavy.catactivity.render.ActivityWrapper
import cat.wavy.catactivity.render.ActivityRender
import cat.wavy.catactivity.setting.CatActivitySettingProjectState
import cat.wavy.catactivity.setting.Details
import cat.wavy.catactivity.setting.SettingState
import cat.wavy.catactivity.types.*
import org.jetbrains.concurrency.runAsync
import java.lang.ref.WeakReference

@Service
class TimeService : Disposable {
    private val startTime = System.currentTimeMillis()
    private var timeTracker = CacheBuilder.newBuilder()
        .expireAfterAccess(1, java.util.concurrent.TimeUnit.HOURS)
        .maximumSize(128)
        .build<String, Long>()
    private var editingFile: FileItem? = null
    private var editingProject: ProjectItem? = null

    init {
        val multicaster: Any = EditorFactory.getInstance().eventMulticaster
        if (multicaster is EditorEventMulticasterEx) {
            multicaster.addFocusChangeListener(object : FocusChangeListener {
                override fun focusGained(editor: Editor) {
                    val project = editor.project ?: return
                    val file = FileDocumentManager.getInstance().getFile(editor.document) ?: return
                    onFileChanged(project, file)
                }
            }, this)
        }
    }

    fun onProjectOpened(project: Project) {
        timeTracker.put("project:${project.name}", System.currentTimeMillis())
        editingProject = ProjectItem.from(project)
        val firstInit = welcomeAlert(project, this)
        if (!firstInit) {
            render(project)
        }
    }

    fun onProjectClosed(project: Project) {
        timeTracker.invalidate("project:${project.name}")
        editingProject = null
        val activityRender = service<ActivityRender>()
        activityRender.clearActivity()
    }

    fun onFileOpened(project: Project, file: VirtualFile) {
        timeTracker.put("file:${file.name}", System.currentTimeMillis())
        editingProject = ProjectItem.from(project)
        editingFile = FileItem.from(file)
        render(project)
    }

    fun onFileClosed(project: Project, file: VirtualFile) {
        timeTracker.invalidate("file:${file.name}")
        editingFile = null
        render(project)
    }

    fun onFileChanged(project: Project, file: VirtualFile) {
        editingFile = FileItem.from(file)
        editingProject = ProjectItem.from(project)
        render(project)
    }

    fun render(project: Project) {
        runAsync {
            runCatching {
                val configState = project.service<CatActivitySettingProjectState>().state
                val problemsCollector = ProblemsCollector.getInstance(project)
                val repo = editingFile?.file?.get()?.let {
                    GitUtil.getRepositoryManager(project).getRepositoryForFile(it)
                }

                val activityRender = service<ActivityRender>()
                val activityWrapper: ActivityWrapper

                if (!configState.isEnabled) {
                    activityRender.clearActivity()
                    return@runAsync
                }

                when (configState.details) {
                    Details.File -> {
                        val variables = mutableMapOf(
                            "%projectName%" to (editingProject?.projectName ?: DefaultVars.PROJECTNAME.default),
                            "%projectPath%" to (editingProject?.projectPath ?: DefaultVars.PROJECTPATH.default),
                            "%projectProblems%" to problemsCollector.getProblemCount().toString(),
                            "%fileName%" to (editingFile?.fileName ?: DefaultVars.FILENAME.default),
                            "%filePath%" to (editingFile?.filePath ?: DefaultVars.FILEPATH.default),
                            "%fileProblems%" to (editingFile?.file?.get()
                                ?.let { problemsCollector.getFileProblemCount(it) } ?: 0).toString(),
                            "%branch%" to (repo?.currentBranch?.name ?: DefaultVars.BRANCH.default),
                            "%repository%" to (repo?.project?.name ?: DefaultVars.REPO.default),
                            "%totalLines%" to (editingFile?.linesCount?.toString() ?: DefaultVars.LINESCOUNT.default),
                            "%fileSize%" to (editingFile?.fileSize?.formatBytes() ?: DefaultVars.FILESIZE.default)
                        )

                        activityWrapper = ActivityWrapper(
                            state = configState.fileStateFormat.replaceVariables(variables),
                            details = configState.fileDetailFormat.ifBlank { null }?.replaceVariables(variables),
                            startTimestamp = editingFile?.key?.let { timeTracker.getIfPresent(it) },
                        ).applyIDEInfo(project).applyFileInfo(project)

                        logger.warn("Rendering file: ${configState.fileStateFormat.replaceVariables(variables)}")
                    }

                    Details.Project -> {
                        val branchName = repo?.currentBranch?.name ?: DefaultVars.BRANCH.default
                        val repoName = repo?.project?.name ?: DefaultVars.REPO.default

                        val variables = mutableMapOf(
                            "%projectName%" to (editingProject?.projectName ?: DefaultVars.PROJECTNAME.default),
                            "%projectPath%" to (editingProject?.projectPath ?: DefaultVars.PROJECTPATH.default),
                            "%projectProblems%" to problemsCollector.getProblemCount().toString(),
                            "%branch%" to branchName,
                            "%repository%" to repoName,
                        )

                        activityWrapper = ActivityWrapper(
                            state = configState.projectStateFormat.replaceVariables(variables),
                            details = configState.projectDetailFormat.ifBlank { null }?.replaceVariables(variables),
                            startTimestamp = editingProject?.key?.let { timeTracker.getIfPresent(it) },
                        ).applyIDEInfo(project)
                    }

                    Details.IDE -> {
                        activityWrapper = ActivityWrapper(
                            state = ApplicationInfoEx.getInstanceEx().fullApplicationName,
                            startTimestamp = startTime,
                        ).applyIDEInfo(project)
                    }
                }

                activityWrapper.let {
                    val handler = ErrorHandler(project, activityRender)
                    activityRender.updateActivity(it, handler::handle)
                }
            }.onFailure {
                it.printStackTrace()
                println("Failed to render activity: ${it.message}")
            }
        }
    }

    class ErrorHandler(
        private val project: Project,
        private val activityRender: ActivityRender
    ) {
        private var used = false
        fun handle(message: String?) {
            if (used) {
                return
            }
            used = true
            reloadAlert(project, activityRender, message)
        }
    }

    private fun getLangIconUrl(state: SettingState, icon: String): String {
        return ICONS_URL + "/${state.usingTheme.name}/$icon.png"
    }

    private fun getIDEIconUrl(state: SettingState): String {
        val ide = if (state.usingDefaultIDEName) IDEType.JETBRAINS else currentIDEType
        return ICONS_URL + "/IDE/${state.usingTheme.name}/${ide.icon}.png"
    }

    private fun ActivityWrapper.applyIDEInfo(project: Project): ActivityWrapper {
        val state = project.service<CatActivitySettingProjectState>().state
        largeImageKey = getIDEIconUrl(state)
        largeImageText = currentIDEType.title
        return this
    }

    private fun ActivityWrapper.applyFileInfo(project: Project): ActivityWrapper {
        val state = project.service<CatActivitySettingProjectState>().state
        editingFile?.let {
            val type = getFileTypeByName(it.type, it.fileName, it.extension)
            smallImageKey = getIDEIconUrl(state)
            smallImageText = largeImageText // swap
            largeImageKey = getLangIconUrl(state, type.icon)
            largeImageText = type.typeName
        }
        return this
    }

    override fun dispose() {
    }
}

sealed class TimedItem(
    val key: String
)

private fun String.replaceVariables(variables: Map<String, String>): String {
    var result = this
    variables.forEach { (key, value) ->
        result = result.replace(key, value)
    }
    return result
}

private fun Long.formatBytes(): String {
    return when {
        this < 1024 -> "$this B"
        this < 1048576 -> "${(this / 1024)} KB"
        this < 1073741824 -> "${(this / 1048576)} MB"
        this < 1099511627776 -> "${(this / 1073741824)} GB"
        else -> "${(this / 1073741824)} TB"
    }
}

class ProjectItem(
    key: String,
    val projectName: String,
    val projectPath: String?,
) : TimedItem(key) {
    companion object {
        fun from(project: Project): ProjectItem {
            return ProjectItem(
                "project:${project.name}",
                project.name,
                project.basePath
            )
        }
    }
}

class FileItem(
    key: String,
    val file: WeakReference<VirtualFile>,
    val fileName: String,
    val type: String,
    val extension: String?,
    val filePath: String,
    val linesCount: Int?,
    val fileSize: Long
) : TimedItem(key) {
    companion object {
        fun from(file: VirtualFile): FileItem {
            return FileItem(
                "file:${file.name}",
                WeakReference(file),
                file.name,
                file.fileType.name,
                file.extension,
                file.path,
                FileDocumentManager.getInstance().getDocument(file)?.lineCount,
                file.length
            )
        }
    }
}