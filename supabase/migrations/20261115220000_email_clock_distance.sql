-- How an account's emails write a time and a distance (#67, #68): on the 24-hour or the 12-hour clock, in
-- kilometres or miles, as the account's app shows them. Each app saves what it shows with the account's
-- notification preferences whenever the two differ, so the emails follow the app the reader last opened. NULL, until
-- an app has saved them: the email's language decides, as before.
--
-- They are the seventh and eighth arguments of set_owned_notification_preferences: NULL keeps what is stored, so a
-- caller that sends the older arguments changes nothing about them. The six-argument function goes in the same
-- transaction: PostgREST must find one candidate for those arguments. claim_delivery_emails hands both out with each
-- email. Everything else in them is copied from 20261115010000_seen_parcels.sql and
-- 20261108130000_emails_skip_named_scans.sql.
--
-- Apply this before deploying the server that reads them. The server before it keeps working on this schema: it names
-- the columns it reads, saves preferences with the arguments it knows, and reads the claim's fields by name.

begin;

alter table public.notification_preferences
  add column clock text check (clock in ('24', '12')),
  add column distance text check (distance in ('km', 'mi'));
comment on column public.notification_preferences.clock is
  'How the account''s emails write a time: 24 or 12 hours, as its app shows them. NULL: the email''s language decides.';
comment on column public.notification_preferences.distance is
  'How the account''s emails write a distance: km or mi, as its app shows them. NULL: the email''s language decides.';
grant select (clock, distance) on public.notification_preferences to authenticated;

drop function public.set_owned_notification_preferences(text[], time, time, text, boolean, boolean);

create function public.set_owned_notification_preferences(
  p_enabled_stages text[],
  p_quiet_hours_start time default null,
  p_quiet_hours_end time default null,
  p_timezone text default 'Europe/Zurich',
  p_email_on_delivery boolean default null,
  p_mark_new_updates boolean default null,
  p_clock text default null,
  p_distance text default null
)
returns public.notification_preferences
language plpgsql
security definer
set search_path = pg_catalog
as $$
declare
  actor_id uuid := auth.uid();
  saved public.notification_preferences;
begin
  if actor_id is null or exists (
    select 1 from auth.users where id = actor_id and is_anonymous
  ) then
    raise exception 'A permanent account is required' using errcode = '42501';
  end if;
  if p_enabled_stages is null
      or cardinality(p_enabled_stages) not between 1 and 10
      or not p_enabled_stages <@ array[
        'registered', 'accepted', 'in_transit', 'customs', 'exception',
        'out_for_delivery', 'failed_attempt', 'ready_for_pickup', 'delivered',
        'returned'
      ]::text[]
      or cardinality(p_enabled_stages) <> cardinality(array(
        select distinct stage from unnest(p_enabled_stages) as stage
      )) then
    raise exception 'Invalid notification stages' using errcode = '22023';
  end if;
  if (p_quiet_hours_start is null) <> (p_quiet_hours_end is null)
      or p_quiet_hours_start = p_quiet_hours_end then
    raise exception 'Invalid quiet hours' using errcode = '22023';
  end if;
  if p_timezone is null
      or char_length(p_timezone) not between 1 and 64
      or not exists (
        select 1 from pg_catalog.pg_timezone_names where name = p_timezone
      ) then
    raise exception 'Invalid timezone' using errcode = '22023';
  end if;
  if p_clock not in ('24', '12') or p_distance not in ('km', 'mi') then
    raise exception 'Invalid clock or distance unit' using errcode = '22023';
  end if;

  insert into public.notification_preferences as stored (
    user_id,
    enabled_stages,
    quiet_hours_start,
    quiet_hours_end,
    timezone,
    email_on_delivery,
    email_enabled_at,
    mark_new_updates,
    clock,
    distance,
    updated_at
  ) values (
    actor_id,
    p_enabled_stages,
    p_quiet_hours_start,
    p_quiet_hours_end,
    p_timezone,
    p_email_on_delivery,
    case when p_email_on_delivery then now() end,
    coalesce(p_mark_new_updates, true),
    p_clock,
    p_distance,
    now()
  )
  on conflict (user_id) do update set
    enabled_stages = excluded.enabled_stages,
    quiet_hours_start = excluded.quiet_hours_start,
    quiet_hours_end = excluded.quiet_hours_end,
    timezone = excluded.timezone,
    email_on_delivery = coalesce(excluded.email_on_delivery, stored.email_on_delivery),
    -- Switching the email on starts it from now: nothing stored before is announced.
    email_enabled_at = case
      when excluded.email_on_delivery and stored.email_on_delivery is not true then now()
      else stored.email_enabled_at
    end,
    mark_new_updates = coalesce(p_mark_new_updates, stored.mark_new_updates),
    clock = coalesce(p_clock, stored.clock),
    distance = coalesce(p_distance, stored.distance),
    updated_at = excluded.updated_at
  returning * into saved;

  return saved;
end;
$$;

revoke all on function public.set_owned_notification_preferences(text[], time, time, text, boolean, boolean, text, text)
  from public, anon;
grant execute on function public.set_owned_notification_preferences(text[], time, time, text, boolean, boolean, text, text)
  to authenticated;


create or replace function public.claim_delivery_emails(
  p_limit integer,
  p_per_account integer,
  p_per_day integer,
  p_stages text[] default array['delivered']
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  claimed jsonb;
begin
  if p_limit is null or p_limit < 1
      or p_per_account is null or p_per_account < 0
      or p_per_day is null or p_per_day < 0 then
    raise exception 'Invalid delivery email allowance' using errcode = '22023';
  end if;
  if p_stages is null or cardinality(p_stages) = 0
      or not p_stages <@ array['delivered', 'ready_for_pickup']::text[] then
    raise exception 'Invalid delivery email stages' using errcode = '22023';
  end if;

  -- One claim at a time: both allowances count what the other servers claimed.
  perform pg_catalog.pg_advisory_xact_lock(pg_catalog.hashtextextended('delivery-emails', 0));

  with scan as (
    select
      package.id as package_id,
      package.user_id,
      event.id as event_id,
      event.stage,
      event.occurred_at,
      event.created_at as stored_at,
      preference.timezone,
      preference.clock,
      preference.distance,
      coalesce(package.owned_since, package.created_at) as joined_at,
      case
        when event.raw_data -> 'observed_without_provider_timestamp' = 'true'::jsonb then 'none'
        when event.raw_data ->> 'time' ~ '[T ][0-9]{2}:[0-9]{2}' then 'timed'
        when event.raw_data ->> 'time' ~ '^([0-9]{4}-[0-9]{2}-[0-9]{2}|[0-9]{2}[./][0-9]{2}[./][0-9]{4})' then 'date'
        else 'none'
      end as delivered_time
    from public.notification_preferences as preference
    join public.packages as package on package.user_id = preference.user_id
    join public.tracking_events as event on event.package_id = package.id
    where preference.email_on_delivery
      and not package.email_muted
      and package.archived_at is null
      and package.current_stage = any(p_stages)
      and event.stage = package.current_stage
      and (event.provider_event_id is null or event.provider_event_id not like 'app:%')
      and event.created_at > preference.email_enabled_at
      and event.created_at > now() - interval '24 hours'
  ),
  candidate as (
    select distinct on (scan.package_id) scan.*, previous.id as previous_id
    from scan
    left join public.delivery_emails as previous
      on previous.package_id = scan.package_id and previous.stage = scan.stage
    where scan.stored_at > scan.joined_at
      and case
        when scan.delivered_time = 'timed' then scan.occurred_at > scan.joined_at
        -- A day is stored as its first moment, so it may read up to a day early.
        else scan.occurred_at > scan.joined_at - interval '1 day' and exists (
          select 1 from public.tracking_sync_attempts as earlier
          where earlier.package_id = scan.package_id
            and earlier.outcome in ('updated', 'unchanged')
            and earlier.selected_stage is distinct from scan.stage
            and earlier.started_at >= scan.joined_at
            and earlier.completed_at < scan.stored_at
        )
      end
      and coalesce(scan.occurred_at >= (
        select max(other.occurred_at)
        from public.tracking_events as other
        where other.package_id = scan.package_id
          and (other.provider_event_id is null or other.provider_event_id not like 'app:%')
          and other.stage <> 'pending'
          and other.occurred_at <= now()
      ) - interval '1 hour', true)
      and (previous.id is null or (
        previous.status = 'failed'
        and previous.attempts < 3
        and previous.claimed_at <= now() - case when previous.attempts < 2 then interval '15 minutes' else interval '1 hour' end
      ))
      and not exists (
        select 1
        from public.delivery_emails as told
        join public.tracking_events as told_scan on told_scan.id = told.event_id
        where told_scan.package_id = scan.package_id
          and told.package_id is distinct from scan.package_id
          and told.stage = scan.stage
      )
    order by scan.package_id, scan.occurred_at desc, scan.stored_at desc, scan.event_id
  ),
  batch as (
    select limited.*,
      row_number() over (partition by limited.user_id order by limited.stored_at, limited.event_id) as account_position
    from (
      select * from candidate
      -- A scan another email already names, as one restaged in place after its
      -- email, is not handed out: its row could not be written, and it would
      -- stay first in line for a day.
      where not exists (
        select 1 from public.delivery_emails as named
        where named.event_id = candidate.event_id
          and named.id is distinct from candidate.previous_id
      )
      order by candidate.stored_at, candidate.event_id limit least(p_limit, 100)
    ) as limited
  ),
  used as (
    select email.user_id, count(*) as emails
    from public.delivery_emails as email
    where email.status in ('claimed', 'sent')
      and coalesce(email.sent_at, email.claimed_at) > now() - interval '24 hours'
    group by email.user_id
  ),
  judged as (
    select batch.*, batch.account_position + coalesce(used.emails, 0) <= p_per_account as account_allows
    from batch
    left join used on used.user_id = batch.user_id
  ),
  decided as (
    select judged.*,
      case
        when not judged.account_allows then 'account_cap'
        when (select coalesce(sum(used.emails), 0) from used)
          + count(*) filter (where judged.account_allows) over (order by judged.stored_at, judged.event_id)
          > p_per_day then 'service_cap'
      end as skipped
    from judged
  ),
  inserted as (
    insert into public.delivery_emails (user_id, package_id, event_id, stage, status, reason, attempts)
    select decided.user_id, decided.package_id, decided.event_id, decided.stage,
      case when decided.skipped is null then 'claimed' else 'skipped' end,
      decided.skipped,
      case when decided.skipped is null then 1 else 0 end
    from decided
    where decided.previous_id is null
    on conflict do nothing
    returning id, package_id, status
  ),
  retried as (
    update public.delivery_emails as email
    set status = case when decided.skipped is null then 'claimed' else 'skipped' end,
      reason = decided.skipped,
      attempts = email.attempts + case when decided.skipped is null then 1 else 0 end,
      event_id = decided.event_id,
      claimed_at = now()
    from decided
    where email.id = decided.previous_id
      and email.status = 'failed'
      and email.attempts < 3
    returning email.id, email.package_id, email.status
  )
  select jsonb_build_object(
    'send', coalesce(jsonb_agg(jsonb_build_object(
      'id', written.id,
      'package_id', decided.package_id,
      'user_id', decided.user_id,
      'event_id', decided.event_id,
      'stage', decided.stage,
      'timezone', decided.timezone,
      'clock', decided.clock,
      'distance', decided.distance,
      'delivered_time', decided.delivered_time
    ) order by decided.stored_at, decided.event_id) filter (where written.status = 'claimed'), '[]'::jsonb),
    'account_cap', count(*) filter (where written.status = 'skipped' and decided.skipped = 'account_cap'),
    'service_cap', count(*) filter (where written.status = 'skipped' and decided.skipped = 'service_cap')
  )
  into claimed
  from (select * from inserted union all select * from retried) as written
  join decided on decided.package_id = written.package_id;

  return claimed;
end;
$$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261115220000_email_clock_distance') on conflict do nothing;

commit;
