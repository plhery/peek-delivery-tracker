---
title: Suivi DayOne : numéros DOT, statuts et livraison
description: Suivre un colis DayOne depuis la Chine : format d’un numéro DOT, signification des statuts sur DayOneTrack, qui livre et qui contacter en cas de retard.
slug: suivi-dayone
published: 2026-11-23
updated: 2026-11-23
---

DayOne est l’entrepôt logistique qui expédie depuis la Chine les commandes en ligne de boutiques partenaires. Ce n’est pas un transporteur : DayOne indique être un entrepôt d’exécution de commandes (« order fulfillment warehouse »), et non un prestataire de livraison. D’autres sociétés transportent le colis, et le suivi de DayOne, [DayOneTrack](https://www.dayonetrack.com/), disponible en anglais et en chinois, affiche leurs scans. Recherche ton numéro DOT sur ce site ou colle-le dans le champ ci-dessus. C’est la boutique qui te donne ce numéro dans les détails d’expédition de ta commande.

## Numéro de suivi DayOne : à quoi il ressemble

DayOne ne publie aucun format officiel. Voici la forme reconnue par Peek comme étant celle de DayOne, avec des chiffres inventés :

| Numéro | Ce que c’est |
| --- | --- |
| `DOT0482917364058291` | DOT suivi de 16 chiffres : un numéro DayOne. |
| `DOT1482917364058291` | DOT1 suivi de 15 autres chiffres : également DayOne. Le suivi DayOne interroge un autre système pour ceux-ci. |
| `DOT048291736405829` | Seulement 15 chiffres : ce n’est pas le format DayOne, et Peek ne reconnaît pas DayOne. Vérifie s’il manque un chiffre. |

Dans Peek, les minuscules, les espaces et les tirets n’ont pas d’importance quand tu colles le numéro seul. Dans un message entier, garde DOT collé aux chiffres : avec un espace juste après DOT, Peek peut ne lire que les chiffres et nommer un autre transporteur.

**Où le trouver.** Dans l’e-mail d’expédition de la boutique ou sur ta page de commande. Si tu ne le trouves pas, consulte [comment trouver ton numéro de suivi](guide:find-tracking-number).

**Sur le suivi de DayOne.** Tu peux rechercher jusqu’à 10 numéros à la fois, un par ligne. Le suivi ne recherche que les numéros contenant DOT0 ou DOT1 : tout autre numéro, comme celui d’une poste locale, affiche `Not found`. À côté du numéro de suivi, un résultat peut indiquer un `Reference Number` (la référence propre à la boutique) ainsi que le pays de destination du colis.

## Statuts de suivi DayOne expliqués

Le suivi DayOne attribue un statut global à chaque colis, en anglais et en chinois, et liste les scans sous `Package Track`, du plus récent au plus ancien :

| Statut DayOne | Ce que ça signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Not found` (查询不到) | DayOne n’a encore rien sous ce numéro. Le suivi ajoute : « The order information has not been updated yet ». | « En attente de DayOne » |
| `Info received` (收到信息) | Les données d’expédition sont enregistrées. Le colis n’a peut-être pas encore quitté l’entrepôt. | « Étiquette créée » |
| `In transit` (运输途中) | En chemin. | « En transit » |
| `Pick up` (到达待取) | Arrivé et en attente de retrait. | « Prêt à être retiré » |
| `Out for Delivery` (派送途中) | En cours de livraison. | « En livraison » |
| `Undelivered` (投递失败) | Une tentative de livraison a échoué. | « Tentative de livraison » |
| `Delivered` (成功签收) | Livré. | « Livré » |
| `Alert` (可能异常) | Il y a peut-être un problème. | « Problème signalé » |
| `Expired` (运输过久) | En chinois, « en transit trop longtemps » : le colis voyage depuis longtemps. | le statut du scan le plus récent |

Peek se fie au scan le plus récent qu’il comprend. Il n’utilise le statut global de DayOne que s’il ne comprend aucun des scans.

Chaque scan indique une heure, un lieu et le libellé de la société qui détient le colis : Yanwen en Chine, puis la poste de ton pays. Voici les scans habituels, dans l’ordre du trajet :

| DayOne affiche | Ce que ça signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Order processed by shipper` | Yanwen a reçu les données de la commande. Personne n’a encore scanné le colis. | « Étiquette créée » |
| `Yanwen Pickup Scan`, `Processing information input` | Yanwen a le colis en Chine et le traite. | « Pris en charge » |
| `Yanwen facility - Outbound`, `Arrived at domestic terminal station` | Il a quitté le centre de tri Yanwen puis atteint un terminal en Chine avant la douane d’exportation. | « En transit » |
| `Port of departure - Received by carrier`, `International shipment release - Export`, `Port of departure - Departure` | Au port de départ, passé par la douane d’exportation chinoise (pas la tienne), puis expédié par avion. | « En transit » |
| `Port of destination - Arrival`, `ARRIVED AT DESTINATION AIRPORT` | Il a atterri dans ton pays ou à proximité. La douane d’importation reste à passer. | « En transit » |
| `CLEARED BY CUSTOMS`, `COLLECTED FROM AIRPORT TERMINAL`, `ARRIVED AT FACILITY IN DESTINATION COUNTRY` | La douane de ton pays l’a libéré et il a été acheminé de l’aéroport vers un centre de traitement. | « En transit » |
| `RECEIVED BY AUSTRALIA POST FOR TRANSPORTATION TO PROCESSING FACILITY` | En Australie : remis à Australia Post. | « En transit » |
| `ITEM PROCESSED AT POST FACILITY`, `PROCESSED THROUGH AUSTRALIA POST FACILITY`, `TRANSFERRED` | Trié par la poste et acheminé. | « En transit » |
| `WITH AUSTRALIA POST FOR DELIVERY TODAY` | En livraison aujourd’hui. | « En livraison » |
| `DELIVERED WITH SAFE DROP` | Livré et déposé dans un endroit sûr à l’adresse. | « Livré » |

`SHIPPING INFORMATION RECEIVED`, `SHIPPING INFORMATION RECEIVED BY AUSTRALIA POST` et `SHIPPING INFORMATION APPROVED BY AUSTRALIA POST` signifient que la poste a reçu les données du colis. Ces lignes peuvent apparaître après des scans ultérieurs : ce n’est pas un retour en arrière, et Peek conserve l’étape la plus avancée.

Si l’unique scan est `Shipment has begun. In transit to destination city`, DayOne a remis le colis à [YunExpress](carrier:yunexpress), et la suite de son historique figure sur le suivi de YunExpress sous un numéro YunExpress (`YT` suivi de 16 chiffres). `Clearence processing completed - Export` correspond au dédouanement export de YunExpress, avec sa propre orthographe.

## Qui livre les colis DayOne

DayOne n’indique pas qui assure la livraison dans ton pays. Ce sont ses scans qui montrent qui a le colis : Yanwen en Chine, puis la poste de ton pays, comme [Australia Post](carrier:australia-post) pour les colis à destination de l’Australie.

DayOne ne publie aucune règle sur les tentatives de livraison, les avis de passage ou les points de retrait : tout dépend du transporteur livreur. Après `Pick up` ou `Undelivered`, cherche un avis ou un message de cette société. Voir [livraisons manquées](guide:missed-delivery) et [colis non retirés](guide:uncollected-parcel).

**Douane dans l’UE.** Les biens achetés en ligne hors de l’UE supportent un droit de douane de 3 € par article sur les colis valant jusqu’à 150 € depuis le 1er juillet 2026, et une taxe européenne de traitement de 2 € par article, quelle que soit la valeur, depuis le 1er novembre 2026. Un article désigne les marchandises partageant le même classement tarifaire, la même description et, si requis, la même origine. Le site de DayOne ne dit rien sur les droits, les frais ni qui les perçoit. Ailleurs, les frais dépendent des règles de ton pays : voir [colis retenus en douane](guide:customs).

## Colis DayOne bloqué, perdu ou retourné

DayOne te renvoie vers la boutique. Son suivi précise que pour tout problème de logistique, perte, remboursement ou renvoi, il faut contacter le service client du magasin où la commande a été passée.

**`Not found`.** DayOne n’a encore rien sous ce numéro. Vérifie le format : DOT et 16 chiffres. S’il est correct, la commande n’est peut-être pas encore partie. Peek affiche « En attente de DayOne » et continue de vérifier. Si DayOne ne connaît toujours pas le numéro deux jours plus tard et qu’aucun autre suivi n’a répondu, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » et vérifie dès lors une fois par jour, puis une fois par semaine après sept jours. Si rien ne bouge au bout de quelques jours, demande à la boutique.

**Uniquement `Info received` ou `Order processed by shipper`.** Personne n’a encore scanné le colis. Si la situation dure plusieurs jours, demande à la boutique quand il a été expédié.

**Aucun nouveau scan pendant plusieurs jours.** Un colis reste souvent silencieux pendant un long vol ou à la douane. Si un colis encore à « Étiquette créée », « Pris en charge » ou « En transit » reste quatre jours sans nouveau scan, Peek le signale par « Aucune mise à jour depuis quatre jours » (ou « Aucune mise à jour depuis … »), explique qu’« Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger DayOne. » et propose « Demande de l’aide à DayOne ». Après trois semaines sans nouveau scan, Peek affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger DayOne. » Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating) et [suivre un colis venant de Chine](guide:tracking-from-china).

**`Alert` ou `Expired`.** Lis les derniers scans, puis demande de l’aide à la boutique avec ton numéro DOT.

**Perdu, endommagé ou retourné.** DayOne ne publie aucune procédure de réclamation pour les destinataires. Demande un remboursement ou un nouvel envoi à la boutique. Voir [colis perdus](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

**Livré mais absent.** `DELIVERED WITH SAFE DROP` signifie que le colis a été déposé à ton adresse dans un lieu sûr. Regarde autour de chez toi, demande aux voisins, puis préviens la boutique. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received).

## Contacter DayOne

D’après le [suivi de DayOne](https://www.dayonetrack.com/) :

- **À propos de ton colis :** la boutique auprès de laquelle tu as commandé, comme le demande DayOne.
- **E-mail professionnel :** info@dayone-shipping.com, fourni par DayOne pour la coopération et les demandes commerciales hors suivi de colis.

Le site de DayOne n’indique ni numéro de téléphone, ni chat, ni formulaire de contact, ni adresse postale. Il indique ne stocker que des numéros de bordereau et ne conserver aucun détail de commande.

## Suivre un colis DayOne avec Peek

[Peek](/) identifie immédiatement DayOne pour un numéro DOT suivi de 16 chiffres (« Transporteur détecté »). Un lien `dayonetrack.com` contenant `trackNo=` fonctionne aussi. Pour tout autre numéro, colle-le tout de même : Peek suit plus de 4 500 transporteurs. Lorsqu’un format correspond à plusieurs transporteurs, Peek affiche « Vérification des services de suivi… » pendant la recherche. Si Peek ne nomme pas DayOne pour ton numéro, appuie sur « Changer » et choisis DayOne sous « Tous les transporteurs » (la recherche « Day One » le trouve aussi), ou utilise « Changer de transporteur » sur un colis déjà ajouté.

Pour un numéro `DOT0`, Peek lit directement le suivi de DayOne : le statut, chaque scan avec son lieu, le pays de destination et l’heure de livraison. Pour un numéro `DOT1`, il interroge des services de suivi à la place.

Lorsque DayOne indique le décalage horaire d’un scan, Peek l’utilise. Pour un scan sans fuseau, Peek déduit l’heure à partir du lieu ou des scans voisins (celle de ton pays dès qu’un scan indique l’arrivée). Il affiche chaque horaire dans le fuseau de ton appareil. Il exclut les codes postaux, le nom de la personne ayant réceptionné ou signé le colis et le `Reference Number` de la boutique. DayOne ne donne ni date de livraison prévue ni poids.

Le suivi de DayOne n’affiche pas le numéro du transporteur final, mais sa réponse interne le contient parfois. Peek interroge alors aussi cette société : la poste de ton pays pour un numéro postal (deux lettres, neuf chiffres, deux lettres) comme Royal Mail, USPS, Postes Canada, An Post ou Australia Post, ou le transporteur unique correspondant au format, comme YunExpress pour un numéro `YT`. Dès que ce transporteur affiche un scan au moins aussi récent que le dernier de DayOne, Peek bascule dessus, affiche son numéro à côté du numéro DOT et conserve l’historique de DayOne.

Pour un numéro `DOT0`, tant que tu gardes le colis ouvert dans Peek ou le jour de livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes, et à partir de 6 h (heure locale) le jour où DayOne scanne le colis en livraison jusqu’à toutes les 2 minutes tant que le suivi propre de DayOne répond ; après deux jours sans nouvelle (12 heures tant que le colis reste à « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures, et s’il reste à « Étiquette créée » pendant sept jours, toutes les 6 heures. Pour un numéro `DOT1`, suivi via des services de suivi, Peek vérifie toutes les heures, toutes les 3 heures après une journée sans nouveau scan, toutes les 6 heures après sept jours à « Étiquette créée », et jusqu’à toutes les 15 minutes le jour où le colis est en cours de livraison, tant que le colis est ouvert dans Peek ou que les notifications sont activées. Il peut te notifier pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut pas contacter DayOne ni la boutique à ta place.

## Questions sur le suivi DayOne

### DayOne est-elle un transporteur ?

Non. DayOne indique être un entrepôt d’exécution de commandes (« order fulfillment warehouse »), et non un transporteur. D’autres entreprises assurent l’acheminement : dans les scans de DayOne, Yanwen en Chine puis la poste de ton pays.

### Où puis-je suivre un numéro DOT ?

Sur le suivi de DayOne, [dayonetrack.com](https://www.dayonetrack.com/), en anglais ou en chinois, jusqu’à 10 numéros à la fois. Ou en le collant dans Peek.

### Pourquoi le suivi DayOne indique-t-il Not found ?

DayOne n’a encore rien enregistré sous ce numéro. Vérifie que tu as saisi DOT et les 16 chiffres. Si le numéro est exact, attends un jour ou deux puis demande à la boutique si le colis a été expédié.

### Pourquoi les heures sur le suivi DayOne semblent-elles dans le désordre ?

Chaque scan affiche l’heure locale du lieu où il a été fait, sans fuseau horaire, et le site trie les scans selon ces heures brutes. En hiver dans l’hémisphère nord, Pékin a 8 heures d’avance sur Londres et 13 sur New York : un scan dans ton pays peut donc paraître plus ancien que le dernier scan en Chine. Sydney a 3 heures d’avance sur Pékin en été austral, si bien que ses scans paraissent plus récents. Peek convertit chaque horaire dans le fuseau de ton appareil.

:::sources
- [DayOne: Dayone Tracking](https://www.dayonetrack.com/) – entrepôt et non transporteur ; service après-vente vers la boutique ; confidentialité ; e-mail professionnel ; anglais et chinois ; 10 numéros à la fois ; recherches DOT0 et DOT1 ; statuts ; champs de résultat ; horaires
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € depuis le 1er juillet 2026, taxe européenne de traitement de 2 € depuis le 1er novembre 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € par article quelle que soit la valeur, définition d’un article
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format de numéro, liens et requêtes, libellés et codes des scans, fuseaux horaires, numéro du transporteur final, bascule, ce que Peek lit
:::
