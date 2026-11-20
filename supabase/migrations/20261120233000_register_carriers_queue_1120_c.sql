-- Accept Evergreen Line, Höegh Autoliners, Qatar Airways Cargo and forty-seven more ocean, air cargo and courier lines as carriers.
-- The constraint and the functions are extended in place, after
-- 20261120223400_register_feima last extended them.
-- Database gate: carriers added aerolineas-argentinas-cargo, alfa-mensajes, atlantic-container-line, bermuda-container-line, cadena-courier, calidex-courier, certipostal, citilink-cargo, conexiones-empresariales, cordelia-shipping, cotaxi, dongjin-shipping, eimskip, ellerman-city-liners, enviamos-comunicaciones, epex-colombia, esm-logistica, ethiopian-cargo, evergreen-line, g2-ocean, garuda-indonesia-cargo, hecny-shipping, hede-shipping, heung-a-line, hoegh-autoliners, honour-lane-shipping, k-line-roro, kenya-airways-cargo, leschaco, mol-ace, pacific-international-lines, procon-express-lines, pronto-envios, qatar-airways-cargo, saco-shipping-line, salam-pacific-indonesia-lines, sallaum-lines, sea-legend-line, sfl-worldwide, sinokor-merchant-marine, sm-line, tailwind-shipping-lines, tanto-intim-line, thai-airways-cargo, translink-shipping, transportes-chevalier, umac-cargo, united-africa-feeder-line, volaris-cargo, wallenius-wilhelmsen

do $migration$
declare
  ids constant text[] := array['aerolineas-argentinas-cargo', 'alfa-mensajes', 'atlantic-container-line',
    'bermuda-container-line', 'cadena-courier', 'calidex-courier', 'certipostal', 'citilink-cargo',
    'conexiones-empresariales', 'cordelia-shipping', 'cotaxi', 'dongjin-shipping', 'eimskip', 'ellerman-city-liners',
    'enviamos-comunicaciones', 'epex-colombia', 'esm-logistica', 'ethiopian-cargo', 'evergreen-line', 'g2-ocean',
    'garuda-indonesia-cargo', 'hecny-shipping', 'hede-shipping', 'heung-a-line', 'hoegh-autoliners',
    'honour-lane-shipping', 'k-line-roro', 'kenya-airways-cargo', 'leschaco', 'mol-ace',
    'pacific-international-lines', 'procon-express-lines', 'pronto-envios', 'qatar-airways-cargo',
    'saco-shipping-line', 'salam-pacific-indonesia-lines', 'sallaum-lines', 'sea-legend-line', 'sfl-worldwide',
    'sinokor-merchant-marine', 'sm-line', 'tailwind-shipping-lines', 'tanto-intim-line', 'thai-airways-cargo',
    'translink-shipping', 'transportes-chevalier', 'umac-cargo', 'united-africa-feeder-line', 'volaris-cargo',
    'wallenius-wilhelmsen'];
  list_end constant text := '''feima'',(\s+)''intl-post'', ''unknown''';
  constraint_end constant text := '''feima''::text, ''intl-post''::text';
  extended text := '''feima'',\1';
  constraint_extended text := '''feima''::text, ';
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

insert into public.applied_migrations (name) values ('20261120233000_register_carriers_queue_1120_c') on conflict do nothing;
