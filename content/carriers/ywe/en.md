---
title: YWE (YanwenExpress) tracking: numbers, scans and who to ask
description: Track a YWE (YanwenExpress) parcel in the United States: what a YWE number looks like, what each scan means from hub to door, and who to ask.
slug: ywe-tracking
published: 2026-10-31
updated: 2026-10-31
---

YWE (YanwenExpress, run by Yanwen Express LLC) is a last-mile delivery company in the United States: it brings parcels from its hubs to your door for online shops, retailers and marketplaces. Its numbers are YWE and 14 digits: paste yours in the box above, or in YWE's own tracker at [yanwenexpress.com](https://www.yanwenexpress.com/tracking.html).

## YWE tracking number: what it looks like

YWE's numbers are YWE and 14 digits, 17 characters in all. With invented digits:

| Number | What it is |
| --- | --- |
| `YWE00000000000000` | YWE and 14 digits: a YWE number. |
| `YWE0000000000000` | Only 13 digits: not YWE's layout. Check for a missing digit. |
| `YWAAA000000000000` | YW, three letters and 12 digits: a [Yanwen](carrier:yanwen) number, not a YWE one. |

Spaces and lower case don't matter when you paste the number into Peek.

**Where to find it.** In the shop's shipping email or on your order page. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

**A tracking link.** A link to YWE's tracker that ends in `?label=` and a number opens that parcel. You can paste the whole link into Peek. YWE's tracker takes up to 30 numbers at once.

## YWE tracking statuses explained

YWE's tracker lists each scan with its date and time, its offset from GMT, and the town and state, newest first. The scans are in English. In the order a parcel gets them:

| YWE says | What it means for you | Peek shows |
| --- | --- | --- |
| `Shipping Order Created.` | The sender has booked the delivery with YWE. YWE doesn't have the parcel yet. | "Label created" |
| `Container picked up by YWE,LAX01 Awaiting Item.` | YWE has collected the container your parcel flew in. The parcel itself isn't scanned yet. | "Received by carrier" |
| `Container received` | The hub has taken the container in. | "Received by carrier" |
| `Parcel received by in LAX01.` | The parcel's own first scan at YWE's hub. | "Received by carrier" |
| `Parcel departed from Facility.` | It has left the hub. | "In transit" |
| `Parcel received by transit facility.`, `In processing at transit facility.`, `In transit to next facility.` | At a hub on the way, before the station that delivers your area. | "In transit" |
| `Parcel received by local  Facility.` | At the delivery station, the depot that delivers your area. | "In transit" |
| `Parcel is out for delivery.` | On the delivery run. | "Out for delivery" |
| `Parcel has been delivered.(Front door)` | Delivered. The brackets say where the parcel was left. | "Delivered" |
| `Parcel delivery abnormal [Restricted Address].Parcel will be returned to the warehouse.` | The delivery failed, for the reason in square brackets. The parcel goes back to YWE's warehouse, not to the sender. | "Delivery attempted" |
| `Return to sender` | The parcel is being sent back. It hasn't reached the sender yet. | "Needs attention" |
| `Returned to sender, shipment has been received in sender warehouse.` | Back with the sender. | "Returned to sender" |

**Codes, reasons and places.** `LAX01` is a hub's code: scans at other hubs carry their own code in its place. In the same way, `[Restricted Address]` is one reason for a failed delivery and `(Front door)` one place a parcel is left: other scans give other reasons and places.

**Alert doesn't always mean a problem.** YWE's tracker sorts parcels into Awaiting, Transit, Delivered, Alert and Returned by their latest scan. It files `Parcel received by local  Facility.`, the delivery station's receipt, under Alert, though it's a normal step. Peek shows it as "In transit".

**Other wordings.** A scan that isn't in the table shows in Peek as YWE wrote it, and Peek reads its stage from its words where it can.

**Times.** Each scan comes with its hub's offset from GMT. Peek reads the times that way and shows them all in your device's time zone.

## Who delivers YWE parcels

**United States.** YWE runs the last mile itself, through what its site calls "Delivery Service Partner networks nationwide": local delivery teams that join its network, anywhere in the US, to deliver or collect parcels for it. In Peek's code, a YWE history runs from YWE's hub to your door and names no other carrier.

YWE says it started in Los Angeles in 2024 and plans to cover most of the United States by the end of 2026.

**Delivery attempts and pickup.** YWE publishes no rules on how many times it tries, on notice cards or on pickup points. After a failed attempt, its scan says the parcel goes back to YWE's warehouse. To ask about a new delivery, email YWE (below) with the tracking number, your full address and any delivery instructions. See [missed delivery](guide:missed-delivery).

**UK, Ireland, Canada and Australia.** YWE names no service outside the United States. If you were given a YWE number for a parcel to another country, ask the seller which carrier delivers it there.

**YWE and Yanwen.** [Yanwen](carrier:yanwen) is a separate carrier in Peek, with its own numbers and tracker, and YWE's site doesn't say whether the two are linked. When Yanwen's tracker names YanwenExpress as a parcel's distributor and gives a YWE number for it, Peek shows "Delivery with YWE" and asks YWE's tracker too. Once YWE shows a scan at least as recent as Yanwen's latest, Peek follows the parcel on YWE's tracker and keeps Yanwen's scans.

## YWE parcel not arriving or tracking stuck

**`NOT_FOUND`.** YWE's tracker shows this for a number it has no scans for, and lists possible causes: the parcel hasn't been accepted or scanned yet, the number is wrong, or it was posted so long ago that its tracking is gone. Peek shows "Waiting for the carrier" and keeps checking. Check the number against the seller's message: YWE and 14 digits. If nothing comes after a few days, ask the seller whether the parcel was sent.

**Stuck on `Shipping Order Created.`** YWE has the delivery's details, not the parcel. YWE first scans a parcel at its US hub, so ask the seller where the parcel is now and when it was sent. If it was shipped from China, see [tracking a parcel from China](guide:tracking-from-china).

**Stuck on `Container picked up by YWE,LAX01 Awaiting Item.`** YWE has the container, but hasn't scanned your parcel out of it yet.

**`Parcel delivery abnormal`.** The delivery failed for the reason in square brackets, and the parcel is going back to YWE's warehouse. Email YWE with the number, your full address and any delivery instructions, and tell the seller.

**`Return to sender`.** The parcel is on its way back. Once `Returned to sender, shipment has been received in sender warehouse.` shows, the sender has it. Ask the seller for a refund or a new parcel. See [returned to sender](guide:returned-to-sender).

**Delivered, but nothing came.** The brackets in the delivery scan, such as `(Front door)`, say where it was left. On a delivered parcel, YWE's tracker has a "view receipt" link: enter your ZIP code as the "Recipient's postal code" to see the delivery photos, when there are any. Then email YWE and tell the seller. See [delivered but not received](guide:delivered-not-received).

**Lost.** YWE's accounts are for businesses only, so the business that booked the delivery is YWE's customer, not you. Ask the seller or the marketplace for a refund or a new parcel. YWE's site gives no claim process for recipients. See [lost parcels](guide:lost-parcel), and more in [why tracking stops updating](guide:tracking-not-updating).

## YWE contact number and customer service

As read on 30 October 2026, YWE's site, in English, lists:

- **Email**: an address in the footer of every page. Clicking it shows what to send: your question, the tracking number, and in the email's body your full address, any delivery instructions, your phone number and email address, and what you're asking for.
- **Phone**: none. The Customer support page says "Calling us for further enquiries" but shows no number and no hours.
- **FAQs**: on the Customer support page, written for delivery partners rather than for people waiting for a parcel.

For your order, ask the seller. For the delivery itself, email YWE.

## Track a YWE parcel with Peek

[Peek](/) recognises a YWE number by its shape, YWE and 14 digits, which no other carrier in its catalog uses, and asks YWE's tracker by it. A link to YWE's tracker works too. No account, postcode or phone number is needed.

Peek shows the status, each scan with its town, state and country, and the time of the delivery. Repeated scans are dropped, and so is the name of whoever took the parcel, when a delivery scan gives one.

YWE's tracker can also show a two-hour delivery window while the parcel is in transit, a map with the driver's position and the delivery photos. Peek leaves those out, and the street lines of a scan's place. It shows no expected delivery date.

YWE's tracker names no other carrier, so on a parcel added with its YWE number Peek shows no "Delivery with" line and stays with YWE's tracker up to the delivery. If YWE's tracker can't be reached, Peek asks tracking services in the meantime.

Peek checks every 10 minutes by day, every 2 minutes once the parcel is out for delivery, and every hour or two overnight. It can notify you of every scan, important steps only (delivery, pickup, customs, missed attempts and returns) or "Delivery only". It can't contact YWE or the seller for you.

## Questions about YWE tracking

### What is YWE (YanwenExpress)?

A last-mile delivery company in the United States, run by Yanwen Express LLC. It delivers parcels for online shops, retailers and marketplaces through a network of local delivery teams, and says it started in Los Angeles in 2024.

### Is YWE the same as Yanwen?

Peek treats them as two carriers: Yanwen has its own numbers and tracker, and YWE's site doesn't say whether the two are linked. When Yanwen's tracker names YanwenExpress as a parcel's distributor and gives a YWE number for it, Peek follows that number on YWE's tracker too.

### Where can I track a YWE parcel?

In the box above, or on YWE's tracker at yanwenexpress.com, which takes up to 30 numbers at once. Use the whole number: YWE and 14 digits.

### What does "Container picked up by YWE" mean?

YWE has picked up the container your parcel flew in. The code after the comma, such as LAX01, names the hub. Your parcel's own scan, `Parcel received by in LAX01.`, comes later. Peek shows "Received by carrier" for both.

### Who do I contact about a missing YWE parcel?

The seller first: it booked the delivery with YWE. YWE takes questions by email, at the address in its site's footer, with the tracking number. It shows no phone number.

:::sources
- [YanwenExpress: home page](https://www.yanwenexpress.com/) – last-mile delivery in the United States through Delivery Service Partner networks, 30 numbers at once, email in the footer and what to send
- [YanwenExpress: About us](https://www.yanwenexpress.com/about.html) – Los Angeles in 2024, most of the United States by the end of 2026
- [YanwenExpress: Tracking](https://www.yanwenexpress.com/tracking.html) – scans with their GMT offset, town and state, the Awaiting, Transit, Delivered, Alert and Returned categories, NOT_FOUND and its possible causes, the delivery window, the driver's map, "view receipt" with the recipient's postal code, 30 numbers
- [YanwenExpress: Customer support](https://www.yanwenexpress.com/contactus.html) – no phone number or hours shown, FAQs for delivery partners
- [YanwenExpress: Join us](https://www.yanwenexpress.com/joinus.html) – delivery teams across the United States joining its network
- [YanwenExpress: Privacy Policy](https://customer-portal.yanwenexpress.com/agreement/Yanwen_privacy_policy) – Yanwen Express LLC, accounts for businesses only
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – names, YWE number shape, the tracker and its link, scan wordings and their stages, hub codes, reasons and places, the Alert filing, offsets from GMT, places and names Peek keeps or drops, what Peek leaves out, no other carrier in YWE's history, the hand-over from Yanwen when it names YanwenExpress
:::
