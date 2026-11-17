import type { Metadata } from 'next';
import { assistantsMetadata, AssistantsRoute } from '../../../src/server/assistantsPage';

export async function generateMetadata(): Promise<Metadata> {
  return assistantsMetadata('pt-br');
}

/** How to connect an assistant to Peek, in Brazilian Portuguese, for anyone, whatever language the browser prefers. English is at `/assistants`. */
export default function BrazilianPortugueseAssistantsPage() {
  return <AssistantsRoute locale="pt-br" />;
}
