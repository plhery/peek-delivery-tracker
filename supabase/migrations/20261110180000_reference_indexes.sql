-- Every reference is found by index.
--
-- Deleting a row reads the rows that point at it. Without an index on the pointing column, each
-- deleted scan read every notification receipt, routing signal and status sample, each deleted
-- parcel every Live Activity row and routing signal, and each ended session every Live Activity
-- device. A parcel's deletion, a merge or a replay deletes scans many at a time.

begin;

create index friendship_push_deliveries_subscription_idx on public.friendship_push_deliveries (subscription_id);
create index friendship_push_deliveries_device_idx on public.friendship_push_deliveries (device_id);
create index friendship_updates_friend_user_idx on public.friendship_updates (friend_user_id);
create index friendship_updates_connection_idx on public.friendship_updates (user_low, user_high);
create index live_activity_devices_session_idx on public.live_activity_devices (session_id);
create index live_activity_event_deliveries_event_idx on public.live_activity_event_deliveries (event_id);
create index live_activity_event_deliveries_parcel_idx on public.live_activity_event_deliveries (package_id);
create index live_activity_update_tokens_package_idx on public.live_activity_update_tokens (package_id);
create index native_push_deliveries_event_idx on public.native_push_deliveries (event_id);
create index push_deliveries_event_idx on public.push_deliveries (event_id);
create index tracking_routing_signals_package_idx on public.tracking_routing_signals (package_id);
create index tracking_status_observations_sample_event_idx on public.tracking_status_observations (sample_event_id);

insert into public.applied_migrations (name) values ('20261110180000_reference_indexes') on conflict do nothing;

commit;
