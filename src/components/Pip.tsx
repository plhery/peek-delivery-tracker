import type { CSSProperties, ReactNode } from 'react';

export type PaperPoint = readonly [number, number];
export interface PaperFlap {
  points: readonly [PaperPoint, PaperPoint, PaperPoint, PaperPoint];
  openedCorner: PaperPoint;
  /** A flap that swings up through upright to open, rather than folding straight over: how far it turns on its hinge, in degrees. */
  swing?: number;
}

/** The box in its 300 × 310 frame, shared by the kraft parcel and by Pip on the card maps. */
export const PARCEL = {
  inside: 'm55 142 95-47 95 47-95 48-95-48Z',
  left: 'm55 142 95 48v87l-95-48v-87Z',
  right: 'm150 190 95-48v87l-95 48v-87Z',
  hairlines: 'M56 144v84l93 47m2 0 92-46v-84',
  edges: 'm55 142 95 48 95-48m-95 48v87',
  tape: 'm96 122 13-7 95 48-13 7-95-48Z',
  seam: 'm103 119 94 47',
  /** The left side's plane, where the face is drawn. */
  facePlane: 'matrix(1 0.505263 0 1 55 142)',
  /** The right side's plane, where the carrier's label sits: just below where the open front flap reaches. */
  labelPlane: 'matrix(1 -0.505263 0 1 160 209)',
  barcode: 'M6 20v14m2.2-14v14m3-14v14m1.6-14v14m3.4-14v14m2-14v14m3-14v14m1.4-14v14m3.6-14v14m2-14v14m2.6-14v14m1.8-14v14m3.2-14v14m2-14v14m2.4-14v14m3-14v14m1.6-14v14',
  glint: 'M0 -1 .24 -.24 1 0 .24 .24 0 1 -.24 .24 -1 0 -.24 -.24Z',
  glints: [
    { x: 43, y: 96, size: 23, color: '#C99B35' },
    { x: 91, y: 57, size: 16, color: '#D6AE48' },
    { x: 151, y: 36, size: 24, color: '#C99B35' },
    { x: 216, y: 55, size: 18, color: '#B594BE' },
    { x: 261, y: 96, size: 25, color: '#D6AE48' },
    { x: 233, y: 145, size: 13, color: '#C99B35' },
  ],
  flaps: {
    backLeft: { points: [[55, 142], [150, 95], [190, 143], [95, 190]], openedCorner: [112, 48] },
    backRight: { points: [[150, 95], [245, 142], [197.5, 166], [102.5, 118.5]], openedCorner: [270, 88] },
    // The front flaps rest just above level, clear of the sides: the left one is a brim over Pip's eyes.
    frontRight: { points: [[245, 142], [150, 190], [102.5, 166], [197.5, 118.5]], openedCorner: [193, 189], swing: 154 },
    frontLeft: { points: [[55, 142], [150, 190], [197.5, 166], [102.5, 118.5]], openedCorner: [107, 189], swing: 154 },
  },
} as const satisfies Record<string, unknown> & { flaps: Record<string, PaperFlap> };

// Trigonometric results can differ at the last decimal across JS runtimes.
const coordinate = (value: number) => Number(value.toFixed(6));

/** A hinge stays fixed while the paper folds through it, just like the native parcel. */
function flapFold({ points, openedCorner, swing }: PaperFlap) {
  const [origin, hinge, corner] = points;
  const angle = Math.atan2(hinge[1] - origin[1], hinge[0] - origin[0]);
  const local = ([x, y]: PaperPoint): PaperPoint => [
    (x - origin[0]) * Math.cos(angle) + (y - origin[1]) * Math.sin(angle),
    (y - origin[1]) * Math.cos(angle) - (x - origin[0]) * Math.sin(angle),
  ];
  const from = local(corner), to = local(openedCorner);
  return { origin, angle, local, scale: to[1] / from[1], slant: (to[0] - from[0]) / from[1], arc: swing === undefined ? null : flapArc(local(hinge)[0], from, to, swing) };
}

/**
 * A swinging flap's corner turns about the hinge's end: at a turn of `a` it stands at `cos(a)` of where it lies
 * closed plus `sin(a)` of where it would stand upright. These are that arc's measures, in the hinge's frame and
 * as shares of the closed flap's depth, for the style sheet to turn the flap with.
 */
function flapArc(hingeLength: number, from: PaperPoint, to: PaperPoint, swing: number) {
  const turn = swing * Math.PI / 180;
  const closed = [from[0] - hingeLength, from[1]], opened = [to[0] - hingeLength, to[1]];
  const upright = closed.map((value, axis) => (opened[axis] - Math.cos(turn) * value) / Math.sin(turn));
  return { run: closed[0] / closed[1], lean: upright[0] / closed[1], rise: upright[1] / closed[1] };
}

/** Where a flap's corners rest, closed on the box or folded open on its hinge. */
export function flapPoints(flap: PaperFlap, open: boolean): string {
  if (!open) return flap.points.map((point) => point.join(',')).join(' ');
  const { origin, angle, local, scale, slant } = flapFold(flap);
  return flap.points.map((point) => {
    const [x, y] = local(point);
    const foldedX = x + y * slant, foldedY = y * scale;
    return [origin[0] + foldedX * Math.cos(angle) - foldedY * Math.sin(angle), origin[1] + foldedX * Math.sin(angle) + foldedY * Math.cos(angle)]
      .map((value) => Number(value.toFixed(2))).join(',');
  }).join(' ');
}

export function ParcelFlap({ flap, tone, ink, rear = false, hidden = false }: {
  flap: PaperFlap;
  tone: string;
  /** The colour of the flap's edge when it is not the kraft parcel's own: Pip on a map is drawn in his card's ink. */
  ink?: string;
  rear?: boolean;
  hidden?: boolean;
}) {
  const { origin, angle, local, scale, slant, arc } = flapFold(flap);
  const fold: Record<string, string | number> = arc ? {
    '--fold-turn': `${flap.swing}deg`,
    '--fold-run': coordinate(arc.run),
    '--fold-lean': coordinate(arc.lean),
    '--fold-rise': coordinate(arc.rise),
  } : {
    '--fold-scale': coordinate(scale),
    '--fold-skew': `${coordinate(Math.atan(slant) * 180 / Math.PI)}deg`,
  };
  return <g transform={`translate(${origin.join(' ')}) rotate(${coordinate(angle * 180 / Math.PI)})`}>
    <g className={`parcel-illustration__flap${arc ? ' parcel-illustration__flap--swing' : ''}${rear ? ' parcel-illustration__flap--rear' : ''}${hidden ? ' parcel-illustration__flap--hidden' : ''}`} style={fold as CSSProperties}>
      <polygon points={flap.points.map((point) => local(point).map(coordinate).join(',')).join(' ')} strokeOpacity=".24" strokeWidth=".7"
        {...(ink ? { style: { fill: tone, stroke: ink } } : { fill: tone, stroke: '#987450' })} />
    </g>
  </g>;
}

/**
 * Pip's face, in the plane of the box's left side. `k` scales the features: 1 at full size, larger on a small parcel.
 * The open eyes give way to happy arcs when the box opens.
 */
export function PipFace({ k = 1, look = [.5, -.2] }: { k?: number; look?: PaperPoint }) {
  const mouth = 47 + 4 * k;
  return <g className="parcel-illustration__pip" transform={PARCEL.facePlane}>
    {[48 - 15.5 * k, 48 + 15.5 * k].map((x) => <g key={x}>
      <g className="parcel-illustration__eye">
        <ellipse cx={x} cy="36" rx={10 * k} ry={11.5 * k} fill="#FFFDF6" />
        <circle cx={x + look[0] * 7 * k} cy={36 + look[1] * 7 * k} r={5.4 * k} fill="#20251E" />
        <circle cx={x + (look[0] * 7 - 1.8) * k} cy={36 + (look[1] * 7 - 2) * k} r={1.6 * k} fill="#FFFFFF" />
      </g>
      <path className="parcel-illustration__happy-eye" d={`M${x - 8 * k} ${36 + 3 * k}Q${x} ${36 - 8 * k} ${x + 8 * k} ${36 + 3 * k}`} stroke="#20251E" strokeWidth={3 * k} strokeLinecap="round" />
      <ellipse cx={x - 1} cy={36 + 15 * k} rx={6.5 * k} ry={3.2 * k} fill="#E9958F" opacity=".55" />
    </g>)}
    <path d={`M${48 - 6 * k} ${mouth}Q48 ${mouth + 7 * k} ${48 + 6 * k} ${mouth}`} stroke="#20251E" strokeWidth={2.4 * k} strokeLinecap="round" />
  </g>;
}

/**
 * Pip: kraft paper, a face on the left side, and a card tucked behind the front faces.
 * A `label`, drawn in the right side's plane, takes the place of the arrow and the seal there: Icon's ParcelIllustration
 * passes the carrier's. This file imports no carrier art, so a page that shows Pip alone (the error and not-found
 * pages, which every page loads) doesn't carry every carrier's truck.
 * With `ribbon`, the box is wrapped as a gift: a lilac ribbon tied in a bow on its lid, and a gift on the card inside.
 */
export function ParcelArt({ className = '', label, ribbon = false }: {
  className?: string;
  label?: ReactNode;
  ribbon?: boolean;
}) {
  return <svg className={`parcel-illustration ${className}`} viewBox="0 0 300 310" fill="none" aria-hidden="true">
    <ellipse className="parcel-illustration__shadow" cx="150" cy="286" rx="84" ry="10" fill="currentColor" opacity=".08" />
    <g className="parcel-illustration__body">
      <path d={PARCEL.inside} fill="#806345" />
      <ParcelFlap flap={PARCEL.flaps.backLeft} tone="#C4A078" rear hidden />
      <ParcelFlap flap={PARCEL.flaps.backRight} tone="#D8B997" rear />
      <ellipse className="parcel-illustration__light" cx="150" cy="140" rx="62" ry="20" fill="#FFE8AE" />
      <g className="parcel-illustration__delivery-card">
        <rect x="110" y="111" width="83" height="111" rx="7" fill="#FCFAF4" stroke="#E6E0D4" strokeWidth=".7" />
        <path d="M112 120v-2a5 5 0 0 1 5-5h69" stroke="white" strokeWidth="1.5" strokeLinecap="round" />
        {ribbon ? <>
          <circle cx="151.5" cy="137" r="12" fill="#EFE3F1" />
          <path d="M145.5 137h12v8h-12zM144.5 133.5h14v3.5h-14zM151.5 133.5v11.5M151.5 133.5c-5.5 0-4.5-5.5-1.7-3.9l1.7 3.9Zm0 0c5.5 0 4.5-5.5 1.7-3.9l-1.7 3.9Z" stroke="#7C6787" strokeWidth="1.2" strokeLinejoin="round" />
          <path d="M128 164h47" stroke="#DAD7CE" strokeWidth="4" strokeLinecap="round" />
          <path d="M137 177h29" stroke="#E7E4DC" strokeWidth="4" strokeLinecap="round" />
        </> : <>
          <circle cx="143" cy="145" r="19" fill="#E7ECE4" />
          <path className="parcel-illustration__check" d="m135 145 5 5 11-12" stroke="#587260" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round" />
          <path d="M124 181h45" stroke="#DAD7CE" strokeWidth="4" strokeLinecap="round" />
          <path d="M124 194h29" stroke="#E7E4DC" strokeWidth="4" strokeLinecap="round" />
        </>}
      </g>
      <path d={PARCEL.left} fill="#C9A47B" />
      <path d={PARCEL.right} fill="#B78F66" />
      <path className="parcel-illustration__face-light" d={PARCEL.left} fill="#FFF4D6" />
      <path d={PARCEL.hairlines} stroke="#987450" strokeOpacity=".25" strokeWidth=".8" />
      <path className="parcel-illustration__edge" d={PARCEL.edges} stroke="#FFF2CF" strokeWidth="1" />
      {label ?? <>
        <g transform="translate(201 201) rotate(-27)"><path d="M10 22V4m-5 5 5-5 5 5" stroke="#735C43" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" /></g>
        <g transform="translate(183 234) rotate(-27)">
          <circle r="14" fill="#DECCE2" />
          <circle className="parcel-illustration__seal-light" r="11.5" stroke="#FFF6FF" strokeWidth="1.2" />
          <path d="M0-7V7m-6-10 12 6M-6 3 6-3" stroke="#7C6787" strokeWidth="2" strokeLinecap="round" />
        </g>
      </>}
      {/* The face is on the side, under the front flaps: the left one swings over the eyes on its way open. */}
      <PipFace />
      <ParcelFlap flap={PARCEL.flaps.frontRight} tone="#D1AE85" hidden />
      <ParcelFlap flap={PARCEL.flaps.frontLeft} tone="#DDBD96" />
      <g className="parcel-illustration__tape"><path d={PARCEL.tape} fill="#EBDDCA" /><path d={PARCEL.seam} stroke="#AF9474" strokeOpacity=".6" strokeWidth="1" strokeDasharray="3 3" /></g>
      {/* The ribbon runs over the lid and down both sides, between Pip's eyes. */}
      {ribbon && <g className="parcel-illustration__ribbon">
        <path d="M102.5 118.5 197.5 166M197.5 118.5 102.5 166M102.5 166v87M197.5 166v87" stroke="#A286B5" strokeWidth="9" />
        <path d="M102.5 118.5 197.5 166M197.5 118.5 102.5 166" stroke="#CDB8DB" strokeWidth="2" opacity=".7" />
        <path d="M150 142c-14-16-34-12-26 0 5 7 20 4 26 0Zm0 0c14-16 34-12 26 0-5 7-20 4-26 0Z" fill="#B99BCB" stroke="#7C6787" strokeWidth="1" strokeOpacity=".5" />
        <path d="M150 142c-6 10-12 18-20 22M150 142c6 10 13 17 22 20" stroke="#A286B5" strokeWidth="5" strokeLinecap="round" />
        <ellipse cx="150" cy="142" rx="6" ry="4.5" fill="#8E6FA3" />
      </g>}
      <g className="parcel-illustration__glints">
        {PARCEL.glints.map(({ x, y, size, color }, index) => <g key={index} transform={`translate(${x} ${y})`}>
          <g className="parcel-illustration__sparkle" style={{
            '--sparkle-x': `${(150 - x) * .55}px`, '--sparkle-y': `${(140 - y) * .7}px`,
            '--sparkle-delay': `${index % 3 * .055}s`,
          } as CSSProperties}>
            <path d={PARCEL.glint} transform={`scale(${size / 2})`} fill={color} />
          </g>
        </g>)}
      </g>
    </g>
  </svg>;
}

/**
 * Pip at sticker size, closed, with a face large enough to read. `look` turns his eyes; with `blinks` he blinks as on
 * the arrival screen, unless the reader asks for less motion.
 */
export function SmallPip({ look, blinks = false }: { look?: PaperPoint; blinks?: boolean } = {}) {
  return <svg className={blinks ? 'small-pip parcel-illustration' : 'small-pip'} viewBox="48 88 204 196" fill="none" aria-hidden="true">
    <path d={PARCEL.inside} fill="#806345" />
    <polygon points={flapPoints(PARCEL.flaps.backRight, false)} fill="#D8B997" />
    <path d={PARCEL.left} fill="#C9A47B" />
    <path d={PARCEL.right} fill="#B78F66" />
    <path d={PARCEL.hairlines} stroke="#987450" strokeOpacity=".25" strokeWidth=".8" />
    <path d={PARCEL.edges} stroke="#FFF2CF" strokeOpacity=".4" strokeWidth="1" />
    <polygon points={flapPoints(PARCEL.flaps.frontLeft, false)} fill="#DDBD96" />
    <PipFace k={1.3} look={look} />
    <path d={PARCEL.tape} fill="#EBDDCA" />
  </svg>;
}
