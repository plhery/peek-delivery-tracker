\set ON_ERROR_STOP on
begin;

-- The API's login, as PostgREST connects.
do $$ begin
  if not exists (select from pg_roles where rolname = 'authenticator') then
    create role authenticator noinherit login;
  end if;
end $$;
grant service_role to authenticator;

insert into public.packages (id, user_id, tracking_number, carrier, current_stage)
values ('20000000-0000-0000-0000-000000000901', '10000000-0000-0000-0000-000000000001', 'EXAMPLE0000000901', 'kun-express', 'delivered');

-- A check through the API leaves no mark.
set local session authorization authenticator;
set local role service_role;
update public.packages set current_stage = 'in_transit' where id = '20000000-0000-0000-0000-000000000901';
reset role;
reset session authorization;

do $$ begin
  if exists (select from private.hand_stage_changes where package_id = '20000000-0000-0000-0000-000000000901') then
    raise exception 'a check through the API was taken for a hand fix';
  end if;
end $$;

-- A fix script's change is kept with its name; a change of something else is not.
set local peek.hand_fix = 'example fix';
update public.packages set current_stage = 'delivered' where id = '20000000-0000-0000-0000-000000000901';
update public.packages set current_stage = 'delivered', label = 'Shoes' where id = '20000000-0000-0000-0000-000000000901';
set local peek.hand_fix = '';
update public.packages set current_stage = 'out_for_delivery' where id = '20000000-0000-0000-0000-000000000901';

do $$
declare marks text;
begin
  select string_agg(concat_ws(' ', from_stage, to_stage, coalesce(fix, '-')), '; ' order by id) into marks
  from private.hand_stage_changes where package_id = '20000000-0000-0000-0000-000000000901';
  if marks is distinct from 'in_transit delivered example fix; delivered out_for_delivery -' then
    raise exception 'hand fixes kept as %', marks;
  end if;
  if has_table_privilege('service_role', 'private.hand_stage_changes', 'select')
      or has_table_privilege('authenticated', 'private.hand_stage_changes', 'select') then
    raise exception 'the API can read hand fixes';
  end if;
end $$;

-- The parcel's marks go with it.
delete from public.packages where id = '20000000-0000-0000-0000-000000000901';
do $$ begin
  if exists (select from private.hand_stage_changes where package_id = '20000000-0000-0000-0000-000000000901') then
    raise exception 'a deleted parcel kept its hand fixes';
  end if;
end $$;

rollback;
