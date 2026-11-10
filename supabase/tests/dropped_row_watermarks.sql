\set ON_ERROR_STOP on
begin;

-- A delivered parcel whose watermark a later stray row of another parcel's history held:
-- a delivery, Peek's own row after it, and the stray scan the watermark stands at.
create function pg_temp.parcel(p_number text, p_mark text) returns public.packages
language plpgsql as $$
declare
  parcel public.packages;
begin
  insert into public.packages (user_id, tracking_number, carrier, current_stage, carrier_data)
  values ('10000000-0000-0000-0000-000000000001', p_number, 'kun-express', 'in_transit',
    jsonb_build_object('tracking_provider', 'Kun Express',
      'routing', jsonb_strip_nulls(jsonb_build_object('last_event_at', p_mark, 'preferred_provider', 'kun-express'))))
  returning * into parcel;
  insert into public.tracking_events (package_id, provider_event_id, stage, description, occurred_at, raw_data)
  values (parcel.id, 'kun-express:delivered', 'delivered', 'Delivered', '2026-10-20T15:15:00Z', '{}'),
    (parcel.id, 'kun-express:stray', 'in_transit', 'Departed the hub', '2026-11-02T08:09:24Z', '{}'),
    (parcel.id, 'kun-express:sighting', 'in_transit', 'In transit', '2026-11-03T06:00:00Z',
      '{"observed_without_provider_timestamp": true}');
  update public.tracking_events set occurred_at = '2026-10-20T19:25:00Z'
  where package_id = parcel.id and provider_event_id = 'app:pending';
  select * into parcel from public.packages where id = parcel.id;
  return parcel;
end;
$$;

create function pg_temp.mark(p_id uuid) returns text language sql as $$
  select carrier_data#>>'{routing,last_event_at}' from public.packages where id = p_id;
$$;

do $$
declare
  parcel public.packages;
  other public.packages;
begin
  if has_function_privilege('authenticated', 'public.release_dropped_watermarks(uuid[],timestamptz[])', 'execute')
      or has_function_privilege('anon', 'public.release_dropped_watermarks(uuid[],timestamptz[])', 'execute') then
    raise exception 'users can release watermarks';
  end if;

  -- A release's migration or a hand fix deleting the row: the newest scan row before it, not Peek's own row.
  parcel := pg_temp.parcel('EXAMPLE0000000101', '2026-11-02T08:09:24.000Z');
  delete from public.tracking_events where package_id = parcel.id and provider_event_id = 'kun-express:stray';
  if pg_temp.mark(parcel.id) is distinct from '2026-10-20T15:15:00.000Z' then
    raise exception 'a deleted row kept the watermark: %', pg_temp.mark(parcel.id);
  end if;
  if (select carrier_data#>>'{routing,preferred_provider}' from public.packages where id = parcel.id) <> 'kun-express' then
    raise exception 'releasing the watermark changed the rest of the routing';
  end if;
  -- With its last scan row, the watermark goes.
  delete from public.tracking_events where package_id = parcel.id and provider_event_id = 'kun-express:delivered';
  if (select carrier_data->'routing' ? 'last_event_at' from public.packages where id = parcel.id) then
    raise exception 'a parcel without scans kept a watermark';
  end if;

  -- A watermark no row held, as a summary sets, stays; so does one another row of the instant still holds.
  parcel := pg_temp.parcel('EXAMPLE0000000102', '2026-11-04T10:00:00.000Z');
  delete from public.tracking_events where package_id = parcel.id and provider_event_id = 'kun-express:stray';
  if pg_temp.mark(parcel.id) is distinct from '2026-11-04T10:00:00.000Z' then
    raise exception 'a summary watermark moved: %', pg_temp.mark(parcel.id);
  end if;
  parcel := pg_temp.parcel('EXAMPLE0000000103', '2026-11-02T08:09:24.000Z');
  insert into public.tracking_events (package_id, provider_event_id, stage, description, occurred_at)
  values (parcel.id, 'kun-express:carrier', 'in_transit', 'Departed the hub', '2026-11-02T08:09:24Z');
  delete from public.tracking_events where package_id = parcel.id and provider_event_id = 'kun-express:stray';
  if pg_temp.mark(parcel.id) is distinct from '2026-11-02T08:09:24.000Z' then
    raise exception 'a watermark another row holds moved: %', pg_temp.mark(parcel.id);
  end if;
  -- A sighting at the watermark never held it.
  parcel := pg_temp.parcel('EXAMPLE0000000104', '2026-11-03T06:00:00.000Z');
  delete from public.tracking_events where package_id = parcel.id and provider_event_id = 'kun-express:sighting';
  if pg_temp.mark(parcel.id) is distinct from '2026-11-03T06:00:00.000Z' then
    raise exception 'a sighting released the watermark: %', pg_temp.mark(parcel.id);
  end if;
  -- A watermark that is no time is left as it is, and the delete goes through.
  parcel := pg_temp.parcel('EXAMPLE0000000105', '2026-13-45T08:09:24.000Z');
  delete from public.tracking_events where package_id = parcel.id and provider_event_id = 'kun-express:stray';
  if pg_temp.mark(parcel.id) is distinct from '2026-13-45T08:09:24.000Z' then
    raise exception 'an unreadable watermark changed';
  end if;

  -- The replay tool moving the row to another time: the watermark follows it back.
  parcel := pg_temp.parcel('EXAMPLE0000000106', '2026-11-02T08:09:24.000Z');
  update public.tracking_events set occurred_at = '2026-11-02T00:09:24Z'
  where package_id = parcel.id and provider_event_id = 'kun-express:stray';
  if pg_temp.mark(parcel.id) is distinct from '2026-11-02T00:09:24.000Z' then
    raise exception 'a moved row kept the watermark: %', pg_temp.mark(parcel.id);
  end if;
  -- Moved later, as a clock fix moves the newest scan, the watermark follows it: no answer may
  -- move it on, as when another provider serves the parcel.
  parcel := pg_temp.parcel('EXAMPLE0000000114', '2026-11-02T08:09:24.000Z');
  update public.tracking_events set occurred_at = '2026-11-02T16:09:24Z'
  where package_id = parcel.id and provider_event_id = 'kun-express:stray';
  if pg_temp.mark(parcel.id) is distinct from '2026-11-02T16:09:24.000Z' then
    raise exception 'a row moved later left the watermark behind: %', pg_temp.mark(parcel.id);
  end if;
  -- Into the future, it leaves the newest row before it.
  parcel := pg_temp.parcel('EXAMPLE0000000115', '2026-11-02T08:09:24.000Z');
  update public.tracking_events set occurred_at = now() + interval '1 day'
  where package_id = parcel.id and provider_event_id = 'kun-express:stray';
  if pg_temp.mark(parcel.id) is distinct from '2026-10-20T15:15:00.000Z' then
    raise exception 'a row moved into the future took the watermark: %', pg_temp.mark(parcel.id);
  end if;
  -- Another row moved later leaves the watermark where it is.
  parcel := pg_temp.parcel('EXAMPLE0000000116', '2026-11-02T08:09:24.000Z');
  update public.tracking_events set occurred_at = '2026-10-21T15:15:00Z'
  where package_id = parcel.id and provider_event_id = 'kun-express:delivered';
  if pg_temp.mark(parcel.id) is distinct from '2026-11-02T08:09:24.000Z' then
    raise exception 'another row moved the watermark: %', pg_temp.mark(parcel.id);
  end if;
  -- A sync's answer moving the row later, the sync keeping its own watermark, as before.
  parcel := pg_temp.parcel('EXAMPLE0000000117', '2026-11-02T08:09:24.000Z');
  if not public.apply_tracking_sync(parcel.id, parcel.tracking_generation,
      jsonb_build_object('sync_status', 'ok', 'carrier_data',
        jsonb_set(parcel.carrier_data, '{routing,last_event_at}', '"2026-11-02T18:09:24.000Z"')),
      '[{"provider_event_id":"kun-express:stray","stage":"in_transit","description":"Departed the hub","occurred_at":"2026-11-02T16:09:24Z"}]') then
    raise exception 'the sync was not saved';
  end if;
  if pg_temp.mark(parcel.id) is distinct from '2026-11-02T18:09:24.000Z' then
    raise exception 'the sync''s own watermark moved: %', pg_temp.mark(parcel.id);
  end if;
  -- A stage the replay steps back keeps the row's instant.
  parcel := pg_temp.parcel('EXAMPLE0000000107', '2026-11-02T08:09:24.000Z');
  update public.tracking_events set stage = 'registered'
  where package_id = parcel.id and provider_event_id = 'kun-express:stray';
  if pg_temp.mark(parcel.id) is distinct from '2026-11-02T08:09:24.000Z' then
    raise exception 'a restaged row released the watermark: %', pg_temp.mark(parcel.id);
  end if;

  -- The sync deleting the row while it keeps the watermark it was given.
  parcel := pg_temp.parcel('EXAMPLE0000000108', '2026-11-02T08:09:24.000Z');
  if not public.apply_tracking_sync(parcel.id, parcel.tracking_generation,
      jsonb_build_object('sync_status', 'ok', 'carrier_data', parcel.carrier_data), '[]', '{}',
      array['kun-express:stray']) then
    raise exception 'the sync was not saved';
  end if;
  if pg_temp.mark(parcel.id) is distinct from '2026-10-20T15:15:00.000Z' then
    raise exception 'the sync kept a dropped row''s watermark: %', pg_temp.mark(parcel.id);
  end if;
  -- One that moves the row in place, through its answer.
  parcel := pg_temp.parcel('EXAMPLE0000000109', '2026-11-02T08:09:24.000Z');
  if not public.apply_tracking_sync(parcel.id, parcel.tracking_generation,
      jsonb_build_object('sync_status', 'ok', 'carrier_data', parcel.carrier_data),
      '[{"provider_event_id":"kun-express:stray","stage":"in_transit","description":"Departed the hub","occurred_at":"2026-11-01T08:09:24Z"}]') then
    raise exception 'the sync was not saved';
  end if;
  if pg_temp.mark(parcel.id) is distinct from '2026-11-01T08:09:24.000Z' then
    raise exception 'the sync kept a moved row''s watermark: %', pg_temp.mark(parcel.id);
  end if;
  -- A watermark the sync sets is its own, even with the row gone.
  parcel := pg_temp.parcel('EXAMPLE0000000110', '2026-11-02T08:09:24.000Z');
  if not public.apply_tracking_sync(parcel.id, parcel.tracking_generation,
      jsonb_build_object('sync_status', 'ok', 'carrier_data',
        jsonb_set(parcel.carrier_data, '{routing,last_event_at}', '"2026-11-05T09:00:00.000Z"')), '[]', '{}',
      array['kun-express:stray']) then
    raise exception 'the sync was not saved';
  end if;
  if pg_temp.mark(parcel.id) is distinct from '2026-11-05T09:00:00.000Z' then
    raise exception 'the sync''s own watermark moved: %', pg_temp.mark(parcel.id);
  end if;
  -- So is a summary's watermark the sync keeps, which no row held before it.
  parcel := pg_temp.parcel('EXAMPLE0000000111', '2026-11-04T10:00:00.000Z');
  if not public.apply_tracking_sync(parcel.id, parcel.tracking_generation,
      jsonb_build_object('sync_status', 'ok', 'carrier_data', parcel.carrier_data), '[]', '{}',
      array['kun-express:stray']) then
    raise exception 'the sync was not saved';
  end if;
  if pg_temp.mark(parcel.id) is distinct from '2026-11-04T10:00:00.000Z' then
    raise exception 'the sync moved a summary''s watermark: %', pg_temp.mark(parcel.id);
  end if;

  -- Rows of two parcels in one statement: each parcel by its own rows.
  parcel := pg_temp.parcel('EXAMPLE0000000112', '2026-11-02T08:09:24.000Z');
  other := pg_temp.parcel('EXAMPLE0000000113', '2026-11-04T10:00:00.000Z');
  delete from public.tracking_events where package_id in (parcel.id, other.id) and provider_event_id = 'kun-express:stray';
  if pg_temp.mark(parcel.id) is distinct from '2026-10-20T15:15:00.000Z'
      or pg_temp.mark(other.id) is distinct from '2026-11-04T10:00:00.000Z' then
    raise exception 'one statement released the wrong watermarks';
  end if;
  -- Deleting a parcel deletes its rows as before.
  delete from public.packages where id in (parcel.id, other.id);
  if exists (select 1 from public.tracking_events where package_id in (parcel.id, other.id)) then
    raise exception 'a deleted parcel kept its rows';
  end if;
end;
$$;

-- The service role's sync, as the worker runs it.
set local role service_role;
do $$
declare
  parcel public.packages;
begin
  select * into parcel from public.packages where tracking_number = 'EXAMPLE0000000103';
  if not public.apply_tracking_sync(parcel.id, parcel.tracking_generation,
      jsonb_build_object('sync_status', 'ok', 'carrier_data', parcel.carrier_data), '[]', '{}',
      array['kun-express:carrier']) then
    raise exception 'the service role''s sync was not saved';
  end if;
  if (select carrier_data#>>'{routing,last_event_at}' from public.packages where id = parcel.id)
      is distinct from '2026-10-20T15:15:00.000Z' then
    raise exception 'the service role''s sync kept a dropped row''s watermark';
  end if;
end;
$$;
rollback;
select 'dropped row watermark assertions passed' as result;
