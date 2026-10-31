---
title: GOFO Express tracking: statuses, photos and support
description: Track a GOFO Express parcel in the US: what a GOFO tracking number looks like, what the statuses mean, missed deliveries, delivery photos and how to reach support.
slug: gofo-express-tracking
published: 2026-10-15
updated: 2026-10-27
---

GOFO Express is a US parcel carrier: it collects parcels from the sender's warehouse and moves them through its hubs to the delivery station that serves your area. Paste the number in the box above to track yours; GOFO's own site has a tracker too. The number comes from the shop that shipped your order, usually in its shipping email or on your order page. Nothing there? See [how to find a tracking number](guide:find-tracking-number).

## GOFO tracking number: what it looks like

GOFO's US numbers come in three shapes. The ones Peek links to GOFO, with invented digits:

| Shape (invented) | What it is |
| --- | --- |
| `GFUS12345678901234` | GFUS followed by 14 digits: GOFO's US waybill number |
| `GF1234567890123` | GF followed by 13 digits: an older series GOFO still tracks |
| `CR123456789012` | CR followed by 12 digits: another older series |

Some parcels carry both a GFUS waybill number and a CR tracking number, and either one finds the same parcel.

**Numbers that look alike.** GOFO runs networks in other countries, each with its own numbers: a `GFCA` number with 14 digits belongs to [GOFO Canada](carrier:gofo-ca), `GFFR` and `CIFR` numbers to [GOFO France](carrier:gofo-fr), `GFIT` and `CIIT` numbers to [GOFO Italy](carrier:gofo-it), `GFES` and `CIES` numbers to GOFO Spain, `GFNL` and `CINL` numbers to GOFO Netherlands. Peek recognises them at once and tracks them with that country's network. A `YT` number is [YunExpress](carrier:yunexpress)'s, even when GOFO does the final delivery: GOFO's US tracker takes the same number, and YunExpress's tracking shows GOFO's scans too.

## GOFO tracking statuses explained

GOFO's scans are in English. These are the ones a parcel may meet, roughly in order; not every parcel meets them all:

| GOFO's status | What it means for you |
| --- | --- |
| `Shipping Label Created` | The sender made the label. GOFO doesn't have the parcel yet. |
| `Pick Up Start` | GOFO has set out to collect the parcel from the sender; it doesn't have it yet. |
| `Pickup Exception, GOFO has not Received the Package from the Warehouse yet.` | GOFO hasn't received the parcel from the sender's warehouse yet. |
| `Pick Up Success` | GOFO collected the parcel from the sender. |
| `Arrived at GOFO Regional Origin Hub` | It reached GOFO's first hub. |
| `Arrived at Sorting Center` | It reached a sorting center, before the regional hub. |
| `Arrived at GOFO Regional Hub` | It reached a hub on the way to you. |
| `Processing at GOFO Facility` | Being sorted at a GOFO building. |
| `Departed GOFO Regional Hub` | It left that hub. |
| `Loaded on Vehicle` | Loaded onto a vehicle for the next leg. |
| `In Transit to Next Facility` | Moving between GOFO buildings. |
| `Arrived at GOFO Delivery Station` | At the local station that delivers your area. |
| `Departed GOFO Delivery Station` | It left the station. |
| `Out for Delivery` | With the driver today. |
| `Delivered` | The driver delivered the parcel. |
| `Delivery Exception, Business Closed` | The driver couldn't deliver because the business was closed. |
| `Label Cancelled` | The label was cancelled: no parcel is coming under this number. |

GOFO's delivered scan can add a detail after the comma, and its tracking page also shows an estimated delivery date.

## GOFO delivery: photos and missed deliveries

**Delivery photos.** GOFO's tracking page can show photos of the delivery. Peek leaves them out, so open GOFO's page to see them.

**Missed delivery.** `Delivery Exception` means the driver tried and couldn't deliver. The reason follows the comma and varies; `Business Closed` is one of them. Peek then shows the parcel as "Delivery attempted" and quotes GOFO's line under "What GOFO Express says". If the address was wrong, tell the shop that shipped it.

## GOFO package delayed, not received or not ordered

**Not moving.** If only `Shipping Label Created`, `Pick Up Start` or `Pickup Exception…` shows, GOFO doesn't have the parcel yet: ask the shop whether it has shipped. If four days pass without a new scan before the parcel is out for delivery, Peek shows a note with a "Get help from GOFO Express" link to GOFO's customer service page. More on [tracking that isn't updating](guide:tracking-not-updating).

**Your rights when an order is late.** Under the FTC's Mail, Internet, or Telephone Order Merchandise Rule, a seller has to ship within the time it states, or within 30 days of your order if it states none. If shipping is delayed, the seller has to tell you and let you choose between agreeing to the delay and cancelling for a full refund. If it doesn't ship, it owes you a full refund, not just a gift card or store credit. The FTC's first step: contact the seller.

**Delivered but not received.** Look at the delivery photos on GOFO's tracking page, then contact the shop, and see [delivered but not received](guide:delivered-not-received). If you never got the order and the company won't reverse the charge, the FTC says to dispute it with your card issuer. For a credit card, the dispute has to be in writing, within 60 days after the first statement with the charge was sent to you.

**A parcel sent to you that you didn't order.** By US law you don't have to pay for merchandise you didn't order, or send it back: the FTC says you can keep it as a free gift.

**Lost.** Ask the shop for a refund or a new shipment; more in [lost parcel](guide:lost-parcel).

## GOFO Express customer service phone number

For anything about the order itself, start with the shop that shipped it. GOFO's own site has a customer service page, the one Peek's "Get help from GOFO Express" link opens. On October 15, 2026, that page listed this WhatsApp line:

- **WhatsApp:** +1 (787) 469-5811, 9 am to 9 pm EST Monday to Friday, 11 am to 8 pm EST Saturday and Sunday.

## Tracking GOFO Express parcels with Peek

For a `GFUS`, `GF` or `CR` number, typed with or without spaces, [Peek](/) asks GOFO whether it has the parcel and names GOFO Express when it does. `GFUS` numbers also go to AliExpress / Cainiao, which tracks some AliExpress parcels under GOFO's number: if Cainiao has the parcel, Peek follows it with Cainiao first and, when Cainiao gives the US as its destination, moves to GOFO once GOFO scans it. Until GOFO knows the number, Peek keeps checking. You can also pick GOFO Express with "Change", or paste a GOFO US tracking link with `searchID=` and the number in it.

Peek reads GOFO's US tracking every 10 minutes from 8 am to 10 pm Swiss time (about 2 am to 4 pm Eastern, 11 pm to 1 pm Pacific), every 2 minutes in those hours once the parcel is out for delivery, and every hour or two the rest of the time. It shows the status, each scan with its town and state on a map, the exact time of each scan, the delivery time, the service name and the destination. Peek removes GOFO's support line from every scan; a delivery shows as "Delivered", without the detail after the comma, and other scans show as GOFO writes them. Peek shows no expected delivery day and leaves out the delivery photos, coordinates and weight, so open GOFO's page for the photos. Peek can notify you of every scan, important steps only or delivery day only. It can't contact GOFO or the shop for you.

## Questions about GOFO tracking

### Is GOFO Express legit?

Yes. GOFO Express is a US parcel carrier with its own tracking, which follows parcels from the sender's warehouse through GOFO's hubs to its delivery stations. GOFO also runs networks in Canada, France, Italy, Spain and the Netherlands, each with its own numbers.

### What does Shipping Label Created mean on GOFO?

The sender has made the shipping label, and GOFO doesn't have the parcel yet. Then come `Pick Up Start`, when GOFO sets out to collect it, and `Pick Up Success`, once it has it.

### Why is my GOFO tracking number not working?

Check that it's a US GOFO number: GFUS, GF or CR. `GFCA`, `GFFR`, `GFIT`, `GFES` and `GFNL` numbers belong to GOFO's networks in other countries, and `YT` numbers to YunExpress. If the shop has only just given you the number, GOFO may not know it yet: Peek keeps checking. If it shows `Label Cancelled`, nothing is coming under that number, so ask the shop for the right one.

### Why does my AliExpress order have a GOFO number?

Cainiao, which tracks AliExpress orders, follows some of them under GOFO's GFUS number when GOFO delivers them in the US. For a GFUS number, Peek asks both GOFO and Cainiao. More in [AliExpress orders](guide:aliexpress-orders).

:::sources
- [GOFO: Customer Service](https://www.gofo.com/us/customer-service) – WhatsApp line and its hours, as listed on 15 October 2026
- [FTC: What To Do if You're Billed for Things You Never Got, or You Get Unordered Products](https://consumer.ftc.gov/articles/what-do-if-youre-billed-things-you-never-got-or-you-get-unordered-products) – shipping deadlines, delays and refunds, card disputes, unordered merchandise
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – GOFO's number shapes and its networks in other countries, GFUS and CR numbers, YunExpress numbers, GOFO's tracker and help page, scan wordings and their meanings, what GOFO's page shows and what Peek reads, Cainiao
:::
