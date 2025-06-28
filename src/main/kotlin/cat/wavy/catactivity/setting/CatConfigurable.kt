package cat.wavy.catactivity.setting

import cat.wavy.catactivity.bundle.ConfigBundle
import com.intellij.openapi.components.service
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.layout.enteredTextSatisfies
import cat.wavy.catactivity.service.TimeService
import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.SimpleListCellRenderer
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.layout.selected
import com.intellij.ui.layout.selectedValueIs
import com.intellij.ui.layout.selectedValueMatches
import javax.swing.JComponent

class CatConfigurable(
    private val project: Project
) : Configurable {
    private val panel = panel {
        val state = project.service<CatActivitySettingProjectState>().state

        var enableCheck: JBCheckBox? = null
        var displayCombo: ComboBox<Details>? = null
        var projectStateField: JBTextField? = null
        var fileStateField: JBTextField? = null
        var idleStateField: JBTextField? = null

        row {
            checkBox(ConfigBundle.message("enablePlugin")).bindSelected(state::isEnabled)
                .applyToComponent { enableCheck = this }
        }

        group(ConfigBundle.message("display")) {
            row(ConfigBundle.message("details")) {
                comboBox(
                    items = Details.entries,
                ).bindItem(state::details.toNullableProperty()).applyToComponent { displayCombo = this }

                contextHelp(ConfigBundle.message("displayModeHelp"))
            }

            row(ConfigBundle.message("theme")) {
                comboBox(items = Theme.entries).bindItem(state::theme.toNullableProperty())
            }

            row(ConfigBundle.message("ideIcons")) {
                comboBox(items = IDEIcon.entries)
                    .applyToComponent {
                        renderer = SimpleListCellRenderer.create { label, icon, _ -> label.text = icon.displayName }
                    }
                    .bindItem(state::ideIcon.toNullableProperty())
            }.enabledIf(enableCheck!!.selected)

            row {
                checkBox(ConfigBundle.message("showRepositoryButton")).bindSelected(state::showRepositoryButton)

                contextHelp(ConfigBundle.message("showRepositoryButtonHelp"))
            }.enabledIf(displayCombo!!.selectedValueMatches { it in setOf(Details.Project, Details.File) })
        }.enabledIf(enableCheck!!.selected)

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
        }.visibleIf(displayCombo!!.selectedValueIs(Details.Project))

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
        }.visibleIf(displayCombo.selectedValueIs(Details.File)).enabledIf(enableCheck.selected)

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
        }.visibleIf(displayCombo.selectedValueIs(Details.File)).enabledIf(enableCheck.selected)
    }

    override fun createComponent(): JComponent = panel

    override fun isModified(): Boolean = panel.isModified()

    override fun apply() = panel.apply().also {
        project.service<TimeService>().render(project)
    }

    override fun reset() = panel.reset()

    override fun getDisplayName(): String = ConfigBundle.message("projectTitle")
}