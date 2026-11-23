---
title: Suivi TSJY (通世集运) : numéros de commande et statuts
description: Suivre un colis groupé TSJY (通世集运) depuis la Chine : format d’un numéro TSJY…CN, signification des statuts en chinois et passage à 17FEIA.
slug: suivi-tsjy
published: 2026-11-23
updated: 2026-11-23
---

TSJY (通世集运) est un service de groupage en Chine exploité par 苏州通世供应链有限公司, une entreprise logistique basée à Suzhou. Tu achètes sur des sites chinois comme Taobao, Tmall, Pinduoduo, JD ou Douyin, et les boutiques expédient tes articles vers l’entrepôt de TSJY à Suzhou. TSJY les stocke, les regroupe dans un même colis et les expédie à l’étranger. Suis ton numéro TSJY sur le [site de TSJY](https://www.tsjy56.com/), dans la rubrique 订单查询 (recherche de commande), ou colle-le dans le champ ci-dessus.

## Numéro de suivi TSJY : à quoi il ressemble

Le site de TSJY ne décrit pas le format de ses numéros, mais ils suivent une structure fixe. TSJY correspond aux initiales de 通世集运, Tongshi Jiyun. Tous les exemples ci-dessous sont inventés :

| Numéro | Ce que c’est |
| --- | --- |
| `TSJY482917365CN` | TSJY, neuf chiffres et CN : un numéro de commande TSJY. |
| `TSJY48291736CN` | Seulement huit chiffres : ce n’est pas le format TSJY, et Peek ne reconnaît pas TSJY. Vérifie s’il manque un chiffre. |
| `FEIKM4829173650YQ` | FEI, deux lettres, dix chiffres et YQ : un numéro [17FEIA](carrier:17feia), de la ligne qui transporte certains envois TSJY à l’étranger. |

Dans Peek, les minuscules, les espaces et les tirets n’ont pas d’importance.

**Où le trouver.** C’est le numéro de ton ordre d’expédition (订单号), que tu soumets sur la page de commande web de TSJY (网页下单) ou sur WeChat (微信下单) une fois tes achats arrivés à l’entrepôt. Si quelqu’un d’autre a réservé l’envoi, demande-lui.

**Deux numéros.** Le champ de recherche de TSJY accepte aussi le numéro de la ligne (运单号). Sur téléphone, la carte de chaque commande affiche le numéro TSJY puis, après une barre oblique, celui de la ligne. Appuie sur la carte pour voir les lignes de suivi (轨迹详情).

## Statuts de suivi TSJY expliqués

Le suivi de TSJY est en chinois. Sur téléphone, chaque carte de résultat donne un statut court pour l’ensemble de la commande :

| La carte affiche | Ce que ça signifie pour toi |
| --- | --- |
| `等待处理` | En attente de traitement : TSJY a bien reçu ton ordre d’expédition. |
| `开始拣货`, `正在拣货`, `拣货完成`, `开始打包`, `已称重`, `已录单` | L’entrepôt prélève tes articles, emballe et pèse le colis, puis enregistre l’ordre. |
| `等待出库`, `已出库`, `运输途中` | En attente de sortie d’entrepôt ; sorti de l’entrepôt ; en transit. |
| `已送达`, `已完成` | Livré ; terminé. |
| `运输异常` | Incident en cours de transport. Contacte TSJY. |

Peek ne lit pas ce statut de carte. Il lit les lignes de suivi (轨迹) qui se trouvent derrière. Viennent d’abord les propres étapes de TSJY dans son entrepôt, en chinois :

| TSJY affiche | Ce que ça signifie pour toi | Peek affiche |
| --- | --- | --- |
| `订单已提交，等待系统确认` | Commande soumise, en attente de confirmation par le système. Rien n’est encore prélevé. | « Étiquette créée » |
| `仓库开始拣货` | L’entrepôt commence le prélèvement de tes articles. | « Pris en charge » |
| `仓库拣货完成` | Le prélèvement est terminé. | « Pris en charge » |
| `订单打包完成，等待出库` | Emballé, en attente de quitter l’entrepôt. Peut apparaître deux fois en une minute. | « Pris en charge » |
| `订单已出库，开始国际运输` | Le colis a quitté l’entrepôt de TSJY et le trajet international commence. | « En transit » |

Sur la ligne 17FEIA, TSJY recopie ensuite les lignes de 17FEIA, en anglais :

| La ligne affiche | Ce que ça signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Item information received.` | 17FEIA a reçu les données de la commande. TSJY a peut-être encore le colis. | « Pris en charge » |
| `The package is on the way to the facility.`, `Warehouse receipt.` | En route vers l’entrepôt de 17FEIA en Chine, puis réceptionné là-bas. | « En transit » |
| `The order has information pending customer confirmation.`, `Order Confirmed.` | L’entrepôt de 17FEIA retient le colis jusqu’à confirmation de la commande. | « En transit » |
| `Order dispatched from warehouse.` | A quitté l’entrepôt de 17FEIA. | « En transit » |
| `Export declaration in progress.` | Déclaration d’exportation depuis la Chine en cours, étape habituelle avant le vol. La douane ne bloque pas le colis, et ce n’est pas la douane de ton pays. | « En transit » |
| `Flight has departed.` | Avion parti. | « En transit » |
| `Flight delayed.` | Le vol est retardé. Le colis n’a pas bougé. | le statut de la ligne précédente |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | Escale en cours de route. | « En transit » |
| `Flight Arrived at Clearance Port.` | Atterri au point où la douane d’importation va le dédouaner. | « En transit » |
| `Broker picked up Shipment and delivered to warehouse.` | Un commissionnaire en douane l’a amené à son entrepôt pour le dédouanement. Ce n’est pas une livraison chez toi. | « À la douane » |
| `Customs Clearance is in progress.` | La douane d’importation vérifie le colis. | « À la douane » |
| `Customs Clearance Completed.` | La douane l’a libéré. | « En transit » |

Sur une autre ligne, la commande sur cette ligne peut aussi apparaître : `已下单` (commande passée), en chinois et sans lieu, entre l’emballage par TSJY et sa sortie. Comme `Item information received.` chez 17FEIA, cette ligne arrive pendant que TSJY a encore le colis : « Pris en charge ». Après la sortie de TSJY, `已出库` (sorti d’entrepôt), également en chinois et sans lieu, signifie que l’entrepôt de cette ligne a réexpédié le colis le lendemain de sa réception : « En transit ».

Peek écarte la ligne de 17FEIA indiquant une date de départ estimée, qui n’est pas un scan, ainsi que toute ligne datée dans le futur. Peek interprète d’autres lignes, comme celles du transporteur final, d’après leurs mots, par exemple livraison ou douane.

**Heures.** La page de détail de TSJY affiche chaque horaire à l’heure de ton appareil, et Peek affiche les étapes propres de TSJY de la même manière. Peek lit les lignes d’entrepôt de 17FEIA à l’heure de Pékin (UTC+8), comme les écrit 17FEIA. Quand le lieu d’une ligne ultérieure finit par un code pays, tel que `(DE)`, Peek lit son heure sur le fuseau de ce pays lorsqu’il n’a qu’un fuseau horaire.

## Qui livre les colis TSJY

Le site de TSJY indique expédier vers plus de 220 pays et régions, par fret aérien express (加急空运) ou maritime économique (经济海运). Il ne précise pas qui livre dans chaque pays et ne donne pas de délai de livraison. Sa page de détail nomme la ligne (渠道, canal) empruntée par chaque commande.

Peek connaît une ligne par son nom : [17FEIA](carrier:17feia) (一起飞), une société logistique chinoise ayant son propre suivi sur www.17feia.com. Le suivi de 17FEIA peut indiquer la société qui livre dans ton pays avec son numéro.

TSJY ne publie aucune règle pour les tentatives de livraison ou les points de retrait : tout dépend du transporteur final. Voir [livraisons manquées](guide:missed-delivery).

**Douane dans l’UE.** Les biens achetés en ligne et introduits dans l’UE depuis l’extérieur supportent un droit de douane temporaire de 3 € par article sur les colis valant jusqu’à 150 € depuis le 1er juillet 2026, et une taxe européenne de traitement de 2 € par article, quelle que soit la valeur, depuis le 1er novembre 2026. Un article désigne les marchandises partageant le même classement tarifaire, la même description et, si requis, la même origine. Les deux s’appliquent aux ventes à distance : des biens expédiés par le vendeur ou pour son compte. Les orientations de la Commission indiquent que les biens ne sont pas expédiés par le vendeur lorsque l’acheteur organise lui-même le transport sans intervention du vendeur, ce qui peut être le cas quand tu réserves TSJY toi-même : la douane de ton pays décide. Le site de TSJY ne dit pas comment les droits ou taxes sont payés. Voir [colis retenus en douane](guide:customs).

## Colis TSJY bloqué, perdu ou retourné

Avec TSJY, tu es généralement le client : c’est toi qui as réservé l’envoi. Pour un colis en retard, perdu ou endommagé, contacte donc d’abord TSJY.

**Articles pas encore arrivés à l’entrepôt.** Avant d’atteindre TSJY, tes achats voyagent avec les transporteurs chinois des boutiques : suis-les là où tu les as achetés. Le site de TSJY indique que son suivi commence à l’arrivée dans son entrepôt et court jusqu’à la signature à la livraison.

**Rien sous ce numéro.** Pour un numéro inconnu, la page de résultats de TSJY sur téléphone affiche `未找到相关的轨迹信息。` (aucune information de suivi trouvée). Vérifie s’il y a une faute de frappe. Le colis affiche « En attente de TSJY » et Peek continue de vérifier. Si TSJY ne connaît toujours pas le numéro deux jours plus tard et qu’aucun autre suivi n’a répondu, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » et vérifie ensuite chaque jour, puis une fois par semaine après sept jours.

**Bloqué à l’entrepôt.** Si les étapes propres de TSJY n’avancent plus pendant plusieurs jours, demande à son service client.

**Silencieux après le vol.** Après `Flight has departed.`, il peut n’y avoir aucune nouvelle avant l’atterrissage ou la douane. Si un colis encore à « Étiquette créée », « Pris en charge » ou « En transit » reste quatre jours sans nouvelle ligne, Peek le signale par « Aucune mise à jour depuis quatre jours » (ou « Aucune mise à jour depuis … »), explique qu’« Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger TSJY. » et propose « Demande de l’aide à TSJY ». Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating) et [suivre un colis venant de Chine](guide:tracking-from-china).

**Perdu ou endommagé.** Le site de TSJY promet une indemnisation intégrale en cas de perte ou de dommage (丢破必赔), mais ses pages publiques ne donnent ni règles ni délais de réclamation : contacte aussitôt son service client et conserve le carton et le contenu. Voir [colis perdus](guide:lost-parcel) et [colis endommagés](guide:damaged-parcel).

**Livré mais absent, ou retourné.** Vérifie autour de chez toi et demande aux voisins, puis préviens TSJY. Son site ne dit rien au sujet des retours. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contact TSJY

D’après la [page d’accueil de TSJY](https://www.tsjy56.com/), dans sa rubrique 客服中心 (centre de service client) :

- **Téléphone et e-mail :** un numéro de téléphone de service client (客户服务电话) et une adresse e-mail (客服邮箱). Aucun horaire d’ouverture n’est indiqué.
- **WeChat :** un QR code pour ajouter le service client WeChat de TSJY (添加微信客服), ainsi que son compte de service et sa mini-application WeChat.
- **Entrepôt :** à Huaqiao (花桥镇), Suzhou, avec l’adresse complète sur la page.

Le site est uniquement en chinois et n’indique pas de service en anglais.

## Suivre un colis TSJY avec Peek

[Peek](/) identifie immédiatement TSJY pour un numéro `TSJY…CN` (« Transporteur détecté ») : aucun autre transporteur suivi par Peek n’utilise ce format. Un lien vers la page de résultats de TSJY avec un numéro TSJY après `nos=` fonctionne aussi ; s’il contient plusieurs numéros, Peek prend le premier. Un lien TSJY portant le numéro de la ligne, ou tout numéro ne suivant pas le format TSJY, n’est pas classé sous TSJY : Peek lit ce numéro seul, comme si tu l’avais collé.

Pour tout autre numéro, colle-le quand même : Peek suit plus de 4 500 transporteurs. Quand le format correspond à plusieurs transporteurs, Peek affiche « Vérification des services de suivi… » pendant la recherche ; quand il ne correspond à aucun, Peek le recherche une fois ajouté. Si Peek ne nomme pas le transporteur, appuie sur « Changer » et choisis-le sous « Tous les transporteurs », ou utilise « Changer de transporteur » plus tard. Si tu choisis TSJY pour un autre numéro, Peek indique que TSJY « n’émet pas de numéros comme celui-ci ».

Peek lit le suivi de TSJY : chaque ligne avec son lieu et son heure. Il omet l’origine, le pays de destination et le nom de la ligne. TSJY ne donne ni date de livraison prévue, ni poids, ni code de retrait, ni signataire. Le statut dans Peek suit la ligne la plus récente qu’il comprend.

Quand TSJY fournit un numéro 17FEIA, ou un numéro de ligne dont la forme désigne un seul transporteur connu de Peek, Peek interroge ce transporteur. Tant que celui-ci n’affiche pas un scan au moins aussi récent que le dernier de TSJY, Peek reste sur le suivi de TSJY, tout en affichant déjà qui prend le relais (« Livraison par 17FEIA », ou « Annoncé à 17FEIA, pas encore reçu » tant que 17FEIA n’a que l’annonce du colis, sans scan propre) et son numéro. Ensuite Peek suit le colis là-bas et conserve l’historique de TSJY.

Tant que tu gardes le colis ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes tant que le suivi propre de TSJY répond (une fois que 17FEIA ou le transporteur final prend le relais, Peek suit le rythme de ce transporteur, y compris jusqu’à toutes les 2 minutes à partir de 6 h heure locale le jour de la livraison lorsque son suivi direct répond). Après deux jours sans nouvelle (12 heures tant que le colis reste à « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures ; et s’il reste à « Étiquette créée » pendant sept jours, toutes les 6 heures. Il peut te notifier pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut pas contacter TSJY, 17FEIA ni les boutiques à ta place.

## Questions sur le suivi TSJY

### Qu’est-ce que TSJY (通世集运) ?

Un service de groupage (集运) pour les personnes à l’étranger qui achètent sur des sites chinois. Son site indique qu’il a été fondé en 2005, stocke les colis gratuitement pendant 180 jours et expédie vers plus de 220 pays et régions.

### Où puis-je suivre un numéro TSJY ?

Sur le [site de TSJY](https://www.tsjy56.com/), sous 订单查询 : saisis le numéro TSJY ou le numéro de la ligne, puis appuie sur 查询. Ou colle-le dans Peek.

### Que signifie 订单打包完成，等待出库 ?

En chinois, cela signifie « commande emballée, en attente de sortie d’entrepôt » : ton colis est emballé dans l’entrepôt de TSJY et attend la ligne de transport. Peek affiche « Pris en charge ». L’étape suivante est `订单已出库，开始国际运输` : il est parti et le trajet international a commencé.

### Pourquoi mon colis TSJY affiche-t-il un numéro 17FEIA ?

[17FEIA](carrier:17feia) est la ligne qui achemine ton colis à l’étranger : la carte de résultat TSJY indique son numéro `FEI…YQ` après une barre oblique. Dès que 17FEIA rattrape le suivi, Peek bascule dessus.

### Comment contacter TSJY ?

Depuis la section 客服中心 de sa page d’accueil : un numéro de téléphone, une adresse e-mail et WeChat, le tout en chinois.

:::sources
- [TSJY: 通世集运 (home page)](https://www.tsjy56.com/) – entreprise, services, promesse d’indemnisation, portée du suivi, recherche de commande et page de résultats sur téléphone, commande, contacts, entrepôt
- [TSJY: 轨迹查询 (tracking results)](https://www.tsjy56.com/orderResult.html) – résultats sur téléphone : cartes avec les deux numéros, statuts de carte, message sans résultat
- [TSJY: 轨迹详情 (track details)](https://www.tsjy56.com/orderDetail.html) – nom et numéro du canal (渠道), lignes de suivi, horaires à l’heure de l’appareil
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € depuis le 1er juillet 2026, taxe européenne de traitement de 2 € depuis le 1er novembre 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € par article quelle que soit la valeur, définition d’un article, ventes à distance et transport organisé par le client
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format du numéro, lien, libellés et étapes, fuseaux horaires, passage à la ligne, ce que Peek lit
:::
