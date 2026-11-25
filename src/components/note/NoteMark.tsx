import type { SearchRange } from '../../lib/searchMatch';
import { SearchMarked } from '../SearchMark';
import './NoteMark.css';

/** The list's quiet mark: a note, or a paperclip once it holds a photo or a file. */
export function NoteMark({ files = false }: { files?: boolean }) {
  return <svg className="note-mark" viewBox="0 0 16 16" aria-hidden="true" focusable="false">
    {files
      ? <path d="M13 7.6 8.1 12.5a3.3 3.3 0 0 1-4.7-4.7l5.3-5.3a2.2 2.2 0 0 1 3.1 3.1l-5.3 5.3a1.1 1.1 0 0 1-1.6-1.6L9.8 4.4" />
      : <><rect x="3.25" y="1.75" width="9.5" height="12.5" rx="2" /><path d="M5.75 5.5h4.5M5.75 8h4.5M5.75 10.5h2.5" /></>}
  </svg>;
}

/**
 * A name with the mark after it. The mark never wraps onto a line alone: it holds on to the name's last characters.
 * `ranges` are a list search's matches in the name, marked as the list marks them.
 */
export function NameWithMark({ name, files = false, ranges = [] }: { name: string; files?: boolean; ranges?: readonly SearchRange[] }) {
  const characters = Array.from(name.trimEnd());
  const lastSpace = Math.max(characters.lastIndexOf(' '), characters.lastIndexOf('\u00a0'));
  const split = Math.max(lastSpace + 1, characters.length - 8);
  const within = ranges.map(([start, end]) => [start, Math.min(end, characters.length)] as const).filter(([start, end]) => start < end);
  const head = within.filter(([start]) => start < split).map(([start, end]) => [start, Math.min(end, split)] as const);
  const tail = within.filter(([, end]) => end > split).map(([start, end]) => [Math.max(start, split) - split, end - split] as const);
  return <>
    <SearchMarked text={characters.slice(0, split).join('')} ranges={head} />
    <span className="note-mark-join"><SearchMarked text={characters.slice(split).join('')} ranges={tail} /><NoteMark files={files} /></span>
  </>;
}
