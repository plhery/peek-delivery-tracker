-- A link's alert steps aside (`account_endpoint`) for an account parcel of the number only when that parcel can tell
-- the update: of the shared parcel's carrier (any, when the shared parcel's is `unknown`), with the postcode and
-- phone digits the shared parcel has. A viewer's kept copy of an 8-digit Mondial Relay parcel has no postcode and
-- finds nothing, and an old parcel of another carrier that used the number tells nothing: the link tells the device.
-- (20261119070000_one_alert_per_device.)
do $$
declare
  definition text;
  changed text;
begin
  perform set_config('search_path', '', true);
  definition := regexp_replace(pg_get_viewdef('public.pending_parcel_link_alerts'::regclass, true), ';\s*$', '');
  if position('own_package.phone_digits' in definition) > 0 then return; end if;
  changed := regexp_replace(definition,
    'own_package\.tracking_number\s*=\s*alerted\.tracking_number\s+AND\s+own_package\.user_id\s+IS\s+NOT\s+NULL',
    'own_package.tracking_number = alerted.tracking_number AND own_package.user_id IS NOT NULL'
      || ' AND (own_package.carrier = alerted.carrier OR alerted.carrier = ''unknown'')'
      || ' AND (alerted.dpd_postcode IS NULL OR lower(replace(own_package.dpd_postcode, '' '', ''''))'
      || ' = lower(replace(alerted.dpd_postcode, '' '', '''')))'
      || ' AND (alerted.phone_digits IS NULL OR own_package.phone_digits = alerted.phone_digits)');
  if changed = definition then
    raise exception 'pending_parcel_link_alerts has no account parcel of the number to narrow';
  end if;
  execute format('create or replace view public.pending_parcel_link_alerts with (security_invoker = true) as %s', changed);
end;
$$;

revoke all on public.pending_parcel_link_alerts from public, anon, authenticated;
grant select on public.pending_parcel_link_alerts to service_role;

insert into public.applied_migrations (name) values ('20261120190000_link_alert_tracking_copy') on conflict do nothing;
