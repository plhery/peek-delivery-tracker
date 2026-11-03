---
title: DayOne tracking: DOT numbers, statuses and who delivers
description: Track a DayOne parcel from China: what a DOT number looks like, what the statuses on DayOne's tracker mean, who delivers it and who to ask when it's late.
slug: dayone-tracking
published: 2026-11-02
updated: 2026-11-02
---

DayOne is the warehouse that sends out a shop's online orders from China. It is not a courier: it says it is "the order fulfillment warehouse, not a courier delivery service provider". Other companies carry the parcel, and DayOne's tracker, [DayOneTrack](https://www.dayonetrack.com/), in English and Chinese, shows their scans. Look up your DOT number there, or paste it in the box above. The shop gives you the number with your order's shipping details.

## DayOne tracking number: what it looks like

DayOne publishes no number format. The shape Peek knows as DayOne's, with made-up digits:

| Number | What it is |
| --- | --- |
| `DOT0000000000000000` | DOT and 16 digits: a DayOne number. |
| `DOT1000000000000000` | DOT1 and 15 more digits: also DayOne's. DayOne's tracker looks these up in another system. |
| `DOT000000000000000` | Only 15 digits: not DayOne's shape, and Peek doesn't name DayOne. Check for a missing digit. |

In Peek, lower case doesn't matter. Keep DOT joined to the digits: with a space or a dash right after DOT, Peek can read the digits alone and name another carrier.

**Where to find it.** In the shop's shipping email or on your order page. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

**On DayOne's tracker.** You can look up as many as 10 numbers at a time, one per line. The tracker only looks up numbers with DOT0 or DOT1 in them: any other number, such as a local post's, shows `Not found`. Next to the tracking number, a result can show a `Reference Number`, the shop's own reference for the order, and the country the parcel is going to.

## DayOne tracking statuses explained

DayOne's tracker gives each parcel one status, in English and Chinese, and lists the scans under `Package Track`, newest first:

| DayOne's status | What it means for you | Peek shows |
| --- | --- | --- |
| `Not found` (查询不到) | DayOne has nothing under this number yet. The tracker adds: "The order information has not been updated yet". | "Waiting for the carrier" |
| `Info received` (收到信息) | The shipping data is in. The parcel may not have left the warehouse. | "Label created" |
| `In transit` (运输途中) | On its way. | "In transit" |
| `Pick up` (到达待取) | It has arrived and is waiting for you to collect it. | "Ready for pickup" |
| `Out for Delivery` (派送途中) | Out for delivery. | "Out for delivery" |
| `Undelivered` (投递失败) | A delivery attempt failed. | "Delivery attempted" |
| `Delivered` (成功签收) | Delivered. | "Delivered" |
| `Alert` (可能异常) | There may be a problem. | "Needs attention" |
| `Expired` (运输过久) | In Chinese, "in transit too long": the parcel has been on its way for a long time. | the newest scan's status |

Peek goes by the newest scan it understands. It uses DayOne's status only when it understands none of the scans.

Each scan has a time, a place and the words of the company that has the parcel: Yanwen's in China, then the post's in your country. The scans people see, in the order a parcel usually meets them:

| DayOne says | What it means for you | Peek shows |
| --- | --- | --- |
| `Order processed by shipper` | Yanwen has the order's data. Nobody has scanned the parcel yet. | "Label created" |
| `Yanwen Pickup Scan`, `Processing information input` | Yanwen has the parcel in China and is processing it. | "Received by carrier" |
| `Yanwen facility - Outbound`, `Arrived at domestic terminal station` | It left Yanwen's sorting centre, then reached a terminal in China, before export customs. | "In transit" |
| `Port of departure - Received by carrier`, `International shipment release - Export`, `Port of departure - Departure` | At the port of departure, through China's export customs (not yours), then flown out. | "In transit" |
| `Port of destination - Arrival`, `ARRIVED AT DESTINATION AIRPORT` | It landed in or near your country. Import customs is still to come. | "In transit" |
| `CLEARED BY CUSTOMS`, `COLLECTED FROM AIRPORT TERMINAL`, `ARRIVED AT FACILITY IN DESTINATION COUNTRY` | Your country's customs released it, and it was taken from the airport to a facility. | "In transit" |
| `RECEIVED BY AUSTRALIA POST FOR TRANSPORTATION TO PROCESSING FACILITY` | In Australia: handed to Australia Post. | "In transit" |
| `ITEM PROCESSED AT POST FACILITY`, `PROCESSED THROUGH AUSTRALIA POST FACILITY`, `TRANSFERRED` | Sorted by the post and sent on. | "In transit" |
| `WITH AUSTRALIA POST FOR DELIVERY TODAY` | Out for delivery today. | "Out for delivery" |
| `DELIVERED WITH SAFE DROP` | Delivered and left in a safe place at the address. | "Delivered" |

`SHIPPING INFORMATION RECEIVED`, `SHIPPING INFORMATION RECEIVED BY AUSTRALIA POST` and `SHIPPING INFORMATION APPROVED BY AUSTRALIA POST` mean the post has the parcel's details. They can come after later scans: that's not a step back, and Peek keeps the later progress.

If the only scan is `Shipment has begun. In transit to destination city`, DayOne handed the parcel to [YunExpress](carrier:yunexpress), and the rest of its history is on YunExpress's tracker, under a YunExpress number (YT and 16 digits). `Clearence processing completed - Export` is YunExpress's export clearance, in YunExpress's spelling.

## Who delivers DayOne parcels

DayOne doesn't say who delivers in your country. Its scans show who has the parcel: Yanwen in China, then the post where you live, such as [Australia Post](carrier:australia-post) for parcels to Australia.

DayOne publishes no rules for delivery attempts, notice cards or pickup points: those are the delivering company's. After `Pick up` or `Undelivered`, look for a card or a message from that company. See [missed deliveries](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

**Customs in the EU.** Goods bought online from outside the EU carry a €3 customs duty per item on parcels worth up to €150, since 1 July 2026, and a €2 Union handling fee per item, whatever the value, since 1 November 2026. An item is the goods sharing a tariff classification, description and, where required, origin. DayOne's site says nothing about duty or fees, or about who collects them. Elsewhere, what you owe depends on your country: see [parcels held at customs](guide:customs).

## DayOne parcel not updating, lost or returned

DayOne sends you to the shop. Its tracker says: "For all package logistics, lost items, refunds, and resending related issues, please contact the customer service of the ordering store."

**`Not found`.** DayOne has nothing under the number yet. Check the number: DOT and 16 digits. If it's right, the order may not have been sent yet. Peek shows "Waiting for the carrier" and keeps checking. After two days with nothing, Peek flags the parcel: "Still waiting for the carrier’s first update". If nothing comes after a few days, ask the shop.

**Only `Info received` or `Order processed by shipper`.** Nobody has scanned the parcel yet. If it stays like that for several days, ask the shop when it was sent.

**No new scan for days.** Parcels often go quiet on long flights and at customs. When a parcel on its way has had no new scan for four days, Peek flags it ("No tracking update for four days"). See [why tracking stops updating](guide:tracking-not-updating) and [tracking parcels from China](guide:tracking-from-china).

**`Alert` or `Expired`.** Read the newest scans, then ask the shop for help, with your DOT number.

**Lost, damaged or returned.** DayOne publishes no claim process for recipients. Ask the shop for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

**Delivered but not there.** `DELIVERED WITH SAFE DROP` means the parcel was left at the address. Look around your home, ask neighbours, then tell the shop. See [delivered but not received](guide:delivered-not-received).

## DayOne contact

As read on 1 November 2026 on [DayOne's tracker](https://www.dayonetrack.com/):

- **About your parcel**: the shop you ordered from, as DayOne asks.
- **Business email**: info@dayone-shipping.com, which DayOne gives for cooperation and business enquiries other than parcel questions.

DayOne's tracker gives no phone number, chat, contact form or address. It stores only waybill numbers and keeps no order details, it says.

## Track a DayOne parcel with Peek

[Peek](/) names DayOne at once from a DOT number with 16 digits ("Detected carrier"). A dayonetrack.com tracking link with `trackNo=` works too. For any other number, paste it all the same: Peek follows more than 3,500 carriers. When a number's shape fits several carriers, Peek shows "Checking tracking services…" while it asks around. If Peek doesn't name DayOne for your number, tap "Change" and pick DayOne under "All carriers" (searching Day One finds it too), or use "Change carrier" on a parcel you've already added.

For a DOT0 number, Peek reads DayOne's own tracker: the status, each scan with its place, the destination country and the time of delivery. For a DOT1 number, it asks tracking services instead.

When DayOne gives a scan the time offset of its place, Peek uses it. For a scan without one, Peek works out its clock from its place or the scans around it (your country's once a scan says the parcel has arrived). It shows every time in your device's time zone. It leaves out postcodes, the name of the person who took a delivered parcel, and the shop's `Reference Number`. DayOne gives no expected delivery date or weight.

DayOne's tracker doesn't show the delivering company's number, but its lookup sometimes has it. Peek then asks that company too: your country's post for a postal number (two letters, nine digits, two letters), such as Royal Mail, USPS, Canada Post, An Post or Australia Post, or the one carrier the number's shape points to, such as YunExpress for a YT number. Once that carrier shows a scan at least as recent as DayOne's newest, Peek follows the parcel there, shows its number next to the DOT number and keeps DayOne's history.

For a DOT0 number, Peek checks every 10 minutes, or every 2 minutes once the parcel is out for delivery; when tracking services answer, as for a DOT1 number, every 15 minutes. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". It can't contact DayOne or the shop for you.

## Questions about DayOne tracking

### Is DayOne a courier?

No. DayOne says it is "the order fulfillment warehouse, not a courier delivery service provider". Other companies carry the order: in DayOne's scans, Yanwen in China, then the post in your country.

### Where can I track a DOT number?

On DayOne's tracker, [dayonetrack.com](https://www.dayonetrack.com/), in English or Chinese, up to 10 numbers at a time. Or paste it in Peek.

### Why does DayOne's tracker say Not found?

DayOne has nothing under that number yet. Check that you typed DOT and all 16 digits. If the number is right, wait a day or two, then ask the shop whether the parcel has left.

### Why are the times on DayOne's tracker out of order?

Each scan shows the local time where it was made, with no time zone, and the tracker sorts the scans by those times. In the northern winter, Beijing is 8 hours ahead of London and 13 ahead of New York, so a scan in your country can look older than the last one in China. Sydney is 3 hours ahead of Beijing in the Australian summer, so scans there look newer. Peek shows each time in your device's time zone.

:::sources
- [DayOne: Dayone Tracking](https://www.dayonetrack.com/) – warehouse, not a courier; after-sales to the shop; privacy statement; business email; English and Chinese; 10 numbers at a time; DOT0 and DOT1 lookups; statuses; result fields; times
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 duty since 1 July 2026, €2 Union handling fee from 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per item whatever the value, what an item is
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shape, links and lookups, scan wordings and codes, clocks, the delivering company's number, hand-over, what Peek reads
:::
