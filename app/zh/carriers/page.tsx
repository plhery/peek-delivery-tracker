import type { Metadata } from 'next';
import { carrierIndexMetadata, CarrierIndexRoute } from '../../../src/server/carrierPages';

export async function generateMetadata(): Promise<Metadata> {
  return carrierIndexMetadata('zh');
}

/** The carriers with a page in Chinese, for anyone, whatever language the browser prefers. English is at `/carriers`. */
export default function ChineseCarriersPage() {
  return <CarrierIndexRoute locale="zh" />;
}
