\set ON_ERROR_STOP on
begin;
truncate public.tracking_health_samples, public.tracking_health_incidents;
create function pg_temp.health_sample(subject text, healthy boolean, kind text default 'provider', package_id uuid default '10000000-0000-4000-8000-000000000001', attempt_id uuid default gen_random_uuid())
returns jsonb language sql as $$
  select public.record_tracking_health(attempt_id,package_id,jsonb_build_array(jsonb_build_object(
    'kind',kind,'subject',subject,'healthy',healthy,'details',jsonb_build_object('error_type','TransportError'))));
$$;
-- One check of a carrier: its direct step and the parcel's refresh.
create function pg_temp.direct_check(subject text, direct boolean, refresh boolean)
returns jsonb language sql as $$
  select public.record_tracking_health(gen_random_uuid(),'10000000-0000-4000-8000-000000000001',jsonb_build_array(
    jsonb_build_object('kind','direct','subject',subject,'healthy',direct,'details','{}'::jsonb),
    jsonb_build_object('kind','refresh','subject',subject,'healthy',refresh,'details','{}'::jsonb)));
$$;
create function pg_temp.direct_notice(result jsonb, subject text)
returns jsonb language sql as $$
  select n from jsonb_array_elements(result) n where n->>'kind'='direct' and n->>'subject'=subject;
$$;
-- A parcel's refresh that answered without a lookup, its number waiting for the owner's input.
create function pg_temp.input_required_refresh(subject text, package_id uuid)
returns jsonb language sql as $$
  select public.record_tracking_health(gen_random_uuid(),package_id,jsonb_build_array(jsonb_build_object(
    'kind','refresh','subject',subject,'healthy',true,'details',jsonb_build_object('category','input_required'))));
$$;
-- A parcel's refresh whose carrier repeated the answer without progress its previous check got.
create function pg_temp.repeated_refresh(subject text, package_id uuid)
returns jsonb language sql as $$
  select public.record_tracking_health(gen_random_uuid(),package_id,jsonb_build_array(jsonb_build_object(
    'kind','refresh','subject',subject,'healthy',true,'details',jsonb_build_object('category','repeated_answer'))));
$$;
create function pg_temp.answered_sample(subject text)
returns jsonb language sql as $$
  select public.record_tracking_health(gen_random_uuid(),null,jsonb_build_array(jsonb_build_object(
    'kind','provider','subject',subject,'healthy',true,'details',jsonb_build_object('error_type','NotFoundError','category','not_found'))));
$$;
do $$
declare result jsonb; sample_id uuid := gen_random_uuid(); notice_id uuid;
begin
  for i in 1..9 loop
    result := pg_temp.health_sample('test-provider',false);
    assert result='[]', 'A small sample must not alert';
  end loop;
  result := pg_temp.health_sample('test-provider',false,'provider',null,sample_id);
  assert jsonb_array_length(result)=1 and result->0->>'state'='open', 'Ten failing lookups must alert';
  assert (result->0->>'failures')::int=10;
  notice_id := (result->0->>'id')::uuid;
  assert pg_temp.health_sample('test-provider',false,'provider',null,sample_id)='[]', 'A duplicate must neither count nor send twice';
  assert (select count(*) from public.tracking_health_samples where subject='test-provider')=10;
  update public.tracking_health_incidents set lease_until=now()-interval '1 second' where subject='test-provider';
  result := pg_temp.health_sample('test-provider',false,'provider',null,sample_id);
  assert (result->0->>'id')::uuid=notice_id, 'Failed delivery must retain the same pending notification';
  perform public.ack_tracking_health(array[notice_id]);
  assert pg_temp.health_sample('test-provider',false)='[]', 'An active incident must be suppressed for six hours';
  assert pg_temp.health_sample('test-provider',true)='[]';
  assert pg_temp.health_sample('test-provider',true)='[]';
  assert pg_temp.health_sample('test-provider',true)='[]', 'Three successful probes while most lookups failed must not recover';
  update public.tracking_health_incidents set last_notified_at=now()-interval '7 hours' where subject='test-provider';
  assert pg_temp.health_sample('test-provider',true)='[]', 'A breached incident whose latest probes succeed must not remind';
  -- 11 failures: the 22nd lookup brings the rate down to half.
  for i in 1..6 loop
    assert pg_temp.health_sample('test-provider',true)='[]', 'Recovery must wait until the rate is back under its threshold';
  end loop;
  result := pg_temp.health_sample('test-provider',true);
  assert result->0->>'state'='recovered' and (result->0->>'failures')::int=11 and (result->0->>'attempts')::int=22,
    'Successful probes must recover once no more than half the lookups failed';
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);
  assert pg_temp.health_sample('test-provider',false)='[]', 'Historical errors must not cause an immediate re-open';
  update public.tracking_health_incidents set last_notified_at=now()-interval '7 hours' where subject='test-provider';
  result := pg_temp.health_sample('test-provider',false);
  assert result->0->>'state'='open';

  assert pg_temp.health_sample('test-carrier',false,'refresh')='[]';
  assert pg_temp.health_sample('test-carrier',false,'refresh')='[]';
  assert pg_temp.health_sample('test-carrier',false,'refresh','20000000-0000-4000-8000-000000000001')='[]', 'Failures on different parcels must not form a streak';
  result := pg_temp.health_sample('test-carrier',false,'refresh');
  assert result->0->>'consecutive_failures'='true', 'Same parcel failing three times must alert';
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);
  for i in 1..3 loop result := pg_temp.input_required_refresh('test-carrier','20000000-0000-4000-8000-000000000001'); end loop;
  assert result='[]', 'Parcels waiting for input must not clear a still-failing parcel';
  -- A parcel that stopped being checked, a one-off lookup leaving the schedule, holds nothing open.
  update public.tracking_health_samples set observed_at=now()-interval '2 hours'
    where subject='test-carrier' and package_id='10000000-0000-4000-8000-000000000001';
  result := pg_temp.input_required_refresh('test-carrier','20000000-0000-4000-8000-000000000001');
  assert result->0->>'state'='recovered', 'A streak that ended before the window must let the incident recover';

  -- One parcel's streak while the carrier answers another parcel, such as a number that trips its
  -- bot defences, is that parcel's own; a second parcel's streak makes it the carrier's.
  perform pg_temp.health_sample('streak-carrier',true,'refresh','20000000-0000-4000-8000-000000000001');
  for i in 1..3 loop result := pg_temp.health_sample('streak-carrier',false,'refresh'); end loop;
  assert result='[]', 'One parcel failing while another parcel answers must not alert';
  -- Below the rate's five failures an hour, so only the streaks can alert.
  update public.tracking_health_samples set observed_at=now()-interval '2 hours' where subject='streak-carrier' and not healthy;
  for i in 1..2 loop perform pg_temp.health_sample('streak-carrier',false,'refresh','20000000-0000-4000-8000-000000000002'); end loop;
  assert pg_temp.health_sample('streak-carrier',false,'refresh')='[]', 'A streak whose parcel stays alone must not alert';
  result := pg_temp.health_sample('streak-carrier',false,'refresh','20000000-0000-4000-8000-000000000002');
  assert result->0->>'state'='open' and result->0->>'consecutive_failures'='true' and (result->0->>'failures')::int=4,
    'Two parcels failing three times in a row must alert';
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);
  for i in 1..3 loop result := pg_temp.health_sample('streak-carrier',true,'refresh','20000000-0000-4000-8000-000000000001'); end loop;
  assert result='[]', 'Another parcel answering must not clear two still-failing parcels';

  -- A carrier repeating a thin answer for its only parcel recovers once three checks repeated it.
  for i in 1..3 loop result := pg_temp.health_sample('thin-carrier',false,'refresh'); end loop;
  assert result->0->>'state'='open', 'One parcel failing three times while no other answers must alert';
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);
  for i in 1..2 loop
    assert pg_temp.repeated_refresh('thin-carrier','10000000-0000-4000-8000-000000000001')='[]', 'Two repeated answers must not recover';
  end loop;
  result := pg_temp.repeated_refresh('thin-carrier','10000000-0000-4000-8000-000000000001');
  assert result->0->>'state'='recovered', 'Three repeated answers must recover the incident';
  -- Like missing input, another parcel's repeated answer does not answer for a failing parcel's streak.
  for i in 1..3 loop result := pg_temp.health_sample('thin-streak',false,'refresh'); end loop;
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);
  for i in 1..3 loop result := pg_temp.repeated_refresh('thin-streak','20000000-0000-4000-8000-000000000001'); end loop;
  assert result='[]', 'Another parcel repeating a thin answer must not clear a still-failing parcel';
  -- Nor does it dilute the outage rate.
  for i in 1..30 loop perform pg_temp.repeated_refresh('thin-rate',gen_random_uuid()); end loop;
  for i in 1..5 loop result := pg_temp.health_sample('thin-rate',false,'refresh',gen_random_uuid()); end loop;
  assert result->0->>'state'='open' and (result->0->>'attempts')::int=5, 'Repeated thin answers must not dilute the outage rate';

  for i in 1..10 loop result := pg_temp.health_sample('half-failing',i%2=0); end loop;
  assert result='[]', 'Exactly 50 percent must not alert';
  result := pg_temp.health_sample('half-failing',false);
  assert result->0->>'state'='open';

  for i in 1..5 loop result := pg_temp.health_sample('rate-carrier',false,'refresh',gen_random_uuid()); end loop;
  assert result->0->>'state'='open' and result->0->>'consecutive_failures'='false', 'Five failures across parcels must trigger the rate condition';

  -- A healthy not-found answer recovers an incident but never dilutes the outage rate.
  for i in 1..10 loop result := pg_temp.answered_sample('probe-carrier'); end loop;
  for i in 1..9 loop result := pg_temp.health_sample('probe-carrier',false); end loop;
  assert result='[]', 'Answered probes must not count toward the ten-lookup minimum';
  result := pg_temp.health_sample('probe-carrier',false);
  assert result->0->>'state'='open' and (result->0->>'attempts')::int=10, 'Ten real failures must alert despite healthy not-found answers';
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);
  assert pg_temp.answered_sample('probe-carrier')='[]';
  assert pg_temp.answered_sample('probe-carrier')='[]';
  assert pg_temp.answered_sample('probe-carrier')='[]', 'Answered probes must not recover while every real lookup failed';
  update public.tracking_health_samples set observed_at=now()-interval '25 hours'
    where subject='probe-carrier' and not healthy;
  result := pg_temp.answered_sample('probe-carrier');
  assert result->0->>'state'='recovered', 'Answered probes must recover an incident once its failures have aged out';
  -- A tier that is no longer probed cannot recover; its incident closes when the evidence expires.
  for i in 1..10 loop result := pg_temp.health_sample('retired-tier',false,'direct'); end loop;
  assert result->0->>'state'='open';
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);
  assert (select active from public.tracking_health_incidents where kind='direct' and subject='retired-tier');
  assert pg_temp.health_sample('other-tier',true)='[]';
  assert (select active from public.tracking_health_incidents where kind='direct' and subject='retired-tier'),
    'An incident with evidence inside the window must stay open';
  update public.tracking_health_samples set observed_at=now()-interval '25 hours' where subject='retired-tier';
  update public.tracking_health_incidents set last_notified_at=now()-interval '25 hours' where subject='retired-tier';
  result := pg_temp.health_sample('other-tier',true);
  assert jsonb_array_length(result)=1 and result->0->>'state'='closed' and result->0->>'kind'='direct'
    and result->0->>'subject'='retired-tier', 'An expired incident must send a closed notice';
  notice_id := (result->0->>'id')::uuid;
  assert pg_temp.health_sample('other-tier',true)='[]', 'A closed notice must not be sent twice within its lease';
  update public.tracking_health_incidents set lease_until=now()-interval '1 second' where subject='retired-tier';
  result := pg_temp.health_sample('other-tier',true);
  assert (result->0->>'id')::uuid=notice_id, 'A closed notice must be sent again until it is acknowledged';
  perform public.ack_tracking_health(array[notice_id]);
  assert (select not active and last_notified_at < now()-interval '24 hours'
    from public.tracking_health_incidents where kind='direct' and subject='retired-tier'),
    'An incident whose evidence expired must close, keeping its last notification time';
  -- A notice still waiting for delivery keeps its incident until it has been acknowledged.
  for i in 1..10 loop result := pg_temp.health_sample('unsent-tier',false,'direct'); end loop;
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);
  for i in 1..10 loop result := pg_temp.health_sample('unsent-tier',true,'direct'); end loop;
  assert result->0->>'state'='recovered';
  update public.tracking_health_samples set observed_at=now()-interval '25 hours' where subject='unsent-tier';
  assert pg_temp.health_sample('other-tier',true)='[]';
  assert (select active and pending is not null from public.tracking_health_incidents where kind='direct' and subject='unsent-tier'),
    'A pending recovery notice must survive the sweep';
  -- A direct step the carrier no longer runs closes once three lookups in a row answered after it.
  for i in 1..10 loop result := pg_temp.health_sample('bypassed-tier',false,'direct'); end loop;
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);
  update public.tracking_health_samples set observed_at=now()-interval '7 hours' where subject='bypassed-tier';
  for i in 1..3 loop
    assert pg_temp.health_sample('bypassed-tier',true)='[]', 'Fewer than three later lookups must keep a direct incident open';
  end loop;
  result := pg_temp.health_sample('bypassed-tier',true);
  assert jsonb_array_length(result)=1 and result->0->>'state'='closed' and result->0->>'reason'='bypassed'
    and result->0->>'kind'='direct', 'A bypassed direct step must send a closed notice';
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);
  assert (select not active from public.tracking_health_incidents where kind='direct' and subject='bypassed-tier');
  -- A failed lookup among the three keeps it open.
  for i in 1..10 loop result := pg_temp.health_sample('flaky-tier',false,'direct'); end loop;
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);
  update public.tracking_health_samples set observed_at=now()-interval '7 hours' where subject='flaky-tier';
  perform pg_temp.health_sample('flaky-tier',true);
  perform pg_temp.health_sample('flaky-tier',false);
  perform pg_temp.health_sample('flaky-tier',true);
  assert pg_temp.health_sample('flaky-tier',true)='[]';
  assert (select active and pending is null from public.tracking_health_incidents where kind='direct' and subject='flaky-tier'),
    'A failed lookup after the direct step must keep its incident open';
  -- So does a direct step paused for less than six hours, as DHL Express's is after a CAPTCHA.
  for i in 1..10 loop result := pg_temp.health_sample('paused-tier',false,'direct'); end loop;
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);
  update public.tracking_health_samples set observed_at=now()-interval '40 minutes' where subject='paused-tier';
  for i in 1..4 loop
    assert pg_temp.health_sample('paused-tier',true)='[]', 'A paused direct step must keep its incident open';
  end loop;
  assert (select active and pending is null from public.tracking_health_incidents where kind='direct' and subject='paused-tier'),
    'A paused direct step must keep its incident open';
  -- A direct step that still runs and fails beside a fallback that answers stays open.
  for i in 1..13 loop
    result := public.record_tracking_health(gen_random_uuid(),null,jsonb_build_array(
      jsonb_build_object('kind','direct','subject','rescued-tier','healthy',false,'details','{}'::jsonb),
      jsonb_build_object('kind','provider','subject','rescued-tier','healthy',true,'details','{}'::jsonb)));
    if i=10 then perform public.ack_tracking_health(array[(result->0->>'id')::uuid]); end if;
  end loop;
  assert (select active and pending is null from public.tracking_health_incidents where kind='direct' and subject='rescued-tier'),
    'A direct step that still fails must keep its incident open while a fallback answers';
  -- A direct step failing only in checks whose refresh answered through another step is rescued.
  for i in 1..10 loop result := pg_temp.direct_check('rescued-direct',false,true); end loop;
  assert pg_temp.direct_notice(result,'rescued-direct')->>'state'='open'
    and (pg_temp.direct_notice(result,'rescued-direct')->'rescued')::boolean,
    'A direct step every refresh survived must be rescued';
  perform public.ack_tracking_health(array[(pg_temp.direct_notice(result,'rescued-direct')->>'id')::uuid]);
  assert (select active and pending is null from public.tracking_health_incidents where kind='direct' and subject='rescued-direct'),
    'A rescued direct incident must stay open for the review';
  -- One failure that left its parcel without a fresh answer, now or earlier in the window, is not.
  for i in 1..9 loop perform pg_temp.direct_check('unrescued-now',false,true); end loop;
  result := pg_temp.direct_check('unrescued-now',false,false);
  assert pg_temp.direct_notice(result,'unrescued-now')->>'state'='open'
    and not (pg_temp.direct_notice(result,'unrescued-now')->'rescued')::boolean,
    'A direct failure whose refresh failed must not be rescued';
  perform public.ack_tracking_health(array[(pg_temp.direct_notice(result,'unrescued-now')->>'id')::uuid]);
  perform pg_temp.direct_check('unrescued-earlier',false,false);
  for i in 1..9 loop result := pg_temp.direct_check('unrescued-earlier',false,true); end loop;
  assert not (pg_temp.direct_notice(result,'unrescued-earlier')->'rescued')::boolean,
    'An earlier direct failure whose refresh failed must keep the incident from being rescued';
  perform public.ack_tracking_health(array[(pg_temp.direct_notice(result,'unrescued-earlier')->>'id')::uuid]);
  -- A tier still failing when traffic resumes opens a new incident at once.
  for i in 1..10 loop result := pg_temp.health_sample('retired-tier',false,'direct'); end loop;
  assert result->0->>'state'='open', 'A closed incident must reopen on fresh failures';
  assert not has_function_privilege('authenticated','public.record_tracking_health(uuid,uuid,jsonb)','execute');
  assert not has_table_privilege('anon','public.tracking_health_samples','select');
end;
$$;
-- Expected JD abstentions remain queryable but cannot mask or create actionable outages.
truncate public.tracking_health_samples, public.tracking_health_incidents;
create function pg_temp.jd_abstention(subject text default 'jd-logistics', kind text default 'provider')
returns jsonb language sql as $$
  select public.record_tracking_health(gen_random_uuid(),null,jsonb_build_array(jsonb_build_object(
    'kind',kind,'subject',subject,'healthy',false,'details',jsonb_build_object(
      'category','challenge','reason','verification_unreadable','error_type','ChallengeError','step','trawl'))));
$$;
do $$
declare result jsonb; notice_id uuid;
begin
  for i in 1..12 loop
    assert pg_temp.jd_abstention()='[]', 'Expected JD matcher abstentions must not open an outage';
  end loop;
  assert (select count(*) from public.tracking_health_samples where subject='jd-logistics' and not healthy)=12,
    'Abstentions must remain failed samples, never fabricated successes';
  assert (select sum(abstentions) from metrics.verification_abstentions)=12,
    'Grafana must be able to count persisted scheduled abstentions';
  assert has_table_privilege('metrics_reader','metrics.verification_abstentions','select');
  assert not has_table_privilege('metrics_reader','metrics.verification_abstentions','insert');
  assert not has_table_privilege('metrics_reader','public.tracking_health_samples','select');
  assert not has_table_privilege('anon','metrics.verification_abstentions','select');
  assert not has_table_privilege('authenticated','metrics.verification_abstentions','select');

  update public.tracking_health_incidents set active=true,
    pending=jsonb_build_object('id',gen_random_uuid(),'state','open'), lease_until=now()-interval '1 second'
    where kind='provider' and subject='jd-logistics';
  result := public.record_tracking_health(gen_random_uuid(),null,'[]');
  assert result->0->>'state'='closed' and result->0->>'reason'='verification_abstained',
    'An old abstention-only outage must close as a review signal, not recover';
  notice_id := (result->0->>'id')::uuid;
  perform public.ack_tracking_health(array[notice_id]);
  assert not (select active from public.tracking_health_incidents where kind='provider' and subject='jd-logistics');
  assert pg_temp.jd_abstention()='[]', 'An abstention-only incident must stay closed';

  -- Interleaving many abstentions must not dilute real errors or replace their evidence.
  for i in 1..9 loop
    assert pg_temp.health_sample('jd-logistics',false)='[]';
    assert pg_temp.jd_abstention()='[]';
  end loop;
  result := pg_temp.health_sample('jd-logistics',false);
  assert result->0->>'state'='open' and (result->0->>'attempts')::int=10
    and (result->0->>'failures')::int=10 and result->0->'evidence'->>'error_type'='TransportError',
    'Ten actionable failures must still alert with actionable evidence';
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);
  for i in 1..3 loop assert pg_temp.jd_abstention()='[]'; end loop;
  assert (select active from public.tracking_health_incidents where kind='provider' and subject='jd-logistics'),
    'Abstentions cannot count as the three successful probes needed for recovery';

  for i in 1..9 loop assert pg_temp.health_sample('jd-logistics',true)='[]'; end loop;
  result := pg_temp.health_sample('jd-logistics',true);
  assert result->0->>'state'='recovered' and (result->0->>'attempts')::int=20,
    'Actual successful lookups still recover once the actionable failure rate allows it';
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);

  for i in 1..9 loop assert pg_temp.jd_abstention('another-carrier')='[]'; end loop;
  result := pg_temp.jd_abstention('another-carrier');
  assert result->0->>'state'='open', 'Other carriers must not inherit this JD policy';
  perform public.ack_tracking_health(array[(result->0->>'id')::uuid]);
  for i in 1..9 loop assert pg_temp.jd_abstention('jd-logistics','direct')='[]'; end loop;
  result := pg_temp.jd_abstention('jd-logistics','direct');
  assert result->0->>'state'='open', 'A direct-step fault is not a final JD matcher abstention';
end;
$$;
rollback;
