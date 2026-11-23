---
title: AnserX tracking: BTYCN...YQ codes, handoffs & contacts
description: Track an AnserX (斑头雁) cross-border parcel from China: BTYCN...YQ waybills, BTY scan codes, local partner handoffs, customs and Shenzhen customer support.
slug: anserx-tracking
published: 2026-11-23
updated: 2026-11-23
---

AnserX (*Shenzhen AnserX International Logistics Technology Co., Ltd.*, Chinese *斑头雁国际物流*), founded in 2018 and headquartered in Shenzhen, is a Chinese cross-border e-commerce logistics provider operating dedicated small-parcel lines from China to the United States, Europe (including the UK, France and Germany), Japan, Canada, Australia, Southeast Asia, Mexico, Nigeria and Saudi Arabia, alongside FBA air/sea freight (0–30 kg, max side 1.2 m) and overseas warehouses. You can track a shipment on [AnserX's OMS tracking portal](https://oms.anserx.com/tracking), or paste your waybill number into the box above.

## AnserX tracking numbers: BTYCN...YQ format

Check your online marketplace order details or dispatch confirmation email for the tracking number. AnserX waybills are 17 characters long: `BTYCN` followed by 10 digits and ending in `YQ`:

| Format (invented) | What it is |
| --- | --- |
| `BTYCN4829173640YQ` | Standard AnserX cross-border parcel waybill: `BTYCN` + 10 digits + `YQ`. |

Once your shipment arrives in the destination country and clears customs, AnserX transfers it to a local postal or courier operator (such as [USPS](carrier:usps) in the United States or a local last-mile partner in Europe or Mexico) and attaches that partner's local delivery tracking number while relaying the local scans under your `BTYCN...YQ` code. For other Chinese cross-border code prefixes, see [tracking number formats](guide:tracking-number-formats).

## What AnserX tracking statuses and codes mean

AnserX pairs `BTY_` milestone codes with English and Chinese scan descriptions across export sorting, air linehaul, customs clearance and destination delivery:

| AnserX code & wording | What it means for you | Peek shows |
| --- | --- | --- |
| `BTY_ORDER_CREATED` (`Shipment information received`) | The seller transmitted electronic order data to AnserX before the physical parcel arrived at the hub. | "Label created" |
| `BTY_WAREHOUSE_INBOUND` (`Shipment inbound in sorting center`) | Received and scanned into AnserX's sorting hub in China. | "Received by carrier" |
| `BTY_WAREHOUSE_OUTBOUND`, `BTY_LINE_HANDOVER` (`Hand over to airline` / `Departed from origin port`), `BTY_LINE_ARRIVED` (`Arrived at destination port`) | Departed China's sorting hub, handed to the airline and landed at the destination airport. | "In transit" |
| `BTY_EXCC_COMPLETED` (`Relased from export customs` / `目的地进口清关完成`) | Customs clearance completed (note: AnserX's English wording says "export" while the Chinese text states destination import clearance finished); the parcel continues into local transit. | "In transit" |
| `BTY_LASTMILE_TRANSIT` (`Delivery to local courier company` / `arrival at transportation partner facilities`) | Handed to the destination country's last-mile courier and moving toward the local delivery office. | "In transit" |
| `BTY_LASTMILE_DELIVERING` (`Parcel currently being delivered` / `Out for Delivery`) | Out with the local delivery driver today. | "Out for delivery" |
| `BTY_LASTMILE_DELIVERED` (`Parcel has been delivered` / `Delivered`) | Delivered to the recipient by the destination partner. | "Delivered" |

## How AnserX cross-border delivery works

On [anserx.com](https://www.anserx.com/) and [About AnserX](https://www.anserx.com/about.html), AnserX describes its intelligent capacity and routing platform linking domestic collection in China, export hubs, air linehaul, customs clearance and destination last-mile carriers (including dedicated small-parcel channels that support battery-containing electronics).

**Last-mile delivery and customs.** AnserX is a cross-border linehaul consolidator rather than a consumer doorstep delivery fleet outside China. Once the parcel clears destination customs (`BTY_EXCC_COMPLETED`), your country's local postal or express partner controls doorstep attempts, safe-place drop-offs, pickup-point storage and returns. Any import VAT, customs duties or handling charges follow destination customs rules unless prepaid by the seller at checkout; see [parcels held at customs](guide:customs).

## AnserX parcel stuck, quiet or not delivered

AnserX's customers are the sellers who ship with it, so contact your seller first for late shipments, wrong addresses, refunds or lost-parcel claims.

- **Quiet gaps during air linehaul or customs:** tracking often pauses for several days between `BTY_LINE_HANDOVER` (departed origin port), `BTY_LINE_ARRIVED` and `BTY_EXCC_COMPLETED` while flights land and customs processes consolidated batches. See [why tracking stops updating](guide:tracking-not-updating).
- **Check the local delivery number after handover:** once AnserX links the destination carrier's tracking number, looking that code up directly on your local courier's site often gives fuller delivery-window or pickup details.
- **Delivered, damaged or missing:** check with household members, neighbours, building reception and your local courier first, then contact the online seller promptly. See [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## AnserX customer service and contacts

Official contacts published on [AnserX Contact Us](https://www.anserx.com/contact-us.html) and [anserx.com](https://www.anserx.com/):

- **24-hour customer service hotlines (China):** `0755-2891 8474` and `0755-2868-5256` (online customer service `09:00–24:00` China time).
- **Customer service email:** `bantouyankf@anserx.com`.
- **Headquarters:** Shenzhen AnserX International Logistics Technology Co., Ltd., Room 804, Block C, Building 3, Tian'an Cloud Park, Xuegang North Road, Bantian Street, Longgang District, Shenzhen, China.

> Watch out for unsolicited SMS or WhatsApp messages asking for an online card fee to "clear customs" or "fix your address" on a parcel from China. Verify any legitimate import fee through your marketplace order page or local postal operator first. See [delivery scam texts](guide:delivery-scam-texts).

## Track an AnserX parcel with Peek

[Peek](/) automatically recognises `BTYCN` + 10 digits + `YQ` (`BTYCN4829173640YQ`, digits invented) as AnserX with high confidence, and also recognises pasted `oms.anserx.com/tracking?nos=` links. If you ever need to choose AnserX manually, tap "Change" while adding or "Change carrier" on the parcel screen (searching "斑头雁" or "AnserX International Logistics" finds it too).

Peek reads AnserX's OMS tracker directly and shows the stage ("Label created", "Received by carrier", "In transit", "Out for delivery", "Delivered"), the English scan wording (the Chinese when a scan has none), the city or hub without its postcode, and the destination country. The `BTY_` codes set the stage; no Peek app shows them. AnserX writes its own scans on Beijing time; the local carrier's relayed scans are dated on their place's time zone (Los Angeles's for a scan in Los Angeles, Mexico City's for one in Puebla), and Peek shows them all on your device's clock. When AnserX gives the delivery carrier's number and Peek recognises that carrier, Peek checks that it has the parcel: the parcel then shows "Delivery with" and the carrier's name, and lists both numbers. A USPS barcode loses its routing ZIP prefix. Peek leaves out the customer account code and order reference; AnserX's tracker gives no estimated delivery date, weight, service name, delivery time of its own or proof-of-delivery photo.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day AnserX shows `BTY_LASTMILE_DELIVERING`, every 2 minutes from 6 a.m. on the clock of the newest scan's place, while AnserX's own tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When AnserX's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking AnserX.", with a "Get help from AnserX" link to AnserX's contact page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking AnserX."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot clear customs or contact AnserX or your local courier for you.

## Questions about AnserX tracking

### What is AnserX (*斑头雁*)?

AnserX (*Shenzhen AnserX International Logistics Technology Co., Ltd.*, founded in 2018 in Shenzhen) is a Chinese cross-border e-commerce logistics provider operating dedicated small-parcel air lines, FBA shipping and overseas warehouses to the US, Europe, Canada, Australia, Japan, Mexico and more.

### What does a `BTYCN...YQ` tracking number mean?

`BTYCN` + 10 digits + `YQ` (for example `BTYCN4829173640YQ`, digits invented) is AnserX's 17-character cross-border parcel waybill.

### Why does AnserX say `Relased from export customs` after arriving in my country?

That is a translation quirk in AnserX's English status feed for code `BTY_EXCC_COMPLETED` — the matching Chinese scan text (`目的地进口清关完成`) confirms destination import customs clearance is finished and the parcel is moving to your local courier.

### How do I contact AnserX customer service?

Ask your online store or marketplace first for order issues and refunds. For carrier inquiries, AnserX lists `bantouyankf@anserx.com` and 24-hour Shenzhen hotlines `0755-2891 8474` and `0755-2868-5256` on [anserx.com/contact-us.html](https://www.anserx.com/contact-us.html).

:::sources
- [AnserX: Official Website](https://www.anserx.com/) – dedicated small-parcel lines, FBA 0–30 kg / 1.2 m limits, overseas warehouses and Shenzhen headquarters
- [AnserX: About Us](https://www.anserx.com/about.html) – founded 2018, global routes (US, Europe, Japan, Southeast Asia, Russia, Australia) and smart routing platform
- [AnserX: Contact Us](https://www.anserx.com/contact-us.html) – `bantouyankf@anserx.com`, `0755-2891 8474`, `0755-2868-5256`, online service `09:00–24:00` and Shenzhen address (help URL linked by Peek)
- [AnserX: OMS Tracking Portal](https://oms.anserx.com/tracking) – public tracking lookup
- [Universal Parcel Scraper: anserx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/anserx) – `BTYCN...YQ` detection, `oms.anserx.com/tracking?nos=` link rule, `BTY_*` scan codes, Beijing time for its own scans and each place's time zone for relayed ones, local `deliveryNo` extraction and help URL
:::
