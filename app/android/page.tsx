import type { Metadata } from 'next';
import { connection } from 'next/server';
import { notFound } from 'next/navigation';
import { AndroidTestScreen } from '../../src/components/AndroidTestScreen';
import { guideAddresses } from '../../src/guides/guideAddresses';
import { androidTestOpen } from '../../src/lib/androidTest';
import { translateMessage } from '../../src/lib/messages';
import { guidePagesOn } from '../../src/server/guideSettings';
import { messagesFor, requestLanguage, requestLocale } from '../../src/server/requestLocale';
import { androidStoreApp } from '../../src/server/siteSettings';

/** A page for the closed test only: never indexed. */
export async function generateMetadata(): Promise<Metadata> {
  const locale = await requestLocale();
  const messages = messagesFor(locale);
  return {
    title: `${translateMessage(locale, 'app.title', undefined, messages)} — ${translateMessage(locale, 'androidTest.page.title', undefined, messages)}`,
    robots: { index: false, follow: false },
  };
}

/** The Android app's three steps, where the site's invitation to test it leads; not found while the site invites no one. */
export default async function AndroidTestPage() {
  await connection();
  if (!androidTestOpen(androidStoreApp())) notFound();
  return <AndroidTestScreen guide={guidePagesOn() ? guideAddresses('android-app') : undefined} {...await requestLanguage()} />;
}
