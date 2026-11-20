---
title: Suivi Lingdong International : numéro SZLD et statuts
description: Suis ton colis Lingdong International (领东国际) depuis Shenzhen : format du numéro SZLD, sens de chaque étape, relais final et frais de douane à l'arrivée.
slug: suivi-lingdong
published: 2026-11-18
updated: 2026-11-20
---

Lingdong International (领东国际, de son nom complet 深圳市领东国际货运代理有限公司, Shenzhen Lingdong International Freight Forwarding Co., Ltd.) est un transitaire installé à Shenzhen, en Chine, qui expédie à l'étranger les commandes de vendeurs en ligne chinois. Ses propres numéros commencent par SZLD : colle le tien dans le champ ci-dessus ou saisis-le dans la case 快件查询 (recherche d'envoi) sur lingdong56.com, qui ouvre le site de suivi de Lingdong. C'est la boutique où tu as commandé qui réserve l'envoi et te transmet ce numéro avec les détails d'expédition.

## Numéro de suivi Lingdong : à quoi il ressemble

Lingdong ne publie aucun format de numéro. Les lettres de transport que son suivi reconnaît en propre se composent de SZLD suivi de dix chiffres. Avec des chiffres inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `SZLD4829105736` | SZLD et dix chiffres : une lettre de transport Lingdong International. |
| `SZLD482910573` | Neuf chiffres seulement : ce n'est pas le format de Lingdong. Vérifie s'il manque un chiffre. |
| `LX482910576CN` | Deux lettres, neuf chiffres et CN : un numéro [China Post](carrier:china-post), pas celui de Lingdong. |

Les espaces et les minuscules ne gênent pas lorsque tu colles le numéro dans Peek. Si tu colles un numéro comme `SZLD482910573` à qui il manque un caractère, Peek t'avertit : « Ça commence comme un numéro de suivi Lingdong International, mais il manque un caractère : il y a peut-être une faute de frappe. Compare-le avec ton message d’expédition. »

**Où le trouver.** Les clients de Lingdong sont des marchands établis en Chine : son site leur propose un espace client, un calculateur de tarifs et un mode d'emploi d'expédition, mais rien pour les destinataires en dehors de la case de suivi. C'est le vendeur qui réserve le colis et t'envoie le numéro, en général dans l'e-mail d'expédition ou sur la page de ta commande. Introuvable ? Consulte [comment retrouver son numéro de suivi](guide:find-tracking-number).

**Autres numéros.** Le site de suivi de Lingdong retrouve aussi un colis par la référence attribuée par la boutique, affichée sous 参考号 (numéro de référence). Lingdong commercialise en outre des envois via [China Post](carrier:china-post), [PostNL](carrier:postnl), [UPS](carrier:ups), [DHL Express](carrier:dhl-express) et [FedEx](carrier:fedex) : un numéro au format de l'un de ces transporteurs appartient à ce transporteur. Voir [reconnaître le transporteur d'après un numéro de suivi](guide:tracking-number-formats).

## Statuts du suivi Lingdong : ce que veut dire chaque ligne

Lingdong ne publie aucune liste de statuts. Sur son site de suivi, les étapes apparaissent en anglais, en chinois seul, ou en chinois suivi de l'anglais (après une barre oblique ou entre crochets `【…】`). Dans l'ordre où un colis les rencontre, avec le statut affiché par Peek tant que la ligne est la plus récente :

| Ce qu'écrit le suivi de Lingdong | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `货物电子信息已经收到` | En chinois : informations électroniques reçues. Le vendeur a enregistré l'envoi, mais Lingdong n'a pas encore le colis. | « Étiquette créée » |
| `Shipping Label Created` | L'étiquette créée pour la commande, quelques heures avant le premier scan à l'entrepôt de Lingdong. | « Étiquette créée » |
| `Shipment arrived at facility` | Le colis est arrivé à l'entrepôt de Lingdong à Shenzhen : premier scan entre les mains de Lingdong. | « Pris en charge » |
| `Shipment departed from facility` | Il a quitté cet entrepôt en direction de l'étranger. | « En transit » |
| `预计…航班/Flight scheduled for …`, `预计…号航班【Expect flight ….】`, `预计…航班/Scheduled flight on …` | Réservé sur un vol : la date du vol figure à la place des points (dans la première forme en mois.jour, par exemple `10.28` pour le 28 octobre). | « En transit » |
| `送往航司途中【On the way to the airline company】` | En route vers la compagnie aérienne. | « En transit » |
| `航班已起飞【The flight has taken off.】`, `航班已启运/Package in transit to destination country` | L'avion a décollé. | « En transit » |
| `航班抵达【Flight arrival】`, `航班已落地，拆板中/Package arrived at destination country` | L'avion a atterri ; la seconde formulation précise que les palettes aériennes sont en cours de dégroupage. | « En transit » |
| `拆板作业中，等待取货通知【Under destuffing, waiting for pickup instructions.】` | Après l'atterrissage, les palettes sont défaites pendant que le fret attend d'être enlevé en vue du dédouanement : tu n'as rien à aller retirer toi-même. | « En transit » |
| `货物在清关【Customs clearance in progress】` | En cours de dédouanement dans le pays de destination, après l'atterrissage. | « À la douane » |
| `已完成国内报关手续【Domestic customs clearance procedures have been completed.】`, `货物已清关完成/Import customs clearance complete`, `货物已完成清关【The goods have completed customs clearance】` | La douane du pays de destination a libéré l'envoi, après l'atterrissage et avant la livraison. Ici, *Domestic* désigne le pays d'arrivée, pas la Chine. | « En transit » |
| `货物已交服务商【The shipment has been transferred to the logistics partner.】`, `包裹交接给本地物流商处理/Received by local delivery company` | Après le dédouanement, Lingdong a remis le colis à l'entreprise chargée de la livraison locale. | « En transit » |

La ligne uniquement en chinois (`货物电子信息已经收到`) n'est pas un scan physique : le système de suivi de Lingdong la génère automatiquement pour une commande qui n'a pas encore de scan, à la date de création de l'ordre. Dans Peek, les lignes bilingues chinois-anglais n'affichent que leur moitié anglaise (par exemple `Flight arrival` ou `Customs clearance in progress`), tandis que `货物电子信息已经收到` reste en chinois.

Aucune des étapes propres à Lingdong n'annonce la tournée ou la livraison chez toi : la dernière est la remise au partenaire après la douane d'importation. En **Italie**, le suivi de Lingdong peut ensuite relayer les scans de [Poste Italiane](carrier:poste-italiane) : `First parcel scan along Poste Italiane’s logistic network`, `In transit along Poste Italiane’s logistic network` et `In transito, la spedizione è in transito`, jusqu'à `In consegna` (« En livraison ») et `Consegnata, la spedizione è stata consegnata` (« Livré »). Aux **États-Unis**, il peut relayer `Arrived at GOFO Regional Hub`, l'arrivée au centre régional de [GOFO Express](carrier:gofo) (« En transit »). En France, en Belgique ou en Suisse, la livraison finale est assurée par un autre transporteur, dont le suivi prend la suite.

## Qui livre les colis Lingdong et quels frais de douane prévoir

Le site de Lingdong énumère les services vendus aux marchands en Chine : lignes dédiées petits colis (专线小包) vers les États-Unis, le Royaume-Uni, l'Union européenne, l'Australie, le Mexique, le Canada et le Japon ; ePacket (E邮宝) et grands colis China Post ; PostNL (荷兰邮政) ; lignes pour marchandises légères et volumineuses ; expéditions express UPS, DHL et FedEx, dont Lingdong se présente comme agent, ainsi que fret aérien, stockage et préparation de commandes à l'unité.

Lingdong n'indique pas quelle société assure le dernier kilomètre sur chaque ligne vers la France, la Belgique ou la Suisse. Pour les colis China Post, sa page précise seulement qu'ils desservent plus de 200 pays et régions par avion, par voie de surface ou en transport combiné, avec suivi de bout en bout. Lingdong ne publie ni délais de livraison ni règles sur les tentatives de remise, les avis de passage, les points relais ou la durée de garde : dès que le transporteur local prend le colis en charge, ce sont ses règles qui s'appliquent.

**Dans l'Union européenne.** Selon la Commission européenne, depuis le 1er juillet 2026, les biens vendus à distance hors de l'UE dans des envois valant jusqu'à 150 € sont soumis à un droit de douane temporaire de 3 € par catégorie d'article ; depuis le 1er novembre 2026 s'ajoutent des frais de traitement de l'Union de 2 € par catégorie d'article, quelle que soit la valeur de l'envoi (une catégorie regroupe les biens relevant d'un même classement tarifaire, d'une même désignation et, lorsqu'elle doit être déclarée, d'une même origine). Le droit de 3 € ne modifie pas le régime de TVA jusqu'à 150 € : si la boutique utilise le guichet IOSS, la TVA est encaissée lors du paiement de la commande et n'est pas redemandée à l'importation. Lingdong ne dit pas si le vendeur acquitte ces montants à l'avance.

**En France.** En métropole, selon la douane française, la TVA s'applique dès le premier euro lorsqu'elle n'a pas été réglée à l'achat via l'IOSS ; dans un département d'outre-mer, la douane applique des règles distinctes : TVA au-delà de 22 € (sauf en Guyane et à Mayotte, où la TVA ne s'applique pas) et octroi de mer. Le transporteur chargé du dédouanement peut facturer des honoraires pour les opérations de dédouanement. Pour un colis annoncé « bloqué en douane », la douane précise qu'il faut s'adresser au transporteur et non à ses services, et qu'elle ne demande jamais de paiement par SMS ou par courriel ; Cybermalveillance.gouv.fr recommande, au moindre doute, de ne jamais cliquer sur les liens et de vérifier le véritable suivi du colis ou de contacter directement l'entreprise de livraison concernée.

**En Belgique.** Pour un achat en ligne venu hors de l'UE et dédouané en Belgique par bpost, bpost envoie la demande de paiement par e-mail, SMS ou courrier, et les frais se règlent via l'app My bpost, le Track & Trace ou dans un Bureau de Poste ; bpost signale aussi de fausses demandes et conseille de ne jamais cliquer sur un lien suspect.

**En Suisse.** La Suisse ne fait pas partie de l'UE. Selon l'Office fédéral de la douane et de la sécurité des frontières (OFDF), les envois venus de l'étranger sont en principe soumis aux droits de douane et à la TVA, mais ni droits ni TVA ne sont perçus lorsque le montant correspondant est inférieur à 5 francs suisses ; le transporteur qui dédouane facture séparément sa prestation. Voir aussi [colis bloqué en douane](guide:customs).

## Colis Lingdong pas arrivé ou suivi bloqué

**Bloqué sur `Shipment departed from facility`.** Cette ligne marque la sortie de l'entrepôt de Lingdong à Shenzhen. Les étapes aériennes et douanières peuvent suivre, mais aucune ligne propre à Lingdong n'annonce la livraison : son suivi peut donc rester immobile pendant que le colis poursuit sa route. Demande au vendeur quel transporteur livre ta commande dans ton pays et quel est son numéro de suivi. Plus de détails dans [pourquoi un suivi ne bouge plus](guide:tracking-not-updating) et [suivre un colis venu de Chine](guide:tracking-from-china).

**Bloqué après la remise au partenaire.** `货物已交服务商【The shipment has been transferred to the logistics partner.】` clôt la partie gérée par Lingdong. Quatre jours après la dernière étape, Peek signale « Aucune mise à jour depuis quatre jours », même si le transporteur local achemine déjà le colis. Si la page de suivi de Lingdong affiche un 渠道转单号 (numéro de transfert du canal) que Peek n'a pas repris automatiquement, ajoute-le dans Peek comme un colis distinct ; sinon, demande-le au vendeur.

**Uniquement `货物电子信息已经收到`.** La boutique a créé l'ordre d'expédition, mais le colis n'est pas encore entré à l'entrepôt de Lingdong. Si rien ne bouge après plusieurs jours, demande au vendeur s'il l'a bien remis.

**Aucun résultat réel.** Pour un numéro qu'il ne connaît pas, le site de suivi de Lingdong affiche une ligne portant ce numéro comme référence et laisse toutes les autres colonnes vides. Peek interprète cette réponse comme un envoi enregistré sans scan pour l'instant : il continue de vérifier et affiche « En attente de Lingdong International », sans te demander de corriger le numéro. Compare donc toi-même le numéro caractère par caractère avec le message du vendeur.

**Perdu, endommagé ou retourné.** Lingdong ne publie aucune procédure de réclamation pour les destinataires : sa page de conditions de transport (运输条款) est vide. Son site comporte un bouton de discussion pour les colis à problème, demandes d'indemnisation et plaintes, mais c'est le vendeur qui détient le contrat de transport avec Lingdong : adresse-toi à la boutique ou à la marketplace pour un remboursement ou un nouvel envoi. Voir [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).

## Lingdong contact : téléphone et service client

Sur lingdong56.com, rédigé uniquement en chinois :

- **Téléphone :** deux numéros de portable chinois affichés dans l'en-tête du site et sur la page de contact (联系我们), sans horaires ni langues annoncés.
- **QQ :** les boutons de discussion du site (devis, grands comptes, colis en anomalie, indemnisations et réclamations) ouvrent tous une conversation sur QQ, une messagerie chinoise.
- **Adresse :** 深圳市宝安区福海街道展城社区福园二路10号濠和智创B栋625, dans le district de Bao'an, à Shenzhen.

Lingdong ne mentionne aucune adresse e-mail ni aucun bureau hors de Chine. Pour toute question sur ta commande, contacte le vendeur.

## Suivre un colis Lingdong avec Peek

[Peek](/) reconnaît aussitôt un numéro SZLD à dix chiffres comme celui de Lingdong International : aucun autre transporteur de son catalogue n'utilise cette forme. Tu peux aussi coller un lien vers le suivi de Lingdong contenant `documentCode=` et le numéro.

Si le vendeur ne t'a donné que sa référence, colle-la quand même, touche « Changer » et choisis Lingdong International parmi les plus de 3 500 transporteurs suivis par Peek (tu le trouves aussi en tapant 领东国际 ou LDGJ), ou utilise « Changer de transporteur » sur un colis déjà enregistré. Lorsque Lingdong suit l'envoi sous un autre numéro propre, Peek l'indique sous « Lingdong International le suit sous le numéro ». En revanche, Peek retire les tirets : une référence qui en contient doit être recherchée directement sur le site de Lingdong.

Peek lit directement le site de suivi de Lingdong et affiche le statut, chaque étape avec son lieu et, lorsque la fiche de Lingdong le renseigne, le pays de destination. Pour les lignes bilingues chinois-anglais, Peek affiche la moitié anglaise ; la ligne uniquement chinoise `货物电子信息已经收到` et les scans italiens de Poste Italiane restent dans leur langue d'origine (lorsque Poste Italiane figure deux fois à la même heure et au même endroit, avec un titre court comme `In transito` puis la phrase complète `In transito, la spedizione è in transito`, Peek ne garde que la ligne complète). Les heures de Lingdong ne précisant aucun fuseau, Peek les lit à l'heure de Pékin (UTC+8) — sauf `Shipping Label Created`, lu à l'heure du Pacifique quelle que soit la route, et un scan localisé dans une ville et un État américains (comme ceux de GOFO), qui suit l'heure locale de ce site — puis les affiche dans le fuseau de ton appareil : un scan de 17 h en Chine apparaît à 10 h en France métropolitaine, en Belgique et en Suisse en hiver (11 h à l'heure d'été) ; sur peektracker.com et dans les applications iPhone et Android, une mention sous l'historique rappelle « Heures dans ton fuseau horaire : … ».

Peek retient toute étape datée après l'instant de sa consultation, ainsi que les lignes écrites plus de douze heures avant l'heure qu'elles affichent, celles écrites à la même seconde plus de six heures avant la leur, et celles issues de la fonction de suivi sur mesure (轨迹定制), capables de situer un colis à l'étranger avant même son arrivée à l'aéroport : ce sont des prévisions, pas des scans. Une fois leur heure passée, Peek affiche ces étapes écrites d'avance en grisé sous « Écrit à l’avance par Lingdong International » et prévient sous le statut jusqu'à la livraison ou au retour : « Le site de Lingdong International affiche des étapes écrites à l’avance, même la livraison. Peek ne peut pas les confirmer et les affiche donc en grisé. Cherche sur ce site le transporteur qui livre dans ton pays et son numéro de suivi ; s’il n’y en a pas, demande au vendeur. » (ou « les laisse donc de côté » tant qu'aucun libellé n'est affiché). Une étape écrite d'avance ne compte jamais comme un scan : elle ne change pas le statut et n'envoie aucune notification. Peek écarte le nom du destinataire, l'adresse, le poids, le nombre de colis et la preuve de livraison, et n'affiche pas de date de livraison estimée, car Lingdong n'en donne aucune.

Sauf si la fiche de Lingdong désigne un transporteur final vers lequel Peek peut basculer, l'historique s'arrête là où s'arrête celui de Lingdong. Peek reconnaît [La Poste suisse](carrier:swiss-post) lorsque la fiche nomme une ligne Swiss Post, contient un numéro de La Poste suisse et n'indique aucune destination hors de la Suisse et du Liechtenstein : le colis affiche alors « Livraison par Swiss Post » (ou « Annoncé à Swiss Post, pas encore reçu » tant que La Poste suisse n'a que l'annonce électronique sans scan propre), et Peek poursuit le suivi chez elle dès qu'elle publie un scan au moins aussi récent que le dernier de Lingdong, tout en gardant les scans précédents (Lingdong ne mentionne pas La Poste suisse sur son site). Lorsqu'un autre numéro remplace le numéro SZLD dans la fiche, Peek interroge la poste du pays de destination s'il s'agit d'un numéro postal international (deux lettres, neuf chiffres, deux lettres), et bascule de la même manière. Tout autre numéro local donné par le vendeur peut être ajouté comme un colis à part.

Tant que tu gardes le colis ouvert ou le jour de sa livraison avec les notifications activées, Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes le jour où le colis passe « En livraison » (dès 6 h, heure locale, ou une demi-heure avant un créneau annoncé, tant que le propre suivi de Lingdong ou du transporteur final répond ; toutes les 5 minutes si la consultation de ce transporteur ouvre un navigateur ; via les services de suivi, toutes les heures, toutes les 3 heures après un jour sans scan, et jusqu'à toutes les 15 minutes le jour de la livraison avec le colis ouvert ou les notifications activées ; après une bascule, Peek suit le rythme du transporteur final). Sur le suivi de Lingdong lui-même, seule la ligne `In consegna` de Poste Italiane active « En livraison ». En dehors de ces cas, ainsi qu'après 12 heures sans nouveau scan sur « Étiquette créée » ou « Pris en charge » (ou deux jours sinon), Peek vérifie toutes les heures. Tu peux choisir les alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement » : parmi les étapes propres à Lingdong, seule `货物在清关【Customs clearance in progress】` déclenche « Étapes importantes ». Peek ne peut contacter ni Lingdong ni le vendeur à ta place.

## Questions sur le suivi Lingdong

### Qu'est-ce que Lingdong International ?

C'est 深圳市领东国际货运代理有限公司 (Shenzhen Lingdong International Freight Forwarding Co., Ltd.), aussi appelée 领东国际, un transitaire de Shenzhen qui expédie à l'étranger les colis de boutiques en ligne chinoises. Ses numéros propres commencent par SZLD et se suivent dans la case 快件查询 de lingdong56.com ou dans Peek.

### Pourquoi mon suivi Lingdong reste-t-il sur « Shipment departed from facility » ?

Cette étape signale le départ du colis de l'entrepôt de Lingdong à Shenzhen. Les étapes de vol et de douane peuvent suivre, mais aucune ligne propre à Lingdong n'annonce la tournée finale : dans ton pays, une autre société livre le colis. Demande au vendeur laquelle et sous quel numéro la suivre.

### Combien de temps met un colis Lingdong à arriver ?

Lingdong ne publie aucun délai de livraison sur son site. Fie-toi à l'estimation annoncée par la boutique lors de ta commande.

### Vais-je payer des frais de douane sur un colis Lingdong ?

Dans l'Union européenne, les commandes à distance venues hors UE jusqu'à 150 € paient un droit de douane de 3 € par catégorie d'article depuis le 1er juillet 2026, auquel s'ajoutent 2 € de frais de traitement de l'Union par catégorie depuis le 1er novembre 2026 quelle que soit la valeur, en plus de la TVA (sauf paiement via l'IOSS en France métropole ou en Belgique ; dans les DOM français et en Suisse s'appliquent leurs propres seuils et taxes). Lingdong n'indique pas ce que le vendeur a réglé d'avance.

### Qui contacter si mon colis Lingdong n'arrive pas ?

La boutique où tu as commandé : c'est elle qui a réservé l'envoi auprès de Lingdong et détient le contrat. Lingdong n'affiche que deux numéros de portable chinois et un chat QQ destinés aux expéditeurs.

:::sources
- [Lingdong : 深圳市领东国际货运代理有限公司 (accueil)](http://www.lingdong56.com/) – case de suivi 快件查询, services aux marchands, téléphones, boutons QQ, adresse
- [Lingdong : 公司介绍 (présentation)](http://www.lingdong56.com/gsjs.html) – transitaire, petits colis, express, fret aérien
- [Lingdong : 主营业务 (services principaux)](http://www.lingdong56.com/zyyw.html) – lignes dédiées par pays, ePacket, PostNL, grands colis China Post, plus de 200 pays et modes de transport
- [Lingdong : 快递业务 (express)](http://www.lingdong56.com/kdyw.html) – agent pour UPS, DHL et FedEx
- [Lingdong : 联系我们 (contact)](http://www.lingdong56.com/lxwm.html) – adresse à Bao'an (Shenzhen) et téléphones
- [Lingdong : 运输条款 (conditions de transport)](http://www.lingdong56.com/ystk.html) – page sans conditions publiées
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format SZLD, références et liens, moitié anglaise des lignes bilingues, étapes écrites d'avance affichées en grisé, fuseaux horaires, relais vers La Poste suisse et les numéros finaux
- [Commission européenne : EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane temporaire de 3 € jusqu'à 150 € depuis le 1er juillet 2026 et frais de traitement de 2 € depuis le 1er novembre 2026
- [Commission européenne : The temporary EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – définition d'une catégorie d'article, frais de 2 € quelle que soit la valeur, maintien de l'IOSS jusqu'à 150 €
- [Douane : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – TVA en métropole et règles dans les départements d'outre-mer (seuil de 22 € hors Guyane et Mayotte, octroi de mer)
- [Douane : Mesures e-commerce européennes](https://www.douane.gouv.fr/fiche/mesures-e-commerce-europeennes) – droit forfaitaire de 3 € depuis le 1er juillet 2026
- [Douane : Mon colis est bloqué](https://www.douane.gouv.fr/fiche/mon-colis-est-bloque) – s'adresser au transporteur et non à la douane, aucun SMS ou courriel de paiement envoyé par la douane
- [Cybermalveillance.gouv.fr : Escroqueries à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – ne pas suivre les liens de messages réclamant des frais et vérifier sur le site officiel du transporteur
- [bpost : Achats en ligne hors de l’UE](https://www.bpost.be/fr/shopping-hors-ue) – demande de paiement par e-mail, SMS ou courrier ; paiement via My bpost, Track & Trace ou un Bureau de Poste
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – droits et TVA non perçus jusqu'à 5 francs suisses et frais du transporteur en Suisse
:::
