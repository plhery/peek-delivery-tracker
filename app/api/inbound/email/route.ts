import { apiRoute, HttpError, noContent, readBodyBytes, requireService } from '../../../../src/server/api';
import { receiveInboundMail } from '../../../../src/server/inboundEmail/receive';
import { inboundEmailConfigured, MAX_INBOUND_EMAIL_BYTES, verifyInboundSignature } from '../../../../src/server/inboundEmail/settings';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/**
 * An email to a forwarding address, posted by the Cloudflare Email Worker with its recipient and a signature over
 * both. Every email it signs is answered 202, whoever it names: the Worker learns nothing about addresses.
 */
export const POST = apiRoute(async (context) => {
  const settings = inboundEmailConfigured();
  if (!settings) throw new HttpError(404, 'Not found');
  const raw = await readBodyBytes(context.request, MAX_INBOUND_EMAIL_BYTES);
  const recipient = context.request.headers.get('x-peek-recipient') ?? '';
  if (!verifyInboundSignature(settings.secret, context.request.headers.get('x-peek-signature'), recipient, raw)) {
    throw new HttpError(401, 'Invalid signature');
  }
  await receiveInboundMail(requireService(context), settings, recipient, raw);
  return noContent(202);
}, {
  authenticated: false,
  serviceRequired: true,
  anyBody: true,
  capability: true,
  // No limit per client: every email comes from Cloudflare's Workers, and the allowances of each address and of
  // every address together limit what a signed email can do.
});
