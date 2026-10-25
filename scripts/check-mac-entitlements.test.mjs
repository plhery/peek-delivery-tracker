import assert from 'node:assert/strict';
import fs from 'node:fs';
import { describe, it } from 'node:test';
import { entitlementDifferences, expandSettings, targetEntitlements } from './check-mac-entitlements.mjs';

const settings = Object.freeze({
  PROJECT_DIR: '/project/ios',
  SDT_APP_GROUP_IDENTIFIER: 'group.com.example.Peek',
  SDT_ASSOCIATED_DOMAIN: 'applinks:peek.example.com',
  APS_ENVIRONMENT: 'production',
});
const app = () => ({
  'com.apple.security.app-sandbox': true,
  'com.apple.security.application-groups': ['group.com.example.Peek'],
  'com.apple.developer.aps-environment': 'production',
});

describe('Mac entitlements', () => {
  it('fills build settings in nested values, in both spellings', () => {
    assert.deepEqual(expandSettings({
      groups: ['$(SDT_APP_GROUP_IDENTIFIER)'], domains: ['${SDT_ASSOCIATED_DOMAIN}'], sandbox: true,
    }, settings), { groups: ['group.com.example.Peek'], domains: ['applinks:peek.example.com'], sandbox: true });
    assert.throws(() => expandSettings('$(MISSING)', settings), /MISSING is not a build setting/);
  });

  it('reads each target with an entitlements file once, from the project directory', () => {
    const read = [];
    const targets = targetEntitlements([
      { target: 'App', buildSettings: { ...settings, CODE_SIGN_ENTITLEMENTS: 'App/App-macOS.entitlements' } },
      { target: 'Tests', buildSettings: settings },
      { target: 'App', buildSettings: { ...settings, CODE_SIGN_ENTITLEMENTS: 'App/App-macOS.entitlements' } },
    ], (file) => { read.push(file); return { 'com.apple.developer.aps-environment': '$(APS_ENVIRONMENT)' }; });
    assert.deepEqual(read, ['/project/ios/App/App-macOS.entitlements']);
    assert.deepEqual(targets, { App: { 'com.apple.developer.aps-environment': 'production' } });
  });

  it('accepts the same entitlements, plus what signing adds', () => {
    const signed = { ...app(), 'com.apple.application-identifier': 'TEAM.com.example.Peek', 'com.apple.security.get-task-allow': true };
    assert.deepEqual(entitlementDifferences({ App: app() }, { App: signed }), []);
  });

  it('names an added, a missing and a changed entitlement, and a target either side lacks', () => {
    const actual = { ...app(), 'com.apple.developer.devicecheck.appattest-environment': 'production' };
    delete actual['com.apple.security.app-sandbox'];
    actual['com.apple.developer.aps-environment'] = 'development';
    assert.deepEqual(entitlementDifferences({ App: app(), Widget: app() }, { App: actual, Share: app() }), [
      'App: com.apple.developer.aps-environment is "development", the list says "production"',
      'App: com.apple.developer.devicecheck.appattest-environment = "production" is not in the list',
      'App: com.apple.security.app-sandbox is missing',
      'Widget: no entitlements were found',
      'Share: not in ios/mac-entitlements.json',
    ]);
  });

  it('lists the network only for the app, and App Attest for no target', () => {
    const list = JSON.parse(fs.readFileSync(new URL('../ios/mac-entitlements.json', import.meta.url), 'utf8'));
    for (const [target, entitlements] of Object.entries(list)) {
      assert.equal(entitlements['com.apple.security.app-sandbox'], true, target);
      assert.equal('com.apple.security.network.client' in entitlements, target === 'PeekDeliveryTracker', target);
      assert.ok(!Object.keys(entitlements).some((key) => key.includes('appattest')), target);
    }
  });
});
