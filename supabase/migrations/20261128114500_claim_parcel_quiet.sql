-- A parcel followed on a device without an account joins the account as quiet or archived as it was there, in the
-- claim itself (`claim_parcel_link`'s `p_muted` and `p_archived`, see 20261125200000_parcel_notes, the last that
-- defined it). The apps asked for each after the claim answered, and a request that failed then, or an app closed in
-- between, left the parcel's alerts on in the account once the device had let go of it. A parcel the account had
-- already keeps its own settings.
--
-- Apply this before deploying the server that uses it. The server before it keeps working: it sends neither, and they
-- default to false.

drop function public.claim_parcel_link(text, text, text, text);
create function public.claim_parcel_link(
  p_link_id text,
  p_owner_key_hash text default null,
  p_label text default '',
  p_note text default null,
  p_muted boolean default false,
  p_archived boolean default false
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  actor_id uuid := auth.uid();
  normalized_label text := btrim(coalesce(p_label, ''));
  normalized_note text := regexp_replace(coalesce(p_note, ''), '^[[:space:]]+|[[:space:]]+$', '', 'g');
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
  if char_length(normalized_note) > 1000 then
    raise exception 'Notes can be at most 1,000 characters' using errcode = '22023';
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
    -- The device's note joins the account's own, after it, unless the account's holds it already.
    if normalized_note <> '' then
      insert into public.package_notes as kept (package_id, user_id, body)
      values (owned_id, actor_id, normalized_note)
      on conflict (package_id) do update
        set body = regexp_replace(left(kept.body || E'\n\n' || excluded.body, 1000), '[[:space:]]+$', ''), updated_at = now()
        where position(excluded.body in kept.body) = 0;
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
    set user_id = actor_id, one_off = false, label = normalized_label,
      notifications_muted = notifications_muted or coalesce(p_muted, false),
      archived_at = case when coalesce(p_archived, false) then coalesce(archived_at, now()) else archived_at end
    where id = source.id;
    -- A refresh already queued now answers to the account's job status.
    update public.sync_jobs set user_id = actor_id
    where package_id = source.id and user_id is null;
    update public.parcel_links
    set created_by = actor_id, owner_key_hash = null
    where id = link.id;
    if normalized_note <> '' then
      insert into public.package_notes (package_id, user_id, body) values (source.id, actor_id, normalized_note);
    end if;
    return jsonb_build_object('outcome', 'kept', 'package_id', source.id);
  end if;

  insert into public.packages (
    user_id, tracking_number, label, carrier, tracking_url, dpd_postcode, phone_digits,
    current_stage, expected_delivery, last_status_text,
    last_synced_at, sync_status, sync_error, carrier_data, notifications_muted, archived_at
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
    case when key_holder then source.carrier_data else '{}'::jsonb end,
    coalesce(p_muted, false),
    case when coalesce(p_archived, false) then now() end
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
  if normalized_note <> '' then
    insert into public.package_notes (package_id, user_id, body) values (kept_id, actor_id, normalized_note);
  end if;
  return jsonb_build_object('outcome', 'kept', 'package_id', kept_id);
end;
$$;

revoke all on function public.claim_parcel_link(text, text, text, text, boolean, boolean) from public, anon, service_role;
grant execute on function public.claim_parcel_link(text, text, text, text, boolean, boolean) to authenticated;

comment on function public.claim_parcel_link(text, text, text, text, boolean, boolean) is
  'Quota-enforced keeping of a parcel link in the current permanent account, with the note it had on the device, quiet or archived as it was there.';

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261128114500_claim_parcel_quiet') on conflict do nothing;
