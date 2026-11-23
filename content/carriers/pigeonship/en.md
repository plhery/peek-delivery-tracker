---
title: PigeonShip tracking — numbers, statuses, and delivery
description: Track PigeonShip same-day and final-mile deliveries in the US and Canada. Check 18-character numbers, live page statuses, due times, and support contacts.
slug: pigeonship-tracking
published: 2026-11-23
updated: 2026-11-23
---

PigeonShip is a final-mile delivery carrier operating across the United States and Canada, with offices in Provo, Utah, and Oakville, Ontario. Retailers, e-commerce shippers, and local businesses use PigeonShip for same-day, next-day, and regional e-commerce deliveries through services including **Pigeon Flex** (2–5 day nationwide delivery), **Pigeon Express** (2-day nationwide delivery), **Pigeon Connect** (on-demand same-day delivery), and **Pigeon Flash** (scheduled same-day delivery).

Unlike national postal operators that record a long chain of depot scans, PigeonShip runs crowdsourced and dedicated final-mile routes and gives recipients a single live tracking link on `admin.pigeonship.com` showing the order's current status, due time, and post-delivery confirmation.

## PigeonShip tracking number format

PigeonShip package tracking numbers are alphanumeric strings of 8 to 30 characters. Live final-mile orders commonly use a structured 18-character format alternating digits and uppercase letters:

- **18 alphanumeric characters** matching `digit-letter-digit-letter-3 digits-letter-3 digits-letter-2 digits-letter-digit-2 letters` (for example, `7P4K829H631N58R2TM`).

PigeonShip sends recipients a direct tracking link formatted as `admin.pigeonship.com/shipping/tracking/package/<trackingNumber>/`. When you paste that link into Peek, Peek extracts the package code and routes it straight to PigeonShip. When you paste a bare 18-character code in this pattern, Peek recognizes PigeonShip as the candidate carrier and confirms the shipment against PigeonShip's live page. Spaces and lowercase letters are stripped automatically. For comparisons with other US and Canadian courier formats, see the [tracking number formats guide](guide:tracking-number-formats).

## How PigeonShip live tracking works

PigeonShip's recipient tracking page is a summary view rather than a traditional multi-scan history table. While the order is active, the page shows the current order status code, a short status label, and an expected deliver-by timestamp (`dueDate`) when scheduled. Once the driver completes the drop-off, PigeonShip replaces the live map screen at that same URL with a completion page reading `Your delivery has been completed successfully.` along with a link to delivery confirmation photos.

| PigeonShip status / page | What it means |
| --- | --- |
| Status `16` — `Assigned` | The package has been assigned to a PigeonShip driver or route and is moving toward delivery. |
| Due date / deliver-by time | The target completion timestamp shown on active orders before drop-off. |
| Status `64` / `Delivered` or `Your delivery has been completed successfully.` | The driver completed the delivery; opening PigeonShip's own link lets you view the delivery confirmation picture. |
| `Page not found` (404) | The tracking number is not active in PigeonShip's live tracking system, or contains a typo. |

Because PigeonShip does not publish a timestamped scan log on its recipient page, you will not see intermediate warehouse or sorting-centre scans the way you would with [UPS](carrier:ups), [USPS](carrier:usps), [Canada Post](carrier:canada-post), or [Intelcom](carrier:intelcom). Instead, the page updates its live state as the delivery progresses and switches to the completion screen once dropped off.

## Same-day delivery expectations and what to check if delayed

Because PigeonShip focuses on local same-day, next-day, and short-window e-commerce routes across North America, most orders move from driver assignment to doorstep drop-off within hours rather than days. On active orders, PigeonShip's tracking link also provides advance arrival alerts and a proof-of-delivery photo once the driver leaves the parcel at your door.

If your order still shows `Assigned` past its expected delivery time, or if the tracking link shows `Page not found`:

- **Check for typos or an unshipped order:** Confirm the full code or full `admin.pigeonship.com` link from the retailer's email or SMS. A newly printed order may not open a live route until PigeonShip creates the package page.
- **Check the retailer's order page:** On e-commerce orders, the merchant is PigeonShip's direct account holder and can verify whether the parcel has been handed off to the local driver or needs re-dispatch.
- **Check delivery instructions and safe drop locations:** PigeonShip drivers follow label notes and typically photograph the porch or front-door drop-off, so open the original tracking link on `admin.pigeonship.com` if you need to see the delivery photo after completion. For more troubleshooting steps, read [when tracking stops updating](guide:tracking-not-updating) and the [missed delivery guide](guide:missed-delivery).

## Tracking PigeonShip with Peek

Paste either your PigeonShip tracking number or the full `admin.pigeonship.com/shipping/tracking/package/...` link into Peek. If Peek does not pre-select PigeonShip for an unconventional code format, choose **Change** while adding the parcel or **Change carrier** on the parcel screen.

Peek fetches PigeonShip's server-rendered tracking page directly through the open-source [universal-parcel-scraper PigeonShip adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship). It reads the live status code, status label, completion state, and explicit deliver-by timestamp (`dueDate`) when present. To protect privacy and avoid noisy map data, Peek intentionally discards the recipient's name, live GPS coordinates, visual progress rail, and delivery photos — open PigeonShip's own link if you want to inspect the drop-off photo.

Because PigeonShip exposes only a current summary rather than a list of historical scans, Peek tracks it as a summary-only parcel rather than showing earlier **Label created** or **Received by carrier** scan rows: an active `Assigned` order displays as **In transit** with its expected delivery time, and a completed delivery displays as **Delivered**.

If an order goes quiet while waiting for a driver or route update, Peek notes: "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking PigeonShip." If nothing changes for over three weeks, Peek displays: "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking PigeonShip." Whenever you need direct assistance, select **Get help from PigeonShip** on the tracking screen to go straight to PigeonShip's contact page.

In Peek's notification settings, pick **Every scan**, **Important steps**, or **Delivery only** to choose which updates alert you.

## Contacting PigeonShip

- **Official contact form:** [PigeonShip Contact Us](https://www.pigeonship.com/contact-us) (select United States, Canada, or Other and describe your delivery inquiry)
- **Official website:** [pigeonship.com](https://www.pigeonship.com/)
- **US office:** PigeonShip USA, 1675 N Freedom Blvd Ste 9B, Provo, Utah 84604-6929
- **Canada office:** PigeonShip Canada, 405 Dundas St W, Oakville, ON L6M 5P9
- **Retailer support:** Because PigeonShip delivers on behalf of merchants, contacting the store that shipped your order is often the fastest way to request a redelivery, address correction, or missing-package replacement.

## Frequently asked questions

### Why does PigeonShip tracking not show a list of past scans?

PigeonShip's live recipient page is summary-only: it shows the order's current status (such as `Assigned` or delivered) and deliver-by time rather than a multi-stop warehouse scan history.

### Where can I see the PigeonShip delivery photo?

Peek does not store or display delivery photos from PigeonShip. Once your parcel is marked delivered, open the original `admin.pigeonship.com/shipping/tracking/package/...` link from your email or SMS to view PigeonShip's completion page and delivery pictures.

### What does status `Assigned` mean on PigeonShip?

`Assigned` (status code `16`) means PigeonShip has assigned your package to a driver or delivery route and it is underway toward your address.

### Does PigeonShip deliver in both the US and Canada?

Yes. PigeonShip operates same-day, next-day, and nationwide final-mile delivery services across the United States and Canada, with offices in Provo, Utah, and Oakville, Ontario.

:::sources
- [PigeonShip](https://www.pigeonship.com/)
- [PigeonShip Contact Us](https://www.pigeonship.com/contact-us)
- [universal-parcel-scraper PigeonShip adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship)
:::
