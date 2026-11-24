-- Twin numbers: one package an email names under two numbers, the marketplace's own logistics number and the
-- last-mile carrier's (AliExpress's, then DHL's or InPost's). The account follows it once. An email that names both
-- adds the marketplace's number alone, or nothing when the account follows either, and the server keeps the pair
-- (package_twin_numbers), so an email that names either number later finds the parcel the account follows
-- (account_tracking_numbers). So does a number a parcel's carrier names as the parcel's next leg.
--
-- When the account follows both numbers as two parcels, is_later_leg takes the pair as its evidence, unless the
-- marketplace's carrier names another number for the next leg: they merge once the last-mile parcel has a scan,
-- as for the other evidence. A parcel whose carrier names the next leg's number without naming its carrier, as
-- AliExpress does, merges the same way with the account's parcel under that number. is_later_leg is otherwise copied
-- from 20261024120000_generic_automatic_links.sql.
--
-- Apply this before deploying the server that calls account_tracking_numbers and record_twin_numbers. The server
-- before it calls neither.

create table public.package_twin_numbers (
  package_id uuid not null references public.packages (id) on delete cascade,
  user_id uuid not null references auth.users (id) on delete cascade,
  earlier text not null check (char_length(earlier) between 4 and 40),
  later text not null check (char_length(later) between 4 and 40 and later <> earlier),
  created_at timestamptz not null default now(),
  primary key (package_id, earlier, later)
);
alter table public.package_twin_numbers enable row level security;
revoke all on public.package_twin_numbers from public, anon, authenticated;
grant select, insert, delete on public.package_twin_numbers to service_role;
create index package_twin_numbers_user_idx on public.package_twin_numbers (user_id, earlier, later);
comment on table public.package_twin_numbers is
  'One package an email named under two numbers: the marketplace''s logistics number (earlier) and the last-mile carrier''s (later), kept for the account''s parcel that holds one of them.';

-- Keeps an email's pair for a parcel of the account; nothing when the parcel is gone or not the account's.
create function public.record_twin_numbers(p_user_id uuid, p_package_id uuid, p_earlier text, p_later text)
returns boolean
language sql
security definer
set search_path = ''
as $$
  with kept as (
    insert into public.package_twin_numbers (package_id, user_id, earlier, later)
    select parcel.id, parcel.user_id, p_earlier, p_later
    from public.packages parcel
    where parcel.id = p_package_id and parcel.user_id = p_user_id
    on conflict do nothing
    returning 1
  )
  select exists (select 1 from kept);
$$;
revoke all on function public.record_twin_numbers(uuid, uuid, text, text) from public, anon, authenticated;
grant execute on function public.record_twin_numbers(uuid, uuid, text, text) to service_role;

-- The parcel of the account that each number names: its own number, then the earlier leg's of a merged parcel, then a
-- number its carrier names as its next leg's, then an email's twin of its number. A parcel the account still follows
-- comes before an archived one, then the newest.
create function public.account_tracking_numbers(p_user_id uuid, p_numbers text[])
returns table (tracking_number text, package_id uuid)
language sql
stable
security definer
set search_path = ''
as $$
  with asked as (
    select distinct number from unnest(p_numbers) as number where number is not null and number <> ''
  ),
  found as (
    select asked.number, parcel.id, parcel.archived_at, parcel.created_at,
      case
        when parcel.tracking_number = asked.number then 1
        when parcel.carrier_data->>'original_tracking_number' = asked.number then 2
        else 3
      end as rank
    from asked
    join public.packages parcel on parcel.user_id = p_user_id
      and asked.number in (parcel.tracking_number, parcel.carrier_data->>'original_tracking_number',
        parcel.carrier_data->>'active_tracking_number', parcel.carrier_data->>'delivery_tracking_number')
    union all
    select asked.number, parcel.id, parcel.archived_at, parcel.created_at, 4
    from asked
    join public.package_twin_numbers twin on twin.user_id = p_user_id and asked.number in (twin.earlier, twin.later)
    join public.packages parcel on parcel.id = twin.package_id and parcel.user_id = p_user_id
  )
  select distinct on (found.number) found.number, found.id
  from found
  order by found.number, found.rank, found.archived_at is not null, found.created_at desc, found.id;
$$;
revoke all on function public.account_tracking_numbers(uuid, text[]) from public, anon, authenticated;
grant execute on function public.account_tracking_numbers(uuid, text[]) to service_role;

-- Whether a carrier confirms that one parcel of an account is the later leg of
-- another's shipment, under another carrier: it names the earlier leg's number as
-- the item's international number; the earlier leg was handed over to it under a
-- number it gives the item (international or domestic); or the earlier carrier
-- names it as its delivery partner, under one of its numbers, or names one of its
-- numbers alone. An email that named both numbers for one package confirms it too,
-- unless the earlier carrier names another number for the next leg. The later leg
-- is not merged yet and has a scan of its own, so its pre-advice never replaces the
-- earlier leg's progress. Carrier references are populated only by server adapters
-- after verifying the requested shipment, and twins only from one email's numbers:
-- never infer identity from labels, senders or number prefixes.
create or replace function public.is_later_leg(original public.packages, delivery public.packages)
returns boolean
language sql
stable
set search_path = pg_catalog
as $$
  select coalesce(delivery.user_id = original.user_id
    and delivery.carrier <> original.carrier
    and delivery.archived_at is null
    and not (delivery.carrier_data ? 'original_carrier')
    and delivery.current_stage not in ('pending', 'registered')
    and ((delivery.carrier_data->>'international_tracking_number' ~ '^[A-Z0-9]{4,40}$'
        and delivery.carrier_data->>'international_tracking_number' = coalesce(
          nullif(original.carrier_data->>'original_canonical_tracking_number', ''),
          case when original.carrier_data->>'active_tracking_carrier' is distinct from delivery.carrier
            then nullif(original.carrier_data->>'canonical_tracking_number', '') end,
          original.tracking_number))
      or (original.carrier_data->>'active_tracking_carrier' = delivery.carrier
        and array[nullif(original.carrier_data->>'active_tracking_number', ''),
          nullif(original.carrier_data->>'canonical_tracking_number', '')]
          && array[delivery.tracking_number,
            nullif(delivery.carrier_data->>'canonical_tracking_number', ''),
            nullif(delivery.carrier_data->>'international_tracking_number', '')])
      or (coalesce(nullif(original.carrier_data->>'delivery_carrier', ''), delivery.carrier) = delivery.carrier
        and nullif(original.carrier_data->>'delivery_tracking_number', '') in (delivery.tracking_number,
          delivery.carrier_data->>'canonical_tracking_number',
          delivery.carrier_data->>'international_tracking_number'))
      or (coalesce(nullif(original.carrier_data->>'delivery_tracking_number', ''), delivery.tracking_number) = delivery.tracking_number
        and (coalesce(original.carrier_data->>'active_tracking_carrier', original.carrier) = original.carrier
          or original.carrier_data->>'active_tracking_number' = delivery.tracking_number)
        and exists (select 1 from public.package_twin_numbers twin
          where twin.user_id = original.user_id and twin.earlier = original.tracking_number
            and twin.later = delivery.tracking_number))), false);
$$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261124140000_twin_numbers') on conflict do nothing;
