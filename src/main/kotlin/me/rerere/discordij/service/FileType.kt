package me.rerere.discordij.service

import me.rerere.discordij.DiscordIJ

enum class FileType(
    val typeName: String, // can be got from VirtualFile.getFileType().getName()
    val icon: String
) {
    JAVA("Java", "java"),
    KOTLIN("Kotlin", "kotlin"),
    RUST("Rust", "rust"),
    PYTHON("Python", "python"),
    JAVASCRIPT("JavaScript", "javascript"),
    TYPESCRIPT("TypeScript", "typescript"),
    C("C", "c"),
    CPP("C++", "cpp"),
    CSHARP("C#", "csharp"),
    VUE("Vue", "vue"),
    PHP("PHP", "php"),
    GOLANG("Go", "golang"),
    RUBY("Ruby", "ruby"),
    SWIFT("Swift", "swift"),
    HTML("HTML", "html"),
    CSS("CSS", "css"),
    JSON("JSON", "json"),
    XML("XML", "xml"),
    YAML("YAML", "yaml"),
    MARKDOWN("Markdown", "markdown"),
    GIT("Git", "git"),
    SVELTE("Svelte", "svelte"),
    FILE("*", "file"), // FALLBACK
}

fun getFileTypeByName(name: String, extension: String?) = when (name) {
    "JAVA" -> FileType.JAVA
    "Kotlin" -> FileType.KOTLIN
    "Rust" -> FileType.RUST
    "Python" -> FileType.PYTHON
    "JavaScript" -> FileType.JAVASCRIPT
    "TypeScript" -> FileType.TYPESCRIPT
    else -> when (extension) {
        "c", "h" -> FileType.C
        "cpp", "hpp", "cxx", "hxx", "cc", "hh" -> FileType.CPP
        "vue" -> FileType.VUE
        "cs" -> FileType.CSHARP
        "php", "phtml", "php3", "php4", "php5", "phps" -> FileType.PHP
        "go" -> FileType.GOLANG
        "swift" -> FileType.SWIFT
        "html", "htm" -> FileType.HTML
        "css", "sass", "scss" -> FileType.CSS
        "json" -> FileType.JSON
        "xml", "xsd", "xsl", "xslt" -> FileType.XML
        "yaml", "yml" -> FileType.YAML
        "md", "markdown" -> FileType.MARKDOWN
        "gitignore", "gitconfig" -> FileType.GIT
        "svelte" -> FileType.SVELTE
        "rb" -> FileType.RUBY
        else -> FileType.FILE.also {
            DiscordIJ.logger.warn("Unknown file type: $name ($extension)")
        }
    }
}