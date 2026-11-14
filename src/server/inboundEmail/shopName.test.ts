import { describe, expect, it } from 'vitest';
import { shopNamedIn } from './shopName';

// Made-up shops only.
describe('the shop a carrier\'s email names', () => {
  it.each([
    ['Livré: Colis de Example Shop', 'Example Shop'],
    ['Colis de EXAMPLE SHOP SA livré par E-Van', 'Example Shop'],
    ['Paket von Example Shop AG durch E-Van zugestellt', 'Example Shop'],
    ['Paket von EXAMPLE SHOP wird heute zugestellt', 'Example Shop'],
    ['Colis de Galeries de Paris est en route', 'Galeries de Paris'],
    ['Ihre Zustellung von Example Shop verzögert sich', 'Example Shop'],
    ['Your Example Shop shipment is on its way', 'Example Shop'],
    ['Ihre Example-Sendung (Auftragsnummer 123)', 'Example'],
    ['Votre envoi de EXAMPLE SHOP (numéro de commande 123)', 'Example Shop'],
    ['Your Parcel From Example Shop Is On Its Way', 'Example Shop'],
    ['Fwd: Paket von Example GmbH & Co. KG ist unterwegs', 'Example'],
    ['Colis de IKEA livré', 'IKEA'],
    ['Paket von ABC STORE ist unterwegs', 'ABC Store'],
  ])('reads "%s"', (subject, shop) => {
    expect(shopNamedIn(subject, subject)).toBe(shop);
  });

  it.each([
    'Ihre Sendung ist unterwegs',
    'Sendung unterwegs – Example Paket',
    'Colis de votre commande',
    'Paket von Ihrem Händler',
    'Your order has shipped',
    'Ihre Sendung ist unterwegs und Ihr Paket kommt morgen',
    'Zustellung von Montag bis Freitag',
    'Neue Informationen zur Sendung 123.45.12345678',
  ])('reads no shop in "%s"', (subject) => {
    expect(shopNamedIn(subject, subject)).toBe('');
  });

  it('reads the shop beside the number or after the sender\'s label in the body', () => {
    expect(shopNamedIn('Zugestellt', 'Zugestellt\nDie Sendungsnummer 4612345678 von Example Shop AG wurde zugestellt.')).toBe('Example Shop');
    expect(shopNamedIn('Livré', 'Livré\nLe bordereau N° 4612345678 de Example Shop a été livré.')).toBe('Example Shop');
    expect(shopNamedIn('In transit', 'Waybill number 4612345678 from Example Shop has left.')).toBe('Example Shop');
    expect(shopNamedIn('Info', 'Sendungsnummer 4612345678 vom Auftraggeber Example Shop S.L. geht heute in die Zustellung')).toBe('Example Shop');
    expect(shopNamedIn('Info', 'The sender has paid.\nAbsender: Example Shop GmbH\nEmpfänger: Alex')).toBe('Example Shop');
    expect(shopNamedIn('Info', 'Rufen Sie 0848123456 von Montag bis Freitag an.')).toBe('');
  });

  it('reads the shop of a parcel the text calls yours', () => {
    expect(shopNamedIn('Ihre Sendung ist unterwegs zu Ihnen', 'Grüezi\nIhre Sendung von Example Shop ist unterwegs zu Ihnen.')).toBe('Example Shop');
    expect(shopNamedIn('Livraison', 'Bonjour,\nVotre colis de Example Shop SA sera livré demain.')).toBe('Example Shop');
    expect(shopNamedIn('On its way', 'Hello\nYour parcel from Example Shop is on its way.')).toBe('Example Shop');
    expect(shopNamedIn('Info', 'Ihre Sendung von heute wurde zugestellt.')).toBe('');
    expect(shopNamedIn('Info', 'Die Zustellung von Paketen erfolgt werktags.')).toBe('');
  });

  it('never takes the carrier\'s own name for a shop', () => {
    expect(shopNamedIn('Your Example Express shipment is on its way', '', ['Example Express'])).toBe('');
    expect(shopNamedIn('Info', 'Absender: Example Express AG', ['Example Express'])).toBe('');
  });
});
