package cat.wavy.catactivity.setting

import cat.wavy.catactivity.types.IDEType
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

enum class Theme(val fullName: String) {
    Frappe("Catppuccin Frappe"),
    Latte("Catppuccin Latte"),
    Macchiato("Catppuccin Macchiato"),
    Mocha("Catppuccin Mocha"),
}

enum class IDEIcon(val displayName: String, val app: IDEType) {
    New("${currentIDEType.title} (New)", currentIDEType),
    Old("${currentIDEType.title} (Old)", currentIDEType),
    JetBrains("JetBrains", IDEType.JETBRAINS),
    CatActivity("Cat Activity", IDEType.CATACTIVITY),
}

/**
 * Combine this [SettingState] with [base] so that every field keeps its
 * non-default value (taken from `this`) and falls back first to [base] and
 * finally to Kotlin-generated defaults.
 */
fun SettingState.mergeWith(
    base: SettingState,
    defaults: SettingState = SettingState()
): SettingState = SettingState(
    isEnabled = coalesce(isEnabled, defaults.isEnabled, base.isEnabled),
    details = coalesce(details, defaults.details, base.details),
    theme = coalesce(theme, defaults.theme, base.theme),
    ideIcon = coalesce(ideIcon, defaults.ideIcon, base.ideIcon),
    showRepositoryButton = coalesce(
        showRepositoryButton, defaults.showRepositoryButton, base.showRepositoryButton
    ),
    projectStateFormat = coalesce(projectStateFormat, defaults.projectStateFormat, base.projectStateFormat),
    projectDetailFormat = coalesce(projectDetailFormat, defaults.projectDetailFormat, base.projectDetailFormat),
    fileStateFormat = coalesce(fileStateFormat, defaults.fileStateFormat, base.fileStateFormat),
    fileDetailFormat = coalesce(fileDetailFormat, defaults.fileDetailFormat, base.fileDetailFormat),
    idleStateFormat = coalesce(idleStateFormat, defaults.idleStateFormat, base.idleStateFormat),
    idleDetailFormat = coalesce(idleDetailFormat, defaults.idleDetailFormat, base.idleDetailFormat),
    firstInit = coalesce(firstInit, defaults.firstInit, base.firstInit),
)

private fun <T> coalesce(value: T, default: T, fallback: T): T = if (value != default) value else fallback
