import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { StrictMode } from 'react';
import { afterEach, describe, expect, it, vi } from 'vitest';
import type { ApiAssistantConnection, ApiAssistantsResponse } from '../generated/apiContract';
import { readAddByEmail } from '../lib/addByEmail';
import { ApiRecentSignInError } from '../lib/apiClient';
import { createPersonalToken, readAssistants, revokeAssistant } from '../lib/assistants';
import { SIGN_IN_AGAIN_STORAGE_KEY } from '../lib/signInAgain';
import type { SignInMethods } from '../peek/session';
import { AccountMenu } from './AccountMenu';
import { AssistantsPage } from './Assistants';

vi.mock('../lib/addByEmail', () => ({ readAddByEmail: vi.fn(), openAddByEmail: vi.fn(), closeAddByEmail: vi.fn() }));
vi.mock('../lib/assistants', () => ({ readAssistants: vi.fn(), createPersonalToken: vi.fn(), revokeAssistant: vi.fn() }));
vi.mock('./NotificationControl', () => ({ NotificationControl: () => null }));

const apiAuth = { userId: 'user-a', getAccessToken: async () => 'session' };
const claude: ApiAssistantConnection = {
  id: '6c000000-0000-4000-a000-0000000000a1', kind: 'oauth', name: 'Claude', redirectHost: 'claude.example',
  scopes: ['parcels:read', 'parcels:write'], createdAt: '2026-11-03T10:00:00.000Z', lastUsedAt: null,
};
const script: ApiAssistantConnection = {
  id: '6c000000-0000-4000-a000-0000000000b1', kind: 'personal', name: 'Home script', redirectHost: null,
  scopes: ['parcels:read'], createdAt: '2026-11-03T10:00:00.000Z', lastUsedAt: null,
};
const offer = (connections: ApiAssistantConnection[]): ApiAssistantsResponse => ({ connections, mcpUrl: 'https://peek.example/mcp' });

const signIn = (more: Partial<SignInMethods> = {}): SignInMethods => ({
  configured: true, googleEnabled: false, appleEnabled: false, emailOtpEnabled: true, oidcName: null,
  sendCode: vi.fn(async () => undefined), verifyCode: vi.fn(async () => undefined), ...more,
});
const made: ApiAssistantConnection = { ...script, id: '6c000000-0000-4000-a000-0000000000b2', name: 'Home Assistant', scopes: ['parcels:read', 'parcels:write'] };
const token = `peek_pat_${'x'.repeat(43)}`;
const note = () => JSON.parse(window.sessionStorage.getItem(SIGN_IN_AGAIN_STORAGE_KEY) ?? 'null') as Record<string, unknown> | null;

afterEach(() => { vi.clearAllMocks(); window.sessionStorage.clear(); });

describe('assistants in Settings', () => {
  it('shows no row while the server connects no assistant to the account', async () => {
    vi.mocked(readAddByEmail).mockResolvedValue(null);
    vi.mocked(readAssistants).mockResolvedValue(null);
    const user = userEvent.setup();
    render(<AccountMenu email="owner@example.test" apiAuth={apiAuth} />);
    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    await waitFor(() => expect(readAssistants).toHaveBeenCalledWith(apiAuth));
    expect(screen.queryByRole('button', { name: 'Assistants and tokens' })).not.toBeInTheDocument();
  });

  it('lists the assistants connected, with the address to give one, and disconnects one', async () => {
    vi.mocked(readAddByEmail).mockResolvedValue(null);
    vi.mocked(readAssistants).mockResolvedValue(offer([claude, script]));
    vi.mocked(revokeAssistant).mockResolvedValue();
    const copy = vi.spyOn(navigator.clipboard, 'writeText').mockResolvedValue();
    const user = userEvent.setup();
    render(<AccountMenu email="owner@example.test" apiAuth={apiAuth} />);
    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    await user.click(await screen.findByRole('button', { name: 'Assistants and tokens' }));
    expect(screen.getByRole('heading', { name: 'Assistants and tokens' })).toBeInTheDocument();
    expect(screen.getByText('https://peek.example/mcp')).toBeInTheDocument();
    expect(screen.getByText(/can get your parcels from https:\/\/peek\.example\/api\/parcels with one of these tokens/)).toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Copy address' }));
    expect(copy).toHaveBeenCalledWith('https://peek.example/mcp');
    expect(screen.getByText('claude.example · Sees, adds and archives parcels · not used yet')).toBeInTheDocument();
    expect(screen.getByText('Sees your parcels · not used yet')).toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Remove Claude' }));
    expect(revokeAssistant).toHaveBeenCalledWith(apiAuth, claude.id);
    expect(await screen.findByText('No assistant is connected yet.')).toBeInTheDocument();
    expect(screen.getByText('Home script')).toBeInTheDocument();
  });

  it('makes a personal token and shows it once', async () => {
    vi.mocked(createPersonalToken).mockResolvedValue({ connection: made, token });
    const onChange = vi.fn();
    const copy = vi.spyOn(navigator.clipboard, 'writeText').mockResolvedValue();
    const user = userEvent.setup();
    render(<AssistantsPage apiAuth={apiAuth} offer={offer([])} onChange={onChange} />);
    expect(screen.getByRole('button', { name: 'Create token' })).toBeDisabled();
    await user.type(screen.getByLabelText('Token name'), '  Home Assistant ');
    await user.click(screen.getByRole('checkbox', { name: 'Can add and archive parcels' }));
    await user.click(screen.getByRole('button', { name: 'Create token' }));
    expect(createPersonalToken).toHaveBeenCalledWith(apiAuth, 'Home Assistant', ['parcels:read', 'parcels:write']);
    expect(onChange).toHaveBeenCalledWith(offer([made]));
    expect(await screen.findByText(token)).toBeInTheDocument();
    expect(screen.getByText('Copy it now: Peek won’t show it again.')).toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Copy token' }));
    expect(copy).toHaveBeenCalledWith(token);
    expect(screen.getByRole('button', { name: 'Copied' })).toBeInTheDocument();
  });

  it('asks for a sign-in in place when a token needs a newer one, then makes the token as typed', async () => {
    vi.mocked(createPersonalToken).mockRejectedValueOnce(new ApiRecentSignInError()).mockResolvedValueOnce({ connection: made, token });
    const methods = signIn();
    const user = userEvent.setup();
    render(<AssistantsPage apiAuth={apiAuth} offer={offer([])} onChange={vi.fn()} signIn={methods} email="owner@example.test" />);
    await user.type(screen.getByLabelText('Token name'), 'Home Assistant');
    await user.click(screen.getByRole('checkbox', { name: 'Can add and archive parcels' }));
    await user.click(screen.getByRole('button', { name: 'Create token' }));
    expect(await screen.findByRole('heading', { name: 'Sign in again to create this token' })).toBeInTheDocument();
    expect(screen.getByText('A token works until you delete it, so Peek checks it’s still you.')).toBeInTheDocument();
    expect(screen.getByLabelText('Email address')).toHaveValue('owner@example.test');
    await user.click(screen.getByRole('button', { name: 'Email me a code' }));
    expect(methods.sendCode).toHaveBeenCalledWith('owner@example.test');
    await user.type(await screen.findByLabelText('Sign-in code'), '123456');
    await user.click(screen.getByRole('button', { name: 'Sign in again' }));
    expect(methods.verifyCode).toHaveBeenCalledWith('owner@example.test', '123456');
    expect(await screen.findByText(token)).toBeInTheDocument();
    expect(createPersonalToken).toHaveBeenCalledTimes(2);
    expect(createPersonalToken).toHaveBeenLastCalledWith(apiAuth, 'Home Assistant', ['parcels:read', 'parcels:write']);
    expect(note()).toBeNull();
  });

  it('goes back to the form as typed when the sign-in is left', async () => {
    vi.mocked(createPersonalToken).mockRejectedValueOnce(new ApiRecentSignInError());
    const user = userEvent.setup();
    render(<AssistantsPage apiAuth={apiAuth} offer={offer([])} onChange={vi.fn()} signIn={signIn()} email="owner@example.test" />);
    await user.type(screen.getByLabelText('Token name'), 'Home Assistant');
    await user.click(screen.getByRole('button', { name: 'Create token' }));
    await user.click(await screen.findByRole('button', { name: 'Cancel' }));
    expect(screen.getByLabelText('Token name')).toHaveValue('Home Assistant');
    expect(createPersonalToken).toHaveBeenCalledOnce();
  });

  it('notes the token before a provider takes the page away, and makes it when the same account is back', async () => {
    vi.mocked(readAddByEmail).mockResolvedValue(null);
    vi.mocked(readAssistants).mockResolvedValue(offer([]));
    vi.mocked(createPersonalToken).mockRejectedValueOnce(new ApiRecentSignInError());
    const signInWithGoogle = vi.fn(async () => undefined);
    const methods = signIn({ googleEnabled: true, signInWithGoogle });
    const user = userEvent.setup();
    const view = render(<AccountMenu email="owner@example.test" apiAuth={apiAuth} signIn={methods} />);
    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    await user.click(await screen.findByRole('button', { name: 'Assistants and tokens' }));
    await waitFor(() => expect(screen.getByRole('heading', { name: 'Assistants and tokens' })).toHaveFocus());
    await user.type(screen.getByLabelText('Token name'), 'Home Assistant');
    await user.click(screen.getByRole('checkbox', { name: 'Can add and archive parcels' }));
    await user.click(screen.getByRole('button', { name: 'Create token' }));
    await user.click(await screen.findByRole('button', { name: 'Continue with Google' }));
    expect(signInWithGoogle).toHaveBeenCalledOnce();
    expect(note()).toMatchObject({ action: 'personal-token', userId: 'user-a', name: 'Home Assistant', writes: true });
    view.unmount();

    // The provider returns to the deliveries, signed in again. Effects that run twice still make one token.
    vi.mocked(createPersonalToken).mockResolvedValueOnce({ connection: made, token });
    render(<StrictMode><AccountMenu email="owner@example.test" apiAuth={apiAuth} signIn={methods} /></StrictMode>);
    expect(await screen.findByText(token)).toBeInTheDocument();
    expect(screen.getByRole('heading', { name: 'Assistants and tokens' })).toBeInTheDocument();
    expect(createPersonalToken).toHaveBeenCalledTimes(2);
    expect(createPersonalToken).toHaveBeenLastCalledWith(apiAuth, 'Home Assistant', ['parcels:read', 'parcels:write']);
    expect(note()).toBeNull();
  });

  it('makes nothing for another account that signs in instead, and says so where the token was typed', async () => {
    vi.mocked(readAddByEmail).mockResolvedValue(null);
    vi.mocked(readAssistants).mockResolvedValue(offer([]));
    vi.mocked(createPersonalToken).mockRejectedValueOnce(new ApiRecentSignInError());
    const identity = new AbortController();
    // A code for another account starts its deliveries afresh: this account's requests end.
    const methods = signIn({ verifyCode: vi.fn(async () => { identity.abort(); }) });
    const user = userEvent.setup();
    const view = render(<AccountMenu email="owner@example.test" apiAuth={{ ...apiAuth, signal: identity.signal }} signIn={methods} />);
    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    await user.click(await screen.findByRole('button', { name: 'Assistants and tokens' }));
    await waitFor(() => expect(screen.getByRole('heading', { name: 'Assistants and tokens' })).toHaveFocus());
    await user.type(screen.getByLabelText('Token name'), 'Home Assistant');
    await user.click(screen.getByRole('button', { name: 'Create token' }));
    await user.clear(await screen.findByLabelText('Email address'));
    await user.type(screen.getByLabelText('Email address'), 'other@example.test');
    await user.click(screen.getByRole('button', { name: 'Email me a code' }));
    await user.type(await screen.findByLabelText('Sign-in code'), '123456');
    await user.click(screen.getByRole('button', { name: 'Sign in again' }));
    expect(methods.verifyCode).toHaveBeenCalledWith('other@example.test', '123456');
    expect(createPersonalToken).toHaveBeenCalledOnce();
    expect(note()).toMatchObject({ action: 'personal-token', userId: 'user-a', name: 'Home Assistant', writes: false });
    view.unmount();

    // The other account's Settings, which replace these.
    const other = { userId: 'user-b', getAccessToken: async () => 'other session' };
    render(<AccountMenu email="other@example.test" apiAuth={other} signIn={methods} />);
    expect(await screen.findByText('You’re now signed in as other@example.test, so no token was created. Create it again if it’s for this account.')).toBeInTheDocument();
    expect(await screen.findByLabelText('Token name')).toHaveValue('Home Assistant');
    expect(createPersonalToken).toHaveBeenCalledOnce();
    expect(note()).toBeNull();
  });
});
