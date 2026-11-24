---
title: Suivi Yunda Express : bordereaux chinois, statuts et 95546
description: Suis un colis Yunda Express (韵达快递) en Chine : numéros à 13 et 15 chiffres, étapes chinoises, retours à l’expéditeur et support 95546 à Shanghai.
slug: suivi-yunda
published: 2026-11-24
updated: 2026-11-24
---

**Yunda Express** (*Shanghai Yunda Freight Co., Ltd.*, en chinois *韵达快递* / *韵达速递* / *上海韵达货运有限公司*) est l’un des plus grands réseaux nationaux de messagerie express de Chine, basé dans le district de Qingpu à Shanghai ([yundaex.com](https://www.yundaex.com/cn/index.php)). Yunda assure la livraison express nationale, y compris e-commerce et express international, ainsi que l’entreposage, le fret (*韵达快运*), la chaîne du froid et des services de chaîne logistique. Tu peux vérifier un bordereau national sur [web.yundaex.com/infoInquiry](https://web.yundaex.com/infoInquiry) ou coller ton numéro Yunda à 13 ou 15 chiffres dans le champ ci-dessus.

## Numéros de suivi Yunda Express (bordereaux à 13 et 15 chiffres)

Les numéros de suivi nationaux Yunda Express en Chine sont composés uniquement de **13 ou 15 chiffres** :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `3148291736054` | Numéro de bordereau national Yunda Express à 13 chiffres. |
| `437391846205819` | Numéro de bordereau national Yunda Express à 15 chiffres (courant sur les commandes e-commerce récentes). |

Les nombres purs de 13 et 15 chiffres chevauchent ceux d’autres transporteurs ; Peek n’interroge donc Yunda que lorsque tu colles un lien `web.yundaex.com/infoInquiry?...&homeWaybill=` ou que tu sélectionnes toi-même Yunda Express. Voir le guide des [formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi Yunda Express

Le suivi grand public de Yunda attribue cinq libellés chinois standard aux étapes nationales, que Peek affiche clairement — y compris pour distinguer un colis reparti vers l’expéditeur lors d’un retour :

| Libellé Yunda (CN) | Signification | Peek affiche |
| --- | --- | --- |
| `已揽件` | Colis collecté et scanné par le livreur ou l’agence Yunda d’origine. | « Pris en charge » |
| `运输中` | En acheminement entre les centres de tri et plateformes Yunda à travers les provinces et villes chinoises. | « En transit » |
| `派送中` | En livraison avec le livreur local Yunda (ou en cours de remise à l’expéditeur après un scan de retour). | « En livraison » |
| `待取件` | Déposé dans un point relais local, un relais Cainiao ou une consigne en attente de retrait (ou en attente de retrait par l’expéditeur sur un retour). | « Prêt à être retiré » |
| `已签收` | Signé ou retiré par le destinataire (ou par l’entrepôt de consolidation lors d’une réexpédition à l’étranger). | « Livré » |
| `已签收` après un scan explicite de retour (`退回件扫描` / signé par l’expéditeur) | Retourné et signé par l’expéditeur d’origine plutôt que par l’acheteur. | « Retourné à l’expéditeur » |

## Livraison nationale en Chine, entrepôts de consolidation et retours

- **Achats depuis l’étranger via un entrepôt en Chine :** si tu as acheté sur Taobao, 1688 ou Pinduoduo par l’intermédiaire d’un agent ou d’un entrepôt de consolidation (comme [DobroPost](carrier:dobropost) ou un transitaire à Guangzhou, Shenzhen, Dongguan ou Yiwu), `已签收` (« Livré ») chez Yunda Express signifie que le colis est arrivé dans ton **entrepôt de consolidation en Chine**, et non dans ton pays. Après réception, le transitaire attribue un nouveau numéro international.
- **Points relais (`待取件`) :** en Chine, `待取件` signifie que le colis attend dans une station colis ou une consigne que le destinataire vienne le retirer.
- **Détection des retours à l’expéditeur :** le suivi brut de Yunda affiche le même libellé `已签收` pour un colis retourné et signé par l’expéditeur que pour une livraison normale ; Peek analyse le texte du scan pour repérer une mention explicite de retour ou de signature de l’expéditeur et le classe en « Retourné à l’expéditeur ».

## Colis Yunda Express bloqué, mal livré ou manquant

- **Commande sur une place de marché chinoise :** contacte d’abord le vendeur sur Taobao, Tmall, Pinduoduo ou JD — il peut demander à l’agence Yunda (*网点*) ayant collecté le colis d’ouvrir une recherche.
- **Aucun scan après création de l’étiquette :** les vendeurs génèrent souvent un bordereau Yunda à 15 chiffres avant la remise physique ; les scans commencent dès que l’agence enregistre `已揽件`. Voir le guide sur le [suivi bloqué](guide:tracking-not-updating).
- **Indiqué livré (`已签收`) mais introuvable :** vérifie si une consigne, la loge de l’immeuble ou l’entrepôt de consolidation a signé la réception, puis ouvre rapidement une réclamation auprès du vendeur ou du transporteur en cas de perte ou d’avarie. Vois aussi [livré mais non reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client Yunda Express et contacts à Shanghai

Coordonnées officielles publiées sur [Yunda Contact Us](https://www.yundaex.com/cn/about_contact.php) et [yundaex.com](https://www.yundaex.com/cn/index.php) :

- **Ligne nationale service client (Chine) :** **`95546`**.
- **Réclamations et suivi en ligne :** [yundaex.com/cn/about_contact.php](https://www.yundaex.com/cn/about_contact.php) et [web.yundaex.com/infoInquiry](https://web.yundaex.com/infoInquiry).
- **Fax :** `021-39297127` (ligne médias `021-39291355`).
- **Siège social :** *Shanghai Yunda Freight Co., Ltd.* (*上海韵达货运有限公司*), No. 6679 Yinggang East Road, Qingpu District, Shanghai `201700`, Chine (*上海市青浦区盈港东路6679号*).

> Méfie-toi des faux SMS ou liens WeChat te demandant de payer des frais de relivraison ou de saisir tes coordonnées bancaires pour un colis Yunda. Vérifie tout incident dans l’application de ta place de marché ou via la ligne officielle `95546`. Voir le guide sur les [SMS frauduleux de livraison](guide:delivery-scam-texts).

## Suivre un colis Yunda Express avec Peek

Peek n’interroge pas Yunda à partir d’un numéro nu de 13 ou 15 chiffres (`3148291736054` ou `437391846205819`, inventés) : pour 13 chiffres il interroge entre autres [iMile](carrier:imile) et [FAN Courier](carrier:fan-courier), et pour 15 chiffres [BRT](carrier:brt). Appuie sur « Changer » lors de l’ajout ou sur « Changer de transporteur » sur la fiche du colis, où Yunda Express figure sous « Correspond à ce numéro » (rechercher « Yunda » ou « uda International » fonctionne aussi), ou colle un lien `web.yundaex.com/infoInquiry?...&homeWaybill=` dans [Peek](/), qui le reconnaît comme Yunda.

Peek lit le suivi Yunda et affiche l’étape (« Pris en charge », « En transit », « En livraison », « Prêt à être retiré », « Retourné à l’expéditeur », « Livré »), le libellé du scan en anglais, la ville ou le district nommé au début du texte du scan et l’heure de livraison. Yunda fournit ses scans à l’heure de Pékin et Peek les affiche à l’heure de ton appareil. Lors d’un retour, les scans indiquent qu’ils repartent vers l’expéditeur. Pendant l’acheminement, ta propre carte peut terminer le trajet dans la ville du destinataire ; l’adresse précise n’est pas affichée, et les liens partagés n’affichent ni l’une ni l’autre. Peek écarte les noms et téléphones des livreurs, les adresses des points relais, les images de signature et les poids sans étiquette. Le suivi Yunda ne donne pas de jour de livraison estimé.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Yunda affiche `派送中`, toutes les 2 minutes à partir de 6 h heure de Pékin, tant que le suivi Yunda répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi de Yunda ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Yunda Express. », avec un lien « Demande de l’aide à Yunda Express » vers la page de contact de Yunda. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Yunda Express. »

Tu peux choisir les alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek suit uniquement les mises à jour et ne peut ni demander une relivraison, ni récupérer un code de consigne, ni contacter Yunda Express à ta place.

## Questions sur le suivi Yunda Express

### À quoi ressemble un numéro de suivi Yunda Express ?

Les bordereaux nationaux Yunda Express comptent 13 ou 15 chiffres uniquement (par exemple `3148291736054` ou `437391846205819`, inventés).

### Que signifient `已揽件`, `派送中`, `待取件` et `已签收` ?

`已揽件` signifie pris en charge, `派送中` en cours de livraison, `待取件` prêt à être retiré en point relais ou en consigne, et `已签收` livré (sauf s’il suit un scan de retour vers l’expéditeur).

### Pourquoi mon colis Yunda indique-t-il « Livré » alors que je suis hors de Chine ?

Si tu as commandé via un agent ou un entrepôt de consolidation en Chine, Yunda Express assure uniquement le trajet intérieur chinois jusqu’à cet entrepôt. Attends que ton transitaire enregistre le colis et émette un numéro international.

### Comment contacter le service client Yunda Express ?

Appelle la ligne nationale chinoise **`95546`** ou utilise la page officielle de contact et de réclamations sur [yundaex.com/cn/about_contact.php](https://www.yundaex.com/cn/about_contact.php).

:::sources
- [Yunda Express: Official Website](https://www.yundaex.com/cn/index.php) – *Shanghai Yunda Freight Co., Ltd.* (*上海韵达货运有限公司*) services express nationaux, fret, chaîne du froid et entreposage
- [Yunda Express: Contact Us](https://www.yundaex.com/cn/about_contact.php) – ligne `95546`, fax `021-39297127`, réclamations en ligne et siège de Qingpu à Shanghai (page d’aide liée par Peek)
- [Yunda Express: Waybill Inquiry](https://web.yundaex.com/infoInquiry) – portail officiel de suivi national
- [Universal Parcel Scraper: yunda](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/yunda) – règle de bordereaux à 13/15 chiffres, règle de lien `web.yundaex.com`, table des 5 statuts chinois, détection de retour, extraction de ville et horaires de Pékin
:::
