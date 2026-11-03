---
title: Canada Post tracking: numbers, statuses and notice cards
description: Track a Canada Post parcel: where the tracking number is, what Canada Post's statuses mean, notice cards and pickup deadlines, missing parcels and customs.
slug: canada-post-tracking
published: 2026-10-15
updated: 2026-10-27
---

Canada Post is the Canada Post Corporation, the federal Crown corporation that runs Canada's postal service. Track a parcel on Canada Post's [Track page](https://www.canadapost-postescanada.ca/track-reperage/en) or in its free app, or paste the number in the box above. The number comes from the sender, usually in the shop's shipping email: Canada Post can't find a parcel by your name or address. If it ends in `CA` and you follow the parcel from outside Canada, Peek can also ask your country's post with the same number.

## Canada Post tracking number: what it looks like

Peek matches these two shapes to Canada Post, here with made-up digits:

| Number (made up) | What it is |
| --- | --- |
| `1234 5678 9012 3452` | 16 digits; the last one is a check digit. [DHL eCommerce](carrier:dhl-ecommerce), [Correos Express](carrier:correos-express), [TNT](carrier:tnt) and [Evri](carrier:evri) also have 16-digit numbers. |
| `RR000000005CA` | An international postal number: 2 letters, 9 digits, `CA`. The last digit before `CA` is a check digit. |

Canada Post can also find a parcel by the 15-digit number of a delivery notice card, or by a reference made only of digits. For those, pick Canada Post yourself, as shown further down.

[Purolator](carrier:purolator), another Canadian carrier, has numbers of its own: 12 digits, or 3 letters and 9 digits. Peek doesn't ask Canada Post about those by itself. If Canada Post doesn't know your number, check which carrier the shop's email names. More in [how to find a tracking number](guide:find-tracking-number) and [tracking number formats](guide:tracking-number-formats).

## What Canada Post's tracking statuses mean

Peek reads Canada Post's scans in English. Below are the ones you'll meet most, word for word and roughly in order, with the step Peek shows.

**In Canada**

| Canada Post says | What it means for you | In Peek |
| --- | --- | --- |
| `Electronic information submitted by shipper` | The sender made a label, but Canada Post doesn't have the parcel yet. | "Label created" |
| `Item accepted at the Post Office` | It was posted at a post office. | "Received by carrier" |
| `Item picked up by Canada Post` | Canada Post collected it from the sender. | "Received by carrier" |
| `Item processed` | It's moving through Canada Post's network. The line can come several times. | "In transit" |
| `Item out for delivery` | It's on its way to you today. | "Out for delivery" |
| `Delivered to your community mailbox, parcel locker or apt./condo mailbox` | In your community mailbox, a parcel locker or your building's mailbox. | "Delivered" |
| `Delivered to recipient's safe drop location` | Left at your safe drop spot. | "Delivered" |
| `Notice card left indicating where and when to pick up item` | Delivery was attempted; the parcel goes to a post office, and the card says where and when to collect it. | "Delivery attempted" |
| `Item available for pickup at Post Office` | It's waiting for you at the post office. | "Ready for pickup" |
| `Final Notice; Item will be returned to sender if not collected within 10 days` | Last reminder: collect it within 10 days or it goes back. | "Ready for pickup" |
| `Recipient not located at address provided. Item being returned to sender.` | The recipient wasn't found at the address; the parcel is going back. | "Needs attention" |
| `Item has been returned and is enroute to the Sender` | It's on its way back to the sender. | "In transit" |

`Delivered to your concierge or building manager` and `Delivered to mailroom` also show as "Delivered". `Item re-routed due to processing error; Possible delay` and `Delivery may be delayed due to transportation delay` warn of a delay: "Needs attention". `Expected delivery date updated` gives a new expected day; the parcel hasn't moved.

**From abroad to Canada**

| Canada Post says | What it means for you | In Peek |
| --- | --- | --- |
| `International item mailed in originating country` | Posted abroad; Canada Post follows it under the sending post's number. | "Received by carrier" |
| `International item has left originating country and is en route to Canada` | On its way to Canada. | "In transit" |
| `Item has arrived in Canada and will be presented for review` | In Canada, waiting for customs. | "At customs" |
| `Item was released by Customs and is now with Canada Post for processing` | Customs is done; Canada Post takes over. | "In transit" |

**From Canada to another country**, `International item being forwarded to destination country` and `Item has arrived in foreign country` show as "In transit", and `Item presented to customs` as "At customs".

In the history, delivery scans read "Delivered"; the other scans keep Canada Post's English wording.

## Missed delivery, pickup and customs in Canada

**Missed delivery.** A parcel that needs a signature, or is too big for your mailbox or parcel compartment, is brought to your door. If no one answers, Canada Post leaves a Delivery Notice Card or, when no signature is needed, may leave the parcel in a safe place. After `Delivery Attempt Made / Rescheduled for delivery next business day`, it normally tries again the next business day.

**Notice card.** When a card is left, Track says "Notice left", then names the nearby post office and a "Pick up by" date once it knows them. A Final Notice Card follows after 5 calendar days; after 15 calendar days in all, the parcel goes back to the sender. More in [missed delivery](guide:missed-delivery) and [uncollected parcel](guide:uncollected-parcel).

**Picking it up.** Bring government-issued photo ID (Track asks for one showing the address on the parcel) and the card, the delivery email or the app's pickup barcode. Someone else can go with their own photo ID and your card, signed by you with their name printed on it, or a letter of authorization.

**Parcel lockers.** Canada Post leaves the key to a parcel locker in your mailbox. After 5 days in the locker, the parcel goes to the post office and you get a card.

**Customs on parcels from abroad.** Canada Post hands all international mail to the Canada Border Services Agency (CBSA) on arrival. While customs reviews a parcel, Canada Post says it can't step in or ask about it.

**Duty and taxes.** Goods by mail worth CAN$20 or less owe no duty or tax, and gifts from family or friends abroad owe no GST up to CAN$60. Above that, expect 5% GST, or HST where your province charges it; British Columbia, Manitoba, Quebec and Saskatchewan add provincial sales tax, and duty depends on the goods. The amount is on Form E14, the CBSA Postal Import Form, attached to the parcel. Canada Post collects it, $9.95 handling fee included. Pay with the "Pay" button on Track or in the app before the parcel is out for delivery; after that, pay by credit card at the door, or at the post office in cash, by card, cheque or Canada Post money order.

**Disagree with the amount?** Refuse the parcel and ask for a reassessment, or pay and ask for an adjustment on Form B2G, on the back of the E14. More in [parcels held at customs](guide:customs).

## Canada Post parcel sent abroad: who delivers it

**Who delivers.** With an international postal number ending `CA` and a valid check digit, Peek can also ask the post of the country you follow the parcel from, with the same number: for example [USPS](carrier:usps) in the United States, [Royal Mail](carrier:royal-mail) in the UK ([Parcelforce](carrier:parcelforce) for numbers starting `E`), [An Post](carrier:an-post) in Ireland, [Australia Post](carrier:australia-post), [NZ Post](carrier:nz-post), [La Poste](carrier:la-poste) in France and [DHL](carrier:dhl) in Germany.

Canada Post gives Peek no destination country, so your country decides: in the iPhone app, the region your iPhone is set to; in a browser, the country you added the parcel from. Once your post shows a scan at least as new as Canada Post's newest, Peek follows the parcel there and keeps Canada Post's history. For one more day it still asks Canada Post too, at most every 55 minutes. Followed from Canada, or with a 16-digit number, the parcel stays with Canada Post. For customs in the destination country, see [parcels held at customs](guide:customs).

## Canada Post parcel late, lost, returned or not there

**No new scans.** After four days without a new scan at "Label created", "Received by carrier" or "In transit", Peek says: "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Canada Post and shows the next scan here." It adds a link to help from the carrier that has the parcel, such as "Get help from Canada Post". Mail at customs in Canada can't be tracked precisely, the CBSA says. More in [tracking that isn't updating](guide:tracking-not-updating).

**Late or lost.** Canada Post says most late parcels arrive within 5 business days of the expected date. After that, check the address and around your home, then ask for an investigation with a support ticket on Canada Post's [missing packages page](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page) (sign in first to follow it), within 90 days of shipping, or 6 months for a parcel sent to the US or abroad. The sender can ask too, and only the sender can be paid: if the parcel doesn't turn up within a few days, Canada Post closes your inquiry and you then ask the sender to claim. If you bought online, start with the shop. More in [lost parcel](guide:lost-parcel).

**Delivered but not there.** Under the Canada Post Corporation Act, mail left in the usual way at your home or business, in your mailbox or with someone who may reasonably receive it for you counts as delivered. Peek shows "Delivered", but Canada Post's own tracking names the place, such as `Delivered to mailroom`: look there first. Then try the parcel locker, security desk or concierge and the people you live with, and check Track for a signature, before asking for an investigation. If your country's post delivered it, check its tracking. More in [delivered but not received](guide:delivered-not-received).

**Returned.** Once a returned parcel reaches the sender, Peek shows "Returned to sender". See [returned to sender](guide:returned-to-sender).

## Canada Post customer service and contact

- **Phone**: 1-866-607-6301 in Canada, 1-416-979-3033 from outside Canada, TTY 1-800-267-2797. Monday to Friday 7 am to 11 pm ET, weekends 9 am to 9 pm ET.
- **Online**: chat, or report a problem with a support ticket, on Canada Post's [support page](https://www.canadapost-postescanada.ca/cpc/en/support.page).

Once a parcel from Canada is with your country's post, ask that post about delivery and pickup; the pages on [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [An Post](carrier:an-post) and [Australia Post](carrier:australia-post) say how. Peek can't contact Canada Post or the shop for you.

> Texts saying a parcel is on hold for unpaid fees, with a link to pay, aren't from Canada Post. Its own texts come from 272727 or 55555 if you signed up, or from 613-734-1002 and 613-734-1004, with links to canadapost-postescanada.ca. Check fees on Track; forward spam texts to 7726.

## Tracking Canada Post parcels with Peek

[Peek](/) names Canada Post at once for international postal numbers ending `CA` with a valid check digit. A 16-digit number with a valid check digit fits several carriers: Peek shows "Checking tracking services…", asks Canada Post, DHL eCommerce, TNT, Correos Express and Evri at the same time, and takes the one that knows the number. For a notice card number or a reference, tap "Change" and pick Canada Post under "All carriers" or through the search. A Canada Post tracking link with `searchFor=` works too; links with `#/search` work only with postal numbers ending `CA`, so paste a 16-digit number yourself.

Peek asks Canada Post directly, without a browser. You see the status, the history with the place of each scan (town and province in Canada), the expected delivery day, a time window once the parcel is out for delivery in Canada and Canada Post gives one, the delivery time and, when Canada Post shows it in full, the sender's name. Peek leaves out delivery options, addresses, signatures and delivery photos.

Peek checks every 10 minutes, and every 2 minutes once Canada Post itself reports the parcel out for delivery that day. After 12 hours without a new scan at "Label created" or "Received by carrier", or 48 hours at any other step, it checks hourly. For notifications, choose "Every scan", "Important steps" or "Delivery only".

**Into Canada.** For a parcel posted abroad with an international postal number, Peek also asks Canada Post with the same number once the sending post names Canada as the destination or, when it names none, if you follow the parcel from Canada. Once Canada Post shows a scan at least as new as the sending post's newest, Peek follows the parcel there and keeps the earlier history. A number starting with `L` and ending `CH` that Peek follows with Swiss Post is never handed to Canada Post.

## Questions about Canada Post tracking

### Why is my Canada Post tracking not updating?

Long flights, customs and weekends often leave a gap between scans. Peek keeps asking, hourly once two days pass without news. If the expected delivery date is more than 5 business days past, ask Canada Post for an investigation. See [tracking that isn't updating](guide:tracking-not-updating).

### Why can't Peek find my Canada Post tracking number?

Canada Post gives the same answer whether a number is unknown, not scanned yet or expired, so on a Canada Post parcel Peek shows "Waiting for the carrier" until the first scan. Track's usual reasons: the parcel isn't with Canada Post yet or is still abroad, it was shipped more than a year ago, or the service has no tracking. Check every digit: in a 16-digit number the last one is a check digit, and in a postal number the last digit before `CA` is.

### Can I track a Canada Post parcel with the notice card number?

Yes. Paste the 15-digit number from the card, tap "Change" and pick Canada Post under "All carriers". Peek looks the parcel up on Canada Post with it.

### Who delivers a Canada Post parcel in the US or the UK?

Your country's post, if the number ends in `CA`: USPS in the US, Royal Mail in the UK, or Parcelforce for numbers starting `E`. Peek asks it with the same number. A 16-digit number stays with Canada Post.

:::sources
- [Canada Post: Track a package by tracking number](https://www.canadapost-postescanada.ca/track-reperage/en) – free app, "Notice left", pickup date and ID, why a number isn't found
- [Canada Post: Tracking - How to use Track](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/how-to-use-track.page) – only the sender has the number, no search by name or address, card numbers
- [Canada Post: Tracking - Tracking statuses](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-statuses.page) – "Notice left", pickup ID, proxies, next-day retry, customs
- [Canada Post: Parcel Services shipping in Canada - Delivery options](https://www.canadapost-postescanada.ca/cpc/en/support/articles/parcel-services-shipping-in-canada/delivery-options.page) – door attempts, safe drops, final notice after 5 days, 15-day hold
- [Canada Post Support: Why wasn't my parcel delivered to my parcel locker?](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/parcel-lockers/why-wasnt-my-parcel-delivered.page) – 5 days in the locker
- [Canada Post Support: How to pay duty and taxes](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/delivery-faq/how-to-pay-duty-and-taxes-online-canada-post.page) – $9.95 fee, how and when to pay
- [Canada Post: Claims - Missing packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page) – 5 business days, where to look, locker key, investigations, deadlines
- [Canada Post Support: Find answers, contact us or report a problem](https://www.canadapost-postescanada.ca/cpc/en/support.page) – phone, hours, chat, tickets
- [Canada Post Support: Recognizing spam, fraudulent emails and text messages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/company-policies/privacy-security/what-do-to-if-you-get-suspicious-email.page) – fake fee texts, real senders, 7726
- [Canada Post: Responsible business practices](https://www.canadapost-postescanada.ca/cpc/en/our-company/corporate-sustainability/responsible-business-practices.page) – federal Crown corporation
- [CBSA: Importing by mail](https://www.cbsa-asfc.gc.ca/import/personal-personnel/postal-postale-eng.html) – customs on mail, $20 exemption, Form E14, handling fee, no precise tracking
- [CBSA: Importing by mail or courier - Paying duty and/or taxes on imported goods](https://www.cbsa-asfc.gc.ca/import/postal-postale/dtytx-drttx-eng.html) – GST, HST and PST, CAN$20 and CAN$60 limits, duty
- [CBSA: Importing by mail or courier - Disputing duty and taxes](https://www.cbsa-asfc.gc.ca/import/postal-postale/dispute-contestation-eng.html) – reassessment, Form B2G
- [Justice Laws Website: Canada Post Corporation Act](https://laws-lois.justice.gc.ca/eng/acts/C-10/page-1.html) – the corporation, when mail counts as delivered
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shapes, wordings and their steps, what Peek shows, hand-offs
:::
