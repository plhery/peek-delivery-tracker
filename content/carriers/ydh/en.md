---
title: YDH tracking: YDH...YQ and AZ codes, handoffs and contacts
description: Track a YDH Express cross-border parcel from China: YDH...YQ and YDH...AZ codes, scan codes like DSD and CARR, local partner handoffs, customs and support.
slug: ydh-tracking
published: 2026-11-23
updated: 2026-11-23
---

YDH (*YDH Express* / *YDH Cross Border*, Chinese *义达跨境*) is a Chinese cross-border e-commerce logistics provider founded in 2005 and headquartered in Shanghai. Serving more than 20 Chinese cross-border e-commerce hubs, Shenzhen, Guangzhou, Hangzhou, Ningbo and Hong Kong among them, with offices in Japan, the United States, the United Kingdom and Belgium, YDH moves small parcels and commercial freight to more than 200 countries and regions before handing them off to local delivery partners. You can look up a shipment on [YDH's tracking portal](https://track.ydhex.com/track_query.aspx), or paste your tracking number into the box above.

## YDH tracking numbers: YQ and AZ formats

Check your online marketplace order details or dispatch email for the waybill number. YDH tracking numbers are 15 characters long, beginning with `YDH`, followed by 10 digits and ending in `YQ` or `AZ`:

| Format (invented) | What it is |
| --- | --- |
| `YDH4829173640YQ` | YDH cross-border parcel number: `YDH` + 10 digits + `YQ`. |
| `YDH4829173640AZ` | YDH cross-border parcel number: `YDH` + 10 digits + `AZ`. |

Once your parcel clears destination customs, YDH hands it to a local carrier: [USPS](carrier:usps) or [GOFO](carrier:gofo) in the United States and [Poste Italiane](carrier:poste-italiane) in Italy are among those whose scans YDH relays under your `YDH` number. YDH can also give the local carrier's own tracking number. For other cross-border number patterns, see [tracking number formats](guide:tracking-number-formats).

## What YDH tracking statuses and codes mean

YDH attaches short milestone codes to its own warehouse, export and airline scans (in Chinese or English) and also relays the destination delivery partner's scans in that partner's own wording.

| YDH code / wording | What it means for you | Peek shows |
| --- | --- | --- |
| `IR` (`Shipment information received`, `Label Created`) | Electronic order received from the seller before YDH scans the physical parcel. | "Label created" |
| `AF` (`Shipment arrived at facility`), `GNZY` (`快件到达作业中心`) | Parcel arrived at YDH's warehouse or sorting hub in China. | "In transit" |
| `HBYP`, `DF`, `DA` (`Shipment departed from airport` / `航班起飞`), `TDDY` (`Flight delay`), `AA` (`Shipment arrived at airport` / `航班到达`) | Queued for air uplift, departed China (or delayed on flight) and landed at the destination airport. | "In transit" |
| `RC` (`Released from customs`), `CARR` (`handed over to the final carrier`), `DSD` (`D-SCAN DELAY`), `PU` | Cleared destination customs, handed to the last-mile carrier, waiting for that partner's first scan (`D-SCAN DELAY`) or picked up by the partner. | "In transit" |
| `OD` (`Out for Delivery`) | Loaded by the destination carrier for final delivery. | "Out for delivery" |
| `DD` | Delivery exception or failed delivery attempt reported by the local partner. | "Delivery attempted" / "Needs attention" |
| `CC` | Delivered by the destination partner (with signatory name when provided). | "Delivered" |

**What `D-SCAN DELAY` (`DSD`) means.** When YDH hands a consolidated batch of parcels to a destination postal or courier partner (`CARR`), several days can pass before that partner scans the individual parcel into its own network. `D-SCAN DELAY` flags that handover lag — the parcel is still on its way rather than lost.

## How YDH cross-border delivery works

On [ydhex.com](https://www.ydhex.com/), YDH names more than 50 airlines and shipping lines it books, and customs clearance in 12 countries. Its [contact page](https://www.ydhex.com/product/59/) lists offices in Japan (Tokyo, Narita, Osaka, Kansai and Nagoya), the United States (La Puente, California), the United Kingdom (Hounslow) and Belgium (Grâce-Hollogne, near Liège).

**Last-mile delivery.** YDH handles export collection, air linehaul and customs clearance, then transfers the parcel (`CARR`) to a local postal or courier operator in your country for final doorstep delivery, pickup-point drop-off or returns. The local carrier sets the delivery attempts, safe places and holding period.

**Customs and import fees.** Cross-border orders shipped through YDH are subject to your country's import VAT, customs duties and any local handling charges unless prepaid by the seller or marketplace at checkout. See [parcels held at customs](guide:customs).

## YDH parcel stuck, quiet or not delivered

Because YDH's transport contract is with the online merchant or marketplace rather than the buyer, always contact the seller or open a platform inquiry first for late deliveries, address fixes or refunds.

- **Quiet gaps during air transit or handover:** pauses of several days are common while parcels wait for flight space (`HBYP`), move through destination customs (`RC`) or await the local partner's first scan (`DSD` / `D-SCAN DELAY`). See [why tracking stops updating](guide:tracking-not-updating).
- **Use the local tracking number after handover:** once YDH shows the local carrier's own number, that carrier's site may offer delivery choices YDH's tracker doesn't.
- **Delivered, damaged or missing:** if tracking shows `CC` / delivered without the parcel arriving, check with household members, neighbours, building reception and the local carrier first, then notify the seller promptly. See [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## YDH customer service and contacts

Official office details published on [YDH's Contact Us page](https://www.ydhex.com/product/59/):

- **Customer service lines in China:** `800-988-1378` / `400-888-1378`.
- **Shanghai customer service:** `(+86) 21-52277600` / `(+86) 21-52277688` (HQ: 28/F, AFC Grand Hongqiao International, No. 999 Li'an Road, Minhang District, Shanghai 201100, China).
- **Hong Kong office:** `+852 2690-1686`, `hk@ydhexpress.com`.
- **Japan office (Tokyo):** `+81 3-5644-5733`.
- **Belgium office (Liège):** `+32 470 588 200`.

> Be wary of unsolicited SMS or email links asking for a small card payment to "clear customs" or "update your address" on a parcel from China. Verify any genuine duty request on your marketplace order page or your local postal operator's official website. See [delivery scam texts](guide:delivery-scam-texts).

## Track a YDH parcel with Peek

[Peek](/) automatically recognises `YDH` + 10 digits + `YQ` or `AZ` (`YDH4829173640YQ` and `YDH4829173640AZ`, digits invented) as YDH with high confidence, and also recognises pasted `track.ydhex.com/track_query.aspx?track_number=` links. If you ever need to select YDH manually, tap "Change" while adding or "Change carrier" on the parcel screen (searching "YDH Express", "YDH Cross Border" or "义达跨境" finds it too).

Peek reads YDH's tracker directly and shows the stage ("Label created", "In transit", "Out for delivery", "Delivery attempted", "Needs attention", "Delivered"), the scan wording, YDH's or the local carrier's, with its place, and the destination country. YDH's codes such as `CARR` and `DSD` set the stage; Peek doesn't show them. YDH dates its scans in China on Shanghai time and the local carrier's on their place's time zone; Peek shows them on your device's clock. When YDH gives the local carrier's own number and Peek recognises that carrier, Peek checks that it has the parcel: the parcel then shows "Delivery with" and the carrier's name, and lists both numbers. The signer's name stays out of every Peek app, and Peek leaves out YDH's customer reference and partner label. YDH's tracker gives no estimated delivery date, weight, service name, pickup point or proof-of-delivery photo.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day YDH shows `OD`, every 2 minutes from 6 a.m. on the clock of YDH's newest scan, while YDH's own tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When YDH's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking YDH.", with a "Get help from YDH" link to YDH's contact page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking YDH."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking only and cannot contact YDH, clear customs or redirect your parcel with the local delivery partner.

## Questions about YDH tracking

### What is YDH Express?

YDH (*义达跨境*) is a Shanghai-based cross-border e-commerce logistics provider founded in 2005 that ships small parcels from China to more than 200 countries and hands them over to local postal or courier partners for final delivery.

### What do `YDH...YQ` and `YDH...AZ` tracking numbers mean?

Both are 15-character YDH Express waybill formats consisting of `YDH`, 10 digits and either `YQ` or `AZ` (for example `YDH4829173640YQ` or `YDH4829173640AZ`, digits invented).

### What does `D-SCAN DELAY` (`DSD`) mean on YDH tracking?

`D-SCAN DELAY` means YDH has transferred the parcel toward the destination country's last-mile carrier, but that partner has not yet posted its first acceptance scan. The shipment remains in transit while waiting for local intake.

### How do I contact YDH customer service?

Contact your online seller or marketplace first for refunds or delivery issues. For carrier inquiries, YDH lists its customer service lines in China (`800-988-1378` / `400-888-1378`), Shanghai customer service (`+86 21-52277600` / `7688`), Hong Kong (`+852 2690-1686`, `hk@ydhexpress.com`), Japan (`+81 3-5644-5733`) and Belgium (`+32 470 588 200`) on [ydhex.com/product/59/](https://www.ydhex.com/product/59/).

:::sources
- [YDH: Official Website](https://www.ydhex.com/) – founded 2005, 20+ Chinese hubs, 200+ countries/regions, 50+ airlines and shipping lines, customs clearance in 12 countries
- [YDH: Contact Us](https://www.ydhex.com/product/59/) – Shanghai headquarters, China hotlines (`800-988-1378`, `400-888-1378`, `+86 21-52277600`/`7688`), Hong Kong, Japan, US, UK and Belgium offices (help URL linked by Peek)
- [YDH: Tracking Portal](https://track.ydhex.com/track_query.aspx) – public YDH parcel tracking lookup
- [Universal Parcel Scraper: ydh](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ydh) – `YDH` + 10 digits + `YQ`/`AZ` detection, `track.ydhex.com` link rule, milestone codes (`IR`, `AF`, `DA`, `AA`, `RC`, `CARR`, `DSD`, `PU`, `OD`, `CC`, `DD`), the local carrier's number, scan clocks and help URL
:::
