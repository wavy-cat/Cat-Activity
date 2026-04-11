# Repository Guidelines

## Project Structure & Module Organization
`src/main/kotlin/cat/wavy/catactivity` contains the JetBrains plugin code, grouped by responsibility: `action`, `listener`, `render`, `service`, `setting`, `bundle`, and `types`. Plugin metadata and localized resources live in `src/main/resources/META-INF` and `src/main/resources/messages`.

`generate/` contains the TypeScript asset builder and code generator used for icon assets. The vendored `generate/vscode-icons/` subtree provides upstream icon sources. CI workflows are in `.github/workflows/`; treat `build/` and generated asset outputs as disposable.

## Build, Test, and Development Commands
Use JDK 21 for Gradle tasks and Node 24 with `pnpm` 10 for the asset pipeline.

- `./gradlew buildPlugin` builds the IntelliJ plugin ZIP.
- `./gradlew check` runs tests and Kover coverage reporting.
- `./gradlew verifyPlugin` runs plugin verification against recommended IDEs.
- `./gradlew runIde` launches a local sandbox IDE for manual testing.
- `./gradlew runIdeForUiTests` starts the IDE with the robot server enabled.
- `pnpm install --frozen-lockfile` installs builder dependencies.
- `pnpm run build` compiles the TypeScript builder to `dist/`.
- `pnpm run dev` runs `generate/index.ts` directly for asset generation work.

## Coding Style & Naming Conventions
Follow Kotlin conventions already used in `src/main`: 4-space indentation, PascalCase for classes and enums, lowerCamelCase for functions and properties, and package names under `cat.wavy.catactivity`. Keep IntelliJ-facing logic small and grouped by feature package.

For TypeScript in `generate/`, keep 4-space indentation and existing naming: camelCase locals, PascalCase types, snake_case filenames only where already established (for example `code_generation.ts`). No dedicated formatter config is present, so preserve the surrounding style exactly.

## Testing Guidelines
The Gradle build is wired for JUnit 4, IntelliJ Platform test framework support, Qodana, and Kover. Name Kotlin tests `*Test.kt` and run `./gradlew check` before opening a PR.

## Commit & Pull Request Guidelines
Recent history follows short conventional-style subjects such as `chore: ...` and `refactor: ...`. Use an imperative subject, keep it scoped, and reference issue numbers when relevant.

PRs should include a concise summary, linked issue or bug ID, and screenshots or GIFs for UI/settings changes. If the change touches `generate/` or icons, mention whether generated assets or code were regenerated.
