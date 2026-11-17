import 'server-only';

/** A token as OpenAI's dashboard gives it: one word of printable ASCII. */
const TOKEN = /^[\x21-\x7e]{1,512}$/;

/**
 * The token that proves to OpenAI that the MCP host is this site's, before Peek is submitted to ChatGPT's plugin
 * directory: `OPENAI_APPS_CHALLENGE`, served at `/.well-known/openai-apps-challenge`. Null when unset or not one token.
 */
export function openaiAppsChallenge(env: NodeJS.ProcessEnv = process.env): string | null {
  const value = env.OPENAI_APPS_CHALLENGE?.trim() ?? '';
  return TOKEN.test(value) ? value : null;
}
