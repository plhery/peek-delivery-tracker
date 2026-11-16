-- Readers' checks have a lane of their own beside the scheduled run: a lookup's first check, a link opened and a
-- refresh. The lane's worker claims only their jobs, in the queue's order, and one whose worker stopped. The other
-- worker still claims every job and keeps the queue's upkeep (claim_sync_job).
create or replace function public.claim_reader_sync_job(p_worker_id text, p_lease_seconds integer default 90)
returns setof public.sync_jobs language plpgsql security definer set search_path = '' as $$
declare
  claimed public.sync_jobs;
  claim_time timestamptz := clock_timestamp();
begin
  if nullif(btrim(p_worker_id), '') is null or p_lease_seconds not between 30 and 3600 then
    raise exception 'Invalid worker lease' using errcode = '22023';
  end if;
  with candidate as (
    select id from public.sync_jobs
    where kind = 'package' and reason in ('added', 'opened', 'refresh')
      and ((state = 'queued' and run_after <= claim_time)
        or (state = 'running' and lease_until < claim_time and attempts < 3))
    order by priority desc, run_after, requested_at for update skip locked limit 1
  )
  update public.sync_jobs j set state = 'running', started_at = coalesce(j.started_at, claim_time),
    completed_at = null, lease_until = claim_time + make_interval(secs => p_lease_seconds),
    locked_by = p_worker_id, attempts = j.attempts + 1, last_error = null
  from candidate where j.id = candidate.id returning j.* into claimed;
  if not found then return; end if;
  -- Finish the previous process's ledger immediately, not 30 minutes later.
  update public.tracking_sync_attempts set outcome = 'abandoned', current_step = 'complete',
    completed_at = clock_timestamp(), error_type = 'WorkerLeaseExpired',
    duration_ms = least(2147483647, greatest(0, extract(epoch from (clock_timestamp() - started_at)) * 1000))::integer
  where job_id = claimed.id and outcome = 'running';
  return next claimed;
end;
$$;

revoke all on function public.claim_reader_sync_job(text, integer) from public, anon, authenticated;
grant execute on function public.claim_reader_sync_job(text, integer) to service_role;

insert into public.applied_migrations (name) values ('20261116024500_reader_sync_lane') on conflict do nothing;
notify pgrst, 'reload schema';
