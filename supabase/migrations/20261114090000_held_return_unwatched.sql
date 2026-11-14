-- A return the carrier's later scans only carry back (`return_held`) stays on the schedule (trackingSync.ts), so
-- nobody waiting for it counts as for any open parcel: it is checked hourly, a lookup's every 3 hours, rather than
-- on the full schedule.

create or replace function public.unwatched_package_ids(p_opened_since timestamptz)
returns table (id uuid)
language sql
stable
security definer
set search_path = ''
as $$
  select package.id
  from public.packages as package
  where package.archived_at is null
    and (
      package.current_stage not in ('delivered', 'returned')
      or package.last_status_text = 'TO_BE_DELIVERED'
      or (package.current_stage = 'returned' and package.carrier_data ->> 'return_held' = 'true')
    )
    -- A link, opened lately or with an alert on.
    and not exists (
      select 1 from public.parcel_links as link
      where link.package_id = package.id
        and (
          link.last_opened_at > p_opened_since
          or exists (
            select 1 from public.parcel_link_alerts as alert
            where alert.link_id = link.id and (link.shared or alert.owner)
          )
        )
    )
    -- The account's apps, open lately.
    and not exists (
      select 1 from public.account_activity as activity
      where activity.user_id = package.user_id
        and activity.last_opened_at > p_opened_since
    )
    -- The account's notifications, which a parcel can be muted for.
    and (
      package.notifications_muted
      or not (
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
    and not exists (
      select 1 from public.live_activity_devices as device
      where device.user_id = package.user_id and device.disabled_at is null
    )
    -- The email on delivery, which a parcel can be left out of.
    and (
      package.email_muted
      or not exists (
        select 1 from public.notification_preferences as preference
        where preference.user_id = package.user_id and preference.email_on_delivery
      )
    );
$$;

insert into public.applied_migrations (name) values ('20261114090000_held_return_unwatched') on conflict do nothing;
