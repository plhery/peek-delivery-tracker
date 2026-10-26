import { useId, useState, type FormEvent } from 'react';
import { PostcodeCountry } from '../../components/PostcodeCountry';
import { useI18n, type MessageKey } from '../../i18n';
import { trackAction } from '../../lib/analytics';
import { carrierInfo } from '../../lib/carriers';
import { initialPostcodeCountry, postcodeCountries, sentPostcodeCountry } from '../../lib/postcodeCountry';
import { deviceCountry } from '../../lib/postcodeExample';
import type { CarrierId } from '../../types';
import { addParcelPostcode, ParcelLinkError, parcelLinkErrorKey, type ParcelLinkView } from '../links';
import { Sheet } from './Sheet';

/** The longest postcode a carrier is sent. */
const MAX_POSTCODE_LENGTH = 32;

/** The words asking for the postcode: another carrier holds the parcel, or its own `carrier` can't find it without one. */
function askedKey(own: boolean): MessageKey {
  return own ? 'detail.carrierInputNeeded' : 'detail.inputNeeded';
}

/**
 * Another carrier holds the parcel, or its own carrier (`own`) needs the postcode it has none of, and asks its owner
 * for it, as a parcel in the deliveries does.
 */
export function PostcodeAsked({ carrier, own, onAdd }: { carrier: CarrierId; own: boolean; onAdd: () => void }) {
  const { t, locale } = useI18n();
  return <div className="detail__input-needed peekp-asked" role="status">
    <p>{t(askedKey(own), { carrier: carrierInfo(carrier, locale).name })}</p>
    <button type="button" className="text-button" onClick={onAdd}>{t('detail.inputNeededAction')}</button>
  </div>;
}

/**
 * The owner adds the postcode, and the country it is from: the parcel's
 * destination when known, else the device's. The server asks the carrier
 * first: a postcode it accepts files the parcel under that carrier and checks
 * it at once, and the page shows the answer; one it refuses shows nothing of
 * the parcel.
 */
export function PostcodeSheet({ linkId, ownerKey, carrier, trackingNumber, destination, own, onAdded, onClose }: {
  linkId: string;
  ownerKey: string;
  carrier: CarrierId;
  /** The parcel's number, which the carrier is asked about. */
  trackingNumber: string;
  destination?: string;
  own: boolean;
  onAdded: (view: ParcelLinkView) => void;
  onClose: () => void;
}) {
  const { t, locale, languageTag } = useI18n();
  const field = useId();
  const name = carrierInfo(carrier, locale).name;
  const [postcode, setPostcode] = useState('');
  const [country, setCountry] = useState(() => initialPostcodeCountry(carrier, trackingNumber, [destination, deviceCountry()]));
  const [working, setWorking] = useState(false);
  const [error, setError] = useState<string | null>(null);

  function refusal(reason: unknown, now: Date): string {
    if (reason instanceof ParcelLinkError && reason.kind === 'refused') return t('link.postcode.refused', { carrier: name });
    if (reason instanceof ParcelLinkError && reason.kind === 'burst') {
      const until = new Date(now.getTime() + (reason.retryAfterSeconds ?? 60) * 1_000);
      return t('link.postcode.tries', { time: until.toLocaleTimeString(languageTag, { hour: '2-digit', minute: '2-digit' }) });
    }
    // The carrier could not be asked, or the service is in trouble: either way, later.
    if (reason instanceof ParcelLinkError && reason.kind === 'server') return t('link.postcode.unreachable', { carrier: name });
    const key: MessageKey = parcelLinkErrorKey(reason);
    return t(key === 'error.trackingNumber' ? 'error.postcode' : key);
  }

  async function add(event: FormEvent, dismiss: () => void) {
    event.preventDefault();
    if (working) return;
    if (!postcode.trim()) {
      setError(t('error.postcode'));
      return;
    }
    setWorking(true);
    setError(null);
    try {
      const view = await addParcelPostcode(linkId, ownerKey, carrier, postcode.trim(), sentPostcodeCountry(carrier, country));
      trackAction('parcel-link-postcode', 'success');
      onAdded(view);
      dismiss();
    } catch (reason) {
      trackAction('parcel-link-postcode', 'error');
      setError(refusal(reason, new Date()));
      setWorking(false);
    }
  }

  return <Sheet title={t('detail.inputNeededAction')} intro={t(askedKey(own), { carrier: name })} onClose={onClose}>
    {(dismiss) => <form onSubmit={(event) => void add(event, dismiss)} noValidate>
      <div className="peeks-gift">
        <label htmlFor={field}>{t('add.requirement.dpdPostcode')}</label>
        <PostcodeCountry carrier={carrier} countries={postcodeCountries(carrier, trackingNumber)} value={country} disabled={working}
          onChange={setCountry}>
          <input id={field} type="text" inputMode="text" autoComplete="postal-code" autoCapitalize="characters" spellCheck={false}
            maxLength={MAX_POSTCODE_LENGTH} value={postcode} disabled={working} aria-describedby={`${field}-private`}
            aria-invalid={error ? true : undefined} onChange={(event) => setPostcode(event.target.value)} />
        </PostcodeCountry>
        <small id={`${field}-private`}>{t('door.postcode.private', { carrier: name })}</small>
      </div>
      {error && <p className="sheet__error" role="alert">{error}</p>}
      <div className="peeks__actions">
        <button type="submit" className="button button--primary" disabled={working} aria-busy={working || undefined}>
          {t(working ? 'link.postcode.checking' : 'detail.inputNeededAction', { carrier: name })}
        </button>
      </div>
    </form>}
  </Sheet>;
}
