-- Keep browser, APNs, Live Activity and shared-link subscriptions in Brazilian Portuguese, Russian and Chinese too.
-- Apply before deploying clients that can register pt-br, ru or zh subscriptions.
alter table public.push_subscriptions
  drop constraint push_subscriptions_locale_check,
  add constraint push_subscriptions_locale_check
    check (locale in ('en', 'de', 'fr', 'it', 'es', 'pt', 'pt-br', 'pl', 'ru', 'zh'));
alter table public.native_push_devices
  drop constraint native_push_devices_locale_check,
  add constraint native_push_devices_locale_check
    check (locale in ('en', 'de', 'fr', 'it', 'es', 'pt', 'pt-br', 'pl', 'ru', 'zh'));
alter table public.live_activity_devices
  drop constraint live_activity_devices_locale_check,
  add constraint live_activity_devices_locale_check
    check (locale in ('en', 'de', 'fr', 'it', 'es', 'pt', 'pt-br', 'pl', 'ru', 'zh'));
alter table public.live_activity_update_tokens
  drop constraint live_activity_update_tokens_locale_check,
  add constraint live_activity_update_tokens_locale_check
    check (locale in ('en', 'de', 'fr', 'it', 'es', 'pt', 'pt-br', 'pl', 'ru', 'zh'));
alter table public.parcel_link_alerts
  drop constraint parcel_link_alerts_locale_check,
  add constraint parcel_link_alerts_locale_check
    check (locale in ('en', 'de', 'fr', 'it', 'es', 'pt', 'pt-br', 'pl', 'ru', 'zh'));
notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261030163000_add_pt_br_ru_zh_locales') on conflict do nothing;
