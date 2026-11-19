import { documentLanguage, guideLanguage, type Locale } from '../lib/locale';
import type { GuideAddresses } from './guideAddresses';

/**
 * A guide, by its title, in the reader's language or the nearest one it is written in, which the link then names
 * (Brazilian Portuguese reads the European guide, Russian and Chinese the English one). Nothing without the guide.
 */
export function GuideTitleLink({ guide, locale }: { guide?: GuideAddresses; locale: Locale }) {
  const language = guideLanguage(locale);
  const shown = guide?.[language];
  if (!shown) return null;
  return <a href={shown.href} hrefLang={language === locale ? undefined : documentLanguage(language)}>
    {shown.title}<span aria-hidden="true"> ›</span>
  </a>;
}
