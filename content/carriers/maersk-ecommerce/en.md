---
title: Maersk eCommerce tracking: 1M parcel codes & scan status
description: Track a Maersk eCommerce (B2C Europe / InterPost) parcel: 19-character 1M code structure, 70000–77200 scan codes, last-mile handoffs and Maersk support.
slug: maersk-ecommerce-tracking
published: 2026-11-23
updated: 2026-11-23
---

**Maersk eCommerce** (*Maersk Logistics & Services USA Inc.*, incorporating the former *B2C Europe* and *InterPost* parcel platforms) is A.P. Moller–Maersk's domestic and cross-border B2C parcel delivery network ([maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)). Moving over 80 million parcels a year across more than 75 integrated last-mile partners in North America and Europe, Maersk eCommerce consolidates online-retailer parcels through its own sortation hubs, handles B2C customs clearance on international lanes and hands off final doorstep delivery to regional couriers such as [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac), [USPS](carrier:usps), [DHL](carrier:dhl) and [DPD Austria](carrier:dpd-at). You can paste your 19-character `1M` parcel number into the box above to track both Maersk's facility scans and the relayed local carrier milestones.

## Maersk eCommerce tracking numbers (`1M` format)

Maersk eCommerce issues 19-character alphanumeric parcel numbers beginning with `1M`, followed by a 7-character merchant/client code (containing at least one letter) and 10 digits:

| Format (invented) | What it is |
| --- | --- |
| `1MAB4829C7360514928` | 19-character Maersk eCommerce parcel code (`1M` + 7-char client code + 10 digits). |
| `1MKX7391E4829173605` | Alternative 19-character `1M` parcel number used on North American or European routes. |

Check your retailer's dispatch email or order-tracking link (`maersk.com/tracking/parcel/...`) for the `1M` number. Once Maersk injects the parcel into a local last-mile carrier, it also attaches that partner's domestic tracking code alongside the original `1M` reference. See [tracking number formats](guide:tracking-number-formats).

## What Maersk eCommerce tracking statuses mean

Maersk eCommerce numbers its own hub events in the `70000`–`72999` range and relayed last-mile carrier scans from `77000` upwards:

| Maersk code & wording | What it means for you | Peek shows |
| --- | --- | --- |
| `70000` `Shipment Manifest Received`, `70001` `Maersk Shipping Label Created` | Retailer transmitted shipment data or Maersk generated its internal parcel label before or at network intake. | "Label created" |
| `70002` `Parcel Received by Maersk` | Physical parcel received and scanned into a Maersk eCommerce induction facility. | "Received by carrier" |
| `72001` `Arrived at Maersk Facility`, `72002` `Processing at Maersk Facility`, `72004` `Departed from Maersk Facility` | Sorted at a Maersk consolidation hub and dispatched toward the destination region or gateway. | "In transit" |
| `77100` `In-Transit Scan`, `77101` `Enroute to Facility`, `77102` `Arrival at Facility`, `77103` `Processing at Facility`, `77105` `Departed from Facility`, `77106` `Parcel In-transit` | Relayed domestic transit events from the local last-mile carrier (such as OnTrac, Amazon Logistics or DPD). | "In transit" |
| `77203` `Out for Delivery` | Loaded onto the local last-mile driver's vehicle for delivery today. | "Out for delivery" |
| `77200` `Parcel Successfully Delivered` | Delivered to the recipient's address by the last-mile partner. | "Delivered" |

Exception-flagged events from Maersk or a partner courier are marked as `"Needs attention"`. Because Maersk frequently relays partner scans without repeating hub details, watching for the linked local-carrier tracking number gives you access to doorstep delivery photos or redelivery controls directly with that courier.

## Coverage, returns and cross-border customs

On [Maersk E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics), Maersk serves merchants across 22 major North American and European markets — including the United States, Canada, the UK, Germany, France, Spain, Italy, the Netherlands, Belgium, Austria, Switzerland, Poland, Portugal, Sweden, Denmark, Norway, Finland, Ireland, Czechia, Slovakia, Hungary and Luxembourg:

- **Multi-partner induction:** Maersk picks up consolidated merchant volumes, sorts them at regional facilities and injects parcels into local delivery networks rather than running branded consumer vans everywhere.
- **International customs clearance:** cross-border shipments into the EU, UK, Canada or US pass through B2C customs clearance; duties and import VAT (including EU non-EU import charges) depend on whether the merchant shipped DDP (delivered duty paid) or DDU. See [parcels held at customs](guide:customs).
- **E-commerce returns:** Maersk also runs domestic and international drop-off returns networks for participating online brands, using separate return labels supplied by the retailer.

## Maersk eCommerce parcel delayed, quiet or missing

Because Maersk eCommerce is a B2B parcel consolidator contracted by e-commerce brands, **always contact the online store first** for address changes, missing items, replacements or refunds.

- **Quiet gap during linehaul or partner handoff:** tracking commonly pauses for a few days between `72004` (`Departed from Maersk Facility`) and the first `77100+` last-mile carrier scan while linehaul trucks or flights transfer parcels to the local courier. See [why tracking stops updating](guide:tracking-not-updating).
- **Use the handed-off carrier code:** once Peek or Maersk links the local delivery carrier's tracking number (for example an [OnTrac](carrier:ontrac), [Amazon Logistics](carrier:amazon-logistics) or [DPD Austria](carrier:dpd-at) code), checking that partner directly often shows delivery photos, locker codes or redelivery scheduling.
- **Marked delivered or damaged:** inspect around entrances, parcel rooms and neighbours, check the local partner's tracking record, and notify the merchant promptly. See [delivered but not received](guide:delivered-not-received), [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel) and [returned to sender](guide:returned-to-sender).

## Maersk eCommerce support and official portals

Maersk eCommerce does not operate a public consumer call centre for individual B2C parcel edits; retailers manage bookings via MyParcel and Maersk's regional teams.

- **Maersk E-Commerce overview & business enquiries:** [maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics).
- **Maersk general support & FAQs:** [maersk.com/support](https://www.maersk.com/support) and [maersk.com](https://www.maersk.com/).
- **Recipient support path:** contact the online retailer where you bought the item (or the handed-off local last-mile carrier once it has scanned the parcel).

> Beware of fake text messages asking you to click an unfamiliar link and pay a small fee to release a "Maersk parcel". Always verify shipment notices through your retailer's order page or local courier's official site. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Maersk eCommerce parcel with Peek

[Peek](/) checks Maersk eCommerce automatically as the sole candidate when you enter a 19-character `1M` parcel number (`1MAB4829C7360514928` or `1MKX7391E4829173605`, invented) and shows `"Maersk eCommerce has this parcel."` once Maersk's parcel API confirms it; Peek also recognises pasted `maersk.com/tracking/parcel/` links directly. If you ever need to select it manually, tap "Change" while adding or "Change carrier" on the parcel screen (searching "B2C Europe", "InterPost", "Maersk Parcel" or "Maersk E-Delivery" finds it too).

Peek queries Maersk's public e-commerce parcel API directly and displays the mapped stage ("Label created", "Received by carrier", "In transit", "Out for delivery", "Needs attention", "Delivered"), Maersk and relayed last-mile scan descriptions and 5-digit status codes, city/region locations, estimated delivery date when Maersk publishes one, service name, destination country, delivery timestamp when delivered, automatic handoff to supported local carriers ([Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) and [DPD Austria](carrier:dpd-at)) when Maersk names them, and exact timestamped instants where an offset is reported (falling back to local wall-clock time when a partner omits its offset). Peek deduplicates identical partner scans reported twice and omits client order numbers, recipient street/postcode and raw partner links.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. local time on the day an out-for-delivery scan arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12") and shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Maersk eCommerce." After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Maersk eCommerce."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot clear customs, reroute parcels or contact Maersk eCommerce or its last-mile partners for you.

## Questions about Maersk eCommerce tracking

### What is Maersk eCommerce (B2C Europe / InterPost)?

Maersk eCommerce is A.P. Moller–Maersk's B2C parcel consolidation and delivery network across North America and Europe (integrating the former B2C Europe and InterPost networks), moving 80M+ parcels a year through 75+ last-mile carrier partners.

### What does a Maersk eCommerce tracking number look like?

Maersk eCommerce parcel numbers are 19 characters long: `1M`, followed by a 7-character alphanumeric client code (with at least one letter) and 10 digits — for example `1MAB4829C7360514928` (invented).

### Who delivers my Maersk eCommerce parcel to my door?

Maersk consolidates and transports the parcel between hubs and then hands final delivery to a regional last-mile partner in your country, such as [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac), [DPD Austria](carrier:dpd-at) or local postal couriers.

### Why does my Maersk eCommerce parcel say `70001 Maersk Shipping Label Created` for days?

`70000` and `70001` mean the merchant created the shipment electronically before or right around handover; transit scans begin once Maersk scans `70002` (`Parcel Received by Maersk`) at its induction hub.

:::sources
- [Maersk: E-Commerce Parcel Delivery Services](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics) – 80M+ annual parcels, 75+ integrated carriers, North America & Europe domestic/international/returns networks
- [Maersk: Official Website](https://www.maersk.com/) – global logistics portal and parcel tracking entry
- [Maersk: Support & FAQs](https://www.maersk.com/support) – official Maersk support portal
- [Universal Parcel Scraper: maersk-ecommerce](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/maersk-ecommerce) – 19-char `1M` format, `maersk.com/tracking/parcel/` link rule, 70000–77200 status codes, ETA/service/country extraction and Amazon/OnTrac/DPD-AT handoff rules
:::
