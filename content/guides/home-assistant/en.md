---
title: Track your parcels in Home Assistant
description: Peek's Home Assistant integration brings your parcels in as sensors, a deliveries calendar and a dashboard card. What you need, and how to install and set it up.
slug: track-parcels-home-assistant
picture: A tablet on a kitchen wall showing a home dashboard, with a list of parcels, a small delivery calendar, and Pip, the parcel with a face, smiling from one of the cards.
published: 2026-11-19
updated: 2026-11-19
---

To track your parcels in Home Assistant, install Peek for Home Assistant: it brings your [Peek](/) parcel list in as sensors, a deliveries calendar, a dashboard card and a tool for Assist. You need a free Peek account and Home Assistant 2026.11 or later, where you search for Peek in the Marketplace; on 2026.10, you search for it in HACS.

## What does Peek show in Home Assistant?

Your Peek parcels, in the forms Home Assistant uses:

| What | What it does |
| --- | --- |
| Sensors | Count the parcels on their way, those arriving today, those that need you (waiting at a pickup point, a missed delivery, customs) and today's deliveries, and give the next delivery day |
| A "Deliveries" calendar | All-day events: each parcel on its way on its expected day, each delivery on the day it came |
| A "Parcel updates" event | Fires when a parcel is added, changes status, goes out for delivery, needs you, is delivered, or its expected day changes |
| Three actions | Add parcels from a text, archive a parcel, or read the list |
| The "Peek parcels" card | Your parcels on a dashboard, as a list or as cards with Pip |
| A tool for Assist | Answers questions such as "what arrives today?" |

Home Assistant reads Peek every 30 minutes, every 10 minutes on a day a parcel is out for delivery or expected, and right after it adds or archives a parcel. To read now, call `homeassistant.update_entity` on any Peek entity. "Today" is the day in Home Assistant's time zone.

## What do I need?

- **Home Assistant 2026.11 or later**, as Peek's page says, and an administrator's login: Home Assistant's Marketplace is for administrators only. On 2026.10, install it from HACS.
- **A Peek account.** Setting up opens Peek to sign in, with a six-digit code Peek emails you, or with Google or Apple where the screen offers it. Your first sign-in creates the account; a guest can't connect Home Assistant.
- **Nothing else.** Peek is free, and there's no client id, secret or Application Credentials to type. One Peek account can be connected to each Home Assistant.

The integration connects the way assistants do, and Peek's connections for assistants are in beta. If Peek says "Peek can’t connect assistants to this account yet.", the integration can't be set up yet.

## How do I install the Peek integration?

Search for Peek in Home Assistant's Marketplace. On 2026.10, search for it in HACS, or install it by hand.

### From the Marketplace (Home Assistant 2026.11 and later)

:::steps
- Open "Settings › Marketplace" | Search for "Peek" and open it.
- Install Peek | Select "Install", then "Set up".
- Sign in to Peek | Your browser opens Peek. Check what Home Assistant asks for (to see your parcels, and to add and archive them), then select "Allow".
- Go back to Home Assistant | Peek sends you back through My Home Assistant (my.home-assistant.io). The first time, it asks for your Home Assistant's address.
:::

### With HACS (Home Assistant 2026.10)

:::steps
- Search for Peek | In HACS, search for "Peek" and open it.
- Download Peek | Then restart Home Assistant.
- Add the integration | Go to "Settings › Devices & services › Add integration", choose Peek, sign in to Peek and select "Allow".
:::

If Peek isn't listed, add `https://github.com/plhery/peek-ha` by its link: with "Add from link" in the Marketplace, which asks you to connect GitHub the first time, or as a custom repository of the "Integration" type in HACS.

You can also install it by hand: copy the `custom_components/peek` folder into your configuration's `custom_components` folder, restart, and add the integration as above.

## How do I add the Peek parcels card to my dashboard?

The integration loads its card on every dashboard itself: there's no resource to add. A page already open while you set Peek up doesn't have the card yet: reload it first, or close the Home Assistant app and open it again.

:::steps
- Edit a dashboard | Open it and select the pencil. On a dashboard Home Assistant made for you, it first asks you to take control.
- Select "Add card" | Then the "By card" tab. The "By entity" tab searches entities, not cards.
- Pick "Peek parcels" | Search for "Peek", pick "Peek parcels" and save.
:::

The card comes in two styles: a list, the default, with a row for each parcel showing its carrier's truck, its stage and its day; or a card for each parcel in its carrier's colours, like Peek's iPhone and Android widgets, with Pip, the town of the latest scan, the parcel's name, its stage and its day. Both follow Peek's sections, starting with the parcels that need you, and end with "+N more" when there are more parcels than rows.

The card follows your theme, light or dark, and your language, if it's English or French. In YAML, it's `type: custom:peek-parcels-card`; the integration's README lists its options.

## Can I use it in automations or ask Assist?

Yes. The README has four ready-made automations:

- a notification on your phone when a parcel goes out for delivery;
- adding the tracking numbers from your shipping emails, read with Home Assistant's IMAP integration;
- a morning message listing what arrives today;
- archiving what was delivered, every evening.

When you add parcels from a text, Peek adds each tracking number it finds, at most 10 at a time, and keeps only the numbers and the name you give, so send just the shipping part of an email when you can.

Once one of Peek's entities is exposed to Assist ("Settings › Voice assistants › Expose"), you can ask "what arrives today?" or where your parcel from a shop is. Assist's Peek tool only reads: it never adds or archives parcels. What it returns (names, shops, carriers, stages, expected days and the latest scan's place) goes to the conversation agent you chose, which may be a cloud service.

## What can't it do?

- **Delete or edit parcels.** Home Assistant asks only to see your parcels, and to add and archive them. You can restore an archived parcel in Peek.
- **Connect Claude, ChatGPT or Gemini.** They connect to Peek on their own: see the guide [Track your parcels in ChatGPT, Claude or Gemini](guide:ai-assistants).

## What does Home Assistant keep, and how do I remove it?

Home Assistant talks to Peek only, at peektracker.com, and keeps the sign-in's tokens in its configuration like any integration. Its history records the sensors' counts and the "Parcel updates" events, with the parcel's name, carrier, stage and latest place, but not the sensors' parcel lists. The card runs in your browser from Home Assistant's own data and loads nothing from anywhere else.

To remove it, delete Peek in "Settings › Devices & services": Home Assistant gives its access back to Peek, which forgets the connection, and your parcels stay in Peek. If Peek can't be reached then, or to cut Home Assistant off from Peek's side, select "Disconnect" next to Home Assistant on Peek's website (not its apps), under "Settings" → "Deliveries" → "Assistants". An integration you keep after that says "Authentication expired for Peek"; reconfiguring it starts the same sign-in.

:::sources
- [Peek for Home Assistant: README](https://github.com/plhery/peek-ha) – what it adds, versions, install, sign-in, the card, automations, Assist, privacy, removal
- [Peek for Home Assistant: hacs.json](https://github.com/plhery/peek-ha/blob/main/hacs.json) – the oldest Home Assistant it installs on, 2026.10
- [Home Assistant: Marketplace](https://www.home-assistant.io/integrations/marketplace/) – Settings › Marketplace, search, Add from link, connecting GitHub, Install, administrators only, replaces HACS from 2026.11
- [HACS: default integrations list](https://github.com/hacs/default/blob/master/integration) – Peek's entry, plhery/peek-ha, in the catalogue HACS and the Marketplace search
- [Home Assistant on GitHub: the Marketplace's catalogue](https://github.com/home-assistant/core/blob/dev/homeassistant/components/marketplace/const.py) – the Marketplace uses HACS's default list, hacs/default
- [Peek: Connect Peek to Claude, ChatGPT or Gemini](https://peektracker.com/assistants) – Home Assistant's steps on Peek's page, 2026.11 or later
- [Peek on GitHub: each assistant's setup](https://github.com/plhery/peek-delivery-tracker/blob/main/src/assistants/setups.tsx) – Home Assistant's menu names
- [Peek on GitHub: English wording](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/en.json) – version, steps, account, beta
- [Peek on GitHub: Settings, Assistants](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Assistants.tsx) – connected assistants, Disconnect
- [Peek: Help](https://peektracker.com/support.html) – signing in with a six-digit code
- [Peek on GitHub: Terms](https://github.com/plhery/peek-delivery-tracker/blob/main/TERMS.md) – free, no fees or subscription
:::
