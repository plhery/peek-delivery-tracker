---
title: Česká pošta (Czech Post) tracking: numbers and statuses
description: Czech Post (Česká pošta) and Balíkovna tracking on postaonline.cz: tracking number formats, English statuses, pickup deadlines (15 or 7 days) and contacts.
slug: ceska-posta-czech-post-tracking
published: 2026-10-13
updated: 2026-11-27
---

For Czech Post tracking (Česká pošta, the Czech national post, and its parcel brand Balíkovna), enter your tracking number on Czech Post's English [Track & Trace](https://www.postaonline.cz/en/trackandtrace) on postaonline.cz, in the Pošta Online app, or in the box above. The number is on the shipping label, in Czech Post's SMS or email, or on the notice in your mailbox.

## Czech Post tracking number: where to find it

Czech Post describes its numbers as a prefix of usually two letters for the type of item, 9 or 10 digits, and a suffix: a letter for the type of sender, or the country code of the item's origin. Upper or lower case doesn't matter.

| Shape (invented) | What it is |
| --- | --- |
| `DR4829173654E` | A domestic parcel: two letters, 10 digits, a letter. |
| `RR482917367CZ` | An international number posted in Czechia: two letters, 9 digits, `CZ`. |
| `RR482917367CN` | An item coming from abroad: it keeps the foreign post's number, ending in the country it came from. |

If the shop passed on your contact details, Czech Post notifies you by email or SMS, and the email links to Track & Trace. The six-character pickup code in that message isn't a tracking number. Can't find any of these? See [where else to find a tracking number](guide:find-tracking-number).

Packeta's numbers are `Z` and 10 digits, and PPL, DHL's Czech partner, uses 11 digits: neither is Czech Post's, so use [Packeta's](carrier:packeta) or [PPL's](carrier:ppl) tracking. A parcel sent from abroad is tracked by the sending post until it reaches Czechia: Czech Post says statuses before an item enters the country depend on what the foreign post passes on.

## What Czech Post tracking statuses mean

Track & Trace comes in Czech and English. These are its English wordings; place names stay in Czech, and each line has a date but no time.

| Czech Post's status | What it means for you |
| --- | --- |
| `Receipt of data about consignment.` | The sender has [sent the parcel's details](guide:tracking-statuses); Czech Post doesn't have it yet. |
| `The consignment was sent for transportation.` | Posted: the first line with a post office or depot. |
| `The consignment is transported.` | On its way through Czech Post's network. |
| `The consignment is being prepared for delivery.` | At the post office or depot that delivers it. |
| `The consignment is being delivered.` | Out for delivery today. |
| `The consignment was deposited - addressee not at home.` | Nobody was in: it waits at the post office named in the line. |
| `The consignment was deposited to Balíkovna.` | Waiting at the Balíkovna point or box named in the line. |
| `The consignment was sent back to the sender - the consignment was not picked up by the addressee in the set Collection Time.` | Not picked up in time: it's going back. |
| `The consignment was returned to the sender.` | It's back with the sender. |

`SMS alert for the addressee - consignment delivery date.` and the email version only record a message sent to you; `Geographical data from the delivery attempt.` records where the courier was. None of them moves the parcel. `There is no record for a consignment with this posting number.` means Czech Post doesn't know the number (yet), while `Czech Post does not display information for this consignment type.` means it knows the number but shows nothing for that kind of item.

## Missed delivery, Balíkovna and post office pickup

**Post office.** If you're out, the item is stored at the post office and you get a notice, on paper, by SMS or by email. Czech Post keeps it the usual 15 days, unless the sender shortened that to 10 days. With "Change Delivery Online" on postaonline.cz or the post office's number on the notice, you can, free unless noted (the first two also in the Pošta Online app):

- ask for redelivery on a business day between 8 a.m. and 4 p.m. (Prague time), at least three business days ahead;
- extend storage up to one month from the day it was ready, unless the sender ruled that out (not for Balík Do ruky);
- move it to another post office;
- have it forwarded to another Czech address, for a fee.

**Balíkovna.** Its pickup points are post office counters, partner shops and self-service boxes such as AlzaBox, OX Point and Penguin Box. A parcel waits 7 calendar days at a post office or shop, and in a box until 7:00 on the second working day after it's ready. Balíkovna's pickup time can't be extended; you get an email or SMS before it ends, and an uncollected parcel goes back to the sender. If a box won't open, call the number on it or Balíkovna on 218 218 218.

**Home delivery.** Balíkovna texts you on the day and the driver calls first; if you're out, the parcel goes to the nearest pickup point. Most domestic parcels can be picked up at any post office with the six-character code and your name or the number, without ID.

## Czech Post parcel stuck, late, lost or damaged

If tracking stops moving, see [tracking that isn't updating](guide:tracking-not-updating). Parcels from outside the EU may need customs: Czech Post can ask you by email, SMS or letter to fill in its form at postaonline.cz/celni-rizeni, and collects any duty and fees on delivery. More in [parcels held at customs](guide:customs).

A lost or late item is the sender's claim: one year from posting within Czechia, six months for international items. So ask the shop. As recipient, report damage or missing contents at any post office within two working days of receipt, with the packaging or photos; Czech Post says international deadlines may be shorter. If the damage shows at the door, you can ask for it to be stored at the post office and claim there, or refuse it. Accept it, and you lose the option to claim. If a complaint isn't upheld or handled within 30 days, you can turn to the Czech Telecommunication Office (ČTÚ).

## Česká pošta contact and customer service

Czech Post's channels, as listed on its sites:

- **Phone** +420 210 123 456, Monday to Friday, 8 a.m. to 6 p.m. (Prague time). A voice assistant, Eliška, answers first and passes you to an operator if she can't help.
- **Email** info@cpost.cz, for questions and complaints.
- **In person** at any Czech Post office.
- **Balíkovna**: the contact form on balikovna.cz.

> Czech Post warns about fake SMS and emails using its name, such as a parcel "at customs" asking for a fee. It never asks for payment, login or personal details through links in messages. Check the status on its site or app yourself.

## Tracking Česká pošta parcels with Peek

[Peek](/) recognises Czech Post's domestic numbers starting with `B` or `D`, or with `NB`, `NP` or `NR`, straight away. Other letters in that shape name no carrier: pick "Česká pošta" with "Change". For international numbers ending `CZ` with a valid check digit, Peek asks Czech Post (and [Chronopost](carrier:chronopost)) and shows "Česká pošta has this parcel." when it knows the number, or "Not found at … yet" until it does. A number from another country's post stays with that post and is handed to Czech Post once the post abroad gives Czechia as the destination (or gives none and you're in Czechia) and Czech Post shows a dated scan no older than that post's newest; otherwise use "Change". Links from postaonline.cz with `/trackandtrace/-/zasilka/` and the number can be pasted too.

Czech Post dates each step by its day, with no time. Peek asks Czech Post and also looks for a history with times at [universal tracking services](guide:universal-tracker): when one adds steps or another status, Peek shows it and asks Czech Post again every 6 hours; when one only dates the same steps, Peek shows those times and still asks Czech Post first; when none has it, Peek lists Czech Post's own steps, each under its day with no time. Statuses keep Czech Post's English wording. Peek can show the post office or Balíkovna point where the parcel waits (by name, not address) and the weight. Czech Post gives no expected delivery day. Peek leaves out postcodes, cash-on-delivery details and the parcel count, and can notify you when the status changes.

## Questions about Czech Post tracking

### How do I track a Czech Post (Česká pošta) or Balíkovna parcel?

Enter the domestic number (such as the invented `DR4829173654E`) or international `CZ` number (`RR482917367CZ`) on [postaonline.cz](https://www.postaonline.cz/en/trackandtrace) or in the box above. The six-character pickup code in Czech Post's SMS or email is for collection, not tracking.

### What is Česká pošta?

It's the Czech national post, Czech Post in English. Balíkovna is its parcel brand.

### Can I track Czech Post in English?

Yes. Track & Trace on postaonline.cz has an English version, and it takes up to 20 numbers at once, separated by commas or semicolons. Place names stay in Czech.

### How long does Czech Post take?

Balíkovna says most parcels are ready for pickup the next working day after sending, and address deliveries typically arrive as early as the next day.

:::sources
- [Česká pošta: Track & Trace](https://www.postaonline.cz/en/trackandtrace) – example number, 20 numbers, English and Czech
- [Česká pošta: All trackable services and their codes](https://www.ceskaposta.cz/en/rady-a-tipy/pri-podani/seznam-druhu-zasilek-s-moznosti-sledovani) – number shapes, where to find it, items from abroad
- [Česká pošta: Notification of parcels](https://www.ceskaposta.cz/en/sluzby/ostatni-sluzby/avizovani-balikovych-zasilek) – free email and SMS
- [Česká pošta: What to do if you are not at home](https://www.ceskaposta.cz/en/rady-a-tipy/pri-dodani/co-delat-kdyz-vas-dorucovatel-nezastihne-doma) – redelivery, storage, other office, forwarding
- [Česká pošta: Services at delivery](https://www.ceskaposta.cz/en/sluzby/online-sluzby/online-sluzby-pri-dodani) – 15 days, three business days, Balík Do ruky
- [Česká pošta: Parcel pickup based on code](https://www.ceskaposta.cz/en/sluzby/zasilky-cr/ostatni-zasilky/vydej-baliku-na-kod) – six-character code, no ID
- [Česká pošta: Claims and complaints](https://www.ceskaposta.cz/en/rady-a-tipy/jine/reklamace-a-stiznosti) – claims, deadlines, escalation
- [Česká pošta: FAQs on customs clearance](https://www.ceskaposta.cz/en/sluzby/zasilky-zahranicni/celni-rizeni/faq-caste-otazky-k-celnimu-rizeni) – customs form, fees on delivery
- [Česká pošta: Client support](https://www.ceskaposta.cz/en/kontakty/pomoc-klientum) – phone, hours, Eliška, email
- [Česká pošta: Contacts](https://www.postaonline.cz/en/kontakty) – +420 210 123 456, hours
- [Česká pošta: Security Warning](https://www.ceskaposta.cz/en/o-spolecnosti/bezpecnostni-informace/bezpecnostni-upozorneni) – never asks via links
- [Česká pošta: Aktuální informace](https://www.ceskaposta.cz/o-spolecnosti/bezpecnostni-informace/aktualni-informace) – fake SMS and email warnings
- [Česká pošta: Poštovní podmínky](https://www.postaonline.cz/documents/10180/282441/zps.pdf) – notices, 15 and 30 days
- [Balíkovna: Homepage](https://www.balikovna.cz/en/) – network, next-day delivery, boxes
- [Balíkovna: FAQ](https://www.balikovna.cz/en/faq) – 7 days, boxes, missed delivery, 218 218 218
- [Balíkovna: Všeobecné podmínky](https://www.balikovna.cz/documents/20124/15302839/Balikovna-VPPS.pdf) – storage, failed delivery, return
- [Portál veřejné správy: Objection to a postal complaint](https://portal.gov.cz/sluzby-vs/sluzba-S2130) – ČTÚ, 30 days
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
