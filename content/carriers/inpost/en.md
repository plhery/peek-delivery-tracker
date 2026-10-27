---
title: InPost tracking: numbers, statuses and locker pickup
description: Track an InPost parcel in the UK, Poland, Italy or Portugal: what the number looks like, what each update means, how long lockers keep it and who to call.
slug: inpost-tracking
published: 2026-10-11
updated: 2026-10-27
---

InPost is a parcel locker network that began in Poland and delivers to lockers, shops and homes in the UK, Poland, Italy, Portugal and beyond. Track a parcel on InPost's site for your country, such as [inpost.co.uk/tracking](https://inpost.co.uk/tracking), or paste the number in the box above. The number comes from the shop or seller; the locker code is something else.

## InPost tracking number: shapes and where to find it

InPost Italy says the number is in the shop's order emails and InPost's pickup emails; InPost Portugal says to ask the sender. No email? See [how to find a tracking number](guide:find-tracking-number).

| Number (invented) | What it is |
| --- | --- |
| `JD0000000000000000` | JD and 16 digits: UK. |
| `JJD0000000000000000` | JJD and 16 digits: UK. |
| `8YDR000000000` | 8YDR and 9 digits: UK. |
| `00XXX0000000A000` | Two digits, three letters, seven digits, `A` and three digits: a Yodel label number, UK. |
| `012345678901234567890123` | 24 digits: Poland and Italy. [bpost](carrier:bpost) and [Ciblex](carrier:ciblex) use it too. |
| `12345678` | 8 digits: inpost.pl and inpost.pt accept it. Also fits [Mondial Relay](carrier:mondial-relay). |

InPost UK says its numbers usually start with JD, JJD or 8. JJD or JD followed by 18 digits is a DHL number. The collection code, PIN, QR code and Poland's "kod nadania" (a sender's code for posting without a label) won't track. If the shop's email names another carrier, [the number may be theirs](guide:tracking-number-formats).

## InPost tracking stages: what each update means

InPost UK's July 2024 blog lists its website's headlines in order; they may have changed since.

| InPost UK says | What it means for you |
| --- | --- |
| `Sit tight, your parcel's loading` | Packed by the sender; InPost doesn't have it yet. |
| `We're on it` | Dropped off by the sender, waiting for InPost to collect it. |
| `It's on the road` | In InPost's network. It can repeat. |
| `Not long now` | At your local delivery centre. |
| `It's getting close` | InPost will try to deliver it today. |
| `Delivery day, yay!` | In the locker or shop: pick it up within 3 days. |
| `Tick tock...` | One day left to pick it up. |
| `Uh oh` | Going back to the sender: not picked up, or InPost couldn't get it to the pickup point. |
| `It's back` | Back with the sender. |

`Delivery day, yay!` means the parcel is already waiting and your 3 days have started, not that a driver is coming.

Polish parcels get InPost's Polish titles: `Nadana przez Paczkomat/PaczkoPunkt` (posted by the sender at a locker or pickup point), `Odebrana przez Kuriera` (collected by InPost's courier, not by you), `Wydana do doręczenia` (out for delivery), `Odmowa przyjęcia` (refused) and `Anulowano etykietę` (label cancelled, never sent). inpost.pl also shows `Brak możliwości doręczenia` (couldn't be delivered).

## InPost locker pickup: codes, time limits and missed deliveries

**Your code.** InPost UK emails you a 9-digit collection code, texts it to the phone number you gave the shop and shows it in the InPost app. At the locker, scan the QR code or type your phone number without the first 0, then the code. In Poland, Italy and Portugal the notice contains a pickup code or PIN and a QR code.

| Where | Time to pick it up |
| --- | --- |
| UK locker or shop | 3 days, with a reminder 24 hours before the end. No extension. |
| Poland | 48 hours in a Paczkomat, 72 at a PaczkoPunkt or limited-hours locker. Paid 24-hour extension in InPost Mobile. |
| Italy | Usually about 3 days; the date is in InPost's email. Rarely extended. |
| Portugal | 5 calendar days for domestic parcels, though InPost's pages also say 8. |

Then it goes back to the sender. Can't make it? InPost UK and Italy suggest sharing your code with someone who can.

**UK home delivery** runs Monday to Saturday, 7am to 9pm, with a 2-hour window in the app once the driver sets off. Missed it? Check the tracking for a neighbour or your safe place first.

## InPost tracking not updating, late or lost

**UK.** International parcels can take up to a week to reach InPost. No update for 72 hours after InPost received it? Contact its Customer Care team. Refunds are handled by the retailer or marketplace, InPost says. See [why tracking stops updating](guide:tracking-not-updating).

**Poland.** The sender can complain (reklamacja), and so can you once the parcel reaches you or the sender passes the claim on. A parcel not delivered within 30 days of posting counts as lost. Complain within 12 months of posting and within 7 days for hidden damage; raise visible damage when you take the parcel. InPost answers within 30 days.

**Italy and Portugal.** InPost Italy takes lost or late parcels by phone and sends you to the shop for damaged contents. InPost Portugal accepts a delay claim once a parcel takes more than 5 days from the shipping notice, and a loss claim after 30 days.

## InPost customer service and phone number

As listed on 10 October 2026:

- **UK:** 0330 335 0950 at your network's local rate, Monday to Friday 7am to 10pm, weekends 8am to 8pm, or "Leave a message" on [InPost's help page](https://inpost.co.uk/yodel-help).
- **Poland:** a virtual assistant around the clock (also on WhatsApp and Messenger), a form on [inpost.pl/kontakt](https://inpost.pl/kontakt), or +48 722 444 000, weekdays 7:00–22:00 and shorter hours at weekends.
- **Italy:** +39 0238582894, Monday to Saturday 8:00–20:00, or servizioclienti@inpost24.it.
- **Portugal:** 211 245 624, weekdays 8:30–20:00 and Saturdays 9:00–13:00.

> InPost Poland says it never texts about surcharges and its real emails never carry attachments. Report fakes to cybersecurity@inpost-group.com.

## Tracking an InPost parcel with Peek

[Peek](/) recognises `8YDR` numbers at once. For 24-digit, `JJD`, `JD` and Yodel label numbers it asks InPost whether it knows them (and Austrian Post and Ciblex for 24 digits). Other shapes, 8 digits included, aren't read as InPost's even if you pick InPost by hand; only universal tracking services are then asked. Links from inpost.pl's tracking page can be pasted, not inpost.co.uk's, and only the number is needed.

In Spain, Peek follows InPost under its [Mondial Relay](carrier:mondial-relay) entry (search "InPost Spain") and reads `FV` numbers (FV and 8 digits) on inpost.es. For an 8-, 10- or 12-digit Spanish number, press "Change" and pick Mondial Relay; Peek then searches Mondial Relay's French site, not confirmed to find InPost Spain's parcels.

It reads InPost's service at inposteasy.com, not yet confirmed to answer for UK numbers. You see the status, the newest 20 scans with each town or hub and country on a map, the delivery time and the destination country; InPost gives no expected date. For a 24-digit parcel waiting in a Paczkomat or pickup point, the point's name and address appear once InPost's Polish tracking confirms them, never your code. Peek asks InPost's service for its English wordings, and a parcel waiting at an Italian point may not show "Ready for pickup". Scans without a time zone are left out, and your name, address and phone are never read.

Peek doesn't follow the parcel to another carrier. It checks every 10 minutes by day, every 2 once out for delivery, and hourly overnight, and can notify you of each change.

## Questions about InPost tracking

### Is InPost the same as Yodel?

Yodel is now InPost's. InPost UK bought it in April 2025, and Yodel by InPost parcels are tracked with InPost's app and website.

### Is InPost a Polish company?

It started in Poland, where its operator, InPost sp. z o.o., is based in Kraków. The group, InPost S.A., is incorporated in Luxembourg.

### Where is my InPost 6-digit code?

InPost UK's collection code has 9 digits and comes by email, SMS and the InPost app; InPost Portugal's 6-digit PIN comes by email or SMS. In Poland, check InPost's texts, emails or InPost Mobile.

### How long does InPost take to deliver?

InPost Poland's locker terms declare delivery within 2 working days of the day after posting and guarantee no more than 5. InPost UK's tracking page tells senders: within 3 working days to a locker or shop, 4 to a home.

:::sources
- [InPost UK: Tracking a parcel page](https://inpost.co.uk/tracking) – 72 hours, codes, delivery times
- [InPost UK: Yodel by InPost help](https://inpost.co.uk/yodel-help) – Yodel, numbers, pickup, delivery, refunds, phone
- [InPost UK: Track to the Future](https://inpost.co.uk/blog/tracking-explained) – website updates
- [InPost: Who we are](https://inpost.eu/who-we-are) – Poland, Luxembourg
- [InPost: Home](https://inpost.eu/) – countries
- [InPost Polska: Śledzenie przesyłek](https://inpost.pl/sledzenie-przesylek) – number lengths, statuses
- [InPost Polska: Jak odebrać paczkę z urządzenia Paczkomat](https://inpost.pl/jak-odebrac-paczke-w-paczkomacie) – QR and pickup code
- [InPost Polska: Regulamin InPost Paczkomat 24/7](https://inpost.pl/sites/default/files/docs/regulaminy/regulamin-swiadczenia-uslugi-inpost-paczkomatr-247-przez-inpost-sp-z-oo-obowiazujacy-od-24072025.pdf) – kod nadania, pickup and delivery times, complaints
- [InPost Polska: Kontakt](https://inpost.pl/kontakt) – contacts, Kraków
- [InPost Polska: Bezpieczeństwo z InPost](https://inpost.pl/bezpieczenstwo-z-inpost) – fake messages
- [InPost Italia: Contatti e assistenza](https://inpost.it/contatti-e-assistenza) – number, PIN, codes, phone, problems
- [InPost Italia: Giacenza](https://inpost.it/news-giacenza-come-funziona-con-inpost) – about 3 days
- [InPost Italia: Pacco InPost, tempi di ritiro](https://inpost.it/news-pacco-inpost-quanto-tempo-hai-il-ritiro) – rare extensions
- [InPost Portugal: Rastrear Encomenda](https://www.inpost.pt/seguimento-do-envio) – 8 characters
- [InPost Portugal: Como recolher uma encomenda](https://www.inpost.pt/recebe-a-tua-encomenda/como-recolher) – PIN, ID, 5 or 8 days
- [InPost Portugal: Fala connosco](https://www.inpost.pt/fala-connosco) – sender, 5 days, claims, phone
- [InPost Easy: Parcel form](https://inposteasy.com/en) – the service Peek reads
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek shows
:::
