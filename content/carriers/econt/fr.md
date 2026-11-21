---
title: Suivi Econt : numéro d'envoi, statuts et agences
description: Suis un colis Econt en Bulgarie : où trouver le numéro à 13 chiffres, sens des statuts anglais et bulgares, agences, Econtomats, réclamations et contacts.
slug: suivi-econt
published: 2026-11-21
updated: 2026-11-21
---

Econt Express (*Econt Express AD*) est un transporteur bulgare fondé en 1993 qui livre à domicile, dans ses agences Econt et dans ses consignes automatiques Econtomat. Tu peux suivre ton envoi via `Track a shipment` sur [econt.com](https://www.econt.com/en/services/track-shipment), depuis le lien reçu par e-mail ou SMS d'Econt, ou en collant le numéro d'expédition dans le champ ci-dessus.

## Numéro de suivi Econt : format et où le trouver

Econt l'appelle numéro d'envoi (*shipment number*) ou numéro de lettre de voiture (*waybill number*). Les notifications par e-mail (gratuites) et par SMS (option facturée `0,11 €` TVA incluse à l'expéditeur, qui n'est donc pas activée sur chaque colis) contiennent un lien direct vers le suivi ainsi que le lieu, l'échéance et le montant éventuel à régler. Sans message d'Econt, vérifie l'e-mail d'expédition du vendeur ou consulte nos conseils pour [retrouver un numéro de suivi](guide:find-tracking-number).

Econt ne publie pas de règle grand public sur ses numéros, mais les lettres de voiture d'exemple de son manuel d'intégration XML comportent **13 chiffres**, et le service de suivi d'Econt les lit comme un entier positif à 13 chiffres ne commençant pas par `0`, comme le numéro inventé `1048291057364`.

Ce format à 13 chiffres est aussi employé par d'autres transporteurs — notamment [DPD France](carrier:dpd-fr), Ukrposhta en Ukraine, FAN Courier en Roumanie ou Delhivery en Inde — d'où l'intérêt de vérifier quel transporteur mentionne ton message d'expédition. Si tu choisis Econt manuellement pour un code qui commence par `0` ou n'a pas 13 chiffres, Peek signale qu'il « n’émet pas de numéros comme celui-ci ».

## Signification des statuts de suivi Econt

Le site `econt.com` s'ouvre par défaut en bulgare (`Проследи пратка`) avec un basculement vers l'anglais (`Track a shipment`). Voici les libellés d'événements en anglais tels que Peek les affiche, dans l'ordre habituel d'un acheminement :

| Statut Econt | Ce que cela signifie | Peek affiche |
| --- | --- | --- |
| `Awaiting delivery to Econt` | L'expéditeur a [créé le bordereau](guide:tracking-statuses) mais n'a pas encore remis le colis à Econt. | « Étiquette créée » |
| `Submitted to courier` | En tout début d'historique : un coursier a collecté le colis chez l'expéditeur (« Pris en charge »). Après un passage en agence ou sur une liaison : le colis est parti en tournée (« En livraison »). | « Pris en charge » / « En livraison » |
| `Arrival at an office` | Arrivée dans l'agence ou le centre logistique indiqué à côté (y compris ton agence de retrait). | « En transit » (ou « Prêt à être retiré ») |
| `Received by line-haul` | Transport sur une liaison interurbaine entre centres ; l'intitulé précise l'itinéraire de ville à ville. | « En transit » |
| `Change delivery terms` | Une instruction a modifié les conditions de livraison (par exemple l'agence ou le jour) ; l'objet suit `Change delivery terms:` sans que le colis ait bougé. | *(note sans étape)* |
| `Shipment forwarding` | Réexpédition vers une autre localité ou une nouvelle adresse. | « En transit » |
| `Failed delivery` | La remise a échoué (motif entre parenthèses) et le coursier a rapporté le colis à l'agence indiquée. | « Tentative de livraison » |
| `Delivered` | Colis remis ou retiré (suivi du nom de la personne qui l'a réceptionné lorsqu'Econt le renseigne). | « Livré » |
| `Delivery return` | Le colis repart vers l'expéditeur. | « Retourné à l’expéditeur » |
| `Shipment destruction` | Destruction programmée de l'envoi ; contacte immédiatement le vendeur ou Econt. | « Problème signalé » |
| `Information` | Note d'information sans changement d'étape. | *(note sans étape)* |
| `Return receipt: Delivered` | Suivi de l'accusé de réception signé renvoyé à l'expéditeur : cette ligne concerne le récépissé, pas ton colis. | *(récépissé, sans étape)* |

Le libellé `Submitted to courier` sert deux fois : avant toute agence, ligne interurbaine, réexpédition ou tentative de livraison, il correspond à l'enlèvement chez l'expéditeur ; ensuite, à la tournée finale vers chez toi.

En en-tête, le traceur d'Econt renvoie aussi un **statut récapitulatif en bulgare** que Peek utilise lorsqu'il tranche l'étape en cours : `Очаква предаване към Еконт` et `Подготвена в eEcont` (« Étiquette créée »), `Приета в Еконт` et `Приета в офис` (« Pris en charge »), `Пътува по линия` (« En transit »), `В офис` et `Пристигнала в офис` (« Prêt à être retiré » dès qu'Econt désigne l'agence qui stocke le colis), `Доставена` (« Livré »), `Върната` et `Върната и доставена към подател` (« Retourné à l’expéditeur »), ainsi que `Връща се към подател`, `Анулирана след изпращане` et `Анулирана преди изпращане` (« Problème signalé »). Lorsque le résumé indique `В куриер` (avec un coursier), c'est le dernier scan qui départage collecte et livraison.

## Livraison Econt, agences et consignes Econtomat

**À domicile.** Dans les localités dotées d'une agence Econt, les colis standard (jusqu'à `50 kg` et `90 x 90 x 90 cm`, documents A4 jusqu'à `0,5 kg`, envois non palettisés de `50` à `1 000 kg`) arrivent le jour ouvré suivant — avant `18:00` en intra-urbain — et sous **3 jours ouvrés** selon planning ailleurs. L'expéditeur peut ajouter un créneau gratuit (`Delivery Time Slots`), une heure fixe (`1,88 €`–`6,50 €`) ou le samedi (`1,88 €`), prix TVA incluse.

**En agence Econt.** Un envoi pour le lendemain ouvré est prêt tôt le matin à l'ouverture. Après le **7e jour** de garde, Econt facture **`3,78 €` par jour et par envoi** (TVA incluse).

**En consigne Econtomat (24/7).** Capacité jusqu'à **`61 x 44 x 37 cm`** et **`30,5 kg`** (casiers de `8`, `18` et `37 cm` de haut), délai de **1 à 2 jours ouvrés**. Pour retirer : paie d'avance dans l'application Econt par carte ou `ecoints`, touche `Receive a shipment`, scanne le QR code ou entre `PIN 1` et `PIN 2` reçus dans l'app ou par SMS, puis prends ton colis.

**Modifier ou vérifier l'envoi.** Tu peux changer à tout moment destinataire, lieu (autre adresse, agence, ami/voisin, autre ville) ou horaire par téléphone, via le lien de l'e-mail ou dans `e-Econt` ([modifier une adresse de livraison](guide:change-delivery-address)). Trois options gratuites activables par le vendeur permettent de vérifier la marchandise : `Review`, `Review and Test` et `Review, Test and Select`.

## Colis Econt en retard, endommagé ou introuvable

Compte le jour ouvré suivant vers une ville équipée d'une agence Econt, jusqu'à 3 jours ouvrés ailleurs en Bulgarie et 1 à 2 jours ouvrés depuis un Econtomat. Si rien ne bouge pendant plusieurs jours, consulte notre guide sur un [suivi qui ne se met plus à jour](guide:tracking-not-updating), puis contacte le vendeur ou Econt.

En cas de colis endommagé, manquant ou perdu ([colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel)), dépose une réclamation via `I want to file a claim` sur le centre client d'Econt et préviens le marchand.

## Contacter le service client Econt

Sur sa [page du centre client](https://www.econt.com/en/client-center), Econt indique :

- **Depuis la Bulgarie et la Grèce :** `0 7001 7300` (au tarif d'un appel local ou selon ton forfait téléphonique).
- **Depuis les autres pays :** `+359 88 565 43 21` (selon le tarif de ton opérateur).
- **En ligne :** assistant virtuel `Ask Ena`, recherche des agences et horaires (`I want to find offices and working time`) et formulaire de réclamation (`I want to file a claim`).

Si tu reçois un SMS suspect réclamant un paiement hors de l'application officielle ou de `econt.com`, vérifie-le avec notre guide sur les [SMS de livraison frauduleux](guide:delivery-scam-texts).

## Suivre un colis Econt avec Peek

Comme 13 chiffres correspondent à plusieurs transporteurs, [Peek](/) n'interroge automatiquement Econt que lorsque tu ajoutes le numéro depuis la Bulgarie sur le site ou sur un téléphone configuré sur la région Bulgarie. Ailleurs, si aucun autre transporteur ne reconnaît le code, choisis Econt avec « Changer » — ou colle directement un lien `econt.com` contenant `/services/track-shipment/`, `/en/services/track-shipment/`, `/tracking/` ou `/tracking/?num=`, qui désigne Econt aussitôt (tant qu'Econt n'a pas encore de dossier ou si l'historique ancien a été purgé, Peek affiche « En attente de Econt », puis, au bout de deux jours, te demande de vérifier le numéro ou de choisir un autre transporteur).

Peek interroge le service de suivi d'Econt sans compte ni code postal, affiche l'étape en cours, place les agences sur la carte, indique l'agence où le colis attend lorsqu'il est « Prêt à être retiré » et garde jusqu'aux 100 scans les plus récents. Pour la personne qui a ajouté le colis, Peek conserve le nom du destinataire et du signataire figurant après `Delivered:` (masqués sur un lien de partage), tandis que le nombre de colis, le nom du coursier, l'expéditeur et les numéros d'ordre sont écartés ; Econt ne communique ni date de livraison estimée, ni poids, ni photo de livraison.

Les heures sont lues sur le fuseau de Sofia (`Europe/Sofia`, `UTC+2` en hiver et `UTC+3` en été) et converties dans celui de ton appareil (« Heures dans ton fuseau horaire : … »). Tant que le colis est ouvert ou le jour de livraison avec notifications actives, Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes dès `06:00` heure de Sofia le jour où Econt scanne le colis « En livraison » tant que le traceur d'Econt répond (via les services de suivi : toutes les heures, toutes les 3 h après un jour calme et jusqu'à toutes les 15 minutes le jour de livraison si le colis est ouvert ou les alertes activées) ; sinon, et après 12 h sur « Étiquette créée » / « Pris en charge » ou deux jours sans nouveauté, toutes les heures (« Chaque scan », « Étapes importantes » ou « Livraison seulement »). Peek ne peut ni modifier tes instructions chez Econt ni déposer de réclamation pour toi.

## Questions fréquentes sur le suivi Econt

### Puis-je consulter le suivi Econt sans lire le bulgare ?

Oui : Peek affiche les étapes d'Econt avec leurs intitulés anglais du tableau ci-dessus et traduit l'état général du colis en français. Le site `econt.com` dispose aussi d'une version anglaise sous `Track a shipment`.

### Quels sont les délais de livraison d'Econt en Bulgarie ?

Econt annonce le jour ouvré suivant vers les localités disposant d'une agence Econt (avant `18:00` à domicile en intra-urbain, dès l'ouverture en agence), jusqu'à 3 jours ouvrés vers les localités sans agence selon le planning de tournée, et 1 à 2 jours ouvrés depuis un Econtomat.

### Comment suivre un colis Econt hors de Bulgarie avec son numéro à 13 chiffres ?

Colle les 13 chiffres dans le champ ci-dessus puis sélectionne Econt via « Changer » (ou colle le lien complet `econt.com/en/services/track-shipment/…`), car un code à 13 chiffres seul n'est testé automatiquement chez Econt que depuis la Bulgarie.

:::sources
- [Econt : Track a shipment](https://www.econt.com/en/services/track-shipment) – suivi en anglais sur econt.com
- [Econt : Проследяване на пратка](https://www.econt.com/services/track-shipment) – page de suivi en bulgare
- [Econt : About Econt Express](https://www.econt.com/en/econt-express) – Econt Express AD, fondé en 1993 en Bulgarie
- [Econt : Customer service center](https://www.econt.com/en/client-center) – 0 7001 7300 (Bulgarie et Grèce), +359 88 565 43 21, Ask Ena, recherche d'agences et réclamations
- [Econt : Courier services](https://www.econt.com/en/services/courier-services) – gabarits jusqu'à 50 kg et 90 x 90 x 90 cm, délais, créneaux, samedi, garde à 3,78 €/jour après le 7e jour, SMS à 0,11 € et options gratuites Review / Test / Select
- [Econt : Econtomat](https://www.econt.com/en/services/econtomat) – consignes 24/7 jusqu'à 61 x 44 x 37 cm et 30,5 kg, paiement préalable, QR code et PIN 1 / PIN 2
- [Econt : Имейл или SMS известяване](https://www.econt.com/developers/29-sms-izvestyavane.html) – notifications par e-mail (gratuites) ou SMS (optionnelles) avec lien de suivi
- [Econt : Instructions form](https://instruction.econt.com/5cZyLrYsBZH) – formulaire de modification d'adresse, d'agence, de créneau ou de remise à un voisin
- [Econt : Integration of e-shops with the information system of Econt Express](https://www.econt.com/e-econt/doc_templates/XML_manual_engl.pdf) – exemples de numéros de lettre de voiture à 13 chiffres
- [Universal Parcel Scraper : Econt](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/econt) – validation entière à 13 chiffres, détection en Bulgarie, statuts anglais et résumés bulgares, fuseau Europe/Sofia, signataire et champs écartés
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – autres transporteurs à 13 chiffres et services de suivi en relais
:::
