---
title: Matkahuolto tracking: statuses, pickup points and contact
description: Track a Matkahuolto parcel in Finland: where the shipment ID is, what the English statuses mean, pickup codes, storage time, lockers and who to contact.
slug: matkahuolto-tracking
published: 2026-10-13
updated: 2026-10-26
---

Matkahuolto is a Finnish parcel carrier with parcel points and lockers across Finland, including some K-markets and R-kiosks. Track a parcel on matkahuolto.fi/tracking (in English) or in the Paketit app, with the shipment ID from the shop's shipping email or Matkahuolto's SMS, or paste it in the box above.

## Matkahuolto tracking number and where to find it

Matkahuolto calls it the shipment ID or consignment ID (lähetystunnus in Finnish). It's in the shop's shipping email and in every SMS or email from Matkahuolto. The pick-up code in the arrival notice is something else: it opens the locker, or you show it at the parcel point.

Matkahuolto doesn't publish its number formats. These are the shapes Peek's catalog knows (digits invented):

| Number | What it is |
| --- | --- |
| `MH000000005FI` | `MH`, 9 digits and `FI`. |
| `MA0000000000100000000` | `MA` followed by 19 digits. |
| `00373000000000000005` | A 20-digit parcel barcode (SSCC). Matkahuolto's tracking also finds it as 18 digits, without the `00`. |

Numbers starting `JJFI`, and [postal numbers](guide:tracking-number-formats) ending `FI` with letters other than `MH` in front (`RR000000005FI`), belong to [Posti](carrier:posti), the Finnish post. If Matkahuolto can't find it, check which carrier the shop's email names.

Moving a parcel to another pickup point creates a new consignment ID. A GLS parcel handed to Matkahuolto in Finland shows its Matkahuolto number on GLS's tracking as "Finland parcel number", starting with 00.

No number? If you're registered with Matkahuolto or use the Paketit app, parcels sent to your phone number or email appear there; if one doesn't, the sender can add your details. See [how to find your tracking number](guide:find-tracking-number).

## What Matkahuolto tracking statuses mean

Matkahuolto's tracking comes in Finnish (the default), Swedish and English, each scan with a date, time and town. The English wordings, in order:

| Matkahuolto's status | What it means for you |
| --- | --- |
| `Matkahuolto is waiting for the consignment from the sender` | The sender has [created the shipment](guide:tracking-statuses); Matkahuolto doesn't have the parcel yet. |
| `Consignment is being processed` | Scanned at a terminal. |
| `The consignment is on its way` | Moving between terminals. |
| `Your parcel has been sorted and will be taken next to the pickup point.` | Sorted at the last terminal; the pickup point is next. |
| `We will deliver the consignment to the pickup point during the day` | On its way to your pickup point today. |
| `Your pick up point has changed` | It's going to another point, named in the line. |
| `The shipment is available for pickup with the pickup code at the pick up point` | Ready for pickup, with the point's name and last pickup day. |
| `We have sent the recipient a message about the arrival of the consignment (sms).` | Your arrival notice went out. Not a movement. |
| `The consignment has been delivered. Thank you for choosing Matkahuolto!` | Picked up from the point or locker. |

**During the day.** The first point on the route may not be yours, Matkahuolto says, but the parcel should be ready for pickup the same day. You get a notice when it is.

## Matkahuolto pickup points, lockers and home parcels

**Picking up.** You need only the pick-up code from the arrival notice. No ID unless the notice marks a hand-delivered shipment, usually alcohol or documents. Anyone with the code can pick it up, except at app-based outdoor lockers, which open only with the recipient's own phone in the Paketit app. If the notice names no locker, ask at the parcel point's checkout.

**Arrival notice.** A Paketit app notification first, then SMS or WhatsApp, and email if the sender gave one.

**How long it waits.** 5 days, then it goes back to the sender; 14 days when the notice comes on paper because the sender gave no phone or email. You can buy 1 to 16 more days in the app or, if registered, on the website, unless the sender has blocked it. GLS's own page gives its parcels 10 days, so go by the last pickup day in your notice.

**Changing the point.** Once, free, in the app before Matkahuolto accepts the parcel; after the arrival notice, only as a paid transfer. Not while it's in transit.

**Home Parcels** come on weekdays between 8:00 and 18:00; Business Parcels and [FedEx](carrier:fedex) parcels come on weekdays between 8:00 and 16:00, without notice. A notice gives the planned day, which you can move to one of the next five business days on the day the notice arrives. The parcel is left in a safe, sheltered spot by the door; if that isn't possible and you can't be reached, it goes to a nearby pickup point the next business day.

**Returns.** Get an activation code with the Palauta button in the app or on the website, write it on the parcel with your name and phone number, and take it to a locker or parcel point.

## Matkahuolto parcel delayed, not updating or damaged

Most parcels arrive the day after dispatch and all within three working days, says Matkahuolto. Parcels from abroad can pass through other carriers first and take a little over a week to reach Matkahuolto.

**Not updating.** Tracking can pause, for example while the parcel waits to go out or its packaging is checked. If it still doesn't move, Matkahuolto asks you to contact the sender. See [tracking that isn't updating](guide:tracking-not-updating).

**No arrival notice.** Usually a typo in your contact details: check spam folders and ask the sender to correct them.

**Damaged or lost.** Tell the shop or sender without delay, with photos. The company that sent the parcel files any claim with Matkahuolto; between consumers, the sender does. Note visible damage when you pick it up; otherwise report it in writing within 7 days, or within 30 days as a consumer. Matkahuolto's liability is €20 per kg, or the goods' value if lower.

## Matkahuolto customer service and phone number

As listed on Matkahuolto's English pages on 13 October 2026:

- **Parcel Services** 0600 11300, Monday to Friday 8 to 18, €2.49 a minute plus local charge.
- **Parcel Services Support** +358 20 33 6161, Monday to Friday 8 to 10 and 14 to 16, at local rate, for lockers, app lockers and home parcels.
- **Chat** with the Mauno chatbot on the [parcel customer service page](https://www.matkahuolto.fi/customer-service/parcels), or in the Paketit app, where an agent may take over.
- **Feedback form** in Finnish at matkahuolto.fi/palautelomake. No email address for recipients is published.

> Matkahuolto warns of fake messages, faked sender names and lookalike sites such as matkahuoltoo.fi. It says it never relays payments or acts as a payment gateway; the payment link it does send goes to senders, for return costs. Check a message by typing the shipment ID into Matkahuolto's tracking or app; if you paid, call your bank.

## Tracking a Matkahuolto parcel with Peek

[Peek](/) asks Matkahuolto, Posti and [Chronopost](carrier:chronopost) about an `MH…FI` number and says "Matkahuolto has this parcel." when only Matkahuolto knows it; if Posti knows it too, you choose. An `MA` number goes to Matkahuolto alone, though about one in a thousand is taken for [Australia Post](carrier:australia-post)'s: use "Change". The 18 and 20-digit barcodes are offered to other carriers, never Matkahuolto, so pick Matkahuolto with "Change" or paste a matkahuolto.fi tracking link with `parcelNumber=` in it, which works with any number.

Matkahuolto's scans carry no time zone, so Peek doesn't show its scan list: it takes the dated history from [universal tracking services](guide:universal-tracker), asks Matkahuolto again every 6 hours and, if no service answers, shows Matkahuolto's current status, dated when Peek saw it. Statuses keep Matkahuolto's English wording. Peek can show the service name and the pickup point or locker with its town and street, and can notify you of every scan, important steps only or "Delivery day only".

Peek gets no expected arrival day or weight from Matkahuolto. It leaves out additional services, the last pickup day and postcodes, and doesn't follow the parcel to another carrier.

## Questions about Matkahuolto tracking

### Is Matkahuolto legit?

Yes, Matkahuolto is a real Finnish carrier. Treat a message asking you to pay in its name as a scam: Matkahuolto says it never relays payments.

### Can I track a Matkahuolto parcel in English?

Yes. Matkahuolto's tracking has an English version at matkahuolto.fi/tracking, and the Paketit app's language can be changed in its settings.

### Can I track a Matkahuolto parcel without the code?

Yes, if you're registered or use the Paketit app and the sender gave your phone number or email: the parcel appears there. Otherwise ask the shop for the shipment ID.

### How long does a Matkahuolto parcel take?

As a rule 1 to 2 working days to lockers and parcel points, and at most three working days within Finland. Parcels from abroad can take a little over a week to reach Matkahuolto.

:::sources
- [Matkahuolto: How to receive a parcel?](https://www.matkahuolto.fi/customer-service/parcels/how-to-receive-a-parcel) – pick-up code, ID, notices, point changes, home parcels, damage, phone numbers
- [Matkahuolto: Frequently asked questions](https://www.matkahuolto.fi/frequently-asked-questions) – home parcels, failed attempts, phone numbers
- [Matkahuolto: Track your parcel](https://www.matkahuolto.fi/customer-service/parcels/track-your-parcel) – tracking views, transit times, pauses, contact the sender
- [Matkahuolto: Receive your parcel easily](https://www.matkahuolto.fi/packages/receive-a-parcel) – 1 to 2 working days, notices, 1 to 16 extra days of storage
- [Matkahuolto: Pakettien säilytysaika muuttuu](https://www.matkahuolto.fi/news/pakettien-sailytysaika-muuttuu-tavoitteena-entistakin-sujuvampi) – 5 and 14 days, extensions
- [Matkahuolto: Domestic parcels](https://www.matkahuolto.fi/packages/domestic-parcels) – 5-day storage, return to sender
- [Matkahuolto: Parcel lockers and parcel points in Finland](https://www.matkahuolto.fi/packages/service-point-network) – network, app lockers
- [Matkahuolto: Transport damage guide for consumers](https://www.matkahuolto.fi/packages/damage-in-transit) – who claims, deadlines, photos
- [Matkahuolto: Kuljetusvahinko-ohje kuluttajille](https://www.matkahuolto.fi/paketit/kuljetusvahingot) – deadlines, €20 per kg
- [Matkahuolto: About scam messages](https://www.matkahuolto.fi/packages/about-scam-messages) – fake messages, genuine sender, shipment ID
- [Matkahuolto: Return the arrived parcel easily](https://www.matkahuolto.fi/packages/return-parcel) – returns with an activation code
- [Matkahuolto: Paketit app](https://www.matkahuolto.fi/packages/paketit-app) – app language, chat
- [Matkahuolto: Customer service for parcel services](https://www.matkahuolto.fi/customer-service/parcels) – Mauno chatbot
- [Matkahuolto: Usein kysyttyä](https://www.matkahuolto.fi/usein-kysyttya) – helpline price, three working days, Business Parcels and FedEx 8:00 to 16:00, feedback form
- [Matkahuolto: Seuraa pakettisi matkaa](https://www.matkahuolto.fi/asiakaspalvelu/paketit/seuraa-paketin-matkaa) – a little over a week from abroad, the senders' payment link for return costs
- [Matkahuolto: Track the parcel](https://www.matkahuolto.fi/tracking) – tracking in English, Finnish and Swedish, status wordings
- [GLS Finland: Lähetysseuranta](https://gls-group.com/FI/fi/laehetysseuranta.html) – Matkahuolto points, Finland parcel number, 10 days
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
