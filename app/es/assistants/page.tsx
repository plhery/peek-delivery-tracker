import type { Metadata } from 'next';
import { assistantsMetadata, AssistantsRoute } from '../../../src/server/assistantsPage';

export async function generateMetadata(): Promise<Metadata> {
  return assistantsMetadata('es');
}

/** How to connect an assistant to Peek, in Spanish, for anyone, whatever language the browser prefers. English is at `/assistants`. */
export default function SpanishAssistantsPage() {
  return <AssistantsRoute locale="es" />;
}
