-- The sign-in an account parcel was added from with Add (the token's session_id), so that an add from the same
-- sign-in minutes later can replace the number half typed (src/server/halfTypedParcels.ts). Parcels added another way
-- have none. Only the server writes it, and the API never serves it.
alter table public.packages add column if not exists added_session uuid;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261124124000_package_added_session') on conflict do nothing;
