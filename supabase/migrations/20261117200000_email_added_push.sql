-- "Added from your email" (#405): each parcel an email adds to an account is notified once to the account's browsers
-- and phones, and starts with the list's new mark.
--
-- The notification is the account's choice, kept with its forwarding address and on until switched off on the
-- "Add parcels by email" page (inbound_mailboxes.notify_added). It goes to every browser and phone of the account
-- with notifications on, whichever stages they follow, after quiet hours like the other notifications, and only
-- while the parcel is still followed, not archived and not yet opened. One that waited more than twelve hours, or
-- failed eight times, is dropped.
--
-- A parcel an email added starts unseen: its seen_at is the epoch, which its first answer keeps
-- (start_package_seen) and its owner's first opening replaces (mark_owned_package_seen).
--
-- Apply this before deploying the server that calls parcels_added_by_email and claims these notifications. The
-- server before it keeps working on this schema: it reads the mailbox's fields by name and calls none of the new
-- functions.


alter table private.inbound_mailboxes add column notify_added boolean not null default true;
comment on column private.inbound_mailboxes.notify_added is
  'Whether each parcel an email adds is notified to the account''s browsers and phones.';

create or replace function private.inbound_mailbox_json(mailbox private.inbound_mailboxes)
returns jsonb
language sql
stable
set search_path = ''
as $$
  select jsonb_build_object(
    'token', mailbox.token,
    'created_at', mailbox.created_at,
    'used_today', coalesce((
      select counted.used from private.inbound_mail_days counted
      where counted.user_id = mailbox.user_id and counted.day = (now() at time zone 'UTC')::date
    ), 0),
    'last_mail_at', mailbox.last_mail_at,
    'last_result', mailbox.last_result,
    'last_added', mailbox.last_added,
    'recent', mailbox.recent,
    'notify_added', mailbox.notify_added,
    'confirmation_code', case when mailbox.confirmation_at > now() - interval '3 days' then mailbox.confirmation_code end,
    'confirmation_link', case when mailbox.confirmation_at > now() - interval '3 days' then mailbox.confirmation_link end,
    'confirmation_at', case when mailbox.confirmation_at > now() - interval '3 days' then mailbox.confirmation_at end
  );
$$;
revoke all on function private.inbound_mailbox_json(private.inbound_mailboxes) from public, anon, authenticated;

-- Switches the notification on or off. Answers the mailbox, or NULL for an account without an address.
create function public.set_inbound_mailbox_notify(p_user_id uuid, p_notify boolean)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  saved private.inbound_mailboxes;
begin
  if p_notify is null then
    raise exception 'Invalid notification choice' using errcode = '22023';
  end if;
  update private.inbound_mailboxes set notify_added = p_notify where user_id = p_user_id returning * into saved;
  if not found then
    return null;
  end if;
  return private.inbound_mailbox_json(saved);
end;
$$;
revoke all on function public.set_inbound_mailbox_notify(uuid, boolean) from public, anon, authenticated;
grant execute on function public.set_inbound_mailbox_notify(uuid, boolean) to service_role;

create table public.email_added_push_deliveries (
  id uuid primary key default gen_random_uuid(),
  user_id uuid not null references auth.users (id) on delete cascade,
  package_id uuid not null references public.packages (id) on delete cascade,
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
alter table public.email_added_push_deliveries enable row level security;
revoke all on public.email_added_push_deliveries from public, anon, authenticated;
grant select, insert, update, delete on public.email_added_push_deliveries to service_role;
create index email_added_push_pending_idx on public.email_added_push_deliveries (next_attempt_at) where sent_at is null;
create index email_added_push_user_idx on public.email_added_push_deliveries (user_id);
create index email_added_push_subscription_idx on public.email_added_push_deliveries (subscription_id) where subscription_id is not null;
create index email_added_push_device_idx on public.email_added_push_deliveries (device_id) where device_id is not null;
comment on table public.email_added_push_deliveries is
  'One "Added from your email" notification per parcel an email added and per browser or phone of its account.';

-- A parcel's first answer starts it as seen, but for one an email added, which stays unseen until opened.
create or replace function public.start_package_seen()
returns trigger
language plpgsql
set search_path = pg_catalog
as $$
begin
  if old.user_id is not distinct from new.user_id and old.seen_at = 'epoch'::timestamptz then
    return new;
  end if;
  new.seen_at := now();
  return new;
end;
$$;
revoke all on function public.start_package_seen() from public, anon, authenticated;

-- The parcels an email just added to the account: each starts unseen and, unless the account switched it off, is
-- notified to each of its browsers and phones. Answers how many notifications were queued.
create function public.parcels_added_by_email(p_user_id uuid, p_package_ids uuid[])
returns integer
language plpgsql
security definer
set search_path = pg_catalog
as $$
declare
  queued integer := 0;
  more integer;
begin
  if p_user_id is null or p_package_ids is null or cardinality(p_package_ids) > 100 then
    raise exception 'Invalid parcels' using errcode = '22023';
  end if;
  update public.packages set seen_at = 'epoch'::timestamptz
    where user_id = p_user_id and id = any(p_package_ids);
  if not coalesce((select notify_added from private.inbound_mailboxes where user_id = p_user_id), false) then
    return 0;
  end if;
  insert into public.email_added_push_deliveries (user_id, package_id, subscription_id)
    select p_user_id, p.id, s.id
    from public.packages p
    join public.push_subscriptions s on s.user_id = p_user_id and s.disabled_at is null
    where p.user_id = p_user_id and p.id = any(p_package_ids)
    on conflict do nothing;
  get diagnostics queued = row_count;
  insert into public.email_added_push_deliveries (user_id, package_id, device_id)
    select p_user_id, p.id, n.id
    from public.packages p
    join public.native_push_devices n on n.user_id = p_user_id and n.disabled_at is null
    where p.user_id = p_user_id and p.id = any(p_package_ids)
    on conflict do nothing;
  get diagnostics more = row_count;
  return queued + more;
end;
$$;
revoke all on function public.parcels_added_by_email(uuid, uuid[]) from public, anon, authenticated;
grant execute on function public.parcels_added_by_email(uuid, uuid[]) to service_role;

-- The notifications due now, leased for five minutes, each to a browser or a phone of a transport this server sends:
-- after the account's quiet hours, for a parcel still its own, not archived and never opened.
create function public.claim_email_added_push(
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
  delete from public.email_added_push_deliveries
    where created_at < now() - interval '12 hours' or (sent_at is null and attempts >= 8);
  for delivery in
    select d.id, d.subscription_id, d.device_id, d.package_id, p.label, p.carrier,
      coalesce(s.locale, n.locale, 'en') locale, coalesce(s.endpoint, n.endpoint) endpoint,
      coalesce(s.p256dh, n.p256dh) p256dh, coalesce(s.auth, n.auth) auth, n.token, n.environment, n.transport
    from public.email_added_push_deliveries d
    join public.packages p on p.id = d.package_id and p.user_id = d.user_id
      and p.archived_at is null and p.seen_at = 'epoch'::timestamptz
    left join public.push_subscriptions s on s.id = d.subscription_id and s.user_id = d.user_id and s.disabled_at is null
    left join public.native_push_devices n on n.id = d.device_id and n.user_id = d.user_id and n.disabled_at is null
    left join public.notification_preferences pref on pref.user_id = d.user_id
    where d.sent_at is null and d.next_attempt_at <= now()
      and (d.locked_until is null or d.locked_until < now())
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
    update public.email_added_push_deliveries
      set lease_token = lease, locked_until = now() + interval '5 minutes', attempts = attempts + 1
      where id = delivery.id;
    return next to_jsonb(delivery) || jsonb_build_object('lease_token', lease);
  end loop;
end;
$$;
revoke all on function public.claim_email_added_push(boolean, boolean, integer, boolean, boolean, boolean)
  from public, anon, authenticated;
grant execute on function public.claim_email_added_push(boolean, boolean, integer, boolean, boolean, boolean)
  to service_role;

create function public.finish_email_added_push(p_id uuid, p_lease uuid, p_success boolean)
returns void
language sql
security definer
set search_path = pg_catalog
as $$
  update public.email_added_push_deliveries set
    sent_at = case when p_success then now() else null end,
    next_attempt_at = now() + make_interval(secs => least(3600, 30 * power(2, least(attempts, 7)))::integer),
    locked_until = null, lease_token = null
    where id = p_id and lease_token = p_lease and sent_at is null;
$$;
revoke all on function public.finish_email_added_push(uuid, uuid, boolean) from public, anon, authenticated;
grant execute on function public.finish_email_added_push(uuid, uuid, boolean) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261117200000_email_added_push') on conflict do nothing;

