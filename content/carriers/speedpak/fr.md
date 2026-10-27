---
title: SpeedPAK suivi : colis eBay, statuts et qui contacter
description: Suivre un colis eBay SpeedPAK : à quoi ressemble le numéro de 28 caractères, ce que veut dire chaque statut, qui le livre et qui contacter s'il bloque.
slug: speedpak-suivi
published: 2026-10-11
updated: 2026-10-27
---

SpeedPAK est le service d'expédition d'eBay pour les commandes venues de l'étranger, conçu avec la société logistique Orange Connex pour les colis qui partent de Chine. Son numéro compte 28 caractères et commence par ES ou EE : saisis-le sur la page de suivi d'Orange Connex, [orangeconnex.com/tracking](https://www.orangeconnex.com/tracking), ou colle-le dans le champ ci-dessus. Sur eBay.fr, tu le trouves sous forme de lien à côté de l'objet, dans « Mon eBay », puis « Objets achetés ».

## Numéro de suivi SpeedPAK : à quoi il ressemble

Tous les numéros SpeedPAK lus sur le suivi d'Orange Connex comptaient 28 lettres et chiffres et commençaient par ES ou EE. Avec des caractères inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `ES0000000000000000000000000N` | ES puis 26 lettres et chiffres : SpeedPAK. |
| `EE1234567890123456789012345X` | EE puis 26 lettres et chiffres : SpeedPAK aussi. |
| `EE123456789CN` | Deux lettres, neuf chiffres, deux lettres : un numéro postal international, pas SpeedPAK. |

Les pages d'eBay destinées aux vendeurs parlent aussi de numéros SpeedPAK qui commencent par EX ou EM. Peek ne les suit pas comme des numéros SpeedPAK.

**Où le trouver.** Sur eBay.fr, va dans « Mon eBay », puis « Objets achetés » : « Le numéro de suivi s'affiche sous la forme de lien » à côté de l'objet. Si tu as acheté sans compte, ouvre l'e-mail « Commande confirmée », puis « Afficher les informations sur la livraison ». Si aucun numéro n'apparaît, eBay te conseille de contacter le vendeur et de lui demander de l'ajouter. Plus de pistes dans [où trouver son numéro de suivi](guide:find-tracking-number).

**Un deuxième numéro en route.** Une fois le colis arrivé dans ton pays, l'historique SpeedPAK nomme souvent le transporteur qui le livre et donne le numéro de ce transporteur. Ce numéro-là se suit sur le site du transporteur en question.

## Suivi colis SpeedPAK : ce que veulent dire les statuts

Le suivi d'Orange Connex n'existe qu'en anglais et en chinois, sans version française. Il s'ouvre en chinois, sauf si tu as déjà choisi l'anglais sur la page ou si la langue de ton navigateur est exactement « en » : avec un navigateur en français, cherche donc le bouton de langue. Les lignes principales, dans l'ordre où un colis les rencontre :

| Ce qu'affiche SpeedPAK | Ce que ça veut dire pour toi |
| --- | --- |
| `Shipment Data Received` | Le vendeur a créé l'étiquette. SpeedPAK a les données, pas encore le colis. |
| `Package Received` | Le colis a été récupéré chez le vendeur. |
| `Arrived at Regional Distribution Center` | Il est dans un centre de tri, en général encore en Chine. |
| `Export Customs Declaration Completed` | Il est dédouané pour quitter le pays de départ. |
| `Departed from Origin Country` | L'avion est parti. |
| `Arrived at Destination Hub` | Il a atterri dans ton pays, avant la douane. |
| `Import Customs Clearance Completed` | La douane de ton pays l'a laissé passer. |
| `Destination DC arrival` | Il est dans un dépôt de livraison de ton pays. |
| `Out for delivery` | Il est en tournée de livraison aujourd'hui. |
| `Delivered` | Livré. |

Certaines lignes changent d'un colis à l'autre : `Arrived at destination country` et `The consignment has arrived in the country of destination` veulent dire la même chose que `Arrived at Destination Hub`. `Shipment Picked Up (A-SCAN)` marque l'enlèvement chez le vendeur : c'est le premier scan physique du colis.

Une ligne du type `LM Carrier: [carrier] , LM tracking No.: [number]` marque le relais : LM veut dire « last mile », le dernier kilomètre. Elle nomme le transporteur qui livre jusqu'à ta porte et donne son numéro. Ensuite, l'historique reprend les scans de ce transporteur avec ses propres mots, par exemple `The item is received by PostNL`. Quelques lignes restent en chinois même en anglais : `货物运输中` veut simplement dire « marchandise en transit ». `Delivered. (Recipient's front door)` précise où le colis a été laissé, ici devant la porte.

Le suivi peut rester plusieurs jours sur `Arrived at Destination Hub` ou sur une ligne de douane avant que le transporteur local scanne le colis. Orange Connex prévient que, selon la destination, il faut parfois quelques jours pour que le colis rejoigne le centre local le plus proche. Ce qui se passe à la frontière : [colis bloqué en douane](guide:customs).

## Livraison SpeedPAK : qui livre ton colis

SpeedPAK ne livre pas lui-même jusqu'à chez toi. Son historique nomme le transporteur qui prend le relais dans ton pays. Dans les historiques lus pour cette page, PostNL (écrit « NL Post ») a pris en charge des colis vers la Suisse et le Portugal ; aux États-Unis, c'étaient USPS, UniUni et DHL eCommerce, suivi d'USPS.

Aucune page d'eBay ou d'Orange Connex ne dit qui livre les colis SpeedPAK en France ou en Belgique. Ne devine pas : attends la ligne `LM Carrier`, ou demande au vendeur.

SpeedPAK et Orange Connex ne publient pour les destinataires aucune règle sur les tentatives de livraison, les avis de passage, les points relais, les consignes ou la durée de garde. Tout cela suit les règles du transporteur qui livre : une fois que tu as son nom et son numéro, va sur son site pour reprogrammer la livraison ou savoir où le colis t'attend.

Certains colis SpeedPAK ne sont suivis qu'une partie du trajet. Orange Connex indique que pour son service Economy, seule une partie du suivi est disponible (« half way tracking information »), et eBay précise que son offre européenne la plus large n'est suivie que « From pickup to destination country only », de l'enlèvement jusqu'au pays de destination. Sur ces colis, l'historique peut s'arrêter à l'atterrissage.

**Combien de temps.** Le tableau d'eBay pour les vendeurs annonce 8 à 12 jours vers 17 pays européens, dont la France, la Belgique et la Suisse, et 10 à 15 jours pour son offre européenne la plus large. Orange Connex donne 8 à 12 jours ouvrés pour son service Standard. Ce sont des moyennes, pas une date pour ta commande.

## Suivi SpeedPAK bloqué ou colis pas reçu

**Pas de mise à jour.** Orange Connex demande aux acheteurs de contacter le vendeur pour vérifier qu'il a bien expédié l'objet, et de le contacter aussi quand le colis n'a plus bougé depuis plus de cinq jours. Voir aussi [pourquoi un suivi de colis ne bouge plus](guide:tracking-not-updating). Les délais d'eBay sont dans [suivre une commande eBay](guide:ebay-orders).

**En retard ou perdu.** Sur eBay.fr, tu as « 30 jours à partir de la date de livraison estimée » pour signaler au vendeur que l'objet n'est pas arrivé. Le vendeur a « 3 jours ouvrables » pour répondre, et eBay écrit : « S'il n'a pas trouvé de solution après 3 jours ouvrables, nous pouvons intervenir. »

**Livré mais introuvable.** Si tu n'étais pas là, eBay conseille de demander à un voisin s'il a accepté le colis pour toi. Sinon, passe par la même demande sur eBay. Le vendeur et eBay restent tes interlocuteurs.

La page de réclamations d'Orange Connex s'adresse aux vendeurs, pas aux destinataires : réclamations et remboursements passent par le vendeur et eBay. Orange Connex rappelle que tu peux contacter le vendeur directement sur eBay ou sur la plateforme où tu as passé commande, y compris pour un retour.

## SpeedPAK contact : téléphone et e-mail

Relevé le 12 octobre 2026, ni eBay ni Orange Connex ne propose de contact SpeedPAK pour les destinataires en France, en Belgique ou en Suisse, et aucun numéro français n'est publié. Orange Connex demande lui-même aux acheteurs de s'adresser au vendeur.

Le service client d'Orange Connex, tel que son site le présente :

- **Téléphone** : 400 126 0008 ou 86 21 60662670 (Chine continentale), 852 30183458 (Hong Kong).
- **Horaires** : du lundi au samedi, de 9 h à 18 h, fermé le dimanche et les jours fériés ; eBay précise qu'il s'agit de l'heure de Chine.
- **E-mail** : CS@orangeconnex.com.

Le site ne dit pas que ces lignes servent aux destinataires, et elles sont en Chine. Une fois le transporteur local nommé, c'est son service client qui peut agir sur une livraison manquée.

## Suivre un colis SpeedPAK avec Peek

[Peek](/) reconnaît tout de suite un numéro ES ou EE de 28 caractères, car aucun autre transporteur n'utilise cette forme. Tu peux aussi coller un lien d'orangeconnex.com ou d'orangeconnex.cn qui contient le numéro. Il affiche le statut, l'historique avec la ville et le pays de chaque scan, le pays de destination, l'heure de livraison, et le transporteur qui livre avec son numéro. Les lignes restent telles que SpeedPAK les écrit, en anglais et parfois en chinois, sauf `Out for delivery` et `Delivered`, que Peek affiche avec ses propres mots en français. SpeedPAK ne donne ni date de livraison prévue ni point de retrait : Peek ne peut donc pas les afficher.

Quand SpeedPAK nomme le transporteur qui livre, Peek peut l'interroger et passer à lui dès qu'il montre des étapes plus récentes. Le colis garde le numéro que tu as ajouté, sa page indique « Livraison par » ce transporteur et donne son numéro, avec un lien vers son suivi. Ça dépend de ce que SpeedPAK publie, donc pas pour chaque colis.

Peek vérifie le suivi toutes les 10 minutes en journée (toutes les 2 minutes une fois le colis en livraison) et toutes les une à deux heures la nuit, place chaque scan sur une carte et peut t'envoyer des notifications : « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ». Il ne peut pas contacter SpeedPAK ni le vendeur, ni ouvrir une réclamation à ta place.

## Questions sur le suivi SpeedPAK

### eBay SpeedPAK, c'est quoi ?

Un service d'expédition d'eBay pour les commandes venues de l'étranger, proposé avec Orange Connex et pensé pour les colis partant de Chine. eBay le propose aussi depuis quelques autres pays ; depuis l'Allemagne, il ne va qu'aux États-Unis, au Royaume-Uni et en Australie.

### Est-ce que La Poste livre les colis SpeedPAK ?

Aucune page officielle ne le dit pour la France ou la Belgique. La ligne `LM Carrier` du suivi nomme le transporteur de ton colis, quand SpeedPAK le publie.

### Que faire après une tentative de livraison SpeedPAK ?

Suis les consignes du transporteur nommé dans la ligne `LM Carrier` : c'est lui qui a tenté de livrer, et SpeedPAK ne publie aucune règle à ce sujet. Son site dit où le colis t'attend et comment le faire relivrer.

### Pourquoi le suivi SpeedPAK s'affiche en chinois ?

La page d'Orange Connex n'existe qu'en anglais et en chinois, et s'ouvre en chinois sauf si ton navigateur est réglé sur l'anglais tout court (« en ») ou si tu as déjà choisi l'anglais. Change la langue sur la page ; quelques lignes restent en chinois malgré tout.

:::sources
- [Orange Connex : Tracking For SpeedPAK](https://www.orangeconnex.com/tracking) – FAQ, niveaux de service, suivi Economy, langues, téléphone, horaires et e-mail, statuts, transporteurs locaux
- [Orange Connex Logistics DE : Tracking](https://globalshipping.orangeconnex.de/tracking) – contacter le vendeur, cinq jours sans mouvement, délais avant le centre local
- [Orange Connex Logistics DE : Hilfecenter](https://globalshipping.orangeconnex.de/helpPage) – les acheteurs s'adressent au vendeur
- [Orange Connex : Claims](https://help.orangeconnex.com/helpCentre/claims.html) – réclamations réservées aux vendeurs
- [eBay.fr : Suivre l'acheminement d'un objet](https://www.ebay.fr/help/buying/shipping-delivery/suivre-votre-objet?id=4027) – Objets achetés, achat sans compte, numéro manquant
- [eBay.fr : Obtenir de l'aide pour un objet qui n'est pas arrivé](https://www.ebay.fr/help/buying/returns-items-not-received-refunds-buyers/obtenir-de-laide-pour-un-objet-qui-nest-pas-arriv?id=4042) – 30 jours, 3 jours ouvrables, intervention d'eBay
- [eBay : SpeedPAK shipping services](https://export.ebay.com/sea-en/speedpak/speedpak-shipping-services/) – service depuis la Chine, délais, listes de pays, heure de Chine
- [eBay : eBay SpeedPAK](https://export.ebay.com/en/ebay-speedpak/) – partenariat avec Orange Connex, numéros EX, EM et EE
- [eBay UK : International purchases and postage](https://www.ebay.co.uk/help/buying/postage-delivery/changing-delivery-address-method/international-purchases-postage-buy?id=4057) – SpeedPAK depuis l'Allemagne vers les États-Unis, le Royaume-Uni et l'Australie
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek affiche
:::
