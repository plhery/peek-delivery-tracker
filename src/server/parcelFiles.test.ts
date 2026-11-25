import sharp from 'sharp';
import { describe, expect, it } from 'vitest';
import { HttpError } from './api';
import { declaredFileType, packageFileName, sniffedFileType, storedFile } from './parcelFiles';

// Synthetic pictures only, drawn here: a flat colour with made-up EXIF, a camera's turn and a made-up place.
function canvas(width: number, height: number) {
  return sharp({ create: { width, height, channels: 3, background: { r: 200, g: 100, b: 50 } } });
}

const madeUpPlace = {
  IFD0: { Copyright: 'Synthetic camera owner' },
  IFD3: { GPSLatitudeRef: 'N', GPSLatitude: '46/1 12/1 0/1', GPSLongitudeRef: 'E', GPSLongitude: '6/1 8/1 0/1' },
};

async function refusal(promise: Promise<unknown>): Promise<HttpError> {
  const error = await promise.then(() => null, (caught: unknown) => caught);
  expect(error).toBeInstanceOf(HttpError);
  return error as HttpError;
}

const PDF = Buffer.from('%PDF-1.4\n% synthetic\n1 0 obj << >> endobj\ntrailer << >>\n%%EOF\n', 'latin1');

describe('a photo added to a parcel\'s note', () => {
  it('is turned upright, kept within 2,560 px and stored without the place it was taken or any other metadata', async () => {
    const taken = await canvas(3_000, 2_000).jpeg().withMetadata({ orientation: 6 }).withExif(madeUpPlace).toBuffer();
    const before = await sharp(taken).metadata();
    expect(before.orientation).toBe(6);
    expect(before.exif?.toString('latin1')).toContain('Synthetic camera owner');

    const stored = await storedFile(taken, 'image/jpeg');
    expect(stored).toMatchObject({ type: 'image/jpeg', width: 1_707, height: 2_560 });
    const after = await sharp(stored.content).metadata();
    expect(after).toMatchObject({ format: 'jpeg', width: 1_707, height: 2_560 });
    expect(after.orientation).toBeUndefined();
    expect(after.exif).toBeUndefined();
    expect(after.xmp).toBeUndefined();
    expect(after.iptc).toBeUndefined();
    expect(stored.content.toString('latin1')).not.toContain('Synthetic camera owner');

    // Its small copy: 384 px, a JPEG for a JPEG.
    const thumbnail = await sharp(stored.thumbnail!).metadata();
    expect(thumbnail).toMatchObject({ format: 'jpeg', width: 256, height: 384 });
    expect(thumbnail.exif).toBeUndefined();
  });

  it('keeps PNG and WebP in their own format, with a WebP small copy, and never enlarges a small one', async () => {
    const png = await storedFile(await canvas(300, 200).png().toBuffer(), 'image/png');
    expect(png).toMatchObject({ type: 'image/png', width: 300, height: 200 });
    expect((await sharp(png.content).metadata()).format).toBe('png');
    expect(await sharp(png.thumbnail!).metadata()).toMatchObject({ format: 'webp', width: 300, height: 200 });

    const webp = await storedFile(await canvas(4_000, 1_000).webp().withExif(madeUpPlace).toBuffer(), 'image/webp');
    expect(webp).toMatchObject({ type: 'image/webp', width: 2_560, height: 640 });
    expect((await sharp(webp.content).metadata()).exif).toBeUndefined();
    expect(await sharp(webp.thumbnail!).metadata()).toMatchObject({ format: 'webp', width: 384, height: 96 });
  });

  it('keeps a PDF as it was sent', async () => {
    await expect(storedFile(PDF, 'application/pdf')).resolves.toEqual({
      type: 'application/pdf', content: PDF, thumbnail: null, width: null, height: null,
    });
  });

  it('refuses with 415 what its first bytes do not show to be its type, or what does not decode', async () => {
    const png = await canvas(10, 10).png().toBuffer();
    expect((await refusal(storedFile(png, 'image/jpeg'))).status).toBe(415);
    expect((await refusal(storedFile(PDF, 'image/png'))).code).toBe('file_type');
    expect((await refusal(storedFile(Buffer.from('<html><script>alert(1)</script>'), 'application/pdf'))).status).toBe(415);
    // A JPEG's signature with nothing after it.
    expect((await refusal(storedFile(Buffer.from([0xff, 0xd8, 0xff, 0xe0, 1, 2, 3]), 'image/jpeg'))).status).toBe(415);
  });

  it('refuses with 413 a photo of too many pixels and a file over 10 MB', async () => {
    const photo = await canvas(200, 200).jpeg().toBuffer();
    const tooMany = await refusal(storedFile(photo, 'image/jpeg', { maxPixels: 10_000 }));
    expect(tooMany).toMatchObject({ status: 413, code: 'file_too_big' });
    const big = Buffer.concat([PDF, Buffer.alloc(10 * 1_048_576)]);
    expect(await refusal(storedFile(big, 'application/pdf'))).toMatchObject({ status: 413, code: 'file_too_big' });
  });
});

describe('a file\'s declared type and name', () => {
  it('takes JPEG, PNG, WebP and PDF, and refuses HEIC, SVG, HTML and a missing type', () => {
    expect(declaredFileType('image/jpeg')).toBe('image/jpeg');
    expect(declaredFileType('IMAGE/JPG; charset=binary')).toBe('image/jpeg');
    expect(declaredFileType('application/pdf')).toBe('application/pdf');
    for (const type of ['image/heic', 'image/svg+xml', 'text/html', 'application/octet-stream', '', null]) {
      expect(() => declaredFileType(type)).toThrow(expect.objectContaining({ status: 415, code: 'file_type' }));
    }
  });

  it('reads the first bytes of the types Peek keeps', () => {
    expect(sniffedFileType(Buffer.from([0xff, 0xd8, 0xff, 0xdb]))).toBe('image/jpeg');
    expect(sniffedFileType(Buffer.from('RIFF\0\0\0\0WEBPVP8 ', 'latin1'))).toBe('image/webp');
    expect(sniffedFileType(Buffer.from('RIFF\0\0\0\0WAVEfmt ', 'latin1'))).toBeNull();
    expect(sniffedFileType(PDF)).toBe('application/pdf');
    expect(sniffedFileType(Buffer.from('GIF89a'))).toBeNull();
  });

  it('keeps the name sent, percent-decoded, without a folder, control or direction marks, ending in its type', () => {
    expect(packageFileName(encodeURIComponent('Reçu — juin.jpg'), 'image/jpeg')).toBe('Reçu — juin.jpg');
    expect(packageFileName(encodeURIComponent('C:\\Users\\someone\\Desktop\\invoice.PDF'), 'application/pdf')).toBe('invoice.PDF');
    expect(packageFileName(encodeURIComponent('../../etc/passwd'), 'application/pdf')).toBe('passwd.pdf');
    expect(packageFileName(encodeURIComponent('receipt\u202Egpj.exe'), 'image/jpeg')).toBe('receipt gpj.jpg');
    expect(packageFileName(encodeURIComponent('photo\n\tline.heic'), 'image/jpeg')).toBe('photo line.jpg');
    expect(packageFileName(encodeURIComponent('scan.png'), 'image/webp')).toBe('scan.webp');
    expect(packageFileName(encodeURIComponent('.jpg'), 'image/jpeg')).toBe('photo.jpg');
    expect(packageFileName(encodeURIComponent('   '), 'application/pdf')).toBe('document.pdf');
    const long = packageFileName(encodeURIComponent(`${'é'.repeat(200)}.jpeg`), 'image/jpeg')!;
    expect([...long]).toHaveLength(120);
    expect(long.endsWith('é.jpeg')).toBe(true);
    // No name, or one that does not decode.
    expect(packageFileName(null, 'image/jpeg')).toBeNull();
    expect(packageFileName('%E0%A4%A', 'image/jpeg')).toBeNull();
  });
});
