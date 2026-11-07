/**
 * A carrier's notices in the stage of its scan before them.
 *
 * An adapter gives a notice to the recipient, such as DPD Poland's "Mail notification", the stage
 * of the scan before it, with `stage_source` `none`, and the sync stores it in that stage
 * (`scanStages`, disputedDelivery.ts). It tells the reader nothing new: the apps show it as a line
 * under that scan (`relay_of`, eventPlaces.ts), so the headline and its time stay on the scan, and
 * it announces nothing, at the check that brings it or a later one (push.ts). A scan in transit is
 * none of them: one no rule reads is in transit in `none` too, and news that the parcel moves.
 */
import { scanSource } from './scanRepeats';
import type { JsonObject } from './types';

export type NoticeScan = { id: string; source: string; stage: string; stageSource: string | null; at: number };

/** Stages a notice in `none` can't be told apart in, or that no stage of a scan's own is in. */
const NOT_NOTICES = new Set(['pending', 'in_transit']);

/** A stored or served row as `stageNotices` reads it. */
export function noticeScan(event: JsonObject): NoticeScan {
  return {
    id: String(event.id), source: scanSource(event.provider_event_id, event.universal_provider), stage: String(event.stage ?? ''),
    stageSource: typeof event.stage_source === 'string' ? event.stage_source : null, at: Date.parse(String(event.occurred_at)),
  };
}

/**
 * Each notice in the stage of its scan before it, with that scan's id: the newest scan of the
 * notice's source in a stage dated at or before it, other such notices aside (a mail notice and a
 * text message of one instant both go under the scan). Peek's own rows are neither.
 */
export function stageNotices(scans: readonly NoticeScan[]): Map<string, string> {
  const ordered = scans.filter((scan) => Number.isFinite(scan.at)).sort((a, b) => a.at - b.at || a.id.localeCompare(b.id));
  const notices = new Map<string, string>();
  for (const notice of ordered) {
    if (notice.stageSource !== 'none' || notice.source === '' || notice.source === 'app' || NOT_NOTICES.has(notice.stage)) continue;
    let lead: NoticeScan | undefined;
    for (const scan of ordered) {
      if (scan.at > notice.at) break;
      if (scan === notice || scan.source !== notice.source || scan.stage === 'pending' || notices.has(scan.id)) continue;
      // Another notice of the same instant in the same stage goes under the scan too.
      if (scan.at === notice.at && scan.stageSource === 'none' && scan.stage === notice.stage) continue;
      if (!lead || scan.at > lead.at || (scan.at === lead.at && scan.stage === notice.stage)) lead = scan;
    }
    if (lead?.stage === notice.stage) notices.set(notice.id, lead.id);
  }
  return notices;
}
