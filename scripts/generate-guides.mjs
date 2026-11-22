import { existsSync, readdirSync, readFileSync, writeFileSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { CARRIER_CATALOG } from 'universal-parcel-scraper';

import { GUIDE_LANGUAGES, interfaceLanguage, isPageOnlyLanguage, PAGE_LANGUAGES, SUPPORTED_LOCALES } from '../src/lib/locale.ts';
import {
  figureShape, GUIDE_SLUG, linkedCarriers, linkedGuides, parseCarrierText, parseGuide, questions, typeset, wordCount,
} from '../src/guides/markdown.ts';

const root = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const contentRoot = path.join(root, 'content', 'guides');
const carriersRoot = path.join(root, 'content', 'carriers');
const picturesRoot = path.join(root, 'public', 'guide-pictures');
const outputPath = path.join(root, 'src', 'generated', 'guides.ts');
const carriersOutputPath = path.join(root, 'src', 'generated', 'carriers.ts');

/** What a search result shows before it cuts the text, and how much a page holds to be worth finding. */
const LIMITS = { title: 60, description: [110, 165], words: 600, translated: [.6, 1.6], questions: [3, 5] };
/** Chinese characters are about twice as wide as Latin letters: a search result shows about half as many. */
const CHINESE_DESCRIPTION = [60, 85];

const INLINE_TYPES = new Set(['text', 'strong', 'em', 'code', 'link', 'guide', 'carrier', 'section']);
/** Every run of inline text in a page's blocks: paragraphs, list items, table cells, notes, steps and sources. */
function inlineRuns(value, runs = []) {
  if (Array.isArray(value) && value.length && value.every((node) => INLINE_TYPES.has(node?.type))) runs.push(value);
  else if (Array.isArray(value)) for (const item of value) inlineRuns(item, runs);
  else if (value && typeof value === 'object') for (const item of Object.values(value)) inlineRuns(item, runs);
  return runs;
}
/** A run as its Markdown reads, bold kept and code left out, to see a list marker in it. */
const markdownOf = (nodes) => nodes.map((node) => node.type === 'text' ? node.text : node.type === 'code' ? '``' :
  node.type === 'strong' ? `**${markdownOf(node.children)}**` : node.type === 'em' ? `_${markdownOf(node.children)}_` :
    markdownOf(node.children)).join('');
/**
 * A list item glued to the end of the line before it, as when an edit loses the line break between two bullets
 * ("…mais IVA.- **CTT, Encomenda Expresso:** …"): the page would show the marker and the item inside the previous one.
 */
const GLUED_ITEM = /[.!?:;)»”"\]]-[ \t]+\S|\S[ \t]+[-*+][ \t]+\*\*[^*]+\*\*|[.!?:;)»”"]\d{1,2}\.[ \t]+\*\*/;

/** What a guide and a carrier's page share: a title and a description a search result shows whole, and its sources last. */
function searchable(text, where, kind, language) {
  for (const run of inlineRuns(text.blocks)) {
    const markdown = markdownOf(run), glued = GLUED_ITEM.exec(markdown);
    if (glued) {
      const around = markdown.slice(Math.max(0, glued.index - 30), glued.index + glued[0].length + 30);
      throw new Error(`${where}: a list item starts in the middle of a line ("…${around}…"): put it on a line of its own`);
    }
  }
  for (const field of ['title', 'description']) {
    if (/^(["']).*\1$/s.test(text[field])) throw new Error(`${where}: the ${field} is wrapped in quotes, which the page would show; write it bare`);
  }
  if (text.title.length > LIMITS.title) throw new Error(`${where}: the title has ${text.title.length} characters; a search result shows about ${LIMITS.title}`);
  const [shortest, longest] = language === 'zh' ? CHINESE_DESCRIPTION : LIMITS.description;
  if (text.description.length < shortest || text.description.length > longest) {
    throw new Error(`${where}: the description has ${text.description.length} characters; write ${shortest} to ${longest}`);
  }
  if (text.blocks.filter((block) => block.type === 'sources').length !== 1 || text.blocks.at(-1).type !== 'sources') {
    throw new Error(`${where}: a ${kind} ends with its ":::sources"`);
  }
  return text;
}

/** A picture's width and height, read from its WebP header (lossy, lossless or extended), or null for any other file. */
export function webpSize(bytes) {
  if (bytes.length < 30 || bytes.toString('latin1', 0, 4) !== 'RIFF' || bytes.toString('latin1', 8, 12) !== 'WEBP') return null;
  const chunk = bytes.toString('latin1', 12, 16);
  if (chunk === 'VP8 ') return { width: bytes.readUInt16LE(26) & 0x3fff, height: bytes.readUInt16LE(28) & 0x3fff };
  if (chunk === 'VP8L') {
    const bits = bytes.readUInt32LE(21);
    return { width: (bits & 0x3fff) + 1, height: ((bits >> 14) & 0x3fff) + 1 };
  }
  if (chunk === 'VP8X') return { width: bytes.readUIntLE(24, 3) + 1, height: bytes.readUIntLE(27, 3) + 1 };
  return null;
}

/** The most a picture weighs: a redrawn screenshot in WebP is a few dozen kilobytes. */
const PICTURE_BYTES = 150_000;

/**
 * Each picture a page shows: a WebP file in its own folder of `public/guide-pictures` (`<id>/<name>.webp`), light,
 * and as wide and high as the page says, which keeps the text from jumping while it loads. Twice as large is fine:
 * a sharp screen draws it at half its size.
 */
function checkPictures(text, where, id, directory = picturesRoot) {
  for (const block of text.blocks) {
    if (block.type !== 'picture') continue;
    if (!block.file.startsWith(`${id}/`)) throw new Error(`${where}: its picture "${block.file}" belongs in public/guide-pictures/${id}/`);
    const file = path.join(directory, block.file);
    if (!existsSync(file)) throw new Error(`${where}: public/guide-pictures/${block.file} is missing`);
    const bytes = readFileSync(file);
    if (bytes.length > PICTURE_BYTES) throw new Error(`${where}: public/guide-pictures/${block.file} weighs ${bytes.length} bytes; keep a picture under ${PICTURE_BYTES}`);
    const size = webpSize(bytes);
    if (!size) throw new Error(`${where}: public/guide-pictures/${block.file} is not a WebP picture`);
    const scale = size.width / block.width;
    if (![1, 2].includes(scale) || size.height !== block.height * scale) {
      throw new Error(`${where}: public/guide-pictures/${block.file} is ${size.width}x${size.height}; the page says ${block.width}x${block.height} (the same, or half)`);
    }
  }
}

/** A translation as long as its English page, give or take: shorter or longer, a part is missing or doubled. */
function translated(text, english, where, kind) {
  const ratio = wordCount(text.blocks) / wordCount(english.blocks);
  if (ratio < LIMITS.translated[0] || ratio > LIMITS.translated[1]) {
    throw new Error(`${where} has ${wordCount(text.blocks)} words where the English ${kind} has ${wordCount(english.blocks)}: a part is missing or doubled`);
  }
}

/**
 * Every guide in every language it is written in (`GUIDE_LANGUAGES`), read and checked: the list in `index.json`
 * names the guides in the order the site shows them, and each has one file per
 * language. A guide that would publish half-made stops the build here. A guide
 * links a carrier's page only in a language the carrier is written in.
 */
export function readGuides(directory = contentRoot, carriers = readCarrierIndex(), pictures = picturesRoot) {
  const ids = JSON.parse(readFileSync(path.join(directory, 'index.json'), 'utf8'));
  if (!Array.isArray(ids) || new Set(ids).size !== ids.length) throw new Error('content/guides/index.json lists each guide once');
  // An id names the guide's screen in the analytics and its `guide:` links.
  for (const id of ids) if (typeof id !== 'string' || !GUIDE_SLUG.test(id)) throw new Error(`content/guides/index.json: ${JSON.stringify(id)} is not an id: lowercase letters, digits and hyphens`);
  const folders = readdirSync(directory, { withFileTypes: true }).filter((entry) => entry.isDirectory()).map((entry) => entry.name);
  for (const folder of folders) if (!ids.includes(folder)) throw new Error(`content/guides/${folder} is not listed in index.json`);

  const guides = ids.map((id) => {
    const languages = Object.fromEntries(GUIDE_LANGUAGES.map((locale) => {
      const file = path.join(directory, id, `${locale}.md`);
      const where = `content/guides/${id}/${locale}.md`;
      if (!existsSync(file)) throw new Error(`${where} is missing: a guide is written in every language`);
      const guide = searchable(parseGuide(readFileSync(file, 'utf8'), where), where, 'guide', locale);
      checkPictures(guide, where, id, pictures);
      for (const linked of linkedGuides(guide.blocks)) {
        if (linked === id || !ids.includes(linked)) throw new Error(`${where}: "guide:${linked}" leads nowhere`);
      }
      for (const linked of linkedCarriers(guide.blocks)) {
        if (!carriers.find((carrier) => carrier.id === linked)?.languages.includes(locale)) throw new Error(`${where}: "carrier:${linked}" leads to no carrier's page in this language`);
      }
      // The landing asks a checker's carrier first, which only a carrier Peek asks by itself can be.
      for (const block of guide.blocks) {
        if (block.type === 'checker' && block.carrier && (!Object.hasOwn(CARRIER_CATALOG, block.carrier) || CARRIER_CATALOG[block.carrier].tracking?.mode !== 'automatic')) {
          throw new Error(`${where}: ":::checker ${block.carrier}" names no carrier Peek tracks by itself`);
        }
      }
      return [locale, guide];
    }));
    const english = languages.en;
    const words = wordCount(english.blocks);
    if (words < LIMITS.words) throw new Error(`content/guides/${id}/en.md has ${words} words; a guide worth finding has at least ${LIMITS.words}`);
    for (const locale of GUIDE_LANGUAGES) {
      const where = `content/guides/${id}/${locale}.md`;
      const guide = languages[locale];
      if (figureShape(guide.blocks).join('\n') !== figureShape(english.blocks).join('\n')) {
        throw new Error(`${where}: its steps, journeys and numbers differ from the English guide's:\n${figureShape(guide.blocks).join('\n')}\n— against —\n${figureShape(english.blocks).join('\n')}`);
      }
      translated(guide, english, where, 'guide');
    }
    return { id, languages };
  });

  for (const locale of GUIDE_LANGUAGES) {
    const slugs = guides.map(({ languages }) => languages[locale].slug);
    const twice = slugs.find((slug, index) => slugs.indexOf(slug) !== index);
    if (twice) throw new Error(`Two ${locale} guides share the address "${twice}"`);
  }
  return guides;
}

/**
 * The carriers that have a page, in the order the site shows them: each its id, its id in Universal Parcel
 * Scraper's catalog, the name its pages use, and the languages it is written in, English always among them:
 * the site's own, and the languages with pages alone (`PAGE_ONLY_LANGUAGES`). Each language has its file in
 * the carrier's folder, and nothing else stands there.
 */
export function readCarrierIndex(directory = carriersRoot) {
  const where = 'content/carriers/index.json';
  const carriers = JSON.parse(readFileSync(path.join(directory, 'index.json'), 'utf8'));
  if (!Array.isArray(carriers)) throw new Error(`${where} lists the carriers`);
  for (const carrier of carriers) {
    const { id, catalog, name, languages } = carrier ?? {};
    if (Object.keys(carrier ?? {}).sort().join() !== 'catalog,id,languages,name') throw new Error(`${where}: ${JSON.stringify(carrier)} is a carrier's id, catalog, name and languages`);
    // An id names the page's screen in the analytics and its `carrier:` links.
    if (typeof id !== 'string' || !GUIDE_SLUG.test(id)) throw new Error(`${where}: ${JSON.stringify(id)} is not an id: lowercase letters, digits and hyphens`);
    if (typeof catalog !== 'string' || !Object.hasOwn(CARRIER_CATALOG, catalog)) throw new Error(`${where}: ${id}'s catalog ${JSON.stringify(catalog)} is no carrier of Universal Parcel Scraper's catalog`);
    if (typeof name !== 'string' || !name.trim()) throw new Error(`${where}: ${id} has no name`);
    if (!Array.isArray(languages) || !languages.includes('en') || new Set(languages).size !== languages.length || languages.some((language) => !PAGE_LANGUAGES.includes(language))) {
      throw new Error(`${where}: ${id} is written in English and in other languages a page can be written in, each named once, not ${JSON.stringify(languages)}`);
    }
  }
  const ids = carriers.map(({ id }) => id);
  if (new Set(ids).size !== ids.length) throw new Error(`${where} lists each carrier once`);
  // A file the list does not name would never be published. What a Mac leaves in a folder is no file of the site.
  const listed = (folder) => readdirSync(folder, { withFileTypes: true }).filter((entry) => !entry.name.startsWith('.'));
  for (const entry of listed(directory)) {
    if (entry.isFile() ? !['index.json', 'README.md'].includes(entry.name) : !ids.includes(entry.name)) throw new Error(`content/carriers/${entry.name} is not listed in index.json`);
  }
  for (const { id, languages } of carriers) {
    for (const locale of languages) {
      if (!existsSync(path.join(directory, id, `${locale}.md`))) throw new Error(`content/carriers/${id}/${locale}.md is missing: index.json lists ${id} in ${locale}`);
    }
    for (const entry of listed(path.join(directory, id))) {
      if (!entry.isFile() || !languages.some((locale) => entry.name === `${locale}.md`)) throw new Error(`content/carriers/${id}/${entry.name} is not one of the languages index.json lists for ${id}`);
    }
  }
  return carriers;
}

/** The mark a question ends with in a language: Greek asks with ";", Chinese with the full-width "？". */
export function questionMark(language) {
  if (language === 'el') return ';';
  return language === 'zh' ? '？' : '?';
}

/**
 * Every carrier's page in each of its languages, read and checked as the guides are, and its questions too:
 * a page that would publish half-made, link nowhere or ask what it does not answer stops the build here.
 * A page in a language with pages alone links a carrier written in its language or, failing that, in the
 * language around it. Each carrier comes with its brand colour from the scraper's catalog.
 */
export function readCarriers(directory = carriersRoot, guides = JSON.parse(readFileSync(path.join(contentRoot, 'index.json'), 'utf8'))) {
  const index = readCarrierIndex(directory);
  const carriers = index.map((carrier) => {
    const pages = Object.fromEntries(carrier.languages.map((locale) => {
      const where = `content/carriers/${carrier.id}/${locale}.md`;
      const page = searchable(parseCarrierText(readFileSync(path.join(directory, carrier.id, `${locale}.md`), 'utf8'), where), where, 'carrier page', locale);
      checkPictures(page, where, carrier.id);
      const asked = questions(page.blocks);
      const [fewest, most] = LIMITS.questions;
      if (asked.length < fewest || asked.length > most) {
        throw new Error(`${where}: its last section asks ${asked.length} questions, each a "### " heading; ask ${fewest} to ${most}`);
      }
      const mark = questionMark(locale);
      for (const { question, answer } of asked) {
        if (!question.endsWith(mark)) throw new Error(`${where}: "${question}" stands among the questions, and ends with "${mark}"`);
        if (!answer.length) throw new Error(`${where}: "${question}" has no answer below it`);
      }
      for (const linked of linkedGuides(page.blocks)) if (!guides.includes(linked)) throw new Error(`${where}: "guide:${linked}" leads nowhere`);
      for (const linked of linkedCarriers(page.blocks)) {
        const languages = index.find(({ id }) => id === linked)?.languages ?? [];
        if (linked === carrier.id || !(languages.includes(locale) || (isPageOnlyLanguage(locale) && languages.includes(interfaceLanguage(locale))))) {
          throw new Error(`${where}: "carrier:${linked}" leads to no other carrier's page in this language`);
        }
      }
      return [locale, page];
    }));
    const words = wordCount(pages.en.blocks);
    if (words < LIMITS.words) throw new Error(`content/carriers/${carrier.id}/en.md has ${words} words; a page worth finding has at least ${LIMITS.words}`);
    for (const locale of carrier.languages) translated(pages[locale], pages.en, `content/carriers/${carrier.id}/${locale}.md`, 'page');
    const { color } = CARRIER_CATALOG[carrier.catalog];
    if (!/^#[0-9a-f]{6}$/i.test(color ?? '')) throw new Error(`content/carriers/index.json: ${carrier.catalog} has no brand colour in Universal Parcel Scraper's catalog`);
    return { ...carrier, color, pages };
  });

  for (const locale of PAGE_LANGUAGES) {
    const slugs = carriers.filter(({ pages }) => pages[locale]).map(({ pages }) => pages[locale].slug);
    const twice = slugs.find((slug, index) => slugs.indexOf(slug) !== index);
    if (twice) throw new Error(`Two ${locale} carrier pages share the address "${twice}"`);
  }
  return carriers;
}

/**
 * The lines that hold only until a date, listed in `content/dated.json`: each names its page under `content/`,
 * words the page says, the last day they hold and what to do then. Once that day has passed (UTC), a page that
 * still says them stops the build; so does an entry whose page no longer says them.
 */
export function checkDated(list = JSON.parse(readFileSync(path.join(root, 'content', 'dated.json'), 'utf8')), directory = path.join(root, 'content'), today = new Date().toISOString().slice(0, 10)) {
  if (!Array.isArray(list)) throw new Error('content/dated.json lists the dated lines');
  for (const entry of list) {
    const { page, line, until, then } = entry ?? {};
    if (Object.keys(entry ?? {}).sort().join() !== 'line,page,then,until' || !/^(guides|carriers)\/[a-z0-9-]+\/[a-z-]+\.md$/.test(page ?? '')
      || [line, then].some((value) => typeof value !== 'string' || !value.trim()) || !/^\d{4}-\d{2}-\d{2}$/.test(until ?? '')) {
      throw new Error(`content/dated.json: ${JSON.stringify(entry)} is a page, words on it, the last day they hold (YYYY-MM-DD) and what to do then`);
    }
    const file = path.join(directory, page);
    if (!existsSync(file) || !readFileSync(file, 'utf8').includes(line)) throw new Error(`content/${page} no longer says "${line}": update or remove its entry in content/dated.json`);
    if (today > until) throw new Error(`content/${page} still says "${line}", which held until ${until}. ${then} Then remove its entry from content/dated.json.`);
  }
}

/** A list as module text. No-break spaces are written as their escapes, to be seen. */
const written = (value) => JSON.stringify(value, null, 2).replace(/\u202f/g, '\\u202f').replace(/\u00a0/g, '\\u00a0');

/**
 * What the app needs of the guides without reading their files: where each one lives and what it is
 * called, set as its language sets it.
 */
export function guidesModule(guides) {
  const links = Object.fromEntries(GUIDE_LANGUAGES.map((locale) => [
    locale,
    guides.map(({ id, languages }) => ({ id, slug: languages[locale].slug, title: typeset(languages[locale], locale).title })),
  ]));
  return `// Generated from content/guides by scripts/generate-guides.mjs. Do not edit.
import type { GuideLanguage } from '../lib/locale';

/** A guide in one language: its id in every language, and its address and title in this one. */
export interface GuideLink { id: string; slug: string; title: string }

export const GUIDE_LINKS: Record<GuideLanguage, readonly GuideLink[]> = ${written(links)};
`;
}

/**
 * What the app needs of the carriers' pages without reading their files: in each language a page can be
 * written in, the carriers written in it, where each page lives and what it is called, set as its language
 * sets it; and Peek's languages a carrier's page is written in, which have the carriers' own page.
 */
export function carriersModule(carriers) {
  const links = Object.fromEntries(PAGE_LANGUAGES.map((locale) => [
    locale,
    carriers.filter(({ pages }) => pages[locale]).map(({ id, catalog, name, color, pages }) => ({ id, catalog, slug: pages[locale].slug, title: typeset(pages[locale], locale).title, name, color })),
  ]));
  const listed = SUPPORTED_LOCALES.filter((locale) => links[locale].length > 0);
  return `// Generated from content/carriers by scripts/generate-guides.mjs. Do not edit.
import type { Locale, PageLanguage } from '../lib/locale';

/**
 * A carrier's page in one language: the carrier's id, its id in Universal Parcel Scraper's catalog, its name
 * and brand colour in every language, and the page's address and title in this one.
 */
export interface CarrierLink { id: string; catalog: string; slug: string; title: string; name: string; color: string }

export const CARRIER_LINKS: Record<PageLanguage, readonly CarrierLink[]> = ${written(links)};

/**
 * Peek's languages a carrier's page is written in. Each has its carriers' own page; a language without one
 * sends its readers to the English one until a carrier's page is written in it.
 */
export const CARRIER_LOCALES: readonly Locale[] = ${JSON.stringify(listed)};
`;
}

if (process.argv[1] && path.resolve(process.argv[1]) === fileURLToPath(import.meta.url)) {
  checkDated();
  const carriers = readCarriers();
  const outputs = [[outputPath, guidesModule(readGuides(contentRoot, carriers))], [carriersOutputPath, carriersModule(carriers)]];
  if (process.argv.includes('--check')) {
    for (const [output, expected] of outputs) {
      if (!existsSync(output) || readFileSync(output, 'utf8') !== expected) throw new Error(`${path.relative(root, output)} is stale. Run npm run guides.`);
    }
    console.log('The guides and the carriers’ pages are complete and their lists are current.');
  } else {
    for (const [output, expected] of outputs) {
      writeFileSync(output, expected);
      console.log(`Wrote ${path.relative(root, output)}`);
    }
  }
}
