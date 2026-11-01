// Grafana's HTTP API, reached through curl inside the Grafana container, over ssh, so the admin
// credentials never leave it. GRAFANA_SSH_HOST and GRAFANA_CONTAINER name the host and container.
// The credentials reach curl as a config line through a pipe on file descriptor 3, which the
// shell's printf builtin feeds, so they are in no command line either (the host's ps lists the
// container's processes); a request's body stays on stdin, which curl reads without opening a
// path (/dev/fd cannot reopen a socket, as stdin can be).
import { spawnSync } from 'node:child_process';

function required(name) {
  const value = process.env[name];
  if (!value) throw new Error(`Set ${name}`);
  return value;
}

function ssh(command, input = '') {
  // The rule list covers every folder in Grafana, well over spawnSync's default 1 MB.
  const result = spawnSync('ssh', ['-o', 'BatchMode=yes', required('GRAFANA_SSH_HOST'), command],
    { input, encoding: 'utf8', maxBuffer: 64 * 1024 * 1024 });
  if (result.error) throw new Error(`ssh failed: ${result.error.message}`);
  if (result.status !== 0) throw new Error(`ssh failed: ${(result.stderr || result.stdout).trim()}`);
  return result.stdout;
}

const quote = value => `'${value.replaceAll("'", "'\\''")}'`;

// curl's `user` config line from the container's admin credentials; sed escapes a quote or a backslash.
const CREDENTIALS = String.raw`printf '%s:%s' "$GF_SECURITY_ADMIN_USER" "$GF_SECURITY_ADMIN_PASSWORD" | sed 's/[\\"]/\\&/g; 1s/^/user = "/; $s/$/"/'`;

export function grafana(method, apiPath, body, { allowMissing = false } = {}) {
  const curl = [
    'exec 4<&0;',
    CREDENTIALS,
    '| curl --config /dev/fd/3 -sS -w "\\n%{http_code}"',
    `-X ${method}`,
    "-H 'Content-Type: application/json'",
    body === undefined ? '' : '--data-binary @-',
    quote(`http://localhost:3000${apiPath}`),
    '3<&0 <&4',
  ].join(' ');
  const output = ssh(`sudo -n docker exec -i ${quote(required('GRAFANA_CONTAINER'))} sh -c ${quote(curl)}`,
    body === undefined ? '' : JSON.stringify(body)).trimEnd();
  const status = Number(output.slice(output.lastIndexOf('\n') + 1));
  const text = output.slice(0, output.lastIndexOf('\n'));
  if (status === 404 && allowMissing) return null;
  if (status < 200 || status >= 300) throw new Error(`${method} ${apiPath} answered ${status}: ${text}`);
  return text ? JSON.parse(text) : null;
}

/** Saves each file in a new directory on the host, under GRAFANA_BACKUP_DIR or the default, readable by root only. */
export function backup(files, defaultDirectory) {
  const directory = `${process.env.GRAFANA_BACKUP_DIR ?? defaultDirectory}/${new Date().toISOString().replace(/[:.]/g, '-')}`;
  for (const [name, content] of Object.entries(files)) {
    const file = quote(`${directory}/${name}`);
    ssh(`sudo -n sh -c ${quote(`umask 077 && mkdir -p ${quote(directory)} && cat > ${file}`)}`, `${JSON.stringify(content, null, 2)}\n`);
  }
  return directory;
}

export function sortedKeys(value) {
  if (Array.isArray(value)) return value.map(sortedKeys);
  if (!value || typeof value !== 'object') return value;
  return Object.fromEntries(Object.keys(value).sort().map(key => [key, sortedKeys(value[key])]));
}
