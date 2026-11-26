import type { ApiShopWait } from '../../generated/apiContract';
import { useI18n } from '../../i18n';
import { localizedCalendarDate } from '../../lib/format';
import { Icon } from '../Icon';

/**
 * The parcels the reader waits for a shop to ship: a card each, under the parcels on their way, named like the parcel
 * it becomes, with the shop and its platform where a parcel shows its carrier ("H&M via parcelLab"). While Peek asks,
 * it says until when, and offers to stop; once 14 days have passed without a parcel, it says so plainly and offers
 * to remove it.
 */
export function ShopWaitCards({ waits, onRemove }: { waits: readonly ApiShopWait[]; onRemove: (id: string) => void }) {
  if (!waits.length) return null;
  return <div className="parcel-grid shop-waits">
    {waits.map((wait) => <ShopWaitCard key={wait.id} wait={wait} onRemove={onRemove} />)}
  </div>;
}

function ShopWaitCard({ wait, onRemove }: { wait: ApiShopWait; onRemove: (id: string) => void }) {
  const { t, languageTag } = useI18n();
  const shop = wait.shop_name;
  const platform = wait.platform_name;
  const expired = wait.expired_at !== null;
  const until = localizedCalendarDate(new Date(wait.expires_at), languageTag);
  const name = wait.label || shop || platform;
  return <article className={`parcel-card shop-wait${expired ? ' shop-wait--expired' : ''}`} data-shop-wait={wait.id} aria-label={name}>
    <span className="parcel-card__top">
      <span className="shop-wait__mark"><Icon name="storefront" />{shop ? t('shopWait.via', { shop, platform }) : t('shopWait.via.unnamed', { platform })}</span>
    </span>
    <strong className="parcel-card__label">{name}</strong>
    <span className="shop-wait__state">
      <Icon name={expired ? 'clock' : 'hourglass'} />
      {expired
        ? (shop ? t('shopWait.expired', { shop }) : t('shopWait.expired.unnamed'))
        : (shop ? t('shopWait.status', { shop }) : t('shopWait.status.unnamed'))}
    </span>
    <span className="parcel-card__sender">{expired ? t('shopWait.expiredHint') : t('shopWait.until', { date: until })}</span>
    <span className="shop-wait__actions">
      <button type="button" className="shop-wait__action" onClick={() => onRemove(wait.id)}>
        {expired ? t('shopWait.remove') : t('shopWait.stop')}
      </button>
    </span>
  </article>;
}
