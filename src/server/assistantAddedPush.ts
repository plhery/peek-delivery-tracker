import 'server-only';
import { assistantAddedNotificationCopy, WEB_NOTIFICATION_PICTURES } from './push';
import { QueuedPushService, QueuedPushWorker, type PushTransports } from './queuedPush';
import type { JsonObject } from './types';

/** "Added by Claude": the parcel's name and carrier, opening the parcel as its other notifications do. */
export function assistantAddedNotification(row: JsonObject): { web: JsonObject; native: JsonObject } {
  const { locale, title, body } = assistantAddedNotificationCopy(row.locale, row.assistant, row.label, row.carrier);
  const id = String(row.package_id);
  return {
    web: { title, body, lang: locale, ...WEB_NOTIFICATION_PICTURES, tag: `parcel-${id}`, data: { url: `/?parcel=${id}` } },
    native: { aps: { alert: { title, body }, sound: 'default', badge: 1, 'thread-id': id }, parcel_id: id },
  };
}

/**
 * Each parcel an assistant added, to each browser and phone of its account (`parcels_added_by_assistant`), unless the
 * account switched it off on the Assistants page. The database drops one for a parcel opened, archived or gone, and
 * those of an assistant disconnected.
 */
export class AssistantAddedPushService extends QueuedPushService {
  protected claim({ web, native, fcm, unifiedpush, inbox }: PushTransports) {
    return this.client.claimAssistantAddedPush(web, native, fcm, unifiedpush, inbox);
  }
  protected finish(id: string, lease: string, success: boolean) { return this.client.finishAssistantAddedPush(id, lease, success); }
  protected notification(row: JsonObject) { return assistantAddedNotification(row); }
}

export class AssistantAddedPushWorker extends QueuedPushWorker {
  constructor(service: Pick<QueuedPushService, 'dispatch'>) { super(service, 'assistant-added-notifications', 'assistant_added_notification_failed'); }
}
