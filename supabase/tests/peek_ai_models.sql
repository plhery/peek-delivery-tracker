\set ON_ERROR_STOP on
begin;

-- Only the server reads Peek AI's model: it is learned from production parcels.
do $$
begin
  if has_table_privilege('anon', 'public.peek_ai_models', 'SELECT')
      or has_table_privilege('authenticated', 'public.peek_ai_models', 'SELECT')
      or has_table_privilege('authenticated', 'public.peek_ai_models', 'INSERT') then
    raise exception 'public database roles can read Peek AI''s model';
  end if;
  if not has_table_privilege('service_role', 'public.peek_ai_models', 'SELECT') then
    raise exception 'the service role cannot read Peek AI''s model';
  end if;
  if not (select relrowsecurity from pg_class where oid = 'public.peek_ai_models'::regclass)
      or exists (select 1 from pg_policies where schemaname = 'public' and tablename = 'peek_ai_models') then
    raise exception 'Peek AI''s model is readable through row level security';
  end if;
end;
$$;

-- A model is a document under a version, and the newest is read first.
insert into public.peek_ai_models (version, created_at, model) values ('2026-11-01.0000', '2026-11-01T00:00:00Z', '{"version":"2026-11-01.0000"}');
insert into public.peek_ai_models (version, model) values ('2026-11-20.0000', '{"version":"2026-11-20.0000"}');
do $$
begin
  if (select version from public.peek_ai_models order by created_at desc limit 1) <> '2026-11-20.0000' then
    raise exception 'the newest Peek AI model is not read first';
  end if;
  begin
    insert into public.peek_ai_models (version, model) values ('2026-11-21.0000', '[]');
    raise exception 'a Peek AI model was stored as something other than a document';
  exception when check_violation then null;
  end;
  begin
    insert into public.peek_ai_models (version, model) values ('', '{}');
    raise exception 'a Peek AI model was stored without a version';
  exception when check_violation then null;
  end;
end;
$$;

rollback;
