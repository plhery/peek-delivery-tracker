import { useMemo, type ReactNode } from 'react';
import { countryFlag, countryName } from 'universal-parcel-scraper/app';
import { useI18n } from '../i18n';
import { carrierInfo } from '../lib/carriers';
import type { CarrierId } from '../types';

/**
 * A postcode field with the country the postcode is from beside it, when it
 * can be from more than one: the flag and code show, and the native list
 * picks. The carrier's own countries lead the list.
 */
export function PostcodeCountry({ carrier, countries, value, disabled, onChange, children }: {
  carrier: CarrierId;
  countries: readonly string[];
  value: string | null;
  disabled?: boolean;
  onChange: (country: string) => void;
  /** The postcode's input. */
  children: ReactNode;
}) {
  const { languageTag, t } = useI18n();
  const [own, others] = useMemo(() => {
    const named = (code: string) => ({ code, name: countryName(code, languageTag) });
    const home = carrierInfo(carrier).countries.filter((code) => countries.includes(code));
    return [home.map(named), countries.filter((code) => !home.includes(code)).map(named)
      .sort((a, b) => a.name.localeCompare(b.name, languageTag))];
  }, [carrier, countries, languageTag]);
  if (countries.length < 2 || !value) return <>{children}</>;
  const option = ({ code, name }: { code: string; name: string }) =>
    <option key={code} value={code}>{`${countryFlag(code)} ${name}`}</option>;
  return <span className="postcode-input">
    {children}
    <span className="postcode-country">
      <span aria-hidden="true">{countryFlag(value)} {value}</span>
      <select aria-label={t('add.requirement.postcodeCountry')} value={value} disabled={disabled}
        onChange={(event) => onChange(event.target.value)}>
        {own.map(option)}
        {own.length > 0 && others.length > 0 && <option disabled>──────</option>}
        {others.map(option)}
      </select>
    </span>
  </span>;
}
