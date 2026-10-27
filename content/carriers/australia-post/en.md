---
title: Australia Post tracking: numbers, statuses and lockers
description: Track an Australia Post parcel: where the tracking number is, what each status means, Post Office and Parcel Locker pickup, late parcels and contacts.
slug: australia-post-tracking
published: 2026-10-15
updated: 2026-10-27
---

Australia Post is Australia's national postal service. Track a parcel on [auspost.com.au/mypost/track](https://auspost.com.au/mypost/track), in the AusPost app or by pasting the number in the box above, no account needed. The number comes from the sender: the shop's order confirmation, an email from Australia Post or your Post Office receipt.

## Australia Post tracking number: where to find it and what it looks like

Australia Post calls it the "tracking number, or article ID". It's in one of these places:

- **Bought online:** the shop's order confirmation or invoice. If the sender gave Australia Post your email, it emails you the number and progress itself.
- **Posted at a Post Office:** on your receipt.
- **Prepaid satchel:** on the barcode and the removable sticker.

Australia Post can't find a parcel by name or address, so if you have no number, ask the sender. Parcels matched to your MyPost email or mobile appear in your account and the app on their own.

Australia Post publishes no number format. These are the shapes Peek knows, with invented examples:

| Shape (example) | What it is |
| --- | --- |
| `ABC123456789000123456` | Article ID, 21 characters: 3 letters or digits, 9 digits, `000`, 6 digits. |
| `12ABC123456789000123456` | The same with 2 digits in front (23 characters). |
| `94001234567890000123456` | 23 digits starting `9400`: still Australia Post. |
| `12ABC1234567` | Consignment number, 12 characters. |
| `EE000000005AU` | EMS, parcel or tracked-letter number: `C`, `E` or `L`, a letter, 9 digits, `AU`. |
| `RR000000005AU` | Other international postal number ending `AU`, such as registered mail. |

Lookalikes: 2 letters and 10 digits (`AB1234567890`) belong to [Aramex Australia](carrier:aramex-au) or [Aramex New Zealand](carrier:aramex-nz); a 22-digit `9400` number is USPS's. Number not found? It may be wrong or not scanned yet: Australia Post suggests trying again in 24 hours. More in [tracking number formats](guide:tracking-number-formats).

## What Australia Post tracking statuses mean

The tracking page shows a short milestone above the scans.

| Australia Post's status | What it means for you |
| --- | --- |
| `Label created by sender` | The sender has made a label; Australia Post doesn't have the parcel yet. |
| `Pending` | Not scanned into Australia Post's system yet. It should update in 24 to 48 hours. |
| `We've got it` | Australia Post has the parcel. |
| `It's on its way` | Posted, processed and moving through the sorting facilities. |
| `International arrival - awaiting clearance` | From abroad: waiting for Australian customs. |
| `Possible delay` | Weather, an emergency, a sender mistake or customs is slowing it down. It still comes. |
| `It's coming today` | On the delivery vehicle today. |
| `Attempted delivery` | No one was available, or the driver couldn't get in. |
| `Awaiting collection` | Waiting at a Post Office or Parcel Locker; the scan names which. |
| `Delivered` | Delivered to the address. |
| `Delivered - Left in a safe place` | Left in a safe place at the address. |
| `Returning to sender` | Not collected, not known at the address, declined or damaged: going back. |

If `Pending` or `Ready for processing` lasts more than a week, ask the sender whether the parcel was really sent. Interstate parcels go through big centres such as Chullora NSW or Sunshine West VIC, so a scan far from both of you is normal.

`Contact sender` means the parcel went back because the details were wrong or incomplete, or a collection notice went unanswered for 10 business days. `Returned` means it's back with the sender: they arrange redelivery.

## Missed delivery, Post Office pickup and Parcel Lockers

If no one is home and there's no safe place, the parcel goes to a nearby Post Office, or may go to a Parcel Locker if your mobile number is on it (you get an access code). MyPost users get an email, app or SMS notice; others may still get a "Sorry We Missed You" card.

A Post Office holds the parcel for 10 business days, then returns it. There's no redelivery from a Post Office; moving it to another one costs $7.20. Bring one photo ID, or two non-photo IDs with one showing your address. Someone else can collect with an authorisation from the app, a form or the card, plus their own ID.

Parcel Lockers are free and open 24/7. Collect within 2 business days with the access code or the app's QR code; after that the parcel moves to a nearby Post Office for 10 business days, with a reminder email 24 hours before.

With a free MyPost account you can redirect a matched parcel to another address, a locker or a Post Office until it's on board for delivery, and choose Safe Drop up to an hour before.

## Australia Post tracking not updating, late or delivered but not received

No scans for a while doesn't mean it stopped: Australia Post says the parcel is still moving. Most late parcels arrive within 5 business days of the expected date. See [tracking that isn't updating](guide:tracking-not-updating).

Australia Post sends receivers to the sender first: confirm the number and address, and the sender raises an enquiry for you. If that fails, use the late or missing item form (it needs a free MyPost account) once 10 business days have passed since the expected date. Parcels from overseas can spend up to 15 days in Australian customs; if customs or quarantine hold one, they write to you.

Marked delivered but not there? Check safe places, your household and the mailroom, look for a delivery photo in the app or the form, then submit the form. Damaged: take the parcel and packaging to a Post Office that does damage assessments. Most services include up to $100 for loss or damage; Australia Post usually pays the sender for lost domestic parcels and the receiver for damaged ones, with claims due within 6 months of posting. Bought online? Contact the seller first. More in [delivered but not received](guide:delivered-not-received).

## Australia Post contact number and help

As listed on auspost.com.au on 15 October 2026:

- Phone: 13 76 78 (13POST) in Australia, +61 3 8340 7239 from overseas, 8am to 6pm Monday to Friday except national public holidays.
- Online: the late or missing item and delivery issue forms, and a chat with a bot or live agent, on [auspost.com.au/help](https://auspost.com.au/help).
- In person: any Post Office can help lodge a complaint.

StarTrack, Australia Post's courier and freight arm, has its own numbers (13 23 45).

> Australia Post never calls, texts or emails asking for payment or personal details. Fake texts mention a "redelivery fee", verifying your postcode or a driver who found no safe place, and some arrive under the "AusPost" sender name. Forward them to scams@auspost.com.au.

## Tracking Australia Post parcels with Peek

[Peek](/) names Australia Post at once for 21- and 23-character article IDs. Consignment numbers and 22-digit prepaid barcodes are shared with other carriers, so Peek asks the likely ones at once and names the one that knows the number; if it can't settle, pick Australia Post with "Change". It also names Australia Post at once for an EMS, parcel or tracked-letter number ending `AU` with a valid check digit; any other postal number ending `AU` shows as a postal item while Peek asks Australia Post. Links from auspost.com.au with `id=` or `/mypost/track/details/` work; older `#/search` links don't, so paste the number.

If Australia Post refuses Peek's plain request, a browser on Peek's server opens auspost.com.au instead. Tracking services hold little of Australia Post's history, so if the browser is refused too, the history can be thin.

Peek shows the status, each scan with its place on a map, the service, the time of delivery, the destination country and the name of the Post Office or Parcel Locker holding the parcel. Statuses stay in Australia Post's English; delivered ones read "Delivered". Peek drops the recipient, the address and delivery photos, and shows no expected delivery date. It can't redirect a parcel or change a locker, and it doesn't track StarTrack.

It checks every 10 minutes from 8am to 10pm Swiss time, about 6pm to 8am in Sydney (4pm to 6am in winter; Perth's clocks are 2 to 3 hours behind Sydney's), every 2 minutes in those hours once out for delivery, and every hour or two the rest of the time, so most of a delivery day is checked every hour or two. It can notify you at every scan, at important steps or on delivery day only. A parcel posted abroad with a postal number bound for Australia can be followed on Australia Post once Australia Post shows progress.

## Questions about Australia Post tracking

### What does pending mean on Australia Post tracking?

The parcel hasn't been scanned into Australia Post's system yet: it may not be posted yet, or was posted in a street posting box. It should update within 24 to 48 hours.

### How long does Australia Post hold parcels in a Parcel Locker?

2 business days. Then the parcel moves to the nearest Post Office offering collection, which holds it 10 business days before returning it.

### Do all Australia Post parcels have tracking numbers?

No. International Post Registered and International Economy (Air and Sea) have no tracking as standard. For a parcel coming to Australia, tracking depends on the sending country's post or courier.

:::sources
- [Australia Post: Lost tracking number](https://auspost.com.au/help/parcel-tracking/tracking-support/lost-tracking-number) – where the number is, no name lookup, not-found messages
- [Australia Post: Help with tracking](https://auspost.com.au/help/parcel-tracking/tracking-support/help-with-tracking) – article ID, processing centres, recent events only
- [Australia Post: What your status means](https://auspost.com.au/help/parcel-tracking/tracking-support/what-your-status-means) – Pending, attempted delivery, Sorry We Missed You card
- [Australia Post: Tracking service](https://auspost.com.au/personal/receiving/tracking-service) – tracking without an account, It's on its way
- [Australia Post: Taking longer than expected](https://auspost.com.au/help/parcel-tracking/taking-longer-than-expected) – Pending and Ready for processing, 5 business days, processing centres
- [Australia Post: Delays and disruptions](https://auspost.com.au/help/parcel-tracking/delays-and-disruptions) – Possible delay
- [Australia Post: Return to sender](https://auspost.com.au/help/delivered-parcel/return-to-sender) – Returned, Returning to sender, Contact sender
- [Australia Post: Missed parcel deliveries](https://auspost.com.au/personal/receiving/parcel-deliveries/missed-parcel-deliveries) – Post Office or locker after a miss, ID, authority to collect
- [Australia Post: Requested delivery but parcel at Post Office](https://auspost.com.au/help/delivered-parcel/parcel-at-post-office) – 10-business-day hold, no redelivery, $7.20 transfer
- [Australia Post: 24/7 Parcel Lockers](https://auspost.com.au/help/collection-delivery/parcel-lockers) – 2 business days, access code, reminder, address format
- [Australia Post: Redirect parcel or change address](https://auspost.com.au/help/parcel-tracking/redirect-parcel-change-address) – redirect rules
- [Australia Post: Leave in a safe place (Safe Drop)](https://auspost.com.au/help/collection-delivery/safe-drop) – Safe Drop, Day of Delivery SMS, delivery hours
- [Australia Post: AusPost app](https://auspost.com.au/about-us/about-our-site/australia-post-app) – digital notices, cards when unmatched
- [Australia Post: No recent update](https://auspost.com.au/help/parcel-tracking/no-recent-update) – no scans, 5 days, 10 business days, phone hours
- [Australia Post: Lost or late items](https://auspost.com.au/personal/receiving/parcel-deliveries/lost-late-items) – enquiry form, MyPost account
- [Australia Post: Customs](https://auspost.com.au/help/parcel-tracking/international-parcel/customs) – up to 15 days, letters from customs
- [Australia Post: International tracking](https://auspost.com.au/help/parcel-tracking/international-parcel/tracking) – services without tracking, inbound tracking
- [Australia Post: Delivered status but parcel not received](https://auspost.com.au/help/delivered-parcel/parcel-not-received) – checks, delivery photo
- [Australia Post: Damaged parcel](https://auspost.com.au/help/delivered-parcel/damaged-parcel) – damage assessment
- [Australia Post: Compensation](https://auspost.com.au/personal/receiving/parcel-deliveries/compensation) – $100, who is paid, 6 months, contact the seller first
- [Australia Post: Complaints and feedback](https://auspost.com.au/about-us/corporate-information/complaints-and-feedback) – phone numbers, StarTrack, Post Office help
- [Australia Post: Help & support](https://auspost.com.au/help) – forms, chat
- [Australia Post: Scam alerts](https://auspost.com.au/about-us/about-our-site/online-security-scams-fraud/scam-alerts) – fake texts, scams@auspost.com.au
- [Australia Post: About us](https://auspost.com.au/about-us) – 4,100 Post Offices
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
