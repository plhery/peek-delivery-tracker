---
title: Suivi Maersk eCommerce : codes 1M, statuts et aide
description: Suis un colis Maersk eCommerce (B2C Europe / InterPost) : structure du code 1M à 19 caractères, codes 70000–77200, relais locaux et support Maersk.
slug: suivi-maersk-ecommerce
published: 2026-11-24
updated: 2026-11-24
---

**Maersk eCommerce** (*Maersk Logistics & Services USA Inc.*, qui regroupe les anciennes plateformes *B2C Europe* et *InterPost*) est le réseau de livraison de colis B2C national et transfrontalier d’A.P. Moller–Maersk ([maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)). Acheminant plus de 80 millions de colis par an avec plus de 75 transporteurs intégrés en Amérique du Nord et en Europe, Maersk eCommerce trie les colis des boutiques en ligne dans ses propres installations, assure le dédouanement B2C sur les liaisons internationales et confie la livraison finale à des transporteurs locaux et opérateurs postaux tels qu’[Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) et [DPD Autriche](carrier:dpd-at). Tu peux coller ton numéro de colis `1M` à 19 caractères dans le champ ci-dessus pour suivre à la fois les scans des centres Maersk et les jalons relayés du transporteur local.

## Numéros de suivi Maersk eCommerce (format `1M`)

Maersk eCommerce attribue des numéros de colis alphanumériques de 19 caractères commençant par `1M`, suivis d’un code marchand/client de 7 caractères (contenant au moins une lettre) et de 10 chiffres :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `1MAB4829C7360514928` | Code colis Maersk eCommerce à 19 caractères (`1M` + code client de 7 caractères + 10 chiffres). |
| `1MKX7391E4829173605` | Autre numéro de colis `1M` à 19 caractères utilisé sur les axes nord-américains ou européens. |

Vérifie l’e-mail d’expédition du vendeur ou le lien de suivi de commande (`maersk.com/tracking/parcel/...`) pour trouver le numéro `1M`. Le suivi Maersk peut indiquer le transporteur du dernier kilomètre et le numéro que ce transporteur a attribué au colis. Voir les [formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi Maersk eCommerce

Les scans propres à Maersk portent des codes de `70000` à `72004`, et ceux du transporteur du dernier kilomètre commencent à `77100` :

| Code et libellé Maersk | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `70000` `Shipment Manifest Received`, `70001` `Maersk Shipping Label Created` | `70000` : le manifeste du vendeur liste le colis avant que Maersk ne le possède. `70001` : étiquette propre de Maersk, créée dès que le colis entre dans son réseau, souvent quelques minutes avant `70002`. | « Étiquette créée » |
| `70002` `Parcel Received by Maersk` | Colis physique réceptionné et scanné dans un centre d’injection Maersk eCommerce. | « Pris en charge » |
| `72001` `Arrived at Maersk Facility`, `72002` `Processing at Maersk Facility`, `72004` `Departed from Maersk Facility` | Trié dans un hub de consolidation Maersk et expédié vers la région de destination ou le hub frontalier. | « En transit » |
| `77100` `In-Transit Scan`, `77101` `Enroute to Facility`, `77102` `Arrival at Facility`, `77103` `Processing at Facility`, `77105` `Departed from Facility`, `77106` `Parcel In-transit` | Événements de transit national relayés par le transporteur local du dernier kilomètre (comme OnTrac, Amazon Logistics ou DPD). | « En transit » |
| `77203` `Out for Delivery` | Chargé dans le véhicule du livreur local pour une livraison aujourd’hui. | « En livraison » |
| `77200` `Parcel Successfully Delivered` | Livré à l’adresse du destinataire par le partenaire local du dernier kilomètre. | « Livré » |

Un scan ne portant aucun de ces codes affiche « Problème signalé » lorsque Maersk le marque comme incident, et reprend sinon l’étape sous laquelle il apparaît sur le suivi Maersk.

## Couverture, retours et douane transfrontalière

Maersk E-Commerce couvre intégralement la livraison dans 32 pays d’Amérique du Nord et d’Europe, avec des points d’injection d’origine en Chine et en Asie du Sud-Est ([Maersk E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)) :

- **Centres et partenaires :** en Europe, 8 centres de tri et de cross-docking, des hubs d’injection à Londres, Taulov et Amsterdam, et plus de 40 partenaires du dernier kilomètre et opérateurs postaux, avec livraison à domicile et points de retrait. En Amérique du Nord, des centres de tri et plateformes à JFK, LAX et ORD ; la livraison Standard prend 2 à 7 jours aux États-Unis et 1 à 10 jours au Canada, l’Expedited 2 à 5 et 1 à 5 jours.
- **Dédouanement international :** Maersk dédouane les colis B2C à destination du Royaume-Uni, de l’Union européenne et des États-Unis. Le paiement éventuel de droits et de TVA à l’arrivée dépend de leur facturation ou non par le vendeur lors de la commande. Voir les [colis retenus à la douane](guide:customs).
- **Retours e-commerce :** retours nationaux et internationaux par dépôt ou collecte à domicile pour les vendeurs qui les proposent, sans papier et exonérés de droits sur certains axes.

## Colis Maersk eCommerce retardé, sans nouvelles ou manquant

Comme Maersk eCommerce est un consolidateur B2B mandaté par des enseignes e-commerce, **contacte toujours d’abord la boutique en ligne** pour un changement d’adresse, un article manquant, un remplacement ou un remboursement.

- **Silence durant l’acheminement ou le transfert partenaire :** le suivi marque souvent une pause de quelques jours entre `72004` (`Departed from Maersk Facility`) et le premier scan `77100+` du transporteur final pendant le transport routier ou aérien vers le livreur local. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **Utiliser le numéro du transporteur local :** dès que Maersk indique le transporteur final et son numéro, le suivi et l’assistance de ce dernier prennent le relais. Peek affiche « Livraison par » pour [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) et [DPD Autriche](carrier:dpd-at).
- **Indiqué livré ou endommagé :** vérifie autour des entrées, locaux colis et chez tes voisins, consulte le suivi du partenaire local et préviens rapidement le marchand. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel) et [colis retourné à l’expéditeur](guide:returned-to-sender).

## Support Maersk eCommerce et portails officiels

La page de support Maersk propose un chat en direct et la gestion de dossiers, principalement pour ses clients professionnels ; les vendeurs réservent via le portail MyParcel de Maersk.

- **Présentation Maersk E-Commerce et demandes pro :** [maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics).
- **Support général Maersk et FAQ :** [maersk.com/support](https://www.maersk.com/support) et [maersk.com](https://www.maersk.com/).
- **Recours destinataire :** contacte la boutique en ligne où tu as acheté l’article (ou le transporteur local du dernier kilomètre une fois qu’il a scanné le colis).

> Méfie-toi des faux SMS te demandant de cliquer sur un lien inconnu et de payer une petite somme pour débloquer un « colis Maersk ». Vérifie toujours tout avis d’expédition via la page de commande de ton vendeur ou le site officiel du livreur local. Voir les [arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis Maersk eCommerce avec Peek

[Peek](/) interroge automatiquement Maersk eCommerce comme seul candidat lorsque tu saisis un numéro de colis `1M` à 19 caractères (`1MAB4829C7360514928` ou `1MKX7391E4829173605`, inventés) et affiche « Maersk eCommerce a ce colis. » dès que l’API colis Maersk le confirme ; Peek reconnaît aussi directement les liens collés `maersk.com/tracking/parcel/`. Si tu dois le choisir manuellement, appuie sur « Changer » à l’ajout ou « Changer de transporteur » sur la fiche du colis (rechercher « B2C Europe », « InterPost », « Maersk Parcel » ou « Maersk E-Delivery » le trouve aussi).

Peek lit le suivi colis de Maersk et affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « En livraison », « Problème signalé », « Livré »), le libellé des scans de Maersk et du transporteur local, la ville et la région, le jour de livraison estimé quand Maersk le fournit, le pays de destination et l’heure de livraison. Les codes de statut et le nom du service restent masqués dans toutes les applications Peek. Maersk indique un décalage UTC pour ses propres scans et la plupart des partenaires ; un scan partenaire sans décalage, comme chez DPD, est daté sur le fuseau horaire de son lieu (Vienne pour un scan à Graz) ou sinon sur celui du scan voisin, et Peek affiche le tout à l’heure de ton appareil. Peek ne garde qu’un exemplaire d’un scan partenaire listé deux fois. Lorsque Maersk cite [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) ou [DPD Autriche](carrier:dpd-at) avec un numéro conforme à ce transporteur, Peek vérifie qu’il a le colis : la fiche affiche alors « Livraison par » suivi du nom du transporteur, et liste les deux numéros. Peek ne lit jamais le numéro de commande client, la ville et le code postal de destination ni le lien partenaire.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Maersk affiche `77203` (`Out for Delivery`), toutes les 2 minutes à partir de 6 h sur l’horloge du dernier scan tant que le suivi Maersk répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, Peek passe à une vérification par heure quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi Maersk ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Maersk eCommerce. » Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Maersk eCommerce. »

Tu peux choisir des alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek suit uniquement les mises à jour de suivi et ne peut ni dédouaner un colis, ni rediriger un colis, ni contacter Maersk eCommerce ou ses partenaires locaux à ta place.

## Questions sur le suivi Maersk eCommerce

### Qu’est-ce que Maersk eCommerce (B2C Europe / InterPost) ?

Maersk eCommerce est le réseau de consolidation et de livraison de colis B2C d’A.P. Moller–Maersk en Amérique du Nord et en Europe (regroupant les anciens réseaux B2C Europe et InterPost), acheminant plus de 80 millions de colis par an via plus de 75 transporteurs partenaires.

### À quoi ressemble un numéro de suivi Maersk eCommerce ?

Les numéros de colis Maersk eCommerce comptent 19 caractères : `1M`, suivi d’un code client alphanumérique de 7 caractères (contenant au moins une lettre) et de 10 chiffres — par exemple `1MAB4829C7360514928` (inventé).

### Qui livre mon colis Maersk eCommerce chez moi ?

Maersk consolide et achemine le colis entre ses hubs, puis confie la livraison finale à un partenaire régional du dernier kilomètre dans ton pays, tel qu’[Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac), [DPD Autriche](carrier:dpd-at) ou un service postal local.

### Pourquoi mon colis Maersk eCommerce reste-t-il sur `70000 Shipment Manifest Received` pendant plusieurs jours ?

`70000` signifie que le manifeste du vendeur recense le colis avant que Maersk ne le reçoive : rien ne bouge tant que le vendeur ne l’a pas remis, demande-lui donc des nouvelles si le statut ne change pas. Maersk ajoute `70001`, sa propre étiquette, dès l’entrée du colis dans son réseau, souvent quelques minutes avant `70002` (`Parcel Received by Maersk`).

:::sources
- [Maersk : services de livraison colis E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics) – 80M+ colis annuels, 75+ transporteurs intégrés, réseaux nationaux, internationaux et retours en Amérique du Nord et Europe
- [Maersk : site officiel](https://www.maersk.com/) – portail logistique mondial et accès au suivi colis
- [Maersk : support et FAQ](https://www.maersk.com/support) – portail d’assistance officiel Maersk
- [Universal Parcel Scraper : maersk-ecommerce](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/maersk-ecommerce) – format `1M` à 19 caractères, règle de lien `maersk.com/tracking/parcel/`, codes de statut 70000–77200, estimation et pays, et règles de relais Amazon/OnTrac/DPD-AT
:::
