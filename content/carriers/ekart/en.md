---
title: Ekart tracking: tracking IDs, statuses and RTO explained
description: Track an Ekart Logistics parcel in India: 14-character tracking ID format, what InscannedAtDH, undelivered_attempted and RTO mean, and who to contact.
slug: ekart-tracking
published: 2026-11-02
updated: 2026-11-27
---

Track an Ekart (eKart Logistics) parcel in India by pasting its 14-character tracking ID (three letters, `C`, `P` or `R`, and ten digits — such as `FMPP…`) in the box above or on ekartlogistics.in. Get the tracking ID (not the store's order number) from the seller's shipping message. If tracking shows `InscannedAtDH`, your parcel is at the delivery hub before going out; `ShipmentRtoConfirmed` or `Marked RTO` means it is returning to the seller — contact the shop or marketplace first, as Ekart lists no recipient phone number.

## Ekart tracking ID: what it looks like

Peek's code knows Ekart's tracking IDs as three letters, then `C`, `P` or `R`, then ten digits: 14 characters in all. With invented digits:

| Tracking ID (invented) | What it is |
| --- | --- |
| `FMPP4829105736` | One of the letter groups Peek ties to Ekart (listed below), then `C`, `P` or `R` and ten digits. Peek names Ekart from the shape alone. |
| `ABCP4829105736` | Other letters in front, same layout. It may be Ekart's, but Peek can't tell from the shape alone, so it asks Ekart (see below). |

The letter groups Peek ties to Ekart are `AMI`, `BAL`, `BGS`, `BSI`, `DEL`, `FLO`, `FMP`, `GLA`, `LUA`, `MYE`, `MYN`, `MYS`, `SDL`, `SHO`, `SPO`, `SRT` and `TEL`. Spaces and lower case don't matter when you paste the ID into Peek. Two exceptions: an ID that starts with `PB` in this layout is read as [Parcelforce](carrier:parcelforce)'s, the UK courier, and one that starts with `HRBC` isn't offered as Ekart's, as it fits an AliExpress shape. If yours came from an Indian shop, press "Change" and pick Ekart.

Use the tracking ID, not the shop's order number. Can't find it? Look for an Ekart tracking link in the shop's message, or see [how to find your tracking number](guide:find-tracking-number).

## Ekart tracking statuses explained

Ekart's tracker lists each scan with a date, a time and a city. Some lines are plain English, such as `Shipment Created`. Others are a code followed by a dash and the hub's name, such as `InscannedAtDH` and then the hub. The lines Peek's code knows from Ekart's tracker, roughly in the order a parcel gets them:

| Ekart says | What it means for you | Peek shows |
| --- | --- | --- |
| `Pickup Requested`, `Shipment Created` | The seller has booked Ekart. Ekart doesn't have the parcel yet. | "Label created" |
| `OutForPickupEvent` | An Ekart pickup run is on its way to the seller. The parcel is still with the seller. | "Label created" |
| `NotPickedEvent` | The pickup failed: the parcel is still with the seller. | "Label created" |
| `Pickup From Seller`, `PickupReceived` | Ekart has collected the parcel from the seller. | "Received by carrier" |
| `ShipmentGrouped`, `ShipmentInTransit`, `dispatched_to_tc`, `dispatched`, `Dispatched to` and the next hub | Moving through Ekart's network, between hubs. | "In transit" |
| `Expected` | The hub it's travelling to has been told it's coming. | "In transit" |
| `Received`, `Received at` and the hub | Arrived at an Ekart hub. | "In transit" |
| `InscannedAtFacility` | Scanned in at an Ekart facility. | "In transit" |
| `InscannedAtDH`, `PrimaryDHInscan` | At the delivery hub (DH) that serves your area: delivery comes next. | "In transit" |
| `Out For Delivery` | On the delivery run today. | "Out for delivery" |
| `Delivered` | Delivered to you. After an RTO line, delivered back to the seller (below). | "Delivered" |
| `undelivered_attempted`, `ShipmentUndeliveredAttempted` | A delivery was tried and didn't succeed. `undelivered_update` can come at the same moment, beside the hub. | "Delivery attempted" |
| `undelivered_unattempted` | The delivery run ended without an attempt. | "Delivery attempted" |
| `Unsuccessful` and a reason, such as `Unsuccessful Unable to attempt delivery due to operational issues` or `Unsuccessful Undelivered. (Rescheduled to: …)` | The day's delivery run ended without delivering, in words. A date after `Rescheduled to:` is the day of the next try. An `Unsuccessful` line about a pickup isn't read this way. | "Delivery attempted" |
| `PickupCancel` | The pickup was cancelled before the parcel entered Ekart's network. | "Needs attention" |
| `ShipmentRtoConfirmed` | The return to the seller (RTO) is confirmed: the parcel is going back. | "Needs attention" |
| `Marked RTO: ORDER_REJECT` | In words, after delivery runs that didn't deliver: the parcel is marked to go back to the seller, with the reason after the colon. Later lines are on the way back. | "Needs attention" |
| `RtoExpected`, `RtoReceived`, `ReturnInscanned` | On its way back to the seller. | "In transit" |

**RTO.** Peek reads `ShipmentRtoConfirmed`, a `Marked RTO` line, or any line whose code starts with `Rto` or `Return`, as the start of the return to the seller: that line and every later one are on the way back. `InscannedAtDH` then means the seller's hub, not yours, and a later `Delivered` means the parcel reached the seller: Peek shows it as "Returned to sender", not "Delivered". More in [what tracking statuses mean](guide:tracking-statuses).

## How Ekart delivers in India

Peek's code knows Ekart in India only. The lines above trace the route: a pickup from the seller, Ekart's hubs, the delivery hub near you, then the delivery run. Peek hands Ekart parcels to no other carrier.

This page gives no delivery times, attempt rules or ways to reschedule, and Peek's code doesn't say how many more tries Ekart makes after `undelivered_attempted` or when a parcel goes back to the seller: ask the shop that booked Ekart. See [missed a delivery](guide:missed-delivery).

## Ekart tracking not updating, RTO or not delivered

**Stuck on `Shipment Created` or `Pickup Requested`.** The seller has booked Ekart but hasn't handed the parcel over yet. `NotPickedEvent` means a pickup failed. Ask the seller when it will ship.

**No new scan for days.** When a parcel that's booked, collected or in transit has had no new scan for four days, Peek flags it with the day of that scan: "No tracking update since Mon 12 Oct". Ask the shop what's holding it up. See [why tracking stops updating](guide:tracking-not-updating).

**`undelivered_attempted`, `undelivered_unattempted` or `Unsuccessful`.** Delivery didn't happen; `Rescheduled to:` gives the day Ekart plans to try again. The tracker's next lines show whether the parcel goes out again or heads back. Make sure the shop has your right address and phone number.

**`ShipmentRtoConfirmed` or `Marked RTO: ORDER_REJECT`.** The parcel is going back to the seller. Once Ekart's tracker shows it `Delivered` there, Peek shows "Returned to sender". Ask the shop for a refund or to send it again: see [returned to sender](guide:returned-to-sender).

**`PickupCancel`.** The pickup from the seller was cancelled, so the parcel never entered Ekart's network. Ask the seller whether your order is still coming.

**Delivered, but nothing came.** Check with your household, neighbours and your building's security or reception, then tell the shop. See [delivered but not received](guide:delivered-not-received).

**Lost or damaged.** Ask the seller or the marketplace you bought from: it booked Ekart. See [lost parcels](guide:lost-parcel) and [damaged parcels](guide:damaged-parcel).

## Ekart customer care and contact

Ekart's website lists no phone number or email for recipients; its FAQ sends sellers to a ticket on its portal, answered within 24 hours. For an order, ask the shop or marketplace you bought from first: it booked Ekart, and a refund or a new parcel comes from it.

Before you pay anything a parcel text asks for, read [delivery scam texts](guide:delivery-scam-texts).

## Track an Ekart parcel with Peek

Paste the tracking ID in [Peek](/). With one of the letter groups above, then `C`, `P` or `R`, Peek names Ekart at once ("Detected carrier"); "Change" picks another carrier. With other letters in front, Peek shows "Checking tracking services…" and asks Ekart's tracker whether it has the ID: once it shows a scan for it, Peek says "Ekart has this parcel." and follows it there. If Ekart's tracker has nothing for it yet, Peek asks tracking services instead; to get Ekart's own lines anyway, press "Change", where Ekart is under "Fits this number", and pick it.

Pasting Ekart's tracking link (ekartlogistics.com, then shipmenttrack and the ID) works too: Peek takes the ID from it and names Ekart, whatever its letters. If the seller sent a shiprocket.co tracking link instead, you can paste that: Peek then follows the parcel on [Shiprocket](carrier:shiprocket)'s tracking page, not Ekart's. Ekart picked by hand for anything other than four letters and ten digits shows "doesn’t issue numbers like this one".

Filed under Ekart, a parcel shows "Waiting for Ekart" until Ekart's first scan. Two days after Ekart first says it doesn't know the number, Peek asks you to check it, choose another carrier or delete the parcel. One added without a carrier shows "No tracking history yet". Peek reads Ekart's own tracker, with no account or phone number: each line in Ekart's words, codes included, with its exact time and the city Ekart gives, newest first and without repeats. Times are shown in your device's time zone; on peektracker.com and in the iPhone and Android apps, the history ends with "Times are in your time zone:" and its name. It shows Ekart's expected delivery date while the parcel is on its way to you, and drops it once the parcel is delivered or heads back.

While you have the parcel open, or on its delivery day with notifications on, Peek checks up to every 10 minutes, or every 2 minutes from 6 a.m. India time on the day Ekart scans the parcel out for delivery, while Ekart's own tracker answers (through tracking services, up to every 15 minutes that day while you have it open or notifications are on); otherwise hourly. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), it checks hourly. Alerts: "Every scan", "Important steps" or "Delivery only". Peek can't contact Ekart or the shop for you.

## Questions about Ekart tracking

### How do I track my Ekart Logistics parcel?

Paste the 14-character tracking ID from the shop's shipping email or SMS into the box above in Peek, or enter it on ekartlogistics.in (or paste an `ekartlogistics.com/shipmenttrack/…` link directly into Peek). Use the tracking ID, not the store's order number.

### What does an Ekart tracking ID look like?

An Ekart tracking ID is 14 characters: three letters, then `C`, `P` or `R`, then ten digits — for example `FMPP4829105736` (invented). Peek recognises 17 common prefixes (including `FMP`, `DEL`, `MYN`, `MYS`, `SHO` and `SRT`) automatically and checks Ekart's tracker for other four-letter prefixes.

### What does InscannedAtDH mean in Ekart tracking?

`InscannedAtDH` (and `PrimaryDHInscan`) means the parcel was scanned in at an Ekart delivery hub (DH). On the way to you, it is the local hub before `Out For Delivery`; after an RTO scan, it is the seller's hub.

### What do undelivered_attempted and RTO mean in Ekart tracking?

`undelivered_attempted` or `Unsuccessful` means the delivery round ended without delivering (a `Rescheduled to:` date shows the next try). `ShipmentRtoConfirmed` or `Marked RTO: ORDER_REJECT` starts Return to Origin (RTO): the parcel is returning to the seller, and a later `Delivered` scan means the seller received it back.

### What is Ekart's customer care number for a missing or delayed parcel?

Ekart's website publishes no customer care phone number or email for recipients (its FAQ covers seller portal tickets, answered within 24 hours). Contact the shop or marketplace you ordered from for delays, RTO refunds or replacements.

:::sources
- [Ekart Logistics: FAQ](https://www.ekartlogistics.in/faq) – support for sellers by a ticket on the portal, answered within 24 hours
- [Universal Parcel Scraper: Ekart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ekart) – names, tracking ID shapes and how Peek detects them, tracking links, Ekart's tracker, scan wordings and codes with their stages, RTO, expected delivery date
- [Universal Parcel Scraper: Shiprocket](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shiprocket) – Shiprocket's tracking links
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Parcelforce's PB and AliExpress's HRBC shapes, carriers recognition asks, hand-offs
:::
