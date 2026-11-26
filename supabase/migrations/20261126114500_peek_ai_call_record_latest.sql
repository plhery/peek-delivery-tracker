-- Peek AI proves a call on its latest cases, not on a span of days (`peek_ai_call_record`, see
-- 20261126100000_peek_ai_call_cases): how the call turned out on the latest `p_cases` cases that can be scored, among
-- those whose time ended in the last `p_days` days. A call that comes up on few parcels might never gather 30 within a
-- few months; counting the latest lets it be made once it has, and still go quiet when it stops holding on the newest.
--
-- Apply this before deploying the server that uses it. The server before it keeps working: it names the days and the
-- reason only, and gets the latest 30 cases of those days.

drop function public.peek_ai_call_record(text, integer);

create function public.peek_ai_call_record(p_why text, p_cases integer default 30, p_days integer default 365)
returns jsonb language sql stable set search_path = pg_catalog as $$
  with scored as (
    select called.package_id, called.holds_until, called.noted_at,
      exists (
        select 1 from public.tracking_events as event
        where event.package_id = called.package_id
          and event.stage in ('delivered', 'ready_for_pickup', 'failed_attempt')
          and event.occurred_at < called.holds_until
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

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261126114500_peek_ai_call_record_latest') on conflict do nothing;
