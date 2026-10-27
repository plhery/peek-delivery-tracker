---
title: Canada Post tracking: numbers, statuses and notice cards
description: Track a Canada Post parcel: where the tracking number is, what Canada Post's statuses mean, notice cards and pickup deadlines, missing parcels and contacts.
slug: canada-post-tracking
published: 2026-10-15
updated: 2026-10-27
---

Canada Post is Canada's national postal service, a federal Crown corporation. Track a parcel with Canada Post's Track tool on [canadapost-postescanada.ca](https://www.canadapost-postescanada.ca/track-reperage/en), in Canada Post's app or by pasting the number in the box above. The number comes from the sender: the shop's shipping confirmation email, or the receipt if you posted it yourself.

## Canada Post tracking number: what it looks like and where to find it

Canada Post's numbers come in two main shapes:

| Shape (example) | Where you see it |
| --- | --- |
| `0000 0000 0000 0000` | 16 digits, Canada Post's own example: labels bought online, printed on the receipt or on the parcel. |
| `RR000000005CA` | 13 characters ending in `CA`: counter labels, prepaid envelopes, Registered Mail, Xpresspost USA and International, Priority Worldwide. |

From a shop, it's in the shipping confirmation email; if you posted it, on your receipt. Only the sender can give you the number: Canada Post keeps no records by name or address.

Track also takes the 15-digit number on a Delivery Notice Card, and a sender's reference number together with the shipping date.

Sixteen digits also fit DHL eCommerce, Correos Express and TNT, so check which carrier the shop's email names. [Purolator](carrier:purolator) numbers have 12 digits, so they can't be mistaken for these. More in [tracking number formats](guide:tracking-number-formats).

## What Canada Post's tracking statuses mean

Track shows a summary label above a list of scans. These are the English scans; the French site shows French ones.

| Canada Post's status | What it means for you |
| --- | --- |
| `Electronic information submitted by shipper` | The label exists; Canada Post doesn't have the parcel yet. |
| `Item accepted at the Post Office` | Canada Post has it. |
| `Item processed` | It's moving through Canada Post's network. |
| `Item has arrived in Canada and will be presented for review` | From abroad: waiting for the Canada Border Services Agency. |
| `Item was released by Customs and is now with Canada Post for processing` | Customs is done. |
| `Item out for delivery` | It's on the van today. |
| `Delivered to your community mailbox, parcel locker or apt./condo mailbox` | Delivered; check the parcel compartment or building mailbox. |
| `Notice card left indicating where and when to pick up item` | Missed delivery: the card says which post office will hold it. |
| `Delivery Attempt Made / Rescheduled for delivery next business day` | The attempt failed; Canada Post normally tries again the next business day. |
| `Item available for pickup at Post Office` | Ready to pick up. |
| `Final Notice; Item will be returned to sender if not collected within 10 days` | Last reminder before it goes back. |
| `Recipient not located at address provided. Item being returned to sender.` | Going back to the sender. |

The summary label can read `Notice left` while the scan says a card was left: same thing. It happens when the parcel won't fit, no one is home and there's no safe place, or the sender chose "Card for pickup".

`Verifying recipient's address; Possible delay` means an addressing problem. If Canada Post can't verify the address, the parcel goes back, and a return that has started can't be stopped. `Check delivery progress` means delivery was interrupted; a progress bar with an arrow at its end means it's returning to the sender. `Item held by customs` means customs is reviewing it, and neither Canada Post nor the sending post can step in.

## Notice card, pickup and missed deliveries

Signature parcels get an attempt at the door; if no one is there, a Delivery Notice Card. Others go to your mailbox, community mailbox or door; if one is too large and no one is home, Canada Post may safe drop it or leave a card.

A card means the parcel waits at a nearby post office. A Final Notice Card follows after 5 calendar days, and the post office keeps the parcel 15 calendar days in all, then returns it to the sender. That's why the Final Notice scan says 10 days.

To pick it up, bring the card if you have it and government-issued photo ID. Someone else can go with their own photo ID plus your signed card with their name printed on it, or a letter of authorization. No card but an attempt in tracking? It may come the next business day with your mail, or Track shows where to go.

In community parcel lockers, a key in your mailbox opens the compartment; the parcel waits up to 5 days, then goes to the post office.

From abroad, the CBSA assesses duty and taxes and Canada Post collects them, with a $9.95 handling fee included. Pay on Track or in the app before the parcel is out for delivery, otherwise at the door by credit card or at the post office. More in [parcels held at customs](guide:customs).

## Canada Post parcel late, missing or damaged

No new scan for days in transit is normal: Canada Post typically scans when an item is mailed and when it's out for delivery. Canada Post says most packages arrive no later than 5 business days after the expected delivery date.

After that, check the address and around your home, then ask for an investigation with a support ticket. Either you or the sender can open one, but only the sender can claim compensation. The sender has 90 days from the shipping date to claim in Canada, 6 months for US and international items.

Marked delivered but not there? Check the mailbox, parcel locker, concierge, mailroom and household, and look for a signature on Track, then ask for an investigation. Damaged: contact the sender, keep the packaging and take photos right away.

## Canada Post contact and customer service

Canada Post's channels, as read on 15 October 2026:

- **Phone** 1-866-607-6301 within Canada, 1-416-979-3033 from outside Canada, TTY 1-800-267-2797. Monday to Friday 7am to 11pm ET, weekends 9am to 9pm ET.
- **Support ticket** on the [support page](https://www.canadapost-postescanada.ca/cpc/en/support.page), with or without signing in, and **chat** there too.
- Local post offices often can't take phone calls.

> Canada Post warns that texts saying a delivery is on hold for unpaid fees, with a payment link, aren't from Canada Post. Its texts come only from 272727 or 55555 (if you signed up) or its support numbers 613-734-1002 and 613-734-1004, and its links point to canadapost-postescanada.ca. Forward spam to 7726.

## Tracking Canada Post parcels with Peek

[Peek](/) names Canada Post at once for postal numbers ending `CA` that pass their check. A 16-digit number with a valid check digit is shared, so Peek asks Canada Post, Correos Express, Evri UK, TNT and DHL eCommerce together (Canada Post leads the list for readers in Canada) and names the one that knows it; a 16-digit number that fails the check is never offered to Canada Post. Links with `searchFor=` work, but not Track's `#/search` links: paste the number. For a notice card number or a reference, pick Canada Post with "Change".

Peek shows the status, each scan with its town and province on a map, the expected delivery day, a delivery time window while one is given, the delivery time, the sender's business name and the service. Each scan keeps the clock of the place it happened. Statuses stay in Canada Post's English; delivered scans read "Delivered" and a parcel back with the sender "Returned to sender". Peek drops delivery options, addresses, signatures and delivery photos, and doesn't show the post office's name or the pickup deadline.

Peek checks every 10 minutes from 8am to 10pm Swiss time (about 2am to 4pm ET, 11pm to 1pm PT), every 2 minutes in those hours once the parcel is out for delivery, and hourly the rest of the time, and can notify you at every scan, at important steps or on delivery day only. When a parcel posted abroad with a postal number is bound for Canada, Peek can follow it on Canada Post once Canada Post shows progress.

## Questions about Canada Post tracking

### Why is my Canada Post tracking not updating?

Usually because Canada Post scans when an item is mailed and again when it's out for delivery, with little in between. If the expected date is more than 5 business days past, start an investigation. See [tracking that isn't updating](guide:tracking-not-updating).

### How long does Canada Post keep tracking information?

Track shows nothing for items shipped more than a year ago. A valid number can also show nothing before the parcel reaches Canada Post.

### Can I track with a Canada Post delivery notice card number?

Yes. Enter the 15-digit card number in Track and it shows the parcel and where to pick it up.

### What does "Notice left" mean?

Canada Post couldn't deliver the parcel and took it to a post office. The card or Track names the office; you have 15 calendar days to pick it up.

:::sources
- [Canada Post: Where to find your tracking number](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/find-your-tracking-number.page) – shapes, examples, only the sender has it
- [Canada Post: How to use Track](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/how-to-use-track.page) – card numbers, references
- [Canada Post: Tracking statuses](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-statuses.page) – status meanings, pickup ID
- [Canada Post: Track](https://www.canadapost-postescanada.ca/track-reperage/en) – labels, not-found reasons
- [Canada Post: Delivery options](https://www.canadapost-postescanada.ca/cpc/en/support/articles/parcel-services-shipping-in-canada/delivery-options.page) – attempts, cards, 5 and 15 days
- [Canada Post: Why wasn't my parcel delivered to my parcel locker?](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/parcel-lockers/why-wasnt-my-parcel-delivered.page) – lockers
- [Canada Post: Delivery was attempted but I didn't get a notice card](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/delivery-faq/i-didnt-get-a-missed-delivery-notice.page) – missing cards
- [Canada Post: Why is my tracking status not updating?](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-status-not-updating.page) – scan frequency
- [Canada Post: How do I pay duty and taxes?](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/delivery-faq/how-to-pay-duty-and-taxes-online-canada-post.page) – $9.95 fee, payment
- [Canada Post: My package hasn't arrived](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page) – 5 business days, investigations, deadlines
- [Canada Post: Find a missing package that shows as Delivered](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/missing-package-showing-as-delivered.page) – checks
- [Canada Post: My package arrived damaged](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/damaged-packages.page) – damage
- [Canada Post: Support](https://www.canadapost-postescanada.ca/cpc/en/support.page) – phone, hours, tickets, chat
- [Canada Post: Recognizing spam, fraudulent emails and text messages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/company-policies/privacy-security/what-do-to-if-you-get-suspicious-email.page) – fake texts
- [Canada Post: Responsible business practices](https://www.canadapost-postescanada.ca/cpc/en/our-company/corporate-sustainability/responsible-business-practices.page) – Crown corporation
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
