-- A JRS Express reference is the airbill number, a hyphen and the branch code printed beside it, and
-- JRS Express's tracker finds a shipment only with both: they vary in length, so the digits alone do
-- not give the reference back. A parcel added under JRS Express keeps the reference as typed, without
-- spaces, when neither part is zeros alone; any other number, and a JRS Express number of another
-- shape, is stored as before, in capitals without spaces, dots or dashes. The scraper's
-- carrierTrackingNumber() makes the same choice.
--
-- A parcel moved to another carrier keeps its reference, which that carrier's lookup reads without
-- the hyphen, so the format check takes the reference's shape whatever the carrier.
--
-- private.carrier_tracking_number, which create_owned_package and create_one_off_parcel call since
-- 20261123224500_keep_tiui_folio_hyphens, gains the JRS Express case.
-- Apply this before deploying the server that sends a JRS Express reference with its hyphen.

do $migration$
declare
  definition text := pg_get_functiondef('private.carrier_tracking_number(text,text)'::regprocedure);
begin
  if position('jrs-express' in definition) <> 0 then
    raise exception 'carrier_tracking_number already keeps a JRS Express reference';
  end if;
  if position($folio$p_carrier = 'tiui'$folio$ in definition) = 0
      or position('[0-9]{1,4}-[0-9]{1,3}-[0-9]{1,6}' in definition) = 0 then
    raise exception 'carrier_tracking_number changed';
  end if;
  if position('private.carrier_tracking_number' in pg_get_functiondef('public.create_owned_package(text,text,text,text,text)'::regprocedure)) = 0
      or position('private.carrier_tracking_number' in pg_get_functiondef('public.create_one_off_parcel(text,text,text,text,text,text,text)'::regprocedure)) = 0 then
    raise exception 'create_owned_package or create_one_off_parcel no longer calls carrier_tracking_number';
  end if;
end;
$migration$;

create or replace function private.carrier_tracking_number(p_tracking_number text, p_carrier text)
returns text
language sql
immutable
set search_path = ''
as $$
  select case
    when p_carrier = 'tiui'
      and regexp_replace(coalesce(p_tracking_number, ''), '[[:space:]]', '', 'g') ~ '^[0-9]{1,4}-[0-9]{1,3}-[0-9]{1,6}$'
      then regexp_replace(p_tracking_number, '[[:space:]]', '', 'g')
    -- An airbill and its branch code, neither of them zeros alone.
    when p_carrier = 'jrs-express'
      and regexp_replace(coalesce(p_tracking_number, ''), '[[:space:]]', '', 'g') ~ '^[0-9]{1,10}-[0-9]{1,4}$'
      and regexp_replace(coalesce(p_tracking_number, ''), '[[:space:]]', '', 'g') !~ '(^|-)0+(-|$)'
      then regexp_replace(p_tracking_number, '[[:space:]]', '', 'g')
    else upper(regexp_replace(coalesce(p_tracking_number, ''), '[[:space:].-]', '', 'g'))
  end;
$$;

revoke all on function private.carrier_tracking_number(text, text) from public, anon, authenticated;

do $migration$
declare
  expression text;
begin
  select pg_get_expr(conbin, conrelid) into expression
  from pg_constraint
  where conrelid = 'public.packages'::regclass and conname = 'packages_tracking_number_format_check';
  if expression is null
      or position('[0-9]{1,4}-[0-9]{1,3}-[0-9]{1,6}' in expression) = 0
      or position('[0-9]{1,10}-[0-9]{1,4}' in expression) <> 0 then
    raise exception 'Tracking number format constraint changed';
  end if;
  execute 'alter table public.packages drop constraint packages_tracking_number_format_check, '
    || 'add constraint packages_tracking_number_format_check check (('
    || expression
    || $check$) or tracking_number ~ '^[0-9]{1,10}-[0-9]{1,4}$') not valid$check$;
end;
$migration$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261124095500_keep_jrs_express_reference_hyphen') on conflict do nothing;
