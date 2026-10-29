---
title: JS Express tracking: JSE…YQ numbers, scans and who delivers
description: Track a JS Express (急速国际) parcel from China: what a JSE…YQ number looks like, what its scans mean and who delivers it in the US, Canada and beyond.
slug: js-express-tracking
published: 2026-10-30
updated: 2026-10-30
---

JS Express (急速国际, also JS EXP) is a Chinese logistics company for cross-border online shops, run by 急创速供应链管理（苏州）有限公司 in Suzhou. If a shop sent your order with JS Express, its number starts with JSE and ends with YQ: paste it in the box above. JS Express gets the parcel out of China and a courier in your country delivers it: its history shows USPS in the United States and Straightship in Canada.

## JS Express tracking number: what it looks like

JS Express's waybills are JSE, two letters, ten digits and YQ, 17 characters in all. With invented digits:

| Number | What it is |
| --- | --- |
| `JSEAA0000000000YQ` | JSE, two letters, ten digits and YQ: a JS Express waybill. |
| `JSEAA000000000YQ` | Only nine digits: not JS Express's layout. Check for a missing digit. |
| `JXCAA0000000000YQ` | Also ends in YQ, but opens with JXC: a [Juxi Express](carrier:juxi-express) number. |

Other Chinese forwarders use the same layout with their own letters in front: FEI for 17FEIA, WNB for WanbExpress, SFC for SFC, CJP for CJ Packet. JSE marks JS Express. Spaces and lower case don't matter in Peek. JS Express's tracker needs the whole number: part of one finds nothing.

**Where to find it.** The shop passes the number on in its shipping email or on your order page. JS Express says it works closely with Amazon, Walmart, Shopify, eBay, AliExpress, Wish and Shopee. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

**No link to share.** JS Express's tracking pages take the number in a form, so no link opens one parcel: copy the number. The English page, "Express Query", answers straight away; the Chinese one asks for a picture code (验证码) first.

## JS Express tracking statuses explained

JS Express's page lists each scan as a time, a place in 【brackets】 and a wording, newest first, with "Latest" on top. Its wordings are mostly English. In the order a parcel gets them:

| JS Express says | What it means for you | Peek shows |
| --- | --- | --- |
| `Electronic notification of order creation received.` | The shop has sent JS Express the order's details. JS Express doesn't have the parcel yet. | "Label created" |
| `Item has been collected` | JS Express's warehouse in China has the parcel: its first real scan. | "Received by carrier" |
| `Item inbound in sorting center.`, `Item outbound in sorting center.` | Sorted at JS Express in China. | "In transit" |
| `Domestic allocation has begun`, `Under domestic allocation` | Still at JS Express's warehouse in China, after sorting and before it leaves. | "In transit" |
| `Item departed from origin.`, `Arrive at international airport to abroad` | On its way out, then at the airport in China. | "In transit" |
| `Released From Export Customs` | China's export customs are done. Not your country's customs. | "In transit" |
| `DEPART ORIGIN AIRPORT`, `Item Flight departure`, `Initial landing`, `Connecting flights flight takeoff` | The flight has left, sometimes with a stop and a connecting flight. | "In transit" |
| `ARRIVED AT DESTINATION AIRPORT`, `Item Flight landing`, `NOA-Notification of arrival from GHA` | Landed in your country. NOA is the airport's ground handler reporting it. | "In transit" |
| `The goods have been picked up abroad` | The agent in your country has taken the shipment from the airport. Not a new pickup from the seller. | "In transit" |
| `Inbound to customs temporary warehouse` | In a customs warehouse, waiting for import clearance. | "At customs" |
| `Released From Import Customs` | Your country's customs have let it through. | "In transit" |
| `Delivery to local courier` | Handed to the courier that delivers in your country. | "In transit" |
| `到达待取` | JS Express's own status, in Chinese: arrived, awaiting collection. Its place is only the country. | "Ready for pickup" |

**The courier's scans.** After `Delivery to local courier`, the history goes on in the courier's words. In the US, USPS's: `Accepted at USPS Origin Facility` (its first scan, not a new shipment), `Out for Delivery`, `Delivered, Front Door/Porch`. In Canada, Straightship's `Package is received at a Straightship station`, then `Delivered` with the town in brackets.

**Lines that aren't scans.** `SHIPPING INFORMATION RECEIVED`, `SHIPMENT CREATED`, `Data Received`, USPS's `Shipping Label Created, USPS Awaiting Item` and `Package information is created in the system` are a partner's record of the parcel's details, sometimes days before it has the parcel. `Package will be delivered before 9:00pm` is a forecast, not a delivery.

**Times out of order.** The times carry no time zone and mix clocks: Beijing time in China, local time at airports and most couriers, and UTC for Straightship, hours ahead of Canada's clocks. Go by the order of the rows, not the times.

## Who delivers JS Express parcels

**United States.** JS Express's history shows [USPS](carrier:usps) delivering, with USPS's scans up to the delivery. Once USPS has the parcel, USPS takes a Missing Mail search from the sender or the recipient, starting 7 days from mailing.

**Canada.** [Straightship](carrier:straightship), a Canadian courier, delivers some JS Express parcels; its scans show in JS Express's history.

**UK, Ireland, Australia and elsewhere.** JS Express's record can hold the local courier's number, but not its name, and JS Express's page shows neither. Peek names the courier when that number fits one carrier in your country, and asks your country's post about a [postal number](guide:tracking-number-formats) (two letters, nine digits, two letters); more below. If Peek names no courier, ask the seller who delivers.

Delivery attempts, notice cards and pickup points follow the local courier's rules: JS Express publishes none. A `到达待取` row names only the country, so check the courier's tracking or ask the seller where the parcel waits.

## JS Express parcel not arriving or tracking stuck

**"未获取到任何数据,请提供有效单号!"** JS Express's English page answers this, in Chinese, for a number it doesn't know: "no data found, please give a valid waybill number". `No track data for now!` means no scans yet. Peek shows "Waiting for the carrier" for both and keeps checking. Check the number character by character; if nothing comes after a few days, ask the seller whether the parcel was sent.

**Stuck on `Electronic notification of order creation received.`** JS Express hasn't collected the parcel yet. Ask the shop when it was handed over.

**Stuck on `Inbound to customs temporary warehouse`.** It's waiting for your country's customs. If you're asked for import charges, see [parcels held at customs](guide:customs); if it sits there for days, tell the seller. More in [why tracking stops updating](guide:tracking-not-updating) and [tracking a parcel from China](guide:tracking-from-china).

**Nothing after `Delivery to local courier`.** The local courier has it. If Peek shows the courier's number, look it up on that courier's tracking; if not, ask the seller who delivers.

**Delivered, but nothing came.** Ask the local courier first (USPS in the US), then the seller. See [delivered but not received](guide:delivered-not-received).

**Lost, damaged or returned.** The seller is JS Express's customer. Under JS Express's terms, the customer claims in writing within 30 days of JS Express receiving the parcel, and claims for insured parcels through its own account. So ask the shop or marketplace for a refund or a new parcel, and don't wait. A parcel that can't be delivered (nobody home, refused, wrong address, customs) is sent back for a fee if the sender asks, or destroyed. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## JS Express contact number and customer service

As read on 30 October 2026, JS Express's contact page in Chinese lists the following. The English contact page is older and still gives other numbers and addresses.

- **Hotline**: 400-0353-518, a Chinese service number, also given for international queries. JS Express publishes no hours and doesn't say whether it works from abroad.
- **QQ and WeChat**: enterprise QQ 4000353518 and the WeChat official account js-express.
- **Complaints**: a form on the English complaint page, and the same hotline.
- **Offices**: a head office and warehouse in Suzhou, more warehouses across China, and offices of its network in Hong Kong and in Walnut, California. The US office's only phone is the Chinese hotline.

JS Express's claims go through the seller who booked the parcel. For your order, ask the seller; for the delivery, the local courier.

## Track a JS Express parcel with Peek

[Peek](/) recognises a JS Express number by its shape: JSE, two letters, ten digits and YQ, which no other carrier in its catalog uses, and asks JS Express's tracker by it. If all you have is the local courier's number, paste that instead: Peek follows more than 3,500 carriers.

Peek shows the history of JS Express's English page, each scan with its place, and the destination country, which that page leaves out. `到达待取` shows as "Arrived, awaiting collection". Names of who took a delivery and phone numbers are dropped. Peek leaves out the delivery estimate, delivery photos and the shop's order number that JS Express's record can hold, so it shows no expected delivery date. Lines that aren't scans don't change the status.

Peek reads rows in China on Beijing time and Straightship's on UTC, works out the others' clocks from their place or the destination, and shows them all in your device's time zone, newest first.

When the courier's number fits one carrier that works in your country, such as a USPS barcode in the US or a Straightship number in Canada, Peek shows "Delivery with" that carrier, with its number, and asks it too; an international postal number goes to your country's post. Once that carrier shows a scan at least as recent as JS Express's latest, Peek follows the parcel there, showing both numbers and keeping JS Express's scans. Until then it stays with JS Express, whose history often carries the courier's scans too.

Peek checks every 10 minutes by day, every 2 minutes once the parcel is out for delivery, and every hour or two overnight. It can notify you of every scan, important steps only (customs, problems, "Ready for pickup", out for delivery, the delivery and a return) or "Delivery only". It can't contact JS Express or the seller for you.

## Questions about JS Express tracking

### What is JS Express (急速国际)?

A Chinese logistics company for cross-border online sellers, run by 急创速供应链管理（苏州）有限公司. It says it started in Hong Kong in 2010 and opened its Suzhou base in 2011.

### Where can I track a JS Express parcel?

In the box above, or on JS Express's English "Express Query" page at js-exp.com. Its Chinese page asks for a picture code first.

### What does 未获取到任何数据 mean on JS Express tracking?

"No data found": JS Express's tracker doesn't know the number, because of a typo or because JS Express hasn't received it yet. Check it with the seller.

### Who delivers JS Express parcels in my country?

A courier in your country. JS Express's history shows USPS delivering in the United States and Straightship in Canada. Elsewhere, ask the seller which company delivers.

### Who do I contact about a missing JS Express parcel?

The seller you ordered from: it booked the parcel with JS Express and makes the claims. JS Express's hotline, 400-0353-518, is a Chinese number.

:::sources
- [JS Express: 急速国际 (home page)](https://www.js-exp.com/) – operating company
- [JS Express: Introduction](https://www.js-exp.com/EN/index.php?page=6) – Hong Kong 2010, Suzhou 2011, cross-border e-commerce
- [JS Express: 企业介绍 (introduction)](https://www.js-exp.com/index.php?page=6) – Suzhou 2011, branches, platforms
- [JS Express: Express Query](https://www.js-exp.com/EN/index.php?page=19) – English tracker, what it shows, its answers
- [JS Express: 快件查询 (tracking)](https://www.js-exp.com/index.php?page=19) – picture code
- [JS Express: Insurance Claims](https://www.js-exp.com/EN/index.php?page=29) – customer's claims, 30 days, undeliverable parcels
- [JS Express: 投保理赔 (insurance claims)](https://www.js-exp.com/index.php?page=29) – 30 days from JS Express receiving the parcel, claims from the customer's account
- [JS Express: Complaint](https://www.js-exp.com/EN/index.php?page=32) – form, hotline
- [JS Express: Contact Us](https://www.js-exp.com/EN/index.php?page=35) – hotline, QQ, WeChat, older numbers and addresses
- [JS Express: 联系我们 (contact us)](https://www.js-exp.com/index.php?page=35) – hotline for international queries, QQ, WeChat, offices
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – senders or recipients, from 7 days
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shapes, scan wordings and stages, clocks, USPS and Straightship scans, the courier's number, what Peek shows and how it hands a parcel over
:::
