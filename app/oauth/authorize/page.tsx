import type { Metadata } from 'next';
import { headers } from 'next/headers';
import { notFound } from 'next/navigation';
import { connection } from 'next/server';
import { AssistantConsentScreen } from '../../../src/components/AssistantConsentScreen';
import { FeedbackScreen } from '../../../src/components/FeedbackScreen';
import { translateMessage } from '../../../src/lib/messages';
import { clientIp, clientNetwork, clientSite } from '../../../src/server/api';
import { readAuthorizationRequest } from '../../../src/server/assistants/authorize';
import { consentDetails } from '../../../src/server/assistants/consent';
import { reviewAccountDigest } from '../../../src/server/assistants/review';
import { databaseUnavailable } from '../../../src/server/observability';
import { assistantsConfigured } from '../../../src/server/assistants/settings';
import { RateLimiter } from '../../../src/server/rateLimit';
import { messagesFor, requestLanguage, requestLocale } from '../../../src/server/requestLocale';
import { serviceClient } from '../../../src/server/runtime';

interface Props {
  searchParams: Promise<{ [key: string]: string | string[] | undefined }>;
}

/**
 * Each opening may fetch a client's metadata document, so one network (an IPv4 address or IPv6 /64) opens the page at
 * most this often a minute, and one IPv6 /48, which may hold a whole street's homes, four times as often.
 */
const OPENINGS_PER_MINUTE = 30;
const OPENINGS_PER_SITE_PER_MINUTE = 120;
const openings = new RateLimiter();

/** Whether this address has opened the page too often lately; each opening counts. */
function openedTooOften(ip: string): boolean {
  const site = clientSite(ip);
  const network = openings.retryAfter(clientNetwork(ip), { limit: OPENINGS_PER_MINUTE, window: 60 }) > 0;
  return (site !== null && openings.retryAfter(site, { limit: OPENINGS_PER_SITE_PER_MINUTE, window: 60 }) > 0) || network;
}

/** The page answers one person's request: it is never indexed, and never leaves as a referrer. */
export async function generateMetadata(): Promise<Metadata> {
  const locale = await requestLocale();
  const messages = messagesFor(locale);
  return {
    title: `${translateMessage(locale, 'app.title', undefined, messages)} — ${translateMessage(locale, 'assistants.consent.tab', undefined, messages)}`,
    robots: { index: false, follow: false },
    referrer: 'no-referrer',
  };
}

/**
 * The OAuth authorization endpoint, where an assistant sends its user to connect Peek. Anyone can register a client,
 * so nothing goes back to one before its owner answers: a request Peek can't take is shown here, never redirected
 * (RFC 9700 §4.11.2).
 */
export default async function AuthorizePage({ searchParams }: Props) {
  await connection();
  const settings = assistantsConfigured();
  const service = serviceClient();
  if (!settings || !service) notFound();
  const language = await requestLanguage();
  if (openedTooOften(clientIp({ headers: await headers() }))) {
    return <AssistantConsentScreen {...language} request={null} busy query="" />;
  }
  const parameters = new URLSearchParams();
  for (const [name, value] of Object.entries(await searchParams)) {
    for (const one of [value ?? []].flat()) parameters.append(name, one);
  }
  const read = await readAuthorizationRequest(settings, service, parameters).catch((error: unknown) => {
    if (databaseUnavailable(error)) return null;
    throw error;
  });
  // The database down for maintenance: said calmly, and the same request can be opened again in a few minutes.
  if (read === null) return <FeedbackScreen title="maintenance.title" description="maintenance.description" {...language} />;
  if (!('request' in read)) return <AssistantConsentScreen {...language} request={null} query="" />;
  // The answer goes where the page says, even if the client's document names another address by then.
  parameters.set('redirect_uri', read.request.redirectUri);
  return <AssistantConsentScreen {...language} request={consentDetails(read.request)} query={parameters.toString()}
    reviewAccount={reviewAccountDigest()} />;
}
