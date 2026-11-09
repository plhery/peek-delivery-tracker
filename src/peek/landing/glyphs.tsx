import { useI18n } from '../../i18n';

const drawings = {
  github: 'M9 19c-5 1.5-5-2.5-7-3m14 6v-3.87a3.37 3.37 0 0 0-.94-2.61c3.14-.35 6.44-1.54 6.44-7A5.44 5.44 0 0 0 20 4.77 5.07 5.07 0 0 0 19.91 1S18.73.65 16 2.48a13.38 13.38 0 0 0-7 0C6.27.65 5.09 1 5.09 1A5.07 5.07 0 0 0 5 4.77a5.44 5.44 0 0 0-1.5 3.78c0 5.42 3.3 6.61 6.44 7A3.37 3.37 0 0 0 9 18.13V22',
  forgets: 'M3 3l18 18M10.6 5.1Q11.3 5 12 5c5 0 9 4.5 10 7-.4 1-1.2 2.3-2.4 3.5M6.6 6.6C4.4 8 2.9 10.2 2 12c1 2.5 5 7 10 7 1.9 0 3.6-.6 5-1.5M9.9 9.9a3 3 0 0 0 4.2 4.2',
} as const;

const APPLE = 'M12.152 6.896c-.948 0-2.415-1.078-3.96-1.04-2.04.027-3.91 1.183-4.961 3.014-2.117 3.675-.546 9.103 1.519 12.09 1.013 1.454 2.208 3.09 3.792 3.039 1.52-.065 2.09-.987 3.935-.987 1.831 0 2.35.987 3.96.948 1.637-.026 2.676-1.48 3.676-2.948 1.156-1.688 1.636-3.325 1.662-3.415-.039-.013-3.182-1.221-3.22-4.857-.026-3.04 2.48-4.494 2.597-4.559-1.429-2.09-3.623-2.324-4.39-2.376-2-.156-3.675 1.09-4.61 1.09zM15.53 3.83c.843-1.012 1.4-2.427 1.245-3.83-1.207.052-2.662.805-3.532 1.818-.78.896-1.454 2.338-1.273 3.714 1.338.104 2.715-.688 3.559-1.701';
/** The Android robot's head, drawn after Google's robot (CC BY 3.0, credited by `AndroidRobotCredit`): its eyes are holes. */
const ANDROID_HEAD = 'M3 18.5a9 9 0 0 1 18 0zM8.6 13.85a1.15 1.15 0 1 0 0 2.3 1.15 1.15 0 1 0 0-2.3zM15.4 13.85a1.15 1.15 0 1 0 0 2.3 1.15 1.15 0 1 0 0-2.3z';
const ANDROID_ANTENNAS = 'M7.5 11.4 5.5 8M16.5 11.4l2-3.4';

/** The X logo: a filled mark, unlike the line drawings below. */
export function XLogo() {
  return <svg className="landing-x" width="24" height="24" viewBox="0 0 24 24" aria-hidden="true">
    <path d="M18.244 2.25h3.308l-7.227 8.26 8.502 11.24H16.17l-5.214-6.817L4.99 21.75H1.68l7.73-8.835L1.254 2.25H8.08l4.713 6.231zm-1.161 17.52h1.833L7.084 4.126H5.117z" />
  </svg>;
}

/** The landing's own line drawings, in the app's icon style. */
export function LandingIcon({ name }: { name: keyof typeof drawings }) {
  return <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.65" strokeLinecap="round" strokeLinejoin="round" aria-hidden="true">
    <path d={drawings[name]} />
  </svg>;
}

/** The apps' platforms on their buttons: Apple's logo and the Android robot's head, filled marks in the button's ink. */
export function PlatformLogo({ name }: { name: 'apple' | 'android' }) {
  return <svg width="24" height="24" viewBox="0 0 24 24" aria-hidden="true">
    {name === 'apple'
      ? <path fill="currentColor" d={APPLE} />
      : <><path fill="currentColor" fillRule="evenodd" d={ANDROID_HEAD} /><path d={ANDROID_ANTENNAS} fill="none" stroke="currentColor" strokeWidth="1.6" strokeLinecap="round" /></>}
  </svg>;
}

/** What the license of Google's Android robot asks of a page that shows a drawing made after it. */
export function AndroidRobotCredit({ className }: { className: string }) {
  const { t } = useI18n();
  const [before, after] = t('landing.footer.androidCredit').split('{{license}}');
  return <p className={className}>
    {before}<a href="https://creativecommons.org/licenses/by/3.0/" target="_blank" rel="noopener noreferrer">CC BY 3.0</a>{after}
  </p>;
}
