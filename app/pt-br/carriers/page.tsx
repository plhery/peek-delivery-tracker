import type { Metadata } from 'next';
import { carrierIndexMetadata, CarrierIndexRoute } from '../../../src/server/carrierPages';

export async function generateMetadata(): Promise<Metadata> {
  return carrierIndexMetadata('pt-br');
}

/** The carriers with a page in Brazilian Portuguese, for anyone, whatever language the browser prefers. English is at `/carriers`. */
export default function BrazilianPortugueseCarriersPage() {
  return <CarrierIndexRoute locale="pt-br" />;
}
