---
title: J&T Express Australia tracking: JTAU numbers and statuses
description: Track a J&T Express Australia parcel: what a JTAU number looks like, what each tracking line means, and what to do when your parcel is late, lost or not delivered.
slug: jt-express-australia-tracking
published: 2026-10-21
updated: 2026-10-27
---

J&T Express Australia is J&T Express's Australian network: it delivers parcels in Australia, including orders from overseas once they've cleared customs. If a shop sent your order with J&T, the tracking number starts with `JTAU`: paste it in the box above, or look it up on J&T's own tracker at www.jtexpress.au/tracking. The shop that sent your order has the number, in its shipping email or on your order page. This page doesn't draw on J&T Express Australia's website: it rests on what Peek's code knows about J&T's tracker and on the Australian Business Register.

## J&T Express Australia tracking number: what it looks like

J&T Express Australia's waybill is `JTAU` followed by 12 digits, 16 characters in all. Peek knows no other shape for it. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `JTAU000000000000` | `JTAU` and 12 digits: a J&T Express Australia parcel. Peek names J&T Express Australia at once. |
| `JTAU00000000000` | One digit short. Not J&T Express Australia's shape, and Peek recognises no carrier: check for a missing digit. |
| `JTUK000000000000` | `JTUK` and 12 digits: J&T Express UK, not the Australian network. |
| `JTDE000000000000` | `JTDE` and 12 digits: [J&T Express Germany](carrier:j-and-t-de). |
| `JTE000000000000` | `JTE` and 12 digits: J&T Express Middle East, in Saudi Arabia, the UAE and Kuwait. |
| `JT0000000000000` | `JT` and 13 digits: J&T's networks in South-East Asia and in China (J&T Express China). |

J&T's networks in other countries have their own trackers and their own numbers: only `JTAU` belongs to the Australian network. If Peek keeps showing "Waiting for the carrier", check all 16 characters against the shop's email, then ask the shop. Spaces, dashes and lower case don't matter in Peek: `jtau 0000 0000 0000` reads the same as the first line. Peek's catalog also knows the carrier as J&T Express (AU), J&T Express AU, J&T Australia and JT Express Australia.

**Where to find it.** In the shop's shipping email, on the order page of the shop or marketplace, or in any message about the delivery. If you can't find it, see [how to find a tracking number](guide:find-tracking-number), or ask the shop.

## J&T Express Australia tracking statuses explained

J&T's tracker gives each scan as a time and one sentence, with no depot or town. Peek asks for the sentences in English. These are the ones Peek's code has seen on live J&T Express Australia parcels, in the order a parcel meets them:

| J&T's status | What it means for you | Peek shows |
| --- | --- | --- |
| `The parcel has been picked up from customs clearance.` | A parcel from overseas has cleared customs, and J&T has collected it from the customs broker. J&T's first scan of an imported parcel. | "Received by carrier" |
| `The parcel has been successfully picked up and is being prepared for transportation.` | J&T has the parcel and is getting it ready to move. | "Received by carrier" |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination.` | It has reached a J&T centre, ready for the next leg. | "In transit" |
| `The parcel is dispatched from the transit center to the next destination` | It has left a J&T centre for the next stop. | "In transit" |
| `Your parcel is out for delivery.` | It's on its way to you for delivery. | "Out for delivery" |
| `The building was closed when we arrived. We'll try again.` | The courier found the building closed, and J&T will try again. | "Delivery attempted" |
| `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.` | The delivery failed because the address looks wrong, and J&T asks for a corrected one. | "Delivery attempted" |
| `Your parcel has been delivered.` | Delivered. | "Delivered" |

`The parcel has been picked up from customs clearance.` sounds like a customs hold, but it means the opposite: customs is done and J&T has the parcel. More on parcels from abroad in [customs](guide:customs).

A sentence not in this table goes by the rules Peek uses for every carrier; one Peek doesn't recognise shows as J&T wrote it and counts as "In transit", unless the parcel is already out for delivery or past that point: then the status stays as it was.

On J&T's tracker, some sentences end with J&T's customer service number or an invitation to call it. Peek leaves that out. It also drops anything in brackets, phone numbers, and the name of whoever signed for, received or collected the parcel, or of a courier, driver, recipient or sender; it keeps the rest. Because the scans name no place, neither J&T's tracker nor Peek can tell you which state or depot the parcel is in.

## J&T Express Australia delivery: what the tracking tells you

On the live parcels behind Peek's code, J&T's courier takes the parcel out for delivery and then either delivers it or reports why the delivery failed. Peek's code shows no hand-over to another carrier: J&T's tracker follows the parcel to your door.

**Out for delivery.** `Your parcel is out for delivery.` means your parcel is on a delivery round. J&T's tracker gives no delivery window or expected day, so Peek can't show one either.

**Building closed.** `The building was closed when we arrived. We'll try again.` means J&T will bring the parcel another time. Where Peek's code saw it on a live parcel, it came right after the parcel went out for delivery, and the parcel then left the transit centre again. If the building is often closed during the day, ask the shop whether you can use another delivery address.

**Address problem.** After `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.`, check the address on your order. If anything is missing, such as a unit number or the postcode, tell the shop at once: it booked the delivery with the address you gave it. See [how to change a delivery address](guide:change-delivery-address) and [missed deliveries](guide:missed-delivery).

**Attempts, safe places and pickup points.** J&T's website can't be used for this page, so it doesn't say how many times J&T tries, whether it leaves parcels in a safe place or takes a photo, or how long it keeps a parcel it couldn't deliver. Among the J&T Express Australia lines Peek's code knows, none sends a parcel to a pickup point or locker. Ask the shop, or look at your delivery messages.

## J&T parcel not moving, missing or delivered but not received

The shop chose J&T, so for an online order the shop is your first contact.

**No new scans.** A parcel showing "Received by carrier" or "In transit" with no new line for four days gets a flag in Peek ("No tracking update for four days") and a "Get help from J&T Express Australia" link. If nothing moves after that, ask the shop. More on [tracking that isn't updating](guide:tracking-not-updating).

**No tracking yet.** A number J&T's tracker doesn't know yet shows as "Waiting for the carrier" in Peek. Shops often create the number before they hand the parcel over. If it stays that way for several days, check the number with the shop and ask whether the parcel has really been sent.

**Delivered but not there.** Check around your door, with your household and with neighbours. Still missing? Tell the shop, with your tracking number, and ask for a refund or a new parcel. See also [delivered but not received](guide:delivered-not-received).

**Lost or damaged.** Ask the shop or marketplace for a refund or replacement, with your tracking number. This page gives no J&T claim rules; see [lost parcels](guide:lost-parcel) and [damaged parcels](guide:damaged-parcel).

## J&T Express Australia customer service and contact

As of 2 November 2026, this page lists no phone number, email or chat for J&T Express Australia: Peek gives only contacts read on a carrier's own pages, and J&T's website can't be used for this page.

Start with the shop: it booked the delivery with J&T. For a marketplace order, use the help on the order's page. J&T's own tracker is at www.jtexpress.au/tracking. On a parcel that has stopped moving, Peek's "Get help from J&T Express Australia" link opens J&T's contact page.

If a text about a J&T parcel asks you to pay or to tap a link, check it against [delivery scam texts](guide:delivery-scam-texts) first: this page can't say what J&T sends.

## Tracking J&T Express Australia parcels with Peek

[Peek](/) names J&T Express Australia at once from a `JTAU` number ("Detected carrier"), typed with or without spaces, in capitals or not, and asks J&T's own tracker with the number alone: no postcode or account. A www.jtexpress.au/tracking link with `?q=` and the number works too; with another kind of number in it, Peek reads that number as if you had pasted it alone. If what you have isn't a `JTAU` number, paste it all the same: Peek follows more than 3,500 carriers. For a `JT` number with 13 digits, Peek shows "Checking tracking services…" while it checks the number. Picking J&T Express Australia with "Change" for another shape doesn't help: Peek says it "doesn’t issue numbers like this one".

Peek shows the status, each scan at its exact time and the delivery time. J&T gives every scan as an exact moment, so Peek shows it in your device's time zone. Scans come in J&T's English. J&T gives no place, expected delivery day or weight, so Peek shows no map. J&T's tracker can also show office contact details; Peek leaves them out. On a very long history it keeps the newest 100 scans. "Open the J&T Express Australia website", on the parcel's page, opens your number on J&T's tracker.

Until J&T scans the parcel, Peek shows "Waiting for the carrier" and keeps asking. When J&T's tracker can't be reached or doesn't know the number, Peek asks tracking services and shows their history, in their own words.

Peek checks every 10 minutes, and every 2 minutes once the parcel is out for delivery. After two days without a new scan (12 hours while it shows "Received by carrier"), it checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". Peek can't contact J&T or the shop for you, change the address or book a new delivery.

## Questions about J&T Express Australia tracking

### Is J&T Express Australia legit?

Yes. The Australian Business Register lists J&T EXPRESS AUSTRALIA PTY LTD as an Australian private company with an ABN (18 698 743 609) active since 4 June 2026 and its main business location in New South Wales. Its tracking numbers start with `JTAU`, and every sentence in the status table above was read on live J&T Express Australia parcels.

### What does "picked up from customs clearance" mean on J&T tracking?

Customs is done. The parcel came from overseas, cleared customs, and J&T collected it from the customs broker to deliver it. It's J&T's first scan of an imported parcel, and Peek shows it as "Received by carrier", not as a customs hold.

### Why is my J&T tracking number not found?

Check that it starts with `JTAU` and has 12 digits after it: numbers from J&T's other networks, such as `JTUK` in the UK, aren't Australian. If the number is right, J&T may not have the parcel yet. Peek shows "Waiting for the carrier" until the first scan; if that lasts several days, ask the shop.

### How do I contact J&T Express Australia?

Ask the shop first: it booked the delivery. This page gives no phone number or email, but on a parcel that has stopped moving, Peek's "Get help from J&T Express Australia" link opens J&T's contact page.

:::sources
- [Universal Parcel Scraper: J&T Express Australia](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – names, the JTAU shape, tracking links, the tracker's sentences read on live parcels and their stages, customs pickup, failed deliveries, English sentences, exact scan times, no place, expected day or weight, customer service lines, bracketed text, names and phone numbers left out, office contact details, newest 100 scans, help page
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – other J&T networks' shapes, no hand-over to another carrier, tracking services as a fallback
- [ABN Lookup: J&T Express Australia Pty Ltd](https://abr.business.gov.au/ABN/View?abn=18698743609) – active ABN since 4 June 2026, Australian private company, main business location in NSW
:::
