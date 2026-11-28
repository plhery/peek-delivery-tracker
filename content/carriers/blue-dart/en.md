---
title: Blue Dart tracking: waybill numbers and statuses explained
description: Track a Blue Dart India parcel by 11-digit waybill: what Shipment Further Connected and Cnee Refused Id/Otp mean, ICC Worldwide hand-offs and Peek setup.
slug: blue-dart-tracking
published: 2026-11-02
updated: 2026-11-27
---

Blue Dart is an Indian courier whose shipments are tracked by an 11-digit waybill number (the last digit is a check digit) from your sender's shipping email or order page. Paste the waybill in the box above (or enter it on bluedart.com) to check India-time scans such as `Shipment Further Connected` or `Shipment Out For Delivery`. Peek couldn't check Blue Dart's website directly for this page, so every detail below comes from what Peek's tracking code reads from Blue Dart's public tracker.

## Blue Dart waybill number: what it looks like

Peek knows one shape for Blue Dart: 11 digits, the last of them a check digit worked out from the first ten. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `00000000011` | 11 digits with a check digit that adds up: the shape of a Blue Dart waybill. The GLS networks, PostLogistics, Japan Post, Mainfreight and others use 11 digits too, so the shape alone doesn't say Blue Dart. |
| `00000000012` | The same shape with a wrong check digit. Peek doesn't suggest Blue Dart for it: check for a typo. |
| `0000000001` | 10 digits: not a Blue Dart waybill. If you pick Blue Dart for it, Peek says Blue Dart "doesn’t issue numbers like this one". The same goes for 12 digits, or a number with letters. |

Aramex's 11-digit waybills and [Japan Post](carrier:japan-post)'s registered mail carry the same check digit, so even a number that passes can be theirs: check which carrier the shop's message names. Spaces and dashes don't matter when you paste a number into Peek. Blue Dart's tracker can also search by other references, but Peek needs the waybill. Can't find it? See [how to find your tracking number](guide:find-tracking-number).

## Blue Dart tracking statuses explained

Blue Dart's tracker shows the shipment's current status, then its scans, newest first, each with a place, a date and a time. The lines Peek knows from Blue Dart's tracker, roughly in the order a shipment meets them:

| Blue Dart says | What it means for you | Peek shows |
| --- | --- | --- |
| `Pickup Employee Is Out To P/U Shipment` | A Blue Dart courier is on the way to pick up (P/U) the shipment from the sender. Blue Dart doesn't have it yet. | "Label created" |
| `Shipment Picked Up` | Blue Dart has collected it from the sender. | "Received by carrier" |
| `Shipment Arrived`, `Shipment Arrived At Hub` | It has reached a Blue Dart location or one of its hubs, which the scan's place names. | "In transit" |
| `Paper Work Inscan` | Scanned at a hub, between its arrival there and its next connection. | "In transit" |
| `Clubbed Canvas Bag Scan` | Scanned into a canvas bag together with other shipments. | "In transit" |
| `Shipment Further Connected` | Sent on from that location to the next one on its way. | "In transit" |
| `Delivery Scheduled For Next Working Day` | At the office that delivers it, but after that day's cut-off: it goes out on the next working day's round. | "In transit" |
| `Shipment Out For Delivery` | On the delivery round. | "Out for delivery" |
| `Cnee Refused Id/Otp Not Shared-Incorrect` | Not delivered, because of a refusal by the consignee (you) or an ID or one-time code (OTP) not shared or incorrect. | "Delivery attempted" |
| `Delivery Delayed`, `Delay Caused Beyond Control` | The delivery is running late; the second line puts the cause beyond Blue Dart's control. | "Needs attention" |
| `Shipment Delivered` | Delivered. | "Delivered" |
| `Shipment Returned Back To Shipper` | The shipment is back with the sender. Blue Dart gives it as the shipment's status once the return is done. | "Returned to sender" |

A line Peek doesn't know yet shows as Blue Dart writes it and counts as "In transit", unless the parcel is already out for delivery or past that point: then the status stays as it was. More in [what tracking statuses mean](guide:tracking-statuses).

## Blue Dart delivery in India: ID and OTP

Peek couldn't check Blue Dart's delivery rules, such as how many attempts it makes or how long it keeps a shipment it couldn't deliver, so this page gives none.

**ID or OTP.** `Cnee Refused Id/Otp Not Shared-Incorrect` shows that a Blue Dart delivery can depend on an ID or a one-time code (OTP). If you were sent a code for the shipment, keep it to hand on delivery day. If this line appears and you refused nothing, ask the shop to arrange another delivery. See [missed a delivery](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

A message about a failed delivery asks you to pay or share a code through a link? See [delivery scam texts](guide:delivery-scam-texts).

## Blue Dart shipments: who delivers and customs

**A Blue Dart waybill.** Peek knows no carrier that Blue Dart hands its shipments on to, in India or abroad. If the shop gives you another carrier's number as well, add that one too.

**Sent with ICC Worldwide.** ICC Worldwide's tracker can name Blue Dart beside a Blue Dart number. Peek follows the shipment on to Blue Dart only when that number has 11 digits: it asks Blue Dart with it, and once Blue Dart shows a scan at least as recent as ICC Worldwide's newest, follows the shipment there, keeping ICC Worldwide's history. With any other number, Peek stays with ICC Worldwide. Add the shipment by ICC Worldwide's own number, then press "Change" and search for ICC Worldwide: Peek doesn't suggest it from the number.

**Customs.** Peek's code knows Blue Dart in India only, and Peek couldn't check Blue Dart's website for its shipments abroad. For a parcel held at customs, see [parcels held at customs](guide:customs).

## Blue Dart tracking not updating, delayed or returned

For a refund or a new shipment, the shop is your contact: it booked Blue Dart.

**No scans yet.** For a number it has nothing on, Blue Dart's tracker answers `There is no information on the Waybill/Reference Number/Order Number currently.` Peek keeps checking. Check the 11 digits against the shop's message; if nothing comes after a few days, ask the shop whether it handed the shipment over.

**Stuck on `Pickup Employee Is Out To P/U Shipment`.** Blue Dart hasn't collected the shipment from the sender yet. Ask the shop when it hands it over.

**`Delivery Delayed` or `Delay Caused Beyond Control`.** Peek shows "Needs attention". If nothing moves after them, ask the shop.

**No update for days.** When a shipment still on its way has had no new scan for four days, Peek flags it with the day of that scan: "No tracking update since Mon 12 Oct". See [why tracking stops updating](guide:tracking-not-updating).

**Delivered but not there.** Ask your household or your building's reception, then the shop. See [delivered but not received](guide:delivered-not-received).

**Lost or returned.** Ask the shop for a refund or a new shipment: see [lost parcels](guide:lost-parcel). `Shipment Returned Back To Shipper` means it went back to the sender: see [returned to sender](guide:returned-to-sender).

## Blue Dart customer care and contact

Peek couldn't check Blue Dart's website, so this page lists no phone number or email for Blue Dart: Peek gives only contacts read on a carrier's own pages.

For an online order, ask the shop first: it booked Blue Dart. Peek can't contact Blue Dart or the shop for you.

## Track a Blue Dart shipment with Peek

Paste the waybill in [Peek](/). Several carriers use 11 digits, so Peek doesn't show "Detected carrier" for a Blue Dart number on its own:

- **In India** (your phone's region, or on the web where you connect from), Peek shows "Checking tracking services…" while it asks the carriers that use the shape, Blue Dart first. When Blue Dart knows the number, you'll see "Blue Dart has this parcel."
- **Outside India**, Peek asks Blue Dart last, once the other carriers and tracking services have missed the number. To file the shipment under Blue Dart straight away, press "Change" and pick Blue Dart under "Fits this number".

If no carrier knows the number yet, add it all the same, or pick Blue Dart yourself: Peek follows more than 4,500 carriers. Peek lists Blue Dart only when the check digit adds up. If yours doesn't and you're sure of the number, search the carrier list for Blue Dart: Peek asks it all the same. A bluedart.com tracking link with the waybill in it (`trackNo=`) works too: Peek takes the number from it and names Blue Dart at once ("Detected carrier").

Filed under Blue Dart, a shipment shows "Waiting for Blue Dart" until its first scan, or "Check the tracking number" if Blue Dart still doesn't know the number after two days; added without a carrier, "No tracking history yet". Peek reads Blue Dart's public tracker by the waybill alone, with no account: the status Blue Dart gives the shipment, then each scan in Blue Dart's words with its place, read on India time. It leaves out the recipient's details and shows no expected delivery date. The link on the parcel's page, "Open the Blue Dart website", opens the waybill on Blue Dart's tracker.

While you have the shipment open, or on its delivery day with notifications on, and Blue Dart's own tracker answers, Peek checks up to every 10 minutes, and up to every 2 minutes from 6 a.m. local time on the day Blue Dart scans the shipment out for delivery; otherwise hourly. After two days without news (12 hours while the shipment still shows "Label created" or "Received by carrier"), Peek checks hourly. When only tracking services answer, it checks hourly, every 3 hours once the newest scan is a day old, and up to every 15 minutes on the day the shipment is out for delivery while you have it open or notifications are on. Alerts: "Every scan", "Important steps" or "Delivery only".

## Questions about Blue Dart tracking

### How do I track a Blue Dart shipment by waybill number?

Enter your 11-digit waybill on bluedart.com or paste it into Peek. From India, Peek asks Blue Dart first; elsewhere, it asks Blue Dart last, once the other carriers and tracking services have missed the number, or you can press "Change" and choose Blue Dart under "Fits this number". A bluedart.com link with the waybill in it (`trackNo=`) names Blue Dart at once.

### What does a Blue Dart 11-digit waybill number look like?

It is always 11 digits with a check digit at the end; 10-digit, 12-digit or alphanumeric codes aren't Blue Dart waybills in Peek. Aramex and Japan Post also use 11-digit numbers with the same check digit, so check which carrier the shop named.

### What does "Shipment Further Connected" mean on Blue Dart?

Blue Dart has dispatched your parcel onward from that hub or office to the next facility along its route. Peek maps this scan to "In transit".

### What does "Cnee Refused Id/Otp Not Shared-Incorrect" mean?

It is a failed delivery attempt ("Delivery attempted" in Peek), recorded when the consignee refused the parcel or didn't share a matching ID or one-time code (OTP). Keep any delivery OTP handy, or ask the shop to arrange a reattempt if you didn't refuse it.

### Why is Blue Dart stuck on "Pickup Employee Is Out To P/U Shipment" or missing?

That line means a courier is on the way to collect the parcel and Blue Dart doesn't have it yet. For pickup delays, lost shipments or returns (`Shipment Returned Back To Shipper`), contact the shop or sender who booked Blue Dart — Peek couldn't verify Blue Dart's own phone or email pages.

:::sources
- [Universal Parcel Scraper: Blue Dart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/blue-dart) – India, the 11-digit waybill and its check digit, the tracker and its no-information answer, wordings and their stages, India time, what is left out, tracking links
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – other carriers' 11-digit numbers, Aramex's and Japan Post's check digit, carriers asked in India and abroad, ICC Worldwide's link to Blue Dart and the 11-digit rule, no hand-over from Blue Dart
:::
