import 'server-only';
import { friendNotificationCopy, WEB_NOTIFICATION_PICTURES } from './push';
import { QueuedPushService, QueuedPushWorker, type PushTransports } from './queuedPush';
import type { JsonObject } from './types';

export function friendshipNotification(row: JsonObject): { web: JsonObject; native: JsonObject } {
  const { locale, title, body } = friendNotificationCopy(row.locale, String(row.nickname ?? ''));
  const id = String(row.friend_id);
  return {
    web: { title, body, lang: locale, ...WEB_NOTIFICATION_PICTURES,
      tag: `friend-${id}`, data: { url: `/?view=friends&friend=${encodeURIComponent(id)}` } },
    native: { aps: { alert: { title, body }, sound: 'default', badge: 1, 'thread-id': 'friends' }, kind: 'friend_accepted', friend_id: id },
  };
}

/** A friendship's notice, to each browser and phone of the friend who sent the invitation. */
export class FriendshipPushService extends QueuedPushService {
  protected claim({ web, native, fcm, unifiedpush, inbox }: PushTransports) {
    return this.client.claimFriendshipPush(web, native, fcm, unifiedpush, inbox);
  }
  protected finish(id: string, lease: string, success: boolean) { return this.client.finishFriendshipPush(id, lease, success); }
  protected notification(row: JsonObject) { return friendshipNotification(row); }
}

export class FriendshipPushWorker extends QueuedPushWorker {
  constructor(service: Pick<QueuedPushService, 'dispatch'>) { super(service, 'friendship-notifications', 'friendship_notification_failed'); }
}
