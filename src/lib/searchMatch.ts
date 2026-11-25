/**
 * Where a list search matched, so a row can mark it. The list finds a parcel by its words in lower case
 * (`parcelMatchesSearch`); these find the same words, and give their places in characters (code points), which is how
 * a row cuts its text.
 */

export type SearchRange = readonly [start: number, end: number];

/** The search as the list reads it: trimmed, its spaces one, in lower case. Empty when nothing is searched. */
function searched(query: string): string {
  return query.trim().replace(/\s+/g, ' ').toLocaleLowerCase();
}

/** Every place `query` stands in `text`, ignoring case, one after the other and none overlapping. */
export function searchRanges(text: string, query: string): SearchRange[] {
  const wanted = searched(query);
  if (!wanted) return [];
  const characters = Array.from(text);
  // Each character in lower case on its own, so a place in the lowered text leads back to a character.
  let lowered = '';
  const startOf: number[] = [];
  for (const character of characters) {
    startOf.push(lowered.length);
    lowered += character.toLocaleLowerCase();
  }
  const characterAt = (offset: number) => {
    let index = startOf.findIndex((start) => start > offset);
    if (index === -1) index = characters.length;
    return index - 1;
  };
  const ranges: SearchRange[] = [];
  for (let at = lowered.indexOf(wanted); at !== -1; at = lowered.indexOf(wanted, at + wanted.length)) {
    ranges.push([characterAt(at), characterAt(at + wanted.length - 1) + 1]);
  }
  return ranges;
}

/** Text cut into its matched and unmatched pieces, in order. */
export function searchPieces(text: string, ranges: readonly SearchRange[]): { text: string; matched: boolean }[] {
  const characters = Array.from(text);
  const pieces: { text: string; matched: boolean }[] = [];
  let at = 0;
  for (const [start, end] of ranges) {
    if (start > at) pieces.push({ text: characters.slice(at, start).join(''), matched: false });
    pieces.push({ text: characters.slice(start, end).join(''), matched: true });
    at = end;
  }
  if (at < characters.length) pieces.push({ text: characters.slice(at).join(''), matched: false });
  return pieces;
}

/** How much of a note a row shows around the first match: a few words before it, and one line in all. */
const BEFORE = 24;
const LENGTH = 90;

export interface NoteSnippet {
  /** The words around the match, with "…" where the note goes on. */
  text: string;
  /** Every match within them. */
  ranges: SearchRange[];
}

/**
 * The few words of a note, or the name of one of its files, that a search matched, for the row that found it. Null
 * when neither holds the search: the row matched by its name, number, carrier or scans.
 */
export function noteSnippet(note: string | undefined, fileNames: readonly string[], query: string): NoteSnippet | null {
  const flat = (note ?? '').replace(/\s+/g, ' ').trim();
  const ranges = searchRanges(flat, query);
  if (ranges.length) return around(flat, ranges);
  for (const name of fileNames) {
    const named = searchRanges(name, query);
    if (named.length) return around(name, named);
  }
  return null;
}

/** The window of `text` that starts a few words before the first match and fits a line, cut between words. */
function around(text: string, ranges: readonly SearchRange[]): NoteSnippet {
  const characters = Array.from(text);
  const [first, firstEnd] = ranges[0];
  let start = Math.max(0, first - BEFORE);
  if (start > 0 && characters[start - 1] !== ' ') {
    // Start on a whole word: after the first space in the few characters before the match, if there is one.
    const space = characters.slice(start, first).indexOf(' ');
    if (space !== -1) start += space + 1;
  }
  let end = Math.min(characters.length, Math.max(start + LENGTH, firstEnd));
  if (end < characters.length) {
    // End on a word, never before the first match does.
    const space = characters.slice(firstEnd, end).lastIndexOf(' ');
    if (space !== -1) end = firstEnd + space;
  }
  const before = start > 0 ? '…' : '';
  const shift = start - before.length;
  const shown = ranges
    .filter(([from, to]) => from >= start && to <= end)
    .map(([from, to]) => [from - shift, to - shift] as const);
  return { text: `${before}${characters.slice(start, end).join('').trimEnd()}${end < characters.length ? '…' : ''}`, ranges: shown };
}
