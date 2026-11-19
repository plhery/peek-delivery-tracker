\set ON_ERROR_STOP on
begin;
truncate public.tracking_health_samples, public.tracking_health_incidents;

insert into auth.users (id, email, is_anonymous) values ('f3000000-0000-4000-8000-000000000001', 'outage@example.com', false);

do $$
begin
  if has_function_privilege('anon', 'public.carrier_outages()', 'EXECUTE')
      or has_function_privilege('authenticated', 'public.carrier_outages()', 'EXECUTE')
      or not has_function_privilege('service_role', 'public.carrier_outages()', 'EXECUTE') then
    raise exception 'Listing the carriers down must be reserved to the server';
  end if;
end;
$$;

-- One lookup of a carrier for a parcel, hours ago: an answer, or a failure in the given category on the given step.
create function pg_temp.lookup(p_carrier text, p_number text, p_hours numeric, p_healthy boolean,
  p_category text default 'transport', p_reason text default null, p_step text default 'direct')
returns void language plpgsql as $$
declare parcel uuid; attempt uuid := gen_random_uuid();
begin
  select id into parcel from public.packages where tracking_number = p_number;
  if parcel is null then
    insert into public.packages (user_id, tracking_number, carrier, current_stage)
    values ('f3000000-0000-4000-8000-000000000001', p_number, p_carrier, 'in_transit') returning id into parcel;
  end if;
  insert into public.tracking_sync_attempts (id, package_id, trigger, configured_carrier, started_at)
  values (attempt, parcel, 'scheduled', p_carrier, now() - make_interval(secs => p_hours * 3600));
  insert into public.tracking_health_samples (attempt_id, kind, subject, healthy, details, observed_at)
  values (attempt, 'provider', p_carrier, p_healthy,
    case when p_healthy then jsonb_build_object('category', 'ok', 'step', p_step)
      else jsonb_build_object('category', p_category, 'reason', p_reason, 'step', p_step) end,
    now() - make_interval(secs => p_hours * 3600));
end;
$$;

create function pg_temp.down() returns text language sql as $$
  select coalesce(string_agg(carrier, ',' order by carrier), '') from public.carrier_outages();
$$;

-- Every carrier below has an open incident but gls-fr.
insert into public.tracking_health_incidents (kind, subject, active)
select kind, subject, true from (values ('refresh', 'colis-prive'), ('provider', 'nacex'), ('provider', 'brt'),
  ('refresh', 'seur'), ('provider', 'dpd-fr'), ('provider', 'usps')) incident(kind, subject);

-- Down: two parcels' lookups find no site since the carrier last answered.
select pg_temp.lookup('colis-prive', 'TESTCP00001', 6, true);
select pg_temp.lookup('colis-prive', 'TESTCP00001', 2, false);
select pg_temp.lookup('colis-prive', 'TESTCP00002', 1, false, 'transport', 'lookup_unavailable');
-- A lookup through another service says nothing of the carrier's site.
select pg_temp.lookup('colis-prive', 'TESTCP00002', 0.5, false, 'transport', 'lookup_pending', 'trawl');
-- Ended: the carrier answered after its failures, a not-found included.
select pg_temp.lookup('nacex', 'TESTNX00001', 3, false);
select pg_temp.lookup('nacex', 'TESTNX00002', 2, false);
select pg_temp.lookup('nacex', 'TESTNX00001', 1, true, 'not_found');
update public.tracking_health_samples set details = jsonb_build_object('category', 'not_found', 'step', 'direct')
  where subject = 'nacex' and healthy;
-- One parcel's own failure: a bot check on two parcels, and one parcel's two failures within the hour.
select pg_temp.lookup('brt', 'TESTBR00001', 3, false, 'challenge');
select pg_temp.lookup('brt', 'TESTBR00002', 2, false, 'challenge');
select pg_temp.lookup('dpd-fr', 'TESTDF00001', 0.9, false);
select pg_temp.lookup('dpd-fr', 'TESTDF00001', 0.2, false);
-- One parcel's site down for over an hour, three lookups: the site down for every number.
select pg_temp.lookup('seur', 'TESTSE00001', 2, false, 'indeterminate', 'maintenance_page', 'trawl');
select pg_temp.lookup('seur', 'TESTSE00001', 1.5, false, 'maintenance');
select pg_temp.lookup('seur', 'TESTSE00001', 0.5, false, 'upstream', null);
update public.tracking_health_samples set details = details || '{"http_status": 503}'
  where subject = 'seur' and details->>'category' = 'upstream';
-- A site down for two parcels, but with a bot check among the failures: not only the site.
select pg_temp.lookup('usps', 'TESTUS00001', 2, false, 'indeterminate', 'maintenance_page');
select pg_temp.lookup('usps', 'TESTUS00002', 1, false, 'challenge', 'api_denied');
-- No open incident: not down yet.
select pg_temp.lookup('gls-fr', 'TESTGL00001', 2, false);
select pg_temp.lookup('gls-fr', 'TESTGL00002', 1, false);

do $$
declare since timestamptz;
begin
  if pg_temp.down() <> 'colis-prive,seur' then
    raise exception 'Carriers down: expected colis-prive,seur, got %', pg_temp.down();
  end if;
  select o.since into since from public.carrier_outages() o where o.carrier = 'colis-prive';
  if since not between now() - interval '2 hours 1 minute' and now() - interval '1 hour 59 minutes' then
    raise exception 'An outage dates from its first failure after the last answer, not %', since;
  end if;
end;
$$;

-- The first answer ends it, before the incident recovers; a recovering incident ends it too.
select pg_temp.lookup('colis-prive', 'TESTCP00002', 0.1, true);
update public.tracking_health_incidents set pending = '{"state": "recovered"}' where subject = 'seur';
do $$
begin
  if pg_temp.down() <> '' then raise exception 'An answer ends the outage, got %', pg_temp.down(); end if;
end;
$$;

rollback;
