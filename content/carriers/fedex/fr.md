---
title: FedEx suivi : numéro, statuts et livraison manquée
description: Suivre un colis FedEx : le numéro à 12 chiffres, le sens de « Delivery exception » et des autres scans, les frais de douane et tes droits si le colis se perd.
slug: fedex-suivi-colis
published: 2026-10-13
updated: 2026-10-28
---

Pour suivre ton colis, colle le numéro de suivi FedEx, ou un lien de suivi FedEx, dans le champ ci-dessus. Le suivi de FedEx lui-même se trouve sur fedex.com et dans l'application FedEx Mobile.

## Numéro de suivi FedEx : où le trouver et combien de chiffres

Regarde dans l'e-mail d'expédition ou sur la page de commande de la boutique. Un lien de suivi fedex.com contient aussi le numéro, après `trknbr=` ou `tracknumbers=`. Toujours rien ? Voir [où trouver son numéro de suivi](guide:find-tracking-number).

| Forme (inventée) | Ce que c'est |
| --- | --- |
| `123456789012` | 12 chiffres : un numéro de suivi FedEx. Le dernier chiffre est une clé de contrôle calculée à partir des 11 précédents. |
| `123456789012343` | 15 chiffres : une forme que Peek classe aussi chez FedEx. |
| `61290000000000000014` | 20 chiffres commençant par `6129` ou `7489` : FedEx Ground Economy (anciennement SmartPost), aux États-Unis. |

Attention aux numéros qui se ressemblent : 12 chiffres, c'est aussi la longueur des numéros de [DPD France](carrier:dpd-fr), [Mondial Relay](carrier:mondial-relay) ou [Colis Privé](carrier:colis-prive), et 15 chiffres celle de DPD France. Si fedex.com ne trouve rien, regarde quel transporteur la boutique nomme dans son e-mail.

## Statuts du suivi FedEx : « Delivery exception » et les autres

Peek lit les scans de FedEx en anglais, tels que FedEx les écrit, et affiche au-dessus l'étape en français. Voici les principaux, à peu près dans l'ordre où un colis les rencontre :

| FedEx écrit | Peek affiche | Ce que ça veut dire pour toi |
| --- | --- | --- |
| `Shipment information sent to FedEx` | Étiquette créée | L'expéditeur a transmis à FedEx les données de l'envoi. |
| `Picked up`, `Tendered at FedEx location` | Pris en charge | FedEx a le colis : un coursier l'a enlevé, ou l'expéditeur l'a déposé dans un site FedEx. |
| `Arrived at FedEx location`, `Departed FedEx location`, `At local FedEx facility`, `On the way` | En transit | Il circule dans le réseau de FedEx. |
| `Clearance in progress`, `Clearance delay` | À la douane | Il est en cours de dédouanement (voir plus bas). |
| `International shipment release - Import` | En transit | La douane l'a libéré dans le pays de destination. |
| `On FedEx vehicle for delivery` | En livraison | Il est dans le véhicule de livraison. |
| `Delivery exception`, `Customer not available or business closed` | Tentative de livraison | La livraison n'a pas pu se faire. |
| `Ready for pickup` | Prêt à être retiré | Il t'attend dans un point de retrait. |
| `Delivered` | Livré | Il a été livré. |
| `Shipment exception` | Problème signalé | FedEx signale un problème avec l'envoi. |
| `Returning to shipper` | Retourné à l'expéditeur | Il repart chez l'expéditeur. |

Quand FedEx marque un colis comme retardé, Peek affiche « Problème signalé », sauf si le dernier scan est `Package received after FedEx cutoff` : FedEx a reçu le colis après l'heure limite du jour, et il part le jour ouvrable suivant.

## Livraison FedEx manquée et point de retrait

Si la livraison échoue, un scan peut en donner la raison : `Customer not available or business closed` si personne n'était là, ou `Local delivery restriction - Delivery not attempted` si une restriction locale a empêché la tentative.

Si tu demandes à FedEx de garder le colis dans un point de retrait, le scan peut afficher `Delivery option requested` pendant le trajet, puis `Ready for pickup` à l'arrivée. Peek montre alors le nom et l'adresse du point si FedEx les donne : pour une mise en attente demandée après l'envoi, la page de FedEx n'indique que la ville aux visiteurs non connectés. La page de FedEx peut appeler la dernière étape `Heading to pickup location`, que Peek compte comme « En livraison ». Ces changements se font auprès de FedEx, pas dans Peek. Plus d'infos dans [livraison manquée](guide:missed-delivery) et [colis non retiré](guide:uncollected-parcel).

## Frais de douane sur un colis FedEx

Un colis venu de l'extérieur de l'UE, ou de l'étranger pour la Suisse, peut demander des frais d'importation. Tant qu'il est en douane, le suivi affiche `Clearance in progress` ou `Clearance delay`, puis `International shipment release - Import` quand la douane le libère.

- **France et Belgique** : comme partout dans l'UE depuis le 1er juillet 2026, une commande de 150 € ou moins venue de l'extérieur de l'UE paie un droit de douane forfaitaire de 3 € par article, en plus de la TVA ; c'est le vendeur ou le transporteur qui le perçoit. En France métropolitaine, la douane précise que ces taxes peuvent t'être demandées à la réception du colis par le transporteur, FedEx compris, qui peut y ajouter ses propres frais de dédouanement, sans lien avec ce que perçoit la douane.
- **Suisse** : selon l'OFDF, dans le trafic de courrier rapide, c'est le transporteur qui dédouane, et lui seul peut t'aider à rechercher ton envoi. Il perçoit des frais de dédouanement pour ce travail ; pour leur montant, renseigne-toi auprès de lui.

Plus d'infos dans [colis bloqué en douane](guide:customs).

## Colis FedEx en retard, perdu ou livré mais pas reçu

Si le suivi ne bouge plus, lis [suivi de colis qui ne bouge plus](guide:tracking-not-updating).

Si tu as acheté dans une boutique, c'est à elle de te faire parvenir le colis, même quand FedEx le transporte : dans l'UE, selon Your Europe, le commerçant répond des dommages subis par la marchandise de l'expédition jusqu'à la réception.

En France, selon Service-Public.fr, quand la livraison passe par le transporteur proposé par le vendeur, le vendeur est seul responsable : la perte ou l'avarie n'est à ta charge qu'une fois le colis physiquement entre tes mains. Sans date convenue, il doit livrer au plus tard 30 jours après la commande. Passé ce délai, demande-lui de livrer dans un délai supplémentaire raisonnable ; s'il ne livre toujours pas, tu peux annuler la commande, et il doit te rembourser sous 14 jours.

**Livré mais pas reçu ?** Le scan de livraison de FedEx nomme la personne qui a signé, et Peek ne le garde pas. Vérifie sur fedex.com, puis contacte la boutique. Plus de pistes dans [colis livré mais non reçu](guide:delivered-not-received) et [colis perdu](guide:lost-parcel).

## Suivre un colis FedEx avec Peek

[Peek](/) reconnaît les numéros FedEx à 12 et 15 chiffres quand leur clé de contrôle est bonne. D'autres transporteurs partagent ces formes, alors le colis est souvent nommé par un service de suivi, ou après une courte attente. Un numéro dont la clé est fausse n'est pas proposé à FedEx : choisis le transporteur avec « Changer de transporteur ». Les liens fedex.com avec `trknbr=` ou `tracknumbers=` fonctionnent aussi. Peek suit les numéros FedEx à 13 chiffres des colis nationaux en Pologne sous le nom FedEx Poland.

Pour FedEx, il ouvre la page de suivi de fedex.com dans un navigateur, sur son propre serveur. Quand FedEx refuse, ou exige une vérification du destinataire, des services de suivi prennent le relais.

Tu vois le statut, chaque scan sur une carte avec son lieu et son heure exacte, le jour de livraison prévu, l'heure de livraison et, tant que le colis attend, le nom et l'adresse du site FedEx qui le garde, si FedEx les donne. Le suivi est vérifié toutes les 10 minutes en journée, toutes les 5 minutes une fois le colis en livraison, toutes les une à deux heures la nuit, et tu peux recevoir des notifications : « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ». Peek ne garde ni ton nom ni ton adresse, ni l'expéditeur, la signature, le poids ou les dimensions. Les scans s'affichent dans l'anglais de FedEx, sauf trois : la livraison, dont la ligne nomme la personne qui a signé et qui s'affiche donc « Livré », ainsi que `Ready for pickup` et `Shipment exception`, que Peek affiche avec ses propres mots. Pour changer une livraison, payer la douane ou réclamer, adresse-toi à FedEx ou à l'expéditeur.

## Questions sur le suivi FedEx

### Que veut dire « Delivery exception » chez FedEx ?

Peek range ce scan de FedEx parmi les livraisons manquées et affiche « Tentative de livraison ». La raison peut apparaître dans un scan comme `Customer not available or business closed` (personne sur place ou commerce fermé).

### Pourquoi le suivi FedEx n'affiche que « Shipment information sent to FedEx » ?

Ce premier scan veut seulement dire que l'expéditeur a transmis à FedEx les données de l'envoi. Le suivi avance quand FedEx a le colis : `Picked up` ou `Tendered at FedEx location`.

### Un SMS de FedEx, c'est une arnaque ?

Ça peut l'être. Cybermalveillance.gouv.fr signale de faux SMS et e-mails au nom de transporteurs, qui annoncent un problème de livraison ou des frais à payer (frais de port, TVA, frais de douane) et renvoient vers un faux site qui demande ta carte bancaire. Ne clique pas sur le lien : vérifie le vrai suivi toi-même, sur fedex.com ou dans Peek, ou contacte directement le transporteur. En France, signale le SMS au 33700 (gratuit). Plus d'infos dans [arnaque colis par SMS](guide:delivery-scam-texts).

:::sources
- [Douane : Droit de douane forfaitaire de 3 euros sur les ventes à distance de biens importés](https://www.douane.gouv.fr/fiche/droit-de-douane-forfaitaire-de-3-euros-sur-les-ventes-distance-de-biens-importes) – 3 € par article jusqu'à 150 €, en vigueur depuis le 1er juillet 2026
- [Douane : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – taxes demandées à la réception par le transporteur (FedEx cité), ses frais de dédouanement
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – 3 € par article dans l'UE, perçu par le vendeur ou le transporteur, responsabilité du commerçant jusqu'à la réception
- [Service-Public.fr : Achat à distance : livraison du bien ou exécution de la prestation](https://www.service-public.gouv.fr/particuliers/vosdroits/F10037) – vendeur seul responsable, transfert des risques, 30 jours, délai supplémentaire, annulation, remboursement sous 14 jours
- [OFDF : Frais de dédouanement et suivi des colis](https://www.bazg.admin.ch/fr/frais-dedouanement-et-suivi-des-colis) – dédouanement par le transporteur, recherche de l'envoi, frais de dédouanement (Suisse)
- [Cybermalveillance.gouv.fr : L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – faux SMS au nom de transporteurs, vérifier le vrai suivi, 33700
- [Universal Parcel Scraper : FedEx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/fedex) – formes de numéro, clés de contrôle, scans de FedEx, ce que Peek lit chez FedEx
:::
