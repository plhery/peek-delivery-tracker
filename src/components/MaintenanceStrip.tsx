import { useI18n, type MessageKey } from '../i18n';
import type { MaintenanceNotice } from '../lib/maintenance';
import type { TrackingEvent } from '../types';
import { momentLabel } from '../peek/parcel/summary';
import { Icon } from './Icon';

type Wording = Parameters<typeof momentLabel>[1];
/** A moment: an ISO time, or a scan, which may give its day alone. */
type Moment = string | Pick<TrackingEvent, 'occurredAt' | 'day'>;

const KEYS = {
  list: ['maintenance.list', 'maintenance.listPlanned'],
  parcel: ['maintenance.parcel', 'maintenance.parcelPlanned'],
  short: ['maintenance.short', 'maintenance.shortPlanned'],
} as const satisfies Record<string, readonly [MessageKey, MessageKey]>;

/**
 * What the strip says: that Peek is down for a few minutes, or until when an announced maintenance runs, and how old
 * what is shown below is. Without anything shown, or a time for it, only the first part.
 */
export function maintenanceText(notice: MaintenanceNotice, at: Moment | null, kind: 'list' | 'parcel', wording: Wording): string {
  const time = at ? momentLabel(at, wording) : '';
  const back = notice.planned && notice.backAround ? momentLabel(notice.backAround, wording) : '';
  const [unplanned, planned] = KEYS[time ? kind : 'short'];
  return back ? wording.t(planned, { back, time }) : wording.t(unplanned, { time });
}

/**
 * The calm notice shown in place of an error while the server is down for maintenance: what is shown stays, and
 * the screen asks again by itself, so there is nothing to press.
 */
export function MaintenanceStrip({ notice, at, kind = 'list', className = 'maintenance-strip' }: {
  notice: MaintenanceNotice;
  /** When what is shown was last read from the server, or the scan it shows last; null when nothing is shown. */
  at: Moment | null;
  kind?: 'list' | 'parcel';
  className?: string;
}) {
  const { t, languageTag, use24HourTime } = useI18n();
  return <p className={className} role="status" data-maintenance={notice.planned ? 'planned' : 'unplanned'}>
    <Icon name="clock" aria-hidden="true" />
    <span>{maintenanceText(notice, at, kind, { t, languageTag, use24HourTime })}</span>
  </p>;
}
