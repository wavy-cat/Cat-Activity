package cat.wavy.catactivity.setting

import com.intellij.openapi.components.service
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.layout.enteredTextSatisfies
import cat.wavy.catactivity.service.TimeService
import cat.wavy.catactivity.types.IDEType
import cat.wavy.catactivity.types.currentIDEType
import com.intellij.openapi.ui.ComboBox
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
            checkBox("Enable Rich Presence")
                .bindSelected(state::isEnabled)
                .applyToComponent { enableCheck = this }
        }

        group("Display") {
            row("Details") {
                comboBox(
                    items = Details.entries,
                ).bindItem(state::details.toNullableProperty())
                    .applyToComponent { displayCombo = this }

                contextHelp(
                    """
                    Display mode:
                    <ul>
                        <li><b>IDE</b>: Display the IDE information</li>
                        <li><b>Project</b>: Display project information only</li>
                        <li><b>File</b>: Display project and file information</li>
                    </ul>
                    """.trimIndent()
                )
            }

            row("Theme") {
                comboBox(items = ThemeList.entries)
                    .bindItem(state::usingTheme.toNullableProperty())
            }

            row("IDE icons") {
                comboBox(items = IconsStyle.entries)
                    .bindItem(state::iconsStyle.toNullableProperty())
            }.enabledIf(enableCheck!!.selected)

            row {
                checkBox("Show JetBrains IDE instead of ${currentIDEType.title}")
                    .bindSelected(state::usingDefaultIDEName)

                contextHelp("Restart required")
            }.visible(currentIDEType != IDEType.JETBRAINS)

            row {
                checkBox("Show a button with a link to the repository")
                    .bindSelected(state::showRepositoryButton)

                contextHelp(
                    """
                    Not available if Details is set as IDE.
                    Not displayed if the project does not use Git or the link is not correct.
                    """.trimIndent()
                )
            }.enabledIf(displayCombo!!.selectedValueMatches { it in setOf(Details.Project, Details.File) })
        }.enabledIf(enableCheck!!.selected)

        group("Format Project") {
            row("Details Line") {
                textField()
                    .columns(COLUMNS_LARGE)
                    .bindText(state::projectDetailFormat)
            }

            row("State Line") {
                textField()
                    .columns(COLUMNS_LARGE)
                    .bindText(state::projectStateFormat)
                    .applyToComponent {
                        projectStateField = this
                    }
            }

            row {
                label("State line can't be empty")
                    .applyToComponent {
                        foreground = JBColor.RED
                    }
                    .visibleIf(projectStateField!!.enteredTextSatisfies { it.isBlank() })
            }

            row {
                contextHelp(
                    """
                    Available variables:
                    <ul>
                        <li>%projectName%: Project name</li>
                        <li>%projectPath%: Project path</li>
                        <li>%projectProblems%: Number of problems in project</li>
                        <li>%branch%: Current branch name</li>
                        <li>%repository%: Current repository</li>
                    </ul>
                    """.trimIndent(), "Placeholders"
                )
            }
        }.visibleIf(displayCombo!!.selectedValueIs(Details.Project))

        group("Format File") {
            row("Details Line") {
                textField()
                    .columns(COLUMNS_LARGE)
                    .bindText(state::fileDetailFormat)
            }

            row("State Line") {
                textField()
                    .columns(COLUMNS_LARGE)
                    .bindText(state::fileStateFormat)
                    .applyToComponent {
                        fileStateField = this
                    }
            }

            row {
                label("State line can't be empty")
                    .applyToComponent {
                        foreground = JBColor.RED
                    }
                    .visibleIf(fileStateField!!.enteredTextSatisfies { it.isBlank() })
            }

            row {
                contextHelp(
                    """
                    Available variables:
                    <ul>
                        <li>%projectName%: Project name</li>
                        <li>%projectPath%: Project path</li>
                        <li>%fileName%: File name</li>
                        <li>%fileProblems%: Number of problems</li>
                        <li>%filePath%: File path</li>
                        <li>%projectProblems%: Number of problems in project</li>
                        <li>%branch%: Current branch name</li>
                        <li>%repository%: Current repository</li>
                        <li>%linesCount%: Number of total lines in the file</li>
                        <li>%fileSize%: Current file size</li>
                        <li>%fileExtension%: Current file extension</li>
                    </ul>
                    """.trimIndent(), "Placeholders"
                )
            }
        }.visibleIf(displayCombo.selectedValueIs(Details.File)).enabledIf(enableCheck.selected)

        group("Format Idle") {
            row("Details Line") {
                textField()
                    .columns(COLUMNS_LARGE)
                    .bindText(state::idleDetailFormat)
            }

            row("State Line") {
                textField()
                    .columns(COLUMNS_LARGE)
                    .bindText(state::idleStateFormat)
                    .applyToComponent {
                        idleStateField = this
                    }
            }

            row {
                label("State line can't be empty")
                    .applyToComponent {
                        foreground = JBColor.RED
                    }
                    .visibleIf(idleStateField!!.enteredTextSatisfies { it.isBlank() })
            }

            row {
                contextHelp(
                    """
                    Available variables:
                    <ul>
                        <li>%projectName%: Project name</li>
                        <li>%projectPath%: Project path</li>
                        <li>%projectProblems%: Number of problems in project</li>
                        <li>%branch%: Current branch name</li>
                        <li>%repository%: Current repository</li>
                    </ul>
                    """.trimIndent(), "Placeholders"
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

    override fun getDisplayName(): String = "Cat Activity"
}