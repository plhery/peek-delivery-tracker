---
title: Chronodiali tracking: CHPU numbers, statuses and contact
description: Track a Chronodiali parcel in Morocco: the CHPU number, its French statuses in plain English, cash on delivery, three delivery attempts and contacts.
slug: chronodiali-tracking
published: 2026-11-09
updated: 2026-11-09
---

Chronodiali (also written Chrono Diali) is a Moroccan delivery company for online shops, a joint venture of Barid Al-Maghrib (Poste Maroc) and Geopost. It delivers e-commerce parcels across Morocco, to your door or to one of its agencies. If the shop chose cash on delivery, you pay the courier when the parcel arrives, in cash or by bank card. Track it under "Suivre mon colis" on [chronodiali.ma](https://chronodiali.ma/suivi_colis.html) or in the box above. The number comes from the shop that sent the parcel; Chronodiali's own example starts with `CHPU`.

## Your Chronodiali tracking number

The shapes Peek knows, with made-up digits:

| Number | What it is |
| --- | --- |
| `CHPU0000000000000000000` | `CHPU` and 19 digits: a Chronodiali number. Peek recognises it at once. |
| `CD0000000000` | `CD` and 10 digits. Peek can track it with Chronodiali too, but the same shape fits [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) and [Aramex New Zealand](carrier:aramex-nz) numbers, so Peek asks all four which one has it (see below). |
| `chronodiali.ma/suivi_colis.html?id=…` | A link to Chronodiali's tracking page. Peek reads it as Chronodiali, whatever number it holds. |

Chronodiali isn't [Chronopost](carrier:chronopost), the French express carrier: their numbers and trackers are different. No number at all? Ask the shop, or see [where to find a tracking number](guide:find-tracking-number).

## Chronodiali tracking statuses in English

Chronodiali writes its tracking in French. In the order a parcel usually meets them:

| Chronodiali says | Peek shows | What it means for you |
| --- | --- | --- |
| `Les données du colis ont été intégrées dans nos systèmes.` | Label created | The shop has sent the parcel's details to Chronodiali. Chronodiali doesn't have the parcel yet. |
| `Le ramassage a été programmé.` | Label created | A pickup at the shop is booked. |
| `Le ramassage est en attente, le livreur se présentera sous peu.` | Label created | Waiting for the courier to collect the parcel from the sender. |
| `Le colis a été collecté par le livreur.` | Received by carrier | The courier has collected it. |
| `Le colis est arrivé au hub d'origine.` | In transit | At Chronodiali's first hub. |
| `Le colis a quitté notre hub.`, `Le colis est arrivé au hub.` | In transit | Moving between Chronodiali's hubs. |
| `Delivery Scheduled` | In transit | Put on a delivery round at the destination hub. |
| `Out For Delivery`, `Le colis est en cours de livraison.` | Out for delivery | The courier has it today. If you pay on delivery, have the amount ready. |
| `Le colis a été livré.` | Delivered | Delivered. |
| `La livraison a échoué : le client n'a pas répondu ou était injoignable.` | Delivery attempted | The courier couldn't reach you, by phone or at the door. |
| `La livraison a échoué : le lieu de livraison est incorrect ou introuvable.` | Delivery attempted | The address is wrong or the courier couldn't find it. |
| `La livraison a échoué : le client est indisponible.` | Delivery attempted | You weren't available. |
| `La livraison a échoué : le client n'a pas le montant COD requis.` | Delivery attempted | You didn't have the cash-on-delivery (COD) amount. |
| `La livraison a échoué : le client a refusé le colis.` | Delivery attempted | The parcel was refused. |
| `La livraison a échoué.` | Delivery attempted | Failed, with no reason given. |
| `Le colis est retenu au hub.` | Needs attention | The parcel is held at a hub. |
| `La livraison a été annulée : le colis est endommagé et ne peut pas être livré.` | Needs attention | The parcel is damaged and won't be delivered. |
| `Le colis a été annulé.` | Needs attention | The shipment was cancelled. |
| `Le colis va être renvoyé à l'expéditeur.` | Returned to sender | It's going back to the shop. |
| `Le colis a été retourné à l'adresse de l'expéditeur.` | Returned to sender | It's back at the shop's address. |

**Not on chronodiali.ma.** Chronodiali's own tracking page skips some of these lines: `Delivery Scheduled`, `Out For Delivery`, the failed deliveries, the cancelled or rescheduled deliveries and `Le colis a été confié à un partenaire tiers.` Peek shows them, so its history can be longer than Chronodiali's.

**Other lines.** `La livraison a été reprogrammée : une nouvelle date a été définie.` means the delivery moved to a new date; `La livraison a été reprogrammée.` gives no detail. `La livraison a été annulée à la demande du client.` means the delivery was cancelled at the customer's request. `Le colis est en attente au hub.` means the parcel is waiting at a hub. For the two `partenaire tiers` lines, see the questions below; Peek shows both as "In transit".

**Parcels from abroad.** Some parcels come into Morocco from another country. You may then see `arrivé dans le pays.` (it has arrived in Morocco; Peek shows "In transit"), `dédouanement en cours` (customs clearance is in progress; "At customs") and `dédouanement terminé` (customs has released it; "In transit"). Chronodiali's site says nothing about duties or who pays them: ask the shop, and see [parcels held at customs](guide:customs).

## Chronodiali delivery: cash on delivery, three attempts, agencies

Chronodiali says it delivers all over Morocco, to more than 300 destinations, at home or at an agency.

- **Paying on delivery:** Chronodiali's tracking page shows the amount to pay, `Montant à payer`, in dirhams (DH). Chronodiali says you can pay it at delivery in cash or by bank card.
- **Three attempts:** Chronodiali lists "3 Tentatives de livraison" (three delivery attempts) among its services. In Peek, each failed attempt shows a line starting `La livraison a échoué`, with or without a reason.
- **Keep your phone on:** one reason for a failed delivery is that you didn't answer or couldn't be reached.
- **Notifications:** its services page lists SMS and email notifications, and a customer notification at each step of the journey.
- **Agencies:** Agadir, Casablanca, Fès, Marrakech, Rabat and Tangier, open Monday to Friday 9:00 to 18:00 and Saturday 9:00 to 13:00. The addresses are on its "Points de livraison" page.

Chronodiali's site doesn't say how long an agency keeps a parcel, or what happens after the third failed attempt. Ask Chronodiali or the shop. Missed the courier? See [missed deliveries](guide:missed-delivery).

## Chronodiali parcel late, refused, returned or not received

For a refund or a new parcel, ask the shop. The shop is Chronodiali's customer: it books the pickups through the platform it gets with its contract.

- **No movement after `Les données du colis ont été intégrées dans nos systèmes.`:** the shop has created the shipment, but Chronodiali hasn't collected the parcel yet. Ask the shop when it will hand it over. If chronodiali.ma shows `En cours de traitement.` (being processed), Chronodiali knows the number but has no scan yet.
- **Late:** Chronodiali publishes no delivery times. Its terms say they are given with the order and can vary with the destination. See [tracking that isn't updating](guide:tracking-not-updating).
- **Refused or not paid:** if you refuse the parcel or can't pay, the courier records a failed delivery. The parcel can then go back to the shop: `Le colis va être renvoyé à l'expéditeur.`, then `Le colis a été retourné à l'adresse de l'expéditeur.` See [returned to sender](guide:returned-to-sender).
- **Delivered but not received:** Chronodiali gives shops a digital proof of delivery in their client space. Ask the shop to check it. More in [delivered but not received](guide:delivered-not-received).
- **Damaged or lost:** tell the shop first. You can also tick "Réclamation ?" on Chronodiali's contact form, or call or email it with your number. See [damaged parcels](guide:damaged-parcel) and [lost parcels](guide:lost-parcel).
- **A message asking for money:** on its site, Chronodiali describes your payment as one made at delivery, in cash or by card. If a text or email asks you to pay through a link, check the tracking first and see [delivery scam texts](guide:delivery-scam-texts).

## Chronodiali contact number and customer service

As read on 1 November 2026 on chronodiali.ma:

- **Phone:** +212 5 20 61 62 63 (05 20 61 62 63 from Morocco).
- **Email:** service-client@chronodiali.ma.
- **Contact form:** on the [Contact](https://chronodiali.ma/contact_us.html) page, choose "Réclamation ?" for a complaint or "Demande d'information ?" for a question. The form asks only for your name, email, phone and a shop name, with no box for a message or a tracking number; Chronodiali says it will contact you.
- **Agencies:** the six above, with the same phone number and email.

The site gives no separate hours for the phone line and lists no WhatsApp number.

## Track a Chronodiali parcel with Peek

Paste a `CHPU` number and [Peek](/) names Chronodiali at once, under "Detected carrier". Lower case and spaces are fine. A link to chronodiali.ma's tracking page works too.

A `CD` number of 10 digits doesn't tell Peek the carrier. Paste it all the same: Peek follows more than 3,500 carriers. Peek shows "Checking tracking services…" while it asks Chronodiali and the carriers whose numbers share the shape; if Chronodiali alone has the parcel, its name shows with "has this parcel". Or tap "Change" and pick "Chronodiali" under "Fits this number". If you pick Chronodiali for a number of another shape, Peek says it "doesn’t issue numbers like this one", and the parcel shows "Check the tracking number".

Peek reads Chronodiali's own tracking: the status, each line as Chronodiali writes it (in French, sometimes in English, as the two lines above), the hub's name as the place where there is one, the destination country and the delivery time. Peek shows the times in your device's time zone. It leaves out the sender's name and yours, the towns, the amount to pay, the proof of delivery and the courier's calls. Chronodiali's tracking gives no expected delivery date and no weight. The lines about a partner company don't name the partner, and Peek doesn't follow the partner's own tracking.

If Chronodiali doesn't know the number yet, Peek shows "Waiting for the carrier" and keeps checking. Peek checks every 10 minutes, and every 2 minutes once the parcel is out for delivery. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". It can't pay, rebook a delivery or contact Chronodiali or the shop for you.

## Questions about Chronodiali tracking

### Is Chronodiali part of Poste Maroc?

Partly. Chronodiali's news page calls it a joint venture: Barid Al-Maghrib (Poste Maroc) holds 51% and Geopost 49%. It has its own tracking at chronodiali.ma and its own customer service.

### How many times does Chronodiali try to deliver?

Three times, Chronodiali says. In Peek, a failed try shows a line starting `La livraison a échoué`, with or without a reason after it.

### Can I pay a Chronodiali parcel by card?

Yes. Chronodiali says you can pay on delivery in cash or by bank card.

### What does "partenaire tiers" mean on Chronodiali tracking?

`Le colis a été assigné à un partenaire tiers.` and `Le colis a été confié à un partenaire tiers.` mean Chronodiali has assigned or handed the parcel to a partner company. The tracking doesn't name the partner. Wait for the next line, or ask Chronodiali with your number.

### How do I contact Chronodiali about my parcel?

Call +212 5 20 61 62 63 or email service-client@chronodiali.ma, with your tracking number. For a refund or a new delivery, ask the shop.

:::sources
- [Chronodiali: Les experts de la livraison e-commerce](https://chronodiali.ma/) – Barid Al-Maghrib and Geopost, payment on delivery in cash or by card, home or agency delivery, more than 300 destinations, agencies and hours, phone, email
- [Chronodiali: Suivre mon colis](https://chronodiali.ma/suivi_colis.html) – tracker, `CHPU` example, amount to pay, French status lines, which of them the page shows, and "En cours de traitement." (in the page's script)
- [Chronodiali: Nos services](https://chronodiali.ma/nos_service.html) – three delivery attempts, SMS and email notifications, cash or card, digital proof of delivery
- [Chronodiali: Points de livraison](https://chronodiali.ma/point_de_livraison.html) – delivery all over Morocco, agencies and hours
- [Chronodiali: Envoyer un colis](https://chronodiali.ma/envoyercolis.html) – shops' accounts and pickups, proofs of delivery for shops
- [Chronodiali: Contact](https://chronodiali.ma/contact_us.html) – contact form with "Réclamation ?" and its fields, phone, email
- [Chronodiali: Gouvernance](https://chronodiali.ma/aboutus.html) – Barid Al-Maghrib (Poste Maroc)
- [Chronodiali: Actualités](https://chronodiali.ma/actualites.html) – joint venture, 51% Barid Al-Maghrib and 49% Geopost
- [Chronodiali: Mention légale](https://chronodiali.ma/mentions_legal.html) – Chrono Diali S.A., Casablanca; terms: the shop's contract and pickups, delivery times given at order
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shapes, status lines and their stages, what Chronodiali's page and Peek show
:::
