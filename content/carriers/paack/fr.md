---
title: Paack suivi colis : numéro, code postal et statuts
description: Suivre un colis Paack en France : numéro de commande et code postal, ce que veulent dire les statuts, les PaackGo Points, le lien avec Colis Privé et le téléphone.
slug: paack-suivi
published: 2026-10-11
updated: 2026-10-27
---

Paack livre les colis des boutiques en ligne sur le dernier kilomètre, à ta porte ou dans un point de retrait. Rapprochés à l'été 2026, Paack et [Colis Privé](carrier:colis-prive) sont présents ensemble en France, en Belgique, au Luxembourg, en Espagne et au Portugal. Pour suivre ton colis, il te faut le numéro de commande et le code postal de livraison : saisis-les sur la page de suivi de Paack, sur paack.co, ou colle le numéro dans le champ ci-dessus et choisis Paack.

## Paack suivi numéro : numéro de commande et code postal

La page de suivi de Paack demande le numéro de commande de la boutique ou le code-barres de l'étiquette, toujours avec le code postal de livraison. Pas de numéro ? [Où trouver son numéro de suivi](guide:find-tracking-number) donne des pistes.

La plupart de ces numéros n'ont pas de forme fixe, si bien qu'un numéro de commande peut ressembler à celui d'un autre transporteur (exemples inventés) :

| Numéro de commande | À quoi il ressemble |
| --- | --- |
| `123456789AB` | À aucun transporteur en particulier. |
| `1234567890AB` | 12 lettres et chiffres : à un numéro Colis Privé. |
| `12345678` | 8 chiffres : à un numéro [Mondial Relay](carrier:mondial-relay), [Colisweb](carrier:colisweb) ou Heppner. |
| `123456789012` | 12 chiffres : à un numéro [FedEx](carrier:fedex), [DPD France](carrier:dpd-fr), Colis Privé et d'autres. |

Si la boutique t'indique que Paack livre ta commande, c'est un numéro Paack, quelle que soit sa forme. Si ton e-mail de suivi vient de livraison@notification.colisprive.com, seule adresse des e-mails de suivi de Colis Privé, suis ton colis sur [colisprive.fr](https://colisprive.fr/) avec ton code postal.

## Paack suivi colis : ce que veulent dire les statuts

La page de suivi de Paack résume le parcours en quatre étapes (commande reçue, arrivée au centre de distribution, en livraison, livré), et son historique ajoute les étapes intermédiaires. Sur sa page en français, l'arrivée chez Paack s'affiche ainsi :

| Paack écrit | Ce que ça veut dire pour toi |
| --- | --- |
| `Au centre de distribution de Paack` | Le colis est arrivé chez Paack. |

Peek, lui, affiche chaque étape avec ses propres mots, presque tous en français ; trois restent en anglais :

| Dans Peek | Ce que ça veut dire pour toi |
| --- | --- |
| `Informations d’expédition reçues` | La boutique a transmis ta commande à Paack, qui n'a pas encore le colis. |
| `Colis pris en charge` | Premier scan du colis par Paack, dans son centre de distribution. |
| `En transit` | Paack a le colis, qui n'est pas encore parti en livraison. |
| `Courier assigned` | Un livreur est affecté à ta livraison. La ligne revient avant chaque nouvelle tentative. |
| `En livraison` | Le livreur l'a : il passe dans le créneau affiché. |
| `Delivery attempt failed`, `Problème de livraison` | Pas livré cette fois : le livreur n'a pas pu, ou ton PaackGo Point était fermé, plein ou a refusé le colis. |
| `New delivery attempt scheduled` | Paack repasse, dans le nouveau créneau affiché. |
| `Prêt à être retiré` | Il t'attend au PaackGo Point ; la page de Paack donne ta date limite. |
| `Livré` | Livré, ou retiré au PaackGo Point. Pas chez toi ? Lis plus bas. |
| `Retour vers l’expéditeur en cours`, `Retourné à l’expéditeur` | Il repart chez la boutique, ou y est déjà : pas retiré à temps, refusé ou non payé au PaackGo Point, par exemple. |
| `Problème avec le colis` | Un problème sur le colis, par exemple abîmé ou perdu au PaackGo Point : contacte la boutique. |

## Paack livraison : créneau horaire et point relais

**Le créneau.** La page de suivi de Paack affiche le jour et le créneau de livraison prévus, à l'heure du pays de livraison. Après un passage raté, c'est celui de la nouvelle tentative.

**Les PaackGo Points** sont les points de retrait de Paack. Une fois le colis arrivé, la page de suivi donne le nom et l'adresse du point, un code de retrait, un code QR et ta date limite. Passé cette date, le colis repart chez la boutique.

## Colis Paack non livré, perdu ou livré mais non reçu

C'est la boutique qui confie ton colis à Paack, et dans l'UE, elle est responsable de tout dommage causé à ta commande « de l'expédition à la réception ». Annulation, remboursement ou colis abîmé : c'est donc avec elle que ça se règle.

- **Rien ne bouge :** lis [pourquoi un suivi ne bouge plus](guide:tracking-not-updating), puis contacte la boutique.
- **Livré mais pas reçu :** contacte la boutique ; la suite dans [colis livré mais non reçu](guide:delivered-not-received).
- **Jamais arrivé :** signale-le à la boutique et laisse-lui un délai supplémentaire raisonnable. Si elle ne livre toujours pas, tu peux annuler la commande et être remboursé.

## Paack contact : téléphone et service client

**Téléphone en France :** +33 1 87 65 31 89, du lundi au vendredi de 10 h à 20 h, le samedi et le dimanche de 10 h à 14 h.

## Suivre un colis Paack avec Peek

[Peek](/) reconnaît tout de suite comme Paack un numéro `PK` suivi de 18 chiffres, celui des colis que Paack livre pour Evri International, et le suit sans code postal. Pour un numéro d'étiquette de `5340` suivi de 14 chiffres, qu'on trouve sur des colis Paack, « Changer » propose Paack en premier sous « Correspond à ce numéro », devant d'autres transporteurs aux numéros de 18 chiffres. Pour un numéro de colis SHEIN (`C` ou `E`, une date en six chiffres, année, mois et jour, puis 8 chiffres), il y propose aussi Paack. Les autres numéros Paack n'ont pas de forme propre, et Peek ne peut pas demander à Paack s'il en connaît un. Tout autre numéro avec des lettres reste en général sans transporteur (« Peek le trouvera quand tu lanceras le suivi ») ; un numéro fait de chiffres peut ressembler à celui d'un autre transporteur, interrogé à la place de Paack. Appuie alors sur « Changer » et choisis Paack. Tu peux aussi coller un lien vers la page de suivi de Paack contenant `tracking_number=`, sur mydeliveries.paack.app ou paack.co/shipments/tracking.

Remplis alors le champ « Code postal de livraison ». Il est marqué facultatif, car un numéro `PK` n'en a pas besoin, mais Paack n'affiche les autres commandes qu'avec lui, et un code postal contenu dans le lien n'est pas repris. Paack ne répond que si le numéro et le code postal correspondent : sinon, le colis a l'air inconnu. Le code postal est envoyé à Paack et, si besoin, à des services de suivi. Le numéro est mis en majuscules, sans espaces, points ni tirets : si rien ne s'affiche, essaie aussi la page de Paack.

Tu vois le statut, l'historique, le jour et le créneau prévus, à l'heure de l'adresse de livraison. Au PaackGo Point, Peek affiche « Prêt à être retiré » avec le nom et l'adresse du point (pas encore vu sur un vrai colis), jamais le code de retrait, le code QR ni la date limite : pour ceux-là, ouvre la page de suivi de Paack. Les étapes s'affichent avec les mots de Peek ; un retrait au point apparaît comme « Livré ». Paack ne donne pas le lieu de ses scans (la carte n'en montre donc aucun) et ne nomme aucun autre transporteur à suivre. Pour un numéro `PK`, en revanche, l'historique vient du suivi d'Evri International, avec ses mots et ses lieux. Le nom de la boutique, ton nom et ton adresse ne sont pas repris. Le suivi est vérifié toutes les 10 minutes en journée, toutes les 2 minutes une fois le colis en livraison, et toutes les une à deux heures la nuit, avec des notifications au choix : « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ».

## Questions sur le suivi Paack

### Paack et Colis Privé, c'est la même société ?

Deux entreprises rapprochées, bientôt sous un même nom. Le 16 octobre 2026, Colis Privé a annoncé qu'après leur rapprochement, « finalisé à l'été 2026 », elles passent sous une marque commune, Paack, « progressivement déployée à partir de janvier 2027 ». D'ici là, la livraison et le suivi « restent accessibles dans les conditions habituelles » : suis ton colis chez le transporteur qui te l'annonce.

### Pourquoi Paack ne trouve pas ma commande ?

Paack vérifie le numéro et le code postal ensemble : si l'un des deux ne correspond pas à ta commande, il ne la trouve pas. Reprends le code postal de l'adresse de livraison et le numéro de commande ou d'étiquette. Si c'est Colis Privé qui t'a annoncé le colis, suis-le sur colisprive.fr.

### Combien de temps met Paack pour livrer ?

Ton jour et ton créneau s'affichent sur la page de suivi de Paack. Sans date annoncée, c'est le délai de la boutique qui compte : dans l'UE, elle doit te livrer dans les 30 jours, sauf si vous avez convenu d'un autre délai.

:::sources
- [Paack : Track your order](https://paack.co/shipments/tracking?lng=fr) – libellé « Au centre de distribution de Paack »
- [Paack Support : À propos de Paack](https://help.paack.co/fr/support/solutions/folders/44001208241) – téléphone, horaires
- [Colis Privé : Colis Privé et Paack s'unissent sous une marque commune : Paack](https://colisprive.fr/blog/colis-prive-et-paack-sunissent-sous-une-marque-commune-paack/) – annonce du 16 octobre 2026, rapprochement, pays, marque commune, janvier 2027, suivi inchangé
- [Colis Privé : Comment suivre mon colis ?](https://colisprive.fr/aide/comment-suivre-mon-colis-3/) – colisprive.fr, code postal
- [Colis Privé : Comment éviter les attaques de phishing ?](https://colisprive.fr/aide/comment-eviter-les-attaques-de-phishing/) – adresse des e-mails de suivi
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – 30 jours, dommages jusqu'à la réception, livraison non effectuée
- [Universal Parcel Scraper : Paack](https://github.com/plhery/universal-parcel-scraper/blob/main/carriers/paack/README.md) – numéro de commande ou code-barres et code postal, formes de numéro (`PK`, `5340`, SHEIN), étapes et mots que Peek enregistre, créneau, PaackGo Points
- [Universal Parcel Scraper : statuts Paack](https://github.com/plhery/universal-parcel-scraper/blob/main/carriers/paack/statuses.json) – étapes des PaackGo Points, nouvelle tentative
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek lit
:::
