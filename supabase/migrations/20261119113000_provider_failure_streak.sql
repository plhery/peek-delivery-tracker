-- A provider incident also opens on a run of failures: the provider's last 4 lookups failed, over at least 2 hours,
-- for at least two parcels. The rate rule (more than half of at least 10 lookups in 24 hours) misses a provider that
-- refuses everything while a breaker lets about one probe an hour through, as ParcelsApp did from 03:56Z on 19 Nov.
-- The function is built on 20261117134500_health_samples_counted_apart, the last that defined it.

create or replace function public.record_tracking_health(p_attempt_id uuid, p_package_id uuid, p_samples jsonb)
returns jsonb language plpgsql set search_path = pg_catalog as $$
declare
  s jsonb; k text; subject_name text; state public.tracking_health_incidents;
  total bigint; failures bigint; consecutive boolean; streak boolean; recovered boolean; breached boolean;
  window_start timestamptz; evidence jsonb; pending_id uuid; notifications jsonb := '[]';
  rescued boolean; refreshed boolean; streaks bigint; saved bigint; unanswered_lately boolean;
begin
  if jsonb_typeof(p_samples) <> 'array' or jsonb_array_length(p_samples) > 40 then
    raise exception 'Invalid health samples';
  end if;
  -- This check's refresh, recorded after its direct samples in the loop below.
  select bool_or((value->>'healthy')::boolean) into refreshed
    from jsonb_array_elements(p_samples) where value->>'kind'='refresh';
  -- Samples are kept a week, to be read again; the rules below read the last 24 hours only.
  delete from public.tracking_health_samples where observed_at < now() - interval '7 days';
  -- Recovery needs three healthy samples of the same kind and subject. A tier that is
  -- no longer probed never produces them, so its incident would stay open for ever.
  -- Once its evidence has expired it closes with a notice that says no recovery was seen.
  update public.tracking_health_incidents i set lease_until=null,
    pending=jsonb_build_object('id',gen_random_uuid(),'kind',i.kind,'subject',i.subject,'state','closed',
      'attempts',0,'failures',0,'window_hours',24,'consecutive_failures',false,'evidence','{}'::jsonb)
    where i.active and i.pending is null and not exists(
      select 1 from public.tracking_health_samples s where s.kind=i.kind and s.subject=i.subject
        and s.observed_at >= now() - interval '24 hours');
  -- A direct incident whose carrier now answers without its direct step (an adapter that goes
  -- straight to its browser step) gets no direct sample to recover from. It closes once the
  -- carrier's three latest lookups, all after its last direct attempt, answered and that attempt
  -- is six hours old: a step paused for a CAPTCHA or a cooldown (DHL Express's half hour) runs
  -- again sooner.
  update public.tracking_health_incidents i set lease_until=null,
    pending=jsonb_build_object('id',gen_random_uuid(),'kind',i.kind,'subject',i.subject,'state','closed','reason','bypassed',
      'attempts',0,'failures',0,'window_hours',24,'consecutive_failures',false,'evidence','{}'::jsonb)
    where i.kind='direct' and i.active and i.pending is null and (
      select direct.at < now()-interval '6 hours' and (
        select count(*)=3 and bool_and(lookup.healthy) from (
          select p.healthy from public.tracking_health_samples p
          where p.kind='provider' and p.subject=i.subject and p.observed_at > direct.at
            and p.observed_at >= now() - interval '24 hours'
          order by p.observed_at desc,p.attempt_id limit 3
        ) lookup)
      from (select max(d.observed_at) as at from public.tracking_health_samples d where d.kind='direct' and d.subject=i.subject
        and d.observed_at >= now() - interval '24 hours') direct);
  -- Deliberate matcher abstentions remain samples, without claiming that a lookup answered.
  -- Close an old outage supported only by those abstentions; real failures keep their incident.
  update public.tracking_health_incidents i set lease_until=null,
    pending=jsonb_build_object('id',gen_random_uuid(),'kind',i.kind,'subject',i.subject,'state','closed',
      'reason','verification_abstained','attempts',0,'failures',0,'window_hours',24,
      'consecutive_failures',false,'evidence','{}'::jsonb)
    where i.kind='provider' and i.subject='jd-logistics' and (i.active or i.pending->>'state'='open')
      and (i.pending is null or i.pending->>'state'='open')
      and exists(select 1 from public.tracking_health_samples s
        where s.observed_at >= now() - interval '24 hours' and s.kind=i.kind and s.subject=i.subject and s.kind='provider' and s.subject='jd-logistics' and not s.healthy and s.details @> '{"category":"challenge","reason":"verification_unreadable","error_type":"ChallengeError","step":"trawl"}'::jsonb)
      and not exists(select 1 from public.tracking_health_samples s
        where s.observed_at >= now() - interval '24 hours' and s.kind=i.kind and s.subject=i.subject and not s.healthy and not (s.kind='provider' and s.subject='jd-logistics' and not s.healthy and s.details @> '{"category":"challenge","reason":"verification_unreadable","error_type":"ChallengeError","step":"trawl"}'::jsonb));
  -- No later sample of its subject would send it again, so every check does until it is acknowledged.
  with sent as (
    update public.tracking_health_incidents set lease_until=now()+interval '2 minutes'
      where pending->>'state'='closed' and (lease_until is null or lease_until < now())
      returning pending)
  select coalesce(jsonb_agg(pending),'[]') into notifications from sent;
  for s in select value from jsonb_array_elements(p_samples) order by value->>'kind', value->>'subject' loop
    k := s->>'kind'; subject_name := s->>'subject';
    if k not in ('refresh', 'provider', 'direct') or length(subject_name) not between 1 and 100
      or jsonb_typeof(s->'healthy') <> 'boolean' then raise exception 'Invalid health sample'; end if;
    insert into public.tracking_health_incidents(kind, subject) values(k, subject_name) on conflict do nothing;
    select * into state from public.tracking_health_incidents where kind=k and subject=subject_name for update;
    insert into public.tracking_health_samples(attempt_id,kind,subject,package_id,healthy,details)
      values(p_attempt_id,k,subject_name,case when k='refresh' then p_package_id end,
        (s->>'healthy')::boolean, coalesce(s->'details','{}')) on conflict do nothing;
    window_start := now() - case when k='refresh' then interval '1 hour' else interval '24 hours' end;
    -- Healthy not-found, missing-input, repeated thin and no-news answers count for recovery, never for the rate, as
    -- do the samples counted apart: a refusal under a quota shared with every caller, a check waiting only on rate
    -- limits, a check of a parcel nothing has answered for.
    -- Expected JD matcher abstentions count toward neither the outage rate nor recovery.
    select count(*) filter(where not (healthy and coalesce(details->>'category','') in ('not_found','input_required','repeated_answer','no_new_scans','shared_quota','rate_limit_wait','unanswered'))),
      count(*) filter(where not healthy) into total,failures
      from public.tracking_health_samples where kind=k and subject=subject_name and observed_at >= window_start
        and not (kind='provider' and subject='jd-logistics' and not healthy and details @> '{"category":"challenge","reason":"verification_unreadable","error_type":"ChallengeError","step":"trawl"}'::jsonb);
    consecutive := false;
    if k='refresh' then
      -- Three failed scheduled checks of the SAME parcel, the last inside the window;
      -- manual refreshes never enter this table.
      -- Each copy of a number (followed in several accounts, or also without one) counts its own
      -- checks: one scheduled run gives every copy a sample of the one lookup they share. Copies of
      -- one number in a streak are one parcel.
      select count(distinct parcel) into streaks from (
        select min(recent.parcel) as parcel from (
          select s.healthy,s.package_id,coalesce(p.tracking_number,s.package_id::text) as parcel,s.observed_at,
            row_number() over(partition by s.package_id order by s.observed_at desc,s.attempt_id) as n
          from public.tracking_health_samples s left join public.packages p on p.id=s.package_id
          where s.kind=k and s.subject=subject_name and s.observed_at >= now() - interval '24 hours'
        ) recent where n<=3 group by recent.package_id
          having count(*)=3 and bool_and(not healthy) and max(observed_at) >= window_start) streak_parcels;
      -- One parcel's streak while another parcel's latest check of the window answered is that
      -- parcel's own, such as a number that trips the carrier's bot defences.
      consecutive := streaks >= 2 or (streaks = 1 and not exists(select 1 from (
        select distinct on (coalesce(p.tracking_number,s.package_id::text)) s.healthy, s.details
        from public.tracking_health_samples s left join public.packages p on p.id=s.package_id
        where s.kind=k and s.subject=subject_name and s.observed_at >= window_start
        order by coalesce(p.tracking_number,s.package_id::text), s.observed_at desc, s.attempt_id
      ) latest where healthy and coalesce(details->>'category','') not in ('not_found','input_required','repeated_answer','no_new_scans','shared_quota','rate_limit_wait','unanswered')));
    end if;
    -- A provider that fails every lookup but is asked too rarely for the rate (a breaker letting one probe an hour
    -- through, as with ParcelsApp's refusals): its last 4 lookups failed, over at least 2 hours, for at least two
    -- parcels. Copies of one number are one parcel, so one number the provider refuses opens nothing.
    if k='provider' then
      select count(*)=4 and bool_and(not recent.healthy) and max(recent.observed_at)-min(recent.observed_at) >= interval '2 hours'
          and count(distinct coalesce(p.tracking_number, a.package_id::text, recent.attempt_id::text)) >= 2
        into consecutive
        from (select s.healthy, s.observed_at, s.attempt_id from public.tracking_health_samples s
          where s.kind=k and s.subject=subject_name and s.observed_at >= now() - interval '24 hours'
            and not (s.kind='provider' and s.subject='jd-logistics' and not s.healthy and s.details @> '{"category":"challenge","reason":"verification_unreadable","error_type":"ChallengeError","step":"trawl"}'::jsonb)
          order by s.observed_at desc, s.attempt_id limit 4) recent
        left join public.tracking_sync_attempts a on a.id=recent.attempt_id
        left join public.packages p on p.id=a.package_id;
      consecutive := coalesce(consecutive, false);
    end if;
    -- A direct failure whose check's refresh answered, a fallback step serving the parcel, left no
    -- parcel without an answer: it counts toward no outage. The checks that went unanswered open an
    -- incident when there are at least 10 of them and they are at least a fifth of the day's direct
    -- checks, rescued ones included. An open one closes only after two hours without one.
    saved := 0; unanswered_lately := false;
    if k='direct' then
      select count(*) filter(where answered),
        coalesce(bool_or(not answered and observed_at >= now() - interval '2 hours'), false)
        into saved, unanswered_lately
        from (select d.observed_at, case when d.attempt_id=p_attempt_id then coalesce(refreshed, false)
            else exists(select 1 from public.tracking_health_samples r
              where r.attempt_id=d.attempt_id and r.kind='refresh' and r.healthy) end as answered
          from public.tracking_health_samples d
          where d.kind=k and d.subject=subject_name and not d.healthy and d.observed_at >= window_start) direct_failures;
    end if;
    rescued := k='direct' and failures=saved;
    breached := case when k='refresh' then consecutive or (failures>=5 and failures::numeric/nullif(total,0)>0.2)
      when k='direct' then failures-saved>=10 and (failures-saved)::numeric/nullif(total,0)>=0.2
      else consecutive or (total>=10 and failures::numeric/nullif(total,0)>0.5) end;
    select count(*)=3 and bool_and(healthy) into streak from (
      select healthy from public.tracking_health_samples where kind=k and subject=subject_name
        and observed_at >= now() - interval '24 hours'
        and not (kind='provider' and subject='jd-logistics' and not healthy and details @> '{"category":"challenge","reason":"verification_unreadable","error_type":"ChallengeError","step":"trawl"}'::jsonb)
      order by observed_at desc,attempt_id limit 3
    ) recent;
    recovered := streak and not breached and not unanswered_lately;
    select details into evidence from public.tracking_health_samples
      where kind=k and subject=subject_name and not healthy and observed_at >= now() - interval '24 hours'
        and not (kind='provider' and subject='jd-logistics' and not healthy and details @> '{"category":"challenge","reason":"verification_unreadable","error_type":"ChallengeError","step":"trawl"}'::jsonb)
      order by observed_at desc,attempt_id limit 1;
    -- A direct incident whose failures of the window all came from checks that still answered, the
    -- next step of the lookup serving the parcel, closes; such failures open none.
    if k='direct' and rescued and failures > 0 and not recovered and state.active and state.pending is null then
      state.pending := jsonb_build_object('id',gen_random_uuid(),'kind',k,'subject',subject_name,'state','closed',
        'reason','rescued','attempts',total,'failures',failures,'window_hours',24,
        'consecutive_failures',false,'evidence',coalesce(evidence,'{}'));
      update public.tracking_health_incidents set pending=state.pending,lease_until=null
        where kind=k and subject=subject_name;
      state.lease_until := null;
    end if;
    -- A direct incident whose carrier's three latest lookups answered, through its browser step or a
    -- provider, closes once the failures that left a parcel without an answer are back under the
    -- threshold and none came in two hours: a direct step challenged on every check (Australia Post's)
    -- never recovers, and a rare unanswered check would keep it from closing as rescued.
    if k='direct' and state.active and state.pending is null and not recovered and not breached and not unanswered_lately and (
      select count(*)=3 and bool_and(lookup.healthy) from (
        select p.healthy from public.tracking_health_samples p
        where p.kind='provider' and p.subject=subject_name and p.observed_at >= now() - interval '24 hours'
        order by p.observed_at desc,p.attempt_id limit 3
      ) lookup) then
      state.pending := jsonb_build_object('id',gen_random_uuid(),'kind',k,'subject',subject_name,'state','closed',
        'reason','answered','attempts',total,'failures',failures-saved,'window_hours',24,
        'consecutive_failures',false,'evidence',coalesce(evidence,'{}'));
      update public.tracking_health_incidents set pending=state.pending,lease_until=null
        where kind=k and subject=subject_name;
      state.lease_until := null;
    end if;
    if state.pending is null and (
      (state.active and recovered) or
      (breached and not streak and not rescued
        and (state.last_notified_at is null or state.last_notified_at < now()-interval '6 hours'))
    ) then
      pending_id := gen_random_uuid();
      state.pending := jsonb_build_object('id',pending_id,'kind',k,'subject',subject_name,
        'state',case when state.active and recovered then 'recovered' else 'open' end,
        'attempts',total,'failures',failures-saved,'window_hours',case when k='refresh' then 1 else 24 end,
        'consecutive_failures',consecutive,'evidence',coalesce(evidence,'{}'))
        || case when k='direct' and not (state.active and recovered) then jsonb_build_object('rescued',rescued) else '{}' end;
      update public.tracking_health_incidents set pending=state.pending,lease_until=null
        where kind=k and subject=subject_name;
      state.lease_until := null;
    end if;
    -- A notification is retried after a failed Sentry flush or worker crash.
    if state.pending is not null and (state.lease_until is null or state.lease_until < now()) then
      notifications := notifications || jsonb_build_array(state.pending);
      update public.tracking_health_incidents set lease_until=now()+interval '2 minutes' where kind=k and subject=subject_name;
    end if;
  end loop;
  return notifications;
end;
$$;

notify pgrst, 'reload schema';
insert into public.applied_migrations (name) values ('20261119113000_provider_failure_streak') on conflict do nothing;
