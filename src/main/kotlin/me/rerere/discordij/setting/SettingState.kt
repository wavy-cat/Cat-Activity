package me.rerere.discordij.setting

data class SettingState(
    var displayMode: DisplayMode = DisplayMode.File,
    var usingTheme: ThemeList = ThemeList.Macchiato,

    var projectStateFormat: String = "Working on Project: %projectName%",
    var projectDetailFormat: String = "%projectProblems% Problems in Project",

    var fileStateFormat: String = "Editing %fileName%",
    var fileDetailFormat: String = "%projectName% (%branch%)",
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