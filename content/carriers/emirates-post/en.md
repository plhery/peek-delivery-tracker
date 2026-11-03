---
title: Emirates Post tracking: AE numbers and statuses explained
description: Track an Emirates Post item sent from or within the UAE: what an AE number looks like, what each status means and how Peek follows it to your country's post.
slug: emirates-post-tracking
published: 2026-11-02
updated: 2026-11-02
---

Emirates Post (بريد الإمارات) is the post of the United Arab Emirates; Peek's catalog also knows it as Emirates Post Group. Its postal numbers end in `AE`, and its tracker names the place of most steps: in the UAE and, for an item sent abroad, in the destination country too. Paste the number in the box above, or look it up on Emirates Post's own tracker at emiratespost.ae. Peek couldn't check Emirates Post's website for this page, so what follows is what Peek's code knows about its tracker.

## Emirates Post tracking number: what it looks like

An Emirates Post postal number has 13 characters in the [international postal format](guide:tracking-number-formats): two letters, nine digits and `AE`, the code for the UAE. The last of the nine digits is a check digit. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `EE000000005AE` | Starts with C, E, L or R and ends in `AE`, with a check digit that adds up: Peek names Emirates Post at once. |
| `UA000000005AE` | Ends in `AE` but starts with another letter. Its shape alone doesn't make it Emirates Post's, so Peek first shows it as "Unknown postal carrier": see below. |
| `EE000000001AE` | The first shape with a wrong check digit. Peek recognises no carrier: check for a typo. |
| `0000000000000` | 13 digits. Peek can look these up on Emirates Post's tracker, but other carriers use 13 digits too, so pick Emirates Post yourself. |
| `RR000000005CN` | Ends in other letters, here `CN`: another post issued it, here [China Post](carrier:china-post). |

Spaces and lower case don't matter when you paste a number into Peek. Can't find yours? See [how to find a tracking number](guide:find-tracking-number).

## Emirates Post tracking statuses explained

Emirates Post's tracker gives each step a date, a time and, mostly, a place. The steps Peek knows, roughly in the order an item meets them:

| Emirates Post says | What it means for you | Peek shows |
| --- | --- | --- |
| `Yet to be received` | Emirates Post has the item's details, but nobody has handed it in yet. It can show days before the item is posted. | "Label created" |
| `Received at counter` | Posted at an Emirates Post branch. | "Received by carrier" |
| `Received - shipment under processing`, `Shipment under Processing` | Being processed after posting. | "In transit" |
| `Item into sorting centre` | At a sorting centre. | "In transit" |
| `Shipped to` | On its way to the place named after it: the next office, the destination city for an item going abroad, or the next place there after import customs. | "In transit" |
| `Arrived at` | At the place named after it: a delivery centre, or the destination country for an item sent abroad. | "In transit" |
| `Item presented to export Customs/Security` | With export customs or security checks before it leaves. | "At customs" |
| `Item returned from export Customs/Security` | Cleared: moving again. | "In transit" |
| `Item presented to import Customs` | With customs in the destination country. | "At customs" |
| `Import terminated` | The destination stopped the import. Later steps can still follow. | "Needs attention" |
| `Item held at delivery depot` | The delivery depot in the destination country has it. The line doesn't say you can collect it. | "In transit" |
| `Ready for collection by customer` | Waiting for you to collect it. | "Ready for pickup" |
| `Out for delivery` | Out for delivery. | "Out for delivery" |
| `Delivered` | Delivered. | "Delivered" |

Peek reads any other step by its wording and shows it in Emirates Post's words. More in [what tracking statuses mean](guide:tracking-statuses).

## Emirates Post items sent abroad: who delivers and customs

Once an item leaves the UAE, `Shipped to` names where it's going. Emirates Post's tracker can then go on with steps in the destination country: arrival, import customs and the delivery depot. In many countries, Peek also asks your national post with the same `AE` number, such as [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [An Post](carrier:an-post), [Canada Post](carrier:canada-post) or [Australia Post](carrier:australia-post): see the last section. Delivery attempts and where a missed parcel waits then follow the rules of whoever has it in your country: see [missed a delivery](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

**EU customs.** The UAE is outside the EU, so a parcel goes through customs in your country. Since 1 July 2026, goods bought online from outside the EU in orders up to €150 carry a €3 EU customs duty per item. Since 1 November 2026, a €2 Union handling fee per item applies as well, whatever the value. An item is the goods in a parcel that share a tariff classification, description and, where required, origin. Peek couldn't check how these are charged on an Emirates Post parcel: ask the shop whether your price included them. Elsewhere, your own country's rules apply: see [parcels held at customs](guide:customs).

## Receiving an Emirates Post item in the UAE

Emirates Post's steps name the office that has the item, such as a sorting centre or a delivery centre. When it waits for you, the step reads `Ready for collection by customer`. Peek couldn't check Emirates Post's website or UAE customs rules on an official page, so this page gives no holding period, delivery rules or customs fees.

**From abroad.** A parcel posted to you in the UAE carries the sending post's number. Peek follows it with that post, or with tracking services, and doesn't hand it over to Emirates Post. It asks Emirates Post's tracker about that number only if you pick Emirates Post with "Change".

## Emirates Post tracking not updating, lost or returned

**Quiet after it left the UAE.** The flight and customs take time. After four days without a new step, Peek flags the parcel ("No tracking update for four days"). Check your country's post with the same number; Peek does it for you where it can. More in [why tracking stops updating](guide:tracking-not-updating).

**Delivered, but nothing came.** Ask whoever delivered it in your country, and tell the sender. See [delivered but not received](guide:delivered-not-received).

**Lost or sent back.** Ask the sender or the shop first: they booked the delivery. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## Emirates Post customer service and contact

As of 31 October 2026, Emirates Post's website answered Peek's check with a browser challenge, so this page lists no phone number, email or opening hours: Peek gives only contacts read on a carrier's own pages.

For an order from a shop, ask the shop first: it booked the delivery. Once your country's post has the parcel, ask that post about the delivery itself.

## Track an Emirates Post parcel with Peek

[Peek](/) names Emirates Post at once for two letters, nine digits and `AE` starting with C, E, L or R, when the check digit is right, and shows "Detected carrier"; "Change" picks another. With a wrong check digit, it says "This number’s check digit doesn’t add up.": check the number, or choose "Track it as typed". A link to Emirates Post's tracker works too: Peek takes the number from it. Filed under Emirates Post, a parcel shows "Waiting for the carrier" until Emirates Post lists it.

An `AE` number starting with another letter, or a 13-digit number, isn't Emirates Post's by shape alone. Paste it all the same: Peek follows more than 3,500 carriers. For the `AE` number, Peek shows "Unknown postal carrier" and "Checking tracking services…" and, when no service has the parcel, asks Emirates Post, then shows "Emirates Post has this parcel." if it does. If a service has it, Peek follows it there; in the parcel's first 30 days, later checks can still ask Emirates Post, and Peek moves the parcel there once Emirates Post shows it moving. For 13 digits, or to go straight to Emirates Post's tracker, pick Emirates Post with "Change".

Emirates Post's tracker puts a browser check in front of its page, so Peek reads it through a browser. Peek shows each step in Emirates Post's English wording, or in Arabic when the tracker gives no English, with its place. It leaves out the sender's and recipient's details, contact details and links. The tracker gives no expected delivery date or weight, so Peek shows none. Peek reads a step at an office in Dubai, Abu Dhabi, Sharjah, Ajman, Fujairah, Umm Al Quwain or Ras Al Khaimah on UAE time, and a step elsewhere on the clock of the place it names, or of the steps around it (the destination's once a step says the item has arrived). "Open the Emirates Post website" on the parcel's page opens the number on Emirates Post's tracker. When the tracker can't be read, Peek asks tracking services instead, ParcelsApp, Ship24 and 17TRACK in turn, and for an `AE` number the Universal Postal Union's tracking last.

When Emirates Post's steps name the destination country, or name no country and you track from outside the UAE, Peek also asks that country's post with the same `AE` number: USPS, Royal Mail, An Post, Canada Post, Australia Post, NZ Post, [DHL](carrier:dhl) in Germany, [La Poste](carrier:la-poste) in France and every other EU country's post, and the posts of Switzerland, Norway, Saudi Arabia, Pakistan, the Philippines, Egypt, Jordan, Kenya and Bangladesh, among others. Once that post shows a scan at least as recent as Emirates Post's newest, Peek follows the parcel there, keeps Emirates Post's history, and the card says "Delivery with USPS", for example. Peek knows no post to ask in some countries, such as India, Sri Lanka, China, Oman, Qatar, Kuwait or Bahrain: there it stays with Emirates Post's tracker. A parcel still shown as "Unknown postal carrier" gets this only when a tracking service that has it names the destination country: Peek then asks that country's post the same way.

Peek checks every 10 minutes, every 5 minutes once out for delivery, as each look at Emirates Post's tracker opens a browser, and hourly after two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"). It can notify you of "Every scan", "Important steps" or "Delivery only". Peek can't contact Emirates Post, your post or the shop for you.

## Questions about Emirates Post tracking

### What does an Emirates Post tracking number look like?

Two letters, nine digits and `AE`, such as `EE000000005AE`. Peek names Emirates Post at once when the number starts with C, E, L or R and the check digit adds up.

### Why is my Emirates Post tracking stuck on "Yet to be received"?

Emirates Post has the item's details, but it hasn't been handed in at a counter yet: the step can show days before posting. Ask the sender when they'll post it.

### What does "Import terminated" mean on Emirates Post tracking?

The destination country stopped the item's import. Peek shows it as "Needs attention". Later steps can still follow, so keep watching, and ask the sender or the shop what happened.

### Can I track an Emirates Post parcel with my own country's post?

In many countries, yes, with the same `AE` number. For a parcel it follows on Emirates Post's tracker, Peek asks your post for you in the US, the UK, Ireland, Canada, Australia, every EU country and some others, and follows the parcel there once that post shows a fresh scan.

:::sources
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty per item up to €150 since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per item whatever the value, what an item is
- [Universal Parcel Scraper: Emirates Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/emirates-post) – names, number shapes and check digit, 13-digit numbers, the tracker read through a browser, step wordings and their stages, places, clocks, destination, what is left out, tracking links
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – China Post's numbers, carriers asked for other `AE` numbers, order of tracking services, hand-off to the post in your country, none to Emirates Post
:::
