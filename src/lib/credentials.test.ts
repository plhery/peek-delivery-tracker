import { describe, expect, it } from 'vitest';
import { credentialName, scrubQueryString, withoutCredentials } from './credentials';

describe('access values', () => {
  it('are named after a key, a token, a password, a session, a signature or a cookie', () => {
    for (const name of ['api_key', 'accessToken', 'set-cookie', 'Authorization', 'x-api-key', 'sessionId', 'client_secret',
      'code_verifier', 'p256dh', 'svix-signature', 'x-peek-recipient']) {
      expect(credentialName(name), name).toBe(true);
    }
    for (const name of ['tracking_number', 'postcode', 'phone', 'keyword', 'monkey', 'state', 'code', 'number_pattern']) {
      expect(credentialName(name), name).toBe(false);
    }
  });

  it('are filtered out of a page\'s hidden fields and out of a query in any shape', () => {
    expect(withoutCredentials('<input type="hidden" name="__RequestVerificationToken" value="abc"><input name="number" value="TEST1">'))
      .toBe('<input type="hidden" name="__RequestVerificationToken" value="[Filtered]"><input name="number" value="TEST1">');
    const image = '/api/friends/invite-image';
    expect(withoutCredentials('https://example.test' + image + '?preview=value')).toContain('?preview=[Filtered]');
    expect(scrubQueryString('preview=value', '')).toBe('preview=[Filtered]');
    expect(scrubQueryString({ preview: 'value' }, image)).toEqual({ preview: '[Filtered]' });
    expect(scrubQueryString('?number=TEST1&token=x', '/api/parcels')).toBe('number=TEST1&token=[Filtered]');
    expect(scrubQueryString([['number', 'TEST1'], ['api_key', 'x']], '/api/parcels')).toEqual([['number', 'TEST1'], ['api_key', '[Filtered]']]);
    expect(scrubQueryString({ t: 'x' }, '/api/email/unsubscribe')).toEqual({ t: '[Filtered]' });
  });
});
