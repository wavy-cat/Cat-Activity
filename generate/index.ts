import {loadConfig} from "./config"
import {config_validator} from "./config_validator";
import {builder} from "./builder";
import {code_generation} from "./code_generation";
import {Command} from "commander";
import Listr from "listr";
import {cpus} from "node:os";

const program = new Command()
const logicalCores = cpus().length

program
  .option('--skip-validation', 'Skips the configuration validation step', false)
  .option('--skip-build', 'Skip the asset compilation step', false)
  .option('--skip-generate-code', 'Skips the code generation step', false)
  .option('--concurrency <number>', 'Number of concurrent icon compilations', parseFloat, logicalCores < 1 ? 1 : logicalCores)
  .parse()

const options = program.opts()

const tasks = new Listr([
  {
    title: "Validating configuration",
    skip: () => {
      if (options.skipValidation) {
        return "Skipped due to the --skip-validation argument"
      }
    },
    task: async (ctx) => {
      ctx.config = await loadConfig('builder.json')
      await config_validator(ctx.config)
    }
  },
  {
    title: "Building assets",
    skip: () => {
      if (options.skipBuild) {
        return "Skipped due to the --skip-build argument"
      }
    },
    task: (ctx) => builder(ctx.config, options.concurrency),
  },
  {
    title: "Generating code",
    skip: () => {
      if (options.skipGenerateCode) {
        return "Skipped due to the --skip-generate-code argument"
      }
    },
    task: async (ctx) => code_generation(ctx.config),
  }
])

if (import.meta.main) tasks.run().catch(err => {
  console.error(err);
});
