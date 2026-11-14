-- Every observation names the check that last saw its wording. Wording no stored scan carries
-- (a scan on a local clock only, or one in an answer the check kept nothing of) has no sample
-- event, and its parcel and provider were lost; the check's attempt row keeps both. Like the
-- sample event, the attempt is an opaque join key, cleared when the attempt is purged.

begin;

alter table public.tracking_status_observations
  add column sample_attempt_id uuid references public.tracking_sync_attempts (id) on delete set null;
create index tracking_status_observations_sample_attempt_idx on public.tracking_status_observations (sample_attempt_id);
comment on column public.tracking_status_observations.sample_attempt_id is
  'The check that last saw the wording (its parcel and the provider that answered); cleared when the attempt is purged.';

-- As before, plus the attempt that saw the wording, when its row exists.
create or replace function public.record_tracking_status_observations(
  p_observations jsonb,
  p_mapped_keys text[] default '{}',
  p_version text default null
)
returns void
language plpgsql
security definer
set search_path = ''
as $$
declare
  version text := nullif(left(p_version, 100), '');
  observed_keys text[];
begin
  if jsonb_typeof(p_observations) <> 'array' or cardinality(p_mapped_keys) > 64 then
    raise exception 'Invalid tracking status observation payload' using errcode = '22023';
  end if;

  with recorded as (
    insert into public.tracking_status_observations as observed (
      observation_key,
      carrier,
      provider_code,
      description_normalized,
      language_guess,
      stage_source,
      chosen_stage,
      sample_event_id,
      sample_attempt_id,
      last_seen_version
    )
    select distinct on (observation.observation_key)
      observation.observation_key,
      left(observation.carrier, 100),
      nullif(left(observation.provider_code, 100), ''),
      left(observation.description_normalized, 500),
      nullif(left(observation.language_guess, 32), ''),
      left(observation.stage_source, 100),
      left(observation.chosen_stage, 100),
      (
        select event.id
        from public.tracking_events as event
        where event.package_id = observation.package_id
          and event.provider_event_id = observation.provider_event_id
      ),
      (
        select attempt.id
        from public.tracking_sync_attempts as attempt
        where attempt.id = observation.attempt_id
      ),
      version
    from jsonb_to_recordset(p_observations) as observation(
      observation_key text,
      carrier text,
      provider_code text,
      description_normalized text,
      language_guess text,
      stage_source text,
      chosen_stage text,
      package_id uuid,
      provider_event_id text,
      attempt_id uuid
    )
    where observation.observation_key is not null
      and nullif(observation.carrier, '') is not null
      and nullif(observation.description_normalized, '') is not null
      and nullif(observation.stage_source, '') is not null
      and nullif(observation.chosen_stage, '') is not null
    on conflict (observation_key) do update set
      count = observed.count + 1,
      last_seen = now(),
      stage_source = excluded.stage_source,
      chosen_stage = excluded.chosen_stage,
      sample_event_id = coalesce(excluded.sample_event_id, observed.sample_event_id),
      sample_attempt_id = coalesce(excluded.sample_attempt_id, observed.sample_attempt_id),
      last_seen_version = excluded.last_seen_version
    returning observed.observation_key
  )
  select coalesce(array_agg(observation_key), '{}') into observed_keys from recorded;

  if version is not null then
    update public.tracking_status_observations
    set reviewed_at = null, resolution = null, reviewed_version = null, review_note = null
    where observation_key = any(observed_keys)
      and (resolution = 'expired' or (resolution = 'mapped' and reviewed_version is not null));

    update public.tracking_status_observations
    set reviewed_at = now(), resolution = 'mapped', reviewed_version = version,
      review_note = 'The carrier status map gave its stage.'
    where observation_key = any(p_mapped_keys) and not observation_key = any(observed_keys)
      and reviewed_at is null;
  end if;

  perform public.close_intentionally_unmapped_observations(version, observed_keys);
end;
$$;

revoke all on function public.record_tracking_status_observations(jsonb, text[], text)
  from public, anon, authenticated;
grant execute on function public.record_tracking_status_observations(jsonb, text[], text) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261114055000_status_observation_attempts') on conflict do nothing;

commit;
