-- A number shape no carrier claims can come back on parcel after parcel without the review seeing
-- it: a one-off lookup's support case (`unknown_shape`) is closed as ignored once the lookup leaves
-- the schedule unanswered. Once three numbers of one shape, each on a parcel, have such a case first
-- seen in the last 14 days, each number is kept as a routing signal, `repeated_unknown_shape`, whose
-- subject is the shape as reviews show it, so the carriers' review lists the shape once, with the
-- parcels' short ids.
alter table public.tracking_routing_signals drop constraint tracking_routing_signals_signal_check;
alter table public.tracking_routing_signals add constraint tracking_routing_signals_signal_check check (signal in (
  'carrier_coverage_discovered', 'carrier_auto_swapped', 'carrier_mismatch_confirmed', 'direct_support_opportunity',
  'unread_delivery_estimate', 'repeated_unknown_shape'));

-- The shape numbers share to count as one: each digit run by its length, each letter run as it is
-- ("ABCDEF + 16"). Finer than `tracking_number_pattern`, which counts runs of four letters or more, and
-- which is what reviews show.
create function private.tracking_number_shape(p_number text)
returns text language sql immutable parallel safe set search_path = pg_catalog as $$
  select coalesce(string_agg(case when m.parts[1] ~ '^[0-9]' then length(m.parts[1])::text else m.parts[1] end,
      ' + ' order by m.n), '')
  from regexp_matches(upper(regexp_replace(coalesce(p_number, ''), '[^A-Za-z0-9]', '', 'g')), '[0-9]+|[A-Z]+', 'g')
    with ordinality as m(parts, n);
$$;

-- Raises the signal for the number's shape once three numbers of it count: each with a support case
-- that gives `unknown_shape`, is no repeat of another case (`duplicate_of`), is still open or ignored, was first
-- seen in the last 14 days, and with a parcel. A number already kept adds nothing; one added marks the
-- shape's unreviewed rows seen again, so the review lists the shape with all its parcels. Answers how
-- many numbers it added.
create function private.raise_repeated_unknown_shape(p_number text)
returns integer language plpgsql security definer set search_path = pg_catalog as $$
declare
  shape text := private.tracking_number_shape(p_number);
  numbers text[];
  parcels uuid[];
  added integer;
begin
  if shape = '' then
    return 0;
  end if;
  select array_agg(c.tracking_number order by c.tracking_number), array_agg(p.id order by c.tracking_number)
    into numbers, parcels
  from (
    select distinct s.tracking_number
    from public.tracking_support_cases as s
    where 'unknown_shape' = any(s.reasons) and s.duplicate_of is null and s.fix_status not in ('fixed', 'verified')
      and s.first_seen > now() - interval '14 days' and length(s.tracking_number) between 1 and 64
      and private.tracking_number_shape(s.tracking_number) = shape
  ) as c
  cross join lateral (
    select k.id from public.packages as k where k.tracking_number = c.tracking_number order by k.created_at desc limit 1
  ) as p;
  if coalesce(cardinality(numbers), 0) < 3 then
    return 0;
  end if;
  insert into public.tracking_routing_signals (signal, carrier, subject, tracking_number, package_id)
  select 'repeated_unknown_shape', 'unknown', left(private.tracking_number_pattern(n.number), 100), n.number, n.parcel
  from unnest(numbers, parcels) as n(number, parcel)
  on conflict (signal, carrier, subject, tracking_number) do nothing;
  get diagnostics added = row_count;
  if added > 0 then
    update public.tracking_routing_signals set last_seen = now()
    where signal = 'repeated_unknown_shape' and tracking_number = any(numbers) and reviewed_at is null;
  end if;
  return added;
end;
$$;
revoke all on function private.raise_repeated_unknown_shape(text) from public, anon, authenticated;

-- Each write of a case that gives `unknown_shape` raises its shape's signal. A failure there leaves
-- the case written.
create function private.raise_repeated_unknown_shape_for_case()
returns trigger language plpgsql security definer set search_path = pg_catalog as $$
begin
  if 'unknown_shape' = any(NEW.reasons) and NEW.duplicate_of is null then
    begin
      perform private.raise_repeated_unknown_shape(NEW.tracking_number);
    exception when others then
      raise warning 'repeated_unknown_shape not raised: %', sqlstate;
    end;
  end if;
  return null;
end;
$$;
revoke all on function private.raise_repeated_unknown_shape_for_case() from public, anon, authenticated;
create trigger tracking_support_cases_raise_repeated_shape
  after insert or update of reasons, duplicate_of on public.tracking_support_cases
  for each row execute function private.raise_repeated_unknown_shape_for_case();

-- The shapes that already repeat.
do $$
begin
  perform private.raise_repeated_unknown_shape(c.tracking_number)
  from (
    select distinct on (private.tracking_number_shape(tracking_number)) tracking_number
    from public.tracking_support_cases
    where 'unknown_shape' = any(reasons) and duplicate_of is null and fix_status not in ('fixed', 'verified')
      and first_seen > now() - interval '14 days'
  ) as c;
end;
$$;

insert into public.applied_migrations (name) values ('20261115063000_repeated_unknown_shape_signal') on conflict do nothing;
