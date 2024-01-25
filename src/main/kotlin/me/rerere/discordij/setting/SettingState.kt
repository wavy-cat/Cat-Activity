package me.rerere.discordij.setting

data class SettingState(
    var displayMode: DisplayMode = DisplayMode.FILE,

    var projectStateFormat: String = "Working on Project: %projectName%",
    var projectDetailFormat: String = "%projectProblems% Problems in Project",

    var fileStateFormat: String = "Editing %fileName%",
    var fileDetailFormat: String = "%projectName% (%branch%)",
)

enum class DisplayMode {
    IDE,
    PROJECT,
    FILE
}