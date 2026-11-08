import { render, screen } from '@testing-library/react';
import userEvent, { type UserEvent } from '@testing-library/user-event';
import { StrictMode } from 'react';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { ApiRecentSignInError } from '../lib/apiClient';
import { noteSignInAgain, SIGN_IN_AGAIN_STORAGE_KEY } from '../lib/signInAgain';
import type { SignInMethods } from '../peek/session';
import { AccountMenu } from './AccountMenu';

vi.mock('../lib/addByEmail', () => ({ readAddByEmail: vi.fn(async () => null), openAddByEmail: vi.fn(), closeAddByEmail: vi.fn() }));
vi.mock('../lib/assistants', () => ({ readAssistants: vi.fn(async () => null), createPersonalToken: vi.fn(), revokeAssistant: vi.fn() }));
vi.mock('./NotificationControl', () => ({ NotificationControl: () => null }));

const apiAuth = { userId: 'user-a', getAccessToken: async () => 'session' };
const signIn = (more: Partial<SignInMethods> = {}): SignInMethods => ({
  configured: true, googleEnabled: false, appleEnabled: false, emailOtpEnabled: true, oidcName: null,
  sendCode: vi.fn(async () => undefined), verifyCode: vi.fn(async () => undefined), ...more,
});
const note = () => JSON.parse(window.sessionStorage.getItem(SIGN_IN_AGAIN_STORAGE_KEY) ?? 'null') as Record<string, unknown> | null;

async function askToDelete(user: UserEvent) {
  await user.click(screen.getByLabelText('Account options for owner@example.test'));
  await user.click(screen.getByRole('button', { name: 'Account & data' }));
  await user.click(screen.getByRole('button', { name: 'Delete account' }));
  await user.type(screen.getByLabelText(/type owner@example.test/i), 'owner@example.test');
  await user.click(screen.getByRole('button', { name: 'Permanently delete' }));
}

afterEach(() => window.sessionStorage.clear());

describe('AccountMenu', () => {
  it('keeps demo actions one level deeper and confirms a reset', async () => {
    const onResetDemo = vi.fn().mockResolvedValue(undefined);
    const onExitDemo = vi.fn();
    const user = userEvent.setup();
    render(<AccountMenu onResetDemo={onResetDemo} onExitDemo={onExitDemo} />);
    await user.click(screen.getByRole('button', { name: 'Account' }));
    expect(screen.queryByRole('button', { name: 'Reset demo data' })).not.toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Demo & data' }));
    expect(screen.getByRole('button', { name: 'Exit demo' })).toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Settings' }));
    expect(screen.getByRole('combobox', { name: 'Language' })).toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Demo & data' }));
    await user.click(screen.getByRole('button', { name: 'Reset demo data' }));
    expect(onResetDemo).not.toHaveBeenCalled();
    await user.click(screen.getByRole('button', { name: 'Reset demo data' }));
    expect(onResetDemo).toHaveBeenCalledOnce();
    expect(onExitDemo).not.toHaveBeenCalled();
  });

  it('leads to the landing from the end of Settings, in place for a plain click', async () => {
    const onOpenLanding = vi.fn();
    const user = userEvent.setup();
    const view = render(<AccountMenu email="owner@example.test" onOpenLanding={onOpenLanding} />);
    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    const home = screen.getByRole('link', { name: /^Home page/ });
    expect(home).toHaveAttribute('href', '/home');
    expect(home).toHaveTextContent(window.location.host);
    await user.click(home);
    expect(onOpenLanding).toHaveBeenCalledOnce();
    view.unmount();
    // Where nothing shows the landing, the row is not there.
    render(<AccountMenu email="owner@example.test" />);
    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    expect(screen.queryByRole('link', { name: /^Home page/ })).not.toBeInTheDocument();
  });

  it('shows the account and signs out once', async () => {
    const onSignOut = vi.fn().mockResolvedValue(undefined);
    const user = userEvent.setup();
    render(<AccountMenu email="owner@example.test" onSignOut={onSignOut} />);

    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    expect(screen.getByText('owner@example.test')).toBeInTheDocument();
    expect(screen.getByRole('combobox', { name: 'Language' })).toBeInTheDocument();
    expect(screen.queryByRole('button', { name: 'Reset demo data' })).not.toBeInTheDocument();
    expect(screen.queryByRole('checkbox')).not.toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Account & data' }));
    expect(screen.getByRole('link', { name: 'Privacy notice' }))
      .toHaveAttribute('href', '/privacy.html');
    await user.click(screen.getByRole('button', { name: 'Sign out' }));

    expect(onSignOut).toHaveBeenCalledOnce();
    expect(screen.getByRole('button', { name: 'Signing out…' })).toBeDisabled();
  });

  it('keeps sign-out failures actionable', async () => {
    const user = userEvent.setup();
    render(
      <AccountMenu
        email="owner@example.test"
        onSignOut={vi.fn().mockRejectedValue(new Error('Network unavailable'))}
      />,
    );

    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    await user.click(screen.getByRole('button', { name: 'Account & data' }));
    await user.click(screen.getByRole('button', { name: 'Sign out' }));

    expect(await screen.findByRole('alert')).toHaveTextContent('Check your internet connection and try again.');
    expect(screen.getByRole('button', { name: 'Sign out' })).toBeEnabled();
  });

  it('exports data and requires the account email before deletion', async () => {
    const onExport = vi.fn().mockResolvedValue(undefined);
    const onDelete = vi.fn().mockResolvedValue(undefined);
    const user = userEvent.setup();
    render(
      <AccountMenu
        email="owner@example.test"
        onExport={onExport}
        onDelete={onDelete}
        onSignOut={vi.fn()}
      />,
    );

    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    await user.click(screen.getByRole('button', { name: 'Account & data' }));
    await user.click(screen.getByRole('button', { name: 'Download my data' }));
    expect(onExport).toHaveBeenCalledOnce();

    await user.click(screen.getByRole('button', { name: 'Delete account' }));
    const deleteButton = screen.getByRole('button', { name: 'Permanently delete' });
    expect(deleteButton).toBeDisabled();
    await user.type(screen.getByLabelText(/type owner@example.test/i), 'Owner@Example.Test');
    expect(deleteButton).toBeEnabled();
    await user.click(deleteButton);
    expect(onDelete).toHaveBeenCalledWith('Owner@Example.Test');
  });

  it('asks for a sign-in in place before deleting, then for the confirmation once more', async () => {
    const onDelete = vi.fn().mockRejectedValueOnce(new ApiRecentSignInError()).mockResolvedValueOnce(undefined);
    const methods = signIn();
    const user = userEvent.setup();
    render(<AccountMenu email="owner@example.test" apiAuth={apiAuth} signIn={methods} onDelete={onDelete} onSignOut={vi.fn()} />);
    await askToDelete(user);
    expect(await screen.findByRole('heading', { name: 'Sign in again to delete your account' })).toBeInTheDocument();
    expect(screen.getByText('Deleting can’t be undone, so Peek checks it’s still you.')).toBeInTheDocument();
    expect(onDelete).toHaveBeenCalledOnce();
    expect(screen.getByLabelText('Email address')).toHaveValue('owner@example.test');
    await user.click(screen.getByRole('button', { name: 'Email me a code' }));
    await user.type(await screen.findByLabelText('Sign-in code'), '123456');
    await user.click(screen.getByRole('button', { name: 'Sign in again' }));
    expect(methods.verifyCode).toHaveBeenCalledWith('owner@example.test', '123456');
    // Signed in again, nothing is deleted until it is confirmed again.
    expect(await screen.findByRole('heading', { name: 'Delete this account permanently?' })).toBeInTheDocument();
    expect(screen.getByLabelText(/type owner@example.test/i)).toHaveValue('owner@example.test');
    expect(onDelete).toHaveBeenCalledOnce();
    await user.click(screen.getByRole('button', { name: 'Permanently delete' }));
    expect(onDelete).toHaveBeenCalledTimes(2);
    expect(onDelete).toHaveBeenLastCalledWith('owner@example.test');
    expect(note()).toBeNull();
  });

  it('leaves the deletion when the sign-in asked for again is cancelled', async () => {
    const onDelete = vi.fn().mockRejectedValueOnce(new ApiRecentSignInError());
    const user = userEvent.setup();
    render(<AccountMenu email="owner@example.test" apiAuth={apiAuth} signIn={signIn()} onDelete={onDelete} />);
    await askToDelete(user);
    await user.click(await screen.findByRole('button', { name: 'Cancel' }));
    expect(screen.queryByRole('heading', { name: 'Delete this account permanently?' })).not.toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Delete account' })).toBeInTheDocument();
    expect(onDelete).toHaveBeenCalledOnce();
  });

  it('asks to confirm the deletion once more after a provider\'s round trip, and never deletes by itself', async () => {
    const onDelete = vi.fn().mockRejectedValueOnce(new ApiRecentSignInError()).mockResolvedValueOnce(undefined);
    const signInWithGoogle = vi.fn(async () => undefined);
    const methods = signIn({ googleEnabled: true, signInWithGoogle });
    const user = userEvent.setup();
    const view = render(<AccountMenu email="owner@example.test" apiAuth={apiAuth} signIn={methods} onDelete={onDelete} />);
    await askToDelete(user);
    await user.click(await screen.findByRole('button', { name: 'Continue with Google' }));
    expect(signInWithGoogle).toHaveBeenCalledOnce();
    expect(note()).toMatchObject({ action: 'delete-account', userId: 'user-a' });
    view.unmount();

    // The provider returns to the deliveries, signed in again.
    render(<StrictMode><AccountMenu email="owner@example.test" apiAuth={apiAuth} signIn={methods} onDelete={onDelete} /></StrictMode>);
    expect(await screen.findByRole('heading', { name: 'Delete this account permanently?' })).toBeInTheDocument();
    expect(screen.getByLabelText(/type owner@example.test/i)).toHaveValue('owner@example.test');
    expect(onDelete).toHaveBeenCalledOnce();
    expect(note()).toBeNull();
    await user.click(screen.getByRole('button', { name: 'Permanently delete' }));
    expect(onDelete).toHaveBeenCalledTimes(2);
  });

  it('tells another account back from the provider that nothing was deleted', async () => {
    noteSignInAgain({ action: 'delete-account', userId: 'user-a' });
    const onDelete = vi.fn();
    render(<AccountMenu email="other@example.test" apiAuth={{ userId: 'user-b', getAccessToken: async () => 'other session' }}
      signIn={signIn()} onDelete={onDelete} />);
    expect(await screen.findByText('You’re now signed in as other@example.test, so nothing was deleted.')).toBeInTheDocument();
    expect(screen.queryByRole('heading', { name: 'Delete this account permanently?' })).not.toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Delete account' })).toBeInTheDocument();
    expect(onDelete).not.toHaveBeenCalled();
    expect(note()).toBeNull();
  });
});
