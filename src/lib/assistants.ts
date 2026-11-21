import type {
  ApiAssistantConsentRequest,
  ApiAssistantConsentResponse,
  ApiAssistantsResponse,
} from '../generated/apiContract';
import { authenticatedFetch, type ApiAuth } from './apiClient';

const PATH = '/api/account/assistants';

/** A refused request, with the status that tells why. */
export class AssistantRequestError extends Error {
  constructor(readonly status: number, message: string) {
    super(message);
  }
}

async function answer<T>(response: Response): Promise<T> {
  const body = (await response.json().catch(() => null)) as (T & { error?: string }) | null;
  if (!response.ok) throw new AssistantRequestError(response.status, body?.error ?? `Assistants request failed (${response.status})`);
  if (!body) throw new Error('The assistants service returned an empty response');
  return body;
}

/** The account's connections, or null when the server connects no assistant to this account. */
export async function readAssistants(auth: ApiAuth): Promise<ApiAssistantsResponse | null> {
  const response = await authenticatedFetch(PATH, auth);
  return response.status === 404 ? null : answer(response);
}

/** Switches the notification of each parcel an assistant adds on or off, and answers the connections again. */
export async function setAssistantsNotify(auth: ApiAuth, notifyAdded: boolean): Promise<ApiAssistantsResponse> {
  return answer(await authenticatedFetch(PATH, auth, { method: 'PATCH', body: JSON.stringify({ notifyAdded }) }));
}

/** Disconnects an assistant; one already gone counts as done. */
export async function revokeAssistant(auth: ApiAuth, id: string): Promise<void> {
  const response = await authenticatedFetch(`${PATH}/${encodeURIComponent(id)}`, auth, { method: 'DELETE' });
  if (!response.ok && response.status !== 404) throw new AssistantRequestError(response.status, `Assistants request failed (${response.status})`);
}

/** The owner's answer to an assistant's request: where the browser goes next. */
export async function answerAssistantConsent(auth: ApiAuth, request: ApiAssistantConsentRequest): Promise<string> {
  const body = await answer<ApiAssistantConsentResponse>(await authenticatedFetch(`${PATH}/consent`, auth, {
    method: 'POST',
    body: JSON.stringify(request),
  }));
  return body.redirect;
}
