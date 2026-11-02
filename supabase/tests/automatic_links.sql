\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values ('10000000-0000-0000-0000-000000000002', 'auto-link-test@example.test') on conflict (id) do nothing;
set local role service_role;
insert into packages (id,user_id,tracking_number,label,carrier,current_stage,sync_status,carrier_data) values
 ('82000000-0000-0000-0000-000000000001','10000000-0000-0000-0000-000000000001','123459999981','Perfume','gls-de','in_transit','ok','{}'),
 ('82000000-0000-0000-0000-000000000002','10000000-0000-0000-0000-000000000001','990000000000000002','Surprise','swiss-post','in_transit','ok','{"international_tracking_number":"12345678901"}'),
 ('82000000-0000-0000-0000-000000000003','10000000-0000-0000-0000-000000000001','223456789011','Same prefix','gls-de','in_transit','ok','{}'),
 ('82000000-0000-0000-0000-000000000004','10000000-0000-0000-0000-000000000001','990000000000000004','Unrelated','swiss-post','in_transit','ok','{"international_tracking_number":"22345678901"}'),
 ('82000000-0000-0000-0000-000000000005','10000000-0000-0000-0000-000000000002','32345678901','Other owner','gls-de','in_transit','ok','{}'),
 ('82000000-0000-0000-0000-000000000006','10000000-0000-0000-0000-000000000001','990000000000000006','Other owner local','swiss-post','in_transit','ok','{"international_tracking_number":"32345678901"}'),
 ('82000000-0000-0000-0000-000000000007','10000000-0000-0000-0000-000000000001','423456789011','Ambiguous A','gls-de','in_transit','ok','{"canonical_tracking_number":"42345678901"}'),
 ('82000000-0000-0000-0000-000000000008','10000000-0000-0000-0000-000000000001','423456789012','Ambiguous B','gls-ch','in_transit','ok','{"canonical_tracking_number":"42345678901"}'),
 ('82000000-0000-0000-0000-000000000009','10000000-0000-0000-0000-000000000001','990000000000000009','Ambiguous local','swiss-post','in_transit','ok','{"international_tracking_number":"42345678901"}');
insert into sync_jobs (id,user_id,package_id,kind,dedupe_key) values
 ('82000000-0000-0000-0000-000000000099','10000000-0000-0000-0000-000000000001','82000000-0000-0000-0000-000000000001','package','auto-link-test');
insert into tracking_events (package_id,stage,description,provider_event_id) values
 ('82000000-0000-0000-0000-000000000001','accepted','GLS accepted','gls-de:auto-link'),
 ('82000000-0000-0000-0000-000000000002','in_transit','Swiss Post arrival','swiss-post:auto-link');

do $$
declare old_generation uuid;
begin
  if has_function_privilege('authenticated','public.auto_link_package_tracking(uuid)','execute') then
    raise exception 'Automatic linking must be service-only';
  end if;
  if public.auto_link_package_tracking('10000000-0000-0000-0000-000000000001') <> 0 then
    raise exception 'Must not guess a canonical number, cross owners or resolve an ambiguous match';
  end if;
  -- Swiss Post arrived first. GLS later supplies the actual canonical identifier.
  update packages set carrier_data='{"canonical_tracking_number":"12345678901"}' where id='82000000-0000-0000-0000-000000000001';
  update packages set current_stage='registered' where id='82000000-0000-0000-0000-000000000002';
  if public.auto_link_package_tracking('10000000-0000-0000-0000-000000000001') <> 0 then
    raise exception 'Do not replace active international progress with local pre-advice';
  end if;
  update packages set current_stage='out_for_delivery',expected_delivery='2026-09-10' where id='82000000-0000-0000-0000-000000000002';
  select tracking_generation into old_generation from packages where id='82000000-0000-0000-0000-000000000002';
  if public.auto_link_package_tracking('10000000-0000-0000-0000-000000000002') <> 0 then
    raise exception 'Account-scoped reconciliation affected another owner';
  end if;
  if public.auto_link_package_tracking('10000000-0000-0000-0000-000000000001') <> 1 then
    raise exception 'Expected one automatic merge';
  end if;
  if not exists (select 1 from packages where id='82000000-0000-0000-0000-000000000002' and label='Perfume / Surprise' and current_stage='out_for_delivery' and expected_delivery='2026-09-10' and carrier_data->>'original_tracking_number'='123459999981') then
    raise exception 'Name, progress, ETA or original tracking identity lost';
  end if;
  if (select count(*) from tracking_events where package_id='82000000-0000-0000-0000-000000000002' and provider_event_id like '%:auto-link') <> 2 then
    raise exception 'Both histories must survive';
  end if;
  if not exists(select 1 from sync_jobs where id='82000000-0000-0000-0000-000000000099' and package_id='82000000-0000-0000-0000-000000000002') then
    raise exception 'Queued refresh disappeared during merge';
  end if;
  if public.apply_tracking_sync('82000000-0000-0000-0000-000000000002', old_generation, '{"carrier_data":{}}') then
    raise exception 'Stale refresh overwrote merged tracking';
  end if;
  if public.auto_link_package_tracking('10000000-0000-0000-0000-000000000001') <> 0 then
    raise exception 'Repeated reconciliation must not duplicate the name';
  end if;
end;
$$;
-- A standalone handoff may happen before the separate delivery entry is added.
insert into packages (id,user_id,tracking_number,label,carrier,current_stage,sync_status,carrier_data) values
 ('83000000-0000-0000-0000-000000000001','10000000-0000-0000-0000-000000000001','523456789011','GLS order','gls-de','in_transit','ok','{"original_carrier":"gls-de","original_tracking_number":"523456789011","original_canonical_tracking_number":"52345678901","canonical_tracking_number":"990000000000000011","active_tracking_carrier":"swiss-post","active_tracking_number":"990000000000000011"}'),
 ('83000000-0000-0000-0000-000000000002','10000000-0000-0000-0000-000000000001','990000000000000011','Post name','swiss-post','out_for_delivery','ok','{"international_tracking_number":"52345678901"}'),
 ('83000000-0000-0000-0000-000000000003','10000000-0000-0000-0000-000000000001','LX999999987NL','AliExpress order','aliexpress','in_transit','ok','{}'),
 ('83000000-0000-0000-0000-000000000004','10000000-0000-0000-0000-000000000001','990000000000000012','Postal order','swiss-post','in_transit','ok','{"international_tracking_number":"LX999999987NL"}'),
 ('83000000-0000-0000-0000-000000000005','10000000-0000-0000-0000-000000000001','SY99999999980','Partner order','sunyou','in_transit','ok','{"original_carrier":"sunyou","active_tracking_carrier":"swiss-post","active_tracking_number":"990000000000000013"}'),
 ('83000000-0000-0000-0000-000000000006','10000000-0000-0000-0000-000000000001','990000000000000013','Local order','swiss-post','in_transit','ok','{}'),
 -- Handed over under the international number; Swiss Post's later answers add its domestic one.
 ('83000000-0000-0000-0000-000000000007','10000000-0000-0000-0000-000000000001','LTN000000001N1','Refills','landmark-global','in_transit','ok','{"original_carrier":"landmark-global","original_tracking_number":"LTN000000001N1","original_canonical_tracking_number":"LTN000000001","canonical_tracking_number":"990000000000000014","international_tracking_number":"CC000000001BE","active_tracking_carrier":"swiss-post","active_tracking_number":"CC000000001BE"}'),
 ('83000000-0000-0000-0000-000000000008','10000000-0000-0000-0000-000000000001','990000000000000014','Unknown','swiss-post','in_transit','ok','{"canonical_tracking_number":"990000000000000014","international_tracking_number":"CC000000001BE"}');
do $$
begin
  if public.auto_link_package_tracking('10000000-0000-0000-0000-000000000001') <> 4 then
    raise exception 'Expected postal reference and already-switched parcels to merge';
  end if;
  if not exists(select 1 from packages where id='83000000-0000-0000-0000-000000000008'
    and label='Refills / Unknown' and carrier_data->>'original_carrier'='landmark-global'
    and carrier_data->>'original_tracking_number'='LTN000000001N1')
    or exists(select 1 from packages where id='83000000-0000-0000-0000-000000000007') then
    raise exception 'A parcel added under Swiss Post''s domestic number stayed apart from its handoff';
  end if;
  if not exists(select 1 from packages where id='83000000-0000-0000-0000-000000000002'
    and label='GLS order / Post name' and carrier_data->>'original_carrier'='gls-de'
    and carrier_data->>'original_tracking_number'='523456789011'
    and carrier_data->>'active_tracking_number'='990000000000000011') then
    raise exception 'Cross-number handoff identity was lost during merge';
  end if;
  if not exists(select 1 from packages where id='83000000-0000-0000-0000-000000000004'
    and label='AliExpress order / Postal order' and carrier_data->>'original_carrier'='aliexpress') then
    raise exception 'Postal partner merge lost the original name or carrier';
  end if;
  if public.auto_link_package_tracking('10000000-0000-0000-0000-000000000001') <> 0 then
    raise exception 'Already-merged handoff linked again';
  end if;
end;
$$;
-- Legs merge whatever their carriers, once a carrier names the other leg's number.
insert into packages (id,user_id,tracking_number,label,carrier,current_stage,sync_status,carrier_data) values
 ('84000000-0000-0000-0000-000000000001','10000000-0000-0000-0000-000000000001','XA000000001FR','Shoes','chronopost','in_transit','ok','{"delivery_carrier":"poste-italiane","delivery_tracking_number":"PI000000001"}'),
 ('84000000-0000-0000-0000-000000000002','10000000-0000-0000-0000-000000000001','PI000000001','Italian leg','poste-italiane','in_transit','ok','{}'),
 ('84000000-0000-0000-0000-000000000003','10000000-0000-0000-0000-000000000001','LTN000000002N1','Books','landmark-global','in_transit','ok','{}'),
 ('84000000-0000-0000-0000-000000000004','10000000-0000-0000-0000-000000000001','CC000000002BE','Belgian leg','bpost','in_transit','ok','{"international_tracking_number":"LTN000000002N1"}'),
 -- One carrier's two numbers for an item are no handoff.
 ('84000000-0000-0000-0000-000000000005','10000000-0000-0000-0000-000000000001','990000000000000021','Domestic','swiss-post','in_transit','ok','{"international_tracking_number":"RR000000003CH"}'),
 ('84000000-0000-0000-0000-000000000006','10000000-0000-0000-0000-000000000001','RR000000003CH','International','swiss-post','in_transit','ok','{"canonical_tracking_number":"990000000000000021"}'),
 -- Each names the other as its delivery partner: which leg came later is open.
 ('84000000-0000-0000-0000-000000000007','10000000-0000-0000-0000-000000000001','1Z0000000000000001','Either A','ups','in_transit','ok','{"delivery_carrier":"usps","delivery_tracking_number":"9200000000000000000001"}'),
 ('84000000-0000-0000-0000-000000000008','10000000-0000-0000-0000-000000000001','9200000000000000000001','Either B','usps','in_transit','ok','{"delivery_carrier":"ups","delivery_tracking_number":"1Z0000000000000001"}');
insert into tracking_events (id,package_id,stage,description,provider_event_id,created_at) values
 ('84100000-0000-0000-0000-000000000001','84000000-0000-0000-0000-000000000001','in_transit','Arrived in Italy','poste-italiane:arrival','2026-09-10T08:00:00Z'),
 ('84100000-0000-0000-0000-000000000002','84000000-0000-0000-0000-000000000002','in_transit','Arrived in Italy','poste-italiane:arrival','2026-09-10T09:00:00Z');
insert into push_subscriptions (id,user_id,endpoint,p256dh,auth,subscribed_at) values
 ('84200000-0000-0000-0000-000000000001','10000000-0000-0000-0000-000000000001','https://fcm.googleapis.com/fcm/send/auto-link','public-encryption-key','auth-secret','2026-09-01T00:00:00Z');
insert into push_deliveries (subscription_id,event_id) values
 ('84200000-0000-0000-0000-000000000001','84100000-0000-0000-0000-000000000001');
do $$
begin
  if public.auto_link_package_tracking('10000000-0000-0000-0000-000000000001') <> 2 then
    raise exception 'Expected the partner and international-number legs to merge, and nothing else';
  end if;
  if not exists(select 1 from packages where id='84000000-0000-0000-0000-000000000002'
    and label='Shoes / Italian leg' and carrier_data->>'original_carrier'='chronopost') then
    raise exception 'A delivery partner named by the earlier carrier stayed apart';
  end if;
  if not exists(select 1 from packages where id='84000000-0000-0000-0000-000000000004'
    and label='Books / Belgian leg' and carrier_data->>'original_carrier'='landmark-global') then
    raise exception 'A carrier naming the earlier number as international stayed apart';
  end if;
  if (select count(*) from packages where id in ('84000000-0000-0000-0000-000000000005','84000000-0000-0000-0000-000000000006',
    '84000000-0000-0000-0000-000000000007','84000000-0000-0000-0000-000000000008')) <> 4 then
    raise exception 'Merged one carrier''s numbers, or two legs that each name the other';
  end if;
  if not exists(select 1 from push_deliveries where event_id='84100000-0000-0000-0000-000000000002'
    and subscription_id='84200000-0000-0000-0000-000000000001') then
    raise exception 'The kept copy of a scan lost the receipt of the copy announced before the merge';
  end if;
end;
$$;
rollback;
