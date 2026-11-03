\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values
 ('e9000000-0000-4000-8000-000000000001', 'inbox-one@example.test'),
 ('e9000000-0000-4000-8000-000000000002', 'inbox-two@example.test');
do $$
declare
  role_name text;
  function_name text;
begin
  foreach role_name in array array['anon', 'authenticated'] loop
    foreach function_name in array array[
      'public.inbound_mailbox(uuid)', 'public.open_inbound_mailbox(uuid,text)', 'public.close_inbound_mailbox(uuid)',
      'public.inbound_mailbox_owner(text)', 'public.claim_inbound_mail(uuid,integer,integer)',
      'public.record_inbound_mail(uuid,text,integer,text,text)'
    ] loop
      if has_function_privilege(role_name, function_name, 'EXECUTE') then
        raise exception '% must be server-only', function_name;
      end if;
    end loop;
    if has_table_privilege(role_name, 'private.inbound_mailboxes', 'SELECT,INSERT,UPDATE,DELETE')
        or has_table_privilege(role_name, 'private.inbound_mail_days', 'SELECT,INSERT,UPDATE,DELETE')
        or has_table_privilege(role_name, 'private.inbound_mail_hours', 'SELECT,INSERT,UPDATE,DELETE') then
      raise exception 'Forwarding addresses must be server-only';
    end if;
  end loop;
end;
$$;
set local role service_role;
do $$
declare
  one constant uuid := 'e9000000-0000-4000-8000-000000000001';
  two constant uuid := 'e9000000-0000-4000-8000-000000000002';
  mailbox jsonb;
  claim jsonb;
begin
  if public.inbound_mailbox(one) is not null then raise exception 'An account starts without an address'; end if;
  mailbox := public.open_inbound_mailbox(one, 'aaaaaaaaaaaaaaaa');
  if mailbox->>'token' <> 'aaaaaaaaaaaaaaaa' or (mailbox->>'used_today')::int <> 0 then
    raise exception 'The address was not opened: %', mailbox;
  end if;
  begin
    perform public.open_inbound_mailbox(two, 'aaaaaaaaaaaaaaaa');
    raise exception 'Two accounts got the same token';
  exception when unique_violation then null;
  end;
  begin
    perform public.open_inbound_mailbox(two, 'NOT-A-TOKEN');
    raise exception 'A malformed token was taken';
  exception when invalid_parameter_value then null;
  end;

  if public.inbound_mailbox_owner('bbbbbbbbbbbbbbbb') is not null then raise exception 'An unknown token named someone'; end if;
  if public.inbound_mailbox_owner('aaaaaaaaaaaaaaaa') is distinct from one then raise exception 'The token did not name its account'; end if;
  if not (public.claim_inbound_mail(one, 2, 100)->>'allowed')::boolean
      or not (public.claim_inbound_mail(one, 2, 100)->>'allowed')::boolean then
    raise exception 'The first two emails of the day were refused';
  end if;
  claim := public.claim_inbound_mail(one, 2, 100);
  if (claim->>'allowed')::boolean or claim->>'scope' <> 'account' then
    raise exception 'The daily allowance was not enforced: %', claim;
  end if;
  if (public.claim_inbound_mail(two, 0, 100)->>'allowed')::boolean then raise exception 'An allowance of none let an email in'; end if;

  perform public.record_inbound_mail(one, 'confirmation', 0, '123456789', 'https://mail-settings.google.com/mail/vf-example');
  mailbox := public.inbound_mailbox(one);
  if mailbox->>'confirmation_code' <> '123456789' or mailbox->>'last_result' <> 'confirmation' then
    raise exception 'The Gmail confirmation was not kept: %', mailbox;
  end if;
  perform public.record_inbound_mail(one, 'added', 2);
  mailbox := public.inbound_mailbox(one);
  if mailbox->>'confirmation_code' is not null or (mailbox->>'last_added')::int <> 2 then
    raise exception 'A later email must clear the confirmation: %', mailbox;
  end if;
  begin
    perform public.record_inbound_mail(one, 'confirmation', 0, null, 'https://example.test/phish');
    raise exception 'A link off Google was kept';
  exception when check_violation then null;
  end;

  -- A new address drops the old one and keeps the day's count.
  mailbox := public.open_inbound_mailbox(one, 'cccccccccccccccc');
  if (mailbox->>'used_today')::int <> 2 or mailbox->>'last_result' is not null then
    raise exception 'A new address must keep the count and forget the last email: %', mailbox;
  end if;
  if public.inbound_mailbox_owner('aaaaaaaaaaaaaaaa') is not null then
    raise exception 'The old address still names the account';
  end if;

  -- Every address shares the hour's allowance.
  perform public.open_inbound_mailbox(two, 'dddddddddddddddd');
  claim := public.claim_inbound_mail(two, 5, 2);
  if (claim->>'allowed')::boolean or claim->>'scope' <> 'overall' then
    raise exception 'The hourly allowance was not enforced: %', claim;
  end if;

  if not public.close_inbound_mailbox(one) then raise exception 'The address was not closed'; end if;
end;
$$;
do $$
declare
  one constant uuid := 'e9000000-0000-4000-8000-000000000001';
  mailbox jsonb;
begin
  if public.inbound_mailbox(one) is not null or public.inbound_mailbox_owner('cccccccccccccccc') is not null then
    raise exception 'A closed address must name nobody';
  end if;
  -- Turning the setting off and on again gives no new allowance.
  mailbox := public.open_inbound_mailbox(one, 'eeeeeeeeeeeeeeee');
  if (mailbox->>'used_today')::int <> 2 or (public.claim_inbound_mail(one, 2, 100)->>'allowed')::boolean then
    raise exception 'Turning the address off and on reset the day''s count: %', mailbox;
  end if;
end;
$$;
reset role;

-- A new day gives a new allowance, and the first email of an hour forgets counts of over a week ago.
update private.inbound_mail_days set day = day - 1;
insert into private.inbound_mail_days (user_id, day, used)
values ('e9000000-0000-4000-8000-000000000002', (now() at time zone 'UTC')::date - 8, 1);
delete from private.inbound_mail_hours;
set local role service_role;
do $$
begin
  if not (public.claim_inbound_mail('e9000000-0000-4000-8000-000000000001', 2, 100)->>'allowed')::boolean then
    raise exception 'A new day must start a new count';
  end if;
end;
$$;
reset role;
do $$
begin
  if exists (select 1 from private.inbound_mail_days where day < (now() at time zone 'UTC')::date - 7) then
    raise exception 'A count of over a week ago was kept';
  end if;
end;
$$;

-- Deleting an account deletes its address and its counts.
delete from auth.users where id = 'e9000000-0000-4000-8000-000000000001';
do $$
begin
  if exists (select 1 from private.inbound_mailboxes where user_id = 'e9000000-0000-4000-8000-000000000001')
      or exists (select 1 from private.inbound_mail_days where user_id = 'e9000000-0000-4000-8000-000000000001') then
    raise exception 'An address outlived its account';
  end if;
end;
$$;
rollback;
\echo inbound mailbox assertions passed
