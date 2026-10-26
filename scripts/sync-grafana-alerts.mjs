// Makes the production Grafana's alert rules match ops/grafana/alerts.json, in a folder of
// their own. Dry run by default: prints what would be created, updated and deleted.
// --apply saves every alert rule and the notification policies on the host, then replaces
// the rule group in one request; --status prints how each rule last evaluated.
//
// Requests run through curl inside the Grafana container, over ssh, so the admin
// credentials never leave it (grafana-host.mjs). The host, container and data sources come
// from the environment: GRAFANA_SSH_HOST, GRAFANA_CONTAINER, GRAFANA_PROMETHEUS_UID,
// GRAFANA_LOKI_UID and GRAFANA_METRICS_UID (the PostgreSQL source that reads the
// metrics schema). GRAFANA_BACKUP_DIR sets where backups go on the host.
import { readFileSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { backup, grafana, sortedKeys } from './grafana-host.mjs';

const DATASOURCE_TYPES = { prometheus: 'prometheus', loki: 'loki', metrics: 'grafana-postgresql-datasource' };
const DATASOURCE_ENV = { prometheus: 'GRAFANA_PROMETHEUS_UID', loki: 'GRAFANA_LOKI_UID', metrics: 'GRAFANA_METRICS_UID' };
const DEFAULT_BACKUP_DIR = '/var/lib/delivery-tracker-grafana-alerts/backups';

export function loadAlerts() {
  return JSON.parse(readFileSync(new URL('../ops/grafana/alerts.json', import.meta.url), 'utf8'));
}

function query(refId, source, datasources) {
  const uid = datasources[source.datasource];
  if (!uid) throw new Error(`No data source uid for ${source.datasource}: set ${DATASOURCE_ENV[source.datasource]}`);
  const datasource = { type: DATASOURCE_TYPES[source.datasource], uid };
  const model = source.datasource === 'metrics'
    ? { datasource, editorMode: 'code', format: 'time_series', rawQuery: true, rawSql: source.sql, refId }
    : source.datasource === 'loki'
      ? { datasource, editorMode: 'code', expr: source.expr, queryType: 'instant', refId }
      : { datasource, editorMode: 'code', expr: source.expr, instant: true, range: false, refId };
  // Loki counts over its own range; the others read the latest value.
  const from = source.datasource === 'loki' ? 900 : 600;
  return { refId, relativeTimeRange: { from, to: 0 }, datasourceUid: uid, model };
}

// A classic condition makes one alert of all a rule's series. A rule with onlyWhile fires
// only while that query is above 0.5 too, so one outage is one alert.
function condition(rule) {
  const above = (refId, threshold) => ({ evaluator: { params: [threshold], type: 'gt' }, operator: { type: 'and' },
    query: { params: [refId] }, reducer: { params: [], type: 'last' }, type: 'query' });
  const conditions = [above('A', rule.threshold ?? 0.5)];
  if (rule.onlyWhile) conditions.push(above('G', 0.5));
  return { refId: 'C', relativeTimeRange: { from: 0, to: 0 }, datasourceUid: '__expr__',
    model: { type: 'classic_conditions', conditions, datasource: { type: '__expr__', uid: '__expr__' }, refId: 'C' } };
}

// Grafana's default notification policy keeps alerts in Grafana; a rule with "notify": "user"
// gets the notify=user label, which a policy of its own sends on to the user.
function labels(rule) {
  if (rule.notify !== undefined && rule.notify !== 'user') throw new Error(`${rule.uid}: notify can only be "user"`);
  return { service: 'delivery-tracker', severity: rule.severity, ...(rule.notify && { notify: rule.notify }) };
}

export function provisionedRule(rule, alerts, datasources) {
  const data = [query('A', rule, datasources)];
  if (rule.onlyWhile) data.push(query('G', rule.onlyWhile, datasources));
  data.push(condition(rule));
  return {
    uid: rule.uid,
    title: rule.title,
    folderUID: alerts.folderUid,
    ruleGroup: alerts.ruleGroup,
    condition: 'C',
    data,
    noDataState: rule.noDataState,
    execErrState: rule.execErrState,
    for: rule.for,
    annotations: { summary: rule.summary, description: rule.description },
    labels: labels(rule),
    isPaused: false,
  };
}

export function provisionedGroup(alerts, datasources) {
  return {
    title: alerts.ruleGroup,
    folderUid: alerts.folderUid,
    interval: alerts.intervalSeconds,
    rules: alerts.rules.map(rule => provisionedRule(rule, alerts, datasources)),
  };
}

/** What the source decides about a rule; Grafana adds fields of its own to what it stores. */
export function comparableRule(rule) {
  return JSON.stringify(sortedKeys({
    title: rule.title,
    ruleGroup: rule.ruleGroup,
    condition: rule.condition,
    data: rule.data.map(entry => ({
      refId: entry.refId,
      datasourceUid: entry.datasourceUid,
      relativeTimeRange: entry.relativeTimeRange,
      query: (entry.model?.expr ?? entry.model?.rawSql)?.replace(/\s+/g, ' '),
      type: entry.model?.type,
      conditions: entry.model?.conditions?.map(item => [item.query?.params, item.evaluator]),
    })),
    for: rule.for,
    noDataState: rule.noDataState,
    execErrState: rule.execErrState,
    annotations: rule.annotations,
    labels: rule.labels,
    isPaused: rule.isPaused,
  }));
}

/** The rules to create, update and delete for the live group to match the source. */
export function plan(group, liveRules) {
  const live = new Map(liveRules.map(rule => [rule.uid, rule]));
  const desired = new Map(group.rules.map(rule => [rule.uid, rule]));
  return {
    create: [...desired.keys()].filter(uid => !live.has(uid)),
    update: [...desired.keys()].filter(uid => live.has(uid) && comparableRule(live.get(uid)) !== comparableRule(desired.get(uid))),
    remove: [...live.keys()].filter(uid => !desired.has(uid)),
  };
}

function status(alerts) {
  const groups = grafana('GET', '/api/prometheus/grafana/api/v1/rules').data.groups
    .filter(group => group.name === alerts.ruleGroup
      && (group.folderUid === alerts.folderUid || group.file === alerts.folderTitle));
  const rules = groups.flatMap(group => group.rules);
  for (const rule of rules) {
    console.log(`${rule.name}: ${rule.state}, health ${rule.health}${rule.lastError ? ` (${rule.lastError})` : ''}`);
  }
  const missing = alerts.rules.filter(rule => !rules.some(live => live.name === rule.title));
  for (const rule of missing) console.log(`${rule.title}: not installed`);
  return missing.length === 0 && rules.every(rule => rule.health === 'ok');
}

function main() {
  const alerts = loadAlerts();
  if (process.argv.includes('--status')) process.exit(status(alerts) ? 0 : 1);

  const datasources = Object.fromEntries(Object.entries(DATASOURCE_ENV).map(([key, name]) => [key, process.env[name]]));
  const group = provisionedGroup(alerts, datasources);
  const folder = grafana('GET', `/api/folders/${alerts.folderUid}`, undefined, { allowMissing: true });
  const liveGroup = folder
    ? grafana('GET', `/api/v1/provisioning/folder/${alerts.folderUid}/rule-groups/${encodeURIComponent(alerts.ruleGroup)}`,
      undefined, { allowMissing: true })
    : null;
  const changes = plan(group, liveGroup?.rules ?? []);
  const intervalChanged = liveGroup && liveGroup.interval !== group.interval;
  console.log(`folder ${alerts.folderUid}: ${folder ? 'exists' : 'create'}`);
  console.log(`${alerts.ruleGroup}: ${liveGroup?.rules.length ?? 0} live, ${group.rules.length} in source`
    + (intervalChanged ? `, interval ${liveGroup.interval}s to ${group.interval}s` : ''));
  console.log(`create: ${changes.create.join(', ') || '-'}`);
  console.log(`update: ${changes.update.join(', ') || '-'}`);
  console.log(`delete: ${changes.remove.join(', ') || '-'}`);
  const changed = !folder || intervalChanged || Object.values(changes).some(list => list.length);
  if (!process.argv.includes('--apply')) {
    console.log(changed ? 'Dry run. Re-run with --apply to change Grafana.' : 'Nothing to change.');
    return;
  }
  if (!changed) return;

  console.log(`backup: ${backup({
    'alert-rules.json': grafana('GET', '/api/v1/provisioning/alert-rules'),
    'notification-policies.json': grafana('GET', '/api/v1/provisioning/policies'),
    'folder.json': folder,
  }, DEFAULT_BACKUP_DIR)}`);
  if (!folder) grafana('POST', '/api/folders', { uid: alerts.folderUid, title: alerts.folderTitle });
  grafana('PUT', `/api/v1/provisioning/folder/${alerts.folderUid}/rule-groups/${encodeURIComponent(alerts.ruleGroup)}`, group);
  console.log('Applied. Run with --status in a minute to see each rule evaluate.');
}

if (process.argv[1] && path.resolve(process.argv[1]) === fileURLToPath(import.meta.url)) main();
