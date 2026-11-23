---
title: EMS tracking: S10 item numbers, statuses & postal help
description: Track an international EMS (Express Mail Service) parcel: 13-character E-prefix S10 numbers, UPU customs and delivery scan meanings, delays and local post help.
slug: ems-tracking
published: 2026-11-23
updated: 2026-11-23
---

**EMS** (*Express Mail Service*) is the international postal express service for documents and merchandise. The **Universal Postal Union (UPU)** created the **EMS Cooperative** ([ems.post](https://www.ems.post/en)) to develop it; the Cooperative counted 179 members in June 2026. Rather than operating its own aircraft or vans, EMS connects designated national postal operators worldwide — such as [Japan Post](carrier:japan-post), [China Post](carrier:china-post), [Korea Post](carrier:korea-post), [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [Poste Italiane](carrier:poste-italiane) and [Australia Post](carrier:australia-post) — so express documents and merchandise move under one standardized 13-character identifier. You can look up an item on the global EMS tracker at [items.ems.post](https://items.ems.post/) or paste your EMS item number into the box above.

## EMS tracking numbers (13-character UPU S10 format)

Every EMS shipment carries a 13-character UPU S10 barcode starting with the letter **`E`** (for Express), followed by a second service letter, 8 serial digits, 1 S10 check digit and the 2-letter ISO code of the origin country:

| Format (invented) | What it is |
| --- | --- |
| `EA482917367JP` | 13-character EMS item dispatched from Japan (`EA` prefix + 9 digits with valid S10 check digit + `JP`). |
| `EE739184627CN` | 13-character EMS item dispatched from China (`EE` prefix + 9 digits with valid S10 check digit + `CN`). |

Notice the last two letters always identify the **origin postal operator**, not the destination country. A parcel sent via EMS from Tokyo to Paris ends in `JP` for its entire journey, even after [La Poste](carrier:la-poste) takes over final delivery in France. Non-`E` postal codes (such as `RR...` registered mail or `CP...` standard parcels) are rejected by the EMS tracker as not denoting an EMS item. See [tracking number formats](guide:tracking-number-formats).

## What EMS tracking statuses mean

The EMS Cooperative public tracking table at [items.ems.post](https://items.ems.post/) translates participating postal operators' EDI events into standardized English descriptions:

| EMS says | What it means for you | Peek shows |
| --- | --- | --- |
| `Posted` | Accepted over the counter or collected by the origin postal operator. | "Received by carrier" |
| `Arrived at export office`, `Departed from export office` | Processed at the origin country's international office of exchange and dispatched abroad. | "In transit" |
| `Presented to export customs and security`, `Held for export customs inspection` | Undergoing export customs or aviation security screening in the origin country. | "At customs" |
| `Released from export customs and security` | Cleared export screening and ready for international dispatch. | "In transit" |
| `Export cancelled` | Export dispatch was cancelled or returned prior to leaving the origin country; contact the sender. | "Needs attention" |
| `Arrived at destination import office`, `Departed from destination import office` | Landed in the destination country's international exchange centre or left it for domestic sorting. | "In transit" |
| `Held at destination import office`, `Presented to import customs` | Held at the border exchange office or presented to destination import customs. | "At customs" |
| `Released from import customs` | Cleared destination customs and handed to the domestic postal network. | "In transit" |
| `Arrived at sorting center`, `Arrived at post office` | Moving through the destination post's domestic hubs or reached the local delivery post office. | "In transit" |
| `Out for delivery` | Out with the local postal carrier or EMS courier for final delivery. | "Out for delivery" |
| `Delivery attempted` | Delivery was attempted without success; check for a missed-delivery card or local post office notice. | "Delivery attempted" |
| `Returned to sender` | Returned back toward the origin country after failed delivery, refusal or customs rejection. | "Returned to sender" |
| `Delivered` | Delivered to the recipient or authorized collection point by the destination operator. | "Delivered" |

## How international handoffs and customs work on EMS

Because EMS is a cooperative network of national posts listed in the [EMS Operators directory](https://www.ems.post/en/global-network/ems-operators), two different postal organizations handle every cross-border item:

- **Origin post vs. destination post:** the origin postal operator handles collection, export clearance and international dispatch; once `Arrived at destination import office` appears, your own national postal operator (or its express arm, such as Parcelforce in the UK) controls import clearance, delivery attempts, redelivery and post-office collection holding periods.
- **Check both EMS and your local post:** the global EMS feed relays standardized EDI milestones, while your destination post's own tracking page often adds richer domestic details such as customs payment links, pickup-office addresses, delivery windows or redelivery scheduling.
- **Customs duties and import VAT:** cross-border EMS parcels are subject to destination customs rules, import VAT, postal clearance fees and customs duties (including EU non-EU import charges). If tracking pauses on `Presented to import customs` or `Held at destination import office`, watch for an official letter, email or portal notice from your local post. See [parcels held at customs](guide:customs).

## EMS parcel delayed, stuck or missing

- **Silent gap between export and import:** international airmail dispatches often go several days without a scan between `Departed from export office` and `Arrived at destination import office`, especially during peak seasons or when waiting for customs intake. See [why tracking stops updating](guide:tracking-not-updating).
- **Your local post helps, not the UPU:** as stated on [EMS Tracking](https://www.ems.post/en/global-network/tracking), the EMS Unit and the UPU do not provide tracking by phone and do not intervene in service disputes between postal operators and customers. For a missing EMS item, EMS's [contacts page](https://www.ems.post/en/about-us/contacts-0) sends you to your local EMS customer service or call centre, which can answer general EMS queries, find your tracking number and help locate the item. See [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel), [delivered but not received](guide:delivered-not-received) and [returned to sender](guide:returned-to-sender).

## EMS customer service and official operator directory

Because each country's EMS service is operated by its local designated post, [EMS Contact Us](https://www.ems.post/en/about-us/contacts-0) publishes a searchable A–Z directory of national EMS customer service pages and call centres filtered by region (Africa, Arab, Asia Pacific, Caribbean, Europe, Latin America and North America):

- **Find your local EMS operator's phone and help desk:** [ems.post/en/about-us/contacts-0](https://www.ems.post/en/about-us/contacts-0) and [ems.post/en/global-network/ems-operators](https://www.ems.post/en/global-network/ems-operators).
- **Global public tracker:** [items.ems.post](https://items.ems.post/) (instructions at [ems.post/en/global-network/tracking](https://www.ems.post/en/global-network/tracking)).
- **No central UPU phone support:** neither the EMS Unit nor the Universal Postal Union resolves individual parcel inquiries by telephone.

> Beware of fake text messages claiming an "EMS package" is blocked until you pay a small fee through an unfamiliar link. Always verify customs fees directly on your national postal operator's official website using your 13-character EMS code. See [delivery scam texts](guide:delivery-scam-texts).

## Track an EMS parcel with Peek

When you enter a 13-character `E`-prefixed S10 code (`EA482917367JP` or `EE739184627CN`, invented) into [Peek](/), Peek normally routes it first to the origin postal operator identified by the final two letters (such as [Japan Post](carrier:japan-post) or [China Post](carrier:china-post)), because EMS itself has no standalone auto-detection rule. To query the global **EMS** Cooperative tracker instead, tap "Change" while adding or "Change carrier" on the parcel screen and select **EMS** (searching "Express Mail Service" or "EMS Post" finds it too). Peek also recognises pasted `items.ems.post/api/publicTracking/track?itemId=` tracking links directly as EMS.

Peek reads `items.ems.post` directly, once it has checked that the code is an `E` S10 number with a valid check digit, and shows the stage ("Received by carrier", "In transit", "At customs", "Out for delivery", "Delivery attempted", "Needs attention", "Returned to sender", "Delivered"), the English EMS wording and the office. EMS gives scan times without a zone: Peek dates each scan on its office's time zone (Tokyo's for `TOKYO INTERNATIONAL`, Paris's for `ROISSY CDG`), and a scan without a place on the zone of the scan beside it, and shows them on your device's clock. The EMS table gives no estimated delivery date, weight, signer's name or separate delivery time.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day EMS shows `Out for delivery`, every 2 minutes from 6 a.m. on the clock of the newest scan's place, while the EMS tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When the EMS tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking EMS.", with a "Get help from EMS" link to EMS's contact directory. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking EMS."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot clear customs, pay import charges or contact your local postal operator for you.

## Questions about EMS tracking

### What does a valid EMS tracking number look like?

An EMS tracking number is a 13-character UPU S10 code starting with `E`, a second letter, 9 digits (ending in a check digit) and the 2-letter ISO code of the sending country — for example `EA482917367JP` or `EE739184627CN` (invented).

### Who delivers my EMS parcel in my country?

EMS parcels are delivered by your country's designated national postal operator (or its express arm), such as [USPS](carrier:usps) in the United States, [Royal Mail](carrier:royal-mail) / Parcelforce in the UK, [La Poste](carrier:la-poste) in France, [Poste Italiane](carrier:poste-italiane) in Italy or [Australia Post](carrier:australia-post) in Australia.

### Why did my EMS parcel stop updating after `Departed from export office`?

After `Departed from export office`, the parcel is in international air transit or waiting in line at the destination country's international mail exchange. Tracking resumes once the destination post scans `Arrived at destination import office`.

### Who do I contact if an EMS parcel is stuck or lost?

The UPU and the EMS Unit give no tracking by phone and do not step into disputes between posts and their customers. Use [ems.post/en/about-us/contacts-0](https://www.ems.post/en/about-us/contacts-0) to find your local EMS customer service or call centre, which can help locate your item.

:::sources
- [EMS Cooperative: Official Website](https://www.ems.post/en) – UPU Express Mail Service Cooperative overview and global postal network
- [EMS Cooperative: Tracking Guide](https://www.ems.post/en/global-network/tracking) – 13-digit S10 item format, UPU/EMS Unit non-intervention disclaimer and no phone tracking policy
- [EMS Cooperative: Global Operator Contacts](https://www.ems.post/en/about-us/contacts-0) – A–Z national EMS customer service directory by region (help URL linked by Peek)
- [EMS Cooperative: Operators A–Z](https://www.ems.post/en/global-network/ems-operators) – designated national postal operator network
- [EMS Public Tracking Portal](https://items.ems.post/) – global EMS public tracking lookup
- [Universal Parcel Scraper: ems](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ems) – S10 `E`-prefix check-digit validation, `items.ems.post` link rule, 18-status EMS Cooperative mapping and each scan dated on its office's time zone
:::
