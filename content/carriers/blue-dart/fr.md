---
title: Suivi Blue Dart : numéros de lettre de transport et statuts
description: Suivre un envoi Blue Dart : format du numéro à 11 chiffres, sens des statuts comme Shipment Further Connected et suivi avec Peek.
slug: suivi-blue-dart
published: 2026-11-22
updated: 2026-11-22
---

Blue Dart est un transporteur indien : le catalogue de Peek le répertorie pour l'Inde et lit ses scans à l'heure indienne. Un envoi Blue Dart est identifié par un numéro de lettre de transport (*waybill*) à 11 chiffres fourni par l'expéditeur. Pour une commande en ligne, il peut figurer par exemple dans l'e-mail d'expédition de la boutique ou sur ta page de commande. Colle-le dans le champ ci-dessus ou saisis-le sur le suivi de Blue Dart sur bluedart.com. Peek n'ayant pas pu lire le site de Blue Dart pour cette page, ce qui suit repose sur ce que le code de Peek connaît du suivi de Blue Dart.

## Numéro de suivi Blue Dart : à quoi il ressemble

Peek connaît un seul format pour Blue Dart : 11 chiffres, dont le dernier est une clé de contrôle calculée à partir des dix premiers. Avec des chiffres inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `48291057360` | 11 chiffres avec une clé de contrôle valide : le format d'un numéro Blue Dart. Les réseaux GLS, PostLogistics, Japan Post, Mainfreight et d'autres utilisent aussi 11 chiffres, donc le format seul ne suffit pas à désigner Blue Dart. |
| `48291057361` | Le même format avec une mauvaise clé de contrôle. Peek ne propose pas Blue Dart pour ce numéro : vérifie s'il y a une faute de frappe. |
| `4829105736` | 10 chiffres : ce n'est pas un numéro Blue Dart. Si tu choisis Blue Dart pour ce numéro, Peek indique que Blue Dart « n’émet pas de numéros comme celui-ci ». Même chose pour 12 chiffres ou un numéro contenant des lettres. |

Les numéros à 11 chiffres d'Aramex et le courrier recommandé de Japan Post utilisent la même clé de contrôle : un numéro qui passe la vérification peut donc leur appartenir. Vérifie quel transporteur figure dans le message de la boutique. Les espaces et les tirets n'ont pas d'importance dans Peek. Le suivi de Blue Dart permet aussi de chercher par d'autres références, mais Peek a besoin du numéro de lettre de transport (*waybill*). Tu ne le trouves pas ? Consulte [où trouver son numéro de suivi](guide:find-tracking-number).

## Statuts de suivi Blue Dart : ce qu'ils signifient

Le suivi de Blue Dart affiche l'état actuel de l'envoi puis ses scans, du plus récent au plus ancien, chacun avec un lieu, une date et une heure. Voici les libellés que Peek connaît sur le suivi de Blue Dart, à peu près dans l'ordre où un envoi les rencontre :

| Ce qu'affiche Blue Dart | Ce que ça veut dire pour toi | Ce qu'affiche Peek |
| --- | --- | --- |
| `Pickup Employee Is Out To P/U Shipment` | Un coursier Blue Dart est en route pour collecter (*P/U*) l'envoi chez l'expéditeur. Blue Dart ne l'a pas encore en main. | « Étiquette créée » |
| `Shipment Picked Up` | Blue Dart a récupéré l'envoi chez l'expéditeur. | « Pris en charge » |
| `Shipment Arrived`, `Shipment Arrived At Hub` | L'envoi est arrivé dans une agence ou un centre de tri Blue Dart, dont le nom figure comme lieu du scan. | « En transit » |
| `Paper Work Inscan` | Scanné dans un centre de tri entre son arrivée et la correspondance suivante. | « En transit » |
| `Clubbed Canvas Bag Scan` | Scanné et regroupé dans un sac de transport avec d'autres envois. | « En transit » |
| `Shipment Further Connected` | Expédié depuis ce site vers l'étape suivante de son trajet. | « En transit » |
| `Delivery Scheduled For Next Working Day` | Arrivé à l'agence de livraison, mais après l'heure limite du jour : il partira en tournée le jour ouvré suivant. | « En transit » |
| `Shipment Out For Delivery` | En tournée de livraison. | « En livraison » |
| `Cnee Refused Id/Otp Not Shared-Incorrect` | Non livré à la suite d'un refus du destinataire (*Cnee*, *consignee*) ou d'une pièce d'identité ou d'un code à usage unique (*OTP*) non communiqué ou incorrect. | « Tentative de livraison » |
| `Delivery Delayed`, `Delay Caused Beyond Control` | La livraison a du retard ; le second libellé précise que la cause est indépendante de Blue Dart. | « Problème signalé » |
| `Shipment Delivered` | Livré. | « Livré » |
| `Shipment Returned Back To Shipper` | L'envoi est retourné chez l'expéditeur. Blue Dart l'affiche comme statut final une fois le retour terminé. | « Retourné à l’expéditeur » |

Un libellé que Peek ne connaît pas encore s'affiche tel que Blue Dart l'écrit et compte comme « En transit », sauf si le colis est déjà en livraison ou à une étape ultérieure : son statut reste alors inchangé. Plus de détails dans [ce que veulent dire les statuts de suivi](guide:tracking-statuses).

## Livraison Blue Dart en Inde : pièce d'identité et code OTP

Peek n'ayant pas pu vérifier les règles de livraison de Blue Dart — comme le nombre de tentatives ou la durée de garde d'un envoi non livré — cette page n'en indique aucune.

**Pièce d'identité ou code OTP.** Le libellé `Cnee Refused Id/Otp Not Shared-Incorrect` montre qu'une livraison Blue Dart peut exiger une pièce d'identité ou un code à usage unique (*OTP*). Si tu as reçu un code pour cet envoi, garde-le sous la main le jour de la livraison. Si cette ligne apparaît alors que tu n'as rien refusé, demande à la boutique d'organiser une nouvelle livraison. Vois [livraison manquée](guide:missed-delivery) et [colis non récupéré](guide:uncollected-parcel).

Un message à propos d'une livraison manquée te demande de payer ou de partager un code via un lien ? Consulte [SMS frauduleux de livraison](guide:delivery-scam-texts).

## Envois Blue Dart : transporteur partenaire et douane

**Un numéro Blue Dart.** Peek ne connaît aucun transporteur auquel Blue Dart confierait la suite d'un envoi, en Inde ou à l'étranger. Si la boutique te fournit aussi le numéro d'un autre transporteur, ajoute-le séparément.

**Envoyé avec ICC Worldwide.** Le suivi d'ICC Worldwide peut citer Blue Dart à côté d'un numéro Blue Dart. Peek ne poursuit l'envoi chez Blue Dart que si ce numéro comporte 11 chiffres : il interroge alors Blue Dart avec ce numéro et, dès que Blue Dart publie un scan au moins aussi récent que le dernier d'ICC Worldwide, suit l'envoi chez Blue Dart tout en conservant l'historique d'ICC Worldwide. Avec tout autre format, Peek reste chez ICC Worldwide. Ajoute l'envoi avec son numéro ICC Worldwide, puis appuie sur « Changer » et recherche ICC Worldwide : Peek ne le propose pas automatiquement d'après le numéro.

**Douane.** Le code de Peek ne connaît Blue Dart qu'en Inde et n'a pas pu vérifier le site de Blue Dart pour les envois internationaux. Pour un colis bloqué en douane, vois [colis retenu en douane](guide:customs).

## Suivi Blue Dart bloqué, retardé ou retourné

Pour un remboursement ou un nouvel envoi, ton interlocuteur est la boutique : c'est elle qui a réservé Blue Dart.

**Aucun scan pour l'instant.** Quand un numéro n'a pas encore de trace, le suivi de Blue Dart répond `There is no information on the Waybill/Reference Number/Order Number currently.` Peek continue de vérifier. Vérifie les 11 chiffres dans le message de la boutique ; si rien n'apparaît après quelques jours, demande-lui si elle a bien remis l'envoi.

**Bloqué sur `Pickup Employee Is Out To P/U Shipment`.** Blue Dart n'a pas encore collecté l'envoi chez l'expéditeur. Demande à la boutique quand elle prévoit la remise.

**`Delivery Delayed` ou `Delay Caused Beyond Control`.** Peek affiche « Problème signalé ». Si rien ne bouge ensuite, contacte la boutique.

**Aucune mise à jour depuis des jours.** Lorsqu'un envoi en cours n'a pas eu de nouveau scan depuis quatre jours, Peek le signale avec le jour de ce scan : « Aucune mise à jour depuis lun 12 oct ». Consulte [suivi qui ne bouge plus](guide:tracking-not-updating).

**Livré mais introuvable.** Demande aux personnes de ton foyer ou à l'accueil de l'immeuble, puis préviens la boutique. Vois [colis livré mais non reçu](guide:delivered-not-received).

**Perdu ou retourné.** Demande un remboursement ou un renvoi à la boutique : vois [colis perdu](guide:lost-parcel). `Shipment Returned Back To Shipper` indique que l'envoi est revenu chez l'expéditeur : vois [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service client et contact Blue Dart

Peek n'ayant pas pu vérifier le site de Blue Dart, cette page n'indique aucun numéro de téléphone ni aucune adresse e-mail : nous ne publions que des contacts lus sur les pages du transporteur lui-même. Lorsqu'un envoi reste bloqué, Peek affiche un lien vers la page d'aide de Blue Dart sous l'intitulé « Demande de l’aide à Blue Dart ».

Pour une commande en ligne, adresse-toi d'abord à la boutique : c'est elle qui a commandé le transport. Peek ne peut pas contacter Blue Dart ni la boutique à ta place.

## Suivre un envoi Blue Dart avec Peek

Colle le numéro de lettre de transport dans [Peek](/). Comme plusieurs transporteurs utilisent 11 chiffres, Peek n'affiche pas « Transporteur détecté » pour un numéro Blue Dart seul :

- **En Inde** (selon la région de ton téléphone ou le pays d'où tu te connectes sur le web), Peek affiche « Vérification des services de suivi… » pendant qu'il interroge les transporteurs compatibles, Blue Dart en premier. Si Blue Dart connaît le numéro, tu vois « Blue Dart a ce colis. ».
- **Hors d'Inde**, Peek interroge Blue Dart en dernier, une fois que les autres transporteurs et les services de suivi n'ont pas trouvé le numéro. Pour le classer tout de suite chez Blue Dart, appuie sur « Changer » et sélectionne Blue Dart sous « Correspond à ce numéro ».

Si aucun transporteur ne connaît encore le numéro, ajoute-le tout de même ou choisis Blue Dart toi-même : Peek suit plus de 4 500 transporteurs. Peek ne place Blue Dart dans la liste automatique que lorsque la clé de contrôle est valide. Si la tienne ne l'est pas mais que tu es sûr du numéro, recherche Blue Dart dans la liste des transporteurs : Peek l'interroge quand même. Un lien de suivi bluedart.com contenant le numéro (`trackNo=`) fonctionne aussi : Peek en extrait le numéro et affiche immédiatement Blue Dart (« Transporteur détecté »).

Classé sous Blue Dart, un envoi affiche « En attente de Blue Dart » jusqu'à son premier scan, ou « Numéro de suivi à vérifier » si Blue Dart ne connaît toujours pas le numéro après deux jours ; ajouté sans transporteur, il affiche « Aucun historique de suivi pour le moment ». Peek lit le suivi public de Blue Dart à partir du numéro seul, sans compte : l'état général donné par Blue Dart, puis chaque scan dans les mots de Blue Dart avec son lieu, lu à l'heure indienne. Il laisse de côté les coordonnées du destinataire et n'affiche pas de date de livraison estimée. Le lien « Ouvrir le site Blue Dart » sur la page du colis ouvre directement le numéro sur le suivi de Blue Dart.

Quand tu gardes le colis ouvert à l'écran, ou le jour de la livraison avec les notifications activées, Peek vérifie jusqu'à toutes les 10 minutes — et jusqu'à toutes les 2 minutes à partir de 6 h (heure locale) le jour où Blue Dart passe l'envoi en livraison — tant que le suivi de Blue Dart répond. Le reste du temps, et après deux jours sans nouvelle (12 heures tant que l'envoi reste en « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Lorsque c'est un service de suivi qui suit l'envoi, Peek vérifie toutes les heures, jusqu'à toutes les 15 minutes le jour de la livraison tant que l'envoi est ouvert ou que les notifications sont activées, et toutes les 3 heures après une journée sans nouveau scan. Les notifications sont réglables sur « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Questions sur le suivi Blue Dart

### Comment suivre un envoi Blue Dart ?

Saisis le numéro de lettre de transport à 11 chiffres sur le suivi de Blue Dart sur bluedart.com, ou colle-le dans Peek. En Inde, Peek interroge Blue Dart en premier ; ailleurs, il l'interroge en dernier, ou tu peux appuyer sur « Changer » et choisir Blue Dart.

### À quoi ressemble un numéro de suivi Blue Dart ?

11 chiffres, dont le dernier est une clé de contrôle. Aramex et d'autres utilisent aussi 11 chiffres : vérifie donc que le message de la boutique nomme bien Blue Dart.

### Que veut dire « Shipment Further Connected » sur Blue Dart ?

Blue Dart a transféré l'envoi de l'un de ses sites vers le suivant sur son trajet. Peek l'affiche comme « En transit ».

### Que veut dire « Cnee Refused Id/Otp Not Shared-Incorrect » ?

Une livraison échouée en raison d'un refus du destinataire ou d'une pièce d'identité ou d'un code à usage unique (OTP) non communiqué ou incorrect. Peek affiche « Tentative de livraison ». Si tu n'as rien refusé, demande à la boutique d'organiser une nouvelle livraison.

### Qui contacter pour un envoi Blue Dart manquant ?

La boutique ou l'expéditeur, qui a commandé Blue Dart. Peek n'ayant pas pu vérifier le site de Blue Dart, cette page ne publie pas de coordonnées Blue Dart.

:::sources
- [Universal Parcel Scraper : Blue Dart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/blue-dart) – Inde, numéro de lettre de transport à 11 chiffres et clé de contrôle, suivi et message d'absence de résultat, libellés et étapes, heure indienne, données écartées, liens de suivi et page d'aide
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – autres transporteurs à 11 chiffres, clé de contrôle d'Aramex et Japan Post, transporteurs interrogés en Inde et à l'étranger, lien d'ICC Worldwide vers Blue Dart et règle des 11 chiffres, absence de transfert depuis Blue Dart
:::
