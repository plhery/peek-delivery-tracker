import type { CarrierLink } from '../generated/carriers';
import type { GuideLink } from '../generated/guides';
import { GuideCta, GuideMeta, GuideText } from '../guides/GuidePage';
import type { CarrierText } from '../guides/markdown';
import { documentLanguage, type Locale, type PageLanguage } from '../lib/locale';
import type { Translate } from '../lib/messages';
import { CarrierCard } from './CarrierIndex';
import { CarrierTracker } from './CarrierTracker';
import { carrierPath } from './paths';

/**
 * One carrier's page: its title, the tracker for its parcels right below, its
 * text, the way to the tracker again, and the language's other carriers. It
 * has no picture: the tracker stands where a guide's picture does. A page in
 * a language with pages alone has its title and text in that language, and
 * everything around them in `locale`.
 */
export function CarrierPage({ text, carrier, locale, language = locale, t, guides, others }: {
  text: CarrierText;
  carrier: CarrierLink;
  locale: Locale;
  /** The language of the page's title and text, when it is not `locale`. */
  language?: PageLanguage;
  t: Translate;
  /** The guides in the language `locale` reads them in (`guideLanguage`), which its text may link. */
  guides: readonly GuideLink[];
  /** The other carriers with a page in this language, with what each answers, and its language when it is not `locale`. */
  others: readonly { link: CarrierLink; description: string; language?: PageLanguage }[];
}) {
  return <>
    <article className="guide">
      <header className="guide__header">
        <a className="guide__crumb" href={carrierPath(locale)}>{t('carriers.title')}</a>
        <h1 lang={language === locale ? undefined : documentLanguage(language)}>{text.title}</h1>
        <GuideMeta updated={text.updated} blocks={text.blocks} locale={locale} t={t} />
      </header>
      <CarrierTracker locale={locale} color={carrier.color} label={t('carriers.track.label', { carrier: carrier.name })}
        placeholder={t('add.trackingPlaceholder')} action={t('sample.yours.action')} note={t('carriers.independent', { carrier: carrier.name })} />
      <GuideText blocks={text.blocks} locale={locale} language={language} t={t} links={guides} />
      <GuideCta locale={locale} t={t} />
    </article>
    {others.length > 0 && <nav className="guide-more" aria-labelledby="guide-more">
      <h2 id="guide-more">{t('carriers.more')}</h2>
      <ul className="guide-cards">{others.map((other) => <CarrierCard key={other.link.id} link={other.link} description={other.description} locale={other.language ?? locale} />)}</ul>
    </nav>}
  </>;
}
