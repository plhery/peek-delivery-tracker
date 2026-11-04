-- The Android app's notifications: a native device and a link's app alert can hold a Firebase
-- Cloud Messaging registration token in place of an APNs device token, and flow through the same
-- queues. The columns the iPhone's rows use keep their names and values; every existing row is an
-- APNs one. Defaulted parameters keep the previous server's calls valid while it still runs.

-- A device says how it is reached. FCM has no separate development service, so an FCM device is
-- recorded as production. FCM tokens are case-sensitive and stay short of the unique index's limit.
alter table public.native_push_devices
  add column transport text not null default 'apns',
  drop constraint native_push_devices_token_check,
  drop constraint native_push_devices_environment_check,
  add constraint native_push_devices_transport_check check (transport in ('apns', 'fcm')),
  add constraint native_push_devices_token_check check (
    case transport
      when 'apns' then char_length(token) between 32 and 512
        and char_length(token) % 2 = 0
        and token ~ '^[0-9a-f]+$'
      when 'fcm' then char_length(token) between 32 and 2048
        and token ~ '^[A-Za-z0-9:_-]+$'
      else false
    end
  ),
  add constraint native_push_devices_environment_check check (
    environment in ('development', 'production')
    and (transport = 'apns' or environment = 'production')
  );

comment on table public.native_push_devices is
  'Server-only APNs and FCM device tokens bound to the account that registered them.';
comment on column public.native_push_devices.transport is
  'apns for the iPhone app, fcm for the Android app. FCM devices record production as their environment.';

-- A link's alert is a browser's, an iPhone's or an Android phone's.
alter table public.parcel_link_alerts
  add column fcm_token text,
  drop constraint parcel_link_alerts_channel_check,
  add constraint parcel_link_alerts_channel_check check (
    (endpoint is not null and p256dh is not null and auth is not null
      and apns_token is null and apns_environment is null and fcm_token is null)
    or (endpoint is null and p256dh is null and auth is null
      and apns_token is not null and apns_environment is not null and fcm_token is null)
    or (endpoint is null and p256dh is null and auth is null
      and apns_token is null and apns_environment is null and fcm_token is not null)
  ),
  add constraint parcel_link_alerts_fcm_token_check check (
    fcm_token is null or (char_length(fcm_token) between 32 and 2048 and fcm_token ~ '^[A-Za-z0-9:_-]+$')
  );

create unique index parcel_link_alerts_link_id_fcm_token_key
  on public.parcel_link_alerts (link_id, fcm_token)
  where fcm_token is not null;

comment on table public.parcel_link_alerts is
  'Browser push subscriptions and app device tokens (APNs or FCM) for one parcel link, kept until the parcel is delivered, the browser or phone unsubscribes or the link goes. Service role only.';

-- The account's queue says which service reaches each device.
do $$
declare
  definition text;
begin
  perform set_config('search_path', '', true);
  if exists (
    select 1 from information_schema.columns
    where table_schema = 'public' and table_name = 'pending_native_push_notifications'
      and column_name = 'transport'
  ) then return; end if;
  definition := regexp_replace(
    pg_get_viewdef(to_regclass('public.pending_native_push_notifications'), true), ';\s*$', '');
  execute format($view$
    create or replace view public.pending_native_push_notifications with (security_invoker = true) as
    select pending.*, source_device.transport
    from (%s) as pending
    join public.native_push_devices as source_device on source_device.id = pending.device_id
  $view$, definition);
end;
$$;

revoke all on public.pending_native_push_notifications from public, anon, authenticated;
grant select on public.pending_native_push_notifications to service_role;

-- The links' queue carries an Android phone's token, and account_endpoint marks an alert on a
-- phone of either kind that already gets the account's notifications. The earlier columns keep
-- their order and meaning; the scan's tie order and time are read from the scan itself.
create or replace view public.pending_parcel_link_alerts
with (security_invoker = true) as
select
  alert.id as alert_id,
  alert.link_id,
  alert.endpoint,
  alert.p256dh,
  alert.auth,
  alert.locale,
  alert.preset,
  alert.owner,
  alert.failures,
  link.gift,
  event.id as event_id,
  event.package_id,
  event.stage,
  event.location,
  event.occurred_at,
  event.created_at as event_created_at,
  package.current_stage as package_stage,
  package.expected_delivery,
  (
    package.expected_delivery is not null
    and package.expected_delivery_changed_at is not null
    and package.expected_delivery_changed_at >= event.created_at
  ) as expected_delivery_changed,
  (
    event.raw_data -> 'observed_without_provider_timestamp' is distinct from 'true'::jsonb
    and coalesce(event.raw_data ->> 'time' ~ '[T ][0-9]{2}:[0-9]{2}', false)
  ) as event_has_time,
  (
    exists (
      select 1 from public.push_subscriptions as own
      where own.user_id = package.user_id
        and own.endpoint = alert.endpoint
        and own.disabled_at is null
    )
    or exists (
      select 1 from public.native_push_devices as own
      where own.user_id = package.user_id
        and own.disabled_at is null
        and (
          (own.transport = 'apns' and own.token = alert.apns_token)
          or (own.transport = 'fcm' and own.token = alert.fcm_token)
        )
    )
  ) as account_endpoint,
  alert.apns_token,
  alert.apns_environment,
  event.raw_data -> 'tie_order' as tie_order,
  event.raw_data ->> 'time' as event_time,
  alert.fcm_token
from public.parcel_link_alerts as alert
join public.parcel_links as link on link.id = alert.link_id
join public.packages as package on package.id = link.package_id
join public.tracking_events as event
  on event.package_id = package.id
 and event.created_at > alert.created_at
 and (event.provider_event_id is null or event.provider_event_id not like 'app:%')
 and event.stage <> 'pending'
left join public.parcel_link_alert_deliveries as delivery
  on delivery.alert_id = alert.id
 and delivery.event_id = event.id
where delivery.event_id is null
  and (link.shared or alert.owner);

revoke all on public.pending_parcel_link_alerts from public, anon, authenticated;
grant select on public.pending_parcel_link_alerts to service_role;

-- Turns on an alert in the app for a link, or updates the one its phone already has. p_transport
-- says which service the token belongs to; an FCM alert keeps no environment. Answers and limits
-- as before. The application has validated the token.
drop function public.add_parcel_link_app_alert(text, text, text, text, text, text);
create function public.add_parcel_link_app_alert(
  p_link_id text,
  p_owner_key_hash text,
  p_token text,
  p_environment text,
  p_locale text,
  p_preset text,
  p_transport text default 'apns'
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
    select 1 from public.packages
    where id = link.package_id and current_stage in ('delivered', 'returned')
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

-- Turns a phone's alert off. Knowing the token is the right to do so: only the phone and the
-- server know it.
create or replace function public.remove_parcel_link_app_alert(p_link_id text, p_token text)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
begin
  delete from public.parcel_link_alerts
  where link_id = p_link_id and (apns_token = p_token or fcm_token = p_token);
  return found;
end;
$$;

revoke all on function public.add_parcel_link_app_alert(text, text, text, text, text, text, text) from public, anon, authenticated;
grant execute on function public.add_parcel_link_app_alert(text, text, text, text, text, text, text) to service_role;

-- Leases prevent multiple app replicas from sending the same receipt together. p_native claims
-- APNs devices and p_fcm FCM ones, so a server only claims what it can send; each row says its
-- transport. Eligibility is checked again here: a device may have signed out, changed accounts,
-- or been disabled since the friendship was created.
drop function public.claim_friendship_push(boolean, boolean, integer);
create function public.claim_friendship_push(
  p_web boolean, p_native boolean, p_limit integer default 20, p_fcm boolean default false
)
returns setof jsonb language plpgsql security definer set search_path = pg_catalog as $$
declare delivery record; lease uuid;
begin
  delete from public.friendship_push_deliveries d using public.friendship_updates u
    where d.update_id = u.id and u.created_at < now() - interval '7 days';
  for delivery in
    select d.id, d.subscription_id, d.device_id, f.id friend_id, f.nickname,
      coalesce(s.locale, n.locale, 'en') locale, s.endpoint, s.p256dh, s.auth, n.token, n.environment,
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
        or (coalesce(p_fcm, false) and n.id is not null and n.transport = 'fcm'))
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
revoke all on function public.claim_friendship_push(boolean, boolean, integer, boolean) from public, anon, authenticated;
grant execute on function public.claim_friendship_push(boolean, boolean, integer, boolean) to service_role;

-- The usage figures count phones of each kind with notifications on.
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
  (select count(distinct user_id) from public.native_push_devices where transport = 'fcm' and disabled_at is null)
    as android_push_accounts;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261104180000_android_push') on conflict do nothing;
