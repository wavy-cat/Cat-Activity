package cat.wavy.catactivity.setting

import cat.wavy.catactivity.DEFAULT_ICONS_URL
import cat.wavy.catactivity.types.IDEType
import cat.wavy.catactivity.types.currentIDEType
import java.net.URI

data class DefaultsState(
    var theme: Theme = Theme.Macchiato,
    var ideIcon: IDEIcon = IDEIcon.New,
    var useCustomIconsServer: Boolean = false,
    var customIconsUrl: String = "",

    var projectStateFormat: String = "Working on %projectName%",
    var projectDetailFormat: String = "%projectProblems% Problems in Project",

    var fileStateFormat: String = "Editing %fileName%",
    var fileDetailFormat: String = "%projectName% (%branch%)",

    var idleStateFormat: String = "Idle",
    var idleDetailFormat: String = "%projectName% (%branch%)",
) {
    val iconsUrl: String
        get() = if (useCustomIconsServer) normalizeUrl(customIconsUrl) ?: DEFAULT_ICONS_URL else DEFAULT_ICONS_URL
}

internal fun normalizeUrl(value: String): String? {
    val trimmed = value.trim().trimEnd('/')
    if (trimmed.isEmpty()) return null

    return runCatching {
        val uri = URI(trimmed)
        if (!uri.isAbsolute || uri.host.isNullOrBlank() || uri.rawUserInfo != null ||
            uri.rawQuery != null || uri.rawFragment != null ||
            !uri.scheme.equals("http", ignoreCase = true) && !uri.scheme.equals("https", ignoreCase = true)
        ) return null
        uri.toASCIIString()
    }.getOrNull()
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

