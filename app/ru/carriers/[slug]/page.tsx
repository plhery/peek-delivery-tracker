import type { Metadata } from 'next';
import { carrierMetadata, CarrierRoute } from '../../../../src/server/carrierPages';

export async function generateMetadata({ params }: { params: Promise<{ slug: string }> }): Promise<Metadata> {
  return carrierMetadata('ru', (await params).slug);
}

/**
 * A carrier's page in Russian, for anyone, whatever language the browser prefers. Any other slug, and a
 * carrier not written in Russian, is the site's 404.
 */
export default async function RussianCarrierPage({ params }: { params: Promise<{ slug: string }> }) {
  return <CarrierRoute locale="ru" slug={(await params).slug} />;
}
