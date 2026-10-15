package cat.wavy.catactivity.setting

import cat.wavy.catactivity.types.currentIDEType

data class UnitedState(
    var isEnabled: Boolean,
    var details: Details,
    var theme: Theme,
    var applicationId: Long,
    var applicationTitle: String,
    var iconVariant: IDEIcon,
    var customIconUrl: String?,
    var iconsUrl: String,
    var showRepositoryButton: Boolean,

    var projectStateFormat: String,
    var projectDetailFormat: String,

    var fileStateFormat: String,
    var fileDetailFormat: String,

    var idleStateFormat: String,
    var idleDetailFormat: String,

    var firstInit: Boolean,
) {
    constructor(projectState: ProjectState, defaultsState: DefaultsState) : this(
        isEnabled = projectState.isEnabled,
        details = projectState.details,
        theme = resolveTheme(projectState.theme, defaultsState.theme),
        applicationId = resolveApplicationId(projectState, defaultsState),
        applicationTitle = resolveApplicationTitle(projectState, defaultsState),
        iconVariant = resolveIconVariant(projectState, defaultsState),
        customIconUrl = resolveCustomIconUrl(projectState, defaultsState),
        iconsUrl = defaultsState.iconsUrl,
        showRepositoryButton = projectState.showRepositoryButton,
        projectStateFormat = projectState.projectStateFormat.ifBlank { defaultsState.projectStateFormat },
        projectDetailFormat = projectState.projectDetailFormat.ifBlank { defaultsState.projectDetailFormat },
        fileStateFormat = projectState.fileStateFormat.ifBlank { defaultsState.fileStateFormat },
        fileDetailFormat = projectState.fileDetailFormat.ifBlank { defaultsState.fileDetailFormat },
        idleStateFormat = projectState.idleStateFormat.ifBlank { defaultsState.idleStateFormat },
        idleDetailFormat = projectState.idleDetailFormat.ifBlank { defaultsState.idleDetailFormat },
        firstInit = projectState.firstInit
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

        internal fun resolveIDEIcon(settingIcon: IDEIconDefaultable, defaultIcon: IDEIcon): IDEIcon {
            return when (settingIcon) {
                IDEIconDefaultable.Default -> defaultIcon
                IDEIconDefaultable.New -> IDEIcon.New
                IDEIconDefaultable.Old -> IDEIcon.Old
                IDEIconDefaultable.JetBrains -> IDEIcon.JetBrains
                IDEIconDefaultable.CatActivity -> IDEIcon.CatActivity
                IDEIconDefaultable.Custom -> IDEIcon.Custom
            }
        }

        internal fun resolveIconVariant(settingVariant: IDEIconDefaultable, defaultVariant: IDEIcon): IDEIcon {
            return when (settingVariant) {
                IDEIconDefaultable.Default -> defaultVariant
                IDEIconDefaultable.New -> IDEIcon.New
                IDEIconDefaultable.Old -> IDEIcon.Old
                IDEIconDefaultable.JetBrains -> IDEIcon.JetBrains
                IDEIconDefaultable.CatActivity -> IDEIcon.CatActivity
                IDEIconDefaultable.Custom -> IDEIcon.Custom
            }
        }

        internal fun resolveApplicationId(projectState: ProjectState, defaultsState: DefaultsState): Long {
            val ideIcon = resolveIDEIcon(projectState.ideIcon, defaultsState.ideIcon)
            if (ideIcon != IDEIcon.Custom) {
                return ideIcon.app?.applicationId ?: currentIDEType.applicationId
            }
            val raw = projectState.customApplicationId.ifBlank { defaultsState.customApplicationId }
            return parseApplicationId(raw) ?: currentIDEType.applicationId
        }

        internal fun resolveApplicationTitle(projectState: ProjectState, defaultsState: DefaultsState): String {
            val ideIcon = resolveIDEIcon(projectState.ideIcon, defaultsState.ideIcon)
            if (ideIcon != IDEIcon.Custom) {
                return ideIcon.app?.title ?: currentIDEType.title
            }
            return projectState.customApplicationTitle.ifBlank { defaultsState.customApplicationTitle }
                .ifBlank { currentIDEType.title }
        }

        internal fun resolveIconVariant(projectState: ProjectState, defaultsState: DefaultsState): IDEIcon {
            val ideIcon = resolveIDEIcon(projectState.ideIcon, defaultsState.ideIcon)
            if (ideIcon != IDEIcon.Custom) return ideIcon

            val variant = resolveIconVariant(projectState.customIconVariant, defaultsState.customIconVariant)
            if (variant == IDEIcon.Custom && resolveCustomIconUrl(projectState, defaultsState) == null) {
                return IDEIcon.New
            }
            return variant
        }

        internal fun resolveCustomIconUrl(projectState: ProjectState, defaultsState: DefaultsState): String? {
            val ideIcon = resolveIDEIcon(projectState.ideIcon, defaultsState.ideIcon)
            if (ideIcon != IDEIcon.Custom) return null

            val variant = resolveIconVariant(projectState.customIconVariant, defaultsState.customIconVariant)
            if (variant != IDEIcon.Custom) return null

            val raw = projectState.customIconUrl.ifBlank { defaultsState.customIconUrl }
            return normalizeIconUrl(raw)
        }
    }
}
