import type { ReactNode } from 'react';
import { SmallPip } from './Icon';

/** Pip behind a box (the address to copy), peeking over its top edge at what it holds, and smiling once `happy`. */
export function PipPerch({ happy, children }: { happy: boolean; children: ReactNode }) {
  return <div className="pip-perch" data-happy={happy || undefined}>
    <span className="pip-perch__pip" aria-hidden="true"><SmallPip look={[-.55, .5]} blinks /></span>
    {children}
  </div>;
}

/** An empty list's place: Pip, looking up, over a line saying what will show there. */
export function PipEmpty({ children }: { children: ReactNode }) {
  return <div className="pip-empty">
    <span className="pip-empty__pip" aria-hidden="true"><SmallPip look={[.15, -.75]} blinks /></span>
    <p>{children}</p>
  </div>;
}
