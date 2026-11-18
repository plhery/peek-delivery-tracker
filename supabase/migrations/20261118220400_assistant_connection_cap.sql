begin;

create or replace function public.open_assistant_code(
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
  perform 1 from auth.users where id = p_user_id for update;
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

create or replace function public.create_personal_token(p_user_id uuid, p_name text, p_scopes text[], p_hash text)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  granted private.assistant_grants;
begin
  perform 1 from auth.users where id = p_user_id for update;
  perform private.drop_ended_assistant_grants(p_user_id);
  if (select count(*) from private.assistant_grants owned where owned.user_id = p_user_id) >= 50 then
    raise exception 'Too many connections' using errcode = 'P0001';
  end if;
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

insert into public.applied_migrations (name) values ('20261118220400_assistant_connection_cap') on conflict do nothing;

commit;
