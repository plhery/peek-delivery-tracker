
create or replace function public.unwatched_package_ids(p_opened_since timestamptz, p_after uuid, p_limit integer)
returns table (id uuid)
language sql
stable
security definer
set search_path = ''
as $$
  select package.id
  from public.packages as package
  where package.archived_at is null
    and (p_after is null or package.id > p_after)
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
    )
  order by package.id
  limit p_limit;
$$;
revoke all on function public.unwatched_package_ids(timestamptz,uuid,integer) from public, anon, authenticated;
grant execute on function public.unwatched_package_ids(timestamptz,uuid,integer) to service_role;

insert into public.applied_migrations (name) values ('20261118221000_paged_unwatched_parcels') on conflict do nothing;

