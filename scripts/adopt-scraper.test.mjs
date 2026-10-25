import assert from 'node:assert/strict';
import { readdirSync, readFileSync } from 'node:fs';
import path from 'node:path';
import { describe, it } from 'node:test';
import {
  BY_HAND_TITLE,
  ENFORCED_BY,
  adoptionSlot,
  chooseTarget,
  compareVersions,
  continueCommand,
  databaseFacets,
  declarationLine,
  declaredChanges,
  decideGate,
  diffDatabaseFacets,
  gateReport,
  holdsRelease,
  installedRelease,
  matchesOnlyLettersAndDigits,
  parseVersion,
  peerMismatches,
  pinnedVersion,
  readMigrations,
  retry,
  slotAdoption,
  slotFailure,
  waitUntilServed,
} from './adopt-scraper.mjs';

const postcode = {
  field: 'dpdPostcode',
  validator: 'swissPostcode',
  label: 'Delivery postcode',
  type: 'text',
  pattern: '^[0-9]{4}$',
  maxLength: 4,
};

function release(overrides = {}) {
  return {
    catalog: {
      'swiss-post': {
        displayName: 'Swiss Post',
        color: '#ffcc00',
        tracking: { mode: 'automatic', adapter: 'swiss-post', refresh: { minMinutes: 30 } },
        detectionRules: [{ pattern: '^9[89]\\d{16}$', confidence: 'high' }],
      },
      dpd: {
        displayName: 'DPD',
        color: '#dc0032',
        tracking: { mode: 'automatic', adapter: 'dpd', requirements: [postcode] },
        detectionRules: [{ pattern: '^\\d{14}$', confidence: 'low' }],
      },
      unknown: { displayName: 'Unknown', tracking: { mode: 'link-only', adapter: null }, detectionRules: [] },
    },
    stages: ['pending', 'in_transit', 'delivered'],
    providers: ['Ship24', '17TRACK'],
    ...overrides,
  };
}

function changesBetween(before, after) {
  return diffDatabaseFacets(databaseFacets(before), databaseFacets(after));
}

describe('scraper release versions', () => {
  it('accepts stable releases and prereleases of main', () => {
    assert.deepEqual(parseVersion('0.2.0'), { major: 0, minor: 2, patch: 0, prerelease: null });
    assert.deepEqual(parseVersion('1.10.3-main.12'), { major: 1, minor: 10, patch: 3, prerelease: 12 });
  });

  it('rejects everything else', () => {
    for (const value of [
      '', 'latest', 'v1.2.3', '1.2', '1.2.3.4', '01.2.3', '1.2.3-main', '1.2.3-main.01', '1.2.3-beta.1',
      '1.2.3-main.1.2', '1.2.3+build', ' 1.2.3', '1.2.3\n', '^1.2.3', '1.2.3 && true', '1.2.99999999999999999999',
      undefined, null, 123, ['1.2.3'],
    ]) {
      assert.throws(() => parseVersion(value), TypeError, JSON.stringify(value));
    }
  });

  it('orders releases by semver precedence', () => {
    const ordered = [
      '0.2.0', '0.2.1-main.1', '0.2.1-main.2', '0.2.1-main.10', '0.2.1', '0.3.0-main.1', '0.10.0', '1.0.0',
    ];
    assert.deepEqual([...ordered].reverse().sort(compareVersions), ordered);
    assert.equal(compareVersions('0.3.0-main.4', '0.3.0-main.4'), 0);
    assert.equal(compareVersions('0.3.0', '0.3.0-main.4'), 1);
    assert.equal(compareVersions('0.3.0-main.4', '0.3.0'), -1);
    assert.equal(compareVersions('0.3.0-main.4', '0.2.9'), 1);
  });

  it('reads the exact pin from package.json', () => {
    assert.equal(pinnedVersion({ dependencies: { 'universal-parcel-scraper': '0.2.0' } }), '0.2.0');
    assert.throws(() => pinnedVersion({ dependencies: { 'universal-parcel-scraper': '^0.2.0' } }), /exact release/);
    assert.throws(() => pinnedVersion({ dependencies: {} }), /exact release/);
  });
});

describe('adoption slots', () => {
  const at = (time) => adoptionSlot(new Date(time)).toISOString();

  it('starts one 7 past every even hour from 06:07 to 22:07 UTC', () => {
    assert.equal(at('2026-10-21T06:07:00Z'), '2026-10-21T06:07:00.000Z');
    assert.equal(at('2026-10-21T08:06:59Z'), '2026-10-21T06:07:00.000Z');
    assert.equal(at('2026-10-21T08:07:00Z'), '2026-10-21T08:07:00.000Z');
    assert.equal(at('2026-10-21T13:45:57Z'), '2026-10-21T12:07:00.000Z');
    assert.equal(at('2026-10-21T22:07:00Z'), '2026-10-21T22:07:00.000Z');
  });

  it('lets the last one run until the first of the next day', () => {
    assert.equal(at('2026-10-21T23:59:00Z'), '2026-10-21T22:07:00.000Z');
    assert.equal(at('2026-10-22T03:00:00Z'), '2026-10-21T22:07:00.000Z');
    assert.equal(at('2026-10-22T06:06:59Z'), '2026-10-21T22:07:00.000Z');
    assert.equal(at('2026-11-01T00:30:00Z'), '2026-10-31T22:07:00.000Z');
  });

  const slot = new Date('2026-10-21T12:07:00Z');
  const run = (id, ...jobs) => ({ id, jobs: [{ name: 'Due', status: 'completed', conclusion: 'success', started_at: '2026-10-21T13:00:00Z' }, ...jobs] });
  const job = (name) => (started_at, conclusion = 'success', status = 'completed') => ({ name, status, conclusion, started_at });
  const adopt = job('Adopt');
  const push = job('Push');

  it('finds the run that pushed a release, or is pushing one, in the slot', () => {
    assert.equal(slotAdoption(slot, [run(1, adopt('2026-10-21T12:07:00Z'), push('2026-10-21T12:20:00Z'))])?.id, 1);
    assert.equal(slotAdoption(slot, [run(1, adopt('2026-10-21T12:07:00Z'), push('2026-10-21T12:20:00Z', null, 'in_progress'))])?.id, 1);
  });

  it('leaves the slot to a later run after one that found nothing newer or whose release failed', () => {
    assert.equal(slotAdoption(slot, [
      // Nothing newer than the app's release: the push is skipped.
      run(1, adopt('2026-10-21T12:07:00Z'), push('2026-10-21T12:09:00Z', 'skipped')),
      // The release failed the app's tests, or the database gate stopped it.
      run(2, adopt('2026-10-21T12:37:00Z', 'failure'), push('2026-10-21T12:50:00Z', 'skipped')),
      // The push failed or was cancelled.
      run(3, adopt('2026-10-21T13:07:00Z'), push('2026-10-21T13:20:00Z', 'failure')),
      run(4, adopt('2026-10-21T13:37:00Z'), push('2026-10-21T13:50:00Z', 'cancelled')),
    ]), undefined);
  });

  it("counts the timer's runs and the backup runs, not a run by hand", () => {
    const pushed = (id, event, display_title = 'Adopt scraper release') => ({
      ...run(id, adopt('2026-10-21T12:30:00Z'), push('2026-10-21T12:40:00Z')), event, display_title,
    });
    const byHand = pushed(1, 'workflow_dispatch', BY_HAND_TITLE);
    assert.equal(slotAdoption(slot, [byHand]), undefined);
    // The timer's dispatch, a scheduled run and one a CI run on main started.
    for (const event of ['workflow_dispatch', 'schedule', 'workflow_run']) {
      assert.equal(slotAdoption(slot, [byHand, pushed(2, event)])?.id, 2, event);
    }
  });

  it('ignores pushes still queued or started before the slot', () => {
    assert.equal(slotAdoption(slot, [
      run(1, adopt('2026-10-21T13:10:00Z'), push(null, null, 'queued')),
      run(2, adopt('2026-10-21T11:50:00Z'), push('2026-10-21T12:06:59Z')),
      run(3),
    ]), undefined);
  });

  // A run whose Adopt job failed at `step`, having recorded the release it tried.
  const failed = (id, version, step, started_at = '2026-10-21T12:07:00Z') => ({
    ...run(id, { ...adopt(started_at, 'failure'), steps: [
      { name: 'Take main as it is now', conclusion: 'success' },
      { name: step, conclusion: 'failure' },
    ] }),
    artifacts: [{ name: `release-${version}` }],
  });

  it('finds the run of the slot whose release failed, for that release only', () => {
    const runs = [failed(1, '0.689.0', 'Test the app with the release')];
    assert.equal(slotFailure(slot, '0.689.0', runs)?.id, 1);
    // A newer release adopts at once.
    assert.equal(slotFailure(slot, '0.689.1-main.3270', runs), undefined);
    // A release that failed in a run by hand waits too.
    const byHand = { ...failed(2, '0.689.0', 'Test the app with the release'), event: 'workflow_dispatch', display_title: BY_HAND_TITLE };
    assert.equal(slotFailure(slot, '0.689.0', [byHand])?.id, 2);
    for (const step of ['Install the release', "Check the scraper's optional peers",
      'Regenerate the contract and the iPhone resources', 'Write the adoption as a patch']) {
      assert.equal(slotFailure(slot, '0.689.0', [failed(1, '0.689.0', step)])?.id, 1);
    }
  });

  it('tries a release again after a failure of the runner, or one in the slot before', () => {
    assert.equal(slotFailure(slot, '0.689.0', [
      failed(1, '0.689.0', 'Run npm ci'),
      failed(2, '0.689.0', 'Test the app with the release', '2026-10-21T11:40:00Z'),
      // Cancelled, or still running: no failure yet.
      { ...failed(3, '0.689.0', 'Test the app with the release'), jobs: [adopt('2026-10-21T12:30:00Z', 'cancelled')] },
      { ...failed(4, '0.689.0', 'Test the app with the release'), jobs: [adopt('2026-10-21T12:30:00Z', null, 'in_progress')] },
    ]), undefined);
  });
});

describe('the release to adopt', () => {
  it('adopts a requested release that is newer', () => {
    assert.deepEqual(chooseTarget({ current: '0.2.0', requested: '0.3.0-main.2' }), {
      version: '0.3.0-main.2', adopt: true,
    });
    assert.deepEqual(chooseTarget({ current: '0.3.0-main.2', requested: '0.3.0' }), { version: '0.3.0', adopt: true });
  });

  it('never downgrades and has nothing to adopt on the pinned release', () => {
    assert.equal(chooseTarget({ current: '0.3.0', requested: '0.3.0' }).adopt, false);
    assert.equal(chooseTarget({ current: '0.3.0', requested: '0.2.9' }).adopt, false);
    assert.equal(chooseTarget({ current: '0.3.0', requested: '0.3.0-main.9' }).adopt, false);
    assert.equal(chooseTarget({ current: '0.3.0', distTags: { latest: '0.3.0', next: '0.3.0-main.9' } }).adopt, false);
  });

  it('defaults to the newer of latest and next', () => {
    assert.deepEqual(chooseTarget({ current: '0.2.0', distTags: { latest: '0.2.0', next: '0.3.0-main.1' } }), {
      version: '0.3.0-main.1', adopt: true,
    });
    assert.equal(chooseTarget({ current: '0.2.0', distTags: { latest: '0.3.0', next: '0.3.0-main.7' } }).version, '0.3.0');
    assert.equal(chooseTarget({ current: '0.2.0', distTags: { latest: '0.2.1' } }).version, '0.2.1');
  });

  it('rejects a malformed request, pin or tag', () => {
    assert.throws(() => chooseTarget({ current: '0.2.0', requested: 'next' }), /Not a scraper release version/);
    assert.throws(() => chooseTarget({ current: 'main', requested: '0.3.0' }), /Not a scraper release version/);
    assert.throws(() => chooseTarget({ current: '0.2.0', distTags: { latest: '0.2.0', next: '0.0.0-stage' } }), /as next/);
    assert.throws(() => chooseTarget({ current: '0.2.0', distTags: {} }), /no latest or next/);
  });

  it('finds the release, or a newer one, already on main', () => {
    const pinning = (version) => ({ dependencies: { 'universal-parcel-scraper': version } });
    assert.equal(holdsRelease(pinning('0.3.0-main.2'), '0.3.0-main.2'), true);
    assert.equal(holdsRelease(pinning('0.3.0'), '0.3.0-main.2'), true);
    assert.equal(holdsRelease(pinning('0.3.0-main.1'), '0.3.0-main.2'), false);
    assert.throws(() => holdsRelease(pinning('^0.3.0'), '0.3.0'), /exact release/);
  });

  it('takes database_ready only for one named release', () => {
    assert.throws(
      () => chooseTarget({ current: '0.2.0', distTags: { latest: '0.3.0' }, databaseReady: true }),
      /pass -f version=<version>/,
    );
    assert.deepEqual(chooseTarget({ current: '0.2.0', requested: '0.3.0', databaseReady: true }), {
      version: '0.3.0', adopt: true,
    });
  });
});

describe('what the database enforces', () => {
  it('projects carriers, inputs, wider numbers, stages and providers', () => {
    assert.deepEqual(databaseFacets(release()), {
      carriers: ['dpd', 'swiss-post', 'unknown'],
      inputs: ['dpd: dpdPostcode, validator swissPostcode, required, pattern ^[0-9]{4}$'],
      numberShapes: [],
      forcedCarriers: [],
      stages: ['delivered', 'in_transit', 'pending'],
      providers: ['17TRACK', 'Ship24'],
    });
  });

  it('ignores what the database does not know', () => {
    const after = release();
    after.catalog.dpd = {
      ...after.catalog.dpd,
      displayName: 'DPD Switzerland',
      color: '#000000',
      trackingUrlTemplate: 'https://carrier.example/{trackingNumber}',
      tracking: {
        ...after.catalog.dpd.tracking,
        refresh: { minMinutes: 5 },
        requirements: [{ ...postcode, label: 'Postcode', help: 'Four digits.', maxLength: 5 }],
      },
      detectionRules: [{ pattern: '^0\\d{13}$', confidence: 'high' }, { pattern: '^(?=.{14}$)[A-Z]{2}\\d+$', confidence: 'low' }],
    };
    after.stages.reverse();
    after.providers.reverse();
    assert.deepEqual(changesBetween(release(), after), []);
  });

  it('reports new and removed carriers', () => {
    const after = release();
    after.catalog['example-post'] = { tracking: { mode: 'automatic', adapter: 'universal' }, detectionRules: [] };
    delete after.catalog['swiss-post'];
    assert.deepEqual(changesBetween(release(), after), [
      { facet: 'carriers', added: ['example-post'], removed: ['swiss-post'] },
    ]);
  });

  it('reports an input that becomes optional, moves to another field or is new', () => {
    const optional = release();
    optional.catalog.dpd.tracking.requirements = [{ ...postcode, optional: true }];
    assert.deepEqual(changesBetween(release(), optional), [{
      facet: 'inputs',
      added: ['dpd: dpdPostcode, validator swissPostcode, optional, pattern ^[0-9]{4}$'],
      removed: ['dpd: dpdPostcode, validator swissPostcode, required, pattern ^[0-9]{4}$'],
    }]);

    const renamed = release();
    renamed.catalog.dpd.tracking.requirements = [{ ...postcode, field: 'postcode' }];
    assert.deepEqual(changesBetween(release(), renamed)[0].added, [
      'dpd: postcode, validator swissPostcode, required, pattern ^[0-9]{4}$',
    ]);

    const conditional = release();
    conditional.catalog['swiss-post'].tracking.requirements = [{
      field: 'trackingUrl', validator: 'planzerSharedUrl', whenTrackingNumber: '^99990\\d{8}$', type: 'url',
    }];
    assert.deepEqual(changesBetween(release(), conditional), [{
      facet: 'inputs',
      added: ['swiss-post: trackingUrl, validator planzerSharedUrl, required, for numbers matching ^99990\\d{8}$'],
      removed: [],
    }]);
  });

  it('reports stages and providers the database has never seen', () => {
    const after = release({ stages: ['pending', 'in_transit', 'delivered', 'lost'], providers: ['Ship24', 'Example'] });
    assert.deepEqual(changesBetween(release(), after), [
      { facet: 'stages', added: ['lost'], removed: [] },
      { facet: 'providers', added: ['Example'], removed: ['17TRACK'] },
    ]);
  });

  it('reports a tracking number that is more than letters and digits', () => {
    const after = release();
    after.catalog.dpd.detectionRules = [{ pattern: '^\\d{4}/\\d{8}$', confidence: 'high' }];
    assert.deepEqual(changesBetween(release(), after), [
      { facet: 'numberShapes', added: ['dpd: ^\\d{4}/\\d{8}$'], removed: [] },
    ]);
  });

  it('reports a changed number shape of a carrier the database recognises by number', () => {
    const quickpac = (pattern, confidence = 'high') => {
      const result = release();
      result.catalog.quickpac = {
        tracking: { mode: 'automatic', adapter: 'planzer' },
        detectionRules: [{ pattern, confidence }],
      };
      return result;
    };
    assert.deepEqual(databaseFacets(quickpac('^44\\d{16}$')).forcedCarriers, ['quickpac: ^44\\d{16}$']);
    assert.deepEqual(changesBetween(quickpac('^44\\d{16}$'), quickpac('^45\\d{18}$')), [
      { facet: 'forcedCarriers', added: ['quickpac: ^45\\d{18}$'], removed: ['quickpac: ^44\\d{16}$'] },
    ]);
    assert.deepEqual(changesBetween(quickpac('^44\\d{16}$'), quickpac('^44\\d{16}$', 'low')), [
      { facet: 'forcedCarriers', added: [], removed: ['quickpac: ^44\\d{16}$'] },
    ]);
  });

  it('tells plain detection patterns from wider ones', () => {
    for (const pattern of [
      '^9[89]\\d{16}$',
      '^[A-Z]{2}\\d{9}CH$',
      '^(JJD|JVGL)[A-Z0-9]{8,}$',
      '^(?=.{13}$|.{15}$)3S[A-Z]{1,4}\\d+$',
      '^(?:(?=[A-Z0-9]{8}$)(?=.*[A-Z])(?=.*\\d)[A-Z0-9]{8}|\\d{11})$',
      '^06(?:0[6-9]|1\\d)\\d{10}$',
      '^(?!(?:PZ|XU|XW))[A-Z]{2}\\d{9}(?!CH$|FR$)[A-Z]{2}$',
      '^(?![^A-Z])(?!.*[/_a-z])[A-Z]{3}\\d{9}$',
    ]) {
      assert.equal(matchesOnlyLettersAndDigits(pattern), true, pattern);
    }
    for (const pattern of [
      '^\\d{4}/\\d{8}$', '^AB.{8}$', '^\\w{12}$', '^[^0-9]{4}\\d{8}$', '^[a-z]{2}\\d{9}$', '^\\d{4}_\\d{4}$',
      '^(?![(])/\\d+$', '^(?=(?:AB|CD)\\d)\\S{12}$', '^(?<=A)\\d{8}$', '^[A-Z]{2,x}$',
    ]) {
      assert.equal(matchesOnlyLettersAndDigits(pattern), false, pattern);
    }
  });

  it('names constraints and functions that exist in the migrations', () => {
    const migrations = path.resolve(import.meta.dirname, '..', 'supabase', 'migrations');
    const sql = readdirSync(migrations).map((name) => readFileSync(path.join(migrations, name), 'utf8')).join('\n');
    for (const name of Object.values(ENFORCED_BY).flat()) {
      assert.ok(sql.includes(name), `${name} is not in supabase/migrations`);
    }
  });

  it('projects the installed release', async () => {
    const { version, facets } = await installedRelease();
    parseVersion(version);
    assert.ok(facets.carriers.includes('unknown'));
    assert.ok(facets.stages.includes('delivered'));
    assert.ok(facets.providers.length > 0);
    assert.deepEqual(diffDatabaseFacets(facets, facets), []);
  });
});

describe('the database gate report', () => {
  const changes = [
    { facet: 'carriers', added: ['example-post'], removed: [] },
    { facet: 'stages', added: ['lost'], removed: ['returned'] },
  ];

  it('names every change, what enforces it and how to continue', () => {
    const report = gateReport({ from: '0.2.0', to: '0.3.0-main.4', changes, databaseReady: false });
    assert.match(report, /^### Blocked: Universal Parcel Scraper 0\.3\.0-main\.4/);
    assert.match(report, /Compared with 0\.2\.0:/);
    assert.match(report, /\*\*Carrier ids\*\* \(`packages_carrier_check`, `create_owned_package`, `change_owned_package_carrier`\)/);
    assert.match(report, /- added: `example-post`/);
    assert.match(report, /- added: `lost`\n- removed: `returned`/);
    assert.match(report, /`set_owned_notification_preferences`\)/);
    assert.match(report, /notification_preferences\.enabled_stages/);
    assert.match(report, /contracts\/openapi\.json/);
    assert.ok(report.includes('gh workflow run adopt-scraper.yml -f version=0.3.0-main.4 -f database_ready=true'));
    assert.equal(continueCommand('1.2.3'), 'gh workflow run adopt-scraper.yml -f version=1.2.3 -f database_ready=true');
  });

  it('only reports once the database is ready', () => {
    const report = gateReport({ from: '0.2.0', to: '0.3.0', changes, databaseReady: true });
    assert.doesNotMatch(report, /Blocked|gh workflow run/);
    assert.match(report, /confirmed as applied/);
    assert.match(report, /- added: `example-post`/);
  });

  it('says so when nothing changed', () => {
    assert.equal(
      gateReport({ from: '0.2.0', to: '0.2.1', changes: [], databaseReady: false }),
      'Universal Parcel Scraper 0.2.1 changes nothing the database enforces, compared with 0.2.0.',
    );
  });
});

describe('migrations that already made a change', () => {
  const postcodeEntry = 'dpd: dpdPostcode, validator swissPostcode, optional, pattern ^[0-9]{4}$';
  const before = { version: '0.2.0', facets: databaseFacets(release()) };
  const after = (() => {
    const result = release();
    result.catalog['example-post'] = { tracking: { mode: 'automatic', adapter: 'universal' }, detectionRules: [] };
    result.catalog.dpd.tracking.requirements = [{ ...postcode, optional: true }];
    return { version: '0.3.0', facets: databaseFacets(result) };
  })();
  const declare = (...lines) => lines.map((line) => `-- Database gate: ${line}`).join('\n');
  const migration = (name, ...lines) => ({ name, sql: `${declare(...lines)}\nselect 1;\n` });
  const allChanges = [
    'carriers added example-post',
    `inputs added ${postcodeEntry}`,
    'inputs removed dpd: dpdPostcode, validator swissPostcode, required, pattern ^[0-9]{4}$',
  ];
  function ledger(missing = []) {
    const asked = [];
    return { asked, missingMigrations: async (names) => { asked.push(names); return names.filter((name) => missing.includes(name)); } };
  }

  it('lets a release through when applied migrations declare all it changes', async () => {
    const { asked, missingMigrations } = ledger();
    const { blocked, report } = await decideGate({
      before,
      after,
      migrations: [migration('20261017100000_example_post', allChanges[0]), migration('20261017110000_optional_dpd', ...allChanges.slice(1))],
      missingMigrations,
    });
    assert.equal(blocked, false);
    assert.deepEqual(asked, [['20261017100000_example_post', '20261017110000_optional_dpd']]);
    assert.match(report, /^### Universal Parcel Scraper 0\.3\.0 changes what the database enforces, as applied migrations declare/);
    assert.match(report, /- added: `example-post`, applied by `20261017100000_example_post`/);
    assert.match(report, /- removed: `dpd: dpdPostcode, validator swissPostcode, required, pattern \^\[0-9\]\{4\}\$`, applied by `20261017110000_optional_dpd`/);
    assert.doesNotMatch(report, /Blocked|gh workflow run|```sql/);
  });

  it('blocks a change no applied migration declares', async () => {
    const cases = {
      'no migration declares it': [migration('20261017100000_example_post', allChanges[0])],
      'it is declared the other way': [migration('20261017100000_example_post', allChanges[0], 'inputs removed ' + postcodeEntry, allChanges[2])],
      'another pattern is declared': [migration('20261017100000_example_post', allChanges[0], allChanges[1].replace('{4}', '{5}'), allChanges[2])],
      'a later migration undoes it': [
        migration('20261017100000_example_post', ...allChanges),
        migration('20261017120000_drop_example_post', 'carriers removed example-post'),
      ],
    };
    for (const [why, migrations] of Object.entries(cases)) {
      const { blocked, report } = await decideGate({ before, after, migrations, missingMigrations: ledger().missingMigrations });
      assert.equal(blocked, true, why);
      assert.match(report, /^### Blocked: Universal Parcel Scraper 0\.3\.0/, why);
      assert.ok(report.includes('gh workflow run adopt-scraper.yml -f version=0.3.0 -f database_ready=true'), why);
    }
  });

  it('blocks a change whose migration production has not applied, and names the lines to declare', async () => {
    const { blocked, report } = await decideGate({
      before,
      after,
      migrations: [migration('20261017100000_example_post', allChanges[0]), migration('20261017110000_optional_dpd', ...allChanges.slice(1))],
      missingMigrations: ledger(['20261017110000_optional_dpd']).missingMigrations,
    });
    assert.equal(blocked, true);
    assert.match(report, /- added: `example-post`, applied by `20261017100000_example_post`/);
    assert.ok(report.includes(['```sql', ...allChanges.slice(1).map((line) => `-- Database gate: ${line}`), '```'].join('\n')));
  });

  it('asks production nothing when nothing is declared, nothing changed or the database is ready', async () => {
    const { asked, missingMigrations } = ledger();
    assert.equal((await decideGate({ before, after, migrations: [], missingMigrations })).blocked, true);
    assert.equal((await decideGate({ before, after: before, migrations: [migration('20261017100000_example_post', ...allChanges)], missingMigrations })).blocked, false);
    const ready = await decideGate({ before, after, migrations: [migration('20261017100000_example_post', ...allChanges)], missingMigrations, databaseReady: true });
    assert.equal(ready.blocked, false);
    assert.match(ready.report, /confirmed as applied/);
    assert.deepEqual(asked, []);
  });

  it('fails when production cannot say which migrations it has', async () => {
    await assert.rejects(decideGate({
      before,
      after,
      migrations: [migration('20261017100000_example_post', ...allChanges)],
      missingMigrations: async () => { throw new Error('The database answered 503'); },
    }), /503/);
  });

  it('reads declaration lines and refuses one it does not understand', () => {
    assert.deepEqual(declaredChanges([migration('20261017100000_example_post', allChanges[1])]), [
      { migration: '20261017100000_example_post', facet: 'inputs', change: 'added', entry: postcodeEntry },
    ]);
    assert.equal(declarationLine({ facet: 'inputs', change: 'added', entry: postcodeEntry }), `-- Database gate: ${allChanges[1]}`);
    for (const line of ['carrier added example-post', 'carriers adds example-post', 'carriers added ', 'carriers  added example-post']) {
      assert.throws(() => declaredChanges([migration('20261017100000_example_post', line)]), /does not understand/, line);
    }
  });

  it('understands the declarations in supabase/migrations', async () => {
    const migrations = await readMigrations();
    assert.deepEqual(migrations.map(({ name }) => name), [...migrations.map(({ name }) => name)].sort());
    assert.ok(declaredChanges(migrations).some(({ migration: name }) => name === '20261017234500_gls_austria_postcode'));
  });
});

describe('peer pins', () => {
  it('reports an exact peer version the app does not have', () => {
    const peers = { 'playwright-core': '1.64.0', sharp: '0.35.5', 'onnxruntime-web': '^1.30.0', absent: '2.0.0' };
    const installed = { 'playwright-core': '1.63.0', sharp: '0.35.5', 'onnxruntime-web': '1.29.0' };
    assert.deepEqual(peerMismatches(peers, installed), [
      { name: 'playwright-core', expected: '1.64.0', installed: '1.63.0' },
    ]);
    assert.deepEqual(peerMismatches(undefined, installed), []);
  });
});

describe('waiting for the registry', () => {
  it('retries until the release is served', async () => {
    const pauses = [];
    let calls = 0;
    const result = await retry(async () => {
      calls += 1;
      if (calls < 3) throw new Error('not served yet');
      return 'served';
    }, { attempts: 5, pauseMs: 10, pause: async (ms) => { pauses.push(ms); } });
    assert.equal(result, 'served');
    assert.deepEqual(pauses, [10, 10]);
  });

  it('waits for the tarball of a release npm already lists', async () => {
    const listed = [{}, { '0.3.0': 'https://registry.example/0.3.0.tgz' }];
    const answers = [false, false, true];
    const pauses = [];
    await waitUntilServed('0.3.0', {
      tarballs: async () => listed.shift() ?? { '0.3.0': 'https://registry.example/0.3.0.tgz' },
      isServed: async (url) => {
        assert.equal(url, 'https://registry.example/0.3.0.tgz');
        return answers.shift();
      },
      attempts: 5, pauseMs: 10, pause: async (ms) => { pauses.push(ms); },
    });
    assert.deepEqual(pauses, [10, 10, 10]);
    assert.deepEqual(answers, []);
  });

  it('stops waiting for a tarball that never comes', async () => {
    let asked = 0;
    await assert.rejects(waitUntilServed('0.3.0', {
      tarballs: async () => ({ '0.3.0': 'https://registry.example/0.3.0.tgz' }),
      isServed: async () => { asked += 1; return false; },
      attempts: 3, pauseMs: 0, pause: async () => {},
    }), /npm does not serve universal-parcel-scraper@0.3.0/);
    assert.equal(asked, 3);
  });

  it('gives up with the last failure', async () => {
    let calls = 0;
    await assert.rejects(retry(async () => {
      calls += 1;
      throw new Error(`attempt ${calls}`);
    }, { attempts: 3, pauseMs: 0, pause: async () => {} }), /attempt 3/);
    assert.equal(calls, 3);
  });
});
