---
title: Suivi Spee-Dee Delivery : numéros SP, États et aide
description: Suis un colis Spee-Dee Delivery Service dans le Midwest américain : codes-barres SP de 18 et 20 caractères, réutilisation, zone desservie et 800-862-5578.
slug: suivi-spee-dee
published: 2026-11-24
updated: 2026-11-24
---

Spee-Dee Delivery (*Spee-Dee Delivery Service, Inc.*) est une entreprise familiale de livraison de deuxième génération fondée en 1978 par Don Weeres comme service de livraison à la demande. Elle se présente comme le leader incontesté de la livraison du dernier kilomètre dans le Midwest américain avec un réseau du lendemain, et propose également du fret LTL, de l’entreposage, des points de dépôt sans rendez-vous et EZR Returns ([speedeedelivery.com](https://speedeedelivery.com/)).

Tu peux suivre un envoi sur la page [Track a Shipment de Spee-Dee](https://speedeedelivery.com/track-a-shipment/) ou coller ton code-barres `SP` dans le champ ci-dessus.

## Formats de numéro de suivi Spee-Dee

Peek connaît les codes-barres Spee-Dee comme `SP` suivi de 16 ou 18 chiffres :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `SP4829173605148291` | `SP` + 16 chiffres (18 caractères au total) — code-barres Spee-Dee standard ; note que Spee-Dee réutilise les numéros à 16 chiffres au fil du temps. |
| `SP482917360514829173` | `SP` + 18 chiffres (20 caractères au total) — format long de code-barres Spee-Dee. |

Les deux formats `SP` identifient Spee-Dee avec une confiance élevée et n’entrent pas en collision avec les grands transporteurs américains tels que [UPS](carrier:ups), [FedEx](carrier:fedex) ou [USPS](carrier:usps). Si tu as un lien `speedeedelivery.com/...?tracking=...`, Peek extrait directement le numéro `SP`. Pour d’autres formats régionaux et nationaux américains, consulte le guide des [formats de numéros de suivi](guide:tracking-number-formats).

## Zone desservie par Spee-Dee dans le Midwest

Selon les [cartes de zone de service Spee-Dee](https://speedeedelivery.com/services/service-area-maps/), Spee-Dee assure une livraison routière régulière dans **l’intégralité** de six États du Midwest ainsi que dans certaines villes et codes postaux (*ZIP Codes*) de quatre États voisins :

- **Couverture intégrale de l’État :** Illinois, Iowa, Minnesota, Dakota du Nord, Dakota du Sud et Wisconsin.
- **Villes et codes postaux sélectionnés :** Kansas (y compris les zones étendues `660` et `666`), Michigan, Missouri (y compris les zones étendues `634–635`, `644–646` et `650–653`) et Nebraska.

Pour les délais de transit, la page *Service Area Maps* affiche une carte *Days in Transit* par code postal d’expédition, et son fichier *Zip Transit File* indique les délais pour chaque code postal desservi par Spee-Dee.

## Signification des statuts de suivi Spee-Dee

Le tableau d’avancement de Spee-Dee comporte trois colonnes — `Date / Time`, `Location` et `Activity` — du plus récent au plus ancien, avec un encart récapitulatif en haut indiquant `Status:`, `Delivered on:`, `Signed by:` et `Delivered to:` une fois la livraison terminée.

| Activité Spee-Dee | Ce que cela signifie |
| --- | --- |
| `ARRIVAL SCAN` | Scanné dans un terminal ou centre de tri Spee-Dee sur le réseau routier du Midwest. |
| `OUT FOR DELIVERY` *(souvent suivi d’un numéro de tournée entre parenthèses)* | Chargé dans un camion de livraison local Spee-Dee pour livraison aujourd’hui à domicile ou en entreprise. |
| `Delivered` | Livraison effectuée ; le récapitulatif sur la page de Spee-Dee indique aussi l’heure de livraison, l’adresse et la signature du destinataire lorsqu’elle a été recueillie. |
| `No packages were found matching the barcode supplied.` | Le code-barres n’a pas encore été scanné dans le système Spee-Dee ou comporte une faute de frappe. |

### Codes-barres de 16 chiffres réutilisés

Spee-Dee réutilise d’anciens codes-barres `SP` à 16 chiffres sur de nouveaux envois. Sur la page brute de Spee-Dee, des lignes d’un colis livré précédemment peuvent encore apparaître sous les scans de ton colis actuel. Quand tu consultes un code-barres réutilisé sur le site de Spee-Dee, regarde les dates en haut du tableau et arrête ta lecture dès que tu rencontres un ancien `Delivered` issu d’une expédition antérieure.

## Retards, scans absents et réclamations

Quelques repères aident lorsque le suivi semble étrange :

- **Aucun scan pour le moment :** si la page de Spee-Dee répond `No packages were found matching the barcode supplied.`, Spee-Dee n’a pas encore scanné le colis ou le code-barres comporte une erreur. Vérifie le code, puis demande au vendeur si le colis a bien été remis à Spee-Dee, ou lis le guide sur le [suivi bloqué](guide:tracking-not-updating).
- **Limitation en cas d’actualisations répétées :** le serveur de suivi de Spee-Dee limite le nombre de recherches par minute et renvoie temporairement une erreur HTTP 503 si tu actualises trop souvent. Attendre une minute ou laisser Peek vérifier automatiquement évite ce blocage.
- **Réclamations pour non-livraison ou avarie :** le [formulaire de service client Spee-Dee](https://speedeedelivery.com/customer-service/) propose les rubriques **Claims – Non-Delivery** et **Claims – Damage** et demande d’indiquer **l’adresse complète de livraison** pour toute demande de suivi. Voir aussi le guide [livré mais non reçu](guide:delivered-not-received) si un colis indique livré sans être arrivé.

## Suivre un colis Spee-Dee avec Peek

Colle n’importe quel code-barres Spee-Dee de 18 caractères (`SP` + 16 chiffres) ou 20 caractères (`SP` + 18 chiffres), ou un lien `speedeedelivery.com`, dans Peek : Peek reconnaît aussitôt Spee-Dee. Si tu dois changer de transporteur, utilise « Changer » lors de l’ajout ou « Changer de transporteur » sur la fiche du colis.

Peek lit directement la page d’avancement de Spee-Dee via l’[adaptateur Spee-Dee open source de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee), et via un réseau distant si l’hôte de Spee-Dee refuse une connexion directe. Pour un **code-barres réutilisé**, il ne conserve que les lignes postérieures à la livraison précédente datant d’au moins un jour. Il supprime les commentaires HTML de débogage et le numéro de tournée après `OUT FOR DELIVERY`, et relève la ville ou le terminal ainsi que l’heure de chaque scan. Spee-Dee indique des heures locales sans décalage par rapport à UTC : Peek date chaque scan sur le fuseau de son lieu (heure de Chicago pour St. Cloud, Fargo, Milwaukee ou Sioux Falls ; heure de Détroit pour Détroit), un scan sans lieu sur le fuseau du scan voisin, sinon à l’heure de New York, puis affiche chaque horaire à l’heure de ton appareil. Le suivi Spee-Dee ne donne ni date de livraison estimée ni poids du colis.

Peek classe `ARRIVAL SCAN` en « En transit », `OUT FOR DELIVERY` en « En livraison » et `Delivered` en « Livré », et interprète toute autre activité selon ses règles de statut partagées.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Spee-Dee affiche `OUT FOR DELIVERY`, toutes les 2 minutes à partir de 6 h sur l’horloge du scan le plus récent, ou toutes les 5 minutes si la vérification précédente a dû ouvrir un navigateur, tant que le suivi Spee-Dee répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque Spee-Dee ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Spee-Dee. », avec un lien « Demande de l’aide à Spee-Dee » vers la page Customer Service de Spee-Dee. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Spee-Dee. »

Dans les préférences de notification de Peek, choisis « Chaque scan », « Étapes importantes » ou « Livraison seulement » pour contrôler tes alertes.

## Contacter Spee-Dee Delivery

Spee-Dee précise sur sa [page Customer Service](https://speedeedelivery.com/customer-service/) que les appelants parlent directement à une personne plutôt qu’à un serveur vocal automatisé :

- **Service client sans frais :** `800-862-5578`
- **Téléphone local :** `320-251-6697`
- **Fax :** `320-251-1846` (fax LTL : `320-240-7995`)
- **Formulaire de contact et réclamations en ligne :** [Service client Spee-Dee](https://speedeedelivery.com/customer-service/) (indique ton code-barres `SP` et l’adresse complète de livraison pour toute question de suivi)
- **Adresse physique :** Spee-Dee Delivery Service Inc., 4101 Clearwater Road, St. Cloud, MN 56301
- **Adresse postale :** P.O. Box 1417, St. Cloud, MN 56302
- **Présentation et suivi :** [À propos de Spee-Dee](https://speedeedelivery.com/about-us/) et [Track a Shipment](https://speedeedelivery.com/track-a-shipment/)

## Questions fréquentes

### Pourquoi ma page de suivi Spee-Dee affiche-t-elle une livraison vieille de plusieurs mois ?

Spee-Dee réutilise les codes-barres `SP` à 16 chiffres au fil du temps, de sorte que sa page web brute peut lister une ancienne expédition terminée sous les scans de ton colis actuel. Peek détecte automatiquement cette coupure et masque les lignes issues des livraisons antérieures.

### Quels États américains Spee-Dee Delivery dessert-il ?

Spee-Dee couvre l’intégralité de l’Illinois, de l’Iowa, du Minnesota, du Dakota du Nord, du Dakota du Sud et du Wisconsin, ainsi que certaines villes et codes postaux du Kansas, du Michigan, du Missouri et du Nebraska.

### Spee-Dee Delivery affiche-t-il une date de livraison estimée sur sa page de suivi ?

Non. La page de suivi Spee-Dee montre les scans d’arrivée en terminal, `OUT FOR DELIVERY` et les détails de livraison finale, sans date de livraison estimée. Tu peux consulter les délais routiers habituels par code postal sur les [cartes de zone de service Spee-Dee](https://speedeedelivery.com/services/service-area-maps/).

### Que faut-il joindre pour contacter Spee-Dee à propos d’un colis ?

Prépare ton numéro `SP` complet de 18 ou 20 caractères ainsi que l’adresse complète de livraison — le formulaire de service client Spee-Dee demande explicitement l’adresse complète de livraison pour les demandes de suivi.

:::sources
- [Spee-Dee Delivery](https://speedeedelivery.com/)
- [Spee-Dee — About Us](https://speedeedelivery.com/about-us/)
- [Spee-Dee — Track a Shipment](https://speedeedelivery.com/track-a-shipment/)
- [Spee-Dee — Service Area Maps](https://speedeedelivery.com/services/service-area-maps/)
- [Spee-Dee — Customer Service](https://speedeedelivery.com/customer-service/)
- [universal-parcel-scraper Spee-Dee adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee)
:::
