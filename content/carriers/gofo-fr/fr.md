---
title: GOFO France suivi colis : numéros GFFR, statuts et aide
description: Suivi colis GOFO France (CIRRO Parcel France) : numéros GFFR et CIFR, statuts, délais (3 à 5 jours ouvrables), 3 tentatives et service client +33 9 70 70 90 65.
slug: gofo-france-suivi
published: 2026-10-11
updated: 2026-11-27
---

Pour le suivi d'un colis GOFO France (aussi appelé CIRRO Parcel France), colle ton numéro commençant par `GFFR` ou `CIFR` (13 ou 14 chiffres) — ou une référence `PK` à 20 chiffres — sur gofo.com ou dans le champ ci-dessus. GOFO annonce en général une livraison sous 3 à 5 jours ouvrables après l'arrivée en France, fait jusqu'à 3 tentatives puis conserve le colis 14 jours à l'entrepôt ; pour joindre le service client GOFO France, appelle le +33 9 70 70 90 65 ou écris à cs@fr.gofo.com.

## Numéro de suivi GOFO : à quoi il ressemble

Voici les formes que Peek attribue à GOFO France, en majuscules ou non :

| Forme | Exemple inventé |
| --- | --- |
| GFFR et 13 chiffres | `GFFR1234567890123` |
| GFFR et 14 chiffres | `GFFR12345678901235` |
| CIFR et 13 chiffres | `CIFR1234567890123` |
| CIFR et 14 chiffres | `CIFR12345678901234` |

Une référence de la boutique peut remplacer le numéro : `PK` suivi de 20 chiffres, parfois écrite avec des tirets, comme `PK-1234567890123456789-0` (inventée). Le suivi de GOFO France répond aussi pour ces références, et GOFO les garde à côté de son propre bordereau `GFFR` ou `CIFR`.

Les autres réseaux de GOFO suivent le même modèle, avec leur pays après `GF` ou `CI`. Les numéros `GFIT` ou `CIIT` appartiennent à GOFO Italie, `GFNL` ou `CINL` à GOFO Pays-Bas et `GFES` ou `CIES` à GOFO Espagne. Un `GFUS` suivi de 14 chiffres, un `GF` suivi de 13 chiffres seulement ou un `CR` suivi de 12 chiffres appartient à GOFO Express aux États-Unis, un réseau à part, et un `GFCA` suivi de 14 chiffres à GOFO Canada. Aucun n'est un numéro de GOFO France. Pour comparer les formes : [à quel transporteur appartient un numéro](guide:tracking-number-formats).

## Suivi GOFO : ce que veut dire chaque statut

Peek lit le suivi de GOFO en français et garde ses mots tels quels. Les étapes les plus courantes, dans l'ordre où un colis les rencontre :

| Les mots de GOFO | Ce que ça veut dire pour toi |
| --- | --- |
| `Votre colis est en cours de préparation par l'expéditeur` | La boutique prépare le colis ; GOFO ne l'a pas encore. |
| `Expédition en transit vers GOFO` | Le colis est en route vers GOFO, qui ne l'a pas encore reçu. |
| `Arrivé au site de prise en charge` | Il est arrivé au site GOFO qui le prend en charge : c'est le premier scan de GOFO. |
| `Votre colis a été pris en charge au centre de tri` | Il a été scanné dans un centre de tri GOFO. |
| `Colis en cours d'acheminement` | Il voyage entre deux sites GOFO. |
| `Votre colis est arrivé à l’agence régionale GOFO de destination` | Il est à l'agence régionale GOFO de ta zone, qui va le livrer. |
| `Le chauffeur est en route pour la livraison` | Il est parti avec le livreur. |
| `Échec de la livraison, motif : Le destinataire n'est pas chez lui` | Pas livré : personne n'était là. |
| `Livré` | Ton colis est livré. |

Les deux premières lignes trompent souvent : tant que ni `Arrivé au site de prise en charge` ni le scan du centre de tri n'apparaissent, GOFO n'a pas le colis, et Peek l'affiche comme « Étiquette créée ». Une livraison s'affiche `Livré` tout court : la ligne de livraison de GOFO peut en dire plus, y compris des détails sur le destinataire, que Peek laisse de côté.

Le suivi de GOFO range aussi les colis dans de grandes catégories. `Alerte` signale un souci repéré par GOFO, que Peek affiche comme « Problème signalé » ; à elle seule, elle ne veut pas dire qu'une livraison a été tentée. `Retourné` désigne un colis qui repart chez l'expéditeur, affiché « Retourné à l’expéditeur ».

## Colis GOFO en retard, manqué, perdu ou abîmé

**Combien de temps ?** Selon GOFO, un colis est en général livré trois à cinq jours ouvrables après son arrivée en France, et un à deux jours ouvrables après que son suivi indique « En cours de livraison » (le terme de GOFO, pas un statut de Peek) ; GOFO ne peut pas donner d'heure de livraison exacte. S'il vient d'un pays hors de l'UE, il doit d'abord être dédouané : tant qu'il n'est pas enregistré dans un entrepôt GOFO, le suivi peut ne pas bouger. Pour les droits, la TVA et les frais de traitement de 2 € par article dus depuis le 1er novembre 2026 (un article regroupe les produits de même code de marchandise et de même pays d'origine : deux t-shirts n'en font qu'un), lis [colis bloqué en douane](guide:customs).

**Le suivi ne bouge pas.** Peek continue d'interroger GOFO. Si un colis reste quatre jours sans nouveau scan avant de partir en livraison, sa page dans Peek le signale et renvoie vers la page d'aide de GOFO France (« Demande de l’aide à GOFO France »). GOFO, lui, demande à être contacté si rien ne change pendant plus de cinq jours ouvrables en France ou dans l'UE. Voir aussi [pourquoi un suivi de colis ne bouge plus](guide:tracking-not-updating).

**Livraison manquée.** Après `Échec de la livraison, motif : Le destinataire n'est pas chez lui`, Peek affiche « Tentative de livraison » ; un échec avec un autre motif après `motif :` s'affiche « Problème signalé ». GOFO fait jusqu'à trois tentatives de livraison ; si elles échouent, il garde le colis 14 jours à l'entrepôt : contacte-le pendant ce délai pour organiser une nouvelle livraison, sinon le colis repart chez l'expéditeur. GOFO ne propose ni point relais ni changement de date, ni retour à ta demande : pour renvoyer un colis dont tu ne veux plus, passe par la boutique. En ton absence, le livreur peut aussi laisser le colis dans ta boîte aux lettres ou chez un voisin : préviens GOFO à l'avance si tu ne le veux pas. Pour changer d'adresse, passe par le formulaire envoyé depuis cs@fr.gofo.com, avant que le colis soit confié au livreur ; si tu ne l'as pas reçu ou si le lien a expiré, contacte vite GOFO. Lis aussi [les livraisons manquées](guide:missed-delivery).

**En retard.** Selon les règles européennes de protection des consommateurs, la boutique doit te livrer dans les 30 jours, sauf si vous avez convenu d'un autre délai. Sinon, accorde-lui un délai supplémentaire raisonnable ; s'il passe lui aussi, tu peux annuler la commande et te faire rembourser.

**Marqué `Livré` mais pas reçu.** Regarde d'abord la preuve de livraison sur la page de suivi de GOFO : elle indique où le colis a été déposé, parfois avec une photo (Peek ne l'affiche pas). Vérifie ta boîte aux lettres, demande aux voisins ou au gardien, puis signale-le vite à GOFO ; des photos de l'extérieur et de l'entrée de ton domicile ou de ton immeuble peuvent accélérer son enquête, sans être obligatoires. C'est la boutique qui est responsable de la livraison de ta commande : à elle de contacter le transporteur et de prouver que le colis a bien été livré, et si elle ne peut pas, de t'envoyer un article identique ou de te rembourser. Plus d'infos dans [colis livré mais non reçu](guide:delivered-not-received).

**Abîmé.** La boutique est responsable de tout dommage, de l'expédition jusqu'à la réception, même s'il est survenu pendant le transport. Quand tu peux, vérifie le colis en présence du livreur. Pour un emballage très abîmé ou des articles manquants, GOFO renvoie lui aussi vers la boutique. Plus d'infos dans [colis abîmé](guide:damaged-parcel).

## Service client GOFO France

La page d'aide de GOFO France se trouve sur son site français, gofo.com, et Peek y renvoie avec « Demande de l’aide à GOFO France » quand un colis reste quatre jours sans nouveau scan avant de partir en livraison. Au 10 novembre 2026, elle donne pour les destinataires :

- **Téléphone :** +33 9 70 70 90 65, du lundi au vendredi de 9 h à 18 h, hors jours fériés ;
- **E-mail :** cs@fr.gofo.com.

Contacte GOFO pour la livraison elle-même : preuve de livraison, nouvelle tentative, adresse. Pour une commande en retard, perdue ou abîmée, commence par la boutique qui l'a envoyée, puisque c'est elle qui répond de la livraison. Garde ton numéro `GFFR` ou `CIFR` et ton numéro de commande sous la main.

## Suivre un colis GOFO France avec Peek

[Peek](/) reconnaît tout seul les numéros `GFFR` et `CIFR`. Tu peux aussi coller un lien de la page de suivi française de GOFO (sur gofo.com ou gofoexpress.fr, avec `id=` et le numéro). Une référence `PK` marche aussi : Peek la demande à GOFO France et nomme le transporteur dès que GOFO connaît le numéro.

Peek lit le suivi de GOFO en français jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes une fois le colis en livraison (le jour même, dès 6 h, heure locale, et tant que c'est le suivi de GOFO qui répond ; via des services de suivi, toutes les 15 minutes). Après deux jours sans nouvelles, ou après 12 heures si le colis en est encore à « Étiquette créée » ou à « Pris en charge », Peek ne vérifie plus que toutes les heures. Il affiche le statut, l'historique avec le lieu que GOFO donne pour chaque étape, l'heure de livraison et ces lieux sur une carte, et peut t'envoyer des notifications : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Quand GOFO ne donne que le code d'un dépôt ou le pays, l'étape s'affiche sans lieu. GOFO donne l'heure locale de chaque étape, et Peek la convertit dans ton fuseau horaire ; sur peektracker.com et dans les applications iPhone et Android, la dernière ligne sous l'historique le nomme (« Heures dans ton fuseau horaire : … »). Tant que GOFO n'a listé aucune étape, Peek affiche « En attente de GOFO France » et continue de demander. Si deux jours plus tard GOFO ne connaît toujours pas le numéro et qu'aucune autre source ne répond pour lui, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » et le vérifie ensuite une fois par jour, puis une fois par semaine quand une semaine s'est écoulée depuis son ajout. Si GOFO a déjà enregistré l'envoi sans l'avoir encore scanné, il connaît le numéro : Peek continue de vérifier et affiche « En attente de GOFO France », sans te demander de contrôler le numéro. Peek affiche la date de livraison prévue par GOFO jusqu'à ce que le colis soit livré ou retourné ; il n'affiche pas le poids et laisse de côté les photos de livraison et les avis de GOFO. Il ne peut pas contacter GOFO ni la boutique à ta place.

## Questions sur le suivi GOFO

### Comment contacter le service client GOFO France ?

Appelle le +33 9 70 70 90 65 (du lundi au vendredi de 9 h à 18 h, hors jours fériés) ou écris à cs@fr.gofo.com avec ton numéro `GFFR`/`CIFR` pour une preuve de livraison, une nouvelle tentative ou un changement d'adresse avant la tournée ; pour un retard, une perte ou un remboursement, contacte d'abord la boutique.

### Que veut dire « Expédition en transit vers GOFO » ?

Le colis est en route vers GOFO, qui ne l'a pas encore scanné (`Arrivé au site de prise en charge` ou `Votre colis a été pris en charge au centre de tri`). Tant que ce premier scan n'apparaît pas, Peek affiche « Étiquette créée ».

### GOFO France, c'est une vraie entreprise ?

Oui. GOFO FRANCE est une SAS française inscrite au répertoire Sirene de l'INSEE sous le Siren 978 559 839, active depuis mai 2023, avec son siège à Marly-la-Ville (Val-d'Oise). Elle livre en France (selon GOFO, en général sous 3 à 5 jours ouvrables après l'arrivée en France, avec jusqu'à 3 tentatives et 14 jours de garde à l'entrepôt) et a un service client sur gofo.com.

### GOFO et CIRRO Parcel, c'est pareil ?

En France, pour le suivi, oui : GOFO France figure aussi sous le nom CIRRO Parcel France dans Peek, avec des numéros `GFFR` et `CIFR` (les réseaux d'Italie, des Pays-Bas et d'Espagne utilisent `GFIT`/`CIIT`, `GFNL`/`CINL` et `GFES`/`CIES`).

### Que faire si mon colis GOFO est perdu ?

Contacte la boutique : GOFO renvoie les réclamations pour perte vers l'expéditeur, et selon les règles européennes le vendeur répond de la livraison jusqu'à la réception (renvoi ou remboursement s'il ne peut pas prouver la livraison). Plus d'infos dans [colis perdu](guide:lost-parcel).

:::sources
- [GOFO France : Service client](https://www.gofo.com/fr/contact-us/) – téléphone, e-mail, horaires ; FAQ : délais, en cours de livraison, pas d'heure exacte, dédouanement, suivi figé, tentatives, entrepôt, adresse, lien expiré, pas de retour, preuve de livraison, photos pour l'enquête, perte et dommages
- [Commission européenne : EU Customs Reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – frais de traitement de 2 € depuis le 1er novembre 2026
- [Douane : Mesures e-commerce européennes](https://www.douane.gouv.fr/fiche/mesures-e-commerce-europeennes) – frais de traitement de 2 € par article
- [INSEE : Avis de situation au répertoire Sirene](https://avis-situation-sirene.insee.fr/) – GOFO FRANCE, Siren 978 559 839, SAS, active depuis le 26 mai 2023, siège à Marly-la-Ville
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – livraison sous 30 jours, délai supplémentaire, dommages jusqu'à la réception, ce qu'est un article de marchandise
- [Your Europe : FAQ, expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_fr.htm) – la boutique prouve la livraison, vérifier le colis en présence du livreur
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formes des numéros, références PK, liens, étapes et catégories en français, CIRRO Parcel France, ce que Peek affiche et laisse de côté
:::
