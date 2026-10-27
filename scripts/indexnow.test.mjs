import assert from 'node:assert/strict';
import { execFileSync } from 'node:child_process';
import { mkdirSync, mkdtempSync, readdirSync, readFileSync, rmSync, writeFileSync } from 'node:fs';
import { tmpdir } from 'node:os';
import path from 'node:path';
import { after, describe, it } from 'node:test';
import { fileURLToPath } from 'node:url';
import {
  DEFAULT_ENDPOINT, INDEXNOW_KEY, MAX_URLS_PER_REQUEST, decodeXml, frontMatter, pagesToSubmit, parseSitemap, readHeads, readSubmitted,
  requestBodies, retitledPaths, run, submit,
} from './indexnow.mjs';

const origin = 'https://peek.example.test';
const host = 'peek.example.test';
const page = (url, lastmod = null) => ({ url, lastmod });
const sitemap = pages => '<?xml version="1.0" encoding="UTF-8"?>\n'
  + '<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9" xmlns:xhtml="http://www.w3.org/1999/xhtml">\n'
  + pages.map(({ url, lastmod }) => `<url>\n<loc>${url}</loc>\n<xhtml:link rel="alternate" hreflang="de" href="${url}/de" />\n`
    + (lastmod ? `<lastmod>${lastmod}</lastmod>\n` : '') + '</url>\n').join('')
  + '</urlset>\n';
/** Writes a page of `content` below `root`, as content/guides/<folder>/<language>.md. */
const writePage = (root, file, { title, description = 'What it says.', slug }) => {
  mkdirSync(path.dirname(path.join(root, 'content', file)), { recursive: true });
  writeFileSync(path.join(root, 'content', file),
    `---\ntitle: ${title}\ndescription: ${description}\nslug: ${slug}\npublished: 2026-10-01\nupdated: 2026-10-17\n---\n\nWhat the page says.\n`);
};

/** A fetch that serves `xml` as the live sitemap and answers IndexNow with `status`. */
function engine(xml, status = 200) {
  const posts = [];
  const gets = [];
  const fetchImpl = async (url, init = {}) => {
    if (init.method === 'POST') {
      posts.push({ url, body: JSON.parse(init.body) });
      return new Response(null, { status });
    }
    gets.push(url);
    return xml === null ? new Response('gone', { status: 503 }) : new Response(xml, { status: 200 });
  };
  return { fetchImpl, posts, gets };
}

describe('the key', () => {
  it('is served as is from the root, named after itself, where it vouches for every page', () => {
    assert.match(INDEXNOW_KEY, /^[0-9a-f]{32}$/);
    const publicDirectory = new URL('../public/', import.meta.url);
    assert.equal(readFileSync(new URL(`${INDEXNOW_KEY}.txt`, publicDirectory), 'utf8'), INDEXNOW_KEY);
    assert.deepEqual(readdirSync(publicDirectory).filter(name => /^[0-9a-f]{32}\.txt$/.test(name)), [`${INDEXNOW_KEY}.txt`]);
  });

  // proxy.ts leaves it alone and every host answers it itself, like robots.txt (siteHosts.ts).
  it('is left to every host, like the other files written for crawlers', () => {
    const read = file => readFileSync(new URL(`../${file}`, import.meta.url), 'utf8');
    assert.ok(read('proxy.ts').includes('|[0-9a-f]{32}\\\\.txt|'));
    assert.ok(read('src/server/siteHosts.ts').includes('|[0-9a-f]{32}\\.txt|'));
  });
});

describe('reading a sitemap', () => {
  it('reads each page and its date, decoding what XML escapes', () => {
    assert.equal(decodeXml('a&amp;b &lt;&gt; &quot;&apos; &#38;&#x26; &amp;amp;'), 'a&b <> "\' && &amp;');
    assert.deepEqual(parseSitemap(sitemap([
      page('https://peek.example.test/'),
      page('https://peek.example.test/de/guides/r&amp;d?a=1&amp;b=&apos;2&apos;', '2026-10-17'),
    ])), [
      page('https://peek.example.test/'),
      page("https://peek.example.test/de/guides/r&d?a=1&b='2'", '2026-10-17'),
    ]);
    assert.deepEqual(parseSitemap('<html>Bad gateway</html>'), []);
  });
});

describe('choosing the pages', () => {
  const before = [
    page(`${origin}/`),
    page(`${origin}/guides/a`, '2026-10-01'),
    page(`${origin}/guides/b`, '2026-10-01'),
    page(`${origin}/guides/gone`, '2026-10-01'),
  ];
  const afterDeploy = [
    page(`${origin}/`),
    page(`${origin}/guides/a`, '2026-10-01'),
    page(`${origin}/guides/b`, '2026-10-17'),
    page(`${origin}/guides/new`, '2026-10-17'),
    page(`${origin}/carriers/undated`),
    page('https://other.example.test/guides/new', '2026-10-17'),
    page('not an address'),
  ];

  it('submits the pages that are new or redated, on the site\'s host only', () => {
    assert.deepEqual(pagesToSubmit(afterDeploy, before, host), [
      `${origin}/guides/b`, `${origin}/guides/new`, `${origin}/carriers/undated`,
    ]);
  });

  it('submits a page that gains or loses its date, and each page once', () => {
    assert.deepEqual(pagesToSubmit([page(`${origin}/a`, '2026-10-17'), page(`${origin}/b`)], [page(`${origin}/a`), page(`${origin}/b`, '2026-10-01')], host),
      [`${origin}/a`, `${origin}/b`]);
    assert.deepEqual(pagesToSubmit([page(`${origin}/a`), page(`${origin}/a`)], [], host), [`${origin}/a`]);
  });

  it('submits every page of the host without a sitemap to compare with', () => {
    assert.equal(pagesToSubmit(afterDeploy, null, host).length, 5);
  });

  it('submits a page whose title or description changed, though its date did not', () => {
    assert.deepEqual(pagesToSubmit(afterDeploy, before, host, new Set(['/guides/a'])), [
      `${origin}/guides/a`, `${origin}/guides/b`, `${origin}/guides/new`, `${origin}/carriers/undated`,
    ]);
  });
});

describe('reading the titles', () => {
  it('reads the fields of a front matter, and nothing below it', () => {
    assert.deepEqual(frontMatter('---\ntitle: InPost: statuses\ndescription:  Track it. \nslug: inpost\n---\n\ntitle: not this\n'),
      { title: 'InPost: statuses', description: 'Track it.', slug: 'inpost' });
    assert.deepEqual(frontMatter('---\r\ntitle: A\r\n---\r\n'), { title: 'A' });
    assert.deepEqual(frontMatter('# No front matter\ntitle: A\n'), {});
  });

  it('keys each carrier\'s page and guide by its address, leaving other files out', () => {
    const root = mkdtempSync(path.join(tmpdir(), 'indexnow-heads-'));
    after(() => rmSync(root, { recursive: true, force: true }));
    assert.equal(readHeads(root), null);
    writePage(root, 'carriers/inpost/en.md', { title: 'InPost tracking', slug: 'inpost' });
    writePage(root, 'carriers/inpost/es.md', { title: 'InPost seguimiento', description: 'Sigue tu paquete.', slug: 'inpost-seguimiento' });
    writePage(root, 'guides/a/de.md', { title: 'A', slug: 'a-de' });
    writePage(root, 'guides/a/notes.md', { title: 'Notes', slug: 'notes' });
    writeFileSync(path.join(root, 'content/guides/README.md'), '# Guides\n');
    assert.deepEqual(readHeads(root), {
      '/carriers/inpost': 'InPost tracking\nWhat it says.',
      '/de/guides/a-de': 'A\nWhat it says.',
      '/es/carriers/inpost-seguimiento': 'InPost seguimiento\nSigue tu paquete.',
    });
  });

  it('reads a title, a description and an address for every page of the site', () => {
    const root = fileURLToPath(new URL('..', import.meta.url));
    const heads = readHeads(root);
    const files = ['carriers', 'guides'].flatMap(section => readdirSync(path.join(root, 'content', section), { withFileTypes: true })
      .filter(folder => folder.isDirectory())
      .flatMap(folder => readdirSync(path.join(root, 'content', section, folder.name)).filter(file => /^[a-z]{2}\.md$/.test(file))));
    assert.equal(Object.keys(heads).length, files.length);
    for (const [address, head] of Object.entries(heads)) {
      assert.match(address, /^(\/[a-z]{2})?\/(carriers|guides)\/[^/]+$/);
      assert.match(head, /^.+\n.+$/, address);
    }
  });

  it('finds the pages retitled since, leaving out the new and the gone', () => {
    assert.deepEqual([...retitledPaths({ '/a': 'A\nx', '/b': 'B\nx', '/new': 'N\nx' }, { '/a': 'A\nx', '/b': 'B\ny', '/gone': 'G\nx' })], ['/b']);
    assert.deepEqual([...retitledPaths({ '/a': 'A\nx' }, null)], []);
    assert.deepEqual([...retitledPaths(null, { '/a': 'A\nx' })], []);
  });
});

describe('submitting', () => {
  it('sends the pages with the key and where to find it', () => {
    assert.deepEqual(requestBodies([`${origin}/guides/new`], `${origin}`), [{
      host,
      key: INDEXNOW_KEY,
      keyLocation: `${origin}/${INDEXNOW_KEY}.txt`,
      urlList: [`${origin}/guides/new`],
    }]);
  });

  it('sends at most ten thousand pages a request', async () => {
    const urls = Array.from({ length: MAX_URLS_PER_REQUEST * 2 + 1 }, (_, index) => `${origin}/p${index}`);
    assert.deepEqual(requestBodies(urls, origin).map(body => body.urlList.length), [10_000, 10_000, 1]);
    const { fetchImpl, posts } = engine('', 202);
    const lines = [];
    assert.equal(await submit(urls, origin, { fetchImpl, log: line => lines.push(line) }), urls.length);
    assert.deepEqual(posts.map(post => post.url), [DEFAULT_ENDPOINT, DEFAULT_ENDPOINT, DEFAULT_ENDPOINT]);
    assert.deepEqual(posts.flatMap(post => post.body.urlList), urls);
    assert.deepEqual(lines, [10_000, 10_000, 1].map(count => `${DEFAULT_ENDPOINT} answered HTTP 202 to ${count} pages`));
  });

  it('reports a refusal or an unreachable engine instead of throwing, and stops there', async () => {
    const urls = Array.from({ length: MAX_URLS_PER_REQUEST + 1 }, (_, index) => `${origin}/p${index}`);
    const refused = engine('', 403);
    const lines = [];
    assert.equal(await submit(urls, origin, { fetchImpl: refused.fetchImpl, log: line => lines.push(line) }), 0);
    assert.equal(refused.posts.length, 1);
    assert.deepEqual(lines, [`${DEFAULT_ENDPOINT} answered HTTP 403 to 10000 pages`]);

    const offline = async () => { throw new TypeError('fetch failed', { cause: new Error('getaddrinfo ENOTFOUND') }); };
    lines.length = 0;
    assert.equal(await submit(urls, origin, { fetchImpl: offline, log: line => lines.push(line) }), 0);
    assert.deepEqual(lines, [`${DEFAULT_ENDPOINT} could not be reached with 10000 pages: fetch failed: getaddrinfo ENOTFOUND`]);
  });
});

describe('the command', () => {
  const directory = mkdtempSync(path.join(tmpdir(), 'indexnow-'));
  after(() => rmSync(directory, { recursive: true, force: true }));
  const ref = 'refs/indexnow/submitted';
  // A clone whose origin is a bare repository, as the Deploy workflow's checkout of the app's.
  let clones = 0;
  const repository = () => {
    const root = path.join(directory, `repository-${clones += 1}`);
    const clone = path.join(root, 'clone');
    mkdirSync(clone, { recursive: true });
    const git = (...args) => execFileSync('git', args, { cwd: clone, encoding: 'utf8' }).trim();
    execFileSync('git', ['init', '--quiet', '--bare', path.join(root, 'origin.git')]);
    git('init', '--quiet');
    git('config', 'user.name', 'CI');
    git('config', 'user.email', 'ci@example.com');
    git('remote', 'add', 'origin', path.join(root, 'origin.git'));
    const atOrigin = () => git('ls-remote', 'origin', ref).split('\t')[0] || null;
    return { clone, git, atOrigin };
  };
  const live = sitemap([page(`${origin}/`), page(`${origin}/guides/a`, '2026-10-17'), page(`${origin}/guides/new`, '2026-10-17')]);
  const command = async (args, xml = live, status = 200, cwd = directory) => {
    const lines = [];
    const io = engine(xml, status);
    const code = await run(args, { fetchImpl: io.fetchImpl, log: line => lines.push(line), cwd });
    return { code, lines, ...io };
  };

  it('starts the ref at the live sitemap, submitting nothing, where origin has none', async () => {
    const { clone, atOrigin } = repository();
    const dry = await command([`--since=${ref}`, '--dry-run', origin], live, 200, clone);
    assert.deepEqual([dry.code, dry.posts.length, atOrigin()], [0, 0, null]);
    const { code, lines, posts } = await command([`--since=${ref}`, origin], live, 200, clone);
    assert.equal(code, 0);
    assert.equal(posts.length, 0);
    assert.match(lines.join('\n'), /nothing is submitted/);
    assert.equal(readSubmitted(ref, clone)?.xml, live);
  });

  it('submits what changed since the sitemap IndexNow last took, then moves the ref to the live one', async () => {
    const { clone, git, atOrigin } = repository();
    const earlier = sitemap([page(`${origin}/`), page(`${origin}/guides/a`, '2026-10-01'), page(`${origin}/guides/gone`, '2026-10-01')]);
    await command([`--since=${ref}`, origin], earlier, 200, clone);
    const start = atOrigin();
    const { code, lines, gets, posts } = await command([`--since=${ref}`, `${origin}/`], live, 200, clone);
    assert.equal(code, 0);
    assert.deepEqual(gets, [`${origin}/sitemap.xml`]);
    assert.deepEqual(posts.map(post => post.body.urlList), [[`${origin}/guides/a`, `${origin}/guides/new`]]);
    assert.ok(lines.includes(`Submitted 2 of 2 pages to ${DEFAULT_ENDPOINT}.`));
    assert.equal(readSubmitted(ref, clone)?.xml, live);
    assert.equal(git('rev-parse', `${atOrigin()}^`), start);
  });

  it('keeps the ref where it is until IndexNow takes the pages, so a later run sends them', async () => {
    const { clone, atOrigin } = repository();
    await command([`--since=${ref}`, origin], sitemap([page(`${origin}/`)]), 200, clone);
    const start = atOrigin();
    // A refusal, then a deploy that changed nothing: the pages wait for a run IndexNow answers.
    assert.equal((await command([`--since=${ref}`, origin], live, 429, clone)).code, 1);
    assert.equal(atOrigin(), start);
    const { code, posts } = await command([`--since=${ref}`, origin], live, 200, clone);
    assert.equal(code, 0);
    assert.deepEqual(posts.map(post => post.body.urlList), [[`${origin}/guides/a`, `${origin}/guides/new`]]);
    assert.notEqual(atOrigin(), start);
    // Then nothing is new, and the ref stays.
    const moved = atOrigin();
    const again = await command([`--since=${ref}`, origin], live, 200, clone);
    assert.deepEqual([again.code, again.posts.length, again.lines.at(-1), atOrigin()], [0, 0, 'Nothing to submit.', moved]);
  });

  it('submits a page whose title or description changed, and keeps the titles with the ref', async () => {
    const { clone, atOrigin } = repository();
    writePage(clone, 'guides/a/en.md', { title: 'A', slug: 'a' });
    await command([`--since=${ref}`, origin], live, 200, clone);
    assert.deepEqual(readSubmitted(ref, clone)?.heads, { '/guides/a': 'A\nWhat it says.' });
    writePage(clone, 'guides/a/en.md', { title: 'A', description: 'What it says now.', slug: 'a' });
    const { code, lines, posts } = await command([`--since=${ref}`, origin], live, 200, clone);
    assert.equal(code, 0);
    assert.ok(lines.includes(`${origin}/sitemap.xml lists 3 pages, 1 of them new, redated or retitled since ${ref}.`));
    assert.deepEqual(posts.map(post => post.body.urlList), [[`${origin}/guides/a`]]);
    assert.deepEqual(readSubmitted(ref, clone)?.heads, { '/guides/a': 'A\nWhat it says now.' });
    const moved = atOrigin();
    const again = await command([`--since=${ref}`, origin], live, 200, clone);
    assert.deepEqual([again.posts.length, atOrigin()], [0, moved]);
  });

  it('keeps the titles with the ref that lacks them, though nothing is submitted', async () => {
    const { clone, git, atOrigin } = repository();
    await command([`--since=${ref}`, origin], live, 200, clone);
    const start = atOrigin();
    assert.equal(readSubmitted(ref, clone)?.heads, null);
    writePage(clone, 'guides/a/en.md', { title: 'A', slug: 'a' });
    const { code, lines, posts } = await command([`--since=${ref}`, origin], live, 200, clone);
    assert.deepEqual([code, posts.length], [0, 0]);
    assert.ok(lines.includes('Nothing to submit.'));
    assert.deepEqual(readSubmitted(ref, clone), { commit: atOrigin(), xml: live, heads: { '/guides/a': 'A\nWhat it says.' } });
    assert.equal(git('rev-parse', `${atOrigin()}^`), start);
  });

  it('lists the pages without sending them or moving the ref on a dry run', async () => {
    const { clone, atOrigin } = repository();
    await command([`--since=${ref}`, origin], sitemap([page(`${origin}/`)]), 200, clone);
    const start = atOrigin();
    const { code, lines, posts } = await command([`--since=${ref}`, '--dry-run', origin], live, 200, clone);
    assert.equal(code, 0);
    assert.equal(posts.length, 0);
    assert.match(lines.at(-1), /^Dry run: 2 pages would go to .* in 1 requests\. Nothing was sent\.$/);
    assert.equal(atOrigin(), start);
  });

  it('submits every page with --all, to the engine asked', async () => {
    const { code, posts } = await command(['--all', '--endpoint=https://www.bing.com/indexnow', origin]);
    assert.equal(code, 0);
    assert.equal(posts[0].url, 'https://www.bing.com/indexnow');
    assert.equal(posts[0].body.urlList.length, 3);
  });

  it('exits with 1 when IndexNow refuses or the sitemap cannot be read or lists nothing, and 2 when misused', async () => {
    assert.equal((await command(['--all', origin], live, 429)).code, 1);
    assert.equal((await command(['--all', origin], null)).code, 1);
    assert.equal((await command(['--all', origin], '<html>Bad gateway</html>')).code, 1);
    for (const args of [[origin], ['--all', `--since=${ref}`, origin], ['--since=indexnow', origin], ['--all'],
      ['--all', 'peek.example.test'], ['--all', '--endpoint=nowhere', origin], ['--all', '--everything', origin]]) {
      assert.equal((await command(args)).code, 2, args.join(' '));
    }
  });
});
