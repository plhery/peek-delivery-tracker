import { useEffect, useState, type FormEvent } from 'react';
import type { ApiAssistantConnection, ApiAssistantScope, ApiAssistantsResponse } from '../generated/apiContract';
import { useI18n } from '../i18n';
import type { ApiAuth } from '../lib/apiClient';
import { createPersonalToken, readAssistants, revokeAssistant } from '../lib/assistants';
import { localizedRelativeTime, type Translate } from '../lib/messages';
import { userErrorMessage } from '../lib/userMessages';
import { Icon } from './Icon';

const MOST_NAME_LENGTH = 100;

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
  const where = connection.kind === 'oauth' && connection.redirectHost ? `${connection.redirectHost} · ` : '';
  return <div className="settings-row assistants__item">
    <span className="assistants__what"><strong>{connection.name}</strong><small>{where}{scopeText(t, connection.scopes)} · {used}</small></span>
    <button className="assistants__revoke" type="button" disabled={working} onClick={onRevoke}
      aria-label={t('assistants.revokeNamed', { name: connection.name })}>{t(connection.kind === 'oauth' ? 'assistants.disconnect' : 'assistants.revoke')}</button>
  </div>;
}

/**
 * The assistants' page in Settings: the address to give an assistant, the ones connected, and personal tokens for
 * scripts and tools that can't sign in.
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
  const [copied, setCopied] = useState<'address' | 'token' | null>(null);
  const [name, setName] = useState('');
  const [writes, setWrites] = useState(false);
  // A new token, shown until the page closes: the server keeps only its digest.
  const [token, setToken] = useState<string | null>(null);
  const assistants = offer.connections.filter((connection) => connection.kind === 'oauth');
  const tokens = offer.connections.filter((connection) => connection.kind === 'personal');

  async function run(operation: () => Promise<void>) {
    if (working) return;
    setWorking(true); onBusy?.(true); setError(null);
    try { await operation(); }
    catch (reason) { setError(userErrorMessage(reason, t, 'assistants.failed')); }
    finally { setWorking(false); onBusy?.(false); }
  }

  async function copy(text: string, what: 'address' | 'token') {
    try { await navigator.clipboard.writeText(text); setCopied(what); }
    catch { /* It stays on screen to select by hand. */ }
  }

  const revoke = (id: string) => void run(async () => {
    await revokeAssistant(apiAuth, id);
    onChange({ ...offer, connections: offer.connections.filter((connection) => connection.id !== id) });
  });

  function create(event: FormEvent) {
    event.preventDefault();
    const named = name.trim();
    if (!named) return;
    const scopes: ApiAssistantScope[] = writes ? ['parcels:read', 'parcels:write'] : ['parcels:read'];
    void run(async () => {
      const created = await createPersonalToken(apiAuth, named, scopes);
      onChange({ ...offer, connections: [created.connection, ...offer.connections] });
      setToken(created.token); setCopied(null); setName(''); setWrites(false);
    });
  }

  return <>
    <p className="settings-description">{t('assistants.intro')}</p>
    {error && <p className="sheet__error" role="alert">{error}</p>}
    <section className="settings-group" aria-labelledby="assistants-address"><h3 className="settings-group__title" id="assistants-address">{t('assistants.address')}</h3>
      <div className="settings-box">
        <p className="add-by-email__address" translate="no">{offer.mcpUrl}</p>
        <button className="settings-row" type="button" onClick={() => void copy(offer.mcpUrl, 'address')}><Icon name={copied === 'address' ? 'check' : 'copy'} />{t(copied === 'address' ? 'inbox.copied' : 'inbox.copy')}</button>
      </div>
      <p className="add-by-email__note">{t('assistants.how')}</p>
    </section>
    <section className="settings-group" aria-labelledby="assistants-connected"><h3 className="settings-group__title" id="assistants-connected">{t('assistants.connected')}</h3>
      <div className="settings-box">
        {assistants.length
          ? assistants.map((connection) => <Connection key={connection.id} connection={connection} working={working} onRevoke={() => revoke(connection.id)} />)
          : <p className="assistants__empty">{t('assistants.none')}</p>}
      </div>
    </section>
    <section className="settings-group" aria-labelledby="assistants-tokens"><h3 className="settings-group__title" id="assistants-tokens">{t('assistants.tokens')}</h3>
      <div className="settings-box">
        {token && <div className="assistants__token">
          <p className="add-by-email__address" translate="no">{token}</p>
          <p className="assistants__token-note">{t('assistants.tokenOnce')}</p>
          <button className="settings-row" type="button" onClick={() => void copy(token, 'token')}><Icon name={copied === 'token' ? 'check' : 'copy'} />{t(copied === 'token' ? 'assistants.tokenCopied' : 'assistants.copyToken')}</button>
        </div>}
        {tokens.map((connection) => <Connection key={connection.id} connection={connection} working={working} onRevoke={() => revoke(connection.id)} />)}
        <form className="assistants__new" onSubmit={create}>
          <label className="field" htmlFor="assistants-token-name"><span>{t('assistants.tokenName')}</span>
            <input className="field__input" id="assistants-token-name" type="text" autoComplete="off" maxLength={MOST_NAME_LENGTH}
              placeholder={t('assistants.tokenPlaceholder')} value={name} onChange={(event) => setName(event.target.value)} /></label>
          <label className="assistants__writes"><input type="checkbox" checked={writes} onChange={(event) => setWrites(event.target.checked)} />
            <span>{t('assistants.tokenWrites')}</span></label>
          <button className="button button--secondary" type="submit" disabled={working || !name.trim()} aria-busy={working || undefined}>{t('assistants.createToken')}</button>
        </form>
      </div>
      <p className="add-by-email__note">{t('assistants.tokensNote')}</p>
    </section>
  </>;
}
