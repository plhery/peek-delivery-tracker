-- A viewer who keeps a shared parcel gets its number and carrier, not its history.
--
-- A viewer's copy took the shared parcel's status line and every scan as stored,
-- with their raw carrier answers: the scans and wording a shared link hides from
-- its viewers (who took the parcel, where it was left, its pickup code), and
-- whatever the carrier answered only to the owner's postcode or phone digits.
-- The link's server filter can't run here, so a viewer's copy now starts as a new
-- lookup of the number: stage, estimate and status line empty, no scans but its
-- own "Tracking added" row. The keep queues the copy's first check, which fills
-- what the carrier tells anyone with the number. The key holder and the link's
-- own account still take the parcel's history, as before.
create or replace function public.claim_parcel_link(
  p_link_id text,
  p_owner_key_hash text default null,
  p_label text default ''
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  actor_id uuid := auth.uid();
  normalized_label text := btrim(coalesce(p_label, ''));
  link public.parcel_links;
  source public.packages;
  key_holder boolean;
  -- The key holder or the account that made the lookup: who read the parcel as its owner.
  own_lookup boolean;
  owned_id uuid;
  active_count integer;
  total_count integer;
  kept_id uuid;
begin
  if actor_id is null or exists (
    select 1 from auth.users where id = actor_id and is_anonymous
  ) then
    raise exception 'A permanent account is required' using errcode = '42501';
  end if;
  if char_length(normalized_label) > 80 then
    raise exception 'Parcel names can be at most 80 characters' using errcode = '22023';
  end if;

  -- The account lock of create_owned_package: quota checks run one at a time.
  perform pg_advisory_xact_lock(hashtextextended(actor_id::text, 0));

  select package.* into source
  from public.parcel_links as candidate
  join public.packages as package on package.id = candidate.package_id
  where candidate.id = p_link_id;
  if not found then
    raise exception 'Parcel unavailable' using errcode = 'P0002';
  end if;
  perform private.lock_one_off_number(source.tracking_number);
  select * into link from public.parcel_links where id = p_link_id for update;
  key_holder := found and coalesce(link.owner_key_hash = p_owner_key_hash, false);
  own_lookup := key_holder or link.created_by is not distinct from actor_id;
  if not found
      or link.package_id <> source.id
      or not (
        key_holder
        or link.created_by is not distinct from actor_id
        -- A viewer keeps what the link shows: not a stopped link, not a gift before it arrives.
        or (
          link.show_number
          and link.shared
          and not (link.gift and source.current_stage <> 'delivered')
        )
      )
      or public.parcel_link_forget_at(link) <= now() then
    raise exception 'Parcel unavailable' using errcode = 'P0002';
  end if;

  select package.id into owned_id
  from public.packages as package
  where package.user_id = actor_id and package.tracking_number = source.tracking_number;
  if owned_id is not null then
    if key_holder then
      delete from public.parcel_links where id = link.id;
      perform private.forget_orphaned_one_off(source.id);
    end if;
    return jsonb_build_object('outcome', 'already', 'package_id', owned_id);
  end if;

  select count(*) filter (where archived_at is null), count(*)
  into active_count, total_count
  from public.packages
  where user_id = actor_id;
  if active_count >= 50 or total_count >= 500 then
    return jsonb_build_object('outcome', 'quota', 'package_id', null);
  end if;

  if key_holder and source.one_off and source.user_id is null and not exists (
    select 1 from public.parcel_links as other
    where other.package_id = source.id and other.id <> link.id
  ) then
    update public.packages
    set user_id = actor_id, one_off = false, label = normalized_label
    where id = source.id;
    -- A refresh already queued now answers to the account's job status.
    update public.sync_jobs set user_id = actor_id
    where package_id = source.id and user_id is null;
    update public.parcel_links
    set created_by = actor_id, owner_key_hash = null
    where id = link.id;
    return jsonb_build_object('outcome', 'kept', 'package_id', source.id);
  end if;

  insert into public.packages (
    user_id, tracking_number, label, carrier, tracking_url, dpd_postcode, phone_digits,
    current_stage, expected_delivery, last_status_text,
    last_synced_at, sync_status, sync_error, carrier_data
  ) values (
    actor_id,
    source.tracking_number,
    normalized_label,
    -- Dachser cannot be filed without its private link.
    case when not key_holder and source.carrier = 'dachser' then 'unknown' else source.carrier end,
    case when key_holder then source.tracking_url end,
    case when key_holder then source.dpd_postcode end,
    case when key_holder then source.phone_digits end,
    -- A viewer's copy starts as a new lookup: its first check fills these.
    case when own_lookup then source.current_stage else 'pending' end,
    case when own_lookup then source.expected_delivery end,
    case when own_lookup then source.last_status_text end,
    case when key_holder then source.last_synced_at end,
    -- A check of the source still running says nothing about the copy.
    case when key_holder and source.sync_status <> 'syncing' then source.sync_status else 'pending' end,
    case when key_holder then source.sync_error end,
    case when key_holder then source.carrier_data else '{}'::jsonb end
  )
  returning id into kept_id;

  insert into public.tracking_events (
    package_id, provider_event_id, stage, description, location, occurred_at, raw_data
  )
  select kept_id, event.provider_event_id, event.stage, event.description,
    event.location, event.occurred_at, event.raw_data
  from public.tracking_events as event
  where event.package_id = source.id and own_lookup
  -- The copy already has its own "Tracking added" row.
  on conflict (package_id, provider_event_id) do nothing;

  if key_holder then
    update public.parcel_links
    set package_id = kept_id, created_by = actor_id, owner_key_hash = null
    where id = link.id;
    -- The copied scans are not news to the link's alerts.
    update public.parcel_link_alerts set created_at = now() where link_id = link.id;
    perform private.forget_orphaned_one_off(source.id);
  end if;
  return jsonb_build_object('outcome', 'kept', 'package_id', kept_id);
end;
$$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261116094500_viewer_keep_without_scans') on conflict do nothing;
