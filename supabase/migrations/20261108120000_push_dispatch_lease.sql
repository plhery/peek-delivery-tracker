-- One server at a time sends the push notifications.
--
-- A dispatch lists every scan waiting for a browser, a phone, a Live Activity
-- or a link alert, sends it, then records it as sent. Two servers that
-- dispatch at once, as the old and new containers of a deploy do, list the
-- same scans and both send them. A dispatch now holds a lease first: a named
-- row with its owner and a deadline on the database clock. The owner renews it
-- while it sends and gives it up at the end; one that crashed loses it at the
-- deadline. A server that cannot take it skips that dispatch, and the scans
-- wait for the next one.
--
-- Apply this before deploying the server that takes the lease. The server
-- before it never reads the table.

begin;

create table public.worker_leases (
  name text primary key check (name ~ '^[a-z_]{1,40}$'),
  owner text not null check (length(owner) between 1 and 200),
  claimed_at timestamptz not null,
  lease_until timestamptz not null
);
alter table public.worker_leases enable row level security;
-- Only the functions below write leases.
revoke all on public.worker_leases from public, anon, authenticated, service_role;
grant select on public.worker_leases to service_role;

comment on table public.worker_leases is
  'Work one server does at a time, such as the push dispatch: which server holds it, and until when on the database clock. A row goes when its holder gives it up. Service role only.';

-- Takes the lease for p_seconds, or extends it for the owner that holds it.
-- False while another owner holds it.
create function public.claim_worker_lease(p_name text, p_owner text, p_seconds integer)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
begin
  if p_name is null or p_name !~ '^[a-z_]{1,40}$'
      or p_owner is null or length(p_owner) not between 1 and 200
      or p_seconds is null or p_seconds not between 5 and 3600 then
    raise exception 'Invalid worker lease' using errcode = '22023';
  end if;
  insert into public.worker_leases as held (name, owner, claimed_at, lease_until)
  values (p_name, p_owner, clock_timestamp(), clock_timestamp() + make_interval(secs => p_seconds))
  on conflict (name) do update set
    owner = excluded.owner, claimed_at = excluded.claimed_at, lease_until = excluded.lease_until
    where held.owner = excluded.owner or held.lease_until <= clock_timestamp();
  return found;
end;
$$;

-- Extends a lease its owner still holds. False once it ran out or another
-- owner took it: the holder then stops.
create function public.renew_worker_lease(p_name text, p_owner text, p_seconds integer)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
begin
  if p_seconds is null or p_seconds not between 5 and 3600 then
    raise exception 'Invalid worker lease' using errcode = '22023';
  end if;
  update public.worker_leases
  set lease_until = clock_timestamp() + make_interval(secs => p_seconds)
  where name = p_name and owner = p_owner and lease_until > clock_timestamp();
  return found;
end;
$$;

-- Gives a lease up. Only its owner can: a late old server never frees the
-- lease another one took over.
create function public.release_worker_lease(p_name text, p_owner text)
returns boolean
language plpgsql
security definer
set search_path = ''
as $$
begin
  delete from public.worker_leases where name = p_name and owner = p_owner;
  return found;
end;
$$;

revoke all on function public.claim_worker_lease(text, text, integer), public.renew_worker_lease(text, text, integer),
  public.release_worker_lease(text, text) from public, anon, authenticated;
grant execute on function public.claim_worker_lease(text, text, integer), public.renew_worker_lease(text, text, integer),
  public.release_worker_lease(text, text) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261108120000_push_dispatch_lease') on conflict do nothing;

commit;
