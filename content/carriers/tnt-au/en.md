---
title: TNT Australia tracking — domestic numbers, statuses & help
description: Track TNT Australia domestic express consignments. Check 3-letter + 9-digit numbers, depot scan wordings, Monday–Friday redelivery, and 13 11 50 support.
slug: tnt-australia-tracking
published: 2026-11-23
updated: 2026-11-23
---

TNT Australia (*TNT Express Australia*, integrated with FedEx Express Australia) is one of Australia's major domestic express parcel, document, and pallet carriers, serving business and e-commerce deliveries nationwide through its network of Australian depots ([tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html)). While international TNT shipments are tracked through the global [TNT](carrier:tnt) or [FedEx](carrier:fedex) systems, Australian domestic consignments have their own dedicated lookup on [tntexpress.com.au](https://www.tntexpress.com.au/interaction/Trackntrace.aspx).

You can check a domestic Australian consignment on [TNT Australia Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) or paste your 12-character consignment number into the box above.

## TNT Australia tracking number formats

TNT Australia domestic consignments use a 12-character alphanumeric format distinct from international 9-digit TNT numbers:

| Format (invented) | What it is |
| --- | --- |
| `SYD482917360` | **3 uppercase letters + 9 digits** (12 characters total) — standard TNT Australia domestic consignment number tracked on `tntexpress.com.au`. |

Because 3 letters followed by 9 digits also matches consignment formats used by [Colis Privé](carrier:colis-prive) and [Purolator](carrier:purolator), Peek treats a bare 12-character number as a low-confidence candidate and confirms it against TNT Australia's tracker (specific prefixes reserved for other carriers, such as `RWM` for Kuehne+Nagel, are excluded from TNT Australia detection). If you paste a direct link from `tntexpress.com.au/interaction/ASPs/trackref_detail.asp?con=...` or `trackref.aspx?con=...`, Peek routes it to TNT Australia immediately.

If your invoice or confirmation shows a **9-digit** consignment number (described in [TNT Australia's Help Center](https://www.tnt.com/express/en_au/site/support.html) as appearing about 3 cm below the customer number on the left side of the invoice), that usually belongs to an international [TNT](carrier:tnt) or [FedEx](carrier:fedex) shipment rather than the `tntexpress.com.au` domestic tracker. For other Australian parcel formats, see the [tracking number formats guide](guide:tracking-number-formats).

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
| `Book-In Delivery Requested` / `Book-In Delivery Arranged` | Booking notices when a business recipient arranges a timed receiving slot — administrative notices that do not change the physical stage, and can even post after delivery. |

## Redelivery, depot collection, and delays

[TNT Australia's Help Center](https://www.tnt.com/express/en_au/site/support.html) explains what to do if you miss a delivery or need to rearrange drop-off:

- **If the driver left a card:** Use TNT's Re-delivery Service to book a fresh delivery date.
- **If no card was left (or delivery hasn't been attempted yet):** Contact TNT Australia with your consignment number, full delivery address, and requested redelivery date.
- **Monday to Friday redeliveries only:** TNT Australia notes that orders can only be redelivered between Monday and Friday.
- **New delivery address required:** If tracking displays `New delivery address required`, the parcel is waiting at the local TNT depot named in the `Depot` column. Contact TNT Australia or the sender promptly to supply a corrected address or arrange depot collection so it is not returned. See the [missed delivery guide](guide:missed-delivery) and [when tracking stops updating](guide:tracking-not-updating) for more help.

If your Australian order is travelling with another domestic carrier, compare tracking on [Australia Post](carrier:australia-post), [Aramex Australia](carrier:aramex-au), [CouriersPlease](carrier:couriersplease), or [Border Express](carrier:border-express).

## Tracking TNT Australia with Peek

Paste your 3-letter + 9-digit domestic consignment number — or a `tntexpress.com.au` tracking link — into Peek. Because 12-character 3-letter + 9-digit shapes overlap with [Colis Privé](carrier:colis-prive) and [Purolator](carrier:purolator), you can select TNT Australia directly using **Change** when adding the parcel or **Change carrier** on the parcel screen.

Peek fetches TNT Australia's domestic tracking detail page directly through the open-source [universal-parcel-scraper TNT Australia adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au), with an automatic retry on network timeouts. It reads every scan wording, depot name, local depot wall-clock date/time (`dd/mm/yyyy HH:mm`, without inventing a time zone across Australian states), and the `Estimated Delivery by:` date while the consignment is in transit (clearing the estimate once delivered). TNT Australia's domestic detail table does not expose parcel weight, service name, or status codes.

Peek maps TNT Australia's wordings into clear stages: **Label created** (`Your shipment data is lodged`), **Received by carrier** (`We've collected your shipment`), **In transit** (`We're processing your shipment`), **Out for delivery** (`Your shipment's with a driver`), **Needs attention** (`New delivery address required`), and **Delivered** (`We've delivered your shipment` or `Your shipment was collected`). Booking notices (`Book-In Delivery Requested` and `Book-In Delivery Arranged`) stay in the timeline without reverting a completed delivery or changing the current stage.

During interstate linehaul between depots or over a weekend, Peek displays: "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking TNT Australia." After more than three weeks without a new scan, Peek shows: "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking TNT Australia." To jump straight to TNT Australia's support centre from your tracking card, tap **Get help from TNT Australia**.

Pick **Every scan**, **Important steps**, or **Delivery only** in Peek's notification settings to choose when Peek alerts you.

## Contacting TNT Australia

- **Phone (within Australia):** `13 11 50` (published across TNT Australia's site)
- **Help Center & redelivery info:** [TNT Australia Support](https://www.tnt.com/express/en_au/site/support.html)
- **Domestic tracking portal:** [tntexpress.com.au Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) (tracks up to 10 domestic consignments, Sameday Local Jobs, or 1 electronic reference at a time)
- **TNT Australia website & international tracking link:** [tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html) and [Track & Trace overview](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)

Have your 12-character domestic consignment number and delivery address ready when calling `13 11 50` or arranging a weekday redelivery.

## Frequently asked questions

### What is the difference between "We've collected your shipment" and "Your shipment was collected"?

`We've collected your shipment` happens at the start of the journey when TNT Australia picks the parcel up from the sender. `Your shipment was collected` happens at the end of the journey when the recipient picks a held parcel up from the TNT depot, so Peek marks it **Delivered**.

### Why did "Book-In Delivery Arranged" appear after my parcel was already delivered?

TNT Australia logs delivery-appointment booking notices separately from physical driver scans, and the confirmation notice can sometimes post after the actual delivery scan. Peek keeps the parcel at **Delivered** rather than moving it backwards.

### Does TNT Australia deliver or redeliver on weekends?

TNT Australia's [Help Center](https://www.tnt.com/express/en_au/site/support.html) states that rearranged deliveries can only be redelivered between **Monday and Friday**.

### My TNT number is 9 digits with no letters — why does TNT Australia reject it?

The `tnt-au` domestic tracker on `tntexpress.com.au` expects 3 letters followed by 9 digits. A pure 9-digit consignment number is normally tracked through international [TNT](carrier:tnt) (or [FedEx](carrier:fedex)) — switch carriers in Peek via **Change carrier**.

:::sources
- [TNT Australia](https://www.tnt.com/express/en_au/site/home.html)
- [TNT Australia Help Center](https://www.tnt.com/express/en_au/site/support.html)
- [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)
- [TNT Express Australia Domestic Tracker](https://www.tntexpress.com.au/interaction/Trackntrace.aspx)
- [universal-parcel-scraper TNT Australia adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au)
:::
