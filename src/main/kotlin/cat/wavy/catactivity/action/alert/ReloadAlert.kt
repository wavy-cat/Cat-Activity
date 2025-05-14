package cat.wavy.catactivity.action.alert

import cat.wavy.catactivity.NOTIFICATION_GROUP_ID
import cat.wavy.catactivity.bundle.ToolsBundle
import cat.wavy.catactivity.render.ActivityRender
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project

private class ReloadAction(
    private val notification: Notification,
    title: String
) : AnAction(title) {
    override fun actionPerformed(p0: AnActionEvent) {
        service<ActivityRender>().reinit()
        notification.expire()
    }
}

private class DismissAction(
    private val notification: Notification,
    title: String
) : AnAction(title) {
    override fun actionPerformed(e: AnActionEvent) {
        service<ActivityRender>().ignoreFlag = true
        notification.expire()
    }
}


fun reloadAlert(project: Project, message: String?) {
    val title = ToolsBundle.message("reloadAlert.title")
    val content = when (message) {
        null -> ToolsBundle.message("reloadAlert.content.noMessage")
        else -> ToolsBundle.message("reloadAlert.content.withMessage", message)
    }

    val notification = Notification(NOTIFICATION_GROUP_ID, title, content, NotificationType.INFORMATION)

    notification.addAction(ReloadAction(notification, ToolsBundle.message("reloadAlert.action.reconnect")))
    notification.addAction(DismissAction(notification, ToolsBundle.message("reloadAlert.action.dismiss")))

    Notifications.Bus.notify(notification, project)
}
