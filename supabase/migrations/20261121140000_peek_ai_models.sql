-- Peek AI's models: what the server reads to say when it thinks a parcel will come (peekAi.ts).
--
-- A model is trained in private on the parcels Peek has followed, with its rule tables, and stored here as one
-- document under its version. The server reads the newest at start and every hour; with none stored, Peek AI says
-- nothing. Only the server reads them: they are learned from production data.
--
-- Apply this before deploying the server that uses it. The server before it keeps working on this schema: it does not
-- use the table.


create table public.peek_ai_models (
  version text primary key check (char_length(version) between 1 and 64),
  created_at timestamptz not null default now(),
  model jsonb not null check (jsonb_typeof(model) = 'object')
);
create index peek_ai_models_created_at_idx on public.peek_ai_models(created_at desc);
alter table public.peek_ai_models enable row level security;
revoke all on table public.peek_ai_models from public, anon, authenticated;
grant select on table public.peek_ai_models to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261121140000_peek_ai_models') on conflict do nothing;
