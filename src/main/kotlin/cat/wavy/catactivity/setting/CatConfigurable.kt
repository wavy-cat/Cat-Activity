package cat.wavy.catactivity.setting

import com.intellij.openapi.components.service
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.layout.enteredTextSatisfies
import cat.wavy.catactivity.service.TimeService
import javax.swing.JComponent

class CatConfigurable(
    private val project: Project
) : Configurable {
    private val panel = panel {
        val state = project.service<CatActivitySettingProjectState>().state

        group("Display") {
            row("Display mode") {
                comboBox(
                    items = DisplayMode.values().toList(),
                ).bindItem(state::displayMode.toNullableProperty())

                contextHelp(
                    """
                    Display mode:
                    <ul>
                        <li><b>Disable</b>: Turns off activity display completely</li>
                        <li><b>IDE</b>: Display the IDE information</li>
                        <li><b>Project</b>: Display project information only</li>
                        <li><b>File</b>: Display project and file information</li>
                    </ul>
                """.trimIndent()
                )
            }

            row("Theme") {
                comboBox(
                    items = ThemeList.values().toList(),
                ).bindItem(state::usingTheme.toNullableProperty())
            }
        }

        var projectStateField: JBTextField? = null
        var fileStateField: JBTextField? = null

        group("Format") {
            group("Project") {
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
            }

            group("File") {
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
                    </ul>
                    """.trimIndent(), "Placeholders"
                )
            }
        }
    }

    override fun createComponent(): JComponent = panel

    override fun isModified(): Boolean = panel.isModified()

    override fun apply() = panel.apply().also {
        service<TimeService>().render(project)
    }

    override fun reset() = panel.reset()

    override fun getDisplayName(): String = "Cat Activity"
}