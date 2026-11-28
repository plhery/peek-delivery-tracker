import { createHash } from 'node:crypto';
import { readFileSync, writeFileSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const appRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const brandRoot = path.join(appRoot, 'src', 'brand');
const identitiesPath = path.join(brandRoot, 'identities.json');
const outputPath = path.join(appRoot, 'src', 'generated', 'brand.ts');
const liveriesPath = path.join(appRoot, 'src', 'generated', 'liveries.ts');

function readJson(file) {
  return JSON.parse(readFileSync(file, 'utf8'));
}

/**
 * The brand data, as src/brand declares it. Shared with
 * scripts/generate-ios-resources.mjs so Brand.json and brand.ts cannot disagree.
 */
export function readBrandData() {
  const palette = readJson(path.join(brandRoot, 'palette.json'));
  const truck = readJson(path.join(brandRoot, 'truck.json'));
  const properties = new Set(palette.properties);

  const carriers = Object.entries(readJson(identitiesPath)).map(([id, brand]) => ({ id, brand }));

  const families = {};
  const members = new Map();
  for (const carrier of carriers) {
    const family = carrier.brand.family ?? carrier.id;
    if (family !== carrier.id) families[carrier.id] = family;
    members.set(family, [...(members.get(family) ?? []), carrier]);
  }

  const palettes = {};
  const decals = {};
  for (const [family, group] of members) {
    for (const key of ['palette', 'decal']) {
      const declaring = group.filter((carrier) => carrier.brand[key] !== undefined);
      if (declaring.length > 1) {
        throw new Error(
          `Brand family ${family} declares brand.${key} in several entries `
          + `(${declaring.map((carrier) => carrier.id).join(', ')}). `
          + 'Exactly one member owns the family identity; the others only declare brand.family.',
        );
      }
      if (declaring.length === 0) continue;
      const value = declaring[0].brand[key];
      if (key === 'palette') {
        for (const property of Object.keys(value)) {
          if (!properties.has(property)) {
            throw new Error(`src/brand/identities.json (${declaring[0].id}) declares an unknown brand property ${property}`);
          }
        }
      }
      for (const carrier of group) (key === 'palette' ? palettes : decals)[carrier.id] = value;
    }
  }

  return {
    defaultColor: palette.defaultColor,
    fallbackColor: palette.fallbackColor,
    properties: palette.properties,
    derivation: palette.derivation,
    toneExamples: palette.toneExamples,
    litEdge: palette.litEdge,
    litEdgeExamples: palette.litEdgeExamples,
    families: sortedByKey(families),
    palettes: sortedByKey(palettes),
    decals: sortedByKey(decals),
    truck,
  };
}

function sortedByKey(value) {
  return Object.fromEntries(Object.entries(value).sort(([first], [second]) => first.localeCompare(second)));
}

function inlineJson(value) {
  return Array.isArray(value) ? `[${value.map(inlineJson).join(', ')}]` : JSON.stringify(value);
}

/** JSON.stringify(value, null, 2), except that coordinate arrays stay on one line. */
function renderJson(value, indent = 0) {
  const pad = ' '.repeat(indent);
  const step = ' '.repeat(indent + 2);
  if (Array.isArray(value)) {
    if (value.length === 0) return '[]';
    const inline = inlineJson(value);
    if (!inline.includes('{') && inline.length + indent <= 92) return inline;
    return `[\n${value.map((item) => step + renderJson(item, indent + 2)).join(',\n')}\n${pad}]`;
  }
  if (value && typeof value === 'object') {
    const entries = Object.entries(value);
    if (entries.length === 0) return '{}';
    const members = entries.map(([key, item]) => `${step}${JSON.stringify(key)}: ${renderJson(item, indent + 2)}`);
    return `{\n${members.join(',\n')}\n${pad}}`;
  }
  return JSON.stringify(value);
}

/** The payload ios/PeekDeliveryTracker/Resources/Brand.json carries. */
export function renderBrandJson(brand) {
  return `${renderJson(brand)}\n`;
}

/** A shape without the straight-line outline the SwiftUI canvas follows. */
function drawnOnTheWeb(shape) {
  return Object.fromEntries(Object.entries(shape).filter(([key]) => key !== 'points' && key !== 'segments'));
}

/**
 * The truck the web draws. Its SVG reads `d` alone, so the outlines spelled a
 * second time for the iPhone app stay out of the browser bundle; Brand.json
 * keeps them. The liveries are not in it: the server sends each as a file of
 * its own (`app/liveries`), so a page downloads only those it shows.
 */
function webTruck(truck) {
  return {
    ...Object.fromEntries(Object.entries(truck).filter(([key]) => key !== 'decals')),
    cab: drawnOnTheWeb(truck.cab),
    windshield: drawnOnTheWeb(truck.windshield),
  };
}

/** Every livery's shapes, as the web draws them. */
function webLiveries(truck) {
  return Object.fromEntries(Object.entries(truck.decals).map(([name, shapes]) => [name, shapes.map(drawnOnTheWeb)]));
}

/** Names the liveries' files: a new drawing gets new addresses, so a browser keeps each file for good. */
function liveryVersion(liveries) {
  return createHash('sha256').update(JSON.stringify(liveries)).digest('hex').slice(0, 12);
}

function generatedBrand(brand) {
  return `${[
    '/* This file is generated by scripts/generate-brand.mjs. Do not edit. */',
    '',
    "import type { CarrierDecal, CarrierPalette, TruckGeometry } from '../brand/types';",
    '',
    `export const DEFAULT_CARRIER_COLOR = ${JSON.stringify(brand.defaultColor)};`,
    '',
    `export const CARRIER_FAMILIES: Record<string, string> = ${renderJson(brand.families)};`,
    '',
    `export const CARRIER_PALETTES: Record<string, Partial<CarrierPalette>> = ${renderJson(brand.palettes)};`,
    '',
    `export const CARRIER_DECALS: Record<string, CarrierDecal> = ${renderJson(brand.decals)};`,
    '',
    `export const CARRIER_TRUCK: TruckGeometry = ${renderJson(webTruck(brand.truck))};`,
    '',
    `export const LIVERY_VERSION = ${JSON.stringify(liveryVersion(webLiveries(brand.truck)))};`,
  ].join('\n')}\n`;
}

function generatedLiveries(brand) {
  return `${[
    '/* This file is generated by scripts/generate-brand.mjs. Do not edit. */',
    '',
    "import type { TruckLiveries } from '../brand/types';",
    '',
    `export const CARRIER_LIVERIES: TruckLiveries = ${renderJson(webLiveries(brand.truck))};`,
  ].join('\n')}\n`;
}

if (process.argv[1] && path.resolve(process.argv[1]) === fileURLToPath(import.meta.url)) {
  const brand = readBrandData();
  const outputs = [[outputPath, generatedBrand(brand)], [liveriesPath, generatedLiveries(brand)]];
  if (process.argv.includes('--check')) {
    for (const [file, expected] of outputs) {
      if (readFileSync(file, 'utf8') !== expected) {
        throw new Error(`${path.relative(appRoot, file)} is stale. Run npm run contract:generate.`);
      }
    }
    console.log('Generated brand assets are current.');
  } else {
    for (const [file, expected] of outputs) {
      writeFileSync(file, expected);
      console.log(`Wrote ${path.relative(path.resolve(appRoot, '..', '..'), file)}`);
    }
  }
}
