import { describe, expect, it } from 'vitest';
import { FILTER_LANGUAGES, MAIN_SUBJECT_WORDS, SHIPPING_SUBJECT_WORDS, filterLanguagesFor, gmailFilter, mailServiceOf, subjectWords } from './emailFilter';

describe('the mail filter for shipping emails', () => {
  it('searches the subject for one language\'s words, quoting those of several words', () => {
    expect(gmailFilter(['fr'])).toBe('subject:(expédié OR expédiée OR expédition OR colis OR livraison OR suivi OR acheminement OR distribution OR "en route" OR "en chemin" OR "en transit" OR commande)');
  });

  it('leaves out the words only a delivery confirmation writes: a delivered parcel comes too late to add', () => {
    const every = gmailFilter(FILTER_LANGUAGES).toLowerCase();
    for (const word of ['delivered', 'livré', 'livrée', 'zugestellt', 'geliefert', 'consegnato', 'entregado', 'entregue', 'bezorgd', 'doręczona', 'доставлен', '签收']) {
      expect(every.split(/[ ():"]+/)).not.toContain(word);
    }
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

  it('gives a one-word-at-a-time rule each language\'s main words, every first word before any second', () => {
    expect(subjectWords(['fr'])).toEqual(['colis', 'livraison', 'expédié', 'expédition']);
    expect(subjectWords(['fr', 'en']).slice(0, 4)).toEqual(['shipped', 'colis', 'shipment', 'livraison']);
    // "entrega" is Spanish and Portuguese: named once.
    expect(subjectWords(['es', 'pt']).filter((word) => word === 'entrega')).toHaveLength(1);
    expect(subjectWords([])).toEqual([]);
    for (const language of FILTER_LANGUAGES) {
      const all: readonly string[] = SHIPPING_SUBJECT_WORDS[language];
      for (const word of MAIN_SUBJECT_WORDS[language]) {
        expect(all).toContain(word);
        expect(word).not.toContain(' ');
      }
    }
  });

  it('reads the mail service off an address, and starts any other on Gmail', () => {
    expect(mailServiceOf('someone@gmail.com')).toBe('gmail');
    expect(mailServiceOf('someone@googlemail.com')).toBe('gmail');
    expect(mailServiceOf('Someone@Hotmail.FR')).toBe('outlook');
    expect(mailServiceOf('someone@live.co.uk')).toBe('outlook');
    expect(mailServiceOf('someone@icloud.com')).toBe('icloud');
    expect(mailServiceOf('someone@me.com')).toBe('icloud');
    expect(mailServiceOf('someone@yahoo.co.uk')).toBe('yahoo');
    expect(mailServiceOf('someone@me.example.test')).toBe('gmail');
    expect(mailServiceOf('someone@example.test')).toBe('gmail');
    expect(mailServiceOf('constructor@constructor.test')).toBe('gmail');
    expect(mailServiceOf(undefined)).toBe('gmail');
  });
});
