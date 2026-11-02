\set ON_ERROR_STOP on
begin;

do $$
declare
  parcel public.packages;
  correction jsonb;
  scans jsonb := jsonb_build_array(jsonb_build_object('provider_event_id', 'unknown:journey-result',
    'stage', 'in_transit', 'description', 'In transit', 'occurred_at', now()));
begin
  insert into public.packages(user_id, tracking_number, carrier)
  values ('10000000-0000-0000-0000-000000000001', 'MLXX000000001R', 'unknown') returning * into parcel;
  correction := jsonb_build_object('carrier', 'mailamericas', 'tracking_url', null, 'dpd_postcode', null,
    'current_stage', 'in_transit', 'sync_status', 'ok', 'carrier_data', jsonb_build_object(
      'tracking_provider', 'ParcelsApp', 'discovered_carrier', 'mailamericas',
      'reported_carriers', jsonb_build_array('MailAmericas', 'Cainiao'),
      'routing', jsonb_build_object('version', 1, 'configured_carrier', 'mailamericas', 'discovered_carrier', 'mailamericas',
        'preferred_number', parcel.tracking_number, 'preferred_provider', 'ParcelsApp', 'last_event_at', now())));
  begin
    perform public.apply_tracking_sync(parcel.id, parcel.tracking_generation,
      jsonb_set(correction, '{carrier_data,reported_carriers}', '["A","B","C","D","E"]'), scans);
    raise exception 'A provider journey of more than four carriers was accepted';
  exception when sqlstate '22023' then null;
  end;
  correction := jsonb_set(correction, '{carrier_data,carrier_answered}', 'true');
  begin
    perform public.apply_tracking_sync(parcel.id, parcel.tracking_generation,
      jsonb_set(correction, '{carrier_data,reported_carriers}', '["A","B","C","D","E"]'), scans);
    raise exception 'An answered carrier was filed from an unbounded provider report';
  exception when sqlstate '22023' then null;
  end;
  begin
    perform public.apply_tracking_sync(parcel.id, parcel.tracking_generation,
      jsonb_set(correction, '{carrier_data,reported_carriers}', '["MailAmericas",null]'), scans);
    raise exception 'An answered carrier was filed from a malformed provider report';
  exception when sqlstate '22023' then null;
  end;
  begin
    -- The sync writer names the carrier that answered as the one routing settled on.
    perform public.apply_tracking_sync(parcel.id, parcel.tracking_generation,
      correction #- '{carrier_data,discovered_carrier}' #- '{carrier_data,routing,discovered_carrier}', scans);
    raise exception 'An answered carrier was filed without being the one routing settled on';
  exception when sqlstate '22023' then null;
  end;
  begin
    perform public.apply_tracking_sync(parcel.id, parcel.tracking_generation, correction);
    raise exception 'An answered carrier was filed without dated movement';
  exception when sqlstate '22023' then null;
  end;
  if not public.apply_tracking_sync(parcel.id, parcel.tracking_generation, correction, scans) then
    raise exception 'The answered carrier of a provider journey was not saved';
  end if;
  if not exists (select 1 from public.packages where id = parcel.id and carrier = 'mailamericas'
      and carrier_data#>>'{routing,confirmed_carrier}' is null
      and not (carrier_data ? 'auto_changed_from')) then
    raise exception 'The answered carrier claimed direct confirmation';
  end if;
end;
$$;

-- A provider's older copy names the carrier: the parcel is filed under it and keeps the saved
-- summary, whose movement is the stored scan at the watermark.
do $$
declare
  parcel public.packages;
  correction jsonb;
begin
  insert into public.packages(user_id, tracking_number, carrier, current_stage, carrier_data)
  values ('10000000-0000-0000-0000-000000000001', 'CTTY0000000001CN', 'unknown', 'in_transit',
    jsonb_build_object('tracking_provider', 'ParcelsApp', 'reported_carriers', jsonb_build_array('CTTY')))
  returning * into parcel;
  insert into public.tracking_events(package_id, provider_event_id, stage, description, occurred_at)
  values (parcel.id, 'unknown:saved-scan', 'in_transit', 'Arrived at the hub', '2026-09-10T11:30:00Z');
  correction := jsonb_build_object('carrier', 'cttyex', 'tracking_url', null, 'dpd_postcode', null,
    'sync_status', 'ok', 'carrier_data', jsonb_build_object(
      'tracking_provider', 'ParcelsApp', 'discovered_carrier', 'cttyex', 'reported_carriers', jsonb_build_array('CTTY'),
      'routing', jsonb_build_object('version', 1, 'configured_carrier', 'cttyex', 'discovered_carrier', 'cttyex',
        'preferred_number', parcel.tracking_number, 'last_event_at', '2026-09-10T11:30:00.000Z')));
  begin
    perform public.apply_tracking_sync(parcel.id, parcel.tracking_generation,
      jsonb_set(correction, '{carrier_data,routing,last_event_at}', '"2026-09-10T11:00:00.000Z"'));
    raise exception 'Provider identity without a stored scan at its watermark was accepted';
  exception when sqlstate '22023' then null;
  end;
  if not public.apply_tracking_sync(parcel.id, parcel.tracking_generation, correction) then
    raise exception 'Provider identity on an older copy was not saved';
  end if;
  if not exists (select 1 from public.packages where id = parcel.id and carrier = 'cttyex' and current_stage = 'in_transit') then
    raise exception 'Provider identity on an older copy changed the saved summary';
  end if;
end;
$$;

-- A carrier that answers for an unknown parcel and hands it to its delivery carrier in the
-- same lookup files the parcel, which keeps the hand-off it reported.
do $$
declare
  parcel public.packages;
  correction jsonb;
  scans jsonb := jsonb_build_array(jsonb_build_object('provider_event_id', 'gofo:hub',
    'stage', 'in_transit', 'description', 'Arrived at the hub', 'occurred_at', now()));
begin
  insert into public.packages(user_id, tracking_number, carrier, current_stage)
  values ('10000000-0000-0000-0000-000000000001', 'QH999999999980FS', 'unknown', 'in_transit') returning * into parcel;
  correction := jsonb_build_object('carrier', 'dhlink', 'tracking_url', null, 'dpd_postcode', null,
    'current_stage', 'in_transit', 'sync_status', 'ok', 'carrier_data', jsonb_build_object(
      'original_carrier', 'dhlink', 'original_tracking_number', parcel.tracking_number,
      'active_tracking_carrier', 'gofo', 'active_tracking_number', 'GFUS01000000000001',
      'auto_changed_from', 'unknown', 'auto_changed_to', 'dhlink',
      'routing', jsonb_build_object('version', 1, 'configured_carrier', 'dhlink', 'preferred_number', parcel.tracking_number,
        'confirmed_carrier', 'dhlink', 'confirmed_number', parcel.tracking_number)));
  begin
    perform public.apply_tracking_sync(parcel.id, parcel.tracking_generation,
      jsonb_set(correction, '{carrier_data,original_carrier}', '"gofo"'), scans);
    raise exception 'A correction naming another carrier as the origin was accepted';
  exception when sqlstate '22023' then null;
  end;
  begin
    perform public.apply_tracking_sync(parcel.id, parcel.tracking_generation,
      jsonb_set(correction, '{carrier_data,original_tracking_number}', '"QH000000000002FS"'), scans);
    raise exception 'A correction naming the origin of another number was accepted';
  exception when sqlstate '22023' then null;
  end;
  begin
    -- A provider's word alone names no hand-off.
    perform public.apply_tracking_sync(parcel.id, parcel.tracking_generation, jsonb_build_object(
      'carrier', 'dhlink', 'tracking_url', null, 'dpd_postcode', null, 'sync_status', 'ok', 'carrier_data', jsonb_build_object(
        'original_carrier', 'dhlink', 'original_tracking_number', parcel.tracking_number,
        'tracking_provider', 'ParcelsApp', 'discovered_carrier', 'dhlink', 'reported_carriers', jsonb_build_array('DHLink'),
        'routing', jsonb_build_object('version', 1, 'configured_carrier', 'dhlink', 'discovered_carrier', 'dhlink',
          'preferred_number', parcel.tracking_number))), scans);
    raise exception 'A provider identity naming a hand-off was accepted';
  exception when sqlstate '22023' then null;
  end;
  if not public.apply_tracking_sync(parcel.id, parcel.tracking_generation, correction, scans) then
    raise exception 'The carrier that answered and handed the parcel on was not saved';
  end if;
  if not exists (select 1 from public.packages where id = parcel.id and carrier = 'dhlink'
      and carrier_data->>'original_carrier' = 'dhlink' and carrier_data->>'active_tracking_carrier' = 'gofo'
      and carrier_data->>'auto_changed_from' = 'unknown') then
    raise exception 'The handed-on parcel lost its hand-off or its correction notice';
  end if;
end;
$$;
rollback;
