package cat.wavy.catactivity.action.alert

import cat.wavy.catactivity.NOTIFICATION_GROUP_ID
import cat.wavy.catactivity.bundle.ToolsBundle
import cat.wavy.catactivity.service.TimeService
import cat.wavy.catactivity.setting.CatActivitySettingProjectState
import cat.wavy.catactivity.setting.Details
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project

private class ShowAction(
    private val notification: Notification,
    private val timeService: TimeService?,
    private val details: Details?,
    title: String
) : AnAction(title) {
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun actionPerformed(e: AnActionEvent) {
        val configState = e.project?.service<CatActivitySettingProjectState>()?.state
        if (configState != null && details != null) {
            configState.details = details
            configState.isEnabled = true
            notification.expire()
            timeService?.render(e.project!!)
        } else if (details == null) {
            configState?.isEnabled = false
            notification.expire()
        }
    }
}

/**
 * Displays a welcome notification and returns true if it is the first initialization.
 * Otherwise, it does nothing and returns false.
 */
fun welcomeAlert(project: Project, timeService: TimeService): Boolean {
    val configState = project.service<CatActivitySettingProjectState>().state

    if (configState.firstInit) {
        val title = ToolsBundle.message("welcomeAlert.title")
        val content = ToolsBundle.message("welcomeAlert.content")
        val notification = Notification(NOTIFICATION_GROUP_ID, title, content, NotificationType.INFORMATION)

        notification.addAction(ShowAction(notification, timeService, Details.IDE, ToolsBundle.message("welcomeAlert.action.onlyIDE")))
        notification.addAction(ShowAction(notification, timeService, Details.Project, ToolsBundle.message("welcomeAlert.action.project")))
        notification.addAction(ShowAction(notification, timeService, Details.File, ToolsBundle.message("welcomeAlert.action.projectAndFile")))
        notification.addAction(ShowAction(notification, null, null, ToolsBundle.message("welcomeAlert.action.disable")))

        Notifications.Bus.notify(notification, project)

        configState.firstInit = false
        return true
    }
    return false
}
