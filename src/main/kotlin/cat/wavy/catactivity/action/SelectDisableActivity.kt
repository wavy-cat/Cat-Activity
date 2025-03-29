package cat.wavy.catactivity.action

import cat.wavy.catactivity.setting.CatActivitySettingProjectState
import cat.wavy.catactivity.service.TimeService
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.DumbAware

class SelectDisableActivity : AnAction("Disable Activity"), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        project.service<CatActivitySettingProjectState>().state.isEnabled = false
        project.service<TimeService>().render(project)
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabled = e.project != null
    }
}