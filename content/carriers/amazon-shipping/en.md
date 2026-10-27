---
title: Amazon Shipping tracking: numbers, statuses and help
description: Track an Amazon Shipping parcel from another shop: where the tracking ID is, what each status means, missed deliveries, Counter pickups and who to contact.
slug: amazon-shipping-tracking
published: 2026-10-12
updated: 2026-10-27
---

Amazon Shipping is Amazon delivering parcels for other businesses, so a shop that isn't Amazon can send your order through Amazon's network. You track it on Amazon Shipping's public tracker (track.amazon.co.uk in the UK) with the tracking ID from the shop's shipping confirmation email, which also holds a link straight to it. Orders placed on Amazon itself are followed in Your Orders instead.

## Amazon Shipping tracking number and tracking ID

Once the seller dispatches your order, its shipping confirmation email gives a tracking number and a link to track.amazon.co.uk. The tracker calls it a "tracking number" or "tracking ID": enter it in the search box at the top of the page and click "Track". No postcode is asked.

Since 14 October 2024, Amazon Shipping's UK tracking IDs are a country code followed by 10 digits, 12 characters in all. Older UK IDs started with a two-character prefix such as `A1`, then 10 digits. Parcels handed to another carrier keep that carrier's format. Peek recognises these shapes:

| Shape (invented) | What it is |
| --- | --- |
| `UK0000000000` | A country code and 10 digits: the current UK layout. |
| `FR0000000000` | Other European country codes work the same way (`FR`, `IT`, `ES`, `DE` and others). |
| `TBA123456789012` | `TBA`, `TBC` or `TBM` and 12 digits. |

The catch: Amazon Logistics, which delivers Amazon's own orders, uses exactly the same shapes. Only Amazon's tracker tells them apart: it answers "not found" for Amazon's own orders. If it finds nothing and you ordered on Amazon itself, see [Amazon Logistics tracking](carrier:amazon-logistics). An order number such as `000-0000000-0000000` isn't a tracking number at all. No email? The shop has the number: [how to find your tracking number](guide:find-tracking-number).

## What Amazon Shipping tracking statuses mean

Amazon's UK tracker shows these in English. The French, Italian and Spanish trackers show their own language, with an English option.

| Amazon Shipping's wording | What it means for you |
| --- | --- |
| `Label Created` | The shop has made the label. Amazon doesn't have the parcel yet. |
| `Package arrived at the carrier facility` | It reached an Amazon sorting site. |
| `Package has left the carrier facility` | It left a site. Right after `Out for delivery`, it's the van leaving. |
| `Package arrived at the final hub/delivery station` | It's at the station that delivers it. |
| `Out for delivery` | It's with the driver today. |
| `Package delivered` | It has been delivered. |
| `On the way, but running late` | Late, but the date hasn't moved. |
| `Delivery attempted` | The driver couldn't deliver. The tracking page shows anything you need to do. |
| `Ready to be collected` | It's waiting for you at a pickup point. |
| `Returning to sender` | It's going back to the shop. |

`Label Created` on its own for days usually means the shop hasn't handed the parcel over yet, so ask the shop, not Amazon. When a parcel runs late with a new date, the headline above the history changes to "Now arriving" with that date.

## Amazon Shipping delivery times, missed deliveries and pickup points

**Hours.** Amazon Shipping delivers between 8am and 10pm every day, Saturdays and Sundays included; bank holidays may differ. It can't give a delivery window.

**Missed delivery.** Amazon makes more than one attempt, without saying how many. Attempts fail over a wrong address, no secure place, no access or a passcode that couldn't be checked. If you need to act, the tracking page says so. Amazon's guide for senders adds that when no safe place is available, the parcel waits at the local delivery station for about seven days; arrange redelivery or collection, or it goes back to the seller.

**Reschedule and instructions.** Enter your tracking ID and log in to the tracker. Under "Manage your delivery", "Reschedule" moves the delivery to another date within 7 days after the original one, but only before the first attempt. You can also add delivery notes, a safe place or a neighbour. The address can't be changed once the order is placed. Under "Get delivery updates", you can sign up for email or SMS updates.

**Proof of delivery.** A driver can photograph a parcel left in a safe place; once it passes Amazon's checks, the photo appears in the tracking. Some parcels need a 6-digit passcode, sent by email or text on the delivery day.

**Delivery to Counter (UK).** Amazon sends an email or SMS with a pickup barcode when the parcel arrives. Show it, or the manual code from the SMS, at the counter. You have 7 calendar days to pick it up, then it goes back to the retailer. Its help describes no locker pickup, but the tracker can give a locker as the place of delivery: "Your package was left in the locker".

## Amazon Shipping parcel not delivered, missing or lost

Amazon Shipping's tracker says it plainly: for delivery changes, refunds or other help, contact the sender.

**Marked delivered, not found.** Before you contact the seller, Amazon suggests checking the address, looking for a notice of attempted delivery, looking around the delivery spot, asking who else may have taken it in (a neighbour) and checking your mailbox, as some parcels travel through more than one carrier. More in [delivered but not received](guide:delivered-not-received).

**Damaged.** Contact the seller for a refund or replacement and send them photos.

**Returned.** A parcel returned to the seller can't be shipped again: ask the seller for a refund or replacement.

**Lost.** Contact the sender. The claim is theirs to make: Amazon Shipping gives shippers 28 days from label creation to claim for a lost or damaged parcel. Amazon publishes no rule for how long a recipient should wait when tracking stops moving.

## Amazon Shipping customer service and contact

Read on 27 October 2026. Amazon Shipping's [Contact us page](https://track.amazon.co.uk/contact-us) has a form to describe your issue, answered by email or, once you log in, by a phone callback. Amazon says it typically replies within a minute by phone and within 24 hours by email. That team only helps with parcels Amazon shipped for other retailers; Amazon.co.uk orders go through Your Orders, and lost or damaged parcels go to the sender. Amazon Shipping lists no phone number and no opening hours.

> If a message mentions a purchase, Amazon says not to reply or click any link. It won't ask for payment by phone or email, never asks you to buy a gift card, and tells you not to call numbers sent by text or email or found in search results. Report anything suspicious with Amazon's self-service tool. More in [delivery scam texts](guide:delivery-scam-texts).

## Tracking Amazon Shipping parcels with Peek

Paste the number or a track.amazon link (`/tracking/` followed by the number) into [Peek](/). Peek asks Amazon's public tracker whether it knows the number. If Amazon answers, you see "Public tracking is available with Amazon Shipping. Peek will keep this delivery up to date." If not, it counts as an Amazon Logistics delivery, usually tracked in Your Orders on Amazon. If the check can't run, try again. Amazon Shipping isn't in the "Change" list: this check is the only way in. No postcode needed.

Peek shows the status, the history with the town, region and country of each scan, the expected delivery day and the delivery time, checks every 10 minutes by day (every 2 once out for delivery) and every hour or two overnight, draws each scan on a map and can notify you of changes. It shows its own short labels in your language rather than Amazon's sentences, except "Arrived at sorting center", "Arrived at delivery center" and "Departed facility", which stay in English. It drops the shop's name, your name and address, the signature and the delivery photo. Amazon is the only source: when Amazon stops giving the history, Peek says so and stops checking. It doesn't follow the parcel to another carrier.

## Questions about Amazon Shipping tracking

### Is Amazon Shipping the same as Amazon?

It's Amazon's network delivering for other shops: you track it on track.amazon.co.uk and contact the shop about problems. Orders placed on Amazon itself live in Your Orders.

### Is Amazon Shipping legit?

Yes. It's Amazon's delivery service for other businesses, and you can sign up on its tracker for genuine email or SMS updates. A text about an order you don't recognise is another matter: Amazon advises not to reply, click its links or call a number it gives.

### How long does Amazon Shipping take?

The tracker's headline gives the expected day. Amazon Shipping sells Next-Day and Two-Day delivery across Great Britain, but the shop chooses the service and when it hands the parcel over.

:::sources
- [Amazon Shipping: Track a Package](https://track.amazon.co.uk/) – what Amazon Shipping is, how it works, how to track, languages of the trackers
- [Amazon Shipping: Help](https://track.amazon.co.uk/faq) – tracking email, hours, attempts, rescheduling, instructions, photo, passcode, Delivery to Counter, damaged and returned parcels
- [Amazon Shipping: Contact us](https://track.amazon.co.uk/contact-us) – form, email or callback, reply times, other retailers only, contact the sender
- [Amazon Shipping: Tracking page texts](https://track.amazon.co.uk/getLocalizedStrings) – status wordings, "Now arriving", contact-the-sender banner, delivery places such as the locker
- [Amazon Shipping UK: Customer experience tools and support](https://shipping.amazon.co.uk/resources/getting-started-guide/customer-experience-tools-and-support) – seven-day hold, pickup points, photo, tracking link
- [Amazon Shipping UK: Help](https://shipping.amazon.co.uk/help) – 28-day shipper claims, seven-day delivery
- [Amazon Shipping UK](https://shipping.amazon.co.uk/) – Next-Day and Two-Day delivery, seven days a week
- [Amazon Shipping developer docs: UK tracking ID format update](https://developer-docs.shipping.amazon.com/apis/changelog/pre-launch-announcement-amazon-shipping-tracking-id-format-update-for-uk) – UK tracking ID formats
- [About Amazon UK: 7 tips to avoid scams impersonating Amazon](https://www.aboutamazon.co.uk/news/retail/how-to-avoid-scams-impersonating-amazon-this-holiday-season) – messages about a purchase, payment, gift cards, numbers not to call, reporting
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek recognises and reads
:::
