import type { Metadata, Viewport } from 'next';
import type { ReactNode } from 'react';
import '../src/styles.css';
import { PublicConfigurationProvider } from '../src/lib/publicConfiguration';
import { APPEARANCE_BOOTSTRAP } from '../src/lib/appearanceConfig';
import { ENTRY_HINT_BOOTSTRAP } from '../src/lib/entryHintConfig';
import { ERROR_REPORTS_META } from '../src/lib/errorReportsConfig';
import { documentLanguage, manifestPath } from '../src/lib/locale';
import { browserErrorReporting } from '../src/server/browserErrors';
import { publicConfiguration } from '../src/server/publicConfiguration';
import { requestLocale } from '../src/server/requestLocale';
import { requestOrigin } from '../src/server/requestOrigin';
import { PREVIEW_LOCALES, sitePicture, siteTitle, wordsIn } from '../src/server/sitePreview';

const site: Metadata = {
  applicationName: 'Peek',
  icons: {
    icon: { url: '/icons/favicon.svg', type: 'image/svg+xml' },
    apple: '/icons/apple-touch-icon.png',
  },
  appleWebApp: {
    capable: true,
    statusBarStyle: 'default',
    title: 'Peek',
  },
};

/**
 * Peek's name and what it is, in the reader's language, for the page and for the app a
 * browser installs from it. A page that draws no preview of its own shares Peek's picture,
 * under its own title and description.
 */
export async function generateMetadata(): Promise<Metadata> {
  const origin = await requestOrigin();
  const locale = await requestLocale();
  const t = wordsIn(locale);
  const picture = sitePicture(origin, locale);
  return {
    ...site,
    title: siteTitle(t),
    description: t('preview.site.description'),
    metadataBase: origin,
    manifest: manifestPath(locale),
    openGraph: { type: 'website', siteName: 'Peek', locale: PREVIEW_LOCALES[locale], images: [picture] },
    twitter: { card: 'summary_large_image', images: [picture.url] },
  };
}

export const viewport: Viewport = {
  width: 'device-width',
  initialScale: 1,
  viewportFit: 'cover',
  themeColor: '#F4F5F1',
};

export default async function RootLayout({ children }: Readonly<{ children: ReactNode }>) {
  const locale = await requestLocale();
  const configuration = publicConfiguration();
  // Where the page's scripts report their errors. A page keeps it with the scripts it loaded, so
  // a page open across a deploy still names the release its scripts belong to.
  const errorReports = browserErrorReporting();
  return (
    <html lang={documentLanguage(locale)} suppressHydrationWarning>
      <head>
        <script dangerouslySetInnerHTML={{ __html: APPEARANCE_BOOTSTRAP }} />
        {/* Tells the stylesheet, before anything is painted, who is about to see the landing. */}
        <script dangerouslySetInnerHTML={{ __html: ENTRY_HINT_BOOTSTRAP }} />
        {configuration.auth && <link rel="preconnect" href={configuration.auth.url} crossOrigin="anonymous" />}
        {errorReports && <meta name={ERROR_REPORTS_META} content={JSON.stringify(errorReports)} />}
      </head>
      <body><PublicConfigurationProvider value={configuration}>{children}</PublicConfigurationProvider></body>
    </html>
  );
}
