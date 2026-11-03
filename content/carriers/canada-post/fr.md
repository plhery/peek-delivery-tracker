---
title: Postes Canada (Canada Post) suivi : numéro, statuts, avis
description: Suivre un colis Postes Canada : où trouver le numéro de repérage, ce que veulent dire les statuts, l'avis de livraison, le ramassage et la douane.
slug: canada-post-suivi
published: 2026-10-15
updated: 2026-10-27
---

Postes Canada, c'est la Société canadienne des postes, créée par une loi fédérale pour exploiter le service postal au Canada et entre le Canada et l'étranger. Pour suivre ton colis, entre son numéro de repérage sur le site de Postes Canada, canadapost-postescanada.ca, ou colle-le dans le champ ci-dessus. Ce numéro vient de l'expéditeur, en général dans le courriel d'expédition de la boutique. S'il finit par `CA` et que tu suis le colis depuis l'étranger, Peek peut aussi interroger la poste de ton pays avec le même numéro.

## Numéro de repérage Postes Canada : à quoi il ressemble

Peek associe ces deux formes à Postes Canada, ici avec des chiffres inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `1234 5678 9012 3452` | 16 chiffres ; le dernier est un chiffre de contrôle. DHL eCommerce, Correos Express, [TNT](carrier:tnt) et Evri UK ont aussi des numéros de 16 chiffres. |
| `RR000000005CA` | Un numéro postal international : 2 lettres, 9 chiffres, `CA`. Le dernier chiffre avant `CA` est un chiffre de contrôle. |

Postes Canada retrouve aussi un colis avec le numéro à 15 chiffres d'un avis de livraison, ou avec une référence faite seulement de chiffres. Pour ces numéros-là, choisis toi-même le transporteur dans Peek, comme expliqué plus bas.

[Purolator](carrier:purolator), un autre transporteur canadien, a ses propres numéros : 12 chiffres, ou 3 lettres et 9 chiffres. Pour ceux-là, Peek n'interroge pas Postes Canada de lui-même. Si Postes Canada ne connaît pas ton numéro, regarde quel transporteur nomme le courriel de la boutique. Plus de détails dans [où trouver son numéro de suivi](guide:find-tracking-number) et [à quel transporteur appartient un numéro](guide:tracking-number-formats).

## Statuts de suivi Postes Canada : ce qu'ils veulent dire

Peek lit les scans de Postes Canada en anglais. Voici ceux que tu croiseras le plus souvent, mot pour mot et à peu près dans l'ordre, avec l'étape que Peek affiche.

**Au Canada**

| Postes Canada écrit | Ce que ça veut dire pour toi | Dans Peek |
| --- | --- | --- |
| `Electronic information submitted by shipper` | L'expéditeur a créé une étiquette, mais Postes Canada n'a pas encore le colis. | « Étiquette créée » |
| `Item accepted at the Post Office` | Le colis a été déposé dans un bureau de poste. | « Pris en charge » |
| `Item picked up by Canada Post` | Postes Canada est allée le chercher chez l'expéditeur. | « Pris en charge » |
| `Item processed` | Il avance dans le réseau de Postes Canada. La ligne peut revenir plusieurs fois. | « En transit » |
| `Item out for delivery` | Il s'en vient chez toi aujourd'hui. | « En livraison » |
| `Delivered to your community mailbox, parcel locker or apt./condo mailbox` | Dans ta boîte postale communautaire, un casier à colis ou la boîte aux lettres de ton immeuble. | « Livré » |
| `Delivered to recipient's safe drop location` | Déposé à l'endroit sûr prévu pour tes colis. | « Livré » |
| `Notice card left indicating where and when to pick up item` | La livraison a été tentée ; le colis part dans un bureau de poste, et l'avis laissé dit où et quand aller le chercher. | « Tentative de livraison » |
| `Item available for pickup at Post Office` | Il t'attend au bureau de poste. | « Prêt à être retiré » |
| `Final Notice; Item will be returned to sender if not collected within 10 days` | Dernier avis : va le chercher d'ici 10 jours, sinon il repart chez l'expéditeur. | « Prêt à être retiré » |
| `Recipient not located at address provided. Item being returned to sender.` | Le destinataire n'a pas été trouvé à l'adresse indiquée ; le colis repart. | « Problème signalé » |
| `Item has been returned and is enroute to the Sender` | Il est en route vers l'expéditeur. | « En transit » |

`Delivered to your concierge or building manager` (remis au concierge ou au gestionnaire de l'immeuble) et `Delivered to mailroom` (livré à la salle du courrier) s'affichent aussi « Livré ». `Item re-routed due to processing error; Possible delay` et `Delivery may be delayed due to transportation delay` annoncent un retard possible : « Problème signalé ». Avec `Expected delivery date updated`, Postes Canada donne un nouveau jour de livraison prévu ; le colis, lui, n'a pas bougé.

**De l'étranger vers le Canada**

| Postes Canada écrit | Ce que ça veut dire pour toi | Dans Peek |
| --- | --- | --- |
| `International item mailed in originating country` | Posté à l'étranger ; Postes Canada le suit sous le numéro de la poste d'origine. | « Pris en charge » |
| `International item has left originating country and is en route to Canada` | En route vers le Canada. | « En transit » |
| `Item has arrived in Canada and will be presented for review` | Arrivé au Canada, il attend la douane. | « À la douane » |
| `Item was released by Customs and is now with Canada Post for processing` | La douane, c'est réglé : Postes Canada prend le relais. | « En transit » |

**Du Canada vers un autre pays**, `International item being forwarded to destination country` (envoyé vers le pays de destination) et `Item has arrived in foreign country` (arrivé dans le pays de destination) s'affichent « En transit », et `Item presented to customs` (présenté à la douane), « À la douane ».

Dans l'historique, les scans de livraison s'affichent « Livré » ; les autres gardent l'anglais de Postes Canada, même si tu utilises Peek en français.

## Livraison manquée, ramassage et douane au Canada

**Livraison manquée.** À partir du scan de dernier avis, tu as 10 jours pour aller chercher le colis avant qu'il reparte chez l'expéditeur. Plus de détails dans [livraison manquée](guide:missed-delivery) et [colis non retiré](guide:uncollected-parcel).

**La douane sur les colis venus de l'étranger.** À l'arrivée, Postes Canada confie tout le courrier international à l'Agence des services frontaliers du Canada (ASFC), qui le traite dans ses centres de Vancouver, Toronto et Montréal. La plupart des envois passent après un premier contrôle, parfois en moins d'une journée. Un envoi qui pourrait être interdit au Canada, qui est mal étiqueté ou qui pourrait cacher de la contrebande est examiné de plus près, parfois par Santé Canada ou l'Agence canadienne d'inspection des aliments, et peut prendre du retard.

**Droits et taxes.** Une marchandise reçue par la poste d'une valeur de 20 $ CA ou moins ne paie ni droits ni taxes, et un cadeau de parents ou d'amis à l'étranger ne paie pas de TPS jusqu'à 60 $ CA. Au-delà, compte la TPS de 5 %, à moins que la marchandise en soit exemptée. Si ta province applique la TVH, tu paies la TVH au lieu de la TPS ; en Colombie-Britannique, au Manitoba, au Québec et en Saskatchewan, la taxe de vente provinciale s'ajoute. Les droits dépendent de la marchandise et du pays d'où elle vient ou où elle a été fabriquée. Ce que tu dois figure sur le formulaire E14 de l'ASFC, joint au colis à la livraison, et Postes Canada facture des frais de manutention quand des droits ou des taxes sont dus.

**Pas d'accord avec le montant ?** Refuse le colis et demande une nouvelle cotisation, ou paie et demande un rajustement avec le formulaire B2G, au verso du E14. Voir aussi [colis bloqué en douane](guide:customs).

## Colis Postes Canada envoyé à l'étranger : qui le livre

**Qui livre.** Si le numéro postal international finit par `CA` et que son chiffre de contrôle est bon, Peek peut aussi interroger, avec le même numéro, la poste du pays d'où tu suis le colis : par exemple [La Poste](carrier:la-poste) en France, [bpost](carrier:bpost) en Belgique, [La Poste suisse](carrier:swiss-post) en Suisse, [POST Luxembourg](carrier:post-luxembourg) au Luxembourg, [DHL](carrier:dhl) en Allemagne, USPS aux États-Unis et Royal Mail au Royaume-Uni (Parcelforce pour les numéros qui commencent par `E`).

Postes Canada ne donne à Peek aucun pays de destination, alors c'est ton pays qui compte : dans l'application iPhone, la région réglée sur ton iPhone ; dans un navigateur, le pays d'où tu as ajouté le colis. Dès que ta poste montre un scan au moins aussi récent que le dernier de Postes Canada, Peek suit le colis chez elle et garde l'historique de Postes Canada. Pendant encore une journée, il interroge aussi Postes Canada, au plus toutes les 55 minutes. Si tu suis le colis depuis le Canada, ou avec un numéro de 16 chiffres, Peek le suit seulement chez Postes Canada. Pour la douane du pays de destination, voir [colis bloqué en douane](guide:customs).

## Colis Postes Canada en retard, perdu, retourné ou introuvable

**Plus de nouveaux scans.** Après quatre jours sans nouveau scan à « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche : « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Canada Post et affichera le prochain scan ici. » Il ajoute un lien vers l'aide du transporteur qui a le colis, comme « Demande de l’aide à Canada Post ». Selon l'ASFC, le courrier à la douane au Canada ne peut pas être suivi avec précision. Voir [suivi qui ne bouge plus](guide:tracking-not-updating).

**Perdu.** Si tu as acheté en ligne, commence par la boutique. Voir [colis perdu](guide:lost-parcel).

**Indiqué livré, mais pas là.** Selon la Loi sur la Société canadienne des postes, un envoi compte comme livré quand il est laissé chez toi ou à ton travail, mis dans ta boîte aux lettres ou confié à quelqu'un qui semble autorisé à le prendre pour toi, de la façon dont ton courrier est d'habitude distribué. Peek affiche « Livré », mais le suivi de Postes Canada précise l'endroit, par exemple `Delivered to mailroom` (livré à la salle du courrier) : regarde là d'abord. Si c'est la poste de ton pays qui l'a livré, regarde son suivi. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Retourné.** Une fois le colis revenu chez l'expéditeur, Peek affiche « Retourné à l’expéditeur ». Voir [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service à la clientèle de Postes Canada et contact

La façon de joindre Postes Canada est sur canadapost-postescanada.ca. Une fois qu'un colis parti du Canada est entre les mains de la poste de ton pays, c'est à elle qu'il faut s'adresser pour la livraison et le retrait ; les pages sur [La Poste](carrier:la-poste), [bpost](carrier:bpost), [La Poste suisse](carrier:swiss-post) et [POST Luxembourg](carrier:post-luxembourg) disent comment la joindre. Peek ne peut pas contacter Postes Canada ni la boutique à ta place.

## Suivre un colis Postes Canada avec Peek

[Peek](/) reconnaît tout de suite Postes Canada pour les numéros postaux internationaux qui finissent par `CA` et dont le chiffre de contrôle est bon. Un numéro de 16 chiffres au chiffre de contrôle valide correspond à plusieurs transporteurs : Peek affiche « Vérification des services de suivi… », interroge en même temps Postes Canada, DHL eCommerce, TNT, Correos Express et Evri UK, et retient celui qui connaît le numéro. Pour un numéro d'avis de livraison ou une référence, touche « Changer » et choisis Canada Post, le nom de Postes Canada dans Peek, sous « Tous les transporteurs » ou avec la recherche. Un lien de suivi de Postes Canada qui contient `searchFor=` fonctionne aussi ; les liens avec `#/search` ne marchent qu'avec les numéros postaux qui finissent par `CA`, alors colle toi-même un numéro de 16 chiffres.

Peek interroge Postes Canada directement, sans navigateur. Tu vois le statut, l'historique avec le lieu de chaque scan (ville et province au Canada), le jour de livraison prévu, une plage horaire une fois le colis en livraison au Canada si Postes Canada en donne une, l'heure de livraison et, quand Postes Canada l'affiche en entier, le nom de l'expéditeur. Peek laisse de côté les options de livraison, les adresses, les signatures et les photos de livraison.

Peek vérifie toutes les 10 minutes, et toutes les 2 minutes dès que Postes Canada elle-même indique que le colis est en livraison ce jour-là. Après 12 heures sans nouveau scan à « Étiquette créée » ou « Pris en charge », ou 48 heures à toute autre étape, il vérifie toutes les heures. Pour les notifications, choisis « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

**Vers le Canada.** Pour un colis posté à l'étranger avec un numéro postal international, Peek interroge aussi Postes Canada avec le même numéro dès que la poste d'origine nomme le Canada comme destination ou, si elle n'en nomme aucune, si tu suis le colis depuis le Canada. Dès que Postes Canada montre un scan au moins aussi récent que le dernier de la poste d'origine, Peek suit le colis chez elle et garde l'historique précédent. Un numéro qui commence par `L` et finit par `CH`, que Peek suit chez La Poste suisse, n'est jamais passé à Postes Canada.

## Questions sur le suivi Postes Canada

### Pourquoi mon suivi Postes Canada ne bouge plus ?

Les longs vols, la douane et les fins de semaine laissent souvent un trou entre deux scans. Peek continue de vérifier, toutes les heures une fois passés deux jours sans nouvelles. Voir [suivi qui ne bouge plus](guide:tracking-not-updating).

### Pourquoi Peek ne trouve pas mon numéro de repérage Postes Canada ?

Postes Canada donne la même réponse qu'un numéro soit inconnu, pas encore scanné ou expiré : pour un colis Postes Canada, Peek affiche donc « En attente du transporteur » jusqu'au premier scan. Vérifie chaque chiffre : dans un numéro de 16 chiffres, le dernier est un chiffre de contrôle, et dans un numéro postal, c'est le dernier chiffre avant `CA`.

### Puis-je suivre un colis avec le numéro de l'avis de livraison ?

Oui. Colle le numéro à 15 chiffres de l'avis, touche « Changer » et choisis Canada Post sous « Tous les transporteurs ». Peek cherche alors le colis chez Postes Canada avec ce numéro.

### Qui livre un colis Postes Canada en France, en Belgique ou en Suisse ?

La poste de ton pays, si le numéro finit par `CA` : La Poste en France, bpost en Belgique, La Poste suisse en Suisse. Peek l'interroge avec le même numéro. Un numéro de 16 chiffres reste suivi chez Postes Canada.

:::sources
- [ASFC : Importation par la poste](https://www.cbsa-asfc.gc.ca/import/personal-personnel/postal-postale-fra.html) – douane sur le courrier, exemption de 20 $, formulaire E14, frais de manutention, pas de suivi précis
- [ASFC : Importation par la poste ou par messagerie - Paiement des droits et des taxes sur les biens importés](https://www.cbsa-asfc.gc.ca/import/postal-postale/dtytx-drttx-fra.html) – TPS, TVH et TVP, limites de 20 $ CA et de 60 $ CA, droits
- [ASFC : Importation par la poste ou par messagerie - Contestation des droits et des taxes](https://www.cbsa-asfc.gc.ca/import/postal-postale/dispute-contestation-fra.html) – nouvelle cotisation, formulaire B2G
- [Site Web de la législation (Justice) : Loi sur la Société canadienne des postes](https://laws-lois.justice.gc.ca/fra/lois/C-10/page-1.html) – la Société, quand un envoi est considéré comme livré
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formes de numéros, libellés de Postes Canada et leurs étapes, ce que Peek affiche, passage d'une poste à l'autre
:::
