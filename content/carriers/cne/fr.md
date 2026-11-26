---
title: Suivi CNE Express : numéros 3A5V, statuts et livraison
description: Suis un colis CNE Express (递一物流) depuis la Chine continentale : format 3A5V, sens des statuts en anglais, livreur local, douane UE et suivi dans Peek.
slug: suivi-cne-express
published: 2026-11-21
updated: 2026-11-21
---

CNE Express (`递一物流`, `CNE EXPRESS CO LTD` / `递一国际物流有限公司`) est un opérateur logistique transfrontalier fondé en 2003 à Shanghai, en Chine continentale, qui couvre plus de 100 pays et régions. Il expédie les commandes de vendeurs sur Amazon, eBay, Walmart, Wish, DHgate, TikTok, Temu, Shopify ou SHEIN depuis la Chine continentale et les confie à un partenaire local à l'arrivée. Consulte ton numéro sur [le traceur en anglais de CNE](https://www.cne.com/en/track) ou colle-le dans le champ ci-dessus ; dans le catalogue de Peek, chercher `CNE` ou `递一物流` trouve aussi CNE Express.

## Numéro de suivi CNE Express : à quoi il ressemble

CNE ne publie pas de règle de numérotation. Voici le format que Peek reconnaît d'emblée comme appartenant à CNE Express, avec des chiffres inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `3A5V482910573` | `3A5V` suivi de 9 chiffres : un numéro CNE Express. |
| `3A5V48291057` | Seulement 8 chiffres : pas le format `3A5V` de CNE. Peek ne désigne pas CNE et, comme il ne correspond au format d'aucun transporteur, le cherche auprès des services de suivi une fois ajouté. Vérifie s'il manque un chiffre. |
| `LX482910576CN` | Deux lettres, 9 chiffres et `CN` : un envoi postal [China Post](carrier:china-post) commercialisé aussi par CNE (*International E-packet*, *China Post Packet*). |

Minuscules, espaces et tirets sont ignorés dans Peek. Le traceur de CNE accepte plusieurs numéros à la fois, séparés par des espaces ou des virgules.

**Où le trouver.** Dans l'e-mail d'expédition du vendeur ou sur la page de ta commande : vois [comment trouver son numéro de suivi](guide:find-tracking-number), [commandes Temu](guide:temu-orders) et [commandes SHEIN](guide:shein-orders). Sous `Shipment Information`, le traceur de CNE peut aussi afficher la référence vendeur (`Order number`) et le numéro du transporteur final (`Last mile number`) avec son site et son téléphone.

## Statuts de suivi CNE Express expliqués

Le traceur de CNE affiche un statut récapitulatif au-dessus de l'historique :

| Statut CNE | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Shipping` | Colis en cours d'acheminement. | le statut de la dernière ligne |
| `Customs inspection` | Retenu par la douane (`扣关` sur la page chinoise de CNE). | « À la douane » |
| `Delivered` | Livré. | « Livré » |
| `Invalid address`, `Lost`, `Exception`, `Destroyed` | Adresse invalide, colis perdu ou détruit, ou autre anomalie. | « Problème signalé » |
| `Returned` | Renvoyé au vendeur (`退件` en chinois). | « Retourné à l’expéditeur » |
| `Not delivered` | La page chinoise indique `未发送` (« non expédié ») : le colis n'est pas encore parti. | le statut de la dernière ligne |

`Time out` et `Unknown` peuvent aussi apparaître : Peek s'appuie alors sur les lignes détaillées. Chaque ligne indique une heure, un libellé et un lieu :

| CNE indique | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Parcel Infomation Received` | Le vendeur a transmis les données du colis (orthographe de CNE) ; CNE ne l'a pas encore. | « Étiquette créée » |
| `Picked Up by Courier` | CNE a collecté le colis chez le vendeur en Chine continentale. | « Pris en charge » |
| `Arrived at CNE Facility`, `Inbound scan`, `Shipment Processed`, `Shipment Loaded` | Arrivée, tri et chargement dans un centre CNE. | « En transit » |
| `Departed CNE Facility`, `Departure Scan`, `Depart From Local Facility` | Départ d'un centre logistique. | « En transit » |
| `Arrived Export Airport`, `Released From Export Customs` | À l'aéroport de départ en Chine continentale, puis sortie de la douane d'exportation. | « En transit » |
| `Shipment Departed From Original Country` | L'avion a quitté la Chine continentale. | « En transit » |
| `Arrived At Transit Airport`, `Shipment Depart From Transit Airport` | Passage par un aéroport de transit. | « En transit » |
| `Arrived at Import Airport`, `Arrived at destination` | Arrivé dans le pays de destination : le tri et la douane d'importation restent à venir (ce n'est pas ton bureau de distribution local). | « En transit » |
| `The shipment is picked up by customs broker` | Pris en charge par un déclarant pour le dédouanement à l'importation. | « À la douane » |
| `Released From Import Customs`, `Pickup from airport` | Dédouané à l'importation puis enlevé à l'aéroport. | « En transit » |
| `Handled by local carrier` | Remis par CNE au réseau de livraison local ; l'acceptation propre à ce transporteur suit. | « En transit » |
| `Arrived At The Delivery Company`, `Your shipment has arrived at sorting center`, `Your shipment is in process at sorting center`, `Parcel Unloaded From Container` | Pris en charge par le transporteur final ou son centre de tri. | « En transit » |
| `Your shipment has been processed at sorting center and ready for shipping`, `Your shipment has departed from the transit origin`, `Your shipment has arrived at the transit destination` | Trié dans le pays d'arrivée et acheminé jusqu'à l'agence locale avant la tournée. | « En transit » |
| `The shipment has been processed in the parcel center of origin.` | Tri DHL en Allemagne relayé par CNE après la remise. | « En transit » |
| `Pendiente de recepción en CTT Express`, `Enviado`, `En reparto`, `Entregado` | Étapes espagnoles de [CTT Express](carrier:ctt-express) relayées avec explication après une virgule : préannonce (« Étiquette créée », sans recul si le colis bouge déjà), expédition (« En transit »), tournée (« En livraison ») et remise (« Livré »). | selon l'étape |
| `On Board with Courier`, `Your shipment is out for delivery` | Chargé dans la camionnette du livreur pour la tournée du jour. | « En livraison » |
| `Your shipment delivery attempt failed [Recipient not at home]` | Personne au domicile lors du passage ; une nouvelle présentation suit. | « Tentative de livraison », ou « Problème signalé » tant que le résumé indique `Exception` |
| `Sorting failed []` | Erreur de tri (crochets vides affichés tels quels par CNE). | « Problème signalé » |
| `It was returned to customer's address by CNE` | Renvoyé par CNE à l'adresse du vendeur expéditeur. | « Retourné à l’expéditeur » |

Après la remise au partenaire local, ses lignes apparaissent souvent sous la forme « libellé；explication » (ou avec virgule pour CTT Express) : Peek ne lit que le libellé qui précède, maintient en « En transit » une préannonce partenaire codée `Shipping` et s'appuie sur le code CNE pour tout libellé inconnu. Un statut récapitulatif final ou bloquant (`Delivered`, `Returned`, `Customs inspection`, `Invalid address`, `Lost`, `Exception`, `Destroyed`) prime sur la dernière ligne.

## Qui livre les colis CNE Express en France, en Belgique, au Luxembourg et en Suisse

CNE exploite ses propres lignes vers l'Europe, l'Amérique, l'Australie ou le Brésil et confie le dernier kilomètre à des partenaires locaux : sa page partenaires cite notamment [La Poste](carrier:la-poste), [DHL](carrier:dhl), [GLS](carrier:gls-de), [DPD](carrier:dpd-de), [POST Luxembourg](carrier:post-luxembourg), [Royal Mail](carrier:royal-mail), [Evri](carrier:evri), [Correos](carrier:correos-spain), [Poste Italiane](carrier:poste-italiane), Austrian Post et [USPS](carrier:usps).

Peek reconnaît directement trois codes de sous-traitants de CNE lorsqu'ils sont accompagnés de leur numéro de transfert : [DHL](carrier:dhl) (`DE DHL`), [Royal Mail](carrier:royal-mail) et [USPS](carrier:usps). La fiche affiche alors « Livraison par … » (ou « Annoncé à …, pas encore reçu » tant que ce transporteur n'a aucun scan propre). Pour un autre code de sous-traitant accompagné d'un numéro de transfert (comme `US SPX`, non rattaché à un nom unique), Peek interroge la poste du pays de destination si le numéro est au format postal international ou l'unique transporteur désigné par la forme de ce numéro ; sans sous-traitant nommé, le second numéro peut être une référence marchand et n'est pas suivi. Les avis de passage et points de retrait dépendent du livreur final ([livraison manquée](guide:missed-delivery)).

**Douane dans l'UE (France, Belgique, Luxembourg) et en Suisse.** Dans l'**Union européenne**, les biens achetés en ligne hors UE dans des colis d'une valeur intrinsèque maximale de 150 € supportent un droit de douane de 3 € par article depuis le 1er juillet 2026, auquel s'ajoutent depuis le 1er novembre 2026 des frais de traitement de l'Union de 2 € par article quelle que soit la valeur (un article regroupe les marchandises d'un même colis partageant classement tarifaire, désignation et, si requise, origine ; au-delà de 150 €, tarif normal). Dans un avis publié le 22 octobre 2026, CNE a informé ses vendeurs que depuis le 26 octobre 2026 à `00:00` (heure de Pékin), sur ses lignes vers les 27 États de l'UE, il précollecte auprès d'eux ces 2 € par colis et par code SH en sus des 3 € déjà précollectés depuis juillet 2026, et dédouane sur la base de leur déclaration. Cet avis ne mentionne ni la TVA d'importation (due sauf si elle a déjà été réglée à l'achat via le guichet IOSS : en **France métropolitaine** « dès le premier euro » selon la douane française, au-delà de « 22 euros » en **Guadeloupe**, **Martinique** et **La Réunion**, et provisoirement non applicable en **Guyane** et à **Mayotte** hors octroi de mer), ni d'éventuels frais du livreur local. En **Suisse**, selon l'OFDF, la TVA est de 8,1 % (2,6 % taux réduit), mais « les droits de douane inférieurs à 5 francs ne sont pas perçus » et « les montants d'impôt inférieurs à 5 francs ne sont pas perçus ». Vois [colis bloqué en douane](guide:customs). CNE publie aussi ses avis de retard par destination sur sa page chinoise `新闻公告`.

## Colis CNE Express bloqué, perdu ou retourné

Les clients de CNE sont les vendeurs qui réservent l'envoi : en cas de retard, perte, avarie ou retour, contacte d'abord la boutique ou la marketplace.

**« En attente de CNE Express ».** Tant que CNE renvoie que la commande n'existe pas (`订单不存在`), Peek affiche « En attente de CNE Express » ; si CNE ne connaît toujours pas le numéro après deux jours et qu'aucun service de suivi ne répond, Peek affiche : « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » Avec `Not delivered` (`未发送`) ou `Parcel Infomation Received`, vérifie auprès du vendeur si l'envoi stagne plusieurs jours.

**Plus de mise à jour depuis quatre jours.** Au bout de quatre jours sans nouvelle ligne en transit, Peek signale le jour de la dernière ligne (« Aucune mise à jour depuis lun 12 oct »), rappelle les silences fréquents sur un long vol, en douane ou le week-end (après trois semaines sans rien de nouveau, il conseille plutôt de contacter le vendeur) et ajoute le bouton « Demande de l’aide à CNE Express » vers la page de contact de CNE. Vois [pourquoi le suivi ne bouge plus](guide:tracking-not-updating) et [suivre un colis venu de Chine](guide:tracking-from-china).

**En douane, en incident, retourné ou marqué livré sans colis.** Pour `Customs inspection` (`扣关`), `The shipment is picked up by customs broker`, `Lost`, `Destroyed`, `Invalid address` ou `Returned`, préviens le vendeur ([colis perdu](guide:lost-parcel), [colis retourné à l'expéditeur](guide:returned-to-sender)). Sur le traceur de CNE, une ligne avec `View POD` donne accès à la preuve de livraison en saisissant le nom ou le code postal du destinataire ([colis indiqué livré mais non reçu](guide:delivered-not-received)).

## Service client et contact CNE Express

Sur la page [Contact us de CNE](https://www.cne.com/en/about/contact) :

- **Ligne nationale** : `400-021-5600` (*National Service Hotline*, numéro de service en Chine continentale).
- **Siège de Shanghai** : `021-5109 6677`, `15/F, Block G, Lane 1777, Hualong Road, QingPu District, Shanghai` (Chine continentale).
- **Horaires** : `08:30-18:00 Mon-Sun` sans fuseau précisé (les centres de tri de Jiaxing et Dongguan en Chine continentale tournent `7 x 24 Hours`).
- **Formulaire en ligne** : *Consulting form* sur la même page en choisissant `Complaint`, avec téléphone, e-mail, nom, numéro de suivi et message (aucun e-mail de support client n'est publié).

**Attention aux SMS frauduleux.** Le domaine officiel de CNE Express est `www.cne.com` (`cne.com`). Avant de suivre un lien reçu par SMS ou e-mail, lis [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts).

## Suivre un colis CNE Express avec Peek

[Peek](/) détecte immédiatement CNE Express sur un numéro `3A5V` (« Transporteur détecté »), et tout lien `cne.com` contenant `?no=` classe le colis sous CNE Express (en gardant le premier numéro s'il y en a plusieurs). Pour un autre numéro CNE de 6 à 40 caractères alphanumériques, si Peek affiche « Vérification des services de suivi… » ou ne nomme pas CNE, touche « Changer » et choisis CNE Express sous « Tous les transporteurs » (ou « Changer de transporteur » ensuite) ; hors format alphanumérique de 6 à 40 caractères, Peek signale qu'il « n’émet pas de numéros comme celui-ci ».

Peek lit le statut, chaque scan avec son lieu, le pays de destination et le transporteur final assorti de son numéro de transfert. Il lit `Arrived at destination` et `Handled by local carrier` à l'heure de Pékin (`UTC+8`), les statuts espagnols relayés de [CTT Express](carrier:ctt-express) en `UTC+02:00` (et en `UTC+01:00` les étapes finales de [CTT](carrier:ctt) vers le Portugal), lit les autres lignes sur l'horloge de leur lieu ou de l'étape où se trouvait le colis, et affiche chaque heure dans le fuseau de ton appareil (« Heures dans ton fuseau horaire : … ») ; Peek écarte le numéro de commande marchand, l'horodatage récapitulatif de livraison sans fuseau et la preuve `View POD` (ni date prévue ni poids ne sont fournis par CNE). « Ouvrir le site CNE Express » ouvre ton suivi sur `cne.com`. Dès que le transporteur final interrogé présente un scan au moins aussi récent que le dernier de CNE, Peek bascule dessus en gardant l'historique de CNE.

Tant que tu gardes le colis ouvert ou, le jour de sa livraison, avec les notifications activées, Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes dès 6 h locales le jour où le colis passe en « En livraison » tant que le traceur de CNE répond (après relais vers le transporteur final, Peek suit le rythme de celui-ci ; via les services de suivi seuls : toutes les heures, toutes les 3 heures après un jour calme et jusqu'à toutes les 15 minutes le jour de la livraison si le colis est ouvert ou que les notifications sont actives) ; sinon, et après deux jours sans nouveauté (12 heures sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Alertes : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni CNE ni le vendeur pour toi.

## Questions fréquentes sur le suivi CNE Express

### CNE Express est-il fiable ?

Oui. `CNE EXPRESS CO LTD` (`递一国际物流有限公司`) a été fondé en 2003 à Shanghai, en Chine continentale, dispose d'un centre opérationnel à Shenzhen et de centres de tri à Jiaxing et Dongguan capables de traiter plus de 2 millions d'envois par jour depuis 2020 selon son site, et expédie pour des vendeurs sur Amazon, eBay, Walmart, TikTok, Temu et SHEIN.

### Qui livre les colis CNE Express ?

Une poste ou un transporteur local choisi par CNE (comme La Poste, DHL, GLS, DPD, POST Luxembourg, Royal Mail ou Evri selon sa page partenaires), dont le traceur de CNE peut afficher le `Last mile number`.

### Que signifie `Arrived at destination` chez CNE Express ?

Le colis est arrivé dans le pays de destination, avant le tri local et la douane d'importation : il n'est pas encore à ton bureau de livraison. CNE horodate cette ligne à l'heure de Pékin (`UTC+8`, soit 7 heures d'avance sur Paris ou Bruxelles en hiver), alors que des lignes locales voisines gardent l'heure locale sans fuseau, ce qui peut donner l'impression d'un décalage.

### Quels sont les délais de livraison de CNE Express ?

CNE indique à ses vendeurs des délais de référence variables selon la ligne : `3-8 business days` pour *CNE Priority Express* (vers les États-Unis, le Royaume-Uni, la France et l'Allemagne) et `5-20 business days` pour *CNE Standard Express*. Fie-toi au délai annoncé par ta boutique.

### Comment contacter CNE Express ?

Appelle le `400-021-5600` (ligne de Chine continentale) ou le `021-5109 6677` à Shanghai, ou remplis le *Consulting form* sur `cne.com/en/about/contact` en choisissant `Complaint`. Pour ta commande, contacte d'abord le vendeur.

:::sources
- [CNE : Track your parcel](https://www.cne.com/en/track) – traceur anglais, libellés de statut, Shipment Information et View POD
- [CNE : 运单追踪 (suivi en chinois)](https://www.cne.com/track) – équivalents chinois 未发送 et 扣关
- [CNE : Home](https://www.cne.com/en) – création en 2003 à Shanghai, destinations, plateformes partenaires et ligne 400
- [CNE : 首页 (accueil en chinois)](https://www.cne.com/) – 递一物流 et 递一国际物流有限公司
- [CNE : About us](https://www.cne.com/en/about/intro) – centres de tri de Dongguan et Jiaxing, capacité de plus de 2 millions d'envois/jour depuis 2020
- [CNE : Partner](https://www.cne.com/en/about/partner) – partenaires logistiques mondiaux
- [CNE : Contact us](https://www.cne.com/en/about/contact) – ligne 400, bureau de Shanghai, horaires 08:30-18:00 et Consulting form (Complaint)
- [CNE : Postal Parcel](https://www.cne.com/en/solutions/economyParcel) – offres China Post vendues par CNE
- [CNE : CNE Priority Express](https://www.cne.com/en/solutions/product/36024) – délai de référence 3-8 business days et routes US/GB/FR/DE
- [CNE : CNE Standard Express](https://www.cne.com/en/solutions/product/36025) – délai de référence 5-20 business days
- [CNE : 新闻公告 (actualités et avis)](https://www.cne.com/news/notice) – avis de retard par pays
- [CNE : 关于欧盟国家2026年11月税改通知 (avis réforme UE)](https://www.cne.com/news/article/rDe4ANa6KYOW) – précollecte des 2 € auprès des vendeurs dès le 26 octobre 2026 en plus des 3 € par code SH et dédouanement sur déclaration vendeur
- [Commission européenne : Réforme douanière de l'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € depuis le 1er juillet 2026 et frais de 2 € depuis le 1er novembre 2026
- [Commission européenne : Orientations sur le droit de 3 € et les frais de l'Union (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € quelle que soit la valeur, définition d'un article et exonération IOSS
- [Douane française : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – TVA en France métropolitaine et dans les DOM
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA suisse 8,1 % / 2,6 % et non-perception des montants inférieurs à 5 francs
- [Universal Parcel Scraper : CNE Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cne) – format 3A5V, liens ?no=, statuts et codes, sous-traitants, horloges Pékin / CTT Express / locales et relais
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – détection China Post et passage au transporteur final
:::
