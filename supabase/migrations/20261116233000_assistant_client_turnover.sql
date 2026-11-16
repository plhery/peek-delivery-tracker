-- Client registration no longer stops at a thousand clients an hour: anyone with enough
-- addresses could use that up, and no assistant could register until the hour was over.
-- Instead at most 2,000 registered clients that no grant uses are kept, and a new one
-- pushes out the oldest of them. Clients a day old that no grant uses still go, and a
-- client a grant uses is never pushed out. The server's limits per address stay.
--
-- Either server works with it: the one before only stops seeing the refusal it handled.

create or replace function public.register_assistant_client(p_id text, p_name text, p_uri text, p_redirect_uris text[])
returns void
language plpgsql
security definer
set search_path = ''
as $$
begin
  delete from private.assistant_clients registered
  where registered.created_at < now() - interval '1 day'
    and not exists (select 1 from private.assistant_grants granted where granted.client_id = registered.id);
  insert into private.assistant_clients (id, name, uri, redirect_uris) values (p_id, p_name, p_uri, p_redirect_uris);
  delete from private.assistant_clients pushed_out
  where pushed_out.id in (
    select unused.id from private.assistant_clients unused
    where not exists (select 1 from private.assistant_grants granted where granted.client_id = unused.id)
    order by unused.created_at desc, unused.id desc
    offset 2000
  );
end;
$$;
revoke all on function public.register_assistant_client(text, text, text, text[]) from public, anon, authenticated;
grant execute on function public.register_assistant_client(text, text, text, text[]) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261116233000_assistant_client_turnover') on conflict do nothing;
