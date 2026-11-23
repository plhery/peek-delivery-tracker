---
title: GO! Express tracking: 12-digit waybills, statuses & help
description: Track a GO! Express & Logistics (General Overnight) shipment in Germany and Europe: 12-digit waybill numbers, hub-line scans, station finder and 0800 859 99 99.
slug: go-express-tracking
published: 2026-11-23
updated: 2026-11-23
---

**GO! Express** (*GO! Express & Logistics*, also known as *General Overnight*) is a German and European express courier and overnight logistics network operating across Germany, Austria, the Czech Republic, Denmark, Luxembourg, the Netherlands, Poland, Slovakia, Switzerland and Liechtenstein ([general-overnight.com/deu_en](https://www.general-overnight.com/deu_en)). Specializing in time-critical overnight B2B and B2C parcels (**GO! Overnight**, **GO! Worldwide**), same-day and direct couriers (**GO! City**, **GO! Direct**, **GO! Same Day**, **GO! On-Board-Courier**, **GO! Air Charter**) and specialist freight (pharmaceuticals, medical technology, hazardous goods and oversized shipments over 50 kg), GO! operates via decentralized regional stations linked by overnight hub lines. You can track a shipment on [general-overnight.com/deu_de/versenden/sendungsverfolgung.html](https://www.general-overnight.com/deu_de/versenden/sendungsverfolgung.html) or paste your 12-digit waybill number into the box above.

## GO! Express tracking numbers (12-digit HWB format)

GO! Express assigns every shipment a **12-digit waybill number** (*Frachtbriefnummer* / HWB) with no letters:

| Format (invented) | What it is |
| --- | --- |
| `348291736054` | Standard 12-digit GO! Express waybill number (*HWB*). |
| `068473918462` | Alternative 12-digit GO! Express waybill number (keep leading zeros). |

Because pure 12-digit numbers overlap with several other parcel and freight carriers across Europe and worldwide, pasting your `general-overnight.com/...?reference=` notification link or selecting **GO! Express** manually ensures Peek queries GO! directly. See [tracking number formats](guide:tracking-number-formats).

## What GO! Express tracking statuses mean

GO!'s tracking system publishes timestamped station and hub-line milestones without numeric status codes (shown in English on GO!'s English tracker and German on the German portal):

| GO! Express says (EN / DE) | What it means for you | Peek shows |
| --- | --- | --- |
| `Picked up - shipment picked up` / `Abgeholt - Sendung übernommen` | Courier collected the shipment from the sender. | "Received by carrier" |
| `Eingang Station`, `Recording station` / `Erfassung Station` | Shipment arrived at and was logged in by the local GO! station. | "In transit" |
| `Loading hub line` / `Beladung Hub-Linie` | Loaded onto GO!'s overnight trunk vehicle heading to the central hub or destination station. | "In transit" |
| `Unloading hub line` / `Entladung Hub-Linie` | Unloaded from the overnight trunk line at a central hub or at the delivering GO! station. | "In transit" |
| `Loading delivery trip` / `Beladung Zustelltour` | Loaded onto the local GO! courier vehicle for final delivery today. | "Out for delivery" |
| `Abholung war nicht möglich.` (or error-flagged scan) | Courier could not collect the shipment from the sender or an error occurred on the route. | "Needs attention" |
| `Delivery consignee` / `Zustellung Empfänger` | Delivered to the recipient by the local GO! courier. | "Delivered" |

## Overnight hub lines, local GO! stations and international shipping

According to [GO! Express Germany](https://www.general-overnight.com/deu_en) and [Shipment Tracking](https://www.general-overnight.com/deu_de/versenden/sendungsverfolgung.html):

- **How overnight hub lines work:** regional GO! stations collect parcels in the afternoon (`Picked up - shipment picked up`), sort them (`Recording station`), move them overnight via central hub lines (`Loading hub line` / `Unloading hub line`), and dispatch them from the destination station early the next morning (`Loading delivery trip`).
- **Station-based support:** because each local GO! station runs its own courier fleet, entering your postcode or city in GO!'s **Station Finder** on [general-overnight.com/deu_en](https://www.general-overnight.com/deu_en) connects you directly to the depot holding your parcel.
- **Cross-border & customs:** shipments inside the EU move without customs duties; **GO! Worldwide** shipments crossing outside the EU (including into Switzerland or non-EU destinations) undergo customs clearance. See [parcels held at customs](guide:customs).

## GO! Express parcel delayed, missed or missing

- **Missed delivery or urgent redelivery:** because GO! specialises in time-definite overnight deliveries, contact your local GO! station or call Germany's toll-free hotline `0800 / 859 99 99` immediately with your 12-digit waybill number to arrange a fresh delivery window or station pickup.
- **Pickup failed (`Abholung war nicht möglich.`):** the shipment is still with the sender; contact your retailer or supplier to reschedule collection.
- **Damaged, late or missing shipment:** notify the shipper right away (they hold the GO! service contract for claims and guaranteed-time enquiries) and check with building reception or neighbours if `Delivery consignee` was posted. See [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel), [returned to sender](guide:returned-to-sender) and [why tracking stops updating](guide:tracking-not-updating).

## GO! Express customer service and contacts

Official contact channels published on [GO! Express Contact (English)](https://www.general-overnight.com/deu_en/online-services/contact.html) and [GO! Kontakt (German)](https://www.general-overnight.com/deu_de/online-services/kontakt.html):

- **Toll-free hotline (Germany):** **`0800 / 859 99 99`**.
- **Online contact forms (routed by postcode to your local GO! station):** [general-overnight.com/deu_en/online-services/contact.html](https://www.general-overnight.com/deu_en/online-services/contact.html) and [general-overnight.com/deu_de/online-services/kontakt.html](https://www.general-overnight.com/deu_de/online-services/kontakt.html).
- **Online tracker & Station Finder:** [general-overnight.com/deu_de/versenden/sendungsverfolgung.html](https://www.general-overnight.com/deu_de/versenden/sendungsverfolgung.html) (works across all GO! country networks).

> Beware of fake text messages asking you to pay a small online fee to reschedule a courier delivery. Verify any delivery update directly via [general-overnight.com/deu_de/versenden/sendungsverfolgung.html](https://www.general-overnight.com/deu_de/versenden/sendungsverfolgung.html) or `0800 / 859 99 99`. See [delivery scam texts](guide:delivery-scam-texts).

## Track a GO! Express parcel with Peek

Paste a `general-overnight.com/...?reference=` tracking link into [Peek](/) and Peek recognises GO! Express automatically. Because bare 12-digit waybill numbers (`348291736054` or `068473918462`, invented) overlap with many other couriers and GO! Express has no exclusive prefix, enter your 12-digit number and tap "Change" while adding or "Change carrier" on the parcel screen to choose **GO! Express** (searching "GO! Express & Logistics", "GO Express" or "General Overnight" finds it too); Peek verifies the waybill directly with GO! and displays `"GO! Express has this parcel."` once confirmed.

Peek queries GO! Express's tracking service directly in English (covering shipments across every GO! European country network) and displays the mapped stage ("Received by carrier", "In transit", "Out for delivery", "Needs attention", "Delivered"), English scan wording, station and hub locations, and local wall-clock timestamps as printed by GO! (without a time-zone offset, deduplicating identical multi-parcel or multi-remark scans at the same minute). Peek omits the progress rail and station contact card; GO!'s guest tracker does not supply an estimated delivery date, weight, service name or sender/recipient names.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. local time on the day an out-for-delivery scan arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12"), shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking GO! Express." and displays "Get help from GO! Express", which opens GO!'s contact page. After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking GO! Express."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot reschedule deliveries, redirect parcels or contact your local GO! station for you.

## Questions about GO! Express tracking

### What does a GO! Express tracking number look like?

GO! Express waybill numbers (*Frachtbriefnummern*) are 12 numeric digits with no letters — for example `348291736054` or `068473918462` (invented; keep any leading zeros).

### What do `Loading hub line` and `Unloading hub line` mean?

GO! moves overnight shipments between decentralized regional stations via central hub trunk lines. `Loading hub line` means your parcel boarded the overnight trunk vehicle, and `Unloading hub line` means it arrived at a central hub or your local delivering station.

### Which countries does GO! Express operate in?

GO! Express & Logistics operates networks in Germany, Austria, the Czech Republic, Denmark, Luxembourg, the Netherlands, Poland, Slovakia, Switzerland and Liechtenstein, plus international **GO! Worldwide** shipping.

### How do I contact GO! Express customer service?

Call Germany's toll-free hotline `0800 / 859 99 99`, find your local depot with the Station Finder on [general-overnight.com/deu_en](https://www.general-overnight.com/deu_en), or submit the postcode-routed contact form at [general-overnight.com/deu_en/online-services/contact.html](https://www.general-overnight.com/deu_en/online-services/contact.html).

:::sources
- [GO! Express & Logistics: Official Website (English)](https://www.general-overnight.com/deu_en) – GO! Overnight, Worldwide, Courier, Solutions, European country networks, Station Finder and `0800 / 859 99 99`
- [GO! Express & Logistics: Sendungsverfolgung (German)](https://www.general-overnight.com/deu_de/versenden/sendungsverfolgung.html) – official waybill tracker, Station Finder and GO! Express App
- [GO! Express & Logistics: Contact (English)](https://www.general-overnight.com/deu_en/online-services/contact.html) – postcode-routed station contact form and `0800 / 859 99 99`
- [GO! Express & Logistics: Kontakt (German)](https://www.general-overnight.com/deu_de/online-services/kontakt.html) – German contact portal (help URL linked by Peek)
- [Universal Parcel Scraper: go-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/go-express) – 12-digit HWB format, `general-overnight.com` link rule, EN/DE scan wording map (`Beladung Hub-Linie` / `Zustellung Empfänger`), local wall clocks and help URL
:::
