-- missing_migrations read only the first 1,000 names it was given, so once the repository
-- held more migrations than that, the newest went unchecked and a deployment that lacked
-- them went through. It reads every name now, and refuses a list of more than 10,000
-- rather than shorten it, which still bounds what an anonymous caller can ask of it.
create or replace function public.missing_migrations(p_names text[])
returns text[]
language plpgsql
stable
security definer
set search_path = pg_catalog
as $$
begin
  if cardinality(p_names) > 10000 then
    raise exception 'missing_migrations reads at most 10,000 names, not %', cardinality(p_names)
      using errcode = 'invalid_parameter_value';
  end if;
  return (
    select coalesce(array_agg(wanted.name order by wanted.name), '{}')
    from unnest(p_names) as wanted(name)
    where not exists (select 1 from public.applied_migrations as applied where applied.name = wanted.name)
  );
end;
$$;
revoke all on function public.missing_migrations(text[]) from public;
grant execute on function public.missing_migrations(text[]) to anon, authenticated, service_role;

insert into public.applied_migrations (name) values ('20261115183000_missing_migrations_reads_every_name') on conflict do nothing;

notify pgrst, 'reload schema';
