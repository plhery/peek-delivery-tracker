---
title: DHL Freight tracking: ActiveTracing IDs, statuses & help
description: Track a DHL Freight European road or rail shipment via ActiveTracing: JND licence plates, order codes, consignment IDs, SSCCs, appointments and support.
slug: dhl-freight-tracking
published: 2026-11-23
updated: 2026-11-23
---

DHL Freight (*DHL Freight GmbH*, part of DHL Group) is DHL's European road and rail freight division, with subsidiaries in more than 40 countries and moving over 45 million tonnes of freight per year across LTL (less-than-truckload), PTL (part-truckload), FTL (full-truckload), temperature-controlled, high-security and European rail networks. Alongside B2B palletised cargo (such as *EuroConnect*), DHL Freight also delivers large residential orders (*DHL Home Delivery*) and domestic Swedish *DHL Service Point* consignments. You can look up a consignment on [DHL ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do), or paste your reference into the box above.

## DHL Freight tracking numbers: order codes, consignment IDs and JND licence plates

DHL ActiveTracing takes several kinds of reference and keeps consignment data online for **six months**:

| Format (invented) | What it is |
| --- | --- |
| `JND48291736405829173` | 20-character **DHL Package ID / License Plate** (`JND` + 17 digits). |
| `FRA-EC-4829173` | **DHL Order code**: 3-letter terminal code + 2-letter product code (such as `EC` for EuroConnect) + 7 digits (with or without hyphens). |
| `4829173640` | **Consignment ID**: 9, 10 or 11 digits (10-digit IDs are common for Swedish *DHL Service Point* domestic consignments). Shares 10-digit length with [DHL](carrier:dhl), [Mondial Relay](carrier:mondial-relay), [Relais Colis](carrier:relais-colis) and [Packeta](carrier:packeta). |
| `00348291736405829177` | 20-digit **GS1 SSCC** package ID starting with `00` (valid GS1 check digit). Shares 20-digit SSCC shape with [USPS](carrier:usps) and [NZ Post](carrier:nz-post). |

Note that standard parcel deliveries in Germany use [DHL](carrier:dhl) (*DHL Paket*) and international air-express documents/parcels use [DHL Express](carrier:dhl-express), whereas pallets, heavy furniture and European road/rail freight track on DHL Freight's ActiveTracing portal. See [tracking number formats](guide:tracking-number-formats).

## What DHL Freight ActiveTracing statuses mean

ActiveTracing gives each row a status sentence, in English or German, the terminal (for example `DE-KOBLENZ`) and the terminal's date and time, without a time zone:

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

- **Delivery appointments (*Avisierung*):** a consignment can wait at the delivery terminal at `Shipment in warehouse - Awaiting delivery appointment` until a delivery day is agreed with the consignee; `Delivery appointment booked` follows once it is. If it waits, ask the shipper or the delivery terminal.
- **Six-month ActiveTracing retention:** [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) displays consignment histories for the **last six months** across English, German, Spanish, French, Swedish, Polish and Italian.
- **Cross-border European customs:** road freight moving between the EU and non-EU neighbours (such as the UK, Switzerland or Norway) requires customs clearance (`On hold - in customs clearance`). For non-EU e-commerce parcels entering the EU, the €3 customs duty on consignments up to €150 and €2 EU handling fee apply under the [EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en); see [parcels held at customs](guide:customs).

## DHL Freight consignment delayed, damaged or partly delivered

The shipper booked the transport with DHL Freight, so contact the seller or shipper first.

- **Visible damage or missing goods (`partly delivered`):** check the goods and the number of pieces on arrival and tell the shipper at once. DHL Freight's Help Center has a *How to Prepare a Claim* page.
- **Waiting for appointment or payment:** if ActiveTracing shows `Awaiting delivery appointment` or `waiting for consignee payment`, contact the destination terminal named in the latest scan (or the shipper) right away.
- See [why tracking stops updating](guide:tracking-not-updating), [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## DHL Freight customer service and contacts

Support channels published on the [DHL Freight Help Center](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) and [DHL Freight Germany](https://www.dhl.com/de-en/home/freight.html):

- **Help Center for European Road and Rail:** [dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) — FAQs, *How to Prepare a Claim*, Incoterms 2020, Terms & Conditions and Dangerous Goods rules.
- **Viva and the terminal finder:** the Help Center offers **Viva**, DHL Freight's AI-based customer service assistant, and *Freight Terminals and Locations*, to find terminal and office locations by country.
- **Business portal:** *myDHLFreight*, with tutorial videos and FAQs in the Help Center.

> Ignore unsolicited SMS or email links requesting an unexpected online payment to "release" a DHL Freight shipment. Verify any freight status directly on [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) or with your destination DHL Freight terminal. See [delivery scam texts](guide:delivery-scam-texts).

## Track a DHL Freight shipment with Peek

[Peek](/) asks DHL Freight about a `JND` licence plate (`JND48291736405829173`, invented), the only carrier it asks for one, and shows "DHL Freight has this parcel." once ActiveTracing answers for it. For a 10-digit consignment ID (`4829173640`), Peek asks DHL Freight among the carriers that use ten digits when you track from Germany, Sweden, Austria, Poland, the Netherlands, Belgium, France, Italy, Spain or the UK, and only as a last resort elsewhere. Peek also recognises a pasted `activetracing.dhl.com/DatPublic/datSelection.do` link (`a=`, `valuePackageField=` or `valueShipmentOrderField=`). For an order code (`FRA-EC-4829173`), a 9- or 11-digit consignment ID or an SSCC (`00348291736405829177`), tap "Change" while adding or "Change carrier" on the parcel screen and choose DHL Freight (searching "DHL ActiveTracing", "DHL Frachtverfolgung" or "DHL Freight GmbH" finds it too).

Peek reads ActiveTracing's English page directly and shows the stage ("Label created", "Received by carrier", "In transit", "At customs", "Out for delivery", "Ready for pickup", "Delivery attempted", "Needs attention", "Returned to sender", "Delivered"), ActiveTracing's status sentences with the terminal (such as `KOBLENZ, DE`), the gross weight and the destination country. Peek dates each row on its terminal's time zone and shows it on your device's clock. The service name stays out of every Peek app. The consignee's town, the one part of the address ActiveTracing shows, can end your own map's route while the shipment is on its way; shared links don't show it. Peek leaves out the origin, piece count, volume, departure date, separate delivery time, other references, proof of delivery and signature; ActiveTracing gives no estimated delivery date, status codes, sender or recipient name.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day ActiveTracing shows `Out for Delivery`, every 2 minutes (every 5 after a check that opened a browser) from 6 a.m. on the clock of the newest scan's place, while ActiveTracing answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When ActiveTracing doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking DHL Freight.", with a "Get help from DHL Freight" link to DHL Freight's Help Center. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking DHL Freight."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot book a delivery appointment, pay freight charges or contact a DHL Freight terminal on your behalf.

## Questions about DHL Freight tracking

### What is the difference between DHL Freight and DHL Parcel or DHL Express?

DHL Freight handles European road and rail freight (pallets, LTL/PTL/FTL, bulky *DHL Home Delivery* and Swedish domestic freight) tracked on [ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do), whereas standard parcels use [DHL](carrier:dhl) and time-definite international air courier shipments use [DHL Express](carrier:dhl-express).

### What tracking numbers work on DHL ActiveTracing?

ActiveTracing accepts DHL Order codes (`FRA-EC-4829173`, invented), 9- to 11-digit Consignment IDs (`4829173640`), 20-character `JND` Package IDs / License Plates (`JND48291736405829173`) and 20-digit GS1 SSCC numbers (`00348291736405829177`).

### Why is my shipment held at `Awaiting delivery appointment`?

The delivery terminal holds the consignment until a delivery day is agreed with the consignee. Ask the shipper, or the terminal named in the latest row.

### How long does DHL ActiveTracing keep shipment history?

[ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) states that consignment data remains available online for the **last six months**.

:::sources
- [DHL Freight: ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) – consignment, order code and package ID/licence plate lookup, 6-month retention, *DHL Freight GmbH*
- [DHL Freight: Germany Home (English)](https://www.dhl.com/de-en/home/freight.html) – 40+ countries, 45M+ tonnes/year, LTL/PTL/FTL, GoGreen Plus Flex, Rail Freight Europe and terminal network
- [DHL Freight: Help Center for European Road and Rail (English)](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) – Viva AI assistant, Freight Terminals finder, How to Prepare a Claim, Incoterms 2020 and myDHLFreight (help URL linked by Peek)
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty up to €150 and €2 EU handling fee
- [Universal Parcel Scraper: dhl-freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-freight) – `JND`, order code, 9–11-digit consignment ID and SSCC parsing, `activetracing.dhl.com` link rules, English/German status map, each terminal's local clock, the weight and destination country it reads, and help URL
:::
