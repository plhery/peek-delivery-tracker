---
title: Delivengo suivi : ton colis La Poste envoyé de France
description: Suivi Delivengo hors de France : le numéro en FR, ce que dit le suivi de La Poste, qui livre en Belgique, en Suisse ou au Canada et qui contacter.
slug: delivengo-suivi
published: 2026-10-12
updated: 2026-10-28
---

Delivengo est le service d'export de [La Poste](carrier:la-poste) pour les petits colis internationaux. Si une boutique française l'a choisi, ton colis quitte la France avec La Poste et c'est la poste de ton pays qui fait le dernier kilomètre : bpost en Belgique, la Poste suisse, [Postes Canada](carrier:canada-post) au Québec. Tu le suis sur le suivi de La Poste, sur laposte.fr, avec le numéro que la boutique te donne, ou en le collant dans la case ci-dessus.

## Numéro de suivi Delivengo : où le trouver

Les numéros Delivengo ont la forme postale internationale : deux lettres, neuf chiffres et `FR`, par exemple `LD000000005FR` (inventé).

**D'où il vient.** C'est la boutique qui te le donne, en général dans son e-mail d'expédition. Le site MyDelivengo, mydelivengo.laposte.fr, est l'espace client de la boutique, pas une page où tu peux le retrouver. Si tu ne le trouves pas, lis [où trouver son numéro de suivi](guide:find-tracking-number).

**Les numéros qui se ressemblent** (tous inventés) :

| Numéro | À qui il appartient |
| --- | --- |
| `LD000000005FR` | La Poste : Delivengo, mais aussi les autres envois de La Poste ou de Colissimo qui ont cette forme. |
| `RR000000005BE` | Un numéro de [bpost](carrier:bpost) : la poste belge, pas Delivengo. |
| `LL000000005CH` | Un numéro de la [Poste suisse](carrier:swiss-post), pas Delivengo. |
| `LD000000005GB` | Un numéro de Royal Mail, la poste britannique. |

Les deux dernières lettres disent quelle poste a créé le numéro. Rien dans un numéro en `…FR` ne distingue Delivengo d'un autre envoi de La Poste, et ce n'est pas grave : le suivi est le même. Ces numéros ont une clé de contrôle : si un chiffre est faux, le numéro n'est plus valable et Peek ne le reconnaît plus. Copie-le plutôt que de le retaper.

## Delivengo suivi colis : ce que dit le suivi

Le suivi de La Poste répond aux numéros Delivengo comme à n'importe quel envoi de La Poste. Peek range chacun de ses scans dans une étape ; voici ce que veut dire le scan de La Poste derrière chacune de ces étapes :

| Étape dans Peek | Ce que veut dire le scan de La Poste |
| --- | --- |
| `Étiquette créée` | L'envoi est annoncé : la boutique a créé l'étiquette, mais La Poste n'a pas encore le colis. |
| `Pris en charge` | La Poste a pris le colis en charge. |
| `En transit` | Le colis avance : en France, prêt à quitter la France, ou arrivé dans ton pays et en route. |
| `À la douane` | Le colis est entré en douane. Une fois dédouané, il repasse `En transit`. |
| `Livré` | Le colis a été livré. S'il n'est pas là, lis [colis livré mais non reçu](guide:delivered-not-received). |
| `Problème signalé` | Quelque chose ne va pas (un dommage, un refus, une adresse fausse), ou le colis repart chez la boutique. Le scan de La Poste peut en donner la raison : adresse incorrecte, délai de retrait dépassé ou documents d'import non conformes. |
| `Retourné à l’expéditeur` | Le colis est revenu chez la boutique. |

Après l'export, c'est la poste de ton pays qui fait le dernier kilomètre, et l'historique de La Poste peut rester muet : le dernier scan visible peut être la sortie de France ou l'arrivée dans ton pays.

## Delivengo livraison : qui livre et que faire en cas d'absence

**Qui livre.** La poste de ton pays fait le dernier kilomètre, pas La Poste.

**Si tu es absent.** Ce sont les règles de ta poste qui s'appliquent, pas celles de La Poste : comment elle te prévient, combien de temps elle garde le colis et si elle repasse. Lis [avis de passage](guide:missed-delivery).

**Douane.** Entre la France et la Belgique ou le Luxembourg, pas de droits de douane : selon l'UE, aucun droit de douane n'est dû quand des marchandises passent d'un pays de l'UE à un autre. Pour la Suisse ou le Canada, hors de l'UE, ce sont les règles de ton pays : lis [colis bloqué en douane](guide:customs).

## Colis Delivengo en retard, bloqué ou perdu

La boutique a envoyé le colis depuis son compte Delivengo : commence par elle.

**Pas de mise à jour depuis des jours.** Après l'export, l'historique de La Poste peut rester muet pendant que ta poste fait le dernier kilomètre. Peek suit aussi la poste de certains pays (voir plus bas). Si rien ne bouge, écris à la boutique. Les causes habituelles sont dans [pourquoi un suivi ne bouge plus](guide:tracking-not-updating).

**Perdu ou abîmé.** Demande à la boutique un nouvel envoi ou un remboursement. Si tu vis dans l'UE, en Belgique ou au Luxembourg par exemple, Your Europe rappelle que le commerçant est responsable de tout dommage causé à tes marchandises, de l'expédition à la réception. Sans livraison dans les 30 jours, ou à la date convenue, signale-le-lui et laisse-lui un délai supplémentaire raisonnable ; s'il ne livre toujours pas, tu as le droit de mettre fin au contrat et d'être remboursé. Lis [colis perdu](guide:lost-parcel).

**Retour.** Un colis impossible à livrer repart chez la boutique : Peek affiche `Problème signalé` pendant le trajet retour, puis `Retourné à l’expéditeur`. Demande à la boutique si elle le renvoie. Lis [colis retourné à l'expéditeur](guide:returned-to-sender).

## Delivengo contact : à qui s'adresser

- **La boutique d'abord.** C'est elle qui a envoyé le colis et qui a le compte Delivengo.
- **La Poste, pour une réclamation.** Selon l'Arcep, le régulateur postal, tu peux joindre La Poste au 3631 (du lundi au vendredi de 8 h 30 à 19 h, le samedi de 8 h 30 à 13 h) ou au +33 810 821 821 (du lundi au vendredi de 8 h à 19 h), sur son site, par courrier ou dans un point de contact postal. L'Arcep précise que les droits de l'expéditeur et du destinataire ne sont pas tout à fait les mêmes, et ne dit pas si ces canaux traitent les colis Delivengo.
- **Ta poste locale** une fois le colis dans ton pays, avec le même numéro.

## Suivre un colis Delivengo avec Peek

[Peek](/) n'a pas de connexion Delivengo à part : il suit ces colis par le suivi de La Poste, comme n'importe quel envoi La Poste ou Colissimo. Colle le numéro en `…FR` et Peek nomme tout de suite « La Poste / Colissimo » ; tu peux choisir « Delivengo » avec « Changer », le suivi est le même. Un lien de suivi laposte.fr, y compris depuis mydelivengo.laposte.fr, marche aussi.

Peek affiche ce que La Poste affiche : le statut, l'historique avec le pays de chaque scan, et la livraison prévue quand La Poste la donne. Il ne reprend pas ton nom ni ton adresse. Les statuts restent dans le français de La Poste.

Quand La Poste nomme un partenaire ou donne le pays de destination, Peek interroge aussi la poste qu'il suit dans ce pays et passe sur elle dès qu'elle montre une étape plus récente. Il le fait pour la Belgique, le Luxembourg, la Suisse et le Canada, comme pour tous les pays de l'UE, le Royaume-Uni ou les États-Unis. L'historique de La Poste peut toutefois rester muet après l'export : la dernière étape n'apparaît donc pas toujours.

Peek vérifie toutes les 10 minutes en journée, toutes les 2 minutes une fois le colis en livraison, toutes les une à deux heures la nuit, et peut t'envoyer des notifications : « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

## Questions sur le suivi Delivengo

### Delivengo, c'est La Poste ou Colissimo ?

Delivengo appartient à La Poste, comme Colissimo, et partage son suivi, mais c'est un service à part : le service d'export de La Poste pour les petits colis internationaux.

### Pourquoi le suivi Delivengo s'arrête-t-il après la France ?

Une fois le colis sorti de France, c'est la poste de ton pays qui fait le dernier kilomètre, et l'historique de La Poste peut rester muet. Le colis peut très bien être encore en route. Peek interroge aussi la poste de certains pays (voir plus haut) ; si rien ne bouge, écris à la boutique.

### Peut-on suivre un colis Delivengo jusqu'en Suisse ou au Canada ?

Oui, avec le même numéro en `…FR` sur le suivi de La Poste. Quand La Poste donne le pays de destination, Peek interroge aussi la [Poste suisse](carrier:swiss-post) ou [Postes Canada](carrier:canada-post), et la suit dès qu'elle montre une étape plus récente. Pour la Belgique, il interroge de même bpost.

:::sources
- [Universal Parcel Scraper : Delivengo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/delivengo) – service d'export de La Poste, dernier kilomètre par la poste du pays, historique muet après l'export, espace client de la boutique, pas de règle de numéro propre
- [Universal Parcel Scraper : La Poste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – suivi partagé, forme du numéro et clé de contrôle, sens de chaque scan
- [Union européenne : L’union douanière de l’UE en action](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_fr) – pas de droits de douane entre pays de l'UE
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – commerçant responsable des dommages jusqu'à la réception, livraison non effectuée
- [Arcep : La résolution des litiges postaux](https://www.arcep.fr/mes-demarches-et-services/consommateurs/fiches-pratiques/la-resolution-des-litiges-postaux.html) – réclamation à La Poste : 3631, +33 810 821 821, horaires, site, courrier, point de contact postal, droits de l'expéditeur et du destinataire
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek lit et affiche
:::
