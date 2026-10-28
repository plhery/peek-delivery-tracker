---
title: Huaya Express tracking: HYYT numbers and where they stop
description: Huaya Express (华亚运通) ships online orders from Guangzhou. How to read an HYYT…GZ number and its English scans, and who to ask after the hand-over.
slug: huaya-express-tracking
published: 2026-10-28
updated: 2026-10-28
---

Huaya Express (华亚运通) is the brand on the logo of 华亚运通国际货运代理有限公司 (Huaya Yuntong International Freight Forwarding), a forwarder in Guangzhou, China, that carries parcels abroad for online shops. A Huaya waybill opens with HYYT and closes with GZ. Put it in the box above, or follow the 物流跟踪 (logistics tracking) link in the top menu of hyytgz.com to Huaya's own tracker. Huaya's history ends when it hands the parcel to a carrier in your country, and it never says which carrier that is.

## Huaya Express tracking number: what it looks like

Huaya publishes no number format. The layout Peek knows is HYYT, twelve digits and GZ. Example numbers, all made up:

| Number | What it is |
| --- | --- |
| `HYYT000000000017GZ` | HYYT, twelve digits, GZ: the shape of a Huaya Express waybill. |
| `HYYT00000000017GZ` | Eleven digits: one got lost in copying. Copy it again from the seller's message. |
| `RR000000005CN` | A postal number ending in CN: [China Post](carrier:china-post) tracks it, not Huaya. |

Peek reads the number with or without spaces, in capitals or not.

**Getting the number.** Huaya works for shops: its home page names Shopify stores, Vova, DHgate (敦煌) and Facebook sellers. So the number comes from the shop you ordered from; if it hasn't arrived, see [how to find your tracking number](guide:find-tracking-number). Huaya's tracker also takes the shop's order reference, typed exactly as the shop wrote it, dashes and all.

**A tracking link with an IP address.** Huaya's tracker isn't on hyytgz.com but on a numeric address over plain HTTP. Paste a link to it, with `trackIndex.htm`, `documentCode=` and your number in it, straight into Peek. The 物流跟踪 link in the site's footer opens 17TRACK, a tracking service, instead.

## Huaya Express tracking statuses explained

Huaya's tracker has a Chinese and an English version, and the scan lines Peek knows from it are in English. In the order a parcel gets them:

| Huaya says | What it means for you |
| --- | --- |
| `Package received & scanned at warehouse` | Huaya has the parcel at its Guangzhou warehouse: its first scan. |
| `Package departed from the warehouse` | It has left that warehouse. |
| `Customs export clearance completed` | China has cleared it for export. Your country's import check comes later. |
| `Package handed over to airline` | Handed to the airline for the international leg. |
| `Shipment Announced` | Huaya has notified the carrier that will deliver it in your country. That carrier hasn't received it yet. |
| `Package in transit to destination country` | Travelling to your country. |
| `Package arrived at destination country` | It has reached your country. |
| `Customs import clearance completed, awaiting pickup by local carrier` | Your country's customs have let it through; the local carrier still has to collect it. |
| `It has been handed over to the service provider.` | The local carrier has collected it. Huaya names neither that carrier nor its number, and Peek knows no Huaya line after this one. |

Huaya's times come without a time zone: they're Beijing time. If the tracker has nothing for a number, its 参考号 (reference) column repeats the number and the rest of the row stays blank.

## Who delivers Huaya Express parcels

Huaya's tracking record names no delivering company and no second number. Its website doesn't settle it either: its pages for sellers don't agree with themselves. The UK line's page ends with USPS, and the Australia line's page has the parcel flown to the US for USPS. The carrier that delivers your parcel is the one the seller names, so ask the seller for that carrier and its tracking number. Import charges are covered in [parcels held at customs](guide:customs).

## Huaya Express parcel not arriving or tracking stuck

**Last line `It has been handed over to the service provider.`** Huaya's tracker won't add anything after it. The parcel now travels under the local carrier's own number: ask the seller for the carrier and that number, and track it there. Background in [tracking a parcel from China](guide:tracking-from-china).

**Waiting after the import customs line.** Customs are done and the parcel is waiting for the local carrier to collect it. If several days pass without the hand-over line, tell the seller. See [why tracking stops updating](guide:tracking-not-updating).

**No result.** Check what you typed against the seller's message: HYYT, twelve digits, GZ. If it's right and Huaya still has nothing, ask the seller to confirm it booked the parcel with Huaya.

**Late, lost or returned.** Huaya's claim terms are written for the shops that book with it, not for you. Ask the seller for a new parcel or a refund, and if the seller stops answering, open a dispute on the marketplace you bought from. See [lost parcels](guide:lost-parcel), [returned to sender](guide:returned-to-sender) and [delivered but not received](guide:delivered-not-received).

## Huaya Express contact number and customer service

What hyytgz.com lists, all in Chinese (checked 28 October 2026):

- **Phone**: 18039073131, which the site calls its company service hotline (公司服务热线). It's a Chinese mobile number: add +86 when calling from outside China. No hours or languages are given.
- **Email**: 2065332407@qq.com, a QQ Mail address labelled as the company email (公司邮箱).
- **Form**: the 投诉建议 (complaints and suggestions) page asks for your name, mobile number, email address and message.
- **Location**: Guangzhou's Baiyun district (广州市白云区), as the footer of its pages gives it.

The 关于我们 (about us) and 服务网点 (branches) pages hold nothing but a title, and no page is written for recipients. Start with the shop for anything about your order.

## Track a Huaya Express parcel with Peek

Paste an HYYT…GZ number, or a link to Huaya's tracker with it after `documentCode=`, and [Peek](/) files it under Huaya Express straight away: in Peek's catalog that layout belongs to Huaya alone. A shop's order reference looks like nothing in particular, but Peek follows more than 3,500 carriers: enter the reference, then pick Huaya Express under "Change" (华亚运通 or GZHY bring it up too). For a parcel you've added already, use "Change carrier". Peek then also shows the number Huaya tracks the order under.

Peek gets its updates from Huaya's own tracker: each line in Huaya's own words with its place when it has one, plus the service name and destination country. The warehouse scan shows as "Received by carrier", the later lines above as "In transit". Times are converted from Beijing to your device's time zone, and if Huaya's tracker shows a line whose time hasn't come yet in Beijing, Peek leaves it out until then. Huaya gives no delivery date, so Peek shows none.

Nothing in Huaya's record names the company that delivers, so Peek stays with Huaya's tracker and the history ends at the hand-over line. To follow the rest, add the local carrier's number from the seller as a second parcel.

Peek counts both customs lines as in transit, since they report clearance, not a hold. So only "All tracking updates" alerts you to Huaya's lines; "Important only" (delivery, pickup, customs, missed attempts and returns) and "Delivery day only" stay quiet.

## Questions about Huaya Express tracking

### What is Huaya Express (华亚运通)?

It's the brand of 华亚运通国际货运代理有限公司 (Huaya Yuntong International Freight Forwarding), a Guangzhou forwarder that ships for online shops. Its site says it mainly uses China Post, its own duties-included lines to the US and Europe, lines for sensitive goods and the big four express companies. Its waybills read HYYT, twelve digits, GZ.

### Why has my Huaya tracking stopped at "It has been handed over to the service provider."?

Because Huaya's part ends there: a carrier in your country has the parcel under a number of its own, and Huaya's line doesn't name it. The seller can give you the carrier and the number.

### Who do I contact about a missing Huaya Express parcel?

The shop you bought from: it's Huaya's customer for that parcel, and Huaya's claim terms are written for it. Huaya lists its contact details in Chinese only.

:::sources
- [Huaya Express: 华亚运通国际货运代理有限公司 (home)](http://hyytgz.com/) – company name and logo, shops it serves, main services, the 物流跟踪 links (tracker in Chinese and English, 17TRACK), hotline, company email, Baiyun address
- [Huaya Express: 欧洲双清包税小包 (Europe and UK lines)](http://hyytgz.com/ywfw.php?id=21) – UK lines with US text ending with USPS; claim terms for shippers
- [Huaya Express: 澳大利亚专线小包 (Australia line)](http://hyytgz.com/ywfw.php?id=27) – Australia line flown to the US for USPS; claim terms for shippers
- [Huaya Express: 投诉建议 (complaints and suggestions)](http://hyytgz.com/message.php) – the form's fields
- [Huaya Express: 关于我们 (about us)](http://hyytgz.com/about.php) – no content
- [Huaya Express: 服务网点 (branches)](http://hyytgz.com/outlets.php) – no content
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – English company name, tracker address and links, HYYT shape, scan wordings and meanings, Beijing clock, lines dated after a check, unknown-number answer, search by reference, no delivering carrier named, what Peek shows
:::
