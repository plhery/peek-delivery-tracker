import { describe, expect, it } from 'vitest';
import { credentialName, scrubAddress, scrubQueryString, shopPageAddress, withoutCredentials } from './credentials';

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

  it('leave out the whole path and query of a shop\'s tracking page, which opens the reader\'s order', () => {
    // Made-up order keys.
    const link = 'https://hm.delivery-status.com/pl/?orderNo=9900000000001&s=0000aaaa1111bbbb2222cccc3333dddd';
    expect(scrubAddress(link)).toBe('https://hm.delivery-status.com/[Filtered]');
    expect(withoutCredentials(`{"text":"Follow package ${link}"}`)).toBe('{"text":"Follow package https://hm.delivery-status.com/[Filtered]"}');
    expect(scrubAddress('https://api.parcellab.com/v2/checkpoints/?orderNo=1&user=2&s=3')).toBe('https://api.parcellab.com/[Filtered]');
    expect(scrubAddress('https://hm.delivery-status.com/')).toBe('https://hm.delivery-status.com');
    expect(shopPageAddress(link)).toBe(true);
    expect(shopPageAddress('https://delivery-status.com.example.test/?orderNo=1')).toBe(false);
    expect(scrubAddress('https://delivery-status.com.example.test/?orderNo=1')).toBe('https://delivery-status.com.example.test/?orderNo=1');
  });
});
