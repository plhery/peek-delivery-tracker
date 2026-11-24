---
title: Suivi 5Post : numéros, retrait Pyaterochka et statuts
description: Suis un colis 5Post (FivePost / X5) en Russie : numéros P Avito et codes commande, statuts russes, retrait Pyaterochka et Perekrestok et contact.
slug: suivi-5post
published: 2026-11-24
updated: 2026-11-24
---

5Post (*5Пост* / *FivePost*, exploité par X5 Group) est un réseau russe de colis e-commerce, d’envois entre particuliers et de logistique qui livre les commandes en ligne et les colis Avito Delivery dans plus de **25 700** consignes automatiques (*постаматы*) et points de retrait en caisse au sein des supermarchés **Pyaterochka** (« Пятёрочка ») et **Perekrestok** (« Перекрёсток ») dans **4 950** villes et localités de Russie, avec l’appui de **51** centres de tri ([fivepost.ru](https://fivepost.ru/)). 5Post collabore avec plus de 1 000 boutiques en ligne et places de marché — dont Avito Delivery et les commandes internationales AliExpress — et assure aussi la livraison par coursier à domicile ainsi que le fret LTL/FTL.

Tu peux vérifier un colis sur [fivepost.ru](https://fivepost.ru/) (sous *Где мой заказ?*) ou coller ton numéro de commande ou un lien `fivepost.ru/tracking?id=...` dans le champ ci-dessus.

## Formats des numéros de suivi 5Post

5Post suit les envois à l’aide du numéro de commande de la boutique ou de la place de marché — toute chaîne unique de 3 à 40 lettres et chiffres contenant au moins un chiffre :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `P48291736051` | `P` + 11 chiffres (12 caractères), le format des numéros Avito Delivery. Peek interroge [Colis Privé](carrier:colis-prive) et 5Post, 5Post en premier en Russie. |
| `99994829173605` | 14 chiffres, un numéro de commande de boutique en ligne. Peek ne propose jamais 5Post pour ce format et interroge les sociétés DPD, Chronopost, SEUR, BRT et d’autres : choisis 5Post toi-même ou colle le lien 5Post. |

Les boutiques choisissent leurs propres numéros de commande, si bien que deux commandes peuvent partager le même. Le site de 5Post demande alors des chiffres du téléphone du destinataire, et Peek ne peut pas distinguer les deux commandes. Un lien `fivepost.ru/tracking?id=...` classe immédiatement le colis sous 5Post. Pour d’autres formats, consulte le [guide des formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi 5Post

Le service de suivi 5Post associe des codes techniques à des descriptions en russe, en mentionnant l’entrepôt régional de tri (`РЦ ...`) lors des scans de traitement et d’expédition.

| Code 5Post et libellé russe | Ce que cela signifie |
| --- | --- |
| `INFORMATION_RECEIVED` — `Ожидается передача заказа в 5post` | Les données de commande sont enregistrées ; 5Post attend que le vendeur ou l’expéditeur lui remette le colis. |
| `PROCESS_WITHDRAWN` — `Передан службе доставки` | L’expéditeur a déposé ou remis le colis à 5Post. |
| `WAREHOUSE_PROCESSING` — `Обрабатывается на складе ...` | En cours de traitement dans un entrepôt régional de tri 5Post (`РЦ`). |
| `READY_FOR_SHIPMENT` — `Покинул склад ...` | A quitté l’entrepôt de tri vers le centre suivant ou le magasin de retrait. |
| `DELIVERED` — `Доставлен в пункт выдачи` | Arrivé à la caisse Pyaterochka / Perekrestok choisie ou dans une consigne 5Post — **prêt à être retiré**, pas encore récupéré ! |
| `DONE` — `Получен` | Retiré par le destinataire (livré). |
| `RECEIVED_BY_COURIER` — `Передан курьеру` | En tournée pour livraison à domicile sur les commandes livrées par coursier. |
| `RETURNING_BY_COURIER` — `Неуспешная попытка вручения` | Une tentative de livraison par coursier a échoué. |
| `CUSTOMS_CLEARANCE_STARTED` / `CUSTOMS_DATA_REQUIRED` / `CUSTOMS_AWAITING_PAYMENT` | Commande venue de l’étranger (comme AliExpress) en cours de dédouanement, en attente de tes données pour le représentant en douane ou en attente du paiement des droits. |
| `CUSTOMS_CLEARANCE_COMPLETED` — `Таможенное оформление завершено` | La douane a libéré le colis pour la suite chez 5Post. |
| `UNCLAIMED_...` / `RETURNED_TO_PARTNER` / `RETURNED_TO_CLIENT` | Non retiré après le délai de garde ou annulé après prise en charge, renvoyé vers la boutique ou l’expéditeur (`UNCLAIMED_READY_FOR_REPICKUP` indique une préparation pour remise en point de retrait). |
| `INCEPTION_PROBLEM` / `LOST` / `CANCELLED` / `REGISTRATION_ERROR_...` | Problème de commande, annulation, perte ou numéro de téléphone / date de livraison / adresse manquant à corriger. |

Retiens une particularité importante des codes 5Post : `DELIVERED` (`Доставлен в пункт выдачи`) signifie **prêt à être retiré** en magasin ou en consigne, alors que `DONE` (`Получен`) signifie que tu l’as effectivement récupéré.

## Retrait chez Pyaterochka, Perekrestok et en consigne

Une commande à retirer attend dans une consigne 5Post (*постамат*) ou à la caisse (*касса*) du magasin Pyaterochka ou Perekrestok choisi lors de la commande ; la FAQ de 5Post indique les horaires d’ouverture des consignes et points de retrait :

- **Code de retrait :** dès que la commande arrive au point (`Доставлен в пункт выдачи`), 5Post envoie le code de retrait par SMS. Saisis-le sur l’écran de la consigne ou donne-le en caisse. La FAQ de 5Post explique où retrouver le code et que faire si le personnel ne trouve pas ta commande.
- **Prolongation de garde et changement de téléphone :** comme indiqué dans la [FAQ 5Post](https://fivepost.ru/faq/), tu peux vérifier les délais de garde, prolonger le délai, payer en ligne sur le site ou l’application mobile, ou suivre les démarches pour changer de numéro de téléphone, ouvrir un casier bloqué ou signaler un casier vide ou erroné.
- **Commandes transfrontalières (AliExpress) :** les commandes internationales acheminées via 5Post comprennent le transport aérien (`DEPARTED_BY_AIR`, `DELIVERED_BY_AIR`) et le dédouanement russe ; si le suivi affiche `Требуется предоставить данные таможенному представителю` (`CUSTOMS_DATA_REQUIRED`) ou `Требуется оплата таможенных платежей` (`CUSTOMS_AWAITING_PAYMENT`), le représentant en douane attend tes informations ou les droits restent à payer ; la FAQ de 5Post précise quelles commandes passent en douane, pourquoi ces données sont demandées, comment contacter le représentant en douane et quand payer. Voir le [guide des douanes](guide:customs) et [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

Pour les colis transportés en Russie par la poste nationale ou un autre transporteur, compare avec [Russian Post](carrier:russian-post) et [CDEK](carrier:cdek).

## Suivre un colis 5Post avec Peek

Colle ton numéro `P`, ton numéro de commande boutique ou ton lien `fivepost.ru/tracking?id=...` dans [Peek](/). Pour un numéro `P`, Peek interroge Colis Privé et 5Post, 5Post en premier en Russie, et affiche « 5Post a ce colis. » dès que 5Post répond. Pour un numéro de commande à 14 chiffres, il ne propose jamais 5Post : appuie sur « Changer » lors de l’ajout ou sur « Changer de transporteur » sur la page du colis et choisis 5Post. Un lien 5Post classe tout de suite le colis sous 5Post.

Peek lit le suivi invité de 5Post via l’adaptateur open source [universal-parcel-scraper 5Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post), et réessaie après une erreur réseau. Il affiche le libellé russe de chaque scan, le centre de tri ou hub cité par un scan d’entrepôt (`РЦ ...`, `ХАБ ...`), le nom et l’adresse du point de retrait une fois la commande disponible en magasin ou en consigne, ainsi que l’heure à laquelle tu l’as récupérée. 5Post date chaque scan avec son décalage UTC, et Peek l’affiche à l’heure de ton appareil. La date limite de garde, les détails de paiement, les horaires du point, le nom de l’expéditeur et le nombre de colis restent sur le site et l’application 5Post.

Peek traduit précisément les codes 5Post afin que `DELIVERED` ne déclenche jamais une fausse alerte de livraison à domicile : **Étiquette créée** (`INFORMATION_RECEIVED`), **Pris en charge** (`PROCESS_WITHDRAWN`), **En transit** (traitement en entrepôt, départ, étapes aériennes et transfert post-douane), **À la douane** (demandes de données du déclarant, dédouanement et paiement des droits), **En livraison** (`RECEIVED_BY_COURIER`), **Tentative de livraison** (`RETURNING_BY_COURIER`), **Prêt à être retiré** (`DELIVERED` — `Доставлен в пункт выдачи`), **Livré** (`DONE` — `Получен`), **Retourné à l’expéditeur** (`RETURNED_TO_...` et les codes `UNCLAIMED_...`, sauf `UNCLAIMED_READY_FOR_REPICKUP` qui reste en En transit), et **Problème signalé** (`LOST`, `CANCELLED`, `CUSTOMS_CLEARANCE_REJECTED` et erreurs d’enregistrement).

Quand le scan le plus récent remonte à quatre jours et que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La page du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger 5Post. », avec un lien « Demande de l’aide à 5Post » vers la FAQ de 5Post. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger 5Post. »

Tant que le colis est ouvert dans Peek, ou le jour de sa livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où 5Post affiche `Передан курьеру`, toutes les 2 minutes à partir de 6 h sur l’horloge du dernier scan, tant que le suivi de 5Post répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelle, il vérifie toutes les heures quelle que soit ta façon de le suivre, puis toutes les 6 heures après sept jours à « Étiquette créée ». Quand le suivi de 5Post ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Il peut t’avertir pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Contacter 5Post

5Post publie ses lignes téléphoniques officielles et ses formulaires de contact invité ou connecté sur [fivepost.ru/contacts](https://fivepost.ru/contacts/) et [fivepost.ru/faq](https://fivepost.ru/faq/) :

- **Téléphone Moscou et région de Moscou :** `8-495-935-88-00` (au tarif de ton opérateur)
- **Régions de Russie :** `8-800-100-05-05`
- **FAQ et formulaire de ticket en ligne :** [FAQ 5Post](https://fivepost.ru/faq/) et [Contacts 5Post](https://fivepost.ru/contacts/) (accepte les demandes des destinataires avec ou sans connexion et des pièces jointes jusqu’à 40 Mo)
- **Adresse légale :** 109029, Moscou, ul. Srednyaya Kalitnikovskaya, 28 bldg. 4 (INN `7722461620`, OGRN `1187746567568`)

## Questions fréquentes

### Pourquoi le statut interne de 5Post indique-t-il `DELIVERED` avant que je retire mon colis ?

Dans le système de suivi de 5Post, `DELIVERED` (`Доставлен в пункт выдачи`) signifie que le colis a été livré au point de retrait ou à la consigne Pyaterochka/Perekrestok et t’y attend. Peek l’affiche comme **Prêt à être retiré** et attend `DONE` (`Получен`) avant d’indiquer **Livré**.

### Où retirer une commande 5Post ?

Tu la retires dans la consigne 5Post (*постамат*) ou à la caisse du supermarché Pyaterochka (« Пятёрочка ») ou Perekrestok (« Перекрёсток ») sélectionné lors de la commande, à l’aide du code reçu par SMS de 5Post.

### Pourquoi 5Post demande-t-il des données douanières pour ma commande AliExpress ?

Une commande venant de l’étranger est dédouanée par un représentant en douane, qui peut avoir besoin de tes données (`CUSTOMS_DATA_REQUIRED`). La FAQ de 5Post précise quelles commandes passent en douane, pourquoi ces informations sont requises et comment joindre le représentant en douane. Une fois le colis libéré, 5Post affiche `Таможенное оформление завершено` (`CUSTOMS_CLEARANCE_COMPLETED`).

### Comment contacter 5Post si un casier ne s’ouvre pas ou si le colis manque ?

Appelle le `8-495-935-88-00` (Moscou et région) ou le `8-800-100-05-05` (régions), ou ouvre un ticket destinataire (avec photos jusqu’à 40 Mo) via [fivepost.ru/contacts](https://fivepost.ru/contacts/) ou ton compte 5Post.

:::sources
- [5Post (fivepost.ru)](https://fivepost.ru/)
- [FAQ 5Post](https://fivepost.ru/faq/)
- [Contacts 5Post](https://fivepost.ru/contacts/)
- [universal-parcel-scraper 5Post adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post)
:::
