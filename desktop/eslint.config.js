import js from '@eslint/js';
import tseslint from 'typescript-eslint';

export default tseslint.config(
  { ignores: ['build/**', 'dist/**', 'node_modules/**', 'test-results/**', 'playwright-report/**'] },
  js.configs.recommended,
  ...tseslint.configs.recommended.map((config) => ({ ...config, files: ['**/*.ts'] })),
  { languageOptions: { globals: { process: 'readonly', console: 'readonly', __dirname: 'readonly', require: 'readonly', module: 'readonly', URL: 'readonly', document: 'readonly', window: 'readonly', HTMLElement: 'readonly', HTMLInputElement: 'readonly', HTMLFormElement: 'readonly', HTMLButtonElement: 'readonly', setTimeout: 'readonly', clearTimeout: 'readonly' } } },
);
