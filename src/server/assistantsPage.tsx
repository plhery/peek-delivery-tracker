import 'server-only';
import type { Metadata } from 'next';
import { notFound } from 'next/navigation';
import { connection } from 'next/server';
import { AssistantsGuide } from '../assistants/AssistantsGuide';
import { assistantsAddresses, assistantsAlternates } from '../assistants/paths';
import { GuideFrame } from '../guides/GuideFrame';
import { GuideCard } from '../guides/GuideIndex';
import { documentLanguage, guideLanguage, type Locale } from '../lib/locale';
import { assistantsConfigured } from './assistants/settings';
import { guideWords, languageNames, pageMetadata } from './guidePages';
import { guide, guideLinks } from './guides';

/** The guides the page offers at its end: connecting an assistant, and Home Assistant. */
const ASSISTANT_GUIDES: readonly string[] = ['ai-assistants', 'home-assistant'];

/** The page's metadata where assistants can connect; elsewhere none, and the page is the site's 404. */
export async function assistantsMetadata(locale: Locale): Promise<Metadata> {
  if (!assistantsConfigured()) return {};
  const t = guideWords(locale);
  return pageMetadata(locale, assistantsAlternates, t('assistants.page.title'), t('assistants.page.description'));
}

/**
 * How to connect an assistant to Peek, in the language of its address, with the address assistants connect to. A
 * deployment where no assistant can connect (`ASSISTANT_ACCOUNTS` unset) has no such page: the proxy answers it with
 * the site's 404 page, and reached anyway, it is still a 404.
 */
export async function AssistantsRoute({ locale }: { locale: Locale }) {
  await connection();
  const settings = assistantsConfigured();
  if (!settings) notFound();
  const t = guideWords(locale);
  // The guides on assistants, in the reader's language where the guides are written in it, in English otherwise.
  const language = guideLanguage(locale);
  const related = guideLinks(language).filter((link) => ASSISTANT_GUIDES.includes(link.id));
  return <GuideFrame locale={locale} t={t} addresses={assistantsAddresses()} languageNames={languageNames()} screen={`assistants/${locale}`}>
    <AssistantsGuide t={t} mcpUrl={settings.resource} />
    {related.length > 0 && <nav className="guide-more" aria-labelledby="guide-more">
      <h2 id="guide-more">{t('guides.more')}</h2>
      <ul className="guide-cards" lang={language === locale ? undefined : documentLanguage(language)}>
        {related.map((link) => <GuideCard key={link.id} link={link} description={guide(language, link.id).description} locale={language} />)}
      </ul>
    </nav>}
  </GuideFrame>;
}
