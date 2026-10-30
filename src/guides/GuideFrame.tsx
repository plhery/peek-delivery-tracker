import { createElement, type ReactNode } from 'react';
import mark from '../brand/mark.json';
import { carrierListPath } from '../carriers/paths';
import { documentLanguage, guideLanguage, hrefLanguage, languagePath, PAGE_LANGUAGES, type Locale, type PageAddresses, type PageLanguage } from '../lib/locale';
import type { Translate } from '../lib/messages';
import { SOURCE_URL } from '../lib/source';
import { GuideAnalytics } from './GuideAnalytics';
import { guidePath } from './paths';
import './guides.css';

/** Peek's mark, drawn without the app around it. */
function Mark({ size }: { size: number }) {
  return <svg className="peek-mark" viewBox={`0 0 ${mark.size} ${mark.size}`} style={{ width: size, height: size }} aria-hidden="true">
    <rect width={mark.size} height={mark.size} rx={mark.tile.radius} fill={mark.tile.fill} />
    {mark.shapes.map(({ tag, ...attributes }, index) => createElement(tag, { key: index, ...attributes }))}
  </svg>;
}

/**
 * What stands around every guides page: the name and the way to the tracker
 * above, and below it the same foot as the landing, with the page in each
 * language as plain links a search engine can follow. The tracker is the
 * landing in the page's language, reached with a plain link so the browser
 * loads it anew: what the landing decides before its first paint only runs on
 * a fresh document. A page whose text is in a language with pages alone
 * stands in the frame of `locale`, which declares its own language, and its
 * foot names its own language too.
 */
export function GuideFrame({ locale, language = locale, t, addresses, languageNames, screen, children }: {
  locale: Locale;
  /** The language of the page's text, when it is not `locale`'s. */
  language?: PageLanguage;
  t: Translate;
  /** This page in every language it is written in. */
  addresses: PageAddresses;
  /** Each language's name, written in that language: the site's own, and those with pages alone. */
  languageNames: Record<Locale, string> & Partial<Record<PageLanguage, string>>;
  /** The page's name in the usage counts. */
  screen: string;
  children: ReactNode;
}) {
  const tracker = languagePath(locale);
  // The document then declares the text's language, and the frame its own.
  return <div className="guide-page" lang={language === locale ? undefined : documentLanguage(locale)}>
    <header className="guide-top">
      <a className="peek-lockup" href={tracker}>
        <Mark size={28} />
        <span className="peek-lockup__text"><span className="peek-lockup__name">{t('app.title')}</span><span className="peek-lockup__tagline">{t('app.tagline')}</span></span>
      </a>
      <a className="button button--primary guide-top__track" href={tracker}>{t('sample.yours.action')}</a>
    </header>
    <main>{children}</main>
    <footer className="guide-footer">
      <div className="guide-footer__links">
        <a className="guide-footer__name" href={tracker}><Mark size={18} />{t('app.title')} · {t('app.tagline')}</a>
        <a href={guidePath(guideLanguage(locale))}>{t('guides.all')}</a>
        <a href={carrierListPath(locale)}>{t('carriers.all')}</a>
        <a href="/privacy.html">{t('auth.privacyLink')}</a>
        <a href={SOURCE_URL} target="_blank" rel="noopener noreferrer">GitHub</a>
      </div>
      <nav className="guide-footer__languages" aria-label={t('language.label')}>
        {PAGE_LANGUAGES.filter((other) => addresses[other] !== undefined).map((other) => <a key={other} href={addresses[other]} hrefLang={hrefLanguage(other)} lang={documentLanguage(other)}
          aria-current={other === language ? 'page' : undefined}>{languageNames[other]}</a>)}
      </nav>
    </footer>
    <GuideAnalytics screen={screen} />
  </div>;
}
