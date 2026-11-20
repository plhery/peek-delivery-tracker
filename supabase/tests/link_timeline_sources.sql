\set ON_ERROR_STOP on
begin;

-- A link's events name their source, for the server alone: it tells Peek's
-- own "Tracking added" from the carrier's scans, and passes neither on. They say
-- when Peek stored them too, which dates the parcel's news, and carry the fields an account's events carry.
do $$
declare
  link_id text;
  parcel uuid;
  sources text[];
begin
  if has_function_privilege('anon', 'public.parcel_link_view(text,text,boolean)', 'EXECUTE')
      or has_function_privilege('authenticated', 'public.parcel_link_view(text,text,boolean)', 'EXECUTE')
      or not has_function_privilege('service_role', 'public.parcel_link_view(text,text,boolean)', 'EXECUTE') then
    raise exception 'Reading a link must stay reserved to the server';
  end if;

  link_id := public.create_one_off_parcel('TIMELINE01', 'unknown', null, null, repeat('b', 64))#>>'{link,id}';
  select package_id into parcel from public.parcel_links where id = link_id;
  insert into public.tracking_events (package_id, stage, description, occurred_at, provider_event_id)
  values (parcel, 'in_transit', 'Scan', now() - interval '2 days', 'swiss-post:scan');

  select array_agg(event->>'provider_event_id' order by event->>'occurred_at' desc) into sources
  from jsonb_array_elements(public.parcel_link_view(link_id)#>'{package,tracking_events}') as event;
  if sources is distinct from array['app:pending', 'swiss-post:scan'] then
    raise exception 'A link''s events do not name their source: %', sources;
  end if;
  select array_agg(event->>'provider_event_id' order by event->>'occurred_at' desc) into sources
  from jsonb_array_elements(public.public_parcel(link_id)#>'{package,tracking_events}') as event;
  if sources is distinct from array['app:pending', 'swiss-post:scan'] then
    raise exception 'A new lookup''s events do not name their source: %', sources;
  end if;
  if exists (
    select 1 from jsonb_array_elements(public.parcel_link_view(link_id)#>'{package,tracking_events}') as event
    join public.tracking_events as stored on stored.id = (event->>'id')::uuid
    where (event->>'stored_at')::timestamptz is distinct from stored.created_at
  ) then
    raise exception 'A link''s events do not say when they were stored';
  end if;

  -- And they carry what an account's events carry: a provider's copy names its provider, a day-only scan its day, a
  -- re-dated row the time it was first given.
  insert into public.tracking_events (package_id, stage, description, occurred_at, provider_event_id, raw_data)
  values (parcel, 'in_transit', 'Scan', now() - interval '1 day', 'unknown:copy', jsonb_build_object(
    'universal_provider', '17TRACK', 'time', '2026-11-14', 'local_time', '2026-11-14', 'observed_without_provider_timestamp', true,
    'tie_order', 2, 'stage_source', 'none', 'retimed_from', '2026-11-14T08:00:00Z'));
  if not exists (
    select 1 from jsonb_array_elements(public.parcel_link_view(link_id)#>'{package,tracking_events}') as event
    where event->>'provider_event_id' = 'unknown:copy' and event->>'universal_provider' = '17TRACK'
      and event->>'time' = '2026-11-14' and event->>'local_time' = '2026-11-14' and event->'observed' = 'true'::jsonb
      and event->'tie_order' = '2'::jsonb and event->>'stage_source' = 'none' and event->>'retimed_from' = '2026-11-14T08:00:00Z'
  ) then
    raise exception 'A link''s events do not carry their provider, times, order, stage source and first time';
  end if;
end;
$$;

rollback;
