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
    HEROKU("Heroku procfile", "heroku"),
    ADONIS("Adonis", "adonis"),
    ALEX("alex", "alex"),
    ANDROID_MANIFEST("AndroidManifest.xml", "android"),
    ANTLR("ANTLR", "antlr"),
    API_BLUEPRINT("API Blueprint", "api-blueprint"),
    APOLLO("Apollo GraphQL", "apollo"),
    ASCIIDOC("AsciiDoc", "asciidoc"),
    ASTRO("Astro", "astro"),
    ASTRO_CONFIG("Astro Config", "astro-config"),
    AZURE_PIPELINES("Azure Pipelines", "azure-pipelines"),
    BABEL("Babel", "babel"),
    BIOME("Biome", "biome"),
    BITBUCKET("Bitbucket Pipelines", "bitbucket"),
    BLITZ("Blitz", "blitz"),
    BOWER("Bower", "bower"),
    BROWSERSLIST("Browserslist", "browserslist"),
    BUNFIG("Bun Config", "bun"),
    BUN_LOCK("Bun Lock", "bun-lock"),
    CADDYFILE("Caddyfile", "caddy"),
    CAPACITOR("Capacitor", "capacitor"),
    CARGO("Cargo", "cargo"),
    CARGO_LOCK("Cargo Lock", "cargo-lock"),
    CHANGELOG("Changelog", "changelog"),
    CIRCLECI("CircleCI", "circle-ci"),
    CODE_CLIMATE("Code Climate", "code-climate"),
    CODE_OF_CONDUCT("Code of conduct", "code-of-conduct"),
    CODEOWNERS("CODEOWNERS", "codeowners"),
    COMMITLINT("commitlint", "commitlint"),
    CONTRIBUTING("CONTRIBUTING", "contributing"),
    CYPRESS("Cypress", "cypress"),
    D("D", "d"),
    DENO("Deno", "deno"),
    DENO_LOCK("Deno Lock", "deno_lock"),
    DEPENDABOT("Dependabot", "dependabot"),
    DEVCONTAINER("Devcontainer", "devcontainer"),
    FILE("File", "file"), // FALLBACK
}

fun getFileTypeByName(fileType: String, fileName: String, extension: String?) = when (fileType) {
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
        "http", "rest" -> FileType.HTTP
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
        "g4" -> FileType.ANTLR
        "apib", "apiblueprint" -> FileType.API_BLUEPRINT
        "adoc", "asciidoc", "asc" -> FileType.ASCIIDOC
        "astro" -> FileType.ASTRO
        "d" -> FileType.D
        else -> when (fileName.lowercase()) {
            "procfile" -> FileType.HEROKU
            ".adonisrc.json", "ace" -> FileType.ADONIS
            ".alexrc", ".alexrc.yaml", ".alexrc.yml", ".alexrc.js" -> FileType.ALEX
            "androidmanifest.xml" -> FileType.ANDROID_MANIFEST
            "apollo.config.js", "apollo.config.ts" -> FileType.APOLLO
            "astro.config.js", "astro.config.mjs", "astro.config.cjs", "astro.config.ts", "astro.config.cts", "astro.config.mts" -> FileType.ASTRO_CONFIG
            "azure-pipelines.yml", "azure-pipelines.yaml", "azure-pipelines-main.yml", "azure-pipelines-main.yaml" -> FileType.AZURE_PIPELINES
            ".babelrc", ".babelrc.cjs", ".babelrc.js", ".babelrc.mjs", ".babelrc.json", "babel.config.cjs", "babel.config.js", "babel.config.mjs", "babel.config.json", "babel-transform.js", ".babel-plugin-macrosrc", ".babel-plugin-macrosrc.json", ".babel-plugin-macrosrc.yaml", ".babel-plugin-macrosrc.yml", ".babel-plugin-macrosrc.js", "babel-plugin-macros.config.js" -> FileType.BABEL
            "biome.json", "biome.jsonc" -> FileType.BIOME
            "bitbucket-pipelines.yaml", "bitbucket-pipelines.yml" -> FileType.BITBUCKET
            "blitz.config.js", "blitz.config.ts", ".blitz.config.compiled.js" -> FileType.BLITZ
            ".bowerrc", "bower.json" -> FileType.BOWER
            "browserslist", ".browserslistrc" -> FileType.BROWSERSLIST
            "bunfig.toml" -> FileType.BUNFIG
            "bun.lockb" -> FileType.BUN_LOCK
            "caddyfile" -> FileType.CADDYFILE
            "capacitor.config.json", "capacitor.config.ts" -> FileType.CAPACITOR
            "cargo.toml" -> FileType.CARGO
            "cargo.lock" -> FileType.CARGO_LOCK
            "changelog", "changelog.md", "changelog.rst", "changelog.txt", "changes", "changes.md", "changes.rst", "changes.txt" -> FileType.CHANGELOG
            "circle.yml" -> FileType.CIRCLECI
            ".codeclimate.yml" -> FileType.CODE_CLIMATE
            "code_of_conduct.md", "code_of_conduct.txt", "code_of_conduct" -> FileType.CODE_OF_CONDUCT
            "codeowners", "owners" -> FileType.CODEOWNERS
            ".commitlintrc", ".commitlintrc.js", ".commitlintrc.cjs", ".commitlintrc.json", ".commitlintrc.yaml", ".commitlintrc.yml", ".commitlint.yaml", ".commitlint.yml", "commitlint.config.js", "commitlint.config.cjs", "commitlint.config.ts", "commitlint.config.cts" -> FileType.COMMITLINT
            "contributing", "contributing.md", "contributing.rst", "contributing.txt" -> FileType.CONTRIBUTING
            "cypress.json", "cypress.env.json", "cypress.config.ts", "cypress.config.js", "cypress.config.cjs", "cypress.config.mjs" -> FileType.CYPRESS
            "deno.json", "deno.jsonc" -> FileType.DENO
            "deno.lock" -> FileType.DENO_LOCK
            "dependabot.yml", "dependabot.yaml" -> FileType.DEPENDABOT
            "devcontainer.json", ".devcontainer.json" -> FileType.DEVCONTAINER
            else -> FileType.FILE.also {
                CatActivity.logger.warn("Unknown file type: $fileType ($extension)")
            }
        }
    }
}