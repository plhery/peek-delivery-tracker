---
title: YunExpress suivi : numéro YT, statuts et qui livre
description: Suivre un colis YunExpress (Yun Express) venu de Chine : le numéro YT, les statuts en anglais expliqués, qui livre en France, en Belgique et en Suisse.
slug: yunexpress-suivi
published: 2026-10-10
updated: 2026-10-27
---

YunExpress, aussi écrit Yun Express, est une société de logistique chinoise, basée à Shenzhen, qui expédie depuis la Chine les petits colis de vendeurs en ligne, notamment de vendeurs présents sur Amazon, Temu, SHEIN ou la Fnac. Pour suivre ton colis, saisis le numéro qui commence par YT sur [yuntrack.com](https://www.yuntrack.com/), le site de suivi de YunExpress, ou colle-le dans le champ ci-dessus. YunExpress ne décrit aucun SMS ni e-mail aux destinataires : le numéro se trouve dans l'e-mail d'expédition de la boutique ou sur la page de ta commande.

## Numéro de suivi YunExpress : YT et 16 chiffres

Yuntrack demande « your YunExpress tracking number (starting with YT) », sans en donner la longueur. Peek reconnaît comme YunExpress les numéros formés de YT et de 16 chiffres, soit 18 caractères en tout. Avec des chiffres inventés :

| Numéro | À qui il appartient |
| --- | --- |
| `YT1234567890123456` | YT et 16 chiffres : YunExpress. |
| `YT1234567890123` | YT et 13 chiffres : YTO Express, un autre transporteur. |
| `YT123456789012345` | YT et 15 chiffres : pas une forme YunExpress. Vérifie s'il manque un chiffre. |
| `LP12345678901234` | LP et 14 chiffres : pas YunExpress. Cette forme renvoie plutôt à [Cainiao](carrier:cainiao). |

Pas de numéro dans les messages de la boutique ? Lis [où trouver son numéro de suivi](guide:find-tracking-number). Un numéro d'une autre forme ? Vois [à quel transporteur il appartient](guide:tracking-number-formats).

Sur yuntrack, colle ton numéro YT et clique sur « Track » ; sépare plusieurs numéros par une virgule, un espace ou un retour à la ligne. Un second numéro, celui du transporteur local, peut ensuite apparaître dans la colonne « Last Mile Tracking ». Ton colis est classé `Not Found` ? Yuntrack n'affiche aucun scan pour ce numéro, et YunExpress ne dit pas au bout de combien de temps un nouveau numéro reçoit son premier scan. Vérifie le YT et les 16 chiffres, puis demande à la boutique de confirmer le numéro.

## Suivi colis YunExpress : ce que veulent dire les statuts

Yuntrack n'existe qu'en anglais, sans choix de langue. Chaque colis y est rangé sous un onglet : `Processing` (en préparation), `Transit` (en route), `Delivered` (livré), `Not Found` (introuvable), `Alert` (alerte) ou `Returned` (retourné). Les scans suivent, en anglais eux aussi. Les plus courants, à peu près dans l'ordre :

| Ce qu'affiche YunExpress | Ce que ça veut dire pour toi |
| --- | --- |
| `Shipment information received` | Le vendeur a transmis les données ; YunExpress n'a peut-être pas encore le colis. |
| `Shipment picked up` | YunExpress a récupéré le colis chez le vendeur, en Chine. |
| `The country of origin commences customs declaration.` | La déclaration d'export en Chine, pas ta douane. |
| `International flight has departed` | L'avion vers ton pays a décollé. |
| `Start Customs Clearence` | Le dédouanement à l'import commence (orthographe de YunExpress). |
| `Customs inspection - Import` | La douane a choisi le colis pour un contrôle. |
| `Clearance processing completed - Import` | Dédouané, le colis repart. |
| `Delivered to local carrier` | Remis au transporteur local qui va te livrer, pas à toi. |
| `Your package is ready to be picked up.` | Ligne relayée de DHL : ton colis t'attend dans un DHL ServicePoint. |
| `Delivered by Mailbox` | Déposé dans ta boîte aux lettres. |
| `POD available` | Ligne relayée de DPD : la preuve de livraison existe, le colis est livré. |

Le statut qui trompe le plus, c'est `Delivered to local carrier` : YunExpress a seulement remis le colis au transporteur du pays de destination, dont les lignes suivent, en anglais elles aussi. La ligne de bpost `Your shipment has been delivered to the postal operator of the country of destination and will be delivered in the coming days.` n'est pas non plus une livraison : le colis passe à la poste du pays de destination. Et `Sender goods issue`, de DPD, est un enregistrement créé quand le colis est encore en Chine, pas un problème avec ta marchandise.

`Transit` veut dire en route ; la colonne « Last Event » montre le dernier scan. YunExpress ne définit pas `Alert`, son onglet des colis à problème : lis la ligne en dessous et écris à la boutique si rien ne bouge. Le code de yuntrack prévoit aussi une barre de progression dont l'étape `Pickup` désigne l'enlèvement chez le vendeur, pas un retrait en point relais. Les étapes communes à tous les colis sont détaillées dans [ce que veulent dire les statuts de suivi](guide:tracking-statuses).

## Qui livre les colis YunExpress en France, en Belgique et en Suisse

YunExpress fait voler les colis depuis la Chine, les fait dédouaner, puis les remet à un transporteur local. Ce trajet est détaillé, étape par étape, dans [suivre un colis venu de Chine](guide:tracking-from-china).

**Pour savoir qui livre ton colis**, ouvre-le sur yuntrack : la colonne « Last Mile Tracking » donne le numéro du transporteur local, l'encadré « Additional Notes » peut renvoyer vers son site, et les lignes après `Delivered to local carrier` viennent de lui. Dans Peek, quand yuntrack renvoie vers un transporteur que Peek connaît et que le numéro lui correspond, la fiche du colis affiche « Livraison par » suivi de son nom, avec ce numéro et un lien vers son suivi.

YunExpress a lancé ses lignes dédiées à la France en 2016 et à la Suisse en 2020, et ses lignes desservent aussi la Belgique. En janvier 2023, son groupe, Zongteng, a ouvert une ligne cargo Shenzhen–Paris-Charles-de-Gaulle avec son propre Boeing 777F. Mais ses pages ne nomment aucun partenaire de livraison dans ces pays : le groupe dit seulement travailler avec des partenaires logistiques locaux. Tu as peut-être lu que [Colissimo](carrier:la-poste) ou [Colis Privé](carrier:colis-prive) livrent les colis YunExpress : aucune page de YunExpress ne le confirme. Des lignes de DPD, de [bpost](carrier:bpost) et de DHL Parcel aux Pays-Bas apparaissent dans des historiques YunExpress, sans que le pays de destination soit noté.

En France, ses lignes « 云选 » couvrent selon lui 95 % des codes postaux, une liste que le transporteur local met à jour. Une de ses lignes standard et sa ligne économique ne livrent pas les îles rattachées aux pays d'Europe. En Suisse, sa ligne économique livre dans tout le pays.

Livraison le samedi, tentatives, avis de passage, durée de garde, nouvelle date : YunExpress ne publie rien de tout cela pour les destinataires. Regarde quel transporteur local est nommé dans le suivi et consulte ses propres règles de livraison.

**Points relais et consignes.** Pour l'Europe, YunExpress propose aux vendeurs deux lignes de retrait : sur l'une, il choisit le point relais ou la consigne le plus proche de l'acheteur, dans un rayon de 3 km, et bloque la commande s'il n'y en a pas ; sur l'autre, l'acheteur choisit. Selon un article non daté, le colis arrive au point en 6 à 10 jours calendaires en moyenne, et le suivi affiche l'adresse du point. YunExpress ne dit pas quels réseaux de relais il utilise.

## Colis YunExpress non reçu, bloqué ou perdu : que faire

YunExpress ne donne aux destinataires ni démarche, ni formulaire, ni délai pour un colis bloqué, en retard, perdu ou abîmé. Son client, c'est le vendeur qui signe le contrat : c'est donc lui qui peut réclamer. Écris à la boutique ou à la marketplace avec ton numéro de commande et ton numéro YT. Plus rien ne bouge depuis des jours ? Lis [pourquoi un suivi de colis ne bouge plus](guide:tracking-not-updating).

Bloqué à `Customs inspection - Import` ? Selon le blog de YunExpress pour les vendeurs, être choisi pour un contrôle ne veut pas dire à lui seul un long blocage : c'est le niveau atteint par le contrôle qui fixe la durée. Ses durées habituelles, pas des promesses : un contrôle des documents prend de quelques jours ouvrés à une semaine, un passage au scanner ou une inspection physique souvent une à deux semaines de plus, parfois avec des frais, et un examen par les autorités des semaines, voire plus d'un mois. Pour l'UE, YunExpress annonçait le 18 juin 2026 la fin, au 1er juillet 2026, de l'exonération de droits de douane pour les colis jusqu'à 150 €, remplacée par un droit provisoire de 3 € par article, sans dire qui le paie sur ta commande. Ce que tu peux devoir en France, en Belgique ou en Suisse : [colis bloqué en douane](guide:customs).

Marqué livré mais rien reçu ? La page de suivi peut proposer une preuve de livraison, qui demande le nom du destinataire. Demande ensuite au transporteur local et à la boutique, puis suis [les étapes pour un colis livré mais non reçu](guide:delivered-not-received). `Returned` est l'onglet de yuntrack pour les colis retournés ; YunExpress ne publie pas de règles de retour pour les destinataires, vois donc avec la boutique.

## YunExpress contact en France : téléphone et e-mail

Relevé le 27 octobre 2026 : YunExpress ne donne ni téléphone, ni formulaire, ni bureau pour les destinataires en France, en Belgique ou en Suisse. Une de ses actualités mentionne bien « YunExpress France », mais sans adresse ni contact. Aucune page en français n'a été trouvée : le choix de langue de yunexpress.com ne propose que l'anglais et le chinois. Il publie :

- **E-mail :** info@yunexpress.com, sous « Customer Service » en bas de yunexpress.com, de 8 h à 23 h, sans fuseau horaire. Rien ne dit que les destinataires y sont servis ; la page d'accueil promet des réponses en anglais et en chinois.
- **Téléphone :** 400-8575-500 sur yunexpress.cn, un numéro de Chine continentale. Rien ne dit qu'il fonctionne depuis l'étranger.
- **Contacts régionaux :** uniquement pour l'Asie et l'Australie.

L'encadré « Feedback » de yuntrack concerne le site, pas ton colis. Commence donc par la boutique, qui a le contrat avec YunExpress, puis, après la remise, par le transporteur local nommé dans le suivi, via ses propres canaux.

## Suivre un colis YunExpress avec Peek

[Peek](/) reconnaît YT suivi de 16 chiffres comme YunExpress, même avec des espaces, et accepte les liens de yuntrack.com. Il vérifie le suivi toutes les 10 minutes en journée (toutes les 5 minutes une fois le colis en livraison) et toutes les une à deux heures la nuit, place chaque scan sur une carte et peut t'envoyer des notifications : à chaque scan, pour les étapes importantes ou avec l'option « Livraison seulement ». Il affiche le statut, l'historique, le lieu de chaque scan et l'heure de livraison.

Quand YunExpress donne le numéro du transporteur local sous une forme connue, Peek peut suivre le colis chez ce transporteur, mais pas pour chaque colis. Il n'affiche ni date de livraison prévue ni point relais pour les colis YunExpress, laisse de côté les références de commande et ne peut ni contacter YunExpress ni réclamer à ta place.

## Questions sur le suivi YunExpress

### YunExpress, c'est quoi ?

Une société chinoise de logistique pour le commerce en ligne : Shenzhen Qianhai YunExpress Logistics Co., Ltd., fondée à Shenzhen en 2014. Elle dit desservir plus de 220 pays et régions depuis la Chine, et être devenue partenaire logistique recommandé d'Amazon en 2017, puis partenaire officiel de la Fnac en 2020.

### Yuntrack, c'est quoi ?

Le site de suivi officiel de YunExpress, sa « Track & Trace Platform » : le lien « Tracking » et le champ de suivi de yunexpress.com y mènent. En anglais et sans compte, il prend jusqu'à 100 numéros YT par recherche.

### Combien de temps met un colis YunExpress pour arriver en France ?

Selon les estimations de YunExpress pour les vendeurs, 3 à 5 jours ouvrés avec Standard Express et 6 à 10 avec sa ligne économique. Vers la Belgique, 3 à 6 jours en standard et 6 à 10 en économique ; vers la Suisse, 6 à 9 en économique. Tu ne sais en général pas quelle ligne le vendeur a choisie, et un contrôle douanier ajoute du temps.

### Pourquoi un colis Amazon arrive-t-il avec YunExpress ?

Parce que certains vendeurs Amazon expédient eux-mêmes depuis la Chine avec YunExpress : selon lui, son service FBM Ship+, pensé pour eux, est ouvert notamment sur le site Amazon français. En cas de problème, adresse-toi au vendeur ou à Amazon.

### Peut-on retirer un colis YunExpress en point relais ?

Oui, si le vendeur a choisi une ligne de retrait de YunExpress : le suivi affiche alors l'adresse du point relais ou de la consigne. Sinon, après une livraison manquée, demande au transporteur local nommé dans le suivi où ton colis t'attend.

:::sources
- [YunExpress : Track & Trace Platform, Yuntrack](https://www.yuntrack.com/) – site de suivi de YunExpress, numéro YT, anglais uniquement
- [YunExpress : code de l'accueil Yuntrack](https://www.yuntrack.com/static/js/1.3908b8f5c10e76577a6e.js) – bouton Track, séparateurs, 100 numéros par recherche
- [YunExpress : code de la page de résultats Yuntrack](https://www.yuntrack.com/static/js/6.9b7c1a51737b2bcaa67c.js) – onglets, Last Mile Tracking, Last Event, Not Found, 100 numéros sans compte
- [YunExpress : code de la page Yuntrack](https://www.yuntrack.com/static/js/0.38a2ccebfb56bd50819e.js) – Alert, Returned, barre de progression, Additional Notes, preuve de livraison, Feedback
- [YunExpress : the Best Logistics Service Provider](https://www.yunexpress.com/) – société, e-mail, horaires, langues (EN, CN), lien Tracking et champ de suivi vers yuntrack
- [YunExpress : About Us](https://www.yunexpress.com/about-us) – 2014, plus de 220 pays et régions, lignes France 2016 et Suisse 2020, Amazon, Fnac
- [YunExpress : Zongteng Group welcomes its Boeing 777F freighter to Paris Charles de Gaulle International Airport](https://www.yunexpress.com/newsroom/detail/5) – ligne Shenzhen–Paris, YunExpress France, partenaires locaux
- [YunExpress : Zongteng Group Announces Its Acquisition of the First Boeing 777F](https://www.yunexpress.com/newsroom/detail/6) – Zongteng, groupe de YunExpress
- [YunExpress : Privacy Policy](https://www.yunexpress.com/privacy-policy) – le vendeur, client sous contrat
- [YunExpress : Partners](https://www.yunexpress.com/yun-ecosystem/partners) – marketplaces
- [YunExpress : 独家！FBM Ship+服务上线](https://www.yunexpress.com/newsroom/detail/4) – FBM Ship+ sur Amazon France
- [YunExpress : Standard Express](https://www.yunexpress.com/our-solutions/b2c/33) – délais vers la France
- [YunExpress : 全球云选专线挂号（带电/普货）](https://www.yunexpress.com/our-solutions/b2c/1) – codes postaux en France
- [YunExpress : 全球专线挂号（标快带电）](https://www.yunexpress.com/our-solutions/b2c/5) – Belgique, îles
- [YunExpress : 全球专线挂号（特惠带电）](https://www.yunexpress.com/our-solutions/b2c/7) – ligne économique, Suisse, îles
- [YunExpress : 最高省21%！云途自提服务上线](https://www.yunexpress.com/newsroom/detail/3) – lignes de retrait en Europe
- [YunExpress : EU Customs Reform 2026 & YunExpress Service Updates](https://www.yunexpress.com/newsroom/detail/13) – droit provisoire de l'UE
- [YunExpress : 為什麼你的包裹會被海關抽查？](https://hk.yunexpress.com/blog/2179/) – contrôles douaniers
- [YunExpress : 云途物流YunExpress](https://www.yunexpress.cn/) – numéro en Chine
- [YunExpress : Contact Us](https://sg.yunexpress.com/contact/) – contacts régionaux
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formes de numéros reconnues par Peek, ce que Peek lit et affiche, Not Found sans scan, transporteur local tiré des Additional Notes, lignes bpost, DHL Parcel et DPD
:::
