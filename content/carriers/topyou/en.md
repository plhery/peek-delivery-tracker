---
title: TopYou tracking: TYZR numbers, scans and who delivers
description: Track a TopYou (通邮) parcel from China: what a TYZR…YQ number looks like, what its English scans mean and who takes the parcel over in your country.
slug: topyou-tracking
published: 2026-10-28
updated: 2026-10-28
---

TopYou (通邮, also 通邮集团) is 深圳市通邮物流科技（集团）股份有限公司, a cross-border forwarder in Shenzhen, China, that runs international lines. If a Chinese online shop sent your order with TopYou, the shop gives you a number that starts with TYZR and ends with YQ: paste it in the box above. TopYou's history runs from its warehouse in China through the flight and customs, and for parcels to the United States on to USPS's delivery.

## TopYou tracking number: what it looks like

TopYou's waybills are TYZR, one letter, ten digits and YQ, 17 characters in all. With invented digits:

| Number | What it is |
| --- | --- |
| `TYZRA0000000017YQ` | TYZR, a letter, ten digits and YQ: a TopYou waybill. |
| `TYZRA000000017YQ` | Only nine digits: not TopYou's layout. Check for a missing digit. |
| `JXCAB0000000017YQ` | Also ends in YQ, as other Chinese forwarders' waybills do, but opens with JXC: a [Juxi Express](carrier:juxi-express) number. |

Spaces and lower case don't matter when you paste the number into Peek. TopYou's own tracker tells capitals from lower case, so type the number there in capitals. If you can't find it in your order's details, see [how to find your tracking number](guide:find-tracking-number).

**A second number.** TopYou's record can also hold the delivering carrier's own number, which TopYou's tracker shows. The tracker finds the parcel by either number.

**No link to share.** TopYou's tracker takes the number in a form only, so no link opens one parcel: copy the number instead.

## TopYou tracking statuses explained

TopYou writes its scans in English. In the order a parcel gets them:

| TopYou says | What it means for you | Peek shows |
| --- | --- | --- |
| `Arrived at the first mile sorting center` | TopYou's warehouse in China has the parcel: the first scan in TopYou's hands. | "Received by carrier" |
| `Departed from the first mile sorting center` | It has left that warehouse. | "In transit" |
| `Arrived at airport of the original country` | At the airport it flies from, before the flight. | "In transit" |
| `Departed from Airport to destination country` | The flight to your country has left. | "In transit" |
| `Arrived at destination airport` | Landed in your country. | "In transit" |
| `Awaiting the import declaration` | Waiting for your country's import customs. | "At customs" |
| `Departed from customs of` | Your country's customs have released it. TopYou leaves the country after "of" blank, and Peek drops the "of". | "In transit" |
| `At U.S. Postal Service facility, Accepted by U.S. Postal Service` | In the US: USPS has taken the parcel over from TopYou after the flight. It isn't a new shipment. | "In transit" |

**USPS's scans.** For parcels to the United States, TopYou's tracker goes on with USPS's scans in USPS's words, each with its place, such as `Out for delivery, Out for delivery with the U.S. Postal Service` ("Out for delivery" in Peek) and `Delivered, Package delivered by U.S. Postal Service to addressee` ("Delivered").

## Who delivers TopYou parcels

**United States.** USPS takes the parcel over from TopYou's line after the flight, and TopYou's tracker relays its scans up to the delivery. The second number on TopYou's record is then the one [USPS](carrier:usps) tracks it by.

**Elsewhere.** TopYou's record can give the delivering carrier's number, but not its name, and the name of the TopYou line your parcel travels on doesn't tell you either: a line can bear another carrier's name. So ask the seller which company delivers your order.

## TopYou parcel not arriving or tracking stuck

**Stuck on `Awaiting the import declaration`.** The parcel is waiting for your country's customs. If you're asked for import charges, see [parcels held at customs](guide:customs); if it stays there for days, tell the seller. More in [why tracking stops updating](guide:tracking-not-updating).

**Nothing after `Departed from customs of`.** The delivering carrier should have the parcel. Look for its number on TopYou's tracker, or ask the seller which carrier delivers and under which number. Background in [tracking a parcel from China](guide:tracking-from-china).

**Not found or no scans yet.** TopYou's tracker answers 查询不到 (not found) for a number it doesn't know, and can answer 暂无轨迹信息 (no tracking information yet) for one it knows but hasn't scanned. Peek shows "Waiting for the carrier" for both and keeps checking. Check the number against the seller's message, character by character; if nothing comes after a few days, ask the seller whether the parcel was really sent.

**Delivered, but nothing came.** On a US parcel the delivered scan is USPS's: ask your post office, then tell the seller. More in [delivered but not received](guide:delivered-not-received).

**Lost or returned.** The seller booked the parcel with TopYou, so the seller, or the marketplace, is the one to ask for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## TopYou contact number and customer service

As of 28 October 2026, Peek couldn't check TopYou's website, so it lists no contact details for TopYou here. The seller booked the parcel with TopYou and is the one to ask about your order, and the marketplace if the seller doesn't answer.

## Track a TopYou parcel with Peek

[Peek](/) recognises a TopYou number by its shape: TYZR, a letter, ten digits and YQ, which no other carrier in its catalog uses, and asks TopYou's tracker by that number only. If all you have is the delivering carrier's number, paste that instead: Peek follows more than 3,500 carriers.

Peek reads TopYou's own tracker and shows the status, each scan in the words of TopYou or of the carrier it relays, the destination country and the weight on TopYou's record. A place that is only a country is left off TopYou's own scans, so the airport scan in China doesn't look like it's in your country. When TopYou's record marks the parcel delivered or returned, Peek shows "Delivered" or "Returned to sender", even if the last scan's words don't. TopYou's own scans are on Beijing time and the USPS scans it relays on the local clock of their place; Peek shows them all in your device's time zone. There's no expected delivery date: TopYou gives none.

Until a hand-over, Peek shows the TopYou number only. When TopYou's record holds the delivering carrier's number, Peek tries it with your country's post if it is an international postal number (two letters, nine digits, two letters), or else with the one carrier its layout surely belongs to, as long as Peek reads that carrier's own tracker. Once that carrier shows a scan at least as recent as TopYou's latest, Peek follows the parcel there and shows both numbers, with TopYou's scans kept in the history. A number Peek can't pin to one carrier, such as 20 digits alone, which USPS and others use, isn't tried: Peek stays with TopYou's tracker, which shows USPS's scans on a US parcel anyway.

Peek checks every 10 minutes by day, every 2 minutes once the parcel is out for delivery, and every hour or two overnight, and can notify you of every scan, important steps only or "Delivery day only". "Important only" (delivery, pickup, customs, missed attempts and returns) alerts you to `Awaiting the import declaration` and the delivery, not to the flight or the customs release. Peek can't contact TopYou or the seller for you.

## Questions about TopYou tracking

### What is TopYou (通邮)?

It is 深圳市通邮物流科技（集团）股份有限公司, also called 通邮集团, a cross-border forwarder in Shenzhen, China, with international lines. Its waybills are TYZR, a letter, ten digits and YQ.

### Why does TopYou show my country before my parcel has left China?

Because TopYou's tracker writes the destination country next to its own scans, even at the departure airport. Go by the wording: `Arrived at airport of the original country` means the parcel is still waiting for its flight.

### Who delivers TopYou parcels in my country?

In the United States, USPS, whose scans TopYou's tracker shows. Elsewhere TopYou's record can give the delivering carrier's number but not its name: ask the seller which company delivers.

### Who do I contact about a missing TopYou parcel?

The seller you ordered from: it booked the parcel with TopYou. Peek couldn't check TopYou's website for contact details.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – company name, TYZR number shape, capitals on TopYou's tracker, search by either number, no tracking link, scan wordings and their stages, the destination country beside TopYou's own scans, Beijing and local clocks, USPS taking over in the US and its relayed scans, the second number and the line name, unknown numbers and numbers without scans, what Peek shows and how it hands a parcel over
:::
