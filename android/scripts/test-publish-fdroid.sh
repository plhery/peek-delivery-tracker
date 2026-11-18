#!/usr/bin/env bash
# Exercise interrupted publication with local fixtures. Git and both publishing tools are stand-ins.
set -eu
scripts=$(cd "$(dirname "$0")" && pwd)
fixture=$(mktemp -d)
trap 'rm -rf "$fixture"' EXIT
export TMPDIR="$fixture" PEEK_FDROID_DIR="$fixture/index" ANDROID_HOME="$fixture/sdk"
export calls="$fixture/calls" record="$fixture/record"
mkdir -p "$ANDROID_HOME/build-tools/37" "$PEEK_FDROID_DIR/repo"
touch "$fixture/demo.apk"
cat > "$ANDROID_HOME/build-tools/37/apksigner" <<'TOOL'
#!/bin/sh
echo 'Signer #1 certificate SHA-256 digest: f433cbf03c68766d1e6b321d1020e8fee51f75780b56c0393ff7420f93103f73'
TOOL
cat > "$ANDROID_HOME/build-tools/37/aapt2" <<'TOOL'
#!/bin/sh
echo "package: name='com.plhery.peekdeliverytracker' versionCode='1' versionName='fixture'"
TOOL
chmod +x "$ANDROID_HOME/build-tools/37/"*
fdroid() { :; }
keytool() { echo 'SHA256: 12:34'; }
git() {
  echo "git $*" >> "$calls"
  case " $* " in
    *' rev-parse '*) echo fixture-source ;;
    *' ls-remote '*) echo fixture-ref ;;
    *' show FETCH_HEAD:README.md '*) cat "$record" ;;
  esac
}
gh() {
  echo "gh $*" >> "$calls"
  if [[ $1 == release && $2 == view ]]; then
    [[ $mode != missing ]] || return 1
    if [[ $mode == complete ]]; then echo peek-fixture-1.apk; fi
  fi
}
export -f git gh fdroid keytool
export mode
echo 'Peek for Android fixture (1), the beta, is built from https://github.com/plhery/peek-delivery-tracker/tree/fixture-source' > "$record"
for mode in missing incomplete complete; do
  : > "$calls"
  bash "$scripts/publish-fdroid.sh" "$fixture/demo.apk" fixture-source >/dev/null
  if grep -q 'push .*refs/tags/' "$calls"; then exit 1; fi
  case "$mode" in
    missing) grep -q 'gh release create ' "$calls" ;;
    incomplete) grep -q 'gh release upload ' "$calls" ;;
    complete) if grep -q 'gh release upload ' "$calls"; then exit 1; fi ;;
  esac
done
echo 'a different source' > "$record"
: > "$calls"
if bash "$scripts/publish-fdroid.sh" "$fixture/demo.apk" fixture-source; then exit 1; fi
if grep -Eq 'push |gh release' "$calls"; then exit 1; fi
