\set ON_ERROR_STOP on
begin;

-- Only the server reads and writes the scraper's holds: an iMile hold's key is a tracking number.
do $$
begin
  if has_table_privilege('anon', 'public.scraper_holds', 'SELECT')
      or has_table_privilege('authenticated', 'public.scraper_holds', 'SELECT')
      or has_table_privilege('authenticated', 'public.scraper_holds', 'INSERT') then
    raise exception 'public database roles can read the scraper holds';
  end if;
  if not has_table_privilege('service_role', 'public.scraper_holds', 'SELECT')
      or not has_table_privilege('service_role', 'public.scraper_holds', 'INSERT')
      or not has_table_privilege('service_role', 'public.scraper_holds', 'UPDATE')
      or not has_table_privilege('service_role', 'public.scraper_holds', 'DELETE') then
    raise exception 'the service role cannot keep the scraper holds';
  end if;
  if not (select relrowsecurity from pg_class where oid = 'public.scraper_holds'::regclass)
      or exists (select 1 from pg_policies where schemaname = 'public' and tablename = 'scraper_holds') then
    raise exception 'the scraper holds are readable through row level security';
  end if;
end;
$$;

-- A hold is saved again under its key, and a key names its kind.
insert into public.scraper_holds (key, until) values ('direct-rest/mailamericas 1a2b3c', '2026-11-19T18:00:00Z');
insert into public.scraper_holds (key, until, value) values ('direct-rest/mailamericas 1a2b3c', '2026-11-19T20:00:00Z', '{"reads":2}')
on conflict (key) do update set until = excluded.until, value = excluded.value;
do $$
begin
  if (select until from public.scraper_holds where key = 'direct-rest/mailamericas 1a2b3c') <> '2026-11-19T20:00:00Z'
      or (select value->>'reads' from public.scraper_holds where key = 'direct-rest/mailamericas 1a2b3c') <> '2' then
    raise exception 'a scraper hold was not saved again under its key';
  end if;
  begin
    insert into public.scraper_holds (key, until) values ('no kind', now());
    raise exception 'a scraper hold took a key without its kind';
  exception when check_violation then null;
  end;
end;
$$;

rollback;
