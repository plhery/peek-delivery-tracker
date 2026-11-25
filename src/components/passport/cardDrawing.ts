import passportData from '../../../shared/passport.json';
import mark from '../../brand/mark.json';
import { countryInk, STAMP_GLYPHS, type Continent, type StampTone, type YearInParcels } from '../../lib/passport';

/**
 * "Your <year> in parcels" as a picture to share: one page of paper on the passport's yellow, light only, drawn the
 * same on the web, the iPhone and Android. It carries aggregates alone: counts, country stamps and earned seals.
 */
export const CARD_WIDTH = 1080;
export const CARD_HEIGHT = 1350;

const INK = '#20251e';
const SOFT = '#657060';
const PAPER = '#fff9e8';
const YELLOW = '#f3cf48';
const RULE = '#e6dcc0';
/** Each tone's ink and paper in the light theme. */
export const TONES: Record<StampTone, { ink: string; paper: string }> = {
  green: { ink: '#24613e', paper: '#dcf0d7' },
  blue: { ink: '#22588a', paper: '#daeafe' },
  peach: { ink: '#963e19', paper: '#ffddc7' },
  lilac: { ink: '#654299', paper: '#e9deff' },
  ochre: { ink: '#745300', paper: '#ffebab' },
  rose: { ink: '#9a3d5c', paper: '#fadde7' },
};

export interface CardWords {
  inParcels: string;
  /** "13 delivered · 5 countries", or "13 delivered" alone with no country. */
  lineOne: string;
  /** "Fastest 18h · peektracker.com", or the address alone. */
  lineTwo: string;
  /** The one country's name, under its stamp when it is the only one. */
  onlyCountry: string | null;
  /** A country stamp's day: "12 AUG 2026". */
  inkDay: (at: number) => string;
}

type Box = { x: number; y: number; w: number; h: number };

/** Where each country stamp goes, by how many there are (at most six). */
export function countryLayout(count: number, top: number, bottom: number): Box[] {
  const rows = ({ 1: [1], 2: [2], 3: [3], 4: [2, 2], 5: [3, 2], 6: [3, 3] } as Record<number, number[]>)[count] ?? [];
  const [w, h, gap, rowGap] = count === 1 ? [420, 312, 0, 0] : count === 2 ? [340, 252, 80, 0] : count === 4 ? [300, 223, 60, 60] : [248, 184, 48, 40];
  const blockHeight = rows.length * h + (rows.length - 1) * rowGap + (count === 1 ? 72 : 0);
  let y = top + (bottom - top - blockHeight) / 2;
  const boxes: Box[] = [];
  for (const inRow of rows) {
    const rowWidth = inRow * w + (inRow - 1) * gap;
    let x = CARD_WIDTH / 2 - rowWidth / 2;
    for (let index = 0; index < inRow; index++) { boxes.push({ x, y, w, h }); x += w + gap; }
    y += h + rowGap;
  }
  return boxes;
}

function withLetterSpacing(context: CanvasRenderingContext2D, spacing: number, draw: () => void) {
  const canvas = context as CanvasRenderingContext2D & { letterSpacing?: string };
  const before = canvas.letterSpacing;
  if (before !== undefined) canvas.letterSpacing = `${spacing}px`;
  draw();
  if (before !== undefined) canvas.letterSpacing = before;
}

/** A country's ink stamp: its continent's outline, an inner line, its number, its code and its first day. */
export function drawInkStamp(context: CanvasRenderingContext2D, box: Box, ink: { code: string; number: number; day: string; continent: Continent; tilt: number; color: string }, font: string) {
  const scale = box.w / 124;
  const layout = passportData.layout[ink.continent];
  const outline = new Path2D(passportData.outlines[ink.continent]);
  context.save();
  context.translate(box.x + box.w / 2, box.y + box.h / 2);
  context.rotate((ink.tilt * Math.PI) / 180);
  context.scale(scale, scale);
  context.translate(-62, -46);
  context.strokeStyle = ink.color;
  context.fillStyle = ink.color;
  context.lineJoin = 'round';
  context.lineWidth = 1.7;
  context.stroke(outline);
  context.save();
  context.globalAlpha = 0.55;
  context.translate(62, 46);
  context.scale(layout.inner, layout.inner);
  context.translate(-62, -46);
  context.lineWidth = 0.8 / layout.inner;
  context.stroke(outline);
  context.restore();
  context.textAlign = 'center';
  context.font = `600 ${layout.small}px ${font}`;
  withLetterSpacing(context, 1.3, () => context.fillText(`Nº ${String(ink.number).padStart(2, '0')}`, 62, layout.number));
  context.font = `700 ${layout.codeSize}px ${font}`;
  withLetterSpacing(context, 2, () => context.fillText(ink.code, 63, layout.code));
  context.font = `600 ${layout.small}px ${font}`;
  withLetterSpacing(context, layout.dateSpacing, () => context.fillText(ink.day, 62, layout.date));
  context.restore();
}

/** A paper seal with its perforated edge, frame, glyph or numeral, and postmark. */
export function drawSeal(context: CanvasRenderingContext2D, box: Box, seal: { glyph?: string; numeral?: string; ink: string; paper: string; tilt?: number }, ground: string, font: string) {
  const unit = box.w / 54;
  context.save();
  context.translate(box.x + box.w / 2, box.y + box.h / 2);
  context.rotate(((seal.tilt ?? 0) * Math.PI) / 180);
  context.translate(-box.w / 2, -box.h / 2);
  context.fillStyle = seal.paper;
  context.fillRect(0, 0, box.w, box.h);
  // The perforations: small bites of the ground along every edge.
  context.fillStyle = ground;
  const bite = 1.05 * unit;
  const step = 3.6 * unit;
  for (let x = step / 2; x < box.w; x += step) for (const y of [0, box.h]) { context.beginPath(); context.arc(x, y, bite, 0, Math.PI * 2); context.fill(); }
  for (let y = step / 2; y < box.h; y += step) for (const x of [0, box.w]) { context.beginPath(); context.arc(x, y, bite, 0, Math.PI * 2); context.fill(); }
  const inset = 6 * unit;
  context.strokeStyle = seal.ink;
  context.globalAlpha = 0.4;
  context.lineWidth = unit;
  context.strokeRect(inset + unit / 2, inset + unit / 2, box.w - 2 * inset - unit, box.h - 2 * inset - unit);
  context.globalAlpha = 1;
  context.fillStyle = seal.ink;
  if (seal.numeral) {
    context.font = `300 ${20 * unit}px ${font}`;
    context.textAlign = 'center';
    context.textBaseline = 'middle';
    context.fillText(seal.numeral, box.w / 2, box.h / 2);
    context.textBaseline = 'alphabetic';
  } else {
    const size = 24 * unit;
    context.save();
    context.translate(box.w / 2 - size / 2, box.h / 2 - size / 2);
    context.scale(unit, unit);
    context.lineWidth = 1.1;
    context.lineCap = 'round';
    context.lineJoin = 'round';
    context.stroke(new Path2D(STAMP_GLYPHS[seal.glyph ?? 'parcel']));
    context.restore();
  }
  // The postmark, over the seal's lower corner.
  const ring = 27 * unit;
  context.save();
  context.globalAlpha = 0.34;
  context.translate(box.w + 11 * unit - ring, box.h + 3 * unit - ring);
  context.scale(ring / 32, ring / 32);
  context.lineWidth = 1.05;
  for (const r of [15, 10.5]) { context.beginPath(); context.arc(16, 16, r, 0, Math.PI * 2); context.stroke(); }
  context.restore();
  context.restore();
}

function drawMark(context: CanvasRenderingContext2D, x: number, y: number, size: number) {
  const scale = size / mark.size;
  context.save();
  context.translate(x, y);
  context.scale(scale, scale);
  context.fillStyle = mark.tile.fill;
  context.beginPath();
  context.roundRect(0, 0, mark.size, mark.size, mark.tile.radius);
  context.fill();
  for (const shape of mark.shapes) {
    context.fillStyle = shape.fill;
    context.beginPath();
    if (shape.tag === 'ellipse') context.ellipse(shape.cx, shape.cy, shape.rx!, shape.ry!, 0, 0, Math.PI * 2);
    else context.arc(shape.cx, shape.cy, shape.r!, 0, Math.PI * 2);
    context.fill();
  }
  context.restore();
}

/** Draws the whole card on a 1080×1350 canvas. */
export function drawYearCard(context: CanvasRenderingContext2D, year: YearInParcels, words: CardWords, font: string) {
  context.fillStyle = YELLOW;
  context.fillRect(0, 0, CARD_WIDTH, CARD_HEIGHT);
  context.fillStyle = PAPER;
  context.beginPath();
  context.roundRect(48, 48, 984, 1254, 56);
  context.fill();

  context.textAlign = 'left';
  context.fillStyle = INK;
  context.font = `650 200px ${font}`;
  withLetterSpacing(context, -7, () => context.fillText(String(year.year), 116, 300));
  context.fillStyle = SOFT;
  context.font = `400 44px ${font}`;
  context.fillText(words.inParcels, 120, 368);

  const seals = year.stamps.length > 7 ? year.stamps.slice(0, 6) : year.stamps;
  const zoneBottom = seals.length ? 900 : 1010;
  if (year.countries.length) {
    const boxes = countryLayout(year.countries.length, 430, zoneBottom);
    year.countries.forEach((country, index) => {
      const { tone, tilt } = countryInk(country.code);
      drawInkStamp(context, boxes[index]!, { code: country.code, number: country.number, day: words.inkDay(country.firstAt), continent: country.continent, tilt, color: TONES[tone].ink }, font);
    });
    if (year.countries.length === 1 && words.onlyCountry) {
      const box = boxes[0]!;
      context.fillStyle = INK;
      context.textAlign = 'center';
      context.font = `600 40px ${font}`;
      context.fillText(words.onlyCountry, CARD_WIDTH / 2, box.y + box.h + 64);
    }
  } else {
    const w = 220;
    const h = 270;
    drawSeal(context, { x: CARD_WIDTH / 2 - w / 2, y: (430 + zoneBottom) / 2 - h / 2, w, h }, { glyph: 'parcel', ink: '#745300', paper: '#ffffff', tilt: -7 }, PAPER, font);
  }

  seals.forEach((stamp, index) => {
    const tone = TONES[stamp.tone];
    drawSeal(context, { x: 120 + index * 96, y: 940, w: 72, h: 90 }, { glyph: stamp.glyph, numeral: stamp.numeral, ink: tone.ink, paper: tone.paper }, PAPER, font);
  });
  if (year.stamps.length > 7) {
    context.fillStyle = SOFT;
    context.textAlign = 'left';
    context.font = `500 34px ${font}`;
    context.fillText(`+${year.stamps.length - 6}`, 120 + 6 * 96, 997);
  }

  context.fillStyle = RULE;
  context.fillRect(120, 1069, 840, 2);
  drawMark(context, 120, 1121, 88);
  context.textAlign = 'left';
  context.fillStyle = INK;
  context.font = `650 44px ${font}`;
  context.fillText(words.lineOne, 232, 1158, 728);
  context.fillStyle = SOFT;
  context.font = `400 34px ${font}`;
  context.fillText(words.lineTwo, 232, 1208, 728);
}
