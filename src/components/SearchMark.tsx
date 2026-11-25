import { createContext, useContext } from 'react';
import { useI18n } from '../i18n';
import { noteSnippet, searchPieces, searchRanges, type NoteSnippet, type SearchRange } from '../lib/searchMatch';
import './SearchMark.css';

/**
 * What the reader's own list is searching for, empty when it isn't. Only the account's list and the device's list give
 * it: a card anywhere else (a shared or gift link, a preview, a friend's view) marks nothing and shows no note.
 */
export const ListSearch = createContext('');

export function useListSearch(): string {
  return useContext(ListSearch);
}

/** Text with the search's matches marked, as the list marks them. */
export function SearchMarked({ text, ranges }: { text: string; ranges: readonly SearchRange[] }) {
  if (!ranges.length) return text;
  return <>{searchPieces(text, ranges).map((piece, index) => piece.matched
    ? <mark key={index} className="search-mark">{piece.text}</mark>
    : piece.text)}</>;
}

/** Text with the list's search marked in it, when the list is searching. */
export function ListSearchMarked({ text }: { text: string }) {
  return <SearchMarked text={text} ranges={searchRanges(text, useListSearch())} />;
}

/**
 * The few words of the note a search found the parcel by, for a row of the reader's own list: only while searching
 * and only when the note or a file's name holds the search.
 */
export function useNoteMatch(note: string | undefined, fileNames: readonly string[]): NoteSnippet | null {
  const query = useListSearch();
  return query.trim() ? noteSnippet(note, fileNames, query) : null;
}

/** The note's words under a row's name, the match marked; "Your note:" before them for a screen reader. */
export function NoteMatch({ snippet, className }: { snippet: NoteSnippet; className?: string }) {
  const { t } = useI18n();
  return <span className={className ? `note-match ${className}` : 'note-match'}>
    <span className="sr-only">{`${t('note.title')}: `}</span>
    <SearchMarked text={snippet.text} ranges={snippet.ranges} />
  </span>;
}
