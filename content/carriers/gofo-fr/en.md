---
title: GOFO France tracking: GFFR numbers, statuses and help
description: Track a GOFO parcel in France: what GFFR and CIFR numbers look like, what GOFO's French statuses mean, and who to ask when a parcel is late or lost.
slug: gofo-france-tracking
published: 2026-10-11
updated: 2026-10-27
---

GOFO France is the French network of GOFO, a parcel carrier with networks in several countries, and it delivers parcels within France. Peek's carrier list also knows it as CIRRO Parcel France. Its tracking numbers start with `GFFR` or `CIFR`: track one on GOFO's French tracking page, on gofo.com, or paste it in the box above. No number yet? See [how to find a tracking number](guide:find-tracking-number).

## GOFO tracking number: what it looks like

These are the shapes Peek files under GOFO France, in capitals or not:

| Shape | Invented example |
| --- | --- |
| GFFR and 13 digits | `GFFR1234567890123` |
| GFFR and 14 digits | `GFFR12345678901234` |
| CIFR and 13 digits | `CIFR1234567890123` |
| CIFR and 14 digits | `CIFR12345678901234` |

A shop's own reference can stand in for the number: `PK` and 20 digits, sometimes written with hyphens, as in `PK-1234567890123456789-0` (invented). GOFO France's tracking answers for those references too, and GOFO keeps them beside its own `GFFR` or `CIFR` waybill.

GOFO's other networks follow the same pattern, with their own country after `GF` or `CI`. `GFIT` or `CIIT` numbers belong to [GOFO Italy](carrier:gofo-it), `GFNL` to GOFO Netherlands and `GFES` to GOFO Spain. `GFUS` with 14 digits, `GF` with only 13, or `CR` with 12 belong to [GOFO Express](carrier:gofo) in the United States, a separate network, and `GFCA` with 14 digits to [GOFO Canada](carrier:gofo-ca). None of these is a GOFO France number. More on telling shapes apart: [tracking number formats](guide:tracking-number-formats).

## GOFO tracking statuses: what the French words mean

Peek reads GOFO's French tracking, so the scans appear in GOFO's French words whatever your language. The usual ones, in the order a parcel meets them:

| GOFO's words | What it means for you |
| --- | --- |
| `Votre colis est en cours de préparation par l'expéditeur` | The shop is preparing the parcel; GOFO doesn't have it yet. |
| `Expédition en transit vers GOFO` | On its way to GOFO, which hasn't received it yet. |
| `Arrivé au site de prise en charge` | At the GOFO site that takes it in: GOFO's first scan of it. |
| `Votre colis a été pris en charge au centre de tri` | Scanned in at a GOFO sorting centre. |
| `Colis en cours d'acheminement` | Moving between GOFO sites. |
| `Votre colis est arrivé à l’agence régionale GOFO de destination` | At GOFO's regional agency for your area, which delivers it. |
| `Le chauffeur est en route pour la livraison` | Out with the driver. |
| `Échec de la livraison, motif : Le destinataire n'est pas chez lui` | Not delivered: nobody was home. |
| `Livré` | Delivered. |

The first two lines trip people up: until `Arrivé au site de prise en charge` or the sorting centre's scan appears, GOFO doesn't have the parcel, and Peek shows it as "Label created". A delivery shows as `Livré` alone, in every language: GOFO's own delivery line can say more, including details about the recipient, which Peek leaves out.

GOFO's tracking also sorts parcels into broad categories. `Alerte` is a problem GOFO has flagged, which Peek shows as "Needs attention"; on its own it doesn't mean a delivery was attempted. `Retourné` is a parcel going back to the sender, shown as "Returned to sender".

## GOFO parcel late, missed, lost or damaged

**Not moving.** Peek keeps asking GOFO. Once a parcel has gone four days without a new scan, Peek's parcel page says so and links to GOFO France's help page ("Get help from GOFO France"). See also [why tracking stops updating](guide:tracking-not-updating).

**Missed delivery.** After `Échec de la livraison, motif : Le destinataire n'est pas chez lui`, Peek shows "Delivery attempted"; a failed attempt with another reason after `motif :` shows as "Needs attention". What happens next is up to GOFO: see [missed deliveries](guide:missed-delivery) for what to check.

**Late.** Under EU consumer rules, a shop must deliver within 30 days unless you agreed on another time. If it doesn't, give it an extra, reasonable deadline; if that passes too, you can cancel the order and get your money back.

**Marked `Livré` but not there.** The shop is responsible for delivering your order: it's up to the shop to contact the carrier and prove the parcel was delivered, and if it can't, to send the item again or refund you. For what to check around your home first, see [delivered but not received](guide:delivered-not-received).

**Damaged.** The shop is responsible for any damage from dispatch until you receive the parcel, even if it happened in transport. When you can, check the parcel in front of the driver. More in [damaged parcels](guide:damaged-parcel).

## GOFO France customer service

GOFO France's help page is on its French site, gofo.com, and Peek links to it as "Get help from GOFO France" when a parcel stops moving. For a late, lost or damaged order, start with the shop that sent it, since the shop answers for the delivery. Keep your `GFFR` or `CIFR` number and your order number at hand.

## Tracking a GOFO France parcel with Peek

[Peek](/) recognises `GFFR` and `CIFR` numbers by themselves. You can also paste a link from GOFO's French tracking page (on gofo.com or gofoexpress.fr, with `id=` and the number). A `PK` reference works too: Peek asks GOFO France about it and names the carrier once GOFO knows the number.

Peek reads GOFO's French tracking every 10 minutes by day, every 2 minutes once the parcel is out for delivery, and every hour or two overnight. It shows the status, the history with the place GOFO names for each scan, the service name and the delivery time, each scan on a map, and it can notify you of every scan, important steps only or delivery day only. Until GOFO lists a first scan, Peek shows "Waiting for the carrier" and keeps asking rather than calling the number unknown. Peek shows no expected delivery date and no weight, and it leaves out delivery photos and GOFO's notices. It can't contact GOFO or the shop for you.

## Questions about GOFO tracking

### What does "Expédition en transit vers GOFO" mean?

The parcel is on its way to GOFO, which hasn't received it yet. GOFO's own first scan comes next: `Arrivé au site de prise en charge` or `Votre colis a été pris en charge au centre de tri`. Until then, Peek shows the parcel as "Label created".

### Is GOFO legit?

Yes. GOFO FRANCE is a French SAS listed in INSEE's Sirene register under Siren 978 559 839, active since May 2023, with its head office in Marly-la-Ville (Val-d'Oise).

### Is GOFO the same as CIRRO Parcel?

In France, for tracking purposes, yes: Peek's carrier list gives GOFO France the other name CIRRO Parcel France. A tracker that says only "Cirro" may mean GOFO's French, Italian or Dutch network, or Cirro's own cross-border line, which hands parcels to national posts under their numbers.

### What can I do if my GOFO parcel is lost?

Contact the shop. Under EU rules it answers for the delivery until the parcel reaches you, so if it can't prove delivery, it must send the item again or refund you. More in [lost parcels](guide:lost-parcel).

:::sources
- [INSEE: Avis de situation au répertoire Sirene](https://avis-situation-sirene.insee.fr/) – GOFO FRANCE, Siren 978 559 839, SAS, active since 26 May 2023, head office in Marly-la-Ville
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – delivery within 30 days, extra deadline, damage until receipt
- [Your Europe: FAQs, shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_en.htm) – the shop proves delivery, checking the parcel on delivery
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shapes, PK references, links, French scan wordings and categories, CIRRO Parcel France, what Peek shows and leaves out
:::
