-- A shop's tracking-page link that names no parcel yet ("waiting for H&M to ship"), kept for its owner while the
-- server asks again every few hours, for up to 14 days. The link opens a page with the reader's name and address, so
-- only the server reads this table: clients see a wait through the API, without its link. The link goes as soon as
-- the wait turns into a parcel (the row is deleted) or runs out (the row keeps its shop and name, to say so).
create table public.shop_waits (
  id uuid primary key default gen_random_uuid(),
  user_id uuid not null references auth.users (id) on delete cascade,
  platform text not null check (platform in ('parcellab')),
  shop text check (char_length(shop) between 1 and 80),
  link text check (char_length(link) between 1 and 2048),
  label text not null default '' check (char_length(label) <= 80),
  created_at timestamptz not null default now(),
  next_check_at timestamptz not null default now(),
  checks integer not null default 0 check (checks >= 0),
  last_reason text check (last_reason in ('not_found', 'no_tracking_yet', 'refused', 'rate_limited', 'unavailable')),
  expires_at timestamptz not null default now() + interval '14 days',
  expired_at timestamptz,
  constraint shop_waits_link_until_expired check ((expired_at is null) = (link is not null))
);

-- One wait per link and account.
create unique index shop_waits_user_link_key on public.shop_waits (user_id, md5(link)) where link is not null;
create index shop_waits_due_idx on public.shop_waits (next_check_at) where expired_at is null;
create index shop_waits_user_idx on public.shop_waits (user_id, created_at);

alter table public.shop_waits enable row level security;
revoke all on table public.shop_waits from public, anon, authenticated;
grant all on table public.shop_waits to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261126010000_shop_waits') on conflict do nothing;
