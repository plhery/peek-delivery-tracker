\set ON_ERROR_STOP on
begin;
-- A delivery-day activity starts once per stretch out for delivery. A provider's
-- copy of the carrier's scan, read as another stage, starts no new stretch; the
-- carrier's own scan in another stage, or a provider's step the carrier never
-- shows, does.
insert into auth.users(id, email) values ('97000000-0000-0000-0000-000000000001', 'copies@example.invalid');
insert into auth.sessions(id, user_id) values ('97000000-0000-0000-0000-000000000002', '97000000-0000-0000-0000-000000000001');
set local role service_role;
insert into public.live_activity_devices(id, user_id, session_id, installation_id, token, environment, subscribed_at)
values ('97000000-0000-0000-0000-000000000003', '97000000-0000-0000-0000-000000000001',
  '97000000-0000-0000-0000-000000000002', '97000000-0000-0000-0000-000000000004', repeat('ab', 32), 'development',
  '2026-10-01T00:00:00Z');
insert into public.packages(id, user_id, tracking_number, label, carrier, current_stage, carrier_data)
values ('97000000-0000-0000-0000-000000000005', '97000000-0000-0000-0000-000000000001', 'COPYTEST1', 'Copies',
  'posti', 'out_for_delivery', '{"routing": {"confirmed_carrier": "posti"}}');
insert into public.tracking_events(id, package_id, stage, description, provider_event_id, occurred_at, created_at, raw_data)
values ('97000000-0000-0000-0000-000000000006', '97000000-0000-0000-0000-000000000005', 'out_for_delivery',
  'Out for delivery', 'posti:out', '2026-10-08T08:40:00Z', '2026-10-08T08:45:00Z', '{}');
insert into public.live_activity_event_deliveries(device_id, event_id, package_id, delivery_kind, event_created_at)
values ('97000000-0000-0000-0000-000000000003', '97000000-0000-0000-0000-000000000006',
  '97000000-0000-0000-0000-000000000005', 'start', '2026-10-08T08:45:00Z');
-- 17TRACK stores the scan again, in its own words and stage, then the carrier tells more of the round.
insert into public.tracking_events(id, package_id, stage, description, provider_event_id, occurred_at, created_at, raw_data)
values ('97000000-0000-0000-0000-000000000007', '97000000-0000-0000-0000-000000000005', 'in_transit',
  'Arrived at delivery facility', 'unknown:copy', '2026-10-08T08:40:21Z', '2026-10-08T08:50:00Z',
  '{"universal_provider": "17TRACK"}'),
 ('97000000-0000-0000-0000-000000000008', '97000000-0000-0000-0000-000000000005', 'out_for_delivery',
  'Two stops away', 'posti:stops', '2026-10-08T09:00:00Z', '2026-10-08T09:05:00Z', '{}');
do $$ begin
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000007') then
    raise exception 'A provider copy at the minute of the carrier''s scan was not hidden';
  end if;
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000006') then
    raise exception 'The carrier''s own scan counted as a provider copy';
  end if;
  if exists (select 1 from public.pending_live_activity_events where event_id = '97000000-0000-0000-0000-000000000008') then
    raise exception 'A provider copy in another stage let a second Live Activity start';
  end if;
end; $$;
-- Without a confirmed carrier, the timeline shows the provider's row, and it starts a new stretch.
update public.packages set carrier_data = '{}' where id = '97000000-0000-0000-0000-000000000005';
do $$ begin
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000007') then
    raise exception 'A provider row was hidden on a parcel with no confirmed carrier';
  end if;
  if not exists (select 1 from public.pending_live_activity_events where event_id = '97000000-0000-0000-0000-000000000008') then
    raise exception 'A shown provider row in another stage did not start a new stretch';
  end if;
end; $$;
update public.packages set carrier_data = '{"routing": {"confirmed_carrier": "posti"}}'
where id = '97000000-0000-0000-0000-000000000005';
-- A provider's step hours from any carrier scan, in another stage, is no copy: it starts a new stretch.
update public.tracking_events set occurred_at = '2026-10-08T06:07:00Z' where id = '97000000-0000-0000-0000-000000000007';
do $$ begin
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000007') then
    raise exception 'A provider step the carrier never shows was hidden';
  end if;
  if not exists (select 1 from public.pending_live_activity_events where event_id = '97000000-0000-0000-0000-000000000008') then
    raise exception 'A provider step the carrier never shows did not start a new stretch';
  end if;
end; $$;
-- A copy whole hours off in the carrier scan's stage is hidden; an unnamed universal row only inside
-- the time the carrier's answers covered, and never one they held.
update public.tracking_events set stage = 'out_for_delivery', occurred_at = '2026-10-08T10:40:00Z',
  raw_data = '{}' where id = '97000000-0000-0000-0000-000000000007';
update public.packages set carrier_data = '{"routing": {"confirmed_carrier": "posti"},
  "direct_history": {"from": "2026-10-08T08:00:00Z", "to": "2026-10-08T12:00:00Z", "held": []}}'
where id = '97000000-0000-0000-0000-000000000005';
do $$ begin
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000007') then
    raise exception 'An unnamed copy two hours off inside the carrier''s time was not hidden';
  end if;
end; $$;
update public.packages set carrier_data = jsonb_set(carrier_data, '{direct_history,held}', '["unknown:copy"]')
where id = '97000000-0000-0000-0000-000000000005';
do $$ begin
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000007') then
    raise exception 'A row a carrier''s answer held was hidden';
  end if;
end; $$;
-- A copy of a scan dated by its day, on a wall clock in no zone: in its stage and words, whole quarter hours off.
update public.packages set carrier_data = '{"routing": {"confirmed_carrier": "posti"}}'
where id = '97000000-0000-0000-0000-000000000005';
update public.tracking_events set occurred_at = '2026-10-08T00:00:00Z', stage = 'in_transit', description = 'Departed hub',
  raw_data = '{"local_time": "2026-10-08T14:20"}' where id = '97000000-0000-0000-0000-000000000006';
update public.tracking_events set occurred_at = '2026-10-08T06:20:41Z', stage = 'in_transit', description = 'Departed  Hub',
  raw_data = '{"universal_provider": "ParcelsApp"}' where id = '97000000-0000-0000-0000-000000000007';
do $$ begin
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000007') then
    raise exception 'A copy showing a day-dated scan''s wall clock was not hidden';
  end if;
end; $$;
update public.tracking_events set occurred_at = '2026-10-08T06:27:00Z' where id = '97000000-0000-0000-0000-000000000007';
do $$ begin
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000007') then
    raise exception 'A row showing another wall clock than the day-dated scan''s was hidden';
  end if;
  if has_function_privilege('authenticated', 'public.hidden_provider_copy(uuid)', 'execute') then
    raise exception 'Clients can run hidden_provider_copy';
  end if;
end; $$;
-- A provider reading the carrier's scans a zone away tells every scan of that minute on the same clock: its other
-- rows of the minute of a copy only whole hours from the carrier's scans are copies too, in any stage.
update public.tracking_events set occurred_at = '2026-10-08T08:40:00Z', stage = 'in_transit',
  description = 'The consignment has left the border point', raw_data = '{}' where id = '97000000-0000-0000-0000-000000000006';
update public.tracking_events set occurred_at = '2026-10-08T09:40:00Z', stage = 'in_transit',
  description = 'Departure from office of exchange', raw_data = '{"universal_provider": "ParcelsApp"}'
where id = '97000000-0000-0000-0000-000000000007';
insert into public.tracking_events(id, package_id, stage, description, provider_event_id, occurred_at, created_at, raw_data)
values ('97000000-0000-0000-0000-000000000009', '97000000-0000-0000-0000-000000000005', 'pending',
  'Item has passed a checkpoint', 'unknown:checkpoint', '2026-10-08T09:40:00Z', '2026-10-08T09:50:00Z',
  '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000010', '97000000-0000-0000-0000-000000000005', 'customs',
  'Customs', 'unknown:other', '2026-10-08T09:40:00Z', '2026-10-08T09:50:00Z', '{"universal_provider": "17TRACK"}');
do $$ begin
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000007')
    or not public.hidden_provider_copy('97000000-0000-0000-0000-000000000009') then
    raise exception 'A provider''s rows of the minute it read a zone away were not hidden';
  end if;
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000010') then
    raise exception 'Another provider''s row of that minute was hidden';
  end if;
end; $$;
-- A copy also close to a carrier's scan keeps the carrier's clock: the provider's other rows of its minute stay.
update public.tracking_events set occurred_at = '2026-10-08T09:35:00Z', stage = 'in_transit'
where id = '97000000-0000-0000-0000-000000000008';
do $$ begin
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000007') then
    raise exception 'A copy close to a carrier scan was not hidden';
  end if;
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000009') then
    raise exception 'A provider row of the minute of a copy on the carrier''s clock was hidden';
  end if;
end; $$;
-- A provider tells some scans on two clocks: its twin of a copy, in the copy's words, place and stage whole hours
-- from it, is the same scan, and so is its row, at any place, by the shift another of its copies shows. Each hides
-- the provider's other rows of its minute. A twin 90 minutes away, or a row by another shift, stays.
insert into public.packages(id, user_id, tracking_number, label, carrier, current_stage, carrier_data)
values ('97000000-0000-0000-0000-000000000011', '97000000-0000-0000-0000-000000000001', 'COPYTEST2', 'Clocks',
  'posti', 'in_transit', '{"routing": {"confirmed_carrier": "posti"}}');
insert into public.tracking_events(id, package_id, stage, description, location, provider_event_id, occurred_at, created_at, raw_data)
values
 ('97000000-0000-0000-0000-000000000012', '97000000-0000-0000-0000-000000000011', 'in_transit', 'Dispatched', 'Origin',
  'posti:dispatched', '2026-10-08T00:12:00Z', '2026-10-09T00:00:00Z', '{}'),
 ('97000000-0000-0000-0000-000000000013', '97000000-0000-0000-0000-000000000011', 'in_transit', 'Ready to leave', 'Airport',
  'posti:ready', '2026-10-08T14:18:00Z', '2026-10-09T00:00:00Z', '{}'),
 ('97000000-0000-0000-0000-000000000014', '97000000-0000-0000-0000-000000000011', 'in_transit', 'Left the country', 'Airport',
  'posti:left', '2026-10-08T23:28:00Z', '2026-10-09T00:00:00Z', '{}'),
 ('97000000-0000-0000-0000-000000000015', '97000000-0000-0000-0000-000000000011', 'accepted', 'Posting', 'Office 1',
  'unknown:posting', '2026-10-08T00:12:00Z', '2026-10-09T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000016', '97000000-0000-0000-0000-000000000011', 'accepted', 'Posting', 'Office 1',
  'unknown:posting-twin', '2026-10-07T21:12:00Z', '2026-10-09T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000017', '97000000-0000-0000-0000-000000000011', 'accepted', 'Item posted', 'Country',
  'unknown:posted', '2026-10-07T21:12:00Z', '2026-10-09T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000018', '97000000-0000-0000-0000-000000000011', 'accepted', 'Posting', 'Office 1',
  'unknown:posting-later', '2026-10-07T22:42:00Z', '2026-10-09T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000019', '97000000-0000-0000-0000-000000000011', 'in_transit', 'In transfer', 'Origin country',
  'unknown:transfer', '2026-10-08T20:28:00Z', '2026-10-09T00:00:00Z', '{"universal_provider": "17TRACK"}'),
 ('97000000-0000-0000-0000-000000000020', '97000000-0000-0000-0000-000000000011', 'registered', 'Received at export', 'Origin country',
  'unknown:export', '2026-10-08T11:18:00Z', '2026-10-09T00:00:00Z', '{"universal_provider": "17TRACK"}'),
 ('97000000-0000-0000-0000-000000000021', '97000000-0000-0000-0000-000000000011', 'registered', 'Received at export', 'Other country',
  'unknown:export-elsewhere', '2026-10-07T21:12:00Z', '2026-10-09T00:00:00Z', '{"universal_provider": "17TRACK"}'),
 ('97000000-0000-0000-0000-000000000022', '97000000-0000-0000-0000-000000000011', 'registered', 'Received at export', 'Origin country',
  'unknown:export-shift', '2026-10-08T12:18:00Z', '2026-10-09T00:00:00Z', '{"universal_provider": "17TRACK"}');
do $$ begin
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000015')
    or not public.hidden_provider_copy('97000000-0000-0000-0000-000000000019') then
    raise exception 'A provider copy of a carrier scan was not hidden';
  end if;
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000016') then
    raise exception 'A provider''s twin of its copy, whole hours away, was not hidden';
  end if;
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000017') then
    raise exception 'A provider''s row of the minute of its twin was not hidden';
  end if;
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000020') then
    raise exception 'A provider row at the shift of its copy at the same place was not hidden';
  end if;
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000018') then
    raise exception 'A provider row 90 minutes from its copy was hidden as a twin';
  end if;
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000021') then
    raise exception 'A provider row at another place, at the shift of its copy, was not hidden';
  end if;
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000022') then
    raise exception 'A provider row at another shift than its copy at the same place was hidden';
  end if;
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000012') then
    raise exception 'The carrier''s own scan counted as a provider copy';
  end if;
end; $$;
-- A copy whole hours from a carrier scan to its second, when that second is not a minute's first, is hidden in
-- any stage and words; a row three hours away at another second, or hours from a scan on a minute's first second,
-- stays. For a scan dated by its day: a copy at that scan's second in some zone, at its wall clock read as UTC in
-- its stage, or in its words with no stage, is hidden; a notice three seconds off, another stage at the wall
-- clock, or a time the wall clock (with no seconds) does not show, stays. A provider's row with no place is read
-- by the shift its copy with no place shows; another shift stays.
insert into public.packages(id, user_id, tracking_number, label, carrier, current_stage, carrier_data)
values ('97000000-0000-0000-0000-000000000031', '97000000-0000-0000-0000-000000000001', 'COPYTEST3', 'Seconds',
  'posti', 'in_transit', '{"routing": {"confirmed_carrier": "posti"}}'),
 ('97000000-0000-0000-0000-000000000041', '97000000-0000-0000-0000-000000000001', 'COPYTEST4', 'Wall clocks',
  'posti', 'in_transit', '{"routing": {"confirmed_carrier": "posti"}}'),
 ('97000000-0000-0000-0000-000000000051', '97000000-0000-0000-0000-000000000001', 'COPYTEST5', 'No place',
  'posti', 'customs', '{"routing": {"confirmed_carrier": "posti"}}');
insert into public.tracking_events(id, package_id, stage, description, location, provider_event_id, occurred_at, created_at, raw_data)
values
 ('97000000-0000-0000-0000-000000000032', '97000000-0000-0000-0000-000000000031', 'accepted', 'Arrived at origin facility.',
  'NRT', 'posti:arrived', '2026-10-21T11:46:56Z', '2026-10-23T00:00:00Z', '{}'),
 ('97000000-0000-0000-0000-000000000033', '97000000-0000-0000-0000-000000000031', 'customs', 'Customs',
  null, 'posti:customs', '2026-10-22T15:00:00Z', '2026-10-23T00:00:00Z', '{}'),
 ('97000000-0000-0000-0000-000000000034', '97000000-0000-0000-0000-000000000031', 'in_transit', 'Arrived at origin facility. Arrived',
  'Narita', 'unknown:arrived', '2026-10-21T12:46:56Z', '2026-10-23T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000035', '97000000-0000-0000-0000-000000000031', 'in_transit', 'Notice',
  null, 'unknown:notice', '2026-10-21T14:46:13Z', '2026-10-23T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000036', '97000000-0000-0000-0000-000000000031', 'in_transit', 'Out for delivery',
  null, 'unknown:round', '2026-10-22T17:00:00Z', '2026-10-23T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000042', '97000000-0000-0000-0000-000000000041', 'registered', 'Created',
  null, 'posti:created', '2026-10-07T00:00:00Z', '2026-10-23T00:00:00Z', '{"local_time": "2026-10-07T12:09:07"}'),
 ('97000000-0000-0000-0000-000000000043', '97000000-0000-0000-0000-000000000041', 'in_transit', 'In transit to the airport',
  null, 'posti:airport', '2026-10-14T00:00:00Z', '2026-10-23T00:00:00Z', '{"local_time": "2026-10-14T11:30:00"}'),
 ('97000000-0000-0000-0000-000000000044', '97000000-0000-0000-0000-000000000041', 'registered', 'Pendiente de recoger',
  null, 'posti:pending', '2026-10-13T00:00:00Z', '2026-10-23T00:00:00Z', '{"local_time": "2026-10-13T15:14:00"}'),
 ('97000000-0000-0000-0000-000000000045', '97000000-0000-0000-0000-000000000041', 'accepted', 'Shipment registered',
  null, 'unknown:registered', '2026-10-07T11:09:07Z', '2026-10-23T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000046', '97000000-0000-0000-0000-000000000041', 'in_transit', 'On the way to the airport',
  null, 'unknown:airport', '2026-10-14T11:30:00Z', '2026-10-23T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000047', '97000000-0000-0000-0000-000000000041', 'pending', 'Pendiente  de recoger',
  null, 'unknown:pending', '2026-10-13T13:14:00Z', '2026-10-23T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000048', '97000000-0000-0000-0000-000000000041', 'registered', 'We are expecting your parcel',
  null, 'unknown:expecting', '2026-10-07T12:09:04Z', '2026-10-23T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000049', '97000000-0000-0000-0000-000000000041', 'customs', 'Customs',
  null, 'unknown:customs', '2026-10-14T11:30:00Z', '2026-10-23T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000050', '97000000-0000-0000-0000-000000000041', 'in_transit', 'Received by sorting center',
  null, 'unknown:sorting', '2026-10-14T12:30:16Z', '2026-10-23T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000052', '97000000-0000-0000-0000-000000000051', 'customs', 'Customs Clearance',
  'IT', 'posti:clearance', '2026-10-21T07:55:00Z', '2026-10-23T00:00:00Z', '{}'),
 ('97000000-0000-0000-0000-000000000053', '97000000-0000-0000-0000-000000000051', 'in_transit', 'B2C ClearanceStatus4',
  'NL', 'posti:cleared', '2026-10-22T10:42:00Z', '2026-10-23T00:00:00Z', '{}'),
 ('97000000-0000-0000-0000-000000000054', '97000000-0000-0000-0000-000000000051', 'customs', 'Customs Import submitted',
  null, 'unknown:submitted', '2026-10-21T08:55:00Z', '2026-10-23T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000055', '97000000-0000-0000-0000-000000000051', 'customs', 'Customs Import cleared',
  null, 'unknown:cleared', '2026-10-22T11:42:00Z', '2026-10-23T00:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000056', '97000000-0000-0000-0000-000000000051', 'customs', 'Customs Import cleared',
  null, 'unknown:cleared-later', '2026-10-22T12:42:00Z', '2026-10-23T00:00:00Z', '{"universal_provider": "ParcelsApp"}');
do $$ begin
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000034') then
    raise exception 'A copy whole hours from a carrier scan to its second, in another stage, was not hidden';
  end if;
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000035')
    or public.hidden_provider_copy('97000000-0000-0000-0000-000000000036') then
    raise exception 'A provider row hours from a carrier scan with no second in common was hidden';
  end if;
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000045')
    or not public.hidden_provider_copy('97000000-0000-0000-0000-000000000046')
    or not public.hidden_provider_copy('97000000-0000-0000-0000-000000000047') then
    raise exception 'A copy of a day-dated scan at its second, at its wall clock or in its words was not hidden';
  end if;
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000048')
    or public.hidden_provider_copy('97000000-0000-0000-0000-000000000049')
    or public.hidden_provider_copy('97000000-0000-0000-0000-000000000050') then
    raise exception 'A provider step beside a day-dated scan was hidden';
  end if;
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000054')
    or not public.hidden_provider_copy('97000000-0000-0000-0000-000000000055') then
    raise exception 'A provider row with no place at the shift of its copy with no place was not hidden';
  end if;
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000056') then
    raise exception 'A provider row with no place at another shift was hidden';
  end if;
end; $$;
-- A provider's row within an hour of a carrier scan, in its stage and words, alone or followed by the row's place,
-- is a copy; one in other words, another stage or over an hour away stays.
insert into public.packages(id, user_id, tracking_number, label, carrier, current_stage, carrier_data)
values ('97000000-0000-0000-0000-000000000061', '97000000-0000-0000-0000-000000000001', 'COPYTEST6', 'Words',
  'posti', 'in_transit', '{"routing": {"confirmed_carrier": "posti"}}');
insert into public.tracking_events(id, package_id, stage, description, location, provider_event_id, occurred_at, created_at, raw_data)
values
 ('97000000-0000-0000-0000-000000000062', '97000000-0000-0000-0000-000000000061', 'in_transit', 'Departed  facility',
  'NORTH HUB', 'posti:departed', '2026-10-23T06:07:00Z', '2026-10-23T06:00:00Z', '{}'),
 ('97000000-0000-0000-0000-000000000063', '97000000-0000-0000-0000-000000000061', 'in_transit', 'Departed Facility, North Hub',
  'North Hub', 'unknown:first-time', '2026-10-23T05:39:00Z', '2026-10-27T07:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000064', '97000000-0000-0000-0000-000000000061', 'in_transit', 'departed facility',
  null, 'unknown:later', '2026-10-23T07:00:00Z', '2026-10-27T07:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000065', '97000000-0000-0000-0000-000000000061', 'in_transit', 'Departed facility early',
  null, 'unknown:other-words', '2026-10-23T05:40:00Z', '2026-10-27T07:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000066', '97000000-0000-0000-0000-000000000061', 'customs', 'Departed facility',
  'NORTH HUB', 'unknown:other-stage', '2026-10-23T05:41:00Z', '2026-10-27T07:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000067', '97000000-0000-0000-0000-000000000061', 'in_transit', 'Departed facility',
  'NORTH HUB', 'unknown:too-late', '2026-10-23T07:08:00Z', '2026-10-27T07:00:00Z', '{"universal_provider": "ParcelsApp"}');
do $$ begin
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000063')
    or not public.hidden_provider_copy('97000000-0000-0000-0000-000000000064') then
    raise exception 'A copy within an hour in the carrier scan''s words was not hidden';
  end if;
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000065')
    or public.hidden_provider_copy('97000000-0000-0000-0000-000000000066')
    or public.hidden_provider_copy('97000000-0000-0000-0000-000000000067') then
    raise exception 'A provider row in other words, another stage or over an hour away was hidden';
  end if;
end; $$;
-- A provider's announcement within an hour of the carrier's is a copy in any words; one over an hour away, or
-- another step in other words, stays.
insert into public.packages(id, user_id, tracking_number, label, carrier, current_stage, carrier_data)
values ('97000000-0000-0000-0000-000000000071', '97000000-0000-0000-0000-000000000001', 'COPYTEST7', 'Announcement',
  'posti', 'in_transit', '{"routing": {"confirmed_carrier": "posti"}}');
insert into public.tracking_events(id, package_id, stage, description, location, provider_event_id, occurred_at, created_at, raw_data)
values
 ('97000000-0000-0000-0000-000000000072', '97000000-0000-0000-0000-000000000071', 'registered', 'Votre envoi sera bientôt transmis',
  null, 'posti:announced', '2026-10-26T12:44:00Z', '2026-10-26T13:00:00Z', '{}'),
 ('97000000-0000-0000-0000-000000000073', '97000000-0000-0000-0000-000000000071', 'in_transit', 'Trip arrived',
  null, 'posti:arrived', '2026-10-27T09:10:00Z', '2026-10-27T09:30:00Z', '{}'),
 ('97000000-0000-0000-0000-000000000074', '97000000-0000-0000-0000-000000000071', 'registered', 'Registered',
  null, 'unknown:announced', '2026-10-26T12:10:00Z', '2026-10-27T07:00:00Z', '{"universal_provider": "17TRACK"}'),
 ('97000000-0000-0000-0000-000000000075', '97000000-0000-0000-0000-000000000071', 'registered', 'Registered',
  null, 'unknown:too-early', '2026-10-26T11:43:00Z', '2026-10-27T07:00:00Z', '{"universal_provider": "Ship24"}'),
 ('97000000-0000-0000-0000-000000000076', '97000000-0000-0000-0000-000000000071', 'in_transit', 'Bag added to trip',
  null, 'unknown:other-step', '2026-10-27T08:51:00Z', '2026-10-27T09:30:00Z', '{"universal_provider": "ParcelsApp"}');
do $$ begin
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000074') then
    raise exception 'A provider''s announcement within an hour of the carrier''s, in other words, was not hidden';
  end if;
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000075')
    or public.hidden_provider_copy('97000000-0000-0000-0000-000000000076') then
    raise exception 'An announcement over an hour away, or another step in other words, was hidden';
  end if;
end; $$;
-- A provider's row in a carrier scan's stage and words, alone or followed by the row's place, at the scan's place
-- (without its postcode) up to three hours before it is a copy; one over three hours before it, after it, at another
-- place or with none stays.
insert into public.packages(id, user_id, tracking_number, label, carrier, current_stage, carrier_data)
values ('97000000-0000-0000-0000-000000000081', '97000000-0000-0000-0000-000000000001', 'COPYTEST8', 'Moved on',
  'posti', 'in_transit', '{"routing": {"confirmed_carrier": "posti"}}');
insert into public.tracking_events(id, package_id, stage, description, location, provider_event_id, occurred_at, created_at, raw_data)
values
 ('97000000-0000-0000-0000-000000000082', '97000000-0000-0000-0000-000000000081', 'in_transit', 'Departed facility',
  'NORTH HUB 00000', 'posti:departed', '2026-10-23T14:15:00Z', '2026-10-23T14:30:00Z', '{}'),
 ('97000000-0000-0000-0000-000000000083', '97000000-0000-0000-0000-000000000081', 'in_transit', 'Departed Facility, North Hub',
  'North Hub', 'unknown:first-time', '2026-10-23T11:24:00Z', '2026-10-27T07:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000084', '97000000-0000-0000-0000-000000000081', 'in_transit', 'Departed facility',
  'NORTH HUB 00000', 'unknown:same-words', '2026-10-23T13:13:00Z', '2026-10-27T07:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000085', '97000000-0000-0000-0000-000000000081', 'in_transit', 'Departed facility',
  'NORTH HUB 00000', 'unknown:too-early', '2026-10-23T11:14:00Z', '2026-10-27T07:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000086', '97000000-0000-0000-0000-000000000081', 'in_transit', 'Departed facility',
  'NORTH HUB 00000', 'unknown:too-late', '2026-10-23T15:16:00Z', '2026-10-27T07:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000087', '97000000-0000-0000-0000-000000000081', 'in_transit', 'Departed Facility, North Hubs',
  'North Hubs', 'unknown:other-place', '2026-10-23T12:10:00Z', '2026-10-27T07:00:00Z', '{"universal_provider": "ParcelsApp"}'),
 ('97000000-0000-0000-0000-000000000088', '97000000-0000-0000-0000-000000000081', 'in_transit', 'Departed facility',
  null, 'unknown:no-place', '2026-10-23T12:20:00Z', '2026-10-27T07:00:00Z', '{"universal_provider": "ParcelsApp"}');
do $$ begin
  if not public.hidden_provider_copy('97000000-0000-0000-0000-000000000083')
    or not public.hidden_provider_copy('97000000-0000-0000-0000-000000000084') then
    raise exception 'A copy in the carrier scan''s words at its place up to three hours before it was not hidden';
  end if;
  if public.hidden_provider_copy('97000000-0000-0000-0000-000000000085')
    or public.hidden_provider_copy('97000000-0000-0000-0000-000000000086')
    or public.hidden_provider_copy('97000000-0000-0000-0000-000000000087')
    or public.hidden_provider_copy('97000000-0000-0000-0000-000000000088') then
    raise exception 'A provider row over three hours before the scan, after it, at another place or with none was hidden';
  end if;
end; $$;
reset role;
rollback;
