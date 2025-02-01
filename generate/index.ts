import {loadAndProcessConfig, type ProcessedConfig} from "./config"
import * as winston from "winston"

const logger = winston.createLogger({
    level: 'info',
    format: winston.format.combine(winston.format.cli()),
    transports: [
        new winston.transports.Console(),
    ],
})

async function main() {
    try {
        const processedConfig: ProcessedConfig = loadAndProcessConfig('assets_config.json')
        // console.log('Processed Config:', processedConfig);
    } catch (error) {
        const e = error as Error
        logger.crit(`Error when loading config: ${e.message}`)
    }
}

main().then()
