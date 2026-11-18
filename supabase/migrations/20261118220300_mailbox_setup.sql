begin;

create or replace function public.open_inbound_mailbox(p_user_id uuid, p_token text, p_renew boolean)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  saved private.inbound_mailboxes;
begin
  if p_user_id is null or p_token is null or p_token !~ '^[a-z2-7]{16}$' then
    raise exception 'Invalid forwarding address' using errcode = '22023';
  end if;
  if not exists (select 1 from auth.users where id = p_user_id and not is_anonymous) then
    raise exception 'A permanent account is required' using errcode = '42501';
  end if;
  insert into private.inbound_mailboxes as mailbox (user_id, token)
  values (p_user_id, p_token)
  on conflict (user_id) do update set
    token = excluded.token,
    created_at = now(),
    last_mail_at = null,
    last_result = null,
    last_added = null,
    confirmation_code = null,
    confirmation_link = null,
    confirmation_at = null
  where p_renew
  returning * into saved;
  if not found then return public.inbound_mailbox(p_user_id); end if;
  return private.inbound_mailbox_json(saved);
end;
$$;
revoke all on function public.open_inbound_mailbox(uuid, text, boolean) from public, anon, authenticated;
grant execute on function public.open_inbound_mailbox(uuid, text, boolean) to service_role;

insert into public.applied_migrations (name) values ('20261118220300_mailbox_setup') on conflict do nothing;

commit;
