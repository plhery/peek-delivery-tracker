---
title: Suivi colis iMile : numéro, statuts et livraison
description: Suis un colis iMile en France, en Belgique ou en Suisse : format du numéro, sens des statuts en anglais, déroulement de la livraison et comment joindre iMile.
slug: imile-suivi
published: 2026-11-16
updated: 2026-11-20
---

iMile est une société de livraison pour le commerce en ligne fondée à Dubaï en 2017. Elle achemine des colis transfrontaliers, notamment depuis la Chine, assure le dédouanement et gère le dernier kilomètre avec ses propres livreurs dans certains pays. Suis ton numéro sur la [page de suivi d'iMile](https://www.imile.com/en/track) ou colle-le dans le champ ci-dessus. Le numéro vient de la boutique, dans le détail d'expédition de ta commande.

## Numéro de suivi iMile : à quoi il ressemble

iMile ne publie aucun exemple de numéro. Voici les formes portées par des colis suivis chez iMile, avec des chiffres inventés :

| Numéro | À qui il correspond |
| --- | --- |
| `IM06010000000000` | `IM`, `0601` et dix chiffres : iMile. |
| `IM99999999999980` | `IM` et 14 chiffres : iMile également. |
| `6010125000000` | 13 chiffres commençant par `6`, suivis de six chiffres en forme de date (mois, jour, année) : le bordereau propre à iMile. |
| `49000000000000` | 14 chiffres commençant par `49` : une forme vue sur des colis iMile en Italie, partagée à 14 chiffres avec DPD, [Chronopost](carrier:chronopost) ou BRT. La forme seule ne suffit donc pas, mais Peek interroge iMile parmi les premiers. |
| `3000000000000` | 13 chiffres commençant par `3` : une autre série suivie par iMile, partagée avec d'autres transporteurs à 13 chiffres. La forme seule ne suffit pas : Peek interroge iMile avec eux. |
| `2000000000000` | Autres numéros à 13 chiffres : ce n'est pas une forme d'iMile. Delhivery, Yunda ou Korea Post en utilisent. |
| `LP99999999999980` | Ce n'est pas iMile, mais [Cainiao](carrier:cainiao), la référence logistique d'AliExpress. |

Sur la page de suivi d'iMile, saisis le code sous « TYPE YOUR ORDER NUMBER » ; l'historique s'affiche sous « ORDER STATUS ». Si iMile ne connaît pas le numéro, cet encadré reste vide, sans message d'erreur. Si tu ne trouves aucun numéro, consulte [où trouver son numéro de suivi](guide:find-tracking-number), puis demande-le au vendeur.

## Statuts du suivi iMile : ce qu'ils veulent dire

Les scans d'iMile s'affichent en anglais, tels que Peek les lit. Les mentions entre crochets `【】` désignent un centre iMile ou un code d'aéroport : ton suivi indiquera donc d'autres lieux. Voici les lignes les plus fréquentes, dans l'ordre du parcours :

| iMile écrit | Ce que ça veut dire pour toi |
| --- | --- |
| `Order submitted, pending to receive.` | Le vendeur a enregistré l'envoi. iMile n'a pas encore le colis. |
| `Your order has been picked up.` | iMile a récupéré le colis chez l'expéditeur. |
| `【IST】The flight has taken off.` | L'avion a décollé ; le code correspond à l'aéroport de départ. |
| `【RUH】Flight Arrived - Waiting for customs clearance.` | L'avion a atterri et le colis attend le dédouanement. |
| `Clearance Processing` | La douane contrôle l'envoi. |
| `【Milan center】Your order has been received.` | Arrivé dans le premier centre d'iMile du pays de destination. |
| `Your order has been shipped from 【Milan center】.` | En route vers le centre suivant ou l'agence de livraison. |
| `Parcel is currently being processed.` | Arrivé à l'agence qui dessert ton secteur. |
| `Our delivery associate is out for delivery.` | En tournée de livraison aujourd'hui. |
| `User not at home` | La tentative a échoué : c'est le motif saisi par le livreur. |
| `Delivery attempt finished,return to Local logistics company warehouse.` | Après l'échec, le colis retourne à l'agence locale. |
| `The delivery method has been confirmed with the recipient as Self-Pickup.` | Après un échec, tu as convenu d'aller retirer le colis toi-même. Il n'a pas encore bougé. |
| `The parcel has been delivered to …. Please collect your parcel promptly at … using your pickup code.` | Il t'attend au point de retrait nommé dans la ligne, à l'adresse indiquée : retire-le avec ton code. « Delivered » désigne ici le dépôt au point de retrait, pas chez toi ; Peek affiche « Prêt à être retiré ». |
| `【Contact Error】need to return.` | Le livreur n'a pas pu te joindre et le retour s'engage. |
| `Your order has been delivered successfully.` | Livré. |
| `Order delivery failed, returning to sender.` | Le colis repart vers l'expéditeur. |
| `【Milan Transit Center】Shipment returned to origin.` | Restitué à l'expéditeur. |

Sur des colis européens, la première ligne s'écrit `Order Submited.` : la faute d'orthographe vient d'iMile, tout comme `Your order has assigned delivery drvier.`, qui signale l'attribution d'un chauffeur avant le départ de la tournée. `Shipment is scheduled to` suivi d'une date entre crochets fixe un jour de livraison. `Scheduled Delivery`, intercalé entre une tournée et la fin de la tentative ou la tournée suivante, reprogramme la livraison sans donner de date : le colis ne bouge pas et Peek garde le statut précédent.

Parmi les autres motifs d'échec saisis par le livreur, outre `User not at home` : `wrong address` (ou `Wrong Address(Insufficient address details)` quand l'adresse est incomplète), `switch off`, `Customer Wants to Change Location`, `Route not completed`, `Traffic, Strike, Bad weather`, une ligne commençant par `failed to schedule due to` suivie d'un motif entre crochets comme `noanswer` (impossible de convenir de la livraison avec toi), ou encore `The driver failed to deliver due to shop(building) closure, we will try to deliver again.` La mention `Lost order` peut aussi apparaître lors d'une tournée sans signifier à elle seule que le colis est définitivement perdu : surveille les scans suivants. Pour un blocage en douane, consulte [colis bloqué en douane](guide:customs).

## Livraison iMile en France, en Belgique et en Suisse

**Qui livre.** Ni la France, ni la Belgique, ni la Suisse ne figurent dans la liste des 30 pays où iMile affiche un bureau, ni parmi les destinations de son service express national (Émirats arabes unis, Arabie saoudite, Mexique, Brésil et Malaisie) ou de son envoi direct international depuis la Chine avec équipe propre du dernier kilomètre (Émirats arabes unis, Arabie saoudite, Mexique, Oman, Koweït, Qatar, Bahreïn, Liban, Australie, Jordanie, Afrique du Sud et Malaisie). iMile ne publie pour ces trois pays ni société locale, ni numéro de téléphone, ni adresse e-mail, ni nom de transporteur partenaire. Demande à la boutique quel transporteur assure la remise finale et continue de consulter le suivi d'iMile.

**Tentatives, points de retrait et preuve de livraison.** iMile ne publie aucune règle propre à la France, à la Belgique ou à la Suisse sur le nombre de passages, les avis de passage ou le délai de garde. Ce sont ses scans qui décrivent la suite : le motif du livreur, le retour à l'agence, une nouvelle tournée, `【Contact Error】need to return.` ou, en cas de retrait convenu, la ligne donnant le nom et l'adresse du point où le colis attend. iMile mentionne les points PUDO (retrait et dépôt) parmi ses services sur sa page d'accueil sans en lister dans ces trois pays. Sa FAQ générale, rédigée surtout pour les Émirats, indique :

- que l'assistance téléphonique prend les demandes de reprogrammation (*rescheduling requests*) ;
- que pour modifier l'adresse, il faut contacter le support client avant que l'envoi n'atteigne l'étape finale de livraison, ou passer par l'*iMile Online Bot* (voir aussi [modifier l'adresse de livraison](guide:change-delivery-address)) ;
- que la preuve de livraison numérique comprend une photo du colis déposé et, le cas échéant, la signature numérique du destinataire.

La page de suivi d'iMile comporte une vue de preuve de livraison qui demande de confirmer que tu es bien la personne autorisée. Dans certains pays, un scan de mise en livraison sur le site d'iMile affiche aussi le téléphone du chauffeur ou celui du service client. Plus de conseils dans [livraison manquée](guide:missed-delivery).

## Colis iMile qui ne bouge plus, perdu ou renvoyé

**Aucun suivi pour l'instant.** Tant que seule la ligne `Order submitted, pending to receive.` apparaît, le vendeur a créé l'ordre d'expédition mais n'a peut-être pas encore remis le colis à iMile. Demande à la boutique quand il est parti. Si tu as choisi iMile avec « Changer » pour un numéro qu'iMile ne connaît pas encore, Peek affiche « En attente de iMile » ; si deux jours plus tard iMile ne connaît toujours pas ce numéro et qu'aucune autre source ne répond pour lui, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » et le vérifie ensuite une fois par jour, puis une fois par semaine après sept jours.

**Suivi figé plusieurs jours.** Un colis venu de Chine reste souvent silencieux entre le décollage, la douane et le premier centre dans le pays d'arrivée. Voir [suivre un colis venu de Chine](guide:tracking-from-china) et [pourquoi un suivi ne bouge plus](guide:tracking-not-updating).

**Douane et frais.** Dans l'Union européenne (France, Belgique), la franchise douanière jusqu'à 150 € a pris fin le 1er juillet 2026 au profit d'un droit de douane forfaitaire provisoire de 3 € par article (par catégorie tarifaire, pas par pièce) sur les ventes à distance importées jusqu'à 150 €, complété depuis le 1er novembre 2026 par des frais de traitement européens de 2 € par article, comptés de la même façon quelle que soit la valeur et dus dès l'acceptation de la déclaration en douane. En Suisse, hors de l'UE, l'Office fédéral de la douane et de la sécurité des frontières (OFDF) rappelle que tout envoi venant de l'étranger est en principe soumis aux droits de douane et à la TVA ; le transporteur qui dédouane le colis perçoit des frais de dédouanement. iMile ne précise pas comment ces sommes sont réglées sur ses envois : vérifie auprès de la boutique si elles étaient incluses au paiement. Détails dans [colis bloqué en douane](guide:customs).

**Le lien de suivi ne s'ouvre pas.** La FAQ d'iMile conseille d'actualiser la page ou de l'ouvrir dans un autre navigateur, de vider le cache et les cookies, puis de contacter le support avec ton numéro de commande pour recevoir un nouveau lien.

**Marqué livré mais introuvable.** Regarde la photo de preuve de livraison sur la page d'iMile, vérifie autour de ta porte et chez les voisins, puis préviens iMile et le vendeur. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Perdu, endommagé ou retourné.** iMile ne publie pas de procédure d'indemnisation pour les destinataires en France, en Belgique ou en Suisse. C'est la boutique qui a le contrat de transport avec iMile : demande-lui un remboursement ou un nouvel envoi. Voir [colis perdu](guide:lost-parcel) et, après `Order delivery failed, returning to sender.`, [colis retourné à l'expéditeur](guide:returned-to-sender).

## Contacter iMile : service client

Tels que relevés le 16 novembre 2026, les canaux publiés par iMile sont les suivants :

- **France, Belgique, Suisse** : aucun téléphone ni e-mail dédié publié sur la page des bureaux d'iMile.
- **iMile Chat** : le bouton en bas à droite d'imile.com ouvre l'assistante virtuelle Alison, qui répond en anglais, espagnol, italien, portugais, allemand, hongrois ou arabe (pas en français).
- **Formulaire d'assistance en ligne** : accessible sur imile.com pour une question sur un envoi, mais il exige aussi un nom d'entreprise (*Company Name*) et un volume quotidien de commandes (*Number of Daily Orders*).

Pour ta commande elle-même, passer par la boutique reste le plus rapide : c'est elle qui a mandaté iMile et peut ouvrir un dossier auprès du transporteur.

## Suivre un colis iMile avec Peek

[Peek](/) reconnaît immédiatement les numéros `IM0601…` comme iMile. Pour les autres numéros `IM` à 14 chiffres ainsi que pour les numéros à 14 chiffres commençant par `49` et ceux à 13 chiffres commençant par `6` ou `3`, que d'autres transporteurs utilisent aussi, la forme seule ne suffit pas : colle quand même ton numéro, Peek suit plus de 3 500 transporteurs. À l'ajout, Peek affiche « Vérification des services de suivi… », interroge les transporteurs compatibles – avec iMile parmi les premiers pour les numéros commençant par `49` et pour ceux commençant par `6` en forme de date – et indique « iMile a ce colis. » dès qu'iMile répond. Si personne ne répond encore ou qu'un autre transporteur s'affiche, choisis iMile via « Changer ». Les liens issus de la page de suivi d'imile.com (`/track?waybillNo=`) sont aussi reconnus comme iMile.

Peek interroge le suivi d'iMile en anglais. Il affiche le statut, l'historique avec le centre ou la ville de chaque scan sur une carte et le pays de destination, en lisant les heures selon l'horloge de ce pays et en les convertissant dans le fuseau horaire de ton appareil (sur peektracker.com et dans les applications iPhone et Android, une ligne sous l'historique le précise : « Heures dans ton fuseau horaire : … »). Lorsqu'un colis quitte la Chine avec [Cainiao](carrier:cainiao) pour le compte d'iMile, Peek ajoute en amont les étapes antérieures de Cainiao : prise en charge, tri, exportation, vol et importation.

Peek retient comme date de livraison prévue le jour indiqué par la dernière ligne `Shipment is scheduled to`, jusqu'à un échec de livraison, un incident, l'arrivée en point de retrait, la livraison, un retour ou un scan postérieur à ce jour. Il écarte le vol programmé, la preuve de livraison, le nom du signataire, les coordonnées et les détails de paiement, et retire des scans le numéro du livreur et celui de l'assistance d'iMile. Des libellés comme « En livraison » ou « Tentative de livraison » sont ceux de Peek ; le texte des scans reste tel qu'iMile l'écrit. Tant que le suivi direct d'iMile répond, Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes le jour où le colis part en livraison (dès 6 h, heure locale) ; via des services de suivi, toutes les heures, et jusqu'à toutes les 15 minutes ce jour-là. Après deux jours sans nouveauté (ou 12 heures tant que le colis reste à « Étiquette créée » ou à « Pris en charge »), Peek passe à une vérification par heure. Tu peux choisir les notifications sur « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni iMile ni la boutique à ta place.

## Questions fréquentes sur le suivi iMile

### Où se trouve mon colis iMile ?

Le dernier scan indique l'étape en cours, et le nom entre crochets `【】` précise le centre ou l'aéroport. `Our delivery associate is out for delivery.` veut dire qu'il est en tournée aujourd'hui ; si un motif du livreur suit cette ligne, la tentative n'a pas abouti.

### Qui livre les colis iMile en France, en Belgique ou en Suisse ?

Les pages d'iMile ne le disent pas : la société ne mentionne ni bureau, ni contact, ni transporteur partenaire dans ces trois pays. Demande à la boutique qui assure le dernier kilomètre et surveille les scans sur le suivi d'iMile.

### Que veut dire « Order Submited » chez iMile ?

Le vendeur a créé le bordereau d'envoi, mais iMile n'a pas encore pris le colis en charge. La faute d'orthographe vient d'iMile ; `Your order has been picked up.` apparaît une fois le colis collecté.

### iMile est-elle une vraie société de livraison ?

Oui. Fondée à Dubaï en 2017, iMile dispose de son propre outil de suivi et de bureaux dans plusieurs pays européens, dont l'Allemagne, l'Autriche, l'Espagne, l'Italie et la Pologne. En cas de blocage, contacte le vendeur ainsi que l'assistance d'iMile.

:::sources
- [iMile : Page d'accueil (en anglais)](https://www.imile.com/en) – fondée à Dubaï en 2017, livraison transfrontalière et dédouanement, points PUDO
- [iMile : Track Your Shipment (en anglais)](https://www.imile.com/en/track) – page de suivi, champs TYPE YOUR ORDER NUMBER et ORDER STATUS, résultat vide sans erreur, vue de preuve de livraison
- [iMile : FAQs (en anglais)](https://www.imile.com/en/CustomerService/FAQ) – suivi en ligne, assistance téléphonique pour reprogrammer, changement d'adresse et Online Bot, preuve de livraison numérique, lien de suivi inactif
- [iMile : Domestic Express Delivery (en anglais)](https://www.imile.com/en/logistics-solutions/domestic-express-delivery) – pays couverts par le service express national
- [iMile : International Dropship Delivery (en anglais)](https://www.imile.com/en/logistics-solutions/international-dropship-delivery) – équipe propre du dernier kilomètre et routes depuis la Chine
- [iMile : Office Locations (en anglais)](https://www.imile.com/en/CustomerService/OfficeLocations) – liste des 30 pays disposant d'un bureau (sans la France, la Belgique ni la Suisse)
- [iMile : Customer Support (en anglais)](https://www.imile.com/en/CustomerService/CustomerSupport) – formulaire d'assistance et champs obligatoires (entreprise et commandes quotidiennes)
- [iMile : iMile Chat](https://help.imile.com/) – assistante virtuelle Alison et langues proposées (sans le français)
- [Douane française : Mesures e-commerce européennes](https://www.douane.gouv.fr/fiche/mesures-e-commerce-europeennes) – suppression de la franchise de 150 € et droit forfaitaire de 3 € par article depuis le 1er juillet 2026, frais de traitement de 2 € par article
- [Commission européenne : EU Customs Reform (en anglais)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – application au 1er novembre 2026 des frais de traitement de l'UE de 2 €
- [Commission européenne : Customs Guidance on EUR 3 customs duty, PDF (en anglais)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – frais de 2 € comptés par catégorie d'article comme les 3 €, quelle que soit la valeur, dus à l'acceptation de la déclaration
- [OFDF : Frais de dédouanement et suivi des colis](https://www.bazg.admin.ch/fr/frais-dedouanement-et-suivi-des-colis) – en Suisse, droits de douane, TVA et frais de dédouanement perçus par le transporteur
- [Universal Parcel Scraper : iMile (en anglais)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/imile) – formes de numéros (dont 49 + 12 chiffres et 3 + 12 chiffres), libellés des scans, étapes Cainiao et fonctionnement dans Peek
:::
