-- The retry route trial's residential bytes are reserved in the database
-- before the attempt begins, so every server process counts against one cap.
--
-- Universal Parcel Scraper 0.985 asks `reserve_retry_route_trial_attempt` for a
-- residential attempt before it hands the worker a lease. The function locks
-- the trial's row in `retry_route_trials`, then ends the trial for good once it
-- is past its deadline, has its residential samples or has charged its bytes;
-- refuses while another residential attempt is still running; and otherwise
-- saves the attempt with its reservation (the worker's byte allowance plus a
-- margin) as its charge. The settlement replaces the charge with the reported
-- bytes plus the margin when the worker proved them, and never removes it.
--
-- A residential attempt is created only by that function: a trigger refuses any
-- other insert, and an attempt keeps its trial, route, start and reservation.
-- `end_retry_route_trial` records an end a server found from the attempts
-- (the deadline or the samples); the first end stays.
--
-- Apply this before deploying the server that uses it. The server before it
-- keeps working on this schema while its trial is off: FEDEX_RETRY_TRIAL is
-- empty in production until the new server is deployed.

begin;

create table public.retry_route_trials (
  trial text primary key check (trial ~ '^[A-Za-z0-9_.:-]{1,64}$'),
  started_at timestamptz not null,
  ended_at timestamptz,
  end_reason text check (end_reason in ('samples', 'bytes', 'deadline')),
  check ((ended_at is null) = (end_reason is null))
);
alter table public.retry_route_trials enable row level security;
revoke all on table public.retry_route_trials from public, anon, authenticated, service_role;
grant select on table public.retry_route_trials to service_role;

create function public.retry_route_trials_keep_end()
returns trigger
language plpgsql
set search_path = pg_catalog
as $$
begin
  if tg_op = 'DELETE' then
    raise exception 'a retry route trial is kept' using errcode = 'check_violation';
  end if;
  if old.end_reason is not null and (new.end_reason is distinct from old.end_reason
      or new.ended_at is distinct from old.ended_at) then
    raise exception 'an ended retry route trial stays ended' using errcode = 'check_violation';
  end if;
  if new.trial <> old.trial or new.started_at <> old.started_at then
    raise exception 'a retry route trial keeps its name and start' using errcode = 'check_violation';
  end if;
  return new;
end;
$$;
create trigger retry_route_trials_keep_end
  before update or delete on public.retry_route_trials
  for each row execute function public.retry_route_trials_keep_end();
revoke all on function public.retry_route_trials_keep_end() from public, anon, authenticated;

alter table public.retry_route_trial_attempts
  add column reserved_bytes bigint check (reserved_bytes > 0),
  add column charged_bytes bigint check (charged_bytes >= 0),
  add constraint retry_route_trial_attempts_reservation_check check (
    (route = 'residential') = (reserved_bytes is not null)
    and (route = 'residential') = (charged_bytes is not null)
  );

create function public.retry_route_trial_attempts_guard()
returns trigger
language plpgsql
set search_path = pg_catalog
as $$
begin
  if tg_op = 'INSERT' then
    if new.route = 'residential'
        and current_setting('app.retry_route_reservation', true) is distinct from new.id::text then
      raise exception 'a residential retry attempt is created by reserve_retry_route_trial_attempt'
        using errcode = 'check_violation';
    end if;
    return new;
  end if;
  if new.id <> old.id or new.trial <> old.trial or new.route <> old.route or new.assigned_at <> old.assigned_at
      or new.reserved_bytes is distinct from old.reserved_bytes then
    raise exception 'a retry attempt keeps its trial, route, start and reservation' using errcode = 'check_violation';
  end if;
  if old.settled_at is not null and new.settled_at is null then
    raise exception 'a settled retry attempt stays settled' using errcode = 'check_violation';
  end if;
  return new;
end;
$$;
create trigger retry_route_trial_attempts_guard
  before insert or update on public.retry_route_trial_attempts
  for each row execute function public.retry_route_trial_attempts_guard();
revoke all on function public.retry_route_trial_attempts_guard() from public, anon, authenticated;

-- The trial's row, created and dated from its first attempt if it has none.
create function public.retry_route_trial_row(p_trial text, p_at timestamptz)
returns public.retry_route_trials
language plpgsql
security definer
set search_path = pg_catalog
as $$
declare
  result public.retry_route_trials;
begin
  insert into public.retry_route_trials (trial, started_at)
  values (p_trial, least(p_at, (select min(assigned_at) from public.retry_route_trial_attempts where trial = p_trial)))
  on conflict (trial) do nothing;
  select * into strict result from public.retry_route_trials where trial = p_trial for update;
  return result;
end;
$$;
revoke all on function public.retry_route_trial_row(text, timestamptz) from public, anon, authenticated, service_role;

create function public.reserve_retry_route_trial_attempt(
  p_id uuid,
  p_trial text,
  p_assigned_at timestamptz,
  p_reserved_bytes bigint,
  p_target_attempts integer,
  p_max_residential_bytes bigint,
  p_duration_ms bigint,
  p_lease_ms bigint
)
returns jsonb
language plpgsql
security definer
set search_path = pg_catalog
as $$
declare
  state public.retry_route_trials;
  clock timestamptz := greatest(clock_timestamp(), p_assigned_at);
  samples integer;
  charged bigint;
  running boolean;
  reason text;
begin
  if p_reserved_bytes is null or p_reserved_bytes <= 0 or p_target_attempts is null or p_target_attempts < 1
      or p_max_residential_bytes is null or p_max_residential_bytes < p_reserved_bytes
      or p_duration_ms is null or p_duration_ms <= 0 or p_lease_ms is null or p_lease_ms <= 0 then
    raise exception 'invalid retry route reservation' using errcode = '22023';
  end if;
  state := public.retry_route_trial_row(p_trial, p_assigned_at);
  if state.end_reason is not null then
    return jsonb_build_object('granted', false, 'end_reason', state.end_reason, 'ended_at', state.ended_at);
  end if;

  select count(*), coalesce(sum(coalesce(charged_bytes, reserved_bytes)), 0),
      coalesce(bool_or(settled_at is null and assigned_at > clock - make_interval(secs => p_lease_ms / 1000.0)), false)
    into samples, charged, running
    from public.retry_route_trial_attempts
    where trial = p_trial and route = 'residential' and outcome is distinct from 'not_started';

  reason := case
    when clock >= state.started_at + make_interval(secs => p_duration_ms / 1000.0) then 'deadline'
    when samples >= p_target_attempts then 'samples'
    when charged + p_reserved_bytes > p_max_residential_bytes and not running then 'bytes'
  end;
  if reason is not null then
    update public.retry_route_trials
      set end_reason = reason,
        ended_at = case when reason = 'deadline' then state.started_at + make_interval(secs => p_duration_ms / 1000.0) else clock end
      where trial = p_trial
      returning * into state;
    return jsonb_build_object('granted', false, 'end_reason', state.end_reason, 'ended_at', state.ended_at);
  end if;
  if running or charged + p_reserved_bytes > p_max_residential_bytes
      or exists (select 1 from public.retry_route_trial_attempts where id = p_id) then
    return jsonb_build_object('granted', false, 'busy', true);
  end if;

  perform set_config('app.retry_route_reservation', p_id::text, true);
  insert into public.retry_route_trial_attempts (id, trial, route, assigned_at, reserved_bytes, charged_bytes)
  values (p_id, p_trial, 'residential', p_assigned_at, p_reserved_bytes, p_reserved_bytes);
  perform set_config('app.retry_route_reservation', '', true);
  return jsonb_build_object('granted', true, 'started_at', state.started_at);
end;
$$;
revoke all on function public.reserve_retry_route_trial_attempt(uuid, text, timestamptz, bigint, integer, bigint, bigint, bigint)
  from public, anon, authenticated;
grant execute on function public.reserve_retry_route_trial_attempt(uuid, text, timestamptz, bigint, integer, bigint, bigint, bigint)
  to service_role;

create function public.end_retry_route_trial(p_trial text, p_reason text, p_at timestamptz)
returns void
language plpgsql
security definer
set search_path = pg_catalog
as $$
declare
  state public.retry_route_trials;
begin
  if p_reason is null or p_reason not in ('samples', 'bytes', 'deadline') or p_at is null then
    raise exception 'invalid retry route trial end' using errcode = '22023';
  end if;
  state := public.retry_route_trial_row(p_trial, p_at);
  if state.end_reason is null then
    update public.retry_route_trials set end_reason = p_reason, ended_at = p_at where trial = p_trial;
  end if;
end;
$$;
revoke all on function public.end_retry_route_trial(text, text, timestamptz) from public, anon, authenticated;
grant execute on function public.end_retry_route_trial(text, text, timestamptz) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261106030000_retry_route_trial_reservations') on conflict do nothing;

commit;
