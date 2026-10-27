import type { CSSProperties, ReactNode } from 'react';
import { PipDrawing } from '../components/map/PipDrawing';
import { PIP_FRAME, type PipMood, type PipSide } from '../components/map/pipGeometry';

/**
 * The guides' pictures: one drawing per guide, with Pip in it. They hold no
 * words, so every language shares them, and they take the page's tones, so
 * they follow its light or dark appearance. The kraft itself stays kraft.
 */
const WIDTH = 640;
const HEIGHT = 300;
const MONO = 'ui-monospace, SFMono-Regular, Menlo, monospace';

/** Pip's tones as the kraft parcel wears them. */
const KRAFT = {
  '--pip-ink': '#987450', '--pip-deep': '#20251E', '--pip-inside': '#806345', '--pip-paper': '#FFFDF6',
  '--pip-left': '#C9A47B', '--pip-right': '#B78F66', '--pip-back-left': '#C4A078', '--pip-back-right': '#D8B997',
  '--pip-front-left': '#DDBD96', '--pip-front-right': '#D1AE85', '--pip-tape': '#EBDDCA', '--pip-seam': '#AF9474',
  '--pip-blush': '#E9958F', '--pip-shadow': 'rgb(32 37 30 / 12%)', '--pip-glint': '#D6AE48', '--pip-glint-deep': '#C99B35',
} as CSSProperties;

/** Pip, standing with his feet on `(x, ground)`. He looks toward `side`. */
function Pip({ x, ground, width, mood, side = 0 }: { x: number; ground: number; width: number; mood: PipMood; side?: PipSide }) {
  const scale = width / PIP_FRAME.width;
  return <svg x={x - PIP_FRAME.groundX * scale} y={ground - PIP_FRAME.groundY * scale} width={width} height={PIP_FRAME.height * scale}
    viewBox={`0 0 ${PIP_FRAME.width} ${PIP_FRAME.height}`} overflow="visible" fill="none" style={KRAFT}>
    <PipDrawing mood={mood} side={side} inside="inside" />
  </svg>;
}

const ink = 'var(--ink)';
const soft = 'var(--ink-soft)';
const paper = 'var(--paper)';
const line = 'var(--line)';

/** A sheet of paper lying on the scene. */
function Sheet({ x, y, width, height, rx = 14, children }: { x: number; y: number; width: number; height: number; rx?: number; children?: ReactNode }) {
  return <g transform={`translate(${x} ${y})`}>
    <rect x="0" y="3" width={width} height={height} rx={rx} fill={ink} opacity=".07" />
    <rect width={width} height={height} rx={rx} fill={paper} />
    {children}
  </g>;
}

/** A delivery truck in a tone, 32 wide before `scale`, driving right unless `flip`. */
function Truck({ x, y, tone, scale = 2, flip = false }: { x: number; y: number; tone: string; scale?: number; flip?: boolean }) {
  return <g transform={`translate(${x} ${y}) scale(${flip ? -scale : scale} ${scale})`}>
    <rect x="2" y="3" width="18" height="13" rx="1.3" fill={`var(--${tone})`} />
    <path d="M20 8h5l5 5v3H20Z" fill={`var(--${tone})`} />
    <path d="M22 9.5h2.5l3 3H22Z" fill={paper} opacity=".9" />
    <path d="M5 9h8" stroke={paper} strokeWidth="2" strokeLinecap="round" opacity=".85" />
    {[8, 25].map((cx) => <g key={cx}><circle cx={cx} cy="16.5" r="2.4" fill={ink} /><circle cx={cx} cy="16.5" r=".9" fill={paper} /></g>)}
  </g>;
}

/** A tracking number on a paper strip, its parts in tones. */
function NumberStrip({ x, y, parts, tone }: { x: number; y: number; parts: readonly (readonly [string, string])[]; tone: string }) {
  const widths = parts.map(([text]) => text.length * 10.4 + 14);
  // Each part starts where the ones before it end.
  const starts = widths.map((_, index) => 62 + widths.slice(0, index).reduce((sum, width) => sum + width + 5, 0));
  return <g transform={`translate(${x} ${y}) scale(1.18)`}>
    <Sheet x={0} y={0} width={starts.at(-1)! + widths.at(-1)! + 13} height={50} rx={16}>
      <Truck x={12} y={11} tone={tone} scale={1.35} />
      {parts.map(([text, color], index) => <g key={text}>
        <rect x={starts[index]} y="12" width={widths[index]} height="26" rx="8" fill={`var(--${color}-soft)`} />
        <text x={starts[index] + widths[index] / 2} y="30.5" textAnchor="middle" fontFamily={MONO} fontSize="15" fontWeight="650" letterSpacing=".5" fill={`var(--${color})`}>{text}</text>
      </g>)}
    </Sheet>
  </g>;
}

/** A dot on a route: passed, the parcel's place now, or still ahead. */
function Stop({ x, y, state, tone }: { x: number; y: number; state: 'done' | 'now' | 'ahead'; tone: string }) {
  if (state === 'ahead') return <circle cx={x} cy={y} r="9" fill={paper} stroke={line} strokeWidth="3" />;
  return <g>
    {state === 'now' && <circle cx={x} cy={y} r="19" fill={`var(--${tone})`} opacity=".18" />}
    <circle cx={x} cy={y} r="11" fill={`var(--${tone})`} />
    {state === 'done'
      ? <path d={`m${x - 5} ${y} 3.5 3.5 6.5-7`} stroke={paper} strokeWidth="2.4" strokeLinecap="round" strokeLinejoin="round" />
      : <circle cx={x} cy={y} r="4" fill={paper} />}
  </g>;
}

function Formats() {
  return <>
    <NumberStrip x={34} y={36} tone="orange" parts={[['1Z', 'orange'], ['999AA1', 'blue'], ['01', 'green'], ['2345', 'lilac']]} />
    <NumberStrip x={58} y={120} tone="blue" parts={[['RR', 'blue'], ['00000000', 'orange'], ['5', 'green'], ['CH', 'lilac']]} />
    <NumberStrip x={34} y={204} tone="green" parts={[['LP', 'green'], ['00123456', 'ochre'], ['789', 'blue']]} />
    {/* The magnifier rests on the middle number's country. */}
    <g transform="translate(354 150)">
      <circle r="30" fill={paper} opacity=".3" />
      <circle r="30" stroke={ink} strokeWidth="5.5" />
      <path d="m23 23 22 22" stroke={ink} strokeWidth="9" strokeLinecap="round" />
    </g>
    <Pip x={520} ground={264} width={222} mood="look" side={-1} />
  </>;
}

function Statuses() {
  const stops = [[70, 196], [178, 150], [286, 196], [394, 150], [560, 150]] as const;
  return <>
    <path d="M70 196C110 196 138 150 178 150S246 196 286 196 354 150 394 150" stroke="var(--green)" strokeWidth="5" strokeLinecap="round" />
    <path d="M394 150H560" stroke={line} strokeWidth="5" strokeLinecap="round" strokeDasharray="2 12" />
    <Stop x={stops[0][0]} y={stops[0][1]} state="done" tone="green" />
    <Stop x={stops[1][0]} y={stops[1][1]} state="done" tone="green" />
    <Stop x={stops[2][0]} y={stops[2][1]} state="done" tone="green" />
    <Stop x={stops[3][0]} y={stops[3][1]} state="now" tone="green" />
    <Stop x={stops[4][0]} y={stops[4][1]} state="ahead" tone="green" />
    {/* What each stop is: a label, a depot, a flight, a van, a home. */}
    <g stroke={soft} strokeWidth="2.2" strokeLinecap="round" strokeLinejoin="round">
      <path d="M56 222h28v18H56zM62 227v8m5-8v8m4-8v8m5-8v8" />
      <path d="m163 118 15-10 15 10v14h-30zM172 132v-9h12v9" />
      <path d="M286 224c1.5 0 2.4 1.8 2.4 4.5v5.4l11.1 6.6v3l-11.1-3.3v6.3l3.6 2.7v1.8l-6-1.5-6 1.5v-1.8l3.6-2.7v-6.3l-11.1 3.3v-3l11.1-6.6v-5.4c0-2.7.9-4.5 2.4-4.5Z" />
      <path d="m545 124 15-13 15 13v16h-10v-10h-10v10h-10z" />
    </g>
    <Truck x={362} y={86} tone="green" scale={2} />
    <Pip x={482} ground={268} width={176} mood="eager" side={1} />
  </>;
}

function Quiet() {
  return <>
    <path d="M60 184H190" stroke="var(--ochre)" strokeWidth="5" strokeLinecap="round" />
    <path d="M190 184H580" stroke={line} strokeWidth="5" strokeLinecap="round" strokeDasharray="2 13" />
    <Stop x={60} y={184} state="done" tone="ochre" />
    <Stop x={190} y={184} state="now" tone="ochre" />
    <Stop x={580} y={184} state="ahead" tone="ochre" />
    {/* A calendar whose days pass with nothing on them. */}
    <Sheet x={64} y={58} width={126} height={92} rx={14}>
      <rect width="126" height="26" rx="14" fill="var(--ochre)" />
      <rect y="14" width="126" height="12" fill="var(--ochre)" />
      {[0, 1, 2].flatMap((row) => [0, 1, 2, 3, 4].map((column) => {
        const passed = row * 5 + column < 8;
        return <rect key={`${row}-${column}`} x={14 + column * 21} y={36 + row * 17} width="14" height="10" rx="3"
          fill={passed ? 'var(--ochre-soft)' : 'var(--control)'} />;
      }))}
      <path d="m16 38 10 6m-10 0 10-6M37 38l10 6m-10 0 10-6M58 38l10 6m-10 0 10-6" stroke="var(--ochre)" strokeWidth="1.8" strokeLinecap="round" />
    </Sheet>
    {/* Three dots of waiting, over a parcel that has dozed off between two scans. */}
    <Sheet x={300} y={52} width={96} height={46} rx={23}>
      {[28, 48, 68].map((cx, index) => <circle key={cx} cx={cx} cy="23" r="6" fill="var(--ochre)" opacity={1 - index * .28} />)}
    </Sheet>
    <path d="M338 98l10 14 8-14z" fill={paper} />
    <Pip x={372} ground={262} width={214} mood="wait" />
    <g fill="none" stroke={soft} strokeWidth="3" strokeLinecap="round" strokeLinejoin="round">
      <path d="M470 96h18l-18 20h18" />
      <path d="M502 66h13l-13 15h13" opacity=".7" />
    </g>
  </>;
}

function Missing() {
  return <>
    {/* A front door, its mat, and the place where the parcel should be. */}
    <rect x="70" y="36" width="150" height="212" rx="10" fill="var(--orange)" />
    <rect x="86" y="54" width="118" height="78" rx="6" fill={paper} opacity=".22" />
    <rect x="86" y="146" width="118" height="84" rx="6" fill={paper} opacity=".14" />
    <circle cx="196" cy="150" r="7" fill="var(--yellow)" />
    <rect x="124" y="92" width="42" height="9" rx="4.5" fill={ink} opacity=".5" />
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <path d="M52 262h186l-14-14H66z" fill="var(--ink-soft)" opacity=".35" />
    <g transform="translate(262 150)">
      <path d="m0 40 62-31 62 31v58l-62 31-62-31z" fill={paper} opacity=".45" />
      <path d="m0 40 62-31 62 31v58l-62 31-62-31zM0 40l62 31 62-31M62 71v58" stroke={soft} strokeWidth="3" strokeLinejoin="round" strokeDasharray="7 8" />
      <path d="M49 50c0-17 26-17 26 0 0 10-13 10-13 22" stroke="var(--orange)" strokeWidth="6" strokeLinecap="round" strokeLinejoin="round" />
      <circle cx="62" cy="88" r="4.5" fill="var(--orange)" />
    </g>
    {/* The carrier's message says it has arrived. */}
    <Sheet x={268} y={40} width={150} height={56} rx={16}>
      <circle cx="30" cy="28" r="15" fill="var(--green-soft)" />
      <path d="m23 28 5 5 9-10" stroke="var(--green)" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round" />
      <rect x="56" y="18" width="74" height="8" rx="4" fill="var(--control)" />
      <rect x="56" y="32" width="48" height="8" rx="4" fill="var(--control)" />
    </Sheet>
    <Pip x={516} ground={262} width={196} mood="worry" side={-1} />
  </>;
}

function Customs() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* The booth, and the barrier across the way. */}
    <rect x="60" y="112" width="96" height="136" rx="10" fill="var(--lilac)" />
    <rect x="74" y="128" width="68" height="52" rx="6" fill={paper} opacity=".85" />
    <path d="M50 112h116l-12-24H62z" fill="var(--lilac)" opacity=".7" />
    <rect x="182" y="150" width="16" height="98" rx="5" fill={ink} opacity=".75" />
    <g transform="rotate(-6 190 160)">
      <rect x="186" y="150" width="252" height="20" rx="10" fill={paper} />
      {[0, 1, 2, 3, 4].map((stripe) => <path key={stripe} d={`M${214 + stripe * 46} 150h22l-12 20h-22z`} fill="var(--orange)" />)}
    </g>
    <circle cx="190" cy="160" r="13" fill={ink} />
    {/* The form, with its stamp. */}
    <Sheet x={246} y={34} width={132} height={84} rx={12}>
      <rect x="16" y="16" width="62" height="8" rx="4" fill="var(--control)" />
      <rect x="16" y="32" width="44" height="8" rx="4" fill="var(--control)" />
      <rect x="16" y="48" width="54" height="8" rx="4" fill="var(--control)" />
      <g transform="translate(94 50) rotate(-14)">
        <circle r="25" stroke="var(--lilac)" strokeWidth="3" />
        <circle r="19.5" stroke="var(--lilac)" strokeWidth="1.2" strokeDasharray="1.5 3" />
        <path d="m-10 1 7 7 13-15" stroke="var(--lilac)" strokeWidth="4.5" strokeLinecap="round" strokeLinejoin="round" />
      </g>
    </Sheet>
    <Pip x={512} ground={262} width={204} mood="wait" side={-1} />
  </>;
}

function FromChina() {
  return <>
    {/* Half the world between a seller's pin and a home. */}
    <path d="M40 262C150 150 490 150 600 262" fill="var(--blue)" opacity=".12" />
    <path d="M40 262C150 150 490 150 600 262" stroke="var(--blue)" strokeWidth="3" opacity=".35" />
    <path d="M112 196C200 40 440 40 528 196" stroke="var(--blue)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    <g transform="translate(320 78) rotate(90)" fill="var(--blue)">
      <path d="M0-26c3.3 0 5.3 4 5.3 10v12l24.7 14.7v6.6L5.3 9.9v14l8 6v4L0 30.6l-13.3 3.3v-4l8-6v-14L-30 17.3v-6.6L-5.3-4v-12c0-6 2-10 5.3-10Z" />
    </g>
    <g transform="translate(112 196)">
      <path d="M0 0c-14-18-22-28-22-40a22 22 0 0 1 44 0c0 12-8 22-22 40Z" fill="var(--orange)" />
      <circle cy="-40" r="8.5" fill={paper} />
    </g>
    <g transform="translate(528 196)">
      <path d="M0 0c-14-18-22-28-22-40a22 22 0 0 1 44 0c0 12-8 22-22 40Z" fill="var(--green)" />
      <path d="m-10-39 10-9 10 9v11h-20z" fill={paper} />
    </g>
    {/* Two carriers: one hands the parcel to the other. */}
    <Truck x={176} y={206} tone="orange" scale={2.2} />
    <Truck x={464} y={206} tone="green" scale={2.2} flip />
    <Pip x={320} ground={270} width={170} mood="eager" side={1} />
  </>;
}

function FindNumber() {
  return <>
    {/* A shipping email, the number marked in it. */}
    <Sheet x={50} y={44} width={250} height={196} rx={18}>
      <circle cx="34" cy="36" r="14" fill="var(--blue-soft)" />
      <path d="m26 32 8 6 8-6m-16-1h16v11H26z" stroke="var(--blue)" strokeWidth="1.8" strokeLinejoin="round" strokeLinecap="round" />
      <rect x="58" y="24" width="112" height="9" rx="4.5" fill="var(--control)" />
      <rect x="58" y="40" width="74" height="8" rx="4" fill="var(--control)" />
      <rect x="22" y="72" width="204" height="8" rx="4" fill="var(--control)" />
      <rect x="22" y="88" width="170" height="8" rx="4" fill="var(--control)" />
      <rect x="16" y="110" width="218" height="36" rx="11" fill="var(--yellow)" />
      <text x="125" y="134" textAnchor="middle" fontFamily={MONO} fontSize="16" fontWeight="650" letterSpacing="1" fill="var(--on-yellow)">RR000000005CH</text>
      <rect x="22" y="160" width="150" height="8" rx="4" fill="var(--control)" />
      <rect x="22" y="176" width="96" height="8" rx="4" fill="var(--control)" />
    </Sheet>
    {/* The same number, under the barcode of the label. */}
    <g transform="rotate(7 372 96)">
      <Sheet x={312} y={50} width={120} height={84} rx={10}>
        <path d="M16 16v34m4-34v34m5-34v34m3-34v34m6-34v34m3-34v34m5-34v34m4-34v34m3-34v34m6-34v34m3-34v34m5-34v34m4-34v34m3-34v34m6-34v34m4-34v34m3-34v34m5-34v34m4-34v34m3-34v34" stroke={ink} strokeWidth="2" />
        <rect x="16" y="60" width="88" height="10" rx="5" fill="var(--yellow)" />
      </Sheet>
    </g>
    <Pip x={508} ground={262} width={214} mood="look" side={-1} />
  </>;
}

function Universal() {
  const trucks = [['orange', 44, 34], ['blue', 30, 84], ['green', 44, 134], ['lilac', 30, 184], ['rose', 44, 234]] as const;
  return <>
    {trucks.map(([tone, x, y]) => <g key={tone}>
      <path d={`M${x + 70} ${y + 18}C${x + 150} ${y + 18} 190 150 268 150`} stroke={`var(--${tone})`} strokeWidth="3" strokeLinecap="round" strokeDasharray="2 9" opacity=".7" />
      <Truck x={x} y={y - 4} tone={tone} scale={1.9} />
    </g>)}
    {/* One list for all of them. */}
    <Sheet x={268} y={38} width={150} height={224} rx={24}>
      <rect x="52" y="12" width="46" height="7" rx="3.5" fill="var(--control)" />
      {['orange', 'blue', 'green', 'lilac'].map((tone, index) => <g key={tone} transform={`translate(14 ${36 + index * 46})`}>
        <rect width="122" height="38" rx="12" fill={`var(--${tone}-soft)`} />
        <circle cx="19" cy="19" r="8" fill={`var(--${tone})`} />
        <rect x="36" y="11" width="62" height="7" rx="3.5" fill={`var(--${tone})`} opacity=".55" />
        <rect x="36" y="23" width="40" height="6" rx="3" fill={`var(--${tone})`} opacity=".3" />
      </g>)}
    </Sheet>
    <Pip x={520} ground={266} width={200} mood="joy" />
  </>;
}

/** Snowflakes: where each falls, and how big; the small ones are further off. */
const SNOW = [[34, 34, 3.5], [92, 196, 2.5], [128, 22, 2.5], [226, 40, 3.5], [244, 120, 2.5], [300, 70, 3], [348, 26, 2.5],
  [392, 104, 3.5], [430, 44, 2.5], [470, 140, 2.5], [486, 18, 3], [560, 60, 3.5], [606, 112, 2.5], [612, 26, 2.5], [232, 196, 2]] as const;

function Christmas() {
  return <>
    {/* Snow is white in any light. */}
    {SNOW.map(([cx, cy, r]) => <circle key={`${cx}-${cy}`} cx={cx} cy={cy} r={r} fill="#FFFFFF" opacity={r > 3 ? .95 : .75} />)}
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* December, with the last day to send circled and the days after it fading. */}
    <Sheet x={48} y={40} width={150} height={124} rx={14}>
      <rect width="150" height="28" rx="14" fill="var(--rose)" />
      <rect y="14" width="150" height="14" fill="var(--rose)" />
      {[0, 1, 2, 3].flatMap((row) => [0, 1, 2, 3, 4, 5].map((column) => {
        const day = row * 6 + column;
        return <rect key={day} x={14 + column * 21} y={40 + row * 19} width="14" height="11" rx="3"
          fill={day === 15 ? 'var(--rose)' : 'var(--control)'} opacity={day > 15 ? .45 : 1} />;
      }))}
      <circle cx="84" cy="83.5" r="12.5" stroke="var(--rose)" strokeWidth="2.6" />
    </Sheet>
    {/* The van in a hurry, a parcel tied with a ribbon on its roof. */}
    <g stroke={soft} strokeWidth="3" strokeLinecap="round" opacity=".6">
      <path d="M196 206h34M210 222h22M202 238h26" />
    </g>
    <Truck x={244} y={184} tone="green" scale={3.4} />
    <g transform="translate(262 156)">
      <rect width="44" height="36" rx="4" fill="#DDBD96" />
      <path d="M0 9h44" stroke="#C9A47B" strokeWidth="2" />
      <rect x="19" width="7" height="36" fill="var(--rose)" />
      <rect y="15" width="44" height="7" fill="var(--rose)" />
      <path d="M22.5 0c-4-9-15-10-14-3 1 5 9 4 14 3Zm0 0c4-9 15-10 14-3-1 5-9 4-14 3Z" fill="var(--rose)" />
    </g>
    <Pip x={520} ground={262} width={196} mood="eager" side={-1} />
  </>;
}

/** The kraft of a parcel that is not Pip: its faces, its tape and its inside. */
const CARD = { top: '#DDBD96', left: '#C9A47B', right: '#B78F66', tape: '#EBDDCA', inside: '#806345', ink: '#987450' };

/** A parcel seen from the front, taped down the middle of its lid. */
function Box({ x, y, width, height, children }: { x: number; y: number; width: number; height: number; children?: ReactNode }) {
  return <g transform={`translate(${x} ${y})`}>
    <rect width={width} height={height} rx="4" fill={CARD.top} />
    <path d={`M0 ${height * .24}H${width}`} stroke={CARD.left} strokeWidth="2" />
    <rect x={width / 2 - 5} width="10" height={height * .24} fill={CARD.tape} />
    {children}
  </g>;
}

/** A phone, its screen in the page's paper. */
function Phone({ x, y, width, height, children }: { x: number; y: number; width: number; height: number; children?: ReactNode }) {
  return <g transform={`translate(${x} ${y})`}>
    <rect y="4" width={width} height={height} rx="24" fill={ink} opacity=".08" />
    <rect width={width} height={height} rx="24" fill={ink} />
    <rect x="7" y="7" width={width - 14} height={height - 14} rx="18" fill={paper} />
    <rect x={width / 2 - 17} y="15" width="34" height="8" rx="4" fill={ink} />
    {children}
  </g>;
}

/** A month on a sheet: a header in a tone, and its days, each drawn by `day`. */
function Month({ x, y, tone, columns, rows, day, children }: {
  x: number; y: number; tone: string; columns: number; rows: number; day: (index: number) => { fill: string; opacity?: number }; children?: ReactNode;
}) {
  const width = 28 + columns * 21 - 7;
  return <Sheet x={x} y={y} width={width} height={52 + rows * 21} rx={14}>
    <rect width={width} height="28" rx="14" fill={`var(--${tone})`} />
    <rect y="14" width={width} height="14" fill={`var(--${tone})`} />
    {Array.from({ length: rows * columns }, (_, index) => {
      const { fill, opacity = 1 } = day(index);
      return <rect key={index} x={14 + index % columns * 21} y={40 + Math.floor(index / columns) * 21} width="14" height="11" rx="3" fill={fill} opacity={opacity} />;
    })}
    {children}
  </Sheet>;
}

function Uncollected() {
  const doors = [[64, 44, 80, 56], [160, 44, 80, 56], [256, 44, 80, 56], [64, 108, 80, 76], [256, 108, 80, 76], [64, 192, 80, 44], [160, 192, 80, 44], [256, 192, 80, 44]] as const;
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* A parcel locker, one of its doors open. */}
    <rect x="48" y="28" width="304" height="220" rx="12" fill="var(--ochre)" />
    {doors.map(([x, y, width, height]) => <g key={`${x}-${y}`}>
      <rect x={x} y={y} width={width} height={height} rx="5" fill={paper} opacity=".2" />
      <circle cx={x + width - 11} cy={y + height / 2} r="3.5" fill={paper} opacity=".6" />
    </g>)}
    <rect x="160" y="108" width="80" height="76" rx="4" fill="#20251E" opacity=".55" />
    <Pip x={200} ground={182} width={112} mood="wait" />
    <path d="M240 108 274 98v106l-34-20z" fill={paper} />
    <circle cx="265" cy="152" r="3.5" fill="var(--ochre)" />
    {/* The days it waits, the last two circled. */}
    <Month x={410} y={58} tone="ochre" columns={7} rows={4} day={(index) => index < 23 ? { fill: 'var(--ochre-soft)' } : index < 25 ? { fill: 'var(--ochre)' } : { fill: 'var(--control)', opacity: .45 }}>
      <rect x="50" y="97" width="47" height="23" rx="11.5" stroke="var(--ochre)" strokeWidth="2.6" />
    </Month>
  </>;
}

function Damaged() {
  return <>
    <path d="M40 252H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* A dented box, a corner torn, its tape coming off. */}
    <g transform="translate(64 58) scale(1.5)">
      <path d="M0 40 62 71v58L0 98z" fill={CARD.left} />
      <path d="m62 71 62-31v58l-62 31z" fill={CARD.right} />
      <path d="M0 40 62 9l62 31-62 31z" fill={CARD.top} />
      <path d="M82 76q12-5 19 7-6 11-17 7 4-7-2-14Z" fill={CARD.ink} opacity=".45" />
      <path d="m82 76-7-5m26 12 8-3m-25 7-5 6" stroke={CARD.ink} strokeWidth="1.4" strokeLinecap="round" />
      <path d="m0 40 17-8-4 8 8 5-9 5 3 7-15-3z" fill={CARD.inside} />
      <path d="m17 32 9 2-5 11" fill={CARD.top} />
      <path d="M52 35 93 55.5" stroke={CARD.ink} strokeWidth="1.2" strokeDasharray="3 3" opacity=".6" />
      <path d="M31 24.5 52 35" stroke={CARD.tape} strokeWidth="9" />
      <path d="M52 35c8-2 12-8 12-17" stroke={CARD.tape} strokeWidth="9" />
    </g>
    {/* A phone takes its picture, with the flash. */}
    <g transform="rotate(-8 356 144)">
      <Phone x={302} y={52} width={108} height={184}>
        <path d="M22 40V28h12M86 28h12v12M98 124v12H86M34 136H22v-12" stroke="var(--orange)" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round" />
        <g transform="translate(29 54) scale(.4)">
          <path d="M0 40 62 71v58L0 98z" fill={CARD.left} />
          <path d="m62 71 62-31v58l-62 31z" fill={CARD.right} />
          <path d="M0 40 62 9l62 31-62 31z" fill={CARD.top} />
          <path d="m0 40 17-8-4 8 8 5-9 5 3 7-15-3z" fill={CARD.inside} />
        </g>
        <circle cx="54" cy="160" r="9" fill="var(--orange)" />
      </Phone>
    </g>
    <g transform="translate(294 66)">
      <circle r="11" fill="var(--yellow)" />
      <path d="M-18 0h-12M-13-13l-8-8M0-18v-12M13-13l8-8M-13 13l-8 8" stroke="var(--yellow)" strokeWidth="4" strokeLinecap="round" />
    </g>
    <Pip x={530} ground={262} width={190} mood="worry" side={-1} />
    {/* A plaster on Pip. */}
    <g transform="translate(566 206) rotate(-28)">
      <rect x="-26" y="-10" width="52" height="20" rx="10" fill="#F2CDA8" />
      <rect x="-9" y="-7" width="18" height="14" rx="3" fill="#E0A982" />
      <path d="M-18-3v0m0 6v0m36-6v0m0 6v0" stroke="#E0A982" strokeWidth="2.4" strokeLinecap="round" />
    </g>
  </>;
}

function Lost() {
  const upper = [[64, 80, 70, 60], [142, 96, 54, 44], [278, 72, 92, 68]] as const;
  const lower = [[62, 170, 84, 68], [154, 188, 58, 50], [220, 178, 72, 60], [300, 192, 70, 46]] as const;
  return <>
    {/* A sorting centre's shelf, a gap among its boxes. */}
    <rect x="48" y="34" width="10" height="214" rx="3" fill={soft} />
    <rect x="376" y="34" width="10" height="214" rx="3" fill={soft} />
    {[...upper, ...lower].map(([x, y, width, height]) => <Box key={`${x}-${y}`} x={x} y={y} width={width} height={height} />)}
    <rect x="42" y="140" width="350" height="10" rx="3" fill={soft} />
    <rect x="42" y="238" width="350" height="10" rx="3" fill={soft} />
    <g transform="translate(204 84)">
      <rect width="66" height="56" rx="4" fill={paper} opacity=".4" />
      <rect width="66" height="56" rx="4" stroke={soft} strokeWidth="3" strokeDasharray="7 8" />
    </g>
    {/* A torch lights the gap, and a magnifier searches it. */}
    <path d="M436 150 262 76v74l174 22z" fill="var(--yellow)" opacity=".35" />
    <g transform="translate(237 112)">
      <circle r="30" fill={paper} opacity=".3" />
      <circle r="30" stroke={ink} strokeWidth="5.5" />
      <path d="m23 23 20 20" stroke={ink} strokeWidth="9" strokeLinecap="round" />
    </g>
    <g transform="translate(436 161) rotate(8)">
      <rect x="6" y="-8" width="38" height="16" rx="5" fill={ink} />
      <path d="M-4-14h12v28H-4z" fill={soft} />
      <rect x="-6" y="-12" width="4" height="24" rx="2" fill="var(--yellow)" />
    </g>
    <Pip x={530} ground={262} width={190} mood="look" side={-1} />
  </>;
}

function Scam() {
  return <>
    {/* A text on a phone, a warning in it, and a hook on its link. */}
    <Phone x={70} y={24} width={176} height={252}>
      <circle cx="34" cy="48" r="12" fill="var(--control)" />
      <rect x="54" y="43" width="70" height="9" rx="4.5" fill="var(--control)" />
      <rect x="18" y="74" width="140" height="128" rx="18" fill="var(--lilac-soft)" />
      <g transform="translate(46 104)">
        <path d="M0-20 20 14h-40z" fill="var(--yellow)" stroke="var(--yellow)" strokeWidth="6" strokeLinejoin="round" />
        <path d="M0-8v10" stroke="var(--on-yellow)" strokeWidth="4" strokeLinecap="round" />
        <circle cy="9" r="2.4" fill="var(--on-yellow)" />
      </g>
      <rect x="78" y="94" width="64" height="8" rx="4" fill="var(--lilac)" opacity=".35" />
      <rect x="78" y="110" width="46" height="8" rx="4" fill="var(--lilac)" opacity=".35" />
      <rect x="32" y="138" width="108" height="8" rx="4" fill="var(--lilac)" opacity=".35" />
      <rect x="32" y="162" width="96" height="10" rx="5" fill="var(--blue)" opacity=".75" />
      <path d="M32 179h96" stroke="var(--blue)" strokeWidth="2.4" strokeLinecap="round" />
    </Phone>
    <path d="M164 0v186" stroke={ink} strokeWidth="2" opacity=".7" />
    <path d="M164 186v14a11 11 0 0 1-22 0v-10m-5 6 5-6 5 6" stroke={ink} strokeWidth="4" strokeLinecap="round" strokeLinejoin="round" />
    {/* A coin, crossed out. */}
    <g transform="translate(340 168)">
      <circle r="32" fill="var(--yellow)" />
      <circle r="23" stroke="var(--on-yellow)" strokeWidth="2.4" opacity=".3" />
      <circle r="44" stroke="var(--rose)" strokeWidth="7" />
      <path d="m-31-31 62 62" stroke="var(--rose)" strokeWidth="7" strokeLinecap="round" />
    </g>
    {/* Pip says stop, a raised hand in his bubble. */}
    <Sheet x={420} y={30} width={84} height={84} rx={42}>
      <g fill="var(--rose)">
        <rect x="28" y="38" width="30" height="28" rx="10" />
        {[[28, 26], [35.5, 20], [43, 22], [50.5, 28]].map(([x, top]) => <rect key={x} x={x} y={top} width="7" height={50 - top} rx="3.5" />)}
        <rect x="22" y="40" width="7" height="24" rx="3.5" transform="rotate(-30 25.5 62)" />
      </g>
    </Sheet>
    <path d="M478 112l14 22 4-26z" fill={paper} />
    <Pip x={530} ground={266} width={190} mood="worry" side={-1} />
  </>;
}

function ChangeAddress() {
  const roads = ['M10 290C120 284 200 252 282 214', 'M282 214C334 190 362 142 446 120', 'M282 214C344 232 420 238 524 236'];
  return <>
    {/* A road that forks: up to the old address, on to a parcel locker. */}
    {roads.map((road) => <path key={road} d={road} stroke={paper} strokeWidth="34" strokeLinecap="round" opacity=".8" />)}
    {roads.map((road) => <path key={road} d={road} stroke={line} strokeWidth="3" strokeLinecap="round" strokeDasharray="10 12" />)}
    <g opacity=".6">
      <path d="M420 72 468 34l48 38z" fill={soft} />
      <rect x="430" y="70" width="76" height="52" fill={paper} />
      <rect x="458" y="90" width="20" height="32" rx="3" fill={soft} />
    </g>
    <rect x="520" y="136" width="84" height="104" rx="8" fill="var(--green)" />
    {[0, 1].flatMap((column) => [0, 1, 2].map((row) => <rect key={`${column}-${row}`} x={528 + column * 36} y={144 + row * 31} width="32" height="26" rx="4"
      fill={column === 1 && row === 1 ? 'var(--yellow)' : paper} opacity={column === 1 && row === 1 ? 1 : .22} />))}
    {/* The signpost at the fork, and the van taking the new turn. */}
    <rect x="276" y="114" width="8" height="102" rx="3" fill={soft} />
    <path d="M284 136 330 112l16 2-6 15-46 24z" fill={soft} opacity=".6" />
    <path d="M284 158h50l13 12-13 12h-50z" fill="var(--green)" />
    <Truck x={368} y={190} tone="green" scale={2.2} />
    <Pip x={116} ground={268} width={164} mood="look" side={1} />
  </>;
}

function Returned() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* The shop it came from. */}
    <rect x="64" y="112" width="136" height="136" fill={paper} />
    <rect x="80" y="134" width="60" height="52" rx="4" fill="var(--orange-soft)" />
    <rect x="154" y="150" width="34" height="98" rx="3" fill="var(--orange)" opacity=".8" />
    <rect x="56" y="84" width="152" height="28" rx="6" fill="var(--orange)" />
    {[0, 1, 2, 3].map((stripe) => <rect key={stripe} x={65 + stripe * 38} y="84" width="19" height="28" fill={paper} opacity=".35" />)}
    {[0, 1, 2, 3, 4, 5, 6, 7].map((scallop) => <circle key={scallop} cx={65.5 + scallop * 19} cy="112" r="9.5" fill="var(--orange)" />)}
    {/* The way back, and the van on it with the stamped parcel. */}
    <path d="M500 118C440 30 300 22 230 74" stroke="var(--orange)" strokeWidth="5" strokeLinecap="round" strokeDasharray="2 12" />
    <path d="M250 73 230 74l6-19" stroke="var(--orange)" strokeWidth="5" strokeLinecap="round" strokeLinejoin="round" />
    <Truck x={440} y={184} tone="orange" scale={3.4} flip />
    <Box x={376} y={150} width={54} height={44}>
      <g transform="translate(27 28)">
        <circle r="12" stroke="var(--orange)" strokeWidth="2.4" />
        <path d="M-5-3h5a4 4 0 0 1 0 8h-4m-1-12-4 4 4 4" stroke="var(--orange)" strokeWidth="2.2" strokeLinecap="round" strokeLinejoin="round" />
      </g>
    </Box>
    <g stroke={soft} strokeWidth="3" strokeLinecap="round" opacity=".6">
      <path d="M448 204h30M452 220h22M446 236h26" />
    </g>
    <Pip x={548} ground={262} width={176} mood="look" side={-1} />
  </>;
}

function Missed() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <path d="M52 262h186l-14-14H66z" fill="var(--ink-soft)" opacity=".35" />
    {/* The card under the door. */}
    <g transform="rotate(-7 146 250)">
      <rect x="110" y="226" width="78" height="42" rx="5" fill={paper} />
      <rect x="110" y="252" width="78" height="8" fill="var(--yellow)" />
    </g>
    <rect x="70" y="36" width="150" height="212" rx="10" fill="var(--ochre)" />
    <rect x="86" y="54" width="118" height="78" rx="6" fill={paper} opacity=".22" />
    <rect x="86" y="146" width="118" height="84" rx="6" fill={paper} opacity=".14" />
    <circle cx="196" cy="150" r="7" fill={paper} opacity=".85" />
    <rect x="124" y="92" width="42" height="9" rx="4.5" fill={ink} opacity=".5" />
    {/* The van, already far off. */}
    <path d="M456 150H608" stroke={line} strokeWidth="3" strokeLinecap="round" />
    <g stroke={soft} strokeWidth="2.4" strokeLinecap="round" opacity=".6">
      <path d="M462 128h22M470 140h16" />
    </g>
    <Truck x={496} y={116} tone="ochre" scale={1.8} />
    {/* Pip, a moment too late and out of breath. */}
    <g fill={soft} opacity=".45">
      <circle cx="300" cy="190" r="9" /><circle cx="282" cy="180" r="6" /><circle cx="270" cy="168" r="4" />
    </g>
    <g fill="var(--blue)" opacity=".7">
      <path d="M444 140c5 7 7 10 7 13a7 7 0 0 1-14 0c0-3 2-6 7-13Z" />
      <path d="M460 164c3.5 5 5 7 5 9a5 5 0 0 1-10 0c0-2 1.5-4 5-9Z" />
    </g>
    <g stroke={soft} strokeWidth="3" strokeLinecap="round" opacity=".6">
      <path d="M452 206h30M458 222h22" />
    </g>
    <Pip x={384} ground={262} width={180} mood="worry" side={-1} />
  </>;
}

function DeliveryTimes() {
  const stars = [[214, 24], [420, 112], [548, 22], [612, 44], [236, 150], [26, 30]] as const;
  return <>
    {stars.map(([cx, cy]) => <circle key={`${cx}-${cy}`} cx={cx} cy={cy} r="3" fill="var(--yellow)" />)}
    <circle cx="428" cy="50" r="20" fill="var(--yellow)" />
    <circle cx="439" cy="43" r="18" fill="var(--blue-soft)" />
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* A clock in the evening. */}
    <circle cx="124" cy="140" r="86" fill={paper} />
    <circle cx="124" cy="140" r="86" stroke="var(--blue)" strokeWidth="8" />
    {Array.from({ length: 12 }, (_, hour) => {
      const angle = hour * Math.PI / 6;
      const inner = hour % 3 ? 68 : 60;
      return <path key={hour} d={`M${124 + Math.sin(angle) * inner} ${140 - Math.cos(angle) * inner}L${124 + Math.sin(angle) * 74} ${140 - Math.cos(angle) * 74}`}
        stroke={soft} strokeWidth="3.5" strokeLinecap="round" />;
    })}
    <path d="M124 140 86 162M124 140V80" stroke={ink} strokeWidth="6" strokeLinecap="round" />
    <circle cx="124" cy="140" r="7" fill={ink} />
    {/* A week whose weekend is marked. */}
    <Month x={234} y={34} tone="blue" columns={7} rows={2} day={(index) => ({ fill: index % 7 > 4 ? 'var(--blue)' : 'var(--control)', opacity: index % 7 > 4 ? .8 : 1 })} />
    {/* The van, its headlights on. */}
    <path d="M330 218 430 198v50l-100-20z" fill="var(--yellow)" opacity=".4" />
    <Truck x={234} y={186} tone="blue" scale={3} />
    <circle cx="330" cy="224" r="3.5" fill="var(--yellow)" />
    {/* Pip at a window, watching. */}
    <rect x="460" y="70" width="190" height="240" fill={paper} />
    <rect x="452" y="60" width="200" height="16" rx="4" fill="var(--blue)" />
    <rect x="484" y="98" width="124" height="108" rx="6" fill="var(--yellow-soft)" />
    <Pip x={546} ground={216} width={150} mood="look" side={-1} />
    <rect x="460" y="206" width="190" height="104" fill={paper} />
    <rect x="484" y="98" width="124" height="108" rx="6" stroke={soft} strokeWidth="5" />
    <rect x="474" y="202" width="144" height="10" rx="4" fill={soft} />
  </>;
}

function Marketplace() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* An order on a phone: the parcel, and how far it has come. */}
    <Phone x={48} y={32} width={132} height={216}>
      <Box x={32} y={40} width={68} height={50} />
      <path d="M34 116V188" stroke="var(--orange)" strokeWidth="4" strokeLinecap="round" />
      <Stop x={34} y={116} state="done" tone="orange" />
      <Stop x={34} y={152} state="now" tone="orange" />
      <Stop x={34} y={188} state="ahead" tone="orange" />
      {[116, 152, 188].map((y) => <rect key={y} x="60" y={y - 5} width={y === 152 ? 54 : 40} height="9" rx="4.5" fill="var(--control)" />)}
    </Phone>
    {/* One van hands the parcel to the next. */}
    <path d="M250 196C276 104 398 104 422 196" stroke="var(--orange)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    <Truck x={206} y={199} tone="orange" scale={2.6} />
    <Truck x={466} y={199} tone="blue" scale={2.6} flip />
    <Box x={308} y={98} width={58} height={46} />
    <Pip x={562} ground={262} width={150} mood="look" side={-1} />
  </>;
}

function Resale() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* The seller's phone: a parcel, its label ready. */}
    <Phone x={34} y={50} width={112} height={190}>
      <Box x={23} y={44} width={66} height={52}>
        <rect x="12" y="24" width="42" height="16" rx="3" fill="var(--yellow)" />
      </Box>
      <rect x="20" y="114" width="72" height="9" rx="4.5" fill="var(--control)" />
      <rect x="20" y="132" width="50" height="9" rx="4.5" fill="var(--control)" />
      <rect x="20" y="152" width="72" height="20" rx="10" fill="var(--green)" />
    </Phone>
    {/* The buyer's phone: the parcel on its way. */}
    <Phone x={494} y={50} width={112} height={190}>
      <path d="M30 60V140" stroke="var(--green)" strokeWidth="4" strokeLinecap="round" />
      <Stop x={30} y={60} state="done" tone="green" />
      <Stop x={30} y={100} state="done" tone="green" />
      <Stop x={30} y={140} state="now" tone="green" />
      {[60, 100, 140].map((y) => <rect key={y} x="52" y={y - 4} width={y === 140 ? 42 : 34} height="8" rx="4" fill="var(--control)" />)}
    </Phone>
    {/* Between them, the van and the parcel it carries. */}
    <path d="M156 112C250 40 390 40 484 112" stroke="var(--green)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    <Box x={188} y={160} width={50} height={40} />
    <Truck x={180} y={191} tone="green" scale={3} />
    <Pip x={398} ground={262} width={150} mood="joy" />
  </>;
}

function Auction() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* A parcel, its label printed, about to set off. */}
    <Box x={56} y={128} width={150} height={120}>
      <g transform="rotate(-4 75 70)">
        <Sheet x={26} y={42} width={100} height={64} rx={8}>
          <path d="M14 12v28m4-28v28m5-28v28m3-28v28m6-28v28m3-28v28m5-28v28m4-28v28m3-28v28m6-28v28m3-28v28m5-28v28m4-28v28m3-28v28m6-28v28m4-28v28" stroke={ink} strokeWidth="2" />
          <rect x="14" y="46" width="72" height="8" rx="4" fill="var(--blue)" />
        </Sheet>
      </g>
    </Box>
    {/* The route it takes: a flight, then a van. */}
    <path d="M214 120C280 30 410 30 440 190" stroke="var(--blue)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    <g transform="translate(330 56) rotate(90) scale(.8)" fill="var(--blue)">
      <path d="M0-26c3.3 0 5.3 4 5.3 10v12l24.7 14.7v6.6L5.3 9.9v14l8 6v4L0 30.6l-13.3 3.3v-4l8-6v-14L-30 17.3v-6.6L-5.3-4v-12c0-6 2-10 5.3-10Z" />
    </g>
    <Truck x={404} y={199} tone="blue" scale={2.6} />
    <Pip x={286} ground={262} width={130} mood="wait" side={1} />
  </>;
}

function Handmade() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* A parcel tied with string, a stamp on it. */}
    <Box x={70} y={118} width={170} height={130}>
      <path d="M0 74H170M100 31V130" stroke="var(--rose)" strokeWidth="4" />
      <path d="M100 74c-14-16-34-16-30-4 3 9 18 7 30 4Zm0 0c14-16 34-16 30-4-3 9-18 7-30 4Z" stroke="var(--rose)" strokeWidth="4" strokeLinejoin="round" />
      <g transform="translate(18 44)">
        <rect width="44" height="52" rx="2" fill={paper} />
        <rect x="6" y="6" width="32" height="40" rx="2" fill="var(--rose-soft)" />
        <path d="m13 34 9-14 9 14z" fill="var(--rose)" />
      </g>
    </Box>
    {/* The post van that takes it. */}
    <Truck x={300} y={191} tone="rose" scale={3} />
    <Pip x={520} ground={262} width={170} mood="eager" side={-1} />
  </>;
}

function SecondHand() {
  // The drop-off code's dots, after its three corner squares.
  const dots = [[54, 40], [62, 48], [54, 56], [62, 64], [38, 70], [46, 70], [70, 70], [54, 80], [70, 80], [78, 88], [62, 96], [86, 96]] as const;
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* The seller's phone: the code a drop-off point scans. */}
    <Phone x={40} y={40} width={120} height={200}>
      {[[26, 36], [74, 36], [26, 84]].map(([x, y]) => <g key={`${x}-${y}`}>
        <rect x={x} y={y} width="22" height="22" rx="4" fill={ink} />
        <rect x={x + 4} y={y + 4} width="14" height="14" rx="2" fill={paper} />
        <rect x={x + 8} y={y + 8} width="6" height="6" rx="1" fill={ink} />
      </g>)}
      {dots.map(([x, y]) => <rect key={`${x}-${y}`} x={x} y={y} width="7" height="7" rx="1.5" fill={ink} />)}
      <rect x="22" y="128" width="76" height="9" rx="4.5" fill="var(--control)" />
      <rect x="22" y="146" width="52" height="9" rx="4.5" fill="var(--control)" />
      <rect x="22" y="166" width="76" height="20" rx="10" fill="var(--lilac)" />
    </Phone>
    {/* A parcel with a handwritten label, and the van that takes it on. */}
    <path d="M168 104C290 20 450 20 516 132" stroke="var(--lilac)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    <Box x={200} y={172} width={88} height={76}>
      <g transform="rotate(-5 44 45)">
        <Sheet x={12} y={26} width={64} height={38} rx={6}>
          <path d="M10 13c5-5 8 3 13-1s8 3 13-1 7 2 10 0" stroke={ink} strokeWidth="2.4" strokeLinecap="round" strokeLinejoin="round" />
          <path d="M10 26c4-3 8 3 12-1s7 2 11-1" stroke={soft} strokeWidth="2.4" strokeLinecap="round" strokeLinejoin="round" />
        </Sheet>
      </g>
    </Box>
    <Truck x={330} y={191} tone="lilac" scale={3} />
    <Pip x={540} ground={262} width={150} mood="eager" side={-1} />
  </>;
}

function Return() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Pip x={104} ground={262} width={140} mood="joy" side={1} />
    {/* The drop-off receipt, kept until the refund comes. */}
    <g transform="rotate(-6 236 166)">
      <Sheet x={194} y={100} width={84} height={124} rx={8}>
        <rect x="14" y="16" width="56" height="9" rx="4.5" fill="var(--control)" />
        <rect x="14" y="34" width="40" height="9" rx="4.5" fill="var(--control)" />
        <path d="M16 62v30m4-30v30m5-30v30m3-30v30m6-30v30m3-30v30m5-30v30m4-30v30m3-30v30m6-30v30m4-30v30" stroke={ink} strokeWidth="2" />
        <rect x="14" y="102" width="56" height="9" rx="4.5" fill="var(--green)" />
      </Sheet>
    </g>
    {/* The van takes the parcel back to the shop's warehouse. */}
    <path d="M300 150C350 96 420 96 452 130" stroke="var(--green)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    <Box x={318} y={170} width={44} height={34} />
    <Truck x={306} y={199} tone="green" scale={2.6} />
    <path d="M440 136 527 90l87 46Z" fill="var(--green)" />
    <rect x="452" y="132" width="150" height="116" fill="var(--green)" />
    <rect x="496" y="176" width="62" height="72" rx="3" fill={paper} opacity=".25" />
    {[188, 200, 212, 224, 236].map((y) => <path key={y} d={`M500 ${y}H554`} stroke={paper} strokeWidth="2" opacity=".5" />)}
    <path d="M512 150h24a9 9 0 0 1 0 18h-6m-18-18 6-6m-6 6 6 6" stroke={paper} strokeWidth="3.5" strokeLinecap="round" strokeLinejoin="round" />
  </>;
}

function Lockers() {
  const doors = [[314, 54, 56], [450, 54, 56], [518, 54, 56], [314, 118, 56], [518, 118, 56], [314, 182, 54], [382, 182, 54], [450, 182, 54], [518, 182, 54]] as const;
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* The phone with the code that opens the door. */}
    <Phone x={40} y={48} width={116} height={192}>
      <rect x="20" y="40" width="56" height="9" rx="4.5" fill="var(--control)" />
      {['4', '8', '2', '9', '1', '3'].map((digit, index) => <g key={index} transform={`translate(${13 + index % 3 * 31} ${62 + Math.floor(index / 3) * 38})`}>
        <rect width="26" height="32" rx="6" fill="var(--rose-soft)" />
        <text x="13" y="22" textAnchor="middle" fontFamily={MONO} fontSize="18" fontWeight="700" fill={ink}>{digit}</text>
      </g>)}
      <rect x="20" y="150" width="76" height="20" rx="10" fill="var(--rose)" />
    </Phone>
    <path d="M168 92C230 30 300 24 380 44" stroke="var(--rose)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    {/* A bank of lockers: its screen and keypad, and the door Pip came out of. */}
    <rect x="300" y="40" width="290" height="208" rx="12" fill="var(--rose)" />
    {doors.map(([x, y, height]) => <g key={`${x}-${y}`}>
      <rect x={x} y={y} width="58" height={height} rx="5" fill={paper} opacity=".2" />
      <circle cx={x + 48} cy={y + height / 2} r="3.5" fill={paper} opacity=".6" />
    </g>)}
    <rect x="382" y="54" width="58" height="56" rx="5" fill={paper} />
    <rect x="392" y="66" width="38" height="8" rx="4" fill="var(--control)" />
    <rect x="392" y="82" width="26" height="8" rx="4" fill="var(--control)" />
    {Array.from({ length: 9 }, (_, index) => <circle key={index} cx={395 + index % 3 * 16} cy={130 + Math.floor(index / 3) * 15} r="4" fill={paper} opacity=".75" />)}
    <rect x="450" y="118" width="58" height="56" rx="4" fill="#20251E" opacity=".55" />
    <path d="M508 118 538 108v76l-30-10z" fill={paper} />
    <circle cx="530" cy="146" r="3.5" fill="var(--rose)" />
    <Pip x={472} ground={262} width={136} mood="joy" side={-1} />
  </>;
}

function Fashion() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Pip x={118} ground={262} width={150} mood="eager" side={1} />
    {/* The parcel, its tag showing a coat hanger. */}
    <path d="M532 164C514 72 336 62 298 156" stroke="var(--blue)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    <Box x={240} y={168} width={104} height={80} />
    <path d="M316 168c8-14 20-22 34-22" stroke={soft} strokeWidth="2.4" strokeLinecap="round" />
    <g transform="rotate(10 382 160)">
      <Sheet x={346} y={136} width={72} height={52} rx={8}>
        <circle cx="12" cy="12" r="4" fill="var(--blue-soft)" />
        <path d="M33 18a5 5 0 1 1 5 5v4l17 12H21l17-12" stroke={ink} strokeWidth="2.6" strokeLinecap="round" strokeLinejoin="round" />
      </Sheet>
    </g>
    {/* The van that brought it. */}
    <Truck x={604} y={172} tone="blue" scale={4} flip />
  </>;
}

function OneBox() {
  const doors = [[394, 70, 50], [458, 70, 50], [522, 70, 50], [394, 128, 52], [522, 128, 52], [394, 188, 46], [458, 188, 46], [522, 188, 46]] as const;
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* The order on a phone: it's in the locker. */}
    <Phone x={40} y={40} width={116} height={200}>
      <Box x={24} y={40} width={68} height={50} />
      <rect x="20" y="108" width="76" height="9" rx="4.5" fill="var(--control)" />
      <rect x="20" y="126" width="52" height="9" rx="4.5" fill="var(--control)" />
      <rect x="20" y="164" width="76" height="20" rx="10" fill="var(--orange)" />
    </Phone>
    <Pip x={262} ground={262} width={140} mood="eager" side={1} />
    {/* A locker with one door open, the parcel inside. */}
    <rect x="380" y="56" width="210" height="192" rx="12" fill="var(--orange)" />
    {doors.map(([x, y, height]) => <g key={`${x}-${y}`}>
      <rect x={x} y={y} width="54" height={height} rx="5" fill={paper} opacity=".2" />
      <circle cx={x + 44} cy={y + height / 2} r="3.5" fill={paper} opacity=".6" />
    </g>)}
    <rect x="458" y="128" width="54" height="52" rx="4" fill="#20251E" opacity=".55" />
    <Box x={466} y={146} width={38} height={30} />
    <path d="M458 128 432 118v72l26-10z" fill={paper} />
    <circle cx="440" cy="154" r="3.5" fill="var(--orange)" />
  </>;
}

function Haul() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* The plane that brought them, and the way down. */}
    <g transform="translate(150 74) rotate(90) scale(1.3)" fill="var(--ochre)">
      <path d="M0-26c3.3 0 5.3 4 5.3 10v12l24.7 14.7v6.6L5.3 9.9v14l8 6v4L0 30.6l-13.3 3.3v-4l8-6v-14L-30 17.3v-6.6L-5.3-4v-12c0-6 2-10 5.3-10Z" />
    </g>
    <path d="M196 92C250 70 300 84 318 116" stroke="var(--ochre)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    {/* A stack of small parcels, and the van that takes them on. */}
    <Box x={266} y={196} width={92} height={52} />
    <Box x={278} y={154} width={66} height={42} />
    <Box x={292} y={122} width={40} height={32} />
    <Truck x={380} y={191} tone="ochre" scale={3} />
    <Pip x={548} ground={262} width={140} mood="look" side={-1} />
  </>;
}

function Mailer() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Truck x={40} y={172} tone="rose" scale={4} />
    <path d="M206 150C236 96 290 92 316 126" stroke="var(--rose)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    {/* A soft mailer bag with a scarf hanging out of it, and its size tag. */}
    <rect x="292" y="128" width="164" height="120" rx="12" fill={paper} />
    <path d="M292 152h164" stroke="var(--rose)" strokeWidth="3" strokeDasharray="8 7" opacity=".7" />
    <path d="M320 118v104" stroke="var(--rose)" strokeWidth="24" strokeLinecap="round" />
    <path d="M308 150h24M308 182h24" stroke={paper} strokeWidth="5" />
    <path d="M312 234v12M320 234v14M328 234v12" stroke="var(--rose)" strokeWidth="3" strokeLinecap="round" />
    <path d="M440 160c14 6 22 14 26 24" stroke={soft} strokeWidth="2.4" strokeLinecap="round" />
    <g transform="rotate(-8 482 200)"><Sheet x={456} y={184} width={52} height={40} rx={8}>
      <circle cx="10" cy="10" r="3.5" fill="var(--rose-soft)" />
      <text x="28" y="31" textAnchor="middle" fontFamily={MONO} fontSize="20" fontWeight="700" fill={ink}>M</text>
    </Sheet></g>
    <Pip x={558} ground={262} width={136} mood="joy" side={-1} />
  </>;
}

function Countdown() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* The plane that brought it, and the way down to the door. */}
    <g transform="translate(116 76) rotate(90) scale(1.2)" fill="var(--lilac)">
      <path d="M0-26c3.3 0 5.3 4 5.3 10v12l24.7 14.7v6.6L5.3 9.9v14l8 6v4L0 30.6l-13.3 3.3v-4l8-6v-14L-30 17.3v-6.6L-5.3-4v-12c0-6 2-10 5.3-10Z" />
    </g>
    <path d="M160 82C214 70 254 100 262 152" stroke="var(--lilac)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    <Box x={214} y={170} width={96} height={78} />
    <Pip x={378} ground={262} width={128} mood="wait" side={1} />
    {/* The order on a phone, its protection clock running down. */}
    <Phone x={466} y={40} width={124} height={200}>
      <circle cx="62" cy="68" r="32" stroke="var(--control)" strokeWidth="9" />
      <path d="M62 36a32 32 0 1 1-30.4 42" stroke="var(--lilac)" strokeWidth="9" strokeLinecap="round" />
      <path d="M62 52v17l11 7" stroke={ink} strokeWidth="3" strokeLinecap="round" strokeLinejoin="round" />
      <rect x="22" y="122" width="80" height="9" rx="4.5" fill="var(--control)" />
      <rect x="22" y="140" width="54" height="9" rx="4.5" fill="var(--control)" />
      <rect x="22" y="166" width="80" height="20" rx="10" fill="var(--lilac)" />
    </Phone>
  </>;
}

function Freight() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Truck x={40} y={172} tone="green" scale={4} />
    {/* A wardrobe-sized box on a hand truck. */}
    <Box x={286} y={56} width={112} height={180} />
    <path d="M270 40v196h140" stroke={ink} strokeWidth="6" strokeLinecap="round" strokeLinejoin="round" />
    <path d="M262 40h16" stroke={ink} strokeWidth="6" strokeLinecap="round" />
    <circle cx="292" cy="242" r="12" fill={ink} />
    <circle cx="292" cy="242" r="4.5" fill={paper} />
    <path d="M214 132C236 104 252 98 264 100" stroke="var(--green)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    <Pip x={518} ground={262} width={140} mood="joy" side={-1} />
  </>;
}

function Showroom() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Truck x={36} y={172} tone="orange" scale={4} />
    {/* A flat TV box, too big for a letterbox. */}
    <Box x={170} y={130} width={140} height={118}>
      <rect x="31" y="44" width="78" height="48" rx="5" stroke={CARD.ink} strokeWidth="5" />
      <path d="M57 104h26M70 92v12" stroke={CARD.ink} strokeWidth="5" strokeLinecap="round" />
    </Box>
    <Box x={318} y={214} width={40} height={34} />
    <Pip x={418} ground={262} width={108} mood="joy" side={1} />
    {/* The shop, its sign showing where parcels are picked up. */}
    <path d="M446 112 526 70l80 42Z" fill="var(--orange)" />
    <rect x="458" y="108" width="136" height="140" fill="var(--orange)" />
    <rect x="494" y="176" width="64" height="72" rx="3" fill={paper} opacity=".25" />
    <rect x="490" y="126" width="72" height="36" rx="8" fill={paper} />
    <rect x="512" y="134" width="28" height="22" rx="3" fill={CARD.top} />
    <rect x="523" y="134" width="6" height="6" fill={CARD.tape} />
  </>;
}

function Relay() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* The pickup point: its awning, its sign and the step. */}
    <rect x="44" y="104" width="164" height="144" fill="var(--ochre)" />
    <rect x="34" y="88" width="184" height="24" rx="6" fill="var(--ochre)" />
    <rect x="86" y="134" width="80" height="34" rx="8" fill={paper} />
    <rect x="112" y="141" width="28" height="20" rx="3" fill={CARD.top} />
    <rect x="123" y="141" width="6" height="6" fill={CARD.tape} />
    <rect x="96" y="186" width="60" height="62" rx="3" fill={paper} opacity=".25" />
    <rect x="80" y="240" width="92" height="10" rx="3" fill={soft} />
    <Pip x={262} ground={262} width={116} mood="look" side={1} />
    {/* What came: books, a game and the box they travelled in. */}
    <Box x={330} y={168} width={92} height={80} />
    {[['blue', 444, 212], ['rose', 450, 194], ['green', 440, 176]].map(([tone, x, y]) => <g key={tone}>
      <rect x={x} y={y} width="96" height="18" rx="3" fill={`var(--${tone})`} />
      <path d={`M${Number(x) + 10} ${Number(y) + 9}H${Number(x) + 86}`} stroke={paper} strokeWidth="2" opacity=".6" />
    </g>)}
    <rect x="452" y="230" width="96" height="18" rx="3" fill="var(--lilac)" />
    <rect x="556" y="146" width="56" height="102" rx="5" fill="var(--lilac)" />
    <rect x="564" y="164" width="40" height="24" rx="12" fill={paper} opacity=".85" />
    <path d="M574 171v10m-5-5h10" stroke="var(--lilac)" strokeWidth="3" strokeLinecap="round" />
    <circle cx="591" cy="173" r="2.6" fill="var(--lilac)" />
    <circle cx="597" cy="180" r="2.6" fill="var(--lilac)" />
  </>;
}

function Department() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* The department store: its floors of windows, the striped awning and the door. */}
    <rect x="40" y="52" width="210" height="196" fill="var(--green)" />
    {[70, 112].map((y) => [58, 104, 150, 196].map((x) => <rect key={`${x}-${y}`} x={x} y={y} width="36" height="28" rx="3" fill={paper} opacity=".25" />))}
    <path d="M30 150H260l-8 26H38Z" fill={paper} />
    {Array.from({ length: 7 }, (_, index) => <path key={index} d={`M${46 + index * 30} 150h15l-1 26h-14Z`} fill="var(--green-soft)" />)}
    <path d="M38 176h214" stroke="var(--green-soft)" strokeWidth="4" strokeLinecap="round" />
    <rect x="114" y="190" width="62" height="58" rx="3" fill={paper} opacity=".3" />
    <path d="M145 190v58" stroke="var(--green)" strokeWidth="3" />
    <Truck x={262} y={184} tone="green" scale={3} />
    {/* A box wrapped as a present, ribbon and bow. */}
    <Box x={392} y={170} width={88} height={78}>
      <path d="M44 0v78M0 40h88" stroke="var(--green)" strokeWidth="9" />
    </Box>
    <path d="M436 170c-22-22-34-4-20 2 6 3 14 0 20-2Zm0 0c22-22 34-4 20 2-6 3-14 0-20-2Z" fill="var(--green)" />
    <Pip x={548} ground={262} width={130} mood="joy" side={-1} />
  </>;
}

function Megastore() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* A bank of lockers; Pip looks out of the big one at the bottom. */}
    <rect x="40" y="44" width="232" height="204" rx="12" fill="var(--blue)" />
    {[[54, 58, 62, 54], [128, 58, 62, 54], [202, 58, 56, 54], [54, 124, 62, 54], [202, 124, 56, 54], [54, 190, 62, 46], [202, 190, 56, 46]].map(([x, y, width, height]) => <g key={`${x}-${y}`}>
      <rect x={x} y={y} width={width} height={height} rx="5" fill={paper} opacity=".2" />
      <circle cx={x + width - 10} cy={y + height / 2} r="3.5" fill={paper} opacity=".6" />
    </g>)}
    <rect x="128" y="124" width="62" height="112" rx="4" fill="#20251E" opacity=".55" />
    <path d="M190 124l26-10v132l-26-10z" fill={paper} />
    <Pip x={159} ground={236} width={58} mood="look" side={1} />
    {/* Boxes of every size, as a big marketplace sends them. */}
    <Box x={316} y={170} width={128} height={78} />
    <Box x={334} y={110} width={88} height={60} />
    <Box x={358} y={76} width={40} height={34} />
    <Box x={452} y={196} width={70} height={52} />
    <Box x={464} y={150} width={46} height={46} />
    <Box x={530} y={222} width={54} height={26} />
    <Box x={536} y={184} width={40} height={38} />
  </>;
}

function Tower() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Truck x={36} y={172} tone="rose" scale={4} />
    {/* A computer tower's box and a monitor's, on a hand truck. */}
    <Box x={262} y={82} width={76} height={154}>
      <rect x="22" y="56" width="32" height="74" rx="4" stroke={CARD.ink} strokeWidth="4" />
      <circle cx="38" cy="72" r="5" stroke={CARD.ink} strokeWidth="3" />
    </Box>
    <Box x={338} y={140} width={104} height={96}>
      <rect x="20" y="36" width="64" height="38" rx="4" stroke={CARD.ink} strokeWidth="4" />
      <path d="M44 82h16M52 74v8" stroke={CARD.ink} strokeWidth="4" strokeLinecap="round" />
    </Box>
    <path d="M248 66v170h206" stroke={ink} strokeWidth="6" strokeLinecap="round" strokeLinejoin="round" />
    <path d="M240 66h16" stroke={ink} strokeWidth="6" strokeLinecap="round" />
    <circle cx="270" cy="242" r="12" fill={ink} />
    <circle cx="270" cy="242" r="4.5" fill={paper} />
    {/* The front door, open, and Pip waiting by it. */}
    <rect x="500" y="88" width="100" height="160" fill="var(--rose)" opacity=".3" />
    <rect x="516" y="104" width="68" height="144" rx="3" fill="#20251E" opacity=".55" />
    <path d="M516 104l-28 12v132l28-6z" fill="var(--rose)" />
    <circle cx="496" cy="182" r="3.5" fill={paper} />
    <Pip x={548} ground={262} width={100} mood="eager" side={-1} />
  </>;
}

function Appliance() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Truck x={44} y={172} tone="rose" scale={4} />
    <path d="M190 150C214 118 236 110 252 112" stroke="var(--rose)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    {/* A washing machine's box: its dials and its round door. */}
    <Box x={262} y={110} width={136} height={138}>
      <rect x="16" y="44" width="40" height="10" rx="3" fill={CARD.ink} opacity=".7" />
      <circle cx="98" cy="49" r="5" fill={CARD.ink} opacity=".7" />
      <circle cx="116" cy="49" r="5" fill={CARD.ink} opacity=".7" />
      <circle cx="68" cy="96" r="30" stroke={CARD.ink} strokeWidth="6" />
      <circle cx="68" cy="96" r="17" stroke={CARD.ink} strokeWidth="3" opacity=".6" />
    </Box>
    <Box x={414} y={190} width={68} height={58} />
    <Pip x={330} ground={110} width={112} mood="joy" side={1} />
  </>;
}

function Shelves() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* Warehouse shelving: two uprights and three shelves of boxes. */}
    <rect x="40" y="36" width="10" height="212" rx="3" fill="var(--lilac)" />
    <rect x="310" y="36" width="10" height="212" rx="3" fill="var(--lilac)" />
    {[100, 170, 240].map((y) => <rect key={y} x="34" y={y} width="292" height="8" rx="3" fill="var(--lilac)" />)}
    <Box x={208} y={62} width={70} height={38} />
    <Box x={62} y={118} width={72} height={52} />
    <Box x={144} y={136} width={46} height={34} />
    <Box x={200} y={108} width={100} height={62} />
    <Box x={58} y={182} width={100} height={58} />
    <Box x={168} y={204} width={52} height={36} />
    <Box x={230} y={190} width={70} height={50} />
    <Pip x={126} ground={100} width={92} mood="look" side={1} />
    <path d="M336 150C362 128 384 128 400 140" stroke="var(--lilac)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    <Truck x={420} y={172} tone="lilac" scale={4} />
  </>;
}

function FlatPack() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Truck x={40} y={172} tone="blue" scale={4} />
    {/* Long flat-pack boxes, stacked, and the key that builds what's inside. */}
    <Box x={222} y={212} width={268} height={36} />
    <Box x={238} y={180} width={236} height={32} />
    <Box x={230} y={150} width={252} height={30} />
    <Box x={256} y={124} width={200} height={26} />
    <path d="M522 238H584V198" stroke="var(--blue)" strokeWidth="8" strokeLinecap="round" strokeLinejoin="round" />
    <Pip x={356} ground={124} width={112} mood="joy" side={-1} />
  </>;
}

function Bag() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Truck x={40} y={172} tone="rose" scale={4} />
    <path d="M190 150C216 116 248 106 276 114" stroke="var(--rose)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    {/* A paper shopping bag with rope handles, and Pip peeking out of it. */}
    <path d="M326 150C326 64 444 64 444 150" stroke="var(--rose)" strokeWidth="6" strokeLinecap="round" />
    <Pip x={385} ground={176} width={108} mood="eager" side={-1} />
    <rect x="292" y="155" width="186" height="96" rx="6" fill={ink} opacity=".07" />
    <rect x="292" y="152" width="186" height="96" rx="6" fill={paper} />
    <path d="M292 152l14 14M478 152l-14 14" stroke={line} strokeWidth="2" strokeLinecap="round" />
    <rect x="292" y="206" width="186" height="14" fill="var(--rose)" opacity=".85" />
  </>;
}

function Bike() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* A long bike box, the bike drawn on its side. */}
    <Box x={52} y={118} width={262} height={130}>
      <circle cx="74" cy="88" r="26" stroke={CARD.ink} strokeWidth="5" />
      <circle cx="188" cy="88" r="26" stroke={CARD.ink} strokeWidth="5" />
      <path d="M74 88 104 56H160L188 88M104 56 128 88H74M128 88 150 50M140 50H162M98 48H114" stroke={CARD.ink} strokeWidth="5" strokeLinecap="round" strokeLinejoin="round" />
    </Box>
    {/* A football, and Pip balancing on it. */}
    <circle cx="384" cy="214" r="34" fill={paper} />
    <path d="M384 200l12 9-5 14h-14l-5-14z" fill="var(--green)" />
    <path d="M384 180v20M396 209l20-8M391 223l11 17M377 223l-11 17M372 209l-20-8" stroke={soft} strokeWidth="2.4" strokeLinecap="round" />
    <Pip x={384} ground={182} width={96} mood="joy" side={1} />
    <Truck x={604} y={172} tone="green" scale={4} flip />
  </>;
}

function Shoebox() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Truck x={40} y={172} tone="ochre" scale={4} />
    {/* An open shoebox, its lid leaning behind, a pair of trainers inside. */}
    <rect x="232" y="146" width="150" height="34" rx="4" fill={CARD.left} transform="rotate(-8 232 180)" />
    {[[300, 160, .8], [252, 166, 1]].map(([x, y, opacity]) => <g key={x} transform={`translate(${x} ${y})`} opacity={opacity}>
      <path d="M0 44V12Q0 0 12 2L22 4Q28 16 40 18L60 24Q78 28 80 40V44Z" fill="var(--ochre)" />
      <path d="M0 42H80" stroke={paper} strokeWidth="5" />
      <path d="M24 10l6 6M32 13l6 6" stroke={paper} strokeWidth="2.4" strokeLinecap="round" />
    </g>)}
    <rect x="236" y="200" width="150" height="48" rx="4" fill={CARD.top} />
    <path d="M236 210H386" stroke={CARD.left} strokeWidth="2" />
    {/* The closed one, with Pip on it. */}
    <Box x={418} y={182} width={150} height={66} />
    <Pip x={493} ground={182} width={100} mood="joy" side={-1} />
  </>;
}

function Hanger() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Truck x={40} y={172} tone="lilac" scale={4} />
    <path d="M186 150C204 126 220 118 236 120" stroke="var(--lilac)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    {/* A clothes rail, a coat on its hanger. */}
    <path d="M256 248V52H428V248M244 248H268M416 248H440" stroke={soft} strokeWidth="5" strokeLinecap="round" strokeLinejoin="round" />
    <path d="M334 60a8 8 0 1 1 8 8v12M342 80 272 112H412Z" stroke={ink} strokeWidth="4" strokeLinecap="round" strokeLinejoin="round" />
    <path d="M278 112H406L418 232H266Z" fill="var(--lilac)" />
    <path d="M318 112 342 148 366 112M342 148V232" stroke={paper} strokeWidth="3" strokeLinecap="round" strokeLinejoin="round" opacity=".85" />
    {[168, 192, 216].map((cy) => <circle key={cy} cx="352" cy={cy} r="4" fill={paper} />)}
    {/* Folded jumpers, and Pip standing on them. */}
    <rect x="456" y="222" width="124" height="26" rx="9" fill="var(--lilac)" opacity=".6" />
    <rect x="462" y="196" width="112" height="26" rx="9" fill={paper} />
    <path d="M478 209H558" stroke="var(--lilac)" strokeWidth="3" strokeLinecap="round" />
    <Pip x={518} ground={196} width={96} mood="joy" side={-1} />
  </>;
}

function Phonebox() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    {/* An electric scooter, and Pip standing on its deck. */}
    <path d="M286 212 304 92M288 94H326" stroke={soft} strokeWidth="7" strokeLinecap="round" />
    <rect x="98" y="206" width="196" height="12" rx="6" fill={ink} />
    {[110, 292].map((cx) => <g key={cx}><circle cx={cx} cy="228" r="18" fill={ink} /><circle cx={cx} cy="228" r="7" fill="var(--blue)" /></g>)}
    <Pip x={198} ground={206} width={96} mood="joy" side={1} />
    {/* A small phone box, the phone drawn on its lid. */}
    <rect x="352" y="183" width="86" height="68" rx="6" fill={ink} opacity=".07" />
    <rect x="352" y="180" width="86" height="68" rx="6" fill={paper} />
    <rect x="380" y="190" width="30" height="50" rx="7" stroke="var(--blue)" strokeWidth="4" />
    <Truck x={604} y={172} tone="blue" scale={4} flip />
  </>;
}

function Laptop() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Truck x={40} y={172} tone="orange" scale={4} />
    <path d="M190 150C216 118 248 110 276 118" stroke="var(--orange)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    {/* A slim laptop box, a small phone box on it, and Pip on top. */}
    <rect x="282" y="217" width="236" height="34" rx="5" fill={ink} opacity=".07" />
    <rect x="282" y="214" width="236" height="34" rx="5" fill={paper} />
    <path d="M376 224H424L432 238H368Z" stroke="var(--orange)" strokeWidth="3" strokeLinejoin="round" />
    <rect x="352" y="173" width="96" height="44" rx="5" fill={ink} opacity=".07" />
    <rect x="352" y="170" width="96" height="44" rx="5" fill={paper} />
    <rect x="390" y="178" width="20" height="28" rx="5" stroke="var(--orange)" strokeWidth="3" />
    <Pip x={400} ground={170} width={96} mood="joy" side={-1} />
  </>;
}

function Perfume() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Truck x={40} y={172} tone="rose" scale={4} />
    <path d="M186 150C206 124 226 116 246 120" stroke="var(--rose)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    {/* A tall perfume bottle and a lipstick. */}
    <rect x="290" y="106" width="28" height="24" rx="3" fill={paper} />
    <rect x="282" y="80" width="44" height="30" rx="7" fill={ink} />
    <rect x="262" y="128" width="84" height="120" rx="16" fill="var(--rose)" />
    <rect x="280" y="168" width="48" height="40" rx="5" fill={paper} opacity=".9" />
    <path d="M274 146V230" stroke={paper} strokeWidth="5" strokeLinecap="round" opacity=".5" />
    <path d="M375 182V158L393 148V182Z" fill="var(--rose)" />
    <rect x="372" y="180" width="24" height="18" rx="2" fill={soft} />
    <rect x="368" y="196" width="32" height="52" rx="4" fill={ink} />
    {/* A gift box, ribbon and bow, and Pip on it. */}
    <rect x="432" y="192" width="132" height="56" rx="5" fill={paper} />
    <rect x="426" y="178" width="144" height="18" rx="4" fill={paper} />
    <path d="M426 196H570" stroke={line} strokeWidth="2" />
    <rect x="458" y="178" width="14" height="70" fill="var(--rose)" />
    <path d="M465 178C453 160 437 162 441 172C444 180 457 179 465 178ZM465 178C477 160 493 162 489 172C486 180 473 179 465 178Z" stroke="var(--rose)" strokeWidth="4" strokeLinejoin="round" />
    <Pip x={526} ground={178} width={92} mood="joy" side={-1} />
  </>;
}

function Crate() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Truck x={40} y={172} tone="blue" scale={4} />
    <path d="M186 150C204 126 220 118 236 120" stroke="var(--blue)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    {/* A garden chair beside a crate of boxed tools. */}
    <path d="M264 112 290 190H338M330 190 342 248M290 190 276 248" stroke="var(--blue)" strokeWidth="7" strokeLinecap="round" strokeLinejoin="round" />
    <path d="M270 136 284 134M276 156 290 154" stroke="var(--blue)" strokeWidth="5" strokeLinecap="round" opacity=".6" />
    <Box x={374} y={152} width={52} height={36} />
    <Box x={432} y={140} width={44} height={48} />
    <rect x="360" y="186" width="210" height="62" rx="5" fill={paper} />
    {[204, 222, 240].map((y) => <path key={y} d={`M372 ${y}H558`} stroke="var(--blue)" strokeWidth="3" strokeLinecap="round" opacity=".45" />)}
    <path d="M360 191H570" stroke="var(--blue)" strokeWidth="4" />
    <Pip x={524} ground={186} width={92} mood="joy" side={-1} />
  </>;
}

function Paint() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Truck x={40} y={172} tone="green" scale={4} />
    <path d="M186 150C204 126 220 118 236 120" stroke="var(--green)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    {/* A stack of planks, a paint tin and a roller on them. */}
    {[[262, 230, CARD.left], [272, 212, CARD.top], [262, 194, CARD.left]].map(([x, y, fill]) => <g key={y}>
      <rect x={x} y={y} width="300" height="18" rx="3" fill={fill as string} />
      <path d={`M${Number(x) + 40} ${Number(y) + 9}H${Number(x) + 120}M${Number(x) + 170} ${Number(y) + 9}H${Number(x) + 250}`} stroke={CARD.ink} strokeWidth="2" strokeLinecap="round" opacity=".5" />
    </g>)}
    <path d="M300 136C300 108 370 108 370 136" stroke={ink} strokeWidth="4" strokeLinecap="round" />
    <rect x="296" y="132" width="78" height="62" rx="6" fill="var(--green)" />
    <rect x="292" y="126" width="86" height="12" rx="4" fill={soft} />
    <path d="M312 138V160Q312 168 318 168Q324 168 324 160V138" fill={paper} opacity=".85" />
    <path d="M386 194 394 162H404" stroke={ink} strokeWidth="5" strokeLinecap="round" strokeLinejoin="round" />
    <rect x="400" y="150" width="54" height="22" rx="11" fill={paper} stroke="var(--green)" strokeWidth="4" />
    <Pip x={516} ground={194} width={92} mood="joy" side={-1} />
  </>;
}

function Refurb() {
  return <>
    <path d="M40 248H600" stroke={line} strokeWidth="4" strokeLinecap="round" />
    <Truck x={40} y={172} tone="lilac" scale={4} />
    <path d="M186 150C204 126 220 118 236 120" stroke="var(--lilac)" strokeWidth="4" strokeLinecap="round" strokeDasharray="3 12" />
    {/* A phone standing up, a closed laptop with a green tag, and Pip on the laptop. */}
    <rect x="270" y="154" width="54" height="94" rx="10" fill={ink} />
    <rect x="277" y="164" width="40" height="70" rx="4" fill="var(--lilac)" />
    <circle cx="297" cy="241" r="3" fill={paper} />
    <rect x="348" y="225" width="196" height="26" rx="6" fill={ink} opacity=".07" />
    <rect x="348" y="222" width="196" height="26" rx="6" fill={paper} />
    <path d="M348 236H544" stroke="var(--lilac)" strokeWidth="3" />
    <path d="M540 230Q562 240 576 214" stroke={ink} strokeWidth="2" strokeLinecap="round" />
    <path d="M548 200H574L584 212L574 224H548Z" fill="var(--green)" />
    <circle cx="575" cy="212" r="3" fill={paper} />
    <path d="M553 212l5 5 9-10" stroke={paper} strokeWidth="3" strokeLinecap="round" strokeLinejoin="round" />
    <Pip x={446} ground={222} width={96} mood="joy" side={-1} />
  </>;
}

const SCENES: Record<string, { tone: string; draw: () => ReactNode }> = {
  'tracking-number-formats': { tone: 'blue', draw: Formats },
  'tracking-statuses': { tone: 'green', draw: Statuses },
  'tracking-not-updating': { tone: 'ochre', draw: Quiet },
  'delivered-not-received': { tone: 'orange', draw: Missing },
  customs: { tone: 'lilac', draw: Customs },
  'tracking-from-china': { tone: 'blue', draw: FromChina },
  'amazon-orders': { tone: 'orange', draw: Marketplace },
  'vinted-orders': { tone: 'green', draw: Resale },
  'ebay-orders': { tone: 'blue', draw: Auction },
  'etsy-orders': { tone: 'rose', draw: Handmade },
  'second-hand-orders': { tone: 'lilac', draw: SecondHand },
  'zalando-orders': { tone: 'blue', draw: Fashion },
  'allegro-orders': { tone: 'orange', draw: OneBox },
  'temu-orders': { tone: 'ochre', draw: Haul },
  'shein-orders': { tone: 'rose', draw: Mailer },
  'aliexpress-orders': { tone: 'lilac', draw: Countdown },
  'otto-orders': { tone: 'green', draw: Freight },
  'mediamarkt-orders': { tone: 'orange', draw: Showroom },
  'fnac-orders': { tone: 'ochre', draw: Relay },
  'el-corte-ingles-orders': { tone: 'green', draw: Department },
  'cdiscount-orders': { tone: 'blue', draw: Megastore },
  'pccomponentes-orders': { tone: 'rose', draw: Tower },
  'worten-orders': { tone: 'rose', draw: Appliance },
  'galaxus-orders': { tone: 'lilac', draw: Shelves },
  'ikea-orders': { tone: 'blue', draw: FlatPack },
  'zara-orders': { tone: 'rose', draw: Bag },
  'decathlon-orders': { tone: 'green', draw: Bike },
  'adidas-orders': { tone: 'ochre', draw: Shoebox },
  'h-m-orders': { tone: 'lilac', draw: Hanger },
  'xiaomi-orders': { tone: 'blue', draw: Phonebox },
  'apple-orders': { tone: 'orange', draw: Laptop },
  'douglas-orders': { tone: 'rose', draw: Perfume },
  'lidl-orders': { tone: 'blue', draw: Crate },
  'leroy-merlin-orders': { tone: 'green', draw: Paint },
  'back-market-orders': { tone: 'lilac', draw: Refurb },
  'find-tracking-number': { tone: 'ochre', draw: FindNumber },
  'universal-tracker': { tone: 'green', draw: Universal },
  'christmas-posting-dates': { tone: 'blue', draw: Christmas },
  'uncollected-parcel': { tone: 'ochre', draw: Uncollected },
  'parcel-lockers': { tone: 'rose', draw: Lockers },
  'damaged-parcel': { tone: 'orange', draw: Damaged },
  'lost-parcel': { tone: 'lilac', draw: Lost },
  'delivery-scam-texts': { tone: 'lilac', draw: Scam },
  'change-delivery-address': { tone: 'green', draw: ChangeAddress },
  'returned-to-sender': { tone: 'orange', draw: Returned },
  'returns-tracking': { tone: 'green', draw: Return },
  'missed-delivery': { tone: 'ochre', draw: Missed },
  'delivery-times': { tone: 'blue', draw: DeliveryTimes },
};

/** The ids that have a picture of their own. Every guide must. */
export const GUIDE_SCENES = Object.keys(SCENES);

/** A guide's picture. With a `label` it is an image to read out; without one it decorates a link that already names the guide. */
export function GuideScene({ id, label }: { id: string; label?: string }) {
  const scene = SCENES[id];
  if (!scene) return null;
  return <svg className="guide-scene" viewBox={`0 0 ${WIDTH} ${HEIGHT}`} fill="none" role={label ? 'img' : undefined} aria-label={label} aria-hidden={label ? undefined : true}>
    <rect width={WIDTH} height={HEIGHT} fill={`var(--${scene.tone}-soft)`} />
    {scene.draw()}
  </svg>;
}
