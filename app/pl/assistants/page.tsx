import type { Metadata } from 'next';
import { assistantsMetadata, AssistantsRoute } from '../../../src/server/assistantsPage';

export async function generateMetadata(): Promise<Metadata> {
  return assistantsMetadata('pl');
}

/** How to connect an assistant to Peek, in Polish, for anyone, whatever language the browser prefers. English is at `/assistants`. */
export default function PolishAssistantsPage() {
  return <AssistantsRoute locale="pl" />;
}
