---
title: Chronopost tracking: numbers, statuses and pickup points
description: Track a Chronopost parcel in or from France: the tracking number format, what the French statuses mean, Pickup relays, missed deliveries and contacts.
slug: chronopost-tracking
published: 2026-10-11
updated: 2026-10-27
---

Chronopost is the express parcel service of the French La Poste group. Track it on Chronopost's tracking page, in the myChronopost app or in the box above, with the number from Chronopost's email or SMS.

## Chronopost tracking number format

Chronopost's number has 13 characters (two letters, nine digits, two letters), or 15 for parcels from abroad. Type it without spaces. It arrives by email or SMS; if not, check your spam or ask the shop, or see [how to find a tracking number](guide:find-tracking-number).

| Shape (invented) | What it is |
| --- | --- |
| `XY000000005FR` | Two letters, nine digits, two letters: the usual Chronopost number. |
| `AB000000000JB` | The same shape ending in JB, JF or TS. |
| `00000000000000U` | 15 characters, 14 digits and a check letter: a parcel from abroad. |

The 13-character shape is the international postal format: a number ending FR may be [La Poste](carrier:la-poste)'s Colissimo, one ending CH, GB or DE another country's post. The 15-character shape is also DPD's, so it may be a [DPD Switzerland](carrier:dpd-ch) or [DPD Germany](carrier:dpd-de) parcel. If Chronopost doesn't know it, check the carrier the shop's email names.

## What Chronopost's tracking statuses mean

Chronopost's tracking statuses are in French. In the order a parcel meets them:

| Chronopost's status | What it means for you |
| --- | --- |
| `Colis en cours de préparation chez l'expéditeur` | Label made; the shop hasn't handed the parcel over. |
| `Colis déposé par l'expéditeur` | The shop has handed it to Chronopost. |
| `Tri effectué dans l'agence de départ` | Sorted at the first depot. |
| `Colis en cours d'acheminement` | Moving through Chronopost's network. |
| `Tri effectué dans l'agence de distribution` | Sorted at your delivery depot. |
| `Livraison reportée de 24h` | The depot keeps it a day without trying; delivery resumes by itself. |
| `Colis en cours de livraison` | Out for delivery today. |
| `Echec de livraison suite à l'absence du destinataire.` | Nobody was home. |
| `Colis mis à disposition au point de retrait` | Waiting for you at a pickup point. |
| `Colis en retour expéditeur` | Going back to the shop. |
| `Livraison effectuée` | Delivered. |

`Livraison reportée de 24h` (a missort, an address check, a closed business) doesn't mean the parcel is lost. `Destinataire informé par SMS ou mail` and `Instruction de livraison reçue` only record a notice or your instruction; the parcel hasn't moved. A `Livraison effectuée` after `Colis en retour expéditeur` means the parcel is back with the shop.

## Chronopost delivery, point relais and missed deliveries

Chronopost delivers from 8 am to 10 pm, Monday to Sunday except public holidays, according to the option the shop chose.

With Predict, Chronopost notifies many recipients the day before by email or SMS; until an hour before, you can pick another date (up to 6 working days later), a neighbour, a safe place or a nearby pickup point. Use Chronopost's tracking, or tell its chatbot Léonard "Donner une instruction". No options shown means the parcel probably isn't eligible; the shop's choices also limit them.

If you're out, you get a calling card and the parcel waits, depending on the service, at the nearest Pickup relay, post office or Chronopost depot; wait for the message saying it's ready. In mainland France it stays:

- 14 days at a post office or depot;
- 7 calendar days at a Pickup relay;
- 5 days in a locker.

Abroad it varies. Uncollected parcels go back to the shop.

At a relay or locker you need the Pickup Pass (QR code) or 6-digit code sent by email or SMS; lockers open around the clock. Someone else can pick it up with a proxy form ("procuration"), their ID and yours. For secure home delivery, the driver needs that code too.

## Chronopost parcel stuck, not delivered or lost

**No update.** For parcels to and from mainland France, Chronopost asks you to contact customer service after 48 hours without change, not counting weekends and public holidays. See [tracking that isn't updating](guide:tracking-not-updating).

**Late.** Léonard can email you updates. Complaints about a late delivery go to the shop, which booked it.

**Lost or damaged.** Claim through the shop. If it arrives damaged, write precise, dated and signed reservations on the driver's slip, then contact the shop. Claim deadlines are the sender's: 14 days from delivery for a private sender, 21 internationally.

**Marked delivered but not received.** Tell Léonard "contester livraison" with the number. Signed for: customer service investigates. Handed over with a code or Pickup Pass: contact the shop quickly. Left in a safe place or with someone: check there first, then contact customer service. See [delivered but not received](guide:delivered-not-received).

**Returned.** A wrong address, an uncollected parcel or a refusal sends it back.

## Chronopost customer service in English

Chronopost's channels, from its English and French contact pages, read on 11 October 2026:

- **Léonard**, the chatbot on chronopost.fr, available at any time, for tracking questions and instructions.
- **Phone** +33 (0)969 391 391, standard rates, Monday to Friday 8 am to 7 pm and Saturday 8 am to noon, Paris time, excluding public holidays. In France: 09 69 391 391. No English-speaking line is promised.
- **X** @ChronopostSAV and **Messenger** @Chronopost.
- Shop2Shop parcels: chronoshop2shop.fr.

If you dispute customer service's answer, turn to the Médiateur du Groupe La Poste.

> Chronopost warns of fake SMS and emails. Its SMS come from "Chronopost" with no number, and by default it asks for no ID or delivery fees for deliveries in France. Forward doubtful emails to abuse@chronopost.fr, SMS to 33700.

## Tracking Chronopost parcels with Peek

[Peek](/) recognises 13-character numbers at once when they start PZ, XR, XT, XU, XW or XY with a valid check digit, or end JB, JF or TS. It asks Chronopost alone about other numbers of that shape (valid FR ones with other prefixes go to La Poste) and about 15-character ones starting 1550. For 14 digits and a check letter it asks Chronopost, DPD Switzerland and DPD Germany; if two know it, or none does, you pick with "Change". Chronopost and chronoshop2shop.fr tracking links work too. No postcode is needed.

Wordings stay French, except `Livraison effectuée` ("Delivered"). Peek maps each scan's office and shows the destination country, delivery time, partner's number abroad and, while the parcel waits, the relay or locker's name and address. An expected day appears only once a redelivery is booked. It drops your address and the point's contacts. A parcel to Germany with a partner number can be followed to DPD Germany. After a return starts, delivery counts as returned to sender.

Peek checks every 10 minutes by day (every 2 once out for delivery) and every hour or two overnight, and can notify you of changes. A scan Chronopost gives without a time zone is read on the clock of its place.

## Questions about Chronopost tracking

### Is Chronopost part of La Poste?

Yes. Chronopost belongs to Geopost, which belongs to La Poste Groupe.

### Is Chronopost the same as DPD?

Chronopost doesn't present itself as DPD: it is a member of Geopost, La Poste's parcel holding. Its numbers for parcels from abroad can have DPD's shape.

### Does Chronopost deliver on Saturday?

It can, if the shop chose that option: Chronopost delivers Monday to Sunday, outside public holidays.

### How long does Chronopost keep a parcel at a Pickup relay?

Seven calendar days in mainland France; it goes back on the 8th day.

:::sources
- [Chronopost: Where can I find my parcel number?](https://www.chronopost.fr/en/faq/recipient/where-can-i-find-my-parcel-number) – number format
- [Chronopost: Suivre votre colis](https://www.chronopost.fr/fr/suivi-colis) – tracking, Léonard, fraud warning
- [Chronopost: When will my parcel be delivered?](https://www.chronopost.fr/en/faq/recipient/when-will-my-parcel-be-delivered) – 8 am to 10 pm, Monday to Sunday
- [Chronopost: I want to change the date or address of my delivery](https://www.chronopost.fr/en/faq/recipient/I-want-to-change-the-date-or-address-of-my-delivery) – Predict, calling card
- [Chronopost: Service Predict](https://www.chronopost.fr/fr/livraison/nos-services/predict) – notice times, 6 working days
- [Chronopost: Combien de temps mon colis peut-il rester au point de proximité ?](https://www.chronopost.fr/fr/faq/destinataire/combien-de-temps-mon-colis-peut-il-rester-au-point-de-proximite) – holding times, proxy
- [Chronopost: Livraison sur présentation d'un code sécurisé](https://www.chronopost.fr/fr/faq/destinataire/quest-ce-quune-livraison-sur-presentation-dun-code-securise-ou-dun-code-pin) – 6-digit code, Pickup Pass
- [Chronopost: Que faire si mon colis est en retard ?](https://www.chronopost.fr/fr/faq/destinataire/que-faire-si-mon-colis-est-en-retard-ou-livre-en-retard) – 48-hour rule, late parcels
- [Chronopost: Will I be compensated if my parcel is lost or damaged?](https://www.chronopost.fr/en/faq/recipient/will-i-be-compensated-if-my-parcel-lost-or-damaged) – lost and damaged parcels
- [Chronopost: Je n'ai pas reçu mon colis qui est indiqué livré](https://www.chronopost.fr/fr/faq/destinataire/je-nai-pas-recu-mon-colis-qui-est-indique-livre) – contester livraison
- [Chronopost: Points Pickup](https://www.chronopost.fr/fr/relais-pickup) – lockers open 24/7
- [Chronopost: Suis-je indemnisé en cas de perte ou d'avarie ?](https://www.chronopost.fr/fr/faq/expediteur/suis-je-indemnise-en-cas-de-perte-ou-davarie) – sender claim deadlines
- [Chronopost: Pourquoi mon colis m'a-t-il été retourné ?](https://www.chronopost.fr/fr/faq/expediteur/pourquoi-mon-colis-m-a-t-il-ete-retourne) – reasons for return
- [Chronopost: Contact us](https://www.chronopost.fr/en/private/contact-us) – phone, hours, X, Messenger
- [Chronopost: Nous contacter](https://www.chronopost.fr/fr/particulier/nous-contacter) – Léonard, French number, Shop2Shop
- [Chronopost: Contact médiateur](https://www.chronopost.fr/fr/contact-mediateur) – La Poste group mediator
- [Chronopost: Eviter les tentatives d'escroqueries](https://www.chronopost.fr/fr/eviter-les-tentatives-descroqueries) – SMS sender name, no fees in France
- [Geopost: leboncoin is teaming up with Chronopost](https://www.geopost.com/en/news/leboncoin-is-teaming-up-with-chronopost/) – Chronopost in Geopost, Shop2Shop
- [Geopost: About us](https://www.geopost.com/en/about-us/) – Geopost belongs to La Poste Groupe
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
