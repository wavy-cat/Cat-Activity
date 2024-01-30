package me.rerere.discordij.service

import com.intellij.openapi.application.ex.ApplicationInfoEx
import me.rerere.discordij.DiscordIJ

enum class IDEType(
    val title: String,
    val icon: String
) {
    IDEA("IntelliJ IDEA", "idea"),
    WEBSTORM("WebStorm", "webstorm"),
    PYCHARM("PyCharm", "pycharm"),
    CLION("CLion", "clion"),
    GOLAND("GoLand", "goland"),
    RIDER("Rider", "rider"),
    PHPSTORM("PhpStorm", "phpstorm"),
    ANDROID_STUDIO("Android Studio", "android_studio"),
    RUSTROVER("RustRover", "rustrover"),
    RUBYMINE("RubyMine", "rubymine"),
    JETBRAINS("JetBrains", "jetbrains"), // FALLBACK
}

/**
 * Get current IDE type
 *
 * IU - IntelliJ IDEA Ultimate
 * IC - IntelliJ IDEA Community
 * IE - IntelliJ IDEA Educational
 * PS - PhpStorm
 * WS - WebStorm
 * PY - PyCharm Professional
 * PC - PyCharm Community
 * PE - PyCharm Educational
 * RM - RubyMine
 * OC - AppCode
 * CL - CLion / CLion Nova
 * GO - GoLand
 * DB - DataGrip
 * RD - Rider
 * AI - Android Studio
 * RR - RustRover
 * RM - RubyMine
 */
val currentIDEType by lazy {
    val info = ApplicationInfoEx.getInstanceEx()
    when(info.build.productCode) {
        "IC", "IU" -> IDEType.IDEA
        "PC", "PY" -> IDEType.PYCHARM
        "WS" -> IDEType.WEBSTORM
        "CL" -> IDEType.CLION
        "GO" -> IDEType.GOLAND
        "RD" -> IDEType.RIDER
        "PS" -> IDEType.PHPSTORM
        "AI" -> IDEType.ANDROID_STUDIO
        "RR" -> IDEType.RUSTROVER
        "RM" -> IDEType.RUBYMINE
        else -> IDEType.JETBRAINS.also {
            DiscordIJ.logger.warn("Unknown IDE type: ${info.build.productCode}")
        }
    }
}