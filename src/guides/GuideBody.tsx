import { Fragment, type ReactNode } from 'react';
import { carrierPath } from '../carriers/paths';
import { CARRIER_LINKS } from '../generated/carriers';
import type { GuideLink } from '../generated/guides';
import { documentLanguage, languagePath, type Locale, type PageLanguage } from '../lib/locale';
import type { Block, Inline, JourneyIcon } from './markdown';
import { NumberChecker, type CheckerWords } from './NumberChecker';
import { guidePath } from './paths';

/** The tones the parts of a number and the stops of a journey take, in turn. */
const TONES = ['blue', 'peach', 'green', 'lilac', 'ochre'] as const;

const JOURNEY_PATHS: Record<JourneyIcon, string> = {
  shop: 'M3 10h18l-2-7H5l-2 7Zm2 0v11h14V10M9 21v-7h6v7',
  label: 'M3 6h18v12H3V6Zm4 3v6m3-6v6m2-6v6m3-6v6m2-6v6',
  warehouse: 'm3 10 9-6 9 6v11H3V10Zm5 11v-8h8v8M8 17h8',
  truck: 'M3 5h11v12H3V5Zm11 5h4l3 4v3h-7M6 20a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm12 0a2 2 0 1 0 0-4 2 2 0 0 0 0 4Z',
  plane: 'M12 3c1 0 1.6 1.2 1.6 3v3.6L21 14v2l-7.4-2.2V18l2.4 1.8V21L12 20l-4 1v-1.2L10.4 18v-4.2L3 16v-2l7.4-4.4V6c0-1.8.6-3 1.6-3Z',
  ship: 'M4 14h16l-2 6H6l-2-6Zm3 0V9h10v5M10 9V5h4v4M2 20c1.5 1.5 3.5 1.5 5 0 1.5 1.5 3.5 1.5 5 0 1.5 1.5 3.5 1.5 5 0 1.5 1.5 3.5 1.5 5 0',
  customs: 'M9 3h6v6l2.5 4v3h-11v-3L9 9V3ZM5 20h14M9 13h6',
  handover: 'M4 8h14m-4-4 4 4-4 4M20 16H6m4-4-4 4 4 4',
  locker: 'M4 3h16v18H4V3Zm8 0v18M4 9h8m0 6h8M8 6h.01M16 9h.01M8 15h.01M16 18h.01',
  home: 'm3 11 9-8 9 8v10h-6v-6H9v6H3V11Z',
};

/** An email address in a page's text. Its letters hold nothing HTML would read as markup. */
const EMAIL = /[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(?:\.[A-Za-z0-9-]+)*\.[A-Za-z]{2,}/g;

/**
 * Text with each email address in it between `email_off` comments, which Cloudflare's email obfuscation
 * leaves alone: it would make the address a link to /cdn-cgi/l/email-protection, which answers crawlers
 * with a 404. The address stays text, as the page writes it.
 */
function withEmails(text: string): ReactNode {
  const parts: ReactNode[] = [];
  let from = 0;
  for (const { 0: email, index } of text.matchAll(EMAIL)) {
    if (index > from) parts.push(text.slice(from, index));
    parts.push(<span key={index} dangerouslySetInnerHTML={{ __html: `<!--email_off-->${email}<!--/email_off-->` }} />);
    from = index + email.length;
  }
  if (from === 0) return text;
  if (from < text.length) parts.push(text.slice(from));
  return parts;
}

function JourneyGlyph({ name }: { name: JourneyIcon }) {
  return <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.65" strokeLinecap="round" strokeLinejoin="round" aria-hidden="true"><path d={JOURNEY_PATHS[name]} /></svg>;
}

/**
 * A guide's blocks as the page's elements. Links to other guides, to a
 * carrier's page and to the tracker (`/`) lead to the guide's own language;
 * every other link leaves the site in a new tab. A text in a language with
 * pages alone leads to a carrier's page in its language where there is one,
 * and everywhere else to `locale`, the language around it.
 */
export function GuideBody({ blocks, locale, language = locale, links, sources, checker }: {
  blocks: readonly Block[];
  locale: Locale;
  /** The language the blocks are written in, when it is not `locale`. */
  language?: PageLanguage;
  links: readonly GuideLink[];
  /** The heading above the sources, in the reader's language. */
  sources: string;
  /** What a number checker says, in the reader's language. */
  checker: CheckerWords;
}) {
  function carrierAddress(id: string): string {
    const own = CARRIER_LINKS[language].find((link) => link.id === id);
    return own ? carrierPath(language, own.slug) : carrierPath(locale, CARRIER_LINKS[locale].find((link) => link.id === id)?.slug);
  }

  function inline(nodes: readonly Inline[]): ReactNode {
    return nodes.map((node, index) => {
      switch (node.type) {
        case 'text': return <Fragment key={index}>{withEmails(node.text)}</Fragment>;
        case 'code': return <code key={index}>{withEmails(node.text)}</code>;
        case 'strong': return <strong key={index}>{inline(node.children)}</strong>;
        case 'em': return <em key={index}>{inline(node.children)}</em>;
        case 'guide': return <a key={index} href={guidePath(locale, links.find((link) => link.id === node.id)?.slug)}>{inline(node.children)}</a>;
        case 'carrier': return <a key={index} href={carrierAddress(node.id)}>{inline(node.children)}</a>;
        case 'link': return node.href === '/'
          ? <a key={index} href={languagePath(locale)}>{inline(node.children)}</a>
          : <a key={index} href={node.href} target="_blank" rel="noopener noreferrer">{inline(node.children)}</a>;
      }
    });
  }

  return blocks.map((block, index) => {
    switch (block.type) {
      case 'heading': return block.level === 2
        ? <h2 key={index} id={block.id}>{inline(block.text)}</h2>
        : <h3 key={index} id={block.id}>{inline(block.text)}</h3>;
      case 'paragraph': return <p key={index}>{inline(block.text)}</p>;
      case 'list': {
        const items = block.items.map((item, at) => <li key={at}>{inline(item)}</li>);
        return block.ordered ? <ol key={index}>{items}</ol> : <ul key={index}>{items}</ul>;
      }
      case 'note': return <aside key={index} className="guide-note">{inline(block.text)}</aside>;
      case 'table': return <div key={index} className="guide-table" tabIndex={0}>
        <table>
          <thead><tr>{block.head.map((cell, at) => <th key={at} scope="col">{inline(cell)}</th>)}</tr></thead>
          <tbody>{block.rows.map((row, at) => <tr key={at}>{row.map((cell, column) => column === 0
            ? <th key={column} scope="row">{inline(cell)}</th>
            : <td key={column}>{inline(cell)}</td>)}</tr>)}</tbody>
        </table>
      </div>;
      case 'steps': return <ol key={index} className="guide-steps">
        {block.items.map(({ title, note }, at) => <li key={at}>
          <span className="guide-steps__mark" aria-hidden="true">{at + 1}</span>
          <strong>{inline(title)}</strong>
          <span>{inline(note)}</span>
        </li>)}
      </ol>;
      case 'anatomy': return <figure key={index} className="guide-anatomy">
        {/* The number is read out part by part below; here it is a picture of itself. */}
        <div className="guide-anatomy__number" aria-hidden="true">
          {block.parts.map(({ text }, at) => <span key={at} className={`tone-${TONES[at % TONES.length]}`}>{text}</span>)}
        </div>
        <ol className="guide-anatomy__parts">
          {block.parts.map(({ text, label }, at) => <li key={at} className={`tone-${TONES[at % TONES.length]}`}>
            <code>{text}</code><span>{inline(label)}</span>
          </li>)}
        </ol>
      </figure>;
      case 'journey': return <ol key={index} className="guide-journey">
        {block.stops.map(({ icon, title, note }, at) => <li key={at} className={`tone-${TONES[at % TONES.length]}`}>
          <span className="guide-journey__stop"><JourneyGlyph name={icon} /></span>
          <strong>{inline(title)}</strong>
          <span>{inline(note)}</span>
        </li>)}
      </ol>;
      case 'checker': return <NumberChecker key={index} locale={locale} words={checker} />;
      case 'sources': return <section key={index} className="guide-sources" aria-labelledby="guide-sources">
        <h2 id="guide-sources" lang={language === locale ? undefined : documentLanguage(locale)}>{sources}</h2>
        <ul>{block.items.map((item, at) => <li key={at}>{inline(item)}</li>)}</ul>
      </section>;
    }
  });
}
