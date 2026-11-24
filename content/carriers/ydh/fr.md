---
title: Suivi YDH : codes YDH...YQ et AZ, relais et contacts
description: Suis un colis transfrontalier YDH Express depuis la Chine : formats YDH...YQ et AZ, codes DSD et CARR, transporteur local, douane et aide.
slug: suivi-ydh
published: 2026-11-24
updated: 2026-11-24
---

YDH (*YDH Express* / *YDH Cross Border*, en chinois *义达跨境*) est un prestataire chinois de logistique e-commerce transfrontalière fondé en 2005 et basé à Shanghai. Desservant plus de 20 pôles chinois d’e-commerce transfrontalier — dont Shenzhen, Canton, Hangzhou, Ningbo et Hong Kong — avec des bureaux au Japon, aux États-Unis, au Royaume-Uni et en Belgique, YDH achemine des petits colis et du fret commercial vers plus de 200 pays et régions avant de les confier à des partenaires locaux. Tu peux consulter un envoi sur [le portail de suivi YDH](https://track.ydhex.com/track_query.aspx), ou coller ton numéro dans le champ ci-dessus.

## Numéros de suivi YDH : formats YQ et AZ

Cherche le numéro de bordereau dans le détail de ta commande ou dans l’e-mail d’expédition. Les numéros de suivi YDH comptent 15 caractères, commençant par `YDH`, suivis de 10 chiffres et terminés par `YQ` ou `AZ` :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `YDH4829173640YQ` | Numéro de colis transfrontalier YDH : `YDH` + 10 chiffres + `YQ`. |
| `YDH4829173640AZ` | Numéro de colis transfrontalier YDH : `YDH` + 10 chiffres + `AZ`. |

Une fois ton colis dédouané dans le pays de destination, YDH le confie à un transporteur local : [USPS](carrier:usps) ou [GOFO](carrier:gofo) aux États-Unis et [Poste Italiane](carrier:poste-italiane) en Italie figurent parmi ceux dont YDH relaie les scans sous ton numéro `YDH`. YDH peut aussi communiquer le propre numéro de suivi du transporteur local. Pour d’autres formats transfrontaliers, consulte [les formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts et codes YDH

YDH associe de courts codes d’étape à ses propres scans d’entrepôt, d’export et de vol (en chinois ou en anglais) et relaie aussi les scans du partenaire local dans le libellé d’origine de ce partenaire.

| Code / libellé YDH | Ce que ça signifie pour toi | Ce qu’affiche Peek |
| --- | --- | --- |
| `IR` (`Shipment information received`, `Label Created`) | Commande électronique reçue du vendeur avant que YDH ne scanne le colis physique. | « Étiquette créée » |
| `AF` (`Shipment arrived at facility`), `GNZY` (`快件到达作业中心`) | Colis arrivé dans l’entrepôt ou le centre de tri YDH en Chine. | « En transit » |
| `HBYP`, `DF`, `DA` (`Shipment departed from airport` / `航班起飞`), `TDDY` (`Flight delay`), `AA` (`Shipment arrived at airport` / `航班到达`) | Mis en attente de vol, parti de Chine (ou retardé sur le vol) et arrivé à l’aéroport de destination. | « En transit » |
| `RC` (`Released from customs`), `CARR` (`handed over to the final carrier`), `DSD` (`D-SCAN DELAY`), `PU` | Dédouané à destination, remis au transporteur local, en attente du premier scan de ce partenaire (`D-SCAN DELAY`) ou collecté par lui. | « En transit » |
| `OD` (`Out for Delivery`) | Chargé par le transporteur local pour la livraison finale. | « En livraison » |
| `DD` | Incident de livraison ou tentative échouée signalée par le partenaire local. | « Tentative de livraison » / « Problème signalé » |
| `CC` | Livré par le partenaire de destination (avec le nom du signataire lorsqu’il est transmis). | « Livré » |

**Ce que signifie `D-SCAN DELAY` (`DSD`).** Lorsque YDH remet un lot consolidé à une poste ou un transporteur local (`CARR`), plusieurs jours peuvent passer avant que ce partenaire ne scanne chaque colis individuellement dans son propre réseau. `D-SCAN DELAY` signale ce délai de prise en charge — le colis reste en chemin et n’est pas perdu.

## Comment fonctionne la livraison transfrontalière YDH

Sur [ydhex.com](https://www.ydhex.com/), YDH cite plus de 50 compagnies aériennes et maritimes réservées, ainsi qu’un dédouanement dans 12 pays. Sa [page de contact](https://www.ydhex.com/product/59/) recense des bureaux au Japon (Tokyo, Narita, Osaka, Kansai et Nagoya), aux États-Unis (La Puente, Californie), au Royaume-Uni (Hounslow) et en Belgique (Grâce-Hollogne, près de Liège).

**Livraison du dernier kilomètre.** YDH prend en charge la collecte export, le transport aérien et le dédouanement, puis transfère le colis (`CARR`) à une poste ou un transporteur local dans ton pays pour la livraison à domicile, le dépôt en point relais ou les retours. Ce transporteur local fixe le nombre de tentatives, les lieux sûrs et la durée de garde.

**Douane et frais d’importation.** Les commandes transfrontalières expédiées par YDH sont soumises à la TVA d’importation, aux droits de douane et aux frais locaux de ton pays, sauf paiement préalable chez le vendeur ou sur la place de marché. Consulte [colis retenu en douane](guide:customs).

## Colis YDH bloqué, silencieux ou non livré

Comme le contrat de transport YDH est conclu avec le vendeur ou la place de marché plutôt qu’avec l’acheteur, contacte toujours le vendeur ou ouvre un suivi sur la plateforme en premier pour tout retard, erreur d’adresse ou remboursement.

- **Pauses pendant le vol ou le transfert :** des silences de plusieurs jours sont courants pendant l’attente de capacité aérienne (`HBYP`), le dédouanement (`RC`) ou l’attente du premier scan local (`DSD` / `D-SCAN DELAY`). Consulte [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **Utilise le numéro local après la remise :** dès que YDH affiche le propre numéro du transporteur local, le site de ce dernier peut proposer des options de livraison absentes chez YDH.
- **Livré, abîmé ou manquant :** si le suivi indique `CC` / livré sans que le colis soit là, vérifie auprès de ton foyer, tes voisins, la réception et le transporteur local, puis avertis aussitôt le vendeur. Consulte [colis livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contacts YDH

Coordonnées officielles publiées sur [la page Contact de YDH](https://www.ydhex.com/product/59/) :

- **Lignes service client en Chine :** `800-988-1378` / `400-888-1378`.
- **Service client Shanghai :** `(+86) 21-52277600` / `(+86) 21-52277688` (siège : 28/F, AFC Grand Hongqiao International, No. 999 Li'an Road, Minhang District, Shanghai 201100, Chine).
- **Bureau de Hong Kong :** `+852 2690-1686`, `hk@ydhexpress.com`.
- **Bureau du Japon (Tokyo) :** `+81 3-5644-5733`.
- **Bureau de Belgique (Liège) :** `+32 470 588 200`.

> Méfie-toi des SMS ou e-mails non sollicités demandant un petit paiement par carte pour « dédouaner » ou « mettre à jour ton adresse » sur un colis venu de Chine. Vérifie toute demande de taxe légitime sur ta page de commande ou le site officiel de la poste de ton pays. Consulte [les SMS d’arnaque à la livraison](guide:delivery-scam-texts).

## Suivre un colis YDH avec Peek

[Peek](/) reconnaît automatiquement `YDH` + 10 chiffres + `YQ` ou `AZ` (`YDH4829173640YQ` et `YDH4829173640AZ`, chiffres inventés) comme YDH avec un haut niveau de confiance, ainsi que les liens collés `track.ydhex.com/track_query.aspx?track_number=`. Si tu dois choisir YDH manuellement, appuie sur « Changer » à l’ajout ou « Changer de transporteur » sur la fiche du colis (rechercher « YDH Express », « YDH Cross Border » ou « 义达跨境 » le trouve aussi).

Peek lit directement le suivi YDH et affiche l’étape (« Étiquette créée », « En transit », « En livraison », « Tentative de livraison », « Problème signalé », « Livré »), le libellé du scan — celui de YDH ou du transporteur local — avec son lieu, ainsi que le pays de destination. Les codes YDH comme `CARR` et `DSD` déterminent l’étape ; Peek ne les affiche pas. YDH date ses scans en Chine à l’heure de Shanghai et ceux du transporteur local à l’heure locale de leur lieu ; Peek les affiche à l’heure de ton appareil. Lorsque YDH communique le numéro du transporteur local et que Peek le reconnaît, Peek vérifie qu’il a le colis : la fiche affiche alors « Livraison par » suivi du nom du transporteur, et liste les deux numéros. Le nom du signataire n’apparaît dans aucune application Peek, et Peek écarte la référence client et l’étiquette partenaire de YDH. Le suivi YDH ne donne ni date estimée, ni poids, ni nom de service, ni point relais, ni photo de preuve de livraison.

Tant que le colis est ouvert dans Peek, ou le jour de la livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où YDH affiche `OD`, toutes les 2 minutes à partir de 6 h à l’heure du dernier scan YDH, tant que le suivi YDH répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec seulement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta façon de le suivre, et toutes les 6 heures après sept jours sur « Étiquette créée ». Lorsque le suivi YDH ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le dernier scan date de quatre jours et que le colis reste à « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec la date de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La fiche du colis affiche alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger YDH. », avec un lien « Demande de l’aide à YDH » vers la page de contact de YDH. Après trois semaines sans nouveau scan, elle indique « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger YDH. »

Tu peux choisir les réglages d’alerte « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek assure uniquement le suivi et ne peut ni contacter YDH, ni dédouaner le colis, ni le rediriger auprès du partenaire local.

## Questions sur le suivi YDH

### Qu’est-ce que YDH Express ?

YDH (*义达跨境*) est un prestataire logistique e-commerce basé à Shanghai fondé en 2005, qui expédie des petits colis depuis la Chine vers plus de 200 pays et les confie à des postes ou transporteurs locaux pour la livraison finale.

### Que signifient les numéros `YDH...YQ` et `YDH...AZ` ?

Les deux sont des bordereaux YDH Express de 15 caractères composés de `YDH`, 10 chiffres puis `YQ` ou `AZ` (par exemple `YDH4829173640YQ` ou `YDH4829173640AZ`, chiffres inventés).

### Que signifie `D-SCAN DELAY` (`DSD`) dans le suivi YDH ?

`D-SCAN DELAY` signifie que YDH a transféré le colis au transporteur local du pays de destination, mais que ce partenaire n’a pas encore enregistré son premier scan de prise en charge. Le colis reste en transit en attendant le scan local.

### Comment contacter le service client YDH ?

Contacte d’abord le vendeur ou la place de marché pour un remboursement ou un souci de livraison. Pour joindre le transporteur, YDH indique ses lignes en Chine (`800-988-1378` / `400-888-1378`), Shanghai (`+86 21-52277600` / `7688`), Hong Kong (`+852 2690-1686`, `hk@ydhexpress.com`), Japon (`+81 3-5644-5733`) et Belgique (`+32 470 588 200`) sur [ydhex.com/product/59/](https://www.ydhex.com/product/59/).

:::sources
- [YDH : Site officiel](https://www.ydhex.com/) – fondé en 2005, 20+ hubs en Chine, 200+ pays/régions, 50+ compagnies aériennes et maritimes, dédouanement dans 12 pays
- [YDH : Contact Us](https://www.ydhex.com/product/59/) – siège à Shanghai, lignes Chine (`800-988-1378`, `400-888-1378`, `+86 21-52277600`/`7688`), bureaux à Hong Kong, Japon, États-Unis, Royaume-Uni et Belgique (URL d’aide liée par Peek)
- [YDH : Portail de suivi](https://track.ydhex.com/track_query.aspx) – consultation publique des colis YDH
- [Universal Parcel Scraper : ydh](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ydh) – détection `YDH` + 10 chiffres + `YQ`/`AZ`, règle de lien `track.ydhex.com`, codes (`IR`, `AF`, `DA`, `AA`, `RC`, `CARR`, `DSD`, `PU`, `OD`, `CC`, `DD`), numéro du transporteur local, fuseaux horaires et URL d’aide
:::
