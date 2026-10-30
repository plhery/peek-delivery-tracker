import assert from 'node:assert/strict';
import fs from 'node:fs';
import { describe, it } from 'node:test';
import { fileURLToPath } from 'node:url';
import {
  infoPlistStrings, keysNamingOnlyTheIPhone, keysWithoutAMacVariant, nativeLocalizationReferences, nativeSwiftSources,
} from './native-localization.mjs';
import { appleLanguage, locales, validateLocalizationCatalogs } from './localization-catalog.mjs';

describe('Shared localization catalogs', () => {
  const catalogs = () => Object.fromEntries(locales.map((locale) => [locale, {
    'passport.stampsEarned': '{{count}} / {{total}}',
    'arrival.tapToOpen': 'Open',
    'detail.updateCount.one': '1 update',
  }]));

  it('requires every platform and language to use the same keys', () => {
    const value = catalogs();
    delete value.fr['arrival.tapToOpen'];
    assert.throws(() => validateLocalizationCatalogs(value), /fr.*arrival.tapToOpen/);
  });

  it('rejects translations that lose interpolation variables', () => {
    const value = catalogs();
    value.de['passport.stampsEarned'] = '{{count}}';
    assert.throws(() => validateLocalizationCatalogs(value), /de.passport.stampsEarned.*variables/);
  });

  it('lets a count’s one form write the number where English writes 1, and adds nothing else', () => {
    const value = catalogs();
    value.ru['detail.updateCount.one'] = '{{count}} обновление';
    assert.doesNotThrow(() => validateLocalizationCatalogs(value));
    value.ru['detail.updateCount.one'] = '{{total}} обновление';
    assert.throws(() => validateLocalizationCatalogs(value), /ru.detail.updateCount.one.*variables/);
    value.ru['detail.updateCount.one'] = '1 обновление';
    value.ru['arrival.tapToOpen'] = '{{count}}';
    assert.throws(() => validateLocalizationCatalogs(value), /ru.arrival.tapToOpen.*variables/);
  });

  it('makes a Russian one form write the number when its family counts, since 21 and 101 read it', () => {
    const value = catalogs();
    for (const locale of locales) value[locale]['detail.updateCount.many'] = '{{count}} updates';
    value.ru['detail.updateCount.many'] = '{{count}} обновлений';
    value.ru['detail.updateCount.one'] = '1 обновление';
    assert.throws(() => validateLocalizationCatalogs(value), /ru.detail.updateCount.one.*\{\{count\}\}/);
    value.ru['detail.updateCount.one'] = '{{count}} обновление';
    assert.doesNotThrow(() => validateLocalizationCatalogs(value));
  });

  it('allows a translation to reorder variables', () => {
    const value = catalogs();
    value.it['passport.stampsEarned'] = '{{total}}: {{count}}';
    assert.doesNotThrow(() => validateLocalizationCatalogs(value));
  });

  it('keeps French punctuation from starting a line', () => {
    const value = catalogs();
    value.fr['arrival.tapToOpen'] = 'Ouvrir ?';
    assert.throws(() => validateLocalizationCatalogs(value), /fr.arrival.tapToOpen.*no-break space/);
    value.fr['arrival.tapToOpen'] = '« Ouvrir » ?';
    assert.throws(() => validateLocalizationCatalogs(value), /fr.arrival.tapToOpen/);
    value.fr['arrival.tapToOpen'] = '«\u00a0Ouvrir\u00a0»\u202f?';
    assert.doesNotThrow(() => validateLocalizationCatalogs(value));
  });
});

const catalogKeys = ['welcome.title', 'auth.title'];

describe('Native localization references', () => {
  it('ignores literal accessibility identifiers that share a localization prefix', () => {
    const source = `
      Text(localizer.text("welcome.title"))
        .accessibilityIdentifier("welcome.openParcel")
        .accessibilityIdentifier(
          "auth.google"
        )
    `;
    assert.deepEqual([...nativeLocalizationReferences(source, catalogKeys)], ['welcome.title']);
  });

  it('keeps missing translations in labels and accessibility copy', () => {
    const source = `
      Text(localizer.text("welcome.missingTitle"))
        .accessibilityLabel(localizer.text("welcome.missingLabel"))
        .accessibilityHint(localizer.text("welcome.missingHint"))
    `;
    assert.deepEqual([...nativeLocalizationReferences(source, catalogKeys)], [
      'welcome.missingTitle', 'welcome.missingLabel', 'welcome.missingHint',
    ]);
  });

  it('keeps a translation reference even when the same key is also an identifier', () => {
    const source = `
      Button(localizer.text("welcome.openParcel"), action: unwrap)
        .accessibilityIdentifier("welcome.openParcel")
    `;
    assert.deepEqual([...nativeLocalizationReferences(source, catalogKeys)], ['welcome.openParcel']);
  });

  it('checks localization calls inside accessibility identifier expressions', () => {
    const source = '.accessibilityIdentifier(localizer.text("welcome.missingTitle"))';
    assert.deepEqual([...nativeLocalizationReferences(source, catalogKeys)], ['welcome.missingTitle']);
  });

  it('deduplicates references and ignores unrelated Swift string literals', () => {
    const source = `
      Image(systemName: "shippingbox.fill")
      Text(localizer.text("welcome.title"))
        .accessibilityLabel(localizer.text("welcome.title"))
    `;
    assert.deepEqual([...nativeLocalizationReferences(source, catalogKeys)], ['welcome.title']);
  });

  it('checks the Share extension copy along with the app and the widget', () => {
    const keys = ['shareExtension.title', 'common.cancel', 'native.done', 'widget.galleryName'];
    const references = nativeLocalizationReferences(nativeSwiftSources(fileURLToPath(new URL('../ios', import.meta.url))), keys);
    for (const key of ['shareExtension.title', 'shareExtension.notFound', 'common.cancel', 'native.done', 'widget.galleryName']) {
      assert.ok(references.has(key), key);
    }
  });
});

describe('Copy that names the iPhone', () => {
  const catalogs = {
    en: { 'native.openSettings': 'Open iPhone Settings', 'native.openSettings.ipad': 'Open iPad Settings', 'app.demo': 'Demo' },
    it: { 'native.openSettings': 'Apri Impostazioni', 'native.openSettings.ipad': 'Apri Impostazioni', 'app.demo': 'Demo' },
  };

  it('passes a key with an iPad variant in every language, and one that names no device', () => {
    assert.deepEqual(keysNamingOnlyTheIPhone(new Set(['native.openSettings', 'app.demo']), catalogs), []);
  });

  it('names a key the app shows that says iPhone without an iPad variant', () => {
    const value = structuredClone(catalogs);
    delete value.it['native.openSettings.ipad'];
    value.it['app.demo'] = 'Demo su iPhone';
    assert.deepEqual(keysNamingOnlyTheIPhone(new Set(['native.openSettings', 'app.demo']), value),
      ['app.demo', 'native.openSettings']);
  });
});

describe('Copy a Mac shows instead of the iPad\'s', () => {
  const catalogs = {
    en: {
      'native.openSettings': 'Open iPhone Settings', 'native.openSettings.ipad': 'Open iPad Settings',
      'native.openSettings.mac': 'Open System Settings', 'liveActivity.systemDisabled.ipad': 'Off on this iPad',
    },
    it: {
      'native.openSettings': 'Apri Impostazioni', 'native.openSettings.ipad': 'Apri Impostazioni',
      'native.openSettings.mac': 'Apri Impostazioni di Sistema', 'liveActivity.systemDisabled.ipad': 'Disattivate su iPad',
    },
  };
  const keys = new Set(['native.openSettings', 'liveActivity.systemDisabled']);

  it('passes a key with a Mac variant in every language, and copy only an iPhone or an iPad shows', () => {
    assert.deepEqual(keysWithoutAMacVariant(keys, catalogs), []);
  });

  it('names a key with an iPad variant but no Mac one', () => {
    const value = structuredClone(catalogs);
    delete value.it['native.openSettings.mac'];
    assert.deepEqual(keysWithoutAMacVariant(keys, value), ['native.openSettings']);
  });
});

describe('Localized Info.plist strings', () => {
  const catalogs = () => Object.fromEntries(locales.map((locale) => [locale, {
    'native.cameraUsage': `Camera ${locale}`,
    'shareExtension.title': `Add to Peek ${locale}`,
    'menuBar.trackWithPeek': `Track with Peek ${locale}`,
  }]));

  it('writes one InfoPlist.strings per target and language', () => {
    const files = infoPlistStrings(catalogs());
    const folders = locales.map(appleLanguage);
    assert.deepEqual(folders.filter((folder) => !locales.includes(folder)), ['pt-BR', 'zh-Hans']);
    assert.deepEqual(files.map(([file]) => file), [
      ...folders.map((folder) => `PeekDeliveryTracker/Resources/${folder}.lproj/InfoPlist.strings`),
      ...folders.map((folder) => `ShareExtension/${folder}.lproj/InfoPlist.strings`),
      ...folders.map((folder) => `PeekDeliveryTracker/Resources/${folder}.lproj/ServicesMenu.strings`),
    ]);
    assert.match(files[1][1], /^"NSCameraUsageDescription" = "Camera de";$/m);
    assert.match(files[2 * locales.length - 1][1], new RegExp(`^"CFBundleDisplayName" = "Add to Peek ${locales.at(-1)}";$`, 'm'));
    assert.match(files.at(-1)[1], new RegExp(`^"Track with Peek" = "Track with Peek ${locales.at(-1)}";$`, 'm'));
  });

  it('names each lproj after a project region and ships it in both targets', () => {
    const project = fs.readFileSync(new URL('../ios/PeekDeliveryTracker.xcodeproj/project.pbxproj', import.meta.url), 'utf8');
    // Xcode quotes a name with a hyphen in it.
    const named = (value) => value.includes('-') ? `"${value}"` : value;
    const regions = project.match(/knownRegions = \(([^)]*)\)/)[1].split(',').map((region) => region.trim());
    for (const folder of locales.map(appleLanguage)) {
      assert.ok(regions.includes(named(folder)), folder);
      assert.equal(project.split(`path = ${named(`${folder}.lproj/InfoPlist.strings`)};`).length - 1, 2, folder);
      assert.equal(project.split(`path = ${named(`${folder}.lproj/ServicesMenu.strings`)};`).length - 1, 1, folder);
    }
    const plist = fs.readFileSync(new URL('../ios/PeekDeliveryTracker/Info.plist', import.meta.url), 'utf8');
    const listed = plist.match(/<key>CFBundleLocalizations<\/key>\s*<array>([\s\S]*?)<\/array>/)[1].match(/<string>[^<]+<\/string>/g).map((entry) => entry.slice(8, -9));
    assert.deepEqual(listed, locales.map(appleLanguage));
  });

  it('escapes quotes, backslashes and line breaks', () => {
    const value = catalogs();
    value.fr['shareExtension.title'] = 'Say "hi" \\ then\nadd';
    const [, contents] = infoPlistStrings(value).find(([file]) => file === 'ShareExtension/fr.lproj/InfoPlist.strings');
    assert.match(contents, /^"CFBundleDisplayName" = "Say \\"hi\\" \\\\ then\\nadd";$/m);
  });

  it('fails when a language lacks the copy', () => {
    const value = catalogs();
    delete value.pl['native.cameraUsage'];
    assert.throws(() => infoPlistStrings(value), /pl\.native\.cameraUsage/);
  });
});
