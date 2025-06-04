<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Cat Activity Changelog

## [Unreleased]

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

[Unreleased]: https://github.com/wavy-cat/Cat-Activity/compare/v2.1.0...HEAD
[2.1.0]: https://github.com/wavy-cat/Cat-Activity/compare/v2.0.0...v2.1.0
[2.0.0]: https://github.com/wavy-cat/Cat-Activity/commits/v2.0.0
