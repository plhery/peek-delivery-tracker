-- The scraper adapters' pauses and pacing, kept across deploys.
--
-- An adapter holds off a request for a while: the direct step's rest after a check only a
-- browser passes (MailAmericas, Correios, Australia Post, HuaHan), USPS's outage pause,
-- Correios's read window, a day without asking Cainiao about an iMile number. The scraper
-- kept them in the process, so each deploy forgot them and asked again. The server now saves
-- each one as it starts and takes up those still running at start. A key is the hold's kind
-- and what it holds: a carrier and a hash of the browser service's address, or, for iMile, a
-- tracking number, which stays with the parcel data, out of logs. The server deletes the
-- holds that ended at each start.
--
-- Apply this before deploying the server that uses it. The server before it keeps working
-- on this schema: it does not use the table.


create table public.scraper_holds (
  key text primary key check (char_length(key) between 3 and 512 and key ~ '^[a-z][a-z0-9-]*/'),
  until timestamptz not null,
  value jsonb
);
create index scraper_holds_until_idx on public.scraper_holds(until);
alter table public.scraper_holds enable row level security;
revoke all on table public.scraper_holds from public, anon, authenticated;
grant select, insert, update, delete on table public.scraper_holds to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261119143000_scraper_holds') on conflict do nothing;

