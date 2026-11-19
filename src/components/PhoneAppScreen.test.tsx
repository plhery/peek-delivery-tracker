import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import de from '../../shared/locales/de.json';
import ru from '../../shared/locales/ru.json';
import { guideAddresses } from '../guides/guideAddresses';
import { PhoneAppScreen } from './PhoneAppScreen';

const mocks = vi.hoisted(() => ({ track: vi.fn(), screen: vi.fn(), start: vi.fn(async () => undefined), iPad: vi.fn(() => false) }));
vi.mock('../lib/analytics', async (original) => ({
  ...await original<typeof import('../lib/analytics')>(), trackAction: mocks.track, trackScreen: mocks.screen, startAnalytics: mocks.start,
}));
vi.mock('../lib/appStore', async (original) => ({ ...await original<typeof import('../lib/appStore')>(), onIPad: mocks.iPad }));

beforeEach(() => {
  mocks.track.mockReset();
  mocks.screen.mockReset();
  mocks.start.mockClear();
  mocks.iPad.mockReturnValue(false);
});

describe('the page of the code for both phones', () => {
  it('gives a computer the code to scan and both apps, each link carrying where the visitor came from', async () => {
    render(<PhoneAppScreen source="landing-qr" />);
    expect(screen.getByRole('heading', { level: 1, name: 'Get the app' })).toBeVisible();
    expect(screen.getByText('Scan with your phone').closest('.phone-app__qr')?.querySelector('img')).toHaveAttribute('src', '/landing/app-qr.svg');
    expect(screen.getByRole('link', { name: 'iPhone app' })).toHaveAttribute('href', 'https://apps.apple.com/app/apple-store/id6823615370?pt=129628155&ct=web-qr&mt=8');
    const android = screen.getByRole('link', { name: 'Android app' });
    expect(android).toHaveAttribute('href', '/android?utm_source=landing-qr');
    expect(screen.getByRole('link', { name: 'CC BY 3.0' }).parentElement).toHaveTextContent('The Android robot is adapted from work by Google, shared under CC BY 3.0.');
    expect(mocks.screen).toHaveBeenCalledExactlyOnceWith('get-app', 'anonymous');

    android.addEventListener('click', (event) => event.preventDefault());
    await userEvent.setup().click(android);
    expect(mocks.track).toHaveBeenCalledExactlyOnceWith('android-test-open', undefined, 'get-app');
  });

  it('links both apps’ guides, in the visitor’s language or the nearest one they are written in', () => {
    const guides = { iphone: guideAddresses('iphone-app'), android: guideAddresses('android-app') };
    const { unmount } = render(<PhoneAppScreen guides={guides} />);
    expect(screen.getByRole('link', { name: /^Peek for iPhone, iPad and Mac/ })).toHaveAttribute('href', '/guides/parcel-tracker-app-iphone');
    expect(screen.getByRole('link', { name: /^Peek for Android/ })).toHaveAttribute('href', '/guides/parcel-tracker-app-android');
    expect(screen.getByRole('link', { name: /^Peek for Android/ })).not.toHaveAttribute('hreflang');
    unmount();

    const { unmount: away } = render(<PhoneAppScreen guides={guides} initialLocale="de" initialMessages={de} />);
    expect(screen.getByRole('link', { name: /^Peek für iPhone/ })).toHaveAttribute('href', '/de/guides/sendungsverfolgung-app-iphone');
    away();
    // No guide is written in Russian: the English one, which the link names.
    render(<PhoneAppScreen guides={guides} initialLocale="ru" initialMessages={ru} />);
    expect(screen.getByRole('link', { name: /^Peek for Android/ })).toHaveAttribute('hreflang', 'en');
  });

  it('counts the App Store as the address itself when it came without the code’s source', () => {
    render(<PhoneAppScreen />);
    expect(screen.getByRole('link', { name: 'iPhone app' })).toHaveAttribute('href', expect.stringContaining('&ct=web-app&'));
    expect(screen.getByRole('link', { name: 'Android app' })).toHaveAttribute('href', '/android');
  });

  it('hides itself on an iPad, which goes on to the App Store without counting a view', () => {
    mocks.iPad.mockReturnValue(true);
    // jsdom does not navigate: the page only has to have asked.
    const { container } = render(<PhoneAppScreen source="landing-qr" />);
    expect(container.querySelector('.phone-app')).not.toBeVisible();
    expect(mocks.screen).not.toHaveBeenCalled();
  });
});
