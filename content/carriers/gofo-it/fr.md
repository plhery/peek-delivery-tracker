---
title: Suivi GOFO Italy : numéros GFIT, statuts et réclamations
description: Suivre un colis GOFO en Italie : format des numéros GFIT et CIIT, signification des statuts en italien, recours AGCOM et suivi avec Peek.
slug: suivi-gofo-italy
published: 2026-11-21
updated: 2026-11-21
---

GOFO Italy est le réseau italien de GOFO, un transporteur de colis présent dans plusieurs pays, qui assure des livraisons en Italie. La liste des transporteurs de Peek le connaît aussi sous le nom de CIRRO Parcel Italy. Ses numéros de suivi commencent par `GFIT` ou `CIIT` : suis ton colis sur la page de suivi italienne de GOFO, sur gofo.com, ou colle le numéro dans le champ ci-dessus. Tu n'as pas encore de numéro ? Consulte [comment trouver ton numéro de suivi](guide:find-tracking-number).

## Numéro de suivi GOFO Italy : à quoi il ressemble

Voici les formats que Peek classe sous GOFO Italy, en majuscules ou en minuscules :

| Format | Exemple inventé |
| --- | --- |
| `GFIT` suivi de 13 chiffres | `GFIT4829105736284` |
| `GFIT` suivi de 14 chiffres (le dernier étant un chiffre de contrôle) | `GFIT48291057362848` |
| `CIIT` suivi de 13 chiffres | `CIIT4829105736284` |
| `CIIT` suivi de 14 chiffres | `CIIT48291057362841` |

Les autres réseaux de GOFO suivent le même principe, avec leur propre code pays après `GF` ou `CI`. Les numéros `GFFR` et `CIFR` appartiennent à [GOFO France](carrier:gofo-fr), `GFNL` à GOFO Pays-Bas et `GFES` à GOFO Espagne. Les numéros `GFUS` à 14 chiffres, `GF` à 13 chiffres ou `CR` à 12 chiffres relèvent de [GOFO Express](carrier:gofo) aux États-Unis, un réseau distinct, et `GFCA` à 14 chiffres de GOFO Canada. Un numéro commençant par `GFIT` avec 12 ou 15 chiffres, ou un `GFIT` à 14 chiffres dont le dernier chiffre est erroné, n'est pas reconnu par Peek comme un numéro GOFO Italy : vérifie qu'il n'y a pas de faute de frappe. Plus de détails dans [formats de numéros de suivi](guide:tracking-number-formats).

## Statuts de suivi GOFO Italy : signification des termes italiens

Peek lit le suivi italien de GOFO : les scans apparaissent donc dans les mots italiens de GOFO, quelle que soit ta langue. Voici les plus courants, dans l'ordre habituel :

| Ce qu'indique GOFO | Ce que cela signifie pour toi |
| --- | --- |
| `Il pacco è in fase di preparazione da parte del mittente e ci sarà presto consegnato` | L'expéditeur prépare le colis et va bientôt le remettre à GOFO. |
| `Registrato presso il centro di trasferimento` | Scanné dans un centre de transfert GOFO. |
| `Il pacco ha lasciato il centro di smistamento` | Parti d'un centre de tri GOFO. |
| `Arrivato alla struttura di destinazione regionale GOFO` | Arrivé au centre régional GOFO de ta zone. |
| `Preparazione alla consegna` | En préparation au dépôt avant le départ du livreur. |
| `Il corriere è fuori per la consegna` | En tournée avec le livreur. |
| `Consegna fallita, tipo:Destinatario assente` | Non livré : personne n'était présent. |
| `Consegna fallita, tipo:L'indirizzo è errato` | Non livré : l'adresse est erronée. |
| `Il pacco è stato restituito al centro di smistamento` | Revenu au centre de tri GOFO après un échec de livraison. |
| `Consegnato` | Livré. |

Deux lignes prêtent souvent à confusion. Tant qu'aucun scan ne suit la première, GOFO n'a pas encore le colis, et Peek affiche « Étiquette créée ». Quant à `Il pacco è stato restituito al centro di smistamento`, cela ne signifie pas que le colis repart chez le vendeur : il est revenu au centre de tri de GOFO, et Peek l'affiche comme « Problème signalé ». Une livraison apparaît sous le seul mot `Consegnato`, dans toutes les langues : la ligne de GOFO peut en dire davantage (notamment sur le destinataire), ce que Peek laisse de côté.

Le suivi de GOFO regroupe aussi les colis en grandes catégories. `Avviso` signale un problème relevé par GOFO, affiché par Peek comme « Problème signalé » ; à lui seul, il n'implique pas qu'une tentative de livraison ait eu lieu. `Restituito` désigne un colis retourné à l'expéditeur, affiché comme « Retourné à l’expéditeur ».

Si [YunExpress](carrier:yunexpress) a remis ton colis à GOFO Italy, le suivi de YunExpress peut relayer les scans de GOFO en anglais : `Preparing for delivery` au dépôt avant la tournée, et `Package departed from sorting center` à côté de l'italien `Il pacco ha lasciato il centro di smistamento`.

## Colis GOFO en retard, manqué, perdu ou endommagé

**Aucune mise à jour.** Peek continue d'interroger GOFO. Si quatre jours s'écoulent sans nouveau scan avant la mise en livraison, Peek le signale (« Aucune mise à jour depuis … » avec le jour du dernier scan) et propose un lien vers la page d'aide de GOFO Italy (« Demande de l’aide à GOFO Italy »). Voir aussi [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**Livraison manquée.** Après une ligne `Consegna fallita` sous le code d'alerte de GOFO — qu'il s'agisse de `Destinatario assente`, de `L'indirizzo è errato` ou d'un autre motif après `tipo:` — Peek affiche « Tentative de livraison ». La suite dépend de GOFO : consulte [livraison manquée](guide:missed-delivery) pour savoir quoi vérifier.

**En retard.** Selon les règles européennes de protection des consommateurs, le vendeur doit livrer dans un délai de 30 jours, sauf délai différent convenu. S'il ne le fait pas, accorde-lui un délai supplémentaire raisonnable ; si celui-ci expire à son tour, tu peux annuler la commande et être remboursé.

**Marqué `Consegnato` mais rien reçu.** Le vendeur est responsable de la livraison : c'est à lui de contacter le transporteur et de prouver que le colis a bien été livré, faute de quoi il doit renvoyer l'article ou te rembourser. Pour ce qu'il faut vérifier autour de chez toi d'abord, voir [colis livré mais non reçu](guide:delivered-not-received).

**Endommagé.** Le vendeur répond de tout dommage entre l'expédition et la réception du colis ; vérifie donc l'état du colis devant le livreur si possible. Si le carton est visiblement abîmé, l'AGCOM (le régulateur postal italien) indique que tu peux le refuser ou l'accepter avec réserve. Plus de détails dans [colis endommagé](guide:damaged-parcel).

## Comment porter réclamation pour une livraison GOFO Italy

Commence par le vendeur qui a expédié le colis, car c'est lui qui répond de la livraison. Pour une réclamation contre le transporteur lui-même, l'AGCOM, l'autorité italienne des communications compétente pour le secteur postal, fixe une procédure pour les opérateurs postaux — les colis jusqu'à 31,5 kg relevant des services postaux :

1. Envoie une réclamation écrite à l'opérateur via le formulaire de son site ou un formulaire demandé par e-mail. Il doit répondre dans un délai de 45 jours.
2. Si la réponse ne te satisfait pas ou n'arrive pas sous 45 jours, demande une conciliation à l'opérateur. Elle se conclut dans un délai de 60 jours.
3. En cas d'échec, tu disposes de 90 jours pour saisir l'AGCOM afin de trancher le litige via son Formulario CP. L'AGCOM statue sous 150 jours et sa décision s'impose aux deux parties.

L'AGCOM peut ordonner des remboursements ou des indemnités prévus par les règles, mais pas de dommages-intérêts : pour cela, et à tout moment, tu peux saisir le juge ordinaire. Si l'opérateur ne répond ni à la réclamation ni à la demande de conciliation, signale-le sur le Portale delle segnalazioni de l'AGCOM. Le centre de contact de l'AGCOM répond du lundi au vendredi, de 10 h à 14 h, au `800.185060` depuis un fixe ou au `081.750750` depuis un mobile ou l'étranger.

## Service client GOFO Italy

La page d'aide de GOFO Italy se trouve sur son site italien, gofo.com, et Peek la relie via « Demande de l’aide à GOFO Italy » après quatre jours sans nouveau scan avant la mise en livraison. La société exploitante, GOFO Italia s.r.l., a son siège social Via Juan Manuel Fangio 11, 20045 Lainate (MI). Garde ton numéro `GFIT` ou `CIIT` et ton numéro de commande à portée de main. L'AGCOM rappelle également que tout opérateur postal doit proposer aux utilisateurs des canaux de communication gratuits.

## Suivre un colis GOFO Italy avec Peek

[Peek](/) reconnaît directement les numéros `GFIT` et `CIIT` (avec vérification du chiffre de contrôle pour les `GFIT` à 14 chiffres). Tu peux aussi coller un lien vers la page de suivi italienne de GOFO (sur gofo.com ou gofoexpress.it, avec `id=` et le numéro).

Tant que tu gardes le colis ouvert à l'écran ou le jour de la livraison avec les notifications activées, et que le suivi de GOFO répond, Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes dès 6 h (heure italienne) le jour où GOFO scanne le colis en livraison ; sinon, toutes les heures. Lorsque ce sont des services de suivi qui répondent, Peek vérifie toutes les heures (toutes les 3 heures après une journée sans nouvelle), et jusqu'à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées. Après deux jours sans nouvelle (12 heures tant que le colis affiche « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures.

Peek affiche le statut, l'historique avec le lieu indiqué par GOFO pour chaque scan, l'heure de livraison et ces lieux sur une carte. Lorsque GOFO indique seulement un code de dépôt ou le pays, le scan apparaît sans lieu. Les horaires sont lus sur l'heure italienne et convertis dans ton fuseau horaire ; sur peektracker.com et dans les applications iPhone et Android, l'historique se termine par « Heures dans ton fuseau horaire : » suivi de son nom. Tu peux activer des notifications pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Tant que GOFO n'affiche aucun premier scan, Peek indique « En attente de GOFO Italy » et continue d'interroger le suivi. Si GOFO ne connaît toujours pas le numéro deux jours plus tard et qu'aucune autre source n'a répondu, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » puis vérifie une fois par jour, et une fois par semaine une fois sept jours passés depuis l'ajout. Si GOFO connaît l'envoi sans l'avoir encore scanné, Peek continue de vérifier avec « En attente de GOFO Italy » sans te demander de vérifier le numéro. Peek affiche la date de livraison prévue par GOFO jusqu'à ce que le colis soit en livraison, livré, retourné ou prêt en point de retrait ; il n'affiche pas le poids et laisse de côté les photos de livraison et les avis de GOFO. Il ne peut contacter ni GOFO ni le vendeur à ta place.

## Questions sur le suivi GOFO Italy

### Où est mon colis GOFO ?

La dernière ligne du suivi l'indique. `Arrivato alla struttura di destinazione regionale GOFO` signifie qu'il est au centre régional GOFO de ta zone, `Preparazione alla consegna` qu'il est préparé pour le livreur et `Il corriere è fuori per la consegna` qu'il est en tournée. Si seule la ligne de préparation chez l'expéditeur apparaît, celui-ci ne l'a pas encore remis à GOFO.

### GOFO Italy est-il fiable ?

La société italienne de GOFO est GOFO Italia s.r.l., domiciliée à Lainate (province de Milan). Dans sa Determina n. 12/26/DSP du 2 septembre 2026, l'AGCOM indique lui avoir notifié le 17 juin 2026 trois griefs : l'exercice d'activités postales relevant du service universel sans la licence individuelle requise, l'absence de charte de services (Carta dei servizi) et l'absence de procédures de règlement des litiges pour les utilisateurs. Selon la décision, l'entreprise a reconnu les griefs relatifs à la charte de services et aux procédures de règlement des litiges et a payé l'amende correspondante à tarif réduit, entraînant le classement de cette partie de la procédure ; elle a contesté le grief relatif à l'autorisation, qui devait suivre son cours ordinaire.

### GOFO et CIRRO Parcel sont-ils la même entreprise ?

En Italie, pour le suivi, oui : la liste des transporteurs de Peek donne aussi à GOFO Italy le nom de CIRRO Parcel Italy. Un suivi mentionnant seulement « Cirro » peut toutefois renvoyer au réseau italien, français ou néerlandais de GOFO.

### Que faire si mon colis GOFO est perdu ?

Contacte le vendeur. Selon les règles de l'UE, il répond de la livraison jusqu'à ce que le colis te parvienne : s'il ne peut prouver la livraison, il doit te renvoyer l'article ou te rembourser. Si le transporteur est en cause, la procédure de réclamation AGCOM ci-dessus s'applique. Plus de détails dans [colis perdu](guide:lost-parcel).

:::sources
- [AGCOM : Determina n. 12/26/DSP](https://www.agcom.it/sites/default/files/provvedimenti/determina/2026/Determina%20arch%20parziale%2012_26_DSP%20-%20Gofo%20pdf.pdf) – GOFO Italia s.r.l., siège à Lainate, procédure AGCOM de 2026 et amende payée en mesure réduite (2 septembre 2026)
- [AGCOM : FAQ contenzioso tra utenti e operatori postali](https://www.agcom.it/faq-contenzioso-tra-utenti-e-operatori-postali) – réclamation sous 45 jours, conciliation sous 60, Formulario CP sous 90, décision sous 150, colis jusqu'à 31,5 kg, colis endommagés, canaux gratuits, centre de contact
- [Your Europe : Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – livraison sous 30 jours, délai supplémentaire, responsabilité des dommages jusqu'à réception
- [Your Europe : FAQs, shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_en.htm) – preuve de livraison à la charge du vendeur, vérification à la réception
- [Universal Parcel Scraper : GOFO Italy](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo-it) – formats `GFIT`/`CIIT` et chiffre de contrôle `gofo-europe`, liens, dictons italiens et catégories, `Consegna fallita` sous code `206`, CIRRO Parcel Italy, champs affichés et écartés
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – autres réseaux GOFO, relais anglais de YunExpress, fin d'affichage de la date prévue (`GOFO_ENDED_STAGES`)
:::
