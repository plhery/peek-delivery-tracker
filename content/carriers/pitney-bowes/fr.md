---
title: Suivi Pitney Bowes : numéros UPAA, UPBE et PBX expliqués
description: Suis un colis Pitney Bowes (comme une commande eBay internationale) : formats UPAA, UPBE et PBX, sens des statuts et qui assure la livraison.
slug: suivi-pitney-bowes
published: 2026-11-23
updated: 2026-11-23
---

Pitney Bowes expédie des colis à l'international : dans le code de Peek, ses numéros correspondent à des commandes eBay expédiées depuis les États-Unis et le Royaume-Uni, ainsi qu'à ses autres envois vers l'étranger. Dans les historiques présents dans le code de Peek, le colis passe par un centre d'expédition Pitney Bowes puis part vers le pays de l'acheteur, où un partenaire — comme la poste nationale — assure la livraison. Le numéro commence par UPAA, UPBE ou PBX et vient du vendeur ou de la place de marché : colle-le dans le champ ci-dessus. Le suivi propre à Pitney Bowes se trouve sur trackpb.shipment.co.

## Numéro de suivi Pitney Bowes : à quoi il ressemble

Peek reconnaît trois formats pour Pitney Bowes, de 14 à 20 caractères chacun. Avec des chiffres fictifs :

| Numéro (fictif) | Ce que c'est |
| --- | --- |
| `UPAAK482917364058291` | UPAA, une lettre et 15 chiffres : dans le code de Peek, un colis eBay provenant des États-Unis ou du Royaume-Uni. |
| `UPBE48291736A4F02819` | UPBE et 16 caractères (chiffres ou lettres de A à F) : un colis eBay provenant du Royaume-Uni. |
| `PBXHK482917365` | PBX, deux lettres et 9 chiffres : un autre colis Pitney Bowes envoyé à l'étranger. |
| `PBXHK48291736A4F0281` | PBX, deux lettres et 15 caractères (chiffres ou lettres de A à F) : le même en version longue. |
| `UPAA482917364058291` | UPAA sans sa lettre : ne correspond pas au format Pitney Bowes, et Peek ne reconnaît aucun transporteur. Si tu choisis Pitney Bowes, Peek indique qu'il « n’émet pas de numéros comme celui-ci ». Vérifie s'il manque un caractère. |

Les espaces, tirets et minuscules n'ont pas d'importance dans Peek. Son catalogue connaît aussi Pitney Bowes sous les noms Pitney Bowes Global Ecommerce, Pitney Bowes Expedited Parcel Service, eBay Global Shipping Program et PBI.

**Où le trouver.** Dans l'e-mail d'expédition du vendeur ou sur la page de ta commande. Pour eBay, consulte [les commandes eBay](guide:ebay-orders) ; sinon, [comment trouver ton numéro de suivi](guide:find-tracking-number).

## Statuts de suivi Pitney Bowes expliqués

Le suivi de Pitney Bowes affiche chaque ligne avec une date, une heure et, la plupart du temps, un lieu, du plus récent au plus ancien. Ses lignes ne portant pas de code, Peek lit leur texte. Dans l'ordre où un colis les rencontre :

**Au centre d'expédition.** Les lignes propres à Pitney Bowes :

| Ce qu'affiche Pitney Bowes | Ce que cela signifie pour toi | Ce qu'affiche Peek |
| --- | --- | --- |
| `Tracking details uploaded`, `Label Created` | Le vendeur a transmis les données du colis à Pitney Bowes. Pitney Bowes n'a pas encore le colis. | « Étiquette créée » |
| `Package not received by Hub or First Mile Carrier yet` | L'étiquette existe, mais ni le centre d'expédition ni le transporteur chargé de l'y apporter ne l'a encore scanné. | « Étiquette créée » |
| `Processing at Shipping Center` | Le centre d'expédition de Pitney Bowes a le colis et le traite. | « En transit » |
| `First Dimension Event`, `Second Dimension Event` | Le centre mesure le colis. | « En transit » |
| `Parcel Documentation and Labeling`, `Customs Documentation and Labeling` | Le centre ajoute les documents d'exportation et les étiquettes. Ce n'est pas encore la douane elle-même. | « En transit » |
| `Container Assignment`, `Container Closed` | Placé dans un conteneur pour le trajet, puis le conteneur est fermé. | « En transit » |
| `Shipped from Global Shipping Center to Destination`, `Departed for Destination` | Le colis a quitté le centre vers le pays de destination. | « En transit » |
| `International Carrier` | Remis au transporteur qui l'achemine à l'étranger. | « En transit » |
| `In Transit` | En cours d'acheminement. Peut apparaître sans lieu. | « En transit » |

**Acheminement, douane et remise.** Dans les historiques du code de Peek, les colis quittant le centre britannique de Pitney Bowes continuent avec Interpost, un partenaire qui dépose la déclaration d'importation puis les remet à la poste du pays de destination. Les lignes de ce tronçon, dont les deux premières paraissent sans lieu pendant le traitement au centre britannique :

| Ce qu'affiche Pitney Bowes | Ce que cela signifie pour toi | Ce qu'affiche Peek |
| --- | --- | --- |
| `Item processed at Interpost` | Le centre britannique traite le colis pour Interpost. | « En transit » |
| `Interpost handed item over to postal company` | Remise par Interpost à la poste du pays de destination, qui livrera le colis. Apparaît au même instant que la ligne ci-dessus, alors que le colis peut encore être au centre. | « En transit » |
| `Customs Clearance` | Déclaration d'importation déposée dans le pays de destination. Formalité administrative : elle peut apparaître quelques heures après le départ du Royaume-Uni, avant l'arrivée du colis. | « À la douane » |
| `B2C ClearanceStatus4` | Dédouané à l'importation. Peek l'écrit `Customs import cleared`, comme le suivi d'Interpost intitule ce code. | « En transit » |
| `B2C ClearanceStatus` suivi d'un autre chiffre | Étape de dédouanement dont le code de Peek ne connaît pas le sens. Peek l'écrit `Customs clearance update`. | « À la douane » |
| `Cleared customs` | Passé en douane. | « En transit » |
| `Package is created in the system` | Le transporteur suivant ouvre son dossier pour le colis. Cela ne le déplace pas. | aucun changement |

**Les lignes de la poste.** Après la remise, le suivi relaie les scans de la poste qui livre :

| Ce qu'affiche Pitney Bowes | Ce que cela signifie pour toi | Ce qu'affiche Peek |
| --- | --- | --- |
| `Item in transit by postal company` | La poste achemine le colis vers toi. | « En transit » |
| `Parcel is out for delivery` | En tournée de livraison. | « En livraison » |
| `Item is waiting for addressee at local post office` | T'attend à ton bureau de poste local : viens le retirer. | « Prêt à être retiré » |
| `Delivered by Consignee Pickup` | Tu l'as retiré toi-même en tant que destinataire. | « Livré » |
| `Item has been delivered` | Livré. | « Livré » |

`Departed carrier facility` et `Processed by Carrier` s'affichent également comme « En transit ». Les autres lignes suivent les règles générales de Peek ; une ligne inconnue s'affiche telle qu'elle est écrite et compte comme « En transit », sauf si le colis est déjà en livraison ou au-delà : dans ce cas le statut reste inchangé.

**Le statut global.** Le suivi donne aussi un statut global au colis. S'il indique livré, Peek affiche « Livré » même si la dernière ligne ne le dit pas. Ses autres statuts ne comptent que tant qu'aucune ligne n'en possède. Son statut d'incident, déjà apparu sur un colis simplement retiré par son destinataire, n'écrase jamais une ligne.

## Qui livre les colis Pitney Bowes

Peek n'ayant pas pu lire le site web de Pitney Bowes, cette page ne dresse pas de liste de ses partenaires ou pays. Le code de Peek montre Interpost prenant le relais depuis le centre britannique pour confier les colis à la poste du pays de destination, et un historique montre [Poste Italiane](carrier:poste-italiane) livrant en Italie. Le suivi de Pitney Bowes nomme le transporteur qui prend la suite et peut relier une ligne à la page de suivi de ce transporteur avec son numéro.

**Tentatives de livraison et retrait.** Les tentatives, avis de passage et délais de garde suivent les règles de la poste qui livre : renseigne-toi auprès d'elle ou consulte sa page sur Peek si elle existe. Voir aussi [livraison manquée](guide:missed-delivery) et [colis non retirés](guide:uncollected-parcel).

**Douane.** Le paiement éventuel de frais à l'arrivée dépend de ton pays et de ce que le vendeur ou la place de marché a facturé à la commande : demande-leur d'abord. Peek n'a pas pu vérifier comment Pitney Bowes gère les frais d'importation. Voir [colis retenus en douane](guide:customs).

## Colis Pitney Bowes sans mise à jour, perdu ou retourné

**Rien pour l'instant.** Pour un numéro inconnu ou un colis dont l'historique a expiré, le suivi de Pitney Bowes renvoie la même réponse que lorsqu'il ne parvient pas à lire un colis. Peek ne pouvant pas le distinguer d'une panne, il interroge les services de suivi et, si aucun n'a le colis, affiche « En attente de Pitney Bowes », puis dès la deuxième vérification « Impossible de joindre Pitney Bowes pour l’instant. Peek continue d’essayer. » tout en poursuivant ses essais. Vérifie le numéro avec le tableau ci-dessus. Si rien n'apparaît au bout de quelques jours, demande au vendeur si le colis a bien été envoyé.

**Bloqué sur `Package not received by Hub or First Mile Carrier yet`.** Le vendeur a créé l'étiquette, mais ni le centre d'expédition Pitney Bowes ni le transporteur du premier kilomètre ne l'a scanné. Demande au vendeur quand il l'a déposé.

**Bloqué sur `Customs Clearance`.** La déclaration est déposée alors que le colis n'est peut-être même pas encore dans le pays. Attends la ligne de dédouanement, affichée `Customs import cleared` dans Peek. Si cela dure longtemps, préviens le vendeur.

**Plus de nouveauté une fois le colis chez la poste.** Après les premières lignes de la poste, son propre suivi peut être en avance sur ce que relaie Pitney Bowes, et Peek ne bascule pas automatiquement dessus. Ouvre la page de la poste depuis la ligne sur le suivi de Pitney Bowes, ou ajoute le numéro de la poste dans Peek comme un colis distinct.

**Aucune mise à jour depuis plusieurs jours.** Après quatre jours sans nouveau scan sur un colis en « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche un signalement (« Aucune mise à jour depuis quatre jours » ou « Aucune mise à jour depuis … ») avec la note « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Pitney Bowes. » Voir [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**Indiqué livré mais introuvable.** Vérifie l'endroit du dépôt et demande aux voisins, puis à la poste qui a livré et au vendeur. Voir [livré mais non reçu](guide:delivered-not-received).

**Perdu ou retourné.** Demande au vendeur ou à la place de marché un remboursement ou un nouvel envoi : c'est eux qui ont réservé l'expédition. Voir [colis perdus](guide:lost-parcel) et [retourné à l'expéditeur](guide:returned-to-sender).

## Service client et contact Pitney Bowes

Peek n'ayant pas pu lire le site web de Pitney Bowes, cette page n'indique aucun numéro de téléphone ni adresse e-mail : Peek ne cite que des coordonnées relevées directement sur les pages du transporteur.

Pitney Bowes achemine le colis pour le vendeur ou la place de marché ; adresse-toi donc à eux d'abord — pour une commande eBay, directement via eBay. Une fois le colis remis à la poste locale, interroge-la pour la livraison elle-même.

## Suivre un colis Pitney Bowes avec Peek

[Peek](/) reconnaît immédiatement Pitney Bowes à partir d'un numéro UPAA, UPBE ou PBX (« Transporteur détecté ») et interroge son suivi avec ce seul numéro : ni code postal ni compte. Un lien de suivi provenant de trackpb.shipment.co ou parceltracking.pb.com fonctionne aussi. Si tu ne disposes que du numéro du premier transporteur ou de celui de la poste, colle-le tout de même : Peek suit plus de 4 500 transporteurs. Choisir Pitney Bowes avec « Changer » pour un autre format ne sert pas : Peek indique qu'il « n’émet pas de numéros comme celui-ci ».

Peek affiche le statut, chaque ligne avec son lieu et le poids dans tes unités (le suivi les fournit en livres et en onces). Il écarte le lien et le numéro du transporteur suivant. Le suivi ne fournissant ni date prévue de livraison ni preuve de livraison, Peek n'affiche ni l'une ni l'autre. « Ouvrir le site Pitney Bowes » sur la page du colis ouvre le numéro sur le suivi de Pitney Bowes.

Peek ne transfère pas le colis à la poste : les scans de la poste arrivent via le suivi de Pitney Bowes. Lorsque celui-ci est injoignable ou ne peut lire le colis, Peek interroge des services de suivi et affiche leur historique dans leurs propres termes.

Le suivi indique chaque heure selon l'horloge de l'opérateur qui a scanné le colis, sans fuseau horaire. Peek lit chaque ligne sur l'horloge de son lieu ou du tronçon en cours. Sans lieu précisé, `In Transit` et `Item processed at Interpost` sont lus en UTC, `Interpost handed item over to postal company` en UTC+2 et `Customs Clearance` sur l'horloge du pays nommé. Chaque heure s'affiche dans le fuseau horaire de ton appareil.

Tant que tu as le colis ouvert dans Peek, ou le jour de la livraison avec les notifications activées, Peek interroge directement Pitney Bowes jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes à partir de 6 h sur l'horloge du dernier scan le jour où le colis passe en livraison, tant que le suivi de Pitney Bowes répond ; sinon toutes les heures. Après deux jours sans nouvelle (12 heures tant que le colis affiche encore « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures ; après sept jours sans nouvelle en « Étiquette créée », toutes les 6 heures. Les notifications peuvent suivre « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut pas contacter Pitney Bowes, le vendeur ni la poste à ta place.

## Questions sur le suivi Pitney Bowes

### Qu'est-ce qu'un numéro de suivi UPAA ?

Un numéro Pitney Bowes : UPAA, une lettre et 15 chiffres. Le code de Peek associe les numéros UPAA à des colis eBay venant des États-Unis et du Royaume-Uni, les numéros UPBE à des colis eBay venant du Royaume-Uni et les numéros PBX aux autres colis internationaux de Pitney Bowes. Son catalogue répertorie aussi eBay Global Shipping Program comme autre nom de Pitney Bowes.

### Que signifie « Interpost handed item over to postal company » ?

C'est la remise de ton colis par Interpost — qui reprend les colis au départ du centre britannique de Pitney Bowes — à la poste du pays de destination qui assure la livraison. Pitney Bowes inscrit cette ligne pendant le traitement au centre britannique, de sorte qu'elle peut apparaître avant même le départ du colis. Les scans de la poste suivent ensuite sur le suivi de Pitney Bowes.

### Pourquoi mon suivi Pitney Bowes reste-t-il bloqué sur « Customs Clearance » ?

C'est la déclaration d'importation, parfois déposée quelques heures après le départ du Royaume-Uni, avant l'arrivée du colis. Attends la ligne de dédouanement, affichée `Customs import cleared` dans Peek. Si rien n'évolue longtemps, demande au vendeur.

### Comment contacter Pitney Bowes au sujet de mon colis ?

Adresse-toi d'abord au vendeur ou à la place de marché : ce sont eux qui ont réservé l'expédition. Peek n'ayant pas pu lire le site de Pitney Bowes, cette page ne publie ni téléphone ni e-mail.

:::sources
- [Universal Parcel Scraper : Pitney Bowes](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pitney-bowes) – noms, les trois formats de numéros et ce qu'ils couvrent, liens de suivi, suivi, libellés et étapes, lignes d'Interpost et code douane, lignes relayées par la poste, Poste Italiane sur un colis vers l'Italie, statut global, horloges, poids, ce que Peek laisse de côté
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – pas de transfert automatique vers la poste locale, services de suivi en secours
:::
