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
| `12345678` | 8 digits, like the example on inpost.pt's tracking page. Also fits [Mondial Relay](carrier:mondial-relay). |

InPost UK says its numbers usually start with JD, JJD or 8. JJD or JD followed by 18 digits is a DHL number. The collection code, PIN, QR code and Poland's "kod nadania" (in Allegro Lokalnie's rules, a 9-digit code InPost gives a seller to post a parcel without printing a label) won't track. If the shop's email names another carrier, [the number may be theirs](guide:tracking-number-formats).

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

In English, InPost's tracking service (the one Peek reads) calls other steps `Sent through Parcel Locker/Parcel Point` (posted by the sender at a locker or pickup point), `Collected by the Courier` (collected by InPost's courier, not by you), `Handed over for delivery` (out for delivery, to your door or a locker) and `Acceptance refusal` (refused).

## InPost locker pickup: codes, time limits and missed deliveries

**Your code.** InPost UK emails you a 9-digit collection code, texts it to the phone number you gave the shop and shows it in the InPost app. At the locker, scan the QR code or type your phone number without the first 0, then the code. In Italy and Portugal the notice contains a pickup code or PIN and a QR code.

| Where | Time to pick it up |
| --- | --- |
| UK locker or shop | 3 days, with a reminder 24 hours before the end. No extension. |
| Poland | Check the InPost Mobile app or InPost's Polish site. |
| Italy | Usually about 3 days; the date is in InPost's email. Rarely extended. |
| Portugal | 5 calendar days for domestic parcels, though InPost's pages also say 8. |

Then it goes back to the sender. Can't make it? InPost UK and Italy suggest sharing your code with someone who can.

**UK home delivery** runs Monday to Saturday, 7am to 9pm, with a 2-hour window in the app once the driver sets off. Missed it? Check the tracking for a neighbour or your safe place first.

## InPost tracking not updating, late or lost

**UK.** International parcels can take up to a week to reach InPost. No update for 72 hours after InPost received it? Contact its Customer Care team. Refunds are handled by the retailer or marketplace, InPost says. See [why tracking stops updating](guide:tracking-not-updating).

**Poland.** UKE, the Polish regulator, gives these general rules for every postal operator, and InPost is on its register of postal operators. The sender can complain (reklamacja), and so can you once the parcel reaches you or the sender passes the claim on. Complain within 12 months of posting and within 7 days for hidden damage; raise visible damage when you take the parcel. The operator answers within 30 days, or the complaint counts as accepted.

**Italy and Portugal.** InPost Italy takes lost or late parcels by phone and sends you to the shop for damaged contents. InPost Portugal accepts a delay claim once a parcel takes more than 5 days from the shipping notice, and a loss claim after 30 days.

## InPost customer service and phone number

As listed on 10 October 2026:

- **UK:** 0330 335 0950 at your network's local rate, Monday to Friday 7am to 10pm, weekends 8am to 8pm, or "Leave a message" on [InPost's help page](https://inpost.co.uk/yodel-help).
- **Poland:** a contact form, an AI chat assistant in the InPost Mobile app, +48 722 444 000 or +48 746 600 000, or a letter to InPost sp. z o.o., ul. Pana Tadeusza 4, 30-727 Kraków.
- **Italy:** +39 0238582894, Monday to Saturday 8:00–20:00, or servizioclienti@inpost24.it.
- **Portugal:** 211 245 624, weekdays 8:30–20:00 and Saturdays 9:00–13:00.

## Tracking an InPost parcel with Peek

[Peek](/) recognises `8YDR` numbers at once. For 24-digit, `JJD`, `JD` and Yodel label numbers it asks InPost whether it knows them (and Austrian Post and Ciblex for 24 digits). Other shapes, 8 digits included, aren't read as InPost's even if you pick InPost by hand; only universal tracking services are then asked. Links from inpost.pl's tracking page can be pasted, not inpost.co.uk's, and only the number is needed.

In Spain, Peek follows InPost under its [Mondial Relay](carrier:mondial-relay) entry (search "InPost Spain") and reads `FV` numbers (FV and 8 digits) on inpost.es. For an 8-, 10- or 12-digit Spanish number, press "Change" and pick Mondial Relay; Peek then searches Mondial Relay's French site, not confirmed to find InPost Spain's parcels.

It reads InPost's service at inposteasy.com, not yet confirmed to answer for UK numbers. You see the status, the newest 20 scans with each town or hub and country on a map, the delivery time and the destination country; InPost gives no expected date. For a 24-digit parcel waiting in a Paczkomat or pickup point, the point's name and address appear once InPost's Polish tracking confirms them, never your code. Peek asks InPost's service for its English wordings, and a parcel waiting at an Italian point may not show "Ready for pickup". Scans without a time zone are left out, and your name, address and phone are never read.

Peek doesn't follow the parcel to another carrier. It checks every 10 minutes by day, every 2 once out for delivery, and every hour or two overnight, and can notify you of each change.

## Questions about InPost tracking

### Is InPost the same as Yodel?

Yodel is now InPost's. InPost UK bought it in April 2025, and Yodel by InPost parcels are tracked with InPost's app and website.

### Is InPost a Polish company?

It started in Poland, where its operator, InPost sp. z o.o., is based in Kraków. The group, InPost S.A., is incorporated in Luxembourg.

### Where is my InPost 6-digit code?

InPost UK's collection code has 9 digits and comes by email, SMS and the InPost app; InPost Portugal's 6-digit PIN comes by email or SMS. In Poland, check the InPost Mobile app.

### How long does InPost take to deliver?

InPost UK's tracking page tells senders: within 3 working days to a locker or shop, 4 to a home.

:::sources
- [InPost UK: Tracking a parcel page](https://inpost.co.uk/tracking) – 72 hours, codes, delivery times
- [InPost UK: Yodel by InPost help](https://inpost.co.uk/yodel-help) – Yodel, numbers, pickup, delivery, refunds, phone
- [InPost UK: Track to the Future](https://inpost.co.uk/blog/tracking-explained) – website updates
- [InPost: Who we are](https://inpost.eu/who-we-are) – Poland, Luxembourg
- [InPost: Home](https://inpost.eu/) – countries
- [InPost: Regulamin Konta InPost](https://terms.inpost-group.com/pl/regulamin) – InPost Mobile, Polish contact form, AI chat, phones, address
- [UKE: Rejestr operatorów pocztowych, InPost Sp. z o.o.](https://bip.uke.gov.pl/rop/rejestr-operatorow-pocztowych/?p=2&page=1&rejestr=B-00562) – registered postal operator, Kraków
- [UKE: Reklamacje usług pocztowych](https://cik.uke.gov.pl/uslugi-pocztowe/dochodzenie-roszczen/reklamacje/) – Polish complaints: who files, 12 months, 30-day answer, accepted if unanswered
- [UKE: Reklamacje i odszkodowania](https://cik.uke.gov.pl/uslugi-pocztowe/pytania-i-odpowiedzi/reklamacje-i-odszkodowania-faq/2.html) – visible damage on delivery, 7 days for hidden damage
- [Allegro: Regulamin Allegro, Załącznik 16A](https://policies.allegro.com/doc/f86a1a793dc4f5bc4b695b0ff0d54d974badab4169f5aaaddad89585c2c90690) – kod nadania in Allegro Lokalnie
- [InPost Italia: Contatti e assistenza](https://inpost.it/contatti-e-assistenza) – number, PIN, codes, phone, problems
- [InPost Italia: Giacenza](https://inpost.it/news-giacenza-come-funziona-con-inpost) – about 3 days
- [InPost Italia: Pacco InPost, tempi di ritiro](https://inpost.it/news-pacco-inpost-quanto-tempo-hai-il-ritiro) – rare extensions
- [InPost Portugal: Rastrear Encomenda](https://www.inpost.pt/seguimento-do-envio) – 8 characters
- [InPost Portugal: Como recolher uma encomenda](https://www.inpost.pt/recebe-a-tua-encomenda/como-recolher) – PIN, ID, 5 or 8 days
- [InPost Portugal: Fala connosco](https://www.inpost.pt/fala-connosco) – sender, 5 days, claims, phone
- [InPost Easy: Parcel form](https://inposteasy.com/en) – the service Peek reads
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek shows, 24-digit Polish numbers, InPost's English wordings
:::
