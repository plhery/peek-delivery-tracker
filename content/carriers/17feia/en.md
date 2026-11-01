---
title: 17FEIA tracking: FEI…YQ numbers, statuses and delivery
description: Track a 17FEIA (一起飞) parcel from China: what a FEI…YQ number looks like, what each line on its tracker means, who delivers it and what Peek shows.
slug: 17feia-tracking
published: 2026-11-03
updated: 2026-11-03
---

17FEIA (一起飞, also Shanghai Hongshan International Logistics, 上海宏杉国际物流) is a Chinese logistics company. It takes parcels in at its warehouse in Shenzhen or Shanghai, flies them out of China and, after import customs, hands them to a carrier for delivery. Its numbers start with FEI and end with YQ. Paste yours in the box above, or use 17FEIA's own tracker on www.17feia.com. The number comes from whoever shipped your order: the seller, or the forwarder that sent your goods on from China.

## 17FEIA tracking number: what it looks like

Peek knows 17FEIA's numbers as FEI, two letters, ten digits and YQ: 17 characters in all. The two letters after FEI are 17FEIA's branch, such as SZ for Shenzhen or SH for Shanghai. Every example below is made up:

| Number | What it is |
| --- | --- |
| `FEISZ0000000000YQ` | FEI, two letters, ten digits and YQ: a 17FEIA number. Peek names 17FEIA from the number alone. |
| `FEISZ000000000YQ` | Only nine digits: not 17FEIA's shape. Check for a missing digit. |
| `FEISZ0000000000AB` | Two letters other than YQ at the end. Peek doesn't name 17FEIA from the number alone, but asks 17FEIA's tracker whether it has it. |
| `HHWAA0000000000YQ` | YQ, but HHW in front: a [HuaHan Logistics](carrier:huahan) number. |

In Peek, small letters, spaces and dashes don't matter.

**Where to find it.** In the shipping email, on your order page, or in your forwarder account if you used one. Otherwise, see [how to find your tracking number](guide:find-tracking-number). Later, 17FEIA's record can also give Peek the delivering carrier's own number.

## 17FEIA tracking statuses explained

17FEIA's record gives most lines a short label in Chinese and a line of text, newest first. Peek shows the English text and reads the step from the label. 17FEIA's own lines, in the order a parcel usually meets them:

| 17FEIA says | What it means for you | Peek shows |
| --- | --- | --- |
| `Item information received.` | 17FEIA has the parcel's details, not the parcel. | "Label created" |
| `The package is on the way to the facility.` (`包裹在去设施路上`) | On its way to 17FEIA's warehouse, which doesn't have it yet. | "Label created" |
| `Warehouse receipt.` (`仓库收货`) | 17FEIA's warehouse in Shenzhen or Shanghai has the parcel. | "Received by carrier" |
| `The order has information pending customer confirmation.` (`订单待客户确认`) | The warehouse waits for 17FEIA's customer, who booked the shipment, to confirm the order. | "Received by carrier" |
| `Order Confirmed.` (`订单已确认`) | The order is confirmed. | "Received by carrier" |
| `Order dispatched from warehouse.` (`订单已出库`) | The parcel left the warehouse. | "In transit" |
| `Export declaration in progress.` (`出口报关中`) | Export customs in China, not your country's. | "At customs" |
| `Flight has departed.` (`航班已起飞`) | The flight has left China. | "In transit" |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | A stop on the way, then the next flight. | "In transit" |
| `Flight Arrived at Clearance Port.` (`航班抵达清关港`) | Landed where the parcel clears import customs. | "In transit" |
| `Broker picked up Shipment and delivered to warehouse.` (`清关行提货到仓`) | A customs broker took the shipment to its warehouse for clearance. Not a delivery to you. | "At customs" |
| `Customs Clearance is in progress.` (`清关中`) | Import customs are checking it. | "At customs" |
| `Customs Clearance Completed.` (`清关完成`) | Import customs let it through. | "In transit" |
| `Arrived at delivery hub，Awaiting carrie scan.` (`已交仓，等待上网`) | Handed in at the delivering carrier's depot. That carrier's first scan comes next. ("carrie" is the tracker's spelling.) | "In transit" |

**Two lines that move nothing.** `Estimated departure date:` followed by a date is a plan written before the flight, not a scan: Peek leaves it out. `Flight delayed.` (`航班延误`) means the flight is late: Peek keeps the status of the line before it.

**The progress bar.** 17FEIA's tracking page also shows a bar from 仓库收货 (warehouse receipt) through 飞机起飞 (plane took off) and 飞机落地 (plane landed) to 成功签收 (signed for). Peek doesn't copy the bar, but shows a line labelled 成功签收 as "Delivered".

**The delivering carrier's lines.** After the hand-over, 17FEIA's tracker can pass on the delivering carrier's scans, labelled 运输途中 (in transit), in that carrier's words and with its place, such as DHL's `The international shipment has been processed in the export parcel center`. Peek reads them by their words: a receipt stays "In transit", while out for delivery or delivered move the status on.

**Why the times look out of order.** The warehouse steps in China are on Beijing time (UTC+8), 8 hours ahead of London and 13 ahead of New York in winter. The delivering carrier's scans are on local time. The flight and customs lines name no place, and the time zone 17FEIA gives them isn't always theirs. Go by the order of the lines.

## Who delivers 17FEIA parcels

After import customs, 17FEIA hands the parcel to a carrier that takes it on to you. Peek couldn't check 17FEIA's website, so this page has no list of its partners or delivery areas.

**What Peek's code shows.** Peek knows three names 17FEIA gives its partners: DPD-DE for [DPD Germany](carrier:dpd-de), DE-DHL for [DHL](carrier:dhl) in Germany and FEDEX-GROUND for [FedEx](carrier:fedex). It reads five destinations on 17FEIA's record: Germany, France, the Netherlands, Poland and the United States. One history in Peek's code shows DHL in Germany taking a parcel bound for France, to hand it to the company that delivers there.

**Missed delivery.** Attempts and pickup follow the delivering carrier's rules: ask it, with its own number. See [missed delivery](guide:missed-delivery).

**Customs in the EU.** Goods bought online from outside the EU carry a €3 customs duty per item on parcels worth up to €150, since 1 July 2026, and a €2 Union handling fee per item, whatever the value, since 1 November 2026. An item is the goods sharing a tariff classification, description and, where required, origin. Peek couldn't check how these are charged on a 17FEIA parcel: ask the seller or your forwarder whether your price included them. Elsewhere, what you owe depends on your country. See [parcels held at customs](guide:customs), and before paying after a text or email, [delivery scam texts](guide:delivery-scam-texts).

## 17FEIA parcel not updating, lost or returned

17FEIA works for whoever booked the shipment: the seller, or the forwarder you used. For a late, lost, damaged or returned parcel, contact them first.

**Number not found.** For a number it doesn't know, 17FEIA's tracker shows no progress. In Peek, the parcel shows "Waiting for the carrier" and Peek keeps checking; after two days, it flags "Still waiting for the carrier’s first update". Check the number against the shipping message: FEI, two letters, ten digits, YQ.

**Stuck on `The order has information pending customer confirmation.`** If you used a forwarder, check your account or messages there. Otherwise, ask the seller.

**Quiet after the flight.** After `Flight has departed.`, there may be nothing new for days. After four days with no new line, Peek flags it ("No tracking update for four days"). See [why tracking stops updating](guide:tracking-not-updating) and [tracking parcels from China](guide:tracking-from-china).

**Delivered, but nothing came.** Check around your home and ask neighbours, then the delivering carrier and the seller. See [delivered but not received](guide:delivered-not-received).

**Lost or returned.** Ask the seller or forwarder for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## 17FEIA contact and customer service

As of 1 November 2026, 17FEIA's website answered Peek with a bot check, so this page lists no contact details for 17FEIA. About your order, ask the seller or forwarder that booked the shipment: they are 17FEIA's customers. For the delivery, ask the delivering carrier.

## Track a 17FEIA parcel with Peek

[Peek](/) names 17FEIA at once from a FEI…YQ number ("Detected carrier"): no other carrier Peek follows uses this shape. A link to 17FEIA's tracking page with the number after `orderNumber=` works too. No postcode or account is needed.

For any other number, such as the delivering carrier's, paste it all the same: Peek follows more than 3,500 carriers. When the shape fits several carriers, none of them for sure, Peek shows "Checking tracking services…" while it asks around; when it fits none, Peek looks it up once you add it. To set the carrier yourself, tap "Change" and search for 17FEIA, Feia or 一起飞, or use "Change carrier" on a parcel you've already added. Peek asks 17FEIA only about numbers made of FEI, two letters, ten digits and two letters: for another one, the line says 17FEIA "doesn’t issue numbers like this one".

Peek shows the status, each line in 17FEIA's English with its place where it gives one, and the destination country. It reads the warehouse steps on Beijing time and the delivering carrier's scans on their place's local time, and shows times in your device's time zone. The flight and customs lines name no place, so Peek takes the clock of the lines around them: Beijing time until a line says the flight arrived, then the delivering carrier's clock if 17FEIA passes on its scans, else your country's. Those times can be a few hours off. Peek leaves out the progress bar, the origin, postcodes and the weight. 17FEIA gives no expected delivery date, service name, sender or recipient details, or proof of delivery.

When 17FEIA names DPD Germany, DHL or FedEx with a number of that carrier's shape, the parcel card says so ("Delivery with DPD Germany", for example) and lists that number after 17FEIA's. Once that carrier shows a scan at least as recent as 17FEIA's newest, Peek follows the parcel there and keeps 17FEIA's history. When 17FEIA's tracker can't be reached, Peek asks tracking services instead and shows their history, in their words.

Peek checks every 10 minutes, and every 2 minutes once the parcel is out for delivery. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only"; "Important steps" includes customs, so the export declaration in China counts. Peek can't contact 17FEIA, the seller or your forwarder for you.

## Questions about 17FEIA tracking

### What is 17FEIA (一起飞)?

A Chinese logistics company, also known as Shanghai Hongshan International Logistics (上海宏杉国际物流). It flies parcels out of China from its warehouse in Shenzhen or Shanghai and, after import customs, hands them to a carrier for delivery. Its numbers start with FEI and end with YQ.

### Where can I track a FEI…YQ number?

In the box above, or on 17FEIA's own tracker on www.17feia.com. Use the whole number: FEI, two letters, ten digits and YQ.

### What does "Awaiting carrie scan" mean on 17FEIA tracking?

The full line is `Arrived at delivery hub，Awaiting carrie scan.`: import customs are done, and 17FEIA handed the parcel in at the delivering carrier's depot. That carrier hasn't scanned it yet. Its own tracking may soon be ahead of 17FEIA's.

### Who delivers 17FEIA parcels?

The carrier 17FEIA hands it to after import customs. 17FEIA's record can name it, with its own number: when it's DPD Germany, DHL in Germany or FedEx, Peek's parcel card shows it. Otherwise, ask the seller.

:::sources
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty per item up to €150 since 1 July 2026, €2 Union handling fee from 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per item whatever the value, what an item is
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – names, number shape and branches, tracker and link, labels, lines and stages, progress bar, warehouses, clocks, partners, destinations, what Peek reads, the hand-over
:::
