import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { StrictMode } from 'react';
import { afterEach, beforeEach, describe, expect, it, vi } from 'vitest';
import { ApiAuthenticationError, ApiRecentSignInError } from '../lib/apiClient';
import { AssistantRequestError, answerAssistantConsent } from '../lib/assistants';
import { PENDING_CONSENT_STORAGE_KEY } from '../lib/pendingConsent';
import { noteSignInAgain, SIGN_IN_AGAIN_STORAGE_KEY } from '../lib/signInAgain';
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

function signedIn(more: Record<string, unknown> = {}) {
  mocks.auth = {
    status: 'authenticated', user: { id: 'user-a', email: 'owner@example.test' }, signal: new AbortController().signal,
    getAccessToken: async () => 'session', signOut: vi.fn(async () => undefined),
    googleEnabled: false, appleEnabled: false, emailOtpEnabled: true, oidcName: null,
    signInWithGoogle, signInWithApple: vi.fn(), signInWithOidc: vi.fn(), sendCode: vi.fn(async () => undefined), verifyCode: vi.fn(async () => undefined),
    ...more,
  };
}
const CALLBACK = 'https://claude.example/callback?code=c&state=s1';
const note = () => JSON.parse(window.sessionStorage.getItem(SIGN_IN_AGAIN_STORAGE_KEY) ?? 'null') as Record<string, unknown> | null;

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

  it('signs out a session Auth no longer confirms, and asks to sign in again keeping the request', async () => {
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

  it('asks the account to sign in again in place when its sign-in is too old, then allows what it kept', async () => {
    signedIn();
    vi.mocked(answerAssistantConsent).mockRejectedValueOnce(new ApiRecentSignInError()).mockResolvedValueOnce(CALLBACK);
    const user = userEvent.setup();
    render(<AssistantConsentScreen request={request} query={QUERY} />);
    await user.click(screen.getByRole('checkbox', { name: /Add parcels/ }));
    await user.click(screen.getByRole('button', { name: 'Allow' }));
    expect(await screen.findByRole('heading', { name: 'Sign in again to connect Claude' })).toBeInTheDocument();
    expect(mocks.auth.signOut).not.toHaveBeenCalled();
    expect(screen.getByLabelText('Email address')).toHaveValue('owner@example.test');
    await user.click(screen.getByRole('button', { name: 'Email me a code' }));
    await user.type(await screen.findByLabelText('Sign-in code'), '123456');
    await user.click(screen.getByRole('button', { name: 'Sign in again' }));
    expect(mocks.auth.verifyCode).toHaveBeenCalledWith('owner@example.test', '123456');
    expect(answerAssistantConsent).toHaveBeenCalledTimes(2);
    expect(answerAssistantConsent).toHaveBeenLastCalledWith(expect.objectContaining({ userId: 'user-a' }),
      { query: QUERY, approve: true, scopes: ['parcels:read'] });
    expect(assign).toHaveBeenCalledWith(CALLBACK);
    expect(await screen.findByRole('heading', { name: 'Claude is connected' })).toBeInTheDocument();
  });

  it('notes the request before a provider takes the page away, and allows it when the same account is back', async () => {
    signedIn({ googleEnabled: true });
    vi.mocked(answerAssistantConsent).mockRejectedValueOnce(new ApiRecentSignInError());
    const user = userEvent.setup();
    const view = render(<AssistantConsentScreen request={request} query={QUERY} />);
    await user.click(screen.getByRole('checkbox', { name: /Add parcels/ }));
    await user.click(screen.getByRole('button', { name: 'Allow' }));
    await user.click(await screen.findByRole('button', { name: 'Continue with Google' }));
    expect(signInWithGoogle).toHaveBeenCalledOnce();
    expect(JSON.parse(window.sessionStorage.getItem(PENDING_CONSENT_STORAGE_KEY)!)).toMatchObject({ query: `?${QUERY}` });
    expect(note()).toMatchObject({ action: 'assistant-consent', userId: 'user-a', query: QUERY, scopes: ['parcels:read'] });
    view.unmount();

    // The deliveries send the page back here, signed in again. Effects that run twice still answer once.
    vi.mocked(answerAssistantConsent).mockResolvedValueOnce(CALLBACK);
    render(<StrictMode><AssistantConsentScreen request={request} query={QUERY} /></StrictMode>);
    expect(await screen.findByRole('heading', { name: 'Claude is connected' })).toBeInTheDocument();
    expect(answerAssistantConsent).toHaveBeenCalledTimes(2);
    expect(answerAssistantConsent).toHaveBeenLastCalledWith(expect.anything(), { query: QUERY, approve: true, scopes: ['parcels:read'] });
    expect(assign).toHaveBeenCalledOnce();
    expect(note()).toBeNull();
  });

  it('shows the request to another account that signs in instead, and allows nothing for it', async () => {
    const identity = new AbortController();
    signedIn({ signal: identity.signal });
    // A code for another account replaces the session, and this account's requests end.
    vi.mocked(mocks.auth.verifyCode as () => Promise<void>).mockImplementation(async () => {
      identity.abort();
      mocks.auth = { ...mocks.auth, user: { id: 'user-b', email: 'other@example.test' }, signal: new AbortController().signal };
    });
    vi.mocked(answerAssistantConsent).mockRejectedValueOnce(new ApiRecentSignInError());
    const user = userEvent.setup();
    const view = render(<AssistantConsentScreen request={request} query={QUERY} />);
    await user.click(screen.getByRole('button', { name: 'Allow' }));
    await user.clear(await screen.findByLabelText('Email address'));
    await user.type(screen.getByLabelText('Email address'), 'other@example.test');
    await user.click(screen.getByRole('button', { name: 'Email me a code' }));
    await user.type(await screen.findByLabelText('Sign-in code'), '123456');
    await user.click(screen.getByRole('button', { name: 'Sign in again' }));
    expect(await screen.findByText('Claude asks to use your Peek account, other@example.test.')).toBeInTheDocument();
    expect(answerAssistantConsent).toHaveBeenCalledOnce();
    expect(screen.getByRole('button', { name: 'Allow' })).toBeEnabled();
    view.unmount();

    // Back from a provider as another account: the access kept shows, and waits for this account's answer.
    noteSignInAgain({ action: 'assistant-consent', userId: 'user-a', query: QUERY, scopes: ['parcels:read'] });
    signedIn({ user: { id: 'user-b', email: 'other@example.test' } });
    render(<AssistantConsentScreen request={request} query={QUERY} />);
    expect(screen.getByText('Claude asks to use your Peek account, other@example.test.')).toBeInTheDocument();
    expect(screen.getByRole('checkbox', { name: /Add parcels/ })).not.toBeChecked();
    expect(answerAssistantConsent).toHaveBeenCalledOnce();
    expect(assign).not.toHaveBeenCalled();
    expect(note()).toBeNull();
  });

  it('allows nothing by itself for another request than the one noted', () => {
    noteSignInAgain({ action: 'assistant-consent', userId: 'user-a', query: 'client_id=peek_c_other&response_type=code&state=s2', scopes: ['parcels:read'] });
    signedIn();
    render(<AssistantConsentScreen request={request} query={QUERY} />);
    expect(screen.getByRole('heading', { name: 'Connect Claude to Peek?' })).toBeInTheDocument();
    expect(answerAssistantConsent).not.toHaveBeenCalled();
    expect(note()).toBeNull();
  });
});
