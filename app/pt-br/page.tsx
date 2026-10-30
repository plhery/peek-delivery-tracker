import type { Metadata } from 'next';
import { landingMetadata } from '../../src/server/landingMetadata';
import { LandingPage } from '../../src/server/LandingPage';

export async function generateMetadata(): Promise<Metadata> {
  return landingMetadata('pt-br');
}

/** The landing in Brazilian Portuguese, for anyone, whatever language the browser prefers. English is at `/`. */
export default function BrazilianPortugueseLandingPage() {
  return <LandingPage language="pt-br" landingRoute />;
}
