---
title: HuaHan Logistics tracking: HHW…YQ numbers and who delivers
description: Track a HuaHan Logistics (华翰物流) parcel from China: what an HHW…YQ number looks like, what its scans mean and how to find who delivers it in your country.
slug: huahan-logistics-tracking
published: 2026-11-02
updated: 2026-11-14
---

HuaHan Logistics (华翰物流, also HuaHan Express or 华翰速递) is a Chinese carrier that flies parcels out of China and hands them to a local carrier in the destination country, which delivers them. If a shop sent your order with HuaHan, look for a number that starts with HHW and ends with YQ, and paste it in the box above. HuaHan's own tracker is on its website, hh-exp.cn, and can name the carrier that delivers near you, with that carrier's number for your parcel.

## HuaHan tracking number: what it looks like

Peek knows HuaHan's waybills as HHW, two letters, ten digits and YQ, 17 characters in all. With invented digits:

| Number | What it is |
| --- | --- |
| `HHWAA0000000000YQ` | HHW, two letters, ten digits and YQ: a HuaHan waybill. |
| `HHWAA000000000YQ` | Only nine digits: not HuaHan's layout. Check for a missing digit. |
| `WNBAA0000000000YQ` | Also ends in YQ, but opens with WNB: a [WanbExpress](carrier:wanbexpress) number. |
| `JXCAA0000000000YQ` | JXC in front: a [Juxi Express](carrier:juxi-express) number. |

Many Chinese forwarders end their waybills in YQ, each with its own letters in front: HHW is what marks HuaHan, and no other carrier in Peek's catalog uses it. Spaces, dashes and lower case don't matter when you paste the number into Peek.

**Where to find it.** The shop gives the number in its shipping email or on your order page. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

**A second number.** HuaHan's tracker can also show the local carrier's own number for the parcel. HuaHan sometimes adds a suffix after an underscore that the carrier's number doesn't have: leave it out when you look the number up on that carrier's site.

## HuaHan tracking statuses explained

HuaHan's tracker lists each scan with a time, a wording and a place, newest first. Its own scans are in English, some with the Chinese first. Here they are in the order Peek's code lists them:

| HuaHan says | What it means for you | Peek shows |
| --- | --- | --- |
| `快件电子信息已经收到 Shipment information received`, `Shipment information received` | The shop has sent HuaHan the parcel's details. HuaHan doesn't have the parcel yet. | "Label created" |
| `快件到达作业中心 Shipment arrived at facility`, `Has entered the warehouse` | HuaHan's centre in China has the parcel: in the histories Peek's code holds, in Shenzhen. | "Received by carrier" |
| `快件操作完成 Shipment departed from facility` | Processed, and on its way out of that centre. | "In transit" |
| `Transfer to Origin airport` | On its way to the airport. | "In transit" |
| `Export customs clearance completed` | Export customs on the way out are done. Not your country's customs. | "In transit" |
| `Departed from airport of Origin` | The flight has left. | "In transit" |
| `Import Customs clearance started` | Customs in the destination country have it. | "At customs" |
| `Arrived at destination airport` | The flight has landed in the destination country. | "In transit" |
| `Import Customs clearance completed` | Import customs have let it through. | "In transit" |
| `Has been given to local carrier` | HuaHan has handed it to the carrier that delivers it. That carrier's own lines follow. | "In transit" |

**The local carrier's lines.** After the hand-over, HuaHan's tracker passes on the local carrier's scans. Peek reads them by HuaHan's code for the step, where there is one, or by their wording. Wordings Peek's code has met and credits to that carrier:

| After the hand-over | What it means for you | Peek shows |
| --- | --- | --- |
| `Ready to Ship, Parcel received and processed` | The local carrier has the parcel. | "In transit" |
| `The parcel is in the central warehouse waiting to be processed.`, `In Transit, Shipment arrived to sorting centre / Shipment in the sorting centre`, `In Transit, Shipment remained at sorting hub` | At the local carrier's warehouse or a sorting centre, or still there. | "In transit" |
| `In Transit, Arrived to HU HUB`, `In Transit, Parcel has entered the Linehaul network.` | At the local carrier's hub in Hungary, then moving between its hubs. | "In transit" |
| `In Transit, Picked Up` (followed by DPD's German `Einrollung`), `In Transit, Scanned By DPD At parcel delivery centre.`, `In Transit, At parcel delivery centre.` | At DPD's delivery depot: its arrival scan there, not a pickup from you, then a scan for each day the parcel waits there. | "In transit" |
| `Out for Delivery, Parcel is at courier, delivery expected soon.` | On the delivery run. | "Out for delivery" |
| `The courier will deliver the parcel soon. Additional details are available in the SAMEDAY app, email or SMS.` | On the delivery run, in the words of [Sameday](carrier:sameday), a courier in Romania and Bulgaria. | "Out for delivery" |
| `Delivery Attempted`, `Delivery Attempted, Unfortunately we have not been able to deliver your parcel. Pickup parcelshop delivery after first delivery attempt.` | DPD tried to deliver it and couldn't. With the longer line, the parcel goes on to a pickup point. | "Delivery attempted" |
| `Awaiting Pickup, Parcel was deposited in Pickup parcel station` | After that attempt, DPD has left the parcel at a pickup point for you to collect. | "Ready for pickup" |
| `The parcel has been successfully delivered.` | Delivered. | "Delivered" |

HuaHan files the local carrier's out-for-delivery, pickup-point and delivery scans under codes of its own, and Peek shows them as "Out for delivery", "Ready for pickup" or "Delivered", unless the words name another step: HuaHan also puts USPS's departure from a distribution centre under its out-for-delivery code.

**Two lines that confuse people.** `We will pick up your parcel from the sender soon.` can turn up near the start, before HuaHan has even received the parcel in China: it's the local carrier's notice that it has been told about the parcel, not a pickup from you. Peek shows "Label created". `The parcel was redirected. As soon as it is in the couriers possession, it will be processed as requested.` is that carrier noting a redirection you asked for, also written while the parcel is still in China. It moves nothing, and Peek gives it no status of its own.

**Data, not scans.** `The instruction data for this shipment have been provided by the sender to DHL electronically.`, sometimes without the full stop, is DHL's record of the parcel's data for the last leg, passed on before HuaHan has the parcel in China or after it has left there. `货物电子信息已经收到` (the goods' electronic data received), in Chinese, is the order's data, and can come after HuaHan has the parcel. Neither is a movement: Peek shows "Label created", or keeps the status the parcel already had.

**One scan, two lines.** HuaHan's tracker can list one of the local carrier's scans twice at the same time: in that carrier's words with its place, and with no place in HuaHan's generic words, such as `Delivered, Parcel was delivered successfully`. Peek keeps the line with the place.

**By post.** HuaHan can also send a parcel through China Post. Its tracker then passes on China Post's scans in Chinese, with the office in 【】, and later the destination post's scans as China Post copies them. Peek shows them in Chinese, as HuaHan passes them on:

| HuaHan passes on | What it means for you | Peek shows |
| --- | --- | --- |
| `中国邮政已收取邮件` | China Post has the parcel from HuaHan: from here it goes by post. | "In transit" |
| `邮件已在【…】完成分拣，准备发出`, `邮件到达【…】`, `邮件离开【…】，正在发往【…】`, `邮件离开【…】，正在发往下一站` | Sorted at, arrived at or leaving a China Post office in China, named in 【】. | "In transit" |
| `送交出口海关` | Handed to export customs in China. Not your country's customs. | "At customs" |
| `出口海关/放行` | Export customs have released it. | "In transit" |
| `已交承运商运输` | Handed to the airline that flies it abroad. | "In transit" |
| `到达寄达地`, `到达寄达地处理中心`, `到达境外经转局` | Arrived in the destination country, at its post's processing centre, or at a transit office on the way. | "In transit" |
| `境外进口海关留存待验(等待提交给边境机构/安全部门)` | Held for your country's import customs; the brackets say it's waiting to be presented to the border or security authorities. | "At customs" |
| `境外进口互换局留存`, `送交境外进口海关` | Kept at the destination post's office of exchange, then handed to import customs. | "At customs" |
| `寄达地海关允许进口（无需缴税）` | Import customs have let it in, with no duty to pay. | "In transit" |
| `离开【…】处理中心`, `已到达【…】投递局` | Left the processing centre in the country named in 【】, then arrived at the delivery office. | "In transit" |

**Through Landmark Global.** For some parcels, HuaHan's tracker relays the lines of [Landmark Global](carrier:landmark-global), in English. The first two come before HuaHan has the parcel in China; the others follow Landmark Global's hand-off to [bpost](carrier:bpost)'s network, which delivers:

| HuaHan relays | What it means for you | Peek shows |
| --- | --- | --- |
| `Shipment Data Uploaded` | Landmark Global's record of the sender's data. Not a movement. | "Label created" |
| `Processed` | Landmark Global's processing, in the same minute as that record. Not a receipt of the parcel: Peek reads it as transit. | "In transit" |
| `Incoming scan at facility` | A depot has the parcel, after the hand-off to bpost's network. | "In transit" |
| `Outgoing scan at facility` | It has left that depot. The delivery round can still be days away. | "In transit" |
| `Out for delivery (On the way)` | On the delivery round. | "Out for delivery" |
| `Delivery attempt (Delivery not possible)` | A delivery round that didn't deliver it. | "Delivery attempted" |

HuaHan files `Outgoing scan at facility` under its out-for-delivery code, but the words name a depot departure, so Peek shows "In transit".

## Who delivers HuaHan parcels

After `Has been given to local carrier`, a carrier in the destination country delivers the parcel, and HuaHan's tracker can name it and give its number. Peek couldn't check HuaHan's website, so this page has no list of HuaHan's partners.

**What Peek's code shows.** One HuaHan history shows Sameday delivering: its out-for-delivery line points to the SAMEDAY app. Peek's code also expects [USPS](carrier:usps) numbers from HuaHan: when HuaHan gives USPS's long label barcode, Peek keeps USPS's own tracking number from it. [DHL](carrier:dhl) is booked for the last leg of some parcels: the data line quoted above is its record of the sender's data. DPD delivers some: its depot scans, such as `In Transit, Scanned By DPD At parcel delivery centre.`, are in the table above. [CTT Express](carrier:ctt-express) is announced for the last leg of others: HuaHan's tracker passes on its notice `Pendiente de recepción en CTT Express` ("awaiting receipt at CTT Express") hours before HuaHan has the parcel in China, and Peek shows "Label created". Others go through [Landmark Global](carrier:landmark-global): HuaHan's tracker relays its lines from its record of the sender's data to the delivery round, and after Landmark Global's hand-off, bpost's network delivers (see "Through Landmark Global" above). Beyond these, the code ties no carrier to any country: Peek goes by the one HuaHan names for your parcel.

**Delivery attempts and pickup.** Attempts, notice cards and where a missed parcel waits, and for how long, follow the local carrier's rules: ask that carrier, with its number, to rearrange a delivery. See [missed delivery](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

**Customs in the EU.** Goods bought online from outside the EU carry a €3 customs duty per item on parcels worth up to €150, since 1 July 2026, and a €2 Union handling fee per item, whatever the value, since 1 November 2026. An item is the goods sharing a tariff classification, description and, where required, origin. Peek couldn't check whether HuaHan charges these to the seller beforehand: ask the shop whether your price included them. Elsewhere, what you owe depends on your country. See [parcels held at customs](guide:customs).

## HuaHan parcel not arriving or tracking stuck

**Number not found.** When HuaHan has no record of the number, its tracker answers 没有找到记录 ("no record found"), and Peek shows "Waiting for the carrier" and keeps checking. Check the number against the seller's message, character by character: HHW, two letters, ten digits, YQ. If nothing comes after a few days, ask the seller whether the parcel was really sent.

**Stuck on `Shipment information received`.** The shop has booked the shipment, but HuaHan hasn't scanned the parcel yet. Ask the shop when it handed the parcel over.

**Stuck at `Import Customs clearance started`.** Customs in your country have the parcel. If you're asked to pay import charges, see [parcels held at customs](guide:customs); if it stays there for long, tell the seller.

**Nothing after `Has been given to local carrier`.** The local carrier has the parcel, and its own tracking may be ahead of HuaHan's. Find its name and number on HuaHan's tracker, or on Peek's parcel card when Peek knows that carrier, or ask the seller which carrier delivers.

**No update for days.** When a parcel still on its way has had no new scan for four days, Peek flags it ("No tracking update for four days") and notes that parcels often go quiet on long flights, at customs or over a weekend. See [why tracking stops updating](guide:tracking-not-updating) and [tracking parcels from China](guide:tracking-from-china).

**Delivered, but nothing came.** Check where it was left and ask neighbours, then the local carrier and the seller. See [delivered but not received](guide:delivered-not-received).

**Lost or returned.** The seller booked the parcel with HuaHan, so the seller, or the marketplace, is the one to ask for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## HuaHan contact number and customer service

As of 31 October 2026, Peek couldn't check HuaHan's website, so this page lists no contact details for HuaHan. The seller booked the parcel with HuaHan and is the one to ask about your order, and the marketplace if the seller doesn't answer. For the delivery itself, ask the local carrier.

## Track a HuaHan parcel with Peek

[Peek](/) names HuaHan Logistics at once from an HHW…YQ number ("Detected carrier"), and asks HuaHan's tracker by that whole number only: no account, postcode or phone number is needed. For an order number, ask the seller for the HHW…YQ waybill. If all you have is the local carrier's number, paste that instead: Peek follows more than 3,500 carriers.

Peek shows the status, each scan with its place, and the destination country on HuaHan's record. HuaHan's tracker prints times with no time zone. Peek reads HuaHan's own scans on the clock of their place or of the leg the parcel was on: Beijing time in China. The local carrier's scans after `Has been given to local carrier` are read on your country's clock when it has one time zone. Every time is shown in your device's time zone. HuaHan gives no expected delivery date or weight, so Peek shows neither.

When HuaHan names a local carrier Peek knows, with a number of that carrier's shape, the parcel card says so ("Delivery with USPS", for example, or "Announced to USPS, not received yet" while that carrier has only the announcement of the parcel, with no scan of its own) and lists that carrier's number after HuaHan's. Peek then asks that carrier too. If HuaHan gives a postal number (two letters, nine digits, two letters) and no carrier Peek knows, Peek asks the destination country's post; for another number, the one carrier its shape points to. Once that carrier shows a scan at least as recent as HuaHan's newest, Peek follows the parcel there and keeps HuaHan's history. If HuaHan gives no number for the local carrier, Peek stays with HuaHan's tracker, which goes on passing that carrier's scans.

When HuaHan's tracker can't be reached, Peek asks tracking services instead and shows the history they hold, in their words.

Peek checks every 10 minutes, and every 2 minutes from 6 a.m. local time on the day the parcel goes out for delivery, while HuaHan's own tracker answers (through tracking services, every 15 minutes). When another carrier takes over the delivery, Peek follows the pace of the carrier delivering the parcel: hourly for Royal Mail and Poste Italiane, every 30 minutes for PostNL and USPS, out for delivery too. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". "Important steps" covers `Import Customs clearance started`, the customs scans of China Post that HuaHan passes on in Chinese for a parcel it sends by post (such as `送交出口海关` and `送交境外进口海关`), the delivery run, a pickup point and the delivery, not flights or HuaHan's own export-clearance scans: the export clearance on the way to the airport, `快件从转运仓发出至机场，出口报关中（ The package is shipped from the transfer warehouse to the airport Export customs clearance in progress ）`, is a routine step before the flight, and Peek shows it as "In transit". Peek can't contact HuaHan or the seller for you.

## Questions about HuaHan tracking

### What is HuaHan Logistics (华翰物流)?

A Chinese carrier, also called HuaHan Express or 华翰速递, that ships parcels abroad. In the histories Peek's code holds, its scans start at its centre in Shenzhen, follow the flight and customs, and end with the scans of the local carrier that delivers in the destination country.

### Where can I track a HuaHan parcel?

In the box above, or on HuaHan's own tracker on hh-exp.cn. That tracker can ask you a simple sum before it shows a parcel. Use the whole HHW…YQ number.

### What does "Has been given to local carrier" mean on HuaHan tracking?

Import customs have let the parcel through, and HuaHan has handed it to the carrier that delivers it in your country. That carrier's scans follow in HuaHan's history.

### Who delivers HuaHan parcels in my country?

A local carrier, which HuaHan's tracker can name, with its number for the parcel. Peek's code shows Sameday in one history and DPD's depot scans, reads USPS numbers, holds DHL's data and CTT Express's notice for the last leg of some parcels, and reads Landmark Global's lines for others, with bpost's network delivering after its hand-off: check HuaHan's tracker or ask the seller.

### Who do I contact about a missing HuaHan parcel?

The seller you ordered from: it booked the parcel with HuaHan. Peek couldn't check HuaHan's website for contact details.

:::sources
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty per item up to €150 since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per item whatever the value, what an item is
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – names, HHW number shape, the tracker and its sum check, scan wordings, codes and their stages, the local carrier's relayed scans and duplicate lines, Sameday's wording, DPD's depot scans, attempts and pickup point, CTT Express's notice, China Post's and the destination post's scans in Chinese on the postal line and their stages, Landmark Global's relayed lines, the hand-off to bpost's network and their stages, the local carrier's name and number with the underscore suffix and USPS barcodes, clocks, destination country, no delivery date or weight, the hand-off
:::
