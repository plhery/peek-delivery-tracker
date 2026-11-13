-- The postcode the owner gives a universal provider ends the wait of every provider that asked
-- for it, Track718's and Track123's too, as the next check asks them again.

create or replace function public.set_owned_package_provider_postcode(p_package_id uuid, p_postcode text)
returns boolean
language plpgsql
security definer
set search_path = pg_catalog
as $$
declare
  actor_id uuid := auth.uid();
  cleaned_data jsonb;
  provider_failures jsonb;
  lookup_number text;
  normalized text := upper(regexp_replace(btrim(coalesce(p_postcode, '')), '[[:space:]]+', ' ', 'g'));
begin
  if actor_id is null or exists (select 1 from auth.users where id = actor_id and is_anonymous) then
    raise exception 'A permanent account is required' using errcode = '42501';
  end if;
  if char_length(normalized) not between 3 and 12 or normalized !~ '[0-9]'
      or normalized !~ '^[A-Z0-9]+([ -][A-Z0-9]+)*$' then
    raise exception 'Invalid delivery postcode' using errcode = '22023';
  end if;
  select coalesce(carrier_data, '{}'::jsonb), tracking_number into cleaned_data, lookup_number
  from public.packages where id = p_package_id and user_id = actor_id for update;
  if not found then return false; end if;
  -- After a handoff the delivery leg's number is the one looked up, as trackingRouting.ts reads it.
  if coalesce(cleaned_data->>'original_carrier', '') <> '' and coalesce(cleaned_data->>'active_tracking_carrier', '') <> ''
      and jsonb_typeof(cleaned_data->'active_tracking_number') = 'string' then
    lookup_number := cleaned_data->>'active_tracking_number';
  end if;
  select coalesce(jsonb_object_agg(key, value), '{}'::jsonb) into provider_failures
  from jsonb_each(case when jsonb_typeof(cleaned_data#>'{routing,failures}') = 'object'
    then cleaned_data#>'{routing,failures}' else '{}'::jsonb end)
  where key not in ('ParcelsApp', '17TRACK', 'Ship24', 'Postal Ninja', 'Track718', 'Track123', 'UPU')
    or value->>'kind' is distinct from 'input_required';
  if cleaned_data#>'{routing,failures}' is not null then
    cleaned_data := jsonb_set(cleaned_data, '{routing,failures}', provider_failures);
  end if;
  update public.sync_jobs
  set state = 'failed', completed_at = now(), lease_until = null,
      locked_by = null, dedupe_key = null,
      last_error = 'Superseded because provider input changed.'
  where package_id = p_package_id and state in ('queued', 'running');
  update public.packages
  set carrier_data = jsonb_set(
        (cleaned_data #- '{routing,provider_input_needed}' #- '{routing,next_check_at}'),
        '{universal_input}', jsonb_build_object('number', lookup_number, 'postcode', normalized)),
      sync_status = 'pending', sync_error = null,
      tracking_generation = gen_random_uuid()
  where id = p_package_id and user_id = actor_id;
  return true;
end;
$$;
revoke all on function public.set_owned_package_provider_postcode(uuid, text) from public, anon;
grant execute on function public.set_owned_package_provider_postcode(uuid, text) to authenticated;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261113213000_provider_postcode_waits') on conflict do nothing;
