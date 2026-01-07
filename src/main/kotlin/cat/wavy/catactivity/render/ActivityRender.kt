package cat.wavy.catactivity.render

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import de.jcm.discordgamesdk.ActivityManager
import de.jcm.discordgamesdk.Core
import de.jcm.discordgamesdk.CreateParams
import de.jcm.discordgamesdk.activity.Activity
import de.jcm.discordgamesdk.activity.ActivityButton
import de.jcm.discordgamesdk.activity.ActivityButtonsMode
import cat.wavy.catactivity.types.currentIDEType
import com.intellij.openapi.project.Project
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.util.*
import java.util.concurrent.Executors
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Service(Service.Level.PROJECT)
class ActivityRender(private val project: Project) : Disposable {
    private lateinit var core: Core
    private lateinit var activityManager: ActivityManager

    private var discordExecutor = Executors.newSingleThreadExecutor { r ->
        Thread(r, "CatActivity-DiscordSDK").apply { isDaemon = true }
    }
    private var discordDispatcher = discordExecutor.asCoroutineDispatcher()

    private var scope = CoroutineScope(SupervisorJob() + discordDispatcher)

    private lateinit var lastActivity: ActivityWrapper
    var clientID = currentIDEType.applicationId

    var onError: ((Project, String?) -> Unit)? = null

    private var updates = Channel<Activity>(capacity = Channel.CONFLATED)

    private fun init() = kotlin.runCatching {
        core = Core(
            CreateParams().apply {
                clientID = this@ActivityRender.clientID
                flags = CreateParams.getDefaultFlags()
            }
        )
        activityManager = core.activityManager()

        scope.launch {
            delay(1.seconds)
            while (isActive) {
                runCatching {
                    core.runCallbacks()
                }.onFailure {
                    thisLogger().warn("Discord runCallbacks failed: ${it.message}")
                }
                delay(100.milliseconds)
            }
        }

        scope.launch {
            for (activityNative in updates) {
                runCatching {
                    withTimeout(2.seconds) {
                        activityManager.updateActivity(activityNative)
                    }
                }.onFailure { t ->
                    thisLogger().warn("Failed to update activity: ${t.message}")
                    onError?.invoke(project, t.message)
                }
            }
        }
    }.also {
        thisLogger().info("ActivityRender init result: ${it.isSuccess}")
    }

    init {
        init()
    }

    fun updateActivity(activity: ActivityWrapper) = kotlin.runCatching {
        val activityNative = Activity().apply {
            state = activity.state
            details = activity.details
            activity.startTimestamp?.let { timestamps().start = Date(it).toInstant() }
            activity.endTimestamp?.let { timestamps().end = Date(it).toInstant() }

            assets().largeImage = activity.largeImageKey
            assets().largeText = activity.largeImageText
            assets().smallImage = activity.smallImageKey
            assets().smallText = activity.smallImageText

            if (activity.buttonLabel != null && activity.buttonLink != null) {
                activityButtonsMode = ActivityButtonsMode.BUTTONS
                addButton(ActivityButton(activity.buttonLabel, activity.buttonLink))
            }
        }

        lastActivity = activity

        updates.trySend(activityNative)
    }

    fun clearActivity() = kotlin.runCatching {
        scope.launch {
            runCatching {
                withTimeout(2.seconds) {
                    activityManager.clearActivity()
                }
            }.onFailure {
                thisLogger().warn("Failed to clear activity: ${it.message}")
            }
        }
    }

    fun reinit(update: Boolean = true) {
        dispose()

        runCatching { discordExecutor.awaitTermination(3, java.util.concurrent.TimeUnit.SECONDS) }
        discordExecutor = Executors.newSingleThreadExecutor { r ->
            Thread(r, "CatActivity-DiscordSDK").apply { isDaemon = true }
        }
        discordDispatcher = discordExecutor.asCoroutineDispatcher()

        updates = Channel(Channel.CONFLATED)
        scope = CoroutineScope(SupervisorJob() + discordDispatcher)

        init()

        if (this::lastActivity.isInitialized && update) updateActivity(lastActivity)
    }

    override fun dispose() {
        runCatching { clearActivity() }
        runCatching { updates.close() }
        runCatching { scope.cancel() }
        runCatching { core.close() }
        runCatching { discordExecutor.shutdown() }
    }
}