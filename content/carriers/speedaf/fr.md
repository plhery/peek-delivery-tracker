---
title: Suivi colis Speedaf Express : numéro, statuts et contacts
description: Suivre un colis Speedaf Express envoyé de Chine au Maroc, au Nigeria, au Kenya ou aux Émirats : le numéro, chaque statut, les tentatives de livraison, les contacts.
slug: speedaf-suivi
published: 2026-11-01
updated: 2026-11-01
---

Speedaf Express (速达非) est un réseau de livraison fondé en 2019 qui envoie par avion les colis de ses entrepôts en Chine vers l'Afrique, le Moyen-Orient, le Pakistan et quelques pays d'Europe, puis les livre lui-même. Pour suivre ton colis, saisis son numéro sous « Track » sur [speedaf.com](https://www.speedaf.com/cn-en/send-parcel) ou colle-le ci-dessus. Le numéro figure dans les détails d'expédition de ta commande.

## Numéro de suivi Speedaf : à quoi il ressemble

Un numéro Speedaf commence par le code du pays de destination : MA pour le Maroc, NG pour le Nigeria, etc. Deux formes, avec des chiffres inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `MA000000000R` | Code pays, 9 chiffres et une lettre, le plus souvent R, parfois E. |
| `MA000000000000` | Code pays et 12 chiffres. |
| `PK000000000000T` | Pareil, avec un T à la fin. |
| `LP00000000000000` | Pas Speedaf : la référence [Cainiao](carrier:cainiao) d'une commande AliExpress. |

Le suivi de speedaf.com accepte jusqu'à 20 numéros, un par ligne. Tape-les en majuscules : il ne reconnaît pas les minuscules. Pas de numéro ? Lis [où trouver son numéro de suivi](guide:find-tracking-number) ou [les commandes AliExpress](guide:aliexpress-orders).

## Suivi colis Speedaf : ce que veulent dire les statuts

Speedaf écrit ses scans en anglais, sinon en chinois, parfois dans la langue de ton pays, avec les sites entre crochets 【comme ceci】 dans la ligne. Dans l'ordre où ils apparaissent pour un colis venu de Chine :

| Ce qu'écrit Speedaf | Peek affiche | Ce que ça veut dire pour toi |
| --- | --- | --- |
| `processing, order has been inbounded` | Pris en charge | L'entrepôt de Speedaf en Chine, nommé juste avant, a le colis. |
| `processing assembling operation`, `processing, order has been outbounded` | En transit | Mis en sac, puis sorti de l'entrepôt. |
| `clearance in progress` | À la douane | Déclaration d'export en Chine. |
| `Delivery to the airport`, `Cargo flight departured`, `Cargo flight arrived` | En transit | Vers l'aéroport, puis en vol jusqu'à ton pays. |
| `Clearance handover`, `Customs clearance in progress` | À la douane | Récupéré à l'aéroport, puis en dédouanement dans ton pays. |
| `pending Tax` | À la douane | Droits à payer avant que la douane le libère (voir plus bas). |
| `Customs clearance tax paid` | À la douane | Droits payés, le dédouanement continue. |
| `Customs Clearance Completed` | En transit | La douane l'a libéré. |
| `Arrived at DC Warehouse`, `Arrival at Speedaf DC` | En transit | À l'entrepôt de Speedaf ou dans un centre de distribution de ton pays. |
| `Received in` | En transit | Reçu au premier site Speedaf de ton pays, pas une nouvelle prise en charge. |
| `Loaded at`, `Departed for`, `Arrive at Distribution`, `Arrived at Site` | En transit | Entre les centres de Speedaf et le site qui te livre. |
| `scanned for delivery` | En livraison | Un livreur l'a pris pour sa tournée. |
| `Issue Parcel Reason【Rescheduled delivery】` | Tentative de livraison | Livraison reportée au jour indiqué entre crochets juste après. |
| `Issue Parcel Reason【Receiver Rejected Parcel】` | Problème signalé | Colis refusé. |
| `Issue Parcel Reason【Delayed payment of tax by customer】` | À la douane | Le livreur est revenu : les droits n'étaient pas payés. |
| `Please come to collect with the Pick-Up Code` | Prêt à être retiré | Il t'attend au site Speedaf nommé juste avant, à retirer avec ton code. |
| `Parcel delivered 【Collected】`, `【Parcel delivered】` | Livré | Livré ou retiré. La ligne peut nommer le site de retrait. |
| `Transported to the warehouse，【Return Overdue】` | Problème signalé | Il repart vers l'entrepôt de Speedaf. |
| `Warehouse received` | Retourné à l’expéditeur | Le colis renvoyé est revenu à l'entrepôt de Speedaf. |

Un `In Transit` seul peut cacher un blocage : son code désigne un colis retenu pour un inventaire. `Products not for personal use` et `Clearance Exception` signalent une question ou un problème à la douane ; `Long Time Tax No Paid,Email To Sender`, que les droits attendent depuis longtemps et que Speedaf écrit à l'expéditeur. En chinois, Speedaf écrit notamment `进行处理，订单已操作入库` (Pris en charge), `清关中` (À la douane), `已做派件扫描` (En livraison) et `包裹【已签收】` (Livré).

## Livraison Speedaf : domicile, point de retrait et tentatives

Speedaf a ses propres livreurs pour le dernier kilomètre, avec livraison à domicile et paiement à la livraison (COD). Sa page « Look for Service Site » situe ses sites sur une carte.

Les règles dépendent des conditions de chaque pays, rédigées pour l'expéditeur ; celles du Nigeria et des Émirats portent sur les livraisons nationales :

- **Émirats arabes unis :** chaque envoi a droit à 3 tentatives de livraison gratuites ; un colis impossible à livrer repart chez l'expéditeur.
- **Kenya, Ouganda et Pakistan :** le colis repart chez l'expéditeur après une troisième tentative ratée, ou si tu le refuses ou refuses d'en payer les frais. Speedaf livre à l'adresse, pas forcément en main propre au destinataire nommé.
- **Nigeria et Émirats :** colis non livrable si tu refuses le colis ou le paiement à la livraison, si l'adresse est fausse ou hors zone, ou si le jour que tu demandes dépasse la durée de garde de Speedaf. Aucun des deux textes ne donne cette durée.

Speedaf ne publie aucun délai de retrait. Livreur manqué ? Lis [avis de passage : que faire](guide:missed-delivery).

## Droits de douane Speedaf : pending Tax

Si des droits sont dus, le suivi s'arrête sur `pending Tax`. Pour un colis vers l'Afrique du Sud, le suivi de Speedaf affiche alors un lien « I want to pay taxes » près du numéro, puis « Taxes paid » une fois les droits réglés.

Si un SMS ou un e-mail te demande de payer, compare d'abord avec le suivi sur speedaf.com : lis [arnaque colis par SMS](guide:delivery-scam-texts) et [colis bloqué en douane](guide:customs).

## Colis Speedaf en retard, perdu ou livré mais pas reçu

**Bloqué.** Si le suivi ne bouge plus depuis des jours, lis [le suivi d'un colis venant de Chine](guide:tracking-from-china) et [pourquoi un suivi ne bouge plus](guide:tracking-not-updating).

**Qui peut réclamer.** Dans ses conditions au Kenya, en Ouganda, au Pakistan, au Nigeria et aux Émirats, Speedaf ne règle les réclamations qu'avec l'expéditeur. Pour une commande en ligne, l'expéditeur, c'est la boutique : demande-lui, ou à la plateforme, un remboursement ou un nouvel envoi.

**Préviens vite la boutique.** Les conditions de Speedaf fixent des délais courts :

- **Colis abîmé :** par écrit sous 7 jours calendaires après la livraison au Kenya et en Ouganda, sous 72 heures au Nigeria et aux Émirats, sous 48 heures en Arabie saoudite.
- **Livré mais pas reçu :** contester la preuve de livraison sous 5 jours calendaires au Nigeria et aux Émirats, sous 5 jours ouvrés au Pakistan.
- **Perdu :** au Nigeria et aux Émirats, déposer une réclamation de suivi sous 14 jours calendaires après la prise en charge. Sans scan depuis plus de 24 heures, Speedaf cherche le colis 7 jours ouvrés, puis le déclare perdu s'il reste introuvable.

Le suivi de Speedaf indique qui a signé pour le colis : vérifie-le d'abord. Voir aussi [colis livré mais non reçu](guide:delivered-not-received) et [colis perdu](guide:lost-parcel).

## Contacter Speedaf : téléphone et service client

Coordonnées relevées le 28 octobre 2026 en bas de page de chaque site national de Speedaf (pour l'Irlande et la Zambie, passe par le choix du pays, puis English), horaires à l'heure locale :

| Pays | Téléphone | E-mail | Horaires |
| --- | --- | --- | --- |
| Nigeria | +234 800 070 7070, +234 708 063 6400, +234 9073064940 (aussi WhatsApp) | ng.info@speedaf.com, nigeria.info@speedaf.com | lun.-ven. 8 h-17 h, sam. 8 h-14 h |
| Ghana | +233 30 824 9100 | gh.info@speedaf.com | lun.-sam. 8 h 30-17 h 30 |
| Kenya | +254 741 000 666, +254 741 000 888, WhatsApp +254 741000666 | kenya.info@speedaf.com | lun.-ven. 8 h-18 h, sam. 8 h-14 h |
| Ouganda | +256 709 198 795 | ug.info@speedaf.com | lun.-ven. 9 h-17 h, sam. 9 h-14 h |
| Zambie | +260 971269390 | zm.info@speedaf.com | lun.-ven. 7 h 45-17 h 15, sam. 8 h-13 h |
| Afrique du Sud | +27 108247184, +27 726393849, WhatsApp +27 664562613 | cs.southafrica@speedaf.com | non indiqués |
| Pakistan | +92 311 118 7654, WhatsApp +92 3450010817 | pk.info@speedaf.com | 7 j/7, 9 h-18 h |
| Émirats | +971 800 178 | ae.info@speedaf.com | lun.-sam. 9 h-18 h |
| Arabie saoudite | aucun | sa.info@speedaf.com | non indiqués |
| Irlande | +35312633068, WhatsApp +353 834281088 | info_customerserviceie@speedaf.com | lun.-ven. 8 h 30-17 h 30 |

Chaque site a aussi un formulaire « Customer Support ».

## Suivre un colis Speedaf avec Peek

[Peek](/) reconnaît un numéro Speedaf à sa forme quand il commence par NG, GH, KE, UG, ZM, ZA, EG, MA, SA, AE, PK, KZ, MU ou MV, puis 9 chiffres et R ou E. Avec 12 chiffres à la place, ou IE et 12 chiffres pour l'Irlande, la forme ne suffit pas : Peek affiche « Vérification des services de suivi… », puis « Speedaf a ce colis. » si Speedaf le connaît. Peek ne connaît aucun format pour la Suisse, l'Albanie, le Monténégro ou la Serbie : pour ces pays et tout numéro hors de ces deux formes, choisis « Speedaf » avec « Changer ». Un lien speedaf.com contenant `tradeNo=` est lu comme Speedaf, quel que soit le numéro. Peek envoie le numéro en majuscules : les minuscules passent.

Peek lit directement le suivi de Speedaf : statut, historique, pays de destination et heure de livraison. Chaque scan est à l'heure de son site (UTC+8 dans les entrepôts chinois, puis celle de ton pays), affichée dans le fuseau horaire de ton appareil. Les sites ne sont nommés que dans les lignes, donc pas de carte, et Speedaf ne donne pas de date de livraison prévue. Si Speedaf connaît le numéro sans montrer de scan, comme pour certains colis anciens, Peek continue de vérifier et affiche « En attente du transporteur », ou « En attente du premier scan de Speedaf » pour un numéro à 12 chiffres si tu n'as pas choisi Speedaf.

Les lignes s'affichent telles qu'écrites, en anglais sinon en chinois, sans les noms du livreur, de l'agent et du signataire ni le téléphone du livreur. Peek écarte aussi la preuve de livraison, le lien de paiement des droits et le nombre de colis, et ne suit que ton numéro, pas les autres colis de la même lettre de transport.

Peek vérifie toutes les 10 minutes, et toutes les 2 minutes une fois le colis en livraison. Ses notifications : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Il ne peut ni payer les droits, ni reprogrammer une livraison, ni contacter Speedaf ou la boutique à ta place.

## Questions sur le suivi Speedaf

### Speedaf Express, c'est fiable ?

Oui : c'est une société de logistique fondée en 2019, avec ZTO parmi ses premiers actionnaires, qui a son propre suivi sur speedaf.com et ses propres livreurs du dernier kilomètre dans ce qu'elle appelle les marchés émergents.

### Dans quels pays livre Speedaf ?

Outre la Chine, la liste de pays de Speedaf cite : Égypte, Afrique du Sud, Maroc, Émirats arabes unis, Ghana, Nigeria, Kenya, Ouganda, Arabie saoudite, Pakistan, Kazakhstan, Maurice, Maldives, Suisse, Monténégro, Albanie, Irlande, Serbie et Zambie. Côté francophone : le Maroc et la Suisse, qui ont un site en français, et Maurice, mais ni la France ni la Belgique.

### Combien de temps met un colis Speedaf ?

Pour les colis de Chine vers le Nigeria, le Ghana, le Kenya, l'Ouganda, le Maroc, l'Égypte, l'Arabie saoudite, les Émirats et le Pakistan, la page eParcel de Speedaf donne un délai de référence de 5 à 10 jours ouvrés. Sa page d'aide dit que ça dépend de la destination, et ses conditions au Kenya, en Ouganda et au Pakistan, que les délais ne sont pas garantis.

### Que veut dire pending Tax sur le suivi Speedaf ?

Des droits ou taxes sont dus avant que la douane de ton pays libère le colis ; une fois payés, suivent `Customs clearance tax paid` puis `Customs Clearance Completed`.

:::sources
- [Speedaf : Enterprise Introduction](https://www.speedaf.com/ng-en/business/about-us) – fondation en 2019, ZTO
- [Speedaf : Select your location and language](https://www.speedaf.com/cn-en/user-opt/locale-select) – pays ; contacts irlandais et zambiens (le pays, puis English)
- [Speedaf : Speedaf eParcel](https://www.speedaf.com/ke-en/product/intl-straight-small) – colis de Chine, 5 à 10 jours ouvrés
- [Speedaf : Warehouse & Fulfillment](https://www.speedaf.com/ke-en/product/warehouse-promise) – livreurs du dernier kilomètre, paiement à la livraison
- [Speedaf : Last-mile delivery](https://www.speedaf.com/ke-en/product/warehouse-delivery) – paiement à la livraison, livraison à domicile
- [Speedaf : Help Center](https://www.speedaf.com/ng-en/help) – délai d'acheminement
- [Speedaf : Track](https://www.speedaf.com/ng-en/send-parcel) – 20 numéros ; paiement des droits en Afrique du Sud
- [Speedaf : Look for Service Site](https://www.speedaf.com/ke-en/location-map) – carte des sites
- [Speedaf : Customer Support](https://www.speedaf.com/ng-en/service/support) – formulaire de contact
- [Speedaf Nigeria : Home](https://www.speedaf.com/ng-en) – téléphones, e-mails, horaires, WhatsApp
- [Speedaf Ghana : Home](https://www.speedaf.com/gh-en) – téléphone, e-mail, horaires
- [Speedaf Kenya : Home](https://www.speedaf.com/ke-en) – téléphones, e-mail, horaires, WhatsApp
- [Speedaf Uganda : Home](https://www.speedaf.com/ug-en) – téléphone, e-mail, horaires
- [Speedaf South Africa : Home](https://www.speedaf.com/za-en) – téléphones, e-mail, WhatsApp
- [Speedaf Pakistan : Home](https://www.speedaf.com/pk-en) – téléphone, e-mail, horaires, WhatsApp
- [Speedaf UAE : Home](https://www.speedaf.com/ae-en) – téléphone, e-mail, horaires
- [Speedaf Saudi Arabia : Home](https://www.speedaf.com/sa-en) – e-mail
- [Speedaf Kenya : Terms and Conditions](https://www.speedaf.com/ke-en/service-rule) – tentatives, refus, destinataire, réclamations, délais
- [Speedaf Uganda : Terms and Conditions](https://www.speedaf.com/ug-en/service-rule) – idem pour l'Ouganda
- [Speedaf Pakistan : Terms and Conditions](https://www.speedaf.com/pk-en/service-rule) – tentatives, destinataire, réclamations, preuve de livraison contestée
- [Speedaf UAE : Terms & Conditions](https://www.speedaf.com/ae-en/service-rule) – livraison intérieure : tentatives, colis non livrables, délais et réclamations
- [Speedaf Nigeria : Service Agreement](https://www.speedaf.com/ng-en/service-rule) – livraison intérieure : colis non livrables, délais et réclamations
- [Speedaf Saudi Arabia : Express Shipping Terms](https://www.speedaf.com/sa-en/service-rule) – dommage signalé sous 48 heures
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – réseau, numéros, lignes de suivi, ce qu'affichent Speedaf et Peek
:::
