-- Forwarded emails are kept 30 days instead of seven, to learn to read them better; an account
-- still keeps its newest 100 at most. Only the scheduled sweep changes: the server calls the same
-- function, so this can be applied before or after its deploy.

create or replace function public.forget_old_inbound_mail()
returns integer
language plpgsql
security definer
set search_path = ''
as $$
declare
  forgotten integer;
begin
  delete from private.inbound_mail_kept where received_at < now() - interval '30 days';
  get diagnostics forgotten = row_count;
  return forgotten;
end;
$$;
revoke all on function public.forget_old_inbound_mail() from public, anon, authenticated;
grant execute on function public.forget_old_inbound_mail() to service_role;

comment on table private.inbound_mail_kept is
  'Emails forwarded to an account''s address and read, as received (MIME), kept 30 days to improve the reading.';

insert into public.applied_migrations (name) values ('20261117190000_inbound_mail_kept_30_days') on conflict do nothing;
