import type { MouseEvent } from 'react';

/**
 * Back on a page of its own, such as `/android`, whose link leads home: where the visitor came from on this site, as
 * it was left; anyone else follows the link to the home page.
 */
export function backOnSite(event: MouseEvent<HTMLAnchorElement>): void {
  if (history.length > 1 && document.referrer.startsWith(`${location.origin}/`)) {
    event.preventDefault();
    history.back();
  }
}
