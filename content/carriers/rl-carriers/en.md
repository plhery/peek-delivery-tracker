---
title: R+L Carriers tracking: PRO numbers, statuses and claims
description: Track an R+L Carriers freight shipment in the US: what a PRO number looks like, what each tracing line means and what to do if freight is late or damaged.
slug: rl-carriers-tracking
published: 2026-11-07
updated: 2026-11-07
---

R+L Carriers is a freight carrier in the United States. In Peek's code, it picks the freight up from the shipper, moves it from terminal to terminal and delivers it from the destination terminal. If a large order or other freight is coming to you with R+L, you track it by its PRO number, which comes from the shop or the business that booked the freight: paste it in the box above. R+L's own tracer is the shipment tracing page on rlcarriers.com.

## R+L Carriers tracking number: the PRO number

R+L's tracer looks freight up by its PRO number. Peek knows these shapes for it. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `00000000-0` | Nine digits, the last one a check digit, here written after a dash. |
| `000000000` | The same PRO without the dash. |
| `AA000000-0` | Two letters and seven digits, the last one a check digit. It can be written without the dash too. |
| `I000000000` | A PRO as R+L's tracer writes it, with an `I` in front. The `I` isn't part of the number: Peek takes the PRO with or without it. |

R+L's tracer checks the last digit, so a mistyped digit can be caught (see below). Peek drops spaces and dashes before it asks R+L. Its catalog also knows R+L Carriers as R&L Carriers, R and L Carriers and RL Carriers.

**Where to find it.** In the shop's shipping email or on your order page; for freight between businesses, ask the business that sent it. See [how to find your tracking number](guide:find-tracking-number).

**Other carriers with the same shape.** Nine digits alone also fit [TNT](carrier:tnt), TForce Freight and XPO, and an `I` followed by nine digits fits [Australia Post](carrier:australia-post). If your email names one of them, track it there. More in [tracking number formats](guide:tracking-number-formats).

## What R+L Carriers' tracking statuses mean

R+L's tracer sums the shipment up above its history, then lists each line with a date and a time. It names terminals by town and state. Its lines carry no code: Peek reads their wording. In the order a shipment meets them, with the dots standing for a town and state, or a terminal:

| R+L says | What it means for you | Peek shows |
| --- | --- | --- |
| `Picked Up in …` | R+L has collected the freight from the shipper, in the shipper's town. Peek writes it `Picked Up`, without the town. | "Received by carrier" |
| `Arrived at origin terminal` | It has reached the R+L terminal on the shipper's side. | "In transit" |
| `Departed … in route to …` | It has left one terminal for the next one, or for the destination terminal. | "In transit" |
| `Arrived at …` | It has reached a terminal on the way, named by its town. | "In transit" |
| `Arrived at destination terminal` | It's at the terminal on your side, the last stop before delivery. | "In transit" |
| `Shipment Delayed` | Something has held it up. R+L's summary then calls the shipment delayed and keeps an expected delivery day. | "In transit" |
| `Delivered` | Delivered. | "Delivered" |

**The summary.** Above the lines, R+L's tracer calls the shipment in transit, delayed or delivered. When that summary is further along than the lines, Peek goes by the summary: a shipment summed up as delivered shows "Delivered" even if the newest line doesn't say so. When the tracer has a summary but no lines yet, Peek shows the status alone.

Peek's code knows no R+L wording for out for delivery, a missed delivery, a problem or a return. Other lines go by the rules Peek uses for every carrier. A line none of them reads still shows as written and counts as news that the freight is moving ("In transit"); after a delivery, it changes nothing. More in [tracking statuses explained](guide:tracking-statuses).

## How R+L Carriers delivers

While the freight moves, R+L's tracer gives an expected delivery day, and Peek shows it until the shipment is delivered. R+L's tracer names no other carrier for the delivery, and Peek's code hands the shipment over to none.

Peek couldn't read R+L Carriers' website, so this page gives no delivery times, delivery appointments, attempts or rules for collecting freight at a terminal. Ask the shop or the business that booked the freight how and when it comes to your door: they hold the booking with R+L.

## R+L Carriers shipment not found, late, damaged or lost

**Not found.** For a PRO it doesn't know, R+L's tracer answers `No records found for Number:` followed by the PRO with an `I` in front. Peek then shows "Waiting for the carrier" and asks again later. Check each digit against the shop's email. If the number is right, ask the shipper whether R+L has picked the freight up.

**A digit is wrong.** For a PRO whose check digit doesn't add up, R+L's tracer answers with the PRO followed by `Pro number failed check digit validation.` Peek then shows "Check the tracking number". Compare the number, digit by digit, with the shop's email or the shipping papers.

**Delayed or not updating.** After `Shipment Delayed`, Peek keeps the shipment "In transit" with R+L's expected day. After four days without a new line, a shipment showing "Received by carrier" or "In transit" gets a flag ("No tracking update for four days"). Ask the shop or the shipper. See [tracking that isn't updating](guide:tracking-not-updating).

**Damaged, short or lost.** Tell the shop or the sender first: they booked the freight. For freight a trucking company carries between US states, federal law sets these rules:

- The carrier is liable for the actual loss or injury to the freight, to the person the receipt or bill of lading entitles to recover. Its liability can be limited to a value the shipper declared, or agreed in writing with the carrier.
- A carrier can't give less than 9 months to file a claim, or less than 2 years to go to court, counted from the day it says in writing that it turns down any part of the claim. The bill of lading or contract sets the actual time limit.
- A claim is a written request, filed with the carrier within those limits, that identifies the shipment, holds the carrier liable and asks for a specified or determinable amount of money. A note of damage or shortage on the delivery receipt doesn't count as a claim on its own.
- When the carrier needs them, the claim comes with the bill of lading, proof of the freight charges and the invoice. For a whole package or shipment that never came, if the carrier can't confirm the loss otherwise, it gets a certified written statement from the consignee, the person it was sent to, that the freight hasn't arrived from any other source.
- The carrier acknowledges a claim in writing within 30 days. It pays, declines or makes a firm offer in writing within 120 days; if it can't, it writes with the claim's status then and every 60 days after.

If tracking says `Delivered` and nothing came, see [delivered but not received](guide:delivered-not-received). More in [damaged parcels](guide:damaged-parcel) and [lost parcels](guide:lost-parcel).

## R+L Carriers customer service and contact

As of 1 November 2026, Peek couldn't read R+L Carriers' website, so this page lists no phone number or email: Peek gives only contacts read on a carrier's own pages.

R+L carries the freight for whoever booked it, so ask them first: the shop for an online order, the sending business for freight between businesses. They hold the booking and can take it up with R+L.

## Track an R+L Carriers shipment with Peek

A PRO's shape doesn't tell Peek it's R+L's: nine digits fit other carriers too. Paste it all the same: Peek follows more than 3,500 carriers. With the dash before its last digit, Peek asks R+L at once and shows "R+L Carriers has this parcel." when R+L knows the PRO; R+L Carriers is also listed under "Fits this number". Without the dash, press "Change" and search for R+L Carriers by name. On a shipment you've already added, use "Change carrier". Left to itself, Peek doesn't ask R+L about a PRO without the dash at first: for a nine-digit PRO it asks TNT, TForce Freight, XPO and tracking services, and for one with two letters, tracking services only. If a tracking service then shows the freight moving and names R+L Carriers, Peek files the shipment under R+L and asks R+L's tracer on later checks.

With the `I` in front, the number fits R+L Carriers and Australia Post: Peek asks both and files the shipment under the one that knows it. A link to R+L's shipment tracing page with the PRO in it (`pro=`) works too: Peek names R+L at once ("Detected carrier").

Peek asks R+L's tracer by the PRO alone: no account or postcode. It shows the status, each line (with the terminal's town and state when the line names them), the expected delivery day and the weight in kilograms (R+L gives pounds). It leaves out the piece count, references, the shipper and consignee, the bill of lading number and documents. R+L's tracer gives Peek no proof of delivery. "Open the R+L Carriers website", on the shipment's page, opens the PRO on R+L's tracer.

When R+L's tracer can't be reached or can't read the shipment, Peek asks [tracking services](guide:universal-tracker) and shows their history, in their own words.

R+L's lines have a date and a time but no time zone. Peek reads each one on the clock of its terminal's town, else of the line before or after it, else on Eastern time, and shows every time in your device's time zone.

Peek checks every 10 minutes; a shipment with no new line for two days (12 hours while it still shows "Label created" or "Received by carrier") is checked hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". Peek can't contact R+L, the shop or the shipper for you, book a delivery or file a claim.

## Questions about R+L Carriers tracking

### What is a PRO number on R+L Carriers?

R+L's tracking number: nine digits, or two letters and seven digits, the last digit a check digit, sometimes written after a dash. R+L's tracer writes it with an `I` in front. It comes from the shop or the business that booked the freight.

### Why does R+L Carriers say "No records found for Number"?

R+L's tracer doesn't know the PRO. Check each digit against the shop's email, check that the email names R+L, and ask the shipper whether R+L has picked the freight up.

### What does "Shipment Delayed" mean on R+L Carriers?

Something has held the freight up on its way. The line gives no reason; R+L's summary then calls the shipment delayed and keeps an expected delivery day, and Peek keeps it "In transit". Ask the shop or the shipper why.

### How long do I have to file a freight claim with R+L Carriers?

For freight carried between US states, federal law gives you at least 9 months to file a claim with the trucking company, and at least 2 years to go to court after it turns down any part of the claim in writing. The bill of lading or contract sets the actual limit; Peek couldn't read R+L's own rules. Start with whoever booked the freight.

:::sources
- [Universal Parcel Scraper: R+L Carriers](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/rl-carriers) – names, PRO shapes and the `I` in front, tracing links, the tracer's wordings and their stages, the summary, not-found and check-digit answers, expected delivery day, weight, clocks, what Peek leaves out
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – carriers sharing the shapes, no hand-off, tracking services as a fallback
- [GovInfo: 49 U.S.C. 14706, Liability of carriers under receipts and bills of lading](https://www.govinfo.gov/content/pkg/USCODE-2024-title49/html/USCODE-2024-title49-subtitleIV-partB-chap147-sec14706.htm) – liability to the person entitled under the bill of lading, limited value, 9 months to claim and 2 years to sue
- [GovInfo: 49 CFR Part 370, Investigation and voluntary disposition of loss and damage claims and processing salvage](https://www.govinfo.gov/content/pkg/CFR-2025-title49-vol5/xml/CFR-2025-title49-vol5-part370.xml) – what a claim contains, notes on delivery receipts, supporting documents, 30, 120 and 60 days
:::
