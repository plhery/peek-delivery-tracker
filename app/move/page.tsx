import type { Metadata } from 'next';
import { landingMetadata } from '../../src/server/landingMetadata';
import { LandingPage } from '../../src/server/LandingPage';

export async function generateMetadata(): Promise<Metadata> {
  return { ...await landingMetadata(), robots: { index: false, follow: false } };
}

/**
 * Another device's parcels arrive here. The page is the one at `/`: it takes the parcels from the address's `#`,
 * which the server never sees, and moves to `/`.
 */
export default function MovePage() {
  return <LandingPage />;
}
