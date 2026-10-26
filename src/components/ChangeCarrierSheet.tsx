import { normalizeDeliveryPostcode } from 'universal-parcel-scraper';
import { isAmazonTrackingNumber } from '../lib/amazon';
import { userErrorMessage } from '../lib/userMessages';
import { useId, useRef, useState, type FormEvent } from 'react';
import { createPortal } from 'react-dom';
import {
  type CarrierInputField,
  carrierInfo,
  carrierRequirements,
  carrierTrackingHintKey,
  detectCarrierMatch,
  formatTrackingNumber,
  requirementSatisfied,
  typedRequirementValue,
} from '../lib/carriers';
import { deviceCountry, lettersPostcode, postcodeExample } from '../lib/postcodeExample';
import { initialPostcodeCountry, postcodeCountries, postcodeFieldFor, sentPostcodeCountry } from '../lib/postcodeCountry';
import { useI18n } from '../i18n';
import { useSheetDialog } from '../lib/modal';
import { useNumberRefusal } from '../lib/numberRefusal';
import type {
  CarrierId,
  ParcelCarrierInput,
  ParcelWithEvents,
} from '../types';
import { CarrierTruck } from './CarrierMark';
import { CarrierPickerSheet, type CarrierPickerSection } from './CarrierPickerSheet';
import { Icon } from './Icon';
import { PostcodeCountry } from './PostcodeCountry';

export function ChangeCarrierSheet({
  parcel,
  initialCarrier = parcel.carrier,
  usedCarriers = [],
  onChange,
  onClose: onDismissed,
}: {
  parcel: ParcelWithEvents;
  /** Preselected carrier, such as one that recognized the number and needs a postcode. */
  initialCarrier?: CarrierId;
  /** The carriers of the latest parcels, offered first in the picker. */
  usedCarriers?: readonly CarrierId[];
  onChange: (input: ParcelCarrierInput) => Promise<unknown>;
  onClose: () => void;
}) {
  const { locale, t } = useI18n();
  const detected = detectCarrierMatch(parcel.trackingNumber);
  // Automatic detection files the parcel as adding it would: under the carrier its number's
  // shape names, else under none, and Peek looks for it. A parcel filed under none already is.
  // An Amazon number is tracked through Amazon Shipping alone.
  const unfiled = parcel.carrier === 'unknown' || parcel.carrier === 'intl-post';
  const automaticCarrier: CarrierId | null = isAmazonTrackingNumber(parcel.trackingNumber) ? null
    : unfiled ? parcel.carrier : detected.confidence === 'high' ? detected.carrier : 'unknown';
  const [selectedCarrier, setSelectedCarrier] = useState<CarrierId | 'auto'>(
    automaticCarrier && unfiled && initialCarrier === parcel.carrier ? 'auto' : initialCarrier);
  const automatic = selectedCarrier === 'auto';
  const chosenCarrier = selectedCarrier === 'auto' ? automaticCarrier ?? parcel.carrier : selectedCarrier;
  const [trackingUrl, setTrackingUrl] = useState(initialCarrier === parcel.carrier ? parcel.trackingUrl ?? '' : '');
  const [dpdPostcode, setDpdPostcode] = useState(initialCarrier === parcel.carrier ? parcel.dpdPostcode ?? '' : '');
  // The country of the parcel's postcode, the home country when it was given without one; for a carrier without a
  // postcode yet, the parcel's destination or the device's country when the carrier takes it.
  const startingCountry = (id: CarrierId) => id === parcel.carrier && parcel.dpdPostcode
    ? parcel.dpdPostcodeCountry ?? initialPostcodeCountry(id, parcel.trackingNumber, [])
    : initialPostcodeCountry(id, parcel.trackingNumber, [parcel.destinationCountry, deviceCountry()]);
  const [postcodeCountry, setPostcodeCountry] = useState(() => startingCountry(
    selectedCarrier === 'auto' ? automaticCarrier ?? parcel.carrier : selectedCarrier));
  const [providerPostcode, setProviderPostcode] = useState('');
  const needsProviderPostcode = Boolean(parcel.providerInputNeeded && chosenCarrier === parcel.carrier);
  let normalizedProviderPostcode: string | undefined;
  if (needsProviderPostcode) { try { normalizedProviderPostcode = normalizeDeliveryPostcode(providerPostcode); } catch { /* The field stays invalid until complete. */ } }
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [pickerOpen, setPickerOpen] = useState(false);
  const carrierButton = useRef<HTMLButtonElement>(null);
  const fieldId = useId();
  const [dialog, onClose] = useSheetDialog<HTMLDivElement>(true, onDismissed, carrierButton);
  const carrier = carrierInfo(chosenCarrier, locale);
  const autoDescription = automaticCarrier && !['unknown', 'intl-post'].includes(automaticCarrier)
    ? t('picker.auto.detected', { carrier: carrierInfo(automaticCarrier, locale).name }) : t('picker.auto.change');
  // A carrier that issues no number of this shape is pointed out before the change is saved.
  const refused = useNumberRefusal(automatic ? null : chosenCarrier, parcel.trackingNumber);
  // The picker leads with automatic detection, then the parcel's own carrier unless it is filed automatically or
  // among the carriers its number fits, which come next, then the ones used before. So the carrier it has is in view
  // without scrolling, and one no longer offered is there at all.
  const fitting = (detected.confidence === 'high' ? [detected.carrier] : detected.candidates)
    .filter((id) => carrierInfo(id).capabilities.selectable);
  const own: CarrierId[] = (automaticCarrier && unfiled) || fitting.includes(parcel.carrier) ? [] : [parcel.carrier];
  const pickerSections: CarrierPickerSection[] = [
    { key: 'current', title: t('picker.section.current'), carriers: own },
    { key: 'fits', title: t(detected.confidence === 'high' ? 'picker.section.detected' : 'picker.section.fits'), carriers: fitting },
    { key: 'used', title: t('picker.section.used'), carriers: usedCarriers.filter((id) => !fitting.includes(id) && !own.includes(id)) },
  ].filter((section) => section.carriers.length > 0);
  const requirements = carrierRequirements(chosenCarrier, parcel.trackingNumber)
    .map((requirement) => postcodeFieldFor(requirement, chosenCarrier, parcel.trackingNumber, postcodeCountry));
  const valueFor = (field: CarrierInputField) => field === 'trackingUrl'
    ? trackingUrl
    : dpdPostcode;
  const requirementsSatisfied = (!needsProviderPostcode || Boolean(normalizedProviderPostcode)) && requirements.every((requirement) =>
    requirementSatisfied(requirement, valueFor(requirement.field)));
  const nextTrackingUrl = requirements.some(({ field }) => field === 'trackingUrl')
    ? trackingUrl.trim()
    : undefined;
  const nextPostcode = requirements.some(({ field }) => field === 'dpdPostcode')
    ? dpdPostcode.trim().toUpperCase()
    : undefined;
  const nextPostcodeCountry = nextPostcode ? sentPostcodeCountry(chosenCarrier, postcodeCountry) : undefined;
  const changed = Boolean(normalizedProviderPostcode) || chosenCarrier !== parcel.carrier
    || (nextTrackingUrl ?? '') !== (parcel.trackingUrl ?? '')
    || (nextPostcode ?? '') !== (parcel.dpdPostcode ?? '')
    || (Boolean(nextPostcode) && nextPostcodeCountry !== parcel.dpdPostcodeCountry);

  function selectCarrier(value: CarrierId | 'auto') {
    const id = value === 'auto' ? automaticCarrier : value;
    setSelectedCarrier(value);
    setTrackingUrl(id === parcel.carrier ? parcel.trackingUrl ?? '' : '');
    setDpdPostcode(id === parcel.carrier ? parcel.dpdPostcode ?? '' : '');
    setPostcodeCountry(startingCountry(id ?? parcel.carrier));
    setError(null);
  }

  async function submit(event: FormEvent) {
    event.preventDefault();
    if (!requirementsSatisfied || !changed || saving) return;
    setSaving(true);
    setError(null);
    try {
      await onChange({
        carrier: chosenCarrier,
        providerPostcode: normalizedProviderPostcode,
        trackingUrl: nextTrackingUrl,
        dpdPostcode: nextPostcode,
        dpdPostcodeCountry: nextPostcodeCountry,
      });
      onClose();
    } catch (reason) {
      setError(userErrorMessage(reason, t, 'detail.changeCarrierFailed'));
      setSaving(false);
    }
  }

  return <>{createPortal(
    <div className="sheet-backdrop" onClick={onClose}>
      <div
        ref={dialog}
        className="sheet change-carrier-sheet"
        role="dialog"
        aria-modal="true"
        aria-labelledby="change-carrier-title"
        tabIndex={-1}
        onClick={(event) => event.stopPropagation()}
      >
        <div className="sheet__grabber" aria-hidden="true" />
        <div className="sheet__heading">
          <div>
            <p className="sheet__eyebrow">{t('detail.label')}</p>
            <h2 className="sheet__title" id="change-carrier-title">
              {t('detail.changeCarrier')}
            </h2>
          </div>
          <button
            type="button"
            className="sheet__close"
            aria-label={t('common.close')}
            onClick={onClose}
          >
            ×
          </button>
        </div>
        <p className="sheet__intro">
          {t('detail.changeCarrierDescription', {
            number: formatTrackingNumber(parcel.trackingNumber, parcel.carrier),
          })}
        </p>
        <form className="sheet__form" onSubmit={submit}>
          <div className="field">
            <span className="field__label" id={`${fieldId}-label`}>{t('add.carrier')}</span>
            <button
              ref={carrierButton}
              type="button"
              className="field__input carrier-field"
              aria-haspopup="dialog"
              aria-labelledby={`${fieldId}-label ${fieldId}-value`}
              aria-describedby={`${fieldId}-hint`}
              onClick={() => setPickerOpen(true)}
            >
              {automatic ? <span className="carrier-picker__detect"><Icon name="detect" /></span> : <CarrierTruck carrier={carrier} />}
              <span className="carrier-field__name" id={`${fieldId}-value`}>{automatic ? t('add.detect') : carrier.name}</span>
              <Icon name="chevron" />
            </button>
            <small className={`field__help${refused ? ' is-attention' : ''}`} id={`${fieldId}-hint`}>
              {refused ? `${carrier.name} ${t('add.line.refused')}` : automatic ? autoDescription
                : t(carrierTrackingHintKey(carrier.id), { carrier: carrier.name })}
            </small>
          </div>
          {requirements.map((requirement) => (
            <label className="field" key={requirement.field}>
              <span className="field__label">
                {locale === 'en'
                  ? requirement.label
                  : t(`add.requirement.${requirement.field}`)}
                {requirement.optional && <> <small>{t('add.optional')}</small></>}
              </span>
              <PostcodeCountry
                carrier={chosenCarrier}
                countries={requirement.field === 'dpdPostcode' ? postcodeCountries(chosenCarrier, parcel.trackingNumber) : []}
                value={postcodeCountry}
                onChange={setPostcodeCountry}
              >
                <input
                  className={`field__input${lettersPostcode(requirement) ? ' field__input--postcode' : ''}`}
                  type={requirement.type}
                  inputMode={requirement.inputMode}
                  autoComplete={requirement.autoComplete}
                  value={valueFor(requirement.field)}
                  placeholder={postcodeExample(chosenCarrier, requirement, postcodeCountry)}
                  pattern={requirement.pattern}
                  maxLength={requirement.maxLength}
                  onChange={(event) => {
                    const value = typedRequirementValue(requirement, event.target.value);
                    if (requirement.field === 'trackingUrl') setTrackingUrl(value);
                    else setDpdPostcode(value);
                  }}
                  onBlur={requirement.field === 'dpdPostcode' ? () => setDpdPostcode((value) => value.trim()) : undefined}
                  autoCapitalize={requirement.type === 'url' ? 'none' : lettersPostcode(requirement) ? 'characters' : undefined}
                  autoCorrect="off"
                  spellCheck={false}
                  required={!requirement.optional}
                />
              </PostcodeCountry>
              {requirement.help && (
                <small className="field__help">
                  {t(requirement.field === 'trackingUrl' ? 'add.requirement.trackingUrlHelp'
                    : requirement.optional ? 'add.requirement.dpdPostcodeOptionalHelp'
                      : 'add.requirement.dpdPostcodeHelp', { carrier: carrier.name })}
                </small>
              )}
            </label>
          ))}
          {needsProviderPostcode && <label className="field">
            <span className="field__label">{t('add.requirement.dpdPostcode')}</span>
            <input className="field__input" value={providerPostcode} maxLength={12} required autoComplete="postal-code"
              autoCapitalize="characters" autoCorrect="off" spellCheck={false}
              onChange={(event) => setProviderPostcode(event.target.value)} />
            <small className="field__help">{t('detail.providerInputNeeded', { provider: parcel.providerInputNeeded!.provider })}</small>
          </label>}
          {error && <p className="sheet__error" role="alert">{error}</p>}
          <div className="sheet__actions">
            <button
              type="button"
              className="button button--secondary"
              onClick={onClose}
              disabled={saving}
            >
              {t('common.cancel')}
            </button>
            <button
              type="submit"
              className="button button--primary"
              disabled={!requirementsSatisfied || !changed || saving}
            >
              {saving ? t('detail.changingCarrier') : t('detail.saveCarrier')}
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
      auto={automaticCarrier ? { description: autoDescription, recommended: false, busy: false } : undefined}
      sections={pickerSections}
      onSelect={(choice) => {
        selectCarrier(choice);
        setPickerOpen(false);
      }}
      onClose={() => setPickerOpen(false)}
    />
  )}
  </>;
}
