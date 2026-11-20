-- The owner of a lookup adds the delivery postcode that another carrier holding
-- the parcel asks for (routing.input_needed). The server sends it to that
-- carrier first. A postcode the carrier refuses stays counted against the link,
-- so the prompt cannot be used to guess a recipient's postcode. A postcode it
-- accepts files the link under that carrier: the link moves to the parcel with
-- that carrier and postcode, found or created as a lookup would. The parcel the
-- link leaves stays as it is for its other links, and goes with its last one.
--
-- Apply this before deploying the server that calls these functions.


create table public.parcel_link_postcode_attempts (
  id uuid primary key default gen_random_uuid(),
  link_id text not null references public.parcel_links (id) on delete cascade,
  attempted_at timestamptz not null default now()
);

create index parcel_link_postcode_attempts_link_idx
  on public.parcel_link_postcode_attempts (link_id, attempted_at);

alter table public.parcel_link_postcode_attempts enable row level security;
revoke all on public.parcel_link_postcode_attempts from public, anon, authenticated;
grant select, insert, update, delete on public.parcel_link_postcode_attempts to service_role;

comment on table public.parcel_link_postcode_attempts is
  'Postcodes a lookup''s owner sent a carrier that it refused or is still checking, for a day. Never the postcode itself. Service role only.';

-- A lookup that another carrier holds and that waits for its delivery postcode.
create function private.waits_for_link_postcode(p_package public.packages, p_carrier text)
returns boolean
language sql
stable
security definer
set search_path = ''
as $$
  select coalesce(
    p_package.one_off
      and p_package.user_id is null
      and p_package.carrier <> p_carrier
      and p_package.current_stage not in ('delivered', 'returned')
      and p_package.carrier_data #>> '{routing,input_needed,carrier}' = p_carrier
      and p_package.carrier_data #>> '{routing,input_needed,field}' = 'dpdPostcode',
    false
  );
$$;

revoke all on function private.waits_for_link_postcode(public.packages, text) from public, anon, authenticated;

-- Counts a postcode about to be sent to the carrier a lookup waits for, for the
-- holder of its owner key. A link has p_limit tries a day: each one the carrier
-- refuses stays counted for a day, and the server gives back the others. An
-- unknown link and a wrong key answer null; a lookup that waits for no postcode
-- from that carrier answers asked = false; a link out of tries answers when the
-- next one is allowed.
create function public.claim_parcel_link_postcode(
  p_link_id text,
  p_owner_key_hash text,
  p_carrier text,
  p_limit integer
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  link public.parcel_links;
  package public.packages;
  used integer;
  oldest timestamptz;
  attempt_id uuid;
begin
  if p_owner_key_hash is null or p_carrier is null or p_limit is null or p_limit < 1 then
    raise exception 'Invalid postcode claim' using errcode = '22023';
  end if;
  -- One claim at a time per link, so the count below holds.
  select * into link from public.parcel_links
  where id = p_link_id and owner_key_hash = p_owner_key_hash
  for update;
  if not found or public.parcel_link_forget_at(link) <= now() then return null; end if;
  select * into package from public.packages where id = link.package_id;
  if not private.waits_for_link_postcode(package, p_carrier) then
    return jsonb_build_object('asked', false);
  end if;

  delete from public.parcel_link_postcode_attempts
  where link_id = link.id and attempted_at <= now() - interval '1 day';
  select count(*), min(attempted_at) into used, oldest
  from public.parcel_link_postcode_attempts
  where link_id = link.id;
  if used >= p_limit then
    return jsonb_build_object('asked', true, 'retry_at', oldest + interval '1 day');
  end if;

  insert into public.parcel_link_postcode_attempts (link_id)
  values (link.id)
  returning id into attempt_id;
  return jsonb_build_object('asked', true, 'attempt', attempt_id, 'tracking_number', package.tracking_number);
end;
$$;

-- Gives back a try the carrier did not refuse: it accepted the postcode, or
-- could not be asked.
create function public.release_parcel_link_postcode(p_attempt_id uuid)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
begin
  delete from public.parcel_link_postcode_attempts where id = p_attempt_id;
  return found;
end;
$$;

-- Files a lookup under the carrier that accepted its delivery postcode, for the
-- holder of its owner key. The link moves to the lookup's parcel with that
-- carrier and postcode, which other lookups may share, or to a new one; the
-- parcel it leaves goes when no link reaches it any more. Answers the link as
-- public_parcel does, with created and the parcel left, null for an unknown
-- link or a wrong key, and asked = false when the lookup waits for no postcode
-- from that carrier any more.
create function public.file_parcel_link_postcode(
  p_link_id text,
  p_owner_key_hash text,
  p_carrier text,
  p_dpd_postcode text
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  normalized_postcode text := nullif(btrim(coalesce(p_dpd_postcode, '')), '');
  link public.parcel_links;
  previous public.packages;
  target_id uuid;
  created boolean := false;
  orphaned boolean;
begin
  if p_owner_key_hash is null or p_carrier is null or normalized_postcode is null then
    raise exception 'Invalid postcode filing' using errcode = '22023';
  end if;
  select parcel.tracking_number into previous.tracking_number
  from public.parcel_links as stored
  join public.packages as parcel on parcel.id = stored.package_id
  where stored.id = p_link_id and stored.owner_key_hash = p_owner_key_hash;
  if not found then return null; end if;

  -- Under the number's lock, as a lookup and a forgetting take it.
  perform private.lock_one_off_number(previous.tracking_number);
  select * into link from public.parcel_links
  where id = p_link_id and owner_key_hash = p_owner_key_hash
  for update;
  if not found or public.parcel_link_forget_at(link) <= now() then return null; end if;
  select * into previous from public.packages where id = link.package_id;
  if not private.waits_for_link_postcode(previous, p_carrier) then
    return jsonb_build_object('asked', false);
  end if;

  select package.id into target_id
  from public.packages as package
  where package.one_off
    and package.user_id is null
    and package.tracking_number = previous.tracking_number
    and (package.carrier = p_carrier or package.carrier_data->>'auto_changed_from' = p_carrier)
    and package.tracking_url is null
    and package.dpd_postcode = normalized_postcode
  order by package.created_at desc
  limit 1;

  if target_id is null then
    insert into public.packages (user_id, one_off, tracking_number, label, carrier, tracking_url, dpd_postcode)
    values (null, true, previous.tracking_number, '', p_carrier, null, normalized_postcode)
    returning id into target_id;
    created := true;
  end if;

  update public.parcel_links set package_id = target_id where id = link.id;
  delete from public.parcel_link_postcode_attempts where link_id = link.id;
  orphaned := private.forget_orphaned_one_off(previous.id);

  return public.public_parcel(link.id, p_owner_key_hash)
    || jsonb_build_object('created', created, 'previous_forgotten', orphaned);
end;
$$;

revoke all on function public.claim_parcel_link_postcode(text, text, text, integer) from public, anon, authenticated;
revoke all on function public.release_parcel_link_postcode(uuid) from public, anon, authenticated;
revoke all on function public.file_parcel_link_postcode(text, text, text, text) from public, anon, authenticated;
grant execute on function public.claim_parcel_link_postcode(text, text, text, integer) to service_role;
grant execute on function public.release_parcel_link_postcode(uuid) to service_role;
grant execute on function public.file_parcel_link_postcode(text, text, text, text) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261024070000_parcel_link_postcodes') on conflict do nothing;

