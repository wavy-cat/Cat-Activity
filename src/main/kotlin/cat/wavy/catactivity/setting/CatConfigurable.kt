package cat.wavy.catactivity.setting

import cat.wavy.catactivity.bundle.ConfigBundle
import com.intellij.openapi.components.service
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import cat.wavy.catactivity.service.TimeService
import com.intellij.ui.components.JBCheckBox
import javax.swing.JComponent
import com.intellij.ui.components.JBTabbedPane
import com.intellij.ui.dsl.builder.bindSelected

class CatConfigurable(
    private val project: Project
) : Configurable {
    private val appState = service<CatActivitySettingAppState>().state
    private val projectState = project.service<CatActivitySettingProjectState>().state

    private lateinit var enableCheck: JBCheckBox
    private val projectTab by lazy { ConfigurablePanel(project, projectState, enableCheck).panel }
    private val appTab by lazy { ConfigurablePanel(project, appState, enableCheck).panel }

    private val panel = com.intellij.ui.dsl.builder.panel {
        row {
            checkBox(ConfigBundle.message("enablePlugin"))
                .bindSelected(projectState::isEnabled)
                .applyToComponent { enableCheck = this }
        }

        val tabbed = JBTabbedPane()
        tabbed.addTab(ConfigBundle.message("tabIDE"), appTab)
        tabbed.addTab(ConfigBundle.message("tabProject"), projectTab)
        row {
            cell(tabbed)
        }
    }

    override fun createComponent(): JComponent = panel

    override fun isModified(): Boolean = panel.isModified() || projectTab.isModified() || appTab.isModified()

    override fun apply() {
        panel.apply()
        if (appTab.isModified()) appTab.apply()
        if (projectTab.isModified()) projectTab.apply()
        project.service<TimeService>().render(project)
    }

    override fun reset() {
        panel.reset()
        appTab.reset()
        projectTab.reset()
    }

    override fun getDisplayName(): String = ConfigBundle.message("projectTitle")
}