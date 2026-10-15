package cat.wavy.catactivity.setting

import cat.wavy.catactivity.bundle.ConfigBundle
import cat.wavy.catactivity.service.TimeService
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.components.service
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.layout.and
import com.intellij.ui.layout.enteredTextSatisfies
import com.intellij.ui.layout.selectedValueMatches
import com.intellij.ui.dsl.listCellRenderer.textListCellRenderer
import javax.swing.JComponent

class DefaultsConfigurable : Configurable {
    private val panel = panel {
        val state = service<CatActivitySettingAppState>().state
        var customServerSelector: ComboBox<Boolean>? = null
        var ideIconSelector: ComboBox<IDEIcon>? = null
        var iconVariantSelector: ComboBox<IDEIcon>? = null

        group(ConfigBundle.message("display")) {
            row(ConfigBundle.message("theme")) {
                comboBox(items = Theme.entries).bindItem(state::theme.toNullableProperty())
            }

            row(ConfigBundle.message("ideIcons")) {
                comboBox(items = IDEIcon.entries)
                    .applyToComponent {
                        ideIconSelector = this
                        renderer = textListCellRenderer("") { it.displayName }
                    }
                    .bindItem(state::ideIcon.toNullableProperty())
            }

            val isCustomApplication = ideIconSelector!!.selectedValueMatches { it == IDEIcon.Custom }

            row(ConfigBundle.message("customApplicationId")) {
                textField().columns(COLUMNS_LARGE)
                    .bindText(state::customApplicationId)
                    .validationOnApply {
                        if (state.ideIcon == IDEIcon.Custom && !isValidApplicationId(state.customApplicationId)) {
                            error(ConfigBundle.message("invalidApplicationId"))
                        } else null
                    }

                contextHelp(ConfigBundle.message("customApplicationHelp"))
            }.visibleIf(isCustomApplication)

            row {
                link(ConfigBundle.message("createApplicationLink")) {
                    BrowserUtil.browse("https://discord.com/developers/applications")
                }
            }.visibleIf(isCustomApplication)

            row(ConfigBundle.message("customApplicationTitle")) {
                textField().columns(COLUMNS_LARGE)
                    .bindText(state::customApplicationTitle)
                    .validationOnApply {
                        if (state.ideIcon == IDEIcon.Custom && state.customApplicationTitle.isBlank()) {
                            error(ConfigBundle.message("emptyApplicationTitle"))
                        } else null
                    }

                contextHelp(ConfigBundle.message("customApplicationTitleHelp"))
            }.visibleIf(isCustomApplication)

            row(ConfigBundle.message("customIconVariant")) {
                comboBox(items = IDEIcon.entries)
                    .applyToComponent {
                        iconVariantSelector = this
                        renderer = textListCellRenderer("") { it.displayName }
                    }
                    .bindItem(state::customIconVariant.toNullableProperty())
            }.visibleIf(isCustomApplication)

            row(ConfigBundle.message("customIconUrl")) {
                textField().columns(COLUMNS_LARGE)
                    .bindText(state::customIconUrl)
                    .validationOnApply {
                        if (state.ideIcon == IDEIcon.Custom && state.customIconVariant == IDEIcon.Custom &&
                            normalizeIconUrl(state.customIconUrl) == null
                        ) {
                            error(ConfigBundle.message("invalidCustomIconUrl"))
                        } else null
                    }
            }.visibleIf(isCustomApplication and iconVariantSelector!!.selectedValueMatches { it == IDEIcon.Custom })

            row(ConfigBundle.message("iconsServer")) {
                comboBox(items = listOf(false, true))
                    .applyToComponent {
                        customServerSelector = this
                        renderer = textListCellRenderer("") {
                            ConfigBundle.message(if (it) "customServer" else "defaultServer")
                        }
                    }
                    .bindItem(state::useCustomIconsServer.toNullableProperty())
            }

            row(ConfigBundle.message("customServerUrl")) {
                textField().columns(COLUMNS_LARGE)
                    .bindText(state::customIconsUrl)
                    .enabledIf(customServerSelector!!.selectedValueMatches { it == true })
                    .validationOnApply {
                        if (state.useCustomIconsServer && normalizeUrl(state.customIconsUrl) == null) {
                            error(ConfigBundle.message("invalidIconsServerUrl"))
                        } else null
                    }

                contextHelp(ConfigBundle.message("iconsServerHelp"))
            }.visibleIf(customServerSelector!!.selectedValueMatches { it == true })

            row {
                link(ConfigBundle.message("selfHostingAssetsLink")) {
                    BrowserUtil.browse("https://github.com/wavy-cat/cat-Activity#Self-hosting-assets")
                }
            }.visibleIf(customServerSelector.selectedValueMatches { it == true })
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

    override fun apply() = panel.apply().also {
        ProjectManager.getInstance().openProjects
            .filter { !it.isDefault }
            .forEach { project -> project.service<TimeService>().render(project) }
    }

    override fun reset() = panel.reset()

    override fun getDisplayName(): String = ConfigBundle.message("defaultsSettingsTitle")
}
