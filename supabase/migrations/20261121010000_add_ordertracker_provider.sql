-- Accept Ordertracker, a universal provider the scraper asks about a few carriers whose feeds it compared, in the
-- providers' shared leases and allowances. Its parcels count as another provider's in metrics.goals_daily, as any
-- provider's without a column of its own.
-- Database gate: providers added Ordertracker
alter table public.tracking_provider_health drop constraint tracking_provider_health_provider_check;
alter table public.tracking_provider_health add constraint tracking_provider_health_provider_check
  check (provider in ('17TRACK', 'Ordertracker', 'ParcelsApp', 'Ship24', 'Postal Ninja', 'Track123', 'Track718', 'UPU'));
insert into public.applied_migrations (name) values ('20261121010000_add_ordertracker_provider') on conflict do nothing;
