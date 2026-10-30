---
title: Shouwu tracking: SWECN…YQ numbers, scans and who delivers
description: Track a Shouwu International (守务国际) parcel from China: what a SWECN…YQ number looks like, what its statuses mean and who delivers it in the UK and US.
slug: shouwu-tracking
published: 2026-10-30
updated: 2026-10-30
---

Shouwu International (守务国际, also SWE) is a Shanghai logistics company that ships parcels for online sellers in China to Europe and the United States, where a post or courier delivers them. If a shop sent your order with Shouwu, its number starts with SWECN and ends with YQ: paste it in the box above or in Shouwu's tracker at [admin.shshouwu.com](https://admin.shshouwu.com/track.html). The shop gives you the number with your order's shipping details.

## Shouwu tracking number: what it looks like

Shouwu's waybills are SWECN, twelve digits and YQ, 19 characters in all. With invented digits:

| Number | What it is |
| --- | --- |
| `SWECN000000000000YQ` | SWECN, twelve digits and YQ: a Shouwu waybill. |
| `SWECN00000000000YQ` | Only eleven digits: not Shouwu's layout. Check for a missing digit. |
| `JSEAA0000000000YQ` | Also ends in YQ, but opens with JSE: a [JS Express](carrier:js-express) number. |

Other Chinese forwarders end their waybills in YQ too, with their own letters in front and, in Peek's catalog, ten digits. SWECN and twelve digits mark Shouwu. Spaces, dashes and lower case don't matter in Peek.

**Where to find it.** If it isn't in the shop's shipping email or on your order page, see [how to find your tracking number](guide:find-tracking-number).

**You may get the courier's number instead.** Shouwu lets the seller choose which number to pass on: Shouwu's own, which follows the parcel end to end, or the number of the courier that delivers in your country. So a Shouwu parcel can reach you under a [Royal Mail](carrier:royal-mail), Yodel or [Evri](carrier:evri) number in the UK, or a [USPS](carrier:usps) number in the US. Paste that all the same: Peek follows more than 3,500 carriers.

**A tracking link.** Shouwu's "Copy Link" button gives a link to its tracker with `?q=` and the number. It opens that parcel, and you can paste the whole link into Peek. Shouwu's tracker takes up to 40 numbers at once, one per line.

## Shouwu tracking statuses explained

Shouwu's tracker is in English under a Chinese heading. It gives each parcel a status and its destination country, then each scan's time and wording, but not its place.

| Shouwu's status | What it means for you | Peek shows |
| --- | --- | --- |
| `Not Found` | Shouwu has nothing under that number yet: "We seem can't identify the number." | "Waiting for the carrier" |
| `Preparing` | Shouwu explains it as a parcel handed to its warehouse in China, or being weighed and sealed there. It can show before the first scan. | "Label created", then "Received by carrier" after a warehouse scan |
| `In Transit` | Anything from the handover to a carrier to customs and the trip inside your country. | "In transit", or the newest scan's stage, such as "At customs"; "Label created" while there is only the order data and `Carrier received the shipment` |
| `Undelivered` | A delivery attempt failed. | "Delivery attempted", or "Ready for pickup" or "Returned to sender" if the newest scan says so |
| `Delivered` | Delivered to the addressee. | "Delivered" |

Shouwu calls its statuses "auto-judged by the system" and "only for information reference use". The scans say more:

| Shouwu says | What it means for you | Peek shows |
| --- | --- | --- |
| `Shipment information received`, `Order information received. We're expecting your parcel to arrive with us.` | The seller has booked the parcel. Shouwu doesn't have it yet. | "Label created" |
| `Carrier received the shipment` | Shouwu writes it two hours after the order data, days before the first warehouse scan: no one has the parcel yet. | "Label created" |
| `Arrival processing center` | Shouwu's warehouse in China has taken the parcel in. | "Received by carrier" |
| `Package processing completed` | The warehouse is done with it, before the flight. | "Received by carrier" |
| `Arrived at Shanghai Pudong Airport`, `Departed from export airport` | At the airport in Shanghai, then flown out of China. | "In transit" |
| `Arrived at airport of Destination` | Landed in your country. | "In transit" |
| `Shipment started the customs clearance process at destination` | Your country's customs are clearing it. | "At customs" |
| `Custom clearance completed` | Your country's customs have let it through. | "In transit" |
| `Delivery to local courier` | Handed to the courier that delivers in your country, not to you. | "In transit" |
| `Out for delivery` | The local courier's delivery round. | "Out for delivery" |
| `Delivered by post man` | The local post has delivered it. | "Delivered", once Shouwu's status says `Delivered` |

**Times out by hours.** Shouwu's system records every scan's local time as if it were Beijing time, scans abroad too, and its tracker shows that in your time zone. So a scan in the UK or the US shows hours early, and the flight looks hours shorter. Go by the order of the rows. Peek works out each scan's clock from its place, or from the leg the parcel was on, and shows them in your device's time zone.

## Who delivers Shouwu parcels

Shouwu says it works directly with USPS, Royal Mail, La Poste, DHL, DPD and Hermes. Its line pages, in Chinese, name the couriers by country:

- **United Kingdom.** The UK lines cover the whole of the UK. Their last-mile couriers are Royal Mail, Yodel and Evri (spelt ERVI there); the line for goods with batteries goes mainly by Royal Mail.
- **United States.** The US lines cover the whole country, delivered mainly by USPS.
- **Ireland, Canada and Australia.** Shouwu lists no line for them and names no courier. Ask the seller who delivers.

Shouwu's tracker gives no courier's name or number, beyond what a scan's wording may say.

**Missed deliveries.** Attempts, cards and pickup points follow the courier's rules. Under `Undelivered`, Shouwu's tracker tells you to contact the carrier for a new delivery or to collect the parcel at once, before the carrier's deadline, or it "might be returned to the sender". Shouwu's UK and US line terms, in Chinese, say a parcel that can't be delivered isn't sent back to China and is destroyed by default. Either way, act on a card quickly. See [missed delivery](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

## Shouwu parcel not arriving or tracking stuck

**`Not Found`.** Shouwu gives four reasons: it hasn't accepted the parcel yet, hasn't scanned it yet, the number is wrong, or the parcel was posted so long ago that its tracking is gone. It says processing usually takes a few days. Peek shows "Waiting for the carrier" and keeps checking. Check the number character by character; if nothing comes after a few days, ask the seller.

**Stuck on `Preparing`, `Shipment information received` or `Carrier received the shipment`.** Shouwu doesn't have the parcel yet, or hasn't sent it off from its warehouse. Ask the seller when it was handed over.

**Stuck at customs.** After `Shipment started the customs clearance process at destination`, it waits for your country's customs. If you're asked for import charges, see [parcels held at customs](guide:customs).

**Nothing after `Delivery to local courier`.** The courier has it, but Shouwu's tracker doesn't give the courier's number. Ask the seller for the courier's number and paste it into Peek. More in [why tracking stops updating](guide:tracking-not-updating) and [tracking a parcel from China](guide:tracking-from-china).

**Delivered, but nothing came.** Shouwu's line terms say that when the courier's site shows a delivery that never arrived, Shouwu helps look into it but pays nothing. So ask the courier first, then the seller. In the US, USPS takes a Missing Mail search from the sender or the recipient, starting 7 days from mailing. See [delivered but not received](guide:delivered-not-received).

**Lost or damaged.** The seller is Shouwu's customer, and under Shouwu's line terms it must claim within 30 days of Shouwu shipping the parcel. For a parcel with no delivery scan after Shouwu's warehouse took it in, Shouwu pays the declared value plus the shipping charge, at most 200 yuan per shipment, and nothing for damage in transit. So ask the shop or marketplace for a refund or a new parcel, and don't wait. See [lost parcels](guide:lost-parcel).

## Shouwu contact number and customer service

As read on 30 October 2026, Shouwu's website, www.sedel.com, is in Chinese and made for sellers booking shipments. It lists mobile numbers for its Shanghai head office and five branches, a QQ chat link and WeChat codes. There's no email address, no contact form, no contact in English and nothing for recipients. The tracker gives no contact at all.

For your order, ask the seller: it booked the parcel with Shouwu and makes the claims. For the delivery itself, ask the courier in your country.

## Track a Shouwu parcel with Peek

[Peek](/) recognises a Shouwu number by its shape: SWECN, twelve digits and YQ, which no other carrier in its catalog uses, and asks Shouwu's tracker by it. No account, postcode or phone number is needed.

Peek takes the parcel's stage from Shouwu's status, as in the tables above, and shows the destination country and each scan with its place, which Shouwu's page leaves out. A place holding a digit, such as a postcode, is dropped. Shouwu gives no delivery estimate, so Peek shows no expected delivery date.

With no courier named, Peek shows no "Delivery with" line and doesn't hand the parcel over to the courier. If you have the courier's number, add it to Peek as a parcel of its own.

Peek checks every 10 minutes by day, every 2 minutes once the parcel is out for delivery, and every hour or two overnight. It can notify you of every scan, important steps only (delivery, pickup, customs, missed attempts and returns) or "Delivery only". It can't contact Shouwu or the seller for you.

## Questions about Shouwu tracking

### What is Shouwu International (守务国际)?

A Shanghai logistics company, 上海守务国际物流有限公司, that ships parcels for cross-border online sellers. It says it started in 2018 as an air freight forwarder and turned to e-commerce lines to Europe and the US in 2019.

### Where can I track a Shouwu parcel?

In the box above, or on Shouwu's English tracker at admin.shshouwu.com. Use the whole SWECN…YQ number.

### Who delivers Shouwu parcels in the UK and the US?

A local post or courier: Shouwu's line pages name Royal Mail, Yodel and Evri in the UK, and mainly USPS in the US. For Ireland, Canada and Australia it names none, so ask the seller.

### How long does Shouwu take to deliver?

It depends on the line the seller chose. Shouwu's reference times are 5 to 8 working days on its UK standard line, 10 to 12 on UK economy, 6 to 8 on US standard and 8 to 12 on US economy.

### Who do I contact about a missing Shouwu parcel?

The seller you ordered from: it booked the parcel with Shouwu and can claim within 30 days of shipping. Shouwu publishes no contact for recipients.

:::sources
- [Shouwu International: 守务国际包裹追踪 (parcel tracking)](https://admin.shshouwu.com/track.html) – English tracker, 40 numbers, statuses and their explanations, scans without places, "Copy Link", "Not Found"
- [Shouwu International: 首页 (home page)](http://www.sedel.com/) – contact list, QQ chat, company name
- [Shouwu International: 关于我们 (about us)](http://www.sedel.com/about-us.html) – 2018 and 2019, direct partners
- [Shouwu International: 主营线路 (main lines)](http://www.sedel.com/main-route.html) – lines by country
- [Shouwu International: 英国标快 (UK standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=2) – Royal Mail, Yodel, ERVI, choice of number, 5 to 8 working days, no return to China, claims
- [Shouwu International: 英国经济 (UK economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=3) – 10 to 12 working days
- [Shouwu International: 英国带电 (UK, batteries)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=4) – mainly Royal Mail
- [Shouwu International: 美国标快 (US standard express)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=1) – mainly USPS, 6 to 8 working days, no return to China, claims
- [Shouwu International: 美国经济 (US economy)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=21) – 8 to 12 working days
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – senders or recipients, from 7 days
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shape, tracker and link, statuses, scan wordings and stages, clocks, places, what Peek shows
:::
