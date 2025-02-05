import {Config} from "../config";
import {fileIcons} from "../vscode-icons/src/defaults/fileIcons";
import {Logger} from "winston";

export async function checker(config: Config, logger: Logger) {
    for (let icon in config.fileIcons) {
        // Проверка на существование иконки в конфиге Catppuccin
        if (!(icon in fileIcons)) {
            throw `Иконка ${icon} отсутствует в исходных иконках Catppuccin`
        }

        if (config.fileIcons[icon].extensions === null &&
            config.fileIcons[icon].fileNames === null &&
            config.fileIcons[icon].fileTypes === null) {
            throw `Для ${icon} не указан ни extensions, ни fileNames, ни fileTypes`
        }
    }

    // Проверка на существование иконки (fallback) в конфиге Catppuccin
    if (!(config.fallbacks.file.icon.startsWith("_")) && !(config.fallbacks.file.icon in fileIcons)) {
        throw `Fallback иконка ${config.fallbacks.file.icon} отсутствует в исходных иконках Catppuccin`
    }

    // Проверяем какие иконки из списка Catppuccin отсутствуют
    const fileIconsSet = new Set(Object.keys(config.fileIcons))
    let difference = Object.keys(fileIcons).filter(item => !fileIconsSet.has(item))

    // Убираем игнорируемые иконки
    const ignoreIconsSet = new Set(config.ignoreIcons)
    difference = difference.filter(item => !ignoreIconsSet.has(item))

    if (difference.length > 0) {
        logger.warn(`Конфиг сборщика отличается на ${difference.length} иконок от исходного конфига Catppuccin: ${difference.join(", ")}`)
    }
}