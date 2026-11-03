-- Assistants and scripts that reach an account's parcels for its owner: an assistant such
-- as Claude connects through Peek's own OAuth server, and the owner can make a personal
-- token for a script. Each connection is a grant with the scopes the owner allowed
-- (parcels:read, parcels:write); its tokens are kept as SHA-256 digests only, never as
-- issued. Revoking a grant ends its tokens at once, because every request looks its token up.
--
-- Clients that register themselves (OAuth dynamic client registration) are kept until a
-- day after registration unless a grant uses them. A client that names itself by a
-- metadata document's address is not stored: the server reads the document when asked.
--
-- Only the server reaches these rows. Apply this before deploying the server that calls
-- the functions; the server before it never does.

create table private.assistant_clients (
  id text primary key check (id ~ '^peek_c_[A-Za-z0-9_-]{22}$'),
  name text not null check (char_length(name) between 1 and 100),
  uri text check (char_length(uri) <= 500),
  redirect_uris text[] not null check (cardinality(redirect_uris) between 1 and 10),
  created_at timestamptz not null default now()
);
create index assistant_clients_created_at on private.assistant_clients (created_at);
alter table private.assistant_clients enable row level security;
revoke all on private.assistant_clients from public, anon, authenticated;
comment on table private.assistant_clients is
  'OAuth clients that registered themselves: their name and redirect addresses.';

create table private.assistant_grants (
  id uuid primary key default gen_random_uuid(),
  user_id uuid not null references auth.users (id) on delete cascade,
  kind text not null check (kind in ('oauth', 'personal')),
  client_id text check (char_length(client_id) <= 500),
  name text not null check (char_length(name) between 1 and 100),
  redirect_host text check (char_length(redirect_host) <= 300),
  scopes text[] not null check (
    cardinality(scopes) between 1 and 2 and scopes <@ array['parcels:read', 'parcels:write']::text[]
  ),
  rotate_refresh boolean not null default true,
  created_at timestamptz not null default now(),
  last_used_at timestamptz,
  check ((kind = 'oauth') = (client_id is not null))
);
create index assistant_grants_user on private.assistant_grants (user_id);
create index assistant_grants_client on private.assistant_grants (client_id) where client_id is not null;
alter table private.assistant_grants enable row level security;
revoke all on private.assistant_grants from public, anon, authenticated;
comment on table private.assistant_grants is
  'An account''s connected assistants and personal tokens: name, scopes, when made and last used.';

create table private.assistant_codes (
  hash text primary key check (hash ~ '^[0-9a-f]{64}$'),
  grant_id uuid not null references private.assistant_grants (id) on delete cascade,
  redirect_uri text not null check (char_length(redirect_uri) <= 2000),
  challenge text not null check (challenge ~ '^[A-Za-z0-9_-]{43}$'),
  scopes text[] not null check (scopes <@ array['parcels:read', 'parcels:write']::text[]),
  expires_at timestamptz not null,
  used_at timestamptz
);
create index assistant_codes_expires_at on private.assistant_codes (expires_at);
create index assistant_codes_grant on private.assistant_codes (grant_id);
alter table private.assistant_codes enable row level security;
revoke all on private.assistant_codes from public, anon, authenticated;
comment on table private.assistant_codes is
  'OAuth authorization codes, as digests: ten minutes to redeem, once.';

create table private.assistant_tokens (
  hash text primary key check (hash ~ '^[0-9a-f]{64}$'),
  grant_id uuid not null references private.assistant_grants (id) on delete cascade,
  kind text not null check (kind in ('access', 'refresh', 'personal')),
  scopes text[] not null check (
    cardinality(scopes) between 1 and 2 and scopes <@ array['parcels:read', 'parcels:write']::text[]
  ),
  created_at timestamptz not null default now(),
  expires_at timestamptz,
  replaced_at timestamptz,
  check ((kind = 'personal') = (expires_at is null)),
  check (replaced_at is null or kind = 'refresh')
);
create index assistant_tokens_grant on private.assistant_tokens (grant_id);
alter table private.assistant_tokens enable row level security;
revoke all on private.assistant_tokens from public, anon, authenticated;
comment on table private.assistant_tokens is
  'Access, refresh and personal tokens of the grants, as digests.';

create function private.assistant_grant_json(granted private.assistant_grants)
returns jsonb
language sql
stable
set search_path = ''
as $$
  select jsonb_build_object(
    'id', granted.id,
    'kind', granted.kind,
    'name', granted.name,
    'redirect_host', granted.redirect_host,
    'scopes', to_jsonb(granted.scopes),
    'created_at', granted.created_at,
    'last_used_at', granted.last_used_at
  );
$$;
revoke all on function private.assistant_grant_json(private.assistant_grants) from public, anon, authenticated;

-- Connections that are over: every token expired, or the code never redeemed within an hour.
create function private.drop_ended_assistant_grants(p_user_id uuid)
returns void
language sql
set search_path = ''
as $$
  delete from private.assistant_grants over
  where over.user_id = p_user_id and over.kind = 'oauth' and over.created_at < now() - interval '1 hour'
    and not exists (select 1 from private.assistant_tokens held where held.grant_id = over.id and held.expires_at > now());
$$;
revoke all on function private.drop_ended_assistant_grants(uuid) from public, anon, authenticated;

-- A client registering itself. Clients a day old that no grant uses go first; past a
-- thousand registrations an hour, the next is refused.
create function public.register_assistant_client(p_id text, p_name text, p_uri text, p_redirect_uris text[])
returns void
language plpgsql
security definer
set search_path = ''
as $$
begin
  delete from private.assistant_clients registered
  where registered.created_at < now() - interval '1 day'
    and not exists (select 1 from private.assistant_grants granted where granted.client_id = registered.id);
  if (select count(*) from private.assistant_clients registered where registered.created_at > now() - interval '1 hour') >= 1000 then
    raise exception 'Too many clients registered this hour' using errcode = 'P0001';
  end if;
  insert into private.assistant_clients (id, name, uri, redirect_uris) values (p_id, p_name, p_uri, p_redirect_uris);
end;
$$;
revoke all on function public.register_assistant_client(text, text, text, text[]) from public, anon, authenticated;
grant execute on function public.register_assistant_client(text, text, text, text[]) to service_role;

create function public.assistant_client(p_id text)
returns jsonb
language sql
stable
security definer
set search_path = ''
as $$
  select jsonb_build_object('id', registered.id, 'name', registered.name, 'uri', registered.uri,
    'redirect_uris', to_jsonb(registered.redirect_uris))
  from private.assistant_clients registered
  where registered.id = p_id;
$$;
revoke all on function public.assistant_client(text) from public, anon, authenticated;
grant execute on function public.assistant_client(text) to service_role;

-- The owner allowed a client: a new grant, and the code the client redeems for its tokens.
-- An account has at most fifty grants.
create function public.open_assistant_code(
  p_user_id uuid,
  p_client_id text,
  p_name text,
  p_redirect_host text,
  p_rotate boolean,
  p_scopes text[],
  p_hash text,
  p_redirect_uri text,
  p_challenge text
)
returns void
language plpgsql
security definer
set search_path = ''
as $$
declare
  granted uuid;
begin
  delete from private.assistant_codes stale where stale.expires_at < now() - interval '1 day';
  perform private.drop_ended_assistant_grants(p_user_id);
  if (select count(*) from private.assistant_grants owned where owned.user_id = p_user_id) >= 50 then
    raise exception 'Too many connections' using errcode = 'P0001';
  end if;
  insert into private.assistant_grants (user_id, kind, client_id, name, redirect_host, scopes, rotate_refresh)
  values (p_user_id, 'oauth', p_client_id, p_name, p_redirect_host, p_scopes, p_rotate)
  returning id into granted;
  insert into private.assistant_codes (hash, grant_id, redirect_uri, challenge, scopes, expires_at)
  values (p_hash, granted, p_redirect_uri, p_challenge, p_scopes, now() + interval '10 minutes');
end;
$$;
revoke all on function public.open_assistant_code(uuid, text, text, text, boolean, text[], text, text, text) from public, anon, authenticated;
grant execute on function public.open_assistant_code(uuid, text, text, text, boolean, text[], text, text, text) to service_role;

-- A client redeeming its code: the code must be unused and fresh, and the client, redirect
-- address and PKCE challenge the ones it was given for. A code redeemed twice may have been
-- copied, so the grant it opened ends. Null when nothing is issued.
create function public.redeem_assistant_code(
  p_hash text,
  p_client_id text,
  p_redirect_uri text,
  p_challenge text,
  p_access text,
  p_access_expires timestamptz,
  p_refresh text,
  p_refresh_expires timestamptz
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  code private.assistant_codes;
  granted private.assistant_grants;
begin
  select * into code from private.assistant_codes pending where pending.hash = p_hash for update;
  if not found then return null; end if;
  if code.used_at is not null then
    delete from private.assistant_grants copied where copied.id = code.grant_id;
    return null;
  end if;
  update private.assistant_codes pending set used_at = now() where pending.hash = p_hash;
  select * into granted from private.assistant_grants owned where owned.id = code.grant_id;
  if code.expires_at < now()
    or granted.client_id is distinct from p_client_id
    or code.redirect_uri <> p_redirect_uri
    or code.challenge <> p_challenge then
    return null;
  end if;
  insert into private.assistant_tokens (hash, grant_id, kind, scopes, expires_at) values
    (p_access, granted.id, 'access', code.scopes, p_access_expires),
    (p_refresh, granted.id, 'refresh', code.scopes, p_refresh_expires);
  update private.assistant_grants owned set last_used_at = now() where owned.id = granted.id;
  return jsonb_build_object('user_id', granted.user_id, 'grant_id', granted.id, 'scopes', to_jsonb(code.scopes));
end;
$$;
revoke all on function public.redeem_assistant_code(text, text, text, text, text, timestamptz, text, timestamptz) from public, anon, authenticated;
grant execute on function public.redeem_assistant_code(text, text, text, text, text, timestamptz, text, timestamptz) to service_role;

-- A client trading its refresh token for a new access token. The refresh token is replaced
-- too unless the grant keeps it (some clients must), and the replaced one stays known for a
-- day: used again after two minutes, it was copied, and the grant ends. Null when nothing
-- is issued; `rotated` says whether p_new_refresh was stored.
create function public.refresh_assistant_tokens(
  p_refresh text,
  p_client_id text,
  p_access text,
  p_access_expires timestamptz,
  p_new_refresh text,
  p_refresh_expires timestamptz
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  presented private.assistant_tokens;
  granted private.assistant_grants;
begin
  select * into presented from private.assistant_tokens held
  where held.hash = p_refresh and held.kind = 'refresh'
  for update;
  if not found then return null; end if;
  select * into granted from private.assistant_grants owned where owned.id = presented.grant_id;
  if granted.client_id is distinct from p_client_id then return null; end if;
  if presented.replaced_at is not null then
    if presented.replaced_at < now() - interval '2 minutes' then
      delete from private.assistant_grants copied where copied.id = granted.id;
    end if;
    return null;
  end if;
  if presented.expires_at < now() then return null; end if;
  delete from private.assistant_tokens stale where stale.grant_id = granted.id and stale.expires_at < now();
  insert into private.assistant_tokens (hash, grant_id, kind, scopes, expires_at)
  values (p_access, granted.id, 'access', presented.scopes, p_access_expires);
  if granted.rotate_refresh then
    update private.assistant_tokens held
    set replaced_at = now(), expires_at = least(held.expires_at, now() + interval '1 day')
    where held.hash = p_refresh;
    insert into private.assistant_tokens (hash, grant_id, kind, scopes, expires_at)
    values (p_new_refresh, granted.id, 'refresh', presented.scopes, p_refresh_expires);
  else
    update private.assistant_tokens held set expires_at = p_refresh_expires where held.hash = p_refresh;
  end if;
  update private.assistant_grants owned set last_used_at = now() where owned.id = granted.id;
  return jsonb_build_object('user_id', granted.user_id, 'grant_id', granted.id, 'scopes', to_jsonb(presented.scopes),
    'rotated', granted.rotate_refresh);
end;
$$;
revoke all on function public.refresh_assistant_tokens(text, text, text, timestamptz, text, timestamptz) from public, anon, authenticated;
grant execute on function public.refresh_assistant_tokens(text, text, text, timestamptz, text, timestamptz) to service_role;

-- Whose an access or personal token is, and what it may do; null when it is unknown or
-- expired. Notes the grant's use, at most once a minute.
create function public.assistant_token(p_hash text)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  presented private.assistant_tokens;
  granted private.assistant_grants;
begin
  select * into presented from private.assistant_tokens held
  where held.hash = p_hash and held.kind in ('access', 'personal') and (held.expires_at is null or held.expires_at > now());
  if not found then return null; end if;
  select * into granted from private.assistant_grants owned where owned.id = presented.grant_id;
  if granted.last_used_at is null or granted.last_used_at < now() - interval '1 minute' then
    update private.assistant_grants owned set last_used_at = now() where owned.id = granted.id;
  end if;
  return jsonb_build_object('user_id', granted.user_id, 'grant_id', granted.id, 'kind', presented.kind,
    'client_id', granted.client_id, 'scopes', to_jsonb(presented.scopes), 'expires_at', presented.expires_at);
end;
$$;
revoke all on function public.assistant_token(text) from public, anon, authenticated;
grant execute on function public.assistant_token(text) to service_role;

-- An account's grants, newest first; the ones that are over go first.
create function public.assistant_grants(p_user_id uuid)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
begin
  perform private.drop_ended_assistant_grants(p_user_id);
  return coalesce((
    select jsonb_agg(private.assistant_grant_json(owned) order by owned.created_at desc)
    from private.assistant_grants owned
    where owned.user_id = p_user_id
      and (owned.kind = 'personal' or exists (select 1 from private.assistant_tokens held where held.grant_id = owned.id))
  ), '[]'::jsonb);
end;
$$;
revoke all on function public.assistant_grants(uuid) from public, anon, authenticated;
grant execute on function public.assistant_grants(uuid) to service_role;

create function public.revoke_assistant_grant(p_user_id uuid, p_grant_id uuid)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
begin
  delete from private.assistant_grants owned where owned.id = p_grant_id and owned.user_id = p_user_id;
  return found;
end;
$$;
revoke all on function public.revoke_assistant_grant(uuid, uuid) from public, anon, authenticated;
grant execute on function public.revoke_assistant_grant(uuid, uuid) to service_role;

-- A personal token for a script. An account has at most ten.
create function public.create_personal_token(p_user_id uuid, p_name text, p_scopes text[], p_hash text)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  granted private.assistant_grants;
begin
  if (select count(*) from private.assistant_grants owned where owned.user_id = p_user_id and owned.kind = 'personal') >= 10 then
    raise exception 'Too many personal tokens' using errcode = 'P0001';
  end if;
  insert into private.assistant_grants (user_id, kind, name, scopes, rotate_refresh)
  values (p_user_id, 'personal', p_name, p_scopes, false)
  returning * into granted;
  insert into private.assistant_tokens (hash, grant_id, kind, scopes) values (p_hash, granted.id, 'personal', p_scopes);
  return private.assistant_grant_json(granted);
end;
$$;
revoke all on function public.create_personal_token(uuid, text, text[], text) from public, anon, authenticated;
grant execute on function public.create_personal_token(uuid, text, text[], text) to service_role;

-- A client giving a token back (OAuth token revocation). A refresh token ends its grant; an
-- access token ends alone. A client that names itself must be the grant's.
create function public.revoke_assistant_token(p_hash text, p_client_id text)
returns void
language plpgsql
security definer
set search_path = ''
as $$
declare
  presented private.assistant_tokens;
begin
  select held.* into presented from private.assistant_tokens held
  join private.assistant_grants owned on owned.id = held.grant_id
  where held.hash = p_hash and held.kind in ('access', 'refresh')
    and (p_client_id is null or owned.client_id = p_client_id);
  if not found then return; end if;
  if presented.kind = 'refresh' then
    delete from private.assistant_grants owned where owned.id = presented.grant_id;
  else
    delete from private.assistant_tokens held where held.hash = p_hash;
  end if;
end;
$$;
revoke all on function public.revoke_assistant_token(text, text) from public, anon, authenticated;
grant execute on function public.revoke_assistant_token(text, text) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261104090000_assistant_access') on conflict do nothing;
