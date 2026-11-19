import { Fragment } from 'react';
import type { MessageKey, Translate } from '../lib/messages';

/**
 * The name of an assistant's menu or button, a page of its own that the line links to, or a command to type, made from
 * Peek's address for assistants where it holds it.
 */
export type SetupLabel = string | { readonly text: string; readonly href: string } | { readonly command: string | ((address: string) => string) };

/** A line of a setup: its text, and the names of the assistant's menus and buttons its `{{…}}` stand for. */
export type SetupLine = readonly [MessageKey, Readonly<Record<string, SetupLabel>>?];

/** Claude's own connectors menu, in its directory steps and its custom-connector ones alike. */
const CLAUDE_CONNECTORS = 'Customize › Connectors';

/**
 * How to add Peek to each assistant, from its own help pages: Peek Parcel Tracker's page in Claude's connector
 * directory and Anthropic's "Use connectors to extend Claude's capabilities", with "Get started with custom connectors
 * using remote MCP" for the custom connector Claude falls back on; OpenAI's "Add custom MCP server" and "Connect and
 * test your plugin"; Google's "Connect & manage custom apps for Gemini Apps"; and OpenClaw's "Transports and OAuth"
 * and "Manage saved MCP servers" (docs.openclaw.ai/cli/mcp), whose commands its released CLI runs only while the
 * Gateway is stopped. Home Assistant connects through Peek's integration (github.com/plhery/peek-ha), whose README gives
 * its steps, named as Home Assistant 2026.11 does. Their menus, buttons and commands are named in English, as those
 * pages give them; `who` says which plans and places can, and `fallback` what to do where the steps don't apply.
 * Settings shows them, and so does the page on connecting an assistant.
 */
export const ASSISTANT_SETUPS: ReadonlyArray<{ name: string; who: SetupLine; steps: readonly SetupLine[]; fallback?: SetupLine }> = [
  {
    name: 'Claude',
    who: ['assistants.setup.claude.who', { organization: 'Organization settings › Connectors', browse: 'Browse connectors', team: 'Add to your team' }],
    steps: [
      ['assistants.setup.claude.open', {
        listing: { text: 'Peek Parcel Tracker', href: 'https://claude.ai/directory/peek-parcel-tracker' }, connectors: CLAUDE_CONNECTORS, plus: '+',
      }],
      ['assistants.setup.claude.add', { connect: 'Connect' }],
      ['assistants.setup.claude.use', { plus: '+', connectors: 'Connectors' }],
    ],
    fallback: ['assistants.setup.claude.custom', {
      connectors: CLAUDE_CONNECTORS, new: '+ Add', custom: 'Add custom connector', continue: 'Continue', add: 'Add',
    }],
  },
  {
    name: 'ChatGPT',
    who: ['assistants.setup.chatgpt.who'],
    steps: [
      ['assistants.setup.chatgpt.open', { plugins: 'chatgpt.com/plugins', plus: '+', custom: 'Add custom MCP server' }],
      ['assistants.setup.chatgpt.add', { oauth: 'OAuth', accept: 'I understand and want to continue', create: 'Create as a plugin' }],
      ['assistants.setup.chatgpt.use', { plus: '+' }],
    ],
  },
  {
    name: 'Gemini',
    who: ['assistants.setup.gemini.who'],
    steps: [
      ['assistants.setup.gemini.open', { settings: 'Settings', apps: 'Connected Apps' }],
      ['assistants.setup.gemini.add', { custom: 'Custom apps', next: 'Next' }],
      ['assistants.setup.gemini.use'],
    ],
  },
  {
    name: 'OpenClaw',
    who: ['assistants.setup.openclaw.who'],
    steps: [
      ['assistants.setup.openclaw.open', {
        stop: { command: 'openclaw gateway stop' },
        set: { command: (address) => `openclaw mcp set peek '${JSON.stringify({ url: address, transport: 'streamable-http', auth: 'oauth' })}'` },
      }],
      ['assistants.setup.openclaw.add', { login: { command: 'openclaw mcp login peek' } }],
      ['assistants.setup.openclaw.use', { start: { command: 'openclaw gateway start' } }],
    ],
    fallback: ['assistants.setup.openclaw.remote'],
  },
  {
    name: 'Home Assistant',
    who: ['assistants.setup.homeAssistant.who'],
    steps: [
      ['assistants.setup.homeAssistant.open', {
        marketplace: 'Settings › Marketplace', link: 'Add from link', repository: 'https://github.com/plhery/peek-ha', add: 'Add',
        setUp: 'Set up',
      }],
      ['assistants.setup.homeAssistant.use', { addCard: 'Add card', byCard: 'By card', card: 'Peek parcels' }],
    ],
  },
];

/**
 * A setup's steps with signing in to Peek as the one before the last: the assistant asks for it when the connector is
 * added or first used.
 */
export function setupSteps(t: Translate, steps: readonly SetupLine[]): SetupLine[] {
  const signIn: SetupLine = ['assistants.setup.signIn', { allow: t('assistants.consent.allow') }];
  return [...steps.slice(0, -1), signIn, ...steps.slice(-1)];
}

/**
 * A setup line's text, with the assistant's own names in bold where the translation puts `{{…}}`, a page of its own as
 * a link that opens beside Peek, and a command as code, with `address`, Peek's address for assistants, in it.
 */
export function SetupText({ text, labels = {}, address }: { text: string; labels?: Readonly<Record<string, SetupLabel>>; address: string }) {
  return <>{text.split(/(\{\{\w+\}\})/).map((piece, index) => {
    const label = labels[piece.slice(2, -2)];
    if (!piece.startsWith('{{') || label === undefined) return <Fragment key={index}>{piece}</Fragment>;
    if (typeof label === 'string') return <strong key={index} className="add-by-email__ui" translate="no">{label}</strong>;
    if ('command' in label) {
      const command = typeof label.command === 'string' ? label.command : label.command(address);
      return <code key={index} className="assistants__command" translate="no">{command}</code>;
    }
    return <a key={index} className="add-by-email__ui" href={label.href} target="_blank" rel="noopener noreferrer" translate="no">{label.text}</a>;
  })}</>;
}
