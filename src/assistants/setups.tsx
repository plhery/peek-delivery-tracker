import { Fragment } from 'react';
import setups from '../../shared/assistant-setups.json';
import type { MessageKey, Translate } from '../lib/messages';

/**
 * The name of an assistant's menu or button, a page of its own that the line links to, or a command to type, in which
 * `{{address}}` stands for Peek's address for assistants.
 */
export type SetupLabel = string | { readonly text: string; readonly href: string } | { readonly command: string };

/** A line of a setup: its text, and the names of the assistant's menus and buttons its `{{…}}` stand for. */
export type SetupLine = readonly [MessageKey, Readonly<Record<string, SetupLabel>>?];

/** An assistant's setup: which plans and places can, its steps, and what to do where they don't apply. */
export interface AssistantSetup { name: string; who: SetupLine; steps: readonly SetupLine[]; fallback?: SetupLine }

/**
 * How to add Peek to each assistant, from shared/assistant-setups.json, which the apps read too. Its steps come from
 * each assistant's own help pages: Peek Parcel Tracker's page in Claude's connector directory and Anthropic's "Use
 * connectors to extend Claude's capabilities", with "Get started with custom connectors using remote MCP" for the
 * custom connector Claude falls back on; OpenAI's "Add custom MCP server" and "Connect and test your plugin"; Google's
 * "Connect & manage custom apps for Gemini Apps"; and OpenClaw's "Transports and OAuth" and "Manage saved MCP servers"
 * (docs.openclaw.ai/cli/mcp), whose commands its released CLI runs only while the Gateway is stopped. Home Assistant
 * connects through Peek's integration (github.com/plhery/peek-ha), whose README gives its steps, named as Home
 * Assistant 2026.11 does. Their menus, buttons and commands are named in English, as those pages give them; `who` says
 * which plans and places can, and `fallback` what to do where the steps don't apply. Settings shows them, and so does
 * the page on connecting an assistant.
 */
export const ASSISTANT_SETUPS = setups.setups as unknown as readonly AssistantSetup[];

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
      return <code key={index} className="assistants__command" translate="no">{label.command.replaceAll('{{address}}', address)}</code>;
    }
    return <a key={index} className="add-by-email__ui" href={label.href} target="_blank" rel="noopener noreferrer" translate="no">{label.text}</a>;
  })}</>;
}
