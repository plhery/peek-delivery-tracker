---
title: TopYou tracking: TYZR numbers, scans and who delivers
description: Track a TopYou (通邮) parcel from China: what a TYZR…YQ number looks like, what its English scans mean and who takes the parcel over in your country.
slug: topyou-tracking
published: 2026-10-28
updated: 2026-11-13
---

TopYou (通邮, Tongyou, also 通邮集团 or SZTY56) is 深圳市通邮物流科技（集团）股份有限公司, a cross-border forwarder in Shenzhen, China, that runs international lines. If a Chinese online shop sent your order with TopYou, the shop gives you a number that starts with TYZ and ends with YQ: paste it in the box above. TopYou's history runs from its warehouse in China through the flight and customs, and for parcels to the United States on to USPS's delivery.

## TopYou tracking number: what it looks like

TopYou's waybills are TYZR, one letter, ten digits and YQ, 17 characters in all; some have another letter in place of the R. With invented digits:

| Number | What it is |
| --- | --- |
| `TYZRA0000000017YQ` | TYZR, a letter, ten digits and YQ: a TopYou waybill. |
| `TYZRA000000017YQ` | Only nine digits: not TopYou's layout. Check for a missing digit. |
| `JXCAB0000000017YQ` | Also ends in YQ, as other Chinese forwarders' waybills do, but opens with JXC: a [Juxi Express](carrier:juxi-express) number. |

Spaces and lower case don't matter when you paste the number into Peek. TopYou's own tracker, [track.szty56.com](https://track.szty56.com/), tells capitals from lower case, so type the number there in capitals. If you can't find it in your order's details, see [how to find your tracking number](guide:find-tracking-number).

**A second number.** TopYou's record can also hold the delivering carrier's own number, which TopYou's tracker shows.

**Copy the number, not a link.** Peek reads no link from TopYou's tracker: paste the number itself.

## TopYou tracking statuses explained

TopYou writes its scans in English. In the order a parcel gets them:

| TopYou says | What it means for you | Peek shows |
| --- | --- | --- |
| `Parcel Data Received` | The shop has sent TopYou the parcel's details, but TopYou doesn't have the parcel yet. | "Label created" |
| `Arrived at the first mile sorting center` | TopYou's warehouse in China has the parcel: the first scan in TopYou's hands. | "Received by carrier" |
| `Departed from the first mile sorting center` | It has left that warehouse. | "In transit" |
| `Arrived at airport of the original country` | At the airport it flies from, before the flight. | "In transit" |
| `Departed from Airport to destination country` | The flight to your country has left. | "In transit" |
| `Arrived at destination airport` | Landed in your country. | "In transit" |
| `Departed from customs of` | Your country's customs have released it. TopYou leaves the country after "of" blank, and Peek drops the "of". | "In transit" |
| `At U.S. Postal Service facility, Accepted by U.S. Postal Service` | In the US: USPS has taken the parcel over from TopYou after the flight. It isn't a new shipment. | "In transit" |

**Newer waybills.** TopYou words some of the same steps differently on its newer waybills:

| TopYou says | What it means for you | Peek shows |
| --- | --- | --- |
| `Carrier received the shipment` | TopYou has the parcel in China: its first scan. | "Received by carrier" |
| `Shipment arrived at a carrier facility` | Moving on from TopYou's first warehouse. The parcel isn't being accepted again. | "In transit" |
| `Shipment started the customs clearance process at origin` | China's export clearance, a routine step before the flight. Customs aren't holding it, and it isn't your country's import customs. | "In transit" |
| `Shipment arrived in the destination country` | It has reached your country. | "In transit" |
| `Shipment started the customs clearance process at destination` | Your country's import customs have it. | "At customs" |
| `Shipment completed the customs clearance process at destination` | Cleared, and on its way to the delivering carrier. | "In transit" |
| `Shipment is out for delivery` | Not out for delivery yet, whatever the words say: in France and Italy, it marks the hand-over to GOFO at its first sorting centre, days before GOFO's driver sets out. | "In transit" |

**Lines a post delivers.** On a line your country's post delivers, TopYou words its scans yet another way, up to your country's customs:

| TopYou says | What it means for you | Peek shows |
| --- | --- | --- |
| `Accepted by carrier` | TopYou has the parcel in China: its first scan. | "Received by carrier" |
| `Inbound in sorting center` | Booked in at TopYou's first warehouse, minutes later. It isn't moving yet. | "Received by carrier" |
| `Outbound in sorting center` | It has left that warehouse. TopYou adds `, Lastmile Number:` and the number the post delivers it under, and Peek shows the line with that number. | "In transit" |
| `Received by line-haul`, `Hand over to airline` | Taken on for transport, then handed to the airline. | "In transit" |
| `Airline arrived at transit country or district.`, `Airline departed from transit or district country.` | A stop on the way to your country. | "In transit" |
| `Airline arrived at destination country`, `Arrive at destination country` | It has reached your country. | "In transit" |
| `Export clearance success` | China's export customs have cleared it. It isn't held. | "In transit" |
| `Import clearance success` | Your country's customs have cleared it for the post. The post can still show customs scans of its own. | "In transit" |
| `Customs Clearance Completed Successfully` | Your country's customs have cleared it, minutes after the hand-over to the delivering carrier, which now has the parcel. | "In transit" |

**The delivering carrier's scans.** After the hand-over, TopYou's tracker can go on with the delivering carrier's own scans: USPS's on a parcel to the United States, GOFO's in France and Italy, such as `Arrived at sorting center`, `The package left sorting center` and `Package departed from sorting center` (at or leaving one of GOFO's sorting centres: "In transit"), `Arrived at GOFO Regional Destination Facility` (at GOFO's regional depot: "In transit") and `The driver is out for delivery` (GOFO's driver is bringing it: "Out for delivery").

In Germany, iMile's scans come as iMile's name for the step, a comma and iMile's sentence: `In Transit, Your order has been shipped from 【iMile Köln DC Center】.` (sent on from an iMile centre: "In transit"), `Delivery, Your order has arrived at 【Dortmund01】` (at the iMile station it is delivered from: "In transit"), `Delivery, Your order has assigned delivery drvier.` (a driver is named but hasn't set out; the misspelling is iMile's: "In transit") and `Delivery, Our delivery associate is out for delivery.` (iMile's driver is bringing it: "Out for delivery"). So a line that opens with `Delivery` doesn't mean the driver has set out. An iMile line that moves nothing, such as a stock count, leaves the status as it was. A pickup point's notice that opens `Arrived, The parcel has been delivered to 【】. Please collect your parcel promptly at 【】 using your pickup code.` means the parcel is waiting there for you to collect it with your pickup code: "Ready for pickup".

In Austria, DPD's scans come in DPD's English: `In parcel delivery center` (at DPD's delivery depot: "In transit"), `In delivery` (DPD's driver is bringing it: "Out for delivery"), `Unfortunately, consignee not present. Notification was left.` (nobody was in, and DPD left a card: "Delivery attempted"), `Parcel was handed over to Pickup parcel shop` and `Takeover by Pickup parcel shop` (it waits for you at a DPD Pickup parcelshop: "Ready for pickup") and `Parcel was not picked up` (not collected in time, so DPD sends it back to the sender: "Needs attention").

In Spain, the delivering partner's scan after customs can read `Parcel in transit` (it's on its way to you: "In transit").

## Who delivers TopYou parcels

**United States.** USPS takes the parcel over from TopYou's line after the flight, and TopYou's tracker can go on with its scans. The second number on TopYou's record is then the one [USPS](carrier:usps) tracks it by.

**France and Italy.** GOFO can deliver: [GOFO France](carrier:gofo-fr) or [GOFO Italy](carrier:gofo-it). TopYou's tracker shows the hand-over as `Shipment is out for delivery`, then GOFO's scans.

**Germany.** [iMile](carrier:imile) can deliver, and TopYou's tracker then goes on with iMile's scans.

**Austria.** [DPD Austria](carrier:dpd-at) can deliver, and TopYou's tracker then goes on with DPD's scans.

**Elsewhere.** TopYou's record can give the delivering carrier's number, but not its name, and the name of the TopYou line your parcel travels on doesn't tell you either: a line can bear another carrier's name. On a line a post delivers, the post's number also ends the `Outbound in sorting center` line, after `Lastmile Number:`. So ask the seller which company delivers your order.

## TopYou parcel not arriving or tracking stuck

**Stuck on `Shipment started the customs clearance process at destination`.** Your country's customs have the parcel. If you're asked for import charges, which in the EU now include a €2 handling fee per item (due since 1 November 2026), see [parcels held at customs](guide:customs); if it stays there for days, tell the seller. More in [why tracking stops updating](guide:tracking-not-updating).

**Nothing after `Departed from customs`, `Shipment completed the customs clearance process at destination`, `Customs Clearance Completed Successfully` or `Import clearance success`.** The delivering carrier should have the parcel. Look for its number on TopYou's tracker, or ask the seller which carrier delivers and under which number. Background in [tracking a parcel from China](guide:tracking-from-china).

**Not found or no scans yet.** TopYou's tracker answers 查询不到 (not found) for a number it doesn't know, and can answer 暂无轨迹信息 (no tracking information yet) for one it knows but hasn't scanned. Peek shows "Waiting for the carrier" for both and keeps checking. If TopYou has shown only `Parcel Data Received` for a day or more, Peek says "The sender hasn’t handed it to TopYou yet. Tracking starts when they do." Ask the shop when it will send it. Check the number against the seller's message, character by character; if nothing comes after a few days, ask the seller whether the parcel was really sent.

**Delivered, but nothing came.** On a US parcel USPS delivers: ask your post office, then tell the seller. More in [delivered but not received](guide:delivered-not-received).

**Lost or returned.** The seller booked the parcel with TopYou, so the seller, or the marketplace, is the one to ask for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## TopYou contact number and customer service

As of 10 November 2026, TopYou's website, [szty56.com](https://www.szty56.com/), gives a phone number in Shenzhen, +86 755 8181 9898 (0755-81819898 within China). It names no opening hours and no service for recipients: its customers are the shops that ship with it. The seller booked the parcel with TopYou and is the one to ask about your order, and the marketplace if the seller doesn't answer.

## Track a TopYou parcel with Peek

[Peek](/) recognises a TopYou number by its shape: TYZR, a letter, ten digits and YQ, which no other carrier in its catalog uses. With another letter in place of the R, the shape is only a weak match: Peek shows "Checking tracking services…" and asks TopYou's tracker whether it knows the number. Peek asks TopYou's tracker about numbers of these two shapes only. If all you have is the delivering carrier's number, paste that instead: Peek follows more than 3,500 carriers.

Peek reads TopYou's own tracker and shows the status, each scan in the words of TopYou or of the carrier it relays, the destination country and the weight on TopYou's record. A place that is only a country is left off TopYou's own scans, so the airport scan in China doesn't look like it's in your country. When TopYou's record marks the parcel delivered or returned, Peek shows "Delivered" or "Returned to sender", even if the last scan's words don't. TopYou's own scans are on Beijing time. For the delivering carrier's scans it relays, TopYou's tracker doesn't always give the right time zone, so Peek works out their time; it shows them all in your device's time zone. There's no expected delivery date: TopYou gives none.

Until a hand-over, Peek shows the TopYou number only. When TopYou's record holds the delivering carrier's number, Peek tries it with your country's post if it is a valid international postal number (two letters, nine digits with a correct check digit, two letters), or else with the one carrier its layout surely belongs to, as long as Peek reads that carrier's own tracker. Once that carrier shows a scan at least as recent as TopYou's latest, Peek follows the parcel there and shows both numbers, with TopYou's scans kept in the history. A number Peek can't pin to one carrier, such as 20 digits alone, which USPS and others use, isn't tried: Peek stays with TopYou's tracker, which can show the delivering carrier's scans too.

Peek checks every 10 minutes, or hourly once TopYou has gone quiet: after 12 hours without a new scan while the parcel is still "Label created" or "Received by carrier", or after two days at any other step. Once the parcel is out for delivery, it checks every 2 minutes (every 5 when the check has to open a browser): on that day, from 6 a.m. local time, while TopYou's own tracker answers. Through tracking services, it checks every 15 minutes. When another carrier takes over the delivery, Peek follows the pace of the carrier delivering the parcel: hourly for Royal Mail, every 30 minutes for Poste Italiane, PostNL and USPS, out for delivery too. It can notify you of every scan, important steps only or "Delivery only". "Important steps" (delivery, pickup, customs, missed attempts and returns) alerts you to your country's customs (`Shipment started the customs clearance process at destination`), to GOFO's `The driver is out for delivery` and iMile's `Delivery, Our delivery associate is out for delivery.`, and to the delivery, not to the export clearance in China, the flight, the customs release or `Shipment is out for delivery`. Peek can't contact TopYou or the seller for you.

## Questions about TopYou tracking

### What is TopYou (通邮)?

It is 深圳市通邮物流科技（集团）股份有限公司, also called 通邮集团, Tongyou or SZTY56, a cross-border forwarder in Shenzhen, China, with international lines. It says it was founded in 2016, has branches and sorting centres in Shenzhen, Guangzhou, Shanghai and other Chinese cities, and handles more than 800,000 orders a day, sent by post, on its own international lines or with the big express couriers. Its waybills are TYZR, a letter, ten digits and YQ, some with another letter in place of the R.

### Why does TopYou show my country before my parcel has left China?

Because TopYou's tracker writes the destination country next to its own scans, even at the departure airport. Go by the wording: `Arrived at airport of the original country` means the parcel is still waiting for its flight.

### Who delivers TopYou parcels in my country?

In the United States, USPS, which takes the parcel over from TopYou's line after the flight. In France and Italy, it can be GOFO, in Germany iMile and in Austria DPD. Elsewhere TopYou's record can give the delivering carrier's number but not its name: ask the seller which company delivers.

### Who do I contact about a missing TopYou parcel?

The seller you ordered from: it booked the parcel with TopYou. TopYou's website gives a phone number in Shenzhen (above), but no service for recipients.

:::sources
- [TopYou: 通邮集团 (home page)](https://www.szty56.com/) – phone
- [TopYou: 关于我们 (about)](https://www.szty56.com/view?path=about/about) – founded 2016, branches, orders a day, services
- [TopYou: 轨迹查询 (tracking)](https://track.szty56.com/) – TopYou's tracker
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €2 Union handling fee from 1 November 2026
- [European Commission: Guidance on the 3 EUR customs duty and Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per item sold at a distance, whatever its value
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – company name and other names, TYZR number shape, capitals on TopYou's tracker, no link Peek reads, scan wordings and their stages, the destination country beside TopYou's own scans, Beijing time and the relayed scans' clocks, USPS taking over in the US, GOFO in France and Italy, iMile in Germany and DPD in Austria, the delivering carrier's relayed scans and a pickup point's notice, the second number, the Lastmile number on a post's line and the line name, unknown numbers and numbers without scans, the parcel's data before TopYou has it, what Peek shows and how it hands a parcel over
:::
