import { describe, expect, it } from 'vitest';
import rules from '../../../shared/email-filter.json';
import { shippingSubject } from './shippingSubject';

// Subjects as shops and carriers write them, with made-up shops and numbers.
const SHIPPING = [
  'Your Example Shop order has shipped',
  'A shipment from order #1001 is on the way',
  'Your parcels are on their way: tracking inside',
  'Fwd: Ihre Bestellung wurde versandt',
  'Unterwegs: Paket von Beispiel Versand AG',
  'Ihre Pakete kommen morgen',
  'Paketzustellung heute zwischen 10 und 12 Uhr',
  'Votre commande a été expédiée',
  'EXPEDIEE : votre colis Exemple',
  'Il tuo ordine è stato spedito',
  'Pacco consegnato al punto di ritiro',
  'Tu pedido está en camino',
  'A sua encomenda foi enviada',
  'Je pakketje is onderweg',
  'Twoja przesyłka jest w drodze',
  'Ваша посылка в пути',
  '您的包裹已发货',
  // An order's name alone: the order confirmations it also lets in name no tracking number Peek takes.
  'Votre commande a été remise au transporteur',
  // A shop's notice that names its order alone, in capitals and forwarded by hand (H&M's French one).
  'TR : Ta COMMANDE est en préparation',
  'Fwd: Merci pour ta commande !',
  'Update on your order #1001',
  'Ihre Bestellung bei Beispiel',
  'Status zamówienia 1001',
  'Статус заказа',
  '您的订单已确认',
];

// Newsletters, bills, sign-ups and notices a reader who forwards all their mail sends on.
const ELSEWHERE = [
  'Good news: you saved on your subscription',
  'Our autumn collection is here',
  'Votre facture de novembre est disponible',
  'Bienvenue dans la communauté !',
  'Willkommen bei Example',
  'Vote for the game of the year and win',
  'Nouvelles règles de confidentialité',
  'Your monthly statement',
  'Inscription confirmée',
];

describe('shippingSubject', () => {
  it('finds a shipping word of any language, its case, accents, plural or a longer form aside', () => {
    for (const subject of SHIPPING) expect(shippingSubject(subject), subject).toBe(true);
  });

  it('finds none in a newsletter, a bill or a sign-up', () => {
    for (const subject of ELSEWHERE) expect(shippingSubject(subject), subject).toBe(false);
  });

  it('finds every word of the filter, in each of its languages', () => {
    for (const [language, words] of Object.entries(rules.words)) {
      for (const word of words) expect(shippingSubject(`Re: ${word} 12`), `${language}: ${word}`).toBe(true);
    }
  });
});
