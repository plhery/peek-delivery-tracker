\set ON_ERROR_STOP on
begin;
-- One server at a time holds a lease; it runs out on the database clock.
delete from public.worker_leases;

do $$
begin
  if has_function_privilege('authenticated', 'public.claim_worker_lease(text,text,integer)', 'execute')
    or has_function_privilege('anon', 'public.renew_worker_lease(text,text,integer)', 'execute')
    or has_function_privilege('authenticated', 'public.release_worker_lease(text,text)', 'execute')
    or has_table_privilege('authenticated', 'public.worker_leases', 'select')
    or has_table_privilege('service_role', 'public.worker_leases', 'update') then
    raise exception 'worker leases are open beyond their functions';
  end if;

  if not public.claim_worker_lease('push_dispatch', 'server-a', 60) then raise exception 'a free lease was refused'; end if;
  if public.claim_worker_lease('push_dispatch', 'server-b', 60) then raise exception 'another server took a held lease'; end if;
  if not public.claim_worker_lease('push_dispatch', 'server-a', 60) then raise exception 'its holder could not take it again'; end if;
  if public.renew_worker_lease('push_dispatch', 'server-b', 60) then raise exception 'another server renewed the lease'; end if;
  if not public.renew_worker_lease('push_dispatch', 'server-a', 60) then raise exception 'its holder could not renew it'; end if;
  if public.release_worker_lease('push_dispatch', 'server-b') then raise exception 'another server gave the lease up'; end if;
  -- Each name is a lease of its own.
  if not public.claim_worker_lease('other_work', 'server-b', 60) then raise exception 'one lease held back another'; end if;

  -- A holder that crashed loses the lease at its deadline, and cannot renew it after.
  update public.worker_leases set lease_until = clock_timestamp() - interval '1 second' where name = 'push_dispatch';
  if public.renew_worker_lease('push_dispatch', 'server-a', 60) then raise exception 'a lease that ran out was renewed'; end if;
  if not public.claim_worker_lease('push_dispatch', 'server-b', 60) then raise exception 'a lease that ran out was not taken over'; end if;
  if public.release_worker_lease('push_dispatch', 'server-a') then raise exception 'the former holder freed its successor''s lease'; end if;
  if (select owner from public.worker_leases where name = 'push_dispatch') <> 'server-b'
    or (select lease_until from public.worker_leases where name = 'push_dispatch') <= clock_timestamp() + interval '50 seconds' then
    raise exception 'the lease taken over is not its new holder''s';
  end if;

  if not public.release_worker_lease('push_dispatch', 'server-b') then raise exception 'its holder could not give it up'; end if;
  if not public.claim_worker_lease('push_dispatch', 'server-a', 60) then raise exception 'a lease given up was refused'; end if;

  begin
    perform public.claim_worker_lease('push_dispatch', 'server-a', 0);
    raise exception 'a lease without a duration was taken';
  exception when invalid_parameter_value then null;
  end;
  begin
    perform public.claim_worker_lease('Push dispatch', 'server-a', 60);
    raise exception 'a lease with a malformed name was taken';
  exception when invalid_parameter_value then null;
  end;
end;
$$;
rollback;
