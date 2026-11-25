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

/** Never indexed: the Android guide is the page search engines show. */
export async function generateMetadata(): Promise<Metadata> {
  const locale = await requestLocale();
  const messages = messagesFor(locale);
  const title = androidTestOpen(androidStoreApp()) ? 'androidTest.page.title' : 'androidTest.alerts.title';
  return {
    title: `${translateMessage(locale, 'app.title', undefined, messages)} — ${translateMessage(locale, title, undefined, messages)}`,
    robots: { index: false, follow: false },
  };
}

/**
 * The Android app's page: the three steps while its closed test is open, where the site's invitation leads; Google
 * Play once it is not. Not found on a site without an Android app.
 */
export default async function AndroidTestPage() {
  await connection();
  if (!androidStoreApp()) notFound();
  return <AndroidTestScreen guide={guidePagesOn() ? guideAddresses('android-app') : undefined} {...await requestLanguage()} />;
}
