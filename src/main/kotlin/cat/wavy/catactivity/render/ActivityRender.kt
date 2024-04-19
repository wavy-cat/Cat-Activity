package cat.wavy.catactivity.render

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.Service
import de.jcm.discordgamesdk.ActivityManager
import de.jcm.discordgamesdk.Core
import de.jcm.discordgamesdk.CreateParams
import de.jcm.discordgamesdk.activity.Activity
import kotlinx.coroutines.*
import cat.wavy.catactivity.CatActivity
import cat.wavy.catactivity.setting.CatActivitySettingProjectState
import cat.wavy.catactivity.types.applicationId
import cat.wavy.catactivity.types.defaultApplicationId
import com.intellij.openapi.components.service
import com.intellij.openapi.project.ProjectManager
import java.util.*

@Service
class ActivityRender : Disposable {
    private lateinit var activityManager: ActivityManager
    private val scope = CoroutineScope(
        Dispatchers.Default + SupervisorJob()
    )

    // TODO: Maybe let use to re-init discord rp if they want
    private fun init() = kotlin.runCatching {
        val state = ProjectManager.getInstance().openProjects.firstOrNull()
            ?.getService(CatActivitySettingProjectState::class.java)?.state
        val core = Core(
            CreateParams().apply {
                clientID = if (state?.usingDefaultIDEName == true) defaultApplicationId else applicationId
                flags = CreateParams.getDefaultFlags()
            }
        )
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
        CatActivity.logger.info("ActivityRender init result: ${it.isSuccess}")
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
        scope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                activityManager.updateActivity(activityNative)
            }
                .onFailure {
                    CatActivity.logger.warn("Failed to update activity: " + it.message)
                }
        }
    }

    fun clearActivity() = kotlin.runCatching {
        activityManager.clearActivity()
    }

    override fun dispose() {
        clearActivity()
        scope.cancel()
    }
}