import type { CSSProperties } from 'react';
import { STAMP_GLYPHS, type StampDefinition } from '../../lib/passport';

/**
 * A paper seal: the stamp's glyph, or its numeral, in a die-cut frame of its tone. An earned one carries a postmark,
 * unless it has no day to stand for (a friend's); a new one is pressed in, then postmarked (`press` is its place among
 * the seals pressed together).
 */
export function PassportSeal({ stamp, earned = true, press, size = 'regular', postmark = true }: {
  stamp: Pick<StampDefinition, 'tone' | 'glyph' | 'numeral'>;
  earned?: boolean;
  press?: number;
  size?: 'regular' | 'cover' | 'mini';
  postmark?: boolean;
}) {
  const pressing = earned && press !== undefined;
  const style = pressing ? { '--press-delay': `${180 + Math.min(press, 2) * 90}ms` } as CSSProperties : undefined;
  return <span className={`passport-seal passport-seal--${size} tone-${stamp.tone}${earned ? '' : ' passport-seal--locked'}${pressing ? ' passport-seal--press' : ''}`}
    style={style} aria-hidden="true">
    <span className="passport-seal__frame">{stamp.numeral
      ? <span className="passport-seal__numeral">{stamp.numeral}</span>
      : <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeLinecap="round" strokeLinejoin="round"><path d={STAMP_GLYPHS[stamp.glyph ?? 'parcel']} /></svg>}</span>
    {earned && postmark && size !== 'mini' && <svg className="passport-postmark" viewBox="0 0 32 32" fill="none" stroke="currentColor">
      <circle className="passport-postmark__outer" cx="16" cy="16" r="15" pathLength={1} />
      <circle className="passport-postmark__inner" cx="16" cy="16" r="10.5" />
    </svg>}
  </span>;
}
