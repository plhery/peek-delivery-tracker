---
title: Relais Colis tracking: statuses, pickup and contact
description: Track a Relais Colis parcel in France: where the CC number is, what the French statuses mean, how long a relay keeps it and who to contact.
slug: relais-colis-tracking
published: 2026-10-11
updated: 2026-10-27
---

Relais Colis is a French network delivering parcels to local shops that act as pickup points, now part of [GLS France](carrier:gls-fr). Track a parcel on [Relais Colis's tracking page](https://www.relaiscolis.com/colis/suivre) with its parcel number, or paste the number in the box above. The number comes from the shop that sent it, or from Relais Colis's confirmation email for a parcel sent between people.

## Relais Colis tracking number (numéro de colis)

Relais Colis's tracking form asks for a "numéro de colis" of 12 to 16 characters; its example starts with `CC`. Relais Colis doesn't explain what the letters or digits mean.

| Shape (invented) | What to know |
| --- | --- |
| `CC1234567890` to `CC12345678901234` | `CC` and 10 to 14 digits: the form Relais Colis's own example takes. |
| `1234567890` | 10 digits. Also fits DHL, [Mondial Relay](carrier:mondial-relay), [TIPSA](carrier:tipsa) and others. |
| `12345678901234` | 14 digits. Also fits DPD, SEUR, BRT, [Ciblex](carrier:ciblex) and others. |
| `VD1234567890` | `VD` and 10 digits. Also fits [Colis Privé](carrier:colis-prive). |

Between people, Relais Colis emails the recipient a confirmation to follow the parcel; for a shop order, look in the shop's shipping email or order page. No number? Relais Colis's "Je n'ai pas mon numéro de colis" search uses the recipient's email or phone and the shop's name; see also [how to find a tracking number](guide:find-tracking-number).

If the shop names another carrier, the number is probably theirs: see [tracking number formats](guide:tracking-number-formats).

## Relais Colis suivi: what the statuses mean in English

Relais Colis's site and tracking are in French only, and it publishes no list of statuses. Each tracking line is a French sentence; look for these words, roughly in the order a parcel meets them:

| Relais Colis says | What it means for you |
| --- | --- |
| `colis a été annoncé` | The shop has announced the parcel; Relais Colis doesn't have it yet. |
| `pris en charge` | Relais Colis has the parcel. |
| `en transit` | It's moving through Relais Colis's network. |
| `livraison dans votre relais` | It's on its way to your relay, not there yet. |
| `en cours de livraison` | Out for delivery. |
| `disponible dans votre relais` | Ready for pickup at your Relais Colis. |
| `retiré par le destinataire` | Picked up by you: delivered. |
| `livraison impossible` | Delivery failed. |
| `retourné à votre vendeur` | Going back to the shop that sent it. |
| `Aucune donnée de suivi pour votre colis, veuillez réessayer plus tard` | The tracking page has no data for this number yet. |

"Votre vendeur" is the shop that sold it. The "no data" message also appears for a mistyped number: check it and try again later.

## Picking up a parcel from a Relais Colis point

**The notice.** When the parcel reaches the relay you chose, Relais Colis sends you an email or SMS.

**How long it waits.** 7 working days, which is about 9 calendar days, up to 11 around public holidays (Relais Colis doesn't count Mondays, Sundays, public holidays or the Tuesday after one). After that, a shop order goes back to the shop, and a parcel sent through relaiscolis.com goes back to the relay it was sent from.

**Collecting it.** Show your QR code, or ID (identity card, passport or driving licence) if you didn't get one.

**Someone else collecting.** They need a proxy (procuration) signed by you, your identity card and theirs.

**Relay closed.** The parcel goes to the nearest Relais Colis, and you get a message with its address.

**Changes.** Relais Colis says the arrival relay can't be changed, and a relay parcel can't be switched to home delivery. Find relays with [Trouver un Relais](https://www.relaiscolis.com/relais/trouver).

**Home delivery.** Relais Colis's FAQ says its team contacts you by email or phone to set an appointment, and again after a missed one; if nobody did, claim through the shop.

## Relais Colis suivi bloqué: late, lost or damaged parcels

For a shop order, the shop holds the contract with Relais Colis, so delays, losses and damage go through it. Relais Colis's own forms are for labels bought on relaiscolis.com.

- **Not moving:** Relais Colis says it handles routing incidents within 15 days. For a label bought on relaiscolis.com, claim with the parcel number if it hasn't reached the relay within the average 3 to 6 working days. See [why tracking stops updating](guide:tracking-not-updating).
- **Lost:** shop order, claim with the shop. A parcel sent through relaiscolis.com goes to Relais Colis's Service Client by email.
- **Damaged:** if the packaging is damaged, tell the relay shop you refuse the parcel. If you accept it, you have 3 days to contact Relais Colis by email and 5 days to send photos of the outside, the inside and the damaged item. For a shop order, claim with the shop.

## Relais Colis customer service and contact

As listed on relaiscolis.com on 11 October 2026; everything is in French.

- **Phone:** 09 70 25 22 31, at the price of a local call. No hours are given.
- **Claim form:** the FAQ's "Pour une réclamation" link leads to Relais Colis's help centre, which didn't open on 11 October 2026.
- **Email:** contactctoc@relaiscolis.com, for lost parcels sent through relaiscolis.com.

> Relais Colis warns of fake emails and calls in its name. Its addresses all end in @relaiscolis.com, it never asks for personal data by phone or email, and you shouldn't call back a premium-rate number.

## Tracking Relais Colis parcels with Peek

[Peek](/) recognises `CC` numbers as Relais Colis's at once. Other shapes are shared, so Peek asks the likely carriers and shows "asking…" first: for 10 digits it asks Relais Colis, TIPSA and [DHL eCommerce Spain](carrier:dhl-ecommerce-es) (and [DHL Express](carrier:dhl-express) first when the number passes DHL Express's check digit), and for `VD` numbers Colis Privé and Relais Colis. With 14 digits, Relais Colis is asked first only for readers in France; elsewhere it may not be asked at all. If several carriers know the number, or none does, press "Change" and pick Relais Colis. A relaiscolis.com tracking link works too. No postcode is needed.

Peek shows the status and history only: no place on the map, no expected delivery and no details of the relay holding the parcel; it drops those and your name and address. Wordings stay in French as Relais Colis writes them; only a few exact ones, such as `Livraison effectuée`, are replaced by Peek's own label ("Delivered"). Times are read as Paris time. When Relais Colis's latest status has no exact time, Peek takes the dated history from other tracking services and asks Relais Colis again every 6 hours. Otherwise it checks every 10 minutes by day, every 2 once out for delivery, and every hour or two overnight, and can notify you of changes.

## Questions about Relais Colis tracking

### What is Relais Colis?

A French network delivering parcels to nearly 7,000 partner shops, says GLS, which bought it in 2026. It began in 1969 as SOGEP, created for La Redoute.

### Why is my Relais Colis tracking not moving?

Relais Colis says it handles routing incidents within 15 days. For a shop order, ask the shop, which deals with Relais Colis.

### Does Relais Colis deliver on Saturdays?

Yes: for parcels sent through relaiscolis.com, its terms say Relais Colis delivers from Tuesday to Saturday.

### Does Relais Colis deliver outside France?

No. Relais Colis says it doesn't deliver to the French overseas territories or abroad for now.

:::sources
- [Relais Colis: Suivre mon colis](https://www.relaiscolis.com/colis/suivre) – number length, example, not-found message
- [Relais Colis: Comment suivre mon colis ?](https://www.relaiscolis.com/faq/question/comment-suivre-mon-colis) – 12 to 16 characters, search without number
- [Relais Colis: Prévenir le destinataire](https://www.relaiscolis.com/faq/question/pr%C3%A9venir%20arriv%C3%A9e%20destinataire) – email or SMS, holding time
- [Relais Colis: Combien de jours mon colis est-il gardé ?](https://www.relaiscolis.com/faq/question/combien-jours-mon-colis-est-il-gard%C3%A9-relais-colis) – 7 working days
- [Relais Colis: Comment retirer mon colis ?](https://www.relaiscolis.com/faq/question/comment-retirer-mon-colis) – QR code or ID
- [Relais Colis: Colis non récupéré](https://www.relaiscolis.com/faq/question/personne-nest-venu-recuperer-le-colis-que-va-t-il-se-passer) – after the deadline
- [Relais Colis: Retard de livraison](https://www.relaiscolis.com/faq/question/que-faire-en-cas-de-retard-de-livraison) – 3 to 6 working days, who claims
- [Relais Colis: Acheminement bloqué](https://www.relaiscolis.com/faq/question/acheminement-de-mon-colis-est-bloque) – 15 days
- [Relais Colis: Colis perdu](https://www.relaiscolis.com/faq/question/mon-colis-est-perdu-que-faire) – lost parcels, email
- [Relais Colis: Colis abîmé](https://www.relaiscolis.com/faq/question/mon-colis-est-abime-que-faire) – refusal, 3 and 5 days
- [Relais Colis: Conditions générales de vente](https://relaiscolis.com/page/conditions-generales-de-ventes) – working days, delivery days, QR code, compensation, mediator
- [Relais Colis: Besoin d'aide FAQ](https://www.relaiscolis.com/faq) – phone, claim link
- [Relais Colis: Signalement fraude](https://www.relaiscolis.com/page/signalement-fraude) – fraud warning
- [Relais Colis: Trouver un Relais](https://www.relaiscolis.com/relais/trouver) – relay finder
- [Relais Colis: Qui sommes nous ?](https://www.relaiscolis.com/page/qui-sommes-nous) – history, GLS
- [Relais Colis: Livraison à domicile](https://www.relaiscolis.com/faq/question/comment-suivre-mon-colis-livraison-a-domicile) – 10 to 16 characters
- [Relais Colis: Recherche avancée](https://www.relaiscolis.com/colis/recherche-avancee) – search without number
- [Relais Colis: Procuration](https://www.relaiscolis.com/faq/question/je-ne-suis-pas-le-destinataire-du-colis-puis-je-le-recuperer-a-sa-place) – proxy
- [Relais Colis: Relais fermé](https://www.relaiscolis.com/faq/question/mon-relais-colis-est-ferme-ou-est-mon-colis) – closed relay
- [Relais Colis: Changer de relais](https://www.relaiscolis.com/faq/question/comment-changer-de-commercant-relais-colis) – no change
- [Relais Colis: Domicile plutôt que relais](https://www.relaiscolis.com/faq/question/mon-destinataire-souhaite-recevoir-son-colis-a-domicile-plutot-que-relais-colis) – no switch
- [Relais Colis: Rendez-vous à domicile](https://www.relaiscolis.com/faq/question/comment-connaitre-la-date-et-lheure-de-ma-livraison-a-domicile) – appointment
- [Relais Colis: Absent](https://www.relaiscolis.com/faq/question/jetais-absente-lors-de-la-livraison-de-mon-colis-comment-replanifier-un-rendez-vous) – new appointment
- [Relais Colis: Personne ne m'a contacté](https://www.relaiscolis.com/faq/question/livraison-domicile-personne-ne-ma-contacte) – the shop
- [Relais Colis: Étranger](https://www.relaiscolis.com/faq/question/faites-vous-des-livraisons-dans-les-dom-tom-ou-a-letranger) – France only
- [GLS France: GLS France acquiert le réseau Relais Colis](https://gls-group.com/FR/fr/relais-colis-gls/) – about 7,000 relays
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::

