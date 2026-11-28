-- The calls a running server makes to the public functions, as PostgREST matches them: by the function's name and
-- the names of the arguments it is given, an argument left out needing a default. A migration is applied before the
-- server it serves (docs/DEPLOYMENT.md), so the running server keeps making them meanwhile.
--
-- Run alone, this prints the functions a server can call. Run with `before_file` set to the file that holds what it
-- printed before a migration, it names each call that worked then and no longer does: the function gone, an
-- argument gone, retyped or made required, a second function that takes the same call, or a result that changed its
-- type or lost a column. scripts/test-migrations.sh runs it around each migration.

select coalesce(jsonb_agg(jsonb_build_object(
    'name', p.proname,
    'signature', p.oid::regprocedure::text,
    'set', p.proretset,
    'returns', format_type(p.prorettype, null),
    'inputs', (
      select coalesce(jsonb_agg(jsonb_build_object(
          'name', a.name, 'type', a.type, 'optional', a.position > p.pronargs - p.pronargdefaults
        ) order by a.position), '[]')
      from (
        select coalesce(nullif(arg.name, ''), 'argument ' || arg.n) as name, format_type(arg.type, null) as type,
          row_number() over (order by arg.n) as position
        from unnest(
            coalesce(p.proargmodes, array_fill('i'::"char", array[p.pronargs::int])), p.proargnames,
            coalesce(p.proallargtypes, p.proargtypes::oid[])
          ) with ordinality as arg(mode, name, type, n)
        where arg.mode in ('i', 'b', 'v')
      ) a
    ),
    'outputs', (
      select coalesce(jsonb_object_agg(coalesce(nullif(arg.name, ''), 'column ' || arg.n), format_type(arg.type, null)), '{}')
      from unnest(
          coalesce(p.proargmodes, array_fill('i'::"char", array[p.pronargs::int])), p.proargnames,
          coalesce(p.proallargtypes, p.proargtypes::oid[])
        ) with ordinality as arg(mode, name, type, n)
      where arg.mode in ('o', 'b', 't')
    )
  )), '[]') as functions
from pg_proc p
join pg_namespace n on n.oid = p.pronamespace
where n.nspname = 'public'
  and p.prokind = 'f'
  and p.prorettype not in ('trigger'::regtype, 'event_trigger'::regtype)
  and not exists (select from pg_depend d where d.classid = 'pg_proc'::regclass and d.objid = p.oid and d.deptype = 'e')
  and (has_function_privilege('service_role', p.oid, 'execute')
    or has_function_privilege('authenticated', p.oid, 'execute')
    or has_function_privilege('anon', p.oid, 'execute'))
\gset

\if :{?before_file}
\set before `cat :'before_file'`
with before as (
  select f->>'name' as name, f->>'signature' as signature, f
  from jsonb_array_elements(:'before'::jsonb) f
), after as (
  select f->>'name' as name, f
  from jsonb_array_elements(:'functions'::jsonb) f
), calls as (
  -- With every argument, and with only those without a default: a call between the two matches what they match.
  select distinct b.name, b.signature, b.f, k.arguments
  from before b
  cross join lateral (
    select array(select i->>'name' from jsonb_array_elements(b.f->'inputs') i order by 1) as arguments
    union
    select array(select i->>'name' from jsonb_array_elements(b.f->'inputs') i where not (i->>'optional')::boolean order by 1)
  ) k
), matches as (
  select c.name, c.signature, c.f, c.arguments, count(a.f) as count, (array_agg(a.f))[1] as match
  from calls c
  left join after a on a.name = c.name
    and c.arguments <@ array(select i->>'name' from jsonb_array_elements(a.f->'inputs') i)
    and array(select i->>'name' from jsonb_array_elements(a.f->'inputs') i where not (i->>'optional')::boolean) <@ c.arguments
  group by c.name, c.signature, c.f, c.arguments
), problems as (
  select m.signature, format('a call with (%s) %s', array_to_string(m.arguments, ', '),
      case when m.count = 0 then 'finds no function that takes it' else format('finds %s functions that take it', m.count) end
    ) as problem
  from matches m
  where m.count <> 1 and exists (select from after a where a.name = m.name)
  union
  select b.signature, 'is gone'
  from before b
  where not exists (select from after a where a.name = b.name)
  union
  select m.signature, format('takes %s as %s, not %s', i->>'name', j->>'type', i->>'type')
  from matches m
  cross join jsonb_array_elements(m.f->'inputs') i
  join jsonb_array_elements(m.match->'inputs') j on j->>'name' = i->>'name'
  where m.count = 1 and i->>'name' = any (m.arguments) and j->>'type' <> i->>'type'
  union
  select m.signature, format('now returns %s%s, not %s%s',
      case when (m.match->>'set')::boolean then 'a set of ' else '' end,
      case when m.match->'outputs' <> '{}' then m.match->>'outputs' else m.match->>'returns' end,
      case when (m.f->>'set')::boolean then 'a set of ' else '' end,
      case when m.f->'outputs' <> '{}' then m.f->>'outputs' else m.f->>'returns' end)
  from matches m
  where m.count = 1 and (
    m.match->'set' <> m.f->'set'
    or (m.f->'outputs' <> '{}' and not ((m.match->'outputs') @> (m.f->'outputs')))
    or (m.f->'outputs' = '{}' and m.f->>'returns' <> 'void' and m.match->>'returns' <> m.f->>'returns')
  )
)
select signature || ': ' || problem from problems order by 1;
\else
\echo :functions
\endif
