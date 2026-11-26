import { useI18n } from '../../i18n';
import type { ShopLinkReading } from './useShopLinkReading';

/**
 * What Add says of a shop's tracking-page link that named no parcel: the shop hasn't shipped yet, and Peek can wait
 * for it (signed in), or the page can't be read, or doesn't answer.
 */
export function ShopLinkNotice({ reading, signedIn }: { reading: ShopLinkReading; signedIn: boolean }) {
  const { t } = useI18n();
  if (reading.state === 'none') return null;
  if (reading.state === 'reading') {
    return <div className="add-parcel-notice shop-link-notice"><p role="status">{t('shopLink.checking')}</p></div>;
  }
  if (reading.state === 'failed') {
    return <div className="add-parcel-notice shop-link-notice"><p role="status">{t('shopLink.unavailable')}</p></div>;
  }
  const { link } = reading;
  if (!link.waitable) {
    return <div className="add-parcel-notice shop-link-notice"><p role="status">{t('shopLink.unreadable')}</p></div>;
  }
  const shop = link.shop_name;
  return <div className="add-parcel-notice shop-link-notice">
    <p role="status">
      <strong>{shop ? t('shopLink.noParcelYet', { shop }) : t('shopLink.noParcelYet.unnamed')}</strong><br />
      {signedIn ? t('shopLink.waitHint') : t('shopLink.signIn')}
    </p>
  </div>;
}
