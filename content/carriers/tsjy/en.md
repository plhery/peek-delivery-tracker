---
title: TSJY tracking (通世集运): order numbers and statuses
description: Track a TSJY (通世集运) consolidated parcel from China: what a TSJY…CN number looks like, what each Chinese status means, and when 17FEIA takes over.
slug: tsjy-tracking
published: 2026-11-03
updated: 2026-11-03
---

TSJY (通世集运) is a consolidation service in China, run by 苏州通世供应链有限公司, a supply chain company in Suzhou. You buy on Chinese shopping sites such as Taobao, Tmall, Pinduoduo, JD or Douyin, and the shops send your goods to TSJY's warehouse in Suzhou. TSJY stores them, packs them together and ships them abroad. Track your TSJY number on [TSJY's site](https://www.tsjy56.com/), under 订单查询 (order search), or paste it in the box above.

## TSJY tracking number: what it looks like

TSJY's site doesn't describe its numbers, but they have one fixed shape. TSJY is the initials of 通世集运, Tongshi Jiyun. Every example below is made up:

| Number | What it is |
| --- | --- |
| `TSJY000000000CN` | TSJY, nine digits and CN: a TSJY order number. |
| `TSJY00000000CN` | Only eight digits: not TSJY's shape, and Peek doesn't name TSJY. Check for a missing digit. |
| `FEIAA0000000000YQ` | FEI, two letters, ten digits and YQ: a [17FEIA](carrier:17feia) number, from the line that carries some TSJY orders abroad. |

In Peek, small letters, spaces and dashes don't matter.

**Where to find it.** It's the number of your shipping order (订单号), which you submit on TSJY's web ordering page (网页下单) or in WeChat (微信下单) once your goods are in its warehouse. If someone else booked the shipment, ask them.

**Two numbers.** TSJY's search box also takes the line's number (运单号). On a phone, each order's result card shows TSJY's number and, after a slash, the line's. Tap the card for the tracking lines (轨迹详情).

## TSJY tracking statuses explained

TSJY's tracker is in Chinese. On a phone, each result card gives one short status for the whole order:

| The card says | What it means for you |
| --- | --- |
| `等待处理` | Waiting to be processed: TSJY has your shipping order. |
| `开始拣货`, `正在拣货`, `拣货完成`, `开始打包`, `已称重`, `已录单` | The warehouse picks your goods, packs and weighs the parcel, and records the order. |
| `等待出库`, `已出库`, `运输途中` | Waiting to leave the warehouse; it has left; in transit. |
| `已送达`, `已完成` | Delivered; completed. |
| `运输异常` | A problem on the way. Ask TSJY. |

Peek doesn't read this card status. It reads the tracking lines (轨迹) behind the card. First come TSJY's own steps in its warehouse, in Chinese:

| TSJY says | What it means for you | Peek shows |
| --- | --- | --- |
| `订单已提交，等待系统确认` | Order submitted, waiting for the system to confirm it. Nothing is picked yet. | "Label created" |
| `仓库开始拣货` | The warehouse starts picking your goods. | "Received by carrier" |
| `仓库拣货完成` | Picking is done. | "Received by carrier" |
| `订单打包完成，等待出库` | Packed, waiting to leave the warehouse. It can show twice within a minute. | "Received by carrier" |
| `订单已出库，开始国际运输` | It left TSJY's warehouse, and the trip abroad begins. | "In transit" |

On the 17FEIA line, TSJY then copies 17FEIA's lines, in English:

| The line says | What it means for you | Peek shows |
| --- | --- | --- |
| `Item information received.` | 17FEIA has the order's details. TSJY may still have the parcel. | "Received by carrier" |
| `The package is on the way to the facility.`, `Warehouse receipt.` | On its way to 17FEIA's warehouse in China, then received there. | "In transit" |
| `The order has information pending customer confirmation.`, `Order Confirmed.` | 17FEIA's warehouse holds the parcel until the order is confirmed. | "In transit" |
| `Order dispatched from warehouse.` | It left 17FEIA's warehouse. | "In transit" |
| `Export declaration in progress.` | China's export customs, not your country's. | "At customs" |
| `Flight has departed.` | Flown out. | "In transit" |
| `Flight delayed.` | The flight is late. The parcel hasn't moved. | the status of the line before |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | A stop on the way. | "In transit" |
| `Flight Arrived at Clearance Port.` | Landed where import customs will clear it. | "In transit" |
| `Broker picked up Shipment and delivered to warehouse.` | A customs broker took it to its warehouse for clearance. Not a delivery to you. | "At customs" |
| `Customs Clearance is in progress.` | Import customs is checking it. | "At customs" |
| `Customs Clearance Completed.` | Customs released it. | "In transit" |

Peek leaves out 17FEIA's line with an estimated departure date, which isn't a scan, and any line dated in the future. Peek reads other lines, such as the delivering company's, by their words, such as delivered or customs.

**Times.** TSJY's detail page shows each time on your device's clock, and Peek shows TSJY's own steps the same way. Peek reads 17FEIA's warehouse lines on Beijing time (UTC+8), as 17FEIA writes them. When a later line's place ends with a country code, such as `(DE)`, Peek reads its time on that country's clock, for a country with one time zone.

## Who delivers TSJY parcels

TSJY's site says it ships to more than 220 countries and regions, by express air (加急空运) or economy sea (经济海运). It doesn't say who delivers in which country, and gives no delivery times. Its detail page names the line (渠道, channel) each order travels on.

Peek knows one line by name: [17FEIA](carrier:17feia) (一起飞), a logistics company in China, with its own tracker on www.17feia.com. 17FEIA's tracking can name the company that delivers in your country, with that company's number.

TSJY publishes no rules for delivery attempts or pickup points: those are the delivering company's. See [missed deliveries](guide:missed-delivery).

**Customs in the EU.** Goods bought online and brought into the EU from outside it carry a temporary €3 customs duty per item on parcels worth up to €150, since 1 July 2026, and a €2 Union handling fee per item, whatever the value, since 1 November 2026. An item is the goods sharing a tariff classification, description and, where required, origin. Both apply to distance sales: goods sent to you by the seller or on its behalf. The Commission's guidance says goods are not sent by the seller when the customer arranges the transport without any intervention of the seller, which may be the case when you book TSJY yourself: your country's customs decides. TSJY's site doesn't say how duty or fees are paid. See [parcels held at customs](guide:customs).

## TSJY parcel not updating, lost or returned

With TSJY you are usually the customer: you booked the shipment. So for a late, lost or damaged parcel, contact TSJY first.

**Goods not at the warehouse yet.** Before your goods reach TSJY, they travel with the shops' couriers in China: track them where you bought them. TSJY's tracking starts when a parcel reaches its warehouse, its site says, and runs until it is signed for.

**Nothing for the number.** For a number it doesn't know, TSJY's results page on a phone says `未找到相关的轨迹信息。` (no tracking information found). Check for a typo. In Peek, the parcel shows "Waiting for the carrier" and Peek keeps checking. After two days with nothing, Peek flags it: "Still waiting for the carrier’s first update".

**Stuck in the warehouse.** If TSJY's own steps stop for several days, ask its customer service.

**Quiet after the flight.** After `Flight has departed.`, there may be nothing new until the landing or customs. When a parcel on its way has had no new line for four days, Peek flags it ("No tracking update for four days"). See [why tracking stops updating](guide:tracking-not-updating) and [tracking parcels from China](guide:tracking-from-china).

**Lost or damaged.** TSJY's site promises to pay in full for lost or damaged parcels (丢破必赔), but its public pages give no claim rules or deadlines: ask its customer service at once, and keep the box and the goods. See [lost parcels](guide:lost-parcel) and [damaged parcels](guide:damaged-parcel).

**Delivered but not there, or returned.** Check around your home and ask neighbours, then tell TSJY. Its site says nothing about returns. See [delivered but not received](guide:delivered-not-received) and [returned to sender](guide:returned-to-sender).

## TSJY customer service and contact

As read on 1 November 2026 on [TSJY's home page](https://www.tsjy56.com/), in its 客服中心 (customer service centre) section:

- **Phone and email**: a customer service phone number (客户服务电话) and email address (客服邮箱). No opening hours are given.
- **WeChat**: a QR code to add TSJY's customer service (添加微信客服), plus its WeChat service account and mini program.
- **Warehouse**: in Huaqiao (花桥镇), Suzhou, with the full address on the page.

The site is in Chinese only and says nothing about service in English.

## Track a TSJY parcel with Peek

[Peek](/) names TSJY at once from a TSJY…CN number ("Detected carrier"): no other carrier Peek follows uses this shape. A link to TSJY's result page with a TSJY number after `nos=` works too; with several numbers in it, Peek takes the first. A TSJY link holding the line's number, or any number not of TSJY's shape, isn't filed under TSJY: Peek reads that number on its own, as if you'd pasted it.

For any other number, paste it all the same: Peek follows more than 3,500 carriers. When the shape fits several carriers, Peek shows "Checking tracking services…" while it asks around; when it fits none, Peek looks the number up once you add it. If Peek doesn't name the carrier, tap "Change" and pick it under "All carriers", or use "Change carrier" later. If you pick TSJY for another number, Peek says TSJY "doesn’t issue numbers like this one".

Peek reads TSJY's tracker: every line with its place and time. It leaves out the origin, the destination country and the line's name. TSJY gives no delivery date or weight. Peek's status follows the newest line it understands.

When TSJY gives a 17FEIA number, or a line's number whose shape points to one carrier Peek knows, Peek asks that carrier. Until that carrier shows a scan at least as recent as TSJY's newest, Peek stays on TSJY's tracking, and the parcel already shows who delivers it ("Delivery with 17FEIA") and that number. Then Peek follows the parcel there and keeps TSJY's history.

Peek checks every 10 minutes. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". It can't contact TSJY, 17FEIA or the shops for you.

## Questions about TSJY tracking

### What is TSJY (通世集运)?

A consolidation service (集运) for people abroad who buy on Chinese shopping sites. Its site says it was founded in 2005, stores parcels free for 180 days and ships to more than 220 countries and regions.

### Where can I track a TSJY number?

On [TSJY's site](https://www.tsjy56.com/), under 订单查询: type the TSJY number or the line's number, then tap 查询. Or paste it in Peek.

### What does 订单打包完成，等待出库 mean?

It's Chinese for order packed, waiting to leave the warehouse: your parcel is packed in TSJY's warehouse and waits for the line. Peek shows "Received by carrier". Next comes `订单已出库，开始国际运输`: it left, and the trip abroad has begun.

### Why does my TSJY parcel show a 17FEIA number?

[17FEIA](carrier:17feia) is the line carrying your parcel abroad: TSJY's result card shows its FEI…YQ number after a slash. Once 17FEIA catches up, Peek follows the parcel there.

### How do I contact TSJY?

Through the 客服中心 section of its home page: a phone number, an email address and WeChat, all in Chinese.

:::sources
- [TSJY: 通世集运 (home page)](https://www.tsjy56.com/) – company, services, compensation promise, tracking span, order search and its results page on a phone, ordering, contacts, warehouse
- [TSJY: 轨迹查询 (tracking results)](https://www.tsjy56.com/orderResult.html) – results on a phone: cards with both numbers, card statuses, no-result message
- [TSJY: 轨迹详情 (track details)](https://www.tsjy56.com/orderDetail.html) – channel (渠道) name and number, tracking lines, times on the device's clock
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 duty since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per item whatever the value, what an item is, distance sales and transport the customer arranges
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shape, link, wordings and stages, clocks, hand-over to the line, what Peek reads
:::
