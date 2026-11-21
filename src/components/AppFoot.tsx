import { useI18n } from '../i18n';
import { trackAction } from '../lib/analytics';
import { LANDING_PATH } from '../lib/experience';
import { LandingIcon } from '../peek/landing/glyphs';
import { followInPlace } from '../peek/landing/links';
import { useSite } from '../SiteContext';
import { PeekMark } from './PeekMark';

/**
 * The foot of the deliveries: the name, then the ways out of the app that the
 * landing's own foot has, led by the landing itself.
 */
export function AppFoot({ onOpenLanding }: { onOpenLanding: () => void }) {
  const { t } = useI18n();
  const { privacy, source } = useSite();
  return <footer className="app-foot">
    <span className="app-foot__name"><PeekMark size={18} />{t('app.title')} · {t('app.tagline')}</span>
    <a href={LANDING_PATH} onClick={(event) => followInPlace(event, onOpenLanding)}>{t('app.homePage')}</a>
    {privacy && <a href={privacy} onClick={() => trackAction('privacy-open')}>{t('auth.privacyLink')}</a>}
    <a href={source} target="_blank" rel="noopener noreferrer"><LandingIcon name="github" />GitHub</a>
  </footer>;
}
