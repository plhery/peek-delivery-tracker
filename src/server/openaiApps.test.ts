import { afterEach, describe, expect, it, vi } from 'vitest';
import { GET } from '../../app/.well-known/openai-apps-challenge/route';
import { config } from '../../proxy';
import { openaiAppsChallenge } from './openaiApps';

const env = (values: Record<string, string>) => values as unknown as NodeJS.ProcessEnv;

afterEach(() => { vi.unstubAllEnvs(); });

describe('the OpenAI domain check', () => {
  it('is the configured token, trimmed, and nothing that is not one token', () => {
    expect(openaiAppsChallenge(env({ OPENAI_APPS_CHALLENGE: '  synthetic-challenge_token.123 \n' }))).toBe('synthetic-challenge_token.123');
    expect(openaiAppsChallenge(env({}))).toBeNull();
    expect(openaiAppsChallenge(env({ OPENAI_APPS_CHALLENGE: ' ' }))).toBeNull();
    expect(openaiAppsChallenge(env({ OPENAI_APPS_CHALLENGE: 'two words' }))).toBeNull();
    expect(openaiAppsChallenge(env({ OPENAI_APPS_CHALLENGE: 'x'.repeat(513) }))).toBeNull();
    expect(openaiAppsChallenge(env({ OPENAI_APPS_CHALLENGE: 'tökén' }))).toBeNull();
  });

  it('serves the token alone as plain text, without a session', async () => {
    vi.stubEnv('OPENAI_APPS_CHALLENGE', 'synthetic-challenge-token');
    const response = GET();
    expect(response.status).toBe(200);
    expect(response.headers.get('content-type')).toBe('text/plain; charset=utf-8');
    expect(response.headers.get('content-security-policy')).toBeNull();
    expect(await response.text()).toBe('synthetic-challenge-token');
  });

  it('does not exist until a token is configured', async () => {
    vi.stubEnv('OPENAI_APPS_CHALLENGE', '');
    const response = GET();
    expect(response.status).toBe(404);
    expect(response.headers.get('cache-control')).toBe('no-store');
    expect(await response.text()).toBe('');
  });

  it('is left alone by the page proxy, like the other files read from other servers', () => {
    const pages = new RegExp(`^${config.matcher[0].source}$`);
    expect(pages.test('/.well-known/openai-apps-challenge')).toBe(false);
    expect(pages.test('/.well-known/openai')).toBe(true);
  });
});
