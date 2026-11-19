\set ON_ERROR_STOP on
begin;

-- Statement texts and durations stay with the database owner.
do $$
begin
  if has_table_privilege('anon', 'extensions.pg_stat_statements', 'SELECT')
      or has_table_privilege('authenticated', 'extensions.pg_stat_statements', 'SELECT')
      or has_table_privilege('service_role', 'extensions.pg_stat_statements', 'SELECT')
      or exists (
        select 1
        from pg_depend as depend
        join pg_extension as extension on extension.oid = depend.refobjid
        cross join unnest(array['anon', 'authenticated', 'service_role']) as role(name)
        where extension.extname = 'pg_stat_statements' and depend.classid = 'pg_proc'::regclass and depend.deptype = 'e'
          and has_function_privilege(role.name, depend.objid, 'EXECUTE')
      ) then
    raise exception 'a client role can read or reset the statement durations';
  end if;
end;
$$;

rollback;
