import type { Metadata } from 'next';
import { connection } from 'next/server';
import { notFound } from 'next/navigation';
import { AndroidTestScreen } from '../../src/components/AndroidTestScreen';
import { ANDROID_TEST_OPEN } from '../../src/lib/androidTest';
import { translateMessage } from '../../src/lib/messages';
import { messagesFor, requestLanguage, requestLocale } from '../../src/server/requestLocale';

/** A page for the closed test only: never indexed. */
export async function generateMetadata(): Promise<Metadata> {
  const locale = await requestLocale();
  const messages = messagesFor(locale);
  return {
    title: `${translateMessage(locale, 'app.title', undefined, messages)} — ${translateMessage(locale, 'androidTest.page.title', undefined, messages)}`,
    robots: { index: false, follow: false },
  };
}

/** Peek for Android's three steps, where the site's invitation to test it leads; not found while it is switched off. */
export default async function AndroidTestPage() {
  if (!ANDROID_TEST_OPEN) notFound();
  await connection();
  return <AndroidTestScreen {...await requestLanguage()} />;
}
