---
title: Meiteshun tracking: AMTS numbers, scans and who to ask
description: Track a Meiteshun Logistics (美特顺) parcel from Shenzhen: what an AMTS number looks like, what its steps mean and why its Delivered step can be written in advance.
slug: meiteshun-tracking
published: 2026-10-28
updated: 2026-10-28
---

Meiteshun Logistics (美特顺物流, 深圳市美特顺物流有限公司) is a forwarder in Shenzhen, China. Its numbers start with AMTS and end with FS: if the shop that sent your order gave you one, paste it in the box above. Read Meiteshun's tracking with care: its system writes a parcel's whole journey, delivery included, when the order is created, so a "Delivered" there doesn't prove your parcel arrived.

## Meiteshun tracking number: what it looks like

Meiteshun's waybills are AMTS, nine digits and FS. With invented digits:

| Number | What it is |
| --- | --- |
| `AMTS000000017FS` | AMTS, nine digits and FS: a Meiteshun waybill. |
| `AMTS00000001FS` | Only eight digits: not Meiteshun's layout. Check for a missing digit. |
| `RR000000005CN` | Two letters, nine digits and CN: a [China Post](carrier:china-post) number, not Meiteshun's. |

Lower case and spaces don't matter when you paste it into Peek.

**Where to find it.** The seller books the parcel with Meiteshun and passes the number on. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

**A shop's reference.** Meiteshun's tracker also finds an order by the seller's own reference, typed exactly as given, dashes included, and then shows the AMTS number.

**A link with numbers instead of a name.** Meiteshun's tracker runs on a bare server address over plain HTTP, not on a web domain; its login page carries the name 美特顺物流. A link to it has that numeric address where a site's name would be, and holds `trackIndex.htm`, `documentCode=` and your number or reference: you can paste it into Peek as it is.

## Meiteshun tracking statuses explained

Every Meiteshun order follows the same script, whatever the destination, and most of its steps are written in advance (below). The wordings, in the order the script runs:

| Meiteshun says | What it means for you |
| --- | --- |
| `The package has been collected and is ready to be sent to the distribution center` | The first step: Meiteshun's collection in Shenzhen. |
| `item inbound in sorting center` | At a sorting centre, with the place `ShenZhen`. |
| `item outbound in sorting center` | Leaving that sorting centre. |
| `Arriving at the terminal` | At an airport terminal, with the place `Hong Kong`. |
| `Leaving the terminal, the airline will take off` | The flight leaves Hong Kong. |
| `Arriving at the destination country` | Landed in your country, shown by its two-letter code (`US`, `GB`…). |
| `Customs clearanceisin progress` | Import customs in your country. The missing spaces are Meiteshun's. |
| `Customs clearance completed` | Through customs and on its way again. |
| `Arriving at the mail processing center` | At a processing centre in your country. |
| `Arrive at the sorting and distribution center` | At a sorting centre near you, with your town or region. |
| `package is on the way` | The step before the delivery. It doesn't say the parcel is out for delivery. |
| `Delivered` | The script's last step (below). |

A step's place comes before a comma at its start, and steps in your country add your town or region and postcode after the wording. Some orders' steps carry no place at all. Every time is on Beijing's clock, even for steps in your country: a step at 21:00 on Meiteshun's tracker is the afternoon in London and the morning in New York.

**Steps written in advance.** Meiteshun runs its tracking on its own tracker on Hualei (华磊), a forwarder platform that other Chinese forwarders such as [Pengyou](carrier:pengyou) use too. On Meiteshun's, all of an order's steps are written when the order is created, each dated ahead, from the collection to `Delivered`. They're a forecast, not scans of your parcel: they say where the parcel was planned to be on a date. A buyer reported a parcel the script showed as delivered that never came. Tracking services that copy Meiteshun's record show the same steps.

## Who delivers Meiteshun parcels

Meiteshun's records don't name the company that delivers in your country, and its steps there don't name one either. The service name on its records is MTS全球小包 ("MTS global small parcel").

Peek found no delivery times from Meiteshun, and no rules for delivery attempts, pickup points or how long a parcel is held. Ask the seller which company delivers your order and for its number: that company's tracking and rules are the ones that count. If you're asked for import charges, see [parcels held at customs](guide:customs).

## Meiteshun parcel not arriving or tracking stuck

**"Delivered", but nothing came.** The step may be the script's forecast, not a scan. Tell the seller the parcel hasn't arrived and ask for the delivering company's number. More in [delivered but not received](guide:delivered-not-received).

**Every step on time, but no parcel.** The steps were written when the order was created, so they show the plan, not where the parcel is. Go by the delivery estimate the shop gave, and ask the seller once it passes.

**Stuck in Shenzhen in Peek.** If Peek shows only the first steps in Shenzhen while a tracking link from the seller shows more, the steps Peek leaves out were written in advance or are dated after its check (below). More in [why tracking stops updating](guide:tracking-not-updating) and [tracking a parcel from China](guide:tracking-from-china).

**Nothing found.** For a number it doesn't know, Meiteshun's tracker shows the number as a reference with every other field empty. Check the number with the seller.

**Lost, damaged or returned.** The seller booked the parcel with Meiteshun, so ask the seller or the marketplace for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## Meiteshun contact number and customer service

As of 28 October 2026, Peek found no contact details for Meiteshun on a page it could read: no phone, email or address for recipients. For anything about your order, the seller is the one to ask, and the marketplace if the seller doesn't answer.

## Track a Meiteshun parcel with Peek

[Peek](/) recognises a Meiteshun number by its shape: AMTS, nine digits and FS, which no other carrier in its catalog uses. A link to Meiteshun's tracker with `documentCode=` and the number or the seller's reference in it can be pasted too. Peek can't tell a seller's reference by its shape: paste it all the same, as Peek follows more than 3,500 carriers, then press "Change" and pick Meiteshun Logistics (typing 美特顺 or MTSCN finds it too), or use "Change carrier" on a parcel you already added. Peek then shows the order's AMTS number too. Peek drops dashes, though, typed or in a link, so look up a reference with dashes on Meiteshun's own tracker.

Peek reads Meiteshun's own tracker and shows the status, each scan with its place, the service name and the destination country. Scans show as Meiteshun writes them: `Customs clearanceisin progress` counts as customs, and `package is on the way` as in transit, not out for delivery. Peek keeps your town from the steps in your country and leaves out the postcode. Scan times are read on Beijing time and shown in your device's time zone. Peek leaves out the names, address, weight, fees, signature and proof of delivery Meiteshun's record can hold, and shows no expected delivery date: Meiteshun gives none.

Peek holds back a step written more than twelve hours before the time it carries, and any step dated after the moment it checks, on Beijing time. It goes by the steps it keeps, not by the record's summary status. When an order's whole script was written at its creation, Peek keeps only the steps dated before that moment or up to twelve hours after it, so the `Delivered` written then is held back. An order whose steps are all held back has no tracking history in Peek yet, and Peek then sets aside the copies tracking services make of those steps too.

Meiteshun's records name no delivering company, so Peek follows the parcel on Meiteshun's tracker only. If the seller gives you the local carrier's number, add it as a parcel of its own.

Peek checks every 10 minutes by day and every hour or two overnight, and can notify you of every scan, important steps only or "Delivery only". It can't contact Meiteshun or the seller for you.

## Questions about Meiteshun tracking

### What is Meiteshun Logistics (美特顺)?

It is 深圳市美特顺物流有限公司, Shenzhen Meiteshun Logistics, a forwarder in Shenzhen, China. Its waybill numbers are AMTS, nine digits and FS, and the service name on its records is MTS全球小包.

### Is an AMTS number a China Post number?

No. AMTS, nine digits and FS is Meiteshun's layout. China Post's international numbers are two letters, nine digits and CN.

### Why does Meiteshun say my parcel was delivered when it wasn't?

Most likely because Meiteshun's system writes every step of an order, delivery included, when the order is created. A "Delivered" written that way is a forecast, not a scan. Tell the seller the parcel hasn't arrived.

### Who delivers Meiteshun parcels in my country?

Meiteshun's records don't say: they name no delivery company. Ask the seller which company delivers your order and for its tracking number.

### Who do I contact about a missing Meiteshun parcel?

The seller you ordered from: it booked the parcel with Meiteshun. Peek found no contact details for Meiteshun on a page it could read.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – company name, Hualei platform, AMTS number shape, the tracker and its links, search by reference, the script and its wordings, places and Beijing clock, steps written in advance and the parcel shown delivered that never came, the answer for an unknown number, service name, no delivery company named, what Peek shows, holds back and leaves out
:::
