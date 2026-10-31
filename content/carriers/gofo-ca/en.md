---
title: GOFO Canada tracking: GFCA numbers, statuses and delays
description: Track a GOFO parcel in Canada: what a GFCA number looks like, what GOFO's scans mean in Peek, and what to do when a parcel is late, missed or missing.
slug: gofo-canada-tracking
published: 2026-10-28
updated: 2026-10-28
---

GOFO Canada is the Canadian network of GOFO, a parcel carrier with networks in several countries, and it runs the same tracking system as GOFO in the United States. Its numbers begin with `GFCA`: track one on GOFO's Canadian tracking page, on gofo.com, or paste it in the box above. No number yet? See [how to find a tracking number](guide:find-tracking-number).

## GOFO Canada tracking number: what it looks like

Peek files a number under GOFO Canada when it has this shape, in capitals or not, with or without spaces:

| Shape (invented) | What it is |
| --- | --- |
| `GFCA00000000000000` | GFCA followed by 14 digits, the GOFO Canada waybill number |

GOFO also gives each parcel a tracking number, which either repeats the `GFCA` waybill or is the shop's own reference. Peek follows the `GFCA` waybill only, so that's the number to paste.

**Numbers that look alike.** GOFO's other networks have their own shapes. `GFUS` and 14 digits, `GF` and 13, or `CR` and 12 belong to [GOFO Express](carrier:gofo) in the US; `GFFR` and `CIFR` numbers to [GOFO France](carrier:gofo-fr); `GFIT` and `CIIT` numbers to [GOFO Italy](carrier:gofo-it); `GFNL` and `GFES` numbers to GOFO's Dutch and Spanish networks. None of them is a GOFO Canada number. More on telling shapes apart: [tracking number formats](guide:tracking-number-formats).

## GOFO Canada tracking statuses explained

GOFO Canada uses GOFO US's scan codes, and Peek reads them with the same map. A Canadian parcel usually meets these scans, in order:

| GOFO's scan | Peek's step | What it means for you |
| --- | --- | --- |
| `Shipping Label Created` | Label created | The shop made the label. GOFO doesn't have the parcel yet. |
| `Arrived at GOFO Regional Hub` | In transit | It reached one of GOFO's regional hubs. |
| `Loaded on Vehicle` | In transit | Loaded for the trip between GOFO facilities, on the way to a delivery station. |
| `Arrived at GOFO Delivery Station` | In transit | At the GOFO station that delivers it. |
| `Out for Delivery` | Out for delivery | With the driver. |
| `Delivered` | Delivered | Delivered. |

Since the codes are shared, a line seen on GOFO's US parcels shows the same way if it turns up on a Canadian one. `Delivery Exception, Business Closed` is a failed attempt, shown as "Delivery attempted"; the reason after the comma varies. `Label Cancelled` means the label was voided after it was created and no parcel is coming under that number; Peek shows it as "Needs attention".

GOFO adds its support phone and email to some scan lines; Peek drops that line, as GOFO's own tracking page does. A delivered scan shows as plain `Delivered`: Peek leaves out whatever GOFO adds after the comma.

## GOFO Canada parcel late, missed or missing

**Not moving.** If only `Shipping Label Created` shows, GOFO doesn't have the parcel yet: the shop still has to hand it over. Peek keeps asking GOFO, and once a parcel has gone four days without a new scan, Peek's parcel page says so. More on [tracking that isn't updating](guide:tracking-not-updating).

**Missed delivery.** A failed attempt shows as "Delivery attempted", with GOFO's reason in the scan line. What happens next is up to GOFO and the shop: see [missed deliveries](guide:missed-delivery) for what to check. If a text asks you to pay to get a GOFO parcel redelivered, read [delivery scam texts](guide:delivery-scam-texts) before you click anything.

**Delivered but not received.** Peek shows when GOFO scanned the parcel as delivered, but not GOFO's delivery photo or the details GOFO adds to its delivered line. Look around your home first, then contact the shop with your order number and the `GFCA` number. See [delivered but not received](guide:delivered-not-received).

**Delayed or lost.** Ask the shop that shipped it: it holds your order and its shipping details. See [lost parcels](guide:lost-parcel) for what to ask. If the parcel is going back to the shop, see [returned to sender](guide:returned-to-sender).

Ordered from Temu or Shein? See [Temu orders](guide:temu-orders) and [Shein orders](guide:shein-orders). For duties on imports, see [customs](guide:customs).

## GOFO Canada customer service

Peek has no help link for GOFO Canada. For a late, missing or damaged order, start with the shop that sent it, and keep your `GFCA` number and your order number at hand. GOFO's own Canadian pages are on gofo.com.

## Track a GOFO Canada parcel with Peek

[Peek](/) recognises a GOFO Canada number by its shape: `GFCA` and 14 digits, in capitals or not, with or without spaces. A GOFO Canada tracking link from gofo.com with `searchID=` and the number in it works too. Peek follows only the `GFCA` waybill, not the shop's reference.

Peek reads GOFO Canada's own tracking: the status, each scan with its time and its town and province (placed on a map), the delivery time, the service name and the destination country. Scans appear as GOFO writes them, without the support line. Peek shows no expected delivery date and leaves out the delivery photos, coordinates and weight.

Peek checks every 10 minutes from 8 am to 10 pm Swiss time (about 2 am to 4 pm Eastern, 11 pm to 1 pm Pacific), every 2 minutes in those hours once the parcel is out for delivery, and every hour or two the rest of the time. It can notify you of every scan, important steps only (delivery, pickup, customs, missed attempts and returns) or delivery day only. It can't book a redelivery or contact GOFO or the shop for you.

## Questions about GOFO Canada tracking

### Is GOFO Canada legit?

Corporations Canada lists GOFO EXPRESS CANADA INC. as an active federal corporation, number 1807362-7, with its registered office in Mississauga, Ontario. It came under the Canada Business Corporations Act in July 2026, continued from Ontario. GOFO also has networks in the US, France, Italy, the Netherlands and Spain, which Peek tracks separately.

### What does "Loaded on Vehicle" mean?

GOFO has loaded the parcel for the trip between two of its facilities, before it reaches the delivery station that brings it to you. The next scan is usually `Arrived at GOFO Delivery Station`, then `Out for Delivery`.

### Why is my GOFO tracking number not working?

Check its shape first: a GOFO Canada number is `GFCA` and 14 digits. A `GFUS`, `GFFR` or `GFIT` number belongs to GOFO's US, French or Italian network, which Peek tracks under its own name. If a `GFCA` number still finds nothing, check it against the shop's shipping email, then ask the shop.

:::sources
- [Corporations Canada: GOFO EXPRESS CANADA INC.](https://ised-isde.canada.ca/cc/lgcy/fdrlCrpDtls.html?corpId=18073627) – active federal corporation 1807362-7, continued from Ontario under the CBCA on 2026-07-08, registered office in Mississauga
- [Universal Parcel Scraper: GOFO Canada](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo-ca) – number shape, links, scan wordings and stages shared with GOFO US, the support line, what Peek keeps and leaves out
:::
