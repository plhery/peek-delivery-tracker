-- A reader's answer keeps the build of the app that gave it (`app_build`), as the app's
-- user-agent names it: the iPhone, iPad and Mac app's build number, the Android app's version
-- code. The web answers with the server it is served by (`server_version`) and has none.
--
-- Apply this before deploying the server that sends it. The server before it keeps working on
-- this schema: it never sends `app_build`.

alter table public.parcel_feedback
  add column app_build text check (app_build ~ '^[A-Za-z0-9._-]{1,40}$');

comment on column public.parcel_feedback.app_build is
  'The build of the app that gave the answer, as its user-agent names it: the Apple app''s build number or the Android app''s version code. None from the web.';

-- As before, with the app's build.
create or replace function public.record_parcel_feedback(p_feedback jsonb)
returns text
language plpgsql
security definer
set search_path = ''
as $$
declare
  feedback_id constant uuid := (p_feedback->>'id')::uuid;
  number constant text := p_feedback->>'tracking_number';
  given_reasons constant text[] := array(
    select pg_catalog.jsonb_array_elements_text(coalesce(p_feedback->'reasons', '[]'))
  );
  existing public.parcel_feedback;
begin
  if feedback_id is null or number is null then
    raise exception 'Invalid parcel feedback' using errcode = '22023';
  end if;
  select * into existing from public.parcel_feedback where id = feedback_id for update;
  if found then
    if existing.tracking_number <> number or existing.answer <> p_feedback->>'answer'
        or existing.created_at < pg_catalog.now() - interval '1 hour' then
      return 'closed';
    end if;
    update public.parcel_feedback set
      reasons = given_reasons,
      note = p_feedback->>'note',
      carrier_name = p_feedback->>'carrier_name',
      tracking_page = p_feedback->>'tracking_page',
      updated_at = pg_catalog.now()
    where id = feedback_id;
    return 'replaced';
  end if;
  if (
    select pg_catalog.count(*) from public.parcel_feedback
    where tracking_number = number and created_at > pg_catalog.now() - interval '1 day'
  ) >= 20 then
    return 'full';
  end if;
  insert into public.parcel_feedback (
    id, tracking_number, carrier, answer, reasons, note, carrier_name, tracking_page, page_said,
    asked, via, app, app_build, locale, server_version, shown
  ) values (
    feedback_id, number, p_feedback->>'carrier', p_feedback->>'answer', given_reasons, p_feedback->>'note',
    p_feedback->>'carrier_name', p_feedback->>'tracking_page', p_feedback->>'page_said', p_feedback->>'asked',
    p_feedback->>'via', p_feedback->>'app', p_feedback->>'app_build', p_feedback->>'locale',
    p_feedback->>'server_version', coalesce(p_feedback->'shown', '{}')
  );
  return 'stored';
end;
$$;

notify pgrst, 'reload schema';

insert into public.applied_migrations (name) values ('20261127010000_parcel_feedback_app_build') on conflict do nothing;
