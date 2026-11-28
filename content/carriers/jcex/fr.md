---
title: Suivi colis JCEX : numéros JCZ, statuts et livraison
description: Suivre un colis JCEX venu de Chine : à quoi ressemble un numéro JCZ, ce que veut dire chaque statut, qui livre en France et qui contacter s'il bloque.
slug: jcex-suivi-colis
published: 2026-11-28
updated: 2026-11-28
---

JCEX (佳成国际, Jiacheng International Logistics) est une société de logistique de Hangzhou, en Chine, qui expédie à l'étranger les colis de vendeurs en ligne et les remet à un transporteur de ton pays pour la livraison finale — comme Colissimo en France ou [USPS](carrier:usps) aux États-Unis. Suis le numéro sur le suivi de JCEX, [track.jcex.com](https://track.jcex.com/), ou dans le champ ci-dessus. C'est la boutique qui le fournit dans les informations d'expédition de ta commande.

## Numéro de suivi JCEX : à quoi il ressemble

JCEX ne publie aucun format officiel de numéro. Ses numéros de bordereau commencent par `JCZ` et finissent par `DH`. Avec des chiffres inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `JCZ4829105736DH` | `JCZ`, 10 chiffres et `DH` : un bordereau JCEX. |
| `JCZ482910573DH` | Seulement 9 chiffres : un numéro mal saisi, pas un format JCEX. |

Minuscules et espaces ne gênent pas Peek. Le suivi de JCEX accepte jusqu'à 100 numéros à la fois, séparés par des virgules.

**Où le trouver.** Aucune page de JCEX n'explique comment le numéro te parvient. C'est le vendeur qui réserve l'envoi et communique le numéro dans l'e-mail d'expédition ou sur la page de commande. Si tu ne le trouves pas, lis [où trouver son numéro de suivi](guide:find-tracking-number).

**Un second numéro.** Le suivi de JCEX peut afficher un second numéro, `跟踪单号` (numéro de suivi), à côté du bordereau lorsque JCEX le renseigne. Peek ne le lit pas : quand Colissimo ou USPS assurent la livraison, leurs scans apparaissent à la place dans l'historique de JCEX.

## Suivi JCEX : ce que veulent dire les statuts

Le suivi de JCEX est uniquement en chinois, mais le texte de chaque ligne peut être en anglais, en chinois ou dans les deux langues, après un décalage UTC, une heure et un lieu. Les lignes que l'on voit habituellement, dans l'ordre du trajet, et ce que Peek affiche tant que chacune est la plus récente :

| Ce qu'affiche JCEX | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Shipment order info received` | Le vendeur a transmis la commande à JCEX. JCEX n'a pas encore le colis. | « Étiquette créée » |
| `Arrive at the delivery point` | L'entrepôt de JCEX en Chine a reçu le colis. Ce n'est pas ton point de livraison local. | « En transit » |
| `Item outbound in sorting center` | Il a quitté le centre de tri de JCEX. | « En transit » |
| `Departed from origin facility to airport` | En route vers l'aéroport. | « En transit » |
| `Flight dispatched（航班已飞）` | L'avion a décollé. | « En transit » |
| `Flight arrived（航班已落地）` | Il a atterri dans le pays de destination. | « En transit » |
| `Customs held（清关中）` | Douane à l'importation. Le chinois dit « en cours de dédouanement » : ce n'est pas forcément un problème. | « À la douane » |
| `Clearance processing complete（清关完成）` | Dédouané. | « En transit » |
| `Handed over to the trucking company, waiting for transport（已交卡车公司 待运输）` | En attente du camion vers le transporteur qui livre. | « En transit » |
| `Delivered to warehouse, waiting to be scanned（已交仓，待扫描）` | Remis au dépôt du transporteur local, pas à toi. | « En transit » |
| `CONSIGNMENT RECEIVED BY OVERSEAS FORWARDER` | Le transporteur local a le colis. | « En transit » |
| `out for delivery` | En cours de livraison. | « En livraison » |
| `Delivered` | Livré chez toi. | « Livré » |

`The order information has been sent` et `货物电子信息已经收到` veulent dire la même chose que la première ligne, et `到达收货点(包裹已入库)` qu'un point de collecte JCEX a enregistré le colis. `under clearance` correspond à la douane à l'importation et `CUSTOMS CLEARANCE COMPLETE` à sa fin. `InTransit_Arrival` et `InTransit_Departure` marquent un hub à Hong Kong, et `turck transferring（卡车运输中）` (orthographe de JCEX) un colis transporté par camion. Après la remise suivent les propres lignes du transporteur qui livre : celles de Colissimo en français, ou celles d'USPS en anglais comme `Departed USPS Regional Facility`, `In Transit to Next Facility` et `ARRIVAL AT POST OFFICE` (toutes « En transit »). La ligne de Colissimo `Votre colis est sur son site de distribution. Nous le préparons pour le mettre en livraison.` (au centre de distribution local, préparé pour la tournée) s'affiche comme « En livraison », et `Votre colis n'a pas pu vous être remis. Il sera mis en livraison demain (hors dimanche et jours fériés).` comme « Tentative de livraison ». `Package has been created and is ready for pickup at the shippers location. This is a pre-transit status event code.` n'est que l'étiquette du transporteur local, avant qu'il n'ait le colis : « Étiquette créée ».

Au-dessus des lignes, le suivi de JCEX classe chaque colis sous un libellé résumé, expliqué dans une note : `收到信息` (en attente de prise en charge), `运输中` (en transit), `到达待取` (en attente en point de retrait), `派送途中` (en livraison), `投递失败` (tentative de livraison échouée), `可能异常` (anomalie possible), `运输过久` (en transit depuis longtemps sans livraison) ou `查询不到` (aucun suivi trouvé).

Deux points prêtent souvent à confusion. JCEX classe la remise au dépôt du transporteur local comme une livraison, si bien que sa page peut afficher `已签收` (signé) alors que ce transporteur a encore le colis : lis les lignes les plus récentes. Et `Arrive at the delivery point` arrive au tout début, en Chine, pas près de chez toi.

## Qui livre les colis JCEX

JCEX ne livre pas lui-même dans ton pays. Ses pages indiquent que les petits colis (jusqu'à 2 kg, par avion) sont livrés par la poste, et les colis express plus lourds par la poste ou par un transporteur privé. Dans les historiques lus sur le suivi de JCEX, Colissimo, le service colis de [La Poste](carrier:la-poste), a livré en France, et USPS aux États-Unis. Pour la Belgique et la Suisse, JCEX ne cite aucun partenaire précis sur ces pages.

JCEX ne publie aucune règle sur les tentatives de livraison, les avis de passage, les points relais ou les délais de garde : tout dépend du transporteur qui livre, et ses lignes dans l'historique JCEX indiquent ce qu'il a fait. Dans un historique français, Colissimo a indiqué qu'il n'avait pas pu remettre le colis et le représenterait le lendemain (hors dimanche et jours fériés). Peek n'affiche pas le propre numéro de ce transporteur : demande-le au vendeur si tu dois replanifier une livraison.

## Colis JCEX qui n'arrive pas ou suivi bloqué

**Qui agit.** Les clients de JCEX sont les vendeurs qui expédient avec lui. JCEX ne publie aucune procédure de réclamation pour les destinataires : pour un colis en retard, perdu, abîmé ou renvoyé, contacte le vendeur ou la plateforme où tu as acheté.

**Pas encore de suivi.** Le suivi de JCEX affiche `查询不到` sans aucune ligne aussi bien pour un nouveau bordereau qu'il n'a pas encore scanné que pour un numéro inconnu ; Peek affiche alors « En attente de JCEX ». Si la réponse de JCEX mentionne le bordereau, ce message reste jusqu'au premier scan, sans te demander de vérifier le numéro. Si JCEX ne connaît toujours pas le numéro deux jours plus tard et qu'aucun autre service n'a répondu, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » et vérifie dès lors chaque jour, puis chaque semaine une fois une semaine écoulée depuis l'ajout. Dans tous les cas, si rien ne bouge, vérifie le numéro auprès du vendeur. Si `Shipment order info received` reste la seule ligne le lendemain, Peek signale que le vendeur n'a pas encore remis le colis à JCEX : demande au vendeur quand il le fera.

**Plus de mise à jour.** JCEX ne précise pas au bout de combien de temps un colis passe en `运输过久`. Si rien n'avance, contacte le vendeur, qui a réservé l'envoi et peut interroger JCEX. Plus d'infos dans [suivi de colis qui ne bouge plus](guide:tracking-not-updating) et [suivi d'un colis venant de Chine](guide:tracking-from-china). Pour les blocages en douane ou les frais d'importation — qui, en France, en Belgique et dans le reste de l'UE, incluent depuis le 1er novembre 2026 des frais de traitement de 2 € par type de produit vendu à distance quelle que soit sa valeur (non applicables en Suisse) — lis [colis bloqué en douane](guide:customs).

**Livré, mais rien reçu.** Vérifie d'abord que la ligne de livraison provient bien du transporteur local et non de la remise au dépôt par JCEX. Si c'est bien lui, vois [colis livré mais non reçu](guide:delivered-not-received).

## JCEX contact : numéro et service client

Au 21 octobre 2026, JCEX ne donne qu'une seule série de contacts, tous en Chine :

- **Téléphone** : 400-887-1083, un numéro chinois, de 9 h à 18 h heure de Chine, du lundi au vendredi. Fermé le week-end et les jours fériés.
- **E-mail** : cs@jcex.com.cn, indiqué en pied de page comme adresse de réclamation.
- **WeChat** : un compte de service client, mentionné en pied de page avec les mêmes horaires.
- **Siège social** : Jiacheng International, 8th floor, Building T4, Tiehui Development Centre, 520 Jiuhe Road, Shangcheng District, Hangzhou, Zhejiang, Chine.

Sa page de contact liste aussi des agences à travers la Chine, destinées aux vendeurs, sans aucun contact pour les destinataires à l'étranger. Pour ta commande, adresse-toi au vendeur ; pour la livraison finale, au transporteur local.

## Suivre un colis JCEX avec Peek

[Peek](/) reconnaît JCEX immédiatement à un numéro `JCZ…DH`, qu'aucun autre transporteur de son catalogue ne partage. Tu peux aussi coller un lien track.jcex.com avec `?nums=` suivi du numéro. Peek ne suit que les bordereaux JCZ de JCEX : si tu n'as qu'un numéro de commande, demande le bordereau au vendeur.

Peek lit le suivi officiel de JCEX et affiche le statut, l'historique avec le lieu de chaque scan sur une carte, l'heure de livraison et le pays de destination. Il affiche le texte anglais d'une ligne quand JCEX en donne un, et les lignes du transporteur local telles qu'il les a rédigées, sauf les correspondances exactes comme `Delivered`, qui apparaissent dans les mots de Peek. `Arrive at the delivery point` et la remise au dépôt comptent comme en transit, pas comme prêts à être retirés ni livrés. Chaque heure de scan est lue sur le fuseau que lui donne JCEX (heure de Chine ou de ton pays) et affichée dans ton fuseau horaire. Les lignes datées après la consultation, ainsi que la chronologie que JCEX préremplit parfois jusqu'à la remise, ne sont pas des scans : Peek les exclut des scans et n'affiche qu'une fois une ligne répétée par JCEX. Une ligne préremplie par JCEX ne compte jamais comme un scan : elle ne change ni le statut ni la date prévue et n'envoie aucune alerte. L'historique s'ouvre alors, jusqu'à la livraison ou au retour du colis, sur « D’autres sites affichent peut-être plus d’étapes, mais ce ne sont que des prévisions. », et « Les voir » les liste en grisé, chacune avec « Prévu pour » et son jour.

Comme JCEX ne nomme pas le transporteur local et que Peek ne lit pas le second numéro, Peek suit le numéro JCEX jusqu'au bout sans basculer sur Colissimo ni USPS, dont les scans arrivent via l'historique de JCEX. Peek n'affiche ni date de livraison prévue, ni service, ni preuve de livraison pour JCEX.

Il vérifie jusqu'à toutes les 10 minutes, ou toutes les 2 minutes à partir de 6 h heure locale le jour où le colis passe en livraison, tant que le suivi de JCEX répond (via des services de suivi, toutes les 15 minutes). Il peut te prévenir pour « Chaque scan », les « Étapes importantes » ou la « Livraison seulement ». Après deux jours sans nouvelles (12 heures tant que le colis affiche « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Il ne peut contacter ni JCEX ni le vendeur à ta place.

## Questions sur le suivi JCEX

### JCEX est-il fiable ?

Oui. JCEX est la marque de Jiacheng International Logistics, fondée en 2000 à Hangzhou, en Chine, dans laquelle Huamao Logistics, cotée à Shanghai, détient une participation depuis 2021. La société se dit prestataire logistique désigné de plateformes comme Alibaba.com, Cainiao et DHgate.

### Que veut dire « Arrive at the delivery point » chez JCEX ?

Cela signifie que l'entrepôt de JCEX en Chine a reçu le colis. Cette étape apparaît au début du suivi, avant le vol, et n'a rien à voir avec un point de livraison près de chez toi.

### Pourquoi JCEX indique-t-il livré alors que je n'ai pas mon colis ?

JCEX classe sa remise au dépôt du transporteur local comme une livraison : la ligne `Delivered to warehouse, waiting to be scanned（已交仓，待扫描）` veut dire que le transporteur local vient de le recevoir. Attends ses propres lignes, jusqu'à `Delivered`.

### Qui livre les colis JCEX en France et aux États-Unis ?

Dans les historiques lus sur le suivi de JCEX, Colissimo (La Poste) a assuré la livraison en France et USPS aux États-Unis. Leurs propres lignes apparaissent directement dans l'historique de JCEX.

### Quel est le délai de livraison de JCEX ?

Sur sa ligne petits colis, JCEX annonce aux vendeurs 9 à 13 jours ouvrés vers l'Europe, 8 à 11 vers le Royaume-Uni et 13 à 16 vers les États-Unis. C'est le délai donné par la boutique pour ta commande qui fait foi.

:::sources
- [JCEX : 全球轨迹查询平台 (suivi)](https://track.jcex.com/) – suivi en chinois uniquement, jusqu'à 100 numéros, libellés résumés et légende, libellés des scans, transporteurs observés, second numéro vide, remise classée comme livrée
- [JCEX : 联系我们 (contact)](https://www.jcex.com/bangzhuzhongxin/lianxiwomen/index.html) – téléphone, e-mail, horaires, siège social, agences
- [JCEX : 佳成国际物流 (accueil)](https://www.jcex.com/) – e-mail de réclamation, service client WeChat, horaires, participation de Huamao
- [JCEX : 佳成介绍 (à propos)](https://www.jcex.com/guanyujiacheng/jiachengjieshao/index.html) – fondée en 2000, vendeurs e-commerce transfrontaliers, Huamao 2021
- [JCEX : site en anglais](https://enjc.jcex.com/) – Jiacheng International Logistics Co., Ltd, plateformes partenaires, e-mail de réclamation
- [JCEX : 国际小包 (petits colis)](https://www.jcex.com/chanpinyufuwu/guojixiaobaoyukuaidi/index.html) – jusqu'à 2 kg, par avion, livraison postale, délais
- [JCEX : 国际快递与快运 (express et fret)](https://www.jcex.com/chanpinyufuwu/guojikuaiyun/1438101270410715138.html) – livraison par la poste ou des transporteurs privés
- [Commission européenne : EU customs reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – frais de traitement de l'Union de 2 € depuis le 1er novembre 2026
- [Commission européenne : Guidance on the 3 EUR customs duty and Union handling fee (PDF, en anglais)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € par type de produit vendu à distance, quelle que soit sa valeur
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek affiche, format du numéro, lignes d'entrepôt et de remise, libellés des scans, lignes relayées de Colissimo et d'USPS, lignes préremplies
:::
