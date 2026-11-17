import type { Metadata } from 'next';
import { assistantsMetadata, AssistantsRoute } from '../../src/server/assistantsPage';

export async function generateMetadata(): Promise<Metadata> {
  return assistantsMetadata('en');
}

/**
 * How to connect an assistant to Peek, in English, for anyone, whatever language the browser prefers. The other
 * languages are at `/<language>/assistants`.
 */
export default function EnglishAssistantsPage() {
  return <AssistantsRoute locale="en" />;
}
