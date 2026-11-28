import { CARRIER_TRUCK, LIVERY_VERSION } from '../brand';
import type { CarrierDecal } from '../brand';
import type { CarrierInfo } from '../lib/carriers';
import { carrierBrand } from '../lib/carrierBrand';

/** SVG numbers in the compact spelling the markup uses: `0.6` is written `.6`. */
export function svgNumber(value: number): string {
  return String(value).replace(/^(-?)0\./, '$1.');
}

const n = svgNumber;

/** A paint is a hex literal, or the name of one of the nine brand properties. */
export function paint(value: string): string {
  return value.startsWith('#') ? value : `var(--carrier-${value})`;
}

/**
 * A paint for dark too. It sits in `style`, which wins over the attribute where `light-dark()` works; a browser
 * without it drops the declaration and keeps the attribute's paint.
 */
function night(property: 'fill' | 'stroke', light: string, dark: string) {
  return { [property]: `light-dark(${light}, ${dark})` };
}

/** On a dark card the outline can light up (`litEdge`). */
const EDGE_IN_DARK = night('stroke', 'var(--carrier-edge)', 'var(--carrier-edge-dark, var(--carrier-edge))');

/** What every truck shares, under its livery: the body, the cab and the windshield. */
function TruckBody() {
  const { strokeWidth, body, cab, windshield } = CARRIER_TRUCK;
  return <>
    <rect x={n(body.x)} y={n(body.y)} width={n(body.width)} height={n(body.height)} rx={n(body.rx)} fill={paint(body.fill)} stroke={paint(body.stroke)} strokeWidth={n(strokeWidth)} style={body.stroke === 'edge' ? EDGE_IN_DARK : undefined} />
    <path d={cab.d} fill={paint(cab.fill)} stroke={paint(cab.stroke)} strokeWidth={n(strokeWidth)} style={cab.stroke === 'edge' ? EDGE_IN_DARK : undefined} />
    <path d={windshield.d} fill={paint(windshield.fill)} style={night('fill', paint(windshield.fill), windshield.dark)} />
  </>;
}

/** What every truck shares over its livery: the wheels. */
function TruckWheels() {
  const { wheels } = CARRIER_TRUCK;
  const { tire } = wheels;
  // In dark a tyre is darker than any card, and a faint rim keeps it round.
  const tireStyle = {
    ...night('fill', paint(tire.fill), tire.dark),
    ...night('stroke', 'transparent', `color-mix(in srgb, ${tire.rim} ${tire.rimOpacity * 100}%, transparent)`),
  };
  return wheels.centers.map(([x, y]) => <g key={x}>
    <circle cx={n(x)} cy={n(y)} r={n(tire.r)} fill={paint(tire.fill)} strokeWidth={n(tire.rimWidth)} style={tireStyle} />
    <circle cx={n(x)} cy={n(y)} r={n(wheels.hub.r)} fill={paint(wheels.hub.fill)} />
  </g>);
}

/**
 * Where a livery is drawn from: its own file, or with `all` the file that holds every livery. The address names the
 * liveries' version, so a browser keeps the file for good.
 */
export function liveryAddress(decal: CarrierDecal, all = false): string {
  return `/liveries/${LIVERY_VERSION}/${all ? 'all' : decal}.svg#livery-${decal}`;
}

/**
 * The livery, from its file (`app/liveries`): a page downloads the liveries it shows, not every carrier's in its
 * scripts. A list that shows them all, the carrier picker, draws them from the one file that holds them.
 */
function TruckDecals({ decal, all }: { decal: CarrierDecal; all?: boolean }) {
  return <use href={liveryAddress(decal, all)} />;
}

/** The truck's shapes in its own frame, for any SVG whose ancestors carry the carrier's brand properties. */
export function TruckArt({ decal }: { decal: CarrierDecal }) {
  return <>
    <TruckBody />
    <TruckDecals decal={decal} />
    <TruckWheels />
  </>;
}

/**
 * The parts every truck shares, drawn once for a list that shows many trucks: each of them then uses `${id}-body`
 * and `${id}-wheels`, painted by the brand properties of the row it stands in, and draws only its own livery.
 */
export function TruckDefinitions({ id }: { id: string }) {
  return <svg className="truck-definitions" aria-hidden="true">
    <defs>
      <g id={`${id}-body`}><TruckBody /></g>
      <g id={`${id}-wheels`}><TruckWheels /></g>
    </defs>
  </svg>;
}

function Truck({ decal, shared }: { decal: CarrierDecal; shared?: string }) {
  const { viewBox } = CARRIER_TRUCK;
  return <svg className="carrier-mark__truck" viewBox={`0 0 ${n(viewBox.width)} ${n(viewBox.height)}`} fill="none" aria-hidden="true">
    {shared ? <>
      <use href={`#${shared}-body`} />
      <TruckDecals decal={decal} all />
      <use href={`#${shared}-wheels`} />
    </> : <TruckArt decal={decal} />}
  </svg>;
}

export function CarrierMark({ carrier }: { carrier: CarrierInfo }) {
  const { family, name, decal } = carrierBrand(carrier);
  return <span className="carrier-mark" data-family={family} title={carrier.name} aria-label={carrier.name}>
    <Truck decal={decal} />
    <span className="carrier-mark__name" aria-hidden="true">{name}{family === 'gls' && <span className="carrier-mark__dot">.</span>}</span>
  </span>;
}

/**
 * The carrier's truck alone, in its own livery, beside a name written elsewhere. In a long list, `shared` is the id
 * of the list's `TruckDefinitions`.
 */
export function CarrierTruck({ carrier, shared }: { carrier: CarrierInfo; shared?: string }) {
  const { decal, style } = carrierBrand(carrier);
  return <span className="carrier-truck" style={style} aria-hidden="true"><Truck decal={decal} shared={shared} /></span>;
}
