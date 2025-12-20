package cat.wavy.catactivity.setting

data class UnitedState(
    var isEnabled: Boolean,
    var details: Details,
    var theme: Theme,
    var ideIcon: IDEIcon,
    var showRepositoryButton: Boolean,

    var projectStateFormat: String,
    var projectDetailFormat: String,

    var fileStateFormat: String,
    var fileDetailFormat: String,

    var idleStateFormat: String,
    var idleDetailFormat: String,

    var firstInit: Boolean,
) {
    constructor(projectState: ProjectState, defaultsState: DefaultsState) : this(
        isEnabled = projectState.isEnabled,
        details = projectState.details,
        theme = resolveTheme(projectState.theme, defaultsState.theme),
        ideIcon = resolveIDEIcon(projectState.ideIcon, defaultsState.ideIcon),
        showRepositoryButton = projectState.showRepositoryButton,
        projectStateFormat = projectState.projectStateFormat.ifBlank { defaultsState.projectStateFormat },
        projectDetailFormat = projectState.projectDetailFormat.ifBlank { defaultsState.projectDetailFormat },
        fileStateFormat = projectState.fileStateFormat.ifBlank { defaultsState.fileStateFormat },
        fileDetailFormat = projectState.fileDetailFormat.ifBlank { defaultsState.fileDetailFormat },
        idleStateFormat = projectState.idleStateFormat.ifBlank { defaultsState.idleStateFormat },
        idleDetailFormat = projectState.idleDetailFormat.ifBlank { defaultsState.idleDetailFormat },
        firstInit = projectState.firstInit
    )

    companion object {
        private fun resolveTheme(settingTheme: ThemeDefaultable, defaultTheme: Theme): Theme {
            return when (settingTheme) {
                ThemeDefaultable.Default -> defaultTheme
                ThemeDefaultable.Frappe -> Theme.Frappe
                ThemeDefaultable.Latte -> Theme.Latte
                ThemeDefaultable.Macchiato -> Theme.Macchiato
                ThemeDefaultable.Mocha -> Theme.Mocha
            }
        }

        private fun resolveIDEIcon(settingIcon: IDEIconDefaultable, defaultIcon: IDEIcon): IDEIcon {
            return when (settingIcon) {
                IDEIconDefaultable.Default -> defaultIcon
                IDEIconDefaultable.New -> IDEIcon.New
                IDEIconDefaultable.Old -> IDEIcon.Old
                IDEIconDefaultable.JetBrains -> IDEIcon.JetBrains
                IDEIconDefaultable.CatActivity -> IDEIcon.CatActivity
            }
        }
    }
}
