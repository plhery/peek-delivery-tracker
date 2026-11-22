import { amazonOrdersUrl, isAmazonTrackingNumber, requiresAmazonAccount } from '../lib/amazon';
import { useGuidePages } from '../guides/GuidePagesContext';
import { guideHref } from '../guides/paths';
import { trackAction } from '../lib/analytics';
import { isDemoBuild } from '../lib/buildMode';
import { userErrorMessage } from '../lib/userMessages';
import { useEffect, useLayoutEffect, useRef, useState, type FocusEvent, type FormEvent } from 'react';
import { createPortal } from 'react-dom';
import {
  type CarrierInputField,
  carrierInfo,
  carrierRequirements,
  carrierTrackingHintKey,
  formatTrackingNumber,
  isPickupCode,
  normalizeTrackingNumber,
  notTrackingNumber,
  parseTrackingInput,
  requirementHelpKey,
  requirementSatisfied,
  tracksAutomatically,
  typedRequirementValue,
} from '../lib/carriers';
import { countryName } from 'universal-parcel-scraper/app';
import { deviceCountry, deviceRegion, lettersPostcode, postcodeExample, timeZoneRegion } from '../lib/postcodeExample';
import { initialPostcodeCountry, postcodeCountries, postcodeFieldFor, sentPostcodeCountry } from '../lib/postcodeCountry';
import { rememberedPostcodeField, type GivenPostcode } from '../lib/postcodeMemory';
import { canAskCarrier, carrierCheck, carrierChoiceSections, carrierChoiceTags, carrierNameList, shapeCarrier } from '../lib/carrierPicker';
import {
  ParcelAlreadyExistsError,
  type CarrierId,
  type NewParcelInput,
} from '../types';
import { useSheetDialog } from '../lib/modal';
import { useI18n } from '../i18n';
import { Icon } from './Icon';
import { CarrierTruck } from './CarrierMark';
import { CarrierPickerSheet } from './CarrierPickerSheet';
import { PostcodeCountry } from './PostcodeCountry';
import { LookupCountrySentence } from './LookupCountry';
import './AddParcelSheet.css';
import { useTypingPause } from '../lib/typingPause';
import { FoundNumber } from '../peek/lookup/FoundNumber';
import { lookupCarrier } from '../lib/carrierDetection';
import { useNumberRefusal } from '../lib/numberRefusal';
import type { ApiAuth } from '../lib/apiClient';
import type { ApiCarrierDetectionResponse } from '../generated/apiContract';

/** Scroll only the sheet's field list, never the page under the fixed sheet. */
function revealFocusedField(list: HTMLElement | null) {
  const field = document.activeElement;
  if (!list || !(field instanceof HTMLElement) || !list.contains(field)) return;
  const focusRing = 4;
  const visible = list.getBoundingClientRect();
  const target = field.getBoundingClientRect();
  if (target.bottom + focusRing > visible.bottom) list.scrollTop += target.bottom + focusRing - visible.bottom;
  else if (target.top - focusRing < visible.top) list.scrollTop -= visible.top - target.top + focusRing;
}

function isTextField(element: unknown): element is HTMLInputElement | HTMLTextAreaElement {
  return element instanceof HTMLInputElement || element instanceof HTMLTextAreaElement;
}

export function AddParcelSheet({
  onAdd,
  onClose: onDismissed,
  postcodes = [],
  initialLabel = '',
  initialTrackingInput = '',
  shareError,
  onOpenParcel,
  onAdded,
  apiAuth,
  usedCarriers = [],
}: {
  onAdd: (input: NewParcelInput) => Promise<{ id: string } | void>;
  onClose: () => void;
  onOpenParcel?: (parcelId: string) => void;
  onAdded?: (parcelId: string) => void;
  /** The postcodes given for earlier parcels, newest first. */
  postcodes?: readonly GivenPostcode[];
  initialLabel?: string;
  initialTrackingInput?: string;
  /** Why something shared to the installed app did not come through, said where a failed paste is. */
  shareError?: string;
  apiAuth?: ApiAuth;
  /** The carriers of the latest parcels, offered first in the picker. */
  usedCarriers?: readonly CarrierId[];
}) {
  const { locale, languageTag, t } = useI18n();
  const [label, setLabel] = useState(initialLabel);
  const [trackingInputValue, setTrackingInputValue] = useState(initialTrackingInput);
  // The input as it stood when typing last paused, or right after a paste.
  const [settledTrackingInput, setSettledTrackingInput] = useState(initialTrackingInput);
  const [carrierInputs, setCarrierInputs] = useState<Record<Exclude<CarrierInputField, 'phoneDigits'>, string>>({
    trackingUrl: '',
    dpdPostcode: '',
  });
  const [phoneInput, setPhoneInput] = useState<{ number: string; carrier: CarrierId; digits: string }>();
  // Each carrier's postcode is kept apart: one typed for a carrier never goes to another.
  const [carrierPostcodes, setCarrierPostcodes] = useState<Partial<Record<CarrierId, string>>>({});
  // And the country each is from, chosen beside it.
  const [postcodeCountryChoices, setPostcodeCountryChoices] = useState<Partial<Record<CarrierId, string>>>({});
  const [region] = useState(deviceCountry);
  const [dialling] = useState(deviceRegion);
  const [selectedCarrier, setSelectedCarrier] = useState<CarrierId | 'auto'>('auto');
  const [verifiedCarrier, setVerifiedCarrier] = useState<ApiCarrierDetectionResponse>();
  // Carrier recognition: a shape several carriers share is checked with them
  // once the number is settled (the field loses focus, a paste, a shared
  // number), never on each keystroke. It never holds the Add button: after
  // saving, the first sync asks the same carriers again.
  const [recognitionNumber, setRecognitionNumber] = useState(() =>
    normalizeTrackingNumber(parseTrackingInput(initialTrackingInput).trackingNumber));
  // Each answer and failure is for a number as asked from a country.
  const [recognition, setRecognition] = useState<{ country: string | null; answer: ApiCarrierDetectionResponse }>();
  const [recognitionFailure, setRecognitionFailure] = useState<{ number: string; country: string | null }>();
  // The carriers are asked as from the browser clock's country, else the server takes the IP's; the reader can
  // pick another for this number, and the parcel added from here keeps it. It is never saved as a setting.
  const [clockCountry] = useState(timeZoneRegion);
  const [pickedCountry, setPickedCountry] = useState<{ number: string; country: string }>();
  const [lookupAttempt, setLookupAttempt] = useState(0);
  const [pickerOpen, setPickerOpen] = useState(false);
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [existingParcelId, setExistingParcelId] = useState<string | null>(null);
  const [pasteError, setPasteError] = useState<string | null>(shareError ?? null);
  const trackingInput = useRef<HTMLTextAreaElement>(null);
  const pastingTrackingInput = useRef(false);
  const backdrop = useRef<HTMLDivElement>(null);
  const fields = useRef<HTMLDivElement>(null);
  const refocusing = useRef(false);
  const saved = useRef<string | null>(null);
  const mounted = useRef(true);
  useEffect(() => {
    mounted.current = true;
    return () => { mounted.current = false; };
  }, []);
  const [dialog, onClose] = useSheetDialog<HTMLDivElement>(true, () => {
    onDismissed();
    if (saved.current) onAdded?.(saved.current);
  }, trackingInput);

  // Mobile keyboards resize the visual viewport without resizing the layout
  // viewport. Keep the full-screen form and its action inside the visible area.
  useEffect(() => {
    const viewport = window.visualViewport;
    const element = backdrop.current;
    if (!viewport || !element) return;
    const update = () => {
      // Leave native pinch zoom alone rather than shrinking the form with it.
      if (viewport.scale !== 1) return;
      element.style.setProperty('--add-viewport-height', `${viewport.height}px`);
      element.style.setProperty('--add-viewport-top', `${viewport.offsetTop}px`);
      // The keyboard covers the home indicator, so its safe-area padding goes.
      element.toggleAttribute('data-keyboard', viewport.height < window.innerHeight - 100);
      revealFocusedField(fields.current);
    };
    update();
    viewport.addEventListener('resize', update);
    viewport.addEventListener('scroll', update);
    return () => {
      viewport.removeEventListener('resize', update);
      viewport.removeEventListener('scroll', update);
    };
  }, []);

  // One line fits a number; grow for pasted links and shipping messages.
  useLayoutEffect(() => {
    const input = trackingInput.current;
    if (!input) return;
    input.style.height = '';
    input.style.height = `${input.scrollHeight + input.offsetHeight - input.clientHeight}px`;
  }, [trackingInputValue]);

  useTypingPause(trackingInputValue, setSettledTrackingInput);

  const parsedTracking = parseTrackingInput(trackingInputValue);
  const trackingNumber = parsedTracking.trackingNumber;
  // Half-typed input is not a mistake: the notice and the carrier picker wait
  // for a pause or a paste, then stay put while editing continues.
  const settledTrackingNumber = parseTrackingInput(settledTrackingInput).trackingNumber;
  const trackingNotFound = Boolean(trackingInputValue.trim() && !trackingNumber
    && settledTrackingInput.trim() && !settledTrackingNumber);
  const normalizedNumber = normalizeTrackingNumber(trackingNumber);
  const amazonNumber = isAmazonTrackingNumber(normalizedNumber);
  // Amazon numbers must be verified before they can be added; a placeholder is asked about nowhere.
  const shouldLookup = Boolean(apiAuth) && amazonNumber && !notTrackingNumber(normalizedNumber, dialling);
  const lookingUp = shouldLookup && verifiedCarrier?.trackingNumber !== normalizedNumber;
  const currentVerification = verifiedCarrier?.trackingNumber === normalizedNumber ? verifiedCarrier : undefined;
  // The check keeps running when a carrier is picked by hand meanwhile: its
  // answer then only says when another carrier has the parcel.
  const recognizable = Boolean(apiAuth) && !amazonNumber && canAskCarrier(parsedTracking, normalizedNumber, dialling);
  const picked = pickedCountry?.number === normalizedNumber ? pickedCountry.country : undefined;
  const sentCountry = picked ?? clockCountry;
  const currentRecognition = recognizable && recognition?.answer.trackingNumber === normalizedNumber
    && recognition.country === sentCountry ? recognition.answer : undefined;
  const check = carrierCheck({
    applies: recognizable,
    settled: recognitionNumber === normalizedNumber,
    answer: currentRecognition,
    failed: recognitionFailure?.number === normalizedNumber && recognitionFailure.country === sentCountry,
  });
  const recognizing = check.status === 'asking';
  // In automatic mode the answer selects the carrier; for a picked one it stays a hint.
  const recognizedCarrier = selectedCarrier === 'auto' && check.status === 'found' ? check.carrier : undefined;
  const shippingConfirmed = amazonNumber && currentVerification?.carrier === 'amazon-shipping'
    && ['available', 'expired'].includes(currentVerification.amazonShippingStatus ?? '');
  const accountRequired = amazonNumber ? !shippingConfirmed : requiresAmazonAccount(selectedCarrier);
  // Amazon's carriers can be tracked: the guide shows where an Amazon order's tracking number is.
  const amazonGuide = useGuidePages() ? guideHref(locale, 'amazon-orders') : null;
  const detectedCarrier = shouldLookup && currentVerification ? currentVerification.carrier
    : recognizedCarrier ?? parsedTracking.carrier;
  const resolvedCarrier = amazonNumber ? shippingConfirmed ? 'amazon-shipping' : 'amazon-logistics'
    : selectedCarrier === 'auto' ? detectedCarrier : selectedCarrier;
  // Six digits are a pickup code unless a carrier picked by hand takes them as its number. The start of a
  // longer number is still being typed: its notice waits for a pause.
  const pickupCode = selectedCarrier === 'auto' && isPickupCode(normalizedNumber);
  const pickupNotice = pickupCode && normalizeTrackingNumber(settledTrackingNumber) === normalizedNumber;
  // A placeholder, such as the app's demo number, or a phone number is held back in the same way, whichever the
  // carrier: no carrier issues either. A demo build looks nothing up.
  const placeholder = !isDemoBuild && !pickupCode && notTrackingNumber(normalizedNumber, dialling);
  const placeholderNotice = placeholder && normalizeTrackingNumber(settledTrackingNumber) === normalizedNumber;
  useEffect(() => {
    if (!shouldLookup || !apiAuth) return;
    const controller = new AbortController();
    const timer = setTimeout(() => {
      void lookupCarrier(normalizedNumber, apiAuth, controller.signal).then((result) => {
        if (!controller.signal.aborted) setVerifiedCarrier(result);
      }).catch(() => {
        if (!controller.signal.aborted) setVerifiedCarrier({ trackingNumber: normalizedNumber, carrier: amazonNumber ? 'amazon-logistics' : 'unknown', ...(amazonNumber ? { amazonShippingStatus: 'unavailable' as const } : {}) });
      });
    }, 350);
    return () => { clearTimeout(timer); controller.abort(); };
  }, [shouldLookup, normalizedNumber, apiAuth, amazonNumber, lookupAttempt]);
  useEffect(() => {
    if (!recognizable || !apiAuth || saving || recognitionNumber !== normalizedNumber) return;
    const controller = new AbortController();
    // A tap on Add also blurs the field; the short wait lets that save go
    // first instead of asking the carriers the first sync asks anyway.
    const timer = setTimeout(() => {
      setRecognition(undefined);
      setRecognitionFailure(undefined);
      // As typed: its dashes and spaces tell some carriers' numbers apart.
      void lookupCarrier(trackingNumber, apiAuth, controller.signal, sentCountry).then((answer) => {
        if (!controller.signal.aborted) setRecognition({ country: sentCountry, answer });
      }).catch(() => {
        // No answer keeps the number a suggestion; the first sync asks again.
        if (!controller.signal.aborted) {
          setRecognitionFailure({ number: normalizedNumber, country: sentCountry });
        }
      });
    }, 200);
    return () => { clearTimeout(timer); controller.abort(); };
  }, [recognizable, recognitionNumber, normalizedNumber, trackingNumber, sentCountry, apiAuth, saving]);
  const knownElsewhere = selectedCarrier !== 'auto' && check.status === 'found' && check.carrier !== selectedCarrier
    ? carrierInfo(check.carrier, locale) : null;
  const carrier = trackingNumber ? carrierInfo(resolvedCarrier, locale) : null;
  // A carrier no service knew the number for may still have it, and answers only with the delivery postcode
  // (Mondial Relay's 8-digit numbers): its postcode is asked for, optional, and the parcel goes to that carrier
  // only with it.
  const suggested = selectedCarrier === 'auto' && !amazonNumber && resolvedCarrier === 'unknown'
    && currentRecognition?.inputSuggested?.field === 'dpdPostcode' && ['none', 'unasked', 'failed'].includes(check.status)
    ? currentRecognition.inputSuggested.carrier : undefined;
  // The carrier the fields are for.
  const inputCarrier = suggested ?? resolvedCarrier;
  const chosenCountry = carrier ? postcodeCountryChoices[inputCarrier] : undefined;
  const carrierFields = !carrier ? [] : suggested
    ? carrierRequirements(suggested, trackingNumber).filter(({ field }) => field === 'dpdPostcode')
      .map((requirement) => ({ ...requirement, optional: true }))
    : carrierRequirements(carrier.id, trackingNumber);
  // A required postcode starts from one given before, with its country; an optional one is only offered.
  const { value: postcodeValue, offered: offeredPostcode, country: rememberedCountry } = rememberedPostcodeField(inputCarrier,
    trackingNumber, carrierFields.find(({ field }) => field === 'dpdPostcode'), carrierPostcodes[inputCarrier], postcodes, chosenCountry);
  // A postcode is from the country the reader names, the remembered one's, or the device's when the carrier takes it.
  const postcodeCountry = carrier
    ? chosenCountry ?? initialPostcodeCountry(inputCarrier, trackingNumber, [rememberedCountry, region]) : null;
  const requirements = carrierFields
    .map((requirement) => postcodeFieldFor(requirement, inputCarrier, trackingNumber, postcodeCountry));
  const requiresCarrierConfirmation =
    selectedCarrier === 'auto' && parsedTracking.confidence === 'low'
    && !tracksAutomatically(parsedTracking.carrier);
  const parsedCarrierTrackingUrl =
    parsedTracking.carrier === resolvedCarrier ? parsedTracking.trackingUrl : undefined;
  const carrierInputValue = (field: CarrierInputField) =>
    field === 'trackingUrl' && parsedCarrierTrackingUrl
      ? parsedCarrierTrackingUrl
      : field === 'phoneDigits' ? phoneInput?.number === normalizedNumber && phoneInput.carrier === resolvedCarrier ? phoneInput.digits : ''
        : field === 'dpdPostcode' ? postcodeValue : carrierInputs[field];
  const requirementsSatisfied = requirements.every((requirement) =>
    requirementSatisfied(requirement, carrierInputValue(requirement.field)));
  const carrierHint = carrier
    ? shippingConfirmed ? t(currentVerification?.amazonShippingStatus === 'expired' ? 'add.amazonHistoryExpired' : 'add.amazonShippingConfirmed') : requiresCarrierConfirmation
      ? t('add.confirmCarrier', {
        carriers: carrierNameList(parsedTracking.candidates, locale, languageTag, 'disjunction'),
      })
      : t(carrierTrackingHintKey(carrier.id), { carrier: carrier.name })
    : '';
  const carrierNames = (ids: readonly CarrierId[]) => carrierNameList(ids, locale, languageTag);
  const automatic = selectedCarrier === 'auto';
  // The carrier line under the number: the carrier once one is known, and
  // otherwise what the carrier check has found so far.
  const lineCarrier = carrier && carrier.id !== 'unknown' ? carrier : null;
  const numberCarrier = shapeCarrier(parsedTracking);
  const preflightDetail = currentRecognition?.carrier !== 'unknown' ? undefined : currentRecognition.trackingFound ? t('add.historyFound')
    : currentRecognition.providers?.some(({ outcome }) => outcome === 'input_required') ? t('add.providerInputNeeded') : undefined;
  // A carrier picked by hand that issues no number of this shape is pointed out before the parcel is added.
  const refused = useNumberRefusal(!automatic && !amazonNumber ? selectedCarrier : null,
    normalizeTrackingNumber(settledTrackingNumber) === normalizedNumber ? normalizedNumber : null);
  // The carriers asked, and so a number found nowhere, depend on the country: the line names it, and the reader can
  // pick another for this number.
  const askedFor = ['asking', 'none', 'failed'].includes(check.status) && !preflightDetail
    ? currentRecognition ? currentRecognition.lookupCountry ?? null : sentCountry : null;
  const lineCountry = automatic && !amazonNumber && !(lineCarrier && lineCarrier.id !== 'intl-post') ? askedFor : null;
  const lineDetail = automatic && preflightDetail ? preflightDetail : !automatic ? t(refused ? 'add.line.refused' : 'add.line.chosen')
    : check.status === 'found' ? t('add.line.found')
      : amazonNumber || (lineCarrier && lineCarrier.id !== 'intl-post') ? t('add.detectedCarrier')
        : check.status === 'asking' ? t('add.checkingServices')
          : check.status === 'several' ? t('add.line.several', { carriers: carrierNames(check.carriers) })
            : check.status === 'none' ? t('add.line.none')
              : check.status === 'failed' ? t('add.line.failed')
                : check.status === 'unasked' ? t('add.line.later') : '';
  const choosing = automatic && (check.status === 'several' || requiresCarrierConfirmation);
  // The picker leads with the carriers that fit the number, then the ones used before.
  const pickerSections = carrierChoiceSections({ detection: parsedTracking, check, used: usedCarriers, t });
  const pickerTags = carrierChoiceTags(check, t);
  const statusDescription = preflightDetail ?? (check.status === 'asking' ? t('add.checkingServices')
    : check.status === 'found' ? t('add.recognized', { carrier: carrierInfo(check.carrier, locale).name })
      : check.status === 'several' ? t('picker.auto.several', { carriers: carrierNames(check.carriers) })
        : check.status === 'none' ? check.services ? t('picker.auto.servicesNone') : t('picker.auto.none', { carriers: carrierNames(check.asked) })
          : check.status === 'failed' ? (check.services ? t('picker.auto.servicesFailed')
            : check.asked.length ? t('picker.auto.failed', { carriers: carrierNames(check.asked) }) : t('add.line.failed'))
            : numberCarrier ? t('picker.auto.detected', { carrier: carrierInfo(numberCarrier, locale).name })
              : t('picker.auto.later'));
  const withCountry = (text: string, country: string | null) =>
    country ? t('add.line.country', { country: countryName(country, languageTag), text }) : text;
  const autoDescription = withCountry(statusDescription, askedFor);
  const lineName = lineCarrier?.name ?? t('add.detect');
  const changeLabel = t(choosing ? 'add.line.choose' : 'add.line.change');

  // iPhone Safari scrolls the page to center every newly focused field above
  // the keyboard. That animated scroll drags this fixed sheet away from the
  // visual viewport and flashes the app header until the sheet catches up.
  // The phone sheet already fits the visible area, so refocus without
  // scrolling. Safari only applies preventScroll to a newly focused field, so
  // the refocus hops through another field before Safari sends its update.
  function focusWithoutPageScroll(event: FocusEvent<HTMLDivElement>) {
    const field = event.target;
    const ios = /iPad|iPhone|iPod/.test(navigator.userAgent) || (navigator.platform === 'MacIntel' && navigator.maxTouchPoints > 1);
    if (refocusing.current || !ios || !isTextField(field) || !window.matchMedia?.('(max-width: 760px)').matches) return;
    const other = Array.from(event.currentTarget.querySelectorAll('input, textarea')).find((element) => element !== field);
    if (!isTextField(other)) return;
    refocusing.current = true;
    other.focus({ preventScroll: true });
    field.focus({ preventScroll: true });
    refocusing.current = false;
    // Script focus restores or selects text; leave the caret after it instead.
    field.setSelectionRange(field.value.length, field.value.length);
    revealFocusedField(fields.current);
  }

  async function pasteTrackingInput() {
    setPasteError(null);
    try {
      const text = await navigator.clipboard.readText();
      if (!text.trim()) throw new Error('Clipboard is empty');
      trackAction('parcel-paste', 'success');
      setTrackingInputValue(text);
      setSettledTrackingInput(text);
      setRecognitionNumber(normalizeTrackingNumber(parseTrackingInput(text).trackingNumber));
    } catch {
      setPasteError(t('add.pasteFailed'));
    }
  }

  async function handleSubmit(e: FormEvent) {
    e.preventDefault();
    if (accountRequired || pickupCode || placeholder || !trackingNumber.trim() || lookingUp || requiresCarrierConfirmation || !requirementsSatisfied || saving) return;
    setSaving(true);
    setError(null);
    setExistingParcelId(null);
    const postcode = requirements.some(({ field }) => field === 'dpdPostcode')
      ? carrierInputValue('dpdPostcode').trim().toUpperCase()
      : undefined;
    // A suggested carrier goes only with its postcode.
    const savedCarrier = suggested && postcode ? suggested : resolvedCarrier;
    try {
      const parcel = await onAdd({
        trackingNumber: trackingNumber.trim(),
        label: label.trim(),
        carrier: savedCarrier,
        trackingUrl: requirements.some(({ field }) => field === 'trackingUrl')
          ? carrierInputValue('trackingUrl').trim()
          : undefined,
        dpdPostcode: suggested && !postcode ? undefined : postcode,
        dpdPostcodeCountry: postcode ? sentPostcodeCountry(savedCarrier, postcodeCountry) : undefined,
        phoneDigits: requirements.some(({ field }) => field === 'phoneDigits')
          ? carrierInputValue('phoneDigits').trim() || undefined
          : undefined,
        lookupCountry: picked,
      });
      if (!mounted.current) return;
      saved.current = parcel?.id ?? null;
      onClose();
    } catch (err) {
      if (!mounted.current) return;
      if (err instanceof ParcelAlreadyExistsError) {
        setError(t('add.alreadyExists'));
        setExistingParcelId(err.parcelId);
      } else {
        setError(userErrorMessage(err, t, 'add.failed'));
      }
      setSaving(false);
    }
  }

  return <>{createPortal(
    <div ref={backdrop} className="sheet-backdrop add-parcel-backdrop" onClick={onClose}>
      <div
        ref={dialog}
        className="sheet add-parcel-sheet"
        role="dialog"
        aria-modal="true"
        aria-labelledby="add-parcel-title"
        tabIndex={-1}
        onClick={(e) => e.stopPropagation()}
        onFocus={focusWithoutPageScroll}
      >
        <div className="sheet__heading">
          <div>
            <h2 className="sheet__title" id="add-parcel-title">{t('add.title')}</h2>
          </div>
          <button
            type="button"
            className="sheet__close"
            aria-label={t('common.close')}
            onClick={onClose}
          >
            <Icon name="close" />
          </button>
        </div>
        <form onSubmit={handleSubmit} className="sheet__form">
          <div ref={fields} className="add-parcel-fields">
            <div className="add-parcel-tracking">
              <div className="field">
                <div className="field__label">
                  <label htmlFor="add-parcel-tracking">{t('add.tracking')}</label>
                  <button
                    type="button"
                    className="field__inline-action"
                    onClick={() => void pasteTrackingInput()}
                  >
                    <Icon name="copy" />{t('add.paste')}
                  </button>
                </div>
                <textarea
                  id="add-parcel-tracking"
                  className="field__input field__input--tracking"
                  ref={trackingInput}
                  value={trackingInputValue}
                  placeholder={t('add.trackingPlaceholder')}
                  aria-describedby={pasteError ? 'add-parcel-paste-error' : undefined}
                  onPaste={() => { pastingTrackingInput.current = true; }}
                  onBlur={(event) => {
                    // Leaving the field for Add saves instead; the first sync recognizes.
                    if (event.relatedTarget instanceof HTMLButtonElement && event.relatedTarget.type === 'submit') return;
                    setRecognitionNumber(normalizedNumber);
                  }}
                  onChange={(e) => {
                    setTrackingInputValue(e.target.value);
                    setPasteError(null);
                    if (pastingTrackingInput.current) {
                      setSettledTrackingInput(e.target.value);
                      setRecognitionNumber(normalizeTrackingNumber(parseTrackingInput(e.target.value).trackingNumber));
                    }
                    pastingTrackingInput.current = false;
                    if (existingParcelId) {
                      setExistingParcelId(null);
                      setError(null);
                    }
                  }}
                  autoCapitalize="characters"
                  autoCorrect="off"
                  spellCheck={false}
                  rows={1}
                  required
                />
              </div>
              {pasteError && <p className="sheet__error" id="add-parcel-paste-error" role="status">{pasteError}</p>}
              {trackingNotFound && (
                <div className="add-parcel-notice">
                  <p role="status">{t('add.notFound')}</p>
                </div>
              )}
              {pickupNotice && (
                <div className="add-parcel-notice">
                  <p role="status"><strong>{t('add.pickupCode.title')}</strong><br />{t('add.pickupCode.body')}</p>
                </div>
              )}
              {placeholderNotice && (
                <div className="add-parcel-notice">
                  <p role="status">{t('tracking.failure.placeholderNumber')}</p>
                </div>
              )}
              {parsedTracking.source !== 'number' && trackingNumber && (
                <FoundNumber className="sheet__carrier-hint" number={formatTrackingNumber(trackingNumber, resolvedCarrier)}
                  source={parsedTracking.source === 'link' ? 'link' : 'text'} />
              )}
              {carrier && trackingNumber && (
                <div className={`add-parcel-carrier${amazonNumber ? ' add-parcel-carrier--account' : ''}`} aria-live="polite" aria-busy={lookingUp || recognizing}>
                  {amazonNumber ? (
                    <div className="add-parcel-carrier__line">
                      {lineCarrier ? <CarrierTruck carrier={lineCarrier} /> : <span className="add-parcel-carrier__detect"><Icon name="detect" /></span>}
                      <span className="add-parcel-carrier__identity">
                        <strong>{carrier.name}</strong>{' '}
                        <small>{lineDetail}</small>
                      </span>
                    </div>
                  ) : (
                    // The whole line opens the picker; the country named in it is a control of its own above it.
                    <div className="add-parcel-carrier__line add-parcel-carrier__line--picker">
                      {lineCarrier ? <CarrierTruck carrier={lineCarrier} /> : (
                        <span className={`add-parcel-carrier__detect${recognizing && automatic ? ' is-busy' : ''}`}><Icon name="detect" /></span>
                      )}
                      <span className="add-parcel-carrier__identity">
                        <strong>{lineName}</strong>{' '}
                        {lineDetail && <small className={choosing || refused ? 'is-attention' : undefined}>
                          {lineCountry ? <LookupCountrySentence country={lineCountry} home={clockCountry} text={lineDetail}
                            onChange={(country) => setPickedCountry({ number: normalizedNumber, country })} /> : lineDetail}
                        </small>}
                      </span>{' '}
                      <button
                        type="button"
                        className="add-parcel-carrier__change"
                        aria-haspopup="dialog"
                        aria-label={[lineName, withCountry(lineDetail, lineCountry), changeLabel].filter(Boolean).join(' ')}
                        onClick={() => setPickerOpen(true)}
                      >
                        {changeLabel}
                      </button>
                    </div>
                  )}
                  {(shippingConfirmed || requiresCarrierConfirmation || !tracksAutomatically(carrier.id)) && (
                    <p className="add-parcel-carrier__hint">{carrierHint}</p>
                  )}
                  {knownElsewhere && (
                    <div>
                      <p className="add-parcel-carrier__hint">{t('add.carrierKnownElsewhere', { carrier: knownElsewhere.name })}</p>
                      <button type="button" className="add-parcel-carrier__account-link" onClick={() => setSelectedCarrier(knownElsewhere.id)}>
                        {t('add.useCarrier', { carrier: knownElsewhere.name })}
                      </button>
                    </div>
                  )}
                  {amazonNumber && currentVerification?.amazonShippingStatus === 'unavailable' && (
                    <div>
                      <p className="add-parcel-carrier__hint">{t('add.amazonCheckUnavailable')}</p>
                      <button type="button" className="add-parcel-carrier__account-link" onClick={() => { setVerifiedCarrier(undefined); setLookupAttempt((attempt) => attempt + 1); }}>{t('add.amazonRetry')}</button>
                    </div>
                  )}
                  {accountRequired && (
                    <a className="add-parcel-carrier__account-link" href={amazonOrdersUrl(normalizedNumber)} target="_blank" rel="noopener noreferrer">
                      {t('add.openAmazonOrders')}
                    </a>
                  )}
                  {accountRequired && amazonGuide && (
                    <a className="add-parcel-carrier__account-link" href={amazonGuide} target="_blank" rel="noopener">{t('add.amazonGuide')}</a>
                  )}
                </div>
              )}
              {requirements
                .filter(({ field }) => field !== 'trackingUrl' || !parsedCarrierTrackingUrl)
                .map((requirement) => (
                  <label className="field" key={requirement.field}>
                    <span className="field__label">
                      {locale === 'en'
                        ? requirement.label
                        : t(`add.requirement.${requirement.field}`)}
                      {requirement.optional && <> <small>{t('add.optional')}</small></>}
                    </span>
                    <PostcodeCountry
                      carrier={inputCarrier}
                      countries={requirement.field === 'dpdPostcode' ? postcodeCountries(inputCarrier, trackingNumber) : []}
                      value={postcodeCountry}
                      onChange={(country) => setPostcodeCountryChoices((current) => ({ ...current, [inputCarrier]: country }))}
                    >
                      <input
                        className={`field__input${lettersPostcode(requirement) ? ' field__input--postcode' : ''}`}
                        type={requirement.type}
                        inputMode={requirement.inputMode}
                        autoComplete={requirement.autoComplete}
                        value={carrierInputValue(requirement.field)}
                        placeholder={postcodeExample(inputCarrier, requirement, requirement.readsCountry ? postcodeCountry : region)}
                        pattern={requirement.pattern}
                        maxLength={requirement.maxLength}
                        onChange={(event) => {
                          const value = typedRequirementValue(requirement, event.target.value);
                          if (requirement.field === 'dpdPostcode') {
                            setCarrierPostcodes((current) => ({
                              ...current,
                              [inputCarrier]: value,
                            }));
                          } else if (requirement.field === 'phoneDigits') {
                            setPhoneInput({ number: normalizedNumber, carrier: inputCarrier, digits: value });
                          } else {
                            setCarrierInputs((current) => ({
                              ...current,
                              [requirement.field]: value,
                            }));
                          }
                        }}
                        onBlur={requirement.field === 'dpdPostcode' ? () => setCarrierPostcodes((current) => ({
                          ...current,
                          [inputCarrier]: current[inputCarrier]?.trim(),
                        })) : undefined}
                        autoCapitalize={requirement.type === 'url' ? 'none' : lettersPostcode(requirement) ? 'characters' : undefined}
                        autoCorrect="off"
                        spellCheck={false}
                        required={!requirement.optional}
                      />
                    </PostcodeCountry>
                    {requirement.field === 'dpdPostcode' && offeredPostcode && (
                      <button
                        type="button"
                        className="field__suggestion"
                        onClick={() => {
                          setCarrierPostcodes((current) => ({ ...current, [inputCarrier]: offeredPostcode.postcode }));
                          // The postcode goes with the country it was given from.
                          const { country } = offeredPostcode;
                          if (country) setPostcodeCountryChoices((current) => ({ ...current, [inputCarrier]: country }));
                        }}
                      >
                        {t('add.usePostcode', { postcode: offeredPostcode.postcode })}
                      </button>
                    )}
                    {suggested ? (
                      <small className="field__help">
                        {t('detail.inputSuggested', { carrier: carrierInfo(suggested, locale).name })}
                      </small>
                    ) : requirement.help && (
                      <small className="field__help">
                        {t(requirementHelpKey(resolvedCarrier, requirement), { carrier: carrier?.name ?? '' })}
                      </small>
                    )}
                  </label>
                ))}
            </div>
            <label className="field">
              <span className="field__label">{t('design.parcelTitle')} <small>{t('add.optional')}</small></span>
              <input
                className="field__input"
                type="text"
                value={label}
                placeholder={t('add.contentsPlaceholder')}
                onChange={(e) => setLabel(e.target.value)}
                maxLength={80}
              />
            </label>
            {error && (
              <p className="sheet__error" role="alert">
                <span>{error}</span>
                {existingParcelId && onOpenParcel && (
                  <>{' '}
                    <a
                      href={`/?parcel=${encodeURIComponent(existingParcelId)}`}
                      onClick={(event) => {
                        event.preventDefault();
                        onClose();
                        onOpenParcel(existingParcelId);
                      }}
                    >
                      {t('add.openExisting')}
                    </a>
                  </>
                )}
              </p>
            )}
          </div>
          <div className="sheet__actions">
            <button
              type="button"
              className="text-button add-parcel-cancel"
              onClick={onClose}
            >
              {t('common.cancel')}
            </button>
            <button
              type="submit"
              className="button button--primary"
              disabled={
                !trackingNumber
                || accountRequired || pickupCode || placeholder || lookingUp || requiresCarrierConfirmation
                || !requirementsSatisfied
                || saving
              }
            >
              {saving ? t('add.adding') : t('app.addParcel')}
            </button>
          </div>
        </form>
      </div>
    </div>,
    document.body,
  )}
  {/* Outside the backdrop: a click in the picker must not reach the sheet's dismiss handler. */}
  {pickerOpen && (
    <CarrierPickerSheet
      selected={selectedCarrier}
      auto={{ description: autoDescription, recommended: !choosing, busy: recognizing }}
      sections={pickerSections}
      tags={pickerTags}
      onSelect={(choice) => { setSelectedCarrier(choice); setPickerOpen(false); }}
      onClose={() => setPickerOpen(false)}
    />
  )}
  </>;
}
