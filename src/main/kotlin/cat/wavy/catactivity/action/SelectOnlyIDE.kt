package cat.wavy.catactivity.action

import cat.wavy.catactivity.service.TimeService
import cat.wavy.catactivity.setting.CatActivitySettingProjectState
import cat.wavy.catactivity.setting.Details
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.DumbAware

class SelectOnlyIDE : ToggleAction("Show only IDE"), DumbAware {
    override fun isSelected(e: AnActionEvent): Boolean {
        val project = e.project ?: return false
        val configState = project.service<CatActivitySettingProjectState>().state

        return configState.details == Details.IDE && configState.isEnabled
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        if (!state) return

        val project = e.project ?: return
        with(project.service<CatActivitySettingProjectState>().state) {
            details = Details.IDE
            isEnabled = true
        }
        project.service<TimeService>().render(project)
    }
}