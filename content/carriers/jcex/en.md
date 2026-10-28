---
title: JCEX tracking: JCZ numbers, statuses and who delivers
description: Track a JCEX parcel from China: what a JCZ waybill number looks like, what each status means, who delivers it in your country and who to ask when it stalls.
slug: jcex-tracking
published: 2026-10-21
updated: 2026-10-27
---

JCEX (佳成国际, Jiacheng International Logistics) is a logistics company in Hangzhou, China, that ships parcels abroad for online sellers and hands them to a carrier in your country for the last stretch, such as [USPS](carrier:usps) in the United States or Colissimo in France. Track the number on JCEX's tracker, [track.jcex.com](https://track.jcex.com/), or in the box above. The shop gives it with your order's shipping details.

## JCEX tracking number: what it looks like

JCEX publishes no number format. Its waybill numbers start with JCZ and end with DH. With invented digits:

| Number | What it is |
| --- | --- |
| `JCZ1234567890DH` | JCZ, 10 digits and DH: a JCEX waybill. |
| `JCZ123456789DH` | Only 9 digits: a mistyped number, not a JCEX shape. |

Lower case and spaces don't matter in Peek. JCEX's tracker takes up to 100 numbers at once, separated by commas.

**Where to find it.** No JCEX page says how the number reaches you. The seller books the parcel and gives the number in the shipping email or on the order page. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

**No second number.** JCEX's tracker has room for a second number, `跟踪单号` (tracking number), next to the waybill. In the JCEX histories read so far it stays empty, even when USPS or Colissimo makes the delivery: their scans appear in JCEX's history instead.

## JCEX tracking statuses explained

JCEX's tracker is in Chinese only, but each line's text can be English, Chinese or both, after a UTC offset, a time and a place. The lines people see, in the order a parcel usually meets them:

| JCEX says | What it means for you |
| --- | --- |
| `Shipment order info received` | The seller sent the order to JCEX. JCEX doesn't have the parcel yet. |
| `Arrive at the delivery point` | JCEX's warehouse in China has received the parcel. Not your local delivery point. |
| `Item outbound in sorting center` | It left JCEX's sorting centre. |
| `Departed from origin facility to airport` | On its way to the airport. |
| `Flight dispatched（航班已飞）` | The flight has left. |
| `Flight arrived（航班已落地）` | It landed in the destination country. |
| `Customs held（清关中）` | Import customs. The Chinese part says "clearing customs": not necessarily a problem. |
| `Clearance processing complete（清关完成）` | Through customs. |
| `Handed over to the trucking company, waiting for transport（已交卡车公司 待运输）` | Waiting for the truck to the delivering carrier. |
| `Delivered to warehouse, waiting to be scanned（已交仓，待扫描）` | Handed to the delivering carrier's depot, not to you. |
| `CONSIGNMENT RECEIVED BY OVERSEAS FORWARDER` | The delivering carrier has the parcel. |
| `out for delivery` | Out for delivery. |
| `Delivered` | Delivered to you. |

`The order information has been sent` and `货物电子信息已经收到` mean the same as the first row, `到达收货点(包裹已入库)` that a JCEX collection point booked the parcel in. `under clearance` is import customs and `CUSTOMS CLEARANCE COMPLETE` its end. `InTransit_Arrival` and `InTransit_Departure` mark a hub in Hong Kong, and `turck transferring（卡车运输中）` (JCEX's spelling) a parcel on a truck. After the handover come the delivering carrier's own lines: USPS's in English, such as `ARRIVAL AT POST OFFICE`, Colissimo's in French.

Above the lines, JCEX's tracker files each parcel under a summary label, explained in a note: `收到信息` (waiting to be collected), `运输中` (in transit), `到达待取` (waiting at a delivery point), `派送途中` (out for delivery), `投递失败` (a failed delivery attempt), `可能异常` (a possible problem), `运输过久` (in transit a long time without delivery) or `查询不到` (no tracking found).

Two things confuse people. JCEX files the handover to the delivering carrier's depot as delivered, so its page can label a parcel `已签收` (signed for) while that carrier still has it: read the newest lines. And `Arrive at the delivery point` comes at the start, in China, not near you.

## Who delivers JCEX parcels

JCEX doesn't deliver in your country itself. Its pages say small parcels (up to 2 kg, by air) are delivered by the post, and heavier express parcels by the post or a commercial courier. In histories read on JCEX's tracker, USPS delivered in the United States and Colissimo, [La Poste](carrier:la-poste)'s parcel service, in France.

JCEX publishes no rules for delivery attempts, notice cards, pickup points or how long a parcel is held: those are the delivering carrier's, and its lines in JCEX's history say what it did. In one French history, Colissimo wrote that it couldn't deliver the parcel and would take it out for delivery again the next day (except Sundays and public holidays). JCEX doesn't show that carrier's own number, so ask the seller for it if you need to rearrange a delivery.

## JCEX parcel not arriving or tracking stuck

**Who acts.** JCEX's customers are the sellers who ship with it. JCEX publishes no claim route for recipients, so for a late, lost, damaged or returned parcel, contact the seller or the marketplace you bought from.

**No tracking yet.** A new waybill can show `查询不到` with no lines until JCEX scans the parcel. If it stays that way, check the number with the seller.

**No update.** JCEX doesn't say after how long a parcel counts as `运输过久`. If nothing moves, ask the seller, which booked the parcel and can ask JCEX. More in [why tracking stops updating](guide:tracking-not-updating) and [tracking parcels from China](guide:tracking-from-china); for customs stops, see [parcels held at customs](guide:customs).

**Delivered but not there.** Check that the delivered line comes from the delivering carrier, not JCEX's handover. If it does, see [delivered but not received](guide:delivered-not-received).

## JCEX contact number and customer service

As read on 21 October 2026, JCEX lists one set of channels, all in China:

- **Hotline**: 400-887-1083, a Chinese service number, 9:00 to 18:00 China time, Monday to Friday. Closed at weekends and on public holidays.
- **Email**: cs@jcex.com.cn, which its site's footer calls its complaints address.
- **WeChat**: a customer service account, listed in its site's footer with the same hours.
- **Head office**: Jiacheng International, 8th floor, Building T4, Tiehui Development Centre, 520 Jiuhe Road, Shangcheng District, Hangzhou, Zhejiang, China.

Its contact page also lists branches across China, which serve sellers, and no contact for recipients abroad. For the order, ask the seller; for the final delivery, the delivering carrier.

## Track a JCEX parcel with Peek

[Peek](/) names JCEX at once from a `JCZ…DH` number, which no other carrier in its catalog shares. A track.jcex.com link with `?nums=` and the number can be pasted too. Peek tracks JCEX's JCZ waybill numbers only: for an order number, ask the seller for the waybill.

Peek reads JCEX's own tracking and shows the status, the history with each scan's place on a map, the delivery time and the destination country. It shows a line's English text where JCEX has one (JCEX's page shows the Chinese, such as `签收` for `Delivered`), and the delivering carrier's lines as it wrote them, except exact matches such as `Delivered`, which appear in Peek's own words. `Arrive at the delivery point` and the handover to the depot count as in transit, not as ready to collect or delivered. Each scan's time is read on the clock JCEX gives it, China's or your country's, and shown in your own time zone.

As JCEX names no delivering carrier and no second number, Peek follows the JCEX number to the end rather than switching to USPS or Colissimo, whose scans arrive through JCEX's history. It shows no expected delivery date, service or proof of delivery for JCEX.

It checks every 10 minutes by day, every 2 minutes once the parcel is out for delivery, and every hour or two overnight, and can notify you of every scan, important steps only or "Delivery only". It can't contact JCEX or the seller for you.

## Questions about JCEX tracking

### Is JCEX legit?

Yes. JCEX is the brand of Jiacheng International Logistics, founded in 2000 in Hangzhou, China, in which Huamao Logistics, a company listed in Shanghai, has held a stake since 2021. It says it is a designated logistics provider for platforms such as Alibaba.com, Cainiao and DHgate.

### What does "Arrive at the delivery point" mean on JCEX?

It means JCEX's warehouse in China has received the parcel. It comes near the start of the history, before the flight, and has nothing to do with a delivery point near you.

### Why does JCEX say delivered when I don't have my parcel?

JCEX files its handover to the delivering carrier's depot as delivered: the line `Delivered to warehouse, waiting to be scanned（已交仓，待扫描）` means the local carrier now has it. Wait for that carrier's own lines, ending with `Delivered`.

### Who delivers JCEX parcels in the US?

In the US histories read on JCEX's tracker, USPS delivered. Its lines, such as `ARRIVAL AT POST OFFICE` and `out for delivery`, appear in JCEX's history.

### How long does JCEX take to deliver?

On its small-parcel line, JCEX tells sellers 13 to 16 working days to the United States, 8 to 11 to the UK and 9 to 13 to Europe. The shop's estimate for your order is the one to go by.

:::sources
- [JCEX: 全球轨迹查询平台 (tracking)](https://track.jcex.com/) – Chinese-only tracker, up to 100 numbers, summary labels and notes, scan wordings, delivering carriers seen, empty second number, handover filed as delivered
- [JCEX: 联系我们 (contact)](https://www.jcex.com/bangzhuzhongxin/lianxiwomen/index.html) – hotline, email, hours, head office, branches
- [JCEX: 佳成国际物流 (home page)](https://www.jcex.com/) – complaints email, WeChat customer service, hours, Huamao stake
- [JCEX: 佳成介绍 (about)](https://www.jcex.com/guanyujiacheng/jiachengjieshao/index.html) – founded 2000, cross-border e-commerce sellers, Huamao 2021
- [JCEX: English site](https://enjc.jcex.com/) – Jiacheng International Logistics Co., Ltd, platforms it serves, complaint email
- [JCEX: 国际小包 (small parcels)](https://www.jcex.com/chanpinyufuwu/guojixiaobaoyukuaidi/index.html) – up to 2 kg, by air, delivered by the post, delivery times
- [JCEX: 国际快递与快运 (express and freight)](https://www.jcex.com/chanpinyufuwu/guojikuaiyun/1438101270410715138.html) – delivered by the post or commercial couriers
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek shows, number shape, warehouse and handover lines, scan wordings
:::
