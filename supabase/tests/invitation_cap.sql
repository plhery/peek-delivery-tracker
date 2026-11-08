\set ON_ERROR_STOP on
begin;
insert into auth.users(id, email) values
  ('a9999999-9999-4999-8999-999999999999', 'cap-owner@example.test'),
  ('b9999999-9999-4999-8999-999999999999', 'cap-recipient@example.test'),
  ('c9999999-9999-4999-8999-999999999999', 'cap-other@example.test');

do $$
declare
  owner_id uuid := 'a9999999-9999-4999-8999-999999999999';
  recipient uuid := 'b9999999-9999-4999-8999-999999999999';
  other_id uuid := 'c9999999-9999-4999-8999-999999999999';
  codes text[] := '{}'; other_codes text[] := '{}'; result jsonb; i integer;
begin
  perform set_config('request.jwt.claim.sub', other_id::text, true);
  perform public.friends_action('save_profile', 'Other sender', false, false);
  for i in 1..20 loop
    other_codes := array_append(other_codes, public.friends_action('create_invite')->>'inviteCode');
  end loop;

  perform set_config('request.jwt.claim.sub', owner_id::text, true);
  perform public.friends_action('save_profile', 'Sender', false, false);
  for i in 1..25 loop
    result := public.friends_action('create_invite');
    if (result->>'previousInviteCount')::integer <> least(i - 1, 19) then
      raise exception 'Wrong previous invitation count at %: %', i, result;
    end if;
    codes := array_append(codes, result->>'inviteCode');
  end loop;
  if (select count(*) from public.friend_invites where user_id=owner_id) <> 20 then
    raise exception 'More than 20 live invitations were kept';
  end if;
  if (select count(*) from public.friend_invites where user_id=other_id) <> 20 then
    raise exception 'The cap removed another owner''s invitations';
  end if;

  -- The oldest five stopped working; the newest twenty still preview.
  perform set_config('request.jwt.claim.sub', recipient::text, true);
  perform public.friends_action('save_profile', 'Recipient', false, false);
  for i in 1..5 loop
    begin
      perform public.friends_action('preview_invite', p_code=>codes[i]);
      raise exception 'Invitation % past the cap still works', i;
    exception when no_data_found then null; end;
  end loop;
  for i in 6..25 loop
    if public.friends_action('preview_invite', p_code=>codes[i])->>'previewNickname' <> 'Sender' then
      raise exception 'Invitation % within the cap stopped working', i;
    end if;
  end loop;
  for i in 1..20 loop
    if public.friends_action('preview_invite', p_code=>other_codes[i])->>'previewNickname' <> 'Other sender' then
      raise exception 'Another owner''s invitation % stopped working', i;
    end if;
  end loop;

  -- Expired links are pruned first and never count toward the cap.
  perform set_config('request.jwt.claim.sub', owner_id::text, true);
  update public.friend_invites set expires_at=now()-interval '1 second'
    where user_id=owner_id and code_hash=encode(sha256(convert_to(codes[6],'UTF8')),'hex');
  result := public.friends_action('create_invite');
  codes := array_append(codes, result->>'inviteCode');
  if (result->>'previousInviteCount')::integer <> 19 then raise exception 'Expired invitation was counted: %', result; end if;
  if (select count(*) from public.friend_invites where user_id=owner_id) <> 20
     or not exists(select 1 from public.friend_invites where user_id=owner_id
       and code_hash=encode(sha256(convert_to(codes[7],'UTF8')),'hex')) then
    raise exception 'A live invitation was dropped when an expired one made room';
  end if;

  -- Cancelling earlier links from the newest one still leaves only that link.
  result := public.friends_action('revoke_previous_invites', p_code=>codes[26]);
  if (result->>'previousInviteCount')::integer <> 0
     or (select count(*) from public.friend_invites where user_id=owner_id) <> 1 then
    raise exception 'Cancelling earlier links after the cap failed: %', result;
  end if;

  perform set_config('request.jwt.claim.sub', recipient::text, true);
  begin
    perform public.friends_action('accept_invite', p_code=>codes[1]);
    raise exception 'An invitation past the cap was accepted';
  exception when no_data_found then null; end;
  perform public.friends_action('accept_invite', p_code=>codes[26]);
  if jsonb_array_length(public.friends_snapshot()->'friends') <> 1 then raise exception 'The newest invitation was not accepted'; end if;
end $$;
rollback;
select 'Live invitation cap assertions passed' as result;
