import { generatedScans } from '../lib/generatedTrack';
import { EVENT_STAGE_ORDER, returnUnderWay, tieOrder } from '../lib/stages';
import 'server-only';

import { connect, constants as http2Constants } from 'node:http2';
import { createPrivateKey, type KeyObject } from 'node:crypto';
import { SignJWT } from 'jose';
import { DateTime, FixedOffsetZone, IANAZone, type Zone } from 'luxon';
import webpush from 'web-push';
import { countryName } from 'universal-parcel-scraper/app';
import { locatePlace } from 'universal-parcel-scraper/places';
import { CARRIER_CAPABILITIES, type ApiParcelAlertPreset } from '../generated/apiContract';
import { dayAndTime } from '../lib/format';
import { catalogLanguage, tagLocale, type Locale } from '../lib/locale';
import { deliveryWallClock, languageTags } from '../lib/messages';
import { ALERT_PRESET_STAGES } from '../lib/notificationPresets';
import { romanisesPlace } from '../lib/scanPlace';
import { capitalized } from '../peek/parcel/summary';
import { FCMAuthError, FcmClient, FCMError, fcmTokenGone } from './fcm';
import { parcelNumberMask } from './hiddenNumbers';
import { servedPlannedSteps } from './plannedSteps';
import { recordAccountPushSent, recordParcelAlertRemoved, recordParcelAlertSent } from './metrics';
import { errorType, logOperationalEvent } from './observability';
import { providerCopies } from './providerCopies';
import { lateScans, relayRepeats } from './relayCopies';
import { repeatedScans } from './scanRepeats';
import { stageNotices } from './stageNotices';
import { messagesFor } from './requestLocale';
import type { SupabaseServiceClient } from './supabase';
import { errorMessage, isRecord, type JsonObject } from './types';
import { WorkerLease } from './workerLease';

/**
 * What a notification says beyond the app's own words for a parcel's stages.
 * Its title is the news; the line under it names the parcel, then says when it
 * arrives or what the step means. Those sentences start in lower case where
 * their language allows, and are capitalised when no name comes before them.
 * Nothing assumes the reader is the recipient: an account also follows the
 * parcels it sends.
 */
const PUSH_COPY: Record<Locale, Record<string, string>> = {
  en: {
    test_title: 'You’ll hear from Peek',
    test_body: 'This device gets a notification for the steps you chose. Change them any time in Settings › Notifications.',
    friend_title: '{{name}} is in',
    friend_body: 'Your passports are side by side now. Have a peek.',
    update: 'Parcel update',
    title_registered: 'Label created',
    title_in_transit: 'On its way',
    title_ready_for_pickup: 'Ready for pickup',
    title_delivered: 'It’s arrived',
    title_failed_attempt: 'Delivery missed',
    title_returned: 'On its way back',
    body_update: 'tap to see what’s new',
    body_pending: 'waiting for the carrier’s first scan',
    body_registered: 'the carrier doesn’t have it yet',
    body_accepted: 'off it goes',
    body_in_transit: 'one step closer',
    body_customs: 'Peek is keeping an eye on it',
    body_exception: 'the carrier flagged a problem. Tap to see what to do.',
    body_out_for_delivery: 'almost there',
    body_ready_for_pickup: 'waiting at the pickup point',
    body_delivered: 'delivered',
    body_failed_attempt: 'the carrier couldn’t deliver it. Tap to see what happens next.',
    body_returned: 'returning to the sender',
    delivered_time: 'delivered at {{time}}',
    delivered_date: 'delivered on {{date}} at {{time}}',
    eta_day: 'arriving {{date}}',
    eta_date: 'arriving {{date}}',
    eta_changed_day: 'now arriving {{date}}',
    eta_changed_date: 'now arriving {{date}}',
    eta_by_day: 'arriving by {{date}}',
    eta_by_date: 'arriving by {{date}}',
    eta_window: 'arriving between {{from}} and {{to}}',
    eta_changed_by_day: 'now arriving by {{date}}',
    eta_changed_by_date: 'now arriving by {{date}}',
    eta_changed_window: 'now arriving between {{from}} and {{to}}',
  },
  de: {
    test_title: 'Ab jetzt hörst du von Peek',
    test_body: 'Dieses Gerät bekommt Mitteilungen für die Schritte, die du gewählt hast. Ändern kannst du sie jederzeit unter Einstellungen › Mitteilungen.',
    friend_title: '{{name}} ist dabei',
    friend_body: 'Eure Reisepässe liegen jetzt nebeneinander. Wirf einen Blick drauf.',
    update: 'Neues vom Paket',
    title_registered: 'Etikett erstellt',
    title_in_transit: 'Unterwegs',
    title_ready_for_pickup: 'Abholbereit',
    title_delivered: 'Angekommen',
    title_failed_attempt: 'Zustellung nicht geklappt',
    title_returned: 'Auf dem Rückweg',
    body_update: 'tippe, um zu sehen, was sich getan hat',
    body_pending: 'wartet auf den ersten Scan des Paketdienstes',
    body_registered: 'der Paketdienst hat es noch nicht',
    body_accepted: 'und los geht’s',
    body_in_transit: 'wieder ein Stück näher',
    body_customs: 'Peek behält es im Auge',
    body_exception: 'der Paketdienst meldet ein Problem. Tippe, um zu sehen, was zu tun ist.',
    body_out_for_delivery: 'fast da',
    body_ready_for_pickup: 'wartet an der Abholstelle',
    body_delivered: 'zugestellt',
    body_failed_attempt: 'der Paketdienst konnte es nicht zustellen. Tippe, um zu sehen, wie es weitergeht.',
    body_returned: 'geht zurück an den Absender',
    delivered_time: 'um {{time}} Uhr zugestellt',
    delivered_date: 'am {{date}} um {{time}} Uhr zugestellt',
    eta_day: 'kommt {{date}}',
    eta_date: 'kommt am {{date}}',
    eta_changed_day: 'neuer Liefertermin: {{date}}',
    eta_changed_date: 'neuer Liefertermin: {{date}}',
    eta_by_day: 'kommt bis {{date}}',
    eta_by_date: 'kommt bis zum {{date}}',
    eta_window: 'kommt zwischen dem {{from}} und dem {{to}}',
    eta_changed_by_day: 'neuer Liefertermin: bis {{date}}',
    eta_changed_by_date: 'neuer Liefertermin: bis zum {{date}}',
    eta_changed_window: 'neuer Liefertermin: zwischen dem {{from}} und dem {{to}}',
  },
  fr: {
    test_title: 'Peek te tient au courant',
    test_body: 'Cet appareil reçoit une notification pour les étapes que tu as choisies. Change-les quand tu veux dans Réglages › Notifications.',
    friend_title: '{{name}} est de la partie',
    friend_body: 'Vos passeports sont maintenant côte à côte. Jette un œil.',
    update: 'Des nouvelles du colis',
    title_registered: 'Étiquette créée',
    title_in_transit: 'En route',
    title_ready_for_pickup: 'Prêt à être retiré',
    title_delivered: 'Bien arrivé',
    title_failed_attempt: 'Livraison manquée',
    title_returned: 'Sur le chemin du retour',
    body_update: 'touche pour voir ce qui a changé',
    body_pending: 'en attente du premier scan du transporteur',
    body_registered: 'le transporteur ne l’a pas encore reçu',
    body_accepted: 'c’est parti',
    body_in_transit: 'il se rapproche',
    body_customs: 'Peek garde un œil dessus',
    body_exception: 'le transporteur signale un problème. Touche pour savoir quoi faire.',
    body_out_for_delivery: 'presque arrivé',
    body_ready_for_pickup: 'il attend au point de retrait',
    body_delivered: 'livré',
    body_failed_attempt: 'le transporteur n’a pas pu le livrer. Touche pour voir la suite.',
    body_returned: 'il repart chez l’expéditeur',
    delivered_time: 'livré à {{time}}',
    delivered_date: 'livré le {{date}} à {{time}}',
    eta_day: 'arrive {{date}}',
    eta_date: 'arrive le {{date}}',
    eta_changed_day: 'arrive désormais {{date}}',
    eta_changed_date: 'arrive désormais le {{date}}',
    eta_by_day: 'arrive d’ici {{date}}',
    eta_by_date: 'arrive d’ici le {{date}}',
    eta_window: 'arrive entre le {{from}} et le {{to}}',
    eta_changed_by_day: 'arrive désormais d’ici {{date}}',
    eta_changed_by_date: 'arrive désormais d’ici le {{date}}',
    eta_changed_window: 'arrive désormais entre le {{from}} et le {{to}}',
  },
  it: {
    test_title: 'Peek ti terrà al corrente',
    test_body: 'Questo dispositivo riceve notifiche per i passaggi che hai scelto. Puoi cambiarli quando vuoi in Impostazioni › Notifiche.',
    friend_title: '{{name}} è nella tua cerchia',
    friend_body: 'I vostri passaporti ora sono fianco a fianco. Dai un’occhiata.',
    update: 'Novità sul pacco',
    title_registered: 'Etichetta creata',
    title_in_transit: 'In viaggio',
    title_ready_for_pickup: 'Pronto per il ritiro',
    title_delivered: 'È arrivato',
    title_failed_attempt: 'Consegna mancata',
    title_returned: 'Sulla via del ritorno',
    body_update: 'tocca per vedere cos’è cambiato',
    body_pending: 'in attesa della prima scansione del corriere',
    body_registered: 'il corriere non ce l’ha ancora',
    body_accepted: 'si parte',
    body_in_transit: 'un passo più vicino',
    body_customs: 'Peek lo tiene d’occhio',
    body_exception: 'il corriere ha segnalato un problema. Tocca per vedere cosa fare.',
    body_out_for_delivery: 'manca poco',
    body_ready_for_pickup: 'in attesa al punto di ritiro',
    body_delivered: 'consegnato',
    body_failed_attempt: 'il corriere non è riuscito a consegnarlo. Tocca per vedere cosa succede ora.',
    body_returned: 'sta tornando al mittente',
    delivered_time: 'consegnato alle {{time}}',
    delivered_date: 'consegnato il giorno {{date}} alle {{time}}',
    eta_day: 'arriva {{date}}',
    eta_date: 'arrivo previsto: {{date}}',
    eta_changed_day: 'ora arriva {{date}}',
    eta_changed_date: 'nuova data prevista: {{date}}',
    eta_by_day: 'arriva entro {{date}}',
    eta_by_date: 'arriva entro {{date}}',
    eta_window: 'arrivo previsto: {{from}} – {{to}}',
    eta_changed_by_day: 'ora arriva entro {{date}}',
    eta_changed_by_date: 'nuova data prevista: entro {{date}}',
    eta_changed_window: 'nuova data prevista: {{from}} – {{to}}',
  },
  es: {
    test_title: 'Peek te mantendrá al tanto',
    test_body: 'Este dispositivo recibe notificaciones de los pasos que has elegido. Cámbialos cuando quieras en Ajustes › Notificaciones.',
    friend_title: '{{name}} se apunta',
    friend_body: 'Vuestros pasaportes ya están uno al lado del otro. Echa un vistazo.',
    update: 'Novedades del paquete',
    title_registered: 'Etiqueta creada',
    title_in_transit: 'En camino',
    title_ready_for_pickup: 'Listo para recoger',
    title_delivered: 'Ha llegado',
    title_failed_attempt: 'Entrega fallida',
    title_returned: 'Camino de vuelta',
    body_update: 'toca para ver qué hay de nuevo',
    body_pending: 'esperando el primer escaneo del transportista',
    body_registered: 'el transportista aún no lo tiene',
    body_accepted: 'en marcha',
    body_in_transit: 'un paso más cerca',
    body_customs: 'Peek no lo pierde de vista',
    body_exception: 'el transportista ha detectado un problema. Toca para ver qué hacer.',
    body_out_for_delivery: 'ya casi está',
    body_ready_for_pickup: 'esperando en el punto de recogida',
    body_delivered: 'entregado',
    body_failed_attempt: 'el transportista no ha podido entregarlo. Toca para ver qué pasa ahora.',
    body_returned: 'se devuelve al remitente',
    delivered_time: 'entregado a las {{time}}',
    delivered_date: 'entregado el {{date}} a las {{time}}',
    eta_day: 'llega {{date}}',
    eta_date: 'llega el {{date}}',
    eta_changed_day: 'ahora llega {{date}}',
    eta_changed_date: 'ahora llega el {{date}}',
    eta_by_day: 'llega para {{date}}',
    eta_by_date: 'llega para el {{date}}',
    eta_window: 'llega entre el {{from}} y el {{to}}',
    eta_changed_by_day: 'ahora llega para {{date}}',
    eta_changed_by_date: 'ahora llega para el {{date}}',
    eta_changed_window: 'ahora llega entre el {{from}} y el {{to}}',
  },
  pt: {
    test_title: 'O Peek vai dando notícias',
    test_body: 'Este dispositivo recebe notificações dos passos que escolheste. Podes alterá-los quando quiseres em Definições › Notificações.',
    friend_title: '{{name}} juntou-se a ti',
    friend_body: 'Os vossos passaportes já estão lado a lado. Dá uma espreitadela.',
    update: 'Atualização da encomenda',
    title_registered: 'Etiqueta criada',
    title_in_transit: 'A caminho',
    title_ready_for_pickup: 'Pronta para levantamento',
    title_delivered: 'Chegou',
    title_failed_attempt: 'Entrega falhada',
    title_returned: 'De regresso',
    body_update: 'toca para ver as novidades',
    body_pending: 'à espera da primeira leitura da transportadora',
    body_registered: 'a transportadora ainda não a tem',
    body_accepted: 'a viagem começou',
    body_in_transit: 'um passo mais perto',
    body_customs: 'o Peek está de olho nela',
    body_exception: 'a transportadora assinalou um problema. Toca para ver o que fazer.',
    body_out_for_delivery: 'quase a chegar',
    body_ready_for_pickup: 'à espera no ponto de levantamento',
    body_delivered: 'entregue',
    body_failed_attempt: 'a transportadora não conseguiu entregá-la. Toca para ver o que se segue.',
    body_returned: 'a ser devolvida ao remetente',
    delivered_time: 'entregue às {{time}}',
    delivered_date: 'entregue a {{date}} às {{time}}',
    eta_day: 'chega {{date}}',
    eta_date: 'chega a {{date}}',
    eta_changed_day: 'afinal chega {{date}}',
    eta_changed_date: 'afinal chega a {{date}}',
    eta_by_day: 'chega até {{date}}',
    eta_by_date: 'chega até {{date}}',
    eta_window: 'chega entre {{from}} e {{to}}',
    eta_changed_by_day: 'afinal chega até {{date}}',
    eta_changed_by_date: 'afinal chega até {{date}}',
    eta_changed_window: 'afinal chega entre {{from}} e {{to}}',
  },
  pl: {
    test_title: 'Peek da Ci znać',
    test_body: 'To urządzenie dostaje powiadomienia o wybranych przez Ciebie krokach. Zmienisz je w każdej chwili tutaj: Ustawienia › Powiadomienia.',
    friend_title: '{{name}} jest już w Twoim kręgu',
    friend_body: 'Wasze paszporty leżą teraz obok siebie. Zerknij.',
    update: 'Nowe wieści o przesyłce',
    title_registered: 'Zarejestrowana przez nadawcę',
    title_in_transit: 'W drodze',
    title_ready_for_pickup: 'Do odbioru',
    title_delivered: 'Dotarła',
    title_failed_attempt: 'Nieudane doręczenie',
    title_returned: 'W drodze powrotnej',
    body_update: 'stuknij, aby zobaczyć szczegóły',
    body_pending: 'czeka na pierwszy skan przewoźnika',
    body_registered: 'przewoźnik jeszcze nie ma przesyłki',
    body_accepted: 'rusza w drogę',
    body_in_transit: 'o krok bliżej',
    body_customs: 'Peek ma wszystko na oku',
    body_exception: 'przewoźnik zgłosił problem. Stuknij, aby zobaczyć, co zrobić.',
    body_out_for_delivery: 'już prawie na miejscu',
    body_ready_for_pickup: 'czeka w punkcie odbioru',
    body_delivered: 'dostarczono',
    body_failed_attempt: 'przewoźnikowi nie udało się doręczyć przesyłki. Stuknij, aby zobaczyć, co dalej.',
    body_returned: 'wraca do nadawcy',
    delivered_time: 'dostarczono o {{time}}',
    delivered_date: 'dostarczono {{date}} o {{time}}',
    eta_day: 'dotrze {{date}}',
    eta_date: 'dotrze {{date}}',
    eta_changed_day: 'nowy termin: {{date}}',
    eta_changed_date: 'nowy termin: {{date}}',
    eta_by_day: 'dotrze najpóźniej {{date}}',
    eta_by_date: 'dotrze najpóźniej {{date}}',
    eta_window: 'dotrze w dniach {{from}} – {{to}}',
    eta_changed_by_day: 'nowy termin: najpóźniej {{date}}',
    eta_changed_by_date: 'nowy termin: najpóźniej {{date}}',
    eta_changed_window: 'nowy termin: {{from}} – {{to}}',
  },
  'pt-br': {
    test_title: 'O Peek vai dar notícias',
    test_body: 'Este dispositivo recebe notificações das etapas que você escolheu. Altere quando quiser em Configurações › Notificações.',
    friend_title: '{{name}} entrou no seu círculo',
    friend_body: 'Agora seus passaportes estão lado a lado. Dê uma espiada.',
    update: 'Atualização da encomenda',
    title_registered: 'Etiqueta criada',
    title_in_transit: 'A caminho',
    title_ready_for_pickup: 'Pronta para retirada',
    title_delivered: 'Chegou',
    title_failed_attempt: 'Entrega não realizada',
    title_returned: 'Em devolução',
    body_update: 'toque para ver as novidades',
    body_pending: 'aguardando a primeira movimentação da transportadora',
    body_registered: 'a transportadora ainda não recebeu a encomenda',
    body_accepted: 'começou a viagem',
    body_in_transit: 'um passo mais perto',
    body_customs: 'o Peek está de olho',
    body_exception: 'a transportadora informou um problema. Toque para ver o que fazer.',
    body_out_for_delivery: 'quase chegando',
    body_ready_for_pickup: 'esperando no ponto de retirada',
    body_delivered: 'entregue',
    body_failed_attempt: 'a transportadora não conseguiu entregar. Toque para ver o que acontece agora.',
    body_returned: 'voltando para o remetente',
    delivered_time: 'entregue às {{time}}',
    delivered_date: 'entregue em {{date}} às {{time}}',
    eta_day: 'chega {{date}}',
    eta_date: 'chega em {{date}}',
    eta_changed_day: 'agora chega {{date}}',
    eta_changed_date: 'agora chega em {{date}}',
    eta_by_day: 'chega até {{date}}',
    eta_by_date: 'chega até {{date}}',
    eta_window: 'chega entre {{from}} e {{to}}',
    eta_changed_by_day: 'agora chega até {{date}}',
    eta_changed_by_date: 'agora chega até {{date}}',
    eta_changed_window: 'agora chega entre {{from}} e {{to}}',
  },
  ru: {
    test_title: 'Peek будет держать вас в курсе',
    test_body: 'Это устройство будет получать уведомления о выбранных вами этапах. Изменить их можно в любой момент: Настройки › Уведомления.',
    friend_title: '{{name}} теперь в вашем круге',
    friend_body: 'Ваши паспорта теперь рядом. Загляните.',
    update: 'Новости о посылке',
    title_registered: 'Этикетка создана',
    title_in_transit: 'Уже в пути',
    title_ready_for_pickup: 'Готова к выдаче',
    title_delivered: 'Посылка прибыла',
    title_failed_attempt: 'Не удалось вручить',
    title_returned: 'Едет обратно',
    body_update: 'нажмите, чтобы узнать, что нового',
    body_pending: 'ждём первой отметки службы доставки',
    body_registered: 'служба доставки ещё не получила посылку',
    body_accepted: 'в путь!',
    body_in_transit: 'ещё на шаг ближе',
    body_customs: 'Peek следит за ситуацией',
    body_exception: 'служба доставки сообщила о проблеме. Нажмите, чтобы узнать, что делать.',
    body_out_for_delivery: 'почти на месте',
    body_ready_for_pickup: 'ждёт в пункте выдачи',
    body_delivered: 'доставлено',
    body_failed_attempt: 'служба доставки не смогла вручить посылку. Нажмите, чтобы узнать, что дальше.',
    body_returned: 'возвращается отправителю',
    delivered_time: 'доставлено в {{time}}',
    delivered_date: 'доставлено {{date}} в {{time}}',
    eta_day: 'прибудет {{date}}',
    eta_date: 'прибудет {{date}}',
    eta_changed_day: 'теперь прибудет {{date}}',
    eta_changed_date: 'теперь прибудет {{date}}',
    eta_by_day: 'прибудет до {{date}}',
    eta_by_date: 'прибудет до {{date}}',
    eta_window: 'прибудет с {{from}} по {{to}}',
    eta_changed_by_day: 'теперь прибудет до {{date}}',
    eta_changed_by_date: 'теперь прибудет до {{date}}',
    eta_changed_window: 'теперь прибудет с {{from}} по {{to}}',
  },
  zh: {
    test_title: 'Peek 会随时通知你',
    test_body: '这台设备会收到你所选环节的通知。随时可以在“设置 › 通知”中更改。',
    friend_title: '{{name}} 已加入',
    friend_body: '你们的护照现在并排放在一起了。快去看看吧。',
    update: '包裹动态',
    title_registered: '已生成面单',
    title_in_transit: '在路上了',
    title_ready_for_pickup: '可以取件了',
    title_delivered: '包裹到了',
    title_failed_attempt: '派送未成功',
    title_returned: '正在退回',
    body_update: '轻点查看最新动态',
    body_pending: '等待快递公司的首次扫描',
    body_registered: '快递公司还没收到包裹',
    body_accepted: '出发了',
    body_in_transit: '又近了一步',
    body_customs: 'Peek 正在帮你盯着',
    body_exception: '快递公司报告了问题。轻点查看该怎么办。',
    body_out_for_delivery: '马上就到',
    body_ready_for_pickup: '正在取件点等你',
    body_delivered: '已送达',
    body_failed_attempt: '快递公司没能送达。轻点查看后续安排。',
    body_returned: '正在退回寄件人',
    delivered_time: '已于 {{time}} 送达',
    delivered_date: '已于 {{date}} {{time}} 送达',
    eta_day: '预计送达：{{date}}',
    eta_date: '预计送达：{{date}}',
    eta_changed_day: '预计送达改为：{{date}}',
    eta_changed_date: '预计送达改为：{{date}}',
    eta_by_day: '预计{{date}}前送达',
    eta_by_date: '预计{{date}}前送达',
    eta_window: '预计{{from}}至{{to}}送达',
    eta_changed_by_day: '预计送达改为：{{date}}前',
    eta_changed_by_date: '预计送达改为：{{date}}前',
    eta_changed_window: '预计送达改为：{{from}}至{{to}}',
  },
};

/**
 * The pictures a browser draws with a notification. Android keeps only the
 * outline of the badge, for the status bar: the mark's eyes on nothing.
 */
export const WEB_NOTIFICATION_PICTURES = { icon: '/icons/icon-192.png', badge: '/icons/badge-96.png' };

export interface PushSummary {
  attempted: number;
  sent: number;
  failed: number;
  expired: number;
  /** What each channel that dispatched sent: the composite's count, by the channel names its logs use. */
  sentByChannel?: Partial<Record<PushChannel, number>>;
}

/** An account's browsers, its iPhones, its Live Activities, and the alerts of parcel links over either push. */
export type PushChannel = 'web' | 'native' | 'live_activity' | 'link_alert';

function emptySummary(): PushSummary {
  return { attempted: 0, sent: 0, failed: 0, expired: 0 };
}

export function notificationText(value: unknown, limit: number): string {
  const cleaned = String(value ?? '').trim().split(/\s+/).filter(Boolean).join(' ');
  const characters = [...cleaned];
  if (characters.length <= limit) return cleaned;
  return `${characters.slice(0, limit - 1).join('').trimEnd()}…`;
}

function stringField(row: JsonObject, name: string): string {
  return typeof row[name] === 'string' ? row[name] : '';
}

/** The carrier's name as the app writes it on the activities it starts, in the reader's language. */
function carrierDisplayName(value: unknown, locale: Locale): string {
  const carrier = typeof value === 'string' ? value : '';
  if (Object.hasOwn(CARRIER_CAPABILITIES, carrier)) {
    const capability: { displayName: string; displayNames?: Partial<Record<Locale, string>> } =
      CARRIER_CAPABILITIES[carrier as keyof typeof CARRIER_CAPABILITIES];
    return capability.displayNames?.[catalogLanguage(locale)] ?? capability.displayName;
  }
  return carrier || appWord(locale, 'add.carrier')!;
}

/** The language a notification is written in: its device's, or English. */
function notificationLocale(value: unknown): Locale {
  return (typeof value === 'string' && tagLocale(value)) || 'en';
}

/** What tells someone a friend took their invitation, in the language of the device it goes to. */
export function friendNotificationCopy(language: unknown, name: string): { locale: Locale; title: string; body: string } {
  const locale = notificationLocale(language);
  const copy = PUSH_COPY[locale];
  // A function, so that a nickname with a dollar sign is written as it is.
  return { locale, title: copy.friend_title!.replace('{{name}}', () => name), body: copy.friend_body! };
}

/** One of the app's own words in a notification's language, so both say the same. */
function appWord(locale: Locale, key: string): string | undefined {
  const messages: Record<string, string> = messagesFor(locale);
  return Object.hasOwn(messages, key) ? messages[key] : undefined;
}

/** A day of the year as a sentence names it: "9 October", without the year. */
function notificationDay(day: DateTime, locale: Locale): string {
  return day.setLocale(languageTags[locale]).toLocaleString({ day: 'numeric', month: 'long' });
}

interface ExpectedDelivery {
  /** The day, or the days, filled into the sentence. */
  date: string;
  to?: string;
  /**
   * The sentence that writes it: a day or time that needs no word before it ("tomorrow"), a day of the year that may
   * ("9 October"), the last day of a window that has begun ("by tomorrow", "by 6 November"), or a window still ahead.
   */
  form: 'day' | 'date' | 'by_day' | 'by_date' | 'window';
}

/**
 * An estimate on the reader's clock, or, with `ownClock`, on the offset it states (a "Z" says nothing of the place):
 * its day, and the time or slot the carrier gave on it.
 */
function estimatePoint(
  value: unknown, zone: string, ownClock: boolean, languageTag: string, use24HourTime: boolean,
): { day: DateTime; time: string; instant: DateTime | null } | null {
  const cleaned = notificationText(value, 100);
  if (!cleaned) return null;
  const slot = /^(\d{4}-\d{2}-\d{2})[ T]+(\d{2}:\d{2})(?:[–-](\d{2}:\d{2}))?$/.exec(cleaned);
  const clock = (time: string) => deliveryWallClock(time, languageTag, use24HourTime);
  if (slot) {
    const day = DateTime.fromISO(slot[1]!, { zone });
    return day.isValid ? { day, time: `${clock(slot[2]!)}${slot[3] ? `–${clock(slot[3])}` : ''}`, instant: null } : null;
  }
  const expected = DateTime.fromISO(cleaned, { zone, setZone: ownClock && /[+-]\d{2}:?\d{2}$/.test(cleaned) });
  if (!expected.isValid) return null;
  const timed = /T\d{2}:\d{2}/.test(cleaned);
  return { day: expected.startOf('day'), time: timed ? clock(expected.toFormat('HH:mm')) : '', instant: timed ? expected : null };
}

/**
 * When a parcel is expected, as a notification says it. A window of days reads whole until it begins, then by its last
 * day. Out for delivery already says the parcel comes today, so only the hours a carrier gives for today are added.
 * A reader who chose no time zone (`ownClock`) reads an estimate that states its offset on that offset, today included,
 * as a delivery's time (`deliveryClock`): a parcel is delivered where its reader is.
 */
function expectedDelivery(
  value: unknown,
  from: unknown,
  stage: string,
  locale: Locale,
  timezone: string,
  now: number,
  ownClock = false,
  use24HourTime = true,
): ExpectedDelivery | null {
  const zone = IANAZone.isValidZone(timezone) ? timezone : 'Europe/Zurich';
  const tag = languageTags[locale];
  const end = estimatePoint(value, zone, ownClock, tag, use24HourTime);
  if (!end) return null;
  const today = DateTime.fromMillis(now, { zone: end.day.zone }).startOf('day');
  if (!today.isValid || end.day < today) return null;
  const start = estimatePoint(from, zone, ownClock, tag, use24HourTime);
  const sameDay = (a: DateTime, b: DateTime) => a.toISODate() === b.toISODate();
  // Two times on one day are a slot on it.
  const time = start?.instant && end.instant && start.instant < end.instant && sameDay(start.day, end.day)
    ? `${start.time}–${end.time}` : end.time;
  const named = (day: DateTime) => {
    const relative = sameDay(day, today) ? 'time.today' : sameDay(day, today.plus({ days: 1 })) ? 'time.tomorrow' : null;
    return { text: relative ? appWord(locale, relative)! : notificationDay(day, locale), relative: !!relative };
  };
  if (stage === 'out_for_delivery') {
    // The end of a window of days that began before today is not today's hours.
    return sameDay(end.day, today) && time && !(start && start.day < today)
      ? { date: dayAndTime(named(today).text, time, tag), form: 'day' } : null;
  }
  if (start && start.day < end.day && !sameDay(end.day, today)) {
    if (start.day > today) return { date: notificationDay(start.day, locale), to: notificationDay(end.day, locale), form: 'window' };
    const last = named(end.day);
    return { date: last.text, form: last.relative ? 'by_day' : 'by_date' };
  }
  const day = named(end.day);
  // The last day of a window is its whole day: the time it ends by says nothing of when the parcel comes.
  const shown = start && start.day < end.day ? '' : time;
  return { date: shown ? dayAndTime(day.text, shown, tag) : day.text, form: day.relative ? 'day' : 'date' };
}

/**
 * When a parcel is expected, as a notification writes it: "tomorrow, 09:00–12:00". Empty for a day that has passed,
 * and for a parcel out for delivery that the carrier gives no hours today for.
 */
export function notificationExpectedDelivery(
  value: unknown,
  locale = 'en',
  timezone = 'Europe/Zurich',
  now = Date.now(),
  from: unknown = null,
  stage = '',
  ownClock = false,
): string {
  const expected = expectedDelivery(value, from, stage, notificationLocale(locale), timezone, now, ownClock);
  if (!expected) return '';
  return expected.to ? `${expected.date} – ${expected.to}` : expected.date;
}

/**
 * The clock a delivery's time is told on: the time zone its reader chose; else the scan's own, when its
 * source wrote the offset (a parcel is delivered where its reader is); else Zurich's. A time written
 * in UTC ("Z") says nothing of the place.
 */
function deliveryClock(row: JsonObject): Zone | string {
  const timezone = stringField(row, 'timezone');
  if (row.timezone_chosen !== false && IANAZone.isValidZone(timezone)) return timezone;
  const offset = /[T ]\d{2}:\d{2}(?::\d{2}(?:\.\d+)?)?([+-])(\d{2}):?(\d{2})$/.exec(stringField(row, 'event_time'));
  const minutes = offset ? Number(offset[2]) * 60 + Number(offset[3]) : NaN;
  if (minutes <= 14 * 60) return FixedOffsetZone.instance(offset![1] === '-' ? -minutes : minutes);
  return 'Europe/Zurich';
}

/** When a delivery happened, if its notification tells the time: the carrier gave a clock time, not in the future. */
function deliveredInstant(row: JsonObject, now: number): DateTime | null {
  // The queue distinguishes carrier times from date-only and observed updates.
  if (stringField(row, 'stage') !== 'delivered' || row.event_has_time !== true) return null;
  const delivered = DateTime.fromISO(stringField(row, 'occurred_at'), { zone: 'utc' });
  return delivered.isValid && delivered.toMillis() <= now ? delivered : null;
}

function deliveredMessage(row: JsonObject, locale: Locale, now: number, use24HourTime: boolean): string {
  const copy = PUSH_COPY[locale];
  const instant = deliveredInstant(row, now);
  const zone = deliveryClock(row);
  const delivered = instant?.setZone(zone);
  const current = DateTime.fromMillis(now, { zone });
  if (!delivered?.isValid || !current.isValid) return copy.body_delivered!;

  const sameDay = delivered.toISODate() === current.toISODate();
  const template = sameDay ? copy.delivered_time : copy.delivered_date;
  return template!
    .replace('{{time}}', deliveryWallClock(delivered.toFormat('HH:mm'), languageTags[locale], use24HourTime))
    .replace('{{date}}', notificationDay(delivered, locale));
}

const ETA_NOTIFICATION_STAGES = new Set([
  'registered', 'accepted', 'in_transit', 'customs', 'out_for_delivery',
]);

/** The news a notification leads with: a title of its own, or the app's word for the stage. */
function notificationTitle(row: JsonObject, locale: Locale): string {
  const stage = stringField(row, 'stage');
  const copy = PUSH_COPY[locale];
  return notificationText(copy[`title_${stage}`] ?? appWord(locale, `stage.${stage}`) ?? copy.update, 80);
}

/** What the step means: when the parcel arrives if that is known, the stage's own sentence otherwise. */
function notificationDetail(row: JsonObject, locale: Locale, now: number, use24HourTime = true): string {
  const stage = stringField(row, 'stage');
  const copy = PUSH_COPY[locale];
  if (stage === 'delivered') return deliveredMessage(row, locale, now, use24HourTime);
  const expected = ETA_NOTIFICATION_STAGES.has(stage)
    ? expectedDelivery(row.expected_delivery, row.expected_delivery_from, stage, locale,
      stringField(row, 'timezone') || 'Europe/Zurich', now, row.timezone_chosen === false, use24HourTime)
    : null;
  if (!expected) return copy[`body_${stage}`] ?? copy.body_update!;
  const changed = row.expected_delivery_changed === true ? 'eta_changed' : 'eta';
  // Functions, so that a day written with a dollar sign is kept as it is.
  return copy[`${changed}_${expected.form}`]!
    .replace(/\{\{(?:date|from)\}\}/, () => expected.date).replace('{{to}}', () => expected.to ?? '');
}

/**
 * The scan's place as a notification says it: as written, unless it is in a script other than Latin and the
 * reader's own (`romanisesPlace`) and the place reader finds its town ("深圳市" is "Shenzhen, China") or its
 * country. The stored scan keeps its words.
 */
function notificationPlace(row: JsonObject, locale: Locale): unknown {
  const location = typeof row.location === 'string' ? row.location.trim() : '';
  if (!romanisesPlace(location, locale)) return row.location;
  const place = locatePlace(location, typeof row.carrier === 'string' ? { carriers: [row.carrier] } : undefined);
  if (!place) return row.location;
  const country = countryName(place.country, languageTags[locale]);
  return place.precision === 'city' ? `${place.name}, ${country}` : country;
}

/**
 * The line under the title: what leads it (the parcel's name, for whoever may
 * read it), then the detail, then the scan's place on a line of its own (`notificationPlace`).
 */
function notificationBody(
  row: JsonObject, locale: Locale, now: number, lead = row.label, detail = notificationDetail(row, locale, now),
): string {
  const name = notificationText(lead, 80);
  const primary = notificationText(name ? `${name} · ${detail}` : capitalized(detail, languageTags[locale]), 220);
  const remaining = 220 - [...primary].length - 1;
  const location = remaining > 1 ? notificationText(notificationPlace(row, locale), Math.min(140, remaining)) : '';
  return location ? `${primary}\n${location}` : primary;
}

function notificationBodies(row: JsonObject, locale: Locale, now: number, lead = row.label) {
  const detail = notificationDetail(row, locale, now);
  const alternate = notificationDetail(row, locale, now, false);
  const body = notificationBody(row, locale, now, lead, detail);
  return alternate === detail ? { body } : { body, body_12_hour: notificationBody(row, locale, now, lead, alternate) };
}

const DEVICE_CLOCK_KEYS = ['scan_at', 'body_same_day', 'body_other_day', 'date_locale', 'body_12_hour'] as const;

/**
 * What lets a device write a delivery's time on its own clock as the notification arrives, wherever it is
 * by then: the scan's instant (`scan_at`), the body with the time left for the device to write, for a
 * delivery on the device's day and on another (`{{time}}`, `{{date}}`), and the language tag the day is
 * written in. The body beside them is what shows where no device code runs. Nothing when the body tells
 * no time. Flat strings, as every push service carries them.
 */
export function deviceClockFields(row: JsonObject, locale: Locale, now: number, lead = row.label): Record<string, string> {
  const instant = deliveredInstant(row, now);
  if (!instant) return {};
  const copy = PUSH_COPY[locale];
  return {
    scan_at: instant.toISO()!,
    body_same_day: notificationBody(row, locale, now, lead, copy.delivered_time!),
    body_other_day: notificationBody(row, locale, now, lead, copy.delivered_date!),
    date_locale: languageTags[locale],
  };
}

// Database insertion times are identical for events imported in one transaction.
// Prefer carrier chronology; resolve coarse/missing timestamps by delivery progress,
// then by the order the scans' source listed them in.


export function compareNotificationEvents(left: JsonObject, right: JsonObject): number {
  const timestamp = (row: JsonObject, key: string) => {
    const value = Date.parse(stringField(row, key));
    return Number.isFinite(value) ? value : 0;
  };
  return timestamp(right, 'occurred_at') - timestamp(left, 'occurred_at')
    || EVENT_STAGE_ORDER.indexOf(stringField(right, 'stage')) - EVENT_STAGE_ORDER.indexOf(stringField(left, 'stage'))
    || tieOrder(right.tie_order) - tieOrder(left.tie_order)
    || timestamp(right, 'event_created_at') - timestamp(left, 'event_created_at')
    || stringField(right, 'event_id').localeCompare(stringField(left, 'event_id'));
}

/**
 * What a batch of new scans is measured against, by parcel: its newest stored
 * scan (`newestNews`), when it joined its account, the scans whose news it
 * already had (`scanNews`), and whether it is archived.
 */
interface ParcelTimes {
  latest: ReadonlyMap<string, string>;
  joined: ReadonlyMap<string, string>;
  known: ReadonlySet<string>;
  archived: ReadonlySet<string>;
}

/** A lookup that fails leaves its answer empty, and its rule then announces every batch as before. */
async function orNone<T>(load: () => Promise<T>, none: T): Promise<T> {
  try { return await load(); } catch { return none; }
}

const batchParcels = (batches: readonly JsonObject[][]) => batches.map((events) => stringField(events[0]!, 'package_id')).filter(Boolean);

type RecentScans = Awaited<ReturnType<SupabaseServiceClient['recentScans']>>;
const NO_SCANS: RecentScans = new Map();

/** The parcels' notices in the stage of their scan before them (stageNotices.ts). */
function noticesIn(recent: RecentScans): Set<string> {
  return new Set([...recent.values()].flatMap((scans) => [...stageNotices(scans.map((scan) => ({
    id: scan.id, source: scan.source, stage: scan.stage, stageSource: scan.stageSource ?? null, at: Date.parse(scan.occurredAt),
  }))).keys()]));
}

/**
 * The scans of the batches whose news their parcel already had: a handed-over
 * parcel's relay repeats and late scans (relayCopies.ts) and the universal
 * copies its timeline leaves out (providerCopies.ts), a carrier's repeats
 * of a scan it had sent (scanRepeats.ts) and its notices in the stage of the
 * scan before them (stageNotices.ts). Copies are looked for only in
 * parcels with a confirmed carrier whose batch holds a universal row. A
 * generated track's moves are no news either (generatedTrack.ts): they are
 * looked for in parcels whose batch holds a scan in transit. With the
 * parcels' recent scans and their notices.
 */
async function scanNews(client: SupabaseServiceClient, batches: readonly JsonObject[][]) {
  const eventIds = batches.flat().map((event) => stringField(event, 'event_id')).filter(Boolean);
  const moving = new Set(batchParcels(batches.filter((events) => events.some((event) => stringField(event, 'stage') === 'in_transit'))));
  const [handoffs, copied, recent] = await Promise.all([
    orNone(() => client.handoffScans(batchParcels(batches)), []),
    orNone(() => client.providerCopyScans(eventIds), []),
    batches.length ? orNone(() => client.recentScans(batchParcels(batches)), NO_SCANS) : NO_SCANS,
  ]);
  const notices = noticesIn(recent);
  const known = new Set([...handoffs.flatMap((row) => [...relayRepeats(row), ...lateScans(row)]),
    ...copied.flatMap((row) => [...providerCopies(row)]),
    ...notices,
    ...[...recent].flatMap(([packageId, scans]) => [
      ...moving.has(packageId) ? generatedScans(scans).map((scan) => scan.id) : [],
      ...repeatedScans(scans.map((scan) => ({ ...scan, at: Date.parse(scan.occurredAt), location: scan.location?.trim() ?? '' })))])]);
  return { known, recent, notices };
}

async function knownScans(client: SupabaseServiceClient, batches: readonly JsonObject[][]): Promise<Set<string>> {
  return (await scanNews(client, batches)).known;
}

/**
 * Each parcel's newest scan, a notice in the stage of the scan before it aside: the scan's news is
 * not older than the notice. Read from the parcel's recent scans when it has such a notice, as
 * `latestScanTimes` reads the stored ones otherwise.
 */
function newestNews(latest: ReadonlyMap<string, string>, recent: RecentScans, notices: ReadonlySet<string>, now: number): Map<string, string> {
  const newest = new Map(latest);
  for (const [packageId, scans] of recent) {
    if (!scans.some((scan) => notices.has(scan.id))) continue;
    const news = scans.filter((scan) => scan.stage !== 'pending' && scan.source !== 'app' && !notices.has(scan.id)
      && Date.parse(scan.occurredAt) <= now).sort((a, b) => Date.parse(b.occurredAt) - Date.parse(a.occurredAt))[0];
    if (news) newest.set(packageId, news.occurredAt);
  }
  return newest;
}

/**
 * What the batches are measured against. Whether a parcel is archived is read for the batches
 * `archivedAmong` names, all of them unless told: unlike the other lookups, that one must answer,
 * since an owner is never told about a parcel they put away (`putAway`). When it fails, so does
 * this, and the dispatch leaves its scans pending for the next one.
 */
async function parcelTimes(
  client: SupabaseServiceClient, batches: Iterable<JsonObject[]>, now: number, archivedAmong?: readonly JsonObject[][],
): Promise<ParcelTimes> {
  const all = [...batches];
  const ids = batchParcels(all);
  const archivable = archivedAmong ? batchParcels(archivedAmong) : ids;
  const [latest, joined, { known, recent, notices }, archived] = await Promise.all([
    orNone(() => client.latestScanTimes(ids), new Map<string, string>()),
    orNone(() => client.packageJoinTimes(ids), new Map<string, string>()),
    scanNews(client, all),
    archivable.length ? client.archivedPackageIds(archivable) : new Set<string>(),
  ]);
  return { latest: newestNews(latest, recent, notices, now), joined, known, archived };
}

/**
 * The scan a batch announces: its newest, leaving out the row of a relay pair
 * that reached the parcel second, which repeats news the parcel already had,
 * an earlier carrier's scan that reached it after a later one, which is older
 * news, and a universal copy of a carrier's scan, which the timeline leaves
 * out. None when that is all the batch holds.
 */
function announcedScan(events: readonly JsonObject[], { known }: Pick<ParcelTimes, 'known'>): JsonObject | undefined {
  return events.filter((event) => !known.has(stringField(event, 'event_id'))).sort(compareNotificationEvents)[0];
}

/**
 * The scan a batch tells of: its newest (`announcedScan`), or the return before it when the batch's scans after the
 * return only carry the parcel back (`returnUnderWay`), as the sync stores the parcel's stage. Whether the batch is
 * news is still judged on its newest scan.
 */
function toldScan(newest: JsonObject, events: readonly JsonObject[], { known }: Pick<ParcelTimes, 'known'>): JsonObject {
  return returnUnderWay(events.filter((event) => !known.has(stringField(event, 'event_id'))).sort(compareNotificationEvents)) ?? newest;
}

const BACKFILL_TOLERANCE_MS = 60 * 60 * 1_000;
/** The check that runs as a parcel is added stores its history within this. */
const ADD_CHECK_MS = 5 * 60 * 1_000;
const DAY_MS = 24 * 60 * 60 * 1_000;

/**
 * A batch is announced by its newest scan (`announcedScan`), unless that scan
 * is not news; the batch is then recorded as handled without a notification.
 *
 * - Backfilled: older than the parcel's newest scan, give or take an hour of
 *   clock skew (partner scans can run ahead). A carrier change backfills
 *   history, a provider reports a scan late.
 * - Known when added: from before the parcel joined its account, and either
 *   stored by the check that runs as it is added (the carrier had it then; a
 *   scan with a clock must also be from before) or more than a day older than
 *   the add, counting a whole day for a scan without a clock. That first check
 *   finds the history the owner added the parcel with, whose newest scan is
 *   always the parcel's newest. A first history that only arrives later, with
 *   scans from the hours before the add, is still announced.
 * - Stale: more than a day old when it would be announced. A leg a provider
 *   reports days late is stored and shown, but no longer worth a
 *   notification. A scan without a clock has no age to judge.
 */
export function isOldNews(newest: JsonObject, { latest, joined }: ParcelTimes, now: number): boolean {
  const packageId = stringField(newest, 'package_id');
  const scanned = Date.parse(stringField(newest, 'occurred_at'));
  const clockless = newest.event_has_time === false;
  if (!clockless && scanned < now - DAY_MS) return true;
  const parcel = Date.parse(latest.get(packageId) ?? '');
  if (Number.isFinite(scanned) && Number.isFinite(parcel) && scanned < parcel - BACKFILL_TOLERANCE_MS) return true;
  const since = Date.parse(joined.get(packageId) ?? '');
  if (!Number.isFinite(since)) return false;
  const stored = Date.parse(stringField(newest, 'event_created_at'));
  if (stored < since + ADD_CHECK_MS && (clockless || scanned < since)) return true;
  return scanned + (clockless ? DAY_MS : 0) < since - DAY_MS;
}

/**
 * Whether the parcel's account put it away, which its owner is not told about:
 * the daily check that follows it there updates it silently, and bringing it
 * back does not announce what was recorded meanwhile. Someone it was shared
 * with still gets their link's alerts.
 */
function putAway(newest: JsonObject, { archived }: Pick<ParcelTimes, 'archived'>): boolean {
  return archived.has(stringField(newest, 'package_id'));
}

export class WebPushNotificationService {
  constructor(
    readonly client: SupabaseServiceClient,
    readonly publicKey: string,
    readonly privateKey: string,
    readonly subject: string,
    readonly now: () => number = () => Date.now(),
  ) {}

  async dispatch(signal?: AbortSignal): Promise<PushSummary> {
    signal?.throwIfAborted();
    const grouped = new Map<string, JsonObject[]>();
    for (const row of await this.client.listPendingPushNotifications()) {
      const key = JSON.stringify([stringField(row, 'subscription_id'), stringField(row, 'package_id')]);
      grouped.set(key, [...(grouped.get(key) ?? []), row]);
    }
    const summary = emptySummary();
    const times = await parcelTimes(this.client, grouped.values(), this.now());
    for (const events of grouped.values()) {
      signal?.throwIfAborted();
      const newest = announcedScan(events, times);
      const subscriptionId = stringField(events[0]!, 'subscription_id');
      if (!newest || putAway(newest, times) || isOldNews(newest, times, this.now())) {
        await this.client.recordPushDeliveries(subscriptionId, events.map((event) => stringField(event, 'event_id')).filter(Boolean));
        recordAccountPushSent('skipped', 'web');
        continue;
      }
      summary.attempted += 1;
      try {
        await this.send(toldScan(newest, events, times));
        // Sent is recorded, even while the server stops: the scan must not go out twice.
        await this.client.recordPushDeliveries(
          subscriptionId,
          events.map((event) => stringField(event, 'event_id')).filter(Boolean),
        );
        await this.client.updatePushSubscription(subscriptionId, {
          last_success_at: new Date().toISOString(),
          last_error: null,
        });
        recordAccountPushSent('sent', 'web');
        summary.sent += 1;
      } catch (error) {
        signal?.throwIfAborted();
        const status = typeof error === 'object' && error !== null && 'statusCode' in error
          ? Number(error.statusCode)
          : 0;
        if (status === 404 || status === 410) {
          await this.client.updatePushSubscription(subscriptionId, {
            disabled_at: new Date().toISOString(),
            last_error: 'Push endpoint expired',
          });
          recordAccountPushSent('expired', 'web');
          summary.expired += 1;
        } else {
          logDeliveryFailure('web', error);
          await this.client.updatePushSubscription(subscriptionId, {
            last_error: 'Push delivery failed',
          });
          recordAccountPushSent('failed', 'web');
          summary.failed += 1;
        }
      }
    }
    return summary;
  }

  async sendTest(subscription: JsonObject): Promise<void> {
    const locale = notificationLocale(subscription.locale);
    const copy = PUSH_COPY[locale];
    await this.send(subscription, {
      title: copy.test_title,
      body: copy.test_body,
      ...WEB_NOTIFICATION_PICTURES,
      tag: 'parcel-post-ready',
      lang: locale,
      data: { url: '/' },
    });
  }

  async send(row: JsonObject, payload?: JsonObject): Promise<void> {
    await webpush.sendNotification(
      {
        endpoint: stringField(row, 'endpoint'),
        keys: {
          p256dh: stringField(row, 'p256dh'),
          auth: stringField(row, 'auth'),
        },
      },
      JSON.stringify(payload ?? this.payload(row)),
      {
        TTL: 86_400,
        timeout: 15_000,
        vapidDetails: {
          subject: this.subject,
          publicKey: this.publicKey,
          privateKey: this.privateKey,
        },
      },
    );
  }

  payload(row: JsonObject): JsonObject {
    const locale = notificationLocale(row.locale);
    const packageId = stringField(row, 'package_id');
    const now = this.now();
    return {
      title: notificationTitle(row, locale),
      ...notificationBodies(row, locale, now),
      ...deviceClockFields(row, locale, now),
      ...WEB_NOTIFICATION_PICTURES,
      tag: `parcel-${packageId}`,
      lang: locale,
      data: { url: `/?parcel=${packageId}` },
    };
  }
}

/** The push service says the subscription is gone, as for an account's. */
const GONE_SUBSCRIPTION = new Set([404, 410]);

/** What a link's alert is sent through: a browser's push service, APNs or FCM. */
function linkAlertTransport(row: JsonObject): 'web' | NativeTransport {
  if (stringField(row, 'apns_token')) return 'apns';
  return stringField(row, 'fcm_token') ? 'fcm' : 'web';
}
/** An alert whose sends keep failing is removed after this many in a row. */
const MAX_LINK_ALERT_FAILURES = 3;
const FINISHED_STAGES = new Set(['delivered', 'returned']);

/**
 * Notifications for parcel links, for people without an account, in a browser,
 * the iPhone app or the Android app. An alert is told about its parcel's scans with the
 * words an account gets, in the alert's language, when its preset covers the
 * scan's stage. It never carries the parcel's name or number.
 *
 * - A batch of new scans announces its newest covered one, unless that is not
 *   news (`isOldNews`), as for accounts. A parcel its account archived is
 *   still announced to the people it was shared with, not to its owner.
 * - A gift on its way is announced as one: without the place of a scan, and
 *   without the scans its page leaves out.
 * - A link that hides its number masks the parcel's numbers in the scan's
 *   place, as its page does (`viewerScan`).
 * - An alert on a browser or phone the parcel's owner gets account
 *   notifications on is passed over, so nobody is told twice.
 * - An alert ends with the journey, when the push service says its
 *   subscription is gone, and after a few failed sends in a row. A failed send
 *   is not repeated: anyone with a link can add an endpoint, so one that never
 *   works must cost a bounded number of requests. For the same reason failures
 *   are logged and counted in metrics, not reported as errors.
 */
export class ParcelLinkAlertService {
  readonly client: SupabaseServiceClient;

  constructor(
    readonly web: WebPushNotificationService | null,
    readonly native: NativePushNotificationService | null = null,
    readonly fcm: FcmPushNotificationService | null = null,
  ) {
    const client = web?.client ?? native?.client ?? fcm?.client;
    if (!client) throw new TypeError('Parcel link alerts need Web Push, APNs or FCM');
    this.client = client;
  }

  /** Milliseconds, whichever channel keeps the clock. */
  now(): number {
    return this.web ? this.web.now() : (this.native ?? this.fcm)!.now() * 1_000;
  }

  async dispatch(signal?: AbortSignal): Promise<PushSummary> {
    signal?.throwIfAborted();
    const { client } = this;
    const grouped = new Map<string, JsonObject[]>();
    const channels = { web: this.web !== null, apns: this.native !== null, fcm: this.fcm !== null };
    for (const row of await client.listPendingParcelLinkAlerts(channels)) {
      const alertId = stringField(row, 'alert_id');
      grouped.set(alertId, [...(grouped.get(alertId) ?? []), row]);
    }
    const summary = emptySummary();
    let failed = 0;
    // Only an owner's own alerts are held back by the archive (`putAway`). When it cannot be read, theirs wait for
    // the next dispatch, which fails with the lookup, and the people the parcel was shared with are still told.
    const owned = batchParcels([...grouped.values()].filter((events) => events[0]!.owner === true));
    const [times, archive] = await Promise.all([
      parcelTimes(client, grouped.values(), this.now(), []),
      owned.length
        ? client.archivedPackageIds(owned).then((ids) => ({ ids }), (error: unknown) => ({ ids: null, error }))
        : { ids: new Set<string>() },
    ]);
    const viewers = [...grouped.values()].filter((events) => events[0]!.owner !== true).map((events) => stringField(events[0]!, 'link_id'));
    const hiding = viewers.length ? await orNone(() => client.numberHidingParcels(viewers), null) : new Map<string, JsonObject>();
    for (const [alertId, events] of grouped) {
      signal?.throwIfAborted();
      const owner = events[0]!.owner === true;
      if (owner && !archive.ids) continue;
      const announced = events.filter((event) => this.announces(event));
      const newest = announcedScan(announced, times);
      const transport = linkAlertTransport(events[0]!);
      const app = transport === 'apns' ? this.native : transport === 'fcm' ? this.fcm : null;
      const channel = app?.app ?? 'web';
      const finished = FINISHED_STAGES.has(stringField(events[0]!, 'package_stage'));
      const failures = Number(events[0]!.failures ?? 0);
      const eventIds = events.map((event) => stringField(event, 'event_id')).filter(Boolean);
      const remove = async (reason: 'delivered' | 'expired' | 'failed') => {
        await client.deleteParcelLinkAlert(alertId);
        recordParcelAlertRemoved(reason);
      };
      /** The batch is handled, whatever became of it; a journey that is over needs no alert. */
      const settle = async (failuresNow: number) => {
        if (finished) return await remove('delivered');
        await client.recordParcelLinkAlertDeliveries(alertId, eventIds);
        if (failuresNow !== failures) await client.setParcelLinkAlertFailures(alertId, failuresNow);
      };

      if (!newest || newest.account_endpoint === true || (owner && putAway(newest, { archived: archive.ids! }))
        || isOldNews(newest, times, this.now())) {
        await settle(failures);
        recordParcelAlertSent('skipped', channel);
        continue;
      }
      summary.attempted += 1;
      const told = this.viewerScan(toldScan(newest, announced, times), hiding);
      try {
        if (transport !== 'web') await app!.send(this.appDevice(told), this.appPayload(told), `link-${stringField(told, 'link_id')}`);
        else await this.web!.send(told, this.payload(told));
      } catch (error) {
        signal?.throwIfAborted();
        const status = typeof error === 'object' && error !== null && 'statusCode' in error ? Number(error.statusCode) : 0;
        if (transport !== 'web' ? app!.isExpired(error) : GONE_SUBSCRIPTION.has(status)) {
          await remove('expired');
          recordParcelAlertSent('expired', channel);
          summary.expired += 1;
        } else if (serverCredentialsFailed(error)) {
          // No device was tried: the alert keeps its count, or every alert of the app would go after three scans.
          failed += 1;
          recordParcelAlertSent('failed', channel);
          await settle(failures);
        } else {
          failed += 1;
          recordParcelAlertSent('failed', channel);
          if (failures + 1 >= MAX_LINK_ALERT_FAILURES) await remove('failed');
          else await settle(failures + 1);
        }
        continue;
      }
      // Sent is recorded, even while the server stops: the alert must not go out twice.
      await settle(0);
      recordParcelAlertSent('sent', channel);
      summary.sent += 1;
    }
    if (failed > 0) logOperationalEvent('parcel_link_alerts_failed', { failed }, 'error');
    if ('error' in archive) throw new PushDispatchError([archive.error], summary);
    return summary;
  }

  /** Whether the alert's preset covers the scan. A gift on its way is not told what its page leaves out. */
  announces(row: JsonObject): boolean {
    const preset = stringField(row, 'preset');
    const stage = stringField(row, 'stage');
    const stages: readonly string[] = Object.hasOwn(ALERT_PRESET_STAGES, preset)
      ? ALERT_PRESET_STAGES[preset as ApiParcelAlertPreset] : [];
    return stages.includes(stage) && !(this.wrappedGift(row) && stage === 'registered');
  }

  /**
   * The scan as the alert may tell it: for a viewer of a link that hides its number, its place with the parcel's
   * numbers masked as its page masks them (`parcelNumberMask`), or without its place when they could not be read.
   */
  viewerScan(row: JsonObject, hiding: ReadonlyMap<string, JsonObject> | null): JsonObject {
    if (row.owner === true || typeof row.location !== 'string') return row;
    if (hiding === null) return { ...row, location: null };
    const parcel = hiding.get(stringField(row, 'link_id'));
    if (!parcel) return row;
    const events = Array.isArray(parcel.tracking_events) ? parcel.tracking_events.filter(isRecord) : [];
    // As the link's page masks it: with the numbers every scan, planned step and worded field names.
    const mask = parcelNumberMask(parcel, [...events, ...servedPlannedSteps(parcel, []), row]);
    return { ...row, location: mask(row.location) };
  }

  /** A gift that has not arrived, for everyone but the lookup's owner. */
  private wrappedGift(row: JsonObject): boolean {
    return row.gift === true && row.owner !== true && stringField(row, 'package_stage') !== 'delivered';
  }

  payload(row: JsonObject): JsonObject {
    const locale = notificationLocale(row.locale);
    const linkId = stringField(row, 'link_id');
    const stage = stringField(row, 'stage');
    const news = notificationTitle(row, locale);
    // A gift is announced as one while it travels and when it lands, in its page's words. The step it is at
    // then leads the line below, unless the title has just said it.
    const gift = row.gift === true && row.owner !== true && stage !== 'returned';
    const title = gift ? appWord(locale, stage === 'delivered' ? 'share.gift.here' : 'share.gift.headline')! : news;
    const lead = gift && stage !== 'delivered' && stage !== 'in_transit' ? news : '';
    // Neither the parcel's name nor its number: both are its owner's. The scan's place, and the clock it
    // was written on, are part of what a gift keeps to itself. Nobody chose a time zone: a delivery is told
    // on the scan's clock, the rest on Zurich's, as for an account without one.
    const told: JsonObject = {
      ...row, timezone: 'Europe/Zurich', timezone_chosen: false,
      ...this.wrappedGift(row) ? { location: null, event_time: null } : {},
    };
    const now = this.now();
    return {
      title: notificationText(title, 80),
      ...notificationBodies(told, locale, now, lead),
      ...deviceClockFields(told, locale, now, lead),
      ...WEB_NOTIFICATION_PICTURES,
      tag: `parcel-link-${linkId}`,
      lang: locale,
      data: { url: `/p/${linkId}` },
    };
  }

  /** Where APNs sends an iPhone's alert, or FCM an Android phone's. */
  private appDevice(row: JsonObject): JsonObject {
    return linkAlertTransport(row) === 'fcm'
      ? { token: stringField(row, 'fcm_token'), environment: 'production' }
      : { token: stringField(row, 'apns_token'), environment: stringField(row, 'apns_environment') };
  }

  /** The same words for the phone apps, which open the link they follow when the notification is tapped. */
  appPayload(row: JsonObject): JsonObject {
    const { title, body, ...web } = this.payload(row);
    const clock = Object.fromEntries(DEVICE_CLOCK_KEYS.flatMap((key) => key in web ? [[key, web[key]]] : []));
    const linkId = stringField(row, 'link_id');
    return {
      aps: {
        alert: { title, body }, sound: 'default', badge: 1, 'thread-id': `link-${linkId}`,
        ...Object.keys(clock).length ? { 'mutable-content': 1 } : {},
      },
      link_id: linkId,
      ...clock,
    };
  }
}

/** APNs refusing the server's own signing key, whatever the device. */
const APNS_PROVIDER_TOKEN_REASONS = new Set(['InvalidProviderToken', 'ExpiredProviderToken', 'MissingProviderToken']);

/** Whether the server's own credentials failed (FCM's service account, APNs's signing key): no device is at fault. */
function serverCredentialsFailed(error: unknown): boolean {
  return error instanceof FCMAuthError || (error instanceof APNsError && APNS_PROVIDER_TOKEN_REASONS.has(error.reason));
}

export class APNsError extends Error {
  constructor(
    readonly statusCode: number,
    readonly reason = '',
  ) {
    super(reason || `APNs returned HTTP ${statusCode}`);
    this.name = 'APNsError';
  }
}

async function postApns(
  host: string,
  path: string,
  headers: Record<string, string>,
  payload: JsonObject,
  timeoutMs: number,
): Promise<{ status: number; reason: string }> {
  return await new Promise((resolve, reject) => {
    const session = connect(`https://${host}`);
    const finish = (error?: Error) => {
      session.close();
      if (error) reject(error);
    };
    const timeout = setTimeout(() => {
      session.destroy();
      reject(new Error('APNs request timed out'));
    }, timeoutMs);
    session.once('error', (error) => {
      clearTimeout(timeout);
      finish(error);
    });
    const request = session.request({
      [http2Constants.HTTP2_HEADER_METHOD]: 'POST',
      [http2Constants.HTTP2_HEADER_PATH]: path,
      ...headers,
    });
    let status = 0;
    const chunks: Buffer[] = [];
    let length = 0;
    request.on('response', (responseHeaders) => {
      status = Number(responseHeaders[http2Constants.HTTP2_HEADER_STATUS] ?? 0);
    });
    request.on('data', (chunk: Buffer) => {
      length += chunk.byteLength;
      if (length <= 65_536) chunks.push(chunk);
      else request.close(http2Constants.NGHTTP2_CANCEL);
    });
    request.once('error', (error) => {
      clearTimeout(timeout);
      finish(error);
    });
    request.once('end', () => {
      clearTimeout(timeout);
      let reason = '';
      try {
        const body: unknown = JSON.parse(Buffer.concat(chunks).toString('utf8') || '{}');
        if (isRecord(body) && typeof body.reason === 'string') reason = body.reason;
      } catch {
        // APNs error bodies are best effort; status remains authoritative.
      }
      session.close();
      resolve({ status, reason });
    });
    request.end(JSON.stringify(payload));
  });
}

/** How the server reaches a phone: APNs for the iPhone app, Firebase Cloud Messaging for the Android app. */
export type NativeTransport = 'apns' | 'fcm';

/** What replaces an earlier notification for the same thing: given, else the parcel's id, else the friend's. */
function collapseKey(row: JsonObject, given?: string): string {
  const packageId = stringField(row, 'package_id');
  return (given ?? (packageId || (row.friend_id ? `friend-${String(row.friend_id)}` : ''))).slice(0, 64);
}

/**
 * The iPhone's payload as FCM data, which holds strings alone: the alert's title and body
 * beside the payload's own fields (`parcel_id`, `link_id`, `kind`, `friend_id` and those of
 * `deviceClockFields`), which the Android app reads as the iPhone's notification service does.
 */
export function fcmData(payload: JsonObject): Record<string, string> {
  const { aps, ...fields } = payload;
  const alert = isRecord(aps) && isRecord(aps.alert) ? aps.alert : {};
  const data: Record<string, string> = {};
  for (const [key, value] of Object.entries({ title: alert.title, body: alert.body, ...fields })) {
    if (value === undefined || value === null) continue;
    data[key] = typeof value === 'string' ? value : JSON.stringify(value);
  }
  return data;
}

/**
 * An account's notifications on its phones, from one queue in the same words: each service
 * sends to the devices of its own transport. Payloads take the iPhone's shape (`payload`);
 * FCM carries the same alert and fields as data (`fcmData`).
 */
abstract class AppPushNotificationService {
  abstract readonly client: SupabaseServiceClient;
  /** Seconds. */
  abstract readonly now: () => number;
  abstract readonly transport: NativeTransport;

  /** `collapseId` replaces a later notification for the same thing; a parcel's id, or a friend's, unless given. */
  abstract send(row: JsonObject, payload?: JsonObject, collapseId?: string): Promise<void>;

  /** Whether the push service says the device's token will never take a notification again. */
  abstract isExpired(error: unknown): boolean;

  /** The app that metrics count this transport's sends for. */
  get app(): 'ios' | 'android' {
    return this.transport === 'fcm' ? 'android' : 'ios';
  }

  private get serviceName(): string {
    return this.transport === 'fcm' ? 'FCM' : 'APNs';
  }

  async dispatch(signal?: AbortSignal): Promise<PushSummary> {
    signal?.throwIfAborted();
    const grouped = new Map<string, JsonObject[]>();
    for (const row of await this.client.listPendingNativePushNotifications(this.transport)) {
      const key = JSON.stringify([stringField(row, 'device_id'), stringField(row, 'package_id')]);
      grouped.set(key, [...(grouped.get(key) ?? []), row]);
    }
    const summary = emptySummary();
    const times = await parcelTimes(this.client, grouped.values(), this.now() * 1_000);
    for (const events of grouped.values()) {
      signal?.throwIfAborted();
      const newest = announcedScan(events, times);
      const deviceId = stringField(events[0]!, 'device_id');
      if (!newest || newest.live_activity_delivered === true || putAway(newest, times)
        || isOldNews(newest, times, this.now() * 1_000)) {
        await this.client.recordNativePushDeliveries(
          deviceId,
          events.map((event) => stringField(event, 'event_id')).filter(Boolean),
        );
        recordAccountPushSent('skipped', this.app);
        continue;
      }
      summary.attempted += 1;
      try {
        await this.send(toldScan(newest, events, times));
        // Sent is recorded, even while the server stops: the scan must not go out twice.
        await this.client.recordNativePushDeliveries(
          deviceId,
          events.map((event) => stringField(event, 'event_id')).filter(Boolean),
        );
        await this.client.updateNativePushDevice(deviceId, {
          last_success_at: new Date().toISOString(),
          last_error: null,
        });
        recordAccountPushSent('sent', this.app);
        summary.sent += 1;
      } catch (error) {
        signal?.throwIfAborted();
        if (this.isExpired(error)) {
          await this.client.updateNativePushDevice(deviceId, {
            disabled_at: new Date().toISOString(),
            last_error: `${this.serviceName} device token expired`,
          });
          recordAccountPushSent('expired', this.app);
          summary.expired += 1;
        } else {
          logDeliveryFailure('native', error, this.transport);
          await this.client.updateNativePushDevice(deviceId, {
            last_error: `${this.serviceName} delivery failed`,
          });
          recordAccountPushSent('failed', this.app);
          summary.failed += 1;
        }
      }
    }
    return summary;
  }

  async sendTest(device: JsonObject): Promise<void> {
    const copy = PUSH_COPY[this.locale(device)];
    await this.send(device, this.payload(copy.test_title!, copy.test_body!, null));
  }

  eventPayload(row: JsonObject): JsonObject {
    const locale = this.locale(row);
    const now = this.now() * 1_000;
    const { body, body_12_hour } = notificationBodies(row, locale, now);
    return this.payload(
      notificationTitle(row, locale),
      body,
      stringField(row, 'package_id'),
      {
        ...deviceClockFields(row, locale, now),
        ...body_12_hour ? { body_12_hour } : {},
      },
    );
  }

  /** `clock` lets the app's notification service write a delivery's time on the device's clock (`deviceClockFields`). */
  payload(title: string, body: string, parcelId: string | null, clock: Record<string, string> = {}): JsonObject {
    const aps: JsonObject = {
      alert: { title, body },
      sound: 'default',
      badge: 1,
    };
    const payload: JsonObject = { aps };
    if (parcelId) {
      aps['thread-id'] = parcelId;
      payload.parcel_id = parcelId;
    }
    if (Object.keys(clock).length) {
      aps['mutable-content'] = 1;
      Object.assign(payload, clock);
    }
    return payload;
  }

  locale(row: JsonObject): Locale {
    return notificationLocale(stringField(row, 'locale'));
  }
}

/** The iPhone app's notifications, through APNs with a provider token. */
export class NativePushNotificationService extends AppPushNotificationService {
  static readonly EXPIRED_REASONS = new Set([
    'BadDeviceToken',
    'DeviceTokenNotForTopic',
    'Unregistered',
  ]);

  readonly transport = 'apns';
  #privateKey: KeyObject;
  #cachedToken: { token: string; issuedAt: number } | null = null;

  constructor(
    readonly client: SupabaseServiceClient,
    readonly teamId: string,
    readonly keyId: string,
    privateKey: string,
    readonly bundleId: string,
    readonly now: () => number = () => Date.now() / 1_000,
  ) {
    super();
    try {
      const key = createPrivateKey(privateKey.replaceAll('\\n', '\n').trim());
      if (
        key.asymmetricKeyType !== 'ec'
        || key.asymmetricKeyDetails?.namedCurve !== 'prime256v1'
      ) throw new TypeError('APNS_PRIVATE_KEY must be a P-256 private key');
      this.#privateKey = key;
    } catch (error) {
      throw new TypeError('APNS_PRIVATE_KEY must be a P-256 private key', { cause: error });
    }
  }

  async send(row: JsonObject, payload?: JsonObject, collapseId?: string): Promise<void> {
    const environment = stringField(row, 'environment') || 'production';
    const host = environment === 'development'
      ? 'api.sandbox.push.apple.com'
      : 'api.push.apple.com';
    const headers: Record<string, string> = {
      authorization: `bearer ${await this.providerToken()}`,
      'apns-topic': this.bundleId,
      'apns-push-type': 'alert',
      'apns-priority': '10',
      'apns-expiration': String(Math.floor(this.now()) + 86_400),
      'content-type': 'application/json',
    };
    const collapse = collapseKey(row, collapseId);
    if (collapse) headers['apns-collapse-id'] = collapse;
    const response = await postApns(
      host,
      `/3/device/${stringField(row, 'token')}`,
      headers,
      payload ?? this.eventPayload(row),
      15_000,
    );
    if (response.status !== 200) throw new APNsError(response.status, response.reason);
  }

  async providerToken(): Promise<string> {
    const issuedAt = Math.floor(this.now());
    if (this.#cachedToken && issuedAt - this.#cachedToken.issuedAt < 50 * 60) {
      return this.#cachedToken.token;
    }
    const token = await new SignJWT({})
      .setProtectedHeader({ alg: 'ES256', kid: this.keyId })
      .setIssuer(this.teamId)
      .setIssuedAt(issuedAt)
      .sign(this.#privateKey);
    this.#cachedToken = { token, issuedAt };
    return token;
  }

  isExpired(error: unknown): boolean {
    return error instanceof APNsError
      && (error.statusCode === 410 || NativePushNotificationService.EXPIRED_REASONS.has(error.reason));
  }
}

/**
 * The Android app's notifications, through Firebase Cloud Messaging. Each is a data message
 * the app writes itself (`fcmData`), sent at high priority, kept a day, and collapsed by the
 * key APNs gets as `apns-collapse-id`. Live Activities stay the iPhone's.
 */
export class FcmPushNotificationService extends AppPushNotificationService {
  readonly transport = 'fcm';
  readonly fcm: FcmClient;

  constructor(
    readonly client: SupabaseServiceClient,
    projectId: string,
    clientEmail: string,
    privateKey: string,
    readonly now: () => number = () => Date.now() / 1_000,
    fetcher: typeof fetch = fetch,
  ) {
    super();
    this.fcm = new FcmClient(projectId, clientEmail, privateKey, now, fetcher);
  }

  async send(row: JsonObject, payload?: JsonObject, collapseId?: string): Promise<void> {
    await this.fcm.send(stringField(row, 'token'), fcmData(payload ?? this.eventPayload(row)), collapseKey(row, collapseId));
  }

  isExpired(error: unknown): boolean {
    return fcmTokenGone(error);
  }
}

type LiveActivityDeliveryKind = 'start' | 'update' | 'end';

const LIVE_ACTIVITY_PHASES = new Set([
  'out_for_delivery',
  'exception',
  'delivered',
  'failed_attempt',
  'ready_for_pickup',
  'returned',
]);

export class DeliveryLiveActivityNotificationService {
  constructor(
    readonly client: SupabaseServiceClient,
    readonly apns: NativePushNotificationService,
  ) {}

  async dispatch(signal?: AbortSignal): Promise<PushSummary> {
    signal?.throwIfAborted();
    const grouped = new Map<string, JsonObject[]>();
    for (const row of await this.client.listPendingLiveActivityEvents()) {
      const key = JSON.stringify([stringField(row, 'device_id'), stringField(row, 'package_id')]);
      grouped.set(key, [...(grouped.get(key) ?? []), row]);
    }
    const summary = emptySummary();
    const known = await knownScans(this.client, [...grouped.values()]);
    const record = (events: readonly JsonObject[], kind: LiveActivityDeliveryKind) => this.client.recordLiveActivityDeliveries(
      events.map((event) => ({
        deviceId: stringField(event, 'device_id'),
        eventId: stringField(event, 'event_id'),
        packageId: stringField(event, 'package_id'),
        deliveryKind: kind,
        eventCreatedAt: stringField(event, 'event_created_at'),
      })),
    );
    for (const events of grouped.values()) {
      signal?.throwIfAborted();
      // A copy or repeat of a scan the parcel had moves no activity: one in another stage would end it.
      const newest = announcedScan(events, { known });
      if (!newest) {
        // Recorded as updates, the batch holds back no later start.
        await record(events, 'update');
        continue;
      }
      const kind = this.deliveryKind(newest);
      if (!kind) continue;
      const deviceId = stringField(newest, 'device_id');
      const updateTokenId = stringField(newest, 'update_token_id');
      summary.attempted += 1;
      try {
        await this.send(newest, kind);
        // Sent is recorded, even while the server stops: the scan must not go out twice.
        await record(events, kind);
        if (kind === 'end' && updateTokenId) {
          await this.client.deleteLiveActivityTokenById(updateTokenId);
        } else if (updateTokenId) {
          await this.client.updateLiveActivityToken(updateTokenId, {
            last_success_at: new Date().toISOString(),
            last_error: null,
          });
        } else {
          await this.client.updateLiveActivityDevice(deviceId, {
            last_success_at: new Date().toISOString(),
            last_error: null,
          });
        }
        summary.sent += 1;
      } catch (error) {
        signal?.throwIfAborted();
        if (this.apns.isExpired(error)) {
          if (updateTokenId) await this.client.deleteLiveActivityTokenById(updateTokenId);
          else {
            await this.client.updateLiveActivityDevice(deviceId, {
              disabled_at: new Date().toISOString(),
              last_error: 'ActivityKit token expired',
            });
          }
          summary.expired += 1;
        } else {
          logDeliveryFailure('live_activity', error);
          if (updateTokenId) {
            await this.client.updateLiveActivityToken(updateTokenId, {
              last_error: 'Live Activity delivery failed',
            });
          } else {
            await this.client.updateLiveActivityDevice(deviceId, {
              last_error: 'Live Activity delivery failed',
            });
          }
          summary.failed += 1;
        }
      }
    }
    return summary;
  }

  deliveryKind(row: JsonObject): LiveActivityDeliveryKind | null {
    const hasUpdateToken = Boolean(stringField(row, 'update_token'));
    if (stringField(row, 'stage') === 'out_for_delivery') {
      return hasUpdateToken ? 'update' : 'start';
    }
    return hasUpdateToken ? 'end' : null;
  }

  async send(row: JsonObject, kind = this.deliveryKind(row)): Promise<void> {
    if (!kind) return;
    const environment = stringField(row, 'environment') || 'production';
    const host = environment === 'development'
      ? 'api.sandbox.push.apple.com'
      : 'api.push.apple.com';
    const token = kind === 'start'
      ? stringField(row, 'push_to_start_token')
      : stringField(row, 'update_token');
    const response = await postApns(
      host,
      `/3/device/${token}`,
      {
        authorization: `bearer ${await this.apns.providerToken()}`,
        'apns-topic': `${this.apns.bundleId}.push-type.liveactivity`,
        'apns-push-type': 'liveactivity',
        'apns-priority': '10',
        'apns-expiration': String(Math.floor(this.apns.now()) + 3_600),
        'content-type': 'application/json',
      },
      this.payload(row, kind),
      15_000,
    );
    if (response.status !== 200) throw new APNsError(response.status, response.reason);
  }

  payload(row: JsonObject, kind: LiveActivityDeliveryKind): JsonObject {
    const timestamp = Math.floor(this.apns.now());
    const parcelId = stringField(row, 'package_id');
    const locale = this.apns.locale(row);
    const stage = stringField(row, 'stage');
    // The words the app writes on the activities it starts itself.
    const status = appWord(locale, `stage.${stage}`) ?? PUSH_COPY[locale].update!;
    // Out for delivery, only the hours the carrier gives for today.
    const expected = notificationExpectedDelivery(
      row.expected_delivery,
      locale,
      stringField(row, 'timezone') || 'Europe/Zurich',
      timestamp * 1_000,
      row.expected_delivery_from,
      stage,
      row.timezone_chosen === false,
    );
    const phase = LIVE_ACTIVITY_PHASES.has(stage) ? stage : 'ended';
    const contentState: JsonObject = {
      parcel: {
        id: parcelId,
        label: notificationText(row.label || appWord(locale, 'common.parcel'), 80),
        carrier: notificationText(carrierDisplayName(row.carrier, locale), 80),
        status: notificationText(status, 80),
        // Older app versions already suppress a detail equal to the status.
        detail: notificationText(stage === 'out_for_delivery' && expected ? expected : status, 100),
        phase,
      },
      languageCode: locale,
    };
    const aps: JsonObject = {
      timestamp,
      event: kind,
      'content-state': contentState,
      'relevance-score': stage === 'out_for_delivery' ? 0.8 : 1,
    };
    if (kind === 'start') {
      aps['attributes-type'] = 'DeliveryActivityAttributes';
      aps.attributes = { parcelID: parcelId };
      aps['input-push-token'] = 1;
      aps['stale-date'] = timestamp + 30 * 60;
      aps.alert = {
        title: notificationTitle(row, locale),
        body: notificationBody(row, locale, timestamp * 1_000),
      };
    } else if (kind === 'update') {
      aps['stale-date'] = timestamp + 30 * 60;
    } else {
      const graceSeconds = stage === 'failed_attempt' || stage === 'ready_for_pickup' || stage === 'exception'
        ? 60 * 60
        : LIVE_ACTIVITY_PHASES.has(stage) ? 30 * 60 : -1;
      aps['dismissal-date'] = timestamp + graceSeconds;
      aps.alert = {
        title: notificationTitle(row, locale),
        body: notificationBody(row, locale, timestamp * 1_000),
      };
    }
    return { aps };
  }
}

/** Logs what the push service said about a send that failed, without its endpoint or token. */
function logDeliveryFailure(channel: 'web' | 'native' | 'live_activity', error: unknown, transport?: NativeTransport): void {
  const status = typeof error === 'object' && error !== null && 'statusCode' in error ? Number(error.statusCode) : null;
  logOperationalEvent('push_delivery_failed', {
    channel,
    ...transport ? { transport } : {},
    status,
    reason: error instanceof APNsError || error instanceof FCMError || error instanceof FCMAuthError
      ? error.reason || null : null,
    error_type: errorType(error),
  }, 'warning');
}

/** Carries what was delivered when a channel, or a part of one, failed. */
export class PushDispatchError extends AggregateError {
  constructor(errors: unknown[], readonly summary: PushSummary) {
    super(errors, `${errors.length} push channel(s) failed`);
    this.name = 'PushDispatchError';
  }
}

export class CompositePushNotificationService {
  constructor(
    readonly web: WebPushNotificationService | null,
    readonly native: NativePushNotificationService | null,
    readonly liveActivities: DeliveryLiveActivityNotificationService | null,
    readonly linkAlerts: ParcelLinkAlertService | null = null,
    readonly fcm: FcmPushNotificationService | null = null,
    /** Taken for each dispatch, so that two servers never send the same scans. */
    readonly lease: WorkerLease | null = null,
  ) {}

  /**
   * Sends what every channel has waiting. While another server dispatches, as the old one of a
   * deploy may, this one sends nothing and the scans wait for its next dispatch.
   */
  async dispatch(signal?: AbortSignal): Promise<PushSummary> {
    signal?.throwIfAborted();
    if (!this.lease) return await this.dispatchChannels(signal);
    const summary = await this.lease.run((held) => this.dispatchChannels(held), signal);
    if (summary) return summary;
    logOperationalEvent('push_dispatch_skipped', { reason: 'lease_held' });
    return { ...emptySummary(), sentByChannel: {} };
  }

  /** Gives the dispatch lease up once the dispatch in progress, if any, has ended. */
  async releaseLease(): Promise<void> {
    await this.lease?.stop();
  }

  private async dispatchChannels(signal?: AbortSignal): Promise<PushSummary> {
    const combined: PushSummary & { sentByChannel: Partial<Record<PushChannel, number>> } = { ...emptySummary(), sentByChannel: {} };
    const errors: unknown[] = [];
    // Accounts first: a parcel link's alerts come on top of its owner's notifications. The iPhone's
    // and Android's sends both count as native.
    const channels = [
      ['live_activity', this.liveActivities], ['web', this.web], ['native', this.native], ['native', this.fcm],
      ['link_alert', this.linkAlerts],
    ] as const;
    const add = (channel: PushChannel, summary: PushSummary) => {
      combined.attempted += summary.attempted;
      combined.sent += summary.sent;
      combined.failed += summary.failed;
      combined.expired += summary.expired;
      combined.sentByChannel[channel] = (combined.sentByChannel[channel] ?? 0) + summary.sent;
    };
    for (const [channel, service] of channels) {
      if (!service) continue;
      try {
        add(channel, await service.dispatch(signal));
      } catch (error) {
        signal?.throwIfAborted();
        // One channel's outage or schema drift must not hold back the others. What a channel sent before it
        // failed still counts.
        if (error instanceof PushDispatchError) add(channel, error.summary);
        errors.push(error);
      }
    }
    if (errors.length > 0) throw new PushDispatchError(errors, combined);
    return combined;
  }
}

interface PushRuntime {
  signature: string;
  client: SupabaseServiceClient;
  service: CompositePushNotificationService;
}

const globalPush = globalThis as typeof globalThis & {
  __deliveryPushRuntime?: PushRuntime;
};

export function pushServices(client: SupabaseServiceClient): CompositePushNotificationService {
  const webValues = {
    publicKey: process.env.VAPID_PUBLIC_KEY?.trim() ?? '',
    privateKey: process.env.VAPID_PRIVATE_KEY?.trim() ?? '',
    // Push services ask who sends: the site itself, unless a contact is set.
    subject: process.env.VAPID_SUBJECT?.trim() || process.env.CANONICAL_ORIGIN?.trim() || 'https://peektracker.com',
  };
  const nativeValues = {
    teamId: process.env.APNS_TEAM_ID?.trim() ?? '',
    keyId: process.env.APNS_KEY_ID?.trim() ?? '',
    privateKey: process.env.APNS_PRIVATE_KEY?.trim() ?? '',
    bundleId: process.env.APNS_BUNDLE_ID?.trim() ?? '',
  };
  const fcmValues = {
    projectId: process.env.FCM_PROJECT_ID?.trim() ?? '',
    clientEmail: process.env.FCM_CLIENT_EMAIL?.trim() ?? '',
    privateKey: process.env.FCM_PRIVATE_KEY?.trim() ?? '',
  };
  if (Boolean(webValues.publicKey) !== Boolean(webValues.privateKey)) {
    throw new Error('VAPID_PUBLIC_KEY and VAPID_PRIVATE_KEY must be configured together');
  }
  if (Object.values(nativeValues).some(Boolean) && !Object.values(nativeValues).every(Boolean)) {
    throw new Error('APNS_TEAM_ID, APNS_KEY_ID, APNS_PRIVATE_KEY and APNS_BUNDLE_ID are all required');
  }
  if (Object.values(fcmValues).some(Boolean) && !Object.values(fcmValues).every(Boolean)) {
    throw new Error('FCM_PROJECT_ID, FCM_CLIENT_EMAIL and FCM_PRIVATE_KEY are all required');
  }
  const signature = JSON.stringify([webValues, nativeValues, fcmValues]);
  if (
    globalPush.__deliveryPushRuntime?.signature !== signature
    || globalPush.__deliveryPushRuntime.client !== client
  ) {
    let web: WebPushNotificationService | null = null;
    if (webValues.publicKey && webValues.privateKey) {
      try {
        webpush.getVapidHeaders(
          'https://push.example.test',
          webValues.subject,
          webValues.publicKey,
          webValues.privateKey,
          'aes128gcm',
        );
      } catch (error) {
        throw new Error('VAPID keys are invalid', { cause: error });
      }
      web = new WebPushNotificationService(
        client,
        webValues.publicKey,
        webValues.privateKey,
        webValues.subject,
      );
    }
    const native = Object.values(nativeValues).every(Boolean)
      ? new NativePushNotificationService(
          client,
          nativeValues.teamId,
          nativeValues.keyId,
          nativeValues.privateKey,
          nativeValues.bundleId,
        )
      : null;
    const fcm = Object.values(fcmValues).every(Boolean)
      ? new FcmPushNotificationService(client, fcmValues.projectId, fcmValues.clientEmail, fcmValues.privateKey)
      : null;
    globalPush.__deliveryPushRuntime = {
      signature,
      client,
      service: new CompositePushNotificationService(
        web,
        native,
        native ? new DeliveryLiveActivityNotificationService(client, native) : null,
        web || native || fcm ? new ParcelLinkAlertService(web, native, fcm) : null,
        fcm,
        new WorkerLease(client, 'push_dispatch'),
      ),
    };
  }
  return globalPush.__deliveryPushRuntime.service;
}

export function pushConfigurationError(error: unknown): Error {
  return new Error(`Push notification configuration failed: ${errorMessage(error)}`, {
    cause: error,
  });
}
