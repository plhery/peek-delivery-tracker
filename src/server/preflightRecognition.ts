import 'server-only';
import type { Recognition } from 'universal-parcel-scraper/node';

/**
 * The first check of a parcel saved from the Add sheet asks the carriers the sheet asked moments before, about the
 * same number. Each answer a carrier gave the sheet over plain HTTP is kept five minutes, and that check takes it once
 * instead of asking again. A carrier that failed or ran out of time left no answer, so it is asked.
 */
const TTL_MS = 5 * 60_000;
const MAX_ENTRIES = 1_000;
// Next compiles the API and instrumentation worker into different module instances.
const stateKey = Symbol.for('peek.preflightRecognition');
const processScope = globalThis as typeof globalThis & { [stateKey]?: Map<string, { at: number; answer: Recognition }> };
const answers: Map<string, { at: number; answer: Recognition }> = processScope[stateKey] ??= new Map();

export function rememberPreflightRecognition(carrier: string, number: string, answer: Recognition): void {
  const key = `${carrier}:${number}`;
  answers.delete(key);
  answers.set(key, { at: Date.now(), answer: { ...answer } });
  if (answers.size > MAX_ENTRIES) answers.delete(answers.keys().next().value!);
}

/** The carrier's fresh answer to the Add sheet about the number, taken once. */
export function takePreflightRecognition(carrier: string, number: string): Recognition | undefined {
  const key = `${carrier}:${number}`;
  const entry = answers.get(key);
  if (!entry) return undefined;
  answers.delete(key);
  return Date.now() - entry.at < TTL_MS ? entry.answer : undefined;
}
