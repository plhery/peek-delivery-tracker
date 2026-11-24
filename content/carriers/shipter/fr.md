---
title: Suivi Shipter : numéros H3 de Corée du Sud et statuts
description: Suis un colis aérien international Shipter (H3 Networks) depuis la Corée du Sud : formats H3...KR, relais locaux, statuts et liens de suivi.
slug: suivi-shipter
published: 2026-11-24
updated: 2026-11-24
---

Shipter, exploité par **H3 NETWORKS Co., Ltd.** en Corée du Sud, expédie des colis B2B et B2C depuis la Corée vers plus de **100 pays** via plus de 40 filières logistiques, sur un réseau aérien reliant plus de 25 aéroports chaque semaine ([shipter.kr](https://www.shipter.kr/)). Son offre B2B couvre le transport, la douane et l’entreposage ; son offre B2C couvre la livraison à l’étranger, le dédouanement et le dernier kilomètre. Amazon Korea l’a retenu comme partenaire logistique officiel dans son réseau SPN (*Service Provider Network*), l’entreprise est enregistrée comme transporteur sur eBay, Kogan et Allegro, son suivi est relié à AfterShip et 17TRACK et elle opère aussi comme partenaire d’envois d’influenceurs.

Tu peux vérifier un colis sur [shipter.kr/tracking](https://www.shipter.kr/tracking) ou coller ton numéro de suivi `H3...KR` dans le champ ci-dessus.

## Format de numéro de suivi Shipter

Les numéros de suivi Shipter commencent par `H3` et se terminent par `KR`, avec deux lettres et cinq ou six chiffres entre les deux :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `H3AU482917KR` | 12 caractères : `H3`, deux lettres, six chiffres et `KR`. Peek interroge uniquement Shipter. |
| `H3FR48291KR` | 11 caractères : `H3`, deux lettres, cinq chiffres et `KR`. Peek interroge uniquement Shipter. |

Un lien de suivi `shipter.kr/tracking/<numéro>` ou `shiptertracking.com/trackingResult/<numéro>` associe immédiatement le colis à Shipter. Pour les colis expédiés de Corée du Sud par Korea Post, consulte [Korea Post](carrier:korea-post) ; pour d’autres formats, vois le guide des [formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi Shipter

L’historique de suivi Shipter associe ses propres étapes d’exportation en Corée du Sud et de fret aérien international aux scans relayés par le transporteur local du dernier kilomètre dans ton pays, en anglais.

| Libellé Shipter / partenaire | Ce que cela signifie |
| --- | --- |
| `The shipment information has been received by shipter, but the package has not been picked up yet.` | Pré-annonce reçue du vendeur coréen ; Shipter n’a pas encore scanné le colis physique. |
| `The parcel has been scanned by shipter and ready to be shipped.` | Shipter a réceptionné et scanné le colis dans son centre en Corée. |
| `The parcel has been shipped.` / `The flight has been departed.` / `Depart origin airport` | Expédié de Corée du Sud à bord d’un vol cargo international. |
| `Arrived at destination airport` / `Collected from airport terminal` | Atterri dans le pays de destination et récupéré au terminal cargo de l’aéroport. |
| `Import customs cleared` | La douane du pays de destination a libéré le colis pour la suite de l’acheminement local. |
| `Arrived at destination` / `In transit` | Intégré au tri local avant les scans du transporteur de destination. |
| `Parcel received in processing centre (...)` / `Parcel depart from ... depot` / `The item is ready for shipment...` | Traité et acheminé au sein du réseau local du transporteur de destination. |
| `Your parcel has been received at ... depot..` / `Your parcel is currently in transit to ...` | Relayé depuis le partenaire local du pays de destination, avec son dépôt et sa ville. |
| `Out for delivery` / `Your parcel is with your local courier and ready for delivery..` | En tournée avec le chauffeur local aujourd’hui. |
| `Your parcel was delivered` / `Delivered.` | Livré à l’adresse du destinataire. |

Deux avis relayés par le partenaire local, `Your parcel will be entrusted to last mile .it is being prepared by your sender` et `Shipping information received`, apparaissent après le scan de prise en charge de Shipter. Ils ne signifient pas que le colis est revenu en arrière.

## Transit aérien international, douane et relais locaux

Shipter expédie les colis par avion depuis la Corée du Sud puis les remet à un transporteur local dans le pays de destination :

- **Vol et aéroport :** après `Depart origin airport`, Shipter affiche `Arrived at destination airport` à l’atterrissage, `Collected from airport terminal` lorsque le colis quitte le terminal cargo, puis `Import customs cleared` une fois le dédouanement import achevé. Consulte le [guide sur la douane](guide:customs) et le guide sur le [suivi bloqué](guide:tracking-not-updating).
- **Livraison locale du dernier kilomètre :** un transporteur dans le pays de destination assure la livraison finale, et Shipter relaie ses scans de dépôt, `Out for delivery` et de livraison dans son propre historique.
- **Pas de date de livraison estimée :** l’API publique de suivi Shipter ne publie pas de date de livraison estimée ; suis l’avancement en guettant `Import customs cleared`, l’arrivée au dépôt local puis `Out for delivery`.

## Suivre un colis Shipter avec Peek

Colle ton numéro `H3...KR`, ou un lien `shipter.kr/tracking/...` ou `shiptertracking.com/trackingResult/...`, dans Peek. Pour un numéro de 11 ou 12 caractères, Peek interroge uniquement Shipter. Il affiche « Shipter a ce colis. » dès que Shipter répond. Si Peek attribue un autre transporteur, appuie sur « Changer » lors de l’ajout ou « Changer de transporteur » sur la fiche du colis, puis choisis Shipter.

Peek lit le suivi Shipter via l’[adaptateur Shipter open source de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter) et réessaie après une erreur réseau. Il affiche le libellé anglais de chaque scan avec le dépôt ou le lieu nommé, ainsi que l’heure de livraison. Shipter transmet ses horaires avec un décalage GMT et Peek les affiche à l’heure de ton appareil ; une heure sans décalage reste affichée telle que Shipter la fournit.

Peek traduit les scans Shipter en étapes claires : « Étiquette créée » pour la pré-annonce avant enlèvement, « Pris en charge » dès que Shipter scanne le colis en Corée, « En transit » pour le départ du vol, l’arrivée à l’aéroport, le dédouanement et les transferts locaux, « En livraison » quand le livreur local entame sa tournée, et « Livré » à la fin. Les avis tardifs du partenaire local (`Shipping information received` et `Your parcel will be entrusted to last mile...`) restent dans l’historique sans faire reculer le colis d’étape.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Shipter. » Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Shipter. »

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Shipter affiche `Out for delivery` ou `Your parcel is with your local courier and ready for delivery..`, toutes les 2 minutes à partir de 6 h sur l’horloge du scan le plus récent, tant que le suivi Shipter répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi de Shipter ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Peek peut t’avertir pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Contacter Shipter

Shipter travaille pour les entreprises qui expédient avec lui :

- **Site officiel et demandes de service :** [shipter.kr](https://www.shipter.kr/) (H3 NETWORKS Co., Ltd. ; *이용문의* pour les demandes de service)
- **Suivi officiel en ligne :** [shipter.kr/tracking](https://www.shipter.kr/tracking)
- **Contacter d’abord le vendeur :** chaque entreprise cliente dispose d’un responsable service client dédié chez Shipter, qui gère les incidents, retours et réclamations avec ses clients. Si ton colis est retardé, endommagé ou manquant, contacte la boutique expéditrice avec ton numéro `H3...KR`.

## Questions fréquentes

### À quoi ressemble un numéro de suivi Shipter ?

Il commence par `H3` et finit par `KR`, avec deux lettres et cinq ou six chiffres au milieu : 11 ou 12 caractères, par exemple `H3AU482917KR`. Le site de Shipter ne précise pas la signification des deux lettres.

### Pourquoi « Shipping information received » apparaît-il des jours après l’expédition du colis ?

C’est un avis du partenaire local dans le pays de destination, relayé par Shipter après son propre scan de prise en charge. Cela ne fait pas reculer le colis : Peek conserve l’étape du scan précédent.

### Pourquoi mon colis Shipter affiche-t-il des mises à jour d’un autre transporteur ?

Shipter gère l’exportation depuis la Corée du Sud et le fret aérien international, puis confie le colis à un transporteur local du dernier kilomètre dans le pays de destination et relaie ses scans de dépôt et de livraison dans ton suivi Shipter.

### Shipter fournit-il une date de livraison estimée ?

Non. Le flux public de Shipter affiche les événements horodatés d’exportation, de vol, de douane et du transporteur local avec décalage GMT, sans date de livraison estimée.

:::sources
- [Shipter (H3 NETWORKS Co., Ltd.)](https://www.shipter.kr/)
- [Shipter Tracking](https://www.shipter.kr/tracking)
- [universal-parcel-scraper Shipter adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter)
:::
