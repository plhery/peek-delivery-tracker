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
          or exists (
            select 1 from public.parcel_link_alerts as alert
            where alert.link_id = link.id and (link.shared or alert.owner)
          )
        )
    )
  order by package.last_synced_at asc nulls first, package.created_at asc;
$$;

insert into public.applied_migrations (name) values ('20261110020000_held_return_followup') on conflict do nothing;
