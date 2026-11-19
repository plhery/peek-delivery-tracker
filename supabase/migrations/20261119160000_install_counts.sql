-- Apps and browsers counted apart from accounts, with a random counting id each
-- install makes and sends alone, at most once a day (POST /api/public/seen).
--
-- private.installs: one row per counting id, as the server's keyed hash of it,
-- with its platform, the first and last day it was seen, on how many days, the
-- first day it was seen without an account and signed in, and how many parcels it
-- last followed without an account. No address, account, link or tracking
-- number. A row not seen for 90 days goes, and every row 25 months after its
-- first day; the apps and the website replace their id after 13 months.
--
-- metrics.installs_daily: installs seen each UTC day, by platform and kind: `new`
-- (first seen that day, without an account), `returning` (seen before, without an
-- account), `account` (signed in), plus `converted` (seen without an account,
-- then signed in for the first time that day). An install counts once a day, as
-- what it first was that day.
--
-- metrics.active_installs: installs seen in the last 1, 7 and 30 days, by
-- platform, those never seen signed in apart.
--
-- metrics.guest_cohorts: installs first seen without an account in each of the
-- last 12 weeks, and what became of them.

begin;

create table private.installs (
  install text primary key check (install ~ '^[0-9a-f]{64}$'),
  platform text not null check (platform in ('web', 'pwa', 'iphone', 'ipad', 'mac', 'android')),
  first_seen date not null,
  last_seen date not null,
  days_seen integer not null check (days_seen > 0),
  guest_on date,
  signed_in_on date,
  parcels integer not null check (parcels >= 0)
);
alter table private.installs enable row level security;
revoke all on private.installs from public, anon, authenticated;

create table metrics.installs_daily (
  day date not null,
  platform text not null,
  kind text not null check (kind in ('new', 'returning', 'account', 'converted')),
  installs integer not null check (installs > 0),
  primary key (day, platform, kind)
);
revoke all on metrics.installs_daily from public, anon, authenticated;

comment on table metrics.installs_daily is
  'Installs seen each UTC day, by platform and kind: new, returning (both without an account), account, converted.';

create function public.record_install_seen(p_install text, p_platform text, p_signed_in boolean, p_parcels integer)
returns void
language plpgsql
security definer
set search_path = ''
as $$
declare
  today constant date := (now() at time zone 'UTC')::date;
  seen private.installs;
  counted text[] := '{}';
  each_kind text;
begin
  if p_install is null or p_install !~ '^[0-9a-f]{64}$'
      or p_platform is null or p_platform not in ('web', 'pwa', 'iphone', 'ipad', 'mac', 'android')
      or p_signed_in is null or p_parcels is null or p_parcels < 0 then
    raise exception 'Invalid install sighting' using errcode = '22023';
  end if;

  select * into seen from private.installs where install = p_install for update;
  if not found then
    insert into private.installs (install, platform, first_seen, last_seen, days_seen, guest_on, signed_in_on, parcels)
    values (p_install, p_platform, today, today, 1,
      case when not p_signed_in then today end, case when p_signed_in then today end, p_parcels)
    on conflict (install) do nothing;
    -- Another request made it first, and counted it.
    if not found then return; end if;
    counted := array[case when p_signed_in then 'account' else 'new' end];
  else
    if seen.last_seen < today then
      counted := array[case when p_signed_in then 'account' else 'returning' end];
    end if;
    if p_signed_in and seen.signed_in_on is null and seen.guest_on is not null then
      counted := counted || 'converted'::text;
    end if;
    update private.installs set
      platform = p_platform,
      last_seen = today,
      days_seen = days_seen + case when seen.last_seen < today then 1 else 0 end,
      guest_on = coalesce(guest_on, case when not p_signed_in then today end),
      signed_in_on = coalesce(signed_in_on, case when p_signed_in then today end),
      parcels = p_parcels
    where install = p_install;
  end if;

  foreach each_kind in array counted loop
    insert into metrics.installs_daily as daily (day, platform, kind, installs)
    values (today, p_platform, each_kind, 1)
    on conflict (day, platform, kind) do update set installs = daily.installs + 1;
  end loop;
end;
$$;
revoke all on function public.record_install_seen(text, text, boolean, integer) from public, anon, authenticated;
grant execute on function public.record_install_seen(text, text, boolean, integer) to service_role;

create view metrics.active_installs as
select install.platform,
  count(*) filter (where install.last_seen >= today.day) as day,
  count(*) filter (where install.last_seen >= today.day - 6) as week,
  count(*) filter (where install.last_seen >= today.day - 29) as month,
  count(*) filter (where install.last_seen >= today.day and install.signed_in_on is null) as guests_day,
  count(*) filter (where install.last_seen >= today.day - 6 and install.signed_in_on is null) as guests_week,
  count(*) filter (where install.last_seen >= today.day - 29 and install.signed_in_on is null) as guests_month
from private.installs as install
cross join (select (now() at time zone 'UTC')::date as day) as today
group by install.platform;

comment on view metrics.active_installs is
  'Installs seen in the last 1, 7 and 30 UTC days by platform, and those never seen signed in.';

create view metrics.guest_cohorts as
select date_trunc('week', install.first_seen)::date as week,
  count(*) as guests,
  -- One day only, once the week is over and a week more has gone by.
  case when date_trunc('week', install.first_seen)::date + 14 <= (now() at time zone 'UTC')::date
    then count(*) filter (where install.days_seen = 1 and install.signed_in_on is null) end as one_day,
  count(*) filter (where install.days_seen >= 2 and install.signed_in_on is null) as came_back,
  count(*) filter (where install.signed_in_on is not null) as signed_in,
  round(avg(install.parcels) filter (where install.signed_in_on is null), 2) as parcels_per_guest
from private.installs as install
where install.guest_on = install.first_seen
  and install.first_seen >= date_trunc('week', (now() at time zone 'UTC')::date)::date - 7 * 11
group by 1;

comment on view metrics.guest_cohorts is
  'Installs first seen without an account in each of the last 12 weeks: one day only, came back, signed in since.';

grant select on metrics.installs_daily, metrics.active_installs, metrics.guest_cohorts to metrics_reader;

-- As before, and installs not seen for 90 days, or first seen 25 months ago, go.
create or replace function public.record_usage_metrics()
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

  if not exists (select 1 from metrics.goals_daily
      where day = today - 1 and as_of >= today::timestamp at time zone 'UTC') then
    perform metrics.record_provider_requests(today - 1);
    perform metrics.record_goals(today - 1);
  end if;
  if not exists (select 1 from metrics.goals_daily where day = today and as_of > now() - interval '10 minutes') then
    perform metrics.record_provider_requests(today);
    perform metrics.record_goals(today);
  end if;

  delete from private.installs where last_seen < today - 90 or first_seen < (today - interval '25 months')::date;
end;
$$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261119160000_install_counts') on conflict do nothing;

commit;
