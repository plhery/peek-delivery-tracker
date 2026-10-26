import { createHash } from 'node:crypto';
import { carrierResult } from '../test/carrierResults';
import { CarrierError, ChallengeError } from 'universal-parcel-scraper';
import { REGISTRY } from 'universal-parcel-scraper/node';
import { afterEach, describe, expect, it, vi } from 'vitest';
import { idlePollDelay, secondsUntilNextSync, syncWindowStart, workerPollDelay } from './background';
import { deployedVersion } from './reviewQueues';
import { normalizeCarrierResult, type CarrierResult } from 'universal-parcel-scraper';
import { STORED_EVENT_IDENTITIES, type SupabaseServiceClient } from './supabase';
import { AdapterRegistry, type AdapterEnvironment } from 'universal-parcel-scraper/node';
import type { StepRecorder } from 'universal-parcel-scraper/node';
import type { UniversalTracker } from 'universal-parcel-scraper/node';
import { eventTimestamp } from 'universal-parcel-scraper/app';
import {
  CarrierTrackingAdapter,
  buildEvents,
  carrierCopies,
  classifyStage,
  collectMappedStatusKeys,
  collectStatusObservations,
  detectSyncAnomalies,
  fairSyncPackages,
  heldStage,
  inferStage,
  MAX_STATUS_OBSERVATIONS_PER_SYNC,
  isOpenedParcelSyncDue,
  isTrackingSyncDue,
  isUnannouncedTrackingError,
  providerEventId,
  restagedNews,
  resultHasUpdate,
  scansMissing,
  resultStage,
  stageToSave,
  TrackingSyncService,
  type TrackingAdapter,
} from './trackingSync';
import type { JsonObject } from './types';
import * as observability from './observability';
import * as metrics from './metrics';
import { catalogRules, recognizers, routingFailure, TrackingRouter, type RoutedResult } from './trackingRouting';
import { WorkerShutdown } from './trackingAudit';
import { UniversalTrackingError } from 'universal-parcel-scraper/node';
import { UpstreamHttpError } from 'universal-parcel-scraper/node';
import { NOOP_RECORDER } from 'universal-parcel-scraper/node';
import { healthStepRecorder } from './trackingHealth';
import { BudgetExceededError, IndeterminateError, InputRequiredError, InvalidInputError, NoHistoryError, NotFoundError, SchemaError } from 'universal-parcel-scraper';
import * as scraper from 'universal-parcel-scraper';
import { doubled, s10 } from '../test/trackingNumbers';

// Keep the published plan configurable without changing the scraper's other exports.
vi.mock(import('universal-parcel-scraper'), async importOriginal => ({ ...await importOriginal() }));

afterEach(() => vi.restoreAllMocks());

// A parcel's hour opens at a minute its number gives: on the hour for this one, at :48 for TEST1234.
const ON_THE_HOUR = 'TEST0067';

describe('dedicated carrier dispatch', () => {
  it('dispatches Royal Mail through its dedicated registry adapter', async () => {
    const universal = { fetch: vi.fn() };
    const adapter = new CarrierTrackingAdapter(universal as unknown as UniversalTracker);
    const track = vi.spyOn(adapter.registry.for('royal-mail')!, 'track').mockResolvedValue({ status: 'in_transit' });

    await expect(adapter.fetch('royal-mail', 'SG999999999GB', null)).resolves.toMatchObject({ status: 'in_transit' });
    expect(track).toHaveBeenCalledExactlyOnceWith({ number: 'SG999999999GB', trackingUrl: null, postcode: null });
    expect(universal.fetch).not.toHaveBeenCalled();
    expect(adapter.registry.has('royal-mail')).toBe(true);
  });

  it.each(['la-poste', 'chronopost', 'gls-fr', 'colis-prive', 'geodis', 'dpd-fr', 'mondial-relay', 'relais-colis', 'swiss-post-cargo', 'gls-ch', 'colisweb', 'c-chez-vous', 'heppner', 'ciblex', 'paack', 'india-post'])('dispatches %s through the public registry', async carrier => {
    const adapter = new CarrierTrackingAdapter();
    const track = vi.spyOn(adapter.registry.for(carrier)!, 'track').mockResolvedValue({ status: 'in_transit' });
    expect(await adapter.fetch(carrier, 'SYNTHETIC0001', null, '00000')).toMatchObject({ status: 'in_transit' });
    expect(track.mock.calls[0][0]).toEqual({ number: 'SYNTHETIC0001', trackingUrl: null, postcode: '00000' });
  });

  it('asks the carrier with the country of a postcode from abroad, and only with a postcode', async () => {
    const adapter = new CarrierTrackingAdapter();
    const track = vi.spyOn(adapter.registry.for('spring-gds')!, 'track').mockResolvedValue({ status: 'in_transit' });
    await adapter.fetch('spring-gds', 'SYNTHETIC0001', null, '1000', null, 'BE');
    await adapter.fetch('spring-gds', 'SYNTHETIC0001', null, null, null, 'BE');
    expect(track.mock.calls.map(([input]) => input)).toEqual([
      { number: 'SYNTHETIC0001', trackingUrl: null, postcode: '1000', postcodeCountry: 'BE' },
      { number: 'SYNTHETIC0001', trackingUrl: null, postcode: null },
    ]);
  });

  it('asks Swiss Post in the language it is given', async () => {
    const adapter = new CarrierTrackingAdapter();
    const track = vi.spyOn(adapter.registry.for('swiss-post')!, 'track').mockResolvedValue({ status: 'in_transit' });
    await adapter.fetch('swiss-post', 'SYNTHETIC0001', null, null, 'fr');
    await adapter.fetch('swiss-post', 'SYNTHETIC0001', null, null, null);
    expect(track.mock.calls.map(([input]) => input)).toEqual([
      { number: 'SYNTHETIC0001', trackingUrl: null, postcode: null, language: 'fr' },
      { number: 'SYNTHETIC0001', trackingUrl: null, postcode: null },
    ]);
  });
});

describe('tracking event normalization', () => {
  it.each(['2026-09-07', '2026-09-07T12:32:00Z'])('preserves the precision of a status-only timestamp: %s', (time) => {
    const events = buildEvents({ id: 'package-1', carrier: 'dpd' }, {
      status: 'delivered', last_status_text: 'Delivered', last_update: time,
    });
    expect(events).toHaveLength(1);
    expect(events[0]?.raw_data).toEqual({ time, stage_source: 'wording:language' });
  });

  it('prioritizes exception and final-stage phrases before broad delivery words', () => {
    expect(inferStage('Delivery attempt failed')).toBe('failed_attempt');
    expect(inferStage('Return to sender')).toBe('exception');
    expect(inferStage('Returned to sender')).toBe('returned');
    expect(inferStage('Parcel handed to DPD')).toBe('accepted');
    expect(inferStage('To be delivered')).toBe('in_transit');
  });

  it('records where every event stage came from', () => {
    const rows = buildEvents({ id: 'package-1', carrier: 'ctt' }, {
      status: 'unknown',
      events: [
        { time: '2026-09-10T09:00:00Z', description: 'Objeto entregue', stage: 'delivered' },
        { time: '2026-09-10T08:00:00Z', description: 'Return to sender' },
        { time: '2026-09-10T07:00:00Z', description: 'Confirmation of receipt' },
        { time: '2026-09-10T06:00:00Z', description: 'Estado interno 99' },
      ],
    });
    expect(rows.map((row) => [row.stage, (row.raw_data as JsonObject).stage_source])).toEqual([
      ['delivered', 'carrier_map'],
      ['exception', 'wording:language'],
      ['delivered', 'wording:delivered'],
      ['in_transit', 'none'],
    ]);
    // The raw provider event is still stored next to the recorded source.
    expect(rows[3]?.raw_data).toEqual({
      time: '2026-09-10T06:00:00Z', description: 'Estado interno 99', stage_source: 'none',
    });
  });

  it('reads a scan in no stage as in transit unless that steps back from the scan in a stage before it', () => {
    // Synthetic notices that neither the carrier nor a wording rule gives a stage.
    const notice = { time: '2026-09-10T15:08:00Z', description: 'Fixture notice 7' };
    const stages = (...events: JsonObject[]) => buildEvents({ id: 'package-1', carrier: 'dpd' }, { status: 'unknown', events })
      .map((row) => [row.description, row.stage]);
    // After a label, an acceptance, in transit or at customs, it is news that the parcel moves.
    for (const [description, stage] of [['Label created', 'registered'], ['Fixture pickup', 'accepted'],
      ['Arrived at depot', 'in_transit'], ['Fixture customs check', 'customs']]) {
      expect(stages(notice, { time: '2026-09-10T08:00:00Z', description, stage })).toEqual([
        ['Fixture notice 7', 'in_transit'], [description, stage],
      ]);
    }
    // After the round, a delivery, a pickup point, a missed attempt, a return or a problem, in
    // transit would step back: it is pending, kept in the timeline in no stage.
    for (const stage of ['out_for_delivery', 'delivered', 'ready_for_pickup', 'failed_attempt', 'returned', 'exception']) {
      expect(stages(notice, { time: '2026-09-10T14:59:00Z', description: 'Fixture scan', stage })).toEqual([
        ['Fixture notice 7', 'pending'], ['Fixture scan', stage],
      ]);
    }
    // A stage the wording gives counts as much as a declared one, and so does the same instant.
    expect(stages({ time: '2026-09-10T15:08:00Z', description: 'Fixture scan' },
      { time: '2026-09-10T14:00:00Z', description: 'Refused by the recipient' })).toEqual([
      ['Fixture scan', 'pending'], ['Refused by the recipient', 'exception'],
    ]);
    expect(stages(notice, { time: notice.time, description: 'Delivered' })).toEqual([
      ['Fixture notice 7', 'pending'], ['Delivered', 'delivered'],
    ]);
    // Before means dated before, in whatever order the answer lists the scans: the notice listed
    // after the delivery is dated after it, and the scan dated first has nothing in a stage before it.
    expect(stages({ time: '2026-09-10T14:59:00Z', description: 'Delivered', stage: 'delivered' }, notice,
      { time: '2026-09-09T08:00:00Z', description: 'Code ZZ9 recorded' })).toEqual([
      ['Delivered', 'delivered'], ['Fixture notice 7', 'pending'], ['Code ZZ9 recorded', 'in_transit'],
    ]);
    // A later scan in transit lets a notice after it read as in transit again.
    expect(stages(notice, { time: '2026-09-10T12:00:00Z', description: 'Parcel in transit' },
      { time: '2026-09-10T08:00:00Z', description: 'Out for delivery', stage: 'out_for_delivery' })).toEqual([
      ['Fixture notice 7', 'in_transit'], ['Parcel in transit', 'in_transit'], ['Out for delivery', 'out_for_delivery'],
    ]);
    // A scan a provider left pending is in no stage: the delivery before it still holds.
    expect(stages(notice, { time: '2026-09-10T15:00:00Z', description: 'Unread provider line', stage: 'pending' },
      { time: '2026-09-10T14:59:00Z', description: 'Delivered', stage: 'delivered' })).toEqual([
      ['Fixture notice 7', 'pending'], ['Unread provider line', 'pending'], ['Delivered', 'delivered'],
    ]);
    // Not when the answer has only such scans, or a scan in a stage with no date, which is not
    // stored: nothing else shows where the parcel is.
    expect(stages(notice, { time: '2026-09-09T08:00:00Z', description: 'Code ZZ9 recorded' })).toEqual([
      ['Fixture notice 7', 'in_transit'], ['Code ZZ9 recorded', 'in_transit'],
    ]);
    expect(stages(notice, { provider_time_text: 'Unreadable heading', description: 'Delivered', stage: 'delivered' })).toEqual([
      ['Fixture notice 7', 'in_transit'],
    ]);
  });

  it('reads a notice that keeps the stage of the scan before it as that stage, unless that steps back', () => {
    // Synthetic notices a carrier marks as keeping the stage of the scan before it (`stage_source` `none`).
    const notice = (stage: string) => ({ time: '2026-09-10T15:08:00Z', description: 'Fixture notice 8', stage, stage_source: 'none' });
    const stages = (...events: JsonObject[]) => buildEvents({ id: 'package-1', carrier: 'dpd' }, { status: 'unknown', events })
      .map((row) => [row.description, row.stage, (row.raw_data as JsonObject).stage_source]);
    // After a label, an acceptance, in transit or at customs, it keeps the stage it was left in.
    for (const stage of ['registered', 'accepted', 'in_transit', 'customs']) {
      expect(stages(notice(stage), { time: '2026-09-10T08:00:00Z', description: 'Fixture scan', stage })).toEqual([
        ['Fixture notice 8', stage, 'none'], ['Fixture scan', stage, 'carrier_map'],
      ]);
    }
    // After the round, a delivery, a pickup point, a missed attempt, a return or a problem it is pending: the
    // scan it follows stays the newest in that stage, so its wording and time stand for the stage, not the notice's.
    for (const stage of ['out_for_delivery', 'delivered', 'ready_for_pickup', 'failed_attempt', 'returned', 'exception']) {
      expect(stages(notice(stage), { time: '2026-09-10T14:59:00Z', description: 'Fixture scan', stage })).toEqual([
        ['Fixture notice 8', 'pending', 'none'], ['Fixture scan', stage, 'carrier_map'],
      ]);
    }
    // A notice is in no stage of its own, so a later one still reads past the scan before both.
    expect(stages({ ...notice('delivered'), time: '2026-09-10T16:00:00Z', description: 'Fixture notice 9' }, notice('delivered'),
      { time: '2026-09-10T14:59:00Z', description: 'Fixture scan', stage: 'delivered' }).map((row) => row[1])).toEqual([
      'pending', 'pending', 'delivered',
    ]);
    // Not a scan in a stage of its own after the round, which the carrier mapped, nor a notice with
    // nothing dated in a stage before it.
    expect(stages({ time: '2026-09-10T15:08:00Z', description: 'Fixture scan 2', stage: 'out_for_delivery', stage_source: 'carrier_map' },
      { time: '2026-09-10T14:59:00Z', description: 'Fixture scan', stage: 'failed_attempt' })).toEqual([
      ['Fixture scan 2', 'out_for_delivery', 'carrier_map'], ['Fixture scan', 'failed_attempt', 'carrier_map'],
    ]);
    expect(stages(notice('registered'))).toEqual([['Fixture notice 8', 'registered', 'none']]);
    // A scan the carrier stages in transit as no rule read it is a scan no rule reads: pending after the round.
    expect(stages(notice('in_transit'), { time: '2026-09-10T14:59:00Z', description: 'Fixture scan', stage: 'out_for_delivery' })).toEqual([
      ['Fixture notice 8', 'pending', 'none'], ['Fixture scan', 'out_for_delivery', 'carrier_map'],
    ]);
    // Not a stage no rule read that differs from the scan before it, such as an incident the carrier flags as a
    // problem after the round, or a scan it stages out for delivery after one in transit: its own judgement stands.
    expect(stages(notice('exception'), { time: '2026-09-10T14:59:00Z', description: 'Fixture scan', stage: 'out_for_delivery' })).toEqual([
      ['Fixture notice 8', 'exception', 'none'], ['Fixture scan', 'out_for_delivery', 'carrier_map'],
    ]);
    expect(stages(notice('out_for_delivery'), { time: '2026-09-10T14:59:00Z', description: 'Fixture scan', stage: 'in_transit' })).toEqual([
      ['Fixture notice 8', 'out_for_delivery', 'none'], ['Fixture scan', 'in_transit', 'carrier_map'],
    ]);
    // Nor one that matches only a scan dated after it.
    expect(stages({ time: '2026-09-10T16:00:00Z', description: 'Fixture scan', stage: 'exception' }, notice('exception'),
      { time: '2026-09-10T14:59:00Z', description: 'Fixture scan 2', stage: 'out_for_delivery' }).map((row) => row[1])).toEqual([
      'exception', 'exception', 'out_for_delivery',
    ]);
  });

  it('dates scans a carrier gives by their day alone at that day, in its order, with no observed row', () => {
    // NACEX heads its history with calendar days and gives no clock (synthetic scans).
    const rows = buildEvents({ id: 'package-1', carrier: 'nacex', current_stage: 'in_transit' }, {
      status: 'exception', current_stage: 'exception', last_status_text: 'Contacta con agencia', last_update: null,
      last_update_local: '2026-10-19', direct_local_fallback: true, events: [
        { local_time: '2026-10-19', description: 'Contacta con agencia', stage: 'exception' },
        { local_time: '2026-10-19', description: 'En reparto', stage: 'out_for_delivery' },
        { local_time: '2026-10-18', description: 'En tránsito', stage: 'in_transit' },
        { provider_time_text: 'Unreadable heading', description: 'Recogido', stage: 'accepted' },
      ] }, 'nacex', new Date('2026-10-20T08:00:00Z'));
    // A day starts at midnight in Madrid. A scan of the same day listed later is older: a second earlier, whatever its stage.
    expect(rows.map((row) => [row.occurred_at, row.stage, row.provider_event_id])).toEqual([
      ['2026-10-18T22:00:01Z', 'exception', providerEventId('nacex', '2026-10-19', '', 'Contacta con agencia')],
      ['2026-10-18T22:00:00Z', 'out_for_delivery', providerEventId('nacex', '2026-10-19', '', 'En reparto')],
      ['2026-10-17T22:00:00Z', 'in_transit', providerEventId('nacex', '2026-10-18', '', 'En tránsito')],
    ]);
    // Among scans with clock times, a day follows the scan before it on that day.
    expect(buildEvents({ id: 'package-1', carrier: 'dpd' }, { status: 'delivered', current_stage: 'delivered',
      last_status_text: 'Delivered', last_update: '2026-10-19T08:12:00Z', events: [
        { local_time: '2026-10-19', description: 'Delivered', stage: 'delivered' },
        { time: '2026-10-19T08:12:00Z', description: 'Out for delivery', stage: 'out_for_delivery' },
        { local_time: '2026-10-19', description: 'At the depot', stage: 'in_transit' },
        { time: '2026-10-18T19:00:00Z', description: 'In transit', stage: 'in_transit' },
      ] }, 'dpd', new Date('2026-10-20T08:00:00Z')).map((row) => [row.occurred_at, row.description])).toEqual([
      ['2026-10-19T08:12:01Z', 'Delivered'], ['2026-10-19T08:12:00Z', 'Out for delivery'],
      ['2026-10-18T22:00:00Z', 'At the depot'], ['2026-10-18T19:00:00Z', 'In transit']]);
    // A clock time read as a local time still dates no row.
    expect(buildEvents({ id: 'package-1', carrier: 'seur', current_stage: 'in_transit' }, { status: 'delivered', current_stage: 'delivered',
      last_status_text: 'Entregado', last_update: null, direct_local_fallback: true,
      events: [{ local_time: '2026-10-19T10:00:00', description: 'Entregado', stage: 'delivered' }] }, 'seur', new Date('2026-10-20T08:00:00Z'))
      .map((row) => (row.raw_data as JsonObject).observed_without_provider_timestamp)).toEqual([true]);
  });

  it('starts a day on the carrier\'s home clock, not its catalog zone of UTC', () => {
    // USPS dates pre-shipment info by its day alone; its catalog zone is UTC (synthetic scans).
    const rows = buildEvents({ id: 'package-1', carrier: 'usps', current_stage: 'registered' }, {
      status: 'pending', current_stage: 'registered', last_status_text: 'Pre-Shipment Info Sent to USPS', last_update: null,
      last_update_local: '2026-10-20', events: [
        { local_time: '2026-10-20', description: 'Pre-Shipment Info Sent to USPS', stage: 'registered' },
      ] }, 'usps', new Date('2026-10-21T08:00:00Z'));
    // Midnight in New York, not 20:00 there the evening before; the day stays the scan's identity.
    expect(rows.map((row) => [row.occurred_at, row.provider_event_id])).toEqual([
      ['2026-10-20T04:00:00Z', providerEventId('usps', '2026-10-20', '', 'Pre-Shipment Info Sent to USPS')]]);
    // A zone the result declares is the carrier's clock.
    expect(buildEvents({ id: 'package-1', carrier: 'usps', current_stage: 'registered' }, {
      status: 'pending', current_stage: 'registered', last_status_text: 'Pre-Shipment Info Sent to USPS', last_update: null,
      timezone: 'America/Los_Angeles', events: [{ local_time: '2026-10-20', description: 'Pre-Shipment Info Sent to USPS', stage: 'registered' }],
    }, 'usps', new Date('2026-10-21T08:00:00Z')).map((row) => row.occurred_at)).toEqual(['2026-10-20T07:00:00Z']);
  });

  it('dates a carrier\'s scans on a wall clock in no zone by their day, among its dated scans', () => {
    // YunExpress gives some scans a wall clock and no zone, the others in full (synthetic scans).
    const answer = (events: JsonObject[], extra: JsonObject = {}): CarrierResult => ({ status: 'in_transit', current_stage: 'in_transit',
      last_status_text: 'Departed', last_update: '2026-10-12T03:00:00+08:00', events, ...extra } as CarrierResult);
    const scans = [
      { time: '2026-10-12T03:00:00+08:00', description: 'Departed', stage: 'in_transit' },
      { local_time: '2026-10-11T18:20:00', description: 'Sorted', stage: 'in_transit' },
      { local_time: '2026-10-11T09:05:00', description: 'Sorted', stage: 'in_transit' },
      { time: '2026-10-11T08:00:00+08:00', description: 'Received', stage: 'accepted' },
      { local_time: '2026-10-09T10:00:00', description: 'Label created', stage: 'registered' },
    ];
    const parcel = { id: 'package-1', carrier: 'yunexpress', current_stage: 'in_transit' };
    const rows = buildEvents(parcel, answer(scans), 'yunexpress', new Date('2026-10-12T08:00:00Z'));
    // In the carrier's order: a second after the dated scan of their day, or at the start of theirs. Two clocks are two scans.
    expect(rows.map((row) => [row.occurred_at, row.description, row.provider_event_id])).toEqual([
      ['2026-10-11T19:00:00Z', 'Departed', providerEventId('yunexpress', '2026-10-12T03:00:00+08:00', '', 'Departed')],
      ['2026-10-11T00:00:02Z', 'Sorted', providerEventId('yunexpress', '2026-10-11T18:20:00', '', 'Sorted')],
      ['2026-10-11T00:00:01Z', 'Sorted', providerEventId('yunexpress', '2026-10-11T09:05:00', '', 'Sorted')],
      ['2026-10-11T00:00:00Z', 'Received', providerEventId('yunexpress', '2026-10-11T08:00:00+08:00', '', 'Received')],
      ['2026-10-08T16:00:00Z', 'Label created', providerEventId('yunexpress', '2026-10-09T10:00:00', '', 'Label created')],
    ]);
    const days = (result: CarrierResult, carrier = 'yunexpress') => buildEvents({ ...parcel, carrier }, result, carrier)
      .map((row) => row.description);
    // The newest scan on a wall clock, a fallback, a provider's answer and a carrier that dates its scans write no such row.
    expect(days(answer([scans[1]!, scans[3]!]))).toEqual(['Received']);
    expect(days(answer(scans, { direct_local_fallback: true }))).toEqual(['Departed', 'Received']);
    expect(days(answer(scans, { tracking_provider: 'UPU' }))).toEqual(['Departed', 'Received']);
    expect(days(answer(scans, { tracking_provider: 'ParcelsApp' }), 'unknown')).toEqual(['Departed', 'Received']);
    expect(days(answer(scans), 'ups')).toEqual(['Departed', 'Received']);
  });

  it('keeps a scan the carrier repeats word for word once', () => {
    const sort = { time: '2026-09-29T06:07:00+02:00', description: 'Tri effectué dans l’agence de distribution' };
    const rows = buildEvents({ id: 'package-1', carrier: 'chronopost' }, {
      status: 'in_transit',
      events: [sort, { ...sort }, { ...sort, location: 'Agence' }, { ...sort, time: '2026-09-29T06:08:00+02:00' }, { ...sort }],
    });
    // Saving the same identity twice in one batch fails the whole refresh.
    expect(rows.map((row) => [row.occurred_at, row.location])).toEqual([
      ['2026-09-29T04:07:00Z', null],
      ['2026-09-29T04:07:00Z', 'Agence'],
      ['2026-09-29T04:08:00Z', null],
    ]);
    expect(new Set(rows.map((row) => row.provider_event_id)).size).toBe(rows.length);
  });

  it('keeps the classifier stage and its rule id in step', () => {
    expect(classifyStage('Delivery attempt failed'))
      .toEqual({ stage: 'failed_attempt', source: 'wording:language' });
    expect(classifyStage('Confirmation of receipt'))
      .toEqual({ stage: 'delivered', source: 'wording:delivered' });
    expect(classifyStage('Estado interno 99', 'pending'))
      .toEqual({ stage: 'pending', source: 'none' });
    expect(inferStage('Estado interno 99', 'pending')).toBe('pending');
  });

  it('normalizes zoneless carrier timestamps into UTC', () => {
    expect(eventTimestamp('15.07.2026 10:00', 'Europe/Zurich'))
      .toBe('2026-07-15T08:00:00Z');
    expect(eventTimestamp('not-a-date', 'Europe/Zurich')).toBeNull();
  });

  it('does not label unrecognized historical scans with the current delivered status', () => {
    const events = buildEvents({ id: 'package-1', carrier: 'ups' }, {
      status: 'delivered', last_status_text: 'Delivered',
      events: [
        { time: '2026-07-15T12:00:00Z', description: 'Delivered' },
        { time: '2026-07-14T12:00:00Z', description: 'Carrier-specific scan' },
        { time: '2026-07-13T12:00:00Z', description: 'Shipper created a label, UPS has not received the package yet.' },
      ],
    });
    expect(events.map((event) => event.stage)).toEqual(['delivered', 'in_transit', 'registered']);
  });

  it.each([
    ['Sorting - forwarding', 'in_transit'],
    ['REPORTED', 'registered'],
    ['Paket wurde elektronisch angekündigt', 'registered'],
    ['Import Scan', 'in_transit'],
    ['Your package is on the way', 'in_transit'],
    ['Delivery will be delayed by one business day.', 'in_transit'],
    ['Your package has been released by a government agency.', 'in_transit'],
    ['The parcel has not been released by customs.', 'customs'],
    ["Your package is pending release from a Government Agency. We'll notify the receiver or sender if information is needed.", 'customs'],
    ['Deposited in the MyPost24 machine', 'ready_for_pickup'],
    ['Delivered to the mailbox/letter box', 'delivered'],
  ])('classifies %s independently of a final fallback', (description, stage) => {
    expect(inferStage(description, 'delivered')).toBe(stage);
  });

  it('uses the summary for an observed update rather than borrowing an unrelated history description', () => {
    const events = buildEvents({ id: 'package-1', carrier: 'ups', current_stage: 'in_transit' }, {
      status: 'delivered', last_status_text: 'Delivered',
      events: [{ time: '2026-07-14T12:00:00Z', description: 'Carrier-specific scan' }],
    }, undefined, new Date('2026-07-15T12:00:00Z'));
    expect(events.map(({ stage, description }) => ({ stage, description }))).toEqual([
      { stage: 'in_transit', description: 'Carrier-specific scan' },
      { stage: 'delivered', description: 'Delivered' },
    ]);
  });

  it('keeps a verified DPD delivery delivered: the proof-of-delivery scan is not the newest row', () => {
    const result = normalizeCarrierResult(carrierResult('dpd-delivered'));
    const rows = buildEvents(
      { id: 'package-1', carrier: 'dpd', current_stage: 'out_for_delivery' },
      result,
      'dpd',
      new Date('2026-07-16T12:00:00Z'),
    );

    expect(resultStage(result)).toBe('delivered');
    expect(rows.map((row) => [
      row.occurred_at, row.stage, row.location, (row.raw_data as JsonObject).stage_source,
    ])).toEqual([
      ['2026-07-16T08:12:00Z', 'delivered', 'Urdorf, CH', 'carrier_map'],
      ['2026-07-16T04:10:45Z', 'out_for_delivery', 'Urdorf, CH', 'carrier_map'],
      ['2026-07-16T01:48:00Z', 'in_transit', 'Urdorf, CH', 'carrier_map'],
      ['2026-07-15T16:05:12Z', 'in_transit', 'Urdorf, CH', 'wording:language'],
      ['2026-07-15T14:30:00Z', 'in_transit', null, 'carrier_map'],
    ]);
    expect(rows[0]?.provider_event_id).toBe(providerEventId(
      'dpd', '2026-07-16T10:12:00+02:00', 'Urdorf, CH', 'Your parcel has been delivered successfully',
    ));
    // Only the unmapped depot-arrival scan is recorded for review.
    expect(collectStatusObservations(rows, 'dpd').map((observation) => [
      observation.provider_code, observation.chosen_stage,
    ])).toEqual([['ORI', 'in_transit']]);
  });

  it('adds no observed DPD row when the newest verified scan is the depot arrival', () => {
    // The enumeration beside ORI (PARCEL_HANDED) has no stage, so the result
    // stage comes from the scan's wording; the scan must agree with it.
    for (const eventTypes of [['ORI'], ['ORI', 'CCO']]) {
      const result = carrierResult(eventTypes.length === 1 ? 'dpd-ORI' : 'dpd-ORI-CCO');
      for (const previousStage of ['pending', 'registered', 'accepted']) {
        const rows = buildEvents(
          { id: 'package-1', carrier: 'dpd', current_stage: previousStage },
          result,
          'dpd',
          new Date('2026-07-15T20:00:00Z'),
        );

        expect(resultStage(result)).toBe('in_transit');
        expect(rows.map((row) => [row.stage, (row.raw_data as JsonObject).observed_without_provider_timestamp ?? false]))
          .toEqual(eventTypes.map(() => ['in_transit', false]));
      }
    }
  });

  it('creates stable provider ids and drops events without usable timestamps', () => {
    const result: CarrierResult = {
      status: 'in_transit',
      events: [
        { time: '2026-07-15T08:00:00Z', description: 'Sorted', location: 'Härkingen' },
        { time: '', description: 'No time' },
      ],
    };
    const rows = buildEvents({ id: 'package-1', carrier: 'dpd' }, result);
    expect(rows).toHaveLength(1);
    expect(rows[0]).toMatchObject({
      package_id: 'package-1',
      stage: 'in_transit',
      location: 'Härkingen',
      occurred_at: '2026-07-15T08:00:00Z',
    });
    expect(rows[0]?.provider_event_id).toBe(
      providerEventId('dpd', '2026-07-15T08:00:00Z', 'Härkingen', 'Sorted'),
    );
  });

  it('names the universal provider on the stage change it reported without a time', () => {
    const observedAt = new Date('2026-08-30T13:00:00Z');
    const rows = buildEvents({ id: 'package-upu', carrier: 'intl-post', current_stage: 'accepted' }, {
      tracking_provider: 'UPU',
      status: 'in_transit',
      current_stage: 'in_transit',
      last_status_text: 'Departure from outward office of exchange',
      events: [{ description: 'Departure from outward office of exchange', stage: 'in_transit', local_time: '2026-08-30T09:00:00', provider_code: 'EXC' }],
    }, 'unknown', observedAt);
    expect(rows).toEqual([expect.objectContaining({
      stage: 'in_transit',
      occurred_at: observedAt.toISOString(),
      raw_data: expect.objectContaining({ observed_without_provider_timestamp: true, local_time: '2026-08-30T09:00:00', universal_provider: 'UPU' }),
    })]);
  });

  it('records an observed event when a timestamp-less current state changes', () => {
    const observedAt = new Date('2026-08-30T13:00:00Z');
    const result: CarrierResult = {
      status: 'out_for_delivery',
      last_status_text: 'En cours de livraison',
      events: [{
        description: 'En cours de livraison',
        stage: 'out_for_delivery',
      }],
    };
    const changed = buildEvents({
      id: 'package-current',
      carrier: 'c-chez-vous',
      current_stage: 'in_transit',
    }, result, undefined, observedAt);
    expect(changed).toEqual([
      expect.objectContaining({
        stage: 'out_for_delivery',
        description: 'En cours de livraison',
        occurred_at: '2026-08-30T13:00:00.000Z',
        raw_data: { observed_without_provider_timestamp: true, stage_source: 'carrier_map' },
      }),
    ]);

    expect(buildEvents({
      id: 'package-current',
      carrier: 'c-chez-vous',
      current_stage: 'out_for_delivery',
    }, result, undefined, observedAt)).toEqual([]);

    expect(buildEvents({
      id: 'package-registered',
      carrier: 'c-chez-vous',
      current_stage: 'pending',
    }, {
      status: 'pending',
      last_status_text: 'Commande enregistrée',
      events: [{ description: 'Commande enregistrée', stage: 'registered' }],
    }, undefined, observedAt)).toEqual([
      expect.objectContaining({ stage: 'registered', occurred_at: observedAt.toISOString() }),
    ]);
  });

  it('recognizes usable event progress even when a carrier summary is pending', () => {
    expect(resultHasUpdate({
      status: 'pending',
      events: [{ description: 'Accepted at depot' }],
    })).toBe(true);
    expect(resultHasUpdate({ status: 'pending', events: [] })).toBe(false);
  });

  it('uses a validated adapter stage instead of reverse-translating display text', () => {
    expect(resultStage({
      status: 'exception',
      current_stage: 'returned',
      last_status_text: 'Livraison annulée',
    })).toBe('returned');
    expect(resultStage({
      status: 'in_transit',
      current_stage: 'accepted',
      last_status_text: 'Shipment collected',
    })).toBe('accepted');
    // A carrier problem that is neither a missed attempt nor a return keeps
    // its own stage instead of promising a delivery round.
    expect(resultStage({
      status: 'exception',
      current_stage: 'exception',
      last_status_text: 'Parcel damaged in transit',
    })).toBe('exception');
    expect(() => normalizeCarrierResult({
      status: 'in_transit',
      current_stage: 'private_internal_state',
    })).toThrow('invalid current stage');
  });

  it('recognizes 404s through an error cause chain', () => {
    const cause = Object.assign(new Error('provider response'), { status: 404 });
    expect(isUnannouncedTrackingError(new Error('lookup failed', { cause }))).toBe(true);
    expect(isUnannouncedTrackingError(new Error('network down'))).toBe(false);
  });
});

describe('fair scheduling', () => {

  it('round-robins owners and caps each account', () => {
    const packages = [
      { id: 'a1', user_id: 'a' },
      { id: 'a2', user_id: 'a' },
      { id: 'a3', user_id: 'a' },
      { id: 'b1', user_id: 'b' },
      { id: 'b2', user_id: 'b' },
    ];
    expect(fairSyncPackages(packages, 2).map((parcel) => parcel.id))
      .toEqual(['a1', 'b1', 'a2', 'b2']);
    expect(() => fairSyncPackages(packages, 0)).toThrow('positive');
  });

  it('aligns daytime checks to two minutes and overnight checks to the hour', () => {
    expect(secondsUntilNextSync(new Date('2026-07-15T07:03:30Z'))).toBe(30);
    expect(secondsUntilNextSync(new Date('2026-07-15T07:04:00Z'))).toBe(120);
    expect(secondsUntilNextSync(new Date('2026-07-15T19:59:30Z'))).toBe(30);
    expect(secondsUntilNextSync(new Date('2026-07-15T20:00:00Z'))).toBe(3_600);
    // The window a run belongs to opened at the last even minute by day, at the hour overnight.
    expect(syncWindowStart(new Date('2026-07-15T07:03:30Z'))).toEqual(new Date('2026-07-15T07:02:00Z'));
    expect(syncWindowStart(new Date('2026-07-15T20:30:00Z'))).toEqual(new Date('2026-07-15T20:00:00Z'));
    expect(secondsUntilNextSync(new Date('2026-07-15T20:15:00Z'))).toBe(2_700);
    expect(() => secondsUntilNextSync(new Date('invalid'))).toThrow('valid');
  });

  it('polls an idle queue less and less often, up to ten intervals apart', () => {
    expect([0, 1, 2, 3, 4, 5, 50].map((empty) => idlePollDelay(1_000, empty)))
      .toEqual([1_000, 1_000, 2_000, 4_000, 8_000, 10_000, 10_000]);
    expect(idlePollDelay(5, 9)).toBe(50);
    expect(() => idlePollDelay(0, 1)).toThrow('positive');
    expect(() => idlePollDelay(1_000, 1.5)).toThrow('non-negative');
  });

  it('backs off boundedly when the job store is unavailable', () => {
    expect(workerPollDelay(1_000, 0)).toBe(1_000);
    expect(workerPollDelay(1_000, 1)).toBe(1_000);
    expect(workerPollDelay(1_000, 4)).toBe(8_000);
    expect(workerPollDelay(1_000, 20)).toBe(60_000);
    expect(() => workerPollDelay(0, 1)).toThrow('positive');
    expect(() => workerPollDelay(1_000, -1)).toThrow('non-negative');
  });
});

describe('status observation collection', () => {
  // The key an observation of the same universal wording carries. No map stages a scan pending.
  const universalKey = (row: JsonObject) => collectStatusObservations(
    [{ ...row, stage: 'pending', raw_data: { ...row.raw_data as JsonObject, stage_source: 'none' } }], 'unknown',
  )[0]?.observation_key;

  it('reviews aggregator wording the universal map leaves unread or reads otherwise, and keeps mapped wording out', () => {
    const rows = buildEvents({ id: 'package-1', carrier: 'unknown' }, {
      tracking_provider: 'ParcelsApp',
      status: 'in_transit', current_stage: 'in_transit',
      events: [
        { time: '2026-01-01T13:00:00Z', description: 'Departed from facility', stage: 'out_for_delivery', stage_source: 'wording:provider' },
        { time: '2026-01-01T12:00:00Z', description: 'Unrecognized carrier message', stage: 'pending', stage_source: 'none' },
        { time: '2026-01-01T11:00:00Z', description: 'Sorted in regional hub', stage: 'in_transit', stage_source: 'wording:language' },
        { time: '2026-01-01T10:00:00Z', description: 'Mapped milestone', stage: 'accepted', stage_source: 'carrier_map' },
        { time: '2026-01-01T09:00:00Z', description: 'Legacy mapped milestone', stage: 'accepted' },
      ],
    });
    expect(rows.map((row) => [row.stage, (row.raw_data as JsonObject).stage_source])).toEqual([
      ['out_for_delivery', 'wording:provider'], ['pending', 'none'], ['in_transit', 'wording:language'],
      ['accepted', 'carrier_map'], ['accepted', 'carrier_map'],
    ]);
    // The universal map reads the first as in transit and does not read the second.
    expect(collectStatusObservations(rows, 'unknown').map((observation) => [
      observation.description_normalized, observation.chosen_stage, observation.stage_source,
    ])).toEqual([
      ['departed from facility', 'out_for_delivery', 'wording:provider'],
      ['unrecognized carrier message', 'pending', 'none'],
    ]);
    // It reads the third as in transit too, so that wording is mapped like the explicit maps'.
    expect(collectMappedStatusKeys(rows, 'unknown')).toEqual(rows.slice(2).map(universalKey));
  });

  it('maps a hand-over the universal map reads as accepted and the sync filed in transit, and no other step back', () => {
    const scan = (description: string, stage: string) => ({
      package_id: 'package-1', stage, description, provider_event_id: `unknown:${description}`, raw_data: { stage_source: 'wording:provider' },
    });
    // The universal map reads both hand-overs as accepted, and the hub scan as in transit.
    const rows = [scan('Parcel handed', 'in_transit'), scan('Package picked up by the courier', 'in_transit'),
      scan('Parcel handed', 'out_for_delivery'), scan('Sorted in regional hub', 'accepted')];
    expect(collectStatusObservations(rows, 'unknown').map((observation) => [observation.description_normalized, observation.chosen_stage]))
      .toEqual([['parcel handed', 'out_for_delivery'], ['sorted in regional hub', 'accepted']]);
    expect(collectMappedStatusKeys(rows.slice(1, 2), 'unknown')).toEqual([universalKey(rows[1]!)]);
  });

  it('keeps wording that names a shop and postcode as one template, without the place', () => {
    const scan = (description: string, id: string) => ({
      package_id: 'package-1', stage: 'pending', description, provider_event_id: `unknown:${id}`, raw_data: { stage_source: 'none' },
    });
    const rows = [
      scan('Received at Corner Local Express XX1 1XX', 'a'),
      scan('Received at  Station Road Local Express xx99 9xx', 'b'),
      scan('Received at Corner Local Express', 'c'),
      scan('Received at hub', 'd'),
    ];
    expect(collectStatusObservations(rows, 'unknown').map((observation) => observation.description_normalized)).toEqual([
      'received at <store> local express <postcode>', 'received at hub',
    ]);
    expect(universalKey(rows[0])).toBe(universalKey(rows[1]));
  });

  it('reads only universal scans with the universal map: a direct carrier\'s wording fallback is still reviewed', () => {
    const scan = (carrier: string) => ({
      package_id: 'package-1', stage: 'in_transit', description: 'Sorted in regional hub',
      provider_event_id: `${carrier}:abc`, raw_data: { stage_source: 'wording:language' },
    });
    const rows = [scan('unknown'), scan('dpd')];
    expect(collectStatusObservations(rows, 'unknown')).toMatchObject([
      { carrier: 'dpd', description_normalized: 'sorted in regional hub', chosen_stage: 'in_transit', stage_source: 'wording:language' },
    ]);
    expect(collectMappedStatusKeys(rows, 'unknown')).toEqual([universalKey(scan('unknown'))]);
  });

  it('preserves summary and local-clock milestone sources when no timestamped scan can be saved', () => {
    const parcel = { id: 'package-1', carrier: 'unknown', current_stage: 'pending' };
    // The universal map reads the wording as in transit: a summary that agrees is
    // mapped, one that does not is reviewed.
    const summary = (stage: string) => buildEvents(parcel, {
      status: 'in_transit', current_stage: stage, current_stage_source: 'wording:provider',
      last_status_text: 'Sorted in regional hub', last_update: '2026-01-01T11:00:00Z',
    });
    expect(summary('in_transit').map((row) => row.raw_data)).toMatchObject([{ stage_source: 'wording:provider' }]);
    expect(collectStatusObservations(summary('in_transit'), 'unknown')).toEqual([]);
    expect(collectMappedStatusKeys(summary('in_transit'), 'unknown')).toEqual(summary('in_transit').map(universalKey));
    expect(collectStatusObservations(summary('out_for_delivery'), 'unknown')).toMatchObject([
      { chosen_stage: 'out_for_delivery', stage_source: 'wording:provider' },
    ]);
    expect(collectMappedStatusKeys(summary('out_for_delivery'), 'unknown')).toEqual([]);
    for (const current_stage_source of [undefined, 'wording:provider']) {
      const observed = buildEvents(parcel, {
        tracking_provider: 'UPU', status: 'in_transit', current_stage: 'in_transit', current_stage_source,
        events: [{ local_time: '2026-01-01T11:00:00', description: 'Sorted in regional hub',
          stage: 'in_transit', stage_source: 'wording:provider', provider_code: 'ZZ1' }],
      }, undefined, new Date('2026-01-01T12:00:00Z'));
      expect(observed.map((row) => [row.stage, row.raw_data])).toMatchObject([
        ['in_transit', { provider_code: 'ZZ1', stage_source: 'wording:provider' }],
      ]);
      expect(collectStatusObservations(observed, 'unknown')).toEqual([]);
      expect(collectMappedStatusKeys(observed, 'unknown')).toEqual(observed.map(universalKey));
    }
  });

  it('keeps wording no carrier map resolved, deduplicated per carrier and code', () => {
    const rows = buildEvents({ id: 'package-1', carrier: 'ctt' }, {
      status: 'unknown',
      events: [
        { time: '2026-09-10T09:00:00Z', description: 'Objeto entregue', stage: 'delivered', provider_code: '5' },
        { time: '2026-09-10T08:00:00Z', description: '  Estado   INTERNO 99 ', provider_code: '99' },
        { time: '2026-09-10T07:00:00Z', description: 'Estado interno 99', provider_code: '99' },
      ],
    });
    const observations = collectStatusObservations(rows, 'ctt');
    expect(observations).toHaveLength(1);
    expect(observations[0]).toMatchObject({
      carrier: 'ctt',
      provider_code: '99',
      description_normalized: 'estado interno 99',
      language_guess: null,
      stage_source: 'none',
      chosen_stage: 'in_transit',
      package_id: 'package-1',
    });
    expect(observations[0]?.observation_key).toMatch(/^[0-9a-f]{64}$/);
  });

  it('keys wording by the whole provider code and stores a long one shortened, apart from codes that start alike', () => {
    const scan = (code: string, id: string) => ({
      package_id: 'package-1', stage: 'in_transit', description: 'Synthetic hub scan',
      provider_event_id: `australia-post:${id}`, raw_data: { stage_source: 'none', provider_code: code },
    });
    const label = 'ZZ-ER1 ' + 'synthetic milestone label '.repeat(4);
    const fits = 'ZZ-ER2 ' + 'x'.repeat(93);
    const rows = [scan(`${label}one`, 'a'), scan(`${label}two`, 'b'), scan(`${label}one`, 'c'), scan(fits, 'd')];
    const observations = collectStatusObservations(rows, 'australia-post');
    expect(observations).toHaveLength(3);
    const [one, two, whole] = observations;
    expect(new Set(observations.map((observation) => observation.observation_key)).size).toBe(3);
    expect(one?.provider_code).toMatch(new RegExp(`^${label.slice(0, 87)}…[0-9a-f]{12}$`));
    expect(two?.provider_code).toMatch(new RegExp(`^${label.slice(0, 87)}…[0-9a-f]{12}$`));
    expect(one?.provider_code).toHaveLength(100);
    expect(one?.provider_code).not.toBe(two?.provider_code);
    // The same code gives the same key and stored code on every check.
    expect(collectStatusObservations([rows[2]!], 'australia-post')).toMatchObject([
      { observation_key: one?.observation_key, provider_code: one?.provider_code },
    ]);
    // A code that fits is kept whole, and keys its row as it always did.
    expect(whole).toMatchObject({
      provider_code: fits,
      observation_key: createHash('sha256').update(JSON.stringify(['australia-post', fits, 'synthetic hub scan'])).digest('hex'),
    });
  });

  it('attributes each wording to the carrier that served it and bounds one sync', () => {
    const merged = collectStatusObservations([
      {
        package_id: 'package-1', stage: 'in_transit', description: 'Unknown German wording',
        provider_event_id: 'gls-de:abc', raw_data: { stage_source: 'none' },
      },
      {
        package_id: 'package-1', stage: 'in_transit', description: 'Unknown Swiss wording',
        provider_event_id: '', raw_data: { stage_source: 'wording:in_transit' },
      },
    ], 'swiss-post');
    expect(merged.map((observation) => observation.carrier)).toEqual(['gls-de', 'swiss-post']);

    const many = Array.from({ length: MAX_STATUS_OBSERVATIONS_PER_SYNC + 8 }, (_, index) => ({
      package_id: 'package-1', stage: 'in_transit', description: `Unknown wording ${index}`,
      provider_event_id: `ctt:${index}`, raw_data: { stage_source: 'none' },
    }));
    expect(collectStatusObservations(many, 'ctt')).toHaveLength(MAX_STATUS_OBSERVATIONS_PER_SYNC);
  });

  it('files a universal provider\'s copy of a direct carrier\'s scan under that carrier, read by its map', () => {
    // The key an unmapped observation of a carrier's wording carries.
    const carrierKey = (carrier: string, description: string) => collectStatusObservations([{ package_id: 'package-1',
      stage: 'pending', description, provider_event_id: `${carrier}:key`, raw_data: { stage_source: 'none' } }], carrier)[0]?.observation_key;
    const parcel = { id: 'package-1', carrier: 'pitney-bowes' };
    const stored = [
      { provider_event_id: 'pitney-bowes:closed', stage: 'in_transit', description: 'Container Closed' },
      { provider_event_id: 'pitney-bowes:sorted', stage: 'in_transit', description: 'Parcel sorted at the regional hub' },
      // Pitney Bowes took this universal row over, so it names no provider.
      { provider_event_id: 'unknown:measured', stage: 'in_transit', description: 'First Dimension Event' },
      { provider_event_id: 'unknown:relayed', stage: 'pending', description: 'Synthetic local note', universal_provider: 'ParcelsApp' },
    ];
    const copy = (id: string, description: string, stage: string, stage_source: string) => ({
      package_id: 'package-1', provider_event_id: `unknown:${id}`, description, stage, raw_data: { stage_source, provider_code: 'InTransit_Other' },
    });
    const events = [
      // A provider's copies of Pitney Bowes's scans, read at its own stage.
      copy('closed-copy', 'Container closed', 'pending', 'none'),
      copy('sorted-copy', 'Parcel sorted at the regional hub', 'in_transit', 'wording:language'),
      copy('measured-copy', 'First dimension event', 'pending', 'none'),
      // A scan Pitney Bowes has not given yet, in words its map stages as the provider did.
      copy('customs', 'Customs Clearance', 'customs', 'wording:language'),
      // The next carrier's scans the provider relays.
      copy('local', 'Synthetic local carrier message', 'pending', 'none'),
      copy('relayed-copy', 'Synthetic local note', 'pending', 'none'),
    ];
    const matched = new Map([['unknown:closed-copy', 'pitney-bowes:closed'], ['unknown:sorted-copy', 'pitney-bowes:sorted'],
      ['unknown:measured-copy', 'unknown:measured'], ['unknown:relayed-copy', 'unknown:relayed']]);
    const copies = carrierCopies(parcel, events, stored, matched);
    expect(copies).toEqual(new Map([
      ['unknown:closed-copy', { carrier: 'pitney-bowes', providerCode: null, stage: 'in_transit' }],
      ['unknown:sorted-copy', { carrier: 'pitney-bowes', providerCode: null, stage: 'in_transit' }],
      ['unknown:measured-copy', { carrier: 'pitney-bowes', providerCode: null, stage: 'in_transit' }],
      ['unknown:customs', { carrier: 'pitney-bowes', providerCode: null, stage: 'customs' }],
    ]));
    // Pitney Bowes's map gives three their stage; the fourth is Pitney Bowes's own wording to review.
    expect(collectStatusObservations(events, 'unknown', copies).map((observation) => [
      observation.carrier, observation.provider_code, observation.description_normalized, observation.chosen_stage,
    ])).toEqual([
      ['pitney-bowes', null, 'parcel sorted at the regional hub', 'in_transit'],
      ['unknown', 'InTransit_Other', 'synthetic local carrier message', 'pending'],
      ['unknown', 'InTransit_Other', 'synthetic local note', 'pending'],
    ]);
    expect(collectMappedStatusKeys(events, 'unknown', copies)).toEqual(
      ['Container Closed', 'First Dimension Event', 'Customs Clearance'].map((wording) => carrierKey('pitney-bowes', wording)));
    // Without a carrier of its own, the parcel's copies stay the provider's.
    expect(carrierCopies({ ...parcel, carrier: 'unknown' }, events, stored, matched)).toEqual(new Map([
      ['unknown:closed-copy', { carrier: 'pitney-bowes', providerCode: null, stage: 'in_transit' }],
      ['unknown:sorted-copy', { carrier: 'pitney-bowes', providerCode: null, stage: 'in_transit' }],
    ]));
  });

  it('skips events whose stage came from the carrier map', () => {
    expect(collectStatusObservations([{
      package_id: 'package-1', stage: 'delivered', description: 'Objeto entregue',
      provider_event_id: 'ctt:abc', raw_data: { stage_source: 'carrier_map' },
    }], 'ctt')).toEqual([]);
  });

  it('keys the wording a carrier map resolved in every scan that carried it', () => {
    const scan = (description: string, stage_source: string, provider_code = '5') => ({
      package_id: 'package-1', stage: 'in_transit', description,
      provider_event_id: `ctt:${description}:${stage_source}`, raw_data: { stage_source, provider_code },
    });
    const mapped = scan('Objeto entregue', 'carrier_map');
    const keys = collectMappedStatusKeys([
      mapped, { ...mapped },
      scan('Em trânsito', 'carrier_map', '3'), scan('Em trânsito', 'none', '3'),
      scan('Estado interno 99', 'none', '99'),
    ], 'ctt');
    // The key an unmapped observation of the same wording would carry.
    const [observed] = collectStatusObservations([{ ...mapped, raw_data: { stage_source: 'none', provider_code: '5' } }], 'ctt');
    expect(keys).toEqual([observed?.observation_key]);

    const many = Array.from({ length: MAX_STATUS_OBSERVATIONS_PER_SYNC + 8 }, (_, index) => scan(`Mapped ${index}`, 'carrier_map'));
    expect(collectMappedStatusKeys(many, 'ctt')).toHaveLength(MAX_STATUS_OBSERVATIONS_PER_SYNC);
  });

  it('asks with the country stored with the parcel’s postcode while the parcel holds that postcode', async () => {
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) };
    const service = new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient, adapter, null);
    const abroad = { postcode_country: { postcode: '1000', country: 'BE' } };
    await service.syncPackage({ id: 'abroad', carrier: 'spring-gds', tracking_number: 'TEST1234', dpd_postcode: '1000', carrier_data: abroad });
    await service.syncPackage({ id: 'changed', carrier: 'spring-gds', tracking_number: 'TEST1234', dpd_postcode: '1012 AB', carrier_data: abroad });
    const asked = adapter.fetch.mock.calls.filter(([carrier]) => carrier === 'spring-gds');
    expect(asked.map((call) => [call[3], call[5]])).toEqual([['1000', 'BE'], ['1012 AB', undefined]]);
  });

  it('sends the keys of mapped wording with the server version', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValue({
      status: 'in_transit', current_stage: 'in_transit', current_stage_source: 'carrier_map',
      events: [{ time: '2026-01-01T12:00:00Z', description: 'Sorted in regional hub', provider_code: 'HUB',
        stage: 'in_transit', stage_source: 'carrier_map' }],
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    await expect(service.syncPackage({ id: 'observed', carrier: 'ctt', tracking_number: 'TEST1234' }))
      .resolves.toMatchObject({ updated: 1 });
    expect(client.recordTrackingStatusObservations).toHaveBeenCalledWith(
      [], [expect.stringMatching(/^[0-9a-f]{64}$/)], deployedVersion(),
    );
  });

  it('records unresolved wording after the events are persisted', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValue({
      status: 'in_transit',
      last_status_text: 'Estado interno 99',
      last_update: '2026-09-10T11:00:00Z',
      events: [{ time: '2026-09-10T11:00:00Z', description: 'Estado interno 99', provider_code: '99' }],
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    await expect(service.syncPackage({ id: 'observed', carrier: 'ctt', tracking_number: 'TEST1234' }))
      .resolves.toMatchObject({ updated: 1 });
    expect(client.recordTrackingStatusObservations).toHaveBeenCalledOnce();
    expect(client.recordTrackingStatusObservations.mock.calls[0][0]).toEqual([
      expect.objectContaining({
        carrier: 'ctt', provider_code: '99', description_normalized: 'estado interno 99',
        stage_source: 'none', chosen_stage: 'in_transit',
      }),
    ]);
    expect(client.recordTrackingStatusObservations.mock.invocationCallOrder[0])
      .toBeGreaterThan(client.insertEvents.mock.invocationCallOrder[0]);
    // No carrier map knows the wording.
    expect(client.closeTrackingStatusObservations).not.toHaveBeenCalled();
  });

  it('closes the wording a carrier map leaves unmapped on purpose once it is recorded', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValue({
      status: 'in_transit', current_stage: 'in_transit', current_stage_source: 'carrier_map',
      events: [
        { time: '2026-01-01T12:00:00Z', description: 'Parcel handed to DPD', provider_code: 'PARCEL_HANDED' },
        { time: '2026-01-01T11:00:00Z', description: 'Synthetic carrier message', provider_code: 'ZZZ' },
      ],
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    await expect(service.syncPackage({ id: 'observed', carrier: 'dpd', tracking_number: 'TEST1234' }))
      .resolves.toMatchObject({ updated: 1, errors: 0 });
    const [recorded] = client.recordTrackingStatusObservations.mock.calls[0]!;
    const handed = (recorded as Array<{ observation_key: string; provider_code: string }>)
      .find((observation) => observation.provider_code === 'PARCEL_HANDED');
    expect(recorded).toHaveLength(2);
    expect(client.closeTrackingStatusObservations).toHaveBeenCalledExactlyOnceWith(deployedVersion(), {
      resolution: 'ignored',
      note: "DPD's map leaves it unmapped on purpose: it moves the parcel in transit without a milestone of its own.",
      keys: [handed?.observation_key],
    });
    expect(client.closeTrackingStatusObservations.mock.invocationCallOrder[0])
      .toBeGreaterThan(client.recordTrackingStatusObservations.mock.invocationCallOrder[0]);
  });

  it('persists aggregator observations even when the scraper has already assigned stages', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValue({
      tracking_provider: 'Ship24', status: 'in_transit', current_stage: 'in_transit',
      current_stage_source: 'wording:language',
      events: [
        { time: '2026-01-01T12:00:00Z', description: 'Unrecognized carrier message', stage: 'pending', stage_source: 'none' },
        { time: '2026-01-01T11:00:00Z', description: 'Sorted in regional hub', stage: 'in_transit', stage_source: 'wording:language' },
      ],
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    await expect(service.syncPackage({ id: 'observed', carrier: 'ctt', tracking_number: 'TEST1234' }))
      .resolves.toMatchObject({ updated: 1 });
    expect(client.recordTrackingStatusObservations).toHaveBeenCalledWith([
      expect.objectContaining({ chosen_stage: 'pending', stage_source: 'none' }),
      expect.objectContaining({ chosen_stage: 'in_transit', stage_source: 'wording:language' }),
    ], [], deployedVersion());
    expect(client.recordTrackingStatusObservations.mock.invocationCallOrder[0])
      .toBeGreaterThan(client.insertEvents.mock.invocationCallOrder[0]);
  });

  it('sends the aggregator wording the universal map reads as mapped and files the rest', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValue({
      tracking_provider: 'Ship24', status: 'in_transit', current_stage: 'in_transit',
      current_stage_source: 'wording:language',
      events: [
        { time: '2026-01-01T12:00:00Z', description: 'Unrecognized carrier message', stage: 'pending', stage_source: 'none' },
        { time: '2026-01-01T11:00:00Z', description: 'Sorted in regional hub', stage: 'in_transit', stage_source: 'wording:language' },
      ],
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    await expect(service.syncPackage({ id: 'observed', carrier: 'unknown', tracking_number: 'TEST1234' }))
      .resolves.toMatchObject({ updated: 1 });
    expect(client.recordTrackingStatusObservations).toHaveBeenCalledWith([
      expect.objectContaining({ carrier: 'unknown', chosen_stage: 'pending', stage_source: 'none' }),
    ], [expect.stringMatching(/^[0-9a-f]{64}$/)], deployedVersion());
    expect(client.closeTrackingStatusObservations).not.toHaveBeenCalled();
  });

  it('records an entirely unresolved history without advancing the parcel', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValue({
      tracking_provider: 'Ship24', status: 'pending', current_stage: 'pending', current_stage_source: 'none',
      events: [{ time: '2026-01-01T12:00:00Z', description: 'Unrecognized carrier message',
        stage: 'pending', stage_source: 'none' }],
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    await expect(service.syncPackage({ id: 'observed', carrier: 'ctt', tracking_number: 'TEST1234', current_stage: 'pending' }))
      .resolves.toMatchObject({ waiting: 1, updated: 0, errors: 0 });
    expect(client.recordTrackingStatusObservations).toHaveBeenCalledWith([
      expect.objectContaining({ chosen_stage: 'pending', stage_source: 'none' }),
    ], [], deployedVersion());
    expect(client.updatePackage).toHaveBeenCalledWith('observed', expect.objectContaining({ current_stage: 'pending' }));
  });

  it('reviews unresolved local-clock wording without manufacturing a timeline timestamp', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValue({
      tracking_provider: 'UPU', status: 'pending', current_stage: 'pending', current_stage_source: 'none',
      events: [{ local_time: '2026-01-01T12:00:00', description: 'Unrecognized carrier message',
        stage: 'pending', stage_source: 'none', provider_code: 'ZZ1' }],
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    await expect(service.syncPackage({ id: 'observed', carrier: 'ctt', tracking_number: 'TEST1234', current_stage: 'pending' }))
      .resolves.toMatchObject({ waiting: 1, updated: 0, errors: 0 });
    expect(client.insertEvents).not.toHaveBeenCalled();
    expect(client.recordTrackingStatusObservations).toHaveBeenCalledWith([
      expect.objectContaining({ provider_code: 'ZZ1', chosen_stage: 'pending', stage_source: 'none' }),
    ], [], deployedVersion());
  });

  it('swallows an observation write failure and reports it once', async () => {
    const captured = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
    const client = {
      ...fakeClient(),
      recordTrackingStatusObservations: vi.fn().mockRejectedValue(new Error('observations unavailable')),
    };
    const adapter = { fetch: vi.fn().mockResolvedValue({
      status: 'in_transit',
      last_status_text: 'Estado interno 99',
      last_update: '2026-09-10T11:00:00Z',
      events: [{ time: '2026-09-10T11:00:00Z', description: 'Estado interno 99' }],
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    const parcel = { id: 'observed', carrier: 'ctt', tracking_number: 'TEST1234' };
    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ updated: 1, errors: 0 });
    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ updated: 1, errors: 0 });
    expect(client.recordTrackingStatusObservations).toHaveBeenCalledTimes(2);
    expect(logged.mock.calls.filter(([event]) => event === 'tracking_status_observation_write_failed'))
      .toHaveLength(2);
    expect(captured).toHaveBeenCalledOnce();
  });
});

function fakeClient(packages: JsonObject[] = []) {
  const client = {
    listActivePackages: vi.fn().mockResolvedValue(packages),
    getPackage: vi.fn(async (id: string): Promise<JsonObject | null> => packages.find((parcel) => parcel.id === id) ?? null),
    listFollowedOneOffPackages: vi.fn().mockResolvedValue([]),
    listUnfiledOneOffPackages: vi.fn().mockResolvedValue([]),
    listUnwatchedPackageIds: vi.fn().mockResolvedValue([]),
    sharedAlertPackageIds: vi.fn().mockResolvedValue(new Set()),
    autoLinkPackages: vi.fn().mockResolvedValue(0),
    reopenScanAnnouncements: vi.fn(async (_packageId: string, ids: string[]) => ids.length),
    updatePackage: vi.fn().mockResolvedValue(undefined),
    insertEvents: vi.fn().mockResolvedValue(undefined),
    deleteEventsByDescriptions: vi.fn().mockResolvedValue(undefined),
    startSyncAttempt: vi.fn().mockResolvedValue(undefined),
    completeSyncAttempt: vi.fn().mockResolvedValue(true),
    recordTrackingHealth: vi.fn().mockResolvedValue([]),
    ackTrackingHealth: vi.fn().mockResolvedValue(undefined),
    previousThinAnswer: vi.fn().mockResolvedValue(null),
    recordTrackingStatusObservations: vi.fn().mockResolvedValue(undefined),
    closeTrackingStatusObservations: vi.fn().mockResolvedValue(1),
  };
  return {
    ...client,
    applyTrackingSync: vi.fn(async (
      parcel: JsonObject, values: JsonObject, events: JsonObject[] = [], descriptions: string[] = [],
    ) => {
      if (events.length) await client.insertEvents(events);
      if (descriptions.length) await client.deleteEventsByDescriptions(parcel.id, new Set(descriptions));
      await client.updatePackage(parcel.id, values);
      return true;
    }),
  };
}

describe('TrackingSyncService', () => {
  it('replaces the stage change it saw without a time once the carrier dates that scan by its day', async () => {
    const client = fakeClient();
    const answer: CarrierResult = { status: 'exception', current_stage: 'exception', last_status_text: 'Contacta con agencia',
      last_update: null, last_update_local: '2026-10-19', events: [
        { local_time: '2026-10-19', description: 'Contacta con agencia', stage: 'exception' },
        { local_time: '2026-10-18', description: 'En reparto', stage: 'out_for_delivery' }] };
    const adapter = { fetch: vi.fn().mockResolvedValue(answer) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-10-20T08:00:00Z'));
    // An earlier check saw the stage change and dated it at that check.
    const observed = { provider_event_id: 'nacex:observed', occurred_at: '2026-10-19T17:00:00.000Z', stage: 'exception',
      description: 'Contacta con agencia', observed_without_provider_timestamp: true };
    await service.syncPackage({ id: 'day-dated', carrier: 'nacex', tracking_number: '0000/00000000', current_stage: 'exception',
      [STORED_EVENT_IDENTITIES]: [observed] });
    const [, , rows, , deleted] = client.applyTrackingSync.mock.calls.at(-1)! as unknown as [JsonObject, JsonObject, JsonObject[], string[], string[]];
    expect(rows.map((row: JsonObject) => [row.occurred_at, row.description])).toEqual([
      ['2026-10-18T22:00:00Z', 'Contacta con agencia'], ['2026-10-17T22:00:00Z', 'En reparto']]);
    expect(deleted).toEqual(['nacex:observed']);
    expect(client.completeSyncAttempt).toHaveBeenLastCalledWith(expect.any(String),
      expect.objectContaining({ anomaly_codes: [] }), expect.any(Array));
  });

  it('lets the scan the carrier now dates take over the stage change it saw without a time', async () => {
    const answer: CarrierResult = { status: 'exception', current_stage: 'exception', last_status_text: 'Contacta con agencia',
      last_update: '2026-10-19T14:00:00+02:00', events: [
        { time: '2026-10-19T14:00:00+02:00', description: 'Contacta con agencia', stage: 'exception' },
        { time: '2026-10-18T09:00:00+02:00', description: 'En reparto', stage: 'out_for_delivery' }] };
    // An earlier check saw the stage change and dated it at that check.
    const observed = { provider_event_id: 'nacex:observed', occurred_at: '2026-10-19T17:00:00.000Z', stage: 'exception',
      description: 'Contacta con agencia', observed_without_provider_timestamp: true };
    const sync = async (stored: JsonObject[]) => {
      const client = fakeClient();
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, { fetch: vi.fn().mockResolvedValue(answer) },
        null, () => new Date('2026-10-20T08:00:00Z'));
      await service.syncPackage({ id: 'dated-later', carrier: 'nacex', tracking_number: '0000/00000000', current_stage: 'exception',
        [STORED_EVENT_IDENTITIES]: stored });
      const [, , rows, , deleted] = client.applyTrackingSync.mock.calls.at(-1)! as unknown as [JsonObject, JsonObject, JsonObject[], string[], string[]];
      return { rows: rows.map((row) => [row.provider_event_id, row.occurred_at]), deleted };
    };
    const taken = await sync([observed]);
    expect(taken.rows[0]).toEqual(['nacex:observed', '2026-10-19T12:00:00Z']);
    expect(taken.deleted).toEqual([]);
    // A provider's copy of the scan was stored already: the scan takes the copy over, and the stage change goes.
    const copy = { provider_event_id: 'unknown:copy', occurred_at: '2026-10-19T12:00:00+00:00', stage: 'exception',
      description: 'Contacta con agencia', universal_provider: 'Ship24' };
    const absorbed = await sync([observed, copy]);
    expect(absorbed.rows[0]).toEqual(['unknown:copy', '2026-10-19T12:00:00Z']);
    expect(absorbed.deleted).toEqual(['nacex:observed']);
  });

  it('drops the stage change it saw without a time in words the carrier no longer gives, once a scan of that stage dates it', async () => {
    const client = fakeClient();
    // The scraper now words the scan the earlier check saw otherwise.
    const answer: CarrierResult = { status: 'exception', current_stage: 'exception', last_status_text: 'Contact the agency',
      last_update: '2026-10-19T14:00:00+02:00', events: [
        { time: '2026-10-19T14:00:00+02:00', description: 'Contact the agency', stage: 'exception' },
        { time: '2026-10-18T09:00:00+02:00', description: 'En reparto', stage: 'out_for_delivery' }] };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, { fetch: vi.fn().mockResolvedValue(answer) },
      null, () => new Date('2026-10-20T08:00:00Z'));
    const observed = { provider_event_id: 'nacex:observed', occurred_at: '2026-10-19T17:00:00.000Z', stage: 'exception',
      description: 'Contacta con agencia', observed_without_provider_timestamp: true };
    const scan = { provider_event_id: 'nacex:reparto', occurred_at: '2026-10-18T07:00:00+00:00', stage: 'out_for_delivery', description: 'En reparto' };
    await service.syncPackage({ id: 'reworded', carrier: 'nacex', tracking_number: '0000/00000000', current_stage: 'exception',
      [STORED_EVENT_IDENTITIES]: [observed, scan] });
    const [, , rows, , deleted] = client.applyTrackingSync.mock.calls.at(-1)! as unknown as [JsonObject, JsonObject, JsonObject[], string[], string[]];
    expect(rows.map((row) => [row.occurred_at, row.description])).toContainEqual(['2026-10-19T12:00:00Z', 'Contact the agency']);
    expect(deleted).toEqual(['nacex:observed']);
  });

  it('consumes new-add query context before fetching and never repeats it on refresh', async () => {
    const candidates = ['dpd', 'seur', 'brt', 'relais-colis', 'ciblex', 'hermes-de'].map((carrier) => ({ carrier, needsInput: null, preferred: false }));
    vi.spyOn(scraper, 'recognitionCandidates').mockReturnValue(candidates);
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z' }),
      fetchUniversal: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z' }),
      recognize: vi.fn().mockResolvedValue({ known: false }) };
    const parcel: JsonObject = { id: 'new-add', carrier: 'unknown', tracking_number: '00000000000001', current_stage: 'pending',
      carrier_data: { add_recognition_pending: true, lookup_country_hint: 'CH' } };
    let now = new Date('2026-09-10T12:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    await service.syncPackage(parcel);
    expect(adapter.recognize).toHaveBeenCalledTimes(6);
    const first = client.updatePackage.mock.calls[0][1];
    expect(first).toMatchObject({ sync_status: 'syncing', carrier_data: { lookup_country_hint: 'CH' } });
    expect(first.carrier_data).not.toHaveProperty('add_recognition_pending');
    expect(client.updatePackage.mock.invocationCallOrder[0]).toBeLessThan(adapter.recognize.mock.invocationCallOrder[0]);
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    expect(saved.carrier_data).not.toHaveProperty('add_recognition_pending');
    now = new Date('2026-09-10T14:00:00Z');
    adapter.recognize.mockClear();
    await service.syncPackage({ ...parcel, ...saved });
    expect(adapter.recognize).toHaveBeenCalledTimes(5);
  });

  it.each([
    // Added at 08:20, a number nobody has seen is checked hourly until 14:20: the carriers are asked by then.
    ['within its hourly checks', '2026-09-10T08:20:00Z', '2026-09-10T13:20:00.000Z'],
    // Past them, the checks come every 6 hours and catch the usual wait.
    ['past its hourly checks', '2026-09-10T05:20:00Z', '2026-09-10T15:40:00.000Z'],
  ])('asks the carriers again by the last hourly check of a number nobody has seen, %s', async (_label, created, retry) => {
    const carriers = ['dpd', 'seur', 'brt'];
    vi.spyOn(scraper, 'recognitionCandidates').mockReturnValue(carriers.map((carrier) => ({ carrier, needsInput: null, preferred: false })));
    vi.spyOn(scraper, 'universalPlan').mockReturnValue({ sources: ['ParcelsApp'], carrier: null, tier: () => 'unknown', rank: () => 0 });
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn().mockRejectedValue(new NoHistoryError('ParcelsApp')),
      recognize: vi.fn().mockResolvedValue({ known: false }) };
    const probes = (count: number, at: string) => Object.fromEntries(carriers.map((carrier) => [carrier, { count, retry_at: at }]));
    const parcel: JsonObject = { id: 'unseen', carrier: 'unknown', tracking_number: '00000000000001', current_stage: 'pending',
      created_at: created, carrier_data: { routing: { version: 1, configured_carrier: 'unknown', failures: {}, probe_cursor: 0,
        discovery_cursor: 0, candidate_probes: probes(2, '2026-09-10T11:30:00Z') } } };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T11:40:00Z'));
    await service.syncPackage(parcel);
    expect(adapter.recognize).toHaveBeenCalledTimes(3);
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.routing.candidate_probes).toEqual(probes(3, retry));
  });

  it.each(['ParcelsApp', 'Ship24'] as const)('keeps the country hint through a %s lookup that does not send it and later direct recovery', async (provider) => {
    vi.spyOn(scraper, 'universalPlan').mockReturnValue({
      sources: [provider], carrier: null, tier: () => 'unknown', rank: () => 0,
    });
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const progress = { status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z' };
    const adapter = { fetch: vi.fn().mockRejectedValueOnce(new Error('down')).mockResolvedValue(progress),
      fetchUniversal: vi.fn().mockResolvedValue(progress) };
    const parcel = { id: 'country-hint', carrier: 'dhl', tracking_number: 'TEST1234', current_stage: 'pending', carrier_data: { lookup_country_hint: 'FR' } };
    let now = new Date('2026-09-10T12:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    await service.syncPackage(parcel);
    expect(adapter.fetchUniversal).toHaveBeenCalledExactlyOnceWith(provider, 'TEST1234', expect.any(Number), null, 'Europe/Berlin', 'dhl');
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    expect(saved.carrier_data.lookup_country_hint).toBe('FR');
    expect(saved.carrier_data.destination_country).toBeUndefined();
    now = new Date('2026-09-10T13:00:00Z');
    await service.syncPackage({ ...parcel, ...saved });
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.lookup_country_hint).toBe('FR');
    expect(adapter.fetch).toHaveBeenCalledTimes(2);
    expect(adapter.fetchUniversal).toHaveBeenCalledTimes(1);
  });

  it.each(['ParcelsApp', 'Ship24'] as const)('clears the displayed %s provider after successful direct recovery', async (provider) => {
    vi.spyOn(scraper, 'universalPlan').mockReturnValue({
      sources: [provider], carrier: null, tier: () => 'unknown', rank: () => 0,
    });
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const progress = { status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z' };
    const adapter = { fetch: vi.fn().mockRejectedValueOnce(new Error('down')).mockResolvedValue(progress),
      fetchUniversal: vi.fn().mockResolvedValue(progress) };
    const parcel = { id: 'link-recovery', carrier: 'dhl', tracking_number: 'TEST1234', current_stage: 'pending' };
    let now = new Date('2026-09-10T12:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    await service.syncPackage(parcel);
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    expect(saved.carrier_data.tracking_provider).toBe(provider);
    now = new Date('2026-09-10T13:00:00Z');
    await service.syncPackage({ ...parcel, ...saved });
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.tracking_provider).toBeUndefined();
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.active_tracking_carrier).toBe('dhl');
    expect(adapter.fetch).toHaveBeenCalledTimes(2);
    expect(adapter.fetchUniversal).toHaveBeenCalledTimes(1);
  });

  describe('a delivery only a provider reported', () => {
    // A BRT import a provider marked delivered the day BRT took it over; numbers, wording and clocks are invented.
    const registered = { local_time: '2026-10-12T10:10:00', description: 'Shipping info sent to BRT', stage: 'registered' };
    const own = (status: CarrierResult['status'], text: string, ...newer: Array<{ local_time: string; stage?: string; stage_source?: string; description: string }>): CarrierResult => ({
      status, current_stage: newer[0]?.stage ?? status, last_status_text: text, last_update: null,
      last_update_local: newer[0]?.local_time ?? registered.local_time, events: [...newer, registered] });
    const provider = (status: CarrierResult['status'], delivered: string): CarrierResult => ({
      status, current_stage: status, last_status_text: status === 'delivered' ? 'Delivered' : 'In viaggio',
      last_update: [delivered, '2026-10-17T01:00:02Z'].sort().at(-1),
      events: [{ time: '2026-10-17T01:00:02Z', description: 'In viaggio', stage: 'in_transit' },
        { time: delivered, description: 'Delivered', stage: 'delivered' }] });
    const parcel = () => {
      const store = eventStore();
      const client = Object.assign(store.client, {
        acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
        finishTrackingProvider: vi.fn().mockResolvedValue(undefined) });
      let saved: JsonObject = { id: 'delivered-by-provider', carrier: 'brt', tracking_number: '12345678901234', current_stage: 'pending',
        created_at: '2026-10-10T00:00:00Z' };
      return async (at: string, carrier: CarrierResult | Error, answer: CarrierResult) => {
        const adapter = { fetch: carrier instanceof Error ? vi.fn().mockRejectedValue(carrier) : vi.fn().mockResolvedValue(carrier),
          fetchUniversal: vi.fn().mockResolvedValue(answer) };
        await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date(at))
          .syncPackage({ ...saved, [STORED_EVENT_IDENTITIES]: store.identities() });
        saved = { ...saved, ...client.applyTrackingSync.mock.calls.at(-1)![1] };
        return { ...saved, stages: [...store.rows.values()].map((row) => row.stage) } as JsonObject & { stages: unknown[] };
      };
    };

    it('gives way in the same check to the carrier\'s own answer that moved the parcel after it', async () => {
      const check = parcel();
      const checked = await check('2026-10-19T20:00:00Z', own('in_transit', 'In transito'), provider('delivered', '2026-10-12T01:00:01Z'));
      expect(checked).toMatchObject({ current_stage: 'in_transit', last_status_text: 'In transito' });
      expect(checked.stages).not.toContain('delivered');
      expect(checked.carrier_data).not.toHaveProperty('unconfirmed_delivery_until');
    });

    it('is checked again for a while, and reopens once the carrier moves the parcel after it', async () => {
      const check = parcel();
      // The carrier was down: the provider's delivery is saved, to be checked again for 3 days.
      const first = await check('2026-10-19T20:00:00Z', new Error('down'), provider('delivered', '2026-10-12T12:00:01Z'));
      expect(first).toMatchObject({ current_stage: 'delivered', carrier_data: { unconfirmed_delivery_until: '2026-10-22T20:00:00.000Z' } });
      expect(first.stages).toContain('delivered');
      // The carrier answers alone with a relay scan after the delivery, unlike its registration, and without a stage: its
      // wording reads as in transit. The provider dated its copy of that scan by the next day, later than the carrier's.
      const relay = { time: '2026-10-17T00:53:23+02:00', description: 'Your parcel is on its way' };
      const reopened = await check('2026-10-21T08:00:00Z', { status: 'in_transit', current_stage: 'in_transit',
        last_status_text: 'In viaggio', last_update: relay.time, events: [relay, registered] }, provider('in_transit', '2026-10-12T12:00:01Z'));
      expect(reopened).toMatchObject({ current_stage: 'in_transit', last_status_text: 'In viaggio' });
      expect(reopened.stages).not.toContain('delivered');
      expect(reopened.carrier_data).not.toHaveProperty('unconfirmed_delivery_until');
    });

    it('stays delivered, checked again, when the carrier\'s only scans after it are notices in no stage of their own', async () => {
      // Synthetic notices BRT dates after the provider's delivery: one no rule reads, one that keeps the stage of the
      // scan before it. Neither moves the parcel, so neither disputes the delivery.
      for (const notice of [{ local_time: '2026-10-20T18:00:00', description: 'Fixture notice 7' },
        { local_time: '2026-10-20T18:00:00', description: 'Fixture notice 8', stage: 'registered', stage_source: 'none' }]) {
        const checked = await parcel()('2026-10-20T19:00:00Z', own('in_transit', notice.description, notice), provider('delivered', '2026-10-20T14:32:00Z'));
        expect(checked).toMatchObject({ current_stage: 'delivered', carrier_data: { unconfirmed_delivery_until: '2026-10-23T19:00:00.000Z' } });
        expect(checked.stages).toContain('delivered');
      }
      // A scan the carrier gives a stage of its own after the delivery still disputes it.
      const moved = { local_time: '2026-10-20T18:00:00', description: 'Fixture scan 9', stage: 'in_transit', stage_source: 'carrier_map' };
      const disputed = await parcel()('2026-10-20T19:00:00Z', own('in_transit', moved.description, moved), provider('delivered', '2026-10-20T14:32:00Z'));
      expect(disputed).toMatchObject({ current_stage: 'in_transit' });
      expect(disputed.stages).not.toContain('delivered');
    });

    it('stays delivered when the carrier\'s newest scan is older, and ends the follow-up once the carrier shows it', async () => {
      const check = parcel();
      const delivering = { local_time: '2026-10-20T09:53:00', description: 'In consegna', stage: 'out_for_delivery' };
      const first = await check('2026-10-20T15:00:00Z', own('out_for_delivery', 'In consegna', delivering), provider('delivered', '2026-10-20T14:32:00Z'));
      expect(first).toMatchObject({ current_stage: 'delivered', carrier_data: { unconfirmed_delivery_until: '2026-10-23T15:00:00.000Z' } });
      const again = await check('2026-10-21T15:00:00Z', own('out_for_delivery', 'In consegna', delivering), provider('delivered', '2026-10-20T14:32:00Z'));
      expect(again).toMatchObject({ current_stage: 'delivered', carrier_data: { unconfirmed_delivery_until: '2026-10-23T15:00:00.000Z' } });
      expect(again.stages).toContain('delivered');
      const confirmed = await check('2026-10-22T15:00:00Z',
        own('delivered', 'Consegnata', { local_time: '2026-10-20T15:30:00', description: 'Consegnata', stage: 'delivered' }, delivering),
        provider('delivered', '2026-10-20T14:32:00Z'));
      expect(confirmed.current_stage).toBe('delivered');
      expect(confirmed.carrier_data).not.toHaveProperty('unconfirmed_delivery_until');
    });
  });

  describe('a return after the delivery', () => {
    // A Poste Italiane parcel left at a pickup point and sent back; the number, wording and clocks are invented.
    const delivery = [
      { time: '2026-10-01T11:40:00+02:00', description: 'Consegnata al punto di ritiro', stage: 'delivered' },
      { time: '2026-10-01T08:10:00+02:00', description: 'In consegna', stage: 'out_for_delivery' },
      { time: '2026-09-30T20:00:00+02:00', description: 'In transito', stage: 'in_transit' },
      { time: '2026-09-29T17:00:00+02:00', description: 'Accettata', stage: 'accepted' }];
    const answer = (status: CarrierResult['status'], stage: string, ...newer: Array<{ time: string; description: string; stage: string }>): CarrierResult => {
      const events = [...newer, ...delivery];
      return { status, current_stage: stage, last_status_text: events[0]!.description, last_update: events[0]!.time, events };
    };
    const delivered = answer('delivered', 'delivered');
    const parcel = () => {
      const store = eventStore();
      const client = Object.assign(store.client, {
        acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
        finishTrackingProvider: vi.fn().mockResolvedValue(undefined) });
      let saved: JsonObject = { id: 'sent-back', carrier: 'poste-italiane', tracking_number: `RA${s10('20481357')}IT`,
        current_stage: 'pending', created_at: '2026-09-29T09:00:00Z' };
      return async (at: string, carrier: CarrierResult) => {
        const adapter = { fetch: vi.fn().mockResolvedValue(carrier), fetchUniversal: vi.fn().mockRejectedValue(new Error('not asked')) };
        await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date(at))
          .syncPackage({ ...saved, [STORED_EVENT_IDENTITIES]: store.identities() });
        saved = { ...saved, ...client.applyTrackingSync.mock.calls.at(-1)![1] };
        const attempt = client.completeSyncAttempt.mock.calls.at(-1)![1] as JsonObject;
        return { ...saved, stages: [...store.rows.values()].map((row) => row.stage), anomalies: attempt.anomaly_codes } as JsonObject;
      };
    };

    it('takes the place of the delivery it came after, and stays once the sender has it back', async () => {
      const check = parcel();
      expect(await check('2026-10-01T18:00:00Z', delivered)).toMatchObject({ current_stage: 'delivered' });
      const sentBack = await check('2026-10-09T10:00:00Z', answer('exception', 'returned',
        { time: '2026-10-09T09:30:00+02:00', description: 'In consegna', stage: 'out_for_delivery' },
        { time: '2026-10-08T15:00:00+02:00', description: 'In transito', stage: 'in_transit' },
        { time: '2026-10-08T08:00:00+02:00', description: 'In restituzione al mittente', stage: 'returned' }));
      expect(sentBack).toMatchObject({ current_stage: 'returned', last_status_text: 'In consegna', expected_delivery: null });
      expect(sentBack.stages).toEqual(expect.arrayContaining(['delivered', 'returned', 'out_for_delivery']));
      expect(sentBack.anomalies).not.toContain('terminal_stage_regression');
      // The delivery to the sender ends the return: the parcel stays returned.
      const back = await check('2026-10-10T10:00:00Z', answer('delivered', 'delivered',
        { time: '2026-10-10T09:00:00+02:00', description: 'Consegnata al mittente', stage: 'delivered' },
        { time: '2026-10-09T09:30:00+02:00', description: 'In consegna', stage: 'out_for_delivery' }));
      expect(back).toMatchObject({ current_stage: 'returned', last_status_text: 'In consegna' });
    });

    it('does not take the place of a delivery at its instant or later', async () => {
      const check = parcel();
      await check('2026-10-01T18:00:00Z', delivered);
      const same = await check('2026-10-02T10:00:00Z', answer('exception', 'returned',
        { time: '2026-10-01T11:40:00+02:00', description: 'In restituzione al mittente', stage: 'returned' }));
      expect(same).toMatchObject({ current_stage: 'delivered', last_status_text: 'Consegnata al punto di ritiro' });
      expect(same.anomalies).toContain('terminal_stage_regression');
    });
  });

  describe('a provider\'s notice after the carrier\'s newest scan', () => {
    // A BRT import whose origin printed its label again after BRT's pre-advice; numbers, wording and clocks are invented.
    const own: CarrierResult = { status: 'pending', current_stage: 'registered', last_status_text: 'Shipping info sent to BRT',
      last_update: '2026-10-13T13:33:00+02:00', events: [
        { time: '2026-10-13T13:33:00+02:00', description: 'Shipping info sent to BRT', stage: 'registered', location: 'Hub' },
        { time: '2026-10-13T08:21:18+08:00', description: 'Parcel handed to DPD', stage: 'accepted' }] };
    const provider = (...newer: Array<{ time: string; description: string; stage: string }>): CarrierResult => ({
      status: 'pending', current_stage: newer[0]?.stage ?? 'registered', last_status_text: newer[0]?.description ?? 'Order printed',
      last_update: newer[0]?.time ?? '2026-10-15T03:12:05Z', events: [...newer,
        { time: '2026-10-15T03:12:05Z', description: 'Order printed', stage: 'registered' },
        { time: '2026-10-13T08:25:09Z', description: 'Order created', stage: 'registered' }] });
    const parcel = () => {
      const store = eventStore();
      const client = Object.assign(store.client, {
        acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
        finishTrackingProvider: vi.fn().mockResolvedValue(undefined) });
      let saved: JsonObject = { id: 'origin-notice', carrier: 'brt', tracking_number: '12345678901234', current_stage: 'pending',
        created_at: '2026-10-20T09:00:00Z' };
      return async (at: string, carrier: CarrierResult | Error, answer: CarrierResult) => {
        const adapter = { fetch: carrier instanceof Error ? vi.fn().mockRejectedValue(carrier) : vi.fn().mockResolvedValue(carrier),
          fetchUniversal: vi.fn().mockResolvedValue(answer) };
        await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date(at))
          .syncPackage({ ...saved, [STORED_EVENT_IDENTITIES]: store.identities() });
        saved = { ...saved, ...client.applyTrackingSync.mock.calls.at(-1)![1] };
        return saved;
      };
    };

    it('gives the summary to the carrier\'s answer once it shows that stage, and keeps the watermark', async () => {
      const check = parcel();
      const first = await check('2026-10-20T09:01:00Z', new Error('down'), provider());
      expect(first).toMatchObject({ current_stage: 'registered', last_status_text: 'Order printed',
        carrier_data: { tracking_provider: expect.any(String) } });
      const answered = await check('2026-10-21T06:08:00Z', own, provider());
      expect(answered).toMatchObject({ current_stage: 'registered', last_status_text: 'Shipping info sent to BRT',
        carrier_data: { routing: { last_event_at: '2026-10-15T03:12:05.000Z' } } });
      expect(answered.carrier_data).not.toHaveProperty('tracking_provider');
      // The provider answering while the carrier is down repeats the notice: the carrier's summary stays.
      const down = await check('2026-10-21T08:00:00Z', new Error('down'), provider());
      expect(down).toMatchObject({ last_status_text: 'Shipping info sent to BRT' });
      expect(down.carrier_data).not.toHaveProperty('tracking_provider');
    });

    it('leaves the summary to a provider whose later scan moved the parcel further', async () => {
      const check = parcel();
      const moved = { time: '2026-10-16T02:00:00Z', description: 'Departed from origin', stage: 'in_transit' };
      await check('2026-10-20T09:01:00Z', new Error('down'), provider(moved));
      const kept = await check('2026-10-21T06:08:00Z', own, provider(moved));
      expect(kept).toMatchObject({ current_stage: 'in_transit', last_status_text: 'Departed from origin',
        carrier_data: { tracking_provider: expect.any(String), routing: { last_event_at: '2026-10-16T02:00:00.000Z' } } });
    });
  });

  it('saves provider identity for an unknown parcel without claiming a direct answer', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    const adapter = { fetch: vi.fn().mockRejectedValue(new Error('carrier blocked')),
      fetchUniversal: vi.fn().mockResolvedValue({ status: 'delivered', current_stage: 'delivered',
        discovered_carrier: 'dhl-express', reported_carriers: ['DHL Express'],
        events: [{ time: '2026-09-10T11:00:00Z', description: 'Delivered', stage: 'delivered' }] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-09-10T12:00:00Z'));
    await service.syncPackage({ id: 'provider-identity', carrier: 'unknown', tracking_number: '1234067892', current_stage: 'pending' });
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    expect(saved).toMatchObject({ carrier: 'dhl-express', current_stage: 'delivered', carrier_data: {
      tracking_provider: 'Ship24', routing: { configured_carrier: 'dhl-express', preferred_number: '1234067892' } } });
    expect(saved.carrier_data).not.toHaveProperty('carrier_answered');
    expect(saved.carrier_data.routing).not.toHaveProperty('confirmed_carrier');
    expect(saved.carrier_data).not.toHaveProperty('auto_changed_from');
  });

  it('follows a delivery only a provider reported under the carrier it files an unknown parcel under', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    // The carrier did not answer; an Add preflight's history leaves it unasked alike.
    const adapter = { fetch: vi.fn().mockRejectedValue(new Error('carrier blocked')),
      fetchUniversal: vi.fn().mockResolvedValue({ status: 'delivered', current_stage: 'delivered',
        discovered_carrier: 'dhl-express', reported_carriers: ['DHL Express'],
        events: [{ time: '2026-09-10T11:00:00Z', description: 'Delivered', stage: 'delivered' }] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-09-10T12:00:00Z'));
    await service.syncPackage({ id: 'delivered-at-add', carrier: 'unknown', tracking_number: '1234067892', current_stage: 'pending' });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ carrier: 'dhl-express', current_stage: 'delivered',
      carrier_data: { tracking_provider: 'Ship24', unconfirmed_delivery_until: '2026-09-13T12:00:00.000Z' } });
  });

  it('files a delivered unknown parcel under the carrier a provider named once a release\'s check confirms it', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    const delivered = { status: 'delivered', current_stage: 'delivered', last_update: '2026-09-08T10:00:00Z',
      events: [{ time: '2026-09-07T09:00:00Z', description: 'In transit', stage: 'in_transit' },
        { time: '2026-09-08T10:00:00Z', description: 'Delivered', stage: 'delivered' }] };
    const adapter = { fetch: vi.fn().mockResolvedValue(delivered),
      fetchUniversal: vi.fn().mockResolvedValue({ ...delivered, discovered_carrier: 'dhl-express', reported_carriers: ['DHL Express'] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-09-10T12:00:00Z'));
    await service.syncPackage({ id: 'delivered', carrier: 'unknown', tracking_number: '1234067892', current_stage: 'delivered',
      created_at: '2026-09-06T08:00:00Z', last_synced_at: '2026-09-08T11:00:00Z',
      carrier_data: { tracking_provider: 'ParcelsApp', reported_carriers: ['DHL Express'],
        routing: { version: 1, configured_carrier: 'unknown', failures: {}, probe_cursor: 0, discovery_cursor: 0, rules: 'older',
          preferred_provider: 'ParcelsApp', last_event_at: '2026-09-08T10:00:00.000Z' } } }, { trigger: 'release' });
    expect(adapter.fetch.mock.calls.map(([carrier]) => carrier)).toEqual(['dhl-express']);
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ carrier: 'dhl-express',
      carrier_data: { routing: { configured_carrier: 'dhl-express', confirmed_carrier: 'dhl-express' } } });
  });

  it('files an unknown parcel under the carrier a provider names even when its copy looks older', async () => {
    const sync = async (savedProvider: string) => {
      const client = { ...fakeClient(),
        acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
        finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
      const adapter = { fetch: vi.fn().mockRejectedValue(new Error('carrier blocked')),
        fetchUniversal: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z',
          discovered_carrier: 'dhl-express', reported_carriers: ['DHL Express'],
          events: [{ time: '2026-09-10T11:00:00Z', description: 'In transit', stage: 'in_transit' }] }) };
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null,
        () => new Date('2026-09-10T12:00:00Z'));
      await service.syncPackage({ id: 'older-copy', carrier: 'unknown', tracking_number: '1234067892', current_stage: 'in_transit',
        last_status_text: 'Arrived at the hub',
        carrier_data: { tracking_provider: savedProvider, reported_carriers: ['DHL'],
          routing: { version: 1, configured_carrier: 'unknown', last_event_at: '2026-09-10T11:30:00.000Z' } },
        [STORED_EVENT_IDENTITIES]: [{ provider_event_id: 'unknown:saved', occurred_at: '2026-09-10T11:30:00.000Z',
          stage: 'in_transit', description: 'Arrived at the hub' }] });
      return client.updatePackage.mock.calls.at(-1)![1];
    };
    const filed = await sync('ParcelsApp');
    expect(filed).toMatchObject({ carrier: 'dhl-express', tracking_url: null, dpd_postcode: null, carrier_data: {
      tracking_provider: 'ParcelsApp', discovered_carrier: 'dhl-express', reported_carriers: ['DHL Express'],
      routing: { configured_carrier: 'dhl-express', discovered_carrier: 'dhl-express', last_event_at: '2026-09-10T11:30:00.000Z' } } });
    // The newer saved summary stays.
    expect(filed).not.toHaveProperty('last_status_text');
    expect(filed).not.toHaveProperty('current_stage');
    // The database files a parcel on a provider's word only beside a provider's summary.
    const kept = await sync('UPU');
    expect(kept).not.toHaveProperty('carrier');
    expect(kept.carrier_data.routing.configured_carrier).toBe('unknown');
  });

  it('atomically persists a verified correction and retains its timestamp on later syncs', async () => {
    const report = vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockRejectedValueOnce(new Error('wrong carrier')).mockResolvedValue({
      status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z',
      events: [{ time: '2026-09-10T11:00:00Z', description: 'Confirmed UPS history', stage: 'in_transit' }],
    }), fetchUniversal: vi.fn() };
    const parcel = { id: 'correction', carrier: 'dhl', tracking_number: '1Z999AA10123456784', current_stage: 'pending' };
    let now = new Date('2026-09-10T12:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ updated: 1 });
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    expect(saved).toMatchObject({ carrier: 'ups', tracking_url: null, dpd_postcode: null, carrier_data: {
      auto_changed_from: 'dhl', auto_changed_to: 'ups', auto_changed_at: now.toISOString(),
    } });
    expect(client.applyTrackingSync.mock.calls.at(-1)?.[2]).toHaveLength(1);
    expect(report).toHaveBeenCalledWith('carrier_auto_swapped', expect.objectContaining({ carrier: 'dhl', provider: 'ups' }));
    now = new Date('2026-09-10T13:00:00Z');
    await service.syncPackage({ ...parcel, ...saved });
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.auto_changed_at).toBe('2026-09-10T12:00:00.000Z');
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier).toBeUndefined();
    expect(adapter.fetchUniversal).not.toHaveBeenCalled();
  });

  it('marks a correction to the carrier detection names or asks first, and no other', async () => {
    const report = vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const progress = { status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z',
      events: [{ time: '2026-09-10T11:00:00Z', description: 'In transit', stage: 'in_transit' }] };
    const sync = async (trackingNumber: string, carrier = 'ups') => {
      const client = { ...fakeClient(),
        acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
        finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
      const adapter = { fetch: vi.fn().mockResolvedValue(progress),
        fetchUniversal: vi.fn().mockResolvedValue({ ...progress, discovered_carrier: carrier }) };
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null,
        () => new Date('2026-09-10T12:00:00Z'));
      await service.syncPackage({ id: 'filed-unknown', carrier: 'unknown', tracking_number: trackingNumber, current_stage: 'pending' });
      expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ carrier });
      return adapter;
    };
    // Detection names UPS for the number: the parcel was filed before it could.
    expect((await sync('1Z999AA10123456784')).fetchUniversal).not.toHaveBeenCalled();
    expect(report).toHaveBeenCalledWith('carrier_auto_swapped',
      { carrier: 'unknown', provider: 'ups', trackingNumber: '1Z999AA10123456784', category: 'detected', attemptId: expect.any(String) });
    // A 12-digit number whose FedEx check digit holds: detection asks FedEx first.
    await sync('314159265355', 'fedex');
    expect(report).toHaveBeenCalledWith('carrier_auto_swapped',
      expect.objectContaining({ carrier: 'unknown', provider: 'fedex', trackingNumber: '314159265355', category: 'detected' }));
    // A made-up number in a shape only DHLink's low rule takes: recognition asks DHLink first.
    await sync('YF2468013579CN', 'dhlink');
    expect(report).toHaveBeenCalledWith('carrier_auto_swapped',
      expect.objectContaining({ carrier: 'unknown', provider: 'dhlink', trackingNumber: 'YF2468013579CN', category: 'detected' }));
    expect(report).not.toHaveBeenCalledWith('carrier_mismatch_confirmed', expect.anything());
    // A provider had to name the carrier: detection has a rule to gain.
    expect((await sync('TEST1234')).fetchUniversal).toHaveBeenCalledOnce();
    expect(report).toHaveBeenCalledWith('carrier_auto_swapped',
      { carrier: 'unknown', provider: 'ups', trackingNumber: 'TEST1234', attemptId: expect.any(String) });
    expect(report).toHaveBeenCalledWith('carrier_mismatch_confirmed', expect.objectContaining({ provider: 'ups', trackingNumber: 'TEST1234' }));
  });

  it('files an unknown parcel under the detected carrier that answered with local clocks once a provider dates it', async () => {
    const report = vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    const adapter = {
      fetch: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit', last_update: null,
        events: [{ local_time: '2026-09-10T09:00:00', description: 'Departed the sorting centre', stage: 'in_transit' }] }),
      fetchUniversal: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z',
        reported_carriers: ['17FEIA', 'DPD'], events: [{ time: '2026-09-10T11:00:00Z', description: 'In transit', stage: 'in_transit' }] }),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-09-10T12:00:00Z'));
    // A synthetic number in 17FEIA's own shape, filed before detection named the carrier.
    const number = 'FEIXY0000000001YQ';
    await service.syncPackage({ id: 'filed-unknown', carrier: 'unknown', tracking_number: number, current_stage: 'in_transit',
      carrier_data: { tracking_provider: 'Ship24', routing: { version: 1, configured_carrier: 'unknown', last_event_at: '2026-09-10T10:00:00.000Z' } } });
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('17feia', number, null, null, null);
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    // The database files a provider-tracked parcel only under the carrier routing settled on.
    expect(saved).toMatchObject({ carrier: '17feia', tracking_url: null, dpd_postcode: null, carrier_data: {
      carrier_answered: true, discovered_carrier: '17feia', routing: { configured_carrier: '17feia', discovered_carrier: '17feia' },
      direct_local_history: { carrier: '17feia', number, events: [expect.objectContaining({ local_time: '2026-09-10T09:00:00' })] },
    } });
    expect(saved.carrier_data.routing).not.toHaveProperty('confirmed_carrier');
    expect(report).toHaveBeenCalledWith('carrier_auto_swapped', expect.objectContaining({ carrier: 'unknown', provider: '17feia', category: 'detected' }));
    // The audit shows the carrier answered, though the provider serves the parcel.
    expect(client.completeSyncAttempt).toHaveBeenCalledWith(expect.anything(), expect.anything(), expect.arrayContaining([
      expect.objectContaining({ step: 'fetch', details: expect.objectContaining({ source_carrier: 'unknown', carrier_answered: true }) })]));
  });

  it('shows a provider’s progress beside a carrier’s label that is days old, the carrier answering too', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    // UPS's public example number; the clocks and wording are invented.
    const number = '1Z999AA10123456784';
    const adapter = {
      fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => {
        // What the tracker's recorder reports of the carrier's lookup.
        healthStepRecorder.lookup({ carrier, finalStep: 'direct', durationMs: 1, attempts: 1, outcome: 'ok', errorType: null });
        return { status: 'pending', current_stage: 'registered', last_update: '2026-09-07T10:00:00Z',
          events: [{ time: '2026-09-07T10:00:00Z', description: 'Shipping label created', stage: 'registered' }] };
      }),
      fetchUniversal: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'customs', discovered_carrier: 'ups', events: [
        { time: '2026-09-10T09:00:00Z', description: 'Customs clearance started', stage: 'customs' },
        { time: '2026-09-07T10:00:00Z', description: 'Shipping label created', stage: 'registered' }] }),
    };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'))
      .syncPackage({ id: 'label-days-old', carrier: 'ups', tracking_number: number, current_stage: 'registered' }, { trigger: 'scheduled' });
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('ups', number, null, null, null);
    expect(adapter.fetchUniversal).toHaveBeenCalledOnce();
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    expect(saved).toMatchObject({ current_stage: 'customs', carrier_data: { carrier_answered: true, routing: { confirmed_carrier: 'ups' } } });
    expect(client.insertEvents.mock.calls.flatMap(([rows]) => rows.map((row: JsonObject) => row.description)))
      .toContain('Customs clearance started');
    expect(client.completeSyncAttempt).toHaveBeenCalledWith(expect.anything(), expect.anything(), expect.arrayContaining([
      expect.objectContaining({ step: 'fetch', details: expect.objectContaining({ source_carrier: 'unknown', carrier_answered: true, direct_outcome: 'ok' }) })]));
  });

  it('files an unknown parcel under the carrier only recognition found once a provider naming none dates its local-clock answer', async () => {
    const report = vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    vi.spyOn(scraper, 'universalPlan').mockReturnValue({ sources: ['Postal Ninja'], carrier: null, tier: () => 'unknown', rank: () => 0 });
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    // A shape detection is unsure of. STO, which alone knows it, lists its scans on Shanghai's
    // clock: 09:00 there is the provider's 01:00 UTC.
    const number = '770000000000001';
    const adapter = {
      recognize: vi.fn(async (carrier: string) => ({ known: carrier === 'sto' })),
      fetch: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit', last_update: null,
        events: [{ local_time: '2026-09-10T09:00:00', description: 'Departed the sorting centre', stage: 'in_transit' }] }),
      fetchUniversal: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T01:00:00Z',
        events: [{ time: '2026-09-10T01:00:00Z', description: 'In transit', stage: 'in_transit' }] }),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-09-10T12:00:00Z'));
    await service.syncPackage({ id: 'recognized', carrier: 'unknown', tracking_number: number, current_stage: 'pending' });
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('sto', number, null, null, null);
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    // The database files it as a probe's confirmation: the carrier confirmed for the number, swapped from unknown.
    expect(saved).toMatchObject({ carrier: 'sto', tracking_url: null, dpd_postcode: null, current_stage: 'in_transit', carrier_data: {
      tracking_provider: 'Postal Ninja', carrier_answered: true, auto_changed_from: 'unknown', auto_changed_to: 'sto',
      routing: { configured_carrier: 'sto', confirmed_carrier: 'sto', confirmed_number: number },
      direct_local_history: { carrier: 'sto', number, events: [expect.objectContaining({ local_time: '2026-09-10T09:00:00' })] },
    } });
    expect(report).toHaveBeenCalledWith('candidate_probe_confirmed', expect.objectContaining({ carrier: 'unknown', provider: 'sto' }));
    expect(report).toHaveBeenCalledWith('carrier_auto_swapped', expect.objectContaining({ carrier: 'unknown', provider: 'sto' }));
  });

  it('keeps no carrier confirmed for a parcel it leaves unknown, so a later check can file it', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    const local = (stage: string, description: string) => ({ status: 'in_transit', current_stage: stage, last_update: null,
      events: [{ local_time: '2026-09-10T09:00:00', description, stage }] });
    const dated = (time: string) => ({ status: 'in_transit', current_stage: 'in_transit', last_update: time,
      reported_carriers: ['17FEIA', 'DPD'], events: [{ time, description: 'In transit', stage: 'in_transit' }] });
    const adapter = {
      fetch: vi.fn().mockResolvedValueOnce(local('registered', 'Shipment information received'))
        .mockResolvedValue(local('in_transit', 'Departed the sorting centre')),
      fetchUniversal: vi.fn().mockResolvedValueOnce(dated('2026-09-10T11:00:00Z')).mockResolvedValue(dated('2026-09-10T18:00:00Z')),
    };
    let now = new Date('2026-09-10T12:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    // A synthetic number in 17FEIA's own shape. An earlier release filed it from a registration on local
    // clocks, which the sync kept out of the provider's saved summary, and saved the carrier as confirmed.
    const number = 'FEIXY0000000001YQ';
    const parcel = { id: 'stale-confirmation', carrier: 'unknown', tracking_number: number, current_stage: 'registered',
      carrier_data: { tracking_provider: 'Ship24', routing: { version: 1, configured_carrier: 'unknown',
        confirmed_carrier: '17feia', confirmed_number: number, confirmed_tracking_url: null, confirmed_postcode: null,
        last_event_at: '2026-09-10T08:00:00.000Z' } } };
    await service.syncPackage(parcel);
    const first = client.updatePackage.mock.calls.at(-1)![1];
    expect(first).not.toHaveProperty('carrier');
    expect(first.carrier_data.routing).toMatchObject({ configured_carrier: 'unknown' });
    for (const key of ['confirmed_carrier', 'confirmed_number', 'confirmed_tracking_url', 'confirmed_postcode']) {
      expect(first.carrier_data.routing).not.toHaveProperty(key);
    }
    // Its carrier moves and a provider dates the progress: the parcel is filed as one never confirmed would be.
    now = new Date('2026-09-10T19:00:00Z');
    await service.syncPackage({ ...parcel, ...first });
    expect(adapter.fetch.mock.calls.map(([carrier]) => carrier)).toEqual(['17feia', '17feia']);
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ carrier: '17feia', carrier_data: {
      discovered_carrier: '17feia', routing: { configured_carrier: '17feia', discovered_carrier: '17feia' } } });
  });

  it('reports and counts a carrier it keeps from filing a parcel once, however many checks confirm it again', async () => {
    const report = vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const recorded = vi.spyOn(metrics, 'recordChecksumRejection').mockImplementation(() => undefined);
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    // A synthetic number whose FedEx check digit fails. A provider named FedEx, whose own lookup
    // still shows the parcel in transit: the sync keeps the saved delivered summary and the parcel unknown.
    const number = '123456789013';
    const adapter = {
      fetch: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z',
        events: [{ time: '2026-09-10T11:00:00Z', description: 'In transit', stage: 'in_transit' }] }),
      fetchUniversal: vi.fn().mockRejectedValue(new NotFoundError('ParcelsApp')),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-09-10T12:00:00Z'));
    let parcel: JsonObject = { id: 'kept-unknown', carrier: 'unknown', tracking_number: number, current_stage: 'delivered',
      carrier_data: { tracking_provider: 'ParcelsApp', routing: { version: 1, configured_carrier: 'unknown',
        discovered_carrier: 'fedex', last_event_at: '2026-09-10T10:00:00.000Z' } } };
    for (let check = 0; check < 2; check++) {
      await service.syncPackage(parcel);
      const saved = client.updatePackage.mock.calls.at(-1)![1];
      expect(saved).not.toHaveProperty('carrier');
      expect(saved.carrier_data.routing).toMatchObject({ configured_carrier: 'unknown', cleared_confirmation: { carrier: 'fedex', number } });
      expect(saved.carrier_data.routing).not.toHaveProperty('confirmed_carrier');
      parcel = { ...parcel, ...saved };
    }
    expect(adapter.fetch.mock.calls.map(([carrier]) => carrier)).toEqual(['fedex', 'fedex']);
    expect(recorded.mock.calls).toEqual([['fedex', 'fedex-1']]);
    expect(report.mock.calls.filter(([code]) => code === 'carrier_mismatch_confirmed')).toEqual([
      ['carrier_mismatch_confirmed', expect.objectContaining({ carrier: 'unknown', provider: 'fedex' })]]);
  });

  it('saves an unknown parcel without its confirmation when the confirmed carrier answers without history and the check is deferred', async () => {
    vi.spyOn(scraper, 'universalPlan').mockReturnValue({ sources: ['ParcelsApp'], carrier: null, tier: () => 'unknown', rank: () => 0 });
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    const adapter = { fetch: vi.fn().mockRejectedValue(new NoHistoryError('Happy-Post')),
      fetchUniversal: vi.fn().mockRejectedValue(new NotFoundError('ParcelsApp')) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-09-10T12:00:00Z'));
    // A synthetic number an earlier check confirmed for Happy-Post, whose summary a provider gave
    // and which already missed a check.
    const number = 'TEST1234';
    await expect(service.syncPackage({ id: 'deferred-confirmation', carrier: 'unknown', tracking_number: number,
      current_stage: 'in_transit', carrier_data: { tracking_provider: 'ParcelsApp', routing: { version: 1,
        configured_carrier: 'unknown', confirmed_carrier: 'happy-post', confirmed_number: number, confirmed_tracking_url: null,
        confirmed_postcode: null, last_success_at: '2026-09-09T12:00:00.000Z', consecutive_failures: 1 } } }))
      .resolves.toMatchObject({ errors: 1 });
    expect(adapter.fetch.mock.calls.map(([carrier]) => carrier)).toEqual(['happy-post']);
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    expect(saved).toMatchObject({ sync_status: 'error', carrier_data: { tracking_provider: 'ParcelsApp', routing: {
      configured_carrier: 'unknown', cleared_confirmation: { carrier: 'happy-post', number },
      failures: { 'happy-post': { kind: 'no_history' }, ParcelsApp: { kind: 'not_found' } } } } });
    for (const key of ['confirmed_carrier', 'confirmed_number', 'confirmed_tracking_url', 'confirmed_postcode']) {
      expect(saved.carrier_data.routing).not.toHaveProperty(key);
    }
  });

  it('persists routing for a universal-only carrier and respects it on the next manual check', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn().mockResolvedValue({ status: 'in_transit',
      current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z', events: [] }) };
    const parcel = { id: 'routed', carrier: 'fedex', tracking_number: 'TEST1234', current_stage: 'pending' };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ updated: 1 });
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    expect(saved.carrier_data.routing).toMatchObject({ preferred_provider: 'ParcelsApp', last_success_at: '2026-09-10T12:00:00.000Z' });
    await expect(service.syncPackage({ ...parcel, ...saved })).resolves.toMatchObject({ checked: 0 });
    expect(adapter.fetchUniversal).toHaveBeenCalledOnce();
  });

  it('keeps last-good progress and persisted success time when a recent direct fetch is rate limited', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockRejectedValue(new UpstreamHttpError('UPS', 429)), fetchUniversal: vi.fn() };
    const parcel = { id: 'rate-limited', carrier: 'ups', tracking_number: 'TEST1234', current_stage: 'in_transit',
      sync_status: 'ok', last_synced_at: '2026-09-10T11:30:00Z', carrier_data: { last_status_text: 'On the way' } };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ waiting: 1, errors: 0 });
    const values = client.updatePackage.mock.calls.at(-1)![1];
    expect(values).toMatchObject({ sync_status: 'ok', carrier_data: { last_status_text: 'On the way', routing: {
      last_success_at: '2026-09-10T11:30:00Z', next_check_at: '2026-09-10T12:15:00.000Z',
    } } });
    expect(values.current_stage).toBeUndefined();
    expect(adapter.fetchUniversal).not.toHaveBeenCalled();
  });

  it.each(['pending', 'registered', 'in_transit'])('persists a missed-check streak for an hourly DPD parcel at %s', async (stage) => {
    const parcel: JsonObject = { id: 'hourly-dpd', carrier: 'dpd', tracking_number: '06080000000001',
      current_stage: stage, last_status_text: 'Saved tracking', sync_status: stage === 'pending' ? 'waiting' : 'ok',
      last_synced_at: '2026-09-10T10:00:00Z', carrier_data: { last_status_text: 'Saved tracking',
        routing: { version: 1, configured_carrier: 'dpd', last_success_at: '2026-09-10T10:00:00Z' } } };
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T11:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    client.updatePackage.mockImplementation(async (_id, values) => { Object.assign(parcel, values); });
    const adapter = { fetch: vi.fn().mockRejectedValue(new UpstreamHttpError('DPD', 503)),
      fetchUniversal: vi.fn().mockRejectedValue(new NotFoundError('Provider')) };
    let now = new Date('2026-09-10T11:00:00Z');
    const service = () => new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    await expect(service().syncPackage(parcel)).resolves.toMatchObject({ waiting: 1, errors: 0 });
    expect(parcel).toMatchObject({ current_stage: stage, last_status_text: 'Saved tracking', sync_error: null,
      sync_status: stage === 'pending' ? 'waiting' : 'ok', carrier_data: { routing: { consecutive_failures: 1 } } });
    expect(client.insertEvents).not.toHaveBeenCalled();
    now = new Date('2026-09-10T12:00:00Z');
    await expect(service().syncPackage(parcel)).resolves.toMatchObject({ errors: 1 });
    expect(parcel).toMatchObject({ current_stage: stage, last_status_text: 'Saved tracking', sync_status: 'error',
      carrier_data: { routing: { consecutive_failures: 2, last_success_at: '2026-09-10T10:00:00Z' } } });
    now = new Date('2026-09-10T13:00:00Z');
    adapter.fetch.mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit',
      last_status_text: 'Recovered tracking', last_update: now.toISOString(), events: [] });
    await expect(service().syncPackage(parcel)).resolves.toMatchObject({ updated: 1, errors: 0 });
    expect(parcel).toMatchObject({ sync_status: 'ok', sync_error: null,
      carrier_data: { routing: { consecutive_failures: 0 } } });
  });

  it('breaks the failure streak when DPD answers that a label has no scans yet', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T11:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockRejectedValueOnce(new NotFoundError('DPD'))
      .mockRejectedValue(new UpstreamHttpError('DPD', 503)),
      fetchUniversal: vi.fn().mockRejectedValue(new NotFoundError('Provider')) };
    const parcel = { id: 'unscanned-dpd', carrier: 'dpd', tracking_number: '06080000000001', current_stage: 'pending',
      carrier_data: { routing: { version: 1, configured_carrier: 'dpd', consecutive_failures: 2 } } };
    let now = new Date('2026-09-10T11:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ waiting: 1, errors: 0 });
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    expect(saved.carrier_data.routing).toMatchObject({ consecutive_failures: 0 });
    now = new Date('2026-09-10T17:00:00Z');
    await expect(service.syncPackage({ ...parcel, ...saved })).resolves.toMatchObject({ waiting: 1, errors: 0 });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'waiting', sync_error: null,
      carrier_data: { routing: { consecutive_failures: 1 } } });
  });

  it('keeps a moving parcel\'s stage when the newest scan is a delivery notice', async () => {
    // A notice filed as an announcement (synthetic wording and times): the
    // summary and the forecast are news, a step back to Announced is not.
    const notice = 'Your parcel will be delivered on Tuesday between 10:00 and 11:00';
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'registered',
      last_status_text: notice, last_update: '2026-09-10T08:00:00Z', expected_delivery: '2026-09-15',
      events: [
        { time: '2026-09-10T08:00:00Z', description: notice, stage: 'registered' },
        { time: '2026-09-09T18:00:00Z', description: 'Arrived at the delivery depot', stage: 'in_transit' },
      ] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    const parcel = { id: 'notice', carrier: 'dpd', tracking_number: 'TEST1234', current_stage: 'out_for_delivery' };
    await service.syncPackage(parcel);
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'out_for_delivery',
      last_status_text: notice, expected_delivery: '2026-09-15' });
    expect(client.completeSyncAttempt).toHaveBeenLastCalledWith(expect.any(String),
      expect.objectContaining({ anomaly_codes: ['early_stage_regression'] }), expect.any(Array));
    // A problem still moves the stage, and a parcel that has not moved yet takes its announcement.
    adapter.fetch.mockResolvedValue({ status: 'exception', current_stage: 'exception', last_status_text: 'Parcel damaged',
      last_update: '2026-09-10T09:00:00Z', events: [{ time: '2026-09-10T09:00:00Z', description: 'Parcel damaged', stage: 'exception' }] });
    await service.syncPackage({ ...parcel, current_stage: 'in_transit' });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'exception' });
    adapter.fetch.mockResolvedValue({ status: 'pending', current_stage: 'registered', last_status_text: 'Label created',
      last_update: '2026-09-10T10:00:00Z', events: [{ time: '2026-09-10T10:00:00Z', description: 'Label created', stage: 'registered' }] });
    await service.syncPackage({ ...parcel, current_stage: 'pending' });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'registered' });
    expect(client.completeSyncAttempt).toHaveBeenLastCalledWith(expect.any(String),
      expect.objectContaining({ anomaly_codes: [] }), expect.any(Array));
  });

  it('reads scans of one second by delivery progress when the summary waits', async () => {
    // Accepted and moved on within one second (synthetic wording and times),
    // the acceptance listed first: the parcel is in transit.
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'pending', last_update: '2026-09-09T23:20:58Z',
      events: [
        { time: '2026-09-09T23:20:58Z', description: 'Parcel received', stage: 'accepted' },
        { time: '2026-09-09T23:20:58Z', description: 'At the distribution centre', stage: 'in_transit' },
      ] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await service.syncPackage({ id: 'same-second', carrier: 'dhl', tracking_number: 'TEST1234', current_stage: 'pending' });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'in_transit' });
  });

  it('follows the carrier once no scan the parcel keeps shows the stage it was held at', async () => {
    // A scraper fix re-reads the scan that moved the parcel as a booked
    // collection (synthetic wording and times): no scan is in transit any more.
    const label = { time: '2026-09-09T13:00:00Z', description: 'Label data received', stage: 'registered' };
    const booked = { time: '2026-09-09T22:00:00Z', description: 'Collection booked for the next working day' };
    const collected = { time: '2026-09-10T08:00:00Z', description: 'Collected from the sender', stage: 'accepted' };
    const row = (event: { time: string; description: string }, stage: string) => ({ stage, description: event.description,
      occurred_at: new Date(event.time).toISOString(), provider_event_id: providerEventId('dhl', event.time, '', event.description) });
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'accepted',
      last_status_text: collected.description, last_update: collected.time,
      events: [collected, { ...booked, stage: 'registered' }, label] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    const parcel = { id: 'restaged', carrier: 'dhl', tracking_number: 'TEST1234', current_stage: 'in_transit',
      [STORED_EVENT_IDENTITIES]: [row(label, 'registered'), row(booked, 'in_transit')] };
    await service.syncPackage(parcel);
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'accepted' });
    expect(client.completeSyncAttempt).toHaveBeenLastCalledWith(expect.any(String),
      expect.objectContaining({ anomaly_codes: [] }), expect.any(Array));
    // A scan in transit that the answer leaves out still holds the stage.
    const sorted = { time: '2026-09-10T06:00:00Z', description: 'Sorted at the hub' };
    await service.syncPackage({ ...parcel, [STORED_EVENT_IDENTITIES]: [...parcel[STORED_EVENT_IDENTITIES], row(sorted, 'in_transit')] });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'in_transit' });
    expect(client.completeSyncAttempt).toHaveBeenLastCalledWith(expect.any(String),
      expect.objectContaining({ anomaly_codes: ['early_stage_regression'] }), expect.any(Array));
  });

  it('keeps one row of a scan its carrier restates without its milliseconds', async () => {
    // Royal Mail dated a locker drop-off to the millisecond, then to the second (synthetic place and number).
    const scan = { description: 'Sender advised item dropped off at parcel locker', location: 'Example Delivery Office RM Parcel Locker',
      stage: 'accepted', provider_code: 'LOPPA' };
    const stored = { ...scan, raw_data: { provider_code: 'LOPPA', time: '2026-10-23T16:31:18.092+01:00' },
      occurred_at: '2026-10-23T15:31:18.092Z', provider_event_id: providerEventId('royal-mail', '2026-10-23T16:31:18.092+01:00', scan.location, scan.description) };
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-10-24T10:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'accepted', last_status_text: scan.description,
      last_update: '2026-10-23T16:31:18+01:00', events: [{ ...scan, time: '2026-10-23T16:31:18+01:00' }] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-10-24T10:00:00Z'));
    await service.syncPackage({ id: 'restated', carrier: 'royal-mail', tracking_number: 'TEST1234', current_stage: 'accepted',
      [STORED_EVENT_IDENTITIES]: [stored] }, { trigger: 'scheduled' });
    const saved = client.insertEvents.mock.calls.flatMap((call) => call[0] as JsonObject[]);
    // The scan updates its stored row in place, now dated to the second.
    expect(saved.map((event) => event.provider_event_id)).toEqual([stored.provider_event_id]);
    expect(Date.parse(String(saved[0]!.occurred_at))).toBe(Date.parse('2026-10-23T15:31:18Z'));
  });

  it('holds a saved stage only as far as the scans the parcel keeps show it', () => {
    const parcel = { [STORED_EVENT_IDENTITIES]: [] };
    const rows = (...stages: string[]) => stages.map((stage) => ({ stage }));
    expect(heldStage(parcel, 'out_for_delivery', rows('registered', 'in_transit'))).toBe('out_for_delivery');
    expect(heldStage(parcel, 'in_transit', rows('registered', 'accepted', 'pending'))).toBe('accepted');
    expect(heldStage(parcel, 'accepted', rows('registered'))).toBe('registered');
    expect(heldStage(parcel, 'in_transit', rows())).toBe('pending');
    // Without its rows, a parcel holds its stage; delivered and returned have their own rule.
    expect(heldStage({}, 'in_transit', rows())).toBe('in_transit');
    expect(heldStage(parcel, 'delivered', rows())).toBe('delivered');
  });

  it('keeps the progress of earlier scans when the newest scan has wording no rule reads', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'pending', current_stage: 'registered',
      last_status_text: 'Noted at the fixture desk', last_update: '2026-09-10T10:00:00Z',
      events: [
        { time: '2026-09-10T10:00:00Z', description: 'Noted at the fixture desk', stage: 'pending' },
        { time: '2026-09-10T08:00:00Z', description: 'Label created', stage: 'registered' },
      ] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await service.syncPackage({ id: 'unread', carrier: 'dpd', tracking_number: 'TEST1234', current_stage: 'pending' });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'registered' });
  });

  it('keeps a delivery or a refusal when a later scan is in no stage', async () => {
    // Synthetic carrier notices no rule gives a stage: one after the delivery, one after a refusal.
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T18:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockResolvedValueOnce({ status: 'delivered', current_stage: 'delivered',
      last_status_text: 'Delivered', last_update: '2026-09-10T14:59:00Z', events: [
        { time: '2026-09-10T15:08:00Z', description: 'Fixture notice 7' },
        { time: '2026-09-10T14:59:00Z', description: 'Delivered', stage: 'delivered' },
      ] }).mockResolvedValueOnce({ status: 'unknown', events: [
        { time: '2026-09-10T15:08:00Z', description: 'Return to origin' },
        { time: '2026-09-10T14:00:00Z', description: 'Refused by the recipient' },
      ] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T18:00:00Z'));
    await service.syncPackage({ id: 'notice', carrier: 'dpd', tracking_number: 'TEST1234', current_stage: 'out_for_delivery' });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'delivered' });
    // The notice is stored, in no stage, so no alert announces it.
    expect(client.insertEvents.mock.calls.at(-1)![0].map((row: JsonObject) => [row.description, row.stage])).toEqual([
      ['Fixture notice 7', 'pending'], ['Delivered', 'delivered'],
    ]);
    await service.syncPackage({ id: 'refused', carrier: 'dpd', tracking_number: 'TEST1234', current_stage: 'exception' });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'exception' });
  });

  it('does not regress a newer saved summary when a fallback returns older history', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-09T10:00:00Z' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await service.syncPackage({ id: 'older', carrier: 'unknown', tracking_number: 'TEST1234', current_stage: 'out_for_delivery',
      carrier_data: { routing: { version: 1, configured_carrier: 'unknown', last_event_at: '2026-09-10T11:00:00Z' } } });
    const values = client.updatePackage.mock.calls.at(-1)![1];
    expect(values.current_stage).toBeUndefined();
    expect(values.carrier_data.routing.last_event_at).toBe('2026-09-10T11:00:00Z');
  });
  it('moves a pending parcel on once its provider drops the notice row that held the watermark', async () => {
    // ParcelsApp dated a notice at the lookup, past the parcel's real scans, and every later
    // answer looked older until a scraper fix left the notice out (synthetic wording and times).
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn().mockResolvedValue({ status: 'delivered', current_stage: 'delivered',
      last_status_text: 'Delivered', last_update: '2026-09-10T05:50:00Z', events: [
        { time: '2026-09-10T05:50:00Z', description: 'Delivered', stage: 'delivered' },
        { time: '2026-09-09T18:00:00Z', description: 'In transit', stage: 'in_transit' }] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    const notice = (provider: string) => ({ provider_event_id: 'unknown:notice', occurred_at: '2026-09-10T11:30:00.000Z', stage: 'pending',
      description: 'Check the number', universal_provider: provider });
    const added = { provider_event_id: 'app:pending', occurred_at: '2026-09-10T10:40:00.000Z', stage: 'pending', description: 'Tracking added' };
    const locked = (row: JsonObject) => ({ id: 'locked', carrier: 'unknown', tracking_number: 'TEST1234', current_stage: 'pending',
      last_status_text: 'Check the number', carrier_data: { tracking_provider: 'ParcelsApp', routing: { version: 1, configured_carrier: 'unknown',
        preferred_provider: 'ParcelsApp', preferred_number: 'TEST1234', last_event_at: '2026-09-10T11:30:00.000Z' } },
      [STORED_EVENT_IDENTITIES]: [added, row] });
    await service.syncPackage(locked(notice('ParcelsApp')));
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'delivered', last_status_text: 'Delivered',
      carrier_data: { routing: { last_event_at: '2026-09-10T05:50:00.000Z' } } });
    // Another source's newer row, a reused number's new label say, still makes this history older.
    for (const row of [notice('Ship24'), { ...notice('ParcelsApp'), universal_provider: undefined }]) {
      await service.syncPackage(locked(row));
      const kept = client.updatePackage.mock.calls.at(-1)![1];
      expect(kept.current_stage).toBeUndefined();
      expect(kept.carrier_data.routing.last_event_at).toBe('2026-09-10T11:30:00.000Z');
    }
  });
  it('keeps the stage its carrier gave while a provider answering in its place shows no newer scan', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    // The provider reads the carrier's last scan as an exception.
    const copy = (time: string, stage: string) => ({ status: stage, current_stage: stage, last_status_text: 'Delivery change',
      last_update: time, events: [{ time, description: 'Delivery change', stage }] });
    const adapter = { fetch: vi.fn().mockRejectedValue(new Error('UPS tracking endpoint is unavailable')), fetchUniversal: vi.fn()
      .mockResolvedValueOnce(copy('2026-09-10T11:00:00Z', 'exception'))
      .mockResolvedValueOnce(copy('2026-09-10T11:30:00Z', 'exception')) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    const parcel = { id: 'ups-down', carrier: 'ups', tracking_number: '1Z0000000000000000', current_stage: 'out_for_delivery',
      carrier_data: { last_status_text: 'Out for delivery', routing: { version: 1, configured_carrier: 'ups', last_event_at: '2026-09-10T11:00:00Z' } } };
    await service.syncPackage(parcel);
    const held = client.updatePackage.mock.calls.at(-1)![1];
    expect(held.current_stage).toBeUndefined();
    expect(held.carrier_data).toMatchObject({ last_status_text: 'Out for delivery', routing: { last_event_at: '2026-09-10T11:00:00Z' } });
    expect(held.carrier_data.tracking_provider).toBeUndefined();
    // A scan the carrier has not shown yet updates it.
    await service.syncPackage(parcel);
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'exception' });
  });
  it('reads a naive local snapshot in its carrier\'s zone before comparing it with the watermark', async () => {
    // India Post reports Kolkata wall time without an offset; routing writes the watermark from that reading.
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-07-01T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const snapshot = (local: string, stage: string, text: string) => ({ status: 'in_transit', current_stage: stage,
      last_status_text: text, last_update: local, events: [{ time: local, description: text, stage }] });
    const adapter = { fetch: vi.fn()
      .mockResolvedValueOnce(snapshot('2026-07-01T10:00:00.000', 'out_for_delivery', 'Out for delivery'))
      .mockResolvedValueOnce(snapshot('2026-07-01T09:00:00.000', 'in_transit', 'In transit')), fetchUniversal: vi.fn() };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-07-01T12:00:00Z'));
    const parcel = { id: 'naive', carrier: 'india-post', tracking_number: 'TEST1234', current_stage: 'pending' };
    await service.syncPackage(parcel);
    const fresh = client.updatePackage.mock.calls.at(-1)![1];
    expect(fresh).toMatchObject({ current_stage: 'out_for_delivery', carrier_data: { routing: { last_event_at: '2026-07-01T04:30:00.000Z' } } });
    // An older snapshot from the same carrier must not regress the summary.
    await service.syncPackage({ ...parcel, ...fresh, last_synced_at: '2026-07-01T11:00:00Z' });
    const stale = client.updatePackage.mock.calls.at(-1)![1];
    expect(stale.current_stage).toBeUndefined();
    expect(stale.carrier_data).toMatchObject({ last_status_text: 'Out for delivery', routing: { last_event_at: '2026-07-01T04:30:00.000Z' } });
  });
  it('reconciles an account right after each check that stored news, in either order, and every account at the end', async () => {
    const packages = [
      { id: 'local', user_id: 'owner', carrier: 'swiss-post', tracking_number: '993412345612345678' },
      { id: 'origin', user_id: 'owner', carrier: 'gls-de', tracking_number: '123456789011' },
    ];
    for (const order of [packages, [...packages].reverse()]) {
      const client = fakeClient(order);
      const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) };
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
      await service.sync();
      expect(client.autoLinkPackages.mock.calls).toEqual([['owner'], ['owner'], [undefined]]);
      const [first, second] = client.completeSyncAttempt.mock.invocationCallOrder;
      expect(client.autoLinkPackages.mock.invocationCallOrder[0]).toBeGreaterThan(first!);
      expect(client.autoLinkPackages.mock.invocationCallOrder[0]).toBeLessThan(second!);
      expect(client.autoLinkPackages.mock.invocationCallOrder[2]).toBeGreaterThan(second!);
    }
  });

  it('sends a parcel’s notifications right after its check, without waiting for a slow one', async () => {
    let answer!: (result: JsonObject) => void;
    const adapter = { fetch: vi.fn(async (_carrier: string, number: string) => number === 'TEST0001'
      ? { status: 'in_transit' }
      : await new Promise<JsonObject>((resolve) => { answer = resolve; })) };
    const notifier = { dispatch: vi.fn().mockResolvedValue({ sent: 1, failed: 0, expired: 0, sentByChannel: { web: 1, native: 0 } }) };
    const emails = { dispatch: vi.fn().mockResolvedValue({ sent: 1, failed: 0, skipped: 0 }) };
    const client = fakeClient([
      { id: 'quick', user_id: 'first', carrier: 'ctt', tracking_number: 'TEST0001' },
      { id: 'slow', user_id: 'second', carrier: 'la-poste', tracking_number: 'TEST0002' },
    ]);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, notifier as never, undefined, emails as never);
    const running = service.sync();
    await vi.waitFor(() => expect(emails.dispatch).toHaveBeenCalledOnce());
    expect(notifier.dispatch).toHaveBeenCalledOnce();
    expect(adapter.fetch).toHaveBeenCalledTimes(2);
    answer({ status: 'in_transit' });
    // The run's own dispatch follows the reconciliation, and the counts add up, each channel's too.
    await expect(running).resolves.toMatchObject({ checked: 2, updated: 2, notifications_sent: 2, emails_sent: 2,
      notifications_sent_web: 2, notifications_sent_native: 0, notifications_sent_live_activity: 0, notifications_sent_link_alert: 0 });
    expect(notifier.dispatch).toHaveBeenCalledTimes(2);
    expect(notifier.dispatch.mock.invocationCallOrder[1]).toBeGreaterThan(client.autoLinkPackages.mock.invocationCallOrder.at(-1)!);
  });

  it('merges a parcel with its other leg before the parcel’s news goes out', async () => {
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) };
    const notifier = { dispatch: vi.fn().mockResolvedValue({ sent: 0, failed: 0, expired: 0 }) };
    const client = fakeClient([
      { id: 'origin', user_id: 'owner', carrier: 'gls-de', tracking_number: 'TEST0001' },
      { id: 'other', user_id: 'neighbour', carrier: 'ctt', tracking_number: 'TEST0003' },
    ]);
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, notifier as never).sync();
    expect(notifier.dispatch).toHaveBeenCalledTimes(2);
    expect(client.autoLinkPackages.mock.calls).toEqual([['owner'], ['neighbour'], [undefined]]);
    expect(notifier.dispatch.mock.invocationCallOrder[0]).toBeGreaterThan(client.autoLinkPackages.mock.invocationCallOrder[0]!);
    expect(notifier.dispatch.mock.invocationCallOrder[0]).toBeLessThan(client.completeSyncAttempt.mock.invocationCallOrder[1]!);
  });

  it('leaves to the next run a parcel a merge earlier in the run removed or gave another leg', async () => {
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) };
    const parcels = [
      { id: 'origin', user_id: 'owner', carrier: 'gls-de', tracking_number: 'TEST0001', carrier_data: {} },
      { id: 'local', user_id: 'owner', carrier: 'swiss-post', tracking_number: 'TEST0002', carrier_data: {} },
      { id: 'gone', user_id: 'owner', carrier: 'dpd-de', tracking_number: 'TEST0003' },
      { id: 'unrelated', user_id: 'owner', carrier: 'ctt', tracking_number: 'TEST0004' },
      { id: 'unread', user_id: 'owner', carrier: 'ctt', tracking_number: 'TEST0005' },
      { id: 'elsewhere', user_id: 'neighbour', carrier: 'ctt', tracking_number: 'TEST0006' },
    ];
    const client = fakeClient(parcels);
    client.autoLinkPackages.mockResolvedValueOnce(1);
    const failure = new Error('parcel unavailable');
    client.getPackage.mockImplementation(async (id: string) => {
      if (id === 'local') return { ...parcels[1], carrier_data: { original_package_id: 'origin' } };
      if (id === 'unread') throw failure;
      return id === 'gone' ? null : parcels.find((parcel) => parcel.id === id) ?? null;
    });
    await expect(new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null).sync())
      .resolves.toMatchObject({ checked: 4 });
    expect(adapter.fetch.mock.calls.map(([, number]) => number)).toEqual(['TEST0001', 'TEST0006', 'TEST0004', 'TEST0005']);
    expect(client.getPackage.mock.calls.map(([id]) => id)).toEqual(['local', 'gone', 'unrelated', 'unread']);
    expect(report).toHaveBeenCalledExactlyOnceWith(failure, { component: 'tracking', operation: 'reread_merged_parcel' });
  });

  it('merges no account for parcels without one, nor after a check without news', async () => {
    const client = fakeClient();
    client.listFollowedOneOffPackages.mockResolvedValue([
      { id: 'first', user_id: null, one_off: true, carrier: 'ctt', tracking_number: 'TEST0001' },
    ]);
    await new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null).sync();
    expect(client.autoLinkPackages).toHaveBeenCalledExactlyOnceWith(undefined);
    const quiet = fakeClient([
      { id: 'first', user_id: 'owner', carrier: 'ctt', tracking_number: 'TEST0001' },
    ]);
    await new TrackingSyncService(quiet as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockRejectedValue(new Error('carrier unavailable')) }, null).sync();
    expect(quiet.autoLinkPackages).toHaveBeenCalledExactlyOnceWith(undefined);
  });

  it('reconciles a manual refresh within its owner’s account', async () => {
    const client = fakeClient();
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null);
    await service.syncPackage({ id: 'parcel', user_id: 'owner', carrier: 'swiss-post', tracking_number: 'TEST1234' });
    expect(client.autoLinkPackages).toHaveBeenCalledExactlyOnceWith('owner');
  });

  it('checks a parcel without an owner without reconciling any account', async () => {
    const client = fakeClient();
    const notifier = { dispatch: vi.fn().mockResolvedValue({ sent: 0, failed: 0, expired: 0 }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, notifier as never);
    await expect(service.syncPackage({ id: 'one-off', user_id: null, one_off: true, carrier: 'swiss-post', tracking_number: 'TEST1234' }))
      .resolves.toMatchObject({ checked: 1, updated: 1, errors: 0 });
    expect(client.autoLinkPackages).not.toHaveBeenCalled();
    // The push queues join on the owner, so the shared dispatch has nothing to send for it.
    expect(notifier.dispatch).toHaveBeenCalledOnce();
  });

  it('sends the delivery emails after the notifications of every job, and counts them', async () => {
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) };
    const notifier = { dispatch: vi.fn().mockResolvedValue({ sent: 2, failed: 0, expired: 1 }) };
    const emails = { dispatch: vi.fn().mockResolvedValue({ sent: 3, failed: 1, skipped: 4 }) };
    const service = new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient, adapter, notifier as never, undefined, emails as never);
    const controller = new AbortController();
    const parcel = { id: 'parcel', user_id: 'owner', carrier: 'swiss-post', tracking_number: 'TEST1234' };
    await expect(service.syncPackage(parcel, { trigger: 'package', signal: controller.signal })).resolves.toMatchObject({
      checked: 1, notifications_sent: 2, notification_errors: 0, subscriptions_expired: 1, emails_sent: 3, email_errors: 1,
    });
    expect(emails.dispatch).toHaveBeenCalledExactlyOnceWith(controller.signal);
    expect(emails.dispatch.mock.invocationCallOrder[0]).toBeGreaterThan(notifier.dispatch.mock.invocationCallOrder[0]!);
    await expect(service.sync()).resolves.toMatchObject({ emails_sent: 3, email_errors: 1 });
    expect(emails.dispatch).toHaveBeenCalledTimes(2);
  });

  it('sends the delivery emails on a deployment without push, and after a push dispatch that failed', async () => {
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) };
    const emails = { dispatch: vi.fn().mockResolvedValue({ sent: 1, failed: 0, skipped: 0 }) };
    const parcel = { id: 'parcel', user_id: 'owner', carrier: 'swiss-post', tracking_number: 'TEST1234' };
    const withoutPush = new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient, adapter, null, undefined, emails as never);
    await expect(withoutPush.syncPackage(parcel)).resolves.toMatchObject({ notifications_sent: 0, emails_sent: 1, email_errors: 0 });
    expect(emails.dispatch).toHaveBeenCalledOnce();

    const notifier = { dispatch: vi.fn().mockRejectedValue(new Error('push queue unavailable')) };
    const afterFailure = new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient, adapter, notifier as never, undefined, emails as never);
    await expect(afterFailure.syncPackage(parcel)).resolves.toMatchObject({ notification_errors: 1, emails_sent: 1 });
    expect(emails.dispatch).toHaveBeenCalledTimes(2);
    expect(report).toHaveBeenCalledExactlyOnceWith(expect.any(Error), { component: 'push', operation: 'dispatch' });
    // Without mail settings there is no service, and the summary says nothing was sent.
    await expect(new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient, adapter, null).syncPackage(parcel))
      .resolves.toMatchObject({ emails_sent: 0, email_errors: 0 });
  });

  it('reports failed push deliveries only when the next dispatch fails again', async () => {
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const notifier = { dispatch: vi.fn() };
    const service = new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, notifier as never);
    const parcel = { id: 'parcel', user_id: 'owner', carrier: 'swiss-post', tracking_number: 'TEST1234' };
    const dispatch = async (...failures: number[]) => {
      for (const failed of failures) {
        notifier.dispatch.mockResolvedValueOnce({ attempted: 1, sent: 1 - failed, failed, expired: 0 });
        await expect(service.syncPackage(parcel)).resolves.toMatchObject({ notification_errors: failed });
      }
    };
    // A failed send stays pending, and the next dispatch delivers what a push service's blip held back.
    await dispatch(1, 0, 1, 0);
    expect(report).not.toHaveBeenCalled();
    // Sends that keep failing are reported, less often as the streak goes on.
    await dispatch(1, 1, 1, 1, 1, 1, 1, 1, 1);
    expect(report.mock.calls.map(([, context]) => context.failureCount)).toEqual([2, 3, 4, 5, 9]);
    expect(report).toHaveBeenCalledWith(expect.any(Error), { component: 'push', operation: 'dispatch', failureCount: 2 });
  });

  it('finishes the job when the delivery emails cannot be dispatched, and reports it', async () => {
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const client = fakeClient();
    const failure = new Error('claim function unavailable');
    const emails = { dispatch: vi.fn().mockRejectedValue(failure) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null, undefined, emails as never);
    await expect(service.syncPackage({ id: 'parcel', user_id: 'owner', carrier: 'swiss-post', tracking_number: 'TEST1234' }))
      .resolves.toMatchObject({ checked: 1, updated: 1, errors: 0, emails_sent: 0, email_errors: 1 });
    expect(report).toHaveBeenCalledExactlyOnceWith(failure, { component: 'delivery-email', operation: 'dispatch' });
  });

  it('stops with the job when it is stopped while the delivery emails are sent', async () => {
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const controller = new AbortController();
    const emails = { dispatch: vi.fn().mockImplementation(async () => { controller.abort(); throw new Error('stopped'); }) };
    const service = new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null, undefined, emails as never);
    await expect(service.syncPackage(
      { id: 'parcel', user_id: 'owner', carrier: 'swiss-post', tracking_number: 'TEST1234' },
      { trigger: 'package', signal: controller.signal },
    )).rejects.toThrow();
    expect(report).not.toHaveBeenCalled();
  });

  it('ends a check that shutdown cuts off as interrupted, without waiting for its carrier', async () => {
    const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
    const client = fakeClient();
    const controller = new AbortController();
    // The carrier never answers and does not watch the signal.
    const adapter = { fetch: vi.fn(() => new Promise<never>(() => {})) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    const checking = service.syncPackage(
      { id: 'parcel', carrier: 'ctt', tracking_number: 'TEST1234', current_stage: 'in_transit' },
      { trigger: 'package', signal: controller.signal },
    );
    await vi.waitFor(() => expect(adapter.fetch).toHaveBeenCalledOnce());
    controller.abort(new WorkerShutdown());
    await expect(checking).rejects.toBeInstanceOf(WorkerShutdown);
    expect(client.completeSyncAttempt).toHaveBeenCalledExactlyOnceWith(expect.any(String), expect.objectContaining({
      outcome: 'interrupted', error_type: 'WorkerShutdown',
    }), expect.any(Array), { timeoutMs: 3_000 });
    expect(logged).toHaveBeenCalledWith('tracking_sync_completed', expect.objectContaining({
      carrier: 'ctt', outcome: 'interrupted', error_type: 'WorkerShutdown',
    }));
    expect(client.updatePackage).not.toHaveBeenCalledWith('parcel', expect.objectContaining({ sync_status: 'error' }));
  });

  it('lets a check whose lease is lost run to its end, without calling it interrupted', async () => {
    const client = fakeClient();
    const controller = new AbortController();
    let answer!: (value: CarrierResult) => void;
    const adapter = { fetch: vi.fn(() => new Promise<CarrierResult>(done => { answer = done; })) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    let settled = false;
    const checking = service.syncPackage(
      { id: 'parcel', carrier: 'ctt', tracking_number: 'TEST1234', current_stage: 'in_transit' },
      { trigger: 'package', signal: controller.signal },
    ).finally(() => { settled = true; });
    await vi.waitFor(() => expect(adapter.fetch).toHaveBeenCalledOnce());
    controller.abort(new Error('lease lost'));
    await Promise.resolve();
    expect(settled).toBe(false);
    answer({ status: 'in_transit' });
    await checking.catch(() => undefined);
    expect(client.completeSyncAttempt).not.toHaveBeenCalledWith(expect.anything(),
      expect.objectContaining({ outcome: 'interrupted' }), expect.anything(), expect.anything());
  });

  it('stops a scheduled run between checks at shutdown, letting the check in progress finish', async () => {
    const due = { carrier: 'ctt', current_stage: 'in_transit', tracking_number: 'TEST1234',
      last_synced_at: '2026-09-09T09:00:00Z', sync_status: 'ok', user_id: 'owner' };
    const client = fakeClient([{ ...due, id: 'first' }, { ...due, id: 'second' }]);
    const stopping = new AbortController();
    const adapter = { fetch: vi.fn(async (): Promise<CarrierResult> => {
      stopping.abort(new WorkerShutdown());
      return { status: 'in_transit' };
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-09-09T10:12:00Z'));
    await expect(service.sync({ trigger: 'scheduled', stopping: stopping.signal })).rejects.toBeInstanceOf(WorkerShutdown);
    expect(adapter.fetch).toHaveBeenCalledOnce();
    expect(client.completeSyncAttempt).toHaveBeenCalledOnce();
    expect(client.completeSyncAttempt.mock.calls[0][1]).not.toMatchObject({ outcome: 'interrupted' });
  });

  it('follows one-off parcels opened in the last 24 hours, after the accounts and ten at most', async () => {
    const due = { carrier: 'swiss-post', current_stage: 'in_transit', tracking_number: 'TEST1234',
      last_synced_at: '2026-09-09T09:00:00Z', sync_status: 'ok' };
    const owned = [
      { ...due, id: 'a1', user_id: 'a' },
      { ...due, id: 'b1', user_id: 'b' },
      { ...due, id: 'legacy', user_id: null },
    ];
    // In the order the database returns them: the least recently checked first.
    const oneOffs = [
      ...Array.from({ length: 6 }, (_, index) => ({ ...due, id: `one-off-${index + 1}`, user_id: null, one_off: true })),
      // Checked within the current ten-minute window: not due, and not counted against the ten.
      { ...due, id: 'one-off-fresh', user_id: null, one_off: true, last_synced_at: '2026-09-09T10:10:30Z' },
      ...Array.from({ length: 6 }, (_, index) => ({ ...due, id: `one-off-${index + 7}`, user_id: null, one_off: true })),
    ];
    const client = fakeClient(owned);
    client.listFollowedOneOffPackages.mockResolvedValue(oneOffs);
    const now = new Date('2026-09-09T10:12:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null, () => now);
    await expect(service.sync()).resolves.toMatchObject({ checked: 13 });
    expect(client.listFollowedOneOffPackages).toHaveBeenCalledExactlyOnceWith(new Date('2026-09-08T10:12:00Z'));
    expect(client.startSyncAttempt.mock.calls.map(([, values]) => values.package_id)).toEqual([
      'a1', 'b1', 'legacy', ...Array.from({ length: 10 }, (_, index) => `one-off-${index + 1}`),
    ]);
    // The scheduled reconciliation is the account-wide one; it never names a parcel without an owner.
    expect(client.autoLinkPackages).toHaveBeenCalledExactlyOnceWith(undefined);
  });

  it('asks once for the copies of a number in a run and keeps them on one route', async () => {
    const routing = (preferred: string) => ({ version: 1, configured_carrier: 'unknown', failures: {}, probe_cursor: 0, discovery_cursor: 0,
      preferred_provider: preferred, preferred_number: 'TEST1234', last_success_at: '2026-09-10T11:00:00Z', last_probe_at: '2026-09-10T11:00:00Z' });
    const parcel = (id: string, user: string, label: string, synced: string, preferred: string, extra: JsonObject = {}): JsonObject => ({
      id, user_id: user, label, carrier: 'unknown', tracking_number: 'TEST1234', current_stage: 'in_transit', created_at: '2026-09-10T08:00:00Z',
      last_synced_at: synced, sync_status: 'ok', carrier_data: { routing: routing(preferred) }, ...extra });
    const client = { ...fakeClient([
      parcel('first', 'a', 'Shoes', '2026-09-10T11:00:00Z', '17TRACK'),
      parcel('second', 'b', 'Gift', '2026-09-10T11:05:00Z', 'Ship24'),
      // The same number with a postcode is another lookup.
      parcel('third', 'c', 'Books', '2026-09-10T11:10:00Z', '17TRACK', { dpd_postcode: '0000' }),
    ]),
    acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
    finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit',
      last_update: '2026-09-10T11:30:00Z', events: [{ time: '2026-09-10T11:30:00Z', description: 'In transit', stage: 'in_transit' }] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.sync()).resolves.toMatchObject({ checked: 3, updated: 3 });
    // Both copies follow the route of the one checked last; the copy with a postcode keeps its own.
    expect(adapter.fetchUniversal.mock.calls.map(([source, , , postcode]) => [source, postcode])).toEqual([['Ship24', null], ['17TRACK', '0000']]);
    expect(client.acquireTrackingProvider).toHaveBeenCalledTimes(2);
    const saved = (id: string) => client.updatePackage.mock.calls.filter(([packageId]) => packageId === id).at(-1)?.[1] as JsonObject;
    expect(saved('first')).toMatchObject({ carrier_data: { tracking_provider: 'Ship24', routing: { preferred_provider: 'Ship24' } } });
    expect(saved('second')).toMatchObject({ carrier_data: { tracking_provider: 'Ship24', routing: { preferred_provider: 'Ship24' } } });
    expect(saved('third')).toMatchObject({ carrier_data: { tracking_provider: '17TRACK', routing: { preferred_provider: '17TRACK' } } });
    // Each copy stores the scan for itself.
    expect(client.insertEvents.mock.calls.flatMap(([events]) => events.map((event: JsonObject) => event.package_id)))
      .toEqual(expect.arrayContaining(['first', 'second', 'third']));
    // The copy checked second records the answer it reused.
    const attempt = client.startSyncAttempt.mock.calls.find(([, values]) => values.package_id === 'second')?.[0];
    expect(client.completeSyncAttempt).toHaveBeenCalledWith(attempt, expect.anything(), expect.arrayContaining([
      expect.objectContaining({ step: 'fetch', status: 'succeeded', details: expect.objectContaining({ shared_answers: 1 }) })]));
  });

  it('gives the copies of a number one carrier answer, failures included', async () => {
    const parcel = (id: string, user: string | null, extra: JsonObject = {}): JsonObject => ({ id, user_id: user, carrier: 'swiss-post',
      tracking_number: 'TEST1234', current_stage: 'in_transit', created_at: '2026-09-10T08:00:00Z', last_synced_at: '2026-09-10T11:00:00Z',
      sync_status: 'ok', ...extra });
    const client = { ...fakeClient([parcel('owned', 'a'), parcel('kept', 'b')]),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    client.listFollowedOneOffPackages.mockResolvedValue([parcel('lookup', null, { one_off: true })]);
    const adapter = { fetch: vi.fn().mockRejectedValue(new UpstreamHttpError('Swiss Post', 502)),
      fetchUniversal: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:30:00Z',
        events: [{ time: '2026-09-10T11:30:00Z', description: 'In transit', stage: 'in_transit' }] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.sync()).resolves.toMatchObject({ checked: 3, updated: 3 });
    // One outage seen once: every copy falls back to the same provider, asked once.
    expect(adapter.fetch).toHaveBeenCalledOnce();
    expect(adapter.fetchUniversal).toHaveBeenCalledOnce();
    for (const id of ['owned', 'kept', 'lookup']) {
      expect(client.updatePackage.mock.calls.filter(([packageId]) => packageId === id).at(-1)?.[1], id).toMatchObject({
        carrier_data: { tracking_provider: adapter.fetchUniversal.mock.calls[0][0],
          routing: { failures: { 'swiss-post': { kind: 'transport' } } } } });
    }
  });

  it("asks a number's carrier once per owner's language, and a lookup without an owner in none", async () => {
    const parcel = (id: string, user: string | null, extra: JsonObject = {}): JsonObject => ({ id, user_id: user, carrier: 'swiss-post',
      tracking_number: 'TEST1234', current_stage: 'in_transit', created_at: '2026-09-10T08:00:00Z', last_synced_at: '2026-09-10T11:00:00Z',
      sync_status: 'ok', ...extra });
    const client = fakeClient([parcel('french', 'a', { owner_locale: 'fr' }), parcel('english', 'b', { owner_locale: 'en' }),
      parcel('also-french', 'c', { owner_locale: 'fr' })]);
    client.listFollowedOneOffPackages.mockResolvedValue([parcel('lookup', null, { one_off: true, owner_locale: null })]);
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:30:00Z',
      events: [{ time: '2026-09-10T11:30:00Z', description: 'Sorting', stage: 'in_transit', provider_code: 'SORT' }] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.sync()).resolves.toMatchObject({ checked: 4 });
    // One language per parcel: the two French owners' copies share an answer, no copy takes another language's.
    expect(adapter.fetch.mock.calls.map((call) => call[4]).sort()).toEqual(['en', 'fr', null]);
    expect(adapter.fetch.mock.calls.every(([carrier, number]) => carrier === 'swiss-post' && number === 'TEST1234')).toBe(true);
  });

  it.each([
    { name: 'a browser that timed out', error: new BudgetExceededError('Royal Mail', 30_000), lookups: 1 },
    { name: 'a challenge', error: new ChallengeError('Royal Mail'), lookups: 3 },
  ])('asks a carrier tracked only by its own browser once per run after $name: $lookups lookups', async ({ error, lookups }) => {
    const parcel = (id: string, number: string): JsonObject => ({ id, user_id: 'a', carrier: 'royal-mail', tracking_number: number,
      current_stage: 'in_transit', created_at: '2026-09-10T08:00:00Z', last_synced_at: '2026-09-10T11:00:00Z', sync_status: 'ok' });
    const client = { ...fakeClient([parcel('first', 'TEST1234'), parcel('second', 'TEST5678'), parcel('third', 'TEST9012')]),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    const adapter = { fetch: vi.fn().mockRejectedValue(error), fetchUniversal: vi.fn().mockRejectedValue(new UpstreamHttpError('ParcelsApp', 502)),
      browserOnly: (carrier: string) => carrier === 'royal-mail' };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.sync()).resolves.toMatchObject({ checked: 3 });
    expect(adapter.fetch).toHaveBeenCalledTimes(lookups);
    const attempt = client.startSyncAttempt.mock.calls.find(([, values]) => values.package_id === 'third')?.[0];
    expect(client.completeSyncAttempt).toHaveBeenCalledWith(attempt, expect.anything(), expect.arrayContaining([
      expect.objectContaining({ step: 'fetch', details: lookups === 1 ? expect.objectContaining({ shared_answers: 1 })
        : expect.not.objectContaining({ shared_answers: expect.anything() }) })]));
    // Each parcel still records the carrier's failure and waits for the next run.
    for (const id of ['first', 'second', 'third']) {
      expect(client.updatePackage.mock.calls.filter(([packageId]) => packageId === id).at(-1)?.[1], id).toMatchObject({
        carrier_data: { routing: { failures: { 'royal-mail': { kind: routingFailure(error).kind } } } } });
    }
  });

  it('knows the carriers tracked only by their own browser', () => {
    const adapter = new CarrierTrackingAdapter({ fetch: vi.fn() } as unknown as UniversalTracker);
    expect(adapter.browserOnly('royal-mail')).toBe(true);
    // A browser service that serves many carriers, or a browser behind direct requests, can fail one parcel alone.
    expect(adapter.browserOnly('usps')).toBe(false);
    expect(adapter.browserOnly('dhl-ecommerce')).toBe(false);
    expect(adapter.browserOnly('swiss-post')).toBe(false);
  });

  it('still checks the accounts when the one-off parcels cannot be listed', async () => {
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const client = fakeClient([{ id: 'a1', user_id: 'a', carrier: 'swiss-post', tracking_number: 'TEST1234' }]);
    const failure = new Error('function unavailable');
    client.listFollowedOneOffPackages.mockRejectedValue(failure);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null);
    await expect(service.sync()).resolves.toMatchObject({ checked: 1, updated: 1 });
    expect(report).toHaveBeenCalledExactlyOnceWith(failure, { component: 'tracking', operation: 'list_one_off_packages' });
  });

  it('keeps a new lookup filed under no carrier on the schedule every 6 hours by day once its number was answered for', async () => {
    const routing = { version: 1, configured_carrier: 'unknown', failures: {}, preferred_provider: 'Postal Ninja',
      last_success_at: '2026-09-08T10:00:00Z', last_event_at: '2026-09-08T09:00:00Z' };
    const lookup = (id: string, extra: JsonObject = {}): JsonObject => ({ id, user_id: null, one_off: true, carrier: 'unknown',
      current_stage: 'in_transit', tracking_number: ON_THE_HOUR, created_at: '2026-09-05T10:00:00Z', archived_at: null,
      last_synced_at: '2026-09-09T06:00:15Z', sync_status: 'ok', carrier_data: { routing }, ...extra });
    const check = async (at: string, unfiled: JsonObject[], followed: JsonObject[] = []) => {
      const client = fakeClient();
      client.listFollowedOneOffPackages.mockResolvedValue(followed);
      client.listUnfiledOneOffPackages.mockResolvedValue(unfiled);
      client.listUnwatchedPackageIds.mockResolvedValue([...unfiled, ...followed].map((parcel) => String(parcel.id)));
      const now = new Date(at);
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
        { fetch: vi.fn(), fetchUniversal: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null, () => now);
      await service.sync();
      // Those added in the last 30 days, as recognition only asks for them, filed under no carrier or one only
      // providers track.
      expect(client.listUnfiledOneOffPackages).toHaveBeenCalledExactlyOnceWith(new Date(now.getTime() - 30 * 86_400_000),
        expect.arrayContaining(['unknown', 'intl-post', 'z-leading']));
      expect(client.listUnfiledOneOffPackages.mock.calls[0][1]).not.toContain('dpd-pl');
      return client.startSyncAttempt.mock.calls.map(([, values]) => values.package_id);
    };
    // Six hours after its last check (08:00 in Zurich), not before, and not overnight.
    await expect(check('2026-09-09T11:00:00Z', [lookup('seen')])).resolves.toEqual([]);
    await expect(check('2026-09-09T12:00:00Z', [lookup('seen')])).resolves.toEqual(['seen']);
    await expect(check('2026-09-09T21:00:00Z', [lookup('seen', { last_synced_at: '2026-09-09T12:00:15Z' })])).resolves.toEqual([]);
    // So does one filed under a carrier only providers track: their next answer can name the carrier that delivers it.
    const filed = lookup('filed', { carrier: 'z-leading', carrier_data: { routing: { ...routing, configured_carrier: 'z-leading' } } });
    await expect(check('2026-09-09T11:00:00Z', [filed])).resolves.toEqual([]);
    await expect(check('2026-09-09T12:00:00Z', [filed])).resolves.toEqual(['filed']);
    // A number nobody has seen stays off the schedule: it would ask every provider in turn.
    const unseen = lookup('unseen', { current_stage: 'pending', sync_status: 'waiting',
      carrier_data: { routing: { version: 1, configured_carrier: 'unknown', failures: {} } } });
    await expect(check('2026-09-09T12:00:00Z', [unseen])).resolves.toEqual([]);
    // A followed lookup keeps its own cadence and goes first; the unfiled list does not check it twice.
    const followed = lookup('followed', { last_synced_at: '2026-09-09T09:00:15Z' });
    await expect(check('2026-09-09T12:00:00Z', [lookup('seen'), followed], [followed])).resolves.toEqual(['followed', 'seen']);
  });

  it('checks a lookup filed under no carrier daily by day once its newest scan is over 14 days old', async () => {
    const routing = (lastEvent: string) => ({ version: 1, configured_carrier: 'unknown', failures: {},
      preferred_provider: 'Postal Ninja', last_success_at: '2026-09-08T10:00:00Z', last_event_at: lastEvent });
    const lookup = (id: string, extra: JsonObject = {}): JsonObject => ({ id, user_id: null, one_off: true, carrier: 'unknown',
      current_stage: 'in_transit', tracking_number: ON_THE_HOUR, created_at: '2026-09-05T10:00:00Z', archived_at: null,
      last_synced_at: '2026-09-09T06:00:15Z', sync_status: 'ok', carrier_data: { routing: routing('2026-08-20T09:00:00Z') }, ...extra });
    const check = async (at: string, unfiled: JsonObject[], followed: JsonObject[] = [], watched: string[] = []) => {
      const client = fakeClient();
      client.listFollowedOneOffPackages.mockResolvedValue(followed);
      client.listUnfiledOneOffPackages.mockResolvedValue(unfiled);
      client.listUnwatchedPackageIds.mockResolvedValue([...unfiled, ...followed].map((parcel) => String(parcel.id))
        .filter((id) => !watched.includes(id)));
      const now = new Date(at);
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
        { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }), fetchUniversal: vi.fn().mockResolvedValue({ status: 'in_transit' }) },
        null, () => now);
      await service.sync();
      return client.startSyncAttempt.mock.calls.map(([, values]) => values.package_id);
    };
    // Its newest scan is 20 days old: not six hours after its last check, but a day after it, by day.
    await expect(check('2026-09-09T12:00:00Z', [lookup('quiet')])).resolves.toEqual([]);
    await expect(check('2026-09-09T12:00:00Z', [lookup('quiet', { last_synced_at: '2026-09-08T13:00:15Z' })])).resolves.toEqual([]);
    await expect(check('2026-09-09T12:00:00Z', [lookup('quiet', { last_synced_at: '2026-09-08T12:00:15Z' })])).resolves.toEqual(['quiet']);
    await expect(check('2026-09-09T21:00:00Z', [lookup('quiet', { last_synced_at: '2026-09-08T12:00:15Z' })])).resolves.toEqual([]);
    // A summary time counts as a scan too; one 13 days old keeps the 6-hour cadence.
    await expect(check('2026-09-09T12:00:00Z', [lookup('recent', { carrier_data: { routing: routing('2026-08-20T09:00:00Z'),
      last_update: '2026-08-27T09:00:00Z' } })])).resolves.toEqual(['recent']);
    // Followed, while nobody has opened its link for an hour: daily too, where it was every 3 hours.
    const followed = lookup('followed', { last_synced_at: '2026-09-09T09:00:15Z' });
    await expect(check('2026-09-09T12:00:00Z', [], [followed])).resolves.toEqual([]);
    // A lookup filed under a carrier keeps its cadence, as does one someone is watching.
    const filed = lookup('filed', { carrier: 'swiss-post', last_synced_at: '2026-09-09T09:00:15Z' });
    await expect(check('2026-09-09T12:00:00Z', [], [filed])).resolves.toEqual(['filed']);
    await expect(check('2026-09-09T12:00:00Z', [], [followed], ['followed'])).resolves.toEqual(['followed']);
    // Opening its link still queues a check as before.
    expect(isOpenedParcelSyncDue(lookup('quiet', { last_synced_at: '2026-09-09T10:00:15Z' }), new Date('2026-09-09T12:00:00Z'))).toBe(true);
  });

  it('asks the providers daily by day while a carrier holding the parcel waits for its postcode', async () => {
    const waiting = { carrier: 'dhl', field: 'dpdPostcode' };
    const owned = (id: string, extra: JsonObject = {}, needed: JsonObject | null = waiting): JsonObject => ({ id, user_id: 'user-1',
      one_off: false, carrier: 'unknown', current_stage: 'in_transit', tracking_number: ON_THE_HOUR, created_at: '2026-09-05T10:00:00Z',
      archived_at: null, last_synced_at: '2026-09-09T09:00:15Z', sync_status: 'ok', ...extra,
      carrier_data: { routing: { version: 1, configured_carrier: String(extra.carrier ?? 'unknown'), failures: {},
        preferred_provider: 'ParcelsApp', last_success_at: '2026-09-09T09:00:15Z', last_event_at: '2026-09-09T08:00:00Z',
        ...(needed ? { input_needed: needed } : {}) } } });
    const check = async (at: string, parcels: JsonObject[]) => {
      const client = fakeClient(parcels);
      const now = new Date(at);
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
        { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }), fetchUniversal: vi.fn().mockResolvedValue({ status: 'in_transit' }) },
        null, () => now);
      await service.sync();
      return client.startSyncAttempt.mock.calls.map(([, values]) => values.package_id);
    };
    // Checked three hours ago: a parcel still moving would be due, but this one waits for a day, by day.
    await expect(check('2026-09-09T12:00:00Z', [owned('moving', {}, null)])).resolves.toEqual(['moving']);
    await expect(check('2026-09-09T12:00:00Z', [owned('waiting')])).resolves.toEqual([]);
    await expect(check('2026-09-09T12:00:00Z', [owned('waiting', { last_synced_at: '2026-09-08T12:00:15Z' })])).resolves.toEqual(['waiting']);
    await expect(check('2026-09-09T21:00:00Z', [owned('waiting', { last_synced_at: '2026-09-08T12:00:15Z' })])).resolves.toEqual([]);
    // Filed under that carrier, or asked for a link the parcel does not ask the owner for, it keeps its cadence.
    await expect(check('2026-09-09T12:00:00Z', [owned('filed', { carrier: 'dhl' })])).resolves.toEqual(['filed']);
    await expect(check('2026-09-09T12:00:00Z', [owned('link', {}, { carrier: 'dhl', field: 'trackingUrl' })])).resolves.toEqual(['link']);
    // A lookup without an account waits the same; opening its link checks it at that cadence.
    const lookup = { ...owned('lookup'), user_id: null, one_off: true };
    expect(isOpenedParcelSyncDue(lookup, new Date('2026-09-09T12:00:00Z'))).toBe(false);
    expect(isOpenedParcelSyncDue({ ...lookup, last_synced_at: '2026-09-08T12:00:15Z' }, new Date('2026-09-09T12:00:00Z'))).toBe(true);
  });

  it('still checks the followed lookups when the unfiled ones cannot be listed', async () => {
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const client = fakeClient();
    client.listFollowedOneOffPackages.mockResolvedValue([{ id: 'one-off', user_id: null, one_off: true, carrier: 'swiss-post',
      tracking_number: 'TEST1234', current_stage: 'in_transit' }]);
    const failure = new Error('unavailable');
    client.listUnfiledOneOffPackages.mockRejectedValue(failure);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null);
    await expect(service.sync()).resolves.toMatchObject({ checked: 1 });
    expect(report).toHaveBeenCalledExactlyOnceWith(failure, { component: 'tracking', operation: 'list_unfiled_one_off_packages' });
  });

  it.each([
    { name: 'a watched parcel', unwatched: false, stage: 'in_transit', time: '10:10:00', checked: 2 },
    { name: 'an unwatched parcel', unwatched: true, stage: 'in_transit', time: '10:10:00', checked: 0 },
    { name: 'an unwatched parcel', unwatched: true, stage: 'in_transit', time: '10:59:59', checked: 0 },
    // The account's parcel hourly; the lookup without an account every 3 hours by day.
    { name: 'an unwatched parcel', unwatched: true, stage: 'in_transit', time: '11:00:00', checked: 1 },
    { name: 'an unwatched parcel', unwatched: true, stage: 'in_transit', time: '13:00:00', checked: 2 },
    { name: 'an unwatched parcel out for delivery', unwatched: true, stage: 'out_for_delivery', time: '10:02:00', checked: 0 },
  ])('checks $name at $time: $checked checks', async ({ unwatched, stage, time, checked }) => {
    const parcel = { carrier: 'swiss-post', current_stage: stage, tracking_number: ON_THE_HOUR,
      last_synced_at: '2026-09-09T10:00:15Z', sync_status: 'ok' };
    const client = fakeClient([{ ...parcel, id: 'owned', user_id: 'a' }]);
    client.listFollowedOneOffPackages.mockResolvedValue([{ ...parcel, id: 'one-off', user_id: null, one_off: true }]);
    client.listUnwatchedPackageIds.mockResolvedValue(unwatched ? ['owned', 'one-off'] : ['another']);
    const now = new Date(`2026-09-09T${time}Z`);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null, () => now);
    await expect(service.sync()).resolves.toMatchObject({ checked });
    // Opened within the last hour is watched; a one-off parcel is still followed for 24 hours.
    expect(client.listUnwatchedPackageIds).toHaveBeenCalledExactlyOnceWith(new Date(now.getTime() - 3_600_000));
    expect(client.listFollowedOneOffPackages).toHaveBeenCalledExactlyOnceWith(new Date(now.getTime() - 86_400_000));
    // A manual refresh is never held back by it.
    if (!checked) await expect(service.syncPackage({ ...parcel, id: 'owned', user_id: 'a' })).resolves.toMatchObject({ checked: 1 });
  });

  it.each([
    { time: '10:46:00', checked: [] },
    { time: '10:48:00', checked: ['at :48', 'its copy'] },
    { time: '11:00:00', checked: ['at :48', 'its copy', 'on the hour'] },
  ])('by day, checks the idle parcels whose hour opened by $time: $checked', async ({ time, checked }) => {
    // Parcels checked hourly are spread over the hour by their number, so no run takes them
    // all; copies of one number open together and share their lookups.
    const parcel = (id: string, number: string, last: string) => ({ id, user_id: 'a', carrier: 'swiss-post', current_stage: 'in_transit',
      tracking_number: number, last_synced_at: `2026-09-09T${last}Z`, sync_status: 'ok' });
    const client = fakeClient([parcel('on the hour', ON_THE_HOUR, '10:00:15'),
      parcel('at :48', 'TEST1234', '09:48:15'), parcel('its copy', 'test 1234', '09:48:15')]);
    client.listUnwatchedPackageIds.mockResolvedValue(['on the hour', 'at :48', 'its copy']);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null, () => new Date(`2026-09-09T${time}Z`));
    await service.sync();
    expect(client.startSyncAttempt.mock.calls.map(([, values]) => values.package_id).sort()).toEqual(checked.sort());
  });

  it.each([
    { time: '02:00:00', zurich: '04:00', checked: ['owned', 'opened'] },
    { time: '20:00:00', zurich: '22:00', checked: ['owned', 'opened'] },
    { time: '06:00:00', zurich: '08:00', checked: ['owned', 'opened', 'unopened'] },
  ])('at $zurich in Zurich, checks $checked', async ({ time, checked }) => {
    const parcel = { carrier: 'swiss-post', current_stage: 'in_transit', tracking_number: 'TEST1234',
      last_synced_at: '2026-09-08T23:55:00Z', sync_status: 'ok' };
    const client = fakeClient([{ ...parcel, id: 'owned', user_id: 'a' }]);
    client.listFollowedOneOffPackages.mockResolvedValue(['opened', 'unopened'].map((id) => ({ ...parcel, id, user_id: null, one_off: true })));
    // Overnight, a lookup without an account that nobody opened in the last hour waits for the morning.
    client.listUnwatchedPackageIds.mockResolvedValue(['owned', 'unopened']);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null, () => new Date(`2026-09-09T${time}Z`));
    await expect(service.sync()).resolves.toMatchObject({ checked: checked.length });
    expect(client.startSyncAttempt.mock.calls.map(([, values]) => values.package_id)).toEqual(checked);
  });

  it.each([['package', false], ['added', false], ['refresh', false], ['opened', false], ['changed', false], ['scheduled', true], ['release', true]] as const)('records a %s check, and keeps the scheduled cadence: %s', async (trigger, scheduled) => {
    const route = vi.spyOn(TrackingRouter.prototype, 'fetch').mockRejectedValue(new Error('down'));
    const parcel = { id: 'parcel', carrier: 'dhl', tracking_number: 'TEST1234', current_stage: 'in_transit' };
    const client = { ...fakeClient(), getPackage: vi.fn().mockResolvedValue(parcel) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, { fetch: vi.fn(), fetchUniversal: vi.fn() }, null,
      () => new Date('2026-09-10T12:00:00Z'));
    await service.syncPackage(parcel, { trigger });
    expect(route.mock.calls[0][1]).toBe(scheduled);
    expect(client.startSyncAttempt.mock.calls[0][1]).toMatchObject({ trigger });
  });

  it('checks a parcel whose link was opened only if the scheduled run has not checked it meanwhile', async () => {
    const parcel = { id: 'opened', carrier: 'swiss-post', tracking_number: 'TEST1234', current_stage: 'in_transit',
      last_synced_at: '2026-09-09T09:59:00Z', sync_status: 'ok' };
    const client = { ...fakeClient(), getPackage: vi.fn().mockResolvedValue({ ...parcel, last_synced_at: '2026-09-09T10:00:15Z' }) };
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-09T10:09:30Z'));
    await expect(service.syncPackage(parcel, { trigger: 'opened' })).resolves.toMatchObject({ checked: 0 });
    expect(client.getPackage).toHaveBeenCalledExactlyOnceWith('opened');
    expect(adapter.fetch).not.toHaveBeenCalled();
    client.getPackage.mockResolvedValue(parcel);
    await expect(service.syncPackage(parcel, { trigger: 'opened' })).resolves.toMatchObject({ checked: 1 });
    expect(adapter.fetch).toHaveBeenCalledOnce();
  });

  it('notes in the routing whether the lookups of the last check opened a browser', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    let step = 'trawl';
    const adapter = { fetchUniversal: vi.fn(), fetch: vi.fn(async () => {
      observability.reportTrackingStep(step);
      return { status: 'out_for_delivery', current_stage: 'out_for_delivery', last_update: '2026-09-10T11:00:00Z' } as CarrierResult;
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    const parcel = { id: 'browsed', carrier: 'swiss-post', tracking_number: 'TEST1234', current_stage: 'in_transit' };
    await service.syncPackage(parcel);
    const browsed = client.updatePackage.mock.calls.at(-1)![1];
    expect(browsed.carrier_data.routing.browser_check).toBe(true);
    step = 'direct';
    await service.syncPackage({ ...parcel, ...browsed, last_synced_at: '2026-09-10T11:00:00Z' });
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.routing).not.toHaveProperty('browser_check');
  });

  it('queues a check, once per catalog, for the recent parcels its rules now point to another carrier', async () => {
    const parcel = (id: string, number: string) => ({ id, user_id: id === 'lookup' ? null : 'a', carrier: 'unknown', tracking_number: number,
      current_stage: 'in_transit', carrier_data: { tracking_provider: 'ParcelsApp', routing: { version: 1, configured_carrier: 'unknown',
        failures: {}, probe_cursor: 0, discovery_cursor: 0, rules: 'older' } } });
    const client = { ...fakeClient(),
      listRedetectionCandidates: vi.fn().mockResolvedValue([
        parcel('owned', '1Z999AA10123456784'), parcel('lookup', '1Z999AA10123456784'),
        // A synthetic number no rule claims and no carrier's recognition is asked about.
        parcel('nowhere', 'ABCD0000000001CN')]),
      enqueueSyncJob: vi.fn().mockResolvedValue({ id: 'job' }) };
    const logged = vi.spyOn(observability, 'logOperationalEvent');
    const now = new Date('2026-09-09T10:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, { fetch: vi.fn() }, null, () => now);
    await expect(service.queueRedetections()).resolves.toBe(2);
    expect(client.listRedetectionCandidates).toHaveBeenCalledExactlyOnceWith(new Date('2026-08-10T10:00:00Z'), catalogRules(), undefined);
    expect(client.enqueueSyncJob.mock.calls).toEqual([[{ packageId: 'owned', userId: 'a', background: true }], [{ packageId: 'lookup', userId: null, background: true }]]);
    expect(logged).toHaveBeenCalledWith('tracking_redetections_queued', { queued: 2, waiting: 0 });
    // The same catalog is not looked at twice.
    await expect(service.queueRedetections()).resolves.toBe(0);
    expect(client.listRedetectionCandidates).toHaveBeenCalledOnce();
  });

  it('queues the checks 50 at a time, the most likely to change first, and one in its cooldown once it ends', async () => {
    const parcel = (id: string, routing: JsonObject = {}) => ({ id, user_id: 'a', carrier: 'unknown', tracking_number: '1Z999AA10123456784',
      current_stage: 'in_transit', carrier_data: { tracking_provider: 'ParcelsApp', routing: { version: 1, configured_carrier: 'unknown',
        failures: {}, probe_cursor: 0, discovery_cursor: 0, rules: 'older', ...routing } } });
    const failure = (kind: string, values: JsonObject = {}) => ({ failures: { ups: { kind, count: 1, retry_at: '2026-09-09T09:00:00Z', ...values } } });
    // Listed newest first: UPS asked again after a rate limit, which its schedule does, a parcel in its
    // cooldown, 51 that never asked UPS, and the oldest, whose number UPS refused under an older release.
    const candidates = [parcel('retry', failure('rate_limited')), parcel('deferred', { next_check_at: '2026-09-09T10:30:00Z' }),
      ...Array.from({ length: 51 }, (_, index) => parcel(`due-${index}`)),
      parcel('refused', failure('schema', { user_error: 'carrier:invalid_input' }))];
    const client = { ...fakeClient(), listRedetectionCandidates: vi.fn().mockResolvedValue(candidates),
      enqueueSyncJob: vi.fn().mockResolvedValue({ id: 'job' }), packageSyncJobsPending: vi.fn().mockResolvedValue(true) };
    const logged = vi.spyOn(observability, 'logOperationalEvent');
    let now = new Date('2026-09-09T10:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, { fetch: vi.fn() }, null, () => now);
    const queued = () => client.enqueueSyncJob.mock.calls.map(([job]) => job.packageId);
    await expect(service.queueRedetections()).resolves.toBe(50);
    expect(queued()).toEqual(['refused', ...Array.from({ length: 49 }, (_, index) => `due-${index}`)]);
    expect(logged).toHaveBeenCalledWith('tracking_redetections_queued', { queued: 50, waiting: 3 });
    // The rest wait until that batch's checks are done.
    now = new Date('2026-09-09T10:30:00Z');
    await expect(service.queueRedetections()).resolves.toBe(0);
    expect(client.packageSyncJobsPending).toHaveBeenLastCalledWith(queued());
    client.packageSyncJobsPending.mockResolvedValue(false);
    await expect(service.queueRedetections()).resolves.toBe(3);
    expect(queued().slice(50)).toEqual(['deferred', 'due-49', 'due-50']);
    await expect(service.queueRedetections()).resolves.toBe(0);
    expect(client.listRedetectionCandidates).toHaveBeenCalledOnce();
  });

  it('checks a parcel filed under no carrier after a release only for a carrier it never asked, or one the release taught its number', async () => {
    const parcel = (id: string, number: string, last: string, routing: JsonObject = {}) => ({ id, user_id: 'a', carrier: 'unknown',
      tracking_number: number, current_stage: 'pending', created_at: '2026-09-01T09:00:00Z', last_synced_at: last,
      sync_status: 'waiting', carrier_data: { routing: { version: 1, configured_carrier: 'unknown', failures: {}, probe_cursor: 0,
        discovery_cursor: 0, rules: 'older', ...routing } } });
    const asked = (carrier: string) => ({ candidate_probes: { [carrier]: { count: 6, retry_at: '2026-09-09T09:00:00Z' } } });
    // A ten-digit DHL Express waybill, whose recognition asks DHL Express first, then other carriers.
    const waybill = '1234067892';
    const client = { ...fakeClient(), packageSyncJobsPending: vi.fn().mockResolvedValue(false),
      listRedetectionCandidates: vi.fn().mockResolvedValue([
        // UPS asked already, a day or a week ago: their own cadence asks it again.
        parcel('checked-yesterday', '1Z999AA10123456784', '2026-09-08T12:00:15Z', asked('ups')),
        parcel('checked-last-week', '1Z999AA10123456784', '2026-09-02T09:00:15Z', asked('ups')),
        // UPS never asked.
        parcel('never-asked', '1Z999AA10123456784', '2026-09-09T09:00:15Z'),
        // A carrier whose recognition the release brought to the number, and the same carriers as before.
        parcel('taught', waybill, '2026-09-08T12:00:15Z', { ...asked('dhl-express'), recognizers: ['dhl-express'] }),
        parcel('same', waybill, '2026-09-08T12:00:15Z', { ...asked('dhl-express'), recognizers: recognizers(waybill) }),
        // Delivered, with UPS named by its provider and never asked, then asked.
        ...[{}, asked('ups')].map((routing, index) => {
          const finished = parcel(index ? 'delivered-asked' : 'delivered', 'ABCD0000000001CN', '2026-09-02T09:00:15Z', routing);
          return { ...finished, current_stage: 'delivered', carrier_data: { ...finished.carrier_data, tracking_provider: 'ParcelsApp', reported_carriers: ['UPS'] } };
        })]),
      enqueueSyncJob: vi.fn().mockResolvedValue({ id: 'job' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, { fetch: vi.fn() }, null,
      () => new Date('2026-09-09T10:00:00Z'));
    await expect(service.queueRedetections()).resolves.toBe(3);
    expect(client.enqueueSyncJob.mock.calls.map(([job]) => job.packageId)).toEqual(['never-asked', 'taught', 'delivered']);
  });

  it('lists every page of the parcels the catalog may point elsewhere', async () => {
    const parcel = (id: string, created_at: string, stage = 'in_transit') => ({ id, user_id: 'a', carrier: 'unknown',
      tracking_number: '1Z999AA10123456784', current_stage: stage, created_at, carrier_data: { tracking_provider: 'ParcelsApp',
        routing: { version: 1, configured_carrier: 'unknown', failures: {}, probe_cursor: 0, discovery_cursor: 0, rules: 'older' } } });
    // A full page of parcels it leaves alone, then the last page.
    const full = Array.from({ length: 500 }, (_, index) => parcel(`left-${index}`, `2026-09-09T09:${String(index % 60).padStart(2, '0')}:00Z`, 'delivered'));
    const client = { ...fakeClient(), packageSyncJobsPending: vi.fn().mockResolvedValue(false),
      listRedetectionCandidates: vi.fn().mockResolvedValueOnce(full).mockResolvedValueOnce([parcel('older', '2026-09-01T08:00:00Z')]),
      enqueueSyncJob: vi.fn().mockResolvedValue({ id: 'job' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, { fetch: vi.fn() }, null,
      () => new Date('2026-09-09T10:00:00Z'));
    await expect(service.queueRedetections()).resolves.toBe(1);
    expect(client.listRedetectionCandidates.mock.calls.map(([, , before]) => before)).toEqual([undefined, full.at(-1)!.created_at]);
    expect(client.enqueueSyncJob).toHaveBeenCalledExactlyOnceWith({ packageId: 'older', userId: 'a', background: true });
  });

  it('looks again after the next run when the recent parcels cannot be listed', async () => {
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const failure = new Error('database down');
    const client = { ...fakeClient(), listRedetectionCandidates: vi.fn().mockRejectedValueOnce(failure).mockResolvedValue([]),
      enqueueSyncJob: vi.fn() };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, { fetch: vi.fn() }, null);
    await expect(service.queueRedetections()).resolves.toBe(0);
    expect(report).toHaveBeenCalledExactlyOnceWith(failure, { component: 'tracking', operation: 'queue_redetections' });
    await expect(service.queueRedetections()).resolves.toBe(0);
    expect(client.listRedetectionCandidates).toHaveBeenCalledTimes(2);
    expect(client.enqueueSyncJob).not.toHaveBeenCalled();
  });

  it('keeps every parcel on the full cadence when the unwatched ones cannot be listed', async () => {
    const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const client = fakeClient([{ id: 'a1', user_id: 'a', carrier: 'swiss-post', current_stage: 'in_transit',
      tracking_number: 'TEST1234', last_synced_at: '2026-09-09T10:00:15Z', sync_status: 'ok' }]);
    const failure = new Error('function unavailable');
    client.listUnwatchedPackageIds.mockRejectedValue(failure);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null, () => new Date('2026-09-09T10:10:00Z'));
    await expect(service.sync()).resolves.toMatchObject({ checked: 1, unchanged: 1 });
    expect(report).toHaveBeenCalledExactlyOnceWith(failure, { component: 'tracking', operation: 'list_unwatched_packages' });
  });

  it('queues a check when a link is opened only if the schedule would check the parcel now', () => {
    const parcel = { carrier: 'swiss-post', current_stage: 'in_transit', tracking_number: 'TEST1234',
      last_synced_at: '2026-09-09T10:00:15Z', sync_status: 'ok', archived_at: null };
    const at = (time: string) => new Date(`2026-09-09T${time}Z`);
    expect(isOpenedParcelSyncDue(parcel, at('10:09:59'))).toBe(false);
    expect(isOpenedParcelSyncDue(parcel, at('10:10:00'))).toBe(true);
    expect(isOpenedParcelSyncDue({ ...parcel, last_synced_at: null }, at('10:00:20'))).toBe(true);
    // A manual refresh would be allowed here; polling a link is not one.
    expect(isTrackingSyncDue(parcel, at('10:09:59'))).toBe(true);
    // A delivered, returned or archived parcel is not followed any more.
    expect(isOpenedParcelSyncDue({ ...parcel, current_stage: 'delivered' }, at('12:00:00'))).toBe(false);
    expect(isOpenedParcelSyncDue({ ...parcel, current_stage: 'returned' }, at('12:00:00'))).toBe(false);
    expect(isOpenedParcelSyncDue({ ...parcel, archived_at: '2026-09-09T09:00:00Z' }, at('12:00:00'))).toBe(false);
    expect(isOpenedParcelSyncDue({ ...parcel, current_stage: 'delivered', last_status_text: 'TO_BE_DELIVERED' }, at('12:00:00'))).toBe(true);
    // A delivery only a provider reported, which the carrier may yet dispute: daily, until the follow-up ends.
    const unconfirmed = { ...parcel, current_stage: 'delivered', carrier_data: { unconfirmed_delivery_until: '2026-09-11T10:00:00Z' } };
    expect(isOpenedParcelSyncDue(unconfirmed, at('12:00:00'))).toBe(false);
    expect(isOpenedParcelSyncDue(unconfirmed, new Date('2026-09-10T11:00:00Z'))).toBe(true);
    expect(isOpenedParcelSyncDue(unconfirmed, new Date('2026-09-11T11:00:00Z'))).toBe(false);
    // Out for delivery, the carrier's own adapter answering: every 2 minutes.
    const delivering = { ...parcel, current_stage: 'out_for_delivery' };
    expect(isOpenedParcelSyncDue(delivering, at('10:01:59'))).toBe(false);
    expect(isOpenedParcelSyncDue(delivering, at('10:02:00'))).toBe(true);
    // A universal provider answering: reading a link does not make it more often.
    const relayed = { ...delivering, carrier_data: { tracking_provider: 'ParcelsApp' } };
    expect(isOpenedParcelSyncDue(relayed, at('10:02:00'))).toBe(false);
    expect(isOpenedParcelSyncDue(relayed, at('10:10:00'))).toBe(true);
    // Every 5 minutes while its lookups open a browser.
    const browsing = { ...delivering, carrier_data: { routing: { version: 1, configured_carrier: 'swiss-post', browser_check: true } } };
    expect(isOpenedParcelSyncDue(browsing, at('10:04:59'))).toBe(false);
    expect(isOpenedParcelSyncDue(browsing, at('10:05:00'))).toBe(true);
    // Overnight it stays hourly.
    expect(isOpenedParcelSyncDue({ ...delivering, last_synced_at: '2026-09-09T20:00:15Z' }, at('20:02:00'))).toBe(false);
    // A provider's cooldown holds a link's reader back as it holds the schedule.
    expect(isOpenedParcelSyncDue({ ...parcel, carrier_data: { routing: { version: 1, configured_carrier: 'swiss-post', next_check_at: '2026-09-09T13:00:00Z' } } }, at('12:00:00'))).toBe(false);
  });

  it.each([
    { scan: 'from today', lastEvent: '2026-09-09T06:00:00Z', every2Minutes: true },
    // A round from an earlier day is over: the regular cadence catches the next one, even for a parcel added today.
    { scan: 'from an earlier day', lastEvent: '2026-09-07T17:00:00Z', every2Minutes: false },
    { scan: 'from an earlier day, without its time', lastEvent: '2026-09-07T17:00:00Z', localDay: '2026-09-08', every2Minutes: false },
    { scan: 'from today, without its time', lastEvent: '2026-09-07T17:00:00Z', localDay: '2026-09-09', every2Minutes: true },
    // The day is the carrier's: 07:00 in Sydney is 21:00 UTC the day before.
    { scan: 'from this morning in Sydney', carrier: 'j-and-t-au', lastEvent: '2026-09-08T21:00:00Z', every2Minutes: true },
    { scan: 'from the morning before in Sydney', carrier: 'j-and-t-au', lastEvent: '2026-09-07T21:00:00Z', every2Minutes: false },
    // A scan that states its offset is read on it, whatever zone the catalog keeps for its carrier.
    { scan: 'from this morning at +10:00', carrier: 'australia-post', lastEvent: '2026-09-08T21:00:00Z', lastUpdate: '2026-09-09T07:00:00+10:00', every2Minutes: true },
    { scan: 'from the morning before at +10:00', carrier: 'australia-post', lastEvent: '2026-09-07T21:00:00Z', lastUpdate: '2026-09-08T07:00:00+10:00', every2Minutes: false },
    { scan: 'from the evening before at -05:00', carrier: 'ups', lastEvent: '2026-09-09T01:00:00Z', lastUpdate: '2026-09-08T20:00:00-05:00', every2Minutes: false },
    // A zero offset is a UTC instant, not the scan's own clock.
    { scan: 'from 01:00 at +00:00', carrier: 'ups', lastEvent: '2026-09-09T01:00:00Z', lastUpdate: '2026-09-09T01:00:00+00:00', every2Minutes: true },
  ])('checks a parcel out for delivery with its scan $scan every 2 minutes: $every2Minutes', ({ carrier = 'swiss-post', lastEvent, lastUpdate, localDay, every2Minutes }) => {
    const parcel = { carrier, current_stage: 'out_for_delivery', tracking_number: 'TEST1234', created_at: '2026-09-09T07:00:00Z',
      last_synced_at: '2026-09-09T10:00:15Z', sync_status: 'ok', archived_at: null,
      carrier_data: { ...(lastUpdate ? { last_update: lastUpdate } : {}), ...(localDay ? { last_update_local: localDay } : {}),
        routing: { version: 1, configured_carrier: carrier, last_event_at: lastEvent } } };
    const at = (time: string) => new Date(`2026-09-09T${time}Z`);
    expect(isOpenedParcelSyncDue(parcel, at('10:02:00'))).toBe(every2Minutes);
    expect(isOpenedParcelSyncDue(parcel, at('10:10:00'))).toBe(true);
  });

  it.each([
    // Out for delivery, the carrier's own adapter answering: every 2 minutes.
    { carrier: 'swiss-post', stage: 'out_for_delivery', time: '10:02:00', checked: 1 },
    { carrier: 'swiss-post', stage: 'out_for_delivery', time: '10:01:59', checked: 0 },
    // Every 5 minutes while its lookups open a browser.
    { carrier: 'swiss-post', browser: true, stage: 'out_for_delivery', time: '10:02:00', checked: 0 },
    { carrier: 'swiss-post', browser: true, stage: 'out_for_delivery', time: '10:04:59', checked: 0 },
    { carrier: 'swiss-post', browser: true, stage: 'out_for_delivery', time: '10:05:00', checked: 1 },
    { carrier: 'swiss-post', browser: true, stage: 'in_transit', time: '10:05:00', checked: 0 },
    // After a failed check, 15 minutes, browser or not.
    { carrier: 'swiss-post', failed: true, stage: 'out_for_delivery', time: '10:02:00', checked: 0 },
    { carrier: 'swiss-post', failed: true, stage: 'out_for_delivery', time: '10:15:14', checked: 0 },
    { carrier: 'swiss-post', failed: true, stage: 'out_for_delivery', time: '10:15:15', checked: 1 },
    { carrier: 'swiss-post', failed: true, browser: true, stage: 'out_for_delivery', time: '10:05:00', checked: 0 },
    { carrier: 'swiss-post', failed: true, browser: true, stage: 'out_for_delivery', time: '10:15:15', checked: 1 },
    // Other stages keep their cadence.
    { carrier: 'swiss-post', failed: true, stage: 'in_transit', time: '10:10:00', checked: 1 },
    // A universal provider answering: like any other stage.
    { carrier: 'swiss-post', provider: 'ParcelsApp', stage: 'out_for_delivery', time: '10:02:00', checked: 0 },
    { carrier: 'swiss-post', provider: 'ParcelsApp', stage: 'out_for_delivery', time: '10:09:59', checked: 0 },
    { carrier: 'swiss-post', provider: 'ParcelsApp', stage: 'out_for_delivery', time: '10:10:00', checked: 1 },
    // A carrier with a longer interval of its own keeps it.
    { carrier: 'spring-gds', stage: 'out_for_delivery', time: '10:02:00', checked: 0 },
    { carrier: 'spring-gds', stage: 'out_for_delivery', time: '10:10:00', checked: 0 },
    { carrier: 'spring-gds', stage: 'out_for_delivery', time: '10:30:00', checked: 1 },
    { carrier: 'swiss-post', stage: 'in_transit', time: '10:02:00', checked: 0 },
    { carrier: 'swiss-post', stage: 'in_transit', time: '10:10:00', checked: 1 },
    { carrier: 'spring-gds', stage: 'in_transit', time: '10:02:00', checked: 0 },
    { carrier: 'spring-gds', stage: 'in_transit', time: '10:10:00', checked: 0 },
    { carrier: 'spring-gds', stage: 'in_transit', time: '10:29:59', checked: 0 },
    { carrier: 'spring-gds', stage: 'in_transit', time: '10:30:00', checked: 1 },
    { carrier: 'spring-gds', stage: 'registered', time: '10:10:00', checked: 0 },
    { carrier: 'spring-gds', stage: 'registered', time: '10:30:00', checked: 1 },
    { carrier: 'swiss-post', stage: 'registered', time: '10:02:00', checked: 0 },
    { carrier: 'swiss-post', stage: 'accepted', time: '10:02:00', checked: 0 },
    { carrier: 'swiss-post', stage: 'customs', time: '10:02:00', checked: 0 },
    { carrier: 'swiss-post', stage: 'registered', time: '10:10:00', checked: 1 },
    { carrier: 'gls-de', stage: 'in_transit', time: '10:02:00', checked: 0 },
  ])('schedules $carrier at $stage at $time (provider: $provider, browser: $browser, failed: $failed): $checked checks', async ({ carrier, provider, browser, failed, stage, time, checked }) => {
    const parcel = {
      id: 'scheduled', carrier, current_stage: stage, tracking_number: 'TEST1234',
      last_synced_at: '2026-09-09T10:00:15Z', sync_status: failed ? 'error' : 'ok',
      ...(provider ? { carrier_data: { tracking_provider: provider } } : {}),
      ...(browser ? { carrier_data: { routing: { version: 1, configured_carrier: carrier, browser_check: true } } } : {}),
    };
    const client = fakeClient([parcel]);
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      adapter, null, () => new Date(`2026-09-09T${time}Z`));
    await expect(service.sync()).resolves.toMatchObject({ checked });
    expect(adapter.fetch).toHaveBeenCalledTimes(checked);
  });

  const challenged = () => Promise.reject(new ChallengeError('Carrier'));
  const lastMileScan = (time: string): CarrierResult => ({ status: 'out_for_delivery', current_stage: 'out_for_delivery',
    last_status_text: 'Out for delivery', last_update: `2026-09-10T${time}Z`,
    events: [{ time: `2026-09-10T${time}Z`, description: 'Out for delivery', stage: 'out_for_delivery' }] });
  it.each([
    // Routing waits 15 minutes after the carrier's lookup fails, whoever answers instead.
    { outcome: 'a provider shows a newer scan', direct: challenged, universal: async () => lastMileScan('09:50:00'), refresh: false },
    { outcome: 'a provider copies the carrier\'s scans', direct: challenged, universal: async () => lastMileScan('09:30:00'), refresh: false },
    { outcome: 'no provider answers', direct: challenged, universal: () => Promise.reject(new Error('Provider unavailable')), refresh: false },
    // The error the check saved holds the others back as long, but not a refresh by hand.
    { outcome: 'the lookup runs outside routing', carrier: 'amazon-shipping', number: 'UK0000000001', direct: challenged, refresh: true },
    { outcome: 'the carrier answers without the parcel\'s progress', direct: async (): Promise<CarrierResult> => ({ status: 'pending', current_stage: 'registered' }), refresh: true },
  ])('holds the last mile back for 15 minutes after a failed carrier lookup when $outcome', async ({ carrier = 'swiss-post', number = 'TEST1234', direct, universal, refresh }) => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T10:02:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn(direct), fetchUniversal: vi.fn(universal ?? (async () => lastMileScan('09:30:00'))) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T10:02:00Z'));
    const parcel = { id: 'delivering', carrier, tracking_number: number, current_stage: 'out_for_delivery',
      last_synced_at: '2026-09-10T10:00:15Z', sync_status: 'ok', carrier_data: { last_status_text: 'Out for delivery',
        routing: { version: 1, configured_carrier: carrier, last_event_at: '2026-09-10T09:30:00Z' } } };
    await service.syncPackage(parcel);
    const checked = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
    const at = (time: string) => new Date(`2026-09-10T${time}Z`);
    expect(isOpenedParcelSyncDue(checked, at('10:04:00'))).toBe(false);
    expect(isOpenedParcelSyncDue(checked, at('10:16:59'))).toBe(false);
    expect(isOpenedParcelSyncDue(checked, at('10:17:00'))).toBe(true);
    expect(isTrackingSyncDue(checked, at('10:04:00'))).toBe(refresh);
  });

  it('checks the last mile every 2 minutes again once the carrier answers after a failed check', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockRejectedValueOnce(new ChallengeError('Amazon Shipping')).mockResolvedValue(lastMileScan('10:10:00')) };
    let now = new Date('2026-09-10T10:02:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    const parcel = { id: 'delivering', carrier: 'amazon-shipping', tracking_number: 'UK0000000001', current_stage: 'out_for_delivery',
      last_synced_at: '2026-09-10T10:00:15Z', sync_status: 'ok', carrier_data: { last_status_text: 'Out for delivery' } };
    await service.syncPackage(parcel);
    const failed = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
    expect(failed.sync_status).toBe('error');
    now = new Date('2026-09-10T10:17:00Z');
    await service.syncPackage(failed);
    const answered = { ...failed, ...client.updatePackage.mock.calls.at(-1)![1] };
    expect(answered.sync_status).toBe('ok');
    expect(isOpenedParcelSyncDue(answered, new Date('2026-09-10T10:17:59Z'))).toBe(false);
    expect(isOpenedParcelSyncDue(answered, new Date('2026-09-10T10:18:00Z'))).toBe(true);
  });

  it.each([
    // The check that filed it at 10:00 under a carrier a provider named did not ask that carrier: the next run does.
    { checkedAt: '10:00:15', time: '10:02:00', checked: 1 },
    { checkedAt: '10:00:15', time: '10:02:00', overnight: true, checked: 1 },
    // A check that left the mark in place keeps the parcel due for 15 minutes at most.
    { checkedAt: '10:12:00', time: '10:14:00', checked: 1 },
    { checkedAt: '10:12:00', time: '10:16:00', checked: 0 },
    // Delivered on the provider's word, it need not wait for the follow-up's next daily check.
    { checkedAt: '10:00:15', time: '10:02:00', delivered: true, checked: 1 },
    { checkedAt: '10:12:00', time: '10:16:00', delivered: true, checked: 0 },
  ])('asks at the next run the carrier a provider named, last checked at $checkedAt, at $time (overnight: $overnight, delivered: $delivered): $checked checks', async ({ checkedAt, time, overnight, delivered, checked }) => {
    const day = overnight ? '2026-09-09T22' : '2026-09-09T10';
    const at = (clock: string) => `${day}${clock.slice(2)}Z`;
    const parcel = {
      id: 'named', carrier: 'dhl-express', current_stage: delivered ? 'delivered' : 'in_transit', tracking_number: 'TEST1234',
      last_synced_at: at(checkedAt), sync_status: 'ok', carrier_data: { tracking_provider: 'Ship24',
        ...(delivered ? { unconfirmed_delivery_until: '2026-09-12T10:00:00Z' } : {}),
        routing: { version: 1, configured_carrier: 'dhl-express', carrier_named_at: at('10:00:10') } },
    };
    const client = fakeClient([parcel]);
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      adapter, null, () => new Date(at(time)));
    await expect(service.sync()).resolves.toMatchObject({ checked });
  });

  it.each([
    { name: 'idle for three days', created: '2026-08-30T09:00:00Z', lastEvent: '2026-09-06T09:00:00Z', stage: 'in_transit', time: '10:10:00', checked: 0 },
    { name: 'idle for three days', created: '2026-08-30T09:00:00Z', lastEvent: '2026-09-06T09:00:00Z', stage: 'in_transit', time: '11:00:00', checked: 1 },
    { name: 'stuck out for delivery', created: '2026-08-30T09:00:00Z', lastEvent: '2026-09-06T09:00:00Z', stage: 'out_for_delivery', time: '10:02:00', checked: 0 },
    { name: 'never tracked since added', created: '2026-09-07T09:00:00Z', lastEvent: null, stage: 'pending', time: '10:10:00', checked: 0 },
    { name: 'added yesterday with old history', created: '2026-09-08T09:00:00Z', lastEvent: '2026-09-01T09:00:00Z', stage: 'in_transit', time: '10:10:00', checked: 1 },
    { name: 'moved within 48 hours', created: '2026-08-30T09:00:00Z', lastEvent: '2026-09-07T10:30:00Z', stage: 'in_transit', time: '10:10:00', checked: 1 },
    { name: 'outside routing with a recent summary', created: '2026-08-30T09:00:00Z', lastEvent: null, lastUpdate: '2026-09-08T18:00:00Z', stage: 'in_transit', time: '10:10:00', checked: 1 },
    // Registered or accepted, a parcel is checked hourly 12 hours after its newest event or after it was added.
    { name: 'registered without news for 14 hours', created: '2026-09-01T09:00:00Z', lastEvent: '2026-09-08T20:00:00Z', stage: 'registered', time: '10:10:00', checked: 0 },
    { name: 'registered without news for 14 hours', created: '2026-09-01T09:00:00Z', lastEvent: '2026-09-08T20:00:00Z', stage: 'registered', time: '11:00:00', checked: 1 },
    { name: 'accepted without news for 14 hours', created: '2026-09-01T09:00:00Z', lastEvent: '2026-09-08T20:00:00Z', stage: 'accepted', time: '10:10:00', checked: 0 },
    { name: 'accepted with news 11 hours ago', created: '2026-09-01T09:00:00Z', lastEvent: '2026-09-08T23:10:00Z', stage: 'accepted', time: '10:10:00', checked: 1 },
    { name: 'registered, added an hour ago with an older label', created: '2026-09-09T09:00:00Z', lastEvent: '2026-09-07T09:00:00Z', stage: 'registered', time: '10:10:00', checked: 1 },
    { name: 'in transit without news for 14 hours', created: '2026-09-01T09:00:00Z', lastEvent: '2026-09-08T20:00:00Z', stage: 'in_transit', time: '10:10:00', checked: 1 },
  ])('checks a parcel $name at $time: $checked checks', async ({ created, lastEvent, lastUpdate, stage, time, checked }) => {
    const parcel = {
      id: 'idle', carrier: 'swiss-post', current_stage: stage, tracking_number: 'TEST1234',
      created_at: created, last_synced_at: '2026-09-09T10:00:15Z', sync_status: 'ok',
      carrier_data: lastUpdate ? { last_update: lastUpdate }
        : { routing: { version: 1, configured_carrier: 'swiss-post', ...(lastEvent ? { last_event_at: lastEvent } : {}) } },
    };
    const client = fakeClient([parcel]);
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      adapter, null, () => new Date(`2026-09-09T${time}Z`));
    await expect(service.sync()).resolves.toMatchObject({ checked });
    // A manual refresh is never held back by the idle schedule.
    if (!checked) await expect(service.syncPackage(parcel)).resolves.toMatchObject({ checked: 1 });
  });

  it.each([
    { name: 'checked less than a day ago', at: '2026-09-09T09:59:59Z', checked: 0 },
    { name: 'checked a day ago', at: '2026-09-09T10:00:00Z', checked: 1 },
    { name: 'checked a day ago, overnight', last: '2026-09-08T22:00:15Z', at: '2026-09-09T22:00:00Z', checked: 1 },
    { name: 'never checked', last: null, at: '2026-09-09T10:00:00Z', checked: 1 },
    { name: 'without news for just under 30 days', lastEvent: '2026-08-10T10:00:01Z', at: '2026-09-09T10:00:00Z', checked: 1 },
    { name: 'without news for 30 days', lastEvent: '2026-08-10T10:00:00Z', at: '2026-09-09T10:00:00Z', checked: 0 },
    { name: 'without news for 30 days, never checked', lastEvent: '2026-08-01T08:00:00Z', last: null, at: '2026-09-09T10:00:00Z', checked: 0 },
    { name: 'added 29 days ago with older scans', created: '2026-08-11T08:00:00Z', lastEvent: '2026-07-01T08:00:00Z', at: '2026-09-09T10:00:00Z', checked: 1 },
    { name: 'in a provider\'s cooldown', nextCheck: '2026-09-09T12:00:00Z', at: '2026-09-09T10:00:00Z', checked: 0 },
  ])('checks an archived parcel $name at $at: $checked checks', async ({
    created = '2026-08-01T08:00:00Z', lastEvent = '2026-09-06T09:00:00Z', last = '2026-09-08T10:00:15Z', nextCheck, at, checked,
  }) => {
    // Put away while it waits at a pickup point, the parcel is checked daily for its collection.
    const parcel = {
      id: 'archived', user_id: 'a', carrier: 'swiss-post', current_stage: 'ready_for_pickup', tracking_number: ON_THE_HOUR,
      created_at: created, archived_at: '2026-09-07T08:00:00Z', last_synced_at: last, sync_status: 'ok',
      carrier_data: { routing: { version: 1, configured_carrier: 'swiss-post', last_event_at: lastEvent,
        ...(nextCheck ? { next_check_at: nextCheck } : {}) } },
    };
    const client = fakeClient([parcel]);
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'ready_for_pickup' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date(at));
    await expect(service.sync()).resolves.toMatchObject({ checked });
    expect(adapter.fetch).toHaveBeenCalledTimes(checked);
    // A manual refresh is never held back by it.
    if (!checked && !nextCheck) await expect(service.syncPackage(parcel)).resolves.toMatchObject({ checked: 1 });
  });

  it('checks a parcel brought back from the archive at the regular cadence again', async () => {
    const parcel = { id: 'restored', user_id: 'a', carrier: 'swiss-post', current_stage: 'ready_for_pickup', tracking_number: 'TEST1234',
      created_at: '2026-09-01T08:00:00Z', last_synced_at: '2026-09-09T10:00:15Z', sync_status: 'ok',
      carrier_data: { routing: { version: 1, configured_carrier: 'swiss-post', last_event_at: '2026-09-08T09:00:00Z' } } };
    const checked = async (archived_at: string | null) => {
      const client = fakeClient([{ ...parcel, archived_at }]);
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
        { fetch: vi.fn().mockResolvedValue({ status: 'ready_for_pickup' }) }, null, () => new Date('2026-09-09T10:10:00Z'));
      return (await service.sync()).checked;
    };
    // Archived, it waits for tomorrow's check; on the list again, for the next ten minutes.
    await expect(checked('2026-09-09T09:00:00Z')).resolves.toBe(0);
    await expect(checked(null)).resolves.toBe(1);
  });

  it('checks an archived parcel at the regular cadence while someone it was shared with gets its alerts', async () => {
    const parcel = { id: 'shared', user_id: 'a', carrier: 'swiss-post', current_stage: 'ready_for_pickup', tracking_number: 'TEST1234',
      created_at: '2026-07-01T08:00:00Z', archived_at: '2026-09-09T09:00:00Z', last_synced_at: '2026-09-09T10:00:15Z', sync_status: 'ok',
      carrier_data: { routing: { version: 1, configured_carrier: 'swiss-post', last_event_at: '2026-07-02T09:00:00Z' } } };
    const checked = async (shared: () => Promise<Set<string>>) => {
      const client = fakeClient([parcel, { ...parcel, id: 'alone' }]);
      client.sharedAlertPackageIds.mockImplementation(shared);
      const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'ready_for_pickup' }) };
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-09T11:00:00Z'));
      const summary = await service.sync();
      expect(client.sharedAlertPackageIds).toHaveBeenCalledExactlyOnceWith(['shared', 'alone']);
      return summary.checked;
    };
    // Without news for two months, the archived parcel alone has left the schedule; the shared one has not.
    await expect(checked(async () => new Set(['shared']))).resolves.toBe(1);
    // When the alerts cannot be read, archived parcels keep their daily check.
    const capture = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    await expect(checked(async () => { throw new Error('down'); })).resolves.toBe(0);
    expect(capture).toHaveBeenCalledWith(expect.any(Error), { component: 'tracking', operation: 'list_shared_alert_packages' });
  });

  it('asks for shared alerts only when an archived parcel is among the candidates', async () => {
    const client = fakeClient([{ id: 'listed', user_id: 'a', carrier: 'swiss-post', current_stage: 'in_transit', tracking_number: 'TEST1234',
      created_at: '2026-09-09T08:00:00Z', archived_at: null, last_synced_at: null, sync_status: 'ok' }]);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null, () => new Date('2026-09-09T10:10:00Z'));
    await service.sync();
    expect(client.sharedAlertPackageIds).not.toHaveBeenCalled();
  });

  it.each([
    { name: 'unseen, added two hours ago', created: '2026-09-09T08:30:00Z', last: '2026-09-09T10:00:15Z', at: '2026-09-09T10:10:00Z', checked: 0 },
    { name: 'unseen, added two hours ago', created: '2026-09-09T08:30:00Z', last: '2026-09-09T10:00:15Z', at: '2026-09-09T11:00:00Z', checked: 1 },
    { name: 'unseen, added a day ago', created: '2026-09-08T10:00:00Z', last: '2026-09-09T06:00:15Z', at: '2026-09-09T11:00:00Z', checked: 0 },
    { name: 'unseen, added a day ago', created: '2026-09-08T10:00:00Z', last: '2026-09-09T06:00:15Z', at: '2026-09-09T12:00:00Z', checked: 1 },
    { name: 'unseen, added three days ago', created: '2026-09-06T09:00:00Z', last: '2026-09-08T12:00:15Z', at: '2026-09-09T11:00:00Z', checked: 0 },
    { name: 'unseen, added three days ago', created: '2026-09-06T09:00:00Z', last: '2026-09-08T12:00:15Z', at: '2026-09-09T12:00:00Z', checked: 1 },
    { name: 'unseen, added eight days ago', created: '2026-09-01T09:00:00Z', last: '2026-09-08T12:00:15Z', at: '2026-09-09T12:00:00Z', checked: 0 },
    { name: 'unseen, added eight days ago', created: '2026-09-01T09:00:00Z', last: '2026-09-02T12:00:15Z', at: '2026-09-09T11:00:00Z', checked: 0 },
    { name: 'unseen, added eight days ago', created: '2026-09-01T09:00:00Z', last: '2026-09-02T12:00:15Z', at: '2026-09-09T12:00:00Z', checked: 1 },
    { name: 'a provider answered, eight days ago', created: '2026-09-01T09:00:00Z', last: '2026-09-09T10:00:15Z', at: '2026-09-09T11:00:00Z', checked: 1,
      routing: { last_success_at: '2026-09-01T10:00:00Z', last_event_at: '2026-09-01T10:00:00Z' } },
    { name: 'a provider answered', created: '2026-09-06T09:00:00Z', last: '2026-09-09T10:00:15Z', at: '2026-09-09T10:10:00Z', checked: 1,
      routing: { last_success_at: '2026-09-09T09:00:00Z', last_event_at: '2026-09-09T09:00:00Z' } },
    { name: 'a carrier needs input', created: '2026-09-06T09:00:00Z', last: '2026-09-09T10:00:15Z', at: '2026-09-09T11:00:00Z', checked: 1,
      routing: { input_needed: { carrier: 'gls-de', field: 'postcode' } } },
  ])('schedules an unknown number ($name) at $at: $checked checks', async ({ created, last, at, checked, routing }) => {
    const parcel = { id: 'unseen', user_id: 'a', carrier: 'unknown', current_stage: 'pending', tracking_number: 'TEST1234',
      created_at: created, last_synced_at: last, sync_status: 'waiting',
      carrier_data: { routing: { version: 1, configured_carrier: 'unknown', failures: {}, ...routing } } };
    const client = fakeClient([parcel]);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null, () => new Date(at));
    await expect(service.sync()).resolves.toMatchObject({ checked });
    // A manual refresh is never held back by it.
    if (!checked) await expect(service.syncPackage(parcel)).resolves.toMatchObject({ checked: 1 });
  });

  it('stops scheduling a one-off lookup nobody has seen after six hours, until its link is opened, unless it has an alert on', async () => {
    const unseen = { carrier: 'unknown', current_stage: 'pending', tracking_number: 'TEST1234', user_id: null, one_off: true,
      created_at: '2026-09-09T05:30:00Z', last_synced_at: '2026-09-09T10:00:15Z', sync_status: 'waiting', archived_at: null,
      carrier_data: { routing: { version: 1, configured_carrier: 'unknown', failures: {} } } };
    // Listed for a link opened in the last day; nobody opened it in the last hour or keeps an alert on, unless `watched`.
    const check = async (parcels: JsonObject[], at: string, watched = false) => {
      const client = fakeClient();
      client.listFollowedOneOffPackages.mockResolvedValue(parcels);
      client.listUnwatchedPackageIds.mockResolvedValue(watched ? [] : parcels.map((parcel) => String(parcel.id)));
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
        { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null, () => new Date(at));
      return (await service.sync()).checked;
    };
    // Hourly while young and watched, then off the schedule.
    await expect(check([{ ...unseen, id: 'young' }], '2026-09-09T11:00:00Z', true)).resolves.toBe(1);
    await expect(check([{ ...unseen, id: 'old' }], '2026-09-09T11:30:00Z')).resolves.toBe(0);
    await expect(check([{ ...unseen, id: 'old' }], '2026-09-10T12:00:00Z')).resolves.toBe(0);
    // With an alert on, daily by day, not overnight, until 30 days after it was added.
    await expect(check([{ ...unseen, id: 'alerted' }], '2026-09-09T16:00:00Z', true)).resolves.toBe(0);
    await expect(check([{ ...unseen, id: 'alerted' }], '2026-09-10T12:00:00Z', true)).resolves.toBe(1);
    await expect(check([{ ...unseen, id: 'alerted' }], '2026-09-10T21:00:00Z', true)).resolves.toBe(0);
    await expect(check([{ ...unseen, id: 'alerted', created_at: '2026-08-10T05:30:00Z' }], '2026-09-10T12:00:00Z', true)).resolves.toBe(0);
    // A number a provider has answered for keeps the schedule.
    const seen = { ...unseen, id: 'seen', carrier_data: { routing: { version: 1, configured_carrier: 'unknown', failures: {},
      last_success_at: '2026-09-09T10:00:15Z' } } };
    await expect(check([seen], '2026-09-09T11:30:00Z', true)).resolves.toBe(1);
    // So does one filed under a carrier that refuses its number as one it does not issue, unlike
    // one whose carrier has yet to answer for its label.
    const refused = { ...unseen, id: 'refused', carrier: 'chronodiali', sync_status: 'error', sync_error: 'carrier:refused_number',
      carrier_data: { routing: { version: 1, configured_carrier: 'chronodiali', failures: {} } } };
    await expect(check([refused], '2026-09-09T11:30:00Z')).resolves.toBe(0);
    await expect(check([{ ...refused, id: 'filed', sync_status: 'waiting', sync_error: null }], '2026-09-09T11:30:00Z', true)).resolves.toBe(1);
    expect(isOpenedParcelSyncDue(refused, new Date('2026-09-09T16:00:00Z'))).toBe(true);
    // Opening the link still queues a check every six hours, then daily.
    expect(isOpenedParcelSyncDue(unseen, new Date('2026-09-09T11:30:00Z'))).toBe(false);
    expect(isOpenedParcelSyncDue(unseen, new Date('2026-09-09T16:00:00Z'))).toBe(true);
    const twoDays = { ...unseen, created_at: '2026-09-07T05:30:00Z' };
    expect(isOpenedParcelSyncDue(twoDays, new Date('2026-09-09T16:00:00Z'))).toBe(false);
    expect(isOpenedParcelSyncDue(twoDays, new Date('2026-09-10T10:00:00Z'))).toBe(true);
    // A week on, the schedule and releases ask weekly, but someone opening the link still gets a daily check.
    const eightDays = { ...unseen, created_at: '2026-09-01T05:30:00Z', last_synced_at: '2026-09-08T10:00:15Z' };
    expect(isOpenedParcelSyncDue(eightDays, new Date('2026-09-09T09:00:00Z'))).toBe(false);
    expect(isOpenedParcelSyncDue(eightDays, new Date('2026-09-09T10:00:00Z'))).toBe(true);
  });

  it('keeps out-for-delivery parcels hourly overnight and allows manual refreshes', async () => {
    const parcel = {
      id: 'overnight', carrier: 'swiss-post', current_stage: 'out_for_delivery', tracking_number: 'TEST1234',
      last_synced_at: '2026-09-09T20:00:15Z', sync_status: 'ok',
    };
    const client = fakeClient([parcel]);
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) };
    let now = new Date('2026-09-09T20:02:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      adapter, null, () => now);
    await expect(service.sync()).resolves.toMatchObject({ checked: 0 });
    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ checked: 1 });
    now = new Date('2026-09-09T21:00:00Z');
    await expect(service.sync()).resolves.toMatchObject({ checked: 1 });
  });

  it.each([
    // Swiss Post's window is on Zurich's clock: 15:00–17:00 opens at 13:00 UTC. Until 30 minutes
    // before, the regular cadence still catches a changed slot.
    { name: 'hours before its window', window: '2026-09-09 15:00–17:00', last: '10:00:15', time: '10:02:00', checked: 0 },
    { name: 'hours before its window', window: '2026-09-09 15:00–17:00', last: '10:00:15', time: '10:10:00', checked: 1 },
    { name: 'just over 30 minutes before its window', window: '2026-09-09 15:00–17:00', last: '12:20:15', time: '12:28:00', checked: 0 },
    { name: '30 minutes before its window', window: '2026-09-09 15:00–17:00', last: '12:30:15', time: '12:32:00', checked: 1 },
    { name: 'inside its window', window: '2026-09-09 15:00–17:00', last: '13:30:15', time: '13:32:00', checked: 1 },
    { name: 'after its window, late', window: '2026-09-09 15:00–17:00', last: '15:30:15', time: '15:32:00', checked: 1 },
    { name: 'before a window on a later day', window: '2026-09-10 09:00–11:00', last: '10:00:15', time: '10:02:00', checked: 0 },
    { name: 'after a window on an earlier day', window: '2026-09-08 09:00–11:00', last: '10:00:15', time: '10:02:00', checked: 1 },
    // A single time has no opening to wait for.
    { name: 'with a time but no window', window: '2026-09-09 17:00', last: '10:00:15', time: '10:02:00', checked: 1 },
    // Evri's window is on London's clock, from the catalog: 13:00–15:00 opens at 12:00 UTC, not 11:00.
    { name: 'before a window on London time', carrier: 'evri-uk', window: '2026-09-09 13:00–15:00', last: '11:20:15', time: '11:22:00', checked: 0 },
    { name: '30 minutes before a window on London time', carrier: 'evri-uk', window: '2026-09-09 13:00–15:00', last: '11:30:15', time: '11:32:00', checked: 1 },
    // The zone the carrier's answer declares comes before its catalog's.
    { name: 'before a window on the clock the answer declares', carrier: 'paack', timezone: 'Europe/Lisbon', window: '2026-09-09 13:00–15:00', last: '11:20:15', time: '11:22:00', checked: 0 },
    // A whole-day slot on the Canary Islands' clock opens at 08:00 UTC.
    { name: 'before a whole-day window', carrier: 'paack', timezone: 'Atlantic/Canary', window: '2026-09-09 09:00–22:00', last: '07:20:15', time: '07:22:00', checked: 0 },
    { name: '30 minutes before a whole-day window', carrier: 'paack', timezone: 'Atlantic/Canary', window: '2026-09-09 09:00–22:00', last: '07:30:15', time: '07:32:00', checked: 1 },
    // UTC stands for no known zone: the window cannot be placed, so it is not waited for.
    { name: 'before a window on an unknown clock', timezone: 'UTC', window: '2026-09-09 15:00–17:00', last: '10:00:15', time: '10:02:00', checked: 1 },
  ])('checks a parcel out for delivery $name at $time: $checked checks', async ({ carrier = 'swiss-post', timezone, window, last, time, checked }) => {
    const parcel = {
      id: 'window', carrier, current_stage: 'out_for_delivery', tracking_number: 'TEST1234', expected_delivery: window,
      last_synced_at: `2026-09-09T${last}Z`, sync_status: 'ok',
      carrier_data: { ...(timezone ? { timezone } : {}), routing: { version: 1, configured_carrier: carrier, last_event_at: '2026-09-09T06:00:00Z' } },
    };
    const client = fakeClient([parcel]);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'out_for_delivery' }) }, null, () => new Date(`2026-09-09T${time}Z`));
    await expect(service.sync()).resolves.toMatchObject({ checked });
  });

  it.each([
    { responseSender: undefined, expected: 'Example sender' },
    { responseSender: 'Updated sender', expected: 'Updated sender' },
    { responseSender: null, expected: undefined },
  ])('retains omitted sender information and accepts explicit changes: $responseSender', async ({ responseSender, expected }) => {
    const parcel: JsonObject = {
      id: 'sender-parcel', carrier: 'swiss-post', tracking_number: 'TEST1234',
      carrier_data: { sender_name: 'Example sender', obsolete: 'old response data' },
    };
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit', sender_name: responseSender }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ checked: 1, updated: 1 });
    const values = client.updatePackage.mock.calls.at(-1)?.[1];
    expect(values.carrier_data.sender_name).toBe(expected);
    expect(values.carrier_data.obsolete).toBeUndefined();
  });

  it('keeps where a parcel is going when a reply does not say, unless the router swapped carriers', async () => {
    const client = fakeClient();
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null);
    await service.syncPackage({ id: 'destination', carrier: 'swiss-post', tracking_number: 'TEST1234',
      carrier_data: { destination_country: 'CH' } });
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.destination_country).toBe('CH');

    const swapped = fakeClient();
    const adapter = { fetch: vi.fn().mockRejectedValueOnce(new Error('wrong carrier')).mockResolvedValue({
      status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z',
      events: [{ time: '2026-09-10T11:00:00Z', description: 'Confirmed UPS history', stage: 'in_transit' }],
    }), fetchUniversal: vi.fn() };
    await new TrackingSyncService(swapped as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'))
      .syncPackage({ id: 'swapped', carrier: 'dhl', tracking_number: '1Z999AA10123456784', current_stage: 'pending',
        carrier_data: { destination_country: 'CH' } });
    const saved = swapped.updatePackage.mock.calls.at(-1)![1];
    expect(saved.carrier).toBe('ups');
    expect(saved.carrier_data.destination_country).toBeUndefined();
  });

  it('takes the destination from an older reply when the newer summary kept does not name one', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit',
      last_status_text: 'Left the border point', last_update: '2026-09-10T09:00:00Z', destination_country: 'CH',
      events: [{ time: '2026-09-10T09:00:00Z', description: 'Left the border point', stage: 'in_transit' }] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await service.syncPackage({ id: 'older-destination', carrier: 'swiss-post', tracking_number: 'TEST1234', current_stage: 'in_transit',
      last_status_text: 'Passed a checkpoint',
      carrier_data: { tracking_provider: 'ParcelsApp', last_status_text: 'Passed a checkpoint',
        routing: { version: 1, configured_carrier: 'swiss-post', last_event_at: '2026-09-10T11:00:00.000Z' } } });
    const values = client.updatePackage.mock.calls.at(-1)![1];
    expect(values).not.toHaveProperty('last_status_text');
    expect(values.carrier_data).toMatchObject({ tracking_provider: 'ParcelsApp', last_status_text: 'Passed a checkpoint',
      destination_country: 'CH', routing: { last_event_at: '2026-09-10T11:00:00.000Z' } });
  });

  it('refreshes the delivery carrier and retains linked original tracking', async () => {
    const identity = {
      original_carrier: 'gls-de', original_tracking_number: '12345678901',
      original_tracking_url: 'https://www.gls-pakete.de/reach-sendungsverfolgung?match=12345678901',
      original_package_id: 'original-id',
    };
    const parcel: JsonObject = {
      id: 'delivery-parcel', carrier: 'swiss-post', tracking_number: '993412345612345678',
      carrier_data: identity,
    };
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'out_for_delivery', expected_delivery: '2026-09-10' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    await service.syncPackage(parcel);
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('swiss-post', parcel.tracking_number, null, null, null);
    expect(client.updatePackage.mock.calls.at(-1)?.[1]).toMatchObject({
      carrier_data: identity, current_stage: 'out_for_delivery', expected_delivery: '2026-09-10',
    });
  });

  it('switches an explicit DHL handoff to Swiss Post and keeps both histories', async () => {
    const parcel: JsonObject = { id: 'handoff', carrier: 'dhl', tracking_number: 'LF123456785DE', label: 'Garden cable' };
    const client = fakeClient();
    const origin = { status: 'in_transit', delivery_carrier: 'swiss-post', timezone: 'Europe/Berlin',
      events: [{ time: '2026-09-09T10:00:00+02:00', description: 'Arrived in destination country', stage: 'in_transit' }] };
    const delivery = { status: 'out_for_delivery', expected_delivery: '2026-09-10', timezone: 'Europe/Zurich',
      events: [{ time: '2026-09-10T07:00:00+02:00', description: 'Loaded into delivery vehicle', stage: 'out_for_delivery' }] };
    const adapter = { fetch: vi.fn().mockResolvedValueOnce(origin).mockResolvedValue(delivery) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    await service.syncPackage(parcel);
    const values = client.updatePackage.mock.calls.at(-1)?.[1];
    expect(values).toMatchObject({ current_stage: 'out_for_delivery', expected_delivery: '2026-09-10', carrier_data: {
      active_tracking_carrier: 'swiss-post', original_carrier: 'dhl', original_tracking_number: 'LF123456785DE',
    } });
    const events = client.insertEvents.mock.calls[0][0];
    expect(events.map((event: JsonObject) => String(event.provider_event_id).split(':')[0])).toEqual(['dhl', 'swiss-post']);
    adapter.fetch.mockClear();
    await service.syncPackage({ ...parcel, carrier_data: values.carrier_data });
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('swiss-post', parcel.tracking_number, null, null, null);
    expect(client.updatePackage.mock.calls.at(-1)?.[1].carrier_data).toMatchObject({ active_tracking_carrier: 'swiss-post', original_carrier: 'dhl' });
  });

  it('hands a France-to-Finland parcel to Posti and reuses the verified delivery leg', async () => {
    const parcel = { id: 'finnish-handoff', carrier: 'la-poste', tracking_number: 'CW123456785FR' };
    const client = fakeClient();
    const origin: CarrierResult = { status: 'in_transit', destination_country: 'FI', delivery_carrier: 'posti',
      last_update: '2026-01-11T10:00:00Z', events: [{ time: '2026-01-11T10:00:00Z', description: 'In transport', stage: 'in_transit' }] };
    const delivery: CarrierResult = { status: 'in_transit', current_stage: 'ready_for_pickup',
      last_update: '2026-01-12T10:00:00Z', events: [{ time: '2026-01-12T10:00:00Z', description: 'Ready for pickup', stage: 'ready_for_pickup' }] };
    const adapter = { fetch: vi.fn().mockResolvedValueOnce(origin).mockResolvedValue(delivery) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    await service.syncPackage(parcel);
    expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['la-poste', 'posti']);
    const values = client.updatePackage.mock.calls.at(-1)![1];
    expect(values).toMatchObject({ current_stage: 'ready_for_pickup', carrier_data: {
      active_tracking_carrier: 'posti', active_tracking_number: parcel.tracking_number, original_carrier: 'la-poste',
    } });
    expect(client.insertEvents.mock.calls[0][0].map((event: JsonObject) => String(event.provider_event_id).split(':')[0]))
      .toEqual(['la-poste', 'posti']);
    adapter.fetch.mockClear();
    await service.syncPackage({ ...parcel, ...values });
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('posti', parcel.tracking_number, null, null, null);
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.original_carrier).toBe('la-poste');
  });

  it.each(['dpd-be', 'dpd-nl'])('sends the parcel\'s postcode to DPD Switzerland when %s hands it a Swiss delivery', async (carrier) => {
    const parcel = { id: 'swiss-dpd-handoff', carrier, tracking_number: '05200000000001', dpd_postcode: '0000' };
    const origin: CarrierResult = { status: 'in_transit', delivery_carrier: 'dpd', last_update: '2026-01-11T10:00:00Z',
      events: [{ time: '2026-01-11T10:00:00Z', description: 'In transport', stage: 'in_transit' }] };
    const delivery: CarrierResult = { status: 'out_for_delivery', last_update: '2026-01-12T08:00:00Z',
      events: [{ time: '2026-01-12T08:00:00Z', description: 'Out for delivery', stage: 'out_for_delivery' }] };
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValueOnce(origin).mockResolvedValue(delivery) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    await service.syncPackage(parcel);
    expect(adapter.fetch.mock.calls).toEqual([[carrier, parcel.tracking_number, null, '0000', null], ['dpd', parcel.tracking_number, null, '0000', null]]);
    const values = client.updatePackage.mock.calls.at(-1)![1];
    expect(values.carrier_data).toMatchObject({ active_tracking_carrier: 'dpd', original_carrier: carrier });
    adapter.fetch.mockClear();
    await service.syncPackage({ ...parcel, ...values });
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('dpd', parcel.tracking_number, null, '0000', null);

    // Without a postcode DPD Switzerland is asked without one, and another brand's carrier never receives it.
    const bare = { fetch: vi.fn().mockResolvedValueOnce(origin).mockResolvedValue(delivery) };
    await new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient, bare, null).syncPackage({ ...parcel, dpd_postcode: null });
    expect(bare.fetch.mock.calls[1]).toEqual(['dpd', parcel.tracking_number, null, null, null]);
    const posti = { fetch: vi.fn().mockResolvedValueOnce({ ...origin, delivery_carrier: 'posti' }).mockResolvedValue(delivery) };
    await new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient, posti, null).syncPackage(parcel);
    expect(posti.fetch.mock.calls[1]).toEqual(['posti', parcel.tracking_number, null, null, null]);
  });

  it('asks the post of the country an outbound postal item is looked up from, when its origin names no destination', async () => {
    const parcel = { id: 'looked-up-handoff', carrier: 'an-post', tracking_number: 'LX123456785IE', current_stage: 'in_transit',
      carrier_data: { lookup_country_hint: 'FI', routing: { version: 1, configured_carrier: 'an-post', last_event_at: '2026-01-12T09:23:00.000Z' } } };
    // An Post's own clocks are offset-less, and it names neither the destination nor the partner.
    const origin: CarrierResult = { status: 'out_for_delivery', current_stage: 'ready_for_pickup', last_update: null,
      last_update_local: '2026-01-12T09:23:00', events: [
        { local_time: '2026-01-12T09:23:00', description: 'Your item is now available for collection', stage: 'ready_for_pickup' },
        { local_time: '2026-01-10T10:17:00', description: 'Your item arrived abroad', stage: 'in_transit' }] };
    const delivery: CarrierResult = { status: 'out_for_delivery', current_stage: 'ready_for_pickup', destination_country: 'FI',
      last_update: '2026-01-12T09:42:00Z', events: [
        { time: '2026-01-12T09:42:00Z', description: 'Arrived at post office', location: 'Example post office', stage: 'ready_for_pickup' }] };
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValueOnce(origin).mockResolvedValue(delivery) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-01-12T10:00:00Z'));
    await service.syncPackage(parcel);
    expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['an-post', 'posti']);
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'ready_for_pickup', carrier_data: {
      active_tracking_carrier: 'posti', original_carrier: 'an-post', destination_country: 'FI' } });

    // Looked up from the issuer's country, or with no country, nothing proposes a post.
    for (const carrier_data of [{ ...parcel.carrier_data, lookup_country_hint: 'IE' }, { routing: parcel.carrier_data.routing }]) {
      const quiet = { fetch: vi.fn().mockResolvedValue(origin) };
      await new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient, quiet, null, () => new Date('2026-01-12T10:00:00Z'))
        .syncPackage({ ...parcel, carrier_data });
      expect(quiet.fetch.mock.calls.map((call) => call[0])).not.toContain('posti');
    }
  });

  it('asks the post of the country a postal item is looked up from while its origin post cannot answer', async () => {
    const parcel = { id: 'looked-up-outage', carrier: 'dhl', tracking_number: 'RR123456785DE', current_stage: 'in_transit',
      carrier_data: { lookup_country_hint: 'FR', routing: { version: 1, configured_carrier: 'dhl', last_event_at: '2026-01-11T10:00:00.000Z' } } };
    const delivery: CarrierResult = { status: 'out_for_delivery', current_stage: 'out_for_delivery',
      last_update: '2026-01-12T09:42:00Z', events: [
        { time: '2026-01-12T09:42:00Z', description: 'En cours de livraison', location: 'Example office', stage: 'out_for_delivery' }] };
    const lookups = (adapter: { fetch: ReturnType<typeof vi.fn> }) => adapter.fetch.mock.calls.map((call) => call[0]);
    const adapter = { fetch: vi.fn(async (carrier: string) => {
      if (carrier === 'la-poste') return delivery;
      throw new ChallengeError('DHL');
    }) };
    const client = fakeClient();
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-01-12T10:00:00Z'));
    await service.syncPackage(parcel);
    expect(lookups(adapter)).toEqual(['dhl', 'la-poste']);
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'out_for_delivery', carrier_data: {
      active_tracking_carrier: 'la-poste', original_carrier: 'dhl' } });

    // Looked up from the issuer's country, or with no country, the origin's failure stands.
    for (const carrier_data of [{ ...parcel.carrier_data, lookup_country_hint: 'DE' }, { routing: parcel.carrier_data.routing }]) {
      const quiet = { fetch: vi.fn().mockRejectedValue(new ChallengeError('DHL')) };
      await new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient, quiet, null, () => new Date('2026-01-12T10:00:00Z'))
        .syncPackage({ ...parcel, carrier_data });
      expect(lookups(quiet)).toEqual(['dhl']);
    }
  });

  it('keeps the lookup country for the origin post a parcel filed under no carrier is asked about', async () => {
    const delivery: CarrierResult = { status: 'out_for_delivery', current_stage: 'out_for_delivery',
      last_update: '2026-01-12T09:42:00Z', events: [
        { time: '2026-01-12T09:42:00Z', description: 'En cours de livraison', location: 'Example office', stage: 'out_for_delivery' }] };
    // Deutsche Post names no destination for the item it exported.
    const exported: CarrierResult = { status: 'in_transit', current_stage: 'in_transit', last_update: '2026-01-11T10:00:00Z',
      events: [{ time: '2026-01-11T10:00:00Z', description: 'Die Sendung wurde ins Zielland transportiert', stage: 'in_transit' }] };
    for (const origin of [exported, new ChallengeError('DHL')]) {
      const client = { ...fakeClient(),
        acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: null }),
        finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
      const adapter = {
        fetch: vi.fn(async (carrier: string) => {
          if (carrier === 'la-poste') return delivery;
          if (origin instanceof Error) throw origin;
          return origin;
        }),
        fetchUniversal: vi.fn().mockRejectedValue(new NoHistoryError('UPU', 'No history')),
      };
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-01-12T10:00:00Z'));
      await service.syncPackage({ id: 'unfiled-postal', carrier: 'unknown', tracking_number: 'RR123456785DE', current_stage: 'pending',
        created_at: '2026-01-10T10:00:00Z', carrier_data: { lookup_country_hint: 'FR' } });
      expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['dhl', 'la-poste']);
      expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'out_for_delivery', carrier_data: {
        active_tracking_carrier: 'la-poste', original_carrier: 'dhl', lookup_country_hint: 'FR' } });
      expect(adapter.fetchUniversal).not.toHaveBeenCalled();
    }
  });

  it('waits out the probe gap for the origin post a parcel filed under no carrier is asked about', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: null }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    const adapter = {
      fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => {
        if (carrier === 'la-poste') throw Object.assign(new Error('Shipment not found'), { status: 404 });
        throw Object.assign(new Error('Bad gateway'), { status: 502 });
      }),
      fetchUniversal: vi.fn().mockRejectedValue(new NoHistoryError('UPU', 'No history')),
    };
    let now = new Date('2026-01-12T10:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    let parcel: JsonObject = { id: 'unfiled-postal', carrier: 'unknown', tracking_number: 'RR123456785DE', current_stage: 'in_transit',
      created_at: '2026-01-10T10:00:00Z', carrier_data: { lookup_country_hint: 'FR' } };
    const refresh = async (time: string) => {
      now = new Date(`2026-01-12T${time}:00Z`);
      adapter.fetch.mockClear();
      await service.syncPackage(parcel, { trigger: 'scheduled' });
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      return adapter.fetch.mock.calls.map((call) => call[0]);
    };

    expect(await refresh('10:00')).toEqual(['dhl', 'la-poste']);
    // DHL is asked without the parcel's data whenever its own wait is over, La Poste once its 55 min have passed.
    expect(await refresh('10:16')).toEqual(['dhl']);
    expect(await refresh('10:46')).toEqual(['dhl']);
    expect(await refresh('11:47')).toEqual(['dhl', 'la-poste']);
    expect(parcel).toMatchObject({ carrier: 'unknown', carrier_data: { delivery_probe: { carrier: 'la-poste', at: '2026-01-12T11:47:00.000Z' } } });
  });

  it('asks the earlier carrier hourly for a day after a handoff, for its late scans only', async () => {
    const client = { ...fakeClient(), acquireTrackingProvider: vi.fn(), finishTrackingProvider: vi.fn() };
    let now = new Date('2026-01-12T10:30:00Z');
    let origin: CarrierResult | Error = { status: 'in_transit', destination_country: 'FI', delivery_carrier: 'posti',
      last_update: '2026-01-11T10:00:00Z', events: [{ time: '2026-01-11T10:00:00Z', description: 'In transport', stage: 'in_transit' }] };
    const delivery: CarrierResult = { status: 'in_transit', current_stage: 'ready_for_pickup',
      last_update: '2026-01-12T10:00:00Z', events: [{ time: '2026-01-12T10:00:00Z', description: 'Ready for pickup', stage: 'ready_for_pickup' }] };
    const adapter = {
      fetch: vi.fn(async (carrier: string) => {
        if (carrier === 'posti') return delivery;
        if (origin instanceof Error) throw origin;
        return origin;
      }),
      fetchUniversal: vi.fn(),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    let parcel: JsonObject = { id: 'late-origin', carrier: 'la-poste', tracking_number: 'CW123456785FR' };
    const sync = async (at: string) => {
      now = new Date(at);
      adapter.fetch.mockClear();
      client.insertEvents.mockClear();
      await service.syncPackage(parcel);
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      return adapter.fetch.mock.calls.map((call) => call[0]);
    };
    expect(await sync('2026-01-12T10:30:00Z')).toEqual(['la-poste', 'posti']);
    expect(parcel.carrier_data).toMatchObject({ active_tracking_carrier: 'posti', handed_over_at: '2026-01-12T10:30:00.000Z' });
    expect(await sync('2026-01-12T11:00:00Z')).toEqual(['posti']);
    // A late scan, stamped after the delivery carrier's newest one, joins the history but not the watermark.
    origin = { ...origin, last_update: '2026-01-12T10:00:30Z', events: [...origin.events!,
      { time: '2026-01-12T10:00:30Z', description: 'Handed to the delivery carrier', stage: 'in_transit' }] };
    expect(await sync('2026-01-12T11:30:00Z')).toEqual(['posti', 'la-poste']);
    expect(client.insertEvents.mock.calls[0][0].map((event: JsonObject) => event.description))
      .toContain('Handed to the delivery carrier');
    expect(parcel).toMatchObject({ current_stage: 'ready_for_pickup', carrier_data: {
      earlier_checked_at: '2026-01-12T11:30:00.000Z', routing: { last_event_at: '2026-01-12T10:00:00.000Z' } } });
    expect(await sync('2026-01-12T12:00:00Z')).toEqual(['posti']);
    // A failed follow-up leaves the delivery carrier's answer, and waits an hour like any other.
    origin = new Error('La Poste is down');
    expect(await sync('2026-01-12T12:30:00Z')).toEqual(['posti', 'la-poste']);
    expect(parcel).toMatchObject({ sync_status: 'ok', carrier_data: { earlier_checked_at: '2026-01-12T12:30:00.000Z' } });
    expect(await sync('2026-01-12T13:00:00Z')).toEqual(['posti']);
    expect(await sync('2026-01-13T10:30:00Z')).toEqual(['posti']);
    expect(adapter.fetchUniversal).not.toHaveBeenCalled();
  });

  it('dates no row from the earlier carrier’s stage it reports without a time', async () => {
    const client = { ...fakeClient(), acquireTrackingProvider: vi.fn(), finishTrackingProvider: vi.fn() };
    let now = new Date('2026-01-12T10:30:00Z');
    let origin: CarrierResult = { status: 'in_transit', destination_country: 'FI', delivery_carrier: 'posti',
      last_update: '2026-01-11T10:00:00Z', events: [{ time: '2026-01-11T10:00:00Z', description: 'In transport', stage: 'in_transit' }] };
    let delivery: CarrierResult = { status: 'in_transit', current_stage: 'out_for_delivery',
      last_update: '2026-01-12T10:00:00Z', events: [{ time: '2026-01-12T10:00:00Z', description: 'Out for delivery', stage: 'out_for_delivery' }] };
    const adapter = { fetch: vi.fn(async (carrier: string) => carrier === 'posti' ? delivery : origin), fetchUniversal: vi.fn() };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    let parcel: JsonObject = { id: 'untimed-origin', carrier: 'la-poste', tracking_number: 'CW123456785FR' };
    await service.syncPackage(parcel);
    parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
    expect(parcel.carrier_data).toMatchObject({ active_tracking_carrier: 'posti' });
    // The earlier carrier relays the delivery on a clock without an offset; the delivery carrier times it.
    origin = { status: 'delivered', current_stage: 'delivered', last_status_text: 'Delivered', last_update_local: '2026-01-12T13:00:00',
      events: [{ local_time: '2026-01-12T13:00:00', description: 'Delivered', stage: 'delivered' }] };
    delivery = { status: 'delivered', current_stage: 'delivered', last_update: '2026-01-12T11:00:00Z',
      events: [...delivery.events!, { time: '2026-01-12T11:00:00Z', description: 'Delivered', stage: 'delivered' }] };
    now = new Date('2026-01-12T11:30:00Z');
    adapter.fetch.mockClear();
    client.insertEvents.mockClear();
    await service.syncPackage(parcel);
    expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['posti', 'la-poste']);
    const rows = client.insertEvents.mock.calls.flatMap((call) => call[0] as JsonObject[]);
    expect(rows.filter((row) => row.stage === 'delivered').map((row) => [row.occurred_at, (row.raw_data as JsonObject).observed_without_provider_timestamp]))
      .toEqual([['2026-01-12T11:00:00Z', undefined]]);
  });

  it('records no step back when the delivery partner starts its leg at acceptance, only when it had moved the parcel itself', async () => {
    const client = { ...fakeClient(), acquireTrackingProvider: vi.fn(), finishTrackingProvider: vi.fn() };
    let now = new Date('2026-01-12T10:30:00Z');
    const moved = { time: '2026-01-11T10:00:00Z', description: 'In transport', stage: 'in_transit' };
    const origin: CarrierResult = { status: 'in_transit', destination_country: 'FI', delivery_carrier: 'posti',
      last_update: moved.time, events: [moved] };
    // The partner's posting scan, in the minute the origin hands the parcel over.
    const posted = { time: '2026-01-12T10:00:00Z', description: 'Item posted', stage: 'accepted' };
    const delivery: CarrierResult = { status: 'in_transit', current_stage: 'accepted', last_update: posted.time, events: [posted] };
    const adapter = { fetch: vi.fn(async (carrier: string) => carrier === 'posti' ? delivery : origin), fetchUniversal: vi.fn() };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    const row = (carrier: string, scan: { time: string; description: string; stage: string }) => ({ stage: scan.stage,
      description: scan.description, occurred_at: scan.time, provider_event_id: providerEventId(carrier, scan.time, '', scan.description) });
    let parcel: JsonObject = { id: 'partner-leg', carrier: 'la-poste', tracking_number: 'CW123456785FR', current_stage: 'in_transit',
      [STORED_EVENT_IDENTITIES]: [row('la-poste', moved)] };
    const sync = async () => {
      await service.syncPackage(parcel);
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      return { stage: parcel.current_stage, anomalies: (client.completeSyncAttempt.mock.calls.at(-1)![1] as JsonObject).anomaly_codes };
    };

    expect(await sync()).toEqual({ stage: 'in_transit', anomalies: [] });
    expect(parcel.carrier_data).toMatchObject({ active_tracking_carrier: 'posti' });
    now = new Date('2026-01-12T12:30:00Z');
    parcel = { ...parcel, [STORED_EVENT_IDENTITIES]: [row('la-poste', moved), row('posti', posted)] };
    expect(await sync()).toEqual({ stage: 'in_transit', anomalies: [] });
    // A partner listing its acceptance without its later scan in transit brings nothing after it:
    // no step back, only the scan it withdrew (next test).
    const sorted = { time: '2026-01-12T11:00:00Z', description: 'Sorted', stage: 'in_transit' };
    now = new Date('2026-01-12T13:30:00Z');
    parcel = { ...parcel, [STORED_EVENT_IDENTITIES]: [row('la-poste', moved), row('posti', posted), row('posti', sorted)] };
    expect(await sync()).toEqual({ stage: 'in_transit', anomalies: ['scans_withdrawn'] });
    // A partner back at acceptance after a scan of its own in transit has stepped back, at the check
    // that brings the scan, and not again while its answers repeat it.
    const reposted = { time: '2026-01-12T12:00:00Z', description: 'Item posted again', stage: 'accepted' };
    adapter.fetch.mockImplementation(async (carrier: string) => carrier === 'posti'
      ? { ...delivery, last_update: reposted.time, events: [reposted, sorted, posted] } : origin);
    now = new Date('2026-01-12T14:30:00Z');
    expect(await sync()).toEqual({ stage: 'in_transit', anomalies: ['early_stage_regression'] });
    now = new Date('2026-01-12T15:30:00Z');
    parcel = { ...parcel, [STORED_EVENT_IDENTITIES]: [row('la-poste', moved), row('posti', posted), row('posti', sorted), row('posti', reposted)] };
    expect(await sync()).toEqual({ stage: 'in_transit', anomalies: [] });
  });

  it.each([
    ['a scan of its own in transit', {}],
    ['the stage its summary showed', { observed_without_provider_timestamp: true }],
  ])('signals a delivery partner’s first answer without %s, once for that partner', async (_case, observed) => {
    const client = { ...fakeClient(), acquireTrackingProvider: vi.fn(), finishTrackingProvider: vi.fn() };
    let now = new Date('2026-01-12T10:30:00Z');
    const moved = { time: '2026-01-11T10:00:00Z', description: 'In transport', stage: 'in_transit' };
    const origin: CarrierResult = { status: 'in_transit', destination_country: 'FI', delivery_carrier: 'posti',
      last_update: moved.time, events: [moved] };
    const posted = { time: '2026-01-12T10:00:00Z', description: 'Item posted', stage: 'accepted' };
    const sorted = { time: '2026-01-12T11:00:00Z', description: 'Sorted', stage: 'in_transit' };
    const delivery: CarrierResult = { status: 'in_transit', current_stage: 'accepted', last_update: posted.time, events: [posted] };
    const adapter = { fetch: vi.fn(async (carrier: string) => carrier === 'posti' ? delivery : origin), fetchUniversal: vi.fn() };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    const row = (carrier: string, scan: typeof moved) => ({ stage: scan.stage,
      description: scan.description, occurred_at: scan.time, provider_event_id: providerEventId(carrier, scan.time, '', scan.description) });
    let parcel: JsonObject = { id: 'partner-withdrew', carrier: 'la-poste', tracking_number: 'CW123456785FR', current_stage: 'in_transit',
      [STORED_EVENT_IDENTITIES]: [row('la-poste', moved)] };
    const sync = async () => {
      await service.syncPackage(parcel);
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      return { stage: parcel.current_stage, anomalies: (client.completeSyncAttempt.mock.calls.at(-1)![1] as JsonObject).anomaly_codes };
    };

    expect(await sync()).toEqual({ stage: 'in_transit', anomalies: [] });
    // The parcel's own carrier was signalled before; the partner still is, at its first such answer.
    parcel = { ...parcel, carrier_data: { ...parcel.carrier_data as JsonObject, scans_withdrawn: { 'la-poste': '2026-01-10T08:00:00.000Z' } },
      [STORED_EVENT_IDENTITIES]: [row('la-poste', moved), row('posti', posted), { ...row('posti', sorted), ...observed }] };
    now = new Date('2026-01-12T12:30:00Z');
    expect(await sync()).toEqual({ stage: 'in_transit', anomalies: ['scans_withdrawn'] });
    expect(parcel).toMatchObject({ sync_status: 'ok', sync_error: null,
      carrier_data: { scans_withdrawn: { 'la-poste': '2026-01-10T08:00:00.000Z', posti: '2026-01-12T12:30:00.000Z' } } });
    now = new Date('2026-01-12T13:30:00Z');
    expect(await sync()).toEqual({ stage: 'in_transit', anomalies: [] });
  });

  it('does not let a Swiss issuer suffix bypass an explicitly selected Posti adapter', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'delivered' }) };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'explicit-posti', carrier: 'posti', tracking_number: 'LX123456785CH' });
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('posti', 'LX123456785CH', null, null, null);
    expect(client.updatePackage.mock.calls.at(-1)![1].current_stage).toBe('delivered');
  });

  it.each(['stale', 'undated', 'registered', 'unknown', 'failed', 'not_found', 'invalid_response', 'terminal_conflict'])(
    'keeps the origin when a Posti handoff is %s', async (state) => {
      const client = fakeClient();
      const origin: CarrierResult = { status: state === 'terminal_conflict' ? 'delivered' : 'in_transit',
        last_update: '2026-01-12T10:00:00Z', delivery_carrier: 'posti', destination_country: 'FI' };
      const delivery: CarrierResult = { status: 'in_transit', current_stage: 'ready_for_pickup', last_update: '2026-01-13T10:00:00Z' };
      if (state === 'stale') delivery.last_update = '2026-01-11T10:00:00Z';
      if (state === 'undated') delivery.last_update = null;
      if (state === 'registered') { delivery.status = 'pending'; delivery.current_stage = 'registered'; }
      if (state === 'unknown') { delivery.status = 'unknown'; delete delivery.current_stage; }
      const adapter = { fetch: vi.fn().mockResolvedValueOnce(origin) };
      if (state === 'failed') adapter.fetch.mockRejectedValueOnce(new Error('Temporary Posti failure'));
      else if (state === 'not_found') adapter.fetch.mockRejectedValueOnce(Object.assign(new Error('Shipment not found'), { status: 404 }));
      else if (state === 'invalid_response') adapter.fetch.mockResolvedValueOnce({ status: 'delivered', events: 'invalid' });
      else adapter.fetch.mockResolvedValueOnce(delivery);
      await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
        .syncPackage({ id: 'unconfirmed', carrier: 'la-poste', tracking_number: 'CW123456785FR' });
      expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.active_tracking_carrier).toBeUndefined();
      expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({
        sync_status: 'ok', current_stage: origin.status, carrier_data: { last_update: origin.last_update },
      });
    },
  );

  it('can confirm an explicit delivery partner after the origin reports delivery', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValueOnce({ status: 'delivered', delivery_carrier: 'posti', last_update: '2026-01-12T10:00:00Z' })
      .mockResolvedValueOnce({ status: 'delivered', last_update: '2026-01-12T10:00:00Z' }) };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'completed', carrier: 'la-poste', tracking_number: 'CW123456785FR' });
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.active_tracking_carrier).toBe('posti');
  });

  it.each(['partner', 'destination'])('retains a %s handoff with same-day completion without replacing a newer saved summary', async (basis) => {
    const client = fakeClient();
    const hints = basis === 'partner' ? { delivery_carrier: 'posti' } : { destination_country: 'FI' };
    const adapter = { fetch: vi.fn().mockResolvedValueOnce({ status: 'delivered', ...hints, last_update: '2026-01-12T13:00:00Z' })
      .mockResolvedValueOnce({ status: 'delivered', last_update: '2026-01-12T12:00:00Z' }) };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'same-completion', carrier: 'la-poste', tracking_number: 'CW123456785FR', current_stage: 'delivered',
        carrier_data: { routing: { version: 1, configured_carrier: 'la-poste', last_event_at: '2026-01-12T13:00:00Z' } } });
    const values = client.updatePackage.mock.calls.at(-1)![1];
    expect(values.carrier_data).toMatchObject({ original_carrier: 'la-poste', active_tracking_carrier: 'posti',
      routing: { last_event_at: '2026-01-12T13:00:00Z' } });
    expect(values.last_status_text).toBeUndefined();
    expect(values.current_stage).toBeUndefined();
  });

  it('rejects a delivery partner’s completion from a different day', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValueOnce({ status: 'delivered', delivery_carrier: 'posti', last_update: '2026-01-12T13:00:00Z' })
      .mockResolvedValueOnce({ status: 'delivered', last_update: '2026-01-11T12:00:00Z' }) };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'old-completion', carrier: 'la-poste', tracking_number: 'CW123456785FR' });
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.active_tracking_carrier).toBeUndefined();
  });

  it('retains named partner probe cooldowns and rechecks when the origin advances', async () => {
    const client = fakeClient();
    let now = new Date('2026-01-12T11:00:00Z');
    let update = '2026-01-12T10:00:00Z';
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => carrier === 'posti'
      ? { status: 'pending', current_stage: 'registered' }
      : { status: 'in_transit', last_update: update, delivery_carrier: 'posti' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    let parcel: JsonObject = { id: 'destination', carrier: 'la-poste', tracking_number: 'CW123456785FR' };
    for (const expected of [['la-poste', 'posti'], ['la-poste'], ['la-poste', 'posti']]) {
      adapter.fetch.mockClear();
      await service.syncPackage(parcel, { trigger: 'scheduled' });
      expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(expected);
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      if (expected.length === 1) update = '2026-01-12T11:05:00Z';
      now = new Date(now.getTime() + 10 * 60_000);
    }
  });

  it('retains an unsuccessful probe even when an older origin summary is preserved', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => carrier === 'posti'
      ? { status: 'unknown' }
      : { status: 'in_transit', delivery_carrier: 'posti', last_update: '2026-01-12T09:00:00Z' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-01-12T11:00:00Z'));
    let parcel: JsonObject = { id: 'preserved', carrier: 'la-poste', tracking_number: 'CW123456785FR',
      current_stage: 'in_transit', carrier_data: { last_update: '2026-01-12T10:00:00Z', routing: {
        version: 1, configured_carrier: 'la-poste', last_event_at: '2026-01-12T10:00:00Z',
      } } };
    await service.syncPackage(parcel);
    parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
    expect(parcel.carrier_data).toMatchObject({ last_update: '2026-01-12T10:00:00Z',
      delivery_probe: { carrier: 'posti', at: '2026-01-12T11:00:00.000Z' } });
    adapter.fetch.mockClear();
    await service.syncPackage(parcel);
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('la-poste', parcel.tracking_number, null, null, null);
  });

  it('lets an aggregator report Posti without a Swiss issuer suffix overriding the partner', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValueOnce({ status: 'in_transit', delivery_carrier: 'posti' })
      .mockResolvedValueOnce({ status: 'out_for_delivery' }) };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'named-postal', carrier: 'aliexpress', tracking_number: 'LX123456785CH' });
    expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['aliexpress', 'posti']);
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.active_tracking_carrier).toBe('posti');
  });

  it('lets the delivery scan take over a universal copy at a handoff and keeps the origin\'s relay of it', async () => {
    const client = fakeClient();
    const hub = { location: 'Springfield Gardens, NY', stage: 'in_transit' };
    const adapter = { fetch: vi.fn()
      .mockResolvedValueOnce({ status: 'in_transit', last_update: '2026-01-11T21:11:25Z',
        delivery_carrier: 'gofo', delivery_tracking_number: 'GFUS01000000000001', events: [
          { time: '2026-01-10T10:00:00Z', description: 'Left the sorting center', location: 'Lviv', stage: 'in_transit' },
          { time: '2026-01-11T21:11:25Z', description: 'Arrived at the depot', ...hub },
        ] })
      .mockResolvedValueOnce({ status: 'in_transit', last_update: '2026-01-11T21:11:25Z', events: [
        { time: '2026-01-11T21:11:25Z', description: 'Arrived at GOFO Regional Hub', ...hub },
      ] }) };
    // A universal provider's copy of the relay, stored before the handoff.
    const copy = { provider_event_id: 'unknown:copy', occurred_at: '2026-01-11T21:11:25+00:00', description: 'Arrived at the depot', ...hub };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'relayed', carrier: 'nova-poshta', tracking_number: '59500000000012', [STORED_EVENT_IDENTITIES]: [copy] });
    expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['nova-poshta', 'gofo']);
    const saved = client.insertEvents.mock.calls[0][0].map((event: JsonObject) => [String(event.provider_event_id).split(':')[0], event.description]);
    expect(saved).toEqual(expect.arrayContaining([['nova-poshta', 'Left the sorting center'], ['nova-poshta', 'Arrived at the depot'],
      ['unknown', 'Arrived at GOFO Regional Hub']]));
    expect(saved).toHaveLength(3);
  });

  it('lets the delivery scan take over a universal copy at a handoff when one carrier keeps the minute and the other the second', async () => {
    const hub = { location: 'Springfield Gardens, NY', stage: 'in_transit' };
    const handoff = async (relayAt: string, scanAt: string, copy: JsonObject) => {
      const client = fakeClient();
      const adapter = { fetch: vi.fn()
        .mockResolvedValueOnce({ status: 'in_transit', last_update: relayAt,
          delivery_carrier: 'gofo', delivery_tracking_number: 'GFUS01000000000001', events: [
            { time: '2026-01-10T10:00:00Z', description: 'Left the sorting center', location: 'Lviv', stage: 'in_transit' },
            { time: relayAt, description: 'Arrived at the depot', ...hub, stage: 'customs' },
          ] })
        .mockResolvedValueOnce({ status: 'in_transit', last_update: scanAt, events: [
          { time: scanAt, description: 'Arrived at GOFO Regional Hub', ...hub },
        ] }) };
      await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
        .syncPackage({ id: 'relayed', carrier: 'nova-poshta', tracking_number: '59500000000012',
          [STORED_EVENT_IDENTITIES]: [{ provider_event_id: 'unknown:copy', ...hub, ...copy }] });
      return client.insertEvents.mock.calls[0][0].map((event: JsonObject) => [String(event.provider_event_id).split(':')[0], event.description]);
    };
    const kept = [['nova-poshta', 'Left the sorting center'], ['nova-poshta', 'Arrived at the depot'], ['unknown', 'Arrived at GOFO Regional Hub']];
    // The origin keeps the minute: taking a provider's copy at its own instant, its relay would leave the delivery scan
    // a row of its own that nothing pairs.
    expect(await handoff('2026-01-11T21:11:00Z', '2026-01-11T21:11:25Z',
      { occurred_at: '2026-01-11T21:11:00+00:00', description: 'Arrived at GOFO Regional Hub' })).toEqual(kept);
    // The delivery carrier keeps the minute: the relay's copy to the second is the delivery scan's to take.
    expect(await handoff('2026-01-11T21:11:25Z', '2026-01-11T21:11:00Z',
      { occurred_at: '2026-01-11T21:11:25+00:00', description: 'Arrived at the depot' })).toEqual(kept);
  });

  it('lets the delivery scan take over a universal copy at a handoff when the origin relays it in another stage', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn()
      .mockResolvedValueOnce({ status: 'exception', last_update: '2026-01-11T21:11:25Z',
        delivery_carrier: 'gofo', delivery_tracking_number: 'GFUS01000000000001', events: [
          { time: '2026-01-10T10:00:00Z', description: 'Left the sorting center', location: 'Lviv', stage: 'in_transit' },
          { time: '2026-01-11T21:11:25Z', description: 'Address incomplete, returning to sender', location: 'Springfield Gardens, NY', stage: 'exception' },
        ] })
      .mockResolvedValueOnce({ status: 'returned', last_update: '2026-01-11T21:11:25Z', events: [
        { time: '2026-01-11T21:11:25Z', description: 'Address incomplete, returning to sender', location: 'US', stage: 'returned' },
      ] }) };
    // A universal provider's copy of the scan in the same words, stored before the handoff.
    const copy = { provider_event_id: 'unknown:copy', occurred_at: '2026-01-11T21:11:25+00:00',
      description: 'Address incomplete, returning to sender', location: 'Springfield Gardens, NY', stage: 'returned' };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'relayed', carrier: 'nova-poshta', tracking_number: '59500000000012', [STORED_EVENT_IDENTITIES]: [copy] });
    const saved = client.insertEvents.mock.calls[0][0].map((event: JsonObject) => [String(event.provider_event_id).split(':')[0], event.stage]);
    // The origin's relay stays its own row, for relayCopies.ts to pair; taking the copy over, it would leave the
    // delivery scan a row of its own that nothing pairs.
    expect(saved).toEqual(expect.arrayContaining([['nova-poshta', 'exception'], ['unknown', 'returned']]));
    expect(saved).toHaveLength(3);
  });

  it('asks the delivery carrier a provider names for a parcel filed under a carrier with no adapter of its own, then follows it there', async () => {
    vi.spyOn(scraper, 'universalPlan').mockReturnValue({ sources: ['ParcelsApp'], carrier: null, tier: () => 'unknown', rank: () => 0 });
    const store = eventStore();
    const client = Object.assign(store.client, {
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) });
    // ParcelsApp follows a Z-Leading parcel to DPD Poland's number; numbers, wording and clocks are invented.
    const out = { time: '2026-10-26T07:00:00Z', description: 'Out for delivery', stage: 'out_for_delivery' };
    const provider: CarrierResult = { status: 'in_transit', current_stage: 'in_transit', last_update: '2026-10-24T06:34:09Z',
      delivery_carrier: 'dpd-pl', delivery_tracking_number: '1000000000001U',
      events: [{ time: '2026-10-24T06:34:09Z', description: 'Handed to the delivery company', stage: 'in_transit' }] };
    const dpd = (...events: Array<typeof out>): CarrierResult => ({ status: events[0].stage as CarrierResult['status'],
      current_stage: events[0].stage, last_update: events[0].time, events });
    const adapter = { fetch: vi.fn().mockRejectedValueOnce(new CarrierError('not_found', 'dpd-pl', 'Unknown parcel'))
      .mockResolvedValueOnce(dpd(out))
      .mockResolvedValueOnce(dpd({ time: '2026-10-26T11:36:00Z', description: 'Delivered', stage: 'delivered' }, out)),
    fetchUniversal: vi.fn().mockResolvedValue(provider) };
    let saved: JsonObject = { id: 'named-last-mile', carrier: 'z-leading', tracking_number: 'ZLAB000000001', current_stage: 'pending',
      created_at: '2026-10-21T15:00:00Z', one_off: true };
    const check = async (at: string) => {
      await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date(at))
        .syncPackage({ ...saved, [STORED_EVENT_IDENTITIES]: store.identities() });
      saved = { ...saved, ...client.applyTrackingSync.mock.calls.at(-1)![1] };
      return saved.carrier_data as JsonObject;
    };
    // DPD Poland does not know its number yet: the provider's answer serves the parcel, and the ask is kept for its gap.
    let data = await check('2026-10-26T06:00:00Z');
    expect(adapter.fetch.mock.calls.map((call) => call.slice(0, 2))).toEqual([['dpd-pl', '1000000000001U']]);
    expect(saved.current_stage).toBe('in_transit');
    expect(data.active_tracking_carrier).toBeUndefined();
    expect(data.delivery_probe).toMatchObject({ carrier: 'dpd-pl', number: '1000000000001U', misses: 1 });
    // Once it scans the parcel, it takes the parcel over.
    data = await check('2026-10-26T09:00:00Z');
    expect(adapter.fetch).toHaveBeenCalledTimes(2);
    expect(data).toMatchObject({ active_tracking_carrier: 'dpd-pl', active_tracking_number: '1000000000001U',
      original_carrier: 'z-leading', original_tracking_number: 'ZLAB000000001' });
    expect(saved.current_stage).toBe('out_for_delivery');
    // The next check asks it alone.
    await check('2026-10-26T12:00:00Z');
    expect(adapter.fetch.mock.calls.map((call) => call.slice(0, 2))).toEqual(Array(3).fill(['dpd-pl', '1000000000001U']));
    expect(adapter.fetchUniversal).toHaveBeenCalledTimes(2);
    expect(saved.current_stage).toBe('delivered');
    expect(saved.carrier).toBe('z-leading');
  });

  it('hands a Cainiao result to its reported local number through normalization', async () => {
    const swissNumber = 'RA314159268CH';
    const origin = { ...carrierResult('cainiao-delivered'), delivery_tracking_number: swissNumber };
    const cainiao = vi.fn().mockResolvedValue(origin);
    const fetcher = vi.fn<typeof fetch>().mockImplementation(async () => Response.json({}));
    const swiss = vi.fn().mockResolvedValue({ status: 'delivered', current_stage: 'delivered',
      last_update: '2026-03-04T12:00:00Z', events: [{ time: '2026-03-04T12:00:00Z', description: 'Delivered', stage: 'delivered' }] });
    const registry = new AdapterRegistry({ factories: {
      aliexpress: () => ({ id: 'aliexpress', steps: ['direct'], track: cainiao }),
      'swiss-post': () => ({ id: 'swiss-post', steps: ['direct'], track: swiss }),
    }, carriers: { aliexpress: 'aliexpress', 'swiss-post': 'swiss-post' } }, {
      fetcher, trawl: null, browserExecutablePath: null, recorder: NOOP_RECORDER, env: {},
    });
    const direct = new CarrierTrackingAdapter(undefined, registry, NOOP_RECORDER);
    const adapter = { fetch: vi.fn(direct.fetch.bind(direct)), fetchUniversal: vi.fn().mockRejectedValue(new Error('Unexpected universal lookup')) };
    const client = fakeClient();
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    const parcel = { id: 'cainiao-reference', carrier: 'aliexpress', tracking_number: 'LP00000000000001' };
    await service.syncPackage(parcel);
    expect(adapter.fetch.mock.calls.map((call) => call.slice(0, 2))).toEqual([
      ['aliexpress', 'LP00000000000001'], ['swiss-post', swissNumber],
    ]);
    expect(swiss).toHaveBeenCalledWith({ number: swissNumber, trackingUrl: null, postcode: null });
    const values = client.updatePackage.mock.calls.at(-1)![1];
    expect(values.carrier_data).toMatchObject({ original_carrier: 'aliexpress',
      original_tracking_number: 'LP00000000000001', active_tracking_carrier: 'swiss-post', active_tracking_number: swissNumber });
    const eventCarriers = new Set(client.insertEvents.mock.calls[0][0].map((event: JsonObject) => String(event.provider_event_id).split(':')[0]));
    expect(eventCarriers).toEqual(new Set(['aliexpress', 'swiss-post']));
    adapter.fetch.mockClear();
    await service.syncPackage({ ...parcel, ...values });
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('swiss-post', swissNumber, null, null, null);
    expect(cainiao).toHaveBeenCalledOnce();
    expect(adapter.fetchUniversal).not.toHaveBeenCalled();
  });

  it.each(['aliexpress', 'intl-post'])('retains the established %s Swiss postal probe and pins only confirmed progress', async (carrier) => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValueOnce({ status: 'in_transit', last_update: '2026-03-04T10:00:00Z' })
      .mockResolvedValueOnce({ status: 'out_for_delivery', last_update: '2026-03-04T12:00:00Z' }) };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'legacy-postal', carrier, tracking_number: 'LX123456785CH' });
    expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual([carrier, 'swiss-post']);
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.active_tracking_carrier).toBe('swiss-post');
  });

  it.each([['aliexpress', true], ['intl-post', true], ['aliexpress', false]] as const)('retains a previously confirmed %s Swiss route with stored carrier %s', async (carrier, storedCarrier) => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'out_for_delivery' }) };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'legacy-confirmed', carrier, tracking_number: 'LX123456785CH', carrier_data: {
        swiss_post_ready: true, ...(storedCarrier ? { active_tracking_carrier: 'swiss-post' } : {}),
      } });
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('swiss-post', 'LX123456785CH', null, null, null);
  });

  it('keeps Cainiao and respects the probe cooldown when Swiss Post does not know the historical postal number', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => {
      if (carrier === 'swiss-post') throw Object.assign(new Error('Shipment not found'), { status: 404 });
      return { status: 'in_transit', last_update: '2026-03-04T10:00:00Z' };
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-03-04T12:00:00Z'));
    const parcel = { id: 'legacy-unannounced', carrier: 'aliexpress', tracking_number: 'LX123456785CH' };
    await service.syncPackage(parcel);
    expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['aliexpress', 'swiss-post']);
    const values = client.updatePackage.mock.calls.at(-1)![1];
    expect(values).toMatchObject({ sync_status: 'ok', current_stage: 'in_transit', carrier_data: { active_tracking_carrier: 'aliexpress' } });
    adapter.fetch.mockClear();
    await service.syncPackage({ ...parcel, ...values });
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('aliexpress', 'LX123456785CH', null, null, null);
  });

  it('asks Swiss Post less often about a Swiss postal number it keeps missing, and at once on Cainiao news', async () => {
    const client = fakeClient();
    let now = new Date('2026-09-10T10:00:00Z');
    let cainiaoUpdate = '2026-09-09T10:00:00+08:00';
    let swiss: CarrierResult | null = null;
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => {
      if (carrier === 'aliexpress') return { status: 'in_transit', last_update: cainiaoUpdate,
        events: [{ time: cainiaoUpdate, description: 'Departed from sorting center', stage: 'in_transit' }] };
      if (swiss) return swiss;
      throw Object.assign(new Error('Shipment not found'), { status: 404 });
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    let parcel: JsonObject = { id: 'swiss-letter', carrier: 'swiss-post', tracking_number: 'LX123456785CH' };
    const refresh = async (time: string, trigger: 'package' | 'scheduled' = 'scheduled') => {
      now = new Date(`2026-09-10T${time}:00Z`);
      adapter.fetch.mockClear();
      await service.syncPackage(parcel, { trigger });
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      return adapter.fetch.mock.calls.map((call) => call[0]);
    };

    expect(await refresh('10:00')).toEqual(['swiss-post', 'aliexpress']);
    expect(parcel.carrier_data).toMatchObject({ delivery_probe: { at: '2026-09-10T10:00:00.000Z', origin_update: cainiaoUpdate, misses: 1 } });
    // Each miss doubles the gap, from 55 min up to 6 h.
    for (const [time, asked, misses] of [['10:50', false, 1], ['10:55', true, 2], ['12:40', false, 2], ['12:45', true, 3],
      ['16:20', false, 3], ['16:25', true, 4], ['22:20', false, 4], ['22:25', true, 5]] as const) {
      expect(await refresh(time)).toEqual(asked ? ['swiss-post', 'aliexpress'] : ['aliexpress']);
      expect(parcel.carrier_data).toMatchObject({ delivery_probe: { misses } });
    }
    // A refresh by hand asks again after 5 min.
    expect(await refresh('22:27', 'package')).toEqual(['aliexpress']);
    expect(await refresh('22:30', 'package')).toEqual(['swiss-post', 'aliexpress']);
    // Cainiao's news asks at once and starts the gaps over.
    cainiaoUpdate = '2026-09-11T05:00:00+08:00';
    expect(await refresh('22:40')).toEqual(['aliexpress', 'swiss-post']);
    expect(parcel.carrier_data).toMatchObject({ delivery_probe: { at: '2026-09-10T22:40:00.000Z', origin_update: cainiaoUpdate, misses: 1 } });
    swiss = { status: 'in_transit', last_update: '2026-09-10T23:30:00+02:00', timezone: 'Europe/Zurich',
      events: [{ time: '2026-09-10T23:30:00+02:00', description: 'Arrived at sorting centre', stage: 'in_transit' }] };
    expect(await refresh('23:40')).toEqual(['swiss-post']);
    expect(parcel.carrier_data).toMatchObject({ swiss_post_ready: true });
  });

  it('keeps the Swiss Post probe’s gap through a check a provider answers while Cainiao is down', async () => {
    vi.spyOn(scraper, 'universalPlan').mockReturnValue({ sources: ['ParcelsApp'], carrier: null, tier: () => 'unknown', rank: () => 0 });
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const cainiaoUpdate = '2026-09-09T10:00:00+08:00';
    const cainiao: CarrierResult = { status: 'in_transit', last_update: cainiaoUpdate,
      events: [{ time: cainiaoUpdate, description: 'Departed from sorting center', stage: 'in_transit' }] };
    let cainiaoDown = false;
    const adapter = {
      fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => {
        if (carrier !== 'aliexpress') throw Object.assign(new Error('Shipment not found'), { status: 404 });
        if (cainiaoDown) throw Object.assign(new Error('Bad gateway'), { status: 502 });
        return cainiao;
      }),
      fetchUniversal: vi.fn().mockResolvedValue(cainiao),
    };
    let now = new Date('2026-09-10T11:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    const probe = { carrier: 'swiss-post', number: 'LX123456785CH', at: '2026-09-10T10:00:00.000Z', origin_update: cainiaoUpdate, misses: 5 };
    let parcel: JsonObject = { id: 'swiss-letter', carrier: 'swiss-post', tracking_number: 'LX123456785CH', current_stage: 'in_transit',
      carrier_data: { active_tracking_carrier: 'aliexpress', swiss_post_ready: false, last_update: cainiaoUpdate, delivery_probe: probe } };

    cainiaoDown = true;
    await service.syncPackage(parcel, { trigger: 'scheduled' });
    expect(adapter.fetchUniversal).toHaveBeenCalledTimes(1);
    parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
    expect(parcel.carrier_data).toMatchObject({ tracking_provider: 'ParcelsApp', delivery_probe: probe });

    // Swiss Post missed five times: it waits its 6 h from the last probe, not 55 min from the provider's answer.
    cainiaoDown = false;
    now = new Date('2026-09-10T12:00:00Z');
    adapter.fetch.mockClear();
    await service.syncPackage(parcel, { trigger: 'scheduled' });
    expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['aliexpress']);
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data).toMatchObject({ delivery_probe: probe });
  });

  it('counts a Swiss Post probe toward its gap when Cainiao fails after it', async () => {
    const client = fakeClient();
    let now = new Date('2026-09-10T10:00:00Z');
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => {
      if (carrier === 'swiss-post') throw Object.assign(new Error('Shipment not found'), { status: 404 });
      throw Object.assign(new Error('Bad gateway'), { status: 502 });
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    let parcel: JsonObject = { id: 'swiss-letter', carrier: 'swiss-post', tracking_number: 'LX123456785CH', current_stage: 'in_transit' };
    const refresh = async (time: string) => {
      now = new Date(`2026-09-10T${time}:00Z`);
      adapter.fetch.mockClear();
      await service.syncPackage(parcel, { trigger: 'scheduled' });
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      return adapter.fetch.mock.calls.map((call) => call[0]);
    };

    expect(await refresh('10:00')).toEqual(['swiss-post', 'aliexpress']);
    expect(parcel).toMatchObject({ sync_status: 'error', carrier_data: {
      delivery_probe: { carrier: 'swiss-post', at: '2026-09-10T10:00:00.000Z', origin_update: null, misses: 1 } } });
    // Within the gap Cainiao answers first; while it cannot say whether the item moved, the gap does not grow.
    expect(await refresh('10:10')).toEqual(['aliexpress']);
    expect(await refresh('10:56')).toEqual(['swiss-post', 'aliexpress']);
    expect(parcel.carrier_data).toMatchObject({ delivery_probe: { at: '2026-09-10T10:56:00.000Z', misses: 1 } });
  });

  it.each(['Switzerland', 'Finland'])('respects Cainiao’s actual destination field before the historical probe: %s', async (country) => {
    const number = 'LX123456785CH';
    const origin = carrierResult(country === 'Switzerland' ? 'cainiao-Switzerland' : 'cainiao-Finland');
    const adapter = { fetch: vi.fn().mockResolvedValueOnce(origin).mockResolvedValueOnce({ status: 'unknown' }) };
    await new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'cainiao-destination', carrier: 'aliexpress', tracking_number: number });
    expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual([
      'aliexpress', country === 'Switzerland' ? 'swiss-post' : 'posti',
    ]);
  });

  it('compares handoff freshness in each carrier’s declared timezone', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValueOnce({ status: 'in_transit', delivery_tracking_number: 'RA123456785CH',
      last_update: '2026-03-04 12:00:00', timezone: 'Asia/Shanghai' })
      .mockResolvedValueOnce({ status: 'out_for_delivery', last_update: '2026-03-04 10:00:00', timezone: 'Europe/Zurich' }) };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'carrier-timezones', carrier: 'aliexpress', tracking_number: 'LP00000000000001' });
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.active_tracking_carrier).toBe('swiss-post');
  });

  it.each([
    ['aliexpress', 'swiss-post'], ['spring-gds', 'posti'], ['sunyou', 'usps'], ['la-poste', 'ups'],
  ])('switches %s to its reported %s partner after confirmation', async (carrier, partner) => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValueOnce({ status: 'in_transit', delivery_carrier: partner })
      .mockResolvedValue({ status: 'out_for_delivery', expected_delivery: '2026-09-10' }) };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'postal-handoff', carrier, tracking_number: 'LX123456785NL' });
    expect(adapter.fetch.mock.calls.map((call) => call.slice(0, 2))).toEqual([
      [carrier, 'LX123456785NL'], [partner, 'LX123456785NL'],
    ]);
    expect(client.updatePackage.mock.calls.at(-1)?.[1]).toMatchObject({
      current_stage: 'out_for_delivery', expected_delivery: '2026-09-10',
      carrier_data: { original_carrier: carrier, active_tracking_carrier: partner },
    });
  });

  it.each(['la-poste', 'aliexpress', 'spring-gds', 'intl-post'])('keeps %s when the national post has no dated progress', async (carrier) => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit', destination_country: 'FI' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    for (const tracking_number of ['CW123456785FR', 'LX123456785CH']) {
      adapter.fetch.mockClear();
      await service.syncPackage({ id: 'no-partner', carrier, tracking_number });
      expect(adapter.fetch.mock.calls.map((call) => call.slice(0, 2))).toEqual([
        [carrier, tracking_number], ['posti', tracking_number],
      ]);
      expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'ok', current_stage: 'in_transit' });
      expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.active_tracking_carrier).not.toBe('posti');
    }
  });

  it('confirms a PostNL destination lookup through the real adapter, normalization and router', async () => {
    const number = 'LX314159268NL';
    const fetcher = vi.fn<typeof fetch>().mockImplementation(async (url) => Response.json(
      String(url).endsWith('/token') ? { access_token: 'visitor-token' } : { data: { items: [{
        item: number, destination_code: 'CH', events: [{ category: 'Departed',
          datetime_local: '2026-03-04T10:00:00Z', status_description: 'Departed', country_code: 'NL' }],
      }] } },
    ));
    const swiss = vi.fn().mockResolvedValue({ status: 'out_for_delivery', last_update: '2026-03-05T09:00:00Z',
      events: [{ time: '2026-03-05T09:00:00Z', description: 'Out for delivery', stage: 'out_for_delivery' }] });
    const registry = new AdapterRegistry({ factories: {
      'spring-gds': REGISTRY.factories['spring-gds'],
      'swiss-post': () => ({ id: 'swiss-post', steps: ['direct'], track: swiss }),
    }, carriers: { 'spring-gds': 'spring-gds', 'swiss-post': 'swiss-post' } }, {
      fetcher, trawl: null, browserExecutablePath: null, recorder: NOOP_RECORDER, env: {},
    });
    const direct = new CarrierTrackingAdapter(undefined, registry, NOOP_RECORDER);
    const adapter = { fetch: vi.fn(direct.fetch.bind(direct)), fetchUniversal: vi.fn() };
    const client = fakeClient();
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    const parcel = { id: 'postal-destination', carrier: 'spring-gds', tracking_number: number };
    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ updated: 1, errors: 0 });
    expect(fetcher).toHaveBeenCalledTimes(2);
    expect(swiss).toHaveBeenCalledExactlyOnceWith({ number, trackingUrl: null, postcode: null });
    expect(adapter.fetchUniversal).not.toHaveBeenCalled();
    const values = client.updatePackage.mock.calls.at(-1)![1];
    expect(values).toMatchObject({ current_stage: 'out_for_delivery', carrier_data: {
      original_carrier: 'spring-gds', original_tracking_number: number,
      active_tracking_carrier: 'swiss-post', active_tracking_number: number,
    } });
    expect(new Set(client.insertEvents.mock.calls[0][0].map((event: JsonObject) => String(event.provider_event_id).split(':')[0])))
      .toEqual(new Set(['spring-gds', 'swiss-post']));
    adapter.fetch.mockClear();
    await service.syncPackage({ ...parcel, ...values });
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('swiss-post', number, null, null, null);
    expect(fetcher).toHaveBeenCalledTimes(2);
  });

  it.each(['unknown', 'registered', 'undated', 'stale', 'failed', 'wrong_identity', 'terminal_conflict'])(
    'keeps PostNL when a national-post probe is %s', async (state) => {
      const client = fakeClient();
      const number = 'LX123456785NL';
      const origin = carrierResult(state === 'terminal_conflict' ? 'postnl-Delivered' : 'postnl-Departed');
      const delivery: CarrierResult = { status: 'out_for_delivery', last_update: '2026-03-05T10:00:00Z' };
      if (state === 'unknown') delivery.status = 'unknown';
      if (state === 'registered') { delivery.status = 'pending'; delivery.current_stage = 'registered'; }
      if (state === 'undated') delivery.last_update = null;
      if (state === 'stale') delivery.last_update = '2026-03-03T10:00:00Z';
      const adapter = { fetch: vi.fn().mockResolvedValueOnce(origin) };
      if (state === 'failed') adapter.fetch.mockRejectedValueOnce(new Error('Swiss Post unavailable'));
      else if (state === 'wrong_identity') adapter.fetch.mockRejectedValueOnce(new SchemaError('Swiss Post', 'Different shipment'));
      else adapter.fetch.mockResolvedValueOnce(delivery);
      await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
        .syncPackage({ id: 'postal-probe', carrier: 'spring-gds', tracking_number: number });
      expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['spring-gds', 'swiss-post']);
      const values = client.updatePackage.mock.calls.at(-1)![1];
      expect(values).toMatchObject({ sync_status: 'ok', current_stage: state === 'terminal_conflict' ? 'delivered' : 'in_transit' });
      expect(values.carrier_data.active_tracking_carrier).not.toBe('swiss-post');
    },
  );

  it('remembers unsuccessful national-post probes and retries a changed destination immediately', async () => {
    const client = fakeClient();
    let destination = 'CH';
    let now = new Date('2026-03-04T12:00:00Z');
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => carrier === 'spring-gds'
      ? { status: 'in_transit', destination_country: destination, last_update: '2026-03-04T10:00:00Z' }
      : { status: 'unknown' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    let parcel: JsonObject = { id: 'postal-retry', carrier: 'spring-gds', tracking_number: 'LX123456785NL' };
    for (const expected of [['spring-gds', 'swiss-post'], ['spring-gds'], ['spring-gds', 'posti']]) {
      adapter.fetch.mockClear();
      await service.syncPackage(parcel, { trigger: 'scheduled' });
      expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(expected);
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      now = new Date(now.getTime() + 10 * 60_000);
      if (expected.length === 1) destination = 'FI';
    }
  });

  it.each([
    ['answers inconclusively', () => new IndeterminateError('Swiss Post', 'Swiss Post returned an inconclusive response'), null],
    ['answers inconclusively behind a wrapper', () => new Error('lookup failed', { cause: new IndeterminateError('Swiss Post') }), null],
    ['gets a server error', () => new UpstreamHttpError('Swiss Post', 502), 'transport'],
    // The first carrier error in the chain decides: running out of time is not an answer.
    ['runs out of budget after an inconclusive step',
      () => new BudgetExceededError('Swiss Post', 45_000, { cause: new IndeterminateError('Swiss Post') }), 'transport'],
  ] as const)('reports a national-post probe that %s as provider_failed unless its first carrier error is inconclusive', async (_case, failure, category) => {
    const report = vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    try {
      const client = fakeClient();
      const error = failure();
      const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => {
        if (carrier === 'swiss-post') throw error;
        return { status: 'in_transit', destination_country: 'CH', last_update: '2026-03-04T10:00:00Z' };
      }) };
      await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
        .syncPackage({ id: 'postal-miss', carrier: 'spring-gds', tracking_number: 'LX123456785NL' });
      expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['spring-gds', 'swiss-post']);
      expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'ok', current_stage: 'in_transit' });
      if (category) expect(report).toHaveBeenCalledExactlyOnceWith('provider_failed', expect.objectContaining({
        carrier: 'spring-gds', provider: 'swiss-post', category, error,
      }));
      else expect(report).not.toHaveBeenCalledWith('provider_failed', expect.anything());
    } finally { report.mockRestore(); }
  });

  it.each([
    ['answers without scans', new IndeterminateError('Swiss Post'), 'indeterminate', []],
    ['gets a server error', new UpstreamHttpError('Swiss Post', 503), 'maintenance', [['swiss-post', false]]],
  ] as const)('counts a partner that %s to a probe toward its health only for a real failure', async (_case, failure, outcome, partner) => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const client = fakeClient();
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => {
      // What the tracker's recorder reports of each lookup.
      const lookup = { carrier, finalStep: 'direct', durationMs: 1, attempts: 1 };
      if (carrier === 'swiss-post') {
        healthStepRecorder.lookup({ ...lookup, outcome, errorType: failure.name, error: failure });
        throw failure;
      }
      healthStepRecorder.lookup({ ...lookup, outcome: 'ok', errorType: null });
      return { status: 'in_transit', destination_country: 'CH', last_update: '2026-03-04T10:00:00Z' };
    }) };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'postal-probe', carrier: 'spring-gds', tracking_number: 'LX123456785NL' }, { trigger: 'scheduled' });
    expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['spring-gds', 'swiss-post']);
    const samples = client.recordTrackingHealth.mock.lastCall![2] as JsonObject[];
    expect(samples.filter((sample) => sample.kind === 'provider').map((sample) => [sample.subject, sample.healthy]))
      .toEqual([['spring-gds', true], ...partner]);
  });

  it('does not upgrade a saved destination guess into partner evidence during an origin outage', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockRejectedValueOnce(new Error('PostNL unavailable'))
      .mockResolvedValueOnce({ status: 'delivered' }) };
    await expect(new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'postal-outage', carrier: 'spring-gds', tracking_number: 'LX123456785NL',
        current_stage: 'in_transit', carrier_data: { destination_country: 'CH' } }))
      .resolves.toMatchObject({ errors: 1 });
    expect(client.updatePackage.mock.calls.at(-1)![1].current_stage).toBeUndefined();
  });

  it('ignores invalid optional partner hints without dropping a successful origin result', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'delivered', delivery_carrier: 'unknown-operator', destination_country: 123 }) };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'malformed-hint', carrier: 'la-poste', tracking_number: 'CW123456785FR' });
    expect(adapter.fetch).toHaveBeenCalledOnce();
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'ok', current_stage: 'delivered' });
  });

  it('asks an unconfirmed partner again only when the origin moves or the gap has passed', async () => {
    const client = fakeClient();
    let now = new Date('2026-09-10T10:00:00Z');
    let originUpdate = '2026-09-09T10:00:00+02:00';
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => {
      if (carrier === 'swiss-post') throw Object.assign(new Error('Shipment not found'), { status: 404 });
      return { status: 'in_transit', delivery_carrier: 'swiss-post', last_update: originUpdate, timezone: 'Europe/Paris',
        events: [{ time: originUpdate, description: 'In transit', stage: 'in_transit' }] };
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    let parcel: JsonObject = { id: 'postal', carrier: 'la-poste', tracking_number: 'LX123456785FR' };
    const refresh = async (time: string) => {
      now = new Date(`2026-09-10T${time}:00Z`);
      adapter.fetch.mockClear();
      await service.syncPackage(parcel, { trigger: 'scheduled' });
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      return adapter.fetch.mock.calls.map((call) => call[0]);
    };

    expect(await refresh('10:00')).toEqual(['la-poste', 'swiss-post']);
    expect(parcel.carrier_data).toMatchObject({ delivery_probe: { at: '2026-09-10T10:00:00.000Z', origin_update: originUpdate, misses: 1 } });
    // Nothing moved: the two-minute and ten-minute cadences no longer repeat the probe.
    expect(await refresh('10:10')).toEqual(['la-poste']);
    expect(await refresh('10:50')).toEqual(['la-poste']);
    expect(parcel.carrier_data).toMatchObject({ delivery_probe: { at: '2026-09-10T10:00:00.000Z' } });
    // Each miss doubles the gap, from 55 min up to 6 h.
    for (const [time, asked, misses] of [['10:55', true, 2], ['12:40', false, 2], ['12:45', true, 3],
      ['16:20', false, 3], ['16:25', true, 4], ['22:20', false, 4], ['22:25', true, 5]] as const) {
      expect(await refresh(time)).toEqual(asked ? ['la-poste', 'swiss-post'] : ['la-poste']);
      expect(parcel.carrier_data).toMatchObject({ delivery_probe: { misses } });
    }
    // A new origin event is the usual sign of a handover, so it asks at once and starts the gaps over.
    originUpdate = '2026-09-10T12:30:00+02:00';
    expect(await refresh('22:40')).toEqual(['la-poste', 'swiss-post']);
    expect(parcel.carrier_data).toMatchObject({ delivery_probe: { at: '2026-09-10T22:40:00.000Z', origin_update: originUpdate, misses: 1 } });
  });

  it('counts a probe made while the origin post cannot answer toward the gap before the next', async () => {
    const client = fakeClient();
    let now = new Date('2026-09-10T10:00:00Z');
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => {
      if (carrier === 'la-poste') throw Object.assign(new Error('Shipment not found'), { status: 404 });
      throw new ChallengeError('DHL');
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    let parcel: JsonObject = { id: 'origin-down', carrier: 'dhl', tracking_number: 'RR123456785DE', current_stage: 'in_transit',
      carrier_data: { lookup_country_hint: 'FR' } };
    const refresh = async (time: string) => {
      now = new Date(`2026-09-10T${time}:00Z`);
      adapter.fetch.mockClear();
      await service.syncPackage(parcel, { trigger: 'scheduled' });
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      return adapter.fetch.mock.calls.map((call) => call[0]);
    };

    expect(await refresh('10:00')).toEqual(['dhl', 'la-poste']);
    expect(parcel).toMatchObject({ sync_status: 'error', carrier_data: { lookup_country_hint: 'FR',
      delivery_probe: { carrier: 'la-poste', at: '2026-09-10T10:00:00.000Z', misses: 1 } } });
    // While the origin stays down the gap does not grow, and La Poste is asked again once it passes.
    expect(await refresh('10:30')).toEqual(['dhl']);
    expect(await refresh('10:55')).toEqual(['dhl', 'la-poste']);
    expect(parcel.carrier_data).toMatchObject({ delivery_probe: { at: '2026-09-10T10:55:00.000Z' } });
  });

  it('keeps the probe an origin post that cannot answer made when the router defers the check', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: null }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    const adapter = {
      fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => {
        if (carrier === 'la-poste') throw Object.assign(new Error('Shipment not found'), { status: 404 });
        throw new ChallengeError('DHL');
      }),
      fetchUniversal: vi.fn().mockRejectedValue(new NoHistoryError('UPU', 'No history')),
    };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T10:00:00Z'))
      .syncPackage({ id: 'origin-down', carrier: 'dhl', tracking_number: 'RR123456785DE', current_stage: 'in_transit',
        carrier_data: { lookup_country_hint: 'FR', routing: { version: 1, configured_carrier: 'dhl', last_event_at: '2026-09-09T10:00:00.000Z' } } });
    expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['dhl', 'la-poste']);
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data).toMatchObject({ routing: { failures: { dhl: {} } },
      delivery_probe: { carrier: 'la-poste', at: '2026-09-10T10:00:00.000Z' } });
  });

  it('asks a partner that has announced the parcel on every check until its first scan', async () => {
    const client = fakeClient();
    let now = new Date('2026-09-10T16:00:00Z');
    const originUpdate = '2026-09-10T17:19:00+02:00';
    let partner: CarrierResult = { status: 'pending', current_stage: 'registered', last_update: '2026-09-10T17:51:00+02:00', timezone: 'Europe/Zurich',
      events: [{ time: '2026-09-10T17:51:00+02:00', description: 'Will shortly be handed over', stage: 'registered' }] };
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => carrier === 'swiss-post' ? partner
      : { status: 'in_transit', destination_country: 'CH', last_update: originUpdate, timezone: 'Europe/Paris',
        events: [{ time: originUpdate, description: 'Arrived in destination country', stage: 'in_transit' }] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    let parcel: JsonObject = { id: 'announced', carrier: 'la-poste', tracking_number: 'CW123456785FR' };
    const refresh = async () => {
      adapter.fetch.mockClear();
      await service.syncPackage(parcel, { trigger: 'scheduled' });
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      return adapter.fetch.mock.calls.map((call) => call[0]);
    };

    expect(await refresh()).toEqual(['la-poste', 'swiss-post']);
    expect(parcel.carrier_data).toMatchObject({ delivery_probe: { at: '2026-09-10T16:00:00.000Z', announced: true } });
    // The origin has nothing new, and the next check ten minutes later asks all the same.
    now = new Date('2026-09-10T16:10:00Z');
    expect(await refresh()).toEqual(['la-poste', 'swiss-post']);
    expect((parcel.carrier_data as JsonObject).active_tracking_carrier).toBeUndefined();
    partner = { status: 'in_transit', last_update: '2026-09-10T18:06:00+02:00', timezone: 'Europe/Zurich', events: [
      { time: '2026-09-10T18:06:00+02:00', description: 'Shipment was sorted', stage: 'in_transit' }] };
    now = new Date('2026-09-10T16:20:00Z');
    expect(await refresh()).toEqual(['la-poste', 'swiss-post']);
    expect(parcel.carrier_data).toMatchObject({ active_tracking_carrier: 'swiss-post', original_carrier: 'la-poste' });
  });

  it('keeps the long gap for a partner whose record is older than the origin’s news', async () => {
    const client = fakeClient();
    let now = new Date('2026-09-10T16:00:00Z');
    const originUpdate = '2026-09-10T17:19:00+02:00';
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => carrier === 'swiss-post'
      ? { status: 'pending', current_stage: 'registered', last_update: '2026-09-08T09:00:00+02:00', timezone: 'Europe/Zurich',
        events: [{ time: '2026-09-08T09:00:00+02:00', description: 'Data received', stage: 'registered' }] }
      : { status: 'in_transit', destination_country: 'CH', last_update: originUpdate, timezone: 'Europe/Paris',
        events: [{ time: originUpdate, description: 'Arrived in destination country', stage: 'in_transit' }] }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    let parcel: JsonObject = { id: 'stale-notice', carrier: 'la-poste', tracking_number: 'CW123456785FR' };
    for (const expected of [['la-poste', 'swiss-post'], ['la-poste']]) {
      adapter.fetch.mockClear();
      await service.syncPackage(parcel, { trigger: 'scheduled' });
      expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(expected);
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      expect((parcel.carrier_data as JsonObject).delivery_probe).not.toHaveProperty('announced');
      now = new Date(now.getTime() + 10 * 60_000);
    }
  });

  it('hands over to a partner relaying the origin’s newest scan to the minute', async () => {
    const origin: CarrierResult = { status: 'in_transit', destination_country: 'PT', last_update: '2026-10-20T05:10:08+01:00',
      timezone: 'Europe/London', events: [{ time: '2026-10-20T05:10:08+01:00', description: 'Item Leaving the UK', stage: 'in_transit' }] };
    for (const [relayed, partner] of [['2026-10-20T05:10:00+01:00', 'ctt'], ['2026-10-20T05:09:00+01:00', undefined]] as const) {
      const client = fakeClient();
      const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => carrier === 'ctt'
        ? { status: 'in_transit', last_update: relayed, timezone: 'Europe/Lisbon',
          events: [{ time: relayed, description: 'Forwarded to the destination country', stage: 'in_transit' }] }
        : origin) };
      await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-10-20T08:00:00Z'))
        .syncPackage({ id: 'relayed-departure', carrier: 'royal-mail', tracking_number: 'RA123456785GB' }, { trigger: 'scheduled' });
      expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['royal-mail', 'ctt']);
      expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.active_tracking_carrier).toBe(partner);
    }
  });

  it.each([
    // A partner can leave its newest scans unstaged, with no summary: the scans before them still show it has the parcel.
    ['holds unstaged scans after a staged one', [['17:30', 'Admitted at the sorting centre', 'in_transit'], ['17:45', 'Code X12'], ['17:51', 'Status 63']], 'swiss-post', false],
    ['holds scans only their wording stages', [['17:45', 'Leaving the sorting centre'], ['17:51', 'Code X12']], 'swiss-post', false],
    // Not a scan: the parcel announced to it, read or not, is the next scan's to confirm.
    ['holds only a pre-advice', [['17:51', 'Pre-advice', 'registered']], undefined, true],
    ['holds a pre-advice and a newer scan no rule reads', [['17:40', 'Pre-advice', 'registered'], ['17:51', 'Code X12']], undefined, true],
    // Older than what the origin already says: the freshness rule still holds for unstaged answers.
    ['holds scans older than the origin’s news', [['2026-09-09T09:00', 'Admitted at the sorting centre', 'in_transit'], ['2026-09-09T10:00', 'Code X12']], undefined, false],
    ['holds undated scans', [[null, 'Leaving the sorting centre']], undefined, false],
  ] as const)('hands over to a partner without a summary stage only when it %s', async (_case, scans, active, announced) => {
    const client = fakeClient();
    const originUpdate = '2026-09-10T17:19:00+02:00';
    const events = scans.map(([time, description, stage]) => ({
      ...(time === null ? {} : { time: `${time.length === 5 ? `2026-09-10T${time}` : time}:00+02:00` }), description, ...(stage ? { stage } : {}),
    }));
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => carrier === 'swiss-post'
      ? { status: 'unknown', timezone: 'Europe/Zurich', events }
      : { status: 'in_transit', destination_country: 'CH', last_update: originUpdate, timezone: 'Europe/Paris',
        events: [{ time: originUpdate, description: 'Arrived in destination country', stage: 'in_transit' }] }) };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T16:00:00Z'))
      .syncPackage({ id: 'unstaged-partner', carrier: 'la-poste', tracking_number: 'CW123456785FR' }, { trigger: 'scheduled' });
    expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['la-poste', 'swiss-post']);
    const data = client.updatePackage.mock.calls.at(-1)![1].carrier_data;
    expect(data.active_tracking_carrier).toBe(active);
    if (!active) expect(Boolean(data.delivery_probe?.announced)).toBe(announced);
  });

  it('asks an unconfirmed partner again on a refresh by hand, a few minutes apart', async () => {
    const client = fakeClient();
    let now = new Date('2026-09-10T10:00:00Z');
    const originUpdate = '2026-09-09T10:00:00+02:00';
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => {
      if (carrier === 'swiss-post') throw Object.assign(new Error('Shipment not found'), { status: 404 });
      return { status: 'in_transit', delivery_carrier: 'swiss-post', last_update: originUpdate, timezone: 'Europe/Paris',
        events: [{ time: originUpdate, description: 'In transit', stage: 'in_transit' }] };
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    let parcel: JsonObject = { id: 'by-hand', carrier: 'la-poste', tracking_number: 'LX123456785FR' };
    const refresh = async (trigger: 'package' | 'scheduled') => {
      adapter.fetch.mockClear();
      await service.syncPackage(parcel, { trigger });
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      return adapter.fetch.mock.calls.map((call) => call[0]);
    };

    expect(await refresh('scheduled')).toEqual(['la-poste', 'swiss-post']);
    // Taps in a row ask the partner once.
    now = new Date('2026-09-10T10:02:00Z');
    expect(await refresh('package')).toEqual(['la-poste']);
    now = new Date('2026-09-10T10:41:00Z');
    expect(await refresh('package')).toEqual(['la-poste', 'swiss-post']);
    expect(parcel.carrier_data).toMatchObject({ delivery_probe: { at: '2026-09-10T10:41:00.000Z', misses: 2 } });
    // The scheduled checks go on counting the gap, doubled by that miss, from that ask.
    now = new Date('2026-09-10T11:40:00Z');
    expect(await refresh('scheduled')).toEqual(['la-poste']);
    now = new Date('2026-09-10T12:31:00Z');
    expect(await refresh('scheduled')).toEqual(['la-poste', 'swiss-post']);
  });

  it('rechecks immediately when the partner or its local reference changes', async () => {
    const client = fakeClient();
    let now = new Date('2026-09-10T10:00:00Z');
    let partner = 'swiss-post';
    let reference = 'LOCAL12345';
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => {
      if (carrier === partner) throw Object.assign(new Error('Shipment not found'), { status: 404 });
      return { status: 'in_transit', delivery_carrier: partner, delivery_tracking_number: reference, last_update: '2026-09-09T10:00:00+02:00',
        events: [{ time: '2026-09-09T10:00:00+02:00', description: 'Arrived in destination country', stage: 'in_transit' }] };
    }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    let parcel: JsonObject = { id: 'named', carrier: 'dhl', tracking_number: 'LF123456785DE' };
    for (const [minute, nextPartner, nextReference] of [['00', 'swiss-post', 'LOCAL12345'], ['10', 'posti', 'LOCAL12345'], ['20', 'posti', 'LOCAL67890']]) {
      partner = nextPartner;
      reference = nextReference;
      now = new Date(`2026-09-10T10:${minute}:00Z`);
      adapter.fetch.mockClear();
      await service.syncPackage(parcel);
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['dhl', partner]);
      expect(adapter.fetch).toHaveBeenLastCalledWith(partner, reference, null, null, null);
    }
  });

  it('reports an origin failure once: here only when the local delivery hides it from the router', async () => {
    const report = vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    try {
      const outage = new UpstreamHttpError('La Poste', 403);
      // Swiss Post knows nothing either: the origin error is rethrown for the caller to report.
      const silent = { fetch: vi.fn().mockRejectedValueOnce(outage)
        .mockRejectedValueOnce(Object.assign(new Error('Shipment not found'), { status: 404 })) };
      await new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient, silent, null)
        .syncPackage({ id: 'blocked', carrier: 'la-poste', tracking_number: 'LX123456785FR', current_stage: 'in_transit',
          carrier_data: { delivery_carrier: 'swiss-post' } });
      expect(report).not.toHaveBeenCalledWith('provider_failed', expect.anything());

      const hidden = { fetch: vi.fn().mockRejectedValueOnce(outage).mockResolvedValueOnce({ status: 'out_for_delivery' }) };
      await new TrackingSyncService(fakeClient() as unknown as SupabaseServiceClient, hidden, null)
        .syncPackage({ id: 'hidden', carrier: 'la-poste', tracking_number: 'LX123456785FR', current_stage: 'in_transit',
          carrier_data: { delivery_carrier: 'swiss-post' } });
      expect(report).toHaveBeenCalledExactlyOnceWith('provider_failed', expect.objectContaining({
        carrier: 'la-poste', provider: 'la-poste', errorClass: 'UpstreamHttpError', error: outage,
      }));
    } finally { report.mockRestore(); }
  });

  it('can confirm a previously reported partner while the international tracker is unavailable', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockRejectedValueOnce(new Error('Cainiao unavailable'))
      .mockResolvedValueOnce({ status: 'out_for_delivery' }) };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'postal-outage', carrier: 'aliexpress', tracking_number: 'LX123456785NL',
        carrier_data: { delivery_carrier: 'swiss-post' } });
    expect(client.updatePackage.mock.calls.at(-1)?.[1]).toMatchObject({
      sync_status: 'ok', carrier_data: { active_tracking_carrier: 'swiss-post' },
    });
  });

  it('asks a saved partner after the first gap while the international tracker is unavailable', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockRejectedValueOnce(new Error('Cainiao unavailable'))
      .mockResolvedValueOnce({ status: 'out_for_delivery' }) };
    // Four misses would wait 6 h with the origin answering.
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T11:00:00Z'))
      .syncPackage({ id: 'postal-outage', carrier: 'aliexpress', tracking_number: 'LX123456785NL', carrier_data: {
        delivery_carrier: 'swiss-post', delivery_probe: { carrier: 'swiss-post', at: '2026-09-10T10:00:00.000Z', origin_update: null, misses: 4 } } },
      { trigger: 'scheduled' });
    expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['aliexpress', 'swiss-post']);
    expect(client.updatePackage.mock.calls.at(-1)?.[1]).toMatchObject({ carrier_data: { active_tracking_carrier: 'swiss-post' } });
  });

  it('persists a different domestic number and uses it for later refreshes', async () => {
    const parcel = { id: 'gls-handoff', carrier: 'gls-de', tracking_number: '123456789011' };
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValueOnce({ status: 'in_transit', delivery_carrier: 'swiss-post',
      delivery_tracking_number: '12345678901', canonical_tracking_number: '12345678901' })
      .mockResolvedValue({ status: 'out_for_delivery', canonical_tracking_number: '990000000000000001' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null);
    await service.syncPackage(parcel);
    expect(adapter.fetch.mock.calls[1]).toEqual(['swiss-post', '12345678901', null, null, null]);
    const values = client.updatePackage.mock.calls.at(-1)?.[1];
    expect(values.carrier_data).toMatchObject({ active_tracking_number: '990000000000000001',
      original_tracking_number: '123456789011', original_canonical_tracking_number: '12345678901' });
    adapter.fetch.mockClear();
    await service.syncPackage({ ...parcel, ...values });
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('swiss-post', '990000000000000001', null, null, null);
    expect(client.updatePackage.mock.calls.at(-1)?.[1].carrier_data).toMatchObject({ active_tracking_number: '990000000000000001', original_canonical_tracking_number: '12345678901' });
  });

  it.each(['missing', 'registered', 'error'])('keeps DHL active when Swiss Post is %s', async (state) => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn().mockResolvedValueOnce({ status: 'in_transit', delivery_carrier: 'swiss-post' }) };
    if (state === 'error') adapter.fetch.mockRejectedValueOnce(new Error('Unavailable'));
    else adapter.fetch.mockResolvedValueOnce(state === 'registered'
      ? { status: 'pending', current_stage: 'registered' } : { status: 'unknown', events: [] });
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null)
      .syncPackage({ id: 'waiting-handoff', carrier: 'dhl', tracking_number: 'LF123456785DE' });
    const values = client.updatePackage.mock.calls.at(-1)?.[1];
    expect(values.current_stage).toBe('in_transit');
    expect(values.carrier_data.active_tracking_carrier).toBeUndefined();
    expect(values.carrier_data.original_carrier).toBeUndefined();
  });

  it.each(['gls-de', 'gls-ch', 'gls-fr'])(
    'checks %s hourly and waits four hours after failures', (carrier) => {
      const parcel = { carrier, last_synced_at: '2026-09-09T10:00:00Z', sync_status: 'ok' };
      expect(isTrackingSyncDue({ carrier }, new Date('2026-09-09T10:00:00Z'))).toBe(true);
      expect(isTrackingSyncDue(parcel, new Date('2026-09-09T10:59:59.999Z'))).toBe(false);
      expect(isTrackingSyncDue(parcel, new Date('2026-09-09T11:00:00Z'))).toBe(true);
      expect(isTrackingSyncDue({ ...parcel, sync_status: 'error' }, new Date('2026-09-09T13:59:59Z'))).toBe(false);
      expect(isTrackingSyncDue({ ...parcel, sync_status: 'error' }, new Date('2026-09-09T14:00:00Z'))).toBe(true);
      expect(isTrackingSyncDue({ ...parcel, last_synced_at: 'invalid' }, new Date())).toBe(true);
    },
  );

  it.each(['ok', 'error'])('checks PostNL every thirty minutes after %s checks', async (syncStatus) => {
    const parcel = {
      id: 'postnl', carrier: 'spring-gds', tracking_number: 'LX123456785NL',
      current_stage: 'in_transit',
      last_synced_at: '2026-09-09T10:00:00Z', sync_status: syncStatus,
    };
    const client = fakeClient([parcel]);
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) };
    let now = new Date('2026-09-09T10:02:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      adapter, null, () => now);
    await expect(service.sync()).resolves.toMatchObject({ checked: 0 });
    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ checked: 1, unchanged: 1 });
    now = new Date('2026-09-09T10:10:00Z');
    await expect(service.sync()).resolves.toMatchObject({ checked: 0 });
    now = new Date('2026-09-09T10:30:00Z');
    await expect(service.sync()).resolves.toMatchObject({ checked: 1, unchanged: 1 });
    expect(adapter.fetch.mock.calls.filter(([carrier]) => carrier === 'spring-gds')).toHaveLength(2);
  });

  it('filters cooldowns before the per-owner quota without delaying other carriers', async () => {
    const parcels = Array.from({ length: 5 }, (_, index) => ({
      id: `cooling-${index}`, user_id: 'owner', carrier: 'gls-de',
      last_synced_at: '2026-09-09T10:00:00Z', sync_status: 'error',
    }));
    const client = fakeClient([...parcels, {
      id: 'due', user_id: 'owner', carrier: 'dpd', tracking_number: 'TEST1234',
      last_synced_at: '2026-09-09T10:00:00Z',
    }]);
    const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      adapter, null, () => new Date('2026-09-09T10:10:00Z'));
    await expect(service.sync()).resolves.toMatchObject({ checked: 1, updated: 1 });
    expect(adapter.fetch).toHaveBeenCalledExactlyOnceWith('dpd', 'TEST1234', null, null, null);
    expect(client.startSyncAttempt).toHaveBeenCalledOnce();
  });

  it('uses persisted failures across service restarts and manual refreshes', async () => {
    const parcel: JsonObject = {
      id: 'gls-retry', carrier: 'gls-de', tracking_number: 'TEST1234', current_stage: 'in_transit',
    };
    const client = fakeClient();
    client.updatePackage.mockImplementation(async (_id, values) => { Object.assign(parcel, values); });
    const adapter = { fetch: vi.fn().mockRejectedValue(new Error('Provider unavailable')) };
    let now = new Date('2026-09-09T10:00:00Z');
    const service = () => new TrackingSyncService(client as unknown as SupabaseServiceClient,
      adapter, null, () => now);
    await expect(service().syncPackage(parcel)).resolves.toMatchObject({ checked: 1, errors: 1 });
    now = new Date('2026-09-09T13:59:59Z');
    await expect(service().syncPackage(parcel)).resolves.toMatchObject({ checked: 0, errors: 0 });
    expect(adapter.fetch).toHaveBeenCalledOnce();
    now = new Date('2026-09-09T14:00:00Z');
    adapter.fetch.mockResolvedValue({ status: 'in_transit' });
    await expect(service().syncPackage(parcel)).resolves.toMatchObject({ checked: 1, unchanged: 1 });
    now = new Date('2026-09-09T14:59:59Z');
    await expect(service().syncPackage(parcel)).resolves.toMatchObject({ checked: 0 });
    now = new Date('2026-09-09T15:00:00Z');
    await expect(service().syncPackage(parcel)).resolves.toMatchObject({ checked: 1, unchanged: 1 });
    expect(adapter.fetch).toHaveBeenCalledTimes(3);
  });

  it("keeps the id of its own carrier's thin answer in a scheduled check's health, the same for the same answer", async () => {
    const parcel: JsonObject = { id: 'thin-own', carrier: 'dhl', tracking_number: 'TEST1234', current_stage: 'in_transit',
      last_status_text: 'In transit', carrier_data: { status: 'in_transit', current_stage: 'in_transit' } };
    const client = fakeClient();
    const label = { stage: 'registered', description: 'Label created', time: '2026-09-08T10:00:00Z' };
    const adapter = { fetch: vi.fn() };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter);
    const thinAnswer = async (answer: CarrierResult) => {
      adapter.fetch.mockResolvedValue(answer);
      await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ errors: 1 });
      const refresh = (client.recordTrackingHealth.mock.lastCall![2] as JsonObject[]).find((sample) => sample.kind === 'refresh')!;
      expect(refresh).toMatchObject({ healthy: false, details: { error_type: 'ProgressDisappeared' } });
      return (refresh.details as JsonObject).thin_answer;
    };
    const first = await thinAnswer({ status: 'pending', current_stage: 'registered', events: [label] });
    expect(first).toMatch(/^[0-9a-f]{16}$/);
    await expect(thinAnswer({ status: 'pending', current_stage: 'registered', events: [label] })).resolves.toBe(first);
    await expect(thinAnswer({ status: 'pending', current_stage: 'registered', events: [label,
      { stage: 'registered', description: 'Data received', time: '2026-09-08T11:00:00Z' }] })).resolves.not.toBe(first);
    expect(JSON.stringify(client.recordTrackingHealth.mock.calls)).not.toContain('TEST1234');
    // The provider the saved summary came from answering without the progress: not the carrier's own answer.
    parcel.carrier_data = { status: 'in_transit', current_stage: 'in_transit', tracking_provider: 'ParcelsApp' };
    await expect(thinAnswer({ status: 'pending', current_stage: 'registered', tracking_provider: 'ParcelsApp', events: [label] })).resolves.toBeUndefined();
  });

  describe('its own carrier listing fewer of the stored scans', () => {
    // A carrier that keeps a parcel's label scans and drops its later ones. Its wording ("arrived at the
    // pickup point", "left the operations center") gives no stage of its own: the adapter's does.
    const label = { stage: 'registered', description: 'Electronic data received', time: '2026-10-04T09:16:20Z' };
    const relabel = { stage: 'registered', description: 'Electronic data received', time: '2026-10-20T03:23:29Z' };
    const accepted = { stage: 'accepted', description: '到达收货点', time: '2026-10-20T03:28:11Z' };
    const departed = { stage: 'in_transit', description: '货物离开操作中心', time: '2026-10-20T03:30:36Z' };
    const answer = (...events: CarrierResult['events'] & object): CarrierResult => ({
      status: events[0]!.stage as CarrierResult['status'], current_stage: events[0]!.stage, last_status_text: events[0]!.description,
      last_update: events[0]!.time, events });
    const tracked = async (carrierData: JsonObject = {}, first = answer(departed, accepted, relabel, label)) => {
      const store = eventStore();
      const adapter = { fetch: vi.fn() };
      let now = new Date('2026-10-21T12:00:00Z');
      const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null, () => now);
      let parcel: JsonObject = { id: 'fewer-scans', carrier: 'dhl', tracking_number: 'TEST1234', current_stage: 'pending',
        carrier_data: carrierData };
      const check = async (result: CarrierResult) => {
        now = new Date(now.getTime() + 60 * 60 * 1_000);
        adapter.fetch.mockResolvedValue(structuredClone(result));
        const counts = await service.syncPackage({ ...parcel, [STORED_EVENT_IDENTITIES]: store.identities() }, { trigger: 'scheduled' });
        parcel = { ...parcel, ...store.client.updatePackage.mock.calls.at(-1)![1] };
        const refresh = (store.client.recordTrackingHealth.mock.lastCall![2] as JsonObject[]).find((sample) => sample.kind === 'refresh');
        return { counts, parcel, refresh, attempt: store.client.completeSyncAttempt.mock.lastCall![1] as JsonObject };
      };
      await check(first);
      return { store, check, save: (values: JsonObject) => { parcel = { ...parcel, ...values }; } };
    };

    it.each([
      ['its label scans', [relabel, label]],
      ['a label scan the rows lack, older than the progress', [{ ...relabel, time: '2026-10-20T03:23:30Z' }, relabel]],
    ])('keeps the stored progress after %s, and the check answered', async (_case, events) => {
      const { store, check } = await tracked();
      const rows = structuredClone([...store.rows.values()]);
      const checked = await check(answer(...events));
      expect(checked.counts).toMatchObject({ unchanged: 1, errors: 0 });
      // The first such answer is a review signal, never an error.
      expect(checked.attempt).toMatchObject({ outcome: 'unchanged', anomaly_codes: ['scans_withdrawn'], error_type: null });
      expect(checked.parcel).toMatchObject({ current_stage: 'in_transit', last_status_text: departed.description,
        sync_status: 'ok', sync_error: null, carrier_data: { scans_withdrawn: { dhl: '2026-10-21T14:00:00.000Z' } } });
      expect(checked.refresh).toMatchObject({ healthy: true });
      expect([...store.rows.values()]).toEqual(rows);
    });

    it('signals the first such answer of a parcel only, even after the carrier showed every scan again', async () => {
      const { check } = await tracked();
      await check(answer(relabel, label));
      expect((await check(answer(relabel, label))).attempt).toMatchObject({ outcome: 'unchanged', anomaly_codes: [] });
      const full = await check(answer(departed, accepted, relabel, label));
      expect(full.parcel).toMatchObject({ carrier_data: { scans_withdrawn: { dhl: '2026-10-21T14:00:00.000Z' } } });
      const again = await check(answer(relabel, label));
      expect(again.attempt).toMatchObject({ outcome: 'unchanged', anomaly_codes: [] });
      expect(again.refresh).toMatchObject({ healthy: true });
    });

    it('counts a signal stored before it was kept per source as its own carrier’s', async () => {
      const { check } = await tracked({ scans_withdrawn_at: '2026-10-20T12:00:00.000Z' });
      const checked = await check(answer(relabel, label));
      expect(checked.attempt).toMatchObject({ outcome: 'unchanged', anomaly_codes: [] });
      expect(checked.parcel).toMatchObject({ carrier_data: { scans_withdrawn_at: '2026-10-20T12:00:00.000Z' } });
    });

    it.each([
      ['a newer scan in an earlier stage', [{ stage: 'registered', description: 'Label printed again', time: '2026-10-21T08:00:00Z' }, relabel, label]],
      ['a stored scan read in an earlier stage', [{ ...accepted, stage: 'registered' }, relabel, label]],
    ])('still finds the progress gone after %s', async (_case, events) => {
      const { check } = await tracked();
      const checked = await check(answer(...events));
      expect(checked.counts).toMatchObject({ errors: 1 });
      expect(checked.attempt).toMatchObject({ outcome: 'error', anomaly_codes: ['progress_disappeared'], error_type: 'ProgressDisappeared' });
      expect(checked.parcel).toMatchObject({ current_stage: 'in_transit', sync_status: 'error' });
    });

    // A carrier that only ever lists the label, and a universal provider's copies of an origin leg it never shows.
    const providerOnly = async () => {
      const { store, check, save } = await tracked({}, answer(relabel, label));
      for (const [id, time, description] of [['unknown:inbound', '2026-10-20T08:00:00Z', 'Inbound at sorting center'],
        ['unknown:outbound', '2026-10-20T20:00:00Z', 'Outbound from sorting center']]) {
        store.rows.set(id!, { provider_event_id: id, occurred_at: time, stage: 'in_transit', description, location: 'Example Town',
          raw_data: { time, stage: 'in_transit', description, universal_provider: 'ParcelsApp' } });
      }
      save({ current_stage: 'in_transit', last_status_text: 'Outbound from sorting center' });
      return { store, check };
    };

    it('keeps progress only another source\'s rows show, without a signal or an error', async () => {
      const { store, check } = await providerOnly();
      const rows = structuredClone([...store.rows.values()]);
      const checked = await check(answer(relabel, label));
      expect(checked.counts).toMatchObject({ unchanged: 1, errors: 0 });
      // None of the carrier's own scans is missing: nothing was withdrawn.
      expect(checked.attempt).toMatchObject({ outcome: 'unchanged', anomaly_codes: [], error_type: null });
      expect(checked.parcel).toMatchObject({ current_stage: 'in_transit', last_status_text: 'Outbound from sorting center',
        sync_status: 'ok', sync_error: null });
      expect(checked.parcel.carrier_data).not.toHaveProperty('scans_withdrawn');
      expect(checked.refresh).toMatchObject({ healthy: true });
      expect([...store.rows.values()]).toEqual(rows);
    });

    // A destination post's pre-advice, dated after the origin's scans in transit.
    it('keeps that progress after a newer label, without a signal or an error', async () => {
      const { store, check } = await providerOnly();
      const rows = structuredClone([...store.rows.values()]);
      const checked = await check(answer({ stage: 'registered', description: 'Label printed again', time: '2026-10-21T08:00:00Z' }, relabel, label));
      expect(checked.counts).toMatchObject({ unchanged: 1, errors: 0 });
      expect(checked.attempt).toMatchObject({ outcome: 'unchanged', anomaly_codes: [], error_type: null });
      expect(checked.parcel).toMatchObject({ current_stage: 'in_transit', sync_status: 'ok', sync_error: null });
      expect([...store.rows.values()]).toEqual(rows);
    });

    it('still finds that progress gone after a newer scan in no stage', async () => {
      const { check } = await providerOnly();
      const checked = await check(answer({ stage: 'pending', description: 'Status 42', time: '2026-10-21T08:00:00Z' }, relabel, label));
      expect(checked.attempt).toMatchObject({ outcome: 'error', anomaly_codes: ['progress_disappeared'], error_type: 'ProgressDisappeared' });
      expect(checked.parcel).toMatchObject({ current_stage: 'in_transit', sync_status: 'error' });
    });
  });

  it.each(['unknown', 'pending'] as const)(
    'preserves DHL progress and delivery details when the provider returns empty %s data', async (status) => {
      const parcel: JsonObject = {
        id: 'dhl-progress', carrier: 'dhl', tracking_number: 'TEST1234', current_stage: 'customs',
        last_status_text: 'At customs', expected_delivery: '2026-09-10',
        carrier_data: { status: 'in_transit', current_stage: 'customs' },
      };
      const client = fakeClient();
      const adapter = { fetch: vi.fn().mockResolvedValue({
        status, last_status_text: 'No tracking information yet', events: status === 'pending'
          ? [{ stage: 'pending', description: 'Pending', time: '2026-09-09T14:00:00Z' }] : [],
      }) };
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter);
      await expect(service.syncPackage(parcel)).resolves.toMatchObject({ errors: 1, waiting: 0 });
      const saved = client.updatePackage.mock.calls.at(-1)![1];
      expect(saved).toEqual({
        last_synced_at: expect.any(String), sync_status: 'error',
        sync_error: 'The carrier temporarily returned no tracking progress. Previous tracking details have been kept.',
      });
      expect(client.insertEvents).not.toHaveBeenCalled();
      // No exception was raised: the check still names its error.
      expect(client.completeSyncAttempt).toHaveBeenCalledWith(expect.any(String), expect.objectContaining({
        outcome: 'error', anomaly_codes: ['progress_disappeared'], error_type: 'ProgressDisappeared' }), expect.any(Array));
      adapter.fetch.mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit',
        last_status_text: 'Departed customs', events: [] });
      await expect(service.syncPackage(parcel)).resolves.toMatchObject({ updated: 1, errors: 0 });
      expect(client.updatePackage).toHaveBeenLastCalledWith(parcel.id, expect.objectContaining({
        sync_status: 'ok', sync_error: null, current_stage: 'in_transit', last_status_text: 'Departed customs',
      }));
    },
  );

  // What a provider that only knows the label answers: one scan, no progress.
  const withoutProgress = { status: 'pending', last_status_text: 'Pending', last_update: '2026-09-10T11:30:00Z',
    events: [{ stage: 'pending', description: 'Pending', time: '2026-09-10T11:30:00Z' }] };
  // The same from a provider that names no stage, in wording no rule maps: read alone, the scan is movement.
  const unreadLabel = { ...withoutProgress, last_status_text: 'Noted at the fixture desk',
    events: [{ description: 'Noted at the fixture desk', time: '2026-09-10T11:30:00Z' }] };
  const leasedClient = () => ({ ...fakeClient(),
    acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
    finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
  });

  const unavailable = () => vi.fn().mockRejectedValue(new IndeterminateError('DPD', 'DPD is unavailable', { status: 503 }));
  it.each([
    ['answers with nothing', 'knows less', () => vi.fn().mockResolvedValue({ status: 'unknown', events: [] }), 'not_found', withoutProgress, '2026-09-10T10:00:00.000Z'],
    ['is unavailable', 'knows less', unavailable, 'transport', withoutProgress, undefined],
    ['is unavailable', 'names no stage in wording no rule maps', unavailable, 'transport', unreadLabel, '2026-09-10T10:00:00.000Z'],
  ] as const)('keeps what is saved without an error when the carrier %s and a fallback %s', async (_carrier, _fallback, fetch, kind, answer, watermark) => {
    const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
    const capture = anomalyLogs(logged, 'progress_disappeared');
    const client = leasedClient();
    const adapter = { fetch: fetch(), fetchUniversal: vi.fn().mockResolvedValue(answer) };
    const parcel = {
      id: 'thin-fallback', carrier: 'dpd', tracking_number: '06080000000002', current_stage: 'registered',
      last_status_text: 'Order created',
      carrier_data: { status: 'pending', current_stage: 'registered', routing: { version: 1, configured_carrier: 'dpd',
        confirmed_carrier: 'dpd', confirmed_number: '06080000000002', failures: {}, probe_cursor: 0, discovery_cursor: 0,
        ...(watermark ? { last_event_at: watermark } : {}) } },
    };
    let now = new Date('2026-09-10T12:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: 1, updated: 0, errors: 0 });
    expect(adapter.fetchUniversal).toHaveBeenCalledOnce();
    const provider = adapter.fetchUniversal.mock.calls[0][0];
    // Only the routing state is saved: the summary, its source and the timeline stay.
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    expect(saved).toEqual({
      last_synced_at: '2026-09-10T12:00:00.000Z', sync_status: 'ok', sync_error: null,
      carrier_data: { status: 'pending', current_stage: 'registered', routing: expect.objectContaining({
        preferred_provider: provider, last_success_at: '2026-09-10T12:00:00.000Z', failures: { dpd: expect.objectContaining({ kind }) },
      }) },
    });
    expect(saved.carrier_data.routing.last_event_at).toBe(watermark);
    expect(client.insertEvents).not.toHaveBeenCalled();
    expect(client.deleteEventsByDescriptions).not.toHaveBeenCalled();
    expect(client.completeSyncAttempt).toHaveBeenCalledWith(expect.any(String),
      expect.objectContaining({ outcome: 'waiting', anomaly_codes: ['fallback_without_progress'], error_type: null }),
      expect.arrayContaining([expect.objectContaining({ step: 'complete', details: expect.objectContaining({
        support_lookup_number: '06080000000002', support_direct_progress: false,
      }) })]));
    expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(expect.any(String), 'thin-fallback',
      expect.arrayContaining([expect.objectContaining({ kind: 'refresh', healthy: true })]));
    expect(capture()).toEqual([]);
    // The scan that was kept out is no freshness watermark: the carrier's next reply, a scan before it, is not older.
    adapter.fetch.mockResolvedValue({ status: 'in_transit', last_status_text: 'In transit', last_update: '2026-09-10T11:00:00Z',
      events: [{ stage: 'in_transit', description: 'In transit', time: '2026-09-10T11:00:00Z' }] });
    now = new Date('2026-09-10T14:00:00Z');
    await expect(service.syncPackage({ ...parcel, ...saved }, { trigger: 'scheduled' })).resolves.toMatchObject({ updated: 1 });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ current_stage: 'in_transit', last_status_text: 'In transit' });
  });

  it.each([
    ['the provider a saved summary came from', false],
    ['another provider, for a carrier without an adapter of its own,', true],
  ] as const)('reports two consecutive thin checks from %s and resets on recovery', async (_label, another) => {
    const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
    const capture = anomalyLogs(logged, 'progress_disappeared');
    const client = leasedClient();
    const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn().mockResolvedValueOnce({ status: 'in_transit',
      current_stage: 'in_transit', last_status_text: 'In transit', last_update: '2026-09-10T11:00:00Z', events: [] }) };
    const parcel = { id: 'no-carrier-to-ask', carrier: 'unknown', tracking_number: 'TEST1234', current_stage: 'pending' };
    let now = new Date('2026-09-10T12:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ updated: 1 });
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    const source = saved.carrier_data.tracking_provider;
    // In the second case that provider fails, and the next one answers.
    adapter.fetchUniversal.mockImplementation(async (provider: string) => {
      if (another && provider === source) throw new IndeterminateError(provider, `${provider} is unavailable`, { status: 503 });
      return withoutProgress;
    });
    now = new Date('2026-09-10T13:00:00Z');
    await expect(service.syncPackage({ ...parcel, ...saved })).resolves.toMatchObject({ errors: 0, waiting: 1 });
    expect(adapter.fetchUniversal.mock.calls.map(([provider]) => provider)).toEqual([source, source, 'Ship24', '17TRACK']);
    const firstThin = client.updatePackage.mock.calls.at(-1)![1];
    expect(firstThin).toMatchObject({ sync_status: 'ok', sync_error: null, carrier_data: {
      tracking_provider: source, routing: { preferred_provider: source, consecutive_failures: 1,
        last_success_at: '2026-09-10T12:00:00.000Z',
        last_event_at: '2026-09-10T11:00:00.000Z' },
    } });
    expect(firstThin.current_stage).toBeUndefined();
    expect(capture()).toEqual([]);
    now = new Date('2026-09-10T14:00:00Z');
    await expect(service.syncPackage({ ...parcel, ...saved, ...firstThin })).resolves.toMatchObject({ errors: 1, waiting: 0 });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'error',
      sync_error: 'The carrier temporarily returned no tracking progress. Previous tracking details have been kept.',
      carrier_data: { tracking_provider: source, routing: { preferred_provider: source, consecutive_failures: 2,
        last_success_at: '2026-09-10T12:00:00.000Z',
        last_event_at: '2026-09-10T11:00:00.000Z' } },
    });
    const secondThin = client.updatePackage.mock.calls.at(-1)![1];
    expect(secondThin.current_stage).toBeUndefined();
    expect(client.completeSyncAttempt).toHaveBeenLastCalledWith(expect.any(String),
      expect.objectContaining({ outcome: 'error', anomaly_codes: ['progress_disappeared'] }), expect.any(Array));
    expect(capture()).toEqual([expect.objectContaining({ carrier: 'unknown', anomaly_code: 'progress_disappeared' })]);
    adapter.fetchUniversal.mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit', last_status_text: 'In transit',
      last_update: '2026-09-10T11:00:00Z', events: [] });
    now = new Date('2026-09-10T15:00:00Z');
    await expect(service.syncPackage({ ...parcel, ...saved, ...secondThin })).resolves.toMatchObject({ updated: 1, errors: 0 });
    const recovered = client.updatePackage.mock.calls.at(-1)![1];
    expect(recovered).toMatchObject({ sync_status: 'ok', sync_error: null });
    expect(recovered.carrier_data.routing.consecutive_failures).toBe(0);
    adapter.fetchUniversal.mockResolvedValue(withoutProgress);
    now = new Date('2026-09-10T16:00:00Z');
    await expect(service.syncPackage({ ...parcel, ...saved, ...recovered })).resolves.toMatchObject({ waiting: 1, errors: 0 });
    expect(client.updatePackage.mock.calls.at(-1)![1].carrier_data.routing.consecutive_failures).toBe(1);
    expect(capture()).toHaveLength(1);
  });

  it('keeps the overnight freshness window while thin answers accumulate missed checks', async () => {
    const logged = vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined);
    const capture = anomalyLogs(logged, 'progress_disappeared');
    const client = leasedClient();
    const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn().mockResolvedValue(withoutProgress) };
    const parcel = { id: 'overnight-thin', carrier: 'unknown', tracking_number: 'TEST1234', current_stage: 'in_transit',
      carrier_data: { tracking_provider: 'Ship24', routing: { version: 1, configured_carrier: 'unknown',
        preferred_provider: 'Ship24', consecutive_failures: 1, last_success_at: '2026-09-10T21:00:00Z' } } };
    let now = new Date('2026-09-10T23:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ waiting: 1, errors: 0 });
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    expect(saved).toMatchObject({ sync_status: 'ok', sync_error: null, carrier_data: { routing: {
      consecutive_failures: 2, last_success_at: '2026-09-10T21:00:00Z',
    } } });
    expect(capture()).toEqual([]);
    now = new Date('2026-09-11T00:00:00Z');
    await expect(service.syncPackage({ ...parcel, ...saved }, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: 0, errors: 1 });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'error', carrier_data: { routing: {
      consecutive_failures: 3, last_success_at: '2026-09-10T21:00:00Z',
    } } });
    expect(capture()).toEqual([expect.objectContaining({ carrier: 'unknown', anomaly_code: 'progress_disappeared' })]);
    // The answer came without an error: its anomaly is the refresh incident's evidence.
    expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(expect.any(String), 'overnight-thin',
      expect.arrayContaining([expect.objectContaining({ kind: 'refresh', healthy: false,
        details: expect.objectContaining({ error_type: 'ProgressDisappeared', anomaly_codes: 'progress_disappeared' }) })]));
  });

  it.each(['result', 'error', 'unannounced'] as const)(
    'discards a late %s after the tracking configuration changes', async (outcome) => {
      const parcel = {
        id: 'corrected-package', carrier: 'ups', tracking_number: '1Z999AA10123456784',
        current_stage: 'pending', tracking_generation: 'old-generation',
      };
      let generation = parcel.tracking_generation;
      const savedEvents: JsonObject[] = [];
      let state: JsonObject = {};
      const client = fakeClient();
      client.applyTrackingSync.mockImplementation(async (snapshot, values, events = []) => {
        if (snapshot.tracking_generation !== generation) return false;
        state = { ...state, ...values };
        savedEvents.push(...events);
        return true;
      });
      const adapter = { fetch: vi.fn(async (): Promise<CarrierResult> => {
        // Simulate the SQL carrier reset while the old request is in flight.
        generation = 'new-generation';
        state = { current_stage: 'pending', sync_status: 'pending' };
        if (outcome === 'error') throw new Error('Old carrier unavailable');
        if (outcome === 'unannounced') throw Object.assign(new Error('not found'), { status: 404 });
        return {
          status: 'delivered' as const,
          events: [{ time: '2026-09-06T12:00:00Z', description: 'Delivered by old carrier' }],
        };
      }) };
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter);

      await expect(service.syncPackage(parcel)).resolves.toMatchObject({ superseded: 1, updated: 0, errors: 0 });
      expect(savedEvents).toEqual([]);
      expect(state).toEqual({ current_stage: 'pending', sync_status: 'pending' });
      expect(client.completeSyncAttempt).toHaveBeenLastCalledWith(
        expect.any(String), expect.objectContaining({ outcome: 'superseded' }), expect.any(Array),
      );

      adapter.fetch.mockResolvedValue({ status: 'in_transit', events: [] });
      await expect(service.syncPackage({ ...parcel, tracking_generation: generation }))
        .resolves.toMatchObject({ updated: 1, superseded: 0 });
      expect(state).toMatchObject({ current_stage: 'in_transit', sync_status: 'ok' });
    },
  );

  it.each(['ups', 'dhl'])('rejects a superseded %s check before fetching or changing status', async (carrier) => {
    const client = fakeClient();
    client.applyTrackingSync.mockResolvedValue(false);
    const adapter = { fetch: vi.fn() };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter);
    await expect(service.syncPackage({ id: 'old-snapshot', carrier }))
      .resolves.toMatchObject({ superseded: 1 });
    expect(adapter.fetch).not.toHaveBeenCalled();
    expect(client.updatePackage).not.toHaveBeenCalled();
  });

  it('tells support the carrier that confirmed the number directly', async () => {
    const parcel = { id: 'package-1', user_id: 'user-1', carrier: 'dhl', tracking_number: 'HL123456789RV', current_stage: 'pending',
      carrier_data: { routing: { version: 1, confirmed_carrier: 'chronopost', confirmed_number: 'HL123456789RV' } } };
    const client = fakeClient([parcel]);
    const adapter: TrackingAdapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit', events: [] }) };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter).syncPackage(parcel);
    expect(client.startSyncAttempt).toHaveBeenCalledWith(expect.any(String), expect.objectContaining({
      support_context: expect.objectContaining({ reasons: ['ambiguous_shape'] }),
    }), undefined);
  });

  it('stores normalized events and advances the package stage', async () => {
    const parcel = {
      id: 'package-1',
      user_id: 'user-1',
      carrier: 'dpd',
      tracking_number: '06080000000002',
      current_stage: 'pending',
    };
    const client = fakeClient([parcel]);
    const adapter: TrackingAdapter = {
      fetch: vi.fn().mockResolvedValue({
        status: 'delivered',
        last_status_text: 'Delivered',
        last_update: '2026-08-04T12:38:28Z',
        events: [{
          time: '2026-08-04T12:38:28Z',
          location: 'Zürich',
          description: 'Delivered',
        }],
      }),
    };
    const service = new TrackingSyncService(
      client as unknown as SupabaseServiceClient,
      adapter,
      null,
      () => new Date('2026-08-04T13:00:00Z'),
    );

    await expect(service.sync()).resolves.toMatchObject({ checked: 1, updated: 1, errors: 0 });
    expect(client.insertEvents).toHaveBeenCalledWith([
      expect.objectContaining({ stage: 'delivered', package_id: 'package-1' }),
    ]);
    expect(client.updatePackage).toHaveBeenLastCalledWith('package-1', expect.objectContaining({
      current_stage: 'delivered',
      sync_status: 'ok',
      sync_error: null,
    }));
    expect(client.startSyncAttempt).toHaveBeenCalledWith(expect.any(String), expect.objectContaining({
      package_id: 'package-1',
      trigger: 'scheduled',
      configured_carrier: 'dpd',
      support_context: expect.objectContaining({
        tracking_number: '06080000000002', reasons: ['ambiguous_shape'],
      }),
    }), undefined);
    expect(client.completeSyncAttempt).toHaveBeenCalledWith(
      expect.any(String),
      expect.objectContaining({
        outcome: 'updated',
        provider_status: 'delivered',
        selected_stage: 'delivered',
        events_received: 1,
        events_normalized: 1,
      }),
      expect.arrayContaining([
        expect.objectContaining({ step: 'fetch', status: 'succeeded' }),
        expect.objectContaining({ step: 'normalize', status: 'succeeded' }),
        expect.objectContaining({ step: 'persist_events', status: 'succeeded' }),
        expect.objectContaining({ step: 'persist_package', status: 'succeeded', details: {
          outcome: 'updated', selected_stage: 'delivered', saved_stage: 'delivered',
        } }),
        expect.objectContaining({ step: 'complete', status: 'succeeded', details: expect.objectContaining({
          support_lookup_number: '06080000000002', support_provider: null, support_direct_progress: true,
        }) }),
      ]),
    );
  });

  it.each([
    ['an answer set aside', 'delivered', { status: 'in_transit', description: 'In transit' }, 'answer_set_aside'],
    ['an earlier stage', 'in_transit', { status: 'accepted', description: 'Parcel accepted' }, 'earlier_stage'],
  ])('logs the stage it saved when %s holds it', async (_case, saved, answer, held) => {
    const parcel = { id: 'package-held', user_id: 'user-1', carrier: 'dpd', tracking_number: '06080000000002', current_stage: saved };
    const client = fakeClient([parcel]);
    const adapter: TrackingAdapter = {
      fetch: vi.fn().mockResolvedValue({ status: answer.status, last_status_text: answer.description, last_update: '2026-08-04T12:38:28Z',
        events: [{ time: '2026-08-04T12:38:28Z', description: answer.description }] }),
    };
    await new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-08-04T13:00:00Z')).syncPackage(parcel);
    const steps = client.completeSyncAttempt.mock.calls.at(-1)![2] as JsonObject[];
    expect(steps.find((step) => step.step === 'persist_package')?.details).toMatchObject({
      selected_stage: answer.status, saved_stage: saved, stage_held: held,
    });
    expect(client.updatePackage.mock.calls.at(-1)?.[1]?.current_stage ?? saved).toBe(saved);
  });

  it('prefers an explicit current failure over an older timestamped milestone', async () => {
    const parcel = {
      id: 'package-current-failure',
      carrier: 'colisweb',
      tracking_number: '10000000',
      current_stage: 'in_transit',
    };
    const client = fakeClient();
    const adapter: TrackingAdapter = {
      fetch: vi.fn().mockResolvedValue({
        status: 'exception',
        last_status_text: 'Incident de livraison',
        last_update: '2026-08-28T11:30:00+02:00',
        events: [{
          description: 'Incident de livraison',
          stage: 'failed_attempt',
        }, {
          time: '2026-08-28T11:30:00+02:00',
          description: 'Colis pris en charge',
          stage: 'in_transit',
        }],
      }),
    };
    const service = new TrackingSyncService(
      client as unknown as SupabaseServiceClient,
      adapter,
      null,
      () => new Date('2026-08-30T13:00:00Z'),
    );

    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ updated: 1, errors: 0 });
    expect(client.insertEvents).toHaveBeenCalledWith(expect.arrayContaining([
      expect.objectContaining({ stage: 'in_transit' }),
      expect.objectContaining({
        stage: 'exception',
        occurred_at: '2026-08-30T13:00:00.000Z',
      }),
    ]));
    expect(client.updatePackage).toHaveBeenLastCalledWith(
      'package-current-failure',
      expect.objectContaining({
        current_stage: 'exception',
        last_status_text: 'Incident de livraison',
        sync_status: 'ok',
      }),
    );
  });

  it('keeps timed progress ahead of a pending provider summary', async () => {
    const parcel = {
      id: 'package-pending-summary',
      carrier: 'paack',
      tracking_number: 'PAACK12345',
      current_stage: 'pending',
    };
    const client = fakeClient();
    const adapter: TrackingAdapter = {
      fetch: vi.fn().mockResolvedValue({
        status: 'pending',
        last_status_text: 'Shipment registered',
        events: [{
          time: '2026-08-28T11:30:00+02:00',
          description: 'Received at hub',
          stage: 'in_transit',
        }],
      }),
    };
    const service = new TrackingSyncService(
      client as unknown as SupabaseServiceClient,
      adapter,
    );

    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ updated: 1, errors: 0 });
    expect(client.updatePackage).toHaveBeenLastCalledWith(
      'package-pending-summary',
      expect.objectContaining({
        current_stage: 'in_transit',
        sync_status: 'ok',
      }),
    );
  });

  it('keeps a newly unannounced shipment waiting without showing an error', async () => {
    const parcel = {
      id: 'package-2',
      carrier: 'dpd',
      tracking_number: '06080000000002',
      current_stage: 'pending',
    };
    const client = fakeClient();
    const adapter: TrackingAdapter = {
      fetch: vi.fn().mockRejectedValue(Object.assign(new Error('not live'), { status: 404 })),
    };
    const service = new TrackingSyncService(
      client as unknown as SupabaseServiceClient,
      adapter,
      null,
      () => new Date('2026-08-04T13:00:00Z'),
    );

    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ waiting: 1, errors: 0 });
    expect(client.updatePackage).toHaveBeenLastCalledWith('package-2', {
      last_synced_at: '2026-08-04T13:00:00.000Z',
      sync_status: 'waiting',
      sync_error: null,
    });
    expect(client.completeSyncAttempt).toHaveBeenCalledWith(
      expect.any(String),
      expect.objectContaining({ outcome: 'waiting', error_type: null }),
      expect.arrayContaining([
        expect.objectContaining({
          step: 'fetch',
          status: 'succeeded',
          details: { disposition: 'unannounced' },
        }),
        expect.objectContaining({ step: 'normalize', status: 'skipped' }),
      ]),
    );
  });

  it('keeps an unannounced parcel waiting when every fallback provider fails on it too', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockRejectedValue(Object.assign(new Error('not live'), { status: 404 })),
      fetchUniversal: vi.fn().mockRejectedValue(new Error('provider down')) };
    const parcel = { id: 'fresh', carrier: 'dhl', tracking_number: 'TEST1234', current_stage: 'pending' };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: 1, errors: 0 });
    expect(adapter.fetchUniversal).toHaveBeenCalled();
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'waiting', sync_error: null,
      carrier_data: { routing: { failures: { dhl: { kind: 'not_found' }, Ship24: { kind: expect.any(String) } } } } });
    expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(expect.any(String), 'fresh',
      expect.arrayContaining([expect.objectContaining({ kind: 'refresh', healthy: true })]));
  });

  it('keeps an unknown-carrier parcel waiting while no provider has history for it', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const noHistory = (source: string) => source === 'Ship24' ? new NotFoundError(source)
      : new IndeterminateError(source, `${source} has no usable shipment history`);
    const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn(async (source: string) => { throw noHistory(source); }) };
    const parcel = { id: 'not-handed-over', carrier: 'unknown', tracking_number: 'TEST1234', current_stage: 'pending' };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: 1, errors: 0 });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'waiting', sync_error: null,
      carrier_data: { routing: { failures: { Ship24: { kind: 'not_found' }, ParcelsApp: { kind: 'no_history' } } } } });

    // A provider that could not be reached leaves the answer open.
    adapter.fetchUniversal.mockImplementation(async (source: string) => {
      throw source === 'ParcelsApp' ? new UpstreamHttpError(source, 502) : noHistory(source);
    });
    await expect(service.syncPackage({ ...parcel, id: 'provider-down' }, { trigger: 'scheduled' }))
      .resolves.toMatchObject({ waiting: 1, errors: 0 });
  });

  it.each([['registered', 'waiting'], ['in_transit', 'error']] as const)(
    'files a %s parcel’s check as %s while every source answers without history', async (stage, outcome) => {
      const client = { ...fakeClient(),
        acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
        finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
      };
      const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn(async (source: string) => {
        throw source === 'Ship24' ? new NotFoundError(source) : new IndeterminateError(source, `${source} has no usable shipment history`);
      }) };
      // A label created is no progress: only a parcel that moved has lost its answer. The previous
      // check already missed, so another missed check would show an error.
      const parcel = { id: 'label-only', carrier: 'unknown', tracking_number: 'TEST1234', current_stage: stage,
        carrier_data: { routing: { version: 1, configured_carrier: 'unknown', consecutive_failures: 1, probe_cursor: 0, discovery_cursor: 0 } } };
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
      const waiting = outcome === 'waiting';
      await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: waiting ? 1 : 0, errors: waiting ? 0 : 1 });
      expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: outcome });
      expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(expect.any(String), 'label-only',
        expect.arrayContaining([expect.objectContaining({ kind: 'refresh', healthy: waiting })]));
    });

  it('signals the first check of a labelled parcel whose every source answers without history, once', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn(async (source: string) => {
      throw source === 'Ship24' ? new NotFoundError(source) : new IndeterminateError(source, `${source} has no usable shipment history`);
    }) };
    let parcel: JsonObject = { id: 'label-only', carrier: 'unknown', tracking_number: 'TEST1234', current_stage: 'registered',
      carrier_data: { routing: { version: 1, configured_carrier: 'unknown', consecutive_failures: 1, probe_cursor: 0, discovery_cursor: 0 } } };
    client.updatePackage.mockImplementation(async (_id: string, values: JsonObject) => { parcel = { ...parcel, ...values }; });
    let now = new Date('2026-09-10T12:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: 1, errors: 0 });
    expect(client.completeSyncAttempt.mock.lastCall![1]).toMatchObject({ outcome: 'waiting', anomaly_codes: ['scans_withdrawn'] });
    expect(parcel).toMatchObject({ sync_status: 'waiting', carrier_data: { scans_withdrawn: { unknown: '2026-09-10T12:00:00.000Z' } } });
    now = new Date('2026-09-10T18:00:00Z');
    await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: 1, errors: 0 });
    expect(client.completeSyncAttempt.mock.lastCall![1]).toMatchObject({ outcome: 'waiting', anomaly_codes: [] });
    expect(parcel).toMatchObject({ carrier_data: { scans_withdrawn: { unknown: '2026-09-10T12:00:00.000Z' } } });
  });

  it.each([
    // Printed ten months before the parcel was added: a reused number's earlier parcel, which an
    // older release stored and today's rules refuse. The check forgets it.
    ['an earlier parcel\'s label', '2025-12-15T10:00:00+00:00', true],
    // The parcel's own label, which the providers stopped listing, stays, with the signal.
    ['its own label', '2026-10-19T10:00:00+00:00', false],
  ])('routes a parcel that stored %s, now that every source answers without history', async (_label, printed, forgets) => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-10-24T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockRejectedValue(new NoHistoryError('Chronopost')), fetchUniversal: vi.fn(async (source: string) => {
      throw source === 'Ship24' ? new NotFoundError(source) : new IndeterminateError(source, `${source} has no usable shipment history`);
    }) };
    const added = '2026-10-20T09:00:00Z';
    const own = { provider_event_id: 'app:pending', occurred_at: added, stage: 'pending', description: 'Tracking added' };
    const label = { provider_event_id: 'unknown:label', occurred_at: printed, stage: 'registered', description: 'Label created',
      universal_provider: 'ParcelsApp' };
    let parcel: JsonObject = { id: 'reused', carrier: 'unknown', tracking_number: '12345678', current_stage: 'registered', created_at: added,
      sync_status: 'waiting', last_status_text: 'Label created', [STORED_EVENT_IDENTITIES]: [own, label],
      carrier_data: { lookup_country_hint: 'FR', status: 'pending', current_stage: 'registered', last_status_text: 'Label created',
        tracking_provider: 'ParcelsApp', discovered_carrier: 'chronopost', reported_carriers: ['Chronopost'],
        routing: { version: 1, configured_carrier: 'unknown', consecutive_failures: 0, probe_cursor: 0, discovery_cursor: 0,
          last_event_at: new Date(printed).toISOString(), last_success_at: '2026-10-21T10:00:00.000Z', discovered_carrier: 'chronopost',
          preferred_provider: 'ParcelsApp' } } };
    client.updatePackage.mockImplementation(async (_id: string, values: JsonObject) => { parcel = { ...parcel, ...values }; });
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-10-24T12:00:00Z'));
    await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: 1, errors: 0 });
    const [, selected, , , deleted] = client.applyTrackingSync.mock.calls[0] as unknown as [JsonObject, JsonObject, JsonObject[], string[], string[]];
    const steps = client.completeSyncAttempt.mock.lastCall![2] as JsonObject[];
    const routing = (parcel.carrier_data as JsonObject).routing as JsonObject;
    if (forgets) {
      expect(selected).toMatchObject({ sync_status: 'syncing', current_stage: 'pending', last_status_text: null, expected_delivery: null });
      expect(deleted).toEqual(['unknown:label']);
      expect(steps.find((step) => step.step === 'selected')?.details).toMatchObject({ earlier_parcel_rows: 1 });
      // As for a number nobody has seen: no signal, and a parcel looked up from France is offered Mondial Relay's postcode.
      expect(client.completeSyncAttempt.mock.lastCall![1]).toMatchObject({ outcome: 'waiting', anomaly_codes: [] });
      expect(parcel).toMatchObject({ current_stage: 'pending', sync_status: 'waiting', carrier_data: { lookup_country_hint: 'FR' } });
      expect(parcel.carrier_data).not.toHaveProperty('discovered_carrier');
      expect(parcel.carrier_data).not.toHaveProperty('tracking_provider');
      expect(routing.input_suggested).toEqual({ carrier: 'mondial-relay', field: 'dpdPostcode' });
      for (const key of ['last_event_at', 'last_success_at', 'discovered_carrier', 'preferred_provider']) expect(routing[key]).toBeUndefined();
    } else {
      expect(selected).toEqual({ sync_status: 'syncing', sync_error: null });
      expect(deleted ?? []).toEqual([]);
      expect(client.completeSyncAttempt.mock.lastCall![1]).toMatchObject({ outcome: 'waiting', anomaly_codes: ['scans_withdrawn'] });
      expect(parcel).toMatchObject({ current_stage: 'registered', carrier_data: { discovered_carrier: 'chronopost' } });
      expect(routing).toMatchObject({ last_event_at: new Date(printed).toISOString(), discovered_carrier: 'chronopost' });
    }
  });

  it('forgets the earlier parcel its own carrier still lists, and the confirmation it gave', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-10-24T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    // Picked up six months before the parcel was added, and never delivered.
    const earlier = { status: 'in_transit', current_stage: 'accepted', last_update: '2026-04-14T10:00:00Z',
      events: [{ time: '2026-04-14T10:00:00Z', description: 'Parcel accepted', stage: 'accepted' }] };
    const adapter = { fetch: vi.fn().mockResolvedValue(earlier), fetchUniversal: vi.fn(async (source: string) => {
      throw source === 'Ship24' ? new NotFoundError(source) : new IndeterminateError(source, `${source} has no usable shipment history`);
    }) };
    const added = '2026-10-06T09:00:00Z';
    let parcel: JsonObject = { id: 'filed', carrier: 'swiss-post', tracking_number: 'TEST1234', current_stage: 'accepted', created_at: added,
      sync_status: 'ok', last_synced_at: '2026-10-07T09:00:00Z', last_status_text: 'Parcel accepted',
      [STORED_EVENT_IDENTITIES]: [{ provider_event_id: 'app:pending', occurred_at: added, stage: 'pending', description: 'Tracking added' },
        { provider_event_id: 'swiss-post:pickup', occurred_at: '2026-04-14T10:00:00+00:00', stage: 'accepted', description: 'Parcel accepted' }],
      carrier_data: { lookup_country_hint: 'DE', status: 'in_transit', active_tracking_carrier: 'swiss-post',
        routing: { version: 1, configured_carrier: 'swiss-post', consecutive_failures: 0, probe_cursor: 0, discovery_cursor: 0, failures: {},
          last_event_at: '2026-04-14T10:00:00.000Z', confirmed_carrier: 'swiss-post', confirmed_number: 'TEST1234' } } };
    client.updatePackage.mockImplementation(async (_id: string, values: JsonObject) => { parcel = { ...parcel, ...values }; });
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-10-24T12:00:00Z'));
    await expect(service.syncPackage(parcel, { trigger: 'refresh' })).resolves.toMatchObject({ waiting: 1, errors: 0 });
    expect(adapter.fetch).toHaveBeenCalled();
    expect((client.applyTrackingSync.mock.calls[0] as unknown[])[4]).toEqual(['swiss-post:pickup']);
    expect(parcel).toMatchObject({ current_stage: 'pending', sync_status: 'waiting', carrier_data: { lookup_country_hint: 'DE' } });
    expect(parcel.carrier_data).not.toHaveProperty('active_tracking_carrier');
    const routing = (parcel.carrier_data as JsonObject).routing as JsonObject;
    expect(routing).toMatchObject({ cleared_confirmation: { carrier: 'swiss-post', number: 'TEST1234' } });
    expect(routing.confirmed_carrier).toBeUndefined();
    expect(routing.last_event_at).toBeUndefined();
  });

  it('keeps a number nobody has seen waiting while one provider rests on a rate limit', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn(async (source: string) => ({ token: source === 'ParcelsApp' ? null : 'lease',
        retry_at: source === 'ParcelsApp' ? '2026-09-10T13:00:00Z' : '2026-09-10T12:01:30Z' })),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn(async (source: string) => { throw new NotFoundError(source); }) };
    // The previous check already missed, so another missed check would show an error.
    const parcel = { id: 'unseen', carrier: 'unknown', tracking_number: 'TEST1234', current_stage: 'pending',
      carrier_data: { routing: { version: 1, configured_carrier: 'unknown', consecutive_failures: 1, probe_cursor: 0, discovery_cursor: 0 } } };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: 1, errors: 0 });
    expect(adapter.fetchUniversal.mock.calls.map(([source]) => source)).not.toContain('ParcelsApp');
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'waiting', sync_error: null,
      carrier_data: { routing: { failures: { Ship24: { kind: 'not_found' }, ParcelsApp: { kind: 'rate_limited' } } } } });
    expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(expect.any(String), 'unseen',
      expect.arrayContaining([expect.objectContaining({ kind: 'refresh', healthy: true })]));
  });

  it('keeps a number nobody has seen waiting while one provider fails without answering, not while its carrier does', async () => {
    const sync = async (carrier: string, fetch: () => Promise<never>) => {
      const client = { ...fakeClient(),
        acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
        finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
      };
      const adapter = { fetch: vi.fn(fetch), fetchUniversal: vi.fn(async (source: string) => {
        throw source === '17TRACK' ? new Error('socket hang up') : new NotFoundError(source);
      }) };
      // The previous check already missed, so another missed check would show an error.
      const parcel = { id: 'unseen', carrier, tracking_number: 'TEST1234', current_stage: 'pending',
        carrier_data: { routing: { version: 1, configured_carrier: carrier, consecutive_failures: 1, probe_cursor: 0, discovery_cursor: 0 } } };
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
      const counts = await service.syncPackage(parcel, { trigger: 'scheduled' });
      return { counts, saved: client.updatePackage.mock.calls.at(-1)![1] };
    };
    const unseen = await sync('unknown', () => { throw new Error('not asked'); });
    expect(unseen.counts).toMatchObject({ waiting: 1, errors: 0 });
    expect(unseen.saved).toMatchObject({ sync_status: 'waiting', sync_error: null,
      carrier_data: { routing: { failures: { Ship24: { kind: 'not_found' }, '17TRACK': { kind: 'transport' } } } } });
    const carrierDown = await sync('dhl', async () => { throw new Error('socket hang up'); });
    expect(carrierDown.counts).toMatchObject({ waiting: 0, errors: 1 });
    expect(carrierDown.saved.carrier_data.routing.failures.dhl).toMatchObject({ kind: 'transport' });
  });

  it('keeps a parcel waiting while its carrier lists it without scans and no provider has history', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockRejectedValue(new NoHistoryError('Ecoscooting', 'Ecoscooting lists the parcel without scans')),
      fetchUniversal: vi.fn(async (source: string) => { throw new IndeterminateError(source, `${source} has no usable shipment history`); }) };
    // The previous check already missed, so another missed check would show an error.
    const parcel = { id: 'announced', carrier: 'ecoscooting', tracking_number: 'TEST1234', current_stage: 'pending',
      carrier_data: { routing: { version: 1, configured_carrier: 'ecoscooting', consecutive_failures: 1, probe_cursor: 0, discovery_cursor: 0 } } };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: 1, errors: 0 });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'waiting', sync_error: null,
      carrier_data: { routing: { consecutive_failures: 0, failures: { ecoscooting: { kind: 'no_history' } } } } });

    // So is a label its tracking does not know yet.
    adapter.fetch.mockRejectedValue(new IndeterminateError('Ecoscooting', 'Ecoscooting could not complete the tracking query', { reason: 'portal_not_found' }));
    await expect(service.syncPackage({ ...parcel, id: 'unannounced' }, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: 1, errors: 0 });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'waiting', sync_error: null,
      carrier_data: { routing: { consecutive_failures: 0, failures: { ecoscooting: { kind: 'no_history' } } } } });
    expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(expect.any(String), 'unannounced',
      expect.arrayContaining([expect.objectContaining({ kind: 'refresh', healthy: true })]));

    // Any other inconclusive answer from the carrier describes the carrier, not the parcel.
    adapter.fetch.mockRejectedValue(new IndeterminateError('Ecoscooting', 'Ecoscooting returned its outage page'));
    await expect(service.syncPackage({ ...parcel, id: 'carrier-down' }, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: 0, errors: 1 });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'error',
      carrier_data: { routing: { failures: { ecoscooting: { kind: 'transport' } } } } });
  });

  it.each(['portal_not_found', 'no_history', 'webtrack_no_history'])(
    'keeps a pending parcel waiting, check after check, while its carrier answers %s and no provider has history', async (reason) => {
      const client = { ...fakeClient(),
        acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
        finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
      };
      const adapter = { fetch: vi.fn().mockRejectedValue(new IndeterminateError('Royal Mail', 'Royal Mail could not confirm the item', { reason })),
        fetchUniversal: vi.fn(async (source: string) => { throw new NoHistoryError(source, `${source} has no usable shipment history`); }) };
      let now = new Date('2026-09-10T12:00:00Z');
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
      let parcel: JsonObject = { id: 'unannounced', carrier: 'royal-mail', tracking_number: 'TEST1234', current_stage: 'pending' };
      // Never answered with a scan, the parcel is never "recent": each check is still an answer, not a failure.
      for (const time of ['12:00', '13:00', '14:00']) {
        now = new Date(`2026-09-10T${time}:00Z`);
        await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: 1, errors: 0 });
        const saved = client.updatePackage.mock.calls.at(-1)![1] as JsonObject;
        expect(saved).toMatchObject({ sync_status: 'waiting', sync_error: null,
          carrier_data: { routing: { consecutive_failures: 0, failures: { 'royal-mail': { kind: 'no_history' } } } } });
        expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(expect.any(String), 'unannounced',
          expect.arrayContaining([expect.objectContaining({ kind: 'refresh', healthy: true })]));
        parcel = { ...parcel, carrier_data: saved.carrier_data };
      }
      expect(adapter.fetch).toHaveBeenCalledTimes(3);
    });

  it('asks the owner for the input its carrier needs, though no provider has history for the parcel either', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockRejectedValue(new InputRequiredError('Heppner', 'the delivery postcode')),
      fetchUniversal: vi.fn(async (source: string) => {
        throw source === 'Ship24' ? new NotFoundError(source) : new IndeterminateError(source, `${source} has no usable shipment history`);
      }) };
    // The previous check already missed, so another missed check shows why.
    const parcel = { id: 'needs-input', carrier: 'heppner', tracking_number: 'TEST1234', current_stage: 'pending',
      carrier_data: { routing: { version: 1, configured_carrier: 'heppner', consecutive_failures: 1, probe_cursor: 0, discovery_cursor: 0 } } };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: 0, errors: 1 });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'error', sync_error: 'carrier:input_required',
      carrier_data: { routing: { consecutive_failures: 2, failures: {
        heppner: { kind: 'input_required', user_error: 'carrier:input_required', retry_at: '2026-09-10T12:15:00.000Z' },
        Ship24: { kind: 'not_found' } } } } });
    // The owner has to supply it: the carrier answered.
    expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(expect.any(String), 'needs-input',
      expect.arrayContaining([expect.objectContaining({ kind: 'refresh', healthy: true })]));
  });

  it('records how the parcel\'s own carrier did when another source served the check', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const failure = new SchemaError('La Poste', 'The answer changed shape');
    const progress: CarrierResult = { status: 'in_transit', current_stage: 'in_transit', last_update: '2026-09-10T11:00:00Z',
      events: [{ time: '2026-09-10T11:00:00Z', description: 'In transit', stage: 'in_transit' }] };
    const adapter = { fetch: vi.fn(async (carrier: string): Promise<CarrierResult> => {
      healthStepRecorder.lookup({ carrier, finalStep: 'direct', durationMs: 1, attempts: 1, outcome: 'schema', errorType: failure.name, error: failure });
      throw failure;
    }), fetchUniversal: vi.fn().mockResolvedValue(progress) };
    let now = new Date('2026-09-10T12:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    const fetchStep = () => (client.completeSyncAttempt.mock.lastCall![2] as { step: string; details: JsonObject }[])
      .find((step) => step.step === 'fetch')!.details;
    const parcel = { id: 'fell-back', carrier: 'la-poste', tracking_number: 'CW123456785FR', current_stage: 'accepted' };
    await service.syncPackage(parcel);
    expect(fetchStep()).toMatchObject({ source_carrier: 'unknown', direct_outcome: 'schema' });
    // A carrier waiting out its failure was not asked.
    now = new Date('2026-09-10T12:20:00Z');
    await service.syncPackage({ ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] });
    expect(client.completeSyncAttempt).toHaveBeenCalledTimes(2);
    expect(adapter.fetch).toHaveBeenCalledOnce();
    expect(fetchStep()).toMatchObject({ source_carrier: 'unknown', direct_outcome: 'not_asked' });
    // A carrier that served its own parcel needs no reason.
    adapter.fetch.mockImplementationOnce(async () => progress);
    await service.syncPackage({ ...parcel, id: 'direct' });
    expect(fetchStep()).toMatchObject({ source_carrier: 'la-poste' });
    expect(fetchStep()).not.toHaveProperty('direct_outcome');
  });

  it('says at the first check that the carrier chosen for a parcel without scans refuses its number', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockRejectedValue(new InvalidInputError('New Zealand Couriers', 'Not a New Zealand Couriers number')),
      fetchUniversal: vi.fn(async (source: string) => {
        throw source === 'Ship24' ? new NotFoundError(source) : new IndeterminateError(source, `${source} has no usable shipment history`);
      }) };
    const parcel = { id: 'refused', carrier: 'nz-couriers', tracking_number: 'TEST1234', current_stage: 'pending',
      carrier_data: { routing: { version: 1, configured_carrier: 'nz-couriers', consecutive_failures: 0, probe_cursor: 0, discovery_cursor: 0 } } };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.syncPackage(parcel, { trigger: 'package' })).resolves.toMatchObject({ waiting: 0, errors: 1 });
    // The number itself is refused: it has its own copy.
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'error', sync_error: 'carrier:refused_number',
      carrier_data: { routing: { failures: { 'nz-couriers': { kind: 'invalid_input', user_error: 'carrier:invalid_input' } } } } });
    // The parcel failed, not its carrier.
    expect(client.completeSyncAttempt.mock.calls.at(-1)![1]).toMatchObject({ outcome: 'refused' });

    // A parcel the providers already followed keeps its scans in view, as before.
    await expect(service.syncPackage({ ...parcel, id: 'followed', current_stage: 'in_transit' }, { trigger: 'package' }))
      .resolves.toMatchObject({ waiting: 1, errors: 0 });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'ok', sync_error: null });

    // A refused postcode asks for the parcel's details, as before: Heppner's adapter takes the number.
    adapter.fetch.mockRejectedValue(new InvalidInputError('Heppner', 'Heppner postcode does not match the country'));
    const postcode = { ...parcel, id: 'postcode', carrier: 'heppner', dpd_postcode: '1000',
      carrier_data: { routing: { ...parcel.carrier_data.routing, configured_carrier: 'heppner' } } };
    await expect(service.syncPackage(postcode, { trigger: 'package' })).resolves.toMatchObject({ waiting: 0, errors: 1 });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'error', sync_error: 'carrier:invalid_input' });
    expect(client.completeSyncAttempt.mock.calls.at(-1)![1]).toMatchObject({ outcome: 'refused' });
  });

  it('asks the owner to correct a placeholder number every source refuses, whatever the parcel showed before', async () => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const refusal = (source: string) => new InvalidInputError(source, `${source} refuses a placeholder number`, { reason: 'placeholder_number' });
    const adapter = { fetch: vi.fn(async () => { throw refusal('DHL'); }),
      fetchUniversal: vi.fn(async (source: string) => { throw refusal(source); }) };
    const parcel = { id: 'placeholder', carrier: 'unknown', tracking_number: '1234567890', current_stage: 'delivered',
      carrier_data: { routing: { version: 1, configured_carrier: 'unknown', consecutive_failures: 0, probe_cursor: 0, discovery_cursor: 0 } } };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: 0, errors: 1 });
    const saved = client.updatePackage.mock.calls.at(-1)![1];
    expect(saved).toMatchObject({ sync_status: 'error', sync_error: 'carrier:placeholder_number' });
    // The stored stage stays: the parcel is served without it (eventPlaces.ts).
    expect(saved).not.toHaveProperty('current_stage');
    // Only the owner can correct the number: the carriers and providers answered.
    expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(expect.any(String), 'placeholder',
      expect.arrayContaining([expect.objectContaining({ kind: 'refresh', healthy: true })]));

    // So does a parcel without scans whose own carrier refuses it, at the first check.
    const own = { ...parcel, id: 'placeholder-dhl', carrier: 'dhl', current_stage: 'pending',
      carrier_data: { routing: { ...parcel.carrier_data.routing, configured_carrier: 'dhl' } } };
    await expect(service.syncPackage(own, { trigger: 'package' })).resolves.toMatchObject({ waiting: 0, errors: 1 });
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ sync_status: 'error', sync_error: 'carrier:placeholder_number' });
  });

  it('records no health sample for a scheduled check that contacted no provider', async () => {
    const client = fakeClient();
    const adapter = { fetch: vi.fn(), fetchUniversal: vi.fn() };
    const cooling = Object.fromEntries(['dhl', 'Ship24', 'ParcelsApp', '17TRACK']
      .map((provider) => [provider, { count: 1, kind: 'transport', retry_at: '2026-09-10T13:00:00.000Z' }]));
    const parcel = { id: 'cooling', carrier: 'dhl', tracking_number: 'TEST1234', current_stage: 'pending',
      carrier_data: { routing: { version: 1, configured_carrier: 'dhl', failures: cooling,
        consecutive_failures: 2, probe_cursor: 0, discovery_cursor: 0 } } };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ errors: 1 });
    expect(adapter.fetch).not.toHaveBeenCalled();
    expect(adapter.fetchUniversal).not.toHaveBeenCalled();
    expect(client.completeSyncAttempt).toHaveBeenCalled();
    expect(client.recordTrackingHealth).not.toHaveBeenCalled();
  });

  it.each([
    ['answering it has no shipment', 'portal_not_found', true],
    ['answering inconclusively', undefined, false],
  ] as const)('answers a pending parcel’s check, while a provider fails, by its carrier %s: %s', async (_case, reason, answered) => {
    vi.spyOn(scraper, 'universalPlan').mockReturnValue({ sources: ['ParcelsApp', '17TRACK'], carrier: null, tier: () => 'unknown', rank: () => 0 });
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: null }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    const adapter = {
      fetch: vi.fn().mockRejectedValue(new IndeterminateError('SF Express', 'SF Express returned no shipment', reason ? { reason } : undefined)),
      fetchUniversal: vi.fn(async (source: string) => {
        if (source === 'ParcelsApp') throw new Error('fetch failed');
        throw new NoHistoryError(source, 'No history');
      }),
    };
    let now = new Date('2026-09-10T10:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    let parcel: JsonObject = { id: 'sf-parcel', carrier: 'sf-express', tracking_number: 'SF1234567890123', current_stage: 'pending',
      created_at: '2026-09-01T10:00:00Z', carrier_data: { routing: { version: 1, configured_carrier: 'sf-express' } } };
    for (const [time, failing] of [['10:00', false], ['12:00', !answered], ['14:00', !answered]] as const) {
      now = new Date(`2026-09-10T${time}:00Z`);
      await expect(service.syncPackage(parcel, { trigger: 'scheduled' })).resolves.toMatchObject({ waiting: failing ? 0 : 1, errors: failing ? 1 : 0 });
      parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
      expect(parcel).toMatchObject({ sync_status: failing ? 'error' : 'waiting' });
      // The provider's outage is its own incident: the refresh got the carrier's answer, or it did not.
      expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(expect.any(String), 'sf-parcel', expect.arrayContaining([
        expect.objectContaining({ kind: 'refresh', subject: 'sf-express', healthy: answered })]));
    }
  });

  it.each([0, 1])('records why every provider failed with a previous streak of %s', async (streak) => {
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockRejectedValue(new UpstreamHttpError('DHL', 502)),
      fetchUniversal: vi.fn().mockRejectedValue(new Error('provider down')) };
    const parcel = { id: 'stale', carrier: 'dhl', tracking_number: 'TEST1234', current_stage: 'in_transit',
      carrier_data: { routing: { version: 1, configured_carrier: 'dhl', consecutive_failures: streak } } };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    await expect(service.syncPackage(parcel, { trigger: 'scheduled' }))
      .resolves.toMatchObject({ errors: streak, waiting: 1 - streak });
    expect(client.completeSyncAttempt).toHaveBeenCalledWith(
      expect.any(String),
      expect.objectContaining({ outcome: streak ? 'error' : 'waiting', error_type: 'RoutingDeferredError' }),
      expect.arrayContaining([expect.objectContaining({
        step: 'fetch', status: 'failed', error_type: 'RoutingDeferredError',
        details: expect.objectContaining({ providers_attempted: expect.any(Number), provider_failures: expect.stringContaining('dhl:transport') }),
      })]),
    );
    // No tier produced a sample here, so the attempt's own type and routing's failures are the refresh evidence.
    expect(client.recordTrackingHealth).toHaveBeenLastCalledWith(expect.any(String), 'stale',
      expect.arrayContaining([expect.objectContaining({ kind: 'refresh', healthy: false,
        details: { error_type: 'RoutingDeferredError', provider_failures: expect.stringContaining('dhl:transport') } })]));
  });

  it('preserves progressed shipment data when a carrier later reports not found', async () => {
    const parcel = {
      id: 'package-progressed',
      carrier: 'colis-prive',
      tracking_number: '99112233445575012',
      current_stage: 'in_transit',
      last_status_text: 'En cours d’acheminement',
    };
    const client = fakeClient();
    const adapter: TrackingAdapter = {
      fetch: vi.fn().mockRejectedValue(Object.assign(new NotFoundError('Colis Privé'), { name: 'ColisPriveTrackingError' })),
    };
    const service = new TrackingSyncService(
      client as unknown as SupabaseServiceClient,
      adapter,
      null,
      () => new Date('2026-08-04T13:00:00Z'),
    );

    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ errors: 1, waiting: 0 });
    expect(client.updatePackage).toHaveBeenLastCalledWith('package-progressed', {
      last_synced_at: '2026-08-04T13:00:00.000Z',
      sync_status: 'error',
      sync_error: 'carrier:not_found',
    });
    expect(client.completeSyncAttempt).toHaveBeenCalledWith(
      expect.any(String),
      expect.objectContaining({ outcome: 'error', error_type: 'ColisPriveTrackingError' }),
      expect.arrayContaining([
        expect.objectContaining({
          step: 'fetch',
          status: 'failed',
          error_type: 'ColisPriveTrackingError',
        }),
      ]),
    );
  });

  it.each(['amazon-logistics', 'unknown', 'ups'])('stops existing Amazon Logistics parcels routed as %s before any provider call', async (carrier) => {
    const client = fakeClient();
    const adapter: TrackingAdapter = { fetch: vi.fn(), fetchUniversal: vi.fn() };
    const capture = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter);
    const parcel = { id: 'amazon-parcel', carrier, tracking_number: 'FR3000000001' };
    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ unsupported: 1, errors: 0 });
    expect(adapter.fetch).not.toHaveBeenCalled();
    expect(adapter.fetchUniversal).not.toHaveBeenCalled();
    expect(capture).not.toHaveBeenCalled();
    expect(client.updatePackage).toHaveBeenCalledWith('amazon-parcel', expect.objectContaining({
      sync_status: 'unsupported', sync_error: expect.stringContaining('Amazon account'),
    }));
    expect(isTrackingSyncDue({ ...parcel, sync_status: 'unsupported' }, new Date())).toBe(false);
  });

  it('dispatches confirmed Shipping without applying the retail guard or trying universal providers', async () => {
    const client = fakeClient();
    const adapter = new CarrierTrackingAdapter();
    const fetch = vi.spyOn(adapter.registry.for('amazon-shipping')!, 'track').mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit' });
    const universal = vi.spyOn(adapter, 'fetchUniversal');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter);
    await service.syncPackage({ id: 'shipping-parcel', carrier: 'amazon-shipping', tracking_number: 'FR0000000001' });
    expect(fetch.mock.calls[0][0]).toMatchObject({ number: 'FR0000000001' });
    expect(universal).not.toHaveBeenCalled();
  });

  it('forwards the stored delivery postcode and the filed carrier to universal providers', async () => {
    const universal = { fetchSource: vi.fn().mockResolvedValue({ status: 'in_transit' }), fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) };
    const recorder: StepRecorder = { step: () => undefined, lookup: () => undefined };
    const registry = new AdapterRegistry({ factories: {}, carriers: { unknown: 'universal' } },
      { trawl: null, browserExecutablePath: null, recorder, env: {} } satisfies AdapterEnvironment);
    const adapter = new CarrierTrackingAdapter(universal as unknown as UniversalTracker, registry, recorder);
    await expect(adapter.fetch('unknown', 'TEST1234', null, '8000')).resolves.toMatchObject({ status: 'in_transit' });
    // Then the country hint, which the app leaves out, and the carrier the lookup is for.
    expect(universal.fetch).toHaveBeenCalledWith('TEST1234', '8000', expect.any(Object), undefined, 'unknown');
    await adapter.fetchUniversal('Ship24', 'TEST1234', 1000, '8000');
    expect(universal.fetchSource).toHaveBeenCalledWith('Ship24', 'TEST1234', 1000, '8000', null, undefined, null, null);
    await adapter.fetchUniversal('17TRACK', 'TEST1234', 1000, null, 'Europe/Zurich', 'swiss-post');
    expect(universal.fetchSource).toHaveBeenLastCalledWith('17TRACK', 'TEST1234', 1000, null, 'Europe/Zurich', undefined, null, 'swiss-post');
  });

  it('asks universal providers about the carrier the parcel is filed under', async () => {
    vi.spyOn(scraper, 'universalPlan').mockReturnValue({
      sources: ['17TRACK'], carrier: null, tier: () => 'unknown', rank: () => 0,
    });
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const universal = { fetch: vi.fn(), fetchSource: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit',
      last_update: '2026-09-10T11:00:00Z', events: [{ time: '2026-09-10T11:00:00Z', description: 'In transit', stage: 'in_transit' }] }) };
    const registry = new AdapterRegistry({ factories: {}, carriers: {} },
      { trawl: null, browserExecutablePath: null, recorder: NOOP_RECORDER, env: {} } satisfies AdapterEnvironment);
    const adapter = new CarrierTrackingAdapter(universal as unknown as UniversalTracker, registry, NOOP_RECORDER);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    // This registry has no ZTO adapter, and a parcel this old asks no carrier whether it knows the number.
    await service.syncPackage({ id: 'filed', carrier: 'zto', tracking_number: 'TEST1234', current_stage: 'in_transit', created_at: '2026-07-01T00:00:00Z' });
    expect(universal.fetchSource).toHaveBeenCalledExactlyOnceWith('17TRACK', 'TEST1234', expect.any(Number), null, 'Asia/Shanghai', undefined, null, 'zto');
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ carrier_data: { tracking_provider: '17TRACK' } });
  });

  it('keeps the postcode the owner gave a provider after the provider answers', async () => {
    vi.spyOn(scraper, 'universalPlan').mockReturnValue({
      sources: ['17TRACK'], carrier: null, tier: () => 'unknown', rank: () => 0,
    });
    const client = { ...fakeClient(),
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-09-10T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const universal = { fetch: vi.fn(), fetchSource: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit',
      last_update: '2026-09-10T11:00:00Z', events: [{ time: '2026-09-10T11:00:00Z', description: 'In transit', stage: 'in_transit' }] }) };
    const registry = new AdapterRegistry({ factories: {}, carriers: {} },
      { trawl: null, browserExecutablePath: null, recorder: NOOP_RECORDER, env: {} } satisfies AdapterEnvironment);
    const adapter = new CarrierTrackingAdapter(universal as unknown as UniversalTracker, registry, NOOP_RECORDER);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-09-10T12:00:00Z'));
    const parcel = { id: 'input', carrier: 'zto', tracking_number: 'TEST1234', current_stage: 'in_transit', created_at: '2026-07-01T00:00:00Z',
      carrier_data: { universal_input: { number: 'TEST1234', postcode: '0000' } } };
    await service.syncPackage(parcel);
    expect(universal.fetchSource.mock.calls[0][3]).toBe('0000');
    // Saved for the next check, which the router hands it to.
    expect(client.updatePackage.mock.calls.at(-1)![1]).toMatchObject({ carrier_data: { universal_input: { number: 'TEST1234', postcode: '0000' } } });
  });

  it('keeps expired Shipping history out of the timeline and Sentry', async () => {
    const client = fakeClient();
    const fetch = vi.fn().mockRejectedValue(new CarrierError('not_found', 'Amazon Shipping', 'Amazon Shipping history expired', { reason: 'history_expired' }));
    const universal = vi.fn();
    const capture = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, { fetch, fetchUniversal: universal });
    await expect(service.syncPackage({ id: 'shipping-parcel', carrier: 'amazon-shipping', tracking_number: 'UK0000000001' }))
      .resolves.toMatchObject({ unsupported: 1, errors: 0 });
    expect(client.updatePackage).toHaveBeenCalledWith('shipping-parcel', expect.objectContaining({ sync_status: 'unsupported', sync_error: 'amazon_shipping_history_expired' }));
    expect(universal).not.toHaveBeenCalled();
    expect(capture).not.toHaveBeenCalled();
  });

  it('marks an unavailable catalog carrier unsupported without pretending it was checked', async () => {
    const client = fakeClient();
    const adapter: TrackingAdapter = { fetch: vi.fn() };
    const service = new TrackingSyncService(
      client as unknown as SupabaseServiceClient,
      adapter,
    );

    await expect(service.syncPackage({ id: 'package-3', carrier: 'unavailable-carrier' }))
      .resolves.toMatchObject({ unsupported: 1, checked: 1 });
    expect(adapter.fetch).not.toHaveBeenCalled();
    expect(client.updatePackage).toHaveBeenCalledWith('package-3', expect.objectContaining({
      sync_status: 'unsupported',
      last_synced_at: null,
    }));
  });

  it('counts a likely typo at the first check of a parcel just added, apart when it found scans', async () => {
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    vi.spyOn(console, 'error').mockImplementation(() => undefined);
    const counted = vi.spyOn(metrics, 'recordTypoCandidate').mockImplementation(() => undefined);
    const typed = doubled(`RR${s10('12345678')}FR`, 5);
    const client = fakeClient();
    const unsupported = new TrackingSyncService(client as unknown as SupabaseServiceClient, { fetch: vi.fn() });
    const added = { trigger: 'added' } as const;
    await unsupported.syncPackage({ id: 'account-parcel', carrier: 'unknown', tracking_number: typed, user_id: 'user-1' }, added);
    await unsupported.syncPackage({ id: 'lookup', carrier: 'unknown', tracking_number: typed, user_id: null }, added);
    // Not a later check, nor a parcel that has progress.
    await unsupported.syncPackage({ id: 'refreshed', carrier: 'unknown', tracking_number: typed, user_id: 'user-1' }, { trigger: 'refresh' });
    await unsupported.syncPackage({ id: 'answered', carrier: 'unknown', tracking_number: typed, current_stage: 'in_transit' }, added);
    const notFound = new TrackingSyncService(client as unknown as SupabaseServiceClient, {
      fetch: vi.fn().mockRejectedValue(new NotFoundError('la-poste', 'Unknown parcel')),
    });
    await notFound.syncPackage({ id: 'not-found', carrier: 'la-poste', tracking_number: typed, user_id: 'user-1' }, added);
    // A first check that stored scans all the same.
    const answering = new TrackingSyncService(client as unknown as SupabaseServiceClient, {
      fetch: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit', last_update: '2026-10-20T08:00:00Z',
        events: [{ time: '2026-10-20T08:00:00Z', description: 'Parcel sorted', stage: 'in_transit' }] }),
    });
    await expect(answering.syncPackage({ id: 'scanned', carrier: 'la-poste', tracking_number: typed, user_id: 'user-1' }, added))
      .resolves.toMatchObject({ updated: 1 });
    expect(counted.mock.calls).toEqual([['s10', 'la-poste', 'account', 'unanswered'], ['s10', 'la-poste', 'lookup', 'unanswered'],
      ['s10', 'la-poste', 'account', 'unanswered'], ['s10', 'la-poste', 'account', 'answered']]);
  });

  it('keeps failed lookup diagnostics in logs without creating an immediate Sentry issue', async () => {
    const parcel = { id: 'unknown-package', carrier: 'unknown', tracking_number: 'TEST1234' };
    const client = fakeClient();
    const error = new UniversalTrackingError([
      { source: '17TRACK', reason: 'history unavailable', error: new TypeError('No matching tracking response') },
      { source: 'ParcelsApp', reason: 'history unavailable', error: new TypeError('Shipment identity missing') },
    ]);
    const output = vi.spyOn(console, 'log').mockImplementation(() => undefined);
    const errors = vi.spyOn(console, 'error').mockImplementation(() => undefined);
    const capture = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, {
      fetch: vi.fn().mockRejectedValue(error),
    });

    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ errors: 1 });
    expect(capture).not.toHaveBeenCalled();
    const logs = [...output.mock.calls, ...errors.mock.calls].map(([line]) => JSON.parse(String(line)));
    expect(logs).toEqual(expect.arrayContaining([
      expect.objectContaining({ event: 'tracking_sync_started', package_id: 'unknown-package', number_pattern: '4 letters + 4 digits' }),
      expect.objectContaining({ event: 'tracking_sync_step', step: 'fetch', step_status: 'failed', package_id: 'unknown-package' }),
      expect.objectContaining({ event: 'tracking_sync_completed', outcome: 'error', package_id: 'unknown-package' }),
    ]));
    expect(JSON.stringify(logs)).not.toContain('TEST1234');
  });

  it('keeps tracking operational when the private audit store is unavailable', async () => {
    const parcel = {
      id: 'package-audit-outage',
      carrier: 'dpd',
      tracking_number: '06080000000002',
      current_stage: 'pending',
    };
    const client = fakeClient();
    client.startSyncAttempt.mockRejectedValue(new Error('audit table unavailable'));
    client.completeSyncAttempt.mockRejectedValue(new Error('audit table unavailable'));
    const adapter: TrackingAdapter = {
      fetch: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit' }),
    };
    vi.spyOn(console, 'error').mockImplementation(() => undefined);
    vi.spyOn(console, 'log').mockImplementation(() => undefined);
    const service = new TrackingSyncService(
      client as unknown as SupabaseServiceClient,
      adapter,
    );

    await expect(service.syncPackage(parcel)).resolves.toMatchObject({ updated: 1, errors: 0 });
    expect(client.updatePackage).toHaveBeenLastCalledWith(
      'package-audit-outage',
      expect.objectContaining({ sync_status: 'ok', current_stage: 'in_transit' }),
    );
  });
});

describe('tracking anomaly detection', () => {
  it('records malformed, future, synthetic, and contradictory carrier evidence', () => {
    const now = new Date('2026-08-31T12:00:00Z');
    expect(detectSyncAnomalies(
      { current_stage: 'delivered' },
      {
        status: 'delivered',
        events: [{ time: 'not-a-time', description: 'Delivered' }],
      },
      [{
        occurred_at: '2026-09-03T12:00:00Z',
        raw_data: { observed_without_provider_timestamp: true },
      }],
      'dpd',
      'in_transit',
      now,
    )).toEqual(expect.arrayContaining([
      'invalid_event_timestamp',
      'future_event_timestamp',
      'observed_without_timestamp',
      'terminal_stage_regression',
      'delivered_status_conflict',
    ]));
  });

  it('flags a scan two hours ahead, the mark of a local clock read as UTC, but not minor skew', () => {
    const now = new Date('2026-06-10T07:00:00Z');
    const ahead = (occurred_at: string) => detectSyncAnomalies({ current_stage: 'in_transit' },
      { status: 'in_transit', events: [] }, [{ occurred_at }], 'spring-gds', 'in_transit', now);
    expect(ahead('2026-06-10T09:15:00Z')).toContain('future_event_timestamp');
    expect(ahead('2026-06-10T07:35:00Z')).not.toContain('future_event_timestamp');
  });

  it('never lets an earlier stage up to accepted replace a later one', () => {
    for (const previous of ['in_transit', 'customs', 'out_for_delivery', 'failed_attempt', 'ready_for_pickup']) {
      for (const selected of ['pending', 'registered', 'accepted']) expect(stageToSave(previous, selected)).toBe(previous);
      expect(stageToSave(previous, 'exception')).toBe('exception');
      expect(stageToSave(previous, 'returned')).toBe('returned');
    }
    expect(stageToSave('accepted', 'registered')).toBe('accepted');
    expect(stageToSave('registered', 'pending')).toBe('registered');
    expect(stageToSave('registered', 'accepted')).toBe('accepted');
    expect(stageToSave('out_for_delivery', 'in_transit')).toBe('in_transit');
    // After a problem or a return, a new label starts over.
    expect(stageToSave('exception', 'registered')).toBe('registered');
    expect(stageToSave('returned', 'registered')).toBe('registered');
  });

  it('accepts a problem reported after delivery without calling it a regression', () => {
    expect(detectSyncAnomalies(
      { current_stage: 'delivered' },
      {
        status: 'exception',
        last_status_text: 'Parcel damaged in transit',
        events: [{ time: '2026-08-31T09:00:00Z', description: 'Parcel damaged in transit' }],
      },
      [{ occurred_at: '2026-08-31T09:00:00Z' }],
      'dpd',
      'exception',
      new Date('2026-08-31T12:00:00Z'),
    )).not.toContain('terminal_stage_regression');
  });

  it('still flags other movement away from a terminal stage', () => {
    expect(detectSyncAnomalies(
      { current_stage: 'delivered' },
      {
        status: 'in_transit',
        last_status_text: 'In transit',
        events: [{ time: '2026-08-31T09:00:00Z', description: 'In transit' }],
      },
      [{ occurred_at: '2026-08-31T09:00:00Z' }],
      'dpd',
      'in_transit',
      new Date('2026-08-31T12:00:00Z'),
    )).toContain('terminal_stage_regression');
  });

  it('flags a progressed parcel when a provider suddenly returns no evidence', () => {
    expect(detectSyncAnomalies(
      { current_stage: 'in_transit' },
      { status: 'unknown', events: [] },
      [],
      'colis-prive',
      null,
      new Date('2026-08-31T12:00:00Z'),
    )).toContain('progress_disappeared');
  });

  it('tells a fallback that knows less from a source that contradicts the saved summary', () => {
    const scan = { time: '2026-08-31T09:00:00Z', description: 'Pending', stage: 'pending' };
    const detect = (carrier: string, carrierData: JsonObject, result: CarrierResult, source: string) => detectSyncAnomalies(
      { carrier, current_stage: 'registered', carrier_data: carrierData }, result, [], source, 'pending', new Date('2026-08-31T12:00:00Z'),
    );
    const fallback: CarrierResult = { tracking_provider: 'ParcelsApp', status: 'pending', events: [scan] };
    // The summary came from the carrier: a provider that knows less is only a fallback.
    expect(detect('dpd', {}, fallback, 'unknown')).toEqual(['fallback_without_progress']);
    expect(detect('dpd', { tracking_provider: 'Ship24' }, fallback, 'unknown')).toEqual(['fallback_without_progress']);
    // The provider the summary came from, and the carrier's own adapter, take their progress back.
    expect(detect('dpd', { tracking_provider: 'ParcelsApp' }, fallback, 'unknown')).toEqual(['progress_disappeared']);
    expect(detect('dpd', { tracking_provider: 'ParcelsApp' }, { status: 'pending', events: [scan] }, 'dpd')).toEqual(['progress_disappeared']);
    // No adapter of its own: routing exhausts the eligible providers before returning a thin answer.
    expect(detect('unknown', { tracking_provider: 'Ship24' }, fallback, 'unknown')).toEqual(['progress_disappeared']);
    expect(detect('intl-post', { tracking_provider: 'Ship24' }, fallback, 'unknown')).toEqual(['progress_disappeared']);
    expect(detect('royal-mail', { tracking_provider: 'Ship24' }, fallback, 'unknown')).toEqual(['fallback_without_progress']);
  });
  it('preserves real movement when a provider has only registration, without flagging a new label', () => {
    const label: CarrierResult = { status: 'pending', current_stage: 'registered', tracking_provider: 'Ship24',
      events: [{ time: '2026-08-31T09:00:00Z', description: 'Information received', stage: 'registered' }] };
    const detect = (stage: string) => detectSyncAnomalies({ carrier: 'unknown', current_stage: stage }, label,
      [], 'unknown', 'registered', new Date('2026-08-31T12:00:00Z'));
    expect(detect('in_transit')).toEqual(['progress_disappeared']);
    expect(detect('pending')).toEqual([]);
    expect(detect('registered')).toEqual([]);
  });
  it('holds an answer only to progress a stored scan shows as the scraper reads it now', () => {
    const label: CarrierResult = { status: 'pending', current_stage: 'registered', tracking_provider: '17TRACK',
      events: [{ time: '2026-08-31T09:00:00Z', description: 'Shipping Label Created', stage: 'registered' }] };
    const relay = { provider_event_id: 'unknown:relay', occurred_at: '2026-08-31T10:00:00Z', stage: 'in_transit',
      description: 'Shipment in Transit to the ePost Global Processing Center' };
    const detect = (rows: JsonObject[]) => detectSyncAnomalies({ carrier: 'intl-post', current_stage: 'in_transit',
      carrier_data: { tracking_provider: 'ParcelsApp' }, [STORED_EVENT_IDENTITIES]: rows }, label, [], 'unknown', 'registered',
    new Date('2026-08-31T12:00:00Z'));
    // ParcelsApp's relay of ePost Global's label, stored as in transit before the scraper read it as the label.
    expect(detect([{ ...relay, universal_provider: 'ParcelsApp' }])).toEqual([]);
    // A carrier's own row keeps its stage, and a provider's row that still reads as moving holds its progress.
    expect(detect([relay])).toEqual(['progress_disappeared']);
    expect(detect([{ ...relay, universal_provider: 'ParcelsApp', description: 'Departed facility' }])).toEqual(['progress_disappeared']);
  });
});

/** The `tracking_sync_anomaly` log lines a spy saw for one anomaly code. */
function anomalyLogs(logged: { mock: { calls: unknown[][] } }, code: string) {
  return () => logged.mock.calls
    .filter(([event, fields]) => event === 'tracking_sync_anomaly' && (fields as JsonObject).anomaly_code === code)
    .map(([, fields]) => fields);
}

/**
 * tracking_events with the RPC's upsert: a stored identity updates its row in
 * place, keeping the row id (and so created_at and push receipts). Postgres
 * refuses one batch that carries an identity twice, and so does this.
 */
function eventStore() {
  const rows = new Map<string, JsonObject>();
  let created = 0;
  const client = fakeClient();
  client.applyTrackingSync.mockImplementation(async (
    parcel: JsonObject, values: JsonObject, events: JsonObject[] = [], descriptions: string[] = [], absorbed: string[] = [],
  ) => {
    const ids = events.map((event) => String(event.provider_event_id));
    if (new Set(ids).size !== ids.length) throw new Error('ON CONFLICT DO UPDATE command cannot affect row a second time');
    for (const event of events) {
      const id = String(event.provider_event_id);
      rows.set(id, { ...event, id: rows.get(id)?.id ?? `row-${++created}` });
    }
    for (const [id, row] of rows) if (descriptions.includes(String(row.description))) rows.delete(id);
    for (const id of absorbed) if (!ids.includes(id)) rows.delete(id);
    await client.updatePackage(parcel.id, values);
    return true;
  });
  return {
    client,
    rows,
    /** What the sync loaders embed: each stored identity, its instant spelled as PostgREST returns it, its stage and wording. */
    identities: () => [...rows.values()].map((row) => ({
      provider_event_id: row.provider_event_id,
      occurred_at: new Date(String(row.occurred_at)).toISOString().replace('.000Z', '+00:00'),
      stage: row.stage,
      description: row.description,
      location: row.location,
      time: (row.raw_data as JsonObject | undefined)?.time,
      provider_code: (row.raw_data as JsonObject | undefined)?.provider_code,
      observed_without_provider_timestamp: (row.raw_data as JsonObject | undefined)?.observed_without_provider_timestamp,
      universal_provider: (row.raw_data as JsonObject | undefined)?.universal_provider,
      local_time: (row.raw_data as JsonObject | undefined)?.local_time,
      time_zone_basis: (row.raw_data as JsonObject | undefined)?.time_zone_basis,
      time_provenance: (row.raw_data as JsonObject | undefined)?.time_provenance,
      provider_time_iso: (row.raw_data as JsonObject | undefined)?.provider_time_iso,
      retimed_from: (row.raw_data as JsonObject | undefined)?.retimed_from,
    })),
    batch: () => (client.applyTrackingSync.mock.calls.at(-1)?.[2] ?? []) as JsonObject[],
  };
}

it.each([false, true])('retains China Post provider history between hourly backfills (local clocks: %s)', async local => {
  vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
  vi.spyOn(scraper, 'universalPlan').mockReturnValue({ sources: ['Ship24'], carrier: null, tier: () => 'unknown', rank: () => 0 });
  const store = eventStore();
  const client = { ...store.client,
    acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
    finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
  const scan = { time: '2026-09-10T11:00:00Z', description: 'In transit', stage: 'in_transit' };
  const older = { time: '2026-09-09T08:00:00Z', description: 'Collected', stage: 'accepted' };
  const provider: CarrierResult = { status: 'in_transit', current_stage: 'in_transit', last_status_text: 'In transit',
    last_update: scan.time, events: [scan, older] };
  const direct: CarrierResult = { ...provider, history_truncated: true, events: [scan] };
  if (local) {
    direct.last_update = null;
    direct.last_update_local = '2026-09-10T11:00:00';
    direct.events = [{ local_time: '2026-09-10T11:00:00', description: scan.description, stage: 'in_transit' }];
  }
  const adapter = { fetch: vi.fn(async () => structuredClone(direct)), fetchUniversal: vi.fn(async () => structuredClone(provider)) };
  let now = new Date('2026-09-10T12:00:00Z');
  const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
  let parcel: JsonObject = { id: 'partial-history', carrier: 'china-post', tracking_number: 'ZA00000000001', current_stage: 'in_transit' };
  const sync = async () => {
    await service.syncPackage({ ...parcel, [STORED_EVENT_IDENTITIES]: store.identities() }, { trigger: 'scheduled' });
    parcel = { ...parcel, ...client.updatePackage.mock.calls.at(-1)![1] };
  };
  await sync();
  expect(adapter.fetchUniversal).toHaveBeenCalledOnce();
  const saved = structuredClone(parcel.carrier_data) as JsonObject;
  const rows = structuredClone([...store.rows.values()]);
  expect(rows).toHaveLength(2);
  now = new Date('2026-09-10T12:15:00Z');
  await sync();
  expect(adapter.fetch).toHaveBeenCalledTimes(2);
  expect(adapter.fetchUniversal).toHaveBeenCalledOnce();
  expect(client.acquireTrackingProvider).toHaveBeenCalledOnce();
  expect([...store.rows.values()]).toEqual(rows);
  const data = parcel.carrier_data as JsonObject;
  expect(data).toMatchObject({ tracking_provider: 'Ship24', last_update: saved.last_update,
    routing: { last_event_at: (saved.routing as JsonObject).last_event_at, last_success_at: now.toISOString(),
      partial_history: { checked_at: '2026-09-10T12:00:00.000Z' }, next_check_at: '2026-09-10T12:30:00.000Z' } });
  expect(parcel.last_status_text).toBe('In transit');
  expect(parcel.current_stage).toBe('in_transit');
  expect(store.batch()).toEqual([]);
  // An older scan uploaded later does not appear in the direct window. The bounded backfill still finds it.
  provider.events!.push({ time: '2026-09-09T10:00:00Z', description: 'Sorted', stage: 'in_transit' });
  now = new Date('2026-09-10T13:00:00Z');
  await sync();
  expect(adapter.fetchUniversal).toHaveBeenCalledTimes(2);
  expect([...store.rows.values()]).toEqual(expect.arrayContaining(rows));
  expect(store.rows.size).toBe(3);
});

it('lets a carrier\'s scan, once dated, take over the row of its day', async () => {
  const store = eventStore();
  const parcel = { id: 'yunexpress-day', user_id: 'owner', carrier: 'yunexpress', tracking_number: 'YT0000000000000001' };
  const departed = { time: '2026-10-12T03:00:00+08:00', description: 'Departed', stage: 'in_transit' };
  const received = { time: '2026-10-11T08:00:00+08:00', description: 'Received', stage: 'accepted' };
  const sorted = { local_time: '2026-10-11T18:20:00', description: 'Sorted', location: 'Shenzhen', stage: 'in_transit' };
  const answer = (events: JsonObject[]) => ({ status: 'in_transit', current_stage: 'in_transit', last_status_text: 'Departed',
    last_update: departed.time, events });
  const adapter = { fetch: vi.fn()
    .mockResolvedValueOnce(answer([departed, sorted, received]))
    // A release dates the scan on its wall clock, in its zone.
    .mockResolvedValue(answer([departed, { ...sorted, time: '2026-10-11T18:20:00+08:00' }, received])) };
  const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null,
    () => new Date('2026-10-12T08:00:00Z'));
  const sync = (stage: string) => service.syncPackage({ ...parcel, current_stage: stage, [STORED_EVENT_IDENTITIES]: store.identities() });

  await sync('pending');
  const day = providerEventId('yunexpress', '2026-10-11T18:20:00', 'Shenzhen', 'Sorted');
  expect(store.rows.get(day)).toMatchObject({ occurred_at: '2026-10-11T00:00:01Z' });
  const row = store.rows.get(day)!.id;
  await sync('in_transit');
  await sync('in_transit');
  // The same row, at the scan's instant: nothing new to announce.
  expect(store.batch().map((event) => event.provider_event_id)).toContain(day);
  expect(store.rows.size).toBe(3);
  expect(store.rows.get(day)).toMatchObject({ id: row, occurred_at: '2026-10-11T10:20:00Z' });
});

it('fills in a UPS scan location without creating another notification event', async () => {
  const store = eventStore();
  const at = '2026-07-11T18:05:00Z';
  const result = (location: string) => ({ status: 'accepted', current_stage: 'accepted', last_update: at,
    last_status_text: 'Package collected', events: [{ time: at, stage: 'accepted', description: 'Package collected', location }] });
  const adapter = { fetch: vi.fn().mockResolvedValueOnce(result('')).mockResolvedValue(result('Example City, France')) };
  const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null,
    () => new Date('2026-07-12T12:00:00Z'));
  const load = () => ({ id: 'ups-parcel', user_id: 'owner', carrier: 'ups', tracking_number: '1Z0000000000000000',
    current_stage: 'accepted', [STORED_EVENT_IDENTITIES]: store.identities() });
  await service.syncPackage(load());
  const original = [...store.rows.values()][0]!;
  await service.syncPackage(load());
  await service.syncPackage(load());
  expect(store.rows.size).toBe(1);
  expect([...store.rows.values()][0]).toMatchObject({ id: original.id, provider_event_id: original.provider_event_id,
    location: 'Example City, France' });
});

describe('scans restaged into news', () => {
  const now = new Date('2026-07-11T18:40:00Z');
  const stored = [
    { provider_event_id: 'ups:collected', stage: 'accepted', occurred_at: '2026-07-11T16:16:00+00:00' },
    { provider_event_id: 'ups:notice', stage: 'ready_for_pickup', occurred_at: '2026-07-11T09:00:00+00:00' },
  ];
  const restaged = (stage: string, previousStage = 'ready_for_pickup', at = now, others: JsonObject[] = []) => restagedNews(
    [...stored, ...others],
    [{ provider_event_id: 'ups:collected', stage, occurred_at: '2026-07-11T16:16:00.000Z' }, { ...stored[1]! }],
    previousStage, at,
  );

  it('names a stored scan turned into a delivery the parcel had not shown', () => {
    expect(restaged('delivered')).toEqual(['ups:collected']);
    expect(restaged('ready_for_pickup', 'in_transit', now, [])).toEqual([]);
    expect(restagedNews([stored[0]!], [{ ...stored[0]!, stage: 'ready_for_pickup' }], 'in_transit', now)).toEqual(['ups:collected']);
  });

  it('leaves out other stages, stages already shown, new scans and scans over a day old', () => {
    expect(restaged('in_transit')).toEqual([]);
    expect(restaged('accepted')).toEqual([]);
    expect(restaged('delivered', 'delivered')).toEqual([]);
    expect(restaged('delivered', 'ready_for_pickup', now,
      [{ provider_event_id: 'ups:earlier', stage: 'delivered', occurred_at: '2026-07-11T12:00:00+00:00' }])).toEqual([]);
    expect(restaged('delivered', 'ready_for_pickup', new Date('2026-07-12T16:17:00Z'))).toEqual([]);
    expect(restagedNews(stored, [{ provider_event_id: 'ups:new', stage: 'delivered', occurred_at: '2026-07-11T18:00:00Z' }],
      'ready_for_pickup', now)).toEqual([]);
  });
});

it('announces once a stored scan the carrier restages into a delivery', async () => {
  const store = eventStore();
  const notice = { time: '2026-07-11T09:00:00Z', stage: 'ready_for_pickup', description: 'Waiting at the parcel shop' };
  const reply = (stage: string) => ({ status: stage, current_stage: stage, last_update: '2026-07-11T16:16:00Z',
    last_status_text: 'Collected from the parcel shop',
    events: [{ time: '2026-07-11T16:16:00Z', stage, description: 'Collected from the parcel shop' }, notice] });
  const adapter = { fetch: vi.fn().mockResolvedValueOnce(reply('accepted')).mockResolvedValue(reply('delivered')) };
  let now = new Date('2026-07-11T16:20:00Z');
  const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null, () => now);
  let stage = 'ready_for_pickup';
  const load = () => ({ id: 'collected-parcel', user_id: 'owner', carrier: 'ups', tracking_number: '1Z0000000000000000',
    current_stage: stage, [STORED_EVENT_IDENTITIES]: store.identities() });
  // First stored as accepted: the parcel stays ready for pickup, and nothing is reopened.
  await expect(service.syncPackage(load())).resolves.toMatchObject({ updated: 1 });
  expect(store.client.updatePackage.mock.calls.at(-1)![1].current_stage).toBe('ready_for_pickup');
  const collected = [...store.rows.values()].find((row) => row.description === 'Collected from the parcel shop')!;
  expect(store.client.reopenScanAnnouncements).not.toHaveBeenCalled();

  now = new Date('2026-07-11T18:40:00Z');
  await expect(service.syncPackage(load())).resolves.toMatchObject({ updated: 1 });
  expect(store.rows.size).toBe(2);
  expect(store.client.reopenScanAnnouncements).toHaveBeenCalledExactlyOnceWith('collected-parcel', [collected.provider_event_id]);
  const steps = store.client.completeSyncAttempt.mock.calls.at(-1)![2] as JsonObject[];
  expect(steps.find((step) => step.step === 'persist_events')!.details).toMatchObject({ events_new: 0, scans_reannounced: 1 });
  stage = 'delivered';
  await expect(service.syncPackage(load())).resolves.toMatchObject({ unchanged: 1 });
  expect(store.client.reopenScanAnnouncements).toHaveBeenCalledOnce();
});

it('keeps a check whose restaged scan cannot be announced again, and reports it', async () => {
  const report = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
  const store = eventStore();
  const failure = new Error('receipts unavailable');
  store.client.reopenScanAnnouncements.mockRejectedValue(failure);
  const reply = (stage: string) => ({ status: stage, current_stage: stage, last_update: '2026-07-11T16:16:00Z',
    last_status_text: 'Collected from the parcel shop',
    events: [{ time: '2026-07-11T16:16:00Z', stage, description: 'Collected from the parcel shop' }] });
  const adapter = { fetch: vi.fn().mockResolvedValueOnce(reply('accepted')).mockResolvedValue(reply('delivered')) };
  const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null,
    () => new Date('2026-07-11T18:40:00Z'));
  const load = () => ({ id: 'collected-parcel', user_id: 'owner', carrier: 'ups', tracking_number: '1Z0000000000000000',
    current_stage: 'ready_for_pickup', [STORED_EVENT_IDENTITIES]: store.identities() });
  await service.syncPackage(load());
  await expect(service.syncPackage(load())).resolves.toMatchObject({ updated: 1, errors: 0 });
  expect(store.client.reopenScanAnnouncements).toHaveBeenCalledOnce();
  expect([...store.rows.values()][0]).toMatchObject({ stage: 'delivered' });
  const steps = store.client.completeSyncAttempt.mock.calls.at(-1)![2] as JsonObject[];
  expect(steps.find((step) => step.step === 'persist_events')!.details)
    .toMatchObject({ scans_reannounced: 0, reannouncement_failed: true });
  expect(report).toHaveBeenCalledExactlyOnceWith(failure, expect.objectContaining({
    component: 'tracking-sync', operation: 'reopen_announcements',
  }));
});

it('rewrites a scan in place when a take-over left its row under an older identity', async () => {
  const store = eventStore();
  const at = '2026-07-11T18:05:00Z';
  const sorted = { time: at, stage: 'in_transit', description: 'Sorted', location: 'Example City' };
  const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'in_transit', last_update: at,
    last_status_text: 'Sorted', events: [sorted] }) };
  const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null,
    () => new Date('2026-07-12T12:00:00Z'));
  store.rows.set('dhl:taken-over', { id: 'row-0', package_id: 'restated-parcel', provider_event_id: 'dhl:taken-over',
    occurred_at: at, stage: 'in_transit', description: 'Sorted', location: 'Example City', raw_data: { time: at } });
  const load = () => ({ id: 'restated-parcel', user_id: 'owner', carrier: 'dhl', tracking_number: 'TEST1234',
    current_stage: 'in_transit', [STORED_EVENT_IDENTITIES]: store.identities() });
  await expect(service.syncPackage(load())).resolves.toMatchObject({ updated: 0, unchanged: 1 });
  await service.syncPackage(load());
  expect([...store.rows.values()].map(({ id, provider_event_id }) => ({ id, provider_event_id })))
    .toEqual([{ id: 'row-0', provider_event_id: 'dhl:taken-over' }]);
});

it('rewrites in place a scan stored with a placeholder of the carrier\'s template left in', async () => {
  const store = eventStore();
  const at = '2026-10-21T07:41:00Z';
  const available = { time: at, stage: 'ready_for_pickup', description: 'Item available at the bbox locker', location: '' };
  const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'ready_for_pickup', current_stage: 'ready_for_pickup',
    last_update: at, last_status_text: available.description, events: [available] }) };
  const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null,
    () => new Date('2026-10-21T12:00:00Z'));
  store.rows.set('bpost:placeholder', { id: 'row-0', package_id: 'placeholder-parcel', provider_event_id: 'bpost:placeholder',
    occurred_at: at, stage: 'in_transit', description: 'Item available at the {{bboxRebrand}}', location: '', raw_data: { time: at } });
  const load = () => ({ id: 'placeholder-parcel', user_id: 'owner', carrier: 'bpost', tracking_number: 'TEST1234',
    current_stage: 'in_transit', [STORED_EVENT_IDENTITIES]: store.identities() });
  await service.syncPackage(load());
  await service.syncPackage(load());
  expect([...store.rows.values()].map(({ id, provider_event_id, description }) => ({ id, provider_event_id, description })))
    .toEqual([{ id: 'row-0', provider_event_id: 'bpost:placeholder', description: 'Item available at the bbox locker' }]);
});

it('rewrites in place a scan whose place gains a qualifier and one stored with an adapter\'s HTML in its words', async () => {
  const store = eventStore();
  const arrived = { time: '2026-10-14T22:10:00Z', stage: 'in_transit', description: 'Arrived at the hub', location: 'Example Town, EX' };
  const card = { time: '2026-10-15T02:41:00Z', stage: 'failed_attempt', description: 'Item left at a depot CARD LEFT', location: '' };
  const adapter = { fetch: vi.fn().mockResolvedValue({ status: 'failed_attempt', current_stage: 'failed_attempt',
    last_update: card.time, last_status_text: card.description, events: [arrived, card] }) };
  const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null,
    () => new Date('2026-10-15T12:00:00Z'));
  store.rows.set('yunexpress:town', { id: 'row-0', package_id: 'restated-parcel', provider_event_id: 'yunexpress:town',
    occurred_at: arrived.time, stage: 'in_transit', description: arrived.description, location: 'Example Town', raw_data: { time: arrived.time } });
  store.rows.set('yunexpress:markup', { id: 'row-1', package_id: 'restated-parcel', provider_event_id: 'yunexpress:markup',
    occurred_at: card.time, stage: 'in_transit', description: "Item left at a depot <b><a href='/Contact'>CARD LEFT</a></b>", location: '',
    raw_data: { time: card.time } });
  const load = () => ({ id: 'restated-parcel', user_id: 'owner', carrier: 'yunexpress', tracking_number: 'TEST1234',
    current_stage: 'in_transit', [STORED_EVENT_IDENTITIES]: store.identities() });
  await service.syncPackage(load());
  await service.syncPackage(load());
  expect([...store.rows.values()].map(({ id, provider_event_id, stage, description, location }) => ({ id, provider_event_id, stage, description, location })))
    .toEqual([
      { id: 'row-0', provider_event_id: 'yunexpress:town', stage: 'in_transit', description: 'Arrived at the hub', location: 'Example Town, EX' },
      { id: 'row-1', provider_event_id: 'yunexpress:markup', stage: 'failed_attempt', description: card.description, location: null },
    ]);
});

it('moves the row of a scan its carrier restates a little later, and keeps the next day\'s scan in the same words', async () => {
  const store = eventStore();
  // A carrier moves a departure an hour later and lists only the later time; a day later it lists a new departure
  // from the same place in the same words, and only that one (synthetic place and times).
  const scan = (time: string, description: string) => ({ time, stage: 'in_transit', description, location: 'Example City Hub' });
  const result = (...events: ReturnType<typeof scan>[]) => ({ status: 'in_transit', current_stage: 'in_transit',
    last_update: events.at(-1)!.time, last_status_text: events.at(-1)!.description, events });
  const arrived = scan('2026-10-22T21:56:00-07:00', 'Arrived at Facility');
  const departed = scan('2026-10-23T01:55:00-07:00', 'Departed Facility');
  const later = { ...departed, time: '2026-10-23T03:00:00-07:00' };
  const again = { ...departed, time: '2026-10-23T04:00:00-07:00' };
  const nextDay = { ...departed, time: '2026-10-24T02:10:00-07:00' };
  const adapter = { fetch: vi.fn()
    .mockResolvedValueOnce(result(arrived, departed))
    .mockResolvedValueOnce(result(arrived, later))
    .mockResolvedValueOnce(result(arrived, later))
    .mockResolvedValueOnce(result(arrived, again))
    .mockResolvedValue(result(arrived, nextDay)) };
  const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null,
    () => new Date('2026-10-24T12:00:00Z'));
  const load = () => ({ id: 'retimed-parcel', user_id: 'owner', carrier: 'dhl', tracking_number: 'TEST1234',
    current_stage: store.rows.size ? 'in_transit' : 'pending', [STORED_EVENT_IDENTITIES]: store.identities() });
  const departures = () => [...store.rows.values()].filter((row) => row.description === 'Departed Facility')
    .map(({ id, occurred_at, raw_data }) => ({ id, at: new Date(String(occurred_at)).toISOString(),
      ...((raw_data as JsonObject).retimed_from ? { first: (raw_data as JsonObject).retimed_from } : {}) }));

  await service.syncPackage(load());
  expect(departures()).toEqual([{ id: 'row-2', at: '2026-10-23T08:55:00.000Z' }]);
  // The departure's row moves to the later time in place: no second row, nothing announced again.
  // It keeps the time it was first stored with, so it does not read as a scan stored ahead of its time.
  const moved = { id: 'row-2', at: '2026-10-23T10:00:00.000Z', first: departed.time };
  await expect(service.syncPackage(load())).resolves.toMatchObject({ updated: 0, unchanged: 1 });
  expect(departures()).toEqual([moved]);
  await expect(service.syncPackage(load())).resolves.toMatchObject({ updated: 0, unchanged: 1 });
  expect(departures()).toEqual([moved]);
  // Moved again, it keeps its first time.
  await expect(service.syncPackage(load())).resolves.toMatchObject({ updated: 0, unchanged: 1 });
  expect(departures()).toEqual([{ ...moved, at: '2026-10-23T11:00:00.000Z' }]);
  // A day later it is another scan, and both stay.
  await expect(service.syncPackage(load())).resolves.toMatchObject({ updated: 1, unchanged: 0 });
  expect(departures()).toEqual([{ ...moved, at: '2026-10-23T11:00:00.000Z' }, { id: 'row-3', at: '2026-10-24T09:10:00.000Z' }]);
});

it('tells a check that stored a scan from one that rewrote the scans it had', async () => {
  const store = eventStore();
  const scan = (time: string, description: string, location = '') => ({ time, stage: 'in_transit', description, location });
  const result = (...events: ReturnType<typeof scan>[]) => ({ status: 'in_transit', current_stage: 'in_transit',
    last_update: events.at(-1)!.time, last_status_text: events.at(-1)!.description, events });
  const sorted = scan('2026-07-11T18:05:00Z', 'Sorted');
  const adapter = { fetch: vi.fn()
    .mockResolvedValueOnce(result(sorted))
    .mockResolvedValueOnce(result({ ...sorted, location: 'Example City' }))
    .mockResolvedValue(result({ ...sorted, location: 'Example City' }, scan('2026-07-12T08:00:00Z', 'Departed'))) };
  const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null,
    () => new Date('2026-07-12T12:00:00Z'));
  const load = () => ({ id: 'news-parcel', user_id: 'owner', carrier: 'ups', tracking_number: '1Z0000000000000001',
    current_stage: store.rows.size ? 'in_transit' : 'pending', [STORED_EVENT_IDENTITIES]: store.identities() });
  const completion = () => store.client.completeSyncAttempt.mock.calls.at(-1)![1] as JsonObject;
  const persisted = () => (store.client.completeSyncAttempt.mock.calls.at(-1)![2] as JsonObject[])
    .find((step) => step.step === 'persist_events')!.details;

  await expect(service.syncPackage(load())).resolves.toMatchObject({ updated: 1, unchanged: 0 });
  expect(completion()).toMatchObject({ outcome: 'updated', events_new: 1 });
  // The location fills in on the stored row: nothing to tell.
  await expect(service.syncPackage(load())).resolves.toMatchObject({ updated: 0, unchanged: 1 });
  expect(completion()).toMatchObject({ outcome: 'unchanged', events_new: 0 });
  expect(persisted()).toMatchObject({ events_persisted: 1, events_new: 0 });
  await expect(service.syncPackage(load())).resolves.toMatchObject({ updated: 1, unchanged: 0 });
  expect(completion()).toMatchObject({ outcome: 'updated', events_new: 1 });
  expect(persisted()).toMatchObject({ events_persisted: 2, events_new: 1 });
  expect(store.rows.size).toBe(2);
});

it('enriches a saved flight in place instead of storing a second event for notifications', async () => {
  const store = eventStore();
  const at = '2026-07-11T20:05:00+02:00';
  const flight = { time: at, stage: 'in_transit', provider_code: 'AircraftTakeOff' };
  const result = (description: string, location: string) => ({ status: 'in_transit', current_stage: 'in_transit',
    last_update: at, last_status_text: description, events: [{ ...flight, description, location }] });
  const rich = 'Flight ZZ0101 departed: Frankfurt Airport (FRA) → Paris Charles de Gaulle Airport (CDG)';
  const adapter = { fetch: vi.fn().mockResolvedValueOnce(result('UPLIFT', 'Office - FRA 000000'))
    .mockResolvedValue(result(rich, 'Frankfurt Airport (FRA), Germany')) };
  const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null,
    () => new Date('2026-07-12T12:00:00Z'));
  const load = () => ({ id: 'flight-parcel', user_id: 'owner', carrier: 'india-post', tracking_number: 'JN067614884IN',
    current_stage: 'in_transit', [STORED_EVENT_IDENTITIES]: store.identities() });
  await service.syncPackage(load());
  const original = [...store.rows.values()][0]!;
  await service.syncPackage(load());
  await service.syncPackage(load());
  expect(store.rows.size).toBe(1);
  expect([...store.rows.values()][0]).toMatchObject({ id: original.id, provider_event_id: original.provider_event_id,
    description: rich, location: 'Frankfurt Airport (FRA), Germany', occurred_at: '2026-07-11T18:05:00Z' });
});

it('enriches coded India Post scans sharing a clock without creating notification events', async () => {
  const store = eventStore();
  const at = '2026-07-11T18:05:00Z';
  const result = (location: string) => ({ status: 'in_transit', current_stage: 'in_transit', last_update: at,
    last_status_text: 'Item received', events: [
      { time: at, stage: 'in_transit', description: 'Arrived at sorting centre', provider_code: 'MailArrived', location },
      { time: at, stage: 'in_transit', description: 'Item received', provider_code: 'ItemReceived', location },
    ] });
  const adapter = { fetch: vi.fn().mockResolvedValueOnce(result('Example Office 000000'))
    .mockResolvedValue(result('Example Office 000001')) };
  const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null,
    () => new Date('2026-07-12T12:00:00Z'));
  const load = () => ({ id: 'coded-parcel', user_id: 'owner', carrier: 'india-post', tracking_number: 'JN067614884IN',
    current_stage: 'in_transit', [STORED_EVENT_IDENTITIES]: store.identities() });
  await service.syncPackage(load());
  const originals = [...store.rows.values()].map(({ id, provider_event_id }) => ({ id, provider_event_id }));
  await service.syncPackage(load());
  await service.syncPackage(load());
  expect(store.rows.size).toBe(2);
  expect([...store.rows.values()].map(({ id, provider_event_id }) => ({ id, provider_event_id }))).toEqual(originals);
  expect([...store.rows.values()].map((row) => row.location)).toEqual(['Example Office 000001', 'Example Office 000001']);
});

describe('India Post scans abroad', () => {
  // La Poste's 07:50 in Paris, first relayed as India's 07:50 (02:20 UTC).
  const NUMBER = 'JN067614884IN';
  const transfer = { time: '2026-07-10T08:15:00Z', stage: 'in_transit', description: 'Transferred to Office of Exchange',
    provider_code: 'ItemTransfered', location: 'EXAMPLE FOREIGN POST OFFICE 999001' };
  const india = (time: string, description: string, destination?: string): CarrierResult => ({
    status: 'in_transit', current_stage: 'in_transit', last_update: time, last_status_text: description, timezone: 'Asia/Kolkata',
    ...(destination ? { destination_country: destination } : {}),
    events: [{ time, stage: 'in_transit', description, provider_code: 'ItemReceived', location: 'EXAMPLE EXCHANGE OFFICE 999001' }, transfer],
  });
  const labelled = () => india('2026-07-14T02:20:00Z', 'Item Received');
  const abroad = (destination?: string) => india('2026-07-14T07:50:00+02:00', 'Item received at office of exchange (Inb)', destination);
  const load = (store: ReturnType<typeof eventStore>, changes: JsonObject = {}) => ({ id: 'abroad-parcel', user_id: 'owner',
    carrier: 'india-post', tracking_number: NUMBER, current_stage: 'in_transit', ...changes, [STORED_EVENT_IDENTITIES]: store.identities() });
  const rows = (store: ReturnType<typeof eventStore>) => [...store.rows.values()]
    .map(({ id, provider_event_id, occurred_at }) => ({ id, provider_event_id, occurred_at }));

  it('moves a stored scan to its own clock in place, and keeps one row when a reply reads it as labelled again', async () => {
    const store = eventStore();
    const adapter = { fetch: vi.fn().mockResolvedValueOnce(labelled()).mockResolvedValueOnce(abroad()).mockResolvedValue(labelled()) };
    const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-07-15T12:00:00Z'));
    await service.syncPackage(load(store));
    const [received, transferred] = rows(store);
    await service.syncPackage(load(store));
    expect(rows(store)).toEqual([{ ...received, occurred_at: eventTimestamp('2026-07-14T07:50:00+02:00', 'UTC') }, transferred]);
    expect(store.batch().map((event) => event.provider_event_id)).toEqual([received!.provider_event_id, transferred!.provider_event_id]);
    await service.syncPackage(load(store));
    expect(rows(store)).toEqual([received, transferred]);
  });

  it('moves the stored scan in place in the sync that hands the item to La Poste', async () => {
    const store = eventStore();
    const delivery: CarrierResult = { status: 'in_transit', current_stage: 'in_transit', last_update: '2026-07-14T08:30:00+02:00',
      last_status_text: 'Votre colis est en transit.', events: [{ time: '2026-07-14T08:30:00+02:00', stage: 'in_transit',
        description: 'Votre colis est en transit.', location: 'Example Hub' }] };
    let origin = labelled();
    const adapter = { fetch: vi.fn(async (carrier: string) => carrier === 'la-poste' ? delivery : origin) };
    const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-07-15T12:00:00Z'));
    await service.syncPackage(load(store));
    const [received, transferred] = rows(store);
    origin = abroad('FR');
    await service.syncPackage(load(store, { carrier_data: store.client.updatePackage.mock.calls.at(-1)![1].carrier_data }));
    expect(adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['india-post', 'india-post', 'la-poste']);
    expect(store.client.updatePackage.mock.calls.at(-1)![1].carrier_data).toMatchObject({
      active_tracking_carrier: 'la-poste', active_tracking_number: NUMBER, original_carrier: 'india-post' });
    expect(rows(store).slice(0, 2)).toEqual([
      { ...received, occurred_at: eventTimestamp('2026-07-14T07:50:00+02:00', 'UTC') }, transferred]);
    expect(rows(store)).toHaveLength(3);
  });
});

describe('DHL Express records from either feed', () => {
  // The mobile API reads a customs record's wall clock on the facility's +02:00 and gives no
  // code; the global portal labels the same wall clock +01:00 and gives the record's code.
  const pickup = { time: '2026-10-04T08:00:00+02:00', stage: 'in_transit', description: 'Shipment picked up', location: 'EXAMPLE TOWN - ITALY' };
  const answer = (time: string, providerCode?: string): CarrierResult => ({ status: 'in_transit', current_stage: 'customs',
    last_update: time, last_status_text: 'Customs clearance status updated', events: [{ time, stage: 'customs',
      description: 'Customs clearance status updated', location: 'EXAMPLE CITY - ITALY', ...(providerCode ? { provider_code: providerCode } : {}) }, pickup] });
  const mobile = () => answer('2026-10-05T11:00:00+02:00');
  const portal = () => answer('2026-10-05T11:00:00+01:00', 'RR');
  const load = (store: ReturnType<typeof eventStore>) => ({ id: 'dhl-express-parcel', user_id: 'owner', carrier: 'dhl-express',
    tracking_number: '1234067892', current_stage: 'in_transit', [STORED_EVENT_IDENTITIES]: store.identities() });
  const rows = (store: ReturnType<typeof eventStore>) => [...store.rows.values()]
    .map(({ id, provider_event_id, occurred_at }) => ({ id, provider_event_id, occurred_at }));

  it('keeps one row for a record the other feed stored, on the clock of the feed that answered last', async () => {
    const store = eventStore();
    const adapter = { fetch: vi.fn().mockResolvedValueOnce(mobile()).mockResolvedValue(portal()) };
    const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-10-06T12:00:00Z'));
    await service.syncPackage(load(store));
    const [customs, pickedUp] = rows(store);
    expect(customs).toMatchObject({ occurred_at: eventTimestamp('2026-10-05T11:00:00+02:00', 'UTC') });
    await service.syncPackage(load(store));
    await service.syncPackage(load(store));
    expect(rows(store)).toEqual([{ ...customs, occurred_at: eventTimestamp('2026-10-05T11:00:00+01:00', 'UTC') }, pickedUp]);
  });
});

describe('reworded DPD scans', () => {
  // The fixtures' synthetic number: a DPD reply must name the parcel asked for.
  const NUMBER = '06080000000001';
  const unverified = () => carrierResult('dpd-unverified');
  const verified = () => carrierResult('dpd-delivered');
  const now = () => new Date('2026-07-16T12:00:00Z');
  const parcel = { id: 'dpd-postcode-later', user_id: 'owner', carrier: 'dpd', tracking_number: NUMBER };
  const ids = (events: JsonObject[]) => events.map((event) => String(event.provider_event_id));

  it('updates the stored scans in place when the postcode is added later', async () => {
    const store = eventStore();
    const adapter = { fetch: vi.fn().mockResolvedValueOnce(unverified()).mockResolvedValue(verified()) };
    const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null, now);
    const load = (changes: JsonObject) => ({ ...parcel, ...changes, [STORED_EVENT_IDENTITIES]: store.identities() });

    await service.syncPackage(load({ current_stage: 'pending', dpd_postcode: null }));
    const unverifiedIds = ids(store.batch());
    expect(unverifiedIds).toHaveLength(4);
    const rowIds = unverifiedIds.map((id) => store.rows.get(id)?.id);
    // A proof-of-delivery row stored before the parser dropped that scan.
    const proof = {
      package_id: parcel.id, provider_event_id: providerEventId('dpd', '2026-07-16T10:41:30+02:00', '', 'We received the proof of delivery'),
      occurred_at: '2026-07-16T08:41:30Z', description: 'We received the proof of delivery', id: 'row-proof',
    };
    store.rows.set(proof.provider_event_id, proof);

    await service.syncPackage(load({ current_stage: 'delivered', dpd_postcode: '8000' }));
    const customs = providerEventId('dpd', '2026-07-15T16:30:00+02:00', '', 'Your parcel cleared customs successfully');
    // Newest first: DEY, DLO, DLI and ORI keep the identities of their unverified twins.
    expect(ids(store.batch())).toEqual([...unverifiedIds, customs]);
    expect(store.client.applyTrackingSync.mock.calls.at(-1)?.[3]).toEqual([]);
    expect(store.rows.size).toBe(6);
    expect(unverifiedIds.map((id) => store.rows.get(id)?.id)).toEqual(rowIds);
    expect(store.rows.get(unverifiedIds[0]!)).toMatchObject({
      description: 'Your parcel has been delivered successfully', location: 'Urdorf, CH', stage: 'delivered',
    });
    expect(store.rows.get(proof.provider_event_id)).toEqual(proof);
    // The depot-arrival wording is still DPD's to review, sampled from the row it updated.
    expect(store.client.recordTrackingStatusObservations.mock.calls.at(-1)?.[0]).toEqual([
      expect.objectContaining({ carrier: 'dpd', provider_code: 'ORI', provider_event_id: unverifiedIds[3] }),
    ]);

    // The next verified reply finds the reused identities again.
    await service.syncPackage(load({ current_stage: 'delivered', dpd_postcode: '8000' }));
    expect(ids(store.batch())).toEqual([...unverifiedIds, customs]);
    expect(store.rows.size).toBe(6);
    expect(store.client.recordTrackingStatusObservations.mock.calls.at(-1)?.[0]).toEqual([
      expect.objectContaining({ provider_event_id: unverifiedIds[3] }),
    ]);
  });

  it('shares one row per scan with a universal copy, in the carrier\'s words once it answers', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-07-16T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const universalCopy: CarrierResult = {
      status: 'delivered', current_stage: 'delivered', last_status_text: 'Delivered to recipient',
      last_update: '2026-07-16T08:12:00Z',
      events: [
        { time: '2026-07-16T08:12:00Z', location: 'Urdorf', description: 'Delivered to recipient', stage: 'delivered' },
        { time: '2026-07-16T04:10:45Z', location: 'Urdorf', description: 'With delivery courier', stage: 'out_for_delivery' },
        { time: '2026-07-16T01:48:00Z', location: 'Urdorf', description: 'Arrived at delivery facility', stage: 'in_transit' },
        { time: '2026-07-15T16:05:12Z', location: 'Urdorf', description: 'Arrived at facility', stage: 'in_transit' },
      ],
    };
    const down = new Error('DPD guest API unavailable');
    const adapter = {
      fetch: vi.fn()
        .mockRejectedValueOnce(down)
        .mockResolvedValueOnce(unverified())
        .mockRejectedValueOnce(down)
        .mockResolvedValueOnce(verified())
        .mockRejectedValueOnce(down),
      fetchUniversal: vi.fn().mockResolvedValue(universalCopy),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
    // Each check starts from a fresh snapshot, so the router asks DPD first every time.
    const sync = async (dpdPostcode: string | null) => {
      await service.syncPackage({ ...parcel, current_stage: store.rows.size ? 'delivered' : 'pending',
        dpd_postcode: dpdPostcode, [STORED_EVENT_IDENTITIES]: store.identities() });
      return ids(store.batch());
    };

    const universalIds = await sync(null);
    expect(universalIds).toHaveLength(4);
    expect(universalIds.every((id) => id.startsWith('unknown:'))).toBe(true);
    const rowIds = universalIds.map((id) => store.rows.get(id)?.id);

    // DPD takes over the universal rows at the same instants.
    expect(await sync(null)).toEqual(universalIds);
    expect(store.rows.get(universalIds[0]!)).toMatchObject({ description: 'Delivered', location: null });
    // The universal copy finds its own identities in DPD's words and leaves them.
    expect(await sync(null)).toEqual([]);
    expect(store.rows.get(universalIds[0]!)).toMatchObject({ description: 'Delivered', location: null });
    // The verified reply takes them over again; only its customs scan is new.
    const verifiedIds = await sync('8000');
    expect(verifiedIds.slice(0, 4)).toEqual(universalIds);
    expect(verifiedIds[4]).toBe(providerEventId('dpd', '2026-07-15T16:30:00+02:00', '', 'Your parcel cleared customs successfully'));
    expect(await sync('8000')).toEqual([]);

    expect(adapter.fetchUniversal).toHaveBeenCalledTimes(3);
    expect(store.rows.size).toBe(5);
    expect(universalIds.map((id) => store.rows.get(id)?.id)).toEqual(rowIds);
  });
});

describe('scans a carrier and a universal provider both report', () => {
  type Scan = { time: string; description: string; stage: string; location?: string };
  const now = () => new Date('2026-01-05T12:00:00Z');
  const parcel = { id: 'gofo-fallback', user_id: 'owner', carrier: 'gofo', tracking_number: 'GFUS00000000000001' };
  const ids = (events: JsonObject[]) => events.map((event) => String(event.provider_event_id));
  // GOFO's own lookup gives Eastern scans their real offset.
  const label: Scan = { time: '2026-01-02T04:30:00-08:00', description: 'Shipping Label Created', stage: 'registered' };
  const hub: Scan = { time: '2026-01-03T08:15:42-05:00', location: 'Hub City, EX', description: 'Arrived at GOFO Regional Hub', stage: 'in_transit' };
  const out: Scan = { time: '2026-01-04T07:59:03-05:00', location: 'Station City, EX', description: 'Out for Delivery', stage: 'out_for_delivery' };
  const delivered: Scan = { time: '2026-01-04T16:58:30-05:00', location: 'Example City, EX', description: 'Delivered', stage: 'delivered' };
  const result = (scans: Scan[]): CarrierResult => ({
    status: scans[0]!.stage as CarrierResult['status'], current_stage: scans[0]!.stage,
    last_status_text: scans[0]!.description, last_update: scans[0]!.time, events: scans,
  });
  // A universal copy keeps the local clock with a Pacific offset and no place: Eastern scans read three hours late.
  const copied = (scan: Scan): Scan => ({ time: scan.time.replace('-05:00', '-08:00'), description: scan.description, stage: scan.stage });
  const own = (scan: Scan) => providerEventId('gofo', scan.time, scan.location ?? '', scan.description);
  const copy = (scan: Scan) => providerEventId('unknown', copied(scan).time, '', scan.description);

  it('counts the scans of a carrier answer the stored rows lack, a copy of a row adding none', () => {
    // What the sync loaders embed for rows built from an answer.
    const stored = (rows: JsonObject[]) => rows.map((row) => {
      const raw = (row.raw_data ?? {}) as JsonObject;
      return { provider_event_id: row.provider_event_id, occurred_at: new Date(String(row.occurred_at)).toISOString().replace('.000Z', '+00:00'),
        stage: row.stage, description: row.description, location: row.location, time: raw.time, provider_code: raw.provider_code,
        universal_provider: raw.universal_provider, local_time: raw.local_time };
    });
    const withRows = { ...parcel, [STORED_EVENT_IDENTITIES]: stored(buildEvents(parcel, result([hub, label].map(copied)), 'unknown')) };
    const answer = (scans: Scan[]) => ({ sourceCarrierId: 'gofo', result: result(scans) }) as RoutedResult;
    expect(scansMissing(withRows, answer([hub, label]))).toBe(0);
    expect(scansMissing(withRows, answer([out, hub, label]))).toBe(1);
    expect(scansMissing({ ...parcel, [STORED_EVENT_IDENTITIES]: [] }, answer([hub, label]))).toBe(2);
    // A parcel loaded without its rows tells nothing.
    expect(scansMissing(parcel, answer([hub, label]))).toBeUndefined();
  });

  it('stores each scan once and keeps the carrier clock across a fallback', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const down = new Error('GOFO tracking endpoint is unavailable');
    const adapter = {
      fetch: vi.fn()
        .mockResolvedValueOnce(result([hub, label]))
        .mockRejectedValueOnce(down)
        .mockResolvedValueOnce(result([delivered, out, hub, label]))
        .mockRejectedValueOnce(down),
      fetchUniversal: vi.fn()
        .mockResolvedValueOnce(result([out, hub, label].map(copied)))
        .mockResolvedValueOnce(result([delivered, out, hub, label].map(copied))),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
    // Each check starts from a fresh snapshot, so the router asks GOFO first every time.
    const sync = async (stage: string) => {
      await service.syncPackage({ ...parcel, current_stage: stage, [STORED_EVENT_IDENTITIES]: store.identities() });
      return ids(store.batch());
    };

    expect(await sync('pending')).toEqual([own(hub), own(label)]);
    // GOFO is down: the copy adds only the scan GOFO has not reported yet.
    expect(await sync('in_transit')).toEqual([copy(out)]);
    const copiedRow = store.rows.get(copy(out))!.id;
    // GOFO is back: its scan takes that row over and sets its clock and place.
    expect(await sync('out_for_delivery')).toEqual([own(delivered), copy(out), own(hub), own(label)]);
    expect(store.rows.get(copy(out))).toMatchObject({ id: copiedRow, occurred_at: '2026-01-04T12:59:03Z', location: 'Station City, EX' });
    // Down again: the copy stores and moves nothing.
    expect(await sync('delivered')).toEqual([]);
    expect(store.rows.size).toBe(4);
    expect(store.rows.get(copy(out))).toMatchObject({ id: copiedRow, occurred_at: '2026-01-04T12:59:03Z' });
    expect(store.rows.get(own(delivered))).toMatchObject({ occurred_at: '2026-01-04T21:58:30Z' });
    expect(adapter.fetchUniversal).toHaveBeenCalledTimes(2);
  });

  it('keeps the universal rows GOFO\'s scans hold and the time they cover, across a fallback', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = {
      fetch: vi.fn().mockResolvedValueOnce(result([hub, label])).mockRejectedValueOnce(new Error('GOFO tracking endpoint is unavailable'))
        .mockResolvedValueOnce(result([delivered, out, hub, label])),
      fetchUniversal: vi.fn().mockResolvedValue(result([out, hub, label].map(copied))),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
    const history = () => (client.updatePackage.mock.calls.at(-1)?.[1] as JsonObject).carrier_data as JsonObject;
    // Only the history goes from one check to the next, so the router asks GOFO first every time.
    const sync = async (stage: string) => service.syncPackage({ ...parcel, current_stage: stage,
      carrier_data: { direct_history: client.updatePackage.mock.calls.length ? history().direct_history : undefined },
      [STORED_EVENT_IDENTITIES]: store.identities() });

    await sync('pending');
    const covered = { from: '2026-01-02T12:30:00.000Z', to: '2026-01-03T13:15:42.000Z', held: [] };
    expect(history().direct_history).toEqual(covered);
    // The provider's answer keeps the carrier's history as it was.
    await sync('in_transit');
    expect(store.rows.has(copy(out))).toBe(true);
    expect(history().direct_history).toEqual(covered);
    // GOFO's scan takes over the provider's row, which its history then holds.
    await sync('out_for_delivery');
    expect(history().direct_history).toEqual({ from: '2026-01-02T12:30:00.000Z', to: '2026-01-04T21:58:30.000Z', held: [copy(out)] });
  });

  it('absorbs every copy at a carrier scan\'s instant and keeps them out of later fallbacks', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    // One provider's two readings of GOFO's hub scan, on the right clock.
    const readings: Scan[] = [{ time: '2026-01-03T13:15:42Z', description: 'Arrived at hub', stage: 'in_transit' },
      { time: '2026-01-03T13:15:42Z', description: 'Arrived at the regional hub of the carrier', stage: 'pending' }];
    const down = new Error('GOFO tracking endpoint is unavailable');
    const adapter = {
      fetch: vi.fn().mockRejectedValueOnce(down).mockResolvedValueOnce(result([hub])).mockRejectedValueOnce(down)
        .mockResolvedValueOnce(result([hub])),
      fetchUniversal: vi.fn().mockResolvedValue({ ...result(readings), tracking_provider: 'ParcelsApp' }),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
    const sync = async (stage: string) => {
      await service.syncPackage({ ...parcel, current_stage: stage, [STORED_EVENT_IDENTITIES]: store.identities() });
      return ids(store.batch());
    };
    const [first, second] = readings.map((reading) => providerEventId('unknown', reading.time, '', reading.description));

    expect(await sync('pending')).toEqual([first, second]);
    const copiedRow = store.rows.get(first!)!.id;
    // GOFO is back: its scan takes over the copy in its stage and absorbs the other.
    expect(await sync('in_transit')).toEqual([first]);
    expect((client.applyTrackingSync.mock.calls.at(-1) as unknown[] | undefined)?.[4]).toEqual([second]);
    expect([...store.rows.values()]).toMatchObject([{ id: copiedRow, description: hub.description, location: 'Hub City, EX' }]);
    // Down again: both of the provider's readings of the row GOFO holds stay out.
    expect(await sync('in_transit')).toEqual([]);
    expect(store.rows.size).toBe(1);
    expect(await sync('in_transit')).toEqual([first]);
    expect([...store.rows.values()]).toMatchObject([{ id: copiedRow, description: hub.description }]);
    expect(adapter.fetchUniversal).toHaveBeenCalledTimes(2);
  });

  it('leaves out and absorbs a provider\'s copy that reads a carrier\'s scan as another stage', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    // 17TRACK reads UPS's out-for-delivery scan as an exception.
    const ups: Scan = { time: '2026-01-05T09:05:52Z', location: 'Example City, EX', description: 'Out For Delivery Today', stage: 'out_for_delivery' };
    const reread: Scan = { ...ups, description: 'Delivery change requested', stage: 'exception' };
    const down = new Error('UPS tracking endpoint is unavailable');
    const adapter = {
      fetch: vi.fn().mockResolvedValueOnce(result([ups])).mockRejectedValueOnce(down).mockResolvedValueOnce(result([ups])),
      fetchUniversal: vi.fn().mockResolvedValue({ ...result([reread]), tracking_provider: '17TRACK' }),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
    const sync = async () => {
      await service.syncPackage({ id: 'ups-copy', user_id: 'owner', carrier: 'ups', tracking_number: '1Z0000000000000001',
        current_stage: 'out_for_delivery', [STORED_EVENT_IDENTITIES]: store.identities() });
      return ids(store.batch());
    };
    const own = providerEventId('ups', ups.time, ups.location!, ups.description);
    const copy = providerEventId('unknown', reread.time, reread.location!, reread.description);

    expect(await sync()).toEqual([own]);
    // UPS is down: the copy stays out of the timeline.
    expect(await sync()).toEqual([]);
    expect([...store.rows.keys()]).toEqual([own]);
    // A copy stored before goes with UPS's next answer.
    store.rows.set(copy, { provider_event_id: copy, occurred_at: ups.time, stage: reread.stage, description: reread.description,
      location: reread.location, raw_data: { universal_provider: '17TRACK' } });
    expect(await sync()).toEqual([own]);
    expect((client.applyTrackingSync.mock.calls.at(-1) as unknown[] | undefined)?.[4]).toEqual([copy]);
    expect([...store.rows.keys()]).toEqual([own]);
  });

  it('reviews a provider\'s copies of the carrier\'s scans as the carrier\'s wording', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    // Pitney Bowes's map stages the first scan; the shared rules stage the second.
    const closed = { time: '2026-01-03T13:15:42Z', location: 'Hub City, EX', description: 'Container Closed', stage: 'in_transit', stage_source: 'carrier_map' };
    const sorted = { time: '2026-01-03T09:00:00Z', location: 'Hub City, EX', description: 'Parcel sorted at the regional hub', stage: 'in_transit', stage_source: 'wording:language' };
    // ParcelsApp copies both to the minute without the place and reads the first by the universal map.
    const copied = [{ ...closed, location: undefined, stage_source: 'universal_map' }, { ...sorted, location: undefined }];
    const answer = (events: JsonObject[], provider?: string): CarrierResult => ({ ...result(events as unknown as Scan[]),
      ...(provider ? { tracking_provider: provider } : {}) });
    const adapter = {
      fetch: vi.fn().mockResolvedValueOnce(answer([closed, sorted])).mockRejectedValueOnce(new Error('Pitney Bowes tracking is unavailable')),
      fetchUniversal: vi.fn().mockResolvedValue(answer(copied, 'ParcelsApp')),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
    const sync = async () => {
      await service.syncPackage({ id: 'pb-copies', user_id: 'owner', carrier: 'pitney-bowes', tracking_number: 'UPAAA000000000000001',
        current_stage: store.rows.size ? 'in_transit' : 'pending', [STORED_EVENT_IDENTITIES]: store.identities() });
      return client.recordTrackingStatusObservations.mock.calls.at(-1) as [JsonObject[], string[], string];
    };
    const own = providerEventId('pitney-bowes', sorted.time, sorted.location, sorted.description);

    const [carrierObserved, carrierMapped] = await sync();
    expect(carrierObserved).toEqual([expect.objectContaining({ carrier: 'pitney-bowes', description_normalized: 'parcel sorted at the regional hub' })]);
    expect(adapter.fetchUniversal).not.toHaveBeenCalled();
    // Pitney Bowes is down: the provider's copies are its scans, reviewed against its map and sampled from its rows.
    const [copyObserved, copyMapped] = await sync();
    expect(adapter.fetchUniversal).toHaveBeenCalledOnce();
    expect(copyObserved).toEqual([expect.objectContaining({ observation_key: carrierObserved[0]!.observation_key,
      carrier: 'pitney-bowes', provider_event_id: own })]);
    expect(copyMapped).toEqual(carrierMapped);
  });

  it('records no offset for a provider\'s copy UPS holds in its own minute, 12 h after another scan of the place', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    // ParcelsApp copies UPS's scans to the minute and leaves the notice unclassified.
    const arrived: Scan = { time: '2026-01-04T05:01:00Z', location: 'Hubtown, EX', description: 'Arrived at Facility', stage: 'in_transit' };
    const notice: Scan = { time: '2026-01-04T17:01:45Z', location: 'Hubtown, EX', description: 'The receiver name is incorrect', stage: 'in_transit' };
    const adapter = {
      fetch: vi.fn().mockResolvedValueOnce(result([notice, arrived])).mockRejectedValueOnce(new Error('UPS tracking endpoint is unavailable')),
      fetchUniversal: vi.fn().mockResolvedValue({ ...result([{ ...notice, time: '2026-01-04T17:01:00Z', stage: 'pending' }, arrived]),
        tracking_provider: 'ParcelsApp' }),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
    const sync = async () => {
      await service.syncPackage({ id: 'ups-notice', user_id: 'owner', carrier: 'ups', tracking_number: '1Z0000000000000001',
        current_stage: 'in_transit', [STORED_EVENT_IDENTITIES]: store.identities() });
      return (client.completeSyncAttempt.mock.calls.at(-1)![1] as JsonObject).anomaly_codes;
    };

    await sync();
    // UPS is down: both copies are its scans, and neither was read a zone away.
    expect(await sync()).not.toContain('provider_clock_offset');
    expect(store.rows.size).toBe(2);
  });

  it('records no offset for a provider\'s copy of a scan USPS dated by its day alone', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    // USPS gives the printing its day alone, midnight in New York; 17TRACK's copy is midnight under another offset.
    const printed = { local_time: '2026-01-04', location: 'Example City, FL', description: 'Shipping Label Printed at Post Office', stage: 'registered' };
    const accepted = { time: '2026-01-04T12:40:00-05:00', location: 'Example City, FL', description: 'USPS in possession of item', stage: 'accepted' };
    const answer = (events: JsonObject[], extra: JsonObject = {}) => ({ status: 'in_transit', current_stage: 'accepted',
      last_status_text: accepted.description, last_update: accepted.time, events, ...extra }) as CarrierResult;
    const adapter = {
      fetch: vi.fn().mockResolvedValueOnce(answer([accepted, printed])).mockRejectedValueOnce(new Error('USPS tracking endpoint is unavailable')),
      fetchUniversal: vi.fn().mockResolvedValue(answer([accepted, { ...printed, local_time: undefined, time: '2026-01-04T00:00:00-06:00' }],
        { tracking_provider: '17TRACK' })),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
    const sync = async () => {
      await service.syncPackage({ id: 'usps-printed', user_id: 'owner', carrier: 'usps', tracking_number: '9400100000000000000001',
        current_stage: 'accepted', [STORED_EVENT_IDENTITIES]: store.identities() });
      return (client.completeSyncAttempt.mock.calls.at(-1)![1] as JsonObject).anomaly_codes;
    };

    await sync();
    // USPS is down: 17TRACK's copies are its scans, and the day has no clock to be off.
    expect(await sync()).not.toContain('provider_clock_offset');
    expect(store.rows.size).toBe(2);
  });

  it('starts the day of a scan a provider gives by its day alone on the clock of the carrier it names', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    // 17TRACK reads clocks in UTC and lists USPS's printing, dated by its day alone, under a label made the evening before.
    const printed = { local_time: '2026-01-04', location: 'Example City, FL', description: 'Shipping Label Printed at Post Office', stage: 'registered' };
    const created = { time: '2026-01-04T02:35:00.000Z', location: 'Example City, FL', description: 'Shipping Label Created', stage: 'registered' };
    const adapter = {
      fetch: vi.fn().mockRejectedValue(new Error('USPS tracking endpoint is unavailable')),
      fetchUniversal: vi.fn().mockResolvedValue({ status: 'in_transit', current_stage: 'registered', last_status_text: created.description,
        last_update: created.time, timezone: 'UTC', tracking_provider: '17TRACK', discovered_carrier: 'usps', events: [created, printed] }),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
    await service.syncPackage({ id: 'usps-day', user_id: 'owner', carrier: 'usps', tracking_number: '9400100000000000000001',
      current_stage: 'pending', [STORED_EVENT_IDENTITIES]: store.identities() });

    // Midnight in New York, as USPS dates it; UTC's midnight is the evening before there.
    const day = [...store.rows.values()].find((row) => row.description === printed.description)!;
    expect(day.occurred_at).toBe('2026-01-04T05:00:00Z');
  });

  it('stores once a scan a provider words its own way on a clock two hours early, and records the offset', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const offsets = anomalyLogs(vi.spyOn(observability, 'logOperationalEvent').mockImplementation(() => undefined), 'provider_clock_offset');
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const down = new Error('GOFO tracking endpoint is unavailable');
    // The copy of the hub scan: other wording, unclassified, and two hours before GOFO's 13:15:42 UTC.
    const reworded: Scan = { time: '2026-01-03T11:15:42Z', description: 'Item at sorting facility', stage: 'pending' };
    const adapter = {
      fetch: vi.fn()
        .mockRejectedValueOnce(down)
        .mockResolvedValueOnce(result([hub, label]))
        .mockRejectedValueOnce(down),
      fetchUniversal: vi.fn().mockResolvedValue(result([reworded])),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
    // The parcel keeps the offsets it showed; its routing starts afresh at each check.
    let shown: unknown;
    const sync = async (stage: string) => {
      await service.syncPackage({ ...parcel, current_stage: stage, carrier_data: { clock_offsets: shown },
        [STORED_EVENT_IDENTITIES]: store.identities() });
      shown = ((client.updatePackage.mock.calls.at(-1)![1] as JsonObject).carrier_data as JsonObject | undefined)?.clock_offsets ?? shown;
      const values = client.completeSyncAttempt.mock.calls.at(-1)![1] as JsonObject;
      return { batch: ids(store.batch()), anomalies: values.anomaly_codes };
    };
    const copyId = providerEventId('unknown', reworded.time, '', reworded.description);

    // GOFO is down: the copy is stored as it came.
    expect(await sync('pending')).toEqual({ batch: [copyId], anomalies: [] });
    const copyRow = store.rows.get(copyId)!.id;
    // GOFO is back: its scan takes the copy's row over, on GOFO's clock and in its words.
    expect(await sync('pending')).toEqual({ batch: [copyId, own(label)], anomalies: ['provider_clock_offset'] });
    expect(store.rows.get(copyId)).toMatchObject({ id: copyRow, occurred_at: '2026-01-03T13:15:42Z', stage: 'in_transit',
      description: hub.description });
    // Down again: the copy stores nothing, and the offset the row showed is not shown again.
    expect(await sync('in_transit')).toEqual({ batch: [], anomalies: [] });
    expect(store.rows.size).toBe(2);
    // The line names the row both readings share.
    expect(offsets().map((line) => (line as JsonObject).scan_id)).toEqual([copyId]);
  });

  it('does not take the carrier reply after a fallback for older history', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const down = new Error('GOFO tracking endpoint is unavailable');
    const adapter = {
      fetch: vi.fn()
        .mockResolvedValueOnce(result([hub, label]))
        .mockRejectedValueOnce(down)
        .mockResolvedValueOnce(result([out, hub, label]))
        .mockRejectedValueOnce(down),
      fetchUniversal: vi.fn().mockResolvedValue(result([out, hub, label].map(copied))),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
    // Each check sees the watermark the previous one saved, without its cooldowns.
    let watermark: unknown;
    const sync = async (stage: string) => {
      await service.syncPackage({ ...parcel, current_stage: stage,
        ...(watermark ? { carrier_data: { routing: { version: 1, configured_carrier: 'gofo', last_event_at: watermark } } } : {}),
        [STORED_EVENT_IDENTITIES]: store.identities() });
      const values = store.client.updatePackage.mock.calls.at(-1)![1] as JsonObject;
      watermark = ((values.carrier_data as JsonObject).routing as JsonObject).last_event_at;
      return values;
    };

    await sync('pending');
    // GOFO is down: the copy's new scan, three hours late, sets the watermark.
    expect((await sync('in_transit')).carrier_data).toMatchObject({ tracking_provider: expect.any(String) });
    expect(watermark).toBe('2026-01-04T15:59:03.000Z');
    // GOFO is back with that scan at its real time: its summary and link replace the copy's.
    const recovered = await sync('out_for_delivery');
    expect(recovered).toMatchObject({ last_status_text: 'Out for Delivery', current_stage: 'out_for_delivery' });
    expect(recovered.carrier_data).not.toHaveProperty('tracking_provider');
    expect(watermark).toBe('2026-01-04T12:59:03.000Z');
    // Down again: copies of stored scans no longer move the watermark past GOFO's clock.
    await sync('out_for_delivery');
    expect(watermark).toBe('2026-01-04T12:59:03.000Z');
    expect(store.rows.size).toBe(3);
  });

  it('takes the carrier reply back from a provider summary whose copies have since moved to the carrier clock', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch: vi.fn().mockResolvedValue(result([out, hub, label])), fetchUniversal: vi.fn() };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
    const sync = async (carrierData?: JsonObject) => {
      await service.syncPackage({ ...parcel, current_stage: carrierData ? 'out_for_delivery' : 'pending',
        ...(carrierData ? { carrier_data: carrierData } : {}), [STORED_EVENT_IDENTITIES]: store.identities() });
      return (store.client.updatePackage.mock.calls.at(-1)![1] as JsonObject).carrier_data as JsonObject;
    };
    await sync();
    // A provider's summary three hours late, saved before its rows moved to GOFO's instants: no copy is left to explain it.
    const late = '2026-01-04T15:59:03.000Z';
    const routing = { version: 1, configured_carrier: 'gofo', last_event_at: late };
    const recovered = await sync({ tracking_provider: 'ParcelsApp', last_update: late, routing });
    expect(recovered).not.toHaveProperty('tracking_provider');
    expect((recovered.routing as JsonObject).last_event_at).toBe('2026-01-04T12:59:03.000Z');
    // A watermark no provider's summary set stands.
    const kept = await sync({ last_update: late, routing });
    expect((kept.routing as JsonObject).last_event_at).toBe(late);
  });

  it('keeps the carrier\'s summary over a provider copy in other words an hour late, and takes it back from one saved before', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    // GOFO's scan to the minute, and a provider's copy in its own words with the wall clock read an hour off:
    // nothing ties the copy to the scan, so it is stored.
    const departed: Scan = { time: '2026-01-04T10:20:00-05:00', location: 'Hub City, EX', description: 'Departed GOFO Regional Hub', stage: 'in_transit' };
    const reworded: Scan = { time: '2026-01-04T16:20:00Z', location: 'HUB CITY', description: 'Left the sorting hub', stage: 'in_transit' };
    const adapter = {
      fetch: vi.fn().mockResolvedValueOnce(result([departed, hub, label]))
        .mockRejectedValueOnce(new Error('GOFO tracking endpoint is unavailable')).mockResolvedValue(result([departed, hub, label])),
      fetchUniversal: vi.fn().mockResolvedValue({ ...result([reworded]), tracking_provider: 'ParcelsApp' }),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
    const sync = async (stage: string, carrierData?: JsonObject) => {
      await service.syncPackage({ ...parcel, current_stage: stage, ...(carrierData ? { carrier_data: carrierData } : {}),
        [STORED_EVENT_IDENTITIES]: store.identities() });
      const values = store.client.updatePackage.mock.calls.at(-1)![1] as JsonObject;
      return { values, data: values.carrier_data as JsonObject,
        anomalies: (client.completeSyncAttempt.mock.calls.at(-1)![1] as JsonObject).anomaly_codes };
    };
    const watermark = (last: string) => ({ routing: { version: 1, configured_carrier: 'gofo', last_event_at: last } });

    const first = await sync('pending');
    expect(first.data).toMatchObject({ routing: { last_event_at: '2026-01-04T15:20:00.000Z' } });
    // GOFO is down: the copy is stored, the offset recorded, and the copy is no newer than GOFO's scan.
    const down = await sync('in_transit', watermark('2026-01-04T15:20:00.000Z'));
    expect(store.rows.size).toBe(4);
    expect(down.anomalies).toContain('provider_clock_offset');
    expect(down.data).not.toHaveProperty('tracking_provider');
    expect(down.data).toMatchObject({ routing: { last_event_at: '2026-01-04T15:20:00.000Z' } });
    // A provider's summary the copy set before: GOFO's answer takes it back.
    const late = '2026-01-04T16:20:00.000Z';
    const back = await sync('in_transit', { tracking_provider: 'ParcelsApp', last_update: late, ...watermark(late) });
    expect(back.values).toMatchObject({ last_status_text: departed.description });
    expect(back.data).not.toHaveProperty('tracking_provider');
    expect(back.data).toMatchObject({ routing: { last_event_at: '2026-01-04T15:20:00.000Z' } });
  });

  it('stores a provider scan from another place a zone after a carrier scan, and moves the watermark to it', async () => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const down = new Error('GOFO tracking endpoint is unavailable');
    // The same wording four hours later, in another town: the parcel moved on.
    const paris: Scan = { time: '2026-01-04T03:00:00-05:00', location: 'Paris, France', description: 'Departed facility', stage: 'in_transit' };
    const lyon: Scan = { time: '2026-01-04T12:00:00Z', location: 'Lyon, France', description: 'Departed facility', stage: 'in_transit' };
    const adapter = {
      fetch: vi.fn().mockResolvedValueOnce(result([paris])).mockRejectedValueOnce(down),
      fetchUniversal: vi.fn().mockResolvedValue(result([lyon])),
    };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
    let watermark: unknown;
    const sync = async (stage: string) => {
      await service.syncPackage({ ...parcel, current_stage: stage,
        ...(watermark ? { carrier_data: { routing: { version: 1, configured_carrier: 'gofo', last_event_at: watermark } } } : {}),
        [STORED_EVENT_IDENTITIES]: store.identities() });
      watermark = (((store.client.updatePackage.mock.calls.at(-1)![1] as JsonObject).carrier_data as JsonObject).routing as JsonObject).last_event_at;
    };

    await sync('pending');
    expect(watermark).toBe('2026-01-04T08:00:00.000Z');
    await sync('in_transit');
    expect(ids(store.batch())).toEqual([providerEventId('unknown', lyon.time, lyon.location!, lyon.description)]);
    expect(store.rows.size).toBe(2);
    expect(watermark).toBe('2026-01-04T12:00:00.000Z');
  });

  describe('rows only the provider wrote', () => {
    const down = new Error('GOFO tracking endpoint is unavailable');
    const leased = (store: ReturnType<typeof eventStore>) => ({ ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    });
    const named = (row: JsonObject | undefined) => (row?.raw_data as JsonObject | undefined)?.universal_provider;
    // A warehouse's scans, first read as UTC, eight hours late. A scraper fix reads them on the warehouse's
    // clock: one by the zone the answer declares for a clock without an offset, one by an offset of its own.
    const sorted: Scan = { time: '2026-01-03T21:15:42', location: 'Example City, CN', description: 'Arrived at sorting center', stage: 'in_transit' };
    const departed = (time: string): Scan => ({ time, location: 'Example City, CN', description: 'Departed from sorting center', stage: 'in_transit' });
    const answer = (timezone: string, scans: Scan[]): CarrierResult => ({ ...result(scans), timezone });
    const universal = (scan: Scan) => providerEventId('unknown', scan.time, scan.location ?? '', scan.description);

    it('move to the provider\'s corrected clock in place, and bring the watermark back', async () => {
      vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
      const store = eventStore();
      const client = leased(store);
      const adapter = { fetch: vi.fn().mockRejectedValue(down), fetchUniversal: vi.fn()
        .mockResolvedValueOnce(answer('UTC', [departed('2026-01-04T02:30:10Z'), sorted]))
        .mockResolvedValueOnce(answer('Asia/Shanghai', [departed('2026-01-03T18:30:10.000Z'), sorted])) };
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
      let watermark: unknown;
      let savedProvider: unknown;
      const sync = async (stage: string) => {
        await service.syncPackage({ ...parcel, current_stage: stage,
          ...(watermark ? { carrier_data: { tracking_provider: savedProvider, routing: { version: 1, configured_carrier: 'gofo', last_event_at: watermark } } } : {}),
          [STORED_EVENT_IDENTITIES]: store.identities() });
        const values = store.client.updatePackage.mock.calls.at(-1)![1] as JsonObject;
        watermark = ((values.carrier_data as JsonObject).routing as JsonObject).last_event_at;
        savedProvider = (values.carrier_data as JsonObject).tracking_provider;
        return { values, anomalies: client.completeSyncAttempt.mock.calls.at(-1)![1].anomaly_codes };
      };
      const lateDeparture = universal(departed('2026-01-04T02:30:10Z'));

      const first = await sync('pending');
      const provider = (first.values.carrier_data as JsonObject).tracking_provider;
      expect(first.anomalies).not.toContain('provider_clock_offset');
      expect(watermark).toBe('2026-01-04T02:30:10.000Z');
      expect([...store.rows.values()].map(named)).toEqual([provider, provider]);
      const rowIds = [lateDeparture, universal(sorted)].map((id) => store.rows.get(id)!.id);

      const second = await sync('in_transit');
      expect(second.anomalies).toEqual(expect.arrayContaining(['provider_clock_offset']));
      // Each scan rewrites its row, the one whose identity the new time changed under its old identity.
      expect(ids(store.batch())).toEqual([lateDeparture, universal(sorted)]);
      expect(store.rows.size).toBe(2);
      expect(store.rows.get(lateDeparture)).toMatchObject({ id: rowIds[0], occurred_at: '2026-01-03T18:30:10Z' });
      expect(store.rows.get(universal(sorted))).toMatchObject({ id: rowIds[1], occurred_at: '2026-01-03T13:15:42Z' });
      expect([...store.rows.values()].map(named)).toEqual([provider, provider]);
      expect(second.values).toMatchObject({ last_status_text: 'Departed from sorting center' });
      expect(watermark).toBe('2026-01-03T18:30:10.000Z');
    });

    it('bring the watermark back past a row stored when it was first seen', async () => {
      vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
      const store = eventStore();
      // A postal scan on a wall clock, stored at the check that first saw it: no scan time.
      store.rows.set('unknown:postal', { provider_event_id: 'unknown:postal', occurred_at: '2026-01-04T09:00:00.123Z', stage: 'in_transit',
        description: 'Departure from outward office of exchange', raw_data: { universal_provider: 'UPU', observed_without_provider_timestamp: true } });
      const client = leased(store);
      const adapter = { fetch: vi.fn().mockRejectedValue(down), fetchUniversal: vi.fn()
        .mockResolvedValueOnce(answer('UTC', [departed('2026-01-04T02:30:10Z'), sorted]))
        .mockResolvedValueOnce(answer('Asia/Shanghai', [departed('2026-01-03T18:30:10.000Z'), sorted])) };
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
      let watermark: unknown;
      for (const stage of ['in_transit', 'in_transit']) {
        await service.syncPackage({ ...parcel, current_stage: stage,
          ...(watermark ? { carrier_data: { routing: { version: 1, configured_carrier: 'gofo', last_event_at: watermark } } } : {}),
          [STORED_EVENT_IDENTITIES]: store.identities() });
        watermark = (((store.client.updatePackage.mock.calls.at(-1)![1] as JsonObject).carrier_data as JsonObject).routing as JsonObject).last_event_at;
      }
      // A carrier whose newest scan is the corrected departure is no longer older than the parcel.
      expect(watermark).toBe('2026-01-03T18:30:10.000Z');
    });

    it('keep a row a carrier took over on the carrier\'s clock', async () => {
      vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
      const store = eventStore();
      const client = leased(store);
      const adapter = {
        fetch: vi.fn().mockRejectedValueOnce(down).mockResolvedValueOnce(result([out])).mockRejectedValueOnce(down),
        fetchUniversal: vi.fn().mockResolvedValue(result([copied(out)])),
      };
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
      const sync = async (stage: string) => {
        await service.syncPackage({ ...parcel, current_stage: stage, [STORED_EVENT_IDENTITIES]: store.identities() });
        return ids(store.batch());
      };

      // GOFO is down: the copy, three hours late, names its provider.
      await sync('pending');
      expect(named(store.rows.get(copy(out)))).toEqual(expect.any(String));
      // GOFO is back: its scan takes the row over on its own clock, without the name.
      await sync('in_transit');
      expect(store.rows.get(copy(out))).toMatchObject({ occurred_at: '2026-01-04T12:59:03Z' });
      expect(named(store.rows.get(copy(out)))).toBeUndefined();
      // Down again: the provider's clock no longer moves the row.
      expect(await sync('out_for_delivery')).toEqual([]);
      expect(store.rows.size).toBe(1);
      expect(store.rows.get(copy(out))).toMatchObject({ occurred_at: '2026-01-04T12:59:03Z' });
    });

    it('leave a row stored before rows named their provider, and drop a named row whose scan another row holds at the corrected instant', async () => {
      vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
      const store = eventStore();
      const client = leased(store);
      const loaded: Scan = { time: '2026-01-03T09:05:00', location: 'Example City, CN', description: 'Loaded onto truck', stage: 'in_transit' };
      const weighed: Scan = { time: '2026-01-03T08:40:00', location: 'Example City, CN', description: 'Weighed at warehouse', stage: 'in_transit' };
      const seed = (id: string, scan: Scan, occurredAt: string, raw: JsonObject) => store.rows.set(id, {
        id: `seed-${store.rows.size}`, package_id: parcel.id, provider_event_id: id, stage: scan.stage, description: scan.description,
        location: scan.location, occurred_at: occurredAt, raw_data: { time: scan.time, stage_source: 'carrier_map', ...raw },
      });
      // Stored before rows named their provider, eight hours late.
      seed(universal(sorted), sorted, '2026-01-03T21:15:42Z', {});
      // Named, and GOFO's own row of the scan already stands at the corrected instant.
      seed(universal(loaded), loaded, '2026-01-03T09:05:00Z', { universal_provider: 'ParcelsApp' });
      seed('gofo:loaded', { ...loaded, time: '2026-01-03T01:05:00Z' }, '2026-01-03T01:05:00Z', {});
      // Named, with nothing at the corrected instant.
      seed(universal(weighed), weighed, '2026-01-03T08:40:00Z', { universal_provider: 'ParcelsApp' });
      const adapter = { fetch: vi.fn().mockRejectedValue(down),
        fetchUniversal: vi.fn().mockResolvedValue(answer('Asia/Shanghai', [sorted, loaded, weighed])) };
      const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, now);
      await service.syncPackage({ ...parcel, current_stage: 'in_transit', [STORED_EVENT_IDENTITIES]: store.identities() });

      expect((store.client.updatePackage.mock.calls.at(-1)![1] as JsonObject).carrier_data)
        .toMatchObject({ tracking_provider: 'ParcelsApp' });
      expect(ids(store.batch())).toEqual([universal(weighed)]);
      expect(store.rows.size).toBe(3);
      expect(store.rows.get(universal(weighed))).toMatchObject({ id: 'seed-3', occurred_at: '2026-01-03T00:40:00Z' });
      expect(store.rows.get(universal(sorted))).toMatchObject({ occurred_at: '2026-01-03T21:15:42Z' });
      expect(store.rows.has(universal(loaded))).toBe(false);
      expect(store.rows.get('gofo:loaded')).toMatchObject({ occurred_at: '2026-01-03T01:05:00Z' });
    });
  });
});

describe('a carrier scan whose zone the scraper deduced', () => {
  // USPS gives a wall clock in no zone, which the scraper dates on a neighbouring scan's Pacific clock; 17TRACK copies
  // the scan with the carrier's own Mountain offset and seconds of its own. The number, wording and clocks are invented.
  const parcel = { id: 'usps-zone', user_id: 'owner', carrier: 'usps', tracking_number: '9400100000000000000002', current_stage: 'in_transit' };
  const scan = { local_time: '2026-01-14T09:40:00', time: '2026-01-14T09:40:00-08:00', time_zone: 'America/Los_Angeles',
    time_zone_basis: 'neighbour', description: 'In Transit to Next Facility', stage: 'in_transit' };
  const own: CarrierResult = { status: 'in_transit', current_stage: 'in_transit', last_status_text: scan.description,
    last_update: scan.time, events: [scan] };
  const copied: CarrierResult = { status: 'in_transit', current_stage: 'in_transit', last_status_text: scan.description,
    last_update: '2026-01-14T16:40:18Z', tracking_provider: '17TRACK', events: [{ time: '2026-01-14T16:40:18Z',
      description: scan.description, stage: 'in_transit', location: 'US', provider_time_iso: '2026-01-14T09:40:18-07:00',
      time_provenance: 'carrier_reported' }] };
  const down = new Error('USPS tracking endpoint is unavailable');

  it.each([
    ['the carrier', [own, down, own, own, down]],
    ['the provider', [down, own, own, down]],
  ] as const)('stores it once, at the offset the provider states, when %s answers first', async (_first, answers) => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-15T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const fetch = vi.fn();
    const adapter = { fetch, fetchUniversal: vi.fn().mockResolvedValue(copied) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date('2026-01-15T12:00:00Z'));
    for (const answer of answers) {
      if (answer instanceof Error) fetch.mockRejectedValueOnce(answer);
      else fetch.mockResolvedValueOnce(answer);
      await service.syncPackage({ ...parcel, [STORED_EVENT_IDENTITIES]: store.identities() });
      expect(store.rows.size).toBe(1);
    }
    expect([...store.rows.values()][0]).toMatchObject({ occurred_at: '2026-01-14T16:40:00Z',
      raw_data: { time: '2026-01-14T09:40:00-07:00', local_time: '2026-01-14T09:40:00', time_zone_basis: 'stated' } });
    // The offset is the scraper's deduction, put right: no provider clock to fix.
    expect(client.updatePackage.mock.calls.map(([, values]) => (values.carrier_data as JsonObject | undefined)?.clock_offsets).filter(Boolean)).toEqual([]);
  });
});

describe('a carrier a provider names, whose scans the provider copied in the wrong zone', () => {
  type Scan = { time: string; description: string; stage: string };
  // A synthetic number 4PX's adapter accepts and detection names no carrier for.
  const parcel = { id: 'four-px-parcel', user_id: 'owner', carrier: 'unknown', tracking_number: 'TEST0000000000001HD' };
  // 4PX gives each scan its own offset.
  const scans: Scan[] = [
    { time: '2026-01-05T08:55:00+02:00', description: 'Departed from the transit hub', stage: 'in_transit' },
    { time: '2026-01-04T10:30:00+02:00', description: 'Released from customs', stage: 'in_transit' },
    { time: '2026-01-03T04:18:58+08:00', description: 'Departed from the origin airport', stage: 'in_transit' },
  ];
  const result = (events: Scan[]): CarrierResult => ({ status: 'in_transit', current_stage: 'in_transit',
    last_status_text: events[0]!.description, last_update: events[0]!.time, events });
  // The provider's copies are eight hours late, the newest after the check.
  const copies: CarrierResult = { ...result(scans.map((scan) => ({ ...scan,
    time: new Date(Date.parse(scan.time) + 8 * 3_600_000).toISOString() }))),
  tracking_provider: 'ParcelsApp', discovered_carrier: 'four-px', reported_carriers: ['4PX Express'] };
  const setup = (fetch: (carrierId: string) => Promise<CarrierResult>) => {
    vi.spyOn(observability, 'reportRoutingEvent').mockImplementation(() => undefined);
    const store = eventStore();
    const client = { ...store.client,
      acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '2026-01-05T12:01:30Z' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined),
    };
    const adapter = { fetch, fetchUniversal: vi.fn().mockResolvedValue(copies) };
    let now = new Date('2026-01-05T12:00:00Z');
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => now);
    const saved = () => store.client.updatePackage.mock.calls.at(-1)![1] as JsonObject;
    const sync = async (at: string, loaded: JsonObject = {}) => {
      now = new Date(at);
      await service.syncPackage({ ...parcel, current_stage: 'pending', ...loaded, [STORED_EVENT_IDENTITIES]: store.identities() });
      return saved();
    };
    return { store, adapter, sync };
  };

  it('files the parcel under the carrier on the check whose provider names it', async () => {
    const fetch = vi.fn<(carrierId: string) => Promise<CarrierResult>>().mockResolvedValue(result(scans));
    const { store, sync } = setup(fetch);
    const values = await sync('2026-01-05T12:00:00Z');
    expect(fetch.mock.calls.map(([carrier]) => carrier)).toEqual(['four-px']);
    expect(values).toMatchObject({ carrier: 'four-px', current_stage: 'in_transit',
      carrier_data: { routing: { confirmed_carrier: 'four-px', last_event_at: '2026-01-05T06:55:00.000Z' } } });
    expect(values.carrier_data).not.toHaveProperty('tracking_provider');
    expect([...store.rows.values()].map((row) => row.occurred_at))
      .toEqual(['2026-01-05T06:55:00Z', '2026-01-04T08:30:00Z', '2026-01-02T20:18:58Z']);
  });

  it('files it under the carrier once the carrier answers again, over the copies stored while it was down', async () => {
    const fetch = vi.fn<(carrierId: string) => Promise<CarrierResult>>()
      .mockRejectedValueOnce(new Error('4PX tracking is unavailable')).mockResolvedValue(result(scans));
    const { store, adapter, sync } = setup(fetch);
    const first = await sync('2026-01-05T12:00:00Z');
    // A copy dated past the check is misdated: the watermark is the newest copy before it.
    expect(first).toMatchObject({ carrier_data: { tracking_provider: 'ParcelsApp',
      routing: { discovered_carrier: 'four-px', last_event_at: '2026-01-04T16:30:00.000Z' } } });
    const copied = new Map([...store.rows.values()].map((row) => [row.provider_event_id, row.id]));
    expect(copied.size).toBe(3);
    // The parcel was added after the carrier's newest scan: the app's row is no scan.
    store.rows.set('app:pending', { id: 'added', provider_event_id: 'app:pending', stage: 'pending',
      description: 'Tracking added', occurred_at: '2026-01-05T09:00:00.000Z' });

    const values = await sync('2026-01-06T12:00:00Z', { current_stage: 'in_transit', carrier_data: first.carrier_data });
    expect(fetch.mock.calls.map(([carrier]) => carrier)).toEqual(['four-px', 'four-px']);
    expect(adapter.fetchUniversal).toHaveBeenCalledOnce();
    expect(values).toMatchObject({ carrier: 'four-px',
      carrier_data: { routing: { confirmed_carrier: 'four-px', last_event_at: '2026-01-05T06:55:00.000Z' } } });
    expect(values.carrier_data).not.toHaveProperty('tracking_provider');
    // The carrier's scans take the copies over at their own instants.
    expect(store.rows.size).toBe(4);
    expect([...copied].map(([id, row]) => store.rows.get(String(id))?.id === row)).toEqual([true, true, true]);
    expect([...copied.keys()].map((id) => store.rows.get(String(id))?.occurred_at))
      .toEqual(['2026-01-05T06:55:00Z', '2026-01-04T08:30:00Z', '2026-01-02T20:18:58Z']);
  });

  it('files it under the carrier when the provider corrected its copies on an earlier check', async () => {
    const fetch = vi.fn<(carrierId: string) => Promise<CarrierResult>>()
      .mockRejectedValueOnce(new Error('4PX tracking is unavailable'))
      .mockRejectedValueOnce(new Error('4PX tracking is unavailable')).mockResolvedValue(result(scans));
    const { store, adapter, sync } = setup(fetch);
    // The parcel was added after the carrier's newest scan, just before its first check.
    store.rows.set('app:pending', { id: 'added', provider_event_id: 'app:pending', stage: 'pending',
      description: 'Tracking added', occurred_at: '2026-01-05T11:59:59.000Z' });
    const first = await sync('2026-01-05T12:00:00Z');
    adapter.fetchUniversal.mockResolvedValue({ ...result(scans), tracking_provider: 'ParcelsApp',
      discovered_carrier: 'four-px', reported_carriers: ['4PX Express'] });
    // The provider's copies come back at the carrier's instants: the watermark goes back to
    // the newest scan, not to the time the parcel was added.
    const second = await sync('2026-01-05T15:00:00Z', { current_stage: 'in_transit', carrier_data: first.carrier_data });
    expect(second.carrier_data).toMatchObject({ routing: { last_event_at: '2026-01-05T06:55:00.000Z' } });

    const third = await sync('2026-01-05T16:00:00Z', { current_stage: 'in_transit', carrier_data: second.carrier_data });
    expect(fetch.mock.calls.map(([carrier]) => carrier)).toEqual(['four-px', 'four-px', 'four-px']);
    expect(third).toMatchObject({ carrier: 'four-px', carrier_data: { routing: { confirmed_carrier: 'four-px' } } });
    expect(third.carrier_data).not.toHaveProperty('tracking_provider');
  });
});

describe('the pickup point a parcel was collected from', () => {
  const POINT = 'Example Kiosk\nExample Street 1, 9999 Sampleville';
  const OTHER = 'Example Locker\nSample Road 2, 9999 Sampleville';
  const NUMBER = 'CW123456785FR';
  const scan = (time: string, stage: string, description: string = stage) => ({ time, stage, description });
  const arrived = scan('2026-10-02T08:00:00Z', 'in_transit', 'Arrived at the local depot');
  const waiting = scan('2026-10-02T14:00:00Z', 'ready_for_pickup', 'Ready for pickup');
  const reminder = scan('2026-10-03T09:00:00Z', 'pending', 'We reminded the recipient by email');
  const collected = scan('2026-10-04T16:00:00Z', 'delivered', 'Delivered');
  const answer = (stage: string, events: ReturnType<typeof scan>[], extra: JsonObject = {}): CarrierResult => ({
    status: stage === 'delivered' ? 'delivered' : 'in_transit', current_stage: stage, last_update: events.at(-1)!.time,
    last_status_text: events.at(-1)!.description, events, ...extra,
  });

  /** Checks the parcel once per answer, each check from what the one before saved, with its stored scans. */
  async function follow(parcel: JsonObject, ...answers: CarrierResult[]) {
    const store = eventStore();
    const adapter = { fetch: vi.fn() };
    for (const next of answers) adapter.fetch.mockResolvedValueOnce(next);
    const service = new TrackingSyncService(store.client as unknown as SupabaseServiceClient, adapter, null,
      () => new Date('2026-10-05T12:00:00Z'));
    let saved: JsonObject = { user_id: 'owner', tracking_number: NUMBER, current_stage: 'in_transit', ...parcel };
    const checks: JsonObject[] = [];
    for (let check = 0; check < answers.length && adapter.fetch.mock.calls.length < answers.length; check += 1) {
      await service.syncPackage({ ...saved, [STORED_EVENT_IDENTITIES]: store.identities() });
      saved = { ...saved, ...store.client.updatePackage.mock.calls.at(-1)![1] as JsonObject };
      checks.push(saved);
    }
    const point = (index: number) => (checks.at(index)!.carrier_data as JsonObject).pickup_point;
    return { checks, point, adapter };
  }

  it('keeps the point once the carrier stops naming it, through notices and later checks', async () => {
    const { checks, point } = await follow({ id: 'collected', carrier: 'la-poste' },
      answer('ready_for_pickup', [arrived, waiting], { pickup_point: POINT }),
      // The delivered answer only has its last scan: the stored ones tell where it waited.
      answer('delivered', [reminder, collected]),
      answer('delivered', [arrived, waiting, reminder, collected], { pickup_point: '' }));
    expect(checks.map((check) => check.current_stage)).toEqual(['ready_for_pickup', 'delivered', 'delivered']);
    expect([point(0), point(1), point(2)]).toEqual([POINT, POINT, POINT]);
  });

  it('keeps it by the saved stage when no scan says the parcel moved', async () => {
    const { point } = await follow({ id: 'undated', carrier: 'la-poste', current_stage: 'ready_for_pickup',
      carrier_data: { pickup_point: POINT } }, { status: 'delivered', current_stage: 'delivered', last_status_text: 'Delivered' });
    expect(point(-1)).toBe(POINT);
  });

  it('drops it for a parcel taken back out for delivery after it waited, or returned', async () => {
    const out = scan('2026-10-04T07:00:00Z', 'out_for_delivery', 'Out for delivery');
    const door = await follow({ id: 'door', carrier: 'la-poste' },
      answer('ready_for_pickup', [arrived, waiting], { pickup_point: POINT }),
      answer('delivered', [arrived, waiting, out, collected]));
    expect(door.checks.at(-1)!.current_stage).toBe('delivered');
    expect(door.point(-1)).toBeUndefined();

    const back = await follow({ id: 'returned', carrier: 'la-poste' },
      answer('ready_for_pickup', [arrived, waiting], { pickup_point: POINT }),
      answer('returned', [arrived, waiting, scan('2026-10-12T09:00:00Z', 'returned', 'Returned to the sender')]));
    expect(back.checks.at(-1)!.current_stage).toBe('returned');
    expect(back.point(-1)).toBeUndefined();
  });

  it('takes a point the carrier names later instead', async () => {
    const moved = scan('2026-10-03T10:00:00Z', 'ready_for_pickup', 'Moved to another pickup point');
    const { point } = await follow({ id: 'moved', carrier: 'la-poste' },
      answer('ready_for_pickup', [arrived, waiting], { pickup_point: POINT }),
      answer('ready_for_pickup', [arrived, waiting, moved], { pickup_point: OTHER }),
      answer('delivered', [arrived, waiting, moved, collected]),
      answer('delivered', [arrived, waiting, moved, collected], { pickup_point: POINT }));
    expect([point(1), point(2), point(3)]).toEqual([OTHER, OTHER, POINT]);
  });

  it('keeps a delivery partner’s point, and never hands one carrier’s point to another', async () => {
    // Followed on La Poste, which names Posti, whose own lookup then follows the parcel.
    const handed = scan('2026-10-02T06:00:00Z', 'in_transit', 'Handed to the delivery partner');
    const partner = await follow({ id: 'partner', carrier: 'la-poste' },
      answer('in_transit', [handed], { delivery_carrier: 'posti', destination_country: 'FI' }),
      answer('ready_for_pickup', [arrived, waiting], { pickup_point: POINT }),
      answer('delivered', [collected]));
    expect(partner.adapter.fetch.mock.calls.map((call) => call[0])).toEqual(['la-poste', 'posti', 'posti']);
    expect(partner.checks.at(-1)).toMatchObject({ current_stage: 'delivered',
      carrier_data: { original_carrier: 'la-poste', active_tracking_carrier: 'posti', pickup_point: POINT } });

    // La Poste said where the parcel waited; Posti, reached only now, delivers it without a point.
    const origin = await follow({ id: 'origin', carrier: 'la-poste' },
      answer('ready_for_pickup', [arrived, waiting], { pickup_point: POINT }),
      answer('ready_for_pickup', [arrived, waiting], { pickup_point: POINT, delivery_carrier: 'posti', destination_country: 'FI' }),
      answer('delivered', [collected]));
    expect(origin.checks.at(-1)).toMatchObject({ current_stage: 'delivered', carrier_data: { active_tracking_carrier: 'posti' } });
    expect(origin.point(-1)).toBeUndefined();

    // The owner changed the carrier: the one the point came from no longer delivers.
    const changed = await follow({ id: 'changed', carrier: 'la-poste', current_stage: 'ready_for_pickup',
      carrier_data: { pickup_point: POINT, routing: { version: 1, configured_carrier: 'chronopost' } } },
    answer('delivered', [waiting, collected]));
    expect(changed.point(-1)).toBeUndefined();
  });
});

describe('rechecks of an account\'s parcel whose number nobody has answered', () => {
  afterEach(() => vi.unstubAllEnvs());
  const routing = { version: 1, configured_carrier: 'unknown', failures: {} };
  const unseen = (id: string, extra: JsonObject = {}): JsonObject => ({ id, user_id: 'a', one_off: false, carrier: 'unknown',
    current_stage: 'pending', tracking_number: 'TEST1234', created_at: '2026-09-01T09:00:00Z', last_synced_at: null,
    sync_status: 'waiting', archived_at: null, carrier_data: { routing }, ...extra });
  const checked = async (parcels: JsonObject[], at: string, client = fakeClient(parcels)) => {
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient,
      { fetch: vi.fn().mockResolvedValue({ status: 'in_transit' }) }, null, () => new Date(at));
    await service.sync();
    return client.startSyncAttempt.mock.calls.map(([, values]) => values.package_id);
  };

  // Added on 1 September at 11:00 in Zurich; `on` with the rechecks, `off` without, as today.
  it.each([
    { name: 'a day on, checked four hours before', at: '2026-09-02T10:00:00Z', last: '2026-09-02T06:00:15Z', on: 1, off: 0 },
    { name: 'two days on, checked after a day', at: '2026-09-03T08:00:00Z', last: '2026-09-02T10:00:15Z', on: 0, off: 1 },
    { name: 'two days on, never checked', at: '2026-09-03T10:00:00Z', last: null, on: 1, off: 1 },
    { name: 'three days on, checked after a day', at: '2026-09-04T10:00:00Z', last: '2026-09-02T10:00:15Z', on: 1, off: 1 },
    { name: 'three days on, at 23:00 in Zurich', at: '2026-09-04T21:00:00Z', last: '2026-09-02T10:00:15Z', on: 0, off: 1 },
    { name: 'three days on, at 08:00 in Zurich', at: '2026-09-05T06:00:00Z', last: '2026-09-02T10:00:15Z', on: 1, off: 1 },
    { name: 'six days on, checked after three', at: '2026-09-07T10:00:00Z', last: '2026-09-04T10:00:15Z', on: 0, off: 1 },
    { name: 'a week on, checked after three days', at: '2026-09-08T10:00:00Z', last: '2026-09-04T10:00:15Z', on: 1, off: 0 },
    { name: 'two weeks on, checked after one', at: '2026-09-15T10:00:00Z', last: '2026-09-08T10:00:15Z', on: 0, off: 1 },
    { name: 'three days on, archived', at: '2026-09-04T10:00:00Z', last: '2026-09-02T10:00:15Z', on: 1, off: 1,
      extra: { archived_at: '2026-09-02T12:00:00Z' } },
    { name: 'two weeks on, archived', at: '2026-09-15T10:00:00Z', last: '2026-09-08T10:00:15Z', on: 0, off: 1,
      extra: { archived_at: '2026-09-02T12:00:00Z' } },
    { name: 'two weeks on, answered for', at: '2026-09-15T10:00:00Z', last: '2026-09-15T09:00:15Z', on: 1, off: 1,
      extra: { carrier_data: { routing: { ...routing, last_success_at: '2026-09-01T10:00:00Z', last_event_at: '2026-09-01T10:00:00Z' } } } },
  ])('checks a parcel $name at $at: $on with the rechecks, $off without', async ({ at, last, on, off, extra }) => {
    const parcel = unseen('unseen', { last_synced_at: last, ...extra });
    await expect(checked([parcel], at)).resolves.toHaveLength(off);
    vi.stubEnv('TRACKING_ENABLE_UNSEEN_RECHECKS', 'false');
    await expect(checked([parcel], at)).resolves.toHaveLength(off);
    vi.stubEnv('TRACKING_ENABLE_UNSEEN_RECHECKS', 'true');
    await expect(checked([parcel], at)).resolves.toHaveLength(on);
  });

  it('leaves lookups without an account as they are', async () => {
    vi.stubEnv('TRACKING_ENABLE_UNSEEN_RECHECKS', 'true');
    const lookup = unseen('lookup', { user_id: null, one_off: true, last_synced_at: '2026-09-02T06:00:15Z' });
    const client = fakeClient();
    client.listFollowedOneOffPackages.mockResolvedValue([lookup]);
    // Off the schedule six hours after it was added, where an account's parcel gets its recheck.
    await expect(checked([], '2026-09-02T10:00:00Z', client)).resolves.toEqual([]);
    await expect(checked([unseen('owned', { last_synced_at: '2026-09-02T06:00:15Z' })], '2026-09-02T10:00:00Z')).resolves.toEqual(['owned']);
    // Opening one of its links still checks it daily, as it does an account's parcel.
    expect(isOpenedParcelSyncDue(lookup, new Date('2026-09-02T13:00:00Z'))).toBe(true);
    const owned = unseen('owned', { last_synced_at: '2026-09-04T10:00:15Z' });
    expect(isOpenedParcelSyncDue(owned, new Date('2026-09-05T11:00:00Z'))).toBe(true);
  });

  it('rechecks after every other parcel and lookup, within its account\'s share, three a run', async () => {
    vi.stubEnv('TRACKING_ENABLE_UNSEEN_RECHECKS', 'true');
    const regular = (id: string, user: string): JsonObject => ({ id, user_id: user, carrier: 'swiss-post', current_stage: 'in_transit',
      tracking_number: `TEST${id}`, created_at: '2026-09-03T09:00:00Z', last_synced_at: null, sync_status: 'ok', archived_at: null });
    const recheck = (id: string, user: string) => unseen(id, { user_id: user, tracking_number: `TEST${id}`, last_synced_at: '2026-09-02T10:00:15Z' });
    const parcels = [recheck('a6', 'a'), ...['a1', 'a2', 'a3', 'a4', 'a5'].map((id) => regular(id, 'a')),
      recheck('b1', 'b'), recheck('b2', 'b'), recheck('b3', 'b'), recheck('b4', 'b'), recheck('c1', 'c')];
    const client = fakeClient(parcels);
    client.listFollowedOneOffPackages.mockResolvedValue([{ ...regular('lookup', 'x'), user_id: null, one_off: true }]);
    // Account a's five regular parcels take its share; b and c share three rechecks after the lookup.
    await expect(checked(parcels, '2026-09-04T10:00:00Z', client)).resolves.toEqual(['a1', 'a2', 'a3', 'a4', 'a5', 'lookup', 'b1', 'c1', 'b2']);
    // Only the rechecks were read again.
    expect(client.getPackage.mock.calls.map(([id]) => id)).toEqual(['b1', 'c1', 'b2']);
  });

  it('skips a recheck when another check ran since the run listed it, or it cannot tell', async () => {
    vi.stubEnv('TRACKING_ENABLE_UNSEEN_RECHECKS', 'true');
    const parcel = unseen('unseen', { last_synced_at: '2026-09-02T10:00:15Z' });
    const at = '2026-09-04T10:00:00Z';
    const reread = async (current: () => Promise<JsonObject | null>) => {
      const client = fakeClient([parcel]);
      client.getPackage.mockImplementation(current);
      return await checked([parcel], at, client);
    };
    await expect(reread(async () => ({ ...parcel }))).resolves.toEqual(['unseen']);
    await expect(reread(async () => ({ ...parcel, last_synced_at: '2026-09-04T09:59:00Z' }))).resolves.toEqual([]);
    await expect(reread(async () => null)).resolves.toEqual([]);
    const capture = vi.spyOn(observability, 'captureOperationalError').mockReturnValue(null);
    const failure = new Error('unavailable');
    await expect(reread(async () => { throw failure; })).resolves.toEqual([]);
    expect(capture).toHaveBeenCalledWith(failure, { component: 'tracking', operation: 'reread_unseen_recheck' });
    // The next run, after its check, finds it done.
    await expect(checked([{ ...parcel, last_synced_at: '2026-09-04T10:00:05Z' }], '2026-09-04T10:02:00Z')).resolves.toEqual([]);
  });

  // The universal providers a check asks of a number no carrier or provider knows.
  const asked = async (check: (service: TrackingSyncService) => Promise<unknown>, parcels: JsonObject[], at = '2026-09-04T10:00:00Z') => {
    const client = { ...fakeClient(parcels), acquireTrackingProvider: vi.fn().mockResolvedValue({ token: 'lease', retry_at: '' }),
      finishTrackingProvider: vi.fn().mockResolvedValue(undefined) };
    const adapter = { fetch: vi.fn().mockRejectedValue(new NotFoundError('Carrier')),
      fetchUniversal: vi.fn().mockRejectedValue(new NotFoundError('Provider')) };
    const service = new TrackingSyncService(client as unknown as SupabaseServiceClient, adapter, null, () => new Date(at));
    await check(service);
    return adapter.fetchUniversal.mock.calls.map(([source]) => source);
  };
  const every = ['ParcelsApp', 'Ship24', '17TRACK'];

  it('asks one universal provider at a recheck nobody asked for, each one when someone asks', async () => {
    const parcel = unseen('unseen', { last_synced_at: '2026-09-02T10:00:15Z' });
    await expect(asked((service) => service.sync(), [parcel])).resolves.toEqual(every);
    vi.stubEnv('TRACKING_ENABLE_UNSEEN_RECHECKS', 'true');
    await expect(asked((service) => service.sync(), [parcel])).resolves.toEqual(['ParcelsApp']);
    await expect(asked((service) => service.syncPackage(parcel, { trigger: 'release' }), [parcel])).resolves.toEqual(['ParcelsApp']);
    await expect(asked((service) => service.syncPackage(parcel, { trigger: 'refresh' }), [parcel])).resolves.toEqual(every);
  });

  it('asks one universal provider at a release\'s re-check of a lookup that left the schedule unseen, with its flag', async () => {
    const lookup = unseen('lookup', { user_id: null, one_off: true, last_synced_at: '2026-09-02T10:00:15Z' });
    const release = (parcel: JsonObject) => (service: TrackingSyncService) => service.syncPackage(parcel, { trigger: 'release' });
    vi.stubEnv('TRACKING_ENABLE_UNSEEN_RECHECKS', 'true');
    await expect(asked(release(lookup), [])).resolves.toEqual(every);
    vi.stubEnv('TRACKING_ENABLE_SPARSE_LOOKUP_RECHECKS', 'true');
    await expect(asked(release(lookup), [])).resolves.toEqual(['ParcelsApp']);
    await expect(asked((service) => service.syncPackage(lookup, { trigger: 'refresh' }), [])).resolves.toEqual(every);
    // Five hours after it was added, the lookup is still on its hourly schedule.
    await expect(asked(release({ ...lookup, last_synced_at: null }), [], '2026-09-01T14:00:00Z')).resolves.toEqual(every);
  });
});
