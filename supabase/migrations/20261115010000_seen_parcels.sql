-- A parcel's scans new since its owner last opened it (#72).
--
-- The list marks a parcel whose latest scan arrived after its owner last opened it. packages.seen_at
-- is that moment, one per parcel, so opening a parcel on one device clears the mark on the others.
-- Every parcel starts as seen: the ones stored now from this migration, a new one from its first
-- answer, and one that joins an account from that moment. The server compares seen_at with the time
-- the parcel's latest scan was stored (tracking_events.created_at), which a scan rewritten in place
-- keeps.
--
-- Whether the mark shows is the account's choice, kept with its notification preferences and on
-- until switched off. It is a sixth argument of set_owned_notification_preferences: NULL keeps what
-- is stored, so a caller that sends the older arguments changes nothing about it. The five-argument
-- function goes in the same transaction: PostgREST must find one candidate for those arguments.
-- Everything else in it is copied from 20261003160000_delivery_emails.sql.
--
-- Apply this before deploying the server that reads seen_at. The server before it keeps working on
-- this schema: it names the columns it reads, and saves preferences with the arguments it knows.

begin;

alter table public.packages add column seen_at timestamptz not null default now();
comment on column public.packages.seen_at is
  'When the owner last opened the parcel, or when it started as seen: at its first answer, or as it joined the account. A latest scan stored after it is new to the owner.';
grant select (seen_at) on public.packages to authenticated;

create function public.start_package_seen()
returns trigger
language plpgsql
set search_path = pg_catalog
as $$
begin
  new.seen_at := now();
  return new;
end;
$$;

revoke all on function public.start_package_seen() from public, anon, authenticated;

-- The first answer for a parcel, or for its new carrier, number or inputs (which clear
-- last_synced_at), is how it starts, and so is what it holds as it joins an account.
create trigger packages_start_seen
before update of user_id, last_synced_at on public.packages
for each row
when (old.user_id is distinct from new.user_id
  or (old.last_synced_at is null and new.last_synced_at is not null))
execute function public.start_package_seen();

-- The owner opened the parcel. Answers the moment stored, or NULL for a parcel that is not theirs.
create function public.mark_owned_package_seen(p_package_id uuid)
returns timestamptz
language plpgsql
security definer
set search_path = pg_catalog
as $$
declare
  seen timestamptz;
begin
  if auth.uid() is null then
    raise exception 'An account is required' using errcode = '42501';
  end if;
  update public.packages
  set seen_at = now()
  where id = p_package_id and user_id = auth.uid()
  returning seen_at into seen;
  return seen;
end;
$$;

revoke all on function public.mark_owned_package_seen(uuid) from public, anon;
grant execute on function public.mark_owned_package_seen(uuid) to authenticated;

alter table public.notification_preferences
  add column mark_new_updates boolean not null default true;
comment on column public.notification_preferences.mark_new_updates is
  'Whether the list marks a parcel with scans new since the account last opened it.';
grant select (mark_new_updates) on public.notification_preferences to authenticated;

drop function public.set_owned_notification_preferences(text[], time, time, text, boolean);

create function public.set_owned_notification_preferences(
  p_enabled_stages text[],
  p_quiet_hours_start time default null,
  p_quiet_hours_end time default null,
  p_timezone text default 'Europe/Zurich',
  p_email_on_delivery boolean default null,
  p_mark_new_updates boolean default null
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

  insert into public.notification_preferences as stored (
    user_id,
    enabled_stages,
    quiet_hours_start,
    quiet_hours_end,
    timezone,
    email_on_delivery,
    email_enabled_at,
    mark_new_updates,
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
    updated_at = excluded.updated_at
  returning * into saved;

  return saved;
end;
$$;

revoke all on function public.set_owned_notification_preferences(text[], time, time, text, boolean, boolean)
  from public, anon;
grant execute on function public.set_owned_notification_preferences(text[], time, time, text, boolean, boolean)
  to authenticated;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261115010000_seen_parcels') on conflict do nothing;

commit;
