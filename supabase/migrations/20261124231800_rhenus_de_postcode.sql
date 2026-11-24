-- Accept Rhenus Germany's optional delivery postcode, five digits as for DPD Germany, wherever a
-- parcel is created or changed. Existing carrier branches and function privileges are preserved.
-- Database gate: inputs added rhenus-de: postcode, validator germanyPostcode, optional, pattern ^[0-9]{5}$

do $migration$
declare
  signature text;
  definition text;
  postcode_carriers text := $list$'dpd', 'dpd-de', 'gls-ch', 'gls-de', 'gls-es', 'mondial-relay', 'heppner', 'paack', 'colis-prive', 'xdp', 'gebrueder-weiss', 'asendia', 'gls-pl', 'relaytech', 'dx', 'arrow-xl', 'gls-at', 'gls-nl', 'ids-logistik', 'packs', 'transmission', 'postmedia-parcel-services', 'dhl', 'spring-gds', 'a-tu-hora-express', 'canpar', 'the-delivery-group'$list$;
  create_branch text := $branch$(p_carrier = 'dpd-de' and normalized_postcode is not null and normalized_postcode !~ '^[0-9]{5}$')$branch$;
  change_branch text := $branch$(normalized_carrier = 'dpd-de' and normalized_postcode is not null and normalized_postcode !~ '^[0-9]{5}$')$branch$;
  expression text;
begin
  foreach signature in array array[
    'public.create_owned_package(text,text,text,text,text)',
    'public.change_owned_package_carrier(uuid,text,text,text)'
  ] loop
    definition := pg_get_functiondef(signature::regprocedure);
    if (length(definition) - length(replace(definition, postcode_carriers || ')', ''))) / length(postcode_carriers || ')') <> 1
        or (length(definition) - length(replace(definition, create_branch, ''))) / length(create_branch)
          + (length(definition) - length(replace(definition, change_branch, ''))) / length(change_branch) <> 1 then
      raise exception 'Delivery-postcode branches changed in %', signature;
    end if;
    execute replace(replace(replace(definition,
      postcode_carriers || ')', postcode_carriers || ', ''rhenus-de'')'),
      create_branch, replace(create_branch, $$p_carrier = 'dpd-de'$$, $$p_carrier in ('dpd-de', 'rhenus-de')$$)),
      change_branch, replace(change_branch, $$normalized_carrier = 'dpd-de'$$, $$normalized_carrier in ('dpd-de', 'rhenus-de')$$));
  end loop;

  select pg_get_expr(conbin, conrelid) into expression
  from pg_constraint
  where conrelid = 'public.packages'::regclass and conname = 'packages_dpd_postcode_check';
  if expression is null or position('rhenus-de' in expression) <> 0 then
    raise exception 'Delivery-postcode constraint changed';
  end if;
  execute 'alter table public.packages drop constraint packages_dpd_postcode_check, '
    || 'add constraint packages_dpd_postcode_check check ((' || expression || ') or ('
    || 'carrier = ''rhenus-de'' and dpd_postcode ~ ''^[0-9]{5}$'')) not valid';
end;
$migration$;

alter table public.packages validate constraint packages_dpd_postcode_check;
notify pgrst, 'reload schema';
insert into public.applied_migrations (name) values ('20261124231800_rhenus_de_postcode') on conflict do nothing;
