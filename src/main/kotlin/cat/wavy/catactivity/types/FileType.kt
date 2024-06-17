package cat.wavy.catactivity.types

import cat.wavy.catactivity.CatActivity

enum class FileType(
    val typeName: String,
    val icon: String
) {
    JAVA("Java", "java"),
    KOTLIN("Kotlin", "kotlin"),
    RUST("Rust", "rust"),
    PYTHON("Python", "python"),
    JAVASCRIPT("JavaScript", "javascript"),
    TYPESCRIPT("TypeScript", "typescript"),
    C("C lang", "c"),
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
    MARKDOWN_MDX("MDX", "markdown-mdx"),
    GIT("Git", "git"),
    SVELTE("Svelte", "svelte"),
    SVELTE_CONFIG("Svalte Config", "svelte-config"),
    BASH("Bash", "bash"),
    BAT("Bat", "bat"),
    DATABASE("Database", "database"),
    DOCKER("Dockerfile", "docker"),
    DOCKER_IGNORE("Docker Ignore", "docker-ignore"),
    DOCKER_COMPOSE("Docker Compose", "docker-compose"),
    GO_MOD("Go.mod", "go-mod"),
    HASKELL("Haskell", "haskell"),
    HTTP("HTTP Request", "http"),
    IMAGE("Image", "image"),
    SVG("SVG", "svg"),
    PERL("Perl", "perl"),
    PRISMA("Prisma schema", "prisma"),
    PROPERTIES("Properties", "properties"),
    R("R lang", "r_lang"),
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
    D("D lang", "d"),
    DENO("Deno", "deno"),
    DENO_LOCK("Deno Lock", "deno_lock"),
    DEPENDABOT("Dependabot", "dependabot"),
    DEVCONTAINER("Devcontainer", "devcontainer"),
    DHALL("Dhall", "dhall"),
    DRAWIO("Diagram file", "drawio"),
    EDITORCONFIG("EditorConfig", "editorconfig"),
    ELEVENTY("Eleventy", "eleventy"),
    EMBER("Ember.js", "ember"),
    ESBUILD("esbuild", "esbuild"),
    ESLINT("ESLint", "eslint"),
    ESLINT_IGNORE("ESLint Ignore File", "eslint-ignore"),
    FASTLANE("fastlane", "fastlane"),
    GATSBY("Gatsby", "gatsby"),
    GCP("GCP", "gcp"),
    GITPOD("Gitpod", "gitpod"),
    GODOT("GodotScript", "godot"),
    GODOT_ASSETS("Godot Assets", "godot-assets"),
    GRADLE("Gradle", "gradle"),
    GRAPHQL("GraphQL", "graphql"),
    GULP("Gulp", "gulp"),
    HAML("Haml", "haml"),
    HANDLEBARS("Handlebars", "handlebars"),
    HAXE("Haxe", "haxe"),
    HISTOIRE("Histoire", "histoire"),
    HUSKY("Husky", "husky"),
    IONIC("Ionic", "ionic"),
    JAVASCRIPT_CONFIG("JavaScript Config", "javascript-config"),
    JEST("Jest", "jest"),
    JINJA("Jinja", "jinja"),
    JULIA("Julia", "julia"),
    KEYSTORE("Keystore", "key"),
    LATEX("LaTeX", "latex"),
    LERNA("Lerna", "lerna"),
    LESS("Less", "less"),
    LIB("Lib", "lib"),
    LICENSE("License", "license"),
    LINT_STAGED("lint-staged", "lint-staged"),
    LIQUID("LiquidJS", "liquid"),
    LISP("Lisp", "lisp"),
    LOG("Log file", "log"),
    MAKEFILE("Makefile", "makefile"),
    MARKO("Marko", "marko"),
    MAVEN("Maven", "maven"),
    MERMAID("Mermaid", "mermaid"),
    MESON("Meson", "meson"),
    MJML("MJML", "mjml"),
    MODERNIZR("Modernizr", "modernizr"),
    EXCEL("Excel", "ms-excel"),
    NATIVESCRIPT("NativeScript", "nativescript"),
    NEST("Nest", "nest"),
    NETLIFY("Netlify", "netlify"),
    NEXT("Next", "next"),
    NEXTFLOW("Nextflow", "nextflow"),
    NGINX("nginx", "nginx"),
    NIM("Nim", "nim"),
    NINJA("Ninja", "ninja"),
    NIX("Nix", "nix"),
    NIX_LOCK("flake.lock", "nix-lock"),
    NODEMON("Nodemon", "nodemon"),
    NPM("npm rcfile", "npm"),
    NPM_IGNORE("npm ignorefile", "npm-ignore"),
    NPM_LOCK("npm lockfile", "npm-lock"),
    NUGET("NuGet", "nuget"),
    NUNJUCKS("Nunjucks", "nunjucks"),
    NUXT("Nuxt", "nuxt"),
    NUXT_IGNORE("Nuxt Ignorefile", "nuxt-ignore"),
    OCAML("OCaml", "ocaml"),
    PACKAGE_JSON("package.json", "package-json"),
    PANDA_CSS("Panda CSS", "panda-css"),
    PDF("PDF", "pdf"),
    PHRASE("Phrase", "phrase"),
    PLAYWRIGHT("Playwright", "playwright"),
    PLOP("Plop", "plop"),
    PNPM("pnpm", "pnpm"),
    PNPM_LOCK("pnpm lock", "pnpm-lock"),
    POSTCSS("PostCSS", "postcss"),
    POWERSHELL("Powershell", "powershell"),
    PREMAKE("Premake", "premake"),
    PRETTIER("Prettier", "prettier"),
    PRETTIER_IGNORE("Prettier Ignorefile", "prettier-ignore"),
    PROLOG("Prolog", "prolog"),
    PUG("Pug", "pug"),
    PUPPETEER("Puppeteer", "puppeteer"),
    PYTHON_COMPILED("Python Compiled", "python-compiled"),
    RACKET("Racket", "racket"),
    RAZOR("Razor", "razor"),
    README("README", "readme"),
    REASON("Reason", "reason"),
    REDWOOD("Redwood", "redwood"),
    REMIX("Remix", "remix"),
    RENOVATE("Renovate", "renovate"),
    RESCRIPT("ReScript", "rescript"),
    ROBOTS("robots.txt", "robots"),
    ROLLUP("Rollup", "rollup"),
    RUBY_GEM("Ruby Gemfile", "ruby-gem"),
    RUBY_GEM_LOCK("Ruby Gem Lockfile", "ruby-gem-lock"),
    SECURITY("SECURITY", "security"),
    SEMANTIC_RELEASE("Semantic Release", "semantic-release"),
    SEMGREP("Semgrep", "semgrep"),
    SEMGREP_IGNORE("Semgrep Ignorefile", "semgrep-ignore"),
    SENTRY("Sentry", "sentry"),
    SERVERLESS("Serverless", "serverless"),
    SHADER("Shader", "shader"),
    SKETCH("Sketch", "sketch"),
    SNOWPACK("Snowpack", "snowpack"),
    SOLIDITY("Solidity", "solidity"),
    SONARCLOUD("SonarCloud", "sonar-cloud"),
    STACKBLITZ("StackBlitz", "stackblitz"),
    STENCIL("Stencil", "stencil"),
    STITCHES("Stitches", "stitches"),
    STORYBOOK("Storybook", "storybook"),
    STORYBOOK_SVELTE("Storybook Svelte", "storybook-svelte"),
    STORYBOOK_VUE("Storybook Vue", "storybook-vue"),
    STYLELINT("Stylelint", "stylelint"),
    STYLELINT_IGNORE("Stylelint Ignorefile", "stylelint-ignore"),
    SUBLIME("Sublime", "sublime"),
    TAILWIND("Tailwind CSS", "tailwind"),
    TAURI("Tauri", "tauri"),
    TAURI_IGNORE("Tauri Ignorefile", "tauri-ignore"),
    TODO("TODO", "todo"),
    TWIG("Twig", "twig"),
    TWINE("Twine", "twine"),
    TYPESCRIPT_CONFIG("TypeScript Config", "typescript-config"),
    UNITY("Unity", "unity"),
    UNOCSS("UnoCSS", "unocss"),
    URL("URL", "url"),
    V("V lang", "v"),
    VENTO("Vento", "vento"),
    VERCEL("Vercel", "vercel"),
    VERCEL_IGNORE("Vercel Ignorefile", "vercel-ignore"),
    VERILOG("Verilog", "verilog"),
    VIM("Vim file", "vim"),
    VISUAL_STUDIO("Visual Studio file", "visual-studio"),
    VSCODE("VSCode file", "vscode"),
    VSCODE_IGNORE("VSCode ignorefile", "vscode-ignore"),
    VITE("Vite", "vite"),
    VITEST("Vitest", "vitest"),
    VUE_CONFIG("Vue Config", "vue-config"),
    WEB_ASSEMBLY("WebAssembly", "web-assembly"),
    WEBPACK("webpack", "webpack"),
    WINDI("Windi CSS", "windi"),
    WXT("WXT", "wxt"),
    XAML("XAML", "xaml"),
    XMAKE("Xmake", "xmake"),
    YARN("Yarn", "yarn"),
    YARN_LOCK("Yarn Lockfile", "yarn-lock"),
    ZIG("Zig", "zig"),
    FILE("File", "file"), // FALLBACK
}

fun getFileTypeByName(fileType: String, fileName: String, extension: String?) = when (fileName.lowercase()) {
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
    "docker-compose.yml", "docker-compose.yaml", "compose.yaml", "compose.yml" -> FileType.DOCKER_COMPOSE
    ".dockerignore" -> FileType.DOCKER_IGNORE
    ".editorconfig" -> FileType.EDITORCONFIG
    ".eleventy.js", "eleventy.config.js", "eleventy.config.mjs", "eleventy.config.cjs", ".eleventyignore" -> FileType.ELEVENTY
    ".ember-cli", ".ember-cli.js", "ember-cli-builds.js" -> FileType.EMBER
    "esbuild.js", "esbuild.ts", "esbuild.cjs", "esbuild.mjs", "esbuild.config.js", "esbuild.config.ts", "esbuild.config.cjs", "esbuild.config.mjs" -> FileType.ESBUILD
    ".eslintrc.yaml", ".eslintrc.yml", ".eslintrc.json", ".eslintrc-md.js", ".eslintrc-jsdoc.js", ".eslintrc", "eslint.config.js", "eslint.config.cjs", "eslint.config.mjs", "eslint.config.ts" -> FileType.ESLINT
    ".eslintignore", ".eslintcache" -> FileType.ESLINT_IGNORE
    "fastfile", "appfile" -> FileType.FASTLANE
    ".firebaserc", "firebase.json", "firestore.rules", "firestore.indexes.json" -> FileType.FIREBASE
    "gatsby-config.js", "gatsby-config.mjs", "gatsby-config.ts", "gatsby-node.js", "gatsby-node.mjs", "gatsby-node.ts", "gatsby-browser.js", "gatsby-browser.tsx", "gatsby-ssr.js", "gatsby-ssr.tsx" -> FileType.GATSBY
    "release-please-config.json", ".release-please-manifest.json" -> FileType.GCP
    ".gitpod.yml" -> FileType.GITPOD
    "go.mod", "go.sum", "go.work", "go.work.sum" -> FileType.GO_MOD
    ".gdignore", "._sc_", "_sc_" -> FileType.GODOT_ASSETS
    "gradle.properties", "gradlew", "gradle-wrapper.properties" -> FileType.GRADLE
    ".graphqlconfig", ".graphqlrc", ".graphqlrc.json", ".graphqlrc.js", ".graphqlrc.cjs", ".graphqlrc.ts", ".graphqlrc.toml", ".graphqlrc.yaml", ".graphqlrc.yml", "graphql.config.json", "graphql.config.js", "graphql.config.cjs", "graphql.config.ts", "graphql.config.toml", "graphql.config.yaml", "graphql.config.yml" -> FileType.GRAPHQL
    "gulpfile.js", "gulpfile.mjs", "gulpfile.ts", "gulpfile.cts", "gulpfile.mts", "gulpfile.babel.js" -> FileType.GULP
    "histoire.config.ts", "histoire.config.js", ".histoire.js", ".histoire.ts" -> FileType.HISTOIRE
    ".huskyrc", "husky.config.js", ".huskyrc.json", ".huskyrc.js", ".huskyrc.yaml", ".huskyrc.yml" -> FileType.HUSKY
    "ionic.config.json", ".io-config.json" -> FileType.IONIC
    "jsconfig.json" -> FileType.JAVASCRIPT_CONFIG
    ".jestrc.json", "jest.teardown.js", "jest-preset.json", "jest-preset.js", "jest-preset.cjs", "jest-preset.mjs", "jest.preset.js", "jest.preset.mjs", "jest.preset.cjs", "jest.preset.json" -> FileType.JEST
    ".jscsrc", ".jshintrc", "composer.lock", ".jsbeautifyrc", ".esformatter", "cdp.pid", ".whitesource" -> FileType.JSON
    ".htpasswd" -> FileType.KEYSTORE
    "lerna.json" -> FileType.LERNA
    "copying", "copying.md", "copying.rst", "copying.txt", "copyright", "copyright.md", "copyright.rst", "copyright.txt", "license", "license-agpl", "license-apache", "license-bsd", "license-mit", "license-gpl", "license-lgpl", "license.md", "license.rst", "license.txt", "licence", "licence-agpl", "licence-apache", "licence-bsd", "licence-mit", "licence-gpl", "licence-lgpl", "licence.md", "licence.rst", "licence.txt" -> FileType.LICENSE
    ".lintstagedrc", ".lintstagedrc.json", ".lintstagedrc.yaml", ".lintstagedrc.yml", ".lintstagedrc.mjs", ".lintstagedrc.cjs", ".lintstagedrc.js", "lint-staged.config.js", "lint-staged.config.mjs", "lint-staged.config.cjs" -> FileType.LINT_STAGED
    ".liquidrc.json", ".liquidrc" -> FileType.LIQUID
    ".luacheckrc" -> FileType.LUA
    "makefile", "gnumakefile", "kbuild" -> FileType.MAKEFILE
    "maven.config", "jvm.config", "pom.xml" -> FileType.MAVEN
    "meson.build", "meson_options.txt" -> FileType.MESON
    ".mjmlconfig" -> FileType.MJML
    ".modernizrrc", ".modernizrrc.js", ".modernizrrc.json" -> FileType.MODERNIZR
    "nativescript.config.ts", "nativescript.config.js" -> FileType.NATIVESCRIPT
    "nest-cli.json", ".nest-cli.json", "nestconfig.json", ".nestconfig.json" -> FileType.NEST
    "netlify.json", "netlify.yml", "netlify.yaml", "netlify.toml" -> FileType.NETLIFY
    "next.config.js", "next.config.mjs", "next.config.ts", "next.config.mts" -> FileType.NEXT
    "nginx.conf" -> FileType.NGINX
    "nodemon.json", "nodemon-debug.json" -> FileType.NODEMON
    ".npmrc" -> FileType.NPM
    ".npmignore" -> FileType.NPM_IGNORE
    "package-lock.json" -> FileType.NPM_LOCK
    "nuget.config", ".nuspec" -> FileType.NUGET
    "nuxt.config.js", "nuxt.config.ts" -> FileType.NUXT
    ".nuxtignore" -> FileType.NUXT_IGNORE
    "package.json", ".nvmrc", ".esmrc", ".node-version" -> FileType.PACKAGE_JSON
    "panda.config.ts", "panda.config.js", "panda.config.mjs", "panda.config.mts", "panda.config.cjs" -> FileType.PANDA_CSS
    ".phrase.yml", ".phraseapp.yml", ".phrase.yaml", ".phraseapp.yaml" -> FileType.PHRASE
    "playwright.config.js", "playwright.config.mjs", "playwright.config.ts", "playwright.config.base.js", "playwright.config.base.mjs", "playwright.config.base.ts", "playwright-ct.config.js", "playwright-ct.config.mjs", "playwright-ct.config.ts" -> FileType.PLAYWRIGHT
    "plopfile.js", "plopfile.cjs", "plopfile.mjs", "plopfile.ts" -> FileType.PLOP
    "pnpm-workspace.yaml", ".pnpmfile.cjs" -> FileType.PNPM
    "pnpm-lock.yaml" -> FileType.PNPM_LOCK
    "postcss.config.js", "postcss.config.cjs", "postcss.config.ts", "postcss.config.cts", ".postcssrc.js", ".postcssrc.cjs", ".postcssrc.ts", ".postcssrc.cts", ".postcssrc", ".postcssrc.json", ".postcssrc.yaml", ".postcssrc.yml" -> FileType.POSTCSS
    "premake4.lua", "premake5.lua", "premake.lua" -> FileType.PREMAKE
    ".prettierrc", "prettier.config.js", "prettier.config.cjs", ".prettierrc.js", ".prettierrc.cjs", ".prettierrc.json", ".prettierrc.json5", ".prettierrc.yaml", ".prettierrc.yml", ".prettierrc.toml", "prettier.config.mjs", ".prettierrc.mjs" -> FileType.PRETTIER
    ".prettierignore" -> FileType.PRETTIER_IGNORE
    "prisma.yml" -> FileType.PRISMA
    ".pug-lintrc", ".pug-lintrc.js", ".pug-lintrc.json" -> FileType.PUG
    ".puppeteerrc.cjs,", ".puppeteerrc.js,", ".puppeteerrc", ".puppeteerrc.json,", ".puppeteerrc.yaml,", "puppeteer.config.js", "puppeteer.config.cjs" -> FileType.PUPPETEER
    ".rhistory" -> FileType.R
    "readme.md", "readme.rst", "readme.txt", "readme" -> FileType.README
    "redwood.toml" -> FileType.REDWOOD
    "remix.config.js", "remix.config.ts" -> FileType.REMIX
    ".renovaterc", ".renovaterc.json", "renovate-config.json", "renovate.json", "renovate.json5" -> FileType.RENOVATE
    "robots.txt" -> FileType.ROBOTS
    "rollup.config.js", "rollup.config.mjs", "rollup.config.ts", "rollup-config.js", "rollup-config.mjs", "rollup-config.ts", "rollup.config.common.js", "rollup.config.common.mjs", "rollup.config.common.ts", "rollup.config.base.js", "rollup.config.base.mjs", "rollup.config.base.ts", "rollup.config.prod.js", "rollup.config.prod.mjs", "rollup.config.prod.ts", "rollup.config.dev.js", "rollup.config.dev.mjs", "rollup.config.dev.ts", "rollup.config.prod.vendor.js", "rollup.config.prod.vendor.mjs", "rollup.config.prod.vendor.ts" -> FileType.ROLLUP
    "gemfile" -> FileType.RUBY_GEM
    "gemfile.lock" -> FileType.RUBY_GEM_LOCK
    "security.md", "security.txt", "security" -> FileType.SECURITY
    ".releaserc", ".releaserc.yaml", ".releaserc.yml", ".releaserc.json", ".releaserc.js", ".releaserc.cjs", "release.config.js", "release.config.cjs" -> FileType.SEMANTIC_RELEASE
    "semgrep.yml" -> FileType.SEMGREP
    ".semgrepignore" -> FileType.SEMGREP_IGNORE
    ".sentryclirc" -> FileType.SENTRY
    "serverless.yml", "serverless.yaml", "serverless.json", "serverless.js", "serverless.ts" -> FileType.SERVERLESS
    "snowpack.config.js", "snowpack.config.cjs", "snowpack.config.mjs", "snowpack.config.ts", "snowpack.config.cts", "snowpack.config.mts", "snowpack.deps.json", "snowpack.config.json" -> FileType.SNOWPACK
    "sonar-project.properties", ".sonarcloud.properties", "sonarcloud.yaml" -> FileType.SONARCLOUD
    ".stackblitzrc" -> FileType.STACKBLITZ
    "stencil.config.js", "stencil.config.ts" -> FileType.STENCIL
    "stitches.config.js", "stitches.config.ts" -> FileType.STITCHES
    "stories.js", "stories.jsx", "stories.mdx", "story.js", "story.jsx", "stories.ts", "stories.tsx", "story.ts", "story.tsx", "story.mdx" -> FileType.STORYBOOK
    "story.svelte", "stories.svelte" -> FileType.STORYBOOK_SVELTE
    "story.vue", "stories.vue" -> FileType.STORYBOOK_VUE
    ".stylelintrc", "stylelint.config.js", "stylelint.config.cjs", "stylelint.config.mjs", ".stylelintrc.json", ".stylelintrc.yaml", ".stylelintrc.yml", ".stylelintrc.js", ".stylelintrc.cjs", ".stylelintrc.mjs" -> FileType.STYLELINT
    ".stylelintignore", ".stylelintcache" -> FileType.STYLELINT_IGNORE
    "svelte.config.js", "svelte.config.ts", "svelte.config.cjs", "svelte.config.mjs" -> FileType.SVELTE_CONFIG
    "tailwind.js", "tailwind.ts", "tailwind.config.js", "tailwind.config.cjs", "tailwind.config.mjs", "tailwind.config.ts", "tailwind.config.cts", "tailwind.config.mts" -> FileType.TAILWIND
    "tauri.conf.json", "tauri.config.json", "tauri.linux.conf.json", "tauri.windows.conf.json", "tauri.macos.conf.json" -> FileType.TAURI
    ".taurignore" -> FileType.TAURI_IGNORE
    "todo.md", "todos.md" -> FileType.TODO
    "tsconfig.json", "tsconfig.app.json", "tsconfig.editor.json", "tsconfig.spec.json", "tsconfig.base.json", "tsconfig.build.json", "tsconfig.eslint.json", "tsconfig.lib.json", "tsconfig.lib.prod.json", "tsconfig.node.json", "tsconfig.test.json", "tsconfig.e2e.json", "tsconfig.web.json", "tsconfig.webworker.json", "tsconfig.worker.json", "tsconfig.config.json", "tsconfig.vitest.json", "tsconfig.cjs.json", "tsconfig.esm.json", "tsconfig.mjs.json", "tsconfig.doc.json", "tsconfig.paths.json", "tsconfig.main.json", "tsconfig.cypress-ct.json", "tsconfig.components.json" -> FileType.TYPESCRIPT_CONFIG
    "uno.config.js", "uno.config.mjs", "uno.config.ts", "uno.config.mts", "unocss.config.js", "unocss.config.mjs", "unocss.config.ts", "unocss.config.mts" -> FileType.UNOCSS
    "vpkg.json", "v.mod" -> FileType.V
    "vercel.json", "now.json" -> FileType.VERCEL
    ".vercelignore", ".nowignore" -> FileType.VERCEL_IGNORE
    ".vscodeignore" -> FileType.VSCODE_IGNORE
    "vite.config.js", "vite.config.mjs", "vite.config.cjs", "vite.config.ts", "vite.config.cts", "vite.config.mts" -> FileType.VITE
    "vitest.config.ts", "vitest.config.mts", "vitest.config.cts", "vitest.config.js", "vitest.config.mjs", "vitest.config.cjs" -> FileType.VITEST
    "vue.config.js", "vue.config.ts" -> FileType.VUE_CONFIG
    // I think webpack takes up too much space. Maybe it is worth checking with a regex?
    "webpack.js", "webpack.cjs", "webpack.mjs", "webpack.ts", "webpack.cts", "webpack.mts" -> FileType.WEBPACK
    "webpack.base.js", "webpack.base.cjs", "webpack.base.mjs", "webpack.base.ts", "webpack.base.cts", "webpack.base.mts" -> FileType.WEBPACK
    "webpack.config.js", "webpack.config.cjs", "webpack.config.mjs", "webpack.config.ts", "webpack.config.cts", "webpack.config.mts" -> FileType.WEBPACK
    "webpack.common.js", "webpack.common.cjs", "webpack.common.mjs", "webpack.common.ts", "webpack.common.cts", "webpack.common.mts" -> FileType.WEBPACK
    "webpack.config.common.js", "webpack.config.common.cjs", "webpack.config.common.mjs", "webpack.config.common.ts", "webpack.config.common.cts", "webpack.config.common.mts", "webpack.config.common.babel.js", "webpack.config.common.babel.ts" -> FileType.WEBPACK
    "webpack.dev.js", "webpack.dev.cjs", "webpack.dev.mjs", "webpack.dev.ts", "webpack.dev.cts", "webpack.dev.mts", "webpack.development.js", "webpack.development.cjs", "webpack.development.mjs", "webpack.development.ts", "webpack.development.cts", "webpack.development.mts" -> FileType.WEBPACK
    "webpack.config.dev.js", "webpack.config.dev.cjs", "webpack.config.dev.mjs", "webpack.config.dev.ts", "webpack.config.dev.cts", "webpack.config.dev.mts", "webpack.config.dev.babel.js", "webpack.config.dev.babel.ts", "webpack.config.main.js", "webpack.config.renderer.ts" -> FileType.WEBPACK
    "webpack.mix.js", "webpack.mix.cjs", "webpack.mix.mjs", "webpack.mix.ts", "webpack.mix.cts", "webpack.mix.mts" -> FileType.WEBPACK
    "webpack.prod.js", "webpack.prod.cjs", "webpack.prod.mjs", "webpack.prod.ts", "webpack.prod.cts", "webpack.prod.mts" -> FileType.WEBPACK
    "webpack.prod.config.js", "webpack.prod.config.cjs", "webpack.prod.config.mjs", "webpack.prod.config.ts", "webpack.prod.config.cts", "webpack.prod.config.mts" -> FileType.WEBPACK
    "webpack.production.js", "webpack.production.cjs", "webpack.production.mjs", "webpack.production.ts", "webpack.production.cts", "webpack.production.mts" -> FileType.WEBPACK
    "webpack.server.js", "webpack.server.cjs", "webpack.server.mjs", "webpack.server.ts", "webpack.server.cts", "webpack.server.mts" -> FileType.WEBPACK
    "webpack.client.js", "webpack.client.cjs", "webpack.client.mjs", "webpack.client.ts", "webpack.client.cts", "webpack.client.mts" -> FileType.WEBPACK
    "webpack.config.server.js", "webpack.config.server.cjs", "webpack.config.server.mjs", "webpack.config.server.ts", "webpack.config.server.cts", "webpack.config.server.mts" -> FileType.WEBPACK
    "webpack.config.client.js", "webpack.config.client.cjs", "webpack.config.client.mjs", "webpack.config.client.ts", "webpack.config.client.cts", "webpack.config.client.mts" -> FileType.WEBPACK
    "webpack.config.production.babel.js", "webpack.config.production.babel.ts", "webpack.config.prod.babel.js", "webpack.config.prod.babel.cjs", "webpack.config.prod.babel.mjs", "webpack.config.prod.babel.ts", "webpack.config.prod.babel.cts", "webpack.config.prod.babel.mts" -> FileType.WEBPACK
    "webpack.config.prod.js", "webpack.config.prod.cjs", "webpack.config.prod.mjs", "webpack.config.prod.ts", "webpack.config.prod.cts", "webpack.config.prod.mts", "webpack.config.production.js", "webpack.config.production.cjs", "webpack.config.production.mjs", "webpack.config.production.ts", "webpack.config.production.cts", "webpack.config.production.mts" -> FileType.WEBPACK
    "webpack.config.staging.js", "webpack.config.staging.cjs", "webpack.config.staging.mjs", "webpack.config.staging.ts", "webpack.config.staging.cts", "webpack.config.staging.mts" -> FileType.WEBPACK
    "webpack.config.babel.js", "webpack.config.babel.ts", "webpack.config.base.babel.js", "webpack.config.base.babel.ts", "webpack.config.staging.babel.js", "webpack.config.staging.babel.ts" -> FileType.WEBPACK
    "webpack.config.base.js", "webpack.config.base.cjs", "webpack.config.base.mjs", "webpack.config.base.ts", "webpack.config.base.cts", "webpack.config.base.mts" -> FileType.WEBPACK
    "webpack.config.coffee", "webpack.config.test.js", "webpack.config.test.cjs", "webpack.config.test.mjs", "webpack.config.test.ts", "webpack.config.test.cts", "webpack.config.test.mts" -> FileType.WEBPACK
    "webpack.config.vendor.js", "webpack.config.vendor.cjs", "webpack.config.vendor.mjs", "webpack.config.vendor.ts", "webpack.config.vendor.cts", "webpack.config.vendor.mts", "webpack.config.vendor.production.js", "webpack.config.vendor.production.cjs", "webpack.config.vendor.production.mjs", "webpack.config.vendor.production.ts", "webpack.config.vendor.production.cts", "webpack.config.vendor.production.mts" -> FileType.WEBPACK
    "webpack.test.js", "webpack.test.cjs", "webpack.test.mjs", "webpack.test.ts", "webpack.test.cts", "webpack.test.mts" -> FileType.WEBPACK
    "webpack.dist.js", "webpack.dist.cjs", "webpack.dist.mjs", "webpack.dist.ts", "webpack.dist.cts", "webpack.dist.mts" -> FileType.WEBPACK
    "webpackfile.js", "webpackfile.cjs", "webpackfile.mjs", "webpackfile.ts", "webpackfile.cts", "webpackfile.mts" -> FileType.WEBPACK
    "windi.config.js", "windi.config.cjs", "windi.config.ts", "windi.config.cts", "windi.config.json" -> FileType.WINDI
    "wxt.config.js", "wxt.config.ts" -> FileType.WXT
    "xmake.lua" -> FileType.XMAKE
    ".htaccess" -> FileType.XML
    ".yarnrc", ".yarnclean", ".yarn-integrity", "yarn-error.log", ".yarnrc.yml", ".yarnrc.yaml", "yarn.config.cjs" -> FileType.YARN
    "yarn.lock" -> FileType.YARN_LOCK
    else -> when (fileType) {
        "JAVA" -> FileType.JAVA
        "Kotlin" -> FileType.KOTLIN
        "Rust" -> FileType.RUST
        "Python" -> FileType.PYTHON
        "JavaScript" -> FileType.JAVASCRIPT
        "TypeScript" -> FileType.TYPESCRIPT
        "Dockerfile" -> FileType.DOCKER
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
            "json", "jsonc", "tsbuildinfo", "json5", "jsonl", "ndjson", "hjson", "webmanifest" -> FileType.JSON
            "xml", "plist", "xsd", "dtd", "xsl", "xslt", "resx", "iml", "xquery", "tmLanguage", "manifest", "project", "xml.dist", "xml.dist.sample", "dmn", "jrxml" -> FileType.XML
            "eyaml", "yaml", "yml" -> FileType.YAML
            "md", "markdown" -> FileType.MARKDOWN
            "gitignore", "gitconfig", "gitmodules", "gitattributes", "gitkeep", "gitinclude" -> FileType.GIT
            "svelte" -> FileType.SVELTE
            "rb", "rbw" -> FileType.RUBY
            "bash", "sh", "bashrc", "bash_profile", "zshrc", "shrc", "ksh", "awk", "csh", "tcsh", "fish", "zsh" -> FileType.BASH
            "bat", "cmd" -> FileType.BAT
            "sqlite", "sqlite3", "db", "database", "sql", "pdb", "db3", "pks", "pkb", "accdb", "mdb", "pgsql", "postgres", "plpgsql", "psql" -> FileType.DATABASE
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
            "woff", "woff2", "ttf", "eot", "suit", "otf", "bmap", "fnt", "odttf", "ttc", "font", "fonts", "sui", "ntf", "mrf" -> FileType.FONT
            "f", "f77", "f90", "f95", "f03", "f08" -> FileType.FORTRAN
            "g4" -> FileType.ANTLR
            "apib", "apiblueprint" -> FileType.API_BLUEPRINT
            "adoc", "asciidoc", "asc" -> FileType.ASCIIDOC
            "astro" -> FileType.ASTRO
            "d" -> FileType.D
            "dhall", "dhallb" -> FileType.DHALL
            "drawio", "dio" -> FileType.DRAWIO
            "gd" -> FileType.GODOT
            "godot", "tres", "tscn", "gdns", "gdnlib", "gdshader", "gdshaderinc", "gdextension" -> FileType.GODOT_ASSETS
            "gradle" -> FileType.GRADLE
            "graphql", "gql" -> FileType.GRAPHQL
            "haml" -> FileType.HAML
            "hbs", "mustache" -> FileType.HANDLEBARS
            "hx" -> FileType.HAXE
            "jl" -> FileType.JULIA
            "pub", "key", "pem", "gpg", "passwd", "keystore" -> FileType.KEYSTORE
            "tex", "sty", "dtx", "ltx" -> FileType.LATEX
            "less" -> FileType.LESS
            "jinja", "jinja2", "j2", "jinja-html" -> FileType.JINJA
            "lib", "bib" -> FileType.LIB
            "lisp", "lsp", "cl", "fast" -> FileType.LISP
            "log" -> FileType.LOG
            "mk" -> FileType.MAKEFILE
            "mdx" -> FileType.MARKDOWN_MDX
            "marko" -> FileType.MARKO
            "mmd", "mermaid" -> FileType.MERMAID
            "wrap" -> FileType.MESON
            "mjml" -> FileType.MJML
            "xlsx", "xlsm", "xls" -> FileType.EXCEL
            "nf" -> FileType.NEXTFLOW
            "nginx", "nginxconf", "nginxconfig" -> FileType.NGINX
            "nim", "nimble" -> FileType.NIM
            "ninja" -> FileType.NINJA
            "nix" -> FileType.NIX
            "nix-lock" -> FileType.NIX_LOCK
            "njk", "nunjucks" -> FileType.NUNJUCKS
            "nupkg" -> FileType.NUGET
            "ml", "mli", "cmx" -> FileType.OCAML
            "pdf" -> FileType.PDF
            "pcss", "sss" -> FileType.POSTCSS
            "ps1", "psm1", "psd1", "ps1xml", "psc1", "pssc" -> FileType.POWERSHELL
            "p", "pro" -> FileType.PROLOG
            "jade", "pug" -> FileType.PUG
            "pyc", "pyo", "pyd" -> FileType.PYTHON_COMPILED
            "rkt" -> FileType.RACKET
            "cshtml", "vbhtml" -> FileType.RAZOR
            "re", "rei" -> FileType.REASON
            "res" -> FileType.RESCRIPT
            "glsl", "vert", "tesc", "tese", "geom", "frag", "comp", "shader", "vertexshader", "fragmentshader", "geometryshader", "computeshader", "hlsl", "wgsl" -> FileType.SHADER
            "sketch" -> FileType.SKETCH
            "sol" -> FileType.SOLIDITY
            "sublime-project", "sublime-workspace" -> FileType.SUBLIME
            "tauri" -> FileType.TAURI
            "todo" -> FileType.TODO
            "twig" -> FileType.TWIG
            "tw", "twee" -> FileType.TWINE
            "unity" -> FileType.UNITY
            "url" -> FileType.URL
            "v" -> FileType.V
            "vto" -> FileType.VENTO
            "vhd", "sv", "svh" -> FileType.VERILOG
            "vimrc", "gvimrc", "exrc", "vim", "viminfo" -> FileType.VIM
            "csproj", "ruleset", "sln", "suo", "vb", "vbs", "vcxitems", "vcxproj" -> FileType.VISUAL_STUDIO
            "vsixmanifest", "vsix", "code-workplace", "code-workspace", "code-profile", "code-snippets" -> FileType.VSCODE
            "wat", "wasm" -> FileType.WEB_ASSEMBLY
            "windi" -> FileType.WINDI
            "xaml" -> FileType.XAML
            "zig" -> FileType.ZIG
            else -> FileType.FILE.also {
                CatActivity.logger.warn("Unknown file type: $fileType ($extension)")
            }
        }
    }
}
