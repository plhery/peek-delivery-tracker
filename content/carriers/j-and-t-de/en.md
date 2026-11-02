---
title: J&T Express Germany tracking: JTDE numbers and statuses
description: Track a J&T Express parcel in Germany: what a JTDE number looks like, what each tracking line means, and what to do when your parcel is late, lost or missing.
slug: jt-express-germany-tracking
published: 2026-10-21
updated: 2026-10-27
---

J&T Express Germany is J&T Express's German network: it delivers parcels in Germany. If a shop sent your order with J&T, the tracking number starts with `JTDE`: paste it in the box above, or look it up on J&T's own tracker at www.jtexpress.de/tracking. The shop that sent your order has the number, in its shipping email or on your order page. This page doesn't draw on J&T Express Germany's website: it rests on what Peek's code knows about J&T's tracker and on German law.

## J&T Express Germany tracking number: what it looks like

J&T Express Germany's waybill is `JTDE` followed by 12 digits, 16 characters in all. Peek knows no other shape for it. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `JTDE000000000000` | `JTDE` and 12 digits: a J&T Express Germany parcel. Peek names J&T Express Germany at once. |
| `JTDE00000000000` | One digit short: not J&T Express Germany's shape, and Peek recognises no carrier. Check for a missing digit. |

**Where to find it.** In the shop's shipping email, on the order page of the shop or marketplace, or in any message about the delivery. If you can't find it, see [how to find a tracking number](guide:find-tracking-number), or ask the shop.

**Numbers that look alike.** J&T's networks in other countries have their own trackers and numbers: `JTUK` and 12 digits is [J&T Express UK](carrier:j-and-t-uk), `JTAU` and 12 digits [J&T Express Australia](carrier:j-and-t-au), `JTE` and 12 digits [J&T Express Middle East](carrier:j-and-t-middle-east), and `JT` with 13 digits is used by J&T in South-East Asia and in China ([J&T Express China](carrier:j-and-t-cn)). Only `JTDE` belongs to the German network. If Peek keeps showing "Waiting for the carrier", compare the number with the shop's email, or ask the shop.

## J&T Express Germany tracking statuses explained

J&T's tracker gives each scan as a time and one sentence, with no place. Peek asks for the sentences in English, so these are the ones Peek shows, in the order a parcel meets them:

| J&T's status | What it means for you | Peek shows |
| --- | --- | --- |
| `The shipment has been successfully picked up and is being prepared for transportation` | J&T has collected the parcel from the sender. | "Received by carrier" |
| `The shipment arrives at the distribution center and is prepared for transport to the next destination.` | It reached a J&T sorting centre. | "In transit" |
| `The shipment is dispatched from the transit center to the next destination` | It left that centre for the next stop. | "In transit" |
| `Your package has been loaded and is on its way to you` | It's in the delivery van: out for delivery. | "Out for delivery" |
| `Delivery failed because the address could not be found` | The driver couldn't find your address. | "Delivery attempted" |
| `Delivery failed, customer refused to accept` | The recipient refused to accept the parcel. | "Delivery attempted" |
| `Your package has been successfully delivered.` | Delivered. J&T adds how (see below). | "Delivered" |
| `Returns have begun` | After a failed delivery, the parcel is on its way back to the sender. | "Returned to sender" |
| `The shipment has been returned` | The return is complete. | "Returned to sender" |

A sentence not in this table goes by the rules Peek uses for every carrier; one Peek doesn't recognise shows as J&T wrote it and counts as "In transit", unless the parcel is already out for delivery or past that point: then the status stays as it was.

The loading line can appear several times: J&T writes it once for each delivery run. The delivered line goes on with the delivery type in 【】 brackets and ends with a customer service line. Peek drops that line, and keeps the type, without brackets, only when it is one of these six:

- `Left with neighbor`: with a neighbour;
- `Front Door Porch`: at the front door;
- `Front Desk Reception`: at a reception desk;
- `Garden Yard`: in the garden or yard;
- `Mailbox`: in the mailbox;
- `Garage`: in the garage.

So a parcel left with a neighbour reads `Your package has been successfully delivered. The delivery type is Left with neighbor.` in Peek.

**Which neighbour?** Peek shows only that the parcel was left with a neighbour: no name and no address. It leaves out any name or phone number a J&T line carries.

## J&T Express Germany delivery: what the tracking tells you

In the histories Peek's code holds, J&T's courier loads the parcel for a delivery run and then either delivers it or reports why the delivery failed. Peek's code shows no hand-over to another carrier: J&T's tracker follows the parcel to your door.

**Out for delivery.** `Your package has been loaded and is on its way to you` means your parcel is on a delivery run. In those histories it comes hours before the delivery, the same morning. J&T's tracker gives no delivery window or expected day, so Peek can't show one either.

**Refused or not found.** After `Delivery failed, customer refused to accept`, the courier loaded the parcel for another run the next morning in the histories Peek's code holds. After `Delivery failed because the address could not be found`, check the address on your order. If anything is missing, such as a flat number or the name on your bell, tell the shop at once: it booked the delivery with the address you gave it. `Returns have begun` can follow a failed delivery. See [how to change a delivery address](guide:change-delivery-address) and [missed deliveries](guide:missed-delivery).

**Attempts, safe places and pickup.** J&T's website can't be used for this page, so it doesn't say how many times J&T tries, whether you can choose where a parcel is left, or how long J&T keeps a parcel it couldn't deliver. Among the J&T Express Germany lines Peek's code knows, none sends a parcel to a shop or locker for collection. Ask the shop, or look at your delivery messages.

## J&T Express parcel late, missing or returned

The shop chose J&T, so for an online order the shop is your first contact. Under German law, when you buy from a business as a consumer, the seller carries the risk of loss or damage in transit unless you hired the carrier yourself and the seller hadn't named that carrier to you beforehand.

**Not moving.** A parcel showing "Received by carrier" or "In transit" with no new line for four days gets a flag in Peek ("No tracking update for four days") and a "Get help from J&T Express Germany" link. If nothing moves after that, ask the shop. More on [tracking that isn't updating](guide:tracking-not-updating).

**No tracking yet.** A number J&T's tracker doesn't know yet shows as "Waiting for the carrier" in Peek. Shops often create the number before they hand the parcel over. If it stays that way for several days, check the number with the shop and ask whether the parcel has really been sent.

**Delivered but not received.** Read the delivered line in Peek: if it names a place, such as `Mailbox` or `Garage`, look there; if it says `Left with neighbor`, ask your neighbours. Still missing? Tell the shop, with your tracking number, and ask for a refund or a new parcel. See also [delivered but not received](guide:delivered-not-received).

**Lost, damaged or returned.** Ask the shop for a refund or replacement, with your tracking number. This page gives no J&T claim rules; see [lost parcels](guide:lost-parcel), [damaged parcels](guide:damaged-parcel) and [returned to sender](guide:returned-to-sender).

## J&T Express Germany customer service and contact

As of 2 November 2026, this page lists no phone number, email or chat for J&T Express Germany: Peek gives only contacts read on a carrier's own pages, and J&T's website can't be used for this page.

Start with the shop: it booked the delivery with J&T. For a marketplace order, use the help on the order's page. J&T's own tracker is at www.jtexpress.de/tracking. On a parcel that has stopped moving, Peek's "Get help from J&T Express Germany" link opens J&T's contact page.

## Tracking J&T Express Germany parcels with Peek

[Peek](/) names J&T Express Germany at once from a `JTDE` number ("Detected carrier"), typed with or without spaces, in capitals or not, and asks J&T's own tracker with the number alone: no postcode or account. A www.jtexpress.de/tracking link with `?q=` and the number works too; with another kind of number in it, Peek reads that number as if you had pasted it alone. If what you have isn't a `JTDE` number, paste it all the same: Peek follows more than 3,500 carriers. Picking J&T Express Germany with "Change" for another shape doesn't help: Peek says it "doesn’t issue numbers like this one".

Peek shows the status, each scan at its exact time and the delivery time. J&T gives every scan as an exact moment, so Peek shows it in your device's time zone. Scans come in J&T's English. J&T gives no place, expected delivery day or weight, so Peek shows no map. On a very long history it keeps the newest 100 scans. "Open the J&T Express Germany website", on the parcel's page, opens your number on J&T's tracker.

Until J&T scans the parcel, Peek shows "Waiting for the carrier" and keeps asking. When J&T's tracker can't be reached or doesn't know the number, Peek asks tracking services and shows their history, in their own words.

Peek checks every 10 minutes, and every 2 minutes once the parcel is out for delivery. After two days without a new scan (12 hours while it shows "Received by carrier"), it checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". Peek can't contact J&T or the shop for you, change the address or book a new delivery.

## Questions about J&T Express Germany tracking

### Is J&T Express Germany legit?

It's a real parcel carrier: J&T Express's German network, which Peek's catalog also lists as J&T Express Germany GmbH. Its tracker answers for `JTDE` numbers, and every sentence in the status table above was read on live J&T Express Germany parcels. J&T's website can't be used for this page, so it gives no company details.

### Which neighbour has my J&T parcel?

Peek can't tell you. When the delivered line says `Left with neighbor`, Peek shows that, with no name or address. Ask your neighbours; if nobody has it, tell the shop.

### Does J&T Express have parcel shops in Germany?

None that Peek's code knows. The delivery types Peek shows on the delivered line are with a neighbour, at the front door, at reception, in the garden, in the mailbox or in the garage. No J&T Express Germany line Peek knows sends a parcel to a shop or locker.

### Why is my J&T tracking number not found?

Check that it starts with `JTDE` and has 12 digits after it: numbers from J&T's other networks, such as `JTUK` in the UK, aren't German. If the number is right, J&T may not have the parcel yet. Peek shows "Waiting for the carrier" until the first scan; if that lasts several days, ask the shop.

### How do I contact J&T Express in Germany?

Ask the shop first: it booked the delivery. This page gives no phone number or email, but on a parcel that has stopped moving, Peek's "Get help from J&T Express Germany" link opens J&T's contact page.

:::sources
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – names, the JTDE shape, tracking links, the tracker's sentences read on live parcels and their stages, delivery runs, delivery types, what is left out, help page
- [Universal Parcel Scraper: J&T's shared tracker lookup](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – English sentences, exact scan times, no place, expected day or weight, names and phone numbers left out, newest 100 scans
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – other J&T networks' shapes, no hand-over to another carrier, tracking services as a fallback
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – the seller's risk in transit
:::
