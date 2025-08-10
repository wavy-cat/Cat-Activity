package cat.wavy.catactivity.types

import com.intellij.openapi.application.ex.ApplicationInfoEx

enum class IDEType(
    val title: String,
    val icon: String,
    val applicationId: Long,
) {
    IDEA("IntelliJ IDEA", "intellij_idea", 1226060730273497098),
    WEBSTORM("WebStorm", "webstorm", 1226061359217774703),
    PYCHARM("PyCharm", "pycharm", 1226061280125653013),
    CLION("CLion", "clion", 1226061434320846949),
    GOLAND("GoLand", "goland", 1226061515212193853),
    RIDER("Rider", "rider", 1226061591087157300),
    PHPSTORM("PhpStorm", "phpstorm", 1226061672276299817),
    ANDROID_STUDIO("Android Studio", "android_studio", 1226061783484338269),
    RUSTROVER("RustRover", "rustrover", 1226061856167301140),
    RUBYMINE("RubyMine", "rubymine", 1226061949897412639),
    WRITERSIDE("Writerside", "writerside", 1226075499168534538),
    AQUA("Aqua", "aqua", 1226075813003132949),
    DATASPELL("DataSpell", "dataspell", 1226075981727404032),
    DATAGRIP("DataGrip", "datagrip", 1226108086985424896),
    APPCODE("AppCode", "appcode", 1247235403832885371),
    MPS("MPS", "mps", 1247234752470188113),
    CATACTIVITY("Cat Activity", "cat_activity", 1382041195777097799),
    JETBRAINS("JetBrains", "jetbrains", 1199736719377965187), // FALLBACK
}

val currentIDEType by lazy {
    val info = ApplicationInfoEx.getInstanceEx()
    when (info.build.productCode) {
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
        "WRS" -> IDEType.WRITERSIDE
        "DS" -> IDEType.DATASPELL
        "DB" -> IDEType.DATAGRIP
        "QA" -> IDEType.AQUA
        "OC" -> IDEType.APPCODE
        "MPS" -> IDEType.MPS
        else -> IDEType.JETBRAINS
    }
}