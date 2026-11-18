create function public.patch_owned_notification_preferences(
  p_set_quiet boolean, p_enabled_stages text[] default null,
  p_quiet_hours_start time default null, p_quiet_hours_end time default null,
  p_timezone text default null, p_email_on_delivery boolean default null,
  p_mark_new_updates boolean default null, p_clock text default null, p_distance text default null
) returns public.notification_preferences language plpgsql security definer set search_path = '' as $$
declare
  saved public.notification_preferences;
begin
  insert into public.notification_preferences (user_id) values (auth.uid()) on conflict do nothing;
  select * into strict saved from public.notification_preferences where user_id = auth.uid() for update;
  return public.set_owned_notification_preferences(coalesce(p_enabled_stages, saved.enabled_stages),
    case when p_set_quiet then p_quiet_hours_start else saved.quiet_hours_start end,
    case when p_set_quiet then p_quiet_hours_end else saved.quiet_hours_end end,
    coalesce(p_timezone, saved.timezone), p_email_on_delivery, p_mark_new_updates, p_clock, p_distance);
end;
$$;
revoke all on function public.patch_owned_notification_preferences(boolean,text[],time,time,text,boolean,boolean,text,text) from public, anon;
grant execute on function public.patch_owned_notification_preferences(boolean,text[],time,time,text,boolean,boolean,text,text) to authenticated;
notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261118190200_partial_notification_preferences') on conflict do nothing;
