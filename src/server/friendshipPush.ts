import 'server-only';
import {
  friendNotificationCopy, WEB_NOTIFICATION_PICTURES,
  type FcmPushNotificationService, type NativePushNotificationService, type WebPushNotificationService, type PushSummary,
} from './push';
import type { SupabaseServiceClient } from './supabase';
import type { JsonObject } from './types';
import { captureOperationalError, databaseUnavailable, inBackgroundScope, logOperationalEvent, errorType } from './observability';

export function friendshipNotification(row: JsonObject): { web: JsonObject; native: JsonObject } {
  const { locale, title, body } = friendNotificationCopy(row.locale, String(row.nickname ?? ''));
  const id = String(row.friend_id);
  return {
    web: { title, body, lang: locale, ...WEB_NOTIFICATION_PICTURES,
      tag: `friend-${id}`, data: { url: `/?view=friends&friend=${encodeURIComponent(id)}` } },
    native: { aps: { alert: { title, body }, sound: 'default', badge: 1, 'thread-id': 'friends' }, kind: 'friend_accepted', friend_id: id },
  };
}

/** A friendship's notice, to a browser, an iPhone through APNs or an Android phone through FCM. */
export class FriendshipPushService {
  constructor(
    readonly client: SupabaseServiceClient,
    readonly web: WebPushNotificationService | null,
    readonly native: NativePushNotificationService | null,
    readonly fcm: FcmPushNotificationService | null = null,
  ) {}
  async dispatch(): Promise<PushSummary> {
    const summary: PushSummary = { attempted: 0, sent: 0, failed: 0, expired: 0 };
    if (!this.web && !this.native && !this.fcm) return summary;
    const deliveries = await this.client.claimFriendshipPush(!!this.web, !!this.native, !!this.fcm);
    // Small leased batches run concurrently; each delivery retains its own acknowledgement.
    await Promise.all(deliveries.map(async (row) => {
      summary.attempted++;
      const payload = friendshipNotification(row);
      let success = false;
      // The claim returns a device only to a server that sends its transport.
      const app = !row.device_id ? null : row.transport === 'fcm' ? this.fcm : this.native;
      try {
        if (row.device_id) await app!.send(row, payload.native);
        else await this.web!.send(row, payload.web);
        success = true; summary.sent++;
      } catch (error) {
        const status = typeof error === 'object' && error && 'statusCode' in error ? Number(error.statusCode) : 0;
        if (row.device_id ? app!.isExpired(error) : status === 404 || status === 410) {
          const values = { disabled_at: new Date().toISOString(), last_error: 'Notification destination expired' };
          if (row.device_id) await this.client.updateNativePushDevice(String(row.device_id), values);
          else await this.client.updatePushSubscription(String(row.subscription_id), values);
          summary.expired++;
        } else { summary.failed++; }
      } finally {
        await this.client.finishFriendshipPush(String(row.id), String(row.lease_token), success);
      }
    }));
    return summary;
  }
}

/** Runs independently of parcel sync; quiet-hour and failed receipts retry here. */
export class FriendshipPushWorker {
  private timer: ReturnType<typeof setTimeout> | null = null;
  private running = false;
  private stopped = false;
  constructor(readonly service: FriendshipPushService) {}
  start() { this.stopped = false; if (!this.timer && !this.running) this.schedule(15_000); }
  wake() { if (this.running || this.stopped) return; if (this.timer) clearTimeout(this.timer); this.schedule(0); }
  stop() { this.stopped = true; if (this.timer) clearTimeout(this.timer); this.timer = null; }
  private schedule(delay: number) {
    this.timer = setTimeout(() => { this.timer = null; void inBackgroundScope('friendship-notifications', () => this.run()); }, delay);
    this.timer.unref();
  }
  private async run() {
    this.running = true;
    try { await this.service.dispatch(); }
    catch (error) {
      logOperationalEvent('friendship_notification_failed', { error_type: errorType(error) }, 'error');
      // The sync worker reports a database that stays unreachable.
      if (!databaseUnavailable(error)) captureOperationalError(error, { component: 'friendship-notifications', operation: 'dispatch' });
    } finally { this.running = false; if (!this.stopped) this.schedule(15_000); }
  }
}
