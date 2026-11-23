---
title: Shipter tracking — H3 Korean export numbers & statuses
description: Track Shipter (H3 Networks) international air parcels from South Korea. Check H3...KR tracking numbers, destination handoffs, statuses, and tracking links.
slug: shipter-tracking
published: 2026-11-23
updated: 2026-11-23
---

Shipter, run by **H3 NETWORKS Co., Ltd.** in South Korea, ships B2B and B2C parcels from Korea to more than **100 countries** through 40+ logistics channels, on an air network that links more than 25 airports every week ([shipter.kr](https://www.shipter.kr/)). Its B2B service covers transport, customs and warehousing; its B2C service covers overseas delivery, customs and the last mile. Amazon Korea chose it as an official logistics partner in its SPN (Service Provider Network), it is registered as a carrier with eBay, Kogan and Allegro, its tracking is linked to AfterShip and 17TRACK, and it works as an influencer seeding partner.

You can check a parcel on [shipter.kr/tracking](https://www.shipter.kr/tracking) or paste your `H3...KR` tracking number into the box above.

## Shipter tracking number format

Shipter's tracking numbers start with `H3` and end with `KR`, with two letters and five or six digits between:

| Format (invented) | What it is |
| --- | --- |
| `H3AU482917KR` | 12 characters: `H3`, two letters, six digits and `KR`. Peek asks [Colis Privé](carrier:colis-prive) and Shipter, Shipter first in South Korea. |
| `H3FR48291KR` | 11 characters: `H3`, two letters, five digits and `KR`. Peek asks Shipter alone. |

A tracking link from `shipter.kr/tracking/<number>` or `shiptertracking.com/trackingResult/<number>` files the parcel under Shipter straight away. For parcels Korea Post carries out of South Korea, see [Korea Post](carrier:korea-post); for other shapes, the [tracking number formats guide](guide:tracking-number-formats).

## What Shipter tracking statuses mean

Shipter's tracking timeline combines its own South Korean export and international air-freight milestones with relayed scans from the local last-mile courier in your destination country, in English.

| Shipter / partner scan wording | What it means |
| --- | --- |
| `The shipment information has been received by shipter, but the package has not been picked up yet.` | Pre-advice received from the Korean seller; Shipter has not scanned the physical box yet. |
| `The parcel has been scanned by shipter and ready to be shipped.` | Shipter received and scanned the parcel at its Korean facility. |
| `The parcel has been shipped.` / `The flight has been departed.` / `Depart origin airport` | Dispatched from South Korea on an international cargo flight. |
| `Arrived at destination airport` / `Collected from airport terminal` | Landed in the destination country and collected from the airport cargo terminal. |
| `Import customs cleared` | Destination customs released the parcel to move on to local delivery. |
| `Arrived at destination` / `In transit` | Handed into the destination sorting flow ahead of local courier scans. |
| `Parcel received in processing centre (...)` / `Parcel depart from ... depot` / `The item is ready for shipment...` | Processed and dispatched inside the local destination courier network. |
| `Your parcel has been received at ... depot..` / `Your parcel is currently in transit to ...` | Relayed from the last-mile partner in the destination country, with its depot and town. |
| `Out for delivery` / `Your parcel is with your local courier and ready for delivery..` | Out with the local delivery driver today. |
| `Your parcel was delivered` / `Delivered.` | Delivered to the recipient's address. |

Two notices relayed from the last-mile partner, `Your parcel will be entrusted to last mile .it is being prepared by your sender` and `Shipping information received`, come after Shipter's acceptance scan. They don't mean the parcel went backwards.

## International air transit, customs, and local handoffs

Shipter flies parcels out of South Korea and hands them to a local courier in the destination country:

- **Flight and airport:** After `Depart origin airport`, Shipter shows `Arrived at destination airport` when the flight lands, `Collected from airport terminal` once the parcel leaves the cargo terminal, and `Import customs cleared` once the destination's import clearance is done. See the [customs guide](guide:customs) and [when tracking stops updating](guide:tracking-not-updating).
- **Local last-mile delivery:** A courier in the destination country delivers the parcel, and Shipter relays its depot, `Out for delivery` and delivery scans into its own timeline.
- **No estimated delivery date:** Shipter's public tracking API does not publish an estimated delivery date; track delivery progress by watching for `Import customs cleared`, local depot receipt, and `Out for delivery`.

## Tracking Shipter with Peek

Paste your `H3...KR` number, or a `shipter.kr/tracking/...` or `shiptertracking.com/trackingResult/...` link, into Peek. For a 12-character number Peek asks [Colis Privé](carrier:colis-prive) and Shipter, Shipter first in South Korea; for an 11-character one, Shipter alone. It says "Shipter has this parcel." once Shipter answers. If Peek names another carrier, tap "Change" while adding the parcel, or "Change carrier" on its page, and pick Shipter.

Peek reads Shipter's tracking through the open-source [universal-parcel-scraper Shipter adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter), and tries again after a network error. It shows each scan's English wording with the depot or place it names, and the delivery time. Shipter gives its times with a GMT offset, and Peek shows them on your device's clock; a time without an offset is shown as Shipter gives it.

Peek maps Shipter's scans into clear stages: **Label created** for pre-advice before pickup, **Received by carrier** once Shipter scans the parcel in Korea, **In transit** for flight departure, airport arrival, customs release, and local courier hub transfers, **Out for delivery** when the local courier starts its round, and **Delivered** upon completion. Late last-mile pre-advice notices (`Shipping information received` and `Your parcel will be entrusted to last mile...`) stay in the timeline without reverting the shipment backwards.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Shipter." After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Shipter."

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day Shipter shows `Out for delivery` or `Your parcel is with your local courier and ready for delivery..`, every 2 minutes from 6 a.m. on the newest scan's clock, while Shipter's tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When Shipter's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day. It can notify you of "Every scan", "Important steps" or "Delivery only".

## Contacting Shipter

Shipter works for the companies that ship with it:

- **Official website & service inquiry:** [shipter.kr](https://www.shipter.kr/) (H3 NETWORKS Co., Ltd.; *이용문의* for service inquiries)
- **Official online tracker:** [shipter.kr/tracking](https://www.shipter.kr/tracking)
- **Contact the seller first:** each client company has a dedicated CS manager at Shipter, which handles issues, returns and claims with its clients. If your parcel is delayed, damaged or missing, contact the store that sent it with your `H3...KR` number.

## Frequently asked questions

### What does a Shipter tracking number look like?

It starts with `H3` and ends with `KR`, with two letters and five or six digits between: 11 or 12 characters, such as `H3AU482917KR`. Shipter's site doesn't say what the letters stand for.

### Why did "Shipping information received" appear days after my parcel already shipped?

It is a notice from the destination country's last-mile partner, which Shipter relays after its own acceptance scan. It doesn't move the parcel back: Peek keeps the stage of the scan before it.

### Why does my Shipter parcel show updates from another courier?

Shipter handles export from South Korea and international air freight, then transfers the parcel to a local last-mile courier in the destination country and displays that partner's depot and delivery scans inside your Shipter timeline.

### Does Shipter provide an estimated delivery date?

No. Shipter's public tracking feed shows timestamped export, flight, customs, and local courier events with GMT offsets, without an estimated delivery date.

:::sources
- [Shipter (H3 NETWORKS Co., Ltd.)](https://www.shipter.kr/)
- [Shipter Tracking](https://www.shipter.kr/tracking)
- [universal-parcel-scraper Shipter adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter)
:::
