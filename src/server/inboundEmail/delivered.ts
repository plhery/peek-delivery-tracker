import 'server-only';

/**
 * Whether an email's subject says its parcel was delivered, in the ten languages of the mail filter
 * (shared/email-filter.json). Such an email comes once the parcel is there, too late for following it to help.
 *
 * Only the past counts. "Will be delivered", "wird zugestellt" and "sera livré" speak of a parcel still on its way,
 * "could not be delivered" and "nicht zugestellt" of one that is not there, and "delivered in 2 days" is a promise. A
 * parcel delivered to a pickup point or a locker waits to be collected, so it counts as on its way too, and so does a
 * subject that also says the parcel shipped or the order was confirmed ("Shipped – delivered by DPD"). A subject that
 * says less than that is read as on its way: adding a delivered parcel costs less than missing one. A post's notice
 * may name the delivery as done rather than the parcel as delivered ("Distribution effectuée: colis de …").
 */

interface Wording {
  /** The words that say the parcel arrived. */
  done: string;
  /** Words before them, in the same part of the subject, that make them a future, a promise or a failure. */
  before: string;
  /** Words after them, in the same part of the subject, that do the same. */
  after: string;
  /** Places a parcel is collected from. */
  pickup: string;
  /** Words anywhere in the subject that say the parcel is still on its way, or that the order was only confirmed. */
  transit: string;
}

const WORDINGS: Record<string, Wording> = {
  en: {
    done: String.raw`delivered|delivery\s+(?:completed|successful)`,
    before: String.raw`will|would|shall|should|could|can|cannot|may|might|must|to|be|being|not|never|yet|soon|almost|\p{L}+n['’]t|expected|estimated|scheduled|get|gets|getting|want|wants|choose|how`,
    after: String.raw`in\s+\d+\p{L}*|within|between|before|by\s+\d+\p{L}*|tomorrow|next|free|fast|quickly|(?:on\s+)?(?:mon|tues|wednes|thurs|fri|satur|sun)day`,
    pickup: String.raw`pick[ -]?up|ready\s+to\s+collect|collection|lockers?|parcel\s?shop|access\s+point|service\s+point|post\s+office|depot|hub\s+counter|amazon\s+hub|relay`,
    transit: String.raw`shipped|dispatched|on\s+(?:its|the)\s+way|in\s+transit|out\s+for\s+delivery|order\s+confirm\p{L}*`,
  },
  de: {
    done: String.raw`zugestellt|geliefert|ausgeliefert|zustellung\s+erfolgt`,
    before: String.raw`wird|werden|wirst|würde|soll|sollte|sollen|kann|konnte|könnte|können|nicht|kein|keine|nie|bald|voraussichtlich|morgen|kostenlos|schnell`,
    after: String.raw`werden|in\s+\d+\p{L}*|innerhalb|zwischen|morgen|bis`,
    pickup: String.raw`packstation|paketshop|filiale|postfiliale|abholstation|paketstation|abholung|abholpunkt|paketbox`,
    transit: String.raw`unterwegs|versandt|verschickt|versendet|in\s+zustellung|auf\s+dem\s+weg|bestellbestätigung|bestellung\s+bestätigt`,
  },
  fr: {
    done: String.raw`livrée?s?|distribuée?s?|(?:livraison|distribution)\s+(?:effectu[ée]e|r[ée]ussie)`,
    before: String.raw`sera|seront|serait|va|vont|être|pas|jamais|doit|doivent|devrait|peut|pourra|bientôt|prochainement`,
    after: String.raw`en\s+\d+\p{L}*|sous\s+\d+\p{L}*|demain|entre|d['’]ici|gratuitement|offerte?`,
    pickup: String.raw`relais|relay|point\s+(?:de\s+)?retrait|consigne|locker|bureau\s+de\s+poste|casier|pick-?up|point\s+colis`,
    transit: String.raw`expédiée?s?|en\s+cours\s+de\s+livraison|en\s+cours\s+d['’]acheminement|en\s+route|en\s+chemin|commande\s+confirmée|confirmation\s+de\s+(?:votre\s+)?commande`,
  },
  it: {
    done: String.raw`consegnat[oaie]|recapitat[oaie]|(?:consegna|distribuzione)\s+effettuata`,
    before: String.raw`sarà|saranno|verrà|verranno|viene|vengono|non|essere|può|potrebbe|deve|dovrebbe|presto|domani`,
    after: String.raw`in\s+\d+\p{L}*|entro|tra|fra|domani|gratis|gratuitamente`,
    pickup: String.raw`punto\s+(?:di\s+)?ritiro|fermopoint|locker|ufficio\s+postale|punto\s+poste|pick-?up|edicola|tabaccheria`,
    transit: String.raw`spedit[oaie]|in\s+consegna|in\s+transito|in\s+viaggio|ordine\s+confermato|conferma\s+(?:dell['’]\s*|d['’]\s*)ordine`,
  },
  es: {
    done: String.raw`entregad[oa]s?|entrega\s+(?:realizada|efectuada|completada)`,
    before: String.raw`será|serán|sería|va|van|ser|siendo|no|puede|podrá|debe|debería|pronto|mañana`,
    after: String.raw`en\s+\d+\p{L}*|entre|mañana|gratis|antes`,
    pickup: String.raw`punto\s+(?:de\s+)?recogida|punto\s+pack|locker|oficina|taquilla|consigna|citypaq|pick-?up`,
    transit: String.raw`enviad[oa]s?|en\s+reparto|en\s+camino|en\s+tránsito|pedido\s+confirmado|confirmación\s+(?:de(?:l)?\s+)?pedido`,
  },
  pt: {
    done: String.raw`entregues?|entrega\s+(?:efetuada|realizada|conclu[íi]da)`,
    before: String.raw`será|serão|seria|vai|vão|ser|sendo|não|pode|poderá|deve|deverá|breve|amanhã`,
    after: String.raw`em\s+\d+\p{L}*|entre|amanhã|grátis|até`,
    pickup: String.raw`ponto\s+(?:de\s+)?(?:recolha|retirada|pickup)|cacifo|locker|loja|ag[eê]ncia|payshop|pick-?up`,
    transit: String.raw`enviad[oa]s?|expedid[oa]s?|em\s+trânsito|a\s+caminho|em\s+distribuição|saiu\s+para\s+entrega|pedido\s+confirmado`,
  },
  nl: {
    done: String.raw`bezorgd|geleverd|afgeleverd`,
    before: String.raw`wordt|worden|zal|zullen|zou|kan|kon|konden|moet|niet|geen|nooit|binnenkort|morgen|gratis`,
    after: String.raw`worden|wordt|in\s+\d+\p{L}*|binnen|tussen|morgen`,
    pickup: String.raw`afhaalpunt|servicepunt|service\s?point|pakketpunt|ophaalpunt|postnl-punt|pakketautomaat|locker|parcelshop|pick-?up`,
    transit: String.raw`verzonden|onderweg|verstuurd|bestelling\s+bevestigd|orderbevestiging`,
  },
  pl: {
    done: String.raw`doręczon[aye]|doręczono|dostarczon[aye]|dostarczono`,
    before: String.raw`zostanie|zostaną|będzie|będą|nie|zostać|może|mogła|mógł|ma|mają|wkrótce|powinna|powinien|jutro`,
    after: String.raw`w\s+\d+\p{L}*|w\s+ciągu|jutro|między|do\s+\d+\p{L}*`,
    pickup: String.raw`paczkoma\p{L}*|automat\p{L}*|punkt\p{L}*|odbioru|placówk\p{L}*|pick-?up`,
    transit: String.raw`wysłan[aeyo]|w\s+drodze|nadan[aeyo]|potwierdzenie\s+zamówienia`,
  },
  ru: {
    done: String.raw`доставлен[аоы]?|вручен[аоы]?|вручён`,
    before: String.raw`будет|будут|не|быть|может|могут|скоро|должен|должна|должно|завтра`,
    after: String.raw`за\s+\d+\p{L}*|в\s+течение|завтра|через|до\s+\d+\p{L}*`,
    pickup: String.raw`пункт\p{L}*|постамат\p{L}*|почтомат\p{L}*|пвз|отделени\p{L}*|выдач\p{L}*`,
    transit: String.raw`отправлен[аоы]?|в\s+пути|передан[аоы]?\s+в\s+доставку|подтвержд\p{L}*`,
  },
};

/** Chinese writes no spaces: "已签收" and "派送成功" say it arrived, and nothing before them in their part may say otherwise. */
const CHINESE = {
  done: /已(?:签收|送达|妥投|投递)|(?:签收|妥投|投递|派送|配送)成功|配送完成/u,
  before: /未|待|将|即将|预计|无法|不能|没有/u,
  pickup: /驿站|自提|快递柜|代收点|丰巢|取件|服务站|自取|提货/u,
  transit: /已发货|运输中|派送中|配送中|已揽收/u,
};

/** A word of the list, not part of a longer one. */
const words = (list: string) => new RegExp(String.raw`(?<![\p{L}\p{N}])(?:${list})(?![\p{L}\p{N}])`, 'iu');
const READ = Object.values(WORDINGS).map((wording) => ({
  done: new RegExp(String.raw`(?<![\p{L}\p{N}])(?:${wording.done})(?![\p{L}\p{N}])`, 'giu'),
  before: words(wording.before),
  after: words(wording.after),
  pickup: words(wording.pickup),
  transit: words(wording.transit),
}));

/** A subject's parts: "Delivered: your order" is two, and so is "Shipped! Delivered in 2 days". A time's colon parts nothing. */
const PARTS = /[!?;|•·，。！？：；]|[.:](?!\d)|\s[-–—]\s/u;
/** How much of a subject is read: a subject says what it says in its first words. */
const MOST_SUBJECT_LENGTH = 300;

export function deliveredSubject(subject: string): boolean {
  const text = subject.slice(0, MOST_SUBJECT_LENGTH);
  if (READ.some(({ pickup, transit }) => pickup.test(text) || transit.test(text))) return false;
  if (CHINESE.pickup.test(text) || CHINESE.transit.test(text)) return false;
  return text.split(PARTS).some((part) => {
    const chinese = CHINESE.done.exec(part);
    if (chinese && !CHINESE.before.test(part.slice(0, chinese.index))) return true;
    return READ.some(({ done, before, after }) => [...part.matchAll(done)].some((found) =>
      !before.test(part.slice(0, found.index)) && !after.test(part.slice(found.index + found[0].length))));
  });
}
