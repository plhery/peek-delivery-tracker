import type { Metadata } from 'next';
import { assistantsMetadata, AssistantsRoute } from '../../../src/server/assistantsPage';

export async function generateMetadata(): Promise<Metadata> {
  return assistantsMetadata('de');
}

/** How to connect an assistant to Peek, in German, for anyone, whatever language the browser prefers. English is at `/assistants`. */
export default function GermanAssistantsPage() {
  return <AssistantsRoute locale="de" />;
}
