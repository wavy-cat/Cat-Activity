<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Cat Activity Changelog

## [Unreleased]

## [2.5.2] - 2026-01-07

### Fixed

- The issue was fixed where the IDE would crash during background restart of the Discord client.

### Changed

- The activity rendering code has been optimized. All notifications and actions in the Tools menu have been switched to background execution mode.
- The kotlinx coroutines library was added directly into the plugin for full Kotlin 2 support, so **the size of the plugin has increased significantly**.

## [2.5.1] - 2025-12-26

### Fixed

- The Theme setting in new projects has been changed from Macchiato to Default.

## [2.5.0] - 2025-12-20

### Changed

- Updated settings menu. Now you can override default values (#65).
- Asset storage domain changed.

### Security

- Updated dependencies

## [2.4.0] - 2025-10-13

### Changed

- Updated Catppuccin icons to [v1.26.0](https://github.com/catppuccin/vscode-icons/blob/main/CHANGELOG.md#v1260)

### Security

- Updated plugin dependencies

## [2.3.0] - 2025-08-18

### Changed

- Updated Catppuccin icons to v1.24.0

## [2.2.1] - 2025-08-10

### Fixed

- Fixed incorrect display of PyCharm and WebStorm.

## [2.2.0] - 2025-08-04

### Added

- Added Cat Activity as the IDE icon

### Changed

- Updated Catppuccin icons from v1.21.0 to v1.23.0
- Redesigned IDE icon selection UI
- Optimized internal logic
- Changed icon format to WebP (≈65% smaller). **Please open an issue if this causes any problems!**
- Raised minimum supported IDE version to 2024.3
- Added a "Disable" button to the disconnect notification for turning off Rich Presence

### Security

- Updated plugin dependencies

## [2.1.0] - 2025-06-04

### Added

- Added `%dinnerbone%` placeholder that flips the text.

### Changed

- Updated Catppuccian icons to version 1.21.0.
- Units in the `%fileSize%` placeholder were changed from SI prefixes to binary (KB, MB, GB, TB → KiB, MiB, GiB, TiB).

### Fixed

- Fixed an error in calculating file size in TiB in the `%fileSize%` placeholder.

### Security

- Updated libraries to the latest stable versions to ensure security and performance.

## [2.0.0] - 2025-05-07

### Changed

- Plugin build system updated to version 2
- Introduced new Changelog (which you are reading right now!)
- Actions were slightly optimized

### Removed

- Dropped support for IDE versions below 2024.2

[Unreleased]: https://github.com/wavy-cat/Cat-Activity/compare/v2.5.2...HEAD
[2.5.2]: https://github.com/wavy-cat/Cat-Activity/compare/v2.5.1...v2.5.2
[2.5.1]: https://github.com/wavy-cat/Cat-Activity/compare/v2.5.0...v2.5.1
[2.5.0]: https://github.com/wavy-cat/Cat-Activity/compare/v2.4.0...v2.5.0
[2.4.0]: https://github.com/wavy-cat/Cat-Activity/compare/v2.3.0...v2.4.0
[2.3.0]: https://github.com/wavy-cat/Cat-Activity/compare/v2.2.1...v2.3.0
[2.2.1]: https://github.com/wavy-cat/Cat-Activity/compare/v2.2.0...v2.2.1
[2.2.0]: https://github.com/wavy-cat/Cat-Activity/compare/v2.1.0...v2.2.0
[2.1.0]: https://github.com/wavy-cat/Cat-Activity/compare/v2.0.0...v2.1.0
[2.0.0]: https://github.com/wavy-cat/Cat-Activity/commits/v2.0.0
