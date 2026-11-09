import type { Metadata } from 'next';
import { connection } from 'next/server';
import { PhoneAppScreen } from '../../src/components/PhoneAppScreen';
import { translateMessage } from '../../src/lib/messages';
import { phoneAppSource } from '../../src/lib/phoneApp';
import { messagesFor, requestLanguage, requestLocale } from '../../src/server/requestLocale';

/** An address for the code to scan only: never indexed. */
export async function generateMetadata(): Promise<Metadata> {
  const locale = await requestLocale();
  const messages = messagesFor(locale);
  return {
    title: `${translateMessage(locale, 'app.title', undefined, messages)} — ${translateMessage(locale, 'phoneApp.title', undefined, messages)}`,
    robots: { index: false, follow: false },
  };
}

/** The landing's code to scan, for whoever the proxy did not send on to an app: a computer, or an iPad that says it is a Mac. */
export default async function PhoneAppPage({ searchParams }: { searchParams: Promise<{ [key: string]: string | string[] | undefined }> }) {
  await connection();
  return <PhoneAppScreen source={phoneAppSource((await searchParams).utm_source)} {...await requestLanguage()} />;
}
