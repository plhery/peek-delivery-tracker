-- The two lists a scheduled sync starts from read the link alerts once per call. Asked per link whether one of its
-- alerts counts, the planner read every alert for each of the thousands of links, and the account's apps per parcel
-- the same way; as sets, each is read once. The rows are the same.

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
    -- A link, opened lately or with an alert on: the owner's, or anyone's while the link is shared.
    and not exists (
      select 1 from public.parcel_links as link
      where link.package_id = package.id
        and (
          link.last_opened_at > p_opened_since
          or link.id in (select alert.link_id from public.parcel_link_alerts as alert where alert.owner)
          or (link.shared and link.id in (select alert.link_id from public.parcel_link_alerts as alert))
        )
    )
    -- The account's apps, open lately. A lookup has no account.
    and not (
      package.user_id is not null
      and package.user_id in (
        select activity.user_id from public.account_activity as activity
        where activity.last_opened_at > p_opened_since
      )
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

-- The one-off parcels a scheduled sync still follows: open ones with a link
-- opened since the given time or with an alert on, the least recently checked
-- first. A delivery only a provider reported, which the parcel's carrier did
-- not show, stays open until `unconfirmed_delivery_until` in its carrier data:
-- the carrier may yet dispute it. A return the carrier's later scans only carry
-- back (`return_held`) stays open too: a delivery round, a problem or a new
-- label after it ends the hold (trackingSync.ts).
create or replace function public.followed_one_off_packages(p_opened_since timestamptz)
returns setof public.packages
language sql
stable
security definer
set search_path = ''
as $$
  select package.*
  from public.packages as package
  where package.one_off
    and package.user_id is null
    and package.archived_at is null
    and (
      package.current_stage not in ('delivered', 'returned')
      or package.last_status_text = 'TO_BE_DELIVERED'
      or (package.current_stage = 'delivered'
        and pg_input_is_valid(package.carrier_data ->> 'unconfirmed_delivery_until', 'timestamptz')
        and (package.carrier_data ->> 'unconfirmed_delivery_until')::timestamptz > now())
      or (package.current_stage = 'returned' and package.carrier_data ->> 'return_held' = 'true')
    )
    and exists (
      select 1 from public.parcel_links as link
      where link.package_id = package.id
        and (
          link.last_opened_at > p_opened_since
          or link.id in (select alert.link_id from public.parcel_link_alerts as alert where alert.owner)
          or (link.shared and link.id in (select alert.link_id from public.parcel_link_alerts as alert))
        )
    )
  order by package.last_synced_at asc nulls first, package.created_at asc;
$$;

insert into public.applied_migrations (name) values ('20261116014500_link_alerts_read_once') on conflict do nothing;
