-- A Tiui folio is three groups of digits joined by hyphens, and Tiui's tracker finds it only with
-- them: its groups vary in length, so the digits alone do not give the folio back. A parcel added
-- under Tiui keeps the folio as typed, without spaces; any other number, and a Tiui number of
-- another shape, is stored as before, in capitals without spaces, dots or dashes. The scraper's
-- carrierTrackingNumber() makes the same choice.
--
-- A parcel moved to another carrier keeps its folio, which that carrier's lookup reads without
-- the hyphens, so the format check takes the folio's shape whatever the carrier.
--
-- create_owned_package and create_one_off_parcel are extended in place, after
-- 20261123100500_register_carrier_callcourier last extended create_owned_package.
-- Apply this before deploying the server that sends a Tiui folio with its hyphens.

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
    else upper(regexp_replace(coalesce(p_tracking_number, ''), '[[:space:].-]', '', 'g'))
  end;
$$;

revoke all on function private.carrier_tracking_number(text, text) from public, anon, authenticated;

do $migration$
declare
  -- The number is checked in its plain form first; the folio replaces it after the check.
  owned_check constant text := E'raise exception ''Invalid tracking number'' using errcode = ''22023'';\n  end if;\n';
  one_off_check constant text := E'raise exception ''Invalid one-off parcel'' using errcode = ''22023'';\n  end if;\n';
  kept constant text := E'  normalized_tracking := private.carrier_tracking_number(p_tracking_number, p_carrier);\n';
  definition text;
  expression text;
begin
  definition := pg_get_functiondef('public.create_owned_package(text,text,text,text,text)'::regprocedure);
  if position('private.carrier_tracking_number' in definition) <> 0 then
    raise exception 'create_owned_package already keeps a Tiui folio';
  end if;
  if (select count(*) from regexp_matches(definition, 'Invalid tracking number', 'g')) <> 1
      or position(owned_check in definition) = 0 then
    raise exception 'create_owned_package''s number check changed';
  end if;
  execute replace(definition, owned_check, owned_check || kept);

  definition := pg_get_functiondef('public.create_one_off_parcel(text,text,text,text,text,text,text)'::regprocedure);
  if position('private.carrier_tracking_number' in definition) <> 0 then
    raise exception 'create_one_off_parcel already keeps a Tiui folio';
  end if;
  if (select count(*) from regexp_matches(definition, 'Invalid one-off parcel', 'g')) <> 1
      or position(one_off_check in definition) = 0 then
    raise exception 'create_one_off_parcel''s number check changed';
  end if;
  execute replace(definition, one_off_check, one_off_check || kept);

  select pg_get_expr(conbin, conrelid) into expression
  from pg_constraint
  where conrelid = 'public.packages'::regclass and conname = 'packages_tracking_number_format_check';
  if expression is null or position('[0-9]{1,4}-[0-9]{1,3}-[0-9]{1,6}' in expression) <> 0 then
    raise exception 'Tracking number format constraint changed';
  end if;
  execute 'alter table public.packages drop constraint packages_tracking_number_format_check, '
    || 'add constraint packages_tracking_number_format_check check (('
    || expression
    || $check$) or tracking_number ~ '^[0-9]{1,4}-[0-9]{1,3}-[0-9]{1,6}$') not valid$check$;
end;
$migration$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261123224500_keep_tiui_folio_hyphens') on conflict do nothing;
