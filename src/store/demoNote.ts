import type { Locale } from '../lib/locale';
import type { ParcelFile } from '../types';

/**
 * The demo's one private note, on the record from Round Midnight Records: made-up order details and a shop address
 * under `.example`, which belongs to no one. It is written in the demo's language.
 */
export const DEMO_NOTE_PARCEL = '1ZDEMO202600000001';

export const DEMO_NOTE: Record<Locale, string> = {
  en: 'Order RM-48213, paid 39 €. Free returns until 24 Dec at round-midnight.example/returns',
  de: 'Bestellung RM-48213, 39 € bezahlt. Kostenlose Rücksendung bis 24. Dez. unter round-midnight.example/returns',
  fr: 'Commande RM-48213, payée 39 €. Retour gratuit jusqu’au 24 déc. sur round-midnight.example/returns',
  it: 'Ordine RM-48213, pagato 39 €. Reso gratuito fino al 24 dic. su round-midnight.example/returns',
  es: 'Pedido RM-48213, pagado 39 €. Devolución gratis hasta el 24 dic. en round-midnight.example/returns',
  pt: 'Encomenda RM-48213, paga 39 €. Devolução grátis até 24 dez. em round-midnight.example/returns',
  'pt-br': 'Pedido RM-48213, pago 39 €. Devolução grátis até 24 dez. em round-midnight.example/returns',
  pl: 'Zamówienie RM-48213, zapłacone 39 €. Darmowy zwrot do 24 gru. na round-midnight.example/returns',
  ru: 'Заказ RM-48213, оплачено 39 €. Бесплатный возврат до 24 дек. на round-midnight.example/returns',
  zh: '订单 RM-48213，已付 39 €。12 月 24 日前可在 round-midnight.example/returns 免费退货',
};

/** The demo's note in another language, when it is the demo's own text; a note someone wrote stays as it is. */
export function demoNoteIn(note: string | undefined, locale: Locale): string | undefined {
  return note && Object.values(DEMO_NOTE).includes(note) ? DEMO_NOTE[locale] : note;
}

/** A cardboard box on a table, drawn for the demo. */
const BOX = `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 400 400">
<rect width="400" height="400" fill="#b9a68d"/><rect y="268" width="400" height="132" fill="#806047"/>
<path d="M0 300h400M0 338h400M0 376h400" stroke="#6c4f39" stroke-width="3"/>
<g stroke="#4b3727" stroke-width="6" stroke-linejoin="round">
<path d="M200 96 330 154 200 212 70 154Z" fill="#e2b47c"/><path d="M70 154 200 212v118L70 272Z" fill="#d09a5b"/>
<path d="M330 154 200 212v118l130-58Z" fill="#bb8447"/></g>
<path d="M134 125 264 183l-14 7L120 132Z" fill="#f3e2c9"/>
<path d="M232 244l62-28v34l-62 28Z" fill="#faf6ee" stroke="#4b3727" stroke-width="4" stroke-linejoin="round"/>
<path d="M244 252l38-17M244 264l26-12" stroke="#8c7a66" stroke-width="4" stroke-linecap="round"/></svg>`;
const BOX_ADDRESS = `data:image/svg+xml,${encodeURIComponent(BOX)}`;

/** A one-page receipt, drawn as a PDF of grey bars. */
function receipt(): string {
  const page = '0.19 0.22 0.18 rg 60 700 150 22 re f 0.78 0.8 0.76 rg 60 650 280 10 re f 60 628 200 10 re f 60 580 280 10 re f'
    + ' 60 558 240 10 re f 60 536 260 10 re f 60 514 180 10 re f 0.19 0.22 0.18 rg 200 450 140 14 re f';
  const objects = [
    '<< /Type /Catalog /Pages 2 0 R >>',
    '<< /Type /Pages /Kids [3 0 R] /Count 1 >>',
    '<< /Type /Page /Parent 2 0 R /MediaBox [0 0 400 800] /Contents 4 0 R >>',
    `<< /Length ${page.length} >>\nstream\n${page}\nendstream`,
  ];
  let pdf = '%PDF-1.4\n';
  const offsets: number[] = [];
  objects.forEach((body, index) => {
    offsets.push(pdf.length);
    pdf += `${index + 1} 0 obj\n${body}\nendobj\n`;
  });
  const table = pdf.length;
  pdf += `xref\n0 ${objects.length + 1}\n0000000000 65535 f \n${offsets.map((offset) => `${String(offset).padStart(10, '0')} 00000 n \n`).join('')}`;
  return `${pdf}trailer\n<< /Size ${objects.length + 1} /Root 1 0 R >>\nstartxref\n${table}\n%%EOF\n`;
}

let receiptAddress: string | null = null;
/** The receipt's address: one the browser opens in a tab of its own, which it does not do for a `data:` PDF. */
function receiptUrl(): string {
  if (receiptAddress) return receiptAddress;
  const text = receipt();
  receiptAddress = typeof URL.createObjectURL === 'function'
    ? URL.createObjectURL(new Blob([text], { type: 'application/pdf' }))
    : `data:application/pdf;base64,${btoa(text)}`;
  return receiptAddress;
}

/** The demo note's own files, which come back on every load: their addresses are made in the page. */
const DEMO_FILE_IDS = ['demo-file-receipt', 'demo-file-box'] as const;
export const isDemoFile = (id: string) => (DEMO_FILE_IDS as readonly string[]).includes(id);

export function demoFiles(createdAt: string): ParcelFile[] {
  const far = '9999-12-31T23:59:59.000Z';
  return [
    { id: DEMO_FILE_IDS[0], name: 'Receipt.pdf', contentType: 'application/pdf', size: receipt().length, createdAt, url: receiptUrl(), thumbnailUrl: null, expiresAt: far },
    { id: DEMO_FILE_IDS[1], name: 'Photo of the box.jpg', contentType: 'image/jpeg', size: 182_000, width: 400, height: 400, createdAt, url: BOX_ADDRESS, thumbnailUrl: BOX_ADDRESS, expiresAt: far },
  ];
}

/** The demo's own file with an address made in this page. */
export function freshDemoFile(file: ParcelFile): ParcelFile {
  return demoFiles(file.createdAt).find((own) => own.id === file.id) ?? file;
}
