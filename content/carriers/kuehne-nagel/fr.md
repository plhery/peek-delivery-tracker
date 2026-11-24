---
title: Suivi Kuehne+Nagel : numéros, statuts, douane et livraison
description: Suis un envoi route, air ou mer Kuehne+Nagel par référence ou lien myKN : formats de numéros, jalons, étapes douanières et options de contact.
slug: suivi-kuehne-nagel
published: 2026-11-24
updated: 2026-11-24
---

Kuehne+Nagel est l’un des principaux prestataires logistiques mondiaux, avec près de 92 000 collaborateurs répartis sur presque 1 300 sites dans près de 100 pays. Le groupe achemine du fret maritime, aérien et routier, et son [suivi public myKN](https://mykn.kuehne-nagel.com/public-tracking/) permet de consulter les envois de tous les modes.

Le suivi public sur myKN couvre les expéditions lancées au cours des 90 derniers jours. Comme Kuehne+Nagel transporte des marchandises via des agences routières, aéroports, stations de groupage conteneur (CFS), terminaux ferroviaires et ports maritimes, un même envoi peut afficher à la fois des jalons physiques d’acheminement et des avis distincts de réservation, de douane ou de documents.

## Formats des numéros de suivi Kuehne+Nagel

Peek accepte les références Kuehne+Nagel de 6 à 35 lettres et chiffres contenant au moins un chiffre, et supprime les espaces et tirets. myKN recherche les numéros de suivi propres à Kuehne+Nagel, lettres de voiture et réservations dans le même champ que les références clients. Peek reconnaît trois formats :

- **`RWM` suivi de 9 chiffres** (12 caractères au total, comme `RWM482917360`) — un numéro de suivi d’envoi routier Kuehne+Nagel, que Peek reconnaît comme appartenant exclusivement à Kuehne+Nagel.
- **`RAPI` suivi de 8 chiffres** (12 caractères au total, comme `RAPI48291736`) — un numéro de suivi Kuehne+Nagel, que Peek reconnaît aussi comme appartenant exclusivement à Kuehne+Nagel.
- **2 chiffres, `A` et 9 chiffres** (12 caractères au total, comme `26A482917360`) — un numéro de suivi routier. Ce format correspond également à [Australia Post](carrier:australia-post) et [Colis Privé](carrier:colis-prive) : Peek interroge donc Australia Post, Colis Privé et Kuehne+Nagel à tour de rôle (Kuehne+Nagel en deuxième position en Suisse et en Allemagne).

Pour le fret aérien et maritime, les expéditeurs peuvent aussi transmettre une lettre de voiture Kuehne+Nagel, un connaissement interne (*house bill of lading*) ou une référence client hors de ces trois schémas. Si tu colles un lien de suivi `mykn.kuehne-nagel.com/public-tracking/shipments?query=...`, Peek en extrait la référence et l’envoie directement à Kuehne+Nagel. Pour une vue d’ensemble des numéros sur les réseaux fret et colis, consulte le [guide des formats de numéros de suivi](guide:tracking-number-formats).

## Comment fonctionnent les jalons Kuehne+Nagel

Le suivi public de Kuehne+Nagel sépare un envoi en deux flux : les étapes physiques sur l’itinéraire (origine, terminal de départ, hub de transit, passerelle, terminal d’arrivée et destination) et les avis d’information transport (réservations, formalités douanières et justificatifs de livraison).

| Libellé Kuehne+Nagel | Ce que cela signifie |
| --- | --- |
| Booking submission / Booking confirmation | L’ordre est enregistré dans le système Kuehne+Nagel avant tout mouvement de marchandise ; une modification tardive de réservation ne fait jamais reculer un envoi déjà en route. |
| Ready for pickup / Arrival (at origin) | La marchandise est prête chez l’expéditeur ou le camion de collecte est arrivé, avant confirmation du chargement. |
| Pickup | Kuehne+Nagel a pris en charge le fret chez l’expéditeur. |
| Arrival / Departure / Load on vehicle / Load on vessel | Scans physiques en terminal, quai de transit, rampe ferroviaire, aéroport ou port le long du trajet principal. |
| Customs entry start / Customs duties payment / Entry documents required | Le dédouanement export ou import est en cours, ou les autorités ont demandé des documents, le paiement de droits ou un contrôle d’agence (tel que FDA ou PGA). |
| Customs entry release / Export customs entry release / Import customs entry release | Les douanes ou l’agence frontalière compétente ont libéré la marchandise pour la suite du trajet. |
| In delivery | Le véhicule routier est en tournée pour la livraison finale ou est arrivé sur le lieu de livraison. |
| Delivery / Freight collection / Proof of delivery available | Le fret a été livré au destinataire ou retiré au terminal ; `Delivery with discrepancies` marque toujours une livraison tout en signalant un manquant ou un dommage. |
| Delivery attempt failed / Delivery not possible | Une tentative de livraison a échoué, par exemple en cas de refus par le destinataire. |
| Shipment on hold / Delivery instructions pending / Missing/incomplete document | L’envoi est bloqué dans une agence ou en douane dans l’attente de documents manquants, d’un paiement ou d’instructions de livraison. |

Quelques libellés méritent une attention particulière :

- **Available at CY / Available at CFS / Ready for collection / Available at rail terminal** signifient que l’envoi est arrivé sur un parc à conteneurs maritime, une station CFS, un aéroport ou un terminal ferroviaire et est prêt pour remise ou enlèvement. Kuehne+Nagel ou un partenaire peut encore assurer la livraison routière finale : ces scans restent donc en transit plutôt que de marquer la livraison finale.
- **Freight handover** signifie que Kuehne+Nagel a remis la marchandise à un commissionnaire en douane ou à un transporteur suivant, et non au destinataire final.
- **Avis documentaires et administratifs** tels que `HAWB available`, `MAWB available`, `Transport document available`, `VGM closure`, `Delivery appointment submission` et `Customs billing invoice available` consignent des étapes administratives et ne modifient pas l’étape physique de l’envoi.
- **Empty container gate-in et départ du véhicule à destination** interviennent après la livraison complète et maintiennent l’envoi à l’étape livrée.

## Livraison, douane et retards

`Delivery appointment submission` enregistre une date de livraison convenue pour plus tard. Ce n’est pas un scan de tournée : l’envoi ne passe en livraison que lorsque `In delivery` s’affiche, ou lors de l’`Arrival` du camion à l’adresse de livraison.

Si le suivi indique `Awaiting consignee's feedback`, `Awaiting consignee's payment` ou `Entry documents required`, la douane attend une réponse, un paiement ou des pièces du destinataire. `Unable to file customs entry - missing details or documentation` signifie que la déclaration en douane ne peut pas être déposée tant qu’ils ne sont pas reçus. Consulte le [guide douane](guide:customs) pour comprendre le dédouanement et les frais d’importation.

Les dates prévisionnelles affichées sur myKN sont des estimations et non des scans réalisés. Pour des conseils généraux sur un suivi bloqué, lis [quand le suivi ne se met plus à jour](guide:tracking-not-updating).

## Suivre Kuehne+Nagel avec Peek

Lorsque tu colles un numéro `RWM` ou `RAPI`, ou un lien `mykn.kuehne-nagel.com/public-tracking/shipments?query=...`, dans Peek, Peek reconnaît Kuehne+Nagel immédiatement. Pour un numéro `26A...`, Peek interroge à tour de rôle les transporteurs ci-dessus et affiche « Kuehne+Nagel a ce colis. » dès que Kuehne+Nagel répond. Pour une lettre de voiture ou une référence client d’un autre format, choisis Kuehne+Nagel via **Changer** à l’ajout du colis ou **Changer de transporteur** sur l’écran de suivi.

Peek lit directement la recherche publique d’envois, les jalons d’itinéraire et les avis transport de Kuehne+Nagel via l’[adaptateur Kuehne+Nagel open source de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel). Il n’enregistre que les jalons effectivement réalisés (en ignorant les dates prévisionnelles planifiées) et récupère le poids total en kilogrammes ainsi que le pays de destination lorsqu’ils sont renvoyés. Lorsque la recherche trouve l’envoi via une référence client, Peek affiche le numéro de suivi propre de Kuehne+Nagel à côté du tien sous « Kuehne+Nagel le suit sous le numéro » ; lorsqu’une référence correspond à plusieurs envois, Peek ne peut pas déterminer lequel est le tien. L’heure d’un scan inclut son décalage UTC lorsque le système de scan le connaît, et Peek conserve cet instant exact. Sinon, Peek date le scan sur le fuseau horaire de son lieu (heure de Berlin pour Hambourg, heure de Zurich pour Bâle), un scan sans lieu sur celui du scan voisin, sinon sur l’heure de Zurich, et affiche chaque scan à l’heure de ton appareil.

Peek traduit les jalons Kuehne+Nagel en étapes claires : **Étiquette créée** pour les réservations et avis prêt à l’enlèvement, **Pris en charge** pour l’enlèvement, **En transit** pour les mouvements en hub, port, rail et remises intermédiaires, **À la douane** pour le dédouanement export/import et les blocages d’agences, **En livraison** pour `In delivery` et l’arrivée du camion à destination, **Tentative de livraison** pour les livraisons échouées, **Problème signalé** pour les blocages, enlèvements échoués, annulations et documents manquants, et **Livré** pour la livraison, l’enlèvement du fret ou la preuve de livraison. Les avis administratifs restent dans la chronologie sans faire reculer l’étape en cours, et un dossier douanier post-livraison ne réinitialise jamais un envoi déjà livré.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Kuehne+Nagel affiche `In delivery` ou l’`Arrival` du camion à l’adresse de livraison, toutes les 2 minutes à partir de 6 h sur l’horloge du dernier scan tant que le suivi Kuehne+Nagel répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, Peek passe à une vérification par heure quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque Kuehne+Nagel ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Kuehne+Nagel. » Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Kuehne+Nagel. »

Tu peux choisir les alertes de notification pour **Chaque scan**, **Étapes importantes** ou **Livraison seulement** dans les réglages du colis.

## Contacter Kuehne+Nagel

Le site de Kuehne+Nagel propose un annuaire d’agences et un formulaire de contact :

- **Site mondial et portail de suivi :** [kuehne-nagel.com](https://www.kuehne-nagel.com/) et [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- **Annuaire des agences locales :** [Kuehne+Nagel locations worldwide](https://www.kuehne-nagel.com/company/locations) pour trouver le bureau national ou local le plus proche
- **Formulaire de demande en ligne :** [Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert) pour recevoir une réponse par e-mail
- **Autres réseaux de fret :** si ta commande a été répartie entre plusieurs transporteurs, compare avec [DHL Freight](carrier:dhl-freight) et [DSV](carrier:dsv).

## Questions fréquentes

### Pourquoi mon numéro de suivi Kuehne+Nagel n’affiche-t-il aucun résultat ?

Le suivi public de Kuehne+Nagel ne couvre que les expéditions lancées dans les 90 derniers jours. Vérifie qu’aucun caractère ne manque et, si la réservation vient d’être créée, attends le premier jalon réalisé. Si une référence correspond à plusieurs envois à la fois, demande à l’expéditeur le numéro `RWM`, `RAPI` ou autre identifiant propre à Kuehne+Nagel.

### Que signifient « Service complete » ou « Freight handover » sur le suivi Kuehne+Nagel ?

`Freight handover` signifie que Kuehne+Nagel a remis la marchandise à un commissionnaire en douane ou à un transporteur suivant : l’envoi est donc toujours en route vers toi. `Service complete` marque la fin du tronçon confié à Kuehne+Nagel — qui peut être une remise en port ou aéroport plutôt qu’une livraison à ta porte —, vérifie donc si un numéro de transporteur du dernier kilomètre distinct a été émis.

### Pourquoi certaines dates affichées sur myKN n’apparaissent-elles pas dans l’historique Peek ?

La vue publique d’itinéraire de Kuehne+Nagel affiche des dates prévisionnelles aux côtés des scans réalisés ainsi que des avis futurs non datés. Peek n’enregistre que les jalons que Kuehne+Nagel marque comme effectivement réalisés, afin que des objectifs planifiés n’apparaissent pas comme des mouvements accomplis.

### Combien de temps le suivi public Kuehne+Nagel reste-t-il disponible ?

Le suivi public sur `mykn.kuehne-nagel.com` couvre les expéditions lancées au cours des 90 derniers jours. Pour consulter un historique plus long, myKN propose une inscription gratuite.

:::sources
- [Site mondial Kuehne+Nagel](https://www.kuehne-nagel.com/)
- [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- [Agences Kuehne+Nagel dans le monde](https://www.kuehne-nagel.com/company/locations)
- [Kuehne+Nagel Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert)
- [adaptateur Kuehne+Nagel de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel)
:::
