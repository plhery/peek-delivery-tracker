---
title: Suivi AnserX : codes BTYCN...YQ, relais locaux et contact
description: Suis un colis transfrontalier AnserX (斑头雁) depuis la Chine : numéros BTYCN...YQ, codes de scan BTY, relais locaux, douane et contact Shenzhen.
slug: suivi-anserx
published: 2026-11-24
updated: 2026-11-24
---

AnserX (*Shenzhen AnserX International Logistics Technology Co., Ltd.*, en chinois *斑头雁国际物流*), fondé en 2018 et basé à Shenzhen, est un prestataire chinois de logistique e-commerce transfrontalière qui exploite des lignes dédiées de petits colis depuis la Chine vers les États-Unis, l’Europe (notamment le Royaume-Uni, la France et l’Allemagne), le Japon, le Canada, l’Australie, l’Asie du Sud-Est, le Mexique, le Nigeria et l’Arabie saoudite, ainsi que du fret aérien/maritime FBA (0–30 kg, côté maximal 1,2 m) et des entrepôts à l’étranger. Tu peux suivre un envoi sur [le portail de suivi OMS d’AnserX](https://oms.anserx.com/tracking) ou coller ton numéro de bordereau dans le champ ci-dessus.

## Numéros de suivi AnserX : format BTYCN...YQ

Cherche ton numéro de suivi dans les détails de ta commande sur la place de marché ou dans l’e-mail de confirmation d’expédition. Les bordereaux AnserX comptent 17 caractères : `BTYCN` suivi de 10 chiffres et terminé par `YQ` :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `BTYCN4829173640YQ` | Bordereau standard de colis transfrontalier AnserX : `BTYCN` + 10 chiffres + `YQ`. |

Une fois ton envoi arrivé dans le pays de destination et dédouané, AnserX le remet à un opérateur postal ou express local (comme [USPS](carrier:usps) aux États-Unis ou un partenaire local du dernier kilomètre en Europe ou au Mexique) et associe le numéro de suivi local de ce partenaire tout en relayant ses scans sous ton code `BTYCN...YQ`. Pour d’autres préfixes transfrontaliers chinois, consulte [les formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts et codes de suivi AnserX

AnserX associe des codes d’étape `BTY_` à des descriptions en anglais et en chinois pour le tri export, l’acheminement aérien, le dédouanement et la livraison finale :

| Code et libellé AnserX | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `BTY_ORDER_CREATED` (`Shipment information received`) | Le vendeur a transmis les données électroniques de commande à AnserX avant l’arrivée physique du colis au hub. | « Étiquette créée » |
| `BTY_WAREHOUSE_INBOUND` (`Shipment inbound in sorting center`) | Reçu et scanné au centre de tri d’AnserX en Chine. | « Pris en charge » |
| `BTY_WAREHOUSE_OUTBOUND`, `BTY_LINE_HANDOVER` (`Hand over to airline` / `Departed from origin port`), `BTY_LINE_ARRIVED` (`Arrived at destination port`) | Sorti du centre de tri en Chine, remis à la compagnie aérienne et arrivé à l’aéroport de destination. | « En transit » |
| `BTY_EXCC_COMPLETED` (`Relased from export customs` / `目的地进口清关完成`) | Dédouanement terminé (remarque : le libellé anglais d’AnserX indique « export » alors que le texte chinois précise que le dédouanement import à destination est terminé) ; le colis poursuit son acheminement local. | « En transit » |
| `BTY_LASTMILE_TRANSIT` (`Delivery to local courier company` / `arrival at transportation partner facilities`) | Remis au transporteur local du pays de destination et acheminé vers l’agence de livraison locale. | « En transit » |
| `BTY_LASTMILE_DELIVERING` (`Parcel currently being delivered` / `Out for Delivery`) | En tournée avec le livreur local aujourd’hui. | « En livraison » |
| `BTY_LASTMILE_DELIVERED` (`Parcel has been delivered` / `Delivered`) | Livré au destinataire par le partenaire de destination. | « Livré » |

## Comment fonctionne la livraison transfrontalière AnserX

Sur [anserx.com](https://www.anserx.com/) et [À propos d’AnserX](https://www.anserx.com/about.html), AnserX présente sa plateforme intelligente de capacité et de routage reliant la collecte nationale en Chine, les hubs d’exportation, le transport aérien, le dédouanement et les transporteurs locaux du dernier kilomètre (y compris des lignes petits colis dédiées compatibles avec les appareils contenant des batteries).

**Livraison finale et douane.** AnserX est un consolidateur aérien transfrontalier et non un réseau de livraison à domicile hors de Chine. Une fois le colis dédouané à destination (`BTY_EXCC_COMPLETED`), le partenaire postal ou express local de ton pays gère les tentatives de livraison, le dépôt en lieu sûr, la garde en point relais et les retours. Les éventuels droits de douane, TVA à l’importation ou frais de dossier suivent les règles douanières du pays de destination, sauf s’ils ont été prépayés par le vendeur lors de la commande ; voir [les colis retenus en douane](guide:customs).

## Colis AnserX bloqué, silencieux ou non livré

Les clients d’AnserX sont les vendeurs qui expédient avec lui : contacte donc d’abord ton vendeur en cas de retard, d’erreur d’adresse, de remboursement ou de réclamation pour colis perdu.

- **Pauses sans scan pendant le vol ou la douane :** le suivi marque souvent une pause de plusieurs jours entre `BTY_LINE_HANDOVER` (départ du port d’origine), `BTY_LINE_ARRIVED` et `BTY_EXCC_COMPLETED` le temps que les vols atterrissent et que la douane traite les lots groupés. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **Vérifie le numéro de livraison local après la remise :** dès qu’AnserX associe le numéro de suivi du transporteur local, le consulter directement sur le site de ce transporteur offre souvent des créneaux ou détails de retrait plus précis.
- **Livré, endommagé ou manquant :** vérifie d’abord auprès des personnes de ton foyer, de tes voisins, de l’accueil de ton immeuble et du transporteur local, puis préviens rapidement le vendeur en ligne. Voir [colis livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contacts AnserX

Coordonnées officielles publiées sur [la page Contact d’AnserX](https://www.anserx.com/contact-us.html) et [anserx.com](https://www.anserx.com/) :

- **Lignes service client 24 h/24 (Chine) :** `0755-2891 8474` et `0755-2868-5256` (service client en ligne `09:00–24:00` heure de Chine).
- **E-mail service client :** `bantouyankf@anserx.com`.
- **Siège social :** Shenzhen AnserX International Logistics Technology Co., Ltd., Room 804, Block C, Building 3, Tian'an Cloud Park, Xuegang North Road, Bantian Street, Longgang District, Shenzhen, Chine.

> Méfie-toi des SMS ou messages WhatsApp non sollicités réclamant des frais par carte en ligne pour « dédouaner » ou « corriger ton adresse » sur un colis venant de Chine. Vérifie toujours toute taxe d’importation légitime sur ta page de commande ou auprès de ta poste locale. Voir [les arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis AnserX avec Peek

[Peek](/) reconnaît automatiquement `BTYCN` + 10 chiffres + `YQ` (`BTYCN4829173640YQ`, chiffres inventés) comme un envoi AnserX avec une confiance élevée, ainsi que les liens collés `oms.anserx.com/tracking?nos=`. Si tu dois sélectionner AnserX manuellement, appuie sur « Changer » lors de l’ajout ou sur « Changer de transporteur » sur l’écran du colis (rechercher « 斑头雁 » ou « AnserX International Logistics » le trouve aussi).

Peek lit directement le suivi OMS d’AnserX et affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « En livraison », « Livré »), le libellé anglais du scan (le chinois lorsqu’un scan n’a pas de version anglaise), la ville ou le hub sans son code postal et le pays de destination. Les codes `BTY_` déterminent l’étape ; aucune application Peek ne les affiche. AnserX horodate ses propres scans à l’heure de Pékin ; les scans relayés du transporteur local sont datés sur le fuseau horaire de leur lieu (celui de Los Angeles pour un scan à Los Angeles, celui de Mexico pour un scan à Puebla), et Peek les affiche tous à l’heure de ton appareil. Quand AnserX fournit le numéro du transporteur de livraison et que Peek reconnaît ce transporteur, Peek vérifie qu’il a bien le colis : le colis affiche alors « Livraison par » suivi du nom du transporteur et liste les deux numéros. Un code-barres USPS perd son préfixe postal de routage. Peek omet le code compte client et la référence commande ; le suivi d’AnserX ne fournit ni date de livraison estimée, ni poids, ni nom de service, ni heure de livraison distincte, ni photo de preuve de livraison.

Tant que le colis est ouvert dans Peek, ou le jour de sa livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où AnserX affiche `BTY_LASTMILE_DELIVERING`, toutes les 2 minutes à partir de 6 h sur l’horloge du lieu du dernier scan, tant que le suivi d’AnserX répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelle, il vérifie toutes les heures quelle que soit ta façon de le suivre, puis toutes les 6 heures après sept jours à « Étiquette créée ». Quand le suivi d’AnserX ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Quand le scan le plus récent remonte à quatre jours et que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La page du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger AnserX. », avec un lien « Demande de l’aide à AnserX » vers la page de contact d’AnserX. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger AnserX. »

Tu peux choisir entre les réglages d’alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek se limite au suivi des mises à jour et ne peut ni dédouaner un colis ni contacter AnserX ou ton transporteur local à ta place.

## Questions fréquentes sur le suivi AnserX

### Qu’est-ce qu’AnserX (*斑头雁*) ?

AnserX (*Shenzhen AnserX International Logistics Technology Co., Ltd.*, fondé en 2018 à Shenzhen) est un prestataire chinois de logistique e-commerce transfrontalière proposant des lignes aériennes dédiées de petits colis, l’expédition FBA et des entrepôts à l’étranger vers les États-Unis, l’Europe, le Canada, l’Australie, le Japon, le Mexique et d’autres pays.

### Que signifie un numéro de suivi `BTYCN...YQ` ?

`BTYCN` + 10 chiffres + `YQ` (par exemple `BTYCN4829173640YQ`, chiffres inventés) est le bordereau transfrontalier de 17 caractères d’AnserX.

### Pourquoi AnserX affiche-t-il `Relased from export customs` après l’arrivée dans mon pays ?

Il s’agit d’une maladresse de traduction dans le flux anglais d’AnserX pour le code `BTY_EXCC_COMPLETED` — le libellé chinois correspondant (`目的地进口清关完成`) confirme que le dédouanement à l’importation dans le pays de destination est terminé et que le colis passe chez ton transporteur local.

### Comment contacter le service client AnserX ?

Contacte d’abord la boutique en ligne ou la place de marché pour les incidents de commande et remboursements. Pour toute demande auprès du transporteur, AnserX indique `bantouyankf@anserx.com` ainsi que les lignes 24 h/24 de Shenzhen `0755-2891 8474` et `0755-2868-5256` sur [anserx.com/contact-us.html](https://www.anserx.com/contact-us.html).

:::sources
- [AnserX : site officiel](https://www.anserx.com/) – lignes dédiées de petits colis, limites FBA 0–30 kg / 1,2 m, entrepôts à l’étranger et siège de Shenzhen
- [AnserX : à propos](https://www.anserx.com/about.html) – fondé en 2018, routes mondiales (États-Unis, Europe, Japon, Asie du Sud-Est, Russie, Australie) et plateforme de routage intelligente
- [AnserX : contact](https://www.anserx.com/contact-us.html) – `bantouyankf@anserx.com`, `0755-2891 8474`, `0755-2868-5256`, service en ligne `09:00–24:00` et adresse à Shenzhen (URL d’aide liée par Peek)
- [AnserX : portail de suivi OMS](https://oms.anserx.com/tracking) – consultation publique du suivi
- [Universal Parcel Scraper : anserx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/anserx) – détection `BTYCN...YQ`, règle de lien `oms.anserx.com/tracking?nos=`, codes `BTY_*`, heure de Pékin pour ses scans et fuseau local pour les scans relayés, extraction `deliveryNo` et URL d’aide
:::
