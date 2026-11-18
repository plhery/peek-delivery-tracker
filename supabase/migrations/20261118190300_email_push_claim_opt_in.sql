create or replace function public.claim_email_added_push(
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
    join private.inbound_mailboxes m on m.user_id = d.user_id and m.notify_added
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

insert into public.applied_migrations (name) values ('20261118190300_email_push_claim_opt_in') on conflict do nothing;
