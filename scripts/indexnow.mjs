// Tells IndexNow which pages were added to the sitemap, redated or retitled since it last took the
// site's pages, so that Bing, whose index most search visitors come through, and the other engines
// that listen read them within days. deploy.yml runs this once a deploy is in:
//
//   node scripts/indexnow.mjs --since=refs/indexnow/submitted https://peek.example.com
//   node scripts/indexnow.mjs --all https://peek.example.com     (every page of the sitemap)
//
// --since names the ref of origin that keeps the sitemap IndexNow last took, and the titles and
// descriptions of the carriers' pages and guides then, read from the checkout's content. A page is
// submitted when that sitemap lacks it or dates it otherwise, or when its title or description
// changed: the sitemap dates a page by the day its facts were last checked, which a new title
// leaves as it was. One that left the sitemap is not. Once IndexNow takes them all, the ref moves
// to the sitemap just read and the titles and descriptions of the checkout, and only then: the
// pages of a deploy that told IndexNow nothing (a refusal, a failed step) go with the next run.
// Where origin has no such ref, nothing is submitted and the ref starts at the live sitemap.
// --dry-run lists the pages, and sends and moves nothing; --endpoint=<url> asks one engine
// directly, such as https://www.bing.com/indexnow. A refusal or an unreachable engine exits with 1,
// which the deploy shows but ignores.
import { execFileSync } from 'node:child_process';
import { existsSync, readdirSync, readFileSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

/**
 * The key that shows IndexNow the site is ours. It is public by design: the file of its name in
 * `public` holds it, at the root, where it vouches for every page.
 */
export const INDEXNOW_KEY = 'dba8d6fc9878f88c622a3c7363b75bed'; // gitleaks:allow -- public by design, served from public/
export const DEFAULT_ENDPOINT = 'https://api.indexnow.org/indexnow';
export const MAX_URLS_PER_REQUEST = 10_000;
const TIMEOUT_MS = 30_000;
const USAGE = 'Usage: node scripts/indexnow.mjs (--since=<ref> | --all) [--dry-run] [--endpoint=<url>] https://peek.example.com';
/** The file of the ref's commits that holds the sitemap. */
const SITEMAP_FILE = 'sitemap.xml';
/** The file of the ref's commits that holds the pages' titles and descriptions. */
const HEADS_FILE = 'heads.json';
/** The folders of `content` whose pages a search result shows by their front matter, each the site's section of the same name. */
const SECTIONS = ['carriers', 'guides'];

const entities = { amp: '&', lt: '<', gt: '>', quot: '"', apos: "'" };

/** XML text as it reads: the five named entities and the character references decoded. */
export function decodeXml(text) {
  return text.replace(/&(?:#x([0-9a-fA-F]+)|#([0-9]+)|(amp|lt|gt|quot|apos));/g, (whole, hex, decimal, name) => {
    if (name) return entities[name];
    const code = hex ? parseInt(hex, 16) : Number(decimal);
    return code <= 0x10ffff ? String.fromCodePoint(code) : whole;
  });
}

/** The pages of a sitemap: each `<url>`'s address, and its `<lastmod>` or null. */
export function parseSitemap(xml) {
  const pages = [];
  for (const [, entry] of xml.matchAll(/<url\b[^>]*>([\s\S]*?)<\/url>/g)) {
    const loc = entry.match(/<loc>([\s\S]*?)<\/loc>/)?.[1];
    if (!loc) continue;
    const lastmod = entry.match(/<lastmod>([\s\S]*?)<\/lastmod>/)?.[1];
    pages.push({ url: decodeXml(loc.trim()), lastmod: lastmod === undefined ? null : decodeXml(lastmod.trim()) });
  }
  return pages;
}

function onHost(address, host) {
  try {
    return new URL(address).host === host;
  } catch {
    return false;
  }
}

/** The fields of a page's front matter: each `name: value` line between its two `---` lines. */
export function frontMatter(text) {
  const block = text.match(/^---\r?\n([\s\S]*?)\r?\n---(?:\r?\n|$)/)?.[1] ?? '';
  return Object.fromEntries(block.split(/\r?\n/).flatMap(line => {
    const field = line.match(/^([a-z]+):\s*(.*?)\s*$/);
    return field ? [[field[1], field[2]]] : [];
  }));
}

/**
 * What a search result shows of each carrier's page and guide in the checkout at `cwd`: its title and
 * description, by the page's path. Null where the checkout has no content.
 */
export function readHeads(cwd) {
  if (!existsSync(path.join(cwd, 'content'))) return null;
  const heads = [];
  for (const section of SECTIONS) {
    const directory = path.join(cwd, 'content', section);
    if (!existsSync(directory)) continue;
    for (const folder of readdirSync(directory, { withFileTypes: true })) {
      if (!folder.isDirectory()) continue;
      for (const file of readdirSync(path.join(directory, folder.name))) {
        const language = file.match(/^([a-z]{2})\.md$/)?.[1];
        if (!language) continue;
        const { slug, title = '', description = '' } = frontMatter(readFileSync(path.join(directory, folder.name, file), 'utf8'));
        if (slug) heads.push([`${language === 'en' ? '' : `/${language}`}/${section}/${slug}`, `${title}\n${description}`]);
      }
    }
  }
  return Object.fromEntries(heads.sort(([a], [b]) => (a < b ? -1 : a > b ? 1 : 0)));
}

/** The paths whose title or description `heads` gives otherwise than `before`; none without both. */
export function retitledPaths(heads, before) {
  if (!heads || !before) return new Set();
  return new Set(Object.keys(heads).filter(page => Object.hasOwn(before, page) && before[page] !== heads[page]));
}

const pathOf = url => new URL(url).pathname;

/**
 * The addresses to submit: the pages of `after` on `host` that `before` lacks or dates
 * otherwise, or whose path is among `retitled`, or all of them without `before`.
 */
export function pagesToSubmit(after, before, host, retitled = new Set()) {
  const dated = before ? new Map(before.map(({ url, lastmod }) => [url, lastmod])) : null;
  const urls = new Set();
  for (const { url, lastmod } of after) {
    if (!onHost(url, host)) continue;
    if (dated?.has(url) && dated.get(url) === lastmod && !retitled.has(pathOf(url))) continue;
    urls.add(url);
  }
  return [...urls];
}

/** What IndexNow is sent: at most 10,000 addresses a request, with the key and where it is. */
export function requestBodies(urls, origin) {
  const { host } = new URL(origin);
  const keyLocation = new URL(`/${INDEXNOW_KEY}.txt`, origin).href;
  const bodies = [];
  for (let start = 0; start < urls.length; start += MAX_URLS_PER_REQUEST) {
    bodies.push({ host, key: INDEXNOW_KEY, keyLocation, urlList: urls.slice(start, start + MAX_URLS_PER_REQUEST) });
  }
  return bodies;
}

const reason = error => (error instanceof Error ? [error.message, error.cause?.message].filter(Boolean).join(': ') : String(error));

/**
 * Submits the addresses and prints the status of each request. Stops at the first refusal or
 * failure, which it reports rather than throws. Returns how many addresses were accepted.
 */
export async function submit(urls, origin, { endpoint = DEFAULT_ENDPOINT, fetchImpl = fetch, log = console.log } = {}) {
  let accepted = 0;
  for (const body of requestBodies(urls, origin)) {
    let response;
    try {
      response = await fetchImpl(endpoint, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json; charset=utf-8' },
        body: JSON.stringify(body),
        signal: AbortSignal.timeout(TIMEOUT_MS),
      });
    } catch (error) {
      log(`${endpoint} could not be reached with ${body.urlList.length} pages: ${reason(error)}`);
      return accepted;
    }
    await response.body?.cancel().catch(() => {});
    log(`${endpoint} answered HTTP ${response.status} to ${body.urlList.length} pages`);
    if (!response.ok) return accepted;
    accepted += body.urlList.length;
  }
  return accepted;
}

async function readSitemap(origin, fetchImpl) {
  const response = await fetchImpl(new URL('/sitemap.xml', origin).href, {
    headers: { 'Cache-Control': 'no-cache' },
    signal: AbortSignal.timeout(TIMEOUT_MS),
  });
  if (!response.ok) throw new Error(`HTTP ${response.status}`);
  return response.text();
}

const git = (cwd, args, input = '') => execFileSync('git', args, { cwd, input, encoding: 'utf8', stdio: ['pipe', 'pipe', 'pipe'] });

/**
 * The sitemap IndexNow last took, as `ref` of origin keeps it: the ref's commit, the sitemap's text
 * and the pages' titles and descriptions then (null where the ref kept none), or null where origin
 * has no such ref.
 */
export function readSubmitted(ref, cwd) {
  if (!git(cwd, ['ls-remote', 'origin', ref]).trim()) return null;
  git(cwd, ['fetch', '--quiet', '--no-tags', 'origin', `+${ref}:${ref}`]);
  const files = git(cwd, ['ls-tree', '--name-only', ref]).split('\n');
  return {
    commit: git(cwd, ['rev-parse', ref]).trim(),
    xml: git(cwd, ['show', `${ref}:${SITEMAP_FILE}`]),
    heads: files.includes(HEADS_FILE) ? JSON.parse(git(cwd, ['show', `${ref}:${HEADS_FILE}`])) : null,
  };
}

/**
 * Moves `ref` of origin to a commit of the sitemap `xml`, and of the pages' `heads` where there are
 * any, that follows `parent`, the commit it was read at. The push is not forced, so it fails if
 * another run moved the ref since.
 */
export function recordSubmitted(ref, { xml, heads = null, parent, message }, cwd) {
  const entry = (file, text) => `100644 blob ${git(cwd, ['hash-object', '-w', '--stdin'], text).trim()}\t${file}\n`;
  const tree = git(cwd, ['mktree'], (heads ? entry(HEADS_FILE, `${JSON.stringify(heads, null, 1)}\n`) : '') + entry(SITEMAP_FILE, xml)).trim();
  const commit = git(cwd, ['commit-tree', tree, ...(parent ? ['-p', parent] : []), '-m', message]).trim();
  git(cwd, ['push', '--quiet', 'origin', `${commit}:${ref}`]);
  return commit;
}

function httpOrigin(address) {
  try {
    const url = new URL(address);
    return url.protocol === 'https:' || url.protocol === 'http:' ? url.origin : null;
  } catch {
    return null;
  }
}

/**
 * Runs the command line `args` and returns its exit code. `cwd` is the clone of the repository
 * whose origin keeps the ref of --since.
 */
export async function run(args, { fetchImpl = fetch, log = console.log, cwd = process.cwd() } = {}) {
  const flags = new Set();
  const values = new Map();
  const positional = [];
  for (const argument of args) {
    const option = argument.match(/^--(?:(all|dry-run)|(since|endpoint)=(.+))$/);
    if (option?.[1]) flags.add(option[1]);
    else if (option) values.set(option[2], option[3]);
    else positional.push(argument);
  }
  const origin = positional.length === 1 && !positional[0].startsWith('--') ? httpOrigin(positional[0]) : null;
  const since = values.get('since');
  const endpoint = values.get('endpoint') ?? DEFAULT_ENDPOINT;
  if (!origin || !httpOrigin(endpoint) || flags.has('all') === (since !== undefined) || (since !== undefined && !since.startsWith('refs/'))) {
    log(USAGE);
    return 2;
  }
  const dryRun = flags.has('dry-run');

  let submitted = null;
  if (since !== undefined) {
    try {
      submitted = readSubmitted(since, cwd);
    } catch (error) {
      log(`${since} could not be read from origin: ${reason(error)}`);
      return 1;
    }
  }
  let xml;
  try {
    xml = await readSitemap(origin, fetchImpl);
  } catch (error) {
    log(`${origin}/sitemap.xml could not be read: ${reason(error)}`);
    return 1;
  }
  const pages = parseSitemap(xml);
  if (pages.length === 0) {
    log(`${origin}/sitemap.xml lists no page: nothing is submitted.`);
    return 1;
  }
  const heads = since === undefined ? null : readHeads(cwd);
  /** Moves the ref of --since to the live sitemap and the checkout's titles, and says whether it could. */
  const record = message => {
    if (since === undefined || dryRun) return true;
    try {
      recordSubmitted(since, { xml, heads, parent: submitted?.commit ?? null, message }, cwd);
      log(`${since} now holds the live sitemap${heads ? ' and the titles and descriptions of the checkout' : ''}.`);
      return true;
    } catch (error) {
      log(`${since} could not be moved: ${reason(error)}`);
      return false;
    }
  };
  if (since !== undefined && !submitted) {
    log(`Origin has no ${since}: nothing is submitted, and ${dryRun ? 'it would start' : 'it starts'} at the live sitemap,`
      + ' which the next run compares with. --all submits every page.');
    return record(`Start at the sitemap of ${pages.length} pages`) ? 0 : 1;
  }

  const { host } = new URL(origin);
  const urls = pagesToSubmit(pages, submitted && parseSitemap(submitted.xml), host, retitledPaths(heads, submitted?.heads));
  const elsewhere = pages.filter(page => !onHost(page.url, host)).length;
  log(`${origin}/sitemap.xml lists ${pages.length} pages, ${urls.length} of them ${submitted ? `new, redated or retitled since ${since}` : `on ${host}`}.`
    + (elsewhere ? ` ${elsewhere} on another host are left out.` : ''));
  if (urls.length === 0) {
    log('Nothing to submit.');
    // Titles the ref lacks, or that changed on pages the sitemap leaves out, are kept all the same.
    const newHeads = heads && JSON.stringify(heads) !== JSON.stringify(submitted?.heads ?? null);
    return !newHeads || record('Keep the titles and descriptions of the pages') ? 0 : 1;
  }
  if (dryRun) {
    for (const url of urls) log(url);
    log(`Dry run: ${urls.length} pages would go to ${endpoint} in ${requestBodies(urls, origin).length} requests. Nothing was sent.`);
    return 0;
  }
  const accepted = await submit(urls, origin, { endpoint, fetchImpl, log });
  log(`Submitted ${accepted} of ${urls.length} pages to ${endpoint}.`);
  if (accepted < urls.length) return 1;
  return record(`Submit ${urls.length} pages to ${endpoint}`) ? 0 : 1;
}

if (process.argv[1] && path.resolve(process.argv[1]) === fileURLToPath(import.meta.url)) {
  process.exitCode = await run(process.argv.slice(2));
}
