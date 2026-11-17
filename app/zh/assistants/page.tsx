import type { Metadata } from 'next';
import { assistantsMetadata, AssistantsRoute } from '../../../src/server/assistantsPage';

export async function generateMetadata(): Promise<Metadata> {
  return assistantsMetadata('zh');
}

/** How to connect an assistant to Peek, in Chinese, for anyone, whatever language the browser prefers. English is at `/assistants`. */
export default function ChineseAssistantsPage() {
  return <AssistantsRoute locale="zh" />;
}
