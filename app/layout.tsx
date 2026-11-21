import type { Metadata, Viewport } from 'next';
import type { ReactNode } from 'react';
import '../src/styles.css';
import { authConfigFromEnvironment } from '../src/auth/authConfig';
import { APPEARANCE_BOOTSTRAP } from '../src/lib/appearanceConfig';
import { smartAppBanner } from '../src/lib/appStore';
import { ENTRY_HINT_BOOTSTRAP } from '../src/lib/entryHintConfig';
import { ERROR_REPORTS_META } from '../src/lib/errorReportsConfig';
import { documentLanguage, manifestPath } from '../src/lib/locale';
import { SiteProvider } from '../src/SiteContext';
import { browserErrorReporting } from '../src/server/browserErrors';
import { requestLocale, requestPageLanguage } from '../src/server/requestLocale';
import { requestOrigin } from '../src/server/requestOrigin';
import { PREVIEW_LOCALES, sitePicture, siteTitle, wordsIn } from '../src/server/sitePreview';
import { appStoreApp, siteName, siteSettings } from '../src/server/siteSettings';

const icons: Metadata['icons'] = {
  icon: { url: '/icons/favicon.svg', type: 'image/svg+xml' },
  apple: '/icons/apple-touch-icon.png',
};

/**
 * The site's name and what it is, in the reader's language, for the page and for the app a
 * browser installs from it. A page that draws no preview of its own shares the site's picture,
 * under its own title and description. On an iPhone, where the site has an app on the App Store,
 * Safari offers it above every page, or opens it where it is installed: at the page's own address
 * where the page names one.
 */
export async function generateMetadata(): Promise<Metadata> {
  const origin = await requestOrigin();
  const locale = await requestLocale();
  const t = wordsIn(locale);
  const picture = sitePicture(origin, locale);
  const name = siteName();
  return {
    applicationName: name,
    icons,
    appleWebApp: { capable: true, statusBarStyle: 'default', title: name },
    title: siteTitle(t),
    description: t('preview.site.description'),
    metadataBase: origin,
    manifest: manifestPath(locale),
    openGraph: { type: 'website', siteName: name, locale: PREVIEW_LOCALES[locale], images: [picture] },
    twitter: { card: 'summary_large_image', images: [picture.url] },
    other: smartAppBanner(appStoreApp(), new URL('/', origin).href),
  };
}

export const viewport: Viewport = {
  width: 'device-width',
  initialScale: 1,
  viewportFit: 'cover',
  themeColor: '#F4F5F1',
};

// A returning account refreshes its sign-in there first; open the connection while the page loads.
const authOrigin = authConfigFromEnvironment({
  supabaseUrl: process.env.NEXT_PUBLIC_SUPABASE_URL,
  supabasePublishableKey: process.env.NEXT_PUBLIC_SUPABASE_PUBLISHABLE_KEY,
})?.url;

export default async function RootLayout({ children }: Readonly<{ children: ReactNode }>) {
  // Where the page's scripts report their errors. A page keeps it with the scripts it loaded, so
  // a page open across a deploy still names the release its scripts belong to.
  const errorReports = browserErrorReporting();
  return (
    <html lang={documentLanguage(await requestPageLanguage())} suppressHydrationWarning>
      <head>
        <script dangerouslySetInnerHTML={{ __html: APPEARANCE_BOOTSTRAP }} />
        {/* Tells the stylesheet, before anything is painted, who is about to see the landing. */}
        <script dangerouslySetInnerHTML={{ __html: ENTRY_HINT_BOOTSTRAP }} />
        {authOrigin && <link rel="preconnect" href={authOrigin} crossOrigin="anonymous" />}
        {errorReports && <meta name={ERROR_REPORTS_META} content={JSON.stringify(errorReports)} />}
      </head>
      {/* What the server's settings say about the site, such as whether the pages link the guides, which only the server knows. */}
      <body><SiteProvider site={siteSettings()}>{children}</SiteProvider></body>
    </html>
  );
}
