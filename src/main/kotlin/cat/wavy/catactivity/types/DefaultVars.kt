package cat.wavy.catactivity.types

enum class DefaultVars(
    val default: String
) {
    BRANCH("no branch"),
    REPO("Unknown repo"),
    PROJECTNAME("No name"),
    PROJECTPATH("--"),
    FILENAME("Empty"),
    FILEPATH("--"),
}