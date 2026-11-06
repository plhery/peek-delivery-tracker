\set ON_ERROR_STOP on
begin;

-- The server reads the trials but changes them only through the two functions.
do $$
begin
  if has_table_privilege('anon', 'public.retry_route_trials', 'SELECT')
      or has_table_privilege('authenticated', 'public.retry_route_trials', 'SELECT')
      or has_table_privilege('service_role', 'public.retry_route_trials', 'INSERT')
      or has_table_privilege('service_role', 'public.retry_route_trials', 'UPDATE')
      or has_table_privilege('service_role', 'public.retry_route_trials', 'DELETE') then
    raise exception 'a database role can change the retry route trials directly';
  end if;
  if not has_table_privilege('service_role', 'public.retry_route_trials', 'SELECT') then
    raise exception 'the service role cannot read the retry route trials';
  end if;
  if has_function_privilege('anon', 'public.reserve_retry_route_trial_attempt(uuid, text, timestamptz, bigint, integer, bigint, bigint, bigint)', 'EXECUTE')
      or has_function_privilege('authenticated', 'public.end_retry_route_trial(text, text, timestamptz)', 'EXECUTE')
      or has_function_privilege('service_role', 'public.retry_route_trial_row(text, timestamptz)', 'EXECUTE')
      or not has_function_privilege('service_role', 'public.reserve_retry_route_trial_attempt(uuid, text, timestamptz, bigint, integer, bigint, bigint, bigint)', 'EXECUTE')
      or not has_function_privilege('service_role', 'public.end_retry_route_trial(text, text, timestamptz)', 'EXECUTE') then
    raise exception 'the retry route reservation functions have the wrong grants';
  end if;
end;
$$;

set local role service_role;

-- An earlier datacenter attempt dates the trial; a residential attempt comes only from a reservation.
insert into public.retry_route_trial_attempts (id, trial, route, assigned_at, settled_at, outcome, history, bytes)
values ('00000000-0000-4000-8000-000000000010', 'reserve-test', 'datacenter', now() - interval '1 hour', now() - interval '59 minutes', 'ok', true, 3000000);
do $$
declare
  answer jsonb;
begin
  begin
    insert into public.retry_route_trial_attempts (id, trial, route, assigned_at, reserved_bytes, charged_bytes)
    values (gen_random_uuid(), 'reserve-test', 'residential', now(), 11000000, 11000000);
    raise exception 'a residential attempt was created without a reservation';
  exception when check_violation then null;
  end;
  begin
    insert into public.retry_route_trial_attempts (id, trial, route, assigned_at, reserved_bytes, charged_bytes)
    values ('00000000-0000-4000-8000-000000000011', 'reserve-test', 'datacenter', now(), 11000000, 11000000);
    raise exception 'a datacenter attempt took a reservation';
  exception when check_violation then null;
  end;

  -- Cap 25 MB, 11 MB a reservation, 3 samples, 6 hours, a 180 s lease.
  answer := public.reserve_retry_route_trial_attempt('00000000-0000-4000-8000-000000000021', 'reserve-test', now(), 11000000, 3, 25000000, 21600000, 180000);
  if not (answer->>'granted')::boolean
      or (answer->>'started_at')::timestamptz <> (select assigned_at from public.retry_route_trial_attempts where id = '00000000-0000-4000-8000-000000000010') then
    raise exception 'the first reservation was refused or dated wrongly: %', answer;
  end if;
  if (select charged_bytes from public.retry_route_trial_attempts where id = '00000000-0000-4000-8000-000000000021') <> 11000000 then
    raise exception 'a reservation was not charged in full';
  end if;

  -- One residential attempt at a time.
  answer := public.reserve_retry_route_trial_attempt('00000000-0000-4000-8000-000000000022', 'reserve-test', now(), 11000000, 3, 25000000, 21600000, 180000);
  if answer <> '{"granted": false, "busy": true}'::jsonb
      or exists (select 1 from public.retry_route_trial_attempts where id = '00000000-0000-4000-8000-000000000022') then
    raise exception 'a second reservation ran beside the first: %', answer;
  end if;

  -- A settlement keeps the reservation and its start, and stays settled.
  update public.retry_route_trial_attempts set settled_at = now(), outcome = 'ok', history = true, bytes = 2000000, charged_bytes = 3000000
    where id = '00000000-0000-4000-8000-000000000021';
  begin
    update public.retry_route_trial_attempts set reserved_bytes = 1 where id = '00000000-0000-4000-8000-000000000021';
    raise exception 'a reservation was changed';
  exception when check_violation then null;
  end;
  begin
    update public.retry_route_trial_attempts set route = 'datacenter', reserved_bytes = null, charged_bytes = null
      where id = '00000000-0000-4000-8000-000000000021';
    raise exception 'an attempt changed its route';
  exception when check_violation then null;
  end;
  begin
    update public.retry_route_trial_attempts set settled_at = null where id = '00000000-0000-4000-8000-000000000021';
    raise exception 'a settled attempt was reopened';
  exception when check_violation then null;
  end;
  begin
    update public.retry_route_trial_attempts set charged_bytes = null where id = '00000000-0000-4000-8000-000000000021';
    raise exception 'a residential charge was removed';
  exception when check_violation then null;
  end;
  begin
    insert into public.retry_route_trial_attempts (id, trial, route, assigned_at)
    values ('00000000-0000-4000-8000-000000000021', 'reserve-test', 'datacenter', now())
    on conflict (id) do update set route = excluded.route, reserved_bytes = null, charged_bytes = null;
    raise exception 'a datacenter save replaced a residential attempt';
  exception when check_violation then null;
  end;

  -- An unused reservation counts nothing; the same id is never reserved twice.
  answer := public.reserve_retry_route_trial_attempt('00000000-0000-4000-8000-000000000021', 'reserve-test', now(), 11000000, 3, 25000000, 21600000, 180000);
  if answer <> '{"granted": false, "busy": true}'::jsonb then raise exception 'an id was reserved twice: %', answer; end if;
  answer := public.reserve_retry_route_trial_attempt('00000000-0000-4000-8000-000000000023', 'reserve-test', now(), 11000000, 3, 25000000, 21600000, 180000);
  if not (answer->>'granted')::boolean then raise exception 'the second reservation was refused: %', answer; end if;
  update public.retry_route_trial_attempts set settled_at = now(), outcome = 'not_started', charged_bytes = 0
    where id = '00000000-0000-4000-8000-000000000023';

  -- 3 MB charged: 3 + 11 fits in 25, and then 14 + 11 still does; 25 + 11 does not.
  answer := public.reserve_retry_route_trial_attempt('00000000-0000-4000-8000-000000000024', 'reserve-test', now(), 11000000, 3, 25000000, 21600000, 180000);
  if not (answer->>'granted')::boolean then raise exception 'a reservation within the cap was refused: %', answer; end if;
  update public.retry_route_trial_attempts set settled_at = now(), outcome = 'transport' where id = '00000000-0000-4000-8000-000000000024';
  answer := public.reserve_retry_route_trial_attempt('00000000-0000-4000-8000-000000000025', 'reserve-test', now(), 11000000, 3, 25000000, 21600000, 180000);
  if not (answer->>'granted')::boolean then raise exception 'a reservation up to the cap was refused: %', answer; end if;

  -- While it runs, a reservation past the cap waits; once it settles, the trial ends on its bytes, for good.
  answer := public.reserve_retry_route_trial_attempt('00000000-0000-4000-8000-000000000026', 'reserve-test', now(), 11000000, 9, 25000000, 21600000, 180000);
  if answer <> '{"granted": false, "busy": true}'::jsonb then raise exception 'a running attempt did not hold the trial: %', answer; end if;
  update public.retry_route_trial_attempts set settled_at = now(), outcome = 'ok', history = true, bytes = 100, charged_bytes = 1000100
    where id = '00000000-0000-4000-8000-000000000025';
  answer := public.reserve_retry_route_trial_attempt('00000000-0000-4000-8000-000000000026', 'reserve-test', now(), 11000000, 9, 25000000, 21600000, 180000);
  if answer->>'end_reason' <> 'bytes' or answer ? 'granted' and (answer->>'granted')::boolean then
    raise exception 'the trial did not end on its bytes: %', answer;
  end if;
  -- A lower charge after the end, a later end and a deadline do not reopen it.
  update public.retry_route_trial_attempts set charged_bytes = 0 where id = '00000000-0000-4000-8000-000000000024';
  perform public.end_retry_route_trial('reserve-test', 'deadline', now());
  answer := public.reserve_retry_route_trial_attempt('00000000-0000-4000-8000-000000000027', 'reserve-test', now(), 1000, 9, 25000000, 21600000, 180000);
  if answer->>'end_reason' <> 'bytes' or (select end_reason from public.retry_route_trials where trial = 'reserve-test') <> 'bytes'
      or exists (select 1 from public.retry_route_trial_attempts where id = '00000000-0000-4000-8000-000000000027') then
    raise exception 'an ended trial reopened: %', answer;
  end if;

  -- The samples and the deadline end a trial too, the deadline dated from the start.
  answer := public.reserve_retry_route_trial_attempt('00000000-0000-4000-8000-000000000031', 'samples-test', now(), 1000, 1, 25000000, 21600000, 180000);
  update public.retry_route_trial_attempts set settled_at = now(), outcome = 'ok' where id = '00000000-0000-4000-8000-000000000031';
  answer := public.reserve_retry_route_trial_attempt('00000000-0000-4000-8000-000000000032', 'samples-test', now(), 1000, 1, 25000000, 21600000, 180000);
  if answer->>'end_reason' <> 'samples' then raise exception 'the trial did not end on its samples: %', answer; end if;
  answer := public.reserve_retry_route_trial_attempt('00000000-0000-4000-8000-000000000041', 'deadline-test', now() - interval '7 hours', 1000, 9, 25000000, 21600000, 180000);
  if answer->>'end_reason' <> 'deadline'
      or (answer->>'ended_at')::timestamptz <> (select started_at + interval '6 hours' from public.retry_route_trials where trial = 'deadline-test') then
    raise exception 'the trial did not end at its deadline: %', answer;
  end if;

  -- A server records the end it found; the first one stays.
  perform public.end_retry_route_trial('end-test', 'samples', '2026-11-06T03:00:00Z');
  perform public.end_retry_route_trial('end-test', 'deadline', '2026-11-06T04:00:00Z');
  if (select (end_reason, ended_at) from public.retry_route_trials where trial = 'end-test')
      is distinct from ('samples'::text, '2026-11-06T03:00:00Z'::timestamptz) then
    raise exception 'a later end replaced the first';
  end if;
  begin
    perform public.reserve_retry_route_trial_attempt(gen_random_uuid(), 'invalid-test', now(), 26000000, 1, 25000000, 21600000, 180000);
    raise exception 'a reservation larger than the cap was taken';
  exception when invalid_parameter_value then null;
  end;
end;
$$;

reset role;
do $$
begin
  update public.retry_route_trials set end_reason = null, ended_at = null where trial = 'reserve-test';
  raise exception 'an ended trial was reopened by hand';
exception when check_violation then null;
end;
$$;

rollback;
