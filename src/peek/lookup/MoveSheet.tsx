import { useEffect, useRef, useState } from 'react';
import { createPortal } from 'react-dom';
import { Icon } from '../../components/Icon';
import { useI18n } from '../../i18n';
import { trackAction } from '../../lib/analytics';
import { useSheetDialog } from '../../lib/modal';
import type { RecentParcel } from '../recents';
import { moveURL } from '../transfer';

/** Past this many characters a code is too dense for a phone to read off a screen: the link is offered alone. */
export const CODE_LIMIT = 1_800;

/** The code's squares, drawn once its encoder has arrived. It comes only with this sheet. */
function useCode(text: string): boolean[][] | null {
  const [code, setCode] = useState<{ text: string; modules: boolean[][] } | null>(null);
  useEffect(() => {
    if (text.length > CODE_LIMIT) return;
    let live = true;
    void import('uqr').then(({ encode }) => {
      if (live) setCode({ text, modules: encode(text, { ecc: 'L', border: 2 }).data });
    }).catch(() => undefined);
    return () => { live = false; };
  }, [text]);
  return code?.text === text ? code.modules : null;
}

function Code({ modules }: { modules: boolean[][] }) {
  const size = modules.length;
  const path = modules.flatMap((row, y) => row.map((dark, x) => dark ? `M${x} ${y}h1v1h-1z` : '')).join('');
  return <svg className="door-move__code" viewBox={`0 0 ${size} ${size}`} aria-hidden="true" shapeRendering="crispEdges">
    <rect width={size} height={size} fill="#fff" />
    <path d={path} fill="#000" />
  </svg>;
}

/**
 * Opens the parcels on this device on another phone or computer: a code to scan, or a link to send oneself. The
 * link carries the owner keys after its `#`, so the other device can do all this one can, and the server never sees
 * them.
 */
export function MoveSheet({ recents, onClose }: { recents: readonly RecentParcel[]; onClose: () => void }) {
  const { t } = useI18n();
  const close = useRef<HTMLButtonElement>(null);
  const [dialog, dismiss] = useSheetDialog<HTMLDivElement>(true, onClose, close);
  const [url] = useState(() => moveURL(recents));
  const modules = useCode(url);
  const [copied, setCopied] = useState(false);
  const canShare = typeof navigator.share === 'function';

  async function copy() {
    try {
      await navigator.clipboard.writeText(url);
      setCopied(true);
      trackAction('parcels-move', 'success');
    } catch {
      trackAction('parcels-move', 'error');
    }
  }

  async function share() {
    try {
      await navigator.share({ url });
      trackAction('parcels-move', 'success');
    } catch { /* Closing the share sheet is not a failure. */ }
  }

  return createPortal(<div className="sheet-backdrop" onClick={dismiss}>
    <div ref={dialog} className="sheet door-move" role="dialog" aria-modal="true" aria-labelledby="door-move-title" aria-describedby="door-move-body" tabIndex={-1} onClick={(event) => event.stopPropagation()}>
      <div className="sheet__grabber" aria-hidden="true" />
      <div className="sheet__heading">
        <h2 className="sheet__title" id="door-move-title">{t('move.title')}</h2>
        <button ref={close} className="sheet__close" type="button" onClick={dismiss} aria-label={t('common.close')}><Icon name="close" /></button>
      </div>
      <p className="sheet__intro" id="door-move-body">{t(url.length > CODE_LIMIT ? 'move.link' : 'move.scan')}</p>
      {url.length <= CODE_LIMIT && <div className="door-move__frame">{modules && <Code modules={modules} />}</div>}
      <p className="door-move__private"><Icon name="lock" />{t('move.private')}</p>
      <div className="door-move__actions">
        <button type="button" className="button button--primary" onClick={() => void copy()} aria-live="polite">
          <Icon name={copied ? 'check' : 'copy'} />{t(copied ? 'link.copied' : 'friends.copyLink')}
        </button>
        {canShare && <button type="button" className="button button--secondary" onClick={() => void share()}>
          <Icon name="share" />{t('link.share')}
        </button>}
      </div>
    </div>
  </div>, document.body);
}
