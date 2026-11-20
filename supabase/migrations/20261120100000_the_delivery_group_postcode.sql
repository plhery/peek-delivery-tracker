-- Keep TDG's delivery postcode optional while accepting it wherever a parcel is created or changed.
-- Existing carrier branches and function privileges are preserved.
-- Database gate: inputs added the-delivery-group: postcode, validator internationalPostcode, optional, pattern ^(?=.{3,12}$)(?=.*[0-9])[A-Za-z0-9]+(?:[ -][A-Za-z0-9]+)*$

do $migration$
declare
  signature text;
  definition text;
  generic_list text := $list$'dpd', 'gls-de', 'gls-es', 'mondial-relay', 'xdp', 'gebrueder-weiss', 'asendia', 'gls-pl', 'relaytech', 'dx', 'arrow-xl', 'gls-at', 'gls-nl', 'ids-logistik', 'packs', 'transmission', 'postmedia-parcel-services'$list$;
  postcode_carriers text := $list$'dpd', 'dpd-de', 'gls-ch', 'gls-de', 'gls-es', 'mondial-relay', 'heppner', 'paack', 'colis-prive', 'xdp', 'gebrueder-weiss', 'asendia', 'gls-pl', 'relaytech', 'dx', 'arrow-xl', 'gls-at', 'gls-nl', 'ids-logistik', 'packs', 'transmission', 'postmedia-parcel-services', 'dhl', 'spring-gds', 'a-tu-hora-express', 'canpar'$list$;
  expression text;
begin
  foreach signature in array array[
    'public.create_owned_package(text,text,text,text,text)',
    'public.change_owned_package_carrier(uuid,text,text,text)'
  ] loop
    definition := pg_get_functiondef(signature::regprocedure);
    if (length(definition) - length(replace(definition, generic_list, ''))) / length(generic_list) <> 2
        or (length(definition) - length(replace(definition, postcode_carriers, ''))) / length(postcode_carriers) <> 1 then
      raise exception 'Delivery-postcode branches changed in %', signature;
    end if;
    execute replace(replace(definition,
      generic_list, generic_list || ', ''the-delivery-group'''),
      postcode_carriers, postcode_carriers || ', ''the-delivery-group''');
  end loop;

  select pg_get_expr(conbin, conrelid) into expression
  from pg_constraint
  where conrelid = 'public.packages'::regclass and conname = 'packages_dpd_postcode_check';
  if expression is null or position('the-delivery-group' in expression) <> 0 then
    raise exception 'Delivery-postcode constraint changed';
  end if;
  execute 'alter table public.packages drop constraint packages_dpd_postcode_check, '
    || 'add constraint packages_dpd_postcode_check check ((' || expression || ') or ('
    || 'carrier = ''the-delivery-group'' and char_length(dpd_postcode) between 3 and 12 '
    || 'and dpd_postcode ~ ''[0-9]'' and dpd_postcode ~ ''^[A-Z0-9]+([ -][A-Z0-9]+)*$'')) not valid';
end;
$migration$;

alter table public.packages validate constraint packages_dpd_postcode_check;
notify pgrst, 'reload schema';
insert into public.applied_migrations (name) values ('20261120100000_the_delivery_group_postcode') on conflict do nothing;
