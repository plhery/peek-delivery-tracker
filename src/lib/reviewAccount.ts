/**
 * Whether an address is the review account's, known to the page only by the SHA-256 digest of its lowercase address
 * (hex), so the page never shows it. False where the browser can't hash, which leaves the address a code.
 */
export async function isReviewAccount(digest: string, email: string): Promise<boolean> {
  try {
    const hash = await crypto.subtle.digest('SHA-256', new TextEncoder().encode(email.trim().toLowerCase()));
    return [...new Uint8Array(hash)].map((byte) => byte.toString(16).padStart(2, '0')).join('') === digest;
  } catch {
    return false;
  }
}
