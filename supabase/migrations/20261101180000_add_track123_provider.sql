-- Database gate: providers added Track123
-- Anonymous FedEx-only fallback uses the existing provider admission protocol.
begin;
alter table public.tracking_provider_health drop constraint tracking_provider_health_provider_check;
alter table public.tracking_provider_health add constraint tracking_provider_health_provider_check
  check (provider in ('17TRACK', 'ParcelsApp', 'Ship24', 'Postal Ninja', 'Track123', 'Track718', 'UPU'));
-- Keep the aggregate classification aligned with the admitted provider names.
do $migration$
declare definition text;
begin
  definition := pg_get_functiondef('metrics.record_goals(date)'::regprocedure);
  if position('''ParcelsApp'', ''17TRACK'', ''Ship24'', ''Postal Ninja'', ''UPU''' in definition) = 0 then
    raise exception 'Review provider classification before applying Track123';
  end if;
  execute replace(definition,
    '''ParcelsApp'', ''17TRACK'', ''Ship24'', ''Postal Ninja'', ''UPU''',
    '''ParcelsApp'', ''17TRACK'', ''Ship24'', ''Postal Ninja'', ''Track123'', ''UPU''');
end;
$migration$;
insert into public.applied_migrations (name) values ('20261101180000_add_track123_provider') on conflict do nothing;
commit;
