-- A number typed twice, once with a character more or less, is one support case.
--
-- A reader who mistypes a number and types it again leaves two numbers one character apart,
-- each opening a case. A case that opens within ten minutes of an open case whose number is
-- one character longer or shorter points at it (`duplicate_of`), and the review counts the
-- two once. A case has no owner and a lookup's link key is its own, so the ten minutes stand
-- in for the same person, as for the start of a saved number; when both numbers are saved
-- by accounts, they must be one account's. Numbers of one length a digit or two apart are
-- left apart: a batch of labels numbers its parcels that way.

begin;

alter table public.tracking_support_cases
  add column duplicate_of uuid references public.tracking_support_cases (id) on delete set null;
create index tracking_support_cases_duplicate_idx on public.tracking_support_cases (duplicate_of);

create function private.one_character_apart(a text, b text)
returns boolean
language sql
immutable
set search_path = pg_catalog
as $$
  select case
    when length(a) = length(b) + 1 then exists (select 1 from generate_series(1, length(a)) i where overlay(a placing '' from i for 1) = b)
    when length(b) = length(a) + 1 then exists (select 1 from generate_series(1, length(b)) i where overlay(b placing '' from i for 1) = a)
    else false end;
$$;

-- The open case a case opening at `seen` under `number` repeats, if any: the earliest.
create function private.support_case_repeated(number text, seen timestamptz, self uuid)
returns uuid
language sql
stable
set search_path = pg_catalog
as $$
  select c.id
  from public.tracking_support_cases c
  where length(number) >= 8 and c.id <> self and c.fix_status = 'open' and c.duplicate_of is null
    and c.first_seen >= seen - interval '10 minutes' and (c.first_seen, c.id) < (seen, self)
    and private.one_character_apart(c.tracking_number, number)
    and (not exists (select 1 from public.packages p where p.tracking_number = c.tracking_number and p.user_id is not null)
      or not exists (select 1 from public.packages p where p.tracking_number = number and p.user_id is not null)
      or exists (select 1 from public.packages p join public.packages q on q.user_id = p.user_id
        where p.tracking_number = c.tracking_number and q.tracking_number = number))
  order by c.first_seen, c.id
  limit 1;
$$;

create function private.mark_repeated_support_case()
returns trigger
language plpgsql
set search_path = pg_catalog
as $$
begin
  new.duplicate_of := coalesce(new.duplicate_of, private.support_case_repeated(new.tracking_number, new.first_seen, new.id));
  return new;
end;
$$;

create trigger tracking_support_cases_repeated
  before insert on public.tracking_support_cases
  for each row execute function private.mark_repeated_support_case();

revoke all on function private.one_character_apart(text, text) from public;
revoke all on function private.support_case_repeated(text, timestamptz, uuid) from public;
revoke all on function private.mark_repeated_support_case() from public;

-- Open cases already opened so: each points at the earliest it repeats.
update public.tracking_support_cases d
set duplicate_of = private.support_case_repeated(d.tracking_number, d.first_seen, d.id)
where d.fix_status = 'open' and private.support_case_repeated(d.tracking_number, d.first_seen, d.id) is not null;

insert into public.applied_migrations (name) values ('20261110183000_near_duplicate_support_cases') on conflict do nothing;

commit;
