-- A save that sends no time zone (the apps' clock and units) creates the account's preferences row on the column's
-- default, Zurich, and the notification queues counted any row as a chosen zone: pushes told estimated times on
-- Zurich's clock. The row now records whether a save sent the zone, and the queues read that.
alter table public.notification_preferences add column timezone_chosen boolean not null default true;
comment on column public.notification_preferences.timezone_chosen is
  'Whether a save sent the zone in timezone. False: every save left it out, and timezone is the column''s default.';

create or replace function public.patch_owned_notification_preferences(
  p_set_quiet boolean, p_enabled_stages text[] default null,
  p_quiet_hours_start time default null, p_quiet_hours_end time default null,
  p_timezone text default null, p_email_on_delivery boolean default null,
  p_mark_new_updates boolean default null, p_clock text default null, p_distance text default null
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
    coalesce(p_timezone, saved.timezone), p_email_on_delivery, p_mark_new_updates, p_clock, p_distance);
  if saved.timezone_chosen is distinct from chosen then
    update public.notification_preferences set timezone_chosen = chosen where user_id = auth.uid() returning * into saved;
  end if;
  return saved;
end;
$$;

-- The queues' `timezone_chosen` (20261104150000_notification_scan_clock, 20261113052000_live_activity_clock).
do $$
declare
  queue text;
  definition text;
  changed text;
begin
  perform set_config('search_path', '', true);
  foreach queue in array array[
    'pending_push_notifications', 'pending_native_push_notifications', 'pending_live_activity_events'
  ] loop
    definition := regexp_replace(pg_get_viewdef(to_regclass('public.' || queue), true), ';\s*$', '');
    changed := regexp_replace(definition,
      '(JOIN (public\.)?notification_preferences preference ON preference\.user_id = package\.user_id\s+WHERE package\.id = \w+\.package_id)\)\) AS timezone_chosen',
      '\1 AND preference.timezone_chosen)) AS timezone_chosen');
    if changed = definition then
      raise exception '% has no timezone_chosen to change', queue;
    end if;
    execute format('create or replace view public.%I with (security_invoker = true) as %s', queue, changed);
    execute format('revoke all on public.%I from public, anon, authenticated', queue);
    execute format('grant select on public.%I to service_role', queue);
  end loop;
end;
$$;
notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261120160000_unchosen_notification_timezone') on conflict do nothing;
