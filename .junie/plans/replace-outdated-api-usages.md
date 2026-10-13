---
sessionId: session-261013-163315-7rhu
---

# Requirements

### Goal
Replace all five reported API usages while preserving settings labels and bindings, localized messages, and activity timestamp caching.

- Replace two `SimpleListCellRenderer.create(...)` calls.
- Replace two `DynamicBundle(String)` constructor calls.
- Replace one `CacheBuilder.expireAfterAccess(long, TimeUnit)` call.
- Keep the IntelliJ IDEA `2026.2.3` dependency and minimum supported build `261` unchanged.
- No dependency upgrades, resource changes, or unrelated refactoring.

# Technical Design

### Existing implementation
The project is a single Kotlin IntelliJ plugin. Both settings screens use Kotlin UI DSL and identical text-only renderer callbacks. The two bundle objects expose matching `message` and `messagePointer` wrappers. `TimeService` stores project/file start timestamps in a Guava cache.

Replacement signatures were verified against the locally resolved IntelliJ `262.10968.63` sources and bundled Guava class.

### Exact replacements
**Settings renderers:** remove `import com.intellij.ui.SimpleListCellRenderer` from both settings files and add:
```kotlin
import com.intellij.ui.dsl.listCellRenderer.textListCellRenderer
```
Replace the renderer assignment in `DefaultsConfigurable.kt:25` with:
```kotlin
renderer = textListCellRenderer<IDEIcon>("") { it.displayName }
```
Replace the renderer assignment in `ProjectConfigurable.kt:56` with:
```kotlin
renderer = textListCellRenderer<IDEIconDefaultable>("") { it.displayName }
```
Keep the existing `applyToComponent` blocks and `bindItem` calls. The public text-only DSL renderer supports platform selection styling; its null-value overload safely displays an empty label when there is no selected item. The `Default` enum entry remains a real item with its existing label.

**Bundle constructors:** replace the declarations at line 10 in the respective files with:
```kotlin
object ConfigBundle : DynamicBundle(ConfigBundle::class.java, BUNDLE) {
```
```kotlin
object ToolsBundle : DynamicBundle(ToolsBundle::class.java, BUNDLE) {
```
The non-obsolete constructor uses an explicit class from the plugin to locate resources. Retain inheritance for this localized migration, preserving the wrapper methods, annotations, bundle paths, and inherited API; delegation refactoring is outside scope.

**Cache expiry:** add `import java.time.Duration` in `TimeService.kt` and replace line 42 with:
```kotlin
.expireAfterAccess(Duration.ofHours(24))
```
Keep `maximumSize(128)`, `build<String, Long>()`, timestamp reads/writes, and explicit invalidation unchanged. Use Java `Duration`, not Kotlin `Duration`, to match Guava’s overload.

### Affected files
All paths below are relative to `src/main/kotlin/cat/wavy/catactivity/`:
- `setting/DefaultsConfigurable.kt`
- `setting/ProjectConfigurable.kt`
- `bundle/ConfigBundle.kt`
- `bundle/ToolsBundle.kt`
- `service/TimeService.kt`

No files need to be added or moved. `build.gradle.kts` requires no change.

# Testing

### Validation
- Build the plugin against the configured target and inspect all five modified files for API, type-inference, and nullability problems.
- Run the existing JUnit suite, including `src/test/kotlin/cat/wavy/catactivity/utils/LazyVariablesTest.kt`.
- Check that both renderers still extract `displayName` and retain their original settings bindings.
- Check that both bundles retain their original resource paths and message-wrapper implementations.
- Check that cache expiry remains access-based for exactly 24 hours, with the same 128-entry bound and invalidation calls.
- Run IntelliJ Plugin Verifier for supported build `261` and the configured `262` target; confirm the five reported usages are absent and replacements introduce no compatibility findings. Report unrelated findings separately.

No new permanent tests or production test hooks are planned for these direct API substitutions.

# Delivery Steps

### ✓ Step 1: Migrate both settings renderers
Both icon selectors use the supported text-only list renderer.

- Replace the renderer calls in `DefaultsConfigurable.kt` and `ProjectConfigurable.kt` with the typed `textListCellRenderer` snippets.
- Replace the obsolete imports and use the empty-string null placeholder.
- Preserve `displayName` extraction and existing item bindings.
- Compile and inspect the changed settings files for renderer type and nullability errors.

### ✓ Step 2: Use explicit-class bundle constructors
Both message bundles use the non-obsolete two-argument constructor.

- Update `ConfigBundle.kt` to pass `ConfigBundle::class.java` and its existing `BUNDLE` constant.
- Update `ToolsBundle.kt` to pass `ToolsBundle::class.java` and its existing `BUNDLE` constant.
- Preserve inheritance, wrapper methods, annotations, and resource paths.
- Compile and inspect both bundle files to confirm constructor compatibility.

### ✓ Step 3: Migrate cache expiry and validate compatibility
Timestamp caching uses the duration overload, and all five replacements are validated together.

- Import `java.time.Duration` in `TimeService.kt` and use `expireAfterAccess(Duration.ofHours(24))`.
- Preserve the cache size, key/value types, access-based expiry, reads/writes, and invalidation behavior.
- Build the plugin, inspect modified files, and run the existing JUnit suite.
- Run Plugin Verifier against builds `261` and `262` and confirm the reported usages are removed without introducing replacement-related incompatibilities.