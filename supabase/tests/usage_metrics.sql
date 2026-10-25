\set ON_ERROR_STOP on
begin;

-- Grafana reads the counts and nothing else, and only the server rewrites them.
do $$
begin
  if not has_table_privilege('metrics_reader', 'metrics.daily', 'SELECT')
      or not has_table_privilege('metrics_reader', 'metrics.active_accounts', 'SELECT')
      or not has_table_privilege('metrics_reader', 'metrics.live', 'SELECT')
      or not has_table_privilege('metrics_reader', 'metrics.open_parcels', 'SELECT')
      or not has_table_privilege('metrics_reader', 'metrics.carriers', 'SELECT')
      or not has_table_privilege('metrics_reader', 'metrics.health', 'SELECT') then
    raise exception 'Grafana cannot read the usage counts';
  end if;
  if has_table_privilege('metrics_reader', 'metrics.daily', 'INSERT')
      or has_table_privilege('metrics_reader', 'metrics.active_accounts', 'UPDATE')
      or has_table_privilege('metrics_reader', 'public.packages', 'SELECT')
      or has_table_privilege('metrics_reader', 'public.account_activity', 'SELECT')
      or has_table_privilege('metrics_reader', 'auth.users', 'SELECT')
      or has_table_privilege('metrics_reader', 'public.tracking_sync_attempts', 'SELECT')
      or has_function_privilege('metrics_reader', 'metrics.record_additions(date, date)', 'EXECUTE')
      or has_function_privilege('metrics_reader', 'public.record_usage_metrics()', 'EXECUTE') then
    raise exception 'Grafana can do more than read the usage counts';
  end if;
  if has_schema_privilege('anon', 'metrics', 'USAGE')
      or has_schema_privilege('authenticated', 'metrics', 'USAGE')
      or has_function_privilege('anon', 'public.record_usage_metrics()', 'EXECUTE')
      or has_function_privilege('authenticated', 'public.record_usage_metrics()', 'EXECUTE')
      or not has_function_privilege('service_role', 'public.record_usage_metrics()', 'EXECUTE') then
    raise exception 'The usage counts are reachable outside the server';
  end if;
end;
$$;

insert into auth.users (id, email) values ('f3000000-0000-4000-8000-000000000001', 'opener@example.com');

create function pg_temp.active(p_period text)
returns integer
language sql
as $$
  select coalesce((select accounts from metrics.active_accounts
    where period = p_period and starts = date_trunc(p_period, now() at time zone 'UTC')::date), 0);
$$;

-- An account counts once in each day, week and month it opens its deliveries in.
do $$
declare
  opener constant uuid := 'f3000000-0000-4000-8000-000000000001';
  day_before constant integer := pg_temp.active('day');
  week_before constant integer := pg_temp.active('week');
  month_before constant integer := pg_temp.active('month');
begin
  perform set_config('request.jwt.claim.sub', opener::text, true);
  perform public.record_account_opened();
  if pg_temp.active('day') <> day_before + 1 or pg_temp.active('week') <> week_before + 1
      or pg_temp.active('month') <> month_before + 1 then
    raise exception 'A first opening was not counted';
  end if;
  perform public.record_account_opened();
  if pg_temp.active('day') <> day_before + 1 then
    raise exception 'An opening within five minutes was counted again';
  end if;

  -- Late enough in the day that six minutes ago is today.
  if (now() at time zone 'UTC')::time >= time '00:10' then
    update public.account_activity set last_opened_at = now() - interval '6 minutes' where user_id = opener;
    perform public.record_account_opened();
    if (select last_opened_at from public.account_activity where user_id = opener) <> now()
        or pg_temp.active('day') <> day_before + 1 or pg_temp.active('month') <> month_before + 1 then
      raise exception 'A second opening the same day was counted again';
    end if;
  end if;

  update public.account_activity set last_opened_at = now() - interval '40 days' where user_id = opener;
  perform public.record_account_opened();
  if pg_temp.active('day') <> day_before + 2 or pg_temp.active('week') <> week_before + 2
      or pg_temp.active('month') <> month_before + 2 then
    raise exception 'An account back after 40 days was not counted again';
  end if;

  perform set_config('request.jwt.claim.sub', '', true);
  perform public.record_account_opened();
  if pg_temp.active('day') <> day_before + 2 then
    raise exception 'An opening without an account was counted';
  end if;
end;
$$;

-- Each pass rewrites today's counts, and yesterday's additions.
do $$
declare
  today constant date := (now() at time zone 'UTC')::date;
  counted constant uuid := 'f3000000-0000-4000-8000-000000000002';
  before metrics.daily;
  after metrics.daily;
  yesterday_before integer;
begin
  perform public.record_usage_metrics();
  select * into strict before from metrics.daily where day = today;
  yesterday_before := (select parcels_added_lookup from metrics.daily where day = today - 1);

  insert into auth.users (id, email) values (counted, 'counted@example.com');
  insert into public.packages (user_id, tracking_number, carrier, current_stage, archived_at) values
    (counted, 'USAGE0001', 'unknown', 'in_transit', null),
    (counted, 'USAGE0002', 'unknown', 'delivered', null),
    (counted, 'USAGE0003', 'unknown', 'in_transit', now());
  insert into public.packages (user_id, one_off, tracking_number, carrier, created_at)
  values (null, true, 'USAGE0004', 'unknown', today::timestamp at time zone 'UTC' - interval '1 minute');
  perform public.record_usage_metrics();
  select * into strict after from metrics.daily where day = today;

  if after.accounts <> before.accounts + 1 or after.accounts_created <> before.accounts_created + 1 then
    raise exception 'The new account was not counted: % then %', before, after;
  end if;
  if after.parcels_added_account <> before.parcels_added_account + 3
      or after.parcels_open_account <> before.parcels_open_account + 1 then
    raise exception 'Only the parcel in transit is being tracked: % then %', before, after;
  end if;
  if after.parcels_added_lookup <> before.parcels_added_lookup
      or after.parcels_open_lookup <> before.parcels_open_lookup + 1
      or (select parcels_added_lookup from metrics.daily where day = today - 1) <> coalesce(yesterday_before, 0) + 1 then
    raise exception 'A lookup in the last minute of yesterday was not counted for yesterday';
  end if;
end;
$$;

-- The alert rules' view counts the last three hours' checks, failures and the carriers they
-- failed for, the time since a check finished, slow scheduled syncs, open incidents, those of
-- them that failed in the last three hours, and their carriers once each, rescued ones aside.
do $$
declare
  parcel constant uuid := 'f3000000-0000-4000-8000-000000000003';
  rescued_check constant uuid := 'f3000000-0000-4000-8000-000000000004';
  before metrics.health;
  after metrics.health;
begin
  select * into strict before from metrics.health;
  insert into public.packages (id, user_id, one_off, tracking_number, carrier)
  values (parcel, null, true, 'HEALTH0001', 'unknown');
  insert into public.tracking_sync_attempts (package_id, trigger, configured_carrier, outcome, started_at, completed_at)
  values
    (parcel, 'scheduled', 'health-a', 'error', now() - interval '61 minutes', now() - interval '60 minutes'),
    (parcel, 'scheduled', 'health-b', 'error', now() - interval '61 minutes', now() - interval '60 minutes'),
    (parcel, 'scheduled', 'health-b', 'updated', now() - interval '61 minutes', now() - interval '60 minutes'),
    (parcel, 'package', 'health-b', 'superseded', now() - interval '61 minutes', now() - interval '60 minutes'),
    (parcel, 'package', 'health-c', 'running', now() - interval '1 minute', null),
    (parcel, 'scheduled', 'health-c', 'error', now() - interval '4 hours', now() - interval '4 hours');
  insert into public.sync_jobs (kind, state, started_at, completed_at) values
    ('scheduled', 'succeeded', now() - interval '20 minutes', now() - interval '20 minutes' + interval '400 seconds'),
    ('scheduled', 'failed', now() - interval '3 hours', now() - interval '150 minutes'),
    ('scheduled', 'running', now() - interval '30 minutes', null);
  insert into public.tracking_health_incidents (kind, subject, active)
  values ('provider', 'health-open', true), ('provider', 'health-closed', false),
    ('provider', 'health-failed-earlier', true), ('provider', 'health-healthy-since', true),
    -- The same carrier's direct step, and a direct step whose check a fallback answered.
    ('direct', 'health-open', true), ('direct', 'health-rescued', true);
  insert into public.tracking_health_samples (attempt_id, kind, subject, healthy, observed_at) values
    (gen_random_uuid(), 'provider', 'health-open', false, now() - interval '2 hours'),
    (gen_random_uuid(), 'provider', 'health-closed', false, now() - interval '2 hours'),
    (gen_random_uuid(), 'provider', 'health-failed-earlier', false, now() - interval '4 hours'),
    (gen_random_uuid(), 'provider', 'health-failed-earlier', true, now() - interval '1 hour'),
    (gen_random_uuid(), 'provider', 'health-healthy-since', true, now() - interval '1 hour'),
    (gen_random_uuid(), 'direct', 'health-open', false, now() - interval '2 hours'),
    (rescued_check, 'direct', 'health-rescued', false, now() - interval '1 hour'),
    (rescued_check, 'refresh', 'health-rescued', true, now() - interval '1 hour');
  select * into strict after from metrics.health;

  if after.checks_3h <> before.checks_3h + 3 or after.failed_checks_3h <> before.failed_checks_3h + 2
      or after.failed_carriers_3h <> before.failed_carriers_3h + 2 then
    raise exception 'The last three hours'' finished checks were miscounted: % then %', before, after;
  end if;
  if after.seconds_since_check > 3600 then
    raise exception 'The last finished check was missed: %', after;
  end if;
  if after.longest_scheduled_sync_2h < 400 or after.longest_scheduled_sync_2h >= 1800 then
    raise exception 'The longest scheduled sync of the last two hours was missed: %', after;
  end if;
  if after.open_health_incidents <> before.open_health_incidents + 5 then
    raise exception 'Open health incidents were miscounted: % then %', before, after;
  end if;
  if after.failing_health_incidents <> before.failing_health_incidents + 3 then
    raise exception 'Open incidents that failed in the last three hours were miscounted: % then %', before, after;
  end if;
  if after.failing_incident_carriers <> before.failing_incident_carriers + 1 then
    raise exception 'The carriers of unrescued failing incidents were miscounted: % then %', before, after;
  end if;
end;
$$;

-- A parcel counts once, in the hour its first check found a scan dated in the future; a parcel
-- flagged before that hour does not count again.
do $$
declare
  fresh constant uuid := 'f3000000-0000-4000-8000-000000000005';
  repeated constant uuid := 'f3000000-0000-4000-8000-000000000006';
  before metrics.health;
  after metrics.health;
begin
  select * into strict before from metrics.health;
  insert into public.packages (id, user_id, one_off, tracking_number, carrier)
  values (fresh, null, true, 'HEALTH0002', 'unknown'), (repeated, null, true, 'HEALTH0003', 'unknown');
  insert into public.tracking_sync_attempts (package_id, trigger, configured_carrier, outcome, started_at, completed_at, anomaly_codes)
  values
    (fresh, 'scheduled', 'health-d', 'updated', now() - interval '30 minutes', now() - interval '30 minutes', '{future_event_timestamp}'),
    (fresh, 'scheduled', 'health-d', 'unchanged', now() - interval '10 minutes', now() - interval '10 minutes', '{future_event_timestamp}'),
    (repeated, 'scheduled', 'health-d', 'updated', now() - interval '2 hours', now() - interval '2 hours', '{future_event_timestamp}'),
    (repeated, 'scheduled', 'health-d', 'unchanged', now() - interval '10 minutes', now() - interval '10 minutes', '{future_event_timestamp}');
  select * into strict after from metrics.health;

  if after.future_scan_parcels_1h <> before.future_scan_parcels_1h + 1 then
    raise exception 'Parcels first flagged with a future scan in the last hour were miscounted: % then %', before, after;
  end if;
end;
$$;

-- Grafana's role reads through the views without reading the tables behind them.
set local role metrics_reader;
select accounts, active_day, parcels_open_account, link_alerts from metrics.live;
select stage, account, lookup from metrics.open_parcels;
select day, carrier, parcels from metrics.carriers where carrier = 'unknown' limit 1;
select checks_3h, failed_carriers_3h, seconds_since_check, open_health_incidents, failing_health_incidents, failing_incident_carriers, future_scan_parcels_1h from metrics.health;
do $$
begin
  perform 1 from public.packages;
  raise exception 'Grafana''s role read the parcels';
exception when insufficient_privilege then null;
end;
$$;
reset role;

rollback;
