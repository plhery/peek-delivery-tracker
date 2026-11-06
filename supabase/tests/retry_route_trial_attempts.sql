\set ON_ERROR_STOP on
begin;

-- Only the server reads and writes the retry trial's attempts.
do $$
begin
  if has_table_privilege('anon', 'public.retry_route_trial_attempts', 'SELECT')
      or has_table_privilege('authenticated', 'public.retry_route_trial_attempts', 'SELECT')
      or has_table_privilege('authenticated', 'public.retry_route_trial_attempts', 'INSERT') then
    raise exception 'public database roles can read the retry trial attempts';
  end if;
  if not has_table_privilege('service_role', 'public.retry_route_trial_attempts', 'SELECT')
      or not has_table_privilege('service_role', 'public.retry_route_trial_attempts', 'INSERT')
      or not has_table_privilege('service_role', 'public.retry_route_trial_attempts', 'UPDATE') then
    raise exception 'the service role cannot keep the retry trial attempts';
  end if;
  if not (select relrowsecurity from pg_class where oid = 'public.retry_route_trial_attempts'::regclass)
      or exists (select 1 from pg_policies where schemaname = 'public' and tablename = 'retry_route_trial_attempts') then
    raise exception 'the retry trial attempts are readable through row level security';
  end if;
end;
$$;

-- An attempt is saved when assigned and again, under its id, when it settles; only the
-- two proxied routes, a trial name's characters and an outcome's are taken.
insert into public.retry_route_trial_attempts (id, trial, route, assigned_at)
values ('00000000-0000-4000-8000-000000000001', 'fedex-datacenter-1', 'residential', '2026-11-06T01:00:00Z');
insert into public.retry_route_trial_attempts (id, trial, route, assigned_at, settled_at, outcome, history, bytes)
values ('00000000-0000-4000-8000-000000000001', 'fedex-datacenter-1', 'residential', '2026-11-06T01:00:00Z',
  '2026-11-06T01:00:40Z', 'ok', true, 1900000)
on conflict (id) do update set settled_at = excluded.settled_at, outcome = excluded.outcome,
  history = excluded.history, bytes = excluded.bytes;
do $$
begin
  if (select bytes from public.retry_route_trial_attempts where id = '00000000-0000-4000-8000-000000000001') <> 1900000 then
    raise exception 'a retry trial attempt was not saved again under its id';
  end if;
  begin
    insert into public.retry_route_trial_attempts (id, trial, route, assigned_at) values (gen_random_uuid(), 'fedex-datacenter-1', 'default', now());
    raise exception 'a retry trial attempt took the default route';
  exception when check_violation then null;
  end;
  begin
    insert into public.retry_route_trial_attempts (id, trial, route, assigned_at) values (gen_random_uuid(), 'a trial', 'datacenter', now());
    raise exception 'a retry trial attempt took a malformed trial name';
  exception when check_violation then null;
  end;
  begin
    insert into public.retry_route_trial_attempts (id, trial, route, assigned_at, outcome) values (gen_random_uuid(), 'fedex-datacenter-1', 'datacenter', now(), 'Proxy at 10.0.0.1');
    raise exception 'a retry trial attempt took free text as its outcome';
  exception when check_violation then null;
  end;
end;
$$;

rollback;
