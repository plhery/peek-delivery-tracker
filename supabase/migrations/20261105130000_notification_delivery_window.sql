-- The queues carry the start of a delivery window, so a notification can say "by Friday" or
-- "4–6 November" instead of naming the window's last day as if it were the day.
do $$
declare
  queue text;
  definition text;
begin
  perform set_config('search_path', '', true);
  foreach queue in array array[
    'pending_push_notifications',
    'pending_native_push_notifications',
    'pending_parcel_link_alerts',
    'pending_live_activity_events'
  ] loop
    if exists (
      select 1 from information_schema.columns
      where table_schema = 'public' and table_name = queue and column_name = 'expected_delivery_from'
    ) then continue; end if;
    definition := regexp_replace(
      pg_get_viewdef(to_regclass('public.' || queue), true), ';\s*$', '');
    execute format($view$
      create or replace view public.%I with (security_invoker = true) as
      select pending.*, window_package.carrier_data ->> 'expected_delivery_from' as expected_delivery_from
      from (%s) as pending
      left join public.packages as window_package on window_package.id = pending.package_id
    $view$, queue, definition);
    execute format('revoke all on public.%I from public, anon, authenticated', queue);
    execute format('grant select on public.%I to service_role', queue);
  end loop;
end;
$$;

insert into public.applied_migrations (name) values ('20261105130000_notification_delivery_window')
on conflict do nothing;
