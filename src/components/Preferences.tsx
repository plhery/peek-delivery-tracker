import { LanguageControl, useI18n } from '../i18n';
import { trackAction } from '../lib/analytics';
import { useAppearance, type Appearance } from '../lib/appearance';
import type { ApiAuth } from '../lib/apiClient';
import { useMarkNewUpdatesChoice } from '../lib/newUpdate';
import { useShowPeekAiChoice } from '../lib/peekAi';
import { clockPreference, distancePreference, weightPreference } from '../lib/units';
import { PeekAiSparkle } from './PeekAi';
import './Settings.css';

/**
 * The reader’s preferences: appearance, time format, distance and weight units, the new-update mark and Peek AI, kept in
 * this browser. With `apiAuth`, the mark's and Peek AI's choices follow the account to its other devices; with `language`,
 * the language too.
 */
export function PreferenceSettings({ apiAuth, language = false }: { apiAuth?: ApiAuth; language?: boolean }) {
  const { t, clock, setClock, distance, setDistance, weight, setWeight, automatic } = useI18n();
  const [appearance, setAppearance] = useAppearance();
  const [markNewUpdates, setMarkNewUpdates] = useMarkNewUpdatesChoice(apiAuth);
  const [showPeekAi, setShowPeekAi] = useShowPeekAiChoice(apiAuth);
  return <section className="settings-group" aria-labelledby="settings-preferences"><h3 className="settings-group__title" id="settings-preferences">{t('settings.preferences')}</h3>
    <div className="settings-box"><fieldset className="settings-appearance"><legend>{t('native.appearance.title')}</legend>
      <div>{(['system', 'light', 'dark'] as Appearance[]).map((option) => <button type="button" key={option} aria-pressed={appearance === option} onClick={() => { setAppearance(option); trackAction('appearance-change'); }}><span className={`settings-mini-screen settings-mini-screen--${option}`} aria-hidden="true"><i /><i /></span>{t(`native.appearance.${option}`)}</button>)}</div>
    </fieldset>
      {language && <LanguageControl className="language-control--account" />}
      <label className="language-control language-control--account">
        <span>{t('settings.timeFormat')}</span>
        <select value={clock} onChange={(event) => setClock(clockPreference(event.target.value))}>
          <option value="auto">{t('settings.automatic', { value: t(automatic.use24HourTime ? 'settings.clock24' : 'settings.clock12') })}</option>
          <option value="24">{t('settings.clock24')}</option>
          <option value="12">{t('settings.clock12')}</option>
        </select>
      </label>
      <label className="language-control language-control--account">
        <span>{t('settings.distanceUnits')}</span>
        <select value={distance} onChange={(event) => setDistance(distancePreference(event.target.value))}>
          <option value="auto">{t('settings.automatic', { value: t(automatic.distanceUnit === 'mi' ? 'settings.milesShort' : 'settings.kilometersShort') })}</option>
          <option value="km">{t('settings.kilometers')}</option>
          <option value="mi">{t('settings.miles')}</option>
        </select>
      </label>
      <label className="language-control language-control--account">
        <span>{t('settings.weightUnits')}</span>
        <select value={weight} onChange={(event) => setWeight(weightPreference(event.target.value))}>
          <option value="auto">{t('settings.automatic', { value: t(automatic.weightUnit === 'lb' ? 'settings.poundsShort' : 'settings.kilogramsShort') })}</option>
          <option value="kg">{t('settings.kilograms')}</option>
          <option value="lb">{t('settings.pounds')}</option>
        </select>
      </label>
      <button className="settings-row settings-mark" type="button" role="switch" aria-checked={markNewUpdates} aria-labelledby="settings-mark-label" aria-describedby="settings-mark-hint"
        onClick={() => setMarkNewUpdates(!markNewUpdates)}>
        <span className="settings-mark__text"><span id="settings-mark-label">{t('settings.markNewUpdates')}</span><small id="settings-mark-hint">{t('settings.markNewUpdatesHint')}</small></span>
        <span className="settings-switch" aria-hidden="true" />
      </button>
      <button className="settings-row settings-mark" type="button" role="switch" aria-checked={showPeekAi} aria-labelledby="settings-peek-ai-label" aria-describedby="settings-peek-ai-hint"
        onClick={() => { setShowPeekAi(!showPeekAi); trackAction(showPeekAi ? 'peek-ai-off' : 'peek-ai-on'); }}>
        <span className="settings-mark__text"><span id="settings-peek-ai-label" className="settings-peek-ai"><PeekAiSparkle />{t('peekAi.name')}</span><small id="settings-peek-ai-hint">{t('settings.peekAiHint')}</small></span>
        <span className="settings-switch" aria-hidden="true" />
      </button>
    </div>
  </section>;
}
