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
    constructor(settingState: SettingState, defaultsState: DefaultsState) : this(
        isEnabled = settingState.isEnabled,
        details = settingState.details,
        theme = resolveTheme(settingState.theme, defaultsState.theme),
        ideIcon = resolveIDEIcon(settingState.ideIcon, defaultsState.ideIcon),
        showRepositoryButton = settingState.showRepositoryButton,
        projectStateFormat = settingState.projectStateFormat.ifBlank { defaultsState.projectStateFormat },
        projectDetailFormat = settingState.projectDetailFormat.ifBlank { defaultsState.projectDetailFormat },
        fileStateFormat = settingState.fileStateFormat.ifBlank { defaultsState.fileStateFormat },
        fileDetailFormat = settingState.fileDetailFormat.ifBlank { defaultsState.fileDetailFormat },
        idleStateFormat = settingState.idleStateFormat.ifBlank { defaultsState.idleStateFormat },
        idleDetailFormat = settingState.idleDetailFormat.ifBlank { defaultsState.idleDetailFormat },
        firstInit = settingState.firstInit
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
