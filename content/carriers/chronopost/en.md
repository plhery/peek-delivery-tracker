---
title: Chronopost tracking: numbers, statuses and pickup points
description: Track a Chronopost parcel in or from France: the tracking number format, what the French statuses mean, Pickup relays, missed deliveries and contacts.
slug: chronopost-tracking
published: 2026-10-11
updated: 2026-10-27
---

Chronopost is the express parcel service of the French La Poste group. Track it on Chronopost's tracking page or in the box above.

## Chronopost tracking number format

Chronopost's number usually has 13 characters (two letters, nine digits, two letters), or 15 for parcels from abroad. If you don't have it, ask the shop, or see [how to find a tracking number](guide:find-tracking-number).

| Shape (invented) | What it is |
| --- | --- |
| `XY000000005FR` | Two letters, nine digits, two letters: the usual Chronopost number. |
| `AB000000000JB` | The same shape ending in JB, JF or TS. |
| `00000000000000U` | 15 characters, 14 digits and a check letter: a parcel from abroad. |

The 13-character shape is the international postal format: a number ending FR may be [La Poste](carrier:la-poste)'s Colissimo, one ending CH, GB or DE another country's post. The 15-character shape is also DPD's, so it may be a [DPD Switzerland](carrier:dpd-ch), [DPD Germany](carrier:dpd-de), [DPD Portugal](carrier:dpd-pt) or [DPD UK](carrier:dpd-uk) parcel. If Chronopost doesn't know it, check the carrier the shop's email names.

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

If you're out, the parcel can wait at a pickup point: in France, Chronopost's are Pickup relays and lockers, post offices and its own depots. At a Pickup point you get an email or SMS when it's ready, with the deadline to collect it. Pickup says that deadline is never more than:

- 8 days at a Pickup relay;
- 5 days in a Pickup locker.

After that deadline, the parcel goes back to the sender automatically.

At a Pickup relay or locker you need the Pickup Pass (QR code) or the codes sent by email or SMS; at a relay, your original ID also works. Lockers are self-service and can be open around the clock. Someone else can collect it with your Pickup Pass or codes, or at a relay with the originals of their ID and yours; Pickup accepts no written proxy.

## Chronopost parcel stuck, not delivered or lost

**No update or late.** Complaints about a late delivery go to the shop, which booked it: in France, an online shop alone answers for a delivery by the carrier it chose. See [tracking that isn't updating](guide:tracking-not-updating).

**Lost or damaged.** Claim through the shop. If it arrives damaged, you can note it on the delivery slip, then contact the shop's customer service quickly. At a Pickup relay, report the damage to the shopkeeper, who records it; you can still take the parcel or refuse it.

**Marked delivered but not received.** Contact the shop: if it can't prove you received the parcel, the loss is its risk. See [delivered but not received](guide:delivered-not-received).

## Chronopost customer service

Chronopost's own contact page is on chronopost.fr. La Poste Groupe and Pickup also name:

- **Léonard**, Chronopost's chatbot, for delivery questions; complex cases go to a person.
- **Phone** 09 69 39 13 91.
- Shop2Shop parcels: chronoshop2shop.fr.

If Chronopost leaves a written, dated and signed complaint unanswered for at least two months, or you dispute its answer, turn to the Médiateur du Groupe La Poste, within a year of the complaint.

> Scam SMS and emails use Chronopost's name. Chronopost says it asks for no ID or delivery fees for deliveries in France. Forward doubtful emails to abuse@chronopost.fr and report scam SMS to 33700.

## Tracking Chronopost parcels with Peek

[Peek](/) recognises 13-character numbers at once when they start PZ, XR, XT, XU, XW or XY with a valid check digit, or end JB, JF or TS. It asks Chronopost alone about those prefixes when the check digit fails and about numbers ending RV or VF; valid ones ending FR with other prefixes go to La Poste. For 14 digits and a check letter it asks Chronopost and the DPD companies that share the shape (DPD Switzerland, Germany, Portugal and UK); if two know it, or none does, you pick with "Change". Chronopost and chronoshop2shop.fr tracking links work too. No postcode is needed.

Wordings stay French, except `Livraison effectuée` ("Delivered"). Peek maps each scan's office and shows the destination country, delivery time, partner's number abroad and, while the parcel waits, the relay or locker's name and address. An expected day appears only once a redelivery is booked. It drops your address and the point's contacts. A parcel to Germany with a partner number can be followed to DPD Germany. After a return starts, delivery counts as returned to sender.

Peek checks every 10 minutes by day (every 2 once out for delivery) and every hour or two overnight, and can notify you of changes. A scan Chronopost gives without a time zone is read on the clock of its place.

## Questions about Chronopost tracking

### Is Chronopost part of La Poste?

Yes. Chronopost belongs to Geopost, which belongs to La Poste Groupe.

### Is Chronopost the same as DPD?

Chronopost doesn't present itself as DPD: it is a member of Geopost, La Poste's parcel holding. Its numbers for parcels from abroad can have DPD's shape.

### How long does Chronopost keep a parcel at a Pickup relay?

Up to 8 days, says Pickup, which runs the relays; the email or SMS gives the exact deadline. After that the parcel goes back to the sender.

:::sources
- [La Poste Groupe: Chronopost](https://www.lapostegroupe.com/en/focus/la-poste-groupes-subsidiaries/chronopost) – express parcels; pickup points in France: depots, post offices, Pickup points
- [La Poste Groupe: 2025 Integrated Report](https://www.lapostegroupe.com/assets/media/le-groupe-laposte/ad4Q_p1ZCF7ETKqb_MEL_LAPO_RI_seul_2025_EN_14042026.pdf) – Léonard, Chronopost's chatbot (p. 18)
- [Pickup: FAQ](https://www.pickup.fr/faq-particulier/) – notice when ready, 8 days at a relay for Chronopost, 5 in a locker, return to sender, Pickup Pass and codes, collecting for someone else, lockers up to 24/7, Chronopost's phone number, damage at a relay
- [Service-Public: Achat à distance, livraison du bien](https://www.service-public.gouv.fr/particuliers/vosdroits/F10037) – the seller answers for the delivery, damaged and missing parcels
- [Médiateur du groupe La Poste: Conditions de saisine](https://mediateur.groupelaposte.com/pages/conditions-requises-a-la-saisine-du-mediateur-de-la-consommation-du-groupe-la-poste) – Chronopost disputes, two months, one year
- [Cybermalveillance.gouv.fr: L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – no ID or fees in France, abuse@chronopost.fr, 33700
- [Geopost: leboncoin is teaming up with Chronopost](https://www.geopost.com/en/news/leboncoin-is-teaming-up-with-chronopost/) – Chronopost in Geopost, Shop2Shop
- [Geopost: About us](https://www.geopost.com/en/about-us/) – Geopost belongs to La Poste Groupe
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
