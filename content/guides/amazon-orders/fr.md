---
title: Suivi de commande Amazon : Amazon, vendeurs, transporteurs
description: Qui expédie ta commande Amazon, quel transporteur la livre, pourquoi le numéro de suivi change ou manque, et quand passer par le vendeur ou la garantie A à Z.
slug: suivi-commande-amazon
picture: Un téléphone affiche l'avancée d'une commande, deux camionnettes se passent un colis, et Pip regarde.
published: 2026-10-16
updated: 2026-11-14
---

Pour suivre une commande Amazon, connecte-toi à ton compte sur amazon.fr ou ouvre l'application Amazon, puis va dans Vos commandes : c'est là que tu vérifies l'état de tes commandes. La page du service client d'Amazon donne aussi accès au suivi de tes colis. Qui t'aide en cas de souci dépend de qui expédie. Amazon s'occupe de ses propres commandes et des articles « Expédié par Amazon », alors que pour un vendeur qui expédie lui-même, c'est d'abord lui que tu contactes.

## Qui expédie ma commande Amazon : Amazon ou un vendeur tiers ?

Une commande Amazon entre dans l'un de ces trois cas :

| Qui vend, qui expédie | Ce que ça veut dire | Qui t'aide pour la livraison |
| --- | --- | --- |
| Amazon vend et expédie | Le stock d'Amazon | Amazon |
| Un vendeur vend, Amazon expédie | « Expédié par Amazon » : le vendeur confie son stock à Amazon, qui le stocke, l'emballe et l'expédie | Amazon, qui gère aussi le service client et les retours |
| Le même vendeur vend et expédie | Le vendeur expédie lui-même et, dans l'UE, répond de la livraison | Le vendeur d'abord |

**En Belgique**, amazon.com.be, ouvert en 2022, propose un service client en français, en néerlandais ou en anglais.

**En Suisse**, tu peux commander sur amazon.de, ou sur amazon.fr pour faire tes achats en français. Sur amazon.de, ce qu'Amazon expédie arrive en 3 à 5 jours ouvrables en livraison standard, 1 à 3 en livraison express, et le prix total affiché au paiement comprend la TVA suisse, les éventuels droits de douane et les frais de port. Un vendeur qui n'expédie pas via Amazon peut faire exception : des frais de douane peuvent s'ajouter s'il ne les a pas inclus dans son prix. Plus de détails : [colis bloqué en douane](guide:customs).

## Quel transporteur livre mon colis Amazon ?

Quand Amazon livre lui-même, c'est [Amazon Logistics](carrier:amazon-logistics), son propre réseau : en France, des entreprises de livraison indépendantes travaillent avec les agences de livraison de proximité d'Amazon. Amazon confie aussi des colis au groupe La Poste, son partenaire historique, et à d'autres spécialistes de la livraison du dernier kilomètre, qui livrent avec leurs propres réseaux. Voici les transporteurs qu'Amazon cite pour chaque pays :

| Pays | Boutique | Transporteurs cités par Amazon |
| --- | --- | --- |
| France | amazon.fr | Amazon et ses entreprises de livraison partenaires, le groupe [La Poste](carrier:la-poste), et en points de retrait [Mondial Relay](carrier:mondial-relay), [Relais Colis](carrier:relais-colis) et La Poste |
| Belgique | amazon.com.be | Amazon et ses partenaires de livraison locaux, [bpost](carrier:bpost) |
| Suisse | amazon.de, amazon.fr | [La Poste suisse](carrier:swiss-post), qui livre avec son propre réseau |

Sur amazon.fr, la page d'aide « Coordonnées des transporteurs » en cite davantage. Les principaux transporteurs qu'elle nomme pour la France métropolitaine sont Amazon, [Chronopost](carrier:chronopost), [Colis Privé](carrier:colis-prive), DHL France, dont les colis sont livrés par La Poste, [DPD France](carrier:dpd-fr), France Express (Geodis), [GLS](carrier:gls-fr), La Poste Colissimo, Mondial Relay, [Paack](carrier:paack), [UPS](carrier:ups) et JP Home (VIR et Agediss). Pour savoir lequel a ton colis, ouvre Vos commandes et choisis « Suivre le colis » : tu y trouves le nom du transporteur et le numéro de suivi. La même page donne le lien de suivi de chacun et, pour la plupart, un numéro de téléphone : en cas de problème de livraison, Amazon conseille de contacter directement le transporteur. Au lieu de ton domicile, tu peux aussi choisir une consigne Amazon Locker en commandant : il y en avait plus de 4 500 en France début 2025, la plupart accessibles 24 h/24 et 7 j/7. Ça ne coûte rien de plus, mais tous les articles n'y ont pas droit, notamment à cause du poids ou de la taille du colis.

Quand un vendeur expédie lui-même, son transporteur peut être l'un de ceux-ci ou un autre. Pour retrouver le numéro de suivi : [trouver ton numéro de suivi](guide:find-tracking-number).

## À quoi ressemble un numéro de suivi Amazon ?

Il y en a deux sortes, plus un numéro de commande qui n'est ni l'un ni l'autre.

Voici les formes que Peek reconnaît comme celles d'Amazon, ici avec des chiffres inventés :

:::anatomy TBA 123456789012
- TBA | Lettres d'Amazon. `TBC` et `TBM` fonctionnent pareil
- 123456789012 | Douze chiffres
:::

| Forme (inventée) | Ce que c'est |
| --- | --- |
| `TBA123456789012` | Amazon : `TBA`, `TBC` ou `TBM` et 12 chiffres |
| `FR9999999981` | Amazon en Europe : un code pays (`FR`, `BE`, `CH` et d'autres) et 10 chiffres |
| `123-1234567-1234567` | Ton numéro de commande Amazon : 3, 7 et 7 chiffres. Pas un numéro de suivi |
| Tout le reste | Le numéro du transporteur, par exemple de Colissimo ou de Chronopost |

Le numéro d'un transporteur marche sur le site de celui-ci, comme expliqué dans [les formats de numéros de suivi](guide:tracking-number-formats). Tu ne sais pas lequel tu as ? Tape-le ici :

:::checker
:::

## Pourquoi mon numéro de suivi Amazon a changé, ou pourquoi j'en ai deux ?

Deux cas peuvent l'expliquer :

- **Un autre transporteur prend le relais.** Pour la Suisse, par exemple, Amazon remet les colis à La Poste suisse, qui les livre avec son propre réseau.
- **Le vendeur expédie lui-même.** Le numéro est alors celui de son transporteur, Colissimo, Mondial Relay ou un autre, pas un numéro Amazon.

:::journey
- warehouse | Amazon ou le vendeur | Un article « Expédié par Amazon » part d'un centre de distribution Amazon, les autres de chez le vendeur
- label | Étiquette créée | L'état de la commande se vérifie dans Vos commandes, sur amazon.fr ou dans l'application
- handover | Transporteur partenaire | Peut prendre le relais, par exemple La Poste suisse pour un colis envoyé en Suisse
- truck | En cours de livraison | Un livreur d'une entreprise partenaire d'Amazon, de La Poste ou du transporteur du vendeur
- home | Toi | À domicile, dans un Amazon Locker ou dans un point de retrait partenaire
:::

Quand le premier transporteur nomme le partenaire qui prend le relais, [Peek](/) garde le premier historique, suit le partenaire dès que son suivi montre du nouveau, et affiche sur la carte le nom et le numéro du partenaire.

## Pourquoi je n'ai pas de suivi pour ma commande Amazon ?

Deux raisons possibles :

- **Amazon Logistics livre le colis.** Il n'a pas de page de suivi publique : il se suit dans ton compte, dans Vos commandes, sur la boutique où tu as commandé.
- **Le vendeur expédie lui-même.** Le suivi dépend alors de lui et de son transporteur, pas d'Amazon.

Pas de suivi du tout sur une commande de vendeur ? Écris d'abord au vendeur ; si le colis n'arrive pas et qu'il ne règle pas le problème, la garantie A à Z te permet de demander un remboursement à Amazon. Quand le suivi reste figé plusieurs jours : [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).

## En combien de temps arrive une commande Amazon ?

Pour les articles expédiés par Amazon en France métropolitaine et à Monaco, amazon.fr publie ces délais. Les options proposées au moment de commander dépendent de l'article et de l'adresse de livraison :

| Option | Délai publié par amazon.fr |
| --- | --- |
| Livraison standard | 3 à 5 jours ouvrés, entre 8 h et 21 h |
| Livraison prioritaire | Sous 3 jours ouvrés, entre 8 h et 22 h du lundi au samedi et entre 9 h et 22 h le dimanche dans les zones éligibles ; le jour même si tu commandes avant l'heure limite indiquée sur la page du produit, quand c'est possible |
| Le jour même ou le lendemain | Avant 22 h le jour même ou avant 13 h le lendemain, dans de nombreuses villes de France (dont Paris, Lyon et Marseille) et en Belgique (dont Bruxelles) |
| Mon Jour Amazon, pour les membres Prime | Le jour de la semaine que tu choisis |
| Livraison à la carte, pour les membres Prime | Le jour que tu choisis dans les 7 jours qui suivent |
| Article lourd ou volumineux (plus de 23 kg, ou plus de 175 cm sur un côté) | Si la livraison planifiée t'est proposée, une date dans les 14 jours, sur une journée entière ou sur un créneau plus court, par exemple de 4 heures |

Pour l'instant, amazon.fr prévient qu'à cause de retards, un jour de plus peut s'ajouter à la livraison prioritaire. Pour un article expédié par un vendeur Marketplace, les délais et les frais peuvent être différents.

## Que faire si ma commande Amazon est en retard ou dite livrée ?

Dans l'Union européenne, le commerçant doit te livrer dans les 30 jours, sauf si vous avez convenu d'un autre délai, et c'est lui qui est responsable de la livraison. Procède dans cet ordre :

:::steps
- Vérifier la commande | Ouvre-la dans Vos commandes, sur amazon.fr ou dans l'application, pour voir son état et l'adresse de livraison choisie : domicile, Amazon Locker ou point de retrait. Dans la plupart des consignes Amazon Locker, le casier s'ouvre avec le code à six chiffres de l'e-mail de confirmation de livraison, sinon avec un code-barres ou l'application ; tu peux transférer cet e-mail à un proche pour qu'il retire le colis. Tu as trois jours calendaires : passé la date indiquée dans l'e-mail, Amazon renvoie le colis et te rembourse.
- Chercher autour | Regarde ta boîte aux lettres et l'endroit où tu reçois d'habitude tes colis, et demande aux voisins.
- Contacter le vendeur | S'il a vendu et expédié l'article, écris-lui d'abord. C'est à lui de prouver que l'article a été livré ; sinon, il doit t'envoyer un article identique ou te rembourser.
- Contacter Amazon | Pour une commande expédiée par Amazon, ou si le vendeur ne règle pas le problème : service client, par chat ou par téléphone de 6 h à minuit, 7 j/7, ou rappel sur demande ; à toute heure, un assistant virtuel répond par chat et peut passer la conversation à un conseiller.
- Faire valoir tes droits | Pas de livraison dans les 30 jours ou à la date convenue ? Accorde au commerçant un délai supplémentaire raisonnable ; s'il ne livre toujours pas, tu peux mettre fin au contrat et être remboursé.
:::

Plus de détails sur chaque cas : [colis livré mais pas reçu](guide:delivered-not-received) et [colis perdu](guide:lost-parcel).

### Quand demander la garantie A à Z ?

Elle couvre les articles qu'un vendeur partenaire a vendus et expédiés lui-même. Si la livraison ou l'état de l'article ne te convient pas et que tu n'as pas pu régler le problème directement avec le vendeur, tu peux demander un remboursement à Amazon.

| Problème | Ce que prévoit la garantie A à Z |
| --- | --- |
| Livraison en retard | Remboursement ou remplacement |
| Article reçu en mauvais état | Remboursement ou remplacement |
| Dommage matériel ou corporel causé par un produit défectueux | Couvert en France depuis mai 2024 : Amazon traite la réclamation avec le vendeur et son assureur |

Pour un dommage, tu fais la réclamation auprès du service client d'Amazon, qui prévient le vendeur.

## Combien de temps pour retourner une commande Amazon ?

Pour un article vendu ou expédié par Amazon, amazon.fr publie ces délais :

- **14 jours après la réception**, quel que soit le motif : c'est ton droit légal de rétractation, avec le remboursement complet, prix et frais d'expédition. Le jour de la livraison ne compte pas, et un délai qui se termine un samedi, un dimanche ou un jour férié passe au jour ouvrable suivant. Certains produits en sont exclus, comme les denrées périssables.
- **30 jours après la réception** pour les produits éligibles, signalés par la mention « Retours sous 30 jours et garanties légales » sous le bouton « Acheter maintenant » : du 15e au 30e jour, Amazon rembourse le prix, sans les frais d'expédition. Les produits Amazon Haul n'y ont pas droit.
- **Pour les fêtes 2026**, la plupart des articles achetés entre le 1er novembre et le 25 décembre 2026 peuvent être retournés jusqu'au 31 janvier 2027, ou dans les 30 jours après la réception si cette date tombe plus tard.

Le retour se demande dans Vos commandes, avec « Retourner ou remplacer des articles », puis tu as 14 jours pour renvoyer l'article. Depuis la France, tu le déposes dans un bureau de poste, dans un point de collecte Mondial Relay participant ou à une borne de retour Amazon, dans certains magasins Monoprix. Pas dans un Amazon Locker : il sert aux livraisons et aux retraits, pas aux retours. Pour un article expédié par un vendeur Marketplace, ce sont les conditions de retour des articles Marketplace qui s'appliquent.

## Est-ce que je peux suivre un colis Amazon ailleurs que sur Amazon ?

Ça dépend du numéro. Colle-le dans [Peek](/) :

- **Le numéro d'un transporteur** (Colissimo, Chronopost, Mondial Relay, bpost, La Poste suisse…) se suit comme n'importe quel colis.
- **Un numéro Amazon**, ou un lien track.amazon : Peek interroge d'abord le suivi public d'Amazon. S'il répond, le colis voyage avec [Amazon Shipping](carrier:amazon-shipping), ou Amazon l'expédie pour un vendeur qui a vendu via un autre canal de vente (Multi-Channel Fulfillment, MCF) : il ne vient pas d'une commande passée sur Amazon même. Peek le suit alors tout seul.
- Sinon, c'est Amazon Logistics qui livre une commande Amazon, et Amazon ne la montre que dans ton compte. Peek te le dit et propose « Voir mes commandes Amazon », qui ouvre tes commandes sur le site Amazon qui correspond au numéro : amazon.fr pour `FR`, amazon.com.be pour `BE`. Peek ne te demande jamais tes identifiants Amazon.
- **Un numéro de commande** reçoit « On dirait un numéro de commande Amazon ». Si l'e-mail d'expédition contient un numéro de suivi, colle plutôt celui-ci.

:::sources
- [About Amazon France : Six conseils pratiques pour se protéger des tentatives d'arnaques](https://www.aboutamazon.fr/actualites/nos-boutiques/six-conseils-pratiques-pour-se-proteger-des-tentatives-darnaques-par-usurpation-didentite) – vérifier ses commandes dans son compte ou l'application, garantie A à Z en cas de retard ou d'article en mauvais état
- [About Amazon France : Comment contacter le service client Amazon ?](https://www.aboutamazon.fr/actualites/nos-boutiques/comment-contacter-le-service-client-amazon) – suivi des colis sur la page du service client, chat et téléphone de 6 h à minuit, rappel, assistant virtuel par chat 24 h/24 qui peut transférer à un conseiller (lu le 14 novembre 2026)
- [About Amazon France : Expédié par Amazon](https://www.aboutamazon.fr/actualites/innovations/expedie-par-amazon) – stockage, emballage, expédition, service client et retours par Amazon, ventes sur Amazon ou via d'autres canaux de vente (2018)
- [About Amazon France : Les clients Amazon mettent leurs livreurs à l'honneur](https://www.aboutamazon.fr/actualites/activite-logistique/les-clients-amazon-mettent-leurs-livreurs-a-lhonneur) – entreprises de livraison indépendantes, agences de livraison d'Amazon, groupe La Poste (décembre 2022)
- [About Amazon France : Garantir des livraisons fiables et efficaces](https://www.aboutamazon.fr/actualites/activite-logistique/garantir-des-livraisons-fiables-et-efficaces-pendant-les-periodes-exceptionnelles) – Amazon Locker, points de retrait Mondial Relay, Relais Colis et La Poste
- [About Amazon France : Comment utiliser Amazon Locker](https://www.aboutamazon.fr/actualites/activite-logistique/comment-utiliser-amazon-locker) – plus de 4 500 consignes en France, la plupart ouvertes 24 h/24, sans coût supplémentaire, poids et taille du colis, code à six chiffres, code-barres ou application, trois jours calendaires, renvoi et remboursement, e-mail transféré à un proche (février 2025, lu le 14 novembre 2026)
- [About Amazon (Europe) : Amazon.com.be launches in Belgium](https://www.aboutamazon.eu/news/amazon-prime/amazon-com-be-launches-in-belgium) – amazon.com.be, service client en français, néerlandais ou anglais
- [About Amazon (Europe) : Amazon announces plans to invest over €1 billion in Belgium](https://www.aboutamazon.eu/news/job-creation-and-investment/amazon-announces-plans-to-invest-over-1-billion-to-enhance-belgian-customer-experience) – bpost, partenaires de livraison locaux
- [About Amazon Deutschland : Amazon in der Schweiz, alles, was du wissen musst](https://www.aboutamazon.de/news/company-news/aus-der-schweiz-bei-amazon-bestellen-das-musst-du-wissen) – amazon.de et amazon.fr depuis la Suisse, délais, TVA, droits de douane et frais de port compris
- [About Amazon Deutschland : Wie kommt mein Paket zu mir?](https://www.aboutamazon.de/news/verpackung/wie-kommt-mein-paket-zu-mir) – livraison en Suisse par La Poste suisse
- [About Amazon France : Amazon étend les protections apportées par la Garantie A à Z](https://www.aboutamazon.fr/actualites/nos-boutiques/amazon-etend-les-protections-apportees-par-la-garantie-a-a-z-en-france) – vendeur d'abord, remboursement, dommages matériels et corporels depuis mai 2024
- [Amazon.fr : Coordonnées des transporteurs](https://www.amazon.fr/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – principaux transporteurs en France métropolitaine, colis DHL livrés par La Poste, « Suivre le colis » dans Vos commandes, contacter directement le transporteur
- [Amazon.fr : Frais et délais de livraison en France métropolitaine et Monaco](https://www.amazon.fr/gp/help/customer/display.html?nodeId=GNWCU626A4NXEEGJ) – livraison standard, prioritaire, le jour même ou le lendemain, Mon Jour Amazon, livraison à la carte, articles lourds ou volumineux, jour supplémentaire possible en prioritaire, vendeurs Marketplace
- [Amazon.fr : À propos de nos politiques de retour](https://www.amazon.fr/gp/help/customer/display.html?nodeId=GKM69DUUYKQWKWX7) – rétractation sous 14 jours, retours sous 30 jours pour les produits éligibles, Amazon Haul exclu, fêtes 2026 jusqu'au 31 janvier 2027, 14 jours pour renvoyer l'article
- [Amazon.fr : Directives pour les retours depuis la France](https://www.amazon.fr/gp/help/customer/display.html?nodeId=G6E3B2E8QPHQ88KF) – « Retourner ou remplacer des articles », bureau de poste, point de collecte Mondial Relay, bornes de retour Amazon chez Monoprix, pas de retour en Amazon Locker
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – 30 jours, délai supplémentaire, fin du contrat et remboursement
- [Your Europe : FAQ expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_fr.htm) – le vendeur répond de la livraison, article identique ou remboursement
- [Universal Parcel Scraper : données transporteurs](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – formes des numéros Amazon, ce que Peek vérifie, Amazon Shipping et MCF dans le suivi public d'Amazon, suivi d'Amazon Logistics dans le compte
:::
