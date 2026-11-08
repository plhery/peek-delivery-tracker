-- A check's write applies only over the parcel it read.
--
-- A check reads the parcel, asks its carrier, then writes what it found, minutes later at
-- worst. Two checks of one parcel overlap when two servers run, as the old and new containers
-- of a deploy do: one's scheduled run and the other's check of the parcel each hold a lease of
-- their own. The tracking generation, which only a change of carrier, number or inputs renews,
-- let both writes through, so the one that ended last won: an answer read before a delivery
-- could take the parcel back in transit, with the delivery left in its timeline.
--
-- Each write apply_tracking_sync applies now adds one to the parcel's sync_revision. A check
-- passes the revision it read, then the one after each of its own writes, so its own writes
-- never turn it away. A write from any other revision stores only the scans new to what its
-- check read (p_new_event_ids), unless the parcel holds a row in their minute, and answers
-- null: the rows it would rewrite or delete, its summary, carrier data and carrier
-- correction give way to the newer write. No order between checks is assumed: the one that
-- read the parcel last writes it. A check that reads the parcel after a delivery and finds it
-- withdrawn writes over it as before. A call without a revision, as from the server before
-- this one, is not fenced and still adds one.
--
-- The functions gain two defaulted parameters, so the old signatures go first; a call with the
-- old arguments still resolves through the defaults. apply_tracking_sync is otherwise copied
-- from 20261106080000_dropped_row_watermarks.sql, apply_leased_tracking_sync from
-- 20261016193000_absorbed_event_copies.sql.
--
-- Apply this before deploying the server that passes the revision.

begin;

alter table public.packages add column sync_revision bigint not null default 0;
comment on column public.packages.sync_revision is
  'One more with each tracking write apply_tracking_sync applies. A check''s write from an older revision stores only its new scans.';

drop function public.apply_leased_tracking_sync(uuid, text, uuid, uuid, jsonb, jsonb, text[], text[]);
drop function public.apply_tracking_sync(uuid, uuid, jsonb, jsonb, text[], text[]);

create function public.apply_tracking_sync(
  p_package_id uuid,
  p_tracking_generation uuid,
  p_values jsonb,
  p_events jsonb default '[]'::jsonb,
  p_delete_descriptions text[] default '{}'::text[],
  p_delete_event_ids text[] default '{}'::text[],
  p_sync_revision bigint default null,
  p_new_event_ids text[] default null
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
  -- Another check wrote the parcel after this one read it: only the scans new to what it read
  -- are stored, unless the parcel holds a row in their minute, which may be the same scan from
  -- another source. The newer write keeps its rows, summary, carrier data and carrier.
  if p_sync_revision is not null and p_sync_revision is distinct from current_row.sync_revision then
    insert into public.tracking_events (
      package_id, provider_event_id, stage, description, location, occurred_at, raw_data
    )
    select p_package_id, event.provider_event_id, event.stage,
      event.description, event.location, event.occurred_at, coalesce(event.raw_data, '{}'::jsonb)
    from jsonb_to_recordset(p_events) as event(
      provider_event_id text, stage text, description text,
      location text, occurred_at timestamptz, raw_data jsonb
    )
    where event.provider_event_id = any(coalesce(p_new_event_ids, '{}'::text[]))
      and event.occurred_at is not null
      and public.is_scan_row(event.provider_event_id, coalesce(event.raw_data, '{}'::jsonb))
      and not exists (
        select 1 from public.tracking_events stored
        where stored.package_id = p_package_id
          and date_trunc('minute', stored.occurred_at) = date_trunc('minute', event.occurred_at)
      )
    on conflict (package_id, provider_event_id) do nothing;
    return null;
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
    carrier_data = case when p_values ? 'carrier_data' then values_row.carrier_data else carrier_data end,
    sync_revision = sync_revision + 1
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

create function public.apply_leased_tracking_sync(
  p_job_id uuid, p_worker_id text, p_package_id uuid, p_tracking_generation uuid,
  p_values jsonb, p_events jsonb default '[]'::jsonb, p_delete_descriptions text[] default '{}'::text[],
  p_delete_event_ids text[] default '{}'::text[], p_sync_revision bigint default null, p_new_event_ids text[] default null
)
returns boolean language plpgsql set search_path = pg_catalog as $$
begin
  perform 1 from public.sync_jobs where id = p_job_id and state = 'running'
    and locked_by = p_worker_id and lease_until > clock_timestamp()
    and (kind = 'scheduled' or package_id = p_package_id) for update;
  if not found then
    if not exists (select 1 from public.packages
      where id = p_package_id and tracking_generation = p_tracking_generation) then
      return false;
    end if;
    raise exception 'Synchronization job lease was lost' using errcode = '55000';
  end if;
  return public.apply_tracking_sync(
    p_package_id, p_tracking_generation, p_values, p_events, p_delete_descriptions, p_delete_event_ids,
    p_sync_revision, p_new_event_ids
  );
end;
$$;

revoke all on function public.apply_tracking_sync(uuid, uuid, jsonb, jsonb, text[], text[], bigint, text[])
  from public, anon, authenticated;
grant execute on function public.apply_tracking_sync(uuid, uuid, jsonb, jsonb, text[], text[], bigint, text[])
  to service_role;
revoke all on function public.apply_leased_tracking_sync(uuid, text, uuid, uuid, jsonb, jsonb, text[], text[], bigint, text[])
  from public, anon, authenticated;
grant execute on function public.apply_leased_tracking_sync(uuid, text, uuid, uuid, jsonb, jsonb, text[], text[], bigint, text[])
  to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261108140000_sync_revision_fence') on conflict do nothing;

commit;
