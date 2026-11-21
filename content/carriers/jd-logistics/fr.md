---
title: Suivi colis JD Logistics : numéros JD, statuts et contact
description: Suivez un colis JD Logistics (京东物流) : formats JD et VG, sens des statuts en anglais et chinois, entrepôts en France et en Europe, douane et service client.
slug: suivi-colis-jd-logistics
published: 2026-11-18
updated: 2026-11-21
---

JD Logistics (京东物流, Jingdong Logistics) est la branche logistique du groupe JD.com, cotée à la Bourse de Hong Kong (`HKEX : 2618`). À l’international, elle opère sous les marques JoyLogistics et JoyExpress : elle expédie les commandes des marchands depuis ses entrepôts en **France**, en **Allemagne**, aux **Pays-Bas**, en **Pologne**, au **Royaume-Uni**, aux **États-Unis** ou en **Australie**, et achemine les colis e-commerce depuis la **Chine**. Vous pouvez suivre un envoi en anglais sur le [suivi international de JD Logistics](https://www.jingdonglogistics.com/Tracking) ou coller votre numéro dans le champ ci-dessus. Ce numéro vous est transmis par la boutique qui a expédié votre commande.

## Numéro de suivi JD Logistics : à quoi il ressemble

Voici les formats que Peek reconnaît pour JD Logistics, ainsi qu’un format voisin qui appartient à un autre transporteur (avec des chiffres inventés) :

| Numéro | Ce que c’est |
| --- | --- |
| `JD4829105736491` | `JD` suivi de 13 chiffres : bordereau JD Logistics. |
| `JDA482910573649` | `JD`, une lettre et 12 chiffres : JD Logistics également. |
| `JDAP48291057364` | `JD`, deux lettres et 11 chiffres : JD Logistics également. |
| `VG48291057364` | `VG` suivi de 11 chiffres : utilisé par JD Logistics, mais ce format correspond aussi à certains numéros de [La Poste](carrier:la-poste), si bien que Peek ne désigne pas JD d’emblée (voir plus bas). |
| `JD482910573649182736` | `JD` suivi de 18 chiffres : ce n’est pas JD Logistics. Peek interroge [DHL Paket](carrier:dhl), qui utilise ce format. |

Les minuscules, les espaces et les tirets n’ont pas d’importance dans Peek. Le suivi international de JD accepte jusqu’à 100 numéros à la fois, séparés par des espaces : appuyez sur Entrée ou cliquez sur « Track ».

**Où le trouver.** Le vendeur qui a confié le colis à JD l’indique dans l’e-mail d’expédition ou sur la page de votre commande. Si vous ne le trouvez pas, consultez [comment trouver votre numéro de suivi](guide:find-tracking-number).

**Colis circulant en Chine continentale.** Un envoi acheminé à l’intérieur de la **Chine** (par exemple vers l’entrepôt d’un transitaire) se suit sur le site chinois [jdl.com](https://www.jdl.com/orderSearch) : saisissez le numéro de bordereau ou de colis sous `运单查询` et cliquez sur `立即查询` (plusieurs numéros peuvent être séparés par des virgules, des espaces ou des retours à la ligne).

## Statuts de suivi JD Logistics expliqués

Le suivi international de JD affiche chaque étape en anglais avec la ville où elle a eu lieu. Voici les libellés relevés par Peek dans les propres termes de JD, dans l’ordre habituel d’un colis expédié depuis un entrepôt JD hors de Chine :

| Ce qu’affiche JD | Statut dans Peek | Ce que cela signifie pour vous |
| --- | --- | --- |
| `Picking Completed.` | « Pris en charge » | Votre commande a été préparée dans un entrepôt JD où le vendeur stockait déjà sa marchandise. |
| `Weighed at Warehouse.` | « Pris en charge » | Le colis a été pesé dans ce même entrepôt avant son départ. |
| `Departed from Warehouse.` | « En transit » | Il a quitté l’entrepôt de JD Logistics. |
| `Handed over to Carrier` | « En transit » | JD l’a remis au transporteur chargé de la livraison finale. |
| `Shipping Label Created` | sans recul de statut | L’étiquette du transporteur local relayée par JD (enregistrée comme création d’étiquette, sans faire reculer un colis déjà en transit). |
| `Arrived at GOFO Regional Hub` | « En transit » | Aux **États-Unis** : [GOFO Express](carrier:gofo), qui assure la livraison locale sur ces envois, a scanné le colis dans son hub régional. |

**Un colis parti de Chine** suit d’autres étapes, dans cet ordre : d’abord `Shipment Picked up` (collecté chez le vendeur), puis `Arrived at Warehouse`, `Weighed at Warehouse` et `Consolidation Completed` dans l’entrepôt chinois de JD qui le garde jusqu’au départ — toutes classées « Pris en charge » (`Consolidation Completed` signifie regroupé avec d’autres colis pour le vol, et non livré). Viennent ensuite, toutes en « En transit » : `Departed from Warehouse.`, `Arrive at airport`, `Customs released from origin port` (dédouanement export terminé), `Departure from origin port`, `Arrive at destination port`, `Cleanrance agency picked` (orthographe de JD : le déclarant en douane a récupéré le colis dédouané, pas vous) et `Handover to local carrier`. Aux **États-Unis**, les étapes de GOFO suivent sous les codes de JD : `Arrived at GOFO Regional Hub`, `In Transit to Next Facility`, `Loaded on Vehicle` (chargé dans un centre GOFO) et `Arrived at GOFO Delivery Station` (« En transit »), puis `Out for Delivery` (« En livraison »).

**Le numéro du transporteur local.** Sur le suivi de JD, la ligne `Departed from Warehouse.` d’un colis parti de Chine se termine parfois par `Tracking no` suivi du numéro attribué par le transporteur final (aux **États-Unis**, un numéro `GFUS` à 14 chiffres tel que `GFUS48291057364918` pour GOFO Express). Peek retire ce numéro du texte du scan, mais interroge directement ce transporteur lorsque le format du numéro l’identifie : dès que ce transporteur présente un scan au moins aussi récent que le dernier de JD, Peek poursuit le suivi chez lui tout en conservant l’historique JD et affiche « Livraison par GOFO Express ». Pour un numéro que Peek ne peut pas relier à un transporteur, recherchez-le sur le site du transporteur local.

Peek interprète aussi les autres étapes de JD grâce à leur code interne : commande créée (« Étiquette créée ») ; dédouanement commencé (« À la douane ») ; dédouanement terminé, déchargement ou chargement en centre de tri, déchargement au site de destination, ou acheminement vers un centre de tri à l’étranger (« En transit ») ; livreur JD en route (« En livraison ») ; signature (« Livré »). La ligne de mise en tournée de JD mentionne parfois le nom et le téléphone du livreur entre crochets `【…】` : Peek les retire.

**Lignes du suivi chinois (`jdl.com`).** Pour un colis circulant en **Chine**, les scans sont en chinois, notamment `京东快件已取件` (collecté chez l’expéditeur), `已到达【…】` (arrivé au centre de tri ou à l’agence de livraison indiquée entre crochets), `正在派送中` (en cours de livraison), `已与收件人约定新的派送时间` (nouveau créneau convenu avec le destinataire) et `已送达` (livré).

## Livraison JD Logistics en Europe et à l’international

**Colis expédiés depuis la Chine.** La page « Cross-Border Parcels » de JD Logistics présente un service destiné aux vendeurs e-commerce expédiant depuis la **Chine** vers les **États-Unis**, le **Royaume-Uni**, l’**Australie**, le **Canada**, le **Mexique**, la **Russie** et d’autres pays en cours de déploiement, avec trois formules (standard, express ou économique). JD achemine les envois par avion puis les confie à des partenaires locaux pour le dernier kilomètre, avec un délai moyen annoncé de 3 à 6 jours ouvrés pour cette distribution locale, sans publier la liste de ces partenaires (aux **États-Unis**, l’historique JD montre [GOFO Express](carrier:gofo)). En **Europe**, JD.com a également lancé en avril 2025 sa marque dédiée de livraison express JoyExpress.

**Colis expédiés depuis un entrepôt JD en Europe ou ailleurs.** JD Logistics exploite aussi des entrepôts hors de Chine, notamment en **France** près de Paris (`FR-JD-PAR1` à Dugny, avec distribution en France et dans l’UE), en **Allemagne** près de Francfort (`DE-JD-FRA1` à Buseck) et de Dortmund (`DE-JD-DTM1` à Unna), aux **Pays-Bas** à Venray (`NL-JD-VEN1`) et Tilburg (`NL-JD-TIL1`), en **Pologne** près de Varsovie (`PL-JD-WAW1` à Urzut et `PL-JD-WAW2` à Brwinów), au **Royaume-Uni** dans le Leicestershire (`GB-JD-BHM1` à Lutterworth), aux **États-Unis** (New Jersey, région de Los Angeles et Géorgie) et en **Australie** (Sydney). Lorsqu’un vendeur expédie depuis ce stock local, l’historique commence par `Picking Completed.` et `Weighed at Warehouse.` au lieu de `Shipment Picked up`.

JD ne précise pas sur ses pages combien de tentatives effectue le transporteur final ni où un colis manqué est mis en instance : cela dépend des règles du transporteur local de votre pays. Voir [livraison manquée](guide:missed-delivery).

## Douane, frais d’importation et faux SMS

Selon les règles de service de l’express international de JD Logistics depuis la **Chine continentale** ([jdl.com/serviceDetails](https://www.jdl.com/serviceDetails), onglet `国际`), des droits et taxes peuvent naître lors du dédouanement transfrontalier : ils sont à la charge de l’expéditeur ou du destinataire et ne sont pas compris dans le prix du transport facturé par JD. Vérifiez auprès du vendeur si la TVA et les droits étaient compris lors de l’achat et d’où votre commande est partie. Voir [colis bloqué en douane](guide:customs) et [reconnaître les faux SMS de livraison](guide:delivery-scam-texts).

- **Union européenne.** Depuis le 1er juillet 2026, dans l’**Union européenne**, les biens achetés à distance hors du territoire douanier de l’UE dans des envois d’une valeur intrinsèque maximale de 150 € supportent un droit de douane de 3 € par catégorie d’articles ; depuis le 1er novembre 2026 s’ajoutent des frais de traitement de l’Union de 2 € par catégorie d’articles, quelle que soit la valeur de l’envoi (une catégorie regroupe les marchandises ayant le même classement tarifaire, la même désignation et, lorsqu’elle est requise, la même origine, quel qu’en soit le nombre d’unités).
- **France et Belgique.** En **France** (où la Guadeloupe, la Guyane, la Martinique, La Réunion et Mayotte font partie du territoire douanier de l’UE mais restent hors du territoire TVA de l’UE), la plateforme officielle [Cybermalveillance.gouv.fr](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) met en garde contre les SMS et courriels d’hameçonnage réclamant de faux frais de douane ou de livraison via un lien et conseille, au moindre doute, de vérifier le véritable suivi du colis ou de contacter directement l’entreprise de livraison. En **Belgique**, pour un colis que bpost traite, dès que les frais d’importation sont connus, [bpost](https://www.bpost.be/fr/shopping-hors-ue) envoie une demande de paiement par e-mail, SMS ou courrier et invite à régler exclusivement via ses canaux officiels (application My bpost, Track & Trace ou Bureau de Poste), sans cliquer sur un lien suspect.
- **Suisse.** En **Suisse**, l’Office fédéral de la douane et de la sécurité des frontières ([OFDF](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets)) ne perçoit ni droits de douane ni TVA à l’importation lorsque le montant respectif de la redevance est inférieur à 5 francs suisses (soit une base d’imposition de 62 CHF au taux de TVA de 8,1 %, ou de 193 CHF au taux réduit de 2,6 %), hors frais de dédouanement facturés séparément par la poste ou le transporteur.

## Colis JD Logistics bloqué, en retard ou retourné

**Aucun résultat pour le moment.** Si le suivi de JD ne trouve encore rien sous ce numéro, vérifiez-le auprès du vendeur. Peek affiche « En attente de JD Logistics » et continue d’interroger le transporteur ; si le numéro reste inconnu après deux jours, le message « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » s’affiche.

**Aucune mise à jour depuis plusieurs jours.** Lorsqu’un colis en route n’a reçu aucun nouveau scan depuis quatre jours, Peek affiche le jour du dernier scan (« Aucune mise à jour depuis lun 12 oct »), rappelle que les colis restent souvent silencieux sur les vols long-courriers, en douane ou le week-end (au bout de trois semaines sans scan, il conseille plutôt de contacter le vendeur), et propose un lien « Demande de l’aide à JD Logistics » vers la page de contact de JD (ou vers le transporteur local lorsqu’un relais comme GOFO a pris la suite). Voir [pourquoi le suivi ne bouge plus](guide:tracking-not-updating) et [suivre un colis depuis la Chine](guide:tracking-from-china).

**En retard ou perdu.** C’est le vendeur qui a réservé le transport auprès de JD : adressez-vous d’abord à lui. Pour son express international au départ de la **Chine continentale**, le règlement de JD précise que le délai de livraison estimé inscrit sur le bordereau n’est donné qu’à titre indicatif : JD ne rembourse pas les frais de port et ne verse pas d’indemnité en cas de dépassement de délai. Voir [colis perdu](guide:lost-parcel).

**Retourné.** Les règles de l’express international de JD au départ de la **Chine continentale** n’offrent pas de service de retour une fois la marchandise expédiée hors de Chine continentale. Pour retourner un achat, demandez la marche à suivre à la boutique. Voir [colis retourné à l’expéditeur](guide:returned-to-sender).

**Marqué livré mais absent.** Contactez le vendeur, vérifiez auprès des voisins ou du gardien et consultez [colis indiqué livré mais non reçu](guide:delivered-not-received).

## Service client et contact JD Logistics

La [page Contact Us internationale de JD Logistics](https://www.jingdonglogistics.com/contact) publie ses adresses d’assistance par pays (fermé le week-end et les jours fériés sauf mention contraire, sans fuseau horaire précisé) :

- **France :** `org.cs.eu1@jd.com`, du lundi au vendredi de 09:00 à 17:00.
- **Allemagne, Pays-Bas et Royaume-Uni :** `org.cs.eu1@jd.com`, du lundi au vendredi de 09:00 à 18:00.
- **Pologne :** `org.cs.intl1@jd.com`, du lundi au vendredi de 09:00 à 17:00.
- **États-Unis :** `org.cs.us1@jd.com`, du lundi au vendredi de 08:00 à 17:00.
- **Australie :** `+61 (02)87239755` et `org.cs.intl1@jd.com`, du lundi au vendredi de 09:00 à 18:00 (fermé le dimanche et les jours fériés).

JD ne publie pas de contact dédié pour la Belgique, la Suisse, l’Espagne, l’Italie, le Portugal ou le Canada. En **Chine**, le numéro du siège de JD Logistics est le `950616` (indiqué `0086 950616` depuis l’étranger sur sa page International Express), et la [page Contact Us de jdl.com](https://www.jdl.com/en/contactUs) propose également un bouton « Online Consultation ». Pour toute question sur votre commande elle-même, contactez la boutique.

## Suivre un colis JD Logistics avec Peek

[Peek](/) identifie immédiatement JD Logistics à partir des formats `JD…` du tableau ci-dessus (« Transporteur détecté »). Un numéro `VG` suivi de 11 chiffres correspondant aussi au format de La Poste, Peek interroge d’abord La Poste et d’autres services de suivi (« Vérification des services de suivi… ») ; s’ils ne le trouvent pas, appuyez sur « Changer » et choisissez JD Logistics. Un lien `jingdonglogistics.com` contenant `waybillCodes=` associe directement le colis à JD Logistics quel que soit le numéro ; à partir d’un lien `jdl.com`, Peek extrait le numéro lorsqu’il correspond à l’un des formats `JD…`. Si votre numéro JD a une autre forme, collez-le quand même : Peek suit plus de 4 000 transporteurs, et vous pouvez toucher « Changer » puis sélectionner JD Logistics sous « Tous les transporteurs » (la recherche accepte aussi `Jingdong` ou `京东物流`), ou choisir « Changer de transporteur » sur un colis déjà enregistré.

Peek lit le suivi international de JD : le statut, chaque étape dans les mots de JD avec sa ville et son pays en anglais (même lorsque JD écrit le pays en chinois) et l’heure de livraison. Les heures sont lues sur l’horloge du lieu du scan et affichées dans le fuseau horaire de votre appareil (« Heures dans ton fuseau horaire : … » sur peektracker.com et dans les apps iPhone et Android). Peek n’affiche pas de date de livraison prévue pour JD Logistics et écarte les coordonnées du destinataire, les photos de preuve de livraison éventuelles, le nom et le téléphone du livreur ainsi que le numéro partenaire collé en fin de ligne de départ.

Pour les séries propres à la **Chine continentale** (`JD` et 13 chiffres commençant par `0` ; `JDJ`, `JDK`, `JDV` ou `JDX` et 12 chiffres ; `JDVA`, `JDVB`, `JDVC` ou `JDAZ` et 11 chiffres), Peek ouvre le suivi chinois sur `jdl.com` au lieu du suivi international. Pour `JDW` et 12 chiffres ou `JDAP` et 11 chiffres, il n’interroge que le suivi international ; pour les autres numéros `JD`, il consulte d’abord le suivi international puis le chinois si le premier ne renvoie rien. Peek affiche les lignes chinoises en chinois sans le nom ni le téléphone du livreur ni l’identité du signataire. Le suivi chinois demandant souvent une vérification visuelle que Peek ne peut pas franchir, Peek met alors en pause l’ouverture de `jdl.com` pour ce numéro pendant 6 heures ou plus, jusqu’à un jour : si un colis interne à la Chine n’affiche rien dans Peek, vérifiez-le directement sur `jdl.com`.

Tant que vous gardez le colis ouvert, ou le jour de sa livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes — et, à partir de 6 h locales le jour où JD scanne le colis en cours de livraison, jusqu’à toutes les 2 minutes tant que le suivi international de JD répond (toutes les 5 minutes lorsqu’il ouvre le suivi chinois sur `jdl.com`) ; via les seuls services de suivi, Peek vérifie une fois par heure, toutes les 3 heures après un jour sans scan et jusqu’à toutes les 15 minutes le jour de livraison tant que le colis est ouvert ou que les notifications sont activées. Le reste du temps, ainsi qu’après deux jours sans nouvelle (12 heures sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Lorsqu’un autre transporteur reprend la livraison finale, Peek adopte sa cadence (aux **États-Unis**, GOFO Express : jusqu’à toutes les 30 minutes, ou toutes les heures après un échec, y compris le jour de livraison). Vous pouvez activer les alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni JD Logistics ni le vendeur à votre place.

## Questions fréquentes sur le suivi JD Logistics

### JD Logistics est-il fiable ?

Oui. JD.com a lancé la construction de son propre réseau logistique en 2007, créé le groupe JD Logistics en avril 2017 et l’a introduit à la Bourse de Hong Kong en mai 2021 (code `2618`). Sa page de présentation indique un réseau de plus de 1 600 entrepôts exploités en propre et 2 000 entrepôts cloud exploités par des tiers dans 26 marchés. Dans le monde, JD Logistics exploite plus de 200 entrepôts sous douane, de distribution directe internationale et à l’étranger.

### Comment suivre un colis JD Logistics ?

Sur `jingdonglogistics.com/Tracking` (jusqu’à 100 numéros séparés par des espaces), sur `jdl.com/orderSearch` pour un colis circulant à l’intérieur de la Chine, ou en collant votre numéro en haut de cette page.

### Qui livre les colis JD Logistics en France et en Europe ?

JD Logistics ne publie pas la liste de ses partenaires de dernier kilomètre pour les envois venus de Chine, mais exploite en Europe sa marque express dédiée JoyExpress ainsi que ses entrepôts près de Paris (Dugny), Francfort (Buseck), Dortmund (Unna), Venray, Tilburg, Varsovie et dans le Leicestershire. Lorsqu’une ligne `Departed from Warehouse.` mentionne `Tracking no` suivi du numéro d’un transporteur local connu de Peek, Peek suit automatiquement ce second numéro dès que ce transporteur a scanné le colis.

### Pourquoi mon suivi JD Logistics ne bouge-t-il plus ?

Soit JD n’a pas encore reçu le colis, soit l’envoi est en vol ou en dédouanement, soit le transporteur local n’a pas encore publié de nouveau scan. Au bout de quatre jours sans mise à jour, contactez le vendeur ou écrivez au service client de JD pour votre pays (`org.cs.eu1@jd.com` en France). Pour un colis interne à la Chine, consultez `jdl.com`.

### Comment contacter JD Logistics ?

Par e-mail via les adresses publiées sur `jingdonglogistics.com/contact` (`org.cs.eu1@jd.com` en France du lundi au vendredi de 09:00 à 17:00, et de 09:00 à 18:00 en Allemagne, aux Pays-Bas et au Royaume-Uni). En Chine, la ligne d’assistance est le `950616` (`0086 950616` depuis l’étranger). Pour votre commande ou un remboursement, contactez d’abord le vendeur.

:::sources
- [JD Logistics : Track your shipment (en anglais)](https://www.jingdonglogistics.com/Tracking) – suivi international en anglais, jusqu’à 100 numéros séparés par des espaces, touche Entrée ou bouton Track
- [JD Logistics : About JINGDONG Logistics (en anglais)](https://www.jingdonglogistics.com/about) – filiale logistique de JD.com (HKEX : 2618), marques JoyLogistics et JoyExpress, plus de 1 600 entrepôts en propre, 2 000 entrepôts cloud et plus de 200 entrepôts internationaux dans 26 marchés
- [JD Logistics : Contact Us (en anglais)](https://www.jingdonglogistics.com/contact) – boîtes e-mail du service client, numéro australien, horaires et fermetures par pays (France, Allemagne, Pays-Bas, Royaume-Uni, Pologne, États-Unis, Australie) et ligne 950616
- [JD Logistics : Cross-Border Parcels (en anglais)](https://www.jingdonglogistics.com/ser/CrossExport) – pays desservis, options standard/express/économique, vols et livraison du dernier kilomètre en 3 à 6 jours ouvrés en moyenne
- [JD Logistics : JINGDONG Logistics (France) (en anglais)](https://www.jingdonglogistics.com/ser/GlobalWarehouseFR) – entrepôt FR-JD-PAR1 à Dugny et distribution en France et dans l’UE
- [JD Logistics : JINGDONG Logistics (Germany) (en anglais)](https://www.jingdonglogistics.com/ser/GlobalWarehouseDE) – entrepôts DE-JD-FRA1 à Buseck et DE-JD-DTM1 à Unna
- [JD Logistics : JINGDONG Logistics (The Netherlands) (en anglais)](https://www.jingdonglogistics.com/ser/GlobalWarehouseNL) – entrepôts NL-JD-VEN1 à Venray et NL-JD-TIL1 à Tilburg
- [JD Logistics : JINGDONG Logistics (Poland) (en anglais)](https://www.jingdonglogistics.com/ser/GlobalWarehousePL) – entrepôts PL-JD-WAW1 à Urzut et PL-JD-WAW2 à Brwinów près de Varsovie
- [JD Logistics : JINGDONG Logistics (United Kingdom) (en anglais)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUK) – entrepôt GB-JD-BHM1 à Lutterworth en Angleterre
- [JD Logistics : JINGDONG Logistics (USA) (en anglais)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUS) – entrepôts aux États-Unis (New Jersey, région de Los Angeles et Géorgie)
- [JD Logistics : JINGDONG Logistics (Australia) (en anglais)](https://www.jingdonglogistics.com/ser/GlobalWarehouseAS) – entrepôts à Sydney
- [JDL : 运单查询 (recherche de bordereau, en chinois)](https://www.jdl.com/orderSearch) – suivi chinois par numéro de bordereau ou de colis, plusieurs numéros séparés par virgules, espaces ou retours à la ligne
- [JDL : 服务细则 (règles de service, en chinois)](https://www.jdl.com/serviceDetails) – onglet international : droits et taxes hors frais de port, délai estimé indicatif sans remboursement ni indemnité, absence de retour hors de Chine continentale
- [JDL : International Express (en anglais)](https://www.jdl.com/en/crossBorder/internationalC2C) – express depuis la Chine continentale vers l’étranger et numéro 0086 950616
- [JDL : Contact Us (en anglais)](https://www.jdl.com/en/contactUs) – hotline 950616 et bouton Online Consultation
- [JDL : Company Profile (en anglais)](https://www.jdl.com/en/profile) – création en avril 2017, cotation à Hong Kong en mai 2021 et lancement de JoyExpress en Europe en avril 2025
- [Commission européenne : EU Customs Reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € par catégorie d’articles jusqu’à 150 € depuis le 1er juillet 2026 et frais de traitement de l’Union de 2 € depuis le 1er novembre 2026
- [Commission européenne : Guidance on the EUR 3 customs duty and the Union handling fee (PDF, en anglais)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – frais de 2 € quelle que soit la valeur et définition d’une catégorie d’articles
- [Commission européenne : champs territoriaux douanier et TVA de l’UE](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – statut des DROM français
- [Cybermalveillance.gouv.fr : Escroqueries à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – prévention des SMS et e-mails frauduleux en France
- [bpost : Acheter hors de l’UE](https://www.bpost.be/fr/shopping-hors-ue) – demande de paiement et règlement exclusif via les canaux officiels en Belgique
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – franchise de perception en dessous de 5 CHF de droits ou de TVA en Suisse et frais séparés du transporteur
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats `JD` et `VG`, libellés et codes JD, relais GOFO Express, suivi chinois `jdl.com` et règles d’affichage dans Peek
:::
