---
title: Paack tracking: order number, postcode and delivery slots
description: Track a Paack parcel: the number and postcode Paack's page asks for, what each status means, PaackGo Points, and how Paack is joining Colis Privé.
slug: paack-tracking
published: 2026-10-11
updated: 2026-10-27
---

Paack delivers online shops' orders. To track a parcel, enter the order number and the delivery postcode on Paack's tracking page, or paste the number in the box above and pick Paack. Paack checks the two as a pair: if either is wrong, it shows nothing.

## Paack tracking number: where to find it

Paack's tracking page asks for the order number, or the barcode number on the parcel's label, with the delivery postcode. Most of Paack's numbers are the shop's own order numbers or label barcodes, so they have no fixed shape: look in the shop's order and shipping messages, or on the label. No number? Ask the shop, or see [how to find your tracking number](guide:find-tracking-number).

With no fixed shape, an order number can look like another carrier's (examples invented):

| Order number | What it looks like |
| --- | --- |
| `123456789AB` | Letters and digits: no carrier in particular. |
| `12345678` | 8 digits: also Mondial Relay's, [Colisweb](carrier:colisweb)'s and Heppner's shape. |
| `123456789012` | 12 digits: also [FedEx](carrier:fedex)'s, MRW's, BRT's and others'. |

If the shop says Paack delivers your order, the number is Paack's, whatever its shape.

A wrong number or postcode gives `Order not found. Incorrect order number or postal code.`

## What Paack's tracking statuses mean

Paack's page sums up the journey in four steps (order details received, at Paack's distribution centre, out for delivery, delivered), and its history adds the steps in between. Peek shows each step in its own words:

| In Peek | What it means for you |
| --- | --- |
| `Shipment information received` | The shop has sent Paack your order's details. Paack doesn't have the parcel yet. |
| `Shipment collected` | Paack's first scan of the parcel, at its distribution centre. |
| `In transit` | Paack has it; it isn't out for delivery yet. |
| `Courier assigned` | A courier has been assigned to deliver it. It comes back before each new attempt. |
| `Out for delivery` | With the courier, in the slot shown. |
| `Delivery attempt failed`, `Delivery issue` | Not delivered this time: the courier couldn't deliver, or your PaackGo Point was closed, full or refused the parcel. |
| `New delivery attempt scheduled` | Paack tries again, in the new slot shown. |
| `Ready for pickup` | Waiting at the PaackGo Point; Paack's page gives your last day to collect it. |
| `Delivered` | Delivered, or collected at the PaackGo Point. |
| `Returning to sender`, `Returned to sender` | Going back to the shop, or back there: for instance not collected in time, refused or left unpaid at the PaackGo Point. |
| `Shipment exception` | A problem with the parcel, such as damage or loss at the PaackGo Point: contact the shop. |

## Paack delivery slots and PaackGo Points

**The slot.** Paack's page gives your expected delivery day and time slot, on the local time of the delivery address. After a failed attempt, the slot shown is the new attempt's.

**PaackGo Points** are Paack's pickup points. Once your parcel is there, Paack's page shows the point's name and address, your last day to collect it, a pickup code and a QR code. If you don't collect it in time, it goes back to the shop.

## Paack parcel late, not delivered or damaged

You bought from the shop, not from Paack, so refunds, returns and cancellations go through the shop:

- **Late or stuck:** see [why tracking stops updating](guide:tracking-not-updating), then ask the shop.
- **Returned to the shop, or no tracking number:** ask the shop.
- **Lost or damaged:** contact the shop.
- **Delivered but not there:** ask your household and neighbours, then the shop. More in [delivered but not received](guide:delivered-not-received).

## Tracking a Paack parcel with Peek

[Peek](/) names Paack straight away for `PK` and 18 digits, the number on parcels Paack delivers for Evri International, and tracks those without a postcode. For a label number of `5340` and 14 more digits, which turns up on Paack's parcels, "Change" lists Paack first under "Fits this number", ahead of other carriers that use 18 digits. For a SHEIN package number, `C` or `E`, a six-digit date (year, month, day) and 8 more digits, it lists Paack there too. Paack's other numbers have no shape of their own, and Peek can't ask Paack whether it knows one. Any other number with letters usually gets no carrier ("Peek looks it up when you track"); one made only of digits may be offered to another carrier with that shape. Press "Change" and pick Paack, or paste a link to Paack's tracking page with `tracking_number=`, from mydeliveries.paack.app or paack.co/shipments/tracking.

Then fill in "Delivery postcode". It's marked optional because a `PK` number needs none, but Paack shows other orders only with it, and a postcode inside a link isn't used. Paack answers only when both match, so a wrong pair looks like an unknown number. The postcode goes to Paack and, when needed, to tracking services. Peek writes the number in capitals and drops spaces, dots and hyphens: if nothing turns up, try Paack's page too, with the number exactly as written.

You see the status, the history and the expected day and slot, on the delivery address's clock (Canary Islands time for postcodes starting 35 or 38). At a PaackGo Point, Peek should show its name and address (not yet seen on a real parcel), never the pickup code, QR code or deadline: for those, open Paack's page. Steps appear in Peek's own words; a PaackGo pickup shows as "Delivered". Paack gives no place for its scans and names no other carrier, so Peek follows none. For a `PK` number, though, the history comes from Evri International's tracker, in its wording and with places. The shop's name and your name and address are dropped. Checks run every 10 minutes by day, every 2 once out for delivery, and every hour or two overnight, with notifications for every scan, important steps or delivery day only.

## Questions about Paack tracking

### What is Paack?

A last-mile delivery company working for online shops. Paack and Colis Privé came together in summer 2026 and will share the Paack brand, rolled out gradually from January 2027; together they're present in France, Belgium, Luxembourg, Spain and Portugal. Until then, Colis Privé says, delivery and tracking carry on as usual: track your parcel with the carrier your shop names.

### Why does Paack ask for my postcode?

Paack shows an order only to someone who has both its number and its delivery postcode, so the pair works like a password. If either is wrong, the page answers `Order not found` as if the number didn't exist. Use the postcode of the address the parcel is going to.

### What does it mean when Paack reschedules?

Paack will try again. After a failed attempt, Peek shows `New delivery attempt scheduled` and Paack's page gives the new day and slot. A delivery to a PaackGo Point is rescheduled when the point was closed, full or refused the parcel; Peek shows that as `Delivery issue`.

### How long does Paack take to deliver?

Paack's tracking page shows the day and time slot expected for your parcel, and Peek shows the same window. For the delivery times on offer, ask the shop.

:::sources
- [Colis Privé: Colis Privé and Paack unite under one brand: Paack](https://colisprive.fr/en/blog/colis-prive-and-paack-unite-under-one-brand-paack/) – 16 October 2026: together since summer 2026, one brand from January 2027, five countries, delivery and tracking as usual
- [Universal Parcel Scraper: Paack](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/paack) – order number or label barcode with the postcode, not-found message, number shapes, steps and the wording Peek stores, delivery slot, PaackGo Points
:::
