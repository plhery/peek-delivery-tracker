\set ON_ERROR_STOP on
begin;
do $$
declare role_name text;
begin
  foreach role_name in array array['anon', 'authenticated'] loop
    if has_function_privilege(role_name, 'public.claim_allowance_report(text,text)', 'EXECUTE')
        or has_table_privilege(role_name, 'private.allowance_reports', 'SELECT,INSERT,UPDATE,DELETE') then
      raise exception 'Allowance reports must be server-only';
    end if;
  end loop;
  if not has_function_privilege('service_role', 'public.claim_allowance_report(text,text)', 'EXECUTE') then
    raise exception 'The server must be able to claim allowance reports';
  end if;
end;
$$;
set local role service_role;
do $$ begin
  if not public.claim_allowance_report('public:lookup:running_out', '2026-11-04')
      or public.claim_allowance_report('public:lookup:running_out', '2026-11-04') then
    raise exception 'A report must be claimed once per period';
  end if;
  if not public.claim_allowance_report('public:lookup:used_up', '2026-11-04')
      or not public.claim_allowance_report('public:lookup:running_out', '2026-11-05')
      or not public.claim_allowance_report('account:detection:running_out', '2026-11-04T01')
      or not public.claim_allowance_report('account:detection:running_out', '2026-11-04T02') then
    raise exception 'Each state and period must be its own report';
  end if;
  begin
    perform public.claim_allowance_report('public:lookup:fine', '2026-11-04');
    raise exception 'An unknown state was claimed';
  exception when invalid_parameter_value then null;
  end;
  begin
    perform public.claim_allowance_report('public:lookup:used_up', 'today');
    raise exception 'An unknown period was claimed';
  exception when invalid_parameter_value then null;
  end;
end; $$;
reset role;
update private.allowance_reports set reported_at = now() - interval '8 days' where period = '2026-11-04';
set local role service_role;
do $$ begin
  if not public.claim_allowance_report('public:detection:used_up', '2026-11-12') then
    raise exception 'A new report must be claimed';
  end if;
end; $$;
reset role;
do $$ begin
  if exists (select from private.allowance_reports where period = '2026-11-04') then
    raise exception 'Reports over a week old must be forgotten';
  end if;
  if (select count(*) from private.allowance_reports) <> 4 then
    raise exception 'Recent reports must be kept';
  end if;
end; $$;
rollback;
