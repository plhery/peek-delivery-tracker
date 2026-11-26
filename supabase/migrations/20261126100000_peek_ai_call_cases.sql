-- The cases of a Peek AI call that waits to be proven on the server's own condition (peekAi.ts): the parcel, the
-- carrier's day it was made after, and when it stops holding. The server notes a case the first time it would make the
-- call, reads every hour how the cases whose time is over turned out (`peek_ai_call_record`), and makes the call only
-- once 30 of them are known and it was right at least 8 times in 10. A case is known once the parcel arrived, or was
-- checked after the call stopped holding; it was right when the parcel arrived (delivered, at a pickup point or a
-- delivery attempt) before then. Learned from production parcels: only the server reads them.
--
-- Apply this before deploying the server that uses it. The server before it keeps working: it does not use the table.

create table public.peek_ai_call_cases (
  package_id uuid not null references public.packages (id) on delete cascade,
  why text not null check (why in ('missed_date')),
  carrier_day date not null,
  holds_until timestamptz not null,
  noted_at timestamptz not null default now(),
  primary key (package_id, why, carrier_day)
);
create index peek_ai_call_cases_why_idx on public.peek_ai_call_cases (why, holds_until);

alter table public.peek_ai_call_cases enable row level security;
revoke all on table public.peek_ai_call_cases from public, anon, authenticated;
grant all on table public.peek_ai_call_cases to service_role;

-- How the call `p_why` turned out on the cases noted in the last `p_days` days: those known, and those right.
create function public.peek_ai_call_record(p_why text, p_days integer default 90)
returns jsonb language sql stable set search_path = pg_catalog as $$
  with scored as (
    select exists (
        select 1 from public.tracking_events as event
        where event.package_id = called.package_id
          and event.stage in ('delivered', 'ready_for_pickup', 'failed_attempt')
          and event.occurred_at < called.holds_until
      ) as arrived,
      coalesce(parcel.last_synced_at >= called.holds_until, false) as checked
    from public.peek_ai_call_cases as called
    join public.packages as parcel on parcel.id = called.package_id
    where called.why = p_why and called.holds_until < now()
      and called.noted_at >= now() - make_interval(days => p_days)
  )
  select jsonb_build_object('cases', count(*) filter (where arrived or checked), 'right', count(*) filter (where arrived))
  from scored;
$$;
revoke all on function public.peek_ai_call_record(text, integer) from public, anon, authenticated;
grant execute on function public.peek_ai_call_record(text, integer) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261126100000_peek_ai_call_cases') on conflict do nothing;
