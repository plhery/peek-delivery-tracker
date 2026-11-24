import { describe, expect, it } from 'vitest';
import { SUPPORTED_LOCALES } from '../../lib/locale';
import { sentByReader, sentName } from './sentByReader';

// Openings as marketplaces and parcel shops write them to a seller, with made-up shops and numbers.
const SENT: Record<string, [string, string][]> = {
  en: [
    ['We’ve got your parcel', 'Thanks for dropping off your Example Market parcel. We’ll send it on to the buyer. Tracking no: AB0000000000'],
    ['Your item is on its way', 'Your item is on its way to your buyer. Tracking number 0000000000'],
    ['Drop-off receipt', 'Thank you for dropping it off at Example Shop. Parcel 0000000000'],
    ['You sold an item!', 'You’ve sold an item on Example Market. Print the label for your buyer.'],
    ['Parcel sent', 'You have sent a parcel with Example Post. Tracking 0000000000'],
  ],
  fr: [
    ['Ton colis est en route', 'Ton colis est en route vers l’acheteur. Numéro de suivi 0000000000'],
    ['Dépôt confirmé', 'Merci d’avoir déposé ton colis au point relais. Il sera livré à ton acheteur.'],
    ['Article vendu', 'Vous avez vendu un article sur Example Market.'],
  ],
  de: [
    ['Dein Paket ist unterwegs', 'Dein Paket ist auf dem Weg zum Käufer. Sendungsnummer 0000000000'],
    ['Abgabe bestätigt', 'Sie haben Ihr Paket in der Filiale abgegeben. Sendungsnummer 0000000000'],
    ['Versand', 'Danke für die Abgabe deines Pakets bei Example Shop.'],
    ['Verkauft!', 'Du hast etwas verkauft. Schicke es an deine Käuferin.'],
  ],
  es: [
    ['Tu paquete va de camino', 'Tu paquete va de camino al comprador. Número de seguimiento 0000000000'],
    ['Paquete depositado', 'Has depositado tu paquete en el punto de recogida.'],
  ],
  it: [
    ['Il tuo pacco è in viaggio', 'Il tuo pacco è in viaggio verso l’acquirente.'],
    ['Spedizione', 'Hai spedito il pacco al tuo acquirente con Example Post.'],
  ],
  pt: [
    ['A tua encomenda está a caminho', 'A tua encomenda está a caminho do comprador.'],
    ['Venda concluída', 'Você vendeu um artigo. Envie para o seu comprador.'],
  ],
  pl: [
    ['Paczka w drodze', 'Twoja paczka jest w drodze do kupującego. Numer przesyłki 0000000000'],
    ['Nadanie', 'Nadałeś paczkę w punkcie Example.'],
  ],
  ru: [
    ['Посылка в пути', 'Ваша посылка в пути к покупателю. Трек-номер 0000000000'],
    ['Отправление', 'Вы отправили посылку через Example Post.'],
  ],
  zh: [['包裹已寄出', '你的包裹已寄给买家，运单号 0000000000']],
  nl: [['Je pakket is onderweg', 'Je pakket is onderweg naar de koper.']],
};

// A buyer's emails, which speak of the seller, of buyer protection or of a parcel left for them to collect.
const RECEIVED: [string, string][] = [
  ['Your order has shipped', 'The seller has shipped your order. Sold by Example Shop. Buyer Protection applies.'],
  ['Your parcel is ready to collect', 'Your parcel has been dropped off at the Example ParcelShop for you to collect.'],
  ['Delivered', 'Your parcel was dropped off in your safe place. Thank you for shopping with us.'],
  ['Ton colis est arrivé', 'Ton colis a été déposé au point relais. Protection acheteurs incluse.'],
  ['Dein Paket kommt', 'Der Verkäufer hat dein Paket verschickt. Käuferschutz inklusive.'],
  ['Tu pedido está en camino', 'Protección al comprador: si no llega, te devolvemos el dinero.'],
  ['O seu pedido foi enviado', 'Proteção ao Comprador: reembolso se não chegar.'],
  ['Il tuo ordine è stato spedito', 'Protezione dell’acquirente inclusa.'],
  ['Twoja paczka jest w drodze', 'Sprzedający wysłał paczkę. Ochrona Kupującego.'],
  ['Ваш заказ отправлен', 'Продавец отправил ваш заказ. Защита покупателя.'],
  ['您的包裹已发货', '卖家已发货。买家保障。'],
  ['Je pakket is onderweg', 'De verkoper heeft je pakket verzonden. Kopersbescherming.'],
];

describe('sentByReader', () => {
  for (const [language, emails] of Object.entries(SENT)) {
    it(`reads a seller's notice in ${language}`, () => {
      for (const [subject, text] of emails) expect(sentByReader(subject, text), `${subject} / ${text}`).toBe(true);
    });
  }

  it("leaves a buyer's email alone", () => {
    for (const [subject, text] of RECEIVED) expect(sentByReader(subject, text), `${subject} / ${text}`).toBe(false);
  });

  it('reads the opening only, not the rules in the footer', () => {
    const footer = 'Selling too? Ship to your buyer with Example Post.';
    expect(sentByReader('Your order has shipped', `${'Your parcel is on its way. '.repeat(100)}${footer}`)).toBe(false);
    expect(sentByReader('Your order has shipped', `Your parcel is on its way. ${footer}`)).toBe(true);
  });

  it('reads whole words only', () => {
    expect(sentByReader('Info', 'Yoursent receipt: thebuyers club')).toBe(false);
  });
});

describe('sentName', () => {
  it('puts the words before the usual name, in the reader’s language', () => {
    expect(sentName('en', 'Example Market')).toBe('(you sent) Example Market');
    expect(sentName('de', 'Example Market')).toBe('(von dir verschickt) Example Market');
    expect(sentName('zh', 'Example Market')).toBe('（你寄出的）Example Market');
  });

  it('says it alone without a name', () => {
    expect(sentName('fr', '  ')).toBe('(envoyé par toi)');
    expect(sentName('zh', '')).toBe('（你寄出的）');
  });

  it('keeps every name within 80 characters, cutting the usual name', () => {
    for (const locale of SUPPORTED_LOCALES) {
      const name = sentName(locale, 'Ä'.repeat(100));
      expect([...name].length).toBeLessThanOrEqual(80);
      expect(name).toContain('Ä');
    }
    expect(sentName('en', '🙂'.repeat(100))).toBe(`(you sent) ${'🙂'.repeat(69)}`);
  });
});
