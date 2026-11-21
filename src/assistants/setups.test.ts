import { readdirSync, readFileSync } from 'node:fs';
import path from 'node:path';
import { describe, expect, it } from 'vitest';
import { ASSISTANT_SETUPS, type SetupLine } from './setups';

const locales = path.resolve(import.meta.dirname, '../../shared/locales');
const catalogs = Object.fromEntries(readdirSync(locales).filter((file) => file.endsWith('.json'))
  .map((file) => [file.replace(/\.json$/, ''), JSON.parse(readFileSync(path.join(locales, file), 'utf8')) as Record<string, string>]));

const lines = ASSISTANT_SETUPS.flatMap(({ lead, who, steps, fallback }): SetupLine[] => [lead, who, ...steps, ...(fallback ? [fallback] : [])]);

describe('the assistants’ setups (shared/assistant-setups.json)', () => {
  it('name a copy key every language has, with a label for each of its {{…}}', () => {
    for (const [key, labels = {}] of lines) {
      for (const [language, catalog] of Object.entries(catalogs)) {
        const text = catalog[key];
        expect(text, `${language}: ${key}`).toBeTypeOf('string');
        const names = [...new Set([...text!.matchAll(/\{\{(\w+)\}\}/g)].map((match) => match[1]))].sort();
        expect(names, `${language}: ${key}`).toEqual(Object.keys(labels).sort());
      }
    }
  });

  it('give each assistant one thing to do, under its name', () => {
    for (const { name, lead } of ASSISTANT_SETUPS) expect(lead?.[0], name).toMatch(/\.lead$/);
  });

  it('give each label a name, a page or a command', () => {
    for (const [, labels = {}] of lines) {
      for (const label of Object.values(labels)) {
        if (typeof label === 'string') expect(label.trim()).not.toBe('');
        else if ('href' in label) expect(new URL(label.href).protocol).toBe('https:');
        else expect(label.command).toMatch(/^\S/);
      }
    }
  });
});
