import { execFileSync } from 'node:child_process';
import { existsSync, readFileSync, readdirSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath, pathToFileURL } from 'node:url';

const iosDirectory = path.join(path.dirname(fileURLToPath(import.meta.url)), '..', 'ios');
const expectedFile = path.join(iosDirectory, 'mac-entitlements.json');

// Signing adds these from the team and the profile; the list holds what the targets ask for.
const signingKeys = new Set([
  'com.apple.application-identifier', 'com.apple.developer.team-identifier', 'com.apple.security.get-task-allow',
]);

/** Fills `$(NAME)` and `${NAME}` from the build settings, as Xcode does before it signs. */
export function expandSettings(value, settings) {
  if (typeof value === 'string') {
    return value.replace(/\$\((\w+)\)|\$\{(\w+)\}/g, (_, round, curly) => {
      const name = round ?? curly;
      if (typeof settings[name] !== 'string') throw new Error(`${name} is not a build setting`);
      return settings[name];
    });
  }
  if (Array.isArray(value)) return value.map((item) => expandSettings(item, settings));
  if (value && typeof value === 'object') {
    return Object.fromEntries(Object.entries(value).map(([key, item]) => [key, expandSettings(item, settings)]));
  }
  return value;
}

/** Each Mac target's entitlements file with its build settings filled in, from `xcodebuild -showBuildSettings -json`. */
export function targetEntitlements(buildSettings, readPlist) {
  const targets = {};
  for (const { target, buildSettings: settings } of buildSettings) {
    if (!settings.CODE_SIGN_ENTITLEMENTS || targets[target]) continue;
    targets[target] = expandSettings(readPlist(path.join(settings.PROJECT_DIR, settings.CODE_SIGN_ENTITLEMENTS)), settings);
  }
  return targets;
}

/** What differs between the checked-in list and the targets' entitlements, one line each. */
export function entitlementDifferences(expected, actual) {
  const lines = [];
  for (const target of new Set([...Object.keys(expected), ...Object.keys(actual)])) {
    if (!actual[target]) { lines.push(`${target}: no entitlements were found`); continue; }
    if (!expected[target]) { lines.push(`${target}: not in ios/mac-entitlements.json`); continue; }
    const keys = new Set([...Object.keys(expected[target]), ...Object.keys(actual[target])]);
    for (const key of [...keys].filter((name) => !signingKeys.has(name)).sort()) {
      const want = JSON.stringify(expected[target][key]);
      const have = JSON.stringify(actual[target][key]);
      if (want === have) continue;
      if (have === undefined) lines.push(`${target}: ${key} is missing`);
      else if (want === undefined) lines.push(`${target}: ${key} = ${have} is not in the list`);
      else lines.push(`${target}: ${key} is ${have}, the list says ${want}`);
    }
  }
  return lines;
}

const readPlist = (file) => JSON.parse(execFileSync('/usr/bin/plutil', ['-convert', 'json', '-o', '-', file], {
  encoding: 'utf8', stdio: ['ignore', 'pipe', 'pipe'],
}));

/** The entitlements a signed Mac app and its extensions carry, by target (the executable's name). */
function signedEntitlements(appPath) {
  const plugIns = path.join(appPath, 'Contents', 'PlugIns');
  const bundles = [appPath, ...(existsSync(plugIns) ? readdirSync(plugIns).map((name) => path.join(plugIns, name)) : [])];
  return Object.fromEntries(bundles.map((bundle) => {
    const xml = execFileSync('/usr/bin/codesign', ['-d', '--entitlements', '-', '--xml', bundle], {
      encoding: 'utf8', stdio: ['ignore', 'pipe', 'pipe'],
    });
    const entitlements = JSON.parse(execFileSync('/usr/bin/plutil', ['-convert', 'json', '-o', '-', '-'], {
      input: xml, encoding: 'utf8',
    }));
    return [readPlist(path.join(bundle, 'Contents', 'Info.plist')).CFBundleExecutable, entitlements];
  }));
}

if (process.argv[1] && import.meta.url === pathToFileURL(process.argv[1]).href) {
  try {
    const appPath = process.argv[2] === '--app' ? process.argv[3] : null;
    if (process.argv[2] && !appPath) throw new Error('Usage: node scripts/check-mac-entitlements.mjs [--app PATH]');
    const actual = appPath ? signedEntitlements(appPath) : targetEntitlements(JSON.parse(execFileSync('xcodebuild', [
      '-project', path.join(iosDirectory, 'PeekDeliveryTracker.xcodeproj'), '-alltargets',
      '-configuration', 'Release', '-sdk', 'macosx', '-showBuildSettings', '-json',
    ], { encoding: 'utf8', stdio: ['ignore', 'pipe', 'pipe'], maxBuffer: 64 * 1024 * 1024 })), readPlist);
    const differences = entitlementDifferences(JSON.parse(readFileSync(expectedFile, 'utf8')), actual);
    if (differences.length) {
      console.error(`The Mac entitlements differ from ios/mac-entitlements.json:\n${differences.map((line) => `- ${line}`).join('\n')}`);
      process.exitCode = 1;
    } else {
      console.log(`The Mac entitlements match ios/mac-entitlements.json (${Object.keys(actual).sort().join(', ')}).`);
    }
  } catch (error) {
    console.error(`Mac entitlements check failed: ${error.message}`);
    process.exitCode = 1;
  }
}
