---
title: HYWL tracking: Huiyi Logistics TRK numbers and scans
description: Track an HYWL (Huiyi Logistics, 汇一物流) parcel from China: what its TRK numbers look like, what its English and Chinese scans mean and who to ask.
slug: hywl-tracking
published: 2026-10-31
updated: 2026-10-31
---

Huiyi Logistics (HYWL, 汇一物流) is 深圳市汇一物流有限公司, a logistics company in Shenzhen, China, that ships parcels abroad for online sellers. If a shop sent your order with HYWL, your number is TRK and twelve more characters, or four letters, two digits, D, four digits and four letters: paste it in the box above. HYWL's own tracker is on its website, in Chinese, and its steps come in English, Chinese or both. HYWL doesn't name the company that delivers in your country.

## HYWL tracking number: what it looks like

HYWL's numbers come in two layouts, both 15 characters long. With invented characters:

| Number | What it is |
| --- | --- |
| `TRK000000000000` | TRK and twelve characters, each a digit or a letter from A to F: the layout HYWL's website gives as its example. |
| `AAAA00D0000AAAA` | Four letters, two digits, D, four digits and four letters: HYWL's other layout. |
| `TRK00000000000` | Only eleven characters after TRK: not HYWL's layout. Check for a missing character. |

No other carrier in Peek's catalog uses either layout, but both are only weak matches, so Peek checks with HYWL's tracker before it counts a number as HYWL's (more below). Spaces, dashes and lower case don't matter in Peek. HYWL's own tracker reads the number exactly as typed, so type it there in capitals.

**Where to find it.** The seller books the parcel with HYWL and passes the number on, in its shipping email or on your order page. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

**A tracking link.** A link to HYWL's tracker that ends in `?number=` or `?trackingNumber=` and a number opens that one parcel. Paste the whole link into Peek: it reads the number and files the parcel under Huiyi Logistics straight away.

## HYWL tracking statuses explained

HYWL's record gives every step a status and a wording. Its tracker shows the parcel's status on top, then each step's time, wording and place, oldest first. The statuses:

| HYWL's status | What it means for you | Peek shows |
| --- | --- | --- |
| `Picked Up` | HYWL has the parcel. | "Received by carrier" |
| `In Transit` | Anything from leaving HYWL's pickup point in China to the trip inside your country. | "In transit" |
| `Customs` | A customs step. The ones in Peek's code say China's export customs are done. | "In transit" for those, "At customs" for any other |
| `Out for Delivery` | Out for delivery. | "Out for delivery" |
| `Delivered` | Delivered. | "Delivered" |

The wordings say more. In the order a parcel gets them:

| HYWL says | What it means for you | Peek shows |
| --- | --- | --- |
| `Shipment accepted, received by SZHYWL`, `SZHYWL已收取邮件` | HYWL (SZHYWL) has taken the parcel in. It's the first step, with a town and an office code. | "Received by carrier" |
| `Departed Pickup Point`, `离开收寄点` | It has left the point where HYWL took it in. | "In transit" |
| `Arrival at Origin Processing Center`, `Departure from Origin Processing Center` | At a processing centre in China, then leaving it. | "In transit" |
| `邮件到达处理中心`, `邮件离开处理中心` | Arrived at a processing centre, then left it. | "In transit" |
| `Received by Carrier`, `承运商接收` | The carrier on HYWL's line has taken the parcel from HYWL. It isn't a new shipment. | "In transit" |
| `Departure from`, `启运` | Left the place named in brackets. | "In transit" |
| `In Transit`, `快件在该国内转运中, 干线中转` | On its way. The Chinese says it's moving inside China, on the line haul. | "In transit" |
| `Customs Clearance Completed`, `出口清关完成` | China's export customs are done. Not your country's customs. | "In transit" |
| `Departure from International Hub`, `Arrival at Transit Hub` | Left an international hub, then reached a transit hub on the way. | "In transit" |
| `Arrival at, 抵达` | Arrived in the destination country, named by its code in 【】. | "In transit" |
| `邮件在该国内转运中` | Moving inside the destination country. | "In transit" |
| `Arrival at Processing Center`, `Departure from Processing Center` | At a processing centre in your country, then leaving it, with your town in 【】. | "In transit" |
| `Dispatching, 出门投递` | Out for delivery. | "Out for delivery" |
| `Delivered, 已妥投` | Delivered. | "Delivered" |

**Places.** HYWL's steps in your country name your town and postcode, and its first step a town and an office code. Peek keeps the town only, and cuts any name after "signed by" or 签收人.

**Times.** HYWL's tracker prints each time with no time zone. Steps whose place is in Chinese, or in Shenzhen, Guangzhou, Dongguan, Huizhou or Hong Kong, are on Beijing time: in winter, 20:00 there is noon in London and 7am in New York.

**Steps dated ahead.** HYWL's tracking page says its staff create the orders and maintain their tracking in an internal back office. Its record can hold steps dated later than the moment you look, and HYWL's tracker shows them all the same, under a status that can be ahead too. Peek holds back each such step from HYWL's tracker until its time has passed: a step in China by Beijing time, any other by its time read as UTC. Peek goes by the steps it keeps, not by HYWL's status. A step dated ahead hasn't happened yet, even `Delivered, 已妥投`.

## Who delivers HYWL parcels

HYWL's website says it offers lines with delivery abroad, but it names no company that delivers them, and HYWL's tracker gives neither the company nor its number for the parcel. Peek's code names none either, for any country. So Peek doesn't know who delivers HYWL parcels in the US, the UK, Ireland, Canada or Australia, and can't hand the parcel over to them.

HYWL's own steps run up to the delivery: `Dispatching, 出门投递`, then `Delivered, 已妥投`. Peek found no HYWL delivery times, and no rules for delivery attempts, notice cards, pickup points or how long a parcel is held: those are the delivering company's. If a delivery goes wrong, ask the seller which company delivers your order and for its tracking number, then see [missed delivery](guide:missed-delivery).

## HYWL parcel not arriving or tracking stuck

**"暂时无法获取轨迹，请确认单号是否正确或稍后再试。"** HYWL's tracker says this when it can't find the number, or can't answer: "can't get the tracking for now, check that the number is right or try again later". Check the number character by character, in capitals. If nothing comes after a few days, ask the seller whether the parcel has really been sent.

**"已查到单号，暂无更多轨迹节点。"** "Number found, no more tracking steps for now": the order exists, but HYWL hasn't put any steps on it. Ask the seller when the parcel was handed over.

**HYWL's tracker shows more than Peek.** Peek holds back the steps on HYWL's tracker dated after the moment it checks, so HYWL's page can show steps Peek doesn't. Check their times: a step dated ahead hasn't happened yet. Go by the delivery estimate the shop gave you, and ask the seller once it has passed. More in [why tracking stops updating](guide:tracking-not-updating) and [tracking a parcel from China](guide:tracking-from-china).

**Nothing after `Arrival at, 抵达`.** The parcel has reached your country. If you're asked to pay import charges, see [parcels held at customs](guide:customs). If it sits for days, ask the seller which company delivers it and under which number.

**`Delivered, 已妥投`, but nothing came.** First check the step's time: if it's still ahead, the step hasn't happened. If it has passed, tell the seller and ask which company delivered. See [delivered but not received](guide:delivered-not-received).

**Lost or returned.** The seller booked the parcel with HYWL, so the seller, or the marketplace, is the one to ask for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## HYWL contact number and customer service

As read on 30 October 2026, HYWL's website is in Chinese and written for sellers booking shipments. It gives an address in the Yantian district of Shenzhen, and 待补充 ("to be added") where its phone number and email should be.

The seller booked the parcel with HYWL and is the one to ask about your order, and the marketplace if the seller doesn't answer.

## Track an HYWL parcel with Peek

[Peek](/) can't be sure of an HYWL number by its shape alone. Paste it all the same: Peek follows more than 3,500 carriers. While you add it, Peek asks HYWL's tracker ("Checking tracking services…") and shows "Huiyi Logistics has this parcel." when the tracker knows the number. Otherwise press "Change" and pick Huiyi Logistics under "Fits this number" (typing HYWL or 汇一物流 finds it too), or use "Change carrier" on a parcel you've already added.

If you add a TRK number without picking a carrier and HYWL doesn't list it yet, Peek shows "Probably Huiyi Logistics" and "Waiting for Huiyi Logistics’s first scan", and keeps asking. After a week, it asks you to check the number or choose another carrier.

Peek shows the status, each step with its place, and the destination country when HYWL's record gives it. It reads steps in China on Beijing time, works out the others' clock from their place or from the leg the parcel was on, and shows every time in your device's time zone, newest first. It leaves out postcodes and the origin country. HYWL gives no expected delivery date, weight, service, delivering company or its number, sender or recipient name, or proof of delivery, so Peek shows none of them.

When HYWL's tracker can't be reached, Peek asks tracking services instead and shows the history they hold, in their words. Peek doesn't hold back steps dated ahead in their copies, so check each step's time.

Peek stays with HYWL's tracker: if the seller gives you the delivering company's number, add it as a parcel of its own. Peek checks every 10 minutes by day, every 2 minutes once the parcel is out for delivery, and every hour or two overnight. It can notify you of every scan, important steps only or the delivery only: "Important steps" covers `Dispatching, 出门投递` and `Delivered, 已妥投`, not HYWL's export customs or sorting. Peek can't contact HYWL or the seller for you.

## Questions about HYWL tracking

### What is HYWL (汇一物流)?

深圳市汇一物流有限公司 (Huiyi Logistics), a logistics company in Shenzhen, China. It says it was founded in 2013 and ships parcels abroad for cross-border online sellers and trading companies. Its numbers start with TRK, or have a D between digits.

### Where can I track an HYWL parcel?

In the box above, or on HYWL's tracker at szhywl.it.com, in Chinese: type the number in capitals and press 官网查询. Its other button, 17TRACK 查询, opens the same number on 17TRACK, a tracking service.

### What do 出门投递 and 已妥投 mean on HYWL tracking?

出门投递 is out for delivery, which HYWL writes `Dispatching, 出门投递`. 已妥投 is delivered: `Delivered, 已妥投`. Check the step's time: HYWL's tracker also shows steps dated ahead, and those haven't happened yet.

### Who delivers HYWL parcels in my country?

HYWL doesn't say, and its tracker gives no delivering company or number; Peek's code names none either. Ask the seller which company delivers your order.

### Who do I contact about a missing HYWL parcel?

The seller you ordered from: it booked the parcel with HYWL. HYWL's website lists no phone number or email, only 待补充 ("to be added").

:::sources
- [Huiyi Logistics: 深圳市汇一物流有限公司 (home page)](https://www.szhywl.it.com/) – company name, Shenzhen, 2013, customers and services, delivery abroad, the tracker's two buttons, address in Yantian, phone and email "to be added"
- [Huiyi Logistics: 物流轨迹查询 (tracking)](https://www.szhywl.it.com/track.html) – public tracker, orders and tracking maintained in the back office, its messages, times printed as stored, the link that opens one parcel
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – names, number layouts and their weak match, links, codes, wordings and their stages, places and postcodes, Beijing clock, steps dated ahead held back, no delivering company, what Peek shows and leaves out
:::
