---
title: Shipter tracking — H3 Korean export numbers & statuses
description: Track Shipter (H3 Networks) international air parcels from South Korea. Check H3...KR tracking numbers, destination handoffs, statuses, and tracking links.
slug: shipter-tracking
published: 2026-11-23
updated: 2026-11-23
---

Shipter (*쉽터*, operated by **H3 NETWORKS Co., Ltd.** in South Korea) is a cross-border e-commerce air-logistics carrier connecting Korean brands, Amazon Korea SPN sellers, eBay/Kogan/Allegro exporters, and influencer seeding campaigns with shoppers in over **100 countries** ([shipter.kr](https://www.shipter.kr/)). Linking more than 25 international airports each week across 40+ global logistics channels, Shipter manages Korean warehouse intake, air linehaul, export/import customs clearance, and handoff to local last-mile couriers worldwide.

You can check a parcel on [shipter.kr/tracking](https://www.shipter.kr/tracking) or paste your `H3...KR` tracking number into the box above.

## Shipter tracking number format

Shipter issues a dedicated `H3...KR` international shipment number combining its company prefix (`H3`), a 2-letter destination country code, a 5- or 6-digit serial number, and `KR` for South Korea:

| Format (invented) | What it is |
| --- | --- |
| `H3AU482917KR` | `H3` + 2-letter destination (`AU`) + 6 digits + `KR` (12 characters total) — shares 12-character length with [Colis Privé](carrier:colis-prive), so Peek verifies it against Shipter. |
| `H3FR48291KR` | `H3` + 2-letter destination (`FR`) + 5 digits + `KR` (11 characters total) — unique candidate for Shipter. |

Notice how the third and fourth letters show the destination country (`AU` for Australia, `FR` for France, and so on). Pasting a direct tracking link from `shipter.kr/tracking/<number>` or `shiptertracking.com/trackingResult/<number>` routes your shipment straight to Shipter immediately. For other South Korean outbound parcels handled by the national post, see [Korea Post](carrier:korea-post) and the [tracking number formats guide](guide:tracking-number-formats).

## What Shipter tracking statuses mean

Shipter's tracking timeline combines its own South Korean export and international air-freight milestones with relayed scans from the local last-mile courier in your destination country — all reported in English with explicit GMT timezone offsets.

| Shipter / partner scan wording | What it means |
| --- | --- |
| `The shipment information has been received by shipter, but the package has not been picked up yet.` | Pre-advice received from the Korean seller; Shipter has not scanned the physical box yet. |
| `The parcel has been scanned by shipter and ready to be shipped.` | Shipter received and scanned the parcel at its Korean facility. |
| `The parcel has been shipped.` / `The flight has been departed.` / `Depart origin airport` | Dispatched from South Korea on an international cargo flight. |
| `Arrived at destination airport` / `Collected from airport terminal` | Landed in the destination country and collected from the airport cargo terminal. |
| `Import customs cleared` | Destination customs released the parcel to move on to local delivery. |
| `Arrived at destination` / `In transit` | Handed into the destination sorting flow ahead of local courier scans. |
| `Parcel received in processing centre (...)` / `Parcel depart from ... depot` / `The item is ready for shipment...` | Processed and dispatched inside the local destination courier network. |
| `Your parcel has been received at ... depot..` / `Your parcel is currently in transit to ...` | Relayed updates from local partners (such as [Aramex Australia](carrier:aramex-au) on Australian routes). |
| `Out for delivery` / `Your parcel is with your local courier and ready for delivery..` | Out with the local delivery driver today. |
| `Your parcel was delivered` / `Delivered.` | Delivered to the recipient's address. |

Two relayed pre-advice messages deserve attention: `Your parcel will be entrusted to last mile .it is being prepared by your sender` and `Shipping information received` often appear *after* Shipter already accepted and flew the parcel — they merely reflect the local last-mile partner receiving electronic pre-advice and do not mean the parcel went backwards.

## International air transit, customs, and local handoffs

Because Shipter handles K-beauty, Korean fashion, and cross-border marketplace shipments from South Korea to more than 100 countries:

- **Air transit and airport terminal intake:** After `Depart origin airport`, expect a quiet stretch of a few days until `Arrived at destination airport` and `Collected from airport terminal` post. Airport terminal collection and import clearance (`Import customs cleared`) typically take a day or more depending on destination airport queues. See the [customs guide](guide:customs) and [when tracking stops updating](guide:tracking-not-updating).
- **Local last-mile delivery:** Once cleared, a domestic carrier in your country performs the final delivery (for example, Aramex on some Australian shipments) and feeds its depot and `Out for delivery` scans back into Shipter's timeline.
- **No estimated delivery date:** Shipter's public tracking API does not publish an estimated delivery date; track delivery progress by watching for `Import customs cleared`, local depot receipt, and `Out for delivery`.

## Tracking Shipter with Peek

Paste your `H3...KR` tracking number — or a `shipter.kr/tracking/...` or `shiptertracking.com/trackingResult/...` link — into Peek. Because 12-character `H3` codes share their length with [Colis Privé](carrier:colis-prive), Peek confirms which carrier holds the parcel automatically, or you can pick Shipter directly using **Change** when adding the parcel or **Change carrier** on the tracking screen.

Peek queries Shipter's tracking endpoint directly through the open-source [universal-parcel-scraper Shipter adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter), with an automatic retry on network timeouts. It reads every English milestone, depot location, destination country code, delivery timestamp (`delivered_at`), and explicit GMT timezone offset so every scan converts cleanly to your local time.

Peek maps Shipter's scans into clear stages: **Label created** for pre-advice before pickup, **Received by carrier** once Shipter scans the parcel in Korea, **In transit** for flight departure, airport arrival, customs release, and local courier hub transfers, **Out for delivery** when the local courier starts its round, and **Delivered** upon completion. Late last-mile pre-advice notices (`Shipping information received` and `Your parcel will be entrusted to last mile...`) stay in the timeline without reverting the shipment backwards.

When scans pause during international flights, destination customs, or weekends, Peek displays: "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Shipter." After more than three weeks without a new scan, Peek shows: "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Shipter."

In Peek's alert settings, pick **Every scan**, **Important steps**, or **Delivery only** to decide which updates trigger notifications.

## Contacting Shipter

Shipter operates primarily as a B2B/B2C fulfillment and export partner for Korean brands and online sellers rather than running a consumer call centre for overseas shoppers:

- **Official website & service inquiry:** [shipter.kr](https://www.shipter.kr/) (operated by H3 NETWORKS Co., Ltd.; *이용문의* inquiry portal for business accounts)
- **Official online tracker:** [shipter.kr/tracking](https://www.shipter.kr/tracking)
- **Contact the seller first:** Shipter assigns dedicated customer-success managers to each brand or exporter account to handle customs issues, returns, and claims. If your parcel is delayed, damaged, or missing after delivery, contact the Korean store or marketplace seller with your `H3...KR` number so their Shipter account manager can investigate directly.

## Frequently asked questions

### What do the letters inside a Shipter `H3...KR` tracking number mean?

`H3` stands for H3 NETWORKS Co., Ltd. (Shipter's operator), the next two letters are the destination country's ISO code (for example `AU` or `FR`), followed by a 5- or 6-digit serial number and `KR` for South Korea.

### Why did "Shipping information received" appear days after my parcel already shipped?

Shipter relays electronic pre-advice scans from the destination country's last-mile courier when it registers the parcel locally. Peek treats those relayed pre-advice notices as non-regressive so your parcel stays in transit.

### Why is my Shipter parcel showing updates from another courier like Aramex?

Shipter handles export from South Korea and international air freight, then transfers the parcel to a local last-mile courier in the destination country and displays that partner's depot and delivery scans inside your Shipter timeline.

### Does Shipter provide an estimated delivery date?

No. Shipter's public tracking feed shows timestamped export, flight, customs, and local courier events with GMT offsets, without an estimated delivery date.

:::sources
- [Shipter (H3 NETWORKS Co., Ltd.)](https://www.shipter.kr/)
- [Shipter Tracking](https://www.shipter.kr/tracking)
- [universal-parcel-scraper Shipter adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter)
:::
