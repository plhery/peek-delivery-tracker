import { defineConfig } from '@playwright/test';
export default defineConfig({ testDir: './tests', workers: 1, timeout: 30000, use: { screenshot: 'only-on-failure' }, reporter: 'list' });
