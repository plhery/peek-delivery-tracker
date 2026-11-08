import { fireEvent } from '@testing-library/react';

/**
 * Picks an input method's candidate with Enter, as browsers send it: Chrome and Firefox while the composition runs,
 * Safari after it ended, with key code 229 alone. Neither Enter is the field's own.
 */
export function pickCandidate(field: HTMLElement): void {
  fireEvent.compositionStart(field);
  fireEvent.keyDown(field, { key: 'Enter', keyCode: 13, isComposing: true });
  fireEvent.compositionEnd(field);
  fireEvent.keyDown(field, { key: 'Enter', keyCode: 229 });
}

/** An Enter someone means. */
export function pressEnter(field: HTMLElement): void {
  fireEvent.keyDown(field, { key: 'Enter', keyCode: 13 });
}
