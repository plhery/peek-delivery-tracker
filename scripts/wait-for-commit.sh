#!/usr/bin/env bash
set -euo pipefail

# Waits until a site serves the build of a commit: until /health/live names it three answers in a
# row, as the proxy may still send a request to the old container once the new one is healthy.
# deploy.yml runs it after each deploy, so that the smoke check and IndexNow read the new build.
#
#   scripts/wait-for-commit.sh https://peek.example.com <full commit>
#
# WAIT_FOR_COMMIT_TIMEOUT sets how long it waits, in seconds (300 by default), and
# WAIT_FOR_COMMIT_INTERVAL how long between two answers (5 by default).

base_url=${1:-}
commit=${2:-}
if [[ -z "$base_url" || ! "$commit" =~ ^[0-9a-f]{40}$ ]]; then
  echo "Usage: $0 https://peek.example.com <full commit, in lower case>" >&2
  exit 2
fi
base_url=${base_url%/}
timeout=${WAIT_FOR_COMMIT_TIMEOUT:-300}
interval=${WAIT_FOR_COMMIT_INTERVAL:-5}

workdir=$(mktemp -d)
trap 'rm -rf "$workdir"' EXIT
nonce=$(date +%s)-$$

# What the site's /health/live says it serves: a commit, or why it names none.
serving() {
  local status
  if ! status=$(curl --silent --max-time 20 -H 'Cache-Control: no-cache' \
    -o "$workdir/live.json" -w '%{http_code}' "$base_url/health/live?commit=$nonce-$SECONDS"); then
    echo 'no answer'
  elif [[ "$status" != 200 ]]; then
    echo "HTTP $status"
  else
    sed -nE 's/.*"commit"[[:space:]]*:[[:space:]]*"([0-9a-f]{40})".*/\1/p' "$workdir/live.json" | grep . \
      || echo 'a build that names no commit'
  fi
}

in_a_row=0
said=
while :; do
  served=$(serving)
  if [[ "$served" != "$said" ]]; then
    echo "$base_url serves $served"
    said=$served
  fi
  if [[ "$served" == "$commit" ]]; then
    in_a_row=$((in_a_row + 1))
    if [[ $in_a_row == 3 ]]; then
      echo "$base_url served $commit three answers in a row"
      exit 0
    fi
  else
    in_a_row=0
  fi
  if (( SECONDS >= timeout )); then
    echo "After $timeout s, $base_url still serves $served, not $commit." \
      "The new build is not serving, so IndexNow is not told: its pages go with the next deploy." >&2
    exit 1
  fi
  sleep "$interval"
done
