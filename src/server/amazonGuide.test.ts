import { describe, expect, it } from 'vitest';
import { GUIDE_LINKS } from '../generated/guides';
import { AMAZON_ACCOUNT_MESSAGE } from '../lib/amazon';
import { amazonAccountMessage, amazonGuideUrl } from './amazonGuide';

const environment = (values: Record<string, string>) => values as unknown as NodeJS.ProcessEnv;
const slug = (language: 'en' | 'fr') => GUIDE_LINKS[language].find(({ id }) => id === 'amazon-orders')?.slug;

describe('the Amazon guide in messages sent away from a page', () => {
  it('links the guide on the canonical origin where the site serves its guides', () => {
    const env = environment({ GUIDE_PAGES: 'on', CANONICAL_ORIGIN: 'https://peek.example' });
    expect(amazonGuideUrl('en', env)).toBe(`https://peek.example/guides/${slug('en')}`);
    expect(amazonGuideUrl('fr', env)).toBe(`https://peek.example/fr/guides/${slug('fr')}`);
    // Peek's own site sends the message it always has.
    expect(amazonAccountMessage(environment({ GUIDE_PAGES: 'on', CANONICAL_ORIGIN: 'https://peektracker.com' }))).toBe('Amazon Logistics deliveries usually need '
      + 'your Amazon account. Public Amazon Shipping tracking must be confirmed before adding this parcel. An Amazon parcel another carrier '
      + `delivers is tracked with that carrier's number, which Amazon shows under Your Orders, then Track package: https://peektracker.com/guides/${slug('en')}`);
  });

  it.each([
    ['serves no guides', { CANONICAL_ORIGIN: 'https://peek.example' }],
    ['names no canonical origin', { GUIDE_PAGES: 'on' }],
  ])('links none on a site that %s', (_, env) => {
    expect(amazonGuideUrl('en', environment(env))).toBeNull();
    expect(amazonAccountMessage(environment(env))).toBe(AMAZON_ACCOUNT_MESSAGE);
  });
});
