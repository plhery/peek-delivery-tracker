-- A Gmail confirmation stays for three days, as the mailbox shows it. Any email after it
-- used to clear it, so a parcel email that came before the owner finished in Gmail took
-- the code away. A later email now keeps a confirmation younger than three days and drops
-- an older one.

create or replace function public.record_inbound_mail(
  p_user_id uuid, p_result text, p_added integer,
  p_confirmation_code text default null, p_confirmation_link text default null
) returns void language plpgsql security definer set search_path = '' as $$
begin
  if p_result is null or p_result not in ('added', 'known', 'none', 'limited', 'full', 'confirmation')
      or p_added is null or p_added not between 0 and 100 then
    raise exception 'Invalid email outcome' using errcode = '22023';
  end if;
  update private.inbound_mailboxes set
    last_mail_at = now(), last_result = p_result, last_added = p_added,
    confirmation_code = case when p_result = 'confirmation' then p_confirmation_code
      when confirmation_at > now() - interval '3 days' then confirmation_code end,
    confirmation_link = case when p_result = 'confirmation' then p_confirmation_link
      when confirmation_at > now() - interval '3 days' then confirmation_link end,
    confirmation_at = case when p_result = 'confirmation' then now()
      when confirmation_at > now() - interval '3 days' then confirmation_at end
  where user_id = p_user_id;
end;
$$;

revoke all on function public.record_inbound_mail(uuid, text, integer, text, text) from public, anon, authenticated;
grant execute on function public.record_inbound_mail(uuid, text, integer, text, text) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261104130000_keep_gmail_confirmation') on conflict do nothing;
