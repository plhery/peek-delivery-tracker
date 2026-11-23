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

After export clearance and arrival abroad, Chukou1 transfers the parcel to a local delivery carrier — such as [USPS](carrier:usps), [DHL](carrier:dhl), [FedEx](carrier:fedex), [UPS](carrier:ups), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), DPD, [Australia Post](carrier:australia-post) or China Post — and displays both Chukou1's first-leg history and the local carrier's relayed feed under your processing number. See [tracking number formats](guide:tracking-number-formats).

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

Peek queries Chukou1's EC-FirstClass tracking API directly and displays the mapped stage ("Label created", "Received by carrier", "In transit", "At customs", "Out for delivery", "Ready for pickup", "Delivery attempted", "Needs attention", "Returned to sender", "Delivered"), combined Chukou1 and relayed destination-carrier scans (scrubbing phone numbers and courier personal contact lines), location, destination country, destination postcode, handed-off local delivery carrier and tracking number when matched, signatory name when a delivery scan names who signed, and local wall-clock timestamps as reported by each facility. Peek discards embedded PDF proof-of-delivery photos; EC-FirstClass does not provide an estimated delivery date, weight, service name, separate delivery timestamp or time-zone offset.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. local time on the day an out-for-delivery scan arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12"), shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Chukou1." and displays "Get help from Chukou1", which opens Chukou1's contact page. After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Chukou1."

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
- [EC-FirstClass (Chukou1 Tracking Platform)](https://ec.chukou1.com/) – 100-number global tracker and partner carriers (USPS, DHL, FedEx, UPS, China Post, Royal Mail, La Poste, GLS, DPD, Australia Post)
- [Universal Parcel Scraper: chukou1](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chukou1) – processing-number format, `ec.chukou1.com`/`chukou1.com` link rules, first-leg + partner scan parsing, signatory/postcode/handoff extraction, dropped POD PDF and help URL
:::
