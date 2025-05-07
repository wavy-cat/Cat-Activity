package cat.wavy.catactivity.listener

import com.intellij.analysis.problemsView.Problem
import com.intellij.analysis.problemsView.ProblemsListener
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.service
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.vfs.VirtualFile
import cat.wavy.catactivity.service.TimeService

class PostStartListener : ProjectActivity {
    override suspend fun execute(project: Project) {
        project.service<TimeService>().onProjectOpened(project)
    }
}

class ProjectListener : ProjectManagerListener {
    override fun projectClosing(project: Project) {
        project.service<TimeService>().onProjectClosed(project)
    }
}

class FileListener : FileEditorManagerListener {
    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        source.project.service<TimeService>().onFileOpened(source.project, file)
    }

    override fun fileClosed(source: FileEditorManager, file: VirtualFile) {
        source.project.service<TimeService>().onFileClosed(source.project, file)
    }

    override fun selectionChanged(event: FileEditorManagerEvent) {
        event.newFile?.let {
            event.manager.project.service<TimeService>().onFileChanged(event.manager.project, it)
        }
    }
}

class FileProblemListener : ProblemsListener {
    override fun problemAppeared(problem: Problem) {
        ApplicationManager.getApplication().invokeLater {
            problem.provider.project.service<TimeService>().render(problem.provider.project)
        }
    }

    override fun problemDisappeared(problem: Problem) {
        ApplicationManager.getApplication().invokeLater {
            problem.provider.project.service<TimeService>().render(problem.provider.project)
        }
    }

    override fun problemUpdated(problem: Problem) {
        ApplicationManager.getApplication().invokeLater {
            problem.provider.project.service<TimeService>().render(problem.provider.project)
        }
    }
}