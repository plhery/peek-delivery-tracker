import type { CSSProperties } from 'react';
import passportData from '../../../shared/passport.json';
import { countryInk } from '../../lib/passport';

type Continent = keyof typeof passportData.outlines;

/**
 * A country's ink stamp: its continent's outline with the inner line, its running number, its code and the day a parcel
 * first reached it, in the country's tone and tilt. A new one drops in (`drop` is its place among those landing together).
 */
export function CountryInk({ code, number, day, drop }: { code: string; number: number; day: string; drop?: number }) {
  const ink = countryInk(code);
  const continent = ink.continent as Continent;
  const outline = passportData.outlines[continent];
  const layout = passportData.layout[continent];
  const style = { '--tilt': `${ink.tilt}deg`, ...(drop === undefined ? {} : { '--drop-delay': `${180 + drop * 120}ms` }) } as CSSProperties;
  return <span className={`passport-ink tone-${ink.tone}${drop === undefined ? '' : drop < 2 ? ' passport-ink--drop' : ' passport-ink--appear'}`} style={style} aria-hidden="true">
    <svg viewBox="0 0 124 92" fill="none" stroke="currentColor" strokeLinejoin="round">
      <path className="passport-ink__outline" d={outline} strokeWidth={1.7} />
      <path d={outline} strokeWidth={0.8 / layout.inner} opacity={0.55} transform={`translate(62 46) scale(${layout.inner}) translate(-62 -46)`} />
      <g fill="currentColor" stroke="none" textAnchor="middle">
        <text x={62} y={layout.number} fontSize={layout.small} fontWeight={600} letterSpacing={1.3}>{`Nº ${String(number).padStart(2, '0')}`}</text>
        <text x={63} y={layout.code} fontSize={layout.codeSize} fontWeight={700} letterSpacing={2}>{code}</text>
        <text x={62} y={layout.date} fontSize={layout.small} fontWeight={600} letterSpacing={layout.dateSpacing}>{day}</text>
      </g>
    </svg>
  </span>;
}
