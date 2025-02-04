import {Config, loadConfig} from "./config"
import {createLogger, format, transports} from "winston"
import {checker} from "./steps/checker";
import {builder} from "./steps/builder";

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
        await checker(config)
    } catch (e) {
        logger.error(`Проверка завершилась неуспешно: ${e}`)
        process.exit(1)
    }
    logger.info("Проверка успешно завершена")

    logger.info("[Этап 2/4] Cборка ассетов") // TODO: Сделать отключаемым
    const assetsCount = await builder(config, logger)
    logger.info(`Успешно собрано ассетов ${assetsCount}`)

    const end = performance.now()
    const seconds = ((end - start) / 1000).toFixed(2)
    logger.info(`Сборщик завершил работу за ${seconds}с`)
}

main().then()
