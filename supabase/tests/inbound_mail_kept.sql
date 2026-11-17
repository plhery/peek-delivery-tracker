\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values
 ('e9100000-0000-4000-8000-000000000001', 'kept-one@example.test'),
 ('e9100000-0000-4000-8000-000000000002', 'kept-two@example.test'),
 ('e9100000-0000-4000-8000-000000000003', 'kept-three@example.test');
do $$
declare
  role_name text;
  function_name text;
begin
  foreach role_name in array array['anon', 'authenticated'] loop
    foreach function_name in array array['public.keep_inbound_mail(uuid,text)', 'public.forget_old_inbound_mail()'] loop
      if has_function_privilege(role_name, function_name, 'EXECUTE') then
        raise exception '% must be server-only', function_name;
      end if;
    end loop;
    if has_table_privilege(role_name, 'private.inbound_mail_kept', 'SELECT,INSERT,UPDATE,DELETE') then
      raise exception 'Kept emails must be server-only';
    end if;
  end loop;
  if not has_function_privilege('service_role', 'public.keep_inbound_mail(uuid,text)', 'EXECUTE')
      or not has_function_privilege('service_role', 'public.forget_old_inbound_mail()', 'EXECUTE') then
    raise exception 'The server must reach the kept emails';
  end if;
  if not (select relrowsecurity from pg_class where oid = 'private.inbound_mail_kept'::regclass) then
    raise exception 'Kept emails must have row level security on';
  end if;
end;
$$;

set local role service_role;
do $$
declare
  one constant uuid := 'e9100000-0000-4000-8000-000000000001';
  two constant uuid := 'e9100000-0000-4000-8000-000000000002';
  three constant uuid := 'e9100000-0000-4000-8000-000000000003';
  email constant text := encode(convert_to(E'From: shop@example.test\r\nSubject: Shipped\r\n\r\nTracking: 1Z999AA10123456784', 'UTF8'), 'base64');
  invalid text;
begin
  perform public.open_inbound_mailbox(one, 'ffffffffffffffff');
  perform public.open_inbound_mailbox(two, 'gggggggggggggggg');
  if not public.keep_inbound_mail(one, email) or not public.keep_inbound_mail(one, email) or not public.keep_inbound_mail(two, email) then
    raise exception 'An email to an address was not kept';
  end if;
  -- An account without an address keeps nothing: one that turned it off as the email came.
  if public.keep_inbound_mail(three, email) then raise exception 'An email was kept for an account without an address'; end if;
  foreach invalid in array array['', '!!!not base64!!!', repeat('A', 1398108)] loop
    begin
      perform public.keep_inbound_mail(one, invalid);
      raise exception 'An invalid email was kept: % characters', char_length(invalid);
    exception when invalid_parameter_value then null;
    end;
  end loop;
  -- The two new outcomes are recorded.
  perform public.record_inbound_mail(one, 'named', 0, null, null, 'Example Shop');
  perform public.record_inbound_mail(one, 'delivered', 0, null, null, 'Example Shop');
  if public.inbound_mailbox(one)->>'last_result' <> 'delivered' or public.inbound_mailbox(one)->'recent'->1->>'result' <> 'named' then
    raise exception 'The new outcomes were not recorded: %', public.inbound_mailbox(one);
  end if;
  begin
    perform public.record_inbound_mail(one, 'unknown', 0);
    raise exception 'An unknown outcome was recorded';
  exception when invalid_parameter_value then null;
  end;
end;
$$;
reset role;

do $$
declare
  one constant uuid := 'e9100000-0000-4000-8000-000000000001';
begin
  if (select count(*) from private.inbound_mail_kept where user_id = one) <> 2
      or not exists (select 1 from private.inbound_mail_kept where user_id = one and convert_from(raw, 'UTF8') like '%Subject: Shipped%') then
    raise exception 'The email was not kept as received';
  end if;
  begin
    insert into private.inbound_mail_kept (user_id, raw) values (one, '\x');
    raise exception 'An empty email was kept';
  exception when check_violation then null;
  end;
  update private.inbound_mail_kept set received_at = now() - interval '30 days 1 minute'
  where id = (select min(id) from private.inbound_mail_kept where user_id = one);
  -- One kept for 29 days stays.
  update private.inbound_mail_kept set received_at = now() - interval '29 days'
  where id = (select max(id) from private.inbound_mail_kept where user_id = one);
end;
$$;

-- The sweep deletes what is over 30 days old, and only that.
set local role service_role;
do $$
begin
  if public.forget_old_inbound_mail() <> 1 or public.forget_old_inbound_mail() <> 0 then
    raise exception 'The sweep did not delete exactly the email over 30 days old';
  end if;
end;
$$;
reset role;
do $$
begin
  if (select count(*) from private.inbound_mail_kept) <> 2 then
    raise exception 'The sweep deleted an email under 30 days old';
  end if;
end;
$$;

-- An account keeps its newest 100 emails at most.
set local role service_role;
do $$
declare
  two constant uuid := 'e9100000-0000-4000-8000-000000000002';
  email constant text := encode(convert_to(E'Subject: Shipped\r\n\r\nmore', 'UTF8'), 'base64');
begin
  for i in 1..101 loop
    perform public.keep_inbound_mail(two, email);
  end loop;
end;
$$;
reset role;
do $$
begin
  if (select count(*) from private.inbound_mail_kept where user_id = 'e9100000-0000-4000-8000-000000000002') <> 100
      or exists (select 1 from private.inbound_mail_kept where user_id = 'e9100000-0000-4000-8000-000000000002'
        and convert_from(raw, 'UTF8') like '%Tracking:%') then
    raise exception 'An account kept more than its newest 100 emails';
  end if;
end;
$$;

-- Turning the address off deletes its emails, and only its own; deleting the account deletes the rest.
set local role service_role;
do $$
begin
  if not public.close_inbound_mailbox('e9100000-0000-4000-8000-000000000001') then raise exception 'The address was not closed'; end if;
  if public.close_inbound_mailbox('e9100000-0000-4000-8000-000000000003') then raise exception 'An account without an address closed one'; end if;
end;
$$;
reset role;
do $$
begin
  if exists (select 1 from private.inbound_mail_kept where user_id = 'e9100000-0000-4000-8000-000000000001') then
    raise exception 'Turning the address off kept its emails';
  end if;
  if not exists (select 1 from private.inbound_mail_kept where user_id = 'e9100000-0000-4000-8000-000000000002') then
    raise exception 'Turning one address off deleted another''s emails';
  end if;
  delete from auth.users where id = 'e9100000-0000-4000-8000-000000000002';
  if exists (select 1 from private.inbound_mail_kept) then
    raise exception 'An email outlived its account';
  end if;
end;
$$;
rollback;
\echo kept inbound mail assertions passed
