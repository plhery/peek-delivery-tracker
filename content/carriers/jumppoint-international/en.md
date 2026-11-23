---
title: Jumppoint International tracking: JP codes, statuses & help
description: Track a Jumppoint International Express parcel from Hong Kong: JP tracking formats, cross-border scan milestones, transit times, customs & WhatsApp support.
slug: jumppoint-international-tracking
published: 2026-11-23
updated: 2026-11-23
---

**Jumppoint International Express** is the cross-border parcel division of **Jumppoint Logistics Technologies Limited**, an e-commerce logistics technology company founded in Hong Kong in 2020 ([jumppoint.io/en/home](https://www.jumppoint.io/en/home)). Using its proprietary routing platform (*jumppoint.ai*), Jumppoint ships cross-border e-commerce parcels from Hong Kong to over 125 cities and regions across Southeast Asia, Taiwan, the European Union, the United States and Canada. International shipments use a dedicated tracker at [int-tracking.jumppoint.io](https://int-tracking.jumppoint.io/) — separate from Jumppoint's Hong Kong domestic express tracker — or you can paste your `JP` tracking number into the box above.

## Jumppoint International tracking numbers (`JP` format)

Jumppoint International Express assigns a 14-character code starting with `JP`, followed by two uppercase letters and 10 digits:

| Format (invented) | What it is |
| --- | --- |
| `JPHK4829173605` | 14-character Jumppoint International Express tracking number (`JP` + 2 letters + 10 digits). |
| `JPUS7391846208` | Alternative 14-character `JP` international parcel code. |

Jumppoint's Hong Kong express service has its own tracker at `tracking.jumppoint.io`, for numbers made of `JP` and 12 digits; Peek follows those as Jumppoint's. A `JP` code with two letters and 10 digits belongs on [int-tracking.jumppoint.io](https://int-tracking.jumppoint.io/) or in Peek. See [tracking number formats](guide:tracking-number-formats).

## What Jumppoint International tracking statuses mean

The Jumppoint International tracker returns translated English and Chinese milestone labels rather than numeric status codes:

| Jumppoint International says | What it means for you | Peek shows |
| --- | --- | --- |
| `Order Created`, `Order Confirmed` | Shipment order created and confirmed electronically by the merchant before intake at Jumppoint's Hong Kong warehouse. | "Label created" |
| `Arrival at Origin Warehouse` | Physical parcel received and scanned at Jumppoint's origin warehouse in Hong Kong. | "Received by carrier" |
| `Handover to Linehaul`, `Departure from Origin Airport`, `Arrival at Destination Airport` | Dispatched onto international air linehaul, departed Hong Kong and landed in the destination country. | "In transit" |
| `Import Customs Handover To Lastmile` | Cleared destination import customs and handed over to the local last-mile delivery partner. | "In transit" |
| `Arrived at Main Sorting Warehouse`, `On way to next Sorting Station`, `Arrival at Last Mile Distribution Point` | Moving through the destination partner's domestic sorting network toward your local delivery depot. | "In transit" |
| `Out For Last Mile Delivery` | Loaded onto a local courier vehicle for final doorstep delivery. | "Out for delivery" |
| `Successful Delivery` | Delivered to the recipient's address by the local last-mile partner. | "Delivered" |

## Delivery times, coverage and cross-border handoffs

According to [Jumppoint International Express](https://www.jumppoint.io/en/international-express) and [Jumppoint About Us](https://www.jumppoint.io/en/about-us):

- **Published transit times from Hong Kong:** **3–5 working days** to Southeast Asia (Malaysia, Thailand, Singapore), **3–7 working days** to Taiwan, **7–11 working days** to the European Union and **7–13 working days** to the United States, across 125+ regions and cities worldwide (including Canada).
- **Pickup schedule & surcharges:** doorstep pickup is available from 6 items; merchants schedule Monday–Saturday collection (excluding Hong Kong public holidays) with a cut-off of 8:00 p.m. the day before pickup, with no fuel or residential surcharges and free delivery protection up to HKD 200 per shipment.
- **Customs and local last-mile delivery:** Jumppoint manages end-to-end pickup, export/import customs clearance and transfer to a local destination carrier (`Import Customs Handover To Lastmile`). Import VAT, duties or local customs procedures depend on the destination country's rules and how the merchant declared the order. See [parcels held at customs](guide:customs).

## Jumppoint International parcel delayed, stuck or missing

- **Quiet gaps between Hong Kong and destination:** tracking often pauses for several days between `Departure from Origin Airport`, `Arrival at Destination Airport` and `Import Customs Handover To Lastmile` while international cargo clears customs batches. See [why tracking stops updating](guide:tracking-not-updating).
- **Early labels:** `Order Created` and `Order Confirmed` come from the merchant's order; the parcel reaches Jumppoint at `Arrival at Origin Warehouse`.
- **Missing, damaged or undelivered parcels:** contact the online store first for replacement or refund (Jumppoint's included delivery warranty of up to HKD 200 applies to the shipping account holder), and reach out to Jumppoint Customer Service on WhatsApp with your 14-character `JP` code if delivery stalls after arrival in your country. See [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel), [delivered but not received](guide:delivered-not-received) and [returned to sender](guide:returned-to-sender).

## Jumppoint International customer service and contacts

Official contact channels published on [Jumppoint Contact Us (English)](https://www.jumppoint.io/en/contact-us) and [jumppoint.io/contact-us](https://www.jumppoint.io/contact-us):

- **Customer service (WhatsApp):** `+852 6058 8546`.
- **Business enquiries (WhatsApp):** `+852 6394 1903`.
- **Email:** `info@jumppoint.io`.
- **Online contact form:** [jumppoint.io/en/contact-us](https://www.jumppoint.io/en/contact-us) (published response time: 2–3 working days).
- **Headquarters:** *Jumppoint Logistics Technologies Limited*, Unit 1213, 12/F, Harbour Centre Tower 1, 1 Hok Cheung Street, Hung Hom, Kowloon, Hong Kong.

> Beware of fake SMS or WhatsApp messages asking for an online payment to release a Hong Kong parcel from customs. Always verify any genuine import fee through your merchant or official local customs/postal channel first. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Jumppoint International parcel with Peek

[Peek](/) detects Jumppoint International Express automatically as the sole candidate when you enter a 14-character `JP` number (`JPHK4829173605` or `JPUS7391846208`, invented) and shows "Jumppoint International Express has this parcel." once Jumppoint returns tracking history; Peek also recognises pasted `int-tracking.jumppoint.io/?q=` tracking links. If you ever need to choose it manually, tap "Change" while adding or "Change carrier" on the parcel screen (searching "Jumppoint International" or "Jumppoint國際快遞" finds it too).

Peek reads Jumppoint's international tracker and shows the stage ("Label created", "Received by carrier", "In transit", "Out for delivery", "Delivered", or another stage when a scan's wording says so) and the English scan labels. Jumppoint gives each scan with its UTC offset, and Peek shows them on your device's clock. Jumppoint's international tracker gives no place for its scans, and no estimated delivery date, weight, signer's name or local carrier's tracking number.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day Jumppoint shows `Out For Last Mile Delivery`, every 2 minutes from 6 a.m. on the newest scan's clock, while Jumppoint's own tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When Jumppoint's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Jumppoint International Express.", with a "Get help from Jumppoint International Express" link to Jumppoint's contact page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Jumppoint International Express."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot clear customs, edit your address or contact Jumppoint or its local delivery partner for you.

## Questions about Jumppoint International tracking

### What does a Jumppoint International tracking number look like?

Jumppoint International Express tracking numbers are 14 characters long: `JP` followed by two uppercase letters and 10 digits (for example `JPHK4829173605` or `JPUS7391846208`, invented).

### How long does Jumppoint International Express take from Hong Kong?

Published transit estimates on [jumppoint.io/en/international-express](https://www.jumppoint.io/en/international-express) are **3–5 working days** to Singapore, Malaysia and Thailand, **3–7 working days** to Taiwan, **7–11 working days** to the European Union and **7–13 working days** to the United States.

### Why won't my `JP` code work on Jumppoint's local Hong Kong tracker?

Jumppoint runs two separate portals: local Hong Kong express orders (`JP` + 12 digits) use `tracking.jumppoint.io`, while international cross-border shipments (`JP` + 2 letters + 10 digits) use [int-tracking.jumppoint.io](https://int-tracking.jumppoint.io/).

### How do I contact Jumppoint International customer service?

Reach Jumppoint Customer Service via WhatsApp at `+852 6058 8546`, email `info@jumppoint.io` or submit the online form at [jumppoint.io/en/contact-us](https://www.jumppoint.io/en/contact-us).

:::sources
- [Jumppoint: Official Website](https://www.jumppoint.io/en/home) – Hong Kong e-commerce logistics platform and domestic vs. international tracking portals
- [Jumppoint: International Economy Express](https://www.jumppoint.io/en/international-express) – 125+ destinations, transit times (SEA, Taiwan, EU, US), HKD 200 warranty and pickup schedule
- [Jumppoint: About Us](https://www.jumppoint.io/en/about-us) – founded 2020 in Hong Kong, *jumppoint.ai* routing platform and Asia-Pacific footprint
- [Jumppoint: Contact Us (English)](https://www.jumppoint.io/en/contact-us) – WhatsApp `+852 6058 8546`, `info@jumppoint.io` and Hung Hom Kowloon headquarters
- [Jumppoint: Contact Us (Help URL)](https://www.jumppoint.io/contact-us) – official help URL linked by Peek
- [Jumppoint International Tracking Portal](https://int-tracking.jumppoint.io/) – dedicated international `JP` waybill tracker
- [Universal Parcel Scraper: jumppoint-international](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jumppoint-international) – `JP[A-Z]{2}\d{10}` format, `int-tracking.jumppoint.io` link rule, 12-label status mapping, browser-backed lookup and help URL
:::
