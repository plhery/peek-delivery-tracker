import 'server-only';

/** The accounts a setting is open to: everyone (`*`), or the ones named by id or email. */
export interface AccountList {
  everyone: boolean;
  ids: ReadonlySet<string>;
  emails: ReadonlySet<string>;
}

const ACCOUNT_ID = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/;

/** Reads a comma-separated list of account ids, emails and `*`; anything else throws, naming the setting. */
export function accountList(value: string | undefined, name: string): AccountList {
  const listed = (value ?? '').split(',').map((entry) => entry.trim().toLowerCase()).filter(Boolean);
  const unknown = listed.find((entry) => entry !== '*' && !ACCOUNT_ID.test(entry) && !/^[^\s@]+@[^\s@]+$/.test(entry));
  if (unknown !== undefined) throw new Error(`${name} lists account ids, emails, or *`);
  return {
    everyone: listed.includes('*'),
    ids: new Set(listed.filter((entry) => ACCOUNT_ID.test(entry))),
    emails: new Set(listed.filter((entry) => entry.includes('@'))),
  };
}

export function listsAccount(list: AccountList, account: { id: string; email?: string | null }): boolean {
  return list.everyone || list.ids.has(account.id.toLowerCase()) || Boolean(account.email && list.emails.has(account.email.toLowerCase()));
}
