// @vitest-environment jsdom
// The files are read back as a browser parses them, with DOMParser.
import { describe, expect, it } from 'vitest';
import { GET } from '../../app/liveries/[version]/[file]/route';
import { config } from '../../proxy';
import { LIVERY_VERSION, type CarrierDecal } from '../brand';
import { ALL_LIVERIES, CARRIER_LIVERIES, isLivery, liveryFile } from './liveries';

const ask = (version: string, file: string) =>
  GET(new Request(`https://peek.test/liveries/${version}/${file}`), { params: Promise.resolve({ version, file }) });

const parsed = (svg: string) => new DOMParser().parseFromString(svg, 'image/svg+xml');

describe('livery files', () => {
  it("sends a livery's file for good under the current version", async () => {
    const response = await ask(LIVERY_VERSION, 'dhl.svg');
    expect(response.status).toBe(200);
    expect(response.headers.get('Content-Type')).toBe('image/svg+xml; charset=utf-8');
    expect(response.headers.get('Cache-Control')).toBe('public, max-age=31536000, immutable');
    const group = parsed(await response.text()).getElementById('livery-dhl');
    expect(group?.children).toHaveLength(CARRIER_LIVERIES.dhl.length);
  });

  it("gives an older build's page today's drawing, kept an hour", async () => {
    const response = await ask('0123456789ab', 'dhl.svg');
    expect(response.status).toBe(200);
    expect(response.headers.get('Cache-Control')).toBe('public, max-age=3600');
    expect(await response.text()).toBe(liveryFile('dhl'));
  });

  it('holds every livery in one file for a list that shows them all', async () => {
    expect(isLivery(ALL_LIVERIES)).toBe(false);
    const all = parsed(await (await ask(LIVERY_VERSION, 'all.svg')).text());
    expect(all.querySelector('parsererror')).toBeNull();
    expect([...all.querySelectorAll('defs > g')].map((group) => group.id))
      .toEqual(Object.keys(CARRIER_LIVERIES).map((decal) => `livery-${decal}`));
  });

  it('writes every livery as well-formed SVG', () => {
    for (const decal of Object.keys(CARRIER_LIVERIES) as CarrierDecal[]) {
      const svg = parsed(liveryFile(decal));
      expect(svg.querySelector('parsererror'), decal).toBeNull();
      expect(svg.getElementById(`livery-${decal}`)?.children.length, decal).toBe(CARRIER_LIVERIES[decal].length);
    }
  });

  it('knows no other file', async () => {
    for (const file of ['unknown-carrier.svg', 'dhl', 'dhl.svg.map', 'constructor.svg', '__proto__.svg', '.svg']) {
      const response = await ask(LIVERY_VERSION, file);
      expect(response.status, file).toBe(404);
      expect(response.headers.get('Cache-Control')).toBe('no-store');
    }
  });

  it('are left alone by the page proxy, as the scripts are', () => {
    const pages = new RegExp(`^${config.matcher[0].source}$`);
    expect(pages.test(`/liveries/${LIVERY_VERSION}/dhl.svg`)).toBe(false);
    expect(pages.test('/carriers/dhl')).toBe(true);
  });
});
