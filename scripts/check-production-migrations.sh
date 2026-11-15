#!/usr/bin/env bash
# Refuses a deployment whose migrations the database lacks. Migrations are applied by
# hand before the server that needs them, and each one records itself when it runs. One
# that must follow the server it serves starts with the line "-- After its server.": it may
# be missing, and the run names it, to apply once the commit is live.
set -euo pipefail

supabase_url=${SUPABASE_URL:-}
supabase_key=${SUPABASE_PUBLISHABLE_KEY:-}
if [[ -z "$supabase_url" || -z "$supabase_key" ]]; then
  echo "SUPABASE_URL and SUPABASE_PUBLISHABLE_KEY are required" >&2
  exit 2
fi

repo_root=$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)
names=$(find "$repo_root/supabase/migrations" -maxdepth 1 -type f -name '*.sql' -exec basename {} .sql \; \
  | sort | jq -R . | jq -cs '{p_names: .}')

workdir=$(mktemp -d)
trap 'rm -rf "$workdir"' EXIT
status=000
for attempt in 1 2 3; do
  status=$(curl --silent --show-error --max-time 20 -o "$workdir/missing.json" -w '%{http_code}' \
    -X POST "${supabase_url%/}/rest/v1/rpc/missing_migrations" \
    -H "apikey: $supabase_key" -H 'Content-Type: application/json' -d "$names") || status=000
  [[ "$status" == 200 ]] && break
  [[ "$attempt" == 3 ]] || sleep 5
done
if [[ "$status" != 200 ]]; then
  echo "The database did not say which migrations it has (HTTP $status):" >&2
  head -c 500 "$workdir/missing.json" >&2 2>/dev/null || true
  echo >&2
  exit 1
fi
# A proxy's or PostgREST's error page can come with a 200: an answer that is not a list of
# names stops the deployment, as one that says nothing would.
if ! jq -e 'type == "array" and all(.[]; type == "string")' "$workdir/missing.json" > /dev/null 2>&1; then
  echo "The database's answer is not a list of migration names:" >&2
  head -c 500 "$workdir/missing.json" >&2 2>/dev/null || true
  echo >&2
  exit 1
fi

blocking=()
later=()
while IFS= read -r name; do
  [[ -n "$name" ]] || continue
  if [[ "$(head -n 1 "$repo_root/supabase/migrations/$name.sql")" == '-- After its server.' ]]; then
    later+=("$name")
  else
    blocking+=("$name")
  fi
done < <(jq -r '.[]' "$workdir/missing.json")
if (( ${#blocking[@]} )); then
  echo "Production lacks these migrations. Apply them, then re-run this job:" >&2
  printf '  %s\n' "${blocking[@]}" >&2
  exit 1
fi
for name in ${later[@]+"${later[@]}"}; do
  echo "::notice title=Apply after this deploy::$name follows its server: apply it once this commit is live."
done
total=$(jq '.p_names | length' <<< "$names")
if (( ${#later[@]} )); then
  echo "Production has $((total - ${#later[@]})) of $total migrations; the other ${#later[@]} follow their server."
else
  echo "Production has all $total migrations."
fi
