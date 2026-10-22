import { apiRoute, json, readJsonBody, requireService, requireUser } from '../../../../src/server/api';
import { claimAccountTracking } from '../../../../src/server/accountTrackingBudget';
import { detectCarrier } from '../../../../src/server/carrierDetection';
import { lookupCountry } from '../../../../src/server/lookupCountry';
import { checkAccountRequest } from '../../../../src/server/nativeVerification';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

export const POST = apiRoute(
  async (context) => {
    const { raw, value: body } = await readJsonBody(context.request);
    return json(await detectCarrier(body, async () => {
      await checkAccountRequest(context.request, requireService(context), raw, 'account_detection');
      await claimAccountTracking(requireService(context), requireUser(context).id, 'detection');
    }, context.request.signal, requireService(context), lookupCountry(context.request, body.lookupCountryHint), context.user?.id));
  },
  { serviceRequired: true },
);
