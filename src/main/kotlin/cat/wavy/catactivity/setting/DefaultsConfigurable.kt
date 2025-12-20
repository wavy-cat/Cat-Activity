package cat.wavy.catactivity.setting

import cat.wavy.catactivity.bundle.ConfigBundle
import com.intellij.openapi.components.service
import com.intellij.openapi.options.Configurable
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.layout.enteredTextSatisfies
import com.intellij.ui.SimpleListCellRenderer
import javax.swing.JComponent

class DefaultsConfigurable() : Configurable {
    private val panel = panel {
        val state = service<CatActivitySettingAppState>().state

        group(ConfigBundle.message("display")) {
            row(ConfigBundle.message("theme")) {
                comboBox(items = Theme.entries).bindItem(state::theme.toNullableProperty())
            }

            row(ConfigBundle.message("ideIcons")) {
                comboBox(items = IDEIcon.entries)
                    .applyToComponent {
                        renderer = SimpleListCellRenderer.create { label, icon, _ -> label.text = icon.displayName }
                    }
                    .bindItem(state::ideIcon.toNullableProperty())
            }
        }

        group(ConfigBundle.message("formatProject")) {
            var projectStateField: JBTextField? = null

            row(ConfigBundle.message("detailsLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::projectDetailFormat)

                contextHelp(
                    ConfigBundle.message("projectFormatHelp"), ConfigBundle.message("placeholders")
                )
            }

            row(ConfigBundle.message("stateLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::projectStateFormat).applyToComponent {
                    projectStateField = this
                }

                contextHelp(
                    ConfigBundle.message("projectFormatHelp"), ConfigBundle.message("placeholders")
                )
            }

            row {
                label(ConfigBundle.message("stateLineEmpty")).applyToComponent {
                    foreground = JBColor.RED
                }.visibleIf(projectStateField!!.enteredTextSatisfies { it.isBlank() })
            }
        }

        group(ConfigBundle.message("formatFile")) {
            var fileStateField: JBTextField? = null

            row(ConfigBundle.message("detailsLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::fileDetailFormat)

                contextHelp(
                    ConfigBundle.message("fileFormatHelp"), ConfigBundle.message("placeholders")
                )
            }

            row(ConfigBundle.message("stateLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::fileStateFormat).applyToComponent {
                    fileStateField = this
                }

                contextHelp(
                    ConfigBundle.message("fileFormatHelp"), ConfigBundle.message("placeholders")
                )
            }

            row {
                label(ConfigBundle.message("stateLineEmpty")).applyToComponent {
                    foreground = JBColor.RED
                }.visibleIf(fileStateField!!.enteredTextSatisfies { it.isBlank() })
            }
        }

        group(ConfigBundle.message("formatIdle")) {
            var idleStateField: JBTextField? = null

            row(ConfigBundle.message("detailsLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::idleDetailFormat)

                contextHelp(
                    ConfigBundle.message("idleFormatHelp"), ConfigBundle.message("placeholders")
                )
            }

            row(ConfigBundle.message("stateLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::idleStateFormat).applyToComponent {
                    idleStateField = this
                }

                contextHelp(
                    ConfigBundle.message("idleFormatHelp"), ConfigBundle.message("placeholders")
                )
            }

            row {
                label(ConfigBundle.message("stateLineEmpty")).applyToComponent {
                    foreground = JBColor.RED
                }.visibleIf(idleStateField!!.enteredTextSatisfies { it.isBlank() })
            }
        }
    }

    override fun createComponent(): JComponent = panel

    override fun isModified(): Boolean = panel.isModified()

    override fun apply() = panel.apply()

    override fun reset() = panel.reset()

    override fun getDisplayName(): String = ConfigBundle.message("defaultsSettingsTitle")
}