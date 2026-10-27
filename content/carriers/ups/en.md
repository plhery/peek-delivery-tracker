---
title: UPS tracking: numbers, statuses and missed deliveries
description: Track a UPS parcel: where to find the 1Z tracking number, what Exception and other UPS statuses mean, InfoNotice, Access Points, claims and UPS contacts.
slug: ups-tracking
published: 2026-10-13
updated: 2026-10-27
---

UPS (United Parcel Service) is the American parcel carrier that many shops in the UK, Ireland, the US and the rest of Europe hand their parcels to. Track a parcel on ups.com, with the link in UPS's email notifications or by pasting the number in the box above. The number is on the label and in the shop's shipping email, and usually starts with `1Z`.

## UPS tracking number: where to find it and what it looks like

Look in the shop's shipping email or order page, in UPS's email notifications and on the label. The US Delivery Notice left after a missed delivery carries a 12-digit reference that also works. Still nothing? See [where else to find a tracking number](guide:find-tracking-number).

UPS lists these shapes on its tracking help pages (digits invented):

| Shape (invented) | What it is |
| --- | --- |
| `1Z1234567812345674` | The usual parcel number: `1Z` and 16 letters and digits, 18 characters in all. |
| `000000000000` | 12 digits, also the length of the Delivery Notice reference. |
| `T1234567890` | `T` and 10 digits. |
| `000000000` | 9 digits. |

The 4 to 6 digit release code some senders give you isn't a tracking number. Twelve digits also fit [FedEx](carrier:fedex) and several European couriers, so if ups.com finds nothing, check which carrier the shop's email names.

## What UPS tracking statuses mean, including delivery exception

These are UPS's English wordings, in the order a parcel usually meets them.

| UPS's status | What it means for you |
| --- | --- |
| `Label Created` | The sender has printed a label and sent UPS the details. UPS doesn't have the parcel yet. |
| `Shipped/On the Way` | UPS has it, it's moving and it now has a scheduled delivery date. |
| `Exception` | Something unexpected happened and the delivery date may change. |
| `Out for Delivery` | The local UPS facility has given it to a driver for delivery. |
| `Transferred to Post Office for Delivery` | US only: the local post office finishes the delivery. |
| `Delivery Attempted` | The driver couldn't deliver. |
| `Delivered to a UPS Access Point` | It's at a UPS Access Point shop, waiting for you. |
| `Awaiting Customer Collection` | The UK wording once you can go and pick it up (the US says `Awaiting Customer Pickup`). |
| `Delivered` | Without a signature, it's left in a safe place; ups.com/track shows a photo. |
| `Return to Sender` | It's going back to the sender. |

`Shipped/On the Way` can stay put for days. UPS says parcels travelling long distances often aren't scanned again until they reach the destination hub.

`Exception` is often just a delay. UPS notes the reason under Shipment Progress and shows the new date once it changes.

`Transferred to Post Office for Delivery` is US Ground Saver: allow an extra day or two, says UPS.

## Missed UPS delivery, InfoNotice and Access Points

UPS delivers Monday to Friday, plus Saturday in select areas (and Sunday in some US areas), excluding UPS holidays. Unless the sender paid for a time-definite service, homes are typically served [between 9 a.m. and 7 p.m.](guide:delivery-times), occasionally later; in the US, UPS says as late as 9 p.m. UPS may make up to three attempts. After the last one, an undeliverable parcel goes back to the sender.

When the first attempt fails, the driver leaves a UPS InfoNotice (a Delivery Notice or door tag in the US). Track the parcel first, then you can:

- **Sign the notice** where it says "Your Signature" and put it back where the driver left it, unless the parcel needs an adult's signature (18+ in the UK, 21+ in the US).
- **Change the delivery**: on the tracking page, select "Change My Delivery" and UPS emails you a one-time passcode; as a guest you can use the Delivery Notice number. Some options may be missing: the sender may block changes, and high-value items or imports may not qualify. Changing by phone costs €6 plus VAT, or the equivalent in local currency, says UPS's UK contact page.
- **Pick it up at a UPS customer centre**: ask by 7 p.m.

UPS may instead take it to a nearby UPS Access Point. Its InfoNotice page says the parcel waits there 7 calendar days, then goes back to the sender; its tracking help says 5 to 7 days, so don't wait. Check that tracking shows `Awaiting Customer Collection`, along with the shop's hours and a map. Bring government-issued photo ID and your tracking number. Someone picking it up for you brings the InfoNotice or a release form.

UPS My Choice is free in the UK and gives members a delivery window estimate.

## UPS tracking not updating, delayed or lost

Quiet days during `Shipped/On the Way` are normal on long journeys; see [tracking that isn't updating](guide:tracking-not-updating) for when to worry.

Marked delivered but not there? Check the delivery photo on ups.com/track, then ask your household, a concierge or a neighbour. If it's still missing, UPS says to contact the seller to start a claim. More in [delivered but not received](guide:delivered-not-received).

For loss or damage, the sender usually claims: UPS pays claims to the shipper, and some shippers (UPS names Amazon, Dell and Apple) block recipients' claims. Where you can file, filing as a guest is faster. In Europe, the deadline follows the terms of the shipper's country, and UPS typically settles in 8 to 15 business days. In the US, UPS's claim page says to start within 60 days of the scheduled delivery and settles in 8 to 10 business days; its help centre encourages a loss investigation within 30 days of the expected delivery date. Ground Saver claims stop once the parcel has been handed to the post office.

## How to contact UPS customer service

UPS's contact pages, read on 13 October 2026:

- **United Kingdom**: +44 (0)3457 877 877, Monday to Friday 8 a.m. to 8 p.m., Saturday 8 a.m. to 5 p.m.; the page also offers a virtual assistant.
- **Ireland**: +353 1 52 45446, Monday to Friday 8 a.m. to 8 p.m., Saturday 8 a.m. to 2 p.m.
- **United States**: 1-800-742-5877, Monday to Friday 7 a.m. to 11 p.m. ET, Saturday 8 a.m. to 8 p.m. ET; hearing impaired 1-800-833-0056; international shipping support 1-800-782-7892, around the clock.

> UPS warns about fake messages claiming a parcel is waiting. It never asks for payments, personal or financial details unprompted. Real UPS texts come from 75137 (or 24031 in the US). If a message asks you to pay, track the parcel on ups.com first and use "pay now" there.

## Tracking UPS parcels with Peek

[Peek](/) names UPS at once for a `1Z` number whose check digit is right; with a wrong one, UPS refuses it as mistyped. Links holding a `1Z` number work too. Peek looks up only `1Z` numbers at UPS: other shapes go to tracking services.

Peek opens ups.com's tracking page in a browser on its own server and reads the reply, every 10 minutes by day, every 5 minutes once the parcel is out for delivery, and every hour or two overnight. If UPS refuses, or only the status can be read, tracking services fill in the history. It shows the status, each scan on a map with its exact time, the scheduled delivery day (never a time window), the delivered time, the service and, while the parcel waits, the Access Point's name and address. It can notify you at every scan, at important steps or on delivery day only.

It drops the signatory, your address, proof of delivery and where the parcel was left, and shows no delivery photo or weight. Scans appear in UPS's English; delivery reads "DELIVERED". When UPS hands a Ground Saver parcel to the [US Postal Service](carrier:usps) and names its number, Peek follows it there once USPS shows progress ("Delivery with USPS").

## Questions about UPS tracking

### How late can a UPS package arrive?

Usually by 7 p.m. at homes, occasionally later. In the US, UPS says Ground parcels to homes can come as late as 9 p.m.

### Why is my UPS tracking number invalid?

Most often it was mistyped: one wrong character in a `1Z` number breaks its check digit. Or it belongs to another carrier.

### How long does UPS tracking take to show up?

Once the sender has sent UPS the shipment details, the number shows `Label Created`. It only moves on once UPS has the parcel and it is moving in its network.

### Is a UPS delivery text a scam?

It can be. UPS never asks for payment or personal details unprompted, and [its real texts](guide:delivery-scam-texts) come from 75137 (or 24031 in the US), so track the parcel on ups.com instead of tapping the link.

:::sources
- [UPS: Tracking Support (United Kingdom)](https://www.ups.com/gb/en/support/tracking-support) – number shapes, 5 to 7 days, 7 p.m., delivered not found, card payments, texts from 75137
- [UPS: Tracking Support (United States)](https://www.ups.com/us/en/support/tracking-support) – number shapes, Delivery Notice, as late as 9 p.m., texts from 24031
- [UPS: Understanding Tracking Status (United Kingdom)](https://www.ups.com/gb/en/support/tracking-support/where-is-my-package/understanding-tracking-status) – status meanings, delivery hours, delivery photo
- [UPS: Understanding Tracking Status (United States)](https://www.ups.com/us/en/support/tracking-support/where-is-my-package/understanding-tracking-status) – status meanings, post office transfer
- [UPS: UPS InfoNotice (United Kingdom)](https://www.ups.com/gb/en/support/tracking-support/where-is-my-package/how-to-use-infonotice) – delivery days, three attempts, signing, 7 p.m., 7 calendar days, ID, cash only
- [UPS: UPS Delivery Notice (United States)](https://www.ups.com/us/en/support/tracking-support/where-is-my-package/how-to-use-infonotice) – Delivery Notice number, 21+, release code, Awaiting Customer Pickup
- [UPS: Change a Delivery (United Kingdom)](https://www.ups.com/gb/en/track/change-delivery) – passcode, guests, restrictions, phone fee
- [UPS: UPS My Choice (United Kingdom)](https://www.ups.com/gb/en/track/ups-my-choice) – free, delivery window, holding times
- [UPS: File a Claim (United Kingdom)](https://www.ups.com/gb/en/support/file-a-claim) – guest claims, shipper restrictions, shipper-country terms, 8 to 15 days
- [UPS: File a Claim (United States)](https://www.ups.com/us/en/support/file-a-claim) – 60 days, 8 to 10 days, Ground Saver
- [UPS: Contact Us (United Kingdom)](https://www.ups.com/gb/en/support/contact-us) – phone, hours, €6 fee
- [UPS: Contact Us (Ireland)](https://www.ups.com/ie/en/support/contact-us) – phone, hours
- [UPS: Help and Support Center (United States)](https://www.ups.com/us/en/support/contact-us) – phone numbers, hours, 30-day advice
- [UPS: Fight Fraud (United Kingdom)](https://www.ups.com/gb/en/support/shipping-support/legal-terms-conditions/fight-fraud) – what UPS never asks, fake messages
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads from UPS
:::
