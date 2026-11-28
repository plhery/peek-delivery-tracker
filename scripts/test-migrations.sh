#!/usr/bin/env bash
set -euo pipefail

repo_root=$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)
database_url=${TEST_DATABASE_URL:-}

# Each migration has a number of its own: two sessions picking the same one leave the order between them to the
# rest of the name. These pairs were applied before the check, under their names.
applied_pairs=" 20260911120000 20260912090000 20261106110000 20261110170000 "
shared=0
while read -r count number; do
  if (( count > 2 )) || [[ "$applied_pairs" != *" $number "* ]]; then
    echo "Migrations share the number $number; give the newer one a later number:" >&2
    find "$repo_root/supabase/migrations" -maxdepth 1 -type f -name "${number}_*.sql" -exec basename {} \; | sort >&2
    shared=1
  fi
done < <(find "$repo_root/supabase/migrations" -maxdepth 1 -type f -name '*.sql' -exec basename {} \; \
  | cut -d_ -f1 | sort | uniq -c | awk '$1 > 1')
if (( shared )); then
  exit 1
fi

# The dry run and the apply wrap each migration in a transaction of their own (docs/DEPLOYMENT.md), as this script
# does: a migration's own commit would end the dry run's for real, and its rollback would only warn. Lines inside a
# dollar-quoted body (plpgsql's `begin` and `end;`) don't count.
wrapped=0
for migration in "$repo_root"/supabase/migrations/*.sql; do
  if awk '!body && tolower($0) ~ /^[[:space:]]*(begin|commit|rollback|end|abort|start[[:space:]]+transaction)([[:space:]]+(work|transaction))?[[:space:]]*;/ { found = 1 }
    { if (gsub(/\$[A-Za-z_]*\$/, "&") % 2) body = !body }
    END { exit !found }' "$migration"; then
    echo "$(basename "$migration") has a transaction of its own: leave begin and commit to the dry run and the apply" >&2
    wrapped=1
  fi
done
if (( wrapped )); then
  exit 1
fi

if [[ -z "$database_url" ]]; then
  echo "TEST_DATABASE_URL is required" >&2
  exit 2
fi

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/bootstrap.sql"

# A migration is applied before the server it serves, and the running server keeps calling the functions it changes
# (docs/DEPLOYMENT.md): each call that worked before a migration must work after it, unless the migration starts with
# "-- After its server.". These two dropped functions before the check, each applied with the server it served.
calls_changed_before=" 20260715000000_shared_backend 20261008230000_drop_on_screen_deliveries "
calls_before=$(mktemp)
trap 'rm -f "$calls_before"' EXIT
server_calls() {
  psql "$database_url" -X -At -v ON_ERROR_STOP=1 "$@" -f "$repo_root/supabase/tests/server_calls.sql"
}
# The check finds a required argument added to a function, and lets an optional one through.
psql "$database_url" -X -q -v ON_ERROR_STOP=1 \
  -c "create function public.server_calls_probe(p_a text) returns boolean language sql as 'select true'"
server_calls > "$calls_before"
psql "$database_url" -X -q -v ON_ERROR_STOP=1 --single-transaction \
  -c "drop function public.server_calls_probe(text)" \
  -c "create function public.server_calls_probe(p_a text, p_b boolean default false) returns boolean language sql as 'select p_b'"
if [[ -n "$(server_calls -v before_file="$calls_before")" ]]; then
  echo "The check of the running server's calls refuses an optional argument" >&2
  exit 1
fi
psql "$database_url" -X -q -v ON_ERROR_STOP=1 --single-transaction \
  -c "drop function public.server_calls_probe(text, boolean)" \
  -c "create function public.server_calls_probe(p_a text, p_b boolean) returns boolean language sql as 'select p_b'"
if [[ "$(server_calls -v before_file="$calls_before")" != *"server_calls_probe(text): a call with (p_a) finds no function"* ]]; then
  echo "The check of the running server's calls misses a required argument" >&2
  exit 1
fi
psql "$database_url" -X -q -v ON_ERROR_STOP=1 -c "drop function public.server_calls_probe(text, boolean)"
broken_calls=0

while IFS= read -r migration; do
  if [[ "$(basename "$migration")" == "20260715000000_shared_backend.sql" ]]; then
    psql "$database_url" -X -v ON_ERROR_STOP=1 \
      -f "$repo_root/supabase/tests/pre_shared_backend.sql"
  fi
  if [[ "$(basename "$migration")" == "20260805200000_restore_user_ownership.sql" ]]; then
    psql "$database_url" -X -v ON_ERROR_STOP=1 \
      -f "$repo_root/supabase/tests/pre_restore_user_ownership.sql"
  fi
  if [[ "$(basename "$migration")" == "20260806130000_fix_to_be_delivered_stage.sql" ]]; then
    psql "$database_url" -X -v ON_ERROR_STOP=1 \
      -f "$repo_root/supabase/tests/pre_fix_to_be_delivered_stage.sql"
  fi
  if [[ "$(basename "$migration")" == "20260825160000_delivery_live_activities.sql" ]]; then
    psql "$database_url" -X -v ON_ERROR_STOP=1 \
      -f "$repo_root/supabase/tests/pre_notification_event_times.sql"
  fi
  if [[ "$(basename "$migration")" == "20260901210000_add_india_post.sql" ]]; then
    psql "$database_url" -X -v ON_ERROR_STOP=1 \
      -f "$repo_root/supabase/tests/pre_india_post.sql"
  fi
  if [[ "$(basename "$migration")" == "20260911150000_short_invitation_previews.sql" ]]; then
    psql "$database_url" -X -v ON_ERROR_STOP=1 \
      -f "$repo_root/supabase/tests/pre_short_invitation_previews.sql"
  fi
  if [[ "$(basename "$migration")" == "20260911180000_keep_previous_invitations.sql" ]]; then
    psql "$database_url" -X -v ON_ERROR_STOP=1 \
      -f "$repo_root/supabase/tests/pre_multiple_invitations.sql"
  fi
  if [[ "$(basename "$migration")" == "20261002120000_parcel_sharing.sql" ]]; then
    psql "$database_url" -X -v ON_ERROR_STOP=1 \
      -f "$repo_root/supabase/tests/pre_parcel_sharing.sql"
  fi
  if [[ "$(basename "$migration")" == "20261005030000_provider_carrier_labels.sql" ]]; then
    psql "$database_url" -X -v ON_ERROR_STOP=1 \
      -f "$repo_root/supabase/tests/pre_provider_carrier_labels.sql"
  fi
  if [[ "$(basename "$migration")" == "20261008050000_review_queue_replays.sql" ]]; then
    psql "$database_url" -X -v ON_ERROR_STOP=1 \
      -f "$repo_root/supabase/tests/pre_review_queue_replays.sql"
  fi
  name=$(basename "$migration" .sql)
  checked=0
  if [[ "$(head -n 1 "$migration")" != '-- After its server.' && "$calls_changed_before" != *" $name "* ]] \
    && grep -qiE 'function|procedure|cascade' "$migration"; then
    server_calls > "$calls_before"
    checked=1
  fi
  psql "$database_url" -X -v ON_ERROR_STOP=1 --single-transaction -f "$migration"
  if (( checked )); then
    broken=$(server_calls -v before_file="$calls_before")
    if [[ -n "$broken" ]]; then
      echo "$name breaks calls that the running server makes until its own server is deployed:" >&2
      sed 's/^/  /' <<< "$broken" >&2
      broken_calls=1
    fi
  fi
  if [[ "$(basename "$migration")" == "20261008050000_review_queue_replays.sql" ]]; then
    psql "$database_url" -X -v ON_ERROR_STOP=1 \
      -f "$repo_root/supabase/tests/review_queue_replays.sql"
  fi
  if [[ "$(basename "$migration")" == "20261005030000_provider_carrier_labels.sql" ]]; then
    psql "$database_url" -X -v ON_ERROR_STOP=1 \
      -f "$repo_root/supabase/tests/provider_carrier_labels.sql"
  fi
  if [[ "$(basename "$migration")" == "20260911150000_short_invitation_previews.sql" ]]; then
    psql "$database_url" -X -v ON_ERROR_STOP=1 \
      -f "$repo_root/supabase/tests/short_invitation_previews.sql"
  fi
  if [[ "$(basename "$migration")" == "20261002120000_parcel_sharing.sql" ]]; then
    psql "$database_url" -X -v ON_ERROR_STOP=1 \
      -f "$repo_root/supabase/tests/parcel_sharing_upgrade.sql"
  fi
done < <(find "$repo_root/supabase/migrations" -maxdepth 1 -type f -name '*.sql' | sort)
if (( broken_calls )); then
  echo "Keep the old calls working (a new argument takes a default), or start the migration with \"-- After its server.\"" >&2
  exit 1
fi

# A deployment asks production which migrations it lacks, so each one must record itself.
unrecorded=$(comm -23 \
  <(find "$repo_root/supabase/migrations" -maxdepth 1 -type f -name '*.sql' -exec basename {} .sql \; | sort) \
  <(psql "$database_url" -X -At -v ON_ERROR_STOP=1 -c 'select name from public.applied_migrations order by name' | sort))
if [[ -n "$unrecorded" ]]; then
  echo "These migrations do not end by recording themselves:" >&2
  sed "s/.*/  insert into public.applied_migrations (name) values ('&') on conflict do nothing;/" <<< "$unrecorded" >&2
  exit 1
fi

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/assertions.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/tracking_sync.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/india_post_flights.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/changed_scan_details.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/retimed_scans.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/settle_repaired_parcels.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/dhl_express_facility_clocks.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/planzer_event_stages.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/notification_event_times.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/notification_locales.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/owner_locale.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/friends.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/friendship_receipts.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/reference_indexes.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/sync_job_leases.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/sync_revision_fence.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/worker_leases.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/live_activity_revocation.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/live_activity_provider_copies.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/live_activity_pending_resets.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/multiple_invitations.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/standalone_invitations.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/invitation_outcomes.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/invitation_cap.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/carrier_history_stages.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/linked_tracking.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/answered_carrier_identity.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/absorbed_event_copies.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/automatic_links.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/tracking_routing.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/audited_tracking_stages.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/proofread_tracking_stages.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/refined_tracking_stages.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/planzer_delivered_wording.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/amazon_shipping.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/ems.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/tracking_status_observations.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/exception_stage.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/tracking_health.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/carrier_outages.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/tracking_support_cases.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/tracking_routing_signals.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/parcel_links.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/parcel_link_postcodes.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/guest_postcode_country.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/parcel_link_carrier.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/parcel_sharing.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/app_link_alerts.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/android_push.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/unifiedpush_inbox.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/gift_messages.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/delivery_emails.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/unwatched_parcels.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/seen_parcels.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/omgo.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/account_tracking_budgets.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/inbound_mailboxes.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/inbound_mail_kept.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/email_added_push.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/assistant_access.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/assistant_added_push.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/allowance_reports.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/native_lookup_verification.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/usage_metrics.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/guest_metrics.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/install_counts.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/carrier_goals.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/provider_requests.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/dhl_express_provider_input.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/forget_provider_input.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/remember_cleared_postcode.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/held_return_links.sql"

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/published_carrier_catalog.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/letter_only_numbers_and_j_and_t_cargo.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/dhl_ecommerce_national_networks.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/international_dpd_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/international_mondial_relay_gls_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/seventeentrack_india_post_copies.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/universal_clock_copies.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/remaining_scan_copies.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/delivery_notice_and_relay_hub_stages.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/la_poste_round_sort_stage.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/link_timeline_sources.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/drop_on_screen_deliveries.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/scraper_status_gaps.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/parcel_feedback.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/dpd_app_sessions.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/scraper_holds.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/peek_ai_models.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/peek_ai_call_cases.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/query_durations.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/retry_route_trial_attempts.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/retry_route_trial_reservations.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/applied_migrations.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/unchanged_tracking_checks.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/dpd_poland.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/emile.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/gofo_regions.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/universal_carriers.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/harvested_carriers.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/cacesa_and_gls_spain_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/xdp_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/gebrueder_weiss_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/dai_post.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/aramex_australia.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/instabox_notification_url.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/freightways_tracking_links.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/amazon_order_links.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/asendia_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/aramex_new_zealand.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/forwarder_carriers.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/gls_poland_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/gls_austria_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/the_delivery_group_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/relay_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/dx_arrow_xl_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/ids_logistik_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/packs_transmission_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/postmedia_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/dhl_twelve_digit_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/postnl_domestic_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/a_tu_hora_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/canpar_postcode.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/stone3pl.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/client_access.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/account_package_creation.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/account_package_edits.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/restaged_scan_copies.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/dhl_express_wall_clock_copies.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/dated_stage_sightings.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/whole_hour_provider_copies.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/misread_clock_provider_copies.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/universal_provider_notices.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/dropped_row_watermarks.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/hand_stage_changes.sql"
psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/parcel_notes.sql"

# The hourly review reads every queue: it must still run on the schema.
psql "$database_url" -X -v hours=24 -f "$repo_root/ops/review/signals.sql" > /dev/null
psql "$database_url" -X -v hours=24 -v until=2026-01-01T09:00Z -f "$repo_root/ops/review/signals.sql" > /dev/null

# It reads a dropped expected day on the parcel's own clock (passed, early, edge, unknown), unless the check that
# dropped it stored a failed attempt or hold (held), and leaves out a carrier whose drops all passed.
psql "$database_url" -X -q -f "$repo_root/supabase/tests/review_dropped_days.sql"
dropped_section=$(psql "$database_url" -X -A -F '|' -v hours=24 -v until=2026-01-01T09:00Z -f "$repo_root/ops/review/signals.sql" \
  | awk '/^== Expected days dropped/ { section = 1; next } /^== / { section = 0 } section')
dropped=$(printf '%s\n' "$dropped_section" | awk '/^la-poste\|in_transit\|/' | cut -d '|' -f 4-9)
passed_only=$(printf '%s\n' "$dropped_section" | awk '/^dhl\|/')
psql "$database_url" -X -q -v ON_ERROR_STOP=1 -c "delete from auth.users where id = '19000000-0000-0000-0000-000000000001'"
if [[ "$dropped" != "7|3|2|1|0|1" ]]; then
  echo "dropped expected days read on the wrong clock: parcels|passed|early|edge|unknown|held = '$dropped'" >&2
  exit 1
fi
if [[ -n "$passed_only" ]]; then
  echo "dropped expected days list a carrier whose drops all passed: '$passed_only'" >&2
  exit 1
fi

# It counts a passed planned step at a clock its carrier holds back as written ahead apart (written), which the server
# serves greyed, from one only a broken rule shows (passed).
psql "$database_url" -X -q -f "$repo_root/supabase/tests/review_planned_steps.sql"
planned_steps=$(psql "$database_url" -X -A -F '|' -v hours=24 -f "$repo_root/ops/review/signals.sql" \
  | awk '/^== Planned steps passed/ { section = 1; next } /^== / { section = 0 } section' | awk '/^gzyl\|/' | cut -d '|' -f 3-5)
psql "$database_url" -X -q -v ON_ERROR_STOP=1 -c "delete from auth.users where id = '19000000-0000-0000-0000-000000000002'"
if [[ "$planned_steps" != "4|2|2" ]]; then
  echo "passed planned steps not told apart from those written ahead: with_plan|passed|written = '$planned_steps'" >&2
  exit 1
fi

# A direct failure whose check still answered is rescued; a carrier is listed from ten of them.
psql "$database_url" -X -q -f "$repo_root/supabase/tests/review_direct_rescued.sql"
direct_rescued=$(psql "$database_url" -X -A -F '|' -v hours=24 -f "$repo_root/ops/review/signals.sql" \
  | awk '/^== Direct steps failing/ { section = 1; next } /^== / { section = 0 } section' | awk '/^review-direct/')
psql "$database_url" -X -q -v ON_ERROR_STOP=1 -c "delete from public.tracking_health_samples where subject like 'review-direct%'"
if [[ "$direct_rescued" != "review-direct|adapters|15|14|12|challenge" ]]; then
  echo "direct steps behind a fallback: carrier|lane|direct_checks|failed|rescued|usual = '$direct_rescued'" >&2
  exit 1
fi

# A check the fence refused saved nothing: the stale stage it loaded is no step back.
psql "$database_url" -X -q -f "$repo_root/supabase/tests/review_step_backs.sql"
step_section=$(psql "$database_url" -X -A -F '|' -v hours=24 -v until=2026-01-01T09:00Z -f "$repo_root/ops/review/signals.sql" \
  | awk '/^== Stage step-backs/ { section = 1; next } /^== / { section = 0 } section')
step_backs=$(awk '/^la-poste\|/' <<< "$step_section" | cut -d '|' -f 1-3,9-10)
psql "$database_url" -X -q -v ON_ERROR_STOP=1 -c "delete from auth.users where id = '19000000-0000-0000-0000-000000000002'" \
  -c "delete from public.tracking_status_observations where observation_key = encode(sha256('review-step-backs re-read'), 'hex')"
if [[ "$step_backs" != "la-poste|delivered|in_transit|1|19000000" ]]; then
  echo "stage step-backs: carrier|from|to|parcels|sample = '$step_backs'" >&2
  exit 1
fi
for correction in 'dhl|delivered|returned|' 'dhl|returned|delivered|'; do
  if ! grep -Fq "$correction" <<< "$step_section"; then
    echo "Missing terminal stage correction: $correction" >&2
    exit 1
  fi
done
# A linked parcel's two legs are read apart: neither stepped back.
if grep -q '39000000' <<< "$step_section"; then
  echo "stage step-backs read across a linked parcel's legs: $(grep '39000000' <<< "$step_section")" >&2
  exit 1
fi
# A parcel handed over in place keeps one leg: its step back is listed.
if ! grep -q '49000000' <<< "$step_section"; then
  echo "stage step-backs miss a parcel handed over in place: '$step_section'" >&2
  exit 1
fi
# A step a scraper release's re-reading of a stored scan explains is left out; one whose scan the mapped release
# stored is listed.
re_read=$(awk '/^cj-packet\|/' <<< "$step_section" | cut -d '|' -f 1-3,9-10)
if [[ "$re_read" != "cj-packet|in_transit|accepted|1|69000000" ]]; then
  echo "stage step-backs after a scraper release's re-reading: carrier|from|to|parcels|sample = '$re_read'" >&2
  exit 1
fi

# A repeat of a closed case is listed as a case of its own; one of an open case counts under it.
psql "$database_url" -X -q -f "$repo_root/supabase/tests/review_support_repeats.sql"
review=$(psql "$database_url" -X -A -F '|' -v hours=24 -v until=2026-01-01T09:00Z -f "$repo_root/ops/review/signals.sql")
psql "$database_url" -X -q -v ON_ERROR_STOP=1 -c "delete from public.tracking_support_cases where tracking_number like 'REVIEW%'"
support=$(awk '/^== Support cases/ { section = 1; next } /^== / { section = 0 } section' <<< "$review")
if [[ "$(awk '/^unknown\|/' <<< "$support" | cut -d '|' -f 5-6)" != "2|1" ]]; then
  echo "support cases: cases|repeats = '$(awk '/^unknown\|/' <<< "$support" | cut -d '|' -f 5-6)'" >&2
  exit 1
fi
# A case whose filed carrier tracked the number is listed apart: nothing waits on it.
learn=$(awk '/^== Shapes detection could learn/ { section = 1; next } /^== / { section = 0 } section' <<< "$review" \
  | awk '/^paack\|/' | cut -d '|' -f 3-5)
if grep -q '^paack|' <<< "$support" || [[ "$learn" != "recognition_unknown|carriers|1" ]]; then
  echo "a case its carrier tracked: support cases '$(grep '^paack|' <<< "$support")', shapes to learn '$learn'" >&2
  exit 1
fi

# A delivered parcel a hand fix reopened in the last hour waits for the check the schedule brings; one still unchecked
# an hour after its fix, like one never reopened, is a scheduler fault.
psql "$database_url" -X -q -f "$repo_root/supabase/tests/review_reopened_unchecked.sql"
unchecked=$(psql "$database_url" -X -A -F '|' -v hours=24 -f "$repo_root/ops/review/signals.sql" \
  | awk '/^== Followed parcels not checked for 24 h/ { section = 1; next } /^== / { section = 0 } section' \
  | awk '/^account\|speedx\|/' | cut -d '|' -f 6)
psql "$database_url" -X -q -v ON_ERROR_STOP=1 -c "delete from auth.users where id = '19000000-0000-0000-0000-000000000062'"
if [[ "$unchecked" != "2" ]]; then
  echo "followed parcels not checked for 24 h: parcels = '$unchecked' (2: a parcel a hand fix reopened 10 minutes ago waits for its check)" >&2
  exit 1
fi

psql "$database_url" -X -v ON_ERROR_STOP=1 \
  -f "$repo_root/supabase/tests/j_and_t_phone_digits.sql"

# Closing a reader's answer marks it reviewed once.
feedback_id=$(psql "$database_url" -X -Atq -v ON_ERROR_STOP=1 -c "
  insert into public.parcel_feedback (id, tracking_number, carrier, answer, reasons, asked, via, app, locale, shown)
  values (gen_random_uuid(), 'TEST000000001', 'unknown', 'wrong', '{status}', 'page', 'link', 'web', 'en', '{}')
  returning id")
closed=$(psql "$database_url" -X -At -v id="$feedback_id" -v note="Routed to the adapters lane" \
  -f "$repo_root/ops/review/close-feedback.sql")
[[ "$closed" == "$feedback_id|Routed to the adapters lane" ]] || { echo "close-feedback.sql did not close the answer: $closed" >&2; exit 1; }
closed=$(psql "$database_url" -X -At -v id="$feedback_id" -v note="Again" -f "$repo_root/ops/review/close-feedback.sql")
[[ -z "$closed" ]] || { echo "close-feedback.sql closed an answer twice: $closed" >&2; exit 1; }
psql "$database_url" -X -q -c "delete from public.parcel_feedback where id = '$feedback_id'"

# A parcel not received goes to a lane only when the page said a carrier's scan delivered it; the others are counted.
psql "$database_url" -X -q -v ON_ERROR_STOP=1 -c "
  insert into public.parcel_feedback (id, tracking_number, carrier, answer, page_said, asked, via, app, locale, shown, created_at)
  values ('59000000-0000-4000-8000-000000000001', 'TEST000000002', 'dpd', 'not_received', 'delivered', 'page', 'link', 'web', 'en', '{}',
      now() - interval '1 hour'),
    ('59000000-0000-4000-8000-000000000002', 'TEST000000003', 'gzyl', 'not_received', 'written_ahead', 'page', 'link', 'web', 'en', '{}',
      now() - interval '1 hour'),
    ('59000000-0000-4000-8000-000000000003', 'TEST000000004', 'dpd', 'not_received', 'in_transit', 'page', 'link', 'web', 'en', '{}',
      now() - interval '1 hour')"
feedback=$(psql "$database_url" -X -A -F '|' -v hours=2 -f "$repo_root/ops/review/signals.sql" \
  | awk '/^== Reader feedback/ { section = 1; next } /^== / { section = 0 } section')
psql "$database_url" -X -q -c "delete from public.parcel_feedback where id::text like '59000000-%'"
listed=$(grep '^59000000-' <<< "$feedback" | awk -F '|' '{ print $1 "|" $5 "|" $NF }')
if [[ "$listed" != "59000000-0000-4000-8000-000000000001|not_received|adapters" ]] || ! grep -q ', 3 not received' <<< "$feedback"; then
  echo "reader feedback: parcels not received listed '$listed', counts '$(grep 'answers given' <<< "$feedback")'" >&2
  exit 1
fi
