---
title: Suivi Mainfreight : numéros de fret et statuts
description: Suis un envoi Mainfreight en Nouvelle-Zélande, en Australie, aux États-Unis ou en Europe : numéros, statuts en français, douane et suivi dans Peek.
slug: suivi-mainfreight
published: 2026-11-22
updated: 2026-11-22
---

Mainfreight achemine du fret par route, air et mer. Le catalogue de Peek le répertorie pour la Nouvelle-Zélande (son pays d’origine), l’Australie et les États-Unis ; son outil de suivi couvre aussi le fret routier en Europe et les mouvements de conteneurs. Pour suivre un envoi ou un colis volumineux expédié par Mainfreight, colle le numéro d’expédition fourni par l’expéditeur dans le champ ci-dessus. Le suivi officiel de Mainfreight se trouve sur www.mainfreight.com.

## Numéro de suivi Mainfreight : à quoi il ressemble

Avec des chiffres inventés :

| Numéro (inventé) | Ce que c’est |
| --- | --- |
| `48291736542` | 11 chiffres : numéro d’expédition routière en Nouvelle-Zélande, en Australie ou aux États-Unis, seul format que le catalogue de Peek relie à Mainfreight. |
| Lettres et chiffres | Numéro de dossier (`S` et 8 chiffres en aérien/maritime, `CJ` et 9 chiffres pour les conteneurs), connaissement maison ou référence de l’expéditeur. Peek interroge Mainfreight avec 4 à 40 lettres et chiffres une fois que tu l’as choisi. |
| Lettre de transport principale | Les 11 chiffres d’une lettre de transport aérien ou un connaissement maritime principal : Peek retrouve le dossier aérien ou maritime si un seul dossier y correspond. |

Le catalogue de Peek connaît aussi Mainfreight sous les noms Mainfreight Group, Mainfreight Transport et Mainfreight Air & Ocean.

**Où le trouver.** Dans l’e-mail d’expédition ou sur ta page de commande ; pour du fret entre entreprises, demande à l’expéditeur. Voir [comment trouver son numéro de suivi](guide:find-tracking-number).

**Autres transporteurs à 11 chiffres.** Onze chiffres correspondent aussi à [GLS Germany](carrier:gls-de), [GLS France](carrier:gls-fr), DHL eCommerce Poland, PostLogistics et d’autres. Si ton e-mail nomme l’un d’eux, suis-le là-bas. Plus d’informations dans [formats des numéros de suivi](guide:tracking-number-formats).

## Que signifient les statuts de suivi Mainfreight

**Fret routier en Nouvelle-Zélande et en Australie :**

| Mainfreight indique | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Information Received` | Mainfreight a reçu les données, pas encore la marchandise. | « Étiquette créée » |
| `Pickup Allocated to Driver` | Un chauffeur est affecté à l’enlèvement ; la marchandise est encore chez l’expéditeur. | « Étiquette créée » |
| `Picked Up` | Enlevé chez l’expéditeur. | « Pris en charge » |
| `In Transit` | En cours d’acheminement. | « En transit » |
| `At Delivery Depot` | Au dépôt chargé de la livraison. | « En transit » |
| `On Delivery Vehicle` | Dans le camion de livraison. | « En livraison » |
| `Delivered` | Livré. | « Livré » |

**Fret routier aux États-Unis.** Les étapes américaines ajoutent le lieu après le libellé ; Peek l’affiche à côté de l’étape.

| Mainfreight indique | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Booked` | Le transport est réservé. | « Étiquette créée » |
| `Pickup assigned to driver` | Un chauffeur est affecté à l’enlèvement. | « Étiquette créée » |
| `Goods picked up` | Enlevé chez l’expéditeur. | « Pris en charge » |
| `Departed from Terminal`, `Arrived at Terminal` | Parti d’un terminal ou arrivé à un terminal. | « En transit » |
| `On drivers vehicle en-route` | Dans le camion en route vers toi. | « En livraison » |
| `Goods delivered` | Livré. | « Livré » |
| `Collected by Customer` | Retiré au terminal par le client. | « Livré » |

**Envois aériens et maritimes :**

| Mainfreight indique | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Booking Confirmation Sent` | La réservation est confirmée. | « Étiquette créée » |
| `Pickup Cartage Advised` | Un camion d’enlèvement est programmé. | « Étiquette créée » |
| `Pick up Cartage Complete` | Marchandise enlevée pour l’export. | « Pris en charge » |
| `Origin Customs Cleared` | Douane export validée avant départ. | « En transit » |
| `Departure from First Load Port`, `Arrival at Final Discharge Port` | Parti du premier port ou aéroport, ou arrivé au dernier. | « En transit » |
| `Customs Clearance Commenced` | Le dédouanement import a commencé. | « À la douane » |
| `Customs Clearance Finalised` | Dédouané et de nouveau en route. | « En transit » |
| `Cargo Available` | Libéré à destination avant livraison. | « En transit » |
| `Cargo Delivered`, `Full Container Delivered` | Livré. | « Livré » |

Les départs et arrivées prévisionnels marqués comme estimés (`(Estimated)`) n’ont pas encore eu lieu : Peek les écarte.

**Autres étapes.** Les mouvements de conteneurs entre sites, comme `Picked Up (Site Movement)`, affichent « En transit », et `Final Delivery (Empty to Customer)` affiche « Livré ». Sur le fret routier européen, `Order received` affiche « Étiquette créée », `Shipment loaded into line haul truck` « En transit » et `Out for delivery` « En livraison ». En l’absence d’étape, Peek utilise le statut global du suivi, tel que `Created` ou `Final Delivery`.

Le code de Peek ne connaît aucun libellé propre à Mainfreight pour une livraison manquée, un incident ou un retour. Les autres libellés suivent les règles générales de Peek ou s’affichent tels quels sans changer d’étape. Voir [comprendre les statuts de suivi](guide:tracking-statuses).

## Comment Mainfreight livre

Peek n’a pas pu lire les pages d’aide du site web de Mainfreight ; cette page n’indique donc ni délais, ni rendez-vous, ni nombre de passages, ni règles de retrait en dépôt. Demande à la personne ou l’entreprise qui a réservé le transport comment et quand la livraison aura lieu.

Le code de Peek ne transfère les envois Mainfreight à aucun autre transporteur. Le suivi peut fournir une date de livraison estimée pour les dossiers aériens, maritimes et le fret routier européen, et Peek l’affiche jusqu’à la mise en livraison. Il ne fournit à Peek ni preuve de livraison ni nom de la personne ayant réceptionné la marchandise.

**Douane.** Pour les marchandises entrant en Nouvelle-Zélande, NZ Customs précise :

- Les achats sur des sites étrangers de 1 000 NZ$ ou moins ne paient pas de droits de douane et le vendeur étranger collecte la GST, sauf s’ils font partie d’un envoi groupé dépassant 1 000 NZ$. Le tabac et l’alcool paient droits et GST quelle que soit leur valeur.
- La GST est de 15 % de la valeur plus droits, fret et assurance éventuels. Une taxe « Customs and MPI Goods Levy » s’applique aux importations non exonérées ; ton courtier ou transitaire la règle et peut te la refacturer.
- Les envois de 1 000 NZ$ ou plus nécessitent un Customs Number, et la douane ne libère pas la marchandise avant paiement des frais.

Ailleurs, consulte [colis bloqué en douane](guide:customs) et demande à l’expéditeur qui règle les frais.

## Envoi Mainfreight en retard, bloqué, endommagé ou perdu

**Rien pour l’instant.** Un envoi enregistré sous Mainfreight que son suivi ne trouve pas encore affiche « En attente de Mainfreight » pendant que Peek continue d’interroger. Onze chiffres collés sans réponse d’aucun transporteur affichent « Aucun historique de suivi pour le moment ». Vérifie les chiffres et demande à l’expéditeur si Mainfreight a déjà pris en charge la marchandise.

**Bloqué sans mise à jour.** Après quatre jours sans nouvelle étape sur un envoi en « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche dans ta liste la date du dernier scan (« Aucune mise à jour depuis … ») et, sur la fiche, « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Mainfreight. », accompagné alors seulement du lien « Demande de l’aide à Mainfreight » vers son formulaire de contact. Demande au vendeur ou à l’expéditeur la raison du délai. Voir [suivi qui ne se met plus à jour](guide:tracking-not-updating).

**Endommagé, incomplet ou perdu.** Préviens d’abord le vendeur ou l’expéditeur qui a réservé le transport. En Nouvelle-Zélande et en Australie, les conditions de Mainfreight et le droit local régissent les réclamations. Pour du fret routier inter-États aux États-Unis, le droit fédéral prévoit :

- Le transporteur répond de la perte ou de l’avarie réelle ; sa responsabilité peut être limitée à une valeur déclarée par l’expéditeur ou convenue par écrit. Il ne peut accorder moins de 9 mois pour déposer une réclamation ni moins de 2 ans pour agir en justice à compter du rejet écrit de tout ou partie de celle-ci.
- Une réclamation est une demande écrite identifiant l’envoi, mettant en cause le transporteur et demandant un montant précis ou déterminable. Une réserve de dommage ou de manquant sur le récépissé de livraison ne suffit pas à elle seule. Le transporteur peut exiger le connaissement, la preuve des frais de transport et la facture.
- Le transporteur accuse réception par écrit sous 30 jours, puis paie, refuse ou formule une offre ferme par écrit sous 120 jours ; à défaut, il informe par écrit de l’avancement à cette échéance puis tous les 60 jours.

Si le suivi indique `Delivered` sans rien avoir reçu, voir [marqué livré mais pas reçu](guide:delivered-not-received). Plus d’infos sur [colis endommagé](guide:damaged-parcel) et [colis perdu](guide:lost-parcel).

## Service client et contact Mainfreight

Peek n’a pas pu lire le site web de Mainfreight ; cette page n’indique donc aucun numéro de téléphone, e-mail ni agence, car Peek ne publie que des coordonnées lues directement sur les pages officielles du transporteur.

Adresse-toi d’abord à celui qui a réservé le transport : la boutique ou le fournisseur pour une commande, l’entreprise expéditrice sinon. C’est elle qui détient le dossier et peut intervenir auprès de Mainfreight.

## Suivre un envoi Mainfreight avec Peek

Depuis la Nouvelle-Zélande, l’Australie ou les États-Unis, Peek interroge Mainfreight en premier sur un numéro à 11 chiffres lors de l’ajout (« Vérification des services de suivi… ») et nomme Mainfreight dès que son suivi reconnaît l’envoi. Depuis les autres pays, Peek n’interroge pas Mainfreight automatiquement à l’ajout : sélectionne-le manuellement.

Aucun autre format n’indique automatiquement Mainfreight à Peek. Colle tout de même la référence : Peek suit plus de 4 500+ transporteurs. Change ensuite de transporteur et choisis Mainfreight (pour 11 chiffres il apparaît parmi les formats compatibles ; sinon recherche-le par son nom). Un lien vers le suivi Mainfreight contenant le numéro identifie aussitôt Mainfreight (« Transporteur détecté »).

Peek n’exige ni compte ni code postal. Si la recherche Mainfreight ne trouve pas un numéro à 11 chiffres, Peek essaie simultanément ses services néo-zélandais, australien et américain ; si plusieurs le connaissent, Peek ne peut pas trancher. Pour un connaissement maison, un connaissement principal ou une référence expéditeur, Peek affiche le numéro sous lequel Mainfreight répond avec la mention « Mainfreight le suit sous le numéro ».

Peek affiche le statut, chaque étape dans le texte de Mainfreight avec son lieu et le poids dans tes unités lorsqu’il est fourni. Il écarte les coordonnées d’agence, les codes postaux, la référence expéditeur et les documents. Le bouton « Ouvrir le site Mainfreight » ouvre le numéro sur le suivi Mainfreight. Lorsque ce suivi est injoignable ou ne peut lire l’envoi, Peek interroge des [services de suivi](guide:universal-tracker) et affiche leur historique dans leurs propres termes.

Les étapes routières mentionnant un fuseau néo-zélandais, australien ou américain (`NZDT`, `AEST`, `PST`, etc.) donnent leur heure exacte. Les autres (étapes européennes, aériennes, maritimes ou conteneurs) n’ont pas de fuseau : Peek les lit sur l’heure du lieu, des étapes voisines ou de la destination, sinon sur l’heure de Nouvelle-Zélande, et les convertit dans ton fuseau horaire (l’historique se termine par « Heures dans ton fuseau horaire : » suivi de son nom), si bien qu’un horaire peut parfois être légèrement décalé.

Tant que la fiche est ouverte à l’écran — ou le jour de la livraison si les notifications sont activées — Peek interroge directement Mainfreight jusqu’à toutes les 10 minutes, ou toutes les 2 minutes dès 6 h heure locale le jour où Mainfreight scanne l’envoi en cours de livraison, tant que son suivi répond. Sinon, ainsi qu’après deux jours sans nouvelle (12 heures sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Via les seuls services de suivi, c’est toutes les heures, toutes les 3 heures après une journée sans scan, et jusqu’à toutes les 15 minutes le jour de la livraison si l’envoi est ouvert à l’écran ou si les notifications sont activées. Choisis « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut pas contacter Mainfreight, planifier une livraison ni ouvrir une réclamation à ta place.

## Questions sur le suivi Mainfreight

### À quoi ressemble un numéro de suivi Mainfreight ?

Pour le fret routier en Nouvelle-Zélande, en Australie et aux États-Unis, c’est un numéro d’expédition à 11 chiffres (comme `48291736542`). Les numéros de dossier, connaissements maison et références d’expéditeur mêlent lettres et chiffres : sélectionne Mainfreight manuellement dans Peek.

### Que signifie « At Delivery Depot » sur le suivi Mainfreight ?

La marchandise est arrivée au dépôt chargé de sa livraison et Peek affiche « En transit ». L’étape suivante est `On Delivery Vehicle`, quand elle part en camion vers toi. Si elle y reste plusieurs jours, demande à l’expéditeur la date prévue.

### Que signifie « Pickup Allocated to Driver » ?

Un chauffeur est affecté à l’enlèvement mais la marchandise est encore chez l’expéditeur ; Peek affiche donc « Étiquette créée ». L’étape suivante est `Picked Up`.

:::sources
- [Universal Parcel Scraper : Mainfreight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/mainfreight) – noms et pays, format à 11 chiffres, autres références et connaissements principaux, liens, recherche et services, libellés et étapes, étapes estimées, horaires, poids, date prévue, champs ignorés par Peek
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transporteurs partageant 11 chiffres, interrogation de Mainfreight depuis la Nouvelle-Zélande, l’Australie et les États-Unis, absence de relais, recours aux services de suivi
- [NZ Customs : Duty and GST](https://www.customs.govt.nz/sending-and-receiving/duty-and-gst) – 1 000 NZ$, GST collectée par les vendeurs étrangers, envois groupés, tabac et alcool, calcul de la GST, Goods Levy, Customs Number, libération après paiement
- [GovInfo : 49 U.S.C. 14706, Liability of carriers under receipts and bills of lading](https://www.govinfo.gov/content/pkg/USCODE-2024-title49/html/USCODE-2024-title49-subtitleIV-partB-chap147-sec14706.htm) – responsabilité pour perte ou dommage réel, limitation de valeur, 9 mois pour réclamer et 2 ans pour agir en justice
- [GovInfo : 49 CFR Part 370, Investigation and voluntary disposition of loss and damage claims and processing salvage](https://www.govinfo.gov/content/pkg/CFR-2025-title49-vol5/xml/CFR-2025-title49-vol5-part370.xml) – contenu d’une réclamation, réserves sur récépissé, pièces justificatives, délais de 30, 120 et 60 jours
:::
