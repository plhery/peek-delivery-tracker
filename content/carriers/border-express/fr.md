---
title: Suivi Border Express (BEX) : numéros d’envoi et statuts
description: Suivi d’un colis Border Express (BEX) en Australie : numéros d’envoi et codes-barres d’étiquette, sens des statuts et fonctionnement dans Peek.
slug: suivi-border-express
published: 2026-11-22
updated: 2026-11-22
---

Border Express, ou BEX, est un transporteur que le catalogue de Peek répertorie uniquement en Australie. Si une boutique en ligne a expédié ta commande par Border Express, le numéro à suivre est son numéro d’envoi (*consignment number*) : colle-le dans la case ci-dessus. La boutique l’indique dans son e-mail d’expédition ou sur ta page de commande. Le propre suivi de Border Express est Quick Track sur BEX Online (`bexonline.borderexpress.com.au`). Peek n’ayant pas pu consulter le site web de Border Express pour cette page, ce qui suit repose sur ce que le code de Peek sait de cet outil de suivi.

## Numéro de suivi Border Express : à quoi il ressemble

Border Express suit une livraison grâce à son numéro d’envoi (*consignment number*). Le code de Peek connaît trois formats. Avec des caractères inventés :

| Numéro (inventé) | Ce que c’est |
| --- | --- |
| `48291736` | 8 chiffres : un numéro d’envoi. |
| `KLMN482917` | 4 lettres (préfixe de l’expéditeur), suivies de chiffres : également un numéro d’envoi. Peek accepte de 5 à 8 chiffres après les lettres. |
| `KLMNBX00000482917001` | 20 caractères : le code-barres imprimé sur l’étiquette d’un article. Les 4 lettres de l’expéditeur, `BX`, les chiffres de l’envoi complétés à gauche par des zéros sur 11 positions, et un numéro d’article à 3 chiffres. |

Un envoi peut comporter plusieurs articles, chacun muni de son propre code-barres d’étiquette. Toutes les étiquettes commencent par les mêmes lettres et chiffres, et le numéro d’envoi les relie : c’est ce numéro que recherche le suivi Border Express, qui n’accepte pas un code-barres d’étiquette. Peek, en revanche, l’accepte et en déduit le numéro d’envoi (voir ci-dessous).

Peek ne peut pas reconnaître un numéro Border Express d’après sa seule forme : son catalogue ne possède aucune règle pour les numéros d’envoi Border Express, et la règle pour le code-barres d’étiquette ne constitue qu’une correspondance faible. D’autres transporteurs utilisent les mêmes formes : pour 8 chiffres, Peek propose [Mondial Relay](carrier:mondial-relay), [Colisweb](carrier:colisweb) et [Heppner](carrier:heppner), tous en Europe, ainsi que TForce Freight (fret nord-américain) lorsque le dernier chiffre passe son contrôle. Quatre lettres suivies de 8 chiffres correspondent aussi aux numéros à 12 caractères de [Colis Privé](carrier:colis-prive). Si la boutique indique Border Express, choisis Border Express manuellement (ci-dessous). Les espaces et les minuscules n’ont aucune importance lorsque tu colles le numéro. Pas de numéro du tout ? Consulte [où trouver ton numéro de suivi](guide:find-tracking-number) et [formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi Border Express

Le suivi de Border Express affiche une barre de progression et une liste d’étapes, de la plus récente à la plus ancienne, chacune accompagnée d’une localité et d’un État. Voici les étapes que le code de Peek connaît, dans l’ordre habituel :

| Border Express indique | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Shipment Data Received` | L’expéditeur a transmis les données du colis à Border Express. Border Express n’a pas encore le colis. La barre de progression affiche `Data Received`. | « Étiquette créée » |
| `Picked Up` | Border Express a collecté le colis chez l’expéditeur. Le suivi indique souvent cette étape sans horaire, et Peek écarte toute étape sans horaire. | « Pris en charge », uniquement lorsque l’étape a une heure |
| `In Transit` | En route dans le réseau de Border Express. | « En transit » |
| `On Board For Delivery` | À bord du véhicule de livraison. La barre de progression affiche `On Board`. | « En livraison » |
| `Partially Delivered` | Certains articles de l’envoi sont livrés ; les autres sont toujours en route. | « En transit » |
| `Partially Delivered - Final` | La livraison est terminée : l’envoi est clôturé avec les articles arrivés. S’il en manque, contacte l’expéditeur. | « Livré » |
| `Delivered` | Livré. | « Livré » |

Peek conserve les libellés anglais de Border Express, surmontés de son propre statut. Une étape absente de ce tableau s’affiche telle quelle, et Peek en déduit le statut d’après les mots employés lorsqu’il le peut.

## Livraison Border Express en Australie

Le suivi Border Express ne nomme aucun autre transporteur pour un colis : Peek suit donc l’envoi chez Border Express de la première étape jusqu’à la livraison, sans relais vers un autre transporteur.

Cette page ne donne aucune règle sur les tentatives de livraison, les avis de passage, le dépôt sans signature, le retrait en dépôt ou le délai de garde, car Peek n’a pas pu vérifier le site web de Border Express. Demande à la boutique qui l’a envoyé ou à Border Express. Voir [livraison manquée](guide:missed-delivery) et [colis non retirés](guide:uncollected-parcel).

## Colis Border Express bloqué, perdu ou livré mais non reçu

**Numéro introuvable.** Tant que le suivi Border Express ne connaît pas le numéro, un colis enregistré sous Border Express affiche « En attente de Border Express » et Peek continue de vérifier. Si Border Express ne le connaît toujours pas deux jours plus tard sans autre source qui réponde, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » et passe à une vérification par jour, puis par semaine passée une semaine. Vérifie le numéro dans l’e-mail de la boutique : 8 chiffres, ou 4 lettres et 5 à 8 chiffres. Si rien n’apparaît après quelques jours, demande à la boutique si le colis a vraiment été expédié.

**Bloqué sur « Étiquette créée ».** Border Express possède les données du colis mais aucun scan horodaté pour le moment. Son suivi affiche peut-être `Picked Up` sans heure — que Peek laisse de côté —, vérifie donc là-bas en premier. S’il n’indique encore que `Shipment Data Received`, demande à la boutique quand elle a remis le colis.

**Aucune mise à jour depuis des jours.** Lorsqu’un colis en route n’a pas eu de nouvelle étape depuis quatre jours, Peek affiche la date du dernier scan (« Aucune mise à jour depuis … ») et indique « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Border Express. », avec le lien « Demande de l’aide à Border Express » vers sa page de service client. Voir [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**Partiellement livré.** Compare ce que tu as reçu avec ta commande. Le reste de l’envoi est toujours en route, et le suivi Border Express détaille les scans de chaque article individuel, que Peek n’affiche pas.

**Livré, mais rien reçu.** Vérifie autour de ton adresse et demande aux voisins. Une fois livré, Peek lit aussi le nom du signataire lorsqu’il figure sur le suivi Border Express. Ensuite, contacte la boutique. Plus dans [livré mais non reçu](guide:delivered-not-received).

**Perdu, abîmé ou retourné.** La boutique a réservé Border Express : c’est donc à elle qu’il faut demander un remboursement ou un nouvel envoi. Voir [colis perdus](guide:lost-parcel), [colis abîmés](guide:damaged-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Contact et service client Border Express

Peek n’ayant pas pu consulter le site de Border Express, cette page n’indique ni numéro de téléphone ni adresse e-mail : Peek ne cite que les contacts lus sur les propres pages d’un transporteur. Comme la boutique a réservé la livraison, adresse-toi d’abord à elle.

Sur la fiche du colis dans Peek, « Ouvrir le site Border Express » ouvre ton numéro sur le suivi Border Express. Avant de payer quoi que ce soit à la suite d’un SMS de livraison, lis [arnaques aux faux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis Border Express avec Peek

Colle le numéro dans [Peek](/), qui suit plus de 4 500 transporteurs. Il peut afficher « Vérification des services de suivi… » pendant qu’il interroge d’autres sources. Appuie ensuite sur « Changer » et choisis Border Express en tapant `Border Express` dans la recherche (`BEX` le trouve aussi, mais affiche d’abord BEX Express, un transporteur en Serbie). Pour un code-barres d’étiquette, Border Express figure sous « Correspond à ce numéro ». Sur un colis déjà ajouté, utilise « Changer de transporteur ». Aucun compte, code postal ni numéro de téléphone n’est requis.

Si tu ajoutes le numéro sans sélectionner de transporteur, Peek n’interroge que les services de suivi, jamais Border Express, et affiche « Aucun historique de suivi pour le moment » jusqu’à ce que l’un d’eux trouve le colis. Ce n’est que si un service de suivi nomme Border Express que Peek interroge ensuite Border Express. Choisir Border Express toi-même est donc le meilleur moyen d’obtenir ses étapes.

Un lien de suivi Border Express fonctionne aussi et identifie Border Express aussitôt (« Transporteur détecté ») : `borderexpress.com.au/tracking` avec `cn=`, ou un lien `bexonline.borderexpress.com.au/bxo/track`. Si tu choisis Border Express pour un numéro d’une autre forme, Peek indique « n’émet pas de numéros comme celui-ci », et une fois ajouté (sauf si un service de suivi connaît le colis) affiche « Numéro de suivi à vérifier » : vérifie s’il manque ou s’il y a un caractère en trop.

**Codes-barres d’étiquette.** Le suivi Border Express ne les accepte pas, donc Peek reconstitue le numéro d’envoi à partir du code-barres : les quatre lettres, puis les 11 chiffres après `BX` sans les zéros initiaux (en gardant au moins six chiffres), sans les 3 derniers chiffres correspondant au numéro d’article. La fiche du colis affiche ce numéro sous « Border Express le suit sous le numéro », et « Ouvrir le site Border Express » ouvre aussi Quick Track avec ce numéro d’envoi. Si Border Express ne le trouve pas ainsi, utilise le numéro d’envoi figurant dans l’e-mail de la boutique.

**Ce que tu vois.** Peek affiche le statut, chaque étape horodatée avec sa localité et son État, le jour de livraison prévu jusqu’à la livraison, l’heure de livraison, la localité du destinataire (banlieue, État et code postal) ainsi que le nom du signataire une fois livré. Il laisse de côté la barre de progression, la date d’expédition, le nombre d’articles et les scans unitaires de chaque étiquette, ainsi que la localité de l’expéditeur ; aucun poids ni preuve de livraison n’est affiché. Chaque étape porte le fuseau horaire de son État (soit dans l’ordre exact entre États sur des fuseaux différents), affiché dans ton fuseau horaire (« Heures dans ton fuseau horaire : » suivi de son nom).

**Fréquence.** Tant que tu as le colis ouvert dans Peek — ou le jour de la livraison si les notifications sont activées — Peek interroge directement Border Express jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes dès 6 h, heure de Sydney, le jour où Border Express scanne le colis « En livraison », tant que son suivi répond ; sinon, et après deux jours sans nouveauté (12 heures tant qu’il reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Si le suivi de Border Express est injoignable, Peek interroge des services de suivi et affiche ce qu’ils détiennent, dans leurs propres mots. Il peut t’alerter pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut pas contacter Border Express ni la boutique pour toi.

## Questions sur le suivi Border Express

### Qu’est-ce qu’un numéro d’envoi Border Express ?

C’est le numéro utilisé par Border Express pour suivre une livraison : 8 chiffres, ou les 4 lettres de l’expéditeur suivies de chiffres. Tous les articles d’une même livraison y sont rattachés. La boutique le donne dans l’e-mail d’expédition ou sur ta page de commande.

### Puis-je suivre Border Express avec le code-barres sur l’étiquette ?

Dans Peek, oui : colle le code-barres à 20 caractères (4 lettres, `BX` et 14 chiffres) et choisis Border Express. Peek en déduit automatiquement le numéro d’envoi. Le suivi propre de Border Express n’accepte que le numéro d’envoi.

### Que signifie « On Board For Delivery » sur le suivi Border Express ?

Le colis est à bord du véhicule de livraison. Peek l’affiche sous le statut « En livraison ».

### Pourquoi mon suivi Border Express affiche-t-il « Partially Delivered » ?

Ton envoi comprend plusieurs articles et seuls certains ont été livrés ; les autres sont toujours en route, donc Peek affiche « En transit ». `Partially Delivered - Final` est différent : Border Express a clôturé l’envoi avec les articles arrivés et Peek affiche « Livré ».

### Que signifie « Shipment Data Received » sur le suivi Border Express ?

L’expéditeur a transmis les données du colis à Border Express, mais Border Express ne l’a pas encore scanné. Peek affiche « Étiquette créée ».

:::sources
- [Universal Parcel Scraper: Border Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/border-express) – noms, Australie, numéros d’envoi et codes-barres d’étiquette, liens de suivi, outil Quick Track, libellés et étapes, étapes sans heure, fuseaux des États, estimation et heure de livraison, localité destinataire et signataire, champs omis, absence de relais
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats de numéros d’autres transporteurs (Mondial Relay, Colisweb, Heppner, Colis Privé), nom de BEX Express, absence de transfert vers ou depuis Border Express
:::
