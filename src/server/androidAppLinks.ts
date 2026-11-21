import 'server-only';

const packagePattern = /^[A-Za-z][A-Za-z0-9_]*(?:\.[A-Za-z][A-Za-z0-9_]*)+$/;
const fingerprintPattern = /^(?:[0-9A-F]{2}:){31}[0-9A-F]{2}$/;

/**
 * The Android app allowed to open this site's links: `ANDROID_APP_PACKAGE`, with
 * the SHA-256 fingerprints of the certificates it is signed with in
 * `ANDROID_APP_CERT_SHA256` (separated by commas): Google's for Play App
 * Signing, and the upload key's for a build signed outside Google Play. They are
 * public: the Play Console and `apksigner verify --print-certs` show them. Null
 * without both, or when what is set is malformed: no app opens the site's links.
 */
export function androidApp(env: NodeJS.ProcessEnv = process.env): { package: string; fingerprints: string[] } | null {
  const name = env.ANDROID_APP_PACKAGE?.trim();
  const listed = (env.ANDROID_APP_CERT_SHA256 ?? '').split(',').map((value) => value.trim().toUpperCase()).filter(Boolean);
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
