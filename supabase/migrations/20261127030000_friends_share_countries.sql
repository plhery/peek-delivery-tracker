-- Friends can share the countries their parcels went through, with a "Countries" switch. Profiles made from now
-- on start with it on; the profiles that exist keep it off until their owners turn it on. The server adds the
-- countries to a shared card while its owner shares them (withSharedPassports in src/server/friends.ts).
alter table public.friend_profiles add column share_countries boolean not null default false;
alter table public.friend_profiles alter column share_countries set default true;

create or replace function private.friend_card(p_owner uuid)
returns jsonb language sql stable security definer set search_path = pg_catalog as $$
  with journeys as (
    select (array_agg(e.stage::text order by e.occurred_at desc,
        case e.stage::text when 'registered' then 0 when 'accepted' then 1 when 'in_transit' then 2
          when 'customs' then 3 when 'out_for_delivery' then 4 when 'failed_attempt' then 5
          when 'ready_for_pickup' then 6 when 'delivered' then 7 when 'returned' then 8 else -1 end desc,
        e.id desc))[1] stage,
      min(e.occurred_at) filter (where e.stage::text = 'delivered') arrived
    from public.tracking_events e join public.packages p on p.id = e.package_id
    where p.user_id = p_owner and e.stage::text <> 'pending'
    group by e.package_id
  )
  select jsonb_build_object(
    'id', f.id, 'nickname', f.nickname, 'sharesStats', f.share_stats, 'sharesCountries', f.share_countries,
    'arrivedThisWeek', case when f.share_arrival then coalesce((select bool_or(stage = 'delivered'
      and arrived >= date_trunc('week', now() at time zone 'UTC') at time zone 'UTC' and arrived <= now()) from journeys), false)
      else null end
  ) from public.friend_profiles f where f.user_id = p_owner;
$$;
revoke all on function private.friend_card(uuid) from public, anon, authenticated;

create or replace function public.friends_snapshot()
returns jsonb language plpgsql stable security definer set search_path = pg_catalog as $$
declare actor uuid := auth.uid(); own public.friend_profiles;
begin
  if actor is null or not exists (select 1 from auth.users where id = actor and not is_anonymous) then
    raise exception 'A permanent account is required' using errcode = '42501';
  end if;
  select * into own from public.friend_profiles where user_id = actor;
  return jsonb_build_object(
    'profile', case when own.user_id is not null then jsonb_build_object('nickname', own.nickname, 'shareStats', own.share_stats,
      'shareArrival', own.share_arrival, 'shareCountries', own.share_countries) else null end,
    'ownCard', case when own.user_id is not null then private.friend_card(actor) else null end,
    'friends', coalesce((select jsonb_agg(private.friend_card(friend_id) order by f.nickname, f.id)
      from public.friend_connections c
      cross join lateral (select case when c.user_low = actor then c.user_high else c.user_low end friend_id) other
      join public.friend_profiles f on f.user_id = friend_id
      where actor in (c.user_low, c.user_high)), '[]'::jsonb)
  );
end;
$$;

-- The Countries choice is a new last argument, so the function is made again under its new signature.
drop function public.friends_action(text, text, boolean, boolean, text, uuid);
create function public.friends_action(
  p_action text, p_nickname text default null, p_share_stats boolean default null,
  p_share_arrival boolean default null, p_code text default null, p_friend_id uuid default null,
  p_share_countries boolean default null
)
returns jsonb language plpgsql security definer set search_path = pg_catalog as $$
declare actor uuid := auth.uid(); owner uuid; code text; expiry timestamptz; preview text; target_name text; inserted_count integer; invite_id bigint; previous_count integer;
begin
  if actor is null or not exists (select 1 from auth.users where id = actor and not is_anonymous) then
    raise exception 'A permanent account is required' using errcode = '42501';
  end if;
  if p_action = 'save_profile' then
    if p_nickname is null or char_length(btrim(p_nickname, U&'\0020\0009\000A\000B\000C\000D\00A0\1680\2000\2001\2002\2003\2004\2005\2006\2007\2008\2009\200A\2028\2029\202F\205F\3000\FEFF')) not between 1 and 24 or p_nickname ~ U&'[[:cntrl:]\00AD\0600-\0605\061C\06DD\070F\0890-\0891\08E2\180E\200B-\200F\202A-\202E\2060-\206F\FEFF\FFF9-\FFFB\+0110BD\+0110CD\+013430-\+01343F\+01BCA0-\+01BCA3\+01D173-\+01D17A\+0E0001\+0E0020-\+0E007F]'
       or p_share_stats is null or p_share_arrival is null then
      raise exception 'Invalid friend profile' using errcode = '22023';
    end if;
    -- Without a Countries choice (apps from before the switch), a new profile shares them and a saved one keeps its choice.
    insert into public.friend_profiles(user_id, nickname, share_stats, share_arrival, share_countries)
      values(actor, btrim(p_nickname, U&'\0020\0009\000A\000B\000C\000D\00A0\1680\2000\2001\2002\2003\2004\2005\2006\2007\2008\2009\200A\2028\2029\202F\205F\3000\FEFF'), p_share_stats, p_share_arrival, coalesce(p_share_countries, true))
      on conflict(user_id) do update set nickname = excluded.nickname, share_stats = excluded.share_stats, share_arrival = excluded.share_arrival,
        share_countries = coalesce(p_share_countries, public.friend_profiles.share_countries);
  elsif p_action = 'acknowledge_friend' then
    update public.friendship_updates u set seen_at = now() from public.friend_profiles f
      where u.recipient_id = actor and u.friend_user_id = f.user_id and f.id = p_friend_id and u.seen_at is null;
  elsif p_action = 'disable' then
    delete from public.friend_profiles where user_id = actor;
  elsif p_action = 'remove_friend' then
    select user_id into owner from public.friend_profiles where id = p_friend_id;
    delete from public.friend_connections where user_low = least(actor, owner) and user_high = greatest(actor, owner);
  elsif p_action in ('revoke_invite', 'revoke_previous_invites') then
    if p_code is not null and p_code !~ '^([A-Za-z0-9_-]{16}|[a-f0-9]{32})$' then
      raise exception 'Invalid invitation' using errcode = '22023';
    end if;
    -- Serialize revocation with creation and acceptance for this owner.
    perform 1 from public.friend_profiles where user_id = actor for update;
    if p_action = 'revoke_previous_invites' then
      select id into invite_id from public.friend_invites where user_id = actor
        and (preview_id = p_code or code_hash = encode(sha256(convert_to(p_code, 'UTF8')), 'hex')) and expires_at > now();
      if not found then raise exception 'Invitation unavailable' using errcode = 'P0002'; end if;
      delete from public.friend_invites where user_id = actor and id < invite_id;
      return jsonb_build_object('snapshot', public.friends_snapshot(), 'previousInviteCount', 0);
    elsif p_code is not null then
      delete from public.friend_invites where user_id = actor
        and (preview_id = p_code or code_hash = encode(sha256(convert_to(p_code, 'UTF8')), 'hex'));
    else
      -- Existing clients and sharing preferences can still revoke every link.
      delete from public.friend_invites where user_id = actor;
    end if;
  elsif p_action = 'create_invite' then
    perform 1 from public.friend_profiles where user_id = actor for update;
    if not found then raise exception 'Create your friend profile first' using errcode = '22023'; end if;
    delete from public.friend_invites where user_id = actor and expires_at <= now();
    code := replace(gen_random_uuid()::text, '-', '');
    expiry := now() + interval '7 days';
    insert into public.friend_invites(user_id, code_hash, expires_at)
      values(actor, encode(sha256(convert_to(code, 'UTF8')), 'hex'), expiry)
      returning preview_id, id into preview, invite_id;
    -- At most 20 live links an account: past that, the oldest stop working.
    delete from public.friend_invites where id in (
      select id from public.friend_invites where user_id = actor and expires_at > now()
      order by id desc offset 20);
    select count(*)::integer into previous_count from public.friend_invites
      where user_id = actor and id < invite_id and expires_at > now();
    return jsonb_build_object('inviteCode', code, 'previewId', preview, 'expiresAt', expiry, 'previousInviteCount', previous_count);
  elsif p_action in ('preview_invite', 'accept_invite') then
    if p_code is null or p_code !~ '^([A-Za-z0-9_-]{16}|[a-f0-9]{32})$' then
      raise exception 'Invitation unavailable' using errcode = 'P0002';
    end if;
    -- A preview discloses only the nickname and consumes nothing.
    select i.user_id, f.nickname, i.id into owner, target_name, invite_id
      from public.friend_invites i join public.friend_profiles f on f.user_id = i.user_id
      where (i.preview_id = p_code or i.code_hash = encode(sha256(convert_to(p_code, 'UTF8')), 'hex'))
        and i.expires_at > now();
    if owner is null then
      select f.nickname into target_name from private.friend_invitation_receipts r
        join public.friend_profiles f on f.user_id = r.sender_id
        where r.recipient_id = actor and (r.preview_id = p_code or r.code_hash = encode(sha256(convert_to(p_code, 'UTF8')), 'hex'));
      if found then
        if p_action = 'preview_invite' then
          return jsonb_build_object('previewNickname', target_name, 'invitationState', 'already_accepted');
        end if;
        return jsonb_build_object('snapshot', public.friends_snapshot(), 'invitationState', 'already_accepted');
      end if;
      raise exception 'Invitation unavailable' using errcode = 'P0002';
    end if;
    if owner = actor then raise exception 'Cannot accept your own invitation' using errcode = 'P0004'; end if;
    if p_action = 'preview_invite' then
      if exists(select 1 from public.friend_connections where user_low = least(actor, owner) and user_high = greatest(actor, owner)) then
        return jsonb_build_object('previewNickname', target_name, 'invitationState', 'already_friends');
      end if;
      return jsonb_build_object('previewNickname', target_name);
    end if;
    -- Lock both profiles in a consistent order to bound concurrent friend counts.
    perform 1 from public.friend_profiles where user_id in (actor, owner) order by user_id for update;
    -- Recheck after locking profiles: revocation, expiry, or another acceptance wins safely.
    perform 1 from public.friend_invites where user_id = owner
      and id = invite_id and expires_at > now() for update;
    if not found then
      -- Another request from this account may have committed while we waited.
      select f.nickname into target_name from private.friend_invitation_receipts r
        join public.friend_profiles f on f.user_id = r.sender_id
        where r.recipient_id = actor and (r.preview_id = p_code or r.code_hash = encode(sha256(convert_to(p_code, 'UTF8')), 'hex'));
      if found then
        if p_action = 'preview_invite' then
          return jsonb_build_object('previewNickname', target_name, 'invitationState', 'already_accepted');
        end if;
        return jsonb_build_object('snapshot', public.friends_snapshot(), 'invitationState', 'already_accepted');
      end if;
      raise exception 'Invitation unavailable' using errcode = 'P0002';
    end if;
    if exists(select 1 from public.friend_connections where user_low = least(actor, owner) and user_high = greatest(actor, owner)) then
      -- Do not consume a fresh link or emit another friendship notification.
      return jsonb_build_object('snapshot', public.friends_snapshot(), 'invitationState', 'already_friends');
    end if;
    if not exists(select 1 from public.friend_profiles where user_id = actor) then
      raise exception 'Create your friend profile first' using errcode = '22023';
    end if;
    if (select count(*) from public.friend_connections where actor in (user_low, user_high)) >= 50
       or (select count(*) from public.friend_connections where owner in (user_low, user_high)) >= 50 then
      raise exception 'Your circle is full' using errcode = 'P0003';
    end if;
    insert into public.friend_connections(user_low, user_high) values(least(actor,owner), greatest(actor,owner)) on conflict do nothing;
    get diagnostics inserted_count = row_count;
    if inserted_count > 0 then
      insert into public.friendship_updates(recipient_id, friend_user_id, user_low, user_high)
        values(owner, actor, least(actor, owner), greatest(actor, owner));
    end if;
    insert into private.friend_invitation_receipts(code_hash, preview_id, sender_id, recipient_id)
      select code_hash, preview_id, user_id, actor from public.friend_invites where id = invite_id;
    delete from public.friend_invites where user_id = owner and id = invite_id;
    return jsonb_build_object('snapshot', public.friends_snapshot(), 'acceptedFriend', private.friend_card(owner));
  else
    raise exception 'Unknown Friends action' using errcode = '22023';
  end if;
  return jsonb_build_object('snapshot', public.friends_snapshot());
end;
$$;

revoke all on function public.friends_action(text, text, boolean, boolean, text, uuid, boolean) from public, anon;
grant execute on function public.friends_action(text, text, boolean, boolean, text, uuid, boolean) to authenticated;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261127030000_friends_share_countries') on conflict do nothing;
