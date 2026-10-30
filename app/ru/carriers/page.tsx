import type { Metadata } from 'next';
import { carrierIndexMetadata, CarrierIndexRoute } from '../../../src/server/carrierPages';

export async function generateMetadata(): Promise<Metadata> {
  return carrierIndexMetadata('ru');
}

/** The carriers with a page in Russian, for anyone, whatever language the browser prefers. English is at `/carriers`. */
export default function RussianCarriersPage() {
  return <CarrierIndexRoute locale="ru" />;
}
