package cat.wavy.catactivity.render

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.Service
import de.jcm.discordgamesdk.ActivityManager
import de.jcm.discordgamesdk.Core
import de.jcm.discordgamesdk.CreateParams
import de.jcm.discordgamesdk.activity.Activity
import kotlinx.coroutines.*
import cat.wavy.catactivity.action.alert.reloadAlert
import cat.wavy.catactivity.types.currentIDEType
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.ProjectManager
import de.jcm.discordgamesdk.activity.ActivityButton
import de.jcm.discordgamesdk.activity.ActivityButtonsMode
import java.util.*

@Service
class ActivityRender : Disposable {
    private lateinit var activityManager: ActivityManager
    private var scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private lateinit var lastActivity: ActivityWrapper
    var clientID = currentIDEType.applicationId
    var ignoreFlag = false

    private fun init() = kotlin.runCatching {
        val core = Core(
            CreateParams().apply {
                clientID = this@ActivityRender.clientID
                flags = CreateParams.getDefaultFlags()
            })
        scope.launch(Dispatchers.IO) {
            delay(1000L)
            runCatching {
                core.runCallbacks()
            }.onFailure {
                it.printStackTrace()
            }
        }
        activityManager = core.activityManager()
    }.also {
        thisLogger().info("ActivityRender init result: ${it.isSuccess}")
    }

    init {
        init()
    }

    fun updateActivity(activity: ActivityWrapper) = kotlin.runCatching {
        val activityNative = Activity()
        activityNative.state = activity.state
        activityNative.details = activity.details
        activity.startTimestamp?.let {
            activityNative.timestamps().start = Date(it).toInstant()
        }
        activity.endTimestamp?.let {
            activityNative.timestamps().end = Date(it).toInstant()
        }
        activityNative.assets().largeImage = activity.largeImageKey
        activityNative.assets().largeText = activity.largeImageText
        activityNative.assets().smallImage = activity.smallImageKey
        activityNative.assets().smallText = activity.smallImageText

        if (activity.buttonLabel != null && activity.buttonLink != null) {
            activityNative.activityButtonsMode = ActivityButtonsMode.BUTTONS
            activityNative.addButton(ActivityButton(activity.buttonLabel, activity.buttonLink))
        }

        lastActivity = activity
        scope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                activityManager.updateActivity(activityNative)
            }.onFailure {
                thisLogger().warn("Failed to update activity: " + it.message)
                val project = ProjectManager.getInstance().openProjects.firstOrNull()
                if (project != null && !ignoreFlag) {
                    reloadAlert(project, it.message)
                    ignoreFlag = true
                }
            }
        }
    }

    fun clearActivity() = kotlin.runCatching {
        activityManager.clearActivity()
    }

    fun reinit(update: Boolean = true) {
        dispose()
        scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
        init()
        ignoreFlag = false
        if (this::lastActivity.isInitialized && update) {
            updateActivity(lastActivity)
        }
    }

    override fun dispose() {
        clearActivity()
        scope.cancel()
    }
}