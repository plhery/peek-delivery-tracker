---
title: XLCourier tracking: XLC codes, heavy UK parcels and help
description: Track an XLCourier (XL Courier UK) bulky parcel: XLC and ZX codes, 100–1600 scan statuses, 30–70 kg delivery times, POD signatures and Birmingham support.
slug: xlcourier-tracking
published: 2026-11-23
updated: 2026-11-23
---

XLCourier (*XL Courier Limited*, based in Birmingham) is a UK courier specialising in heavy, oversized and bulky B2B and e-commerce deliveries — particularly parcels weighing 30 to 70 kg such as furniture, household appliances, electric bicycles, large-capacity batteries, fitness equipment and fragile or industrial goods. Operating its own UK delivery fleet across the North East, East and South of England alongside nationwide UK distribution, XLCourier quotes an average transit time of 1 to 3 working days with live GPS tracking and electronic Proof of Delivery (POD). You can look up a parcel on [XLCourier's Track Your Order page](https://www.xlcourier.com/trackOrder), or paste your tracking number into the box above.

## XLCourier tracking numbers: XLC and ZX formats

Find your tracking number in the retailer's dispatch email, SMS notification or order screen. XLCourier numbers follow two prefixes:

| Format (invented) | What it is |
| --- | --- |
| `XLC48291736405` | Standard XLCourier UK waybill: `XLC` followed by 10 to 12 digits (13 to 15 characters total). |
| `ZX48291736` | Alternate 10-character XLCourier code (`ZX` + 8 digits), accepted by XLCourier's tracking portal. |

If your bulky UK delivery is carried by another UK large-parcel network — such as [XDP](carrier:xdp), [Parcelforce](carrier:parcelforce) or [APC Overnight](carrier:apc-overnight) — check [tracking number formats](guide:tracking-number-formats) to identify its prefix.

## What XLCourier tracking statuses and codes mean

XLCourier's tracker gives each scan a numeric event code (`100` to `1600`), a short title and a line describing it (with the reason when a visit failed):

| XLCourier code & title | What it means for you | Peek shows |
| --- | --- | --- |
| `100` (`Created`), `200` (`Scheduled`), `300` (`Out for Pickup`), `400` (`Pickup Failed`) | Parcel data submitted and pickup booked or attempted; XLCourier has not collected the physical parcel yet. | "Label created" |
| `500` (`Picked Up`) | Collected successfully from the seller or warehouse. | "Received by carrier" |
| `600` (`Received`), `700` (`Pending Transit`), `800` (`In Transit`), `900` (`Scheduled`) | Arrived at XLCourier's local depot or national hub, pending transfer, departed the hub or scheduled onto a delivery route. | "In transit" |
| `1000` (`Out for Delivery`) | Loaded on an XLCourier vehicle for delivery today. | "Out for delivery" |
| `1100` (`Delivery Failed`) | Delivery attempt could not be completed (with a reason appended where provided). | "Delivery attempted" |
| `1200` (`Return Scheduled`), `1300` (`Returning`), `1400` (`Return Failed`), `1500` (`Returned`) | Scheduled for return or travelling back to the sender. | "Returned to sender" |
| `1570` (`Destroyed`) | Parcel marked destroyed in the carrier's system; contact the seller immediately. | "Needs attention" |
| `1600` (`Delivered`) | Delivered to the recipient with electronic POD signature. | "Delivered" |

XLCourier uses the same title `Scheduled` for code `200` (pickup from the sender scheduled) and code `900` (delivery to you scheduled): the line under the title tells them apart.

## How XLCourier UK bulky delivery works

According to [xlcourier.com](https://www.xlcourier.com/) and [XLCourier's About Us page](https://www.xlcourier.com/aboutUs):

- **Heavy and specialist items:** built for 30–70 kg and oversized items including sofas, cabinets, white goods, e-bikes, scooters, portable power stations / large-capacity batteries, gym machines, glass/TV displays and industrial equipment, handled with forklifts and padded straps.
- **Transit time and notifications:** XLCourier quotes an average delivery time of **1 to 3 working days**, with UK customer support available over live chat, WhatsApp, SMS and email and live GPS driver visibility on delivery day.
- **Electronic Proof of Delivery (POD):** couriers capture a digital recipient signature and multi-angle POD photo upon delivery.

## XLCourier parcel delayed, failed delivery or damaged

Because the UK retailer or e-commerce seller is XLCourier's contracting client, always notify the seller alongside contacting XLCourier if a heavy delivery fails or arrives damaged.

- **Failed delivery (`1100`):** check the scan reason (for example access restrictions, no answer or incomplete address) and contact XLCourier's customer service and the seller right away, before a return (`1200`) is scheduled.
- **Heavy or fragile goods damaged:** inspect external packaging before signing the digital POD; photograph any crush marks or torn cartons and report visible damage immediately to the seller and XLCourier.
- **Stuck or missing parcel:** see [why tracking stops updating](guide:tracking-not-updating), [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## XLCourier customer service and contacts

Official contacts published on [XLCourier Contact Us](https://www.xlcourier.com/contactUs):

- **UK customer service email:** `customerservice@xlcourier.com`.
- **UK phone:** `07476 947927`.
- **Germany customer service:** `decustomerservice@xlcourier.com`, `+49 177 5831818`.
- **Address:** XL Courier Limited, Unit 40, Holly Park Industrial Estate, Spitfire Road, Birmingham, B24 9PB, United Kingdom.
- **Additional channels:** live chat, WhatsApp, SMS and email support noted on [xlcourier.com](https://www.xlcourier.com/).

> Be cautious of unsolicited text messages demanding an online card fee to "reschedule" a heavy UK delivery. Verify your `XLC` number directly on [xlcourier.com/trackOrder](https://www.xlcourier.com/trackOrder) or confirm with `customerservice@xlcourier.com`. See [delivery scam texts](guide:delivery-scam-texts).

## Track an XLCourier parcel with Peek

[Peek](/) automatically recognises `XLC` + 10 to 12 digits (`XLC48291736405`, digits invented) as XLCourier with high confidence, and also recognises pasted `xlcourier.com/trackOrder?number=` links. For `ZX` + 8-digit codes (`ZX48291736`), tap "Change" while adding or "Change carrier" on the parcel screen and select XLCourier (searching "XL Courier" or "XLC" finds it too).

Peek reads XLCourier's tracker directly and shows the stage ("Label created", "Received by carrier", "In transit", "Out for delivery", "Delivery attempted", "Needs attention", "Returned to sender", "Delivered"), XLCourier's scan line with the reason for a failed visit (phone numbers and email addresses taken out) and the depot or hub an arrival line names. XLCourier's codes (`100` to `1600`) set the stage; Peek doesn't show them. XLCourier gives each scan's time without a zone: Peek dates it on its place's time zone, UK time for a UK depot, and shows it on your device's clock. The recipient's address stays out of every Peek app. XLCourier's tracker gives no estimated delivery date, weight, service name, separate delivery time or local partner.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day XLCourier shows `1000` (`Out for Delivery`), every 2 minutes from 6 a.m. on the clock of the newest scan's place, while XLCourier's own tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When XLCourier's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking XLCourier.", with a "Get help from XLCourier" link to XLCourier's contact page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking XLCourier."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot book a delivery slot, access live driver GPS maps or arrange redelivery with XLCourier for you.

## Questions about XLCourier tracking

### What is XLCourier in the UK?

XLCourier (*XL Courier Limited*, based in Birmingham) is a specialist UK courier focused on heavy and oversized parcels (typically 30–70 kg) such as furniture, appliances, e-bikes, large-capacity batteries and fitness equipment.

### What does an XLCourier tracking number look like?

Most XLCourier numbers start with `XLC` followed by 10 to 12 digits (for example `XLC48291736405`, digits invented). XLCourier's tracker also supports `ZX` + 8-digit codes (`ZX48291736`).

### How long does XLCourier delivery take?

On [xlcourier.com](https://www.xlcourier.com/), XLCourier states an average UK delivery time of **1 to 3 working days**, with electronic Proof of Delivery (POD) and signature on arrival.

### How do I contact XLCourier UK customer service?

Email `customerservice@xlcourier.com` or call `07476 947927` with your `XLC` tracking number. XLCourier also names live chat, WhatsApp and SMS on [xlcourier.com](https://www.xlcourier.com/).

:::sources
- [XLCourier: Home](https://www.xlcourier.com/) – UK extra-large/heavy courier (30–70 kg), 1–3 working days average delivery, live GPS, POD signature and UK coverage
- [XLCourier: About Us](https://www.xlcourier.com/aboutUs) – TMS/WMS workflow, specialist handling, electronic POD and company background
- [XLCourier: Track Your Order](https://www.xlcourier.com/trackOrder) – public tracking portal
- [XLCourier: Contact Us](https://www.xlcourier.com/contactUs) – `customerservice@xlcourier.com`, `07476 947927`, DE contacts and Birmingham headquarters (`Unit 40, Holly Park Industrial Estate, B24 9PB`) (help URL linked by Peek)
- [Universal Parcel Scraper: xlcourier](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/xlcourier) – `XLC` / `ZX` formats, `trackOrder?number=` link rule, event codes `100`–`1600`, each scan's place clock, depot parsing and help URL
:::
