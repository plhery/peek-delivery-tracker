-- The attempts of a bounded retry route trial, kept across deploys.
--
-- The scraper assigns a FedEx browser retry to the datacenter or the residential
-- worker while a trial named by FEDEX_RETRY_TRIAL runs, and saves each attempt
-- here when it starts and again when it ends. A restart reads them back, so the
-- trial's sample target, its residential byte cap and its deadline hold across
-- deploys. A row names the trial, the route, the times, the step outcome, whether
-- dated history came back and the tunnel bytes: no parcel, account, address or
-- proxy.
--
-- Apply this before deploying the server that uses it. The server before it
-- keeps working on this schema: it does not use the table.

begin;

create table public.retry_route_trial_attempts (
  id uuid primary key,
  trial text not null check (trial ~ '^[A-Za-z0-9_.:-]{1,64}$'),
  route text not null check (route in ('datacenter', 'residential')),
  assigned_at timestamptz not null,
  settled_at timestamptz,
  outcome text check (outcome ~ '^[a-z_]{1,32}$'),
  history boolean,
  bytes bigint check (bytes >= 0)
);
create index retry_route_trial_attempts_trial_idx on public.retry_route_trial_attempts(trial, assigned_at);
alter table public.retry_route_trial_attempts enable row level security;
revoke all on table public.retry_route_trial_attempts from public, anon, authenticated;
grant select, insert, update on table public.retry_route_trial_attempts to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261106000000_retry_route_trial_attempts') on conflict do nothing;

commit;
