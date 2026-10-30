---
title: 4PX suivi : numéro, statuts et qui contacter
description: Suivre un colis 4PX venu de Chine : à quoi ressemble le numéro, ce que veut dire chaque statut, qui le livre en France ou en Belgique et qui contacter.
slug: 4px-suivi
published: 2026-10-11
updated: 2026-10-27
---

4PX (递四方速递, 4PX Express Co., Ltd.) est une société de logistique transfrontalière basée à Shenzhen, en Chine : elle expédie à l'étranger les colis de vendeurs en ligne, puis les confie à un transporteur de ton pays pour la livraison. Pour suivre ton colis, saisis son numéro sur [track.4px.com](https://track.4px.com/), le site de suivi de 4PX, ou colle-le dans le champ ci-dessus. Le numéro vient de la boutique ou de la plateforme où tu as commandé, dans les infos d'expédition de ta commande.

## Numéro de suivi 4PX : à quoi il ressemble

4PX ne publie aucun exemple de numéro. Son site accepte de 5 à 50 lettres et chiffres, sans autre caractère, et jusqu'à 100 numéros à la fois, un par ligne. Les formes courantes, avec des chiffres inventés :

| Numéro | À qui il appartient |
| --- | --- |
| `4PX1234567890123CN` | 4PX, 13 chiffres, puis CN : le numéro de 4PX. |
| `LP1234567890123CN` | LP, 13 chiffres et CN : peut-être 4PX. |
| `LP12345678901234` | LP et 14 chiffres, sans CN : la référence logistique de [Cainiao](carrier:cainiao) et AliExpress. |
| `RR000000005CN` | Un [numéro postal](guide:tracking-number-formats) terminé par un code pays : la poste de ce pays, ici China Post. |
| `YT1234567890123456` | Pas 4PX, mais [YunExpress](carrier:yunexpress). |

Dans le champ ci-dessus, minuscules et espaces passent aussi (`4PX 1234567890123 CN`). En français, la page de résultat de 4PX peut afficher deux numéros : le « Numéro 4px » et le « Numéro de suivi », qui est celui du transporteur qui livre dans ton pays. La boutique te donne parfois ce numéro local plutôt que celui de 4PX ; le site de 4PX en reconnaît aussi certains. Une commande en plusieurs colis apparaît en « Sous-package ».

## Suivi colis 4PX : ce que veulent dire les statuts

Le site de 4PX existe en français, mais les lignes de suivi restent dans leur langue d'origine, le plus souvent l'anglais : il propose seulement de les traduire en anglais ou en chinois simplifié, ou de garder le texte original. Les lignes les plus courantes, dans l'ordre où un colis les rencontre :

| Ce qu'affiche 4PX | Ce que ça veut dire pour toi |
| --- | --- |
| `Parcel information received` | Le vendeur a créé l'envoi. 4PX n'a pas encore le colis. |
| `4px received shipment.` | 4PX a reçu le colis dans son entrepôt. |
| `Shipment arrived at facility and measured.` | Mesuré dans un centre de 4PX. |
| `Hand over to airline.` | Remis à la compagnie aérienne pour le vol. |
| `Arrival to the destination airport` | Il a atterri dans ton pays. |
| `Customs clearance agent received the shipment in destnation country.` | À la douane de ton pays (la faute de frappe est de 4PX). |
| `Released from customs: customs cleared.` | Dédouané, il poursuit sa route. |
| `In transit, it's progressing through post network` | Le transporteur local l'a pris en charge. |
| `Out for delivery` | En tournée de livraison. |
| `Delivery attempt unsuccessful` | Le livreur n'a pas pu te le remettre. |
| `Delivered, in/at mailbox` | Livré dans ta boîte aux lettres. |

`Shipping information received`, `Waybill generated`, `Shipping label created` et `Advance notice - package information received.` veulent dire la même chose que la première ligne : le colis n'est pas encore parti.

En plus des lignes, 4PX range chaque colis sous une étiquette, et ses traductions françaises prêtent à confusion. `recevoir des prévisions` est un préavis : 4PX a reçu l'annonce de l'envoi, pas encore le colis. `En transit` couvre tout le trajet, de l'entrepôt à la dernière tournée. `Marchandises à ramasser` ne parle pas d'un ramassage chez l'expéditeur : le colis t'attend dans un point de livraison. `Anomalie` regroupe destinataire injoignable, adresse fausse, refus, dommage, perte, colis non retiré à temps, retard, première tentative ratée ou souci de douane. `Retour suspecté` : le transporteur risque de le renvoyer. `Suivi en stagnation` : aucune mise à jour depuis 15 jours. `Aucune donnée trouvée` : 4PX ne trouve rien sous ce numéro, peut-être mal saisi ou pas encore actualisé.

## Livraison 4PX en France : transporteur, point relais et livraison manquée

4PX ne livre pas lui-même. Son guide de suivi l'annonce par « Colis pris en charge par le transporteur local. » : le colis passe alors à une société de livraison du pays de destination. 4PX ne publie pas qui livre en France, en Belgique ou en Suisse. Sur la page de résultat, l'encadré « fournisseur de services de terminaux » donne, une fois la remise faite, le site et les coordonnées de ce transporteur.

Nombre de tentatives, avis de passage, nouvelle date de livraison, durée de garde en point relais : ce sont les règles du transporteur local, et 4PX n'en publie aucune. Pour `Marchandises à ramasser`, 4PX te demande d'aller chercher le colis au plus vite pour éviter qu'il ne soit renvoyé, sans donner de nombre de jours. Après `Delivery attempt unsuccessful`, prends le « Numéro de suivi » et le contact de l'encadré pour demander au transporteur local une nouvelle livraison ou le lieu de retrait.

## 4PX colis non reçu, bloqué ou renvoyé

**Pas encore de suivi.** Le site de 4PX dit alors qu'il n'y a pas encore de trace du colis et te conseille d'attendre ou de contacter le commerçant. `Aucune donnée trouvée` peut aussi venir d'un numéro mal recopié.

**Bloqué depuis des jours.** Après 15 jours sans scan, 4PX classe le colis en `Suivi en stagnation` : il a pu être perdu ou retardé, ou un transporteur n'a pas saisi ses scans. Regarde d'abord le suivi du transporteur local avec le « Numéro de suivi ». Les silences pendant le vol ou à la douane sont expliqués dans [le suivi d'un colis venant de Chine](guide:tracking-from-china).

**Livré mais pas reçu.** Sous `Livraison réussie`, 4PX te demande de contacter le service client pour confirmation. Commence par le transporteur local, qui a fait la livraison, puis suis [colis livré mais non reçu](guide:delivered-not-received).

**Renvoyé ou perdu.** 4PX ne publie ni procédure de réclamation ni délai pour les destinataires, et ses sites présentent retours et réclamations comme des services pour les vendeurs. C'est la boutique ou la plateforme qui a le contrat avec 4PX : demande-lui un remboursement ou un renvoi, et adresse-toi au transporteur local pour ce qui s'est passé à la livraison.

## 4PX contact : téléphone et e-mail

Relevé le 11 octobre 2026, le site de suivi de 4PX donne un seul contact pour tout le monde, dans Help, puis Contact Us :

- **Téléphone :** 0755-23508000, un numéro de Shenzhen, en Chine (+86 755 2350 8000 depuis la France, la Belgique ou la Suisse). Ni horaires, ni langues, ni coût indiqués.
- **E-mail :** track@4px.com.
- **Adresse :** 4PX Express Co., Ltd., No.12 Building, FuYong e-cool, BaoAn District, ShenZhen, GuangDong, Chine.

4PX ne publie aucun contact pour les destinataires en France, en Belgique ou en Suisse. Pour ta commande, la boutique répond plus vite ; pour la livraison, passe par le transporteur indiqué dans l'encadré « fournisseur de services de terminaux ».

## Suivre un colis 4PX avec Peek

[Peek](/) reconnaît d'emblée un numéro `4PX…CN`. Pour un numéro LP terminé par CN, il affiche « Vérification des services de suivi… », puis « 4PX a ce colis. » si 4PX le connaît ; sinon, choisis le transporteur avec « Changer ». Un lien de track.4px.com contenant `#/result/0/` suivi du numéro est lu comme 4PX quel que soit le numéro, ce qui marche aussi pour le numéro du transporteur local. Les commandes en plusieurs colis sont confiées à des services de suivi universels.

Peek interroge 4PX en anglais et affiche le statut, l'historique avec le lieu de chaque scan sur une carte, l'heure de livraison, le pays de destination et le transporteur local avec son numéro. Quand ce transporteur figure dans sa liste, Peek peut suivre le colis chez lui dès qu'il montre une étape plus récente. `Out for delivery` et `Delivery attempt unsuccessful` s'affichent avec les mots de Peek, les autres lignes telles que 4PX les écrit. 4PX ne donne ni date de livraison prévue, ni détails de point de retrait, ni poids.

Les heures sont lues dans le fuseau de 4PX, celles d'un scan sans fuseau à l'heure de son lieu, puis affichées dans le fuseau horaire de ton appareil. Peek vérifie toutes les 10 minutes en journée, toutes les 2 minutes une fois le colis en livraison, toutes les une à deux heures la nuit, et peut t'envoyer des notifications pour chaque scan, les étapes importantes ou « Livraison seulement ». Il ne peut contacter ni 4PX ni la boutique à ta place.

## Questions sur le suivi 4PX

### Quel transporteur livre les colis 4PX en France ?

4PX ne le publie pas : il confie le colis à un transporteur du pays de destination, qui peut varier. Une fois la remise faite, son nom et son site apparaissent dans l'encadré « fournisseur de services de terminaux » de la page de résultat.

### Quel est le délai de livraison 4PX ?

4PX ne publie aucun délai pour les destinataires : fie-toi à l'estimation affichée par la boutique ou la plateforme pour ta commande. Si rien ne bouge pendant 15 jours, 4PX considère que le suivi stagne.

### Peut-on retirer un colis 4PX en point relais ?

Ça dépend du transporteur local, pas de 4PX. Quand le colis t'attend dans un point de livraison, le site de 4PX affiche `Marchandises à ramasser` et te demande d'aller le chercher vite, sans dire combien de jours il y reste.

### 4PX, c'est fiable ?

4PX est une société de logistique chinoise établie, fondée en 2004 selon elle, qui a son propre site de suivi mondial et, avec Cainiao, un parc logistique à Dongguan. Si son site affiche `Aucune donnée trouvée`, vérifie le numéro, puis demande à la boutique.

:::sources
- [4PX : Plateforme Mondiale de Suivi de Colis](https://track.4px.com/) – règles des numéros, étiquettes en français, traduction, encadré du transporteur local
- [4PX : fichier de langue du site de suivi](https://track.4px.com/js/index.6aecf0a2c58f0d7c56a3.js) – explication des étiquettes, remise au transporteur local, pas de trace du colis
- [4PX : Contact Us](https://track.4px.com/#/help/contact) – téléphone, e-mail, adresse
- [4PX : Privacy Policy](https://track.4px.com/#/help/privacy) – 4PX Group, réclamations
- [4PX : For Perfect X-border](https://www.4px.com/view/index.html) – société et services aux vendeurs
- [4PX : 退换货 (retours)](https://www.4px.com/view/return.html) – retours, un service pour les vendeurs
- [4PX : 递四方成都至伦敦全货机航线成功首航](https://www.4px.com/view/newsCenterContentfive.html) – fondation en 2004
- [4PX : 菜鸟&递四方国际智慧物流园盛大开园](https://www.4px.com/view/newsCenterContentsix.html) – parc logistique de Cainiao et 4PX
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek affiche
:::
