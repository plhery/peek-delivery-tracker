import { render, screen } from '@testing-library/react';
import { renderOnPeek } from '../test/site';
import userEvent from '@testing-library/user-event';
import { describe, expect, it, vi } from 'vitest';
import { PLAIN_SITE } from '../lib/site';
import { SiteProvider } from '../SiteContext';
import { SignInScreen } from './SignInScreen';

describe('SignInScreen', () => {
  it('offers a sole custom provider without email, Google or Apple', async () => {
    const signInWithOidc = vi.fn().mockResolvedValue(undefined);
    renderOnPeek(<SignInScreen configured oidcName="Authentik" signInWithOidc={signInWithOidc}
      emailOtpEnabled={false} sendCode={vi.fn()} verifyCode={vi.fn()} />);
    expect(screen.queryByLabelText('Email address')).not.toBeInTheDocument();
    expect(screen.getAllByRole('button')).toHaveLength(1);
    await userEvent.setup().click(screen.getByRole('button', { name: 'Continue with Authentik' }));
    expect(signInWithOidc).toHaveBeenCalledOnce();
    expect(screen.getByRole('button', { name: 'Opening Authentik…' })).toHaveAttribute('aria-busy', 'true');
  });

  it('offers OIDC alongside social providers, prevents duplicate starts and retries translated failures', async () => {
    let reject!: (error: Error) => void;
    const signInWithOidc = vi.fn().mockImplementationOnce(() => new Promise((_, fail) => { reject = fail; }))
      .mockResolvedValue(undefined);
    const user = userEvent.setup();
    renderOnPeek(<SignInScreen configured oidcName="Authentik" signInWithOidc={signInWithOidc}
      googleEnabled appleEnabled signInWithGoogle={vi.fn()} signInWithApple={vi.fn()}
      sendCode={vi.fn()} verifyCode={vi.fn()} />);
    await user.click(screen.getByRole('button', { name: 'Continue with Authentik' }));
    for (const button of screen.getAllByRole('button')) expect(button).toBeDisabled();
    await user.click(screen.getByRole('button', { name: 'Opening Authentik…' }));
    expect(signInWithOidc).toHaveBeenCalledOnce();
    reject(new Error('Internal provider diagnostics'));
    expect(await screen.findByRole('alert')).toHaveTextContent('Couldn’t open Authentik sign-in. Try again.');
    await user.click(screen.getByRole('button', { name: 'Continue with Authentik' }));
    expect(signInWithOidc).toHaveBeenCalledTimes(2);
  });

  it('hides an unconfigured custom provider', () => {
    renderOnPeek(<SignInScreen configured signInWithOidc={vi.fn()} sendCode={vi.fn()} verifyCode={vi.fn()} />);
    expect(screen.queryByRole('button', { name: /Continue with/ })).not.toBeInTheDocument();
  });

  it('renders the configured provider name as text', () => {
    renderOnPeek(<SignInScreen configured oidcName={'<img src=x onerror=alert(1)>'} signInWithOidc={vi.fn()}
      sendCode={vi.fn()} verifyCode={vi.fn()} />);
    const button = screen.getByRole('button', { name: 'Continue with <img src=x onerror=alert(1)>' });
    expect(button.querySelector('img')).toBeNull();
  });

  it('requests and verifies an emailed one-time code', async () => {
    const sendCode = vi.fn().mockResolvedValue(undefined);
    const verifyCode = vi.fn().mockResolvedValue(undefined);
    const user = userEvent.setup();
    renderOnPeek(
      <SignInScreen card configured sendCode={sendCode} verifyCode={verifyCode} />,
    );

    expect(screen.getByRole('heading', {
      name: 'Sign in',
    })).toBeInTheDocument();
    expect(screen.getByText('Keep your deliveries synced.'))
      .toBeInTheDocument();
    expect(screen.getByText('Your tracking details stay private.')).toBeInTheDocument();
    expect(screen.getByRole('link', { name: 'Privacy' }))
      .toHaveAttribute('href', '/privacy.html');

    await user.type(screen.getByLabelText('Email address'), 'Owner@Example.Test');
    await user.click(screen.getByRole('button', { name: 'Email me a code' }));
    expect(sendCode).toHaveBeenCalledWith('owner@example.test');

    const code = screen.getByLabelText('Sign-in code');
    expect(code).toHaveAttribute('autocomplete', 'one-time-code');
    await user.type(code, '12a 3456');
    await user.click(screen.getByRole('button', { name: "View my parcels" }));
    expect(verifyCode).toHaveBeenCalledWith('owner@example.test', '123456');
  });

  it('links the privacy notice only where the site names one', () => {
    const { unmount } = render(<SignInScreen card configured sendCode={vi.fn()} verifyCode={vi.fn()} />);
    expect(screen.getByText('Your tracking details stay private.')).toBeInTheDocument();
    expect(screen.queryByRole('link', { name: 'Privacy' })).toBeNull();
    unmount();
    render(
      <SiteProvider site={{ ...PLAIN_SITE, privacy: 'https://example.com/privacy' }}>
        <SignInScreen card configured sendCode={vi.fn()} verifyCode={vi.fn()} />
      </SiteProvider>,
    );
    expect(screen.getByRole('link', { name: 'Privacy' })).toHaveAttribute('href', 'https://example.com/privacy');
  });

  it('asks the one address that has a password for it instead of sending a code', async () => {
    const sendCode = vi.fn().mockResolvedValue(undefined);
    const signInWithPassword = vi.fn().mockRejectedValueOnce(Object.assign(new Error('Invalid login credentials'), { status: 400 }))
      .mockResolvedValue(undefined);
    const passwordFor = vi.fn(async (email: string) => email === 'review@example.test');
    const user = userEvent.setup();
    renderOnPeek(<SignInScreen card configured sendCode={sendCode} verifyCode={vi.fn()} passwordFor={passwordFor}
      signInWithPassword={signInWithPassword} />);

    await user.type(screen.getByLabelText('Email address'), 'owner@example.test');
    await user.click(screen.getByRole('button', { name: 'Email me a code' }));
    expect(sendCode).toHaveBeenCalledWith('owner@example.test');
    expect(screen.queryByLabelText('Password')).not.toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Use a different email' }));

    await user.clear(screen.getByLabelText('Email address'));
    await user.type(screen.getByLabelText('Email address'), 'Review@Example.Test');
    await user.click(screen.getByRole('button', { name: 'Email me a code' }));
    expect(passwordFor).toHaveBeenLastCalledWith('review@example.test');
    expect(sendCode).toHaveBeenCalledOnce();
    expect(screen.getByText('Enter the password for Review@Example.Test.')).toBeInTheDocument();
    const password = screen.getByLabelText('Password');
    expect(password).toHaveAttribute('type', 'password');
    expect(password).toHaveAttribute('autocomplete', 'current-password');
    await user.type(password, 'wrong password');
    await user.click(screen.getByRole('button', { name: 'View my parcels' }));
    expect(await screen.findByRole('alert')).toHaveTextContent('Couldn’t sign you in. Check the password and try again.');
    await user.clear(password);
    await user.type(password, 'the right password');
    await user.click(screen.getByRole('button', { name: 'View my parcels' }));
    expect(signInWithPassword).toHaveBeenLastCalledWith('review@example.test', 'the right password');
  });

  it('keeps provider errors actionable', async () => {
    const sendCode = vi.fn().mockRejectedValue(new Error('Email rate limit reached'));
    const user = userEvent.setup();
    renderOnPeek(
      <SignInScreen configured sendCode={sendCode} verifyCode={vi.fn()} />,
    );
    await user.type(screen.getByLabelText('Email address'), 'owner@example.test');
    await user.click(screen.getByRole('button', { name: 'Email me a code' }));
    expect(await screen.findByRole('alert')).toHaveTextContent('Please wait a moment before trying again.');
  });

  it('supports Google-only production sign-in', async () => {
    const signInWithGoogle = vi.fn().mockResolvedValue(undefined);
    const user = userEvent.setup();
    renderOnPeek(
      <SignInScreen
        configured
        googleEnabled
        emailOtpEnabled={false}
        signInWithGoogle={signInWithGoogle}
        sendCode={vi.fn()}
        verifyCode={vi.fn()}
      />,
    );

    expect(screen.queryByLabelText('Email address')).not.toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Continue with Google' }));
    expect(signInWithGoogle).toHaveBeenCalledOnce();
  });

  it('offers Apple alongside Google, prevents duplicate starts, and allows retry after failure', async () => {
    let reject!: (error: Error) => void;
    const signInWithApple = vi.fn().mockImplementationOnce(() => new Promise((_, fail) => { reject = fail; }))
      .mockResolvedValue(undefined);
    const signInWithGoogle = vi.fn();
    const user = userEvent.setup();
    renderOnPeek(<SignInScreen configured appleEnabled googleEnabled signInWithApple={signInWithApple}
      signInWithGoogle={signInWithGoogle} sendCode={vi.fn()} verifyCode={vi.fn()} />);
    expect(screen.getAllByRole('button', { name: /^Continue with / }).map((button) => button.textContent))
      .toEqual(['Continue with Google', 'Continue with Apple']);
    await user.click(screen.getByRole('button', { name: 'Continue with Apple' }));
    expect(screen.getByRole('button', { name: 'Opening Apple…' })).toBeDisabled();
    expect(screen.getByRole('button', { name: 'Continue with Google' })).toBeDisabled();
    expect(screen.getByRole('button', { name: 'Sign in with email' })).toBeDisabled();
    await user.click(screen.getByRole('button', { name: 'Opening Apple…' }));
    expect(signInWithApple).toHaveBeenCalledOnce();
    reject(new Error('Provider configuration detail'));
    expect(await screen.findByRole('alert')).toHaveTextContent('Couldn’t open Apple sign-in. Try again.');
    await user.click(screen.getByRole('button', { name: 'Continue with Apple' }));
    expect(signInWithApple).toHaveBeenCalledTimes(2);
    expect(signInWithGoogle).not.toHaveBeenCalled();
  });

  it('keeps email secondary with Apple as the only social provider', async () => {
    const user = userEvent.setup();
    renderOnPeek(<SignInScreen configured appleEnabled signInWithApple={vi.fn()} sendCode={vi.fn()} verifyCode={vi.fn()} />);
    expect(screen.queryByLabelText('Email address')).not.toBeInTheDocument();
    expect(screen.queryByRole('button', { name: 'Continue with Google' })).not.toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Sign in with email' }));
    expect(screen.getByLabelText('Email address')).toHaveFocus();
  });

  it('hides Apple until the provider is configured', () => {
    renderOnPeek(<SignInScreen configured signInWithApple={vi.fn()} sendCode={vi.fn()} verifyCode={vi.fn()} />);
    expect(screen.queryByRole('button', { name: 'Continue with Apple' })).not.toBeInTheDocument();
  });

  it('keeps email secondary until the user asks for it', async () => {
    const user = userEvent.setup();
    renderOnPeek(
      <SignInScreen
        configured
        googleEnabled
        emailOtpEnabled
        signInWithGoogle={vi.fn()}
        sendCode={vi.fn()}
        verifyCode={vi.fn()}
      />,
    );

    expect(screen.queryByLabelText('Email address')).not.toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Sign in with email' }));
    expect(screen.getByLabelText('Email address')).toHaveFocus();
  });

  it('explains missing deployment configuration', () => {
    renderOnPeek(
      <SignInScreen configured={false} sendCode={vi.fn()} verifyCode={vi.fn()} />,
    );
    expect(screen.getByRole('alert')).toHaveTextContent(
      'You can explore the app with demo parcels while sign-in is unavailable.',
    );
  });
});
