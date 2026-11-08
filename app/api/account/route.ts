import { revokeAppleSignIn } from '../../../src/server/appleSignIn';
import {
  apiRoute,
  HttpError,
  json,
  readJsonObject,
  requireRecentSignIn,
  requireService,
  requireUser,
} from '../../../src/server/api';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

export const DELETE = apiRoute(async (context) => {
  const payload = await readJsonObject(context.request);
  const confirmation = payload.confirmation;
  const user = requireUser(context);
  if (
    typeof confirmation !== 'string'
    || !user.email
    || confirmation.trim().toLocaleLowerCase('en-US') !== user.email.toLocaleLowerCase('en-US')
  ) throw new HttpError(400, 'Type your account email exactly to confirm deletion');
  // The iPhone app signs in with Apple again before deleting an account that uses it.
  const appleCode = payload.appleAuthorizationCode;
  if (appleCode !== undefined && (typeof appleCode !== 'string' || !appleCode || appleCode.length > 4_096)) {
    throw new HttpError(400, 'Invalid Apple authorization code');
  }
  const freshUser = await requireRecentSignIn(context, 'Sign in again before permanently deleting your account');
  const service = requireService(context);
  await revokeAppleSignIn(appleCode, () => service.appleSubjects(freshUser.id));
  await service.deleteAuthUser(freshUser.id);
  return json({ ok: true });
}, { serviceRequired: true });
