-- The Android app asks whether a parcel is right, as the iPhone app does, and its answers
-- say they come from `android`.
--
-- Apply this before deploying the server that accepts them. The server before it keeps
-- working on this schema: it still refuses an answer from any app but the web and iOS.

alter table public.parcel_feedback
  drop constraint parcel_feedback_app_check,
  add constraint parcel_feedback_app_check check (app in ('web', 'ios', 'android'));

insert into public.applied_migrations (name) values ('20261103160000_android_parcel_feedback') on conflict do nothing;
