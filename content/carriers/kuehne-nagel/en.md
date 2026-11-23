---
title: Kuehne+Nagel tracking — numbers, statuses, and delivery
description: Track Kuehne+Nagel road, air, and sea shipments by reference or myKN link. Check number formats, milestone meanings, customs steps, and contact options.
slug: kuehne-nagel-tracking
published: 2026-11-23
updated: 2026-11-23
---

Kuehne+Nagel is a global freight forwarder headquartered in Switzerland, operating road, air, sea, and contract logistics networks across Europe, the Americas, Asia-Pacific, Africa, and the Middle East. Most recipients see Kuehne+Nagel on B2B pallet deliveries, bulky home-delivery orders moved through road freight terminals, and international air or ocean consignments tracked through the [myKN public tracker](https://mykn.kuehne-nagel.com/public-tracking/).

Public tracking on myKN covers shipments started within the last 90 days. Because Kuehne+Nagel moves freight across road stations, airports, container freight stations, rail ramps, and seaports, a single shipment can display both physical route milestones and separate booking, customs, or document notices.

## Kuehne+Nagel tracking number formats

Kuehne+Nagel accepts shipment references of 6 to 35 letters and digits on its public search, ignoring case, spaces, and dashes. The tracker searches by exact whole reference across Kuehne+Nagel tracking numbers as well as customer or freight references. Three formats appear regularly on Kuehne+Nagel parcels and road consignments:

- **`RWM` followed by 9 digits** (12 characters total, such as `RWM482917360`) — a Kuehne+Nagel road-logistics shipment number that identifies Kuehne+Nagel unambiguously.
- **`RAPI` followed by 8 digits** (12 characters total, such as `RAPI48291736`) — another Kuehne+Nagel road reference unique to the carrier.
- **2 digits, `A`, and 9 digits** (12 characters total, such as `26A482917360`) — a shorter reference shape that overlaps with [Australia Post](carrier:australia-post) and [Colis Privé](carrier:colis-prive), so Peek checks which carrier holds the shipment before locking onto one.

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
| Delivery attempt failed / Delivery not possible | A delivery visit did not succeed, for example if the consignee refused the freight or redirected it. |
| Shipment on hold / Delivery instructions pending / Missing/incomplete document | The consignment is paused at a station or customs point until missing paperwork, payment, or delivery instructions are supplied. |

A few milestone labels deserve a closer look:

- **Available at CY / Available at CFS / Ready for collection / Available at rail terminal** mean the shipment has arrived at an ocean container yard, container freight station, airport, or rail ramp and is ready for handover or onward release. Kuehne+Nagel or a partner may still perform the final road delivery, so these scans stay in transit rather than marking final delivery.
- **Freight handover** means Kuehne+Nagel handed the freight to a broker or onward carrier, not to the final recipient.
- **Document and filing notices** such as `HAWB available`, `MAWB available`, `Transport document available`, `VGM closure`, `Delivery appointment submission`, and `Customs billing invoice available` record administrative steps and do not change the physical stage of the shipment.
- **Empty container gate-in and vehicle departure at destination** occur after delivery is complete and remain at the delivered stage.

## Delivery, customs, and delays

Kuehne+Nagel moves palletised road freight, air cargo, and ocean containers rather than standard letterbox parcels. On European road deliveries, the local depot often books a delivery appointment with the recipient or receiving bay before sending the truck out; `Delivery appointment submission` records that an appointment date was entered, though the shipment only switches to out for delivery when `In delivery` posts.

International air and ocean shipments pass through export and import customs brokers. If tracking shows `Awaiting consignee's feedback`, `Awaiting consignee's payment`, `Entry documents required`, or `Unable to file customs entry - missing details or documentation`, the customs broker needs commercial invoices, EORI or tax details, or duty settlement before the shipment can leave the port or airport. See the [customs guide](guide:customs) for how import clearance and charges work.

Gaps of several days between scans are common during ocean sailings, multi-leg air consolidations, weekend depot closures, and terminal staging. Planned milestone dates shown on myKN are forecasts rather than achieved scans. For general advice on stalled freight and parcel tracking, read [when tracking stops updating](guide:tracking-not-updating).

## Tracking Kuehne+Nagel with Peek

When you paste a `RWM` or `RAPI` reference or a `mykn.kuehne-nagel.com` link into Peek, Peek recognizes Kuehne+Nagel immediately. For a 12-character `26A...` number shared with [Australia Post](carrier:australia-post) and [Colis Privé](carrier:colis-prive), or a custom freight reference without a dedicated prefix, pick Kuehne+Nagel using **Change** while adding the parcel or **Change carrier** on the tracking screen.

Peek reads Kuehne+Nagel's public shipment search, route milestones, and transport notices directly through the open-source [universal-parcel-scraper Kuehne+Nagel adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel). It records achieved milestones only (ignoring planned forecast dates), reads total weight in kilograms and destination country when returned, and replaces a secondary customer reference with Kuehne+Nagel's canonical tracking number when the search resolves to one shipment. Where Kuehne+Nagel supplies a UTC offset on a timestamp, Peek stores an exact instant; otherwise it displays the local clock reported by the tracker without guessing a zone.

Peek maps Kuehne+Nagel milestones into clear stages: **Label created** for bookings and ready-for-pickup notices, **Received by carrier** for pickup, **In transit** for hub, port, rail, and handover movements, **At customs** for export/import clearance and agency holds, **Out for delivery** for `In delivery` and truck arrival at destination, **Delivery attempted** for failed delivery attempts, **Needs attention** for holds, failed pickups, cancellations, and missing documents, and **Delivered** for delivery, freight collection, or proof-of-delivery completion. Bookkeeping notices stay in the timeline without moving the current stage backwards, and a post-delivery customs filing will not revert a delivered shipment.

If scans pause mid-journey, Peek reminds you that "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Kuehne+Nagel." After more than three weeks without a new milestone, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Kuehne+Nagel."

You can choose notification alerts for **Every scan**, **Important steps**, or **Delivery only** from the parcel settings.

## Contacting Kuehne+Nagel

Kuehne+Nagel handles shipment inquiries through its local country offices and the shipper's account team rather than a single global consumer helpline.

- **Global website and tracking portal:** [kuehne-nagel.com](https://www.kuehne-nagel.com/) and [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- **Local office directory:** [Kuehne+Nagel locations worldwide](https://www.kuehne-nagel.com/company/locations) to locate the nearest country or branch desk
- **Online inquiry form:** [Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert) for general shipment and service inquiries
- **Shipper or supplier first:** For commercial freight and bulky e-commerce orders, the party that booked the transport with Kuehne+Nagel (usually the seller or supplier) holds the account needed to amend delivery appointments, supply missing customs paperwork, or open a claim. Compare with other European freight networks such as [DHL Freight](carrier:dhl-freight) and [DSV](carrier:dsv) if your order was split across carriers.

## Frequently asked questions

### Why does my Kuehne+Nagel tracking number say no results found?

Kuehne+Nagel's public tracker only returns shipments that started within the last 90 days and matches whole references only. Check that no digits are missing, and if the booking was just created, wait for the first achieved milestone; if a reference matches several shipments at once, ask the shipper for Kuehne+Nagel's own `RWM`, `RAPI`, or house tracking number.

### What does "Service complete" or "Freight handover" mean on Kuehne+Nagel tracking?

`Freight handover` means Kuehne+Nagel transferred the cargo to a customs broker or onward carrier, so the shipment is still on its way to you. `Service complete` marks the end of Kuehne+Nagel's contracted leg — which may be a handover at a port or airport rather than doorstep delivery — so check whether a separate last-mile carrier number was issued.

### Why are some dates on myKN missing from Peek's scan history?

Kuehne+Nagel's public route view displays planned forecast dates alongside achieved scans, as well as undated future notices. Peek records only milestones that Kuehne+Nagel marks as actually achieved, so scheduled targets do not appear as completed movements.

### How long does Kuehne+Nagel public tracking stay available?

Public tracking on `mykn.kuehne-nagel.com` covers shipments initiated within the past 90 days. Older freight records require a registered myKN business account or confirmation from the shipper.

:::sources
- [Kuehne+Nagel global site](https://www.kuehne-nagel.com/)
- [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- [Kuehne+Nagel locations worldwide](https://www.kuehne-nagel.com/company/locations)
- [Kuehne+Nagel Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert)
- [universal-parcel-scraper Kuehne+Nagel adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel)
:::
