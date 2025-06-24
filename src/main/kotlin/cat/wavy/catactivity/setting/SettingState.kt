package cat.wavy.catactivity.setting

import cat.wavy.catactivity.types.currentIDEType

data class SettingState(
    var isEnabled: Boolean = false,
    var details: Details = Details.IDE,
    var theme: Theme = Theme.Macchiato,
    var ideIcon: IDEIcon = IDEIcon.New,
    var showRepositoryButton: Boolean = false,

    var projectStateFormat: String = "Working on %projectName%",
    var projectDetailFormat: String = "%projectProblems% Problems in Project",

    var fileStateFormat: String = "Editing %fileName%",
    var fileDetailFormat: String = "%projectName% (%branch%)",

    var idleStateFormat: String = "Idle",
    var idleDetailFormat: String = "%projectName% (%branch%)",

    var firstInit: Boolean = true,
)

enum class Details {
    IDE,
    Project,
    File,
}

enum class Theme {
    Frappe,
    Latte,
    Macchiato,
    Mocha,
}

enum class IDEIcon(val displayName: String) {
    New("${currentIDEType.title} (New)"),
    Old("${currentIDEType.title} (Old)"),
    JetBrains("JetBrains"),
    CatActivity("Cat Activity"),
}
