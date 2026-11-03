#!/bin/sh
# Starts a release build of the app on a phone or emulator and fails unless it opens and stays up. What R8 breaks shows
# only when the shrunk app runs: a constructor something makes by reflection, a serializer, a class a library loads by
# name. Run it on every APK made for Google Play before it is uploaded (README.md), and CI runs it on each change.
#
# Usage: scripts/release-smoke.sh <release APK> [device serial]
# The device's build of the app is uninstalled first, with its data: a release can't replace a debug build.
set -eu

apk=$1
serial=${2:-${ANDROID_SERIAL:-}}
package=com.plhery.peekdeliverytracker
seconds=${SMOKE_SECONDS:-15}

device() {
  if [ -n "$serial" ]; then adb -s "$serial" "$@"; else adb "$@"; fi
}

device wait-for-device
device uninstall "$package" >/dev/null 2>&1 || true
device install "$apk" >/dev/null
device logcat -c
device logcat -b crash -c 2>/dev/null || true

# The launcher's own intent, waiting for the first frame: the launch window holds until the first screen is composed.
started=$(device shell am start -W -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -n "$package/.MainActivity")
echo "$started"

# Then the stores are made and the first screen drawn: whatever fails there fails within these seconds.
sleep "$seconds"
pid=$(device shell pidof "$package" | tr -d '\r' || true)
crash=$(device logcat -d -b crash | grep -A 40 "Process: $package" || true)
if [ -z "$crash" ]; then
  crash=$(device logcat -d | grep -A 40 "FATAL EXCEPTION" | grep -A 39 "Process: $package" || true)
fi

if [ -n "$crash" ] || [ -z "$pid" ] || ! echo "$started" | grep -q "Status: ok"; then
  echo "The release build didn't stay up on $(device shell getprop ro.build.version.release | tr -d '\r') (API $(device shell getprop ro.build.version.sdk | tr -d '\r'))."
  [ -n "$crash" ] && echo "$crash"
  exit 1
fi
echo "The release build opened and was up after $seconds s (pid $pid)."
