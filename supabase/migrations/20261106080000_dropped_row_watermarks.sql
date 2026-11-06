-- A parcel's freshness watermark (carrier_data.routing.last_event_at) is the newest scan
-- the sync took. When the stored row that held it went or moved (a sync deleting a copy a
-- carrier's scan took over, a release's migration, the replay tool's clock fix), the
-- watermark stayed: every later answer read as older than a scan the parcel no longer
-- had, and the parcel kept the stage and summary that scan gave. In the same transaction
-- it now comes back to the newest scan row left before it, or goes with the parcel's last
-- one. A watermark no row held, as an answer's summary sets without a scan, stays.
-- apply_tracking_sync is otherwise copied from 20261018170000_handed_on_carrier_correction.sql.

-- The watermark as a time; none when it is missing or not an ISO 8601 time.
create function public.routing_watermark(p_carrier_data jsonb)
returns timestamptz
language sql
stable
set search_path = pg_catalog
as $$
  select case
    when p_carrier_data#>>'{routing,last_event_at}' ~ '^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}(:\d{2}(\.\d{1,6})?)?(Z|[+-]\d{2}(:?\d{2})?)$'
      and pg_input_is_valid(p_carrier_data#>>'{routing,last_event_at}', 'timestamptz')
    then (p_carrier_data#>>'{routing,last_event_at}')::timestamptz
  end;
$$;

-- A scan row: neither Peek's own nor a stage a check saw without the carrier's time.
create function public.is_scan_row(p_provider_event_id text, p_raw_data jsonb)
returns boolean
language sql
immutable
set search_path = pg_catalog
as $$
  select p_provider_event_id not like 'app:%'
    and p_raw_data->'observed_without_provider_timestamp' is distinct from 'true'::jsonb;
$$;

-- Parcels whose watermark is one of these instants, which no scan row holds any more,
-- take back the newest scan row before it, or lose it with their last one.
create function public.release_dropped_watermarks(p_package_ids uuid[], p_marks timestamptz[])
returns void
language sql
set search_path = pg_catalog
as $$
  with dropped as (
    select distinct dropped.package_id, dropped.mark
    from unnest(p_package_ids, p_marks) as dropped(package_id, mark)
    where dropped.package_id is not null and dropped.mark is not null
  ), released as (
    select parcel.id, (
      select max(event.occurred_at) from public.tracking_events event
      where event.package_id = parcel.id and event.occurred_at < dropped.mark
        and public.is_scan_row(event.provider_event_id, event.raw_data)
    ) as newest
    from dropped
    join public.packages parcel on parcel.id = dropped.package_id
    where public.routing_watermark(parcel.carrier_data) = dropped.mark
      and not exists (
        select 1 from public.tracking_events event
        where event.package_id = parcel.id and event.occurred_at = dropped.mark
          and public.is_scan_row(event.provider_event_id, event.raw_data)
      )
  )
  update public.packages parcel set carrier_data = case
    when released.newest is null then parcel.carrier_data #- '{routing,last_event_at}'
    else jsonb_set(parcel.carrier_data, '{routing,last_event_at}',
      to_jsonb(to_char(released.newest at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS.MS"Z"')))
  end
  from released
  where parcel.id = released.id;
$$;

create function public.release_deleted_event_watermarks()
returns trigger
language plpgsql
security definer
set search_path = pg_catalog
as $$
begin
  perform public.release_dropped_watermarks(array_agg(gone.package_id), array_agg(gone.occurred_at))
  from gone_events gone
  where public.is_scan_row(gone.provider_event_id, gone.raw_data);
  return null;
end;
$$;

-- A row moved to another time or parcel, or no longer a scan, leaves its instant.
create function public.release_moved_event_watermarks()
returns trigger
language plpgsql
security definer
set search_path = pg_catalog
as $$
begin
  perform public.release_dropped_watermarks(array_agg(before.package_id), array_agg(before.occurred_at))
  from old_events before
  join new_events after on after.id = before.id
  where public.is_scan_row(before.provider_event_id, before.raw_data)
    and ((after.package_id, after.occurred_at) is distinct from (before.package_id, before.occurred_at)
      or not public.is_scan_row(after.provider_event_id, after.raw_data));
  return null;
end;
$$;

create trigger tracking_events_release_deleted_watermarks
after delete on public.tracking_events
referencing old table as gone_events
for each statement execute function public.release_deleted_event_watermarks();

create trigger tracking_events_release_moved_watermarks
after update on public.tracking_events
referencing old table as old_events new table as new_events
for each statement execute function public.release_moved_event_watermarks();

revoke all on function public.routing_watermark(jsonb) from public, anon, authenticated;
revoke all on function public.is_scan_row(text, jsonb) from public, anon, authenticated;
revoke all on function public.release_dropped_watermarks(uuid[], timestamptz[]) from public, anon, authenticated;
revoke all on function public.release_deleted_event_watermarks() from public, anon, authenticated;
revoke all on function public.release_moved_event_watermarks() from public, anon, authenticated;
grant execute on function public.routing_watermark(jsonb) to service_role;
grant execute on function public.is_scan_row(text, jsonb) to service_role;
grant execute on function public.release_dropped_watermarks(uuid[], timestamptz[]) to service_role;

create or replace function public.apply_tracking_sync(
  p_package_id uuid,
  p_tracking_generation uuid,
  p_values jsonb,
  p_events jsonb default '[]'::jsonb,
  p_delete_descriptions text[] default '{}'::text[],
  p_delete_event_ids text[] default '{}'::text[]
)
returns boolean
language plpgsql
set search_path = pg_catalog
as $$
declare
  values_row public.packages;
  current_row public.packages;
  provider_identification boolean;
  held_mark timestamptz;
begin
  select * into current_row from public.packages
  where id = p_package_id and tracking_generation = p_tracking_generation
  for update;
  if not found then return false; end if;
  -- The watermark a stored scan holds, which this sync may drop (release_dropped_watermarks).
  held_mark := public.routing_watermark(current_row.carrier_data);
  if not exists (
    select 1 from public.tracking_events event
    where event.package_id = p_package_id and event.occurred_at = held_mark
      and public.is_scan_row(event.provider_event_id, event.raw_data)
  ) then
    held_mark := null;
  end if;

  if jsonb_typeof(p_values) is distinct from 'object'
      or jsonb_typeof(p_events) is distinct from 'array'
      or (p_values - array[
        'current_stage', 'expected_delivery', 'last_status_text', 'last_synced_at',
        'sync_status', 'sync_error', 'carrier_data', 'carrier', 'tracking_url', 'dpd_postcode'
      ]) <> '{}'::jsonb then
    raise exception 'Invalid tracking sync payload' using errcode = '22023';
  end if;
  if p_values ?| array['carrier', 'tracking_url', 'dpd_postcode'] then
    if not (p_values ?& array['carrier', 'tracking_url', 'dpd_postcode', 'carrier_data'])
      or p_values->>'carrier' is null
      or p_values->>'carrier' = current_row.carrier
      or p_values#>>'{carrier_data,routing,configured_carrier}' is distinct from p_values->>'carrier'
      or current_row.carrier_data->>'original_carrier' is not null
      -- The only hand-off it names is the corrected carrier's, from this very number.
      or (p_values#>>'{carrier_data,original_carrier}' is not null and (
        p_values#>>'{carrier_data,original_carrier}' is distinct from p_values->>'carrier'
        or p_values#>>'{carrier_data,original_tracking_number}' is distinct from current_row.tracking_number)) then
      raise exception 'Invalid automatic carrier correction' using errcode = '22023';
    end if;
    -- Only the trusted sync writer supplies provider identity and number-bound movement.
    provider_identification := current_row.carrier = 'unknown'
      and p_values->>'tracking_url' is null and p_values->>'dpd_postcode' is null
      and p_values#>>'{carrier_data,original_carrier}' is null
      and p_values#>>'{carrier_data,discovered_carrier}' = p_values->>'carrier'
      and p_values#>>'{carrier_data,routing,discovered_carrier}' = p_values->>'carrier'
      and p_values#>>'{carrier_data,routing,preferred_number}' = current_row.tracking_number
      and p_values#>>'{carrier_data,routing,confirmed_carrier}' is null
      and current_row.carrier_data#>>'{routing,confirmed_carrier}' is null
      and p_values#>>'{carrier_data,tracking_provider}' in ('Ship24', 'ParcelsApp', '17TRACK', 'Postal Ninja')
      and jsonb_typeof(p_values#>'{carrier_data,reported_carriers}') = 'array'
      and jsonb_array_length(p_values#>'{carrier_data,reported_carriers}') between 1 and 4
      and not exists (
        select 1 from jsonb_array_elements(p_values#>'{carrier_data,reported_carriers}') reported
        where jsonb_typeof(reported) <> 'string'
      )
      and exists (
        select 1 from jsonb_to_recordset(p_events) as event(stage text, occurred_at timestamptz)
        where event.stage not in ('pending', 'registered')
          and event.occurred_at is not null and event.occurred_at <= now() + interval '1 hour'
        union all
        select 1 from public.tracking_events event
        where event.package_id = p_package_id and event.stage not in ('pending', 'registered')
          and event.occurred_at = (p_values#>>'{carrier_data,routing,last_event_at}')::timestamptz
      );
    if not coalesce(provider_identification, false) then
      if p_values#>>'{carrier_data,routing,confirmed_carrier}' is distinct from p_values->>'carrier'
        or p_values#>>'{carrier_data,routing,confirmed_number}' is distinct from current_row.tracking_number
        or p_values#>>'{carrier_data,auto_changed_from}' is distinct from current_row.carrier
        or p_values#>>'{carrier_data,auto_changed_to}' is distinct from p_values->>'carrier' then
        raise exception 'Invalid automatic carrier correction' using errcode = '22023';
      end if;
      p_values := jsonb_set(p_values, '{carrier_data,auto_changed_at}', to_jsonb(now()));
    else
      p_values := jsonb_set(p_values, '{carrier_data}',
        (p_values->'carrier_data') - array['auto_changed_from', 'auto_changed_to', 'auto_changed_at']);
    end if;
  end if;
  values_row := jsonb_populate_record(null::public.packages, p_values);

  insert into public.tracking_events (
    package_id, provider_event_id, stage, description, location, occurred_at, raw_data
  )
  select p_package_id, event.provider_event_id, event.stage,
    event.description, event.location, event.occurred_at, coalesce(event.raw_data, '{}'::jsonb)
  from jsonb_to_recordset(p_events) as event(
    provider_event_id text, stage text, description text,
    location text, occurred_at timestamptz, raw_data jsonb
  )
  on conflict (package_id, provider_event_id) do update set
    stage = excluded.stage,
    description = excluded.description,
    location = excluded.location,
    occurred_at = excluded.occurred_at,
    raw_data = excluded.raw_data;

  delete from public.tracking_events
  where package_id = p_package_id and description = any(p_delete_descriptions);
  -- Universal copies a carrier's scans absorbed, never a row this batch writes.
  delete from public.tracking_events
  where package_id = p_package_id and provider_event_id = any(p_delete_event_ids)
    and provider_event_id not in (
      select event.provider_event_id from jsonb_to_recordset(p_events) as event(provider_event_id text)
      where event.provider_event_id is not null
    );

  update public.packages set
    current_stage = case when p_values ? 'current_stage' then values_row.current_stage else current_stage end,
    expected_delivery = case when p_values ? 'expected_delivery' then values_row.expected_delivery else expected_delivery end,
    last_status_text = case when p_values ? 'last_status_text' then values_row.last_status_text else last_status_text end,
    last_synced_at = case when p_values ? 'last_synced_at' then values_row.last_synced_at else last_synced_at end,
    sync_status = case when p_values ? 'sync_status' then values_row.sync_status else sync_status end,
    sync_error = case when p_values ? 'sync_error' then values_row.sync_error else sync_error end,
    carrier_data = case when p_values ? 'carrier_data' then values_row.carrier_data else carrier_data end
  where id = p_package_id;
  -- Keep ordinary status writes from firing the input-generation trigger.
  if p_values ? 'carrier' then
    update public.packages set carrier = values_row.carrier,
      tracking_url = values_row.tracking_url, dpd_postcode = values_row.dpd_postcode
    where id = p_package_id;
  end if;
  -- A watermark the sync kept, which no scan row holds after it, comes back to the rows left.
  if held_mark is not null then
    perform public.release_dropped_watermarks(array[p_package_id], array[held_mark]);
  end if;
  return true;
end;
$$;

revoke all on function public.apply_tracking_sync(uuid, uuid, jsonb, jsonb, text[], text[])
  from public, anon, authenticated;
grant execute on function public.apply_tracking_sync(uuid, uuid, jsonb, jsonb, text[], text[])
  to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261106080000_dropped_row_watermarks') on conflict do nothing;
