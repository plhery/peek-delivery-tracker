---
title: Suivi OnTrac : numéros, statuts et livraisons manquées
description: Suis un colis OnTrac ou LaserShip aux États-Unis : formats de numéros C, D, LX et 1LS, sens des statuts, absences, retards et assistance Wink.
slug: suivi-ontrac
published: 2026-11-21
updated: 2026-11-21
---

OnTrac est un transporteur américain du dernier kilomètre qui livre les commandes e-commerce pour les grandes enseignes et marques dans `35` États américains et à Washington, D.C. (`70 %` de la population des États-Unis), et qui regroupe LaserShip depuis leur fusion en 2021. Tu peux suivre un colis sur [ontrac.com/tracking](https://www.ontrac.com/tracking/) avec ton numéro de suivi, ou le coller directement dans le champ ci-dessus.

## Format du numéro de suivi OnTrac et où le trouver

Ton numéro figure sur la page de commande ou dans l'e-mail d'expédition du marchand (un numéro de commande ne fonctionne pas). La FAQ d'OnTrac indique que ses numéros commencent par `C`, `D`, `1LS`, `LS`, `LX` ou `BN` ([retrouver un numéro de suivi](guide:find-tracking-number)). Voici les formes que Peek reconnaît, avec des chiffres inventés :

| Forme (inventée) | Ce que c'est |
| --- | --- |
| `C14829105736497` | `C1` suivi de 13 chiffres se terminant par une clé de contrôle (15 caractères) : identifié d'office comme OnTrac. |
| `D10482910573645` | `D10` suivi de 12 chiffres avec clé de contrôle (15 caractères) : identifié d'office comme OnTrac (`C24829105736495` et les autres `C`/`D` à 14 chiffres valides sont vérifiés auprès d'OnTrac). |
| `LX48291057` | Format LaserShip : `L`, puis `A`, `E`, `H`, `I`, `N` ou `X`, et 8 chiffres (10 caractères). |
| `1LS482910573649` | Format LaserShip : `1LS` suivi de 12 à 14 lettres ou chiffres. |
| `AD482910573` | Référence expéditeur (`AD` + 9 chiffres), qu'OnTrac convertit en son propre numéro de suivi une fois OnTrac sélectionné. |

Le domaine historique `lasership.com` redirige désormais vers `ontrac.com`, où tous les numéros LaserShip sont suivis. Pour un préfixe `LS` ou `BN` (ou tout code ne respectant pas l'une des formes ci-dessus), consulte directement `ontrac.com/tracking/`, car une sélection manuelle dans Peek affichera qu'OnTrac « n’émet pas de numéros comme celui-ci ». Un code tout neuf peut rester vide au début : OnTrac recommande d'attendre **1 à 3 jours** après l'e-mail d'expédition, certains vendeurs mettant jusqu'à 24 heures à transmettre leurs données.

## Signification des statuts de suivi OnTrac

Le site d'OnTrac est uniquement en anglais. Voici les libellés d'événements et de retards que lit Peek, dans l'ordre habituel :

| Statut OnTrac | Ce que cela signifie | Peek affiche |
| --- | --- | --- |
| `On its way to OnTrac` | OnTrac a reçu les données de l'étiquette mais pas encore le colis (`LABEL CREATED, TRACKING DETAILS WILL BE AVAILABLE ONCE THE PACKAGE ARRIVES AT THE ONTRAC FACILITY` ou `Waiting for package from shipper.`). | « Étiquette créée » |
| `Origin Scan` | OnTrac a réceptionné le colis et l'achemine vers ton centre local (`Arrived at Facility` jalonne les passages intermédiaires). | « En transit » |
| `Arrived at Final Servicing Facility` | Arrivé au centre local qui assure ta livraison. | « En transit » |
| `Loaded onto vehicle` | Chargé dans un véhicule de liaison ou de tournée. | « En transit » |
| `Out for Delivery` | Avec le chauffeur pour livraison aujourd'hui. | « En livraison » |
| `Package Delivered` | Colis livré. | « Livré » |
| `Delivery Pending` | Le chauffeur n'a pas pu laisser le colis (accès sécurisé, signature requise, etc.) ; nouvelle tentative prévue. | « Tentative de livraison » |
| `Business closed. Please provide hours` | Commerce fermé lors du passage ; OnTrac retente jusqu'à 3 fois et demande tes horaires d'ouverture. | « Tentative de livraison » |
| `Incomplete address. Please contact us` | Adresse incomplète lors de la tournée ; fournis le complément sous 3 jours ouvrés pour éviter le retour. | « Tentative de livraison » |
| `Details needed. Please contact us` | Colis retenu au centre en attente de précisions d'adresse ou d'accès (sous 3 jours ouvrés). | « Problème signalé » |
| `Order has been transferred to another carrier` | OnTrac a remis le colis à un transporteur tiers non nommé (le titre sur `ontrac.com` affiche pourtant *Delivered*). | « En transit » |
| `Delivery Delay`, `Delayed. Delivery date updated`, `Delayed by road closure`, `Delayed. Could not reach delivery area` | Retard d'acheminement, route fermée ou zone inaccessible ; une nouvelle date de livraison est calculée. | « En transit » |
| `Weather delay. Delivery date updated`, `Weather delay. See service alerts` | Retard météorologique sur le réseau OnTrac. | « En transit » |
| `Misrouted. Delivery date updated` | Erreur d'aiguillage corrigée avec mise à jour de la date prévue. | « En transit » |

Consulte aussi notre guide général sur [le sens des statuts de suivi](guide:tracking-statuses).

## Horaires de livraison OnTrac et livraisons manquées

**Horaires.** OnTrac livre habituellement à domicile entre **`08:00` et `21:00`** (heure locale) à la date prévue, et assure aussi des livraisons le **samedi et le dimanche** sur les mêmes horaires dans une partie de ses zones de desserte (gabarit standard dans les conditions complémentaires : jusqu'à `50 lbs`, longueur `48 in`, deuxième côté `30 in`, volume `8 640 po³`).

**Livraison manquée.** OnTrac effectue jusqu'à **3 tentatives de livraison** (la suivante sous **1 à 2 jours ouvrés**, jamais le jour même), puis renvoie ou traite le colis selon les instructions de l'expéditeur ([livraison manquée](guide:missed-delivery)).

**Ajouter un code d'accès ou modifier l'adresse.** Si une tentative échoue alors que tu étais présent, vérifie l'adresse sur ta commande puis transmets un digicode ou des instructions au chatbot **Wink** avec ton numéro de suivi (si le colis est déjà en tournée, la consigne ne s'appliquera qu'au passage suivant sous 1 à 2 jours ouvrés). En revanche, tout **changement d'adresse** doit être approuvé par le vendeur ([modifier une adresse de livraison](guide:change-delivery-address)).

**Retrait en centre et alertes SMS.** OnTrac ne propose ni points relais ni consignes propres, et ne garde un colis en agence locale que sur demande préalable (apporte le numéro de suivi et une pièce d'identité avec photo). Sur `ontrac.com/tracking/`, le bouton `Get Text Alerts` (`NotifyMe`) permet de recevoir un SMS lors de la mise en livraison, d'une tentative ou de la remise (désinscription en répondant `STOP` au `(469) 529-1133`), et d'afficher la photo de livraison (`VPOD`).

## Colis OnTrac en retard, bloqué ou livré mais introuvable

- **Aucune mise à jour :** si le suivi n'évolue pas pendant **3 jours ouvrés**, OnTrac conseille de contacter d'abord le marchand, puis l'assistant **Wink** si besoin ([suivi qui ne se met plus à jour](guide:tracking-not-updating)).
- **Indiqué livré mais absent :** vérifie l'adresse et la photo de livraison sur `ontrac.com/tracking/` (ou auprès du gestionnaire si le colis est dans un casier d'immeuble), puis sélectionne `Tracking shows delivered; no package` sous `Have a question?` ou ouvre un ticket via **Wink**. OnTrac envoie un numéro de dossier par e-mail et enquête sous **48 heures** ; sans nouvelles après **2 jours ouvrés**, recontacte le vendeur ([indiqué livré mais non reçu](guide:delivered-not-received)).
- **Endommagé ou perdu :** les conditions d'OnTrac stipulent que toute réclamation officielle d'indemnisation est déposée par l'expéditeur (`cargoclaims@ontrac.com`) ; demande un remplacement ou un remboursement à la boutique ([colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel)).

## Service client OnTrac : chat, SMS et horaires

Sur ses pages [Support](https://www.ontrac.com/support/) et [FAQ](https://www.ontrac.com/faq/), OnTrac ne publie ni numéro d'appel vocal ni adresse e-mail grand public, et propose trois canaux aux destinataires :

- **Chat en ligne avec Wink :** icône rouge en bas à droite sur `ontrac.com` (numéro de suivi valide requis).
- **Ligne SMS automatisée :** envoie `start` par SMS au **`703-348-8441`** (SMS uniquement, tarifs opérateur habituels, `STOP` pour arrêter) pour suivre un colis ou ouvrir un dossier.
- **Formulaire de suivi :** rubrique `Have a question?` affichée sous le résultat sur `ontrac.com/tracking/`.
- **Horaires du centre de contact :** du lundi au vendredi de `08:00` à `21:00` (heure de l'Est, *Eastern Time*) et les samedi et dimanche de `08:00` à `20:00` (*Eastern Time*), fermé le jour de Noël, le 26 décembre et le jour de l'An.

Pour éviter les faux messages imitant une alerte de livraison, consulte [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts).

## Suivre un colis OnTrac avec Peek

[Peek](/) reconnaît immédiatement OnTrac (« Transporteur détecté ») pour les numéros `C1` et `D10` validés par la clé de contrôle d'OnTrac, les codes LaserShip `LX` et `1LS`, ainsi que les liens `ontrac.com` comportant `number=`. Pour les autres codes `C` ou `D` à 14 chiffres valides, Peek affiche « Vérification des services de suivi… » puis « OnTrac a ce colis. » dès qu'OnTrac renvoie des scans. Pour une référence expéditeur `AD` à 9 chiffres, appuie sur « Changer » et choisis OnTrac : Peek remplace alors la référence par le vrai numéro OnTrac du colis (avant le premier scan, l'envoi affiche « En attente de OnTrac »).

Peek interroge directement le service JSON d'OnTrac et affiche l'état, chaque scan avec sa ville et son État sur la carte (jusqu'aux 100 plus récents), la date de livraison prévue, l'heure de livraison, le poids et les dimensions, tout en omettant le destinataire, la signature et la photo de livraison. Lorsqu'OnTrac fournit un décalage horaire, l'heure est convertie dans le fuseau de ton appareil (« Heures dans ton fuseau horaire : … ») ; sinon, l'heure locale de l'étape est conservée telle quelle. `Order has been transferred to another carrier` reste « En transit » dans Peek, sans suivi ultérieur puisque OnTrac ne nomme pas le transporteur relais.

Tant que tu gardes le colis ouvert ou le jour de livraison avec notifications actives, Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes dès `06:00` heure locale le jour du scan `Out for Delivery` tant que le service d'OnTrac répond ; sinon, et après 12 h sur « Étiquette créée » / « Pris en charge » ou deux jours sans nouveauté, toutes les heures (« Chaque scan », « Étapes importantes » ou « Livraison seulement »). Peek ne peut contacter ni OnTrac ni le marchand pour toi.

## Questions fréquentes sur le suivi OnTrac

### Par quelles lettres commencent les numéros de suivi OnTrac ?

Par `C`, `D`, `1LS`, `LS`, `LX` ou `BN` selon OnTrac ; les numéros `C` et `D` suivis par Peek comptent 15 caractères au total (la lettre suivie de 14 chiffres).

### Pourquoi mon suivi OnTrac ne bouge-t-il pas ?

Compte d'abord 1 à 3 jours après l'e-mail d'expédition pour que le colis arrive en centre OnTrac. Si un colis déjà actif n'a plus évolué depuis 3 jours ouvrés, contacte le vendeur puis l'assistant Wink.

### À quelle heure livre OnTrac ?

Entre `08:00` et `21:00` (heure locale) le jour prévu, y compris le samedi et le dimanche dans les zones desservies le week-end.

### OnTrac propose-t-il un suivi en direct ?

OnTrac affiche chaque scan avec sa date, son heure et sa ville (plus une photo de remise lorsqu'elle est disponible), et son programme `NotifyMe` envoie des SMS lors du départ en tournée, d'une tentative et de la livraison.

### OnTrac est-il un transporteur fiable ?

Oui : fondé en 1991 sous le nom *California Overnight* et fusionné en 2021 avec LaserShip (créé en 1986), OnTrac opère plus de `102` sites et `18` centres de tri aux États-Unis et fait partie des transporteurs recommandés pour les vendeurs Amazon expédiant eux-mêmes leurs commandes (*MFN*).

:::sources
- [OnTrac : FAQ](https://www.ontrac.com/faq/) – préfixes de numéros, livraison 08:00–21:00 et week-end, 1–2 jours entre tentatives, Wink, ligne SMS 703-348-8441, 102+ sites et 18 centres de tri
- [OnTrac : Support](https://www.ontrac.com/support/) – délai initial de 1 à 3 jours, enquête sous 48 heures, NotifyMe et horaires/fermetures (dont le 26 décembre)
- [OnTrac : Tracking](https://www.ontrac.com/tracking/) – formulaire de suivi, photo de livraison et formulaire Have a question?
- [OnTrac : Script de la page de suivi](https://www.ontrac.com/wp-content/themes/ontrac/assets/js/ontrac-package-status.js) – titres d'état, colonnes d'historique et motifs de demande
- [OnTrac : Codes de statut](https://www.ontrac.com/wp-content/themes/ontrac/assets/data/statusCodes.json) – signification des tentatives, retenues sous 3 jours ouvrés, transferts et retards
- [OnTrac : Additional Terms & Conditions](https://www.ontrac.com/terms-conditions-additional/) – 3 tentatives, limites de 50 lbs / 48 in et réclamations déposées par l'expéditeur
- [OnTrac : About OnTrac](https://www.ontrac.com/about/) – création de LaserShip en 1986 et d'OnTrac en 1991, fusion en 2021 et réseau transcontinental
- [OnTrac : Residential E-Commerce Delivery](https://www.ontrac.com/residential-e-commerce-delivery/) – 35 États et Washington, D.C., 70 % de la population américaine et preuve photo VPOD
- [OnTrac : Amazon MFN Sellers](https://www.ontrac.com/amazon-mfn-sellers/) – transporteur partenaire pour les vendeurs Amazon MFN
- [OnTrac : NotifyMe Terms of Use](https://www.ontrac.com/notifyme-terms-of-use/) – alertes SMS par colis et désinscription STOP au (469) 529-1133
- [Universal Parcel Scraper : OnTrac](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ontrac) – formats C, D, LX, 1LS et référence AD, 18 libellés de statut, horloges, poids/dimensions et transfert NFRP
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – détection multi-transporteurs et services de suivi
:::
