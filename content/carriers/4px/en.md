---
title: 4PX tracking: numbers, statuses and who to contact
description: Track a 4PX parcel from China: what a 4PX number looks like, what each status means, who delivers the last mile and who to ask when tracking stalls.
slug: 4px-tracking
published: 2026-10-11
updated: 2026-10-26
---

4PX (递四方速递, 4PX Express Co., Ltd.) is a cross-border logistics company based in Shenzhen, China, that ships parcels abroad for online sellers and hands them to a carrier in your country for the last mile. Track the number on 4PX's own site, [track.4px.com](https://track.4px.com/), or in the box above. The number comes from the shop or marketplace you bought from, in its shipping details for your order.

## 4PX tracking number: what it looks like

4PX publishes no example number; its site takes 5 to 50 letters and digits. Common shapes, with invented digits:

| Number | Whose it is |
| --- | --- |
| `4PX1234567890123CN` | 4PX, then 13 digits, then CN: 4PX's own number. |
| `LP1234567890123CN` | LP, 13 digits and CN: likely 4PX. |
| `LP12345678901234` | LP and 14 digits, no CN: [Cainiao](carrier:cainiao), AliExpress's logistics reference. |
| `RR000000005GB` | A [postal number](guide:tracking-number-formats) ending in a country code: the post of that country, here Royal Mail. |
| `YT1234567890123456` | Not 4PX but [YunExpress](carrier:yunexpress). |

Peek also accepts lower case and spaces. 4PX's result page can show two numbers: the "4PX Order No." and a "Tracking No.", which belongs to the carrier that delivers in your country. The shop may give you that local number instead of 4PX's; 4PX's site answers some of them too.

## 4PX tracking statuses explained

4PX's scan lines are usually in English, and its site can translate them into Simplified Chinese or show the original text. The lines people see, in the order a parcel meets them:

| 4PX says | What it means for you |
| --- | --- |
| `Parcel information received` | The seller created the shipment. 4PX doesn't have the parcel yet. |
| `4px received shipment.` | 4PX has the parcel at its warehouse. |
| `Shipment arrived at facility and measured.` | Measured at a 4PX facility. |
| `Hand over to airline.` | Handed to the airline for the flight abroad. |
| `Arrival to the destination airport` | Landed in your country. |
| `Customs clearance agent received the shipment in destnation country.` | At customs in your country (the typo is 4PX's). |
| `Released from customs: customs cleared.` | Customs is done; it moves on. |
| `In transit, it's progressing through post network` | The local carrier has it. |
| `Out for delivery` | With the driver, on its way to you. |
| `Delivery attempt unsuccessful` | The driver couldn't deliver it. |
| `Delivered, in/at mailbox` | Delivered into your mailbox. |

`Shipping information received`, `Waybill generated`, `Shipping label created` and `Advance notice - package information received.` mean the same as the first row. `Shipping label created, usps awaiting item` means a USPS label exists and USPS hasn't scanned the parcel yet; the "usps awaiting item" ending can follow later lines too.

On top of the scans, 4PX files each parcel under one summary label. `receive forecast` is the pre-advice: 4PX has the order but not the parcel. `In Transit` covers everything from the warehouse to the last mile. `Arrived for Pickup` means the parcel waits at a delivery point. `Exception` covers an unreachable recipient, a wrong address, a refusal, damage, loss, a parcel unclaimed in time, a delay, a failed first attempt or customs trouble. `Suspected return` means the delivering carrier may send it back. `Trajectory stalled` means no update for 15 days, and `No Data Found` means 4PX finds nothing under that number: it may be mistyped or have no updates yet.

## 4PX delivery, pickup and failed delivery

4PX doesn't deliver to your door itself. Its site says the parcel "is being delivered to the delivery company of the destination country", for example [Royal Mail](carrier:royal-mail) in the UK or [USPS](carrier:usps) in the US. Once that happens, the result page's "Terminal Service Provider" box gives that company's contact details and website.

Attempts, notice cards, rescheduling and how long a parcel is held are that carrier's rules, not 4PX's: 4PX publishes none. Its `Arrived for Pickup` note asks you to collect the parcel "as soon as possible to avoid it being returned by the service provider". After `Delivery attempt unsuccessful`, use the "Tracking No." and the contact in that box to ask the local carrier about a new delivery or a pickup.

## 4PX package not updating, lost or returned

**No tracking yet.** 4PX's site says: "There is currently no trace of your package. Please wait patiently or contact the merchant to inquire about the delivery status." `No Data Found` can also mean a mistyped number.

**Stuck for days.** After 15 days without a scan, 4PX files the parcel under `Trajectory stalled` and says it may be lost or delayed, or that a carrier didn't enter its scans. Check the local carrier's own tracking with the "Tracking No." first. More in [why tracking stops updating](guide:tracking-not-updating).

**Delivered but not there.** 4PX's note under `Successful delivery` asks you to contact customer service for confirmation. Start with the local carrier, which made the delivery: see [delivered but not received](guide:delivered-not-received).

**Returned or lost.** 4PX publishes no claim process or deadline for recipients, and its sites describe returns and claims as services for sellers. The shop or marketplace holds the contract with 4PX, so ask it for a refund or a new parcel, and the local carrier about anything that happened at delivery.

## 4PX customer service and contact number

As read on 11 October 2026, 4PX's tracking site lists one contact for everyone, under Help, Contact Us:

- **Phone**: 0755-23508000, a number in Shenzhen, China (+86 755 2350 8000 from abroad). No hours or languages are given.
- **Email**: track@4px.com.
- **Address**: 4PX Express Co., Ltd., No.12 Building, FuYong e-cool, BaoAn District, ShenZhen, GuangDong, China.

4PX lists no recipient contact for the UK, Ireland, the US or any other country. For anything about the order, the shop is quicker; for the final delivery, use the local carrier in the "Terminal Service Provider" box.

## Track a 4PX parcel with Peek

[Peek](/) recognises a `4PX…CN` number straight away. For an LP number ending in CN, the app shows "asking 4PX…" and then "4PX has this parcel." when 4PX knows it; if it doesn't, pick the carrier with "Change". A link from track.4px.com with `#/result/0/` and the number is read as 4PX whatever number it holds, so it works for the local "Tracking No." too. Orders of several packages go to universal tracking services.

It reads 4PX in English and shows the status, the history with the place of each scan on a map, the delivery time, the destination country and the local carrier with its number. When that carrier is in Peek's list (USPS, for example), the parcel can be followed there once it shows newer progress. `Out for delivery` and `Delivery attempt unsuccessful` appear in Peek's own words; other lines as 4PX writes them. 4PX gives no expected delivery date, pickup point details or weight.

Scan times keep 4PX's time zones; a scan without one is read on the clock of its place. Peek checks every 10 minutes by day, every 2 minutes once the parcel is out for delivery, and hourly overnight, and can notify you of every scan, important steps only or "Delivery day only". It can't contact 4PX or the shop for you.

## Questions about 4PX tracking

### Where is 4PX based?

In Shenzhen, China: its tracking site gives 4PX Express Co., Ltd. in BaoAn District, ShenZhen. 4PX says it was founded in 2004.

### Is 4PX legit?

Yes, 4PX is an established Chinese logistics company that runs its own global tracking site and, with Cainiao, a logistics park in Dongguan. If its site shows `No Data Found`, 4PX says the number may be wrong or not updated yet: check it, then ask the shop.

### What does "Parcel information received" mean for a 4PX package?

The seller has created the shipment and sent its details, but 4PX hasn't received the parcel. The next step, such as `4px received shipment.`, comes once the parcel reaches 4PX's warehouse.

### How long does 4PX shipping take?

4PX publishes no delivery times for recipients, so the estimate to go by is the one the shop or marketplace shows for your order. If nothing moves for 15 days, 4PX treats the parcel as stalled.

:::sources
- [4PX: Global Order Tracking](https://track.4px.com/) – number rules, summary labels, translation, Terminal Service Provider box
- [4PX: tracking site language file](https://track.4px.com/js/index.6aecf0a2c58f0d7c56a3.js) – label hints, handover to the local carrier, no-trace message
- [4PX: Contact Us](https://track.4px.com/#/help/contact) – phone, email, address
- [4PX: Privacy Policy](https://track.4px.com/#/help/privacy) – 4PX Group, claims
- [4PX: For Perfect X-border](https://www.4px.com/view/index.html) – company and seller services
- [4PX: 退换货 (returns)](https://www.4px.com/view/return.html) – returns as a seller service
- [4PX: 递四方成都至伦敦全货机航线成功首航](https://www.4px.com/view/newsCenterContentfive.html) – founded in 2004
- [4PX: 菜鸟&递四方国际智慧物流园盛大开园](https://www.4px.com/view/newsCenterContentsix.html) – Cainiao and 4PX logistics park
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek shows
:::
