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

// A shop's or a carrier's notice of a return the reader sends back, with made-up shops and numbers.
const RETURNED: Record<string, [string, string][]> = {
  en: [
    ['Return received', 'Hi, we’ve received your return for order 0000. Your refund is on its way.'],
    ['Your return is on its way', 'Your return parcel has been dropped off at Example Shop. Tracking number 0000000000'],
    ['Here’s your return label', 'Here’s your return label for order 0000. Print it and drop the parcel off.'],
    ['Example Shop', 'Thank you for your return. Tracking 0000000000'],
  ],
  fr: [
    ['Retour enregistré', 'Votre retour a bien été enregistré. Numéro de suivi 0000000000'],
    ['Ton colis retour', 'Ton colis retour est en route vers Example Shop.'],
    ['Example Shop', 'Nous avons bien reçu votre retour. Le remboursement suit.'],
  ],
  de: [
    ['Retoure angemeldet', 'Deine Retoure ist bei uns eingegangen. Sendungsnummer 0000000000'],
    ['Deine Rücksendung', 'Wir haben deine Rücksendung erhalten und prüfen sie.'],
    ['Example Shop', 'Danke für Ihre Rücksendung. Hier ist Ihr Retourenetikett.'],
  ],
  es: [
    ['Devolución registrada', 'Tu devolución ha sido registrada. Número de seguimiento 0000000000'],
    ['Example Shop', 'Hemos recibido tu devolución.'],
  ],
  it: [
    ['Reso registrato', 'Il tuo reso è stato registrato. Numero di tracciamento 0000000000'],
    ['Example Shop', 'Abbiamo ricevuto il tuo reso.'],
  ],
  pt: [
    ['Devolução registada', 'A tua devolução foi registada. Número de seguimento 0000000000'],
    ['Example Shop', 'Recebemos a sua devolução.'],
  ],
  pl: [
    ['Zwrot przyjęty', 'Twój zwrot został zarejestrowany. Numer przesyłki 0000000000'],
    ['Example Shop', 'Dziękujemy za zwrot. Paczka zwrotna została nadana.'],
  ],
  ru: [['Возврат оформлен', 'Ваш возврат оформлен. Трек-номер 0000000000']],
  zh: [['退货已寄出', '您的退货已寄出，运单号 0000000000']],
  nl: [['Retour ontvangen', 'We hebben je retourzending in goede orde ontvangen.']],
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

// A buyer's emails that speak of returns: a shop's rules, a parcel going back to its sender, a refund, feedback.
const RETURN_RULES: [string, string][] = [
  ['Your order has shipped', 'Track your order. Free returns. Your order 0000 is on its way.'],
  ['Dispatched', 'You can return a product within 30 days. Use our online returns centre.'],
  ['Shipped', 'Not right? Use the enclosed label for your return parcel. Once your return is received, we refund you.'],
  ['Christmas delivery dates and extended returns', 'Returns on November and December orders run until January.'],
  ['Your parcel could not be delivered', 'Your parcel is being returned to the sender.'],
  ['Ta commande est expédiée', 'Vous pouvez demander le retour du produit jusqu’à 30 jours après réception. Droit de retour.'],
  ['Ton avis compte', 'Merci pour votre retour ! Votre retour a été pris en compte.'],
  ['Colis en retour', 'Votre colis est en retour vers l’expéditeur.'],
  ['Versandbestätigung', 'Wenn du etwas zurücksenden möchtest, fordere bitte eine Rücksendung an. Kostenlose Rücksendung.'],
  ['Zustellung nicht möglich', 'Rücksendung an den Absender eingeleitet.'],
  ['Pedido enviado', 'Devoluciones gratuitas. Tu envío está en devolución al remitente.'],
  ['Ordine spedito', 'Reso gratuito entro 30 giorni. Reso al mittente.'],
  ['Encomenda enviada', 'Devoluções grátis. Devolução ao remetente.'],
  ['Zwrot pieniędzy', 'Otrzymaliśmy zwrot pieniędzy. Darmowy zwrot. Zwrot do nadawcy.'],
  ['Заказ отправлен', 'Бесплатный возврат. Мы получили возврат денег. Возврат отправителю.'],
  ['订单已发货', '7天无理由退货。退货政策。'],
  ['Je pakket komt eraan', 'Gratis retourneren. Je pakket gaat retour afzender.'],
];

describe('sentByReader', () => {
  for (const [language, emails] of Object.entries(SENT)) {
    it(`reads a seller's notice in ${language}`, () => {
      for (const [subject, text] of emails) expect(sentByReader(subject, text), `${subject} / ${text}`).toBe(true);
    });
  }

  for (const [language, emails] of Object.entries(RETURNED)) {
    it(`reads a return the reader sends back in ${language}`, () => {
      for (const [subject, text] of emails) expect(sentByReader(subject, text), `${subject} / ${text}`).toBe(true);
    });
  }

  it("leaves a buyer's email alone", () => {
    for (const [subject, text] of RECEIVED) expect(sentByReader(subject, text), `${subject} / ${text}`).toBe(false);
  });

  it("leaves a buyer's email alone when it speaks of returns", () => {
    for (const [subject, text] of RETURN_RULES) expect(sentByReader(subject, text), `${subject} / ${text}`).toBe(false);
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
