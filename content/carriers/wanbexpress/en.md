---
title: WanbExpress tracking: WNB…YQ numbers, scans and who delivers
description: Track a WanbExpress (Wanb Express) parcel from China: what a WNB…YQ number looks like, what its scans mean and who to ask when it stalls.
slug: wanbexpress-tracking
published: 2026-10-30
updated: 2026-10-30
---

WanbExpress (also Wanb Express, WanBang Express or WANB) is a Chinese carrier that sends parcels abroad, where a local carrier delivers them. If a shop sent your order with WanbExpress, its number starts with WNB and ends with YQ: paste it in the box above. WanbExpress's own tracker, tracking.wanbexpress.com, can also name the carrier that delivers in your country, with that carrier's number for the parcel.

## WanbExpress tracking number: what it looks like

WanbExpress's waybills are WNB, two letters, ten digits and YQ, 17 characters in all. With invented digits:

| Number | What it is |
| --- | --- |
| `WNBAA0000000000YQ` | WNB, two letters, ten digits and YQ: a WanbExpress waybill. |
| `WNBAA000000000YQ` | Only nine digits: not WanbExpress's layout. Check for a missing digit. |
| `JSEAA0000000000YQ` | Also ends in YQ, but opens with JSE: a [JS Express](carrier:js-express) number. |
| `JXCAA0000000000YQ` | JXC in front: a [Juxi Express](carrier:juxi-express) number. |

Several Chinese forwarders end their waybills in YQ, each with its own letters in front: WNB is what marks WanbExpress. Spaces, dashes and lower case don't matter when you paste the number into Peek.

**Where to find it.** The shop gives the number in its shipping email or on your order page. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

**A tracking link.** A link to WanbExpress's tracker that ends in `?trackingNumbers=` and a number opens that one parcel. The WNB number is the part after the equals sign: copy it from there into Peek.

**A second number.** WanbExpress's tracker can also show the number the delivering carrier uses for the parcel. That number belongs to the other carrier: look it up on that carrier's tracking, or paste it into Peek as a parcel of its own.

## WanbExpress tracking statuses explained

WanbExpress's scans are in English, each with a time and sometimes a place. Here they are in the order Peek's code lists them:

| WanbExpress says | What it means for you | Peek shows |
| --- | --- | --- |
| `Shipping Order Created.`, `Parcel Data Received` | The shop has booked the parcel and sent its details. WanbExpress doesn't have the parcel yet. | "Label created" |
| `Parcel received at Shenzhen Sorting Centre.` | The sorting centre in Shenzhen, China, has the parcel: the first scan of the parcel itself. | "Received by carrier" |
| `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Sorted, then on its way out of that centre. | "In transit" |
| `Customs declaration completed` | A customs declaration for the parcel is done. The wording doesn't say which country's customs, and it isn't the clearance: that's `Custom clearance completed`. | "In transit" |
| `Parcel departure in Transit Facility, Parcel Departure` | It has left a transit facility. | "In transit" |
| `Parcel is leaving Airport, Parcel Leaving Port` | Leaving on the flight to the destination country. | "In transit" |
| `Parcel arrives at Airport, Parcel Arrival` | Landed in your country, or near it. Customs come next. | "In transit" |
| `Custom clearance completed` | Import customs have let it through. | "In transit" |
| `Parcel received by in JFK01.` | WanbExpress files it as a receipt for the last leg. The wording leaves out who received it. | "In transit" |
| `In transit along Poste Italiane’s logistic network` | On its way to you through the delivering carrier's network, here [Poste Italiane](carrier:poste-italiane)'s. | "In transit" |
| `Parcel is out for delivery.` | On the delivery run. | "Out for delivery" |
| `Parcel has been delivered.(Front door)` | Delivered. `(Front door)` says where it was left. | "Delivered" |

**The delivering carrier's scans.** After the flight, WanbExpress's tracker passes on the delivering carrier's own scans in that carrier's words, mostly without a WanbExpress code. On a parcel to Germany, WanbExpress's history shows [iMile](carrier:imile) delivering, each iMile line after the name of its centre or station in 【brackets】:

| iMile says | What it means for you | Peek shows |
| --- | --- | --- |
| `Received.` | iMile's centre in the destination country has the parcel. | "In transit" |
| `Arrived.` | At the delivery station, the depot that delivers your area. | "In transit" |
| `Dispatch task assigned to DA.` | A driver (DA, delivery associate) is named for the run, which can be hours before it starts. | "In transit" |
| `Shipment out for delivery.` | On the delivery run. | "Out for delivery" |
| `<Route not completed>`, `Back To DS.` | The run ended without the delivery, and the parcel went back to the delivery station (DS). | "Delivery attempted" |

**Times.** Each scan's time comes with its offset from UTC. Peek reads them that way and shows them all in your device's time zone, newest first. For the gaps and handovers that parcels from China go through, see [tracking a parcel from China](guide:tracking-from-china).

## Who delivers WanbExpress parcels

WanbExpress's scans follow the parcel out of China, through customs and on to a local carrier, which delivers it. WanbExpress's tracker can name that carrier, give its number for the parcel and show a delivery photo. Peek keeps none of these three, so for them look at WanbExpress's tracker, or ask the seller.

**What Peek's code shows.** On a parcel to Germany, WanbExpress's history shows iMile delivering, and one WanbExpress scan names Poste Italiane's network. For the United States, the UK, Ireland, Canada and Australia, Peek's code names no delivering carrier.

**Delivery attempts and pickup.** Whoever delivers, its own rules apply: how many attempts it makes, whether it leaves a card, where a missed parcel waits and for how long. Peek found no WanbExpress rules for any of these. Once you know the carrier and its number, check its own tracking and ask it how to rearrange a delivery. See [missed delivery](guide:missed-delivery).

## WanbExpress parcel not arriving or tracking stuck

**Number not found.** If WanbExpress's tracker doesn't know the number, Peek shows "Waiting for the carrier" and keeps checking. Check the number against the seller's message, character by character: WNB, two letters, ten digits, YQ. If nothing comes after a few days, ask the seller whether the parcel was really sent.

**Stuck on `Shipping Order Created.` or `Parcel Data Received`.** The shop has booked the shipment, but WanbExpress hasn't scanned the parcel yet. Ask the shop when it handed the parcel over.

**Stuck after `Parcel arrives at Airport, Parcel Arrival`.** The parcel is most likely waiting for import customs. If you're asked to pay import charges, see [parcels held at customs](guide:customs); if it sits there for long, tell the seller. More in [why tracking stops updating](guide:tracking-not-updating).

**Nothing after `Custom clearance completed`.** The delivering carrier has the parcel, or soon will, and its own tracking may be ahead of WanbExpress's. Find its name and number on WanbExpress's tracker, or ask the seller which carrier delivers and under which number.

**Delivery attempted.** After a line such as `<Route not completed>`, the delivering carrier decides when it tries again and where the parcel waits. Ask that carrier, not WanbExpress.

**Delivered, but nothing came.** Look for a delivery photo on WanbExpress's tracker, then ask the delivering carrier and the seller. More in [delivered but not received](guide:delivered-not-received).

**Lost or returned.** The seller booked the parcel with WanbExpress, so the seller, or the marketplace, is the one to ask for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## WanbExpress contact number and customer service

As of 30 October 2026, Peek couldn't check WanbExpress's website, so this page lists no contact details for WanbExpress. The seller booked the parcel with WanbExpress and is the one to ask about your order, and the marketplace if the seller doesn't answer. For the delivery itself, ask the delivering carrier.

## Track a WanbExpress parcel with Peek

[Peek](/) recognises a WanbExpress number by its shape: WNB, two letters, ten digits and YQ, which no other carrier in its catalog uses, and asks WanbExpress's tracker by that number only. No account, postcode or phone number is needed.

WanbExpress's tracker won't answer until Google's reCAPTCHA has checked that a person is asking, so Peek asks it from a browser. When the tracker still won't answer, Peek turns to tracking services and shows the history they hold, in their words. That history can lag behind WanbExpress's own.

From WanbExpress's tracker, Peek shows the status, each scan with its place, and the origin and destination countries on WanbExpress's record. When that record marks the parcel in transit, out for delivery or delivered, Peek goes by it, even if the last scan's words say less. Peek shows no expected delivery date.

Peek leaves out the delivering carrier's name and number, the delivery photos and the recipient's address that WanbExpress's tracker can show. So it shows no "Delivery with" line and doesn't move the parcel over to the delivering carrier: for that carrier's own scans, add its number to Peek as well. Peek follows more than 3,500 carriers.

While WanbExpress's tracker answers, Peek checks every 10 minutes by day and every few minutes once the parcel is out for delivery. While tracking services answer instead, it checks every 15 minutes, out for delivery too. Overnight, every hour or two. It can notify you of every scan, important steps only or "Delivery only". "Important steps" (delivery, pickup, customs, missed attempts and returns) covers the delivery run, a missed attempt and the delivery, but not WanbExpress's customs lines, which Peek counts as "In transit". Peek can't contact WanbExpress or the seller for you.

## Questions about WanbExpress tracking

### What is WanbExpress?

A Chinese carrier, also called Wanb Express, WanBang Express or WANB. In Peek's code, its scans start at a sorting centre in Shenzhen, follow the parcel's flight and customs, and end with the scans of the carrier that delivers it in the destination country.

### Where can I track a WanbExpress parcel?

In the box above, or on WanbExpress's own tracker at tracking.wanbexpress.com. That tracker checks with Google's reCAPTCHA that you're a person, and can also name the delivering carrier. Use the whole WNB…YQ number.

### What does "Customs declaration completed" mean on WanbExpress tracking?

That a customs declaration for your parcel is done. The wording doesn't say which country's customs, and it isn't the clearance: that shows as `Custom clearance completed`. Peek shows both as "In transit".

### Who delivers WanbExpress parcels in my country?

A local carrier, which WanbExpress's tracker can name, with its number for the parcel. Peek's code shows iMile delivering on a parcel to Germany, but names none for the US, the UK, Ireland, Canada or Australia: check WanbExpress's tracker or ask the seller.

### Who do I contact about a missing WanbExpress parcel?

The seller you ordered from: it booked the parcel with WanbExpress. Peek couldn't check WanbExpress's website for contact details.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – names, WNB number shape, the tracker, its reCAPTCHA check and the link that opens one parcel, scan wordings and their stages, iMile's relayed scans on a parcel to Germany, the scan naming Poste Italiane's network, times with UTC offsets, what the tracker shows (delivering carrier, its number, delivery photos, recipient's address), what Peek keeps and leaves out, and the tracking services it turns to
:::
