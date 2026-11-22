---
title: Suivi Ekart : identifiants, statuts InscannedAtDH et RTO
description: Suis un colis Ekart en Inde : format à 14 caractères (FMPP…), sens des codes InscannedAtDH et RTO, tentatives de livraison et suivi avec Peek.
slug: suivi-ekart
published: 2026-11-21
updated: 2026-11-21
---

Ekart (aussi appelé *eKart Logistics* et *Ekart Express*), lancé en 2009 selon sa page *About Us*, exploité par *Instakart Services Private Limited* et basé à Bengaluru (Karnataka), est l'un des principaux réseaux indiens de livraison e-commerce. Il collecte les commandes en ligne chez le vendeur, les achemine à travers ses centres de tri et les livre dans plus de 15 000 codes PIN en Inde. Récupère l'identifiant de suivi dans le message d'expédition de la boutique et colle-le dans le champ ci-dessus, ou saisis-le sur la [page Track Order d'ekartlogistics.in](https://www.ekartlogistics.in/track-order).

## Identifiant de suivi Ekart : à quoi il ressemble

Le code de Peek reconnaît les identifiants e-commerce d'Ekart sous la forme de trois lettres, suivies de `C`, `P` ou `R`, puis de dix chiffres — soit 14 caractères au total (exemples inventés) :

| Identifiant (inventé) | Ce que c'est |
| --- | --- |
| `FMPP4829105736` | L'un des 17 groupes de trois lettres rattachés à Ekart (listés ci-dessous), suivi de `C`, `P` ou `R` et de dix chiffres : Peek désigne Ekart sur la seule forme. |
| `ABCP4829105736` | Trois autres lettres devant le même schéma : cela peut être un envoi Ekart, mais la forme seule ne suffit pas et Peek interroge d'abord Ekart (voir plus bas). |

Les 17 préfixes de trois lettres reconnus d'office pour Ekart sont `AMI`, `BAL`, `BGS`, `BSI`, `DEL`, `FLO`, `FMP`, `GLA`, `LUA`, `MYE`, `MYN`, `MYS`, `SDL`, `SHO`, `SPO`, `SRT` et `TEL`. Espaces et minuscules n'ont pas d'importance dans Peek. Deux exceptions : un code commençant par `PB` sur ce modèle est réservé au transporteur britannique Parcelforce, et un code débutant par `HRBC` (`HRBC4829105736`) correspond à un format [Cainiao](carrier:cainiao) / AliExpress et n'est pas proposé d'office pour Ekart — si une boutique indienne te l'a transmis pour Ekart, touche « Changer » et choisis Ekart.

Utilise bien l'identifiant de suivi et non le numéro de commande du marchand ([comment trouver son numéro de suivi](guide:find-tracking-number)) ; la page `ekartlogistics.in/track-order` propose séparément un lien pour les expéditions lourdes B2B/FTL/PTL suivies par identifiant LR.

## Statuts de suivi Ekart expliqués

Le traceur d'Ekart associe à chaque scan une date, une heure et une ville. Certaines lignes sont en anglais courant (`Shipment Created`), d'autres combinent un code technique et le nom du centre (`InscannedAtDH` suivi du centre). Dans l'ordre habituel du trajet :

| Ekart indique | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Pickup Requested`, `Shipment Created` | Le vendeur a réservé l'envoi chez Ekart ; Ekart n'a pas encore le colis. | « Étiquette créée » |
| `OutForPickupEvent` | Un livreur Ekart est en route pour récupérer le colis chez le vendeur. | « Étiquette créée » |
| `NotPickedEvent` | La collecte chez le vendeur a échoué ; le colis est toujours chez lui. | « Étiquette créée » |
| `Pickup From Seller`, `PickupReceived` | Ekart a pris le colis en charge chez le vendeur. | « Pris en charge » |
| `ShipmentGrouped`, `ShipmentInTransit`, `dispatched_to_tc`, `dispatched`, `Dispatched to` + centre | En transit entre les centres de tri du réseau Ekart. | « En transit » |
| `Expected` | Le centre suivant est prévenu de l'arrivée prochaine du colis. | « En transit » |
| `Received`, `Received at` + centre | Arrivé dans un centre de tri Ekart. | « En transit » |
| `InscannedAtFacility` | Scanné à l'entrée d'une installation Ekart. | « En transit » |
| `InscannedAtDH`, `PrimaryDHInscan` | Arrivé à l'agence de livraison (*Delivery Hub*, DH) qui dessert ton secteur : la mise en tournée vient ensuite. | « En transit » |
| `Out For Delivery` | En tournée de livraison aujourd'hui. | « En livraison » |
| `Delivered` | Livré chez toi (ou rendu au vendeur lorsqu'il suit une étape RTO, voir ci-dessous). | « Livré » |
| `undelivered_attempted`, `ShipmentUndeliveredAttempted`, `undelivered_update` | Une tentative de livraison a eu lieu sans aboutir (`undelivered_update` peut s'afficher au même instant à côté de l'agence). | « Tentative de livraison » |
| `undelivered_unattempted` | La tournée s'est terminée sans passage effectif. | « Tentative de livraison » |
| `Unsuccessful Unable to attempt delivery due to operational issues`, `Unsuccessful Undelivered. (Rescheduled to: …)` | Échec de la tournée en toutes lettres ; la date après `Rescheduled to:` indique le prochain essai prévu (une mention `Unsuccessful` visant une collecte n'est pas lue comme échec de livraison). | « Tentative de livraison » |
| `PickupCancel` | La collecte chez le vendeur a été annulée avant l'entrée dans le réseau Ekart. | « Problème signalé » |
| `ShipmentRtoConfirmed` | Le retour à l'expéditeur (*Return to Origin*, RTO) est confirmé : le colis repart chez le vendeur. | « Problème signalé » |
| `Marked RTO: ORDER_REJECT` | Après des tournées infructueuses, le colis est marqué en retour vendeur avec le motif après les deux-points. | « Problème signalé » |
| `RtoExpected`, `RtoReceived`, `ReturnInscanned` | En cours d'acheminement retour vers le vendeur. | « En transit » |

**Comprendre le RTO.** Peek interprète `ShipmentRtoConfirmed`, toute ligne `Marked RTO` ou tout code commençant par `Rto` ou `Return` comme le départ du retour vers le vendeur : cette ligne et toutes les suivantes appartiennent au trajet retour. Dès lors, `InscannedAtDH` désigne l'agence du vendeur et un `Delivered` ultérieur signifie que le vendeur a récupéré l'envoi : Peek affiche alors « Retourné à l’expéditeur » et non « Livré » ([que signifient les statuts de suivi](guide:tracking-statuses)).

## Comment Ekart livre en Inde

Le code de Peek ne connaît Ekart qu'en Inde et Peek ne transfère les colis Ekart vers aucun autre transporteur. Sur `ekartlogistics.in`, la page B2C Express annonce couvrir plus de 15 000 codes PIN en livraison (et la FAQ mentionne 5 000 codes PIN en collecte première étape, ainsi que 14 000 codes PIN et 3 500 hubs de dernier kilomètre sur la page *About Us*), avec des options le jour même ou le lendemain à courte distance et en 48 à 96 heures à plus longue distance (selon la FAQ, le délai dépend des codes PIN d'enlèvement).

Selon la FAQ officielle d'Ekart, Ekart fait **cinq nouvelles tentatives** (*reattempts*) en première étape (collecte chez le vendeur) et **trois nouvelles tentatives** sur le dernier kilomètre (livraison au destinataire), et une livraison peut être reprogrammée à la demande du client tant que l'envoi n'a pas été marqué `RTO` ([livraison manquée](guide:missed-delivery)).

## Suivi Ekart bloqué, en RTO ou non livré

**Bloqué sur `Shipment Created`, `Pickup Requested` ou `NotPickedEvent`.** Le marchand n'a pas encore remis le colis à Ekart (ou une collecte a échoué) ; `PickupCancel` indique que l'enlèvement a été annulé. Demande au vendeur quand la commande partira.

**Aucun nouveau scan pendant plusieurs jours.** Après quatre jours sans nouvelle étape sur un colis enregistré, pris en charge ou en transit, Peek affiche « Aucune mise à jour depuis … » suivi du jour de ce dernier scan. Contacte la boutique ([pourquoi le suivi ne bouge plus](guide:tracking-not-updating)).

**Échec de livraison ou passage en RTO.** Après `undelivered_attempted`, `undelivered_unattempted` ou `Unsuccessful`, vérifie la date éventuelle après `Rescheduled to:` et confirme ton adresse et ton numéro de téléphone auprès du marchand. Si `ShipmentRtoConfirmed` ou `Marked RTO: ORDER_REJECT` apparaît, l'envoi repart chez le vendeur : demande un remboursement ou une réexpédition ([colis retourné à l'expéditeur](guide:returned-to-sender)).

**Indiqué livré mais non reçu, perdu ou endommagé.** Vérifie auprès des membres de ton foyer, des voisins et du gardien ou de l'accueil de l'immeuble, puis préviens la boutique ou la place de marché qui a réservé Ekart ([indiqué livré mais non reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel)).

## Service client et contact Ekart

Sur `ekartlogistics.in`, la FAQ indique aux marchands d'ouvrir un ticket sur le portail expéditeur (réponse annoncée sous 24 heures) et la page *Corporate Information* donne l'adresse d'*Instakart Services Private Limited* à Bengaluru (`Embassy Tech Village, Outer Ring Road, Devarabeesanahalli Village, Bengaluru KA 560103`), sans publier de ligne téléphonique ni d'e-mail destinés aux destinataires. Pour toute question, réexpédition ou remboursement concernant ta commande, adresse-toi directement à la boutique ou à la place de marché où tu as acheté.

**Attention aux SMS frauduleux.** Les domaines officiels d'Ekart sont `www.ekartlogistics.in` (`ekartlogistics.in`) et `ekartlogistics.com`. Avant de payer quoi que ce soit réclamé par SMS ou message, lis [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts).

## Suivre un colis Ekart avec Peek

Colle l'identifiant dans [Peek](/) : avec l'un des 17 préfixes ci-dessus suivi de `C`, `P` ou `R` et de dix chiffres, Peek retient Ekart immédiatement (« Transporteur détecté »). Avec d'autres lettres en tête (`ABCP4829105736`), Peek affiche « Vérification des services de suivi… » et interroge le traceur d'Ekart : dès qu'un scan existe, il affiche « Ekart a ce colis. » (s'il n'y a encore rien, touche « Changer » et sélectionne Ekart sous « Correspond à ce numéro »). Un lien `ekartlogistics.com` contenant `shipmenttrack/` et l'identifiant classe aussi d'emblée le colis sous Ekart ; en revanche, un lien `shiprocket.co` est suivi sur Shiprocket et non sur Ekart. Toute saisie manuelle sous Ekart ne comportant pas exactement quatre lettres et dix chiffres affiche qu'il « n’émet pas de numéros comme celui-ci ».

Classé sous Ekart, un envoi sans scan affiche « En attente de Ekart » (ou « Aucun historique de suivi pour le moment » sans transporteur attribué). Deux jours après qu'Ekart a répondu pour la première fois ne pas connaître le numéro, Peek te demande de le vérifier, de choisir un autre transporteur ou de supprimer le colis. Peek lit directement le traceur d'Ekart sans compte ni numéro de téléphone : chaque étape dans les termes et codes d'Ekart avec sa ville et son heure exacte convertie dans le fuseau de ton appareil (« Heures dans ton fuseau horaire : … »), sans doublons, ainsi que la date de livraison prévue tant que l'envoi est en route vers toi (retirée dès qu'il est livré, annulé ou en retour RTO).

Tant que le traceur d'Ekart répond, avec le colis ouvert ou le jour de livraison avec notifications actives, Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes dès 6 h (heure indienne) le jour où Ekart scanne `Out For Delivery` (via les services de suivi, jusqu'à toutes les 15 minutes ce jour-là si le colis est ouvert ou que les notifications sont activées) ; sinon, et après 12 h sur « Étiquette créée » / « Pris en charge » ou deux jours sans nouveauté, toutes les heures. Alertes au choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni Ekart ni le vendeur pour toi.

## Questions fréquentes sur le suivi Ekart

### Comment suivre mon colis Ekart ?

Colle l'identifiant de suivi à 14 caractères reçu du vendeur dans le champ en haut de cette page, ou saisis-le dans la rubrique *Ecommerce Parcel Tracking* sur `ekartlogistics.in/track-order`.

### À quoi ressemble un numéro de suivi Ekart ?

À trois lettres suivies de `C`, `P` ou `R` puis de dix chiffres, comme `FMPP4829105736` (exemple inventé).

### Que signifie `RTO` dans le suivi Ekart ?

*Return to Origin* : le colis repart chez le vendeur. `ShipmentRtoConfirmed` ou `Marked RTO: ORDER_REJECT` déclenche le retour, et un `Delivered` ultérieur signifie qu'il a été rendu au marchand (« Retourné à l’expéditeur » dans Peek).

### Que signifie `InscannedAtDH` chez Ekart ?

Le colis a été scanné à son arrivée dans l'agence de livraison (*Delivery Hub*) : à l'aller, celle qui dessert ton adresse ; après un passage en RTO, celle du vendeur.

### Combien de tentatives de livraison effectue Ekart ?

La FAQ officielle d'Ekart indique trois nouvelles tentatives (*reattempts*) sur le dernier kilomètre vers le destinataire (et cinq lors de l'enlèvement chez le vendeur). Pour toute aide sur ta commande, contacte d'abord la boutique où tu as acheté.

:::sources
- [Ekart Logistics : Accueil](https://www.ekartlogistics.in/) – réseau express e-commerce en Inde et couverture de plus de 15 000 codes PIN
- [Ekart Logistics : Track Order](https://www.ekartlogistics.in/track-order) – suivi des colis e-commerce par numéro de suivi et lien séparé B2B/LR
- [Ekart Logistics : FAQ](https://www.ekartlogistics.in/faq) – trois nouvelles tentatives de livraison, cinq d'enlèvement, reprogrammation hors RTO, 5 000 / 15 000 codes PIN et support expéditeur par ticket
- [Ekart Logistics : About Us](https://www.ekartlogistics.in/about-us) – création en 2009, 3 500 hubs de livraison, plus de 80 centres de tri et 14 000 codes PIN
- [Ekart Logistics : B2C Express](https://www.ekartlogistics.in/b2c-express) – livraisons le jour même / lendemain et en 48–96 heures en Inde
- [Ekart Logistics : Corporate Information](https://www.ekartlogistics.in/corporate-info) – Instakart Services Private Limited et siège à Bengaluru
- [Ekart Logistics : Terms & Conditions](https://www.ekartlogistics.in/terms-and-conditions) – exploitation du domaine ekartlogistics.com par Instakart Services Private Limited
- [Universal Parcel Scraper : Ekart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ekart) – 17 préfixes à 3 lettres, liens shipmenttrack, 31 codes et statuts, logique RTO, horodatages exacts et date de livraison prévue
- [Universal Parcel Scraper : Shiprocket](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shiprocket) – liens de suivi shiprocket.co
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – exceptions PB (Parcelforce) et HRBC (Cainiao / AliExpress) et absence de relais externe
:::
