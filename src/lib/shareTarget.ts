export interface SharedParcelInput {
  label: string;
  trackingInput: string;
}

/**
 * Why shared content never reached the app. The service worker cannot know the
 * reader's language, so it names the reason in the app's address and the app
 * says it.
 */
export type ShareFailure = 'too-large' | 'failed';

/** What was shared to the installed app: the text to add, or why there is none. */
export type SharedParcel = { input: SharedParcelInput } | { failure: ShareFailure };

const SHARE_MARKER = 'share-target';
/** Where the service worker keeps each share's draft, under the draft's own one-use id. */
export const SHARE_DRAFTS = '/share-target/draft/';
const DRAFT_ID = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/;
const FAILURES: readonly string[] = ['too-large', 'failed'] satisfies ShareFailure[];

/** Whether a text is a draft's id as the service worker makes one: random, and never the shared content. */
export function isShareDraftId(value: string): boolean {
  return DRAFT_ID.test(value);
}

/** The draft's address in the service worker, which hands it out once. */
export function shareDraftAddress(id: string): string {
  return `${SHARE_DRAFTS}${id}`;
}

type ShareMarker = { draft: string } | { failure: ShareFailure };

function shareMarker(location: Location): ShareMarker | null {
  const marker = new URLSearchParams(location.search).get(SHARE_MARKER);
  if (marker === null) return null;
  if (FAILURES.includes(marker)) return { failure: marker as ShareFailure };
  return isShareDraftId(marker) ? { draft: marker } : null;
}

/** Where the service worker sends the app after a share: to read its draft, or to say why there is none. */
export function shareTargetAddress(marker: ShareMarker): string {
  return `/?${SHARE_MARKER}=${'draft' in marker ? marker.draft : marker.failure}`;
}

async function readDraft(id: string): Promise<SharedParcelInput | null> {
  try {
    const response = await fetch(shareDraftAddress(id), {
      cache: 'no-store',
      credentials: 'same-origin',
    });
    if (!response.ok) return null;
    const value: unknown = await response.json();
    if (!value || typeof value !== 'object') return null;
    const draft = value as Partial<SharedParcelInput>;
    if (typeof draft.label !== 'string' || typeof draft.trackingInput !== 'string') return null;
    const trackingInput = draft.trackingInput.trim().slice(0, 10_000);
    return trackingInput ? { label: draft.label.trim().slice(0, 80), trackingInput } : null;
  } catch {
    return null;
  }
}

/** The answer for each draft asked for: it is handed out once, so a screen drawn again reads the same answer. */
const answers = new Map<string, Promise<SharedParcel>>();

/**
 * What a share brought, when the address says the app was opened by one. A
 * draft that is gone, expired or empty is a share that failed: the person
 * shared something and must hear that nothing came through.
 */
export async function readSharedParcel(
  location: Location = window.location,
): Promise<SharedParcel | null> {
  const marker = shareMarker(location);
  if (!marker) return null;
  if ('failure' in marker) return marker;
  let answer = answers.get(marker.draft);
  if (!answer) {
    answer = readDraft(marker.draft).then((input): SharedParcel => input ? { input } : { failure: 'failed' });
    answers.set(marker.draft, answer);
  }
  return answer;
}

export function clearSharedParcelInput(location: Location = window.location): void {
  if (!shareMarker(location)) return;
  const url = new URL(location.href);
  url.searchParams.delete(SHARE_MARKER);
  window.history.replaceState(
    window.history.state,
    '',
    `${url.pathname}${url.search}${url.hash}`,
  );
}
