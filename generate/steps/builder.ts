import {Config} from "../config";
import {Logger} from "winston";
import {mkdir, readdir, writeFile} from 'fs/promises'
import * as path from "node:path"
const sharp = require('sharp');
import {Palette} from "../consts";

async function processImage(file: string, backgroundColor: string, canvasSize: number, iconSize: number): Promise<Buffer<ArrayBufferLike>> {
    // Конвертируем SVG в PNG
    const iconBuffer = await sharp(file)
        .resize(iconSize, iconSize)
        .png()
        .toBuffer()

    // Создаем базовое изображение с нужным фоном
    const baseImage = sharp({
        create: {
            width: canvasSize,
            height: canvasSize,
            channels: 4, // RGBA
            background: backgroundColor
        },
    })

    // Параметры итогового изображения
    const offset = (canvasSize - iconSize) / 2

    // Компонуем иконку поверх базового изображения
    return await baseImage
        .composite([{input: iconBuffer, left: offset, top: offset}])
        .png()
        .toBuffer()
}

// Собирает ассеты иконок файлов.
// Возвращает кол-во обработанных ассетов
async function buildFiles(config: Config, color: string): Promise<number> {
    return 0
}

// Собирает ассеты иконок IDE.
// Возвращает кол-во обработанных ассетов
async function buildIde(config: Config, color: string, sourcePath: string, destPath: string): Promise<number> {
    const files = await readdir(sourcePath)

    for (let file of files) {
        const image = await processImage(
            path.join(sourcePath, file),
            Palette[color],
            config.renderSettings.canvasSize,
            config.renderSettings.iconSize
        )

        const filename = file.toLowerCase().split('.', 1)[0]
        const outputPath = path.join(destPath, `${filename}.png`)
        await writeFile(outputPath, image)
    }

    return files.length
}

// Возвращает кол-во сгенерированных изображений
export async function builder(config: Config, logger: Logger): Promise<number> {
    let funcs: any[] = []

    for (let color in Palette) {
        await mkdir(path.join(".builder-tmp", color), {recursive: true})
        await mkdir(path.join(".builder-tmp", "IDE", color), {recursive: true})
        await mkdir(path.join(".builder-tmp", "IDE", "new", color), {recursive: true})

        funcs.push(
            buildFiles(config, color),
            buildIde(config, color, path.join("generate", "ide-icons", "old"), path.join(".builder-tmp", "IDE", color)),
            buildIde(config, color, path.join("generate", "ide-icons", "new"), path.join(".builder-tmp", "IDE", "new", color))
        )
    }

    const result = await Promise.all(funcs)
    return result.reduce((acc, el) => acc + el, 0)
}
