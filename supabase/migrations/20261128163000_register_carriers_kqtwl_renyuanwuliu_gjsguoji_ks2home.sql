-- Registers Kaiqitong Logistics (kqtwl), Renyuan Logistics (renyuanwuliu), GJS Express (gjsguoji) and
-- Ks2Home (ks2home). The constraint and the functions are extended in place,
-- after 20261128160000_register_carriers_parcel100_dyb_express_stwles_xhongda56 last extended them.
-- Database gate: carriers added kqtwl, renyuanwuliu, gjsguoji, ks2home

do $migration$
declare
  ids constant text[] := array['kqtwl', 'renyuanwuliu', 'gjsguoji', 'ks2home'];
  list_end constant text := '''xhongda56'',(\s+)''intl-post'', ''unknown''';
  constraint_end constant text := '''xhongda56''::text, ''intl-post''::text';
  extended text := '''xhongda56'',\1';
  constraint_extended text := '''xhongda56''::text, ';
  signature text;
  definition text;
  expression text;
  carrier text;
begin
  foreach carrier in array ids loop
    extended := extended || quote_literal(carrier) || ',\1';
    constraint_extended := constraint_extended || quote_literal(carrier) || '::text, ';
  end loop;
  extended := extended || '''intl-post'', ''unknown''';
  constraint_extended := constraint_extended || '''intl-post''::text';

  foreach signature in array array[
    'public.create_owned_package(text,text,text,text,text)',
    'public.change_owned_package_carrier(uuid,text,text,text)'
  ] loop
    definition := pg_get_functiondef(signature::regprocedure);
    if (select count(*) from regexp_matches(definition, list_end, 'g')) <> 1 then
      raise exception 'Carrier list changed in %', signature;
    end if;
    foreach carrier in array ids loop
      if position(quote_literal(carrier) in definition) <> 0 then
        raise exception 'Carrier % already in %', carrier, signature;
      end if;
    end loop;
    execute regexp_replace(definition, list_end, extended);
  end loop;

  select pg_get_expr(conbin, conrelid) into expression
  from pg_constraint
  where conrelid = 'public.packages'::regclass and conname = 'packages_carrier_check';
  if expression is null or (select count(*) from regexp_matches(expression, constraint_end, 'g')) <> 1 then
    raise exception 'Carrier constraint changed';
  end if;
  foreach carrier in array ids loop
    if position(quote_literal(carrier) || '::text' in expression) <> 0 then
      raise exception 'Carrier % already in the constraint', carrier;
    end if;
  end loop;
  execute 'alter table public.packages drop constraint packages_carrier_check, add constraint packages_carrier_check check ('
    || replace(expression, constraint_end, constraint_extended)
    || ') not valid';
end;
$migration$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261128163000_register_carriers_kqtwl_renyuanwuliu_gjsguoji_ks2home') on conflict do nothing;
