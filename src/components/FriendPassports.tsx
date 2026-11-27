import { useMemo, type CSSProperties } from 'react';
import { countryFlag, countryName } from 'universal-parcel-scraper/app';
import type { ApiFriendCard, ApiFriendStamp, ApiFriendStats } from '../generated/apiContract';
import { useI18n } from '../i18n';
import { passportBook, SHARED_STAMPS, STAMPS, type StampProgress } from '../lib/passport';
import type { ParcelWithEvents } from '../types';
import { FriendStampCollection } from './FriendStampCollection';
import { CountryInk } from './passport/CountryInk';
import { STAMP_COPY } from './passport/copy';
import { PassportSeal } from './passport/PassportSeal';
import './passport/passport.css';
import './FriendPassports.css';

const stampOf = (key: string) => STAMPS.find((stamp) => stamp.key === key)!;

/** Two made-up passports side by side: what Friends shows, before anyone has made a profile. */
const SAMPLE = [
  { name: 'Alex', tone: 'blue', delivered: 10, days: 6, stamps: ['first', 'ten', 'connected', 'express', 'rightNextDoor', 'pickedUp'] },
  { name: 'Mila', tone: 'lilac', delivered: 12, days: 3, stamps: ['first', 'ten', 'connected', 'express', 'aroundWorld'] },
] as const;

export function FriendsSample() {
  const { t } = useI18n();
  return <figure className="friends-sample">
    <figcaption>{t('friends.sampleTitle')}</figcaption>
    <div className="friends-sample__pair">{SAMPLE.map((passport) => <div key={passport.name} className={`friends-sample__passport tone-${passport.tone}`}>
      <strong>{passport.name}</strong>
      <small>{passport.delivered} {t('passport.delivered', { count: passport.delivered }).toLocaleLowerCase()} · {t('friends.days', { count: passport.days })}</small>
      <span className="friends-sample__seals" aria-hidden="true">{passport.stamps.map((key) => <PassportSeal key={key} stamp={stampOf(key)} size="mini" />)}</span>
    </div>)}</div>
  </figure>;
}

/**
 * A friend's Passport beside the reader's: the stamps they both have, those only the friend has with how far the reader
 * is, and those only the reader has; then the friend's countries, when they share them. The reader's side is worked out
 * on the device from their own parcels, as their Passport is.
 */
export function FriendPassportCompare({ friend, stats, parcels }: { friend: ApiFriendCard; stats: ApiFriendStats; parcels: readonly ParcelWithEvents[] }) {
  const { t, languageTag } = useI18n();
  const book = useMemo(() => passportBook(parcels), [parcels]);
  const mine = new Map<string, StampProgress>(book.stamps.map((stamp) => [stamp.key, stamp]));
  const theirs = new Set<ApiFriendStamp>(stats.stamps);
  const has = (key: ApiFriendStamp) => mine.get(key)?.earned === true;
  const groups = [
    { id: 'both', heading: t('friends.bothHave'), keys: SHARED_STAMPS.filter((key) => theirs.has(key) && has(key)) },
    { id: 'theirs', heading: t('friends.onlyFriend', { name: friend.nickname }), keys: SHARED_STAMPS.filter((key) => theirs.has(key) && !has(key)), progress: true },
    { id: 'mine', heading: t('friends.onlyYou'), keys: SHARED_STAMPS.filter((key) => !theirs.has(key) && has(key)) },
  ].filter((group) => group.keys.length);
  let order = 0;
  const rise = () => ({ '--rise': order++ }) as CSSProperties;
  const countries = [...stats.countries ?? []].map((country) => ({ ...country, name: countryName(country.code, languageTag) }))
    .sort((a, b) => b.count - a.count || a.name.localeCompare(b.name, languageTag));
  const reached = new Set(book.countries.map((country) => country.code));
  const common = countries.filter((country) => reached.has(country.code)).length;
  return <div className="friend-compare">
    {groups.length ? groups.map((group) => <section key={group.id} className="friend-compare__group">
      <h3>{group.heading} <span className="friend-compare__count">{group.keys.length}</span></h3>
      <ul className="friend-compare__grid">{group.keys.map((key) => {
        const stamp = mine.get(key)!;
        const progress = group.progress && stamp.total > 1 ? t('friends.yourProgress', { count: Math.min(stamp.count, stamp.total), total: stamp.total }) : null;
        return <li key={key} className="friend-compare__stamp" style={rise()}>
          <PassportSeal stamp={stamp} postmark={false} />
          <span className="stamp-card__name">{t(STAMP_COPY[key].title)}</span>
          {progress && <span className="friend-compare__progress">{progress}</span>}
        </li>;
      })}</ul>
    </section>) : <FriendStampCollection stats={stats} />}
    {countries.length > 0 && <section className="friend-compare__group friend-countries">
      <h3>{t('passport.countriesLabel', { count: countries.length })} <span className="friend-compare__count">{countries.length}</span></h3>
      {common > 0 && <p className="friend-countries__common">{t('friends.countriesBoth', { count: common })}</p>}
      <ul className="passport-countries__grid">{countries.map((country) => <li key={country.code} className="country-ink-cell friend-country" style={rise()}>
        <CountryInk code={country.code} />
        <span className="country-ink__name"><span aria-hidden="true">{countryFlag(country.code)}</span> {country.name}</span>
        <span className="country-ink__count">{t('passport.parcels.many', { count: country.count })}</span>
      </li>)}</ul>
    </section>}
  </div>;
}
