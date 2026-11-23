---
title: SkyPostal tracking: SKAMZUS & SP codes, statuses & support
description: Track a SkyPostal parcel from the US to Latin America: SKAMZUS and SP...BR codes, Miami hub and customs scan codes (CEN, HTI, DEL) and country support emails.
slug: skypostal-tracking
published: 2026-11-23
updated: 2026-11-23
---

**SkyPostal** is a Miami-headquartered US domestic and cross-border Latin America parcel delivery network founded by Albert P. and A.J. Hernandez ([skypostal.com](https://www.skypostal.com/)). Operating through its main US export hub in Miami, Florida, SkyPostal moves e-commerce parcels for major retailers and marketplaces — including Amazon, Temu and eBay — into Brazil, Mexico, Colombia, Peru, Ecuador, Chile and Costa Rica, clearing export and import customs and handing off final doorstep delivery to regional Latin American partners. You can check a shipment on [tracking.skypostal.com](https://tracking.skypostal.com/) or paste your SkyPostal tracking number or link into the box above.

## SkyPostal tracking numbers (`SKAMZUS` & `SP...BR` formats)

SkyPostal issues several cross-border tracking formats depending on the originating merchant and destination country, most commonly 17-character `SKAMZUS` codes (used heavily for Amazon US exports to Latin America) and 15-character `SP...BR` codes for Brazil:

| Format (invented) | What it is |
| --- | --- |
| `SKAMZUS0482917365` | 17-character SkyPostal Amazon-US export code (`SKAMZUS` + 10 digits). |
| `SP04829173605BR` | 15-character SkyPostal Brazil cross-border parcel code (`SP` + 11 digits + `BR`). |

When selected manually, SkyPostal's tracker also looks up other 8- to 40-character alphanumeric merchant waybills and partner references containing at least one digit. See [tracking number formats](guide:tracking-number-formats).

## What SkyPostal tracking statuses mean

SkyPostal attaches a 3-letter event code to each checkpoint — covering electronic pre-clearance, Miami hub intake, flights, destination customs and relayed local-carrier scans:

| SkyPostal code & wording | What it means for you | Peek shows |
| --- | --- | --- |
| `DRC` (`DATA RECEIVED`), `HCV` (`HS_CODE VALIDATION`), `TIC` (`TAX ID COLLECTED`), `DCO` (`DOCUMENTATION COMPLETE`) | Order data, HS tariff validation and buyer tax ID received before the parcel arrives at SkyPostal's hub. | "Label created" |
| `RUT` (`RECEPTACLE RECEIVED IN US TRANSPORT FACILITY`), `REC` (`RECEIVED`) | Physical parcel received at SkyPostal's Miami export facility. | "Received by carrier" |
| `XRS` (`X-RAY SCAN`), `PLP` (`PROVIDER LABEL PRINTED`), `INC` (`NESTED TO RECEPTACLE`), `PRO`, `MNF`, `FDP` (`FLIGHT DEPARTED`), `ATT`, `ATC` (`ARRIVED IN COUNTRY`), `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) | Processed at Miami, flown out and transferred from the airline to the ground operator in the destination country (not doorstep delivery). | "In transit" |
| `CEN` (`CUSTOMS ENTRY`), `CHE` (`CUSTOMS HOLD AT CONTROL ENTITY`) | Entered destination customs clearance or waiting for release from the airport bonded warehouse. | "At customs" |
| `CCH` (`CUSTOMS CLEARED`), `TTC` (`TENDERED TO LOCAL CARRIER`), `TDP`, `PES` (`PROVIDER ENTRY SCAN`), `INT`, `PRH`, `PDH`, `SLH` (`HOLD RESOLVED`) | Cleared customs, handed to the local Latin American carrier and moving through local transfer hubs. | "In transit" |
| `OFD` (`OUT FOR DELIVERY`) | Out with the destination partner courier for delivery today. | "Out for delivery" |
| `PIK` (`AVAILABLE FOR PICKUP`) | Ready for recipient collection at a local pickup point or post office. | "Ready for pickup" |
| `CNA` (`CONSIGNEE NOT AVAILABLE`) | Delivery attempt failed because no one was available to receive the parcel. | "Delivery attempted" |
| `HTI` (`HOLD FOR PROBLEMATIC TAX ID`), `ICA` (`INCORRECT / INCOMPLETE ADDRESS`), `DRF` (`DELIVERY REFUSED`), `PLR` (`PROVIDER LABEL REJECTED`), `INH` (`ON HOLD`) | Held for a tax ID problem, a wrong or incomplete address, a rejected label or a refusal. | "Needs attention" |
| `DEL` (`DELIVERED`) | Delivered to the recipient by the destination partner. | "Delivered" |

Note that `DTP` (*Delivered from airline to operator*) simply means the airline handed the airmail bag to the destination ground handler — Peek keeps `DTP` at "In transit" rather than marking it delivered. Internal billing events (`CUT`, `PAY`, `FSC`) are left unstaged.

## Latin America customs, tax IDs (`HTI`) and local handoffs

Cross-border shipments moving from the United States into Latin America through SkyPostal rely on strict customs and tax-ID compliance:

- **Buyer tax ID (`TIC` / `HTI`):** SkyPostal's scans record when the buyer's tax ID is collected (`TAX ID COLLECTED`) and when a parcel is held over it (`HOLD FOR PROBLEMATIC TAX ID`). If tracking shows `HTI`, ask the seller, or email SkyPostal's support address for your country with your full tracking number. See [parcels held at customs](guide:customs).
- **Local last-mile delivery:** once `CCH` (*Customs cleared*) and `TTC` (*Tendered to local carrier*) appear, a local carrier delivers the parcel, and SkyPostal's tracker lists its scans with the partner's town.

## SkyPostal parcel delayed, held or missing

- **Quiet gaps between Miami and destination customs:** tracking often pauses between `FDP` (`FLIGHT DEPARTED`), `ATC` (`ARRIVED IN COUNTRY`) and `CCH` (`CUSTOMS CLEARED`) while consolidated shipments clear customs inspection. See [why tracking stops updating](guide:tracking-not-updating).
- **Address errors (`ICA`) or refused deliveries (`DRF`):** contact your online marketplace (Amazon, Temu, eBay, etc.) first for reshipment or refund, and email SkyPostal's dedicated country support address with your full tracking number if the parcel is still held locally. See [delivered but not received](guide:delivered-not-received), [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel) and [returned to sender](guide:returned-to-sender).

## SkyPostal customer service and country support emails

On [SkyPostal Customer Service](https://www.skypostal.com/customer-service), SkyPostal asks final recipients to contact the team **by email** and always include their full tracking number and inquiry details (published response time: **3–5 business days**):

- **Brazil:** `Brazilcs@skypostal.com`
- **Mexico:** `Mexicocs@skypostal.com`
- **Colombia:** `Colombiacs@skypostal.com`
- **Peru:** `Perucs@skypostal.com`
- **Ecuador:** `Ecuadorcs@skypostal.com`
- **All other countries:** `customerservice@skypostal.com`
- **US headquarters:** *SkyPostal*, 2990 NW 75th Ave, Miami, FL 33122, United States ([skypostal.com/about-us](https://www.skypostal.com/about-us)).

> Beware of fake WhatsApp or SMS messages demanding an urgent tax payment through an unfamiliar website to release a Latin America import. Verify any real customs tax directly through your marketplace order page or official national customs/postal portal. See [delivery scam texts](guide:delivery-scam-texts).

## Track a SkyPostal parcel with Peek

[Peek](/) detects SkyPostal automatically as the sole candidate when you enter a `SKAMZUS` number (`SKAMZUS0482917365`, invented) or an `SP...BR` number (`SP04829173605BR`, invented) and shows "SkyPostal has this parcel." once SkyPostal's tracker returns history; Peek also recognises pasted `tracking.skypostal.com/?trck_number=` and `tracking.skypostal.com/single-tracking.aspx?trck_number=` links. For other merchant waybills, tap "Change" while adding or "Change carrier" on the parcel screen and select **SkyPostal** (searching "Sky Postal" finds it too).

Peek reads SkyPostal's tracker and shows the stage ("Label created", "Received by carrier", "In transit", "At customs", "Out for delivery", "Ready for pickup", "Delivery attempted", "Needs attention", "Delivered"), the scan's English wording, and the partner's town or else SkyPostal's airport code. The 3-letter codes set the stage, and no Peek app shows them. SkyPostal gives each scan a clock without an offset: Peek dates it on its place's time zone (New York's for `MIA`, São Paulo's for `GRU`, Bogotá's or Lima's for a scan there), a scan without a place on the zone of the scan beside it, and shows them all on your device's clock. A partner's scans can come twice, from feeds hours apart, so an earlier stage listed after a delivery doesn't undo it. Peek doesn't keep the proof of delivery, the comments or the partner's own number, and no Peek app shows who signed for the parcel. SkyPostal's tracker gives no estimated delivery day, weight or delivery time.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day SkyPostal shows `OFD` (`OUT FOR DELIVERY`), every 2 minutes from 6 a.m. on the newest scan's clock, while SkyPostal's tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When SkyPostal's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking SkyPostal.", with a "Get help from SkyPostal" link to SkyPostal's customer service page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking SkyPostal."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot submit tax IDs, clear Latin American customs or contact SkyPostal for you.

## Questions about SkyPostal tracking

### What does a SkyPostal tracking number look like?

Common SkyPostal codes are 17-character `SKAMZUS` numbers (`SKAMZUS0482917365`, invented) for Amazon US exports and 15-character `SP...BR` numbers (`SP04829173605BR`, invented) for Brazil, though SkyPostal also accepts other 8- to 40-character merchant references.

### What does `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) mean on SkyPostal?

It does not mean your parcel was delivered to your house. `DTP` means the airline handed the consolidated shipment over to the destination airport's ground handler ahead of customs clearance (`CEN`) and local courier handoff (`TTC`).

### What does `HTI` (`HOLD FOR PROBLEMATIC TAX ID`) mean?

Destination customs flagged a missing or mismatched recipient tax ID (such as CPF in Brazil or RFC in Mexico). Contact your retailer and email the country-specific SkyPostal support address listed on [skypostal.com/customer-service](https://www.skypostal.com/customer-service).

### How do I contact SkyPostal customer service?

Email the destination desk (`Brazilcs@`, `Mexicocs@`, `Colombiacs@`, `Perucs@`, `Ecuadorcs@`, or `customerservice@skypostal.com`) with your tracking number; SkyPostal states replies take 3–5 business days.

:::sources
- [SkyPostal: Official Website](https://www.skypostal.com/) – US domestic and Latin America cross-border shipping, Miami FL headquarters
- [SkyPostal: About Us](https://www.skypostal.com/about-us) – company history and Latin America private delivery network
- [SkyPostal: Customer Service](https://www.skypostal.com/customer-service) – recipient support emails by country (Brazil, Mexico, Colombia, Peru, Ecuador, general), Amazon/Temu/eBay partner note and 3–5 business day response time (help URL linked by Peek)
- [SkyPostal: Tracking Portal](https://tracking.skypostal.com/) – public single-parcel tracker
- [Universal Parcel Scraper: skypostal](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/skypostal) – `SKAMZUS` and `SP...BR` detection rules, `tracking.skypostal.com` link rules, 3-letter event code map (`DRC`–`DEL`, `DTP`, `HTI`) and the scan clocks
:::
