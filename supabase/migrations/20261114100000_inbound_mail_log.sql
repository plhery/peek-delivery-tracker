-- Adding parcels by email: the mailbox keeps what its last twenty emails did, each with when
-- it came, who sent it and how many parcels it added, so that the owner sees which emails
-- gave a parcel and which named none. Never an email's content. Emails refused one after
-- another for the allowance count as one line.
--
-- The server before this one calls record_inbound_mail without a sender, which the new
-- function still takes: apply this before deploying the server that sends one.

alter table private.inbound_mailboxes
  add column recent jsonb not null default '[]'::jsonb check (jsonb_typeof(recent) = 'array');

comment on table private.inbound_mailboxes is
  'Forwarding addresses for adding parcels by email: the token and what the last emails did, with their senders'' names. No email content.';

create or replace function private.inbound_mailbox_json(mailbox private.inbound_mailboxes)
returns jsonb
language sql
stable
set search_path = ''
as $$
  select jsonb_build_object(
    'token', mailbox.token,
    'created_at', mailbox.created_at,
    'used_today', coalesce((
      select counted.used from private.inbound_mail_days counted
      where counted.user_id = mailbox.user_id and counted.day = (now() at time zone 'UTC')::date
    ), 0),
    'last_mail_at', mailbox.last_mail_at,
    'last_result', mailbox.last_result,
    'last_added', mailbox.last_added,
    'recent', mailbox.recent,
    'confirmation_code', case when mailbox.confirmation_at > now() - interval '3 days' then mailbox.confirmation_code end,
    'confirmation_link', case when mailbox.confirmation_at > now() - interval '3 days' then mailbox.confirmation_link end,
    'confirmation_at', case when mailbox.confirmation_at > now() - interval '3 days' then mailbox.confirmation_at end
  );
$$;
revoke all on function private.inbound_mailbox_json(private.inbound_mailboxes) from public, anon, authenticated;

drop function public.record_inbound_mail(uuid, text, integer, text, text);

create function public.record_inbound_mail(
  p_user_id uuid, p_result text, p_added integer,
  p_confirmation_code text default null, p_confirmation_link text default null,
  p_sender text default null
) returns void language plpgsql security definer set search_path = '' as $$
begin
  if p_result is null or p_result not in ('added', 'known', 'none', 'limited', 'full', 'confirmation')
      or p_added is null or p_added not between 0 and 100 then
    raise exception 'Invalid email outcome' using errcode = '22023';
  end if;
  update private.inbound_mailboxes as mailbox set
    last_mail_at = now(), last_result = p_result, last_added = p_added,
    recent = case
      when p_result = 'limited' and mailbox.recent -> 0 ->> 'result' = 'limited'
        then jsonb_set(mailbox.recent, '{0,at}', to_jsonb(now()))
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

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261114100000_inbound_mail_log') on conflict do nothing;
