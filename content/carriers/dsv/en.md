---
title: DSV tracking: shipment numbers, statuses and DB Schenker
description: Track a DSV road, air or sea shipment, or a former DB Schenker one: which number to use, what each status means, delivery, claims and who to contact.
slug: dsv-tracking
published: 2026-11-06
updated: 2026-11-06
---

DSV is a Danish transport and logistics group that moves freight by road, air and sea, and in 2025 it bought Schenker (DB Schenker) from Deutsche Bahn. On dsv.com, the "Track your shipment" box has two fields: "Track DSV shipment" opens DSV's tracker, myDSV, and "Track Schenker shipment" the former DB Schenker one. You need no account. The number comes from whoever booked the transport, usually the sender or the shop; paste it in the box above.

## DSV tracking number: what it looks like and where to find it

The business that booked DSV has the number: look in the shop's shipping email or on your order page, or ask the sender. More in [how to find your tracking number](guide:find-tracking-number).

While DSV merges Schenker into its systems, it keeps two trackers: a shipment booked with Schenker may only be in the Schenker one. If one says nothing, try the other.

| Number (invented) | What it is |
| --- | --- |
| `DEXXX000000000` | 14 characters: a European country code such as DE, SE, DK or PL, three letters and nine digits. A former DB Schenker road shipment number, and the shape Peek's catalog gives DSV. |
| `XYZ0000000` | Three letters and seven or eight digits: another former DB Schenker road shipment number. Peek doesn't name DSV from this shape. |
| `099999999999999980` | 18 digits (20 with `00` in front): the barcode number on a package of a former DB Schenker road shipment. |
| Letters and digits | A myDSV shipment, booking or house bill number. Peek asks myDSV with any 5 to 40 letters and digits that hold at least one digit. |
| `mydsv.com/track-shipment?query=DEXXX000000000` | A link to myDSV's tracker, as dsv.com's "Track DSV shipment" opens it. |

A customer or order reference, or the airline's master air waybill, can find a shipment in myDSV's search, but Peek doesn't follow those: use DSV's shipment, booking or house bill number. Nor can Peek pick one of several shipments a number matches.

## What DSV tracking statuses mean

myDSV logs each time a shipment is scanned, from the booking to the proof of delivery. Its public view gives high-level information; the private view, on request, shows the shipment's details. Here are myDSV's steps as Peek reads them:

| DSV says | What it means for you | Peek's status |
| --- | --- | --- |
| `Booking` | DSV has the booking, dated without a time. | "Label created" |
| `Creation` | The shipment was created in DSV's road system. | "Label created" |
| `Actual pickup` | DSV picked it up from the sender. | "Received by carrier" |
| `Cargo received` | The goods reached DSV, at a terminal or from the pickup. | "Received by carrier" |
| `Actual Departure`, `Actual departure - Origin Port` | It left a port or airport on the main leg. | "In transit" |
| `Actual arrival`, `Actual arrival- Destination Port` | It reached a port or airport on the main leg. | "In transit" |
| `Handover responsibilty of cargo`, `Handover of Cargo` | DSV handed the cargo over at the end of its service, which completes the shipment (the first keeps DSV's spelling). | "Delivered" |
| `Actual delivery` | Delivered. | "Delivered" |

`Estimated Departure`, `Estimated arrival`, `Estimated pickup`, `Estimated delivery`, `Requested pickup` and `Requested delivery` are plans, not scans: Peek leaves them out of the history and shows `Estimated delivery` as the expected delivery day, or range of days, until delivery.

The former DB Schenker tracker covers road shipments with its own wording:

| DB Schenker tracker says | What it means for you | Peek's status |
| --- | --- | --- |
| `Booked` | The sender booked the transport. | "Label created" |
| `Out for collection` | A driver is on the way to the sender. | "Label created" |
| `Collected` | Picked up from the sender. | "Received by carrier" |
| `Delivered to terminal by shipper` | The sender brought it to a terminal. | "Received by carrier" |
| `Arrived`, `Departed` | It reached or left a terminal. | "In transit" |
| `Customs clearance initiated` | Customs clearance has started. | "At customs" |
| `Customs clearance finalised` | Cleared, and moving again. | "In transit" |
| `Out for Delivery` | On the delivery truck. | "Out for delivery" |
| `Not delivered` | The delivery failed; a reason may follow. | "Delivery attempted" |
| `To consignee's disposal`, `Ready for pick up` | It waits for you to collect it. | "Ready for pickup" |
| `Delivered` | Delivered. | "Delivered" |
| `Picked up by consignee`, `Picked up at agent` | Collected by the recipient, or from an agent. | "Delivered" |
| `ePOD available` | The electronic proof of delivery is ready. | "Delivered" |

On the way it also shows `Linehaul departed`, `Linehaul arrived`, `Terminal inventory`, `Vessel Loaded`, `Vessel Unloaded`, `Train Loaded`, `Train Unloaded`, `Vessel/Train Loaded` and `Vessel/Train Unloaded`: all "In transit" in Peek. `Not Collected` (the pickup failed) and `Not loaded` (it missed its planned load, with a reason) show "Needs attention". When the tracker prints a reason beside a step, such as why a delivery failed, Peek adds it after the wording. More on stages in [tracking statuses](guide:tracking-statuses).

## How DSV delivers

**Pallets and part loads.** DSV calls its road groupage network Europe's largest: 36+ countries, 400+ terminals and consignments up to 2,500 kg, so a pallet or bulky order may come on a truck from a DSV terminal.

**Online orders.** DSV's Parcel E-commerce service collects a shop's parcels, consolidates them across borders and has local last-mile networks deliver them, so another carrier may bring yours. If the shop's email names one or gives a second number, track that too: Peek's DSV lookup names no next carrier.

**Updates and proof of delivery.** In myDSV, users can set up notifications, for example for pickup or customs clearance, see estimated arrival times for road cargo and download the proof of delivery once the shipment is complete. If you didn't book DSV, ask the sender. DSV's pages don't say how many delivery attempts it makes or how long a terminal keeps goods: ask the DSV office handling the delivery.

**Customs.** In most countries where it has offices, DSV is a licensed customs broker; elsewhere it works with agents. For help with a clearance, DSV says to contact DSV in that country. In Ireland and the rest of the EU, goods bought online from outside the EU carry a €3 customs duty per item on orders up to €150, since 1 July 2026, and a €2 Union handling fee per item, whatever the value, since 1 November 2026. Elsewhere, your country's own rules apply: see [parcels held at customs](guide:customs).

## DSV shipment late, stuck or damaged

**Not moving.** If one tracker stops, try the other on dsv.com: a shipment booked with Schenker may not be in myDSV yet. Then ask the sender, who booked DSV and has its contact. See [tracking that isn't updating](guide:tracking-not-updating).

**Damaged or missing goods.** For an online order, start with the shop, which booked DSV. DSV's eClaims takes claims and documents, shows their status and puts you in touch with its local claims adjusters; it gives no deadline there, so don't wait. See [damaged parcel](guide:damaged-parcel) and [lost parcel](guide:lost-parcel).

**Misuse of DSV's name.** DSV warns that firms and people with no link to it sometimes use its name to sell freight services. If you're unsure a website or contact is really DSV's, check with your local DSV office; for texts about a parcel, see [delivery scam texts](guide:delivery-scam-texts).

## DSV contact and customer service

As read on dsv.com on 1 November 2026:

- **Contact form:** on DSV's [help and contact page](https://www.dsv.com/en/support/help-and-contact).
- **Local offices:** DSV is in more than 90 countries; [find your local DSV office](https://www.dsv.com/en/support/find-dsv) by country.
- **Claims:** through eClaims, from DSV's [Claims page](https://www.dsv.com/en/support/self-services/claims).
- **DSV A/S:** Hedehusene, Denmark, +45 43 20 30 40, as its legal notice gives them. It isn't a tracking line.

DSV's help pages give no phone number for tracking.

## Track a DSV shipment with Peek

Paste a 14-character number like `DEXXX000000000` and Peek asks DSV while you add it ("Checking tracking services…"), then shows "DSV has this parcel." when DSV knows it. If DSV doesn't list it yet, the parcel reads "Waiting for DSV’s first scan", marked "Probably DSV", while Peek keeps asking; after a week, Peek asks you to check the number or choose another carrier.

Peek can't tell DSV by any other number's shape. Paste it all the same: Peek follows more than 3,500 carriers. To set DSV yourself, press "Change" and search for DSV; "Schenker" finds it too ("Also called DB Schenker"). On a parcel you've already added, use "Change carrier". A mydsv.com tracking link files the parcel under DSV straight away, and so does a link from the Schenker tracker on dsv.com with `refNumber=`.

Peek asks myDSV first. A former DB Schenker road number or package barcode that myDSV doesn't know goes to the Schenker tracker, which Peek reads for road shipments only: for an air or sea shipment found only there, use "Track Schenker shipment" on dsv.com.

Peek shows the status, each step in DSV's wording with its town and country, and the expected delivery day. DSV gives each scan's local time without a time zone; Peek works out the zone from the scan's place (a town, a port, an airport code) or the scans around it, else Copenhagen time, so now and then it can be wrong. Peek leaves out the service name, the sender's town, your address, postcodes and DSV's progress bar, and from the Schenker tracker the parties, references, goods and the name of whoever signed.

If Early Bird, a Swedish delivery company, hands your parcel over to DSV or DB Schenker, Peek names DSV as the carrier delivering it and follows DSV once DSV shows fresh scans.

Peek checks every 10 minutes, or every 2 once the shipment is out for delivery, and can notify you of "Every scan", "Important steps" or "Delivery only". After two days without news (12 hours while the shipment still shows "Label created" or "Received by carrier"), Peek checks hourly. It can't contact DSV or the sender for you, book a delivery or file a claim.

## Questions about DSV tracking

### Is DSV the same company as DB Schenker?

Yes. DSV, founded in 1976, completed its purchase of Schenker from Deutsche Bahn on 30 April 2025, and dbschenker.com has been merged into dsv.com. Schenker shipments keep their own tracker for now.

### How do I track a DB Schenker shipment now?

Type the number under "Track Schenker shipment" in dsv.com's "Track your shipment" box, or paste it in the box above; unless it has 14 characters and starts with a country code, choose DSV as the carrier yourself. Peek asks myDSV, then the Schenker tracker for a road shipment.

### What does handover of cargo mean on DSV tracking?

DSV has handed the goods over at the end of its service, and the shipment is complete; Peek shows "Delivered". If nothing has reached you, ask the sender where DSV handed them over, and see [delivered but not received](guide:delivered-not-received).

### Does DSV have a customer service phone number?

Its help pages give none for tracking. Use the contact form or find your local DSV office on dsv.com. The number in DSV A/S's legal notice, +45 43 20 30 40 in Denmark, isn't a tracking line.

:::sources
- [DSV: Book and track shipments with myDSV](https://www.dsv.com/en/digital-solutions/book-and-track) – tracking without an account, the two tracking fields, dbschenker.com merged into dsv.com
- [DSV: myDSV](https://www.dsv.com/en/digital-solutions/mydsv) – public and private view, scans log, notifications, proof of delivery
- [DSV: Self-services](https://www.dsv.com/en/support/self-services) – DSV and Schenker trackers, eClaims, Schenker systems until migration
- [DSV: Frequently asked questions](https://www.dsv.com/en/support/faq) – contact form, two myDSV versions during the transition
- [DSV: Help and contact](https://www.dsv.com/en/support/help-and-contact) – over 90 countries, office finder
- [DSV: Find your local DSV office](https://www.dsv.com/en/support/find-dsv) – offices by country
- [DSV: Claims](https://www.dsv.com/en/support/self-services/claims) – what eClaims does
- [DSV: Groupage transport](https://www.dsv.com/en/our-solutions/modes-of-transport/road-transport/groupage) – 36+ countries, 400+ terminals, up to 2,500 kg
- [DSV: E-commerce delivery solutions](https://www.dsv.com/en/our-solutions/contract-logistics/ecommerce-solutions/last-mile-delivery) – cross-border consolidation, local last-mile networks
- [DSV: Customs clearance](https://www.dsv.com/en/our-solutions/modes-of-transport/value-added-services/customs-clearance) – licensed customs broker, agents, contact DSV in that country
- [DSV: History](https://www.dsv.com/en/about-dsv/history) – founded in 1976, road, air and sea, Schenker bought from Deutsche Bahn in 2025
- [DSV: DSV completes acquisition of Schenker](https://www.dsv.com/en/about-dsv/press/news/dsv-completes-acquisition-of-schenker-2628130) – completed 30 April 2025, integration
- [DSV: Legal notice](https://www.dsv.com/en/about-dsv/legal-notice) – DSV A/S in Denmark, telephone, misuse of the DSV name
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty per item up to €150, €2 Union handling fee
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per item whatever the value
- [Universal Parcel Scraper: DSV](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dsv) – number shapes and links, the two trackers, DSV's wordings and stages, local clocks, what Peek reads
:::
