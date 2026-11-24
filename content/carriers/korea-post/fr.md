---
title: Suivi Korea Post : codes EMS KR, colis 13 chiffres et aide
description: Suis un colis Korea Post (ePOST) : numéros S10 en KR, codes internes à 13 chiffres, statuts EMS et coréens, passage en douane, délais et contact.
slug: suivi-korea-post
published: 2026-11-24
updated: 2026-11-24
---

Korea Post (*우정사업본부*, portail en ligne *ePOST* / *인터넷우체국*) est l’opérateur postal national de Corée du Sud, rattaché au ministère des Sciences et des TIC. Il assure la livraison de colis et de courriers recommandés sur tout le territoire sud-coréen ainsi que les envois internationaux **EMS** (Express Mail Service vers plus de 100 destinations, et plus de 70 pays via EMS Premium), **K-Packet** et les colis et courriers recommandés par avion. Tu peux consulter un envoi international sur [le suivi EMS en anglais de Korea Post](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp), vérifier un colis national sur [le suivi national ePOST](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp), ou coller ton numéro de suivi dans le champ ci-dessus.

## Numéros de suivi Korea Post : formats S10 en KR et codes nationaux à 13 chiffres

Regarde ton reçu de guichet postal, la page de commande de la boutique coréenne ou l’e-mail de confirmation d’expédition. Korea Post utilise deux grands formats selon que l’envoi voyage à l’international ou en Corée du Sud :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `EM482917367KR` | Envoi international **EMS** de marchandises (format UPU S10 terminé par `KR` ; les documents commencent généralement par `EE`). |
| `RR482917367KR` | Courrier recommandé international ou envoi K-Packet (format UPU S10 : 2 lettres + 9 chiffres avec chiffre de contrôle + `KR`). |
| `6482917364058` | Numéro national sud-coréen à 13 chiffres de courrier recommandé ou de colis (commençant par un chiffre de `1` à `6`). Partage ce format numérique à 13 chiffres avec [Correos de Chile](carrier:correos-chile), [Delhivery](carrier:delhivery), [Econt](carrier:econt), [Paquetexpress](carrier:paquetexpress) et d’autres transporteurs. |

Sur [la présentation EMS de Korea Post](https://ems.epost.go.kr/front.Introduction01.postal), le préfixe `EE` désigne les documents EMS (exemptés d’inspection douanière pour marchandises) tandis que le préfixe `EM` désigne les envois EMS de marchandises. Pour d’autres codes postaux internationaux, consulte [les formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi Korea Post

Les envois internationaux en `KR` affichent des lignes de scan UPU/EMS en anglais conservant l’heure locale déclarée par l’opérateur postal de chaque pays, tandis que les colis nationaux à 13 chiffres affichent des libellés en coréen à l’heure normale de Corée (`Asia/Seoul`, UTC+9 toute l’année) :

| Korea Post indique (international / national) | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `운송장출력` *(national)* | Bordereau d’expédition imprimé avant le dépôt au bureau de poste. | « Étiquette créée » |
| `Posting/Collection` / `접수`, `집하완료`, `인수완료` | Pris en charge au guichet d’un bureau de poste coréen ou collecté par un livreur Korea Post. | « Pris en charge » |
| `Arrival at outward office of exchange`, `Ready for dispatch`, `Handed over to a transport company`, `Received by Air carriers`, `Departure from the Airport`, `Arrival at Destination Airport`, `Delivered to Destination Post`, `Arrival at inward office of exchange`, `Departure from inward office of exchange` / `발송`, `도착` | Traité au bureau d’échange international en Corée (comme Incheon), acheminé par avion, remis à l’opérateur postal du pays de destination ou déplacé entre centres de tri nationaux. | « En transit » |
| `Ready for customs clearance` | Présenté en douane dans le pays de destination (ou contrôle douanier à l’export/import). | « À la douane » |
| `배달준비` *(national)* | Préparé pour la livraison finale par le livreur du bureau de poste local en Corée. | « En livraison » |
| `Unsuccessful delivery` | La tentative de livraison dans le pays de destination n’a pas pu aboutir. | « Tentative de livraison » |
| `Delivery complete` / `배달완료` | Livré ; le suivi Korea Post peut indiquer le nom de la personne qui a reçu l’envoi. | « Livré » |

## Délais EMS Korea Post, douane et prise en charge locale

D’après [la présentation du service EMS de Korea Post](https://ems.epost.go.kr/front.Introduction01.postal), [les conditions d’expédition par pays](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) et [le calculateur de délais et tarifs](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal) :

- **Délais d’acheminement EMS :** pour les documents (numéros `EE`) déposés à Séoul avant midi, Korea Post annonce le lendemain ou 3 à 4 jours vers les villes disposant de vols directs, comme Tokyo, Hong Kong et Singapour, et 4 à 6 jours ailleurs ; sa page de présentation indique 2 à 3 jours et 3 à 5 jours pour les documents déposés à Séoul le matin. Compte un jour de plus pour les envois déposés après midi ou hors de Séoul, ou adressés hors de la ville du bureau d’échange de destination ; depuis un bureau de poste de montagne ou insulaire, ajoute les jours nécessaires pour rejoindre le centre logistique du courrier international. Les marchandises (numéros `EM`) attendent aussi le contrôle douanier du pays de destination. Le jour de dépôt et les jours fériés ne comptent pas.
- **Déclarations en douane :** les documents et imprimés utilisent une déclaration CN22, tandis que les marchandises et articles taxables exigent une déclaration CN23 (accompagnée d’une facture commerciale et des éventuels permis requis pour les envois commerciaux). Les articles interdits ou soumis à restriction figurent dans [le guide des articles interdits et réglementés d’ePOST](https://ems.epost.go.kr/front.Introduction04.postal).
- **Relais postal à destination :** une fois ton envoi en `KR` arrivé dans ton pays (`Delivered to Destination Post` / `Arrival at inward office of exchange`), ton opérateur postal national (par exemple [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl), [Japan Post](carrier:japan-post) ou [Australia Post](carrier:australia-post)) gère la perception des droits et taxes, les présentations à domicile et la mise en instance selon ses propres règles. Suis aussi ce même numéro `...KR` sur le site de ta poste locale pour obtenir des options de livraison plus détaillées. Voir [les colis retenus en douane](guide:customs).

## Colis Korea Post retardé, bloqué en transit ou manquant

- **Pause sans scan après `Departure from the Airport` :** les sacs postaux internationaux EMS et K-Packet restent souvent sans nouveau scan pendant plusieurs jours, voire plus d’une semaine, entre le départ de Corée du Sud et le scan d’entrée au bureau d’échange du pays de destination (`Arrival at inward office of exchange`). Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **Bloqué sur `Ready for customs clearance` :** consulte le site de ton opérateur postal de destination avec le même numéro `...KR` pour vérifier si la TVA à l’importation, des droits de douane ou des frais de gestion impayés retardent la libération.
- **Envoi perdu, endommagé ou manquant :** pour un achat en ligne, contacte d’abord le vendeur ou la place de marché coréenne ; ePOST accepte une demande de recherche (*행방조사청구*) en ligne ou par écrit, et publie ses règles d’indemnisation (*손해배상규정*). Voir [colis livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contacts Korea Post

Coordonnées officielles publiées sur [le centre client ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm), [le portail principal ePOST](https://www.epost.go.kr/main.retrieveMainPage.comm) et [la présentation du service EMS](https://ems.epost.go.kr/front.Introduction01.postal) :

- **Centre client Korea Post (depuis la Corée du Sud) :** `1588-1300` (du lundi au vendredi de `09:00` à `18:00` KST, fermé les samedis et jours fériés ; appel payant).
- **Depuis l’étranger :** `+82-42-609-4295`, aux mêmes horaires.
- **E-mail :** `help@posa.or.kr`.
- **FAQ et demandes en ligne :** [centre client ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) et option *e-mail EMS Inquiry* sur [trace.epost.go.kr](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp).
- **Adresse en pied de page d’ePOST :** 1 Jeongbohwa-gil (Bitgaram-dong), Naju, 58323, Corée du Sud.

> Ignore les faux SMS prétendant que ton colis Korea Post ne peut pas être livré sans payer de petits frais via un lien non officiel. Les véritables frais d’importation sur un colis entrant en `KR` sont encaissés par la poste ou le portail douanier de ton pays, jamais via un lien SMS inattendu. Voir [les arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis Korea Post avec Peek

[Peek](/) reconnaît automatiquement avec une confiance élevée tout numéro de suivi UPU S10 terminé par `KR` (`EM482917367KR` ou `RR482917367KR`, chiffres inventés) comme un envoi Korea Post, ainsi que les liens collés `trace.epost.go.kr` contenant `POST_CODE=` et `service.epost.go.kr` contenant `sid1=`. Un numéro national à 13 chiffres (`6482917364058`) correspond à plusieurs transporteurs : Peek cite Korea Post parmi eux et te demande de choisir. Tu peux aussi le sélectionner plus tard avec « Changer de transporteur » sur l’écran du colis (une recherche de « South Korea Post » ou « ePOST » le trouve aussi), ou coller le lien ePOST à la place.

Peek lit le suivi EMS en anglais de Korea Post pour les numéros en `KR` et le suivi national coréen d’ePOST pour ceux à 13 chiffres. Il affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « À la douane », « En livraison » sur les colis nationaux, « Tentative de livraison », « Livré »), le libellé du scan avec son bureau de poste ou son aéroport, et l’heure de livraison sur les colis nationaux. Les scans nationaux sont datés à l’heure coréenne, et les scans internationaux sur le fuseau horaire de leur lieu (celui de New York pour un scan à New York, par exemple) ; Peek les affiche à l’heure de ton appareil. Le nom du destinataire affiché par Korea Post apparaît sous « Destinataire » sur ta propre page de colis, jamais sur un lien partagé ; le nom du signataire n’apparaît dans aucune application Peek, et Peek omet le nom du service, le nom de l’expéditeur et les numéros de téléphone des bureaux. Le suivi Korea Post ne fournit ni date de livraison estimée, ni poids, ni point de retrait.

Tant que le colis est ouvert dans Peek, ou le jour de sa livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où un colis national affiche `배달준비`, toutes les 2 minutes à partir de 6 h heure de Corée, tant que le suivi Korea Post répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelle, il vérifie toutes les heures quelle que soit ta façon de le suivre, puis toutes les 6 heures après sept jours à « Étiquette créée ». Quand le suivi de Korea Post ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Quand le scan le plus récent remonte à quatre jours et que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La page du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Korea Post. », avec un lien « Demande de l’aide à Korea Post » vers le centre client d’ePOST. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Korea Post. »

Tu peux choisir entre les réglages d’alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek se limite au suivi des mises à jour et ne peut ni dédouaner un colis, ni le rediriger, ni déposer une réclamation EMS auprès de Korea Post à ta place.

## Questions fréquentes sur le suivi Korea Post

### Que signifient `EE...KR`, `EM...KR` et `RR...KR` ?

Ce sont des numéros postaux internationaux UPU S10 de 13 caractères expédiés depuis la Corée du Sud : `EE` sert aux documents EMS, `EM` aux colis de marchandises EMS et `RR` (ou d’autres préfixes `R`/`C`/`L`) aux courriers recommandés, envois K-Packet et colis internationaux.

### Pourquoi mon colis EMS Korea Post ne bouge-t-il plus après avoir quitté l’aéroport ?

Une fois que `Departure from the Airport` apparaît en Corée du Sud, le suivi reste muet jusqu’à ce que l’opérateur postal du pays de destination scanne le sac postal à son bureau d’échange d’entrée (`Arrival at inward office of exchange`). Vérifie aussi le même numéro `...KR` sur le site de ta poste nationale.

### Korea Post assure-t-elle elle-même la livraison à l’étranger ?

Korea Post gère la collecte, le tri export et l’expédition aérienne au départ de la Corée du Sud, puis remet le colis (`Delivered to Destination Post`) au service postal national de ton pays de destination pour le dédouanement à l’importation et la livraison finale.

### Comment contacter le service client Korea Post ?

Appelle le `1588-1300` en Corée du Sud (lun.–ven. `09:00–18:00` KST, fermé les samedis et jours fériés), écris à `help@posa.or.kr`, ou utilise [le centre client ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm).

:::sources
- [Korea Post : suivi EMS en anglais](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp) – portail de suivi EMS international et postes partenaires de destination
- [Korea Post (ePOST) : suivi national](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp) – suivi des colis et courriers recommandés nationaux à 13 chiffres
- [Korea Post (ePOST) : présentation du service EMS](https://ems.epost.go.kr/front.Introduction01.postal) – préfixes `EE` et `EM`, délais EMS, règles douanières CN22/CN23, plus de 100 destinations EMS / plus de 70 EMS Premium et `1588-1300`
- [Korea Post (ePOST) : conditions d’expédition par pays](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) – limites et remarques douanières EMS par pays
- [Korea Post (ePOST) : calculateur de délais et tarifs](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal) – calculateur de livraison EMS, EMS Premium et colis
- [Korea Post (ePOST) : articles interdits et réglementés](https://ems.epost.go.kr/front.Introduction04.postal) – restrictions d’expédition internationale
- [Korea Post (ePOST) : centre client](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) – `1588-1300`, `help@posa.or.kr`, horaires `09:00–18:00` KST en semaine et adresse à Naju (URL d’aide liée par Peek)
- [Korea Post (ePOST) : portail principal](https://www.epost.go.kr/main.retrieveMainPage.comm) – aperçu colis nationaux, recommandés, EMS et K-Packet, `+82-42-609-4295` depuis l’étranger
- [Universal Parcel Scraper : korea-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/korea-post) – détection `KR` S10 et 13 chiffres, règles de liens `trace.epost.go.kr` / `service.epost.go.kr`, tables de statuts EMS anglais et coréen national, fuseaux horaires, champs écartés et URL d’aide
:::
