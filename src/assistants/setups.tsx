import { Fragment } from 'react';
import type { MessageKey, Translate } from '../lib/messages';

/** A line of a setup: its text, and the names of the assistant's menus and buttons its `{{…}}` stand for. */
export type SetupLine = readonly [MessageKey, Readonly<Record<string, string>>?];

/**
 * How to add Peek to each assistant, from its own help pages: Anthropic's "Get started with custom connectors using
 * remote MCP", OpenAI's "Add custom MCP server" and "Connect and test your plugin", and Google's "Connect & manage
 * custom apps for Gemini Apps". Home Assistant connects through Peek's integration (github.com/plhery/peek-ha), whose
 * README gives its steps, named as Home Assistant 2026.11 does. Their menus and buttons are named in English, as those
 * pages give them; `who` says which plans and places can. Settings shows them, and so does the page on connecting an
 * assistant.
 */
export const ASSISTANT_SETUPS: ReadonlyArray<{ name: string; who: SetupLine; steps: readonly SetupLine[] }> = [
  {
    name: 'Claude',
    who: ['assistants.setup.claude.who', { connect: 'Connect', connectors: 'Customize › Connectors' }],
    steps: [
      ['assistants.setup.claude.open', { connectors: 'Customize › Connectors', add: '+ Add', custom: 'Add custom connector' }],
      ['assistants.setup.claude.add', { continue: 'Continue', add: 'Add' }],
      ['assistants.setup.claude.use', { plus: '+', connectors: 'Connectors' }],
    ],
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

/** A setup line's text, with the assistant's own names in bold where the translation puts `{{…}}`. */
export function SetupText({ text, labels = {} }: { text: string; labels?: Readonly<Record<string, string>> }) {
  return <>{text.split(/(\{\{\w+\}\})/).map((piece, index) => {
    const label = labels[piece.slice(2, -2)];
    return piece.startsWith('{{') && label !== undefined
      ? <strong key={index} className="add-by-email__ui" translate="no">{label}</strong>
      : <Fragment key={index}>{piece}</Fragment>;
  })}</>;
}
