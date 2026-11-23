---
title: Suivi Emirates Post : numéros AE et statuts expliqués
description: Suis un colis Emirates Post depuis ou dans les Émirats arabes unis : format AE, sens des statuts, douane UE et relais vers ta poste.
slug: suivi-emirates-post
published: 2026-11-22
updated: 2026-11-22
---

Emirates Post (بريد الإمارات) est la poste des Émirats arabes unis ; le catalogue de Peek la connaît aussi sous le nom Emirates Post Group. Ses numéros postaux se terminent par `AE` et son suivi indique le lieu de la plupart des étapes, aux Émirats arabes unis comme dans le pays de destination pour un envoi vers l’étranger. Colle le numéro dans le champ ci-dessus ou recherche-le sur le suivi d’Emirates Post à l’adresse emiratespost.ae. Peek n’ayant pas pu consulter le site web d’Emirates Post pour cette page, ce qui suit repose sur ce que le code de Peek sait de son suivi.

## Numéro de suivi Emirates Post : à quoi il ressemble

Un numéro postal Emirates Post compte 13 caractères au [format postal international](guide:tracking-number-formats) : deux lettres, neuf chiffres et `AE`, le code des Émirats arabes unis. Le dernier des neuf chiffres est un chiffre de contrôle. Avec des chiffres fictifs :

| Numéro (fictif) | À quoi il correspond |
| --- | --- |
| `EE482910372AE` | Commence par C, E, L ou R et finit par `AE`, avec un chiffre de contrôle valide : Peek sélectionne immédiatement Emirates Post. |
| `UA482910372AE` | Finit par `AE` mais commence par une autre lettre. Sa forme seule ne suffit pas à désigner Emirates Post, donc Peek affiche d’abord « Transporteur postal inconnu » (voir plus bas). |
| `EE482910371AE` | Même forme avec un chiffre de contrôle erroné. Peek ne reconnaît aucun transporteur : vérifie s’il y a une faute de frappe. |
| `7394028156342` | 13 chiffres. Peek peut les rechercher sur le suivi d’Emirates Post, mais d’autres transporteurs utilisent aussi 13 chiffres : sélectionne donc Emirates Post toi-même. |
| `RR482910372CN` | Se termine par d’autres lettres, ici `CN` : c’est une autre poste qui l’a émis, ici [China Post](carrier:china-post). |

Les espaces et minuscules ne comptent pas quand tu colles un numéro dans Peek. Tu ne trouves pas le tien ? Consulte [comment retrouver un numéro de suivi](guide:find-tracking-number).

## Statuts de suivi Emirates Post expliqués

Le suivi d’Emirates Post attribue à chaque étape une date, une heure et, la plupart du temps, un lieu. Voici les étapes connues de Peek, dans l’ordre habituel :

| Emirates Post indique | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Yet to be received` | Emirates Post a reçu les données de l’envoi, mais personne ne l’a encore déposé. Peut s’afficher plusieurs jours avant le dépôt. | « Étiquette créée » |
| `Received at counter` | Déposé au guichet d’une agence Emirates Post. | « Pris en charge » |
| `Received - shipment under processing`, `Shipment under Processing` | En cours de traitement après le dépôt. | « En transit » |
| `Item into sorting centre` | Dans un centre de tri. | « En transit » |
| `Shipped to` | En route vers le lieu nommé ensuite : bureau suivant, ville de destination à l’étranger ou étape suivante après la douane d’importation. | « En transit » |
| `Arrived at` | Arrivé au lieu nommé ensuite : centre de distribution ou pays de destination pour un envoi international. | « En transit » |
| `Item presented to export Customs/Security` | Contrôle douanier ou de sûreté à l’exportation avant le départ. | « À la douane » |
| `Item returned from export Customs/Security` | Libéré : le colis repart. | « En transit » |
| `Item presented to import Customs` | En douane dans le pays de destination. | « À la douane » |
| `Import terminated` | Le pays de destination a interrompu l’importation. D’autres étapes peuvent encore suivre. | « Problème signalé » |
| `Item held at delivery depot` | Le dépôt de livraison du pays de destination a le colis. Cette ligne n’indique pas encore qu’il est prêt au retrait. | « En transit » |
| `Ready for collection by customer` | Il t’attend pour être retiré. | « Prêt à être retiré » |
| `Out for delivery` | En cours de livraison. | « En livraison » |
| `Delivered` | Livré. | « Livré » |

Peek interprète toute autre étape selon son libellé et l’affiche avec les mots d’Emirates Post. Plus de détails dans [que signifient les statuts de suivi](guide:tracking-statuses).

## Envois Emirates Post vers l’étranger : livraison et douane

Une fois que l’envoi quitte les Émirats arabes unis, `Shipped to` indique sa destination. Le suivi d’Emirates Post peut ensuite continuer à afficher les étapes dans le pays de destination : arrivée, douane d’importation et dépôt de distribution. Dans de nombreux pays, Peek interroge aussi ta poste nationale avec le même numéro `AE`, comme [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post) ou [Australia Post](carrier:australia-post) (voir la dernière section). Les tentatives de livraison et le lieu de retrait dépendent alors de l’opérateur qui a le colis dans ton pays : voir [livraison manquée](guide:missed-delivery) et [colis non retiré](guide:uncollected-parcel).

**Douane dans l’UE.** Les Émirats arabes unis étant hors UE, un colis passe par la douane dans ton pays. Depuis le 1er juillet 2026, les achats en ligne hors UE dans des commandes jusqu’à 150 € supportent un droit de douane européen de 3 € par article. Depuis le 1er novembre 2026, des frais de traitement de l’Union de 2 € par article s’appliquent aussi, quelle que soit la valeur. Un article désigne les marchandises d’un colis partageant classement tarifaire, description et, le cas échéant, origine. Peek n’ayant pas pu vérifier comment ces frais sont perçus sur un envoi Emirates Post, demande à la boutique si ton prix les incluait. Ailleurs, les règles de ton pays s’appliquent : voir [colis bloqué en douane](guide:customs).

## Recevoir un envoi Emirates Post aux Émirats arabes unis

Les étapes d’Emirates Post nomment le bureau qui détient le colis, par exemple un centre de tri ou un centre de distribution. Lorsqu’il t’attend, la ligne indique `Ready for collection by customer`. Peek n’ayant pas pu consulter le site d’Emirates Post ni les règles douanières des Émirats sur une page officielle, cette page ne donne ni délai de garde, ni règles de livraison, ni droits de douane.

**Envoi depuis l’étranger.** Un colis posté vers les Émirats arabes unis porte le numéro de la poste d’origine. Peek le suit chez cette poste ou via des services de suivi et ne le transfère pas automatiquement à Emirates Post : il n’interroge Emirates Post sur ce numéro que si tu choisis Emirates Post avec « Changer ».

## Suivi Emirates Post bloqué, colis perdu ou retourné

**Silencieux après le départ des Émirats.** Le vol et la douane prennent du temps. Après quatre jours sans nouveau scan sur un envoi en « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche le jour du dernier scan (« Aucune mise à jour depuis … »). Vérifie aussi ta poste nationale avec le même numéro ; Peek le fait pour toi lorsque c’est possible. Plus de détails dans [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**Livré, mais rien reçu.** Adresse-toi au transporteur qui a effectué la livraison dans ton pays et préviens l’expéditeur. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Perdu ou renvoyé.** Contacte d’abord l’expéditeur ou la boutique : ce sont eux qui ont réservé l’envoi. Voir [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contact Emirates Post

Le site web d’Emirates Post répondant aux vérifications automatisées par un contrôle navigateur, cette page n’indique ni numéro de téléphone, ni e-mail, ni horaires d’ouverture : Peek ne publie que les coordonnées lues sur les pages officielles d’un transporteur.

Pour une commande en boutique, adresse-toi d’abord au vendeur. Une fois le colis pris en charge par la poste de ton pays, contacte cette poste pour la livraison.

## Suivre un colis Emirates Post avec Peek

[Peek](/) sélectionne aussitôt Emirates Post pour un numéro de deux lettres, neuf chiffres et `AE` commençant par C, E, L ou R avec un chiffre de contrôle valide, et affiche « Transporteur détecté » ; « Changer » permet d’en choisir un autre. Si le chiffre de contrôle est faux, il affiche « Le chiffre de contrôle de ce numéro ne correspond pas. » : vérifie le numéro ou choisis « Suivre tel quel ». Un lien vers le suivi d’Emirates Post fonctionne aussi : Peek en extrait le numéro. Classé sous Emirates Post, le colis affiche « En attente de Emirates Post » jusqu’à ce qu’Emirates Post le référence.

Un numéro `AE` commençant par une autre lettre, ou un numéro à 13 chiffres, ne suffit pas par sa forme à identifier Emirates Post. Colle-le tout de même : Peek suit plus de 4 500 transporteurs. Pour cet `AE`, Peek affiche « Transporteur postal inconnu », interroge d’abord [Chronopost](carrier:chronopost) (s’il le connaît, Peek le classe sous Chronopost), puis affiche « Vérification des services de suivi… » ; si aucun service n’a le colis, il interroge Emirates Post et affiche « Emirates Post a ce colis. » si elle le trouve. Si un service l’a trouvé, Peek le suit là-bas ; pendant les 30 premiers jours du colis, les vérifications ultérieures peuvent encore interroger Emirates Post et y transférer le colis dès qu’Emirates Post le montre en mouvement. Pour un numéro à 13 chiffres, ou pour aller directement sur le suivi d’Emirates Post, choisis Emirates Post avec « Changer » (ou « Changer de transporteur » après ajout).

Le suivi d’Emirates Post plaçant un contrôle navigateur devant sa page, Peek le lit via un navigateur. Peek affiche chaque étape dans le texte anglais d’Emirates Post (ou en arabe en l’absence d’anglais) avec le lieu, sauf `Out for delivery` et `Delivered`, qu’il affiche « En livraison » et « Livré ». Il laisse de côté les coordonnées de l’expéditeur et du destinataire, les informations de contact, les références de suivi et les liens d’événements. Le suivi ne fournissant ni date de livraison prévue ni poids, Peek n’en affiche pas. Peek lit les étapes à Dubaï, Abou Dabi, Charjah, Ajman, Fujaïrah, Oumm al-Qaïwaïn et Ras al-Khaïmah à l’heure des Émirats arabes unis, et les étapes ailleurs sur l’horloge locale du lieu nommé ou des étapes voisines (celle du pays de destination dès l’arrivée), puis convertit toutes les heures dans ton fuseau horaire (« Heures dans ton fuseau horaire : »). « Ouvrir le site Emirates Post » sur la fiche du colis ouvre le numéro sur le suivi d’Emirates Post. Lorsque le suivi est inaccessible, Peek interroge des services de suivi — ParcelsApp, Ship24 et 17TRACK, sans ordre fixe, puis le suivi de l’Union postale universelle en dernier pour un numéro `AE`.

Quand les étapes d’Emirates Post nomment le pays de destination, ou n’en nomment aucun et que tu fais le suivi hors des Émirats arabes unis, Peek interroge aussi la poste de ce pays avec le même numéro `AE` : [USPS](carrier:usps), [Royal Mail](carrier:royal-mail) (ou [Parcelforce](carrier:parcelforce) pour un envoi EMS commençant par E), An Post, [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post), NZ Post, [DHL](carrier:dhl) en Allemagne, [La Poste](carrier:la-poste) en France et la poste de chaque autre pays de l’UE sauf les Pays-Bas, ainsi que celles de Suisse, Norvège, Arabie saoudite, Pakistan, Philippines, Égypte, Jordanie, Kenya et Bangladesh, entre autres. Dès que cette poste montre un scan au moins aussi récent que le dernier d’Emirates Post, Peek suit le colis là-bas, garde l’historique d’Emirates Post et la carte affiche par exemple « Livraison par USPS ». Dans certains pays (Pays-Bas, Inde, Sri Lanka, Chine, Oman, Koweït, Bahreïn), Peek n’interroge aucune poste : il reste alors sur Emirates Post. Un colis encore affiché comme « Transporteur postal inconnu » bénéficie de ce relais uniquement si un service de suivi qui le suit indique le pays de destination.

Tant que tu as le colis ouvert dans Peek, ou le jour de sa livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes, et jusqu’à toutes les 5 minutes à partir de 6 h sur l’horloge du dernier scan le jour où le colis passe « En livraison », lorsque le suivi d’Emirates Post répond (chaque lecture de ce suivi passe par un navigateur) ; sinon, toutes les heures. Une fois le relais passé à la poste de ton pays, Peek suit la fréquence de cette poste. Il vérifie toutes les heures après deux jours sans nouvelle (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge ») ; après sept jours sans nouvelle sur « Étiquette créée », toutes les 6 heures. Pour les notifications, tu peux choisir « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après quatre jours sans scan sur un envoi en « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche « Aucune mise à jour depuis … », rappelle « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Emirates Post. » et propose « Demande de l’aide à Emirates Post ». Peek ne peut pas contacter Emirates Post, ta poste ni la boutique à ta place.

## Questions sur le suivi Emirates Post

### À quoi ressemble un numéro de suivi Emirates Post ?

Deux lettres, neuf chiffres et `AE`, par exemple `EE482910372AE`. Peek sélectionne immédiatement Emirates Post quand le numéro commence par C, E, L ou R et que le chiffre de contrôle est valide.

### Pourquoi mon suivi Emirates Post reste-t-il sur « Yet to be received » ?

Emirates Post a reçu les données de l’envoi, mais le colis n’a pas encore été déposé au guichet : ce statut peut apparaître plusieurs jours avant le dépôt. Demande à l’expéditeur quand il le postera.

### Que signifie « Import terminated » sur le suivi Emirates Post ?

Le pays de destination a arrêté l’importation de l’envoi. Peek l’affiche sous « Problème signalé ». D’autres étapes peuvent encore suivre : continue de surveiller et demande à l’expéditeur ou à la boutique ce qui s’est passé.

### Puis-je suivre un colis Emirates Post auprès de la poste de mon pays ?

Dans de nombreux pays, oui, avec le même numéro `AE`. Pour un colis suivi sur Emirates Post, Peek interroge ta poste aux États-Unis, au Royaume-Uni, en Irlande, au Canada, en Australie, dans tous les pays de l’UE sauf les Pays-Bas et dans d’autres encore, puis bascule dessus dès qu’elle affiche un scan au moins aussi récent que le dernier d’Emirates Post.

:::sources
- [European Commission : EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane UE de 3 € par article jusqu’à 150 € depuis le 1er juillet 2026, frais de traitement de l’Union de 2 € depuis le 1er novembre 2026
- [European Commission : Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € par article quelle que soit la valeur, définition d’un article
- [Universal Parcel Scraper : Emirates Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/emirates-post) – noms, formats et chiffre de contrôle, numéros à 13 chiffres, lecture par navigateur, statuts et étapes, lieux, horloges, destination, champs écartés, liens de suivi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – numéros China Post, transporteurs interrogés pour d’autres `AE`, ordre des services de suivi, relais vers la poste de destination, aucun relais vers Emirates Post
:::
