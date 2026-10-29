// Fills in the iPhone app's App Store Connect listing from docs/app-store: each language's
// name, subtitle, promotional text, description, keywords and links, the categories, the age
// rating and the review notes. Dry run by default: prints the changes it would send.
// --apply sends them and stops at the first one App Store Connect refuses. It never submits
// for review, and leaves pricing, availability, App Privacy, screenshots and builds alone.
//
//   ASC_KEY_ID=… ASC_ISSUER_ID=… node scripts/app-store-listing.mjs [--apply]
//     [--version=1.0.1] [--copyright="<year> <account holder>"]
//
// The key is read from ASC_KEY_PATH, or ~/.appstoreconnect/private_keys/AuthKey_<ASC_KEY_ID>.p8.
// --version is the version to create when the app has none to edit; by default the app's
// MARKETING_VERSION.
import { existsSync, readdirSync, readFileSync } from 'node:fs';
import { homedir } from 'node:os';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { parseArgs } from 'node:util';
import { importPKCS8, SignJWT } from 'jose';

export const BUNDLE_ID = 'com.plhery.PeekDeliveryTracker';
const API = 'https://api.appstoreconnect.apple.com';
const LISTING = new URL('../docs/app-store/listing/', import.meta.url);
const README = new URL('../docs/app-store/README.md', import.meta.url);
const PROJECT = new URL('../ios/PeekDeliveryTracker.xcodeproj/project.pbxproj', import.meta.url);
const CATEGORIES = { primaryCategory: 'UTILITIES', secondaryCategory: 'SHOPPING' };
/** The review contact, set by hand in App Store Connect: it stays out of this repository. */
const REVIEW_CONTACT = ['contactFirstName', 'contactLastName', 'contactPhone', 'contactEmail'];
const URLS = {
  privacyPolicyUrl: 'https://peektracker.com/privacy.html',
  supportUrl: 'https://peektracker.com/support.html',
  marketingUrl: 'https://peektracker.com',
};

/** The App Store locales each listing file serves. */
export const LOCALES = { 'en-GB': ['en-GB'], 'en-US': ['en-US'], de: ['de-DE'], fr: ['fr-FR'], it: ['it'], es: ['es-ES'],
  pt: ['pt-PT'], pl: ['pl'] };
const HEADINGS = { name: 'Name', subtitle: 'Subtitle', promotionalText: 'Promotional text', description: 'Description',
  keywords: 'Keywords', whatsNew: "What's new" };
const LIMITS = { name: [2, 30], subtitle: [0, 30], promotionalText: [0, 170], description: [0, 4000], whatsNew: [0, 4000] };

const EDITABLE = new Set(['PREPARE_FOR_SUBMISSION', 'DEVELOPER_REJECTED', 'REJECTED', 'METADATA_REJECTED']);
const LIVE = new Set(['READY_FOR_DISTRIBUTION', 'READY_FOR_SALE']);
// Every age rating question, answered None or No (docs/app-store/README.md). The overrides,
// the kids age band and the classification fields are left alone.
const NONE = ['alcoholTobaccoOrDrugUseOrReferences', 'contests', 'gamblingSimulated', 'gunsOrOtherWeapons',
  'horrorOrFearThemes', 'matureOrSuggestiveThemes', 'medicalOrTreatmentInformation', 'profanityOrCrudeHumor',
  'sexualContentGraphicAndNudity', 'sexualContentOrNudity', 'violenceCartoonOrFantasy', 'violenceRealistic',
  'violenceRealisticProlongedGraphicOrSadistic'];
const NO = ['advertising', 'ageAssurance', 'gambling', 'healthOrWellnessTopics', 'lootBox', 'messagingAndChat',
  'parentalControls', 'socialMedia', 'socialMediaAgeRestricted', 'unrestrictedWebAccess', 'userGeneratedContent'];

/** The ```text block under each `## ` heading. */
function textBlocks(markdown) {
  return new Map(markdown.split(/^## /m).slice(1).map(section =>
    [section.slice(0, section.indexOf('\n')).trim(), section.match(/^```text\n([\s\S]*?)\n```$/m)?.[1]]));
}

/** A listing file's fields, keyed by their App Store Connect names. */
export function parseListing(markdown, file) {
  const blocks = textBlocks(markdown);
  return Object.fromEntries(Object.entries(HEADINGS).map(([field, heading]) => {
    if (!blocks.get(heading)) throw new Error(`${file}: no \`\`\`text block under "## ${heading}"`);
    return [field, blocks.get(heading)];
  }));
}

/** Throws, naming the file and field, when a field doesn't fit App Store Connect's limits. */
export function checkLimits(listing, file) {
  for (const [field, [min, max]] of Object.entries(LIMITS)) {
    const length = [...listing[field]].length;
    if (length < min || length > max) {
      throw new Error(`${file}: ${HEADINGS[field]} has ${length} characters; App Store Connect takes ${min ? `${min} to ` : 'up to '}${max}`);
    }
  }
  const bytes = Buffer.byteLength(listing.keywords);
  if (bytes > 100) throw new Error(`${file}: Keywords take ${bytes} bytes; App Store Connect takes up to 100`);
}

const words = text => text.toLocaleLowerCase().match(/[\p{L}\p{N}]+/gu) ?? [];

/** Throws on a word used twice in the name, subtitle and keywords: the App Store searches them together. */
export function checkWords(listing, file) {
  const seen = new Set();
  for (const word of [listing.name, listing.subtitle, listing.keywords].flatMap(words)) {
    if (seen.has(word)) throw new Error(`${file}: "${word}" is repeated across the name, subtitle and keywords`);
    seen.add(word);
  }
}

/** Every locale's listing, checked. */
export function loadListings(directory = LISTING) {
  return readdirSync(directory).filter(file => file.endsWith('.md')).sort().flatMap(file => {
    const locales = LOCALES[path.basename(file, '.md')];
    if (!locales) throw new Error(`${file}: no App Store locale for it in LOCALES`);
    const listing = parseListing(readFileSync(new URL(file, directory), 'utf8'), file);
    checkLimits(listing, file);
    checkWords(listing, file);
    return locales.map(locale => ({ locale, ...listing }));
  });
}

export function reviewNotes(readme) {
  const notes = textBlocks(readme).get('App Review');
  if (!notes) throw new Error('docs/app-store/README.md: no ```text block under "## App Review"');
  return notes;
}

export const appInfoFields = listing => ({ name: listing.name, subtitle: listing.subtitle, privacyPolicyUrl: URLS.privacyPolicyUrl });

/** App Store Connect refuses What's New for an app's first version. */
export const versionFields = (listing, released) => ({
  description: listing.description,
  keywords: listing.keywords,
  promotionalText: listing.promotionalText,
  supportUrl: URLS.supportUrl,
  marketingUrl: URLS.marketingUrl,
  ...(released && { whatsNew: listing.whatsNew }),
});

/** The attributes of `wanted` that `current` doesn't have yet, or null. */
export function changes(current, wanted) {
  const changed = Object.fromEntries(Object.entries(wanted).filter(([key, value]) => current[key] !== value));
  return Object.keys(changed).length ? changed : null;
}

/** None or No to each age rating question the declaration has, where it says otherwise. */
export function ageRatingAnswers(attributes) {
  const answers = [...NONE.map(key => [key, 'NONE']), ...NO.map(key => [key, false])].filter(([key]) => key in attributes);
  return changes(attributes, Object.fromEntries(answers));
}

const ref = (type, id) => ({ data: { type, id } });
const summary = (label, attributes) => `${label}: ${Object.keys(attributes).filter(key => key !== 'locale').join(', ')}`;

/** A POST when there's no `existing` resource, else a PATCH of the attributes that differ, or null. */
export function upsert(type, existing, attributes, relationships, label) {
  if (!existing) {
    return { method: 'POST', path: `/v1/${type}`, summary: summary(label, attributes),
      body: { data: { type, attributes, relationships } } };
  }
  const changed = changes(existing.attributes, attributes);
  return changed && { method: 'PATCH', path: `/v1/${type}/${existing.id}`, summary: summary(label, changed),
    body: { data: { type, id: existing.id, attributes: changed } } };
}

const states = ({ attributes }) => [attributes.state, attributes.appVersionState, attributes.appStoreState];
const editable = resource => states(resource).some(state => EDITABLE.has(state));
const live = resource => states(resource).some(state => LIVE.has(state));
const byLocale = resources => new Map(resources.map(resource => [resource.attributes.locale, resource]));

/** The app information being prepared, else the last one that isn't live. */
export const editableAppInfo = appInfos => appInfos.find(editable) ?? appInfos.filter(info => !live(info)).at(-1);

/**
 * Brings the app's listing in App Store Connect in line with `listings` and `notes`. Reads
 * with GET; writes only with `apply`. Returns the writes, sent or planned.
 */
export async function syncListing({ api, apply = false, listings, notes, versionString, copyright, log = console.log }) {
  const writes = [];
  const write = async request => {
    if (!request) return null;
    writes.push(request);
    log(`${request.method} ${request.path}  ${request.summary}`);
    return apply ? (await api(request.method, request.path, request.body))?.data : null;
  };
  const list = async apiPath => (await api('GET', apiPath)).data;

  const app = (await list(`/v1/apps?filter[bundleId]=${BUNDLE_ID}`)).find(item => item.attributes.bundleId === BUNDLE_ID);
  if (!app) throw new Error(`App Store Connect has no app ${BUNDLE_ID}. Create the app record first (docs/app-store/README.md).`);

  const versions = await list(`/v1/apps/${app.id}/appStoreVersions?filter[platform]=IOS&limit=200`);
  const released = versions.some(live);
  const existing = versions.find(editable);
  if (existing) log(`Version ${existing.attributes.versionString}, ${states(existing).find(Boolean)}`);
  const written = await write(upsert('appStoreVersions', existing,
    { ...(!existing && { platform: 'IOS', versionString }), ...(copyright && { copyright }) },
    { app: ref('apps', app.id) }, `version ${existing?.attributes.versionString ?? versionString}`));
  // A dry run that creates the version has no id to read its localizations or review details.
  const versionId = existing?.id ?? written?.id;
  const version = ref('appStoreVersions', versionId);

  // A new version brings app information to edit. A dry run that would create it compares with the live one.
  const appInfos = await list(`/v1/apps/${app.id}/appInfos?include=primaryCategory,secondaryCategory`);
  const appInfo = editableAppInfo(appInfos) ?? (!existing && !apply ? appInfos.at(-1) : null);
  if (!appInfo) throw new Error('The app has no app information to edit.');
  const infoLocalizations = byLocale(await list(`/v1/appInfos/${appInfo.id}/appInfoLocalizations?limit=200`));
  for (const listing of listings) {
    await write(upsert('appInfoLocalizations', infoLocalizations.get(listing.locale),
      { locale: listing.locale, ...appInfoFields(listing) }, { appInfo: ref('appInfos', appInfo.id) }, listing.locale));
  }
  const categories = Object.entries(CATEGORIES).filter(([key, id]) => appInfo.relationships?.[key]?.data?.id !== id);
  if (categories.length) {
    await write({ method: 'PATCH', path: `/v1/appInfos/${appInfo.id}`,
      summary: categories.map(([key, id]) => `${key}: ${id}`).join(', '),
      body: { data: { type: 'appInfos', id: appInfo.id,
        relationships: Object.fromEntries(categories.map(([key, id]) => [key, ref('appCategories', id)])) } } });
  }

  const declaration = (await api('GET', `/v1/appInfos/${appInfo.id}/ageRatingDeclaration`)).data;
  const answers = ageRatingAnswers(declaration.attributes);
  if (answers) {
    await write({ method: 'PATCH', path: `/v1/ageRatingDeclarations/${declaration.id}`,
      summary: `age rating: ${Object.keys(answers).length} questions to None or No`,
      body: { data: { type: 'ageRatingDeclarations', id: declaration.id, attributes: answers } } });
  }

  const versionLocalizations = byLocale(versionId
    ? await list(`/v1/appStoreVersions/${versionId}/appStoreVersionLocalizations?limit=200`) : []);
  for (const listing of listings) {
    await write(upsert('appStoreVersionLocalizations', versionLocalizations.get(listing.locale),
      { locale: listing.locale, ...versionFields(listing, released) }, { appStoreVersion: version }, listing.locale));
  }

  const detail = versionId
    ? (await api('GET', `/v1/appStoreVersions/${versionId}/appStoreReviewDetail`, undefined, { allowMissing: true }))?.data
    : null;
  // Notes that start with ours keep the parcel added after their last line.
  const keptNotes = detail?.attributes.notes?.startsWith(notes);
  await write(upsert('appStoreReviewDetails', detail, { ...(!keptNotes && { notes }), demoAccountRequired: false },
    { appStoreVersion: version }, 'review'));
  if (!REVIEW_CONTACT.every(field => detail?.attributes[field])) log('In App Store Connect, add the review contact details.');
  return writes;
}

const parse = text => {
  try {
    return JSON.parse(text);
  } catch {
    return null;
  }
};

/** Calls the API with a fresh token each time. Errors carry Apple's details, never the token. */
export function createApi(token, fetchImpl = fetch) {
  return async (method, apiPath, body, { allowMissing = false } = {}) => {
    const response = await fetchImpl(`${API}${apiPath}`, {
      method,
      headers: { authorization: `Bearer ${await token()}`, ...(body && { 'content-type': 'application/json' }) },
      body: body && JSON.stringify(body),
      signal: AbortSignal.timeout(30_000),
    });
    if (response.status === 404 && allowMissing) return null;
    const text = await response.text();
    const json = parse(text);
    if (response.ok) return json;
    const details = json?.errors?.map(error => error.detail ?? error.title).join(' ') || text.slice(0, 300);
    throw new Error(`${method} ${apiPath} answered ${response.status}: ${details}`);
  };
}

/** Signs App Store Connect API tokens, each valid for 15 minutes. */
export async function tokenSigner({ keyId, issuer, privateKey }) {
  const key = await importPKCS8(privateKey, 'ES256');
  return () => new SignJWT({})
    .setProtectedHeader({ alg: 'ES256', kid: keyId, typ: 'JWT' })
    .setIssuer(issuer)
    .setAudience('appstoreconnect-v1')
    .setIssuedAt()
    .setExpirationTime('15m')
    .sign(key);
}

function required(name) {
  const value = process.env[name];
  if (!value) throw new Error(`Set ${name}`);
  return value;
}

async function main() {
  const { values } = parseArgs({
    options: {
      apply: { type: 'boolean', default: false },
      version: { type: 'string', default: readFileSync(PROJECT, 'utf8').match(/MARKETING_VERSION = ([\d.]+);/)[1] },
      copyright: { type: 'string' },
    },
  });
  if (!/^\d+(\.\d+){0,2}$/.test(values.version)) throw new Error(`--version ${values.version} isn't a version like 1.0`);
  const listings = loadListings();
  const notes = reviewNotes(readFileSync(README, 'utf8'));
  const keyId = required('ASC_KEY_ID');
  const issuer = required('ASC_ISSUER_ID');
  const keyPath = process.env.ASC_KEY_PATH || path.join(homedir(), '.appstoreconnect', 'private_keys', `AuthKey_${keyId}.p8`);
  if (!existsSync(keyPath)) throw new Error(`No API key at ${keyPath}`);
  const api = createApi(await tokenSigner({ keyId, issuer, privateKey: readFileSync(keyPath, 'utf8') }));

  const writes = await syncListing({ api, apply: values.apply, listings, notes, versionString: values.version, copyright: values.copyright });
  if (!writes.length) console.log('Nothing to change.');
  else console.log(values.apply ? `Sent ${writes.length} changes.` : `Dry run: ${writes.length} changes. Re-run with --apply to send them.`);
  console.log('A parcel of your own can follow the last line of the review notes.');
}

if (process.argv[1] && path.resolve(process.argv[1]) === fileURLToPath(import.meta.url)) {
  try {
    await main();
  } catch (error) {
    console.error(String(error?.message ?? error));
    process.exitCode = 1;
  }
}
