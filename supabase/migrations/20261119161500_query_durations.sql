-- Statement durations, so a statement timeout for the server's role can be set on figures.
--
-- Postgres already loads pg_stat_statements, and it counts each normalized statement's calls and
-- durations per role since the last restart. The extension adds the view that reads them. Its
-- text has the values replaced with placeholders, but only the database owner reads it.


create extension if not exists pg_stat_statements with schema extensions;
revoke all on extensions.pg_stat_statements, extensions.pg_stat_statements_info from public, anon, authenticated, service_role;
-- The functions differ between Postgres versions: revoke each one the extension owns.
do $$
declare
  owned regprocedure;
begin
  for owned in
    select depend.objid::regprocedure
    from pg_depend as depend
    join pg_extension as extension on extension.oid = depend.refobjid
    where extension.extname = 'pg_stat_statements' and depend.classid = 'pg_proc'::regclass and depend.deptype = 'e'
  loop
    execute format('revoke all on function %s from public, anon, authenticated, service_role', owned);
  end loop;
end;
$$;

insert into public.applied_migrations (name) values ('20261119161500_query_durations') on conflict do nothing;

