-- Lets the read-only role run public.hidden_provider_copy, so history reads can tell a provider's copies the server
-- hides from the rows it shows. The function is stable and runs as its caller, on tables the role can already read.
-- Later `create or replace` keeps the grant. Databases without the role (CI) skip it.

do $$
begin
  if exists (select 1 from pg_catalog.pg_roles where rolname = 'supabase_read_only_user') then
    grant execute on function public.hidden_provider_copy(uuid) to supabase_read_only_user;
  end if;
end $$;

insert into public.applied_migrations (name) values ('20261120150000_read_only_hidden_provider_copy') on conflict do nothing;
