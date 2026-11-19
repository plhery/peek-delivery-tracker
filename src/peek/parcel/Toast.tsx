import { useSyncExternalStore, type ReactNode } from 'react';
import { ToastMark } from '../../components/ToastMark';
import { useI18n, type MessageKey } from '../../i18n';
import './Toast.css';

/** A short word at the edge of the screen. It is a status: read out, never focused. */
export function Toast({ mark, kind = 'success', children, action, place = 'bottom', tone = false }: {
  mark?: ReactNode;
  /** The standard mark, when the toast has no mark of its own. */
  kind?: 'success' | 'archive';
  children: ReactNode;
  action?: ReactNode;
  place?: 'top' | 'bottom';
  /** The mark takes the parcel's own colours. */
  tone?: boolean;
}) {
  return <div className={`peekp-toast peekp-toast--${place}`} role="status">
    {mark !== undefined ? <span className={`peekp-toast__mark${tone ? ' peekp-toast__mark--tone' : ''}`} aria-hidden="true">{mark}</span> : <ToastMark kind={kind} />}
    <span className="peekp-toast__text">{children}</span>
    {action}
  </div>;
}

interface Notice { id: number; text: MessageKey; values?: Record<string, string | number>; kind?: 'success' | 'archive' }

const NOTICE_MS = 4_000;
const listeners = new Set<() => void>();
let notice: Notice | null = null;
let serial = 0;
let ending: ReturnType<typeof setTimeout> | undefined;

function show(next: Notice | null) {
  notice = next;
  // A notice lasts its few seconds whether a screen shows it or not: it never turns up later, out of its moment.
  clearTimeout(ending);
  if (next) ending = setTimeout(() => show(null), NOTICE_MS);
  for (const listener of [...listeners]) listener();
}

/** Says something that outlives the screen it happened on: a parcel forgotten, then the front door. */
export function announceNotice(text: MessageKey, values?: Notice['values'], kind?: Notice['kind']): void {
  show({ id: ++serial, text, values, kind });
}

function subscribe(listener: () => void) {
  listeners.add(listener);
  return () => { listeners.delete(listener); };
}

/** Shows the notice wherever the visitor is now, for a few seconds. */
export function NoticeToast({ place }: { place?: 'top' | 'bottom' }) {
  const { t } = useI18n();
  const current = useSyncExternalStore(subscribe, () => notice, () => null);
  return current ? <Toast key={current.id} kind={current.kind} place={place}>{t(current.text, current.values)}</Toast> : null;
}
