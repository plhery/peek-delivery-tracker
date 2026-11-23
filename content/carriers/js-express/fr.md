---
title: Suivi colis JS Express : numéro JSE, statuts et livraison
description: Suivre un colis JS Express (急速国际) venu de Chine : le numéro JSE…YQ, ce que veulent dire ses scans et qui livre en France, en Belgique et en Suisse.
slug: js-express-suivi
published: 2026-11-17
updated: 2026-11-20
---

JS Express (急速国际, aussi JS EXP) est une société logistique chinoise destinée aux boutiques en ligne transfrontalières, exploitée à Suzhou par 急创速供应链管理（苏州）有限公司. Si un vendeur a expédié ta commande avec JS Express, ton numéro commence par JSE et finit par YQ : colle-le dans le champ ci-dessus. JS Express achemine le colis hors de Chine jusqu'à ton pays, puis un transporteur local prend le relais ; en France, son suivi reprend parfois en français les scans de [Colissimo](carrier:la-poste).

## Numéro de suivi JS Express : à quoi il ressemble

Un bordereau JS Express se compose de JSE, deux lettres, dix chiffres et YQ, soit 17 caractères en tout. Avec des chiffres inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `JSEAA9999999980YQ` | JSE, deux lettres, dix chiffres et YQ : un bordereau JS Express. |
| `JSEAA999999980YQ` | Neuf chiffres seulement : ce n'est pas le format de JS Express. Vérifie s'il manque un chiffre. |
| `JXCAA9999999980YQ` | Finit aussi par YQ, mais commence par JXC : un numéro [Juxi Express](carrier:juxi-express) (que Peek vérifie d'abord chez [DHLink](carrier:dhlink) lorsque JXC est suivi de YI, ZO, US ou ZI). |

D'autres transitaires chinois utilisent la même structure avec leurs propres lettres au début : FEI pour 17FEIA, WNB pour WanbExpress, SFC pour SFC, CJP pour CJ Packet. JSE désigne JS Express. Les espaces et les minuscules ne gênent pas Peek : un zéro tapé à la place de la seconde des deux lettres est lu comme un O, et un O parmi les dix chiffres comme un zéro. Le suivi de JS Express exige en revanche le numéro complet : un extrait ne donne rien.

**Où le trouver.** La boutique t'envoie ce numéro dans son e-mail d'expédition ou l'affiche sur la page de ta commande. JS Express indique travailler étroitement avec Amazon, Walmart, Shopify, eBay, AliExpress, Wish et Shopee. Si tu ne remets pas la main dessus, consulte [où trouver son numéro de suivi](guide:find-tracking-number).

**Aucun lien à partager.** Les pages de suivi de JS Express reçoivent le numéro dans un formulaire, si bien qu'aucune adresse web n'ouvre directement un colis : copie le numéro. La page anglaise, « Express Query », répond tout de suite ; la page chinoise demande d'abord un code image (验证码).

## Suivi JS Express : ce que veulent dire les statuts

La page de JS Express affiche chaque scan avec une date et une heure, un lieu entre 【crochets】 et un message, du plus récent au plus ancien, le premier marqué « Latest ». Les messages sont le plus souvent en anglais. Dans l'ordre où un colis les traverse :

| Ce qu'écrit JS Express | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Electronic notification of order creation received.` | La boutique a envoyé les données de la commande à JS Express. JS Express n'a pas encore le colis. | « Étiquette créée » |
| `PRE-ADVICE` | Un partenaire a reçu les données de l'envoi, avant que l'entrepôt de JS Express ait le colis. | L'étape déjà atteinte |
| `Item has been collected` | L'entrepôt de JS Express en Chine a le colis : c'est son premier vrai scan. | « Pris en charge » |
| `Item inbound in sorting center.`, `Item outbound in sorting center.` | Trié chez JS Express en Chine. | « En transit » |
| `Domestic allocation has begun`, `Under domestic allocation` | Toujours à l'entrepôt de JS Express en Chine, après le tri et avant le départ. | « En transit » |
| `Waybill Generated for` | Le transporteur de ton pays a créé son bordereau pendant que le colis est encore à l'entrepôt de JS Express en Chine. | « Étiquette créée », ou l'étape déjà atteinte, le plus souvent « En transit » |
| `Arrive at transit center` | Au centre de transit de JS Express en Chine, entre le tri et le départ. | « En transit » |
| `Item departed from origin.`, `Arrive at international airport to abroad` | En route vers la sortie, puis arrivé à l'aéroport en Chine. | « En transit » |
| `Arrived at export center`, `Exports Center Sorting and Packing`, `Arrived at Origin Port` | Sur certaines lignes, entre ces étapes : un centre d'export en Chine trie et emballe le colis, qui gagne ensuite le point de sortie de Chine. | « En transit » |
| `Shipment arrived at facility and measured.`, `Depart from facility to service provider.` | Un site en Chine pèse et mesure le colis, puis le transmet à la ligne qui assure le vol. | « En transit » |
| `Start on-site transportation and shipment` | À l'aéroport en Chine, après l'arrivée sur place : la manutention a commencé avant que la douane chinoise libère l'envoi à l'export. | « En transit » |
| `Released From Export Customs` | Le dédouanement à l'export en Chine est terminé. Ce n'est pas encore la douane de ton pays. | « En transit » |
| `DEPART ORIGIN AIRPORT`, `Departure from the original airport`, `Item Flight departure`, `Initial landing`, `Connecting flights flight takeoff` | L'avion a décollé, parfois avec une escale et un vol de correspondance. | « En transit » |
| `ARRIVED AT DESTINATION AIRPORT`, `Arrival to the destination airport`, `Item Flight landing`, `NOA-Notification of arrival from GHA` | Atterri à l'aéroport d'arrivée. NOA est l'avis d'arrivée envoyé par le prestataire au sol de l'aéroport. | « En transit » |
| `The customs clearance agent is queuing up to pick up the goods` | Le déclarant en douane attend au terminal fret de l'aéroport pour récupérer l'envoi : ni lui ni la douane ne l'ont encore. | « En transit » |
| `The customs clearance agent has picked up the goods` | Le déclarant a récupéré l'envoi au terminal fret pour le dédouanement à l'import. | « À la douane » |
| `The goods have been picked up abroad` | Le partenaire à destination a repris l'envoi à l'aéroport. Ce n'est pas un nouvel enlèvement chez le vendeur. | « En transit » |
| `Customs clearance agent received the shipment in destnation country.` | Le déclarant dans ton pays a pris l'envoi en charge après le vol pour l'importation (« destnation » est l'orthographe de JS Express). | « À la douane » |
| `Inbound to customs temporary warehouse` | Entré en entrepôt sous douane, en attente du dédouanement à l'import. | « À la douane » |
| `Released From Import Customs`, `Released from customs: customs cleared.` | La douane de ton pays a laissé passer le colis. | « En transit » |
| `Depart from the destination distribution center.` | Un centre de distribution dans ton pays fait suivre le colis après la douane. | « En transit » |
| `In transit, it's progressing through post network` | La poste de ton pays achemine le colis dans son réseau, avant toute tournée. | « En transit » |
| `collection by overseas warehouse` | L'entrepôt de JS Express à destination a réceptionné le colis après la douane. | « En transit » |
| `Load cargo overseas`, `Vehicles depart from` | Cet entrepôt charge le colis pour le transporteur local, puis le camion part du lieu indiqué avec le scan. | « En transit » |
| `Delivery to local courier`, `Last Mile Delivery` | Remis au transporteur qui livre dans ton pays, avant son premier scan. Le colis n'est pas encore chez toi. | « En transit » |
| `Delivery in progress` | Sur certaines routes : le transporteur local a le colis dans son agence, avant la tournée. Malgré la formule, il n'est pas encore en livraison. | « En transit » |
| `到达待取` | Statut propre à JS Express, en chinois : arrivé, en attente de retrait. Le lieu n'indique que le pays. | « Prêt à être retiré » |
| `The delivery address is incorrect. Please ask the recipient to contact their local area?` | Avis de JS Express après un échec de livraison : l'adresse est incomplète ou erronée. Il t'invite à joindre l'agence locale, dont la page de JS Express donne le téléphone ; Peek retire les numéros de téléphone. | « Problème signalé » |

Ce même avis d'adresse existe aussi en chinois dans le suivi (`收件人地址不详或有误，派送失败，逾期退回或销毁的具体费用以账单为准，麻烦让收件人联系下当地`), avec un numéro de téléphone à la fin : Peek l'affiche en anglais et le classe lui aussi en « Problème signalé ».

**Les scans de Colissimo en France.** Quand [Colissimo](carrier:la-poste) assure la livraison en France, l'historique de JS Express reprend ses étapes en français :

| Ce qu'écrit Colissimo dans JS Express | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Votre Colissimo va bientôt nous être confié ! Il est en cours de préparation chez votre expéditeur.` | Colissimo a enregistré l'étiquette pendant que JS Express prépare ou achemine encore l'envoi : cette ligne ne change pas l'étape déjà atteinte. | L'étape précédente |
| `Votre colis est en transit sur nos plateformes logistiques.` | Colissimo achemine le colis entre ses plateformes en France. | « En transit » |
| `Votre Colissimo est en cours de traitement sur le site de tri local.` | Le colis est arrivé sur la plateforme de tri proche de chez toi. | « En transit » |
| `L'adresse indiquée sur votre colis est incomplète ou incorrecte. Nous procédons à sa correction afin de permettre le bon acheminement de votre colis.` | Colissimo vérifie et corrige un détail d'adresse pour poursuivre l'acheminement. | « En transit » |
| `Votre colis est sur son site de distribution. Nous le préparons pour le mettre en livraison.` | L'agence de distribution prépare le colis pour la tournée du jour. | « En livraison » |
| `Votre Colissimo n'a pas pu vous être livré en raison d'un problème d'adresse. Il va être retourné à l'expéditeur.` | La livraison a échoué à cause de l'adresse et le retour est annoncé, mais l'expéditeur ne l'a pas encore récupéré. | « Problème signalé » |
| `Votre colis est livré à son expéditeur à la suite d'un retour.` | Le colis retourné a été remis à l'expéditeur. | « Retourné à l’expéditeur » |

**Les scans d'une poste partenaire en Europe.** Sur certaines lignes postales européennes, l'historique reprend en anglais `Package scanned at carrier facility` et `Departure to country of destination` au hub européen, puis `Arrival from abroad`, `Departure to distribution network`, `Your shipment has arrived at the postal operator of the country of destination and will be delivered in the coming days.` et `Arrival distribution office` dans ton pays, tous affichés « En transit ». Si le facteur ne trouve personne, `Not Delivered - Addressee not present- Message left` signale l'avis laissé (« Tentative de livraison »), suivi de `Item arrival at collection point for pick-up` quand le colis t'attend au point de retrait (« Prêt à être retiré »).

**Un transporteur sans nom en majuscules.** Sur certaines lignes, JS Express relaie les scans du livreur en capitales sans nommer la société ni le pays. `SHIPMENT RECEIVED`, `PROCESSED AT HUB` et `SHIPMENT PROCESSED` marquent le passage au hub, et `DEPARTED FROM DEPOT` le départ vers le dépôt de livraison (« En transit »). `RECEIVED AT LOCAL DEPOT` indique ce dépôt local avant une tournée ou au retour d'un échec : c'est le dépôt du transporteur, pas un point relais, donc Peek garde le statut de la ligne précédente. `DELIVERY ATTEMPTED: 1ST DELIVERY ATTEMPT`, `DELIVERY ATTEMPTED: 2ND DELIVERY ATTEMPT` et `DELIVERY ATTEMPTED: 3RD DELIVERY ATTEMPT` sont des passages infructueux (« Tentative de livraison ») ; après le troisième, le transporteur demande de convenir d'une livraison, puis `SHIPMENT REDIRECTED: ON CONSIGNEE'S REQUEST` indique que le colis repart selon ta demande (« En transit »). `DELIVERY EXCEPTION` signale un blocage au hub avant toute tournée, et `DELIVERY EXCEPTION, WAIT FOR INFORMATION CONFIRMATION`, après `ARRIVE AT THE WAREHOUSE` (« En transit »), signifie que le transporteur retient le colis en attendant une confirmation : Peek affiche les deux en « Problème signalé ».

**Des lignes qui ne sont pas des scans.** `SHIPPING INFORMATION RECEIVED`, `SHIPMENT CREATED`, `Data Received`, `货物电子信息已经收到`, `Your package is being prepared by the sender and will be handed over to us soon` et, dans un hub européen, `Shipment Data Uploaded` suivi de `Processed` n'enregistrent que les données informatiques de l'envoi, parfois plusieurs jours avant le colis lui-même. `Consignee Contacted`, écrit à la même seconde qu'un échec de livraison, note un appel ou un message du livreur : aucune de ces lignes ne fait avancer le statut.

**Des heures dans le désordre.** Les horaires s'affichent sans fuseau et mêlent plusieurs horloges : l'heure de Pékin en Chine, et le plus souvent l'heure locale aux aéroports et chez les transporteurs européens. Fie-toi à l'ordre des lignes plutôt qu'aux heures brutes.

## Qui livre les colis JS Express en France, en Belgique et en Suisse

**France.** JS Express cite la France parmi les pays couverts par sa ligne Europe (avec le Royaume-Uni, l'Allemagne, l'Italie, l'Espagne, la Suisse et le Danemark, hors certains codes postaux éloignés). Dans son suivi, c'est [Colissimo](carrier:la-poste) qui apparaît en France avec ses scans en français (voir plus haut), sans que JS Express affirme pour autant que chaque envoi vers la France passe par La Poste.

**Belgique et Suisse.** La page de la ligne Europe de JS Express mentionne la Suisse parmi ses destinations, mais l'historique connu de Peek n'y nomme pas le transporteur final, pas plus qu'en Belgique. La fiche informatique de JS Express peut contenir le numéro du transporteur local sans jamais indiquer son nom, et la page web de JS Express ne montre ni l'un ni l'autre. Quand la forme de ce second numéro désigne à coup sûr un seul transporteur actif dans ton pays (comme un numéro Colissimo en France ou un numéro propre à La Poste suisse en Suisse), Peek nomme ce transporteur et l'interroge aussi. S'il s'agit d'un [numéro postal international](guide:tracking-number-formats) valide (deux lettres, neuf chiffres et deux lettres), Peek interroge la poste du pays : [La Poste](carrier:la-poste) en France, [bpost](carrier:bpost) en Belgique ou [La Poste suisse](carrier:swiss-post) en Suisse. Si Peek n'affiche aucun nom de livreur, demande à la boutique qui assure la remise et sous quel numéro.

Les passages, avis de passage et délais de garde suivent les règles du transporteur local : JS Express n'en publie aucune. Comme une ligne `到达待取` n'indique que le pays, consulte le suivi du transporteur local ou interroge le vendeur pour savoir où retirer le colis. Voir aussi [avis de passage : que faire](guide:missed-delivery) et [colis non retiré](guide:uncollected-parcel).

## Colis JS Express bloqué, en retard ou pas arrivé

**`未获取到任何数据,请提供有效单号!`** La page anglaise de JS Express répond cette phrase en chinois lorsqu'elle ne connaît pas le numéro (« aucune donnée trouvée, veuillez fournir un numéro de bordereau valide »). `No track data for now!` signifie qu'il n'y a pas encore de scan. Dans les deux cas, Peek affiche « En attente de JS Express » et continue de vérifier. Si JS Express ne reconnaît toujours pas ce numéro deux jours plus tard et qu'aucun autre service n'a répondu pour lui, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » Compare le numéro caractère par caractère avec celui de la boutique ; si rien ne bouge après quelques jours, demande au vendeur s'il a bien remis l'envoi.

**Bloqué sur `Electronic notification of order creation received.`** JS Express n'a pas encore réceptionné le colis. Demande à la boutique quand elle l'a confié au transitaire.

**Bloqué sur `Inbound to customs temporary warehouse`.** Le colis attend le dédouanement à l'importation. Sur certaines routes, les lignes s'intitulent `Start Custom clearance Process`, puis `customs clearance completed` ; un `Under customs clearance, please wait patiently.` plus tôt dans le parcours correspond à la douane d'exportation chinoise à l'aéroport de départ (« En transit »). JS Express vend sa ligne Europe en droits acquittés (DDP) : la société préperçoit la TVA auprès du vendeur et ne fournit pas de bordereau fiscal. Dans l'Union européenne, les ventes à distance de biens importés jusqu'à 150 € supportent un droit de douane forfaitaire de 3 € par type de produit depuis le 1er juillet 2026, et des frais de traitement douanier de 2 € par type de produit s'ajoutent depuis le 1er novembre 2026, quelle que soit la valeur. En France, la douane rappelle qu'il est inutile de la contacter pour un colis « bloqué » : adresse-toi au transporteur, et méfie-toi des SMS ou courriels réclamant un paiement en son nom, car elle n'en envoie jamais. En Suisse, l'Office fédéral de la douane et de la sécurité des frontières (OFDF) indique de même que, dans le trafic postal et le trafic de courrier rapide, le dédouanement est assuré par le transporteur (UPS, La Poste, etc.) : lui seul peut t'aider à retrouver l'envoi, et les questions sur ses frais de dédouanement relèvent aussi de lui. Plus de détails dans [colis bloqué en douane](guide:customs), [pourquoi un suivi ne bouge plus](guide:tracking-not-updating) et [suivre un colis venu de Chine](guide:tracking-from-china).

**Plus rien après `Delivery to local courier`.** Le transporteur local a pris le relais. Si Peek affiche son numéro, consulte son propre suivi ; sinon, demande au vendeur qui livre ton colis.

**Marqué livré, mais rien dans la boîte.** Vérifie d'abord auprès du transporteur local (La Poste en France, bpost en Belgique, La Poste suisse en Suisse), puis préviens la boutique. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Perdu, endommagé ou retourné.** Le client de JS Express est le vendeur. D'après les conditions de JS Express, toute demande d'indemnisation doit être déposée par écrit par son client dans les 30 jours suivant la réception du colis par JS Express, et les réclamations d'assurance passent par le compte client du vendeur. Demande donc sans attendre un remboursement ou un renvoi à la boutique ou à la plateforme. Lorsqu'un colis ne peut pas être livré (destinataire absent, refus, adresse erronée, blocage douanier), JS Express peut organiser le retour aux frais de l'expéditeur s'il le demande ; sinon, l'envoi est abandonné et détruit par la douane à destination. Sur certaines routes, `Refused` suivi de `Returned` signifie que le colis refusé en tournée est revenu au centre de tri du livreur, pas encore chez le vendeur : Peek affiche « Problème signalé ». Voir [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).

## JS Express contact et service client

Au 16 novembre 2026, la page de contact en chinois de JS Express indique les coordonnées suivantes (la page anglaise, plus ancienne, affiche encore d'autres numéros et adresses) :

- **Ligne de service :** 400-0353-518, un numéro chinois également indiqué pour les recherches internationales. JS Express ne publie pas d'horaires et ne précise pas si ce numéro est joignable depuis l'étranger.
- **QQ et WeChat :** QQ d'entreprise 4000353518 et compte officiel WeChat `js-express`.
- **Réclamations :** un formulaire sur la page anglaise « Complaint » et la même ligne 400-0353-518.
- **Bureaux :** siège et entrepôt à Suzhou, d'autres entrepôts en Chine, ainsi que des bureaux de son réseau à Hong Kong et à Walnut, en Californie.

Comme seul le vendeur peut déposer une réclamation auprès de JS Express, adresse-toi à la boutique pour ta commande, et au transporteur local pour la livraison.

## Suivre un colis JS Express avec Peek

[Peek](/) reconnaît un numéro JS Express à sa forme (JSE, deux lettres, dix chiffres et YQ), qu'aucun autre transporteur de son catalogue n'utilise, et interroge directement le suivi de JS Express. Si tu n'as que le numéro du transporteur local, colle-le à la place : Peek suit plus de 4 500 transporteurs, et si le transporteur n'est pas reconnu automatiquement, tu peux le sélectionner avec « Changer ».

Peek lit la page anglaise de JS Express et affiche l'historique complet, chaque scan avec son lieu ainsi que le pays de destination, que cette page n'affiche pas. `到达待取` apparaît en anglais sous la forme `Arrived, awaiting collection`, tout comme l'avis chinois d'adresse incomplète. Les numéros de téléphone sont retirés, mais le nom de la personne qui a signé est conservé ; Peek laisse aussi de côté les photos de livraison et le numéro de commande de la boutique que la fiche peut contenir. Comme date de livraison estimée, Peek affiche le jour prévu par JS Express, puis retire cette date dès que le colis passe « En livraison », puisque la tournée a lieu le jour même. Les lignes qui ne sont pas des scans ne modifient pas le statut.

Les scans enregistrés en Chine sont lus à l'heure de Pékin, et ceux des transporteurs européens dans le fuseau déduit du lieu ou du pays de destination ; tous s'affichent dans le fuseau horaire de ton appareil, du plus récent au plus ancien.

Lorsque la fiche de JS Express contient un numéro local dont la forme appartient sans ambiguïté à un transporteur actif dans ton pays, Peek affiche « Livraison par » suivi de ce transporteur et de son numéro, et l'interroge aussi ; un numéro postal international valide est vérifié auprès de la poste du pays de destination (La Poste, bpost ou La Poste suisse). Tant que ce transporteur n'a enregistré que l'annonce de l'envoi sans scan propre, Peek affiche « Annoncé à …, pas encore reçu ». Dès que ce transporteur publie un scan au moins aussi récent que le dernier de JS Express, Peek suit le colis chez lui en affichant les deux numéros et en conservant l'historique de JS Express. Jusque-là, Peek reste sur JS Express, qui relaie souvent déjà les scans du livreur.

Tant que tu gardes le colis ouvert, ou le jour de sa livraison avec les notifications activées, Peek vérifie le suivi jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes (toutes les 5 lorsqu'il faut ouvrir la page de JS Express dans un navigateur) dès 6 h, heure locale, le jour où le colis passe « En livraison », tant que le suivi de JS Express répond lui-même ; sinon toutes les heures. Via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après un jour sans nouveauté et jusqu'à toutes les 15 minutes le jour de la livraison, à la même condition. Lorsqu'un autre transporteur prend le relais pour la livraison, Peek suit le rythme de celui qui livre le colis. Après deux jours sans nouveau scan (ou 12 heures tant que le colis reste à « Étiquette créée » ou « Pris en charge »), Peek passe à une vérification par heure. Tu peux choisir les alertes « Chaque scan », « Étapes importantes » (douane, problèmes, tentatives, « Prêt à être retiré », mise en livraison, livraison et retour) ou « Livraison seulement ». Peek ne peut contacter ni JS Express ni le vendeur à ta place.

## Questions fréquentes sur le suivi JS Express

### C'est quoi, JS Express (急速国际) ?

Une entreprise logistique chinoise spécialisée dans le e-commerce transfrontalier, exploitée par 急创速供应链管理（苏州）有限公司. Elle indique avoir été enregistrée à Hong Kong en 2010 et avoir ouvert son centre d'opérations à Suzhou en 2011.

### Où suivre un colis JS Express ?

Dans le champ en haut de cette page, ou sur la page anglaise « Express Query » du site js-exp.com. La page chinoise demande d'abord un code image.

### Que veut dire 未获取到任何数据 sur le suivi JS Express ?

« Aucune donnée trouvée » : le suivi de JS Express ne connaît pas ce numéro, soit à cause d'une faute de frappe, soit parce que JS Express n'a pas encore reçu l'envoi. Vérifie le numéro auprès du vendeur.

### Qui livre les colis JS Express en France, en Belgique et en Suisse ?

Un transporteur local dans ton pays. En France, l'historique de JS Express montre les scans en français de Colissimo. En Belgique et en Suisse, l'historique connu de Peek ne nomme pas le livreur : si la fiche contient un numéro postal international, Peek interroge bpost ou La Poste suisse ; sinon, demande au vendeur qui livre ta commande.

### Qui contacter si mon colis JS Express n'arrive pas ?

La boutique où tu as passé commande : c'est elle qui a expédié le colis avec JS Express et qui peut déposer une réclamation dans le délai de 30 jours. Le numéro 400-0353-518 de JS Express est une ligne chinoise.

:::sources
- [JS Express : 急速国际 (accueil)](https://www.js-exp.com/) – société exploitante
- [JS Express : Introduction (en anglais)](https://www.js-exp.com/EN/index.php?page=6) – Hong Kong 2010, Suzhou 2011, e-commerce transfrontalier
- [JS Express : 企业介绍 (présentation)](https://www.js-exp.com/index.php?page=6) – Suzhou 2011, agences, plateformes partenaires
- [JS Express : Express Query (en anglais)](https://www.js-exp.com/EN/index.php?page=19) – suivi anglais par formulaire sans lien direct, réponses
- [JS Express : 快件查询 (suivi)](https://www.js-exp.com/index.php?page=19) – code image sur la page chinoise
- [JS Express : Insurance Claims (en anglais)](https://www.js-exp.com/EN/index.php?page=29) – réclamations du client sous 30 jours, colis non livrables
- [JS Express : 投保理赔 (assurance et indemnisation)](https://www.js-exp.com/index.php?page=29) – délai écrit de 30 jours après réception du colis par JS Express, compte client, retour payant ou destruction
- [JS Express : Complaint (en anglais)](https://www.js-exp.com/EN/index.php?page=32) – formulaire de réclamation et ligne 4000353518
- [JS Express : Contact Us (en anglais)](https://www.js-exp.com/EN/index.php?page=35) – anciens numéros et adresses
- [JS Express : 联系我们 (contact)](https://www.js-exp.com/index.php?page=35) – ligne 400-0353-518 pour les recherches internationales, QQ, WeChat, bureaux en Chine, à Hong Kong et en Californie
- [JS Express : 急速欧洲专线 (ligne Europe)](https://www.js-exp.com/index.php?aid=35) – pays desservis (dont la France et la Suisse), mode DDP avec TVA préperçue, codes postaux éloignés exclus
- [Douane française : Mesures e-commerce européennes](https://www.douane.gouv.fr/fiche/mesures-e-commerce-europeennes) – droit forfaitaire de 3 € sur les envois jusqu'à 150 € et frais de traitement
- [Douane française : Mon colis est bloqué](https://www.douane.gouv.fr/fiche/mon-colis-est-bloque) – s'adresser au transporteur, aucun SMS ou courriel de paiement envoyé par la douane
- [Office fédéral de la douane et de la sécurité des frontières (OFDF) : Frais de dédouanement et suivi des colis](https://www.bazg.admin.ch/fr/frais-dedouanement-et-suivi-des-colis) – dédouanement effectué par le transporteur en Suisse, renseignements auprès de lui
- [Commission européenne : EU Customs Reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € dès le 1er juillet 2026 et frais de traitement de 2 € dès le 1er novembre 2026
- [Commission européenne : Guidance on the 3 EUR customs duty and Union handling fee (PDF, en anglais)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € par type de produit vendu à distance quelle que soit sa valeur
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format JSE…YQ, libellés et étapes des scans, scans français de Colissimo, scans d'une poste partenaire en Europe et d'un livreur sans nom en majuscules, lignes sans mouvement, fuseaux horaires, repli navigateur, numéro du livreur et passage de relais vers La Poste, bpost ou La Poste suisse
:::
