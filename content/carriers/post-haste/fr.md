---
title: Suivi Post Haste : tickets NZ, codes d’envoi et aide
description: Suis un colis Post Haste Couriers en Nouvelle-Zélande : formats de tickets et bordereaux, statuts Freightways, relivraison et agences.
slug: suivi-post-haste
published: 2026-11-24
updated: 2026-11-24
---

**Post Haste** (*Post Haste Couriers*) est un réseau de messagerie néo-zélandais qui a rejoint le **groupe Freightways** en 1977 et travaille en partenariat avec Castle Parcels ([posthaste.co.nz](https://posthaste.co.nz/)). Présent sur l’île du Nord comme sur l’île du Sud grâce à plus de 20 agences régionales et des partenaires Delivery Link, Post Haste assure des livraisons locales, régionales du lendemain, inter-îles du lendemain et économiques en deux jours, ainsi que la desserte des zones rurales. Tu peux consulter un colis sur [posthaste.co.nz/tools/track/](https://posthaste.co.nz/tools/track/) ou coller ton lien de suivi Post Haste ou ton code d’expédition dans le champ ci-dessus.

## Numéros de suivi Post Haste (tickets, étiquettes et liens)

Post Haste emploie plusieurs formats d’étiquettes Freightways selon que l’expéditeur utilise un ticket ou une pochette prépayée, une étiquette facturée à 8 chiffres, un code-barres SSCC à 20 chiffres ou une étiquette imprimée par le client (par exemple Despatch IT) :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `PH48291736` | Ticket de messagerie ou pochette Express Pack : code produit de 2 à 4 lettres (par exemple `PE` ou `PH`) + numéro de ticket de 5 à 8 chiffres (à saisir sans espaces). |
| `73918462` | Numéro d’étiquette facturée Post Haste à 8 chiffres. |
| `KLM4829173` | Numéro d’expédition imprimé par le client (lettres + 6 à 8 chiffres) ; certaines étiquettes imprimées par l’expéditeur nécessitent aussi le numéro de compte de l’expéditeur contenu dans le lien de suivi de ton e-mail (`receiver.posthaste.co.nz/?consignmentid=CU..…`). |

Si le vendeur t’a envoyé un lien `receiver.posthaste.co.nz` ou `posthaste.co.nz` par e-mail ou SMS, **colle ce lien complet directement dans Peek** : il transmet automatiquement le numéro d’expédition et, lorsqu’il est requis, le code compte de l’expéditeur. Consulte le guide des [formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi Post Haste

Post Haste repose sur le système de suivi partagé de Freightways (utilisé par Post Haste, Castle Parcels, New Zealand Couriers et Now Couriers) et indique des types et sous-types de scans accompagnés de la banlieue ou région :

| Code ou scan Post Haste | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Label created` | Étiquette électronique créée par le système d’expédition du vendeur avant l’enlèvement. | « Étiquette créée » |
| `P` (`Picked Up`) | Collecté chez l’expéditeur par un chauffeur Post Haste. | « Pris en charge » |
| `STAT/RZDEL` | Avis de zone rurale ou non urbaine ; le relais par un partenaire rural ajoute généralement 2 à 3 jours ouvrables. | « En transit » |
| `STAT/REDEL` | Reprogrammé et remis en acheminement après une livraison manquée. | « En transit » |
| `D` (`Delivery Handed Over` / `Delivered by courier or branch` vers un partenaire rural) | Remis à un sous-traitant de livraison rurale ; encore en route vers chez toi et pas encore à ta porte. | « En transit » |
| `ONBD` | Chargé dans la camionnette du chauffeur local pour livraison aujourd’hui. | « En livraison » |
| `NOND` / `NOND/*` | Événement de non-livraison (absence avec avis de passage, locaux fermés ou problème d’accès). | « Tentative de livraison » |
| `D`, `3P/D` | Livré à l’adresse du destinataire (y compris `3P/D` effectué par un partenaire rural). | « Livré » |

`STAT/HLDFR8` signifie que le colis est retenu au comptoir fret de l’agence après une livraison qui n’a pas abouti. Ce libellé ne dit pas si le colis attend un retrait ou une nouvelle tentative : demande une relivraison ou contacte l’agence.

## Délais, zones rurales et relivraison en Nouvelle-Zélande

Selon [Post Haste Sending & Receiving](https://posthaste.co.nz/services/sending-and-receiving/), [Redelivery](https://posthaste.co.nz/tools/redelivery/) et [Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/) :

- **Délais publiés :** livraison **locale** entre entreprises dans la même ville ; livraison **régionale** du lendemain (généralement vers midi le jour ouvrable suivant) ; livraison **nationale** du lendemain sur une même île ou entre les îles du Nord et du Sud (en général vers midi le jour ouvrable suivant pour les entreprises, en fin de journée pour les particuliers) ; service **économique inter-îles en deux jours** ; plus **livraison le samedi** dans les grandes agglomérations.
- **Adresses non urbaines et rurales :** Post Haste dessert la plupart des adresses rurales néo-zélandaises via des partenaires ruraux (`STAT/RZDEL`), ce qui ajoute deux à trois jours ouvrables aux délais urbains.
- **Adresse sans présence et relivraison :** par défaut, Post Haste ne laisse **pas** les colis à une adresse sans présence sauf autorisation préalable (*authority to leave*). En cas de livraison manquée, programme une relivraison en ligne sur [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) avec la référence de livraison à 16 chiffres, ou contacte ton agence locale si tu ne l’as pas. Apporte une pièce d’identité pour un retrait en agence.

## Colis Post Haste retardé, retenu ou introuvable

- **La remise rurale ressemble à une livraison sur certains suivis :** lorsqu’un scan indique `Delivered by courier or branch` ou `Delivery Handed Over`, le colis a en général été confié à un prestataire rural (`3P`) plutôt que déposé chez toi — compte 2 à 3 jours ouvrables pour la tournée rurale (`3P/D`). Peek maintient ces remises rurales sur « En transit » afin de ne pas les marquer livrées trop tôt.
- **Aucun scan après l’étiquette ou colis bloqué en dépôt :** si un numéro imprimé par le client ne répond pas seul, colle le lien complet `receiver.posthaste.co.nz` de ton e-mail d’expédition afin que Peek transmette le code compte de l’expéditeur. Si un colis reste bloqué en agence, contacte ton dépôt Post Haste local ou le vendeur.
- **Réclamations pour perte ou avarie :** consulte les règles de perte et d’avarie sur [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/) et contacte d’abord le commerçant qui a réservé le transport. Vois aussi les guides [livré mais non reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel), [retourné à l’expéditeur](guide:returned-to-sender) et [suivi bloqué](guide:tracking-not-updating).

## Service client Post Haste et agences en Nouvelle-Zélande

Coordonnées et annuaires officiels publiés sur [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) et [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/) :

- **Annuaire des agences locales (îles du Nord et du Sud) :** [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) rassemble les numéros de téléphone et adresses des agences de l’île du Nord (Whangārei, North Harbour, Auckland, Hamilton, Tauranga, Rotorua, New Plymouth, Taupō, Gisborne, Napier, Hastings, Whanganui, Palmerston North, Wellington) et de l’île du Sud (Nelson, Blenheim, West Coast, Christchurch, Timaru, Dunedin, Queenstown, Invercargill), y compris les agences partagées avec Castle Parcels.
- **Outil de relivraison en ligne :** [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/).
- **Centre d’aide destinataires et règles :** [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/).

> Méfie-toi des faux SMS affirmant qu’un colis néo-zélandais exige un petit paiement par carte pour replanifier la livraison. Passe toujours par [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) ou vérifie directement auprès de ton agence Post Haste. Consulte notre guide sur les [SMS frauduleux de livraison](guide:delivery-scam-texts).

## Suivre un colis Post Haste avec Peek

Le moyen le plus simple d’ajouter un envoi Post Haste dans [Peek](/) consiste à coller le lien complet de ton e-mail d’expédition (`receiver.posthaste.co.nz/?consignmentid=CU..…` ou `posthaste.co.nz/track?query=`), que Peek reconnaît automatiquement comme Post Haste et utilise pour extraire le compte expéditeur si nécessaire. Si tu saisis un numéro nu de ticket, d’étiquette facturée ou d’expédition (`PH48291736`, `73918462` ou `KLM4829173`, inventés), Peek ne reconnaît pas Post Haste sur le numéro seul (il interroge TNT Italie pour un ticket comme `PH48291736` et Australia Post pour un envoi comme `KLM4829173`) : appuie donc sur « Changer » lors de l’ajout ou « Changer de transporteur » sur la fiche du colis, puis sélectionne **Post Haste** (rechercher « Post Haste Couriers » ou « Posthaste » fonctionne aussi). Peek propose alors un champ facultatif « Lien de suivi complet » pour le lien reçu par e-mail, qui fournit le compte expéditeur requis par certains colis.

Peek lit la requête Freightways sous le nom de Post Haste et affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « En livraison », « Tentative de livraison », « Livré »), le libellé du scan, la banlieue et la région, la date de livraison estimée lorsque Post Haste en fournit une, ainsi que l’heure de livraison. Les types et sous-types de scans servent à définir l’étape et n’apparaissent pas dans les applications Peek. Les horaires de scan arrivent en instants UTC et s’affichent à l’heure de ton appareil. Lorsqu’un autre réseau Freightways, comme Castle Parcels, publie le scan le plus récent, le colis affiche « Livraison par » suivi du nom de ce réseau. Peek ne conserve ni la signature ni le nom du signataire, ni les photos de livraison, ni la mention d’autorisation de dépôt, ni les codes postaux, ni le chauffeur.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Post Haste affiche `ONBD`, toutes les 2 minutes à partir de 6 h heure de Nouvelle-Zélande, tant que le suivi de Post Haste répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi de Post Haste ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Post Haste. », avec un lien « Demande de l’aide à Post Haste » vers l’aide destinataires de Post Haste. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Post Haste. »

Tu peux choisir les alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek suit uniquement l’avancement du colis et ne peut pas réserver de relivraison, accorder une autorisation de dépôt ni contacter ton agence Post Haste à ta place.

## Questions sur le suivi Post Haste

### Quels numéros de suivi fonctionnent avec Post Haste ?

Post Haste suit les tickets de messagerie et pochettes Express Pack (2 à 4 lettres + 5 à 8 chiffres, par exemple `PH48291736`, inventé), les étiquettes facturées à 8 chiffres (`73918462`, inventé), les codes-barres SSCC à 20 chiffres et les numéros d’expédition imprimés par le client (`KLM4829173`, inventé) — ou bien colle le lien complet `receiver.posthaste.co.nz` de ton e-mail d’expédition.

### Pourquoi mon numéro d’expédition imprimé par le vendeur n’apparaît-il pas ?

Certaines étiquettes imprimées par le client (comme les envois Despatch IT) sont liées au numéro de compte de l’expéditeur. Colle le lien complet `receiver.posthaste.co.nz` de ton e-mail d’expédition afin que le code compte soit inclus dans la recherche.

### Que faire si j’ai manqué ma livraison Post Haste ?

Post Haste exige normalement une présence sauf autorisation de dépôt préalable. Utilise le formulaire en ligne sur [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) avec ta référence de livraison à 16 chiffres, ou appelle ton agence locale sur [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/).

### Pourquoi le suivi Post Haste indique-t-il `Delivery Handed Over` ou `STAT/RZDEL` ?

`STAT/RZDEL` signale une adresse en zone rurale ou non urbaine, et un scan de remise signifie que le colis a été confié à un partenaire rural — ce qui ajoute généralement 2 à 3 jours ouvrables avant la livraison finale à ta porte (`3P/D`).

:::sources
- [Post Haste: Official Website](https://posthaste.co.nz/) – réseau néo-zélandais de messagerie (Freightways Group)
- [Post Haste: Our Story](https://posthaste.co.nz/about-us/our-story/) – acquisition par Freightways (1977) et partenariat avec Castle Parcels
- [Post Haste: Sending & Receiving Services](https://posthaste.co.nz/services/sending-and-receiving/) – livraison locale, régionale du lendemain, nationale du lendemain, économique inter-îles deux jours, samedi et zones rurales
- [Post Haste: Advanced Track & Trace](https://posthaste.co.nz/tools/track/) – étiquettes imprimées par le client (avec compte société), tickets/Express Packs, codes-barres et sacs contractuels
- [Post Haste: Redelivery](https://posthaste.co.nz/tools/redelivery/) – demandes de relivraison en ligne et pièce d’identité pour retrait en agence
- [Post Haste: Contact & Branches](https://posthaste.co.nz/about-us/contact/) – annuaire des agences des îles du Nord et du Sud
- [Post Haste: Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/) – politique d’absence, notifications et réclamations perte/avarie (page d’aide liée par Peek)
- [Universal Parcel Scraper: post-haste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/post-haste) – recherche Freightways tickets/étiquettes/SSCC/envois, règles de lien `receiver.posthaste.co.nz`, table de codes (`P`, `ONBD`, `NOND`, `STAT/RZDEL`, `D`, `3P/D`) et date estimée
:::
