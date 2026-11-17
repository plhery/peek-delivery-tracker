import 'server-only';
import type { Metadata } from 'next';
import { notFound } from 'next/navigation';
import { connection } from 'next/server';
import { AssistantsGuide } from '../assistants/AssistantsGuide';
import { assistantsAddresses, assistantsAlternates } from '../assistants/paths';
import { GuideFrame } from '../guides/GuideFrame';
import type { Locale } from '../lib/locale';
import { assistantsConfigured } from './assistants/settings';
import { guideWords, languageNames, pageMetadata } from './guidePages';

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
  return <GuideFrame locale={locale} t={t} addresses={assistantsAddresses()} languageNames={languageNames()} screen={`assistants/${locale}`}>
    <AssistantsGuide t={t} mcpUrl={settings.resource} />
  </GuideFrame>;
}
