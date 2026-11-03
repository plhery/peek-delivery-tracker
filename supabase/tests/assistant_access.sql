\set ON_ERROR_STOP on
begin;
insert into auth.users (id, email) values
 ('ea000000-0000-4000-8000-000000000001', 'assistant-one@example.test'),
 ('ea000000-0000-4000-8000-000000000002', 'assistant-two@example.test');
do $$
declare
  role_name text;
  function_name text;
  table_name text;
begin
  foreach role_name in array array['anon', 'authenticated'] loop
    foreach function_name in array array[
      'public.register_assistant_client(text,text,text,text[])', 'public.assistant_client(text)',
      'public.open_assistant_code(uuid,text,text,text,boolean,text[],text,text,text)',
      'public.redeem_assistant_code(text,text,text,text,text,timestamptz,text,timestamptz)',
      'public.refresh_assistant_tokens(text,text,text,timestamptz,text,timestamptz)',
      'public.assistant_token(text)', 'public.assistant_grants(uuid)', 'public.revoke_assistant_grant(uuid,uuid)',
      'public.create_personal_token(uuid,text,text[],text)', 'public.revoke_assistant_token(text,text)'
    ] loop
      if has_function_privilege(role_name, function_name, 'EXECUTE') then
        raise exception '% must be server-only', function_name;
      end if;
      if not has_function_privilege('service_role', function_name, 'EXECUTE') then
        raise exception 'The server cannot call %', function_name;
      end if;
    end loop;
    foreach table_name in array array[
      'private.assistant_clients', 'private.assistant_grants', 'private.assistant_codes', 'private.assistant_tokens'
    ] loop
      if has_table_privilege(role_name, table_name, 'SELECT,INSERT,UPDATE,DELETE') then
        raise exception '% must be server-only', table_name;
      end if;
    end loop;
  end loop;
end;
$$;
-- The rest runs as the owner, which moves the clock of rows back by hand.
do $$
declare
  one constant uuid := 'ea000000-0000-4000-8000-000000000001';
  two constant uuid := 'ea000000-0000-4000-8000-000000000002';
  challenge constant text := repeat('c', 43);
  later constant timestamptz := now() + interval '1 hour';
  much_later constant timestamptz := now() + interval '60 days';
  issued jsonb;
  seen jsonb;
  grants jsonb;
  personal jsonb;
begin
  -- A client registers itself and is read back.
  perform public.register_assistant_client('peek_c_aaaaaaaaaaaaaaaaaaaaaa', 'Example assistant', null,
    array['https://assistant.example.test/callback']);
  seen := public.assistant_client('peek_c_aaaaaaaaaaaaaaaaaaaaaa');
  if seen->>'name' <> 'Example assistant' or seen->'redirect_uris'->>0 <> 'https://assistant.example.test/callback' then
    raise exception 'The client was not registered: %', seen;
  end if;
  if public.assistant_client('peek_c_bbbbbbbbbbbbbbbbbbbbbb') is not null then raise exception 'An unknown client was found'; end if;
  begin
    perform public.register_assistant_client('not-a-client', 'Bad', null, array['https://assistant.example.test/callback']);
    raise exception 'A malformed client id was taken';
  exception when check_violation then null;
  end;

  -- The owner allows it; the code is redeemed once, with its client, address and challenge.
  perform public.open_assistant_code(one, 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', 'Example assistant', 'assistant.example.test', true,
    array['parcels:read', 'parcels:write'], repeat('1', 64), 'https://assistant.example.test/callback', challenge);
  if public.redeem_assistant_code(repeat('2', 64), 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', 'https://assistant.example.test/callback',
      challenge, repeat('a', 64), later, repeat('b', 64), much_later) is not null then
    raise exception 'An unknown code was redeemed';
  end if;
  issued := public.redeem_assistant_code(repeat('1', 64), 'peek_c_aaaaaaaaaaaaaaaaaaaaaa',
    'https://assistant.example.test/callback', challenge, repeat('a', 64), later, repeat('b', 64), much_later);
  if issued->>'user_id' <> one::text or issued->'scopes' <> '["parcels:read", "parcels:write"]'::jsonb then
    raise exception 'The code was not redeemed: %', issued;
  end if;
  seen := public.assistant_token(repeat('a', 64));
  if seen->>'user_id' <> one::text or seen->>'kind' <> 'access' or seen->>'client_id' <> 'peek_c_aaaaaaaaaaaaaaaaaaaaaa' then
    raise exception 'The access token did not name its account: %', seen;
  end if;
  if public.assistant_token(repeat('b', 64)) is not null then raise exception 'A refresh token was taken as an access token'; end if;

  -- Redeemed again, the code was copied: its grant ends with every token.
  if public.redeem_assistant_code(repeat('1', 64), 'peek_c_aaaaaaaaaaaaaaaaaaaaaa',
      'https://assistant.example.test/callback', challenge, repeat('d', 64), later, repeat('e', 64), much_later) is not null then
    raise exception 'A code was redeemed twice';
  end if;
  if public.assistant_token(repeat('a', 64)) is not null then raise exception 'A copied code''s grant kept its tokens'; end if;

  -- A wrong challenge, client or address issues nothing, and spends the code.
  perform public.open_assistant_code(one, 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', 'Example assistant', 'assistant.example.test', true,
    array['parcels:read'], repeat('3', 64), 'https://assistant.example.test/callback', challenge);
  if public.redeem_assistant_code(repeat('3', 64), 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', 'https://assistant.example.test/callback',
      repeat('x', 43), repeat('f', 64), later, repeat('0', 64), much_later) is not null then
    raise exception 'A wrong PKCE challenge was accepted';
  end if;
  perform public.open_assistant_code(one, 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', 'Example assistant', 'assistant.example.test', true,
    array['parcels:read'], repeat('4', 64), 'https://assistant.example.test/callback', challenge);
  if public.redeem_assistant_code(repeat('4', 64), 'peek_c_cccccccccccccccccccccc', 'https://assistant.example.test/callback',
      challenge, repeat('f', 64), later, repeat('0', 64), much_later) is not null then
    raise exception 'Another client redeemed the code';
  end if;
  perform public.open_assistant_code(one, 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', 'Example assistant', 'assistant.example.test', true,
    array['parcels:read'], repeat('5', 64), 'https://assistant.example.test/callback', challenge);
  if public.redeem_assistant_code(repeat('5', 64), 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', 'https://elsewhere.example.test/callback',
      challenge, repeat('f', 64), later, repeat('0', 64), much_later) is not null then
    raise exception 'The code was redeemed for another address';
  end if;

  -- A read-only grant: refreshing replaces the refresh token, keeps the scopes, and a stale
  -- replaced token used again ends the grant.
  perform public.open_assistant_code(one, 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', 'Example assistant', 'assistant.example.test', true,
    array['parcels:read'], repeat('6', 64), 'https://assistant.example.test/callback', challenge);
  issued := public.redeem_assistant_code(repeat('6', 64), 'peek_c_aaaaaaaaaaaaaaaaaaaaaa',
    'https://assistant.example.test/callback', challenge, repeat('7', 64), later, repeat('8', 64), much_later);
  if issued->'scopes' <> '["parcels:read"]'::jsonb then raise exception 'The scopes changed: %', issued; end if;
  if public.refresh_assistant_tokens(repeat('8', 64), 'peek_c_cccccccccccccccccccccc', repeat('9', 64), later,
      repeat('aa', 32), much_later) is not null then
    raise exception 'Another client refreshed the grant';
  end if;
  issued := public.refresh_assistant_tokens(repeat('8', 64), 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', repeat('9', 64), later,
    repeat('ab', 32), much_later);
  if not (issued->>'rotated')::boolean or issued->'scopes' <> '["parcels:read"]'::jsonb then
    raise exception 'The refresh did not rotate: %', issued;
  end if;
  if public.assistant_token(repeat('9', 64))->'scopes' <> '["parcels:read"]'::jsonb then
    raise exception 'The new access token lost its scopes';
  end if;
  -- At once, the replaced token is refused without ending anything (a retry).
  if public.refresh_assistant_tokens(repeat('8', 64), 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', repeat('ac', 32), later,
      repeat('ad', 32), much_later) is not null or public.assistant_token(repeat('9', 64)) is null then
    raise exception 'A retried refresh ended the grant or issued tokens';
  end if;
  update private.assistant_tokens set replaced_at = now() - interval '5 minutes' where hash = repeat('8', 64);
  if public.refresh_assistant_tokens(repeat('8', 64), 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', repeat('ae', 32), later,
      repeat('af', 32), much_later) is not null then
    raise exception 'A replaced refresh token was taken';
  end if;
  if public.assistant_token(repeat('9', 64)) is not null
      or exists (select 1 from private.assistant_tokens where hash = repeat('ab', 32)) then
    raise exception 'A copied refresh token''s grant kept its tokens';
  end if;

  -- A grant that keeps its refresh token: refreshing extends it and stores no new one.
  perform public.open_assistant_code(one, 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', 'Example assistant', 'assistant.example.test', false,
    array['parcels:write'], repeat('ba', 32), 'https://assistant.example.test/callback', challenge);
  perform public.redeem_assistant_code(repeat('ba', 32), 'peek_c_aaaaaaaaaaaaaaaaaaaaaa',
    'https://assistant.example.test/callback', challenge, repeat('bb', 32), later, repeat('bc', 32), now() + interval '1 day');
  issued := public.refresh_assistant_tokens(repeat('bc', 32), 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', repeat('bd', 32), later,
    repeat('be', 32), much_later);
  if (issued->>'rotated')::boolean or exists (select 1 from private.assistant_tokens where hash = repeat('be', 32))
      or (select expires_at from private.assistant_tokens where hash = repeat('bc', 32)) < now() + interval '59 days' then
    raise exception 'A kept refresh token was replaced or not extended: %', issued;
  end if;

  -- An expired access token is unknown; an expired refresh token issues nothing.
  update private.assistant_tokens set expires_at = now() - interval '1 second' where hash in (repeat('bb', 32), repeat('bd', 32), repeat('bc', 32));
  if public.assistant_token(repeat('bd', 32)) is not null then raise exception 'An expired access token was taken'; end if;
  if public.refresh_assistant_tokens(repeat('bc', 32), 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', repeat('bf', 32), later,
      repeat('c0', 32), much_later) is not null then
    raise exception 'An expired refresh token was taken';
  end if;

  -- Personal tokens: at most ten, listed with the grants, never expiring.
  personal := public.create_personal_token(one, 'Home Assistant', array['parcels:read'], repeat('d0', 32));
  if personal->>'kind' <> 'personal' or personal->>'name' <> 'Home Assistant' then
    raise exception 'The personal token was not made: %', personal;
  end if;
  seen := public.assistant_token(repeat('d0', 32));
  if seen->>'kind' <> 'personal' or seen->>'client_id' is not null or seen->>'expires_at' is not null then
    raise exception 'The personal token was not read: %', seen;
  end if;
  for i in 2..10 loop
    perform public.create_personal_token(one, 'Script ' || i, array['parcels:read'], lpad(to_hex(i), 64, 'e'));
  end loop;
  begin
    perform public.create_personal_token(one, 'Script 11', array['parcels:read'], repeat('d1', 32));
    raise exception 'An eleventh personal token was made';
  exception when sqlstate 'P0001' then null;
  end;
  begin
    perform public.create_personal_token(two, 'Bad', array['parcels:delete'], repeat('d2', 32));
    raise exception 'An unknown scope was taken';
  exception when check_violation then null;
  end;

  -- The list: the personal tokens and the live connection, not the ones that are over.
  update private.assistant_grants set created_at = now() - interval '2 hours' where user_id = one;
  grants := public.assistant_grants(one);
  if jsonb_array_length(grants) <> 10 or exists (
    select 1 from jsonb_array_elements(grants) listed where listed->>'kind' <> 'personal'
  ) then
    raise exception 'The list kept connections that are over: %', grants;
  end if;
  if public.assistant_grants(two) <> '[]'::jsonb then raise exception 'Another account''s grants were listed'; end if;

  -- Revoking: only the owner, and the token stops at once.
  if public.revoke_assistant_grant(two, (personal->>'id')::uuid) then raise exception 'Another account revoked a grant'; end if;
  if not public.revoke_assistant_grant(one, (personal->>'id')::uuid) then raise exception 'The owner could not revoke'; end if;
  if public.assistant_token(repeat('d0', 32)) is not null then raise exception 'A revoked token still worked'; end if;

  -- Token revocation by the client: an access token alone, a refresh token with its grant.
  perform public.open_assistant_code(two, 'peek_c_aaaaaaaaaaaaaaaaaaaaaa', 'Example assistant', 'assistant.example.test', true,
    array['parcels:read'], repeat('f1', 32), 'https://assistant.example.test/callback', challenge);
  perform public.redeem_assistant_code(repeat('f1', 32), 'peek_c_aaaaaaaaaaaaaaaaaaaaaa',
    'https://assistant.example.test/callback', challenge, repeat('f2', 32), later, repeat('f3', 32), much_later);
  perform public.revoke_assistant_token(repeat('f2', 32), 'peek_c_cccccccccccccccccccccc');
  if public.assistant_token(repeat('f2', 32)) is null then raise exception 'Another client revoked a token'; end if;
  perform public.revoke_assistant_token(repeat('f2', 32), 'peek_c_aaaaaaaaaaaaaaaaaaaaaa');
  if public.assistant_token(repeat('f2', 32)) is not null then raise exception 'The access token was not revoked'; end if;
  if jsonb_array_length(public.assistant_grants(two)) <> 1 then raise exception 'Revoking an access token ended the grant'; end if;
  perform public.revoke_assistant_token(repeat('f3', 32), null);
  if public.assistant_grants(two) <> '[]'::jsonb then raise exception 'Revoking the refresh token left the grant'; end if;

  -- A registered client no grant uses goes a day later.
  update private.assistant_clients set created_at = now() - interval '2 days' where id = 'peek_c_aaaaaaaaaaaaaaaaaaaaaa';
  perform public.register_assistant_client('peek_c_dddddddddddddddddddddd', 'Another', 'https://another.example.test',
    array['http://127.0.0.1/callback']);
  if public.assistant_client('peek_c_aaaaaaaaaaaaaaaaaaaaaa') is not null then raise exception 'An unused client stayed'; end if;
end;
$$;

-- Fifty grants an account at most.
do $$
declare
  one constant uuid := 'ea000000-0000-4000-8000-000000000001';
begin
  for i in 1..41 loop
    perform public.open_assistant_code(one, 'peek_c_dddddddddddddddddddddd', 'Another', 'another.example.test', true,
      array['parcels:read'], lpad(to_hex(i), 64, '7'), 'http://127.0.0.1/callback', repeat('c', 43));
  end loop;
  begin
    perform public.open_assistant_code(one, 'peek_c_dddddddddddddddddddddd', 'Another', 'another.example.test', true,
      array['parcels:read'], repeat('70', 32), 'http://127.0.0.1/callback', repeat('c', 43));
    raise exception 'A fifty-first grant was opened';
  exception when sqlstate 'P0001' then null;
  end;
end;
$$;

-- Deleting an account deletes its grants and tokens.
delete from auth.users where id = 'ea000000-0000-4000-8000-000000000001';
do $$
begin
  if exists (select 1 from private.assistant_grants where user_id = 'ea000000-0000-4000-8000-000000000001')
      or exists (select 1 from private.assistant_tokens where hash = lpad(to_hex(2), 64, 'e')) then
    raise exception 'A grant outlived its account';
  end if;
end;
$$;
rollback;
\echo assistant access assertions passed
