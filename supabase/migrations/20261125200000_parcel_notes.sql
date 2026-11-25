-- A private note on each of an account's parcels: up to 1,000 characters and up to five photos or PDFs, read by its
-- owner alone. The owner reads the note and the files' details under its own token; it writes them only through the
-- functions below, which check that the parcel is the account's own. The files' bytes are kept by the service role,
-- which serves them through signed addresses (src/server/parcelNotes.ts). Nothing here reaches a link:
-- parcel_link_view lists its columns. Merging two legs joins their notes and keeps the files, keeping a parcel from a
-- link takes the device's note along, and deleting the parcel or the account deletes them.
--
-- A gift's sender may show its note and who it is from before delivery (`gift_words.early`); the name of what is
-- inside still waits for delivery. A running server reads no early words: it serves a wrapped gift's link none.

create table public.package_notes (
  package_id uuid primary key references public.packages (id) on delete cascade,
  user_id uuid not null references auth.users (id) on delete cascade,
  body text not null check (char_length(body) between 1 and 1000 and body ~ '[^[:space:]]'),
  updated_at timestamptz not null default now()
);
create index package_notes_user_id_idx on public.package_notes (user_id);

create table public.package_files (
  id uuid primary key default gen_random_uuid(),
  package_id uuid not null references public.packages (id) on delete cascade,
  user_id uuid not null references auth.users (id) on delete cascade,
  name text not null check (char_length(name) between 1 and 120),
  content_type text not null check (content_type in ('image/jpeg', 'image/png', 'image/webp', 'application/pdf')),
  size integer not null check (size between 1 and 10485760),
  width integer check (width > 0),
  height integer check (height > 0),
  created_at timestamptz not null default now()
);
create index package_files_package_id_idx on public.package_files (package_id, created_at);
create index package_files_user_id_idx on public.package_files (user_id);

-- The bytes, apart from the rows the owner reads: only the service role reads them. They are compressed already.
create table public.package_file_contents (
  file_id uuid primary key references public.package_files (id) on delete cascade,
  content bytea not null,
  thumbnail bytea
);
alter table public.package_file_contents
  alter column content set storage external,
  alter column thumbnail set storage external;

alter table public.package_notes enable row level security;
alter table public.package_files enable row level security;
alter table public.package_file_contents enable row level security;
create policy "select own package notes" on public.package_notes
  for select to authenticated
  using (user_id = auth.uid());
create policy "select own package files" on public.package_files
  for select to authenticated
  using (user_id = auth.uid());

revoke all on public.package_notes, public.package_files, public.package_file_contents from public, anon, authenticated;
grant select on public.package_notes, public.package_files to authenticated;
grant all on public.package_notes, public.package_files, public.package_file_contents to service_role;

-- The owner's note on its parcel: null or blank deletes it. False when the parcel is not the caller's.
create function public.set_package_note(p_package_id uuid, p_body text)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
declare
  actor_id uuid := auth.uid();
  body text := regexp_replace(coalesce(p_body, ''), '^[[:space:]]+|[[:space:]]+$', '', 'g');
begin
  if actor_id is null then
    raise exception 'A sign-in is required' using errcode = '42501';
  end if;
  if char_length(body) > 1000 then
    raise exception 'Notes can be at most 1,000 characters' using errcode = '22023';
  end if;
  if not exists (select 1 from public.packages where id = p_package_id and user_id = actor_id) then
    return false;
  end if;
  if body = '' then
    delete from public.package_notes where package_id = p_package_id;
  else
    insert into public.package_notes (package_id, user_id, body)
    values (p_package_id, actor_id, body)
    on conflict (package_id) do update set body = excluded.body, updated_at = now();
  end if;
  return true;
end;
$$;

-- Adds a file to the owner's note on its parcel, its bytes and a photo's thumbnail in base64. The server re-encodes a
-- photo first; this checks what it stores: the type by its first bytes, 10 MB a file (PF003), 5 a parcel (PF001) and
-- 200 MB an account (PF002), one upload of the account at a time.
create function public.add_package_file(
  p_package_id uuid,
  p_name text,
  p_content_type text,
  p_content text,
  p_thumbnail text default null,
  p_width integer default null,
  p_height integer default null
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  actor_id uuid := auth.uid();
  file_name text := btrim(coalesce(p_name, ''));
  content bytea;
  thumbnail bytea;
  -- Whether the content's first bytes are those of its type.
  typed boolean;
  added public.package_files;
begin
  if actor_id is null then
    raise exception 'A sign-in is required' using errcode = '42501';
  end if;
  if char_length(file_name) not between 1 and 120 then
    raise exception 'Invalid file name' using errcode = '22023';
  end if;
  content := decode(coalesce(p_content, ''), 'base64');
  thumbnail := decode(p_thumbnail, 'base64');
  if octet_length(content) > 10485760 then
    raise exception 'Files can be at most 10 MB' using errcode = 'PF003';
  end if;
  typed := case p_content_type
    when 'image/jpeg' then substring(content from 1 for 3) = '\xffd8ff'::bytea
    when 'image/png' then substring(content from 1 for 8) = '\x89504e470d0a1a0a'::bytea
    when 'image/webp' then substring(content from 1 for 4) = '\x52494646'::bytea
      and substring(content from 9 for 4) = '\x57454250'::bytea
    when 'application/pdf' then substring(content from 1 for 5) = '\x255044462d'::bytea
    else false
  end;
  if not typed then
    raise exception 'Not a photo or PDF of its type' using errcode = 'PF004';
  end if;
  if thumbnail is not null and (
    p_content_type = 'application/pdf'
    or octet_length(thumbnail) > 1048576
    or not (substring(thumbnail from 1 for 3) = '\xffd8ff'::bytea
      or (substring(thumbnail from 1 for 4) = '\x52494646'::bytea and substring(thumbnail from 9 for 4) = '\x57454250'::bytea))
  ) then
    raise exception 'Invalid thumbnail' using errcode = '22023';
  end if;
  if (p_content_type = 'application/pdf' and (p_width is not null or p_height is not null))
      or p_width <= 0 or p_height <= 0 then
    raise exception 'Invalid dimensions' using errcode = '22023';
  end if;

  perform pg_advisory_xact_lock(hashtextextended('package_files:' || actor_id::text, 0));
  -- A merge of the parcel waits for the file, then moves it.
  perform 1 from public.packages where id = p_package_id and user_id = actor_id for share;
  if not found then
    raise exception 'Package not found' using errcode = 'P0002';
  end if;
  if (select count(*) from public.package_files where package_id = p_package_id) >= 5 then
    raise exception 'A parcel holds up to 5 files' using errcode = 'PF001';
  end if;
  if (select coalesce(sum(size), 0) from public.package_files where user_id = actor_id) + octet_length(content) > 209715200 then
    raise exception 'The account''s 200 MB for files are used' using errcode = 'PF002';
  end if;

  insert into public.package_files (package_id, user_id, name, content_type, size, width, height)
  values (p_package_id, actor_id, file_name, p_content_type, octet_length(content), p_width, p_height)
  returning * into added;
  insert into public.package_file_contents (file_id, content, thumbnail) values (added.id, content, thumbnail);
  return jsonb_build_object(
    'id', added.id, 'name', added.name, 'content_type', added.content_type, 'size', added.size,
    'width', added.width, 'height', added.height, 'created_at', added.created_at
  );
end;
$$;

-- Deletes a file of the owner's note. False when it is not the caller's.
create function public.delete_package_file(p_file_id uuid)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
declare
  actor_id uuid := auth.uid();
begin
  if actor_id is null then
    raise exception 'A sign-in is required' using errcode = '42501';
  end if;
  delete from public.package_files where id = p_file_id and user_id = actor_id;
  return found;
end;
$$;

-- A file's bytes, or its thumbnail's, in base64, for the server to serve through a signed address of its owner.
create function public.package_file_content(p_file_id uuid, p_user_id uuid, p_thumbnail boolean default false)
returns text
language sql
stable
security definer
set search_path = ''
as $$
  select encode(case when p_thumbnail then stored.thumbnail else stored.content end, 'base64')
  from public.package_file_contents as stored
  join public.package_files as file on file.id = stored.file_id
  where file.id = p_file_id and file.user_id = p_user_id
$$;

revoke all on function public.set_package_note(uuid, text) from public, anon, service_role;
grant execute on function public.set_package_note(uuid, text) to authenticated;
revoke all on function public.add_package_file(uuid, text, text, text, text, integer, integer) from public, anon, service_role;
grant execute on function public.add_package_file(uuid, text, text, text, text, integer, integer) to authenticated;
revoke all on function public.delete_package_file(uuid) from public, anon, service_role;
grant execute on function public.delete_package_file(uuid) to authenticated;
revoke all on function public.package_file_content(uuid, uuid, boolean) from public, anon, authenticated;
grant execute on function public.package_file_content(uuid, uuid, boolean) to service_role;

-- A gift's words may say that its note and who it is from show before delivery.
alter table public.parcel_links drop constraint parcel_links_gift_words_check;
alter table public.parcel_links add constraint parcel_links_gift_words_check check (
  gift_words is null or (
    jsonb_typeof(gift_words) = 'object'
    and gift_words ?& array['name', 'note', 'from']
    and gift_words - array['name', 'note', 'from', 'early'] = '{}'::jsonb
    and jsonb_typeof(gift_words->'name') in ('string', 'null')
    and jsonb_typeof(gift_words->'note') in ('string', 'null')
    and jsonb_typeof(gift_words->'from') in ('string', 'null')
    and (not gift_words ? 'early' or jsonb_typeof(gift_words->'early') = 'boolean')
    and coalesce(length(gift_words->>'name'), 0) <= 80
    and coalesce(length(gift_words->>'note'), 0) <= 280
    and coalesce(length(gift_words->>'from'), 0) <= 60
  )
);

-- Keeping a parcel from a link takes the note it had on the device (`p_note`); the rest is
-- 20261116094500_viewer_keep_without_scans, the last that defined it.
drop function public.claim_parcel_link(text, text, text);
create function public.claim_parcel_link(
  p_link_id text,
  p_owner_key_hash text default null,
  p_label text default '',
  p_note text default null
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
    set user_id = actor_id, one_off = false, label = normalized_label
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
  if normalized_note <> '' then
    insert into public.package_notes (package_id, user_id, body) values (kept_id, actor_id, normalized_note);
  end if;
  return jsonb_build_object('outcome', 'kept', 'package_id', kept_id);
end;
$$;

revoke all on function public.claim_parcel_link(text, text, text, text) from public, anon, service_role;
grant execute on function public.claim_parcel_link(text, text, text, text) to authenticated;

comment on function public.claim_parcel_link(text, text, text, text) is
  'Quota-enforced keeping of a parcel link in the current permanent account, with the note it had on the device.';

-- A gift whose sender shows its note early gives a viewer its note and who it is from before delivery, never its
-- name. The rest is 20261124161000_link_package_columns, the last that defined it.
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
      'gift_words', case
        when is_owner or (link.gift and package.current_stage = 'delivered') then link.gift_words
        -- Its sender shows the note and who it is from before delivery: what is inside still waits.
        when link.gift and link.gift_words->'early' = 'true'::jsonb then jsonb_build_object(
          'note', link.gift_words->'note', 'from', link.gift_words->'from', 'early', true
        )
      end,
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

-- Merging two legs keeps the owner's notes and files. The rest is 20261024120000_generic_automatic_links, the last
-- that defined it.
create or replace function public.link_package_tracking(p_original_id uuid, p_delivery_id uuid)
returns uuid
language plpgsql
set search_path = pg_catalog
as $$
declare
  original public.packages;
  delivery public.packages;
  combined_label text;
begin
  if p_original_id = p_delivery_id then
    raise exception 'Choose two different parcels' using errcode = '22023';
  end if;
  perform id from public.sync_jobs where package_id in (p_original_id, p_delivery_id) order by id for update;
  perform id from public.packages where id in (p_original_id, p_delivery_id) order by id for update;
  select * into original from public.packages where id = p_original_id;
  select * into delivery from public.packages where id = p_delivery_id;
  if original.id is null and delivery.carrier_data->>'original_package_id' = p_original_id::text then
    return p_delivery_id; -- Safe retry after a completed link.
  end if;
  if original.id is null or delivery.id is null then
    raise exception 'Parcel not found' using errcode = '22023';
  end if;
  if original.user_id is distinct from delivery.user_id or original.user_id is null then
    raise exception 'Parcels must belong to the same account' using errcode = '22023';
  end if;
  if original.carrier_data ? 'original_package_id'
    or (original.carrier_data ? 'original_carrier' and original.carrier_data->>'original_carrier' <> original.carrier)
    or delivery.carrier_data ? 'original_carrier' then
    raise exception 'Parcel already has linked tracking' using errcode = '22023';
  end if;
  combined_label := concat_ws(' / ', nullif(btrim(original.label), ''), nullif(btrim(delivery.label), ''));
  if char_length(combined_label) > 80 then
    raise exception 'Combined parcel name exceeds 80 characters' using errcode = '22023';
  end if;

  -- Keep the delivery carrier's copy of an event if both trackers supplied it, with
  -- the receipts of either copy, so no channel announces the scan a second time.
  insert into public.push_deliveries (subscription_id, event_id, sent_at)
  select receipt.subscription_id, later.id, receipt.sent_at
  from public.tracking_events earlier
  join public.tracking_events later on later.package_id = p_delivery_id
    and later.provider_event_id = earlier.provider_event_id
  join public.push_deliveries receipt on receipt.event_id = earlier.id
  where earlier.package_id = p_original_id
  on conflict (subscription_id, event_id) do nothing;
  insert into public.native_push_deliveries (device_id, event_id, sent_at)
  select receipt.device_id, later.id, receipt.sent_at
  from public.tracking_events earlier
  join public.tracking_events later on later.package_id = p_delivery_id
    and later.provider_event_id = earlier.provider_event_id
  join public.native_push_deliveries receipt on receipt.event_id = earlier.id
  where earlier.package_id = p_original_id
  on conflict (device_id, event_id) do nothing;
  insert into public.parcel_link_alert_deliveries (alert_id, event_id, sent_at)
  select receipt.alert_id, later.id, receipt.sent_at
  from public.tracking_events earlier
  join public.tracking_events later on later.package_id = p_delivery_id
    and later.provider_event_id = earlier.provider_event_id
  join public.parcel_link_alert_deliveries receipt on receipt.event_id = earlier.id
  where earlier.package_id = p_original_id
  on conflict (alert_id, event_id) do nothing;
  delete from public.tracking_events earlier
  using public.tracking_events later
  where earlier.package_id = p_original_id and later.package_id = p_delivery_id
    and earlier.provider_event_id = later.provider_event_id;
  update public.tracking_events set package_id = p_delivery_id where package_id = p_original_id;

  update public.packages set
    label = combined_label,
    created_at = least(original.created_at, delivery.created_at),
    notifications_muted = original.notifications_muted or delivery.notifications_muted,
    email_muted = original.email_muted or delivery.email_muted,
    tracking_generation = gen_random_uuid(),
    carrier_data = coalesce(delivery.carrier_data, '{}'::jsonb) || jsonb_strip_nulls(jsonb_build_object(
      'original_package_id', original.id,
      'original_carrier', original.carrier,
      'original_tracking_number', original.tracking_number,
      'original_tracking_url', original.tracking_url,
      'active_tracking_carrier', delivery.carrier,
      'active_tracking_number', delivery.tracking_number,
      'sender_name', coalesce(original.carrier_data->>'sender_name', delivery.carrier_data->>'sender_name')
    ))
  where id = p_delivery_id;
  update public.sync_jobs set package_id = p_delivery_id where package_id = p_original_id;
  update public.tracking_sync_attempts set package_id = p_delivery_id where package_id = p_original_id;
  update public.delivery_emails set package_id = p_delivery_id
  where package_id = p_original_id
    and not exists (select 1 from public.delivery_emails where package_id = p_delivery_id);
  delete from public.parcel_link_alerts as alert
  using public.parcel_links as stopping
  where alert.link_id = stopping.id
    and stopping.package_id = p_original_id and stopping.created_by is not null and stopping.shared
    and exists (
      select 1 from public.parcel_links as kept
      where kept.package_id = p_delivery_id and kept.created_by = stopping.created_by and kept.shared
    );
  update public.parcel_links as stopping set shared = false, stopped_at = now()
  where stopping.package_id = p_original_id and stopping.created_by is not null and stopping.shared
    and exists (
      select 1 from public.parcel_links as kept
      where kept.package_id = p_delivery_id and kept.created_by = stopping.created_by and kept.shared
    );
  update public.parcel_links set package_id = p_delivery_id where package_id = p_original_id;
  -- The owner's notes join, the original's first, and the files go with the parcel.
  insert into public.package_notes as kept (package_id, user_id, body)
  select p_delivery_id, note.user_id, note.body
  from public.package_notes as note
  where note.package_id = p_original_id
  on conflict (package_id) do update
    set body = case when kept.body = excluded.body then kept.body
      else regexp_replace(left(excluded.body || E'\n\n' || kept.body, 1000), '[[:space:]]+$', '') end,
      updated_at = now();
  update public.package_files set package_id = p_delivery_id where package_id = p_original_id;
  delete from public.packages where id = p_original_id;
  return p_delivery_id;
end;
$$;

revoke all on function public.link_package_tracking(uuid, uuid) from public, anon, authenticated;
grant execute on function public.link_package_tracking(uuid, uuid) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261125200000_parcel_notes') on conflict do nothing;
