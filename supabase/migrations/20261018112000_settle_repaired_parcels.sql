-- private.settle_repaired_parcels(ids) settles what a manual data repair leaves behind once
-- it has moved, rewritten or deleted a parcel's rows (docs/ROUTING.md): the freshness
-- watermark (routing.last_event_at) becomes the newest scan left, neither the app's rows
-- ("Tracking added") nor a row stamped when it was observed, at most now; without one it goes.
-- A summary whose stage no row left shows takes the newest row's stage and words, or waits
-- again without one. The next check refreshes the rest of the summary. It returns each
-- parcel's stage and watermark before and after, so a repair can be dry-run in a transaction
-- that is rolled back.


create function private.settle_repaired_parcels(p_package_ids uuid[])
returns table (package_id uuid, stage_before text, stage_after text, watermark_before text, watermark_after text)
language sql
set search_path = pg_catalog
as $$
  with left_over as (
    select parcel.id, parcel.current_stage, parcel.carrier_data->'routing'->>'last_event_at' as watermark,
      (select max(least(event.occurred_at, now())) from public.tracking_events as event
        where event.package_id = parcel.id and event.provider_event_id not like 'app:%'
          and coalesce(event.raw_data->>'observed_without_provider_timestamp', 'false') <> 'true') as newest_scan,
      -- The app's "Tracking added" row shows a parcel waiting for its first scan.
      exists (select 1 from public.tracking_events as event
        where event.package_id = parcel.id and event.stage = parcel.current_stage) as stage_shown,
      newest.stage as newest_stage, newest.description as newest_words
    from public.packages as parcel
    left join lateral (
      select event.stage, event.description from public.tracking_events as event
      where event.package_id = parcel.id and event.provider_event_id not like 'app:%'
      order by event.occurred_at desc, event.created_at desc, event.id desc
      limit 1
    ) as newest on true
    where parcel.id = any(p_package_ids)
  )
  update public.packages as parcel set
    current_stage = case when settled.stage_shown then parcel.current_stage else coalesce(settled.newest_stage, 'pending') end,
    last_status_text = case when settled.stage_shown then parcel.last_status_text else settled.newest_words end,
    carrier_data = case
      when jsonb_typeof(parcel.carrier_data->'routing') is distinct from 'object' then parcel.carrier_data
      when settled.newest_scan is null then jsonb_set(parcel.carrier_data, '{routing}', (parcel.carrier_data->'routing') - 'last_event_at')
      else jsonb_set(parcel.carrier_data, '{routing,last_event_at}',
        to_jsonb(to_char(settled.newest_scan at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS.MS"Z"'))) end
  from left_over as settled
  where parcel.id = settled.id
  returning parcel.id, settled.current_stage, parcel.current_stage, settled.watermark,
    parcel.carrier_data->'routing'->>'last_event_at';
$$;

comment on function private.settle_repaired_parcels(uuid[]) is
  'After a manual repair of parcels'' rows: the watermark becomes the newest scan left, and a summary whose stage no row left shows takes the newest row''s. Returns each parcel''s stage and watermark before and after.';

revoke all on function private.settle_repaired_parcels(uuid[]) from public, anon, authenticated;

insert into public.applied_migrations (name) values ('20261018112000_settle_repaired_parcels') on conflict do nothing;

