-- After its server.
-- A link's package lists the columns the server reads (publicParcelResponse and the routes under
-- /api/public/parcels) instead of the whole row (`to_jsonb(package)`), so a column added to packages later, a note
-- or anything else its owner keeps, never reaches a link unless it is named here. The account's id becomes whether
-- the parcel is a lookup, which Peek AI reads (`lookup`); the server reads it from this commit on, so it follows its
-- server. The owner's name for the parcel, the account and its email and notification settings, when the owner last
-- opened the parcel and the sync's bookkeeping stay out. supabase/tests/parcel_sharing.sql holds the list. The
-- function is built on 20261121140500_link_peek_ai_fields, the last that defined it.


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
    -- The columns the server reads, never the whole row: whatever a column added to packages holds stays out.
    'package', jsonb_build_object(
      'id', package.id,
      'tracking_number', package.tracking_number,
      'carrier', package.carrier,
      'created_at', package.created_at,
      'current_stage', package.current_stage,
      'expected_delivery', package.expected_delivery,
      'last_status_text', package.last_status_text,
      'last_synced_at', package.last_synced_at,
      'sync_status', package.sync_status,
      'sync_error', package.sync_error,
      'tracking_url', package.tracking_url,
      'dpd_postcode', package.dpd_postcode,
      'phone_digits', package.phone_digits,
      'carrier_data', package.carrier_data,
      'archived_at', package.archived_at,
      'one_off', package.one_off,
      'lookup', package.user_id is null,
      'tracking_events', coalesce((
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
        'retimed_from', event.raw_data->>'retimed_from',
        'provider_code', event.raw_data->>'provider_code',
        'written_at', event.raw_data->>'written_at',
        'stored_at', event.created_at
      ) order by event.occurred_at desc)
      from public.tracking_events as event
      where event.package_id = package.id
    ), '[]'::jsonb))
  );
end;
$$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261124161000_link_package_columns') on conflict do nothing;
