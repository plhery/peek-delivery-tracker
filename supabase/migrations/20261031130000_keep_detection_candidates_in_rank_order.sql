-- A support case keeps the detection candidates in the order detection ranked them, once each.
-- They went through select distinct, which dropped that order, so a case could list another
-- carrier ahead of the one detection ranked first.
create or replace function public.record_tracking_support_observation(
  p_tracking_number text,
  p_context jsonb,
  p_evidence jsonb,
  p_observed_at timestamptz,
  p_observation_key text
)
returns uuid language plpgsql security definer set search_path = pg_catalog as $$
declare
  number text := upper(regexp_replace(p_tracking_number, '[[:space:].-]', '', 'g'));
  context jsonb := coalesce(p_context, '{}');
  evidence jsonb := coalesce(p_evidence, '{}');
  reason_tags text[];
  candidate_ids text[];
  support_case public.tracking_support_cases;
  observation public.tracking_support_observations;
  inserted_count integer;
  completed boolean;
  provider text;
  source text;
  direct_progress boolean;
  settled boolean;
begin
  if number is null or length(number) not between 4 and 40 or (number !~ '[0-9]' and number !~ '^[A-Z]{6,}$')
      or number !~ '^([A-Z0-9]+|[0-9]{4}/[0-9]{8})$'
      or p_observed_at is null or not isfinite(p_observed_at)
      or p_observation_key is null or length(p_observation_key) not between 1 and 160
      or jsonb_typeof(context) <> 'object' or octet_length(context::text) > 4096
      or jsonb_typeof(evidence) <> 'object' or octet_length(evidence::text) > 4096 then
    raise exception 'Invalid tracking support observation' using errcode = '22023';
  end if;
  if (context ? 'reasons' and jsonb_typeof(context->'reasons') <> 'array')
      or (context ? 'detection_candidates' and jsonb_typeof(context->'detection_candidates') <> 'array') then
    raise exception 'Invalid tracking support context' using errcode = '22023';
  end if;
  reason_tags := array(
    select distinct tag from (
      select jsonb_array_elements_text(coalesce(context->'reasons', '[]')) as tag
      union all select nullif(evidence->>'support_gap_reason', '')
    ) as supplied where tag is not null order by tag
  );
  candidate_ids := array(select candidate from jsonb_array_elements_text(coalesce(context->'detection_candidates', '[]'))
    with ordinality as listed(candidate, position) group by candidate order by min(position));
  if cardinality(reason_tags) > 16 or exists(select 1 from unnest(reason_tags) as tag where tag !~ '^[a-z][a-z0-9_]{0,63}$')
      or cardinality(candidate_ids) > 64 or exists(select 1 from unnest(candidate_ids) as id where length(id) not between 1 and 100) then
    raise exception 'Invalid tracking support tags' using errcode = '22023';
  end if;
  completed := nullif(evidence->>'outcome', '') is not null and evidence->>'outcome' <> 'running';
  if completed and evidence->>'outcome' in ('superseded', 'abandoned', 'interrupted') then return null; end if;
  select * into support_case from public.tracking_support_cases where tracking_number = number for update;
  if not found then
    if cardinality(reason_tags) = 0 then return null; end if;
    insert into public.tracking_support_cases(tracking_number, first_seen, last_seen, held_until)
    values(number, p_observed_at, p_observed_at,
      case when context->>'browser_pending' = 'true' then p_observed_at + interval '1 hour' end)
    on conflict(tracking_number) do nothing;
    select * into strict support_case from public.tracking_support_cases where tracking_number = number for update;
  end if;
  insert into public.tracking_support_observations(observation_key, case_id, observed_at)
  values(p_observation_key, support_case.id, p_observed_at) on conflict(observation_key) do nothing;
  get diagnostics inserted_count = row_count;
  select * into strict observation from public.tracking_support_observations where observation_key = p_observation_key for update;
  if observation.case_id <> support_case.id then
    raise exception 'Tracking support observation key reused' using errcode = '22023';
  end if;
  if observation.completed then return support_case.id; end if;
  provider := nullif(left(evidence->>'support_provider', 100), '');
  source := nullif(left(evidence->>'source_carrier', 100), '');
  direct_progress := completed and evidence->>'outcome' in ('updated', 'unchanged')
    and evidence->>'support_direct_progress' = 'true' and provider is null
    and source is not null and source not in ('unknown', 'intl-post')
    and upper(regexp_replace(evidence->>'support_lookup_number', '[[:space:].-]', '', 'g')) = number;
  settled := direct_progress and evidence->>'support_settled' = 'true'
    and support_case.fix_status = 'open' and p_observed_at <= support_case.held_until;
  update public.tracking_support_cases set
    first_seen = least(first_seen, p_observed_at),
    last_seen = greatest(last_seen, p_observed_at),
    configured_carrier = case when p_observed_at >= last_seen and context ? 'configured_carrier' then nullif(left(context->>'configured_carrier', 100), '') else configured_carrier end,
    detection_carrier = case when p_observed_at >= last_seen and context ? 'detection_carrier' then nullif(left(context->>'detection_carrier', 100), '') else detection_carrier end,
    detection_confidence = case when p_observed_at >= last_seen and context ? 'detection_confidence' then nullif(context->>'detection_confidence', '') else detection_confidence end,
    detection_candidates = case when p_observed_at >= last_seen and context ? 'detection_candidates' then candidate_ids else detection_candidates end,
    app_version = case when p_observed_at >= last_seen and context ? 'app_version' then nullif(left(context->>'app_version', 100), '') else app_version end,
    reasons = array(select distinct tag from unnest(case
      when p_observation_key like 'attempt:%' and context ? 'reasons' and p_observed_at >= last_seen
        then array(select tag from unnest(reasons) as tag where tag <> all(array['ambiguous_shape', 'unknown_shape',
          'generic_postal', 'no_direct_adapter', 'carrier_mismatch'])) || reason_tags
      else reasons || reason_tags end) as tag order by tag),
    seen_count = seen_count + inserted_count,
    fallback_count = fallback_count + case when completed and provider is not null then 1 else 0 end,
    last_provider = case when completed and p_observed_at >= last_seen then provider else last_provider end,
    last_source_carrier = case when completed and p_observed_at >= last_seen then source else last_source_carrier end,
    last_outcome = case when completed and p_observed_at >= last_seen then nullif(left(evidence->>'outcome', 100), '') else last_outcome end,
    last_error_type = case when completed and p_observed_at >= last_seen then nullif(left(evidence->>'error_type', 100), '') else last_error_type end,
    direct_verified_at = case when direct_progress then greatest(direct_verified_at, p_observed_at) else direct_verified_at end,
    direct_verified_carrier = case when direct_progress and (direct_verified_at is null or p_observed_at >= direct_verified_at) then source else direct_verified_carrier end,
    fix_status = case when direct_progress and fix_status = 'fixed' and p_observed_at >= fixed_at then 'verified'
      when settled then 'ignored' else fix_status end,
    notes = case when settled then format('%s, which recognition asks only through a browser, if at all, tracked the number '
      'within the hour it was held for: no gap left.', source) else notes end
  where id = support_case.id;
  if completed then
    update public.tracking_support_observations set completed = true where observation_key = p_observation_key;
  end if;
  return support_case.id;
end;
$$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261031130000_keep_detection_candidates_in_rank_order') on conflict do nothing;
