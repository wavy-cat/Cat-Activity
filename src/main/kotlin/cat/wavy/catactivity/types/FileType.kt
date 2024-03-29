package cat.wavy.catactivity.types

import cat.wavy.catactivity.CatActivity

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
    TERRAFORM("Terraform", "terraform"),
    TOML("TOML", "toml"),
    TXT("TXT", "txt"),
    CSV("CSV", "csv"),
    LUA("Lua", "lua"),
    GROOVY("Groovy", "groovy"),
    ERLANG("Erlang", "erlang"),
    FSHARP("F#", "fsharp"),
    ASSEMBLY("Assembly", "assembly"),
    DART("Dart", "dart"),
    JSX("JSX", "react"),
    TSX("TSX", "react"),
    ENV(".env", "env"),
    FILE("File", "file"), // FALLBACK
}

fun getFileTypeByName(name: String, extension: String?) = when (name) {
    "JAVA" -> FileType.JAVA
    "Kotlin" -> FileType.KOTLIN
    "Rust" -> FileType.RUST
    "Python" -> FileType.PYTHON
    "JavaScript" -> FileType.JAVASCRIPT
    "TypeScript" -> FileType.TYPESCRIPT
    "Dockerfile" -> FileType.DOCKER
    "DockerIgnore file" -> FileType.DOCKER_IGNORE
    "Properties" -> FileType.PROPERTIES
    "CSV" -> FileType.CSV
    "lua" -> FileType.LUA
    "Groovy" -> FileType.GROOVY
    "Erlang" -> FileType.ERLANG
    "Dart" -> FileType.DART
    else -> when (extension?.lowercase()) {
        "c", "h" -> FileType.C
        "cpp", "hpp", "cxx", "hxx", "cc", "hh", "ipp" -> FileType.CPP
        "vue" -> FileType.VUE
        "cs", "csx" -> FileType.CSHARP
        "fs", "fsi", "fsx", "fsscript" -> FileType.FSHARP
        "php", "phtml", "php3", "php4", "php5", "php7", "php8", "phps" -> FileType.PHP
        "go", "gox" -> FileType.GOLANG
        "swift", "swiftui" -> FileType.SWIFT
        "html", "htm" -> FileType.HTML
        "css" -> FileType.CSS
        "sass", "scss" -> FileType.SASS
        "json" -> FileType.JSON
        "xml", "xsd", "xsl", "xslt" -> FileType.XML
        "eyaml", "yaml", "yml" -> FileType.YAML
        "md", "markdown" -> FileType.MARKDOWN
        "gitignore", "gitconfig", "gitmodules" -> FileType.GIT
        "svelte" -> FileType.SVELTE
        "rb", "rbw" -> FileType.RUBY
        "sh", "bashrc", "bash_profile", "zshrc", "shrc" -> FileType.BASH
        "bat" -> FileType.BAT
        "sqlite", "sqlite3", "db", "database", "sql" -> FileType.DATABASE
        "mod" -> FileType.GO_MOD
        "hs", "lhs" -> FileType.HASKELL
        "http" -> FileType.HTTP
        "png", "jpeg", "jpg", "bmp", "webp", "eps", "gif", "ico", "tiff" -> FileType.IMAGE
        "svg" -> FileType.SVG
        "pl", "pm", "pod" -> FileType.PERL
        "prisma" -> FileType.PRISMA
        "r" -> FileType.R
        "scala", "sc" -> FileType.SCALA
        "terraformrc" -> FileType.TERRAFORM
        "toml" -> FileType.TOML
        "txt" -> FileType.TXT
        "asm", "s" -> FileType.ASSEMBLY
        "jsx" -> FileType.JSX
        "tsx" -> FileType.TSX
        "env" -> FileType.ENV
        else -> FileType.FILE.also {
            CatActivity.logger.warn("Unknown file type: $name ($extension)")
        }
    }
}