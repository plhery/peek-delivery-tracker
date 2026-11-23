---
title: Chukou1 tracking: EC-FirstClass codes, handoffs & help
description: Track a Chukou1 (CK1 / 出口易) cross-border parcel via EC-FirstClass: processing number structure, scan milestones, local carrier handoffs & China contacts.
slug: chukou1-tracking
published: 2026-11-23
updated: 2026-11-23
---

Chukou1 (*CK1*, Chinese *出口易*, operated by *Guangzhou Beifayi Information Technology Co., Ltd.* / *BFE*) is a major Chinese cross-border e-commerce logistics and overseas warehousing operator headquartered in Guangzhou with branches in Shenzhen, Shanghai and Wuhan and processing hubs in Dongguan and Yiwu. Operating overseas fulfilment warehouses in the US, UK, Germany, France, Poland, Canada, Australia, Peru and Argentina alongside dedicated international small-parcel lines (Europe/US super lines, global economy lines, Latin America and TEMU-Y2) for sellers on Amazon, eBay, AliExpress, SHEIN, TikTok Shop, Walmart and Wayfair, Chukou1 tracks parcels through its **EC-FirstClass** platform ([ec.chukou1.com](https://ec.chukou1.com/), supporting up to 100 numbers at once). You can check a shipment there or paste your code into the box above.

## Chukou1 tracking numbers (EC-FirstClass processing format)

Check your marketplace order page or dispatch email for Chukou1's processing number. Chukou1's own numbers encode a 3-letter service prefix, a 6-digit `YYMMDD` order date, a 3- or 4-character merchant code and a 6- or 7-digit serial (18 to 20 characters total):

| Format (invented) | What it is |
| --- | --- |
| `CZN261015ABCD482917` | 19-character Chukou1 processing number (`CZN` + date `261015` + 4-char customer code `ABCD` + 6-digit serial). |
| `CUE261015KLM4829173` | 19-character Chukou1 processing number (`CUE` + date `261015` + 3-char customer code `KLM` + 7-digit serial). |

After export clearance, Chukou1 hands the parcel to a carrier for the rest of the journey (EC-FirstClass names China Post, [USPS](carrier:usps), [DHL](carrier:dhl), [FedEx](carrier:fedex) and [UPS](carrier:ups) among the carriers it works with) and shows both Chukou1's first-leg history and that carrier's relayed scans under your processing number. See [tracking number formats](guide:tracking-number-formats).

## What Chukou1 (EC-FirstClass) tracking statuses mean

Chukou1 pairs an overall shipment status (`InProcess`, `InTransit`, `Delivered`) with first-leg English milestones and relayed destination-carrier scans using each scanning location's local wall-clock time:

| Chukou1 says | What it means for you | Peek shows |
| --- | --- | --- |
| `Shipment information received`, `Carrier received the manifest` | Electronic order received from the seller before physical intake at Chukou1's hub. | "Label created" |
| `Item received by Logistics Centre`, `Carrier received the shipment` | Physical parcel scanned into Chukou1's origin warehouse in China. | "Received by carrier" |
| `Item was sent out from Logistics Centre`, `Transfer to Airport of Origin`, `Departed from Airport of Origin`, `Arrived at Airport of destination`, `Shipment started the customs clearance process at origin` | Moved from hub to origin airport, exported and landed in the destination country. | "In transit" |
| `Customs clearance in progress`, `Shipment started the customs clearance process at destination`, `Shipment is awaiting customs clearance at destination` | Undergoing destination import customs inspection. | "At customs" |
| `Customs declaration completed`, `Turned over to last mile facility`, `Last mile Ascan`, `Arrived at Post Office` | Cleared import customs, transferred to the local last-mile carrier and scanned into its network. | "In transit" |
| `Shipment is out for delivery` / `Out for Delivery` | Loaded for final delivery by the destination carrier. | "Out for delivery" |
| `Shipment is available for pickup` | Ready to collect at a local post office or pickup point. | "Ready for pickup" |
| `Wrong Address`, `Refused`, `Cannot be delivered` | Delivery attempt failed due to address issue, refusal or inability to deliver. | "Delivery attempted" |
| `Return To Sender`, `Delivered To Sender` | Returned back toward or delivered back to the shipper. | "Returned to sender" |
| `Lost` | Exception flagged as lost in transit; contact your seller immediately. | "Needs attention" |
| `Shipment delivered to customer` / `Delivered` | Delivered by the destination partner (with signatory name where recorded). | "Delivered" |

## Cross-border delivery, local handoffs and customs

On [chukou1.com](https://www.chukou1.com/) and [ec.chukou1.com](https://ec.chukou1.com/), Chukou1 operates as a B2B cross-border consolidator and overseas warehouse provider rather than a consumer doorstep delivery fleet.

- **Local partner controls final delivery:** once `Turned over to last mile facility` and `Last mile Ascan` appear, delivery attempts, redirection, safe-place rules and pickup-point holding days follow whichever local postal or courier operator takes over in your country.
- **Customs and import charges:** cross-border shipments into the EU, US, UK, Canada or Australia are subject to destination customs clearance (`Customs clearance in progress`), import VAT and applicable duties (including the €3 EU customs duty up to €150 and €2 EU handling fee on non-EU orders) unless settled by the merchant at checkout. See [parcels held at customs](guide:customs).

## Chukou1 parcel stuck, quiet or missing

Because online merchants hold the shipping account with Chukou1, always contact the seller or marketplace first for refunds, reshipments or address corrections.

- **Quiet gaps between China and destination:** international air transit and customs batches often pause tracking for several days between `Departed from Airport of Origin`, `Arrived at Airport of destination` and `Last mile Ascan`. See [why tracking stops updating](guide:tracking-not-updating).
- **Track with the handed-off local number:** once EC-FirstClass links the destination partner's barcode, checking that local carrier directly often reveals delivery windows or redelivery options.
- **Delivered, damaged or lost:** check with household members, neighbours and the local delivery carrier first, then raise a dispute with the seller before marketplace buyer protection expires. See [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## Chukou1 customer service and contacts

Official contacts published on [Chukou1 Contact Us](https://www.chukou1.com/About/Contact.aspx) and [chukou1.com](https://www.chukou1.com/):

- **China national service hotline:** `4006 908 223`.
- **Complaints line (Mon–Fri):** `13360562736`.
- **Branch offices:** Guangzhou HQ `020-23380666` (Units 2403–04, Tower A1, Hangxin Centre, No. 840 Huangpu Avenue East, Huangpu District, Guangzhou), Shenzhen `0755-28263408`, Shanghai `021-59793099`, Wuhan `027-59083347`.

> Beware of fake text messages asking for a small online payment to "release" a cross-border parcel at customs. Verify any genuine import fee through your marketplace order page or local postal operator's website first. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Chukou1 parcel with Peek

[Peek](/) checks Chukou1 automatically as the sole candidate when you enter a Chukou1 processing number (`CZN261015ABCD482917` or `CUE261015KLM4829173`, invented) and shows "Chukou1 has this parcel." when EC-FirstClass confirms it; Peek also recognises pasted `ec.chukou1.com/track?track_number=` and `chukou1.com/LogistictsTrack.aspx?trackNo=` links for Chukou1 numbers. If you ever need to select Chukou1 manually, tap "Change" while adding or "Change carrier" on the parcel screen (searching "出口易" or "CK1" finds it too).

Peek reads Chukou1's EC-FirstClass tracker directly and shows the stage ("Label created", "Received by carrier", "In transit", "At customs", "Out for delivery", "Ready for pickup", "Delivery attempted", "Needs attention", "Returned to sender", "Delivered"), Chukou1's scans and the destination carrier's relayed ones without phone numbers or the delivering courier's name, their place and the destination country. The destination postcode and the name of whoever signed stay private, and no Peek app shows them. EC-FirstClass gives scan times without a zone: Peek dates each scan on its place's time zone and shows it on your device's clock. When EC-FirstClass names the delivery carrier and its number and Peek recognises that carrier, Peek checks that it has the parcel: the parcel then shows "Delivery with" and the carrier's name, and lists both numbers. Peek leaves out the proof-of-delivery PDF; EC-FirstClass gives no estimated delivery date, weight, service name or delivery time of its own.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day Chukou1 shows an out-for-delivery scan, every 2 minutes from 6 a.m. on the clock of the newest scan's place, while EC-FirstClass answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When EC-FirstClass doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Chukou1.", with a "Get help from Chukou1" link to Chukou1's contact page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Chukou1."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot clear customs, view embedded POD PDFs or contact Chukou1 or your local delivery partner for you.

## Questions about Chukou1 tracking

### What is Chukou1 (CK1 / EC-FirstClass)?

Chukou1 (*出口易*, operated by *Guangzhou Beifayi Information Technology Co., Ltd.*) is a Chinese cross-border e-commerce logistics and overseas warehouse operator whose tracking portal is called **EC-FirstClass** ([ec.chukou1.com](https://ec.chukou1.com/)).

### What does a Chukou1 tracking number look like?

Chukou1 processing numbers have 3 prefix letters, a 6-digit `YYMMDD` date, a 3- or 4-character merchant code and a 6- or 7-digit serial (for example `CZN261015ABCD482917`, invented).

### What does `Last mile Ascan` mean on Chukou1 tracking?

`Last mile Ascan` means your country's local postal or courier partner has performed its first acceptance scan after receiving the parcel from Chukou1's cross-border linehaul.

### How do I contact Chukou1 customer service?

Contact your online retailer first for refunds or address changes. Chukou1's published China hotline is `4006 908 223` (complaints Mon–Fri `13360562736`, Guangzhou HQ `020-23380666`) on [chukou1.com/About/Contact.aspx](https://www.chukou1.com/About/Contact.aspx).

:::sources
- [Chukou1: Official Website](https://www.chukou1.com/) – cross-border small-parcel lines, overseas warehouses (US, UK, DE, FR, PL, CA, AU, PE, AR), FBA first-mile and Guangzhou HQ (*广州市贝法易信息科技有限公司*)
- [Chukou1: Contact Us](https://www.chukou1.com/About/Contact.aspx) – `4006 908 223`, complaints `13360562736`, Guangzhou/Shenzhen/Shanghai/Wuhan branch phones and addresses (help URL linked by Peek)
- [EC-FirstClass (Chukou1 Tracking Platform)](https://ec.chukou1.com/) – 100-number global tracker and the carriers it names (China Post, USPS, DHL, FedEx, UPS)
- [Universal Parcel Scraper: chukou1](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chukou1) – processing-number format, `ec.chukou1.com`/`chukou1.com` link rules, first-leg + partner scan parsing, signatory/postcode/handoff extraction, dropped POD PDF and help URL
:::
