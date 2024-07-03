package cat.wavy.catactivity.action

import cat.wavy.catactivity.CatActivity
import cat.wavy.catactivity.render.ActivityRender
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.DumbAware

class ReloadAction : AnAction("Reconnect to Discord"), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        service<ActivityRender>().reinit()
        CatActivity.logger.info("ActivityRender is forcefully reinitialized")
    }
}