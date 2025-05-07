package cat.wavy.catactivity.action

import cat.wavy.catactivity.setting.CatActivitySettingProjectState
import cat.wavy.catactivity.setting.Details
import cat.wavy.catactivity.service.TimeService
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.DumbAware

class SelectShowProject : ToggleAction("Show Project"), DumbAware {
    override fun isSelected(e: AnActionEvent): Boolean {
        val project = e.project ?: return false
        val configState = project.service<CatActivitySettingProjectState>().state

        return configState.details == Details.Project && configState.isEnabled
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        if (!state) return

        val project = e.project ?: return
        val configState = project.service<CatActivitySettingProjectState>().state
        val timeService = project.service<TimeService>()

        configState.details = Details.Project
        configState.isEnabled = true
        timeService.render(project)
    }
}