-- A check's row names the carriers and providers its lookups asked, with how
-- each lookup ended (`success`, `not_found`, `error` and the like), so a check
-- that ended refused or waiting still shows whom it asked. Null for checks
-- recorded before the column and for those the server running during the
-- rollout completes; an empty object when a check asked nobody.

begin;

alter table public.tracking_sync_attempts add column lookups jsonb
  constraint tracking_sync_attempts_lookups_check check (jsonb_typeof(lookups) = 'object' and octet_length(lookups::text) <= 8192);
comment on column public.tracking_sync_attempts.lookups is
  'Carrier or provider asked by the check''s lookups, mapped to how its lookup ended. Null for checks recorded before the column.';

-- Also stores the lookups; a payload without them leaves the column null.
create or replace function public.complete_tracking_sync_attempt(
  p_attempt_id uuid,
  p_values jsonb,
  p_steps jsonb
)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
declare
  updated_count integer;
begin
  if jsonb_typeof(p_values) <> 'object' or jsonb_typeof(p_steps) <> 'array' then
    raise exception 'Invalid tracking sync audit payload' using errcode = '22023';
  end if;

  insert into public.tracking_sync_steps (
    attempt_id,
    sequence,
    step,
    status,
    occurred_at,
    duration_ms,
    details,
    error_type
  )
  select
    p_attempt_id,
    row.sequence,
    row.step,
    row.status,
    coalesce(row.occurred_at, now()),
    row.duration_ms,
    coalesce(row.details, '{}'::jsonb),
    row.error_type
  from jsonb_to_recordset(p_steps) as row(
    sequence smallint,
    step text,
    status text,
    occurred_at timestamptz,
    duration_ms integer,
    details jsonb,
    error_type text
  )
  on conflict (attempt_id, sequence) do update set
    step = excluded.step,
    status = excluded.status,
    occurred_at = excluded.occurred_at,
    duration_ms = excluded.duration_ms,
    details = excluded.details,
    error_type = excluded.error_type;

  update public.tracking_sync_attempts
  set
    source_carrier = case
      when p_values ? 'source_carrier' then nullif(p_values ->> 'source_carrier', '')
      else source_carrier
    end,
    outcome = p_values ->> 'outcome',
    current_step = 'complete',
    provider_status = nullif(p_values ->> 'provider_status', ''),
    reported_stage = nullif(p_values ->> 'reported_stage', ''),
    selected_stage = nullif(p_values ->> 'selected_stage', ''),
    status_text = nullif(left(p_values ->> 'status_text', 500), ''),
    events_received = coalesce((p_values ->> 'events_received')::integer, 0),
    events_normalized = coalesce((p_values ->> 'events_normalized')::integer, 0),
    events_new = (p_values ->> 'events_new')::integer,
    anomaly_codes = coalesce(
      array(select jsonb_array_elements_text(p_values -> 'anomaly_codes')),
      '{}'::text[]
    ),
    error_type = nullif(p_values ->> 'error_type', ''),
    completed_at = coalesce((p_values ->> 'completed_at')::timestamptz, now()),
    duration_ms = (p_values ->> 'duration_ms')::integer,
    lookups = p_values -> 'lookups'
  where id = p_attempt_id
    and outcome = 'running';

  get diagnostics updated_count = row_count;
  return updated_count = 1;
end;
$$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261026180000_tracking_sync_attempt_lookups') on conflict do nothing;

commit;
