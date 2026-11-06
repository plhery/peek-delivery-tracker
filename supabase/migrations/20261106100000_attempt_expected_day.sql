-- The expected day a check found on the parcel, beside the stage it found: the review tells a day that passed
-- from one an answer dropped while it was still ahead (ops/review/signals.sql). Checks before this column have none.
alter table public.tracking_sync_attempts
  add column if not exists previous_expected_delivery text,
  add constraint tracking_sync_attempts_previous_expected_delivery_check check (
    previous_expected_delivery is null or length(previous_expected_delivery) between 1 and 100
  );

-- Preserve the lease fence while recording the same context as manual checks.
create or replace function public.start_leased_sync_attempt(p_attempt_id uuid, p_job_id uuid, p_worker_id text, p_values jsonb)
returns boolean language plpgsql set search_path = pg_catalog as $$
begin
  perform 1 from public.sync_jobs where id = p_job_id and state = 'running' and locked_by = p_worker_id
    and lease_until > clock_timestamp()
    and (kind = 'scheduled' or package_id = (p_values->>'package_id')::uuid) for share;
  if not found then return false; end if;
  insert into public.tracking_sync_attempts(id, job_id, package_id, trigger, configured_carrier, previous_stage,
    previous_expected_delivery, started_at, support_context)
  values(p_attempt_id, p_job_id, (p_values->>'package_id')::uuid, p_values->>'trigger',
    p_values->>'configured_carrier', p_values->>'previous_stage', nullif(p_values->>'previous_expected_delivery', ''),
    (p_values->>'started_at')::timestamptz, nullif(p_values->'support_context', 'null'::jsonb));
  return true;
end;
$$;
revoke all on function public.start_leased_sync_attempt(uuid,uuid,text,jsonb) from public, anon, authenticated;
grant execute on function public.start_leased_sync_attempt(uuid,uuid,text,jsonb) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261106100000_attempt_expected_day') on conflict do nothing;
