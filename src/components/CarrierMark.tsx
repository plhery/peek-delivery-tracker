import { CARRIER_TRUCK } from '../brand';
import type { CarrierDecal, TruckDecalShape } from '../brand';
import type { CarrierInfo } from '../lib/carriers';
import { carrierBrand } from '../lib/carrierBrand';

/** SVG numbers in the compact spelling the markup uses: `0.6` is written `.6`. */
function n(value: number): string {
  return String(value).replace(/^(-?)0\./, '$1.');
}

/** A paint is a hex literal, or the name of one of the nine brand properties. */
function paint(value: string): string {
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

function Decal({ shape }: { shape: TruckDecalShape }) {
  if (shape.type === 'circle') {
    return <circle cx={n(shape.cx)} cy={n(shape.cy)} r={n(shape.r)} fill={paint(shape.fill)} />;
  }
  if (shape.type === 'polygon') return <path d={shape.d} fill={paint(shape.fill)} />;
  return <path d={shape.d} stroke={paint(shape.stroke)} strokeWidth={n(shape.strokeWidth)} />;
}

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

/** The livery: decals have a fixed paint order, and concentric layers can share coordinates. */
function TruckDecals({ decal }: { decal: CarrierDecal }) {
  return CARRIER_TRUCK.decals[decal].map((shape, index) => <Decal key={`${decal}-${index}`} shape={shape} />);
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
      <TruckDecals decal={decal} />
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
