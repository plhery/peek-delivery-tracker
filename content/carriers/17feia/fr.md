---
title: Suivi 17FEIA : numéros FEI…YQ, statuts et livraison
description: Suivre un colis 17FEIA (一起飞) depuis la Chine : format FEI…YQ, signification des statuts, transporteur final, douane et affichage dans Peek.
slug: suivi-17feia
published: 2026-11-21
updated: 2026-11-21
---

17FEIA (一起飞, également appelé Shanghai Hongshan International Logistics, 上海宏杉国际物流) est une société logistique chinoise. Elle réceptionne les colis dans son entrepôt de Shenzhen ou de Shanghai, les achemine hors de Chine puis, après le dédouanement à l'importation, les remet à un transporteur chargé de la livraison. Ses numéros commencent par `FEI` et se terminent par `YQ`. Colle le tien dans le champ ci-dessus ou utilise le suivi propre à 17FEIA sur www.17feia.com. Le numéro est fourni par l'expéditeur : la boutique ou le commissionnaire qui a expédié tes articles depuis la Chine.

## Numéro de suivi 17FEIA : à quoi il ressemble

Peek identifie les numéros 17FEIA par le format `FEI`, deux lettres, dix chiffres et `YQ`, soit 17 caractères au total. Pour les départs de Shenzhen, les deux lettres après `FEI` sont `SZ`. Exemples inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `FEISZ4829105736YQ` | `FEI`, deux lettres, dix chiffres et `YQ` : un numéro 17FEIA. Peek reconnaît 17FEIA d'après ce seul numéro. |
| `FEISZ482910573YQ` | Seulement neuf chiffres : ne correspond pas au format 17FEIA. Vérifie s'il manque un chiffre. |
| `FEISZ4829105736AB` | Deux lettres autres que `YQ` à la fin. Peek ne désigne pas 17FEIA d'emblée, mais interroge le suivi de 17FEIA pour vérifier s'il le connaît. |
| `HHWKG4829105736YQ` | Se termine par `YQ`, mais commence par `HHW` : un numéro [HuaHan Logistics](carrier:huahan). |

Dans Peek, les minuscules, espaces et tirets ne changent rien.

**Où le trouver.** Dans l'e-mail d'expédition, sur la page de commande ou sur ton compte d'agent d'expédition si tu en as utilisé un. Sinon, consulte [comment trouver ton numéro de suivi](guide:find-tracking-number). Plus tard, le dossier 17FEIA peut aussi transmettre à Peek le numéro du transporteur final.

## Statuts de suivi 17FEIA expliqués

Le dossier 17FEIA associe à la plupart des lignes un libellé court en chinois et une phrase de texte, du plus récent au plus ancien. Peek affiche le texte en anglais et détermine l'étape à partir du libellé chinois. Voici les lignes propres à 17FEIA dans l'ordre habituel :

| Ce qu'indique 17FEIA | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Item information received.` | 17FEIA a reçu les données du colis, pas encore le colis lui-même. | « Étiquette créée » |
| `The package is on the way to the facility.` (`包裹在去设施路上`) | En route vers l'entrepôt 17FEIA, qui ne l'a pas encore reçu. | « Étiquette créée » |
| `Warehouse receipt.` (`仓库收货`) | L'entrepôt 17FEIA de Shenzhen ou de Shanghai a reçu le colis. | « Pris en charge » |
| `The order has information pending customer confirmation.` (`订单待客户确认`) | L'entrepôt attend que le client de 17FEIA (qui a réservé l'envoi) confirme la commande. | « Pris en charge » |
| `Order Confirmed.` (`订单已确认`) | La commande est confirmée. | « Pris en charge » |
| `Order dispatched from warehouse.` (`订单已出库`) | Le colis a quitté l'entrepôt. | « En transit » |
| `Export declaration in progress.` (`出口报关中`) | Le colis fait l'objet d'une déclaration d'exportation en Chine, étape courante avant le départ. Il n'est pas retenu et il ne s'agit pas de la douane de ton pays. | « En transit » |
| `Flight has departed.` (`航班已起飞`) | Le vol a quitté la Chine. | « En transit » |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | Escale en cours de route, puis départ du vol suivant. | « En transit » |
| `Flight Arrived at Clearance Port.` (`航班抵达清关港`) | Arrivé à l'aéroport où s'effectue le dédouanement à l'importation. | « En transit » |
| `Broker picked up Shipment and delivered to warehouse.` (`清关行提货到仓`) | Un commissionnaire en douane a transféré l'envoi vers son entrepôt pour le dédouanement (ou après la mainlevée). Ce n'est pas une livraison chez toi. | « À la douane » (ou « En transit » s'il intervient après `清关完成`) |
| `Customs Clearance is in progress.` (`清关中`) | La douane d'importation examine l'envoi. | « À la douane » |
| `Customs inspection in Progress, Release Pending.` (`海关查验，暂未放行`) | Après l'arrivée au port de dédouanement, la douane inspecte l'envoi et ne l'a pas encore libéré. | « À la douane » |
| `Customs Clearance Completed.` (`清关完成`) | La douane d'importation a libéré le colis. | « En transit » |
| `Arrived at delivery hub，Awaiting carrie scan.` (`已交仓，等待上网`) | Remis au dépôt du transporteur qui livre. Le premier scan de ce transporteur suit. (« carrie » est l'orthographe du suivi.) | « En transit » |
| `包裹已做国内退回处理` (`国内退回`) | Retour intérieur en Chine : après confirmation à l'entrepôt, le colis repart vers l'expéditeur au lieu de quitter le pays. | « Retourné à l’expéditeur » |

**Deux lignes qui ne font pas avancer le statut.** `Estimated departure date:` suivi d'une date (`预计起飞日期`) est une prévision écrite avant le vol, pas un scan : Peek l'écarte. `Flight delayed.` (`航班延误`) signale un vol retardé : Peek conserve l'étape de la ligne précédente.

**La barre de progression.** La page de suivi de 17FEIA affiche aussi une barre allant de `仓库收货` (réception en entrepôt) à `成功签收` (signé à la livraison) en passant par `飞机起飞` (décollage) et `飞机落地` (atterrissage). Peek ne reprend pas cette barre, mais classe une ligne libellée `成功签收` en « Livré ».

**Les lignes du transporteur final.** Après la remise, le suivi de 17FEIA peut relayer les scans du transporteur final sous le libellé `运输途中` (en transit), dans ses propres mots et avec son lieu : par exemple les scans de DHL (`The international shipment has been processed in the export parcel center`), d'UPS aux États-Unis (`Arrived at Facility`, `Departed from Facility`, `On the Way, Delivery will be delayed by one business day.`) ou d'un partenaire au Royaume-Uni (`Shipment received in depot`). Peek les interprète selon leur texte : une réception reste « En transit », tandis qu'une mise en livraison ou une livraison font avancer le statut.

**Pourquoi les horaires semblent dans le désordre.** Les étapes en entrepôt en Chine (y compris un retour intérieur) suivent l'heure de Pékin (UTC+8). Les scans relayés du transporteur local utilisent l'heure locale de leur lieu quand celui-ci définit un fuseau unique. Les lignes de vol et de douane n'indiquent pas de lieu et le fuseau affiché par 17FEIA ne correspond pas toujours à celui de l'étape : fie-toi à l'ordre des lignes.

## Qui livre les colis 17FEIA

Après le dédouanement à l'importation, 17FEIA confie le colis à un transporteur qui assure l'acheminement jusqu'à toi. Peek n'ayant pas pu consulter le site web de 17FEIA, cette page ne dresse pas de liste exhaustive de ses partenaires ni de ses zones desservies.

**Ce que montre le code de Peek.** Peek reconnaît trois noms que 17FEIA utilise pour ses partenaires : `DPD-DE` pour [DPD Germany](carrier:dpd-de), `DE-DHL` pour [DHL](carrier:dhl) en Allemagne et `FEDEX-GROUND` pour [FedEx](carrier:fedex). Il reconnaît cinq destinations dans le dossier 17FEIA : Allemagne, France, Pays-Bas, Pologne et États-Unis. Un historique dans le code de Peek montre DHL en Allemagne prenant en charge un colis destiné à la France pour le confier au transporteur qui y livre.

**Livraison manquée.** Les tentatives de livraison et le retrait suivent les règles du transporteur final : contacte-le avec son propre numéro. Voir [livraison manquée](guide:missed-delivery).

**Douane dans l'UE.** Les biens achetés en ligne hors de l'Union européenne supportent un droit de douane temporaire de 3 € par article sur les envois d'une valeur allant jusqu'à 150 €, depuis le 1er juillet 2026, ainsi qu'une redevance de traitement de l'Union de 2 € par article, quelle que soit la valeur, depuis le 1er novembre 2026. Un article désigne les marchandises partageant le même classement tarifaire, la même description et, lorsqu'elle est requise, la même origine. Peek n'a pas pu vérifier comment ces frais sont perçus sur un colis 17FEIA : demande au vendeur ou à ton commissionnaire s'ils étaient inclus dans le prix. Hors UE, les frais dépendent de ton pays. Pendant que la douane d'importation inspecte le colis sans l'avoir libéré, le suivi affiche `Customs inspection in Progress, Release Pending.` Voir [colis retenu à la douane](guide:customs), et avant de payer à la suite d'un SMS ou d'un e-mail, [SMS frauduleux de livraison](guide:delivery-scam-texts).

## Colis 17FEIA bloqué, perdu ou retourné

17FEIA travaille pour le donneur d'ordre qui a réservé l'expédition : le vendeur ou le commissionnaire auquel tu as fait appel. Pour un colis en retard, perdu, endommagé ou retourné, contacte-le en premier.

**Numéro introuvable.** Pour un numéro inconnu, le suivi de 17FEIA n'affiche aucun avancement. Peek indique « En attente de 17FEIA » et continue de vérifier. Si 17FEIA ne connaît toujours pas le numéro deux jours plus tard et qu'aucune autre source n'a répondu, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. », puis vérifie une fois par jour, et une fois par semaine une fois sept jours passés depuis l'ajout. Vérifie le numéro par rapport au message d'expédition : `FEI`, deux lettres, dix chiffres, `YQ`.

**Bloqué sur `The order has information pending customer confirmation.`** Si tu es passé par un agent d'expédition, vérifie ton compte ou tes messages chez lui. Sinon, contacte le vendeur.

**Aucune nouvelle après le vol.** Après `Flight has departed.`, plusieurs jours peuvent passer sans nouvelle ligne. Après quatre jours sans nouvelle ligne, Peek le signale avec le jour de cette ligne (« Aucune mise à jour depuis … »). Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating) et [suivre un colis depuis la Chine](guide:tracking-from-china).

**Affiché comme livré, mais rien reçu.** Vérifie autour de chez toi et auprès des voisins, puis contacte le transporteur final et le vendeur. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Perdu ou retourné.** Demande un remboursement ou un renvoi au vendeur ou au commissionnaire. Voir [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service client et contact 17FEIA

Le site web de 17FEIA ayant répondu à Peek par un contrôle anti-robot, cette page ne fournit aucune coordonnée de contact pour 17FEIA. Pour toute question sur ta commande, contacte le vendeur ou le commissionnaire qui a réservé l'expédition : ce sont eux les clients de 17FEIA. Pour la livraison finale, adresse-toi au transporteur local.

## Suivre un colis 17FEIA avec Peek

[Peek](/) reconnaît aussitôt 17FEIA à partir d'un numéro `FEI…YQ` (« Transporteur détecté ») : aucun autre transporteur suivi par Peek n'utilise ce format. Un lien vers la page de suivi de 17FEIA contenant le numéro après `orderNumber=` fonctionne aussi. Aucun code postal ni compte n'est requis.

Pour tout autre numéro, par exemple celui du transporteur final, colle-le tout de même : Peek suit plus de 4 500 transporteurs. Lorsque le format correspond à plusieurs transporteurs sans certitude, Peek affiche « Vérification des services de suivi… » le temps d'interroger ses sources ; s'il ne correspond à aucun format, Peek le recherche une fois ajouté. Pour choisir le transporteur toi-même, appuie sur « Changer » et recherche `17FEIA`, `Feia` ou `一起飞`, ou utilise « Changer de transporteur » sur un colis déjà ajouté. Peek n'interroge 17FEIA que pour les numéros composés de `FEI`, deux lettres, dix chiffres et deux lettres : pour un autre format, la ligne indique que 17FEIA « n’émet pas de numéros comme celui-ci ».

Peek affiche le statut, les lignes dans l'anglais de 17FEIA avec le lieu quand il est fourni, ainsi que le pays de destination. Il lit les étapes d'entrepôt sur l'heure de Pékin et les scans relayés ayant un pays à fuseau unique (ou un État américain à fuseau unique) sur leur heure locale, puis les convertit dans le fuseau de ton appareil. Les lignes de vol et de douane n'indiquent pas de lieu : Peek leur applique l'heure des lignes voisines, celle de Pékin jusqu'à ce qu'une ligne annonce l'arrivée du vol, puis celle du transporteur final si 17FEIA relaie ses scans, sinon celle de ton pays. Ces heures peuvent être décalées de quelques heures. Peek n'affiche pas la barre de progression, l'origine, les codes postaux ni le poids. 17FEIA ne fournit ni date de livraison prévue, ni nom de service, ni coordonnées de l'expéditeur ou du destinataire, ni preuve de livraison.

Lorsque 17FEIA indique DPD Germany, DHL ou FedEx avec un numéro correspondant au format de ce transporteur, la fiche du colis l'affiche (par exemple « Livraison par DPD Germany ») et inscrit ce numéro après celui de 17FEIA. Tant que ce transporteur n'a reçu que l'annonce du colis sans scan propre, la fiche indique « Annoncé à …, pas encore reçu ». Dès que ce transporteur enregistre un scan au moins aussi récent que le dernier de 17FEIA, Peek bascule le suivi chez lui tout en conservant l'historique 17FEIA. Si le suivi de 17FEIA est injoignable, Peek interroge des services de suivi et affiche leur historique dans leurs termes.

Tant que tu gardes le colis ouvert à l'écran ou le jour de la livraison avec les notifications activées, et que le suivi de 17FEIA répond, Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes dès 6 h (heure du transporteur) le jour où le colis passe en livraison ; sinon, toutes les heures. Lorsque ce sont des services de suivi qui répondent, Peek vérifie toutes les heures, et jusqu'à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées. Après deux jours sans nouvelle (12 heures tant que le colis affiche « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Il peut te prévenir pour « Chaque scan », « Étapes importantes » ou « Livraison seulement » ; « Étapes importantes » inclut la douane au port de dédouanement, mais pas la déclaration d'exportation en Chine. Peek ne peut contacter ni 17FEIA, ni le vendeur, ni ton commissionnaire à ta place.

## Questions sur le suivi 17FEIA

### Qu'est-ce que 17FEIA (一起飞) ?

Une société logistique chinoise, aussi connue sous le nom de Shanghai Hongshan International Logistics (上海宏杉国际物流). Elle expédie des colis hors de Chine depuis son entrepôt de Shenzhen ou de Shanghai et, après le dédouanement à l'importation, les remet à un transporteur chargé de la livraison. Ses numéros commencent par `FEI` et finissent par `YQ`.

### Où suivre un numéro FEI…YQ ?

Dans le champ ci-dessus, ou sur le suivi de 17FEIA à l'adresse www.17feia.com. Saisis le numéro complet : `FEI`, deux lettres, dix chiffres et `YQ`.

### Que signifie « Awaiting carrie scan » sur le suivi 17FEIA ?

La ligne complète est `Arrived at delivery hub，Awaiting carrie scan.` : le dédouanement à l'importation est terminé et 17FEIA a remis le colis au dépôt du transporteur qui livre. Ce dernier ne l'a pas encore scanné ; son propre suivi peut rapidement prendre de l'avance sur celui de 17FEIA.

### Qui livre les colis 17FEIA ?

Le transporteur auquel 17FEIA remet le colis après le dédouanement à l'importation. Le dossier 17FEIA peut indiquer son nom et son numéro : lorsqu'il s'agit de DPD Germany, de DHL en Allemagne ou de FedEx, la fiche Peek l'affiche. Sinon, demande au vendeur.

:::sources
- [Universal Parcel Scraper : 17FEIA](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/17feia) – noms, format `FEI…YQ` et départs de Shenzhen (`SZ`), suivi et lien `orderNumber=`, libellés chinois, textes anglais et étapes (y compris l'inspection douanière, `国内退回`, le changement d'étape de `清关行提货到仓` après `清关完成` et les lignes relayées DHL/UPS/dépôt UK), barre de progression, fuseaux horaires, partenaires (`DPD-DE`, `DE-DHL`, `FEDEX-GROUND`) et destinations
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats concurrents `YQ` (`HuaHan Logistics`), services de suivi de secours et bascule vers le transporteur final
- [Commission européenne : EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane temporaire de 3 € par article jusqu'à 150 € depuis le 1er juillet 2026, redevance de traitement de l'Union de 2 € depuis le 1er novembre 2026
- [Commission européenne : Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – redevance de 2 € par article quelle que soit la valeur, définition d'un article
:::
