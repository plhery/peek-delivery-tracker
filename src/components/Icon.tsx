import type { SVGProps } from 'react';
import { carrierBrand } from '../lib/carrierBrand';
import type { CarrierInfo } from '../lib/carriers';
import { TruckArt } from './CarrierMark';
import { PARCEL, ParcelArt } from './Pip';

export { flapPoints, KraftSides, PARCEL, ParcelFlap, PipFace, SmallPip, type PaperFlap, type PaperPoint } from './Pip';

const paths = {
  parcel: 'M3 7l9-5 9 5v10l-9 5-9-5V7Zm0 0 9 5 9-5M12 12v10M7.5 4.5l9 5',
  truck: 'M3 5h11v12H3V5Zm11 5h4l3 4v3h-7M6 20a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm12 0a2 2 0 1 0 0-4 2 2 0 0 0 0 4Z',
  passport: 'M6 3h13v18H6a3 3 0 0 1-3-3V6a3 3 0 0 1 3-3Zm0 0v18M10 10a3 3 0 1 0 6 0 3 3 0 0 0-6 0Zm0 7h6',
  border: 'M20 12a8 8 0 1 1-8-8M4 12h9M12 4c-4 4-4 12 0 16M12 20c2-2 3-4 3-7M15 4h6v6m-7 1 7-7',
  worldMap: 'm3 5 6-2 6 2 6-2v16l-6 2-6-2-6 2V5Zm6-2v16m6-14v16M5 9l2 2m4-2 2 4m4-5 2 2',
  houses: 'm2 10 5-4 5 4v10H3V10m9-5 5-4 5 4v11h-7M6 20v-6h3v6m7-9V8h3v3',
  hourglass: 'M6 3h12M6 21h12M7 3v4l5 5-5 5v4m10-18v4l-5 5 5 5v4M9 18h6',
  parcels: 'm7 5 5-3 5 3v6l-5 3-5-3V5m0 0 5 3 5-3m-5 3v6M2 14l5-3 5 3v6l-5 3-5-3v-6m0 0 5 3 5-3m-5 3v6m5-9 5-3 5 3v6l-5 3-5-3m0-6 5 3 5-3m-5 3v6',
  storefront: 'M3 10h18l-2-7H5l-2 7Zm2 0v11h14V10M9 21v-7h6v7M8 3l-1 7m9-7 1 7',
  gift: 'M3 9h18v4H3V9Zm2 4v8h14v-8M12 9v12m0-12C3 9 5 1 9 4l3 5Zm0 0c9 0 7-8 3-5l-3 5Z',
  globe: 'M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0ZM3 12h18M12 3c5 5 5 13 0 18-5-5-5-13 0-18Z',
  plus: 'M12 5v14M5 12h14',
  close: 'm6 6 12 12M6 18 18 6',
  arrow: 'M6 18 18 6M6 6h12v12',
  chevron: 'm9 5 7 7-7 7',
  detect: 'M12 3.5v3m0 11v3M3.5 12h3m11 0h3M6 6l2 2m8 8 2 2M6 18l2-2m8-8 2-2m-3.8 6a2.2 2.2 0 1 1-4.4 0 2.2 2.2 0 0 1 4.4 0Z',
  back: 'm15 5-7 7 7 7',
  refresh: 'M19 8a7.5 7.5 0 1 0 .2 7.6M19 4v4h-4',
  filter: 'M4 6h16M7 12h10M10 18h4',
  search: 'M17 10a7 7 0 1 1-14 0 7 7 0 0 1 14 0Zm-2 5 6 6',
  friends: 'M10 7a3 3 0 1 1-6 0 3 3 0 0 1 6 0Zm10 1a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM2 21v-3a5 5 0 0 1 10 0v3m2-7a5 5 0 0 1 8 4v3',
  settings: 'M4 7h16M4 17h16M9 4v6m6 4v6',
  account: 'M16 7a4 4 0 1 1-8 0 4 4 0 0 1 8 0ZM4 21v-2a8 8 0 0 1 16 0v2',
  archive: 'M4 5h16v4H4V5Zm2 4v11h12V9M10 13h4',
  check: 'm5 12 4 4L19 6',
  clock: 'M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0ZM12 6v6l4 2',
  express: 'm13 2-9 12h7l-1 8L21 9h-8l1-7',
  stamp: 'M4 3h16v18H4V3Zm3 3h10v12H7V6Zm3 4 2 3 3-5',
  location: 'M19 9c0 6-7 12-7 12S5 15 5 9a7 7 0 0 1 14 0Zm-4 0a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z',
  bell: 'M18 8a6 6 0 0 0-12 0c0 7-3 7-3 9h18c0-2-3-2-3-9M10 21h4',
  lock: 'M5 10h14v11H5V10Zm3 0V7a4 4 0 0 1 8 0v3M12 14v3',
  sun: 'M16 12a4 4 0 1 1-8 0 4 4 0 0 1 8 0ZM12 2v2M12 20v2M2 12h2M20 12h2M5 5l1 1M18 18l1 1M5 19l1-1M18 6l1-1',
  moon: 'M20 15a9 9 0 0 1-11-11A9 9 0 1 0 20 15Z',
  system: 'M3 4h18v13H3V4Zm5 17h8M12 17v4',
  exit: 'M10 3H4v18h6M10 12h11m-5-5 5 5-5 5',
  download: 'M12 3v12m-5-5 5 5 5-5M4 16v5h16v-5',
  trash: 'M3 6h18M9 6V3h6v3M5 6l1 15h12l1-15M10 10v7M14 10v7',
  mail: 'M3 5h18v14H3V5Zm0 1 9 7 9-7',
  copy: 'M9 8h12v13H9V8ZM6 16H3V3h12v2',
  share: 'M12 15V3m-4 4 4-4 4 4M8 9H5v12h14V9h-3',
} as const;

export type IconName = keyof typeof paths;

export function Icon({ name, ...props }: SVGProps<SVGSVGElement> & { name: IconName }) {
  return <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.65" strokeLinecap="round" strokeLinejoin="round" aria-hidden="true" {...props}><path d={paths[name]} /></svg>;
}

export function PostageStamp({ icon = 'truck' }: { icon?: IconName }) {
  return <span className="postage-stamp" aria-hidden="true"><span className="postage-stamp__print"><Icon name={icon} /></span><span className="postage-stamp__cancel" /></span>;
}

/** The label's own measures. A name or a number too long for its line is set narrower to fit. */
const LABEL = { width: 74, height: 48, margin: 6, nameStart: 23, nameSize: 7, numberSize: 5.6, numberSpacing: .3 } as const;

/**
 * The carrier's label on the box's right side: its truck, its name in its own
 * colour, a barcode and the tracking number. The label's paper is light in
 * either appearance, so the name takes the brand's light-mode colour.
 */
function ParcelLabel({ carrier, number }: { carrier: CarrierInfo; number?: string | null }) {
  const { family, name, decal, style } = carrierBrand(carrier);
  const nameRoom = LABEL.width - LABEL.nameStart - 4;
  const numberRoom = LABEL.width - 2 * LABEL.margin;
  const nameWidth = [...name].length * LABEL.nameSize * .6;
  const numberWidth = [...number ?? ''].length * (LABEL.numberSize * .6 + LABEL.numberSpacing);
  return <g className="parcel-illustration__label" data-family={family} transform={`${PARCEL.labelPlane} scale(.97)`} stroke="none" style={style}>
    <rect width={LABEL.width} height={LABEL.height} rx="2.5" fill="#FFFEFA" />
    <g transform="translate(5 5) scale(.5)"><TruckArt decal={decal} /></g>
    <text className="parcel-illustration__label-name" x={LABEL.nameStart} y="13" fill="var(--carrier-brand-light)" fontSize={LABEL.nameSize} fontWeight="800"
      fontStyle={family === 'dhl' ? 'italic' : undefined}
      {...(nameWidth > nameRoom ? { textLength: nameRoom, lengthAdjust: 'spacingAndGlyphs' } : {})}>{name}</text>
    <path d={PARCEL.barcode} stroke="#20251E" strokeWidth="1.05" />
    {number && <text className="parcel-illustration__label-number" x={LABEL.margin} y="43" fill="#20251E" fontSize={LABEL.numberSize} letterSpacing={LABEL.numberSpacing}
      style={{ fontFamily: 'ui-monospace, SFMono-Regular, Menlo, monospace' }}
      {...(numberWidth > numberRoom ? { textLength: numberRoom, lengthAdjust: 'spacingAndGlyphs' } : {})}>{number}</text>}
  </g>;
}

/**
 * Pip, with the carrier's label in place of the arrow and the seal when `label` names one. A page that never shows a
 * label draws ParcelArt, which leaves the carriers' trucks out of its scripts.
 */
export function ParcelIllustration({ label, ...props }: {
  className?: string;
  label?: { carrier: CarrierInfo; number?: string | null };
  ribbon?: boolean;
}) {
  return <ParcelArt {...props} label={label && <ParcelLabel {...label} />} />;
}
