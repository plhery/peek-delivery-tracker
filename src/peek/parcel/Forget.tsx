import { useEffect, useRef, useState } from 'react';
import { Icon } from '../../components/Icon';
import { useI18n, type MessageKey } from '../../i18n';
import { trackAction } from '../../lib/analytics';
import { LandingIcon } from '../landing/glyphs';
import { SOURCE_URL } from '../landing/links';
import { turnOffAlert } from '../alerts';
import { forgetParcelLink, ParcelLinkError, parcelLinkErrorKey } from '../links';
import { forgetRecent } from '../recents';

/**
 * Forgets a lookup everywhere: on the server with the owner key, then on this
 * device. A link that is already gone counts as forgotten.
 */
export async function forgetParcel(linkId: string, key: string): Promise<void> {
  try {
    await forgetParcelLink(linkId, key);
  } catch (error) {
    if (!(error instanceof ParcelLinkError && error.kind === 'unavailable')) {
      trackAction('parcel-link-forget', 'error');
      throw error;
    }
  }
  forgetRecent(linkId);
  trackAction('parcel-link-forget', 'success');
}

/**
 * Takes a parcel someone shared off this device: this browser's alerts for it
 * go first, and the link keeps working for everyone else. A link that is
 * already gone has no alert left to turn off.
 */
export async function removeSharedParcel(linkId: string): Promise<void> {
  try {
    await turnOffAlert(linkId);
  } catch (error) {
    if (!(error instanceof ParcelLinkError && error.kind === 'unavailable')) {
      trackAction('parcel-link-remove', 'error');
      throw error;
    }
  }
  forgetRecent(linkId);
  trackAction('parcel-link-remove', 'success');
}

/**
 * Asks once before a parcel is forgotten for good. With `arrived`, it is the
 * owner's gentle exit after "I have it": forget the parcel now, or keep it
 * until Peek forgets it by itself.
 */
export function ForgetDialog({ onForget, onCancel, arrived }: {
  /** Forgets the parcel; a rejection keeps the dialog open with its reason. */
  onForget: () => Promise<void>;
  onCancel: () => void;
  /** The parcel has arrived; `forgetLine` says when Peek forgets it anyway. */
  arrived?: { forgetLine: string | null };
}) {
  const { t } = useI18n();
  const dialog = useRef<HTMLDialogElement>(null);
  const cancel = useRef<HTMLButtonElement>(null);
  const [working, setWorking] = useState(false);
  const [error, setError] = useState<MessageKey | null>(null);

  useEffect(() => {
    const element = dialog.current;
    if (!element) return;
    if (typeof element.showModal === 'function') element.showModal();
    else element.setAttribute('open', '');
    // Only an open dialog's buttons take focus, so autoFocus would come too early.
    cancel.current?.focus();
    return () => { if (typeof element.close === 'function' && element.open) element.close(); };
  }, []);

  async function forget() {
    if (working) return;
    setWorking(true);
    setError(null);
    try {
      await onForget();
    } catch (reason) {
      const key = parcelLinkErrorKey(reason);
      setError(key === 'error.generic' ? 'link.forget.failed' : key);
      setWorking(false);
    }
  }

  return <dialog ref={dialog} className="delete-parcel-dialog" aria-labelledby="peekp-forget-title" aria-describedby="peekp-forget-body"
    onCancel={(event) => { event.preventDefault(); if (!working) onCancel(); }}>
    <h2 id="peekp-forget-title">{t(arrived ? 'alerts.have.title' : 'link.forget.title')}</h2>
    <p id="peekp-forget-body">{arrived ? [arrived.forgetLine, t('alerts.have.body')].filter(Boolean).join(' ') : t('link.forget.body')}</p>
    {error && <p className="sheet__error" role="alert">{t(error)}</p>}
    <div className="delete-parcel-dialog__actions">
      <button type="button" className="button button--secondary" onClick={onCancel} disabled={working} ref={cancel}>{t(arrived ? 'alerts.have.keep' : 'common.cancel')}</button>
      <button type="button" className="button button--danger" onClick={() => void forget()} disabled={working}>{t(working ? 'link.forget.working' : 'link.forget.now')}</button>
    </div>
  </dialog>;
}

/**
 * The privacy promise at the foot of the page, with the way to forget the parcel at once for its owner, or to take it
 * off this device for someone it was shared with, and to archive it on this device, or restore it, while the page's
 * actions don't offer that.
 */
export function ForgetFooter({ promise, onForget, onRemove, onArchive, onRestore }: {
  /** When Peek forgets the parcel by itself; absent for a parcel shared from an account. */
  promise: string | null;
  /** Offered to the owner only. */
  onForget?: () => void;
  /** Offered to someone the parcel was shared with, whose device lists it. */
  onRemove?: () => void;
  onArchive?: () => void;
  onRestore?: () => void;
}) {
  const { t } = useI18n();
  return <footer className="peekp-footer">
    {promise && <p><Icon name="lock" />{promise}</p>}
    <p>
      {onArchive && <><button type="button" className="peekp-footer__forget" onClick={onArchive}>{t('detail.archive')}</button> · </>}
      {onRestore && <><button type="button" className="peekp-footer__forget" onClick={onRestore}>{t('detail.restore')}</button> · </>}
      {onForget && <><button type="button" className="peekp-footer__forget" onClick={onForget}>{t('link.forget.now')}</button> · </>}
      {onRemove && <><button type="button" className="peekp-footer__forget" onClick={onRemove}>{t('link.remove.now')}</button> · </>}
      <a href="/privacy.html" onClick={() => trackAction('privacy-open')}>{t('auth.privacyLink')}</a> · <a href={SOURCE_URL} target="_blank" rel="noopener noreferrer"><LandingIcon name="github" />GitHub</a>
    </p>
  </footer>;
}
