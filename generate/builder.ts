import type {Config} from "./config"
import {mkdir, readdir, writeFile} from 'fs/promises'
import * as path from "node:path"
import sharp from 'sharp'
import {DistFolder, Palette} from "./consts"
import Listr from "listr";

async function runWithConcurrency<T>(tasks: Array<() => Promise<T>>, cap: number): Promise<T[]> {
  const results: T[] = new Array(tasks.length)
  let nextIndex = 0

  const workers = Array.from({length: Math.min(cap, tasks.length)}, async () => {
    while (true) {
      const current = nextIndex++
      if (current >= tasks.length) return
      results[current] = await tasks[current]!()
    }
  })

  await Promise.all(workers)
  return results
}

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

type IconJob = () => Promise<void>

function collectFileIconJobs(config: Config, color: keyof typeof Palette, sourcePath: string, destPath: string): IconJob[] {
  const icons = Object.entries(config.fileIcons)

  return icons.map(([iconName, iconProperties]) => async () => {
    const image = await processImage(
      path.join(sourcePath, `${iconName}.svg`),
      Palette[color],
      config.renderSettings.canvasSize,
      config.renderSettings.iconSize
    )

    const filename = iconName.toLowerCase()
    await writeFile(path.join(destPath, `${filename}.webp`), image)

    if (iconProperties.altName !== undefined) {
      await writeFile(path.join(destPath, `${iconProperties.altName}.webp`), image)
    }
  })
}

async function collectIdeIconJobs(config: Config, color: keyof typeof Palette, sourcePath: string, destPath: string): Promise<IconJob[]> {
  const files = await readdir(sourcePath)

  return files.map((file) => async () => {
    const image = await processImage(
      path.join(sourcePath, file),
      Palette[color],
      config.renderSettings.canvasSize,
      config.renderSettings.iconSize
    )

    const filename = file.toLowerCase().split('.', 1)[0]
    await writeFile(path.join(destPath, `${filename}.webp`), image)
  })
}

function collectCatActivityJob(config: Config, color: keyof typeof Palette, sourcePath: string, destPaths: string[]): IconJob {
  return async () => {
    const image = await processImage(
      path.join(sourcePath, color == "Latte" ? "icon.svg" : "icon_dark.svg"),
      Palette[color],
      config.renderSettings.canvasSize,
      config.renderSettings.iconSize
    )

    const filename = "cat_activity"
    await Promise.all(destPaths.map(destPath =>
      writeFile(path.join(destPath, `${filename}.webp`), image)
    ))
  }
}

export function builder(config: Config, concurrency_cap: number): Listr<any> {
  return new Listr([
    {
      title: "Preparing folders",
      task: async () => {
        for (let color in Palette) {
          const paletteColor = color as keyof typeof Palette
          await mkdir(path.join(DistFolder, paletteColor), {recursive: true})
          await mkdir(path.join(DistFolder, "IDE", paletteColor), {recursive: true})
          await mkdir(path.join(DistFolder, "IDE", "new", paletteColor), {recursive: true})
        }
      }
    },
    {
      title: `Building icons`,
      task: async () => {
        const jobs: IconJob[] = []

        for (let color in Palette) {
          const paletteColor = color as keyof typeof Palette
          jobs.push(...collectFileIconJobs(
            config,
            paletteColor,
            path.join("generate", "vscode-icons", "icons", color.toLocaleLowerCase()),
            path.join(DistFolder, color)
          ))

          jobs.push(...await collectIdeIconJobs(
            config,
            paletteColor,
            path.join("generate", "ide-icons", "old"),
            path.join(DistFolder, "IDE", color)
          ))

          jobs.push(...await collectIdeIconJobs(
            config,
            paletteColor,
            path.join("generate", "ide-icons", "new"),
            path.join(DistFolder, "IDE", "new", color)
          ))

          jobs.push(collectCatActivityJob(
            config,
            paletteColor,
            path.join("generate", "ide-icons", "cat-activity"),
            [
              path.join(DistFolder, "IDE", color),
              path.join(DistFolder, "IDE", "new", color),
            ]
          ))
        }

        await runWithConcurrency(jobs, concurrency_cap)
      }
    },
  ])
}