package cat.wavy.catactivity.action.alert

import cat.wavy.catactivity.NOTIFICATION_GROUP_ID
import cat.wavy.catactivity.bundle.ToolsBundle
import cat.wavy.catactivity.render.ActivityRender
import cat.wavy.catactivity.setting.ActivityNotificationService
import cat.wavy.catactivity.setting.CatActivitySettingProjectState
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project

private class ReloadAction(
    private val notification: Notification,
    title: String
) : AnAction(title) {
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        project.service<ActivityNotificationService>().ignoreReloadAlert.set(false)
        project.service<ActivityRender>().reinit()
        notification.expire()
    }
}

private class DismissAction(
    private val notification: Notification,
    title: String
) : AnAction(title) {
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        project.service<ActivityNotificationService>().ignoreReloadAlert.set(true)
        notification.expire()
    }
}

private class DisableAction(
    private val notification: Notification,
    title: String
) : AnAction(title) {
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun actionPerformed(e: AnActionEvent) {
        val configState = e.project?.service<CatActivitySettingProjectState>()?.state
        configState?.isEnabled = false
        e.project?.service<ActivityRender>()?.clearActivity()
        notification.expire()
    }
}

fun reloadAlert(project: Project, message: String?) {
    if (project.service<ActivityNotificationService>().ignoreReloadAlert.compareAndSet(false, true)) {
        val title = ToolsBundle.message("reloadAlert.title")
        val content = when (message) {
            null -> ToolsBundle.message("reloadAlert.content.noMessage")
            else -> ToolsBundle.message("reloadAlert.content.withMessage", message)
        }

        val notification = Notification(NOTIFICATION_GROUP_ID, title, content, NotificationType.INFORMATION)

        notification.addAction(ReloadAction(notification, ToolsBundle.message("reloadAlert.action.reconnect")))
        notification.addAction(DisableAction(notification, ToolsBundle.message("reloadAlert.action.disable")))
        notification.addAction(DismissAction(notification, ToolsBundle.message("reloadAlert.action.dismiss")))

        Notifications.Bus.notify(notification, project)
    }
}