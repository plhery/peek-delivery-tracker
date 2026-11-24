---
title: Suivi DobroPost : codes DBRPST, statuts et hubs en Chine
description: Suis un colis DobroPost de Chine vers la Russie ou la Biélorussie : formats DBRPST, scans entrepôt et douane, retrait PVZ, relais et aide.
slug: suivi-dobropost
published: 2026-11-24
updated: 2026-11-24
---

DobroPost (*ДоброПост*, exploité par *OOO « Dobropost Logistika »* à Moscou conjointement avec *OOO ICOM DP SOLUTIONS* en Arménie) est un service de réexpédition de colis de la Chine vers la Russie et la Biélorussie utilisé par les acheteurs sur les places de marché chinoises comme Taobao, Poizon, 1688, Pinduoduo, Tmall, 95, Goofish et JD. Les acheteurs font livrer leurs achats à l’entrepôt DobroPost de Jixi (Heilongjiang), où les articles sont vérifiés, emballés et pesés ; le colis passe ensuite en douane puis rejoint un point de retrait DobroPost (PVZ) ou est remis à [CDEK](carrier:cdek), DPD ou [Russian Post](carrier:russian-post). Le suivi DobroPost relaie également les scans de Boxberry. Tu peux vérifier un envoi sur [tracking.dobropost.com](https://tracking.dobropost.com/) ou coller ton code `DBRPST` dans le champ ci-dessus.

## Numéros de suivi DobroPost (format `DBRPST`)

Un numéro de colis DobroPost commence par `DBRPST`, suivi de 10 ou 11 chiffres :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `DBRPST6482917304` | Numéro de colis DobroPost à 16 caractères (`DBRPST` + 10 chiffres). |
| `DBRPST73918462051` | Numéro de colis DobroPost à 17 caractères (`DBRPST` + 11 chiffres). |

Les numéros attribués par les transporteurs chinois sur le trajet vers l’entrepôt DobroPost appartiennent à ces transporteurs, pas à DobroPost : [tracking.dobropost.com](https://tracking.dobropost.com/) et Peek suivent le colis par son numéro `DBRPST`. Voir [les formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi DobroPost

DobroPost publie des jalons en russe accompagnés de codes internes — d’abord pour son entrepôt en Chine et les étapes de douane, puis relayés directement depuis le transporteur national du dernier kilomètre ([CDEK](carrier:cdek), Boxberry ou [Russian Post](carrier:russian-post)) :

| Code ou libellé DobroPost | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `VIRTUAL_PARCEL` | Le colis est créé dans le dossier DobroPost ; l’entrepôt en Chine y emballe tes articles quelques jours plus tard. | « Étiquette créée » |
| `ON_ASSEMBLY`, `ON_WEIGHING`, `AWAITING_PAYMENT`, `READY_FOR_SENT`, `ADDED_TO_THE_REGISTRY` | Emballé et pesé à l’entrepôt DobroPost en Chine, en attente de paiement de l’expédition ou ajouté à un bordereau de départ. | « Pris en charge » |
| `LEFT_THE_DP_WERHOUSE_IN_CHINA`, `FLEW_OUT_FROM_CHINA` | A quitté l’entrepôt DobroPost en Chine et parti de Chine sur la liaison internationale. | « En transit » |
| `AT_CUSTOMS_IN_CHINA`, `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`, `START_OF_CUSTOMS_CLEARANCE`, `Выпуск с таможни` | En cours de dédouanement export en Chine ou import en Russie ou en Biélorussie. | « À la douane » |
| `RELEASE_OF_GOODS_WITHOUT_PAYMENT_OF_CUSTOMS_DUTIES`, `LEFT_CUSTOMS_AND_HEADING_TO_LAST_MILE_CARRIER'S_WAREHOUSE`, `READY_FOR_SHIPMENT_FOR_LAST_MILE_DELIVERY` | Libéré par la douane et acheminé vers le réseau local du dernier kilomètre. | « En transit » |
| `REFUSAL_TO_RELEASE_GOODS_WITH_INDICATION_OF_THE_CODE_OF_THE_REASON_FOR_REFUSAL` | La douane a refusé la mainlevée ; le suivi indique le motif, par exemple marchandise non destinée à un usage personnel. Vérifie ton compte DobroPost. | « Problème signalé » |
| `RECEIVED_AT_SHIPMENT_WAREHOUSE`, `SEND_TO_TRANSIT_OFFICE`, `SEND_TO_RECIPIENT_OFFICE`, `ACCEPTED_FOR_DELIVERY`, `SENT_TO_DESTINATION_CITY`, `Прием`, `Обработка` | Scans nationaux relayés de CDEK, Boxberry ou Russian Post en route vers ta ville. | « En transit » |
| `ACCEPTED_AT_WAREHOUSE_ON_DEMAND` (CDEK), `RECEIVED_AT_THE_POINT_OF_ISSUE` (Boxberry) | Arrivé au point relais ou agence choisie et prêt à être retiré. | « Prêt à être retiré » |
| `GETTING_READY_TO_RETURN`, `SENT_TO_COLLECTION_POINT`, `RETURNED_TO_COLLECTION_POINT`, `RETURNED`, `Возврат` | Non retiré au point relais ou renvoyé sur le dernier trajet, en retour vers DobroPost qui peut le réexpédier. | « Retourné à l’expéditeur » |
| `RELEASE_OF_GOODS_CUSTOMS_PAYMENTS_PAID` (`Возврат в Китай разрешен`) | La douane a refusé la mainlevée et autorisé le retour du colis en Chine. | « Retourné à l’expéditeur » |
| `PARCEL_DELIVERED_TO_RECIPIENT`, `DELIVERED`, `Вручение` | Retiré au point relais ou livré au destinataire. | « Livré » |

## Délais, tarifs et points de retrait DobroPost PVZ

D’après [les tarifs DobroPost](https://dobropost.com/tariffs), les délais se comptent de l’expédition hors de l’entrepôt DobroPost en Chine jusqu’à l’arrivée en point de retrait (PVZ) ou la remise à un transporteur national :

- **Tarifs Express** (poids volumétrique, sans articles sensibles) : généralement **9 à 13 jours** vers Moscou, **10 à 14 jours** vers Saint-Pétersbourg, **9 à 12 jours** vers Novossibirsk, Krasnodar, Iekaterinbourg, Kazan, Irkoutsk et Oulan-Oudé, et **11 à 15 jours** vers Minsk (Biélorussie).
- **Tarifs Economy** (poids réel, adaptés aux colis lourds ou volumineux) : généralement **19 à 25 jours** vers Moscou, **20 à 27 jours** vers Saint-Pétersbourg, **15 à 23 jours** vers Irkoutsk et **22 à 29 jours** vers Minsk.
- **Tarifs Extrême-Orient et Colis Lourds :** **6 à 9 jours** vers Vladivostok, **8 à 12 jours** vers Blagovechtchensk, et **6 à 9 jours** pour les colis lourds individuels de 5 à 30 kg.
- **Points de retrait DobroPost PVZ :** répertoriés sur [dobropost.com/pvz](https://dobropost.com/pvz) à Moscou, Odintsovo, Saint-Pétersbourg, Kazan, Krasnodar, Novossibirsk, Iekaterinbourg, Irkoutsk, Oulan-Oudé, Blagovechtchensk, Vladivostok, Oussouriïsk et Minsk. Les frais de remise PVZ publiés sont de `100 RUB` (jusqu’à 5 kg), `200 RUB` (jusqu’à 10 kg) et `300 RUB` (au-delà de 10 kg) ; les villes sans PVZ DobroPost passent par des points relais et transporteurs partenaires. Un colis attend gratuitement 7 jours en PVZ DobroPost, puis moyennant 50 ₽ par jour, et est détruit 30 jours après son arrivée.

## Douane, pauses de suivi et incidents

- **Paiement du transport requis avant départ :** si le suivi s’arrête sur `AWAITING_PAYMENT`, le colis reste à l’entrepôt en Chine jusqu’au règlement de la facture d’expédition dans ton compte client DobroPost.
- **Pauses lors du passage frontalier et en douane :** les scans marquent fréquemment une pause entre `FLEW_OUT_FROM_CHINA` et `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`, ou pendant l’examen douanier. Voir [les colis retenus en douane](guide:customs) et [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **Dommages, articles manquants ou retours :** les [conditions de DobroPost](https://dobropost.com/rules) demandent de noter tout dommage sur le bordereau de réception et de présenter le contenu ainsi que l’emballage d’origine pour toute réclamation. Sa [base de connaissances](https://dobropost.com/baza_main) explique la marche à suivre si un article manque. Un colis non retiré revient chez DobroPost (`RETURNED`), qui peut le réexpédier sur demande au support. Voir [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel), [colis livré mais non reçu](guide:delivered-not-received) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et liens officiels DobroPost

Le site de DobroPost comporte un bouton « Напишите в поддержку » (écrire au support), une communauté clients et une chaîne Telegram, et permet d’envoyer réclamations et suggestions directement à son PDG, Sergey Serbin. La [base de connaissances](https://dobropost.com/baza_main) détaille comment écrire au support, changer le tarif d’un colis déjà créé et accomplir le dédouanement.

- **Opérateur russe enregistré :** *OOO « Dobropost Logistika »* (*ООО «Добропост Логистика»*, INN `9722023780`), Moscou (`119192, Lomonosovsky Prospekt, 43 bldg. 2, office 4/1/1` ; le site indique aussi `Michurinsky Prospekt, 54 bldg. 2`).
- **Entité arménienne :** *OOO ICOM DP SOLUTIONS* (*ООО ИКОМ ДП СОЛУШНС*), Vanadzor, province de Lori, Arménie.
- **Conditions générales et tarifs :** [dobropost.com/rules](https://dobropost.com/rules), [dobropost.com/tariffs](https://dobropost.com/tariffs) et [dobropost.com/pvz](https://dobropost.com/pvz).

> Méfie-toi des faux messages Telegram ou liens SMS réclamant des frais de douane supplémentaires hors de ton espace client officiel DobroPost ou du portail officiel du déclarant en douane. Vérifie toujours tout paiement dans ton compte DobroPost d’abord. Voir [les arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis DobroPost avec Peek

[Peek](/) détecte automatiquement DobroPost comme unique candidat lorsque tu saisis un numéro `DBRPST` (`DBRPST6482917304` ou `DBRPST73918462051`, inventés) et l’indique aussitôt avec « Transporteur détecté » ; Peek reconnaît aussi les liens collés `tracking.dobropost.com/?trackNumber=`. Si tu dois choisir DobroPost manuellement, appuie sur « Changer » lors de l’ajout ou sur « Changer de transporteur » sur l’écran du colis (rechercher « ДоброПост » ou « Dobropost » le trouve aussi).

Peek lit directement le suivi de DobroPost et affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « À la douane », « Prêt à être retiré », « Problème signalé », « Retourné à l’expéditeur », « Livré »), les libellés russes de DobroPost et des partenaires qu’il relaie (CDEK, Boxberry et Russian Post) sans numéros de téléphone, ainsi que l’heure de livraison une fois livré. La ville mentionnée entre parenthèses par CDEK à la fin d’un scan sert de lieu pour ce scan. Les codes statut n’apparaissent dans aucune application Peek. DobroPost transmet chaque scan avec le décalage UTC de Moscou, y compris pour l’entrepôt en Chine, et Peek les affiche à l’heure de ton appareil. Le suivi DobroPost ne fournit ni date de livraison estimée, ni poids, ni nom du signataire, ni numéro de suivi du dernier kilomètre.

Tant que le colis est ouvert dans Peek, ou le jour de sa livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes tant que le suivi DobroPost répond. Sinon, Peek vérifie toutes les heures. Le suivi de DobroPost n’affiche pas d’étape en livraison, donc Peek ne passe jamais aux vérifications toutes les 2 minutes du jour de livraison. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelle, il vérifie toutes les heures quelle que soit ta façon de le suivre, puis toutes les 6 heures après sept jours à « Étiquette créée ». Quand le suivi DobroPost ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Quand le scan le plus récent remonte à quatre jours et que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La page du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger DobroPost. » Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger DobroPost. »

Tu peux choisir entre les réglages d’alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek se limite au suivi des mises à jour et ne peut ni payer les factures d’expédition, ni transmettre tes données de passeport en douane, ni contacter DobroPost ou ses partenaires nationaux à ta place.

## Questions fréquentes sur le suivi DobroPost

### À quoi ressemble un numéro de suivi DobroPost ?

Les numéros de suivi internationaux DobroPost commencent par `DBRPST` suivi de 10 ou 11 chiffres (par exemple `DBRPST6482917304` ou `DBRPST73918462051`, inventés).

### Combien de temps prend la livraison DobroPost depuis la Chine ?

Les estimations publiées sur [dobropost.com/tariffs](https://dobropost.com/tariffs) vont de **6 à 9 jours** vers Vladivostok et **9 à 13 jours** (Express) ou **19 à 25 jours** (Economy) vers Moscou, et **11 à 15 jours** (Express) ou **22 à 29 jours** (Economy) vers Minsk, à compter du départ de l’entrepôt DobroPost en Chine.

### Quels transporteurs nationaux livrent les colis DobroPost en Russie et en Biélorussie ?

DobroPost livre vers ses propres points PVZ en Russie et à Minsk ainsi que, selon ses tarifs, via [CDEK](carrier:cdek), DPD et [Russian Post](carrier:russian-post) (uniquement CDEK pour Minsk). Son suivi relaie les scans de CDEK, Boxberry et Russian Post.

### Pourquoi mon colis DobroPost est-il bloqué sur `AWAITING_PAYMENT` ou en douane ?

`AWAITING_PAYMENT` signifie que ton colis a été emballé et pesé à l’entrepôt DobroPost en Chine et attend le règlement du transport dans ton compte client. Si le suivi marque une pause en douane, la [base de connaissances](https://dobropost.com/baza_main) de DobroPost explique le dédouanement et son support peut t’informer de l’avancement.

:::sources
- [DobroPost : site officiel](https://dobropost.com/) – réexpédition de colis Chine–Russie/Biélorussie, entrepôt de Jixi, support et ligne PDG, entités légales à Moscou et en Arménie (*OOO « Dobropost Logistika »*, INN 9722023780)
- [DobroPost : suivi de colis](https://tracking.dobropost.com/) – page publique de suivi `DBRPST`
- [DobroPost : tarifs et délais](https://dobropost.com/tariffs) – délais Express, Economy, Extrême-Orient et Colis Lourds, règles de poids et partenaires
- [DobroPost : points de retrait (PVZ)](https://dobropost.com/pvz) – emplacements PVZ DobroPost en Russie et à Minsk, frais de remise et garde
- [DobroPost : base de connaissances](https://dobropost.com/baza_main) – guides d’achat, support, changement de tarif, dédouanement et articles manquants
- [DobroPost : conditions générales de transport](https://dobropost.com/rules) – conditions de transport et courtage en douane
- [Universal Parcel Scraper : dobropost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – format `DBRPST`, règle de lien `tracking.dobropost.com`, correspondance des statuts DobroPost/CDEK/Boxberry/Russian Post, ville entre parenthèses comme lieu de scan et horodatages à l’heure de Moscou
:::
