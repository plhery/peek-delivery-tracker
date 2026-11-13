\set ON_ERROR_STOP on
begin;
set local role service_role;
do $$
declare
  key constant text := repeat('a', 64);
  held_link text := public.create_one_off_parcel('HELDRETURN0001', 'unknown', null, null, key) -> 'link' ->> 'id';
  ended_link text := public.create_one_off_parcel('HELDRETURN0002', 'unknown', null, null, key) -> 'link' ->> 'id';
  fresh_link text := public.create_one_off_parcel('HELDRETURN0003', 'unknown', null, null, key) -> 'link' ->> 'id';
  held uuid;
  ended uuid;
  fresh uuid;
begin
  select id into held from public.packages where tracking_number = 'HELDRETURN0001';
  select id into ended from public.packages where tracking_number = 'HELDRETURN0002';
  select id into fresh from public.packages where tracking_number = 'HELDRETURN0003';
  -- Looked up 45 days ago, opened an hour ago: a return 40 days ago, then scans carrying the parcel back,
  -- the newest two days ago. The first is a held return, the second a return the carrier states.
  update public.parcel_links set created_at = now() - interval '45 days', last_opened_at = now() - interval '1 hour'
  where package_id in (held, ended);
  insert into public.tracking_events (package_id, stage, description, occurred_at, provider_event_id)
  select package_id, stage, 'Scan', now() - age, 'unknown:' || package_id || ':' || age
  from (values ('returned', interval '40 days'), ('in_transit', interval '20 days'), ('in_transit', interval '2 days')) as scan(stage, age)
  cross join (values (held), (ended)) as parcel(package_id);
  update public.packages set current_stage = 'returned', carrier_data = '{"return_held": true}' where id in (held, fresh);
  update public.packages set current_stage = 'returned' where id = ended;
  insert into public.tracking_events (package_id, stage, description, occurred_at, provider_event_id) values
    (fresh, 'returned', 'Scan', now() - interval '3 days', 'unknown:fresh:return');

  -- A held return's link is forgotten as an open parcel's: 90 days after its last opening.
  if (select round(extract(epoch from public.parcel_link_forget_at(link) - now()) / 86400) from public.parcel_links as link
      where id = held_link) <> 90 then
    raise exception 'A held return is forgotten 30 days after its return scan';
  end if;
  if (select public.parcel_link_forget_at(link) > now() from public.parcel_links as link where id = ended_link) then
    raise exception 'A return the carrier states is no longer forgotten 30 days after it';
  end if;

  -- An alert can still be turned on for a held return, not for a finished one.
  if public.add_parcel_link_alert(fresh_link, key, 'https://push.example.test/held', 'p256dh', 'auth', 'en', 'all') <> 'added' then
    raise exception 'An alert on a held return was refused';
  end if;
  if public.add_parcel_link_app_alert(fresh_link, key, repeat('held-token:', 4), 'production', 'en', 'all', 'fcm') <> 'added' then
    raise exception 'An app alert on a held return was refused';
  end if;
  update public.packages set carrier_data = '{}' where id = fresh;
  if public.add_parcel_link_alert(fresh_link, key, 'https://push.example.test/ended', 'p256dh', 'auth', 'en', 'all') <> 'finished' then
    raise exception 'An alert on a finished return was accepted';
  end if;
  update public.packages set carrier_data = '{"return_held": true}' where id = fresh;

  -- The alert queue says the return is held.
  insert into public.tracking_events (package_id, stage, description, occurred_at, provider_event_id, created_at) values
    (fresh, 'in_transit', 'Scan', now() - interval '1 hour', 'unknown:fresh:back', now() + interval '1 minute');
  if (select count(*) filter (where return_held) from public.pending_parcel_link_alerts where link_id = fresh_link) <> 2 then
    raise exception 'The alert queue does not say the return is held';
  end if;
  if exists (select 1 from public.pending_parcel_link_alerts where link_id <> fresh_link and return_held) then
    raise exception 'The alert queue says another parcel''s return is held';
  end if;

  -- The sweep keeps the held return, its link and its alerts; the finished return goes.
  delete from public.tracking_events where package_id = fresh and provider_event_id = 'unknown:fresh:back';
  perform public.forget_expired_parcel_links();
  if not exists (select 1 from public.packages where id = held) then
    raise exception 'The sweep forgot a held return the sync still follows';
  end if;
  if exists (select 1 from public.packages where id = ended) then
    raise exception 'The sweep kept a return the carrier states';
  end if;
  if (select count(*) from public.parcel_link_alerts where link_id = fresh_link) <> 2 then
    raise exception 'The sweep removed the alerts of a held return';
  end if;
end;
$$;
rollback;
