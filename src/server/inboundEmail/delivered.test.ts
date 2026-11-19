import { describe, expect, it } from 'vitest';
import { deliveredSubject } from './delivered';

// Subjects as shops and carriers write them, with made-up shops and numbers.
const DELIVERED: Record<string, string[]> = {
  en: [
    'Delivered: Your Example Shop order #112-0000000-0000000',
    'Your package has been delivered!',
    'Your parcel was delivered',
    'UPS Update: Package Delivered',
    'Order #1234 delivered',
    'Your Example Shop parcel has been successfully delivered to your mailbox',
    'Fwd: Your shipment was delivered today',
    'Delivery completed: your Example Shop order',
  ],
  de: [
    'Ihre Sendung wurde zugestellt',
    'Zugestellt: Ihr Paket von Example Shop',
    'Paket von Example Shop AG durch E-Van zugestellt',
    'Ihr Paket ist zugestellt worden',
    'Deine Bestellung wurde geliefert',
    'WG: Ihre DHL Sendung wurde heute zugestellt',
    'Zustellung erfolgt: Paket von Example Shop AG',
  ],
  fr: [
    'Votre colis a été livré',
    'Livré : Colis de Example Shop',
    'Colis de EXAMPLE SHOP SA livré par E-Van',
    'Votre commande est livrée !',
    "Votre colis vous a bien été livré aujourd'hui",
    'Votre colis a été distribué',
    'TR: Livraison effectuée - Commande 123456',
    'Distribution effectuée: Colis de Example Shop SA',
    'Distribution effectuee : colis de Example Shop SA',
  ],
  it: [
    'Il tuo pacco è stato consegnato',
    'Consegnato: il tuo ordine Example Shop',
    'La tua spedizione è stata consegnata',
    'Ordine 123456 consegnato',
    'Distribuzione effettuata: pacco di Example Shop SA',
  ],
  es: [
    'Tu pedido ha sido entregado',
    'Entregado: tu paquete de Example Shop',
    'Tu envío fue entregado',
    'Paquete entregado',
    'Entrega realizada: tu paquete de Example Shop',
  ],
  pt: [
    'A sua encomenda foi entregue',
    'Encomenda entregue',
    'Seu pedido foi entregue!',
    'Entregue: o seu envio Example Shop',
    'Entrega efetuada: a sua encomenda Example Shop',
  ],
  nl: [
    'Je pakket is bezorgd',
    'Uw zending is afgeleverd',
    'Bezorgd: je bestelling bij Example Shop',
    'Je pakket is vandaag bezorgd',
  ],
  pl: [
    'Twoja przesyłka została doręczona',
    'Paczka dostarczona',
    'Przesyłka nr 123456789 doręczona',
    'Zamówienie zostało dostarczone',
  ],
  ru: [
    'Посылка доставлена',
    'Ваш заказ доставлен',
    'Отправление вручено',
    'Ваша посылка успешно доставлена',
  ],
  zh: [
    '您的包裹已签收',
    '【顺丰速运】您的快件已送达',
    '订单派送成功',
    '包裹已妥投',
  ],
};

const ON_ITS_WAY: Record<string, string[]> = {
  en: [
    'Your package will be delivered today',
    'Out for delivery: your Example Shop order',
    'Your parcel could not be delivered',
    "We couldn't deliver your parcel",
    'Your parcel was not delivered',
    'Your order has shipped and will be delivered Tuesday',
    'Shipped! Delivered in 2-3 days',
    'Your package is being delivered by DPD',
    'Your parcel has been delivered to a pickup point',
    'Your parcel has been delivered to a pick up point',
    'Ready to collect: Your parcel was delivered to the store',
    'Your order is expected to be delivered tomorrow',
    'Undelivered parcel: action required',
    'Your order has shipped – delivered by DPD',
    'Order confirmed! Delivered Thursday',
    'Expected delivered today by 9pm',
    'Your parcel gets delivered today',
    'Choose how you want your parcel delivered',
    'Your parcel was delivered to the Amazon Hub Counter',
  ],
  de: [
    'Ihr Paket wird heute zugestellt',
    'Ihr Paket wird morgen zwischen 10:00 und 12:00 zugestellt',
    'Ihre Sendung konnte nicht zugestellt werden',
    'Ihre Sendung wurde nicht zugestellt',
    'Paket von Example Shop wird heute zugestellt',
    'Ihr Paket ist in Zustellung',
    'Ihr Paket wurde in eine Packstation geliefert',
    'Morgen geliefert: jetzt bestellen',
    'Ihre Bestellung ist unterwegs – zugestellt durch DHL',
    'Zustellung heute: Paket von Example Shop AG',
    'Zustellung erfolgt morgen zwischen 10:00 und 12:00',
  ],
  fr: [
    'Votre colis sera livré demain',
    'Votre colis va être livré aujourd’hui',
    "Votre colis n'a pas pu être livré",
    "Votre colis n'a pas été livré",
    'Votre colis est en cours de livraison',
    'Votre colis vous sera livré entre 10h et 12h',
    'Votre colis a été livré dans votre Point Relais',
    'Expédié ! Livré en 48h',
    'Votre commande sera livrée par DPD',
    'Votre commande de livres a été expédiée',
    "Chronopost vous livre aujourd'hui",
    "La Poste distribue votre colis aujourd'hui",
    "Votre colis est en cours de livraison - livré aujourd'hui",
    'Votre commande expédiée, livrée par Colissimo',
    'Votre colis a été livré au point Mondial Relay',
    'Distribution aujourd’hui: Colis de Example Shop SA',
    'Suivez l’envoi en temps réel: Colis de Example Shop SA',
  ],
  it: [
    'Il tuo pacco sarà consegnato domani',
    'Il tuo pacco verrà consegnato oggi',
    'Il tuo pacco non è stato consegnato',
    'La tua spedizione è in consegna',
    'Il tuo pacco è stato consegnato al punto di ritiro',
    'Distribuzione oggi: pacco di Example Shop SA',
  ],
  es: [
    'Tu pedido será entregado mañana',
    'Tu paquete no ha podido ser entregado',
    'Tu envío está en reparto',
    'Tu paquete no ha sido entregado',
    'Tu paquete ha sido entregado en el punto de recogida',
    'Tu pedido ha sido enviado y será entregado por SEUR',
  ],
  pt: [
    'A sua encomenda será entregue amanhã',
    'A sua encomenda não foi entregue',
    'Seu pedido saiu para entrega',
    'A sua encomenda vai ser entregue hoje',
    'A sua encomenda foi entregue no ponto de recolha',
    'O seu pedido foi enviado e será entregue pelos CTT',
  ],
  nl: [
    'Je pakket wordt vandaag bezorgd',
    'Je pakket wordt morgen tussen 10:00 en 12:00 bezorgd',
    'Je pakket kon niet bezorgd worden',
    'Je pakket is onderweg',
    'Je pakket is bezorgd bij een PostNL-punt',
    'Je bestelling is verzonden en wordt bezorgd door PostNL',
  ],
  pl: [
    'Twoja przesyłka zostanie doręczona jutro',
    'Przesyłka nie została doręczona',
    'Przesyłka będzie dostarczona dzisiaj',
    'Przesyłka w doręczeniu',
    'Paczka dostarczona do Paczkomatu',
    'Paczka dostarczona w Paczkomacie',
  ],
  ru: [
    'Посылка будет доставлена завтра',
    'Посылка не доставлена',
    'Заказ скоро будет доставлен',
    'Посылка в пути',
    'Посылка доставлена в пункт выдачи',
  ],
  zh: [
    '您的包裹未送达',
    '您的包裹即将送达',
    '快递正在派送中',
    '包裹预计明天送达',
    '您的包裹已送达驿站，请及时取件',
    '您的订单已发货',
  ],
};

describe('a subject that says its parcel was delivered', () => {
  it.each(Object.entries(DELIVERED).flatMap(([language, subjects]) => subjects.map((subject) => [language, subject])))(
    'is read so in %s: "%s"',
    (_, subject) => expect(deliveredSubject(subject)).toBe(true),
  );

  it.each(Object.entries(ON_ITS_WAY).flatMap(([language, subjects]) => subjects.map((subject) => [language, subject])))(
    'is not read in a future, a failure or a pickup point in %s: "%s"',
    (_, subject) => expect(deliveredSubject(subject)).toBe(false),
  );

  it('covers the languages of the mail filter', async () => {
    const { default: rules } = await import('../../../shared/email-filter.json');
    expect(Object.keys(DELIVERED).sort()).toEqual(Object.keys(rules.words).sort());
    expect(Object.keys(ON_ITS_WAY).sort()).toEqual(Object.keys(rules.words).sort());
  });

  it('reads a shipping subject as on its way', () => {
    for (const subject of ['Your order has shipped', 'Ihre Bestellung wurde versandt', 'Votre colis est en route', '']) {
      expect(deliveredSubject(subject)).toBe(false);
    }
  });
});
