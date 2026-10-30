---
title: TopYou suivi colis : numéro TYZR, statuts et livraison
description: Suivre un colis TopYou (通邮) venu de Chine : le numéro TYZR, ce que disent ses scans en anglais et qui prend le relais en France, en Belgique et en Suisse.
slug: topyou-suivi-colis
published: 2026-10-30
updated: 2026-10-30
---

TopYou (通邮, ou 通邮集团) est 深圳市通邮物流科技（集团）股份有限公司, un transitaire de Shenzhen, en Chine, qui fait voyager des colis sur ses propres lignes internationales. Si une boutique chinoise a confié ta commande à TopYou, ton numéro commence par TYZR et finit par YQ : colle-le dans le champ ci-dessus. Le suivi de TopYou va de son entrepôt en Chine jusqu'à ton pays, vol et douane compris ; un transporteur local prend ensuite le relais.

## Numéro de suivi TopYou : à quoi il ressemble

Un numéro TopYou, c'est TYZR, une lettre, dix chiffres et YQ : 17 caractères en tout. Avec des chiffres inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `TYZRA0000000017YQ` | Un numéro TopYou. |
| `TYZRA000000017YQ` | Neuf chiffres seulement : pas un numéro TopYou. Vérifie qu'il ne manque pas un chiffre. |
| `JXCAB0000000017YQ` | Finit aussi par YQ, comme chez d'autres transitaires chinois, mais commence par JXC : un numéro [Juxi Express](carrier:juxi-express). |

Espaces et minuscules ne gênent pas Peek. Le site de suivi de TopYou, lui, distingue majuscules et minuscules : tapes-y le numéro en majuscules. Aucun lien n'y ouvre directement un colis. Si tu ne trouves pas le numéro, lis [où trouver son numéro de suivi](guide:find-tracking-number).

## Suivi TopYou : ce que veulent dire les statuts

TopYou écrit ses scans en anglais, et Peek les affiche tels quels. Dans l'ordre où un colis les reçoit :

| Ce qu'affiche TopYou | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Arrived at the first mile sorting center` | Premier scan : l'entrepôt de TopYou en Chine a le colis. | Pris en charge |
| `Departed from the first mile sorting center` | Il a quitté cet entrepôt. | En transit |
| `Arrived at airport of the original country` | À l'aéroport de départ, en Chine, avant le vol. | En transit |
| `Departed from Airport to destination country` | Le vol vers ton pays est parti. | En transit |
| `Arrived at destination airport` | Il a atterri dans ton pays. | En transit |
| `Departed from customs of` | La douane de ton pays l'a libéré. TopYou laisse le pays vide après « of », et Peek retire ce « of ». | En transit |

**Numéros plus récents.** Sur ceux-ci, TopYou formule les étapes autrement :

| Ce qu'affiche TopYou | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Carrier received the shipment` | Premier scan : TopYou a le colis en Chine. | Pris en charge |
| `Shipment arrived at a carrier facility` | Le colis quitte le premier entrepôt de TopYou. Ce n'est pas une deuxième prise en charge. | En transit |
| `Shipment started the customs clearance process at origin` | Dédouanement à l'export, en Chine, avant le vol : pas encore la douane de ton pays. | À la douane |
| `Shipment arrived in the destination country` | Le colis est arrivé dans ton pays. | En transit |
| `Shipment started the customs clearance process at destination` | Le dédouanement à l'import a commencé dans ton pays. | À la douane |
| `Shipment completed the customs clearance process at destination` | La douane a fini ; le colis part chez le transporteur qui va le livrer. | En transit |

**Ensuite.** Le site de TopYou peut reprendre les scans du transporteur local jusqu'à la livraison, dans les mots de ce transporteur et avec leur lieu.

## Qui livre les colis TopYou en France, en Belgique et en Suisse

Après la douane, TopYou remet le colis à un transporteur de ton pays. La fiche TopYou, affichée sur son site, peut donner le numéro sous lequel ce transporteur suit le colis, mais pas son nom. Le nom de la ligne TopYou ne le dit pas non plus : un colis livré par la poste américaine a voyagé sur une ligne appelée « DHL Paket ». Demande donc à la boutique qui livre ta commande, et sous quel numéro.

Si c'est la poste de ton pays qui a le colis :

**France : La Poste (Colissimo).** Suis aussi le colis chez elle ; pour l'avis de passage et le délai de garde, consulte la page [La Poste](carrier:la-poste).

**Belgique : [bpost](carrier:bpost).** Venu de Chine, le colis passe par une procédure d'importation dès son arrivée en Belgique ; tant que le suivi de bpost affiche « En route pour : Belgique », il n'y est pas encore. Si la TVA n'a pas été payée à l'achat, ou si le colis vaut plus de 150 €, le service de dédouanement de bpost calcule d'abord les frais d'importation d'après la facture, qu'il peut te demander ; tu as 14 jours pour les payer. La plupart des envois sont dédouanés en une semaine (prévois 3 à 7 jours en cas de contrôles supplémentaires) ; une fois le colis libéré, bpost te le fait parvenir quelques jours plus tard. Un code-barres qui commence par U ne sert qu'à l'importation et ne se suit pas.

**Suisse : [La Poste suisse](carrier:swiss-post).** En douane, le traitement peut durer plusieurs jours. Si tu étais absent, le lieu de retrait figure sur l'invitation à retirer un envoi. En général, un colis peut y être retiré pendant 7 jours, ou 15 pour un envoi international, et tu peux prolonger ce délai gratuitement, sans compte.

Le détail du retrait est dans [avis de passage : que faire](guide:missed-delivery) et [colis non retiré](guide:uncollected-parcel).

## Colis TopYou bloqué, perdu ou pas arrivé : que faire

**Bloqué à la douane.** Avec `Shipment started the customs clearance process at destination`, ou « À la douane » dans Peek après le vol, le colis attend le dédouanement à l'import. Pour les frais, lis [colis bloqué en douane](guide:customs) ; si rien ne bouge pendant des jours, lis [pourquoi un suivi ne bouge plus](guide:tracking-not-updating) et préviens la boutique. En France, la douane le dit : pour un colis « bloqué », inutile de s'adresser à elle ; contacte la société qui livre. Elle ne te contactera jamais par SMS ou par e-mail pour te faire payer des droits et taxes.

**Plus rien après la douane.** Après `Departed from customs of` ou `Shipment completed the customs clearance process at destination`, le transporteur local devrait avoir le colis. Cherche son numéro sur le site de TopYou et suis le colis chez lui, ou demande à la boutique. Voir aussi [le suivi d'un colis venu de Chine](guide:tracking-from-china).

**Introuvable ou pas encore de scan.** Le site de TopYou répond 查询不到 (introuvable) à un numéro inconnu, et peut répondre 暂无轨迹信息 (pas encore d'informations de suivi) à un numéro connu sans scan. Peek affiche alors « En attente du transporteur » et continue de vérifier. Compare le numéro avec le message de la boutique, caractère par caractère ; si rien ne vient après quelques jours, demande-lui si le colis est vraiment parti.

**Livré mais pas reçu.** C'est le transporteur local qui a livré : commence par lui, puis la boutique. Voir [colis livré mais non reçu](guide:delivered-not-received).

**Perdu ou renvoyé.** La boutique a réservé l'envoi chez TopYou : c'est à elle, ou à la plateforme, que tu demandes un remboursement ou un nouvel envoi. En Belgique, bpost le dit aussi : le destinataire ne peut pas introduire lui-même de plainte ; bpost l'invite à contacter l'expéditeur. Voir [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).

## TopYou contact : qui joindre

Au 30 octobre 2026, Peek n'a pas pu vérifier le site de TopYou : pas de contact TopYou ici. Pour ta commande, adresse-toi à la boutique, puis à la plateforme si elle ne répond pas. Si la poste de ton pays a déjà le colis :

- **bpost :** 02 278 51 27, le chat de Track & Trace, un Bureau de Poste, Point Poste ou Point Colis, ou une lettre sans timbre à bpost, Boîte 5000, 1000 Bruxelles.
- **La Poste suisse :** +41 848 888 888 (CHF 0.08 la minute depuis le réseau fixe suisse), du lundi au vendredi de 7 h 30 à 18 h et le samedi de 8 h à 12 h, ou le chat en ligne.
- **La Poste (France) :** ses contacts sont sur la page [La Poste](carrier:la-poste).

## Suivre un colis TopYou avec Peek

[Peek](/) reconnaît un numéro TopYou à sa forme, qu'aucun autre transporteur de son catalogue n'utilise, et n'interroge TopYou qu'avec ces numéros-là. Si tu n'as que le numéro du transporteur local, colle-le quand même : Peek suit plus de 3 500 transporteurs. Si Peek ne reconnaît pas le transporteur, choisis-le avec « Changer ».

Peek lit le site de TopYou et affiche le statut, chaque scan dans les mots de TopYou ou du transporteur dont il reprend les scans, le pays de destination et le poids enregistré par TopYou. Quand la fiche TopYou marque le colis livré ou renvoyé, Peek affiche « Livré » ou « Retourné à l'expéditeur », même si la dernière ligne dit autre chose. Les scans de TopYou sont à l'heure de Pékin, ceux du transporteur local à l'heure locale ; Peek les affiche dans le fuseau de ton appareil. Pas de date de livraison prévue : TopYou n'en donne pas.

Jusqu'au passage de relais, Peek n'affiche que le numéro TopYou. Si la fiche TopYou contient le numéro du transporteur local, Peek s'en sert pour se renseigner : s'il s'agit d'un numéro postal international valide (deux lettres, neuf chiffres, deux lettres), il interroge la poste du pays de destination indiqué par TopYou, soit La Poste en France, bpost en Belgique et La Poste suisse en Suisse ; sinon, le seul transporteur dont c'est à coup sûr la forme de numéro, si Peek lit son suivi. Ce n'est qu'une question, pas la preuve que ce transporteur livre. Dès qu'il montre un scan au moins aussi récent que le dernier de TopYou, Peek suit le colis chez lui, affiche les deux numéros et garde les scans de TopYou. Un numéro qui ne désigne aucun transporteur à coup sûr, comme 20 chiffres seuls, n'est pas essayé : Peek reste sur le suivi de TopYou.

Peek vérifie toutes les 10 minutes en journée, toutes les 2 minutes une fois le colis en livraison et au moins toutes les deux heures la nuit. Ses notifications : « Chaque scan », « Étapes importantes » (les lignes de douane, export en Chine compris, et la livraison, mais pas le vol ni la sortie de douane) ou « Livraison seulement ». Peek ne peut contacter ni TopYou, ni la boutique, ni la poste à ta place.

## Questions sur le suivi TopYou

### C'est quoi, TopYou (通邮) ?

深圳市通邮物流科技（集团）股份有限公司, dit 通邮 ou 通邮集团, est un transitaire de Shenzhen, en Chine, avec ses propres lignes internationales. Ses numéros : TYZR, une lettre, dix chiffres et YQ.

### Qui livre les colis TopYou en France, en Belgique et en Suisse ?

Un transporteur de ton pays, dont la fiche TopYou peut donner le numéro, mais pas le nom : demande à la boutique. Si ce numéro est postal, Peek interroge lui-même la poste du pays : La Poste, bpost ou La Poste suisse.

### Pourquoi TopYou affiche-t-il mon pays alors que le colis est encore en Chine ?

Parce que TopYou met le pays de destination comme lieu de ses propres scans, même à l'aéroport de départ ; Peek retire ce lieu. Fie-toi au texte : `Arrived at airport of the original country` veut dire que le colis attend encore son vol.

### Qui contacter si mon colis TopYou n'arrive pas ?

La boutique où tu as commandé : c'est elle qui a réservé l'envoi chez TopYou. Si la poste de ton pays a déjà le colis, contacte-la pour la livraison.

:::sources
- [bpost : Où se trouve mon envoi international ?](https://www.bpost.be/fr/faq/pourquoi-mon-envoi-international-na-t-il-pas-encore-ete-livre) – importation, « En route pour : Belgique », frais et facture, délais, codes-barres en U
- [bpost : Comment introduire une plainte en cas de colis endommagé, perdu ou non livré ?](https://www.bpost.be/fr/faq/comment-introduire-une-plainte-en-cas-de-colis-endommage-perdu-ou-non-livre) – plainte du destinataire
- [bpost : Comment contacter le service clientèle de bpost ?](https://www.bpost.be/fr/faq/comment-contacter-le-service-clientele-de-bpost) – téléphone, chat, bureaux, courrier
- [La Poste suisse : Invitation à retirer un envoi](https://www.post.ch/fr/reception/invitation-a-retirer) – lieu de retrait, 7 et 15 jours, prolongation gratuite sans compte
- [La Poste suisse : Questions et réponses concernant la réception](https://www.post.ch/fr/aide-et-contact/reception) – douane
- [La Poste suisse : Aide et contact](https://www.post.ch/fr/aide-et-contact) – téléphone, tarif, horaires, chat
- [Douane : Mon colis est bloqué](https://www.douane.gouv.fr/fiche/mon-colis-est-bloque) – à qui s'adresser, SMS et courriels
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – TopYou : société, numéros, site de suivi, scans, deuxième numéro ; ce que Peek affiche et le passage de relais
:::
