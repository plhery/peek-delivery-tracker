import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, describe, expect, it, vi } from 'vitest';
import type { ApiAssistantConnection, ApiAssistantsResponse } from '../generated/apiContract';
import { readAddByEmail } from '../lib/addByEmail';
import { readAssistants, revokeAssistant } from '../lib/assistants';
import { AccountMenu } from './AccountMenu';
import { AssistantsPage } from './Assistants';

vi.mock('../lib/addByEmail', () => ({ readAddByEmail: vi.fn(), openAddByEmail: vi.fn(), closeAddByEmail: vi.fn() }));
vi.mock('../lib/assistants', () => ({ readAssistants: vi.fn(), revokeAssistant: vi.fn() }));
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

afterEach(() => { vi.clearAllMocks(); });

describe('assistants in Settings', () => {
  it('shows no row while the server connects no assistant to the account', async () => {
    vi.mocked(readAddByEmail).mockResolvedValue(null);
    vi.mocked(readAssistants).mockResolvedValue(null);
    const user = userEvent.setup();
    render(<AccountMenu email="owner@example.test" apiAuth={apiAuth} />);
    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    await waitFor(() => expect(readAssistants).toHaveBeenCalledWith(apiAuth));
    expect(screen.queryByRole('button', { name: /^Assistants/ })).not.toBeInTheDocument();
  });

  it('lists the assistants connected, with the address to give one, marked experimental, and disconnects one', async () => {
    vi.mocked(readAddByEmail).mockResolvedValue(null);
    vi.mocked(readAssistants).mockResolvedValue(offer([claude, script]));
    vi.mocked(revokeAssistant).mockResolvedValue();
    const copy = vi.spyOn(navigator.clipboard, 'writeText').mockResolvedValue();
    const user = userEvent.setup();
    render(<AccountMenu email="owner@example.test" apiAuth={apiAuth} />);
    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    await user.click(await screen.findByRole('button', { name: /^Assistants ?Experimental$/ }));
    expect(screen.getByRole('heading', { name: 'Assistants Experimental' })).toBeInTheDocument();
    expect(screen.getByText(/This feature is experimental, and it may change\.$/)).toBeInTheDocument();
    expect(screen.getByText('https://peek.example/mcp')).toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Copy address' }));
    expect(copy).toHaveBeenCalledWith('https://peek.example/mcp');
    expect(screen.getByText('claude.example · Sees, adds and archives parcels · not used yet')).toBeInTheDocument();
    // Personal tokens have no screen: none is listed, and none can be made.
    expect(screen.queryByText('Home script')).not.toBeInTheDocument();
    expect(screen.queryByText(/token|Home Assistant|\/api\/parcels/i)).not.toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Remove Claude' }));
    expect(revokeAssistant).toHaveBeenCalledWith(apiAuth, claude.id);
    expect(await screen.findByText('No assistant is connected yet.')).toBeInTheDocument();
  });

  it('shows how to add Peek to Claude, ChatGPT and Gemini, in their own words, and what to ask', async () => {
    const user = userEvent.setup();
    render(<AssistantsPage apiAuth={apiAuth} offer={offer([])} onChange={vi.fn()} />);
    const setup = screen.getByRole('region', { name: 'Add Peek to your assistant' });
    const names = Array.from(setup.querySelectorAll('summary')).map((summary) => summary.textContent);
    expect(names).toEqual(['Claude›', 'ChatGPT›', 'Gemini›']);
    await user.click(screen.getByText('Gemini'));
    const steps = Array.from(setup.querySelectorAll('details[open] li')).map((step) => step.textContent);
    expect(steps).toEqual([
      'Open Settings, then Connected Apps.',
      'Under Custom apps, paste the address above and select Next.',
      'Sign in to Peek when it asks, then select Allow.',
      'In a chat, type @ and pick Peek.',
    ]);
    // The assistant's own names stay as its help pages give them, out of a page translator's reach.
    expect(screen.getByText('Connected Apps')).toHaveAttribute('translate', 'no');
    const uses = screen.getByRole('region', { name: 'What to ask it' });
    expect(uses.querySelector('li')).toHaveTextContent('Ask it to look through your emails and add the tracking numbers it finds. This works when it can read your mail too, for example through its Gmail connector.');
  });
});
