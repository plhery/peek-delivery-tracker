-- Rows that point at a sync job or a parcel are found by index.
--
-- Deleting a sync job sets job_id to null on the attempts it ran, and deleting a parcel
-- deletes its sync jobs. Neither column had an index, so each deleted job read every attempt
-- and each deleted parcel every job. An account's deletion runs both for each of its jobs and
-- parcels in one statement, which outlasted the Auth server's ten seconds: the deletion
-- failed and the account stayed. claim_sync_job also read every attempt for each finished
-- job it pruned.

begin;

create index tracking_sync_attempts_job_idx on public.tracking_sync_attempts (job_id);
create index sync_jobs_package_idx on public.sync_jobs (package_id);

insert into public.applied_migrations (name) values ('20261110170000_sync_job_reference_indexes') on conflict do nothing;

commit;
