---
title: Xinghe Freight tracking: GZXH…YQ numbers and scans
description: Track a Xinghe Freight (星和货运) parcel from Guangzhou: what a GZXH…YQ number looks like, whose tracker it runs on, what its steps mean and who to ask.
slug: xinghe-freight-tracking
published: 2026-11-03
updated: 2026-11-03
---

Xinghe Freight (星和货运, 广州星和货运代理有限公司) is a freight forwarder in Guangzhou, China. If a Chinese shop sent your order with Xinghe, look for a number of GZXH, ten digits and YQ, the shape Peek knows as Xinghe's, and paste it in the box above. Xinghe's tracking runs on the tracker of GZYL, another Guangzhou forwarder: the tracking box on Xinghe's website sends your number there. That tracker sits on a bare server address, over plain HTTP. Peek reads it for you, and asks tracking services when that tracker doesn't know your number.

## Xinghe Freight tracking number: what it looks like

The numbers Peek knows as Xinghe Freight's are GZXH, ten digits and YQ, 16 characters in all. With invented digits:

| Number | What it is |
| --- | --- |
| `GZXH0000000000YQ` | GZXH, ten digits and YQ: Xinghe Freight's layout. Peek names Xinghe Freight straight away. |
| `GZXH000000000YQ` | Only nine digits: not Xinghe's layout. Check for a missing digit. |
| `YL0000000000YQ` | YL, ten digits and YQ: the layout of [GZYL](carrier:gzyl)'s waybills. Same tracker, another forwarder. |
| `JXCAA0000000000YQ` | Also ends in YQ, as many Chinese forwarders' numbers do, but starts with JXC: a [Juxi Express](carrier:juxi-express) number. |

Spaces, dashes and lower case don't matter when you paste the number into Peek.

**Where to find it.** The seller books the parcel with Xinghe and passes the number on. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

## Xinghe Freight tracking statuses explained

Xinghe's tracking runs on GZYL's tracker, so its steps come in GZYL's words: in English, typos included. Peek reads them with GZYL's list of steps. In the order a parcel gets them:

| The tracker says | What it means for you | Peek shows |
| --- | --- | --- |
| `Order information received` | The seller has sent the order's details. Nobody has the parcel yet. | "Label created" |
| `Arrive at the cross border receiving point` | The forwarder has the parcel: the first scan in its hands. | "Received by carrier" |
| `Goods leave the operation center` | The parcel has left the operation centre. | "In transit" |
| `Arrive at the international airport` | At the airport, waiting for its flight. | "In transit" |
| `Departing from the airport` | The flight has left. | "In transit" |
| `Item arrives at destination airport` | Landed in your country. | "In transit" |
| `local carrier Agent Import customs clearance` | An agent in your country is clearing the parcel through import customs. | "At customs" |
| `The import customs keep it for inspection` | Your country's customs are inspecting the parcel. | "At customs" |
| `Customs clearance completed` | Through customs and on its way again. | "In transit" |
| `Internationaliy Package received at processing center` | A processing centre in your country has taken the cleared parcel. It isn't a new pickup. | "In transit" |
| `Leave the email processing center` | The parcel has left that processing centre. | "In transit" |
| `Arrival sorting, distribution center` | At a distribution centre in your country, with its town after the words. | "In transit" |
| `Leaving the sorting center` | Leaving a sorting centre, with its town. | "In transit" |
| `Package is on the way` | Moving on in your country, with a town. It doesn't say the parcel is out for delivery. | "In transit" |
| `Delivered` | The delivery, with the town. It may be a plan, not a scan: see below. | "Delivered", with a note on the fixed schedule, unless Peek holds it back (below) |

**A line in Chinese.** For an order with no scans yet, the tracker's page can show `货物电子信息已经收到`, which means that the goods' electronic information has been received. The platform writes this line by itself. The seller has created the order, but nobody has scanned the parcel. Peek shows the line in Chinese, under "Label created".

**Times.** Peek reads every time on the tracker as Beijing time, steps in your country included, and shows it in your device's time zone. In winter, a step at 20:00 in Beijing is 13:00 in Paris or Berlin, 12:00 in London and 7:00 in New York.

**Steps written in advance.** On this tracking platform, forwarders can write a parcel's whole timeline ahead, from collection to its last step in your country, and the tracker shows each step once its time comes. Such a step is a plan, not a scan. On GZYL's tracker, the plan then ends in a `Delivered` the platform adds in an evening batch on the day it shows: dated the day after the plan's last step, during office hours in Beijing, whatever your country. Peek holds these steps back (see below), but a tracking service's copy can still show them, so a `Delivered` there doesn't prove your parcel arrived.

## Who delivers Xinghe Freight parcels

Peek doesn't know who delivers Xinghe parcels in your country. The tracker's steps there name no company, and neither does Xinghe's site. The only delivering company Peek can hand a Xinghe parcel over to is [Swiss Post](carrier:swiss-post), in Switzerland and Liechtenstein, when Xinghe's record names it.

Xinghe's own site says nothing about parcels for online shoppers: no delivery times, delivery attempts or pickup points. Those are the delivering company's rules. Ask the seller which company delivers your order, and for its tracking number.

**Customs fees in the EU.** Since 1 July 2026, goods bought online from outside the EU, in parcels worth up to €150, pay a €3 customs duty per item. Since 1 November 2026, a €2 Union handling fee per item is due as well, whatever the value. An item here means the goods in a parcel that share the same tariff classification and description, and origin where it must be declared, whatever their number. For what you pay, and how, in the UK, Ireland, the US, Canada and Australia, see [parcels held at customs](guide:customs).

## Xinghe Freight parcel not arriving or tracking stuck

**Only `Order information received` or `货物电子信息已经收到`.** The seller has created the order, but nobody has scanned the parcel. If nothing follows after a few days, ask the seller whether it has really been sent.

**"Waiting for the carrier" in Peek.** Peek has no step for your number yet: GZYL's tracker doesn't know it or holds only a plan of steps written ahead, which can end in `Delivered`, and the tracking services Peek asks have nothing either. Peek keeps checking. Compare the number with the seller's message too.

**Stuck at customs.** If you're asked to pay import charges, see [parcels held at customs](guide:customs). If the parcel stays there for days, tell the seller.

**The tracker or a tracking service shows more than Peek.** The extra steps, `Delivered` included, may be a plan that Peek holds back, not scans. Go by the delivery estimate the shop gave, and ask the seller once it has passed. More in [why tracking stops updating](guide:tracking-not-updating) and [tracking a parcel from China](guide:tracking-from-china).

**`Delivered`, but nothing came.** The step may be part of a timeline written in advance. Unless Swiss Post delivers the parcel, Peek says so under "Delivered": "Xinghe Freight updates on a fixed schedule, with no signature. If nothing came, ask the seller." Tell the seller the parcel hasn't arrived, and ask which company delivers it and for its number. More in [delivered but not received](guide:delivered-not-received).

**Lost or returned.** The seller booked the parcel with Xinghe, so ask the seller, or the marketplace, for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## Xinghe Freight contact number and customer service

As read on 2 November 2026, Xinghe's website, xinghe-express.cn, in Chinese only, lists:

- **Hotline**: 19924483103, which it says is open 24 hours a day, every day (+86 199 2448 3103 from abroad).
- **Email**: 3975605292@qq.com, for business enquiries and price quotes.
- **Address**: Guangzhou, Guangdong province, with no street. For the full address, it asks visitors to get in touch first.

The site has nothing for recipients abroad: its contact form only opens an email to that address. Xinghe's customer is the seller, so ask the seller about your order first, and the marketplace if the seller doesn't answer.

## Track a Xinghe Freight parcel with Peek

[Peek](/) names Xinghe Freight straight away for a GZXH…YQ number, with "Detected carrier". If the seller gave you a number of another shape, paste it all the same: Peek follows more than 3,500 carriers. To put a number under Xinghe by hand, press "Change" and search for Xinghe Freight (typing 星和货运 finds it too), or use "Change carrier" on a parcel you've already added.

Peek asks GZYL's tracker, as Xinghe's own tracking box does. It shows the status, each step with its place, and the destination country when the record gives it. Times are read on Beijing time and shown in your device's time zone. Peek leaves out the names, address, postcodes, weight, payment details, signature and proof of delivery the record can hold, and shows no expected delivery date: the tracker gives none.

When the tracker doesn't know your number, Peek asks tracking services instead and shows their copy of the steps, which may be all Peek has for a Xinghe number. While neither has a step for it, the parcel shows "Waiting for the carrier".

Peek holds back any step written more than 12 hours before the time it shows, and any step dated after the moment it checks, on Beijing time. On a record with steps written ahead, it holds back `Delivered` too, even one written on its own day. It goes by the steps it keeps, not by the record's summary status, and sets aside tracking services' copies of the steps it holds back as written ahead, `Delivered` included, even when it keeps other steps. When the tracker has no record of your number or can't be reached, though, Peek can't tell a planned step in a tracking service's copy from a scan.

Swiss Post aside, Peek doesn't follow a Xinghe parcel on to the company that delivers it: if the seller gives you that company's number, add it as a parcel of its own. Peek checks every 10 minutes, and hourly once the parcel has gone quiet. It can notify you of every scan, important steps only or the delivery only: "Important steps" covers the two customs steps and `Delivered`, not flights or sorting. Peek can't contact Xinghe Freight, GZYL or the seller for you.

## Questions about Xinghe Freight tracking

### What is Xinghe Freight (星和货运)?

广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding), a freight forwarder in Guangzhou, China. Its site says it was founded in 2014 and handles sea, air and land freight, ship agency and customs paperwork. The numbers Peek files under it are GZXH, ten digits and YQ.

### Why does my Xinghe Freight parcel show GZYL's tracking?

Xinghe's tracking runs on GZYL's tracker: the tracking box on Xinghe's own site sends your number there, and the steps come in GZYL's words. Peek keeps the parcel under Xinghe Freight and reads that tracker for you.

### Who delivers Xinghe Freight parcels in my country?

Peek doesn't know: neither the tracker's steps nor Xinghe's site name a company. Only in Switzerland and Liechtenstein can Peek follow a Xinghe parcel on to Swiss Post. Ask the seller which company delivers your order, and for its tracking number.

### Why does Peek show no tracking for my GZXH number yet?

The tracker may not have the order yet, or only steps written ahead, which can end in `Delivered`: Peek holds them back. Peek asks tracking services too and keeps checking. If nothing comes after a few days, ask the seller whether the parcel has really been sent.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (home page)](http://xinghe-express.cn/) – company name, freight forwarding services, tracking box that opens GZYL's tracker, hotline and email
- [Xinghe Freight: 关于我们 (about)](http://xinghe-express.cn/about.html) – founded in 2014, sea, air and land freight, ship agency, customs declaration
- [Xinghe Freight: 产品服务 (services)](http://xinghe-express.cn/services.html) – services offered, nothing on parcels for online shoppers
- [Xinghe Freight: 联系我们 (contact)](http://xinghe-express.cn/contact.html) – 24-hour hotline, email for business enquiries and quotes, address given as Guangzhou only, get in touch before visiting, contact form opening an email
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty per item on parcels up to €150 since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and the Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – handling fee per item whatever the value, what an item is
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – names, GZXH…YQ number shape, Xinghe read on GZYL's tracker, step wordings and stages, Chinese line, Beijing clock, timelines written ahead, tracking services as fallback, Swiss Post hand-over, what Peek shows, holds back and leaves out
:::
