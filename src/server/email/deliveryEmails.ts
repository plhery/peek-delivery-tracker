import 'server-only';

import type { ApiPackageRow } from '../../generated/apiContract';
import { tagLocale, type Locale } from '../../lib/locale';
import { withEventPlaces } from '../eventPlaces';
import { recordDeliveryEmail, type DeliveryEmailReason } from '../metrics';
import { captureOperationalError, errorType, logOperationalEvent } from '../observability';
import type { DeliveryEmailClaim, SupabaseServiceClient } from '../supabase';
import type { JsonObject } from '../types';
import { emailSettings, undeliverableAddress, type EmailSettings } from './config';
import { deliveryEmailContent } from './content';
import { EmailSendError, openTransport, type EmailTransport, type OutgoingEmail } from './transport';
import { DELIVERY_CARD_CID, type DeliveryEmailContent } from './types';
import { unsubscribeUrls } from './unsubscribe';

/** Emails one run claims, one at a time. The rest wait for the next run, which follows every sync job. */
export const CLAIMS_PER_RUN = 20;

export interface DeliveryEmailSummary {
  sent: number;
  failed: number;
  skipped: number;
}

type Ending =
  | { outcome: 'sent' }
  | { outcome: 'skipped'; reason: DeliveryEmailReason }
  | { outcome: 'failed'; reason: DeliveryEmailReason; error?: unknown };

function accountLocale(stored: string | null): Locale {
  return (stored && tagLocale(stored)) || 'en';
}

/**
 * Tells accounts by email that a parcel is ready to collect, and that it was
 * delivered: at most one email per parcel for each. The database decides which
 * scans are news and hands each parcel out once per stage (see
 * `claim_delivery_emails`); this writes the email, sends it and records how it
 * ended. An email that could not be sent is claimed again later, up to three
 * times while its scan is fresh. One email that fails never stops the others.
 */
export class DeliveryEmailService {
  constructor(
    readonly client: SupabaseServiceClient,
    readonly settings: EmailSettings,
    readonly open: (settings: EmailSettings) => EmailTransport = openTransport,
    readonly now: () => Date = () => new Date(),
  ) {}

  /**
   * Claims and sends one email at a time, up to `CLAIMS_PER_RUN`, until a claim comes back
   * empty: a server killed mid-run leaves at most the email it was sending claimed, and that
   * one is never sent again, since it may have gone out.
   */
  async dispatch(signal?: AbortSignal): Promise<DeliveryEmailSummary> {
    signal?.throwIfAborted();
    const summary: DeliveryEmailSummary = { sent: 0, failed: 0, skipped: 0 };
    const capped = { account_cap: 0, service_cap: 0 };
    const reported = new Set<string>();
    let transport: EmailTransport | null = null;
    try {
      for (let claims = 0; claims < CLAIMS_PER_RUN && !signal?.aborted; claims += 1) {
        const claimed = await this.client.claimDeliveryEmails(1, this.settings.perAccountPerDay, this.settings.perDay);
        // Beyond an allowance, an email is recorded as skipped by the claim itself.
        capped.account_cap += claimed.accountCap;
        capped.service_cap += claimed.serviceCap;
        if (claimed.send.length === 0) {
          if (claimed.accountCap + claimed.serviceCap > 0) continue;
          break;
        }
        transport ??= this.open(this.settings);
        for (const claim of claimed.send) await this.handle(claim, transport, summary, reported, signal);
      }
    } finally {
      transport?.close();
      for (const [reason, total] of Object.entries(capped) as [keyof typeof capped, number][]) {
        if (total <= 0) continue;
        summary.skipped += total;
        recordDeliveryEmail('skipped', reason, total);
        logOperationalEvent('delivery_emails_capped', { reason, emails: total }, 'warning');
      }
    }
    signal?.throwIfAborted();
    return summary;
  }

  /** Sends one claimed email and records how it ended. */
  private async handle(
    claim: DeliveryEmailClaim,
    transport: EmailTransport,
    summary: DeliveryEmailSummary,
    reported: Set<string>,
    signal?: AbortSignal,
  ): Promise<void> {
    // A run stopped since the claim hands the email back, to be claimed again.
    const ending: Ending = signal?.aborted
      ? { outcome: 'failed', reason: 'interrupted' }
      : await this.deliver(claim, transport);
    summary[ending.outcome] += 1;
    this.record(claim, ending, reported);
    try {
      await this.client.finishDeliveryEmail(claim.id, ending.outcome, ending.outcome === 'sent' ? null : ending.reason);
    } catch (error) {
      // The claim stays open and is never handed out again: no email goes out twice.
      logOperationalEvent('delivery_email_finish_failed', {
        package_id: claim.packageId, outcome: ending.outcome, error_type: errorType(error),
      }, 'error');
      if (!signal?.aborted) captureOperationalError(error, { component: 'delivery-email', operation: 'finish' });
    }
  }

  private async deliver(claim: DeliveryEmailClaim, transport: EmailTransport): Promise<Ending> {
    let account: Awaited<ReturnType<SupabaseServiceClient['getAuthAccount']>>;
    try {
      account = await this.client.getAuthAccount(claim.userId);
    } catch (error) {
      return { outcome: 'failed', reason: 'account', error };
    }
    const address = account?.email ?? null;
    const undeliverable = undeliverableAddress(this.settings, address, account?.emailConfirmed === true);
    if (undeliverable || address === null) return { outcome: 'skipped', reason: undeliverable ?? 'no_address' };

    let parcel: JsonObject | null;
    try {
      parcel = await this.client.getOwnedPackage(claim.packageId, claim.userId);
    } catch (error) {
      return { outcome: 'failed', reason: 'parcel', error };
    }
    if (!parcel) return { outcome: 'skipped', reason: 'parcel_gone' };

    let message: OutgoingEmail;
    try {
      const locale = accountLocale(account?.locale ?? null);
      // The page its "turn it off" link opens speaks the email's language.
      const links = unsubscribeUrls(this.settings.origin, claim.userId, locale);
      const content = await deliveryEmailContent({
        parcel: withEventPlaces(parcel) as unknown as ApiPackageRow,
        locale,
        stage: claim.stage,
        timezone: claim.timezone,
        // What a notification opens.
        journeyUrl: `${this.settings.origin}/?parcel=${encodeURIComponent(claim.packageId)}`,
        offUrl: links.page,
        deliveredTime: claim.deliveredTime,
        now: this.now(),
      });
      message = deliveryEmail(address, content, links.oneClick);
    } catch (error) {
      return { outcome: 'failed', reason: 'content', error };
    }

    try {
      await transport.send(message);
    } catch (error) {
      return { outcome: 'failed', reason: 'smtp', error };
    }
    return { outcome: 'sent' };
  }

  /** Counts and logs one email by its parcel and how it ended: never by its address, its subject or the parcel's name. */
  private record(claim: DeliveryEmailClaim, ending: Ending, reported: Set<string>): void {
    const reason = ending.outcome === 'sent' ? 'none' : ending.reason;
    recordDeliveryEmail(ending.outcome, reason);
    const error = ending.outcome === 'failed' ? ending.error : undefined;
    logOperationalEvent('delivery_email', {
      package_id: claim.packageId,
      stage: claim.stage,
      outcome: ending.outcome,
      reason,
      ...(error === undefined ? {} : { error_type: errorType(error) }),
      ...(error instanceof EmailSendError ? { error_code: error.code, smtp_status: error.smtpStatus } : {}),
    }, ending.outcome === 'failed' ? 'error' : 'info');
    // An outage fails every email of a run the same way: one report per kind of failure.
    if (error === undefined || reported.has(reason)) return;
    reported.add(reason);
    captureOperationalError(error, { component: 'delivery-email', operation: reason });
  }
}

/**
 * The email as it is sent. The two `List-Unsubscribe` headers give a mail app
 * its own "Unsubscribe": it posts to the address, which must be HTTPS (RFC
 * 8058). The others ask robots, such as out-of-office replies, not to answer.
 * The card travels inside the email, so nothing is fetched when it is read.
 */
export function deliveryEmail(address: string, content: DeliveryEmailContent, oneClickUrl: string): OutgoingEmail {
  return {
    to: address,
    subject: content.subject.replace(/\s+/g, ' ').trim(),
    text: content.text,
    html: content.html,
    headers: {
      ...(oneClickUrl.startsWith('https://') ? {
        'List-Unsubscribe': `<${oneClickUrl}>`,
        'List-Unsubscribe-Post': 'List-Unsubscribe=One-Click',
      } : {}),
      'Auto-Submitted': 'auto-generated',
      'X-Auto-Response-Suppress': 'All',
    },
    inline: content.card
      ? [{ cid: DELIVERY_CARD_CID, filename: 'parcel.png', contentType: 'image/png', content: content.card }]
      : [],
  };
}

/** The service for this deployment, or null when it has no mail settings. A malformed set throws. */
export function deliveryEmailService(client: SupabaseServiceClient): DeliveryEmailService | null {
  const settings = emailSettings();
  return settings ? new DeliveryEmailService(client, settings) : null;
}
