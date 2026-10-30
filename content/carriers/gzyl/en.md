---
title: GZYL tracking: YL…YQ numbers, scans and who to ask
description: Track a GZYL (广州云链) parcel from Guangzhou: what a YL…YQ number looks like, what its scans mean and why its Delivered step can be written in advance.
slug: gzyl-tracking
published: 2026-10-30
updated: 2026-10-30
---

GZYL (广州云链, Guangzhou Yunlian) is 广州云链货运代理有限公司, a freight forwarder in Guangzhou, China. If a Chinese shop sent your order with GZYL, your number is YL, ten digits and YQ: paste it in the box above. Peek knows of no website for GZYL, and its tracker runs on a bare server address. Read that tracker with care: GZYL writes much of a parcel's journey in advance, so a step there, even `Delivered`, isn't always a scan.

## GZYL tracking number: what it looks like

GZYL's waybills are YL, ten digits and YQ, 14 characters in all. With invented digits:

| Number | What it is |
| --- | --- |
| `YL0000000000YQ` | YL, ten digits and YQ: the layout of GZYL's waybills. Peek checks it with GZYL's tracker. |
| `YL000000000YQ` | Only nine digits: not GZYL's layout. Check for a missing digit. |
| `GZXH0000000000YQ` | GZXH, ten digits and YQ: Xinghe Freight (星和货运), another forwarder. Its tracking runs on GZYL's tracker, in GZYL's words. |
| `JXCAA0000000000YQ` | Also ends in YQ, as many Chinese forwarders' numbers do, but starts with JXC: a [Juxi Express](carrier:juxi-express) number. |

Spaces and lower case don't matter when you paste the number into Peek.

**Where to find it.** The seller books the parcel with GZYL and passes the number on. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

## GZYL tracking statuses explained

GZYL writes its steps in English, typos included. In the order a parcel gets them:

| GZYL says | What it means for you | Peek shows |
| --- | --- | --- |
| `Order information received` | The seller has sent GZYL the order's details. GZYL doesn't have the parcel yet. | "Label created" |
| `Arrive at the cross border receiving point` | GZYL has the parcel: the first scan in its hands. | "Received by carrier" |
| `Goods leave the operation center` | The parcel has left GZYL's operation centre. | "In transit" |
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
| `Delivered` | The delivery, with the town. It may be a plan, not a scan: see below. | "Delivered" |

**A line in Chinese.** For an order with no scans yet, the platform GZYL's tracker runs on writes `货物电子信息已经收到` by itself: "the goods' electronic information has been received". The seller has created the order, but nobody has scanned the parcel. Peek shows the line in Chinese, under "Label created".

**Times.** Every time on GZYL's tracker is on Beijing's clock, steps in your country included, with no time zone shown. In winter, a step at 20:00 there is noon in London and 7am in New York.

**Steps written in advance.** GZYL can write a parcel's whole timeline ahead, from collection to `Delivered`, well before the times its steps show, and the tracker shows each one once its time comes. A step written that way is a plan, not a scan of your parcel. [Meiteshun](carrier:mtscn), on the same tracking platform, does this too. Tracking services that copy GZYL's record can show those steps too, so a `Delivered` on GZYL's tracker or on a tracking service doesn't prove your parcel arrived.

## Who delivers GZYL parcels

Peek doesn't know who delivers GZYL parcels in the US, the UK, Ireland, Canada or Australia: GZYL's steps in your country name no company. The only delivering company Peek can hand a GZYL parcel over to is [Swiss Post](carrier:swiss-post), in Switzerland and Liechtenstein.

Peek found no delivery times from GZYL, and no rules for delivery attempts, pickup points or how long a parcel is held: those are the delivering company's. Ask the seller which company delivers your order and for its tracking number.

## GZYL parcel not arriving or tracking stuck

**Only `Order information received` or `货物电子信息已经收到`.** The seller has created the order, but GZYL hasn't scanned the parcel. If nothing follows after a few days, ask the seller whether it has really been sent.

**Stuck at customs.** If you're asked for import charges, see [parcels held at customs](guide:customs). If the parcel stays there for days, tell the seller.

**GZYL's tracker or a tracking service shows more than Peek.** Peek holds back steps written ahead of the time they show and steps dated after its last check (below), so the extra steps may be GZYL's plan, not scans. Go by the delivery estimate the shop gave, and ask the seller once it has passed. More in [why tracking stops updating](guide:tracking-not-updating) and [tracking a parcel from China](guide:tracking-from-china).

**`Delivered`, but nothing came.** The step may be part of GZYL's plan, not a scan. Tell the seller the parcel hasn't arrived, and ask which company delivers it and for its number. More in [delivered but not received](guide:delivered-not-received).

**Nothing found.** For a number it doesn't know, GZYL's tracker shows one result with your number under 参考号 (reference) and every other field empty. Peek keeps checking, but compare the number with the seller's message.

**Lost or returned.** The seller booked the parcel with GZYL, so ask the seller, or the marketplace, for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## GZYL contact number and customer service

As of 30 October 2026, Peek found no contact details for GZYL: no website besides its tracker, and no phone number or email for recipients. The seller booked the parcel and is the one to ask about your order, and the marketplace if the seller doesn't answer.

## Track a GZYL parcel with Peek

[Peek](/) can't be sure of a GZYL number by its shape alone: YL, ten digits and YQ is only a weak match. Paste it all the same: Peek follows more than 3,500 carriers. While you add it, Peek asks GZYL's tracker ("Checking tracking services…") and shows "GZYL has this parcel." when the tracker knows the number. Otherwise press "Change" and pick GZYL under "Fits this number" (typing 广州云链 finds it too), or use "Change carrier" on a parcel you've already added. A seller's reference works the same way, and Peek then shows the order's own number, labelled "GZYL tracks this as". Peek drops dashes, though, so a reference with dashes isn't found: ask the seller for the YL…YQ number.

Peek reads GZYL's own tracker and shows the status, each step with its place, and the service name and destination country when GZYL's record gives them. Times are read on Beijing time and shown in your device's time zone. Peek leaves out the names, address, weight, fees, signature and proof of delivery GZYL's record can hold, and shows no expected delivery date: GZYL gives none.

Peek holds back any step written more than twelve hours before the time it shows, and any step dated after the moment it checks, on Beijing time. It goes by the steps it keeps, not by the record's summary status. An order whose steps are all held back has no tracking history in Peek yet, and Peek sets aside tracking services' copies of the steps written ahead too. When GZYL's tracker can't be reached, though, Peek asks tracking services instead, and their copies can include steps GZYL wrote ahead.

Peek stays with GZYL's tracker: if the seller gives you the delivering company's number, add it as a parcel of its own. Peek checks every 10 minutes by day and every hour or two overnight. It can notify you of every scan, important steps only or the delivery only: "Important steps" covers GZYL's two customs steps and `Delivered`, not its flights or sorting. Peek can't contact GZYL or the seller for you.

## Questions about GZYL tracking

### What is GZYL (广州云链)?

广州云链货运代理有限公司 (Guangzhou Yunlian), a freight forwarder in Guangzhou, China. Its waybills are YL, ten digits and YQ.

### Is every YL…YQ number a GZYL number?

Not for sure. YL, ten digits and YQ is GZYL's layout, and no other carrier in Peek's catalog uses it, but Peek still asks GZYL's tracker before it counts the number as GZYL's. With nine or eleven digits, it isn't GZYL's layout.

### Why does GZYL say my parcel was delivered when it wasn't?

It may be a step GZYL wrote in advance. GZYL writes much of a parcel's timeline ahead, `Delivered` included, and a step written that way is a plan, not a scan. Peek holds such steps back from GZYL's tracker. Tell the seller the parcel hasn't arrived and ask which company delivers it.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – company and other names, the tracker on a bare address, YL…YQ number shape and its weak match, Xinghe Freight on GZYL's tracker, search by number or reference with its dashes, step wordings and their stages, the platform's Chinese line, towns after wordings, Beijing clock, timeline written in advance, the answer for an unknown number, Swiss Post as the only hand-over, what Peek shows, holds back and leaves out
:::
