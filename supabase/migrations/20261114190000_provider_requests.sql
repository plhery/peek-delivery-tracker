-- Universal providers are a fallback the app means to need less and less: each may refuse
-- an unofficial client, the sooner the more it is asked. The carrier dashboard counts what
-- the checks send to each of them and why, so the reliance can be watched and cut. See
-- docs/OBSERVABILITY.md.
--
-- metrics.provider_requests_daily counts, per UTC day, the lookups the checks sent to each
-- provider, by why the check asked and how the lookup ended. A provider is a key with a
-- capital letter in a check's lookups (carrier ids have none), so one the scraper adds is
-- counted without a change here. record_usage_metrics rewrites a day with its goals.
--
-- metrics.goals_daily also counts the parcels Track123 and Track718 answered last, which
-- no column held, and the day's first scans with those a provider found. A provider
-- without a column of its own counts as another provider, never nowhere.
--
-- Nothing here names a parcel, an account or a number. The server needs no change: it
-- already calls record_usage_metrics.

begin;

create table metrics.provider_requests_daily (
  day date not null,
  provider text not null check (length(provider) between 1 and 100),
  purpose text not null check (purpose in (
    'unseen', 'no_adapter', 'adapter_failed', 'adapter_no_answer', 'label_only', 'beside_carrier')),
  requests integer not null check (requests >= 0),
  with_history integer not null check (with_history >= 0),
  without_history integer not null check (without_history >= 0),
  failed integer not null check (failed >= 0),
  with_news integer not null check (with_news >= 0),
  primary key (day, provider, purpose)
);

comment on table metrics.provider_requests_daily is
  'Lookups the checks sent to each universal provider per UTC day, by why the check asked. The Add preflight''s lookups are not a check''s and count only in carrier_lookup_total.';
comment on column metrics.provider_requests_daily.purpose is
  'Why the check asked a provider. unseen: the parcel showed no scan yet. no_adapter: filed under no carrier, or under one whose own adapter answered no check in the 30 days before. adapter_failed: the carrier''s adapter failed in the check. adapter_no_answer: the carrier''s adapter was not asked, or held no history. label_only: the carrier answered and the parcel showed its label only. beside_carrier: the carrier answered a parcel already moving.';
comment on column metrics.provider_requests_daily.with_history is 'Lookups that returned history.';
comment on column metrics.provider_requests_daily.without_history is
  'Lookups the provider answered without history: it knows no such number, holds nothing for it, or asks for an input.';
comment on column metrics.provider_requests_daily.failed is 'Lookups that ended in an error: a refusal, a challenge, a timeout.';
comment on column metrics.provider_requests_daily.with_news is
  'Lookups that returned history in a check a provider answered and that changed what the parcel shows.';

create function metrics.record_provider_requests(p_day date)
returns void
language sql
security definer
set search_path = ''
as $$
  delete from metrics.provider_requests_daily where day = p_day;

  with bounds as (
    select p_day::timestamp at time zone 'UTC' as starts,
      least((p_day + 1)::timestamp at time zone 'UTC', now()) as ends
  ), asked as (
    select attempt.configured_carrier as carrier, attempt.previous_stage,
      attempt.lookups ->> attempt.configured_carrier as own,
      attempt.outcome = 'updated' and attempt.source_carrier = 'unknown' as news,
      lookup.key as provider, lookup.value as result
    from public.tracking_sync_attempts as attempt
    cross join bounds
    cross join lateral jsonb_each_text(attempt.lookups) as lookup
    where attempt.started_at >= bounds.starts and attempt.started_at < bounds.ends
      and lookup.key <> lower(lookup.key)
  ), direct as (
    -- Of the carriers those checks were filed under, the ones whose own adapter answered a
    -- check in the 30 days before.
    select filed.carrier
    from (select distinct carrier from asked where carrier not in ('unknown', 'intl-post')) as filed
    cross join bounds
    where exists (select 1 from public.tracking_sync_attempts as attempt
      where attempt.configured_carrier = filed.carrier and attempt.source_carrier = filed.carrier
        and attempt.outcome in ('updated', 'unchanged')
        and attempt.started_at >= bounds.ends - interval '30 days' and attempt.started_at < bounds.ends)
  )
  insert into metrics.provider_requests_daily (
    day, provider, purpose, requests, with_history, without_history, failed, with_news)
  select p_day, request.provider, request.purpose, count(*),
    count(*) filter (where request.result = 'success'),
    count(*) filter (where request.result not in ('success', 'error')),
    count(*) filter (where request.result = 'error'),
    count(*) filter (where request.result = 'success' and request.news)
  from (
    select asked.provider, asked.result, asked.news,
      case
        when asked.previous_stage is null or asked.previous_stage = 'pending' then 'unseen'
        when asked.own = 'success' and asked.previous_stage = 'registered' then 'label_only'
        when asked.own = 'success' then 'beside_carrier'
        when asked.own = 'error' then 'adapter_failed'
        when asked.own is not null or asked.carrier in (select carrier from direct) then 'adapter_no_answer'
        else 'no_adapter'
      end as purpose
    from asked
  ) as request
  group by request.provider, request.purpose;
$$;

revoke all on function metrics.record_provider_requests(date) from public;

alter table metrics.goals_daily
  add column parcels_track123 integer not null default 0 check (parcels_track123 >= 0),
  add column parcels_track718 integer not null default 0 check (parcels_track718 >= 0),
  add column first_scans integer not null default 0 check (first_scans >= 0),
  add column first_scans_by_provider integer not null default 0 check (first_scans_by_provider >= 0);

comment on column metrics.goals_daily.parcels_other_provider is
  'Unfinished parcels a provider without a column of its own answered last, or one the check did not name.';
comment on column metrics.goals_daily.first_scans is
  'Checks that started that day and gave a parcel its first scan.';
comment on column metrics.goals_daily.first_scans_by_provider is
  'First scans a universal provider found rather than a carrier''s adapter.';

-- As before, with Track123's and Track718's parcels and the day's first scans.
create or replace function metrics.record_goals(p_day date)
returns void
language sql
security definer
set search_path = ''
as $$
  with bounds as (
    select p_day::timestamp at time zone 'UTC' as starts,
      least((p_day + 1)::timestamp at time zone 'UTC', now()) as ends
  ), checks as (
    select
      count(*) as checks,
      count(*) filter (where attempt.outcome in ('error', 'abandoned')) as failed,
      count(*) filter (where attempt.outcome in ('updated', 'unchanged')) as answered,
      count(*) filter (where attempt.outcome in ('updated', 'unchanged') and attempt.source_carrier is not null
        and attempt.source_carrier <> 'unknown') as direct,
      count(*) filter (where cardinality(attempt.anomaly_codes) > 0) as anomalous,
      count(*) filter (where attempt.outcome = 'updated' and attempt.selected_stage <> 'pending'
        and (attempt.previous_stage is null or attempt.previous_stage = 'pending')) as first_scans,
      count(*) filter (where attempt.outcome = 'updated' and attempt.selected_stage <> 'pending'
        and (attempt.previous_stage is null or attempt.previous_stage = 'pending')
        and attempt.source_carrier = 'unknown') as first_scans_by_provider
    from public.tracking_sync_attempts as attempt, bounds
    where attempt.started_at >= bounds.starts and attempt.started_at < bounds.ends
      and attempt.outcome in ('updated', 'unchanged', 'waiting', 'error', 'abandoned')
  ), parcel as (
    select package.id,
      (package.user_id is not null and coalesce(package.owned_since, package.created_at) < bounds.ends)
        or exists (select 1 from public.tracking_sync_attempts as attempt
          where attempt.package_id = package.id and attempt.trigger <> 'release'
            and attempt.started_at >= bounds.ends - interval '24 hours' and attempt.started_at < bounds.ends)
        as followed,
      answer.served_by, answer.provider, answer.started_at as answered_at, bounds.ends
    from public.packages as package
    cross join bounds
    left join lateral (
      select case when attempt.source_carrier = 'unknown' then 'provider'
                  when attempt.source_carrier = attempt.configured_carrier then 'adapter'
                  else 'other_adapter' end as served_by,
        (select step.details->>'support_provider' from public.tracking_sync_steps as step
          where step.attempt_id = attempt.id and step.step = 'complete' limit 1) as provider,
        attempt.started_at
      from public.tracking_sync_attempts as attempt
      where attempt.package_id = package.id and attempt.outcome in ('updated', 'unchanged')
        and attempt.started_at < bounds.ends
      order by attempt.started_at desc
      limit 1
    ) as answer on true
    where package.created_at < bounds.ends
      and (package.archived_at is null or package.archived_at >= bounds.ends)
      -- Delivered or returned by then: when a check, or else a stored scan, first said so.
      and (package.current_stage not in ('delivered', 'returned') or coalesce(
        (select min(attempt.started_at) from public.tracking_sync_attempts as attempt
          where attempt.package_id = package.id and attempt.selected_stage = package.current_stage
            and attempt.outcome in ('updated', 'unchanged')),
        (select min(event.created_at) from public.tracking_events as event
          where event.package_id = package.id and event.stage = package.current_stage),
        package.last_synced_at, package.created_at) >= bounds.ends)
      -- A parcel without a scan yet waits for its carrier: neither direct nor stale.
      and exists (select 1 from public.tracking_sync_attempts as attempt
        where attempt.package_id = package.id and attempt.outcome in ('updated', 'unchanged')
          and attempt.selected_stage <> 'pending' and attempt.started_at < bounds.ends)
  ), parcels as (
    select
      count(*) as parcels,
      count(*) filter (where served_by = 'adapter') as own_adapter,
      count(*) filter (where served_by = 'other_adapter') as other_adapter,
      count(*) filter (where served_by = 'provider' and provider = 'ParcelsApp') as parcelsapp,
      count(*) filter (where served_by = 'provider' and provider = '17TRACK') as seventeentrack,
      count(*) filter (where served_by = 'provider' and provider = 'Ship24') as ship24,
      count(*) filter (where served_by = 'provider' and provider = 'Postal Ninja') as postal_ninja,
      count(*) filter (where served_by = 'provider' and provider = 'UPU') as upu,
      count(*) filter (where served_by = 'provider' and provider = 'Track123') as track123,
      count(*) filter (where served_by = 'provider' and provider = 'Track718') as track718,
      -- Every provider named above has its column; any other counts here.
      count(*) filter (where served_by = 'provider' and (provider is null or provider not in (
        'ParcelsApp', '17TRACK', 'Ship24', 'Postal Ninja', 'UPU', 'Track123', 'Track718'))) as other_provider,
      count(*) filter (where followed) as followed,
      count(*) filter (where followed and answered_at < ends - interval '24 hours') as stale
    from parcel
  ), copies as (
    -- Only now: a copy the sync later absorbs leaves no trace of having been shown.
    select count(distinct copy.package_id) as parcels
    from public.tracking_events as copy
    join public.tracking_events as scan on scan.package_id = copy.package_id and scan.id <> copy.id
      and not (scan.raw_data ? 'universal_provider') and scan.stage = copy.stage
    where copy.raw_data ? 'universal_provider' and copy.package_id in (select id from parcel)
      and (date_trunc('second', copy.occurred_at) = date_trunc('second', scan.occurred_at)
        or (lower(btrim(copy.description)) = lower(btrim(scan.description))
          and abs(extract(epoch from copy.occurred_at - scan.occurred_at)) <= 14 * 3600
          and mod(extract(epoch from copy.occurred_at - scan.occurred_at)::numeric, 900) = 0))
  ), feedback as (
    select count(*) as answers, count(*) filter (where answer.answer = 'right') as right_answers
    from public.parcel_feedback as answer, bounds
    where answer.created_at >= bounds.ends - interval '7 days' and answer.created_at < bounds.ends
  ), scans as (
    select count(*) as scans,
      count(*) filter (where event.raw_data->>'stage_source' = 'none') as unmapped,
      count(*) filter (where event.raw_data->>'stage_source' like 'wording:%') as by_wording
    from public.tracking_events as event, bounds
    where event.created_at >= bounds.starts and event.created_at < bounds.ends and event.raw_data ? 'stage_source'
  ), wordings as (
    select count(*) as wordings
    from public.tracking_status_observations as observation, bounds
    where observation.first_seen >= bounds.starts and observation.first_seen < bounds.ends
  )
  insert into metrics.goals_daily as goals (
    day, as_of, checks, failed_checks, answered_checks, direct_checks, anomalous_checks,
    parcels, parcels_own_adapter, parcels_other_adapter, parcels_parcelsapp, parcels_17track, parcels_ship24,
    parcels_postal_ninja, parcels_upu, parcels_track123, parcels_track718, parcels_other_provider,
    followed_parcels, stale_parcels, copied_parcels,
    feedback_7d, feedback_right_7d, scans, scans_unmapped, scans_by_wording, new_unmapped_wordings,
    first_scans, first_scans_by_provider
  )
  select p_day, bounds.ends, checks.checks, checks.failed, checks.answered, checks.direct, checks.anomalous,
    parcels.parcels, parcels.own_adapter, parcels.other_adapter, parcels.parcelsapp, parcels.seventeentrack,
    parcels.ship24, parcels.postal_ninja, parcels.upu, parcels.track123, parcels.track718, parcels.other_provider,
    parcels.followed, parcels.stale,
    case when bounds.ends > now() - interval '1 hour' then copies.parcels end,
    feedback.answers, feedback.right_answers, scans.scans, scans.unmapped, scans.by_wording, wordings.wordings,
    checks.first_scans, checks.first_scans_by_provider
  from bounds, checks, parcels, copies, feedback, scans, wordings
  on conflict (day) do update set
    as_of = excluded.as_of, checks = excluded.checks, failed_checks = excluded.failed_checks,
    answered_checks = excluded.answered_checks, direct_checks = excluded.direct_checks,
    anomalous_checks = excluded.anomalous_checks, parcels = excluded.parcels,
    parcels_own_adapter = excluded.parcels_own_adapter, parcels_other_adapter = excluded.parcels_other_adapter,
    parcels_parcelsapp = excluded.parcels_parcelsapp, parcels_17track = excluded.parcels_17track,
    parcels_ship24 = excluded.parcels_ship24, parcels_postal_ninja = excluded.parcels_postal_ninja,
    parcels_upu = excluded.parcels_upu, parcels_track123 = excluded.parcels_track123,
    parcels_track718 = excluded.parcels_track718, parcels_other_provider = excluded.parcels_other_provider,
    followed_parcels = excluded.followed_parcels, stale_parcels = excluded.stale_parcels,
    copied_parcels = coalesce(excluded.copied_parcels, goals.copied_parcels),
    feedback_7d = excluded.feedback_7d, feedback_right_7d = excluded.feedback_right_7d,
    scans = excluded.scans, scans_unmapped = excluded.scans_unmapped, scans_by_wording = excluded.scans_by_wording,
    new_unmapped_wordings = excluded.new_unmapped_wordings,
    first_scans = excluded.first_scans, first_scans_by_provider = excluded.first_scans_by_provider;
$$;

-- As before, and the day's provider requests with its goals.
create or replace function public.record_usage_metrics()
returns void
language plpgsql
security definer
set search_path = ''
as $$
declare
  today constant date := (now() at time zone 'UTC')::date;
begin
  perform metrics.record_additions(today - 1, today);
  update metrics.daily set
    accounts = (select count(*) from auth.users where not is_anonymous),
    parcels_open_account = (select count(*) from public.packages
      where user_id is not null and archived_at is null and current_stage not in ('delivered', 'returned')),
    parcels_open_lookup = (select count(*) from public.packages
      where one_off and archived_at is null and current_stage not in ('delivered', 'returned'))
  where day = today;

  if not exists (select 1 from metrics.goals_daily
      where day = today - 1 and as_of >= today::timestamp at time zone 'UTC') then
    perform metrics.record_provider_requests(today - 1);
    perform metrics.record_goals(today - 1);
  end if;
  if not exists (select 1 from metrics.goals_daily where day = today and as_of > now() - interval '10 minutes') then
    perform metrics.record_provider_requests(today);
    perform metrics.record_goals(today);
  end if;
end;
$$;

grant select on metrics.provider_requests_daily to metrics_reader;

-- Past days keep the goals they recorded. Until now Track123's parcels were the ones no
-- column counted; Track718's stay among the other providers'.
update metrics.goals_daily set parcels_track123 = greatest(0, parcels - (parcels_own_adapter
  + parcels_other_adapter + parcels_parcelsapp + parcels_17track + parcels_ship24 + parcels_postal_ninja
  + parcels_upu + parcels_other_provider));

update metrics.goals_daily as goals
set first_scans = first.scans, first_scans_by_provider = first.by_provider
from (
  select (attempt.started_at at time zone 'UTC')::date as day, count(*) as scans,
    count(*) filter (where attempt.source_carrier = 'unknown') as by_provider
  from public.tracking_sync_attempts as attempt
  where attempt.outcome = 'updated' and attempt.selected_stage <> 'pending'
    and (attempt.previous_stage is null or attempt.previous_stage = 'pending')
  group by 1
) as first
where goals.day = first.day;

alter table metrics.goals_daily
  alter column parcels_track123 drop default,
  alter column parcels_track718 drop default,
  alter column first_scans drop default,
  alter column first_scans_by_provider drop default;

-- The days whose checks name what they asked.
select metrics.record_provider_requests(day::date)
from generate_series(
  (select (min(started_at) at time zone 'UTC')::date from public.tracking_sync_attempts where lookups is not null),
  (now() at time zone 'UTC')::date,
  interval '1 day'
) as day
where exists (select 1 from public.tracking_sync_attempts where lookups is not null);

-- A day's checks, whatever their outcome. Last, so that the checks being written wait only
-- for the index to be built.
create index tracking_sync_attempts_started_idx on public.tracking_sync_attempts (started_at);

insert into public.applied_migrations (name) values ('20261114190000_provider_requests') on conflict do nothing;

commit;
