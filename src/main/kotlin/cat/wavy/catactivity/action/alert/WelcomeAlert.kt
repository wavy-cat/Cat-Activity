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
    private val timeService: TimeService,
    private val details: Details,
    title: String
) : AnAction(title) {
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        project.service<CatActivitySettingProjectState>().state.let {
            it.details = details
            it.isEnabled = true
            it.firstInit = false
        }
        timeService.render(project)
        notification.expire()
    }
}

private class ShowActionDisable(
    private val notification: Notification,
    title: String
) : AnAction(title) {
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        project.service<CatActivitySettingProjectState>().state.let {
            it.isEnabled = false
            it.firstInit = false
        }
        notification.expire()
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
        notification.addAction(ShowActionDisable(notification, ToolsBundle.message("welcomeAlert.action.disable")))

        Notifications.Bus.notify(notification, project)
        return true
    }
    return false
}
