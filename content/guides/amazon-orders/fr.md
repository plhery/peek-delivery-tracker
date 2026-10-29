---
title: Suivi de commande Amazon : Amazon, vendeurs, transporteurs
description: Qui expédie ta commande Amazon, quel transporteur la livre, pourquoi le numéro de suivi change ou manque, et quand passer par le vendeur ou la garantie A à Z.
slug: suivi-commande-amazon
picture: Un téléphone affiche l'avancée d'une commande, deux camionnettes se passent un colis, et Pip regarde.
published: 2026-10-16
updated: 2026-10-27
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
| `FR1234567890` | Amazon en Europe : un code pays (`FR`, `BE`, `CH` et d'autres) et 10 chiffres |
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

## Que faire si ma commande Amazon est en retard ou dite livrée ?

Dans l'Union européenne, le commerçant doit te livrer dans les 30 jours, sauf si vous avez convenu d'un autre délai, et c'est lui qui est responsable de la livraison. Procède dans cet ordre :

:::steps
- Vérifier la commande | Ouvre-la dans Vos commandes, sur amazon.fr ou dans l'application, pour voir son état et l'adresse de livraison choisie : domicile, Amazon Locker ou point de retrait.
- Chercher autour | Regarde ta boîte aux lettres et l'endroit où tu reçois d'habitude tes colis, et demande aux voisins.
- Contacter le vendeur | S'il a vendu et expédié l'article, écris-lui d'abord. C'est à lui de prouver que l'article a été livré ; sinon, il doit t'envoyer un article identique ou te rembourser.
- Contacter Amazon | Pour une commande expédiée par Amazon, ou si le vendeur ne règle pas le problème : service client, par chat ou par téléphone de 6 h à minuit, 7 j/7, ou rappel sur demande.
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

## Est-ce que je peux suivre un colis Amazon ailleurs que sur Amazon ?

Ça dépend du numéro. Colle-le dans [Peek](/) :

- **Le numéro d'un transporteur** (Colissimo, Chronopost, Mondial Relay, bpost, La Poste suisse…) se suit comme n'importe quel colis.
- **Un numéro Amazon**, ou un lien track.amazon : Peek interroge d'abord le suivi public d'Amazon. S'il répond, le colis voyage avec [Amazon Shipping](carrier:amazon-shipping), ou Amazon l'expédie pour un vendeur qui a vendu via un autre canal de vente (Multi-Channel Fulfillment, MCF) : il ne vient pas d'une commande passée sur Amazon même. Peek le suit alors tout seul.
- Sinon, c'est Amazon Logistics qui livre une commande Amazon, et Amazon ne la montre que dans ton compte. Peek te le dit et propose « Voir mes commandes Amazon », qui ouvre tes commandes sur le site Amazon qui correspond au numéro : amazon.fr pour `FR`, amazon.com.be pour `BE`. Peek ne te demande jamais tes identifiants Amazon.
- **Un numéro de commande** reçoit « On dirait un numéro de commande Amazon ». Si l'e-mail d'expédition contient un numéro de suivi, colle plutôt celui-ci.

:::sources
- [About Amazon France : Six conseils pratiques pour se protéger des tentatives d'arnaques](https://www.aboutamazon.fr/actualites/nos-boutiques/six-conseils-pratiques-pour-se-proteger-des-tentatives-darnaques-par-usurpation-didentite) – vérifier ses commandes dans son compte ou l'application, garantie A à Z en cas de retard ou d'article en mauvais état
- [About Amazon France : Comment contacter le service client Amazon ?](https://www.aboutamazon.fr/actualites/nos-boutiques/comment-contacter-le-service-client-amazon) – suivi des colis sur la page du service client, chat et téléphone de 6 h à minuit, rappel
- [About Amazon France : Expédié par Amazon](https://www.aboutamazon.fr/actualites/innovations/expedie-par-amazon) – stockage, emballage, expédition, service client et retours par Amazon, ventes sur Amazon ou via d'autres canaux de vente (2018)
- [About Amazon France : Les clients Amazon mettent leurs livreurs à l'honneur](https://www.aboutamazon.fr/actualites/activite-logistique/les-clients-amazon-mettent-leurs-livreurs-a-lhonneur) – entreprises de livraison indépendantes, agences de livraison d'Amazon, groupe La Poste (décembre 2022)
- [About Amazon France : Garantir des livraisons fiables et efficaces](https://www.aboutamazon.fr/actualites/activite-logistique/garantir-des-livraisons-fiables-et-efficaces-pendant-les-periodes-exceptionnelles) – Amazon Locker, points de retrait Mondial Relay, Relais Colis et La Poste
- [About Amazon (Europe) : Amazon.com.be launches in Belgium](https://www.aboutamazon.eu/news/amazon-prime/amazon-com-be-launches-in-belgium) – amazon.com.be, service client en français, néerlandais ou anglais
- [About Amazon (Europe) : Amazon announces plans to invest over €1 billion in Belgium](https://www.aboutamazon.eu/news/job-creation-and-investment/amazon-announces-plans-to-invest-over-1-billion-to-enhance-belgian-customer-experience) – bpost, partenaires de livraison locaux
- [About Amazon Deutschland : Amazon in der Schweiz, alles, was du wissen musst](https://www.aboutamazon.de/news/company-news/aus-der-schweiz-bei-amazon-bestellen-das-musst-du-wissen) – amazon.de et amazon.fr depuis la Suisse, délais, TVA, droits de douane et frais de port compris
- [About Amazon Deutschland : Wie kommt mein Paket zu mir?](https://www.aboutamazon.de/news/verpackung/wie-kommt-mein-paket-zu-mir) – livraison en Suisse par La Poste suisse
- [About Amazon France : Amazon étend les protections apportées par la Garantie A à Z](https://www.aboutamazon.fr/actualites/nos-boutiques/amazon-etend-les-protections-apportees-par-la-garantie-a-a-z-en-france) – vendeur d'abord, remboursement, dommages matériels et corporels depuis mai 2024
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – 30 jours, délai supplémentaire, fin du contrat et remboursement
- [Your Europe : FAQ expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_fr.htm) – le vendeur répond de la livraison, article identique ou remboursement
- [Universal Parcel Scraper : données transporteurs](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – formes des numéros Amazon, ce que Peek vérifie, Amazon Shipping et MCF dans le suivi public d'Amazon, suivi d'Amazon Logistics dans le compte
:::
