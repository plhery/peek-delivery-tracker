/** Whether an open modal has shut `element` off: out of reach, and not read out. */
export function shutOff(element: Element | null): boolean {
  return Boolean(element?.closest('[inert]') && element.closest('[aria-hidden="true"]'));
}
