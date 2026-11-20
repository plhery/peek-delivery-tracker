-- A parcel opened is seen as of the news its page showed (news_at, eventPlaces.ts), not as of the moment the
-- request arrives: a scan stored while the page was opening stays new. A request that names no news, as an older
-- app's, marks the moment it arrives. The news is served to the microsecond and a device may keep it to the
-- millisecond, so the end of that millisecond is stored; never later than now, nor earlier than what the account had
-- seen already.


drop function public.mark_owned_package_seen(uuid);

create function public.mark_owned_package_seen(p_package_id uuid, p_news_at timestamptz default null)
returns timestamptz
language plpgsql
security definer
set search_path = pg_catalog
as $$
declare
  seen timestamptz;
begin
  if auth.uid() is null then
    raise exception 'An account is required' using errcode = '42501';
  end if;
  update public.packages
  set seen_at = greatest(seen_at, case when p_news_at is null then now()
    else least(now(), date_trunc('milliseconds', p_news_at) + interval '999 microseconds') end)
  where id = p_package_id and user_id = auth.uid()
  returning seen_at into seen;
  return seen;
end;
$$;

revoke all on function public.mark_owned_package_seen(uuid, timestamptz) from public, anon;
grant execute on function public.mark_owned_package_seen(uuid, timestamptz) to authenticated;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261115200000_seen_at_shown_news') on conflict do nothing;

