import type { JsonObject } from '../server/types';

/** Query parameters that are no column filter. `or` and `and` are the database's: a fixture holds the rows they keep. */
const NOT_FILTERS = new Set(['select', 'order', 'limit', 'offset', 'on_conflict', 'or', 'and']);

/**
 * What PostgREST answers a read of `rows` with, for the server's tests: the columns its `select`
 * names alone, under their aliases and embeds included, so a fixture can't be richer than the
 * query; the rows its column filters keep (`eq.`, `gt.`, `in.(…)`, `is.` and the like), in its
 * `order`, at most `limit` and `cap` of them, as the database's API ends an answer at its row limit
 * without saying so.
 */
export function postgrestRead(rows: readonly JsonObject[], path: string, cap = Infinity): JsonObject[] {
  const params = new URL(`https://database.example${path}`).searchParams;
  const filters = [...params].filter(([key]) => !NOT_FILTERS.has(key));
  let kept = rows.filter((row) => filters.every(([key, filter]) => matches(resolve(row, key), filter)));
  const order = params.get('order');
  if (order) kept = [...kept].sort(ordering(order));
  const limit = Math.min(params.has('limit') ? Number(params.get('limit')) : Infinity, cap);
  return kept.slice(0, limit).map((row) => project(row, params.get('select') ?? '*'));
}

/**
 * A request fake answering reads of these tables and functions (`packages`,
 * `rpc/followed_one_off_packages`) as postgrestRead does, whatever the function's arguments:
 * each fixture holds the rows the database returns.
 */
export function postgrestTables(tables: Record<string, readonly JsonObject[]>, cap = Infinity) {
  return async (path: string): Promise<JsonObject[]> => {
    const name = path.replace(/^\/rest\/v1\//, '').split('?')[0]!;
    const rows = tables[name];
    if (!rows) throw new Error(`The fake database has no ${name}`);
    return postgrestRead(rows, path, cap);
  };
}

function matches(value: unknown, filter: string): boolean {
  const negated = filter.startsWith('not.');
  const [operator, ...rest] = (negated ? filter.slice(4) : filter).split('.');
  const operand = rest.join('.');
  const text = value == null ? null : String(value);
  const result = (() => {
    switch (operator) {
      case 'eq': return text === operand;
      case 'neq': return text !== null && text !== operand;
      case 'gt': return text !== null && text > operand;
      case 'gte': return text !== null && text >= operand;
      case 'lt': return text !== null && text < operand;
      case 'lte': return text !== null && text <= operand;
      case 'in': return text !== null && operand.replace(/^\(|\)$/g, '').split(',')
        .map((item) => item.replace(/^"|"$/g, '')).includes(text);
      case 'is': return operand === 'null' ? value == null : value === (operand === 'true');
      default: throw new Error(`The fake database cannot filter with ${filter}`);
    }
  })();
  return negated ? !result : result;
}

/** A column, or a path into a JSON one (`carrier_data->routing->>rules`). */
function resolve(row: JsonObject, path: string): unknown {
  const [column, ...keys] = path.split(/->>?/);
  return keys.reduce<unknown>((value, key) => value !== null && typeof value === 'object' ? (value as JsonObject)[key] : undefined,
    row[column!]);
}

function ordering(order: string): (a: JsonObject, b: JsonObject) => number {
  const keys = order.split(',').map((item) => {
    const [column, direction = 'asc', nulls] = item.split('.');
    return { column: column!, sign: direction === 'desc' ? -1 : 1, nullsFirst: nulls ? nulls === 'nullsfirst' : direction === 'desc' };
  });
  return (a, b) => {
    for (const { column, sign, nullsFirst } of keys) {
      const [left, right] = [resolve(a, column), resolve(b, column)];
      if (left == null || right == null) {
        if (left == null && right == null) continue;
        return (left == null) === nullsFirst ? -1 : 1;
      }
      if (String(left) !== String(right)) return String(left) < String(right) ? -sign : sign;
    }
    return 0;
  };
}

/** The row with the columns `select` names alone, as PostgREST names them. */
function project(row: JsonObject, select: string): JsonObject {
  const projected: JsonObject = {};
  for (const item of topLevel(select)) {
    if (item === '*') {
      Object.assign(projected, row);
      continue;
    }
    const open = item.indexOf('(');
    const head = open < 0 ? item : item.slice(0, open);
    const [alias, source] = head.includes(':') ? head.split(':') as [string, string] : [undefined, head];
    if (open >= 0) {
      const inner = item.slice(open + 1, -1);
      const embedded = row[source];
      projected[alias ?? source] = Array.isArray(embedded)
        ? embedded.map((nested) => project(nested as JsonObject, inner))
        : embedded && typeof embedded === 'object' ? project(embedded as JsonObject, inner) : embedded === undefined ? [] : null;
      continue;
    }
    projected[alias ?? source.split(/->>?/).at(-1)!] = resolve(row, source) ?? null;
  }
  return projected;
}

/** The items of a select, split at the commas outside an embed's parentheses. */
function topLevel(select: string): string[] {
  const items: string[] = [];
  let depth = 0;
  let start = 0;
  for (const [index, character] of [...select].entries()) {
    if (character === '(') depth += 1;
    else if (character === ')') depth -= 1;
    else if (character === ',' && depth === 0) {
      items.push(select.slice(start, index));
      start = index + 1;
    }
  }
  items.push(select.slice(start));
  return items.filter(Boolean);
}
