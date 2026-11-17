import { createHash } from 'node:crypto';
import { describe, expect, it, vi } from 'vitest';
import { isReviewAccount } from './reviewAccount';

const DIGEST = createHash('sha256').update('review@example.test').digest('hex');

describe('the review account', () => {
  it('is known by its address, whatever its case and spaces', async () => {
    await expect(isReviewAccount(DIGEST, ' Review@Example.TEST ')).resolves.toBe(true);
    await expect(isReviewAccount(DIGEST, 'review@example.tes')).resolves.toBe(false);
    await expect(isReviewAccount(DIGEST, '')).resolves.toBe(false);
  });

  it('is no address where the browser can\'t hash', async () => {
    const digest = vi.spyOn(crypto.subtle, 'digest').mockRejectedValueOnce(new Error('Not a secure context'));
    await expect(isReviewAccount(DIGEST, 'review@example.test')).resolves.toBe(false);
    digest.mockRestore();
  });
});
