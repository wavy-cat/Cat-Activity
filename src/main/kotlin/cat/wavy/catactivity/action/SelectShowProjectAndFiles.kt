package cat.wavy.catactivity.action

import cat.wavy.catactivity.setting.CatActivitySettingProjectState
import cat.wavy.catactivity.setting.Details
import cat.wavy.catactivity.service.TimeService
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.DumbAware

class SelectShowProjectAndFiles : AnAction("Show Project and File"), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        with(project.service<CatActivitySettingProjectState>().state) {
            details = Details.File
            isEnabled = true
        }
        project.service<TimeService>().render(project)
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabled = e.project != null
    }
}