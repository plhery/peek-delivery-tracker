-- A held return (`return_held`: a return the carrier's later scans only carry back, which the scheduled
-- sync still follows, trackingSync.ts) is no finished journey for a link: its forget date is an open
-- parcel's, 90 days after its last scan or opening, an alert can still be turned on and stays on, and
-- the alert queue says the return is held, so that the server keeps the alert too (push.ts).

create or replace function public.parcel_link_forget_at(p_link public.parcel_links)
returns timestamp with time zone
language sql
stable security definer
set search_path = ''
as $$
  select case
    when p_link.created_by is not null then p_link.stopped_at + interval '30 days'
    when package.current_stage in ('delivered', 'returned')
      and not (package.current_stage = 'returned' and package.carrier_data ->> 'return_held' is not distinct from 'true') then greatest((
      select max(event.occurred_at)
      from public.tracking_events as event
      where event.package_id = package.id
        and event.stage = package.current_stage
        and event.occurred_at <= now()
    ), p_link.created_at) + interval '30 days'
    else greatest((
      select max(event.occurred_at)
      from public.tracking_events as event
      where event.package_id = package.id
        and event.occurred_at <= now()
    ), p_link.last_opened_at, p_link.created_at) + interval '90 days'
  end
  from public.packages as package
  where package.id = p_link.package_id;
$$;

create or replace function public.add_parcel_link_alert(p_link_id text, p_owner_key_hash text, p_endpoint text, p_p256dh text, p_auth text, p_locale text, p_preset text)
returns text
language plpgsql
security definer
set search_path = ''
as $$
declare
  link public.parcel_links;
  is_owner boolean;
begin
  -- One alert at a time per link, so the count below holds.
  select * into link from public.parcel_links where id = p_link_id for update;
  if not found or public.parcel_link_forget_at(link) <= now() then return null; end if;
  is_owner := coalesce(link.owner_key_hash = p_owner_key_hash, false);
  if not link.shared and not is_owner then return 'stopped'; end if;
  if exists (
    select 1 from public.packages as package
    where package.id = link.package_id and package.current_stage in ('delivered', 'returned')
      and not (package.current_stage = 'returned' and package.carrier_data ->> 'return_held' is not distinct from 'true')
  ) then
    return 'finished';
  end if;

  update public.parcel_link_alerts
  set p256dh = p_p256dh, auth = p_auth, locale = p_locale, preset = p_preset,
    owner = owner or is_owner, failures = 0
  where link_id = link.id and endpoint = p_endpoint;
  if found then return 'updated'; end if;

  if (
    select count(*) from public.parcel_link_alerts where link_id = link.id and owner = is_owner
  ) >= 10 then
    return 'full';
  end if;
  insert into public.parcel_link_alerts (link_id, endpoint, p256dh, auth, locale, preset, owner)
  values (link.id, p_endpoint, p_p256dh, p_auth, p_locale, p_preset, is_owner);
  return 'added';
end;
$$;

create or replace function public.add_parcel_link_app_alert(p_link_id text, p_owner_key_hash text, p_token text, p_environment text, p_locale text, p_preset text, p_transport text DEFAULT 'apns'::text)
returns text
language plpgsql
security definer
set search_path = ''
as $$
declare
  link public.parcel_links;
  is_owner boolean;
  fcm boolean := p_transport = 'fcm';
begin
  if p_transport is null or p_transport not in ('apns', 'fcm') then
    raise exception 'Unknown notification transport %', p_transport using errcode = '22023';
  end if;
  -- One alert at a time per link, so the count below holds.
  select * into link from public.parcel_links where id = p_link_id for update;
  if not found or public.parcel_link_forget_at(link) <= now() then return null; end if;
  is_owner := coalesce(link.owner_key_hash = p_owner_key_hash, false);
  if not link.shared and not is_owner then return 'stopped'; end if;
  if exists (
    select 1 from public.packages as package
    where package.id = link.package_id and package.current_stage in ('delivered', 'returned')
      and not (package.current_stage = 'returned' and package.carrier_data ->> 'return_held' is not distinct from 'true')
  ) then
    return 'finished';
  end if;

  if fcm then
    update public.parcel_link_alerts
    set locale = p_locale, preset = p_preset, owner = owner or is_owner, failures = 0
    where link_id = link.id and fcm_token = p_token;
  else
    update public.parcel_link_alerts
    set apns_environment = p_environment, locale = p_locale, preset = p_preset,
      owner = owner or is_owner, failures = 0
    where link_id = link.id and apns_token = p_token;
  end if;
  if found then return 'updated'; end if;

  if (
    select count(*) from public.parcel_link_alerts where link_id = link.id and owner = is_owner
  ) >= 10 then
    return 'full';
  end if;
  insert into public.parcel_link_alerts (link_id, apns_token, apns_environment, fcm_token, locale, preset, owner)
  values (
    link.id,
    case when fcm then null else p_token end,
    case when fcm then null else p_environment end,
    case when fcm then p_token end,
    p_locale, p_preset, is_owner
  );
  return 'added';
end;
$$;

create or replace function public.forget_expired_parcel_links()
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  expired record;
  links integer := 0;
  packages integer := 0;
  stopped integer := 0;
  alerts integer := 0;
begin
  for expired in
    select link.id, link.package_id, package.tracking_number
    from public.parcel_links as link
    join public.packages as package on package.id = link.package_id
    where link.created_by is null
      -- No forget date is earlier than 30 days after the lookup, and an
      -- undelivered parcel's is 90 days after its link was last opened.
      and link.created_at <= now() - interval '30 days'
      and (
        package.current_stage in ('delivered', 'returned')
        or link.last_opened_at <= now() - interval '90 days'
      )
      and public.parcel_link_forget_at(link) <= now()
    order by package.tracking_number, link.id
    limit 500
  loop
    perform private.lock_one_off_number(expired.tracking_number);
    delete from public.parcel_links as link
    where link.id = expired.id
      and link.created_by is null
      and public.parcel_link_forget_at(link) <= now();
    if found then
      links := links + 1;
      if private.forget_orphaned_one_off(expired.package_id) then
        packages := packages + 1;
      end if;
    end if;
  end loop;

  delete from public.parcel_links as link
  where link.created_by is not null
    and not link.shared
    and public.parcel_link_forget_at(link) <= now();
  get diagnostics stopped = row_count;

  -- An alert ends with the journey. One with a scan still to announce waits
  -- for the server to send it.
  delete from public.parcel_link_alerts as alert
  using public.parcel_links as link, public.packages as package
  where link.id = alert.link_id
    and package.id = link.package_id
    and package.current_stage in ('delivered', 'returned')
    and not (package.current_stage = 'returned' and package.carrier_data ->> 'return_held' is not distinct from 'true')
    and not exists (
      select 1 from public.pending_parcel_link_alerts as pending where pending.alert_id = alert.id
    );
  get diagnostics alerts = row_count;

  return jsonb_build_object('links', links, 'packages', packages, 'stopped', stopped, 'alerts', alerts);
end;
$$;

do $$
declare
  definition text;
begin
  perform set_config('search_path', '', true);
  if exists (
    select 1 from information_schema.columns
    where table_schema = 'public' and table_name = 'pending_parcel_link_alerts' and column_name = 'return_held'
  ) then return; end if;
  definition := regexp_replace(pg_get_viewdef('public.pending_parcel_link_alerts'::regclass, true), ';\s*$', '');
  execute format($view$
    create or replace view public.pending_parcel_link_alerts with (security_invoker = true) as
    select pending.*, coalesce(held_package.carrier_data ->> 'return_held' = 'true', false) as return_held
    from (%s) as pending
    left join public.packages as held_package on held_package.id = pending.package_id
  $view$, definition);
  revoke all on public.pending_parcel_link_alerts from public, anon, authenticated;
  grant select on public.pending_parcel_link_alerts to service_role;
end;
$$;

insert into public.applied_migrations (name) values ('20261113051000_held_return_links')
on conflict do nothing;
