-- Guests in the usage dashboard, from what is stored today: no id is added, and
-- the views hold counts only.
--
-- metrics.lookup_returns: lookups made on each UTC day of the last 30, and those
-- opened again on a later day. parcel_links.last_opened_at moves when anyone
-- reads the link, at most every 5 minutes, and not for a page out of sight. A
-- lookup is a link a device still holds the key of: one kept in an account since
-- leaves the count. Older days would miss the lookups the service has forgotten
-- since (30 days after delivery at the earliest), so the view stops there.
--
-- metrics.lookup_accounts: accounts made on each UTC day, and those that hold a
-- lookup made before them: guests who signed in and kept it, since keeping a
-- lookup gives its link to the account (claim_parcel_link). One whose link was
-- deleted since, with its parcel or 30 days after its sharing stopped, leaves it.
--
-- metrics.link_alert_devices: devices with an alert on a parcel link, by kind,
-- and those following two parcels or more. A device is its push endpoint or
-- token, counted, never shown. Alerts end once the parcel is delivered.

begin;

create view metrics.lookup_returns as
select (link.created_at at time zone 'UTC')::date as day,
  count(*) as lookups,
  count(*) filter (
    where (link.last_opened_at at time zone 'UTC')::date > (link.created_at at time zone 'UTC')::date
  ) as opened_later
from public.parcel_links as link
where link.owner_key_hash is not null
  and link.created_at >= (((now() at time zone 'UTC')::date - 30)::timestamp at time zone 'UTC')
group by 1;

comment on view metrics.lookup_returns is
  'Lookups made on each UTC day of the last 30, and those opened again on a later day.';

create view metrics.lookup_accounts as
select (account.created_at at time zone 'UTC')::date as day,
  count(*) as accounts,
  count(*) filter (where exists (
    select 1 from public.parcel_links as link
    where link.created_by = account.id and link.created_at < account.created_at
  )) as from_lookup
from auth.users as account
where not account.is_anonymous
group by 1;

comment on view metrics.lookup_accounts is
  'Accounts made on each UTC day, and those that kept a lookup made before them.';

create view metrics.link_alert_devices as
select device.kind,
  count(*) as devices,
  count(*) filter (where device.parcels >= 2) as following_several
from (
  select
    case
      when alert.endpoint is not null then 'web'
      when alert.apns_token is not null then 'iphone'
      else 'android'
    end as kind,
    count(distinct link.package_id) as parcels
  from public.parcel_link_alerts as alert
  join public.parcel_links as link on link.id = alert.link_id
  group by 1, coalesce(alert.endpoint, alert.apns_token, alert.fcm_token, alert.app_token)
) as device
group by device.kind;

comment on view metrics.link_alert_devices is
  'Devices with an alert on a parcel link, by kind (web, iphone, android), and those following two parcels or more.';

grant select on metrics.lookup_returns, metrics.lookup_accounts, metrics.link_alert_devices to metrics_reader;

insert into public.applied_migrations (name) values ('20261119060000_guest_metrics') on conflict do nothing;

commit;
