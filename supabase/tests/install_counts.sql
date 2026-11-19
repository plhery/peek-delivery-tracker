\set ON_ERROR_STOP on
begin;

-- Only the server records a sighting, and Grafana reads counts, never the installs.
do $$
begin
  if has_table_privilege('metrics_reader', 'private.installs', 'SELECT')
      or has_table_privilege('anon', 'private.installs', 'SELECT')
      or has_table_privilege('authenticated', 'private.installs', 'SELECT')
      or has_function_privilege('anon', 'public.record_install_seen(text, text, boolean, integer)', 'EXECUTE')
      or has_function_privilege('authenticated', 'public.record_install_seen(text, text, boolean, integer)', 'EXECUTE')
      or has_function_privilege('metrics_reader', 'public.record_install_seen(text, text, boolean, integer)', 'EXECUTE')
      or not has_function_privilege('service_role', 'public.record_install_seen(text, text, boolean, integer)', 'EXECUTE') then
    raise exception 'The installs are reachable outside the server';
  end if;
  if not has_table_privilege('metrics_reader', 'metrics.installs_daily', 'SELECT')
      or not has_table_privilege('metrics_reader', 'metrics.active_installs', 'SELECT')
      or not has_table_privilege('metrics_reader', 'metrics.guest_cohorts', 'SELECT')
      or has_table_privilege('metrics_reader', 'metrics.installs_daily', 'INSERT') then
    raise exception 'Grafana cannot read the install counts, or can write them';
  end if;
end;
$$;

do $$
declare
  guest constant text := repeat('a', 64);
  member constant text := repeat('b', 64);
  today constant date := (now() at time zone 'UTC')::date;
  counts jsonb;
begin
  -- A guest's first day, seen twice: counted once, as new.
  perform public.record_install_seen(guest, 'iphone', false, 2);
  perform public.record_install_seen(guest, 'iphone', false, 3);
  -- It signs in later the same day: a conversion, not a second sighting.
  perform public.record_install_seen(guest, 'iphone', true, 3);
  perform public.record_install_seen(guest, 'iphone', true, 3);
  -- A signed-in browser.
  perform public.record_install_seen(member, 'web', true, 0);

  select jsonb_object_agg(platform || ':' || kind, installs) into counts from metrics.installs_daily where day = today;
  if counts <> '{"iphone:new": 1, "iphone:converted": 1, "web:account": 1}'::jsonb then
    raise exception 'Unexpected counts for the first day: %', counts;
  end if;
  if (select (days_seen, guest_on, signed_in_on, parcels) from private.installs where install = guest)
      is distinct from (1, today, today, 3) then
    raise exception 'The guest''s row is wrong';
  end if;

  -- The next day, without an account again: returning, one more day seen.
  update private.installs set first_seen = today - 1, last_seen = today - 1, guest_on = today - 1, signed_in_on = null
    where install = guest;
  delete from metrics.installs_daily;
  perform public.record_install_seen(guest, 'iphone', false, 1);
  select jsonb_object_agg(platform || ':' || kind, installs) into counts from metrics.installs_daily where day = today;
  if counts <> '{"iphone:returning": 1}'::jsonb then
    raise exception 'Unexpected counts for a returning guest: %', counts;
  end if;
  if (select days_seen from private.installs where install = guest) <> 2 then
    raise exception 'A new day was not counted';
  end if;

  if (select (week, guests_week) from metrics.active_installs where platform = 'iphone') is distinct from (1::bigint, 1::bigint)
      or (select (week, guests_week) from metrics.active_installs where platform = 'web') is distinct from (1::bigint, 0::bigint) then
    raise exception 'Unexpected active installs';
  end if;
  if (select sum(came_back) from metrics.guest_cohorts) <> 1 then
    raise exception 'The returning guest is missing from its cohort';
  end if;

  -- Not seen for 91 days, or first seen 25 months ago: gone at the next maintenance pass.
  update private.installs set last_seen = today - 91 where install = guest;
  update private.installs set first_seen = (today - interval '25 months 1 day')::date where install = member;
  perform public.record_usage_metrics();
  if exists (select 1 from private.installs) then
    raise exception 'Old installs were kept';
  end if;
end;
$$;

-- Nothing that is not a hash or a known platform.
do $$
begin
  begin
    perform public.record_install_seen('not-a-hash', 'web', false, 0);
    raise exception 'A raw id was accepted';
  exception when invalid_parameter_value then null;
  end;
  begin
    perform public.record_install_seen(repeat('c', 64), 'watch', false, 0);
    raise exception 'An unknown platform was accepted';
  exception when invalid_parameter_value then null;
  end;
end;
$$;

rollback;
