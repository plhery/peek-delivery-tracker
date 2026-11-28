-- Peek AI's missed-date call counts as right only on an arrival it had not seen when it was made
-- (`peek_ai_call_record`, see 20261126114500_peek_ai_call_record_latest). The call is made on a parcel whose newest scan
-- is not an arrival, but an older one can come first: a delivery tried on the carrier's day, then back at the depot.
-- That attempt counted the case right straight away. An arrival now counts unless a carrier's scan of the parcel on its
-- way (accepted, in transit, customs, out for delivery, an exception: Peek AI's own stages, not the app's pending row)
-- followed it before the case was noted. One that came after the note, or before it unread by the server, counts.
--
-- Apply this before or after the server: it keeps the function's arguments and answer.

create or replace function public.peek_ai_call_record(p_why text, p_cases integer default 30, p_days integer default 365)
returns jsonb language sql stable set search_path = pg_catalog as $$
  with scored as (
    select called.package_id, called.holds_until, called.noted_at,
      exists (
        select 1 from public.tracking_events as event
        where event.package_id = called.package_id
          and event.stage in ('delivered', 'ready_for_pickup', 'failed_attempt')
          and event.occurred_at < called.holds_until
          and not exists (
            select 1 from public.tracking_events as later
            where later.package_id = called.package_id
              and later.stage in ('accepted', 'in_transit', 'customs', 'out_for_delivery', 'exception')
              and later.occurred_at > event.occurred_at
              and later.occurred_at <= called.noted_at
          )
      ) as arrived,
      coalesce(parcel.last_synced_at >= called.holds_until, false) as checked
    from public.peek_ai_call_cases as called
    join public.packages as parcel on parcel.id = called.package_id
    where called.why = p_why and called.holds_until < now()
      and called.holds_until >= now() - make_interval(days => p_days)
  ), latest as (
    select arrived from scored
    where arrived or checked
    order by holds_until desc, noted_at desc, package_id
    limit p_cases
  )
  select jsonb_build_object('cases', count(*), 'right', count(*) filter (where arrived))
  from latest;
$$;
revoke all on function public.peek_ai_call_record(text, integer, integer) from public, anon, authenticated;
grant execute on function public.peek_ai_call_record(text, integer, integer) to service_role;

insert into public.applied_migrations (name) values ('20261128083000_peek_ai_call_record_after_call') on conflict do nothing;
