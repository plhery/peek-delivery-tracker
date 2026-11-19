\set ON_ERROR_STOP on
begin;

-- Grafana reads the guest counts, and nothing behind them.
do $$
begin
  if not has_table_privilege('metrics_reader', 'metrics.lookup_returns', 'SELECT')
      or not has_table_privilege('metrics_reader', 'metrics.lookup_accounts', 'SELECT')
      or not has_table_privilege('metrics_reader', 'metrics.link_alert_devices', 'SELECT') then
    raise exception 'Grafana cannot read the guest counts';
  end if;
  if has_table_privilege('metrics_reader', 'public.parcel_links', 'SELECT')
      or has_table_privilege('metrics_reader', 'public.parcel_link_alerts', 'SELECT')
      or has_table_privilege('anon', 'metrics.lookup_returns', 'SELECT')
      or has_table_privilege('authenticated', 'metrics.link_alert_devices', 'SELECT') then
    raise exception 'The guest counts open more than counts';
  end if;
end;
$$;

-- Start from no links, so the counts are the fixtures'.
delete from public.parcel_links;
insert into auth.users (id, email, created_at) values
  ('f4000000-0000-4000-8000-000000000001', 'kept@example.com', now() - interval '2 days'),
  ('f4000000-0000-4000-8000-000000000002', 'new@example.com', now() - interval '2 days');

do $$
declare
  made_day constant timestamptz := ((now() at time zone 'UTC')::date - 3)::timestamp at time zone 'UTC' + interval '10 hours';
  lookups text[] := '{}';
  parcels uuid[] := '{}';
  made jsonb;
  opened record;
  accounts record;
  web record;
  iphone record;
  android record;
begin
  for i in 1..4 loop
    made := public.create_one_off_parcel('3SGUEST00000' || (10 + i), 'unknown', null, null, repeat(i::text, 64));
    lookups := lookups || (made#>>'{link,id}');
    parcels := parcels || (made#>>'{package,id}')::uuid;
  end loop;
  -- Three lookups made the same day: one opened again the next day, one later that day.
  update public.parcel_links set created_at = made_day, last_opened_at = made_day + interval '1 day' where id = lookups[1];
  update public.parcel_links set created_at = made_day, last_opened_at = made_day + interval '5 hours' where id = lookups[2];
  update public.parcel_links set created_at = made_day, last_opened_at = made_day where id = lookups[3];
  -- One made before the 30 days the view keeps.
  update public.parcel_links set created_at = now() - interval '40 days', last_opened_at = now() where id = lookups[4];

  select * into opened from metrics.lookup_returns where day = (made_day at time zone 'UTC')::date;
  if opened.lookups <> 3 or opened.opened_later <> 1
      or (select count(*) from metrics.lookup_returns) <> 1 then
    raise exception 'Lookups opened again later were miscounted: %', opened;
  end if;

  -- A guest who signed in kept a lookup made before the account; a share from an account made after it is no lookup.
  update public.packages set user_id = 'f4000000-0000-4000-8000-000000000001', one_off = false where id = parcels[1];
  update public.parcel_links set created_by = 'f4000000-0000-4000-8000-000000000001', owner_key_hash = null where id = lookups[1];
  insert into public.parcel_links (package_id, created_by, created_at)
  values (parcels[2], 'f4000000-0000-4000-8000-000000000002', now());
  select * into accounts from metrics.lookup_accounts where day = ((now() - interval '2 days') at time zone 'UTC')::date;
  if accounts.accounts < 2 or accounts.from_lookup <> 1 then
    raise exception 'Accounts from a lookup were miscounted: %', accounts;
  end if;
  if (select returned.lookups from metrics.lookup_returns as returned where returned.day = (made_day at time zone 'UTC')::date) <> 2 then
    raise exception 'A kept lookup still counts as one';
  end if;

  -- One browser follows two parcels, an iPhone one, an Android phone one parcel through two of its links.
  insert into public.parcel_link_alerts (link_id, endpoint, p256dh, auth, locale, preset) values
    (lookups[2], 'https://push.example/one', 'key', 'auth', 'en', 'all'),
    (lookups[3], 'https://push.example/one', 'key', 'auth', 'en', 'all'),
    (lookups[3], 'https://push.example/two', 'key', 'auth', 'en', 'all');
  insert into public.parcel_link_alerts (link_id, apns_token, apns_environment, locale, preset)
  values (lookups[2], repeat('ab', 32), 'production', 'en', 'delivery');
  insert into public.parcel_link_alerts (link_id, fcm_token, locale, preset)
  values (lookups[2], repeat('A', 40), 'en', 'important');
  insert into public.parcel_links (id, package_id, owner_key_hash) values ('k7Qm2xHd9RtW', parcels[2], repeat('9', 64));
  insert into public.parcel_link_alerts (link_id, fcm_token, locale, preset)
  values ('k7Qm2xHd9RtW', repeat('A', 40), 'en', 'important');

  select * into web from metrics.link_alert_devices where kind = 'web';
  select * into iphone from metrics.link_alert_devices where kind = 'iphone';
  select * into android from metrics.link_alert_devices where kind = 'android';
  if web.devices <> 2 or web.following_several <> 1 or iphone.devices <> 1 or iphone.following_several <> 0
      or android.devices <> 1 or android.following_several <> 0 then
    raise exception 'Devices with alerts were miscounted: % % %', web, iphone, android;
  end if;
end;
$$;

rollback;
