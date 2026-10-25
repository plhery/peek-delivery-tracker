import type { ImportCharges } from '../types';
import type { Translate } from './messages';

/** Poste Italiane's code for a closed online payment window: what is left is paid on delivery. */
const ONLINE_WINDOW_CLOSED = 'FINESTRA_PAGAMENTO_CHIUSA';

/** The line a parcel's import charges read as, and whether they can still be paid online. */
export interface ImportChargesLine {
  text: string;
  payableOnline: boolean;
}

/**
 * What importing the parcel costs: customs with the carrier's fee on delivery,
 * and its lower online price while it can still be paid that way. Paid charges
 * say so and keep their prices. None when the carrier lists neither an amount
 * nor a payment.
 */
export function importChargesLine(
  charges: ImportCharges | undefined,
  delivered: boolean,
  t: Translate,
  languageTag: string,
): ImportChargesLine | null {
  if (!charges) return null;
  const paid = charges.paid === true;
  const customs = charges.customs ?? 0;
  const amount = charges.customs === undefined && charges.carrier_fee === undefined ? undefined : customs + (charges.carrier_fee ?? 0);
  const lower = amount === undefined || charges.carrier_fee_online === undefined || charges.status_code === ONLINE_WINDOW_CLOSED
    ? undefined : customs + charges.carrier_fee_online;
  // Once delivered, unpaid charges were settled at the door or not at all: the online price no longer applies.
  const online = lower !== undefined && lower < amount! && (paid || !delivered) ? lower : undefined;
  const money = (value: number) => new Intl.NumberFormat(languageTag, { style: 'currency', currency: charges.currency }).format(value);
  if (paid) {
    const text = amount === undefined ? t('detail.importChargesPaid')
      : online === undefined ? t('detail.importChargesPaidAmount', { amount: money(amount) })
        : t('detail.importChargesPaidAmounts', { amount: money(amount), online: money(online) });
    return { text, payableOnline: false };
  }
  if (amount === undefined) return null;
  return online === undefined ? { text: money(amount), payableOnline: false }
    : { text: t('detail.importChargesOnline', { amount: money(amount), online: money(online) }), payableOnline: true };
}
