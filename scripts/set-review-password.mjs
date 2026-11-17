import path from 'node:path';
import { fileURLToPath } from 'node:url';

/**
 * Gives the review account a password, so a directory's reviewers can sign in to it on the page where an assistant
 * asks to connect (`REVIEW_ACCOUNT_EMAIL`, docs/AUTHENTICATION.md). The account must exist, its address confirmed:
 * sign in to it once with a code first. Run it again to change the password; either way Auth ends the account's
 * sessions.
 *
 *   SUPABASE_URL=https://auth.example.com npm run review-password -- review@example.com
 *
 * The password and, unless `SUPABASE_SERVICE_ROLE_KEY` is set, the service role key are typed at a prompt that
 * doesn't show them: never in arguments, files or the shell's history.
 */

export const MIN_LENGTH = 8;

/** Why a password can't be the review account's, or null when it can. */
export function passwordProblem(password, repeated) {
  if (password !== repeated) return 'The two passwords differ.';
  if (password.length < MIN_LENGTH) return `Use at least ${MIN_LENGTH} characters.`;
  if (password.trim() !== password) return 'The password starts or ends with a space.';
  return null;
}

/** The account with exactly this address, among those Auth lists for a search; it must have confirmed it. */
export function reviewUser(users, email) {
  const user = users.find((candidate) => candidate.email?.toLowerCase() === email);
  if (!user) throw new Error(`No account has the address ${email}. Sign in to Peek with it once, with a code.`);
  if (!user.email_confirmed_at) throw new Error(`${email} isn't confirmed yet. Sign in to Peek with it once, with a code.`);
  return user;
}

async function admin(fetcher, url, key, pathname, init = {}) {
  const response = await fetcher(new URL(`/auth/v1/admin/${pathname}`, url), {
    ...init,
    headers: { apikey: key, Authorization: `Bearer ${key}`, 'Content-Type': 'application/json', ...init.headers },
  });
  const body = await response.json().catch(() => ({}));
  if (!response.ok) {
    throw new Error(`Auth answered ${response.status}: ${body.msg ?? body.message ?? body.error_description ?? 'no reason given'}`);
  }
  return body;
}

/** Sets the password of the account with this address through Auth's admin API. Auth checks it and stores its hash. */
export async function setReviewPassword({ url, key, email, password, fetcher = fetch }) {
  const address = email.trim().toLowerCase();
  const { users = [] } = await admin(fetcher, url, key, `users?filter=${encodeURIComponent(address)}&per_page=50`);
  const user = reviewUser(users, address);
  await admin(fetcher, url, key, `users/${encodeURIComponent(user.id)}`, { method: 'PUT', body: JSON.stringify({ password }) });
  return user.id;
}

/** Reads a line at the terminal without echoing it. */
export function hiddenLine(question, { input = process.stdin, output = process.stderr } = {}) {
  if (!input.isTTY) return Promise.reject(new Error('Run this in a terminal: secrets are typed at a prompt, never piped.'));
  output.write(question);
  input.setRawMode(true);
  input.setEncoding('utf8');
  input.resume();
  return new Promise((resolve, reject) => {
    let value = '';
    const finish = (settle) => {
      input.off('data', read);
      input.setRawMode(false);
      input.pause();
      output.write('\n');
      settle();
    };
    function read(chunk) {
      for (const character of chunk) {
        if (character === '\r' || character === '\n') return finish(() => resolve(value));
        if (character === '\u0003' || character === '\u0004') return finish(() => reject(new Error('Stopped.')));
        if (character === '\u007f' || character === '\b') value = value.slice(0, -1);
        else if (character >= ' ') value += character;
      }
    }
    input.on('data', read);
  });
}

async function main() {
  const email = process.argv[2];
  const url = process.env.SUPABASE_URL;
  if (!email || !email.includes('@') || process.argv.length > 3) {
    throw new Error('Name the review account: SUPABASE_URL=https://auth.example.com npm run review-password -- review@example.com');
  }
  if (!url) throw new Error('Set SUPABASE_URL to the Auth server\'s public address, such as https://auth.example.com.');
  const { protocol, hostname } = new URL(url);
  if (protocol !== 'https:' && !['localhost', '127.0.0.1', '[::1]'].includes(hostname)) {
    throw new Error('SUPABASE_URL must start with https://: the service role key would otherwise travel in clear.');
  }
  const key = process.env.SUPABASE_SERVICE_ROLE_KEY || await hiddenLine('Service role key: ');
  const password = await hiddenLine(`New password for ${email}: `);
  const problem = passwordProblem(password, await hiddenLine('Same password again: '));
  if (problem) throw new Error(problem);
  await setReviewPassword({ url, key, email, password });
  console.log(`${email} signs in with this password on the page where an assistant asks to connect, while REVIEW_ACCOUNT_EMAIL names it.`);
}

if (process.argv[1] && path.resolve(process.argv[1]) === fileURLToPath(import.meta.url)) {
  main().catch((error) => {
    console.error(error.message);
    process.exitCode = 1;
  });
}
