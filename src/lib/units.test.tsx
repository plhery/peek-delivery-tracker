import { act, render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { formatKm } from '../components/map/route';
import { I18nProvider, useI18n } from '../i18n';
import { localizedClockTime } from './format';
import { browserDistanceUnit, clockPreference, distancePreference, localeDistanceUnit, localeUses24HourTime } from './units';

/** A browser set to `locale` in `timeZone`: its clock, which `Intl` gives when no locale is named, its language and its zone. */
function browser(locale: string, timeZone: string) {
  const DateTimeFormat = Intl.DateTimeFormat;
  const resolvedOptions = DateTimeFormat.prototype.resolvedOptions;
  vi.spyOn(Intl, 'DateTimeFormat').mockImplementation(function (tag?: string | string[], options?: Intl.DateTimeFormatOptions) {
    return new DateTimeFormat(tag ?? locale, options);
  } as never);
  vi.spyOn(DateTimeFormat.prototype, 'resolvedOptions').mockImplementation(function (this: Intl.DateTimeFormat) {
    return { ...resolvedOptions.call(this), timeZone };
  });
  vi.spyOn(window.navigator, 'language', 'get').mockReturnValue(locale);
}

function Probe() {
  const { languageTag, use24HourTime, distanceUnit, clock, setClock, distance, setDistance } = useI18n();
  return <>
    <output aria-label="time">{localizedClockTime(new Date(2026, 10, 14, 16, 51), languageTag, use24HourTime)}</output>
    <output aria-label="distance">{formatKm(447, languageTag, distanceUnit)}</output>
    <output aria-label="saved">{clock} {distance}</output>
    <button type="button" onClick={() => setClock('24')}>24-hour</button>
    <button type="button" onClick={() => setClock('12')}>12-hour</button>
    <button type="button" onClick={() => setClock('auto')}>Automatic time</button>
    <button type="button" onClick={() => setDistance('km')}>Kilometres</button>
    <button type="button" onClick={() => setDistance('mi')}>Miles</button>
  </>;
}

const shown = () => ['time', 'distance'].map((name) => screen.getByLabelText(name).textContent);

afterEach(() => { vi.restoreAllMocks(); });

describe('the clock and the units of distance', () => {
  it('reads a locale’s clock and its region’s units', () => {
    expect(['en-US', 'en-AU', 'en-GB', 'de-CH', 'fr-FR', 'ja-JP'].map(localeUses24HourTime)).toEqual([false, false, true, true, true, true]);
    expect(['en-US', 'en-GB', 'en', 'en-AU', 'de-CH', 'fr-CA', 'es-MX', 'my-MM', 'en-LR'].map(localeDistanceUnit))
      .toEqual(['mi', 'mi', 'mi', 'km', 'km', 'km', 'km', 'mi', 'mi']);
    expect([undefined, '', 'not a language'].map(localeDistanceUnit)).toEqual(['km', 'km', 'km']);
  });

  it('measures in the units of the place the browser’s time zone names', () => {
    const zones = ['America/New_York', 'America/Indiana/Knox', 'America/Indianapolis', 'Pacific/Honolulu', 'US/Eastern',
      'Europe/London', 'Europe/Jersey', 'Africa/Monrovia', 'Asia/Yangon', 'Europe/Zurich', 'Europe/Paris', 'America/Toronto',
      'America/Mexico_City', 'Australia/Sydney'];
    expect(zones.map((zone) => browserDistanceUnit('en-US', zone)))
      .toEqual(['mi', 'mi', 'mi', 'mi', 'mi', 'mi', 'mi', 'mi', 'mi', 'km', 'km', 'km', 'km', 'km']);
    expect(browserDistanceUnit('fr-FR', 'America/Chicago')).toBe('mi');
    // A zone that names no place leaves it to the language.
    expect([['en-US', 'UTC'], ['en-GB', 'Etc/UTC'], ['en-US', undefined], ['de-CH', 'UTC']].map(([locale, zone]) => browserDistanceUnit(locale, zone)))
      .toEqual(['mi', 'mi', 'mi', 'km']);
  });

  it('follows the browser for anything but a saved choice', () => {
    expect([null, '', 'auto', 'true', 'false', '24', '12'].map(clockPreference)).toEqual(['auto', 'auto', 'auto', 'auto', 'auto', '24', '12']);
    expect([null, 'auto', 'miles', 'km', 'mi'].map(distancePreference)).toEqual(['auto', 'auto', 'auto', 'km', 'mi']);
  });

  it('follows a browser on a 12-hour clock that measures in miles', () => {
    browser('en-US', 'America/New_York');
    render(<I18nProvider><Probe /></I18nProvider>);
    expect(shown()).toEqual(['4:51 PM', '280 mi']);
    expect(screen.getByLabelText('saved')).toHaveTextContent('auto auto');
  });

  it('follows a browser on a 24-hour clock that measures in kilometres', () => {
    browser('de-CH', 'Europe/Zurich');
    render(<I18nProvider><Probe /></I18nProvider>);
    expect(shown()).toEqual(['16:51', '450 km']);
  });

  it('measures in kilometres for Chrome’s American English in Switzerland', () => {
    browser('en-US', 'Europe/Zurich');
    render(<I18nProvider><Probe /></I18nProvider>);
    expect(shown()[1]).toBe('450 km');
  });

  it('follows the browser when its language changes', () => {
    browser('de-CH', 'Europe/Zurich');
    render(<I18nProvider><Probe /></I18nProvider>);
    vi.restoreAllMocks();
    browser('en-US', 'America/New_York');
    act(() => { window.dispatchEvent(new Event('languagechange')); });
    expect(shown()).toEqual(['4:51 PM', '280 mi']);
  });

  it('keeps a saved choice whatever the browser says', () => {
    browser('en-US', 'America/New_York');
    window.localStorage.setItem('deliveryTrackerClock', '24');
    window.localStorage.setItem('deliveryTrackerDistanceUnit', 'km');
    const first = render(<I18nProvider><Probe /></I18nProvider>);
    expect(shown()).toEqual(['16:51', '450 km']);
    first.unmount();

    vi.restoreAllMocks();
    browser('de-CH', 'Europe/Zurich');
    window.localStorage.setItem('deliveryTrackerClock', '12');
    window.localStorage.setItem('deliveryTrackerDistanceUnit', 'mi');
    render(<I18nProvider><Probe /></I18nProvider>);
    expect(shown()).toEqual(['4:51 PM', '280 mi']);
  });

  it('saves a choice, for the push worker too, and goes back to the browser’s', async () => {
    browser('de-CH', 'Europe/Zurich');
    const put = vi.fn(async () => undefined);
    vi.stubGlobal('caches', { open: vi.fn(async () => ({ put })) });
    const user = userEvent.setup();
    render(<I18nProvider><Probe /></I18nProvider>);
    // Reading the browser saves nothing.
    expect(put).not.toHaveBeenCalled();
    expect(window.localStorage.getItem('deliveryTrackerClock')).toBeNull();

    await user.click(screen.getByRole('button', { name: '12-hour' }));
    await user.click(screen.getByRole('button', { name: 'Miles' }));
    expect(shown()).toEqual(['4:51 PM', '280 mi']);
    expect(window.localStorage.getItem('deliveryTrackerClock')).toBe('12');
    expect(window.localStorage.getItem('deliveryTrackerDistanceUnit')).toBe('mi');
    const saved = put.mock.calls.at(-1) as unknown as [string, Response];
    expect(saved[0]).toBe('/__peek/preferences/clock');
    expect(await saved[1].text()).toBe('12');

    await user.click(screen.getByRole('button', { name: 'Automatic time' }));
    expect(shown()).toEqual(['16:51', '280 mi']);
    expect(window.localStorage.getItem('deliveryTrackerClock')).toBe('auto');
    expect(await (put.mock.calls.at(-1) as unknown as [string, Response])[1].text()).toBe('auto');
    vi.unstubAllGlobals();
  });

  it('takes another tab’s choice', () => {
    browser('de-CH', 'Europe/Zurich');
    render(<I18nProvider><Probe /></I18nProvider>);
    window.localStorage.setItem('deliveryTrackerClock', '12');
    act(() => { window.dispatchEvent(new StorageEvent('storage', { key: 'deliveryTrackerClock' })); });
    expect(shown()).toEqual(['4:51 PM', '450 km']);
  });
});
