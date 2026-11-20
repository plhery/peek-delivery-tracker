-- One alert per update for a device that follows a parcel through a link and
-- through an account.
--
-- A link's alert is skipped (`account_endpoint`) when its browser or phone gets
-- the notifications of the account that owns the link's parcel: the account
-- tells it. Someone who kept a parcel shared with them has their own copy in
-- their account, and a browser or phone that kept the link's alert heard every
-- update twice, from the link and from the account. The alert is now skipped as
-- well when its device gets the notifications of an account that has its own
-- parcel of the number. The columns keep their order and meaning.


do $$
declare
  definition text;
  columns text;
begin
  perform set_config('search_path', '', true);
  definition := regexp_replace(pg_get_viewdef('public.pending_parcel_link_alerts'::regclass, true), ';\s*$', '');
  if position('own_package.tracking_number = alerted.tracking_number' in definition) > 0 then return; end if;
  select string_agg(
    case column_name
      when 'account_endpoint' then $column$(
        pending.account_endpoint
        or exists (
          select 1
          from public.packages as alerted
          join public.packages as own_package
            on own_package.tracking_number = alerted.tracking_number and own_package.user_id is not null
          where alerted.id = pending.package_id
            and own_package.user_id in (
              select own.user_id from public.push_subscriptions as own
              where own.endpoint = pending.endpoint and own.disabled_at is null
              union all
              select own.user_id from public.native_push_devices as own
              where own.disabled_at is null
                and (
                  (own.transport = 'apns' and own.token = pending.apns_token)
                  or (own.transport = 'fcm' and own.token = pending.fcm_token)
                  or (own.transport in ('unifiedpush', 'inbox') and own.token = pending.app_token)
                )
            )
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
    select %s from (%s) as pending
  $view$, columns, definition);
end;
$$;

revoke all on public.pending_parcel_link_alerts from public, anon, authenticated;
grant select on public.pending_parcel_link_alerts to service_role;

insert into public.applied_migrations (name) values ('20261119070000_one_alert_per_device') on conflict do nothing;

