---
title: DHLink tracking: numbers, statuses and who delivers
description: Track a DHLink parcel from a DHgate order: which numbers DHLink's tracker takes, what its English scans mean, who delivers in your country and who to ask.
slug: dhlink-tracking
published: 2026-10-22
updated: 2026-10-27
---

DHLink (驼飞侠) is the cross-border logistics platform of DHgate (敦煌网). Founded in 2014, it ships orders for DHgate sellers and other customers, from China or from its warehouses abroad, and hands them to a carrier in your country for the last leg. Track the number on DHLink's [tracking page](https://www.dhlink.com/trackQuery?lang=en) or in the box above. Take the number from your DHgate order, or ask the seller for it.

## DHLink tracking number: what it looks like

DHLink publishes no number format. Its tracker takes up to 40 numbers at once and accepts what it calls the Global Tracking No., the Logistics Delivery No. and the DHLink pickup number. Shapes seen on DHLink parcels, with invented digits:

| Number | What it is |
| --- | --- |
| `FC0000000000000` | FC and 13 digits: DHLink's own number, the "DHLink No." On some lines it's also the tracking number. |
| `XZG000000000WL` | XZG, 9 digits, WL: a line partner's number, which DHLink finds as the tracking number. |
| `YLUS0000000000` | YLUS and 10 digits: a US line that GOFO delivers. |
| `QH000000000000FS` | QH, 12 digits, FS: another US line that GOFO delivers. |
| `EYT0000000000HZ` | EYT, 10 digits, HZ: a US line that SwiftX delivers. |
| `RR000000005CN` | Two letters, 9 digits and CN: a [China Post](carrier:china-post) number. Some DHLink parcels travel with China Post under such a number. |
| `GFUS00000000000000` | Not DHLink's: a [GOFO Express](carrier:gofo) number, the last leg of some US lines. |

On a result, DHLink lists the "DHLink No." and the "Tracking No.", and, when another carrier delivers, a "Last Leg No." for that carrier. If the tracking number differs from the DHLink number, use the tracking number: looked up by the DHLink number alone, such a parcel may not show. Without `lang=en` in the address the page opens in Chinese.

## DHLink tracking statuses explained

A result shows the status (`In transit`, `Delivered`), the origin and destination country codes, the line's code, a progress bar with `Original Leaving`, `Destination Arrived`, `Pending delivery` and `Delivered`, and every scan with its time. A delivered parcel also shows "Signed for - Signed for time". The rows come from several feeds, so the same scan often appears more than once. DHLink's English lines, in the order a parcel meets them:

| DHLink says | What it means for you |
| --- | --- |
| `Arrive at the collection warehouse` | DHLink's collection warehouse in China has the parcel. |
| `The collection warehouse has completed sorting` | Sorted there. |
| `Package received & scanned at warehouse` | Booked in at DHLink's warehouse. |
| `Parcel handed over to carrier,waiting for transportation` | The collection point has it and it waits for transport. |
| `Package departed from the warehouse`, `The package has sent from the warehouse` | It has left the warehouse. |
| `Flight departure`, `The flight has taken off` | Flown out of China. |
| `Shipment Departed From Original Country at …` | Same: it has left China. |
| `The flight has arrived and the board is being dismantled` | Landed; the pallets are being broken down. |
| `Shipment has arriveved at destination Country at …` | In your country. The spelling is DHLink's. |
| `Customs clearance completed` | Import customs has released it. |
| `UPS/USPS/Fedex` | A handover to the US carrier is coming. |
| `货物已交UPS/USPS/Fedex,The goods have been delivered to UPS/USPS/Fedex` | Handed to the US carrier. Not delivered to you yet. |
| `Package picked up by last-mile carrier` | The carrier in your country has taken it over. |
| `Scheduled for Delivery` | Out for delivery. |
| `Shipment has delivered at …` | Delivered. |

US lines add hub scans such as `Arrived at Origin Hub` and `Departed Destination Hub`. After the handover come the delivering carrier's rows, such as GOFO's `Arrived at GOFO Delivery Station` or USPS's `No Access to Delivery Location` (a failed attempt). On China Post lines, China Post's rows appear too, some only in Chinese: `中国邮政 已收取邮件` (China Post took the item), `送交出口海关` (presented to export customs), `已妥投` after a country code (delivered). For a number it doesn't know, the page says `There is no track for this waybill number`.

## Who delivers DHLink parcels

DHLink's site is written for sellers and names no last-mile partner by country. Its tracker shows who delivers. On US lines, one scan says the parcel went to "UPS/USPS/Fedex" without saying which; the rows that follow are that carrier's, such as [USPS](carrier:usps)'s. Some US lines end with [GOFO Express](carrier:gofo) or SwiftX, whose number shows as the "Last Leg No.". Parcels carried by China Post keep their CN number to the end: see [China Post](carrier:china-post) for who delivers them in your country.

Once you know the carrier and its number, its own tracking has the local detail, and its rules apply to delivery attempts, notice cards and pickup: see [missed a delivery](guide:missed-delivery). For duties and fees on goods from China, see [parcels held at customs](guide:customs).

## DHLink parcel not updating, lost or delivered but missing

**Not found.** Check the number on your DHgate order, and try the tracking number rather than the DHLink number. A last-mile number finds nothing on DHLink's page: check it with that carrier.

**Stuck after the flight or customs.** Look for a "Last Leg No." and check it with that carrier, whose scans may be newer than DHLink's. More in [why tracking stops updating](guide:tracking-not-updating) and [tracking a parcel from China](guide:tracking-from-china).

**Delivered but not there, lost or late.** DHLink's refund and compensation rules for problem parcels are written for sellers: the seller claims from DHLink, and you deal with the seller or DHgate. They exclude what the buyer causes: a late delivery because the parcel wasn't collected or signed for, or failed customs clearance because the buyer didn't help in time. So pick it up and answer customs requests promptly. See [delivered but not received](guide:delivered-not-received) and [lost parcels](guide:lost-parcel).

## DHLink contact and customer service

As read on 22 October 2026, DHLink's contact page lists one customer service address, dhlinkservice@dhlink.com, and a platform enquiry form. The site also has a chat assistant, "AI 小飞". The contact page is in Chinese and lists no phone number. For your order, contact the seller through DHgate first; for a delivery problem in your country, the delivering carrier.

## Track a DHLink parcel with Peek

[Peek](/) can't tell DHLink by the number's shape alone: its FC, XZG, YLUS, QH and EYT numbers are only weak matches. Paste it all the same: Peek follows more than 3,500 carriers. It shows "Checking tracking services…" and then "DHLink has this parcel." when DHLink knows the number; otherwise pick DHLink with "Change". A CN number goes to China Post.

Peek reads DHLink's tracker. It shows the status, the history with each scan's place and the destination country, and lists each scan once. Scans in China are read on Beijing time and USPS scans on the time of their US state; others keep the time DHLink gives. DHLink gives no expected delivery date, delivery time, weight or service name, and Peek drops postcodes and order references. When the "Last Leg No." is a GOFO number (GFUS and 14 digits) or a SwiftX one (SWX and 18 digits), Peek shows that carrier and its number, asks it and switches to it once it shows newer progress. Other last-leg numbers stay on DHLink's history.

Peek checks every 10 minutes by day, every 2 minutes once the parcel is out for delivery, and every hour or two overnight, and can notify you at every scan, at important steps or on delivery day only. It can't contact DHLink, DHgate or the seller for you.

## Questions about DHLink tracking

### What is DHLink?

DHgate's cross-border logistics platform, run by 驼飞侠科技（北京）有限公司 in Beijing. Founded in 2014, it ships orders from China and from overseas warehouses through its own lines and partner carriers.

### Who delivers my DHLink package?

A carrier in your country. On US lines, DHLink hands parcels to UPS, USPS or FedEx, or to GOFO or SwiftX. Look for the "Last Leg No." on DHLink's result.

### What does "The goods have been delivered to UPS/USPS/Fedex" mean?

DHLink has handed the parcel to the US carrier. It isn't delivered to you yet: that carrier's scans follow.

:::sources
- [DHLink: About us (关于我们)](https://www.dhlink.com/about/introduce) – founded 2014, DHgate's cross-border logistics platform, for DHgate and other customers
- [DHLink: Home page (首页)](https://www.dhlink.com/) – direct shipping from China, overseas warehouses, link to DHgate's order tracking
- [DHLink: Tracking (轨迹查询)](https://www.dhlink.com/trackQuery?lang=en) – 40 numbers, accepted numbers, result fields, DHLink No., Tracking No., Last Leg No., progress bar, "There is no track for this waybill number"
- [DHLink: Contact us (联系我们)](https://www.dhlink.com/about/connect) – customer service email, enquiry form, chat
- [DHLink: Service agreement (服务协议)](https://www.dhlink.com/agreementPage/service) – operator 驼飞侠科技（北京）有限公司
- [DHgate Seller Help: DHLink物流异常件赔付规则 (DHLink compensation rules)](https://seller.dhgate.com/help/c7502/516002.html) – compensation for sellers, logistics types, exclusions for buyer-caused late delivery and failed clearance
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shapes, scan wordings, last-leg partners, what Peek reads and shows
:::
