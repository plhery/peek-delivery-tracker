---
title: Suivi SXW Express : numéros SXW, statuts et livraison
description: Suivez un colis SXW Express (Shengxingwang) expédié de Shenzhen : format du numéro SXW, suivi officiel, sens des statuts, transporteur final et douane.
slug: suivi-sxw-express
published: 2026-11-21
updated: 2026-11-21
---

SXW Express est le nom utilisé par Peek pour `Shenzhen Shengxingwang Supply Chain Management Co., Ltd.` (`深圳胜兴旺供应链管理有限公司`), un commissionnaire logistique transfrontalier basé à **Shenzhen, en Chine**, qui expédie des marchandises à l'international pour les exportateurs et les vendeurs en ligne. Si une boutique chinoise a confié votre commande à SXW Express, cherchez un numéro composé de `SXW` suivi de douze chiffres et collez-le dans le champ ci-dessus. Vous pouvez aussi le vérifier sur le traceur officiel ouvert depuis l'encadré *Express Tracking* de `sxwexpress.cn`, que Peek interroge directement pour vous.

## Numéro de suivi SXW Express : format et exemples

Le site de SXW Express ne publie pas de règle de numérotation. Le format que Peek reconnaît comme propre à SXW Express compte 15 caractères : les lettres `SXW` suivies de douze chiffres. Exemples avec des chiffres inventés :

| Numéro (inventé) | Ce qu'il désigne |
| --- | --- |
| `SXW482910573649` | `SXW` et douze chiffres : format SXW Express. Peek sélectionne aussitôt SXW Express. |
| `SXW48291057364` | Seulement onze chiffres : format incomplet pour SXW Express. Vérifiez s'il manque un chiffre dans le message du vendeur. |
| `GFUS48291057364918` | `GFUS` et quatorze chiffres : numéro [GOFO Express](carrier:gofo), que le dossier SXW peut indiquer pour la livraison finale aux **États-Unis**. |

Les espaces, tirets et minuscules n'ont aucune importance lorsque vous collez le code dans Peek. C'est le vendeur qui réserve l'envoi auprès de SXW et vous transmet ce numéro ; si vous ne le retrouvez pas, consultez [comment trouver son numéro de suivi](guide:find-tracking-number).

**Vérifier sur le site de SXW.** Sur la page d'accueil de `sxwexpress.cn`, l'onglet *Express Tracking* affiche un champ *Enter your tracking number here!* et un bouton *Track* qui ouvre le traceur de SXW dans un nouvel onglet, en chinois ; il existe aussi une page de suivi en anglais (`sxwexpress.cn:8082/en/trackIndex.htm`). Juste à côté du bouton, un logo 17TRACK renvoie vers `17track.net`, un service externe distinct du traceur de SXW.

## Statuts de suivi SXW Express expliqués

Sur le traceur de SXW, le système interne écrit trois étapes en chinois — que Peek traduit en anglais — tandis que les lignes aériennes et partenaires écrivent en anglais. Voici les principaux libellés dans l'ordre du trajet :

| Traceur SXW / affiché dans Peek | Ce que cela signifie pour votre colis | Peek affiche |
| --- | --- | --- |
| `Parcel Data Received`, `Electronic notification of order creation received.` | Le vendeur a transmis les données électroniques ; le colis n'a pas encore été remis. | « Étiquette créée » |
| `货物电子信息已经收到` → `The electronic cargo information has been received` | Même étape écrite par la plateforme de SXW (traduite en anglais dans Peek) : commande enregistrée. | « Étiquette créée » |
| `到达收货点` → `Arrived at the receiving point` | Premier scan physique chez SXW (traduit en anglais dans Peek) : colis reçu au point de collecte. | « Pris en charge » |
| `Parcel picked up`, `Item has been collected` | Colis pris en charge par la ligne d'expédition. | « Pris en charge » |
| `货物离开操作中心` → `Goods leave the operation center` | Le colis a quitté le centre opérationnel de SXW (traduit en anglais dans Peek). | « En transit » |
| `Parcel received at Shenzhen Sorting Centre.`, `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Réception, tri puis départ d'un centre de tri à Shenzhen. | « En transit » |
| `Parcel departure in Transit Facility, Parcel Departure` | Départ d'une plateforme de transit en cours d'acheminement. | « En transit » |
| `Customs declaration completed`, `Clearence processing completed - Export`, `Released From Export Customs` | Déclaration douanière d'exportation terminée en **Chine** avant le vol (avec l'orthographe `Clearence` de la ligne). Le colis n'est pas retenu dans votre pays. | « En transit » |
| `Parcel is leaving Airport, Parcel Leaving Port`, `International flight has departed` | Départ de l'avion depuis l'aéroport chinois. | « En transit » |
| `Parcel arrives at Airport, Parcel Arrival`, `International flight has arrived` | Arrivée du vol à l'aéroport de destination. | « En transit » |
| `Start customs clearence` | Début du dédouanement à l'importation dans le pays d'arrivée (orthographié `clearence`). | « À la douane » |
| `Clearance processing completed - Import`, `Released From Import Customs` | Dédouanement import terminé ; le colis poursuit sa route. | « En transit » |
| `Returned parcel has been recieved by shipper` | Colis retourné et récupéré par l'expéditeur en Chine (orthographié `recieved`). | « Retourné à l’expéditeur » |

Comme deux systèmes alimentent le même dossier, deux lignes proches peuvent coexister (`货物电子信息已经收到` et `Parcel Data Received`, ou `到达收货点` et `Parcel picked up`). Sur certaines liaisons, SXW recopie aussi des scans du transporteur final (par exemple `Arrived at GOFO Regional Hub` aux États-Unis, `Outward MC - Handheld Acceptance` chez Royal Mail au Royaume-Uni, ou `Onboard for delivery` et `Delivered - Left in a safe place` chez Australia Post en Australie). Plus de repères dans [que signifient les statuts de suivi](guide:tracking-statuses).

**Horaires et fuseaux.** Peek lit les étapes enregistrées en Chine à l'heure de Pékin (`Asia/Shanghai`, UTC+8) et les convertit dans le fuseau de votre appareil ; à partir de la première ligne dont le lieu se termine par le code d'un autre pays (comme `…, US`, `…, GB` ou `AU`), Peek conserve l'heure locale du pays de destination. Sur peektracker.com et dans les applications iPhone et Android, le bas de l'historique mentionne « Heures dans ton fuseau horaire : … ».

## Qui livre les colis SXW Express et quelle douane s'applique ?

Le site de SXW Express annonce des livraisons porte-à-porte `DDU/DDP` et du dernier kilomètre local (*local last-mile delivery*) en Europe, en Amérique, en Asie du Sud-Est, au Moyen-Orient et en Afrique. Son catalogue cite des lignes [UPS](carrier:ups) depuis Hong Kong (*Hong Kong UPS Direct Line Double Clearing With Tax Included - F*, *Hong Kong UPS-5000 (H)*), une *ARAMEX Special Line*, un *Middle East and Europe small package* et un *US Express Mail Small Packet - F*, sans publier quel transporteur livre dans chaque pays.

**Relais détectés par Peek.** Pour un envoi vers les **États-Unis**, le dossier SXW peut fournir un numéro [GOFO Express](carrier:gofo) (`GFUS` + 14 chiffres) : Peek affiche alors « Livraison par GOFO Express » (sauf si le canal d'expédition du dossier désigne un autre transporteur tel qu'UPS), indique « Annoncé à GOFO Express, pas encore reçu » tant que GOFO ne l'a pas scanné, puis bascule sur le suivi de GOFO dès qu'un scan GOFO est aussi récent ou plus récent que le dernier scan de SXW. De même, si le canal du dossier nomme [La Poste suisse](carrier:swiss-post) avec un numéro postal suisse valide vers la **Suisse** ou le **Liechtenstein**, Peek passe le relais à La Poste suisse dès son premier scan. Les modalités de passage et de retrait suivent les règles du livreur final : demandez son nom et son numéro local au vendeur, et voyez [livraison manquée](guide:missed-delivery).

**Douane dans l'UE, en France et en Suisse.** Dans l'**Union européenne**, depuis le 1er juillet 2026, les biens achetés en ligne hors UE dans des envois d'une valeur intrinsèque maximale de 150 € supportent un droit de douane de 3 € par article ; depuis le 1er novembre 2026 s'ajoutent des frais de traitement de l'Union de 2 € par article, quelle que soit la valeur (un article regroupe les biens d'un même colis partageant classement tarifaire, désignation et, si requise, origine ; au-delà de 150 €, tarif douanier normal). Selon la douane française, en **France métropolitaine**, la TVA s'applique « dès le premier euro » ; en **Guadeloupe**, **Martinique** et **La Réunion**, elle s'applique si la valeur totale « dépasse 22 euros », et elle « n'est provisoirement pas applicable dans les départements de la Guyane et de Mayotte » (hors octroi de mer et droits au-delà de 150 €). En **Suisse**, selon l'OFDF, la TVA est de 8,1 % (2,6 % au taux réduit), mais « les droits de douane inférieurs à 5 francs ne sont pas perçus » et « les montants d'impôt inférieurs à 5 francs ne sont pas perçus », hors frais de dédouanement éventuels. Vérifiez auprès du vendeur si l'envoi est parti en `DDP` (frais prépayés) ou `DDU`, et consultez [colis bloqué en douane](guide:customs).

## Suivi SXW Express bloqué ou colis en retard

**Uniquement `Parcel Data Received` ou `The electronic cargo information has been received`.** Le vendeur a enregistré l'expédition, mais aucun colis n'a encore été scanné. Si rien ne suit après quelques jours, demandez au vendeur s'il l'a réellement remis.

**« En attente de SXW Express ».** Tant que SXW répertorie la commande sans scan réel (ou uniquement avec des étapes écrites à l'avance) et que les services de suivi n'ont rien non plus, Peek conserve « En attente de SXW Express » : dans l'application iPhone ou Android ou sur un compte, Peek interroge SXW toutes les 6 heures après les premières heures ; sans compte, pendant les 6 premières heures, puis à l'ouverture du lien (au plus toutes les 6 heures, puis une fois par jour après deux jours) et chaque jour jusqu'à 30 jours si les notifications sont activées. En revanche, si le traceur de SXW répond qu'il ne connaît pas du tout ce numéro et qu'aucun service de suivi ne le trouve, Peek affiche après deux jours : « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. »

**`Customs declaration completed`.** Il s'agit de la déclaration d'exportation en Chine avant l'avion (« En transit »), et non d'un blocage douanier à l'arrivée.

**Plus de mise à jour, marqué livré sans colis, perte ou retour.** Si Peek affiche un numéro GOFO Express ou Poste suisse, consultez ce suivi ; sinon fiez-vous au délai annoncé par la boutique et contactez le vendeur s'il est dépassé. Voyez [pourquoi le suivi ne bouge plus](guide:tracking-not-updating), [suivre un colis venu de Chine](guide:tracking-from-china), [colis indiqué livré mais non reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service client et contact SXW Express

Le site `sxwexpress.cn` ne publie aucun numéro de téléphone, aucune adresse e-mail et aucune adresse postale : sa page *Contact US* ne contient que le titre et un volet *Online Support* vide, et l'espace membre sur `sxwexpress.cn:8082` est réservé aux comptes expéditeurs (inutile pour suivre un colis).

Le client contractuel de SXW Express étant le vendeur, adressez-vous d'abord à la boutique où vous avez passé commande, puis ouvrez un litige sur la place de marché si elle ne répond pas. Pour une livraison assurée par GOFO Express aux États-Unis, contactez GOFO au sujet de la tournée finale.

## Suivre un colis SXW Express avec Peek

[Peek](/) identifie immédiatement un code composé de `SXW` et douze chiffres avec la mention « Transporteur détecté », car aucun autre transporteur du catalogue n'utilise ce format (pour un numéro d'un autre format, Peek couvre plus de 3 500 transporteurs ; vous pouvez aussi choisir SXW Express manuellement via « Changer » ou « Changer de transporteur » en tapant `SXW Express`, `Shengxingwang` ou `胜兴旺`).

Peek interroge le traceur officiel de SXW sans compte, code postal ni téléphone. Il affiche le statut, chaque scan (avec les trois étapes chinoises traduites en anglais), le lieu lorsqu'il est fourni, le nom du service et le pays de destination, tout en écartant les noms, adresse, codes postaux, nombre de colis, poids, référence client, détails de paiement, signataire, signature et preuve de livraison. Le traceur de SXW ne donnant pas de date de livraison estimée, Peek n'en affiche pas.

**Étapes écrites à l'avance.** Peek retient toute étape datée dans le futur à l'heure de Pékin, les lignes de suivi simulé (`CO_C_CR`) ainsi que toute étape enregistrée plus de 12 heures avant l'heure qu'elle affiche (ou plus de 6 heures avant dans la même seconde qu'une telle étape) : il s'agit de prévisions et non de scans. Avant que l'heure d'une étape retenue ne soit atteinte, Peek les omet et affiche : « Le site de SXW Express affiche des étapes écrites à l’avance, même la livraison. Peek ne peut pas les confirmer et les laisse donc de côté. Cherche sur ce site le transporteur qui livre dans ton pays et son numéro de suivi ; s’il n’y en a pas, demande au vendeur. » Une fois l'heure passée, Peek les regroupe en grisé sous « Écrit à l’avance par SXW Express » avec le message correspondant (« … et les affiche donc en grisé … ») jusqu'à une vraie livraison ou un retour ; elles ne changent jamais le statut et n'envoient aucune notification. Si le traceur de SXW ne répond pas ou ne connaît pas le numéro, Peek interroge des services de suivi en relais.

Tant que vous gardez la page du colis ouverte ou, le jour de sa livraison, avec les notifications activées, Peek vérifie les mises à jour jusqu'à toutes les 10 minutes — et jusqu'à toutes les 2 minutes à partir de 6 h locales le jour où le colis passe en « En livraison » tant que le traceur direct de SXW répond (après un relais vers GOFO Express ou La Poste suisse, Peek suit le rythme du transporteur final ; via les services de suivi seuls, toutes les heures, toutes les 3 heures après un jour sans mouvement et jusqu'à toutes les 15 minutes le jour de la livraison si le colis est ouvert ou que les notifications sont actives). En dehors de ces cas, ainsi qu'après deux jours sans nouveauté (ou 12 heures sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Alertes au choix : « Chaque scan », « Étapes importantes » (douane d'importation telle que `Start customs clearence`, incidents, tournée, échecs, retrait, livraison et retours — `Customs declaration completed` ne la déclenche donc pas) ou « Livraison seulement » / « Jour de livraison uniquement ». Peek ne peut contacter ni SXW Express ni le vendeur à votre place.

## Questions fréquentes sur le suivi SXW Express

### Qu'est-ce que SXW Express ?

C'est le nom utilisé par Peek pour `Shenzhen Shengxingwang Supply Chain Management Co., Ltd.` (`深圳胜兴旺供应链管理有限公司`, `sxwexpress.cn`), prestataire logistique transfrontalier à Shenzhen proposant fret maritime et aérien, rail Chine-Europe, express international et livraison porte-à-porte DDU/DDP.

### Où suivre moi-même un colis SXW Express ?

Dans l'encadré *Express Tracking* sur la page d'accueil de `sxwexpress.cn` : saisissez le numéro et cliquez sur *Track*. Le traceur officiel s'ouvre en chinois et dispose aussi d'une page en anglais ; le logo 17TRACK voisin mène à un site tiers distinct.

### Que signifie « Customs declaration completed » sur le suivi SXW ?

Que la déclaration douanière d'exportation en Chine est terminée avant le vol. Peek l'affiche en « En transit » : il ne s'agit pas de la douane de votre pays et le colis n'est pas bloqué.

### Qui livre les colis SXW Express dans mon pays ?

Le site de SXW ne publie pas de liste par pays. Vers les États-Unis, le dossier peut contenir un numéro GOFO Express que Peek suit dès que GOFO scanne le colis (et vers la Suisse, La Poste suisse lorsque le canal la désigne) ; sinon, demandez le nom du transporteur final et son numéro au vendeur.

### Qui contacter si mon colis SXW Express n'arrive pas ?

Contactez d'abord le vendeur chez qui vous avez commandé, car `sxwexpress.cn` ne mentionne ni téléphone ni e-mail. Si le vendeur ne répond pas, ouvrez une réclamation auprès de la place de marché.

:::sources
- [SXW Express : page d'accueil](http://www.sxwexpress.cn/) – raison sociale, encadré Express Tracking et bouton Track, lien 17TRACK, présentation DDU/DDP
- [SXW Express : About US](http://www.sxwexpress.cn/about.html) – siège à Shenzhen, fret maritime, aérien, ferroviaire et express, DDU/DDP et dernier kilomètre en Europe, Amérique, Asie du Sud-Est, Moyen-Orient et Afrique
- [SXW Express : Products](http://www.sxwexpress.cn/products/default.html) – lignes UPS Hong Kong, ARAMEX Special Line, petits paquets Moyen-Orient/Europe et États-Unis
- [SXW Express : Contact US](http://www.sxwexpress.cn/contact.html) – page de contact sans téléphone, e-mail ni adresse et volet Online Support vide
- [SXW Express : 会员登录 (espace membre)](http://www.sxwexpress.cn:8082/) – nom chinois de l'entreprise sur le système de suivi
- [SXW Express : trace (suivi en anglais)](http://www.sxwexpress.cn:8082/en/trackIndex.htm) – page anglaise du traceur officiel
- [Commission européenne : Réforme douanière de l'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € dès le 1er juillet 2026 et frais de 2 € dès le 1er novembre 2026
- [Commission européenne : Orientations sur le droit de 3 € et les frais (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – calcul par article
- [Douane française : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – TVA en France métropolitaine et dans les DOM
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA suisse et non-perception sous 5 francs
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format SXW, traduction anglaise des lignes chinoises, fuseaux horaires, étapes écrites à l'avance, relais GOFO et Poste suisse
:::
