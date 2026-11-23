---
title: SEKO Logistics tracking — numbers, OmniParcel, and delivery
description: Track SEKO Logistics and SEKO OmniParcel shipments. Check tracking formats, GX numbers, milestone meanings, US and UK hotlines, and delivery estimates.
slug: seko-logistics-tracking
published: 2026-11-23
updated: 2026-11-23
---

SEKO Logistics began in 1976 with a single office in Chicago and now has more than 150 offices in over 60 countries and 15 primary hubs. Its services include air, ocean and ground freight, e-commerce, White Glove & Specialty Delivery, and Home Delivery & Last Mile. Shipments are tracked on [sekologistics.com/en/track](https://www.sekologistics.com/en/track/), and cross-border e-commerce parcels on SEKO's [OmniParcel tracker](https://track.omniparcel.com/).

Because SEKO's standard tracking portal and OmniParcel operate as two separate systems with different tracking number formats and scan styles, knowing which format you have helps you read the timeline accurately.

## SEKO Logistics and OmniParcel tracking number formats

SEKO Logistics shipments do not follow one exclusive prefix; instead, they fall into two families handled by different portals:

- **SEKO Logistics shipment numbers:** Peek takes 8 to 20 letters and digits with a run of at least 7 digits. Shapes seen include 3 letters followed by 7 digits (such as `WFY4829173`), 5 letters followed by 7 digits, or 9 digits alone (such as `482917360`). Peek never recognises SEKO from a bare number: for 3 letters + 7 digits it asks [Australia Post](carrier:australia-post), for 5 letters + 7 digits [Colis Privé](carrier:colis-prive), and for 9 digits [DPD Ireland](carrier:dpd-ie), outside Ireland only as a last resort. Select SEKO Logistics manually or paste your tracking link.
- **SEKO OmniParcel numbers (`GX` + 17 or 18 digits):** Cross-border e-commerce consignments on `track.omniparcel.com` use `GX` followed by 17 or 18 digits (such as `GX482917360514829173`). SEKO's main tracking page does not recognize `GX` numbers; only OmniParcel's tracker answers them.

If you paste a link from `sekologistics.com/en/track/?trackingNumbers=...` or `track.omniparcel.com/...`, Peek extracts the number and routes it straight to SEKO Logistics automatically. For more on how logistics and parcel codes differ, see the [tracking number formats guide](guide:tracking-number-formats).

## SEKO Logistics and OmniParcel tracking statuses

SEKO's main tracking page often phrases milestones as `Status Changed to [...]` (occasionally with a trailing dash instead of brackets), while OmniParcel lists international hub, flight, customs, and final-mile courier descriptions.

| SEKO / OmniParcel wording | What it means |
| --- | --- |
| `Shipment Data Received` / `Shipment Created` / `Tracking number allocated & order ready` | Shipment details have reached SEKO or OmniParcel, before physical pickup or warehouse intake. |
| `Arrived at PU location` | The pickup driver reached the shipper; freight is not yet recorded as collected. |
| `Picked Up From Customer` / `At Pickup Terminal` / `Received at Origin Warehouse` | SEKO has collected the shipment or checked it into the origin terminal or warehouse. |
| `In Transit` / `In Transit to Delivery Terminal` / `At Transfer Terminal` / `Processed through Export Hub` / `Flight - Freight departed to destination` | Moving along domestic linehaul or international air freight toward the destination country or delivery station. |
| `At Delivery Terminal` / `At Delivery Terminal Delivery Appt Set` / `On Hand Destination Station` / `Delivered To Warehouse` | Arrived at SEKO's destination terminal or local warehouse ahead of final delivery; `Delivery Appt Set` means a home-delivery appointment has been booked. |
| `Received by Destination Customs` / `Customs held for inspection and clearance` | Undergoing destination customs processing or inspection on an OmniParcel cross-border shipment. |
| `Customs cleared` / `Received by Last Mile Provider` / `Received by carrier` | Cleared customs and transferred to the local destination hub or final-mile courier. |
| `Shipment is Out for Delivery` / `Driver on site Delivery Location` / `The order is on the way to the recipient.` | Out with the delivery driver today, or the driver has arrived on site. |
| `Awaiting collection` / `Customer to collect from carrier` / `Delivered to locker/collection point` | Waiting for the recipient to pick up from a carrier depot, collection point, or locker. |
| `Attempted delivery` / `Attempted Delivery - Refused by customer` | A delivery attempt did not succeed. |
| `Temporary Origin Hold` / `Temporary Destination Hold` | A hold has paused the shipment at the origin or destination terminal. |
| `Shipment Delivered` / `Delivered - Authority to Leave / Safe Drop` | Delivered to the recipient's address (or left via authority to leave). |
| `Return to sender` | Heading back to the shipper. |

Two phrases on SEKO's main portal are worth noting:

- **`Delivered To Warehouse`** means the freight reached SEKO's destination warehouse, not your home — delivery has not happened yet.
- **`POD Image Attached` and `POD IMAGED`** record proof-of-delivery document filing (sometimes days after delivery) and **`Return Data Received`** records that a return was booked without proving the freight has moved back. Neither changes the physical delivery stage.

## White-glove home delivery, cross-border customs, and delays

SEKO's site lists White Glove & Specialty Delivery and Home Delivery & Last Mile among its services, and secure logistics for electronics and hardware, beside cross-border OmniParcel orders.

- **Home delivery appointments:** `At Delivery Terminal Delivery Appt Set` and `On Hand DEST Delv Appt Set` mean a delivery appointment is set while the shipment waits at the delivering terminal. SEKO's page often posts either an `Expected Arrival` date or an `Estimated delivery between` date range while the shipment is in transit.
- **Cross-border OmniParcel handoffs:** On international e-commerce orders (`GX...`), OmniParcel's scans run from the origin warehouse through export, the flight and destination customs to a local last-mile carrier (`Received by Last Mile Provider`). Scans can pause during flights and customs; see the [customs guide](guide:customs) and [when tracking stops updating](guide:tracking-not-updating) if updates pause.
- **Times and repeats:** SEKO's tracking page gives times to the minute without a zone and sometimes lists the same status twice within a minute in different capitals; Peek keeps one and dates SEKO's milestones, which name no place, on Chicago time. OmniParcel's page shows times in UTC+10:00 unless you switch its time-zone dropdown; Peek asks OmniParcel in UTC, so each scan keeps its exact instant. Peek shows every scan on your device's clock.

If your cross-border order uses a separate major courier for another part of the trip, compare tracking with [DHL Express](carrier:dhl-express) or [FedEx](carrier:fedex).

## Tracking SEKO Logistics with Peek

Because SEKO's shipment codes overlap with other carriers and carry no universal prefix, paste your full `sekologistics.com/en/track/?trackingNumbers=...` or `track.omniparcel.com/...` link when you have one. If you paste a bare tracking number — whether `WFY4829173`, `482917360`, or an OmniParcel `GX...` code — choose SEKO Logistics manually via **Change** when adding the parcel or **Change carrier** on the tracking screen.

Peek queries both portals directly through the open-source [universal-parcel-scraper SEKO adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko): `GX` + 17–18 digit numbers automatically route to OmniParcel (requested in UTC, reading scan locations, service name, weight in kilograms, and destination country), while SEKO shipment numbers route to SEKO's tracking page (reading expected arrival date or delivery window, service name, destination country, and deduplicated milestones, while filtering out `POD Image Attached` / `POD IMAGED` bookkeeping rows and discarding signatory names and postcodes).

Peek maps SEKO and OmniParcel milestones into standard stages: **Label created** for data received, created, and driver-at-pickup notices, **Received by carrier** for pickup and origin warehouse intake, **In transit** for hub, flight, transfer, delivery-terminal, warehouse-arrival, customs-cleared, and last-mile handover scans, **At customs** for destination customs inspection, **Out for delivery** for out-for-delivery and driver-on-site scans, **Ready for pickup** for collection-point or locker holds, **Delivery attempted** for failed attempts, **Needs attention** for origin or destination holds, **Delivered** for delivery and safe-drop scans, and **Returned to sender** for returns.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day SEKO's page shows `Shipment is Out for Delivery`, `Out for Delivery` or `Driver on site Delivery Location`, or OmniParcel shows `Out for delivery` or `The order is on the way to the recipient.`, every 2 minutes from 6 a.m. on the newest scan's clock, while SEKO's tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When SEKO doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking SEKO Logistics." After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking SEKO Logistics."

In Peek's alert settings, choose **Every scan**, **Important steps**, or **Delivery only** to tailor your notifications.

## Contacting SEKO Logistics

SEKO publishes regional customer service hotlines directly on its [tracking page](https://www.sekologistics.com/en/track/) and [Contact Us page](https://www.sekologistics.com/en/contact-us/):

- **US Home Delivery Hotline:** (866) 268-5263
- **UK Customer Service:** +44 1784 417120
- **Official online tracking:** [SEKO Track My Shipment](https://www.sekologistics.com/en/track/) (accepts up to 30 tracking numbers at once) and [OmniParcel Tracker](https://track.omniparcel.com/)
- **Online shipment inquiry:** [Contact SEKO Logistics](https://www.sekologistics.com/en/contact-us/) (select the "Track My Shipment" form)
- **Company overview:** [About SEKO Logistics](https://www.sekologistics.com/en/about/) and [sekologistics.com](https://www.sekologistics.com/en/)

Keep your SEKO tracking number at hand when you call or fill in the Track My Shipment form.

## Frequently asked questions

### Why does my `GX` tracking number not work on `sekologistics.com/en/track/`?

Numbers starting with `GX` followed by 17 or 18 digits belong to SEKO's OmniParcel platform and only resolve on [track.omniparcel.com](https://track.omniparcel.com/). In Peek, selecting SEKO Logistics automatically sends `GX` numbers to OmniParcel and standard SEKO numbers to SEKO's main tracker.

### Does "Delivered To Warehouse" mean my order was delivered to the wrong address?

No. On SEKO Logistics tracking, `Delivered To Warehouse` means the shipment arrived at SEKO's destination warehouse prior to final delivery. Wait for `Out for delivery` and `Shipment Delivered` to confirm doorstep delivery.

### Why did Peek ask me to pick a carrier for my SEKO tracking number?

SEKO's numbers (such as 3 letters + 7 digits or 9 digits) fit other carriers too, and Peek never recognises SEKO from a bare number, `GX` numbers included. Selecting SEKO Logistics via **Change** or pasting the official SEKO or OmniParcel tracking link tells Peek exactly where to look.

### How do I schedule a SEKO Logistics home delivery appointment?

Call the US Home Delivery Hotline at `(866) 268-5263` or UK Customer Service at `+44 1784 417120`, or use the Track My Shipment form on [sekologistics.com/en/contact-us](https://www.sekologistics.com/en/contact-us/).

:::sources
- [SEKO Logistics](https://www.sekologistics.com/en/)
- [SEKO Logistics — Track My Shipment](https://www.sekologistics.com/en/track/)
- [SEKO Logistics — About](https://www.sekologistics.com/en/about/)
- [SEKO Logistics — Contact Us](https://www.sekologistics.com/en/contact-us/)
- [SEKO OmniParcel Tracker](https://track.omniparcel.com/)
- [universal-parcel-scraper SEKO adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko)
:::
