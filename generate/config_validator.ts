import type {Config} from "./config"
import {fileIcons} from "./vscode-icons/src/defaults/fileIcons"

export async function config_validator(config: Config) {
  for (let icon in config.fileIcons) {
    // Checking for the existence of an icon in the Catppuccin config
    if (!(icon.startsWith("_")) && !(icon in fileIcons)) {
      throw `The ${icon} icon is missing from the original Catppuccin icons`
    }
  }

  // Check which icons are missing from the Catppuccin list
  const fileIconsSet = new Set(Object.keys(config.fileIcons))
  let difference = Object.keys(fileIcons).filter(item => !fileIconsSet.has(item))

  // Remove ignored icons
  const ignoreIconsSet = new Set(config.ignoreIcons)
  difference = difference.filter(item => !ignoreIconsSet.has(item))

  if (difference.length > 0) {
    throw new Error(`The builder config differs by ${difference.length} of icons from the original Catppuccin config: ${difference.join(", ")}`)
  }
}