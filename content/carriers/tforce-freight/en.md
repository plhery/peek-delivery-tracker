---
title: TForce Freight tracking: PRO numbers, statuses and claims
description: Track a TForce Freight shipment in the US or Canada: what a PRO number looks like, what each tracking line means, home deliveries and freight claims.
slug: tforce-freight-tracking
published: 2026-11-03
updated: 2026-11-10
---

TForce Freight is a less-than-truckload (LTL) carrier: it moves pallets and heavy freight between its service centers across the United States and all of Canada, and to more than 600 points in Mexico. If a large order or other freight is coming to you with TForce, track it by its PRO number, which comes from the shop or the business that booked the freight: paste it in the box above. TForce's own tracker is the Tracking page on www.tforcefreight.com.

## TForce Freight tracking number: the PRO number

TForce's tracking page looks freight up by PRO number, or by bill of lading (BOL) or purchase order (PO) number with a range of shipping dates. Peek tracks by PRO. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `000000000` | The PRO: nine digits. The last is a check digit, what's left when the first eight are divided by 7, so it's always 0 to 6. |
| `00000000` | A PRO without its leading 0. TForce's tracker adds the 0, and so does Peek once you pick TForce Freight. |
| `2205000000000` | A PARS number, used by customs brokers for freight into Canada: 2205, then the PRO. Paste it whole: Peek asks TForce by the PRO in it. |

A nine-digit PRO ending in 7, 8 or 9 has a typo. Peek's catalog also knows TForce Freight as UPS Freight and TForce.

**Where to find it.** In the shop's shipping email or on your order page; for freight between businesses, ask the sender. See [how to find your tracking number](guide:find-tracking-number).

**Same shape, other carriers.** Nine digits with the same check digit also fit [TNT](carrier:tnt) and XPO. TForce Logistics (TForce Final Mile), whose numbers start with `TF`, is a separate carrier in Peek. If your email names one of them, pick that carrier. More in [tracking number formats](guide:tracking-number-formats).

## What TForce Freight's tracking statuses mean

TForce's tracker shows a status beside the PRO, such as `In Transit` or `Delivered`, then the history, newest first, each line with the service center's town and state, a date and a time. The lines carry no code: Peek reads their wording. In the order a shipment meets them:

| TForce Freight says | What it means for you | Peek shows |
| --- | --- | --- |
| `SHIPMENT HAS BEEN PICKED-UP.` | The driver has collected the freight from the shipper. | "Received by carrier" |
| `SHIPMENT IS AT A TFORCE FREIGHT LOCATION` | It's at the service center on the shipper's side. | "In transit" |
| `DEPARTURE` | It has left a service center. | "In transit" |
| `SHIPMENT HAS ARRIVED AT A SERVICE CENTER.` | It has reached a service center on the way, or the one that delivers to you. | "In transit" |
| `SENT EMAIL. TFORCE FREIGHT IS ATTEMPTING TO OBTAIN APPOINTMENT INFORMATION` | TForce has emailed the consignee, whoever receives the freight, to set a delivery appointment. Answer it. | "In transit" |
| `A DELIVERY APPOINTMENT HAS BEEN SCHEDULED WITH THE CONSIGNEE.` | A delivery day is agreed. This line is sometimes recorded after the delivery. | "In transit" |
| `OUT FOR DELIVERY` | It's on the truck for delivery. | "Out for delivery" |
| `Shipment not delivered. Delivery attempted after normal business hours. Tforce will attempt delivery on next business day we service this area.` | Not delivered on this run. Any line starting `Shipment not delivered.` reads this way, with the reason after it. | "Delivery attempted" |
| `SHIPMENT HAS BEEN DELIVERED TO THE CONSIGNEE.` | Delivered. TForce's page shows who signed. | "Delivered" |

A `Delivered` status beside the PRO always shows as "Delivered"; otherwise the newest line Peek can read decides, and other lines show as TForce writes them. TForce's tracking page can also email you updates. More in [tracking statuses explained](guide:tracking-statuses).

## How TForce Freight delivers

TForce carries the freight to the service center that serves you, then delivers it by truck. Its tracker gives an estimated delivery day, which Peek shows until the freight is out for delivery or an attempt fails. Peek's code hands the shipment over to no other carrier.

TForce's rules tariff (LTL Rules Tariff UPGF 102-T, effective 17 August 2026) sets out how delivery works:

- **Homes.** Where its trucks may legally go, TForce must first agree the date and approximate time with you or the shipper. A business run from a home counts as a home. It delivers to the ground-floor entrance, garage entrance or carport, or you collect the freight at its terminal. Home delivery is an extra service with its own charge, billed to whoever ordered it or pays the freight.
- **At the truck.** Delivery means unloading next to the truck. Inside delivery and a lift gate cost extra: ask whoever booked the freight whether they're included.
- **Arrival notice.** When the shipping papers ask for one, TForce notifies the consignee by the next business day after the freight reaches the delivering service center, normally by phone or email.
- **Not delivered.** If the freight is refused or the consignee can't be found, TForce tells the shipper it's in storage, and storage charges can apply. A new attempt is charged unless you collect the freight at the service center; at a home, only if you missed an agreed date.

**Into Canada.** As TForce's Canada page explains, freight from the US clears customs at the border through a customs broker. Freight that fails to clear there waits in a customs-bonded warehouse at destination, and storage charges apply. The importer of record pays the broker's fee, duties and taxes.

## TForce Freight shipment not found, late, damaged or lost

**Not found.** For a PRO it doesn't know, TForce's tracker shows `Not Available` and `Please check ProNumber` with the number. Peek then shows "Waiting for the carrier" if you filed the shipment under TForce Freight, "No tracking history yet" if not, and asks again later. Check each digit, and ask the shipper whether TForce has the freight yet.

**A digit is wrong.** A PRO whose last digit fails the check isn't TForce's, and Peek never asks TForce about it. If you pick TForce Freight anyway, Peek says it "doesn’t issue numbers like this one", and the shipment shows "Check the tracking number".

**Not updating.** After four days without a new line, a shipment showing "Received by carrier" or "In transit" gets a flag ("No tracking update for four days"). Ask the shop or the shipper. See [tracking that isn't updating](guide:tracking-not-updating).

**Missed delivery.** After a `Shipment not delivered.` line, Peek flags the shipment ("Check the carrier’s next steps"). Call TForce or ask whoever booked the freight to set a new delivery. More in [missed deliveries](guide:missed-delivery).

**Short or damaged at delivery.** TForce's Cargo Claims Information page says to check the freight every time and tell the driver at once:

- Count the pieces against the delivery receipt, and open damaged boxes with the driver.
- Note exactly what's missing or damaged on both your copy and TForce's copy of the delivery receipt.
- Don't refuse the shipment unless the damage has made the goods worthless.
- Ask the Over, Short & Damage (OS&D) person at your local service center to trace missing pieces.

**Damage found later.** Report it within 5 business days of delivery and ask for an inspection, leaving the packing as you found it. An inspection report isn't a claim.

**Filing a claim.** A claim is a written demand for payment by the shipment's owner. For an online order, start with the shop, which holds the booking. TForce's rules:

- File it within nine months of delivery, or of the shipping date if nothing arrived.
- Use TForce's Standard Claim Form or a letter naming the shipment and the amount, with the vendor invoice, freight bill, bill of lading and any repair invoices. Mail or fax it (below), not both.
- TForce acknowledges claims within 30 days. Its liability is limited to $25.00 per pound per package, less for some goods, unless the shipper bought extra coverage on the bill of lading.

If tracking says delivered and nothing came, see [delivered but not received](guide:delivered-not-received). More in [damaged parcels](guide:damaged-parcel) and [lost parcels](guide:lost-parcel).

## TForce Freight customer service and contact

As read on 1 November 2026:

- **Customer Service:** (800) 333-7400, toll-free from the US, Canada, Guam, Puerto Rico and the U.S. Virgin Islands, for tracking, proof of delivery and problems. From elsewhere: (804) 231-8555.
- **Email:** the Email TForce Freight Customer Support form, linked from TForce's Contact Us page; it has a field for the PRO.
- **Claims Department:** (888) 653-9720, for a claim's status. Claims go to Claims Processing, TForce Freight, PO Box 1216, Richmond, VA 23218, or by fax to (866) 580-1944.
- **Your service center:** the Service Center Directory, under Service Information on www.tforcefreight.com.

The shop or the sending business is often quicker: they hold the booking with TForce.

TForce's pages quoted here, all on www.tforcefreight.com and read on 1 November 2026: Tracking; Fact Sheet; Rules Tariffs, with the LTL Rules Tariff UPGF 102-T; Canada; Cargo Claims Information; Contact Us, with Email TForce Freight Customer Support; Service Information.

## Track a TForce Freight shipment with Peek

A PRO's shape doesn't tell Peek it's TForce's: nine digits fit TNT and XPO too. Paste it all the same: Peek follows more than 3,500 carriers. While "Checking tracking services…" shows, Peek asks all three, and tracking services if none knows it; if TForce alone has the freight, its name shows with "has this parcel". For a PARS number, TForce comes first among the carriers Peek asks; for an eight-digit PRO, Peek asks only tracking services. Or press "Change" and pick TForce Freight under "Fits this number", or use "Change carrier" later. A link to TForce's tracker (`proNumbers=`) or the UPS Freight tracker (`TrackProNumber=`) names TForce at once ("Detected carrier").

Peek asks TForce's tracker by the PRO alone. It shows the status, each line with its town and state, the estimated delivery day, the destination country on the shipment's map and the weight in kilograms; it leaves out names, the appointment, BOL and PO numbers and the piece count, and gets no proof of delivery. "Open the TForce Freight website" opens your number on TForce's tracker, or for a PARS number the PRO in it. If that tracker can't be reached or asks for a CAPTCHA, Peek asks [tracking services](guide:universal-tracker).

TForce gives each line its service center's local time, with no time zone. Peek reads it on the clock of the line's town, else of a line next to it or of where the freight is going, else on Eastern time, and shows it in your device's time zone.

Peek checks every 10 minutes, and every 2 minutes from 6 a.m. local time on the day TForce scans the freight out for delivery, while TForce's own tracker answers (through tracking services, every 15 minutes). After two days without news (12 hours while the shipment still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". It can't contact TForce, the shop or the shipper for you, or file a claim.

## Questions about TForce Freight tracking

### What is a TForce Freight PRO number?

TForce's tracking number: nine digits, the last a check digit from 0 to 6. The shop or the business that booked the freight gives it to you.

### Can I track TForce Freight without a PRO number?

On TForce's tracking page, yes: by bill of lading or purchase order number, with a range of shipping dates. Peek tracks by PRO only.

### Does TForce Freight deliver to homes?

Yes, where its trucks may legally go, at an extra charge. TForce first agrees the date and approximate time with you or the shipper, then delivers to the ground-floor entrance, garage entrance or carport. Inside delivery and a lift gate cost extra too.

### How long do I have to file a claim with TForce Freight?

Nine months from delivery, or from the shipping date if nothing arrived. Report damage found after delivery within 5 business days.

### What is TForce Freight's customer service number?

(800) 333-7400, toll-free from the US and Canada. From elsewhere, (804) 231-8555.

:::sources
- [Universal Parcel Scraper: TForce Freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tforce-freight) – names, PRO shapes, links, wordings and stages, not-found answer, what Peek keeps, clocks
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – carriers sharing the shape, no hand-off, tracking services as a fallback
:::
