---
title: Amazon Shipping tracking: numbers, problems and help
description: Track an Amazon Shipping parcel from another shop: UK or TBA tracking numbers, how it differs from Amazon Logistics, missing or damaged parcels and scam tips.
slug: amazon-shipping-tracking
published: 2026-10-12
updated: 2026-11-27
---

Amazon Shipping is Amazon's parcel delivery network carrying orders for other businesses, whether you bought on Amazon.co.uk or another website. Track your shipment on Amazon Shipping's public tracker (track.amazon.co.uk for UK parcels) or paste the tracking number in the box above. Amazon's own retail orders use Amazon Logistics and are tracked in Your Orders instead.

## Amazon Shipping tracking number and tracking ID

Peek recognises these shapes as Amazon tracking numbers:

| Shape (invented) | What it is |
| --- | --- |
| `UK0000000000` | A country code and 10 digits, here the UK's. |
| `FR0000000000` | Other European country codes work the same way (`FR`, `IT`, `ES`, `DE` and others). |
| `TBA123456789012` | `TBA`, `TBC` or `TBM` and 12 digits. |

The catch: Amazon Logistics, which delivers Amazon's own orders, uses exactly the same shapes. Only Amazon's tracker tells them apart: it answers "not found" for Amazon's own orders. If it finds nothing and you ordered on Amazon itself, see [Amazon Logistics tracking](carrier:amazon-logistics). An order number such as `000-0000000-0000000` isn't a tracking number at all. No tracking number? The shop has it: [how to find your tracking number](guide:find-tracking-number).

## Amazon Shipping parcel not delivered, missing or lost

In England, Citizens Advice says that if you bought something online, you should try to contact the seller before you complain to the delivery company or ask it for compensation: you'll usually get a better result.

**Missing, or marked delivered but not there.** Ask the seller for a refund or redelivery. More in [delivered but not received](guide:delivered-not-received).

**Damaged.** Contact the seller and check your options for returning damaged goods.

## Amazon Shipping customer service and contact

If your parcel hasn't arrived or arrived damaged, your contact is the shop that sent it, as above.

> If a message mentions a purchase, Amazon says not to reply or click any link. It won't ask for payment by phone or email, never asks you to buy a gift card, and tells you not to call numbers sent by text or email or found in search results. Report anything suspicious with Amazon's self-service tool. More in [delivery scam texts](guide:delivery-scam-texts).

## Tracking Amazon Shipping parcels with Peek

Paste the number or a track.amazon link (`/tracking/` followed by the number) into [Peek](/). Peek asks Amazon's public tracker whether it knows the number. If Amazon answers, you see "Public tracking is available with Amazon Shipping. Peek will keep this delivery up to date." If not, it counts as an Amazon Logistics delivery, usually tracked in Your Orders on Amazon. If the check can't run, try again. Amazon Shipping isn't in the "Change" list: this check is the only way in. No postcode needed.

Peek shows the status, the history with the town, region and country of each scan, the expected delivery day and the delivery time, checks up to every 10 minutes (up to every 2 from 6 a.m. local time on the day Amazon scans the parcel out for delivery), draws each scan on a map and can notify you of changes. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It shows its own short labels in your language rather than Amazon's sentences, except "Arrived at sorting center", "Arrived at delivery center" and "Departed facility", which stay in English. It drops the shop's name, your name and address, the signature and the delivery photo. Amazon is the only source: when Amazon stops giving the history, Peek says so and stops checking. It doesn't follow the parcel to another carrier.

## Questions about Amazon Shipping tracking

### What is the difference between Amazon Shipping and Amazon Logistics?

Amazon Shipping delivers parcels for other businesses—whether you bought on Amazon.co.uk or another website—and offers public tracking on track.amazon.co.uk. Amazon Logistics delivers Amazon's own orders, which are tracked inside Your Orders.

### Is Amazon Shipping legit, and how do I spot a fake text?

Yes, Amazon Shipping is Amazon's official delivery service for third-party businesses. If you get an unexpected message about a purchase, Amazon advises not to reply, click links or call numbers in texts or emails, and notes it never asks for payment by phone or email or for gift cards.

### Who do I contact if my Amazon Shipping parcel is missing or damaged?

Contact the shop or seller that sent the order first. Citizens Advice advises starting with the seller for a refund, redelivery or damaged-goods return rather than complaining to the carrier first.

### How long does Amazon Shipping take to deliver?

When Amazon's public tracker publishes an expected delivery day for your parcel, Peek shows it (except for `TBA`, `TBC` and `TBM` numbers).

:::sources
- [Amazon Shipping: Track a Package](https://track.amazon.co.uk/) – order placed on Amazon.co.uk or any other website, the seller creates the shipment, delivery services for businesses
- [About Amazon UK: Amazon CEO Andy Jassy's 2023 Letter to Shareholders](https://www.aboutamazon.co.uk/news/company-news/ceo-andy-jassys-2023-letter-to-shareholders) – what Amazon Shipping is: Amazon's last-mile delivery network carrying other businesses' parcels to their customers
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – contact the seller first, refund or redelivery, returning damaged goods
- [About Amazon UK: 7 tips to avoid scams impersonating Amazon](https://www.aboutamazon.co.uk/news/retail/how-to-avoid-scams-impersonating-amazon-this-holiday-season) – messages about a purchase, payment, gift cards, numbers not to call, reporting
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek recognises and reads, which tracker answers which numbers, Amazon's own orders not found on the public tracker
:::
