\set ON_ERROR_STOP on
begin;
-- Two servers' checks of one parcel, each under a live lease of its own: the old container's
-- scheduled run and the new one's check of the parcel, as during a deploy.
delete from public.sync_jobs;
insert into public.packages (id, user_id, tracking_number, carrier)
values ('9a000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001', 'FENCETEST0001', 'ups');
insert into public.sync_jobs (id, kind, dedupe_key, state, locked_by, lease_until, started_at, attempts)
values ('9a000000-0000-0000-0000-000000000002', 'scheduled', 'fence-test-scheduled', 'running', 'old-server',
  clock_timestamp() + interval '10 minutes', now(), 1);
insert into public.sync_jobs (id, user_id, package_id, kind, dedupe_key, state, locked_by, lease_until, started_at, attempts)
values ('9a000000-0000-0000-0000-000000000003', '10000000-0000-0000-0000-000000000001',
  '9a000000-0000-0000-0000-000000000001', 'package', 'fence-test-package', 'running', 'new-server',
  clock_timestamp() + interval '10 minutes', now(), 1);
-- What both checks read: one scan, a universal copy the next answer absorbs and one it drops.
insert into public.tracking_events (package_id, provider_event_id, stage, description, occurred_at, raw_data) values
  ('9a000000-0000-0000-0000-000000000001', 'ship24:stray', 'in_transit', 'Another parcel''s scan', '2026-10-19T07:00:00Z',
    '{"universal_provider":"Ship24"}'),
  ('9a000000-0000-0000-0000-000000000001', 'ups:accepted', 'accepted', 'Shipper created a label', '2026-10-20T08:00:00Z', '{}'),
  ('9a000000-0000-0000-0000-000000000001', 'ship24:copy', 'in_transit', 'Departed facility', '2026-10-20T09:00:00Z',
    '{"universal_provider":"Ship24"}');

do $$
declare
  parcel_id constant uuid := '9a000000-0000-0000-0000-000000000001';
  scheduled_job constant uuid := '9a000000-0000-0000-0000-000000000002';
  parcel_job constant uuid := '9a000000-0000-0000-0000-000000000003';
  generation uuid;
  read_revision bigint;
  scheduled_revision bigint;
  parcel_revision bigint;
  saved_data jsonb;
  applied boolean;
begin
  if not has_function_privilege('service_role', 'public.apply_tracking_sync(uuid,uuid,jsonb,jsonb,text[],text[],bigint,text[])', 'execute')
      or not has_function_privilege('service_role', 'public.apply_leased_tracking_sync(uuid,text,uuid,uuid,jsonb,jsonb,text[],text[],bigint,text[])', 'execute')
      or has_function_privilege('authenticated', 'public.apply_tracking_sync(uuid,uuid,jsonb,jsonb,text[],text[],bigint,text[])', 'execute')
      or has_function_privilege('anon', 'public.apply_leased_tracking_sync(uuid,text,uuid,uuid,jsonb,jsonb,text[],text[],bigint,text[])', 'execute') then
    raise exception 'fenced writes have the wrong grants';
  end if;
  -- One function by each name, so the database's API never has two to choose from.
  if (select count(*) from pg_proc where pronamespace = 'public'::regnamespace and proname = 'apply_tracking_sync') <> 1
      or (select count(*) from pg_proc where pronamespace = 'public'::regnamespace and proname = 'apply_leased_tracking_sync') <> 1 then
    raise exception 'an old signature of the tracking write is left';
  end if;

  select tracking_generation, sync_revision into generation, read_revision from public.packages where id = parcel_id;
  if read_revision <> 0 then raise exception 'a new parcel starts at revision %', read_revision; end if;

  -- The old server's run claims the parcel first: its check holds the revision after its claim.
  if not public.apply_leased_tracking_sync(scheduled_job, 'old-server', parcel_id, generation,
      '{"sync_status":"syncing","sync_error":null}', '[]', '{}', '{}', read_revision, '{}') then
    raise exception 'the first claim was turned away';
  end if;
  scheduled_revision := read_revision + 1;

  -- A check that read the parcel before that claim is turned away at its own, before it asks anyone.
  if public.apply_leased_tracking_sync(parcel_job, 'new-server', parcel_id, generation,
      '{"sync_status":"syncing","sync_error":null}', '[]', '{}', '{}', read_revision, '{}') is not null then
    raise exception 'a second claim from the same revision was not turned away';
  end if;

  -- The parcel's own check reads it after the run's claim, claims it and finds it delivered.
  select sync_revision into parcel_revision from public.packages where id = parcel_id;
  if parcel_revision <> scheduled_revision then raise exception 'a turned-away claim moved the revision'; end if;
  if not public.apply_leased_tracking_sync(parcel_job, 'new-server', parcel_id, generation,
      '{"sync_status":"syncing","sync_error":null}', '[]', '{}', '{}', parcel_revision, '{}') then
    raise exception 'a claim from the current revision was turned away';
  end if;
  parcel_revision := parcel_revision + 1;
  -- Its own claim does not turn its result away. The carrier's scan absorbs the universal copy.
  if not public.apply_leased_tracking_sync(parcel_job, 'new-server', parcel_id, generation,
      '{"current_stage":"delivered","last_status_text":"Delivered","last_synced_at":"2026-10-21T12:05:00Z",
        "sync_status":"ok","sync_error":null,"carrier_data":{"routing":{"version":1,"last_event_at":"2026-10-21T11:58:00Z"}}}',
      '[{"provider_event_id":"ups:accepted","stage":"accepted","description":"Shipper created a label","occurred_at":"2026-10-20T08:00:00Z"},
        {"provider_event_id":"ups:departed","stage":"in_transit","description":"Departed from facility","occurred_at":"2026-10-20T09:00:00Z"},
        {"provider_event_id":"ups:delivered","stage":"delivered","description":"Delivered","occurred_at":"2026-10-21T11:58:00Z"}]',
      '{}', array['ship24:copy', 'ship24:stray'], parcel_revision, array['ups:departed', 'ups:delivered']) then
    raise exception 'a check''s own claim turned its result away';
  end if;
  parcel_revision := parcel_revision + 1;
  select carrier_data into saved_data from public.packages where id = parcel_id;

  -- The run's answer, read before the delivery, lands last. It rewords a stored scan, deletes
  -- the delivery's row and a stored one by their words, and holds a copy of a scan the delivered
  -- write already stored, a scan in the delivery's minute, the rows its check read that the newer
  -- write deleted, its own app row, one observed without a time and one scan nobody stored.
  applied := public.apply_leased_tracking_sync(scheduled_job, 'old-server', parcel_id, generation,
    '{"current_stage":"in_transit","last_status_text":"In transit","last_synced_at":"2026-10-21T12:01:00Z",
      "sync_status":"ok","sync_error":null,"carrier_data":{"routing":{"version":1,"last_event_at":"2026-10-21T10:30:00Z"}}}',
    '[{"provider_event_id":"ups:accepted","stage":"accepted","description":"Label created (stale words)","occurred_at":"2026-10-20T08:00:00Z"},
      {"provider_event_id":"ship24:stray","stage":"in_transit","description":"Another parcel''s scan","occurred_at":"2026-10-19T07:00:00Z"},
      {"provider_event_id":"ship24:copy","stage":"in_transit","description":"Departed facility","occurred_at":"2026-10-20T09:00:00Z"},
      {"provider_event_id":"ship24:departed","stage":"in_transit","description":"Departed from facility","occurred_at":"2026-10-20T09:00:30Z"},
      {"provider_event_id":"ship24:late","stage":"out_for_delivery","description":"Out for delivery","occurred_at":"2026-10-21T11:58:20Z"},
      {"provider_event_id":"ship24:sorted","stage":"in_transit","description":"Sorted","occurred_at":"2026-10-21T10:30:00Z"},
      {"provider_event_id":"app:stage:in_transit","stage":"in_transit","description":"In transit","occurred_at":"2026-10-21T12:01:00Z"},
      {"provider_event_id":"ship24:untimed","stage":"in_transit","description":"Moving","occurred_at":"2026-10-21T12:01:00Z",
        "raw_data":{"observed_without_provider_timestamp":true}}]',
    array['Delivered', 'Shipper created a label'], array['ups:delivered'], scheduled_revision,
    array['ship24:departed', 'ship24:late', 'ship24:sorted', 'app:stage:in_transit', 'ship24:untimed']);
  if applied is not null then raise exception 'an overtaken write answered %', applied; end if;

  -- The delivery stands: summary, check time, routing and revision are the newer write's.
  if not exists (select 1 from public.packages where id = parcel_id and current_stage = 'delivered'
      and last_status_text = 'Delivered' and last_synced_at = '2026-10-21T12:05:00Z' and sync_revision = parcel_revision
      and carrier_data = saved_data) then
    raise exception 'an overtaken write changed the parcel: %',
      (select to_jsonb(p) - 'tracking_generation' from public.packages p where id = parcel_id);
  end if;
  -- Its deletions and rewrites did not apply, the row the newer write deleted did not come back,
  -- and only its one new scan nobody held was kept.
  if (select array_agg(provider_event_id || '=' || description order by occurred_at) from public.tracking_events
      where package_id = parcel_id and provider_event_id not like 'app:%')
      is distinct from array['ups:accepted=Shipper created a label', 'ups:departed=Departed from facility',
        'ship24:sorted=Sorted', 'ups:delivered=Delivered'] then
    raise exception 'an overtaken write left the wrong rows: %', (select array_agg(provider_event_id || '=' || description
      order by occurred_at) from public.tracking_events where package_id = parcel_id);
  end if;
  if exists (select 1 from public.tracking_events where package_id = parcel_id and provider_event_id = 'app:stage:in_transit') then
    raise exception 'an overtaken write stored its own app row';
  end if;

  -- The run's next write from its old revision is fenced too, whatever it carries.
  if public.apply_leased_tracking_sync(scheduled_job, 'old-server', parcel_id, generation,
      '{"sync_status":"error","sync_error":"Carrier unavailable"}', '[]', '{}', '{}', scheduled_revision, '{}') is not null
      or not exists (select 1 from public.packages where id = parcel_id and sync_status = 'ok' and sync_error is null) then
    raise exception 'an overtaken error state was written';
  end if;

  -- A check that reads the parcel after the delivery, and finds the carrier withdrew it, corrects it.
  select sync_revision into read_revision from public.packages where id = parcel_id;
  if not public.apply_leased_tracking_sync(parcel_job, 'new-server', parcel_id, generation,
      '{"sync_status":"syncing","sync_error":null}', '[]', '{}', '{}', read_revision, '{}')
      or not public.apply_leased_tracking_sync(parcel_job, 'new-server', parcel_id, generation,
        '{"current_stage":"in_transit","last_status_text":"Delivery attempt pending","last_synced_at":"2026-10-21T14:00:00Z",
          "sync_status":"ok","sync_error":null}',
        '[{"provider_event_id":"ups:departed","stage":"in_transit","description":"Departed from facility","occurred_at":"2026-10-20T09:00:00Z"}]',
        '{}', array['ups:delivered'], read_revision + 1, '{}') then
    raise exception 'a carrier''s correction was turned away';
  end if;
  if not exists (select 1 from public.packages where id = parcel_id and current_stage = 'in_transit'
      and last_synced_at = '2026-10-21T14:00:00Z' and sync_revision = read_revision + 2)
      or exists (select 1 from public.tracking_events where package_id = parcel_id and provider_event_id = 'ups:delivered') then
    raise exception 'a carrier''s correction did not land';
  end if;

  -- The write before this one takes no revision: it is not fenced, and still moves the revision on.
  select sync_revision into read_revision from public.packages where id = parcel_id;
  if not public.apply_tracking_sync(parcel_id, generation, '{"sync_status":"waiting"}')
      or not public.apply_tracking_sync(
        p_package_id => parcel_id, p_tracking_generation => generation, p_values => '{"sync_status":"ok"}',
        p_events => '[{"provider_event_id":"ups:arrived","stage":"in_transit","description":"Arrived","occurred_at":"2026-10-21T15:00:00Z"}]',
        p_delete_descriptions => '{}', p_delete_event_ids => '{}')
      or not public.apply_leased_tracking_sync(
        p_job_id => parcel_job, p_worker_id => 'new-server', p_package_id => parcel_id, p_tracking_generation => generation,
        p_values => '{"sync_status":"ok"}', p_events => '[]', p_delete_descriptions => '{}', p_delete_event_ids => '{}') then
    raise exception 'a write without a revision was turned away';
  end if;
  if not exists (select 1 from public.packages where id = parcel_id and sync_revision = read_revision + 3)
      or not exists (select 1 from public.tracking_events where package_id = parcel_id and provider_event_id = 'ups:arrived') then
    raise exception 'a write without a revision did not apply as before';
  end if;

  -- A parcel whose carrier, number or inputs changed answers false, as before, whatever revision the check read.
  select sync_revision into read_revision from public.packages where id = parcel_id;
  update public.packages set tracking_number = 'FENCETEST0002' where id = parcel_id;
  if public.apply_tracking_sync(parcel_id, generation, '{"sync_status":"ok"}', '[]', '{}', '{}', read_revision, '{}')
      or public.apply_tracking_sync(parcel_id, generation, '{"sync_status":"ok"}', '[]', '{}', '{}', read_revision - 1, '{}') then
    raise exception 'a reconfigured parcel did not answer false';
  end if;
  -- A lost lease still raises before the fence is read.
  update public.sync_jobs set lease_until = clock_timestamp() - interval '1 minute' where id = scheduled_job;
  select tracking_generation into generation from public.packages where id = parcel_id;
  begin
    perform public.apply_leased_tracking_sync(scheduled_job, 'old-server', parcel_id, generation,
      '{"sync_status":"ok"}', '[]', '{}', '{}', 0, '{}');
    raise exception 'an expired lease wrote';
  exception when object_not_in_prerequisite_state then null;
  end;
end;
$$;
rollback;
