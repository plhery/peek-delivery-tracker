-- J&T Express Mexico and J&T Express China show a parcel only with the last four digits of the
-- recipient's or sender's phone. The owner may give them: they are kept with the parcel, go to
-- that carrier with each check and to no one else, and are deleted with the parcel, which goes
-- with its account. Changing the parcel's carrier or number forgets them.
-- Database gate: inputs added j-and-t-cn: phoneDigits, validator phoneLastFour, optional, pattern ^[0-9]{4}$
-- Database gate: inputs added j-and-t-mx: phoneDigits, validator phoneLastFour, optional, pattern ^[0-9]{4}$

alter table public.packages add column phone_digits text;

alter table public.packages add constraint packages_phone_digits_check check (
  phone_digits is null or (phone_digits ~ '^[0-9]{4}$' and carrier in ('j-and-t-mx', 'j-and-t-cn'))
);

-- The digits belong to the carrier and number they were given for. A write that sets them
-- with the new carrier keeps them; change_owned_package_carrier and corrections do not.
create function public.forget_phone_digits()
returns trigger
language plpgsql
set search_path = pg_catalog
as $$
begin
  new.phone_digits := null;
  return new;
end;
$$;

create trigger packages_forget_phone_digits
  before update of carrier, tracking_number
  on public.packages
  for each row
  when (old.phone_digits is not null and new.phone_digits is not distinct from old.phone_digits
    and (old.carrier is distinct from new.carrier or old.tracking_number is distinct from new.tracking_number))
  execute function public.forget_phone_digits();

revoke all on function public.forget_phone_digits() from public, anon, authenticated;

-- New digits are a new input, as a new postcode is: a check that read the parcel before is set
-- aside, and the carrier's wait ends.
drop trigger packages_renew_tracking_generation on public.packages;
create trigger packages_renew_tracking_generation
  before update of carrier, tracking_number, tracking_url, dpd_postcode, phone_digits
  on public.packages
  for each row execute function public.renew_tracking_generation();

drop trigger packages_clear_carrier_wait on public.packages;
create trigger packages_clear_carrier_wait
  before update of tracking_url, dpd_postcode, phone_digits
  on public.packages
  for each row
  when (old.carrier = new.carrier and (old.tracking_url is distinct from new.tracking_url
    or old.dpd_postcode is distinct from new.dpd_postcode or old.phone_digits is distinct from new.phone_digits))
  execute function public.clear_carrier_wait();

-- A lookup without an account shares its parcel only with lookups that gave the same digits.
drop function public.create_one_off_parcel(text, text, text, text, text);
create or replace function public.create_one_off_parcel(
  p_tracking_number text,
  p_carrier text,
  p_tracking_url text,
  p_dpd_postcode text,
  p_owner_key_hash text,
  p_phone_digits text default null
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  normalized_tracking text := upper(regexp_replace(coalesce(p_tracking_number, ''), '[[:space:].-]', '', 'g'));
  normalized_url text := nullif(btrim(coalesce(p_tracking_url, '')), '');
  normalized_postcode text := nullif(btrim(coalesce(p_dpd_postcode, '')), '');
  normalized_digits text := nullif(btrim(coalesce(p_phone_digits, '')), '');
  target_id uuid;
  link_id text;
  created boolean := false;
begin
  if char_length(normalized_tracking) not between 4 and 40
      or (normalized_tracking !~ '^[A-Z0-9]+$' and normalized_tracking !~ '^\d{4}/\d{8}$')
      or (normalized_tracking !~ '[0-9]' and normalized_tracking !~ '^[A-Z]{6,}$')
      or p_carrier is null
      or p_owner_key_hash is null then
    raise exception 'Invalid one-off parcel' using errcode = '22023';
  end if;

  perform private.lock_one_off_number(normalized_tracking);
  select package.id into target_id
  from public.packages as package
  where package.one_off
    and package.user_id is null
    and package.tracking_number = normalized_tracking
    and (package.carrier = p_carrier or package.carrier_data->>'auto_changed_from' = p_carrier)
    and package.tracking_url is not distinct from normalized_url
    and package.dpd_postcode is not distinct from normalized_postcode
    and package.phone_digits is not distinct from normalized_digits
  order by package.created_at desc
  limit 1;

  if target_id is null then
    insert into public.packages (user_id, one_off, tracking_number, label, carrier, tracking_url, dpd_postcode, phone_digits)
    values (null, true, normalized_tracking, '', p_carrier, normalized_url, normalized_postcode, normalized_digits)
    returning id into target_id;
    created := true;
  end if;

  insert into public.parcel_links (package_id, owner_key_hash)
  values (target_id, p_owner_key_hash)
  returning id into link_id;

  return public.public_parcel(link_id, p_owner_key_hash) || jsonb_build_object('created', created);
end;
$$;

revoke all on function public.create_one_off_parcel(text, text, text, text, text, text) from public, anon, authenticated;
grant execute on function public.create_one_off_parcel(text, text, text, text, text, text) to service_role;

-- Keeps a parcel from a link in the signed-in account. The device that made
-- the lookup proves it with its key; anyone else may keep a parcel whose link
-- shows its number, is still shared and is not a gift on its way. Every other
-- case answers like an unknown link.
--
-- The key holder of a one-off parcel nobody else follows takes the parcel
-- itself. Otherwise the account gets its own copy: the key holder's with the
-- inputs and routing state the lookup had, a viewer's with the number, carrier
-- and visible history only, never the postcode, phone digits or private link
-- someone else entered. The key holder's link then belongs to the account and loses its key.
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
    source.current_stage,
    source.expected_delivery,
    source.last_status_text,
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
  where event.package_id = source.id
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

insert into public.applied_migrations (name) values ('20261108113000_j_and_t_phone_digits') on conflict do nothing;
