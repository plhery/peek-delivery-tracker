import 'server-only';
import type { CarrierDecal, TruckDecalShape } from '../brand';
import { paint, svgNumber } from '../components/CarrierMark';
import { CARRIER_LIVERIES } from '../generated/liveries';

/**
 * Every livery's shapes. The pages draw each from its file (`app/liveries`), so only the server reads them all: the
 * email's card and the link preview, which draw a truck with nothing to fetch.
 */
export { CARRIER_LIVERIES };

/** The file holding every livery, for a list that shows them all: its name is no livery's. */
export const ALL_LIVERIES = 'all';

/** Whether `name` names a livery. */
export function isLivery(name: string): name is CarrierDecal {
  return Object.hasOwn(CARRIER_LIVERIES, name);
}

/** A livery's shapes as the truck's SVG spells them: paints are the carrier's brand properties, read where it is used. */
export function liveryMarkup(shapes: readonly TruckDecalShape[]): string {
  return shapes.map((shape) => {
    if (shape.type === 'circle') return `<circle cx="${svgNumber(shape.cx)}" cy="${svgNumber(shape.cy)}" r="${svgNumber(shape.r)}" fill="${paint(shape.fill)}"/>`;
    if (shape.type === 'polygon') return `<path d="${shape.d}" fill="${paint(shape.fill)}"/>`;
    return `<path d="${shape.d}" stroke="${paint(shape.stroke)}" stroke-width="${svgNumber(shape.strokeWidth)}"/>`;
  }).join('');
}

const group = (decal: CarrierDecal) => `<g id="livery-${decal}">${liveryMarkup(CARRIER_LIVERIES[decal])}</g>`;
const file = (groups: string) => `<svg xmlns="http://www.w3.org/2000/svg"><defs>${groups}</defs></svg>`;

/** One livery's file: a group a truck's `<use>` draws, with the brand properties of the truck it stands in. */
export function liveryFile(decal: CarrierDecal): string {
  return file(group(decal));
}

let everyLivery: string | undefined;

/** Every livery in one file, each in the group its own file holds. */
export function allLiveriesFile(): string {
  everyLivery ??= file((Object.keys(CARRIER_LIVERIES) as CarrierDecal[]).map(group).join(''));
  return everyLivery;
}
