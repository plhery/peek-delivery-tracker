import { GUIDE_LINKS } from '../generated/guides';
import { GUIDE_LANGUAGES, type GuideLanguage } from '../lib/locale';
import { guidePath } from './paths';

/** One guide's address and title in each language it is written in: small enough for a page to hand its client. */
export type GuideAddresses = Partial<Record<GuideLanguage, { href: string; title: string }>>;

/** The addresses of the guide `id`, for a server page to pass on, so the client never loads every guide's links. */
export function guideAddresses(id: string): GuideAddresses {
  return Object.fromEntries(GUIDE_LANGUAGES.flatMap((language) => {
    const link = GUIDE_LINKS[language].find((entry) => entry.id === id);
    return link ? [[language, { href: guidePath(language, link.slug), title: link.title }]] : [];
  }));
}
