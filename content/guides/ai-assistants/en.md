---
title: Track your parcels in ChatGPT, Claude or Gemini
description: Connect Peek to ChatGPT, Claude or Gemini and ask where your parcels are, or have it add the tracking numbers from your emails. Who can, and how to set it up.
slug: track-parcels-chatgpt-claude-gemini
picture: Pip, the parcel with a face, leaning out of a chat window on a laptop, where a question about this week's parcels gets a short list in reply.
published: 2026-11-19
updated: 2026-11-19
---

You can track your parcels in Claude, ChatGPT or Gemini by connecting them to [Peek](/), a free parcel tracker: the assistant can then tell you where your parcels are and add the tracking numbers you give it. You need a Peek account, and each assistant decides who can: Claude on any plan, ChatGPT on a Business, Enterprise or Edu plan, and Gemini for adults in the US with a personal Google Account. Peek labels the feature "Beta", so it may still change.

## Can ChatGPT, Claude or Gemini track my parcels?

Yes, once you connect them to Peek. Each assistant sets its own conditions:

| Assistant | Where it works | Who can add Peek |
| --- | --- | --- |
| Claude | claude.ai, and Claude's desktop, iPhone and Android apps | Any plan. On a Team or Enterprise plan, an owner adds Peek first |
| ChatGPT | chatgpt.com | A Business, Enterprise or Edu plan. In a workspace, an admin may have to add or allow Peek first |
| Gemini | Connected at gemini.google.com, then in the Gemini app too | 18 or over, in the US, with a personal Google Account and History on, in English |
| Another assistant | Any that lets you add a custom connector or MCP server | Whoever can add one there |

On Peek's side, any account can connect one (a guest can't), and Peek is free.

Home Assistant connects through Peek's own integration instead: see the guide [Track your parcels in Home Assistant](guide:home-assistant).

## How do I add Peek to Claude, ChatGPT or Gemini?

You add Peek in the assistant, with Peek's address for assistants: `https://peektracker.com/mcp`. The steps below are [Peek's own](https://peektracker.com/assistants); signed in on Peek's website (not its apps), they're also under "Settings" → "Deliveries" → "Assistants", with a "Copy address" button.

### Claude

:::steps
- Open "Peek Parcel Tracker" | In [Claude's connector directory](https://claude.ai/directory/peek-parcel-tracker), or in Claude: open "Customize › Connectors", select "+" and search for Peek.
- Select "Connect" | Claude then asks you to sign in to Peek.
- Sign in to Peek | Choose what Claude may do, then select "Allow".
- Turn Peek on in a chat | Select "+" at the bottom left, then "Connectors", and turn Peek on.
:::

On a Team or Enterprise plan, an owner adds Peek first: "Organization settings › Connectors", "Browse connectors", then "Add to your team".

If Claude doesn't list "Peek Parcel Tracker", add Peek as a custom connector on claude.ai or in the desktop app (the Free plan allows one):

:::steps
- Open "Customize › Connectors" | Select "+ Add", then "Add custom connector".
- Name it Peek | Paste `https://peektracker.com/mcp` and select "Continue".
- Keep the sign-in settings Claude finds | Select "Continue", then "Add".
:::

### ChatGPT

:::steps
- Go to "chatgpt.com/plugins" | Select "+", then "Add custom MCP server".
- Name it Peek | Paste `https://peektracker.com/mcp` and choose "OAuth" for authentication. Select "I understand and want to continue", then "Create as a plugin".
- Sign in to Peek | Choose what ChatGPT may do, then select "Allow".
- Install it | Open Peek in your plugins and select "+". Then type @ in a new chat and pick Peek.
:::

### Gemini

Google's help page says that to connect a custom app such as Peek, you must "Be 18 or over and in the US", use a personal Google Account, not a work or school one, and "Have History on". You connect it at gemini.google.com, and it then works in the Gemini mobile app too, in English only. Google names no paid plan. Google's page and Peek's both say to add Peek's address in Gemini's "Settings", then "Connected Apps", under "Custom apps".

## What can I ask it?

Ask in your own words, for example:

- “Look through my emails and add the tracking numbers you find.” This works when the assistant can read your mail itself, for example through its own Gmail connector: Peek never reads your email.
- “What’s arriving this week?”
- “Where’s my parcel from IKEA?”
- “Archive everything that’s been delivered.”
- “What was my latest delivered parcel?”

It can also pick out the parcels that need you, such as one waiting at a pickup point, and those expected today, in your time zone.

To add parcels, give it a tracking number or the shipping part of an email: Peek follows each tracking number it finds, up to 10 at a time, and keeps only the numbers and the name given, never the text. An order number isn't a tracking number: [here's how to tell them apart](guide:find-tracking-number).

### Amazon orders

Amazon's emails usually hold no tracking number. An assistant that can browse the Amazon site you're signed in to is told to open "Your Orders", then "Track package", and send the carrier and "Tracking ID", never the order number. It may only read there: never buy, cancel, return or change anything, or sign in for you. Otherwise, find the number yourself: [our Amazon guide](guide:amazon-orders) shows where. A parcel Amazon delivers itself, with no tracking ID, is tracked only in your Amazon account.

## Can the assistant delete or change my parcels?

It can never delete a parcel: you do that in Peek. The rest is up to you: when you connect it, Peek's screen ("Connect Claude to Peek?", for example) shows your email address and a tick box for each permission the assistant asks for:

| Permission on Peek's screen | What the assistant can then do |
| --- | --- |
| "See your parcels: where each one is, when it’s expected and its history" | List your parcels, and open one with its scans |
| "Add parcels from the tracking numbers you give it, and archive them" | Add parcels, and archive one |
| "Change your parcels’ name, carrier or notifications, and restore archived ones" | Rename a parcel, change its carrier, turn its notifications or Peek's emails off or on, and restore an archived one |

Untick what you don't want; "Allow" stays greyed out until one box is ticked. If you later ask for something you didn't allow, the assistant is told to connect Peek again with that permission.

## What does the assistant see, and how do I disconnect it?

It receives what you allowed: your parcels' names, senders, carriers, tracking numbers, statuses, expected days, pickup points and scans. Never the recipient's name, postcode or phone digits: a scan that names the recipient, who signed, a code or a door reaches it only as its stage, such as "Delivered". Its provider's own privacy terms then apply. Peek's [privacy notice](https://peektracker.com/privacy.html) says what Peek keeps about each assistant you connect.

To disconnect an assistant, on Peek's website:

:::steps
- Open "Settings" | From the round button at the top with the first letter of your email address.
- Select "Assistants" | Under "Deliveries". Each assistant shows what it may do and when it was last used.
- Select "Disconnect" | Next to the assistant.
:::

You can also remove Peek in the assistant's own settings. A connection unused for 60 days ends by itself, and deleting your Peek account deletes its connected assistants.

## Why can't I add Peek to my assistant?

Usually it's the plan, the country or the account:

- **Your plan or country.** Check the table above, and on a team or workspace plan ask its owner or admin.
- **No Peek account.** Sign in when the assistant asks, with a six-digit code Peek emails you, or with Google or Apple where the screen offers it. Your first sign-in creates the account.
- **Peek asks you to sign in again.** Allowing an assistant needs a recent sign-in, even when you're signed in: sign in, then select "Allow".
- **"This link can’t connect an assistant".** Start connecting again from your assistant.

More cases are under "If something goes wrong" on [Peek's assistants page](https://peektracker.com/assistants), and Peek's [help page](https://peektracker.com/support.html) says how to reach Peek.

:::sources
- [Peek: Connect Peek to Claude, ChatGPT or Gemini](https://peektracker.com/assistants) – who can, each assistant's steps, what to ask, what Peek shares, troubleshooting
- [Peek on GitHub: each assistant's setup](https://github.com/plhery/peek-delivery-tracker/blob/main/src/assistants/setups.tsx) – steps and menu names, signing in before the last step
- [Peek on GitHub: English wording](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/en.json) – plans and places, permissions, Beta, troubleshooting
- [Peek on GitHub: the assistants' tools](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – what an assistant can list, add and edit, no delete, Amazon
- [Peek on GitHub: the consent screen](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/AssistantConsentScreen.tsx) – tick boxes, Allow, recent sign-in
- [Peek on GitHub: Settings, Assistants](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Assistants.tsx) – address, connected assistants, Disconnect
- [Peek: Privacy notice](https://peektracker.com/privacy.html) – what an assistant receives, what Peek keeps, 60 days, account deletion
- [Peek on GitHub: Terms](https://github.com/plhery/peek-delivery-tracker/blob/main/TERMS.md) – free, no fees or subscription
- [Peek: Help](https://peektracker.com/support.html) – signing in with a six-digit code
- [Claude Help Center: Get started with custom connectors using remote MCP](https://support.claude.com/en/articles/11175166-get-started-with-custom-connectors-using-remote-mcp) – custom connector steps, one on the Free plan
- [Gemini Apps Help: Connect & manage custom apps for Gemini Apps](https://support.google.com/gemini/answer/17209137?hl=en&co=GENIE.Platform%3DDesktop) – who can connect a custom app, History on, English, connecting on the web
:::
