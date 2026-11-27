import { describe, expect, it } from 'vitest';
import { shopNameOf, shopNamedIn } from './shopName';

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
    ['Colis de EXAMPLE® SHOP livré', 'Example Shop'],
    ['Ihre Die schöne Werkstatt Sendung verzögert sich', 'Die schöne Werkstatt'],
    ['Fwd: Your Haus und Hof Example shipment is on its way', 'Haus und Hof Example'],
    // A first word in small letters is left out, a shop's or not.
    ['Fwd: Ihre examplia Example Store Sendung ist unterwegs', 'Example Store'],
    ['Ihre neue Example Sendung ist unterwegs', 'Example'],
    ['Your upcoming Example Shop delivery', 'Example Shop'],
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
    'Ihre neue Sendung ist unterwegs',
    'Your Example gift shipment is on its way',
    'Ihre Example ist unterwegs Sendung',
    'Ihre examplia Sendung ist unterwegs',
    'Ihre ist Example Sendung',
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
    expect(shopNamedIn('Ihre neue Example Express Sendung', '', ['Example Express'])).toBe('');
    expect(shopNamedIn('Info', 'Absender: Example Express AG', ['Example Express'])).toBe('');
  });
});

describe('a shop\'s own name, as its emails sign them', () => {
  it.each([
    ['EXAMPLE® SHOP CH', 'Example Shop CH'],
    ['Example Shop AG', 'Example Shop'],
    ['Example™ Store', 'Example Store'],
    ['EXAMPLESHOP', 'Exampleshop'],
    ['ABCD', 'ABCD'],
    ['Example Shop', 'Example Shop'],
    ['example-shop.co.uk', 'example-shop.co.uk'],
    ['Shop.example', 'Shop.example'],
    ['AG', 'AG'],
    ['®', '®'],
  ])('reads "%s" as "%s"', (sender, shop) => {
    expect(shopNameOf(sender)).toBe(shop);
  });
});
