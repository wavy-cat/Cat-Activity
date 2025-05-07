package cat.wavy.catactivity.types

import com.intellij.openapi.application.ex.ApplicationInfoEx

enum class IDEType(
    val title: String,
    val icon: String
) {
    IDEA("IntelliJ IDEA", "intellij_idea"),
    WEBSTORM("WebStorm", "webstorm"),
    PYCHARM("PyCharm", "pycharm"),
    CLION("CLion", "clion"),
    GOLAND("GoLand", "goland"),
    RIDER("Rider", "rider"),
    PHPSTORM("PhpStorm", "phpstorm"),
    ANDROID_STUDIO("Android Studio", "android_studio"),
    RUSTROVER("RustRover", "rustrover"),
    RUBYMINE("RubyMine", "rubymine"),
    WRITERSIDE("Writerside", "writerside"),
    AQUA("Aqua", "aqua"),
    DATASPELL("DataSpell", "dataspell"),
    DATAGRIP("DataGrip", "datagrip"),
    APPCODE("AppCode", "appcode"),
    MPS("MPS", "mps"),
    JETBRAINS("JetBrains", "jetbrains"), // FALLBACK
}

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
        "WRS" -> IDEType.WRITERSIDE
        "DS" -> IDEType.DATASPELL
        "DB" -> IDEType.DATAGRIP
        "QA" -> IDEType.AQUA
        "OC" -> IDEType.APPCODE
        "MPS" -> IDEType.MPS
        else -> IDEType.JETBRAINS
    }
}

const val defaultApplicationId = 1199736719377965187

val applicationId by lazy {
    when (currentIDEType) {
        IDEType.IDEA -> 1226060730273497098
        IDEType.PYCHARM -> 1226061280125653013
        IDEType.WEBSTORM -> 1226061359217774703
        IDEType.CLION -> 1226061434320846949
        IDEType.GOLAND -> 1226061515212193853
        IDEType.RIDER -> 1226061591087157300
        IDEType.PHPSTORM -> 1226061672276299817
        IDEType.ANDROID_STUDIO -> 1226061783484338269
        IDEType.RUSTROVER -> 1226061856167301140
        IDEType.RUBYMINE -> 1226061949897412639
        IDEType.WRITERSIDE -> 1226075499168534538
        IDEType.AQUA -> 1226075813003132949
        IDEType.DATASPELL -> 1226075981727404032
        IDEType.DATAGRIP -> 1226108086985424896
        IDEType.APPCODE -> 1247235403832885371
        IDEType.MPS -> 1247234752470188113
        else -> defaultApplicationId // IDEType.JETBRAINS
    }
}