package cat.wavy.catactivity.action

import cat.wavy.catactivity.setting.CatActivitySettingProjectState
import cat.wavy.catactivity.service.TimeService
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.DumbAware

class SelectDisableActivity : ToggleAction("Disable Activity"), DumbAware {
    override fun isSelected(e: AnActionEvent): Boolean {
        val project = e.project ?: return false
        val configState = project.service<CatActivitySettingProjectState>().state

        return !configState.isEnabled
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        val project = e.project ?: return
        val configState = project.service<CatActivitySettingProjectState>().state
        val timeService = project.service<TimeService>()

        configState.isEnabled = !state
        timeService.render(project)
    }
}