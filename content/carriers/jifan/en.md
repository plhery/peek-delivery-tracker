---
title: Jifan tracking: JFGJ numbers, scans and Beijing time
description: Track a Jifan (吉凡国际物流) parcel from China to the US: what a JFGJ…CN number looks like, what the tracker's lines mean, and why every time on it is Beijing time.
slug: jifan-tracking
published: 2026-11-07
updated: 2026-11-07
---

Jifan is Shenzhen Jifan International Logistics (深圳市吉凡国际物流有限公司, or 吉凡国际物流 for short), a logistics company in Shenzhen, China, founded in 2017. It ships parcels from businesses in China to buyers in the United States, by air and by sea. Its tracking numbers are JFGJ, nine digits and CN. Look yours up on [Jifan's tracker](https://www.szjf56.cn/index/tracknumber/track), or paste it in the box above. The seller gives you the number with your order's shipping details.

## Jifan tracking number: what it looks like

Jifan's site doesn't describe its numbers, but they have one fixed shape. JFGJ is the company's initials: 吉凡国际, Jifan Guoji, "Jifan International". Every example below is made up:

| Number | What it is |
| --- | --- |
| `JFGJ000000000CN` | JFGJ, nine digits and CN: a Jifan number. |
| `JFGJ00000000CN` | Only eight digits: not Jifan's shape, and Peek doesn't name Jifan. Check for a missing digit. |
| `RR000000005CN` | Two letters, nine digits and CN: a [China Post](carrier:china-post) number, not Jifan's, despite the same CN ending. |

In Peek, lower case, spaces and dashes don't matter. On Jifan's tracker, type the number in capital letters, without spaces.

**Where to find it.** In the seller's shipping email, or on your order page. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

**On Jifan's site.** The tracker, 快件跟踪 (parcel tracking), has a 跟踪(Track) button. It takes up to 20 numbers at a time and only finds parcels from the last two months.

## Jifan tracking statuses explained

Jifan's tracker is in Chinese, but the tracking lines (轨迹) are in English, newest first, each with its time (时间). Above them, a summary row gives the destination (目的地), a status (状态) and the latest line. Two statuses you may see there:

| The tracker says | What it means for you |
| --- | --- |
| `运输途中` | Chinese for "in transit". Read the lines below it to see where the parcel really is. |
| `查询不到` | Chinese for "can't be found". The tracker has no parcel under this number, and shows no lines. |

Then come Jifan's own steps in China, from the order data to the flight out. None of them gives a place. In the order a parcel usually meets them:

| Jifan says | What it means for you | Peek shows |
| --- | --- | --- |
| `Order information received` | The seller sent Jifan the parcel's details. Jifan doesn't have the parcel yet. | "Label created" |
| `Arrive at the cross border receiving point` | Jifan has the parcel now, at the place in China where it takes in parcels going abroad. | "Received by carrier" |
| `Goods leave the operation center` | The parcel left Jifan's operation centre in China. | "In transit" |
| `Arrival at domestic internationality airport` | At the airport in China, before the flight. `internationality` is the tracker's own spelling. | "In transit" |

After the flight, the lines come from Jifan's agent in the United States and from the company that delivers: the landing, import customs, then sorting centres. In the US, some of these lines start with the town, state and ZIP code. Jifan's tracker runs on the same system as [CTTYEX](carrier:cttyex)'s and writes its lines the same way, so CTTYEX's page lists the wording you may see.

Peek reads a line about import customs clearance as "At customs". The delivering company's first line in your region stays "In transit", not a new start: Jifan has had the parcel since China. A line saying the parcel was delivered shows as "Delivered".

**Every time is Beijing time.** Jifan's tracker prints its times without a time zone. Each one is Beijing time (UTC+8), even for the lines from the United States. Beijing's date changes at 16:00 UTC. So a scan made in New York after 11:00 in winter (noon in summer), or in Los Angeles after 08:00 in winter (09:00 in summer), shows the next day's date.

## Who delivers Jifan parcels

Jifan's site lists four lines, all to the United States:

- **US economy small parcel** (美国经济小包-轻小件) for light, flat items: under 360 g and under 1.8 cm thick, on direct cargo flights, delivered by UPS, anywhere in the US, overseas islands and military addresses included. Jifan says it can be tracked all the way. About 10 to 12 calendar days.
- **US air small parcel** (美国空运小包标快): 0 to 4.5 kg, on direct cargo flights, delivered by UPS ("UPS MI", Jifan writes), anywhere in the US, overseas islands and military addresses included. About 8 to 10 calendar days, it says.
- **Matson sea parcels** (美森正班（限时达）海运大/小包): small parcels up to 4.5 kg are delivered the same way as the air line. Large ones, from 4.5 to 68 kg, go by UPS Ground, to the mainland US only. About 20 to 25 calendar days.
- **Oversize by sea** (美国海运超大件专线): 100 to 2,000 kg per piece, on Matson and COSCO ships, then by truck to the door, mainland US only. About 25 to 40 calendar days.

For its economy parcels, and its small parcels up to 4.5 kg by air or by sea, Jifan says one waybill covers the whole trip (一单到底). Its tracker doesn't name the delivering company and gives no second number. If the seller gave you a UPS number as well, you can track it with [UPS](carrier:ups).

Jifan publishes no rules for recipients on delivery attempts, notice cards or pickup points: those are the delivering company's. See [missed deliveries](guide:missed-delivery).

**Customs.** Jifan's site says nothing for recipients about duty or taxes. What you may owe depends on your country: see [parcels held at customs](guide:customs).

## Jifan parcel not updating, lost or returned

Jifan's customers are the businesses that send parcels with it, not you. For a late, lost, damaged or returned parcel, contact the seller or marketplace first.

**`查询不到` (can't be found).** Check the number for a typo, type it in capitals, and ask the seller if it still fails. In Peek, the parcel shows "Waiting for the carrier" and Peek keeps checking. After two days with nothing, Peek flags it: "Still waiting for the carrier’s first update".

**Only `Order information received`.** Jifan has the order's data, not the parcel. If nothing new comes for several days, ask the seller whether the parcel was handed over.

**Quiet after the airport.** After `Arrival at domestic internationality airport`, there may be nothing new until the parcel lands or reaches customs in the US. Sea parcels take weeks, so long gaps are normal for them. When a parcel on its way has no new line for four days, Peek flags it ("No tracking update for four days") and notes that parcels often go quiet on long flights, at customs or over a weekend. See [why tracking stops updating](guide:tracking-not-updating) and [tracking parcels from China](guide:tracking-from-china).

**At customs.** Jifan's tracker shows no duty or fee to pay. So it can't confirm a text or email asking you to pay for a Jifan parcel. Read [delivery scam texts](guide:delivery-scam-texts) before you pay anything.

**Lost, damaged or returned.** Jifan's site has nothing for recipients on claims or returns. It tells its customers, the senders, that it pays for delays and that for a lost parcel it refunds the shipping charge and pays the same again. That is between Jifan and the seller: ask the seller for a refund or a new parcel. See [lost parcels](guide:lost-parcel), [damaged parcels](guide:damaged-parcel) and [returned to sender](guide:returned-to-sender).

**Delivered but not there.** Check around your home and ask neighbours, then tell the seller: see [delivered but not received](guide:delivered-not-received).

## Jifan contact

As read on 1 November 2026 on Jifan's [home page](https://www.szjf56.cn/), in the 联系我们 (contact us) section:

- **Hotline**: 400-186-0001, for enquiries (咨询电话), a Chinese service number. Jifan gives no opening hours and doesn't say whether it answers from abroad or in English.
- **Office**: Nanwan, Longgang District, Shenzhen.

The page is in Chinese only, with no contact form, chat or help for recipients. About your order, contact the seller first.

## Track a Jifan parcel with Peek

[Peek](/) names Jifan at once from a JFGJ…CN number ("Detected carrier"): no other carrier Peek follows uses this shape. A link to Jifan's tracker with a JFGJ number after `nos=` works too; if the link holds several numbers, Peek takes the first.

For any other number the seller gave you, paste it all the same: Peek follows more than 3,500 carriers. If the number's shape doesn't name one, Peek asks tracking services, as you add it or just after. If they don't name the carrier, tap "Change" and pick the one the seller names, or use "Change carrier" on a parcel you've already added. Peek reads Jifan's tracker only for JFGJ numbers: if you pick Jifan for another number, the carrier line says Jifan "doesn’t issue numbers like this one".

Peek reads the parcel from Jifan's tracker: its status, every line in the tracker's English, and the country it's going to. When a line in the US starts with a town and state, Peek makes them the scan's place and drops the ZIP code. Each time is read as Beijing time and shown in your device's time zone.

Peek's status follows the newest line it understands, and the summary status (`运输途中`) only when it understands none. A line Peek doesn't know is read by its words.

Jifan's tracker shows no delivery date, weight, service name, delivering company or second number, so Peek has none either. It keeps following the JFGJ number on Jifan's tracker to the end.

Peek checks every 10 minutes. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". It can't contact Jifan or the seller for you.

## Questions about Jifan tracking

### What is Jifan logistics?

Shenzhen Jifan International Logistics (深圳市吉凡国际物流有限公司), a Shenzhen company founded in 2017, it says. It ships parcels for businesses from China to buyers in the United States, by air and by sea. Its numbers start with JFGJ and end with CN.

### Where can I track a JFGJ number?

On Jifan's tracker, [快件跟踪 on szjf56.cn](https://www.szjf56.cn/index/tracknumber/track), typed in capitals, or in Peek. The tracker only finds parcels from the last two months.

### Why are the times on Jifan's tracker wrong?

They aren't wrong, they're in Beijing time (UTC+8), even for scans in the United States. Beijing is 13 hours ahead of New York and 16 ahead of Los Angeles in winter, so a scan made after 11:00 in New York or after 08:00 in Los Angeles shows the next day's date. Peek shows each time in your device's time zone.

### What does 查询不到 mean on Jifan's tracker?

It's Chinese for "can't be found": no parcel on Jifan's tracker matches what you typed. Check for a typo, type the number in capitals, and ask the seller if it still fails. Parcels older than two months aren't on the tracker any more.

### How long does Jifan take to deliver?

Jifan's site gives about 8 to 10 calendar days for its US air parcels, about 10 to 12 for its economy small parcels, about 20 to 25 for its Matson sea parcels, and about 25 to 40 for oversize freight. The seller's estimate for your order is the one to go by.

:::sources
- [Jifan: 深圳市吉凡国际物流有限公司 (home page)](https://www.szjf56.cn/) – company name, founded 2017, US lines only, weights and sizes, UPS and UPS Ground delivery, Matson and COSCO, areas served, calendar days, one waybill, compensation promise to its customers, hotline, office district
- [Jifan: 快件跟踪 (tracking)](https://www.szjf56.cn/index/tracknumber/track) – Chinese tracker powered by 吉凡物流, Track button, result columns, parcels from the last two months, 20 numbers at a time
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – JFGJ number shape and initials, tracker link, CTTYEX's system, summary statuses and English lines, Jifan's steps in China, its agent and the delivering company abroad, Beijing time, towns, states and ZIP codes, what Peek shows and leaves out
:::
