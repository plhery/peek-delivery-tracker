-- A carrier's adapter that answers with a reply field holding a delivery estimate or slot it
-- does not read yet names the field; the check keeps it as a routing signal with the number,
-- so the adapters' review can capture the reply and teach the adapter its shape.
alter table public.tracking_routing_signals drop constraint tracking_routing_signals_signal_check;
alter table public.tracking_routing_signals add constraint tracking_routing_signals_signal_check check (signal in (
  'carrier_coverage_discovered', 'carrier_auto_swapped', 'carrier_mismatch_confirmed', 'direct_support_opportunity',
  'unread_delivery_estimate'));

create or replace function public.record_tracking_routing_signals(
  p_attempt_id uuid, p_package_id uuid, p_tracking_number text, p_signals jsonb
)
returns integer language plpgsql security definer set search_path = pg_catalog as $$
declare
  number text := upper(regexp_replace(coalesce(p_tracking_number, ''), '[[:space:].-]', '', 'g'));
  parcel uuid;
  s jsonb;
  recorded integer := 0;
begin
  if length(number) not between 1 and 64 or jsonb_typeof(p_signals) <> 'array' or jsonb_array_length(p_signals) > 32 then
    raise exception 'Invalid tracking routing signals' using errcode = '22023';
  end if;
  delete from public.tracking_routing_signals where last_seen < now() - interval '90 days';
  select id into parcel from public.packages where id = p_package_id;
  for s in select value from jsonb_array_elements(p_signals) loop
    if jsonb_typeof(s) <> 'object'
        or coalesce(s->>'signal', '') not in ('carrier_coverage_discovered', 'carrier_auto_swapped',
          'carrier_mismatch_confirmed', 'direct_support_opportunity', 'unread_delivery_estimate')
        or length(coalesce(s->>'carrier', '')) not between 1 and 100
        or length(coalesce(s->>'subject', '')) not between 1 and 100 then
      raise exception 'Invalid tracking routing signal' using errcode = '22023';
    end if;
    insert into public.tracking_routing_signals as stored (signal, carrier, subject, tracking_number, package_id, last_attempt_id)
    values (s->>'signal', s->>'carrier', s->>'subject', number, parcel, p_attempt_id)
    on conflict (signal, carrier, subject, tracking_number) do update set
      count = stored.count + 1, last_seen = now(), last_attempt_id = excluded.last_attempt_id,
      package_id = coalesce(excluded.package_id, stored.package_id);
    recorded := recorded + 1;
  end loop;
  return recorded;
end;
$$;
revoke all on function public.record_tracking_routing_signals(uuid,uuid,text,jsonb) from public, anon, authenticated;
grant execute on function public.record_tracking_routing_signals(uuid,uuid,text,jsonb) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261105230000_unread_delivery_estimate_signal') on conflict do nothing;
