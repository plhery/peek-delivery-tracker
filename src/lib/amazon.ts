/**
 * Amazon number recognition and portal links live in the scraper; the
 * user-facing messages below belong to the application.
 */
export {
  AMAZON_NUMBER_PATTERN,
  isAmazonTrackingNumber,
} from 'universal-parcel-scraper';
export {
  amazonMarketplace,
  amazonOrdersUrl,
  amazonShippingOrigin,
  amazonShippingUrl,
  requiresAmazonAccount,
} from 'universal-parcel-scraper';

export const AMAZON_HISTORY_EXPIRED = 'amazon_shipping_history_expired';

/**
 * Why a parcel Amazon tracks itself is not added. The server sends it with the address of the guide that shows where an
 * Amazon order's carrier and tracking number are, where it serves one (`amazonAccountMessage`).
 */
export const AMAZON_ACCOUNT_MESSAGE = 'Amazon Logistics deliveries usually need your Amazon account. Public Amazon Shipping tracking must be confirmed before adding this parcel. '
  + 'An Amazon parcel another carrier delivers is tracked with that carrier\'s number, which Amazon shows under Your Orders, then Track package.';
export const AMAZON_CHECK_UNAVAILABLE_MESSAGE = 'Amazon Shipping could not be checked right now. Please try again shortly or track this delivery in your Amazon account.';
