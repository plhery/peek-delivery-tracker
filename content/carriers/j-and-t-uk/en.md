---
title: J&T Express UK tracking: JTUK numbers and statuses
description: Track a J&T Express UK parcel: what a JTUK number looks like, what each tracking line means, and what to do when your parcel is late, lost or not delivered.
slug: jt-express-uk-tracking
published: 2026-11-03
updated: 2026-11-03
---

J&T Express UK delivers parcels in the United Kingdom. If a shop sent your order with J&T, the tracking number starts with `JTUK`: paste it in the box above, or look it up on J&T's own tracker at www.jtexpress.uk/tracking. The shop that sent your order has the number, in its shipping email or on your order page. Peek couldn't read J&T Express UK's website, so this page rests on what Peek's code knows about J&T's tracker and on official UK sources.

## J&T Express UK tracking number: what it looks like

J&T Express UK's waybill is `JTUK` followed by 12 digits, 16 characters in all. Peek knows no other shape for it. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `JTUK000000000000` | `JTUK` and 12 digits: a J&T Express UK parcel. Peek names J&T Express UK at once. |
| `JTUK00000000000` | One digit short. Not J&T Express UK's shape, and Peek recognises no carrier: check for a missing digit. |
| `JTDE000000000000` | `JTDE` and 12 digits: [J&T Express Germany](carrier:j-and-t-de), not the UK network. |
| `JTAU000000000000` | `JTAU` and 12 digits: [J&T Express Australia](carrier:j-and-t-au). |
| `JTE000000000000` | `JTE` and 12 digits: [J&T Express Middle East](carrier:j-and-t-middle-east). |
| `JT0000000000000` | `JT` and 13 digits: J&T's networks in South-East Asia and in China ([J&T Express China](carrier:j-and-t-cn)). |

Spaces, dashes and lower case don't matter in Peek: `jtuk 0000 0000 0000` reads the same as the first line. Peek's catalog also knows the carrier as J&T Express (UK), J&T Express UK Ltd and J&T UK.

**Where to find it.** In the shop's shipping email, on the order page of the shop or marketplace, or in any message about the delivery. If you can't find it, see [how to find your tracking number](guide:find-tracking-number), or ask the shop.

## J&T Express UK tracking statuses explained

J&T's tracker shows each scan as a time and one sentence, with no place. These are the sentences Peek's code has seen on live J&T Express UK parcels, in the order a parcel meets them:

| J&T says | What it means for you | Peek shows |
| --- | --- | --- |
| `The parcel has been successfully picked up and is being prepared for transportation` | J&T has collected the parcel from the sender and is getting it ready to move. | "Received by carrier" |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination` | It has reached a J&T sorting centre. | "In transit" |
| `The parcel is dispatched from the transit center to the next destination` | It has left that centre for the next stop. | "In transit" |
| `The parcel has been collected and is on its way` | The courier has taken your parcel for the delivery round. Despite the words, this isn't the first pickup: J&T files it as a delivery scan. | "Out for delivery" |
| `The parcel has been successfully delivered to the recipient` | Delivered. | "Delivered" |
| `The delivery address is incomplete or incorrect.` | The round failed: the courier couldn't deliver to the address as it was written. In the history Peek's code holds, it came hours after the parcel went out, the same day. | "Delivery attempted" |

A sentence not in this table goes by the rules Peek uses for every carrier; one Peek doesn't recognise shows as J&T wrote it and counts as "In transit", unless the parcel is already out for delivery or past that point: then the status stays as it was.

If a sentence ends with a customer service number or an invitation to call it, Peek leaves that out. It also drops anything in brackets, phone numbers, and the name of whoever signed for, received or collected the parcel, or of a courier, driver, recipient or sender; it keeps the rest.

## J&T Express UK delivery: what the tracking tells you

In the histories Peek's code holds, J&T's courier takes the parcel out and either delivers it or reports a problem with the address. Peek's code shows no hand-over to another carrier: J&T's tracker follows the parcel to your door.

**Out for delivery.** `The parcel has been collected and is on its way` means your parcel is on today's round. J&T's tracker gives no delivery window or expected day, so Peek can't show one either.

**Address problem.** After `The delivery address is incomplete or incorrect.`, check the address on your order. If anything is missing, such as a flat number or the postcode, tell the shop at once: it booked the delivery with the address you gave it. See [how to change a delivery address](guide:change-delivery-address) and [missed deliveries](guide:missed-delivery).

**Attempts, safe places and pickup points.** Peek couldn't read J&T Express UK's website, so this page doesn't say how many times J&T tries, whether it leaves parcels with neighbours or in a safe place, or how long it keeps a parcel it couldn't deliver. Ask the shop, or look at your delivery messages.

**Orders from outside the UK.** If what you bought came from abroad, GOV.UK says the company delivering it contacts you if any VAT, duty or delivery charges are due, with a bill for exactly what to pay. It normally holds the parcel for about 3 weeks, then sends it back if the bill isn't paid. For goods worth £135 or less that you bought yourself, the seller has already included VAT in the price, except for excise goods such as alcohol, tobacco or vapes. Above £135, you pay the VAT to the delivery company before delivery or when you collect, plus any Customs Duty, which depends on the goods and where they came from. Northern Ireland has its own rules. Peek couldn't check how J&T bills import charges. More in [parcels held at customs](guide:customs).

## J&T Express UK parcel late, lost or returned

The shop chose J&T, so for an online order the shop is your first contact. Under the Consumer Rights Act 2015, goods you buy from a business stay at the seller's risk until they're in your hands, or those of someone you named to take them. That changes only if you hired the carrier yourself and the seller hadn't offered it.

**Not moving.** A parcel showing "Received by carrier" or "In transit" with no new line for four days gets a flag in Peek ("No tracking update for four days"). If nothing moves after that, ask the shop. See [why tracking stops updating](guide:tracking-not-updating).

**No tracking yet.** A number J&T's tracker doesn't know yet shows as "Waiting for the carrier" in Peek. Shops often create the number before they hand the parcel over. If it stays that way for several days, check the number with the shop and ask whether the parcel has really been sent.

**Delivered, but nothing came.** Check around your door, with your household and with neighbours. Then tell the shop: under that law the parcel stays at its risk until you, or someone you named, have it, so ask it for a refund or a new parcel. More in [delivered but not received](guide:delivered-not-received).

**Lost or damaged.** Ask the shop for a refund or a replacement, with your tracking number. Peek couldn't read J&T's rules for claims; see [lost parcels](guide:lost-parcel) and [damaged parcels](guide:damaged-parcel).

**Sent back.** If the parcel goes back to the shop after a failed delivery, the shop decides on a refund or a new delivery. See [returned to sender](guide:returned-to-sender).

## J&T Express UK customer service and contact

As of 2 November 2026, Peek couldn't read J&T Express UK's website, so this page lists no phone number, email or chat: Peek gives only contacts read on a carrier's own pages.

Start with the shop: it booked the delivery with J&T. For a marketplace order, use the help on the order's page. J&T's own tracker is at www.jtexpress.uk/tracking.

## Track a J&T Express UK parcel with Peek

[Peek](/) names J&T Express UK at once from a `JTUK` number ("Detected carrier") and asks J&T's own tracker with the number alone: no postcode or account. A www.jtexpress.uk/tracking link with `?q=` and the number works too; with another kind of number in it, Peek reads that number as if you had pasted it alone. If what you have isn't a `JTUK` number, paste it all the same: Peek follows more than 3,500 carriers. For a `JT` number with 13 digits, Peek shows "Checking tracking services…" while it checks the number. Picking J&T Express UK with "Change" for another shape doesn't help: Peek says it "doesn’t issue numbers like this one".

Peek shows the status, each scan at its exact time and the delivery time. J&T gives every scan as an exact moment, so Peek shows them in your device's time zone, in summer as in winter. Scans come in J&T's English. J&T gives no place, expected delivery day or weight, so Peek shows no map. J&T's tracker can show proof of delivery; Peek leaves it out. On a very long history it keeps the newest 100 scans. "Open the J&T Express UK website", on the parcel's page, opens your number on J&T's tracker.

Until J&T scans the parcel, Peek shows "Waiting for the carrier" and keeps asking. When J&T's tracker can't be reached or doesn't know the number, Peek asks tracking services and shows their history, in their own words.

Peek checks every 10 minutes, and every 2 minutes from 6 a.m. local time on the day J&T scans the parcel out for delivery, while J&T's own tracker answers (through tracking services, every 15 minutes). After two days without a new scan (12 hours while it shows "Received by carrier"), it checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". Peek can't contact J&T or the shop for you, change the address or book a new delivery.

## Questions about J&T Express UK tracking

### Is J&T Express UK legit?

Yes. Companies House lists J&T EXPRESS UK LTD as an active private limited company, incorporated on 18 December 2025; the nature of business it filed includes road freight, warehousing and postal activities. Its tracking numbers start with `JTUK`, and Peek reads its tracker for them.

### What does "The parcel has been collected and is on its way" mean on J&T?

Your parcel is out for delivery. The courier has collected it for the delivery round; J&T files this line as a delivery scan, and Peek shows "Out for delivery". It doesn't mean the parcel has just left the sender.

### Why does J&T say my delivery address is incomplete or incorrect?

The courier couldn't deliver because the address was missing something or didn't match. Check the address on your order and tell the shop straight away, with the full address and your tracking number.

### Why is my J&T tracking number not found?

Check that it starts with `JTUK` and has 12 digits after it: numbers from J&T's other networks, such as `JTDE` in Germany, aren't the UK's. If the number is right, J&T may not have the parcel yet. Peek shows "Waiting for the carrier" until the first scan; if that lasts several days, ask the shop.

### How do I contact J&T Express UK?

Ask the shop first: it booked the delivery. Peek couldn't read J&T Express UK's website, so this page gives no phone number or email.

:::sources
- [Universal Parcel Scraper: J&T Express UK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-uk) – names, the JTUK shape, tracking links, the tracker's sentences and their stages, the delivery round and address scans, exact scan times, no place, expected day or weight, proof of delivery left out, newest 100 scans
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – other J&T networks' shapes, customer service lines, bracketed text, names and phone numbers left out (J&T's shared lookup), no hand-over to another carrier, tracking services as a fallback
- [Companies House: J&T EXPRESS UK LTD](https://find-and-update.company-information.service.gov.uk/company/16920174) – active private limited company, incorporated 18 December 2025, nature of business
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – bill from the delivery company, held about 3 weeks, VAT at £135 or less and above, Customs Duty, Northern Ireland
- [UK legislation: Consumer Rights Act 2015, section 29](https://www.legislation.gov.uk/ukpga/2015/15/section/29) – goods at the trader's risk until in the consumer's physical possession
:::
