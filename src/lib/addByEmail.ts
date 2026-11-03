import type { ApiAddByEmailResponse } from '../generated/apiContract';
import { authenticatedFetch, type ApiAuth } from './apiClient';

const PATH = '/api/account/inbox';

async function answer(response: Response): Promise<ApiAddByEmailResponse> {
  const body = (await response.json().catch(() => null)) as (ApiAddByEmailResponse & { error?: string }) | null;
  if (!response.ok) throw new Error(body?.error ?? `Forwarding address request failed (${response.status})`);
  if (!body) throw new Error('The forwarding address service returned an empty response');
  return body;
}

/** The account's forwarding address, or null when the server doesn't offer one to this account. */
export async function readAddByEmail(auth: ApiAuth): Promise<ApiAddByEmailResponse | null> {
  const response = await authenticatedFetch(PATH, auth);
  return response.status === 404 ? null : answer(response);
}

/** Makes the address, or a new one in its place when `renew` is set. */
export async function openAddByEmail(auth: ApiAuth, renew = false): Promise<ApiAddByEmailResponse> {
  return answer(await authenticatedFetch(PATH, auth, { method: 'POST', body: JSON.stringify(renew ? { renew } : {}) }));
}

export async function closeAddByEmail(auth: ApiAuth): Promise<void> {
  const response = await authenticatedFetch(PATH, auth, { method: 'DELETE' });
  if (!response.ok) throw new Error(`Forwarding address request failed (${response.status})`);
}
