import 'server-only';

import type { SupabaseServiceClient } from '../supabase';
import { assistantsOpenTo, type AssistantSettings } from './settings';

const EMAIL_LIFETIME_MS = 600_000;
const emails = new Map<string, { email: string | null; until: number }>();

/** The account's confirmed email, read at most every ten minutes, for a list that names accounts by email. */
async function confirmedEmail(service: SupabaseServiceClient, userId: string, now: number): Promise<string | null> {
  const cached = emails.get(userId);
  if (cached && cached.until > now) return cached.email;
  const account = await service.getAuthAccount(userId);
  const email = account?.emailConfirmed ? account.email : null;
  if (emails.size >= 1_000) emails.delete(emails.keys().next().value!);
  emails.set(userId, { email, until: now + EMAIL_LIFETIME_MS });
  return email;
}

/** Whether the setting is open to an account: by its id, or by its confirmed email. */
export async function assistantsOpenToAccount(
  settings: AssistantSettings,
  service: SupabaseServiceClient,
  userId: string,
  now = Date.now(),
): Promise<boolean> {
  if (assistantsOpenTo(settings, { id: userId })) return true;
  if (!settings.accounts.emails.size) return false;
  return assistantsOpenTo(settings, { id: userId, email: await confirmedEmail(service, userId, now) });
}

/** Forgets cached emails, for tests. */
export function forgetAssistantEmails(): void {
  emails.clear();
}
