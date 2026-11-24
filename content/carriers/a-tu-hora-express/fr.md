---
title: Suivi A Tu Hora Express : codes, code postal et aide
description: Suis un colis A Tu Hora Express en Espagne : codes à 10, 16 et 24 chiffres, pourquoi le code postal à 5 chiffres est requis, statuts et contact.
slug: suivi-a-tu-hora-express
published: 2026-11-24
updated: 2026-11-24
---

A Tu Hora Express (*A TU HORA EXPRESS, S.L.*) est un transporteur espagnol du dernier kilomètre basé à San Fernando de Henares (Madrid), avec des agences en Espagne continentale, notamment à Madrid, Barcelone, Valence, Séville, Malaga, Alicante, Murcie, Almería, Cadix, Castellón, Cordoue, Gérone, Grenade, Huelva, Jaén, Lérida et Tarragone. Il livre des commandes e-commerce et B2B via les services *Express 24h*, *Economy 48h*, *Masivo 72h*, *Reverse Logistics* (*Logística Inversa*) et *B2B Premium*. Tu peux consulter un colis sur [le portail de suivi d’A Tu Hora Express](https://mydelivery.atuhoraexpress.com/#/login-form-cli) ou coller ton numéro de suivi dans le champ ci-dessus.

## Numéros de suivi A Tu Hora Express et code postal de livraison

Cherche ton numéro de service ou de suivi dans l’e-mail d’expédition du marchand ou sur la page de commande. Les envois A Tu Hora Express utilisent trois longueurs entièrement numériques :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `0000004829173640` | Numéro de service A Tu Hora Express à 16 chiffres (`000000` + 10 chiffres). Demande ton code postal espagnol de livraison à 5 chiffres sur le suivi. |
| `4829173640` | Code court à 10 chiffres (complété par six zéros initiaux pour former le numéro de service à 16 chiffres). Demande aussi ton code postal de livraison à 5 chiffres. |
| `000000482917364000128013` | Code-barres complet à 24 chiffres : numéro de service à 16 chiffres + `001` + code postal espagnol de livraison à 5 chiffres (ici `28013`). Partage cette longueur de 24 chiffres avec [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost), [Austrian Post](carrier:austrian-post) et [Express One](carrier:express-one). |

**Pourquoi le code postal compte.** Sur [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli), les numéros à 10 et 16 chiffres n’affichent le détail du colis qu’avec le **code postal espagnol de livraison à 5 chiffres**. Le code-barres à 24 chiffres intègre déjà ce code postal dans ses cinq derniers chiffres et fonctionne donc sans saisie séparée. Pour d’autres formats espagnols et internationaux, consulte [les formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi A Tu Hora Express

A Tu Hora Express affiche chaque scan en espagnol, avec un code statut (`cod`) et une heure à l’heure de Madrid :

| A Tu Hora Express indique (code) | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Documentado` (`0`) | L’expéditeur a enregistré l’envoi électroniquement avant sa remise en agence. | « Étiquette créée » |
| `En tránsito` (`1`) | En transit entre agences sur le réseau espagnol d’A Tu Hora Express. | « En transit » |
| `En agencia distribución` (`11`) | Arrivé à l’agence locale espagnole desservant ton code postal. | « En transit » |
| `En reparto` (`2`) / `En realización` | En tournée avec le livreur local pour livraison aujourd’hui. | « En livraison » |
| `Incidencia - Pendiente nuevo reparto` (`3`), `Ausente`, `Dirección incorrecta o incompleta`, `Rechazado` | La livraison n’a pas pu aboutir (destinataire absent, adresse incomplète, colis refusé ou programmé pour une nouvelle tentative). | « Tentative de livraison » |
| `Incidencia - Falta parcial` (`3`), `Incidencia - No cumplimiento agencia` / `Incumplimiento de la agencia` | Incident opérationnel tel qu’un colis manquant dans un envoi multi-colis ou un défaut de livraison de l’agence. | « Problème signalé » |
| `Pendiente de realizar` (`5`) | Associé à un incident au même horodatage pour signaler qu’une nouvelle livraison est en attente ; c’est la ligne d’incident qui fixe l’étape. | *(conserve l’étape de l’incident)* |
| `Realizado con éxito` (`4`) / `Entregado con éxito` | Livré par A Tu Hora Express. | « Livré » |
| `Devuelto` | Renvoyé vers l’expéditeur. | « Retourné à l’expéditeur » |

## Services de livraison et informations publiées sur le site officiel

Sur [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/) et [atuhoraexpress.com](https://atuhoraexpress.com/), A Tu Hora Express présente cinq services principaux — *Express 24h*, *Economy 48h*, *Masivo 72h*, *Reverse Logistics* et *B2B Premium* — sur son réseau d’agences espagnol, avec un suivi accessible via [atuhoraexpress.com/tracking/](https://atuhoraexpress.com/tracking/) et [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli).

Le site d’A Tu Hora Express ne publie ni le nombre de tentatives de livraison, ni les limites de poids ou de dimensions, ni le délai de garde. Si ta commande provient de l’extérieur de l’Union européenne, elle est soumise à un droit de douane de 3 € par article lorsqu’elle vaut 150 € ou moins, ainsi qu’aux frais de gestion de l’Union de 2 € par article, sauf si la boutique les a déjà facturés à la commande ; voir [les colis retenus en douane](guide:customs).

## Colis A Tu Hora Express retardé, bloqué ou manquant

Comme le contrat de transport est conclu entre la boutique en ligne et A Tu Hora Express, contacte d’abord le marchand si un colis reste bloqué en `Incidencia`, présente une erreur d’adresse (`Dirección incorrecta o incompleta`) ou dépasse sa date de livraison prévue.

- **Incidents répétés ou `Pendiente nuevo reparto` :** confirme ton adresse, ton étage/porte et ton numéro de téléphone auprès du marchand et écris à `info@atuhoraexpress.com` avec ton code à 16 ou 24 chiffres et ton code postal de livraison.
- **Indiqué `Realizado con éxito` mais non reçu :** vérifie d’abord auprès de ton foyer, de tes voisins et du concierge (*portería*) de ton immeuble, puis contacte immédiatement le marchand et A Tu Hora Express.
- Pour la marche à suivre étape par étape, consulte [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating), [colis livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contacts A Tu Hora Express

Coordonnées officielles publiées sur [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/), [atuhoraexpress.com/aviso-legal/](https://atuhoraexpress.com/aviso-legal/) et [atuhoraexpress.com/ayudanos-a-mejorar/](https://atuhoraexpress.com/ayudanos-a-mejorar/) :

- **Téléphone :** `911 631 356` (Espagne).
- **E-mail clients :** `info@atuhoraexpress.com`.
- **Formulaire d’avis :** [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/).
- **E-mail protection des données :** `protecciondedatos@atuhoraexpress.com`.
- **Siège de Madrid :** Calle Artesanos 10, 28830 San Fernando de Henares (Madrid), Espagne (siège social légal de *A TU HORA EXPRESS, S.L.*, NIF `B19300748` : C/ Espliego 16, 19208 Alovera, Guadalajara).

> Méfie-toi des SMS ou messages WhatsApp demandant un petit paiement par carte pour reprogrammer une livraison A Tu Hora Express. Vérifie ton colis directement sur [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) ou auprès de `info@atuhoraexpress.com`. Voir [les arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis A Tu Hora Express avec Peek

[Peek](/) reconnaît automatiquement avec une confiance élevée tout code à 16 chiffres commençant par six zéros (`0000004829173640`, chiffres inventés) comme un envoi A Tu Hora Express. Un code-barres à 24 chiffres (`000000482917364000128013`) correspond à plusieurs transporteurs : Peek interroge A Tu Hora Express (avec le code postal contenu dans le code-barres), [Austrian Post](carrier:austrian-post), [InPost](carrier:inpost) et [Ciblex](carrier:ciblex) pour savoir s’ils le connaissent, puis [Express One](carrier:express-one) uniquement si aucun ne répond, et affiche « A Tu Hora Express a ce colis. » lorsque A Tu Hora Express répond. Pour un code à 10 chiffres (`4829173640`), appuie sur « Changer » lors de l’ajout ou sur « Changer de transporteur » sur l’écran du colis et choisis A Tu Hora Express (rechercher « A Tu Hora » le trouve aussi).

Pour les codes à 10 et 16 chiffres, Peek propose un champ facultatif « Code postal de livraison » accompagné de la mention « A Tu Hora Express n’affiche le colis qu’avec le code postal de livraison. ». Tant que tu ne l’as pas renseigné, l’écran du colis propose « Ajouter le code postal » et Peek suit le colis via des services de suivi, en vérifiant une fois par jour après une journée sans réponse. La saisie du code postal déclenche une vérification immédiate. Un code-barres à 24 chiffres contient déjà son code postal dans ses cinq derniers chiffres et n’en a donc pas besoin.

Quand A Tu Hora Express répond, Peek affiche l’étape (« Étiquette créée », « En transit », « En livraison », « Tentative de livraison », « Problème signalé », « Retourné à l’expéditeur », « Livré »), le libellé espagnol du scan avec sa ville, le jour de livraison prévu tant qu’A Tu Hora Express en indique un sur un envoi en cours, et le poids. Les codes statut déterminent l’étape ; Peek ne les affiche pas. Les scans sont datés à l’heure de Madrid et affichés à l’heure de ton appareil. Peek omet les coordonnées GPS associées aux scans, la barre de progression, la date de départ, la référence client, les noms de l’expéditeur et du destinataire, l’adresse de destination, le nombre de colis, le volume, les remarques, la preuve de livraison et les options de livraison ; le suivi d’A Tu Hora Express n’indique ni point de retrait ni nom de service.

Tant que le colis est ouvert dans Peek, ou le jour de sa livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où A Tu Hora Express affiche `En reparto` ou `En realización`, toutes les 2 minutes à partir de 6 h heure de Madrid, tant que le suivi d’A Tu Hora Express répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelle, il vérifie toutes les heures quelle que soit ta façon de le suivre, puis toutes les 6 heures après sept jours à « Étiquette créée ». Quand le suivi d’A Tu Hora Express ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Quand le scan le plus récent remonte à quatre jours et que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La page du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger A Tu Hora Express. » Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger A Tu Hora Express. »

Tu peux choisir entre les réglages d’alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek se limite au suivi des mises à jour et ne peut ni reprogrammer une livraison, ni modifier ton adresse, ni contacter A Tu Hora Express à ta place.

## Questions fréquentes sur le suivi A Tu Hora Express

### Pourquoi A Tu Hora Express demande-t-il mon code postal de livraison ?

A Tu Hora Express n’affiche un code à 10 ou 16 chiffres qu’avec son code postal espagnol de livraison à 5 chiffres, aussi bien sur [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) que dans Peek, où le champ est facultatif mais sans lequel A Tu Hora Express ne répond pas. Si la boutique t’a donné un code à 24 chiffres, les cinq derniers chiffres contiennent déjà le code postal.

### Que signifie `Realizado con éxito` dans le suivi A Tu Hora Express ?

`Realizado con éxito` (code statut `4`) signifie qu’A Tu Hora Express a enregistré l’envoi comme livré. Si tu ne l’as pas reçu, vérifie auprès de ton foyer, de tes voisins et du concierge, puis contacte le marchand et `info@atuhoraexpress.com`.

### Que signifie `Incidencia - Pendiente nuevo reparto` ?

Cela signifie qu’une tentative de livraison n’a pas pu aboutir et que le colis est programmé pour repasser en tournée (souvent accompagné de `Pendiente de realizar` au même horodatage). Confirme ton adresse et ton numéro de téléphone auprès du marchand si la situation se répète.

### Comment contacter A Tu Hora Express en Espagne ?

Appelle le `911 631 356`, écris à `info@atuhoraexpress.com` ou envoie le formulaire [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/) avec ton numéro de suivi et ton code postal de livraison à 5 chiffres.

:::sources
- [A Tu Hora Express : accueil (espagnol)](https://atuhoraexpress.com/) – transporteur espagnol du dernier kilomètre, agences, siège de Madrid, `911 631 356` et `info@atuhoraexpress.com`
- [A Tu Hora Express : accueil (anglais)](https://atuhoraexpress.com/home/) – Express 24h, Economy 48h, Masivo 72h, Reverse Logistics, B2B Premium et liste des agences
- [A Tu Hora Express : page de suivi](https://atuhoraexpress.com/tracking/) – point d’entrée du suivi sur le site
- [A Tu Hora Express : portail MyDelivery](https://mydelivery.atuhoraexpress.com/#/login-form-cli) – portail de recherche directe d’envoi
- [A Tu Hora Express : mentions légales](https://atuhoraexpress.com/aviso-legal/) – *A TU HORA EXPRESS, S.L.*, NIF `B19300748`, siège social à Alovera (Guadalajara) et `protecciondedatos@atuhoraexpress.com`
- [A Tu Hora Express : formulaire d’avis](https://atuhoraexpress.com/ayudanos-a-mejorar/) – formulaire de retour client
- [Universal Parcel Scraper : a-tu-hora-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/a-tu-hora-express) – détection 10/16/24 chiffres, exigence facultative de code postal à 5 chiffres, codes statut espagnols (`0`, `1`, `11`, `2`, `3`, `4`, `5`), heure locale `Europe/Madrid`, date de livraison prévue, poids et champs écartés
:::
