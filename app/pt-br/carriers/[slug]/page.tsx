import type { Metadata } from 'next';
import { carrierMetadata, CarrierRoute } from '../../../../src/server/carrierPages';

export async function generateMetadata({ params }: { params: Promise<{ slug: string }> }): Promise<Metadata> {
  return carrierMetadata('pt-br', (await params).slug);
}

/**
 * A carrier's page in Brazilian Portuguese, for anyone, whatever language the browser prefers. Any other slug, and a
 * carrier not written in Brazilian Portuguese, is the site's 404.
 */
export default async function BrazilianPortugueseCarrierPage({ params }: { params: Promise<{ slug: string }> }) {
  return <CarrierRoute locale="pt-br" slug={(await params).slug} />;
}
