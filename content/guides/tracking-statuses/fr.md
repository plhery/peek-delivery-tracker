---
title: Étapes du suivi d'un colis : que veut dire chaque statut ?
description: Ce que veut dire chaque étape du suivi d'un colis, de « pris en charge » à « livré », chez Mondial Relay, bpost ou La Poste suisse, et quand agir.
slug: etapes-suivi-colis-signification
picture: Une route sinueuse aux étapes cochées (étiquette, dépôt, avion), une camionnette à l'étape en cours, une maison plus loin et Pip qui file.
published: 2026-10-09
updated: 2026-10-27
---

Un statut de suivi résume en quelques mots le dernier scan du transporteur, et la plupart ne te demandent rien. « En préparation chez l'expéditeur » veut dire que le transporteur n'a pas encore ton colis, « en cours d'acheminement » qu'il est quelque part entre deux scans, et « en cours de livraison » qu'il est dans la camionnette du jour. Seuls quelques-uns demandent une action de ta part : un avis de passage, un colis en instance, des frais de douane, ou un « livré » alors que rien n'est arrivé.

## Quelles sont les étapes du suivi d'un colis ?

Tous les colis passent par les mêmes étapes, et un colis venu de l'étranger en ajoute trois.

:::journey
- label | Étiquette créée | Le transporteur a les données, pas le colis.
- warehouse | En transit | Pris en charge, puis de centre de tri en centre de tri, sans scan sur la route.
- plane | Départ du pays | Depuis l'étranger. Aucun scan en vol.
- customs | Douane | Hors UE, ou à l'entrée en Suisse. Parfois des frais à payer.
- handover | Transporteur local | Depuis l'étranger. Ses scans ne s'affichent parfois que sur son site.
- truck | En cours de livraison | Dans la camionnette du jour.
- locker | En instance | Au bureau de poste, en point relais ou en consigne.
- home | Livré | Remis, ou déposé là où le transporteur estime que c'est chez toi.
:::

## Que veulent dire les statuts de Mondial Relay et de La Poste suisse ?

Mêmes étapes, autres mots. Pour [Mondial Relay](carrier:mondial-relay), ce sont les libellés relevés dans de vrais suivis.

| Étape | Mondial Relay | La Poste suisse |
| --- | --- | --- |
| Étiquette créée | `Colis en préparation chez l'expéditeur` | `Transmission des données par l'expéditeur` |
| Pris en charge | `Prise en charge` | `Moment du dépôt de l'envoi` |
| En transit | `Colis en cours de traitement sur le site` | `L'envoi a été trié en vue de son acheminement` |
| En cours de livraison | `Colis en route vers le point de livraison` (vers le Point Relais, pas chez toi) | `L'envoi a été trié en vue de sa Distribution` (encore au centre de tri) |
| En instance | `Disponible dans votre Point Relais` | `Arrivée à l'office de retrait / à l'office de distribution` |
| Livré | `Remis au destinataire` | `Distribué par` |

## « En préparation chez l'expéditeur » ou « étiquette créée » : le colis est-il parti ?

Non : le vendeur a créé une étiquette et transmis les données, rien de plus. Chez [FedEx](carrier:fedex), cette étape s'intitule en anglais `Shipment information sent to FedEx`, et le premier scan d'[UPS](carrier:ups), `Shipper created a label, UPS has not received the package yet.`, dit qu'UPS n'a pas encore le colis. À [La Poste suisse](carrier:swiss-post), `Transmission des données par l'expéditeur` veut dire que l'envoi ne lui a normalement pas encore été remis ; chez [Colis Privé](carrier:colis-prive), `Colis attendu par Colis Privé`, qu'il n'a pas encore reçu le colis.

Chez [bpost](carrier:bpost), `en préparation` a deux sens : soit l'expéditeur prépare encore le colis, soit bpost l'a déjà mais doit encore le trier. Selon bpost, la plupart des colis sont traités dans les 5 jours après que l'expéditeur l'a choisi.

Si rien ne bouge, demande au vendeur, pas au transporteur : Colis Privé te renvoie vers le site du vendeur, et chez bpost, seul l'expéditeur peut faire ouvrir un dossier.

## Que veut dire « en cours d'acheminement » ou « en transit » ?

Le transporteur a ton colis, quelque part dans son réseau. Ça ne veut pas dire qu'il roule : une ligne comme `L'envoi a été trié en vue de son acheminement` (La Poste suisse) est un passage en centre de tri.

Entre deux centres, le silence est normal : en Belgique, Mondial Relay te propose de le contacter si le colis n'est pas arrivé au Point Relais une fois passé son délai moyen.

### Combien de temps un colis reste-t-il en transit ?

En Belgique, Mondial Relay compte en moyenne 3 jours ouvrés après la prise en charge pour une livraison en Point Relais, et 3 à 6 à l'international. Le délai annoncé est dépassé et rien ne bouge ? Lis [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).

## Colis venu de l'étranger : départ, douane, transporteur local

- **Départ du pays.** Le colis prend l'avion, le bateau ou le train, sans scan en route. La Poste suisse affiche `Arrivée au poste frontalier dans le pays de destination` : le colis est là, mais doit peut-être encore passer la douane. Et `Date de distribution inconnue` veut simplement dire que l'envoi vient de l'étranger ou y part, ou qu'il n'est pas arrivé comme annoncé.
- **Douane.** Chez [La Poste](carrier:la-poste), le suivi indique que les formalités d'import/export sont en cours, puis qu'elles sont terminées, ou qu'elles sont suspendues faute de facture. En Suisse, tu verras `L'envoi a été remis à la douane`, `En cours de dédouanement`, puis `Libéré par la Douane suisse` : le colis part alors vers la distribution. La Poste suisse compte trois à quatre jours ouvrés si les informations sont complètes. Aucun de ces transporteurs ne donne de durée maximale. N'agis que si on te le demande, pour payer des frais ou fournir un document ; lis [colis bloqué en douane](guide:customs).
- **Transporteur local.** Une société fait le long trajet, une autre le dernier kilomètre. Pour un envoi international, le suivi de La Poste peut indiquer le nom de ce partenaire et la référence du colis chez lui : si le suivi s'arrête, cherche le numéro chez celui qui livre. Commande AliExpress, Temu ou Shein ? Lis [suivre un colis venu de Chine](guide:tracking-from-china). Les lignes en anglais de [Cainiao](carrier:cainiao) et de [YunExpress](carrier:yunexpress) sont traduites sur leurs pages.

## « En cours de livraison » : le colis arrive-t-il aujourd'hui ?

En général, oui. Chez bpost, l'app My bpost te laisse changer tes préférences de livraison jusqu'à 5 minutes avant la livraison.

Deux statuts prêtent à confusion. Chez La Poste, `Votre colis est sur son site de distribution. Nous le préparons pour le mettre en livraison.` a l'air d'une attente, mais c'est le tri pour la tournée du jour : Peek affiche « En livraison », et dans les suivis qu'il lit, la livraison ou une tentative suit le jour même. Et `L'envoi a été trié en vue de sa Distribution` (La Poste suisse) est un tri en centre de traitement, pas la camionnette.

Toujours rien le soir ? Regarde si la date a bougé : chez [Chronopost](carrier:chronopost), `Livraison reportée de 24h` veut dire que l'agence garde le colis un jour sans tenter de le livrer, puis reprend la livraison d'elle-même.

[Peek](/) vérifie un colis jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes sur le dernier kilomètre. Tu peux régler ses notifications sur « Jour de livraison uniquement ».

## « Destinataire absent » ou « avis de passage » : que faire ?

Là, il faut agir : [personne n'a pu prendre le colis](guide:missed-delivery), ou le livreur n'a pas pu entrer.

:::steps
- Lis l'avis | Papier, SMS ou e-mail : où est parti le colis, et [le numéro à donner](guide:find-tracking-number).
- Vérifie le délai | De 5 à 15 jours selon le transporteur et le lieu, souvent en jours calendaires.
- Reprogramme ou redirige | Sur le site ou l'app du transporteur : autre jour, voisin, point relais.
- Va le chercher | Avec une pièce d'identité et l'avis ou le code-barres.
:::

| Transporteur | Après une livraison manquée | Délai de retrait |
| --- | --- | --- |
| Colis Privé (FR) | Soit un relais proche (adresse et date par e-mail ou SMS), soit une seconde présentation à choisir : chez toi, chez un tiers ou en relais | Pas de délai chiffré dans son aide |
| Mondial Relay (FR, BE) | Livraison en Point Relais ou en Locker | 5 jours (calendaires en Belgique, dimanches et jours fériés compris), sans prolongation |
| bpost (BE) | Avis papier seulement si bpost ne peut pas te joindre par e-mail ou dans l'app | 14 jours calendaires en Point d'enlèvement, 5 en distributeur de colis |
| La Poste suisse (CH) | `Annoncé pour retrait (invitation à retirer un envoi)` ; avec un login « Mes envois », deuxième distribution (gratuite devant la porte ou chez un voisin, CHF 3.30 à l'étage, CHF 7.– le samedi) ou réexpédition (CHF 8.– en semaine) | 7 jours en général, 15 pour les envois internationaux ; prolongation gratuite, sans compte |

> Des escrocs se font passer pour Mondial Relay, Colissimo, Chronopost ou UPS, par SMS ou par e-mail, prévient Cybermalveillance.gouv.fr : au moindre doute, vérifie le vrai suivi du colis ou contacte directement le transporteur. En France, signale le SMS au 33700, ou l'e-mail à abuse@chronopost.fr s'il imite Chronopost. Ne reprogramme une livraison que sur le site ou l'app du transporteur.

## « En instance » ou « disponible en point de retrait » : où est le colis ?

Il t'attend au bureau de poste, en point relais ou en consigne, [jusqu'à la fin du délai](guide:uncollected-parcel) indiqué dans le tableau ci-dessus. Le suivi de La Poste dit alors que le colis « vous attend dans votre point de retrait », et Mondial Relay affiche les jours restants pour le retirer. Chez UPS, `Delivered to UPS Access Point™` veut dire qu'il t'attend au relais, pas chez toi.

Attends ce statut ou le message du transporteur avant d'y aller : bpost demande de vérifier l'état du colis en ligne avant d'aller au Point d'enlèvement. À La Poste suisse, `Arrivée à l'office de retrait / à l'office de distribution` donne l'heure à partir de laquelle tu peux le retirer, et le point de retrait figure sur l'invitation à retirer un envoi.

## « Livré » : l'as-tu vraiment reçu ?

Pas toujours. Ça veut dire que le livreur l'a scanné comme remis ou déposé :

- **Dans la boîte aux lettres ou la boîte à lait.** La Poste suisse écrit `Distribué dans le compartiment annexe/la boîte aux lettres` : le compartiment annexe, c'est la boîte à lait.
- **Dans un lieu sûr ou chez un voisin.** bpost le fait si tu l'as choisi dans tes préférences. La page d'UPS peut indiquer où le colis a été laissé (par exemple `Left at: Front Door`).
- **Au guichet.** `Remis au guichet` (La Poste suisse) : dans une filiale, une filiale en partenariat ou un point PickPost.

Sur le suivi en anglais de [Planzer](carrier:planzer), qui sert aussi aux colis [Quickpac](carrier:quickpac), `Shipped` veut dire livré, pas expédié. Rien à la porte ? Cherche dans le suivi complet une photo, un nom ou un lieu, puis lis [colis livré mais non reçu](guide:delivered-not-received).

## « Anomalie » ou « exception » : le colis est-il perdu ?

Non. Chez UPS, `Exception` est le statut générique, et il peut ne signaler qu'un simple retard : lis le scan en dessous.

Chez La Poste, Peek affiche « Problème signalé » quand le scan parle d'incident, d'anomalie, d'avarie, de colis endommagé ou refusé, ou d'adresse incorrecte. Et `Erreur d'acheminement` (La Poste suisse) signale un envoi parti au mauvais endroit, par exemple à cause d'un NPA (code postal) erroné : il est réacheminé, avec un retard possible.

Regarde de plus près quand le problème touche l'adresse, comme `Tentative de distribution, adresse incorrecte` à La Poste suisse : chez La Poste aussi, le suivi cite l'adresse incorrecte parmi les raisons d'un retour.

## Pourquoi mon colis est-il retourné à l'expéditeur ?

Les raisons que donnent les transporteurs : colis non retiré à temps, adresse incorrecte, destinataire introuvable ou déménagé, refus, documents d'importation non conformes. Certains retours ne se reconnaissent pas au premier coup d'œil :

- **Mondial Relay :** un colis non retiré dans le délai repart automatiquement vers l'expéditeur.
- **Chronopost :** `Colis en retour expéditeur` ouvre le retour ; si le suivi affiche ensuite `Livraison effectuée`, c'est l'expéditeur qui a reçu le colis.
- **La Poste suisse :** `Renvoi`, avec le motif : `Envoi non retiré`, `Refusé`, `Destinataire introuvable à l'adresse indiquée` ou `Destinataire a déménagé, délai de réexpédition expiré`.

Et ensuite ? Contacte l'expéditeur (le site marchand) pour [un nouvel envoi ou un remboursement](guide:returned-to-sender).

:::sources
- [Universal Parcel Scraper : La Poste / Colissimo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – tri pour la tournée, formalités d'import/export, partenaire à l'étranger, point de retrait, motifs de retour, ce que Peek signale
- [Universal Parcel Scraper : libellés Chronopost](https://github.com/plhery/universal-parcel-scraper/blob/main/carriers/chronopost/statuses.json) – « Livraison reportée de 24h », retour à l'expéditeur
- [Cybermalveillance.gouv.fr : L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – transporteurs imités, vrai suivi, 33700, abuse@chronopost.fr
- [Colis Privé : suivre mon colis](https://colisprive.fr/aide/comment-suivre-mon-colis-3/) – colis attendu
- [Colis Privé : avis de passage](https://colisprive.fr/aide/jai-eu-un-avis-de-passage/) – relais, seconde présentation
- [Mondial Relay Belgique : délai moyen de livraison](https://www.mondialrelay.be/fr-be/faq/envoyer-un-colis/quel-est-le-delai-moyen-de-livraison/) – 3 jours ouvrés en Belgique, 3 à 6 à l'international
- [Mondial Relay Belgique : délai de garde en Locker ou Point Relais](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/quel-est-le-d%C3%A9lai-de-garde-dun-colis-en-locker-ou-point-relais/) – 5 jours en France et en Belgique, sans prolongation, retour à l'expéditeur
- [Mondial Relay Belgique : retard de livraison](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/que-faire-en-cas-de-retard-de-livraison/) – contact après le délai moyen
- [Universal Parcel Scraper : libellés Mondial Relay](https://github.com/plhery/universal-parcel-scraper/blob/main/carriers/mondial-relay/statuses.json) – libellés relevés dans des suivis
- [bpost : colis « en préparation »](https://www.bpost.be/fr/faq/mon-colis-reste-en-preparation-quest-ce-que-cela-signifie-et-que-puis-je-faire) – deux sens, 5 jours
- [bpost : Point d'enlèvement](https://www.bpost.be/fr/faq/combien-de-temps-mon-colis-reste-t-il-disponible-au-point-denlevement) – 14 et 5 jours
- [bpost : pas d'avis de passage](https://www.bpost.be/fr/faq/je-nai-pas-recu-davis-de-passage-ou-et-comment-puis-je-recuperer-mon-colis) – avis papier, statut
- [bpost : préférences de livraison](https://www.bpost.be/fr/faq/je-ne-serai-pas-la-maison-comment-selectionner-mes-preferences-de-livraison) – lieu sûr, voisin, 5 minutes
- [bpost : Track & Trace](https://www.bpost.be/fr/faq/je-ne-recois-pas-de-nouvelles-mises-jour-track-trace-est-ce-normal) – seul l'expéditeur
- [La Poste suisse : événements du suivi](https://www.post.ch/fr/reception/suivi-des-envois/evenements-suivi-des-envois) – libellés
- [La Poste suisse : invitation à retirer](https://www.post.ch/fr/reception/invitation-a-retirer) – délais, tarifs
- [Universal Parcel Scraper : UPS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ups) – scans d'UPS que lit Peek : étiquette créée, Access Point, « Left at », « Exception »
- [Universal Parcel Scraper : FedEx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/fedex) – scans de FedEx que lit Peek : informations d'envoi transmises à FedEx
- [La Poste suisse : FAQ Importation](https://www.post.ch/fr/solutions-commerciales/exportation-importation-et-dedouanement/importation/faq-importation-droits-de-douane-et-tva) – trois à quatre jours ouvrés
- [Peek : page d'accueil](https://peektracker.com/fr) – jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes sur le dernier kilomètre
:::
