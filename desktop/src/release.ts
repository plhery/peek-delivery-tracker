import { z } from 'zod';

const stableVersion = '(0|[1-9][0-9]*)\\.(0|[1-9][0-9]*)\\.(0|[1-9][0-9]*)';
export const releaseSchema = z.object({
  version: z.string().regex(new RegExp(`^${stableVersion}(?:-nightly\\.[1-9][0-9]*\\.g[0-9a-f]{12})?$`)),
  channel: z.enum(['stable', 'nightly']),
  sha: z.string().regex(/^[0-9a-f]{40}$/),
  buildNumber: z.number().int().min(1).max(65535),
}).strict().superRefine((value, ctx) => {
  if (value.version.includes('-nightly.') !== (value.channel === 'nightly')) {
    ctx.addIssue({ code: 'custom', message: 'Release version must match the channel.' });
  }
  const suffix = value.version.split('.g')[1];
  if (suffix && !value.sha.startsWith(suffix)) {
    ctx.addIssue({ code: 'custom', message: 'Nightly version must identify the source SHA.' });
  }
  for (const component of value.version.split('-')[0]?.split('.') ?? []) {
    if (Number(component) > 65535) ctx.addIssue({ code: 'custom', message: 'Native version component exceeds 65535.' });
  }
});
export type Release = z.infer<typeof releaseSchema>;

export function nativeVersions(release: Release) {
  const value = releaseSchema.parse(release);
  const base = value.version.split('-')[0];
  if (!base) throw new Error('Missing stable base.');
  const b = value.buildNumber;
  return {
    macShortVersion: base,
    macBuildVersion: `${1 + Math.floor(b / 10000)}.${Math.floor(b / 100) % 100}.${b % 100}`,
    windowsVersion: `${base}.${b}`,
    debianVersion: value.version.replace('-nightly.', '~nightly.'),
  };
}

export function channelIdentity(channel: Release['channel']) {
  return channel === 'nightly'
    ? { appId: 'app.peek.delivery.nightly', packageName: 'peek-desktop-nightly', productName: 'Peek Nightly', profile: 'Peek Nightly' }
    : { appId: 'app.peek.delivery', packageName: 'peek-desktop', productName: 'Peek', profile: 'Peek' };
}
