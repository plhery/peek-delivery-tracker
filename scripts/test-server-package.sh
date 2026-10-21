#!/usr/bin/env bash
set -euo pipefail

format="${1:?Expected deb or apk}"
directory="${2:?Expected package output directory}"
upgrade_directory="${3:?Expected fixture upgrade package directory}"
upgrade_version="${4:?Expected fixture upgrade version}"
: "${PEEK_RELEASE_VERSION:?}"
: "${PEEK_RELEASE_CHANNEL:?}"
: "${PEEK_RELEASE_SHA:?}"
name=peek-server
if [ "$PEEK_RELEASE_CHANNEL" = nightly ]; then name=peek-server-nightly; fi
case "$format" in
  deb)
    package="$directory/$name-$PEEK_RELEASE_VERSION-linux-amd64.deb"
    upgrade_package="$upgrade_directory/$name-$upgrade_version-linux-amd64.deb"
    apt-get install -y --no-install-recommends "$package"
    node="/usr/lib/$name/runtime/bin/node"
    tini=/usr/bin/tini
    test -f "/usr/lib/systemd/system/$name.service"
    ;;
  apk)
    package="$directory/$name-$PEEK_RELEASE_VERSION-linux-x86_64.apk"
    upgrade_package="$upgrade_directory/$name-$upgrade_version-linux-x86_64.apk"
    install -m 0644 "$directory/peek-server-apk.rsa.pub" /etc/apk/keys/peek-server-apk.rsa.pub
    # Trust is installed explicitly from the isolated CI signing fixture. No
    # signature or dependency checks are disabled during package installation.
    apk add --no-cache "$package"
    node=/usr/bin/node
    tini=/sbin/tini
    test -x "/etc/init.d/$name"
    ;;
  *) exit 2 ;;
esac
test -x "$node"
test -x "$tini"
test "$(id -u "$name")" != 0
app="/usr/lib/$name/app"
launcher="/usr/lib/$name/launcher.mjs"
config="/etc/$name/server.env"
jq --exit-status --arg version "$PEEK_RELEASE_VERSION" --arg sha "$PEEK_RELEASE_SHA" --arg channel "$PEEK_RELEASE_CHANNEL" \
  '.version == $version and .sha == $sha and .channel == $channel and .configuration == "runtime" and .architecture == "amd64"' \
  "/usr/share/doc/$name/build-info.json"
"$node" "$launcher" --version | grep --fixed-strings "$PEEK_RELEASE_SHA"
if "$node" "$launcher" --check-environment > /tmp/peek-unconfigured.log 2>&1; then
  echo 'A fresh unconfigured server must fail before startup' >&2
  exit 1
fi
grep --fixed-strings 'SUPABASE_URL' /tmp/peek-unconfigured.log
cat > "$config" <<'CONFIG'
HOSTNAME=127.0.0.1
PORT=3000
SUPABASE_URL=http://127.0.0.1:9
SUPABASE_PUBLIC_URL=https://synthetic-auth.invalid
SUPABASE_PUBLISHABLE_KEY=synthetic-public-key
SUPABASE_SERVICE_ROLE_KEY=synthetic-private-service-canary
NEXT_PUBLIC_USE_API=true
NEXT_PUBLIC_AUTH_GOOGLE_ENABLED=false
NEXT_PUBLIC_AUTH_APPLE_ENABLED=false
NEXT_PUBLIC_AUTH_EMAIL_OTP_ENABLED=true
CONFIG
chown root:"$name" "$config"
chmod 0640 "$config"
su -s /bin/sh -c "'$node' '$launcher' --check-environment" "$name"
su -s /bin/sh -c "set -e; test ! -w '$app/server.js'; touch '/var/cache/$name/next/write-test'; printf 'preserved' > '/var/lib/$name/package-smoke-state'" "$name"
sha256sum "$config" > /tmp/peek-config.sha256

service_pid=''
stop_server() {
  if [ -n "$service_pid" ] && kill -0 "$service_pid" 2>/dev/null; then
    kill -TERM -- "-$service_pid" 2>/dev/null || true
    for _ in $(seq 1 30); do
      kill -0 "$service_pid" 2>/dev/null || break
      sleep 1
    done
    if kill -0 "$service_pid" 2>/dev/null; then
      echo 'Native server did not shut down within its service grace period' >&2
      kill -KILL -- "-$service_pid" || true
      return 1
    fi
    wait "$service_pid" || true
  fi
  service_pid=''
}
trap 'stop_server; cat /tmp/peek-native-server.log 2>/dev/null || true' EXIT
start_server() {
  # Docker sets HOSTNAME in this test environment; remove only that harness
  # value so the operator's dotenv configuration selects the listen address.
  setsid su -s /bin/sh -c "cd '$app' && exec env -u HOSTNAME '$tini' -s -- '$node' '$launcher'" "$name" > /tmp/peek-native-server.log 2>&1 &
  service_pid=$!
  for _ in $(seq 1 30); do
    curl --fail --silent http://127.0.0.1:3000/health/live >/dev/null && return
    kill -0 "$service_pid" 2>/dev/null || { cat /tmp/peek-native-server.log; return 1; }
    sleep 1
  done
  echo 'Native server never became live' >&2
  return 1
}
start_server
SMOKE_EXPECT_READY=false bash /source/scripts/smoke-url.sh http://127.0.0.1:3000
su -s /bin/sh -c "cd '$app' && TRACKING_CHROMIUM_PATH=/usr/bin/chromium exec '$tini' -s -- '$node' --input-type=module" "$name" < /source/scripts/test-tracking-runtime.mjs
stop_server

# Refuse a legacy or tampered cache symlink before changing ownership. This
# direct rendered-hook regression is separate from the real package upgrade
# below. The fixture is root-created; the hook must never chown its target.
sed "s/@PACKAGE_NAME@/$name/g" "/source/packaging/server/$format-postinstall" > /tmp/peek-postinstall-fixture
mkdir /tmp/peek-cache-victim
victim_owner="$(stat -c '%u:%g' /tmp/peek-cache-victim)"
mv "/var/cache/$name/next" "/var/cache/$name/next.saved"
ln -s /tmp/peek-cache-victim "/var/cache/$name/next"
if sh /tmp/peek-postinstall-fixture > /tmp/peek-postinstall-fixture.log 2>&1; then
  echo 'Package hooks accepted a cache symlink' >&2
  exit 1
fi
grep --fixed-strings 'Refusing a symlink at' /tmp/peek-postinstall-fixture.log
test "$(stat -c '%u:%g' /tmp/peek-cache-victim)" = "$victim_owner"
rm "/var/cache/$name/next"
mv "/var/cache/$name/next.saved" "/var/cache/$name/next"
sh /tmp/peek-postinstall-fixture
case "$format" in
  deb) dpkg --install "$upgrade_package" ;;
  apk) apk add --no-cache "$upgrade_package" ;;
esac
jq --exit-status --arg version "$upgrade_version" '.version == $version' "/usr/share/doc/$name/build-info.json"
sha256sum --check /tmp/peek-config.sha256
test "$(cat "/var/lib/$name/package-smoke-state")" = preserved
test -f "/var/cache/$name/next/write-test"
start_server
curl --fail --silent http://127.0.0.1:3000/health/live >/dev/null
stop_server
case "$format" in
  deb) dpkg --remove "$name" ;;
  apk) apk del "$name" ;;
esac
test ! -f "$app/server.js"
test "$(cat "/var/lib/$name/package-smoke-state")" = preserved
echo "$format: installed native server, runtime/browser dependencies, upgrade/config preservation and removal passed"
