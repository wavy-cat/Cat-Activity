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

const ConfigSchema = z.object({
    renderSettings: RenderSettingsSchema,
    fileIcons: z.record(FileIconSchema),
    ignoreIcons: z.array(z.string())
})

export type Config = z.infer<typeof ConfigSchema>

export async function loadConfig(filepath: string): Promise<Config> {
    const data = await fs.readFile(filepath, 'utf-8')
    const parsed = JSON.parse(data)

    const result = await ConfigSchema.safeParseAsync(parsed)
    if (!result.success) {
        throw result.error.errors
            .map(err => `${err.path.join('.')} - ${err.message}`)
            .join('; ')
    }

    return result.data
}
