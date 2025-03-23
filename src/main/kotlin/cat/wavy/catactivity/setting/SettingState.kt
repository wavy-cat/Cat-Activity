package cat.wavy.catactivity.setting

data class SettingState(
    var isEnabled: Boolean = false,
    var details: Details = Details.IDE,
    var usingTheme: ThemeList = ThemeList.Macchiato,
    var usingDefaultIDEName: Boolean = false,
    var showRepositoryButton: Boolean = false,

    var projectStateFormat: String = "Working on %projectName%",
    var projectDetailFormat: String = "%projectProblems% Problems in Project",

    var fileStateFormat: String = "Editing %fileName%",
    var fileDetailFormat: String = "%projectName% (%branch%)",
    var iconsStyle: IconsStyle = IconsStyle.New,

    var idleStateFormat: String = "Idle",
    var idleDetailFormat: String = "%projectName% (%branch%)",

    var firstInit: Boolean = true,
)

enum class Details {
    IDE,
    Project,
    File
}

enum class ThemeList {
    Frappe,
    Latte,
    Macchiato,
    Mocha
}

enum class IconsStyle {
    New,
    Old
}
