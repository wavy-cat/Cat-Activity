import * as fs from 'fs';

export interface RenderSettings {
    canvasSize: number;
    iconSize: number;
}

export interface FileIcon {
    title: string;
    altName?: string;
    fileTypes?: string[];
    fileNames?: string[] | null;
    extensions?: string[] | null;
}

export interface IDEIcon {
    title: string;
    enumName?: string;
    productCodes?: string[] | null;
    applicationId: string;
}

export interface Config {
    renderSettings: RenderSettings;
    fileIcons: Record<string, FileIcon>;
    ideIcons: Record<string, IDEIcon>;
}

export interface ProcessedConfig {
    renderSettings: RenderSettings;
    fileIcons: Record<string, FileIcon> & { fallback: FileIcon };
    ideIcons: Record<string, IDEIcon> & { fallback: IDEIcon };
}

// Validate and process the configuration file
export function loadAndProcessConfig(filePath: string): ProcessedConfig {
    const rawData = fs.readFileSync(filePath, 'utf-8');
    const config: Config = JSON.parse(rawData);

    // Process fileIcons
    let fallbackFileIcon: FileIcon | undefined;
    for (const [key, icon] of Object.entries(config.fileIcons)) {
        if (!icon.title) {
            throw new Error(`File icon ${key} is missing a title.`);
        }
        if (!icon.fileTypes && !icon.fileNames && !icon.extensions) {
            fallbackFileIcon = icon;
        }
    }
    if (!fallbackFileIcon) {
        throw new Error('No fallback file icon found.');
    }

    // Process ideIcons
    let fallbackIDEIcon: IDEIcon | undefined;
    const usedProductCodes = new Set<string>();
    for (const [key, icon] of Object.entries(config.ideIcons)) {
        if (!icon.title || !icon.applicationId) {
            throw new Error(`IDE icon ${key} is missing a title or applicationId.`);
        }
        if (icon.productCodes) {
            icon.productCodes.forEach(code => {
                if (usedProductCodes.has(code)) {
                    throw new Error(`Duplicate product code detected: ${code}`);
                }
                usedProductCodes.add(code);
            });
        } else {
            fallbackIDEIcon = icon;
        }
    }
    if (!fallbackIDEIcon) {
        throw new Error('No fallback IDE icon found.');
    }

    return {
        renderSettings: config.renderSettings,
        fileIcons: { ...config.fileIcons, fallback: fallbackFileIcon },
        ideIcons: { ...config.ideIcons, fallback: fallbackIDEIcon },
    };
}
