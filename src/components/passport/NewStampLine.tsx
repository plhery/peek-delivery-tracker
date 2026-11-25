import { countryName } from 'universal-parcel-scraper/app';
import { useI18n } from '../../i18n';
import { countryInk } from '../../lib/passport';
import { Icon } from '../Icon';
import { STAMP_COPY } from './copy';
import type { PassportNewsItem } from './news';
import { PassportSeal } from './PassportSeal';

/** The one line a delivered parcel shows under its card while what it earned is not seen yet; it opens the passport. */
export function NewStampLine({ items, onOpen }: { items: readonly PassportNewsItem[] | undefined; onOpen: () => void }) {
  const { t, languageTag } = useI18n();
  if (!items?.length) return null;
  const [first] = items;
  const names = items.map((item) => item.country ? countryName(item.country.code, languageTag) : t(STAMP_COPY[item.stamp!.key].title));
  const heading = items.length === 1 ? t('passport.newStamp') : t('passport.newStamps', { count: items.length });
  return <button type="button" className="passport-news-line" aria-label={`${heading}: ${names.join(', ')}`} onClick={onOpen}>
    {first!.country
      ? <span className={`passport-news-line__country tone-${countryInk(first!.country.code).tone}`} aria-hidden="true">{first!.country.code}</span>
      : <PassportSeal stamp={first!.stamp!} size="mini" />}
    <span className="passport-news-line__text">
      <strong>{heading}</strong>
      <span>{names.join(' · ')}</span>
    </span>
    <Icon name="chevron" className="passport-news-line__chevron" />
  </button>;
}
