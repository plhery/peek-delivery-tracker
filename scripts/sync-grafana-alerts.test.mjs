import assert from 'node:assert/strict';
import { describe, it } from 'node:test';
import { comparableRule, loadAlerts, plan, provisionedGroup } from './sync-grafana-alerts.mjs';

const alerts = loadAlerts();
const datasources = { prometheus: 'prom', loki: 'logs', metrics: 'counts' };
const group = provisionedGroup(alerts, datasources);

describe('the alert rules', () => {
  it('each say what fired and what to look at', () => {
    assert.equal(new Set(alerts.rules.map(rule => rule.uid)).size, alerts.rules.length);
    for (const rule of alerts.rules) {
      assert.ok(rule.title && rule.summary && rule.description, rule.uid);
      assert.match(rule.severity, /^(critical|warning)$/, rule.uid);
      assert.match(rule.for, /^\d+[smh]$/, rule.uid);
      assert.match(rule.noDataState, /^(Alerting|OK|NoData|KeepLast)$/, rule.uid);
      assert.match(rule.execErrState, /^(Alerting|OK|Error|KeepLast)$/, rule.uid);
    }
  });

  it('read the database through the counts of metrics.health only', () => {
    for (const rule of alerts.rules.filter(item => item.datasource === 'metrics')) {
      assert.match(rule.sql, /^select now\(\) as time, .+ as value from metrics\.health$/, rule.uid);
    }
  });

  it('point each query at its data source', () => {
    for (const rule of group.rules) {
      for (const entry of rule.data.filter(item => item.refId !== 'C')) {
        assert.ok(Object.values(datasources).includes(entry.datasourceUid), rule.uid);
        assert.equal(entry.model.datasource.uid, entry.datasourceUid);
      }
      assert.equal(rule.condition, 'C');
      assert.equal(rule.labels.service, 'delivery-tracker');
    }
    assert.throws(() => provisionedGroup(alerts, { ...datasources, loki: undefined }), /GRAFANA_LOKI_UID/);
  });

  it('reach the user only when the source says notify: user', () => {
    for (const [index, rule] of group.rules.entries()) {
      const source = alerts.rules[index];
      assert.deepEqual(rule.labels, source.notify
        ? { service: 'delivery-tracker', severity: source.severity, notify: 'user' }
        : { service: 'delivery-tracker', severity: source.severity }, rule.uid);
    }
    assert.deepEqual(group.rules.filter(rule => rule.labels.notify).map(rule => rule.uid), ['peek-down-15m']);
    const [first] = alerts.rules;
    assert.throws(() => provisionedGroup({ ...alerts, rules: [{ ...first, notify: 'everyone' }] }, datasources),
      /notify can only be "user"/);
  });

  it('page the user only for an outage that lasts 15 minutes, never for missing data', () => {
    const down = alerts.rules.find(rule => rule.uid === 'peek-down-15m');
    const unreachable = alerts.rules.find(rule => rule.uid === 'peek-unreachable');
    assert.equal(unreachable.expr, `absent(up{job="delivery-tracker"}) or ${down.expr}`);
    assert.deepEqual([down.for, down.severity, down.noDataState], ['15m', 'critical', 'OK']);
    assert.equal(unreachable.notify, undefined);
  });

  it('fire a rule with onlyWhile only while both queries are above their thresholds', () => {
    const gated = group.rules.find(rule => rule.data.some(entry => entry.refId === 'G'));
    const conditions = gated.data.at(-1).model.conditions;
    assert.deepEqual(conditions.map(item => [item.query.params[0], item.operator.type]), [['A', 'and'], ['G', 'and']]);
  });
});

describe('a sync', () => {
  const stored = rule => ({ ...rule, id: 7, orgID: 1, updated: '2026-01-01T00:00:00Z', provenance: 'api',
    data: rule.data.map(entry => ({ ...entry, model: { ...entry.model, intervalMs: 1000, maxDataPoints: 43200 } })) });

  it('leaves alone what Grafana stored as the source says', () => {
    assert.deepEqual(plan(group, group.rules.map(stored)), { create: [], update: [], remove: [] });
  });

  it('creates new rules, updates changed ones and deletes the ones the source dropped', () => {
    const [first, second, ...rest] = group.rules;
    const live = [stored({ ...second, for: '1m' }), ...rest.map(stored), { ...stored(first), uid: 'gone' }];
    assert.deepEqual(plan(group, live), { create: [first.uid], update: [second.uid], remove: ['gone'] });
  });

  it('sees a changed threshold', () => {
    const [first] = group.rules;
    const changed = structuredClone(first);
    changed.data.at(-1).model.conditions[0].evaluator.params = [9];
    assert.notEqual(comparableRule(changed), comparableRule(first));
  });

  it('puts back a notify label Grafana lost', () => {
    const notified = group.rules.find(rule => rule.labels.notify);
    const live = group.rules.map(rule => stored(rule === notified
      ? { ...rule, labels: { service: rule.labels.service, severity: rule.labels.severity } } : rule));
    assert.deepEqual(plan(group, live), { create: [], update: [notified.uid], remove: [] });
  });
});
