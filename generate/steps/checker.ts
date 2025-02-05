import {Config} from "../config";
import {fileIcons} from "../vscode-icons/src/defaults/fileIcons";
import {Logger} from "winston";

export async function checker(config: Config, logger: Logger) {
    for (let icon in config.fileIcons) {
        // Проверка на существование иконки в конфиге Catppuccin
        if (!(icon.startsWith("_")) && !(icon in fileIcons)) {
            throw `Иконка ${icon} отсутствует в исходных иконках Catppuccin`
        }
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