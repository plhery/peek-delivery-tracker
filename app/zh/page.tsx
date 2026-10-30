import type { Metadata } from 'next';
import { landingMetadata } from '../../src/server/landingMetadata';
import { LandingPage } from '../../src/server/LandingPage';

export async function generateMetadata(): Promise<Metadata> {
  return landingMetadata('zh');
}

/** The landing in Chinese, for anyone, whatever language the browser prefers. English is at `/`. */
export default function ChineseLandingPage() {
  return <LandingPage language="zh" landingRoute />;
}
