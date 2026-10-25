import { describe, expect, it } from 'vitest';
import en from '../../shared/locales/en.json';
import it_ from '../../shared/locales/it.json';
import { importChargesLine } from './importCharges';
import { translateMessage, type MessageKey, type Messages } from './messages';
import type { ImportCharges } from '../types';

const english = (key: MessageKey, variables?: Record<string, string | number>) => translateMessage('en', key, variables, en as Messages);
const italian = (key: MessageKey, variables?: Record<string, string | number>) => translateMessage('it', key, variables, it_ as Messages);
const listed: ImportCharges = { currency: 'EUR', customs: 23.41, carrier_fee: 11, carrier_fee_online: 8.09 };

describe('the import charges line', () => {
  it.each([
    ['unpaid, with a lower online price', listed, false, { text: '€34.41 (€31.50 if paid online)', payableOnline: true }],
    ['the online window closed', { ...listed, status_code: 'FINESTRA_PAGAMENTO_CHIUSA' }, false, { text: '€34.41', payableOnline: false }],
    ['no online price', { ...listed, carrier_fee_online: undefined }, false, { text: '€34.41', payableOnline: false }],
    ['an online price that is no lower', { ...listed, carrier_fee_online: 11 }, false, { text: '€34.41', payableOnline: false }],
    ['delivered unpaid: the online price is gone', listed, true, { text: '€34.41', payableOnline: false }],
    ['paid, with both prices', { ...listed, paid: true, status_code: 'PAGATO' }, false, { text: 'Paid (€34.41 on delivery, €31.50 online)', payableOnline: false }],
    ['paid and delivered, with both prices', { ...listed, paid: true }, true, { text: 'Paid (€34.41 on delivery, €31.50 online)', payableOnline: false }],
    ['paid, with one price', { currency: 'EUR', customs: 23.41, paid: true }, false, { text: 'Paid (€23.41)', payableOnline: false }],
    ['paid, without an amount', { currency: 'EUR', paid: true }, false, { text: 'Paid', payableOnline: false }],
    ['customs only', { currency: 'EUR', customs: 23.41 }, false, { text: '€23.41', payableOnline: false }],
  ] as const)('%s', (_, charges, delivered, line) => {
    expect(importChargesLine(charges, delivered, english, 'en-CH')).toEqual(line);
  });

  it('is none without an amount or a payment', () => {
    expect(importChargesLine(undefined, false, english, 'en-CH')).toBeNull();
    expect(importChargesLine({ currency: 'EUR', status_code: 'IN_ATTESA' }, false, english, 'en-CH')).toBeNull();
    expect(importChargesLine({ currency: 'EUR', paid: false, carrier_fee_online: 8.09 }, false, english, 'en-CH')).toBeNull();
  });

  it('writes the amounts as the reader\'s language does', () => {
    expect(importChargesLine(listed, false, italian, 'it-CH')?.text).toBe('€\u00a034.41 (€\u00a031.50 pagando online)');
    expect(importChargesLine({ ...listed, paid: true }, false, italian, 'it-CH')?.text).toBe('Pagati (€\u00a034.41 alla consegna, €\u00a031.50 online)');
  });
});
