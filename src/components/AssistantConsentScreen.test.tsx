import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { ApiAuthenticationError } from '../lib/apiClient';
import { AssistantRequestError, answerAssistantConsent } from '../lib/assistants';
import { PENDING_CONSENT_STORAGE_KEY } from '../lib/pendingConsent';
import { AssistantConsentScreen, type ConsentRequest } from './AssistantConsentScreen';

const mocks = vi.hoisted(() => ({ auth: {} as Record<string, unknown> }));
vi.mock('../auth/AuthContext', () => ({ AuthProvider: ({ children }: { children: unknown }) => children, useAuth: () => mocks.auth }));
vi.mock('../lib/assistants', async (original) => ({
  ...await original<typeof import('../lib/assistants')>(), answerAssistantConsent: vi.fn(),
}));

const QUERY = 'client_id=peek_c_synthetic&response_type=code&state=s1';
const request: ConsentRequest = {
  clientName: 'Claude', clientHost: 'claude.example', clientUri: null, redirectHost: 'claude.example', local: false,
  scopes: ['parcels:read', 'parcels:write'],
};
const assign = vi.fn();
const signInWithGoogle = vi.fn(async () => undefined);

function signedIn() {
  mocks.auth = {
    status: 'authenticated', user: { id: 'user-a', email: 'owner@example.test' }, signal: new AbortController().signal,
    getAccessToken: async () => 'session', signOut: vi.fn(async () => undefined),
  };
}

beforeEach(() => {
  window.history.replaceState(null, '', `/oauth/authorize?${QUERY}`);
  vi.stubGlobal('location', { ...window.location, assign });
});
afterEach(() => {
  vi.unstubAllGlobals();
  vi.clearAllMocks();
  window.sessionStorage.clear();
  window.history.replaceState(null, '', '/');
});

describe('the page where an assistant asks to connect', () => {
  it('asks to try again when opened too often from one network', () => {
    signedIn();
    render(<AssistantConsentScreen request={null} query="" busy />);
    expect(screen.getByRole('alert')).toHaveTextContent('Couldn’t answer the assistant. Try again.');
  });

  it('says when the request names a client or an address Peek doesn\'t know', () => {
    signedIn();
    render(<AssistantConsentScreen request={null} query={QUERY} />);
    expect(screen.getByRole('heading', { name: 'This link can’t connect an assistant' })).toBeInTheDocument();
    expect(screen.getByRole('link', { name: 'Open Peek' })).toHaveAttribute('href', '/');
  });

  it('asks a visitor to sign in first, and notes the request before a provider takes them away', async () => {
    mocks.auth = {
      status: 'anonymous', user: null, googleEnabled: true, appleEnabled: false, emailOtpEnabled: true, oidcName: null,
      signInWithGoogle, signInWithApple: vi.fn(), signInWithOidc: vi.fn(), sendCode: vi.fn(), verifyCode: vi.fn(),
    };
    const user = userEvent.setup();
    render(<AssistantConsentScreen request={request} query={QUERY} />);
    expect(screen.getByRole('heading', { name: 'Sign in to connect Claude' })).toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Continue with Google' }));
    expect(signInWithGoogle).toHaveBeenCalled();
    expect(JSON.parse(window.sessionStorage.getItem(PENDING_CONSENT_STORAGE_KEY)!)).toMatchObject({ query: `?${QUERY}` });
  });

  it('grants only what the owner keeps, then goes back to the assistant', async () => {
    signedIn();
    vi.mocked(answerAssistantConsent).mockResolvedValue('https://claude.example/callback?code=c&state=s1');
    const user = userEvent.setup();
    render(<AssistantConsentScreen request={request} query={QUERY} />);
    expect(screen.getByRole('heading', { name: 'Connect Claude to Peek?' })).toBeInTheDocument();
    expect(screen.getByText('Claude asks to use your Peek account, owner@example.test.')).toBeInTheDocument();
    expect(screen.getByText('Its name comes from claude.example.')).toBeInTheDocument();
    expect(screen.getByText('Peek then sends you back to claude.example.')).toBeInTheDocument();
    await user.click(screen.getByRole('checkbox', { name: /Add parcels/ }));
    await user.click(screen.getByRole('button', { name: 'Allow' }));
    expect(answerAssistantConsent).toHaveBeenCalledWith(expect.objectContaining({ userId: 'user-a' }),
      { query: QUERY, approve: true, scopes: ['parcels:read'] });
    expect(assign).toHaveBeenCalledWith('https://claude.example/callback?code=c&state=s1');
    expect(await screen.findByRole('heading', { name: 'Claude is connected' })).toBeInTheDocument();
  });

  it('allows nothing without a scope kept, and refuses on request', async () => {
    signedIn();
    vi.mocked(answerAssistantConsent).mockResolvedValue('https://claude.example/callback?error=access_denied');
    const user = userEvent.setup();
    render(<AssistantConsentScreen request={request} query={QUERY} />);
    for (const box of screen.getAllByRole('checkbox')) await user.click(box);
    expect(screen.getByRole('button', { name: 'Allow' })).toBeDisabled();
    await user.click(screen.getByRole('button', { name: 'Don’t allow' }));
    expect(answerAssistantConsent).toHaveBeenCalledWith(expect.anything(), { query: QUERY, approve: false, scopes: [] });
    expect(await screen.findByRole('heading', { name: 'Claude isn’t connected' })).toBeInTheDocument();
  });

  it('asks to sign in again when the server wants a newer sign-in, keeping the request', async () => {
    signedIn();
    const signedInAuth = mocks.auth;
    const visitor = {
      status: 'anonymous', user: null, googleEnabled: true, appleEnabled: false, emailOtpEnabled: true, oidcName: null,
      signInWithGoogle, signInWithApple: vi.fn(), signInWithOidc: vi.fn(), sendCode: vi.fn(), verifyCode: vi.fn(),
    };
    vi.mocked(signedInAuth.signOut as () => Promise<void>).mockImplementation(async () => { mocks.auth = visitor; });
    vi.mocked(answerAssistantConsent).mockRejectedValueOnce(new ApiAuthenticationError());
    const user = userEvent.setup();
    render(<AssistantConsentScreen request={request} query={QUERY} />);
    await user.click(screen.getByRole('button', { name: 'Allow' }));
    expect(signedInAuth.signOut).toHaveBeenCalledOnce();
    expect(await screen.findByRole('heading', { name: 'Sign in again to connect Claude' })).toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Continue with Google' }));
    expect(JSON.parse(window.sessionStorage.getItem(PENDING_CONSENT_STORAGE_KEY)!)).toMatchObject({ query: `?${QUERY}` });
    expect(assign).not.toHaveBeenCalled();
  });

  it('warns when the answer goes to an app on this device, and says why the server refused', async () => {
    signedIn();
    vi.mocked(answerAssistantConsent).mockRejectedValueOnce(new AssistantRequestError(409, 'Too many'))
      .mockRejectedValueOnce(new AssistantRequestError(403, 'Closed'));
    const user = userEvent.setup();
    render(<AssistantConsentScreen request={{ ...request, clientHost: null, local: true, redirectHost: 'cursor://' }} query={QUERY} />);
    expect(screen.getByText(/an app on this device \(cursor:\/\/\)/)).toBeInTheDocument();
    expect(screen.getByText('Peek can’t check this name: allow it only if you just connected Peek from Claude.')).toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Allow' }));
    expect(await screen.findByRole('alert')).toHaveTextContent('too many connected assistants');
    await user.click(screen.getByRole('button', { name: 'Allow' }));
    expect(await screen.findByRole('alert')).toHaveTextContent('can’t connect assistants to this account');
    expect(assign).not.toHaveBeenCalled();
  });
});
