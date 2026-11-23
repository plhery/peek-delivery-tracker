---
title: PigeonShip tracking — numbers, statuses, and delivery
description: Track PigeonShip same-day and final-mile deliveries in the US and Canada. Check 18-character numbers, live page statuses, due times, and support contacts.
slug: pigeonship-tracking
published: 2026-11-23
updated: 2026-11-23
---

PigeonShip is a final-mile delivery carrier operating across the United States and Canada, with offices in Provo, Utah, and Oakville, Ontario. Retailers, e-commerce shippers, and local businesses use PigeonShip for same-day, next-day, and regional e-commerce deliveries through services including **Pigeon Flex** (2–5 day nationwide delivery), **Pigeon Express** (2-day nationwide delivery), **Pigeon Connect** (on-demand same-day delivery), and **Pigeon Flash** (scheduled same-day delivery).

PigeonShip recruits independent drivers and runs dedicated delivery programs for businesses. Recipients get a tracking link on `admin.pigeonship.com` that shows the order's status and due time, then a completion page once it is delivered.

## PigeonShip tracking number format

Peek takes PigeonShip numbers of 8 to 30 letters and digits; it removes spaces and capitalises letters. Peek recognises one 18-character shape that alternates digits and capital letters:

- **18 alphanumeric characters** matching `digit-letter-digit-letter-3 digits-letter-3 digits-letter-2 digits-letter-digit-2 letters` (for example, `7P4K829H631N58R2TM`).

PigeonShip's tracking links have the form `admin.pigeonship.com/shipping/tracking/package/<trackingNumber>/`. When you paste that link into Peek, Peek extracts the package code and routes it straight to PigeonShip. From a bare 18-character code in this shape, Peek asks PigeonShip alone and shows "PigeonShip has this parcel." once it answers. For comparisons with other US and Canadian courier formats, see the [tracking number formats guide](guide:tracking-number-formats).

## How PigeonShip live tracking works

PigeonShip's recipient tracking page is a summary view rather than a scan history. While the order is active, it shows "In transit" and the driver on a map, with a deliver-by time (`dueDate`) when one is set; behind the page, the order carries a status code and label, such as `16` and `Assigned`. The page shows "Delivered" for code `64` alone. Once the delivery is complete, PigeonShip replaces the page at that same URL with a completion page reading `Your delivery has been completed successfully.` and a link to the delivery's pictures.

| PigeonShip status / page | What it means |
| --- | --- |
| Status `16` — `Assigned` | The order is on a driver's route; the page shows "In transit". |
| Due date / deliver-by time | The target completion timestamp shown on active orders before drop-off. |
| Status `64` / `Delivered` or `Your delivery has been completed successfully.` | The driver completed the delivery; opening PigeonShip's own link lets you view the delivery confirmation picture. |
| `Page not found` (404) | The tracking number is not active in PigeonShip's live tracking system, or contains a typo. |

Because PigeonShip does not publish a timestamped scan log on its recipient page, you will not see intermediate warehouse or sorting-centre scans the way you would with [UPS](carrier:ups), [USPS](carrier:usps), [Canada Post](carrier:canada-post), or [Intelcom](carrier:intelcom). Instead, the page updates its live state as the delivery progresses and switches to the completion screen once dropped off.

## Same-day delivery expectations and what to check if delayed

PigeonShip offers same-day and next-day delivery anywhere in the US and Canada. Reviews on its site mention a text before the driver arrives and a photo after the drop-off.

If your order still shows `Assigned` past its expected delivery time, or if the tracking link shows `Page not found`:

- **Check for typos:** Confirm the full code or full `admin.pigeonship.com` link from the retailer's email or SMS; PigeonShip's site answers `Page not found` for a number it has no page for.
- **Contact PigeonShip:** use its [contact form](https://www.pigeonship.com/contact-us) if the order stays on "In transit" past its due time.
- **Check delivery instructions:** reviews on PigeonShip's site mention drivers following the label's directions and a photo of the drop-off; open the original tracking link on `admin.pigeonship.com` to see the pictures after completion. For more troubleshooting steps, read [when tracking stops updating](guide:tracking-not-updating) and the [missed delivery guide](guide:missed-delivery).

## Tracking PigeonShip with Peek

Paste either your PigeonShip tracking number or the full `admin.pigeonship.com/shipping/tracking/package/...` link into Peek. If Peek does not pre-select PigeonShip for an unconventional code format, choose **Change** while adding the parcel or **Change carrier** on the parcel screen.

Peek fetches PigeonShip's server-rendered tracking page directly through the open-source [universal-parcel-scraper PigeonShip adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship). It reads the order's status code and label, whether it is delivered, and the deliver-by time (`dueDate`) when present, which Peek shows as the expected delivery. Peek leaves out the recipient's name, the driver's map coordinates, the progress rail and the delivery photos — open PigeonShip's own link to see the drop-off photo.

Because PigeonShip shows only the current state, Peek adds a row dated when it saw each change of stage: an active `Assigned` order shows as **In transit** with its expected delivery time, and a completed delivery as **Delivered**.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes while PigeonShip's tracking page answers. Otherwise Peek checks hourly. PigeonShip's tracking page shows no out-for-delivery step, so Peek never switches to its 2-minute delivery-day checks. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When PigeonShip's tracking page doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking PigeonShip.", with a "Get help from PigeonShip" link to PigeonShip's contact page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking PigeonShip."

In Peek's notification settings, pick **Every scan**, **Important steps**, or **Delivery only** to choose which updates alert you.

## Contacting PigeonShip

- **Official contact form:** [PigeonShip Contact Us](https://www.pigeonship.com/contact-us) (select United States, Canada, or Other and describe your delivery inquiry)
- **Official website:** [pigeonship.com](https://www.pigeonship.com/)
- **US office:** PigeonShip USA, 1675 N Freedom Blvd Ste 9B, Provo, Utah 84604-6929
- **Canada office:** PigeonShip Canada, 405 Dundas St W, Oakville, ON L6M 5P9

## Frequently asked questions

### Why does PigeonShip tracking not show a list of past scans?

PigeonShip's live recipient page is summary-only: it shows the order's current status ("In transit" or "Delivered") and deliver-by time rather than a multi-stop warehouse scan history.

### Where can I see the PigeonShip delivery photo?

Peek does not store or display delivery photos from PigeonShip. Once your parcel is marked delivered, open the original `admin.pigeonship.com/shipping/tracking/package/...` link from your email or SMS to view PigeonShip's completion page and delivery pictures.

### What does status `Assigned` mean on PigeonShip?

`Assigned` (status code `16`) is the order's status behind PigeonShip's page while it is on a driver's route; the page itself shows "In transit". Peek shows it as **In transit**.

### Does PigeonShip deliver in both the US and Canada?

Yes. PigeonShip operates same-day, next-day, and nationwide final-mile delivery services across the United States and Canada, with offices in Provo, Utah, and Oakville, Ontario.

:::sources
- [PigeonShip](https://www.pigeonship.com/)
- [PigeonShip Contact Us](https://www.pigeonship.com/contact-us)
- [universal-parcel-scraper PigeonShip adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship)
:::
