import fs from 'node:fs';
import path from 'node:path';

// The app and both extensions take their copy from Localization.json.
const nativeSourceDirectories = ['PeekDeliveryTracker', 'DeliveryWidgetExtension', 'ShareExtension'];

// iOS shows these Info.plist strings before Peek runs, so each target ships them as
// InfoPlist.strings in one lproj per catalog language, named like the project's knownRegions.
// A Mac's Services menu reads its item's title, keyed by the English one, from ServicesMenu.strings.
const infoPlistCopy = [
  ['PeekDeliveryTracker/Resources', { NSCameraUsageDescription: 'native.cameraUsage' }],
  ['ShareExtension', { CFBundleDisplayName: 'shareExtension.title' }],
  ['PeekDeliveryTracker/Resources', { 'Track with Peek': 'menuBar.trackWithPeek' }, 'ServicesMenu.strings'],
];

export function nativeSwiftSources(iosDirectory) {
  return nativeSourceDirectories
    .flatMap((directory) => fs.readdirSync(path.join(iosDirectory, directory))
      .filter((name) => name.endsWith('.swift'))
      .map((name) => fs.readFileSync(path.join(iosDirectory, directory, name), 'utf8')))
    .join('\n');
}

export function nativeLocalizationReferences(swiftSource, localizationKeys) {
  // Accessibility identifiers are stable UI automation hooks, not display copy.
  // Only skip literal arguments so localization calls inside expressions are checked.
  const source = swiftSource.replace(
    /\.\s*accessibilityIdentifier\s*\(\s*"(?:\\.|[^"\\])*"\s*\)/g,
    '',
  );
  const prefixes = new Set(localizationKeys.map((key) => key.split('.')[0]));
  return new Set(
    [...source.matchAll(/"([a-z][A-Za-z0-9_-]*(?:\.[A-Za-z0-9_-]+)+)"/g)]
      .map((match) => match[1])
      .filter((key) => prefixes.has(key.split('.')[0])),
  );
}

/** The keys the app shows that name the iPhone in some language but have no `.ipad` variant for an iPad. */
export function keysNamingOnlyTheIPhone(referencedKeys, catalogs) {
  return [...referencedKeys]
    .filter((key) => !key.endsWith('.ipad'))
    .filter((key) => Object.values(catalogs).some((catalog) => catalog[key]?.includes('iPhone')))
    .filter((key) => !Object.values(catalogs).every((catalog) => typeof catalog[`${key}.ipad`] === 'string'))
    .sort();
}

// Copy for what only an iPhone or an iPad has, which a Mac never shows.
const iOSOnlyKeys = new Set(['liveActivity.systemDisabled']);

/** The keys the app shows with an `.ipad` variant but no `.mac` one: a Mac names neither the iPhone nor the iPad. */
export function keysWithoutAMacVariant(referencedKeys, catalogs) {
  return [...referencedKeys]
    .filter((key) => !key.endsWith('.ipad') && !key.endsWith('.mac') && !iOSOnlyKeys.has(key))
    .filter((key) => Object.values(catalogs).some((catalog) => typeof catalog[`${key}.ipad`] === 'string'))
    .filter((key) => !Object.values(catalogs).every((catalog) => typeof catalog[`${key}.mac`] === 'string'))
    .sort();
}

const stringsEscapes = { '\\': '\\\\', '"': '\\"', '\n': '\\n', '\r': '\\r', '\t': '\\t' };
const stringsLiteral = (text) => `"${text.replace(/[\\"\n\r\t]/g, (character) => stringsEscapes[character])}"`;

/** Every target's InfoPlist.strings and ServicesMenu.strings in every language, as [path under ios/, contents]. */
export function infoPlistStrings(catalogs) {
  return infoPlistCopy.flatMap(([directory, keys, file = 'InfoPlist.strings']) => Object.entries(catalogs).map(([language, catalog]) => {
    const lines = Object.entries(keys).map(([plistKey, key]) => {
      if (typeof catalog[key] !== 'string') throw new Error(`Missing Info.plist translation: ${language}.${key}`);
      return `${stringsLiteral(plistKey)} = ${stringsLiteral(catalog[key])};\n`;
    });
    return [
      `${directory}/${language}.lproj/${file}`,
      `/* Generated from shared/locales by npm run ios:resources. */\n${lines.join('')}`,
    ];
  }));
}
