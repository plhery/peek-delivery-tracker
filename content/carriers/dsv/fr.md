---
title: Suivi DSV : numéros, statuts et DB Schenker
description: Suivre un envoi DSV ou ex-DB Schenker (route, air, mer) : quel numéro utiliser, signification des statuts, livraison, réclamations et contact.
slug: suivi-dsv
published: 2026-11-23
updated: 2026-11-23
---

DSV est un groupe danois de transport et de logistique qui achemine du fret par route, air et mer, et qui a racheté Schenker (DB Schenker) à la Deutsche Bahn en 2025. Sur dsv.com, le bloc « Track your shipment » comporte deux champs : « Track DSV shipment » ouvre le suivi de DSV, myDSV, et « Track Schenker shipment » celui de l'ancien DB Schenker. Aucun compte n'est nécessaire. Le numéro vient de celui qui a réservé le transport, en général l'expéditeur ou le magasin : colle-le dans le champ ci-dessus.

## Numéro de suivi DSV : à quoi il ressemble et où le trouver

L'entreprise qui a réservé le transport chez DSV détient le numéro : regarde dans l'e-mail d'expédition du magasin ou sur ta page de commande, ou demande à l'expéditeur. Plus dans [où trouver ton numéro de suivi](guide:find-tracking-number).

Tant que DSV intègre Schenker dans ses systèmes, il maintient deux outils de suivi : un envoi réservé chez Schenker peut n'apparaître que dans celui de Schenker. Si l'un ne répond rien, essaie l'autre.

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `DEKLM482917365` | 14 caractères : un code pays européen comme DE, SE, DK ou PL, trois lettres et neuf chiffres. Un ancien numéro d'envoi routier DB Schenker, et le format que le catalogue de Peek associe à DSV. |
| `KLM4829173` | Trois lettres et sept ou huit chiffres : un autre ancien numéro d'envoi routier DB Schenker. Peek ne nomme pas DSV à partir de ce format. |
| `094827361549283710` | 18 chiffres (20 avec `00` devant) : le numéro de code-barres sur un colis d'un ancien envoi routier DB Schenker. |
| Lettres et chiffres | Un numéro d'envoi, de réservation ou de connaissement interne (*house bill*) myDSV. Peek interroge myDSV avec toute chaîne de 5 à 40 lettres et chiffres contenant au moins un chiffre. |
| `mydsv.com/track-shipment?query=DEKLM482917365` | Un lien vers le suivi myDSV, tel que l'ouvre « Track DSV shipment » sur dsv.com. |

Une référence client ou de commande, ou la lettre de transport aérien principale (*master air waybill*) de la compagnie aérienne, peut retrouver un envoi dans la recherche myDSV, mais Peek ne suit pas ces références : utilise le numéro d'envoi, de réservation ou de *house bill* DSV. Peek ne peut pas non plus choisir un envoi particulier lorsqu'un même numéro correspond à plusieurs expéditions.

## Signification des statuts de suivi DSV

myDSV enregistre chaque scan d'un envoi, de la réservation à la preuve de livraison. Sa vue publique affiche des informations générales ; la vue privée, sur demande, donne le détail de l'expédition. Voici les étapes myDSV telles que Peek les lit :

| DSV affiche | Ce que ça veut dire pour toi | Statut Peek |
| --- | --- | --- |
| `Booking` | DSV a enregistré la réservation, datée sans heure. | « Étiquette créée » |
| `Creation` | L'envoi a été créé dans le système routier de DSV. | « Étiquette créée » |
| `Actual pickup` | DSV l'a enlevé chez l'expéditeur. | « Pris en charge » |
| `Cargo received` | La marchandise a rejoint DSV, sur un terminal ou après l'enlèvement. | « Pris en charge » |
| `Actual Departure`, `Actual departure - Origin Port` | Parti d'un port ou d'un aéroport sur le trajet principal. | « En transit » |
| `Actual arrival`, `Actual arrival- Destination Port` | Arrivé à un port ou un aéroport sur le trajet principal. | « En transit » |
| `Handover responsibilty of cargo`, `Handover of Cargo` | DSV a remis la marchandise en fin de prestation, ce qui clôt l'envoi (le premier conserve l'orthographe de DSV). | « Livré » |
| `Actual delivery` | Livré. | « Livré » |

`Estimated Departure`, `Estimated arrival`, `Estimated pickup`, `Estimated delivery`, `Requested pickup` et `Requested delivery` sont des prévisions, pas des scans : Peek les écarte de l'historique et affiche `Estimated delivery` comme jour de livraison prévu (ou plage de jours) jusqu'à la mise en livraison.

L'ancien suivi DB Schenker couvre les envois routiers avec ses propres libellés :

| Le suivi DB Schenker affiche | Ce que ça veut dire pour toi | Statut Peek |
| --- | --- | --- |
| `Booked` | L'expéditeur a réservé le transport. | « Étiquette créée » |
| `Out for collection` | Un chauffeur est en route vers l'expéditeur. | « Étiquette créée » |
| `Collected` | Enlevé chez l'expéditeur. | « Pris en charge » |
| `Delivered to terminal by shipper` | L'expéditeur l'a déposé dans un terminal. | « Pris en charge » |
| `Arrived`, `Departed` | Arrivé dans un terminal ou reparti de celui-ci. | « En transit » |
| `Customs clearance initiated` | Le dédouanement a commencé. | « À la douane » |
| `Customs clearance finalised` | Dédouané, et de nouveau en route. | « En transit » |
| `Out for Delivery` | Dans le camion de livraison. | « En livraison » |
| `Not delivered` | La livraison a échoué ; un motif peut suivre. | « Tentative de livraison » |
| `To consignee's disposal`, `Ready for pick up` | Mis à disposition pour retrait. | « Prêt à être retiré » |
| `Delivered` | Livré. | « Livré » |
| `Picked up by consignee`, `Picked up at agent` | Retiré par le destinataire ou chez un agent. | « Livré » |
| `ePOD available` | La preuve de livraison électronique est disponible. | « Livré » |

En chemin, il affiche aussi `Linehaul departed`, `Linehaul arrived`, `Terminal inventory`, `Vessel Loaded`, `Vessel Unloaded`, `Train Loaded`, `Train Unloaded`, `Vessel/Train Loaded` et `Vessel/Train Unloaded` : tous apparaissent comme « En transit » dans Peek. `Not Collected` (enlèvement manqué) et `Not loaded` (chargement prévu manqué, avec un motif) affichent « Problème signalé ». Quand le suivi affiche un motif à côté d'une étape, par exemple la raison d'un échec de livraison, Peek l'ajoute après le libellé. Plus sur les étapes dans [signification des statuts de suivi](guide:tracking-statuses).

## Comment DSV livre

**Palettes et lots partiels.** DSV décrit son réseau de groupage routier comme le plus vaste d'Europe : plus de 36 pays, plus de 400 terminaux et des envois jusqu'à 2 500 kg ; une palette ou une commande volumineuse peut donc arriver par camion depuis un terminal DSV.

**Commandes en ligne.** Le service Parcel E-commerce de DSV collecte les colis d'un magasin, les consolide à l'international et confie la livraison finale à des réseaux locaux : un autre transporteur peut donc te livrer. Si l'e-mail du magasin en nomme un ou donne un second numéro, suis-le aussi : la recherche DSV de Peek n'indique aucun transporteur suivant.

**Notifications et preuve de livraison.** Dans myDSV, les utilisateurs peuvent activer des alertes (par exemple pour l'enlèvement ou le dédouanement), consulter les heures d'arrivée estimées pour le fret routier et télécharger la preuve de livraison une fois l'envoi terminé. Si ce n'est pas toi qui as réservé DSV, demande à l'expéditeur. Les pages de DSV ne précisent ni le nombre de tentatives de livraison ni la durée de garde dans un terminal : adresse-toi au bureau DSV chargé de la livraison.

**Douane.** Dans la plupart des pays où il possède des bureaux, DSV est commissionnaire en douane agréé ; ailleurs il travaille avec des agents. Pour toute aide au dédouanement, DSV demande de contacter DSV dans le pays concerné. En Irlande et dans le reste de l'UE, les biens achetés en ligne hors de l'UE sont soumis à un droit de douane de 3 € par type de produit sur les commandes jusqu'à 150 € depuis le 1er juillet 2026, et à des frais de dossier de l'Union de 2 € par type de produit, quelle que soit la valeur, depuis le 1er novembre 2026. Ailleurs s'appliquent les règles de ton pays : voir [colis bloqué en douane](guide:customs).

## Envoi DSV en retard, bloqué ou abîmé

**Aucune évolution.** Si un suivi s'arrête, essaie l'autre sur dsv.com : un envoi réservé chez Schenker n'est peut-être pas encore dans myDSV. Ensuite, contacte l'expéditeur qui a réservé DSV et dispose de son contact direct. Voir [pourquoi un suivi ne bouge plus](guide:tracking-not-updating).

**Marchandise abîmée ou manquante.** Pour une commande en ligne, commence par le magasin qui a réservé DSV. Le portail eClaims de DSV recueille les réclamations et justificatifs, affiche leur avancement et te met en relation avec ses gestionnaires de sinistres locaux ; aucun délai n'y est mentionné, donc n'attends pas. Voir [colis abîmé](guide:damaged-parcel) et [colis perdu](guide:lost-parcel).

**Usurpation du nom DSV.** DSV prévient que des entreprises ou des personnes sans lien avec le groupe utilisent parfois son nom pour vendre des services de fret. En cas de doute sur un site ou un interlocuteur, vérifie auprès de ton bureau DSV local ; pour des SMS suspects liés à un colis, voir [arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Contacter DSV

Sur dsv.com :

- **Formulaire de contact :** sur la [page d'aide et de contact de DSV](https://www.dsv.com/en/support/help-and-contact).
- **Bureaux locaux :** DSV est présent dans plus de 90 pays ; [trouver ton bureau DSV local](https://www.dsv.com/en/support/find-dsv) par pays.
- **Réclamations :** via eClaims depuis la [page Claims de DSV](https://www.dsv.com/en/support/self-services/claims).
- **DSV A/S :** Hedehusene, Danemark, +45 43 20 30 40 (tel qu'indiqué dans ses mentions légales ; ce n'est pas une ligne de suivi).

Les pages d'aide de DSV ne publient aucun numéro de téléphone dédié au suivi.

## Suivre un envoi DSV avec Peek

Colle un numéro à 14 caractères tel que `DEKLM482917365` : Peek interroge DSV pendant l'ajout (« Vérification des services de suivi… »), puis affiche « DSV a ce colis. » lorsque DSV le connaît. Si DSV ne le liste pas encore, l'envoi affiche « En attente du premier scan de DSV », avec la mention « Probablement DSV », pendant que Peek continue de vérifier ; au bout d'une semaine, Peek propose de vérifier le numéro ou de choisir un autre transporteur.

Peek ne reconnaît pas DSV à partir d'un autre format de numéro. Colle-le tout de même : Peek suit plus de 4 500 transporteurs. Pour choisir DSV toi-même, appuie sur « Changer » et recherche DSV ; « Schenker » le trouve aussi (« Aussi appelé DB Schenker »). Sur un colis déjà ajouté, utilise « Changer de transporteur ». Un lien de suivi mydsv.com (`?query=`) classe aussitôt l'envoi sous DSV, tout comme un lien du suivi Schenker sur dsv.com avec `refNumber=`.

Peek interroge d'abord myDSV. Un ancien numéro routier ou code-barres de colis DB Schenker inconnu de myDSV est transmis au suivi Schenker, que Peek lit uniquement pour les envois routiers : pour un envoi aérien ou maritime présent uniquement dans ce dernier, utilise « Track Schenker shipment » sur dsv.com.

Peek affiche le statut, chaque étape dans la formulation de DSV avec sa ville et son pays, ainsi que le jour de livraison prévu. DSV donne l'heure locale de chaque scan sans fuseau horaire : Peek déduit le fuseau à partir du lieu (ville, port, code aéroport) ou des scans voisins, sinon à l'heure de Copenhague, ce qui peut parfois être inexact. Peek omet le nom du service, la ville de l'expéditeur, ton adresse, les codes postaux et la barre de progression DSV, ainsi que (depuis le suivi Schenker) les parties, références, marchandises et le nom du signataire.

Si Early Bird, un transporteur suédois, confie ton colis à DSV ou DB Schenker, Peek indique DSV comme transporteur livreur et le suit dès que DSV publie des scans plus récents.

Peek vérifie jusqu'à toutes les 10 minutes, ou toutes les 2 minutes dès 6 h heure locale le jour où DSV scanne l'envoi en livraison, tant que le suivi de DSV répond (via les services de suivi, toutes les heures, et jusqu'à toutes les 15 minutes le jour de livraison quand le colis est ouvert ou les alertes activées), et peut te notifier pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après deux jours sans nouvelles (12 heures tant que l'envoi affiche « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Il ne peut ni contacter DSV ou l'expéditeur, ni réserver une livraison, ni ouvrir une réclamation.

## Questions sur le suivi DSV

### DSV et DB Schenker sont-ils la même entreprise ?

Oui. DSV, fondé en 1976, a finalisé le rachat de Schenker à la Deutsche Bahn le 30 avril 2025, et dbschenker.com a été fusionné dans dsv.com. Les envois Schenker gardent pour l'instant leur propre suivi.

### Comment suivre aujourd'hui un envoi DB Schenker ?

Saisis le numéro dans « Track Schenker shipment » sous « Track your shipment » sur dsv.com, ou colle-le dans le champ ci-dessus ; sauf s'il comporte 14 caractères commençant par un code pays, choisis DSV toi-même. Peek interroge myDSV, puis le suivi Schenker pour les envois routiers.

### Que veut dire « handover of cargo » dans le suivi DSV ?

DSV a remis la marchandise à la fin de sa prestation et l'envoi est terminé : Peek affiche « Livré ». Si rien ne t'est parvenu, demande à l'expéditeur où DSV l'a remis, et lis [colis livré mais non reçu](guide:delivered-not-received).

### DSV a-t-il un numéro de téléphone de service client ?

Ses pages d'aide n'en donnent aucun pour le suivi. Utilise le formulaire de contact ou trouve ton bureau DSV local sur dsv.com. Le numéro des mentions légales de DSV A/S, +45 43 20 30 40 au Danemark, n'est pas une ligne de suivi.

:::sources
- [DSV : Book and track shipments with myDSV](https://www.dsv.com/en/digital-solutions/book-and-track) – suivi sans compte, deux champs de suivi, fusion de dbschenker.com dans dsv.com
- [DSV : myDSV](https://www.dsv.com/en/digital-solutions/mydsv) – vues publique et privée, journal des scans, alertes, preuve de livraison
- [DSV : Self-services](https://www.dsv.com/en/support/self-services) – suivis DSV et Schenker, eClaims, systèmes Schenker jusqu'à la migration
- [DSV : Frequently asked questions](https://www.dsv.com/en/support/faq) – formulaire de contact, deux versions myDSV pendant la transition
- [DSV : Help and contact](https://www.dsv.com/en/support/help-and-contact) – plus de 90 pays, recherche de bureau
- [DSV : Find your local DSV office](https://www.dsv.com/en/support/find-dsv) – bureaux par pays
- [DSV : Claims](https://www.dsv.com/en/support/self-services/claims) – fonctionnement d'eClaims
- [DSV : Groupage transport](https://www.dsv.com/en/our-solutions/modes-of-transport/road-transport/groupage) – 36+ pays, 400+ terminaux, jusqu'à 2 500 kg
- [DSV : E-commerce delivery solutions](https://www.dsv.com/en/our-solutions/contract-logistics/ecommerce-solutions/last-mile-delivery) – consolidation transfrontalière, réseaux locaux du dernier kilomètre
- [DSV : Customs clearance](https://www.dsv.com/en/our-solutions/modes-of-transport/value-added-services/customs-clearance) – commissionnaire agréé, agents, contacter DSV dans le pays concerné
- [DSV : History](https://www.dsv.com/en/about-dsv/history) – fondé en 1976, route, air et mer, rachat de Schenker à la Deutsche Bahn en 2025
- [DSV : DSV completes acquisition of Schenker](https://www.dsv.com/en/about-dsv/press/news/dsv-completes-acquisition-of-schenker-2628130) – finalisé le 30 avril 2025, intégration
- [DSV : Legal notice](https://www.dsv.com/en/about-dsv/legal-notice) – DSV A/S au Danemark, téléphone, usurpation du nom DSV
- [Commission européenne : réforme douanière de l'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € par type de produit jusqu'à 150 €, frais de dossier de 2 €
- [Commission européenne : guide sur le droit de 3 € et les frais de dossier de l'Union (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € par type de produit quelle que soit la valeur
- [Universal Parcel Scraper : DSV](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dsv) – formats et liens, deux suivis, libellés et étapes DSV, heures locales, ce que Peek lit
:::
