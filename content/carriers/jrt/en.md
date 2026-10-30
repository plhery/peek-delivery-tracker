---
title: JRT tracking: JRT…MM numbers, flight scans and DHL
description: Track a JRT (今日通, Jinritong) parcel from Xiamen, China: what a JRT…MM number looks like, what its Chinese and English scans mean and who delivers it.
slug: jrt-tracking
published: 2026-10-30
updated: 2026-10-30
---

JRT (今日通, Jinritong) is 厦门今日通商贸有限公司, a forwarder in Xiamen, China. If a Chinese shop sent your order with JRT, it gives you a number that starts with JRT and ends with MM: paste it in the box above. Its tracker follows the parcel from China through the flight and customs to the company that delivers: into Europe, DHL, whose scans it copies.

## JRT tracking number: what it looks like

JRT's own waybills are JRT, ten digits and MM, 15 characters in all; JRT is 今日通's initials. With invented digits:

| Number | What it is |
| --- | --- |
| `JRT0000000000MM` | JRT, ten digits and MM: a JRT waybill. |
| `JRT000000000MM` | Only nine digits: not JRT's layout. Check for a missing digit. |
| `UP0000000000000000` | UP and sixteen digits: a shipper's reference that can find a JRT order. Nothing shows that only JRT's shippers use it. |
| `RR000000005CN` | Two letters, nine digits and CN: a [China Post](carrier:china-post) number, not JRT's. |

Spaces and lower case don't matter in Peek. Can't find the number? See [how to find your tracking number](guide:find-tracking-number). JRT's tracker also finds an order by the seller's own reference, typed exactly as the seller wrote it, dashes included.

**A link with numbers instead of a name.** JRT's tracker runs on a bare server address over plain HTTP, not on a web domain, and Peek knows no other JRT website. A link to it has that numeric address where a site's name would be, and holds `trackIndex.htm`, `documentCode=` and a number or reference: you can paste it into Peek as it is. Peek itself doesn't link to the tracker: a plain-HTTP link would carry your number in the clear.

## JRT tracking statuses explained

Many of JRT's lines are Chinese followed by JRT's own English, and Peek quotes them whole. In the order a parcel gets them:

| JRT says | What it means for you | Peek shows |
| --- | --- | --- |
| `货物电子信息已经收到` | "The electronic cargo information has been received": the seller has created the order. Written by Hualei (华磊), the platform behind JRT's tracker, not a scan. | "Label created" |
| `Shipment information received` | The shipment's data has reached JRT; nobody has scanned the parcel yet. | "Label created" |
| `到达收货点` | Arrived at JRT's receiving point: the first scan in JRT's hands. | "Received by carrier" |
| `货物离开操作中心` | The parcel has left JRT's operations centre. | "In transit" |
| `货物已交机场,等待航班安排中 Cargo delivered to airport, pending flight arrangement` | Handed over at the airport, waiting for a flight. "Delivered" means to the airport, not to you. | "In transit" |
| `航班起飞，以实际抵达为准 Flight departure subject to actual arrival time` | The flight has left. | "In transit" |
| `航班落地，拆板中 Flight landing, dismantling board in progress` | Landed. The pallets are broken down before customs. | "In transit" |
| `机场内等待提货，等待清关 Waiting for cargo pickup and customs clearance at the airport.` | At the destination airport, waiting for pickup and import customs. | "At customs" |
| `已提货，清关中 The goods have been picked up and are currently undergoing customs clearance` | Import clearance is under way. | "At customs" |
| `清关完成，卡车发运 Customs clearance completed; truck dispatched.` | Through customs and on a truck. | "In transit" |
| `已交服务商 Delivered to service provider` | Handed to the company that delivers the last leg. Not delivered to you. | "In transit" |

Lines JRT writes in Chinese only show in Peek in the English other forwarders give them: `货物电子信息已经收到` as "The electronic cargo information has been received", `到达收货点` as "Arrived at the receiving point", `货物离开操作中心` as "Goods leave the operation center" and `快件操作完成` as "Shipment departed from facility". The airport line that books a flight, `货物已交机场,预计…号的航班 Goods delivered to airport, scheduled on flight of the …`, names the flight's day in both halves; Peek shows it as "Goods delivered to airport", as the flight is still to come.

**Lines in English only.** Other JRT lines come in English alone, all "In transit" in Peek unless noted:

- `Shipment arrived at facility`, `Sending to the airport`, `The goods have been packed and sent to the airline`: on the way to the flight.
- `Arriving at the airport - arranging flights`, `Arriving at the airline, in the process of booking a flight`: booking a flight.
- `Flight is expected to take off on …` or `The flight is expected to depart on … and land on …`, with dates where the dots are: the flight is booked.
- `Flight has flown`, `The flight has departed and is expected to … land`: in the air.
- `Flight has arrived`, `The flight has arrived and is awaiting dismantling`, `The NOA has been received` (the airline's notice of arrival), `Dismantling and picking up goods in progress`: landed, before customs.
- `Customs clearance of the cargo is in progress` ("At customs"), `Customs clearance completed`, then `The goods have been handed over to the carrier`, to the company that delivers.

In `The flight is expected to depart on … and land on …` and `The flight has departed and is expected to … land`, dates are month.day: 11.12 is 12 November.

**DHL's and FedEx's lines.** Into Europe, the tracker goes on with DHL's scans, mostly in lower case: `the instruction data for this shipment have been provided by the sender to dhl electronically` (DHL has the label's data: "Label created"), `The shipment has been posted by the sender at the retail outlet` (JRT has handed the parcel in at a Deutsche Post outlet), `the shipment has been processed in the parcel center of origin` and `loaded to movement / tour vehicle` ("In transit"). Later DHL lines are copied too: `not delivered-consignee could not be reached` (nobody was in: "Delivery attempted"), `held for consignee collection` (left at a pickup point: "Ready for pickup"), `shipment will be returned to origin-not ordered` (refused as not ordered: "Returned to sender") and `the shipment has been successfully delivered` ("Delivered", or "Returned to sender" when it follows a return line, since the parcel has then reached the sender). Some parcels open with `Shipment information sent to FedEx` instead: the label's data has gone to FedEx, which delivers the last leg ("Label created").

Every time on JRT's tracker is Beijing time, DHL's copied scans included: 17:00 there is 09:00 in London and 10:00 in Berlin in winter.

## Who delivers JRT parcels

**Into Europe**, JRT's lines end with DHL: JRT hands the parcel in at a Deutsche Post outlet, and its tracker copies DHL's scans. JRT's record holds neither DHL's number nor the line's name, so Peek can't follow the parcel on DHL's own tracker. Ask the seller for DHL's number: DHL's tracking follows the parcel to its delivery (see [DHL Paket](carrier:dhl) for its numbers and statuses).

**With FedEx**, the first line names FedEx, but Peek doesn't follow the parcel on to FedEx's tracking. Ask the seller for FedEx's number and follow it on [FedEx](carrier:fedex).

**Elsewhere**, Peek doesn't know who delivers JRT's parcels. Ask the seller which company delivers your order, and under which number: delivery attempts, pickup points and holding times are that company's rules. Asked for import charges? See [parcels held at customs](guide:customs).

## JRT parcel not arriving or tracking stuck

**Only the first lines.** `货物电子信息已经收到`, `Shipment information received` or a DHL or FedEx label line alone mean the seller has booked the parcel but nobody has scanned it. If nothing follows after a few days, ask the seller whether it was really sent.

**Waiting for a flight.** A flight line gives the day the flight is booked for. If that day passes with no departure line, tell the seller.

**Nothing after `已交服务商`.** JRT's part is done. Unless its tracker copies DHL's scans, the next steps are in the delivering company's tracking: ask the seller for that number. More in [why tracking stops updating](guide:tracking-not-updating) and [tracking a parcel from China](guide:tracking-from-china).

**Nothing found.** For a number it doesn't know, JRT's tracker shows one result with the number as the reference and every other column empty. Peek shows "Waiting for the carrier" and keeps checking. Compare the number with the seller's message, character by character.

**Delivered, but nothing came.** The delivery scan is the delivering company's, not JRT's: ask that company, then tell the seller. More in [delivered but not received](guide:delivered-not-received).

**Lost or returned.** The seller booked the parcel with JRT, so ask the seller, or the marketplace, for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## JRT contact number and customer service

As of 29 October 2026, Peek found no contact details for JRT: it knows no website of the company's besides its tracker, and no phone number, email or address for recipients. The seller booked the parcel with JRT and is the one to ask about your order, and the marketplace if the seller doesn't answer.

## Track a JRT parcel with Peek

[Peek](/) recognises a JRT number by its shape: JRT, ten digits and MM, which no other carrier in its catalog uses, and a link to JRT's tracker (above). For a UP reference with sixteen digits, Peek asks JRT's tracker whether it knows it, and picks JRT when it does. Any other reference has no shape to go by: paste it all the same, as Peek follows more than 3,500 carriers, then press "Change" and pick JRT (typing 今日通 or Jinritong finds it too), or use "Change carrier" on a parcel you already added. Peek then shows the JRT number under "JRT tracks this as". Peek drops dashes, though, so a reference with dashes isn't found through Peek: ask the seller for the JRT…MM number.

Peek shows the status, each line with its place, and the service name and destination country when JRT's record gives them, going by the lines rather than the record's summary. Times are read on Beijing time and shown in your device's time zone. Peek holds back a line written more than twelve hours before the time it carries, and any line dated after its check. It leaves out the names, address, postcodes, weight, fees, signature and proof of delivery, and shows no expected delivery date: JRT gives none.

Peek follows parcels into Europe on JRT's tracker only, with the DHL scans it copies. The one hand-over Peek takes from JRT's record is to [Swiss Post](carrier:swiss-post), when the record names a Swiss Post line and number for a parcel to Switzerland or Liechtenstein. With any other delivering number from the seller, add it as a parcel of its own.

Peek checks every 10 minutes by day and every hour or two overnight, and hourly once JRT goes quiet (12 hours at its first lines, or two days with nothing new). It can notify you of every scan, important steps only or "Delivery only". "Important steps" (delivery, pickup, customs, missed attempts and returns) alerts you to JRT's "At customs" lines and to DHL's copied missed-attempt, pickup-point, delivery and return lines, not to JRT's flights or `已交服务商`. Peek can't contact JRT or the seller for you.

## Questions about JRT tracking

### What is JRT (今日通)?

It is 厦门今日通商贸有限公司, Xiamen Jinritong, a forwarder in Xiamen, China. Its waybills are JRT, ten digits and MM, and its tracker runs on Hualei (华磊), a platform other Chinese forwarders use too.

### Why does JRT say "delivered" when my parcel hasn't arrived?

Because of its translations. `Cargo delivered to airport` means JRT handed the parcel over for its flight, and `Delivered to service provider` means it handed it to the delivering company. Neither is a delivery to you.

### Who delivers JRT parcels in my country?

Into Europe, DHL, whose scans JRT's tracker copies. Some parcels go to FedEx. Elsewhere Peek doesn't know: ask the seller which company delivers and for its number.

### Who do I contact about a missing JRT parcel?

The seller you ordered from: it booked the parcel with JRT. Peek knows no website or contact details for JRT besides its tracker.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – company name and initials, Hualei platform, the tracker on a bare address over plain HTTP, its links and no link published, JRT…MM and UP number shapes, search by number or reference with its dashes, line wordings and their stages, the English for Chinese-only lines, DHL's copied lines and the Deutsche Post outlet, FedEx's line, no DHL number in the record, Swiss Post hand-over, Beijing clock, the answer for an unknown number, what Peek shows, holds back and leaves out
:::
