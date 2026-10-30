import type { Metadata } from 'next';
import { landingMetadata } from '../../src/server/landingMetadata';
import { LandingPage } from '../../src/server/LandingPage';

export async function generateMetadata(): Promise<Metadata> {
  return landingMetadata('ru');
}

/** The landing in Russian, for anyone, whatever language the browser prefers. English is at `/`. */
export default function RussianLandingPage() {
  return <LandingPage language="ru" landingRoute />;
}
