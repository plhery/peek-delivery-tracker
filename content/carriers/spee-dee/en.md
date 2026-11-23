---
title: Spee-Dee Delivery tracking — SP numbers, states & help
description: Track Spee-Dee Delivery Service packages across the US Midwest. Check 18- and 20-char SP barcodes, reused barcode handling, service area, and 800-862-5578.
slug: spee-dee-tracking
published: 2026-11-23
updated: 2026-11-23
---

Spee-Dee Delivery (*Spee-Dee Delivery Service, Inc.*) is a family-owned US Midwest regional parcel and LTL freight carrier founded in 1978 by Don Weeres in St. Cloud, Minnesota. Built around economical next-day ground delivery across the Upper Midwest, Spee-Dee serves B2B distributors, auto-parts and industrial suppliers, pharmacies, and regional e-commerce retailers, alongside walk-in shipping locations and EZR Returns ([speedeedelivery.com](https://speedeedelivery.com/)).

You can track a shipment on [Spee-Dee's Track a Shipment page](https://speedeedelivery.com/track-a-shipment/) or paste your `SP` barcode into the box above.

## Spee-Dee tracking number formats

Spee-Dee barcodes always begin with `SP` followed by either 16 or 18 digits:

| Format (invented) | What it is |
| --- | --- |
| `SP4829173605148291` | `SP` + 16 digits (18 characters total) — standard Spee-Dee barcode; note that Spee-Dee recycles 16-digit serials over time. |
| `SP482917360514829173` | `SP` + 18 digits (20 characters total) — longer Spee-Dee barcode format. |

Both `SP` formats identify Spee-Dee with high confidence and do not collide with national US carriers such as [UPS](carrier:ups), [FedEx](carrier:fedex), or [USPS](carrier:usps). If you have a link from `speedeedelivery.com/...?tracking=...`, Peek extracts the `SP` number directly. For other US regional and national formats, see the [tracking number formats guide](guide:tracking-number-formats).

## Spee-Dee service area across the Midwest

According to [Spee-Dee's Service Area Maps](https://speedeedelivery.com/services/service-area-maps/), Spee-Dee provides regular ground delivery to **all** of six Midwest states and select cities and ZIP Codes in four neighbouring states:

- **Full-state coverage:** Illinois, Iowa, Minnesota, North Dakota, South Dakota, and Wisconsin.
- **Select cities and ZIP Codes:** Kansas (including expanded ZIPs `660` and `666`), Michigan, Missouri (including expanded ZIPs `634–635`, `644–646`, and `650–653`), and Nebraska.

Transit times across most of the core network are overnight (next business day) to two business days depending on origin and destination ZIP Codes.

## What Spee-Dee tracking statuses mean

Spee-Dee's package progress table lists three columns — `Date / Time`, `Location`, and `Activity` — newest first, alongside a top summary block showing `Status:`, `Delivered on:`, `Signed by:`, and `Delivered to:` once completed.

| Spee-Dee activity | What it means |
| --- | --- |
| `ARRIVAL SCAN` | Scanned into a Spee-Dee terminal or sorting hub along the Midwest ground network. |
| `OUT FOR DELIVERY` *(often followed by a route number in parentheses)* | Loaded onto a local Spee-Dee delivery truck for doorstep or business delivery today. |
| `Delivered` | Completed delivery; the summary on Spee-Dee's own page also records the delivery time, delivery address, and recipient signature when collected. |
| `No packages were found matching the barcode supplied.` | The barcode has not been scanned into Spee-Dee's system yet or contains a typo. |

### Reused 16-digit barcodes

Spee-Dee reuses older 16-digit `SP` barcodes on new shipments. On Spee-Dee's raw progress page, rows from a previously delivered parcel can still appear underneath your current package's scans. When you check a reused barcode on Spee-Dee's site, look at the dates at the top of the table and stop reading once you hit an earlier `Delivered` scan from a previous shipment.

## Delays, missing scans, and claims

Because Spee-Dee operates regional overnight linehaul trucks rather than multi-week long-haul freight, most shipments post an `ARRIVAL SCAN` the evening after pickup and go `OUT FOR DELIVERY` the next business morning.

- **No scans yet:** If your tracking page returns `No packages were found matching the barcode supplied.`, the shipper may have printed the label before Spee-Dee's evening pickup scan. Wait until the next business morning before checking with the seller, or see [when tracking stops updating](guide:tracking-not-updating).
- **Rate limits on frequent refreshes:** Spee-Dee's tracking host limits searches per minute and returns HTTP 503 temporarily if queried repeatedly in quick succession. Waiting a minute or letting Peek check automatically avoids hitting that limit.
- **Non-delivery or damage claims:** Spee-Dee's [Customer Service form](https://speedeedelivery.com/customer-service/) includes dedicated **Claims – Non-Delivery** and **Claims – Damage** request types, and asks you to include the **full delivery address** when submitting a tracking inquiry. See the [delivered not received guide](guide:delivered-not-received) if a package shows delivered without arriving.

## Tracking Spee-Dee with Peek

Paste any 18-character (`SP` + 16 digits) or 20-character (`SP` + 18 digits) Spee-Dee barcode — or a `speedeedelivery.com` link — into Peek, and Peek recognizes Spee-Dee automatically with high confidence. If you ever need to adjust the carrier manually, use **Change** while adding the parcel or **Change carrier** on the tracking screen.

Peek reads Spee-Dee's package progress page directly through the open-source [universal-parcel-scraper Spee-Dee adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee), with automatic remote fallback if Spee-Dee's host filters a direct connection. Peek automatically strips earlier history from **reused barcodes** (stopping at any prior completed delivery older than a day), drops internal HTML debug comments and parenthetical route numbers from `OUT FOR DELIVERY`, and records each scan's city/terminal location and local wall-clock timestamp (`MM/DD/YYYY hh:mm am/pm`, without guessing a time zone offset). Spee-Dee's tracker does not provide an estimated delivery date or parcel weight.

Because Spee-Dee's progress table starts once the parcel reaches a sorting terminal rather than listing separate **Label created** or **Received by carrier** rows, Peek maps `ARRIVAL SCAN` to **In transit**, `OUT FOR DELIVERY` to **Out for delivery**, and `Delivered` to **Delivered** (while checking any other activity text through Peek's shared status rules).

If no new scan arrives overnight or over a weekend, Peek displays: "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Spee-Dee." After more than three weeks without a new scan, Peek shows: "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Spee-Dee." To reach Spee-Dee's support team straight from the parcel view, tap **Get help from Spee-Dee**.

In Peek's notification preferences, pick **Every scan**, **Important steps**, or **Delivery only** to control your alerts.

## Contacting Spee-Dee Delivery

Spee-Dee notes on its [Customer Service page](https://speedeedelivery.com/customer-service/) that phone callers speak directly to a person rather than an automated phone tree:

- **Toll-free customer service:** `800-862-5578`
- **Local phone:** `320-251-6697`
- **Fax:** `320-251-1846` (LTL fax: `320-240-7995`)
- **Online contact & claims form:** [Spee-Dee Customer Service](https://speedeedelivery.com/customer-service/) (include your `SP` barcode and full delivery address for tracking questions)
- **Headquarters (physical address):** Spee-Dee Delivery Service Inc., 4101 Clearwater Road, St. Cloud, MN 56301
- **Mailing address:** P.O. Box 1417, St. Cloud, MN 56302
- **Company overview & tracking:** [About Spee-Dee](https://speedeedelivery.com/about-us/) and [Track a Shipment](https://speedeedelivery.com/track-a-shipment/)

## Frequently asked questions

### Why does my Spee-Dee tracking page show a delivery from months ago?

Spee-Dee reuses 16-digit `SP` barcodes over time, so its raw webpage can list an older completed shipment beneath your current parcel's scans. Peek automatically detects that boundary and hides rows belonging to previous deliveries.

### Which US states does Spee-Dee Delivery serve?

Spee-Dee covers all of Illinois, Iowa, Minnesota, North Dakota, South Dakota, and Wisconsin, plus select cities and ZIP Codes in Kansas, Michigan, Missouri, and Nebraska.

### Does Spee-Dee Delivery show an estimated delivery date on its tracking page?

No. Spee-Dee's tracking page shows terminal arrival scans, `OUT FOR DELIVERY`, and final delivery details, without an estimated delivery date. You can look up typical ground transit days by ZIP Code on [Spee-Dee's Service Area Maps](https://speedeedelivery.com/services/service-area-maps/).

### What should I include when contacting Spee-Dee about a package?

Have your full 18- or 20-character `SP` tracking number and the full delivery address ready — Spee-Dee's customer service form explicitly asks for the full delivery address on tracking inquiries.

:::sources
- [Spee-Dee Delivery](https://speedeedelivery.com/)
- [Spee-Dee — About Us](https://speedeedelivery.com/about-us/)
- [Spee-Dee — Track a Shipment](https://speedeedelivery.com/track-a-shipment/)
- [Spee-Dee — Service Area Maps](https://speedeedelivery.com/services/service-area-maps/)
- [Spee-Dee — Customer Service](https://speedeedelivery.com/customer-service/)
- [universal-parcel-scraper Spee-Dee adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee)
:::
