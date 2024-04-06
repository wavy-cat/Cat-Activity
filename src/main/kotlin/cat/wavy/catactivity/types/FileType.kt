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
    C_HEADER("Header file", "c_header"),
    CPP_HEADER("Header file", "cpp_header"),
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
    JUPYTER("Jupyter", "jupyter"),
    AUDIO("Audio", "audio"),
    BINARY("Binary", "binary"),
    CERTIFICATE("Certificate", "certificate"),
    CLOJURE("Clojure", "clojure"),
    CMAKE("CMake", "cmake"),
    COBOL("Cobol", "cobol"),
    COFFEESCRIPT("CoffeeScript", "coffeescript"),
    CUDA("CUDA", "cuda"),
    DIFF("Diff", "diff"),
    DJANGO("Django", "django"),
    EJS("EJS", "ejs"),
    ELIXIR("Elixir", "elixir"),
    ELM("Elm", "elm"),
    FIREBASE("Firebase", "firebase"),
    FONT("Font", "font"),
    FORTRAN("Fortran", "fortran"),
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
    "Jupyter" -> FileType.JUPYTER
    "DIFF" -> FileType.DIFF
    else -> when (extension?.lowercase()) {
        "c", "i", "mi" -> FileType.C
        "h" -> FileType.C_HEADER
        "cpp", "c++", "cp", "cc", "cxx", "mii", "ii" -> FileType.CPP
        "hh", "hpp", "hxx", "h++", "hp", "tcc", "inl" -> FileType.CPP_HEADER
        "vue" -> FileType.VUE
        "cs", "csx", "csharp" -> FileType.CSHARP
        "fs", "fsi", "fsx", "fsscript", "fsproj" -> FileType.FSHARP
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
        "gitignore", "gitconfig", "gitmodules", "gitattributes", "gitkeep", "gitinclude" -> FileType.GIT
        "svelte" -> FileType.SVELTE
        "rb", "rbw" -> FileType.RUBY
        "bash", "sh", "bashrc", "bash_profile", "zshrc", "shrc", "ksh", "awk", "csh", "tcsh", "fish", "zsh" -> FileType.BASH
        "bat", "cmd" -> FileType.BAT
        "sqlite", "sqlite3", "db", "database", "sql", "pdb", "db3", "pks", "pkb", "accdb", "mdb", "pgsql", "postgres", "plpgsql", "psql" -> FileType.DATABASE
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
        "asm", "s", "a51", "incc", "nasm", "ms", "agc", "ags", "aea", "argus", "mitigus", "binsource" -> FileType.ASSEMBLY
        "jsx" -> FileType.JSX
        "tsx" -> FileType.TSX
        "env" -> FileType.ENV
        "aac", "aiff", "alac", "flac", "m4a", "m4p", "mogg", "mp3", "oga", "opus", "wav", "wma", "wv" -> FileType.AUDIO
        "bin" -> FileType.BINARY
        "cer", "cert", "crt", "pfx" -> FileType.CERTIFICATE
        "clj", "cljs", "cljc" -> FileType.CLOJURE
        "cmake" -> FileType.CMAKE
        "cob", "cbl" -> FileType.COBOL
        "coffee", "cson", "iced" -> FileType.COFFEESCRIPT
        "cu", "cuh" -> FileType.CUDA
        "djt" -> FileType.DJANGO
        "ejs" -> FileType.EJS
        "ex", "exs", "eex", "leex", "heex" -> FileType.ELIXIR
        "elm" -> FileType.ELM
        "firebaserc" -> FileType.FIREBASE
        "woff", "woff2", "ttf", "eot", "suit", "otf", "bmap", "fnt", "odttf", "ttc", "font", "fonts", "sui", "ntf", "mrf" -> FileType.FONT
        "f", "f77", "f90", "f95", "f03", "f08" -> FileType.FORTRAN
        else -> FileType.FILE.also {
            CatActivity.logger.warn("Unknown file type: $name ($extension)")
        }
    }
}