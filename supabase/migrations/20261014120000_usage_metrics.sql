-- Counts of accounts and parcels for the usage dashboard in Grafana.
--
-- Grafana reads the metrics schema as metrics_reader, which can read nothing else. The
-- schema holds counts only: nothing in it names an account, a parcel or a number, and
-- PostgREST does not serve it. Production gives metrics_reader LOGIN and a password
-- outside the repository.
--
-- metrics.daily keeps one row per UTC day. The maintenance pass after each scheduled sync
-- rewrites today's row, and the additions of the day before, whose last hour the day's
-- last pass may not have seen. Accounts and parcels being tracked are what the day's
-- last pass counted.
--
-- metrics.active_accounts counts the accounts that opened their deliveries in each UTC
-- day, week (from Monday) and month: record_account_opened counts an account when its
-- last_opened_at moves into a period it was not in yet.
--
-- Past days are filled from what is stored now, so accounts and parcels deleted since
-- are missing from them, and a parcel counts as tracked until its delivery or return was
-- stored, or it was archived. Past activity comes from sign-ins and session renewals in
-- the Auth audit log; the current day, week and month from the recorded openings.
--
-- Apply this before deploying the server that calls record_usage_metrics. The server
-- before it keeps working: record_account_opened keeps its signature.


create schema metrics;
revoke all on schema metrics from public;

comment on schema metrics is
  'Counts of accounts and parcels for the usage dashboard. Nothing here names an account or a parcel.';

create table metrics.daily (
  day date primary key,
  accounts integer check (accounts >= 0),
  accounts_created integer not null default 0 check (accounts_created >= 0),
  parcels_added_account integer not null default 0 check (parcels_added_account >= 0),
  parcels_added_lookup integer not null default 0 check (parcels_added_lookup >= 0),
  parcels_open_account integer check (parcels_open_account >= 0),
  parcels_open_lookup integer check (parcels_open_lookup >= 0)
);

comment on table metrics.daily is
  'One row per UTC day. Today''s row changes with each scheduled sync.';
comment on column metrics.daily.accounts is
  'Accounts at the end of the day. NULL for a day without a scheduled sync.';
comment on column metrics.daily.accounts_created is 'Accounts made that day.';
comment on column metrics.daily.parcels_added_account is
  'Parcels that joined an account that day: added to it, or kept from a lookup.';
comment on column metrics.daily.parcels_added_lookup is 'Parcels looked up without an account that day.';
comment on column metrics.daily.parcels_open_account is
  'Parcels of accounts neither delivered, returned nor archived at the end of the day.';
comment on column metrics.daily.parcels_open_lookup is
  'Parcels looked up without an account neither delivered, returned nor archived at the end of the day.';

create table metrics.active_accounts (
  period text not null check (period in ('day', 'week', 'month')),
  starts date not null,
  accounts integer not null check (accounts >= 0),
  primary key (period, starts)
);

comment on table metrics.active_accounts is
  'Accounts that opened their deliveries in each UTC day, week (from Monday) and month.';

-- Writes how many accounts and parcels were added on each UTC day from p_from to p_to.
create function metrics.record_additions(p_from date, p_to date)
returns void
language sql
security definer
set search_path = ''
as $$
  insert into metrics.daily as daily (day, accounts_created, parcels_added_account, parcels_added_lookup)
  select
    day.day,
    (select count(*) from auth.users as account
      where not account.is_anonymous and account.created_at >= day.starts and account.created_at < day.ends),
    (select count(*) from public.packages as package
      where package.user_id is not null
        and coalesce(package.owned_since, package.created_at) >= day.starts
        and coalesce(package.owned_since, package.created_at) < day.ends),
    (select count(*) from public.packages as package
      where package.one_off and package.created_at >= day.starts and package.created_at < day.ends)
  from (
    select series.day::date as day,
      series.day at time zone 'UTC' as starts,
      (series.day + interval '1 day') at time zone 'UTC' as ends
    from generate_series(p_from::timestamp, p_to::timestamp, interval '1 day') as series (day)
  ) as day
  on conflict (day) do update set
    accounts_created = excluded.accounts_created,
    parcels_added_account = excluded.parcels_added_account,
    parcels_added_lookup = excluded.parcels_added_lookup;
$$;

revoke all on function metrics.record_additions(date, date) from public;

-- Rewrites today's counts, and the additions of the day before.
create function public.record_usage_metrics()
returns void
language plpgsql
security definer
set search_path = ''
as $$
declare
  today constant date := (now() at time zone 'UTC')::date;
begin
  perform metrics.record_additions(today - 1, today);
  update metrics.daily set
    accounts = (select count(*) from auth.users where not is_anonymous),
    parcels_open_account = (select count(*) from public.packages
      where user_id is not null and archived_at is null and current_stage not in ('delivered', 'returned')),
    parcels_open_lookup = (select count(*) from public.packages
      where one_off and archived_at is null and current_stage not in ('delivered', 'returned'))
  where day = today;
end;
$$;

revoke all on function public.record_usage_metrics() from public, anon, authenticated;
grant execute on function public.record_usage_metrics() to service_role;

comment on function public.record_usage_metrics() is
  'Rewrites today''s counts for the usage dashboard, and the additions of the day before. Service role only.';

-- Records that the current account read its parcels, at most every 5 minutes, and
-- counts it in each day, week and month it had not opened them in yet.
create or replace function public.record_account_opened()
returns void
language plpgsql
security definer
set search_path = ''
as $$
declare
  account constant uuid := auth.uid();
  opened_before timestamptz;
begin
  if account is null then
    return;
  end if;
  insert into public.account_activity (user_id) values (account) on conflict (user_id) do nothing;
  if not found then
    select last_opened_at into opened_before from public.account_activity where user_id = account for update;
    if opened_before >= now() - interval '5 minutes' then
      return;
    end if;
    update public.account_activity set last_opened_at = now() where user_id = account;
  end if;

  insert into metrics.active_accounts as active (period, starts, accounts)
  select period.name, date_trunc(period.name, now() at time zone 'UTC')::date, 1
  from (values ('day'), ('week'), ('month')) as period (name)
  where opened_before is null
    or opened_before < date_trunc(period.name, now() at time zone 'UTC') at time zone 'UTC'
  on conflict (period, starts) do update set accounts = active.accounts + 1;
end;
$$;

-- What the dashboard shows as the latest figures.
create view metrics.live as
select
  (select count(*) from auth.users where not is_anonymous) as accounts,
  (select count(*) from public.account_activity where last_opened_at > now() - interval '1 day') as active_day,
  (select count(*) from public.account_activity where last_opened_at > now() - interval '7 days') as active_week,
  (select count(*) from public.account_activity where last_opened_at > now() - interval '30 days') as active_month,
  (select count(*) from public.packages
    where user_id is not null and archived_at is null and current_stage not in ('delivered', 'returned'))
    as parcels_open_account,
  (select count(*) from public.packages
    where one_off and archived_at is null and current_stage not in ('delivered', 'returned'))
    as parcels_open_lookup,
  (select count(*) from public.packages
    where user_id is not null and coalesce(owned_since, created_at) > now() - interval '1 day')
    as parcels_added_account,
  (select count(*) from public.packages where one_off and created_at > now() - interval '1 day')
    as parcels_added_lookup,
  (select count(distinct user_id) from public.push_subscriptions where user_id is not null and disabled_at is null)
    as web_push_accounts,
  (select count(distinct user_id) from public.native_push_devices where disabled_at is null)
    as iphone_push_accounts,
  (select count(*) from public.notification_preferences where email_on_delivery) as email_accounts,
  (select count(*) from public.parcel_link_alerts) as link_alerts;

comment on view metrics.live is
  'Latest figures: accounts, those that opened their deliveries in the last day, week and month, parcels being tracked, added in the last day, and the notifications switched on.';

-- Parcels being tracked, by stage.
create view metrics.open_parcels as
select package.current_stage as stage,
  count(*) filter (where package.user_id is not null) as account,
  count(*) filter (where package.one_off) as lookup
from public.packages as package
where package.archived_at is null
  and package.current_stage not in ('delivered', 'returned')
  and (package.user_id is not null or package.one_off)
group by package.current_stage;

-- Stored parcels by the UTC day they were added and their carrier.
create view metrics.carriers as
select (coalesce(package.owned_since, package.created_at) at time zone 'UTC')::date as day,
  package.carrier,
  count(*) as parcels
from public.packages as package
where package.user_id is not null or package.one_off
group by 1, 2;

do $$
begin
  create role metrics_reader nologin;
exception when duplicate_object then null;
end;
$$;

alter role metrics_reader connection limit 4;
alter role metrics_reader set statement_timeout = '10s';
alter role metrics_reader set default_transaction_read_only = on;

comment on role metrics_reader is 'Grafana''s usage dashboard. Reads the metrics schema only.';

grant usage on schema metrics to metrics_reader;
grant select on metrics.daily, metrics.active_accounts, metrics.live, metrics.open_parcels, metrics.carriers
  to metrics_reader;

-- Past days, from what is stored now.
select metrics.record_additions(
  (select (min(made.created_at) at time zone 'UTC')::date
    from (select created_at from auth.users union all select created_at from public.packages) as made),
  (now() at time zone 'UTC')::date
);

with parcel as (
  select package.user_id is not null as account,
    coalesce(package.owned_since, package.created_at) as added,
    least(package.archived_at, case when package.current_stage in ('delivered', 'returned') then coalesce(
      (select min(event.created_at) from public.tracking_events as event
        where event.package_id = package.id and event.stage = package.current_stage),
      package.last_synced_at,
      package.created_at
    ) end) as finished
  from public.packages as package
  where package.user_id is not null or package.one_off
)
update metrics.daily as daily set
  accounts = (select count(*) from auth.users as account
    where not account.is_anonymous and account.created_at < (daily.day + 1)::timestamp at time zone 'UTC'),
  parcels_open_account = (select count(*) from parcel
    where parcel.account and parcel.added < (daily.day + 1)::timestamp at time zone 'UTC'
      and (parcel.finished is null or parcel.finished >= (daily.day + 1)::timestamp at time zone 'UTC')),
  parcels_open_lookup = (select count(*) from parcel
    where not parcel.account and parcel.added < (daily.day + 1)::timestamp at time zone 'UTC'
      and (parcel.finished is null or parcel.finished >= (daily.day + 1)::timestamp at time zone 'UTC'))
where daily.day < (now() at time zone 'UTC')::date;

select public.record_usage_metrics();

-- Past activity, from sign-ins and session renewals.
with opened (account, at) as (
  select entry.payload ->> 'actor_id', entry.created_at
  from auth.audit_log_entries as entry
  where entry.payload ->> 'action' in ('login', 'token_refreshed', 'user_signedup')
  union all
  select activity.user_id::text, activity.last_opened_at from public.account_activity as activity
)
insert into metrics.active_accounts (period, starts, accounts)
select period.name, date_trunc(period.name, opened.at at time zone 'UTC')::date, count(distinct opened.account)
from opened
cross join (values ('day'), ('week'), ('month')) as period (name)
where opened.account is not null
  and date_trunc(period.name, opened.at at time zone 'UTC') < date_trunc(period.name, now() at time zone 'UTC')
group by 1, 2;

-- The current periods, which later openings add to.
insert into metrics.active_accounts (period, starts, accounts)
select period.name, date_trunc(period.name, now() at time zone 'UTC')::date,
  (select count(*) from public.account_activity as activity
    where activity.last_opened_at >= date_trunc(period.name, now() at time zone 'UTC') at time zone 'UTC')
from (values ('day'), ('week'), ('month')) as period (name);

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261014120000_usage_metrics') on conflict do nothing;

