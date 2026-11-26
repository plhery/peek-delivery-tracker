import { useI18n } from '../../i18n';
import type { ShopLinkReading } from '../../components/shopLink/useShopLinkReading';
import { DoorNote } from './DoorNote';

/**
 * What the door says of a shop's tracking-page link that names no parcel, as Add does: the page is being read, the
 * shop hasn't shipped yet (signing in, or opening one's deliveries, waits for it there), or the page can't be read.
 */
export function DoorShopLink({ reading, signedIn, onWait }: {
  reading: ShopLinkReading;
  signedIn: boolean;
  /** Takes the link to the deliveries' Add, through signing in for a visitor, where Peek can wait for the shop. */
  onWait: () => void;
}) {
  const { t } = useI18n();
  if (reading.state === 'none') return null;
  if (reading.state === 'reading') return <DoorNote icon="hourglass"><p role="status">{t('shopLink.checking')}</p></DoorNote>;
  if (reading.state === 'failed') return <DoorNote icon="refresh" tone="warm"><p role="status">{t('shopLink.unavailable')}</p></DoorNote>;
  const { link } = reading;
  if (!link.waitable) return <DoorNote icon="receipt" tone="warm"><p role="status">{t('shopLink.unreadable')}</p></DoorNote>;
  const shop = link.shop_name;
  // Not shipped yet is no fault: it reads on the door's paper, not in the warning tone.
  return <DoorNote icon="hourglass" title={shop ? t('shopLink.noParcelYet', { shop }) : t('shopLink.noParcelYet.unnamed')}>
    <p role="status">{signedIn ? t('shopLink.waitHint') : t('shopLink.signIn')}</p>
    <button type="button" className="door-note__action" onClick={onWait}>
      {signedIn ? (shop ? t('shopLink.wait', { shop }) : t('shopLink.wait.unnamed')) : t('arrival.signInTitle')}
    </button>
  </DoorNote>;
}
