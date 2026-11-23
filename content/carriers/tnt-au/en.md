---
title: TNT Australia tracking — domestic numbers, statuses & help
description: Track TNT Australia domestic express consignments. Check 3-letter + 9-digit numbers, depot scan wordings, Monday–Friday redelivery, and 13 11 50 support.
slug: tnt-australia-tracking
published: 2026-11-23
updated: 2026-11-23
---

TNT Australia (*TNT Express Australia*, integrating with FedEx Express) carries packages, documents and pallets across Australia through its depots ([tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html)). International shipments have a separate tracker (see [TNT](carrier:tnt)); Australian domestic consignments have a dedicated lookup on [tntexpress.com.au](https://www.tntexpress.com.au/interaction/Trackntrace.aspx).

You can check a domestic Australian consignment on [TNT Australia Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) or paste your 12-character consignment number into the box above.

## TNT Australia tracking number formats

Peek tracks TNT Australia's domestic consignments by a 12-character number:

| Format (invented) | What it is |
| --- | --- |
| `SYD482917360` | **3 uppercase letters + 9 digits** (12 characters total) — standard TNT Australia domestic consignment number tracked on `tntexpress.com.au`. |

Because 3 letters followed by 9 digits also matches numbers used by [Colis Privé](carrier:colis-prive) and [Purolator](carrier:purolator), Peek asks these three carriers in turn: TNT Australia first in Australia, Purolator first in Canada, Colis Privé first elsewhere. It shows "TNT Australia has this parcel." once TNT Australia answers. Some prefixes, such as `RWM` for Kuehne+Nagel, are left out of TNT Australia's shape. A direct link from `tntexpress.com.au/interaction/ASPs/trackref_detail.asp?con=...` or `trackref.aspx?con=...` goes to TNT Australia at once.

TNT Australia's [Help Center](https://www.tnt.com/express/en_au/site/support.html) calls the **9-digit** number on the left side of the invoice, about 3 cm below the customer number, the shipment (consignment) number. Peek's TNT Australia tracker takes only 3 letters and 9 digits. For other Australian parcel formats, see the [tracking number formats guide](guide:tracking-number-formats).

## What TNT Australia tracking statuses mean

TNT Australia's domestic detail page lists four columns — `Status`, `Date`, `Time`, and `Depot` — along with an `Estimated Delivery by:` date at the top when available.

| TNT Australia wording | What it means |
| --- | --- |
| `Your shipment data is lodged` | The sender electronically submitted consignment data; TNT Australia has not collected the parcel yet. |
| `We've collected your shipment` | TNT Australia picked the consignment up from the sender, ahead of the first depot scan. |
| `We're processing your shipment` | Scanned at an origin, transit, or delivering TNT Australia depot. |
| `Your shipment's with a driver` | Out on a delivery vehicle for doorstep or business delivery today. |
| `New delivery address required` | A delivery round could not deliver to the listed address; the parcel is held at the local depot awaiting an updated address or depot collection. |
| `We've delivered your shipment` | Delivered to the recipient's address. |
| `Your shipment was collected` | The recipient collected the parcel in person from the holding TNT depot (distinct from `We've collected your shipment`, which is sender pickup). |
| `Book-In Delivery Requested` / `Book-In Delivery Arranged` | The recipient asked for a booked delivery, and TNT confirmed it. Notices, not scans: they keep the parcel's stage, and the confirmation can post after the delivery. |

## Redelivery, depot collection, and delays

[TNT Australia's Help Center](https://www.tnt.com/express/en_au/site/support.html) explains what to do if you miss a delivery or need to rearrange drop-off:

- **If the driver left a card:** Use TNT's Re-delivery Service to book a fresh delivery date.
- **If no card was left (or delivery hasn't been attempted yet):** Contact TNT Australia with your consignment number, full delivery address, and requested redelivery date.
- **Monday to Friday redeliveries only:** TNT Australia notes that orders can only be redelivered between Monday and Friday.
- **New delivery address required:** the parcel stays at the depot named in the `Depot` column until you give another address or collect it; contact TNT Australia. See the [missed delivery guide](guide:missed-delivery) and [when tracking stops updating](guide:tracking-not-updating) for more help.

If your Australian order is travelling with another domestic carrier, compare tracking on [Australia Post](carrier:australia-post), [Aramex Australia](carrier:aramex-au), [CouriersPlease](carrier:couriersplease), or [Border Express](carrier:border-express).

## Tracking TNT Australia with Peek

Paste your 3-letter + 9-digit domestic consignment number, or a `tntexpress.com.au` tracking link, into Peek. If Peek files it under another carrier, use **Change** when adding the parcel or **Change carrier** on the parcel screen.

Peek fetches TNT Australia's domestic tracking detail page directly through the open-source [universal-parcel-scraper TNT Australia adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au), with an automatic retry on network timeouts. It reads every scan wording, depot and time, and the `Estimated Delivery by:` date until the consignment is delivered. TNT Australia gives depot times without an offset: Peek dates each on its depot's zone (Sydney time for Sydney, Perth time for Perth, and so on), a scan without a depot on its neighbour's, else on Sydney time, and shows every scan on your device's clock. TNT Australia's domestic detail table does not expose parcel weight, service name, or status codes.

Peek maps TNT Australia's wordings into clear stages: **Label created** (`Your shipment data is lodged`), **Received by carrier** (`We've collected your shipment`), **In transit** (`We're processing your shipment`), **Out for delivery** (`Your shipment's with a driver`), **Needs attention** (`New delivery address required`), and **Delivered** (`We've delivered your shipment` or `Your shipment was collected`). Booking notices (`Book-In Delivery Requested` and `Book-In Delivery Arranged`) stay in the timeline without reverting a completed delivery or changing the current stage.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day TNT Australia shows `Your shipment's with a driver`, every 2 minutes from 6 a.m. on the newest scan's clock, while TNT Australia's tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When TNT Australia doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking TNT Australia.", with a "Get help from TNT Australia" link to TNT Australia's Help Center. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking TNT Australia."

Pick **Every scan**, **Important steps**, or **Delivery only** in Peek's notification settings to choose when Peek alerts you.

## Contacting TNT Australia

- **Phone (within Australia):** `13 11 50` (published across TNT Australia's site)
- **Help Center & redelivery info:** [TNT Australia Support](https://www.tnt.com/express/en_au/site/support.html)
- **Domestic tracking portal:** [tntexpress.com.au Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) (tracks up to 10 domestic consignments, Sameday Local Jobs, or 1 electronic reference at a time)
- **TNT Australia website & international tracking link:** [tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html) and [Track & Trace overview](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)

Have your 12-character domestic consignment number and delivery address ready when calling `13 11 50` or arranging a weekday redelivery.

## Frequently asked questions

### What is the difference between `We've collected your shipment` and `Your shipment was collected`?

`We've collected your shipment` happens at the start of the journey when TNT Australia picks the parcel up from the sender. `Your shipment was collected` happens at the end of the journey when the recipient picks a held parcel up from the TNT depot, so Peek marks it **Delivered**.

### Why did `Book-In Delivery Arranged` appear after my parcel was already delivered?

`Book-In Delivery Arranged` is TNT Australia's confirmation of a booked delivery, a notice rather than a scan, and it can post after the delivery itself. Peek keeps the parcel at **Delivered** rather than moving it backwards.

### Does TNT Australia deliver or redeliver on weekends?

TNT Australia's [Help Center](https://www.tnt.com/express/en_au/site/support.html) states that rearranged deliveries can only be redelivered between **Monday and Friday**.

### My TNT number is 9 digits with no letters — why doesn't it track as TNT Australia in Peek?

Peek's TNT Australia tracker takes 3 letters followed by 9 digits, the domestic consignments it reads on `tntexpress.com.au`. TNT's Help Center calls a 9-digit number on the invoice the shipment (consignment) number; try [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html) with it, or call `13 11 50`.

:::sources
- [TNT Australia](https://www.tnt.com/express/en_au/site/home.html)
- [TNT Australia Help Center](https://www.tnt.com/express/en_au/site/support.html)
- [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)
- [TNT Express Australia Domestic Tracker](https://www.tntexpress.com.au/interaction/Trackntrace.aspx)
- [universal-parcel-scraper TNT Australia adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au)
:::
