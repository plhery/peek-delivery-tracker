---
title: GO! Express tracking: 12-digit waybills, statuses & help
description: Track a GO! Express & Logistics (General Overnight) shipment in Germany and Europe: 12-digit waybill numbers, hub-line scans, station finder and 0800 859 99 99.
slug: go-express-tracking
published: 2026-11-23
updated: 2026-11-23
---

**GO! Express** (*GO! Express & Logistics*, also known as *General Overnight*) is a European express and courier network with country sites for Germany, Austria, the Czech Republic, Denmark, Luxembourg, the Netherlands, Poland and Slovakia, plus GO! Switzerland and GO! Liechtenstein ([general-overnight.com/deu_en](https://www.general-overnight.com/deu_en)). Its products include the national **GO! Overnight** and international **GO! Worldwide** express services, couriers (**GO! City**, **GO! Direct**, **GO! Same Day**, **GO! On-Board-Courier**, **GO! Air Charter**) and B2B solutions for pharmaceutical logistics, medical technology and hazardous goods; its shipping request form has a note on shipments over 50 kg or longer than 2.70 m. GO! stations are linked by overnight hub lines. You can track a shipment on [general-overnight.com/deu_de/versenden/sendungsverfolgung.html](https://www.general-overnight.com/deu_de/versenden/sendungsverfolgung.html) or paste your 12-digit waybill number into the box above.

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
| `Recording station` / `Eingang Station`, `Erfassung Station` | The shipment entered a GO! station and the station recorded it. | "In transit" |
| `Loading hub line` / `Beladung Hub-Linie` | Loaded onto GO!'s overnight trunk vehicle heading to the central hub or destination station. | "In transit" |
| `Unloading hub line` / `Entladung Hub-Linie` | Unloaded from the overnight trunk line at a central hub or at the delivering GO! station. | "In transit" |
| `Loading delivery trip` / `Beladung Zustelltour` | Loaded onto the local GO! courier vehicle for final delivery today. | "Out for delivery" |
| `Abholung war nicht möglich.` (or error-flagged scan) | Courier could not collect the shipment from the sender or an error occurred on the route. | "Needs attention" |
| `Delivery consignee` / `Zustellung Empfänger` | Delivered to the recipient by the local GO! courier. | "Delivered" |

## Overnight hub lines, local GO! stations and international shipping

According to [GO! Express Germany](https://www.general-overnight.com/deu_en) and [Shipment Tracking](https://www.general-overnight.com/deu_de/versenden/sendungsverfolgung.html):

- **How hub lines work:** a hub line is GO!'s overnight trunk between its stations and hubs. A shipment is loaded onto it where it leaves a station or hub (`Loading hub line`) and unloaded at a hub or at the delivering station (`Unloading hub line`); the courier's delivery tour follows (`Loading delivery trip`).
- **Your GO! station:** GO!'s tracking page sends questions about a shipment to the GO! station responsible for it; the **Station Finder** on [general-overnight.com/deu_en](https://www.general-overnight.com/deu_en) finds it from your postcode or town.
- **Customs:** GO! lists complete customs clearance among its additional services. See [parcels held at customs](guide:customs).

## GO! Express parcel delayed, missed or missing

- **Missed delivery:** contact your GO! station or call `0800 / 859 99 99` with your 12-digit waybill number.
- **Pickup failed (`Abholung war nicht möglich.`):** the shipment is still with the sender; contact your retailer or supplier to reschedule collection.
- **Damaged, late or missing shipment:** contact the sender and your GO! station, and if `Delivery consignee` shows, check with building reception or neighbours. See [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel), [returned to sender](guide:returned-to-sender) and [why tracking stops updating](guide:tracking-not-updating).

## GO! Express customer service and contacts

Official contact channels published on [GO! Express Contact (English)](https://www.general-overnight.com/deu_en/online-services/contact.html) and [GO! Kontakt (German)](https://www.general-overnight.com/deu_de/online-services/kontakt.html):

- **Toll-free hotline (Germany):** **`0800 / 859 99 99`**.
- **Online contact forms (routed by postcode to your local GO! station):** [general-overnight.com/deu_en/online-services/contact.html](https://www.general-overnight.com/deu_en/online-services/contact.html) and [general-overnight.com/deu_de/online-services/kontakt.html](https://www.general-overnight.com/deu_de/online-services/kontakt.html).
- **Online tracker & Station Finder:** [general-overnight.com/deu_de/versenden/sendungsverfolgung.html](https://www.general-overnight.com/deu_de/versenden/sendungsverfolgung.html) (works across all GO! country networks).

> Beware of fake text messages asking you to pay a small online fee to reschedule a courier delivery. Verify any delivery update directly via [general-overnight.com/deu_de/versenden/sendungsverfolgung.html](https://www.general-overnight.com/deu_de/versenden/sendungsverfolgung.html) or `0800 / 859 99 99`. See [delivery scam texts](guide:delivery-scam-texts).

## Track a GO! Express parcel with Peek

Paste a `general-overnight.com/...?reference=` tracking link into [Peek](/) and Peek recognises GO! Express. Peek never asks GO! Express from a bare 12-digit number (`348291736054` or `068473918462`, invented), as 12 digits fit other carriers: it asks [BRT](carrier:brt) and [Colis Privé](carrier:colis-prive), among others, instead. Enter your waybill number and tap "Change" while adding or "Change carrier" on the parcel screen to pick **GO! Express** (searching "GO! Express & Logistics", "GO Express" or "General Overnight" finds it too); Peek then checks the number with GO! and shows "GO! Express has this parcel." once confirmed.

Peek reads GO!'s tracker in English, as Germany's English page shows it (every GO! country's page reads the same shipments), and shows the stage ("Received by carrier", "In transit", "Out for delivery", "Needs attention", "Delivered"), the English scan wording and the station or hub of each scan. GO! gives each scan a clock without an offset: Peek dates it on its place's time zone (Berlin's for a scan in Frankfurt, Vienna's for one in Wien), a scan without a place on the zone of the scan beside it, or else on Berlin's, and shows them all on your device's clock. A scan listed twice with the same time, wording and place is kept once. Peek leaves out the progress rail and the station's contact details; GO!'s tracker gives no estimated delivery date, weight, service name or sender or recipient names.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day GO! shows `Loading delivery trip`, every 2 minutes from 6 a.m. on the newest scan's clock, while GO!'s tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When GO!'s tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking GO! Express.", with a "Get help from GO! Express" link to GO!'s contact page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking GO! Express."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot reschedule deliveries, redirect parcels or contact your local GO! station for you.

## Questions about GO! Express tracking

### What does a GO! Express tracking number look like?

GO! Express waybill numbers (*Frachtbriefnummern*) are 12 numeric digits with no letters — for example `348291736054` or `068473918462` (invented; keep any leading zeros).

### What do `Loading hub line` and `Unloading hub line` mean?

GO! moves overnight shipments between its stations and hubs on overnight hub lines. `Loading hub line` means your parcel boarded the overnight trunk vehicle, and `Unloading hub line` means it arrived at a central hub or your local delivering station.

### Which countries does GO! Express operate in?

GO! Express & Logistics operates networks in Germany, Austria, the Czech Republic, Denmark, Luxembourg, the Netherlands, Poland, Slovakia, Switzerland and Liechtenstein, plus international **GO! Worldwide** shipping.

### How do I contact GO! Express customer service?

Call Germany's toll-free hotline `0800 / 859 99 99`, find your local depot with the Station Finder on [general-overnight.com/deu_en](https://www.general-overnight.com/deu_en), or submit the postcode-routed contact form at [general-overnight.com/deu_en/online-services/contact.html](https://www.general-overnight.com/deu_en/online-services/contact.html).

:::sources
- [GO! Express & Logistics: Official Website (English)](https://www.general-overnight.com/deu_en) – GO! Overnight, Worldwide, Courier, Solutions, European country networks, Station Finder and `0800 / 859 99 99`
- [GO! Express & Logistics: Sendungsverfolgung (German)](https://www.general-overnight.com/deu_de/versenden/sendungsverfolgung.html) – official waybill tracker, Station Finder and GO! Express App
- [GO! Express & Logistics: Contact (English)](https://www.general-overnight.com/deu_en/online-services/contact.html) – postcode-routed station contact form and `0800 / 859 99 99`
- [GO! Express & Logistics: Kontakt (German)](https://www.general-overnight.com/deu_de/online-services/kontakt.html) – German contact portal (help URL linked by Peek)
- [Universal Parcel Scraper: go-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/go-express) – 12-digit HWB format, `general-overnight.com` link rule, EN/DE scan wording map (`Beladung Hub-Linie` / `Zustellung Empfänger`), the scan clocks and help URL
:::
