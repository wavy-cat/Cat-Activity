package cat.wavy.catactivity.setting

import cat.wavy.catactivity.types.IDEType
import cat.wavy.catactivity.types.currentIDEType

data class ProjectState(
    var isEnabled: Boolean = false,
    var details: Details = Details.IDE,
    var theme: ThemeDefaultable = ThemeDefaultable.Default,
    var ideIcon: IDEIconDefaultable = IDEIconDefaultable.Default,
    var showRepositoryButton: Boolean = false,

    var projectStateFormat: String = "",
    var projectDetailFormat: String = "",

    var fileStateFormat: String = "",
    var fileDetailFormat: String = "",

    var idleStateFormat: String = "",
    var idleDetailFormat: String = "",

    var firstInit: Boolean = true,
)

enum class Details {
    IDE,
    Project,
    File,
}

enum class ThemeDefaultable {
    Default,
    Frappe,
    Latte,
    Macchiato,
    Mocha,
}

enum class IDEIconDefaultable(val displayName: String, val app: IDEType?) {
    Default("Default", null),
    New("${currentIDEType.title} (New)", currentIDEType),
    Old("${currentIDEType.title} (Old)", currentIDEType),
    JetBrains("JetBrains", IDEType.JETBRAINS),
    CatActivity("Cat Activity", IDEType.CATACTIVITY),
}
