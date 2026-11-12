import en from '../shared/locales/en.json';
import { readFileSync } from 'node:fs';
import { resolve } from 'node:path';
import { StrictMode } from 'react';
import de from '../shared/locales/de.json';
import fr from '../shared/locales/fr.json';
import itMessages from '../shared/locales/it.json';
import trackingMessages from '../shared/tracking-messages.json';
import { act, render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, beforeAll, beforeEach, describe, expect, it, vi } from 'vitest';
import {
  detectLocale,
  I18nProvider,
  LanguageControl,
  loadMessages,
  localizedExpectedDelivery,
  localizedDeliveryDate,
  localizedDatePhrase,
  localizedRelativeTime,
  stageLabel,
  type Translate,
  useI18n,
  translate,
  localizedEventDescription,
  localizedDeliveryWindow,
  localizedDeliveryEstimate,
  trackingFailureMessage,
  SUPPORTED_LOCALES,
  type Locale,
  type MessageKey,
} from './i18n';
import { documentLanguage } from './lib/locale';
import { deliveryWindowOpen, languageTags } from './lib/messages';

function TranslationProbe() {
  const { t } = useI18n();
  return (
    <>
      <p>{t('app.emptyTitle')}</p>
      <p>{stageLabel(t, 'out_for_delivery')}</p>
    </>
  );
}

describe('localization', () => {
  beforeAll(async () => { await Promise.all(SUPPORTED_LOCALES.map(loadMessages)); });

  it('preserves literal replacement tokens in provider display names in every locale', () => {
    const provider = 'ACME $& $$ SSO';
    const keys: MessageKey[] = ['auth.oidc', 'auth.oidcOpening', 'auth.oidcFailed'];
    for (const locale of SUPPORTED_LOCALES) {
      for (const key of keys) {
        const message = translate(locale, key, { provider });
        expect(message).toContain(provider);
        expect(message).not.toContain('{{provider}}');
      }
    }
  });

  it('restores the saved language before persisting during StrictMode effect replay', async () => {
    window.localStorage.setItem('deliveryTrackerLocale', 'fr');
    render(<StrictMode><I18nProvider><TranslationProbe /></I18nProvider></StrictMode>);
    await screen.findByText('Pas encore de colis');
    expect(window.localStorage.getItem('deliveryTrackerLocale')).toBe('fr');
  });
  it('writes the sign-in email in every app language', () => {
    const email = readFileSync(resolve(process.cwd(), 'public/auth-emails/magic-link.html'), 'utf8');
    for (const locale of SUPPORTED_LOCALES.filter((locale) => locale !== 'en')) {
      expect(email).toContain(`if eq .Data.locale "${locale}" -}}`);
      expect(email).toContain(`$lang = "${documentLanguage(locale)}"`);
    }
    expect(email.match(/\{\{-? ?if /g)).toHaveLength(1);
    expect(email.match(/\{\{-? ?end /g)).toHaveLength(1);
  });
  it('translates every shared tracking message in every locale and preserves original scan notes', () => {
    for (const locale of SUPPORTED_LOCALES) {
      const t: Translate = (key, variables) => translate(locale, key, variables);
      for (const [description, message] of Object.entries(trackingMessages.events)) {
        expect(localizedEventDescription(description, t)).toBe(t(message.key as MessageKey, message.variables));
        expect(localizedEventDescription(description, t)).not.toMatch(/\{\{/);
      }
      for (const kind of Object.keys(trackingMessages.failures)) expect(trackingFailureMessage(`carrier:${kind}`, t)).toBeTruthy();
      expect(localizedEventDescription('Scanned by Sophie at dock 12', t)).toBe('Scanned by Sophie at dock 12');
      expect(localizedEventDescription('toString', t)).toBe('toString');
      expect(trackingFailureMessage('HTTP 403 internal diagnostic', t)).toBe(t('detail.trackingUnavailable'));
      expect(trackingFailureMessage('carrier:new_kind', t)).toBe(t('detail.trackingUnavailable'));
    }
    expect(localizedEventDescription('Shipment exception', key => translate('fr', key))).toBe('Problème avec le colis');
  });

  it('shows delivery windows and ignores missing, reversed or invalid starts', () => {
    const now = new Date(2026, 8, 9, 12).getTime();
    const t: Translate = (key, variables) => translate('en', key, variables);
    expect(localizedDeliveryWindow('2026-09-10', '2026-09-11', t, 'en-CH', now)).toBe('tomorrow – Fri 11 Sep');
    for (const from of [undefined, 'invalid', '2026-09-12', '2026-09-11']) {
      expect(localizedDeliveryWindow(from, '2026-09-11', t, 'en-CH', now)).toBe('Fri 11 Sep');
    }
  });

  it('gives a window of days whole until it begins, then by its last day', () => {
    const now = new Date(2026, 10, 4, 20, 45).getTime();
    const t: Translate = (key, variables) => translate('en', key, variables);
    // Not begun: both ends, as the carrier gives them.
    expect(localizedDeliveryEstimate('2026-11-05', '2026-11-06', t, 'en-CH', now)).toBe('tomorrow – Fri 6 Nov');
    // Begun today or before: it may still come any day until its last.
    expect(localizedDeliveryEstimate('2026-11-04', '2026-11-06', t, 'en-CH', now)).toBe('by Fri 6 Nov');
    expect(localizedDeliveryEstimate('2026-10-23', '2026-11-06', t, 'en-CH', now)).toBe('by Fri 6 Nov');
    expect(localizedDeliveryEstimate('2026-11-03', '2026-11-05', t, 'en-CH', now)).toBe('by tomorrow');
    expect(localizedDeliveryEstimate('2026-11-02', '2026-11-04', t, 'en-CH', now)).toBe('today');
    // One day, with or without its hours, reads as before.
    expect(localizedDeliveryEstimate(undefined, '2026-11-04', t, 'en-CH', now)).toBe('today');
    // Its hours on the device's clock, whatever zone the tests run in.
    const at = (hour: number) => new Date(2026, 10, 4, hour, 5).toISOString();
    expect(localizedDeliveryEstimate(at(13), at(16), t, 'en-CH', now)).toBe('today, 13:05–16:05');
    expect(deliveryWindowOpen('2026-11-04', '2026-11-06', now)).toBe(true);
    expect(deliveryWindowOpen('2026-11-05', '2026-11-06', now)).toBe(false);
    expect(deliveryWindowOpen(undefined, '2026-11-06', now)).toBe(false);
    const by: Record<string, [string, string]> = {
      de: ['bis Fr 6. Nov', 'bis morgen'], fr: ['d’ici ven 6 nov', 'd’ici demain'], it: ['entro ven 6 nov', 'entro domani'],
      es: ['para el vie 6 nov', 'para mañana'], pt: ['até sex 6 nov', 'até amanhã'], 'pt-br': ['até sex 6 nov', 'até amanhã'],
      pl: ['do pt 6 lis', 'do jutra'], ru: ['до пт 6 нояб', 'до завтра'], zh: ['11月6日周五前', '明天前'],
    };
    for (const [locale, [date, tomorrow]] of Object.entries(by)) {
      const words: Translate = (key, variables) => translate(locale as Locale, key, variables);
      expect(localizedDeliveryEstimate('2026-11-04', '2026-11-06', words, languageTags[locale as Locale], now)).toBe(date);
      expect(localizedDeliveryEstimate('2026-11-04', '2026-11-05', words, languageTags[locale as Locale], now)).toBe(tomorrow);
    }
  });

  it('dates the first day of a window once it has passed', () => {
    const now = new Date(2026, 10, 5, 9).getTime();
    const t: Translate = (key, variables) => translate('en', key, variables);
    expect(localizedDeliveryWindow('2026-11-04', '2026-11-06', t, 'en-CH', now)).toBe('Wed 4 Nov – Fri 6 Nov');
    expect(localizedDeliveryWindow('2026-11-05', '2026-11-07', t, 'en-CH', now)).toBe('today – Sat 7 Nov');
  });

  it('names the day of a window within one day once', () => {
    const now = new Date(2026, 8, 9, 12).getTime();
    const at = (day: number, hour: number) => new Date(2026, 8, day, hour).toISOString();
    const t: Translate = (key, variables) => translate('en', key, variables);
    expect(localizedDeliveryWindow(at(11, 10), at(11, 14), t, 'en-CH', now)).toBe('Fri 11 Sep, 10:00–14:00');
    expect(localizedDeliveryWindow(at(10, 8), at(10, 18), t, 'en-CH', now)).toBe('tomorrow, 08:00–18:00');
    expect(localizedDeliveryWindow(at(11, 10), at(11, 14), (key) => translate('zh', key), 'zh-CN', now)).toBe('9月11日周五 10:00–14:00');
    // Across midnight, or from a day to a time, both ends keep their day.
    expect(localizedDeliveryWindow(at(10, 22), at(11, 2), t, 'en-CH', now)).toBe('tomorrow, 22:00 – Fri 11 Sep, 02:00');
    expect(localizedDeliveryWindow('2026-09-11', at(11, 14), t, 'en-CH', now)).toBe('Fri 11 Sep – Fri 11 Sep, 14:00');
  });
  it('detects all supported Swiss languages and falls back to English', () => {
    expect(detectLocale(['de-CH'])).toBe('de');
    expect(detectLocale(['rm-CH', 'it-CH'])).toBe('it');
    expect(detectLocale(['es-ES'])).toBe('es');
    expect(detectLocale(['pt-PT'])).toBe('pt');
    expect(detectLocale(['pl-PL'])).toBe('pl');
    expect(detectLocale(['pt-BR'])).toBe('pt-br');
    expect(detectLocale(['ru-RU'])).toBe('ru');
    expect(detectLocale(['zh-CN'])).toBe('zh');
    expect(detectLocale(['zh-TW', 'fr-CH'])).toBe('fr');
    expect(detectLocale(['nl-NL'])).toBe('en');
  });

  it('persists an explicit language and updates the document language', async () => {
    window.localStorage.setItem('deliveryTrackerLocale', 'de');
    const user = userEvent.setup();
    render(
      <I18nProvider>
        <LanguageControl />
        <TranslationProbe />
      </I18nProvider>,
    );

    await waitFor(() => {
      expect(screen.getByLabelText('Sprache')).toHaveValue('de');
      expect(screen.getByText('Noch keine Pakete')).toBeInTheDocument();
      expect(screen.getByText('In Zustellung')).toBeInTheDocument();
    });

    await user.selectOptions(screen.getByLabelText('Sprache'), 'fr');
    expect(screen.getByText('Pas encore de colis')).toBeInTheDocument();
    expect(screen.getByText('En livraison')).toBeInTheDocument();
    await waitFor(() => {
      expect(document.documentElement.lang).toBe('fr');
      expect(window.localStorage.getItem('deliveryTrackerLocale')).toBe('fr');
    });

    // The page says its Portuguese is European; the saved choice is still `pt`.
    await user.selectOptions(screen.getByLabelText('Langue'), 'pt');
    await waitFor(() => {
      expect(document.documentElement.lang).toBe('pt-PT');
      expect(window.localStorage.getItem('deliveryTrackerLocale')).toBe('pt');
      expect(document.cookie).toContain('sdt.locale=pt');
    });
  });

  it('links the installed app’s manifest in the language the page shows', async () => {
    // The page as the server wrote it, in the language of its request.
    const link = Object.assign(document.createElement('link'), { rel: 'manifest' });
    link.setAttribute('href', '/fr/manifest.webmanifest');
    document.head.append(link);
    try {
      window.localStorage.setItem('deliveryTrackerLocale', 'de');
      const user = userEvent.setup();
      render(<I18nProvider initialLocale="fr" initialMessages={fr}><LanguageControl /></I18nProvider>);
      await waitFor(() => expect(link.getAttribute('href')).toBe('/de/manifest.webmanifest'));
      await user.selectOptions(screen.getByLabelText('Sprache'), 'en');
      await waitFor(() => expect(link.getAttribute('href')).toBe('/manifest.webmanifest'));
    } finally {
      link.remove();
    }
  });

  it('starts in the server language and applies a loaded saved choice before paint', () => {
    const first = render(<I18nProvider initialLocale="fr" initialMessages={fr}><TranslationProbe /></I18nProvider>);
    expect(screen.getByText('Pas encore de colis')).toBeInTheDocument();
    first.unmount();

    window.localStorage.setItem('deliveryTrackerLocale', 'de');
    render(<I18nProvider initialLocale="fr" initialMessages={fr}><TranslationProbe /></I18nProvider>);
    // No waiting: the saved language is in place when rendering returns.
    expect(screen.getByText('Noch keine Pakete')).toBeInTheDocument();
    expect(document.cookie).toContain('sdt.locale=de');
  });

  it('keeps the chosen language for server-rendered pages', async () => {
    const user = userEvent.setup();
    render(<I18nProvider><LanguageControl /></I18nProvider>);
    await user.selectOptions(screen.getByLabelText('Language'), 'it');
    expect(document.cookie).toContain('sdt.locale=it');
    expect(window.localStorage.getItem('deliveryTrackerLocale')).toBe('it');
  });

  describe('at a language address', () => {
    const forgetCookie = () => { document.cookie = 'sdt.locale=; Path=/; Max-Age=0'; };
    beforeEach(forgetCookie);
    afterEach(() => { forgetCookie(); history.replaceState(null, '', '/'); });
    const landing = (englishAddress?: () => string) => render(
      <I18nProvider initialLocale="de" initialMessages={de}><LanguageControl englishAddress={englishAddress} /><TranslationProbe /></I18nProvider>,
    );

    it('shows the address’s language whatever was saved, and saves nothing of the visit', () => {
      window.localStorage.setItem('deliveryTrackerLocale', 'fr');
      history.replaceState(null, '', '/de');
      landing();
      // No waiting: the page is in German when rendering returns, and stays so.
      expect(screen.getByText('Noch keine Pakete')).toBeInTheDocument();
      expect(screen.getByLabelText('Sprache')).toHaveValue('de');
      expect(document.documentElement.lang).toBe('de');
      // The saved choice is still French, for `/` and every other address.
      expect(window.localStorage.getItem('deliveryTrackerLocale')).toBe('fr');
      expect(document.cookie).toContain('sdt.locale=fr');
      expect(document.cookie).not.toContain('sdt.locale=de');
    });

    it('leaves a browser that chose nothing with nothing chosen', () => {
      history.replaceState(null, '', '/de');
      landing();
      expect(screen.getByText('Noch keine Pakete')).toBeInTheDocument();
      expect(window.localStorage.getItem('deliveryTrackerLocale')).toBeNull();
      expect(document.cookie).not.toContain('sdt.locale');
    });

    it('saves a language chosen there and moves the address to that language’s, in place', async () => {
      history.replaceState({ kept: 1 }, '', '/de?utm_source=test#who');
      const heard = vi.fn();
      window.addEventListener('popstate', heard);
      const user = userEvent.setup();
      landing();
      const length = history.length;

      await user.selectOptions(screen.getByLabelText('Sprache'), 'fr');
      expect(screen.getByText('Pas encore de colis')).toBeInTheDocument();
      expect(location.pathname + location.search + location.hash).toBe('/fr?utm_source=test#who');
      expect(window.localStorage.getItem('deliveryTrackerLocale')).toBe('fr');
      expect(document.cookie).toContain('sdt.locale=fr');
      // The page stays: no step is added to the history, and what follows the address hears of the move.
      expect(history.length).toBe(length);
      expect(history.state).toEqual({ kept: 1 });
      expect(heard).toHaveBeenCalledTimes(1);

      // English lives at `/`.
      await user.selectOptions(screen.getByLabelText('Langue'), 'en');
      expect(screen.getByText('No parcels yet')).toBeInTheDocument();
      expect(location.pathname + location.search + location.hash).toBe('/?utm_source=test#who');
      expect(window.localStorage.getItem('deliveryTrackerLocale')).toBe('en');
      expect(history.length).toBe(length);
      expect(heard).toHaveBeenCalledTimes(2);

      // `/` is no language's address: a choice made there leaves it where it is, as before.
      await user.selectOptions(screen.getByLabelText('Language'), 'it');
      expect(location.pathname).toBe('/');
      expect(heard).toHaveBeenCalledTimes(2);
      window.removeEventListener('popstate', heard);
    });

    it('leads English where the landing is for a reader whose `/` is something else', async () => {
      history.replaceState(null, '', '/de');
      const user = userEvent.setup();
      landing(() => '/home');
      await user.selectOptions(screen.getByLabelText('Sprache'), 'en');
      expect(location.pathname).toBe('/home');
      expect(screen.getByText('No parcels yet')).toBeInTheDocument();
    });

    it('follows Back to a language address, and keeps the language on screen when the address is left', async () => {
      window.localStorage.setItem('deliveryTrackerLocale', 'fr');
      history.replaceState(null, '', '/de');
      landing();
      expect(screen.getByText('Noch keine Pakete')).toBeInTheDocument();
      // A parcel opened from the landing is read in the landing's language.
      act(() => { history.pushState(null, '', '/sample'); window.dispatchEvent(new PopStateEvent('popstate')); });
      expect(screen.getByText('Noch keine Pakete')).toBeInTheDocument();
      act(() => { history.replaceState(null, '', '/it'); window.dispatchEvent(new PopStateEvent('popstate')); });
      expect(await screen.findByText(itMessages['app.emptyTitle'])).toBeInTheDocument();
      expect(window.localStorage.getItem('deliveryTrackerLocale')).toBe('fr');
    });

    it('shows in its menu the language an address brought, after a choice made earlier', async () => {
      history.replaceState(null, '', '/de');
      const user = userEvent.setup();
      landing();
      await user.selectOptions(screen.getByLabelText('Sprache'), 'fr');
      expect(screen.getByLabelText('Langue')).toHaveValue('fr');
      act(() => { history.replaceState(null, '', '/de'); window.dispatchEvent(new PopStateEvent('popstate')); });
      expect(screen.getByLabelText('Sprache')).toHaveValue('de');
    });

    it('leaves the address alone everywhere else', async () => {
      history.replaceState(null, '', '/home');
      const user = userEvent.setup();
      render(<I18nProvider><LanguageControl englishAddress={() => '/home'} /><TranslationProbe /></I18nProvider>);
      await user.selectOptions(screen.getByLabelText('Language'), 'de');
      expect(screen.getByText('Noch keine Pakete')).toBeInTheDocument();
      expect(location.pathname).toBe('/home');
    });
  });

  it('preserves precise carrier estimates in the recipient timezone', () => {
    const t = ((key: string) => key) as Translate;
    const timestamp = new Date('2026-09-07T12:30:00Z');
    const time = new Intl.DateTimeFormat('fr-CH', { hour: '2-digit', minute: '2-digit', hourCycle: 'h23' }).format(timestamp);
    expect(localizedExpectedDelivery(timestamp.toISOString(), t, 'fr-CH', timestamp.getTime()))
      .toBe(`time.today, ${time}`);
  });

  it('uses the chosen clock for delivery windows and keeps date only estimates as dates', () => {
    const t = ((key: string) => key) as Translate;
    const now = new Date(2026, 10, 12, 8).getTime();
    const range = '2026-11-12 00:15–15:30';

    expect(localizedExpectedDelivery(range, t, 'en-US', now)).toBe('time.today, 00:15–15:30');
    expect(localizedExpectedDelivery(range, t, 'en-US', now, false)).toBe('time.today, 12:15 AM–3:30 PM');
    expect(localizedDeliveryWindow('2026-11-12T11:30:00', '2026-11-12T12:30:00', t, 'en-US', now, false))
      .toBe('time.today, 11:30 AM–12:30 PM');
    expect(localizedExpectedDelivery('2026-11-12 23:00–24:00', t, 'en-US', now, false))
      .toBe('time.today, 11:00 PM–12:00 AM');
    expect(localizedExpectedDelivery('2026-11-12', t, 'en-US', now, false)).toBe('time.today');
  });

  it('formats calendar dates with the selected language tag', () => {
    const t = ((key: string) => key) as Translate;
    const now = new Date(2026, 0, 1, 12).getTime();
    const update = new Date(2025, 11, 20, 12);

    expect(localizedExpectedDelivery('2026-12-31', t, 'en-CH', now)).toBe(
      'Thu 31 Dec',
    );
    expect(localizedRelativeTime(update.toISOString(), t, 'it-CH', now)).toBe(
      'sab 20 dic',
    );
  });
});

describe('relative delivery dates', () => {
  it.each([
    ['es', ['ayer', 'hoy', 'mañana'], '1 sello', '2 sellos'],
    ['pt', ['ontem', 'hoje', 'amanhã'], '1 selo', '2 selos'],
    ['pt-br', ['ontem', 'hoje', 'amanhã'], '1 selo', '2 selos'],
    ['pl', ['wczoraj', 'dziś', 'jutro'], '1 znaczek', '2 znaczki'],
    ['ru', ['вчера', 'сегодня', 'завтра'], '1 марка', '2 марки'],
    ['zh', ['昨天', '今天', '明天'], '1 枚邮票', '2 枚邮票'],
  ] as const)('supports dates and counts in %s', (locale, days, one, two) => {
    const t: Translate = (key, variables) => translate(locale, key, variables);
    const now = new Date(2026, 8, 9, 12).getTime();
    for (const [index, day] of [8, 9, 10].entries()) {
      const date = localizedDeliveryDate(new Date(2026, 8, day), t, locale, now);
      expect(date).toBe(days[index]);
      expect(localizedDatePhrase(date, t)).toBe(date);
    }
    expect(t('friends.stampCount', { count: 1 })).toBe(one);
    expect(t('friends.stampCount', { count: 2 })).toBe(two);
  });

  it.each([0, 1, 2, 4, 5, 12, 14, 21, 22, 25, 101, 102, 112])('declines Polish quantities for %i', (count) => {
    const few = [2, 4, 22, 102].includes(count);
    expect(translate('pl', 'friends.stampCount', { count }))
      .toBe(`${count} ${count === 1 ? 'znaczek' : few ? 'znaczki' : 'znaczków'}`);
    expect(translate('pl', count === 1 ? 'detail.updateCount.one' : 'detail.updateCount.many', { count }))
      .toBe(`${count} ${count === 1 ? 'aktualizacja' : few ? 'aktualizacje' : 'aktualizacji'}`);
    expect(translate('pl', count === 1 ? 'passport.parcels.one' : 'passport.parcels.many', { count }))
      .toBe(`${count} ${count === 1 ? 'przesyłka' : few ? 'przesyłki' : 'przesyłek'}`);
  });

  it.each([0, 1, 2, 4, 5, 11, 12, 14, 21, 22, 25, 101, 102, 111, 112])('declines Russian quantities for %i', (count) => {
    // Russian counts 21 and 101 as it does 1, and 22 and 102 as it does 2.
    const forms = (one: string, few: string, many: string) =>
      `${count} ${[1, 21, 101].includes(count) ? one : [2, 4, 22, 102].includes(count) ? few : many}`;
    expect(translate('ru', 'friends.stampCount', { count })).toBe(forms('марка', 'марки', 'марок'));
    expect(translate('ru', count === 1 ? 'detail.updateCount.one' : 'detail.updateCount.many', { count }))
      .toBe(forms('обновление', 'обновления', 'обновлений'));
    expect(translate('ru', count === 1 ? 'passport.parcels.one' : 'passport.parcels.many', { count }))
      .toBe(forms('посылка', 'посылки', 'посылок'));
  });

  it('runs a Chinese day on into its delivery window after a space', () => {
    const t: Translate = (key, variables) => translate('zh', key, variables);
    const now = new Date(2026, 8, 9, 12).getTime();
    expect(localizedExpectedDelivery('2026-09-10 14:00-16:00', t, 'zh-CN', now)).toBe('明天 14:00–16:00');
    expect(localizedExpectedDelivery('2026-09-12', t, 'zh-CN', now)).toBe('9月12日周六');
  });

  it.each(['en', 'de', 'fr', 'it'] as const)('uses natural date phrases and singular counts in %s', (locale) => {
    const t: Translate = (key, variables) => translate(locale, key, variables);
    for (const key of ['time.yesterday', 'time.today', 'time.tomorrow'] as const) {
      expect(localizedDatePhrase(t(key), t)).toBe(t(key));
      expect(t('parcel.ariaExpected', { name: 'Parcel', status: 'In transit', date: t(key) }))
        .toContain(`: ${t(key)}`);
    }
    expect(localizedDatePhrase('12/09/2026', t)).toBe(t('parcel.onDate', { date: '12/09/2026' }));
    const singularStamps = { en: '1 stamp', fr: '1 timbre', de: '1 Briefmarke', it: '1 francobollo' };
    const pluralStamps = { en: 'stamps', fr: 'timbres', de: 'Briefmarken', it: 'francobolli' };
    expect(t('friends.stampCount', { count: 1 })).toBe(singularStamps[locale]);
    for (const count of [0, 2, 10]) {
      expect(t('friends.stampCount', { count })).toBe(`${count} ${pluralStamps[locale]}`);
    }
    const singleLink = { en: 'Cancel 1 previous link', fr: 'Annuler 1 lien précédent', de: '1 früheren Link deaktivieren', it: 'Annulla 1 link precedente' };
    expect(t('friends.cancelPrevious', { count: 1 })).toBe(singleLink[locale]);
    const singleDelivered = { en: 'Delivered', fr: 'Livré', de: 'Zugestellt', it: 'Consegnato' };
    expect(t('passport.delivered', { count: 1 })).toBe(singleDelivered[locale]);
    expect(t('friends.day', { count: 1 })).toBe(t('friends.day'));
  });

  it.each([
    ['en-CH', en, ['yesterday', 'today', 'tomorrow']],
    ['de-CH', de, ['gestern', 'heute', 'morgen']],
    ['fr-CH', fr, ['hier', 'aujourd’hui', 'demain']],
    ['it-CH', itMessages, ['ieri', 'oggi', 'domani']],
  ] as const)('localizes nearby dates and preserves delivery times in %s', (locale, messages, labels) => {
    const t: Translate = (key) => messages[key];
    const now = new Date(2026, 8, 9, 12).getTime();
    for (const [index, day] of [8, 9, 10].entries()) {
      const value = `2026-09-${String(day).padStart(2, '0')}`;
      expect(localizedExpectedDelivery(value, t, locale, now)).toBe(labels[index]);
      expect(localizedExpectedDelivery(`${value} 14:00-16:00`, t, locale, now)).toBe(`${labels[index]}, 14:00–16:00`);
      expect(localizedExpectedDelivery(new Date(2026, 8, day, 14, 5).toISOString(), t, locale, now)).toBe(`${labels[index]}, 14:05`);
    }
    expect(localizedExpectedDelivery('Awaiting estimate', t, locale, now)).toBe('Awaiting estimate');
  });

  it.each([
    [new Date(2026, 0, 1, 0, 5), new Date(2025, 11, 31, 23, 55), 'yesterday'],
    [new Date(2026, 11, 31, 23, 55), new Date(2027, 0, 1, 0, 5), 'tomorrow'],
    [new Date(2026, 2, 30, 0, 5), new Date(2026, 2, 29, 0, 5), 'yesterday'],
    [new Date(2026, 9, 25, 0, 5), new Date(2026, 9, 26, 0, 5), 'tomorrow'],
    [new Date(2026, 8, 9, 23, 55), new Date(2026, 8, 9, 0, 5), 'today'],
    [new Date(2026, 8, 9, 23, 55), new Date(2026, 8, 11, 0, 5), 'Fri 11 Sep'],
  ])('uses calendar days from %s to %s', (now, date, expected) => {
    expect(localizedDeliveryDate(date, (key) => en[key], 'en-CH', now.getTime())).toBe(expected);
  });
});
