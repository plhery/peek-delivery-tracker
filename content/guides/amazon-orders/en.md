---
title: Amazon order tracking: find your tracking number
description: How to track your Amazon shipment: where to find Delivery By and your Tracking ID in Your Orders, Shipped with Amazon (TBA/UK numbers), and tracking in Peek.
slug: amazon-order-tracking
picture: A phone showing an order's progress, two vans passing a parcel between them, and Pip watching.
published: 2026-10-16
updated: 2026-11-27
---

To track your Amazon shipment, open **Returns & Orders** (in the app, **Your Orders**) and select **Track package**: Amazon shows the carrier after "Delivery By" and the Tracking ID beneath it, distinct from your 17-digit order number. Copy that Tracking ID into [Peek](/) to follow your Amazon parcel alongside all your other deliveries across every Amazon site; "Shipped with Amazon" orders (`TBA` and 12 digits in the US, or a country code and 10 digits) sometimes track only inside your Amazon account.

## Where's the tracking number on Amazon?

:::steps
- Open Your Orders | On the website, select Returns & Orders at the top right. In the Amazon app, tap the person icon, then Your Orders.
- Select Track package | It's next to each parcel on its way or just delivered. An order in several parcels has a button for each.
- Copy the Tracking ID | The carrier's name comes after "Delivery By", and the number after "Tracking ID".
- Paste it into Peek | Peek recognises the carrier and follows the parcel.
:::

:::picture amazon-orders/orders-en.webp 480x300
Your Orders on Amazon: an order with the Track package button highlighted beside it. Sample order, not a real one.
:::

:::picture amazon-orders/track-en.webp 480x300
Amazon's Track package page: "Delivery By Royal Mail", then "Tracking ID" and the number, both highlighted. Sample values.
:::

On other Amazon sites, the same buttons are called:

| Site | Your Orders | Track package |
| --- | --- | --- |
| amazon.fr | Vos commandes | Suivre le colis |
| amazon.de | Meine Bestellungen | Sendung verfolgen |
| amazon.it | I miei ordini | Traccia il mio pacco |
| amazon.es | Mis pedidos | Localiza tu paquete |
| amazon.pl | Moje zamówienia | Śledź przesyłkę |

### No tracking number?

- **A new order.** The number usually appears once the carrier has the parcel.
- **Amazon delivers it itself.** The order may say "Shipped with Amazon" and show no number, or Amazon's own: two letters for the country and 10 digits, such as `UK1234567890`, or in the US `TBA` and 12 digits. Paste it into Peek anyway: Peek follows it if Amazon tracks it publicly, and tells you if only your Amazon account does.
- **The order number,** three groups of digits such as `123-1234567-1234567`, is never a tracking number.

## Can ChatGPT or Claude do it for me?

Yes, on a computer: the assistant has to control the web browser where you're signed in to Amazon. Claude does it with Claude in Chrome, on a paid Claude plan; ChatGPT does it from a Work chat in its desktop app, with its browser extension. On a phone, or in a chat that can only read your open tabs or browses from its own servers, the assistant can't reach your orders, and Amazon's emails don't give the tracking number.

### With Peek in the assistant

Connect Peek once. In Claude, add "Peek Parcel Tracker" from [Claude's connector directory](https://claude.ai/directory/peek-parcel-tracker). In ChatGPT, add Peek as a plugin, on a Plus, Pro, Business, Enterprise or Edu plan. [Here are all the steps](guide:ai-assistants).

Then ask:

> Import my Amazon parcels into Peek.

Peek tells the assistant where Amazon shows the numbers. The assistant adds each parcel, named after its item, and tells you which ones Amazon tracks only in your account.

### Without the Peek connector

Copy this into the assistant:

> Import my Amazon parcels into Peek in this browser, as https://peektracker.com/guides/amazon-order-tracking shows: copy each parcel's Tracking ID from Your Orders, then Track package, and add it at peektracker.com. On Amazon, only read: don't buy, cancel, return or change anything, and don't sign in for me.

Sign in to Peek in that browser first: without an account, Peek looks up only a few parcels a day.

Either way, the assistant only reads Amazon. If Amazon asks you to sign in, do it yourself in that window, then ask again.

Parcel late, or marked delivered but not there? See [delivered but not received](guide:delivered-not-received) and [lost parcels](guide:lost-parcel).

:::sources
- [Amazon Help: Tracking your package](https://www.amazon.com/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – Your Orders, Track package, the tracking numbers; the same page on amazon.co.uk, .fr, .de, .it, .es and .pl gives their labels
- [Amazon Help: Contact a Carrier](https://www.amazon.com/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – the carrier's name and tracking number under Track package, one carrier per package
- [Amazon Help: Missing Tracking Information](https://www.amazon.com/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – no number while the parcel is on its way to the carrier
- [Amazon Help: About Deliveries Shipped with Amazon](https://www.amazon.com/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – orders Amazon delivers itself show as shipped with Amazon
- [Amazon Help: Return a Gift](https://www.amazon.com/gp/help/customer/display.html?nodeId=GMTDJ6XPUMN7NWNB) – the 17-digit order number
- [About Amazon: How to track your packages](https://www.aboutamazon.com/news/retail/track-amazon-package) – Returns & Orders, the person icon in the app, Track package
- [Claude Help Center: Get started with Claude in Chrome](https://support.claude.com/en/articles/12012173-get-started-with-claude-in-chrome) – paid Claude plans, the side panel, Claude in Chrome in a chat's connectors
- [ChatGPT: Browser extension](https://learn.chatgpt.com/docs/chrome-extension) – set up from the ChatGPT desktop app; reads and acts on sites where you're signed in, from a Work chat there, and uses plugins too
- [ChatGPT: Browser](https://learn.chatgpt.com/codex/browser) – ChatGPT Work on the web and on the phone browses in the cloud, without your logins
- [Peek: Connect Peek to Claude, ChatGPT or Gemini](https://peektracker.com/assistants) – Peek's steps for Claude and ChatGPT
- [Peek on GitHub: the assistants' tools](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – what Peek tells an assistant about Amazon: Your Orders, Track package, "Delivery By", "Tracking ID", only reading
- [Peek on GitHub: English wording](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/en.json) – a few lookups a day without an account
- [Universal Parcel Scraper: carrier data](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – the shapes of Amazon's own numbers
:::
