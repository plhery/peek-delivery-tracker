
drop function public.refresh_assistant_tokens(text,text,text,timestamptz,text,timestamptz);
create or replace function public.refresh_assistant_tokens(
  p_refresh text,
  p_client_id text,
  p_access text,
  p_access_expires timestamptz,
  p_new_refresh text,
  p_refresh_expires timestamptz,
  p_scopes text[] default null
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
  if p_scopes is not null and (cardinality(p_scopes) = 0 or not (p_scopes <@ presented.scopes)) then
    return jsonb_build_object('error', 'invalid_scope');
  end if;
  delete from private.assistant_tokens stale where stale.grant_id = granted.id and stale.expires_at < now();
  insert into private.assistant_tokens (hash, grant_id, kind, scopes, expires_at)
  values (p_access, granted.id, 'access', coalesce(p_scopes, presented.scopes), p_access_expires);
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
  return jsonb_build_object('user_id', granted.user_id, 'grant_id', granted.id, 'scopes', to_jsonb(coalesce(p_scopes, presented.scopes)),
    'rotated', granted.rotate_refresh);
end;
$$;
revoke all on function public.refresh_assistant_tokens(text, text, text, timestamptz, text, timestamptz, text[]) from public, anon, authenticated;
grant execute on function public.refresh_assistant_tokens(text, text, text, timestamptz, text, timestamptz, text[]) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261118220200_assistant_refresh_scope') on conflict do nothing;

