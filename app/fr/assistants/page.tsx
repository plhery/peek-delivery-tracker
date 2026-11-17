import type { Metadata } from 'next';
import { assistantsMetadata, AssistantsRoute } from '../../../src/server/assistantsPage';

export async function generateMetadata(): Promise<Metadata> {
  return assistantsMetadata('fr');
}

/** How to connect an assistant to Peek, in French, for anyone, whatever language the browser prefers. English is at `/assistants`. */
export default function FrenchAssistantsPage() {
  return <AssistantsRoute locale="fr" />;
}
