-- The open parcels someone waits for, the ones unwatched_package_ids leaves out, and why: their page or the account's
-- apps on screen lately, an alert on, or only a read in the last hour. A scheduled sync checks a parcel up to every
-- 10 minutes only while it is on screen, or while an alert is on and it is due or out for delivery today; the others
-- someone waits for hourly.

create or replace function public.waiting_package_ids(p_opened_since timestamptz, p_on_screen_since timestamptz)
returns table (id uuid, on_screen boolean, alerted boolean)
language sql
stable
security definer
set search_path = ''
as $$
  select package.id, waiting.on_screen, waiting.alerted
  from public.packages as package
  cross join lateral (
    select
      -- A link opened, or the account's apps.
      exists (
        select 1 from public.parcel_links as link
        where link.package_id = package.id and link.last_opened_at > p_on_screen_since
      ) or (
        package.user_id is not null
        and package.user_id in (
          select activity.user_id from public.account_activity as activity
          where activity.last_opened_at > p_on_screen_since
        )
      ) as on_screen,
      exists (
        select 1 from public.parcel_links as link
        where link.package_id = package.id and link.last_opened_at > p_opened_since
      ) or (
        package.user_id is not null
        and package.user_id in (
          select activity.user_id from public.account_activity as activity
          where activity.last_opened_at > p_opened_since
        )
      ) as opened,
      -- A link's alert: the owner's, or anyone's while the link is shared.
      exists (
        select 1 from public.parcel_links as link
        where link.package_id = package.id
          and (
            link.id in (select alert.link_id from public.parcel_link_alerts as alert where alert.owner)
            or (link.shared and link.id in (select alert.link_id from public.parcel_link_alerts as alert))
          )
      )
      -- The account's notifications, which a parcel can be muted for.
      or (
        not package.notifications_muted
        and (
          exists (
            select 1 from public.push_subscriptions as subscription
            where subscription.user_id = package.user_id and subscription.disabled_at is null
          )
          or exists (
            select 1 from public.native_push_devices as device
            where device.user_id = package.user_id and device.disabled_at is null
          )
        )
      )
      or exists (
        select 1 from public.live_activity_devices as device
        where device.user_id = package.user_id and device.disabled_at is null
      )
      -- The email on delivery, which a parcel can be left out of.
      or (
        not package.email_muted
        and exists (
          select 1 from public.notification_preferences as preference
          where preference.user_id = package.user_id and preference.email_on_delivery
        )
      ) as alerted
  ) as waiting
  where package.archived_at is null
    and (
      package.current_stage not in ('delivered', 'returned')
      or package.last_status_text = 'TO_BE_DELIVERED'
      or (package.current_stage = 'returned' and package.carrier_data ->> 'return_held' = 'true')
    )
    and (waiting.opened or waiting.alerted);
$$;

revoke all on function public.waiting_package_ids(timestamptz, timestamptz) from public, anon, authenticated;
grant execute on function public.waiting_package_ids(timestamptz, timestamptz) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261117171000_waiting_parcels') on conflict do nothing;
