import { useRef, useState } from 'react';
import { createPortal } from 'react-dom';
import { useI18n } from '../i18n';
import { useSheetDialog } from '../lib/modal';
import { Icon } from './Icon';
import { PreferenceSettings } from './Preferences';
import './Settings.css';

/**
 * Settings for a visitor without an account: appearance, time format, distance units and the new-update mark,
 * kept in this browser.
 */
export function GuestSettings({ className = '' }: { className?: string }) {
  const { t } = useI18n();
  const [open, setOpen] = useState(false);
  const closeButton = useRef<HTMLButtonElement>(null);
  const [dialog, close] = useSheetDialog<HTMLDivElement>(open, () => setOpen(false), closeButton);
  return <>
    <button type="button" className={`icon-button guest-settings-trigger ${className}`.trim()} aria-label={t('settings.title')}
      aria-haspopup="dialog" onClick={() => setOpen(true)}><Icon name="settings" /></button>
    {open && createPortal(<div className="sheet-backdrop" onClick={close}>
      <div ref={dialog} className="sheet settings-sheet refined-settings guest-settings" role="dialog" aria-modal="true"
        aria-labelledby="guest-settings-title" tabIndex={-1} onClick={(event) => event.stopPropagation()}>
        <div className="sheet__grabber" aria-hidden="true" />
        <div className="sheet__heading"><h2 className="sheet__title" id="guest-settings-title">{t('settings.title')}</h2>
          <button ref={closeButton} className="sheet__close" type="button" onClick={close} aria-label={t('common.close')}><Icon name="close" /></button></div>
        <PreferenceSettings />
        <p className="settings-description guest-settings__kept">{t('door.recents.kept')}</p>
      </div>
    </div>, document.body)}
  </>;
}
