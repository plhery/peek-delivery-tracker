---
title: Korea Post tracking: EMS KR codes, 13-digit parcels & help
description: Track a Korea Post (ePOST) parcel: S10 numbers ending in KR, 13-digit domestic codes, EMS & Korean statuses, customs handoffs, delivery times and support.
slug: korea-post-tracking
published: 2026-11-23
updated: 2026-11-23
---

Korea Post (*우정사업본부*, online portal *ePOST* / *인터넷우체국*) is the national postal operator of South Korea under the Ministry of Science and ICT. It operates domestic parcel and registered-mail delivery across South Korea alongside international **EMS** (Express Mail Service to over 100 destinations, plus 70+ countries via EMS Premium), **K-Packet** and international registered airmail/parcels. You can check international shipments on [Korea Post's English EMS tracker](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp), check domestic parcels on [ePOST Domestic Tracking](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp), or paste your tracking number into the box above.

## Korea Post tracking numbers: KR S10 and 13-digit domestic formats

Check your postal counter receipt, Korean online-store order screen or dispatch confirmation email. Korea Post uses two main formats depending on whether the item travels internationally or within South Korea:

| Format (invented) | What it is |
| --- | --- |
| `EM482917367KR` | International **EMS** merchandise shipment (UPU S10 format ending in `KR`; documents typically start with `EE`). |
| `RR482917367KR` | International registered mail or K-Packet item (UPU S10 format: 2 letters + 9 digits with check digit + `KR`). |
| `6482917364058` | 13-digit domestic South Korean registered mail or parcel number (starting with digits `1`–`6`). Shares 13-digit all-numeric shape with [Correos de Chile](carrier:correos-chile), [Delhivery](carrier:delhivery), [Econt](carrier:econt), [Paquetexpress](carrier:paquetexpress) and others. |

On [Korea Post's EMS overview](https://ems.epost.go.kr/front.Introduction01.postal), `EE` prefixes denote EMS documents (not requiring merchandise customs inspection) while `EM` prefixes denote EMS merchandise shipments. For other international postal codes, see [tracking number formats](guide:tracking-number-formats).

## What Korea Post tracking statuses mean

International `KR` items display English UPU/EMS scan rows preserving local wall-clock times as each country's postal operator reports them, whereas 13-digit domestic items display Korean scan labels on Korea Standard Time (`Asia/Seoul`, UTC+9 year-round):

| Korea Post says (international / domestic) | What it means for you | Peek shows |
| --- | --- | --- |
| `운송장출력` *(domestic)* | Shipping waybill printed before post-office intake. | "Label created" |
| `Posting/Collection` / `접수`, `집하완료`, `인수완료` | Accepted at a Korean post office counter or collected by a Korea Post courier. | "Received by carrier" |
| `Arrival at outward office of exchange`, `Ready for dispatch`, `Handed over to a transport company`, `Received by Air carriers`, `Departure from the Airport`, `Arrival at Destination Airport`, `Delivered to Destination Post`, `Arrival at inward office of exchange`, `Departure from inward office of exchange` / `발송`, `도착` | Processed at Korea's international exchange office (such as Incheon), flown abroad, handed to the destination postal operator or moved between domestic sorting centres. | "In transit" |
| `Ready for customs clearance` | Presented to customs at the destination country (or export/import customs check). | "At customs" |
| `배달준비` *(domestic)* | Prepared for final delivery by the local Korean post office courier. | "Out for delivery" |
| `Unsuccessful delivery` | Destination delivery attempt could not be completed. | "Delivery attempted" |
| `Delivery complete` / `배달완료` | Delivered; Korea Post's tracker can name who received it. | "Delivered" |

## Korea Post EMS delivery times, customs and local handoff

According to [Korea Post's EMS Service Overview](https://ems.epost.go.kr/front.Introduction01.postal), [Shipping Conditions by Country](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) and [Expected Delivery Days & Price Calculator](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal):

- **EMS transit times:** for documents (`EE` numbers) posted in Seoul by noon, Korea Post gives next day or 3–4 days to cities with direct flights, such as Tokyo, Hong Kong and Singapore, and 4–6 days elsewhere; its overview gives 2–3 and 3–5 days for documents posted in Seoul in the morning. Add a day for items posted after noon or outside Seoul, or addressed outside the destination's office-of-exchange city; from a mountain or island post office, add the days to reach the International Mail Logistics Center. Merchandise (`EM` numbers) also waits for the destination's customs inspection. The day of posting and public holidays don't count.
- **Customs declarations:** documents and printed matter use CN22 declarations, while merchandise and dutiable items require CN23 declarations (plus a commercial invoice and any required permits for commercial shipments). Prohibited and restricted items are detailed on [ePOST's Prohibited/Restricted Guide](https://ems.epost.go.kr/front.Introduction04.postal).
- **Destination postal handoff:** once your `KR` item reaches your country (`Delivered to Destination Post` / `Arrival at inward office of exchange`), your national postal operator (for example [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl), [Japan Post](carrier:japan-post) or [Australia Post](carrier:australia-post)) handles customs fee collection, doorstep attempts and post-office holding under its own local rules. Track the same `...KR` number on your local post's site too for fuller local delivery options. See [parcels held at customs](guide:customs).

## Korea Post parcel delayed, stuck in transit or missing

- **Quiet gap after `Departure from the Airport`:** international EMS and K-Packet mailbags often show no scan for several days to over a week between leaving South Korea and being scanned into the destination country's inward office of exchange (`Arrival at inward office of exchange`). See [why tracking stops updating](guide:tracking-not-updating).
- **Held at `Ready for customs clearance`:** check your destination postal operator's website with the same `...KR` number to see whether import VAT, customs duty or an unpaid handling fee is holding up release.
- **Lost, damaged or missing items:** for online orders, contact the Korean seller or marketplace first; ePOST takes a trace request (*행방조사청구*) online or in writing, and lists its compensation rules (*손해배상규정*). See [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## Korea Post customer service and contacts

Official contacts published on [ePOST Customer Centre](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm), [ePOST Main Portal](https://www.epost.go.kr/main.retrieveMainPage.comm) and [EMS Service Overview](https://ems.epost.go.kr/front.Introduction01.postal):

- **Korea Post Customer Centre (within South Korea):** `1588-1300` (weekdays `09:00–18:00` KST, closed Saturdays and public holidays; caller pays).
- **From outside South Korea:** `+82-42-609-4295`, same hours.
- **Email:** `help@posa.or.kr`.
- **Online FAQ & inquiries:** [ePOST Customer Centre](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) and the *e-mail EMS Inquiry* option on [trace.epost.go.kr](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp).
- **Address in ePOST's footer:** 1 Jeongbohwa-gil (Bitgaram-dong), Naju, 58323, South Korea.

> Ignore fake text messages claiming your Korea Post parcel cannot be delivered without paying a small fee through an unofficial link. Genuine import charges on an incoming `KR` parcel are collected by your own country's postal service or customs portal, not by random SMS links. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Korea Post parcel with Peek

[Peek](/) automatically recognises any UPU S10 tracking number ending in `KR` (`EM482917367KR` or `RR482917367KR`, digits invented) as Korea Post with high confidence, and also recognises pasted `trace.epost.go.kr` links carrying `POST_CODE=` and `service.epost.go.kr` links carrying `sid1=`. A 13-digit domestic number (`6482917364058`) fits several carriers: Peek names Korea Post among them and asks you to choose. You can also pick it later with "Change carrier" on the parcel screen (searching "South Korea Post" or "ePOST" finds it too), or paste the ePOST link instead.

Peek reads Korea Post's English EMS tracker for `KR` numbers and ePOST's Korean domestic tracker for 13-digit ones. It shows the stage ("Label created", "Received by carrier", "In transit", "At customs", "Out for delivery" on domestic parcels, "Delivery attempted", "Delivered"), the scan wording with its post office or airport, and the delivery time on domestic parcels. Domestic scans are dated on Korean time, and international ones on their place's time zone (New York's for a scan in New York, for example); Peek shows them on your device's clock. The recipient's and signer's names that Korea Post shows stay out of every Peek app, and Peek leaves out the service name, the sender's name and the offices' phone numbers. Korea Post's tracker gives no estimated delivery date, weight or pickup point.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day a domestic parcel shows `배달준비`, every 2 minutes from 6 a.m. Korean time, while Korea Post's own tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When Korea Post's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Korea Post.", with a "Get help from Korea Post" link to ePOST's Customer Centre. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Korea Post."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot clear customs, redirect a parcel or file an EMS inquiry with Korea Post on your behalf.

## Questions about Korea Post tracking

### What do `EE...KR`, `EM...KR` and `RR...KR` mean?

They are 13-character UPU S10 international postal numbers sent from South Korea: `EE` is used for EMS documents, `EM` for EMS merchandise parcels and `RR` (or other `R`/`C`/`L` prefixes) for registered mail, K-Packet and international parcels.

### Why did my Korea Post EMS parcel stop updating after leaving the airport?

Once `Departure from the Airport` appears in South Korea, tracking stays quiet until the destination country's postal operator scans the incoming mailbag at its inward office of exchange (`Arrival at inward office of exchange`). Check the same `...KR` number on your local national post's website as well.

### Does Korea Post deliver internationally itself?

Korea Post handles collection, export sorting and air dispatch out of South Korea, then hands the parcel over (`Delivered to Destination Post`) to your destination country's national postal service for import customs and doorstep delivery.

### How do I contact Korea Post customer service?

Call `1588-1300` within South Korea (Mon–Fri `09:00–18:00` KST, closed Saturdays and holidays), email `help@posa.or.kr`, or use [ePOST Customer Centre](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm).

:::sources
- [Korea Post: English EMS Track/Confirm](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp) – international EMS tracking portal and partner destination posts
- [Korea Post (ePOST): Domestic Tracking](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp) – 13-digit domestic parcel and registered-mail tracking
- [Korea Post (ePOST): EMS Service Overview](https://ems.epost.go.kr/front.Introduction01.postal) – `EE` vs `EM` prefixes, EMS transit timelines, CN22/CN23 customs rules, 100+ EMS / 70+ EMS Premium destinations and `1588-1300`
- [Korea Post (ePOST): Shipping Conditions by Country](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) – country-specific EMS limits and customs notes
- [Korea Post (ePOST): Expected Delivery Days & Price Calculator](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal) – EMS, EMS Premium and parcel delivery calculator
- [Korea Post (ePOST): Prohibited & Restricted Items](https://ems.epost.go.kr/front.Introduction04.postal) – international mailing restrictions
- [Korea Post (ePOST): Customer Centre](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) – `1588-1300`, `help@posa.or.kr`, weekday `09:00–18:00` KST hours and the Naju address (help URL linked by Peek)
- [Korea Post (ePOST): Main Portal](https://www.epost.go.kr/main.retrieveMainPage.comm) – domestic parcel, registered mail, EMS and K-Packet overview, `+82-42-609-4295` for callers abroad
- [Universal Parcel Scraper: korea-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/korea-post) – `KR` S10 and 13-digit detection, `trace.epost.go.kr` / `service.epost.go.kr` link rules, English EMS + Korean domestic status maps, scan clocks, dropped fields and help URL
:::
