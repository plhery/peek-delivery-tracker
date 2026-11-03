-- Adding parcels by email: an account may have one forwarding address, whose token names
-- it. The server reads the tracking numbers out of an email sent there, adds them to the
-- account and drops the email. A mailbox keeps the token and what the last email did,
-- never an email's content; how many emails an account sent a day is kept apart, for a
-- week, so that a new address or turning it off and on gives no new allowance. When Gmail
-- asks to confirm forwarding, its code and link are kept for the owner to finish Gmail's
-- setup, until the next email.
--
-- Only the server reaches these rows, after checking the account's sign-in or the
-- signature of the service that receives the mail. Apply this before deploying the server
-- that calls the functions; the server before it never does.

create table private.inbound_mailboxes (
  user_id uuid primary key references auth.users (id) on delete cascade,
  token text not null unique check (token ~ '^[a-z2-7]{16}$'),
  created_at timestamptz not null default now(),
  last_mail_at timestamptz,
  last_result text check (last_result in ('added', 'known', 'none', 'limited', 'full', 'confirmation')),
  last_added smallint check (last_added between 0 and 100),
  confirmation_code text check (confirmation_code ~ '^[0-9]{6,12}$'),
  confirmation_link text check (
    char_length(confirmation_link) <= 1000 and confirmation_link ~ '^https://(mail-settings|mail|isolated\.mail)\.google\.com/'
  ),
  confirmation_at timestamptz,
  check ((confirmation_code is null and confirmation_link is null) or confirmation_at is not null)
);
alter table private.inbound_mailboxes enable row level security;
revoke all on private.inbound_mailboxes from public, anon, authenticated;
comment on table private.inbound_mailboxes is
  'Forwarding addresses for adding parcels by email: the token and what the last email did. No email content.';

create table private.inbound_mail_days (
  user_id uuid not null references auth.users (id) on delete cascade,
  day date not null,
  used integer not null check (used > 0),
  primary key (user_id, day)
);
alter table private.inbound_mail_days enable row level security;
revoke all on private.inbound_mail_days from public, anon, authenticated;
comment on table private.inbound_mail_days is
  'Emails to an account''s forwarding addresses per UTC day. Kept for seven days.';

create table private.inbound_mail_hours (
  hour timestamptz primary key,
  count integer not null default 0 check (count >= 0)
);
alter table private.inbound_mail_hours enable row level security;
revoke all on private.inbound_mail_hours from public, anon, authenticated;
comment on table private.inbound_mail_hours is
  'Emails to every forwarding address together per UTC hour. Kept for seven days.';

-- The mailbox as the server shows it to its owner. A Gmail confirmation older than three
-- days is left out: Gmail's own code has expired by then.
create function private.inbound_mailbox_json(mailbox private.inbound_mailboxes)
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
    'confirmation_code', case when mailbox.confirmation_at > now() - interval '3 days' then mailbox.confirmation_code end,
    'confirmation_link', case when mailbox.confirmation_at > now() - interval '3 days' then mailbox.confirmation_link end,
    'confirmation_at', case when mailbox.confirmation_at > now() - interval '3 days' then mailbox.confirmation_at end
  );
$$;
revoke all on function private.inbound_mailbox_json(private.inbound_mailboxes) from public, anon, authenticated;

create function public.inbound_mailbox(p_user_id uuid)
returns jsonb
language sql
stable
security definer
set search_path = ''
as $$
  select private.inbound_mailbox_json(mailbox) from private.inbound_mailboxes mailbox where mailbox.user_id = p_user_id;
$$;
revoke all on function public.inbound_mailbox(uuid) from public, anon, authenticated;
grant execute on function public.inbound_mailbox(uuid) to service_role;

-- A new address for the account, in place of any it had: emails to the old one are dropped
-- from then on.
create function public.open_inbound_mailbox(p_user_id uuid, p_token text)
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
  returning * into saved;
  return private.inbound_mailbox_json(saved);
end;
$$;
revoke all on function public.open_inbound_mailbox(uuid, text) from public, anon, authenticated;
grant execute on function public.open_inbound_mailbox(uuid, text) to service_role;

create function public.close_inbound_mailbox(p_user_id uuid)
returns boolean
language sql
security definer
set search_path = ''
as $$
  with closed as (delete from private.inbound_mailboxes where user_id = p_user_id returning 1)
  select exists (select 1 from closed);
$$;
revoke all on function public.close_inbound_mailbox(uuid) from public, anon, authenticated;
grant execute on function public.close_inbound_mailbox(uuid) to service_role;

-- The account an address names, or null. Counts nothing: the server checks that the
-- account takes email before it claims an allowance.
create function public.inbound_mailbox_owner(p_token text)
returns uuid
language sql
stable
security definer
set search_path = ''
as $$
  select user_id from private.inbound_mailboxes where token = p_token;
$$;
revoke all on function public.inbound_mailbox_owner(text) from public, anon, authenticated;
grant execute on function public.inbound_mailbox_owner(text) to service_role;

-- Counts one email to the account against this hour's allowance for every address and the
-- account's own for today (UTC), unless one of them is used up: then nothing is counted and
-- `scope` names it, `overall` before `account`. The first email of an hour forgets the hours
-- and days of over a week ago.
create function public.claim_inbound_mail(p_user_id uuid, p_limit integer, p_overall_limit integer)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  this_hour constant timestamptz := date_trunc('hour', now(), 'UTC');
  today constant date := (now() at time zone 'UTC')::date;
  used_overall integer;
  claimed integer;
begin
  if p_user_id is null or p_limit is null or p_limit < 0 or p_overall_limit is null or p_overall_limit < 0 then
    raise exception 'Invalid email allowance' using errcode = '22023';
  end if;

  insert into private.inbound_mail_hours (hour) values (this_hour) on conflict do nothing;
  if found then
    delete from private.inbound_mail_hours where hour < this_hour - interval '7 days';
    delete from private.inbound_mail_days where day < today - 7;
  end if;
  select count into used_overall from private.inbound_mail_hours where hour = this_hour for update;
  if used_overall >= p_overall_limit then
    return jsonb_build_object('allowed', false, 'scope', 'overall');
  end if;

  if p_limit > 0 then
    insert into private.inbound_mail_days as counted (user_id, day, used)
    values (p_user_id, today, 1)
    on conflict (user_id, day) do update set used = counted.used + 1 where counted.used < p_limit
    returning used into claimed;
  end if;
  if claimed is null then
    return jsonb_build_object('allowed', false, 'scope', 'account');
  end if;

  update private.inbound_mail_hours set count = count + 1 where hour = this_hour;
  return jsonb_build_object('allowed', true, 'scope', null);
end;
$$;
revoke all on function public.claim_inbound_mail(uuid, integer, integer) from public, anon, authenticated;
grant execute on function public.claim_inbound_mail(uuid, integer, integer) to service_role;

-- What an email did. A Gmail confirmation keeps its code and link; any other email clears
-- them, since forwarding then works.
create function public.record_inbound_mail(
  p_user_id uuid,
  p_result text,
  p_added integer,
  p_confirmation_code text default null,
  p_confirmation_link text default null
)
returns void
language plpgsql
security definer
set search_path = ''
as $$
begin
  if p_result is null or p_result not in ('added', 'known', 'none', 'limited', 'full', 'confirmation')
      or p_added is null or p_added not between 0 and 100 then
    raise exception 'Invalid email outcome' using errcode = '22023';
  end if;
  update private.inbound_mailboxes set
    last_mail_at = now(),
    last_result = p_result,
    last_added = p_added,
    confirmation_code = case when p_result = 'confirmation' then p_confirmation_code end,
    confirmation_link = case when p_result = 'confirmation' then p_confirmation_link end,
    confirmation_at = case when p_result = 'confirmation' then now() end
  where user_id = p_user_id;
end;
$$;
revoke all on function public.record_inbound_mail(uuid, text, integer, text, text) from public, anon, authenticated;
grant execute on function public.record_inbound_mail(uuid, text, integer, text, text) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261103200000_inbound_mailboxes') on conflict do nothing;
