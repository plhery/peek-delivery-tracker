---
title: Suivi HuaHan Logistics : numéro HHW…YQ et livraison
description: Suivez un colis HuaHan Logistics (华翰物流) depuis la Chine : format du numéro HHW…YQ, sens des statuts, transporteur final, douane et suivi dans Peek.
slug: suivi-colis-huahan-logistics
published: 2026-11-18
updated: 2026-11-20
---

HuaHan Logistics (华翰物流, également appelé HuaHan Express ou 华翰速递) est un transporteur chinois spécialisé dans la logistique e-commerce transfrontalière qui expédie les colis hors de Chine par avion ou par voie postale avant de les confier à un transporteur local dans le pays de destination pour la livraison finale. Si une boutique en ligne a expédié votre commande avec HuaHan, repérez un numéro commençant par `HHW` et se terminant par `YQ`, puis collez-le dans le champ ci-dessus. Le suivi public de HuaHan sur [hh-exp.cn/WebTrack/CargoTrack.html](https://www.hh-exp.cn/WebTrack/CargoTrack.html) permet aussi de voir le nom du transporteur local chargé du dernier kilomètre ainsi que son propre numéro de suivi.

## Numéro de suivi HuaHan : à quoi il ressemble

Peek reconnaît les lettres de transport HuaHan sous la forme `HHW`, deux lettres, dix chiffres et `YQ`, soit 17 caractères au total. Exemples avec des chiffres fictifs :

| Numéro | Ce que c’est |
| --- | --- |
| `HHWCN4829105736YQ` | `HHW`, deux lettres, dix chiffres et `YQ` : une lettre de transport HuaHan. |
| `HHWCN482910573YQ` | Seulement neuf chiffres : ce format n’est pas celui de HuaHan. Vérifiez s’il manque un chiffre. |
| `WNBAA4829105736YQ` | Se termine aussi par `YQ`, mais commence par `WNB` : un numéro WanbExpress. |
| `JXCAA4829105736YQ` | Commence par `JXC` : un numéro [Juxi Express](carrier:juxi-express). |

De nombreux transitaires chinois terminent leurs numéros par `YQ`, chacun avec son propre préfixe : c’est `HHW` qui distingue HuaHan, et aucun autre transporteur du catalogue de Peek n’utilise ce préfixe. Les espaces, les tirets et les minuscules n’ont aucune importance lorsque vous collez le numéro dans Peek.

**Où le trouver.** Le vendeur l’indique dans l’e-mail d’expédition ou sur la page de votre commande. Si vous ne le trouvez pas, consultez [comment trouver votre numéro de suivi](guide:find-tracking-number). Sur le site public de HuaHan ([hh-exp.cn/WebTrack/CargoTrack.html](https://www.hh-exp.cn/WebTrack/CargoTrack.html), intitulé `运单追踪`), l’interface est en chinois et accepte jusqu’à 20 numéros séparés par une virgule (`多个单号请用英文逗号隔开（最多支持 20 个单号同时查询）`), après une éventuelle vérification par addition simple ; la racine [hh-exp.cn](https://www.hh-exp.cn/) renvoie au portail de connexion réservé aux expéditeurs (`华翰物流订单管理系统`), avec un lien vers la page publique de suivi.

**Un second numéro.** Le suivi de HuaHan peut afficher le numéro attribué au colis par le transporteur local. HuaHan y ajoute parfois un suffixe après un tiret bas (`_`) qui ne fait pas partie du numéro du transporteur final : retirez le `_` et ce qui suit si vous cherchez le colis directement sur le site de ce transporteur.

## Statuts de suivi HuaHan expliqués

Le suivi de HuaHan affiche chaque événement avec la date et l’heure, le libellé et le lieu, du plus récent au plus ancien. Ses propres jalons sont en anglais, parfois précédés du chinois ou uniquement en chinois sur certaines étapes d’entrepôt et d’aéroport. Voici les principaux jalons dans l’ordre habituel du trajet :

| Ce qu’affiche HuaHan | Ce que cela signifie pour vous | Statut dans Peek |
| --- | --- | --- |
| `快件电子信息已经收到 Shipment information received`, `Shipment information received` | Le vendeur a transmis les données du colis à HuaHan, qui n’a pas encore reçu physiquement l’envoi (Peek traduit `Shipment information received` seul en « Informations d’expédition reçues »). | « Étiquette créée » |
| `快件到达作业中心 Shipment arrived at facility`, `Arrived at origin facility`, `Has entered the warehouse` | Le centre de tri de HuaHan en Chine (à Shenzhen dans les historiques connus du code de Peek) a pris le colis en charge. | « Pris en charge » |
| `快件操作完成 Shipment departed from facility`, `Departed from origin facility`, `货物离开操作中心`, `Shipment received`, `Shipments were sent` | Le colis est traité, quitte le centre d’origine ou transite par l’entrepôt de transfert de HuaHan avant l’exportation. | « En transit » |
| `Transfer to Origin airport`, `快件从转运仓发出至机场，出口报关中（…）` | En route vers l’aéroport de départ, avec la déclaration d’exportation en cours. | « En transit » |
| `Export customs clearance completed`, `快件抵达机场，预计…号航班飞`, `航班延误，重新配载至…号航班飞` | Dédouanement à l’exportation terminé (il ne s’agit pas de la douane de votre pays) et programmation ou report du vol. | « En transit » |
| `Departed from airport of Origin` | L’avion a décollé. | « En transit » |
| `Arrived at destination airport`, `拆板中` | L’avion a atterri dans le pays d’arrivée ou de transit ; la palette aérienne est en cours de dégroupage (`拆板中`). | « En transit » |
| `Import Customs clearance started` | La douane d’importation examine l’envoi. | « À la douane » |
| `Import Customs clearance completed`, `目的国清关完成` | La douane d’importation a libéré le colis. | « En transit » |
| `Has been given to local carrier`, `转单：…` | HuaHan a remis le colis au transporteur local (ou indique le numéro de transfert après `转单：`). Les scans de ce transporteur prennent le relais. | « En transit » |

**Les scans du transporteur local.** Après la remise, le suivi de HuaHan relaie les scans du transporteur final. Peek les classe d’après le code d’étape de HuaHan ou leur formulation :

| Après la remise au transporteur local | Ce que cela signifie pour vous | Statut dans Peek |
| --- | --- | --- |
| `Ready to Ship, Parcel received and processed` | Le transporteur local a réceptionné et trié le colis. | « En transit » |
| `The parcel is in the central warehouse waiting to be processed.`, `In Transit, Shipment arrived to sorting centre / Shipment in the sorting centre`, `In Transit, Shipment remained at sorting hub` | Au centre logistique ou au hub de tri du transporteur local. | « En transit » |
| `In Transit, Arrived to HU HUB`, `In Transit, Parcel has entered the Linehaul network.` | Passage par un hub européen (ici en Hongrie), puis acheminement inter-agences. | « En transit » |
| `In Transit, Picked Up` (suivi de l’allemand `Einrollung` de DPD), `In Transit, Scanned By DPD At parcel delivery centre.`, `In Transit, At parcel delivery centre.` | Arrivée à l’agence de livraison DPD (ce n’est pas un enlèvement chez vous), puis scan quotidien tant que le colis y attend. | « En transit » |
| `Out for Delivery, Parcel is at courier, delivery expected soon.` | En tournée de livraison avec le chauffeur. | « En livraison » |
| `The courier will deliver the parcel soon. Additional details are available in the SAMEDAY app, email or SMS.` | En tournée avec Sameday (transporteur actif en Roumanie et en Bulgarie). | « En livraison » |
| `Delivery Attempted`, `Delivery Attempted, Unfortunately we have not been able to deliver your parcel. Pickup parcelshop delivery after first delivery attempt.` | Tentative de livraison infructueuse chez DPD ; avec la phrase longue, le colis est redirigé vers un point relais Pickup. | « Tentative de livraison » |
| `Awaiting Pickup, Parcel was deposited in Pickup parcel station` | Le colis attend votre retrait dans un relais ou une consigne Pickup de DPD. | « Prêt à être retiré » |
| `The parcel has been successfully delivered.`, `Delivered` | Colis livré (Peek traduit `Delivered` seul en « Livré »). | « Livré » |

HuaHan range les scans de mise en tournée, de point relais et de livraison sous ses propres codes (`CB`, `PB`, `CC`), mais Peek vérifie d’abord le texte : HuaHan utilise par exemple aussi son code de tournée `CB` pour le départ d’un centre de tri [USPS](carrier:usps) (`Departed USPS Facility`), que Peek maintient en « En transit ».

**Deux lignes trompeuses.** `We will pick up your parcel from the sender soon.` apparaît parfois tout au début, avant même que HuaHan ait reçu le colis en Chine : c’est l’annonce enregistrée chez le transporteur final, pas un passage chez vous. Peek affiche « Étiquette créée ». De même, `The parcel was redirected. As soon as it is in the couriers possession, it will be processed as requested.` note une consigne de redirection demandée alors que le colis est encore en Chine : cet enregistrement ne fait pas avancer le colis et Peek ne lui attribue aucun statut.

**Données électroniques, pas mouvements.** `The instruction data for this shipment have been provided by the sender to DHL electronically.` (avec ou sans point final) est l’enregistrement informatique de [DHL](carrier:dhl) pour le dernier kilomètre ; `Pre-Shipment Info Sent to USPS` est l’annonce électronique d’USPS ; `Pendiente de recepción en CTT Express` est l’avis d’attente de [CTT Express](carrier:ctt-express), transmis avant même que HuaHan ait le colis en Chine ; `货物电子信息已经收到` signale la réception des données de la commande en chinois. Aucun de ces messages n’est un scan physique : Peek affiche « Étiquette créée » ou conserve le statut déjà atteint.

**Un même scan en double.** Le suivi de HuaHan affiche parfois deux lignes à la même minute pour un événement du transporteur final : l’une avec le lieu et les mots du transporteur, l’autre sans lieu avec une formule générique de HuaHan telle que `Delivered, Parcel was delivered successfully`. Peek ne garde que la ligne comportant le lieu.

**Par la voie postale.** HuaHan confie certains envois à [China Post](carrier:china-post). Son suivi relaie alors en chinois les scans de China Post (avec le bureau entre `【】`), puis ceux de la poste de destination tels que China Post les copie :

| Scan relayé par HuaHan | Ce que cela signifie pour vous | Statut dans Peek |
| --- | --- | --- |
| `中国邮政已收取邮件` | China Post a pris le colis en charge auprès de HuaHan. | « En transit » |
| `邮件已在【…】完成分拣，准备发出`, `邮件到达【…】`, `邮件离开【…】，正在发往【…】`, `邮件离开【…】，正在发往下一站` | Tri, arrivée ou départ dans un bureau China Post indiqué entre `【】`. | « En transit » |
| `送交出口海关` | Remis à la douane d’exportation en Chine (pas celle de votre pays). | « À la douane » |
| `出口海关/放行`, `已交承运商运输` | Libéré par la douane chinoise, puis remis à la compagnie aérienne. | « En transit » |
| `到达寄达地`, `到达寄达地处理中心`, `到达境外经转局` | Arrivé dans le pays de destination, à son centre postal ou dans un bureau de transit. | « En transit » |
| `境外进口海关留存待验(等待提交给边境机构/安全部门)`, `境外进口互换局留存`, `送交境外进口海关` | Retenu au bureau d’échange ou présenté à la douane d’importation du pays de destination. | « À la douane » |
| `寄达地海关允许进口（无需缴税）` | Autorisé à l’importation par la douane locale sans droits ni taxes à payer. | « En transit » |
| `离开【…】处理中心`, `已到达【…】投递局` | A quitté le centre de tri local puis est arrivé au bureau distributeur. | « En transit » |

**Via Landmark Global.** Sur d’autres envois, HuaHan relaie en anglais les étapes de [Landmark Global](carrier:landmark-global). Les deux premières arrivent avant la prise en charge en Chine ; les suivantes interviennent après la remise au réseau de [bpost](carrier:bpost) :

| Scan Landmark Global relayé | Ce que cela signifie pour vous | Statut dans Peek |
| --- | --- | --- |
| `Shipment Data Uploaded` | Données de l’expéditeur enregistrées par Landmark Global. | « Étiquette créée » |
| `Processed` | Traitement informatique dans la même minute, lu comme transit par Peek. | « En transit » |
| `Incoming scan at facility` | Arrivée dans un dépôt après remise au réseau de bpost. | « En transit » |
| `Outgoing scan at facility` | Départ de ce dépôt (classé sous le code de tournée par HuaHan, mais affiché « En transit » par Peek car la tournée peut avoir lieu plusieurs jours plus tard). | « En transit » |
| `Out for delivery (On the way)` | En tournée de livraison. | « En livraison » |
| `Delivery attempt (Delivery not possible)` | Tentative de livraison non aboutie. | « Tentative de livraison » |

## Qui livre les colis HuaHan et en combien de temps

Sur son site officiel ([hh-exp.com](https://www.hh-exp.com/) et [hh-exp.com/gyhh](https://www.hh-exp.com/gyhh)), HuaHan (`深圳市华翰鸿运国际物流技术有限公司`, fondé en 2008 et basé à Shenzhen) indique couvrir plus de 150 pays et régions en Europe, en Amérique du Nord, en Amérique du Sud et en Australie, notamment pour les petits colis e-commerce de marchandises spéciales (`特货小包专线` : cosmétiques, batteries, cigarettes électroniques) et les lignes postales (`特货邮路`). Sa page d’accueil annonce un délai global indicatif de 6 à 12 jours ouvrés (`整体时效6-12个工作日`), mais ne publie pas de grille par pays ni la liste complète de ses partenaires locaux : fiez-vous à la fourchette annoncée par votre boutique lors de la commande.

Après `Has been given to local carrier`, c’est un transporteur du pays de destination qui assure la livraison. Dans les parcours observés par le code de Peek, HuaHan remet selon les lignes ses colis à [DHL](carrier:dhl), DPD, [CTT Express](carrier:ctt-express), Sameday, [USPS](carrier:usps), [China Post](carrier:china-post) (puis la poste nationale du pays d’arrivée) ou [Landmark Global](carrier:landmark-global) avec livraison par le réseau de [bpost](carrier:bpost). Peek s’appuie toujours sur le transporteur et le numéro effectivement indiqués sur le dossier de votre colis.

**Tentatives de livraison et retrait.** Le nombre de passages, l’avis de passage et le délai de garde en point relais ou en bureau de poste suivent les règles du transporteur local : contactez ce dernier avec son propre numéro de suivi pour reprogrammer la livraison. Voir [livraison manquée](guide:missed-delivery) et [colis non réclamé](guide:uncollected-parcel).

## Douane, frais d’importation et faux SMS

HuaHan s’adresse aux vendeurs e-commerce et ses pages publiques ne précisent pas si votre envoi voyage tous droits payés ou non : vérifiez sur votre facture si la TVA et les frais de douane étaient inclus au paiement. Voir aussi [colis bloqué en douane](guide:customs) et [reconnaître les faux SMS de livraison](guide:delivery-scam-texts).

- **Union européenne.** Depuis le 1er juillet 2026, les biens achetés à distance hors du territoire douanier de l’UE dans des envois d’une valeur intrinsèque maximale de 150 € supportent un droit de douane de 3 € par catégorie d’articles ; depuis le 1er novembre 2026 s’ajoute des frais de traitement de l’Union de 2 € par catégorie d’articles, quelle que soit la valeur de l’envoi (une catégorie regroupe les marchandises partageant le même classement tarifaire, la même désignation et, lorsqu’elle est requise, la même origine, quel qu’en soit le nombre d’unités).
- **France et Belgique.** En **France** (où la Guadeloupe, la Guyane, la Martinique, La Réunion et Mayotte font partie du territoire douanier de l’UE mais restent hors du territoire TVA de l’UE), la plateforme officielle Cybermalveillance.gouv.fr met en garde contre les SMS et courriels d’hameçonnage réclamant de faux frais de douane ou de livraison via un lien et conseille, au moindre doute, de vérifier le véritable suivi du colis ou de contacter directement l’entreprise de livraison. En **Belgique**, lorsque [bpost](carrier:bpost) livre le colis, dès que les frais d’importation sont connus, bpost envoie une demande de paiement par e-mail, SMS ou courrier et invite à payer exclusivement via ses canaux officiels (l’application My bpost, Track & Trace ou un Bureau de Poste), sans jamais cliquer sur un lien suspect.
- **Suisse.** En **Suisse**, l’Office fédéral de la douane et de la sécurité des frontières (OFDF) ne perçoit ni droits de douane ni TVA à l’importation lorsque le montant respectif de la redevance est inférieur à 5 francs suisses (ce qui correspond à une base d’imposition de 62 CHF au taux de TVA de 8,1 %, ou de 193 CHF au taux réduit de 2,6 %), hors frais de dédouanement facturés séparément par la poste ou le transporteur pour sa prestation.

## Colis HuaHan en retard, bloqué ou introuvable

**Numéro introuvable (`没有找到记录`).** Tant que HuaHan ne connaît pas le numéro, son suivi répond `没有找到记录` (« aucun enregistrement trouvé ») ; Peek affiche « En attente de HuaHan Logistics » et continue de vérifier. Si HuaHan ne reconnaît toujours pas le numéro après deux jours et qu’aucune autre source n’a répondu, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » et espace ensuite les vérifications à une fois par jour, puis une fois par semaine après sept jours. Vérifiez chaque caractère (`HHW`, deux lettres, dix chiffres, `YQ`) auprès du vendeur.

**Bloqué sur `Shipment information received`.** Le vendeur a créé l’expédition, mais HuaHan n’a pas encore scanné le colis en entrepôt. Demandez au vendeur quand il a remis la marchandise.

**Bloqué sur `Import Customs clearance started`.** La douane de votre pays contrôle l’envoi. Si le blocage dure plusieurs jours sans demande du transporteur local, prévenez le vendeur.

**Plus rien après `Has been given to local carrier`.** Le transporteur local a pris le relais et son propre suivi est souvent plus détaillé. Relevez son nom et son numéro sur le suivi HuaHan ou sur la fiche du colis dans Peek.

**Aucune mise à jour depuis plusieurs jours.** Lorsqu’un colis en cours d’acheminement n’a reçu aucun nouveau scan depuis quatre jours, Peek le signale (« Aucune mise à jour depuis quatre jours ») et rappelle qu’un colis reste souvent silencieux sur un vol long-courrier, en douane ou le week-end. Voir [pourquoi le suivi ne bouge plus](guide:tracking-not-updating) et [suivre un colis depuis la Chine](guide:tracking-from-china).

**Marqué livré, mais rien reçu.** Vérifiez votre boîte aux lettres, vos voisins et les points relais proches, puis contactez le transporteur local et le vendeur. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received).

**Perdu ou retourné.** C’est le vendeur qui a réservé l’envoi auprès de HuaHan : adressez-vous au vendeur ou à la place de marché pour obtenir un remboursement ou un nouvel envoi. Voir [colis perdu](guide:lost-parcel) et [colis retourné à l’expéditeur](guide:returned-to-sender).

## Numéro de téléphone et service client HuaHan

Sur son site officiel ([hh-exp.com/lxwm](https://www.hh-exp.com/lxwm)), HuaHan publie les coordonnées suivantes en **Chine** :

- **Ligne téléphonique officielle (`官方热线`) :** `400-015-6080` (numéro chinois en 400, sans horaires d’ouverture publiés ; la ligne `13794450408` est dédiée aux demandes commerciales des vendeurs `业务咨询`).
- **Siège social :** 10e étage, bâtiment C, Bairuida Building, 4001 Xuegang Avenue, Bantian, district de Longgang, Shenzhen (`深圳市龙岗区坂田雪岗大道4001号百瑞达大厦C座10楼`), avec des agences à Yiwu, Zhengzhou et Hangzhou.

HuaHan ne publie pas d’adresse e-mail d’assistance pour les destinataires particuliers. Pour toute question sur votre commande, un retard ou un remboursement, contactez le vendeur ou la plateforme d’achat ; pour la livraison finale, contactez le transporteur local.

## Suivre un colis HuaHan avec Peek

[Peek](/) identifie immédiatement HuaHan Logistics à partir d’un numéro `HHW…YQ` (« Transporteur détecté ») et interroge le suivi de HuaHan avec ce numéro complet uniquement, sans compte, code postal ni numéro de téléphone. Si le site de HuaHan est injoignable, Peek interroge les services de suivi.

Peek affiche le statut, chaque scan avec son lieu et le pays de destination enregistré chez HuaHan. Le suivi de HuaHan imprime les heures sans fuseau horaire : Peek conserve l’heure locale affichée pour les propres étapes de HuaHan avant la remise, et lit les scans postérieurs à `Has been given to local carrier` sur le fuseau du pays de destination lorsque celui-ci n’en compte qu’un, en convertissant les heures datées dans le fuseau de votre appareil (« Heures dans ton fuseau horaire : … » sur peektracker.com et dans les apps iPhone et Android). HuaHan ne fournit ni date de livraison prévue ni poids, si bien que Peek n’en affiche pas.

Lorsque HuaHan indique un transporteur local connu de Peek avec un numéro correspondant à son format, la fiche du colis l’affiche (« Livraison par USPS », ou « Annoncé à USPS, pas encore reçu » tant que ce transporteur n’a que l’annonce électronique) et interroge aussi ce transporteur. Si HuaHan fournit un numéro postal international (deux lettres, neuf chiffres, deux lettres) sans nommer de transporteur connu, Peek interroge la poste du pays de destination. Dès que ce transporteur présente un scan au moins aussi récent que le dernier de HuaHan, Peek suit la livraison chez lui tout en conservant l’historique HuaHan.

Tant que vous gardez le colis ouvert, ou le jour de sa livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes — et, le jour de la mise en tournée à partir de 6 h locales, jusqu’à toutes les 2 minutes lorsque le suivi HuaHan répond directement (toutes les 5 minutes lorsque la vérification ouvre un navigateur) ; via les seuls services de suivi, Peek vérifie une fois par heure, toutes les 3 heures après un jour sans scan, et jusqu’à toutes les 15 minutes le jour de livraison tant que le colis est ouvert ou que les notifications sont activées. Le reste du temps, ainsi qu’après deux jours sans nouvelle (12 heures sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures, et suit la cadence du transporteur local dès que celui-ci prend le relais. Vous pouvez choisir les alertes « Chaque scan », « Étapes importantes » (qui couvrent `Import Customs clearance started`, les scans douaniers postaux comme `送交出口海关` et `送交境外进口海关`, la tournée, le point relais et la livraison, mais pas le dédouanement export de routine `快件从转运仓发出至机场，出口报关中`) ou « Livraison seulement ». Peek ne peut contacter ni HuaHan ni le vendeur à votre place.

## Questions fréquentes sur le suivi HuaHan

### Qu’est-ce que HuaHan Logistics (华翰物流) ?

C’est un logisticien chinois du e-commerce transfrontalier (`深圳市华翰鸿运国际物流技术有限公司`), fondé en 2008 à Shenzhen et aussi connu sous le nom de HuaHan Express (`华翰速递`). Il achemine les colis depuis la Chine vers plus de 150 pays et les remet à un transporteur local pour la livraison finale.

### Où suivre un colis HuaHan ?

Dans le champ en haut de cette page, ou sur le suivi public de HuaHan à l’adresse `hh-exp.cn/WebTrack/CargoTrack.html` (jusqu’à 20 numéros séparés par des virgules, après une éventuelle vérification par addition simple). Utilisez toujours le numéro `HHW…YQ` complet à 17 caractères.

### Que signifie « Has been given to local carrier » sur un suivi HuaHan ?

Le dédouanement à l’importation est terminé et HuaHan a confié le colis au transporteur qui assure la livraison dans votre pays. Les lignes suivantes de l’historique relaient les scans de ce transporteur local, dont le nom et le numéro peuvent figurer sur le dossier.

### Qui livre les colis HuaHan dans mon pays ?

Un transporteur local variable selon la ligne choisie par l’expéditeur : le code de Peek rencontre notamment DHL, DPD, CTT Express, Sameday, USPS, China Post (avec remise à la poste nationale) ainsi que Landmark Global suivi du réseau bpost. Consultez la fiche du colis dans Peek ou sur le suivi HuaHan pour voir celui attribué à votre envoi.

### Qui contacter si mon colis HuaHan n’arrive pas ?

Le vendeur chez qui vous avez passé commande (ou la place de marché), car c’est lui qui est le client de HuaHan. Une fois le colis remis au transporteur final dans votre pays, contactez ce transporteur local avec son propre numéro de suivi pour les questions de livraison.

:::sources
- [HuaHan Express : 运单追踪 (suivi public des colis, en chinois)](https://www.hh-exp.cn/WebTrack/CargoTrack.html) – suivi public jusqu’à 20 numéros séparés par une virgule, colonnes de statut et détail des scans
- [HuaHan Logistics : 华翰物流订单管理系统 (portail expéditeurs, en chinois)](https://www.hh-exp.cn/) – portail de connexion des vendeurs et lien vers la page publique de suivi
- [HuaHan Express : Accueil officiel (en chinois)](https://www.hh-exp.com/) – fondation en 2008, lignes e-commerce et marchandises spéciales vers plus de 150 pays et régions, délai global indicatif de 6 à 12 jours ouvrés, hotline 400-015-6080
- [HuaHan Express : 关于华翰 (présentation de l’entreprise, en chinois)](https://www.hh-exp.com/gyhh) – historique depuis 2008, nom complet de la société, lignes spéciales et postales, agences
- [HuaHan Express : 联系我们 (contact, en chinois)](https://www.hh-exp.com/lxwm) – siège de Shenzhen, succursales de Yiwu, Zhengzhou et Hangzhou, hotline officielle 400-015-6080 et numéro commercial
- [Commission européenne : EU Customs Reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € par catégorie d’articles jusqu’à 150 € depuis le 1er juillet 2026 et frais de traitement de l’Union de 2 € depuis le 1er novembre 2026
- [Commission européenne : Guidance on the EUR 3 customs duty and the Union handling fee (PDF, en anglais)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – frais de 2 € quelle que soit la valeur et définition d’une catégorie d’articles
- [Commission européenne : Territorial Scope (en anglais)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Guadeloupe, Guyane, Martinique, La Réunion et Mayotte dans le territoire douanier de l’UE mais hors du territoire TVA de l’UE
- [Cybermalveillance.gouv.fr : Escroqueries à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – au moindre doute, vérifier le véritable suivi du colis ou contacter l’entreprise de livraison face aux faux SMS et courriels en France
- [bpost : Acheter hors de l’UE](https://www.bpost.be/fr/shopping-hors-ue) – demande de paiement par e-mail, SMS ou courrier et règlement exclusivement via l’app My bpost, Track & Trace ou en Bureau de Poste en Belgique
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – franchise de perception en dessous de 5 CHF de droits ou de TVA en Suisse et frais de dédouanement séparés du transporteur
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format `HHW…YQ`, vérification par addition, codes et libellés des scans HuaHan, China Post, Landmark Global/bpost, DPD, Sameday, DHL, CTT Express et USPS, gestion des horloges et remise au transporteur local
:::
