-- Accept Muser International Logistics and Zhongzhou Logistics, tracked through universal providers.
-- The constraint and the functions are extended in place: 20261120100000_the_delivery_group_postcode
-- changed the functions after 20261119141200_register_carrier_gls_hu last defined them.
-- Database gate: carriers added muser, zhongzhou-logistics

do $migration$
declare
  signature text;
  definition text;
  expression text;
  list_end constant text := '''gls-hu'',(\s+)''intl-post'', ''unknown''';
  extended constant text := '''gls-hu'',\1''muser'',\1''zhongzhou-logistics'',\1''intl-post'', ''unknown''';
begin
  foreach signature in array array[
    'public.create_owned_package(text,text,text,text,text)',
    'public.change_owned_package_carrier(uuid,text,text,text)'
  ] loop
    definition := pg_get_functiondef(signature::regprocedure);
    if (select count(*) from regexp_matches(definition, list_end, 'g')) <> 1
        or position('''muser''' in definition) <> 0 or position('''zhongzhou-logistics''' in definition) <> 0 then
      raise exception 'Carrier list changed in %', signature;
    end if;
    execute regexp_replace(definition, list_end, extended);
  end loop;

  select pg_get_expr(conbin, conrelid) into expression
  from pg_constraint
  where conrelid = 'public.packages'::regclass and conname = 'packages_carrier_check';
  if expression is null or (select count(*) from regexp_matches(expression, '''gls-hu''::text, ''intl-post''::text', 'g')) <> 1
      or position('''muser''' in expression) <> 0 or position('''zhongzhou-logistics''' in expression) <> 0 then
    raise exception 'Carrier constraint changed';
  end if;
  execute 'alter table public.packages drop constraint packages_carrier_check, add constraint packages_carrier_check check ('
    || replace(expression, '''gls-hu''::text, ''intl-post''::text',
      '''gls-hu''::text, ''muser''::text, ''zhongzhou-logistics''::text, ''intl-post''::text')
    || ') not valid';
end;
$migration$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261120130000_register_carriers_muser_zhongzhou') on conflict do nothing;
