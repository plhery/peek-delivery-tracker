---
title: Suivi colis Shouwu : numéro SWECN…YQ, statuts et livraison
description: Suivre un colis Shouwu International (守务国际) venu de Chine : à quoi ressemble le numéro SWECN…YQ, ce que veulent dire les statuts et qui livre en France.
slug: shouwu-suivi
published: 2026-11-02
updated: 2026-11-12
---

Shouwu International (守务国际, aussi SWE) est une société de logistique de Shanghai qui expédie les colis de vendeurs en ligne chinois vers l'Europe et les États-Unis, où une poste ou un transporteur les livre. En France, c'est surtout Colissimo, et Shouwu annonce 4 à 7 jours ouvrés. Le vendeur choisit le numéro qu'il te transmet : celui de Shouwu, qui commence par SWECN et finit par YQ, ou celui de Colissimo. Colle l'un ou l'autre dans le champ ci-dessus, ou le numéro Shouwu sur son site de suivi, [admin.shshouwu.com](https://admin.shshouwu.com/track.html). Pas de numéro ? Demande-le au vendeur : Shouwu n'a aucun contact pour les destinataires.

## Numéro de suivi Shouwu : à quoi il ressemble

Un numéro Shouwu, c'est SWECN, douze chiffres et YQ, soit 19 caractères. Avec des chiffres inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `SWECN000000000000YQ` | SWECN, douze chiffres et YQ : un numéro Shouwu. |
| `SWECN00000000000YQ` | Onze chiffres seulement : pas un numéro Shouwu. Vérifie qu'il ne manque pas un chiffre. |
| `JSEAA0000000000YQ` | Finit aussi par YQ, mais commence par JSE : un numéro de JS Express. |

D'autres transitaires chinois terminent aussi leurs numéros par YQ, avec leurs propres lettres devant et, dans le catalogue de Peek, le plus souvent dix chiffres. SWECN et douze chiffres, c'est Shouwu. Espaces, tirets et minuscules ne gênent pas Peek.

**Où le trouver.** S'il n'est ni dans l'e-mail d'expédition de la boutique ni sur la page de ta commande, lis [où trouver son numéro de suivi](guide:find-tracking-number).

**Tu as peut-être le numéro du transporteur.** Selon les pages de ses lignes, le numéro Shouwu suit le colis de bout en bout, et celui du transporteur, la livraison finale. Si la boutique t'a donné un numéro Colissimo, colle-le quand même : Peek suit plus de 3 500 transporteurs.

**Un lien de suivi.** Le bouton « Copy Link » du site de Shouwu copie un lien avec `?q=` et le numéro. Il ouvre ce colis et se colle tel quel dans Peek. Le site de Shouwu accepte jusqu'à 40 numéros à la fois, un par ligne.

## Suivi colis Shouwu : ce que veulent dire les statuts

Le suivi de Shouwu est en anglais, sous un titre en chinois. Pour chaque colis, il donne un statut et le pays de destination, puis l'heure et le libellé de chaque scan, mais pas son lieu.

| Statut Shouwu | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Not Found` | Shouwu n'a encore rien sous ce numéro : « We seem can't identify the number. » | « En attente du transporteur » |
| `Preparing` | Selon Shouwu, le colis a été remis à son entrepôt en Chine, ou y est pesé et scellé. Ce statut peut apparaître avant tout scan. | « Étiquette créée », puis « Pris en charge » après un scan de l'entrepôt |
| `In Transit` | Tout le trajet, de la remise à un transporteur jusqu'à la douane et à l'acheminement dans ton pays. | « En transit », ou l'étape du dernier scan, comme « À la douane » ; « Étiquette créée » tant qu'il n'y a que les données de commande et `Carrier received the shipment` |
| `Undelivered` | Une tentative de livraison a échoué. | « Tentative de livraison », ou « Prêt à être retiré » ou « Retourné à l’expéditeur » si le dernier scan le dit |
| `Delivered` | Livré au destinataire. | « Livré » |

Shouwu prévient que ses statuts sont « auto-judged by the system », attribués automatiquement, et « only for information reference use », donnés à titre indicatif. Les scans en disent plus :

| Ce qu'écrit Shouwu | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Shipment information received`, `Order information received. We're expecting your parcel to arrive with us.` | Le vendeur a réservé l'envoi. Shouwu n'a pas encore le colis. | « Étiquette créée » |
| `Carrier received the shipment` | Shouwu écrit cette ligne deux heures après les données de commande, des jours avant le premier scan de l'entrepôt : personne n'a encore le colis. | « Étiquette créée » |
| `Arrival processing center`, `Package processing completed` | L'entrepôt de Shouwu en Chine a reçu le colis, puis l'a préparé pour le vol. | « Pris en charge » |
| `Export customs declaration completed`, `Export customs clearance completed`, `Export Released` | La Chine l'a dédouané à l'export ; rien n'est bloqué. | « En transit » |
| `The parcel has arrived at the airport and is currently being loaded onto a flight` | Arrivé à l'aéroport de départ après le dédouanement à l'export, il est chargé dans un avion. | « En transit » |
| `Arrived at Shanghai Pudong Airport`, `Departed from export airport` | À l'aéroport de Shanghai, puis parti de Chine. | « En transit » |
| `Arrived at airport of Destination`, `Shipment arrived in the destination country` | Arrivé par avion dans ton pays. | « En transit » |
| `NOA received` | L'agent sur place a reçu l'avis d'arrivée de la compagnie aérienne ; la douane vient ensuite. | « En transit » |
| `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country`, `Currently processing import customs clearance`, `Customs clearance for imports` | Dédouanement en cours dans ton pays. | « À la douane » |
| `Custom clearance completed`, `Shipment completed the customs clearance process at destination`, `Customs clearance has been completed`, `Customs clearance in the destination country has been completed` | La douane de ton pays l'a libéré. | « En transit » |
| `Scheduled For Delivery` | Ligne de Shouwu après le dédouanement, des jours avant l'agence et la tournée du transporteur : la livraison est prévue, pas encore en cours. | « En transit » |
| `Delivery to local courier`, `The goods have been deliverey to the final delivery service provider`, `The goods have been delivered to the carrier` | Remis au transporteur qui livre chez toi : ce n'est pas encore la livraison (« deliverey » est l'orthographe de Shouwu). | « En transit » |
| `Your consignment has been picked up` | Le transporteur l'a récupéré à l'entrepôt de Shouwu à l'étranger, après la douane : ce n'est ni un enlèvement chez le vendeur, ni un retrait par toi. | « En transit » |
| `FR, Votre envoi est en transit sur nos plateformes logistiques.,运输途中` | Une ligne du transporteur qui livre en France, reprise avec le code du pays devant et « en transit » en chinois derrière. | « En transit » |
| `The shipment has been processed in the destination parcel center` | DHL l'a trié au centre colis qui dessert ton adresse, avant sa tournée. | « En transit » |
| `Out for delivery` | La tournée de livraison du transporteur local. | « En livraison » |
| `The shipment could not be delivered. Please find more information here.` | Le transporteur n'a pas pu livrer ; une autre tournée suit. Le lien annoncé n'est pas repris. | « Tentative de livraison » |
| `Delivered by post man`, `This parcel has been delivered` | La poste ou le transporteur local l'a livré. | « Livré », une fois que le statut de Shouwu dit aussi `Delivered` |

D'autres lignes sont à connaître. `Shipment started the customs clearance process at origin`, c'est le début du dédouanement à l'export en Chine, une étape de routine avant le vol : la douane ne retient pas le colis, et Peek affiche « En transit ». Il en va de même pour `The package is shipped from the transfer warehouse to the airport Export customs clearance in progress` : le colis part de l'entrepôt de transit vers l'aéroport, où la Chine le dédouane à l'export. Et `Abnormal delivery`, suivi d'une raison comme `<Incorrect address.>` (adresse erronée), signale une tournée sans livraison : Peek affiche « Tentative de livraison ».

Pour certains colis, l'historique reprend aussi des lignes de Colis Privé, en anglais :

| Ce qu'écrit Shouwu | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Your parcel has been prepared by your web merchant. It will soon be delivered to Colis Privé.` | Colis Privé a les données du colis ; rien n'a bougé. | « Étiquette créée » |
| `Your parcel arrived at the shipping partner. It will be sent to Colis Privé.` | Chez le partenaire qui l'amène en France. | « En transit » |
| `Your package is in transit to Colis Privé` | En route vers Colis Privé, qui ne l'a pas encore. | « En transit » |
| `Delivered to letterbox` | Colis Privé l'a déposé dans ta boîte aux lettres. | « Livré », une fois que le statut de Shouwu dit aussi `Delivered` |

Quand iMile livre en Italie, l'historique de Shouwu reprend les lignes d'iMile, avec le centre entre crochets 【】 (ton historique peut nommer un autre centre que Milan center ou ROM 10) :

| Ce qu'écrit Shouwu | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `【Milan center】Received.` | Le centre d'iMile a reçu le colis. | « En transit » |
| `【Milan center】Parcel shipped to【ROM 10】.`, `【ROM 10】Arrived.` | Envoyé à l'agence de livraison d'iMile, puis arrivé là-bas. | « En transit » |
| `【ROM 10】Shipment out for delivery.` | La tournée de livraison d'iMile. | « En livraison » |
| `<User not at home>`, `【ROM 10】 Back To DS.` | Le livreur ne t'a pas trouvé, et le colis est revenu à l'agence de livraison. | « Tentative de livraison » |
| `【ROM 10】Parcel shipped to【Milan Transit Center】.` | Après deux tournées sans livraison, l'agence le renvoie au centre d'iMile. | « En transit » |
| `Back to Sender for reason: wrong recipient address or address related problem` | Après ses tournées sans livraison, iMile renvoie le colis, ici à cause de l'adresse (une autre raison peut suivre les deux-points) : il est sur le chemin du retour, pas encore chez l'expéditeur. Contacte le vendeur. | « Problème signalé » |
| `【Milan Transit Center】Order Closed.` | iMile clôt la livraison sans avoir livré ; contacte le vendeur. | « Problème signalé » |

**Quand DPD livre.** Sur une ligne que livre DPD, l'historique de Shouwu continue avec les lignes de DPD, en anglais et terminées chacune par un point : `In transit.`, puis `At parcel delivery centre.`, l'agence DPD qui livre, avant la tournée et de nouveau après une tournée sans livraison (« En transit »), et `Out for delivery.` (« En livraison »). `Unfortunately we have not been able to deliver your parcel. Consignee address not correct.` est une tournée qui a échoué à cause d'une adresse erronée : le colis revient à l'agence DPD, pas chez l'expéditeur (« Tentative de livraison »).

**Des lignes en chinois.** Certains colis ont aussi des lignes que Shouwu écrit en chinois, et Peek les affiche telles quelles. `货物电子信息已经收到` (les données électroniques des marchandises sont reçues) accompagne les données de commande : « Étiquette créée ». Viennent ensuite, toutes affichées « En transit » : `申报过港中` après le code d'un lot (le lot est déclaré pour la route vers Hong Kong), `已到港，安排航班中` (arrivé au point de départ, en attente d'un vol), `预计23号的航班` (le vol réservé, ici le 23 ; avec `二程` devant, pour le second tronçon), `航班已飞` (le vol est parti), `二程航班已飞` (le vol du second tronçon est parti) et `已落地` (atterri). Peek affiche `已落地，清关提货中` (atterri, en cours de dédouanement et de retrait auprès de la compagnie aérienne) comme « À la douane », puis `清关完成` (dédouané) et `已交仓扫描` (scanné à l'entrepôt du transporteur qui livre) de nouveau comme « En transit ».

**Des heures décalées.** Shouwu enregistre l'heure locale de chaque scan, même à l'étranger, comme si c'était l'heure de Pékin (UTC+8), et son site l'affiche dans ton fuseau horaire. Un scan fait en France à 10 h s'affiche donc à 3 h en hiver (UTC+1) et à 4 h en été (UTC+2), et un vol vers la France semble durer sept heures de moins, six en été. Fie-toi à l'ordre des lignes. Peek déduit l'heure de chaque scan de son lieu, ou de l'étape où se trouvait le colis, et l'affiche à l'heure de ton appareil.

## Qui livre les colis Shouwu en France

Shouwu dit travailler directement avec USPS, Royal Mail, La Poste, DHL, DPD et Hermes. Vers la France, il a trois lignes qui couvrent tout le pays : standard, économique et marchandises avec batteries. Selon leurs pages, en chinois, le dernier kilomètre passe aujourd'hui surtout par [Colissimo](carrier:la-poste). Certains historiques montrent aussi [Colis Privé](carrier:colis-prive), avec les lignes du tableau ci-dessus.

**Livraison manquée.** Tentatives, avis de passage et points de retrait suivent les règles du transporteur. Sous `Undelivered`, le suivi de Shouwu te conseille de contacter le transporteur pour une nouvelle livraison ou de retirer le colis avant la fin du délai de garde, faute de quoi il pourrait repartir chez l'expéditeur (« might be returned to the sender »). Réagis donc vite. Voir [avis de passage : que faire](guide:missed-delivery) et [colis non retiré](guide:uncollected-parcel).

## Colis Shouwu bloqué ou qui n'arrive pas

**`Not Found`.** Shouwu donne quatre raisons : il n'a pas encore accepté le colis, ne l'a pas encore scanné, le numéro est faux, ou le colis a été posté il y a si longtemps que son suivi a disparu. Il ajoute que le traitement prend en général quelques jours. Peek affiche « En attente du transporteur » et continue de vérifier. Vérifie le numéro caractère par caractère ; si rien ne vient après quelques jours, demande au vendeur.

**Bloqué sur `Preparing`, `Shipment information received` ou `Carrier received the shipment`.** Shouwu n'a pas encore le colis, ou ne l'a pas encore expédié de son entrepôt. Demande au vendeur quand il le lui a remis.

**Bloqué en douane.** Après `Shipment started the customs clearance process at destination`, `Customs clearance in progress`, `Currently undergoing customs clearance in the destination country` ou `已落地，清关提货中`, le colis attend la douane de ton pays. Depuis le 1er novembre 2026, un achat en ligne venu de Chine est soumis, en plus des 3 € de droits par catégorie de produit (jusqu'à 150 €), aux frais de traitement de l'UE : 2 € par catégorie de produit, quelle que soit la valeur. Les pages des lignes de Shouwu ne disent pas qui les paie. Si on te demande de payer des frais d'importation, lis [colis bloqué en douane](guide:customs).

**Plus rien après `Delivery to local courier`.** Le transporteur a le colis, mais le suivi de Shouwu ne donne pas son numéro. Demande ce numéro au vendeur et colle-le dans Peek. Voir aussi [pourquoi un suivi ne bouge plus](guide:tracking-not-updating) et [le suivi d'un colis venant de Chine](guide:tracking-from-china).

**Livré, mais rien reçu.** Si le site du transporteur indique une livraison que tu n'as jamais reçue, Shouwu aide à chercher mais n'indemnise pas, selon les conditions de ses lignes. Commence donc par le transporteur, puis la boutique. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Perdu ou abîmé.** Le client de Shouwu, c'est le vendeur, et selon ces mêmes conditions il doit réclamer dans les 30 jours suivant l'expédition par Shouwu. Si le colis n'a aucun scan de livraison après son entrée dans l'entrepôt de Shouwu, Shouwu rembourse la valeur déclarée plus les frais de port, 200 yuans maximum par envoi, et rien pour un colis abîmé en route. Demande donc vite un remboursement ou un nouvel envoi à la boutique ou à la plateforme. Voir [colis perdu](guide:lost-parcel).

## Contacter Shouwu : téléphone et service client

Relevé le 30 octobre 2026, le site de Shouwu, www.sedel.com, est en chinois et s'adresse aux vendeurs qui réservent des envois. Il donne des numéros de portable pour le siège de Shanghai et cinq agences, un lien de chat QQ et des QR codes WeChat. Pas d'adresse e-mail, pas de formulaire de contact, aucun contact en anglais et rien pour les destinataires. Le site de suivi ne donne aucun contact.

Pour ta commande, adresse-toi au vendeur : c'est lui qui a réservé l'envoi chez Shouwu et qui fait les réclamations. Pour la livraison elle-même, contacte le transporteur en France.

## Suivre un colis Shouwu avec Peek

[Peek](/) reconnaît un numéro Shouwu à sa forme : SWECN, douze chiffres et YQ, qu'aucun autre transporteur de son catalogue n'utilise. Il interroge alors le suivi de Shouwu. Pas besoin de compte, de code postal ni de numéro de téléphone.

Peek tire l'étape du colis du statut de Shouwu, comme dans les tableaux ci-dessus, et affiche le pays de destination et chaque scan avec son lieu, que la page de Shouwu ne montre pas. Un lieu qui contient un chiffre, comme un code postal, est écarté. Shouwu ne donne pas de date de livraison prévue, donc Peek n'en affiche pas.

Shouwu ne nomme pas à part le transporteur qui livre : Peek n'affiche donc pas de ligne « Livraison par » et ne suit pas le colis chez ce transporteur, même quand un scan nomme Colis Privé. Si tu as le numéro du transporteur, ajoute-le dans Peek comme un colis à part.

Peek vérifie toutes les 10 minutes, et toutes les 2 minutes une fois le colis en livraison (le jour même, dès 6 h, heure locale, et tant que c'est le suivi de Shouwu qui répond ; via des services de suivi, toutes les 15 minutes). Après deux jours sans nouvelles, ou après 12 heures si le colis en est encore à « Étiquette créée » ou à « Pris en charge », Peek ne vérifie plus que toutes les heures. Pour les notifications, tu choisis entre « Chaque scan », « Étapes importantes » (livraison, retrait, douane, tentatives de livraison échouées et retours) et « Livraison seulement ». Peek ne peut contacter ni Shouwu ni le vendeur à ta place.

## Questions sur le suivi Shouwu

### Shouwu International (守务国际), c'est quoi ?

Une société de logistique de Shanghai, 上海守务国际物流有限公司, qui expédie les colis de vendeurs en ligne vers l'étranger. Elle dit avoir démarré en 2018 comme commissionnaire de fret aérien, puis s'être tournée en 2019 vers les lignes e-commerce pour l'Europe et les États-Unis.

### Où suivre un colis Shouwu ?

Dans le champ ci-dessus, ou sur le site de suivi en anglais de Shouwu, admin.shshouwu.com. Utilise le numéro complet, SWECN…YQ.

### Qui livre les colis Shouwu en France ?

Surtout Colissimo, selon les pages de Shouwu ; certains historiques montrent aussi Colis Privé. En cas de doute, demande au vendeur qui livre et sous quel numéro.

### Combien de temps met un colis Shouwu pour arriver en France ?

Shouwu donne un délai de référence de 4 à 7 jours ouvrés sur ses trois lignes vers la France.

### Qui contacter si un colis Shouwu n'arrive pas ?

Le vendeur chez qui tu as commandé : il a réservé l'envoi chez Shouwu et peut réclamer dans les 30 jours suivant l'expédition. Shouwu ne publie aucun contact pour les destinataires.

:::sources
- [Shouwu International : 守务国际包裹追踪 (suivi de colis)](https://admin.shshouwu.com/track.html) – suivi en anglais, 40 numéros, statuts et leurs explications, scans sans lieu, « Copy Link », « Not Found »
- [Shouwu International : 首页 (accueil)](http://www.sedel.com/) – contacts, chat QQ, nom de la société
- [Shouwu International : 关于我们 (à propos)](http://www.sedel.com/about-us.html) – 2018 et 2019, partenaires directs
- [Shouwu International : 主营线路 (lignes principales)](http://www.sedel.com/main-route.html) – lignes par pays
- [Shouwu International : 法国标快 (France, standard)](http://www.sedel.com/main-route-secondary.html?groupId=1&groupItemId=20) – toute la France, surtout Colissimo, choix du numéro, 4 à 7 jours ouvrés, réclamations
- [Shouwu International : 法国经济 (France, économique)](http://www.sedel.com/main-route-secondary.html?groupId=2&groupItemId=23) – surtout Colissimo, 4 à 7 jours ouvrés
- [Shouwu International : 法国带电 (France, avec batteries)](http://www.sedel.com/main-route-secondary.html?groupId=3&groupItemId=26) – surtout Colissimo, 4 à 7 jours ouvrés
- [Commission européenne : EU Customs Reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droits de 3 € depuis le 1er juillet 2026, frais de traitement de 2 € depuis le 1er novembre 2026
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – forme du numéro, suivi et lien, statuts, libellés des scans et leurs étapes (depuis le 9 novembre aussi le dédouanement à l'export et à l'arrivée, le chargement à l'aéroport et la remise au transporteur), lignes de Colis Privé et du transporteur français, lignes d'iMile en Italie (renvoi à l'expéditeur compris), lignes de DPD, heures, lieux, ce qu'affiche Peek
:::
