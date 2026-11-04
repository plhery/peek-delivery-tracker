-- Append each scan's time as its source wrote it, and whether the account chose a time zone, so a
-- notification can tell a delivery's time on the scan's own clock when nobody chose one. The queues'
-- filters do not change.
do $$
declare
  queue text;
  definition text;
begin
  perform set_config('search_path', '', true);
  foreach queue in array array[
    'pending_push_notifications', 'pending_native_push_notifications', 'pending_parcel_link_alerts'
  ] loop
    if to_regclass('public.' || queue) is null then continue; end if;
    if exists (
      select 1 from information_schema.columns
      where table_schema = 'public' and table_name = queue and column_name = 'event_time'
    ) then continue; end if;
    definition := regexp_replace(pg_get_viewdef(to_regclass('public.' || queue), true), ';\s*$', '');
    execute format($view$
      create or replace view public.%I with (security_invoker = true) as
      select pending.*, source_event.raw_data ->> 'time' as event_time%s
      from (%s) as pending
      join public.tracking_events as source_event on source_event.id = pending.event_id
    $view$, queue,
      -- A link's alert belongs to whoever opened the link, not to the parcel's account.
      case when queue = 'pending_parcel_link_alerts' then '' else $chosen$,
        exists (
          select 1 from public.packages as package
          join public.notification_preferences as preference on preference.user_id = package.user_id
          where package.id = pending.package_id
        ) as timezone_chosen$chosen$ end,
      definition);
    execute format('revoke all on public.%I from public, anon, authenticated', queue);
    execute format('grant select on public.%I to service_role', queue);
  end loop;
end;
$$;
notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261104150000_notification_scan_clock') on conflict do nothing;
