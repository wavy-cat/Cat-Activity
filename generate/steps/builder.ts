import {Config} from "../config"
import {mkdir, readdir, writeFile} from 'fs/promises'
import * as path from "node:path"
import sharp from 'sharp'
import {DistFolder, Palette} from "../consts"

async function processImage(file: string, backgroundColor: string, canvasSize: number, iconSize: number): Promise<Buffer<ArrayBufferLike>> {
    const iconBuffer = await sharp(file)
        .resize(iconSize, iconSize)
        .webp({preset: "icon", lossless: true})
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
        .webp({preset: "icon", lossless: true})
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
        const outputPath = path.join(destPath, `${filename}.webp`)
        await writeFile(outputPath, image)

        if (iconProperties.altName !== undefined) {
            await writeFile(path.join(destPath, `${iconProperties.altName}.webp`), image)
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
        const outputPath = path.join(destPath, `${filename}.webp`)
        await writeFile(outputPath, image)
    }

    return files.length
}

async function buildCA(config: Config, color: string, sourcePath: string, ...destPaths: string[]): Promise<number> {
    const image = await processImage(
        path.join(sourcePath, color == "Latte" ? "icon.svg" : "icon_dark.svg"),
        Palette[color],
        config.renderSettings.canvasSize,
        config.renderSettings.iconSize
    )

    const filename = "cat_activity"

    for (let destPath of destPaths) {
        await writeFile(path.join(destPath, `${filename}.webp`), image)
    }

    return destPaths.length
}

export async function builder(config: Config): Promise<number> {
    let funcs: Promise<number>[] = []

    for (let color in Palette) {
        await mkdir(path.join(DistFolder, color), {recursive: true})
        await mkdir(path.join(DistFolder, "IDE", color), {recursive: true})
        await mkdir(path.join(DistFolder, "IDE", "new", color), {recursive: true})

        funcs.push(
            buildFiles(
                config,
                color,
                path.join("generate", "vscode-icons", "icons", color.toLocaleLowerCase()),
                path.join(DistFolder, color)
            ),
            buildIde(
                config,
                color,
                path.join("generate", "ide-icons", "old"),
                path.join(DistFolder, "IDE", color)
            ),
            buildIde(
                config,
                color,
                path.join("generate", "ide-icons", "new"),
                path.join(DistFolder, "IDE", "new", color)
            ),
            buildCA(
                config,
                color,
                path.join("generate", "ide-icons", "cat-activity"),
                path.join(DistFolder, "IDE", color),
                path.join(DistFolder, "IDE", "new", color)
            )
        )
    }

    const result = await Promise.all(funcs)
    return result.reduce((acc, el) => acc + el, 0)
}
