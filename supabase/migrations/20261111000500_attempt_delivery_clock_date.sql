-- The date a check began on, on the clock the parcel's expected day is read on (the zone the answer declares or the
-- catalog names for its carrier, else the offset its newest scan states), beside the day it found: the review tells a
-- day that passed from one dropped while it was still ahead without knowing the catalog's zones
-- (ops/review/signals.sql). Kept only with an expected day; checks before this column have none.
begin;

alter table public.tracking_sync_attempts
  add column if not exists delivery_clock_date date;

create or replace function public.start_leased_sync_attempt(p_attempt_id uuid, p_job_id uuid, p_worker_id text, p_values jsonb)
returns boolean language plpgsql set search_path = pg_catalog as $$
begin
  perform 1 from public.sync_jobs where id = p_job_id and state = 'running' and locked_by = p_worker_id
    and lease_until > clock_timestamp()
    and (kind = 'scheduled' or package_id = (p_values->>'package_id')::uuid) for share;
  if not found then return false; end if;
  insert into public.tracking_sync_attempts(id, job_id, package_id, trigger, configured_carrier, previous_stage,
    previous_expected_delivery, delivery_clock_date, started_at, support_context)
  values(p_attempt_id, p_job_id, (p_values->>'package_id')::uuid, p_values->>'trigger',
    p_values->>'configured_carrier', p_values->>'previous_stage', nullif(p_values->>'previous_expected_delivery', ''),
    nullif(p_values->>'delivery_clock_date', '')::date,
    (p_values->>'started_at')::timestamptz, nullif(p_values->'support_context', 'null'::jsonb));
  return true;
end;
$$;
revoke all on function public.start_leased_sync_attempt(uuid,uuid,text,jsonb) from public, anon, authenticated;
grant execute on function public.start_leased_sync_attempt(uuid,uuid,text,jsonb) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261111000500_attempt_delivery_clock_date') on conflict do nothing;

commit;
