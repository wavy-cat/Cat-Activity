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

    logger.info("Загрузка конфигурации сборщика...")

    let config: Config
    try {
        config = await loadConfig('builder_config.json')
    } catch (e) {
        logger.error(`Ошибка при загрузке конфигурации: ${e}`)
        process.exit(1)
    }

    logger.info("Конфигурация успешно загружена")

    logger.info("[Этап 1/3] Проверка соответствия конфига")

    try {
        await checker(config, logger)
    } catch (e) {
        logger.error(`Проверка завершилась неуспешно: ${e}`)
        process.exit(1)
    }
    logger.info("Проверка успешно завершена")

    logger.info("[Этап 2/3] Cборка ассетов")

    let assetsCount: number
    try {
        assetsCount = await builder(config)
    } catch (e) {
        logger.error(`Сборка завершилась неуспешно: ${e}`)
        process.exit(1)
    }

    logger.info(`Успешно собрано ${assetsCount} ассетов`)

    logger.info("[Этап 3/3] Генерация кода")

    try {
        await code_generation(config)
    } catch (e) {
        logger.error(`Генерация завершилась неуспешно: ${e}`)
        process.exit(1)
    }

    logger.info("Код успешно сгенерирован!")

    const end = performance.now()
    const seconds = ((end - start) / 1000).toFixed(2)
    logger.info(`Сборщик завершил работу за ${seconds}с`)
}

main().then()
