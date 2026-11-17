import 'server-only';
import type {
  FcmPushNotificationService, InboxPushNotificationService, NativePushNotificationService,
  UnifiedPushNotificationService, WebPushNotificationService, PushSummary,
} from './push';
import type { SupabaseServiceClient } from './supabase';
import type { JsonObject } from './types';
import { captureOperationalError, databaseUnavailable, inBackgroundScope, logOperationalEvent, errorType } from './observability';

/** Which transports a server sends: a claim hands it only deliveries it can send. */
export interface PushTransports { web: boolean; native: boolean; fcm: boolean; unifiedpush: boolean; inbox: boolean }

/**
 * A notification queued in the database, one row per browser or phone, sent to a browser, an iPhone through APNs or
 * an Android phone through FCM, UnifiedPush or the inbox. A claim leases its rows, so the app's replicas never send
 * one twice, and each is finished with its lease: sent, or released for a later retry.
 */
export abstract class QueuedPushService {
  constructor(
    readonly client: SupabaseServiceClient,
    readonly web: WebPushNotificationService | null,
    readonly native: NativePushNotificationService | null,
    readonly fcm: FcmPushNotificationService | null = null,
    readonly unifiedpush: UnifiedPushNotificationService | null = null,
    readonly inbox: InboxPushNotificationService | null = null,
  ) {}
  protected abstract claim(transports: PushTransports): Promise<JsonObject[]>;
  protected abstract finish(id: string, lease: string, success: boolean): Promise<void>;
  protected abstract notification(row: JsonObject): { web: JsonObject; native: JsonObject };
  private app(transport: unknown) {
    if (transport === 'fcm') return this.fcm;
    if (transport === 'unifiedpush') return this.unifiedpush;
    return transport === 'inbox' ? this.inbox : this.native;
  }
  async dispatch(): Promise<PushSummary> {
    const summary: PushSummary = { attempted: 0, sent: 0, failed: 0, expired: 0 };
    if (!this.web && !this.native && !this.fcm && !this.unifiedpush && !this.inbox) return summary;
    const deliveries = await this.claim({
      web: !!this.web, native: !!this.native, fcm: !!this.fcm, unifiedpush: !!this.unifiedpush, inbox: !!this.inbox,
    });
    // Small leased batches run concurrently; each delivery retains its own acknowledgement.
    await Promise.all(deliveries.map(async (row) => {
      summary.attempted++;
      const payload = this.notification(row);
      let success = false;
      // The claim returns a device only to a server that sends its transport.
      const app = row.device_id ? this.app(row.transport) : null;
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
        await this.finish(String(row.id), String(row.lease_token), success);
      }
    }));
    return summary;
  }
}

/**
 * Sends a queue's notifications every 15 seconds, and at once when woken, independently of parcel sync: quiet-hour
 * and failed ones retry here. `job` names the work in Sentry and the logs.
 */
export class QueuedPushWorker {
  private timer: ReturnType<typeof setTimeout> | null = null;
  private running = false;
  private stopped = false;
  constructor(readonly service: Pick<QueuedPushService, 'dispatch'>, readonly job: string, readonly failure: string) {}
  start() { this.stopped = false; if (!this.timer && !this.running) this.schedule(15_000); }
  wake() { if (this.running || this.stopped) return; if (this.timer) clearTimeout(this.timer); this.schedule(0); }
  stop() { this.stopped = true; if (this.timer) clearTimeout(this.timer); this.timer = null; }
  private schedule(delay: number) {
    this.timer = setTimeout(() => { this.timer = null; void inBackgroundScope(this.job, () => this.run()); }, delay);
    this.timer.unref();
  }
  private async run() {
    this.running = true;
    try { await this.service.dispatch(); }
    catch (error) {
      logOperationalEvent(this.failure, { error_type: errorType(error) }, 'error');
      // The sync worker reports a database that stays unreachable.
      if (!databaseUnavailable(error)) captureOperationalError(error, { component: this.job, operation: 'dispatch' });
    } finally { this.running = false; if (!this.stopped) this.schedule(15_000); }
  }
}
