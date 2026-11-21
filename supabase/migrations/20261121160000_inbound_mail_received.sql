-- Adding parcels by email: an address's daily allowance counts the emails read, not the ones ignored, so that a
-- reader who forwards all their mail still has it for the shipping ones. Every email an address receives, read or
-- not, counts against a larger allowance of its own (`received`), taken before the email is parsed;
-- claim_inbound_mail then counts only an email that is read, as before.
--
-- The server before this one claims every email with claim_inbound_mail, which still works: apply this before
-- deploying the server that calls claim_received_inbound_mail.

alter table private.inbound_mail_days
  add column received integer not null default 0 check (received >= 0),
  drop constraint inbound_mail_days_used_check,
  add constraint inbound_mail_days_used_check check (used >= 0);

-- Until now every email received was counted as used.
update private.inbound_mail_days set received = used;

comment on table private.inbound_mail_days is
  'Emails to an account''s forwarding addresses per UTC day: those read (used) and all of them (received). Kept for seven days.';

-- Counts one email to the account against its allowance of emails received today (UTC), read or not, unless it is
-- used up. claim_inbound_mail forgets the days of over a week ago.
create function public.claim_received_inbound_mail(p_user_id uuid, p_limit integer)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
declare
  claimed integer;
begin
  if p_user_id is null or p_limit is null or p_limit < 0 then
    raise exception 'Invalid email allowance' using errcode = '22023';
  end if;
  if p_limit = 0 then
    return false;
  end if;
  insert into private.inbound_mail_days as counted (user_id, day, used, received)
  values (p_user_id, (now() at time zone 'UTC')::date, 0, 1)
  on conflict (user_id, day) do update set received = counted.received + 1 where counted.received < p_limit
  returning received into claimed;
  return claimed is not null;
end;
$$;
revoke all on function public.claim_received_inbound_mail(uuid, integer) from public, anon, authenticated;
grant execute on function public.claim_received_inbound_mail(uuid, integer) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261121160000_inbound_mail_received') on conflict do nothing;
