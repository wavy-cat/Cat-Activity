import { loadAndProcessConfig, type ProcessedConfig } from 'config';

try {
    const processedConfig: ProcessedConfig = loadAndProcessConfig('assets_config.json');
    console.log('Processed Config:', processedConfig);
} catch (error) {
    if (error instanceof Error) {
        console.error('Error processing config:', error.message);
    } else {
        console.error('Unexpected error:', error);
    }
}
