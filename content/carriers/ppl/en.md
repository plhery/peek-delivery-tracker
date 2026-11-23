---
title: PPL tracking: shipment numbers, statuses and Parcelshops
description: Track a PPL parcel in Czechia: the 11-digit shipment number, what PPL's English statuses mean, Parcelshops, Parcelboxes, missed deliveries and contacts.
slug: ppl-tracking
published: 2026-10-12
updated: 2026-11-17
---

PPL (PPL CZ) is a Czech parcel carrier, part of DHL Group, that delivers to homes, Parcelshops and Parcelboxes across Czechia. Track a parcel with its 11-digit shipment number on [PPL's tracking page](https://www.ppl.cz/en/track-a-shipment), in the mojePPL app, or in the box above, then pick PPL. The number comes from the shop's shipping email or order page, or from PPL's own email and SMS.

## PPL tracking number and where to find it

PPL's help pages ask for "the 11-digit shipment number". PPL publishes no meaning for its parts:

| Example (invented) | What it is |
| --- | --- |
| `24829173640` | A PPL shipment number: 11 digits. |

PPL emails you when it accepts the parcel for transport, and sends an SMS and email when the parcel is ready for pickup. If you have no message at all, the shop has the number; see [how to find a tracking number](guide:find-tracking-number).

Eleven digits are a common length. GLS (in Germany, Switzerland, France, Austria and Poland), Swiss Post's PostLogistics, Coordinadora, Mainfreight and Paquetexpress use it too, so an 11-digit number on its own doesn't prove the parcel is with PPL. Check which carrier the shop's email names; ten or twelve digits point to a typo or another carrier.

mojePPL only lists a parcel when the email the sender gave PPL matches the one you signed in with.

## What PPL's tracking statuses mean

PPL's tracking shows its wordings in Czech or English only; these are the English ones, shown when the site is set to English.

| PPL's status | What it means for you |
| --- | --- |
| `We are waiting for the shipment to be accepted for transport` | The sender has created the label; PPL doesn't have the parcel yet. |
| `We have received the shipment from the sender` | PPL has it. |
| `We have received the shipment from the foreign partner` | A parcel from abroad has been taken over by PPL in Czechia. |
| `The shipment is in transit` | Moving between depots, or being prepared for delivery. |
| `The shipment will be delivered today` | Loaded for today's round: out for delivery. |
| `The shipment was not delivered due to lack of time` | A delay on PPL's side; nobody tried your door. |
| `The shipment was not delivered due to failure to reach the recipient` | A missed delivery: the driver couldn't reach you. |
| `We have delivered the shipment to the pick-up point` | Waiting for you at a Parcelshop or Parcelbox: ready for pickup. |
| `We have delivered the shipment to the recipient` | Delivered to you. |
| `The shipment has not been collected by the recipient and will be returned to the sender` | Not picked up in time; it is going back. |
| `The shipment has been handed over to a foreign partner for delivery` | A partner abroad will deliver it. |

"Delivered to the pick-up point" is not delivery to you: the parcel is waiting, and the time to pick it up is running. "Will be delivered today" is the loading scan. "Not delivered due to lack of time" (or to a car breakdown, late arrival at the depot or a route problem) means nobody came. Above a finished history PPL also prints a summary line, `The shipment has been delivered to the recipient.`

## PPL delivery, Parcelshops and Parcelboxes

You choose home delivery or a pickup point at the shop's checkout: a Parcelshop, a Parcelbox, or an AlzaBox or other third-party box. PPL says it delivers by the next working day, and a parcel from abroad reaches Czechia 2 to 5 working days after collection abroad.

**Changing the delivery.** In mojePPL, or on [PPL's change-of-delivery page](https://www.ppl.cz/en/change-of-delivery) with the shipment number and PIN, you can have the parcel delivered to a PPL pickup point instead.

**Not at home.** PPL's terms let an adult relative or someone else at the address take the parcel for you. For private customers PPL tries the address once: if the driver can't reach you, the parcel may go to the nearest pickup point, or to the favourite pickup point you set in mojePPL.

**Picking it up.** Bring the PIN from the SMS, email or mojePPL; anyone with the PIN can pick it up. PPL gives 7 calendar days at a Parcelshop, at most 72 hours in a PPL Parcelbox and 48 hours in a third-party box or AlzaBox, counted from the SMS or email. Only Parcelbox storage can be extended: in mojePPL, by two, four or six days at a time, with PPL points or a card, up to 30 extra calendar days. Between 1 October and 31 January PPL's terms let it shorten storage with no extension, so trust the SMS or email, which says where the parcel is and until when. Parcels not picked up go back to the sender, and PPL doesn't deliver again.

**Cash on delivery.** Pay the driver in cash or with a Czech-issued card. Parcelshops may have no terminal; Parcelboxes take cards only; other boxes need payment online first, in mojePPL or via the link in the SMS or email.

## PPL parcel delayed, damaged or not delivered

PPL's pages say nothing about tracking that stops moving or lost parcels, and set no deadline for them. The sender holds the contract with PPL, and PPL handles complaints "mainly" with the sender, so start with the shop. See [tracking not updating](guide:tracking-not-updating) for how long to wait.

**Damaged box:** you may accept it, but have the damage recorded in the driver's scanner. **Damaged contents in an intact box:** report it on [PPL's claims form](https://www.ppl.cz/en/claims) within 7 calendar days of delivery, with photos of the parcel, contents, filling and packaging (all labels included) and the invoice where relevant. PPL handles complete complaints within 30 days.

**Cash-on-delivery fraud.** PPL warns about fake shops, mostly abroad, whose cash-on-delivery parcels hold something worthless or only filler, and about cash-on-delivery parcels nobody ordered. If you've paid for one, keep the order confirmation, payment details, photos of the contents and your messages with the seller, and contact the Czech Police at once: PPL has to pass the amount on, and only the police can order it stopped.

For unresolved disputes, PPL names the Czech Telecommunications Office and the Czech Trade Inspection Authority.

## PPL customer service and contact

For recipients, ppl.cz offers:

- **Chatbot**: around the clock, at the bottom right of ppl.cz, from tracking to changing the delivery.
- **Web form**: [Contact form](https://www.ppl.cz/en/contact-form), also for complaints.
- **Help pages**: [Customer service](https://www.ppl.cz/en/customer-service), with answers in English.

PPL publishes no phone line for recipients. Its About us page gives info@ppl.cz as the company's email.

## Tracking PPL parcels with Peek

[Peek](/) can't tell a PPL number from its 11 digits alone, so for a number starting with 2 to 9 it asks the carriers whose numbers look the same: PPL, GLS, [GLS Switzerland](carrier:gls-ch) and PostLogistics, with PPL first in Czechia, then Coordinadora, Mainfreight and Paquetexpress. It names the one that knows the number. You can also pick PPL with "Change" ("Change carrier" on a parcel already added), or paste a ppl.cz tracking link with `shipmentId=`, which Peek reads as PPL's. A dhl.com link with a PPL number won't work.

Peek reads PPL's English statuses as written, with times read on Prague time and shown in your device's time zone. It shows the expected delivery day while PPL gives one, the weight, the delivery time and the Parcelshop or Parcelbox name while the parcel waits there. PPL gives no places for its scans. Peek leaves out the delivery window, sender, customer reference, the pickup point's address, cash-on-delivery details and proof of delivery. When PPL's reply links a partner abroad's tracker with the partner's number, Peek names that partner as the one delivering. It doesn't take SmartPIN codes.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day PPL scans it out for delivery, every 2 minutes from 6 a.m. Czech time while PPL's own tracking answers. Otherwise it checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When PPL's tracking doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking PPL.", with a "Get help from PPL" link to PPL's customer service. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking PPL."

Peek can notify you of "Every scan", "Important steps" or "Delivery only".

## Questions about PPL tracking

### Can I track PPL in English?

Yes. PPL's tracking and help pages have English versions, with English statuses.

### Is there a PPL tracking app?

Yes, mojePPL for Android and iOS. It tracks parcels, changes the delivery place, sets a favourite pickup point, extends Parcelbox storage, pays cash on delivery online and shows the PIN for picking up.

### What is the difference between a PPL Parcelshop and a Parcelbox?

A Parcelshop is a shop that hands out parcels during its opening hours. A Parcelbox is a self-service locker open 24/7 that takes card payments only.

### Is PPL the same as DHL?

Not quite: PPL is a Czech carrier that has been part of DHL Group since 2006 and delivers across Europe with DHL. It has its own tracking and 11-digit numbers.

:::sources
- [PPL CZ: Customer service](https://www.ppl.cz/en/customer-service) – 11-digit number, emails, change to a pickup point, missed delivery, holding times, cash on delivery, damage, complaints, fraud, chatbot
- [PPL CZ: Zákaznický servis](https://www.ppl.cz/zakaznicky-servis) – the same answers in Czech
- [PPL CZ: Shipment tracking](https://www.ppl.cz/en/track-a-shipment) – tracking page, Parcelboxes 24/7, language switch, status wordings
- [PPL CZ: Pick-up points](https://www.ppl.cz/en/what-are-pickup-points) – Parcelshops, boxes, holding times, Parcelbox extension, transferable PIN
- [PPL CZ: mojePPL](https://www.ppl.cz/en/mobile-app) – app features
- [PPL CZ: About us](https://www.ppl.cz/en/about-us) – DHL Group, company email
- [PPL CZ: International transport](https://www.ppl.cz/en/international-transport) – delivery across Europe with DHL
- [PPL CZ: Přepravce PPL varuje](https://www.ppl.cz/ppl-varuje-pred-podvodnymi-zasilkami) – fake shops, unordered cash-on-delivery parcels, only the police can stop the payment (in Czech)
- [PPL CZ: Prodlužte si úložní dobu v PPL boxu](https://www.ppl.cz/novinka-prodluzte-si-ulozni-dobu-v-ppl-boxu-klidne-na-tyden) – Parcelbox extension by 2, 4 or 6 days, up to 30 days (in Czech)
- [PPL CZ: Oblíbené výdejní místo](https://www.ppl.cz/oblibene-vydejni-misto-ppl) – favourite pickup point (in Czech)
- [PPL CZ: Dispute resolution](https://www.ppl.cz/en/dispute-resolution) – ČTÚ and ČOI
- [PPL CZ: Contact form](https://www.ppl.cz/en/contact-form) – web form
- [PPL CZ: Claims](https://www.ppl.cz/en/claims) – damage form
- [PPL CZ: Všeobecné podmínky doručování zásilek](https://www.ppl.cz/documents/20122/1825980/ppl_cz_obchodni_podminky.pdf/80b8645c-14e6-e5ce-b9ca-06c60a9c8239) – storage shortened from October to January, who may take the parcel at the address (in Czech)
- [PPL CZ: Produktové podmínky](https://www.ppl.cz/documents/20122/1732429/PPL_CZ_produktove_podminky.pdf/08160b07-44e6-6405-de08-98297b32f8c0) – one delivery attempt for private customers (in Czech)
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
