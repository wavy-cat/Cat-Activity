package cat.wavy.catactivity.setting

import cat.wavy.catactivity.bundle.ConfigBundle
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.components.service
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.intellij.ui.dsl.builder.*
import cat.wavy.catactivity.service.TimeService
import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.dsl.listCellRenderer.textListCellRenderer
import com.intellij.ui.layout.and
import com.intellij.ui.layout.selected
import com.intellij.ui.layout.selectedValueIs
import com.intellij.ui.layout.selectedValueMatches
import javax.swing.JComponent

class ProjectConfigurable(
    private val project: Project
) : Configurable {
    private val panel = panel {
        val state = project.service<CatActivitySettingProjectState>().state
        val defaults = service<CatActivitySettingAppState>().state

        var enableCheck: JBCheckBox? = null
        var displayCombo: ComboBox<Details>? = null
        var ideIconSelector: ComboBox<IDEIconDefaultable>? = null
        var iconVariantSelector: ComboBox<IDEIconDefaultable>? = null

        row {
            checkBox(ConfigBundle.message("enablePlugin")).bindSelected(state::isEnabled)
                .applyToComponent { enableCheck = this }
        }

        group(ConfigBundle.message("privacy")) {
            row(ConfigBundle.message("details")) {
                comboBox(
                    items = Details.entries,
                ).bindItem(state::details.toNullableProperty()).applyToComponent { displayCombo = this }

                contextHelp(ConfigBundle.message("detailsModeHelp"), ConfigBundle.message("detailsModeHelpTitle"))
            }

            row {
                checkBox(ConfigBundle.message("showRepositoryButton")).bindSelected(state::showRepositoryButton)
                    .enabledIf(displayCombo!!.selectedValueMatches { it in setOf(Details.Project, Details.File) })

                contextHelp(ConfigBundle.message("showRepositoryButtonHelp"))
            }
        }.enabledIf(enableCheck!!.selected)

        group(ConfigBundle.message("display")) {
            row(ConfigBundle.message("theme")) {
                comboBox(items = ThemeDefaultable.entries).bindItem(state::theme.toNullableProperty())
            }

            row(ConfigBundle.message("ideIcons")) {
                comboBox(items = IDEIconDefaultable.entries)
                    .applyToComponent {
                        ideIconSelector = this
                        renderer = textListCellRenderer("") { it.displayName }
                    }
                    .bindItem(state::ideIcon.toNullableProperty())
            }

            val isCustomApplication = ideIconSelector!!.selectedValueMatches { it == IDEIconDefaultable.Custom }

            row(ConfigBundle.message("customApplicationId")) {
                textField().columns(COLUMNS_LARGE)
                    .bindText(state::customApplicationId)
                    .applyToComponent { emptyText.text = ConfigBundle.message("leaveBlankDefaultValue") }
                    .validationOnApply {
                        val effectiveId = state.customApplicationId.ifBlank { defaults.customApplicationId }
                        if (state.ideIcon == IDEIconDefaultable.Custom && !isValidApplicationId(effectiveId)) {
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
                    .applyToComponent { emptyText.text = ConfigBundle.message("leaveBlankDefaultValue") }
                    .validationOnApply {
                        val effectiveTitle = state.customApplicationTitle.ifBlank { defaults.customApplicationTitle }
                        if (state.ideIcon == IDEIconDefaultable.Custom && effectiveTitle.isBlank()) {
                            error(ConfigBundle.message("emptyApplicationTitle"))
                        } else null
                    }

                contextHelp(ConfigBundle.message("customApplicationTitleHelp"))
            }.visibleIf(isCustomApplication)

            row(ConfigBundle.message("customIconVariant")) {
                comboBox(items = IDEIconDefaultable.entries)
                    .applyToComponent {
                        iconVariantSelector = this
                        renderer = textListCellRenderer("") { it.displayName }
                    }
                    .bindItem(state::customIconVariant.toNullableProperty())
            }.visibleIf(isCustomApplication)

            row(ConfigBundle.message("customIconUrl")) {
                textField().columns(COLUMNS_LARGE)
                    .bindText(state::customIconUrl)
                    .applyToComponent { emptyText.text = ConfigBundle.message("leaveBlankDefaultValue") }
                    .validationOnApply {
                        val effectiveVariant = UnitedState.resolveIconVariant(state.customIconVariant, defaults.customIconVariant)
                        val effectiveUrl = state.customIconUrl.ifBlank { defaults.customIconUrl }
                        if (state.ideIcon == IDEIconDefaultable.Custom && effectiveVariant == IDEIcon.Custom &&
                            normalizeIconUrl(effectiveUrl) == null
                        ) {
                            error(ConfigBundle.message("invalidCustomIconUrl"))
                        } else null
                    }
            }.visibleIf(isCustomApplication and iconVariantSelector!!.selectedValueMatches { it == IDEIconDefaultable.Custom })
        }.enabledIf(enableCheck.selected)

        group(ConfigBundle.message("formatProject")) {
            row(ConfigBundle.message("detailsLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::projectDetailFormat)
                    .applyToComponent {
                        emptyText.text = ConfigBundle.message("leaveBlankDefaultValue")
                    }

                contextHelp(
                    ConfigBundle.message("projectFormatHelp"), ConfigBundle.message("placeholders")
                )
            }

            row(ConfigBundle.message("stateLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::projectStateFormat)
                    .applyToComponent { emptyText.text = ConfigBundle.message("leaveBlankDefaultValue") }

                contextHelp(
                    ConfigBundle.message("projectFormatHelp"), ConfigBundle.message("placeholders")
                )
            }
        }.visibleIf(displayCombo!!.selectedValueIs(Details.Project)).enabledIf(enableCheck.selected)

        group(ConfigBundle.message("formatFile")) {
            row(ConfigBundle.message("detailsLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::fileDetailFormat)
                    .applyToComponent { emptyText.text = ConfigBundle.message("leaveBlankDefaultValue") }

                contextHelp(
                    ConfigBundle.message("fileFormatHelp"), ConfigBundle.message("placeholders")
                )
            }

            row(ConfigBundle.message("stateLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::fileStateFormat)
                    .applyToComponent { emptyText.text = ConfigBundle.message("leaveBlankDefaultValue") }

                contextHelp(
                    ConfigBundle.message("fileFormatHelp"), ConfigBundle.message("placeholders")
                )
            }
        }.visibleIf(displayCombo.selectedValueIs(Details.File)).enabledIf(enableCheck.selected)

        group(ConfigBundle.message("formatIdle")) {
            row(ConfigBundle.message("detailsLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::idleDetailFormat)
                    .applyToComponent { emptyText.text = ConfigBundle.message("leaveBlankDefaultValue") }

                contextHelp(
                    ConfigBundle.message("idleFormatHelp"), ConfigBundle.message("placeholders")
                )
            }

            row(ConfigBundle.message("stateLine")) {
                textField().columns(COLUMNS_LARGE).bindText(state::idleStateFormat)
                    .applyToComponent { emptyText.text = ConfigBundle.message("leaveBlankDefaultValue") }

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
