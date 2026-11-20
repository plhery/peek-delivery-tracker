-- A universal provider's row at the minute a carrier first gave a scan it has
-- re-dated since (`retimed_from`), in its stage or none, copies that scan
-- (providerCopies.ts's firstGivenAt): 17TRACK keeps a USPS departure at the
-- time USPS first gave it, hours before USPS moved it on. Such a copy shows no
-- zone the provider read the carrier's other scans in.

-- Whether a stored row is such a copy, as providerCopies.ts tells it: on a
-- parcel with a confirmed carrier, a row only a universal provider wrote (it
-- names the provider, or lies in the time the carrier's answers covered and
-- none of them held it), at the minute of one of the carrier's scans, whole
-- hours from it to a second that is not a minute's first, or in its stage
-- within 15 minutes, within an hour in its words (alone or followed by the
-- row's place, or in any words for the parcel's announcement), so worded up to
-- three hours before it at its place (as the scan names it, or without what
-- follows, such as a postcode), or a day before a USPS departure, or whole
-- hours up to 14 h away, or at the minute the carrier first gave it before
-- re-dating it, in its stage or none; for a scan dated
-- by its day
-- on a wall clock in no zone, at a time showing that wall clock, whole quarter
-- hours away up to 14 h, in its words and stage (or none), or at its second
-- when that is not a minute's first, or at the wall clock read as UTC to the
-- second in its stage; for a scan dated by its day alone, at the start of that
-- day on the row's own wall clock in its words and stage (or none); the
-- provider's rows dated by their day alone in a stage the carrier scanned that
-- day with a time of day (on its local clock, else in the offset of its time),
-- as many as those scans or fewer; the provider's twin
-- of such a copy; its row, at any
-- place, by the shift of a copy only whole hours from the carrier's scans; or a
-- row of the minute of the same provider's copy that is only whole hours from
-- the carrier's scans, or of a twin or such a row.
create or replace function public.hidden_provider_copy(p_event_id uuid)
returns boolean
language sql
stable
set search_path = ''
as $$
  with target as (
    select
      event.package_id,
      coalesce(event.raw_data ->> 'universal_provider', '') as provider,
      case when pg_input_is_valid(history ->> 'from', 'timestamptz') and pg_input_is_valid(history ->> 'to', 'timestamptz')
        then history end as history
    from public.tracking_events as event
    join public.packages as package on package.id = event.package_id
    cross join lateral (select package.carrier_data -> 'direct_history' as history) as stored
    where event.id = p_event_id
      and jsonb_typeof(package.carrier_data -> 'routing' -> 'confirmed_carrier') = 'string'
      and package.carrier_data -> 'routing' ->> 'confirmed_carrier' <> 'unknown'
  ), provider_rows as (
    -- The rows only the row's universal provider wrote.
    select
      provider_row.id,
      provider_row.stage,
      provider_row.occurred_at,
      date_trunc('minute', provider_row.occurred_at, 'UTC') as minute,
      date_trunc('second', provider_row.occurred_at, 'UTC') as second,
      lower(btrim(regexp_replace(coalesce(provider_row.description, ''), '\s+', ' ', 'g'))) as wording,
      lower(btrim(regexp_replace(coalesce(provider_row.location, ''), '\s+', ' ', 'g'))) as place,
      provider_row.raw_data ->> 'local_time' as local_time,
      case when coalesce(btrim(provider_row.raw_data ->> 'time'), '') = ''
        and coalesce(provider_row.raw_data ->> 'local_time', '') ~ '^\d{4}-\d{2}-\d{2}$'
        and pg_input_is_valid(provider_row.raw_data ->> 'local_time', 'date')
        then (provider_row.raw_data ->> 'local_time')::date end as dated_day
    from target
    join public.tracking_events as provider_row
      on provider_row.package_id = target.package_id
     and coalesce(provider_row.raw_data ->> 'universal_provider', '') = target.provider
    where provider_row.provider_event_id like 'unknown:%'
      and provider_row.occurred_at is not null
      and provider_row.raw_data -> 'observed_without_provider_timestamp' is distinct from 'true'::jsonb
      and not (coalesce(target.history -> 'held', '[]'::jsonb) ? provider_row.provider_event_id)
      and (target.provider <> '' or (target.history is not null
        and provider_row.occurred_at between (target.history ->> 'from')::timestamptz and (target.history ->> 'to')::timestamptz))
  ), scans as (
    -- The carrier's scans.
    select
      scan.stage,
      scan.occurred_at,
      date_trunc('minute', scan.occurred_at, 'UTC') as minute,
      date_trunc('second', scan.occurred_at, 'UTC') as second,
      lower(btrim(regexp_replace(coalesce(scan.description, ''), '\s+', ' ', 'g'))) as wording,
      lower(btrim(regexp_replace(coalesce(scan.location, ''), '\s+', ' ', 'g'))) as place,
      -- A USPS departure, which USPS re-dates up to a day on (eventIdentity.ts's redatedDeparture).
      split_part(scan.provider_event_id, ':', 1) = 'usps'
        and lower(btrim(regexp_replace(coalesce(scan.description, ''), '\s+', ' ', 'g'))) ~ '^departed\y' as redated_departure,
      scan.raw_data ->> 'local_time' as local_time,
      -- The minute the carrier first gave a scan it re-dated since.
      case when pg_input_is_valid(scan.raw_data ->> 'retimed_from', 'timestamptz')
        then date_trunc('minute', (scan.raw_data ->> 'retimed_from')::timestamptz, 'UTC') end as first_minute,
      coalesce(btrim(scan.raw_data ->> 'time'), '') = ''
        and coalesce(scan.raw_data ->> 'local_time', '') ~ '^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}(:\d{2})?$'
        and pg_input_is_valid(left(scan.raw_data ->> 'local_time', 10), 'date') as by_day,
      coalesce(btrim(scan.raw_data ->> 'time'), '') = ''
        and coalesce(scan.raw_data ->> 'local_time', '') ~ '^\d{4}-\d{2}-\d{2}$'
        and pg_input_is_valid(scan.raw_data ->> 'local_time', 'date') as by_date,
      case when coalesce(btrim(scan.raw_data ->> 'time'), '') = '' then null
        when coalesce(scan.raw_data ->> 'local_time', '') ~ '^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}' then
          case when pg_input_is_valid(left(scan.raw_data ->> 'local_time', 10), 'date')
            then left(scan.raw_data ->> 'local_time', 10)::date end
        when btrim(scan.raw_data ->> 'time') ~ '^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}(:\d{2}(\.\d+)?)?[+-]\d{2}(:?\d{2})?$'
          and pg_input_is_valid(left(btrim(scan.raw_data ->> 'time'), 10), 'date')
          then left(btrim(scan.raw_data ->> 'time'), 10)::date end as timed_day
    from target
    join public.tracking_events as scan on scan.package_id = target.package_id
    where scan.occurred_at is not null
      and scan.raw_data -> 'observed_without_provider_timestamp' is distinct from 'true'::jsonb
      and position(':' in coalesce(scan.provider_event_id, '')) > 1
      and split_part(scan.provider_event_id, ':', 1) <> 'app'
      and (scan.provider_event_id not like 'unknown:%' or coalesce(target.history -> 'held', '[]'::jsonb) ? scan.provider_event_id)
  ), repeated as (
    -- Each carrier scan a provider row repeats, how far from it, and whether only whole hours.
    select
      provider_row.id,
      clock.minutes_apart,
      not scans.by_day and not clock.first_given and clock.minutes_apart <> 0 and mod(clock.minutes_apart, 3600) = 0
        as hours_away
    from provider_rows as provider_row
    cross join scans
    cross join lateral (select
      extract(epoch from provider_row.minute - scans.minute) as minutes_apart,
      extract(epoch from provider_row.second - scans.second) as seconds_apart,
      case when scans.by_day then scans.local_time::timestamp at time zone 'UTC' end as wall_clock,
      coalesce(scans.first_minute = provider_row.minute, false)
        and (scans.stage = provider_row.stage or coalesce(provider_row.stage, '') in ('', 'pending')) as first_given) as clock
    where clock.first_given or case
      when scans.by_day then
        ((scans.stage = provider_row.stage or provider_row.stage in ('', 'pending'))
          and scans.wording = provider_row.wording
          and mod(extract(epoch from provider_row.minute - date_trunc('minute', clock.wall_clock, 'UTC')), 900) = 0
          and abs(extract(epoch from provider_row.minute - date_trunc('minute', clock.wall_clock, 'UTC'))) <= 50400)
        or (date_trunc('minute', clock.wall_clock, 'UTC') <> clock.wall_clock
          and mod(extract(epoch from provider_row.second - clock.wall_clock), 900) = 0
          and abs(extract(epoch from provider_row.second - clock.wall_clock)) <= 50400)
        or (provider_row.second = clock.wall_clock and scans.stage = provider_row.stage)
      else
        clock.minutes_apart = 0
        or (scans.minute <> scans.second and clock.seconds_apart <> 0
          and mod(clock.seconds_apart, 3600) = 0 and abs(clock.seconds_apart) <= 50400)
        or (scans.stage = provider_row.stage and (
          abs(extract(epoch from provider_row.occurred_at - scans.occurred_at)) <= 900
          or (abs(extract(epoch from provider_row.occurred_at - scans.occurred_at)) <= 3600 and (scans.stage = 'registered'
            or (scans.wording <> '' and provider_row.wording in (scans.wording, scans.wording || ', ' || provider_row.place))))
          or (scans.wording <> '' and provider_row.wording in (scans.wording, scans.wording || ', ' || provider_row.place)
            and provider_row.place <> '' and (scans.place = provider_row.place or starts_with(scans.place, provider_row.place || ' '))
            and scans.occurred_at > provider_row.occurred_at
            and scans.occurred_at <= provider_row.occurred_at
              + case when scans.redated_departure then interval '24 hours' else interval '3 hours' end)
          or (mod(clock.minutes_apart, 3600) = 0 and abs(clock.minutes_apart) <= 50400)
        ))
        or (scans.by_date and (scans.stage = provider_row.stage or provider_row.stage in ('', 'pending'))
          and scans.wording <> '' and scans.wording = provider_row.wording
          and provider_row.local_time in (scans.local_time || 'T00:00', scans.local_time || 'T00:00:00'))
    end
  ), zoned as (
    -- The copies only whole hours from the carrier's scans: the provider read those a zone away.
    select id from repeated group by id having bool_and(hours_away)
  ), retold as (
    -- The provider's twin of a copy, and its row, at any place, by the shift a zoned copy shows.
    select provider_row.id
    from provider_rows as provider_row
    where not exists (select 1 from repeated where repeated.id = provider_row.id)
      and (exists (
        select 1
        from provider_rows as copy
        cross join lateral (select extract(epoch from provider_row.minute - copy.minute) as minutes_apart) as clock
        where exists (select 1 from repeated where repeated.id = copy.id)
          and copy.wording = provider_row.wording
          and copy.place = provider_row.place
          and copy.stage = provider_row.stage
          and clock.minutes_apart <> 0 and mod(clock.minutes_apart, 3600) = 0 and abs(clock.minutes_apart) <= 50400
      ) or exists (
        select 1
        from scans
        join repeated
          on repeated.hours_away
         and repeated.minutes_apart = extract(epoch from provider_row.minute - scans.minute)
        join zoned on zoned.id = repeated.id
        where not scans.by_day
      ))
  ), day_copied as (
    -- The provider's rows dated by their day alone in a stage the carrier scanned that day with a time of day, as
    -- many as those scans or fewer.
    select dated.id
    from (
      select provider_row.id, provider_row.stage, provider_row.dated_day,
        count(*) over (partition by provider_row.stage, provider_row.dated_day) as rows_that_day
      from provider_rows as provider_row
      where provider_row.dated_day is not null and coalesce(provider_row.stage, '') not in ('', 'pending')
    ) as dated
    cross join lateral (
      select count(*) as scanned from scans where scans.timed_day = dated.dated_day and scans.stage = dated.stage
    ) as own
    where own.scanned > 0 and dated.rows_that_day <= own.scanned
  )
  select exists (select 1 from repeated where id = p_event_id)
    or exists (select 1 from day_copied where id = p_event_id)
    or exists (select 1 from retold where id = p_event_id)
    or exists (
      select 1
      from provider_rows as self_row
      join provider_rows as other on other.minute = self_row.minute and other.id <> self_row.id
      where self_row.id = p_event_id
        and (exists (select 1 from zoned where zoned.id = other.id) or exists (select 1 from retold where retold.id = other.id))
    );
$$;

revoke all on function public.hidden_provider_copy(uuid) from public, anon, authenticated;
grant execute on function public.hidden_provider_copy(uuid) to service_role;

insert into public.applied_migrations (name) values ('20261120060000_provider_copy_first_given') on conflict do nothing;
