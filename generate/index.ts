import {Config, loadConfig} from "./config"
import {createLogger, format, transports} from "winston"
import {checkConfig} from "./steps/checker";

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
    logger.info("Загрузка конфигурации сборщика...")
    let config: Config

    try {
        config = await loadConfig('assets_config.json')
    } catch (e) {
        logger.error(`Ошибка при загрузке конфигурации: ${e}`)
        process.exit(1)
    }
    logger.info("Конфигурация успешно загружена")

    logger.info("[Этап 1/4] Проверка соответствия конфига") // TODO: Сделать отключаемым

    try {
        await checkConfig(config)
    } catch (e) {
        logger.error(`Проверка завершилась неуспешно: ${e}`)
        process.exit(1)
    }
    logger.info("Проверка успешно завершена")
}

main().then()
