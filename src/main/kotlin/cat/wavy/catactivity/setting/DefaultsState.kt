package cat.wavy.catactivity.setting

import cat.wavy.catactivity.types.IDEType
import cat.wavy.catactivity.types.currentIDEType

data class DefaultsState(
    var theme: Theme = Theme.Macchiato,
    var ideIcon: IDEIcon = IDEIcon.New,

    var projectStateFormat: String = "Working on %projectName%",
    var projectDetailFormat: String = "%projectProblems% Problems in Project",

    var fileStateFormat: String = "Editing %fileName%",
    var fileDetailFormat: String = "%projectName% (%branch%)",

    var idleStateFormat: String = "Idle",
    var idleDetailFormat: String = "%projectName% (%branch%)",
)

enum class Theme {
    Frappe,
    Latte,
    Macchiato,
    Mocha,
}

enum class IDEIcon(val displayName: String, val app: IDEType) {
    New("${currentIDEType.title} (New)", currentIDEType),
    Old("${currentIDEType.title} (Old)", currentIDEType),
    JetBrains("JetBrains", IDEType.JETBRAINS),
    CatActivity("Cat Activity", IDEType.CATACTIVITY),
}

