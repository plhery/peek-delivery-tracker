import { apiRoute, json, readJsonObject, requireService, noContent } from '../../../../../src/server/api';
import { androidAttestSettings, nativeAttestSettings, registerNativeKey } from '../../../../../src/server/nativeVerification';
import { turnstileSettings } from '../../../../../src/server/lookupVerification';

export const dynamic = 'force-dynamic';
export const runtime = 'nodejs';

/** Android's: the mode, the app ID keys register under, and the Cloud project the Play build asks Play Integrity tokens for. */
export const GET = apiRoute(async () => {
  const android = androidAttestSettings();
  return json({
    appAttestAppId: nativeAttestSettings()?.appId ?? null,
    // As appAttestAppId for the iPhone: set where signed lookups skip the browser check.
    androidAttestAppId: android?.mode === 'enforce' ? android.appId : null,
    androidAttestation: android && {
      mode: android.mode,
      appId: android.appId,
      playIntegrityCloudProjectNumber: android.playIntegrity?.projectNumber ?? null,
    },
    turnstile: turnstileSettings() !== null,
  });
}, { authenticated: false, loadService: false, publicRateLimit: { limit: 30, window: 60 } });

// An Android registration carries a certificate chain and, from the Play build, a Play Integrity token.
export const POST = apiRoute(async (context) => {
  await registerNativeKey(context.request, requireService(context), await readJsonObject(context.request, 32_768));
  return noContent();
}, { authenticated: false, serviceRequired: true, capability: true, publicRateLimit: { limit: 5, window: 60 } });
