package cat.wavy.catactivity.action

import cat.wavy.catactivity.render.ActivityRender
import cat.wavy.catactivity.setting.ActivityNotificationService
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.DumbAwareAction

class ReloadAction : DumbAwareAction() {
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        project.service<ActivityRender>().reinit()
        project.service<ActivityNotificationService>().ignoreReloadAlert.set(false)
        thisLogger().info("ActivityRender is forcefully reinitialized")
    }
}