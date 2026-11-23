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
| `HTI` (`HOLD FOR PROBLEMATIC TAX ID`), `ICA` (`INCORRECT / INCOMPLETE ADDRESS`), `DRF` (`DELIVERY REFUSED`), `PLR` (`PROVIDER LABEL REJECTED`), `INH` (`ON HOLD`) | Shipment held due to invalid tax ID (CPF/RFC/RUT/DNI), bad address, label issue or refusal — action required. | "Needs attention" |
| `DEL` (`DELIVERED`) | Delivered to the recipient by the destination partner. | "Delivered" |

Note that `DTP` (*Delivered from airline to operator*) simply means the airline handed the airmail bag to the destination ground handler — Peek keeps `DTP` in `"In transit"` rather than marking it delivered. Internal billing events (`CUT`, `PAY`, `FSC`) are left unstaged.

## Latin America customs, tax IDs (`HTI`) and local handoffs

Cross-border shipments moving from the United States into Latin America through SkyPostal rely on strict customs and tax-ID compliance:

- **Buyer tax ID (`TIC` / `HTI`):** customs authorities in Brazil (CPF/CNPJ), Mexico (RFC/CURP), Peru (DNI/RUC), Colombia (CC/NIT), Ecuador (Cédula/RUC) and Chile (RUT) require an accurate recipient tax ID matching the recipient's name. If tracking displays `HTI` (*Hold for problematic tax ID*), update your tax ID with the retailer or email SkyPostal's country support desk promptly. See [parcels held at customs](guide:customs).
- **Local last-mile delivery:** once `CCH` (*Customs cleared*) and `TTC` (*Tendered to local carrier*) appear, local postal or private couriers (such as [Correos de México](carrier:correos-de-mexico), [Estafeta](carrier:estafeta), [Serpost](carrier:serpost), [Jadlog](carrier:jadlog) or regional Brazilian/Colombian couriers) execute final delivery and relay scans back to SkyPostal.

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

[Peek](/) detects SkyPostal automatically as the sole candidate when you enter a `SKAMZUS` number (`SKAMZUS0482917365`, invented) or an `SP...BR` number (`SP04829173605BR`, invented) and confirms `"SkyPostal has this parcel."` once SkyPostal's tracker returns history; Peek also recognises pasted `tracking.skypostal.com/?trck_number=` and `tracking.skypostal.com/single-tracking.aspx?trck_number=` links. For other merchant waybills, tap "Change" while adding or "Change carrier" on the parcel screen and select **SkyPostal** (searching "Sky Postal" finds it too).

Peek queries SkyPostal's tracking gateway directly and displays the mapped stage ("Label created", "Received by carrier", "In transit", "At customs", "Out for delivery", "Ready for pickup", "Delivery attempted", "Needs attention", "Delivered"), English scan descriptions and 3-letter codes, partner town or airport IATA station codes, signatory name when a delivery comment states who signed, and local wall-clock timestamps as reported by SkyPostal (without a time-zone offset, keeping deliveries intact even when duplicate partner feeds arrive with shifted clocks). Peek omits proof-of-delivery attachments, raw comments and internal partner references; SkyPostal's public tracker does not return an estimated delivery date, weight or separate delivery timestamp.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. local time on the day an out-for-delivery scan arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12"), shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking SkyPostal." and displays "Get help from SkyPostal", which opens SkyPostal's customer service page. After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking SkyPostal."

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
- [Universal Parcel Scraper: skypostal](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/skypostal) – `SKAMZUS` and `SP...BR` detection rules, `tracking.skypostal.com` link rules, 3-letter event code map (`DRC`–`DEL`, `DTP`, `HTI`), signer extraction and local wall clocks
:::
