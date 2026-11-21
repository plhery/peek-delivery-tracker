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

  it('lists the assistants connected, with the address to give one, marked beta, and disconnects one', async () => {
    vi.mocked(readAddByEmail).mockResolvedValue(null);
    // A connection from before editing had a scope of its own, and one allowed everything.
    const chatgpt: ApiAssistantConnection = { ...claude, id: '6c000000-0000-4000-a000-0000000000a2', name: 'ChatGPT', redirectHost: 'chatgpt.example',
      scopes: ['parcels:read', 'parcels:write', 'parcels:edit'] };
    vi.mocked(readAssistants).mockResolvedValue(offer([claude, chatgpt, script]));
    vi.mocked(revokeAssistant).mockResolvedValue();
    const copy = vi.spyOn(navigator.clipboard, 'writeText').mockResolvedValue();
    const user = userEvent.setup();
    render(<AccountMenu email="owner@example.test" apiAuth={apiAuth} />);
    await user.click(screen.getByLabelText('Account options for owner@example.test'));
    await user.click(await screen.findByRole('button', { name: /^Assistants ?Beta$/ }));
    expect(screen.getByRole('heading', { name: 'Assistants Beta' })).toBeInTheDocument();
    expect(screen.getByText(/This feature is in beta, and it may still change\./)).toBeInTheDocument();
    // The public page on connecting an assistant says the same and more, in a tab of its own.
    const how = screen.getByRole('link', { name: 'How it works' });
    expect(how).toHaveAttribute('href', '/assistants');
    expect(how).toHaveAttribute('target', '_blank');
    expect(screen.getByText('https://peek.example/mcp')).toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Copy address' }));
    expect(copy).toHaveBeenCalledWith('https://peek.example/mcp');
    expect(screen.getByText('claude.example · Sees and adds parcels · not used yet')).toBeInTheDocument();
    expect(screen.getByText('chatgpt.example · Sees, adds, edits and archives parcels · not used yet')).toBeInTheDocument();
    // Personal tokens have no screen: none is listed, and none can be made.
    expect(screen.queryByText('Home script')).not.toBeInTheDocument();
    expect(screen.queryByText(/token|\/api\/parcels/i)).not.toBeInTheDocument();
    await user.click(screen.getByRole('button', { name: 'Remove Claude' }));
    expect(revokeAssistant).toHaveBeenCalledWith(apiAuth, claude.id);
    await user.click(await screen.findByRole('button', { name: 'Remove ChatGPT' }));
    expect(revokeAssistant).toHaveBeenCalledWith(apiAuth, chatgpt.id);
    // The empty list says so in a plain line: Pip stays over the address only.
    expect((await screen.findByText('No assistant is connected yet.')).closest('.pip-empty')).toBeNull();
    expect(document.querySelectorAll('.small-pip')).toHaveLength(1);
  });

  it('shows how to add Peek to Claude, ChatGPT, Gemini, OpenClaw and Home Assistant, in their own words, and what to ask', async () => {
    const user = userEvent.setup();
    render(<AssistantsPage apiAuth={apiAuth} offer={offer([])} onChange={vi.fn()} />);
    const setup = screen.getByRole('region', { name: 'Add Peek to your assistant' });
    const names = Array.from(setup.querySelectorAll('summary')).map((summary) => summary.textContent);
    expect(names).toEqual(['Claude›', 'ChatGPT›', 'Gemini›', 'OpenClaw›', 'Home Assistant›']);
    // Claude finds Peek in its connector directory, with a custom connector where it doesn't list it.
    const claudeSetup = setup.querySelector('details');
    expect(Array.from(claudeSetup?.querySelectorAll('li') ?? []).map((step) => step.textContent)).toEqual([
      'Open Peek Parcel Tracker in Claude’s connector directory, or in Claude open Customize › Connectors, select + and search for Peek.',
      'Select Connect.',
      'Sign in to Peek when it asks, then select Allow.',
      'In a chat, select + at the bottom left, then Connectors, and turn Peek on.',
    ]);
    expect(screen.getByRole('link', { name: 'Peek Parcel Tracker' })).toHaveAttribute('href', 'https://claude.ai/directory/peek-parcel-tracker');
    expect(claudeSetup?.querySelector('.assistants__fallback')).toHaveTextContent(
      'If Claude doesn’t list Peek Parcel Tracker, add Peek as a custom connector instead, on claude.ai or in the desktop app '
      + '(not in the phone apps; the Free plan allows one custom connector): open Customize › Connectors, select + Add, then Add custom '
      + 'connector. Name it Peek, paste the address above and select Continue. Keep the sign-in settings Claude finds: select '
      + 'Continue, then Add.',
    );
    await user.click(screen.getByText('Gemini'));
    const steps = Array.from(setup.querySelectorAll('details[open] li')).map((step) => step.textContent);
    expect(steps).toEqual([
      'Open Settings, then Connected Apps.',
      'Under Custom apps, paste the address above and select Next.',
      'Sign in to Peek when it asks, then select Allow.',
      'In a chat, type @ and pick Peek.',
    ]);
    // OpenClaw saves Peek from a terminal while its Gateway is stopped, with the address in the command to type.
    await user.click(screen.getByText('OpenClaw'));
    const openClaw = Array.from(setup.querySelectorAll('details')).find((details) => details.querySelector('summary')?.textContent === 'OpenClaw›');
    expect(Array.from(openClaw?.querySelectorAll('li') ?? []).map((step) => step.textContent)).toEqual([
      'Stop OpenClaw’s Gateway with openclaw gateway stop, then add Peek with openclaw mcp set peek '
      + '\'{"url":"https://peek.example/mcp","transport":"streamable-http","auth":"oauth"}\'.',
      'Run openclaw mcp login peek and open the link it prints.',
      'Sign in to Peek when it asks, then select Allow.',
      'Start the Gateway again with openclaw gateway start, then ask your agent about your parcels.',
    ]);
    expect(openClaw?.querySelector('.assistants__fallback')).toHaveTextContent(
      'If your browser is on another device, the page it opens after you sign in won’t load: finish with the code in that '
      + 'page’s address, as OpenClaw explains in the terminal.',
    );
    const login = screen.getByText('openclaw mcp login peek');
    expect(login.tagName).toBe('CODE');
    expect(login).toHaveAttribute('translate', 'no');
    // Home Assistant connects through Peek's integration, and its card shows once the page is reloaded.
    await user.click(screen.getByText('Home Assistant'));
    const homeAssistant = Array.from(setup.querySelectorAll('details')).at(-1);
    expect(Array.from(homeAssistant?.querySelectorAll('li') ?? []).map((step) => step.textContent)).toEqual([
      'Open Settings › Marketplace, search for Peek, open it and select Install, then Set up. If Peek isn’t listed, select Add from link, paste https://github.com/plhery/peek-ha and select Add.',
      'Sign in to Peek when it asks, then select Allow.',
      'Reload the page. Then edit a dashboard, select Add card, then By card, and pick Peek parcels.',
    ]);
    // The assistant's own names stay as its help pages give them, out of a page translator's reach.
    expect(screen.getByText('Connected Apps')).toHaveAttribute('translate', 'no');
    const uses = screen.getByRole('region', { name: 'What to ask it' });
    expect(uses.querySelector('li')).toHaveTextContent('Ask it to look through your emails and add the tracking numbers it finds. This works when it can read your mail too, for example through its Gmail connector.');
  });
});
