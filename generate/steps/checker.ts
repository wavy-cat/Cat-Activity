import {Config} from "../config";
import {fileIcons} from "../vscode-icons/src/defaults/fileIcons";

export async function checkConfig(config: Config) {
    for (let icon in config.fileIcons) {
        if (!(icon in fileIcons)) {
            throw `${icon} отсутствует в исходных иконках Catppuccin`
        }

        if (config.fileIcons[icon].extensions === null &&
            config.fileIcons[icon].fileNames === null &&
            config.fileIcons[icon].fileTypes === null) {
            throw `Для ${icon} не указан ни extensions, ни fileNames, ни fileTypes`
        }
    }
}