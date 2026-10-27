---
title: Posti tracking: item ID, statuses, lockers and contact
description: Track a Posti parcel in Finland: what the JJFI item ID looks like, what each English status means, how long lockers keep parcels and how to reach Posti.
slug: posti-tracking
published: 2026-10-12
updated: 2026-10-27
---

Posti is Finland's postal and parcel company. Track a parcel on [Posti's tracking page](https://www.posti.fi/en/tracking) or in Posti's OmaPosti app with its item ID, or paste the number in the box above. The number comes from the sender, and it is also on Posti's notice of arrival in OmaPosti, by SMS or by email.

## Posti tracking number: the JJFI item ID

Posti calls the number the "item ID" (sometimes "shipment ID"). For Posti's own parcels it starts with `JJFI`. Posti's site has an English version ("EN" at the top).

| Number (invented) | What it is |
| --- | --- |
| `JJFI12345678901234567` | A Posti parcel: `JJFI` and 17 digits, 21 characters in all. |
| `RR000000005FI` | A registered or tracked item posted in Finland, in the international format: two letters, nine digits, `FI`. |
| `RR000000005CN` | A parcel from abroad keeps the sender's number. This one ends `CN`, so it was posted with China Post; Posti tracks it under the same number. |
| `00064000000000000002` | A 20-digit SSCC barcode; `64` after the first three digits is Finland's prefix. |

If the sender gave Posti your phone number, the parcel appears in OmaPosti by itself; otherwise add it by hand. Posti's tracking only answers an exact code, never a name or address.

The pickup code and the locker opening code (6 or 9 characters) are not tracking numbers. A number starting `JJEE` belongs to [Omniva](carrier:omniva), Estonia's post, and one ending `SE` to [PostNord](carrier:postnord) in Sweden. Not sure which carrier you have? See [tracking number formats](guide:tracking-number-formats).

## Posti tracking statuses in English

Posti's tracking shows its wordings in English, Finnish or Swedish, following the page language, often with a line explaining the step. These are the ones a parcel usually meets, roughly in order:

| Posti says | What it means for you |
| --- | --- |
| `We have received information about an upcoming delivery from the sender` | The sender announced the parcel. Posti doesn't have it yet. |
| `Item received for transport` | Posti has the parcel. |
| `Item is in transport in country of origin.` | Posted abroad. No delivery estimate yet. |
| `Item arrived in the destination country.` | It has reached Finland and goes on as a Posti delivery. |
| `The item is in sorting` | At a sorting centre. Parcels are usually sorted once or twice, more on long routes. |
| `Item accepted from transport` | Still on its way. Not delivered, despite the wording. |
| `Item is routed to an outlet because the locker was full.` | Your chosen locker was full, so it goes to a Posti outlet instead. |
| `Item is ready for a pick up` | Waiting for you. The notice of arrival has the place and code. |
| `We sent the recipient a text message about the item` | Posti texted you. The parcel hasn't moved. |
| `The item has been delivered` | Delivered, including after you picked it up. |
| `Item delivered to the recipient.` | Delivered to you. |

`Item accepted from transport` confuses many people. Posti says it is a transport marking: delivery is still in progress and nobody has handed the parcel over yet. Scans abroad carry the place `ULKOMAILLA`, Finnish for "abroad", in every language.

## Posti parcel lockers, pickup and missed deliveries

**Notice of arrival.** When a parcel reaches a Parcel Locker or service point, Posti sends the details and the opening code in OmaPosti, by SMS and by email. Pick it up within 5 days. Some parcels wait longer, so trust the last pickup date on your notice.

**More time.** In OmaPosti, "Extend pickup time" adds up to 14 days from the original last pickup date, for €1.90 according to Posti's English page, €1.90 a day according to its Finnish one.

**At the locker.** Type the opening code (on keyboard lockers: C, the code, then OK). Anyone with the code can pick up for you, no ID needed. Registered and insured items need a power of attorney. Items without a pickup code wait at a Posti outlet: bring the item ID and an identity document.

**Why it went elsewhere.** A parcel too big for the locker, needing a personal handover or sent with a wrong phone number goes to a Posti outlet instead.

**Home delivery.** If Posti can't deliver to your door, it takes the parcel to the nearest pickup location and tells you in OmaPosti, by SMS or by email. A Small parcel is left at the door or in the mailbox when nobody is home. In OmaPosti you can switch to home delivery only while the sender still has the parcel.

**Changes.** Only the sender can change the address. Customer service can move a parcel to another pickup point for a charge. A parcel nobody picks up goes back to the sender.

## Posti parcel delayed, lost, damaged or at customs

**No update.** Until a parcel from abroad reaches Finland, Posti can't estimate when it arrives. See [why tracking stops updating](guide:tracking-not-updating).

**Lost or late.** If tracking shows no sign of the parcel, contact Posti's customer service for a clarification request; sender or recipient can ask. If it isn't found, the sender claims compensation, because Posti pays whoever paid for the transport, almost always the sender. The online shop is responsible for the transport under Finnish law and usually sends a replacement. Finland's consumer authority, KKV, counts a domestic parcel as lost 14 days after the agreed delivery period, or 28 days after collection if none was agreed.

**Delivered but not there.** Check the mailbox for a Small parcel, then contact customer service. See [delivered but not received](guide:delivered-not-received).

**Damaged.** Tell the driver at handover, take photos, use Posti's damaged item form and tell the sender.

**Customs.** A parcel from outside the EU may need clearing before delivery, on Finnish Customs' site or in OmaPosti. It waits 20 days in Posti's warehouse, then goes back. When taxes are due at clearance, Posti adds a €3.10 handling fee (€3.30 at an outlet), paid on Posti's own website. From 1 November 2026 Finnish Customs also charges €2 per goods item. Current fees are on [Posti's customs page](https://www.posti.fi/en/receiving/customs-clearance).

> Posti never sends a link to pay customs or a handling fee. A fake message can even contain a genuine tracking code: delete it without opening the link.

## Posti customer service and contact number

As listed on posti.fi on 12 October 2026:

- **Phone:** 0100 5577, or +358 100 5577 from abroad. Calls cost a mobile or local network charge. Posti's [customer service page](https://www.posti.fi/en/customer-service) gives the opening hours.
- **Chat:** in the OmaPosti app.
- **Forms:** claim for compensation, report a damaged item, and undeliverable parcel (when Posti could deliver it neither to you nor back to the sender).

Posti lists no customer email address.

## Posti parcel tracking with Peek

[Peek](/) names a `JJFI` number as Posti's straight away. A posti.fi tracking link (`/tracking/`, `/seuranta/` or `/uppfoljning/` followed by the number, also after a language such as `/en/`) names Posti for any number. No postcode needed.

For a postal number ending `FI`, Peek asks Posti first, with Chronopost, and names Posti once it knows it. If Posti doesn't know it yet, you see "Not found at … yet. Peek will keep checking after you add it." Added to a Peek account, the parcel is then checked hourly for 6 hours, then less and less often; without an account, checks stop after 6 hours unless you turn on "Notify me when it’s found", which keeps a daily check going until 30 days after you added it. With a wrong check digit, or for an SSCC that several carriers know, pick Posti with "Change". A foreign number tracks at Posti after "Change" or from a posti.fi link, and Peek can follow some international parcels to Posti.

In Estonia, Latvia and Lithuania, Posti goes by SmartPosti: Peek's carrier search finds Posti under that name ("Also called SmartPosti"), and tracking links from smartposti.ee or itella.ee (or their .lv and .lt sites) name Posti for any code. Peek keeps Posti's answer only when exactly one shipment carries the exact code you typed, so for a SmartPosti code it may find nothing; then look the parcel up on smartposti.ee.

Peek reads Posti in English and shows each status with Posti's explanation line, the town of each scan, the weight, the dimensions and the pickup point's name and address. It checks every 10 minutes by day (every 2 once out for delivery) and every hour or two overnight, draws each scan on a map and can notify you of changes. It drops the pickup code and your address, and shows no expected delivery or sender. A number covering several parcels isn't tracked, and old parcels read as not found.

## Questions about Posti tracking

### How do I track a Posti parcel in English?

Open posti.fi/en/tracking and type the item ID; the statuses then show in English.

### How long does Posti keep a parcel at a parcel locker?

Posti asks you to pick it up within 5 days; the notice gives the exact last day. In OmaPosti you can extend it by up to 14 days for €1.90 (€1.90 a day, says Posti's Finnish page).

### How long does Posti take to deliver?

A Small parcel arrives 2 to 5 business days after Posti receives it. Otherwise, Posti's tracking shows an estimated delivery time.

### Does Posti customer service have chat?

Yes, in the OmaPosti app.

:::sources
- [Posti: Picking up an item](https://www.posti.fi/en/receiving/picking-up-an-item) – JJFI item ID, notices, storage, extension, lockers, codes, power of attorney, returns, phone, chat
- [Posti: Lähetyksen noutaminen](https://www.posti.fi/vastaanottaminen/lahetyksen-noutaminen) – in Finnish: extension €1.90 a day
- [Posti: Track item](https://www.posti.fi/en/tracking) – status wordings, explanation lines, ULKOMAILLA, languages
- [Posti: Home delivery](https://www.posti.fi/en/receiving/home-delivery) – missed delivery, Small parcel, delivery times, delivered not received
- [Posti: Customs clearance](https://www.posti.fi/en/receiving/customs-clearance) – handling fee, Finnish Customs, €2 fee from 1 November 2026, IOSS
- [Posti: Ordering from abroad and customs clearance](https://www.posti.fi/en/receiving/customs-clearance/ordering-from-abroard-and-custom-clearance) – 20 days in the warehouse, OmaPosti
- [Posti: What does Posti compensate for and to whom](https://www.posti.fi/en/customer-service/damage-situations/what-does-posti-compensate-for-and-to-whom) – clarification request, compensation, damage steps
- [Posti: Claim for compensation](https://www.posti.fi/en/customer-service/damage-situations/claim-for-compensation) – claim form
- [Posti: Report a damaged item](https://www.posti.fi/en/customer-service/damage-situations/report-damage) – damage form, notify the sender
- [Posti: Undeliverable parcel](https://www.posti.fi/en/customer-service/damage-situations/undeliverable-parcel) – undeliverable form
- [Posti: Security and phishing messages](https://www.posti.fi/en/customer-service/security-and-phishing-messages) – fake messages, no payment links
- [Posti: Privacy statement for Item Tracking](https://www.posti.fi/en/customer-service/terms-and-statements/data-protection/privacy-statements/privacy-statement-for-item-tracking) – tracking by exact code, call cost
- [Posti: Changes to parcel storage periods](https://www.posti.fi/en/latest-news-at-posti/changes-to-parcel-storage-periods-to-improve-availability-at-posti-parcel-lockers-11-8-2025) – five-day storage
- [Posti: Customer Service](https://www.posti.fi/en/customer-service) – customer service page
- [SmartPosti: Meist (About us)](https://www.smartposti.ee/ariklient/meist) – part of Posti Group, SmartPosti in Latvia and Lithuania
- [SmartPosti: Saadetise jälgimine](https://www.smartposti.ee/saadetise-jalgimine) – SmartPosti's tracking page
- [KKV: Postal and parcel services](https://www.kkv.fi/en/consumer-affairs/telephone-and-internet-subscriptions-postal-and-parcel-services/postal-and-parcel-services/) – when a parcel counts as lost
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
