import 'server-only';
import { emailAddedNotificationCopy, WEB_NOTIFICATION_PICTURES } from './push';
import { QueuedPushService, QueuedPushWorker, type PushTransports } from './queuedPush';
import type { JsonObject } from './types';

/** "Added from your email": the parcel's name and carrier, opening the parcel as its other notifications do. */
export function emailAddedNotification(row: JsonObject): { web: JsonObject; native: JsonObject } {
  const { locale, title, body } = emailAddedNotificationCopy(row.locale, row.label, row.carrier);
  const id = String(row.package_id);
  return {
    web: { title, body, lang: locale, ...WEB_NOTIFICATION_PICTURES, tag: `parcel-${id}`, data: { url: `/?parcel=${id}` } },
    native: { aps: { alert: { title, body }, sound: 'default', badge: 1, 'thread-id': id }, parcel_id: id },
  };
}

/**
 * Each parcel an email added, to each browser and phone of its account (`parcels_added_by_email`), unless the account
 * switched it off on the "Add parcels by email" page. The database drops one for a parcel opened, archived or gone.
 */
export class EmailAddedPushService extends QueuedPushService {
  protected claim({ web, native, fcm, unifiedpush, inbox }: PushTransports) {
    return this.client.claimEmailAddedPush(web, native, fcm, unifiedpush, inbox);
  }
  protected finish(id: string, lease: string, success: boolean) { return this.client.finishEmailAddedPush(id, lease, success); }
  protected notification(row: JsonObject) { return emailAddedNotification(row); }
}

export class EmailAddedPushWorker extends QueuedPushWorker {
  constructor(service: Pick<QueuedPushService, 'dispatch'>) { super(service, 'email-added-notifications', 'email_added_notification_failed'); }
}
