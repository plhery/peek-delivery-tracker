---
title: Suivi SEKO Logistics : numéros, OmniParcel et livraison
description: Suis un envoi SEKO Logistics ou SEKO OmniParcel : formats de numéros, codes GX, statuts de suivi, lignes US et UK et délais estimés.
slug: suivi-seko-logistics
published: 2026-11-24
updated: 2026-11-24
---

SEKO Logistics a débuté en 1976 avec un seul bureau à Chicago et compte aujourd’hui plus de 150 bureaux dans plus de 60 pays ainsi que 15 hubs principaux. Ses services couvrent le fret aérien, maritime et routier, le e-commerce, la livraison *White Glove & Specialty Delivery* et la livraison à domicile et du dernier kilomètre (*Home Delivery & Last Mile*). Les envois se suivent sur [sekologistics.com/en/track](https://www.sekologistics.com/en/track/), et les colis e-commerce transfrontaliers sur le [suivi OmniParcel](https://track.omniparcel.com/) de SEKO.

Comme le portail principal de SEKO et OmniParcel fonctionnent sur deux systèmes distincts avec des formats de numéros et des libellés différents, savoir quel format tu détiens aide à lire correctement l’historique.

## Formats de numéros SEKO Logistics et OmniParcel

Les envois SEKO Logistics ne suivent pas un préfixe exclusif unique ; ils se répartissent en deux familles traitées par deux portails différents :

- **Numéros d’expédition SEKO Logistics :** Peek accepte de 8 à 20 lettres et chiffres comprenant une suite d’au moins 7 chiffres. Parmi les formes observées figurent 3 lettres suivies de 7 chiffres (comme `WFY4829173`), 5 lettres suivies de 7 chiffres, ou 9 chiffres seuls (comme `482917360`). Peek ne reconnaît jamais SEKO à partir d’un numéro nu : pour 3 lettres + 7 chiffres il interroge [Australia Post](carrier:australia-post), pour 5 lettres + 7 chiffres [Colis Privé](carrier:colis-prive), et pour 9 chiffres [DPD Ireland](carrier:dpd-ie) (hors Irlande uniquement en dernier recours). Sélectionne SEKO Logistics manuellement ou colle ton lien de suivi.
- **Numéros SEKO OmniParcel (`GX` + 17 ou 18 chiffres) :** les envois e-commerce transfrontaliers suivis sur `track.omniparcel.com` utilisent `GX` suivi de 17 ou 18 chiffres (comme `GX482917360514829173`). La page de suivi principale de SEKO ne reconnaît pas les numéros `GX` ; seul le suivi OmniParcel y répond.

Si tu colles un lien `sekologistics.com/en/track/?trackingNumbers=...` ou `track.omniparcel.com/...`, Peek extrait automatiquement le numéro et l’oriente directement vers SEKO Logistics. Pour en savoir plus sur les différents formats de codes, consulte le guide des [formats de numéros de suivi](guide:tracking-number-formats).

## Statuts de suivi SEKO Logistics et OmniParcel

La page de suivi principale de SEKO formule souvent ses jalons sous la forme `Status Changed to [...]` (parfois avec un tiret final à la place des crochets), tandis qu’OmniParcel détaille le hub international, le vol, la douane et le transporteur local du dernier kilomètre.

| Libellé SEKO / OmniParcel | Ce que cela signifie |
| --- | --- |
| `Shipment Data Received` / `Shipment Created` / `Tracking number allocated & order ready` | Les informations d’expédition sont parvenues à SEKO ou OmniParcel, avant l’enlèvement physique ou l’entrée en entrepôt. |
| `Arrived at PU location` | Le chauffeur chargé de l’enlèvement est arrivé chez l’expéditeur ; le fret n’est pas encore enregistré comme collecté. |
| `Picked Up From Customer` / `At Pickup Terminal` / `Received at Origin Warehouse` | SEKO a collecté l’envoi ou l’a réceptionné au terminal ou à l’entrepôt d’origine. |
| `In Transit` / `In Transit to Delivery Terminal` / `At Transfer Terminal` / `Processed through Export Hub` / `Flight - Freight departed to destination` | En acheminement routier national ou en fret aérien international vers le pays ou la station de livraison. |
| `At Delivery Terminal` / `At Delivery Terminal Delivery Appt Set` / `On Hand Destination Station` / `Delivered To Warehouse` | Arrivé au terminal de destination SEKO ou à l’entrepôt local avant la livraison finale ; `Delivery Appt Set` signifie qu’un rendez-vous de livraison à domicile a été fixé. |
| `Received by Destination Customs` / `Customs held for inspection and clearance` | En cours de traitement ou d’inspection par la douane à destination sur un envoi transfrontalier OmniParcel. |
| `Customs cleared` / `Received by Last Mile Provider` / `Received by carrier` | Dédouané et transféré au hub local de destination ou au transporteur du dernier kilomètre. |
| `Shipment is Out for Delivery` / `Driver on site Delivery Location` / `The order is on the way to the recipient.` | En tournée avec le chauffeur aujourd’hui, ou chauffeur arrivé sur place. |
| `Awaiting collection` / `Customer to collect from carrier` / `Delivered to locker/collection point` | En attente de retrait par le destinataire dans un dépôt, un point relais ou une consigne. |
| `Attempted delivery` / `Attempted Delivery - Refused by customer` | Une tentative de livraison n’a pas abouti. |
| `Temporary Origin Hold` / `Temporary Destination Hold` | Une retenue temporaire met l’envoi en pause au terminal d’origine ou de destination. |
| `Shipment Delivered` / `Delivered - Authority to Leave / Safe Drop` | Livré à l’adresse du destinataire (ou déposé en lieu sûr avec autorisation). |
| `Return to sender` | En cours de retour vers l’expéditeur. |

Deux libellés du portail principal SEKO méritent attention :

- **`Delivered To Warehouse`** signifie que le fret est arrivé à l’entrepôt de destination de SEKO, et non chez toi — la livraison n’a pas encore eu lieu.
- **`POD Image Attached` et `POD IMAGED`** enregistrent le dépôt du justificatif de livraison (parfois plusieurs jours après la livraison) et **`Return Data Received`** signale qu’un retour a été saisi sans prouver que la marchandise est repartie. Aucun de ces libellés ne modifie l’étape physique de livraison.

## Livraison à domicile, douane transfrontalière et retards

Le site de SEKO propose *White Glove & Specialty Delivery* et *Home Delivery & Last Mile*, ainsi qu’une logistique sécurisée pour l’électronique et le matériel informatique, aux côtés des commandes transfrontalières OmniParcel.

- **Rendez-vous de livraison à domicile :** `At Delivery Terminal Delivery Appt Set` et `On Hand DEST Delv Appt Set` indiquent qu’un rendez-vous de livraison est fixé pendant que l’envoi attend au terminal de distribution. La page SEKO affiche souvent une date `Expected Arrival` ou une fourchette `Estimated delivery between` pendant le transit.
- **Passages transfrontaliers OmniParcel :** sur les commandes e-commerce internationales (`GX...`), les scans OmniParcel couvrent l’entrepôt d’origine, l’export, le vol, la douane de destination puis la remise à un transporteur local (`Received by Last Mile Provider`). Les scans peuvent marquer une pause pendant les vols et la douane ; consulte le [guide sur la douane](guide:customs) et celui sur le [suivi bloqué](guide:tracking-not-updating) si les mises à jour s’interrompent.
- **Horaires et doublons :** la page de suivi SEKO affiche les heures à la minute près sans fuseau horaire et répète parfois le même statut deux fois dans la même minute avec une casse différente ; Peek n’en conserve qu’un et date les jalons SEKO (qui ne mentionnent aucun lieu) à l’heure de Chicago. La page OmniParcel affiche les heures en UTC+10:00 sauf changement du menu déroulant de fuseau horaire ; Peek interroge OmniParcel en UTC afin que chaque scan conserve son instant exact. Peek affiche chaque scan à l’heure de ton appareil.

Si ta commande internationale utilise un autre grand transporteur sur une autre partie du trajet, compare avec [DHL Express](carrier:dhl-express) ou [FedEx](carrier:fedex).

## Suivre un colis SEKO Logistics avec Peek

Comme les codes SEKO se recoupent avec d’autres transporteurs et n’ont pas de préfixe universel, colle ton lien complet `sekologistics.com/en/track/?trackingNumbers=...` ou `track.omniparcel.com/...` quand tu le possèdes. Si tu colles un numéro nu — `WFY4829173`, `482917360` ou un code OmniParcel `GX...` — choisis manuellement SEKO Logistics via « Changer » lors de l’ajout ou « Changer de transporteur » sur la fiche du colis.

Peek interroge directement les deux portails grâce à l’[adaptateur SEKO open source de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko) : les numéros `GX` + 17–18 chiffres sont automatiquement dirigés vers OmniParcel (interrogé en UTC, avec lecture des lieux de scan, du nom de service, du poids en kilogrammes et du pays de destination), tandis que les numéros d’expédition SEKO passent par la page de suivi SEKO (date d’arrivée prévue ou fenêtre de livraison, nom de service, pays de destination et jalons dédupliqués, tout en filtrant les lignes administratives `POD Image Attached` / `POD IMAGED` et en écartant noms de signataires et codes postaux).

Peek traduit les jalons SEKO et OmniParcel en étapes standard : « Étiquette créée » pour les données reçues, la création et l’arrivée du chauffeur à l’enlèvement ; « Pris en charge » pour l’enlèvement et l’entrée en entrepôt d’origine ; « En transit » pour les scans de hub, vol, transfert, terminal de livraison, arrivée en entrepôt, dédouanement terminé et remise au dernier kilomètre ; « À la douane » pour l’inspection douanière à destination ; « En livraison » pour la mise en livraison et l’arrivée du chauffeur sur site ; « Prêt à être retiré » pour l’attente en point relais ou en consigne ; « Tentative de livraison » pour les tentatives échouées ; « Problème signalé » pour les retenues d’origine ou de destination ; « Livré » pour la livraison et le dépôt en lieu sûr ; et « Retourné à l’expéditeur » pour les retours.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où la page SEKO affiche `Shipment is Out for Delivery`, `Out for Delivery` ou `Driver on site Delivery Location`, ou qu’OmniParcel affiche `Out for delivery` ou `The order is on the way to the recipient.`, toutes les 2 minutes à partir de 6 h sur l’horloge du scan le plus récent, tant que le suivi de SEKO répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque SEKO ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger SEKO Logistics. » Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger SEKO Logistics. »

Dans les réglages d’alertes de Peek, choisis « Chaque scan », « Étapes importantes » ou « Livraison seulement » pour adapter tes notifications.

## Contacter SEKO Logistics

SEKO publie ses lignes régionales de service client directement sur sa [page de suivi](https://www.sekologistics.com/en/track/) et sa [page Contact Us](https://www.sekologistics.com/en/contact-us/) :

- **Ligne US Home Delivery :** (866) 268-5263
- **Service client Royaume-Uni :** +44 1784 417120
- **Suivi officiel en ligne :** [SEKO Track My Shipment](https://www.sekologistics.com/en/track/) (accepte jusqu’à 30 numéros de suivi à la fois) et [OmniParcel Tracker](https://track.omniparcel.com/)
- **Demande d’information en ligne :** [Contacter SEKO Logistics](https://www.sekologistics.com/en/contact-us/) (sélectionner le formulaire « Track My Shipment »)
- **Présentation de l’entreprise :** [À propos de SEKO Logistics](https://www.sekologistics.com/en/about/) et [sekologistics.com](https://www.sekologistics.com/en/)

Garde ton numéro de suivi SEKO sous la main avant d’appeler ou de remplir le formulaire Track My Shipment.

## Questions fréquentes

### Pourquoi mon numéro `GX` ne fonctionne-t-il pas sur `sekologistics.com/en/track/` ?

Les numéros commençant par `GX` suivis de 17 ou 18 chiffres appartiennent à la plateforme OmniParcel de SEKO et ne répondent que sur [track.omniparcel.com](https://track.omniparcel.com/). Dans Peek, choisir SEKO Logistics dirige automatiquement les numéros `GX` vers OmniParcel et les numéros SEKO classiques vers le suivi principal de SEKO.

### « Delivered To Warehouse » signifie-t-il que ma commande a été livrée à la mauvaise adresse ?

Non. Dans le suivi SEKO Logistics, `Delivered To Warehouse` indique que l’envoi est arrivé à l’entrepôt de destination de SEKO avant la livraison finale. Attends `Out for delivery` puis `Shipment Delivered` pour la confirmation de livraison à domicile.

### Pourquoi Peek m’a-t-il demandé de choisir un transporteur pour mon numéro SEKO ?

Les numéros SEKO (comme 3 lettres + 7 chiffres ou 9 chiffres) correspondent aussi à d’autres transporteurs, et Peek ne reconnaît jamais SEKO à partir d’un numéro nu, codes `GX` compris. Sélectionner SEKO Logistics via « Changer » ou coller le lien officiel SEKO ou OmniParcel indique précisément à Peek où chercher.

### Comment planifier un rendez-vous de livraison à domicile SEKO Logistics ?

Appelle la ligne US Home Delivery au `(866) 268-5263` ou le service client UK au `+44 1784 417120`, ou utilise le formulaire Track My Shipment sur [sekologistics.com/en/contact-us](https://www.sekologistics.com/en/contact-us/).

:::sources
- [SEKO Logistics](https://www.sekologistics.com/en/)
- [SEKO Logistics — Track My Shipment](https://www.sekologistics.com/en/track/)
- [SEKO Logistics — About](https://www.sekologistics.com/en/about/)
- [SEKO Logistics — Contact Us](https://www.sekologistics.com/en/contact-us/)
- [SEKO OmniParcel Tracker](https://track.omniparcel.com/)
- [universal-parcel-scraper SEKO adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko)
:::
