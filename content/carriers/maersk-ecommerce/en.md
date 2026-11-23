---
title: Maersk eCommerce tracking: 1M parcel codes & scan status
description: Track a Maersk eCommerce (B2C Europe / InterPost) parcel: 19-character 1M code structure, 70000–77200 scan codes, last-mile handoffs and Maersk support.
slug: maersk-ecommerce-tracking
published: 2026-11-23
updated: 2026-11-23
---

**Maersk eCommerce** (*Maersk Logistics & Services USA Inc.*, incorporating the former *B2C Europe* and *InterPost* parcel platforms) is A.P. Moller–Maersk's domestic and cross-border B2C parcel delivery network ([maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)). Moving more than 80 million parcels a year with more than 75 carriers integrated in North America and Europe, Maersk eCommerce sorts online sellers' parcels in its own facilities, clears B2C parcels through customs on international routes and hands final delivery to local carriers and postal providers, such as [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) and [DPD Austria](carrier:dpd-at). You can paste your 19-character `1M` parcel number into the box above to track both Maersk's facility scans and the relayed local carrier milestones.

## Maersk eCommerce tracking numbers (`1M` format)

Maersk eCommerce issues 19-character alphanumeric parcel numbers beginning with `1M`, followed by a 7-character merchant/client code (containing at least one letter) and 10 digits:

| Format (invented) | What it is |
| --- | --- |
| `1MAB4829C7360514928` | 19-character Maersk eCommerce parcel code (`1M` + 7-char client code + 10 digits). |
| `1MKX7391E4829173605` | Alternative 19-character `1M` parcel number used on North American or European routes. |

Check your retailer's dispatch email or order-tracking link (`maersk.com/tracking/parcel/...`) for the `1M` number. Maersk's tracker can name the last-mile carrier and the number that carrier gave the parcel. See [tracking number formats](guide:tracking-number-formats).

## What Maersk eCommerce tracking statuses mean

Maersk's own scans carry codes from `70000` to `72004`, the last-mile carrier's from `77100`:

| Maersk code & wording | What it means for you | Peek shows |
| --- | --- | --- |
| `70000` `Shipment Manifest Received`, `70001` `Maersk Shipping Label Created` | `70000`: the seller's manifest lists the parcel before Maersk has it. `70001`: Maersk's own label, made once the parcel reaches its network, often minutes before `70002`. | "Label created" |
| `70002` `Parcel Received by Maersk` | Physical parcel received and scanned into a Maersk eCommerce induction facility. | "Received by carrier" |
| `72001` `Arrived at Maersk Facility`, `72002` `Processing at Maersk Facility`, `72004` `Departed from Maersk Facility` | Sorted at a Maersk consolidation hub and dispatched toward the destination region or gateway. | "In transit" |
| `77100` `In-Transit Scan`, `77101` `Enroute to Facility`, `77102` `Arrival at Facility`, `77103` `Processing at Facility`, `77105` `Departed from Facility`, `77106` `Parcel In-transit` | Relayed domestic transit events from the local last-mile carrier (such as OnTrac, Amazon Logistics or DPD). | "In transit" |
| `77203` `Out for Delivery` | Loaded onto the local last-mile driver's vehicle for delivery today. | "Out for delivery" |
| `77200` `Parcel Successfully Delivered` | Delivered to the recipient's address by the last-mile partner. | "Delivered" |

A scan without one of these codes shows "Needs attention" when Maersk flags it as an exception, and otherwise takes its stage from the step it falls under on Maersk's tracker.

## Coverage, returns and cross-border customs

Maersk E-Commerce gives full delivery coverage in 32 countries across North America and Europe, with origin injection points in China and Southeast Asia ([Maersk E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)):

- **Facilities and partners:** in Europe, 8 sorting and cross-docking facilities, injection hubs in London, Taulov and Amsterdam, and more than 40 last-mile partners and postal providers, with home delivery and pick-up points. In North America, sorting facilities and gateways at JFK, LAX and ORD; Standard delivery takes 2–7 days in the US and 1–10 days in Canada, Expedited 2–5 and 1–5.
- **International customs clearance:** Maersk clears B2C parcels through customs for shipping into the UK, the EU and the US. Whether you pay duties and import VAT on arrival depends on whether the seller charged them at checkout. See [parcels held at customs](guide:customs).
- **E-commerce returns:** domestic and international returns by drop-off or home collection for the sellers that offer them, paperless and duty-exempt on some routes.

## Maersk eCommerce parcel delayed, quiet or missing

Because Maersk eCommerce is a B2B parcel consolidator contracted by e-commerce brands, **always contact the online store first** for address changes, missing items, replacements or refunds.

- **Quiet gap during linehaul or partner handoff:** tracking commonly pauses for a few days between `72004` (`Departed from Maersk Facility`) and the first `77100+` last-mile carrier scan while linehaul trucks or flights transfer parcels to the local courier. See [why tracking stops updating](guide:tracking-not-updating).
- **Use the last-mile carrier's number:** once Maersk names the carrier and its number, that carrier's own tracker and help take over. Peek shows "Delivery with" for [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) and [DPD Austria](carrier:dpd-at).
- **Marked delivered or damaged:** inspect around entrances, parcel rooms and neighbours, check the local partner's tracking record, and notify the merchant promptly. See [delivered but not received](guide:delivered-not-received), [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel) and [returned to sender](guide:returned-to-sender).

## Maersk eCommerce support and official portals

Maersk's support page offers a live chat and case management, mostly for its business customers; sellers book through Maersk's MyParcel login for business.

- **Maersk E-Commerce overview & business enquiries:** [maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics).
- **Maersk general support & FAQs:** [maersk.com/support](https://www.maersk.com/support) and [maersk.com](https://www.maersk.com/).
- **Recipient support path:** contact the online retailer where you bought the item (or the handed-off local last-mile carrier once it has scanned the parcel).

> Beware of fake text messages asking you to click an unfamiliar link and pay a small fee to release a "Maersk parcel". Always verify shipment notices through your retailer's order page or local courier's official site. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Maersk eCommerce parcel with Peek

[Peek](/) checks Maersk eCommerce automatically as the sole candidate when you enter a 19-character `1M` parcel number (`1MAB4829C7360514928` or `1MKX7391E4829173605`, invented) and shows "Maersk eCommerce has this parcel." once Maersk's parcel API confirms it; Peek also recognises pasted `maersk.com/tracking/parcel/` links directly. If you ever need to select it manually, tap "Change" while adding or "Change carrier" on the parcel screen (searching "B2C Europe", "InterPost", "Maersk Parcel" or "Maersk E-Delivery" finds it too).

Peek reads Maersk's parcel tracker and shows the stage ("Label created", "Received by carrier", "In transit", "Out for delivery", "Needs attention", "Delivered"), the wording of Maersk's and the last-mile carrier's scans, the town and region, the estimated delivery day when Maersk gives one, the destination country and the delivery time. The status codes and the service name stay out of every Peek app. Maersk states an offset for its own scans and most partners'; a partner scan without one, such as DPD's, is dated on its place's time zone (Vienna's for a scan in Graz), or else on the zone of the scan beside it, and Peek shows them all on your device's clock. Peek keeps one copy of a partner scan the tracker lists twice. When Maersk names [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) or [DPD Austria](carrier:dpd-at) with a number that fits that carrier's own format, Peek checks that it has the parcel: the parcel then shows "Delivery with" and the carrier's name, and lists both numbers. Peek never reads the client's order number, the destination town and postcode or the partner's link.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day Maersk shows `77203` (`Out for Delivery`), every 2 minutes from 6 a.m. on the newest scan's clock, while Maersk's tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When Maersk's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Maersk eCommerce." After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Maersk eCommerce."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot clear customs, reroute parcels or contact Maersk eCommerce or its last-mile partners for you.

## Questions about Maersk eCommerce tracking

### What is Maersk eCommerce (B2C Europe / InterPost)?

Maersk eCommerce is A.P. Moller–Maersk's B2C parcel consolidation and delivery network across North America and Europe (integrating the former B2C Europe and InterPost networks), moving 80M+ parcels a year through 75+ last-mile carrier partners.

### What does a Maersk eCommerce tracking number look like?

Maersk eCommerce parcel numbers are 19 characters long: `1M`, followed by a 7-character alphanumeric client code (with at least one letter) and 10 digits — for example `1MAB4829C7360514928` (invented).

### Who delivers my Maersk eCommerce parcel to my door?

Maersk consolidates and transports the parcel between hubs and then hands final delivery to a regional last-mile partner in your country, such as [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac), [DPD Austria](carrier:dpd-at) or local postal couriers.

### Why does my Maersk eCommerce parcel stay at `70000 Shipment Manifest Received` for days?

`70000` means the seller's manifest lists the parcel before Maersk has it, so nothing moves until the seller hands it over: ask the seller if it stays there. Maersk adds `70001`, its own label, once the parcel reaches its network, often minutes before `70002` (`Parcel Received by Maersk`).

:::sources
- [Maersk: E-Commerce Parcel Delivery Services](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics) – 80M+ annual parcels, 75+ integrated carriers, North America & Europe domestic/international/returns networks
- [Maersk: Official Website](https://www.maersk.com/) – global logistics portal and parcel tracking entry
- [Maersk: Support & FAQs](https://www.maersk.com/support) – official Maersk support portal
- [Universal Parcel Scraper: maersk-ecommerce](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/maersk-ecommerce) – 19-char `1M` format, `maersk.com/tracking/parcel/` link rule, 70000–77200 status codes, ETA and country, and the Amazon/OnTrac/DPD-AT hand-over rules
:::
