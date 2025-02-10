import {Config} from "../config"
import {mkdir, readdir, writeFile} from 'fs/promises'
import * as path from "node:path"
import * as sharp from 'sharp'
import {Palette} from "../consts"

async function processImage(file: string, backgroundColor: string, canvasSize: number, iconSize: number): Promise<Buffer<ArrayBufferLike>> {
    const iconBuffer = await sharp(file)
        .resize(iconSize, iconSize)
        .png()
        .toBuffer()

    const baseImage = sharp({
        create: {
            width: canvasSize,
            height: canvasSize,
            channels: 4, // RGBA
            background: backgroundColor
        },
    })

    const offset = (canvasSize - iconSize) / 2

    return await baseImage
        .composite([{input: iconBuffer, left: offset, top: offset}])
        .png()
        .toBuffer()
}

async function buildFiles(config: Config, color: string, sourcePath: string, destPath: string): Promise<number> {
    const icons = Object.entries(config.fileIcons)

    for (let [iconName, iconProperties] of icons) {
        const image = await processImage(
            path.join(sourcePath, `${iconName}.svg`),
            Palette[color],
            config.renderSettings.canvasSize,
            config.renderSettings.iconSize
        )

        const filename = iconName.toLowerCase()
        const outputPath = path.join(destPath, `${filename}.png`)
        await writeFile(outputPath, image)

        if (iconProperties.altName !== undefined) {
            await writeFile(path.join(destPath, `${iconProperties.altName}.png`), image)
        }
    }

    return icons.length
}

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

export async function builder(config: Config): Promise<number> {
    let funcs: Promise<number>[] = []

    for (let color in Palette) {
        await mkdir(path.join(".builder-tmp", color), {recursive: true})
        await mkdir(path.join(".builder-tmp", "IDE", color), {recursive: true})
        await mkdir(path.join(".builder-tmp", "IDE", "new", color), {recursive: true})

        funcs.push(
            buildFiles(config, color, path.join("generate", "vscode-icons", "icons", color.toLocaleLowerCase()), path.join(".builder-tmp", color)),
            buildIde(config, color, path.join("generate", "ide-icons", "old"), path.join(".builder-tmp", "IDE", color)),
            buildIde(config, color, path.join("generate", "ide-icons", "new"), path.join(".builder-tmp", "IDE", "new", color))
        )
    }

    const result = await Promise.all(funcs)
    return result.reduce((acc, el) => acc + el, 0)
}
