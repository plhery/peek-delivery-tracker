import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { afterEach, describe, expect, it, vi } from 'vitest';
import type { ApiAssistantConnection, ApiAssistantsResponse } from '../generated/apiContract';
import { readAddByEmail } from '../lib/addByEmail';
import { createPersonalToken, readAssistants, revokeAssistant } from '../lib/assistants';
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

afterEach(() => vi.clearAllMocks());

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
    const token = `peek_pat_${'x'.repeat(43)}`;
    const made: ApiAssistantConnection = { ...script, id: '6c000000-0000-4000-a000-0000000000b2', name: 'Home Assistant', scopes: ['parcels:read', 'parcels:write'] };
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
});
