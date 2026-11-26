\set ON_ERROR_STOP on
begin;

-- Only the server reads Peek AI's cases: they are production parcels.
do $$
begin
  if has_table_privilege('anon', 'public.peek_ai_call_cases', 'SELECT')
      or has_table_privilege('authenticated', 'public.peek_ai_call_cases', 'SELECT')
      or has_function_privilege('authenticated', 'public.peek_ai_call_record(text, integer)', 'EXECUTE') then
    raise exception 'public database roles can read Peek AI''s cases';
  end if;
  if not has_table_privilege('service_role', 'public.peek_ai_call_cases', 'INSERT') then
    raise exception 'the service role cannot note Peek AI''s cases';
  end if;
end;
$$;

-- A case is known once the parcel arrived or was checked after the call stopped holding, and right when it arrived by then.
do $$
declare
  arrived public.packages;
  late public.packages;
  waiting public.packages;
  unchecked public.packages;
  recent public.packages;
  scored jsonb;
begin
  insert into public.packages(user_id, tracking_number, carrier) values ('10000000-0000-0000-0000-000000000001', 'EXAMPLE0000000011', 'swiss-post') returning * into arrived;
  insert into public.packages(user_id, tracking_number, carrier, last_synced_at) values ('10000000-0000-0000-0000-000000000001', 'EXAMPLE0000000012', 'swiss-post', now()) returning * into late;
  insert into public.packages(user_id, tracking_number, carrier, last_synced_at) values ('10000000-0000-0000-0000-000000000001', 'EXAMPLE0000000013', 'swiss-post', now()) returning * into waiting;
  insert into public.packages(user_id, tracking_number, carrier, last_synced_at) values ('10000000-0000-0000-0000-000000000001', 'EXAMPLE0000000014', 'swiss-post', now() - interval '3 days') returning * into unchecked;
  insert into public.packages(user_id, tracking_number, carrier) values ('10000000-0000-0000-0000-000000000001', 'EXAMPLE0000000015', 'swiss-post') returning * into recent;
  insert into public.peek_ai_call_cases(package_id, why, carrier_day, holds_until) values
    (arrived.id, 'missed_date', current_date - 4, now() - interval '2 days'),
    (late.id, 'missed_date', current_date - 4, now() - interval '2 days'),
    (waiting.id, 'missed_date', current_date - 4, now() - interval '2 days'),
    (unchecked.id, 'missed_date', current_date - 4, now() - interval '2 days'),
    (recent.id, 'missed_date', current_date, now() + interval '1 day');
  insert into public.tracking_events(package_id, provider_event_id, stage, description, occurred_at) values
    (arrived.id, 'gls:arrived', 'delivered', 'Delivered', now() - interval '2 days 1 hour'),
    (late.id, 'gls:late', 'delivered', 'Delivered', now() - interval '1 day'),
    (recent.id, 'gls:recent', 'delivered', 'Delivered', now() - interval '1 hour');
  -- arrived: right; late: arrived after, wrong; waiting: checked since, wrong; unchecked: unknown; recent: still holds.
  scored := public.peek_ai_call_record('missed_date');
  if scored <> '{"cases": 3, "right": 1}'::jsonb then
    raise exception 'Peek AI''s missed-date cases scored as %', scored;
  end if;
  update public.peek_ai_call_cases set noted_at = now() - interval '100 days' where package_id = arrived.id;
  if public.peek_ai_call_record('missed_date') <> '{"cases": 2, "right": 0}'::jsonb then
    raise exception 'Peek AI scored a case noted before its window';
  end if;
  begin
    insert into public.peek_ai_call_cases(package_id, why, carrier_day, holds_until) values (arrived.id, 'other', current_date, now());
    raise exception 'Peek AI noted a case of a call it does not prove';
  exception when check_violation then null;
  end;
end;
$$;

rollback;
