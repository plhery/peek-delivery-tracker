-- The Android app without Google's services: a native device and a link's app alert can be reached
-- through UnifiedPush, Web Push to the endpoint of the distributor the phone chose, or through an
-- inbox the app polls. Both name the device by the app's installation token, 43 URL-safe base64
-- characters; switching between them keeps the row. The columns the iPhone's and FCM's rows use
-- keep their names and values, and defaulted parameters keep the previous server's calls valid
-- while it still runs.

-- A UnifiedPush device carries its endpoint and Web Push keys; no other device has any.
alter table public.native_push_devices
  add column endpoint text,
  add column p256dh text,
  add column auth text,
  drop constraint native_push_devices_transport_check,
  drop constraint native_push_devices_token_check,
  add constraint native_push_devices_transport_check check (transport in ('apns', 'fcm', 'unifiedpush', 'inbox')),
  add constraint native_push_devices_token_check check (
    case transport
      when 'apns' then char_length(token) between 32 and 512
        and char_length(token) % 2 = 0
        and token ~ '^[0-9a-f]+$'
      when 'fcm' then char_length(token) between 32 and 2048
        and token ~ '^[A-Za-z0-9:_-]+$'
      when 'unifiedpush' then token ~ '^[A-Za-z0-9_-]{43}$'
      when 'inbox' then token ~ '^[A-Za-z0-9_-]{43}$'
      else false
    end
  ),
  add constraint native_push_devices_web_push_check check (
    case transport
      when 'unifiedpush' then endpoint is not null and p256dh is not null and auth is not null
      else endpoint is null and p256dh is null and auth is null
    end
  ),
  add constraint native_push_devices_endpoint_check check (
    endpoint is null or (char_length(endpoint) <= 4096 and endpoint ~ '^https://[^/?#@[:space:]]+/[^#[:space:]]*$')
  ),
  add constraint native_push_devices_keys_check check (
    (p256dh is null or p256dh ~ '^[A-Za-z0-9_-]{87}$') and (auth is null or auth ~ '^[A-Za-z0-9_-]{22}$')
  );

comment on table public.native_push_devices is
  'Server-only APNs and FCM device tokens, and the Android app''s installation tokens for UnifiedPush or the inbox, bound to the account that registered them.';
comment on column public.native_push_devices.transport is
  'apns for the iPhone app, fcm, unifiedpush or inbox for the Android app. Only APNs devices record a development environment.';
comment on column public.native_push_devices.endpoint is
  'A UnifiedPush device''s Web Push endpoint, a secret of its owner like p256dh and auth.';

-- A link's alert is a browser's, an iPhone's, an Android phone's through FCM, or an Android phone's
-- through UnifiedPush or the inbox (app_token). The browser's columns stay the browser's: the
-- previous server sends whatever has an endpoint as Web Push to a browser service.
alter table public.parcel_link_alerts
  add column app_transport text,
  add column app_token text,
  add column app_endpoint text,
  add column app_p256dh text,
  add column app_auth text,
  drop constraint parcel_link_alerts_channel_check,
  add constraint parcel_link_alerts_channel_check check (
    (endpoint is not null and p256dh is not null and auth is not null
      and apns_token is null and apns_environment is null and fcm_token is null and app_token is null)
    or (endpoint is null and p256dh is null and auth is null
      and apns_token is not null and apns_environment is not null and fcm_token is null and app_token is null)
    or (endpoint is null and p256dh is null and auth is null
      and apns_token is null and apns_environment is null and fcm_token is not null and app_token is null)
    or (endpoint is null and p256dh is null and auth is null
      and apns_token is null and apns_environment is null and fcm_token is null and app_token is not null)
  ),
  add constraint parcel_link_alerts_app_check check (
    case app_transport
      when 'unifiedpush' then app_token is not null
        and app_endpoint is not null and app_p256dh is not null and app_auth is not null
      when 'inbox' then app_token is not null
        and app_endpoint is null and app_p256dh is null and app_auth is null
      else app_transport is null and app_token is null
        and app_endpoint is null and app_p256dh is null and app_auth is null
    end
  ),
  add constraint parcel_link_alerts_app_token_check check (app_token is null or app_token ~ '^[A-Za-z0-9_-]{43}$'),
  add constraint parcel_link_alerts_app_endpoint_check check (
    app_endpoint is null or (char_length(app_endpoint) <= 4096 and app_endpoint ~ '^https://[^/?#@[:space:]]+/[^#[:space:]]*$')
  ),
  add constraint parcel_link_alerts_app_keys_check check (
    (app_p256dh is null or app_p256dh ~ '^[A-Za-z0-9_-]{87}$') and (app_auth is null or app_auth ~ '^[A-Za-z0-9_-]{22}$')
  );

create unique index parcel_link_alerts_link_id_app_token_key
  on public.parcel_link_alerts (link_id, app_token)
  where app_token is not null;

comment on table public.parcel_link_alerts is
  'Browser push subscriptions and app device tokens (APNs, FCM, or the Android app''s installation token for UnifiedPush or the inbox) for one parcel link, kept until the parcel is delivered, the browser or phone unsubscribes or the link goes. Service role only.';

-- The alerts waiting for a phone that polls: each holds a notification's FCM data, every value a
-- string. A newer one for the same thing (collapse_key) replaces it, and none outlives a day.
-- device_id names an account's device, whose sign-out takes its alerts along; a link's alert has
-- none, and its alerts go when the phone turns it off.
create table public.push_inbox (
  id bigint generated always as identity primary key,
  token text not null check (token ~ '^[A-Za-z0-9_-]{43}$'),
  collapse_key text check (collapse_key is null or char_length(collapse_key) between 1 and 64),
  data jsonb not null check (
    jsonb_typeof(data) = 'object'
    and octet_length(data::text) <= 8192
    and not jsonb_path_exists(data, '$.* ? (@.type() != "string")')
  ),
  device_id uuid references public.native_push_devices (id) on delete cascade,
  created_at timestamptz not null default now(),
  expires_at timestamptz not null default now() + interval '1 day'
);

create index push_inbox_token_idx on public.push_inbox (token, created_at, id);
create unique index push_inbox_token_collapse_key_key on public.push_inbox (token, collapse_key);
create index push_inbox_expires_at_idx on public.push_inbox (expires_at);
create index push_inbox_device_id_idx on public.push_inbox (device_id) where device_id is not null;

alter table public.push_inbox enable row level security;
revoke all on public.push_inbox from public, anon, authenticated;
revoke all on sequence public.push_inbox_id_seq from public, anon, authenticated;
grant select, insert, update, delete on public.push_inbox to service_role;
grant usage, select on sequence public.push_inbox_id_seq to service_role;

comment on table public.push_inbox is
  'Notifications waiting for the Android app''s inbox transport, by installation token, kept at most a day. Service role only.';

-- Puts a notification in a phone's inbox, in place of the one waiting for the same thing. A phone
-- that never polls keeps its newest fifty.
create function public.put_push_inbox(
  p_token text, p_collapse_key text, p_data jsonb, p_device_id uuid default null
)
returns void
language plpgsql
security definer
set search_path = ''
as $$
begin
  insert into public.push_inbox (token, collapse_key, data, device_id)
  values (p_token, nullif(p_collapse_key, ''), p_data, p_device_id)
  on conflict (token, collapse_key) do update
  set data = excluded.data, device_id = excluded.device_id,
    created_at = now(), expires_at = now() + interval '1 day';
  delete from public.push_inbox
  where token = p_token
    and id not in (
      select id from public.push_inbox where token = p_token order by created_at desc, id desc limit 50
    );
end;
$$;

-- Hands a phone the notifications waiting for it, oldest first, and removes them in the same
-- statement, so each is handed over once. Expired ones, the phone's and everyone's, go unread.
create function public.take_push_inbox(p_token text)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  messages jsonb;
begin
  with taken as (
    delete from public.push_inbox where token = p_token
    returning id, data, created_at, expires_at
  )
  select coalesce(jsonb_agg(data order by created_at, id) filter (where expires_at > now()), '[]'::jsonb)
  into messages
  from taken;
  delete from public.push_inbox where expires_at <= now();
  return messages;
end;
$$;

-- The housekeeping's sweep of what no phone came for.
create function public.forget_expired_push_inbox()
returns integer
language plpgsql
security definer
set search_path = ''
as $$
declare
  forgotten integer;
begin
  delete from public.push_inbox where expires_at <= now();
  get diagnostics forgotten = row_count;
  return forgotten;
end;
$$;

revoke all on function public.put_push_inbox(text, text, jsonb, uuid) from public, anon, authenticated;
grant execute on function public.put_push_inbox(text, text, jsonb, uuid) to service_role;
revoke all on function public.take_push_inbox(text) from public, anon, authenticated;
grant execute on function public.take_push_inbox(text) to service_role;
revoke all on function public.forget_expired_push_inbox() from public, anon, authenticated;
grant execute on function public.forget_expired_push_inbox() to service_role;

-- The account's queue carries a UnifiedPush device's endpoint and keys.
do $$
declare
  definition text;
begin
  perform set_config('search_path', '', true);
  if exists (
    select 1 from information_schema.columns
    where table_schema = 'public' and table_name = 'pending_native_push_notifications' and column_name = 'endpoint'
  ) then return; end if;
  definition := regexp_replace(pg_get_viewdef('public.pending_native_push_notifications'::regclass, true), ';\s*$', '');
  execute format($view$
    create or replace view public.pending_native_push_notifications with (security_invoker = true) as
    select pending.*, push_device.endpoint, push_device.p256dh, push_device.auth
    from (%s) as pending
    join public.native_push_devices as push_device on push_device.id = pending.device_id
  $view$, definition);
end;
$$;

revoke all on public.pending_native_push_notifications from public, anon, authenticated;
grant select on public.pending_native_push_notifications to service_role;

-- The links' queue carries the app's installation token, transport, endpoint and keys, and
-- account_endpoint also marks an alert on a phone that gets the account's notifications through
-- either. The earlier columns keep their order and meaning.
do $$
declare
  definition text;
  columns text;
begin
  perform set_config('search_path', '', true);
  if exists (
    select 1 from information_schema.columns
    where table_schema = 'public' and table_name = 'pending_parcel_link_alerts' and column_name = 'app_token'
  ) then return; end if;
  definition := regexp_replace(pg_get_viewdef('public.pending_parcel_link_alerts'::regclass, true), ';\s*$', '');
  select string_agg(
    case column_name
      when 'account_endpoint' then $column$(
        pending.account_endpoint
        or exists (
          select 1 from public.packages as own_package
          join public.native_push_devices as own on own.user_id = own_package.user_id
          where own_package.id = pending.package_id
            and own.disabled_at is null
            and own.transport in ('unifiedpush', 'inbox')
            and own.token = app_alert.app_token
        )
      ) as account_endpoint$column$
      else format('pending.%I', column_name)
    end,
    ', ' order by ordinal_position
  )
  into columns
  from information_schema.columns
  where table_schema = 'public' and table_name = 'pending_parcel_link_alerts';
  execute format($view$
    create or replace view public.pending_parcel_link_alerts with (security_invoker = true) as
    select %s, app_alert.app_transport, app_alert.app_token, app_alert.app_endpoint, app_alert.app_p256dh,
      app_alert.app_auth
    from (%s) as pending
    join public.parcel_link_alerts as app_alert on app_alert.id = pending.alert_id
  $view$, columns, definition);
end;
$$;

revoke all on public.pending_parcel_link_alerts from public, anon, authenticated;
grant select on public.pending_parcel_link_alerts to service_role;

-- Turns on an alert in the app for a link, or updates the one its phone already has. A UnifiedPush
-- alert takes its endpoint and keys, and no other alert takes any; an alert that changes between
-- UnifiedPush and the inbox keeps its row. Answers and limits as before. The application has
-- validated the token, endpoint and keys.
drop function public.add_parcel_link_app_alert(text, text, text, text, text, text, text);
create function public.add_parcel_link_app_alert(
  p_link_id text,
  p_owner_key_hash text,
  p_token text,
  p_environment text,
  p_locale text,
  p_preset text,
  p_transport text default 'apns',
  p_endpoint text default null,
  p_p256dh text default null,
  p_auth text default null
)
returns text
language plpgsql
security definer
set search_path = ''
as $$
declare
  link public.parcel_links;
  is_owner boolean;
  fcm boolean := p_transport = 'fcm';
  app boolean := p_transport in ('unifiedpush', 'inbox');
begin
  if p_transport is null or p_transport not in ('apns', 'fcm', 'unifiedpush', 'inbox') then
    raise exception 'Unknown notification transport %', p_transport using errcode = '22023';
  end if;
  if num_nonnulls(p_endpoint, p_p256dh, p_auth) <> (case when p_transport = 'unifiedpush' then 3 else 0 end) then
    raise exception 'An endpoint and its keys go with the unifiedpush transport alone' using errcode = '22023';
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

  if app then
    update public.parcel_link_alerts
    set app_transport = p_transport, app_endpoint = p_endpoint, app_p256dh = p_p256dh, app_auth = p_auth,
      locale = p_locale, preset = p_preset, owner = owner or is_owner, failures = 0
    where link_id = link.id and app_token = p_token;
  elsif fcm then
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
  insert into public.parcel_link_alerts (
    link_id, apns_token, apns_environment, fcm_token, app_transport, app_token, app_endpoint, app_p256dh, app_auth,
    locale, preset, owner
  )
  values (
    link.id,
    case when fcm or app then null else p_token end,
    case when fcm or app then null else p_environment end,
    case when fcm then p_token end,
    case when app then p_transport end,
    case when app then p_token end,
    p_endpoint, p_p256dh, p_auth,
    p_locale, p_preset, is_owner
  );
  return 'added';
end;
$$;

revoke all on function public.add_parcel_link_app_alert(text, text, text, text, text, text, text, text, text, text) from public, anon, authenticated;
grant execute on function public.add_parcel_link_app_alert(text, text, text, text, text, text, text, text, text, text) to service_role;

-- Turns a phone's alert off, whichever service its token is for, with the link's notifications
-- still waiting in its inbox. Knowing the token is the right to do so: only the phone and the
-- server know it.
create or replace function public.remove_parcel_link_app_alert(p_link_id text, p_token text)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
declare
  removed boolean;
begin
  delete from public.parcel_link_alerts
  where link_id = p_link_id and (apns_token = p_token or fcm_token = p_token or app_token = p_token);
  removed := found;
  delete from public.push_inbox where token = p_token and data ->> 'link_id' = p_link_id;
  return removed;
end;
$$;

-- p_unifiedpush and p_inbox claim the Android app's devices of those transports, so a server only
-- claims what it can send. A UnifiedPush device's endpoint and keys come in the browser's columns:
-- a delivery is for a browser or a device, never both.
drop function public.claim_friendship_push(boolean, boolean, integer, boolean);
create function public.claim_friendship_push(
  p_web boolean, p_native boolean, p_limit integer default 20, p_fcm boolean default false,
  p_unifiedpush boolean default false, p_inbox boolean default false
)
returns setof jsonb language plpgsql security definer set search_path = pg_catalog as $$
declare delivery record; lease uuid;
begin
  delete from public.friendship_push_deliveries d using public.friendship_updates u
    where d.update_id = u.id and u.created_at < now() - interval '7 days';
  for delivery in
    select d.id, d.subscription_id, d.device_id, f.id friend_id, f.nickname,
      coalesce(s.locale, n.locale, 'en') locale, coalesce(s.endpoint, n.endpoint) endpoint,
      coalesce(s.p256dh, n.p256dh) p256dh, coalesce(s.auth, n.auth) auth, n.token, n.environment,
      n.transport
    from public.friendship_push_deliveries d
    join public.friendship_updates u on u.id = d.update_id
    join public.friend_profiles f on f.user_id = u.friend_user_id
    left join public.push_subscriptions s on s.id = d.subscription_id and s.user_id = u.recipient_id
      and s.disabled_at is null and s.subscribed_at <= u.created_at
    left join public.native_push_devices n on n.id = d.device_id and n.user_id = u.recipient_id
      and n.disabled_at is null and n.subscribed_at <= u.created_at
    left join public.notification_preferences p on p.user_id = u.recipient_id
    where d.sent_at is null and d.attempts < 8 and d.next_attempt_at <= now()
      and (d.locked_until is null or d.locked_until < now()) and u.seen_at is null
      and ((p_web and s.id is not null)
        or (p_native and n.id is not null and n.transport = 'apns')
        or (coalesce(p_fcm, false) and n.id is not null and n.transport = 'fcm')
        or (coalesce(p_unifiedpush, false) and n.id is not null and n.transport = 'unifiedpush')
        or (coalesce(p_inbox, false) and n.id is not null and n.transport = 'inbox'))
      and (p.quiet_hours_start is null or p.quiet_hours_end is null or case
        when p.quiet_hours_start < p.quiet_hours_end then
          (now() at time zone p.timezone)::time < p.quiet_hours_start or (now() at time zone p.timezone)::time >= p.quiet_hours_end
        else (now() at time zone p.timezone)::time < p.quiet_hours_start and (now() at time zone p.timezone)::time >= p.quiet_hours_end end)
    order by u.created_at, d.id limit greatest(1, least(p_limit, 50)) for update of d skip locked
  loop
    lease := gen_random_uuid();
    update public.friendship_push_deliveries set lease_token = lease, locked_until = now() + interval '5 minutes', attempts = attempts + 1 where id = delivery.id;
    return next to_jsonb(delivery) || jsonb_build_object('lease_token', lease);
  end loop;
end $$;
revoke all on function public.claim_friendship_push(boolean, boolean, integer, boolean, boolean, boolean) from public, anon, authenticated;
grant execute on function public.claim_friendship_push(boolean, boolean, integer, boolean, boolean, boolean) to service_role;

-- The usage figures count the Android app's phones with notifications on, whichever way they come.
create or replace view metrics.live as
select
  (select count(*) from auth.users where not is_anonymous) as accounts,
  (select count(*) from public.account_activity where last_opened_at > now() - interval '1 day') as active_day,
  (select count(*) from public.account_activity where last_opened_at > now() - interval '7 days') as active_week,
  (select count(*) from public.account_activity where last_opened_at > now() - interval '30 days') as active_month,
  (select count(*) from public.packages
    where user_id is not null and archived_at is null and current_stage not in ('delivered', 'returned'))
    as parcels_open_account,
  (select count(*) from public.packages
    where one_off and archived_at is null and current_stage not in ('delivered', 'returned'))
    as parcels_open_lookup,
  (select count(*) from public.packages
    where user_id is not null and coalesce(owned_since, created_at) > now() - interval '1 day')
    as parcels_added_account,
  (select count(*) from public.packages where one_off and created_at > now() - interval '1 day')
    as parcels_added_lookup,
  (select count(distinct user_id) from public.push_subscriptions where user_id is not null and disabled_at is null)
    as web_push_accounts,
  (select count(distinct user_id) from public.native_push_devices where transport = 'apns' and disabled_at is null)
    as iphone_push_accounts,
  (select count(*) from public.notification_preferences where email_on_delivery) as email_accounts,
  (select count(*) from public.parcel_link_alerts) as link_alerts,
  (select count(distinct user_id) from public.native_push_devices
    where transport in ('fcm', 'unifiedpush', 'inbox') and disabled_at is null)
    as android_push_accounts;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261115234000_unifiedpush_inbox') on conflict do nothing;
