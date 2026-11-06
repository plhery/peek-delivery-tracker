---
title: Yanwen suivi : numéros, statuts et qui contacter
description: Suivre un colis Yanwen venu de Chine : à quoi ressemble le numéro, ce que veut dire chaque statut, qui le livre chez toi et qui contacter s'il bloque.
slug: yanwen-suivi
published: 2026-10-11
updated: 2026-11-06
---

Yanwen est une société de logistique chinoise, fondée à Pékin en 1998, qui expédie à l'étranger les colis de vendeurs en ligne puis les confie à un transporteur local pour le dernier kilomètre. Pour suivre ton colis, saisis son numéro sur [track.yw56.com.cn](https://track.yw56.com.cn/en/index), le site de suivi de Yanwen, ou colle-le dans le champ ci-dessus. Le numéro vient de la boutique ou de la plateforme où tu as commandé, dans les détails d'expédition de ta commande.

## Numéro de suivi Yanwen : à quoi il ressemble

Yanwen ne publie aucun exemple de numéro et aucune page d'aide pour les destinataires. Les formes que portent ses colis, avec des chiffres inventés :

| Numéro | À qui il appartient |
| --- | --- |
| `BYS123456789` | BYS et 9 chiffres : Yanwen. |
| `UL000000005YP` | Deux lettres, 9 chiffres, puis YP : Yanwen. UL et UK sont des débuts fréquents. |
| `YWABC123456789012` | YW, trois lettres et 12 chiffres : le numéro du dernier kilomètre d'un colis livré aux États-Unis. |
| `RR000000005CN` | La même forme postale terminée par CN : [China Post](carrier:china-post), pas Yanwen. |
| `YT1234567890123456` | Pas Yanwen, mais [YunExpress](carrier:yunexpress). |
| `LP12345678901234` | Pas Yanwen, mais [Cainiao](carrier:cainiao), la référence logistique d'AliExpress. |

Un numéro postal de Yanwen se termine toujours par YP ; toute autre fin appartient à une autre poste. La page de résultat de Yanwen affiche deux numéros : le « Tracking Number », celui de Yanwen, et le « Last Mile Tracking », celui du transporteur qui livre dans ton pays. Si la boutique t'a donné ce numéro local, le site de Yanwen y répond aussi. Si tu n'as aucun numéro, demande-le à la boutique.

Le site de Yanwen accepte jusqu'à 100 numéros à la fois, mais son suivi en libre-service ne couvre que les envois de la dernière année. Il ne suit pas les produits de China Post.

## Suivi colis Yanwen : ce que veulent dire les statuts

Le site de suivi de Yanwen n'existe qu'en anglais et en chinois, et les lignes de suivi propres à Yanwen sont en anglais dans les deux versions. Pas de version française : voici les lignes qu'on voit le plus, dans l'ordre où un colis les rencontre.

| Ce qu'affiche Yanwen | Ce que ça veut dire pour toi |
| --- | --- |
| `Order processed by shipper` | Le vendeur a réservé l'envoi. Yanwen n'a pas encore le colis. |
| `Yanwen Pickup Scan` | Yanwen a le colis en Chine. |
| `Yanwen facility - Outbound` | Il a quitté le centre de tri de Yanwen. |
| `Tendered for Delivery` | Remis pour le voyage vers l'étranger, quelques minutes après la sortie du centre de tri de Yanwen et avant le dédouanement à l'export. Malgré les mots, ce n'est pas encore la livraison. |
| `International shipment release - Export` | Le dédouanement à l'export en Chine est fait. |
| `Port of departure - Departure` | Remis à la compagnie aérienne, il s'envole. |
| `Port of destination - Arrival` | Arrivé dans ton pays ou près de lui. |
| `Arrived at customs clearance warehouse` | Il attend le dédouanement à l'arrivée. |
| `Cleared Customs` | La douane l'a libéré. |
| `Batch delivery to carrier` | Remis au transporteur local. Ses scans suivent. |
| `Delivered` | Le colis est livré ; Yanwen ajoute le nombre de jours de trajet. |

`Order Submited.`, `Waybill Generated` et `Shipping Order Created.` veulent dire la même chose que la première ligne : seule l'étiquette existe, et le colis n'a peut-être pas quitté le vendeur. `Pick up by local carrier at destination port` équivaut à `Batch delivery to carrier`. Ensuite, les lignes sont celles du transporteur local, parfois précédées d'un code pays ou du nom d'un dépôt entre crochets.

Certains colis passent à China Post après `Yanwen facility - Outbound`. Yanwen affiche alors les lignes de China Post en chinois, expliquées sur la page [China Post](carrier:china-post) ; `中国邮政已收取邮件` est cette remise. `【…】退回，备注:安检退回` veut dire que le contrôle de sécurité d'un centre de tri a refusé le colis, et que le centre règle le problème avant de le réexpédier : il ne repart pas chez le vendeur. Peek l'affiche comme « Problème signalé ».

Au-dessus des scans, Yanwen range chaque colis dans une catégorie. `Info_Received`, c'est l'étape de l'étiquette. `In_Transportation` couvre tout, du vol à la douane et à la livraison locale. `End_of_Tracking` veut dire que Yanwen a arrêté de suivre le colis : ça ne dit rien de l'endroit où il est, ce n'est ni une livraison ni une perte. `Not_Found`, avec la ligne `No information was found`, veut dire que Yanwen n'a rien sous ce numéro. Un colis livré affiche la durée du trajet, par exemple « Delivered（7d） ».

## Yanwen livraison en France, en Belgique et en Suisse

Yanwen ne livre pas lui-même en Europe : il dit travailler avec des transporteurs locaux pour le dernier kilomètre. Quand il nomme ce transporteur, la page de résultat affiche un encadré avec « Distributor », « Distributor Website » et « Contact Number ». Pour certains colis, l'encadré reste vide.

Le transporteur dépend du pays et du service choisi par le vendeur. Pour la France, le catalogue de Yanwen pour les vendeurs propose une ligne [Colissimo](carrier:la-poste) et une ligne « CP », sans fiche détaillée. Sur la ligne Colissimo, Yanwen imprime directement l'étiquette et le numéro Colissimo, et renvoie au suivi de laposte.fr : c'est La Poste qui livre, avec ce numéro. Yanwen reprend alors les phrases de La Poste mot pour mot : `Votre Colissimo va bientôt nous être confié ! Il est en cours de préparation chez votre expéditeur.` arrive avant même la prise en charge par Yanwen, et Peek l'affiche comme « Étiquette créée » ; `Votre colis est en transit sur nos plateformes logistiques.` et `Votre Colissimo est en cours de traitement sur le site de tri local.` donnent « En transit », `Votre colis est sur son site de distribution. Nous le préparons pour le mettre en livraison.` donne « En livraison ».

Aucune page de Yanwen ne dit qui livre en Belgique ou en Suisse. En Australie, Yanwen remet ses colis par lots à J&T Express Australia au port d'arrivée ; les scans de J&T apparaissent ensuite dans l'historique de Yanwen, et Peek affiche la prise en charge par J&T comme « En transit ».

Tentatives, avis de passage, nouvelle date et durée de garde en point relais relèvent du transporteur local : Yanwen n'en publie aucune règle. Pour un colis Colissimo, voir la page [La Poste](carrier:la-poste). `Available_For_Pick_Up` veut dire que le colis t'attend dans un point de retrait ; `Undelivered`, que la livraison a échoué. Avec le numéro « Last Mile Tracking » et le contact de l'encadré, demande à ce transporteur une nouvelle livraison.

La page de résultat propose aussi une fenêtre « Proof of Delivery Images », qui s'ouvre avec le code postal du destinataire. Chaque scan affiche son décalage horaire GMT, et un bouton change le fuseau.

## Colis Yanwen bloqué, perdu ou renvoyé : que faire

**Pas encore de suivi.** Tant que le colis est en `Info_Received`, le vendeur a créé une étiquette mais ne l'a peut-être pas encore remis. Demande à la boutique quand il est parti.

**Bloqué depuis des jours.** Une fois le colis chez le transporteur local, regarde son propre suivi avec le numéro « Last Mile Tracking ». Les silences pendant le vol ou à la douane sont expliqués dans [le suivi d'un colis venu de Chine](guide:tracking-from-china), les autres dans [pourquoi un suivi ne bouge plus](guide:tracking-not-updating).

**Bloqué en douane.** Sur sa ligne Colissimo, la TVA est réglée d'avance : la boutique la déclare avec son numéro IOSS, ou Yanwen la lui facture, et le destinataire n'a alors pas de TVA à payer ; sur sa ligne pour l'UE, tous les droits et taxes sont à la charge de l'expéditeur. Depuis le 1er juillet 2026, ces envois paient 3 € de droits par catégorie de produit jusqu'à 150 € ; depuis le 1er novembre 2026, la douane perçoit en plus, avec les droits, 2 € de frais de traitement de l'UE par ligne de déclaration, quelle que soit la valeur. Les fiches de Yanwen ne disent pas qui paie ces 2 €. Si on te demande de payer, vérifie auprès de la boutique et lis [colis bloqué en douane](guide:customs).

**Livré mais pas reçu.** C'est le transporteur local qui a livré : commence par lui, puis par la boutique. Si le site du transporteur montre le colis livré, Yanwen aide la boutique à le chercher, mais ne l'indemnise pas. Étape par étape : [colis livré mais non reçu](guide:delivered-not-received).

**Perdu, abîmé ou renvoyé.** Yanwen ne publie pour les destinataires ni procédure de réclamation, ni délai, ni indemnisation. Ses sites s'adressent aux vendeurs, et c'est la boutique ou la plateforme qui a un contrat avec Yanwen : demande-lui un remboursement ou un nouvel envoi. Préviens-la vite : elle peut lancer une recherche à partir de 10 jours après l'envoi, et plus aucune passé 60 jours. Sur la ligne Colissimo, un colis non livré ne revient pas en Chine : si la boutique ne demande pas de nouvelle livraison dans les 14 jours, il est détruit. `Exception` et `Returned` sont les catégories de Yanwen pour ces cas.

**Colis ancien.** Après un an, un envoi sort du suivi en libre-service ; selon une annonce de Yanwen, une personne de sa hotline peut encore le retrouver.

## Yanwen contact : téléphone et e-mail

Relevé le 11 octobre 2026, Yanwen publie ces canaux :

- **Hotline :** 400-108-5656, du lundi au samedi de 9 h à 18 h, selon sa page de contact en chinois. Un numéro en 400 est un numéro de service chinois ; Yanwen ne dit pas s'il fonctionne depuis l'étranger, ni en quelles langues on y répond.
- **E-mail :** marketing@yw56.com.cn, la seule adresse de son site anglais. Yanwen ne la présente pas comme une adresse de service client.
- **Autres canaux :** un chat en bas à droite de yw56.com.cn, un portail client et un compte WeChat, tous en chinois et destinés aux vendeurs.

Yanwen ne publie aucun contact pour la France, la Belgique ou la Suisse. Pour la commande, la boutique ou la plateforme répond plus vite ; pour la livraison, contacte le transporteur nommé dans l'encadré, au « Contact Number » s'il est indiqué.

## Suivre un colis Yanwen avec Peek

[Peek](/) reconnaît d'emblée les numéros BYS, les numéros YP et les numéros YW américains. Un numéro YP dont le chiffre de contrôle est faux n'est pas reconnu comme Yanwen : choisis Yanwen avec « Changer ». Un lien de track.yw56.com.cn qui contient `nums=` est lu comme Yanwen quel que soit le numéro, donc il marche aussi pour le numéro local.

Il affiche le statut, l'historique avec la ville ou la région de chaque scan sur une carte, l'heure de livraison, le pays de destination et le transporteur local avec son numéro (« Livraison par » ce transporteur, ou « Annoncé à …, pas encore reçu » tant que Peek ne trouve chez lui que l'annonce du colis, sans scan de sa part). Quand Yanwen nomme un transporteur local que Peek connaît et que le numéro lui correspond, il l'interroge et bascule sur lui dès qu'il montre une étape plus récente ; pour un numéro YP, il peut aussi suivre la poste du pays de destination. Yanwen ne donne pas de date de livraison prévue ni de point de retrait, et Peek écarte références de commande, contacts, codes postaux, codes PIN de livraison et numéros de porte.

Les heures sont lues avec le décalage GMT de Yanwen et affichées dans le fuseau horaire de ton appareil ; les scans répétés sont supprimés. Les scans de La Poste que Yanwen traduit en anglais, comme `Arrived at destination`, portent chez lui l'heure UTC sous le décalage français : Peek les remet à l'heure de La Poste, une ou deux heures plus tard. Peek affiche `Delivered` et `Out for delivery` avec ses propres mots, en français ; les autres lignes restent telles qu'écrites. Il vérifie toutes les 10 minutes, toutes les 2 minutes une fois le colis en livraison (le jour même, dès 6 h, heure locale, et tant que c'est le suivi de Yanwen qui répond ; via des services de suivi, toutes les 15 minutes), et peut t'envoyer des notifications : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après deux jours sans nouvelles, ou après 12 heures si le colis en est encore à « Étiquette créée » ou à « Pris en charge », Peek ne vérifie plus que toutes les heures. Il ne peut contacter ni Yanwen ni la boutique à ta place.

## Questions sur le suivi Yanwen

### Combien de temps met un colis Yanwen pour arriver en France ?

Yanwen ne publie aucun délai de livraison pour les destinataires : fie-toi à l'estimation que la boutique ou la plateforme affiche pour ta commande. Une fois livré, le suivi de Yanwen indique le nombre de jours du trajet.

### Peut-on retirer un colis Yanwen en point relais ?

Parfois : Yanwen a une catégorie `Available_For_Pick_Up` pour un colis qui attend dans un point de retrait. Le point et la durée de garde dépendent du transporteur local, pas de Yanwen : La Poste pour un colis Colissimo.

### Yanwen, avis : est-ce un transporteur fiable ?

Yanwen est une vraie société de logistique, fondée à Pékin en 1998, avec son propre site de suivi. Elle dit être devenue en 2015 un prestataire logistique recommandé par AliExpress et eBay.

### À quelle heure Yanwen livre-t-il ?

Yanwen ne livre pas lui-même chez toi : c'est le transporteur local qui fixe ses horaires. Une fois le colis livré, l'heure de livraison apparaît dans le suivi.

:::sources
- [Yanwen : YW Tracking](https://track.yw56.com.cn/en/index) – site de suivi, langues, colonnes du résultat, catégories, encadré du transporteur, preuve de livraison, fuseaux, hotline
- [Yanwen : 燕文追踪 (suivi, en chinois)](https://track.yw56.com.cn/) – lignes de suivi en anglais, produits China Post
- [Yanwen : page d'accueil YANWEN EXPRESS](https://www.yw56.com.cn/en/) – société, Pékin 1998, dernier kilomètre par des transporteurs locaux, hotline, e-mail
- [Yanwen : Company Profile](https://www.yw56.com.cn/webfile/Profile/) – AliExpress et eBay
- [Yanwen : Contact Us](https://www.yw56.com.cn/webfile/ContactUs/) – hotline, e-mail
- [Yanwen : 联系我们 (contact, en chinois)](https://www.yw56.com.cn/webfile/联系我们/) – horaires de la hotline, lignes France Colissimo et CP (relu le 6 novembre 2026)
- [Yanwen : 燕文法国Colissimo快线 (fiche de la ligne Colissimo, en chinois)](https://www.yw56.com.cn/cn/wishDetail.html?productCode=779&productName=燕文法国Colissimo快线-普货) – numéro et étiquette Colissimo, suivi sur laposte.fr, TVA payée par l'expéditeur, 3 € de droits, pas de retour en Chine, 14 jours, recherche de 10 à 60 jours, livré mais pas reçu
- [Yanwen : 燕文TemuY2欧盟快线 (fiche de la ligne UE, en chinois)](https://www.yw56.com.cn/cn/wishDetail.html?productCode=1938&productName=燕文TemuY2欧盟快线-普货) – droits et taxes à la charge de l'expéditeur
- [Commission européenne : EU Customs Reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droits de 3 € depuis le 1er juillet 2026, frais de traitement de 2 € depuis le 1er novembre 2026
- [Yanwen : 关于官方快件查询入口及服务的通告 (canaux de suivi officiels)](https://www.yw56.com.cn/webfile/ANNOUNCEMENTS/2025/06-30/7345353199651393536.html) – 100 numéros, limite d'un an, recherche par téléphone, chat, portail, WeChat
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek affiche, phrases de La Poste reprises par Yanwen et heure de ses scans traduits, scans de J&T Express Australia après la remise par lots
:::
