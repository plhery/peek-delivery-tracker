import assert from 'node:assert/strict';
import { existsSync, mkdirSync, mkdtempSync, readFileSync, rmSync, writeFileSync } from 'node:fs';
import { tmpdir } from 'node:os';
import path from 'node:path';
import { after, beforeEach, describe, it } from 'node:test';
import { grafana } from './grafana-host.mjs';

// Fake ssh, sudo and docker run the command here, and a fake curl records its arguments, its stdin
// (the config) and the file it sends; each fake also logs its own arguments, as ps would show them.
const directory = mkdtempSync(path.join(tmpdir(), 'grafana-host-'));
after(() => rmSync(directory, { recursive: true, force: true }));
const bin = path.join(directory, 'bin');
const log = path.join(directory, 'log');
mkdirSync(bin);
const fake = (name, body) => writeFileSync(path.join(bin, name),
  `#!/bin/bash\nprintf '%s\\n' "${name} $*" >>"$GRAFANA_TEST_LOG/commands"\n${body}\n`, { mode: 0o755 });
fake('ssh', 'exec sh -c "$4"');
fake('sudo', '[ "$1" = -n ] && shift\nexec "$@"');
fake('docker', '[ "$1" = exec ] || exit 9\nshift\n[ "$1" = -i ] && shift\nshift\nexec "$@"');
fake('curl', `cat >"$GRAFANA_TEST_LOG/config"
for argument in "$@"; do
  case "$argument" in @*) cat "\${argument#@}" >"$GRAFANA_TEST_LOG/body" ;; esac
done
printf '%s\\n%s' "$GRAFANA_TEST_ANSWER" "$GRAFANA_TEST_STATUS"`);

const PASSWORD = String.raw`pa"ss\wo'rd`;
Object.assign(process.env, {
  PATH: `${bin}${path.delimiter}${process.env.PATH}`, GRAFANA_SSH_HOST: 'grafana-host', GRAFANA_CONTAINER: 'grafana',
  GRAFANA_TEST_LOG: log, GF_SECURITY_ADMIN_USER: 'admin', GF_SECURITY_ADMIN_PASSWORD: PASSWORD,
});
const read = name => existsSync(path.join(log, name)) ? readFileSync(path.join(log, name), 'utf8') : null;

describe("Grafana's API through the container", () => {
  beforeEach(() => {
    rmSync(log, { recursive: true, force: true });
    mkdirSync(log);
    Object.assign(process.env, { GRAFANA_TEST_ANSWER: '{"id":7}', GRAFANA_TEST_STATUS: '200' });
  });

  it('gives curl the admin credentials on stdin and the body on another descriptor, in no command line', () => {
    assert.deepEqual(grafana('POST', '/api/dashboards/db', { dashboard: { uid: 'peek' } }), { id: 7 });
    assert.equal(read('config').trimEnd(), String.raw`user = "admin:pa\"ss\\wo'rd"`);
    assert.equal(read('body'), '{"dashboard":{"uid":"peek"}}');
    const commands = read('commands');
    assert.match(commands, /^curl --config - .*--data-binary @\/dev\/fd\/3 http:\/\/localhost:3000\/api\/dashboards\/db$/m);
    assert.ok(!commands.includes('pa"ss'), commands);
    assert.ok(!commands.includes('-u '), commands);
  });

  it('sends no body with a GET, and reads a missing answer as null when allowed', () => {
    Object.assign(process.env, { GRAFANA_TEST_ANSWER: '{"message":"not found"}', GRAFANA_TEST_STATUS: '404' });
    assert.equal(grafana('GET', '/api/folders/peek', undefined, { allowMissing: true }), null);
    assert.equal(read('body'), null);
    assert.equal(read('config').trimEnd(), String.raw`user = "admin:pa\"ss\\wo'rd"`);
    assert.throws(() => grafana('GET', '/api/folders/peek'), /answered 404/);
  });
});
