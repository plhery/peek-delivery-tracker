-- A lookup's claim also says how many lookups its IPv6 /48 has made today, `network`: the
-- server warns when a network reaches half its allowance, the sign to give app installs their
-- own. With PUBLIC_LOOKUPS_BY_INSTALL on, an install's proven lookups count on their address's
-- and network's `app:` counters instead of the ones other visitors share, so the claim takes
-- those too; the usage summary still reads clients' counters alone. Built on
-- 20261003100000_public_allowances, the last that defined it; the arguments and every other
-- field are unchanged.

alter table public.public_lookup_usage
  drop constraint public_lookup_usage_bucket_format,
  add constraint public_lookup_usage_bucket_format
    check (bucket ~ '^(global|detection|((network|detection):)?(app:)?[0-9a-f]{64})$');

comment on table public.public_lookup_usage is
  'Lookups and carrier detections without an account per day (UTC), by hashed client address or network, the app installs that proved themselves there, and overall. Kept for seven days.';

create or replace function public.claim_public_lookup(
  p_bucket text,
  p_limit integer,
  p_global_limit integer,
  p_global_bucket text default 'global',
  p_network_bucket text default null,
  p_network_limit integer default null
)
returns jsonb
language plpgsql
security definer
set search_path = ''
as $$
declare
  today constant date := (now() at time zone 'UTC')::date;
  used integer;
  used_by_network integer;
  used_overall integer;
  answer jsonb;
begin
  if p_bucket is null or p_bucket !~ '^(detection:)?(app:)?[0-9a-f]{64}$'
      or p_limit is null or p_limit < 0
      or p_global_bucket is null or p_global_bucket not in ('global', 'detection')
      or p_global_limit is null or p_global_limit < 0
      or (p_network_bucket is not null and (
        p_network_bucket !~ '^network:(app:)?[0-9a-f]{64}$' or p_network_limit is null or p_network_limit < 0
      )) then
    raise exception 'Invalid lookup allowance' using errcode = '22023';
  end if;

  delete from public.public_lookup_usage where day < today - 7;

  insert into public.public_lookup_usage (bucket, day)
  select counted.bucket, today
  from unnest(array[p_global_bucket, p_network_bucket, p_bucket]) as counted(bucket)
  where counted.bucket is not null
  on conflict do nothing;
  select count into used_overall from public.public_lookup_usage
  where bucket = p_global_bucket and day = today for update;
  if p_network_bucket is not null then
    select count into used_by_network from public.public_lookup_usage
    where bucket = p_network_bucket and day = today for update;
  end if;
  select count into used from public.public_lookup_usage
  where bucket = p_bucket and day = today for update;

  if used >= p_limit then
    answer := jsonb_build_object('allowed', false, 'scope', 'bucket', 'remaining', 0, 'overall', used_overall);
  elsif used_by_network >= p_network_limit then
    answer := jsonb_build_object('allowed', false, 'scope', 'network', 'remaining', p_limit - used, 'overall', used_overall);
  elsif used_overall >= p_global_limit then
    answer := jsonb_build_object('allowed', false, 'scope', 'global', 'remaining', p_limit - used, 'overall', used_overall);
  else
    update public.public_lookup_usage set count = count + 1
    where day = today and bucket in (p_global_bucket, p_network_bucket, p_bucket);
    used_by_network := used_by_network + 1;
    answer := jsonb_build_object('allowed', true, 'scope', null, 'remaining', p_limit - used - 1, 'overall', used_overall + 1);
  end if;
  if p_network_bucket is not null then
    answer := answer || jsonb_build_object('network', used_by_network);
  end if;
  return answer;
end;
$$;
revoke all on function public.claim_public_lookup(text, integer, integer, text, text, integer) from public, anon, authenticated;
grant execute on function public.claim_public_lookup(text, integer, integer, text, text, integer) to service_role;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261119180000_lookup_network_count') on conflict do nothing;
