package cat.wavy.catactivity.setting

import cat.wavy.catactivity.types.IDEType
import cat.wavy.catactivity.types.currentIDEType

data class SettingState(
    var isEnabled: Boolean = false,
    var details: Details = Details.IDE,
    var theme: Theme = Theme.Macchiato,
    var ideIcon: IDEIcon = IDEIcon.New,
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
