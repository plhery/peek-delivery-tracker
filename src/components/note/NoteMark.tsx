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
 */
export function NameWithMark({ name, files = false }: { name: string; files?: boolean }) {
  const characters = Array.from(name.trimEnd());
  const lastSpace = Math.max(characters.lastIndexOf(' '), characters.lastIndexOf('\u00a0'));
  const split = Math.max(lastSpace + 1, characters.length - 8);
  return <>
    {characters.slice(0, split).join('')}
    <span className="note-mark-join">{characters.slice(split).join('')}<NoteMark files={files} /></span>
  </>;
}
