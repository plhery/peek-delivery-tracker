import { useEffect, useState } from 'react';
import { assistantsPath } from '../assistants/paths';
import { ASSISTANT_SETUPS, setupSteps, SetupText } from '../assistants/setups';
import type { ApiAssistantConnection, ApiAssistantScope, ApiAssistantsResponse } from '../generated/apiContract';
import { useI18n } from '../i18n';
import type { ApiAuth } from '../lib/apiClient';
import { readAssistants, revokeAssistant } from '../lib/assistants';
import { localizedRelativeTime, type MessageKey, type Translate } from '../lib/messages';
import { userErrorMessage } from '../lib/userMessages';
import { Icon } from './Icon';
import { PipPerch } from './PipPerch';

/**
 * Each assistant's steps to add Peek, folded under its name with what to do where they don't apply, then a line for any
 * other assistant. Signing in to Peek is the step before the last: the assistant asks for it when the connector is added
 * or first used. A command to type holds `address`, Peek's address for assistants.
 */
function Setups({ address }: { address: string }) {
  const { t } = useI18n();
  return <section className="settings-group" aria-labelledby="assistants-setup"><h3 className="settings-group__title" id="assistants-setup">{t('assistants.setup')}</h3>
    <div className="settings-box">
      {ASSISTANT_SETUPS.map(({ name, who, steps, fallback }) => {
        const lines = setupSteps(t, steps);
        return <details key={name} className="assistants__setup">
          <summary className="settings-row"><span translate="no">{name}</span><span className="settings-chevron" aria-hidden="true">›</span></summary>
          <div className="assistants__setup-body">
            <p className="assistants__who"><SetupText text={t(who[0])} labels={who[1]} address={address} /></p>
            <ol className="add-by-email__steps">{lines.map(([key, labels]) => <li key={key}><SetupText text={t(key)} labels={labels} address={address} /></li>)}</ol>
            {fallback && <p className="assistants__who assistants__fallback"><SetupText text={t(fallback[0])} labels={fallback[1]} address={address} /></p>}
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

/** What a connection may do, by whether it sees, adds, and edits and archives parcels. */
const SCOPE_TEXT: Record<string, MessageKey> = {
  read: 'assistants.scope.read',
  write: 'assistants.scope.write',
  edit: 'assistants.scope.edit',
  'read write': 'assistants.scope.both',
  'read edit': 'assistants.scope.readEdit',
  'write edit': 'assistants.scope.writeEdit',
  'read write edit': 'assistants.scope.all',
};

function scopeText(t: Translate, scopes: readonly ApiAssistantScope[]): string {
  const kinds = (['read', 'write', 'edit'] as const).filter((kind) => scopes.includes(`parcels:${kind}`));
  return t(SCOPE_TEXT[kinds.join(' ')] ?? 'assistants.scope.read');
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
 * The assistants' page in Settings: the address to give an assistant, how to add Peek to each one, what to ask one, and
 * the ones connected. Its first lines lead to the public page on connecting an assistant, in a tab of its own, which
 * says the same and more. Personal tokens have no screen: the server makes none while they are off.
 */
export function AssistantsPage({ apiAuth, offer, onChange, onBusy }: {
  apiAuth: ApiAuth;
  offer: ApiAssistantsResponse;
  onChange: (next: ApiAssistantsResponse) => void;
  onBusy?: (busy: boolean) => void;
}) {
  const { t, locale } = useI18n();
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
    <p className="settings-description">{t('assistants.intro')} <a href={assistantsPath(locale)} target="_blank" rel="noopener">{t('assistants.page.link')}</a></p>
    {error && <p className="sheet__error" role="alert">{error}</p>}
    <section className="settings-group" aria-labelledby="assistants-address"><h3 className="settings-group__title" id="assistants-address">{t('assistants.address')}</h3>
      <PipPerch happy={copied}><div className="settings-box">
        <p className="add-by-email__address" translate="no">{offer.mcpUrl}</p>
        <button className="settings-row" type="button" onClick={() => void copy()}><Icon name={copied ? 'check' : 'copy'} />{t(copied ? 'inbox.copied' : 'inbox.copy')}</button>
      </div></PipPerch>
    </section>
    <Setups address={offer.mcpUrl} />
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
