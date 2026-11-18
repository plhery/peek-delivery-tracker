import { Fragment, type ReactNode } from 'react';
import type { MessageKey, Translate } from '../lib/messages';
import { ASSISTANT_SETUPS, setupSteps, SetupText } from './setups';

/** A text with each `{{…}}` it names replaced by its piece: a link, or the name of a menu or button in bold. */
function Filled({ text, pieces }: { text: string; pieces: Readonly<Record<string, ReactNode>> }) {
  return <>{text.split(/(\{\{\w+\}\})/).map((part, index) => {
    const name = part.slice(2, -2);
    return <Fragment key={index}>{part.startsWith('{{') && Object.hasOwn(pieces, name) ? pieces[name] : part}</Fragment>;
  })}</>;
}

/** The page's sections in order, each at an address of its own that stays the same in every language. */
const SECTIONS = {
  can: 'assistants.page.can',
  address: 'assistants.address',
  setup: 'assistants.setup',
  ask: 'assistants.uses',
  shares: 'assistants.page.shares',
  disconnect: 'assistants.page.disconnect',
  troubleshooting: 'assistants.page.trouble',
} as const satisfies Record<string, MessageKey>;

/** What goes wrong most often, and what to do about it. */
const TROUBLES: ReadonlyArray<readonly [problem: MessageKey, answer: MessageKey]> = [
  ['assistants.page.signIn', 'assistants.page.signInBody'],
  ['assistants.consent.invalidTitle', 'assistants.consent.invalidBody'],
  ['assistants.page.stopped', 'assistants.page.stoppedBody'],
  ['assistants.page.limit', 'assistants.page.limitBody'],
  ['assistants.page.none', 'assistants.page.noneBody'],
  ['assistants.page.menus', 'assistants.page.menusBody'],
];

/**
 * How to connect an assistant to Peek, for anyone: what it can do, the address to give it, each assistant's steps as
 * Settings gives them, what to ask it, what Peek shares, how to disconnect it and what to do when something goes wrong.
 * `mcpUrl` is the address assistants connect to, on the site's canonical origin.
 */
export function AssistantsGuide({ t, mcpUrl }: { t: Translate; mcpUrl: string }) {
  const place = `${t('settings.title')} › ${t('assistants.row')}`;
  const heading = (id: keyof typeof SECTIONS) => <h2 id={id}>{t(SECTIONS[id])}</h2>;
  return <article className="guide assistants-guide">
    <header className="guide__header">
      <p className="guide__crumb">{t('assistants.row')}<span className="guide__badge">{t('assistants.experimental')}</span></p>
      <h1>{t('assistants.page.title')}</h1>
    </header>
    <div className="guide-body guide-lead"><p>{t('assistants.intro')}</p></div>
    <div className="guide__columns">
      <nav className="guide-contents" aria-labelledby="guide-contents">
        <strong id="guide-contents">{t('guides.contents')}</strong>
        <ol>{Object.entries(SECTIONS).map(([id, key]) => <li key={id}><a href={`#${id}`}>{t(key)}</a></li>)}</ol>
      </nav>
      <div className="guide-body">
        {heading('can')}
        <ul>
          <li>{t('assistants.consent.read')}</li>
          <li>{t('assistants.consent.write')}</li>
          <li>{t('assistants.consent.edit')}</li>
        </ul>
        <p>{t('assistants.page.choose')}</p>
        <p>{t('assistants.page.limits')}</p>

        {heading('address')}
        <p className="assistants-address" translate="no"><code>{mcpUrl}</code></p>
        <p>{t('assistants.page.account')}</p>

        {heading('setup')}
        {ASSISTANT_SETUPS.map(({ name, who, steps, fallback }) => <Fragment key={name}>
          <h3 translate="no">{name}</h3>
          <p><SetupText text={t(who[0])} labels={who[1]} /></p>
          <ol>{setupSteps(t, steps).map(([key, labels]) => <li key={key}><SetupText text={t(key)} labels={labels} /></li>)}</ol>
          {fallback && <p><SetupText text={t(fallback[0])} labels={fallback[1]} /></p>}
        </Fragment>)}
        <p>{t('assistants.how')}</p>

        {heading('ask')}
        <ul>
          <li><strong>{t('assistants.uses.email')}</strong> {t('assistants.uses.emailNote')}</li>
          <li>{t('assistants.uses.week')}</li>
          <li>{t('assistants.uses.where')}</li>
          <li>{t('assistants.uses.archive')}</li>
        </ul>

        {heading('shares')}
        <p>{t('assistants.page.sharesBody')}</p>
        <p><Filled text={t('assistants.page.privacy')} pieces={{ privacy: <a href="/privacy.html">{t('assistants.page.privacyLink')}</a> }} /></p>

        {heading('disconnect')}
        <p><Filled text={t('assistants.page.disconnectBody')} pieces={{ place: <strong>{place}</strong>, disconnect: <strong>{t('assistants.disconnect')}</strong> }} /></p>

        {heading('troubleshooting')}
        {TROUBLES.map(([problem, answer]) => <Fragment key={problem}>
          <h3>{t(problem)}</h3>
          <p><Filled text={t(answer)} pieces={{ place: <strong>{place}</strong>, allow: <strong>{t('assistants.consent.allow')}</strong> }} /></p>
        </Fragment>)}
        <p><Filled text={t('assistants.page.help')} pieces={{ help: <a href="/support.html">{t('assistants.page.helpLink')}</a> }} /></p>
      </div>
    </div>
  </article>;
}
