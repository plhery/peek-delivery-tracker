-- The Live Activity queue carries each scan's time as its source wrote it, and whether the account
-- chose a time zone, as the other notification queues do (20261104150000_notification_scan_clock):
-- an activity tells a delivery window on the estimate's own clock when nobody chose one, as the
-- push beside it does. The queue's filters do not change.
do $$
declare
  definition text;
begin
  perform set_config('search_path', '', true);
  if exists (
    select 1 from information_schema.columns
    where table_schema = 'public' and table_name = 'pending_live_activity_events' and column_name = 'timezone_chosen'
  ) then return; end if;
  definition := regexp_replace(pg_get_viewdef('public.pending_live_activity_events'::regclass, true), ';\s*$', '');
  execute format($view$
    create or replace view public.pending_live_activity_events with (security_invoker = true) as
    select pending.*, source_event.raw_data ->> 'time' as event_time,
      exists (
        select 1 from public.packages as package
        join public.notification_preferences as preference on preference.user_id = package.user_id
        where package.id = pending.package_id
      ) as timezone_chosen
    from (%s) as pending
    join public.tracking_events as source_event on source_event.id = pending.event_id
  $view$, definition);
  revoke all on public.pending_live_activity_events from public, anon, authenticated;
  grant select on public.pending_live_activity_events to service_role;
end;
$$;
notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261113052000_live_activity_clock') on conflict do nothing;
