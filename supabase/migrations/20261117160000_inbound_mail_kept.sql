-- Adding parcels by email keeps each email it read for seven days, to learn to read emails
-- better: one that gave no number, or the wrong one, can be read again. An account keeps its
-- newest 100 at most, so a flood can't fill the disk. Only an email that
-- reached an address and was read is kept: never one to an unknown address, one an allowance
-- or the parcel limit stopped, or Gmail's forwarding confirmation, which holds a code. The scheduled run deletes
-- those older than seven days, turning the address off deletes the account's at once, and
-- deleting the account deletes them with it.
--
-- An email can also have named a parcel (`named`: one the account followed without a name took
-- the shop's) or said its parcel was delivered (`delivered`), which adds none.
--
-- Apply this before deploying the server that keeps emails and records the new outcomes; the
-- server before it calls none of the new functions and records only the old outcomes.

create table private.inbound_mail_kept (
  id bigint generated always as identity primary key,
  user_id uuid not null references auth.users (id) on delete cascade,
  received_at timestamptz not null default now(),
  raw bytea not null check (octet_length(raw) between 1 and 1048576)
);
create index inbound_mail_kept_user_id_idx on private.inbound_mail_kept (user_id);
create index inbound_mail_kept_received_at_idx on private.inbound_mail_kept (received_at);
alter table private.inbound_mail_kept enable row level security;
revoke all on private.inbound_mail_kept from public, anon, authenticated;
comment on table private.inbound_mail_kept is
  'Emails forwarded to an account''s address and read, as received (MIME), kept seven days to improve the reading.';

alter table private.inbound_mailboxes
  drop constraint inbound_mailboxes_last_result_check,
  add constraint inbound_mailboxes_last_result_check
    check (last_result in ('added', 'known', 'named', 'delivered', 'none', 'limited', 'full', 'confirmation'));

-- Keeps one email (base64) for the account, while its address is on, and the account's newest 100 at most.
-- Returns whether it was kept.
create function public.keep_inbound_mail(p_user_id uuid, p_raw text)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
declare
  content bytea;
  kept boolean;
begin
  -- 1 MiB, the most the server takes, is 1,398,104 characters of base64.
  if p_user_id is null or p_raw is null or char_length(p_raw) > 1398104 then
    raise exception 'Invalid email' using errcode = '22023';
  end if;
  content := decode(p_raw, 'base64');
  if octet_length(content) not between 1 and 1048576 then
    raise exception 'Invalid email' using errcode = '22023';
  end if;
  insert into private.inbound_mail_kept (user_id, raw)
  select p_user_id, content
  where exists (select 1 from private.inbound_mailboxes where user_id = p_user_id);
  kept := found;
  delete from private.inbound_mail_kept
  where user_id = p_user_id
    and id < (select min(newest.id) from (
      select id from private.inbound_mail_kept where user_id = p_user_id order by id desc limit 100
    ) as newest);
  return kept;
end;
$$;
revoke all on function public.keep_inbound_mail(uuid, text) from public, anon, authenticated;
grant execute on function public.keep_inbound_mail(uuid, text) to service_role;

-- The scheduled run's sweep: emails kept over seven days. Returns how many it deleted.
create function public.forget_old_inbound_mail()
returns integer
language plpgsql
security definer
set search_path = ''
as $$
declare
  forgotten integer;
begin
  delete from private.inbound_mail_kept where received_at < now() - interval '7 days';
  get diagnostics forgotten = row_count;
  return forgotten;
end;
$$;
revoke all on function public.forget_old_inbound_mail() from public, anon, authenticated;
grant execute on function public.forget_old_inbound_mail() to service_role;

-- Turning the address off deletes the emails it kept too.
create or replace function public.close_inbound_mailbox(p_user_id uuid)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
begin
  delete from private.inbound_mail_kept where user_id = p_user_id;
  delete from private.inbound_mailboxes where user_id = p_user_id;
  return found;
end;
$$;
revoke all on function public.close_inbound_mailbox(uuid) from public, anon, authenticated;
grant execute on function public.close_inbound_mailbox(uuid) to service_role;

create or replace function public.record_inbound_mail(
  p_user_id uuid, p_result text, p_added integer,
  p_confirmation_code text default null, p_confirmation_link text default null,
  p_sender text default null
) returns void language plpgsql security definer set search_path = '' as $$
begin
  if p_result is null or p_result not in ('added', 'known', 'named', 'delivered', 'none', 'limited', 'full', 'confirmation')
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

comment on table private.inbound_mailboxes is
  'Forwarding addresses for adding parcels by email: the token and what the last emails did, with their senders'' names. The emails are kept apart (inbound_mail_kept).';

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261117160000_inbound_mail_kept') on conflict do nothing;
