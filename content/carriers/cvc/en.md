---
title: CVC tracking (Си Ви Си): follow a parcel in Bulgaria
description: Track a CVC (Си Ви Си, e-CVC) parcel in Bulgaria with Peek: how to add the number and pick CVC, what each status means and what to do when it stops.
slug: cvc-tracking
published: 2026-11-13
updated: 2026-11-21
---

CVC is a carrier in Bulgaria. Peek's catalog also knows it as Си Ви Си, Си Ви Си ЕООД and e-CVC. To track a CVC parcel, paste a link to CVC's tracker in the box above, or paste its number and pick CVC as the carrier: Peek can't tell a CVC number from its shape. The number comes from the shop or the person who sent the parcel. CVC's own tracker is at my.e-cvc.bg/track, in Bulgarian: it asks for the waybill number and has a single button, "Провери" (Check).

CVC's own sites say little more: my.e-cvc.bg shows only that tracker and a login page. So this page says what CVC's tracker asks for and what Peek does with a CVC parcel, but nothing about CVC's delivery rules, prices or contacts.

## CVC tracking number: what it looks like

CVC's tracker asks for a waybill number ("товарителница" in Bulgarian). Its box is for digits: it drops letters and spaces as you type and takes at most 20 characters. So a CVC number is made of digits, but Peek's catalog has no number shape for CVC: Peek can't recognise the number as CVC's, and CVC never appears under "Fits this number" when you add it. This page gives no example: CVC's tracker doesn't show what a full number looks like.

| What you paste | What Peek does |
| --- | --- |
| The CVC number alone | It doesn't name CVC. It may list other carriers whose shapes the number fits, or none. Pick CVC yourself, as shown under "Track a CVC parcel with Peek" below. |
| A link to CVC's tracker: `my.e-cvc.bg/track?wb=`, then the number | Peek takes the number out of the link and names CVC at once ("Detected carrier"). |
| A number the shop says is another carrier's | Track it with that carrier: the shop's email is the best clue to who delivers. |

**Where to find it.** In the shop's shipping email or on your order page, sometimes as the waybill number. For a parcel from a person, ask them. No number at all? See [how to find a tracking number](guide:find-tracking-number), and [tracking number formats](guide:tracking-number-formats) for what other carriers' numbers look like.

## What CVC tracking statuses mean in Peek

Peek reads CVC's own tracker and shows each line in CVC's Bulgarian words, sorted into one of Peek's statuses. The wordings Peek's code knows:

| CVC says | Peek shows | What it means for you |
| --- | --- | --- |
| `Приета за събиране` | "Label created" | The shipment is booked for pickup. CVC doesn't have the parcel yet. |
| `Отложено събиране за …` | "Label created" | The pickup was put off to the day it names. |
| `Взета от куриер …` | "Received by carrier" | A CVC courier picked the parcel up. |
| `Приета в Хъб …`, `Транспорт към Хъб …` | "In transit" | The parcel reached a CVC hub, or is on its way to one. |
| `Приета за разнос` | "Out for delivery" | It's on the delivery round. |
| `Доставена`, `Доставена на …` | "Delivered" | Delivered, to you or someone who took it for you. |

A line Peek's code doesn't know keeps the status before it, with CVC's words. When CVC's tracker doesn't know the number, it answers `Пратката не е открита!` (shipment not found). If CVC's tracker fails, Peek can also ask tracking services, such as ParcelsApp, Ship24 and 17TRACK, and show the history of the first one that has the parcel, in that service's words.

Before CVC has a line, Peek shows "Checking for updates" while it asks, then "Waiting for CVC". If two checks in a row fail, the parcel still shows "Waiting for CVC", and its page says "CVC couldn’t be reached yet. Peek keeps trying." More in [tracking statuses explained](guide:tracking-statuses).

## CVC delivery in Bulgaria

CVC's own sites give no delivery times, delivery attempts, offices, cash-on-delivery rules or pickup periods: my.e-cvc.bg shows only its tracker and a login. Ask the shop or the sender how and when CVC delivers: they booked the delivery. If a delivery fails or the parcel waits somewhere for you, see [missed delivery](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

## CVC parcel not updating, lost or returned

**Nothing yet.** CVC's tracker may not know the number yet. Peek then shows "Waiting for CVC" and keeps asking. If CVC still doesn't know the number two days after it first said so, Peek asks you to check it, choose another carrier or delete the parcel. Check the number against the shop's email. If nothing comes after a few days, open CVC's own tracker with "Open the CVC website" on the parcel's page, and ask the shop whether the parcel was really sent.

**No news for days.** After four days without a new line, a parcel still showing "Label created", "Received by carrier" or "In transit" gets a flag with the day of that line: "No tracking update since Mon 12 Oct". See [why tracking stops updating](guide:tracking-not-updating).

**Delivered, but nothing came.** Check around the door and with neighbours, then ask the shop. See [delivered but not received](guide:delivered-not-received).

**Lost or damaged.** Tell the shop first: it booked the delivery and can take it up with CVC or send you a new parcel. See [lost parcels](guide:lost-parcel) and [damaged parcels](guide:damaged-parcel).

**Returned.** "Returned to sender" means the parcel went back to the shop or the sender. Ask them for a refund or a new delivery. See [returned to sender](guide:returned-to-sender).

## CVC contact and customer service

CVC's own sites list no phone number, email or office: my.e-cvc.bg shows only its tracker and a login page. Peek gives only contacts read on a carrier's own pages, so this page lists none. The shop or the sender booked the delivery with CVC, so ask them first, with your order and tracking numbers to hand: they can take it up with CVC.

## Track a CVC parcel with Peek

[Peek](/) follows more than 4,500 carriers, CVC among them, but it can't tell a CVC parcel by its number alone. A link to CVC's tracker names CVC at once; with just the number, pick the carrier yourself:

1. Paste the number in the box above.
2. Press "Change" beside the carrier line and search for CVC. "e-CVC" or "Си Ви Си" finds it too, with "Also called e-CVC" or "Also called Си Ви Си" under the name, and so does "Bulgaria".
3. Pick CVC: the line then reads "Chosen by you". Track the parcel.

On a parcel you've already added, use "Change carrier". For CVC, Peek takes 1 to 20 digits; for anything else, it says CVC "doesn’t issue numbers like this one". If Peek lists other carriers under "Fits this number", they only fit the number's shape. Before you choose, the carrier line may say "not found yet · Peek keeps checking": that's normal for a new number.

If you leave the carrier to Peek, it looks the number up with tracking services. If they show the parcel moving and name CVC as its carrier, Peek files the parcel under CVC by itself.

**What you see.** The status, and each line of CVC's tracker with its date and time, read as Bulgarian time. CVC's tracker gives no place, expected day, pickup point or weight. "Open the CVC website" on the parcel's page opens the number on CVC's own tracker, in Bulgarian. If a tracking service shows the history instead, the link opens that service's page, as "Open the ParcelsApp website", for example.

**Another carrier.** If CVC's tracker doesn't know the number, Peek also asks, in the parcel's first 30 days, the carriers whose number shapes fit yours. If one of them shows the parcel moving under that number, Peek moves the parcel there and says "Swapped automatically from CVC".

**How often.** While the parcel is open in Peek, or with notifications on on the day CVC scans it `Приета за разнос`, Peek checks it up to every 10 minutes, and that day every 2 minutes from 6 a.m. Bulgarian time. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly, and every 6 hours after seven days without news at "Label created". Notifications can follow "Every scan", "Important steps" or "Delivery only".

Peek can't contact CVC, the shop or the sender for you, change the delivery or file a claim.

## Questions about CVC tracking

### How do I track a CVC parcel?

Paste a link to CVC's tracker in the box above and Peek names CVC itself. With the number alone, press "Change" and pick CVC: Peek can't recognise a CVC number by its shape. Peek then follows the parcel on CVC's own tracker. On a parcel you've already added, use "Change carrier". CVC's own tracker is at my.e-cvc.bg/track: type the waybill number and press "Провери".

### What does a CVC tracking number look like?

It's a waybill number ("товарителница") made of digits: CVC's tracker drops letters and spaces and takes at most 20 characters. Peek's catalog has no shape for it, so Peek can't recognise it as CVC's: pick CVC yourself.

### Is e-CVC the same as CVC?

Yes. CVC's tracker on my.e-cvc.bg calls itself e-CVC.bg and carries CVC's logo, and Peek's catalog lists e-CVC, Си Ви Си and Си Ви Си ЕООД as other names for CVC. Searching for any of these names in Peek's carrier list finds CVC.

### Why does my CVC parcel say "Waiting for CVC"?

On peektracker.com, a parcel filed under CVC shows this while CVC's tracker has no line for the number yet, or couldn't be reached: its page then says "CVC couldn’t be reached yet. Peek keeps trying." Peek keeps asking. Check the number with the shop, and look it up on CVC's own tracker with "Open the CVC website".

### What is CVC's phone number?

This page gives none: CVC's own sites list no phone number. Ask the shop that sent the parcel: it booked the delivery with CVC.

:::sources
- [CVC: e-CVC.bg tracking](https://my.e-cvc.bg/track) – tracker in Bulgarian, "Потърсете товарителница", digits, at most 20 characters, "Провери", number after `wb=`, e-CVC.bg name, CVC logo
- [CVC: e-CVC.bg login](https://my.e-cvc.bg/) – login page only, no delivery, prices or contact pages
- [Universal Parcel Scraper: CVC](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cvc) – names and other names, Bulgaria, tracker address, link rule, no number shape, CVC's tracker read directly, its Bulgarian wordings, Bulgarian time
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – tracking services, a service's carrier name matched to CVC, links to the carrier's or the service's page
:::
