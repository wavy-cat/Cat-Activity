const fs = require('fs').promises
import {z} from "zod"

const RenderSettingsSchema = z.object({
    canvasSize: z.number(),
    iconSize: z.number(),
})

const FileIconSchema = z.object({
    title: z.string(),
    altName: z.string().optional(),
    fileTypes: z.array(z.string()).nullable().optional(),
    fileNames: z.array(z.string()).nullable().optional(),
    extensions: z.array(z.string()).nullable().optional(),
    enumName: z.string().min(1).optional()
})

const FileIconsSchema = z.record(
    z.string(),
    z.union([
        FileIconSchema,
        z.string().transform(str => ({
            title: str,
            altName: undefined,
            fileTypes: undefined,
            fileNames: undefined,
            extensions: undefined,
            enumName: undefined
        }))
    ])
)

const ConfigSchema = z.object({
    renderSettings: RenderSettingsSchema,
    fileIcons: FileIconsSchema,
    ignoreIcons: z.array(z.string())
})

export type Config = z.infer<typeof ConfigSchema>

export async function loadConfig(filepath: string): Promise<Config> {
    const data = await fs.readFile(filepath, 'utf-8')
    const parsed = JSON.parse(data)

    const result = await ConfigSchema.safeParseAsync(parsed)
    if (!result.success) {
        throw z.treeifyError(result.error)
    }

    return result.data
}
