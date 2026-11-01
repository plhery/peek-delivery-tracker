---
title: JRT tracking: JRT…MM numbers, flight scans and DHL
description: Track a JRT (今日通, Jinritong) parcel from Xiamen, China: what a JRT…MM number looks like, what its Chinese and English scans mean and who delivers it.
slug: jrt-tracking
published: 2026-10-30
updated: 2026-10-30
---

JRT (今日通, Jinritong) is 厦门今日通商贸有限公司, a forwarder in Xiamen, China. If a Chinese shop sent your order with JRT, it gives you a number that starts with JRT and ends with MM: paste it in the box above. Its tracker follows the parcel from China through the flight and customs to the company that delivers: into Europe, DHL, and into the United States, UPS, whose scans it copies. JRT's FedEx line to Ireland ends with FedEx instead.

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
| `货物打包完成，已发往航司` | Packed and on its way to the airline, before the flight. Peek shows it in English: "Goods packed and sent to the airline". | "In transit" |
| `抵达航司已起飞` | With the airline, and the flight has left the airport the line names. Peek shows "Arrived at the airline, flight departed". | "In transit" |
| `货物已交机场,等待航班安排中 Cargo delivered to airport, pending flight arrangement` | Handed over at the airport, waiting for a flight. "Delivered" means to the airport, not to you. | "In transit" |
| `航班起飞，以实际抵达为准 Flight departure subject to actual arrival time` | The flight has left. | "In transit" |
| `航班落地，拆板中 Flight landing, dismantling board in progress` | Landed. The pallets are broken down before customs. | "In transit" |
| `机场内等待提货，等待清关 Waiting for cargo pickup and customs clearance at the airport.` | At the destination airport, waiting for pickup and import customs. | "At customs" |
| `已提货，清关中 The goods have been picked up and are currently undergoing customs clearance` | Import clearance is under way. | "At customs" |
| `清关完成，卡车发运 Customs clearance completed; truck dispatched.` | Through customs and on a truck. | "In transit" |
| `已交服务商 Delivered to service provider` | Handed to the company that delivers the last leg. Not delivered to you. | "In transit" |

Some lines JRT writes in Chinese only show in Peek in the English other forwarders give them: `货物电子信息已经收到` as "The electronic cargo information has been received", `到达收货点` as "Arrived at the receiving point", `货物离开操作中心` as "Goods leave the operation center" and `快件操作完成` as "Shipment departed from facility". The airport line that books a flight, `货物已交机场,预计…号的航班 Goods delivered to airport, scheduled on flight of the …`, names the flight's day in both halves; Peek shows it as "Goods delivered to airport", as the flight is still to come.

On the line into Europe, three later lines also come in Chinese only, and Peek shows them in English, all "In transit": `航班已抵达` as "Flight has arrived" (the landing at the European airport it names), `清关完成，中转德国中` as "Customs cleared, in transit to Germany" (cleared at the airport of arrival, on its way to the hand-over in Germany) and `抵达德国，已交服务商` as "Arrived in Germany, handed to the delivering carrier" (handed to the company that delivers, such as DHL; not a delivery to you).

**Lines in English only.** Other JRT lines come in English alone, all "In transit" in Peek unless noted:

- `Shipment arrived at facility`, `Sending to the airport`, `The goods have been packed and sent to the airline`: on the way to the flight.
- `Arriving at the airport - arranging flights`, `Arriving at the airline, in the process of booking a flight`: booking a flight.
- `Flight is expected to take off on …` or `The flight is expected to depart on … and land on …`, with dates where the dots are: the flight is booked.
- `Flight has flown`, `The flight has departed and is expected to … land`: in the air.
- `Flight has arrived`, `The flight has arrived and is awaiting dismantling`, `The NOA has been received` (the airline's notice of arrival), `Dismantling and picking up goods in progress`: landed, before customs.
- `Customs clearance of the cargo is in progress` ("At customs"), `Customs clearance completed`, then `The goods have been handed over to the carrier`, to the company that delivers.

In `The flight is expected to depart on … and land on …` and `The flight has departed and is expected to … land`, dates are month.day: 11.12 is 12 November.

**DHL's and FedEx's lines.** Into Europe, the tracker goes on with DHL's scans, mostly in lower case: `the instruction data for this shipment have been provided by the sender to dhl electronically` (DHL has the label's data: "Label created"), `The shipment has been posted by the sender at the retail outlet` (JRT has handed the parcel in at a Deutsche Post outlet), `the shipment has been processed in the parcel center of origin` and `loaded to movement / tour vehicle` ("In transit"). Later DHL lines are copied too: `not delivered-consignee could not be reached` (nobody was in: "Delivery attempted"), `held for consignee collection` (left at a pickup point: "Ready for pickup"), `shipment will be returned to origin-not ordered` (refused as not ordered: "Returned to sender") and `the shipment has been successfully delivered` ("Delivered", or "Returned to sender" when it follows a return line, since the parcel has then reached the sender). Some parcels open with `Shipment information sent to FedEx` instead: the label's data has gone to FedEx, which delivers the last leg ("Label created").

**JRT's and UPS's lines into the United States.** On its line into the United States, JRT writes in Chinese only, and Peek shows its lines in English, all "In transit": `…号航班`, with the flight's month and day where the dots are (`10/22号航班` is 22 October), as "Booked on a flight"; `航班已飞` as "Flight has flown"; `航班抵达` as "Flight has arrived"; `提货中` as "Picking up the goods" (at the airport, after the landing and before customs); `清关完成，已提货，待交仓` as "Customs clearance completed, goods picked up, awaiting hand-over to the warehouse"; and `已交仓` as "Handed over to the warehouse", the delivering company's, before UPS's first facility scan. The tracker then copies UPS's scans in Chinese, which Peek shows in UPS's own English: `发件人已创建标签，但是 UPS 尚未收到包裹。` ("Shipper created a label, UPS has not received the package yet.": "Label created"); `抵达设施` ("Arrived at Facility"), `离开设施` ("Departed from Facility"), `UPS 设施处理` ("Processing at UPS Facility") and `已提取` ("Picked Up", between UPS's facility scans, not a pickup by you), all "In transit"; `已装上递送车辆` ("Loaded on Delivery Vehicle") and `准备今天递送` ("Out For Delivery Today"), "Out for delivery"; and `已递送` ("Delivered").

On the line into Europe, JRT's own lines are on Beijing time: 17:00 there is 09:00 in London and 10:00 in Berlin in winter. DHL's copied lines that begin `The shipment …`, `The international shipment …` or `the instruction data …` and give no place are on DHL's local time instead. Peek reads the label data and the lines about the parcel centre of origin, the export parcel centre and the transport to the destination country on German time, and DHL's other lines of this kind on the destination country's time (German time if JRT's record names no destination). DHL's other copied lines, such as `loaded to movement / tour vehicle`, it reads on Beijing time, as it does JRT's. Peek reads UPS's copied scans, and any line placed in the United States, on the local time of their place.

## Who delivers JRT parcels

**Into Europe**, JRT's lines end with DHL, all but its FedEx line to Ireland (below): JRT hands the parcel in at a Deutsche Post outlet, and its tracker copies DHL's scans. Once JRT has handed the parcel in, its record holds DHL's number in place of JRT's, though still not the line's name. When Peek can tell that number is DHL's, it shows "Delivery with DHL" with the number, and moves to DHL's own tracking once DHL has scanned the parcel (more below). If not, ask the seller for DHL's number: DHL's tracking follows the parcel to its delivery (see [DHL Paket](carrier:dhl) for its numbers and statuses).

**Into the United States**, JRT's lines end with UPS: JRT hands the parcel over at the delivering company's warehouse (`已交仓`), and its tracker copies UPS's scans up to the delivery.

**On its FedEx line to Ireland**, whose product name includes FedEx, JRT hands the parcel over to FedEx, and its record then holds FedEx's number in place of JRT's. When that number has one of FedEx's shapes, Peek shows "Delivery with FedEx" with the number, and moves to FedEx's own tracking once FedEx has scanned the parcel, as with DHL (more below). Whenever FedEx delivers and Peek shows no FedEx number, ask the seller for FedEx's number and follow it on [FedEx](carrier:fedex).

**Elsewhere**, Peek doesn't know who delivers JRT's parcels. Ask the seller which company delivers your order, and under which number: delivery attempts, pickup points and holding times are that company's rules. Asked for import charges? See [parcels held at customs](guide:customs).

## JRT parcel not arriving or tracking stuck

**Only the first lines.** `货物电子信息已经收到`, `Shipment information received` or a DHL or FedEx label line alone mean the seller has booked the parcel but nobody has scanned it. If nothing follows after a few days, ask the seller whether it was really sent.

**Waiting for a flight.** A flight line gives the day the flight is booked for. If that day passes with no departure line, tell the seller.

**Nothing after `已交服务商` or `已交仓`.** JRT's part is done. Unless its tracker copies DHL's or UPS's scans, or Peek shows DHL's or FedEx's number, the next steps are in the delivering company's tracking: ask the seller for that number. More in [why tracking stops updating](guide:tracking-not-updating) and [tracking a parcel from China](guide:tracking-from-china).

**Nothing found.** For a number it doesn't know, JRT's tracker shows one result with the number as the reference and every other column empty. Peek shows "Waiting for the carrier" and keeps checking (for a UP reference you left Peek to match to a carrier, "Waiting for JRT’s first scan"). Compare the number with the seller's message, character by character.

**Delivered, but nothing came.** The delivery scan is the delivering company's, not JRT's: ask that company, then tell the seller. More in [delivered but not received](guide:delivered-not-received).

**Lost or returned.** The seller booked the parcel with JRT, so ask the seller, or the marketplace, for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## JRT contact number and customer service

As of 29 October 2026, Peek found no contact details for JRT: it knows no website of the company's besides its tracker, and no phone number, email or address for recipients. The seller booked the parcel with JRT and is the one to ask about your order, and the marketplace if the seller doesn't answer.

## Track a JRT parcel with Peek

[Peek](/) recognises a JRT number by its shape: JRT, ten digits and MM, which no other carrier in its catalog uses, and a link to JRT's tracker (above). For a UP reference with sixteen digits, Peek asks JRT's tracker whether it knows it, and picks JRT when it does. Any other reference has no shape to go by: paste it all the same, as Peek follows more than 3,500 carriers, then press "Change" and pick JRT (typing 今日通 or Jinritong finds it too), or use "Change carrier" on a parcel you already added. When you track a reference, Peek then shows the JRT number under "JRT tracks this as", until JRT hands the parcel over and its record holds DHL's or FedEx's number instead. Peek drops dashes, though, so a reference with dashes isn't found through Peek: ask the seller for the JRT…MM number.

Peek shows the status, each line with its place, and the destination country when JRT's record gives it, going by the lines rather than the record's summary. Times are read on Beijing time, the DHL lines described above on DHL's local time, UPS's copied scans and lines placed in the United States on the local time of their place, and shown in your device's time zone. Peek holds back a line written more than twelve hours before the time it carries, and any line dated after its check. It leaves out the names, address, postcodes, weight, fees, signature and proof of delivery, and shows no expected delivery date: JRT gives none.

Peek follows parcels into the United States on JRT's tracker only, with the UPS scans it copies. Into Europe, once JRT's record holds a number Peek can tell is DHL's, Peek asks DHL too, and moves to DHL's tracking once DHL shows a scan at least as recent as JRT's latest, keeping JRT's scans in the history; until then it stays on JRT's tracker, with the DHL scans it copies. On the FedEx line to Ireland, Peek does the same with FedEx once JRT's record holds a number with one of FedEx's shapes. The only other hand-over Peek takes from JRT's record is to [Swiss Post](carrier:swiss-post), when the record names a Swiss Post line and number for a parcel to Switzerland or Liechtenstein. With any other delivering number from the seller, add it as a parcel of its own.

Peek checks every 10 minutes, and hourly once JRT goes quiet (12 hours at its first lines, or two days with nothing new). It can notify you of every scan, important steps only or "Delivery only". "Important steps" (delivery, pickup, customs, missed attempts and returns) alerts you to JRT's "At customs" lines, to DHL's copied missed-attempt, pickup-point, delivery and return lines and to UPS's copied out-for-delivery and delivery lines, not to JRT's flights, `已交服务商` or `已交仓`. Peek can't contact JRT or the seller for you.

## Questions about JRT tracking

### What is JRT (今日通)?

It is 厦门今日通商贸有限公司, Xiamen Jinritong, a forwarder in Xiamen, China. Its waybills are JRT, ten digits and MM, and its tracker runs on Hualei (华磊), a platform other Chinese forwarders use too.

### Why does JRT say "delivered" when my parcel hasn't arrived?

Because of its translations. `Cargo delivered to airport` means JRT handed the parcel over for its flight, and `Delivered to service provider` means it handed it to the delivering company. Neither is a delivery to you.

### Who delivers JRT parcels in my country?

Into Europe, DHL, and into the United States, UPS: JRT's tracker copies their scans. Some parcels go to FedEx, such as those on JRT's FedEx line to Ireland. Elsewhere Peek doesn't know: ask the seller which company delivers and for its number.

### Who do I contact about a missing JRT parcel?

The seller you ordered from: it booked the parcel with JRT. Peek knows no website or contact details for JRT besides its tracker.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – company name and initials, Hualei platform, the tracker on a bare address over plain HTTP, its links and no link published, JRT…MM and UP number shapes, search by number or reference with its dashes, line wordings and their stages, the English for Chinese-only lines, the Chinese-only lines into Europe from the landing to the hand-over in Germany, DHL's copied lines and the Deutsche Post outlet, the US line and UPS's copied lines on their local clock, FedEx's line, DHL's or FedEx's number in the record after the hand-over, the hand-over to DHL and, on the FedEx line to Ireland, to FedEx, Swiss Post hand-over, Beijing clock and DHL's local clock for its copied sentences, the answer for an unknown number, what Peek shows, holds back and leaves out
:::
