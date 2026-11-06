-- A parcel's stage changed outside the API: a fix script, a migration or the stored-scan replay,
-- run over psql. The app writes through PostgREST, which logs in as authenticator; any other
-- login's change is kept here, so the hourly review (ops/review/signals.sql) tells a step back
-- set by hand from one a check made. A script names its fix with `set local peek.hand_fix = '<name>'`.
create table if not exists private.hand_stage_changes (
  id bigint generated always as identity primary key,
  package_id uuid not null references public.packages (id) on delete cascade,
  changed_at timestamptz not null default now(),
  from_stage text,
  to_stage text,
  fix text check (fix is null or length(fix) between 1 and 200)
);
create index if not exists hand_stage_changes_package on private.hand_stage_changes (package_id, changed_at);
alter table private.hand_stage_changes enable row level security;
revoke all on private.hand_stage_changes from public, anon, authenticated;
comment on table private.hand_stage_changes is
  'Stage changes of parcels made outside the API (fix scripts, migrations, the stored-scan replay), with the fix''s name.';

create or replace function private.record_hand_stage_change()
returns trigger
language plpgsql
security definer
set search_path = ''
as $$
begin
  if session_user = 'authenticator' then return null; end if;
  insert into private.hand_stage_changes (package_id, from_stage, to_stage, fix)
  values (new.id, old.current_stage, new.current_stage, left(nullif(current_setting('peek.hand_fix', true), ''), 200));
  return null;
end;
$$;
revoke all on function private.record_hand_stage_change() from public, anon, authenticated, service_role;

drop trigger if exists packages_record_hand_stage_change on public.packages;
create trigger packages_record_hand_stage_change
  after update of current_stage on public.packages
  for each row when (old.current_stage is distinct from new.current_stage)
  execute function private.record_hand_stage_change();

insert into public.applied_migrations (name) values ('20261106110000_hand_stage_changes') on conflict do nothing;
