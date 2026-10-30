import type { Metadata } from 'next';
import { carrierMetadata, CarrierRoute } from '../../../../src/server/carrierPages';

export async function generateMetadata({ params }: { params: Promise<{ slug: string }> }): Promise<Metadata> {
  return carrierMetadata('zh', (await params).slug);
}

/**
 * A carrier's page in Chinese, for anyone, whatever language the browser prefers. Any other slug, and a
 * carrier not written in Chinese, is the site's 404.
 */
export default async function ChineseCarrierPage({ params }: { params: Promise<{ slug: string }> }) {
  return <CarrierRoute locale="zh" slug={(await params).slug} />;
}
