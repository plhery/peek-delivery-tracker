import { describe, expect, it } from 'vitest';
import { noteSnippet, searchPieces, searchRanges } from './searchMatch';

const marked = (snippet: ReturnType<typeof noteSnippet>) =>
  snippet && searchPieces(snippet.text, snippet.ranges).filter((piece) => piece.matched).map((piece) => piece.text);

describe('where a list search matched', () => {
  it('finds every place the search stands, in any case, by characters', () => {
    expect(searchRanges('Vinyl, more VINYL and vinyl', ' vinyl ')).toEqual([[0, 5], [12, 17], [22, 27]]);
    // An emoji before the match counts as one character, as the row cuts it.
    expect(searchRanges('🎷 Kind of Blue', 'blue')).toEqual([[10, 14]]);
    expect(searchRanges('Kind of Blue', '')).toEqual([]);
    expect(searchRanges('Kind of Blue', 'green')).toEqual([]);
    expect(searchPieces('Kind of Blue', searchRanges('Kind of Blue', 'of')))
      .toEqual([{ text: 'Kind ', matched: false }, { text: 'of', matched: true }, { text: ' Blue', matched: false }]);
  });

  it('keeps a character whose lower case is longer whole', () => {
    // "İ" lowers to two characters; the match still begins and ends on whole characters.
    expect(searchRanges('İstanbul, Istanbul', 'stanbul')).toEqual([[1, 8], [11, 18]]);
  });

  it('shows a short note whole, every match marked', () => {
    const snippet = noteSnippet('Door code 4812.\nCode for the bike shed: 99', [], 'code');
    expect(snippet?.text).toBe('Door code 4812. Code for the bike shed: 99');
    expect(marked(snippet)).toEqual(['code', 'Code']);
  });

  it('shows a long note from a few words before the match, cut between words, with … where it goes on', () => {
    const note = `${'Ordered for the living room after a long wait at the shop. '.repeat(3)}Gift receipt is in the drawer, under the blue folder with the warranty papers and the spare keys. Call before noon.`;
    const snippet = noteSnippet(note, [], 'receipt')!;
    expect(snippet.text.startsWith('…')).toBe(true);
    expect(snippet.text.endsWith('…')).toBe(true);
    expect(snippet.text).toMatch(/^…[^ ]/);
    expect(Array.from(snippet.text).length).toBeLessThanOrEqual(92);
    // The match stands near the start, where a one-line row still shows it.
    expect(snippet.ranges[0][0]).toBeLessThanOrEqual(25);
    expect(marked(snippet)).toEqual(['receipt']);
    expect(note).toContain(snippet.text.slice(1, -1));
  });

  it('marks only the matches the snippet shows', () => {
    const note = `Box 1 of 2 here. ${'Lorem ipsum dolor sit amet. '.repeat(6)}Box 2 of 2 at the neighbour's.`;
    const snippet = noteSnippet(note, [], 'box')!;
    expect(snippet.text.startsWith('Box 1 of 2')).toBe(true);
    expect(marked(snippet)).toEqual(['Box']);
  });

  it("falls back to a file's name, and is null when neither the note nor a file holds the search", () => {
    expect(noteSnippet('Door code 4812', ['Receipt.pdf', 'Box.jpg'], 'box')).toEqual({ text: 'Box.jpg', ranges: [[0, 3]] });
    expect(noteSnippet('Door code 4812', ['Receipt.pdf'], 'vinyl')).toBeNull();
    expect(noteSnippet(undefined, [], 'vinyl')).toBeNull();
  });
});
