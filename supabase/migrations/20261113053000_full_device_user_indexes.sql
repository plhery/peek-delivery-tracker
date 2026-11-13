-- Deleting an account deletes its push subscriptions and devices, disabled ones too, by user_id. An
-- index that leaves the disabled rows out cannot find them, so each delete read the whole table: these
-- index every row (supabase/tests/reference_indexes.sql).
drop index if exists public.push_subscriptions_user_idx;
create index push_subscriptions_user_idx on public.push_subscriptions (user_id);
drop index if exists public.native_push_devices_user_idx;
create index native_push_devices_user_idx on public.native_push_devices (user_id);
create index if not exists live_activity_devices_user_idx on public.live_activity_devices (user_id);

insert into public.applied_migrations (name) values ('20261113053000_full_device_user_indexes') on conflict do nothing;
