import {Config, loadConfig} from "./config"
import {createLogger, format, transports} from "winston"
import {checker} from "./steps/checker";
import {builder} from "./steps/builder";
import {code_generation} from "./steps/code_generation";

const logger = createLogger({
    level: 'info',
    format: format.combine(
        format.cli()
    ),
    transports: [
        new transports.Console(),
    ],
})

async function main() {
    const start = performance.now()

    logger.info("Loading the builder configuration...")

    let config: Config
    try {
        config = await loadConfig('builder_config.json')
    } catch (e) {
        logger.error(`Error while loading the configuration: ${e}`)
        process.exit(1)
    }

    logger.info("Configuration successfully loaded!")

    logger.info("[Step 1/3] Configure check")

    try {
        await checker(config, logger)
    } catch (e) {
        logger.error(`The inspection was unsuccessful: ${e}`)
        process.exit(1)
    }
    logger.info("Checking successfully completed!")

    logger.info("[Step 2/3] Asset Building")

    let assetsCount: number
    try {
        assetsCount = await builder(config)
    } catch (e) {
        logger.error(`The building ended unsuccessfully: ${e}`)
        process.exit(1)
    }

    logger.info(`Successfully built ${assetsCount} of assets!`)

    logger.info("[Step 3/3] Code Generation")

    try {
        await code_generation(config)
    } catch (e) {
        logger.error(`The generation ended unsuccessfully: ${e}`)
        process.exit(1)
    }

    logger.info("Code successfully generated!")

    const end = performance.now()
    const seconds = ((end - start) / 1000).toFixed(2)
    logger.info(`Builder finished in ${seconds} sec.`)
}

main().then()
