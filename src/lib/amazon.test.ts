import { describe, expect, it } from 'vitest';
import { AMAZON_ACCOUNT_MESSAGE } from './amazon';
import { userErrorKey } from './userMessages';

describe('the message a client gets when Amazon tracks a parcel itself', () => {
  it('still reads as the Amazon account message in the apps, which match its words', () => {
    expect(userErrorKey(new Error(AMAZON_ACCOUNT_MESSAGE))).toBe('add.amazonAccount');
    // The iPhone and Android apps match the same words, lowercased: "amazon logistics.*amazon account".
    expect(/amazon logistics.*amazon account/.test(AMAZON_ACCOUNT_MESSAGE.toLowerCase())).toBe(true);
  });
});
