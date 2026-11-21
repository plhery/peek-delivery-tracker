-- Peek AI's own estimates show on cards and parcel pages until the account switches them
-- off ("Peek AI" in Settings). The two save functions take the choice; a save that leaves it out keeps it.
alter table public.notification_preferences add column show_peek_ai boolean not null default true;
comment on column public.notification_preferences.show_peek_ai is
  'Whether the apps show Peek AI''s own estimates. On until switched off.';
grant select (show_peek_ai) on public.notification_preferences to authenticated;

drop function public.patch_owned_notification_preferences(boolean, text[], time, time, text, boolean, boolean, text, text);
drop function public.set_owned_notification_preferences(text[], time, time, text, boolean, boolean, text, text);

create function public.set_owned_notification_preferences(
  p_enabled_stages text[],
  p_quiet_hours_start time default null,
  p_quiet_hours_end time default null,
  p_timezone text default 'Europe/Zurich',
  p_email_on_delivery boolean default null,
  p_mark_new_updates boolean default null,
  p_clock text default null,
  p_distance text default null,
  p_show_peek_ai boolean default null
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
    show_peek_ai,
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
    coalesce(p_show_peek_ai, true),
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
    show_peek_ai = coalesce(p_show_peek_ai, stored.show_peek_ai),
    updated_at = excluded.updated_at
  returning * into saved;

  return saved;
end;
$$;
revoke all on function public.set_owned_notification_preferences(text[], time, time, text, boolean, boolean, text, text, boolean)
  from public, anon;
grant execute on function public.set_owned_notification_preferences(text[], time, time, text, boolean, boolean, text, text, boolean)
  to authenticated;

create function public.patch_owned_notification_preferences(
  p_set_quiet boolean, p_enabled_stages text[] default null,
  p_quiet_hours_start time default null, p_quiet_hours_end time default null,
  p_timezone text default null, p_email_on_delivery boolean default null,
  p_mark_new_updates boolean default null, p_clock text default null, p_distance text default null,
  p_show_peek_ai boolean default null
) returns public.notification_preferences language plpgsql security definer set search_path = '' as $$
declare
  saved public.notification_preferences;
  chosen boolean;
begin
  insert into public.notification_preferences (user_id, timezone_chosen) values (auth.uid(), false) on conflict do nothing;
  select * into strict saved from public.notification_preferences where user_id = auth.uid() for update;
  chosen := saved.timezone_chosen or p_timezone is not null;
  saved := public.set_owned_notification_preferences(coalesce(p_enabled_stages, saved.enabled_stages),
    case when p_set_quiet then p_quiet_hours_start else saved.quiet_hours_start end,
    case when p_set_quiet then p_quiet_hours_end else saved.quiet_hours_end end,
    coalesce(p_timezone, saved.timezone), p_email_on_delivery, p_mark_new_updates, p_clock, p_distance, p_show_peek_ai);
  if saved.timezone_chosen is distinct from chosen then
    update public.notification_preferences set timezone_chosen = chosen where user_id = auth.uid() returning * into saved;
  end if;
  return saved;
end;
$$;
revoke all on function public.patch_owned_notification_preferences(boolean, text[], time, time, text, boolean, boolean, text, text, boolean)
  from public, anon;
grant execute on function public.patch_owned_notification_preferences(boolean, text[], time, time, text, boolean, boolean, text, text, boolean)
  to authenticated;
notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261121150000_show_peek_ai') on conflict do nothing;
