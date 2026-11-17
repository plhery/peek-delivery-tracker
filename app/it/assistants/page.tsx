import type { Metadata } from 'next';
import { assistantsMetadata, AssistantsRoute } from '../../../src/server/assistantsPage';

export async function generateMetadata(): Promise<Metadata> {
  return assistantsMetadata('it');
}

/** How to connect an assistant to Peek, in Italian, for anyone, whatever language the browser prefers. English is at `/assistants`. */
export default function ItalianAssistantsPage() {
  return <AssistantsRoute locale="it" />;
}
