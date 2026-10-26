---
title: Express One tracking: statuses, parcel points, contacts
description: Track an Express One parcel in Hungary: where the number comes from, what the statuses mean, parcel points, lockers, missed deliveries and contacts.
slug: express-one-tracking
published: 2026-10-13
updated: 2026-10-26
---

Express One Hungary is a Hungarian courier owned by [Austrian Post](carrier:austrian-post), delivering for online shops with around 700 couriers and over 2,300 parcel points and lockers. Track a parcel by its package ID on [tracking.expressone.hu](https://tracking.expressone.hu/) or paste it in the box above. The number comes in the email Express One sends when the sender registers the parcel.

## Express One tracking number

Express One emails you a pre-notice once the sender registers the parcel; an SMS comes only if the sender pays for it. Within Hungary, the messages give the delivery window, the courier's contact, any cash-on-delivery amount and a link to change the delivery. Parcels going abroad get none. No message? Ask the shop, and see [how to find your tracking number](guide:find-tracking-number).

The tracker also takes the shipper's reference or document number, but those need the sender's 5-digit Company ID, so use the package ID (the barcode number).

Express One publishes no format. Package IDs seen so far come in two shapes (digits invented):

| Number | Whose it is |
| --- | --- |
| `1234567890123` | 13 digits: an Express One package ID, but [La Poste](carrier:la-poste) and other carriers use this length too. |
| `100000000000000000001011` | 24 digits, ending in a Hungarian postcode: Express One. [InPost](carrier:inpost), [bpost](carrier:bpost) and [Ciblex](carrier:ciblex) also use 24 digits. |
| `00000000000000` | 14 digits: DPD, never Express One. |
| `00000000000` | 11 or 12 digits: GLS, never Express One. |
| `RR000000005HU` | Two letters, 9 digits, `HU`: [Magyar Posta](carrier:magyar-posta), never Express One. |

Go by the carrier the shop's email names; more in [tracking number formats](guide:tracking-number-formats).

## What Express One tracking statuses mean

tracking.expressone.hu shows English or Hungarian, following your browser's language, with an EN/HU switch. Each scan has a code, a wording, a date and time, and hub and depot scans add the place in brackets, such as "(HU - Budapest)". The English wordings, in the order a parcel meets them:

| Express One's status | What it means for you |
| --- | --- |
| `Shipping details received, shipment not yet handed over to EXOHU` | The sender has [registered the parcel](guide:tracking-statuses); Express One doesn't have it yet. |
| `Shipment (pan) data uploaded` | The parcel's data is in Express One's system, usually right after a hub scan. |
| `Arrived at central hub` | At the central hub in Budapest. |
| `Departed central hub` | It has left the hub. |
| `Arrived at depot` | At the depot that delivers to your address. |
| `Out for delivery` | On the courier's van today. |
| `Delivery schedule updated` | The delivery plan was recalculated; nothing moved. |
| `Processing in progress` | The delivery moved to a later time, without a reason. |
| `Delivery failed - consignee not present at address` | The courier came and nobody was there. |
| `Consignee provided instructions for OOH shipment` | You chose a parcel point or locker through the change link. |
| `It can be handed over at the pick - up point` | Ready for pickup at the parcel point or locker. |
| `Successful delivery` | Delivered. |

EXOHU is Express One Hungary: the first status means only the label exists. The bar above the history has its own labels, from `Shipment data uploaded` to `Successfully delivered`. In Hungarian, delivered reads `Sikeres kézbesítés`.

## Express One delivery, parcel points and missed deliveries

**When it comes.** The pre-notice gives a 4-hour interval. On the delivery morning, a message gives a 2-hour window, which Express One calls only indicative: the courier may arrive earlier or later. That day the tracking page shows how many stops the courier has left before you. There's no weekend delivery, except paid Saturday delivery for business senders.

**Changing it.** The "Modification" button in the morning SMS or email lets you change the time or address, or send the parcel to a parcel shop or locker, which works even an hour before the courier arrives. Changes are possible only on the first delivery day and only once. Some options depend on the sender (a large parcel can't go to a locker), and Express One's terms say a change counts as a delivery attempt and may cost extra.

**At the door.** Private recipients sign and give an ID document number. Cash on delivery is paid in cash or by card, or in advance through a link in the pre-arrival message. You can't unpack before signing and paying.

**Missed delivery.** Under Express One's terms, it tells you by email or SMS by the next working day, tries once more for free and holds the parcel until the 5th working day at most. Meanwhile you can ask for a new date or a parcel point. After a second failed attempt the parcel goes back to the sender.

**Parcel points and lockers.** At an Exopoint parcel point or locker you can pick up the parcel for up to a week, 24/7. Many shops let you pick one at checkout. Picking up at the Budapest logistics centre (H-1239 Budapest, Európa street 12, building L1) needs an appointment with customer service.

## Express One parcel delayed, damaged or lost

**No update or not found.** Express One gives no waiting time. Its tracker says a number may be wrong, older than 180 days or not yet registered by the sender, and to try again later. Its terms let it erase the data if no parcel arrives within 5 working days, and call the expected time informational only. See [tracking that isn't updating](guide:tracking-not-updating).

**Damaged or wrong item.** Tell the shop you ordered from as soon as possible: Express One starts a complaint once the sender reports it, and is responsible for delivery, not contents. Note visible damage when the courier hands it over; report hidden damage in writing within 3 working days of delivery, or the right lapses.

**Lost.** The sender usually claims, though you can when the parcel was delivered to you or the sender assigned you the right in writing. A written claim for total loss is due within 6 months, counted from the 15th day after dispatch, with the waybill number, proof of contents and value and Express One's claim form. Express One answers complaints within 30 calendar days; if you're not satisfied, you can turn to the NMHH, Hungary's media and communications authority, within 30 days.

## Express One contact number and customer service

As listed on Express One's English contact page on 12 October 2026, on working days from 8 am to 5 pm (the FAQ says until 6 pm):

- **Customer service:** +36 1 8 777 400, ugyfelszolgalat@expressone.hu, also if your courier doesn't answer. In busy periods Express One suggests writing.
- **International customer service:** +36 1 8 777 410, intcs@expressone.hu; complaints intcsreklamacio@expressone.hu.
- **Damage claims:** +36 1 8 777 470, karugy@expressone.hu.
- **Contact form:** on [Express One's contact page](https://expressone.hu/en/kapcsolat), with subjects such as "I am expecting a parcel" and "Delivery complaint"; both need the parcel number.

Express One's Parcel Tracking page also lists +36 1 8 777 444 and a chatbot.

## Tracking Express One parcels with Peek

[Peek](/) can't tell an Express One number from its digits. A 13-digit number is offered to other carriers, a 24-digit one to InPost, bpost and Ciblex. When none knows it you'll see "not found yet · Peek keeps checking": pick "Express One" with "Change". Or paste a tracking.expressone.hu link with `?plc_number=`, which names Express One whatever the number.

Express One's scans carry a time without a time zone: Peek reads them on the clock of the scan's place, Hungarian time for scans in Hungary, and lists Express One's own history. From Express One it reads the status and, for hub scans, the town and country. Wordings stay as Express One writes them, except `Out for delivery`; `Successful delivery` counts as delivered and a return trip as returned. A pickup-point scan shows Express One's wording without a stage. You can be notified when the status changes. There's no expected delivery, weight or pickup point, and the progress bar and signature are left out.

## Questions about Express One tracking

### What does "shipment not yet handed over to EXOHU" mean?

The sender has created the shipment, but Express One Hungary hasn't received the parcel yet. If it stays there for days, ask the shop.

### How long does an Express One parcel wait at a parcel point?

Up to one week, and you can pick it up at any hour.

### Is Express One in Slovakia or Slovenia the same company?

No. They also belong to Austrian Post but are separate companies; this page and Peek's "Express One" cover the Hungarian one.

:::sources
- [Express One: Parcel Tracking](https://expressone.hu/en/csomagkovetes) – courier's stops, call centre, chatbot
- [Express One: Tracking](https://tracking.expressone.hu/) – ID types, Company ID, not-found message, 180 days, statuses, languages
- [Express One: Frequently asked questions](https://expressone.hu/en/gyik) – modification, 2-hour window, Saturday, cash on delivery, damage, wrong item
- [Express One: Contact](https://expressone.hu/en/kapcsolat) – phones, emails, hours, form, logistics centre
- [Express One: Parcel point](https://expressone.hu/en/csomagpont) – 2,300 points and lockers, one week, checkout choice
- [Express One: Home](https://expressone.hu/en) – Austrian Post, 700 couriers, redirect up to an hour before
- [Express One: General Terms and Conditions](https://expressone.hu/downloads/General_Terms_and_Conditions_2026_08_15.pdf) – attempts, hold, ID, change fee, claims, complaints, NMHH, data erasure
- [Express One: Merre jár a csomagom?](https://expressone.hu/blog/merre-jar-a-csomagom-csomagkovetes-es-ertesitesi-rendszerek-alkalmazasa) – pre-notice, 4-hour and 2-hour windows, SMS option
- [Express One: eBOX GYIK](https://expressone.hu/ebox/faq) – notification contents, none for export
- [Austrian Post: Austrian Post Group acquires Hungarian parcel delivery provider](https://news.post.at/presse/en/post/id/2255390/) – wholly owned subsidiary
- [Austrian Post: International subsidiaries](https://post.at/en/j/c/careers-austrian-post-subsidiaries-international) – Express One companies in other countries
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
