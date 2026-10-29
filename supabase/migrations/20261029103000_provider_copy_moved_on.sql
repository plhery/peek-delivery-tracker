-- A universal provider's row in a carrier scan's words (alone or followed by the
-- row's place), in its stage and at its place up to three hours before it is a
-- copy of it (providerCopies.ts): ParcelsApp dates a USPS departure at the time
-- USPS first gave it, before USPS moved the departure on.

-- Whether a stored row is such a copy, as providerCopies.ts tells it: on a
-- parcel with a confirmed carrier, a row only a universal provider wrote (it
-- names the provider, or lies in the time the carrier's answers covered and
-- none of them held it), at the minute of one of the carrier's scans, whole
-- hours from it to a second that is not a minute's first, or in its stage
-- within 15 minutes, within an hour in its words (alone or followed by the
-- row's place, or in any words for the parcel's announcement), so worded up to
-- three hours before it at its place (as the scan names it, or without what
-- follows, such as a postcode) or whole hours up to 14 h away; for a scan dated
-- by its day
-- on a wall clock in no zone, at a time showing that wall clock, whole quarter
-- hours away up to 14 h, in its words and stage (or none), or at its second
-- when that is not a minute's first, or at the wall clock read as UTC to the
-- second in its stage; the provider's twin of such a copy; its row, at any
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
      lower(btrim(regexp_replace(coalesce(provider_row.location, ''), '\s+', ' ', 'g'))) as place
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
      scan.raw_data ->> 'local_time' as local_time,
      coalesce(btrim(scan.raw_data ->> 'time'), '') = ''
        and coalesce(scan.raw_data ->> 'local_time', '') ~ '^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}(:\d{2})?$'
        and pg_input_is_valid(left(scan.raw_data ->> 'local_time', 10), 'date') as by_day
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
      not scans.by_day and clock.minutes_apart <> 0 and mod(clock.minutes_apart, 3600) = 0 as hours_away
    from provider_rows as provider_row
    cross join scans
    cross join lateral (select
      extract(epoch from provider_row.minute - scans.minute) as minutes_apart,
      extract(epoch from provider_row.second - scans.second) as seconds_apart,
      case when scans.by_day then scans.local_time::timestamp at time zone 'UTC' end as wall_clock) as clock
    where case
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
            and scans.occurred_at > provider_row.occurred_at and scans.occurred_at <= provider_row.occurred_at + interval '3 hours')
          or (mod(clock.minutes_apart, 3600) = 0 and abs(clock.minutes_apart) <= 50400)
        ))
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
  )
  select exists (select 1 from repeated where id = p_event_id)
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

insert into public.applied_migrations (name) values ('20261029103000_provider_copy_moved_on') on conflict do nothing;
