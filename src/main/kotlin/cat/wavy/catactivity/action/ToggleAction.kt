package cat.wavy.catactivity.action

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.actionSystem.ToggleAction

abstract class ToggleAction(
    text: String
) : ToggleAction(text), DumbAware {
    override fun update(e: AnActionEvent) {
        super.update(e)
        e.presentation.isEnabled = e.project != null
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }
}