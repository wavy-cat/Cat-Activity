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
    SASS("SASS", "sass"),
    JSON("JSON", "json"),
    XML("XML", "xml"),
    YAML("YAML", "yaml"),
    MARKDOWN("Markdown", "markdown"),
    GIT("Git", "git"),
    SVELTE("Svelte", "svelte"),
    BASH("Bash", "bash"),
    BAT("Bat", "bat"),
    DATABASE("Database", "database"),
    DOCKER("Dockerfile", "docker"),
    DOCKER_IGNORE("Docker Ignore", "docker-ignore"),
    GO_MOD("Go.mod", "go-mod"),
    HASKELL("Haskell", "haskell"),
    HTTP("HTTP Request", "http"),
    IMAGE("Image", "image"),
    SVG("SVG", "svg"),
    PERL("Perl", "perl"),
    PRISMA("Prisma schema", "prisma"),
    PROPERTIES("Properties", "properties"),
    R("R", "r_lang"),
    SCALA("Scala", "scala"),
    TERRAFORM("Terraform CLI Config", "terraform"),
    TOML("TOML", "toml"),
    TXT("TXT", "txt"),
    FILE("File", "file"), // FALLBACK
}

fun getFileTypeByName(name: String, extension: String?) = when (name) {
    "JAVA" -> FileType.JAVA
//    else -> FileType.FILE.also {
//        DiscordIJ.logger.warn("Unknown file type: $name ($extension)")
//    }
    "Kotlin" -> FileType.KOTLIN
    "Rust" -> FileType.RUST
    "Python" -> FileType.PYTHON
    "JavaScript" -> FileType.JAVASCRIPT
    "TypeScript" -> FileType.TYPESCRIPT
    "Dockerfile" -> FileType.DOCKER
    "DockerIgnore file" -> FileType.DOCKER_IGNORE
    "Properties" -> FileType.PROPERTIES
    else -> when (extension) {
        "c", "h" -> FileType.C
        "cpp", "hpp", "cxx", "hxx", "cc", "hh" -> FileType.CPP
        "vue" -> FileType.VUE
        "cs" -> FileType.CSHARP
        "php", "phtml", "php3", "php4", "php5", "phps" -> FileType.PHP
        "go" -> FileType.GOLANG
        "swift" -> FileType.SWIFT
        "html", "htm" -> FileType.HTML
        "css" -> FileType.CSS
        "sass", "scss" -> FileType.SASS
        "json" -> FileType.JSON
        "xml", "xsd", "xsl", "xslt" -> FileType.XML
        "yaml", "yml" -> FileType.YAML
        "md", "markdown" -> FileType.MARKDOWN
        "gitignore", "gitconfig" -> FileType.GIT
        "svelte" -> FileType.SVELTE
        "rb" -> FileType.RUBY
        "sh" -> FileType.BASH
        "bat" -> FileType.BAT
        "sqlite", "sqlite3", "db", "database", "sql" -> FileType.DATABASE
        "mod" -> FileType.GO_MOD
        "hs", "lhs" -> FileType.HASKELL
        "http" -> FileType.HTTP
        "png", "jpeg", "jpg", "bmp", "webp", "eps", "gif" -> FileType.IMAGE
        "svg" -> FileType.SVG
        "pl" -> FileType.PERL
        "prisma" -> FileType.PRISMA
        "r" -> FileType.R
        "scala", "sc" -> FileType.SCALA
        "terraformrc" -> FileType.TERRAFORM
        "toml" -> FileType.TOML
        "txt" -> FileType.TXT
        else -> FileType.FILE.also {
            DiscordIJ.logger.warn("Unknown file type: $name ($extension)")
        }
    }
}