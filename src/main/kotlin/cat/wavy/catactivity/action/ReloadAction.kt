package cat.wavy.catactivity.action

import cat.wavy.catactivity.render.ActivityRender
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.DumbAwareAction

class ReloadAction : DumbAwareAction() {
    override fun actionPerformed(e: AnActionEvent) {
        service<ActivityRender>().reinit()
        thisLogger().info("ActivityRender is forcefully reinitialized")
    }
}