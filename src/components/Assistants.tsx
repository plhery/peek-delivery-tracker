import { Fragment, useEffect, useState } from 'react';
import type { ApiAssistantConnection, ApiAssistantScope, ApiAssistantsResponse } from '../generated/apiContract';
import { useI18n } from '../i18n';
import type { ApiAuth } from '../lib/apiClient';
import { readAssistants, revokeAssistant } from '../lib/assistants';
import { localizedRelativeTime, type MessageKey, type Translate } from '../lib/messages';
import { userErrorMessage } from '../lib/userMessages';
import { Icon } from './Icon';

/** A line of a setup: its text, and the names of the assistant's menus and buttons its `{{…}}` stand for. */
type SetupLine = readonly [MessageKey, Readonly<Record<string, string>>?];

/**
 * How to add Peek to each assistant, from its own help pages: Anthropic's "Get started with custom connectors using
 * remote MCP", OpenAI's "Add custom MCP server" and "Connect and test your plugin", and Google's "Connect & manage
 * custom apps for Gemini Apps". Their menus and buttons are named in English, as those pages give them; `who` says
 * which plans and places can. Peek's own sign-in step ends each.
 */
const SETUPS: ReadonlyArray<{ name: string; who: SetupLine; steps: readonly SetupLine[] }> = [
  {
    name: 'Claude',
    who: ['assistants.setup.claude.who', { connect: 'Connect', connectors: 'Customize › Connectors' }],
    steps: [
      ['assistants.setup.claude.open', { connectors: 'Customize › Connectors', add: '+ Add', custom: 'Add custom connector' }],
      ['assistants.setup.claude.add', { continue: 'Continue', add: 'Add' }],
      ['assistants.setup.claude.use', { plus: '+', connectors: 'Connectors' }],
    ],
  },
  {
    name: 'ChatGPT',
    who: ['assistants.setup.chatgpt.who'],
    steps: [
      ['assistants.setup.chatgpt.open', { plugins: 'chatgpt.com/plugins', plus: '+', custom: 'Add custom MCP server' }],
      ['assistants.setup.chatgpt.add', { oauth: 'OAuth', accept: 'I understand and want to continue', create: 'Create as a plugin' }],
      ['assistants.setup.chatgpt.use', { plus: '+' }],
    ],
  },
  {
    name: 'Gemini',
    who: ['assistants.setup.gemini.who'],
    steps: [
      ['assistants.setup.gemini.open', { settings: 'Settings', apps: 'Connected Apps' }],
      ['assistants.setup.gemini.add', { custom: 'Custom apps', next: 'Next' }],
      ['assistants.setup.gemini.use'],
    ],
  },
];

/** A setup line's text, with the assistant's own names in bold where the translation puts `{{…}}`. */
function SetupText({ text, labels = {} }: { text: string; labels?: Readonly<Record<string, string>> }) {
  return <>{text.split(/(\{\{\w+\}\})/).map((piece, index) => {
    const label = labels[piece.slice(2, -2)];
    return piece.startsWith('{{') && label !== undefined
      ? <strong key={index} className="add-by-email__ui" translate="no">{label}</strong>
      : <Fragment key={index}>{piece}</Fragment>;
  })}</>;
}

/**
 * Each assistant's steps to add Peek, folded under its name, then a line for any other assistant. Signing in to Peek is
 * the step before the last: the assistant asks for it when the connector is added or first used.
 */
function Setups() {
  const { t } = useI18n();
  const signIn: SetupLine = ['assistants.setup.signIn', { allow: t('assistants.consent.allow') }];
  return <section className="settings-group" aria-labelledby="assistants-setup"><h3 className="settings-group__title" id="assistants-setup">{t('assistants.setup')}</h3>
    <div className="settings-box">
      {SETUPS.map(({ name, who, steps }) => {
        const lines = [...steps.slice(0, -1), signIn, ...steps.slice(-1)];
        return <details key={name} className="assistants__setup">
          <summary className="settings-row"><span translate="no">{name}</span><span className="settings-chevron" aria-hidden="true">›</span></summary>
          <div className="assistants__setup-body">
            <p className="assistants__who"><SetupText text={t(who[0])} labels={who[1]} /></p>
            <ol className="add-by-email__steps">{lines.map(([key, labels]) => <li key={key}><SetupText text={t(key)} labels={labels} /></li>)}</ol>
          </div>
        </details>;
      })}
    </div>
    <p className="add-by-email__note">{t('assistants.how')}</p>
  </section>;
}

/** What to ask a connected assistant: first, to add the tracking numbers in the user's emails. */
function Uses() {
  const { t } = useI18n();
  return <section className="settings-group" aria-labelledby="assistants-uses"><h3 className="settings-group__title" id="assistants-uses">{t('assistants.uses')}</h3>
    <ul className="settings-box assistants__uses">
      <li><strong>{t('assistants.uses.email')}</strong> {t('assistants.uses.emailNote')}</li>
      <li>{t('assistants.uses.week')}</li>
      <li>{t('assistants.uses.where')}</li>
      <li>{t('assistants.uses.archive')}</li>
    </ul>
  </section>;
}

/**
 * The account's assistants, read each time Settings opens. Null hides the row: the server answers 404 until the
 * feature is set up and open to the account.
 */
export function useAssistants(apiAuth: ApiAuth | undefined, open: boolean) {
  const [offer, setOffer] = useState<{ userId: string; value: ApiAssistantsResponse | null } | null>(null);
  useEffect(() => {
    if (!open || !apiAuth) return;
    let current = true;
    void readAssistants(apiAuth).then((value) => { if (current) setOffer({ userId: apiAuth.userId, value }); }, () => undefined);
    return () => { current = false; };
  }, [apiAuth, open]);
  const value = apiAuth && offer?.userId === apiAuth.userId ? offer.value : null;
  const update = (next: ApiAssistantsResponse) => { if (apiAuth) setOffer({ userId: apiAuth.userId, value: next }); };
  return [value, update] as const;
}

function scopeText(t: Translate, scopes: readonly ApiAssistantScope[]): string {
  const reads = scopes.includes('parcels:read');
  const writes = scopes.includes('parcels:write');
  return t(reads && writes ? 'assistants.scope.both' : writes ? 'assistants.scope.write' : 'assistants.scope.read');
}

function Connection({ connection, working, onRevoke }: {
  connection: ApiAssistantConnection;
  working: boolean;
  onRevoke: () => void;
}) {
  const { t, languageTag } = useI18n();
  const used = connection.lastUsedAt
    ? t('assistants.lastUsed', { time: localizedRelativeTime(connection.lastUsedAt, t, languageTag) })
    : t('assistants.neverUsed');
  const where = connection.redirectHost ? `${connection.redirectHost} · ` : '';
  return <div className="settings-row assistants__item">
    <span className="assistants__what"><strong>{connection.name}</strong><small>{where}{scopeText(t, connection.scopes)} · {used}</small></span>
    <button className="assistants__revoke" type="button" disabled={working} onClick={onRevoke}
      aria-label={t('assistants.revokeNamed', { name: connection.name })}>{t('assistants.disconnect')}</button>
  </div>;
}

/**
 * The assistants' page in Settings: the address to give an assistant, how to add it to Claude, ChatGPT and Gemini, what
 * to ask one, and the ones connected. Personal tokens have no screen: the server makes none while they are off.
 */
export function AssistantsPage({ apiAuth, offer, onChange, onBusy }: {
  apiAuth: ApiAuth;
  offer: ApiAssistantsResponse;
  onChange: (next: ApiAssistantsResponse) => void;
  onBusy?: (busy: boolean) => void;
}) {
  const { t } = useI18n();
  const [working, setWorking] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [copied, setCopied] = useState(false);
  const assistants = offer.connections.filter((connection) => connection.kind === 'oauth');

  async function copy() {
    try { await navigator.clipboard.writeText(offer.mcpUrl); setCopied(true); }
    catch { /* It stays on screen to select by hand. */ }
  }

  const revoke = (id: string) => void (async () => {
    if (working) return;
    setWorking(true); onBusy?.(true); setError(null);
    try {
      await revokeAssistant(apiAuth, id);
      onChange({ ...offer, connections: offer.connections.filter((connection) => connection.id !== id) });
    } catch (reason) {
      setError(userErrorMessage(reason, t, 'assistants.failed'));
    } finally {
      setWorking(false); onBusy?.(false);
    }
  })();

  return <>
    <p className="settings-description">{t('assistants.intro')}</p>
    {error && <p className="sheet__error" role="alert">{error}</p>}
    <section className="settings-group" aria-labelledby="assistants-address"><h3 className="settings-group__title" id="assistants-address">{t('assistants.address')}</h3>
      <div className="settings-box">
        <p className="add-by-email__address" translate="no">{offer.mcpUrl}</p>
        <button className="settings-row" type="button" onClick={() => void copy()}><Icon name={copied ? 'check' : 'copy'} />{t(copied ? 'inbox.copied' : 'inbox.copy')}</button>
      </div>
    </section>
    <Setups />
    <Uses />
    <section className="settings-group" aria-labelledby="assistants-connected"><h3 className="settings-group__title" id="assistants-connected">{t('assistants.connected')}</h3>
      <div className="settings-box">
        {assistants.length
          ? assistants.map((connection) => <Connection key={connection.id} connection={connection} working={working} onRevoke={() => revoke(connection.id)} />)
          : <p className="assistants__empty">{t('assistants.none')}</p>}
      </div>
    </section>
  </>;
}
