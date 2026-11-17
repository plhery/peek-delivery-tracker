import { createHash } from 'node:crypto';
import { describe, expect, it } from 'vitest';
import { reviewAccountDigest } from './review';

describe('the review account the consent page knows', () => {
  it('is the digest of its lowercase address, so the page never shows it', () => {
    const digest = createHash('sha256').update('review@example.test').digest('hex');
    expect(reviewAccountDigest({ REVIEW_ACCOUNT_EMAIL: ' Review@Example.test ' })).toBe(digest);
  });

  it('is none without an address', () => {
    expect(reviewAccountDigest({})).toBeNull();
    expect(reviewAccountDigest({ REVIEW_ACCOUNT_EMAIL: '' })).toBeNull();
    expect(reviewAccountDigest({ REVIEW_ACCOUNT_EMAIL: 'review' })).toBeNull();
    expect(reviewAccountDigest({ REVIEW_ACCOUNT_EMAIL: 'a@b@c d' })).toBeNull();
  });
});
