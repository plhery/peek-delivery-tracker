-- Database gate: providers added Track718
begin;
alter table public.tracking_provider_health drop constraint tracking_provider_health_provider_check;
alter table public.tracking_provider_health add constraint tracking_provider_health_provider_check
  check (provider in ('17TRACK', 'ParcelsApp', 'Ship24', 'Postal Ninja', 'UPU', 'Track718'));
insert into public.applied_migrations (name) values ('20261101173000_track718_juxi_provider') on conflict do nothing;
commit;
