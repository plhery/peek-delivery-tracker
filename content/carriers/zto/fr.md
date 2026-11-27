---
title: Suivi ZTO Express : numéros 中通快递, statuts et contact
description: Suivi colis ZTO Express (中通快递) en Chine : numéros à 12/14 chiffres, différence ZTO International (771), statuts 已签收/已代收, délais, consignes et 95311.
slug: suivi-zto-express
published: 2026-11-21
updated: 2026-11-27
---

ZTO Express (`中通快递`, Zhongtong) est un transporteur chinois fondé à **Shanghai** le 8 mai 2002 (38,52 milliards de colis acheminés en 2025). Un numéro ZTO Express (12 chiffres ou 14 chiffres commençant par `7`) suit un colis circulant en **Chine continentale** — souvent un achat Taobao ou en ligne en route vers une adresse chinoise ou vers l'entrepôt d'un transitaire. Suivez-le sur le [traceur chinois de ZTO](https://www.zto.com/check) ou dans le champ ci-dessus ; pour les envois transfrontaliers du groupe, ZTO International (`中通国际`) utilise des numéros en `771` sur `ztoglobal.com`.

## Numéro de suivi ZTO Express : format et exemples

ZTO l'appelle numéro de lettre de transport (`运单号`). Peek reconnaît deux formats pour ZTO Express. Exemples avec des chiffres inventés :

| Numéro (inventé) | Ce qu'il désigne |
| --- | --- |
| `784920573280` | 12 chiffres : numéro ZTO Express, format également utilisé par [FedEx](carrier:fedex), [SF Express](carrier:sf-express), [DHL](carrier:dhl), [BRT](carrier:brt) et d'autres. |
| `73184920573649` | 14 chiffres commençant par `7` : numéro ZTO Express aussi, partagé avec [DPD Allemagne](carrier:dpd-de), [Chronopost](carrier:chronopost), [SEUR](carrier:seur), [BRT](carrier:brt) et d'autres réseaux. |
| `77148291057364` | `771` suivi de 11 chiffres : format de ZTO International (`中通国际`). Peek interroge ZTO International en premier. |

Les espaces ne gênent pas dans Peek. Le traceur officiel de ZTO accepte jusqu'à 10 numéros à la fois, séparés par des virgules ou des espaces (`最多可查询10条，以逗号、空格隔开`).

**Où le trouver.** L'expéditeur (en général la boutique) conserve son feuillet de la lettre de transport (`发件联`) et communique le numéro dans les détails d'expédition. Introuvable ? Consultez [comment trouver son numéro de suivi](guide:find-tracking-number).

**Sur le site de ZTO.** Saisissez le numéro sur `zto.com/check` et cliquez sur `查询` (rechercher). Le site peut demander de résoudre un puzzle de vérification avant d'afficher les résultats ; scanner le code QR de la page avec WeChat permet de s'abonner aux mises à jour en temps réel.

## Statuts de suivi ZTO Express expliqués

Chaque scan comporte un court intitulé d'étape en chinois, que Peek affiche en anglais :

| ZTO écrit | Peek affiche | Étape dans Peek | Ce que cela signifie pour votre colis |
| --- | --- | --- | --- |
| `已取件` | `Collected` | « Pris en charge » | Un coursier ZTO a récupéré le colis chez l'expéditeur. |
| `已收件` | `Received` | « Pris en charge » | Une agence ZTO a enregistré le colis. |
| `运输中` | `In transit` | « En transit » | En cours d'acheminement entre les centres de tri et agences ZTO. |
| `派件中` | `Out for delivery` | « En livraison » | Un livreur ZTO a le colis en tournée. |
| `已代收` | `Held at a collection point for the recipient` | « Prêt à être retiré » | Un point de retrait (comme une station de quartier) garde le colis pour vous. |
| `已签收` | `Signed for` | « Livré » | Quelqu'un a réceptionné le colis (`签收`) : vous, un tiers pour vous ou l'entrepôt destinataire. |
| `异常通知` | `Exception notice` | « Problème signalé » | ZTO a signalé une anomalie. Peek n'affiche que le nom de l'étape ; le traceur de ZTO contient la phrase complète. |

Les autres étapes, telles qu'un retour (`退回件`) ou une réexpédition, conservent leur libellé chinois d'origine dans Peek. Plus de repères dans [que signifient les statuts de suivi](guide:tracking-statuses).

**Envoi vers un transitaire ?** Dans ce cas, `已签收` signifie que l'entrepôt du transitaire en Chine a réceptionné le colis et que la mission intérieure de ZTO Express est terminée : un nouveau numéro émis par le transitaire prend le relais vers l'étranger. ZTO propose aussi un service de groupage international (`中通国际集运`) qui rassemble plusieurs achats en entrepôt avant expédition groupée ; en Chine continentale, il se joint au `95311` en demandant le service international (`95311转国际`).

## Livraison ZTO Express en Chine : coursiers, stations et consignes

Les règles ci-dessous s'appliquent aux livraisons effectuées en **Chine continentale** :

Un livreur ZTO remet le colis à l'adresse, ou le dépose dans une station de quartier (`驿站`), un point de collecte (`代收点`) ou une consigne automatique (`智能快件箱`) à proximité : le contrat de service de ZTO conclu avec les expéditeurs prévoit ces modes de remise. Les *Mesures d'administration du marché de l'express* en Chine (`快递市场管理办法`), en vigueur depuis le 1er mars 2024, exigent toutefois votre accord préalable avant tout dépôt en consigne ou en station et vous autorisent à vérifier l'envoi à la livraison — voire à l'ouvrir ou à le refuser si l'emballage extérieur est visiblement endommagé ou si le contenu est fragile.

Pour les colis non remis en main propre (porte, consigne, station), le contrat de ZTO demande au destinataire de filmer (`录制视频`) le retrait, l'ouverture et la vérification du contenu à titre de preuve ; une livraison effectuée comme convenu est réputée confirmée faute d'objection écrite dans les trois jours (`三日`).

D'après la FAQ officielle de ZTO en Chine :

- **Pas de code de retrait pour une consigne ?** Cherchez l'agence qui dessert l'adresse via la [recherche d'agences ZTO](https://www.zto.com/service/station) (`服务网点查询`) et appelez-la, ou communiquez le numéro de suivi au `95311` pour obtenir le numéro du livreur ou de l'agence.
- **Frais demandés pour dépassement de garde en consigne ?** ZTO indique d'appuyer sur passer (`跳过`) lors du retrait pour ne rien payer.
- **Frais de livraison réclamés à la remise ?** L'agence distributrice n'a le droit de facturer aucun frais de livraison au destinataire (`派件网点不可以向收件客户收取任何派送费`).
- **Adresse hors zone de desserte ?** Retirez le colis à l'agence (`自提`) ou demandez à l'expéditeur de le faire suivre par un autre transporteur.
- **Erreur d'adresse ?** Impossible de la modifier une fois le colis en tournée ou livré ; avant cela, l'expéditeur doit contacter l'agence de départ, selon l'endroit où se trouve l'envoi.

## Colis ZTO sans mise à jour, perdu ou retourné

**Aucun résultat pour l'instant.** Si le traceur de ZTO ne trouve rien, vérifiez le numéro auprès du vendeur. En attendant, un colis enregistré sous ZTO Express affiche « En attente de ZTO Express » ; sans transporteur attribué, il indique « Aucun historique de suivi pour le moment ».

**Plus de nouveau scan.** Lorsqu'un colis toujours en route n'a reçu aucun scan depuis 4 jours, Peek rappelle que « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger ZTO Express. » et ajoute le lien « Demande de l’aide à ZTO Express » vers la page de contact de ZTO. Consultez [pourquoi le suivi ne bouge plus](guide:tracking-not-updating) et [suivre un colis venu de Chine](guide:tracking-from-china).

**Perdu ou endommagé (en Chine continentale).** Prévenez le vendeur : selon le contrat de ZTO, l'expéditeur (ou l'ayant droit qu'il désigne) présente une réclamation écrite dans les 30 jours (`三十日`) après avoir eu connaissance de la perte ou de l'avarie. Sans assurance valeur déclarée (`保价`), ZTO indemnise au maximum à hauteur de 500 yuans (`500元`) ; avec assurance, dans la limite de la valeur assurée. Les manquants ou dommages internes lorsque l'emballage extérieur est intact sont exclus. Voyez [colis perdu](guide:lost-parcel).

**Retourné à l'expéditeur.** En Chine continentale, ZTO peut renvoyer un colis lorsque l'adresse est imprécise, que le destinataire refuse l'envoi ou que deux tentatives échouent sans faute de ZTO (`两次派送未成功的`). Voyez [colis retourné à l'expéditeur](guide:returned-to-sender).

**Marqué livré mais introuvable.** `已代收` signifie qu'un point de retrait garde le colis, et `已签收` que quelqu'un a signé (pas forcément vous : accueil d'immeuble, station ou entrepôt de transitaire). Pour réclamer en Chine continentale, donnez le numéro au service client au `95311` ou passez par le [formulaire de réclamation ZTO](https://www.zto.com/after/complaint/problemTypes) (`投诉反馈`, connexion requise) ; en cas de réponse insatisfaisante ou tardive, la réglementation chinoise permet de saisir l'administration postale (`邮政管理部门`) en médiation (`申诉`). Voyez [colis indiqué livré mais non reçu](guide:delivered-not-received).

## Numéro de téléphone et service client ZTO Express

Coordonnées publiées par ZTO Express en **Chine continentale** :

- **`95311` :** ligne nationale du service client (`全国统一客服热线`, appelée *Customer Service Hotline* sur le site anglais de ZTO), ouverte de `08:30` à `17:30` (heure de Chine) selon la FAQ.
- **`95720` :** numéro sortant dédié utilisé par les livreurs ZTO pour appeler les destinataires (`中通业务员专属外呼号码`).
- **Service client en ligne** (`在线客服`) sur `zto.com`, ainsi que le mini-programme WeChat (`微信小程序`), le compte officiel WeChat et l'application mobile ZTO.

Pour votre commande elle-même, contactez d'abord le vendeur qui a expédié l'article.

## Suivre un colis ZTO Express avec Peek

Collez le numéro dans [Peek](/) : il suit plus de 4 500 transporteurs. Coller un numéro ZTO seul ne suffit pas pour que Peek interroge ZTO Express tout de suite : Peek interroge d'abord certains transporteurs partageant le même format (comme [FedEx](carrier:fedex), [DHL](carrier:dhl), [BRT](carrier:brt) et [Colis Privé](carrier:colis-prive) sur 12 chiffres, jamais ZTO ; DPD, [Chronopost](carrier:chronopost), [SEUR](carrier:seur) et [BRT](carrier:brt) sur 14 chiffres ; ZTO International en premier sur un numéro en `771`), puis les services de suivi en affichant « Vérification des services de suivi… ». Si ces services connaissent le colis et désignent ZTO Express, Peek le classe sous ZTO Express et interroge ZTO au contrôle suivant. Après les services de suivi, pour 14 chiffres commençant par `73` ou `79`, Peek consulte le traceur officiel de ZTO dans un navigateur et classe le colis sous ZTO Express si ZTO le connaît. Si rien n'apparaît, la ligne indique « pas encore trouvé · Peek continue de vérifier ». Pour interroger d'emblée le traceur de ZTO Express, appuyez sur « Changer » et sélectionnez **ZTO Express** sous « Correspond à ce numéro », ou recherchez `ZTO` ou `中通快递` (`中通` affiche aussi ZTO International) ; sur un colis déjà ajouté, utilisez « Changer de transporteur ». L'adaptateur ZTO Express n'accepte que 12 chiffres ou 14 chiffres commençant par `7` : pour tout autre format, Peek signale « n’émet pas de numéros comme celui-ci ».

Peek lit le traceur officiel de ZTO et ouvre un navigateur lorsque ZTO demande une vérification. Pour chaque scan, il affiche l'étape traduite en anglais (tableau ci-dessus), la ville ou le nom chinois du site ZTO et l'heure convertie dans le fuseau de votre appareil — sur peektracker.com et dans les applications iPhone et Android, une ligne sous l'historique rappelle ce fuseau (« Heures dans ton fuseau horaire : … »). Peek écarte la phrase complète de chaque scan ainsi que les coordonnées des livreurs, numéros de téléphone, nom et adresse du destinataire, et n'affiche pas de date estimée. Comme le suivi public sur ordinateur de ZTO peut masquer les toutes premières étapes de la commande, elles peuvent aussi manquer dans Peek. Peek ne suit que le numéro ZTO : ajoutez séparément le numéro international d'un éventuel transitaire.

Tant que vous gardez la page du colis ouverte ou, le jour de sa livraison, avec les notifications activées, Peek vérifie les mises à jour jusqu'à toutes les 10 minutes — et jusqu'à toutes les 2 minutes (toutes les 5 minutes lorsque la consultation de ZTO a ouvert un navigateur) à partir de 6 h locales le jour où ZTO scanne le colis en « En livraison », tant que le traceur de ZTO répond (via les services de suivi seuls, toutes les heures, toutes les 3 heures après un jour sans mouvement et jusqu'à toutes les 15 minutes le jour de la livraison si le colis est ouvert ou que les notifications sont actives). En dehors de ces cas, ainsi qu'après deux jours sans nouveauté (ou 12 heures sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Alertes disponibles : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni ZTO, ni le transitaire, ni le vendeur à votre place.

## Questions fréquentes sur le suivi ZTO Express

### Qu'est-ce que ZTO Express (中通快递) et est-ce fiable ?

ZTO Express est l'un des principaux transporteurs express chinois, fondé à Shanghai le 8 mai 2002 et coté aux Bourses de New York (2016) et de Hong Kong (2020). En 2025, son réseau comptait plus de 31 000 points de service couvrant plus de 99 % des districts et comtés chinois.

### Quelle est la différence entre ZTO Express et ZTO International (771) ?

Ils appartiennent au même groupe mais utilisent deux services et deux traceurs séparés. ZTO Express ne couvre que la Chine continentale (hors Hong Kong, Macao et Taïwan) sur `zto.com/check` ; ZTO International (`中通国际`), créé en 2014, prend en charge les colis internationaux sous des numéros commençant par `771` sur `ztoglobal.com`.

### Combien de temps prend une livraison ZTO Express en Chine ?

D'après la FAQ de ZTO (délais indicatifs), un colis arrive en général le lendemain dans la même province ainsi qu'entre le Jiangsu, le Zhejiang, Shanghai et l'Anhui, en 3 à 5 jours entre les autres provinces et en 5 à 7 jours vers certaines régions éloignées. Pour un achat passant par un transitaire, ajoutez ensuite le transport international.

### Que veulent dire « 已签收 » et « 已代收 » sur le suivi ZTO ?

« 已签收 » signifie réceptionné contre signature (`Signed for`, étape « Livré » dans Peek) : vous, un proche, l'accueil ou l'entrepôt du transitaire en Chine a signé. « 已代收 » (`Held at a collection point for the recipient`, étape « Prêt à être retiré ») indique que le colis attend dans une station ou un point de retrait.

### Comment contacter ZTO Express et que faire sans code de consigne ?

Appelez la ligne nationale `95311` en Chine continentale (08:30–17:30, heure de Chine) ou utilisez le service client en ligne sur `zto.com` ; les appels du `95720` proviennent des livreurs ZTO. Sans code de consigne, recherchez l'agence locale via `zto.com/service/station` ou demandez son numéro au `95311`.

:::sources
- [ZTO Express : 运单查询 (recherche d'envoi)](https://www.zto.com/check) – suivi en chinois, jusqu'à 10 numéros et abonnement WeChat
- [ZTO Express : 关于中通 (à propos)](https://www.zto.com/introduce/about/home) – création en 2002, cotations, 38,52 milliards de colis et réseau 2025, Taobao depuis 2008
- [ZTO Express : 首页 (accueil)](https://www.zto.com/) – 95311, 95720, application et WeChat
- [ZTO Express : Home (anglais)](https://en.zto.com/) – mention Customer Service Hotline: 95311
- [ZTO Express : 联系我们 (contact)](https://www.zto.com/introduce/about/contact) – service client en ligne et canaux officiels
- [ZTO Express : 常见问题 (FAQ)](https://www.zto.com/after/answers) – horaires 08:30–17:30, consignes, interdiction de frais de remise, hors zone, changement d'adresse et délais
- [ZTO Express : 服务网点查询 (recherche d'agences)](https://www.zto.com/service/station) – agence desservant une adresse
- [ZTO Express : 投诉反馈 (réclamations)](https://www.zto.com/after/complaint/problemTypes) – formulaire de réclamation après connexion
- [ZTO Express : 快递服务协议 (contrat de service)](https://mtoc.zto.com/agreement/service-protocol-pc.html) – périmètre Chine continentale, modes de remise, vidéo et délai de 3 jours, retours, plafond de 500 yuans et réclamation sous 30 jours
- [ZTO Express : 中通国际 (ZTO International)](https://www.zto.com/introduce/service/international) – branche internationale du groupe depuis 2014
- [ZTO Express : 中通集运 (groupage)](https://www.zto.com/introduce/service/concentrated) – groupage international et contact 95311
- [Ministère chinois des Transports : 快递市场管理办法](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – vérification à la remise, accord requis pour consignes/stations et recours postal
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats de numéros, traduction anglaise des étapes, vérification par navigateur, reconnaissance par navigateur des numéros de 14 chiffres commençant par 73 ou 79 et champs écartés
:::
