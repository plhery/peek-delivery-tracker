---
title: Swiss Post tracking: statuses, collection notes and contact
description: Track a Swiss Post parcel: what its numbers look like, what each English status means, what to do after a collection note, and how to reach Swiss Post.
slug: swiss-post-tracking
published: 2026-10-11
updated: 2026-10-27
---

Swiss Post (Die Post, La Poste, La Posta) is Switzerland's national postal service and delivers parcels across Switzerland and Liechtenstein. Track one free on [post.ch](https://www.post.ch/en/receiving-mail/track-consignments) with the consignment number, or paste it in the box above. The number comes from the sender, so look in the shop's shipping email first.

## Swiss Post tracking number format

The sender creates the number, usually before handing the parcel over. Swiss Post publishes no format list; these are the shapes Peek knows as its own:

| Number (invented) | What it is |
| --- | --- |
| `RR000000005CH` | Two letters, nine digits and `CH`: an international postal number issued in Switzerland. The last digit before `CH` is a check digit. |
| `99.00.000000.00000000` | 18 digits starting `98` or `99`, with or without dots or spaces: Swiss Post's domestic barcode number. |

Copy the number rather than typing it: one wrong, missing or extra digit makes it look like another carrier's. 18 digits starting `44` belong to [Quickpac](carrier:quickpac). Pallets, furniture and other freight come with [Swiss Post Cargo](carrier:swiss-post-cargo), Swiss Post's freight brand, which has its own tracker. A parcel from abroad keeps its foreign number (ending `DE`, `FR` and so on) in Switzerland too: a [Delivengo](carrier:delivengo) parcel from a French shop, for one, keeps its La Poste number ending `FR`. No number at all? See [how to find your tracking number](guide:find-tracking-number).

## Swiss Post tracking statuses explained

post.ch prints each scan in the language you view it in (English, German, French or Italian). These are the English texts, in the order a parcel usually meets them:

| Swiss Post says | What it means for you |
| --- | --- |
| `Your shipment will shortly be handed over to the Swiss Post` | The sender has announced the parcel. Swiss Post doesn't have it yet. |
| `Time at which your consignment was mailed` | Handed in, with the post office it was mailed from. |
| `Customs clearance process underway` | A parcel from abroad is at Swiss postal customs. |
| `Completion of customs clearance process` | Cleared and handed over for delivery in Switzerland. |
| `Sorting - forwarding` | Sorted at one centre and sent on to the next. |
| `Arrival at the delivery point` | At the office that delivers to you. |
| `Loading into delivery vehicle` | Out for delivery. |
| `Delivered` | Delivered. |
| `Delivered to the mailbox/letter box` | Left in your letter box or its parcel compartment. |
| `Delivery failed: Recipient absent` | Nobody was in to receive it. |
| `Registered for collection` | A collection note was left: the parcel waits at a branch for you. |
| `Deposited in the MyPost24 machine` | Waiting in a My Post 24 terminal for you to pick up. |

`Registered for collection` sounds like paperwork, but it means a missed delivery and a collection note. `Consignment recorded by the foreign sender (data delivered)` only means a sender abroad sent the data: nothing was delivered. `Returned item`, with a reason such as `Reason: Not collected`, means it is going back to the sender.

## Swiss Post delivery failed: collection notes and pickup

The day before delivery, Swiss Post sends a notice by SMS, email or Post-App push. With "My consignments" you generally get a 40 to 90 minute time slot.

**Missed it?** You get a collection note. Swiss Post says:

- You can pick the parcel up from the next day, at the place printed on the note, with the note or the QR code in the Post-App.
- Parcels usually wait seven days, or 15 for international consignments. Extending the deadline is free and needs no account.
- With a "My consignments" login, a free one-off authorisation lets someone else pick it up, and you can book a second delivery: left in a safe place or with a neighbour free, Saturday CHF 7. Forwarding to a My Post 24 terminal, a branch or another address in Switzerland or Liechtenstein costs CHF 8 on weekdays.

**Before delivery**, "My consignments" lets you redirect a parcel free, choose another weekday free (Saturday CHF 3.80) or name a safe place. You have until midnight to change the time and place; Swiss Post can't guarantee later changes.

**Pickup points.** Over 2,700 PickPost points and over 320 My Post 24 terminals, open around the clock, both free.

## Swiss Post parcel late, lost or damaged

**No update.** At customs, Swiss Post says processing may take several days. Otherwise see [why tracking stops updating](guide:tracking-not-updating).

**Lost.** The sender or the recipient can lodge an enquiry once the delivered event is missing and the published delivery time is more than two days past, or the recipient confirms it never arrived. Swiss Post's deadlines: up to a year after mailing in Switzerland, six months for international items. For international parcels its help pages put the sender, usually the shop, in charge of the enquiry.

**Damaged.** Report it within eight days of delivery, online, in the Post-App under "Receive" > "Report damage", or at a branch with the parcel and its packaging.

**Import charges.** For parcels from abroad you pay VAT and duty plus Swiss Post's clearance fee, payable online in the Post-App. The fees change: check [Swiss Post's import page](https://www.post.ch/en/receiving-mail/import-customs-clearance) and Peek's [customs guide](guide:customs).

## Swiss Post contact number, chat and WhatsApp

As listed on post.ch on 11 October 2026:

- **Phone:** +41 848 888 888, CHF 0.08 per minute from a Swiss landline, Monday to Friday 7.30 am to 6 pm, Saturday 8 am to noon.
- **Chat:** a chatbot with specialists behind it, linked from the German, French and Italian contact pages.
- **WhatsApp:** listed for changing delivery orders, along with phone and chat.
- **Enquiry forms:** "Start a domestic enquiry" and "Start an international enquiry" on the [help page](https://www.post.ch/en/help-and-contact/receiving-mail).
- **In person:** for delivery orders, at a branch with valid ID.

> Swiss Post warns of fake parcel messages, such as a parcel "withheld" or a small customs or postage fee to pay through a link. It never asks for passwords, security codes or full card details by SMS or email. Check parcels only on swisspost.ch or in the official Post-App.

## Tracking Swiss Post parcels with Peek

[Peek](/) recognises both shapes at once, with dots or spaces. A service.post.ch link ending in `/entry/search/` and the number works too; other post.ch links only when one of those numbers is inside. No postcode needed.

Peek reads Swiss Post's English texts, whatever your language. It shows the status, each scan's facility on a map, the expected delivery day (with a time window when Swiss Post gives one), the weight, size, delivery time and destination country. While the parcel waits, it shows the branch or My Post 24 terminal's name and address, gone once delivered. It leaves out your name, address, signature and delivery instructions, and doesn't read the pickup deadline.

`Delivered` shows as Peek's own "Delivered". `Registered for collection` and `Deposited in the MyPost24 machine` show as ready for pickup.

Peek checks every 10 minutes by day, every 2 once out for delivery, and every hour or two overnight, and can notify you of every scan, important steps only or delivery day only. [GLS Switzerland](carrier:gls-ch) parcels and foreign postal numbers heading to Switzerland can be followed to Swiss Post; a `CH` number going abroad, to the destination's national post when Peek knows it (Germany, France or Italy, for example) once it shows newer progress.

## Questions about Swiss Post tracking

### Does Swiss Post deliver on Saturdays?

Yes, if you book it in "My consignments": CHF 3.80 chosen in advance, or CHF 7 as a second delivery after a missed one.

### What does "delivery date unknown" mean at Swiss Post?

Swiss Post doesn't know the delivery date yet, because the parcel comes from abroad, goes abroad or hasn't been handed over as announced.

### What is a Swiss Post delivery authorisation?

There are two: a free deposit order telling Swiss Post where to leave parcels safely, and a free one-off authorisation letting someone else pick up a specific parcel. Both need a "My consignments" login.

### Where is my nearest Swiss Post parcel locker?

Swiss Post's lockers are My Post 24 terminals, over 320 of them, open around the clock. Find them with swisspost.ch/locations.

:::sources
- [Swiss Post: Track consignments](https://www.post.ch/en/receiving-mail/track-consignments) – search field, notifications, time slot, abroad not guaranteed
- [Swiss Post: Track consignments events](https://www.post.ch/en/receiving-mail/track-consignments/track-consignments-events) – status meanings, delivery date unknown
- [Swiss Post: Tracking wordings (en)](https://service.post.ch/ekp-web/core/rest/translations/en/shipment-text-messages) – exact scan texts
- [Swiss Post: Collection note](https://www.post.ch/en/receiving-mail/collection-note) – deadlines, authorisation, second delivery prices
- [Swiss Post: Manage and receive parcels](https://www.post.ch/en/receiving-mail/manage-consignment/manage-one-off) – redirection, safe place, Saturday, midnight cut-off
- [Swiss Post: Questions and answers on receiving](https://www.post.ch/en/help-and-contact/receiving-mail) – day-before notice, next-day pickup, WhatsApp, enquiry forms
- [Swiss Post: Damage or loss](https://www.post.ch/en/sending-parcels/damage-or-loss) – enquiries, deadlines, damage reports
- [Swiss Post: Consignments from abroad](https://www.post.ch/en/receiving-mail/import-customs-clearance) – import charges
- [Swiss Post: Help and contact](https://www.post.ch/en/help-and-contact) – phone, hours, address
- [Swiss Post: Hilfe und Kontakt](https://www.post.ch/de/hilfe-und-kontakt) – chat
- [Swiss Post: Recognize phishing and fraud](https://site.post.ch/en/about-us/security/phishing-and-fraud) – fake messages
- [Swiss Post: Receipt locations](https://www.post.ch/en/receiving-mail/receipt-locations) – PickPost, My Post 24
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
