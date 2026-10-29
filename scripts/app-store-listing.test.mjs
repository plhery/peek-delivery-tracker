import assert from 'node:assert/strict';
import { generateKeyPairSync } from 'node:crypto';
import { readFileSync } from 'node:fs';
import { describe, it } from 'node:test';
import { decodeJwt, decodeProtectedHeader } from 'jose';
import {
  BUNDLE_ID, checkLimits, checkWords, createApi, loadListings, parseListing, reviewNotes, syncListing, tokenSigner,
} from './app-store-listing.mjs';

const listings = loadListings();
const notes = reviewNotes(readFileSync(new URL('../docs/app-store/README.md', import.meta.url), 'utf8'));
const english = parseListing(readFileSync(new URL('../docs/app-store/listing/en-GB.md', import.meta.url), 'utf8'), 'en-GB.md');
const LOCALES = ['de-DE', 'en-GB', 'en-US', 'es-ES', 'fr-FR', 'it', 'pl', 'pt-PT'];

describe('the listing files', () => {
  it('give each of the seven languages every field', () => {
    for (const listing of listings) {
      for (const field of ['name', 'subtitle', 'promotionalText', 'description', 'keywords', 'whatsNew']) {
        assert.ok(listing[field].trim(), `${listing.locale} ${field}`);
        assert.doesNotMatch(listing[field], /```/, `${listing.locale} ${field}`);
      }
      assert.doesNotMatch(listing.keywords, /\s/, listing.locale);
    }
    assert.equal(english.name, 'Peek: Parcel Tracker');
    assert.match(english.description, /^Where’s my parcel\?[\s\S]+Their names belong to their owners\.$/);
  });

  it('serve the App Store locales: parcels in the U.K., packages in the U.S.', () => {
    assert.deepEqual(listings.map(listing => listing.locale), LOCALES);
    const [gb, us] = ['en-GB', 'en-US'].map(locale => listings.find(listing => listing.locale === locale));
    assert.deepEqual({ ...gb, locale: undefined }, { ...english, locale: undefined });
    assert.equal(us.name, 'Peek: Package Tracker');
    for (const field of ['promotionalText', 'description']) {
      assert.doesNotMatch(gb[field], /package/i, `en-GB ${field}`);
      assert.doesNotMatch(us[field], /parcel/i, `en-US ${field}`);
    }
    assert.match(listings.find(listing => listing.locale === 'pt-PT').name, /encomendas/);
  });

  it('use each word once across the name, subtitle and keywords', () => {
    assert.throws(() => checkWords({ ...english, keywords: `parcel,${english.keywords}` }, 'en-GB.md'),
      /^Error: en-GB\.md: "parcel" is repeated across the name, subtitle and keywords$/);
    assert.throws(() => checkWords({ ...english, subtitle: 'Any Carrier, any courier' }, 'en-GB.md'), /"any" is repeated/);
  });

  it('fail, naming the file and field, past App Store Connect limits', () => {
    assert.throws(() => checkLimits({ ...english, subtitle: 'Package tracking without an account' }, 'en-GB.md'),
      /^Error: en-GB\.md: Subtitle has 35 characters; App Store Connect takes up to 30$/);
    assert.throws(() => checkLimits({ ...english, name: 'P' }, 'en-GB.md'), /en-GB\.md: Name has 1 characters/);
    // 95 characters, but each accented letter takes two bytes.
    const keywords = 'expédition,numéro,arrivée,étiquette,réception,dépôt,livré,colis,douane,où,mise,à,jour,été,prévu';
    assert.ok([...keywords].length <= 100);
    assert.throws(() => checkLimits({ ...english, keywords }, 'fr.md'), /^Error: fr\.md: Keywords take 108 bytes/);
    assert.throws(() => parseListing('## Name\n\n```text\nPeek\n```\n', 'xx.md'), /xx\.md: no ```text block under "## Subtitle"/);
  });

  it('come with review notes that end on the demo and real tracking numbers', () => {
    assert.match(notes, /^Peek follows parcels[\s\S]+\nDemo mode: [^\n]+Any real tracking number also works\.$/);
  });
});

describe('an API token', () => {
  it('is an ES256 JWT for App Store Connect that lasts at most 20 minutes', async () => {
    const { privateKey } = generateKeyPairSync('ec', { namedCurve: 'P-256' });
    const sign = await tokenSigner({ keyId: 'TESTKEY000', issuer: '00000000-0000-0000-0000-000000000000',
      privateKey: privateKey.export({ type: 'pkcs8', format: 'pem' }) });
    const token = await sign();
    assert.deepEqual(decodeProtectedHeader(token), { alg: 'ES256', kid: 'TESTKEY000', typ: 'JWT' });
    const claims = decodeJwt(token);
    assert.equal(claims.iss, '00000000-0000-0000-0000-000000000000');
    assert.equal(claims.aud, 'appstoreconnect-v1');
    assert.ok(claims.exp - claims.iat <= 20 * 60);
  });
});

const app = { type: 'apps', id: 'app-1', attributes: { bundleId: BUNDLE_ID } };
const version = (id, versionString, state) => ({ type: 'appStoreVersions', id,
  attributes: { platform: 'IOS', versionString, appVersionState: state, appStoreState: state, copyright: null } });
const routes = {
  '/v1/apps': [app],
  '/v1/apps/app-1/appInfos': [{ type: 'appInfos', id: 'info-1', attributes: { state: 'PREPARE_FOR_SUBMISSION' },
    relationships: { primaryCategory: { data: null } } }],
  '/v1/appInfos/info-1/appInfoLocalizations': [{ type: 'appInfoLocalizations', id: 'info-en-gb',
    attributes: { locale: 'en-GB', name: 'Peek', subtitle: null, privacyPolicyUrl: null } }],
  '/v1/appInfos/info-1/ageRatingDeclaration': { type: 'ageRatingDeclarations', id: 'rating-1', attributes: {
    violenceRealistic: null, gambling: null, messagingAndChat: true, contests: 'NONE', seventeenPlus: null,
    kidsAgeBand: null, ageRatingOverride: 'NONE', koreaAgeRatingOverride: 'NONE' } },
  '/v1/apps/app-1/appStoreVersions': [version('version-1', '1.0', 'PREPARE_FOR_SUBMISSION')],
  '/v1/appStoreVersions/version-1/appStoreVersionLocalizations': [],
};

/** A fetch that answers GETs from `canned` (404 for the rest) and writes with `write`. */
function fakeApi(canned, write = () => new Response('{"data":{}}', { status: 201 })) {
  const calls = [];
  const api = createApi(async () => 'test-token', async (url, init) => {
    const { pathname, search } = new URL(url);
    calls.push({ method: init.method, path: `${pathname}${decodeURIComponent(search)}`, authorization: init.headers.authorization });
    if (init.method !== 'GET') return write();
    return pathname in canned
      ? new Response(JSON.stringify({ data: canned[pathname] }))
      : new Response('{"errors":[{"status":"404","detail":"Not found."}]}', { status: 404 });
  });
  return { api, calls };
}

const sync = (api, options = {}) => syncListing({ api, listings, notes, log: () => {}, ...options });
const requests = writes => writes.map(({ method, path }) => `${method} ${path}`);

describe('a dry run', () => {
  it('plans the listing for a first version with only GET requests', async () => {
    const { api, calls } = fakeApi(routes);
    const writes = await sync(api);
    assert.deepEqual(requests(writes), [
      ...LOCALES.map(locale => locale === 'en-GB' ? 'PATCH /v1/appInfoLocalizations/info-en-gb' : 'POST /v1/appInfoLocalizations'),
      'PATCH /v1/appInfos/info-1',
      'PATCH /v1/ageRatingDeclarations/rating-1',
      ...LOCALES.map(() => 'POST /v1/appStoreVersionLocalizations'),
      'POST /v1/appStoreReviewDetails',
    ]);
    assert.ok(calls.every(call => call.method === 'GET'));
    assert.ok(calls.every(call => call.authorization === 'Bearer test-token'));
    assert.equal(calls[0].path, `/v1/apps?filter[bundleId]=${BUNDLE_ID}`);
    assert.ok(calls.some(call => call.path === '/v1/apps/app-1/appStoreVersions?filter[platform]=IOS&limit=200'));

    const body = request => request.body.data;
    const [, englishInfo, usInfo] = writes;
    assert.deepEqual(body(englishInfo), { type: 'appInfoLocalizations', id: 'info-en-gb',
      attributes: { name: english.name, subtitle: english.subtitle, privacyPolicyUrl: 'https://peektracker.com/privacy.html' } });
    assert.equal(body(usInfo).attributes.locale, 'en-US');
    assert.deepEqual(body(usInfo).relationships, { appInfo: { data: { type: 'appInfos', id: 'info-1' } } });
    assert.deepEqual(body(writes[8]).relationships, { primaryCategory: { data: { type: 'appCategories', id: 'UTILITIES' } },
      secondaryCategory: { data: { type: 'appCategories', id: 'SHOPPING' } } });
    // Only the questions the declaration has, and not those it already answers None or No.
    assert.deepEqual(body(writes[9]).attributes, { violenceRealistic: 'NONE', gambling: false, messagingAndChat: false });

    const versionLocalizations = writes.slice(10, 18).map(body);
    assert.deepEqual(versionLocalizations.map(data => data.attributes.locale), LOCALES);
    for (const data of versionLocalizations) {
      assert.deepEqual(Object.keys(data.attributes).sort(),
        ['description', 'keywords', 'locale', 'marketingUrl', 'promotionalText', 'supportUrl']);
      assert.deepEqual(data.relationships, { appStoreVersion: { data: { type: 'appStoreVersions', id: 'version-1' } } });
    }
    assert.deepEqual(body(writes.at(-1)).attributes, { notes, demoAccountRequired: false });
  });

  it('creates the next version when none is editable, with What’s New once one is out', async () => {
    const { api, calls } = fakeApi({ ...routes, '/v1/apps/app-1/appStoreVersions': [version('version-1', '1.0', 'READY_FOR_DISTRIBUTION')] });
    const writes = await sync(api, { versionString: '1.1', copyright: '2026 Example Holder' });
    const created = writes.find(write => write.path === '/v1/appStoreVersions');
    assert.deepEqual(created.body.data.attributes, { platform: 'IOS', versionString: '1.1', copyright: '2026 Example Holder' });
    const localizations = writes.filter(write => write.path === '/v1/appStoreVersionLocalizations');
    assert.equal(localizations.length, LOCALES.length);
    assert.ok(localizations.every(write => write.body.data.attributes.whatsNew));
    assert.ok(!calls.some(call => call.path.includes('version-1/')), 'reads nothing of the released version');
    assert.ok(calls.every(call => call.method === 'GET'));
  });

  it('keeps the parcel added after the review notes', async () => {
    const { api } = fakeApi({ ...routes, '/v1/appStoreVersions/version-1/appStoreReviewDetail': { type: 'appStoreReviewDetails',
      id: 'review-1', attributes: { notes: `${notes} EXAMPLE123`, demoAccountRequired: false } } });
    const writes = await sync(api);
    assert.ok(!writes.some(write => write.path.startsWith('/v1/appStoreReviewDetails')));
  });

  it('asks for the review contact only while it is missing', async () => {
    const reminded = async attributes => {
      const { api } = fakeApi({ ...routes, '/v1/appStoreVersions/version-1/appStoreReviewDetail': { type: 'appStoreReviewDetails',
        id: 'review-1', attributes: { notes, demoAccountRequired: false, ...attributes } } });
      const lines = [];
      await sync(api, { log: line => lines.push(line) });
      return lines.some(line => line.includes('review contact'));
    };
    const contact = { contactFirstName: 'Sam', contactLastName: 'Example', contactPhone: '+41 00 000 00 00', contactEmail: 'review@example.com' };
    assert.equal(await reminded({}), true);
    assert.equal(await reminded({ ...contact, contactPhone: null }), true);
    assert.equal(await reminded(contact), false);
  });

  it('stops when the app record does not exist', async () => {
    const { api } = fakeApi({ ...routes, '/v1/apps': [] });
    await assert.rejects(sync(api), /no app com\.plhery\.PeekDeliveryTracker\. Create the app record first/);
  });
});

describe('--apply', () => {
  it('creates the version first, then edits the app information it brings', async () => {
    const [prepared] = routes['/v1/apps/app-1/appInfos'];
    const liveInfo = { ...prepared, id: 'info-live', attributes: { state: 'READY_FOR_DISTRIBUTION' } };
    const canned = { ...routes, '/v1/apps/app-1/appInfos': [liveInfo],
      '/v1/apps/app-1/appStoreVersions': [version('version-1', '1.0', 'READY_FOR_DISTRIBUTION')],
      '/v1/appStoreVersions/version-2/appStoreVersionLocalizations': [] };
    const { api, calls } = fakeApi(canned, () => {
      canned['/v1/apps/app-1/appInfos'] = [liveInfo, prepared];
      return new Response('{"data":{"type":"appStoreVersions","id":"version-2"}}', { status: 201 });
    });
    const writes = await sync(api, { apply: true, versionString: '1.0.1' });
    assert.equal(requests(writes)[0], 'POST /v1/appStoreVersions');
    assert.ok(requests(writes).includes('PATCH /v1/appInfoLocalizations/info-en-gb'));
    assert.ok(writes.filter(write => write.method === 'POST' && write.path === '/v1/appInfoLocalizations')
      .every(write => write.body.data.relationships.appInfo.data.id === 'info-1'));
    assert.ok(!calls.some(call => call.path.includes('info-live')), 'leaves the live app information alone');
  });

  it('stops at the first write App Store Connect refuses, with its details', async () => {
    const refused = '{"errors":[{"status":"409","code":"ENTITY_ERROR","detail":"The name is already in use."}]}';
    const { api, calls } = fakeApi(routes, () => new Response(refused, { status: 409 }));
    await assert.rejects(sync(api, { apply: true }), error => {
      assert.equal(error.message, 'POST /v1/appInfoLocalizations answered 409: The name is already in use.');
      return true;
    });
    assert.equal(calls.filter(call => call.method !== 'GET').length, 1);
  });
});
