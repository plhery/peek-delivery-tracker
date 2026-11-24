---
title: Suivi CJ Packet : codes CJP...YQ, relais locaux et contact
description: Suis un colis CJ Packet (CJ Dropshipping) depuis la Chine : numéros CJP...YQ, statuts et scans locaux relayés, douane et contact support.
slug: suivi-cj-packet
published: 2026-11-24
updated: 2026-11-24
---

CJ Packet (*CJPacket*) est le service d’expédition et de suivi de colis transfrontaliers lié à [CJ Dropshipping](https://cjdropshipping.com/), acheminant de petites commandes e-commerce, des colis économiques en volume et des envois express rapides depuis des entrepôts logistiques en Chine vers les acheteurs du monde entier par fret aérien international puis partenaires locaux du dernier kilomètre. Tu peux consulter un envoi directement sur [cjpacket.com](https://www.cjpacket.com/) ou coller ton numéro de suivi dans le champ ci-dessus.

## Numéros de suivi CJ Packet : format CJP...YQ

Cherche ton bordereau CJ Packet dans l’e-mail de confirmation d’expédition ou sur la page de commande de ta boutique en ligne. Les numéros CJ Packet commencent par `CJP`, suivis de 2 ou 3 lettres (indiquant la ligne d’expédition), de 10 chiffres et de `YQ` (17 ou 18 caractères au total) :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `CJPOS4829173640YQ` | Bordereau CJ Packet de 17 caractères (`CJP` + 2 lettres de ligne + 10 chiffres + `YQ`). |
| `CJPAEE4829173640YQ` | Bordereau CJ Packet de 18 caractères (`CJP` + 3 lettres de ligne + 10 chiffres + `YQ`). |

Une fois ta commande arrivée dans le pays de destination et dédouanée, CJ Packet la remet à un transporteur local ou à la poste nationale — comme [USPS](carrier:usps), [Postes Canada](carrier:canada-post), [FedEx](carrier:fedex) ou [CTT Express](carrier:ctt-express) — et relaie le numéro de suivi local ainsi que les scans de livraison de ce transporteur dans l’historique `CJP...YQ`. Pour d’autres préfixes e-commerce chinois, consulte [les formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi CJ Packet

CJ Packet associe un onglet de résumé global (`0` à `7`) à des scans détaillés ligne par ligne provenant de l’entrepôt CJ, du transitaire export et du transporteur final à destination :

| Résumé CJ Packet (code) / libellé de scan | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Processing` (`0`) / `Label created. Warehouse is processing this order.`, `Shipment info received` | Commande préparée ou étiquette générée dans l’entrepôt CJ avant la collecte par le transitaire. | « Étiquette créée » |
| `Dispatched` (`1`) / `Item has been collected` | Colis remis par l’entrepôt CJ au transitaire transfrontalier. | « Pris en charge » |
| `En Route` (`2`), `Arrived Courier Facility` (`3`) / `Item Flight departure`, `Item Flight landing`, `Customs Clearance Completed`, `Delivery to local courier` | Acheminé par avion depuis la Chine vers le pays de destination, dédouané et transféré au centre de tri du transporteur local. | « En transit » |
| `Inbound to customs temporary warehouse`, `In clearance processing - Import` | En cours de dédouanement à l’importation dans le pays de destination. | « À la douane » |
| `Out for Delivery` (`4`) / `Out for delivery` / `En reparto` | En tournée avec ton livreur local aujourd’hui. | « En livraison » |
| `Available for Pickup` (`5`) | Prêt à être retiré au bureau de poste local ou en point relais. | « Prêt à être retiré » |
| `Unsuccessful Delivery` (`6`) | La livraison n’a pas pu aboutir (adresse incorrecte ou destinataire absent, par exemple). | « Problème signalé » |
| `Delivered` (`7`) / `Package delivered`, `Delivered, At the door/Yard`, `Entregado` | Livré par le partenaire de destination (en indiquant parfois qui l’a réceptionné). | « Livré » |

Les mentions informatives comme `EXPECTED DELIVERY DATE UPDATED` provenant d’un transporteur local sont des estimations et ne modifient pas l’étape de transit sous-jacente du colis.

## Comment fonctionne la livraison transfrontalière CJ Packet

Sur [cjpacket.com](https://www.cjpacket.com/), CJ Packet propose trois grands modes d’expédition internationale — *Small-package Transportation*, *Low-cost Bulk Packages* et *Fast Express* — associant lignes aériennes partenaires, accompagnement douanier et transporteurs locaux dans le monde entier.

**Qui assure la livraison finale.** Dès que `Delivery to local courier` apparaît, la poste ou le transporteur local qui prend le relais fixe les tentatives de livraison, le dépôt en lieu sûr, le délai de garde en point relais et les retours. Le site de CJ Packet s’adresse aux marchands qui expédient avec lui : en cas de retard, de remboursement ou de réclamation, contacte la boutique en ligne auprès de laquelle tu as acheté. Pour la TVA à l’importation, les droits de douane et les retenues en douane (y compris les règles de l’UE sur les colis hors UE), voir [les colis retenus en douane](guide:customs).

## Colis CJ Packet bloqué, silencieux ou marqué livré

Comme ton contrat d’achat te lie à la boutique en ligne (et non à CJ Dropshipping ou CJ Packet), contacte toujours le vendeur en premier si ta commande est en retard, endommagée ou manquante.

- **Pauses sans scan après `Label created` ou le départ du vol :** attendre plusieurs jours pendant que l’entrepôt CJ prépare l’article (`Label created. Warehouse is processing this order.`) ou pendant le dédouanement du fret aérien à destination est fréquent pour les petits colis internationaux. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **Utilise le numéro du transporteur local :** dès que CJ Packet affiche le numéro du partenaire local (un code USPS, Postes Canada ou d’un transporteur local, par exemple), consulter ce partenaire directement peut révéler l’adresse de retrait ou des options de relivraison.
- **Livraison échouée (`6`) ou marqué livré sans réception :** vérifie d’abord auprès des personnes de ton foyer, de tes voisins, de l’accueil de ton immeuble et du transporteur local, puis écris aussitôt au vendeur avant l’expiration des délais de contestation bancaire ou de la plateforme. Voir [colis livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contacts CJ Packet

Coordonnées officielles publiées sur [cjpacket.com](https://www.cjpacket.com/) et [cjdropshipping.com](https://cjdropshipping.com/) :

- **E-mail support logistique :** `logistics@cjpacket.com` (support client 24 h/24 et 7 j/7 mentionné sur [cjpacket.com](https://www.cjpacket.com/)).
- **Portail de suivi :** [cjpacket.com](https://www.cjpacket.com/).
- **Plateforme de dropshipping :** [cjdropshipping.com](https://cjdropshipping.com/) (pour l’assistance logistique côté marchands).

> Ignore les SMS ou messages WhatsApp non sollicités demandant un petit paiement par carte sur un lien externe pour « livrer » ou « dédouaner » un colis. Vérifie toujours d’éventuels frais d’importation sur la page de commande de ta boutique ou le site officiel de ta poste nationale. Voir [les arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis CJ Packet avec Peek

[Peek](/) reconnaît automatiquement `CJP` + 2 ou 3 lettres + 10 chiffres + `YQ` (`CJPOS4829173640YQ` et `CJPAEE4829173640YQ`, chiffres inventés) comme un envoi CJ Packet avec une confiance élevée, et reconnaît aussi les liens collés `cjpacket.com/?trackingNumber=`. Si tu dois choisir CJ Packet manuellement, appuie sur « Changer » lors de l’ajout ou sur « Changer de transporteur » sur l’écran du colis (rechercher « CJPacket » ou « CJ Dropshipping » le trouve aussi).

Peek lit directement le suivi de CJ Packet et affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « À la douane », « En livraison », « Prêt à être retiré », « Problème signalé », « Livré »), le libellé des scans de l’entrepôt, du transitaire et du transporteur local sans numéros de téléphone, e-mails ni mentions de support des transporteurs, le lieu du scan et le pays de destination. Le nom du service ou de la ligne n’apparaît dans aucune application Peek, et le nom de la personne ayant réceptionné le colis reste privé. Un scan doté d’un décalage horaire le conserve, un scan sans décalage est daté sur le fuseau horaire de son lieu, et Peek les affiche tous à l’heure de ton appareil, avec l’heure de livraison lorsque le scan de livraison comporte un décalage. Quand CJ Packet indique le transporteur local et son numéro et que Peek reconnaît ce transporteur, Peek vérifie que ce transporteur a bien le colis : le colis affiche alors « Livraison par » suivi du nom du transporteur et liste les deux numéros. Peek omet le pays d’origine, les numéros alternatifs, les références commande/marchand et les coordonnées ; le suivi de CJ Packet ne fournit ni date de livraison estimée, ni poids, ni photo de preuve de livraison.

Tant que le colis est ouvert dans Peek, ou le jour de sa livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où CJ Packet affiche `Out for Delivery` (`4`), toutes les 2 minutes à partir de 6 h sur l’horloge du lieu du dernier scan, tant que le suivi de CJ Packet répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelle, il vérifie toutes les heures quelle que soit ta façon de le suivre, puis toutes les 6 heures après sept jours à « Étiquette créée ». Quand le suivi de CJ Packet ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant qu’il est ouvert ou que les notifications sont activées.

Quand le scan le plus récent remonte à quatre jours et que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La page du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger CJ Packet. » Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger CJ Packet. »

Tu peux choisir entre les réglages d’alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek se limite au suivi des mises à jour et ne peut ni dédouaner un colis, ni contacter CJ Packet, ni demander un remboursement auprès de ta boutique en ligne.

## Questions fréquentes sur le suivi CJ Packet

### Qu’est-ce que CJ Packet ?

CJ Packet est le service d’expédition et de suivi de colis internationaux utilisé par les marchands qui expédient leurs commandes via [CJ Dropshipping](https://cjdropshipping.com/) depuis des entrepôts en Chine vers des acheteurs du monde entier.

### Que signifie un numéro de suivi `CJP...YQ` ?

Les numéros commençant par `CJP`, suivis de 2 ou 3 lettres de ligne, de 10 chiffres et de `YQ` (comme `CJPOS4829173640YQ` ou `CJPAEE4829173640YQ`, chiffres inventés) sont des bordereaux transfrontaliers CJ Packet.

### Qui livre un colis CJ Packet localement ?

Après le vol depuis la Chine et le dédouanement à destination, CJ Packet remet le colis (`Delivery to local courier`) à une poste nationale ou à un transporteur local (comme USPS, Postes Canada, FedEx ou un transporteur régional européen) pour la livraison finale.

### Comment contacter le service client CJ Packet ?

Contacte d’abord la boutique en ligne où tu as passé commande pour un remboursement ou un problème d’adresse. L’e-mail logistique publié par CJ Packet sur [cjpacket.com](https://www.cjpacket.com/) est `logistics@cjpacket.com`.

:::sources
- [CJ Packet : portail de suivi officiel](https://www.cjpacket.com/) – Small-package Transportation, Low-cost Bulk Packages, Fast Express, support 24/7 et `logistics@cjpacket.com`
- [CJ Dropshipping](https://cjdropshipping.com/) – plateforme mère de traitement e-commerce
- [Universal Parcel Scraper : cj-packet](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cj-packet) – détection `CJP[A-Z]{2,3}\d{10}YQ`, règle de lien `cjpacket.com/?trackingNumber=`, codes résumé `0`–`7`, relais partenaire local et analyse signataire, fuseaux horaires et champs écartés
:::
