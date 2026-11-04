-- An overall allowance that is running out or used up is reported once for its UTC day or
-- hour. Each server process remembered that for itself, so every deploy or restart
-- reported the same day again. The server now claims each report here first: the first
-- claim of a report for its period sends it, and the others say nothing.
--
-- Only the server reaches these rows. Apply this before deploying the server that calls
-- the function; the server before it never does.

create table private.allowance_reports (
  report text not null check (report ~ '^(public|account):(lookup|detection):(running_out|used_up)$'),
  period text not null check (period ~ '^[0-9]{4}-[0-9]{2}-[0-9]{2}(T[0-9]{2})?$'),
  reported_at timestamptz not null default now(),
  primary key (report, period)
);
create index allowance_reports_reported_at on private.allowance_reports (reported_at);
alter table private.allowance_reports enable row level security;
revoke all on private.allowance_reports from public, anon, authenticated;
comment on table private.allowance_reports is
  'Which overall allowance states were reported, for which UTC day or hour. Kept for a week.';

-- True for the first claim of a report in its period, false for every later one. A first
-- claim forgets the reports of over a week ago.
create function public.claim_allowance_report(p_report text, p_period text)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
begin
  if p_report is null or p_report !~ '^(public|account):(lookup|detection):(running_out|used_up)$'
      or p_period is null or p_period !~ '^[0-9]{4}-[0-9]{2}-[0-9]{2}(T[0-9]{2})?$' then
    raise exception 'Invalid allowance report' using errcode = '22023';
  end if;
  insert into private.allowance_reports (report, period) values (p_report, p_period)
  on conflict do nothing;
  if not found then
    return false;
  end if;
  delete from private.allowance_reports where reported_at < now() - interval '7 days';
  return true;
end;
$$;
revoke all on function public.claim_allowance_report(text, text) from public, anon, authenticated;
grant execute on function public.claim_allowance_report(text, text) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261104100000_allowance_reports') on conflict do nothing;
