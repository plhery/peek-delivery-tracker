---
title: Thailand Post tracking: TH S10 codes, EMS statuses & help
description: Track a Thailand Post (ไปรษณีย์ไทย) parcel: 13-character TH S10 codes, domestic and EMS scan codes, COD remittance, customs clearance and 1545 Contact Center.
slug: thailand-post-tracking
published: 2026-11-23
updated: 2026-11-23
---

**Thailand Post** (*Thailand Post Co., Ltd.*, Thai *บริษัท ไปรษณีย์ไทย จำกัด*) is the national postal operator of Thailand ([thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en)). Serving domestic mail, registered parcels, EMS, Same Day, heavy LogisPost and cash-on-delivery (COD) across Thailand as well as international [EMS](carrier:ems) World, Registered, ePacket, Courier Post (up to 200 kg) and Courier Post Plus worldwide, Thailand Post provides public tracking at [track.thailandpost.co.th](https://track.thailandpost.co.th/) and international updates via [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update). You can look up a Thai postal shipment there or paste your 13-character `TH` tracking number into the box above.

## Thailand Post tracking numbers (13-character `TH` S10 format)

Tracked domestic and international Thailand Post items use 13-character Universal Postal Union (UPU) S10 codes: two uppercase service letters, 8 serial digits, 1 S10 check digit and the country code `TH`:

| Format (invented) | What it is |
| --- | --- |
| `EE482917367TH` | 13-character Thailand Post [EMS](carrier:ems) express item (`EE` prefix + 9 digits with valid S10 check digit + `TH`). |
| `RR739184627TH` | 13-character Thailand Post registered mail or small packet (`RR` prefix + 9 digits with valid S10 check digit + `TH`). |

Domestic Express/EMS, Registered (`R...TH`), ePacket (`L...TH`) and domestic parcel prefixes (`E`, `R`, `C`, `W`, etc. ending in `TH`) all follow the same 13-character S10 check-digit structure. Untracked ordinary letters without a 13-character barcode cannot be tracked online. See [tracking number formats](guide:tracking-number-formats).

## What Thailand Post tracking statuses mean

Thailand Post's tracker pairs UPU-aligned numeric `statusID` codes with English descriptions (and Thai branch names), covering both Thai domestic scans and relayed international EDI updates from destination postal operators:

| Thailand Post code & wording | What it means for you | Peek shows |
| --- | --- | --- |
| `3` `Posting/Collection` | Accepted at a Thailand Post counter or collected from the sender. | "Received by carrier" |
| `9` `Arrival at outward office of exchange`, `15` `Departure From Outward OE`, `18` `Arrival at inward office of exchange` | Processed through an international office of exchange for export or import. | "In transit" |
| `23`–`27` `In transit`, `Item into sorting center`, `Arrival at post office` | Moving between mail sorting centres and arriving at the delivering local post office. | "In transit" |
| `31` `Item out for physical delivery` | Out with the Thailand Post postal officer for doorstep delivery today. | "Out for delivery" |
| `57` `Contact recipient` | Delivery officer attempted to call the recipient (non-movement activity; keeps the parcel's current stage). | Keeps prior stage |
| `32` `Item arrival at collection point for pick-up (by recipient)` | Deposited at a Thailand Post parcel locker or collection point ready for pickup. | "Ready for pickup" |
| `34` `Scheduled for further delivery attempt today` / `Item held, addressee notified` (group 4) | Delivery attempt unsuccessful (for example addressee absent or customs/COD charges due). | "Delivery attempted" |
| `28` `Return to the origin post office` | Exception flagged and item routing back toward the origin post office. | "Needs attention" |
| `35/L` `The sender received the returned item` | Returned parcel handed back to the original sender. | "Returned to sender" |
| `35/S` `Final delivery` | Delivered to the recipient (may be followed by `36` `Successfully transfer money to seller` on COD orders, which does not alter the delivery time). | "Delivered" |

## Domestic COD, international handoffs and customs

- **International exports (`...TH` abroad):** after `15` (`Departure From Outward OE`), your country's national postal operator — such as [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl) / Deutsche Post or [Australia Post](carrier:australia-post) — handles import customs clearance and local delivery while relaying EDI scans back to Thailand Post.
- **Customs duties & import VAT:** parcels exported from Thailand or imported into Thailand are subject to destination customs rules, import VAT and clearance fees (including EU charges on non-EU orders). If tracking shows `34` (`Item held, addressee notified due to Payment of charges`), check your mailbox or local post office for a customs/payment notice. See [parcels held at customs](guide:customs).
- **Cash on Delivery (COD) inside Thailand:** on domestic COD shipments, Thailand Post posts `35/S` (`Final delivery`) when the recipient receives and pays for the parcel, followed by `36` (`Successfully transfer money to seller`) once funds remit to the seller.

## Thailand Post parcel delayed, held or missing

- **Quiet gap after leaving Thailand:** international airmail dispatches (especially Registered `R...TH` and economy parcels) frequently pause for several days or weeks between `Departure From Outward OE` and `Arrival at inward office of exchange`. Checking the same 13-character `TH` barcode on your destination post's website often reveals extra customs or local delivery details. See [why tracking stops updating](guide:tracking-not-updating).
- **Lost, damaged or returned shipments:** contact the sender or marketplace first and keep your 13-character barcode and receipt. See [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel), [delivered but not received](guide:delivered-not-received) and [returned to sender](guide:returned-to-sender).

## Thailand Post customer service and 1545 Contact Center

Official support channels published on [Thailand Post Contact Us](https://www.thailandpost.co.th/un/contact_thp/un/41) and [thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en):

- **THP Contact Center (Thailand):** **`1545`** (general inquiries, item tracing and service complaints).
- **Official contact & inquiry portal:** [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).
- **Online tracker & international advisories:** [track.thailandpost.co.th](https://track.thailandpost.co.th/) and [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update).

> Beware of fake text messages or LINE messages claiming a Thailand Post parcel needs a small fee paid through an unfamiliar link. Verify any tracking or customs charge directly via [track.thailandpost.co.th](https://track.thailandpost.co.th/) or the THP Contact Center `1545`. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Thailand Post parcel with Peek

[Peek](/) detects Thailand Post automatically as the sole candidate when you enter any valid 13-character `TH` S10 code (`EE482917367TH` or `RR739184627TH`, invented) and recognises pasted `track.thailandpost.co.th/?trackNumber=` tracking links directly. If you ever need to switch carriers manually (for example to compare your destination post's own scans once an export arrives abroad), tap "Change" while adding or "Change carrier" on the parcel screen.

Peek reads Thailand Post's tracker, and its other host when the first fails, and shows the stage ("Received by carrier", "In transit", "Out for delivery", "Ready for pickup", "Delivery attempted", "Needs attention", "Returned to sender", "Delivered"), the scan's English wording, the post office and the delivery time. The status codes set the stage, and no Peek app shows them; the addressee's name and the name of whoever took the parcel stay private. Thailand Post's own scans carry Bangkok's offset. A foreign post's EDI scan holds that office's wall clock, which Thailand Post stores as if it were Bangkok time, so Peek dates it on the office's time zone (Tokyo's for `TOKYO INTERNATIONAL`), a scan without a place on the zone of the scan beside it, and shows them all on your device's clock. Peek leaves out the driver's name, phone numbers, the signature and the masked contact note; Thailand Post gives no estimated delivery day.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day Thailand Post shows `31` (`Item out for physical delivery`), every 2 minutes from 6 a.m. on the newest scan's clock, while Thailand Post's tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When Thailand Post's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Thailand Post.", with a "Get help from Thailand Post" link to Thailand Post's contact page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Thailand Post."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot clear customs, pay COD charges or contact Thailand Post for you.

## Questions about Thailand Post tracking

### What does a Thailand Post tracking number look like?

Thailand Post tracked items use 13-character UPU S10 codes starting with two service letters, followed by 9 digits (ending in a check digit) and `TH` — for example `EE482917367TH` for EMS or `RR739184627TH` for Registered mail (invented).

### What does code `36` (`Successfully transfer money to seller`) mean?

On Thai domestic cash-on-delivery (COD) orders, code `36` confirms Thailand Post transferred the collected COD payment to the seller after final delivery (`35/S`). It is a financial event rather than a parcel movement.

### Why did my Thailand Post parcel stop updating after `Departure From Outward OE`?

The parcel has left Thailand's outward international exchange and is in international air transit or waiting for import intake by the destination country's postal operator.

### How do I contact Thailand Post customer service?

Call the THP Contact Center on **`1545`** in Thailand or use the official contact page at [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).

:::sources
- [Thailand Post: Official Website](https://www.thailandpost.co.th/index.php?page=index&language=en) – *Thailand Post Co., Ltd.*, domestic/international EMS, Registered, Courier Post, LogisPost and COD services
- [Thailand Post: Contact Us & THP Contact Center 1545](https://www.thailandpost.co.th/un/contact_thp/un/41) – official customer support, complaints portal and `1545` hotline (help URL linked by Peek)
- [Thailand Post: Public Tracker](https://track.thailandpost.co.th/) – official online tracking lookup
- [Thailand Post: International Service Advisories](https://sites.google.com/thailandpost.com/interthp/update) – international tracking status and service notices
- [Universal Parcel Scraper: thailand-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/thailand-post) – `TH` S10 check-digit validation, dual-host failover, UPU-aligned `statusID` mapping (`3`–`36`, `57`), Bangkok vs. foreign EDI clocks and signatory extraction
:::
