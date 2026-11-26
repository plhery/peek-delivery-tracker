-- How long a UnifiedPush device whose distributor refused sends waits before the next (push.ts): the refusals in a row
-- and when the next send may go. Kept on the device so that a new server process waits as the last one did, and so
-- that the refusals that put a device away count across deploys. A send that goes through, or the device registering
-- again, starts them over.
--
-- Apply this before deploying the server that uses it. The server before it keeps working: it does not read them.

alter table public.native_push_devices
  add column retry_failures integer not null default 0 check (retry_failures >= 0),
  add column retry_at timestamptz;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261126113000_native_push_retries') on conflict do nothing;
