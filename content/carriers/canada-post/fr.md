---
title: Postes Canada (Canada Post) suivi : numéro, statuts, avis
description: Suivre un colis Postes Canada : où trouver le numéro de repérage, ce que veulent dire les statuts, l'avis de livraison, le ramassage et la douane.
slug: canada-post-suivi
published: 2026-10-15
updated: 2026-10-27
---

Postes Canada, c'est la Société canadienne des postes, la société d'État fédérale qui exploite le service postal du Canada. Pour suivre ton colis, entre son numéro de repérage dans l'outil Repérage sur [canadapost-postescanada.ca](https://www.canadapost-postescanada.ca/track-reperage/fr), dans l'appli gratuite de Postes Canada ou dans le champ ci-dessus. Ce numéro vient de l'expéditeur, en général dans le courriel d'expédition de la boutique ; Postes Canada ne peut pas retrouver un colis à partir de ton nom ou de ton adresse. S'il finit par `CA` et que tu suis le colis depuis l'étranger, Peek peut aussi interroger la poste de ton pays avec le même numéro.

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

**Livraison manquée.** Si un colis exige une signature, ou s'il est trop gros pour ta boîte aux lettres ou ton compartiment à colis, Postes Canada tente de le livrer à ta porte. Si personne ne répond, elle laisse une carte Avis de livraison ou, quand aucune signature n'est requise, peut laisser le colis en lieu sûr. Après `Delivery Attempt Made / Rescheduled for delivery next business day` (« Tentative de livraison effectuée / livraison reportée au jour ouvrable suivant » dans l'aide de Postes Canada), elle essaie normalement de nouveau le jour ouvrable suivant.

**Avis de livraison.** Quand une carte est laissée, Repérage indique « Avis laissé », puis le bureau de poste proche et une date « Ramasser avant » dès qu'il les connaît. Le statut devient ensuite « Disponible pour ramassage » (« Prêt à être ramassé » dans l'aide). Une carte Avis final suit après cinq jours civils ; après 15 jours civils en tout, le colis repart chez l'expéditeur. Plus de détails dans [livraison manquée](guide:missed-delivery) et [colis non retiré](guide:uncollected-parcel).

**Le ramassage.** Apporte une pièce d'identité avec photo émise par le gouvernement (Repérage en demande une qui porte l'adresse inscrite sur le colis) et la carte, le courriel d'avis ou le code à barres de ramassage dans l'appli. Quelqu'un d'autre peut y aller à ta place avec sa propre pièce d'identité avec photo et ta carte, que tu as signée en y inscrivant son nom, ou une lettre d'autorisation.

**Casiers à colis.** Postes Canada laisse la clé du casier à colis dans ta boîte aux lettres. Après cinq jours au casier, le colis part au bureau de poste et tu reçois une carte.

**La douane sur les colis venus de l'étranger.** À l'arrivée, Postes Canada confie tout le courrier international à l'Agence des services frontaliers du Canada (ASFC). Pendant que la douane examine un colis, Postes Canada dit ne pouvoir ni intervenir ni se renseigner.

**Droits et taxes.** Une marchandise reçue par la poste d'une valeur de 20 $ CA ou moins ne paie ni droits ni taxes, et un cadeau de parents ou d'amis à l'étranger ne paie pas de TPS jusqu'à 60 $ CA. Au-delà, compte la TPS de 5 %, ou la TVH si ta province l'applique ; en Colombie-Britannique, au Manitoba, au Québec et en Saskatchewan, la taxe de vente provinciale s'ajoute, et les droits dépendent de la marchandise. Le montant figure sur le formulaire E14 de l'ASFC, joint au colis. Postes Canada le perçoit, frais de manutention de 9,95 $ inclus. Paie avec le bouton « Payer » dans Repérage ou dans l'appli avant que le colis soit sorti pour livraison ; ensuite, paie par carte de crédit à la porte, ou au bureau de poste en argent comptant, par carte, par chèque ou par mandat-poste de Postes Canada.

**Pas d'accord avec le montant ?** Refuse le colis et demande une nouvelle cotisation, ou paie et demande un rajustement avec le formulaire B2G, au verso du E14. Voir aussi [colis bloqué en douane](guide:customs).

## Colis Postes Canada envoyé à l'étranger : qui le livre

**Qui livre.** Si le numéro postal international finit par `CA` et que son chiffre de contrôle est bon, Peek peut aussi interroger, avec le même numéro, la poste du pays d'où tu suis le colis : par exemple [La Poste](carrier:la-poste) en France, [bpost](carrier:bpost) en Belgique, [La Poste suisse](carrier:swiss-post) en Suisse, [POST Luxembourg](carrier:post-luxembourg) au Luxembourg, [DHL](carrier:dhl) en Allemagne, USPS aux États-Unis et Royal Mail au Royaume-Uni (Parcelforce pour les numéros qui commencent par `E`).

Postes Canada ne donne à Peek aucun pays de destination, alors c'est ton pays qui compte : dans l'application iPhone, la région réglée sur ton iPhone ; dans un navigateur, le pays d'où tu as ajouté le colis. Dès que ta poste montre un scan au moins aussi récent que le dernier de Postes Canada, Peek suit le colis chez elle et garde l'historique de Postes Canada. Pendant encore une journée, il interroge aussi Postes Canada, au plus toutes les 55 minutes. Si tu suis le colis depuis le Canada, ou avec un numéro de 16 chiffres, Peek le suit seulement chez Postes Canada. Pour la douane du pays de destination, voir [colis bloqué en douane](guide:customs).

## Colis Postes Canada en retard, perdu, retourné ou introuvable

**Plus de nouveaux scans.** Après quatre jours sans nouveau scan à « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche : « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Canada Post. » Il ajoute un lien vers l'aide du transporteur qui a le colis, comme « Demande de l’aide à Canada Post ». Selon l'ASFC, le courrier à la douane au Canada ne peut pas être suivi avec précision. Voir [suivi qui ne bouge plus](guide:tracking-not-updating).

**En retard ou perdu.** Selon Postes Canada, la plupart des colis en retard arrivent dans les cinq jours ouvrables suivant la date prévue. Passé ce délai, vérifie l'adresse et regarde autour de chez toi, puis demande une enquête en ouvrant un billet de service sur la page [Colis manquants](https://www.canadapost-postescanada.ca/scp/fr/soutien/bc/reclamations/colis-manquants.page) de Postes Canada (ouvre d'abord une session pour pouvoir le suivre), dans les 90 jours suivant l'expédition, ou dans les six mois pour un colis envoyé aux États-Unis ou à l'étranger. L'expéditeur peut aussi la demander, et lui seul peut être indemnisé : si le colis n'arrive pas au bout de quelques jours, Postes Canada ferme ta demande et c'est à toi de demander à l'expéditeur de faire une réclamation. Si tu as acheté en ligne, commence par la boutique. Voir [colis perdu](guide:lost-parcel).

**Indiqué livré, mais pas là.** Selon la Loi sur la Société canadienne des postes, un envoi compte comme livré quand il est laissé chez toi ou à ton travail, mis dans ta boîte aux lettres ou confié à quelqu'un qui semble autorisé à le prendre pour toi, de la façon dont ton courrier est d'habitude distribué. Peek affiche « Livré », mais le suivi de Postes Canada précise l'endroit, par exemple `Delivered to mailroom` (livré à la salle du courrier) : regarde là d'abord. Vérifie ensuite le casier à colis, le comptoir de sécurité ou le concierge, demande aux personnes qui vivent avec toi et regarde dans Repérage si quelqu'un a signé, avant de demander une enquête. Si c'est la poste de ton pays qui l'a livré, regarde son suivi. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Retourné.** Une fois le colis revenu chez l'expéditeur, Peek affiche « Retourné à l’expéditeur ». Voir [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service à la clientèle de Postes Canada et contact

- **Téléphone** : 1 866 607-6301 au Canada, 1 416 979-3033 de l'extérieur du Canada, ATS 1 800 267-2797. Du lundi au vendredi de 7 h à 23 h HE, la fin de semaine de 9 h à 21 h HE.
- **En ligne** : clavardage, ou signalement d'un problème par billet de service, sur la page [Aide](https://www.canadapost-postescanada.ca/scp/fr/soutien.page) de Postes Canada.

Une fois qu'un colis parti du Canada est entre les mains de la poste de ton pays, c'est à elle qu'il faut s'adresser pour la livraison et le retrait ; les pages sur [La Poste](carrier:la-poste), [bpost](carrier:bpost), [La Poste suisse](carrier:swiss-post) et [POST Luxembourg](carrier:post-luxembourg) disent comment la joindre. Peek ne peut pas contacter Postes Canada ni la boutique à ta place.

> Les textos qui annoncent un colis retenu pour des frais impayés, avec un lien pour payer, ne viennent pas de Postes Canada. Ses propres textos viennent du 272727 ou du 55555 si tu t'es inscrit, ou du 613 734-1002 et du 613 734-1004, avec des liens vers canadapost-postescanada.ca. Vérifie les frais dans Repérage ; transfère les textos indésirables au 7726.

## Suivre un colis Postes Canada avec Peek

[Peek](/) reconnaît tout de suite Postes Canada pour les numéros postaux internationaux qui finissent par `CA` et dont le chiffre de contrôle est bon. Un numéro de 16 chiffres au chiffre de contrôle valide correspond à plusieurs transporteurs : Peek affiche « Vérification des services de suivi… », interroge en même temps Postes Canada, DHL eCommerce, TNT, Correos Express et Evri UK, et retient celui qui connaît le numéro. Pour un numéro d'avis de livraison ou une référence, touche « Changer » et choisis Canada Post, le nom de Postes Canada dans Peek, sous « Tous les transporteurs » ou avec la recherche. Un lien de suivi de Postes Canada qui contient `searchFor=` fonctionne aussi ; les liens avec `#/search` ne marchent qu'avec les numéros postaux qui finissent par `CA`, alors colle toi-même un numéro de 16 chiffres.

Peek interroge Postes Canada directement, sans navigateur. Tu vois le statut, l'historique avec le lieu de chaque scan (ville et province au Canada), le jour de livraison prévu, une plage horaire une fois le colis en livraison au Canada si Postes Canada en donne une, l'heure de livraison et, quand Postes Canada l'affiche en entier, le nom de l'expéditeur. Peek laisse de côté les options de livraison, les adresses, les signatures et les photos de livraison.

Peek vérifie toutes les 10 minutes, et toutes les 2 minutes dès que Postes Canada elle-même indique que le colis est en livraison ce jour-là, à partir de 6 h, heure locale, ou, si Postes Canada donne une plage horaire, à partir d'une demi-heure avant son début ; via des services de suivi, toutes les 15 minutes. Après 12 heures sans nouveau scan à « Étiquette créée » ou « Pris en charge », ou 48 heures à toute autre étape, il vérifie toutes les heures. Pour les notifications, choisis « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

**Vers le Canada.** Pour un colis posté à l'étranger avec un numéro postal international, Peek interroge aussi Postes Canada avec le même numéro dès que la poste d'origine nomme le Canada comme destination ou, si elle n'en nomme aucune, si tu suis le colis depuis le Canada. Dès que Postes Canada montre un scan au moins aussi récent que le dernier de la poste d'origine, Peek suit le colis chez elle et garde l'historique précédent. Un numéro qui commence par `L` et finit par `CH`, que Peek suit chez La Poste suisse, n'est jamais passé à Postes Canada.

## Questions sur le suivi Postes Canada

### Pourquoi mon suivi Postes Canada ne bouge plus ?

Les longs vols, la douane et les fins de semaine laissent souvent un trou entre deux scans. Peek continue de vérifier, toutes les heures une fois passés deux jours sans nouvelles. Si la date de livraison prévue est dépassée de plus de cinq jours ouvrables, demande une enquête à Postes Canada. Voir [suivi qui ne bouge plus](guide:tracking-not-updating).

### Pourquoi Peek ne trouve pas mon numéro de repérage Postes Canada ?

Postes Canada donne la même réponse qu'un numéro soit inconnu, pas encore scanné ou expiré : pour un colis Postes Canada, Peek affiche donc « En attente du transporteur » jusqu'au premier scan. Raisons habituelles selon Repérage : le colis n'est pas encore chez Postes Canada ou encore à l'étranger, il a été expédié il y a plus d'un an, ou le service ne comprend pas le repérage. Vérifie chaque chiffre : dans un numéro de 16 chiffres, le dernier est un chiffre de contrôle, et dans un numéro postal, c'est le dernier chiffre avant `CA`.

### Puis-je suivre un colis avec le numéro de l'avis de livraison ?

Oui. Colle le numéro à 15 chiffres de l'avis, touche « Changer » et choisis Canada Post sous « Tous les transporteurs ». Peek cherche alors le colis chez Postes Canada avec ce numéro.

### Qui livre un colis Postes Canada en France, en Belgique ou en Suisse ?

La poste de ton pays, si le numéro finit par `CA` : La Poste en France, bpost en Belgique, La Poste suisse en Suisse. Peek l'interroge avec le même numéro. Un numéro de 16 chiffres reste suivi chez Postes Canada.

:::sources
- [Postes Canada : Repérer un colis au moyen du numéro de repérage](https://www.canadapost-postescanada.ca/track-reperage/fr) – appli gratuite, « Avis laissé », « Disponible pour ramassage », date et pièce d'identité pour le ramassage, raisons d'un numéro introuvable
- [Postes Canada : Repérage – Comment utiliser l’outil Repérage](https://www.canadapost-postescanada.ca/scp/fr/soutien/bc/reperage/comment-utiliser-loutil-reperage.page) – seul l'expéditeur a le numéro, pas de recherche par nom ou adresse, numéro de carte
- [Postes Canada : Repérage – Statuts de suivi](https://www.canadapost-postescanada.ca/scp/fr/soutien/bc/reperage/statuts-de-suivi.page) – « Avis laissé », « Prêt à être ramassé », pièce d'identité, procuration, nouvel essai, douane
- [Postes Canada : Expédition de colis, Canada – Options de livraison](https://www.canadapost-postescanada.ca/scp/fr/soutien/sujet/services-expedition-colis-canada/options-livraison.page) – essais à la porte, lieu sûr, carte Avis final après cinq jours, 15 jours
- [Postes Canada : Pourquoi mon colis n’a pas été livré à mon casier à colis?](https://www.canadapost-postescanada.ca/scp/fr/soutien/bc/recevoir/casier-a-colis/pourquoi-mon-colis-na-pas-ete-livre.page) – cinq jours au casier
- [Soutien Postes Canada : Comment payer vos droits de douane et taxes](https://www.canadapost-postescanada.ca/scp/fr/soutien/bc/recevoir/faq-livraison/comment-payer-vos-droits-et-taxes-en-ligne-postes-canada.page) – frais de 9,95 $, quand et comment payer
- [Postes Canada : Réclamations – Colis manquants](https://www.canadapost-postescanada.ca/scp/fr/soutien/bc/reclamations/colis-manquants.page) – cinq jours ouvrables, où chercher, clé du casier, enquêtes, délais
- [Aide Postes Canada : Poser une question ou signaler un problème](https://www.canadapost-postescanada.ca/scp/fr/soutien.page) – téléphone, heures, clavardage, billets
- [Soutien Postes Canada : Reconnaître les courriels et messages textes frauduleux](https://www.canadapost-postescanada.ca/scp/fr/soutien/bc/politiques-entreprise/confidentialite-securite/que-faire-si-vous-recevez-un-courriel-suspect.page) – faux textos de frais, vrais expéditeurs, 7726
- [Canada Post: Responsible business practices](https://www.canadapost-postescanada.ca/cpc/en/our-company/corporate-sustainability/responsible-business-practices.page) – société d'État fédérale
- [ASFC : Importation par la poste](https://www.cbsa-asfc.gc.ca/import/personal-personnel/postal-postale-fra.html) – douane sur le courrier, exemption de 20 $, formulaire E14, frais de manutention, pas de suivi précis
- [ASFC : Importation par la poste ou par messagerie - Paiement des droits et des taxes sur les biens importés](https://www.cbsa-asfc.gc.ca/import/postal-postale/dtytx-drttx-fra.html) – TPS, TVH et TVP, limites de 20 $ CA et de 60 $ CA, droits
- [ASFC : Importation par la poste ou par messagerie - Contestation des droits et des taxes](https://www.cbsa-asfc.gc.ca/import/postal-postale/dispute-contestation-fra.html) – nouvelle cotisation, formulaire B2G
- [Site Web de la législation (Justice) : Loi sur la Société canadienne des postes](https://laws-lois.justice.gc.ca/fra/lois/C-10/page-1.html) – la Société, quand un envoi est considéré comme livré
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formes de numéros, libellés de Postes Canada et leurs étapes, ce que Peek affiche, passage d'une poste à l'autre
:::
