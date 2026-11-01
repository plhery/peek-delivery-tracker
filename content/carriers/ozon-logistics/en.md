---
title: Ozon tracking: order numbers and statuses in English
description: Track an Ozon order: what its numbers look like, what the Russian steps on Ozon's tracker mean in English, and how Peek follows it.
slug: ozon-tracking
published: 2026-11-07
updated: 2026-11-07
---

Ozon's delivery service carries the orders that sellers hand over to it, to your door or to a pickup point. Peek's catalog lists it in Russia, Belarus and Kazakhstan, also under the names Ozon Rocket and Ozon Доставка. Paste your number in the box above, with its dashes. Ozon's own tracker is tracking.ozon.ru, and Peek reads it for you, as explained below.

## Ozon tracking number: what it looks like

Peek knows two shapes for Ozon, and both are written with dashes. The dashes matter: without them, the digits look like other carriers' numbers. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `00000000-0000-0` | 8 digits, 4 digits and 1 digit, joined by dashes. Peek names Ozon from this shape at once. |
| `0000000000-0000-0` | The same with 10 digits at the start. Peek names Ozon too. |
| `0000000000000` | The first number without its dashes, or with spaces instead of dashes. DPD France, Ukrposhta and other carriers have 13-digit numbers, so Peek doesn't suggest Ozon. Type the dashes back in, or pick Ozon yourself (below). The same goes for 15 digits, a length FedEx and others use. |
| `PL-000000-00000` | PL, 6 digits, then more letters or digits, with dashes. Peek names Ozon. |
| `PL-000000-0000` | PL with exactly 10 digits is also the shape of Amazon's numbers. Peek takes it for an [Amazon](carrier:amazon-logistics) number and doesn't let you pick Ozon for it, so it can't follow it with Ozon. |

Lower case doesn't matter: `pl-` works like `PL-`. Can't find your number? See [how to find your tracking number](guide:find-tracking-number) and [tracking number formats](guide:tracking-number-formats).

## Ozon tracking statuses in English

Peek's code lists the steps Ozon's tracker shows, in Russian. Peek reads that tracker and shows these steps in the same Russian words, except `Заказ в работе`, `Заказ на пути к границе страны назначения` and `Заказ прибывает на приграничный пункт`, which it leaves out (see "Track an Ozon order with Peek").

**In your country.** Roughly in the order an order gets them:

| Ozon says | What it means for you |
| --- | --- |
| `Передается в доставку` | The seller has packed your order and is handing it over for delivery. Ozon may not have it yet. |
| `Заказ принят перевозчиком.` | The carrier has accepted the order: it is on its way. |
| `Заказ отправили на сортировочный терминал`, `Заказ отправлен на сортировочный терминал.` | Sent to a sorting centre. |
| `Заказ покинул сортировочный терминал.` | It has left the sorting centre. |
| `Заказ ожидает отправки в город получателя.` | It is waiting to be sent on to your city. |
| `Заказ везут в город получателя` | It is on its way to your city. |
| `Заказ везут` | It is on its way. |
| `Заказ передали в курьерскую доставку` | Handed to courier delivery. The courier then calls you to agree a time. |
| `Заказ в пункте выдачи` | It is at your pickup point. Go and collect it. |
| `Заказ получен в пункте выдачи.` | You collected it at the pickup point or post office: delivered. |
| `Заказ успешно доставлен получателю.` | Delivered. Ozon's tracker says this when the order didn't go to a pickup point or post office, such as when a courier brings it. |
| `Заказ отменён` | The order was cancelled. |
| `Заказ в работе` | The order is in progress. Ozon's tracker shows it as a placeholder, not as a step of the journey. |

**From another country.** An order sent from abroad also gets customs steps, roughly in this order:

| Ozon says | What it means for you |
| --- | --- |
| `Заказ везут на таможню в стране отправления` | It is on its way to customs in the country it was sent from. |
| `Заказ привезли на таможню для экспортного таможенного оформления` | It is at export customs in that country. |
| `Заказ покинул зону экспортного таможенного оформления` | It has left export customs. |
| `Заказ на пути к границе страны назначения` | It is on its way to the border of your country. |
| `Заказ прибывает на приграничный пункт` | It is arriving at the border crossing. |
| `Заказ привезли в страну назначения` | It has arrived in your country. |
| `Заказ передан на импортное таможенное оформление.` | Handed to import customs in your country. |
| `Заказ проходит импортное таможенное оформление` | It is going through import customs. |
| `Заказ выпущен импортной таможней.` | Released by import customs: on its way again. |
| `Импортная таможня задержала заказ` | Import customs is holding the order for a further check. |

## How Ozon delivers: courier or pickup point

The steps tell you which way your order comes.

**To your door.** After `Заказ передали в курьерскую доставку`, a courier brings the order and calls you first to agree a time. The last step is `Заказ успешно доставлен получателю.`

**To a pickup point.** Wait for `Заказ в пункте выдачи`, then go and collect it. Once you have it, Ozon's tracker shows `Заказ получен в пункте выдачи.` It uses the same words when you collect an order at a post office.

Peek couldn't read Ozon's website, so this page gives no delivery times, opening hours, holding periods or rules for delivery attempts. Ask Ozon about them. See also [uncollected parcels](guide:uncollected-parcel) and [missed deliveries](guide:missed-delivery).

**From abroad.** The customs steps above show where an order from another country is. This page gives no customs rules or fees: Peek couldn't check them on an official page. See [parcels held at customs](guide:customs).

## Ozon order not arriving or tracking stuck

**Nothing yet, or only `Передается в доставку`.** The seller may not have handed the order over yet, or Ozon's tracker doesn't have the number yet. Peek keeps asking. If you added the number without its dashes, pick Ozon for it with "Change carrier".

**Peek is behind Ozon's tracker.** When Ozon's tracker doesn't answer Peek, a tracking service fills in, and it can lag behind Ozon. Compare with tracking.ozon.ru before you worry.

**No new step for days.** If the newest step is four or more days old while Peek shows "Label created", "Received by carrier" or "In transit", Peek flags it: "No tracking update for four days". More in [why tracking stops updating](guide:tracking-not-updating).

**`Импортная таможня задержала заказ`.** Customs is checking the order. Wait for `Заказ выпущен импортной таможней.` If nothing changes for a long time, ask Ozon about it.

**`Заказ отменён`.** The order was cancelled, so it won't come. Ask Ozon, or the shop you bought from, about a refund.

**Delivered, but nothing came.** Check whether someone collected it for you, then contact Ozon. More in [delivered but not received](guide:delivered-not-received).

**Lost.** Contact Ozon, or the shop you bought from, about your order. See [lost parcels](guide:lost-parcel).

## Ozon customer service and contact

As of 1 November 2026, Peek couldn't read Ozon's website, so this page lists no phone number or email: Peek gives only contacts read on a carrier's own pages. Ozon's tracker is tracking.ozon.ru.

For an order you bought on Ozon, contact Ozon about it. For an order from another shop, ask that shop first: it booked the delivery.

## Track an Ozon order with Peek

[Peek](/) follows an Ozon number with the number alone: no account, no phone number and no code.

- **With its dashes.** Peek names Ozon at once ("Detected carrier") and files the parcel under Ozon.
- **Without its dashes.** Peek can't tell the carrier by the digits alone. Paste it all the same: Peek follows more than 3,500 carriers. While it asks the carriers whose numbers have this shape, and tracking services, it shows "Checking tracking services…". If the tracking services name Ozon, with dated steps, Peek files the parcel under Ozon by itself. Or press "Change" and search for Ozon. On a parcel you've already added, use "Change carrier".
- **A link.** Paste a tracking.ozon.ru link with the number in it (`track=`), or a rocket.ozon.ru tracking link (`SearchId=`), and Peek tracks that number with Ozon.

**Where the steps come from.** Peek reads Ozon's tracker and shows each step in Ozon's Russian words, as in the tables above, with its time, and Ozon's expected delivery dates. Ozon gives no place for a step, so there's no map. Until Ozon's tracker knows the number, Peek shows "Waiting for the carrier", or "No tracking history yet" for a number added without a carrier. When Ozon's tracker doesn't answer Peek, Peek asks tracking services instead, ParcelsApp, Ship24 and 17TRACK in turn, and shows the history of the first one that has the parcel, in that service's words, which can be behind tracking.ozon.ru. Peek's Ozon lookup doesn't take a `PL-` number: Peek shows "Check the tracking number" for it until a tracking service has the parcel.

**The link on the parcel's page.** "Open the Ozon website" opens the number on Ozon's tracker. If a tracking service filled in, the link opens that service instead, such as "Open the ParcelsApp website".

**Another carrier's parcel.** Peek keeps Ozon's number without its dashes. Until Ozon's tracker has shown the parcel, a check that gets nothing from it also asks, in the parcel's first 30 days and before the tracking services, some carriers whose numbers have as many digits, such as Ukrposhta and Delhivery for 13, or Hermes Germany and BRT for 15. If one of them shows the parcel moving, Peek moves the parcel there and shows "Swapped automatically from Ozon".

**Another carrier delivering.** If a tracking service names another carrier for your parcel, with that carrier's number, and Peek reads that carrier's own tracking, Peek asks it too. Once it shows a scan at least as recent as the service's latest, Peek follows the parcel there.

**Checks and alerts.** While a tracking service has the parcel, Peek checks it every 15 minutes, also when it is out for delivery. After two days without a new step, it checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". For a pickup point, choose "Important steps": "Delivery only" doesn't tell you when the order is ready to collect. Peek can't contact Ozon or the seller for you.

## Questions about Ozon tracking

### What does an Ozon tracking number look like?

Peek links two shapes to Ozon, both with dashes: digits in three groups, 8 or 10 digits, then 4, then 1, such as `00000000-0000-0`, and numbers starting with `PL-`, such as `PL-000000-00000`. Type the dashes: without them, Peek can't tell the number is Ozon's.

### Why doesn't Peek recognise my Ozon number?

You probably typed it without its dashes, or with spaces. Type it again as `00000000-0000-0`, with your own digits, or press "Change" and pick Ozon.

### What does "Заказ в пункте выдачи" mean?

Your order is at your pickup point, and you can collect it. When you have it, Ozon's tracker shows `Заказ получен в пункте выдачи.`

### Can I track an Ozon order in English?

Yes. Peek shows the order's status in English, such as "In transit", and its history in Ozon's Russian words. The tables above explain those steps.

:::sources
- [Universal Parcel Scraper: Ozon](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ozon-logistics) – names, countries, the two number shapes and their dashes, tracking.ozon.ru and rocket.ozon.ru links, what Peek reads from tracking.ozon.ru and leaves out, the Russian steps of Ozon's tracker and their meaning, the courier's call, collection at a pickup point or post office
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – the carriers sharing 13 and 15 digits, Amazon's numbers, the order of tracking services, carriers asked in a parcel's first 30 days, hand-overs a tracking service names
:::
