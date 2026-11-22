---
title: Suivi Ozon : numéros de commande et statuts en français
description: Suivre une commande Ozon (Ozon Rocket, Ozon Доставка) : format des numéros avec tirets, signification des statuts russes en français et suivi dans Peek.
slug: suivi-ozon
published: 2026-11-21
updated: 2026-11-21
---

Le service de livraison d'Ozon achemine les commandes remises par les vendeurs jusqu'à ta porte ou vers un point de retrait. Le catalogue de Peek le répertorie en Russie, en Biélorussie et au Kazakhstan, aussi sous les noms Ozon Rocket, OZON Rocket Доставка, Ozon Доставка et Ozon Dostavka. Colle ton numéro dans le champ ci-dessus avec ses tirets. Le suivi propre à Ozon se trouve sur tracking.ozon.ru, et Peek le lit pour toi comme expliqué ci-dessous.

## Numéro de suivi Ozon : à quoi il ressemble

Peek connaît deux formats pour Ozon, tous deux écrits avec des tirets. Les tirets comptent : sans eux, les chiffres ressemblent aux numéros d'autres transporteurs. Exemples avec des chiffres inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `48291057-3628-1` | 8 chiffres, 4 chiffres et 1 chiffre reliés par des tirets. Peek reconnaît aussitôt Ozon. |
| `4829105736-2841-2` | Le même format avec 10 chiffres au début. Peek reconnaît Ozon aussi. |
| `4829105736281` | Le premier numéro sans tirets, ou avec des espaces à la place. DPD France, Ukrposhta et d'autres transporteurs emploient des numéros à 13 chiffres : les chiffres seuls ne suffisent donc pas à identifier Ozon. Ajouté depuis la Russie, la Biélorussie ou le Kazakhstan, Peek interroge Ozon parmi d'autres transporteurs ; ailleurs, il n'interroge pas Ozon automatiquement. Remets les tirets ou choisis Ozon manuellement (ci-dessous). Même règle pour 15 chiffres, longueur utilisée par FedEx et d'autres. |
| `PL-482910-57362` | `PL`, 6 chiffres, puis d'autres lettres ou chiffres, séparés par des tirets. Peek reconnaît Ozon. |
| `PL-482910-5736` | `PL` suivi de exactement 10 chiffres correspond aussi au format des numéros [Amazon](carrier:amazon-logistics). Peek le classe comme un numéro Amazon et ne permet pas de choisir Ozon pour celui-ci. |

Les minuscules ne changent rien : `pl-` fonctionne comme `PL-`. Numéro introuvable ? Voir [comment trouver ton numéro de suivi](guide:find-tracking-number) et [formats de numéros de suivi](guide:tracking-number-formats).

## Statuts de suivi Ozon en français

Le code de Peek recense les étapes du suivi d'Ozon, en russe. Peek lit ce suivi et affiche ces étapes dans les mêmes mots russes, à l'exception de `Заказ в работе`, `Заказ на пути к границе страны назначения` et `Заказ прибывает на приграничный пункт`, qu'il laisse de côté (voir « Suivre une commande Ozon avec Peek »).

**Dans ton pays.** À peu près dans l'ordre où ils apparaissent :

| Ce qu'indique Ozon | Ce que cela signifie pour toi |
| --- | --- |
| `Передается в доставку` | Le vendeur a préparé la commande et la remet pour livraison. Ozon ne l'a peut-être pas encore. |
| `Заказ принят перевозчиком.` | Le transporteur a pris en charge la commande : elle est en route. |
| `Заказ отправили на сортировочный терминал`, `Заказ отправлен на сортировочный терминал.` | Envoyée vers un centre de tri. |
| `Заказ покинул сортировочный терминал.` | A quitté le centre de tri. |
| `Заказ ожидает отправки в город получателя.` | En attente d'expédition vers ta ville. |
| `Заказ везут в город получателя` | En route vers ta ville. |
| `Заказ везут` | En cours d'acheminement. |
| `Заказ передали в курьерскую доставку` | Transmise à la livraison par coursier ; celui-ci t'appelle pour convenir d'un créneau. |
| `Заказ в пункте выдачи` | Disponible à ton point de retrait : tu peux aller la chercher. |
| `Заказ получен в пункте выдачи.` | Retirée au point de retrait ou au bureau de poste : livrée. |
| `Заказ успешно доставлен получателю.` | Livrée. Le suivi d'Ozon affiche ce libellé lorsque la commande n'est pas passée par un point de retrait ou un bureau de poste, par exemple en livraison par coursier. |
| `Заказ отменён` | La commande a été annulée. |
| `Заказ в работе` | Commande en cours : le suivi d'Ozon l'affiche comme marqueur d'attente, pas comme étape du trajet. |

**Depuis un autre pays.** Une commande expédiée depuis l'étranger passe aussi par des étapes douanières, dans cet ordre approximatif :

| Ce qu'indique Ozon | Ce que cela signifie pour toi |
| --- | --- |
| `Заказ везут на таможню в стране отправления` | En route vers la douane du pays d'expédition. |
| `Заказ привезли на таможню для экспортного таможенного оформления` | Arrivée en douane d'exportation dans ce pays. |
| `Заказ покинул зону экспортного таможенного оформления` | A quitté la zone de dédouanement à l'exportation. |
| `Заказ на пути к границе страны назначения` | En route vers la frontière de ton pays. |
| `Заказ прибывает на приграничный пункт` | Arrive au poste-frontière. |
| `Заказ привезли в страну назначения` | Arrivée dans ton pays. |
| `Заказ передан на импортное таможенное оформление.` | Remise à la douane d'importation de ton pays. |
| `Заказ проходит импортное таможенное оформление` | En cours de dédouanement à l'importation. |
| `Заказ выпущен импортной таможней.` | Libérée par la douane d'importation : de nouveau en route. |
| `Импортная таможня задержала заказ` | La douane d'importation retient la commande pour un contrôle complémentaire. |

## Comment Ozon livre : coursier ou point de retrait

Les étapes indiquent le mode de livraison retenu.

**À domicile.** Après `Заказ передали в курьерскую доставку` (classé en « En transit » dans Peek), un coursier apporte la commande et t'appelle d'abord pour convenir d'un horaire. La dernière étape est `Заказ успешно доставлен получателю.`

**En point de retrait.** Attends le statut `Заказ в пункте выдачи` (« Prêt à être retiré »), puis rends-toi sur place pour la retirer. Une fois retirée, le suivi affiche `Заказ получен в пункте выдачи.` ; le même libellé s'applique en bureau de poste.

Peek n'ayant pas pu lire le site web d'Ozon, cette page ne fournit ni délais de livraison, ni horaires d'ouverture, ni durées de garde, ni règles relatives aux tentatives de livraison : renseigne-toi auprès d'Ozon. Voir aussi [colis non retiré](guide:uncollected-parcel) et [livraison manquée](guide:missed-delivery).

**Depuis l'étranger.** Les étapes douanières ci-dessus indiquent où se situe une commande internationale. Cette page n'énonce aucune règle ni taxe douanière, Peek n'ayant pas pu les vérifier sur une source officielle. Voir [colis retenu à la douane](guide:customs).

## Commande Ozon bloquée ou qui n'arrive pas

**Rien pour l'instant, ou seulement `Передается в доставку`.** Le vendeur n'a peut-être pas encore remis la commande, ou le suivi d'Ozon ne connaît pas encore le numéro. Peek continue de vérifier. Si tu as saisi le numéro sans ses tirets, sélectionne Ozon via « Changer de transporteur ».

**Peek a du retard sur tracking.ozon.ru.** Lorsque le suivi d'Ozon ne répond pas à Peek, un service de suivi prend le relais et peut avoir du retard. Compare avec tracking.ozon.ru avant de t'inquiéter.

**Aucune nouvelle étape depuis plusieurs jours.** Si la dernière étape date de quatre jours ou plus alors que Peek affiche « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de cette étape (« Aucune mise à jour depuis lun 12 oct ») et propose un lien vers l'aide d'Ozon (« Demande de l’aide à Ozon »). Plus de détails dans [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**`Импортная таможня задержала заказ`.** La douane examine la commande. Attends `Заказ выпущен импортной таможней.` ; si la situation s'éternise, contacte Ozon.

**`Заказ отменён`.** La commande a été annulée et ne sera pas livrée. Demande un remboursement à Ozon ou à la boutique où tu as acheté.

**Livrée, mais rien reçu.** Vérifie si un proche l'a retirée pour toi, puis contacte Ozon. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Perdue.** Contacte Ozon ou la boutique où tu as passé commande. Voir [colis perdu](guide:lost-parcel).

## Service client et contact Ozon

Peek n'ayant pas pu lire le site web d'Ozon, cette page ne mentionne ni numéro de téléphone ni adresse e-mail : Peek n'indique que des coordonnées vérifiées sur les pages propres du transporteur. Le suivi d'Ozon se trouve sur tracking.ozon.ru.

Pour une commande achetée sur Ozon, contacte Ozon. Pour une commande passée dans une autre boutique, adresse-toi d'abord à cette boutique, qui a réservé la livraison.

## Suivre une commande Ozon avec Peek

[Peek](/) suit un numéro Ozon avec ce seul numéro : ni compte, ni téléphone, ni code supplémentaire.

- **Avec ses tirets.** Peek reconnaît aussitôt Ozon (« Transporteur détecté ») et classe le colis sous Ozon.
- **Sans ses tirets.** Peek ne peut pas identifier le transporteur à partir des seuls chiffres. Colle-le tout de même : Peek suit plus de 4 500 transporteurs. Pendant qu'il interroge les transporteurs compatibles et les services de suivi, il affiche « Vérification des services de suivi… ». Ozon fait partie des transporteurs interrogés uniquement lorsque le numéro est ajouté depuis la Russie, la Biélorussie ou le Kazakhstan. Si les services de suivi identifient Ozon avec des étapes datées, Peek classe le colis sous Ozon automatiquement. Sinon, appuie sur « Changer » et cherche Ozon (ou utilise « Changer de transporteur » sur un colis déjà ajouté).
- **Un lien.** Colle un lien tracking.ozon.ru contenant `track=` ou un lien rocket.ozon.ru contenant `SearchId=`, et Peek suit ce numéro avec Ozon.

**D'où viennent les étapes.** Peek lit le suivi d'Ozon et affiche chaque étape dans le russe d'Ozon (comme dans les tableaux ci-dessus), avec son heure et les dates prévues de livraison indiquées par Ozon. Les horaires avec décalage explicite sont lus directement et ceux sans décalage sont interprétés sur l'heure de Moscou, puis affichés dans le fuseau horaire de ton appareil ; sur peektracker.com et dans les applications iPhone et Android, l'historique se termine par « Heures dans ton fuseau horaire : » et son nom. Ozon n'indiquant pas de lieu pour chaque étape, aucune carte n'apparaît. Tant que le suivi d'Ozon ne connaît pas le numéro, Peek affiche « En attente de Ozon », ou « Aucun historique de suivi pour le moment » pour un numéro ajouté sans transporteur. Si le suivi d'Ozon ne répond pas, Peek interroge tour à tour ParcelsApp, Ship24 et 17TRACK et affiche l'historique du premier qui trouve le colis, dans ses propres mots (pouvant avoir du retard sur tracking.ozon.ru). Le suivi direct d'Ozon dans Peek n'accepte pas les numéros `PL-` : Peek affiche « Numéro de suivi à vérifier » jusqu'à ce qu'un service de suivi trouve le colis.

**Le lien sur la page du colis.** « Ouvrir le site Ozon » ouvre le numéro sur tracking.ozon.ru. Si un service de suivi a répondu à sa place, le lien ouvre ce service (par exemple « Ouvrir le site ParcelsApp »).

**Un colis d'un autre transporteur.** Peek enregistre le numéro Ozon sans ses tirets. Tant que le suivi d'Ozon n'a pas reconnu le colis, une vérification sans résultat interroge aussi, durant les 30 premiers jours et avant les services de suivi, certains transporteurs utilisant la même longueur (comme Ukrposhta et Delhivery pour 13 chiffres, ou Hermes Germany et BRT pour 15). Si l'un d'eux montre le colis en mouvement, Peek bascule le colis chez lui et affiche « Transporteur remplacé automatiquement (ancien : Ozon) ».

**Livraison par un autre transporteur.** Si un service de suivi indique un autre transporteur avec son propre numéro et que Peek lit son suivi direct, Peek l'interroge aussi ; dès que ce transporteur publie un scan au moins aussi récent que celui du service, Peek bascule le suivi chez lui.

**Vérifications et alertes.** Tant que tu gardes le colis ouvert à l'écran ou le jour de la livraison avec les notifications activées et que le suivi d'Ozon répond, Peek vérifie jusqu'à toutes les 10 minutes ; sinon, toutes les heures. Lorsque c'est un service de suivi qui suit le colis, Peek vérifie toutes les heures, jusqu'à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées, et toutes les 3 heures après une journée sans nouvelle étape. Tu peux choisir d'être prévenu pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Pour un point de retrait, choisis « Étapes importantes » : « Livraison seulement » ne prévient pas quand la commande arrive au point de retrait. Peek ne peut contacter ni Ozon ni le vendeur à ta place.

## Questions sur le suivi Ozon

### À quoi ressemble un numéro de suivi Ozon ?

Peek associe deux formats à Ozon, tous deux avec tirets : trois groupes de chiffres (8 ou 10 chiffres, puis 4, puis 1, comme `48291057-3628-1`) et les numéros commençant par `PL-`, comme `PL-482910-57362`. Saisis bien les tirets : sans eux, Peek ne peut pas identifier Ozon seul.

### Pourquoi Peek ne reconnaît-il pas mon numéro Ozon ?

Tu l'as probablement saisi sans ses tirets ou avec des espaces. Saisis-le de nouveau sous la forme `48291057-3628-1` avec tes propres chiffres, ou appuie sur « Changer » pour sélectionner Ozon.

### Que signifie « Заказ в пункте выдачи » ?

Ta commande est arrivée à ton point de retrait et tu peux aller la chercher. Une fois retirée, le suivi d'Ozon affiche `Заказ получен в пункте выдачи.`

### Peut-on suivre une commande Ozon en français ?

Oui. Peek affiche l'étape globale en français (par exemple « En transit ») et l'historique dans le russe d'Ozon ; les tableaux ci-dessus traduisent chaque étape.

:::sources
- [Universal Parcel Scraper : Ozon](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ozon-logistics) – noms, pays desservis (`RU`, `BY`, `KZ`), formats avec tirets, liens tracking.ozon.ru et rocket.ozon.ru, page d'aide `docs.ozon.ru/common/dostavka/`, champs lus et statuts écartés (`FantomDelivery` et statuts fantômes de frontière), étapes russes et leur correspondance, appel du coursier, retrait en point de retrait ou bureau de poste
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transporteurs partageant 13 et 15 chiffres, numéros Amazon (`PL-` + 10 chiffres), ordre des services de suivi, transporteurs interrogés durant les 30 premiers jours, bascules nommées par un service de suivi
:::
