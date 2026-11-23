---
title: Kuehne+Nagel tracking — numbers, statuses, and delivery
description: Track Kuehne+Nagel road, air, and sea shipments by reference or myKN link. Check number formats, milestone meanings, customs steps, and contact options.
slug: kuehne-nagel-tracking
published: 2026-11-23
updated: 2026-11-23
---

Kuehne+Nagel is one of the world's leading logistics providers, with close to 92,000 employees at almost 1,300 sites in close to 100 countries. It moves sea, air and road freight, and its [myKN public tracker](https://mykn.kuehne-nagel.com/public-tracking/) looks up shipments of every mode.

Public tracking on myKN covers shipments started within the last 90 days. Because Kuehne+Nagel moves freight across road stations, airports, container freight stations, rail ramps, and seaports, a single shipment can display both physical route milestones and separate booking, customs, or document notices.

## Kuehne+Nagel tracking number formats

Peek takes Kuehne+Nagel references of 6 to 35 letters and digits, with at least one digit, and removes spaces and dashes. myKN looks up Kuehne+Nagel's own tracking numbers, waybills and bookings in the same search as customer references. Peek recognises three shapes:

- **`RWM` followed by 9 digits** (12 characters total, such as `RWM482917360`) — a Kuehne+Nagel road shipment's tracking number, which Peek recognises as Kuehne+Nagel's alone.
- **`RAPI` followed by 8 digits** (12 characters total, such as `RAPI48291736`) — a Kuehne+Nagel tracking number, which Peek also recognises as Kuehne+Nagel's alone.
- **2 digits, `A`, and 9 digits** (12 characters total, such as `26A482917360`) — a road shipment's tracking number. The shape fits [Australia Post](carrier:australia-post) and [Colis Privé](carrier:colis-prive) too, so Peek asks Australia Post, Colis Privé and Kuehne+Nagel in turn (Kuehne+Nagel second in Switzerland and Germany).

For air and sea freight, shippers may also send a Kuehne+Nagel waybill, house bill of lading, or customer reference that falls outside those three patterns. If you paste a tracking link from `mykn.kuehne-nagel.com/public-tracking/shipments?query=...`, Peek extracts the reference and routes it directly to Kuehne+Nagel. For a broader look at how carrier numbers compare across freight and parcel networks, see the [tracking number formats guide](guide:tracking-number-formats).

## How Kuehne+Nagel milestones work

Kuehne+Nagel's public tracker separates a shipment into two feeds: physical stops along the route (origin, departure terminal, transit hub, gateway station, arrival terminal, and destination) and transport information notices (bookings, customs filings, and proof-of-delivery documents).

| Kuehne+Nagel wording | What it means |
| --- | --- |
| Booking submission / Booking confirmation | The order is registered in Kuehne+Nagel's system before cargo moves; a late booking edit never pushes a moving shipment backwards. |
| Ready for pickup / Arrival (at origin) | Cargo is prepared at the shipper or the collection truck has arrived, before loading is confirmed. |
| Pickup | Kuehne+Nagel has collected the freight from the shipper. |
| Arrival / Departure / Load on vehicle / Load on vessel | Physical terminal, cross-dock, rail-ramp, airport, or port scans along the linehaul leg. |
| Customs entry start / Customs duties payment / Entry documents required | Export or import customs clearance is underway, or authorities have requested paperwork, duties, or agency inspection (such as FDA or PGA checks). |
| Customs entry release / Export customs entry release / Import customs entry release | Customs or the relevant border agency has cleared the cargo to continue moving. |
| In delivery | The road vehicle is out on the final delivery leg, or has arrived at the delivery site. |
| Delivery / Freight collection / Proof of delivery available | Cargo has been delivered to the consignee or collected from the terminal; `Delivery with discrepancies` still marks delivery while noting a shortage or damage. |
| Delivery attempt failed / Delivery not possible | A delivery visit did not succeed, for example when the consignee refused it. |
| Shipment on hold / Delivery instructions pending / Missing/incomplete document | The consignment is paused at a station or customs point until missing paperwork, payment, or delivery instructions are supplied. |

A few milestone labels deserve a closer look:

- **Available at CY / Available at CFS / Ready for collection / Available at rail terminal** mean the shipment has arrived at an ocean container yard, container freight station, airport, or rail ramp and is ready for handover or onward release. Kuehne+Nagel or a partner may still perform the final road delivery, so these scans stay in transit rather than marking final delivery.
- **Freight handover** means Kuehne+Nagel handed the freight to a broker or onward carrier, not to the final recipient.
- **Document and filing notices** such as `HAWB available`, `MAWB available`, `Transport document available`, `VGM closure`, `Delivery appointment submission`, and `Customs billing invoice available` record administrative steps and do not change the physical stage of the shipment.
- **Empty container gate-in and vehicle departure at destination** occur after delivery is complete and remain at the delivered stage.

## Delivery, customs, and delays

`Delivery appointment submission` records a delivery date agreed for later. It isn't a scan: the shipment only switches to out for delivery when `In delivery` posts, or the truck's `Arrival` at the delivery address.

If tracking shows `Awaiting consignee's feedback`, `Awaiting consignee's payment` or `Entry documents required`, customs is waiting for the consignee's answer, payment or papers. `Unable to file customs entry - missing details or documentation` means the customs entry can't be filed until they arrive. See the [customs guide](guide:customs) for how import clearance and charges work.

Planned milestone dates shown on myKN are forecasts rather than achieved scans. For general advice on stalled freight and parcel tracking, read [when tracking stops updating](guide:tracking-not-updating).

## Tracking Kuehne+Nagel with Peek

When you paste a `RWM` or `RAPI` number or a `mykn.kuehne-nagel.com/public-tracking/shipments?query=...` link into Peek, Peek recognizes Kuehne+Nagel at once. For a `26A...` number, Peek asks the carriers above in turn and shows "Kuehne+Nagel has this parcel." once Kuehne+Nagel answers. For a waybill or customer reference without these shapes, pick Kuehne+Nagel using **Change** while adding the parcel or **Change carrier** on the tracking screen.

Peek reads Kuehne+Nagel's public shipment search, route milestones and transport notices directly through the open-source [universal-parcel-scraper Kuehne+Nagel adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel). It records achieved milestones only (ignoring planned forecast dates) and reads the total weight in kilograms and the destination country when returned. When the search finds the shipment under a customer reference, Peek lists Kuehne+Nagel's own tracking number beside yours, under "Kuehne+Nagel tracks this as"; when a reference matches several shipments, Peek can't tell which one is yours. A scan's time comes with its offset when the scanning system knows it, and Peek keeps that instant. Otherwise Peek dates the scan on its place's zone (Berlin time for Hamburg, Zurich time for Basel), a scan without a place on its neighbour's, else on Zurich time, and shows every scan on your device's clock.

Peek maps Kuehne+Nagel milestones into clear stages: **Label created** for bookings and ready-for-pickup notices, **Received by carrier** for pickup, **In transit** for hub, port, rail, and handover movements, **At customs** for export/import clearance and agency holds, **Out for delivery** for `In delivery` and truck arrival at destination, **Delivery attempted** for failed delivery attempts, **Needs attention** for holds, failed pickups, cancellations, and missing documents, and **Delivered** for delivery, freight collection, or proof-of-delivery completion. Bookkeeping notices stay in the timeline without moving the current stage backwards, and a post-delivery customs filing will not revert a delivered shipment.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day Kuehne+Nagel shows `In delivery` or the truck's `Arrival` at the delivery address, every 2 minutes from 6 a.m. on the newest scan's clock, while Kuehne+Nagel's tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When Kuehne+Nagel doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Kuehne+Nagel." After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Kuehne+Nagel."

You can choose notification alerts for **Every scan**, **Important steps**, or **Delivery only** from the parcel settings.

## Contacting Kuehne+Nagel

Kuehne+Nagel's site offers a location finder and a contact form:

- **Global website and tracking portal:** [kuehne-nagel.com](https://www.kuehne-nagel.com/) and [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- **Local office directory:** [Kuehne+Nagel locations worldwide](https://www.kuehne-nagel.com/company/locations) to locate the nearest country or branch desk
- **Online inquiry form:** [Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert) to get a tailored solution by email
- **Other freight networks:** if your order was split across carriers, compare [DHL Freight](carrier:dhl-freight) and [DSV](carrier:dsv).

## Frequently asked questions

### Why does my Kuehne+Nagel tracking number say no results found?

Kuehne+Nagel's public tracker only covers shipments that started in the last 90 days. Check that no characters are missing, and if the booking was just created, wait for the first achieved milestone. If a reference matches several shipments at once, ask the shipper for Kuehne+Nagel's own `RWM`, `RAPI` or other tracking number.

### What does "Service complete" or "Freight handover" mean on Kuehne+Nagel tracking?

`Freight handover` means Kuehne+Nagel transferred the cargo to a customs broker or onward carrier, so the shipment is still on its way to you. `Service complete` marks the end of Kuehne+Nagel's contracted leg — which may be a handover at a port or airport rather than doorstep delivery — so check whether a separate last-mile carrier number was issued.

### Why are some dates on myKN missing from Peek's scan history?

Kuehne+Nagel's public route view displays planned forecast dates alongside achieved scans, as well as undated future notices. Peek records only milestones that Kuehne+Nagel marks as actually achieved, so scheduled targets do not appear as completed movements.

### How long does Kuehne+Nagel public tracking stay available?

Public tracking on `mykn.kuehne-nagel.com` covers shipments that started in the last 90 days. To see more, myKN offers a free sign-up.

:::sources
- [Kuehne+Nagel global site](https://www.kuehne-nagel.com/)
- [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- [Kuehne+Nagel locations worldwide](https://www.kuehne-nagel.com/company/locations)
- [Kuehne+Nagel Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert)
- [universal-parcel-scraper Kuehne+Nagel adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel)
:::
