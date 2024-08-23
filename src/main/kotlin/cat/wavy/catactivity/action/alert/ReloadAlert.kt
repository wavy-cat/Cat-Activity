package cat.wavy.catactivity.action.alert

import cat.wavy.catactivity.NOTIFICATION_GROUP_ID
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
    val title = "Connection to Discord lost"
    val content = when (message) {
        null -> "Do you want to try reconnecting?"
        else -> "Reason: $message. Do you want to try reconnecting?"
    }

    val notification = Notification(NOTIFICATION_GROUP_ID, title, content, NotificationType.INFORMATION)

    notification.addAction(ReloadAction(notification, "Reconnect"))
    notification.addAction(DismissAction(notification, "Dismiss"))

    Notifications.Bus.notify(notification, project)
}