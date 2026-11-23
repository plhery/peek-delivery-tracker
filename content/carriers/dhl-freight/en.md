---
title: DHL Freight tracking: ActiveTracing IDs, statuses & help
description: Track a DHL Freight European road or rail shipment via ActiveTracing: JND licence plates, order codes, consignment IDs, SSCCs, appointments and support.
slug: dhl-freight-tracking
published: 2026-11-23
updated: 2026-11-23
---

DHL Freight (*DHL Freight GmbH*, part of DHL Group) is DHL's European road and rail freight division, operating branches in more than 40 countries and moving over 45 million tonnes of freight per year across LTL (less-than-truckload), PTL (part-truckload), FTL (full-truckload), temperature-controlled, high-security and European rail networks. Alongside B2B palletised cargo (*EuroConnect*, *Eurapid*), DHL Freight also delivers large residential orders (*DHL Home Delivery*) and domestic Swedish *DHL Service Point* consignments. You can look up a consignment on [DHL ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do), or paste your reference into the box above.

## DHL Freight tracking numbers: order codes, consignment IDs and JND licence plates

DHL ActiveTracing accepts several distinct reference types printed on your shipping note, invoice or appointment SMS (keeping consignment records online for **six months**):

| Format (invented) | What it is |
| --- | --- |
| `JND48291736405829173` | 20-character **DHL Package ID / License Plate** (`JND` + 17 digits). |
| `FRA-EC-4829173` | **DHL Order code**: 3-letter terminal code + 2-letter product code (such as `EC` for EuroConnect) + 7 digits (with or without hyphens). |
| `4829173640` | **Consignment ID**: 9, 10 or 11 digits (10-digit IDs are common for Swedish *DHL Service Point* domestic consignments). Shares 10-digit length with [DHL](carrier:dhl), [Mondial Relay](carrier:mondial-relay), [Relais Colis](carrier:relais-colis) and [Packeta](carrier:packeta). |
| `00348291736405829177` | 20-digit **GS1 SSCC** package ID starting with `00` (valid GS1 check digit). Shares 20-digit SSCC shape with [USPS](carrier:usps) and [NZ Post](carrier:nz-post). |

Note that standard parcel deliveries in Germany use [DHL](carrier:dhl) (*DHL Paket*) and international air-express documents/parcels use [DHL Express](carrier:dhl-express), whereas pallets, heavy furniture and European road/rail freight track on DHL Freight's ActiveTracing portal. See [tracking number formats](guide:tracking-number-formats).

## What DHL Freight ActiveTracing statuses mean

ActiveTracing reports English or German milestone sentences paired with the terminal code (for example `DE-KOBLENZ`) and local terminal wall-clock date/time (`Europe/Berlin` or local European terminal time):

| DHL Freight says (EN / DE) | What it means for you | Peek shows |
| --- | --- | --- |
| `Consignment created`, `Collection arranged - OK`, `Out for Collection`, `Not Collected - ...` / `Auftrag erfasst`, `In Abholung` | Freight order entered or collection driver dispatched; goods are still with the shipper. | "Label created" |
| `Collected`, `Shipment handover by sender` / `Abholung` | Picked up from the shipper or dropped off at a Swedish DHL Service Point. | "Received by carrier" |
| `Received at terminal`, `Shipped from terminal`, `Arrival at destination`, `Loaded into ferry`, `Direct delivery`, `Receiver notified`, `Shipment in warehouse - Awaiting delivery appointment` / `Delivery appointment booked` / `Empfangen im Terminal`, `Verladen`, `Avisierung` | Moving through DHL Freight terminals, crossing by ferry, cleared customs (`Customs cleared`) or held at the destination terminal while agreeing a delivery slot. | "In transit" |
| `On hold - in customs clearance` | Held for cross-border customs clearance (e.g. Switzerland, UK or Norway). | "At customs" |
| `Out for Delivery` / `In Zustellung` | Loaded on the local freight truck for delivery today. | "Out for delivery" |
| `Shipment in warehouse - ready for self collection by consignee` | Ready for self-collection at the local DHL Freight terminal. | "Ready for pickup" |
| `Attempted DHL delivery - ...` | Delivery attempted but could not be completed (for example consignee closed or refused). | "Delivery attempted" |
| `Shipment in warehouse - waiting for consignee payment`, `Shipment delivered with exception - partly delivered`, `Exception occurred - Strike`, `New shipping instruction - consignment cancelled by sender` | Payment hold, partial delivery shortage, strike disruption or shipper cancellation. | "Needs attention" |
| `New shipping instruction - shipment returned` | Freight returned to the shipper under new instructions. | "Returned to sender" |
| `Delivered`, `Received by consignee`, `Picked up by receiver`, `Shipment delivered with exception` / `Zugestellt` | Delivered to the consignee or collected at the terminal/Service Point. | "Delivered" |

## Delivery appointments (*Avisierung*), customs and freight rules

According to [DHL Freight Germany](https://www.dhl.com/de-en/home/freight.html) and the [DHL Freight Help Center](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html):

- **Delivery appointments (*Avisierung*):** bulky road-freight and *DHL Home Delivery* orders typically pause at `Shipment in warehouse - Awaiting delivery appointment` (`Avisierung`) while the local destination terminal contacts you by phone, SMS or email to agree a delivery day. Respond quickly so the consignment moves to `Delivery appointment booked` and avoids warehouse storage delays.
- **Six-month ActiveTracing retention:** [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) displays consignment histories for the **last six months** across English, German, Spanish, French, Swedish, Polish and Italian.
- **Cross-border European customs:** road freight moving between the EU and non-EU neighbours (such as the UK, Switzerland or Norway) requires customs clearance (`On hold - in customs clearance`). For non-EU e-commerce parcels entering the EU, the €3 customs duty on consignments up to €150 and €2 EU handling fee apply under the [EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en); see [parcels held at customs](guide:customs).

## DHL Freight consignment delayed, damaged or partly delivered

Because road-freight contracts sit between the shipper and the local DHL Freight terminal, contact the seller or shipper first alongside your destination terminal.

- **Visible damage or missing pallets (`partly delivered`):** inspect pallet wrap, piece counts and cartons on arrival and write any shortage or visible damage directly on the delivery receipt before signing. DHL Freight's Help Center includes a dedicated *How to Prepare a Claim* guide for shippers and consignees.
- **Waiting for appointment or payment:** if ActiveTracing shows `Awaiting delivery appointment` or `waiting for consignee payment`, contact the destination terminal named in the latest scan (or the shipper) right away.
- See [why tracking stops updating](guide:tracking-not-updating), [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## DHL Freight customer service and contacts

Support channels published on the [DHL Freight Help Center](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) and [DHL Freight Germany](https://www.dhl.com/de-en/home/freight.html):

- **Help Center for European Road and Rail:** [dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) — FAQs, *How to Prepare a Claim*, Incoterms 2020, Terms & Conditions and Dangerous Goods rules.
- **Viva AI Customer Service Assistant & Terminal Finder:** DHL Freight directs shipment inquiries through **Viva** (its AI customer service assistant) and its country-by-country **Freight Terminals and Locations** directory (because local road-freight terminals handle delivery appointments and claims directly).
- **Business portal:** *myDHLFreight* for commercial shippers managing quotes, bookings and eBilling.

> Ignore unsolicited SMS or email links requesting an unexpected online payment to "release" a DHL Freight shipment. Verify any freight status directly on [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) or with your destination DHL Freight terminal. See [delivery scam texts](guide:delivery-scam-texts).

## Track a DHL Freight shipment with Peek

[Peek](/) checks DHL Freight automatically as a candidate for `JND` + 17-digit licence plates (`JND48291736405829173`, invented — where DHL Freight is the sole candidate) and 10-digit Swedish domestic consignment IDs (`4829173640`), showing "DHL Freight has this parcel." when ActiveTracing confirms it, and recognises pasted `activetracing.dhl.com/DatPublic/datSelection.do` links (`a=`, `valuePackageField=` or `valueShipmentOrderField=`). For DHL Order codes (`FRA-EC-4829173`), 9- or 11-digit consignment IDs or 20-digit GS1 SSCC codes (`00348291736405829177`), tap "Change" while adding or "Change carrier" on the parcel screen and select DHL Freight (searching "DHL ActiveTracing", "DHL Frachtverfolgung" or "DHL Freight GmbH" finds it too).

Peek queries ActiveTracing's English details page directly and shows the mapped stage ("Label created", "Received by carrier", "In transit", "At customs", "Out for delivery", "Ready for pickup", "Delivery attempted", "Needs attention", "Returned to sender", "Delivered"), English milestone sentences, terminal locations (formatted such as `KOBLENZ, DE`), service/product name, gross weight in kilograms, destination country, consignee town and local terminal wall-clock timestamps. Peek discards origin details, piece count, volume, departure date, separate delivery timestamp, alias IDs, proof-of-delivery PDFs and signatures; ActiveTracing does not expose an estimated delivery date, numeric status codes, sender name or recipient name.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. Berlin time on the day an `Out for Delivery` scan arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12"), shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking DHL Freight." and displays "Get help from DHL Freight", which opens DHL Freight's Help Center. After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking DHL Freight."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot book a delivery appointment, pay freight charges or contact a DHL Freight terminal on your behalf.

## Questions about DHL Freight tracking

### What is the difference between DHL Freight and DHL Parcel or DHL Express?

DHL Freight handles European road and rail freight (pallets, LTL/PTL/FTL, bulky *DHL Home Delivery* and Swedish domestic freight) tracked on [ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do), whereas standard parcels use [DHL](carrier:dhl) and time-definite international air courier shipments use [DHL Express](carrier:dhl-express).

### What tracking numbers work on DHL ActiveTracing?

ActiveTracing accepts DHL Order codes (`FRA-EC-4829173`, invented), 9- to 11-digit Consignment IDs (`4829173640`), 20-character `JND` Package IDs / License Plates (`JND48291736405829173`) and 20-digit GS1 SSCC numbers (`00348291736405829177`).

### Why is my shipment held at `Awaiting delivery appointment`?

For bulky road freight and home deliveries, the destination DHL Freight terminal waits to agree a delivery date with the recipient (*Avisierung*) before putting the goods on a local truck. Check your phone, SMS or email for a scheduling message, or contact the shipper.

### How long does DHL ActiveTracing keep shipment history?

[ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) states that consignment data remains available online for the **last six months**.

:::sources
- [DHL Freight: ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) – consignment, order code and package ID/licence plate lookup, 6-month retention, *DHL Freight GmbH*
- [DHL Freight: Germany Home (English)](https://www.dhl.com/de-en/home/freight.html) – 40+ countries, 45M+ tonnes/year, LTL/PTL/FTL, GoGreen Plus Flex, Rail Freight Europe and terminal network
- [DHL Freight: Help Center for European Road and Rail (English)](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) – Viva AI assistant, Freight Terminals finder, How to Prepare a Claim, Incoterms 2020 and myDHLFreight (help URL linked by Peek)
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty up to €150 and €2 EU handling fee
- [Universal Parcel Scraper: dhl-freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-freight) – `JND`, order code, 9–11-digit consignment ID and SSCC parsing, `activetracing.dhl.com` link rules, English/German status map, `Europe/Berlin` local clocks, scraped weight/town/product and help URL
:::
