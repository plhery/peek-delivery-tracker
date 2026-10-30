-- Append each scan's place among those its source dated alike, so a batch announces the newest of
-- one instant and stage, without changing the queues' filters.
do $$
declare
  queue text;
  definition text;
begin
  perform set_config('search_path', '', true);
  foreach queue in array array[
    'pending_push_notifications', 'pending_native_push_notifications', 'pending_live_activity_events',
    'pending_parcel_link_alerts'
  ] loop
    if to_regclass('public.' || queue) is null then continue; end if;
    if exists (
      select 1 from information_schema.columns
      where table_schema = 'public' and table_name = queue and column_name = 'tie_order'
    ) then continue; end if;
    definition := regexp_replace(pg_get_viewdef(to_regclass('public.' || queue), true), ';\s*$', '');
    execute format($view$
      create or replace view public.%I with (security_invoker = true) as
      select pending.*, source_event.raw_data -> 'tie_order' as tie_order
      from (%s) as pending
      join public.tracking_events as source_event on source_event.id = pending.event_id
    $view$, queue, definition);
    execute format('revoke all on public.%I from public, anon, authenticated', queue);
    execute format('grant select on public.%I to service_role', queue);
  end loop;
end;
$$;
notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261030024800_notification_tie_order') on conflict do nothing;
