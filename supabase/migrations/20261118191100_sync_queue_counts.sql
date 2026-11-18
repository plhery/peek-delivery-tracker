create function public.sync_queue_counts()
returns table (kind text, queued bigint, oldest timestamptz)
language sql stable set search_path = '' as $$
  select case when j.kind = 'scheduled' then 'scheduled' when j.priority = -30 then 'release'
              when j.user_id is null then 'lookup' else 'account' end,
         count(*), min(j.requested_at)
  from public.sync_jobs j where j.state = 'queued'
  group by 1;
$$;
revoke all on function public.sync_queue_counts() from public, anon, authenticated;
grant execute on function public.sync_queue_counts() to service_role;

notify pgrst, 'reload schema';
insert into public.applied_migrations (name) values ('20261118191100_sync_queue_counts') on conflict do nothing;
