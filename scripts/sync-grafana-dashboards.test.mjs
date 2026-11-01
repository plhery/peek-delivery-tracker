import assert from 'node:assert/strict';
import { describe, it } from 'node:test';
import { changedPanels, comparableDashboard, loadDashboards, syncedDashboard } from './sync-grafana-dashboards.mjs';

const dashboards = loadDashboards();

describe('the dashboards', () => {
  it('each have a uid and title of their own', () => {
    assert.ok(dashboards.length >= 2);
    assert.equal(new Set(dashboards.map(({ dashboard }) => dashboard.uid)).size, dashboards.length);
    for (const { file, dashboard } of dashboards) assert.ok(dashboard.uid && dashboard.title, file);
  });

  it('gives every panel its own id, including panels in collapsed rows', () => {
    const flatten = panels => panels.flatMap(panel => [panel, ...flatten(panel.panels ?? [])]);
    for (const { file, dashboard } of dashboards) {
      const panels = flatten(dashboard.panels);
      assert.equal(new Set(panels.map(panel => panel.id)).size, panels.length, file);
    }
  });

  it('name no Grafana id, version or data source of one install', () => {
    for (const { file, dashboard } of dashboards) {
      assert.equal(dashboard.id ?? null, null, file);
      assert.equal(dashboard.version, undefined, file);
      const uids = JSON.stringify(dashboard).match(/"datasource":\{[^}]*"uid":"[^"]*"/g) ?? [];
      for (const uid of uids) assert.match(uid, /"uid":"(\$\{\w+\}|-- Grafana --)"/, file);
    }
  });
});

describe('a sync', () => {
  const [{ dashboard }] = dashboards;
  const variable = dashboard.templating.list[0];
  const chosen = { text: 'Production', value: 'prod-uid' };
  const live = { ...structuredClone(dashboard), id: 12, version: 9,
    templating: { ...dashboard.templating, list: dashboard.templating.list.map(item => item.name === variable.name ? { ...item, current: chosen } : item) } };

  it('leaves alone what Grafana stored as the source says', () => {
    assert.equal(comparableDashboard(live), comparableDashboard(dashboard));
    assert.deepEqual(changedPanels(dashboard, live), []);
  });

  it('names the panels that changed', () => {
    const edited = structuredClone(live);
    edited.panels[0].description = 'Edited in Grafana';
    assert.notEqual(comparableDashboard(edited), comparableDashboard(dashboard));
    assert.deepEqual(changedPanels(dashboard, edited), [dashboard.panels[0].title]);
  });

  it('writes the source over the version it read, with the data sources chosen in Grafana', () => {
    const synced = syncedDashboard(dashboard, live);
    assert.deepEqual([synced.id, synced.version], [12, 9]);
    assert.deepEqual(synced.templating.list.find(item => item.name === variable.name).current, chosen);
    assert.equal(comparableDashboard(synced), comparableDashboard(dashboard));
    assert.equal(syncedDashboard(dashboard, null).id, null);
  });
});
