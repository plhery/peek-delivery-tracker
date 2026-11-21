import 'server-only';

/**
 * Peek AI's learned model: how many of a carrier's delivery days a parcel still needs, read from what its scans tell
 * at one moment. A gradient-boosted classifier, trained on the parcels Peek has followed, answers "will it have
 * arrived within h delivery days?" for h = 0 to the model's horizon; the answers, made non-decreasing in h, are the
 * parcel's chance of arriving by each delivery day (`predictArrival`).
 *
 * The model is trained in private, on production data, and lives in the database (`peek_ai_models`), never in this
 * repository. Its features are computed here exactly as they were for training: from the stored scans of a parcel
 * (Peek's own rows, scans with no time of their own, scans stamped with when the carrier's system wrote them and
 * scans dated more than a day after they were stored left out), placed as `placesForEvents` places them, at a moment
 * on the parcel's clock (`snapshotFeatures`).
 */

export type PeekAiTree =
  | { leaf: number }
  | { f: number[]; t: Array<number | number[]>; d: number[]; m: number[]; l: number[]; r: number[]; v: number[] };

export interface PeekAiModelDoc {
  horizon: number;
  /** Feature names in the model's order, `h` last. */
  features: string[];
  /** Each categorical feature's categories, in the order the trees' category splits count them. */
  categories: Record<string, string[]>;
  /** Per carrier and status (`<carrier>|<status key>`): sum of delivery days (capped), arrivals within a day, rows. */
  targetEncoding: { prior: [number, number]; k: number; cap: number; keys: Record<string, [number, number, number]> };
  trees: PeekAiTree[];
}

/** A stored scan as the model reads it. `ts` is in seconds. */
export interface AiScan {
  stage: string;
  ts: number;
  description: string | null;
  providerCode: string | null;
  source: string;
  country: string | null;
  lat: number | null;
  lon: number | null;
  precision: string | null;
}

/**
 * The parcel's own facts the model reads: its carrier, the destination country its carrier declared (the model never
 * learned from a guessed one), its service and weight, whether it is a lookup, and its clock's offset from UTC in
 * seconds.
 */
export interface AiParcel {
  c: string;
  destinationCountry: string | null;
  serviceName: string | null;
  weightKg: unknown;
  lookup: boolean;
  offset: number;
}

export type AiFeatures = Record<string, number | string | null>;

/** Stages in which a parcel has been handed to a carrier. */
export const PHYSICAL_STAGES: ReadonlySet<string> = new Set(['accepted', 'in_transit', 'customs', 'out_for_delivery', 'exception',
  'failed_attempt', 'ready_for_pickup', 'delivered']);
/** Stages that end the model's reading: the parcel arrived, waits at a pickup point, or a delivery was tried. */
export const ARRIVAL_STAGES: ReadonlySet<string> = new Set(['delivered', 'ready_for_pickup', 'failed_attempt']);
const MAX_SCAN_AGE_HOURS = 30 * 24;
const MAX_JOURNEY_DAYS = 45;
const DEFAULT_DELIVERY_DAYS: readonly number[] = [0, 1, 2, 3, 4, 5];

// What a scan's words tell about where the parcel is on its way, on its words in lower case. Trained with Python's
// regular expressions: `\b` and `$` are spelt here as Python reads them (`pythonPattern`).
const MILESTONES: ReadonlyArray<readonly [string, string]> = [
  ['label', String.raw`label created|information (received|sent)|info received|pre-?advis|order (creat|submitted|information)|electronic(ally)? |data (received|was entered)|shipment (order )?created|not yet (been )?(handed|received)|has not received|en cours de préparation|préparation chez|bientôt nous être confié|elektronisch angekündigt|pedido (em preparação|foi criado)|utworzona|przygotowana przez nadawcę|pendiente de recepción|pickup requested|pending to receive|instruction data`],
  ['pickup', String.raw`picked up|pick-?up scan|collected|coletado|posting/collection|accepted at|in possession|received by logistics|carrier received|postado|nadana|pris en charge|déposé|dropped off|handed to dpd|received the mail|abgeholt|eingeliefert|ritirat|recogid|recolhid`],
  ['export', String.raw`export|leaving from departure|departed from departure|departure country|country of origin|departed from origin|parcel center of origin|departure from [a-z]+$|picked up abroad|transit country`],
  ['air', String.raw`airport|flight|airline|\bnoa\b|\bgha\b|landing|take-?off|aéroport|flughafen|aeroporto|aeropuerto|lotnisk|air cargo|uplift`],
  ['import', String.raw`import|customs.*destination|destination.*customs|released from customs|dédouan|verzoll|\bzoll|sdoganament|aduana|alfândega de import|odprawa celna|departed from customs|customs temporary warehouse|customs clearance (complete|completed|finished)$`],
  ['destarr', String.raw`destination country|arrived in the destination|region (of|des) (the )?(recipient|empfänger)|région de destination|pays de destination|zielland|zielregion|país de destino|paese di destinazione|kraju przeznaczenia|domestic terminal`],
  ['handover', String.raw`local (courier|carrier|delivery company)|last[- ]mile|accepted by delivery carrier|handed over to (the )?(local|delivery|partner|carrier)|delivery partner|shipping partner|remis au transporteur|übergeben an|consegnato al corriere|entregue à transportadora`],
  ['depot', String.raw`delivery (depot|station|cent(er|re)|office|base|facility|unit)|destination (facility|depot|station|office|agency)|local (facility|depot|distribution|post office|delivery|fedex)|agence de (distribution|livraison)|site de (distribution|livraison)|point de livraison|zustellbasis|zustell(depot|stützpunkt)|último centro logístico|centro de distribui|centro di (distribuzione|recapito)|sorted for delivery|prepared for delivery|final delivery|assigned delivery|arrived at post office|unidade de (distribuição|entrega)|centro de reparto|oficina de entrega|bureau distributeur|de destination$|ready for delivery`],
  ['forecast', String.raw`delivery forecast|scheduled for delivery|delivery (is )?(planned|scheduled|expected)|livraison prévue|zustellung (geplant|voraussichtlich)|consegna prevista|entrega prevista|planowane doręczenie`],
  ['problem', String.raw`delay|retard|verzöger|ritard|atras|opóźn|unable|could not|couldn't|failed|incorrect address|address (problem|issue|query)|\bheld\b|on hold|retenu|missed|exception|damaged|não conseguimos|no se pudo|impossible|incident|weather|backlog|rescheduled`],
  ['vehicle', String.raw`loaded on (vehicle|truck|van)|loaded for (delivery|transport)|with (the )?(delivery )?(courier|driver)|chargé|verladen|in consegna|em rota|w doręczeniu`],
  ['sort', String.raw`sort|hub|facility|centre|center|centro|plateforme|zentrum|sortownia|processing|transit`],
];
const WORD = String.raw`[\p{L}\p{N}_]`;
/** A Python pattern in JavaScript: `\b` between Unicode word characters, `$` also before a final newline. */
export function pythonPattern(source: string): RegExp {
  return new RegExp(source.replaceAll(String.raw`\b`, `(?:(?<=${WORD})(?!${WORD})|(?<!${WORD})(?=${WORD}))`).replace(/\$(?=\||\)|$)/g, String.raw`(?=\n?$)`), 'u');
}
const MILESTONE_PATTERNS = MILESTONES.map(([name, source]) => [name, pythonPattern(source)] as const);
export const MILESTONE_NAMES = MILESTONES.map(([name]) => name);

/** The milestones a scan's words tell, as the model reads them: a customs scan that tells no export is an import. */
export function scanMilestones(stage: string, description: string | null): Set<string> {
  const text = pythonLower(codePoints(description ?? '', 200));
  const marks = new Set(MILESTONE_PATTERNS.filter(([, pattern]) => pattern.test(text)).map(([name]) => name));
  if (stage === 'customs' && !marks.has('export')) marks.add('import');
  return marks;
}

const EXPRESS = pythonPattern(String.raw`express|priority|prio|overnight|next day|two day|1st|first class|expedited|premium|urgent|chrono|crono plus|sedex|speed post|ems|快`);
const ECONOMY = pythonPattern(String.raw`econom|ground|standard|saver|smartpost|surepost|parcel select|media|small parcel|小包|慢|packet|airmail|pac\b|freight|untracked|parcel post`);

/** Python's `str.lower()`, a character at a time: no final-sigma rule. */
export function pythonLower(text: string): string {
  let out = '';
  for (const char of text) out += char === 'Σ' ? 'σ' : char.toLowerCase();
  return out;
}

/** The first `length` characters of a text, by code point, as Postgres's `left` and Python's slices cut it. */
export function codePoints(text: string, length: number): string {
  return Array.from(text).slice(0, length).join('');
}

/** The status key the target encoding is learned on: the carrier's code, else its words without digits. */
export function statusKey(providerCode: string | null, description: string | null): string {
  if (providerCode !== null) return `c:${codePoints(providerCode, 40)}`;
  return `d:${codePoints(pythonLower(description ?? '').replace(/[0-9]+/g, '#').replace(/\s+/g, ' ').trim(), 50)}`;
}

export function serviceClass(name: string | null): string {
  if (name === null) return 'unknown';
  const lower = pythonLower(name);
  if (EXPRESS.test(lower)) return 'express';
  if (ECONOMY.test(lower)) return 'economy';
  return 'other';
}

function haversine(aLat: number | null, aLon: number | null, bLat: number | null, bLon: number | null): number | null {
  if (aLat === null || aLon === null || bLat === null || bLon === null) return null;
  const p = Math.PI / 180;
  const x = Math.sin((bLat - aLat) * p / 2) ** 2 + Math.cos(aLat * p) * Math.cos(bLat * p) * Math.sin((bLon - aLon) * p / 2) ** 2;
  return 6371 * 2 * Math.asin(Math.sqrt(x));
}

/**
 * Python's `round(x, 1)`, as text: the binary value rounded to one decimal, an exact half (x a quarter) to even. Only
 * equality matters here, so -0.0 and 0.0 are one.
 */
export function roundTenth(x: number): string {
  if (!Number.isFinite(x)) return String(x);
  const quarters = x * 4;
  let text: string;
  if (Number.isInteger(quarters) && quarters % 2 !== 0) {
    const tenths = x * 10;
    const low = Math.floor(tenths);
    text = ((low % 2 === 0 ? low : low + 1) / 10).toFixed(1);
  } else text = x.toFixed(1);
  return text === '-0.0' ? '0.0' : text;
}

const mod = (a: number, b: number) => ((a % b) + b) % b;

/** Delivery days after day `a` up to and including day `b` (days since 1970-01-01, a Thursday). */
export function deliveryDaysBetween(days: readonly number[], a: number, b: number): number {
  let n = 0;
  for (let d = a + 1; d <= b; d++) if (days.includes(mod(d + 3, 7))) n++;
  return n;
}

export interface ModelContext {
  deliveryDays: Readonly<Record<string, readonly number[]>>;
  homeCountries: Readonly<Record<string, readonly string[]>>;
}

/** The scans in the order the model was trained on them: by time, then stage name. */
export function orderedScans(scans: readonly AiScan[]): AiScan[] {
  return [...scans].sort((a, b) => a.ts - b.ts || (a.stage < b.stage ? -1 : a.stage > b.stage ? 1 : 0));
}

/**
 * What the scans dated up to moment `t` (seconds) tell, as the model was trained to read them; null where it was not
 * trained to answer: before the first physical scan, after an arrival or a return, past 45 days or when the newest
 * scan is more than 30 days old. `scans` must be ordered (`orderedScans`). `snapDay` and `hour` are the moment's
 * day and hour on the parcel's clock.
 */
export function snapshotFeatures(parcel: AiParcel, scans: readonly AiScan[], t: number, snapDay: number, hour: number,
  context: ModelContext): AiFeatures | null {
  const ts = scans.map((scan) => scan.ts);
  const st = scans.map((scan) => scan.stage);
  const firstPhysIndex = st.findIndex((stage) => PHYSICAL_STAGES.has(stage));
  if (firstPhysIndex < 0) return null;
  const firstPhys = ts[firstPhysIndex]!;
  const arrivalIndex = st.findIndex((stage) => ARRIVAL_STAGES.has(stage));
  const returnIndex = st.indexOf('returned');
  let end = arrivalIndex >= 0 ? ts[arrivalIndex]! : Infinity;
  if (returnIndex >= 0) end = Math.min(end, ts[returnIndex]!);
  end = Math.min(end, firstPhys + MAX_JOURNEY_DAYS * 86400);
  if (t < firstPhys || t >= end) return null;
  let i = 0;
  while (i < ts.length && ts[i]! <= t) i++;
  if (i === 0) return null;
  const j = i - 1;
  if (st.slice(0, i).includes('returned')) return null;
  const ageH = (t - ts[j]!) / 3600;
  if (ageH > MAX_SCAN_AGE_HOURS) return null;
  const off = parcel.offset;
  const dest = parcel.destinationCountry || null;
  const home = context.homeCountries[parcel.c] ?? [];
  const deliveryDays = context.deliveryDays[parcel.c] ?? DEFAULT_DELIVERY_DAYS;
  const ctry = scans.map((scan) => scan.country);
  const lat = scans.map((scan) => scan.lat);
  const lon = scans.map((scan) => scan.lon);
  const srcs = scans.map((scan) => scan.source);
  const words = scans.map((scan) => pythonLower(codePoints(scan.description ?? '', 200)));
  const marks = Object.fromEntries(MILESTONE_PATTERNS.map(([name, pattern]) => [name, words.map((text) => pattern.test(text))]));
  marks.import = marks.import!.map((hit, k) => hit || (st[k] === 'customs' && !marks.export![k]));
  const pl: number[] = [];
  for (let k = 0; k < i; k++) if (typeof ctry[k] === 'string') pl.push(k);
  const jp = pl.length ? pl[pl.length - 1]! : -1;
  const countries = new Set(pl.map((k) => ctry[k]));
  const inDest = pl.filter((k) => dest !== null && ctry[k] === dest);
  const firstCountry = pl.length ? ctry[pl[0]!]! : null;
  const since = (mask: readonly boolean[]) => {
    for (let k = 0; k < i; k++) if (mask[k]) return (t - ts[k]!) / 3600;
    return null;
  };
  const back = pl.filter((k) => ts[k]! <= t - 48 * 3600);
  const flag = (value: boolean) => value ? 1 : 0;
  const features: AiFeatures = {
    c: parcel.c, stage: st[j]!, dow: mod(snapDay + 3, 7), hour,
    deliv_today: flag(deliveryDays.includes(mod(snapDay + 3, 7))), deliv_tomorrow: flag(deliveryDays.includes(mod(snapDay + 4, 7))),
    age_h: ageH, scan_hour: Math.floor(mod(ts[j]! + off, 86400) / 3600), scan_dow: mod(Math.floor((ts[j]! + off) / 86400) + 3, 7),
    n_scans: i, n_24h: ts.slice(0, i).filter((x) => x > t - 86400).length, n_48h: ts.slice(0, i).filter((x) => x > t - 2 * 86400).length,
    n_72h: ts.slice(0, i).filter((x) => x > t - 3 * 86400).length, days_since_first: (t - ts[0]!) / 86400, days_since_phys: (t - firstPhys) / 86400,
    h_since_ofd: since(st.map((stage) => stage === 'out_for_delivery')), ever_ofd: flag(st.slice(0, i).includes('out_for_delivery')),
    ever_exception: flag(st.slice(0, i).includes('exception')),
  };
  for (const name of MILESTONE_NAMES) {
    features[`now_${name}`] = flag(marks[name]![j]!);
    features[`ever_${name}`] = flag(marks[name]!.slice(0, i).some(Boolean));
  }
  Object.assign(features, {
    h_since_depot: since(marks.depot!), h_since_handover: since(marks.handover!),
    h_since_import: since(marks.import!.map((hit, k) => hit || st[k] === 'customs')), h_since_air: since(marks.air!),
    ever_customs: flag(st.slice(0, i).includes('customs')),
    placed: flag(pl.length > 0), n_countries: countries.size, reached: flag(inDest.length > 0),
    pos_in_dest: flag(jp >= 0 && dest !== null && ctry[jp] === dest),
    pos_in_origin: flag(jp >= 0 && countries.size > 0 && ctry[jp] === firstCountry),
    abroad: flag(jp >= 0 && dest !== null && ctry[jp] !== dest),
    h_in_dest: inDest.length ? (t - ts[inDest[0]!]!) / 3600 : null,
    km_from_first: jp >= 0 ? haversine(lat[pl[0]!]!, lon[pl[0]!]!, lat[jp]!, lon[jp]!) : null,
    km_48h: jp >= 0 && back.length ? haversine(lat[back[back.length - 1]!]!, lon[back[back.length - 1]!]!, lat[jp]!, lon[jp]!) : null,
    n_places: new Set(pl.map((k) => `${roundTenth(lat[k] ?? NaN)},${roundTenth(lon[k] ?? NaN)}`)).size,
    dest, origin: firstCountry, lane: !(dest && firstCountry) ? 'unknown' : dest === firstCountry ? 'domestic' : 'cross',
    dest_declared: flag(typeof parcel.destinationCountry === 'string'),
    n_sources: new Set(srcs.slice(0, i)).size, handed_on: flag(srcs[j] !== srcs[0]),
    local_carrier: flag(dest !== null && (context.homeCountries[srcs[j]!] ?? []).includes(dest)),
    carrier_home_dest: flag(dest !== null && home.includes(dest)),
    service: serviceClass(parcel.serviceName), weight: weightOf(parcel.weightKg), lookup: flag(parcel.lookup),
  });
  return features;
}

function weightOf(value: unknown): number | null {
  if (value === null || value === undefined) return null;
  const text = String(value);
  if (!/^[0-9.]+$/.test(text)) return null;
  const weight = Number(text);
  return Number.isFinite(weight) ? weight : null;
}

/** The target encoding of the newest scan's status: how long parcels took after it, smoothed toward all parcels. */
export function withTargetEncoding(features: AiFeatures, key: string, model: PeekAiModelDoc): AiFeatures {
  const { prior, k, keys } = model.targetEncoding;
  const [sum, within, n] = keys[`${features.c}|${key}`] ?? keys[`${features.c}|?`] ?? [0, 0, 0];
  return { ...features, te_mean: (sum + k * prior[0]) / (n + k), te_p1: (within + k * prior[1]) / (n + k), te_n: Math.log1p(n) };
}

/** The features in the model's order, categories as their index, a missing value as NaN. */
export function featureVector(features: AiFeatures, model: PeekAiModelDoc, h: number): Float64Array {
  const out = new Float64Array(model.features.length);
  model.features.forEach((name, index) => {
    const value = name === 'h' ? h : features[name];
    const categories = model.categories[name];
    if (categories) {
      const category = categories.indexOf(value === null || value === undefined ? '?' : String(value));
      out[index] = category >= 0 ? category : NaN;
    } else out[index] = typeof value === 'number' ? value : NaN;
  });
  return out;
}

/** One tree's answer, read as LightGBM reads its splits. */
function treeValue(tree: PeekAiTree, x: Float64Array): number {
  if ('leaf' in tree) return tree.leaf;
  let node = 0;
  for (;;) {
    let value = x[tree.f[node]!]!;
    const threshold = tree.t[node]!;
    let next: number;
    if (Array.isArray(threshold)) {
      next = Number.isNaN(value) || Math.trunc(value) < 0 || !threshold.includes(Math.trunc(value)) ? tree.r[node]! : tree.l[node]!;
    } else {
      const missing = tree.m[node]!;
      if (Number.isNaN(value) && missing !== 2) value = 0;
      if ((missing === 1 && value >= -1e-35 && value <= 1e-35) || (missing === 2 && Number.isNaN(value))) {
        next = tree.d[node] ? tree.l[node]! : tree.r[node]!;
      } else next = value <= threshold ? tree.l[node]! : tree.r[node]!;
    }
    if (next < 0) return tree.v[~next]!;
    node = next;
  }
}

/** The model's raw score for one feature vector. */
export function rawScore(model: PeekAiModelDoc, x: Float64Array): number {
  let sum = 0;
  for (const tree of model.trees) sum += treeValue(tree, x);
  return sum;
}

/** The chance of having arrived within h delivery days, for h = 0 to the horizon, never decreasing. */
export function predictArrival(model: PeekAiModelDoc, features: AiFeatures): number[] {
  const out: number[] = [];
  let best = 0;
  for (let h = 0; h <= model.horizon; h++) {
    const p = 1 / (1 + Math.exp(-rawScore(model, featureVector(features, model, h))));
    best = Math.max(best, p);
    out.push(best);
  }
  return out;
}

/** The same, without the running maximum: for the parity check against the trained model. */
export function predictArrivalRaw(model: PeekAiModelDoc, features: AiFeatures): number[] {
  const out: number[] = [];
  for (let h = 0; h <= model.horizon; h++) out.push(1 / (1 + Math.exp(-rawScore(model, featureVector(features, model, h)))));
  return out;
}
