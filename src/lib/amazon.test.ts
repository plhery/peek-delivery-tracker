import { describe, expect, it } from 'vitest';
import { GUIDE_LINKS } from '../generated/guides';
import { AMAZON_ACCOUNT_MESSAGE, AMAZON_GUIDE_URL } from './amazon';
import { userErrorKey } from './userMessages';

describe('the message a client gets when Amazon tracks a parcel itself', () => {
  it('links the Amazon guide at its English address', () => {
    const guide = GUIDE_LINKS.en.find(({ id }) => id === 'amazon-orders');
    expect(AMAZON_GUIDE_URL).toBe(`https://peektracker.com/guides/${guide?.slug}`);
    expect(AMAZON_ACCOUNT_MESSAGE).toContain(AMAZON_GUIDE_URL);
  });

  it('still reads as the Amazon account message in the apps, which match its words', () => {
    expect(userErrorKey(new Error(AMAZON_ACCOUNT_MESSAGE))).toBe('add.amazonAccount');
    // The iPhone and Android apps match the same words, lowercased: "amazon logistics.*amazon account".
    expect(/amazon logistics.*amazon account/.test(AMAZON_ACCOUNT_MESSAGE.toLowerCase())).toBe(true);
  });
});
