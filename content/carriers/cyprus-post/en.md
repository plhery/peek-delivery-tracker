---
title: Cyprus Post tracking: numbers, statuses and collection
description: Track a Cyprus Post item: what its barcode looks like, what the English UPU statuses mean, collecting from the post office or Parcel24 and who to call.
slug: cyprus-post-tracking
published: 2026-10-13
updated: 2026-10-27
---

Cyprus Post (Κυπριακά Ταχυδρομεία) is the Republic of Cyprus's national post, the Department of Postal Services. Track an item on its [Track & Trace page](https://www.cypruspost.post/en/track-n-trace-results), in its free app or by pasting the number in the box above. The number comes from the sender: the shop or person who posted the item.

## Cyprus Post tracking number

Cyprus Post's tracker reads the barcodes of registered items, EMS items, parcels and EPG parcels. They are 13 characters: [two letters, nine digits and two letters](guide:tracking-number-formats) for the country of origin.

:::anatomy RR 000000005 CY
- RR | Item category: the first letter says what kind of item it is
- 000000005 | Nine digits, the last one a check digit
- CY | Country of origin: CY for an item posted in Cyprus
:::

| First letter (invented examples) | What it is |
| --- | --- |
| `RR000000005CY` | R: registered item |
| `CP000000005CY` | C: parcel (CE for premium parcels) |
| `EE000000005CY` | E: EMS item |
| L | Express item |

An item posted abroad keeps the number its own post gave it, ending in that country's letters (`…GB`, `…FR`, `…GR`). Cyprus Post's tracker takes it and shows the foreign post's scans too.

A 12-digit number on a parcel from Germany is Deutsche Post's internal reference: Cyprus Post asks for the `CX…DE` barcode, which [DHL](carrier:dhl)'s German tracking gives.

No result? Type the number in English letters, with no spaces, and check it with the sender. No number at all? See [where to find a tracking number](guide:find-tracking-number).

## What Cyprus Post tracking statuses mean

Cyprus Post's statuses are the [international postal (UPU) event names](guide:tracking-statuses), in English on both the English and the Greek site; only the column headings change. `(Otb)` marks a step in the posting country, `(Inb)` one in the destination country. For an item coming to you:

| Cyprus Post's status | What it means for you |
| --- | --- |
| `Receive item from customer (Otb)` | The item was posted, in Cyprus or abroad. |
| `Insert item into bag (Otb)` | Bagged at the office of exchange to go abroad. |
| `Receive item at office of exchange (Inb)` | It has reached the destination country. |
| `Send items to delivery point/ collection point` | On its way to the office that will deliver it or hold it for you. |
| `Hold item at point of delivery (Inb)` | At the delivery office, waiting for its round. |
| `Handover item to delivery agent (Inb)` | With the postman. |
| `Item not delivered/ Pending delivery` | Not delivered: the Extra Information column says why. |
| `Receive item at collection point for pick-up (Inb)` | Ready for pickup at the post office. |
| `Deliver item (Inb)` | Delivered, or collected at the post office. |

An item with goods sent from outside the EU, even one bought from an EU shop, also goes through customs examination: where possible before it reaches Cyprus, and without you having to be there.

After a missed delivery, read Extra Information: `No home delivery: registered/insured/COD taxes` means that kind of item isn't brought to the door, and `Addressee advised to pick up the item` means you've been told to pick it up.

If `Insert item into bag (Otb)` is the last step for an item leaving Cyprus, check the destination post's tracking. `There is no information for this item` means the number isn't in Cyprus Post's system.

## Cyprus Post delivery, collection notices and Parcel24

Registered mail isn't delivered at home. You get a printed or SMS notice and pick the item up at your local post office with a valid photo ID (identity card, passport, driving licence). Items held for customs are picked up at the delivery post office named on the notice. Small packets go in your post box.

Once Extra Information shows the collection entry, you can pick it up without the notice once it has had time to be processed. Someone else can pick it up with their own ID and a written authorisation, or just your Cyprus Post SMS forwarded to them.

**Holding time:** 30 days from the notice. After that the item goes back to the sender, or for parcels the sender's instructions apply.

**Delivery Choice:** for parcels and EMS items (C and E barcodes) not subject to customs, with your mobile number or email in the item's data, Cyprus Post sends a link when the item arrives in Cyprus. Until 6:00 the next morning you can pick a Parcel24 locker in your district, the post office for your postcode, another address or home delivery. It's free and optional: without a choice, the item goes to the address on the label.

**Parcel24 lockers** open 24/7 in the Lefkosia, Lemesos, Larnaka, Pafos and Ammochostos districts and keep an item for 72 hours.

## Cyprus Post parcel stuck, lost or damaged

Cyprus Post's FAQ leaves inquiries to the sender: whoever posted the item fills in the Postal Item Inquiry form. For an item from abroad, ask the shop, which asks its own post. An inquiry has to be made within 3 months of posting for EMS items and within 6 months for parcels and registered items. Cyprus Post warns of delays around Christmas. See [tracking that isn't updating](guide:tracking-not-updating).

**[Shown as delivered](guide:delivered-not-received) to someone else?** Check the number with the sender, then use Cyprus Post's contact form.

**Damaged item from abroad:** bring it to any post office within 3 working days of picking it up, in its packaging, for a Damage Report, then tell the sender. If the box looks damaged, check the contents when you pick it up and ask for a damage report there. Compensation for loss or damage goes to the sender, who can waive it in your favour; there is none for delay.

**From outside the EU:** the Department of Customs sets duties and taxes. Upload documents or pay in advance on ThalisUpp, or pay when you pick the item up. Cyprus Post's FAQ puts its presentation-to-customs fee, on items owing duties or taxes, at €3.50 per item at the moment. A request to pay doesn't mean the item is already in Cyprus. More in [parcels held at customs](guide:customs).

## Cyprus Post contact number and customer service

Cyprus Post's channels, as its contact page listed them on 13 October 2026:

- **Call centre:** toll-free 8000 2000 in Cyprus, +357 22310100 from abroad, open 24/7.
- **Contact form** on the [contact page](https://www.cypruspost.post/en/contact), with "Track & trace" as the subject and the barcode in your message.
- **Post offices:** 8.00am to 2.30pm, Monday to Friday; find them on its [post office map](https://www.cypruspost.post/en/post-office-locations) or in the app.

> Cyprus Post never asks for bank or card details, never calls from a non-Cypriot number and never asks for payment to solve a problem: a message saying your item goes back unless you pay is fraud. It does send SMS and emails with links for Delivery Choice; its official domain is cypruspost.post.

## Tracking Cyprus Post parcels with Peek

For a postal number ending `CY` with a valid check digit, [Peek](/) asks Cyprus Post and [Chronopost](carrier:chronopost) and shows "Cyprus Post has this parcel." when Cyprus Post knows the number, or "Not found at … yet. Peek will keep checking after you add it." With a wrong check digit, Cyprus Post isn't asked: check the number, or pick the carrier with "Change". A cypruspost.post link works when it holds the number.

Peek doesn't name Cyprus Post for an item posted abroad, but Cyprus Post's lookup takes it: pick "Cyprus Post" with "Change". Peek switches to Cyprus Post by itself only when the carrier abroad names it.

Peek shows Cyprus Post's statuses as written, the office or facility and country of each scan on a map, the delivery time and the post office where an item waits or was collected. Cypriot scans keep their exact time, and a foreign post's are read on its country's clock; where a country has several time zones, Peek works the zone out from the place named, which can put the hour off. Peek checks every 10 minutes by day, every 2 once the item is with the postman, and every hour or two overnight, and can notify you when the status changes. It shows no expected delivery or weight, and drops the Extra Information, next office, postcodes and who signed.

## Questions about Cyprus Post tracking

### How long does Cyprus Post take to deliver?

Cyprus Post publishes no delivery times for incoming items. Go by the tracking: shops' estimated dates aren't based on it.

### Can I track an item with a U barcode?

Usually not. A U barcode only shows that the item contains goods; if customs checks the item, it can be traced.

### Is the Cyprus Post office open today?

Post offices open 8.00am to 2.30pm, Monday to Friday; the call centre, 24/7.

### Does Cyprus Post have an email address?

Its contact page lists no customer email. Use the contact form there, with your barcode in the message.

:::sources
- [Cyprus Post: FAQs, tracking and general postal questions](https://www.cypruspost.post/en/faq-track-trace) – barcode forms, columns, non-delivery, German numbers, delays, inquiry periods
- [Cyprus Post: Track and Trace](https://www.cypruspost.post/en/track-n-trace-results) – tracking form, live wordings, foreign scans
- [Cyprus Post: FAQs, receiving parcels and letters](https://www.cypruspost.post/en/faq-receive-items) – collection, 30 days, ID, authorisation, Delivery Choice, customs examination of items sent from outside the EU, customs fee, damage report
- [Cyprus Post: Delivery Choice](https://www.cypruspost.post/en/news/delivery-choice) – choice link and options
- [Cyprus Post: Parcel24](https://www.cypruspost.post/en/parcel24) – lockers, 72 hours
- [Cyprus Post: Thalis](https://www.cypruspost.post/en/thalis) – small packets, customs items at post offices, customs examination before arrival and without the addressee
- [Cyprus Post: ThalisUpp](https://www.cypruspost.post/en/thalisupp) – customs documents and payment
- [Cyprus Post: FAQs, compensations](https://www.cypruspost.post/en/faq-compensations) – compensation to the sender, exclusions
- [Cyprus Post: Contact](https://www.cypruspost.post/en/contact) – call centre, form
- [Cyprus Post: FAQs, other information](https://www.cypruspost.post/en/faq-other-info) – opening hours, 24/7 call centre
- [Cyprus Post: Spam alert](https://www.cypruspost.post/en/news/spam-alert) – fake messages
- [Cyprus Post: Mobile app](https://www.cypruspost.post/en/MobileApp) – app tracking
- [Cyprus Post: About us](https://www.cypruspost.post/en/about-us) – Department of Postal Services
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
