import { describe, expect, it } from 'vitest';
import { FILTER_LANGUAGES, SHIPPING_SUBJECT_WORDS, filterLanguagesFor, gmailFilter } from './emailFilter';

describe('the mail filter for shipping emails', () => {
  it('searches the subject for one language\'s words, quoting those of several words', () => {
    expect(gmailFilter(['fr'])).toBe('subject:(expédié OR expédiée OR expédition OR colis OR livraison OR livré OR livrée OR suivi OR acheminement OR distribution OR "en route" OR "en chemin" OR "en transit")');
  });

  it('joins the chosen languages in the order they are offered, naming a shared word once', () => {
    const filter = gmailFilter(['pt', 'es']);
    expect(filter.match(/\benviado\b/g)).toHaveLength(1);
    expect(filter.match(/\bentrega\b/g)).toHaveLength(1);
    expect(filter.indexOf('paquete')).toBeLessThan(filter.indexOf('encomenda'));
    expect(gmailFilter(['es', 'pt'])).toBe(filter);
  });

  it('is empty with no language', () => {
    expect(gmailFilter([])).toBe('');
  });

  it('starts with the language Peek shows, the browser\'s and English', () => {
    expect(filterLanguagesFor(['fr-CH', 'de-CH', 'de'])).toEqual(['en', 'de', 'fr']);
    expect(filterLanguagesFor(['pt-BR'])).toEqual(['en', 'pt']);
    expect(filterLanguagesFor(['sv-SE', ''])).toEqual(['en']);
  });

  it('holds only words Gmail takes as written: no operator, bracket or quote, and none twice in a language', () => {
    for (const language of FILTER_LANGUAGES) {
      const words: readonly string[] = SHIPPING_SUBJECT_WORDS[language];
      expect(new Set(words.map((word) => word.toLowerCase())).size).toBe(words.length);
      for (const word of words) {
        expect(word).toMatch(/^[\p{L}\p{M}]+(?: [\p{L}\p{M}]+)*$/u);
        expect(word.split(' ')).not.toContain('OR');
      }
    }
  });

  it('stays within what Gmail\'s filter takes when every language is chosen', () => {
    expect(gmailFilter(FILTER_LANGUAGES).length).toBeLessThan(1_500);
  });
});
