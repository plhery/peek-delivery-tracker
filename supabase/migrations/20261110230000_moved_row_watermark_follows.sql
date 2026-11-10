-- A clock fix moving a parcel's newest scan row later (the replay tool, a hand fix) released the
-- parcel's watermark to the newest row before the row's old instant: the watermark fell behind the
-- parcel's own newest scan, and when no source answered with that scan any more, as when another
-- provider serves the parcel, nothing moved it on. The watermark now follows the row to its new
-- instant, one already past. A row moved into the future, or earlier, still leaves it at the newest
-- scan row before its old instant. The sync's own watermark still wins: apply_tracking_sync writes
-- the parcel after its rows.
create or replace function public.release_moved_event_watermarks()
returns trigger
language plpgsql
security definer
set search_path = pg_catalog
as $$
begin
  with followed as (
    select before.package_id, before.occurred_at as mark, max(after.occurred_at) as moved_to
    from old_events before
    join new_events after on after.id = before.id
    where public.is_scan_row(before.provider_event_id, before.raw_data)
      and public.is_scan_row(after.provider_event_id, after.raw_data)
      and after.package_id = before.package_id
      and after.occurred_at > before.occurred_at and after.occurred_at <= now()
    group by before.package_id, before.occurred_at
  )
  update public.packages parcel set carrier_data = jsonb_set(parcel.carrier_data, '{routing,last_event_at}',
    to_jsonb(to_char(followed.moved_to at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS.MS"Z"')))
  from followed
  where parcel.id = followed.package_id and public.routing_watermark(parcel.carrier_data) = followed.mark;

  perform public.release_dropped_watermarks(array_agg(before.package_id), array_agg(before.occurred_at))
  from old_events before
  join new_events after on after.id = before.id
  where public.is_scan_row(before.provider_event_id, before.raw_data)
    and ((after.package_id, after.occurred_at) is distinct from (before.package_id, before.occurred_at)
      or not public.is_scan_row(after.provider_event_id, after.raw_data));
  return null;
end;
$$;

insert into public.applied_migrations (name) values ('20261110230000_moved_row_watermark_follows') on conflict do nothing;
