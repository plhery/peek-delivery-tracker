---
title: CJ Packet tracking: CJP...YQ codes, handoffs & contacts
description: Track a CJ Packet (CJ Dropshipping) parcel from China: CJP...YQ tracking numbers, summary & relayed local statuses, customs gaps and support email.
slug: cj-packet-tracking
published: 2026-11-23
updated: 2026-11-23
---

CJ Packet (*CJPacket*) is the cross-border parcel shipping and tracking service linked to [CJ Dropshipping](https://cjdropshipping.com/), moving small e-commerce orders, low-cost bulk parcels and fast-express shipments from Chinese fulfilment warehouses to buyers worldwide via international air linehaul and local last-mile delivery partners. You can look up a shipment directly on [cjpacket.com](https://www.cjpacket.com/), or paste your tracking number into the box above.

## CJ Packet tracking numbers: CJP...YQ format

Look for your CJ Packet waybill in your online store's shipping confirmation email or order page. CJ Packet numbers begin with `CJP`, followed by 2 or 3 letters (indicating the shipping line), 10 digits and `YQ` (17 or 18 characters total):

| Format (invented) | What it is |
| --- | --- |
| `CJPOS4829173640YQ` | 17-character CJ Packet waybill (`CJP` + 2 line letters + 10 digits + `YQ`). |
| `CJPAEE4829173640YQ` | 18-character CJ Packet waybill (`CJP` + 3 line letters + 10 digits + `YQ`). |

Once your order lands in the destination country and clears customs, CJ Packet hands it to a local courier or national post — such as [USPS](carrier:usps), [Canada Post](carrier:canada-post), [FedEx](carrier:fedex), [GOFO Spain](carrier:gofo-es) or [CTT Express](carrier:ctt-express) — and relays that carrier's local tracking number and delivery scans inside the `CJP...YQ` history. For other Chinese e-commerce prefixes, see [tracking number formats](guide:tracking-number-formats).

## What CJ Packet tracking statuses mean

CJ Packet pairs an overall summary tab (`0` through `7`) with detailed line-by-line scans from CJ's warehouse, the export forwarder and the destination last-mile courier:

| CJ Packet summary (code) / scan wording | What it means for you | Peek shows |
| --- | --- | --- |
| `Processing` (`0`) / `Label created. Warehouse is processing this order.`, `Shipment info received` | Order packed or label generated at CJ's warehouse before forwarder collection. | "Label created" |
| `Dispatched` (`1`) / `Item has been collected` | Parcel handed over from CJ's warehouse to the cross-border forwarder. | "Received by carrier" |
| `En Route` (`2`), `Arrived Courier Facility` (`3`) / `Item Flight departure`, `Item Flight landing`, `Customs Clearance Completed`, `Delivery to local courier` | Flown from China to the destination country, cleared customs and transferred to the local courier's sorting centre. | "In transit" |
| `Inbound to customs temporary warehouse`, `In clearance processing - Import` | Undergoing destination import customs clearance. | "At customs" |
| `Out for Delivery` (`4`) / `Out for delivery` / `En reparto` | Out with your local delivery driver today. | "Out for delivery" |
| `Available for Pickup` (`5`) | Ready to collect at a local post office or pickup point. | "Ready for pickup" |
| `Unsuccessful Delivery` (`6`) | Delivery could not be completed (for example wrong address or recipient unavailable). | "Needs attention" |
| `Delivered` (`7`) / `Package delivered`, `Delivered, At the door/Yard`, `Entregado` | Delivered by the destination partner (sometimes naming who received it). | "Delivered" |

Note that notices such as `EXPECTED DELIVERY DATE UPDATED` from a destination courier are informational estimates and do not change the parcel's underlying transit stage.

## How CJ Packet cross-border delivery works

On [cjpacket.com](https://www.cjpacket.com/), CJ Packet offers three main cross-border models — *Small-package Transportation*, *Low-cost Bulk Packages* and *Fast Express* — combining partner air lines, customs clearance support and worldwide local couriers.

**Who handles doorstep delivery.** CJ Packet does not drive consumer delivery vans in North America, Europe or Australia; your local postal or express partner controls final doorstep attempts, safe-place placement, pickup-point storage and returns once `Delivery to local courier` appears. Because CJ Packet serves online dropshipping merchants rather than retail consumers, its public website does not publish consumer transit guarantees, doorstep attempt limits or claim deadlines — those follow the online store you bought from. For import VAT, duties and customs holds (including EU import rules on non-EU parcels), see [parcels held at customs](guide:customs).

## CJ Packet parcel stuck, quiet or marked delivered

Because your purchase contract is with the online store (not CJ Dropshipping or CJ Packet), always contact the seller first if your order is overdue, damaged or missing.

- **Quiet pauses after `Label created` or flight departure:** waiting several days while CJ's warehouse packs the item (`Label created. Warehouse is processing this order.`) or while air cargo clears destination customs is common on cross-border small packets. See [why tracking stops updating](guide:tracking-not-updating).
- **Use the last-mile carrier number:** once CJ Packet reveals a local `lastTrackNumber` (for example a USPS, Canada Post or local courier code), checking that partner directly can show pickup addresses or redelivery options.
- **Unsuccessful delivery (`6`) or delivered without arrival:** check with household members, neighbours, building reception and your local courier first, then email the seller right away before card or marketplace dispute deadlines pass. See [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## CJ Packet customer service and contacts

Official contact details published on [cjpacket.com](https://www.cjpacket.com/) and [cjdropshipping.com](https://cjdropshipping.com/):

- **Logistics support email:** `logistics@cjpacket.com` (24/7 customer support listed on [cjpacket.com](https://www.cjpacket.com/)).
- **Tracking portal:** [cjpacket.com](https://www.cjpacket.com/).
- **Dropshipping platform:** [cjdropshipping.com](https://cjdropshipping.com/) (for merchant-side fulfilment support).

> Ignore unsolicited SMS or WhatsApp messages asking you to pay a small card fee on an external link to "deliver" or "clear" a parcel. Verify any genuine import fee on your store's order screen or your national postal operator's official site. See [delivery scam texts](guide:delivery-scam-texts).

## Track a CJ Packet parcel with Peek

[Peek](/) automatically recognises `CJP` + 2 or 3 letters + 10 digits + `YQ` (`CJPOS4829173640YQ` and `CJPAEE4829173640YQ`, digits invented) as CJ Packet with high confidence, and also recognises pasted `cjpacket.com/?trackingNumber=` links. If you ever need to choose CJ Packet manually, tap "Change" while adding or "Change carrier" on the parcel screen (searching "CJPacket" or "CJ Dropshipping" finds it too).

Peek queries CJ Packet's tracking API directly and displays the mapped stage ("Label created", "Received by carrier", "In transit", "At customs", "Out for delivery", "Ready for pickup", "Needs attention", "Delivered"), combined warehouse, forwarder and local-courier scan wording (with courier phone numbers, email addresses and promotional support blurbs stripped out), scan locations, service/line name, destination country, handed-off local delivery carrier and tracking number when matched, signatory name when a delivery scan names who received it, delivery timestamp when time-zone offset data is present, and timestamps with time-zone offsets (or local wall-clock times when no offset is supplied). Peek discards origin country labels, alias numbers, order/merchant references and contact details; CJ Packet's tracker does not supply an estimated delivery date, parcel weight or proof-of-delivery photos.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. local time on the day an `Out for Delivery` scan arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12") and shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking CJ Packet." After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking CJ Packet."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot clear customs, contact CJ Packet or request a refund from your online store.

## Questions about CJ Packet tracking

### What is CJ Packet?

CJ Packet is the cross-border parcel shipping and tracking service used by merchants fulfilling orders through [CJ Dropshipping](https://cjdropshipping.com/) from warehouses in China to buyers worldwide.

### What does a `CJP...YQ` tracking number mean?

Numbers starting with `CJP`, 2 or 3 route letters, 10 digits and `YQ` (such as `CJPOS4829173640YQ` or `CJPAEE4829173640YQ`, digits invented) are CJ Packet cross-border waybills.

### Who delivers a CJ Packet parcel locally?

After flying from China and clearing destination customs, CJ Packet hands the parcel over (`Delivery to local courier`) to a local national post or courier (such as USPS, Canada Post, FedEx or a regional European courier) for doorstep delivery.

### How do I contact CJ Packet customer service?

Contact the online retailer where you placed your order first for refunds or address issues. CJ Packet's published logistics email on [cjpacket.com](https://www.cjpacket.com/) is `logistics@cjpacket.com`.

:::sources
- [CJ Packet: Official Tracking Portal](https://www.cjpacket.com/) – Small-package Transportation, Low-cost Bulk Packages, Fast Express, 24/7 support and `logistics@cjpacket.com`
- [CJ Dropshipping](https://cjdropshipping.com/) – parent e-commerce fulfilment platform
- [Universal Parcel Scraper: cj-packet](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cj-packet) – `CJP[A-Z]{2,3}\d{10}YQ` detection, `cjpacket.com/?trackingNumber=` link rule, summary codes `0`–`7`, relayed local-partner handoff & signatory parsing, offset/local clocks and stripped fields
:::
