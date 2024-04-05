package cat.wavy.catactivity.service

import cat.wavy.catactivity.setting.CatActivitySettingProjectState
import cat.wavy.catactivity.setting.DisplayMode
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project

private const val groupId = "Cat Activity Notifications"

class WelcomeService {
    private class ShowAction(private val notification: Notification,
                             private val timeService: TimeService?,
                             private val mode: DisplayMode,
                             title: String
    ) : AnAction(title) {
        override fun actionPerformed(e: AnActionEvent) {
            val configState = e.project?.service<CatActivitySettingProjectState>()?.state
            if (configState != null) {
                configState.displayMode = mode
                notification.expire()
                timeService?.render(e.project!!)
            }
        }
    }

    companion object {
        /**
         * Displays a welcome notification and returns true if it is the first initialization.
         * Otherwise, it does nothing and returns false.
         */
        fun welcomeAlert(project: Project, timeService: TimeService): Boolean {
            // Perhaps this is a bad implementation. WavyCat, think about it.
            val configState = project.service<CatActivitySettingProjectState>().state

            if (configState.firstInit) {
                val title = "Welcome to Cat Activity!"
                val content = "What details would you like to showcase in your profile?"
                val notification = Notification(groupId, title, content, NotificationType.INFORMATION)

                notification.addAction(ShowAction(notification, timeService, DisplayMode.IDE, "Only IDE"))
                notification.addAction(ShowAction(notification, timeService, DisplayMode.Project, "Project"))
                notification.addAction(ShowAction(notification, timeService, DisplayMode.File, "Project and File"))
                notification.addAction(ShowAction(notification, null, DisplayMode.Disable, "Disable"))

                Notifications.Bus.notify(notification, project)

                configState.firstInit = false
                return true
            }
            return false
        }
    }
}