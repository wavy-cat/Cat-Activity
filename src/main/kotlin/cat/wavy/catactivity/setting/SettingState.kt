package cat.wavy.catactivity.setting

data class SettingState(
    var displayMode: DisplayMode = DisplayMode.Disable,
    var usingTheme: ThemeList = ThemeList.Macchiato,

    var projectStateFormat: String = "Working on %projectName%",
    var projectDetailFormat: String = "%projectProblems% Problems in Project",

    var fileStateFormat: String = "Editing %fileName%",
    var fileDetailFormat: String = "%projectName% (%branch%)",

    var firstInit: Boolean = true,
)

enum class DisplayMode {
    Disable,
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