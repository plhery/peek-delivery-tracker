-- A link's events say which rows are stored as notices their carrier's map says keep the stage of the scan before
-- them (`kept_stage`, keptNotices in disputedDelivery.ts), as an account's parcel's events do (PACKAGE_SELECT,
-- supabase.ts), so the server shows a link's notice in transit under its scan too. The function is built on
-- 20261115190000_link_event_sources, the last that defined it.
--
-- The server reads the field as soon as the view gives it.

begin;

create or replace function public.parcel_link_view(
  p_link_id text,
  p_owner_key_hash text default null,
  p_touch boolean default false
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  link public.parcel_links;
  package public.packages;
  forget_at timestamptz;
  is_owner boolean;
begin
  select * into link from public.parcel_links where id = p_link_id;
  if not found then return null; end if;
  forget_at := public.parcel_link_forget_at(link);
  if forget_at <= now() then return null; end if;
  is_owner := coalesce(link.owner_key_hash = p_owner_key_hash, false);
  if not link.shared and not is_owner then
    return jsonb_build_object('stopped', true);
  end if;
  if p_touch and link.last_opened_at < now() - interval '5 minutes' then
    update public.parcel_links set last_opened_at = now() where id = link.id
    returning * into link;
    forget_at := public.parcel_link_forget_at(link);
  end if;
  select * into package from public.packages where id = link.package_id;

  return jsonb_build_object(
    'link', jsonb_build_object(
      'id', link.id,
      'owner', is_owner,
      'shared', link.created_by is not null,
      'show_number', link.show_number,
      'gift', link.gift,
      'gift_words', case when is_owner or (link.gift and package.current_stage = 'delivered') then link.gift_words end,
      'stopped', not link.shared,
      'created_at', link.created_at,
      'forget_at', forget_at
    ),
    'package', to_jsonb(package) || jsonb_build_object('tracking_events', coalesce((
      select jsonb_agg(jsonb_build_object(
        'id', event.id,
        'package_id', event.package_id,
        'stage', event.stage,
        'description', event.description,
        'location', event.location,
        'occurred_at', case when event.provider_event_id like 'app:%'
          then greatest(event.occurred_at, link.created_at) else event.occurred_at end,
        'provider_event_id', event.provider_event_id,
        'point', event.raw_data->'point',
        'time', event.raw_data->>'time',
        'local_time', event.raw_data->>'local_time',
        'observed', event.raw_data->'observed_without_provider_timestamp',
        'universal_provider', event.raw_data->>'universal_provider',
        'recipient_action', event.raw_data->>'recipient_action',
        'tie_order', event.raw_data->'tie_order',
        'stage_source', event.raw_data->>'stage_source',
        'kept_stage', event.raw_data->'kept_stage',
        'stored_at', event.created_at
      ) order by event.occurred_at desc)
      from public.tracking_events as event
      where event.package_id = package.id
    ), '[]'::jsonb))
  );
end;
$$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261117150000_link_kept_notices') on conflict do nothing;

commit;
