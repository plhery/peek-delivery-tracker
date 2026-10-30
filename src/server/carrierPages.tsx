import 'server-only';
import type { Metadata } from 'next';
import { notFound } from 'next/navigation';
import { connection } from 'next/server';
import { CarrierIndex } from '../carriers/CarrierIndex';
import { CarrierPage } from '../carriers/CarrierPage';
import { carrierPath, hasCarrierPages } from '../carriers/paths';
import { GuideFrame } from '../guides/GuideFrame';
import { plainBlocks, questions } from '../guides/markdown';
import { guideLanguage, hrefLanguage, interfaceLanguage, type Locale, type PageLanguage } from '../lib/locale';
import { carrierAddresses, carrierAlternates, carrierLinkBySlug, carrierLinks, carrierText } from './carrierTexts';
import { guideWords, languageNames, pageMetadata } from './guidePages';
import { guideLinks } from './guides';
import { landingAddress } from './landingMetadata';
import { jsonForScript, peekOrganization } from './landingStructuredData';
import { siteOrigin } from './requestOrigin';
import { sitePicture } from './sitePreview';

/**
 * The carriers' own page's metadata. In a language no carrier's page is written in yet, it lists the English
 * pages, so search engines leave it out and no other language names it, until one is.
 */
export async function carrierIndexMetadata(locale: Locale): Promise<Metadata> {
  const t = guideWords(locale);
  const metadata = await pageMetadata(locale, (site) => carrierAlternates(site), t('carriers.heading'), t('carriers.lead'));
  return hasCarrierPages(locale) ? metadata : { ...metadata, alternates: undefined, robots: { index: false, follow: true } };
}

/** A carrier's page's metadata, or none of its own for a slug without a page, which the page answers with a 404. */
export async function carrierMetadata(locale: PageLanguage, slug: string): Promise<Metadata> {
  const link = carrierLinkBySlug(locale, slug);
  if (!link) return {};
  const { title, description, published, updated } = carrierText(locale, link.id);
  return pageMetadata(locale, (site) => carrierAlternates(site, link.id), title, description, { published, updated });
}

const described = (locale: PageLanguage) => carrierLinks(locale).map((link) => ({ link, description: carrierText(locale, link.id).description, language: locale }));

/** The carriers with a page in a language, all on one page; the English ones until a carrier's page is written in it. */
export async function CarrierIndexRoute({ locale }: { locale: Locale }) {
  await connection();
  const t = guideWords(locale);
  const addresses = { ...carrierAddresses(), [locale]: carrierPath(locale) };
  return <GuideFrame locale={locale} t={t} addresses={addresses} languageNames={languageNames()} screen={`carriers/${locale}`}>
    <CarrierIndex locale={locale} t={t} carriers={described(hasCarrierPages(locale) ? locale : 'en')} />
  </GuideFrame>;
}

/**
 * One carrier's page, in the language of its address. The proxy sends an address without a page, a
 * carrier's in a language it is not written in too, to the site's 404 page before it gets here; reached
 * anyway, such a slug is still a 404. Its language links lead to the carrier's page in the languages it is
 * written in, and to the carriers' own page in the others. A page in a language with pages alone stands in
 * English: its words around the text, its trail, its picture and its way to the tracker; its carriers lead
 * to their pages in its language first, then to the English ones.
 */
export async function CarrierRoute({ locale, slug }: { locale: PageLanguage; slug: string }) {
  await connection();
  const link = carrierLinkBySlug(locale, slug);
  if (!link) notFound();
  const text = carrierText(locale, link.id);
  const ui = interfaceLanguage(locale);
  const t = guideWords(ui);
  const site = await siteOrigin();
  const url = carrierAlternates(site, link.id)[locale];
  const peek = peekOrganization(site);
  const data = {
    '@context': 'https://schema.org',
    '@graph': [
      {
        '@type': 'Article', headline: text.title, description: text.description, inLanguage: hrefLanguage(locale),
        datePublished: text.published, dateModified: text.updated, mainEntityOfPage: url,
        image: sitePicture(site, ui).url, author: peek, publisher: peek,
      },
      {
        '@type': 'BreadcrumbList',
        itemListElement: [
          { '@type': 'ListItem', position: 1, name: 'Peek', item: landingAddress(site, ui) },
          { '@type': 'ListItem', position: 2, name: t('carriers.title'), item: carrierAlternates(site)[ui] },
          { '@type': 'ListItem', position: 3, name: text.title, item: url },
        ],
      },
      // The questions the page's last section answers, each answer as plain words.
      {
        '@type': 'FAQPage', inLanguage: hrefLanguage(locale), url,
        mainEntity: questions(text.blocks).map(({ question, answer }) => ({
          '@type': 'Question', name: question, acceptedAnswer: { '@type': 'Answer', text: plainBlocks(answer) },
        })),
      },
    ],
  };
  const own = described(locale);
  const others = [...own, ...described(ui).filter((other) => !own.some(({ link: written }) => written.id === other.link.id))];
  return <GuideFrame locale={ui} language={locale} t={t} addresses={carrierAddresses(link.id)} languageNames={languageNames()} screen={`carriers/${locale}/${link.id}`}>
    {/* Data, not a script: the browser runs nothing of it, so the page's script policy does not apply. */}
    <script type="application/ld+json" dangerouslySetInnerHTML={{ __html: jsonForScript(data) }} />
    <CarrierPage text={text} carrier={link} locale={ui} language={locale} t={t} guides={guideLinks(guideLanguage(ui))}
      others={others.filter((other) => other.link.id !== link.id)} />
  </GuideFrame>;
}
