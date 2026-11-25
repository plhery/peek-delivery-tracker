\set ON_ERROR_STOP on

-- What the publishable key reaches in public, signed in or not. Supabase grants every
-- function, table and sequence a migration creates there to these roles, and
-- bootstrap.sql does the same, so a migration that forgets a revoke fails here.
do $$
declare
  anon_functions constant text[] := array['missing_migrations'];
  authenticated_functions constant text[] := array[
    'add_package_file', 'change_owned_package_carrier', 'change_owned_package_carrier_with_digits', 'claim_parcel_link',
    'delete_owned_archived_package', 'delete_owned_package', 'delete_package_file', 'friends_action', 'friends_activity',
    'friends_snapshot', 'mark_owned_package_seen', 'missing_migrations', 'owned_delivery_emails', 'owned_package_share',
    'record_account_opened', 'rename_owned_package', 'set_owned_notification_preferences', 'patch_owned_notification_preferences',
    'set_owned_package_archived', 'set_owned_package_email_muted',
    'set_owned_package_notifications_muted', 'set_owned_package_provider_postcode', 'set_package_note',
    'share_owned_package', 'stop_owned_package_share'
  ];
  found text;
begin
  select string_agg(p.proname, ', ' order by p.proname) into found
  from pg_proc p
  where p.pronamespace = 'public'::regnamespace
    and has_function_privilege('anon', p.oid, 'execute')
    and p.proname <> all (anon_functions);
  if found is not null then
    raise exception 'Callable without signing in: %. Revoke it from public and anon in its migration, or list it here.', found;
  end if;

  select string_agg(p.proname, ', ' order by p.proname) into found
  from pg_proc p
  where p.pronamespace = 'public'::regnamespace
    and has_function_privilege('authenticated', p.oid, 'execute')
    and p.proname <> all (authenticated_functions);
  if found is not null then
    raise exception 'Callable by any signed-in user: %. Revoke it from public, anon and authenticated in its migration, or list it here.', found;
  end if;

  select string_agg(name, ', ' order by name) into found
  from unnest(anon_functions || authenticated_functions) as name
  where not exists (
    select 1 from pg_proc p
    where p.pronamespace = 'public'::regnamespace
      and p.proname = name
      and has_function_privilege('authenticated', p.oid, 'execute')
  );
  if found is not null then
    raise exception 'Listed here and not callable by a signed-in user: %', found;
  end if;

  select string_agg(c.relname, ', ' order by c.relname) into found
  from pg_class c
  where c.relnamespace = 'public'::regnamespace
    and c.relkind in ('r', 'p', 'v', 'm')
    and (has_table_privilege('anon', c.oid, 'select') or has_table_privilege('anon', c.oid, 'insert')
      or has_table_privilege('anon', c.oid, 'update') or has_table_privilege('anon', c.oid, 'delete')
      or has_table_privilege('anon', c.oid, 'truncate'));
  if found is not null then
    raise exception 'Readable or writable without signing in: %', found;
  end if;

  select string_agg(c.relname, ', ' order by c.relname) into found
  from pg_class c
  where c.relnamespace = 'public'::regnamespace
    and c.relkind = 'S'
    and (has_sequence_privilege('anon', c.oid, 'usage') or has_sequence_privilege('authenticated', c.oid, 'usage'));
  if found is not null then
    raise exception 'Sequence usable by a client role: %', found;
  end if;
end;
$$;

-- The account functions still answer a parcel's owner, and refuse a visitor who is not signed in.
begin;
insert into auth.users (id, email) values ('1a000000-0000-0000-0000-000000000018', 'client-access-test@example.test');
select set_config('request.jwt.claim.sub', '1a000000-0000-0000-0000-000000000018', true);
set local role authenticated;
do $$
declare
  parcel public.packages;
begin
  parcel := test_support.add_parcel('CA000000000001', 'Client access fixture');

  set local role anon;
  begin
    perform public.rename_owned_package(parcel.id, 'Renamed');
    raise exception 'rename_owned_package ran without a sign-in';
  exception when insufficient_privilege then null;
  end;
  begin
    perform public.delete_owned_package(parcel.id);
    raise exception 'delete_owned_package ran without a sign-in';
  exception when insufficient_privilege then null;
  end;

  set local role authenticated;
  perform public.rename_owned_package(parcel.id, 'Renamed');
  perform public.set_owned_package_notifications_muted(parcel.id, true);
  perform public.set_owned_package_archived(parcel.id, true);
  perform public.delete_owned_archived_package(parcel.id);
  if exists (select 1 from public.packages where id = parcel.id) then
    raise exception 'The owner could not delete the archived parcel';
  end if;
end;
$$;
rollback;
