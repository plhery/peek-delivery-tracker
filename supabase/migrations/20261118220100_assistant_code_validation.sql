begin;

create or replace function public.redeem_assistant_code(
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
  select * into granted from private.assistant_grants owned where owned.id = code.grant_id;
  if code.expires_at < now()
    or granted.client_id is distinct from p_client_id
    or code.redirect_uri is distinct from p_redirect_uri
    or code.challenge is distinct from p_challenge then
    return null;
  end if;
  if code.used_at is not null then
    delete from private.assistant_grants copied where copied.id = code.grant_id;
    return null;
  end if;
  update private.assistant_codes pending set used_at = now() where pending.hash = p_hash;
  insert into private.assistant_tokens (hash, grant_id, kind, scopes, expires_at) values
    (p_access, granted.id, 'access', code.scopes, p_access_expires),
    (p_refresh, granted.id, 'refresh', code.scopes, p_refresh_expires);
  update private.assistant_grants owned set last_used_at = now() where owned.id = granted.id;
  return jsonb_build_object('user_id', granted.user_id, 'grant_id', granted.id, 'scopes', to_jsonb(code.scopes));
end;
$$;
revoke all on function public.redeem_assistant_code(text, text, text, text, text, timestamptz, text, timestamptz) from public, anon, authenticated;
grant execute on function public.redeem_assistant_code(text, text, text, text, text, timestamptz, text, timestamptz) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261118220100_assistant_code_validation') on conflict do nothing;

commit;
