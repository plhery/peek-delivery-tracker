import type { Metadata } from 'next';
import { assistantsMetadata, AssistantsRoute } from '../../../src/server/assistantsPage';

export async function generateMetadata(): Promise<Metadata> {
  return assistantsMetadata('ru');
}

/** How to connect an assistant to Peek, in Russian, for anyone, whatever language the browser prefers. English is at `/assistants`. */
export default function RussianAssistantsPage() {
  return <AssistantsRoute locale="ru" />;
}
