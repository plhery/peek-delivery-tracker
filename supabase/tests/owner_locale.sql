\set ON_ERROR_STOP on
begin;

insert into auth.users (id, email, raw_user_meta_data) values
  ('97000000-0000-0000-0000-000000000001', 'owner-locale@example.invalid', '{"locale":"fr"}'),
  ('97000000-0000-0000-0000-000000000002', 'owner-no-locale@example.invalid', null);
set local role service_role;

insert into public.packages (id, user_id, tracking_number, carrier, one_off) values
  ('97000000-0000-0000-0000-000000000011', '97000000-0000-0000-0000-000000000001', 'TEST000000001CH', 'swiss-post', false),
  ('97000000-0000-0000-0000-000000000012', '97000000-0000-0000-0000-000000000002', 'TEST000000002CH', 'swiss-post', false),
  ('97000000-0000-0000-0000-000000000013', null, 'TEST000000003CH', 'swiss-post', true);

do $$
declare
  found text;
begin
  select string_agg(p.id::text || '=' || coalesce(public.owner_locale(p), '-'), ',' order by p.id) into found
  from public.packages p where p.id::text like '97000000-%';
  if found is distinct from '97000000-0000-0000-0000-000000000011=fr,97000000-0000-0000-0000-000000000012=-,97000000-0000-0000-0000-000000000013=-' then
    raise exception 'owner_locale gave %', found;
  end if;
end;
$$;

reset role;
do $$
begin
  if has_function_privilege('authenticated', 'public.owner_locale(public.packages)', 'execute') then
    raise exception 'owner_locale is callable by a signed-in user';
  end if;
end;
$$;

rollback;
select 'owner locale assertions passed' as result;
