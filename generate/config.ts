const fs = require('fs').promises
import {z} from "zod"

const snowflakeIdRegex = /^\d{0,64}$/g

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
})

const IDEIconSchema = z.object({
    title: z.string(),
    enumName: z.string().optional(),
    productCodes: z.array(z.string().min(2).max(3)).min(1).max(2),
    applicationId: z.string().regex(snowflakeIdRegex),
})

const FallbackFileIconSchema = z.object({
    title: z.string(),
    icon: z.string(),
    name: z.string()
})

const FallbackIDEIconSchema = z.object({
    title: z.string(),
    applicationId: z.string().regex(snowflakeIdRegex),
    enumName: z.string().optional(),
    icon: z.string(),
})

const FallbacksSchema = z.object({
    file: FallbackFileIconSchema,
    ide: FallbackIDEIconSchema
})

const ConfigSchema = z.object({
    renderSettings: RenderSettingsSchema,
    fileIcons: z.record(FileIconSchema),
    ideIcons: z.record(IDEIconSchema),
    ignoreIcons: z.array(z.string()),
    fallbacks: FallbacksSchema
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
