import { useMemo } from 'react';
import { countryFlag, countryName } from 'universal-parcel-scraper/app';
import { useI18n } from '../i18n';
import { COUNTRIES } from '../lib/postcodeCountry';

const MARK = '';

/**
 * A sentence of the Add sheet's carrier line with the country the carriers are asked for in it
 * (`add.line.country`). The country's name shows and the native list beside it picks another, for this number
 * only; the browser clock's country leads the list.
 */
export function LookupCountrySentence({ country, home, text, onChange }: {
  country: string;
  /** The browser clock's country, when it names one. */
  home: string | null;
  text: string;
  onChange: (country: string) => void;
}) {
  const { languageTag, t } = useI18n();
  const others = useMemo(() => COUNTRIES.filter((code) => code !== home)
    .map((code) => ({ code, name: countryName(code, languageTag) }))
    .sort((a, b) => a.name.localeCompare(b.name, languageTag)), [home, languageTag]);
  const [before, after] = t('add.line.country', { country: MARK, text }).split(MARK);
  const option = ({ code, name }: { code: string; name: string }) =>
    <option key={code} value={code}>{`${countryFlag(code)} ${name}`}</option>;
  return <>
    {before}
    <span className="lookup-country">
      {countryName(country, languageTag)}
      <select aria-label={t('add.lookupCountry')} value={country} onChange={(event) => onChange(event.target.value)}>
        {home && option({ code: home, name: countryName(home, languageTag) })}
        {home && <option disabled>──────</option>}
        {others.map(option)}
      </select>
    </span>
    {after}
  </>;
}
