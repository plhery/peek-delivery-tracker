---
title: Amazon Shipping tracking: numbers, problems and help
description: Track an Amazon Shipping parcel sent by another shop: what its tracking number looks like, what to do if it doesn't arrive, and who to contact.
slug: amazon-shipping-tracking
published: 2026-10-12
updated: 2026-10-27
---

Amazon Shipping is Amazon delivering parcels for other businesses, so a shop that isn't Amazon can send your order through Amazon's network. You track it with its tracking number on Amazon Shipping's public tracker (track.amazon.co.uk for UK numbers). Orders placed on Amazon itself are followed in Your Orders instead.

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

Peek shows the status, the history with the town, region and country of each scan, the expected delivery day and the delivery time, checks every 10 minutes by day (every 2 once out for delivery) and every hour or two overnight, draws each scan on a map and can notify you of changes. It shows its own short labels in your language rather than Amazon's sentences, except "Arrived at sorting center", "Arrived at delivery center" and "Departed facility", which stay in English. It drops the shop's name, your name and address, the signature and the delivery photo. Amazon is the only source: when Amazon stops giving the history, Peek says so and stops checking. It doesn't follow the parcel to another carrier.

## Questions about Amazon Shipping tracking

### Is Amazon Shipping the same as Amazon?

It's Amazon's network delivering for other shops: you track it on track.amazon.co.uk and contact the shop about problems. Orders placed on Amazon itself live in Your Orders.

### Is Amazon Shipping legit?

Yes. It's Amazon's delivery service for other businesses. A text about an order you don't recognise is another matter: Amazon advises not to reply, click its links or call a number it gives.

### How long does Amazon Shipping take?

When Amazon's tracker gives an expected delivery day, Peek shows it, except for `TBA`, `TBC` and `TBM` numbers.

:::sources
- [About Amazon UK: Amazon CEO Andy Jassy's 2023 Letter to Shareholders](https://www.aboutamazon.co.uk/news/company-news/ceo-andy-jassys-2023-letter-to-shareholders) – what Amazon Shipping is: Amazon's last-mile delivery network carrying other businesses' parcels to their customers
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – contact the seller first, refund or redelivery, returning damaged goods
- [About Amazon UK: 7 tips to avoid scams impersonating Amazon](https://www.aboutamazon.co.uk/news/retail/how-to-avoid-scams-impersonating-amazon-this-holiday-season) – messages about a purchase, payment, gift cards, numbers not to call, reporting
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek recognises and reads, which tracker answers which numbers, Amazon's own orders not found on the public tracker
:::
