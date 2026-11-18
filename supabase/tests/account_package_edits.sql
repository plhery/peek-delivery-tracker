\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values ('19000000-0000-0000-0000-000000000054', 'account-edits-owner@example.test');
insert into auth.users (id, email) values ('19000000-0000-0000-0000-000000000055', 'account-edits-other@example.test');
set local role service_role;
do $$
declare
  parcel public.packages;
  changed public.packages;
  done boolean;
begin
  parcel := public.create_account_package('19000000-0000-0000-0000-000000000054', 'XD000000000013', 'Edits fixture', 'xdp', null, 'AB1 2CD');

  -- The server changes the carrier for the account it names, with the owner's own checks.
  if not public.change_account_package_carrier('19000000-0000-0000-0000-000000000054', parcel.id, 'dhl') then
    raise exception 'The owner''s carrier change was refused';
  end if;
  select * into changed from public.packages where id = parcel.id;
  if changed.carrier <> 'dhl' or changed.dpd_postcode is not null or changed.sync_status <> 'pending' then
    raise exception 'The carrier change did not reset the parcel for its new carrier';
  end if;
  if auth.uid() is not null then raise exception 'The named account outlived the call'; end if;

  -- Another account's parcel is not found, and stays as it was.
  done := public.change_account_package_carrier('19000000-0000-0000-0000-000000000055', parcel.id, 'ups');
  if done then
    raise exception 'Another account changed the parcel''s carrier';
  end if;
  if (select carrier from public.packages where id = parcel.id) <> 'dhl' then
    raise exception 'Another account''s carrier change reached the parcel';
  end if;
  begin
    perform public.change_account_package_carrier('19000000-0000-0000-0000-000000000054', parcel.id, 'no-such-carrier');
    raise exception 'An unknown carrier was taken';
  exception when sqlstate '22023' then null;
  end;

  -- Archive and restore, for the owner only. Each call is its own statement, so the check after it reads its change.
  done := public.set_account_package_archived('19000000-0000-0000-0000-000000000054', parcel.id, true);
  if not done or (select archived_at from public.packages where id = parcel.id) is null then
    raise exception 'The owner could not archive the parcel';
  end if;
  done := public.set_account_package_archived('19000000-0000-0000-0000-000000000055', parcel.id, false);
  if done or (select archived_at from public.packages where id = parcel.id) is null then
    raise exception 'Another account restored the parcel';
  end if;
  done := public.set_account_package_archived('19000000-0000-0000-0000-000000000054', parcel.id, false);
  if not done or (select archived_at from public.packages where id = parcel.id) is not null then
    raise exception 'The owner could not restore the parcel';
  end if;
  if auth.uid() is not null then raise exception 'The named account outlived the archive'; end if;
end;
$$;
reset role;

-- A restore counts against the active-parcel limit, as the owner's own restore does.
insert into public.packages (user_id, tracking_number, carrier)
select '19000000-0000-0000-0000-000000000054', 'XE' || lpad(n::text, 12, '0'), 'xdp' from generate_series(1, 49) as n;
update public.packages set archived_at = now() where tracking_number = 'XD000000000013';
insert into public.packages (user_id, tracking_number, carrier) values ('19000000-0000-0000-0000-000000000054', 'XE999999999999', 'xdp');
set local role service_role;
do $$
begin
  perform public.set_account_package_archived(
    '19000000-0000-0000-0000-000000000054',
    (select id from public.packages where tracking_number = 'XD000000000013'),
    false
  );
  raise exception 'A restore went past the parcel limit';
exception when sqlstate 'P0001' then null;
end;
$$;
reset role;

do $$
begin
  if has_function_privilege('authenticated', 'public.change_account_package_carrier(uuid,uuid,text,text,text)', 'EXECUTE')
      or has_function_privilege('anon', 'public.change_account_package_carrier(uuid,uuid,text,text,text)', 'EXECUTE')
      or has_function_privilege('authenticated', 'public.set_account_package_archived(uuid,uuid,boolean)', 'EXECUTE')
      or has_function_privilege('anon', 'public.set_account_package_archived(uuid,uuid,boolean)', 'EXECUTE') then
    raise exception 'A user can act for another account';
  end if;
end;
$$;
rollback;
