package cat.wavy.catactivity.setting

import cat.wavy.catactivity.bundle.ConfigBundle
import com.intellij.openapi.components.service
import com.intellij.openapi.options.Configurable
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.layout.enteredTextSatisfies
import cat.wavy.catactivity.service.TimeService
import com.intellij.openapi.project.Project
import com.intellij.ui.SimpleListCellRenderer
import javax.swing.JComponent

class DefaultsConfigurable(
    private val project: Project
) : Configurable {
    private val panel = panel {
        val state = service<CatActivitySettingAppState>().state

        var projectStateField: JBTextField? = null
        var fileStateField: JBTextField? = null
        var idleStateField: JBTextField? = null

        group(ConfigBundle.message("display")) {
            row(ConfigBundle.message("ideIcons")) {
                comboBox(items = IDEIcon.entries)
                    .applyToComponent {
                        renderer = SimpleListCellRenderer.create { label, icon, _ -> label.text = icon.displayName }
                    }
                    .bindItem(state::ideIcon.toNullableProperty())
            }

            row {
                checkBox(ConfigBundle.message("showRepositoryButton")).bindSelected(state::showRepositoryButton)

                contextHelp(ConfigBundle.message("showRepositoryButtonHelp"))
            }
        }

        group(ConfigBundle.message("formatProject")) {
            row(ConfigBundle.message("detailsLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::projectDetailFormat)
            }

            row(ConfigBundle.message("stateLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::projectStateFormat).applyToComponent {
                    projectStateField = this
                }
            }

            row {
                label(ConfigBundle.message("stateLineEmpty")).applyToComponent {
                    foreground = JBColor.RED
                }.visibleIf(projectStateField!!.enteredTextSatisfies { it.isBlank() })
            }

            row {
                contextHelp(
                    ConfigBundle.message("projectFormatHelp"), ConfigBundle.message("placeholders")
                )
            }
        }

        group(ConfigBundle.message("formatFile")) {
            row(ConfigBundle.message("detailsLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::fileDetailFormat)
            }

            row(ConfigBundle.message("stateLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::fileStateFormat).applyToComponent {
                    fileStateField = this
                }
            }

            row {
                label(ConfigBundle.message("stateLineEmpty")).applyToComponent {
                    foreground = JBColor.RED
                }.visibleIf(fileStateField!!.enteredTextSatisfies { it.isBlank() })
            }

            row {
                contextHelp(
                    ConfigBundle.message("fileFormatHelp"), ConfigBundle.message("placeholders")
                )
            }
        }

        group(ConfigBundle.message("formatIdle")) {
            row(ConfigBundle.message("detailsLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::idleDetailFormat)
            }

            row(ConfigBundle.message("stateLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::idleStateFormat).applyToComponent {
                    idleStateField = this
                }
            }

            row {
                label(ConfigBundle.message("stateLineEmpty")).applyToComponent {
                    foreground = JBColor.RED
                }.visibleIf(idleStateField!!.enteredTextSatisfies { it.isBlank() })
            }

            row {
                contextHelp(
                    ConfigBundle.message("idleFormatHelp"), ConfigBundle.message("placeholders")
                )
            }
        }
    }

    override fun createComponent(): JComponent = panel

    override fun isModified(): Boolean = panel.isModified()

    override fun apply() = panel.apply().also {
        project.service<TimeService>().render(project)
    }

    override fun reset() = panel.reset()

    override fun getDisplayName(): String = ConfigBundle.message("projectTitle")
}