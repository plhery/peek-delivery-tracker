---
title: Suivi SpeedX : numéros SPX, statuts et livraison manquée
description: Suivre un colis SpeedX aux États-Unis : format du numéro SPX, sens des statuts en anglais, tentatives de livraison, photo avec code ZIP et service client.
slug: suivi-speedx
published: 2026-11-21
updated: 2026-11-21
---

SpeedX (Speed Xpress Inc., établie à New Hyde Park dans l'État de New York) est une société de livraison du dernier kilomètre qui remet à domicile les commandes passées en ligne aux **États-Unis**. Tu peux suivre un colis sur [tracking.speedx.io](https://tracking.speedx.io/) ou coller son numéro dans le champ ci-dessus. Ce code vient du marchand auprès duquel tu as commandé : SpeedX génère l'étiquette pour la boutique, de sorte que le vendeur dispose du numéro avant même que SpeedX n'ait reçu physiquement le colis.

## Numéro de suivi SpeedX : format et où le trouver

SpeedX indique que tous ses numéros commencent par `SPX`. En pratique, ils se composent de `SPX`, de trois lettres puis de 12 ou 18 chiffres. Formats reconnus (avec caractères inventés) :

| Format inventé | Ce qu'il désigne |
| --- | --- |
| `SPXLGA482910573649` | `SPX`, 3 lettres et 12 chiffres (18 caractères). |
| `SPXLGA482910573649182736` | `SPX`, 3 lettres et 18 chiffres (24 caractères). |
| `SPXPH482910573649` | `SPXPH` suivi de chiffres : ce n'est pas SpeedX aux États-Unis, mais SPX Express aux Philippines, une autre société. |

Peek ignore les minuscules et les espaces.

**Où il se trouve.** Cherche-le dans l'e-mail de confirmation d'expédition de la boutique ou sur la page de ta commande. S'il est introuvable, SpeedX conseille de le demander au vendeur ; [comment trouver son numéro de suivi](guide:find-tracking-number) recense les autres endroits où vérifier. Aux États-Unis, SpeedX propose aussi des alertes SMS lors de la mise en livraison, auxquelles on s'inscrit sur son site.

**Numéros proches.** `SPX` suivi uniquement de chiffres ne correspond à aucun des deux formats de SpeedX. S'il manque un chiffre ou s'il y en a un de trop dans un code `SPX…`, compare-le au message du marchand. Les numéros de SpeedX ne se recoupent pas non plus avec ceux d'[UniUni](carrier:uniuni), de [GOFO](carrier:gofo) ou d'[USPS](carrier:usps) aux États-Unis. Si le code reste inconnu, SpeedX invite à vérifier auprès de la boutique : le colis voyage peut-être avec un autre livreur du dernier kilomètre. Pour d'autres structures, consulte [les formats des numéros de suivi](guide:tracking-number-formats).

## Que signifient les statuts de suivi SpeedX

La page de suivi de SpeedX existe uniquement en anglais. Voici les étapes courantes dans l'ordre où un colis les franchit :

| Statut SpeedX | Ce que cela signifie pour ton colis | Peek affiche |
| --- | --- | --- |
| `Shipping Label Created` | La boutique a créé l'étiquette ; SpeedX n'a pas encore le colis. | « Étiquette créée » |
| `Pickup Requested` | Le marchand a demandé un enlèvement à SpeedX, qui ne détient pas encore l'envoi. | « Étiquette créée » |
| `Parcel Picked Up` | Un chauffeur SpeedX a collecté le colis chez l'expéditeur. | « Pris en charge » |
| `Arrived at Origin Facility` / `Processing at Origin Facility` | Le colis est arrivé et est trié dans le premier centre SpeedX. | « En transit » |
| `In transit to destination facility` | En acheminement entre centres SpeedX aux États-Unis. | « En transit » |
| `Processing at Transit Facility` | Trié dans un centre intermédiaire sur le parcours. | « En transit » |
| `Arrival at Destination Facility` | Arrivé au centre SpeedX qui dessert ton secteur aux États-Unis. | « En transit » |
| `Processing at Designated Facility` | Trié sur place pour être affecté à la tournée d'un chauffeur. | « En transit » |
| `Out for Delivery` | Un livreur l'emporte pour une remise aujourd'hui. | « En livraison » |
| `Attempted Delivery: No access` | Le chauffeur n'a pas pu accéder à la porte : portail fermé, immeuble inaccessible, travaux ou intempéries. | « Tentative de livraison » |
| `Delivered` | Livré. La page SpeedX précise l'endroit où le colis a été déposé (par exemple local courrier) et joint une photo. | « Livré » |
| `Delivered to PUDO` / `Waiting for collection from Parcelshop` | Après la tournée, le colis a été déposé dans un commerce relais (*parcelshop*) où il attend ton retrait. | « Prêt à être retiré » |
| `Self picked up from Parcelshop` | Le colis a été retiré au *parcelshop*. | « Livré » |
| `Parcel Void` | SpeedX a signalé un problème sur l'envoi. Contacte la boutique. | « Problème signalé » |
| `Exception- Parcel not found at the sort facility` | Colis introuvable au centre de tri. Vois avec le marchand. | « Problème signalé » |
| `Parcel Disposal` | SpeedX a enregistré un incident sans en publier le motif. Demande la suite au vendeur. | « Problème signalé » |

Tant que le suivi n'affiche que `Shipping Label Created` (ou un enlèvement échoué chez l'expéditeur comme `Pickup Failure:Closed Business Hours`), SpeedX n'a pas encore reçu la marchandise : elle se trouve toujours chez le vendeur ou son logisticien.

Une tentative échouée peut mentionner d'autres causes, comme `Attempted Delivery:Building Closed/Weekend/Holiday` pour un local professionnel fermé (« Tentative de livraison »). Sous la tentative, SpeedX ajoute une note indiquant la suite : `The package is undeliverable and schedule to redelivery` annonce une nouvelle mise en livraison, tandis que `The package is undeliverable and return to facility` signale le retour au centre. Peek affiche cette note à la suite du libellé.

Lorsque le dernier événement relève d'une anomalie (`Parcel Void`, `Parcel Disposal` ou colis non trouvé), SpeedX le range dans une catégorie comme `Last Mile Intercept` ou `Last Mile Undelivered`. SpeedX n'explique pas ces catégories publiquement : adresse-toi au vendeur.

## Livraison SpeedX aux États-Unis, tentatives et retrait

Toutes les modalités ci-dessous concernent le réseau de SpeedX aux **États-Unis** :

**Colis expédiés depuis l'étranger.** SpeedX livre aussi aux États-Unis des commandes venues d'autres pays : son service transfrontalier les réceptionne dans de grands hubs aéroportuaires américains et assure le dédouanement auprès des douanes américaines via son propre courtage avant de les injecter dans son réseau de livraison. Sur les droits éventuels, lis [colis retenu en douane](guide:customs).

**Week-ends.** Aux États-Unis, SpeedX livre le samedi de 8 h à 20 h (heure locale) dans toutes les zones couvertes, et le dimanche aux mêmes horaires dans certains secteurs. L'entreprise ne propose ni créneau sur rendez-vous ni alerte d'approche du chauffeur.

**Signature et photo de livraison.** La plupart des colis SpeedX aux États-Unis sont remis sans signature ; les articles soumis à une limite d'âge, comme l'alcool, peuvent exiger la signature d'un adulte de 21 ans ou plus. En l'absence du destinataire, le chauffeur dépose le colis et prend une photo : pour la consulter, ouvre le suivi sur `speedx.io` et saisis ton code postal américain (*ZIP code*) de livraison.

**Instructions et changement d'adresse.** SpeedX enregistre les consignes de livraison (codes de portail, endroit où déposer le colis, horaires d'ouverture ou numéro de téléphone) via Miles, son chatbot, muni du numéro de suivi ; ces notes remontent en temps réel jusqu'au chauffeur, qu'il n'est pas possible d'appeler directement. Reçue à temps, une correction de rue ou de numéro d'appartement aux États-Unis peut être prise en compte par SpeedX ; pour changer de ville, d'État ou de code ZIP, il faut passer par le marchand.

**Tentative de livraison manquée.** Si tu contestes un échec de livraison, vérifie d'abord l'adresse enregistrée sur le site du marchand, puis transmets tes précisions d'accès via le chat Miles : SpeedX indique qu'une nouvelle livraison intervient alors en principe sous 1 à 2 jours ouvrés. SpeedX ne publie ni le nombre maximal de tentatives ni sa durée de garde. Voir aussi [livraison manquée](guide:missed-delivery).

**Retrait.** SpeedX précise ne pas autoriser le retrait par les destinataires dans ses propres entrepôts aux États-Unis : le colis doit être livré. Certains envois sont toutefois confiés après la tournée à un point partenaire (*parcelshop*), signalé par `Waiting for collection from Parcelshop`, où tu peux aller les chercher.

## Colis SpeedX bloqué, marqué livré ou perdu

Les démarches suivantes s'appliquent aux colis pris en charge par SpeedX aux **États-Unis** :

**Suivi immobile.** SpeedX invite à contacter le détaillant si le statut n'a pas évolué depuis trois jours ouvrés. Plus d'informations dans [suivi qui ne bouge plus](guide:tracking-not-updating).

**Marqué livré mais introuvable.** La page d'aide de SpeedX reconnaît qu'un colis est parfois marqué livré avant d'arriver réellement, ou laissé à un endroit inattendu de la propriété. Aux États-Unis, vérifie d'abord la photo de livraison, puis inspecte le porche avant et arrière, la boîte aux lettres ou la salle de courrier (*mailroom*), le garage, les côtés de la maison et les endroits abrités, et interroge ton entourage ou le voisinage. Préviens ensuite SpeedX via Miles, puis contacte le marchand pour la suite. Consulte aussi [colis livré mais non reçu](guide:delivered-not-received).

**Perte, dommage ou erreur d'adresse.** SpeedX n'accepte pas de réclamation directe de la part des destinataires : contacte le marchand pour demander une enquête, un remboursement ou un remplacement, avec ton numéro de commande, le code de suivi et tes échanges (ainsi que des photos en cas d'avarie). SpeedX ne publie pas de délai de forclusion. Voir [colis perdu](guide:lost-parcel).

**Retours.** Une fois le colis livré, tout retour s'effectue auprès de la boutique d'achat ; SpeedX ne collecte pas les retours à domicile aux États-Unis. Si le colis n'est pas encore livré, tu peux demander à Miles d'interrompre la livraison pour le renvoyer au marchand. Voir [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service client SpeedX : chat, téléphone et e-mail

Canaux indiqués pour les destinataires aux **États-Unis** sur la [page d'assistance de SpeedX](https://speedx.io/customer-service-and-support/) :

- **Chat :** Miles, l'assistant virtuel de SpeedX, tous les jours de 8 h à 20 h ET (heure de l'Est américain) depuis le portail de suivi après saisie du numéro.
- **Téléphone aux États-Unis :** `1-949-667-6769`, du lundi au vendredi de 9 h à 21 h EST et le week-end de 11 h à 20 h EST. Une autre question de la même FAQ indique cependant que SpeedX ne dispose pas de ligne téléphonique et renvoie vers Miles : attends-toi à être orienté vers le chat.
- **E-mail :** `speedxops@speedx.io`.

## Suivre un colis SpeedX avec Peek

[Peek](/) reconnaît immédiatement SpeedX à partir d'un code `SPX` de l'un ou l'autre format (`SPX` + 3 lettres + 12 ou 18 chiffres) ou d'un lien `tracking.speedx.io` contenant le numéro, et affiche « Transporteur détecté » avec « Changer ».

Peek lit directement la page de suivi de SpeedX et présente le statut actuel, chaque scan avec sa ville sur la carte à l'heure enregistrée par SpeedX (convertie dans le fuseau de ton appareil, avec la mention « Heures dans ton fuseau horaire : … » sous l'historique sur `peektracker.com` et dans les applications iPhone et Android), ainsi que l'heure exacte de livraison et, si la page de SpeedX en indique un, le jour de livraison estimé. Peek écarte ton adresse postale et la photo de livraison : pour voir la photo, ouvre `speedx.io` avec ton code ZIP. Quand SpeedX ajoute une note sur l'endroit où le colis a été posé, la ligne de livraison la reprend telle quelle, en anglais ; sur un lien partagé, elle affiche seulement « Livré », sans cette note. Il n'y a pas de transfert vers un autre transporteur.

Tant que SpeedX ne connaît pas encore le numéro, Peek affiche « En attente de SpeedX » ; après deux jours sans reconnaissance par SpeedX ni par une autre source, il affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » Si quatre jours s'écoulent sans nouveau scan sur « Étiquette créée », « Pris en charge » ou « En transit », Peek propose « Demande de l’aide à SpeedX », qui renvoie vers la page d'assistance de SpeedX.

Tant que tu gardes le colis ouvert à l'écran, ou le jour de sa livraison avec les notifications activées, Peek vérifie jusqu'à toutes les 10 minutes — et jusqu'à toutes les 2 minutes à partir de 6 h (heure locale) le jour où SpeedX scanne le colis en livraison (`Out for Delivery`), tant que le suivi de SpeedX répond (via des services de suivi seuls : toutes les heures, toutes les 3 heures après une journée calme et jusqu'à toutes les 15 minutes le jour de la livraison si le colis est ouvert ou si les notifications sont activées) ; le reste du temps, ainsi qu'après deux jours sans nouvelle étape (ou 12 heures sur « Étiquette créée » ou « Pris en charge »), le contrôle est horaire. Tu peux activer les notifications pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Lorsque la page de SpeedX ne répond pas, les autres sources de suivi détiennent rarement les envois SpeedX : aucune nouveauté peut n'apparaître avant son retour.

## Questions fréquentes sur le suivi SpeedX

### Le suivi SpeedX est-il fiable ?

Oui : SpeedX est la marque commerciale de Speed Xpress Inc., une société américaine de livraison du dernier kilomètre fondée en 2022 dans l'État de New York qui livre les commandes e-commerce à travers les États-Unis.

### Puis-je retirer mon colis SpeedX en entrepôt ?

Non dans les centres SpeedX aux États-Unis : l'entreprise n'autorise pas le retrait en agence et doit effectuer la livraison. En revanche, si l'historique affiche `Waiting for collection from Parcelshop`, le colis a été déposé après la tournée dans un commerce partenaire (*parcelshop*) où tu peux le retirer.

### Combien de temps SpeedX met-elle à livrer ?

SpeedX ne publie pas de délai garanti pour les destinataires. Elle vend aux expéditeurs des offres à J+1 (*next-day*) et J+2 (*2nd-day*) aux États-Unis, mais toute date estimée provient du marchand et SpeedX prévient qu'elle peut différer de la date réelle.

### Pourquoi mon numéro de suivi SpeedX ne fonctionne-t-il pas ?

Lorsque `tracking.speedx.io` n'affiche encore aucune donnée (`No tracking information for … is available at this time`), l'expéditeur n'a peut-être pas encore transmis les données du colis à SpeedX ou le numéro comporte une erreur. SpeedX conseille de vérifier le code auprès du marchand, car l'envoi peut avoir été confié à un autre livreur.

:::sources
- [SpeedX : Customer Service and Support](https://speedx.io/customer-service-and-support/) – préfixe SPX, glossaire, SMS, livraisons samedi et dimanche, signature à 21 ans, photos par code ZIP, consignes et changements d'adresse via Miles, pas de retrait en entrepôt, délai de 3 jours ouvrés sans scan, réclamations via le marchand, retours, téléphone, e-mail et horaires
- [SpeedX : Tracking](https://tracking.speedx.io/) – portail anglophone de suivi SpeedX
- [SpeedX : Tech-Enabled Last Mile Delivery](https://speedx.io/) – livraison du dernier kilomètre aux États-Unis, étiquettes créées par SpeedX, offres J+1 et J+2 et photos de remise
- [SpeedX : Cross-Border Shipping](https://speedx.io/cross-border-shipping/) – prise en charge dans les hubs aéroportuaires américains, courtage en douane propre et injection dans le réseau SpeedX
- [SpeedX : About Us](https://speedx.io/about-us/) – création en 2022 à New York et couverture urbaine aux États-Unis
- [SpeedX : Privacy Policy](https://speedx.io/privacy-policy/) – raison sociale Speed Xpress Inc. et siège à New Hyde Park
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats SPX reconnus, lecture des statuts, notes de tentative, message d'absence de données et confidentialité sur les liens partagés
:::
