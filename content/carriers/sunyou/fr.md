---
title: SunYou tracking : suivi du colis, statuts et qui livre
description: Suivre un colis SunYou (SYPost) venu de Chine : à quoi ressemble le numéro SY, ce que veut dire chaque statut, qui le livre chez toi et qui contacter s'il bloque.
slug: sunyou-suivi
published: 2026-10-12
updated: 2026-10-26
---

SunYou, aussi appelé SYPost, est une entreprise logistique de Shenzhen qui expédie à l'étranger les petits colis de vendeurs en ligne chinois, puis les confie à un transporteur de ton pays pour la fin du trajet. Ses numéros commencent par SY : saisis le tien sur son site de suivi, [sypost.net](https://www.sypost.net), qui existe en français, ou colle-le dans le champ ci-dessus. C'est la boutique qui te donne ce numéro, avec les informations d'expédition de ta commande.

## Numéro de suivi SunYou : les formes SY

SunYou ne publie aucun format de numéro, mais ceux qu'on voit sur son suivi ont deux formes. Avec des chiffres inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `SY12345678901` | SY et 11 chiffres : SunYou. |
| `SYFR000000000` | SY, deux lettres et 9 chiffres : SunYou aussi. |
| `LP00000000000000` | LP et 14 chiffres : une référence logistique d'AliExpress et Cainiao. |
| `RR000000005CN` | Deux lettres, neuf chiffres, CN : un numéro de [China Post](carrier:china-post). |

Les deux lettres après SY ne donnent pas toujours le pays de destination. Sur le suivi de SunYou, un numéro `SYRM` est allé au Japon, un autre en Bulgarie, et un numéro `SYAE` en Grèce.

**Où le trouver.** Aucune page de SunYou ne dit comment le numéro t'arrive : on n'y trouve rien sur un SMS ou un e-mail. Le vendeur enregistre le colis chez SunYou, et la boutique te donne le numéro avec les informations d'expédition de ta commande. Si tu ne le trouves pas, lis [où trouver son numéro de suivi](guide:find-tracking-number).

**Les numéros qui se ressemblent.** Certaines boutiques annoncent SunYou pour un numéro LP, mais cette forme appartient à la logistique d'AliExpress : suis-le comme un [colis Cainiao](carrier:cainiao). Un numéro SY de 10 chiffres seulement, ou de 12, n'a pas la forme d'un numéro SunYou.

**Un deuxième numéro en route.** Une fois le colis sorti du centre de SunYou en Chine, le suivi peut ajouter un « numéro de suivi transporteur », avec le nom du transporteur local et son site. Ce numéro marche sur le suivi de ce transporteur.

## SunYou suivi : ce que veulent dire les statuts

sypost.net affiche les étapes dans la langue que tu choisis, dont le français ; en anglais, les mêmes lignes sont celles que Peek affiche. Dans l'ordre où un colis les rencontre en général :

| Sur sypost.net en français | En anglais | Ce que ça veut dire pour toi |
| --- | --- | --- |
| `Quitte le centre de traitement` | `Departed Sunyou Facility` | Il quitte le centre de SunYou en Chine, souvent avec le numéro du transporteur local. |
| `Dédouanement à l'export réussi` | `Export Customs Clearance Successed` | Il peut sortir de Chine. |
| `Départ du port de départ` | `Departed From Port Of Origin` | L'avion ou le bateau est parti. |
| `Arrivée au centre de transit postal` | `Arrived At GMTC` | Il passe par le centre de transit mondial de SunYou. |
| `Arrivée à l'aéroport de destination` | `Arrived At Destination Country Airport` | Il a atterri dans ton pays. |
| `En cours de dédouanement` | `Customs Clearance In Process` | Il passe la douane à l'arrivée. |
| `Dédouanement réussi` | `Customs Clearance Successed` | La douane l'a laissé passer. |
| `Réception pour livraison` | `Hand Over To Last Mile` | Le transporteur local l'a. Suis son numéro à partir d'ici. |
| `En cours de livraison` | `Delivery In Progress` | En tournée de livraison. |
| `Livré avec succès` | `Delivered` | Livré. |

Avant tout ça, « Préavis reçu » veut dire que le vendeur a enregistré le colis, mais que SunYou ne l'a pas encore ; « Réceptionné » qu'il l'a reçu en Chine. `Dédouanement terminé` équivaut à `Dédouanement réussi`. `Livré à la boîte aux lettres` et `Livré à la porte` disent où le colis a été laissé.

Trois choses déroutent. D'abord, `Livré avec succès` peut suivre directement `Réception pour livraison`, voire la ligne de l'aéroport, sans rien entre les deux : les étapes manquantes sont sur le suivi du transporteur local. Ensuite, le résumé en haut de page a ses propres libellés : `Possibilité d'anomalie` signale un problème possible et `Transit trop long` un colis en route depuis trop longtemps ; aucun des deux ne veut dire une livraison ratée. `Échec de la livraison`, si, et l'historique peut en donner la raison, comme `Échec de la livraison, raison : adresse incorrecte/incomplète`. Enfin, la même étape s'écrit `Prêt à être récupéré` en français et `Out For Delivery` en anglais : un même code, deux sens différents selon la langue.

À la douane, surveille `Colis retenu en douane` et `Échec de la livraison, raison : refus de payer la TVA / droits de douane` ; voir [colis bloqué en douane](guide:customs).

## SunYou livraison : qui livre en France, en Belgique et en Suisse

SunYou ne livre rien lui-même en Europe : sa ligne commerciale confie les colis à des transporteurs locaux du pays de destination, et son suivi nomme celui qui prend le relais. Aucune page de SunYou ne dit qui livre dans quel pays. Parmi les logos de ses partenaires figurent [La Poste](carrier:la-poste), bpost et La Poste suisse, mais aussi DHL, [UPS](carrier:ups) ou [FedEx](carrier:fedex). Dans les historiques lus sur son suivi, [DHL Paket](carrier:dhl) livrait en Allemagne et Royal Mail au Royaume-Uni.

SunYou ne publie aucune règle sur les tentatives de livraison, les avis de passage, les points de retrait ou la durée de garde. Ce sont celles du transporteur local : avec son nom et son numéro, passe par son site pour reprogrammer une livraison ou savoir où le colis t'attend.

Certains colis SunYou voyagent en courrier ordinaire, que SunYou dit suivre seulement jusqu'à la prise en charge par la poste du pays de destination. Pour ceux-là, l'historique peut s'arrêter une fois le colis chez cette poste.

Le suivi de SunYou propose aussi un lien de preuve de livraison (« Proof of Delivery »), qui demande le numéro de téléphone du destinataire.

## Colis SunYou non reçu ou bloqué : que faire

**Qui agit.** Le vendeur est le client de SunYou : il ouvre un compte, enregistre le colis et a le contrat. Les pages de SunYou n'offrent aucune voie de réclamation aux destinataires, et ses indemnisations sont réservées aux vendeurs. Pour un colis en retard, perdu, abîmé ou renvoyé, contacte donc le vendeur ou la plateforme où tu as commandé.

**Combien de temps.** Aux vendeurs, SunYou annonce « 4-5 jours » entre la prise en charge et certaines grandes destinations sur sa ligne générale, et une livraison « en 5 jours au plus vite » vers les principaux pays d'Europe et d'Amérique sur sa ligne commerciale. Ce sont des arguments de vente, pas une date pour ta commande.

**Pas de mise à jour.** Si l'historique montre un numéro de transporteur, regarde d'abord le suivi de ce transporteur. Les autres silences sont expliqués dans [pourquoi un suivi de colis ne bouge plus](guide:tracking-not-updating).

**Perdu ou renvoyé.** SunYou l'écrit `Désolé, votre colis a été perdu`, `Retourné` ou `Colis retourné, raison : adresse incomplète`. Dans chaque cas, contacte le vendeur.

## SunYou contact : téléphone et e-mail

Relevé le 12 octobre 2026, SunYou donne les mêmes canaux pour tous les pays, sans bureau pour les destinataires en France, en Belgique ou en Suisse, et sans page de contact en français :

- **Téléphone :** +86 4006075388, un numéro chinois. Ni horaires ni coût d'appel publiés.
- **E-mail :** mto@sypost.com, sa boîte de suivi des colis.
- **Réclamations :** tousu@sypost.com, sur une page de contact en chinois.

Pour faire bouger les choses, adresse-toi au vendeur et au transporteur local. SunYou ne publie aucun avertissement sur les faux SMS de livraison.

## Suivre un colis SunYou avec Peek

[Peek](/) reconnaît tout de suite les deux formes SY, qu'aucun autre transporteur de son catalogue n'utilise. Tu peux aussi coller un lien de sypost.net qui contient le numéro, comme celui que donne « Copy Link ».

Peek interroge SunYou en anglais : les lignes restent en anglais, sauf les libellés exacts qu'il connaît, comme `Delivered`, qu'il affiche avec ses propres mots en français. Il montre le statut, l'historique (jusqu'à 20 scans, sans lieu), le pays de destination, l'heure de livraison et le transporteur local avec son numéro. Pas de date de livraison prévue ni de point de retrait. Les heures gardent le fuseau que SunYou leur donne, celui de la Chine puis celui de ton pays.

Quand SunYou donne le numéro du transporteur local, Peek peut l'interroger, par exemple La Poste pour un numéro postal à destination de la France, et passer à lui dès qu'il montre des étapes plus récentes. Le colis garde son numéro SunYou, sa page indique « Livraison par » ce transporteur et donne son numéro, avec un lien vers son suivi. Pas pour chaque colis : Meest en Grèce, par exemple, n'est pas suivi.

Il vérifie le suivi toutes les 10 minutes en journée, toutes les 2 minutes une fois le colis en livraison, toutes les heures la nuit, et peut t'envoyer des notifications : « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ». Il ne peut contacter ni SunYou ni le vendeur à ta place.

## Questions sur le suivi SunYou

### Le suivi SunYou, c'est fake ou réel ?

SunYou est une vraie entreprise logistique, fondée à Shenzhen en 2008, et son suivi est sur sypost.net. Si ton numéro y donne `Aucun résultat trouvé`, demande au vendeur de le vérifier.

### C'est quoi SunYou Post ?

C'est SunYou Logistics, une entreprise de Shenzhen qui expédie à l'étranger les petits colis de vendeurs en ligne chinois et les confie à un transporteur local pour la livraison. Elle dit traiter plus de 100 000 colis par jour.

### Où est le « Sunyou Facility » ?

En Chine : SunYou a son siège à Shenzhen et des agences à Guangzhou, Dongguan, Yiwu, Kunshan et Ningbo. `Departed Sunyou Facility` veut dire que le colis a quitté le centre de SunYou.

### Un colis SunYou peut-il venir d'une commande Amazon ?

C'est possible : SunYou affiche le logo d'Amazon parmi ses plateformes partenaires, avec ceux d'AliExpress, Temu, eBay, Joom ou Wish. C'est le vendeur qui choisit SunYou ; pour un problème, passe par ta commande sur la plateforme.

:::sources
- [SunYou : Sunyou Package Tracking](https://www.sypost.net/) – site de suivi, langues, saisie du numéro, téléphone, e-mail, preuve de livraison
- [SunYou : fichier de langue du suivi](https://www.sypost.net/language) – statuts et étapes en français et en anglais
- [SunYou : Search.js du suivi](https://www.sypost.net/track/Scripts/Search.js) – statuts du résumé, numéro du transporteur, Copy Link
- [SunYou : 联系我们 (contact)](https://www.sypost.com/views/contact.html) – téléphone, e-mail, réclamations
- [SunYou : 顺邮宝专线 (ligne générale)](https://www.sypost.com/views/services-1.html) – suivi du courrier ordinaire, délais, indemnisation des vendeurs
- [SunYou : 顺速宝专线 (ligne commerciale)](https://www.sypost.com/views/services-2.html) – transporteurs locaux, délais
- [SunYou : 投寄说明 (instructions d'envoi)](https://www.sypost.com/views/document-3.html) – le vendeur enregistre le colis, suivi sur sypost.net
- [SunYou : 顺友简介 (présentation)](https://www.sypost.com/views/about.html) – fondation en 2008, Shenzhen, agences, volumes
- [SunYou : plateformes partenaires](https://www.sypost.com/views/customer-1.html) – Amazon, AliExpress, Temu et autres
- [SunYou : postes et transporteurs partenaires](https://www.sypost.com/views/customer-2.html) – La Poste, bpost, La Poste suisse
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek affiche, formes de numéros
:::
