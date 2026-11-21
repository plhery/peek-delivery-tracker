-- "Added by Claude": each parcel an assistant adds to an account is notified once to the account's browsers and
-- phones, naming the assistant, and starts with the list's new mark, as a parcel an email adds does.
--
-- The notification is the account's choice, on until switched off on the Assistants page
-- (private.assistant_preferences.notify_added, a row only once the account chose). It goes to every browser and phone
-- of the account with notifications on, whichever stages they follow, after quiet hours like the other notifications,
-- and only while the parcel is still followed, not archived and not yet opened. One that waited more than twelve
-- hours, or failed eight times, is dropped; disconnecting the assistant drops its own.
--
-- Apply this before deploying the server that calls parcels_added_by_assistant and claims these notifications. The
-- server before it calls none of the new functions.

create table private.assistant_preferences (
  user_id uuid primary key references auth.users (id) on delete cascade,
  notify_added boolean not null default true
);
alter table private.assistant_preferences enable row level security;
revoke all on private.assistant_preferences from public, anon, authenticated;
comment on table private.assistant_preferences is
  'An account''s choices about its assistants: whether each parcel one adds is notified to its browsers and phones.';

-- Whether each parcel an assistant adds is notified: on for an account that never chose.
create function public.assistant_added_notify(p_user_id uuid)
returns boolean
language sql
stable
security definer
set search_path = ''
as $$
  select coalesce((select chosen.notify_added from private.assistant_preferences chosen where chosen.user_id = p_user_id), true);
$$;
revoke all on function public.assistant_added_notify(uuid) from public, anon, authenticated;
grant execute on function public.assistant_added_notify(uuid) to service_role;

-- Switches the notification on or off. Answers the choice saved.
create function public.set_assistant_added_notify(p_user_id uuid, p_notify boolean)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
begin
  if p_user_id is null or p_notify is null then
    raise exception 'Invalid notification choice' using errcode = '22023';
  end if;
  insert into private.assistant_preferences (user_id, notify_added) values (p_user_id, p_notify)
    on conflict (user_id) do update set notify_added = excluded.notify_added;
  return p_notify;
end;
$$;
revoke all on function public.set_assistant_added_notify(uuid, boolean) from public, anon, authenticated;
grant execute on function public.set_assistant_added_notify(uuid, boolean) to service_role;

create table public.assistant_added_push_deliveries (
  id uuid primary key default gen_random_uuid(),
  user_id uuid not null references auth.users (id) on delete cascade,
  package_id uuid not null references public.packages (id) on delete cascade,
  grant_id uuid not null references private.assistant_grants (id) on delete cascade,
  subscription_id uuid references public.push_subscriptions (id) on delete cascade,
  device_id uuid references public.native_push_devices (id) on delete cascade,
  created_at timestamptz not null default now(),
  sent_at timestamptz,
  next_attempt_at timestamptz not null default now(),
  locked_until timestamptz,
  lease_token uuid,
  attempts integer not null default 0,
  check ((subscription_id is null) <> (device_id is null)),
  unique (package_id, subscription_id),
  unique (package_id, device_id)
);
alter table public.assistant_added_push_deliveries enable row level security;
revoke all on public.assistant_added_push_deliveries from public, anon, authenticated;
grant select, insert, update, delete on public.assistant_added_push_deliveries to service_role;
create index assistant_added_push_pending_idx on public.assistant_added_push_deliveries (next_attempt_at) where sent_at is null;
create index assistant_added_push_user_idx on public.assistant_added_push_deliveries (user_id);
create index assistant_added_push_grant_idx on public.assistant_added_push_deliveries (grant_id);
create index assistant_added_push_subscription_idx on public.assistant_added_push_deliveries (subscription_id) where subscription_id is not null;
create index assistant_added_push_device_idx on public.assistant_added_push_deliveries (device_id) where device_id is not null;
comment on table public.assistant_added_push_deliveries is
  'One "Added by <assistant>" notification per parcel an assistant added and per browser or phone of its account.';

-- The parcels an assistant just added to the account, through one of the account's grants: each starts unseen and,
-- unless the account switched it off, is notified to each of its browsers and phones. Answers how many notifications
-- were queued.
create function public.parcels_added_by_assistant(p_user_id uuid, p_grant_id uuid, p_package_ids uuid[])
returns integer
language plpgsql
security definer
set search_path = pg_catalog
as $$
declare
  queued integer := 0;
  more integer;
begin
  if p_user_id is null or p_grant_id is null or p_package_ids is null or cardinality(p_package_ids) > 100 then
    raise exception 'Invalid parcels' using errcode = '22023';
  end if;
  if not exists (select 1 from private.assistant_grants g where g.id = p_grant_id and g.user_id = p_user_id) then
    return 0;
  end if;
  update public.packages set seen_at = 'epoch'::timestamptz
    where user_id = p_user_id and id = any(p_package_ids);
  if not public.assistant_added_notify(p_user_id) then
    return 0;
  end if;
  insert into public.assistant_added_push_deliveries (user_id, package_id, grant_id, subscription_id)
    select p_user_id, p.id, p_grant_id, s.id
    from public.packages p
    join public.push_subscriptions s on s.user_id = p_user_id and s.disabled_at is null
    where p.user_id = p_user_id and p.id = any(p_package_ids)
    on conflict do nothing;
  get diagnostics queued = row_count;
  insert into public.assistant_added_push_deliveries (user_id, package_id, grant_id, device_id)
    select p_user_id, p.id, p_grant_id, n.id
    from public.packages p
    join public.native_push_devices n on n.user_id = p_user_id and n.disabled_at is null
    where p.user_id = p_user_id and p.id = any(p_package_ids)
    on conflict do nothing;
  get diagnostics more = row_count;
  return queued + more;
end;
$$;
revoke all on function public.parcels_added_by_assistant(uuid, uuid, uuid[]) from public, anon, authenticated;
grant execute on function public.parcels_added_by_assistant(uuid, uuid, uuid[]) to service_role;

-- The notifications due now, leased for five minutes, each to a browser or a phone of a transport this server sends,
-- with the assistant's name: after the account's quiet hours, while it keeps the notification on, for a parcel still
-- its own, not archived and never opened.
create function public.claim_assistant_added_push(
  p_web boolean, p_native boolean, p_limit integer default 20, p_fcm boolean default false,
  p_unifiedpush boolean default false, p_inbox boolean default false
)
returns setof jsonb
language plpgsql
security definer
set search_path = pg_catalog
as $$
declare
  delivery record;
  lease uuid;
begin
  delete from public.assistant_added_push_deliveries
    where created_at < now() - interval '12 hours' or (sent_at is null and attempts >= 8);
  for delivery in
    select d.id, d.subscription_id, d.device_id, d.package_id, p.label, p.carrier, g.name assistant,
      coalesce(s.locale, n.locale, 'en') locale, coalesce(s.endpoint, n.endpoint) endpoint,
      coalesce(s.p256dh, n.p256dh) p256dh, coalesce(s.auth, n.auth) auth, n.token, n.environment, n.transport
    from public.assistant_added_push_deliveries d
    join private.assistant_grants g on g.id = d.grant_id and g.user_id = d.user_id
    join public.packages p on p.id = d.package_id and p.user_id = d.user_id
      and p.archived_at is null and p.seen_at = 'epoch'::timestamptz
    left join private.assistant_preferences chosen on chosen.user_id = d.user_id
    left join public.push_subscriptions s on s.id = d.subscription_id and s.user_id = d.user_id and s.disabled_at is null
    left join public.native_push_devices n on n.id = d.device_id and n.user_id = d.user_id and n.disabled_at is null
    left join public.notification_preferences pref on pref.user_id = d.user_id
    where d.sent_at is null and d.next_attempt_at <= now()
      and (d.locked_until is null or d.locked_until < now())
      and coalesce(chosen.notify_added, true)
      and ((p_web and s.id is not null)
        or (p_native and n.id is not null and n.transport = 'apns')
        or (coalesce(p_fcm, false) and n.id is not null and n.transport = 'fcm')
        or (coalesce(p_unifiedpush, false) and n.id is not null and n.transport = 'unifiedpush')
        or (coalesce(p_inbox, false) and n.id is not null and n.transport = 'inbox'))
      and (pref.quiet_hours_start is null or pref.quiet_hours_end is null or case
        when pref.quiet_hours_start < pref.quiet_hours_end then
          (now() at time zone pref.timezone)::time < pref.quiet_hours_start
          or (now() at time zone pref.timezone)::time >= pref.quiet_hours_end
        else (now() at time zone pref.timezone)::time < pref.quiet_hours_start
          and (now() at time zone pref.timezone)::time >= pref.quiet_hours_end end)
    order by d.created_at, d.id
    limit greatest(1, least(p_limit, 50))
    for update of d skip locked
  loop
    lease := gen_random_uuid();
    update public.assistant_added_push_deliveries
      set lease_token = lease, locked_until = now() + interval '5 minutes', attempts = attempts + 1
      where id = delivery.id;
    return next to_jsonb(delivery) || jsonb_build_object('lease_token', lease);
  end loop;
end;
$$;
revoke all on function public.claim_assistant_added_push(boolean, boolean, integer, boolean, boolean, boolean)
  from public, anon, authenticated;
grant execute on function public.claim_assistant_added_push(boolean, boolean, integer, boolean, boolean, boolean)
  to service_role;

create function public.finish_assistant_added_push(p_id uuid, p_lease uuid, p_success boolean)
returns void
language sql
security definer
set search_path = pg_catalog
as $$
  update public.assistant_added_push_deliveries set
    sent_at = case when p_success then now() else null end,
    next_attempt_at = now() + make_interval(secs => least(3600, 30 * power(2, least(attempts, 7)))::integer),
    locked_until = null, lease_token = null
    where id = p_id and lease_token = p_lease and sent_at is null;
$$;
revoke all on function public.finish_assistant_added_push(uuid, uuid, boolean) from public, anon, authenticated;
grant execute on function public.finish_assistant_added_push(uuid, uuid, boolean) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261121190000_assistant_added_push') on conflict do nothing;
