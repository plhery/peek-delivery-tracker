import { apiRoute, json, readJsonBody, requireService, requireUser } from '../../../../src/server/api';
import { claimAccountTracking } from '../../../../src/server/accountTrackingBudget';
import { detectCarrier } from '../../../../src/server/carrierDetection';
import { lookupCountry, withLookupCountry } from '../../../../src/server/lookupCountry';
import { checkAccountRequest } from '../../../../src/server/nativeVerification';
import { clientInputs, detectionWithInputs } from '../../../../src/server/suggestedInputs';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

export const POST = apiRoute(
  async (context) => {
    const { raw, value: body } = await readJsonBody(context.request);
    const country = lookupCountry(context.request, body.lookupCountryHint);
    const answer = await detectCarrier(body, async () => {
      await checkAccountRequest(context.request, requireService(context), raw, 'account_detection');
      await claimAccountTracking(requireService(context), requireUser(context).id, 'detection');
    }, context.request.signal, requireService(context), country, context.user?.id);
    return json(withLookupCountry(detectionWithInputs(answer, clientInputs(context.request)), country));
  },
  { serviceRequired: true },
);
