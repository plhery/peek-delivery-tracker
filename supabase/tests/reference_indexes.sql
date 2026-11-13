\set ON_ERROR_STOP on
-- Deleting a row finds the rows that point at it by index: without one, each row deleted reads the
-- whole pointing table, and deleting an account outlasts the Auth server's limit. A reference's
-- columns lead some index of its table, in any order. A partial index serves only when it leaves
-- out no row that points at anything: its condition is that one of those columns is not null.
do $$
declare
  missing text;
begin
  select string_agg(c.conrelid::regclass::text || ' (' || c.conname || ')', ', ' order by 1) into missing
  from pg_constraint c
  join pg_class r on r.oid = c.conrelid
  join pg_namespace n on n.oid = r.relnamespace
  where c.contype = 'f' and n.nspname in ('public', 'private')
    and not exists (
      select 1 from pg_index i
      where i.indrelid = c.conrelid
        and (select array_agg(k order by k) from unnest((i.indkey::int2[])[0:cardinality(c.conkey) - 1]) k)
          = (select array_agg(k order by k) from unnest(c.conkey) k)
        and (i.indpred is null or exists (
          select 1 from unnest(c.conkey) as k
          join pg_attribute as a on a.attrelid = c.conrelid and a.attnum = k
          where pg_get_expr(i.indpred, i.indrelid) = '(' || quote_ident(a.attname) || ' IS NOT NULL)')));
  if missing is not null then raise exception 'references without an index: %', missing; end if;
end;
$$;
