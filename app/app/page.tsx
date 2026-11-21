import type { Metadata } from 'next';
import { notFound } from 'next/navigation';
import { connection } from 'next/server';
import { PhoneAppScreen } from '../../src/components/PhoneAppScreen';
import { guideAddresses } from '../../src/guides/guideAddresses';
import { translateMessage } from '../../src/lib/messages';
import { phoneAppSource } from '../../src/lib/phoneApp';
import { guidePagesOn } from '../../src/server/guideSettings';
import { messagesFor, requestLanguage, requestLocale } from '../../src/server/requestLocale';
import { appStoreApp } from '../../src/server/siteSettings';

/** An address for the code to scan only: never indexed. */
export async function generateMetadata(): Promise<Metadata> {
  const locale = await requestLocale();
  const messages = messagesFor(locale);
  return {
    title: `${translateMessage(locale, 'app.title', undefined, messages)} — ${translateMessage(locale, 'phoneApp.title', undefined, messages)}`,
    robots: { index: false, follow: false },
  };
}

/**
 * The landing's code to scan, for whoever the proxy did not send on to an app: a computer, or an iPad that says it is a
 * Mac. Not found on a site without an iPhone app, which shows no such code.
 */
export default async function PhoneAppPage({ searchParams }: { searchParams: Promise<{ [key: string]: string | string[] | undefined }> }) {
  await connection();
  if (!appStoreApp()) notFound();
  const guides = guidePagesOn() ? { iphone: guideAddresses('iphone-app'), android: guideAddresses('android-app') } : undefined;
  return <PhoneAppScreen source={phoneAppSource((await searchParams).utm_source)} guides={guides} {...await requestLanguage()} />;
}
