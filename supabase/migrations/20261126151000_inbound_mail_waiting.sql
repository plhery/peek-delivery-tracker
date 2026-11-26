-- An email whose shop link names no parcel yet starts a wait for the shop (shop_waits), and its address's log says so
-- (`waiting`) instead of "No tracking number in it". The account's log is served with `result` `none` and `waiting`
-- true, which apps that don't know it read as before.
--
-- Apply this before deploying the server that records `waiting`; the server before it never records it.

alter table private.inbound_mailboxes
  drop constraint inbound_mailboxes_last_result_check,
  add constraint inbound_mailboxes_last_result_check
    check (last_result in ('added', 'known', 'named', 'delivered', 'none', 'limited', 'full', 'confirmation', 'ignored', 'waiting'));

create or replace function public.record_inbound_mail(
  p_user_id uuid, p_result text, p_added integer,
  p_confirmation_code text default null, p_confirmation_link text default null,
  p_sender text default null
) returns void language plpgsql security definer set search_path = '' as $$
begin
  if p_result is null or p_result not in ('added', 'known', 'named', 'delivered', 'none', 'limited', 'full', 'confirmation', 'ignored', 'waiting')
      or p_added is null or p_added not between 0 and 100 then
    raise exception 'Invalid email outcome' using errcode = '22023';
  end if;
  update private.inbound_mailboxes as mailbox set
    last_mail_at = now(), last_result = p_result, last_added = p_added,
    recent = case
      when p_result = 'limited' and mailbox.recent -> 0 ->> 'result' = 'limited'
        then jsonb_set(mailbox.recent, '{0,at}', to_jsonb(now()))
      when p_result = 'ignored' and mailbox.recent -> 0 ->> 'result' = 'ignored'
        then jsonb_set(jsonb_set(mailbox.recent, '{0,at}', to_jsonb(now())), '{0,sender}', to_jsonb(left(coalesce(p_sender, ''), 80)))
      else (
        select coalesce(jsonb_agg(listed.entry order by listed.position), '[]'::jsonb)
        from jsonb_array_elements(
          jsonb_build_array(jsonb_build_object(
            'at', now(), 'sender', left(coalesce(p_sender, ''), 80), 'result', p_result, 'added', p_added
          )) || mailbox.recent
        ) with ordinality as listed(entry, position)
        where listed.position <= 20
      )
    end,
    confirmation_code = case when p_result = 'confirmation' then p_confirmation_code
      when mailbox.confirmation_at > now() - interval '3 days' then mailbox.confirmation_code end,
    confirmation_link = case when p_result = 'confirmation' then p_confirmation_link
      when mailbox.confirmation_at > now() - interval '3 days' then mailbox.confirmation_link end,
    confirmation_at = case when p_result = 'confirmation' then now()
      when mailbox.confirmation_at > now() - interval '3 days' then mailbox.confirmation_at end
  where mailbox.user_id = p_user_id;
end;
$$;
revoke all on function public.record_inbound_mail(uuid, text, integer, text, text, text) from public, anon, authenticated;
grant execute on function public.record_inbound_mail(uuid, text, integer, text, text, text) to service_role;

insert into public.applied_migrations (name) values ('20261126151000_inbound_mail_waiting') on conflict do nothing;
