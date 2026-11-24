---
title: Suivi Chukou1 : codes EC-FirstClass, relais locaux et aide
description: Suis un colis transfrontalier Chukou1 (CK1 / 出口易) via EC-FirstClass : structure du numéro, étapes de scan, relais locaux et contacts en Chine.
slug: suivi-chukou1
published: 2026-11-24
updated: 2026-11-24
---

Chukou1 (*CK1*, en chinois *出口易*, exploité par *Guangzhou Beifayi Information Technology Co., Ltd.* / *BFE*) est un grand opérateur chinois de logistique e-commerce transfrontalière et d’entreposage à l’étranger basé à Guangzhou, avec des agences à Shenzhen, Shanghai et Wuhan et des centres de traitement à Dongguan et Yiwu. Exploitant des entrepôts logistiques aux États-Unis, au Royaume-Uni, en Allemagne, en France, en Pologne, au Canada, en Australie, au Pérou et en Argentine ainsi que des lignes dédiées de petits colis (lignes Europe/États-Unis, lignes économiques mondiales, Amérique latine et TEMU-Y2) pour les vendeurs sur Amazon, eBay, AliExpress, SHEIN, TikTok Shop, Walmart et Wayfair, Chukou1 assure le suivi des colis via sa plateforme **EC-FirstClass** ([ec.chukou1.com](https://ec.chukou1.com/), jusqu’à 100 numéros à la fois). Tu peux vérifier un envoi sur ce portail ou coller ton code dans le champ ci-dessus.

## Numéros de suivi Chukou1 (format de traitement EC-FirstClass)

Cherche le numéro de traitement Chukou1 sur la page de commande de la place de marché ou dans l’e-mail d’expédition. Les numéros propres à Chukou1 combinent un préfixe de service de 3 lettres, une date de commande `AAMMJJ` de 6 chiffres, un code marchand de 3 ou 4 caractères et un numéro de série de 6 ou 7 chiffres (18 à 20 caractères au total) :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `CZN261015ABCD482917` | Numéro de traitement Chukou1 de 19 caractères (`CZN` + date `261015` + code client 4 car. `ABCD` + série 6 chiffres). |
| `CUE261015KLM4829173` | Numéro de traitement Chukou1 de 19 caractères (`CUE` + date `261015` + code client 3 car. `KLM` + série 7 chiffres). |

Après le dédouanement export, Chukou1 remet le colis à un transporteur pour la suite du trajet (EC-FirstClass cite China Post, [USPS](carrier:usps), [DHL](carrier:dhl), [FedEx](carrier:fedex) et [UPS](carrier:ups) parmi ses partenaires) et affiche sous ton numéro de traitement à la fois l’historique initial de Chukou1 et les scans relayés par ce transporteur. Voir [les formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi Chukou1 (EC-FirstClass)

Chukou1 associe un statut global (`InProcess`, `InTransit`, `Delivered`) à des étapes initiales en anglais et aux scans relayés du transporteur de destination, en utilisant l’heure locale de chaque lieu de scan :

| Chukou1 indique | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Shipment information received`, `Carrier received the manifest` | Commande électronique reçue du vendeur avant la réception physique au hub de Chukou1. | « Étiquette créée » |
| `Item received by Logistics Centre`, `Carrier received the shipment` | Colis physique scanné dans l’entrepôt d’origine de Chukou1 en Chine. | « Pris en charge » |
| `Item was sent out from Logistics Centre`, `Transfer to Airport of Origin`, `Departed from Airport of Origin`, `Arrived at Airport of destination`, `Shipment started the customs clearance process at origin` | Transféré du hub à l’aéroport d’origine, exporté et arrivé dans le pays de destination. | « En transit » |
| `Customs clearance in progress`, `Shipment started the customs clearance process at destination`, `Shipment is awaiting customs clearance at destination` | En cours de contrôle douanier à l’importation dans le pays de destination. | « À la douane » |
| `Customs declaration completed`, `Turned over to last mile facility`, `Last mile Ascan`, `Arrived at Post Office` | Dédouané à l’importation, remis au transporteur local du dernier kilomètre et scanné dans son réseau. | « En transit » |
| `Shipment is out for delivery` / `Out for Delivery` | Chargé pour livraison finale par le transporteur de destination. | « En livraison » |
| `Shipment is available for pickup` | Prêt à être retiré au bureau de poste local ou en point relais. | « Prêt à être retiré » |
| `Wrong Address`, `Refused`, `Cannot be delivered` | Échec de tentative de livraison pour problème d’adresse, refus ou impossibilité de livrer. | « Tentative de livraison » |
| `Return To Sender`, `Delivered To Sender` | Renvoyé ou restitué à l’expéditeur. | « Retourné à l’expéditeur » |
| `Lost` | Incident signalé comme perdu en transit ; contacte immédiatement ton vendeur. | « Problème signalé » |
| `Shipment delivered to customer` / `Delivered` | Livré par le partenaire de destination (avec nom du signataire si enregistré). | « Livré » |

## Livraison transfrontalière, relais locaux et douane

Sur [chukou1.com](https://www.chukou1.com/) et [ec.chukou1.com](https://ec.chukou1.com/), Chukou1 opère comme consolidateur transfrontalier B2B et exploitant d’entrepôts à l’étranger, et non comme réseau de livraison finale aux particuliers.

- **Le partenaire local gère la livraison finale :** dès que `Turned over to last mile facility` et `Last mile Ascan` apparaissent, les tentatives de livraison, la redirection, le dépôt en lieu sûr et les délais de garde dépendent de l’opérateur postal ou express qui prend le relais dans ton pays.
- **Douane et frais d’importation :** les envois transfrontaliers vers l’UE, les États-Unis, le Royaume-Uni, le Canada ou l’Australie sont soumis au dédouanement à destination (`Customs clearance in progress`), à la TVA à l’importation et aux droits applicables (y compris le droit de douane UE de 3 € jusqu’à 150 € et les frais de gestion UE de 2 € sur les commandes hors UE), sauf règlement par le marchand à la commande. Voir [les colis retenus en douane](guide:customs).

## Colis Chukou1 bloqué, silencieux ou manquant

Comme ce sont les marchands en ligne qui détiennent le compte d’expédition chez Chukou1, contacte toujours le vendeur ou la place de marché en premier pour un remboursement, une réexpédition ou une correction d’adresse.

- **Pauses entre la Chine et la destination :** le transport aérien international et les lots en douane laissent souvent plusieurs jours sans scan entre `Departed from Airport of Origin`, `Arrived at Airport of destination` et `Last mile Ascan`. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **Suis avec le numéro local après remise :** une fois qu’EC-FirstClass associe le code-barres du partenaire local, consulter directement ce transporteur révèle souvent des créneaux ou options de relivraison.
- **Livré, endommagé ou perdu :** vérifie d’abord auprès de ton foyer, de tes voisins et du transporteur local, puis ouvre un litige auprès du vendeur avant l’expiration de la protection acheteur. Voir [colis livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contacts Chukou1

Coordonnées officielles publiées sur [Chukou1 Contact](https://www.chukou1.com/About/Contact.aspx) et [chukou1.com](https://www.chukou1.com/) :

- **Ligne nationale en Chine :** `4006 908 223`.
- **Ligne réclamations (lun.–ven.) :** `13360562736`.
- **Agences :** siège de Guangzhou `020-23380666` (Units 2403–04, Tower A1, Hangxin Centre, No. 840 Huangpu Avenue East, Huangpu District, Guangzhou), Shenzhen `0755-28263408`, Shanghai `021-59793099`, Wuhan `027-59083347`.

> Méfie-toi des faux SMS réclamant un petit paiement en ligne pour « libérer » un colis transfrontalier en douane. Vérifie toujours toute taxe légitime sur ta page de commande ou le site de ta poste locale. Voir [les arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis Chukou1 avec Peek

[Peek](/) interroge automatiquement Chukou1 comme unique candidat lorsque tu saisis un numéro de traitement Chukou1 (`CZN261015ABCD482917` ou `CUE261015KLM4829173`, inventés) et affiche « Chukou1 a ce colis. » dès qu’EC-FirstClass le confirme ; Peek reconnaît aussi les liens collés `ec.chukou1.com/track?track_number=` et `chukou1.com/LogistictsTrack.aspx?trackNo=` pour les codes Chukou1. Si tu dois sélectionner Chukou1 manuellement, appuie sur « Changer » lors de l’ajout ou sur « Changer de transporteur » sur l’écran du colis (rechercher « 出口易 » ou « CK1 » le trouve aussi).

Peek lit directement le suivi EC-FirstClass de Chukou1 et affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « À la douane », « En livraison », « Prêt à être retiré », « Tentative de livraison », « Problème signalé », « Retourné à l’expéditeur », « Livré »), les scans de Chukou1 et ceux relayés du transporteur de destination sans numéros de téléphone ni nom du livreur, leur lieu et le pays de destination. Le code postal de destination et le nom du signataire restent privés et aucune application Peek ne les affiche. EC-FirstClass donne les heures de scan sans fuseau : Peek date chaque scan sur le fuseau horaire de son lieu et l’affiche à l’heure de ton appareil. Quand EC-FirstClass indique le transporteur de livraison et son numéro et que Peek reconnaît ce transporteur, Peek vérifie qu’il a bien le colis : le colis affiche alors « Livraison par » suivi du nom du transporteur et liste les deux numéros. Peek omet le PDF de preuve de livraison ; EC-FirstClass ne fournit ni date de livraison estimée, ni poids, ni nom de service, ni heure de livraison distincte.

Tant que le colis est ouvert dans Peek, ou le jour de sa livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Chukou1 affiche un scan en livraison, toutes les 2 minutes à partir de 6 h sur l’horloge du lieu du dernier scan, tant qu’EC-FirstClass répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelle, il vérifie toutes les heures quelle que soit ta façon de le suivre, puis toutes les 6 heures après sept jours à « Étiquette créée ». Quand EC-FirstClass ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Quand le scan le plus récent remonte à quatre jours et que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La page du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Chukou1. », avec un lien « Demande de l’aide à Chukou1 » vers la page de contact de Chukou1. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Chukou1. »

Tu peux choisir entre les réglages d’alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek se limite au suivi des mises à jour et ne peut ni dédouaner un colis, ni afficher les PDF de preuve de livraison intégrés, ni contacter Chukou1 ou ton partenaire local à ta place.

## Questions fréquentes sur le suivi Chukou1

### Qu’est-ce que Chukou1 (CK1 / EC-FirstClass) ?

Chukou1 (*出口易*, exploité par *Guangzhou Beifayi Information Technology Co., Ltd.*) est un opérateur chinois de logistique e-commerce transfrontalière et d’entrepôts à l’étranger dont le portail de suivi s’appelle **EC-FirstClass** ([ec.chukou1.com](https://ec.chukou1.com/)).

### À quoi ressemble un numéro de suivi Chukou1 ?

Les numéros de traitement Chukou1 comportent 3 lettres de préfixe, une date `AAMMJJ` de 6 chiffres, un code marchand de 3 ou 4 caractères et un numéro de série de 6 ou 7 chiffres (par exemple `CZN261015ABCD482917`, inventé).

### Que signifie `Last mile Ascan` sur le suivi Chukou1 ?

`Last mile Ascan` signifie que la poste ou le transporteur local de ton pays a effectué son premier scan d’acceptation après réception du colis issu de la liaison internationale de Chukou1.

### Comment contacter le service client Chukou1 ?

Contacte d’abord ta boutique en ligne pour un remboursement ou un changement d’adresse. La ligne publiée en Chine par Chukou1 est le `4006 908 223` (réclamations lun.–ven. `13360562736`, siège de Guangzhou `020-23380666`) sur [chukou1.com/About/Contact.aspx](https://www.chukou1.com/About/Contact.aspx).

:::sources
- [Chukou1 : site officiel](https://www.chukou1.com/) – lignes petits colis transfrontalières, entrepôts à l’étranger (US, UK, DE, FR, PL, CA, AU, PE, AR), premier kilomètre FBA et siège de Guangzhou (*广州市贝法易信息科技有限公司*)
- [Chukou1 : contact](https://www.chukou1.com/About/Contact.aspx) – `4006 908 223`, réclamations `13360562736`, téléphones et adresses des agences de Guangzhou/Shenzhen/Shanghai/Wuhan (URL d’aide liée par Peek)
- [EC-FirstClass (plateforme de suivi Chukou1)](https://ec.chukou1.com/) – suivi mondial de 100 numéros et transporteurs cités (China Post, USPS, DHL, FedEx, UPS)
- [Universal Parcel Scraper : chukou1](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chukou1) – format de numéro de traitement, règles de liens `ec.chukou1.com`/`chukou1.com`, analyse des scans initiaux et partenaires, extraction signataire/code postal/relais, PDF POD écarté et URL d’aide
:::
