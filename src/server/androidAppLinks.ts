import 'server-only';

/**
 * Peek's Android app on Google Play, and the SHA-256 fingerprint of the
 * certificate Google signs it with (Play App Signing). Both are public: the
 * Play Console shows them, and the app's manifest names only Peek's own hosts,
 * so another site that serves this file opens nothing in the app.
 */
const PEEK_PACKAGE = 'com.plhery.peekdeliverytracker';
const PEEK_FINGERPRINTS = ['F1:55:DB:E7:76:F4:5F:EA:F2:51:4F:73:3B:98:4D:A3:FC:5F:56:2A:DA:66:F2:BD:65:1F:C5:89:D2:BD:DE:60'];

const packagePattern = /^[A-Za-z][A-Za-z0-9_]*(?:\.[A-Za-z][A-Za-z0-9_]*)+$/;
const fingerprintPattern = /^(?:[0-9A-F]{2}:){31}[0-9A-F]{2}$/;

/**
 * The Android app allowed to open this site's links: `ANDROID_APP_PACKAGE` and
 * the fingerprints in `ANDROID_APP_CERT_SHA256` (separated by commas) when both
 * are set, or Peek's app on Google Play. Null when what is set is malformed.
 */
export function androidApp(env: NodeJS.ProcessEnv = process.env): { package: string; fingerprints: string[] } | null {
  const name = env.ANDROID_APP_PACKAGE?.trim();
  const listed = (env.ANDROID_APP_CERT_SHA256 ?? '').split(',').map((value) => value.trim().toUpperCase()).filter(Boolean);
  if (!name && listed.length === 0) return { package: PEEK_PACKAGE, fingerprints: PEEK_FINGERPRINTS };
  const fingerprints = [...new Set(listed.filter((value) => fingerprintPattern.test(value)))];
  if (!name || !packagePattern.test(name) || fingerprints.length === 0) return null;
  return { package: name, fingerprints };
}

/**
 * The statement Android reads from `/.well-known/assetlinks.json` before it
 * opens this site's links in the app. Which links is the app's to say (its
 * manifest: parcel links and invitations); the file only vouches for the app.
 */
export function androidAssetLinks(env: NodeJS.ProcessEnv = process.env) {
  const app = androidApp(env);
  if (!app) return null;
  return [{
    relation: ['delegate_permission/common.handle_all_urls'],
    target: { namespace: 'android_app', package_name: app.package, sha256_cert_fingerprints: app.fingerprints },
  }];
}
